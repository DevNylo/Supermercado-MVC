package com.supermarket.mvcsupermarket.Command;

import com.supermarket.mvcsupermarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductCommand {

    @Autowired
    private ProductService productService;

    public boolean execute(Integer id) {
        return productService.deleteProduct(id);
    }
}