import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import * as echarts from 'echarts';
//添加全局样式
import './assets/css/global.css'

//导入axios
import axios from 'axios'

//挂载axios
Vue.prototype.$http = axios;
//挂在echarts,用于生成图表
Vue.prototype.$echarts = echarts
//设置访问根路径
axios.defaults.baseURL = "http://localhost:8085"

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
