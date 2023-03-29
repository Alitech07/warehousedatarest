package com.warehousedatarest.controller;

import com.warehousedatarest.entity.Input;
import com.warehousedatarest.entity.Output;
import com.warehousedatarest.payload.InputDto;
import com.warehousedatarest.payload.OutputDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.service.InputService;
import com.warehousedatarest.service.OutputService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping
    public HttpEntity<?> getOutputs(){
        List<Output> outputs = outputService.getOutputsService();
        return ResponseEntity.status(!outputs.isEmpty()?200:409).body(outputs);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOutput(@PathVariable Integer id){
        Output output = outputService.getOutputService(id);
        return ResponseEntity.status(output!=null?202:409).body(output);
    }
    @PostMapping("/add")
    public HttpEntity<?> addOutput(@Valid @RequestBody OutputDto outputDto){
        Result result = outputService.addOutputService(outputDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editOutput(@RequestBody OutputDto outputDto,@PathVariable Integer id){
        Result result = outputService.editOutputService(outputDto,id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOutput(@PathVariable Integer id){
        Result result = outputService.deleteOutputService(id);
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
