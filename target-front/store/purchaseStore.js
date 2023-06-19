import * as purchaseService from '/service/purchaseService'
import Vue from "vue";

function _initialState() {
  return {
    purchases : [],
  }
}

export const state = () => (_initialState())


export const getters = {
  getPurchases(state) {
    return state.purchases;
  },
}

export const actions = {
  fetchPurchases({commit}) {
    purchaseService.fetchAllPurchases()
      .then(response => commit('SET_UNIVERSAL', {key : 'purchases',  payload : response.data}))
      .catch(reason => {
        commit('SET_UNIVERSAL', {key : 'purchases',  payload : []})
        console.log(reason)
      })
  },
}

export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
}
