<template>
<view class="content">
	<view class="box" :style='{"width":"100%","padding":"24rpx","alignItems":"center","background":"url(http://codegen.caihongy.cn/20241213/478195efd93242f2a21511d43d30ee5a.png) center center/100% 100%","display":"flex","height":"100%"}'>
		<view :style='{"padding":"0 0 20rpx","boxShadow":"0px 8rpx 8rpx 0px rgb(39, 148, 150)","margin":"0 auto","borderRadius":"0 0 20rpx 20rpx","flexWrap":"wrap","background":"rgba(255, 255, 255, 0.5)","display":"flex","width":"85%","position":"relative","height":"auto"}'>
			<image :style='{"width":"160rpx","margin":"0 auto 24rpx auto","borderRadius":"8rpx","display":"none","height":"160rpx"}' src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg" mode="aspectFill"></image>
			<view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">用户账号：</view>
				<input :style='{"border":"0px solid rgb(255, 170, 51)","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"none","fontSize":"28rpx","height":"70rpx"}'  v-model="ruleForm.yonghuzhanghao"  type="text"  class="uni-input" name="" placeholder="用户账号" />
			</view>
			<view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">用户姓名：</view>
				<input :style='{"border":"0px solid rgb(255, 170, 51)","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"none","fontSize":"28rpx","height":"70rpx"}'  v-model="ruleForm.yonghuxingming"  type="text"  class="uni-input" name="" placeholder="用户姓名" />
			</view>
			<view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">密码：</view>
				<input :style='{"border":"0px solid rgb(255, 170, 51)","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"none","fontSize":"28rpx","height":"70rpx"}'  v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">确认密码：</view>
				<input :style='{"border":"0px solid rgb(255, 170, 51)","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"none","fontSize":"28rpx","height":"70rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
            <view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" class="">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">性别：</view>
				<picker :style='{"width":"100%","margin":"0","display":"flex","height":"auto"}' v-if="tableName=='yonghu'"  @change="yonghuxingbieChange" :value="yonghuxingbieIndex" :range="yonghuxingbieOptions">
					<view>
						<view :style='{"padding":"0 20rpx","lineHeight":"88rpx","fontSize":"28rpx","color":"#000","flex":"1"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
            <view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" @tap="yonghutouxiangTap" class="">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">头像：</view>
				<image :style='{"width":"100rpx","borderRadius":"16rpx","display":"block","height":"100rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
                <image :style='{"width":"100rpx","borderRadius":"16rpx","display":"block","height":"100rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
            </view>
			<view :style='{"padding":"0 0 10rpx","margin":"0 auto 24rpx","borderColor":"#19BFF0","borderWidth":"0 0 2rpx","display":"flex","width":"calc(100% - 40rpx)","borderStyle":"solid","height":"auto","order":"2"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<view :style='{"width":"128rpx","padding":"0","lineHeight":"70rpx","fontSize":"24rpx","color":"#000"}' class="label">电话号码：</view>
				<input :style='{"border":"0px solid rgb(255, 170, 51)","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"none","fontSize":"28rpx","height":"70rpx"}'  v-model="ruleForm.dianhuahaoma"  type="text"  class="uni-input" name="" placeholder="电话号码" />
			</view>
			<button :style='{"border":"0","padding":"0px","margin":"40rpx auto 20rpx","color":"rgb(255, 255, 255)","borderRadius":"0","background":"#37E8DC","width":"calc(100% - 40rpx)","lineHeight":"88rpx","fontSize":"32rpx","height":"88rpx","order":"5"}' class="btn-submit" @tap="register" type="primary">注册</button>
			
			<view class="idea1" :style='{"margin":"-24rpx 0 20rpx","color":"#000","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241213/5cdff122bb194ccab81b9ff3b63265a7.png) center center/100% 100%","display":"block","width":"100%","lineHeight":"140rpx","fontSize":"36rpx","fontWeight":"bold","height":"120rpx","order":"1"}'>登录</view>
			<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
			<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
		</view>
	</view>
</view>
</template>

<script>
    import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
                yonghuxingbieOptions: [],
                yonghuxingbieIndex: 0,
				ruleForm: {
                yonghuzhanghao: '',
                yonghuxingming: '',
                mima: '',
                xingbie: '',
                touxiang: '',
                dianhuahaoma: '',
                jifen: '0',
				},
				tableName:""
			}
		},
        components: {
            multipleSelect
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
		async onLoad() {
			let res = [];
			let table = uni.getStorageSync("loginTable");
            this.tableName = table;

                        // 自定义下拉框值
			if(this.tableName=='yonghu'){
                this.yonghuxingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.yonghuxingbieOptions[0]
			}
            if(`yonghu` == this.tableName){
                this.ruleForm.jifen='0'
            }
			
			this.styleChange()
		},
		methods: {

            // 下拉变化
            yonghuxingbieChange(e) {
                    this.yonghuxingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.yonghuxingbieOptions[this.yonghuxingbieIndex]
            },
            yonghutouxiangTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
                });
            },

            toggleTab(str) {
                this.$refs[str].show();
            },

			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.registerFrom.content.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 注册
			async register() {
				
				if((!this.ruleForm.yonghuzhanghao) && `yonghu` == this.tableName){
					this.$utils.msg(`用户账号不能为空`);
					return
				}
				if((!this.ruleForm.yonghuxingming) && `yonghu` == this.tableName){
					this.$utils.msg(`用户姓名不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `yonghu` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
                if(`yonghu` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
                    this.$utils.msg(`两次密码输入不一致`);
                    return
                }
				if(`yonghu` == this.tableName && this.ruleForm.dianhuahaoma&&(!this.$validate.isMobile(this.ruleForm.dianhuahaoma))){
					this.$utils.msg(`电话号码应输入手机格式`);
					return
				}
				if(`yonghu` == this.tableName && this.ruleForm.jifen&&(!this.$validate.isIntNumer(this.ruleForm.jifen))){
					this.$utils.msg(`积分应输入整数`);
					return
				}
				await this.$api.register(`${this.tableName}`, this.ruleForm);
				this.$utils.msgBack('注册成功');;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
