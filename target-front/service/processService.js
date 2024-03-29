import apiService from "../utils/apiClient";

const ROOT_URL = '/processes'

export const fetchAllProcesses = () => {
  let url = ROOT_URL + '/all'
  return apiService.get(url)
}

export const fetchOffers = () => {
  let url = '/offers'
  return apiService.get(url)
}
