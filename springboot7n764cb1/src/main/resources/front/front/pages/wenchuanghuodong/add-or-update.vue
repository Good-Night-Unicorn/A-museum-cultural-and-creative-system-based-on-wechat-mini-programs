<template>
<view class="content">
	<view :style='{"minHeight":"100vh","width":"100%","padding":"0","position":"relative","background":"#DCF7FF","height":"auto"}'>
		<form :style='{"width":"100%","padding":"0","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">活动编号</view>
				<view :style='{"padding":"0px 24rpx","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)","flex":"1"}' class="right-input">
					{{ruleForm.huodongbianhao}}
				</view>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">活动名称</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.huodongmingcheng" v-model="ruleForm.huodongmingcheng" placeholder="活动名称"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">活动状态</view>
				<picker :disabled="ro.huodongzhuangtai" :style='{"width":"100%","flex":"1","height":"auto"}' @change="huodongzhuangtaiChange" :value="huodongzhuangtaiIndex" :range="huodongzhuangtaiOptions">
					<view :style='{"width":"100%","padding":"0px 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#1CC3EE"}' class="uni-input">{{ruleForm.huodongzhuangtai?ruleForm.huodongzhuangtai:"请选择活动状态"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">活动日期</view>
				<picker  :disabled="ro.huodongriqi" :style='{"width":"100%","flex":"1","height":"auto"}' mode="date" :value="ruleForm.huodongriqi" @change="huodongriqiChange">
					<view :style='{"width":"100%","padding":"0px 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#1CC3EE"}' class="uni-input">{{ruleForm.huodongriqi?ruleForm.huodongriqi:"请选择活动日期"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">发布时间</view>
				<picker  :disabled="ro.fabushijian" :style='{"width":"100%","flex":"1","height":"auto"}' mode="date" :value="ruleForm.fabushijian" @change="fabushijianChange">
					<view :style='{"width":"100%","padding":"0px 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#1CC3EE"}' class="uni-input">{{ruleForm.fabushijian?ruleForm.fabushijian:"请选择发布时间"}}</view>
				</picker>
			</view>
       
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","borderWidth":"0 0 2rpx 0","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">活动内容</view>
				<xia-editor ref="huodongneirong" :style='{"minHeight":"360rpx","border":"0px solid #efefef","width":"100%","padding":"20rpx","height":"auto"}' v-model="ruleForm.huodongneirong" placeholder="活动内容" @editorChange="huodongneirongChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","alignItems":"center","flexDirection":"column","justifyContent":"space-between","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0px","margin":"0 0 20rpx","color":"rgb(255, 255, 255)","background":"#37E8DC","width":"60%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				huodongbianhao: this.getUUID(),
				huodongmingcheng: '',
				tupian: '',
				huodongzhuangtai: '',
				huodongriqi: '',
				huodongneirong: '',
				fabushijian: '',
				},
				huodongzhuangtaiOptions: [],
				huodongzhuangtaiIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   huodongbianhao : false,
				   huodongmingcheng : false,
				   tupian : false,
				   huodongzhuangtai : false,
				   huodongriqi : false,
				   huodongneirong : false,
				   fabushijian : false,
				},
				virtualPay: false,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},



		},
		async onLoad(options) {
			if(options.virtualPay){
				this.virtualPay = true
			}
			this.ruleForm.fabushijian = this.$utils.getCurDate();
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取


			// 自定义下拉框值
			this.huodongzhuangtaiOptions = "进行中,已截止".split(',')

			// 如果有登录，获取登录后保存的userid
			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`wenchuanghuodong`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='huodongbianhao'){
						this.ruleForm.huodongbianhao = obj[o];
						this.ro.huodongbianhao = true;
						continue;
					}
					if(o=='huodongmingcheng'){
						this.ruleForm.huodongmingcheng = obj[o];
						this.ro.huodongmingcheng = true;
						continue;
					}
					if(o=='tupian'){
						this.ruleForm.tupian = obj[o].split(",")[0];
						this.ro.tupian = true;
						continue;
					}
					if(o=='huodongzhuangtai'){
						this.ruleForm.huodongzhuangtai = obj[o];
						this.ro.huodongzhuangtai = true;
						continue;
					}
					if(o=='huodongriqi'){
						this.ruleForm.huodongriqi = obj[o];
						this.ro.huodongriqi = true;
						continue;
					}
					if(o=='huodongneirong'){
						this.ruleForm.huodongneirong = obj[o];
						this.ro.huodongneirong = true;
						continue;
					}
					if(o=='fabushijian'){
						this.ruleForm.fabushijian = obj[o];
						this.ro.fabushijian = true;
						continue;
					}
				}
			}
			this.styleChange()
			this.$forceUpdate()
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap()
				}, 300)
			}
		},
		methods: {
			huodongneirongChange(e) {
				this.ruleForm.huodongneirong = e
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.app-update-pv . .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 多级联动参数

			huodongriqiChange(e) {
				this.ruleForm.huodongriqi = e.target.value;
				this.$forceUpdate();
			},
			fabushijianChange(e) {
				this.ruleForm.fabushijian = e.target.value;
				this.$forceUpdate();
			},


			// 下拉变化
			huodongzhuangtaiChange(e) {
				this.huodongzhuangtaiIndex = e.target.value
				this.ruleForm.huodongzhuangtai = this.huodongzhuangtaiOptions[this.huodongzhuangtaiIndex]
			},

			tupianTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.tupian = 'upload/' + res.file;
					_this.$forceUpdate();
					_this.$nextTick(()=>{
						_this.styleChange()
					})
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap() {
				let that = this
				if(this.ruleForm.huodongbianhao){
					this.ruleForm.huodongbianhao = String(this.ruleForm.huodongbianhao)
				}
				//跨表计算判断
				var obj;
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				if(this.cross){
					uni.setStorageSync('crossCleanType',true);
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						if(!obj) {
							obj = uni.getStorageSync('crossObj');
						}
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName){
									obj[o] = statusColumnValue;
								}

							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						} else {
							   crossuserid=Number(uni.getStorageSync('appUserid'));
							   crossrefid=obj['id'];
							   crossoptnum=uni.getStorageSync('statusColumnName');
							   crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
						}
					}
				}
				if(crossrefid && crossuserid) {
					this.ruleForm.crossuserid=crossuserid;
					this.ruleForm.crossrefid=crossrefid;
					let params = {
						page: 1,
						limit:10,
						crossuserid:crossuserid,
						crossrefid:crossrefid,
					}
					let res = await this.$api.list(`wenchuanghuodong`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`wenchuanghuodong`, this.ruleForm);
						}else{
							oet = await this.$api.add(`wenchuanghuodong`, this.ruleForm);
						}

						this.$utils.msgBack('提交成功');
					}
				} else {
				//跨表计算
					let oet = {}
					if(this.ruleForm.id){
						await this.$api.update(`wenchuanghuodong`, this.ruleForm);
					}else{
						oet = await this.$api.add(`wenchuanghuodong`, this.ruleForm);
					}

					this.$utils.msgBack('提交成功');
				}
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				if(this.ro[str]){
					return false
				}
				this.$refs[str].show();
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
