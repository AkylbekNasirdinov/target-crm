<template>
  <div
    v-bind="$attrs"
    @dragover.prevent
    @dragenter.prevent
    @drop="onDrop($event, step)">
    <v-card v-bind="$attrs"
            draggable="true"
            @dragstart="onDrag($event, step)"
            min-height="120">
      <v-card-title
        class="d-flex justify-center">
        {{ getStepName }}
      </v-card-title>
      <v-card-text class="d-flex justify-center" >
        <step-param
          v-if="getParams(step?.id) !== null && getParams(step?.id) !== undefined "
          :param="getParams(step?.id)"
        />
        <v-btn
          v-else-if="getParams(step?.id) === null || getParams(step?.id) === undefined && !!step?.actionDefinition.requiredParameter"
          @click="addParam()"
          color="primary"
          width="90%">
          <v-icon>add</v-icon>
        </v-btn>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";
import StepParam from "../param/StepParam";
import Parameter from "/models/StepParam"
export default {
  name: "ProcessStep",
  components: {StepParam},
  props: ['step'],
  computed: {
    ...mapGetters('paramStore', {getParams: 'getStepParam'}),
    canDrag() {
      return !!this.step?.actionDefinition?.requiredParameter
    },
    getStepName() {
      return this.step?.actionDefinition?.name
    },
  },
  methods: {
    ...mapMutations('paramStore', {putParam: 'PUT_PARAM'}),
    ...mapActions('paramStore', {fetchParams: 'fetchStepParams'}),
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
      let param = new Parameter({'stepId': this.step.id, 'parameterType': this.step.actionDefinition.requiredParameter})
      this.putParam({key: this.step.id, value: param})
    },
    fetchStepParams() {
      if (this.step && this.step.id && !this.busy){
        debugger
        console.log('param: ' + this.params.get(this.step.id))
        this.param = this.params.get(this.step.id)
      }

    }
  }
}
</script>

<style scoped>

</style>
