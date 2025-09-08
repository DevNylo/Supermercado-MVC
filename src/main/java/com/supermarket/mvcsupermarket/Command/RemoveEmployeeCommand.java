package com.supermarket.mvcsupermarket.Command;

import com.supermarket.mvcsupermarket.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveEmployeeCommand {

    @Autowired
    private EmployeeService employeeService;

    public boolean execute(Integer id) {
        return employeeService.deleteFuncionario(id);
    }
}
