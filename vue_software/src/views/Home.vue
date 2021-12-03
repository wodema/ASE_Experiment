/* eslint-disable */
<template lang='html'>
  <div class="">
    <el-header style='flex: auto; border: 5px solid #eee'>
      <el-button @click="logout" size="mini">退出登录</el-button>
      <el-button @click="reverse" size="mini">手机侧边收缩</el-button>
    </el-header>
    <el-container style='flex: auto; border: 5px solid #eee'>
      <el-aside width="auto">
        <!--                @mouseenter.native="collapseOpen"-->
        <!--                @mouseleave.native="collapseClose">-->
        <!--        >-->
        <!--                @mouseenter.native="reverse"-->
        <!--                @mouseleave.native="reverse">-->
        <!--      >-->
        <el-menu
            :default-active='activeAlias'
            @select='handleSelect'
            class='el-menu-vertical-demo'
            background-color='#F0F6F6'
            text-color='#3C3F41'
            active-text-color='#f60'
            :collapse="isCollapse"
        >
          <NavMenu :navMenus='totalList' :collapse="isCollapse"></NavMenu>
          <!--          navMenus='totalList绑定起来了-->
        </el-menu>
      </el-aside>
      <el-container>
<!--        <div class="home">-->
<!--          <img alt="Vue logo" src="../assets/logo.png">-->
<!--        </div>-->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<script>
import myConfig from './myconfig'
import NavMenu from './MyAsideTemplate'

export default {
  data () {
    return {
      isCollapse: true,
      totalList: []
    }
  },
  methods: {
    collapseOpen () {
      this.isCollapse = false
    },
    collapseClose () {
      this.isCollapse = true
    },
    reverse () {
      this.isCollapse = !this.isCollapse
    },
    handleSelect (key, keyPath) {
      // 被@注册了
      console.log('handleSelect', key, keyPath)
    },
    logout () {
      // localStorage.removeItem('Authorization')
      // this.$store.commit('changeToken', {Authorization: ''})
      this.$router.push('/login')
    },
    getStatus () {
      this.$store.dispatch('getStatus')
    }
  },
  components: {
    NavMenu
  },
  watch: {
    $route (to, from) {
      console.log('rootContainer watch', to, from)
      this.activeAlias = to.params.alias
      console.log('this.activeAlias=', this.activeAlias)
    }
  },
  created () {
    // this.totalList = config.childs
    this.totalList = myConfig.childs
  }
}
</script>

<style lang='css'>
</style>
/* eslint-disable */
