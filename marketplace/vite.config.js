/// <reference types="vitest" />

import path from 'path'
import { defineConfig, loadEnv } from 'vite'
import Vue from '@vitejs/plugin-vue'
import Pages from 'vite-plugin-pages'
import Layouts from 'vite-plugin-vue-layouts'
import Components from 'unplugin-vue-components/vite'
import AutoImport from 'unplugin-auto-import/vite'
import VueI18n from '@intlify/vite-plugin-vue-i18n'

export default ({ mode }) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) }

  return defineConfig({
    server: {
      port: process.env.VITE_PORT,
      proxy: {
        '/api': {
          target: process.env.VITE_API_URL,
          changeOrigin: true,
          ws: true,
          rewrite: path => path.replace('/api/v1', ''),
        },
      },
    },
    resolve: {
      alias: {
        '~/': `${path.resolve(__dirname, 'src')}/`,
      },
    },
    plugins: [
      // https://vuejs.org/guide/extras/reactivity-transform.html
      Vue({
        reactivityTransform: true,
      }),

      // https://github.com/hannoeru/vite-plugin-pages
      Pages(),

      // https://github.com/JohnCampionJr/vite-plugin-vue-layouts
      Layouts(),

      // https://github.com/antfu/unplugin-auto-import
      AutoImport({
        imports: [
          'vue',
          'vue-router',
          'vue/macros',
          'vue-i18n',
          '@vueuse/head',
          { },
        ],
        dts: 'src/auto-imports.d.ts',
      }),

      // https://github.com/antfu/unplugin-vue-components
      Components({
        dts: 'src/components.d.ts',
      }),

      // https://github.com/intlify/bundle-tools/tree/main/packages/vite-plugin-vue-i18n
      VueI18n({
        runtimeOnly: true,
        compositionOnly: true,
        include: [path.resolve(__dirname, 'locales/**')],
      }),
    ],

    // https://github.com/vitest-dev/vitest
    test: {
      environment: 'jsdom',
    },
  })
}
