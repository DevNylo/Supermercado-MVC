package com.supermarket.mvcsupermarket.Command;

import com.supermarket.mvcsupermarket.Entity.Employee;
import com.supermarket.mvcsupermarket.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddEmployeeCommand {

    @Autowired
    private EmployeeService employeeService;

    public void execute(Employee employee) {
        employeeService.salvarFuncionario(employee);
    }
}