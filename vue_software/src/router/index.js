import Vue from 'vue'
import VueRouter from 'vue-router'
import WrongQuestion from '../components/WrongQuestion.vue'
import StudentAnalyse from '../components/StudentAnalyse.vue'
import TeacherAnalyse from '../components/TeacherAnalyse.vue'
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
    path: '/studentAnalyse',
    name: 'Studentanalyse',
    component: StudentAnalyse
  },
  {
    path: '/tearcherAnalyse',
    name: 'TeacherAnalyse',
    component: TeacherAnalyse
  }
]

const router = new VueRouter({
  routes
})

export default router
