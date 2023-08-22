<template>
  <div class="travel_planner">
    <Nav></Nav>

    <b-jumbotron bg-variant="secondary" class="mt-0">
      <template #header>About Us</template>
      <template #lead>


        <b-container class="mt-3">
          <br />
          <h2>What is travelplanner?</h2>
          <br />
          <p>TravelPlanner is your one-stop destination for all your travel needs, on our website you can easily explore
            attractions, create itineraries and book hotels, transportation and tours.</p>

          <br />
          <h2>How Does travelplanner Work?</h2>
          <br />
          <p>Well, it's simple - enter the details you need and you'll get the itinerary you plan to travel.
            When you visit the site, you can choose where you want to travel and the dates, and if you're not sure what
            to do, we'll also give you popular tourist spots. You can also choose tours by group!
            If you choose to plan your next vacation, you can do so by inserting some details about what you want. You
            just need to tell us where you want to go, your stay, your transportation, accommodation and activity needs.
          </p>

          <br />
          <br />
          <h2 style="text-align:center">Enjoy your travel and have fun!</h2>
        </b-container>
      </template>

    </b-jumbotron>

    <Footer></Footer>
  </div>
</template>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import Nav from "@/views/HomePage/Nav";
import Carousel from "@/views/HomePage/Carousel";
import Jum from "@/views/HomePage/Jum";
import Footer from "@/views/HomePage/Footer";
import Cards from "@/views/HomePage/Cards";

export default {
  name: 'about',
  data() {
    // declare message with an empty value
    return {
      message: ''
    }
  },
  components: {
    Cards,
    Footer,
    Jum,
    Carousel,
    Nav
  },
  computed: {
    // ...mapGetters([
    //   'name'
    // ])
  },
  methods: {
    test() {
      this.axios.get("/user/list").then(res => {
        console.log(res.data)
      })
    }

  }
}
</script>
<style scoped>
.travel_planner {
  background-color: #a9a8a8;
  /* background-color: antiquewhite; */
}

p {
  text-align: left;
}
</style>