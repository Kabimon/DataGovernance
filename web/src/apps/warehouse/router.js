// 如果子应用无需自己的layout，subAppRoutes可去掉
export const subAppRoutes = {
  path: '',
  name: 'layout',
  // component: () => import('./view/layout.vue'),
  component: () => import('@/dss/view/layout.vue'),
  redirect: '/warehouse',
  meta: {
    title: 'DataGovernance',
    publicPage: true // 权限公开
  },
  children: []
}

export default [
  {
    path: '/warehouse',
    name: 'warehouse',
    redirect: '/warehouse/index',
    meta: {
      title: 'Warehouse',
      publicPage: true, // false可以隐藏菜单
      icon: 'ios-browsers'
    },
    component: () => import('./view/linkis/index.vue'),
    children: [
      {
        name: 'Warehouse home',
        path: '/warehouse/index',
        component: () => import('./view/home/index.vue'),
        meta: {
          title: 'Warehouse home',
          publicPage: true,
          icon: 'ios-paper'
        }
      },
      {
        name: 'Warehouse home2',
        path: '/warehouse/second',
        component: () => import('./view/home/second.vue'),
        meta: {
          title: 'Warehouse home2',
          publicPage: true,
          icon: 'ios-paper'
        }
      }
    ]
  }
]
