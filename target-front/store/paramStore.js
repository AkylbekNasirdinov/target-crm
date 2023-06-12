import Vue from "vue";
import * as parameterService from "../service/parameterService";

function _initialState() {
  return {
    categories: [],
    params: new Map(),
    busy: false
  }
}

export const state = () => (_initialState())

export const getters = {
  getStepParam(state, stepId) {
    return state.params.get(stepId)
  },
  getCategories(state) {
    return state.categories
  }
}

export const actions = {
  fetchStepParams({commit}, stepId) {
    commit('SET_UNIVERSAL', {key: 'busy', value: true})
    parameterService.fetchStepParams(stepId)
      .then(response => {
        commit('PUT_PARAM', {key: stepId, value: response.data})
        commit('SET_UNIVERSAL', {key: 'busy', value: false})
      })
      .catch(reason => {
        commit('PUT_PARAM', {key: stepId, value: null})
        commit('SET_UNIVERSAL', {key: 'busy', value: false})
        console.log( reason)
      })
  },
  fetchCategories({commit}) {
    commit('SET_UNIVERSAL', {key: 'busy', value: true})
    parameterService.fetchCategories()
      .then(response => {
        commit('SET_UNIVERSAL', {key: 'categories', value: response.data})
        commit('SET_UNIVERSAL', {key: 'busy', value: false})
      })
      .catch(reason => {
        commit('SET_UNIVERSAL', {key: 'categories ', value: []})
        commit('SET_UNIVERSAL', {key: 'busy', value: false})
        console.log( reason)
      })
  },
}

export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.value)
  },
  PUT_PARAM (state, payload) {
    state.params.set(payload.key, payload.value)
  },
}
