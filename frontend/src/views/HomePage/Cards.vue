<template>
  <div class="mb-3">
    <b-card-group deck fluid class="p-5 bg-light" >
      <b-card :title="cityList[0]" img-height="45%" img-src="https://www.tripsavvy.com/thmb/NrpZyAWL1TgfxvmENhSOK-os3Gs=/1885x1414/smart/filters:no_upscale()/OperaHouse-755d893182dc4811b608eb1a99792fd7.jpg" img-alt="Image" img-top>
        <b-card-text>
          Sydney offers an urban mix of rich history and contemporary buzz, but with a distinctly Australian spirit.
        </b-card-text>
        <template #footer><b-button pill size="lg"  href="#" variant="secondary" @click="getWeatherCondition(cityList[0])">Check Weather</b-button> </template>
      </b-card>

      <b-card :title="cityList[1]" img-height="45%" img-src="https://www.gostudy.com.au/wp-content/uploads/2018/10/MEL_header_2-1.jpg" img-alt="Image" img-top>
        <b-card-text>
          Colourful, lively Melbourne has broad appeal for every type of traveller, from cosy cafes to local art to Australian and Aboriginal history to spectator sports.
        </b-card-text>
        <template #footer><b-button pill size="lg"  href="#" variant="secondary" @click="getWeatherCondition(cityList[1])">Check Weather </b-button> </template>
      </b-card>

      <b-card :title="cityList[2]" img-height="45%" img-src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Skylines_of_Brisbane_in_winter_misty_morning_seen_from_Kangaroo_Point%2C_Queensland_04.jpg/1200px-Skylines_of_Brisbane_in_winter_misty_morning_seen_from_Kangaroo_Point%2C_Queensland_04.jpg" img-alt="Image" img-top>
        <b-card-text>
          Australia’s third-largest city, Brisbane is the hub of Queensland culture, offering a peek at the past and a glimpse into the future.
        </b-card-text>
        <template #footer ><b-button pill size="lg"  href="#" variant="secondary" @click="getWeatherCondition(cityList[2])">Check Weather</b-button> </template>

      </b-card>
    </b-card-group>
    <el-dialog
        title="Weather Condition In The Next Few Days"
        :visible.sync="dialogVisible">
      <el-image :src="weatherDiagram"></el-image>
      <el-button @click="dialogVisible=false" type="primary">I Got it</el-button>
    </el-dialog>
  </div>

</template>

<script>


export default {
  name: "Cards",
  data(){
    return{
      cityList:["Sydney","Melbourne","Brisbane"],
      dialogVisible:false,
      weatherDiagram:'',

    }
  },
  methods:{
    getWeatherCondition(city){
      this.axios.get("https://nominatim.openstreetmap.org/search.php?city="+city+"&format=json").then(res=>{
        let data = res.data;
        let la=data[0].lat
        let lo=data[0].lon
        this.dialogVisible=true
        this.weatherDiagram="http://www.7timer.info/bin/civil.php?lon="+lo+"&lat="+la+"&ac=0&lang=en&unit=metric&output=internal&tzshift=0"
      })
    }
  }


}
</script>

<style scoped>

</style>
