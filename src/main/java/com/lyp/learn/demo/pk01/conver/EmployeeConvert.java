package com.lyp.learn.demo.pk01.conver;

import com.lyp.learn.demo.pk01.crud.entities.Department;
import com.lyp.learn.demo.pk01.crud.entities.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConvert implements Converter<String,Employee> {
    @Override
    public Employee convert(String source) {
        System.out.println("EmployeeConvert ............");
        if(source != null){
            String[] values = source.split("-");
            if(values != null && values.length == 4){
                String lastName = values[0];
                String email = values[1];
                Integer gender = Integer.parseInt(values[2]);
                Department department = new Department();
                department.setId(Integer.parseInt(values[3]));
                Employee employee = new Employee(null,lastName,email,gender,department);
                System.out.println(source + " ::::convert::::" + employee);
                return employee;

            }
        }
        return null;
    }
}
