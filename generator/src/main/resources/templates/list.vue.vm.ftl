<template>
    <div>
        <br>
        <!--工具栏-->
        <Row type="flex" justify="start" class="code-row-bg margin-bottom-10">
            <Col span="4"><Button @click="add" v-if="wShow">新增</Button> <Button @click="disable" v-if="rwShow">禁用</Button> <Button @click="enable" v-if="rwShow">启用</Button></Col>
            <Col span="4" offset="14"><Input placeholder="请输入..." v-model="searchContent" v-if="rShow"/></Col>
            <Col span="1"><Button type="primary" icon="ios-search" @click="search" v-if="rShow">搜索</Button></Col>
        </Row>
        <!--表格-->
        <Table :data="tableData" :columns="tableColumns" stripe ref="table" height="522" v-if="rShow"  @on-sort-change="changeSort"></Table>
        <!--分页-->
        <div style="margin: 10px;overflow: hidden" v-if="rShow">
            <div style="float: right;">
                <Page :total="total" :current="current" :page-size="pageSize" @on-change="changePage" @on-page-size-change="changePageSize" show-total show-elevator show-sizer></Page>
            </div>
        </div>
        <!--模态框-->
        <Modal v-model="modal1" :title="modalTitle" @on-ok="ok" @on-cancel="cancel" :loading="loading">
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                <FormItem prop="id">
                    <input v-model="formValidate.id" name="id" hidden="true"/>
                </FormItem>
         <#list table.fields as column>
             <#if (column.keyFlag == false && column.propertyName != 'createTime' && column.propertyName != 'updateTime' && column.propertyName != 'status')>
                  <FormItem label="${column.comment}" prop="${column.propertyName}">
                        <Input v-model="formValidate.${column.propertyName}" placeholder="请输入${column.comment}"></Input>
                    </FormItem>
            </#if>
            <#if (column.propertyName == 'status')>
                    <FormItem label="${column.comment}" prop="${column.propertyName}">
                        <i-switch v-model="formValidate.status" size="large" >
                            <span slot="close">禁用</span>
                            <span slot="open">正常</span>
                        </i-switch>
                    </FormItem>
            </#if>
         </#list>
            </Form>
        </Modal>
    </div>
