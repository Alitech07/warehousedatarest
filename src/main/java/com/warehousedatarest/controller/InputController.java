package com.warehousedatarest.controller;

import com.warehousedatarest.entity.Input;
import com.warehousedatarest.payload.InputDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.service.InputService;
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
@RequestMapping("/api/input")
public class InputController {
    @Autowired
    InputService inputService;

    @GetMapping
    public HttpEntity<?> getInputs(){
        List<Input> inputs = inputService.getInputsService();
        return ResponseEntity.status(!inputs.isEmpty()?200:409).body(inputs);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getInput(@PathVariable Integer id){
        Input input = inputService.getInputService(id);
        return ResponseEntity.status(input!=null?202:409).body(input);
    }
    @PostMapping("/add")
    public HttpEntity<?> addInput(@Valid @RequestBody InputDto inputDto){
        Result result = inputService.addInputService(inputDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editInput(@RequestBody InputDto inputDto,@PathVariable Integer id){
        Result result = inputService.editInputService(inputDto, id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInput(@PathVariable Integer id){
        Result result = inputService.deleteInputService(id);
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
