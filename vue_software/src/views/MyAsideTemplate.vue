<template>
  <div class="navMenu" style="padding-top: 10px">
    <label v-for="navMenu0 in navMenus" :key="navMenu0.entity.id">
<!--      if 对应页面才可点进去 -->
<!--      <router-link :to="{ name: 'MyTabPane', params: {id:navMenu0.entity.id,alias: navMenu0.entity.name} }">-->
<!--      <router-link :to="{ path:'home/' + navMenu0.entity.id }">-->
      <router-link :to="{ path:navMenu0.entity.id }">
        <el-menu-item v-if="navMenu0.childs==null&&navMenu0.entity&&(navMenu0.entity.type===$store.getters.getPrivilege||navMenu0.entity.value===$store.getters.getPrivilege)"
                      :key="navMenu0.entity.id" :data="navMenu0" :index="navMenu0.entity.name" :route="navMenu0.entity.value">
          <i :class="navMenu0.entity.icon"></i>
          <span slot="title">{{navMenu0.entity.alias}}</span>
        </el-menu-item>
      </router-link>
      <el-submenu v-if="navMenu0.childs&&navMenu0.entity&&(navMenu0.entity.type===$store.getters.getPrivilege||navMenu0.entity.value===$store.getters.getPrivilege)"
                  :key="navMenu0.entity.id" :data="navMenu0" :index="navMenu0.entity.name">
        <NavMenu :navMenus="navMenu0.childs"></NavMenu>
        <!--        递归调用-->
      </el-submenu>
    </label>

  </div>
</template>

<script>
export default {
  name: 'NavMenu',
  props: ['navMenus'],
  data () {
    return {}
  },
  methods: {}
}
</script>

<style scoped>
a{
  text-decoration:none
}
</style>
