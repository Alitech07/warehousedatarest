package com.warehousedatarest.service;

import com.warehousedatarest.entity.Input;
import com.warehousedatarest.entity.InputProduct;
import com.warehousedatarest.entity.Product;
import com.warehousedatarest.payload.InputProductDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.repository.InputProductRepository;
import com.warehousedatarest.repository.InputRepository;
import com.warehousedatarest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputRepository inputRepository;

    public List<InputProduct> getInputProductsService(){
        return inputProductRepository.findAll();
    }
    public InputProduct getInputProductService(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }
    public Result addInputProductService(InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday mahsulot mavjud emas!",false);
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setDate(inputProductDto.getExpireDate());

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) return new Result("Bunday kirmlar tarixi mavjud emas.",false);
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Yangi mahsulot qo'shilish taarixi kiritldi.",true);
    }
    public Result editInputProductService(InputProductDto inputProductDto,Integer id){
        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);
        if (!inputProductOptional.isPresent()) return new Result("Bunday mahsulot kirimi mavjud emas!",false);
        InputProduct inputProduct = inputProductOptional.get();

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday mahsulto mavjud emas!",false);
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setDate(inputProductDto.getExpireDate());

        Optional<Input> inputOptional = inputRepository.findById(inputProductDto.getInputId());
        if (!inputOptional.isPresent()) return new Result("Bundya kirimlar tarixi mavjued emas!",false);
        inputProduct.setInput(inputOptional.get());
        inputProductRepository.save(inputProduct);
        return new Result("Yangi mahsulot kirim malumotlari yangilandi.",true);
    }
    public Result deleteInputProductService(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) return new Result("Bunday mahsulto kirimi tarixi mavjud emas.!",false);
        inputProductRepository.deleteById(id);
        return new Result("Yangi Mahsulot kirimi o'chirildi.",true);
    }
}
