package com.example;

import com.example.domain.Product;
import com.example.domain.Products;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class RestApplication {
		private static final String URL_FIND_PRODUCTS_BY_PRODUCT_NAME =
				"http://localhost:8080/restful/product/pn/{pn}";
		private static final String URL_FIND_PRODUCTS_BY_TIMESTAMP =
				"http://localhost:8080/restful/product/ts/{ts}";
		private static final String URL_FIND_PRODUCT_BY_ID =
				"http://localhost:8080/restful/product/id/{pn}/{ts}";
		private static final String URL_CREATE_PRODUCT =
				"http://localhost:8080/restful/product/";
		private static final String URL_UPDATE_PRODUCT =
				"http://localhost:8080/restful/product/{ts}/{pn}";
		private static final String URL_DELETE_PRODUCT =
				"http://localhost:8080/restful/product/{ts}/{pn}";

		public static void main(String[] args) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load("classpath:META-INF/spring/restful-client-app-context.xml");
			ctx.refresh();

			RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

			String productName = "Potatoes";
			System.out.println("Testing retrieve the products by product name:");
			Products productsByName = restTemplate.getForObject(URL_FIND_PRODUCTS_BY_PRODUCT_NAME, Products.class, productName);
			listProducts(productsByName);

			String timeStamp = "2017-01-01T00:00:00";
			System.out.println("Testing retrieve the products by timestamp:");
			Products productsByTimestamp  = restTemplate.getForObject(URL_FIND_PRODUCTS_BY_TIMESTAMP, Products.class, timeStamp);
			listProducts(productsByTimestamp);
			System.out.println("");

			Product product;
			product = restTemplate.getForObject(URL_FIND_PRODUCT_BY_ID, Product.class, productName, timeStamp);
			product.setPrice(BigDecimal.TEN);
			System.out.println("Testing update product by id :");
			restTemplate.put(URL_UPDATE_PRODUCT, product, productName, timeStamp);
			product = restTemplate.getForObject(URL_FIND_PRODUCT_BY_ID, Product.class, productName, timeStamp);
			System.out.println("Product update successfully: " + product);
			System.out.println("");

			restTemplate.delete(URL_DELETE_PRODUCT, productName, timeStamp);
			System.out.println("Testing delete product by id :");

			Products productsByNameAfterDel = restTemplate.getForObject(URL_FIND_PRODUCTS_BY_PRODUCT_NAME,
					Products.class, productName);
			listProducts(productsByNameAfterDel);
//			boolean listContainsDeletedEntity = productsByNameAfterDel.getProducts().contains(product);
//			System.out.println("The \"by name\" list" + (listContainsDeletedEntity ? "contains" : "doesn't contain")
//			 + "deleted entity");

			Products productsByTimestampAfterDel = restTemplate.getForObject(URL_FIND_PRODUCTS_BY_TIMESTAMP,
					Products.class, timeStamp);
			listProducts(productsByTimestampAfterDel);
//			listContainsDeletedEntity = productsByTimestampAfterDel.getProducts().contains(product);
//			System.out.println("The \"by timestamp\"  list" +
//					(listContainsDeletedEntity ? "contains" : "doesn't contain") + "deleted entity");

			System.out.println("Testing create product :");
			Product productNew = new Product();
			String productNameNew = "Cucumbers";
			Instant timestampNew = OffsetDateTime
					.parse("2018-01-01T09:12:12", DateTimeFormatter.ISO_DATE_TIME).toInstant();
			productNew.setTimestamp(timestampNew);
			productNew.setProductName(productNameNew);
			productNew.setPrice(BigDecimal.valueOf(13.99));
			productNew = restTemplate.postForObject(URL_CREATE_PRODUCT, productNew, Product.class);
			System.out.println("Product created successfully: " + productNew);

			Products productsByTimestampAfterCreate = restTemplate.getForObject(URL_FIND_PRODUCTS_BY_TIMESTAMP,
					Products.class, timeStamp);
			listProducts(productsByTimestampAfterCreate);
//			boolean listContainsCreatedEntity = productsByTimestampAfterCreate.getProducts().contains(product);
//			System.out.println("The \"by timestamp\"  list" +
//					(listContainsCreatedEntity ? "contains" : "doesn't contain") + "created entity");

			Products productsByProductNameAfterCreate = restTemplate.getForObject(URL_FIND_PRODUCTS_BY_TIMESTAMP,
					Products.class, timeStamp);
			listProducts(productsByProductNameAfterCreate);
//			listContainsCreatedEntity = productsByProductNameAfterCreate.getProducts().contains(product);
//			System.out.println("The \"by product name\"  list" +
//					(listContainsCreatedEntity ? "contains" : "doesn't contain") + "created entity");
		}

		private static void listProducts(Products products) {
			products.getNameAndPriceList().forEach(System.out::println);
			products.getTimestampAndPriceList().forEach(System.out::println);

			System.out.println("");
		}
	}

