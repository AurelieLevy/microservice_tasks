import React from "react"
import { Layout, Icon } from "antd"

import "./style.scss"

module.exports = () => (
	<Layout.Header styleName="header">
		<div styleName="header-content">
			<Icon type="api" />
			<div styleName="title">Disruptive Task Monitor</div>
		</div>
	</Layout.Header>
)
