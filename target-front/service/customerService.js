import apiService from "../utils/apiClient";

const ROOT_URL = '/customers'

export const fetchAllCustomers = () => {
  let url = ROOT_URL + '/get-all'
  return apiService.get(url)
}
