<template>
    <div style="background-color:#a9a8a8; min-height: 100%; height: 50vh; "> 
      <Nav></Nav>
      <b-container style="min-height:100%">
          <b-row align-h="center" class="mt-5">
              <b-col md="2">
                  <b-avatar src="https://placekitten.com/300/300" size="10rem"></b-avatar>
              </b-col>
          </b-row>
          <b-row align-h="center" class="my-3">
              <b-col md="8">
                  <b-tabs content-class="mt-3" active-nav-item-class="font-weight-bold text-dark" > <!--fill="true"-->
                      <b-tab title="Current" active title-link-class="text-light font-weight-bold">
                          <b-table striped hover :items="currentOrder" table-variant="secondary" head-variant="dark">
                          </b-table>
                          <b-button variant="success" class="mx-3" @click="toSchedule">Schedule</b-button>
                          <b-button variant="danger">Refund</b-button>
                      </b-tab>
                      <b-tab title="Past" title-link-class="text-light font-weight-bold">
                          <b-table striped hover :items="orderHistory" table-variant="secondary" head-variant="dark">
                          </b-table>
                      </b-tab>
                  </b-tabs>
              </b-col>
          </b-row>
      </b-container>
      <Footer></Footer>
  </div>
</template>

<script>
import Nav from "@/views/HomePage/Nav";
import Footer from "@/views/HomePage/Footer";
export default {
  name: 'OrderView',
  components: {
      Footer,
      Nav
  },
  data() {
      return {
          changed: false,
          disabled: true,
          buttonVar: 'danger',
          editing: false,
          hidePassword: 'password',
          form: {
              userName: 'Ranger',
              fitstName: 'Joe',
              lastName: 'Biden',
              email: 'President@whilehouse.gov.us',
              password: '123456'
          },
          orderHistory: [
              { ID: 1, From: 'Sydney', To: 'Melbourne', Flight: 'VA498', Duration: '7', Price: '2000' },
              { ID: 2, From: 'Melbourne', To: 'Sydney', Flight: 'VA498', Duration: '2', Price: '4000' },
              { ID: 3, From: 'Sydney', To: 'Brisbane', Flight: 'VA498', Duration: '5', Price: '10000' }, ,
              { ID: 4, From: 'Brisbane', To: 'Melbourne', Flight: 'VA498', Duration: '6', Price: '1000' },
          ],
          currentOrder: [
              { Day: 1, Destination: 'Sydney Opera House ', Accommodation: 'Sydney Hotel' },
              { Day: 2, Destination: 'Sydney Zoo', Accommodation: 'Sydney Hotel' },
              { Day: 3, Destination: 'Sydney Opera House ', Accommodation: 'Sydney Hotel' },
              { Day: 4, Destination: 'Sydney Zoo', Accommodation: 'Sydney Hotel' },
              { Day: 5, Destination: 'Sydney Opera House ', Accommodation: 'Sydney Hotel' },
              { Day: 6, Destination: 'Sydney Zoo', Accommodation: 'Sydney Hotel' },
              { Day: 7, Destination: 'Sydney Opera House ', Accommodation: 'Sydney Hotel' },

          ]
      }
  },
  methods: {
      toSchedule() {
          this.$router.push({ path: '/plan' })
      }
  }
};
</script>

<style scoped>
span {
  display: block;
}


</style>