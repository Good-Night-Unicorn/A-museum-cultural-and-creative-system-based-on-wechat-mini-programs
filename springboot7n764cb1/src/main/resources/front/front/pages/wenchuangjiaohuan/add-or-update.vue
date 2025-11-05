<template>
<view class="content">
	<view :style='{"minHeight":"100vh","width":"100%","padding":"0","position":"relative","background":"#DCF7FF","height":"auto"}'>
		<form :style='{"width":"100%","padding":"0","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">产品名称</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.chanpinmingcheng" v-model="ruleForm.chanpinmingcheng" placeholder="产品名称"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">产品分类</view>
				<picker :disabled="ro.chanpinfenlei" :style='{"width":"100%","flex":"1","height":"auto"}' @change="chanpinfenleiChange" :value="chanpinfenleiIndex" :range="chanpinfenleiOptions">
					<view :style='{"width":"100%","padding":"0px 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#1CC3EE"}' class="uni-input">{{ruleForm.chanpinfenlei?ruleForm.chanpinfenlei:"请选择产品分类"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">用户账号</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.yonghuzhanghao" v-model="ruleForm.yonghuzhanghao" placeholder="用户账号"  type="text"></input>
			</view>
      
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">交换条件</view>
				<textarea :style='{"border":"0","padding":"20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"300rpx"}' v-model="ruleForm.jiaohuantiaojian" placeholder="交换条件" :maxlength="-1"></textarea>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","borderWidth":"0 0 2rpx 0","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"right"}' class="title">产品介绍</view>
				<xia-editor ref="chanpinjieshao" :style='{"minHeight":"360rpx","border":"0px solid #efefef","width":"100%","padding":"20rpx","height":"auto"}' v-model="ruleForm.chanpinjieshao" placeholder="产品介绍" @editorChange="chanpinjieshaoChange"></xia-editor>
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
				chanpinmingcheng: '',
				chanpinfenlei: '',
				tupian: '',
				jiaohuantiaojian: '',
				chanpinjieshao: '',
				yonghuzhanghao: '',
				},
				chanpinfenleiOptions: [],
				chanpinfenleiIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   chanpinmingcheng : false,
				   chanpinfenlei : false,
				   tupian : false,
				   jiaohuantiaojian : false,
				   chanpinjieshao : false,
				   yonghuzhanghao : false,
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
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取
			this.ruleForm.yonghuzhanghao = this.user.yonghuzhanghao
			this.ro.yonghuzhanghao = true;


			// 下拉框
			res = await this.$api.option(`chanpinfenlei`,`chanpinfenlei`,{});
			this.chanpinfenleiOptions = res.data;
			this.chanpinfenleiOptions.unshift("请选择产品分类");

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
				res = await this.$api.info(`wenchuangjiaohuan`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='chanpinmingcheng'){
						this.ruleForm.chanpinmingcheng = obj[o];
						this.ro.chanpinmingcheng = true;
						continue;
					}
					if(o=='chanpinfenlei'){
						this.ruleForm.chanpinfenlei = obj[o];
						this.ro.chanpinfenlei = true;
						continue;
					}
					if(o=='tupian'){
						this.ruleForm.tupian = obj[o].split(",")[0];
						this.ro.tupian = true;
						continue;
					}
					if(o=='jiaohuantiaojian'){
						this.ruleForm.jiaohuantiaojian = obj[o];
						this.ro.jiaohuantiaojian = true;
						continue;
					}
					if(o=='chanpinjieshao'){
						this.ruleForm.chanpinjieshao = obj[o];
						this.ro.chanpinjieshao = true;
						continue;
					}
					if(o=='yonghuzhanghao'){
						this.ruleForm.yonghuzhanghao = obj[o];
						this.ro.yonghuzhanghao = true;
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
			chanpinjieshaoChange(e) {
				this.ruleForm.chanpinjieshao = e
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.app-update-pv . .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 多级联动参数



			// 下拉变化
			chanpinfenleiChange(e) {
				this.chanpinfenleiIndex = e.target.value
				this.ruleForm.chanpinfenlei = this.chanpinfenleiOptions[this.chanpinfenleiIndex]
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
					let res = await this.$api.list(`wenchuangjiaohuan`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`wenchuangjiaohuan`, this.ruleForm);
						}else{
							oet = await this.$api.add(`wenchuangjiaohuan`, this.ruleForm);
						}

						this.$utils.msgBack('提交成功');
					}
				} else {
				//跨表计算
					let oet = {}
					if(this.ruleForm.id){
						await this.$api.update(`wenchuangjiaohuan`, this.ruleForm);
					}else{
						oet = await this.$api.add(`wenchuangjiaohuan`, this.ruleForm);
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
