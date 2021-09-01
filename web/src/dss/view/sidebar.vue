<template>
  <div class="main-sidebar">
    <Menu @on-select="gotoRouteHandle">
      <sidebar-sub-menu
        v-for="menu in menuList"
        :key="menu.menuId"
        :menu="menu"
      />
    </Menu>
  </div>
</template>
<script>
import SidebarSubMenu from './sidebar-sub-menu.vue';
export default {
  name: 'sidebar',
  components: {
    SidebarSubMenu
  },
  data() {
    return {
      menuActiveName: 'hive',
      menuList: []
    }
  },
  created() {
    // menuList可在router.beforeEach中生成
    this.menuList = [
      {
        menuId: 1,
        name: 'warehouse',
        title: '数据仓库',
        icon: 'ios-people',
        list: [
          {
            menuId: 'warehouse_1',
            name: 'hive',
            title: 'hive',
            path: '/warehouse/index'
          },
          {
            menuId: 'warehouse_2',
            name: 'spark',
            title: 'spark',
            path: '/warehouse/linkis'
          }
        ]
      },
      {
        menuId: 2,
        name: 'hadoop',
        title: '数据治理',
        icon: 'ios-people',
        list: [
          {
            menuId: 'hadoop_1',
            name: 'hdfs',
            title: 'hdfs',
            path: '/warehouse/iframe'
          },
          {
            menuId: 'hadoop_2',
            name: 'yarn',
            title: 'yarn',
            path: '/warehouse/index'
          }
        ]
      }
    ]
    this.menuRoutes = this.flatMenu(this.menuList)
  },
  methods: {
    flatMenu(menuList = [], routes = []) {
      var temp = []
      for (var i = 0; i < menuList.length; i++) {
        if (menuList[i].list && menuList[i].list.length >= 1) {
          temp = temp.concat(menuList[i].list)
        } else {
          routes.push(menuList[i])
        }
      }
      if (temp.length >= 1) {
        return this.flatMenu(temp, routes)
      } else {
        return routes
      }
    },
    gotoRouteHandle(menuName) {
      const route = this.menuRoutes.find(i => i.name == menuName)
      this.$router.push({ path: route.path })
    }
  }
};
</script>
<style lang="scss" scoped>
.main-sidebar {
  position: fixed;
  top: 54px;
  left: 0;
  bottom: 0;
  z-index: 1020;
  width: 230px;
  overflow: hidden;
}
</style>

