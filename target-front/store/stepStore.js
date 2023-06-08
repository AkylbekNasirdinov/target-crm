import Vue from "vue";
import * as stepService from '/service/stepService'
import _ from "lodash";

function _initialState() {
  return {
    steps : [],
    params: new Map()
  }
}

export const state = () => (_initialState())

export const getters = {
  getSteps(state) {
    return state.steps;
  },
  getStepParam(state, stepId) {
    return state.params.get(stepId)
  }
}

export const actions = {
  fetchSteps({commit}, processId) {
    stepService.fetchProcessSteps(processId)
      .then(response => {
        commit('SET_UNIVERSAL', {key : 'steps',  payload : response.data})
      })
      .catch(reason => {
        commit('SET_UNIVERSAL', {key : 'steps',  payload : []})
        console.log( reason)
      })
  },
  fetchStepParams({commit}, stepId) {
    stepService.fetchStepParams(stepId)
      .then(response => {
        commit('PUT_PARAM', {key: stepId, value: response.data})
      })
      .catch(reason => {
        console.log( reason)
      })
  }
}

const sortSteps = (steps) => {
  let sortedSteps = []
  sortedSteps.push(_.find(steps, (step) => {return step.isInitialStep}))
  for (let i in steps.length - 1) {
    sortedSteps.push(sortedSteps[i].nextStep)
  }
  return sortedSteps
}
export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
  PUT_PARAM (state, payload) {
    state.params.set(payload.key, payload.value)
  }
}
