import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
/**
 * 重写路由的push方法--->这个是vue-cli4.x以上的坑，不然的话，你是跳转不了的
 */
const routerPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return routerPush.call(this, location).catch(error => error)
}
Vue.use(VueRouter)

  const routes = [
      {
        path: '/',
          name: 'Login',
          component: Login,
          hidden:true
      },
      {
        path: '/home',
          name: 'Home',
          component: Home,
          hidden:true
      }

]

const router = new VueRouter({
  routes
})

export default router
