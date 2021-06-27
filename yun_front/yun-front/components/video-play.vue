<template>
	<view class="videoPlayer">
		<video 
		id="myvideoId" 
		class="myvideo" 
		:loop="false" 
		:src="video.url" 
		controls></video>
	</view>
</template>

<script>
	export default {
		name: "video-play",
		props:['video'],
		data() {
			return {
				videoContext:{},
			};
		},
		methods:{
			pauseVideo(){
				this.videoContext.pause();
				console.log("暂停")
			}
		},
		mounted() {
			this.videoContext = uni.createVideoContext("myvideoId",this);
		}
	}
</script>

<style>
	.videoPlayer {
		height: 100%;
		width: 100%;
	}

	.myvideo {
		height: 70%;
		width: 100%;
	}
</style>
