import apiService from "../utils/apiClient";

const ROOT_URL = '/purchases'

export const fetchAllPurchases = () => {
  let url = ROOT_URL + '/get-all'
  return apiService.get(url)
}
