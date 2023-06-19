import * as customerService from '/service/customerService'
import Vue from "vue";

function _initialState() {
  return {
    customers : [],
  }
}

export const state = () => (_initialState())


export const getters = {
  getCustomers(state) {
    return state.customers;
  },
}

export const actions = {
  fetchCustomers({commit}) {
    customerService.fetchAllCustomers()
      .then(response => commit('SET_UNIVERSAL', {key : 'customers',  payload : response.data}))
      .catch(reason => {
        commit('SET_UNIVERSAL', {key : 'customers',  payload : []})
        console.log(reason)
      })
  },
}

export const mutations = {
  SET_UNIVERSAL (state, payload) {
    Vue.set(state, payload.key, payload.payload)
  },
}
