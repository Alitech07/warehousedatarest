package com.warehousedatarest.controller;

import com.warehousedatarest.entity.Input;
import com.warehousedatarest.entity.InputProduct;
import com.warehousedatarest.payload.InputDto;
import com.warehousedatarest.payload.InputProductDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.service.InputProductService;
import com.warehousedatarest.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public HttpEntity<?> getInputProducts(){
        List<InputProduct> inputProducts = inputProductService.getInputProductsService();
        return ResponseEntity.status(!inputProducts.isEmpty()?200:409).body(inputProducts);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getInputProduct(@PathVariable Integer id){
        InputProduct inputProduct = inputProductService.getInputProductService(id);
        return ResponseEntity.status(inputProduct!=null?202:409).body(inputProduct);
    }
    @PostMapping("/add")
    public HttpEntity<?> addInputProduct(@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.addInputProductService(inputProductDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editInputProduct(@RequestBody InputProductDto inputProductDto,@PathVariable Integer id){
        Result result = inputProductService.editInputProductService(inputProductDto,id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInputProduct(@PathVariable Integer id){
        Result result = inputProductService.deleteInputProductService(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
}
