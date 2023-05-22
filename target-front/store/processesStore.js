import * as processService from '/service/processService'
import Vue from "vue";

function _initialState() {
  return {
    processes : []
  }
}

export const state = () => (_initialState())


export const getters = {
  getProcesses(state) {
    return state.processes;
  },
}

export const actions = {
  fetchProcesses({commit}) {
    processService.fetchAllProcesses()
      .then(response => commit('SET_UNIVERSAL', {key : 'processes',  payload : response.data}))
      .catch(reason => {
        commit('SET_UNIVERSAL', {key : 'processes',  payload : []})
        console.log(reason)
      })
  }
}

export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
}
