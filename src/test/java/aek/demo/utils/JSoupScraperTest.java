package aek.demo.utils;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class JSoupScraperTest {
    private static String documentEmpty = "";
    private static String documentWithVariables = "<!doctype html><!doctype html>\n" +
            "<html class=\"noJs\" xmlns:wairole=\"http://www.w3.org/2005/01/wai-rdf/GUIRoleTaxonomy#\"\n" +
            "      xmlns:waistate=\"http://www.w3.org/2005/07/aaa\" lang=\"en\" xml:lang=\"en\"> <!-- BEGIN ProductDisplay.jsp -->  \n" +
            "<!-- Mirrored from www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 08 Aug 2017 08:45:15 GMT -->\n" +
            " <!-- Added by HTTrack -->  \n" +
            "<head> \n" +
            "</head>\n" +
            "  \n" +
            "<body id=\"productDetails\">  \n" +
            "<div id=\"page\">    <!-- Header Nav Start -->    <!-- BEGIN LayoutContainerTop.jspf -->    \n" +
            "\n" +
            "     \n" +
            "    <div id=\"main\">     <!-- Content Start -->    \n" +
            "        <div class=\"article\" id=\"content\">      <!--  Breadcrumb include to go here -->      \n" +
            "            \n" +
            "                  <!--  END BreadCrumbTrailDisplay.jsp -->       \n" +
            "            <div class=\"section productContent\">       <!-- BEGIN MessageDisplay.jspf -->     \n" +
            "                <!-- END MessageDisplay.jspf -->      \n" +
            "                <div class=\"errorBanner hidden\" id=\"error124183\"></div>\n" +
            "                       <!-- BEGIN CachedProductOnlyDisplay.jsp -->      \n" +
            "aaaaa                <div class=\"pdp\">        \n" +
            "                    <div class=\"productSummary\">        \n" +
            "                        <div class=\"productTitleDescriptionContainer\">          <h1>Sainsbury's Strawberries 400g</h1>  \n" +
            "                                   \n" +
            "                            <div id=\"productImageHolder\">           <img\n" +
            "                                    src=\"../../../../wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/product_images/2017/May/media_7555699_L.jpg\"\n" +
            "                                    alt=\"Image for Sainsbury's Strawberries 400g from Sainsbury's\" class=\"productImage \"\n" +
            "                                    id=\"productImageID\">          \n" +
            "                            </div>\n" +
            "                                      \n" +
            "                            <div class=\"reviews\">          <!-- BEGIN CatalogEntryRatingsReviewsInfoDetailsPage.jspf -->\n" +
            "                                         <!-- END CatalogEntryRatingsReviewsInfoDetailsPage.jspf -->           \n" +
            "                            </div>\n" +
            "                                    \n" +
            "                        </div>\n" +
            "                                \n" +
            "                        <div class=\"addToTrolleytabBox\">          <!-- Start UserSubscribedOrNot.jspf -->          \n" +
            "                            <!-- Start UserSubscribedOrNot.jsp -->          \n" +
            "                            <!--  If the user is not logged in, render this opening  DIV adding an addtional class to fix the border top which is removed  and replaced by the tabs -->\n" +
            "                                      \n" +
            "                            <div class=\"addToTrolleytabContainer addItemBorderTop\">          \n" +
            "                                <!-- End AddToSubscriptionList.jsp -->          <!-- End AddSubscriptionList.jspf -->   \n" +
            "                                     \n" +
            "                                <!--      ATTENTION!!!     <div class=\"addToTrolleytabContainer\">     This opening div is inside \"../../ReusableObjects/UserSubscribedOrNot.jsp\"     -->\n" +
            "                                          \n" +
            "                                <div class=\"pricingAndTrolleyOptions\">            \n" +
            "                                    <div class=\"priceTab activeContainer priceTabContainer\" id=\"addItem_124183\">        \n" +
            "                                            <!-- CachedProductOnlyDisplay.jsp -->            \n" +
            "aaaa                                        <div class=\"pricing\">              <p class=\"pricePerUnit\"> £1.75<abbr\n" +
            "                                                title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
            "                                        </p>              <p class=\"pricePerMeasure\">£4.38<abbr\n" +
            "                                                title=\"per\">/</abbr><abbr title=\"kilogram\"><span\n" +
            "                                                class=\"pricePerMeasureMeasure\">kg</span></abbr></p>            \n" +
            "                                        </div>\n" +
            "                                                    \n" +
            "                                        <div class=\"addToTrolleyForm \">              <!-- BEGIN AddToTrolley.jsp -->    \n" +
            "                                                     <!-- fire an on add to bag here from  -->               \n" +
            "                                            <form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124183\"\n" +
            "                                                  action=\"http://www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/OrderItemAdd\"\n" +
            "                                                  method=\"post\" id=\"OrderItemAddForm_124183\">               <input\n" +
            "                                                    type=\"hidden\" name=\"storeId\" value=\"10151\">               <input\n" +
            "                                                    type=\"hidden\" name=\"langId\" value=\"44\">               <input\n" +
            "                                                    type=\"hidden\" name=\"catalogId\" value=\"10123\">               <input\n" +
            "                                                    type=\"hidden\" name=\"errorViewName\" value=\"ProductDisplayView\">      \n" +
            "                                                        <input type=\"hidden\" name=\"SKU_ID\" value=\"7555699\">            \n" +
            "                                                  <input type=\"hidden\" name=\"itemType\" value=\"\">               <input\n" +
            "                                                        type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555699\">              \n" +
            "                                                <label class=\"access\" for=\"quantity_124182\">Quantity</label>            \n" +
            "                                                  <input name=\"quantity\" id=\"quantity_124182\" type=\"text\" size=\"3\"\n" +
            "                                                         value=\"1\" class=\"quantity\">               <input type=\"hidden\"\n" +
            "                                                                                                          name=\"catEntryId\"\n" +
            "                                                                                                          value=\"124183\">  \n" +
            "                                                            <input type=\"hidden\" name=\"productId\" value=\"124182\">      \n" +
            "                                                        <input type=\"hidden\" name=\"URL\"\n" +
            "                                                               value=\"http://www.sainsburys.co.uk/shop/ProductDisplay?catalogId=10123&amp;level=2&amp;errorViewName=ProductDisplayErrorView&amp;langId=44&amp;categoryId=12563&amp;productId=124182&amp;storeId=10151\">  \n" +
            "                                                            <input type=\"hidden\" name=\"page\" value=\"\">              \n" +
            "                                                <input type=\"hidden\" name=\"contractId\" value=\"\">               <input\n" +
            "                                                        type=\"hidden\" name=\"calculateOrder\" value=\"1\">              \n" +
            "                                                <input type=\"hidden\" name=\"calculationUsage\" value=\"-1,-2,-3\">          \n" +
            "                                                    <input type=\"hidden\" name=\"updateable\" value=\"1\">              \n" +
            "                                                <input type=\"hidden\" name=\"merge\" value=\"***\">               <input\n" +
            "                                                        type=\"hidden\" name=\"callAjax\" value=\"false\">              \n" +
            "                                                <input class=\"button process\" type=\"submit\" name=\"Add\" value=\"Add\">    \n" +
            "                                                         \n" +
            "                                            </form>\n" +
            "                                                          <!-- END AddToTrolley.jsp -->               \n" +
            "                                            <div class=\"numberInTrolley hidden numberInTrolley_124182\"\n" +
            "                                                 id=\"numberInTrolley_124182\">              \n" +
            "                                            </div>\n" +
            "                                                        \n" +
            "                                        </div>\n" +
            "                                                    \n" +
            "                                    </div>\n" +
            "                                                <!-- Start AddToSubscriptionList.jspf -->            \n" +
            "                                    <!-- Start AddToSubscriptionList.jsp -->            \n" +
            "                                    <!-- End AddToSubscriptionList.jsp -->            \n" +
            "                                    <!-- End AddToSubscriptionList.jspf -->           \n" +
            "                                </div>\n" +
            "                                          <!-- End pricingAndTrolleyOptions -->          \n" +
            "                            </div>\n" +
            "                                     <!-- End addToTrolleytabContainer -->         \n" +
            "                        </div>\n" +
            "                                \n" +
            "                        <div class=\"BadgesContainer\">          \n" +
            "                            <div class=\"roundelContainer\">          </div>\n" +
            "                                    \n" +
            "                        </div>\n" +
            "                                \n" +
            "                        <div id=\"sitecatalyst_ESPOT_NAME_WF_013_eSpot_1\" class=\"siteCatalystTag\">       \n" +
            "                             WF_013_eSpot_1       \n" +
            "                        </div>\n" +
            "                                \n" +
            "                    </div>\n" +
            "                            <!--ThumbnailRestriction -->         \n" +
            "                    <div class=\"alcoholRestrictionsWrapper\">        </div>\n" +
            "                          \n" +
            "                </div>\n" +
            "                      \n" +
            "                <div class=\"mainProductInfoWrapper\">        \n" +
            "                    <div class=\"mainProductInfo\">         <p class=\"itemCode\"> Item code: 7555699 </p>        \n" +
            "                        <div class=\"socialLinks\">          <h2 class=\"access\">Social Links (may open in a new\n" +
            "                            window)</h2>          \n" +
            "                            <ul>          \n" +
            "                                <li class=\"twitter\"><a\n" +
            "                                        href=\"https://twitter.com/share?text=Check%20this%20out&amp;url=http://www.sainsburys.co.uk/shop/gb/groceries/sainsburys-british-strawberries-400g\"\n" +
            "                                        target=\"_blank\"><span>Tweet</span> <span class=\"access\">on Twitter</span></a>\n" +
            "                                </li>\n" +
            "                                          \n" +
            "                                <li class=\"facebook\">\n" +
            "                                    <iframe src=\"http://www.facebook.com/plugins/like.php?href=http://www.sainsburys.co.uk/shop/gb/groceries/sainsburys-british-strawberries-400g&amp;send=false&amp;layout=button_count&amp;width=90&amp;show_faces=false&amp;action=like&amp;colorscheme=light&amp;font&amp;height=21\"\n" +
            "                                            scrolling=\"no\" frameborder=\"0\" allowtransparency=\"true\"></iframe>\n" +
            "                                </li>\n" +
            "                                          \n" +
            "                            </ul>\n" +
            "                                    \n" +
            "                        </div>\n" +
            "                                \n" +
            "                        <div class=\"tabs\">          \n" +
            "                            <ul class=\"tabLinks\">          \n" +
            "                                <li class=\"first\"><a href=\"#information\" class=\"currentTab\">Information</a></li>\n" +
            "                                          \n" +
            "                            </ul>\n" +
            "                                      \n" +
            "                            <div class=\"section\" id=\"information\">           <h2 class=\"access\">Information</h2>        \n" +
            "                                 \n" +
            "aaaa                                <productcontent xmlns:a=\"http://www.inspire-js.com/SOL\">            \n" +
            "                                    <htmlcontent contentpath=\"/Content/media/html/products/label//_label_inspire.html\"\n" +
            "                                                 outputmethod=\"xhtml\">             <h3 class=\"productDataItemHeader\">\n" +
            "                                        Description</h3>            \n" +
            "aaaa                                        <div class=\"productText\">              <p>by Sainsbury's strawberries</p>      \n" +
            "                                                   <p></p>             <p></p>              <p></p>            \n" +
            "                                        </div>\n" +
            "                                                     <h3 class=\"productDataItemHeader\">Nutrition</h3>            \n" +
            "                                        <div class=\"productText\">              \n" +
            "                                            <div>               <p><strong>Table of Nutritional Information</strong></p>\n" +
            "                                                              \n" +
            "                                                <div class=\"tableWrapper\">                \n" +
            "                                                    <table class=\"nutritionTable\">                \n" +
            "                                                        <thead>                  \n" +
            "                                                        <tr class=\"tableTitleRow\">                  \n" +
            "                                                            <th scope=\"col\">Typical Values</th>\n" +
            "                                                                             \n" +
            "                                                            <th scope=\"col\">Per 100g&nbsp;</th>\n" +
            "                                                                             \n" +
            "                                                            <th scope=\"col\">% based on RI for Average Adult</th>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                        \n" +
            "                                                        </thead>\n" +
            "                                                                        \n" +
            "                                                        <tbody>                 \n" +
            "                                                        <tr class=\"tableRow1\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\" rowspan=\"2\">Energy</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow1\">140kJ</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow1\">-</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow0\">                  \n" +
            "                                                            <td class=\"nutritionLevel1\">33kcal</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">2%</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow1\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Fat</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">&lt;0.5g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">-</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow0\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Saturates</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">&lt;0.1g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">-</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow1\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Carbohydrate</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow1\">6.1g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow1\">2%</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow0\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Total Sugars</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel2\">6.1g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel2\">7%</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow1\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Fibre</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow1\">1.0g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow1\">-</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow0\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Protein</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow0\">0.6g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"tableRow0\">1%</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                          \n" +
            "                                                        <tr class=\"tableRow1\">                  \n" +
            "                                                            <th scope=\"row\" class=\"rowHeader\">Salt</th>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">&lt;0.01g</td>\n" +
            "                                                                             \n" +
            "                                                            <td class=\"nutritionLevel1\">-</td>\n" +
            "                                                                              \n" +
            "                                                        </tr>\n" +
            "                                                                        \n" +
            "                                                        </tbody>\n" +
            "                                                                       \n" +
            "                                                    </table>\n" +
            "                                                                  \n" +
            "                                                </div>\n" +
            "                                                               <p>RI= Reference Intakes of an average adult (8400kJ /\n" +
            "                                                    2000kcal)</p>              \n" +
            "                                            </div>\n" +
            "                                                        \n" +
            "                                        </div>\n" +
            "                                                     <h3 class=\"productDataItemHeader\">Ingredients</h3>            \n" +
            "                                        <div class=\"productText\">              <p>Strawberry&nbsp; This product conforms\n" +
            "                                            to the Sainsbury's Produce Sourcing Policy VP001&nbsp; </p>            \n" +
            "                                        </div>\n" +
            "                                                     <h3 class=\"productDataItemHeader\">Country of Origin</h3>          \n" +
            "                                         \n" +
            "                                        <div class=\"productText\">             <p>Grown in Belgium, Egypt, Germany,\n" +
            "                                            Israel, Mexico, Morocco, Portugal, Spain, United Kingdom, USA, Jordan,\n" +
            "                                            Netherlands</p>           \n" +
            "                                        </div>\n" +
            "                                                     <h3 class=\"productDataItemHeader\">Size</h3>            \n" +
            "                                        <div class=\"productText\">              <p>400gram</p>            </div>\n" +
            "                                                     <h3 class=\"productDataItemHeader\">Packaging</h3>            \n" +
            "                                        <div class=\"productText\">              <p>Plastic - APET punnet</p>            \n" +
            "                                             <p>Plastic - APET punnet</p>              <p>Plastic - APET punnet</p>    \n" +
            "                                                     <p>Plastic - APET punnet</p>              <p>Laminate film film</p>\n" +
            "                                                          <p>Paper laminate label -glued</p>            \n" +
            "                                        </div>\n" +
            "                                                     <h3 class=\"productDataItemHeader\">Manufacturer</h3>            \n" +
            "                                        <div class=\"productText\">              <p>We are happy to replace this item if\n" +
            "                                            it is not satisfactory</p>              <p>Sainsbury's Supermarkets Ltd.</p>\n" +
            "                                                          <p>33 Holborn, London EC1N 2HT</p>              <p>Customer\n" +
            "                                                services 0800 636262</p>            \n" +
            "                                        </div>\n" +
            "                                                    \n" +
            "                                        <!-- Mirrored from www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 08 Aug 2017 08:45:17 GMT -->\n" +
            "                                                    \n" +
            "                                    </htmlcontent>\n" +
            "                                              \n" +
            "                                </productcontent>\n" +
            "                                           <p></p>          <h3>Important Information</h3>          <p>The above details\n" +
            "                                    have been prepared to help you select suitable products. Products and their\n" +
            "                                    ingredients are liable to change.</p>          <p><strong>You should always read the\n" +
            "                                    label before consuming or using the product and never rely solely on the information\n" +
            "                                    presented here.</strong></p>          <p>If you require specific advice on any\n" +
            "                                    Sainsbury's branded product, please contact our Customer Careline on 0800 636262.\n" +
            "                                    For all other products, please contact the manufacturer.</p>          <p> This\n" +
            "                                    information is supplied for your personal use only. It may not be reproduced in any\n" +
            "                                    way without the prior consent of Sainsbury's Supermarkets Ltd and due\n" +
            "                                    acknowledgement.</p>          <p></p>          \n" +
            "                            </div>\n" +
            "                                    \n" +
            "                        </div>\n" +
            "                                 <p class=\"skuCode\">7555699</p>        \n" +
            "                    </div>\n" +
            "                          \n" +
            "                </div>\n" +
            "                      \n" +
            "                <div id=\"additionalItems_124183\" class=\"additionalProductInfo\">        <!--  Left hand side column -->  \n" +
            "                         <!-- BEGIN MerchandisingAssociationsDisplay.jsp -->        \n" +
            "                    <!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp -->        \n" +
            "                    <!-- END MerchandisingAssociationsDisplay.jsp -->       \n" +
            "                </div>\n" +
            "                       <!-- END CachedProductOnlyDisplay.jsp -->      \n" +
            "            </div>\n" +
            "                 <!-- productContent End -->     \n" +
            "        </div>\n" +
            "    </div>\n" +
            "        <!-- Main Area End -->    <!-- Footer Start Start -->    <!-- BEGIN LayoutContainerBottom.jspf -->    \n" +
            "\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
    private JSoupScraper JSoupScraperPowerMock = new JSoupScraper();

    @Test
    public void getTitle_ShouldReturnTittle() throws Exception {
        String actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getTitle", Jsoup.parse(documentWithVariables));

        assertEquals("Sainsbury's Strawberries 400g", actual);
    }

    @Test
    public void getTitle_ShouldReturnEmptyString() throws Exception {
        String actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getTitle", Jsoup.parse(documentEmpty));

        assertEquals("", actual);
    }

    @Test
    public void getKcalPer100g_ShouldReturnKcalPer100g() throws Exception {
        Optional<Integer> actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getKcalPer100g", Jsoup.parse(documentWithVariables));

        assertEquals(Optional.of(33), actual);
    }

    @Test
    public void getKcalPer100g_ShouldReturnNull() throws Exception {
        String actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getKcalPer100g", Jsoup.parse(documentEmpty));

        assertNull(actual);
    }

    @Test
    public void getUnitPrice_ShouldReturnUnitPrice() throws Exception {
        BigDecimal actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getUnitPrice", Jsoup.parse(documentWithVariables));

        assertEquals(new BigDecimal(1.75), actual);
    }

    @Test
    public void getUnitPrice_ShouldReturnEmptyString() throws Exception {
        BigDecimal actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getUnitPrice", Jsoup.parse(documentEmpty));

        assertEquals(new BigDecimal(-1), actual);
    }

    @Test
    public void getDescription_ShouldReturnDescription() throws Exception {
        String actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getDescription", Jsoup.parse(documentWithVariables));

        assertEquals("by Sainsbury's strawberries", actual);
    }

    @Test
    public void getDescription_ShouldReturnEmptyString() throws Exception {
        String actual = Whitebox.invokeMethod(JSoupScraperPowerMock,
                "getDescription", Jsoup.parse(documentEmpty));

        assertEquals("", actual);
    }
}