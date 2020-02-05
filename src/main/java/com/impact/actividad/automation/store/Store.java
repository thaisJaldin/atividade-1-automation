package com.impact.actividad.automation.store;

import java.util.HashMap;
import java.util.Map;


public class Store {

    private Map<String, Product> products;

    private static String CODE_OBLIGATORY = "Product code is obligatory.";
    private static String CODE_NAME = "Product name is obligatory.";
    private static String CODE_PRICE = "Product price is obligatory.";

    public Store() {
        products = new HashMap<String, Product>();
    }

    public String addNewProduct(String code, String name, Double price) {
        if (null == code || code.isEmpty()) {
            return CODE_OBLIGATORY;
        }

        if (null != products.get(code)) {
            return "Already exist a product with code " + code + ", please add other code.";
        }

        if (null == name || name.isEmpty()) {
            return CODE_NAME;
        }

        if (null == price) {
            return CODE_PRICE;
        }

        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setPrice(price);
        product.setStock(1);

        products.put(code, product);
        return "The new product was added";
    }

    public String addExistProduct(String code) {
        if (null == code || code.isEmpty()) {
            return CODE_OBLIGATORY;
        }

        if (null == products.get(code)) {
            return "Not exist product with code " + code + ", please first add New Product.";
        }

        Product product = products.get(code);
        product.setStock(product.getStock() + 1);
        products.put(code, product);

        return "The product was added.";
    }

    public String updateName(String code, String name) {
        if (null == code || code.isEmpty()) {
            return CODE_OBLIGATORY;
        }

        if (null == name || name.isEmpty()) {
            return CODE_NAME;
        }

        Product product = products.get(code);
        product.setName(name);
        products.put(code, product);

        return "The name of the product with code " + code + " was update.";
    }

    public String updatePrice(String code, Double price) {
        if (null == code || code.isEmpty()) {
            return CODE_OBLIGATORY;
        }

        if (null == price) {
            return CODE_PRICE;
        }

        Product product = products.get(code);
        product.setPrice(price);
        products.put(code, product);

        return "The price of the product with code " + code + " was update.";
    }

    public String sellProduct(String code, Integer quantity) {
        if (null == code || code.isEmpty()) {
            return CODE_OBLIGATORY;
        }

        if (null == quantity) {
            return "Quantity param is obligatory.";
        }

        Product product = products.get(code);
        Integer stock = product.getStock();
        if (stock < quantity) {
            return "Can not sell " + quantity + " because you have a stock of " + stock + ".";
        }
        product.setStock(stock - quantity);
        products.put(code, product);

        return "Success Sell. You have " + stock + " unity of this product in stock.";
    }

    public String getStockOfProduct(String code) {
        if (null == code || code.isEmpty()) {
            return CODE_OBLIGATORY;
        }

        return products.get(code).getStock().toString();
    }

    public Product getProduct(String code) {
        if (null == code || code.isEmpty()) {
            return null;
        }

        return products.get(code);
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
