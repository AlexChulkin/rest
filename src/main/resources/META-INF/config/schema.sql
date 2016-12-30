DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    "Id" INT(11) NOT NULL AUTO_INCREMENT
    , "Product name" VARCHAR(60) NOT NULL
    , "Timestamp" TIMESTAMP NOT NULL
    , "Price" DECIMAL(14,2) NOT NULL
    , "Version" INT NOT NULL DEFAULT 0
    , UNIQUE UQ_PRODUCT_1 ("Product name", "Timestamp")
    , PRIMARY KEY ("Product name", "Timestamp")
);
