<template>
  <Modal
    title="新建/编辑主题域"
    v-model="visible"
    @on-ok="handleOk"
    @on-cancel="handleCancel"
    :loading="visible"
  >
    <Form
      ref="formRef"
      :rules="ruleValidate"
      :model="formState"
      label-position="top"
    >
      <FormItem label="名称" prop="name">
        <Input v-model="formState.name" placeholder="名称"></Input>
      </FormItem>
      <FormItem label="英文名" prop="enName">
        <Input v-model="formState.enName" placeholder="英文名"></Input>
      </FormItem>
      <FormItem label="负责人" prop="owner">
        <Input v-model="formState.owner" placeholder="负责人"></Input>
      </FormItem>
      <FormItem label="可用角色" prop="owner">
        <Select
          v-model="formState.principalName"
          multiple
          placeholder="可用角色"
        >
          <Option
            v-for="item in authorityList"
            :value="item.value"
            :key="item.value"
          >
            {{ item.label }}
          </Option>
        </Select>
      </FormItem>
      <FormItem label="描述" prop="description">
        <Input
          type="textarea"
          v-model="formState.description"
          placeholder="描述"
        ></Input>
      </FormItem>
      <Spin v-if="loading" fix></Spin>
    </Form>
  </Modal>
</template>

<script>
import {
  createThemedomains,
  getThemedomainsById,
  editThemedomains,
} from '../../service/api'
export default {
  props: {
    // 是否可见
    visible: {
      type: Boolean,
      required: true,
    },
    // 模式
    mode: {
      type: String,
      required: true,
    },
    id: {
      type: Number,
    },
  },
  emits: ['finish', 'update:visible'],

  watch: {
    visible(val) {
      if (val && this.id) this.handleGetById(this.id)
    },
  },
  data() {
    return {
      // 验证规则
      ruleValidate: {
        name: [
          {
            required: true,
          },
        ],
        enName: [
          {
            required: true,
          },
        ],
        owner: [
          {
            required: true,
          },
        ],
      },
      // 是否加载中
      loading: false,
      // 表单数据
      formState: {
        name: '',
        enName: '',
        owner: '',
        principalName: [],
        description: '',
      },
      authorityList: [
        {
          value: 'New York',
          label: 'New York',
        },
        {
          value: 'London',
          label: 'London',
        },
      ],
    }
  },
  methods: {
    async handleGetById(id) {
      this.loading = true
      let { item } = await getThemedomainsById(id)
      this.loading = false
      this.formState.name = item.name
      this.formState.enName = item.enName
      this.formState.owner = item.owner
      this.formState.principalName = item.principalName.split(',')
      this.formState.description = item.description
    },
    async handleCancel() {
      this.$refs['formRef'].resetFields()
    },
    handleOk() {
      this.$refs['formRef'].validate(async (valid) => {
        if (valid) {
          try {
            if (this.mode === 'create') {
              this.loading = true
              await createThemedomains(
                Object.assign({}, this.formState, {
                  principalName: this.formState.principalName.join(','),
                })
              )
              this.loading = false
            }
            if (this.mode === 'edit') {
              this.loading = true
              await editThemedomains(
                this.id,
                Object.assign({}, this.formState, {
                  principalName: this.formState.principalName.join(','),
                })
              )
              this.loading = false
            }
            this.$refs['formRef'].resetFields()
            this.$emit('update:visible', false)
            this.$emit('finish')
          } catch (error) {
            console.log(error)
          }
        }
      })
    },
  },
}
</script>

<style scoped lang="less"></style>
