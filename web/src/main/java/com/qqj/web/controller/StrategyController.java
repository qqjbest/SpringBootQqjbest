package com.qqj.web.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qqj.bean.PagerBean;
import com.qqj.bean.Result;
import com.qqj.bean.ResultGenerator;
import com.qqj.entity.Strategy;
import com.qqj.service.IStrategyService;
import com.qqj.util.CommonUtil;
import com.qqj.util.DateUtils;
import com.qqj.util.ValidatorUtil;
import com.qqj.vo.VoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
@RestController
@RequestMapping("/admin/strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    PagerBean<Strategy> getAll(PagerBean<Strategy> pager)
    {
        if (pager.isPage())
        {
            Page<Strategy> page = PageHelper.startPage(pager.getCurPage(), pager.getLimit());
            strategyService.getAllByMap(pager.getSearch());
            pager.setRowData(page.getResult());
            pager.setTotal((int) page.getTotal());
        }
        else
        {
            pager.setRowData(strategyService.getAllByMap(pager.getSearch()));
        }
        return pager;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody VoStrategy get(@PathVariable("id") Long id)
    {
        Strategy strategy = strategyService.getById(id);
        VoStrategy jsonstrategy = new VoStrategy();
        CommonUtil.copyPropertiesIgnoreNull(strategy, jsonstrategy);
        jsonstrategy.setCreateTime(strategy.getCreateTime().getTime());
        return jsonstrategy;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Result post(Strategy strategy)
    {

        strategy.setCreateTime(DateUtils.getSystemTime());
        strategyService.save(strategy);
        return  ResultGenerator.genSuccessResult();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Result put(Strategy strategy)
    {
        strategy.setUpdateTime(DateUtils.getSystemTime());
        strategyService.updateById(strategy);
        return  ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "{ids}", method = RequestMethod.DELETE)
    public @ResponseBody Result del(String ids)
    {
        strategyService.delete(ids);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public @ResponseBody Result enable(Long id)
    {
        strategyService.enable(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public @ResponseBody Result disable(Long id)
    {
        strategyService.disable(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据角色id获取策略
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getByRoleId")
    public @ResponseBody
    List<VoStrategy> getByRoleId(Long id)
    {
        List<VoStrategy> voStrategys = new ArrayList<>();
        List<Strategy> ownStrategys = new ArrayList<>();
        if (ValidatorUtil.isNotNull(id))
        {
            ownStrategys = strategyService.getByRoleId(id);
        }

        for (Strategy Strategy : ownStrategys)
        {
            VoStrategy tempRoels = new VoStrategy();
            CommonUtil.copyPropertiesIgnoreNull(Strategy, tempRoels);
            tempRoels.setIsOwn(true);
            voStrategys.add(tempRoels);
        }

        List<Strategy> allStrategy = strategyService.getAllEnable();
        allStrategy.removeAll(ownStrategys);
        for (Strategy Strategy : allStrategy)
        {
            VoStrategy tempRoels = new VoStrategy();
            CommonUtil.copyPropertiesIgnoreNull(Strategy, tempRoels);
            voStrategys.add(tempRoels);
        }
        return voStrategys;
    }

}
