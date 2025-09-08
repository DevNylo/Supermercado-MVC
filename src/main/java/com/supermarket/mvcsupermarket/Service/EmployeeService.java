package com.supermarket.mvcsupermarket.Service;

import com.supermarket.mvcsupermarket.Entity.Employee;
import com.supermarket.mvcsupermarket.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listEmployee() {
        return employeeRepository.findAll();
    }

    public void salvarFuncionario(Employee employee) {
        employeeRepository.save(employee);
    }

    public boolean deleteFuncionario(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateEmployeeName(long id, String newName) {
        Employee employee = employeeRepository.findById(id);

        if (employee != null) {
            employee.setNome(newName);
            employeeRepository.save(employee);
            return true;
        } else {
            return false;
        }
    }

    public List<Employee> searchEmployee(String query) {
        List<Employee> employees = new ArrayList<>();
        employees = employeeRepository.findByNomeContainingIgnoreCaseOrId(query, Integer.parseInt(query));
        return employees;
    }
}
