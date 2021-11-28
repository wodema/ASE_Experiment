import Vue from 'vue'
import VueRouter from 'vue-router'
import WrongQuestion from '../components/WrongQuestion.vue'
import Login from '../components/Login.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Question',
    component: WrongQuestion
  },
  {
    path: '/question',
    name: 'Question',
    component: WrongQuestion
  }
]

const router = new VueRouter({
  routes
})

export default router
