import Vue from 'vue'
import VueRouter from 'vue-router'
import WrongQuestion from '../components/WrongQuestion.vue'
import Analyse from '../components/Analyse.vue'
import Question from "@/components/Question";
Vue.use(VueRouter)

const routes = [
  {
    path: '/question',
    name: 'Question',
    component: Question
  },
  {
    path: '/wrongQuestion',
    name: 'WrongQuestion',
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
