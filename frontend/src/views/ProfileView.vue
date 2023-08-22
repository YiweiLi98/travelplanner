<template>
  <div style="background-color:antiquewhite">
    <Nav></Nav>
    <b-container>
      <b-row align-h="center" class="mt-5">
        <b-col md="2">
          <img src="@/assets/default.png" style="width:160px; height:min-content;" class="my-2 my-sm-0 ">
        </b-col>
      </b-row>

      <b-row align-h="center" class="mt-5">
        <b-col md="6">
          <p>Personal INFO</p>
          <b-form @submit.stop.prevent="onSubmit">
            <b-row class="mb-1">
              <b-col md="3">
                <p class="my-3">User Name: </p>
              </b-col>
              <b-col md="9">
                <b-form-input v-model="form.username" type="text" size="lg" :disabled="disabled"
                  :state="this.form.username ? null : false"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col md="3">
                <p class="my-2">First Name: </p>
              </b-col>
              <b-col md="9">
                <b-form-input v-model="form.firstname" type="text" size="lg" :disabled="disabled"
                  :state="this.form.firstname ? null : false"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-3">
              <b-col md="3">
                <p class="my-2">Last Name: </p>
              </b-col>
              <b-col md="9">
                <b-form-input v-model="form.lastname" type="text" size="lg" :disabled="disabled"
                  :state="this.form.lastname ? null : false"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-3">
              <b-col md="3">
                <p class="my-2">Email: </p>
              </b-col>
              <b-col md="9">
                <b-form-input v-model="form.email" type="email" size="lg" :disabled="disabled" :state="checkEmail()">
                </b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-3">
              <b-col md="3">
                <p class="my-2">Password: </p>
              </b-col>
              <b-col md="9">
                <b-form-input v-model="form.password" :type="hidePassword" size="lg" :disabled="disabled"
                  :state="this.form.password ? null : false"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="mt-4 mb-5">
              <b-col md="3" offset-md="3">
                <b-button type="submit" :variant="buttonVar" :disabled="disabled" block>Save</b-button>
              </b-col>
              <b-col md="3">
                <b-button variant="success" @click="editForm" block>Edit</b-button>
              </b-col>
            </b-row>

          </b-form>
        </b-col>
      </b-row>

    </b-container>
    <Footer></Footer>
  </div>
</template>
  
<script>
import user from '@/store/modules/user';
import { getToken } from '@/utils/auth';

import Nav from "@/views/HomePage/Nav";
import Footer from "@/views/HomePage/Footer";
export default {
  name: 'ProfileView',
  components: {
    Footer,
    Nav
  },
  data() {
    var editing
    return {
      changed: false,
      disabled: true,
      buttonVar: 'danger',
      editing: false,
      hidePassword: 'password',
      form: {
        // username: 'Ranger',
        // fitstname: 'Joe',
        // lastname: 'Biden',
        // email: 'President@whilehouse.gov.us',
        // password: '123456'
      },
      avatar: ''
    }
  },
  methods: {
    editForm() {
      if (!this.editing) {   //user edit info, editing===false
        this.editing = true
        this.disabled = false
        this.buttonVar = 'success'
        this.hidePassword = "text"
      }
      else {
        this.editing = false
        this.disabled = true
        this.buttonVar = 'danger'
        this.hidePassword = "password"
      }
    },

    checkEmail() {
      const email = this.form.email
      // console.log(email)
      return /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/.test(email) ? null : false
    },

    checkSubmit() {
      console.log(Object.values(this.form))
      const userData = Object.values(this.form).slice(1,7)
      const noEmpty = userData.every(value => {    // return true only when all value is not empty
        if (value != '') {
          return true;
        }
        return false;
      })
      return noEmpty
      // console.log(noEmpty)
    },

    onSubmit() {
      console.log("form submitted")
      // console.log(this.form.username)
      if (this.checkSubmit() && this.checkEmail() != false){
        this.axios.post("/user/userByID?userID=" + this.form.id, this.form).then(res => {
        console.log("updated")
        console.log(this.form)
        console.log(res)
        res.data === 1 ? this.$message.success("Change Saved!") : this.$message.error("Save Failed")
        this.editForm()
      })
      } else {
        this.$message.error("Empty input or invalid E-mail!")
      }
    }
  },

  created: function () {
    console.log("profile View created")
    console.log(user.state)
    const check = getToken()
    if (check) {
      this.axios.get("/user/userByName?username=" + user.state.name).then(res => {
        // console.log(res)
        this.form = res.data
        console.log(this.form)
        this.avatar = this.form.avatar

      })
    } else {
      this.$message.error("You haven't login yet!")
      this.$router.push('/login')
    }
  }
};
</script>
  
<style scoped>
span {
  display: block;
}

p {
  font-size: large;
}
</style>