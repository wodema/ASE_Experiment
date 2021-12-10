<template>
  <div id="login-wrap" class="clearfix">
    <div id="login-bg">
      <h3 align="center">测试账号密码</h3>
<!--      <table width="50%" align="center" style="border: 2px " bgcolor="#ffd700">-->
      <table width="50%" align="center" style="border: 2px;background-color:rgba(255,255,255,.3);" >
<!--        &lt;!&ndash;      测试账号密码&ndash;&gt;<tr><td>测试账号密码</td></tr>-->
        <tr><td>账号</td><td> 密码</td> <td> 权限</td></tr>
        <tr><td>3</td><td> 123456</td> <td> 学生</td></tr>
        <tr><td>5</td><td> 123456</td> <td> 学生</td></tr>
        <tr><td>6</td><td> 123456</td> <td> 老师</td></tr>
        <tr><td>7</td><td> 123456</td> <td> 学生</td></tr>
      </table>
<!--      <div class="el-container">-->

      <div style="float: right;width: 30%; flex: auto;" >
        <el-form :model="loginModel" :rules="loginRules"
                 status-icon
                 ref="loginRules"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm login-page">
<!--                 style="float: right;width: 30%; flex: auto">-->

<!--          <h3 class="title">在线练习系统</h3>-->
<!--          <h3 class="login-form-title">在线练习系统</h3>-->
<!--          <div id="login-form.login-form-title">在线练习系统</div>-->
          <div id="login-form-title">在线练习系统</div>
          <el-form-item prop="userId" id="login-form-name">
            <el-input type="text"
                      v-model="loginModel.userId"
                      auto-complete="off"
                      placeholder="用户id"

            ></el-input>
          </el-form-item>
          <el-form-item prop="password" id="login-form-pass">
            <el-input type="password"
                      v-model="loginModel.password"
                      auto-complete="off"
                      placeholder="密码"

                      show-password
            ></el-input>

          </el-form-item>
          <el-checkbox style="padding: 5px"
              v-model="checked"
          >记住密码</el-checkbox>
          <el-form-item style="width:100%;">
            <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="loadingTrigger">登录</el-button>
          </el-form-item>
        </el-form>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  data () {
    return {
      loadingTrigger: false,
      loginModel: {
        userId: '6',
        password: '123456'
      },
      loginRules: {
        userId: [
          {required: true, message: 'please enter your account', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          // {min: 5, max: 10, type: 'number', message: '长度在 6 到 10 个字符', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'},
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
            v.$router.push({path: '/home'})
            v.$router.push({path: '/paper-list'})
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

<style scoped lang="less">
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  //width: 350px;
  padding: 35px 35px 15px;
  //background: #fff;
  background: rgba(255,255,255,0.8);
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
/**上述的会被覆盖**/
#login-wrap{
  background: url("../assets/img/loginbg.jpg") no-repeat;
  background-size: cover;
  height: 100%;

  #login-bg{
    height: 100%;
    background-color: rgba(82,101,249,0.3);
    overflow: hidden;
    #login-bg-block-top{
      width: 2000px;
      height: 600px;
      background-color: rgba(204, 229, 249, .9);
      transform: rotate(15deg) translate(-300px, -375px);
      transition: 5s;
      &.block-active{
        transform: rotate(15deg) translate(0, -425px);
      }
    }
    #login-bg-block-bottom{
      width: 2000px;
      height: 600px;
      background-color: rgba(204, 229, 249, .9);
      transform: rotate(15deg) translate(-600px, 225px);
      transition: 5s;
      &.block-active{
        transform: rotate(15deg) translate(-600px, 175px);
      }
    }
  }
}
#login-form-title{
  font-size: 24px;
  text-align: center;
  color: dodgerblue;
  margin: 25px;
  font-weight: bold;
}
#login-form-name, #login-form-pass, #login-form-submit{
  //margin: 15px;
  background-color:rgba(255,255,255,.2);
  color: rgba(255,255,255,.1);
}
</style>
