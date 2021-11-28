<template>
    <div class="login_container">
        <div class="login_box">
            <!-- 登录块 -->
            <div class="avatar_box">
                <img src="../assets/logo.png" alt/>
            </div>
            <!-- 表单区域 -->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
                <!-- 用户名 -->
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="el-icon-user-solid"></el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" placeholder="请输入密码" prefix-icon="el-icon-lock" type="password"
                    show-password
                    ></el-input>
                </el-form-item>
                <!-- 按钮 -->
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="info" @click="resetLoginForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return{
            //表单对象
            loginForm:{
                username:"",
                password:""
            },
            //验证对象
            loginRules:{
                username:[
                    { required: true, message: '请输入用户名', trigger: 'change' },
                    { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'change' }
                ],
                password:[
                    { required: true, message: '请输入密码', trigger: 'change' },
                    { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'change' }
                ],
            },
        };
    },
    methods: {
        resetLoginForm(){
            this.$refs.loginFormRef.resetFields();
        },
        login(){
            //验证校验规则(valid相当于一个变量，可改名)
            this.$refs.loginFormRef.validate(async valid =>{
                if(!valid) return;
                const {data: res} = await this.$http.post("login", this.loginForm);//请求响应
                if(res.flag == "ok"){
                    window.sessionStorage.setItem("user", JSON.stringify(res.user));
                    this.$message.success("操作成功！");
                    this.$router.push({path:"/home"});//跳转
                    console.log(res.user);
                }else{
                    this.$message.error("操作失败！");
                }

            })
        },
    },

}
</script>

<style lang="less" scoped>
.login_container{
    background-color: #2b4b6b;
    height: 100%;
}
.login_box{
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 20px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    .avatar_box{
        width: 130px;
        height: 130px;
        border: 1px solid #eee;
        border-radius: 50%;
        padding: 5px;
        box-shadow: 0 0 10px #ddd;
        position: absolute;
        left: 50%;
        transform: translate(-50%,-50%);
        background-color: gray;
        img{
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #eee;
        }
    }
}
.btns{
    display: flex;
    justify-content: flex-end;
}
.login_form{
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 50px 25px 20px;
    box-sizing: border-box;
}
</style>
