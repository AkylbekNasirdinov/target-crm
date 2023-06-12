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
          v-if="step?.stepId"
          :param="parameter"
        />
        <v-btn v-else-if="step && !step.isInitialStep"
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
import {mapActions, mapGetters, mapState} from "vuex";
import StepParam from "../param/StepParam";
import Parameter from "/models/StepParam"
export default {
  name: "ProcessStep",
  components: {StepParam},
  props: ['step'],
  data () {
    return {
      param: {}
    }
  },
  computed: {
    ...mapState('paramStore', {params: 'params', busy: 'busy'}),
    ...mapGetters('paramStore', {getParams: 'getStepParam'}),
    parameter: {
      get() {
        if (this.step)
        return  this.params.get(this.step.id) || this.param
        return this.param
      },
      set(newValue) {
        this.param = newValue
      }
    },
    getStepName() {
      return this.step && this.step.actionDefinition ? this.step.actionDefinition.name : ''
    },
  },
  methods: {

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
      this.parameter =  new Parameter({'stepId': this.step.id, 'parameterType': this.step.actionDefinition.requiredParameter})
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
