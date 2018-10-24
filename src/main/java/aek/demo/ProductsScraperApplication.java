package aek.demo;

import aek.demo.controller.ProductsScraperController;
import aek.demo.domain.Result;
import aek.demo.domain.Product;
import aek.demo.utils.JsonWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class ProductsScraperApplication {

    private static String URL = "https://jsainsburyplc.github.io/serverside-test/site/" +
            "www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public static void main(String[] args) {
        SpringApplication.run(ProductsScraperApplication.class, args);

        Set<Product> products = ProductsScraperController.getProductsFromUrl(URL);
        Result result = new Result(products);
        JsonWriter.writeToJsonFile(result);
    }
}
