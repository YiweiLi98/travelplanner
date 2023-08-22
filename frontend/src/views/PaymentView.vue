<template>
  <div class="payment-container">
    <Nav></Nav>


    <!-- <Modal :show="show" :title="title" @hideModal="hideModal" @submit="submit">
      <p>Payment success!</p>
    </Modal> -->

    <el-form class="payment-form" ref="paymentForm" :rules="rules" :model="paymentForm" label-width="0px" label-position="left"
      :show="back_show" @hideBack="hideBack" style="margin-top:-5px">

      <h1>You need to pay ${{price}}</h1>
      <h3 class="payment-title">Payment</h3>

      <el-form-item prop="cardNumber">
        <el-input type="text" v-model="paymentForm.cardNumber" placeholder="Card number" tabindex="1"
          autocomplete="on" />
      </el-form-item>
      <el-form-item prop="expiryDate">
        <el-input type="text" v-model="paymentForm.expiryDate" placeholder="Expriy Date" tabindex="1"
          autocomplete="on" />
      </el-form-item>
      <el-form-item prop="cvv">
        <el-input type="text" v-model="paymentForm.cvv" placeholder="Security Code" tabindex="1" autocomplete="on" />
      </el-form-item>

      <el-form-item class="button1">
        <!--el-button type="primary" :loading="loading" @click="handleLogin">Login</el-button>
        <el-button type="primary"  @click="toRegister">Register</el-button-->


        <el-button type="primary" :loading="loading" @click="onSubmit">Confirm</el-button>

        <el-button type="primary" @click="toHome">Cancel</el-button>
      </el-form-item>
    </el-form>

    <Footer></Footer>
  </div>
</template>
<script>
// @ is an alias to /src
import Footer from "@/views/HomePage/Footer";
import Nav from "@/views/HomePage/Nav";
import Modal from './Modal.vue'
export default {
  name: 'LoginView',
  components: {
    Nav,
    Footer,
    Modal
  },


  data() {
    return {
      paymentForm: {
        cardNumber: '',
        cvv: '',
        expiryDate: ''
      },

      rules: {
        cardNumber: [
          { required: true, message: 'Please enter right card number', max:16, min:16}
        ],
        expiryDate: [
          { required: true, message: 'Please enter right expiry date', pattern: /^(0[1-9]|1[0-2])\/?([0-9]{2})$/}
        ],
        cvv: [
          { required: true, message: 'Please enter right CVV number', max:3, pattern: /^[1-9]\d{0,2}(?:\,\d{1,3})?$/}
        ]
      },

      loading: false,
      title: 'Payment result',
      show: false,
      back_show: false,
      price: this.$store.state.user.price
    }
  },
  methods: {
    toCombo() {
      this.$router.push({ path: '/combo' })
    },
    toHome() {
      this.$router.push({ path: '/home/plan' })
    },
    checkSubmit() {
      console.log(Object.values(this.paymentForm))
      const userData = Object.values(this.paymentForm)
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
      if (this.checkSubmit()) {
        this.$message.success("Payment success!")
        this.$router.push({ path: '/home/homepage' })
      } else {
        this.$message.error("Invalid input!")
      }
    }


    // hideBack() {
    //   this.back_show = false
    // },
    // hideModal() {
    //   // 取消弹窗回调
    //   this.show = false
    //   //Modal.show;
    // },
    // showModal() {
    //   this.show = true

    // },

    // submit() {
    //   // 确认弹窗回调
    //   this.show = false
    //   this.$router.push("/")
    // },

  },

}
</script>
<style>
/*body{
  margin: 0px;
  height:100%;
  position: relative;
}*/
.payment-container {

  background: antiquewhite;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6ca;

  /*background-color: antiquewhite;*/
  min-height: 100%;
  height: 100%;
  width: 100%;
  position: fixed;



  /*min-height: 100%;
  height: 100%;

  min-height: 100%;
  height: 100%;
  overflow: hidden;*/
}

.payment-form {

  position: relative;

  width: 520px;
  max-width: 100%;
  height: 86%;

  padding: 160px 12px 0;
  margin: 0 auto;
  overflow: hidden;

}

.payment-title {
  font-size: 26px;
  color: black;
  margin: 0 auto 20px auto;
  text-align: center;
  font-weight: 700;
}

.payment-container .el-input input {
  background: transparent;

  border: 0;
  -webkit-appearance: none;
  border-radius: 0;
  padding: 15px 5px 15px 15px;
  color: black;
  height: 47px;
  caret-color: black;
  margin: 10px 20px;


}

.payment-container.el-input input::-webkit-input-placeholder {
  color: black;
}

.payment-form .el-input input::-webkit-input-placeholder {
  color: gray;
}

.payment-container.el-input:-webkit-autofill {
  box-shadow: 0 0 0px 1000px #2d3a4b;
  -webkit-text-fill-color: black;
}

/*.el-form-item {
  border: 1px solid rgba(255, 255, 255, 0.1);
  background: rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  color: #454545;
}
.el-form-item.button1{
  background: transparent;
  border: none;
}
.el-button--primary {
  padding: 10px 20px;
  font-size: 14px;
  border-radius: 4px;
  width: 130px;
}*/
</style>
