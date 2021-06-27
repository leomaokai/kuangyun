<template>
	<view class="tab">
		<!-- 没用 -->
		<view class="tab-box">
			<view class="iconfont icon-shangchuan icon-box" @click="uploadVideo()"></view>
		</view>
		<view class="tab-box">
			<view class="iconfont icon-xiazai icon-box" @click="downloadVideo()"></view>
		</view>
	</view>
</template>

<script>
	export default {
		props: ['videoUrl'],
		name: "tab",
		data() {
			return {};
		},
		methods: {
			uploadVideo() {
				uni.chooseVideo({
					count: 1,
					sourceType: ['album'],
					success: (chooseFile) => {
						console.log(chooseFile);
						uni.uploadFile({
							url: "http://leomaokai.xyz:8888/file/uploadVideo",
							// url: "http://localhost:8888/file/uploadVideo",
							method: "POST",
							filePath: chooseFile.tempFilePath,
							name: "videoFile",
							header: {
								"Authorization": "Cumter" + uni.getStorageSync('token')
							},
							success: () => {
								console.log("success");
							},
						})
					}
				});

			},
			downloadVideo() {
				uni.downloadFile({
					url: "",
				})
			}
		}
	}
</script>

<style>
	.tab {
		height: 50px;
		width: 100%;
		position: fixed;
		bottom: 200rpx;
		left: 0;
	}

	.tab-box {
		float: left;
		width: 50%;
		color: #FFFFFF;
		text-align: center;
		height: 50px;
		line-height: 50px;
		font-size: 20px;
	}

	.icon-box {
		width: 60%;
		height: 30px;
		background-color: white;
		color: #333333;
		margin: 10px 20%;
		line-height: 30px;
		border-radius: 5px;
		font-size: 80rpx;
	}
</style>
