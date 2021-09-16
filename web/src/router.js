/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import VueRouter from "vue-router";
import { routes, subRoutes } from './dynamic-apps'

// 解决重复点击路由跳转报错
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const router = new VueRouter({
  routes,
  isAddDynamicMenuRoutes: false, // 是否已经添加动态(菜单)路由
});

// 如有需要，可在beforeEach中添加动态(菜单)路由
router.beforeEach((to, from, next) => {
  if (router.options.isAddDynamicMenuRoutes) {
    if (to.meta) {
      // 给路由添加参数，控制显示对应header
      if (to.meta.header) {
        to.query.showHeader = to.meta.header
      }
      if (to.meta.publicPage) {
        // 公共页面不需要权限控制（404，500）
        next();
      } else {
        next('/404')
      }
    }
  } else {
    router.options.isAddDynamicMenuRoutes = true;
    // 根据apps下的各个子应用的routes集成的subRoutes来生成菜单
    sessionStorage.setItem('menuList', JSON.stringify(subRoutes.children || []))
    if (to.meta) {
      // 给路由添加参数，控制显示对应header
      if (to.meta.header) {
        to.query.showHeader = to.meta.header
      }
      if (to.meta.publicPage) {
        // 公共页面不需要权限控制（404，500）
        next();
      } else {
        next('/404')
      }
    }
  }
});

router.afterEach((to) => {
  if (to.meta) {
    document.title = to.meta.title || 'DataGovernance';
  }
});

export default router;
