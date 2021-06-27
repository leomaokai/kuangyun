<template>
	<view class="content">
		<view class="loginImage">
			<image></image>
		</view>
		<form @submit="loginSubmit">
			<view class="formItem">
				<view class="formIcon">
					<view class="iconfont icon-yonghuming" style="font-size: 50rpx;"></view>
				</view>
				<input class="formInput" type="number" v-model="username" placeholder="用户名为学号" maxlength="8" />
			</view>
			<view class="formItem">
				<view class="formIcon">
					<view class="iconfont icon-mima" style="font-size: 50rpx;"></view>
				</view>
				<input class="formInput" type="text" password="true" v-model="password" placeholder="请输入密码" />
			</view>
			<view>
				<button form-type="submit" class="loginBtn">Sign In</button>
			</view>
		</form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username: "",
				password: ""
			}
		},
		methods: {
			loginSubmit() {
				uni.request({
					url:"http://localhost:8888/user/login",
					method: "POST",
					data: {
						username: this.username,
						password: this.password
					},
					success: (res) => {
						if (res.data.code === 200200) {
							console.log(res.data.obj.token);
							uni.setStorage({
								key:'token',
								data:res.data.obj.token
							});
							this.password="";
							uni.reLaunch({
								url: "../home/home",
							})
						}
					},

				})
			}
		}
	}
</script>

<style>
	.content {}

	.loginBtn {
		height: 100rpx;
		width: 375rpx;
		margin: 0 auto;
		margin-top: 50rpx;
		background: #007AFF;
		border-radius: 50rpx;
		color: white;
		line-height: 100rpx;
		font-size: 40rpx;
	}

	.formInput {
		width: 70%;
		display: inline-block;
		text-align: left;
		margin-left: 40rpx;
		font-size: 50rpx;
	}

	.formIcon {
		display: inline-block;
		font-size: 50rpx;
		position: relative;
		top: -20rpx;
	}

	.formItem {
		text-align: center;
		height: 100rpx;
	}
</style>
