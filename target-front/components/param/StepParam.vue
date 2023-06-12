<template>
  <div>
    <v-card-title>
      {{ getParameterType }}
    </v-card-title>
    <v-card-text>
      <v-select
        v-if="getParameterType === 'CATEGORY'"
        :items="this.categories"
        item-text="name"
        item-value="name"
        v-model="param.value"/>
      <v-text-field
        v-else-if="this.in(['SERVICE_AMOUNT', 'PRICE_MINIMUM', 'PRICE_TOTAL'], getParameterType)"
        v-model="param.value"/>
      <v-row v-else-if="getParameterType === 'DATE_PERIOD'">
        <v-date-picker
          range
          v-model="dateRange"/>
      </v-row>
    </v-card-text>
  </div>
</template>

<script>
import * as _ from 'lodash'
import {mapGetters} from "vuex";

export default {
  name: "StepParam",
  props: {
    param: Object,
  },
  data() {
    return {
      dateRange: ''
    }
  },
  mounted() {
  },
  computed: {
    ...mapGetters('paramStore', {categories: 'getCategories'}),
    getParameterType() {
      return this.param ? this.param.parameterType : ''
    }
  },
  methods: {
    in(array, value) {
      return _.includes(array, value)
    },
    showProps() {
      console.log(this.param)
    }
  }

}
</script>

<style scoped>

</style>
