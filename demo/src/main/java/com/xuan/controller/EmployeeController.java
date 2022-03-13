package com.xuan.controller;

import com.xuan.dao.DepartmentDao;
import com.xuan.dao.EmployeeDao;
import com.xuan.pojo.Department;
import com.xuan.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model){
        //获得所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping ("/emp")
    public String toEmp(Employee employee){
        System.out.println("employee"+employee);
        employeeDao.save(employee);//保存员工的信息
        return "redirect:/emps";
    }

    //去员工修改界面
    @PutMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){

        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/upateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/delemp/{id}")
    public String delete(@PathVariable("id")int id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
