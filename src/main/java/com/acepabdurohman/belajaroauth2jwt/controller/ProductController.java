package com.acepabdurohman.belajaroauth2jwt.controller;

import com.acepabdurohman.belajaroauth2jwt.dto.MyResponse;
import com.acepabdurohman.belajaroauth2jwt.dto.ProductRequest;
import com.acepabdurohman.belajaroauth2jwt.model.Product;
import com.acepabdurohman.belajaroauth2jwt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableResourceServer
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(
                productService.getAll()
        );
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity save(@Valid @RequestBody ProductRequest request){
        productService.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MyResponse("Data Created"));
    }
}