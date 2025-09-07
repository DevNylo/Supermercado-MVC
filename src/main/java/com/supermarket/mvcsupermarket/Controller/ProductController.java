package com.supermarket.mvcsupermarket.Controller;

import com.supermarket.mvcsupermarket.Command.AddProductCommand;
import com.supermarket.mvcsupermarket.Command.RemoveProductCommand;
import com.supermarket.mvcsupermarket.Entity.Product;
import com.supermarket.mvcsupermarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private RemoveProductCommand removeProductCommand;
    @Autowired
    private AddProductCommand addProductCommand;

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        addProductCommand.execute(product);
        return "redirect:/products";
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.listProduct();
        model.addAttribute("products", products);
        return "pages/produtos";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("query") String query, Model model) {
        List<Product> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        return "pages/produtos";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            existingProduct.setNome(updatedProduct.getNome());
            existingProduct.setDescricao(updatedProduct.getDescricao());
            existingProduct.setPreco(updatedProduct.getPreco());
            existingProduct.setSKU(updatedProduct.getSKU());
            existingProduct.setFabricacao(updatedProduct.getFabricacao());

            productService.salvarProduto(existingProduct);
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (removeProductCommand.execute(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

