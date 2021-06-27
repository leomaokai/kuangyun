<template>
	<view class="myhome">
		<video-list @return-login="returnLogin"></video-list>
	</view>
</template>

<script>
	import videoList from "../../components/video-list.vue"
	export default {
		components:{
			videoList
		},
		data() {
			return {
			}
		},
		methods: {
			returnLogin(){
				uni.reLaunch({
					url:"../index/index"
				})
			}
		},
		created() {
		}
	}
</script>

<style>
	.myhome{
		height: 100%;
		width: 100%;
	}
</style>
