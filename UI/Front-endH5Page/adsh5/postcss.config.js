const path = require('path');
module.exports = ({ file }) => {
  //const designWidth = file.dirname.includes(path.join('node_modules', 'vant')) ? 375 : 750;
 const designWidth = 750;
 //750 UI设计稿的宽度
  return {
    plugins: {
      autoprefixer: {},
      "postcss-px-to-viewport": {
        unitToConvert: "px",
        viewportWidth: designWidth,
        unitPrecision: 6,
        propList: ["*"],
        viewportUnit: "vw",
        fontViewportUnit: "vw",
        selectorBlackList: [],
        minPixelValue: 1,
        mediaQuery: true,
        exclude: [],
        landscape: false
      }
    }
  }
}