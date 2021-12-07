import Vue from 'vue'
import Vuex from 'vuex'
import persistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [persistedState()],
  state: {
    userId: 5,
    privilege: 3,
    userPaperMap4Time: new Map(),
    userPaperMap4Answer: new Map(),
  },
  getters: {
    getUserPaperMap4Time: state => { //  这里的state对应着上面这个state
        return state.userPaperMap4Time
    },
    getUserPaperMap4Answer: state => { //  这里的state对应着上面这个state
        return state.userPaperMap4Answer
    },
    getUserId: state => { //  这里的state对应着上面这个state
        return state.userId
    },
    getPrivilege: state => { //  这里的state对应着上面这个state
        if(state.privilege<4){return '学生'}
        else{return '老师'}
        // return state.privilege
    }
  },
  mutations: {
    changeUserPaperMap4Time (state, payload) {
        state.userPaperMap4Time = payload.userPaperMap4Time
    },
    changeUserPaperMap4Answer (state, payload) {
        state.userPaperMap4Answer = payload.userPaperMap4Answer
    },
    changeUserId (state, payload) {
        state.userId = payload.userId
    },
    changePrivilege (state, payload) {
        state.privilege = payload.privilege
    },
  },
  actions: {
  },
  modules: {
  }
})
