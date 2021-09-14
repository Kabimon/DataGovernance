// 如果子应用无需自己的layout，subAppRoutes可去掉
export const subAppRoutes = {
  path: '',
  name: 'layout',
  // component: () => import('./view/layout.vue'),
  component: () => import('@/dss/view/layout.vue'),
  redirect: '/dataGovernance',
  meta: {
    title: 'DataGovernance',
    publicPage: true // 权限公开
  },
  children: []
}

const routes = [
  {
    path: '/dataGovernance',
    name: 'dataGovernance',
    redirect: '/dataGovernance/overview',
    component: () => import('./view/governance/index.vue'),
    meta: {
      title: '数据治理',
      publicPage: true
    },
    children: [
      {
        path: '/dataGovernance/overview',
        name: 'dataGovernance/overview',
        component: () => import('./module/dataGovernance/overview.vue'),
        meta: {
          title: '数据总览',
          publicPage: true
        }
      },
      {
        path: '/dataGovernance/assets',
        name: 'dataGovernance/assets',
        redirect: '/dataGovernance/assets/search',
        component: () => import('./module/dataGovernance/assetsIndex.vue'),
        meta: {
          title: '数据资产目录',
          publicPage: true,
          cover: 'dataGovernance/assets/search'
        },
        children: [
          {
            path: '/dataGovernance/assets/search',
            name: 'dataGovernance/assets/search',
            component: () => import('./view/assetsSearch/index.vue'),
            meta: {
              title: '数据资产目录',
              publicPage: true
            }
          },
          {
            path: '/dataGovernance/assets/info/:guid',
            name: 'dataGovernance/assets/info',
            component: () => import('./view/assetsInfo/index.vue'),
            meta: {
              title: '数据资产详情',
              publicPage: false
            }
          }
        ]
      }
    ]
  }
]

export default routes
