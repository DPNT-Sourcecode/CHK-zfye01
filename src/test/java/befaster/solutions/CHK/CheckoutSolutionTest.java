package befaster.solutions.CHK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {
    private CheckoutSolution checkout;

    @BeforeEach
    public void setUp() {
        checkout = new CheckoutSolution();
    }

    @Test
    public void returnsCorrectPricesPerSKU() {
        assertThat(checkout.checkout("A"), equalTo(50));
        assertThat(checkout.checkout("B"), equalTo(30));
        assertThat(checkout.checkout("C"), equalTo(20));
        assertThat(checkout.checkout("D"), equalTo(15));
    }

    @Test
    public void returnsCorrectOfferPricesPerSKU() {
        assertThat(checkout.checkout("AAA"), equalTo(130));
        assertThat(checkout.checkout("BB"), equalTo(45));
    }

    @Test
    public void returnsCorrectBasketPrice() {
        assertThat(checkout.checkout("AAABBCD"), equalTo(210));
    }
    
}

