package com.acepabdurohman.belajaroauth2jwt.service.impl;

import com.acepabdurohman.belajaroauth2jwt.dto.ProductRequest;
import com.acepabdurohman.belajaroauth2jwt.model.Product;
import com.acepabdurohman.belajaroauth2jwt.repository.ProductRepository;
import com.acepabdurohman.belajaroauth2jwt.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void insert(ProductRequest productRequest) {
        Product product = new Product();

        BeanUtils.copyProperties(productRequest, product);

        String price = productRequest.getPrice();
        BigDecimal priceNumber = new BigDecimal(price);

        product.setPrice(priceNumber);

        productRepository.save(product);
    }
}
