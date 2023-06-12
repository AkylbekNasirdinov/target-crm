import apiService from "../utils/apiClient";

const ROOT_URL = '/step/params'


export const fetchStepParams = (stepId) => {
  return apiService.get(ROOT_URL, {
    params: {
      id: stepId
    }
  })
}

export const fetchCategories = () => {
  return apiService.get('/categories')
}
