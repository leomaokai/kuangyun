<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@font-face {
	  font-family: "iconfont"; /* Project id 2626388 */
	  src: url('./static/iconfont/iconfont.woff2?t=1624353073753') format('woff2'),
	       url('./static/iconfont/iconfont.woff?t=1624353073753') format('woff'),
	       url('./static/iconfont/iconfont.ttf?t=1624353073753') format('truetype');
	}
	
	.iconfont {
	  font-family: "iconfont" !important;
	  font-size: 16px;
	  font-style: normal;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	}
	
	.icon-yonghuming:before {
	  content: "\e801";
	}
	
	.icon-bofang:before {
	  content: "\e66a";
	}
	
	.icon-dakaiopen127:before {
	  content: "\e665";
	}
	
	.icon-mima:before {
	  content: "\e65e";
	}
	
	.icon-xiazai:before {
	  content: "\e61b";
	}
	
	.icon-shangchuan:before {
	  content: "\e64a";
	}
	
	.icon-suspend_icon:before {
	  content: "\e668";
	}
	
	.icon-yun:before {
	  content: "\e60b";
	}
	
	.icon-iconzhengli_tiaozhuan:before {
	  content: "\e648";
	}
	
	.icon-denglu:before {
	  content: "\e6bd";
	}
	
	page{
		height: 100%;
	}
</style>
