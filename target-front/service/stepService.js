import apiService from "../utils/apiClient";

const ROOT_URL = '/steps'

export const fetchProcessSteps = (processId) => {
  return apiService.get(ROOT_URL, {
    params: {
      processId: processId
    }
  })
}

export const fetchStepParams = (stepId) => {
  return apiService.get('/step/params', {
    params: {
      id: stepId
    }
  })
}
