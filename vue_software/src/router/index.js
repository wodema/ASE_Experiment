import Vue from 'vue'
import VueRouter from 'vue-router'
import StudentAnalyse from '../components/StudentAnalyse.vue'
import TeacherAnalyse from '../components/TeacherAnalyse.vue'
// import Analyse from '../components/Analyse.vue'
import Question from "../components/Question";
import PaperList from "../components/PaperList";
import PaperDetail from "../components/PaperDetail";
import Home from "../views/Home";
import Login from "../views/Login";
import WrongQuestion from '../components/WrongQuestion.vue'
import StudentList from "../components/StudentList";
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
  ,
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '/question',
        name: 'Question',
        component: Question
      },
      // {
      //   path: '/analyse',
      //   name: 'Analyse',
      //   component: Analyse
      // },
      {
        path: '/paper-list',
        name: 'PaperList',
        component: PaperList
      },
      {
        path: '/student-list',
        name: 'StudentList',
        component: StudentList
      },
      {
        /**params多个参数这里页必须写上否则跳转后的页面读取不了，汉字放在url会被转化**/
        path: '/paper-detail:id:time:score',
        name: 'PaperDetail',
        component: PaperDetail
      }
      ,
      {
        path: '/studentAnalyse',
        name: 'StudentAnalyse',
        component: StudentAnalyse
      },
      {
        path: '/teacherAnalyse',
        name: 'TeacherAnalyse',
        component: TeacherAnalyse
      },
      {
        path: '/wrongQuestion',
        name: 'WrongQuestion',
        component: WrongQuestion
      },
    ]
  },
  {
    path: '*',
    redirect: '/Login',
    name: 'Login0',
    component: Login
  },
]

const router = new VueRouter({
  routes
})

export default router
