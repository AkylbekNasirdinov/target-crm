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
    this.fetchCategories()
  },
  methods: {
    ...mapActions('processesStore', { fetchProcesses: 'fetchProcesses' }),
    ...mapActions('paramStore', {fetchCategories: 'fetchCategories'}),
  }
}
</script>

<style scoped>

</style>
