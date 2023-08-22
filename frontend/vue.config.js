const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

// CORS Setting
module.exports = {
  devServer: {     
      port: 8080, //设置本地默认端口 (optional)
      proxy: {                 
          '/api': {              // add an interceptor:   '/'+拦截器名字，名字可以自己定
              target: 'http://localhost:9090',     // target address to change the origin
              changeOrigin: true,              //set to be the same origin
              pathRewrite: {                  
                '/api': ''                     //选择忽略拦截器里面的单词
            }
          }
      }
  }
}