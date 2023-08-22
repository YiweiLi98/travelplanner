<template>
  <div class="travel_planner">
<!--    <Nav></Nav>-->
<!--    <Jum class="mb-2" :title=name></Jum>-->

    <Jum class="mb-2" ></Jum>
    <div class="container">
      <Cards></Cards>
      <Carousel ></Carousel>
    </div>
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
  name: 'HomeView',
  data(){
    // declare message with an empty value
    return{
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
  methods:{
    test(){
      this.axios.get("/user/list").then(res=>{
        console.log(res.data)
      })
    }

  }
}
</script>
<style>
.travel_planner{
  background-color: antiquewhite;
}
</style>
