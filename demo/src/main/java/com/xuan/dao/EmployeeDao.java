package com.xuan.dao;

import com.xuan.pojo.Department;
import com.xuan.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    //模拟数据库的数据
    private  static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static{
        employees = new HashMap<Integer, Employee>();//创建一个部门表

        employees.put(101,new Employee(1001,"AA","1@qq.com",1,new Department(101,"教学部")));
        employees.put(102,new Employee(1002,"BB","2@qq.com",0,new Department(102,"市场部")));
        employees.put(103,new Employee(1003,"CC","3@qq.com",1,new Department(103,"教研部")));
        employees.put(104,new Employee(1004,"DD","4@qq.com",0,new Department(104,"运营部")));
        employees.put(105,new Employee(1005,"EE","5@qq.com",1,new Department(105,"后勤部")));
    }

    private static Integer initId = 1006;

    //增加一个员工
    public  void  save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }


    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工
    public void delete(Integer id){
        employees.remove(id);
    }




}
