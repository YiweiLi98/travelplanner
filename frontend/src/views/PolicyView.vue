<template>
  <div class="travel_planner">
    <Nav></Nav>

    <b-jumbotron bg-variant="secondary" class="mt-2">
      <template #header>Privacy Policy</template>
      <template #lead>


        <b-container class="mt-3">
          <strong>WHAT INFORMATION DO WE COLLECT?</strong>
          <br/>
          <br/>
          <p>We collect personal information that you voluntarily provide to us when you register on the Website,
            express
            an
            interest in obtaining information about us or our products and Services, when you participate in activities
            on
            the Website or otherwise when you contact us.</p>
          <p>The personal information that we collect depends on the context of your interactions with us and the
            Website,
            the
            choices you make and the products and features you use. The personal information we collect may include the
            following:</p>
          <p>Personal Information Provided by You. We may collect names; phone numbers; email addresses; mailing
            addresses;
            job titles; contact preferences; data collected from surveys; usernames; passwords; contact or
            authentication
            data; information you provide when using our products (e.g., business name, business email, business
            address,
            business fax, company website, etc.); number of unique views and total number of visitors when you deploy
            your
            cookie banner using our consent management solution; information in blog comments; financial information
            (e.g.,
            last four digits of your debit or credit card number, billing history, billing address, card type, issuing
            bank,
            expiration date, card origin by country); profile photo; and other similar information.</p>
          <p>Payment Data. We may collect data necessary to process your payment if you make purchases, such as your
            payment
            instrument number (such as a credit card number), and the security code associated with your payment
            instrument.
            All payment data is stored by Stripe. You may find their privacy notice link here.</p>
          <p>Social Media Login Data. We may provide you with the option to register with us using your existing social
            media
            account details, like your Facebook, Twitter or other social media account. If you choose to register in
            this
            way, we will collect the information described in the section called “HOW DO WE HANDLE YOUR SOCIAL LOGINS?”
            below.</p>
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
  name: 'policy',
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