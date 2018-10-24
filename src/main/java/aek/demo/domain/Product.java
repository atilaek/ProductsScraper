package aek.demo.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * Product object for holding each retail product
 *
 * @author Atila Ekimci
 */
public class Product {
    private String title;
    private Optional<Integer> kcalPer100g;
    private BigDecimal unitPrice;
    private String description;

    public Product(String title, Optional<Integer> kcalPer100g, BigDecimal unitPrice, String description) {
        this.title = title;
        this.kcalPer100g = kcalPer100g;
        this.unitPrice = unitPrice.setScale(2, RoundingMode.HALF_UP);
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<Integer> getKcalPer100g() {
        return kcalPer100g;
    }

    public void setKcalPer100g(Optional<Integer> kcalPer100g) {
        this.kcalPer100g = kcalPer100g;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
