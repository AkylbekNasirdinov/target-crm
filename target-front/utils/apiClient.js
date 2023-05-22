let client;

export const setClient = (newClient) => {
  client = newClient
}

const reqMethods = [
  'request', 'delete', 'get', 'head', 'options', // url, config
  'post', 'put', 'patch' // url, data, config
]

const apiService = {}

reqMethods.forEach((method) => {
  apiService[method] = function () {
    if (!client) { throw new Error('apiClient not installed') }
    return client[method].apply(null, arguments)
  }
})

apiService.main = function () {
  if (!client) { throw new Error('apiClient not installed') }
  return client.apply(null, arguments)
}

export default apiService
