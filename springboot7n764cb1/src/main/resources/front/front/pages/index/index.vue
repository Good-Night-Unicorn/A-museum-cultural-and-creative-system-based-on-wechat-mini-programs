<template>
	<view class="content">
		<view :style='{"width":"100%","flexWrap":"wrap","background":"rgba(220,247,255,1)","display":"flex","height":"auto"}'>
			<view class="list-swiper-4" :style='{"width":"100%","position":"relative","height":"400rpx"}' @touchstart="touchStart" @touchmove="touchMove" @touchend="touchEnd">
				<view :style='{"width":"100%","position":"absolute","top":"0%","left":"0%","background":"#fff","height":"400rpx"}' class="item animate__animated" :class="prevNumList4 == index  ? 'animate__backOutRight' : (numList4 == index  ? 'animate__backInLeft' : '')" v-for="(swiper,index) in swiperList" :key="index" v-if="numList4 == index || prevNumList4 == index">
					<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"400rpx"}' mode="aspectFill" :src="baseUrl+swiper.img" @tap="onSwiperTap(swiper)"></image>
					<view :style='{"padding":"8rpx 20rpx","margin":"-20rpx 0 0 0","transform":"translate3d(-50%, -50%, 0)","top":"50%","color":"#000","left":"50%","background":"rgba(255, 255, 255, 0.3)","display":"none","lineHeight":"1.5","fontSize":"40rpx","position":"absolute"}'>{{ swiper.title }}</view>
				</view>
				<view class="animate__navigation" :style='{"alignItems":"center","left":"0%","bottom":"0%","background":"rgba(0,0,0,.3)","display":"flex","width":"100%","position":"absolute","justifyContent":"center","height":"40rpx"}'>
					<block v-for="(swiper,index) in swiperList" :key="index">
						<text class="navigation-item" v-if="numList4 == index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#37E8DC","height":"16rpx"}'></text>
						<text class="navigation-item" v-if="numList4 != index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","opacity":"0.2","background":"#37E8DC","height":"16rpx"}'></text>
					</block>
				</view>
			</view>
			<!-- menu -->
			<view v-if="true" class="menu_view">
				<block v-for="(item,index1) in menuList" v-bind:key="item.roleName">
					<block v-if="index1==0" v-bind:key="index" v-for=" (menu,index) in item.frontMenu">
						<block v-bind:key="sort" v-for=" (child,sort) in menu.child">
							<block v-bind:key="sort2" v-for=" (button,sort2) in child.buttons">
								<view class="menu-item" v-if="button=='查看' && child.tableName!='yifahuodingdan' && child.tableName!='yituikuandingdan' &&child.tableName!='yiquxiaodingdan' && child.tableName!='weizhifudingdan' && child.tableName!='yizhifudingdan' && child.tableName!='yiwanchengdingdan' " @tap="onPageTap2(child.tableName)">
									<view class="iconarr" :class="child.appFrontIcon" :style="{'background':menuColor[index]}"></view>
									
									<view class="text">{{child.menu.split("列表")[0]}}</view>
								</view>
							</block>
						</block>
					</block>
				</block>
			</view>
			<!-- 关于我们 -->
			<view :style='{"padding":"20rpx 20rpx 40rpx","margin":"0","flexWrap":"wrap","background":"none","display":"flex","width":"100%","position":"relative","height":"auto","order":"3"}'>
				<view :style='{"color":"#333","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241213/aeecfd445ea74d09b1e040b942a5c733.png) center center/100% 100%","width":"100%","lineHeight":"172rpx","fontSize":"36rpx","fontWeight":"bold","height":"136rpx","order":"1"}'>{{aboutUsDetail.title}}</view>
				<view :style='{"padding":"20rpx 0","margin":"0","color":"#000","textAlign":"center","width":"100%","lineHeight":"1.5","fontSize":"32rpx","position":"relative","zIndex":"2","order":"2"}'>{{aboutUsDetail.subtitle}}</view>
				<view :style='{"padding":"0 40rpx","flexWrap":"wrap","display":"flex","width":"100%","position":"relative","height":"auto","order":"4","zIndex":"2"}'>
					<img :style='{"width":"calc(33% - 20rpx)","margin":"0 30rpx 0 0","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"160rpx"}' v-if="aboutUsDetail.picture1" :src="baseUrl+aboutUsDetail.picture1">
					<img :style='{"width":"calc(33% - 20rpx)","margin":"0 30rpx 0 0","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"160rpx"}' v-if="aboutUsDetail.picture2" :src="baseUrl+aboutUsDetail.picture2">
					<img :style='{"width":"calc(33% - 20rpx)","margin":"0","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"160rpx"}' v-if="aboutUsDetail.picture3" :src="baseUrl+aboutUsDetail.picture3">
				</view>
				<view :style='{"padding":"0 40rpx","margin":"0 0 20rpx 0","color":"#797979","width":"100%","lineHeight":"2","fontSize":"28rpx","position":"relative","zIndex":"2","order":"3"}' v-html="aboutUsDetail.content"></view>
				<view :style='{"border":"2rpx solid #19BFF0","padding":"0 30rpx","margin":"20rpx auto","borderRadius":"10rpx","textAlign":"center","background":"none","display":"block","width":"auto","lineHeight":"40rpx","position":"relative","order":"5","zIndex":"2"}' @tap="onDetailTap('aboutus',1)">
					<text :style='{"color":"#19BFF0","fontSize":"24rpx","lineHeight":"40rpx"}'>更多</text>
					<text class="icon iconfont icon-gengduo1" :style='{"color":"#19BFF0","fontSize":"24rpx","lineHeight":"40rpx"}'></text>
				</view>
				<view :style='{"top":"156rpx","left":"20rpx","background":"url(http://codegen.caihongy.cn/20241213/60072c86fe2a4528819f6c25c60695dd.png) center center/100% 100%","display":"block","width":"calc(100% - 40rpx)","position":"absolute","height":"calc(100% - 176rpx)","zIndex":"1"}' />
				<view :style='{"width":"50%","background":"url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat","display":"none","height":"160rpx"}' />
			</view>
			<!-- 系统简介 -->
			<view :style='{"padding":"20rpx 20rpx 40rpx","margin":"0","flexWrap":"wrap","background":"none","display":"flex","width":"100%","position":"relative","height":"auto","order":"6"}'>
				<view :style='{"color":"#333","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241213/aeecfd445ea74d09b1e040b942a5c733.png) center center/100% 100%","width":"100%","lineHeight":"172rpx","fontSize":"36rpx","fontWeight":"bold","height":"136rpx","order":"1"}'>{{systemIntroductionDetail.title}}</view>
				<view :style='{"padding":"20rpx 0","margin":"0","color":"#000","textAlign":"center","width":"100%","lineHeight":"1.5","fontSize":"32rpx","position":"relative","zIndex":"2","order":"2"}'>{{systemIntroductionDetail.subtitle}}</view>
				<view :style='{"padding":"0 40rpx","flexWrap":"wrap","display":"flex","width":"100%","position":"relative","height":"auto","order":"4","zIndex":"2"}'>
					<img :style='{"width":"calc(33% - 20rpx)","margin":"0 30rpx 0 0","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"160rpx"}' v-if="systemIntroductionDetail.picture1" :src="baseUrl+systemIntroductionDetail.picture1">
					<img :style='{"width":"calc(33% - 20rpx)","margin":"0 30rpx 0 0","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"160rpx"}' v-if="systemIntroductionDetail.picture2" :src="baseUrl+systemIntroductionDetail.picture2">
					<img :style='{"width":"calc(33% - 20rpx)","margin":"0","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"160rpx"}' v-if="systemIntroductionDetail.picture3" :src="baseUrl+systemIntroductionDetail.picture3">
				</view>
				<view :style='{"padding":"0 40rpx","margin":"0 0 20rpx 0","color":"#797979","width":"100%","lineHeight":"2","fontSize":"28rpx","position":"relative","zIndex":"2","order":"3"}' v-html="systemIntroductionDetail.content"></view>
				<view :style='{"border":"2rpx solid #19BFF0","padding":"0 30rpx","margin":"20rpx auto","borderRadius":"10rpx","textAlign":"center","background":"none","display":"block","width":"auto","lineHeight":"40rpx","position":"relative","order":"5","zIndex":"2"}' @tap="onDetailTap('systemintro',1)">
					<text :style='{"color":"#19BFF0","fontSize":"24rpx","lineHeight":"40rpx"}'>更多</text>
					<text class="icon iconfont icon-gengduo1" :style='{"color":"#19BFF0","fontSize":"24rpx","lineHeight":"40rpx"}'></text>
				</view>
				<view :style='{"top":"156rpx","left":"20rpx","background":"url(http://codegen.caihongy.cn/20241213/60072c86fe2a4528819f6c25c60695dd.png) center center/100% 100%","display":"block","width":"calc(100% - 40rpx)","position":"absolute","height":"calc(100% - 176rpx)","zIndex":"1"}' />
				<view :style='{"width":"50%","background":"url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat","display":"none","height":"160rpx"}' />
			</view>
			<!-- 商品推荐 -->
			<view class="listBox recommend" :style='{"width":"100%","padding":"20rpx","margin":"0","background":"none","order":"1"}'>
				<view class="title" :style='{"width":"100%","padding":"0 0","margin":"0","background":"none"}'>
					<view :style='{"padding":"0 0 0 60rpx","color":"#000","background":"url(http://codegen.caihongy.cn/20241213/cc4d3e7e15444fbc9d9fc1032b6a41f6.png) center center/100% 100% no-repeat","width":"340rpx","fontSize":"28rpx","lineHeight":"60rpx","fontWeight":"bold","height":"80rpx"}'>文创商品推荐</view>
				</view>
				<!-- 样式5 -->
				<view class="list-box style5">
					<swiper :style='{"width":"100%","borderRadius":"20rpx","background":"#fff","height":"480rpx"}' class="swiper" :indicator-dots='false' :autoplay='false' :circular='false' indicator-active-color='#000000' indicator-color='rgba(0, 0, 0, .3)' :duration='500' :interval='5000' :vertical='false'>
						<swiper-item :style='{"width":"100%","borderRadius":"20rpx","background":"#fff","height":"auto"}' @tap="onDetailTap('wenchuangshangpin',product.id)" v-for="(product,index) in wenchuangshangpinlist" :key="index">
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"300rpx"}' class="list-item-image" mode="aspectFill" v-if="product.tupian.substring(0,4)=='http'" :src="product.tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"300rpx"}' class="list-item-image" mode="aspectFill" v-else :src="product.tupian?baseUrl+product.tupian.split(',')[0]:''"></image>
							<view :style='{"width":"100%","padding":"10rpx 20rpx","flexWrap":"wrap","background":"#fff","display":"flex"}'>
								<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"60rpx","fontSize":"28rpx","color":"#000","order":"1"}'>产品名称:{{product.chanpinmingcheng}}</view>
								<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"60rpx","fontSize":"28rpx","color":"#000","order":"1"}'>产品分类:{{product.chanpinfenlei}}</view>
								<view :style='{"padding":"0 20rpx","order":"6"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"70rpx","fontSize":"24rpx","color":"#2DAA90"}'></text>
									<text :style='{"color":"#2DAA90","lineHeight":"70rpx","fontSize":"24rpx"}'>{{product.addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block","order":"2"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"70rpx","fontSize":"24rpx","color":"#EFEA4E"}'></text>
									<text :style='{"color":"#EFEA4E","lineHeight":"70rpx","fontSize":"24rpx"}'>{{product.thumbsupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block","order":"3"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"70rpx","fontSize":"24rpx","color":"#03A48D"}'></text>
									<text :style='{"color":"#03A48D","lineHeight":"70rpx","fontSize":"24rpx"}'>{{product.storeupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block","order":"4"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"70rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
									<text :style='{"color":"#37E8DC","lineHeight":"70rpx","fontSize":"24rpx"}'>{{product.clicknum}}</text>
								</view>
							</view>
						</swiper-item>
					</swiper>
				</view>
			</view>
			<!-- 商品推荐 -->
			
			<!-- 商品列表 -->
			<!-- 商品列表 -->
			<!-- 新闻资讯 -->
			<view class="listBox news" :style='{"width":"100%","padding":"20rpx","margin":"0","background":"none","order":"4"}'>
				<view class="title" :style='{"padding":"0 0","margin":"0","alignItems":"flex-start","background":"none","display":"flex","width":"100%","justifyContent":"space-between"}'>
					<view :style='{"padding":"0 0 0 60rpx","color":"#000","background":"url(http://codegen.caihongy.cn/20241213/cc4d3e7e15444fbc9d9fc1032b6a41f6.png) center center/100% 100% no-repeat","width":"340rpx","fontSize":"28rpx","lineHeight":"60rpx","fontWeight":"bold","height":"80rpx"}'>文创资讯</view>
					<view :style='{"border":"2rpx solid #19BFF0","padding":"0 20rpx","alignItems":"center","borderRadius":"10rpx","justifyContent":"center","display":"flex"}' @tap="onPageTap('news')">
					  <text :style='{"color":"#19BFF0","fontSize":"26rpx","lineHeight":"50rpx"}'>更多</text>
					  <text class="icon iconfont icon-gengduo1" :style='{"color":"#19BFF0","fontSize":"26rpx","lineHeight":"50rpx"}'></text>
					</view>
				</view>
				<!-- 样式2 -->
				<view class="list-box style2" :style='{"width":"100%","padding":"24rpx 0 0","height":"auto"}'>
					<view class="tabView" :style='{"width":"100%","margin":"0 0 20rpx","flexWrap":"wrap","justifyContent":"center","display":"flex"}'>
						<view class="tab" :class="newsIndex2==index?'tabActive':''" v-for="(item,index) in newsCateList2" :key="index" @click="newsTabClick2(index)">
							<text class="icon iconfont icon-zhangjie8" :style='{"margin":"0 4rpx 0 0","lineHeight":"68rpx","fontSize":"28rpx","color":"inherit"}'></text>
							<text :style='{"color":"inherit","lineHeight":"68rpx","fontSize":"28rpx"}'>{{item.typename}}</text>
						</view>
					</view>
					<view :style='{"width":"100%","background":"none"}'>
						<view @tap="onNewsDetailTap(item.id)" v-for="(item,index) in news" :key="index" class="list-item" :style='{"width":"100%","padding":"0","margin":"0 0 20rpx","borderRadius":"20rpx","background":"#fff","display":"flex"}'>
							<view :style='{"borderColor":"#37E8DC","alignItems":"center","color":"#fff","borderRadius":"20rpx 50% 50% 20rpx","background":"none","flexDirection":"column","borderWidth":"4rpx","display":"flex","width":"200rpx","fontSize":"28rpx","borderStyle":"solid","justifyContent":"center"}'>
								<view :style='{"fontSize":"40rpx","lineHeight":"1.5","color":"#37E8DC","fontWeight":"bold"}'>{{item.addtime.split(" ")[0].split("-")[2]}}</view>
								<view :style='{"color":"#37E8DC","fontSize":"30rpx","lineHeight":"1.5"}'>{{item.addtime.split(" ")[0].split("-")[0]}}-{{item.addtime.split(" ")[0].split("-")[1]}}</view>
							</view>
							<view class="list-item-body" :style='{"width":"calc(100% - 200rpx)","padding":"30rpx 20rpx","flexWrap":"wrap","flex":"1","display":"flex"}'>
								<view :style='{"padding":"0","margin":"0","whiteSpace":"nowrap","overflow":"hidden","color":"#000","width":"100%","lineHeight":"60rpx","fontSize":"30rpx","textOverflow":"ellipsis","order":"1"}' class="title">{{item.title}}</view>
								<view :style='{"padding":"0","whiteSpace":"nowrap","overflow":"hidden","color":"#AEAEAE","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","textOverflow":"ellipsis","order":"2"}' class="text">{{item.introduction}}</view>
								<view :style='{"width":"40%","padding":"0 20rpx 0 0","order":"6"}'>
									<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"50rpx","fontSize":"24rpx","color":"#6DB1DD"}'></text>
									<text :style='{"color":"#6DB1DD","lineHeight":"50rpx","fontSize":"24rpx"}'>{{item.name}}</text>
								</view>
								<view :style='{"width":"20%","padding":"0 20rpx 0 0","display":"inline-block","order":"3"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"50rpx","fontSize":"24rpx","color":"#19BFF0"}'></text>
									<text :style='{"color":"#19BFF0","lineHeight":"50rpx","fontSize":"24rpx"}'>{{item.thumbsupnum}}</text>
								</view>
								<view :style='{"width":"20%","padding":"0 20rpx 0 0","display":"inline-block","order":"4"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"50rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
									<text :style='{"color":"#37E8DC","lineHeight":"50rpx","fontSize":"24rpx"}'>{{item.storeupnum}}</text>
								</view>
								<view :style='{"width":"20%","padding":"0 20rpx 0 0","display":"inline-block","order":"5"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"50rpx","fontSize":"24rpx","color":"#15D1E8"}'></text>
									<text :style='{"color":"#15D1E8","lineHeight":"50rpx","fontSize":"24rpx"}'>{{item.clicknum}}</text>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 新闻资讯 -->
			<view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx"}'>
				<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
			</view>
		</view>
	</view>
</template>

<script>
    import menu from '@/utils/menu'
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				startX: 0,
				prevNumList4: '',
				numList4: 0,
				timerList4: null,
				flagList4: false,
				navigationActive: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#37E8DC","height":"16rpx"},
				navigationDefault: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","opacity":"0.2","background":"#37E8DC","height":"16rpx"},
				options2: {
					effect: 'flip',
					loop : true
				},
				options3: {
					effect: 'cube',
					loop : true,
					cubeEffect: {
						shadow: true,
						slideShadows: true,
						shadowOffset: 20,
						shadowScale: 0.94,
					}
				},
				rows: 2,
				column: 4,
				iconArr: [
					'cuIcon-same',
					'cuIcon-deliver',
					'cuIcon-evaluate',
					'cuIcon-shop',
					'cuIcon-ticket',
					'cuIcon-cascades',
					'cuIcon-discover',
					'cuIcon-question',
					'cuIcon-pic',
					'cuIcon-filter',
					'cuIcon-footprint',
					'cuIcon-pulldown',
					'cuIcon-pullup',
					'cuIcon-moreandroid',
					'cuIcon-refund',
					'cuIcon-qrcode',
					'cuIcon-remind',
					'cuIcon-profile',
					'cuIcon-home',
					'cuIcon-message',
					'cuIcon-link',
					'cuIcon-lock',
					'cuIcon-unlock',
					'cuIcon-vip',
					'cuIcon-weibo',
					'cuIcon-activity',
					'cuIcon-friendadd',
					'cuIcon-friendfamous',
					'cuIcon-friend',
					'cuIcon-goods',
					'cuIcon-selection'
				],
				role : '',
				aboutUsDetail: {},
				systemIntroductionDetail: {},
				menuList: [],
				swiperMenuList:[],
				user: {},
				tableName:'',
				menuColor: '#52B7BB,#3361FF,#9ED4D0,#FF8073,#6388FA,#58D3D3,#D63626,#37E8DC,#BBFFFA,#19BFF0'.split(','),

				//轮播
				swiperList: [],
				wenchuangshangpinlist: [],
				news: [],
				newsCateList2: [],
				newsIndex2: 0,
				scrollTop: 0,
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(){
			this.menuColor = this.menuColor.sort(()=> {
				return (0.5-Math.random());
			});
		},
		async onShow() {
			if (this.timerList4&&this.timerList4!=null) clearInterval(this.timerList4)
			this.swiperMenuList = []
			this.role = uni.getStorageSync("appRole");
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.user = res.data;
			this.tableName = table;
			let menus = menu.list();
			this.menuList = menus;
			this.menuList.forEach((item,key) => {
				if(key==0) {
					item.frontMenu.forEach((item2,key2) => {
						if(item2.child[0].buttons.indexOf("查看")>-1) {
							this.swiperMenuList.push(item2);
						}
					})
				}
			})
			// let res;
			// 轮播图
			let swiperList = []
			res = await this.$api.list('config', {
				page: 1,
				limit: 5
			});
			for (let item of res.data.list) {
				if (item.name.indexOf('picture') >= 0 && item.value && item.value!="" && item.value!=null ) {
					swiperList.push({
						img: item.value,
						title: item.name,
						url: item.url
					});
				}
			}
			if (swiperList) {
				this.swiperList = swiperList;
			}
			
			this.prevNumList4 = this.swiperList.length - 1
			this.timerList4 = setInterval(this.autoPlayList4, 3000)

			this.getAboutUs();
			this.getSystemIntroduction();
			if(uni.getStorageSync("appUserid")) {
				res = await this.$api.page('newstype', {page:1,limit:100});
			} else {
				res = await this.$api.list('newstype', {page:1,limit:100});
			}
			res.data.list.splice(0,0,{id:0,typename:'全部'})
			this.newsIndex2 = 0
			this.newsCateList2 = res.data.list
			// 推荐信息
			this.getRecommendList()
			this.getHomeList()
			this.getNewsList()
		},
		methods: {
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
			},
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			newsTabClick2(index){
				this.newsIndex2 = index
				this.getNewsList()
			},
			async getNewsList(){
				let res;
				let params = {
					page: 1,
					limit: 6,
					sort: 'id',
					order: 'desc',
				}
				if(this.newsIndex2){
					params.typename = this.newsCateList2[this.newsIndex2].typename
				}
				// 文创资讯
				res = await this.$api.list('news', params)
				this.news = res.data.list
			},
			homeTabClick2(index,name){
				this['home' + name + 'Index2'] = index
				this.getHomeList()
			},
			async getHomeList(){
				let res;
				let params;
			},
			recommendTabClick2(index,name){
				this[name + 'Index2'] = index
				this.getRecommendList()
			},
			async getRecommendList(){
				let res;
				let params;
				// 推荐信息
				params = {
					page: 1,
					limit: 6,
				}
				res = await this.$api.recommend('wenchuangshangpin', params);
				this.wenchuangshangpinlist = res.data.list
				

			},
			autoPlayList4() {
				this.prevNumList4 = this.numList4
			
				this.numList4++
				if (this.numList4 == this.swiperList.length) this.numList4 = 0
			},
			touchStart(event) {
				this.startX = event.touches[0].clientX
				
				clearInterval(this.timerList4)
				this.flagList4 = true
			},
			touchMove(event) {
				const currentX = event.touches[0].clientX;
				const deltaX = currentX - this.startX;
				
				if (deltaX > 50) {
					// 向右滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4++
						if (this.numList4 == this.swiperList.length) this.numList4 = 0
					}
					
				} else if (deltaX < -50) {
					// 向左滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4--
						if (this.numList4 < 0) this.numList4 = this.swiperList.length - 1
					}
				}
			},
			touchEnd() {
				this.startX = 0
				this.timerList4 = setInterval(this.autoPlayList4, 3000)
				this.flagList4 = false
			},
			//轮播图跳转
			onSwiperTap(e) {
				if(e.url) {
					if (e.url.indexOf('https') != -1) {
						// #ifdef MP-WEIXIN
						uni.navigateTo({
						    url: '../../common/linkOthers/linkOthers?url=' + encodeURIComponent(e.url),
						});
						return false
						// #endif
						window.open(e.url)
					} else {
						this.$utils.jump(e.url)
					}
				}
			},
			async getAboutUs() {
				let res = await this.$api.info('aboutus', 1)
				this.aboutUsDetail = res.data;
			},
			async getSystemIntroduction() {
				let res = await this.$api.info('systemintro', 1)
				this.systemIntroductionDetail = res.data;
			},
			// 新闻详情
			onNewsDetailTap(id) {
				this.$utils.jump(`../news-detail/news-detail?id=${id}`)
			},
			// 推荐列表点击详情
			onDetailTap(tableName, id) {
				this.$utils.jump(`../${tableName}/detail?id=${id}`)
			},
			onPageTap(tableName){
				uni.navigateTo({
					url: `../${tableName}/list`,
					fail: function(){
						uni.switchTab({
							url: `../${tableName}/list`
						});
					}
				});
				// this.$utils.jump(`../${tableName}/list`)
			},
			onPageTap2(index) {
				let url = '../' + index + '/list'
				if(index=='forum'){
					url = '../forum-index/forum-index'
				}
				uni.setStorageSync("useridTag",0);
				uni.navigateTo({
					url: url,
					fail: function() {
						uni.switchTab({
							url: url
						});
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.list-swiper-4 .animate__animated {
		--animate-delay: 300ms;
	}

	.news {
		.style2 {
			.tabView {
				.tab {
					border: 0px solid #13b400;
					border-radius: 10rpx;
					padding: 0 30rpx;
					margin: 0 1% 10rpx;
					clip-path: polygon(5% 0,5% 0%,0 100%,95% 100%,100% 0);
					color: #fff;
					background: #37E8DC;
					width: calc(100% / 3 - 2%);
				}
				.tabActive {
					border: 0px solid #13b400;
					border-radius: 10rpx;
					padding: 0 30rpx;
					margin: 0 1% 10rpx;
					clip-path: polygon(5% 0,5% 0%,0 100%,95% 100%,100% 0);
					color: #fff;
					background: #19BFF0;
					width: calc(100% / 3 - 2%);
				}
			}
		}
	}
	.menu_view {
		padding: 0;
		margin: 0;
		background: none;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		flex-wrap: wrap;
		height: auto;
		.menu-item {
			padding: 12rpx 0;
			margin: 10rpx 1% 0;
			width: 23%;
			height: auto;
			.iconarr {
				border-radius: 10rpx;
				padding: 0;
				margin: 0px auto;
				color: #fff;
				background: #fff;
				display: block;
				width: 80rpx;
				font-size: 48rpx;
				line-height: 80rpx;
				text-align: center;
				height: 80rpx;
			}
			.text {
				padding: 0;
				margin: 12rpx auto 0;
				color: #000;
				width: 100%;
				font-size: 28rpx;
				line-height: 28rpx;
				text-align: center;
			}
		}
	}
</style>
