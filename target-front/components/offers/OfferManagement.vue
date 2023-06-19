<template>
  <div>
    <v-card >
      <v-card-title>
        {{ 'Предложения' }}
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
        :items="getOffers"
        :search="search"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.createdAt }}</td>
            <td>{{ item.service.name }}</td>
            <td>{{ item.isConfirmed }}</td>
            <td>{{ item.customer?.name }}</td>
            <td>{{ item.process?.id }}</td>
          </tr>

        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "OfferManagement",
  data() {
    return {
      search: ''
    }
  },
  computed: {
    ...mapGetters('processesStore', { getOffers: 'getOffers' })
  },
  methods: {
    getHeaders() {
      return [
        {text: 'ID', value: 'id'},
        {text: 'Дата Создания', value: 'name'},
        {text: 'Предлагаемая услуга', value: 'service.name'},
        {text: 'Согласен', value: 'isConfirmed'},
        {text: 'Клиент', value: 'customer.name'},
        {text: 'Процесс', value: 'process.id'},
      ]
    },
  }
}
</script>

<style scoped>

</style>
