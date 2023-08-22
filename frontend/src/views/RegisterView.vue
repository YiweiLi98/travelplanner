<!--template>
  <div>
    REGISTER!


    :model="registerForm"

    v-model="registerForm.username"

    v-model="loginForm.password" 
  </div-->
<template>
  <div class="travel_planner">
    <div class="register-container">
      <Nav></Nav>
      <el-form class="register-form" ref="registerForm" :rules="rules" :model="registerForm" label-width="0px"
        label-position="left">
        <h3 class="register-title">User Register</h3>
        <el-form-item prop="username">
          <el-input type="text" v-model="registerForm.username" placeholder="username" tabindex="1" autocomplete="on"
            prefix-icon="el-icon-user" />
        </el-form-item>
        <el-form-item prop="firstname">
          <el-input type="text" v-model="registerForm.firstname" placeholder="Firstname" tabindex="1" autocomplete="on"
            prefix-icon="el-icon-user" />
        </el-form-item>
        <el-form-item prop="lastname">
          <el-input type="text" v-model="registerForm.lastname" placeholder="Lastname" tabindex="1" autocomplete="on"
            prefix-icon="el-icon-user" />
        </el-form-item>
        <el-form-item prop="email">
          <el-input type="email" v-model="registerForm.email" placeholder="Email" tabindex="1" autocomplete="on"
            prefix-icon="el-icon-message" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="registerForm.password" placeholder="password" prefix-icon="el-icon-lock"
            show-password name="password" tabindex="2" autocomplete="on" />
        </el-form-item>
        <el-form-item class="button1">
          <!--el-button type="primary" :loading="loading" @click="handleLogin">Login</el-button>
          <el-button type="primary"  @click="toRegister">Register</el-button-->

          <el-button type="primary" :loading="loading" @click="save">Confirm</el-button>
          <el-button type="primary" @click="toLogin">Login</el-button>
        </el-form-item>
      </el-form>
    </div>
    <Footer></Footer>
  </div>
</template>
 

<!--script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'RegisterView',
  components: {
  }
}
</script-->

<script>
import Nav from "@/views/HomePage/Nav";
import Footer from "@/views/HomePage/Footer";

import request from "@/utils/request";
// @ is an alias to /src

export default {
  name: 'LoginView',
  data() {
    return {
      registerForm: {
        username: '',
        password: '',
        firstname: '',
        lastname: '',
        email: '',
        age: 18,
        avatar: "Elec5619/frontend/src/assets/default.png",
      },

      rules: {
        username: [
          { required: true, message: 'Please enter your username' }
        ],
        firstname: [
          { required: true, message: 'Please enter your firstname' }
        ],
        lastname: [
          { required: true, message: 'Please enter your lastname' }
        ],
        email: [
          { required: true, message: 'Please enter the correct email format', pattern: /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/ }
        ],
        password: [
          { required: true, message: 'Please enter your password' }
        ]
      },
      loading: false
    }
  },
  methods: {
    toLogin() {
      this.$router.push({ path: '/login' })
    },

    checkEmail() {
      const email = this.registerForm.email
      // console.log(email)
      return /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/.test(email) ? null : false
    },

    checkSubmit() {
      console.log(Object.values(this.registerForm))
      const userData = Object.values(this.registerForm)
      const noEmpty = userData.every(value => {    // return true only when all value is not empty
        if (value != '') {
          return true;
        }
        return false;
      })
      return noEmpty
      // console.log(noEmpty)
    },

    save() {
      console.log("form submitted")
      // console.log(this.form.username)
      if (this.checkSubmit() && this.checkEmail() != false) {
        request.post("/user/register", this.registerForm).then((res) => {
          if (res) {
            this.$message.success("Registration success")
            this.$router.push({ path: '/login' })
            this.loading = false
          }
        }).catch(() => {
          this.$message.error("Registration error")
          this.loading = false
        })
        this.loading = false
      } else {
        this.$message.error("Empty input or invalid E-mail!")
      }
    }
  },
  components: {
    Nav,
    Footer
  }
}
</script>


<style>
.travel_planner {
  background-color: #6c757d;
  height: 100%;
}

body {
  margin: 0px;
  height: 100%;
  position: relative;
}

.register-container {
  /*
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6ca; 
  */
  background-color: antiquewhite;
  min-height: 100%;
  height: 100vh;
  width: 100%;
  overflow: hidden;
}

.register-form {
  position: relative;
  width: 520px;
  max-width: 100%;
  padding: 50px 35px 0;
  margin: 0 auto;
  overflow: hidden;
}

.register-title {
  font-size: 26px;
  color: black;
  margin: 0 auto 40px auto;
  text-align: center;
  font-weight: 700;
}

.register-container .el-input input {
  background: transparent;
  border: 0;
  -webkit-appearance: none;
  border-radius: 0;
  padding: 15px 5px 15px 15px;
  /* color: #fff; */
  height: 47px;
  caret-color: #fff;
  margin: 10px 20px;
}

.el-input:-webkit-autofill {
  box-shadow: 0 0 0px 1000px #2d3a4b;
  -webkit-text-fill-color: #fff;
}

.el-form-item {
  border: 1px solid rgba(255, 255, 255, 0.1);
  background: rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  color: #454545;
}

.el-form-item.button1 {
  background: transparent;
  border: none;
}

.el-button--primary {
  padding: 10px 20px;
  font-size: 14px;
  border-radius: 4px;
  width: 130px;
}

.el-icon-message {
  color: rgb(88, 87, 87);
}

/* span {
  margin-right: 16px;
} */

/* .svg-container {
    padding: 6px 5px 6px 15px;
    color: #2d3a4b;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
} */
</style>