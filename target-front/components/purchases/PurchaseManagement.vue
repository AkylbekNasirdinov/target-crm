<template>
  <div>
    <v-card >
      <v-card-title>
        {{ 'Покупки' }}
        <v-spacer/>
        <v-spacer/>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Поиск"
          single-line
          hide-details
        />
        <v-spacer/>
      </v-card-title>
      <v-data-table
        :headers="getHeaders()"
        :items="getPurchases"
        :search="search"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.customer.name }}</td>
            <td>{{ item.purchasedService.name }}</td>
          </tr>

        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "PurchaseManagement",
  data() {
    return {
      search: ''
    }
  },
  computed: {
    ...mapGetters('purchaseStore', { getPurchases: 'getPurchases' })
  },
  methods: {
    getHeaders() {
      return [
        {text: 'ID', value: 'id'},
        {text: 'Имя Клиента', value: 'customer.name'},
        {text: 'Название Услуги', value: 'purchasedService.name'},
      ]
    },
  }
}
</script>

<style scoped>

</style>
