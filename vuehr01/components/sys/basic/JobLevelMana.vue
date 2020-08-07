<template>
    <div>
        <div>
            <el-input
                size="small"
                style="width:300px;"
                placeholder="添加职称..."
                prefix-icon="el-icon-plus"
                v-model="jl.name">
            </el-input>

            <el-select
                    v-model="jl.titleLevel"
                    placeholder="职称等级"
                    size="small"
                    style="width:120px; margin-left: 5px; margin-right: 5px;">
                <el-option
                        v-for="item in titleLevels"
                        :key="item"
                        :label="item"
                        :value="item">
                </el-option>
            </el-select>
            <el-button icon="el-icon-plus" type="primary" size="small" @click="addJobLevel">添加</el-button>
        </div>


        <div style="margin-top: 12px;">

            <el-table
                    :data="jls"
                    stripe
                    border
                    v-loading="loading"
                    element-loading-text="正在加载"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    @selection-change="handleSelectionChange"
                    size="small"
                    style="width: 80%">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="编号"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职称名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="titleLevel"
                        label="职称级别">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        label="是否启用">
                                <template slot-scope="scope">
                                    <el-tag size="small" type="success" v-if="scope.row.enabled">已启用</el-tag>
                                    <el-tag size="small" type="denger" v-else>未启用</el-tag>
                                </template>
                </el-table-column>
                <el-table-column
                        label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" @click="showEditView(scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="deleteHandleJl(scope.row)">删除</el-button>
                    </template>
                </el-table-column>


            </el-table>
            <el-button @click="deleteMany" type="danger" size="small" style="margin-top: 10px" :disabled="multipleSelection.length==0">批量删除</el-button>
        </div>


            <el-dialog
                    title="修改职称"
                    :visible.sync="dialogVisible"
                    width="30%">
                <div>
                      <table>
                          <tr>
                              <td><el-tag>职称名</el-tag></td>
                              <td><el-input size="small" v-model="updateJl.name"></el-input></td>
                          </tr>
                          <tr>
                              <td><el-tag>职称级别</el-tag></td>
                              <td>
                                  <el-select
                                          placeholder="职称等级"
                                          size="small"
                                          style="width:120px; margin-left: 5px; margin-right: 5px;"
                                          v-model="updateJl.titleLevel">
                                      <el-option
                                              v-for="item in titleLevels"
                                              :key="item"
                                              :label="item"
                                              :value="item">
                                      </el-option>
                                  </el-select>
                              </td>
                          </tr>
                          <tr>
                              <td><el-tag>是否启用</el-tag></td>
                              <td><el-switch   active-text="启用" inactive-text="禁用" v-model="updateJl.enabled"></el-switch></td>
                          </tr>
                      </table>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                    <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
            </el-dialog>


    </div>
</template>
<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import ElSwitch from "../../../../node_modules/element-ui/packages/switch/src/component";
    export default{
        components: {
            ElSwitch,
            ElInput,
            ElButton},
        name: 'JobLevelMana',

        data(){
            return{
                loading:false,
                jl:{
                    name:'',
                    titleLevel:''
                },
                dialogVisible:false,
                updateJl:{
                    name:'',
                    titleLevel:'',
                    enabled:false
                },
                titleLevels:[
                    '正高级',
                    '副高级',
                    '中级',
                    '初级',
                    '员级'
                ],
                multipleSelection: [],
                jls:[]
            }
        },
        mounted(){
            this.initJls();
        },
        methods:{
            initJls(){
                this.loading=true;
                this.getRequest("/system/basic/joblevel/").then(resp=>{
                    this.loading=false;
                    if(resp){
                        this.jls=resp;
                    }
                })
            },
            addJobLevel(){
                if(this.jl.name && this.jl.titleLevel){
                    this.postRequest("/system/basic/joblevel/",this.jl).then(resp=>{
                        if(resp){
                            this.initJls();
                            this.jl={
                                name:'',
                                titleLevel:''
                            }
                        }
                    })
                }else{
                    this.$message.error("输入框字段不能为空");
                }

            },
            deleteHandleJl(data){
                this.$confirm('此操作将永久删除「'+data.name+'」, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/joblevel/"+data.id).then(resp=>{
                        if(resp){
                            this.initJls();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            showEditView(data){

                Object.assign(this.updateJl,data);
                this.dialogVisible=true;
            },
            doUpdate(){
                this.putRequest("/system/basic/joblevel/",this.updateJl).then(resp=>{
                    if(resp){
                        this.initJls();
                        this.dialogVisible=false;
                    }

                })
            },
            handleSelectionChange(val){
                this.multipleSelection=val;
            },
            deleteMany(){
                this.$confirm('此操作将永久删除「'+this.multipleSelection.length+'」, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids='?';

                    this.multipleSelection.forEach(item=>{
                        ids +='ids='+item.id+'&';
                    })

                    this.deleteRequest("/system/basic/joblevel/"+ids).then(resp=>{
                        if(resp){
                            this.initJls();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        }
    }
</script>
<style scoped>
</style>