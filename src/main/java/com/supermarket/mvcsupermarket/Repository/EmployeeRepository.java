package com.supermarket.mvcsupermarket.Repository;

import com.supermarket.mvcsupermarket.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
    List<Employee> findByNomeContainingIgnoreCaseOrId(String nome, long id);
}