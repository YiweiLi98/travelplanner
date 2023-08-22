<template>
  <div>
    <b-jumbotron bg-variant="secondary">
      <template #header>Your Travel Planner</template>
      <template #lead>
        Hi,{{ title }},We are always here for your travel plan!
      </template>
      <hr class="my-4">
      <div class="my-0">
        <b-button variant="success " href="#" size="lg" @click="goToPage">Plan Next Vacation</b-button>&nbsp;
        <b-button variant="success" href="#" size="lg"@click="toAttraction">Explore Attractions</b-button>
      </div>
    </b-jumbotron>
  </div>
</template>

<script>
import user from '@/store/modules/user'
import { getToken } from '@/utils/auth'
export default {
  computed:{
    title(){
      console.log(user.state.name)
      return user.state.name
    }
  },
  name: "Jum",
  methods:{
    goToPage() {
      const check = getToken()
      if(check) {
        this.$router.push('/choose1')
      } else {
        this.$message.error("Please login before planning")
        this.$router.push('/login')
      }

    },
    toAttraction(){
      const scrollDom = document.getElementById('carousel');
      scrollDom.scrollIntoView();
    }

  }
}
</script>

<style scoped>

</style>
