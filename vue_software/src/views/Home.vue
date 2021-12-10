<template lang='html'>
  <div id="back-stage-controller" class="clearfix">
    <div id="back-stage-controller-header">
      <div id="back-stage-header-text">
        <div id="back-stage-header-title">
          在线练习系统
        </div>
        <div id="back-stage-header-title-eng">
          PRACTICE SYSTEM
        </div>
<!--&lt;!&ndash;              <el-container mode="horizontal">&ndash;&gt;-->
<!--                <el-menu-->
<!--                    @select='handleSelect'-->
<!--                    class='el-menu-vertical-demo'-->
<!--                    background-color='#F0F6F6'-->
<!--                    text-color='#3C3F41'-->
<!--                    active-text-color='#f60'-->
<!--                    :collapse="isCollapse"-->
<!--                    mode="horizontal"-->
<!--                >-->
<!--                  <el-menu-item @click="reverse">-->
<!--                    <i class="el-icon-rank"></i>-->
<!--                    <span slot="title">展开侧边栏</span>-->
<!--                  </el-menu-item>-->
<!--                  <NavMenu :navMenus='totalList' :collapse="isCollapse" mode="horizontal"></NavMenu>-->
<!--                  &lt;!&ndash;          navMenus='totalList绑定起来了&ndash;&gt;-->
<!--                </el-menu>-->
<!--&lt;!&ndash;              </el-container>&ndash;&gt;-->
      </div>

      <el-popover
          placement="top-start"
          width="200"
          trigger="hover"
      >
        <div id="logout-link">
          <div>userId:{{userId}}</div>
          <div>权限:{{privilege}}</div>
          <el-button @click="logout" size="mini" style="width:98%">退出登录</el-button>
<!--          <el-link @click="logout">注销登录</el-link>-->
        </div>
        <div id="back-stage-controller-header-avatar"  slot="reference"></div>
      </el-popover>
    </div>
    <el-container>
      <el-header >
<!--          不能删掉这个header否则css有bug-->
        <el-row type="flex">
        </el-row>
      </el-header>
      <el-container>
<!--        <el-aside width="auto" style="height:100%;background-color:#F0F6F6">-->
        <el-aside width="auto">
          <el-menu
              @select='handleSelect'
              class='el-menu-vertical-demo'
              background-color='#F0F6F6'
              text-color='#3C3F41'
              active-text-color='#f60'
              :collapse="isCollapse"
              style="height:100%;background-color:#F0F6F6"
          >
            <el-menu-item @click="reverse">
              <i class="el-icon-rank"></i>
              <span slot="title">展开侧边栏</span>
            </el-menu-item>
            <NavMenu :navMenus='totalList' :collapse="isCollapse"></NavMenu>
            <!--          navMenus='totalList绑定起来了-->
          </el-menu>
        </el-aside>
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

<style scoped lang="less">
//.my-header {
//  /*height: @rowheight * 10 !important;*/
//  /*height: 40px !important;*/
//  height: 100% !important;
//}
.el-menu.el-menu--horizontal{
  border-bottom: 0;

}
#back-stage-controller{
  min-height: 100%;

  background-color: rgba(9, 132, 217, .15);

  #back-stage-controller-header{
    height: 70px;
    background-color: rgb(9, 132, 217);
    position: fixed;
    z-index: 2000;
    width: 100%;
    #back-stage-header-text{
      text-align: center;
      width: 400px;
      padding-top: 10px;
      float: left;
      #back-stage-header-title{
        color: #eee;
        font-weight: bold;
        font-size: 24px;
        margin-bottom: 10px;
      }
      #back-stage-header-title-eng{
        color: #eee;
        font-weight: bold;
      }
    }
    #back-stage-controller-header-avatar{
      background: url("../assets/img/avatar.png") center;
      -webkit-background-size: cover;
      background-size: cover;
      cursor: pointer;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      position: absolute;
      right: 30px;
      top: 10px;
    }
  }
}

#back-stage-controller-main{
  width: 83%;
  padding: 20px;
  /*position: fixed;*/
  top: 80px;
  margin-top: 70px;
  float: right;
  right: 0;
}
#back-stage-controller-menu{
  width: 17%;
  min-height: 100%;
  float: left;
  background-color: #fff;
  border-right: 1px solid #c3e7ff;
  position: fixed;
  top: 70px;
}
</style>
