# Admin Panel

### Installation

*This panels requires the task api to be available on localhost:8080
If you run it on a different port/address you need to change the configuration file in the folder `config`*

To run it individualy in a docker
first build it

`docker build -t admin-panel .`

then start it

`docker run -d -p 3000:3000 admin-panel`

*The default port is 3000*

### Dev

If you want to do some change on this admin panel run the webpack watcher to auto bundle your js/scss files using the command line `yarn run webpack`

If you want to change the index.html page change the `index.pug` file in the folder `src/views` then start the pug wather using the command line `yarn run pug`

Once you're done with your changes, use the command line `yarn run build` to build you project, the resulting js bundle and css files will be placed in the folder `app`

