package com.supermarket.mvcsupermarket;

import com.supermarket.mvcsupermarket.Command.RemoveEmployeeCommand;
import com.supermarket.mvcsupermarket.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RemoveEmployeeCommandTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private RemoveEmployeeCommand removeEmployeeCommand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteEmployeeSuccess() {
        when(employeeService.deleteFuncionario(1)).thenReturn(true);

        ResponseEntity<Void> response = removeEmployeeCommand.deleteEmployee(1);

        verify(employeeService).deleteFuncionario(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        // Mock do serviço para indicar que o funcionário não foi encontrado
        when(employeeService.deleteFuncionario(1)).thenReturn(false);

        // Chamar o método do controlador para excluir um funcionário
        ResponseEntity<Void> response = removeEmployeeCommand.deleteEmployee(1);

        // Verificar se o método do serviço foi chamado corretamente
        verify(employeeService).deleteFuncionario(1);

        // Verificar se a resposta HTTP está correta (notFound)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}