package com.zyfproject.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyfproject.reggie.common.CustomerException;
import com.zyfproject.reggie.entity.Category;
import com.zyfproject.reggie.entity.Dish;
import com.zyfproject.reggie.entity.Setmeal;
import com.zyfproject.reggie.mapper.CategoryMapper;
import com.zyfproject.reggie.service.CategoryService;
import com.zyfproject.reggie.service.DishService;
import com.zyfproject.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id分类，删除之前判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        // 查询当前分类是否关联菜品，如果已经关联，抛出业务异常
        if (count1 > 0) {
            throw new CustomerException("当前分类下关联了菜品，不能删除");

        }
        // 查询当前分类是否关联套餐，如果已经关联，抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count();
        if(count2>0){

        }

    }
}
