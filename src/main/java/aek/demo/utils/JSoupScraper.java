package aek.demo.utils;

import aek.demo.domain.Product;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Scarper class for specific URL Class for json file writing.
 *
 * @author Atila Ekimci
 */
public class JSoupScraper {

    /**
     * Returns @{@link Set<Product>} based on @{@link Document}<p>
     *
     * @param doc Document of the webside to scrap from.
     * @return @{@link Set<Product>}
     */
    public static Set<Product> getProducts(Document doc) {
        Set<String> productLinks = getProductLinks(doc);
        Set<Product> products = new HashSet<>();

        productLinks.iterator().forEachRemaining(productLink -> {
            Document productDoc = getDocument(productLink);
            Product product = createProductFromDocument(productDoc);
            products.add(product);
        });

        return products;
    }

    /**
     * Returns product links of each product<p>
     * based on @{@link Document}.
     *
     * @param doc Document of the webside to scrap from.
     * @return @{@link Set<String>} set of each product link strings
     */
    private static Set<String> getProductLinks(Document doc) {
        Elements productLinkElements = doc.select("div.productInfo");
        Set<String> productLinks = new HashSet<>();

        for (Element productLinkElement : productLinkElements) {
            String link = productLinkElement.getElementsByTag("a").first().attr("abs:href");
            productLinks.add(link);
        }

        return productLinks;
    }

    /**
     * Gets the @{@link Document} of received URL.
     *
     * @param url Url of webside to scrap products from.
     * @return @{@link Document} Recieved Urls document.
     */
    public static Document getDocument(String url) {
        Connection conn = Jsoup.connect(url);
        Document doc = null;
        try {
            doc = conn.timeout(10000).get();
        } catch (Exception ignored) {

        }
        return doc;
    }

    /**
     * Creates @{@link Product} from product's @{@link Document} file.
     *
     * @param doc Products document file.
     * @return @{@link Product} Created product based on document file.
     */
    private static Product createProductFromDocument(Document doc) {
        return new Product(getTitle(doc), getKcalPer100g(doc), getUnitPrice(doc), getDescription(doc));
    }

    /**
     * Gets title field from product's @{@link Document}
     *
     * @param doc Products document file.
     * @return @{@link String} Title of the product.
     */
    private static String getTitle(final Document doc) {
        final Elements productTitle = doc.select("div.productTitleDescriptionContainer");

        if (productTitle.isEmpty()) {
            return "";
        }

        return productTitle.select("h1").first().text();
    }

    /**
     * Gets kcal based energy level of product<p>
     * if it exists.
     *
     * @param doc Products document file.
     * @return @{@link Optional} Nullable/Optional kcal based energy value.
     */
    private static Optional<Integer> getKcalPer100g(Document doc) {
        Elements nutritionLevels = doc.select("productcontent").select("htmlcontent")
                .select("div.tableWrapper").select("table.nutritionTable").select("tr.tableRow0");

        if (nutritionLevels.isEmpty()) {
            return null;
        }

        String input = nutritionLevels.first().text();
        input = input.substring(0, input.indexOf("kcal"));
        return Optional.of(Integer.parseInt(input));
    }

    /**
     * Gets unit price from product's @{@link Document}
     *
     * @param doc Products document file.
     * @return @{@link BigDecimal} Unit price of the product.
     */
    private static BigDecimal getUnitPrice(final Document doc) {
        final Elements pricePerUnit = doc.select("p.pricePerUnit");

        if (pricePerUnit.isEmpty()) {
            return new BigDecimal(-1);
        }

        String text = pricePerUnit.first().text()
                .replace("£", "")
                .replace("/unit", "");
        return new BigDecimal(text);
    }

    /**
     * Gets first line of description from product's @{@link Document}
     *
     * @param doc Products document file.
     * @return @{@link String} description of the product.
     */
    private static String getDescription(final Document doc) {
        Elements productText = doc.select("div.ProductText").select("div.memo");

        if (productText.isEmpty()) {
            if (doc.select("div.ProductText").isEmpty()) {
                return "";
            } else {
                productText = doc.select("div.ProductText");
            }
        }

        return productText.first().text();
    }

}
