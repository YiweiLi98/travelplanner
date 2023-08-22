<template>
  <div>
    <div class="planbody">
      <b-row v-for="j in rowbutlast">
        <b-col v-for="i in colbutlast">
          <div>
            <h5><b>Day {{ (j - 1) * 3 + i }} - {{ home }}, {{ des }}</b></h5>
            <p class="mx-0"> {{ displayDate((j - 1) * 3 + i - 1) }}</p>
          </div>
          <!--          <b-card v-for="th in things_todo" :key="th.id" :img-src="th.img" img-alt="Card image" img-left class="mb-3"-->
          <!--                  img-width="50%" >-->
          <!--            <b-card-body :title="th.name" title-tag="b" class="card_body">-->
          <!--              <b-card-text>-->
          <!--                {{ th.address }}-->
          <!--                <p><b>Price: </b>{{ th.price }}$</p>-->
          <!--                            <b>Type: </b>{{ th.type }}-->
          <!--              </b-card-text>-->
          <!--            </b-card-body>-->
          <!--          </b-card>-->
          <b-card v-for="th in plan[(j - 1) * 3 + i]" :key="th.id" :img-src="th.img" img-alt="Card image" img-left
                  class="mb-3"
                  img-width="50%" style="height: 250px">
            <b-card-body :title="th.name" title-tag="b" class="card_body">
              <b-card-text>
                {{ th.address }}
                <p><b>Price: </b>{{ th.price }}$</p>
                <b v-if="th.type">Type: </b>{{ th.type }}
              </b-card-text>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <b-row cols="3" v-if="collast!=0">
        <b-col v-for="i in collast">
          <div>
            <h5><b>Day {{ rowbutlast * 3 + i }} - {{ home }}, {{ des }}</b></h5>
            <p class="mx-0"> {{ displayDate(rowbutlast * 3 + i - 1) }}</p>
          </div>
          <!--          <b-card v-for="th in things_todo" :key="th.id" :img-src="th.img" img-alt="Card image" img-left class="mb-3"-->
          <!--                  img-width="50%">-->
          <!--            <b-card-body :title="th.name" title-tag="p" class="card_body">-->
          <!--              <b-card-text>-->
          <!--                {{ th.address }}-->
          <!--                <p><b>Price: </b>{{ th.price }}$</p>-->
          <!--                              <p><b>Type: </b>{{ th.type }}</p>-->
          <!--              </b-card-text>-->
          <!--            </b-card-body>-->
          <!--          </b-card>-->
          <b-card v-for="th in plan[ rowbutlast * 3 + i]" :key="th.id" :img-src="th.img" img-alt="Card image" img-left
                  class="mb-3"
                  img-width="50%" style="height: 250px">
            <b-card-body :title="th.name" title-tag="b" class="card_body">
              <b-card-text>
                {{ th.address }}
                <p><b>Price: </b>{{ th.price }}$</p>
                <p><b v-if="th.type">Type: </b>{{ th.type }}</p>
              </b-card-text>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <b-button squared variant="secondary" size="lg" class="col-4 my-2" pill @click="goToPayment">Proceed to Payment
      </b-button>

    </div>

    <!--    <div style="width: 100%;position: fixed;bottom: 0">-->
    <div class="navbar-fixed-bottom">
      <Footer></Footer>
    </div>
    <!--      </div>-->
  </div>


</template>

<script>
import dateFormat from "dateformat";
import Footer from "@/views/HomePage/Footer";

export default {
  name: "PlanBodyView",
  components: {
    Footer

  },
  data() {
    return {
      plan: {},
      things_todo: [],
      home: this.$store.state.user.home,
      des: this.$store.state.user.des,
      day: this.$store.state.user.day,
      budget: this.$store.state.user.budget,
      startDay: new Date(this.$store.state.user.startDay),
      endDay: new Date(this.$store.state.user.endDay),
      rowbutlast: this.$store.state.user.day <= 3 ? 1 : parseInt(this.$store.state.user.day / 3),
      colbutlast: this.$store.state.user.day <= 3 ? this.$store.state.user.day : 3,
      collast: this.$store.state.user.day <= 3 ? 0 : parseInt(this.$store.state.user.day % 3),
      types: this.$store.state.user.style,
      mode: this.$store.state.user.mode,
      group: this.$store.state.user.group,
      price: ''
    }
  },
  created() {

    // this.axios.post('/plan/list', {
    //   startDay:this.formatDate(this.startDay),
    //   endDay:this.formatDate(this.endDay),
    //   home:this.home,
    //   des:this.des,
    //   budget:this.budget,
    //   style:this.types.toString(),
    //   mode:this.mode,
    //   group:this.group,
    //   day:this.day
    // })
    //     .then((response) => {
    //       console.log(response.data);
    //     })
    const params_test = {
      startDay: '2022-10-21',
      endDay: '2022-10-27',
      home: 'sydney',
      des: 'melbourne',
      budget: 1,
      style: ['nature', 'food'].toString(),
      mode: 3,
      group: 2,
    }
    const params_reality = {
      startDay: this.formatDate(this.startDay),
      endDay: this.formatDate(this.endDay),
      home: this.home,
      des: this.des,
      budget: this.budget,
      style: this.types.toString(),
      mode: this.mode,
      group: this.group,
    }
    this.axios.get("/plan/list", {
      params: params_reality
    }).then(res => {
      console.log(res.data)
      this.plan = res.data
      let price = 0
      for (const resKey in this.plan) {
        for (const resKeyKey in this.plan[resKey]) {
          price += parseInt(this.plan[resKey][resKeyKey].price)
        }
      }
      this.price = price
      console.log(this.price)
      // console.log(res.data[1][0].price)
      this.$store.commit("user/SET_PRICE", this.price)
    })

    // this.axios.get("/things/listbytype",{params:{
    //   types:['Attraction'].toString(),
    //     city:'Sydney'
    //   }}).then(res => {
    //   this.things_todo = res.data
    // })


  }, mounted() {

  },
  methods: {
    displayDate(num) {
      let date = new Date(this.startDay)
      date.setDate(date.getDate() + num)
      return dateFormat(date, "fullDate");
    },
    formatDate(date) {
      // console.log(date.toISOString().slice(0, 10))
      return date.toISOString().slice(0, 10);
    },
    goToPayment() {
      this.$router.push("/payment")
      this.$store.commit('user/SET_PRICE', this.price)
    }
  }
}
</script>

<style scoped>
.planbody {
  width: 90%;
  margin: 0 auto;
  min-height: 1000px;
  background-color: white;
}

.card_body {
  text-align: left;
  padding: 0;
}


</style>
