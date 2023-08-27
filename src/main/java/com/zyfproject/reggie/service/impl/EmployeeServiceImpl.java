package com.zyfproject.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyfproject.reggie.entity.Employee;
import com.zyfproject.reggie.mapper.EmployeeMapper;
import com.zyfproject.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
