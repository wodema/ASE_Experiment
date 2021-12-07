<template>
  <div id="login-wrap" class="clearfix">
    <h5 width="50%" align="center">部署上线后f5刷新才能自动填上密码,不知道为什么.</h5>
    <h5 width="50%" align="center">提示登录失败多点几次登录就行,暂且蒙鼓</h5>
    <table width="50%" align="center" style="border: 2px " bgcolor="#ffd700">
<!--      测试账号密码-->
      <tr><td>账号</td><td> 密码</td> <td> 权限</td></tr>
      <tr><td>3</td><td> 123456</td> <td> 学生</td></tr>
      <tr><td>5</td><td> 123456</td> <td> 学生</td></tr>
      <tr><td>6</td><td> 123456</td> <td> 老师</td></tr>
      <tr><td>7</td><td> 123456</td> <td> 学生</td></tr>
<!--    3	123456	1学生-->
<!--    5	123456	1学生-->
<!--    6	123456	8老师-->
<!--    7	123456	3学生-->
    </table>
    <div class="login-container">
      <el-form :model="loginModel" :rules="loginRules"
               status-icon
               ref="loginRules"
               label-position="left"
               label-width="0px"
               class="demo-ruleForm login-page">
        <h3 class="title">在线练习系统</h3>
        <el-form-item prop="userId">
          <el-input type="text"
                    v-model="loginModel.userId"
                    auto-complete="off"
                    placeholder="用户id"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password"
                    v-model="loginModel.password"
                    auto-complete="off"
                    placeholder="密码"
                    show-password
          ></el-input>

        </el-form-item>
        <el-checkbox
          v-model="checked"
        >记住密码</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="loadingTrigger">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// import axios from '@/request/request'
// import {mapMutations} from 'vuex'

export default {
  data () {
    return {
      loadingTrigger: false,
      loginModel: {
        userId: '3',
        password: '123456'
      },
      loginRules: {
        userId: [
          {required: true, message: 'please enter your account', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          // {min: 5, max: 10, type: 'number', message: '长度在 6 到 10 个字符', trigger: 'blur'},
          {min: 5, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur'},
          {required: true, message: 'enter your password', trigger: 'blur'}
        ]
      },
      checked: true
    }
  },
  methods: {
    handleSubmit (event) {
      this.$refs.loginRules.validate((valid) => {
        if (valid) {
          let v = this
          this.$http.post('/students/login', {
            'sid': v.loginModel.userId,
            'password': v.loginModel.password
          }).then(function (res) {
            console.log(res)
            v.$store.commit('changeUserId', {userId: res.data['student']['id']})
            v.$store.commit('changePrivilege', {privilege: res.data['student']['privilege']})
            // console.log(v.$store.getters.getPrivilege)
            // console.log(v.$store.getters.getUserId)
            v.$router.push({path: '/home'})
          })
            .catch(function (err) {
              console.log('err', err)
              v.$message('密码或用户名错误')
            })
        } else {
          //  console.log('error submit!')
          return false
        }
      })
    }
  },
  created(){
    this.$store.commit('changeUserId', {userId: -1})
    this.$store.commit('changePrivilege', {privilege: -1})
  }
}
</script>

<style scoped>
#login-wrap{
    /*background: url("src/assets/logo.png") no-repeat;*/
    background-size: cover;
    height: 100%;
}
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>
