<template>
    <div style="width: 500px;">
        <el-input
                placeholder="请输入部门名称进行搜索..."
                v-model="filterText"></el-input>
        <el-tree
                :data="deps"
                :props="defaultProps"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                ref="tree">

            <span class="custom-tree-node" style="display: flex; justify-content: space-between; width: 100%;" slot-scope="{ node, data }">
                <span>{{ data.name }}</span>
                <span>
                  <el-button
                          type="primary"
                          size="mini"
                          class="dpbtn"
                          @click="() => showAddDepView(data)">
                    添加部门
                  </el-button>
                  <el-button
                          type="danger"
                          size="mini"
                          class="dpbtn"
                          @click="() => deleteDep(data)">
                    删除部门
                  </el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog
                title="添加部门"
                :visible.sync="dialogVisible"
                width="30%">
            <div>
                   <table>
                       <tr>
                           <td><el-tag>上级部门</el-tag></td>
                           <td>{{pname}}</td>
                       </tr>
                       <tr>
                           <td><el-tag>部门名称</el-tag></td>
                           <td><el-input
                                   placeholder="请输入部门名称..."
                                   prefix-icon="el-icon-search"
                                   v-model="filterText">

                           </el-input></td>
                       </tr>
                   </table>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doAddDep">确 定</el-button>
              </span>
        </el-dialog>
    </div>
</template>
<script>
    import ElTag from "../../../../node_modules/element-ui/packages/tag/src/tag";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    export default{
        components: {
            ElInput,
            ElTag},
        name: 'DepMana',
        data(){
            return {
                filterText:'',
                dialogVisible:false,
                dep:{
                    name:'',
                    parentId:-1
                },
                pname:'',
                deps:[],
                defaultProps:{
                    children:'children',
                    label:'name'
                }
            }
        },
        watch:{
            filterText(val){
                this.$refs.tree.filter(val);
            }
        },
        mounted(){
            this.initDeps();
        },
        methods:{
            initDep(){
              this.dep={
                  name:'',
                  parentId:-1
              }
              this.pname='';
            },
            addDep2Deps(deps,dep){
                for(let i=0; i<deps.length; i++){
                    let d=deps[i];
                    if(d.id==dep.parentId){
                        d.children=d.children.concat(dep);
                        return;
                    }else{
                        this.addDep2Deps(d.children,dep);
                    }
                }
            },
            doAddDep(){
                this.postRequest("/system/basic/department/",this.dep).then(resp=>{
                    if(resp){
                        this.addDep2Deps(this.deps,resp.obj);
                        //this.initDeps();
                        this.dialogVisible=false;
                        //初始化页面
                        this.initDep();
                    }
                })
            },
            showAddDepView(data){

                this.pname=data.name;
                this.dep.parentId=data.id;
                this.dialogVisible=true;
            },
            removeDepFromDeps(desp,id){
                for(let i=0;i<desp.length;i++) {
                    let d = desp[i];
                    if (d.id == id) {
                        desp.splice(i, 1);
                        return;
                    } else {
                        this.removeDepFromDeps(d.children, id);
                    }
                }
            },
            deleteDep(data){
                console.log(data);
                if(data.parent){
                    this.$message.error("父部门删除失败");
                }else{
                    this.$confirm('此操作将永久删除该['+data.name+']部门, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.deleteRequest("/system/basic/department/"+data.id).then(resp=>{
                            if(resp){
                                this.removeDepFromDeps(this.deps,data.id);
                            }
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
                }
            },
            initDeps(){
                this.getRequest("/system/basic/department/").then(resp=>{
                    if(resp){
                        this.deps=resp;
                    }
                })
            },
            filterNode(value,data){
                if(!value)return true;
                return data.name.indexOf(value)!=-1;

            }
        }
    }
</script>
<style >
    .dpbtn{
        pading: 2px;
    }
</style>