package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.WenchuangjiaohuanEntity;
import com.entity.view.WenchuangjiaohuanView;

import com.service.WenchuangjiaohuanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 文创交换
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-01 09:55:52
 */
@RestController
@RequestMapping("/wenchuangjiaohuan")
public class WenchuangjiaohuanController {
    @Autowired
    private WenchuangjiaohuanService wenchuangjiaohuanService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WenchuangjiaohuanEntity wenchuangjiaohuan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			wenchuangjiaohuan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<WenchuangjiaohuanEntity> ew = new EntityWrapper<WenchuangjiaohuanEntity>();


        //查询结果
		PageUtils page = wenchuangjiaohuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenchuangjiaohuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,WenchuangjiaohuanEntity wenchuangjiaohuan, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<WenchuangjiaohuanEntity> ew = new EntityWrapper<WenchuangjiaohuanEntity>();

        //查询结果
		PageUtils page = wenchuangjiaohuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenchuangjiaohuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WenchuangjiaohuanEntity wenchuangjiaohuan){
       	EntityWrapper<WenchuangjiaohuanEntity> ew = new EntityWrapper<WenchuangjiaohuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wenchuangjiaohuan, "wenchuangjiaohuan")); 
        return R.ok().put("data", wenchuangjiaohuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WenchuangjiaohuanEntity wenchuangjiaohuan){
        EntityWrapper< WenchuangjiaohuanEntity> ew = new EntityWrapper< WenchuangjiaohuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wenchuangjiaohuan, "wenchuangjiaohuan")); 
		WenchuangjiaohuanView wenchuangjiaohuanView =  wenchuangjiaohuanService.selectView(ew);
		return R.ok("查询文创交换成功").put("data", wenchuangjiaohuanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WenchuangjiaohuanEntity wenchuangjiaohuan = wenchuangjiaohuanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(wenchuangjiaohuan,deSens);
        return R.ok().put("data", wenchuangjiaohuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WenchuangjiaohuanEntity wenchuangjiaohuan = wenchuangjiaohuanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(wenchuangjiaohuan,deSens);
        return R.ok().put("data", wenchuangjiaohuan);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WenchuangjiaohuanEntity wenchuangjiaohuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenchuangjiaohuan);
        wenchuangjiaohuanService.insert(wenchuangjiaohuan);
        return R.ok().put("data",wenchuangjiaohuan.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WenchuangjiaohuanEntity wenchuangjiaohuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenchuangjiaohuan);
        wenchuangjiaohuanService.insert(wenchuangjiaohuan);
        return R.ok().put("data",wenchuangjiaohuan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody WenchuangjiaohuanEntity wenchuangjiaohuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenchuangjiaohuan);
        //全部更新
        wenchuangjiaohuanService.updateById(wenchuangjiaohuan);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wenchuangjiaohuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
