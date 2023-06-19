<template>
  <div>
    <v-card-title>
      {{ getParameterType }}
    </v-card-title>
    <v-card-text>
      <v-select
        v-if="this.param.parameterType === 'CATEGORY'"
        :items="this.categories"
        item-text="name"
        item-value="name"
        v-model="param.value"/>
      <v-text-field
        v-else-if="this.in(['SERVICE_AMOUNT', 'PRICE_MINIMUM', 'PRICE_TOTAL'], this.param.parameterType)"
        v-model="param.value"/>
      <v-row v-else-if="this.param.parameterType === 'DATE_PERIOD'">
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
      dateRange: '',
      parameterTypes: [{key: 'CATEGORY', value: 'Категория'},
                       {key: 'SERVICE_AMOUNT', value: 'Количество Услуг'},
                       {key: 'PRICE_MINIMUM', value: 'Минимальная Цена'},
                       {key: 'PRICE_TOTAL', value: 'Общая Стоимость'},
                       {key: 'DATE_PERIOD', value: 'Период Времени'}]
    }
  },
  mounted() {
  },
  computed: {
    ...mapGetters('paramStore', {categories: 'getCategories'}),
    getParameterType() {
      return _.find(this.parameterTypes, (parameterType) => {return parameterType.key === this.param.parameterType}).value
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
