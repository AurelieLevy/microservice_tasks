module.exports = {
	plugins: [
		require("autoprefixer")({
			browsers: [
				"> 1%",
				"last 2 versions",
				"not Explorer <= 10",
				"not ExplorerMobile <= 11",
				"Firefox ESR",
				"iOS >= 7",
				"Safari >= 8"
			]
		}),
		require("postcss-flexbugs-fixes")
	]
}
