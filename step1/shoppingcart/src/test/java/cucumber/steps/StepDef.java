package cucumber.steps;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.test.Invoice;
import com.test.Product;
import com.test.ShoppingCart;
import com.test.ShoppingProcessor;
import com.test.ShoppingProcessorImpl;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDef {
    private static ShoppingProcessor shoppingProcessor;
    private static Map<String, Product> store = new HashMap<>();
    private static ShoppingCart.ShoppingCartBuilder shoppingCartBuilder = new ShoppingCart.ShoppingCartBuilder();
    private ShoppingCart shoppingCart;
    private Invoice invoice;

    static void setup() {
        final Product doveSoap = Product.create("Dove Soap", 39.99);
        final Map<UUID, Product> inventory = new HashMap<>();
        inventory.put(doveSoap.getId(), doveSoap);
        shoppingProcessor = new ShoppingProcessorImpl(inventory);
    }

    static void tearDown() {
        store.clear();
        shoppingCartBuilder.empty();
    }

    @Given("^an empty shopping cart$")
    public void emptyShoppingCart() {
        shoppingCartBuilder.empty();
    }

    @And("a product, (.+) with a unit price of (.+)")
    public void aProductDoveSoapWithAUnitPriceOf(final String productName, final double unitPrice) {
        final Product product = Product.create(productName, unitPrice);
        store.put(product.getName(), product);

    }

    @When("the user adds ([0-9]+) (.+) to the shopping cart")
    public void theUserAddsDoveSoapToTheShoppingCart(int quantity, String productName) {
        System.out.println(quantity);
        System.out.println(productName);
        shoppingCart = shoppingCartBuilder.addProduct(store.get(productName), quantity).build();
        invoice = shoppingProcessor.process(shoppingCart);
    }

    @Then("The shopping cart should contain ([0-9]+) (.+) each with a unit price of (.+)")
    public void theShoppingCartShouldContainDoveSoapsEachWithAUnitPriceOf(int quantity, String productName, double unitPrice) {
        final Product product = store.get(productName);
        assertEquals(Integer.valueOf(quantity), shoppingCart.getShoppingList().get(product));
        assertEquals(unitPrice, product.getPrice(), 0.0002);
    }

    @And("the shopping cart’s total price should equal (.+)")
    public void theShoppingCartSTotalPriceShouldEqual(String totalPrice) {
        assertEquals(totalPrice, invoice.formatPrice());
    }
}
