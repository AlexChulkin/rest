package com.example.controller;

import com.example.domain.Product;
import com.example.domain.Products;
import com.example.handler.DateTimeFieldHandler;
import com.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

/**
 * Created by alexc_000 on 2016-12-29.
 */
@Controller
@RequestMapping(value="/product")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/pn/{pn}", method = RequestMethod.GET)
    @ResponseBody
    public Products findProductsByProductName(@PathVariable("pn")  String productName) {
        logger.info("Finding products with product name: " + productName);
        Products result = new Products().setTimestampAndPriceList(productService.findProductsByProductName(productName));
        logger.info("Successfully found products with product name: " + productName);
        return result;
    }

    @RequestMapping(value="/ts/{ts}", method=RequestMethod.GET)
    @ResponseBody
    public Products findProductsByTimestamp(
            @PathVariable("ts") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String timestampString) {
        Instant timestamp = DateTimeFieldHandler.parse(timestampString);
        logger.info("Finding products with timestamp: " + timestampString);
        Products result = new Products().setNameAndPriceList(productService.findProductsByTimestamp(timestamp));
        logger.info("Successfully found products with timestamp: " + timestampString);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product findProductById(@PathVariable("id")  long id) {
        logger.info("Finding product by id: " + id);
        Product result =  productService.findProductById(id);
        logger.info("Successfully found product with id: " + id);
        return result;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @ResponseBody
    public Product create(@Valid @RequestBody Product product) {
        logger.info("Creating product: " + product);
        productService.saveProduct(product);
        logger.info("Successfully created product: " + product);
        return product;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public void update(@Valid @RequestBody Product product,
                       @PathVariable("id") long id) {
        logger.info("Updating product: " + product);
        productService.saveProduct(product);
        logger.info("Product updated successfully with info: " + product);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") long id) {
        logger.info("Deleting product with id: " + id);
        Product product = productService.findProductById(id);
        productService.deleteProduct(product);
        logger.info("Product deleted successfully");
    }
}



