<template>
  <v-card>
    <v-card-title>
      {{step.actionDefinition.name}}
    </v-card-title>
    <v-card-text>
      
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "ProcessStep",
  props: ['step']
}
</script>

<style scoped>

</style>
