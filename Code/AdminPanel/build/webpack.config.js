const path = require("path")
const webpack = require("webpack")
const createVariants = require("parallel-webpack").createVariants
const config = require("config")
const fs = require("fs")
const ExtractTextPlugin = require("extract-text-webpack-plugin")
const CompressionPlugin = require("compression-webpack-plugin")

// This will take the config based on the current NODE_ENV and save it to 'config/client_config.js'
// The webpack alias below will then build that file into the client build.
const CLIENT_CONFIG = `module.exports = ${JSON.stringify(config)}`
fs.writeFileSync(path.resolve(__dirname, "../config/client.js"), CLIENT_CONFIG)

const CSS_MODULES_HASH = "[local]__[hash:base64:7]"

const SOURCE_DIR = path.resolve(__dirname, "../src")
const PUBLIC_DIR = path.resolve(__dirname, "../app")
const ROOT_DIR = path.resolve(__dirname, "../")
const THEME = require(path.resolve(__dirname, "./theme.js"))()

const variants = {
	moduleName: ["app"]
}

const generateConfig = options => ({
	context: SOURCE_DIR,
	resolve: {
		alias: {
			config: path.resolve(__dirname, "../config/client.js")
		},
		modules: ["node_modules", "./app"]
	},
	entry: `${SOURCE_DIR}/components/${options.moduleName}`,
	output: {
		path: PUBLIC_DIR,
		publicPath: "/",
		filename: `js/${options.moduleName}.bundle.js`
	},
	devtool: "source-map",
	module: {
		rules: [
			{
				test: /\.less$/,
				include: [/node_modules\/.*antd/],
				use: ExtractTextPlugin.extract({
					fallback: "style-loader",
					use: [
						{
							loader: "css-loader"
						},
						{
							loader: "less-loader",
							options: {
								sourceMap: true,
								modifyVars: THEME
							}
						}
					]
				})
			},
			{
				// compile CSS modules
				test: /\.scss$/,
				include: SOURCE_DIR,
				use: ExtractTextPlugin.extract({
					fallback: "style-loader",
					use: [
						{
							// translates CSS into CommonJS
							loader: "css-loader",
							options: {
								autoprefixer: false,
								sourceMap: true,
								importLoaders: 1,
								modules: true,
								localIdentName: CSS_MODULES_HASH
							}
						},
						{
							// prefix CSS
							loader: "postcss-loader"
						},
						{
							// compiles Sass to CSS
							loader: "sass-loader"
						}
					]
				})
			},
			{
				test: /.js?$/,
				exclude: /node_modules/,
				use: [
					{
						loader: "babel-loader",
						options: {
							cacheDirectory: false,
							presets: ["es2015", "react", "stage-2"],
							plugins: [
								[
									"module-alias",
									[
										{
											src: "./src",
											expose: "src"
										},
										{
											src: ROOT_DIR,
											expose: "root"
										}
									]
								],
								[
									"import",
									{
										libraryName: "antd",
										style: true
									}
								],
								[
									"react-css-modules",
									{
										context: SOURCE_DIR,
										generateScopedName: CSS_MODULES_HASH,
										filetypes: {
											".scss": {
												syntax: "postcss-scss"
											}
										}
									}
								]
							]
						}
					}
				]
			}
		]
	},
	plugins: [
		new webpack.DefinePlugin({
			"process.env.NODE_ENV": '"production"'
		}),
		new webpack.optimize.UglifyJsPlugin({
			mangle: true,
			compress: {
				warnings: false, // Suppress uglification warnings
				dead_code: true,
				unused: true,
				pure_getters: true,
				unsafe: true,
				unsafe_comps: true,
				screw_ie8: true
			},
			sourceMap: false,
			output: {
				comments: false
			},
			test: /\.js($|\?)/i,
			exclude: [/\.min\.js$/gi] // skip pre-minified libs
		}),
		new webpack.ContextReplacementPlugin(/moment[\/\\]locale$/, /fr/),
		new ExtractTextPlugin({
			filename: `css/${options.moduleName}.css`,
			ignoreOrder: true
		}),
		new CompressionPlugin({
			asset: "[path].gz",
			algorithm: "gzip",
			test: /\.js$/,
			threshold: 10240,
			minRatio: 0
		})
	]
})

module.exports = createVariants(variants, generateConfig)
