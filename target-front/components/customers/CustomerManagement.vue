<template>
  <div>
    <v-card >
      <v-card-title>
        {{ 'Клиенты' }}
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
        :items="getCustomers"
        :search="search"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
          </tr>

        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "CustomerManagement",
  data() {
    return {
      search: ''
    }
  },
  computed: {
    ...mapGetters('customerStore', { getCustomers: 'getCustomers' })
  },
  methods: {
    getHeaders() {
      return [
        {text: 'ID', value: 'id'},
        {text: 'Имя', value: 'name'},
      ]
    },
  }
}
</script>

<style scoped>

</style>
