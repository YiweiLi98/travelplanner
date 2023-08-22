<template>
  <div class="travel_planner">
    <Nav></Nav>
      <div class="login-container">
        <h3 class="login-title" style="margin-top:100px; margin-bottom:0px;">User Login</h3>
        <el-form class="login-form" ref="loginForm" :model="loginForm" :rules="loginRules" label-width="0px" label-position="left">
          <el-form-item prop="username">
            <el-input
              type="text" 
              v-model="loginForm.username" 
              placeholder="username" 
              autocomplete="on"
              prefix-icon="el-icon-user"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input 
              type="password" 
              v-model="loginForm.password" 
              placeholder="password" 
              prefix-icon="el-icon-lock"
              show-password
              name="password"
              autocomplete="on"
              />
          </el-form-item>
          <el-form-item class="button1">
            <el-button type="primary" :loading="loading" @click="handleLogin">Login</el-button>
            <el-button type="primary"  @click="toRegister">Register</el-button> 
            <!-- <el-button type="primary"  @click="toHome">Back</el-button> -->
          </el-form-item>
        </el-form>
      </div>
    <Footer></Footer>
  </div>
</template>

<script>
// @ is an alias to /src
import Nav from "@/views/HomePage/Nav";
import Footer from "@/views/HomePage/Footer";
export default {
  name: 'LoginView',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('The username can not be empty'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('The password can not be empty'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: '',
      },
      loginRules: {
        username: [
          {required: true, message: 'Please enter your username', trigger: 'blur', validator: validateUsername },
        ],
        password: [
          {required: true, message: 'Please enter your password', trigger: 'blur', validator: validatePassword },
        ]
      },
      loading: false
    }
  },
  methods: {
    toRegister() {
      this.$router.push({path: '/register'} )
    },
    handleLogin() {
      console.log(this.$refs.loginForm) // debug

      this.$refs.loginForm.validate(valid => {
      if (valid) {
        console.log('DEBUGGING handleLogin: true valid')
        this.loading = true
        this.$store.dispatch('user/login', this.loginForm).then(res => {
          this.$router.push({ path: '/home'})
          this.loading = false
          }
        ).catch(() => {
          this.loading = false
        })
      } else {
        console.log('error submit!!')
        return false
      }
    })
  }
  },
  components: {
    Footer,
    Nav
  }
}
</script>

<style>
.travel_planner {
  background-color: #6c757d;
  height: 100%;
}
.login-container{
  background-color: antiquewhite;
  min-height: 100%;
  height: 100vh;
  width: 100%;
  overflow: hidden;
}
 .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 100px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

 .login-title {
  font-size: 26px;
  color: #2d3a4b;
  margin: 0 auto 40px auto;
  text-align: center;
  font-weight: 700;
}

.login-container .el-input input {
    background: transparent;
    border: 0;
    color: #2d3a4b;
    height: 47px;
    padding: 15px 5px 15px 15px;
    margin: 10px 20px;
}

/* google */
.el-input--prefix .el-input__inner::-webkit-input-placeholder {
  color:  #2d3a4b;
}
.el-form-item {
  border: 2px solid rgba(255, 255, 255, 0.1);
  /* background: #546376; */
  border-radius: 5px;
  color: #454545;
  border-color: grey;
}
.el-form-item.button1{
  background: transparent;
  border: none;
}

.el-button.el-button--primary {
    padding: 10px 20px;
    font-size: 14px;
    border-radius: 4px;
    width: 130px;
    background-color: #6c757d;
    border-color: #6c757d;
}

.el-icon-user:before, .el-icon-user:after {
    color: rgb(88, 87, 87);
}

.el-icon-lock:before, .el-icon-lock:after {
    color: rgb(88, 87, 87);
}

span {
  color: #fff;
}

</style>