package com.supermarket.mvcsupermarket.Controller;


import com.supermarket.mvcsupermarket.Entity.Product;
import com.supermarket.mvcsupermarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @Autowired
    private ProductService productService;

    @GetMapping("/reports")
    public String generateReport(Model model) {
        int totalProducts = productService.countTotalProducts();
        Product highestPricedProduct = productService.findHighestPricedProduct();
        Product lowestPricedProduct = productService.findLowestPricedProduct();
        double totalStockValue = productService.calculateTotalStockValue();

        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("highestPricedProduct", highestPricedProduct);
        model.addAttribute("lowestPricedProduct", lowestPricedProduct);
        model.addAttribute("totalStockValue", totalStockValue);

        return "pages/report";
    }
}
