// 如果子应用无需自己的layout，subAppRoutes可去掉
export const subAppRoutes = {
  path: '',
  name: 'layout',
  // component: () => import('./view/layout.vue'),
  component: () => import('@/dss/view/layout.vue'),
  redirect: '/appdemo',
  meta: {
    title: 'appdemo',
    publicPage: true, // 权限公开
  },
  children: []
}

export default [
  {
    path: '/appdemo',
    name: 'appdemo',
    redirect: '/appdemo/demo1',
    meta: {
      title: 'appdemo',
      publicPage: true,
      icon: 'ios-albums'
    },
    component: () => import('./view/linkis/index.vue'),
    children: [{
      name: 'appdemo1',
      path: '/appdemo/demo1',
      component: () => import('./view/home/index.vue'),
      meta: {
        title: 'appdemo1',
        publicPage: true,
        icon: 'ios-person'
      },
    },
    {
      name: 'appdemo2',
      path: '/appdemo/demo2',
      component: () => import('./view/home/second.vue'),
      meta: {
        title: 'appdemo2',
        publicPage: true,
        icon: 'ios-person',
      }
    }
    ]
  }
]
