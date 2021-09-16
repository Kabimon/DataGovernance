<template>
  <div class="layout-body" :class="{ 'layout-top': !showHeader}">
    <layout-header
      v-show="showHeader"
      @clear-session="clearSession"
      @set-init="setInit"></layout-header>

    <sidebar v-if="showSideBar" />

    <div :class="getClass()">
      <iframe
        v-if="showIframe"
        :src="$route.meta.iframeUrl"
        width="100%" height="100%" frameborder="0" scrolling="yes">
      </iframe>

      <template v-else>
        <keep-alive v-if="isInit">
          <router-view v-if="$route.meta.keepAlive"/>
        </keep-alive>
        <router-view v-if="!$route.meta.keepAlive"/>
        <layout-footer v-show="showFooter"/>
      </template>
    </div>
  </div>
</template>
<script>
import headerModule from '@/dss/module/header';
import footerModule from '@/dss/module/footer';
import layoutMixin from '@/common/service/layoutMixin.js';
import sidebar from './sidebar.vue';
export default {
  components: {
    layoutFooter: footerModule.component,
    layoutHeader: headerModule.component,
    sidebar
  },
  data() {
    return {
      isInit: false
    }
  },
  mixins: [layoutMixin],
  computed: {
    showSideBar() {
      return location.hash.indexOf('dataGovernance/') < 0
    },
    showHeader() {
      return this.$route.query.noHeader || location.search.indexOf('noHeader') < 0
    },
    showFooter() {
      return this.$route.query.noFooter || location.search.indexOf('noFooter') < 0
    },
    showIframe() {
      if (this.$route.meta) {
        return /^http[s]?:\/\/.*/.test(this.$route.meta.iframeUrl) ? true : false;
      }
      return false;
    }
  },
  methods: {
    setInit() {
      this.isInit = true;
    },
    getClass() {
      if (location.hash.indexOf('warehouse/') > -1) {
        return 'main-content-wrap'
      } else {
        return 'main-content-wrap no-margin'
      }
    }
  },
};
</script>
<style lang="scss" scoped>
.main-content-wrap {
  position: relative;
  padding-top: 54px;
  margin-left: 240px;
  height: 100%;
  background: #f1f4f5;
  &.no-margin {
     margin-left: 0;
  }
}
</style>
