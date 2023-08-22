<template>
  <div>

    <!--      <b-nav class="navbar navbar-dark bg-dark navbar-static-bottom mt-2">-->
    <!--        <b-nav-item >Policy Agreement</b-nav-item>-->
    <!--        <b-nav-item>@copyright</b-nav-item>-->
    <!--        <b-nav-item>About us</b-nav-item>-->
    <!--        <b-nav-item >Feedback</b-nav-item>-->
    <!--      </b-nav>-->
    <!--   -->
    <div>
      <b-nav tabs align="center" class="navbar navbar-dark bg-dark navbar-static-bottom mt-2">
        <b-nav-item @click="toPolicy()">Policy Agreement</b-nav-item>
        <!-- <b-nav-item>@copyright</b-nav-item> -->
        <b-nav-item @click="toAbout()">About us</b-nav-item>
        <!-- <b-nav-item>Feedback</b-nav-item> -->
      </b-nav>
    </div>
  </div>
</template>

<script>
export default {
  name: "Footer",
  methods: {

    toAbout() {
      this.$router.push('/about')
    },
    toPolicy() {
      this.$router.push('/policy')
    },
  }
}
</script>

<style scoped>
.nav {
  margin: 0;
}
</style>
