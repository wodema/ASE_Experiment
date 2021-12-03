import Vue from 'vue'
import VueRouter from 'vue-router'
import WrongQuestion from '../components/WrongQuestion.vue'
import Analyse from '../components/Analyse.vue'
import Question from "../components/Question";
import PaperList from "../components/PaperList";
import PaperDetail from "../views/PaperDetail";
import Home from "../views/Home";
Vue.use(VueRouter)

const routes = [
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
        path: '/paper-list',
        name: 'PaperList',
        component: PaperList
      }
      ,
      {
        path: '/paper-detail:id',
        name: 'PaperDetail',
        component: PaperDetail
      }
      ,
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
