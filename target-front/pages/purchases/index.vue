<template>
 <purchase-management/>
</template>

<script>
import PurchaseManagement from "../../components/purchases/PurchaseManagement";
import {mapActions} from "vuex";
export default {
  name: "index",
  components: {PurchaseManagement},
  mounted() {
    this.fetchPurchases()
  },
  methods: {
    ...mapActions('purchaseStore', {fetchPurchases: 'fetchPurchases'})
  }
}
</script>

<style scoped>

</style>
