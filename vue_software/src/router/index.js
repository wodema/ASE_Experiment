import Vue from 'vue'
import VueRouter from 'vue-router'
import WrongQuestion from '../components/WrongQuestion.vue'
import Analyse from '../components/Analyse.vue'
import Question from "@/components/Question";
import ExamList from "../views/ExamList";
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
  },
  {
    path: '/exam-list',
    name: 'ExamList',
    component: ExamList
  }
  // ,
  // {
  //   path: '/exam:id',
  //   name: 'exam',
  //   component: Exam
  // }
]

const router = new VueRouter({
  routes
})

export default router
