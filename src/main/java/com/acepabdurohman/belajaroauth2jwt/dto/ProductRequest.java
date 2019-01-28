package com.acepabdurohman.belajaroauth2jwt.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String price;

    private String imageName;
}