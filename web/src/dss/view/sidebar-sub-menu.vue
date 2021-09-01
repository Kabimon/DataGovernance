<template>
  <Submenu
    v-if="menu.list && menu.list.length >=1"
    :name="menu.name"
  >
    <template slot="title">
      <Icon :type="menu.icon"></Icon>
      {{menu.title}}
    </template>
    <sidebar-sub-menu
      v-for="item in menu.list" 
      :key="item.menuId"
      :menu="item"
    />
  </Submenu>
  <Menu-item v-else :name="menu.name" >
    <Icon :type="menu.icon" v-if="menu.icon"></Icon>
    {{menu.title}}
  </Menu-item>
</template>
<script>
import SidebarSubMenu from './sidebar-sub-menu.vue';
export default {
  name: 'sidebar-sub-menu',
  components: {
    SidebarSubMenu
  },
  props: {
    menu: {
      type: Object,
      required: true
    }
  }
};
</script>

