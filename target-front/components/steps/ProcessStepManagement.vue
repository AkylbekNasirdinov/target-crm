<template>
  <v-container>
    <v-row>
      <v-col v-for="step in steps"
        key="id">
        <process-step
          :step="step"
          @dropStep="changeOrder"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import draggable from 'vuedraggable'
import VDComponent from 'vue-flexable-dnd';
import ProcessStep from "./ProcessStep";
import {mapGetters, mapActions} from "vuex";

export default {
  name: "processStepManagement",
  components: {ProcessStep, draggable, VDComponent},
  computed: {
    ...mapGetters('stepStore', {steps: 'getSteps'})
  },
  methods: {
    ...mapActions('stepStore', {changeStepOrder: 'changeStepOrder'}),
    changeOrder(changedSteps) {
      let currentOrder = this.steps.map(step => step.id)
      let firstIndex = currentOrder.indexOf(changedSteps[0])
      let secondIndex = currentOrder.indexOf(changedSteps[1])
      if (firstIndex ===0 || secondIndex ===0)
        return
      let temp = currentOrder[firstIndex]
      currentOrder[firstIndex] = currentOrder[secondIndex]
      currentOrder[secondIndex] = temp
      this.changeStepOrder(currentOrder)
    }
  }
}
</script>

<style scoped>

</style>
