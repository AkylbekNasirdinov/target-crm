import apiService from "../utils/apiClient";

const ROOT_URL = '/services'


export const fetchAllService = () => {
  return apiService.get(ROOT_URL)
}

export const saveService = (service) => {
  return apiService.post(service)
}
