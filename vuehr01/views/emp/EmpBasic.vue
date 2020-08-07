<template>
    <div>
        <div>
            <div style="display:flex; justify-content: space-between" >
                <el-input placeholder="请输入员工姓名进行搜索，可直接按回车..." prefix-icon="el-icon-search" style="width: 300px; margin-right: 10px;">
                    <el-button icon="el-icon-search" type="primary">搜索</el-button>
                    <el-button icon="el-icon-search">
                        <i class="fa fa-angle-double-down" aria-hidden="true"></i>高级搜索</el-button>
                </el-input>
            </div>
            <div>
                <el-button type="success">
                    <i class="fa fa-level-up" aria-hidden="true"></i>
                    导入数据
                </el-button>
                <el-button type="success">
                    <i class="fa fa-level-down" aria-hidden="true"></i>
                    导出数据
                </el-button>
                <el-button type="primary" icon="el-icon-plus">
                    添加用户
                </el-button>
            </div>
        </div>

        <div style="margin-top: 15px;">
            <el-table
                    :data="emps"
                    stripe
                    border
                    v-loading="loading"
                    element-loading-text="正在加载数据"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    style="width: 100%">

                <el-table-column
                        type="selection"
                        width="55"
                ></el-table-column>

                <el-table-column
                        fixed
                        prop="name"
                        align="left"
                        label="姓名"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="wordID"
                        label="工号"
                        width="85">
                </el-table-column>
                <el-table-column
                        prop=""
                        label="性别"
                        align="left"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="birthday"
                        label="出生日期"
                        align="left"
                        width="95">
                </el-table-column>
                <el-table-column
                        prop="idCard"
                        label="身份证号码"
                        align="left"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="wedlock"
                        label="婚姻状况"
                        width="70">
                </el-table-column>
                <el-table-column
                        prop="nation.name"
                        label="民族"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="nativePlace"
                        label="籍贯"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="politicsstatus.name"
                        label="政治面貌">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="电子邮件"
                        align="left"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="电话号码"
                        align="left"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="联系地址"
                        align="left"
                        width="220">
                </el-table-column>
                <el-table-column
                        prop="department.name"
                        label="所属部门"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="position.name"
                        label="职位"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="jobLevel.name"
                        label="职称"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="engageForm"
                        label="聘用形式"
                        align="left"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="beginDate"
                        label="入职日期"
                        align="left"
                        width="95">
                </el-table-column>
                <el-table-column
                        prop="conversionTime"
                        label="转正日期"
                        align="left"
                        width="95">
                </el-table-column>
                <el-table-column
                        prop="beginContract"
                        label="合同起始日期"
                        align="left"
                        width="95">
                </el-table-column>
                <el-table-column
                        prop="endContract"
                        label="合同截止日期"
                        align="left"
                        width="95">
                </el-table-column>


                <el-table-column
                        prop="contractTerm"
                        label="合同期限"
                        align="left"
                        width="100">
                    <template slot="scope">
                        <el-tag>{{scope.row.contractTerm}}</el-tag>年
                    </template>
                </el-table-column>
                <el-table-column
                        prop="tiptopDegree"
                        label="最高学历"
                        align="left"
                        width="95">
                </el-table-column>


                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button @click="" padding="3px" size="mini">编辑</el-button>
                        <el-button @click="" padding="3px" type="primany" size="mini">查看高级资料</el-button>
                        <el-button @click="" padding="3px" type="danger" size="mini">删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
            <div style="display: flex;justify-content: flex-end">
                <el-pagination
                        background
                        @current-change="currentChange"
                        @size-change="sizeChange"
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="1000">
                </el-pagination>
            </div>

        </div>
    </div>
</template>
<script>
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import ElTableColumn from "element-ui/packages/table/src/table-column";
    export default{
        components: {
            ElTableColumn,
            ElButton},
        name: 'EmpBasic',
        loading:false,
        data(){
            return{
                emps:[],
                loading:false,
                total:0,
                page:1,
                size:10

            }
        },
        methods:{
            sizeChange(currentSize){
                this.size=currentSize;
                this.initEmps();
            },
            currentChange(currentPage){
                    this.page=currentPage;
                    this.initEmps();
            },
            initEmps(){
                this.loading=true;
                this.getRequest("/emp/basic/?page="+this.page+'&size='+this.size).then(resp=>{
                    this.loading=false;
                    if(resp){
                        this.emps=resp.data;
                        this.total=resp.total;
                    }
                })
            }
        }
    }
</script>
<style scoped>
</style>