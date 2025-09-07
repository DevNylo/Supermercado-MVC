package com.supermarket.mvcsupermarket;

import com.supermarket.mvcsupermarket.Command.RemoveProductCommander;
import com.supermarket.mvcsupermarket.Service.ProductService;
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

public class RemoveProductCommanderTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private RemoveProductCommander removeProductCommander;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteProductSuccess() {
        when(productService.deleteProduct(1)).thenReturn(true);
        ResponseEntity<Void> response = removeProductCommander.deleteProduct(1);
        verify(productService).deleteProduct(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteProductNotFound() {
        when(productService.deleteProduct(1)).thenReturn(false);

        ResponseEntity<Void> response = removeProductCommander.deleteProduct(1);

        verify(productService).deleteProduct(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}