package com.zyfproject.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyfproject.reggie.common.R;
import com.zyfproject.reggie.entity.Employee;
import com.zyfproject.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        if(employee == null){
            return null;
        }
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee singleEmp = employeeService.getOne(queryWrapper);
        if(singleEmp == null){
            return R.error("登陆失败");
        }

        if(!singleEmp.getPassword().equals(password)){
            return R.error("登陆失败");
        }
        // 查看员工状态是否禁用
        if(singleEmp.getStatus() == 0){
            return R.error("账号已被禁用");
        }

        // 登陆成功，将用户id存入session
        request.getSession().setAttribute("employee", singleEmp.getId());
        return R.success(employee);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 清楚session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工信息，员工信息：{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateTime(LocalDateTime.now());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增一条员工信息成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {}, name = {}");

        // 分页构造器
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        return null;
    }
}
