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

import com.entity.WenchuangshangpinEntity;
import com.entity.view.WenchuangshangpinView;

import com.service.WenchuangshangpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 文创商品
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-01 09:55:51
 */
@RestController
@RequestMapping("/wenchuangshangpin")
public class WenchuangshangpinController {
    @Autowired
    private WenchuangshangpinService wenchuangshangpinService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WenchuangshangpinEntity wenchuangshangpin,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<WenchuangshangpinEntity> ew = new EntityWrapper<WenchuangshangpinEntity>();


        //查询结果
		PageUtils page = wenchuangshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenchuangshangpin), params), params));
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
    public R list(@RequestParam Map<String, Object> params,WenchuangshangpinEntity wenchuangshangpin, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<WenchuangshangpinEntity> ew = new EntityWrapper<WenchuangshangpinEntity>();

        //查询结果
		PageUtils page = wenchuangshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenchuangshangpin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WenchuangshangpinEntity wenchuangshangpin){
       	EntityWrapper<WenchuangshangpinEntity> ew = new EntityWrapper<WenchuangshangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wenchuangshangpin, "wenchuangshangpin")); 
        return R.ok().put("data", wenchuangshangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WenchuangshangpinEntity wenchuangshangpin){
        EntityWrapper< WenchuangshangpinEntity> ew = new EntityWrapper< WenchuangshangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wenchuangshangpin, "wenchuangshangpin")); 
		WenchuangshangpinView wenchuangshangpinView =  wenchuangshangpinService.selectView(ew);
		return R.ok("查询文创商品成功").put("data", wenchuangshangpinView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WenchuangshangpinEntity wenchuangshangpin = wenchuangshangpinService.selectById(id);
        if(null==wenchuangshangpin.getClicknum()){
            wenchuangshangpin.setClicknum(0);
        }
		wenchuangshangpin.setClicknum(wenchuangshangpin.getClicknum()+1);
		wenchuangshangpin.setClicktime(new Date());
		wenchuangshangpinService.updateById(wenchuangshangpin);
        wenchuangshangpin = wenchuangshangpinService.selectView(new EntityWrapper<WenchuangshangpinEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(wenchuangshangpin,deSens);
        return R.ok().put("data", wenchuangshangpin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WenchuangshangpinEntity wenchuangshangpin = wenchuangshangpinService.selectById(id);
        if(null==wenchuangshangpin.getClicknum()){
            wenchuangshangpin.setClicknum(0);
        }
		wenchuangshangpin.setClicknum(wenchuangshangpin.getClicknum()+1);
		wenchuangshangpin.setClicktime(new Date());
		wenchuangshangpinService.updateById(wenchuangshangpin);
        wenchuangshangpin = wenchuangshangpinService.selectView(new EntityWrapper<WenchuangshangpinEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(wenchuangshangpin,deSens);
        return R.ok().put("data", wenchuangshangpin);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        WenchuangshangpinEntity wenchuangshangpin = wenchuangshangpinService.selectById(id);
        if(type.equals("1")) {
        	wenchuangshangpin.setThumbsupnum(wenchuangshangpin.getThumbsupnum()+1);
        } else {
        	wenchuangshangpin.setCrazilynum(wenchuangshangpin.getCrazilynum()+1);
        }
        wenchuangshangpinService.updateById(wenchuangshangpin);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WenchuangshangpinEntity wenchuangshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenchuangshangpin);
        wenchuangshangpinService.insert(wenchuangshangpin);
        return R.ok().put("data",wenchuangshangpin.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WenchuangshangpinEntity wenchuangshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenchuangshangpin);
        wenchuangshangpinService.insert(wenchuangshangpin);
        return R.ok().put("data",wenchuangshangpin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody WenchuangshangpinEntity wenchuangshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenchuangshangpin);
        //全部更新
        wenchuangshangpinService.updateById(wenchuangshangpin);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wenchuangshangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,WenchuangshangpinEntity wenchuangshangpin, HttpServletRequest request,String pre){
        EntityWrapper<WenchuangshangpinEntity> ew = new EntityWrapper<WenchuangshangpinEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");

		PageUtils page = wenchuangshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenchuangshangpin), params), params));
        return R.ok().put("data", page);
    }








}
