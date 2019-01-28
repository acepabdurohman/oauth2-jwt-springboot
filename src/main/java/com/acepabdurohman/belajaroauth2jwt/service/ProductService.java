package com.acepabdurohman.belajaroauth2jwt.service;

import com.acepabdurohman.belajaroauth2jwt.dto.ProductRequest;
import com.acepabdurohman.belajaroauth2jwt.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    void insert(ProductRequest productRequest);
}
