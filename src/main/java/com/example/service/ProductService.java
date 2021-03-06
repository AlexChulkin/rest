package com.example.service;

/**
 * Created by alexc_000 on 2016-12-29.
 */
import com.example.domain.NameAndPrice;
import com.example.domain.Product;
import com.example.domain.TimestampAndPrice;

import java.time.Instant;
import java.util.List;

public interface ProductService {
    List<TimestampAndPrice> findProductsByProductName(String productName);
    List<NameAndPrice> findProductsByTimestamp(Instant timestamp);
    Product findProductById(long productId);
    Product saveProduct(Product product);
    void deleteProduct(Product product);
}

