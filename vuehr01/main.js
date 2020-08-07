import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'

import {postKeyvalueRequest} from "./utils/api"
import {postRequest} from "./utils/api"
import {putRequest} from "./utils/api"
import {getRequest} from "./utils/api"
import {deleteRequest} from "./utils/api"
import {initMenu} from "./utils/menus"
import 'font-awesome/css/font-awesome.min.css'
Vue.prototype.postKeyvalueRequest =postKeyvalueRequest;
Vue.prototype.postRequest =postRequest;
Vue.prototype.putRequest =putRequest;
Vue.prototype.getRequest =getRequest;
Vue.prototype.deleteRequest =deleteRequest;

Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"});

router.beforeEach((to,from,next)=>{
     //console.log(to)
    // console.log(from)
  if(to.path=='/'){
    next();
  }else{
    if(window.sessionStorage.getItem("user")){
        initMenu(router,store);
        next();
    }else{
        next('/?redirect='+to.path);
    }

  }
})
new Vue({
  router,
    store,
  render: h => h(App)
}).$mount('#app')
