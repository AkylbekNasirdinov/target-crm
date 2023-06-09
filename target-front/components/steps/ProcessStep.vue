<template>
  <div
    v-bind="$attrs"
    @dragover.prevent
    @dragenter.prevent
    @drop="onDrop($event, step)">
    <v-card v-bind="$attrs"
            draggable="true"
            @dragstart="onDrag($event, step)">
      <v-card-title
        class="d-flex justify-center">
        {{ getStepName }}
      </v-card-title>
      <v-card-text class="d-flex justify-center">
        <step-param
          v-if="false"
          :param="{}"
          :categories="[]"
        />
        <v-btn v-else
          @click=""
          color="primary"
          width="90%">
          <v-icon>add</v-icon>
        </v-btn>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import StepParam from "../param/StepParam";
export default {
  name: "ProcessStep",
  components: {StepParam},
  props: ['step'],
  computed: {
    ...mapGetters('stepStore', {getParams: 'getStepParam'}),
    getStepName() {
      return this.step && this.step.actionDefinition ? this.step.actionDefinition.name : ''
    }
  },
  mounted() {
    this.fetchStepParams()
  },
  methods: {
    ...mapActions('stepStore', {fetchParams: 'fetchStepParams'}),
    onDrag(event, step) {
      event.dataTransfer.dropEffect = 'move'
      event.dataTransfer.effectAllowed = 'move'
      event.dataTransfer.setData("Object", JSON.stringify(step));
    },
    onDrop(event, step) {
      let data = JSON.parse(event.dataTransfer.getData("Object"));
      let steps = []
      steps.push(step.id)
      steps.push(data.id)
      this.$emit('dropStep', steps)
    },

    addParam() {

    },
    fetchStepParams() {
      if (this.step && this.step.id)
        this.fetchParams(this.step.id)
    }
  }
}
</script>

<style scoped>

</style>
