import React from "react"
import { Timeline, Icon, Layout, Breadcrumb } from "antd"
import { Link } from "react-router-dom"
import moment from "moment"
import request from "src/resources/request"

import Header from "src/components/app/Header"

import "./style.scss"

const DONE_DOT = <Icon type="check-circle" style={{ fontSize: "16px" }} />

const STATUS_COLOR = {
	Running: "blue",
	Success: "green",
	Log: "yellow",
	Error: "red"
}

const ICON_TYPE = {
	Running: "clock-circle-o",
	Success: "check-circle-o"
}

const TYPE_COLOR = {
	Running: "#1890ff",
	Success: "#52c41a"
}

module.exports = class Task extends React.Component {
	state = {
		data: [],
		loading: false
	}

	componentDidMount = () => this.fetchData()

	fetchData = () => {
		this.setState({
			loading: true
		})
		request.get(`tasks/${this.props.id}`, {}, data => {
			// const data = this.state.data.concat(DEFAULT_DATA)
			this.setState(
				{
					data,
					loading: false
				},
				() => window.dispatchEvent(new Event("resize"))
			)
		})
	}

	renderStep = step => (
		<Timeline.Item
			key={step.id}
			dot={step.status === "done" ? DONE_DOT : null}
			color={STATUS_COLOR[step.status]}
		>
			<div styleName="step">
				<div styleName="desc">{step.name}</div> <div styleName="desc">{step.context}</div>{" "}
				<div styleName="date">{moment(step.creationDate).format("DD/MM/YY, HH:mm:ss")}</div>
			</div>
		</Timeline.Item>
	)

	renderExec = (exec, index, execs) => {
		const lastStep = exec.steps[exec.steps.length - 1] || { status: "Running" }
		return (
			<div styleName="exec" key={exec.id}>
				<div styleName="status">
					<Icon type={ICON_TYPE[lastStep.status]} style={{ color: TYPE_COLOR[lastStep.status] }} />
					<div styleName="number">{`Execution ${execs.length - index}`}</div>
					<div styleName="date">{moment(exec.creationDate).format("DD/MM/YY, HH:mm:ss")}</div>
				</div>
				<Timeline
					pending={lastStep.status === "Success" ? false : <div styleName="bold">Recording...</div>}
				>
					{exec.steps.map(this.renderStep)}
				</Timeline>
			</div>
		)
	}

	renderExecList = () => {
		const { data } = this.state
		if (data.executions && data.executions.length > 0) {
			return (
				<div styleName="list" key="list">
					{data.executions.map(this.renderExec)}
				</div>
			)
		}
		return null
	}

	render() {
		const { data } = this.state
		return (
			<Layout>
				<Header />
				<Layout.Content styleName="content">
					<div styleName="content-header">
						<div styleName="content-title">Execution List for Task {data.task_id}</div>
						<Breadcrumb>
							<Breadcrumb.Item>
								<Link to="/tasks">Tasks</Link>
							</Breadcrumb.Item>
							<Breadcrumb.Item>
								<Link to={`/task/${this.props.id}`}>Task {data.task_id}</Link>
							</Breadcrumb.Item>
						</Breadcrumb>
					</div>

					{this.renderExecList()}
				</Layout.Content>
			</Layout>
		)
	}
}
