package com.supermarket.mvcsupermarket.Controller;

import com.supermarket.mvcsupermarket.Command.AddEmployeeCommand;
import com.supermarket.mvcsupermarket.Command.PatchEmployeeCommand;
import com.supermarket.mvcsupermarket.Command.RemoveEmployeeCommand;
import com.supermarket.mvcsupermarket.Entity.Employee;
import com.supermarket.mvcsupermarket.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AddEmployeeCommand addEmployeeCommand;
    @Autowired
    private RemoveEmployeeCommand removeEmployeeCommand;
    @Autowired
    private PatchEmployeeCommand patchEmployeeCommand;

    @PostMapping("/employee")
    public String cadastrarFuncionario(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "./pages/funcionario";
        }
        addEmployeeCommand.execute(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee")
    public String listarFuncionarios(Model model) {
        List<Employee> employees = employeeService.listEmployee();
        model.addAttribute("employee", employees);
        return "./pages/funcionario";
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Void> updateEmployeeName(@PathVariable long id, @RequestBody String newName) {
        if (patchEmployeeCommand.execute(id, newName)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/search")
    public String searchEmployee(@RequestParam String query, Model model) {
        List<Employee> employees = employeeService.searchEmployee(query);
        model.addAttribute("employee", employees);
        return "./pages/funcionario";
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        if (removeEmployeeCommand.execute(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
