package com.warehousedatarest.controller;

import com.warehousedatarest.entity.InputProduct;
import com.warehousedatarest.entity.OutputProduct;
import com.warehousedatarest.payload.InputProductDto;
import com.warehousedatarest.payload.OutputProductDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.service.InputProductService;
import com.warehousedatarest.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public HttpEntity<?> getOutputProducts(){
        List<OutputProduct> outputProducts = outputProductService.getOutputProductsService();
        return ResponseEntity.status(!outputProducts.isEmpty()?200:409).body(outputProducts);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOutputProduct(@PathVariable Integer id){
        OutputProduct outputProduct = outputProductService.getOutputProductService(id);
        return ResponseEntity.status(outputProduct!=null?202:409).body(outputProduct);
    }
    @PostMapping("/add")
    public HttpEntity<?> addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.addOutputProductService(outputProductDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editOutputProduct(@RequestBody OutputProductDto outputProductDto,@PathVariable Integer id){
        Result result = outputProductService.editOutputProductService(outputProductDto,id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOutputProduct(@PathVariable Integer id){
        Result result = outputProductService.deleteOutputProductService(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
}
