import Vue from "vue";
import * as parameterService from "../service/parameterService";
import _ from "lodash";

function _initialState() {
  return {
    categories: [],
    params: [],
    busy: false
  }
}

export const state = () => (_initialState())

export const getters = {
  getStepParam: (state) => (stepId) =>{
    return _.find(state.params, (param) => {return param.key === stepId})?.value
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
        if (response.data!== "")
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
   state.params.push(payload)
  },
}
