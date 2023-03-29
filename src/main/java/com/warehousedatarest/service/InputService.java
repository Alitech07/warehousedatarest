package com.warehousedatarest.service;

import com.warehousedatarest.entity.*;
import com.warehousedatarest.payload.InputDto;
import com.warehousedatarest.payload.OutputDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;

    /**
     * BAZADAN BARCHA KIRIMLAR TARIXINI OLISH.
     * @return INPUT TOIFASIDA LIST QAYTARADI.
     */
    public List<Input> getInputsService(){
        return inputRepository.findAll();
    }

    /**
     * ID BO'YICHA BAZADAN KIRIMLARNI OLIB KELADI.
     * @param id
     * @return INPUT TOIFASIDA BITTA KIRIMNI QAYTARADI.
     */
    public Input getInputService(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }

    /**
     * YANGI MAHSULOTLAR KIRIMINI QO'SHISH.
     * @param inputDto
     * @return
     */
    public Result addInputService(InputDto inputDto){
        Input input = new Input();
        input.setDate(inputDto.getDate());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday ombor mavjud eams.",false);
        input.setWarehouse(optionalWarehouse.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday o'lchov birligi mavjud emas!",false);
        input.setCurrency(optionalCurrency.get());

        input.setFucture_number(inputDto.getFacturNumber());
        boolean exists = inputRepository.existsInputByCode(inputDto.getCode());
        if (exists) return new Result("Bunday code mavjud.",false);
        input.setCode(inputDto.getCode());

        inputRepository.save(input);
        return new Result("Yangi mahsulotlar qo'shildi.",true);
    }

    /**
     * YANGI KIRITILGAN MAHSULOTLAR KIRIMINI TAHRIRLASH.
     * @param inputDto
     * @param id
     * @return
     */
    public Result editInputService(InputDto inputDto,Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new Result("Bunday kirim mavjud emas.",false);
        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday ombor mavjud emas.",false);
        input.setWarehouse(optionalWarehouse.get());

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) return new Result("Bunday yetkazib beruvchi mavjud emas.",false);
        input.setSupplier(optionalSupplier.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new Result("Bundya valyuta turi mavjud emas.",false);
        input.setCurrency(optionalCurrency.get());

        input.setFucture_number(inputDto.getFacturNumber());
        boolean exists = inputRepository.existsInputByCodeNot(inputDto.getCode());
        if (exists) return new Result("Bunday shtrih raqamli mahsulot mavjud.",false);
        input.setCode(inputDto.getCode());
        inputRepository.save(input);
        return new Result("Kirimlar ma'lumotlari yangilandi.",true);
    }

    /**
     * KIRIMLARNI O'CHIRISH.
     * @param id
     * @return
     */
    public Result deleteInputService(Integer id){
        Optional<Input> optionalOutput = inputRepository.findById(id);
        if (!optionalOutput.isPresent()) return  new Result("Bundya output mavjud emas.",false);
        inputRepository.deleteById(id);
        return new Result("Input o'chirildi.",true);
    }
}
