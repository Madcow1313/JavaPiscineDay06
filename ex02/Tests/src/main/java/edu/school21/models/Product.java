package edu.school21.models;

import java.util.Objects;

public class Product {
    Long identifier;
    String name;
    Long price;

    public Product(Long identifier, String name, Long price){
        this.identifier = identifier;
        this.name = name;
        this.price = price;
    }

    public Long getIdentifier(){
        return identifier;
    }

    public Long getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(identifier, product.identifier) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, price);
    }
}
