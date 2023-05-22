<template>
  <services-management/>
</template>

<script>
import {mapActions} from 'vuex'
import ServicesManagement from "../../components/services/ServicesManagement";
export default {
  name: "index",
  components: {ServicesManagement},
  methods: {
    ...mapActions('servicesStore', {fetchServices: 'fetchAllServices'})
  },
  mounted() {
    this.fetchServices()
  }
}
</script>

<style scoped>

</style>
