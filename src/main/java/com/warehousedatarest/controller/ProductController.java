package com.warehousedatarest.controller;

import com.warehousedatarest.entity.Product;
import com.warehousedatarest.payload.ProductDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public HttpEntity<?> getProducts(){
        List<Product> products = productService.getProductsService();
        return ResponseEntity.status(!products.isEmpty()?200:409).body(products);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Integer id){
        Product product = productService.getProduct(id);
        return ResponseEntity.status(product!=null?200:409).body(product);
    }

    @PostMapping("/add")
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProductService(productDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.editProductService(productDto, id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProductServoce(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
