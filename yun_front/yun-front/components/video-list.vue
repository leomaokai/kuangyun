<template>
	<view class="videoList">
		<view class="swiper-box">
			<swiper class="myswiper" :vertical="true" 
			@change="change"
			:current="currentIndex">
				<swiper-item v-for="item of list" :key="item.id">
					<view class="myswiper-item">
						<video-paly :video="item" ref="player"></video-paly>
					</view>
				</swiper-item>
			</swiper>
		</view>
		<view class="tab">
			<view class="tab-box">
				<view class="iconfont icon-shangchuan icon-box" 
				@click="uploadVideo()"></view>
			</view>
			<view class="tab-box">
				<view class="iconfont icon-xiazai icon-box" 
				@click="downloadVideo()"></view>
			</view>
		</view>
		<view class="phone">
			<input class="number" 
			type="text" 
			placeholder="输入目标电话" 
			v-model="tempPhoneNumber"/>
			<view class="iconfont icon-iconzhengli_tiaozhuan call" 
			@click="getPhone()"></view>
		</view>
	</view>
</template>

<script>
	import videoPaly from "./video-play.vue"
	export default {
		name: "video-list",
		components: {
			videoPaly
		},
		data() {
			return {
				list: [],
				currentVideo:{},
				currentIndex:0,
				tempPhoneNumber:"",
			};
		},
		methods: {
			async listVideo() {
				await this.getVideo();
			},
			getVideo: function() {
				return new Promise((resolve, reject) => {
					uni.request({
						url: "http://localhost:8888/file/listVideo",
						method: "GET",
						header: {
							"Authorization": "Cumter" + uni.getStorageSync('token')
						},
						success: (res) => {
							this.list = res.data;
							if(this.currentIndex===0) {
								this.currentVideo=this.list[0];
							};
							// console.log(this.list);
							resolve("suc");
							if(res.statusCode === 500) {
								console.log(res.statusCode);
								this.$emit('return-login');
							}
						},
						fail: (err) => {
							reject("err");
						}
					})
				});
			},
			change(res) {
				console.log(res);
				this.currentVideo=this.list[res.detail.current];
				// this.currentUrl
				console.log("currentIndex:"+this.currentIndex);
				console.log("res.detail.current:"+res.detail.current);
				if (this.currentIndex!=res.detail.current) {
					// 父组件调用子组件的方法
					console.log("currentIndex:"+this.currentIndex);
					this.$refs.player[this.currentIndex].pauseVideo();
					this.currentIndex=res.detail.current;
					console.log("res.detail.current:"+res.detail.current);
				}
			},
			uploadVideo() {
				uni.chooseVideo({
					count: 1,
					sourceType: ['album'],
					success: (chooseFile) => {
						console.log(chooseFile);
						uni.uploadFile({
							method: "POST",
							filePath: chooseFile.tempFilePath,
							name: "videoFile",
							header: {
								"Authorization": "Cumter" + uni.getStorageSync('token')
							},
							success: () => {
								console.log("success");
								this.listVideo();
							},
						})
					}
				});
			
			},
			downloadVideo() {
				// console.log("hello"+this.currentVideo.url);
				uni.downloadFile({
					url:this.currentVideo.url,
				})
			},
			getPhone(){
				uni.makePhoneCall({
					phoneNumber:this.tempPhoneNumber,
				})
			}
		},
		created() {
			this.listVideo();
		},
		mounted() {
			console.log(this.list);
		}
	}
</script>

<style>
	.videoList {
		height: 100%;
		width: 100%;
	}

	.swiper-box {
		height: 100%;
		width: 100%;
	}

	.myswiper {
		height: 100%;
		width: 100%;
		/* background-color: ; */
	}

	.myswiper-item {
		height: 100%;
		width: 100%;
	}

	.myvideo {
		height: 100%;
		width: 100%;
	}
	
	.tab {
		height: 50px;
		width: 100%;
		position: fixed;
		bottom: 180rpx;
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
	.phone{
		position: fixed;
		bottom: 50rpx;
		left: 0;
	}
	.number{
		width: 400rpx;
		display: inline-block;
		font-size: 50rpx;
		margin-left: 80rpx;
	}
	.call{
		display: inline-block;
		font-size: 80rpx;
		margin-left: 20rpx;
	}
</style>
