package com.example.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.Instant;

/**
 * Created by alexc_000 on 2016-12-29.
 */
@NamedQueries({
        @NamedQuery(name = "Product.findProductsByProductName",
                query = "SELECT NEW com.example.domain.TimestampAndPrice(p.timestamp, p.price) " +
                        "FROM Product p WHERE p.productName = :productName"),
        @NamedQuery(name = "Product.findProductsByTimestamp",
                query = "SELECT NEW com.example.domain.NameAndPrice(p.productName, p.price) " +
                        "FROM Product p WHERE p.timestamp = :timestamp"),
})
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;
    private Instant timestamp;
    private BigDecimal price;
    private int version;
    private long id;

    @NotNull
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "\"Id\"", length = 11)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotBlank
    @Column(name = "\"Product name\"")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    @NotNull
    @Column(name = "\"Timestamp\"")
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @NotNull
    @Column(name = "\"Price\"")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Version
    @Column(name = "\"Version\"")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}

