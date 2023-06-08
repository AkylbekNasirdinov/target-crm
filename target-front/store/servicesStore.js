import Vue from "vue";
import * as servicesService from '/service/servicesService'

function _initialState() {
  return {
    services : []
  }
}

export const state = () => (_initialState())

export const getters = {
  getServices(state) {
    return state.services;
  },
}

export const actions = {
  fetchAllServices({commit}) {
      servicesService.fetchAllService()
        .then(response => commit('SET_UNIVERSAL', {key : 'services',  payload : response.data}))
        .catch(reason => {
          commit('SET_UNIVERSAL', {key : 'services',  payload : []})
          console.log(reason)
        })
  }
}

export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
}
