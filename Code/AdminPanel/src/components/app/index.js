import React from "react"
import { render } from "react-dom"
import { Route, BrowserRouter, Redirect, Switch } from "react-router-dom"
import { LocaleProvider } from "antd"
import frFR from "antd/lib/locale-provider/fr_FR"
import ScrollToTop from "src/components/utils/ScrollToTop"

import Tasks from "./Tasks"
import Task from "./Task"

import "./style.scss"

const renderTask = ({ match }) => <Task key={match.params.taskId} id={match.params.taskId} />

const App = () => (
	<BrowserRouter>
		<ScrollToTop>
			<LocaleProvider locale={frFR}>
				<Switch>
					<Redirect from="/" exact to="/tasks" />
					<Route path="/tasks" component={Tasks} />
					<Route path="/task/:taskId" component={renderTask} />
				</Switch>
			</LocaleProvider>
		</ScrollToTop>
	</BrowserRouter>
)

render(<App />, document.getElementById("react-app"))
