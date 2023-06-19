<template>
<customer-management/>
</template>

<script>
import CustomerManagement from "../../components/customers/CustomerManagement";
import {mapActions} from "vuex";
export default {
  name: "index",
  components: {CustomerManagement},
  mounted() {
    this.fetchCustomers()
  },
  methods: {
    ...mapActions('customerStore', {fetchCustomers: 'fetchCustomers'})
  }
}
</script>

<style scoped>

</style>
