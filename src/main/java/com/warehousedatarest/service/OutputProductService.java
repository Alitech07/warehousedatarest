package com.warehousedatarest.service;

import com.warehousedatarest.entity.*;
import com.warehousedatarest.payload.InputProductDto;
import com.warehousedatarest.payload.OutputProductDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public List<OutputProduct> getOutputProductsService(){
        return outputProductRepository.findAll();
    }
    public OutputProduct getOutputProductService(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElse(null);
    }
    public Result addOutputProductService(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday mahsulot mavjud emas!",false);
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) return new Result("Bunday chiqim tarixi mavjud emas.",false);
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Mahsulot chiqimi tarixi qo'shildi.",true);
    }
    public Result editOutputProductService(OutputProductDto outputProductDto,Integer id){
        Optional<OutputProduct> outputProductOptional = outputProductRepository.findById(id);
        if (!outputProductOptional.isPresent()) return new Result("Bunday mahsulot chiqimi mavjud emas!",false);
        OutputProduct outputProduct = outputProductOptional.get();

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday mahsulto mavjud emas!",false);
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Output> outputOptional = outputRepository.findById(outputProductDto.getOutputId());
        if (!outputOptional.isPresent()) return new Result("Bundya chiqimlar tarixi mavjued emas!",false);
        outputProduct.setOutput(outputOptional.get());

        outputProductRepository.save(outputProduct);
        return new Result("Yangi mahsulot kirim malumotlari yangilandi.",true);
    }
    public Result deleteOutputProductService(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new Result("Bunday mahsulto chiqimi tarixi mavjud emas.!",false);
        outputProductRepository.deleteById(id);
        return new Result("Mahsulot chiqimi tarixi o'chirildi.",true);
    }
}
