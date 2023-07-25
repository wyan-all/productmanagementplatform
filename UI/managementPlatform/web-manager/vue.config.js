const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')

module.exports = {
  lintOnSave: false,
  css: {
    extract: false
  },
  
  publicPath: './',
  configureWebpack: {
    plugins: [
      require('unplugin-vue-components/webpack')({ /* options */ }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
  
    ],
  },
}
