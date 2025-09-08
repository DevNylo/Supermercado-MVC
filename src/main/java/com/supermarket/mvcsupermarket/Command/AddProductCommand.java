package com.supermarket.mvcsupermarket.Command;

import com.supermarket.mvcsupermarket.Entity.Product;
import com.supermarket.mvcsupermarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductCommand {

    @Autowired
    private ProductService productService;

    public void execute(Product product) {
        productService.salvarProduto(product);
    }
}
