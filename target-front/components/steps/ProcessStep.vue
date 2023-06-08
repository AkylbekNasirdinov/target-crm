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
        {{ step.actionDefinition.name }}
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
    ...mapGetters('stepStore', {getParams: 'getStepParam'})
  },
  mounted() {
    this.fetchParams(this.step.id)
  },
  methods: {
    ...mapActions('stepStore', {fetchParams: 'fetchStepParams'}),
    onDrag(event, step) {
      console.log(step)
      event.dataTransfer.dropEffect = 'move'
      event.dataTransfer.effectAllowed = 'move'
      event.dataTransfer.setData("Object", JSON.stringify(step));
    },
    onDrop(event, step) {
      console.log(step)
      let data = JSON.parse(event.dataTransfer.getData("Object"));
      debugger
    },
    addParam() {

    }
  }
}
</script>

<style scoped>

</style>
