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
export const subAppRoutes = {
  path: '',
  name: 'layout',
  component: () => import('./view/layout.vue'),
  redirect: '/warehouse',
  meta: {
    title: 'DataGovernance',
    publicPage: true, // 权限公开
  },
  children: []
}

export default [
  {
    path: 'warehouse',
    name: 'Warehouse',
    redirect: '/warehouse/index',
    meta: {
      title: 'Warehouse',
      publicPage: true,
    },
    component: () => import('./view/linkis/index.vue'),
    children: [{
      name: 'Warehouse home',
      path: 'index',
      component: () => import('./view/home/index.vue'),
      meta: {
        title: 'Warehouse home',
        publicPage: true,
      },
    },
    {
      name: 'Warehouse home2',
      path: 'index2',
      component: () => import('./view/home/index.vue'),
      meta: {
        title: 'Warehouse home',
        publicPage: true,
      },
    }
    ]
  }
]
