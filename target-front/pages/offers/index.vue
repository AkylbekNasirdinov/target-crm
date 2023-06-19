<template>
<offer-management/>
</template>

<script>
import {mapActions} from "vuex";
import OfferManagement from "../../components/offers/OfferManagement";

export default {
  name: "index",
  components: {OfferManagement},
  mounted() {
    this.fetchOffers()
  },
  methods: {
    ...mapActions('processesStore', {fetchOffers: 'fetchOffers'})
  }
}
</script>

<style scoped>

</style>
