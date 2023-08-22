<template>
  <div class="modal-bg" v-show="show">
    <div class="modal-container">
      <div class="modal-header">
        {{ title }}
      </div>
      <div class="modal-main">
        <slot></slot>
      </div>
      <div class="modal-footer">
        <button @click="hideModal">Cancel</button>
        <button @click="submit">Confirm</button>
      </div>
    </div>
  </div>
</template>



<script>
export default {
  name: 'modal',
  props: {
    show: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    },
  },
  methods: {
    hideModal() {
      this.$emit('hideModal')
    },

    submit() {
      this.$emit('submit')
    },
  }
}
</script>
<style>

/*.modal-container {

  position: relative;
  width: 200px;
  height: 20%;

  margin: 0 auto;
  overflow: hidden;
  background: grey;

}*/

</style>
