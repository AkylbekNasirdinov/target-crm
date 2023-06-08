<template>
  <process-step-management/>
</template>

<script>
import ProcessStepManagement from "../../../components/steps/ProcessStepManagement";
import {mapActions} from "vuex";
export default {
  name: "index",
  components: {ProcessStepManagement},
  methods: {
    ...mapActions('stepStore', {fetchSteps: 'fetchSteps'}),
  },
  mounted() {
    this.fetchSteps(this.$route.params.id)
  }
}
</script>

<style scoped>

</style>
