/* eslint-disable no-alert  */
import 'primeflex/primeflex.scss'
import 'primeicons/primeicons.css'
import 'primevue/resources/primevue.min.css'

import { createApp } from 'vue'

import App from './App.vue'
import router from '~/router'

const app = createApp(App)

// install all modules under `modules/`
const modules = Object.values(import.meta.globEager('./modules/*.ts')).map((i) => {
  if (i.install)
    return i.install(app)
  else
    return Promise.resolve()
})

// wait with mounting the app until all modules are installed
// add router after all modules are loaded, otherwise the routeguard will activate earlier
Promise.all(modules)
  .then(() => app.use(router))
  .then(() => app.mount('#app'))
  .catch(error => alert(`Developer warning:\n\n${error}`))
