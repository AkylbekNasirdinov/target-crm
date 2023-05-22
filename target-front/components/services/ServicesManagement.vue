<template>
  <div>
    <v-dialog  v-model="dialog" width="600">

    </v-dialog>
    <v-card >
      <v-card-title>
        {{ 'Услуги' }}
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
        <v-btn
          @click=""
          class="ml-3 mt-2"
          color="primary"
          text
          outlined
          small
        >
          <v-icon dark>
            add
          </v-icon>
          {{ 'Создать' }}
        </v-btn>
      </v-card-title>
      <v-data-table
        :headers="getHeaders()"
        :items="getServices"
        :search="search"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.description }}</td>
            <td>{{ item.category.name }}</td>
            <td>{{ item.price }}</td>
            <td>
              <v-btn  icon>
                <v-icon @click="(item)">
                  edit
                </v-icon>
              </v-btn>
            </td>
          </tr>

        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: "ServicesManagement",
  data ()  {
    return {
      search: '',
      dialog: false
    }
  },
  computed: {
    ...mapGetters('servicesStore', { getServices: 'getServices' })
  },
  methods: {
    getHeaders() {
      return [
        {text: 'ID', value: 'id'},
        {text: 'Название', value: 'name'},
        {text: 'Описание', value: 'description'},
        {text: 'Категория', value: 'category.name'},
        {text: 'Цена', value: 'price'},
      ]
    }
  }
}
</script>

<style scoped>

</style>
