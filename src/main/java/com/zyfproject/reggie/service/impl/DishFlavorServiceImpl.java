package com.zyfproject.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyfproject.reggie.entity.DishFlavor;
import com.zyfproject.reggie.mapper.DishFlavorMapper;
import com.zyfproject.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
