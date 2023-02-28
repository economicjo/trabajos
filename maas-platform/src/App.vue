<template>
  <router-view :key="preferenceStore.reRenderKey" />
  <AppConfig />
</template>

<script setup lang="ts">
import { usePreferenceStore, useToastStore } from '~/stores'

// https://github.com/vueuse/head
// you can use this to manipulate the document head in any components
// comes in handy to display current route in browser tab
useHead({
  title: '21finance',
  meta: [
    { name: 'description', content: '21f starter template' },
  ],
})

// https://primefaces.org/primevue/toast
// we need to init this once in the main app
useToastStore()

const preferenceStore = usePreferenceStore()
</script>

<style lang="scss">
// import global styles
@import "./App.scss";
</style>
