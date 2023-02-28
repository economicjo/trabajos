# Development

New Developers: **read and understand this guide!**


## Tools

-   Node LTS/16.14.0 (recommended approach to install is via [nvm](https://github.com/nvm-sh/nvm))
-   [VSCode](https://code.visualstudio.com/)
-   Docker


## Frameworks and Technolgy

- [Vue.js](https://vuejs.org/) (v3) as frontend-framework
- [PrimeVue](https://www.primefaces.org/primevue/#/) for theming
- [DiamondVue](https://www.primefaces.org/layouts/diamond-vue) as theming boilerplate
- [Vite](https://vitejs.dev/) as build-framework


## Mentionable libs

Our template is based on the popular vue3 starter template [vitesse](https://github.com/antfu/vitesse) and [vitesse-lite](https://github.com/antfu/vitesse-lite). It adds various libs to increase productivity and defines a clear folder structure that helps understanding and traversing the project layout. It also comes preconfigured with sensible formatting and linting rules.


### [sass](https://github.com/sass/sass)

* CSS preprocessor scripting language 
* [DiamondVue](https://www.primefaces.org/layouts/diamond-vue) ships with this and we most likely have to use it


### [vue-i18n](https://github.com/intlify/vue-i18n-next)

* Vue interrnationalization plugin


### [pinia](https://github.com/vuejs/pinia)

* State Management and store for Vue
* Successor of Vuex with better typing support
* Although, we could manage states by sharing refs, we get some devtool support from this plugin which improves productivity
* It's more clearly defined when we share data between components


### [vite-plugin-pages](https://github.com/hannoeru/vite-plugin-pages)

* File system based routing for Vue
* Adds a logical structure to the pages folder and removes some configuration woes for the vue-router


### [vite-plugin-vue-layouts](https://github.com/JohnCampionJr/vite-plugin-vue-layouts)

* Improves the file system based routing by adding additional layouts
* Let's you easily define and switch between different application-layouts


### [unplugin-vue-components](https://github.com/antfu/unplugin-vue-components)

* On-demand components auto importing
* Makes them globally available - no need to import them again in each file
* Auto-Imports .vue components in the component folder
* Auto-Imports components from Primevue


### [unplugin-auto-import](https://github.com/antfu/unplugin-auto-import)

* Global imports for commonly used methods and helpers
* Gets rid of repeatedly imports ala `import { ref, .. } from 'vue'`


### [vitest](https://github.com/vitest-dev/vitest)

* unit test framework for vue & vite

**When adding new libs, please align with the technical lead.**


## Project Layout


```
.
├── locales // i18n localization files.
├── public  // all static assets.
├── src     // check below
│   ├── api
│   ├── components
│   ├── composables
│   ├── layouts
│   ├── modules
│   ├── pages
│   ├── router
│   ├── stores
│   ├── styles
│   ├── types
│   ├── utils
│   ├── App.vue             // duh
│   ├── auto-imports.d.ts   // unplugin-auto-import file
│   ├── components.d.ts     // unplugin-vue-components file
│   └── main.ts             // duh
├── test              // unit test folder
├── utils             // additional files like nginx config
├── index.html        // app entrypoint
└── vite.config.js    // vite config
```

`src/api` - fetch/axios implementations. might be a good idea to generate this layer.

`src/components` - vue components

`src/composables` - vue compositions and service layer

`src/layouts` - define app layouts here.

`src/modules` - all files here are expected to export the install method. we use it to auto install additional modules for vue.

`src/pages` - the file based page routing happens here. file name and layout is important here. please check [vite-plugin-pages](https://github.com/hannoeru/vite-plugin-pages) for additional information.

`src/router` - vue router configuration and global navigation guards.

`src/stores` - pinia store for state management.

`src/styles` - location of the diamond scss layout files.

`src/types` - global typescript types.

`src/utils` - place all helper and util scripts here.

! Make sure to read the subfolder README if available.


## VSCode Setup

Please check and use the `.vscode/launch.template.json` and `.vscode/settings.template.json` as a starting point and install the recommendet plugins from `.vscode/extensions.json`.

These configs set up up to format on safe and through the `Format Document` command via eslint. The format configuration is already aligned with eslint.


## Naming conventions

We follow the [Google TypeScript Style Guide](https://google.github.io/styleguide/tsguide.html)


### Files

For files, follow the following guidelines

```
VueComponents.vue for src/components/*

useFoo.ts for src/composables/*

lowercase.vue for src/layouts/*

dashed-lowercase.vue for src/pages/*

layout-colours.scss for src/layouts/*

typescript-files.ts for other typescript files
```


## General vue.js conventions and guidelines

* use [script setup](https://vuejs.org/api/sfc-script-setup.html)

* use [reactivity-transform](https://vuejs.org/guide/extras/reactivity-transform.html)

  ```
  ref -> $ref
  computed -> $computed
  shallowRef -> $shallowRef
  customRef -> $customRef
  toRef -> $toRef
  ```

* share data between components via stores


Also check out the official [Style Guide](https://vuejs.org/style-guide/)

## DiamondVue Adaptions

We have adapted the provided diamond-vue template in certain aspects to fit our needs. The following is a summary of the changes we made.

* switched the build system from vue-cli to vite
* converted javascript to typescript
* removed all css files and included the scss files instead because vite supports this
* moved App.vue to layouts/default.vue
* moved the other App*.vue to components/layout
* replaced router.js and special routing in main.js with file based routing plugin
* removed some dependencies until needed (@fullcalender, chart.js, prismjs)
* removed all other components and files that are not needed for the default layout
* kept login and error pages


## Open Points

### E2E Testing

* We could use [cypress](https://www.cypress.io/)
