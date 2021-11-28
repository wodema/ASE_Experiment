import Vue from 'vue'
import VueRouter from 'vue-router'
import WrongQuestion from '../components/WrongQuestion.vue'
import Analyse from '../components/Analyse.vue'
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
  },
  {
    path: '/analyse',
    name: 'Analyse',
    component: Analyse
  }
]

const router = new VueRouter({
  routes
})

export default router
