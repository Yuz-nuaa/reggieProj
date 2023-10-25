package com.zyfproject.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyfproject.reggie.dto.SetmealDto;
import com.zyfproject.reggie.entity.Setmeal;

import java.util.List;


public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);


    public void removeWithDish(List<Long> ids);
}
