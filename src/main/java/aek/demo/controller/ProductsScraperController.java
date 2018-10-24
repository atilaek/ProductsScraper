package aek.demo.controller;

import aek.demo.domain.Product;
import aek.demo.utils.JSoupScraper;
import org.jsoup.nodes.Document;

import java.util.Set;

/**
 * ProductScraperController for product scraping related operations.
 *
 * @author Atila Ekimci
 */
public class ProductsScraperController {

    /**
     * Returns a set of Products from URL <p>
     * using JSoup operations of @{@link JSoupScraper}.<p>
     * Get Url to scrap products from.
     *
     * @param url Url of webside to scrap products from.
     * @return @{@link Set<Product>}
     */
    public static Set<Product> getProductsFromUrl(String url) {
        Document doc = JSoupScraper.getDocument(url);
        return JSoupScraper.getProducts(doc);
    }
}
