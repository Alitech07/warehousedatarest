package com.warehousedatarest.service;

import com.warehousedatarest.entity.*;
import com.warehousedatarest.payload.OutputDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.repository.ClientRepository;
import com.warehousedatarest.repository.CurrencyRepository;
import com.warehousedatarest.repository.OutputRepository;
import com.warehousedatarest.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    /**
     * BAZADAN BARCHA CHIQIMLAR TARIXI OLISH.
     * @return
     */
    public List<Output> getOutputsService(){
        return outputRepository.findAll();
    }

    /**
     * ID BO'YICHA BAZADAN BITTA CHIQIMLAR TARIXINI OLISH.
     * @param id
     * @return
     */
    public Output getOutputService(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElse(null);
    }

    /**
     * YANGI CHIQIMLAR TARIXINI QO'SHISH.
     * @param outputDto
     * @return
     */
    public Result addOutputService(OutputDto outputDto){
        Output output = new Output();
        output.setDate(outputDto.getDate());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday ombor mavjud eams.",false);
        output.setWarehouse(optionalWarehouse.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday o'lchov birligi mavjud emas!",false);
        output.setCurrency(optionalCurrency.get());

        output.setFucture_number(outputDto.getFacturNumber());
        boolean exists = outputRepository.existsOutputByCode(outputDto.getCode());
        if (exists) return new Result("Bunday code mavjud.",false);
        output.setCode(outputDto.getCode());

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) return new Result("Bunday client mavjud emas.",false);
        output.setClient(optionalClient.get());

        outputRepository.save(output);
        return new Result("Yangi output qo'shildi.",true);
    }

    /**
     * QO'SHILGAN CHIQIMLAR TARIXI MALUMOTLARINI YANGILASH.
     * @param outputDto
     * @param id
     * @return
     */
    public Result editOutputService(OutputDto outputDto,Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Bunday chiqimlar tarixi mavjud emas.",false);
        Output output = optionalOutput.get();
        output.setDate(outputDto.getDate());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday ombor mavjud emas.",false);
        output.setWarehouse(optionalWarehouse.get());

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) return new Result("Bunday mijoz mavjud emas.",false);
        output.setClient(optionalClient.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new Result("Bundya valyuta turi mavjud emas.",false);
        output.setCurrency(optionalCurrency.get());

        output.setFucture_number(outputDto.getFacturNumber());
        boolean exists = outputRepository.existsOutputByCodeNot(outputDto.getCode());
        if (exists) return new Result("Bunday shtrih raqamli chiqimlar tarixi mavjud.",false);
        output.setCode(outputDto.getCode());
        outputRepository.save(output);
        return new Result("Chiqimlar ma'lumotlari yangilandi.",true);
    }

    /**
     * CHIQIMLAR TARIXINI YANGILASH.
     * @param id
     * @return
     */
    public Result deleteOutputService(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return  new Result("Bundya output mavjud emas.",false);
        outputRepository.deleteById(id);
        return new Result("Output o'chirildi.",true);
    }
}
