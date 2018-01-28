import config from "config"
import "whatwg-fetch"
import "babel-polyfill"

require("es6-promise").polyfill()

const get = (url, headers, callback) => {
	fetch(config.endpoints.api + url, {
		method: "GET",
		headers
	})
		.then(response => response.text())
		.then(responseText => {
			callback(JSON.parse(responseText))
		})
		.catch(error => console.log(error))
}

const post = (url, body, headers, callback) => {
	fetch(config.endpoints.api + url, {
		method: "POST",
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json",
			...headers
		},
		body: JSON.stringify(body)
	})
		.then(response => response.text())
		.then(responseText => {
			callback(JSON.parse(responseText))
		})
		.catch(error => console.log(error))
}

const put = (url, body, headers, callback) => {
	fetch(config.endpoints.api + url, {
		method: "PUT",
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json",
			...headers
		},
		body: JSON.stringify(body)
	})
		.then(response => response.text())
		.then(responseText => {
			callback(JSON.parse(responseText))
		})
		.catch(error => console.log(error))
}

const remove = (url, headers, callback) => {
	fetch(config.endpoints.api + url, {
		method: "DELETE",
		headers
	})
		.then(response => response.text())
		.then(responseText => {
			callback(JSON.parse(responseText))
		})
		.catch(error => console.log(error))
}

module.exports = {
	get,
	post,
	put,
	remove
}
