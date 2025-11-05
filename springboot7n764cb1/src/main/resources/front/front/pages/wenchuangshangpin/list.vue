<template>
<!-- category 1 -->
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		<view class="content">
			<view :style='{"minHeight":"100vh","width":"100%","padding":"20rpx 20rpx 240rpx","position":"relative","background":"#DCF7FF","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"width":"100%","padding":"20rpx 0 0","background":"none","display":"flex","height":"auto"}'>
					<view :style='{"margin":"0 12rpx 0 0","flex":"1","position":"relative"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"#37E8DC","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #333" :style='{"border":"2rpx solid rgb(55, 232, 220)","padding":"12rpx 20rpx 12rpx 80rpx","color":"#333","borderRadius":"10rpx","background":"#fff","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.chanpinmingcheng" type="text" placeholder="产品名称" ></input>
					</view>
					<button :style='{"border":"0","padding":"0px","margin":"0","color":"#fff","borderRadius":"10rpx","background":"#37E8DC","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"padding":"20rpx","margin":"20rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%"}'>
					<view @click="sortClick('clicknum')" :style='{"border":"2rpx solid rgb(55, 232, 220)","padding":"0 12rpx","margin":"0 8rpx 0 0","outline":"0","borderRadius":"8rpx","background":"#BBFFFA","display":"flex"}'>
						<text :style='{"color":"#37E8DC","lineHeight":"48rpx","fontSize":"24rpx"}'>点击量</text>
						<text v-if="listSort!='clicknum'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 0 0 8rpx","lineHeight":"48rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
						<text v-else-if="listSort=='clicknum'&&listOrder=='asc'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 0 0 8rpx","lineHeight":"48rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
						<text v-else-if="listSort=='clicknum'&&listOrder=='desc'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 0 0 8rpx","lineHeight":"48rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
					</view>
					<view @click="sortClick('addtime')" :style='{"border":"2rpx solid rgb(55, 232, 220)","padding":"0 12rpx","margin":"0 8rpx 0 0","outline":"0","borderRadius":"8rpx","background":"#BBFFFA","display":"flex"}'>
						<text :style='{"color":"#37E8DC","lineHeight":"48rpx","fontSize":"24rpx"}'>按日期</text>
						<text v-if="listSort!='addtime'" class="icon iconfont icon-shijian18" :style='{"margin":"0 0 0 8rpx","lineHeight":"48rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='asc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 0 0 8rpx","lineHeight":"48rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='desc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 0 0 8rpx","lineHeight":"48rpx","fontSize":"24rpx","color":"#37E8DC"}'></text>
					</view>
				</view>
				<view :style='{"width":"100%","background":"none","height":"auto"}'>
					<!-- 样式1 -->
					<view class="uni-product-list" :style='{"padding":"40rpx 0 0","alignItems":"flex-start","flexWrap":"wrap","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
						<view @tap="onDetailTap(product)" class="uni-product" :style='{"padding":"0 0 20rpx","margin":"0 0 40rpx","backgroundColor":"#fff","borderRadius":"20rpx","flexWrap":"wrap","display":"flex","width":"48%","height":"auto"}' v-for="(product,index) in list" :key="index">
							<view class="uni-product-title" :style='{"padding":"4rpx 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"1.4","fontSize":"28rpx","textOverflow":"ellipsis"}'>产品名称:{{product.chanpinmingcheng}}</view>
							<view class="uni-product-title" :style='{"padding":"4rpx 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"1.4","fontSize":"28rpx","textOverflow":"ellipsis"}'>产品分类:{{product.chanpinfenlei}}</view>
							<image :style='{"padding":"0","margin":"0","objectFit":"cover","borderRadius":"20rpx 20rpx 0 0","display":"block","width":"100%","height":"300rpx","order":"-1"}' mode="aspectFill" class="uni-product-image" v-if="preHttp(product.tupian)" :src="product.tupian.split(',')[0]"></image>
							<image :style='{"padding":"0","margin":"0","objectFit":"cover","borderRadius":"20rpx 20rpx 0 0","display":"block","width":"100%","height":"300rpx","order":"-1"}' mode="aspectFill" class="uni-product-image" v-else :src="product.tupian?baseUrl+product.tupian.split(',')[0]:''"></image>

							<view :style='{"color":"#2DAA90","padding":"0 10rpx","margin":"0"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{product.addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"color":"#EFEA4E","padding":"0 10rpx","margin":"0"}'>
								<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{product.thumbsupnum}}</text>
							</view>
							<view :style='{"color":"#03A48D","padding":"0 10rpx","margin":"0"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{product.storeupnum}}</text>
							</view>
							<view :style='{"color":"#37E8DC","padding":"0 10rpx"}'>
								<text class="icon iconfont icon-chakan2" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{product.clicknum}}</text>
							</view>
							<!-- #ifdef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('wenchuangshangpin','修改')) || (!userid && isAuthFront('wenchuangshangpin','修改'))" @tap.stop.proevent="onUpdate" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('wenchuangshangpin','删除')) || (!userid && isAuthFront('wenchuangshangpin','删除'))" @tap.stop.proevent="onDelete" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
							<!-- #ifndef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('wenchuangshangpin','修改')) || (!userid && isAuthFront('wenchuangshangpin','修改'))" @tap.stop.proevent="onUpdateTap(product)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('wenchuangshangpin','删除')) || (!userid && isAuthFront('wenchuangshangpin','删除'))" @tap.stop.proevent="onDeleteTap(product.id)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
						</view>
					</view>
			
			
			


			
			
			
			
				</view>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx #00000030","color":"#fff","bottom":"120rpx","outline":"none","borderRadius":"100%","left":"20rpx","background":"#F75433","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"fixed","height":"80rpx","zIndex":"999"}' class="add-btn" @click="screenBoxShow=true">筛</button>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","bottom":"120rpx","right":"160rpx","outline":"none","borderRadius":"100%","background":"#37E8DC","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"fixed","height":"80rpx","zIndex":"999"}' v-if="userid && isAuth('wenchuangshangpin','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","bottom":"120rpx","right":"160rpx","outline":"none","borderRadius":"100%","background":"#37E8DC","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"fixed","height":"80rpx","zIndex":"999"}' v-if="!userid && isAuthFront('wenchuangshangpin','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<view :style='{"top":"0","left":"0","background":"rgba(0, 0, 0, .3)","width":"100%","position":"absolute","height":"100%","zIndex":"665"}' v-if="screenBoxShow" @click="screenBoxShow=false"></view>
				<view class="screenBox" :class="screenBoxShow?'screenBoxActive':''">
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0"}'>
						<view :style='{"width":"100%","padding":"0 0 20rpx 20rpx","fontSize":"24rpx","color":"#858585"}'>产品分类</view>
						<view :style='{"width":"100%","flexWrap":"wrap","justifyContent":"space-around","display":"flex"}'>
							<view @click="chanpinfenleiChange(item)" v-for="(item,index) in chanpinfenleiOptions" :key="index" class="screenTab" :class="searchForm.chanpinfenlei==item?'screenTabActive':''">{{item}}</view>
						</view>
					</view>
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0","alignItems":"center","flexWrap":"wrap","justifyContent":"space-around","display":"flex"}'>
						<view :style='{"width":"100%","padding":"0 0 20rpx 20rpx","fontSize":"24rpx","color":"#858585"}'>规格</view>
						<input :style='{"border":"2rpx solid #CCCCCC","padding":"0 20rpx 0 20rpx","borderRadius":"4rpx","background":"#fff","width":"100%","lineHeight":"70rpx","height":"70rpx"}' placeholder="请输入规格" v-model="searchForm.guige">
					</view>
					<view :style='{"width":"100%","padding":"40rpx 0 0","alignItems":"center","justifyContent":"center","display":"flex"}'>
						<div :style='{"border":"2rpx solid rgb(204, 204, 204)","margin":"0 20rpx","color":"#858585","textAlign":"center","background":"#EDEDED","width":"20%","lineHeight":"60rpx","height":"60rpx"}' @click="screenReset">重置</div>
						<div :style='{"margin":"0 20rpx","color":"#fff","textAlign":"center","background":"#37E8DC","width":"20%","lineHeight":"60rpx","height":"60rpx"}' @click="search">搜索</div>
					</view>
				</view>
			</view>
		</view>
	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				chanpinfenleiOptions:[],
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				clicknumColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				list: [],
				lists: [],
                userid: '',
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				searchForm:{
					chanpinmingcheng: '',
					chanpinfenlei: '',
					guige: '',
				},
				CustomBar: '0',
				listSort: 'id',
				listOrder: 'desc',
				screenBoxShow: false,
			};
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
		async onShow() {
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.clicknumColor = this.clicknumColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		async onLoad(options) {
            if(options.userid) {
                this.userid=options.userid;
            } else {
                this.userid = "";
            }
			this.hasNext = true
			// 重新加载数据
			// if (this.mescroll) this.mescroll.resetUpScroll()
			let res = {};
			//chanpinfenlei筛选
			res = await this.$api.option(`chanpinfenlei`,`chanpinfenlei`,{});
			this.chanpinfenleiOptions = res.data;
		},
		components: {
		},
		methods: {
			chanpinfenleiChange(e){
				this.searchForm.chanpinfenlei = e
				this.$forceUpdate()
			},
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
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
			sortClick(type){
				if(this.listSort==type){
					if(this.listOrder == 'desc'){
						this.listOrder = 'asc'
					}else{
						this.listOrder = 'desc'
					}
				}else{
					this.listSort = type
					this.listOrder = 'desc'
				}
				this.search()
			},
            priceChange(price) {
                return Number(price).toFixed(2);
            },
            preHttp(str) {
                return str && str.substr(0,4)=='http';
            },
			//类别搜索
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
				}
				params['sort'] = this.listSort;
				params['order'] = this.listOrder;

				if(this.searchForm.chanpinmingcheng){
					params['chanpinmingcheng'] = '%' + this.searchForm.chanpinmingcheng + '%'
				}
				if(this.searchForm.chanpinfenlei){
					params['chanpinfenlei'] = '%' + this.searchForm.chanpinfenlei + '%'
				}
				if(this.searchForm.guige){
					params['guige'] = '%' + this.searchForm.guige + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`wenchuangshangpin`, params);
                } else {
                    res = await this.$api.list(`wenchuangshangpin`, params);
                }

				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				this.$forceUpdate()
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(item) {
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			onUpdate(e){
				this.onUpdateTap(e.currentTarget.dataset.row)
			},
			// 修改
			onUpdateTap(row){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update?id=${row.id}`)
			},
			// 添加
			onAddTap(){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update`)
			},
			onDelete(e){
				this.onDeleteTap(e.currentTarget.dataset.row.id)
			},
			onDeleteTap(id){
				var _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.del('wenchuangshangpin', JSON.stringify([id]));
							let obj = await _this.$api.list('storeup',{
								page: 1,
								limit: 100,
								tablename: 'wenchuangshangpin',
								refid: id,
							})
							if(obj.data.list.length){
								let arr = []
								for(let x in obj.data.list){
									arr.push(obj.data.list[x].id)
								}
								await _this.$api.del('storeup',JSON.stringify(arr))
							}
							_this.$utils.msg('删除成功');
							_this.hasNext = true
							// 重置分页参数页数为1
							_this.search()
						}
					}
				});
			},
			// 搜索
			async search(){
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
				}
				searchForm['sort'] = this.listSort;
				searchForm['order'] = this.listOrder;

				if(this.searchForm.chanpinmingcheng){
					searchForm['chanpinmingcheng'] = '%' + this.searchForm.chanpinmingcheng + '%'
				}
				if(this.searchForm.chanpinfenlei){
					searchForm['chanpinfenlei'] = '%' + this.searchForm.chanpinfenlei + '%'
				}
				if(this.searchForm.guige){
					searchForm['guige'] = '%' + this.searchForm.guige + '%'
				}
                let res = {};
                if(this.userid) {
                    res = await this.$api.page(`wenchuangshangpin`, searchForm);
                } else {
                    res = await this.$api.list(`wenchuangshangpin`, searchForm);
                }
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
				this.screenBoxShow = false
			}
		}
	};
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.category-one .tab {
		cursor: pointer;
		border-radius: 10rpx;
		padding: 0 40rpx;
		box-shadow: inset 10rpx -10rpx 8rpx 0px rgba(255, 255, 255, 0.25),inset 0px 8rpx 8rpx 0px rgb(205, 206, 206);
		margin: 0 20rpx 0 0;
		color: #999898;
		background: linear-gradient(135.00deg, rgb(225, 239, 238) 0%,rgb(213, 213, 213) 100%);
		display: inline-block;
		width: auto;
		font-size: 28rpx;
		line-height: 80rpx;
		height: 80rpx;
	}
	
	.category-one .tab.active {
		cursor: pointer;
		border: 2rpx solid rgba(255, 255, 255, 0.5);
		padding: 0 40rpx;
		margin: 0 20rpx 0 0;
		color: #fff;
		display: inline-block;
		font-size: 28rpx;
		line-height: 80rpx;
		border-radius: 10rpx;
		box-shadow: inset 10rpx -10rpx 8rpx 0px rgba(255, 255, 255, 0.25),inset 0px 8rpx 8rpx 0px rgb(25, 191, 240);
		background: linear-gradient(135.00deg, rgb(55, 232, 220) 0%,rgb(25, 191, 240) 100%);
		width: auto;
		height: 80rpx;
	}
	.screenBox {
		border-radius: 0 0 40rpx 40rpx;
		padding: 20rpx 20rpx 60rpx;
		transform: translate3d(0, -100%, 0);
		z-index: 666;
		top: 0;
		background: #fff;
		width: 100%;
		position: absolute;
		right: 0;
		transition: transform .3s;
		height: auto;
		.screenTab {
			border: 2rpx solid #CCCCCC;
			border-radius: 4rpx;
			margin: 10rpx 0 10rpx 0;
			color: #CCCCCC;
			background: #fff;
			width: calc(100% / 3 - 24rpx);
			line-height: 70rpx;
			text-align: center;
		}
		.screenTabActive {
			border-radius: 4rpx;
			margin: 10rpx 0 10rpx 0;
			color: #fff;
			background: linear-gradient(-45.00deg, rgb(25, 191, 240) 0%,rgb(55, 232, 220) 100%);
			width: calc(100% / 3 - 20rpx);
			line-height: 70rpx;
			text-align: center;
		}
	}
	.screenBoxActive {
		transform: translate3d(0, 0, 0);
	}
</style>
