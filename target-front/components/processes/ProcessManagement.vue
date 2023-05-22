<template>
  <div>
    <v-card >
      <v-card-title>
        {{ 'Процессы' }}
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
        :items="getProcesses"
        :search="search"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.offeredService.name }}</td>
            <td>{{ item.isActive }}</td>
            <td>{{ item.executionCron }}</td>
            <td>{{ item.lastExecutionDate }}</td>
            <td>
              <v-btn  icon>
                <v-icon @click="(item)">
                  edit
                </v-icon>
              </v-btn>
            </td>
            <td>
              <v-btn  icon>
                <v-icon @click="(item)">
                  mdi-cog
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
import {mapGetters} from "vuex";

export default {
  name: "ProcessManagement",
  data() {
    return {
      search: ''
    }
  },
  computed: {
    ...mapGetters('processesStore', { getProcesses: 'getProcesses' })
  },
  methods: {
    getHeaders() {
      return [
        {text: 'ID', value: 'id'},
        {text: 'Название', value: 'name'},
        {text: 'Предлагаемая услуга', value: 'offeredService.name'},
        {text: 'Активен', value: 'isActive'},
        {text: 'Периодичность запуска', value: 'executionCron'},
        {text: 'Последний запуск', value: 'lastExecutionDate'},
        {text: 'Редактировать'},
        {text: 'Настроить шаги'},
      ]
    }
  }
}
</script>

<style scoped>

</style>
