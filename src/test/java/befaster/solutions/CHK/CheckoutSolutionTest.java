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
        assertThat(checkout.checkout("AABCD"), equalTo(165));
        assertThat(checkout.checkout("AAABBCD"), equalTo(210));
    }

    // TEST REMOVED DUE TO FAILING DEPLOYMENT REQUESTS
    // @Test
    // public void handlesLowercaseBasket() {
    //     assertThat(checkout.checkout("aaabbcd"), equalTo(checkout.checkout("AAABBCD")));
    // }

    @Test
    public void handlesWhitespaceBasket() {
        assertThat(checkout.checkout("A B C D"), equalTo(checkout.checkout("ABCD")));
    }

    @Test
    public void handlesEmptyBasketPrice() {
        assertThat(checkout.checkout(""), equalTo(0));
    }

    @Test
    public void rejectsInvalidItemType() {
        assertThat(checkout.checkout("E"), equalTo(-1));
        assertThat(checkout.checkout("a"), equalTo(-1));
    }
    
}

