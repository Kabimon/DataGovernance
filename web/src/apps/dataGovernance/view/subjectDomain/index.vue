<template>
  <div class="console-page">
    <div class="top-line">
      <Input
        search
        enter-button
        placeholder="输入名称搜索"
        style="width: 300px"
        @on-search="handleSearch"
      />
      <Button type="primary" icon="md-add" @click="handleCreate">
        创建主题域
      </Button>
    </div>
    <Table
      :columns="columns"
      :data="datalist"
      :loading="loading"
      style="margin-bottom: 16px"
    >
      <template slot-scope="{ row }" slot="principalName">
        <Tag v-for="name of row.principalName.split(',')" :key="name">
          {{ name }}
        </Tag>
      </template>
      <template slot-scope="{ row }" slot="isAvailable">
        {{ row.isAvailable ? '启用' : '禁用' }}
      </template>

      <template slot-scope="{ row }" slot="action">
        <Button
          size="small"
          @click="handleEdit(row.id)"
          style="margin-right: 5px"
        >
          编辑
        </Button>
        <Button
          size="small"
          @click="handleDisable(row.id)"
          style="margin-right: 5px"
          v-if="row.isAvailable"
        >
          禁用
        </Button>
        <Button
          type="primary"
          size="small"
          @click="handleEnable(row.id)"
          style="margin-right: 5px"
          v-else
        >
          启用
        </Button>

        <Button type="error" size="small" @click="handleDelete(row.id)">
          删除
        </Button>
      </template>
    </Table>
    <div class="page-line">
      <Page
        :total="pageCfg.total"
        :current="pageCfg.page"
        :page-size="pageCfg.pageSize"
        @on-change="changePage"
      />
    </div>
    <EditModal
      :visible.sync="modalCfg.visible"
      :id="modalCfg.id"
      :mode="modalCfg.mode"
      @finish="handleModalFinish"
    />
  </div>
</template>

<script>
import EditModal from './editModal.vue'
import {
  getThemedomains,
  deleteThemedomains,
  enableThemedomains,
  disableThemedomains,
} from '../../service/api'
export default {
  components: { EditModal },
  methods: {
    handleModalFinish() {
      this.handleGetData()
    },
    handleCreate() {
      this.modalCfg = {
        visible: true,
        mode: 'create',
      }
    },
    async handleDelete(id) {
      this.loading = true
      await deleteThemedomains(id)
      this.loading = false
      this.handleGetData()
    },
    handleEdit(id) {
      this.modalCfg = {
        visible: true,
        mode: 'edit',
        id,
      }
    },
    async handleEnable(id) {
      this.loading = true
      await enableThemedomains(id)
      this.loading = false
      this.handleGetData()
    },
    async handleDisable(id) {
      this.loading = true
      await disableThemedomains(id)
      this.loading = false
      this.handleGetData()
    },
    handleSearch(name) {
      this.pageCfg.page = 1
      this.handleGetData(name)
    },
    async handleGetData(name) {
      this.loading = true
      let data = await getThemedomains(
        this.pageCfg.page,
        this.pageCfg.pageSize,
        name
      )
      this.loading = false
      let { current, pageSize, items, total } = data.page
      this.pageCfg.page = current
      this.pageCfg.pageSize = pageSize
      this.pageCfg.total = total
      this.datalist = items
    },
    changePage(page) {
      this.pageCfg.page = page
    },
  },
  mounted() {
    this.handleGetData()
  },
  watch: {
    pageCfg: {
      handler() {
        this.handleGetData()
      },
      deep: true,
    },
  },

  data() {
    return {
      columns: [
        {
          title: '主题域名称',
          key: 'name',
        },
        {
          title: '主题域英文名',
          key: 'enName',
        },
        {
          title: '状态',
          key: 'isAvailable',
          slot: 'isAvailable',
        },
        {
          title: '描述',
          key: 'description',
          ellipsis: true,
        },
        {
          title: '主题域选择权限',
          key: 'principalName',
          slot: 'principalName',
        },
        {
          title: '负责人',
          key: 'owner',
        },
        {
          title: '创建时间',
          key: 'createTime',
        },
        {
          title: '更新时间',
          key: 'updateTime',
        },
        {
          title: '操作',
          key: 'action',
          slot: 'action',
        },
      ],
      datalist: [],
      // 弹窗参数
      modalCfg: {
        mode: '',
        id: NaN,
        visible: false,
      },
      // 是否加载中
      loading: false,
      // 分页配置
      pageCfg: {
        page: 1,
        pageSize: 10,
        total: 20,
      },
    }
  },
}
</script>

<style lang="scss" scoped>
.top-line {
  margin-bottom: 16px;
  display: flex;
  justify-content: space-between;
}
.page-line {
  display: flex;
  justify-content: flex-end;
}
</style>
