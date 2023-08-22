<!--template>
  <div>
    REGISTER!
    :model="registerForm"
    v-model="registerForm.username"
    v-model="loginForm.password"
  </div-->
<template>
    <div>
  <div class="combo-container">
    <Nav></Nav>

    <el-form class="combo-form" ref="comboForm"  label-width="0px" label-position="left">

      <b-row>
        <b-col>
          <img :src="travel1" alt="travel" style="height: 160px;width: 160px" >
        </b-col>
        <b-col>
          <img :src="travel2" alt="travel" style="height: 200px;width: 200px" >
        </b-col>
        <b-col>
          <img :src="travel3" alt="travel" style="height: 200px;width: 200px" >
        </b-col>
        <!--div class="w-100"></div>
        <b-col>Column</b-col>
        <b-col>Column</b-col-->
      </b-row>

      <h3 class="combo-title">User Plan Choice</h3>
      <h3 class="combo-choice1">Plan A:Book your transport,accomadtion,things to do with us!</h3>
      <h3 class="combo-choice1">Plan B:Book your transport,things to do with us!</h3>
      <h3 class="combo-choice1">Plan C:Book your things to do with us!</h3>

      <el-form-item class="button1">
        <!--el-button type="primary" :loading="loading" @click="handleLogin">Login</el-button>
        <el-button type="primary"  @click="toRegister">Register</el-button-->


        <el-button type="primary" :loading="loading" @focus="planA" :disabled="disabled_A" @click="clickA">Plan A</el-button>
        <el-button type="primary" :loading="loading" @focus="planB" :disabled="disabled_B" @click="clickB">Plan B</el-button>
        <el-button type="primary" :loading="loading" @focus="planC" :disabled="disabled_C" @click="clickC">Plan C</el-button>
        <!--el-button type="primary"  @click="toLogin">Login</el-button-->
      </el-form-item>
    </el-form>
    <b-button variant="success " href="#" size="lg" class="mt-3 col-2 " @click="onSubmit">Start Planning</b-button>
  </div>
      <div class="mt-3">
       <Footer></Footer>
      </div>
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
// @ is an alias to /src

import Footer from "@/views/HomePage/Footer";
import Nav from "@/views/HomePage/Nav";

export default {
  name: 'LoginView',
  data() {
    return {
      travel1:require('@/assets/travel-icon-224.png'),
      travel2:require('@/assets/travel-icon-213.png'),
      travel3:require('@/assets/5227818.png'),
      loginForm: {
        username: '',
        password: '',
      },
      loading: false,
       disabled_A:false,
      disabled_B:false,
      disabled_C:false,
      mode:1
    }
  },
  methods: {
    toLogin() {
      this.$router.push({path: '/'} )
    },
    toPayment() {
      this.$router.push({path: '/payment'} )
    },
    
    onSubmit(){
      this.$store.commit('user/SET_MODE',this.mode)
      this.$router.push({ path: '/home/plan'})

    },
    planA(){
      this.disabled_B=true;
      this.disabled_C=true;
      this.disabled_A=false;
    },
    planB(){
      this.disabled_B=false
      this.disabled_C=true;
      this.disabled_A=true;
    },
    planC(){
      this.disabled_B=false;
      this.disabled_C=false;
      this.disabled_A=true;
    },
    clickA(){
      this.mode="3"

    },
    clickB(){
      this.mode="2"

    },
    clickC(){
      this.mode="1"
    }
  },
  components: {
    Nav,
    Footer
  }
}
</script>


<style>
.travel{
  width: 5px;
  height: 5px;
}
body{
  margin: 0px;
  height:100%;
  position: relative;
}
.combo-container{
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
.combo-form {

  position: relative;
  width: 750px;
  max-width: 100%;
  height: 84%;
  padding: 160px 35px 0;
  margin: 0 auto;
  overflow: hidden;
}
.combo-title {
  font-size: 26px;
  color: black;
  margin: 0 auto 40px auto;
  text-align: center;
  font-weight: 700;
}
.combo-choice1 {
  font-size: 20px;
  color: black;
  margin: 0 auto 20px auto;
  text-align: left;
  font-weight: 700;
}
.combo-container .el-input input {
  background: transparent;
  border: 0;
  -webkit-appearance: none;
  border-radius: 0;
  padding: 15px 5px 15px 15px;
  color: #fff;
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
.el-form-item.button1{
  background: transparent;
  border: none;
}
.el-button--primary {
  padding: 10px 20px;
  font-size: 14px;
  border-radius: 4px;
  width: 130px;
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

