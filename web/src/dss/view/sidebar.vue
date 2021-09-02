<template>
  <div class="main-sidebar">
    <Menu class="menu-wrap" :active-name="activeMenu" :open-names="openMenu">
      <sidebar-sub-menu
        v-for="menu in menuList"
        :key="menu.path"
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
  computed: {
    activeMenu() {
      // 以route的path做menu的name，方便处理open-names
      return this.$route.path
    },
    openMenu() {
      return [this.$route.path.split('/').slice(0, 2).join('/')]
    }
  },
  created() {
    this.menuList = JSON.parse(sessionStorage.getItem('menuList') || '[]')
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
  width: 240px;
  overflow: hidden;
  .menu-wrap {
    height: 100%;
  }
}
</style>

