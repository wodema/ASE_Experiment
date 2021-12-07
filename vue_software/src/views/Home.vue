/* eslint-disable */
<template lang='html'>
  <div class="">
    <el-header class='my-header' style='border: 5px solid #eee'>
      <el-row type="flex">
        <el-col style="width:100%;">
      <el-button @click="logout" size="mini" style="width:98%">退出登录</el-button>
        </el-col>
        <el-col style="width:100%;">
      <el-button @click="reverse" size="mini"style="width:98%">手机侧边收缩</el-button>
        </el-col>
        <el-col style="width:100%;">
          userId:{{userId}}
        </el-col>
        <el-col style="width:100%;">
          权限:{{privilege}}
        </el-col>
      </el-row>
    </el-header>
    <el-container style='flex: auto; border: 5px solid #eee'>
      <el-aside width="auto">
        <el-menu
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
      userId: this.$store.getters.getUserId,
      privilege: this.$store.getters.getPrivilege,
      isCollapse: true,
      totalList: []
    }
  },
  methods: {

    reverse () {
      this.isCollapse = !this.isCollapse
    },
    handleSelect (key, keyPath) {
      // 被@注册了
      // console.log('handleSelect', key, keyPath)
    },
    logout () {
      this.$store.commit('changeUserId', {userId: -1})
      this.$store.commit('changePrivilege', {privilege: -1})
      this.$router.push('/login')
    },
  },
  components: {
    NavMenu
  },
  // watch: {
  //   $route (to, from) {
  //     console.log('rootContainer watch', to, from)
  //     this.activeAlias = to.params.alias
  //     console.log('this.activeAlias=', this.activeAlias)
  //   }
  // },
  created () {
    if(this.$store.getters.getUserId<0||this.$store.getters.getPrivilege<0){
      this.$router.push('/login')
    }
    this.totalList = myConfig.childs
  }
}
</script>

<style lang='css'>
.my-header {
  /*height: @rowheight * 10 !important;*/
  /*height: 40px !important;*/
  height: 100% !important;
}
</style>
/* eslint-disable */
