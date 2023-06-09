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
    return sortSteps(state.steps);
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
  },
  changeStepOrder({commit}, newStepOrder) {
    commit('CHANGE_ORDER', newStepOrder)
  }
}

const sortSteps = (steps) => {
  let sortedSteps = []
  sortedSteps.push(_.find(steps, (step) => {return step.isInitialStep}))
  for (let i=0; i < steps.length - 1; i++) {
    let nextStep = _.find(steps, (step) => {return  sortedSteps[i].nextStep && step.id===sortedSteps[i].nextStep.id})
    sortedSteps.push(nextStep)
  }
  return sortedSteps
}
export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
  PUT_PARAM (state, payload) {
    state.params.set(payload.key, payload.value)
  },
  CHANGE_ORDER(state, order) {
    let newSteps = []
    let firstStep = _.find(state.steps, (step) => {return step.isInitialStep})
    newSteps.push(firstStep)
    for (let i = 1; i < order.length; i++) {
      let nextStep = _.find(state.steps, (step) => {return  step.id === order[i]})
      newSteps[newSteps.length -1].nextStep = nextStep
      newSteps.push(nextStep)
    }
    newSteps[newSteps.length - 1].nextStep = null
    Vue.set(state, 'steps', newSteps)
  }
}
