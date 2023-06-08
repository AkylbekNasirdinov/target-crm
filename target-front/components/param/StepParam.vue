<template>
  <v-card>
    <v-card-title>
      {{ param.parameterType }}
    </v-card-title>
    <v-card-text>
      <v-select
        v-if="param.parameterType === 'CATEGORY'"
        :items="this.categories"
        item-value="name"
        v-model="param.value"/>
      <v-text-field
        v-if="this.in(['SERVICE_AMOUNT', 'PRICE_MINIMUM', 'PRICE_TOTAL'], param.parameterType)"
        v-model="param.value"/>
      <v-row v-else>
        <v-date-picker
          range
          v-model="dateRange"/>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
import * as _ from 'lodash'

export default {
  name: "StepParam",
  props: {
    param: Object,
    categories: Array
  },
  data() {
    return {
      dateRange: ''
    }
  },
  methods: {
    in(array, value) {
      return _.includes(array, value)
    }
  }

}
</script>

<style scoped>

</style>
