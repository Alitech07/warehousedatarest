package com.warehousedatarest.service;

import com.warehousedatarest.entity.Attachment;
import com.warehousedatarest.entity.Category;
import com.warehousedatarest.entity.Measurement;
import com.warehousedatarest.entity.Product;
import com.warehousedatarest.payload.ProductDto;
import com.warehousedatarest.payload.Result;
import com.warehousedatarest.repository.AttachmentRepository;
import com.warehousedatarest.repository.CategoryRepository;
import com.warehousedatarest.repository.MeasurementRepository;
import com.warehousedatarest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * BAZADAN BARCHA PRODUCTLARNI OLIB KELISH.
     * @return
     */
    public List<Product> getProductsService(){
        return productRepository.findAll();
    }

    /**
     * BAZADAN PRODUCTNI ID BO'YICHA OLIB KELISH.
     * @param id
     * @return
     */
    public Product getProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    /**
     * BAZAGA YANGI PRODUCT QO'SHISH.
     * @param productDto
     * @return
     */
    public Result addProductService(ProductDto productDto){
        boolean exists = productRepository.existsProductByCode(productDto.getCode());
        if (exists) return new Result("Bunday product mavjud.",false);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(productDto.getCode());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("Product rasmi yuklanmadi.",false);
        product.setAttachment(optionalAttachment.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurmentId());
        if (!optionalMeasurement.isPresent()) return new Result("Bunday o'lchov birligi mavjud emas.",false);
        product.setMeasurement(optionalMeasurement.get());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Bundya categoyr mavjud emas.",false);
        product.setCategory(optionalCategory.get());

        productRepository.save(product);
        return new Result("Product qo'shildi.",true);
    }

    /**
     * PRODUCT MA'LUMOTLARINI YANGILASH.
     * @param productDto
     * @param id
     * @return
     */
    public Result editProductService(ProductDto productDto,Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new Result("Bundya product mavjud emas.",false);
        Product editProduct = optionalProduct.get();
        editProduct.setName(productDto.getName());
        boolean exists = productRepository.existsProductByCodeNot(productDto.getCode());
        if (exists) return new Result("Bunday codli product mavjud",false);
        editProduct.setCode(productDto.getCode());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Bunday category mavjud emas.",false);
        editProduct.setCategory(optionalCategory.get());

        Optional<Attachment> attachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!attachment.isPresent()) return new Result("Rasm mavjud emas.",false);
        editProduct.setAttachment(attachment.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurmentId());
        if (!optionalMeasurement.isPresent()) return new Result("Bunday o'lchov birligi mavjud emas!", false);
        editProduct.setMeasurement(optionalMeasurement.get());

        productRepository.save(editProduct);
        return new Result("Product malumotlari yangilandi.",false);
    }

    /**
     * PRODUCTNI ID BO'YICHA O'CHIRISH.
     * @param id
     * @return
     */
    public Result deleteProductServoce(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new Result("Bunday Product mavjud emas!",false);
        productRepository.deleteById(id);
        return new Result("Product o'chirildi.",true);
    }
}
