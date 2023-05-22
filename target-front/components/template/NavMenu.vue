<template>
  <div>
    <v-list dense>
      <template v-for="(item, i) in items">
        <v-subheader v-if="item.header" :key="i">
          {{ (item.header) }}
        </v-subheader>

        <v-list-item
          v-if="!item.header  && !item.children"
          :key="i"
          :to="item.route"
          value="true"
          color="primary"
          nuxt
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon" />
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="(item.title)" />
          </v-list-item-content>
        </v-list-item>

        <v-list-group
          v-if="!item.header && item.children"
          :key="i"
          :prepend-icon="item.icon"
          color="primary"
          nuxt
        >
          <template v-slot:activator>
            <v-list-item-title v-text="item.title"/>
          </template>
          <template v-for="(children, indx) in item.children">
            <v-list-item
              :key="indx"
              :to="children.route"
              value="true"
              color="primary"
              nuxt
              class="pl-6"
            >
              <v-list-item-icon>
                <v-icon v-text="children.icon"/>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="(children.title)"/>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-list-group>
      </template>
    </v-list>
  </div>
</template>

<script>

import { items } from '~/configs/menuItems'
export default {
  name: 'NavMenu',
  data () {
    return {
      name: 'Меню',
      items: items
    }
  },
  computed: {

  },
  methods: {
  }
}
</script>
