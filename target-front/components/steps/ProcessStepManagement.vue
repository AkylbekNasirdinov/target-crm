<template>
  <v-container>
    <v-row>
      <v-col v-for="step in steps">
        <process-step
          :step="step"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import draggable from 'vuedraggable'
import VDComponent from 'vue-flexable-dnd';
import ProcessStep from "./ProcessStep";
import {mapGetters} from "vuex";

export default {
  name: "processStepManagement",
  components: {ProcessStep, draggable, VDComponent},
  computed: {
    ...mapGetters('stepStore', {steps: 'getSteps'})
  },
}
</script>

<style scoped>

</style>
