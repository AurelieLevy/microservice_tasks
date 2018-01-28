import React from "react"
import { List, Badge, Button, Spin, Layout } from "antd"
import { Link } from "react-router-dom"
import Header from "src/components/app/Header"
import request from "src/resources/request"

import "./style.scss"

const STATUS = {
	Running: "processing",
	Error: "error",
	Log: "default",
	Success: "success"
}

module.exports = class Tasks extends React.Component {
	state = {
		data: [],
		loading: false,
		loadingMore: false,
		showLoadingMore: true
	}

	componentDidMount = () => this.fetchData()

	fetchData = () => {
		this.setState({
			loadingMore: true
		})
		request.get("tasks", {}, data => {
			// const data = this.state.data.concat(DEFAULT_DATA)
			this.setState(
				{
					data,
					loadingMore: false
				},
				() => window.dispatchEvent(new Event("resize"))
			)
		})
	}

	onLoadMore = () => this.fetchData()

	renderStatus = item => {
		const lastExec = item.executions[item.executions.length - 1]
		const lastStep =
			lastExec && lastExec.steps.length > 0
				? lastExec.steps[lastExec.steps.length - 1]
				: { status: "Running" }
		return <Badge status={STATUS[lastStep.status]} text={lastStep.status} styleName="badge" />
	}

	renderItem = item => (
		<List.Item
			styleName="list-item"
			key={item.id}
			actions={[<Link to={`/task/${item.id}`}>Details</Link>]}
		>
			<List.Item.Meta title={item.title} description={item.description} />
			{this.renderStatus(item)}
		</List.Item>
	)

	render() {
		const { data, loading, loadingMore, showLoadingMore } = this.state
		const loadMore = showLoadingMore ? (
			<div styleName="loadmore">
				{loadingMore && <Spin />}
				{!loadingMore && <Button onClick={this.onLoadMore}>Gimme more</Button>}
			</div>
		) : null

		return (
			<Layout>
				<Header />
				<Layout.Content styleName="content">
					<div styleName="title">{data.length} useless tasks</div>
					<List
						styleName="list"
						loading={loading}
						itemLayout="horizontal"
						loadMore={loadMore}
						dataSource={data}
						renderItem={this.renderItem}
					/>
				</Layout.Content>
			</Layout>
		)
	}
}
