<template>
  <div v-if="menu.meta.publicPage">
    <Submenu
      v-if="menu.children && menu.children.length >=1"
      :name="menu.path"
    >
      <template slot="title">
        <Icon :type="menu.meta.icon"></Icon>
        {{menu.meta.title}}
      </template>
      <sidebar-sub-menu
        v-for="item in menu.children" 
        :key="item.path"
        :menu="item"
      />
    </Submenu>
    <Menu-item v-else :name="menu.path">
      <Icon :type="menu.meta.icon" v-if="menu.meta.icon"></Icon>
      {{menu.meta.title}}
      <router-link :to="menu.path" class="menu-item">
      </router-link>
    </Menu-item>
  </div>
</template>
<script>
export default {
  name: 'sidebar-sub-menu',
  props: {
    menu: {
      type: Object,
      required: true
    }
  }
};
</script>
<style lang="scss" scoped>
.menu-item {
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  color: inherit;
}
</style>
