package com.supermarket.mvcsupermarket.Controller;

import com.supermarket.mvcsupermarket.Command.AddEmployeeCommand;
import com.supermarket.mvcsupermarket.Entity.Employee;
import com.supermarket.mvcsupermarket.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddEmployeeCommand.class)
public class AddEmployeeCommandTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private AddEmployeeCommand addEmployeeCommand;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCadastrarFuncionario() throws Exception {
        Employee employee = new Employee();
        employee.setId(110044);
        employee.setNome("John Doe");
        employee.setCargo("Analista");
        employee.setSalario("5000");

        doNothing().when(employeeService).salvarFuncionario(any(Employee.class));

        mockMvc.perform(post("/products/employee")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", String.valueOf(employee.getId()))
                        .param("nome", employee.getNome())
                        .param("cargo", employee.getCargo())
                        .param("salario", employee.getSalario()))
                .andExpect(status().is3xxRedirection());

        verify(employeeService).salvarFuncionario(any(Employee.class));
    }
}
