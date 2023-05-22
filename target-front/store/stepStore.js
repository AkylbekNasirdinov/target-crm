import Vue from "vue";
import * as stepService from '/service/stepService'

function _initialState() {
  return {
    steps : []
  }
}

export const state = () => (_initialState())

export const getters = {
  getSteps(state) {
    return state.steps;
  },
}

export const actions = {
  fetchSteps({commit}, processId) {
    stepService.fetchProcessSteps(processId)
      .then(response => commit('SET_UNIVERSAL', {key : 'steps',  payload : response.data}))
      .catch(reason => {
        commit('SET_UNIVERSAL', {key : 'steps',  payload : []})
        console.log(reason)
      })
  }
}

export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
}