</template>
<script>
    import util from '@/libs/util';
    export default{
        data () {
            return {
                wShow:false,
                rShow:false,
                rwShow:false,
                modalTitle:"新增",
                order:'',
                sort:'',
                formValidate: {
                    id:'',
                    <#list table.fields as column>
                        <#if (column.keyFlag == false)>
                    ${column.propertyName}:'',
                        </#if>
                    </#list>
                },
                searchContent:'',
                ruleValidate: {
                 <#list table.fields as column>
                     <#if (column.keyFlag == false)>
                          ${column.propertyName}:[
                         {
                             required:true,
                             message: '${column.comment}不能为空',
                             trigger:'blur'
                         }],
                     </#if>
                 </#list>
                },
                modal1: false,
                pageSize:10,
                current:1,
                total:100,
                loading:true,
                tableData: this.mocktableData(),
                tableColumns: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: 'ID',
                        key: 'id',
                        sortable: true
                    },
                <#list table.fields as column>
                    <#if (column.keyFlag == false && column.propertyName != 'createTime' && column.propertyName != 'updateTime' && column.propertyName !='status')>
                     {
                         title: '${column.comment}',
                         key: '${column.propertyName}',
                         sortable: true
                     },
                    </#if>
                    <#if column.propertyName == 'createTime'>
                     {
                         title: '创建时间',
                         key: 'createTime',
                         sortable: true,
                         render: (h, params) => {
                         const row = params.row;
                            return h('div', util.dateTimeFormat(row.createTime));
                        }
                    },
                    </#if>
                    <#if column.propertyName == 'updateTime'>
                     {
                         title: '更新时间',
                                 key: 'updateTime',
                             sortable: false,
                             render: (h, params) => {
                         const row = params.row;
                         return h('div', util.dateTimeFormat(row.updateTime));
                     }
                     },
                    </#if >
                    <#if column.propertyName == 'status'>
                    {
                        title: '状态',
                                key: 'status',
                            sortable: true,
                            render: (h, params) => {
                        const row = params.row;
                        const color = row.status==0 ?'green' : 'red';
                        const text = row.status==0 ? '正常' : '禁用';
                        return h('Tag', {
                            props: {
                                type: 'dot',
                                color: color
                            }
                        }, text);
                    }
                    },
                    </#if>

                </#list>
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            if(this.wShow)
                            {
                                return h('div', [
                                    h('Button', {
                                        props: {
                                            type: 'primary',
                                            size: 'small'
                                        },
                                        style: {
                                            marginRight: '5px'
                                        },
                                        on: {
                                            click: () => {
                                                this.show(params.index)}
                                        }
                                    }, '编辑'),
                                    h('Button', {
                                        props: {
                                            type: 'error',
                                            size: 'small'
                                        },
                                        on: {
                                            click: () => {
                                                this.remove(params.index)}
                                        }
                                    }, '删除')]);
                            }
                            else
                            {
                                return null;
                            }
                        }
                    }
                    ]
                    }
                    },
        methods: {
            mocktableData (offset) {
                let retData = [];
                if(offset == undefined)
                {
                    offset = 0;
                }
                var temp = {
                    'limit': this.pageSize, //页面大小
                    'offset': offset, //页码
                    'search[order]':this.order,
                    'search[sort]':this.sort
                };
                var that = this;
                util.ajaxGet('/admin/${table.entityPath}/all', {
                    params: temp,
                }).then((response)=>{
                var data = response.data;
                that.current = data.curPage;
                that.total = data.total;
                var rows = data.rows;
                for (let i = 0; i < rows.length; i++) {
                    retData.push({
                    <#list table.fields as column>
                        ${column.propertyName}:rows[i].${column.propertyName},
                    </#list>
                    })
                }
            })
            .catch(function (response) {
                });
                return retData;
            },
            changePage (index) {
                // 这里直接更改了模拟的数据，真实使用场景应该从服务端获取数据
                var offset = (index-1) * this.pageSize;
                this.tableData = this.mocktableData(offset);
            },
            changePageSize (size)
            {
                var offset = 0;
                this.pageSize = size;
                this.tableData = this.mocktableData(offset);
            },
            changeSort(cko)
            {
                this.order = cko.order;
                this.sort = cko.key;
                this.tableData = this.mocktableData(0);
            },
            show (index) {
                this.modal1 = true;
                this.modalTitle ="编辑";
                this.$refs['formValidate'].resetFields();
                var id = this.tableData[index].id;
                var that = this;
                util.ajaxGet('/admin/${table.entityPath}/'+id).then((response)=>{
                    var data = response.data;
                <#list table.fields as column>
                <#if (column.keyFlag == false && column.propertyName != 'createTime' && column.propertyName != 'updateTime' && column.propertyName !='status')>
                    that.formValidate.${column.propertyName} = data.${column.propertyName};
                </#if>
                <#if (column.propertyName == 'status')>
                    that.formValidate.${column.propertyName} = data.${column.propertyName} ==0?true:false;
                </#if>
                </#list>
                }).catch(function (response) {
                    that.$Message.info('fail');
                });
            },
            add() {
                this.modal1 = true;
                this.modalTitle = "新增";
                this.$refs['formValidate'].resetFields();
<#list table.fields as column>
<#if (column.propertyName == 'status')>
                this.formValidate.${column.propertyName} = true;
</#if>
</#list>
            },
            remove (index) {
                this.$Modal.confirm({
                    title: '提示',
                    content: '<p>确定要删除吗?</p><p></p>',
                    onOk: () => {
                        var ids = this.tableData[index].id;
                        var that = this;
                        util.ajax({'/admin/${table.entityPath}/'+ids, method: 'delete'}).then((response)=>{
                            that.tableData.splice(index, 1);
                            that.$Message.success('success');
                        }).catch(function (response) {
                            that.$Message.error('fail');
                        });
                    }
                });
            },
            changeLoading() {
                this.loading = false;
                this.$nextTick(() => {
                    this.loading = true;
            });
            },
            ok () {
                this.$refs['formValidate'].validate(valid => {
				if(!valid) {
				return this.changeLoading();
				}
				//请求提交表单
				var ${table.entityPath} = {};
<#list table.fields as column>
                ${table.entityPath}['${column.propertyName}'] = this.formValidate.${column.propertyName};
</#list>
                var method = 'post';
                if(this.formValidate.id != '')
                {
                    method = 'put';
                }
                var that = this;
                util.ajax({
				url: '/admin/${table.entityPath}',
				method: method,
				data: ${table.entityPath}
				}).then((response)=>{
                    that.$Message.success('success');
				})
				.catch(function (response) {
                    that.$Message.error('fail');
				});
				setTimeout(() => {
				this.changeLoading();
				this.modal1 = false;
				}, 1000);
				});
            },
            cancel () {
            },
            search (){
                this.tableData = this.mocktableData(0);
            },
            disable () {
                var ids = util.getSelectIds(this.$refs['table'].getSelection());
                if(ids == '')
                {
                    this.$Message.warning('请选择一个');
                    return;
                }
                if(ids.split(",").length > 1)
                {
                    this.$Message.warning('仅能选择一个');
                    return;
                }
                this.$Modal.confirm({
                    title: '提示',
                    content: '<p>确定要禁用吗?</p><p></p>',
                    onOk: () => {
                        var postData = {id:ids};
                        var that = this;
                        util.ajax({
                            url: '/admin/${table.entityPath}/disable',
                            method: 'post',
                            data: postData
                        }).then((response)=>{
                            that.$Message.success('success');
                        }).catch(function (response) {
                            that.$Message.error('fail');
                        });
                    }
                });
            },
            enable () {
                var ids = util.getSelectIds(this.$refs['table'].getSelection());
                if(ids == '')
                {
                    this.$Message.warning('请选择一个');
                    return;
                }
                if(ids.split(",").length > 1)
                {
                    this.$Message.warning('仅能选择一个');
                    return;
                }
                this.$Modal.confirm({
                    title: '提示',
                    content: '<p>确定要启用吗?</p><p></p>',
                    onOk: () => {
                        var postData = {id:ids};
                        var that = this;
                        util.ajax({
                            url: '/admin/${table.entityPath}/enable',
                            method: 'post',
                            data: postData
                        }).then((response)=>{
                            that.$Message.success('success');
                        }).catch(function (response) {
                            that.$Message.error('fail');
                        });
                    }
                });
            }
        },
        mounted () {
            var perm = sessionStorage.getItem("user");
            if(perm == 11 || perm == 1)
            {
                this.wShow = true;
            }
            if(perm == 11 || perm == 10)
            {
                this.rShow = true;
            }
            if(perm == 11)
            {
                this.rwShow = true;
            }
        }
    }
</script>
