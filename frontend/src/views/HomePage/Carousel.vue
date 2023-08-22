<template>
  <div id="carousel">
    <div class="page-header  text-dark pb-lg-2" id="card-carousel">
      <h1>Top Attractions
        <small class="text-white">in three amazing cities</small>
      </h1>
    </div>
    <b-carousel
        id="carousel-1"
        :interval="4000"
        controls
        indicators
        fade
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333;" >
      <b-carousel-slide
          v-for="att in attractions"
          :key="att.id"
          :caption="att.name"
          :text="att.desc"
          :img-src="att.img"
      ></b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  name: "Carousel",
  data(){
    // declare message with an empty value
    return{
      message: '',
      attractions:[]
    }
  },
  created() {
    this.axios.get("/things/attractionlist").then(res=>{
      this.attractions=res.data
    })
  }
}
</script>

<style scoped>

</style>
