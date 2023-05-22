<template>
  <process-management/>
</template>

<script>
import ProcessManagement from "../../components/processes/ProcessManagement";
import {mapActions} from "vuex";
export default {
  name: "index",
  components: {ProcessManagement},
  mounted() {
    this.fetchProcesses()
  },
  methods: {
    ...mapActions('processesStore', { fetchProcesses: 'fetchProcesses' })
  }
}
</script>

<style scoped>

</style>
