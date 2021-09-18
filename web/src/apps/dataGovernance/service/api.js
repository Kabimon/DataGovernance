import API_PATH from '@/common/config/apiPath.js'
import api from '@/common/service/api'

/**
 * 数据资产概要
 * @returns {Object.result}
 *
 */
const getHiveSummary = () =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveSummary`, 'get')

/**
 * 查询hive表--基础&列
 * @param {*} guid
 * @returns
 */
const getHiveTblBasic = guid =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveTbl/${guid}/basic`, {}, 'get')

/**
 * 查询hive表-分区信息
 * @param {*} guid
 * @returns
 */
const getHiveTblPartition = guid =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveTbl/${guid}/partition`, {}, 'get')

/**
 * 查询hive表--select语句
 * @param {*} guid
 * @returns
 */
const getSelectSql = guid =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveTbl/${guid}/select`, {}, 'get')

/**
 * 查询hive表--create语句
 * @param {*} guid
 * @returns
 */
const getSelectDdl = guid =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveTbl/${guid}/create`, {}, 'get')

/**
 * 搜索hive表
 * @param {query}
 * @returns
 */
const getHiveTbls = params =>
  api.fetch(
    `${API_PATH.DATA_GOVERNANCE}hiveTbl/search?query=${params.query}&owner=${params.owner}&limit=${params.limit}&offset=${params.offset}`,
    {},
    'get'
  )

/*
 * 查询hive表--血缘
 * @param {*} guid
 * @returnsL
 */
const getLineage = guid =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveTbl/${guid}/lineage`, {}, 'get')

/**
 *  批量修改注释
 * @params {Map}
 * @returns
 */
const putCommetBulk = params => {
  return api.fetch(`${API_PATH.DATA_GOVERNANCE}comment/bulk`, params, 'put')
}

/**
 * 存储量前10表
 * @params {void}
 * @returns Array
 */
const getTopStorage = () =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}hiveTbl/topStorage`, {}, 'get')

/**
 * 设置标签--表或列
 * @params {guid}
 * @returns String
 */
const postSetLabel = (guid, params) =>
  api.fetch(`${API_PATH.DATA_GOVERNANCE}label/${guid}`, params, 'post')

/**
 * 修改注释--表或列
 * @params {guid, Obiect}
 * @returns String
 */
const postSetComment = (guid, comment) =>
  api.fetch(
    `${API_PATH.DATA_GOVERNANCE}comment/${guid}?comment=${comment}`,
    {},
    'put'
  )

/**
 * 负责人查询
 * @params {workspaceId}
 * @returns Array
 */
const getWorkspaceUsers = (workspackId, search) =>
  api.fetch(
    `${API_PATH.DATA_GOVERNANCE}getWorkspaceUsers/${workspackId}/${search}`,
    {},
    'get'
  )

/**
 * 查询主主题域
 * @params {workspaceId}
 * @returns Array
 */
const getThemedomains = (page = 1, size = 10, name) =>
  api.fetch(`/governance/warehouse/themedomains`, { page, size, name }, 'get')

/**
 * 创建主体域
 * @params {workspaceId}
 * @returns Array
 */
const createThemedomains = body =>
  api.fetch(`/governance/warehouse/themedomains`, body, 'post')

/**
 * 删除主题域
 * @params {id}
 * @returns Any
 */
const deleteThemedomains = id =>
  api.fetch(`/governance/warehouse/themedomains/${id}`, {}, 'delete')

/**
 * 禁用主题域
 * @params {workspaceId}
 * @returns Array
 */
const disableThemedomains = id =>
  api.fetch(`/governance/warehouse/themedomains/${id}/disable`, {}, 'put')

/**
 * 启用主题域
 * @params {workspaceId}
 * @returns Array
 */
const enableThemedomains = id =>
  api.fetch(`/governance/warehouse/themedomains/${id}/enable`, {}, 'put')

/**
 * 根据id获取主题
 * @params {workspaceId}
 * @returns Array
 */
const getThemedomainsById = id =>
  api.fetch(`/governance/warehouse/themedomains/${id}`, {}, 'get')

/**
 * 编辑主题
 * @params {workspaceId}
 * @returns Array
 */
const editThemedomains = (id, body) =>
  api.fetch(`/governance/warehouse/themedomains/${id}`, body, 'put')

/**
 * 查询所有预置分层
 * @params {workspaceId}
 * @returns Array
 */
export const getLayersPreset = () =>
  api.fetch(`/governance/warehouse/layers/preset`, {}, 'get')
/**
 * 分页查询自定义分层
 * @params {workspaceId}
 * @returns Array
 */
export const getLayersCustom = (page = 1, size = 10) =>
  api.fetch(`/governance/warehouse/layers/custom`, { page, size }, 'get')

/**
 * 新增自定义分层
 * @params {workspaceId}
 * @returns Array
 */
export const createLayersCustom = body =>
  api.fetch(`/governance/warehouse/layers/custom`, body, 'post')

/**
 * 根据ID查询某个分层信息
 * @params {workspaceId}
 * @returns Array
 */
export const getLayersById = id =>
  api.fetch(`/governance/warehouse/layers/${id}`, {}, 'get')

/**
 * 编辑分层
 * @params {workspaceId}
 * @returns Array
 */
export const editLayersCustom = (id, body) =>
  api.fetch(`/governance/warehouse/layers/${id}`, body, 'put')

/**
 * 删除分层
 * @params {workspaceId}
 * @returns Array
 */
export const deleteLayers = id =>
  api.fetch(`/governance/warehouse/layers/${id}`, {}, 'delete')

/**
 * 禁用分层
 * @params {workspaceId}
 * @returns Array
 */
export const disableLayers = id =>
  api.fetch(`/governance/warehouse/layers/${id}/disable`, {}, 'put')

/**
 *  启用分层
 * @params {workspaceId}
 * @returns Array
 */
export const enableLayers = id =>
  api.fetch(`/governance/warehouse/layers/${id}/enable`, {}, 'put')

export {
  getHiveSummary,
  getHiveTblBasic,
  getHiveTblPartition,
  getSelectSql,
  getSelectDdl,
  getHiveTbls,
  getLineage,
  putCommetBulk,
  getTopStorage,
  postSetLabel,
  postSetComment,
  getWorkspaceUsers,
  getThemedomains,
  createThemedomains,
  deleteThemedomains,
  disableThemedomains,
  enableThemedomains,
  getThemedomainsById,
  editThemedomains
}
