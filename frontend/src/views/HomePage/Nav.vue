<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark">
      <a class="navbar-brand text-success" href="#/home">TravelPlanner <i class="el-icon-suitcase"></i></a>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

          <li class="nav-item">
            <a class="nav-link text-white text-uppercase ml-3" href="#" @click="toPage">Plan Travel</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white text-uppercase ml-3" href="#" @click="toProfile"> Profile</a>
          </li>
<!--          <li class="nav-item">-->
<!--            <a class="nav-link text-white text-uppercase ml-3" href="#">Favourite</a>-->
<!--          </li>-->
          <!-- <li class="nav-item">
            <a class="nav-link text-white text-uppercase ml-3" href="#"  @click="toHistory">Order History</a>
          </li> -->
<!--          <li class="nav-item">-->
<!--            <a class="nav-link text-white text-uppercase ml-3" href="#">Personal Choice</a>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <a class="nav-link text-white text-uppercase ml-3" href="#">-->
<!--              <router-link to="/home/plan">Plan</router-link>-->
<!--            </a>-->
<!--          </li>-->

        </ul>
        <div class="form-inline my-2 my-lg-0" v-if="!verifyLogin()">
          <button class="btn btn-outline-success my-2 my-sm-0 mx-1 " type="submit" @click="toLogin">Login</button>
          <button class="btn btn-outline-success my-2 my-sm-0 " type="submit" @click="toRegister">Sign Up</button>
        </div>
        <div class="form-inline my-2 my-lg-0" v-else>
          <img src="@/assets/default.png" style="width:30px; height:min-content;" class="my-2 my-sm-0 "
            @click="toProfile">
          <button class="btn btn-outline-success my-2 my-sm-0 ml-2 " type="submit" @click="onExit">Log out</button>

        </div>

    </div>
  </nav>
 <router-view></router-view>

</div>

</template>

<script>
import { getToken } from '@/utils/auth'

export default {
  name: "OrderView",

  methods: {

    toPage() {
      if(this.verifyLogin) {
        this.$router.push('/choose1')
      } else {
        this.$router.push('/login')
      }



    },
    verifyLogin() {
      const check = getToken()
      // console.log("If login or not, token:" + check)
      return check
    },
    toProfile(){
      this.$router.push('/profile')
    } ,
    async onExit() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/home`)
    },
    toLogin() {
      this.$router.push('/login')
    },
    toRegister() {
      this.$router.push('/register')
    },
    // toHistory(){
    //   this.$router.push('/history')
    // }

  },
  created() {
    // console.log(this.$route)
    // this.$router
  }
}
</script>
<style>

</style>
