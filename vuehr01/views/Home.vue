<!--suppress ALL -->
<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">微人事</div>
                <el-dropdown class="userInfo" @command="commandHandler">
                      <span class="el-dropdown-link">
                        {{user.name}}<i><img :src="user.userface" alt=""></i>
                      </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                        <el-dropdown-item command="setting">设置</el-dropdown-item>
                        <el-dropdown-item command="logout" divided >注销登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-container>
                <el-aside width="200px">

                    <el-menu  router>
                        <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
                            <template slot="title">
                                <i style="color: #74ff65;margin-right: 5px" :class="item.iconCls"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">{{child.name}}</el-menu-item>
                        </el-submenu>
                    </el-menu>


                </el-aside>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
                        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">欢迎来到VHR</div>
                    <router-view class="homeRouterView"/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
    export default{
        name: 'Home',
        data(){
            return{
                user:JSON.parse(window.sessionStorage.getItem("user"))
                //user:JSON.parse(JSON.stringify(window.sessionStorage.getItem("user")))
            }
        },
        computed:{
            routes(){
                return this.$store.state.routes;
            }
        },
        methods:{
            commandHandler(cmd){
                if(cmd=='logout'){

                        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                                this.getRequest("/logout");
                                window.sessionStorage.removeItem("user");
                                this.$router.replace("/");
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消删除'
                            });
                        });

                }
            }
        }
    }
</script>
<style >
    .homeRouterView{
        margin-top: 15px;
    }
    .homeWelcome{
        text-align: center;
        font-size:30px;
        font-family: 华文行楷;
        color: #63ffd3;
        padding-top:50px;
    }
    .homeHeader{
        background: #30ff9b;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding:0px 15px;
        box-sizing: border-box;
    }
    .homeHeader .title{
        font-size:30px;
        font-family: 华文行楷;
        color: #fffefa;
    }
    .homeHeader .userInfo{
        cursor: pointer;
    }
    .el-dropdown-link img{
        width:48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }
    .el-dropdown-link{
        display:flex;
        align-items: center;
    }
</style>