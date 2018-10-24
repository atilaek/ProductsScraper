package aek.demo.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

/**
 * Result class for creating Json file
 *
 * @author Atila Ekimci
 */
public class Result {
    private final Set<Product> results;
    private final Total total;

    public Result(final Set<Product> products) {
        this.results = products;
        this.total = new Total(products);
    }

    public Set<Product> getResults() {
        return results;
    }

    public Total getTotal() {
        return total;
    }


    /**
     * Total class for holding gross and vat values of listed products for Json file
     *
     * @author Atila Ekimci
     */
    public class Total {
        private final BigDecimal gross;
        private final BigDecimal vat;

        /**
         * Total class constructor with scale level 2 for each value.
         *
         * @author Atila Ekimci
         */
        public Total(final Set<Product> products) {
            BigDecimal calculatedGross;
            calculatedGross = products.stream()
                    .map(Product::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

            this.vat = calculatedGross.multiply(new BigDecimal(0.2)).setScale(2, RoundingMode.HALF_UP);

            this.gross = calculatedGross.setScale(2, RoundingMode.HALF_UP);
        }

        public BigDecimal getGross() {
            return gross;
        }

        public BigDecimal getVat() {
            return vat;
        }
    }

}
