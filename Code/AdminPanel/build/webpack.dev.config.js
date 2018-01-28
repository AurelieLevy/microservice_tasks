const path = require("path")
const webpack = require("webpack")
const config = require("config")
const fs = require("fs")

const SOURCE_DIR = path.resolve(__dirname, "../src")
const PUBLIC_DIR = path.resolve(__dirname, "../app")
const ROOT_DIR = path.resolve(__dirname, "../")
const THEME = require(path.resolve(__dirname, "./theme.js"))()

const CSS_MODULES_HASH = "[local]__[hash:base64:7]"

// This will take the config based on the current NODE_ENV and save it to 'config/client_config.js'
// The webpack alias below will then build that file into the client build.
const CLIENT_CONFIG = `module.exports = ${JSON.stringify(config)}`
fs.writeFileSync(path.resolve(__dirname, "../config/client.js"), CLIENT_CONFIG)

module.exports = {
	context: SOURCE_DIR,
	resolve: {
		alias: {
			config: path.resolve(__dirname, "../config/client.js")
		},
		modules: ["node_modules"]
	},
	entry: {
		app: `${SOURCE_DIR}/components/app`
	},
	output: {
		path: PUBLIC_DIR,
		publicPath: "/",
		filename: "js/[name].bundle.js"
	},
	devtool: "eval", // 'cheap-module-eval-source-map',
	module: {
		rules: [
			{
				// compile CSS modules
				test: /\.scss$/,
				include: SOURCE_DIR,
				use: [
					{
						loader: "style-loader",
						options: {
							sourceMap: true
						}
					},
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
											src: SOURCE_DIR,
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
			},
			{
				test: /\.less$/,
				include: [/node_modules\/.*antd/],
				use: [
					{
						loader: "style-loader"
					},
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
			}
		]
	},
	plugins: [
		new webpack.DefinePlugin({
			"process.env.NODE_ENV": '"dev"'
		})
		// new require('webpack-bundle-analyzer').BundleAnalyzerPlugin()
	]
}
