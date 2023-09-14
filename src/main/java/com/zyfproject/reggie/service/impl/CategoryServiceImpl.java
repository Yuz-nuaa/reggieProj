package com.zyfproject.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyfproject.reggie.entity.Category;
import com.zyfproject.reggie.mapper.CategoryMapper;
import com.zyfproject.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
