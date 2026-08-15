import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8083/retroswap'
})

api.interceptors.request.use(config => {

  const token = localStorage.getItem('token')

  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }

  return config
}

)
api.interceptors.request.use(config => {

  const token = localStorage.getItem('token')

  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }

  return config
})
api.interceptors.response.use(
  (response) => response,

  (error) => {
    if (error.response?.status === 401) {

      // Don't redirect if login itself failed
      if (!error.config.url.includes("/login")) {
        localStorage.removeItem("token");
        window.location.href = "/login";
      }
    }

    return Promise.reject(error);
  }
);
export default api