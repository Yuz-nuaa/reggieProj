package com.zyfproject.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyfproject.reggie.common.R;
import com.zyfproject.reggie.dto.DishDto;
import com.zyfproject.reggie.entity.Dish;
import com.zyfproject.reggie.service.DishFlavorService;
import com.zyfproject.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishFlavorController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        return null;
    }

    /**
     * 菜品信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Dish> pageInfo = new Page<>(page, pageSize);

        // 条件构造器
        LambdaQueryWrapper<Dish> queryWarpper = new LambdaQueryWrapper<>();
        queryWarpper.like(name != null, Dish::getName, name);
        // 排序条件，根据更新时间降序排序
        queryWarpper.orderByDesc(Dish::getUpdateTime);

        dishService.page(pageInfo, queryWarpper);

        return R.success(pageInfo);
    }
}
