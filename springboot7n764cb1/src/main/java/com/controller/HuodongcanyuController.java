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

import com.entity.HuodongcanyuEntity;
import com.entity.view.HuodongcanyuView;

import com.service.HuodongcanyuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 活动参与
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-01 09:55:51
 */
@RestController
@RequestMapping("/huodongcanyu")
public class HuodongcanyuController {
    @Autowired
    private HuodongcanyuService huodongcanyuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuodongcanyuEntity huodongcanyu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			huodongcanyu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<HuodongcanyuEntity> ew = new EntityWrapper<HuodongcanyuEntity>();


        //查询结果
		PageUtils page = huodongcanyuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongcanyu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,HuodongcanyuEntity huodongcanyu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<HuodongcanyuEntity> ew = new EntityWrapper<HuodongcanyuEntity>();

        //查询结果
		PageUtils page = huodongcanyuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongcanyu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuodongcanyuEntity huodongcanyu){
       	EntityWrapper<HuodongcanyuEntity> ew = new EntityWrapper<HuodongcanyuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huodongcanyu, "huodongcanyu")); 
        return R.ok().put("data", huodongcanyuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuodongcanyuEntity huodongcanyu){
        EntityWrapper< HuodongcanyuEntity> ew = new EntityWrapper< HuodongcanyuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huodongcanyu, "huodongcanyu")); 
		HuodongcanyuView huodongcanyuView =  huodongcanyuService.selectView(ew);
		return R.ok("查询活动参与成功").put("data", huodongcanyuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuodongcanyuEntity huodongcanyu = huodongcanyuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(huodongcanyu,deSens);
        return R.ok().put("data", huodongcanyu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuodongcanyuEntity huodongcanyu = huodongcanyuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(huodongcanyu,deSens);
        return R.ok().put("data", huodongcanyu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HuodongcanyuEntity huodongcanyu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongcanyu);
        huodongcanyuService.insert(huodongcanyu);
        return R.ok().put("data",huodongcanyu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody HuodongcanyuEntity huodongcanyu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongcanyu);
        huodongcanyuService.insert(huodongcanyu);
        return R.ok().put("data",huodongcanyu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody HuodongcanyuEntity huodongcanyu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongcanyu);
        //全部更新
        huodongcanyuService.updateById(huodongcanyu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        huodongcanyuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
