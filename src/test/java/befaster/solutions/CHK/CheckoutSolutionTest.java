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
        assertThat(checkout.checkout("E"), equalTo(40));
        assertThat(checkout.checkout("F"), equalTo(10));
        assertThat(checkout.checkout("G"), equalTo(20));
        assertThat(checkout.checkout("H"), equalTo(10));
        assertThat(checkout.checkout("I"), equalTo(35));
        assertThat(checkout.checkout("J"), equalTo(60));
        assertThat(checkout.checkout("K"), equalTo(80));
        assertThat(checkout.checkout("L"), equalTo(90));
        assertThat(checkout.checkout("M"), equalTo(15));
        assertThat(checkout.checkout("N"), equalTo(40));
        assertThat(checkout.checkout("O"), equalTo(10));
        assertThat(checkout.checkout("P"), equalTo(50));
        assertThat(checkout.checkout("Q"), equalTo(30));
        assertThat(checkout.checkout("R"), equalTo(50));
        assertThat(checkout.checkout("S"), equalTo(30));
        assertThat(checkout.checkout("T"), equalTo(20));
        assertThat(checkout.checkout("U"), equalTo(40));
        assertThat(checkout.checkout("V"), equalTo(50));
        assertThat(checkout.checkout("W"), equalTo(20));
        assertThat(checkout.checkout("X"), equalTo(90));
        assertThat(checkout.checkout("Y"), equalTo(10));
        assertThat(checkout.checkout("Z"), equalTo(50));
    }

    @Test
    public void returnsCorrectDiscountOfferPricesPerSKU() {
        assertThat(checkout.checkout("AAA"), equalTo(130));
        assertThat(checkout.checkout("AAAAA"), equalTo(200));
        assertThat(checkout.checkout("BB"), equalTo(45));
        assertThat(checkout.checkout("HHHHH"), equalTo(45));
        assertThat(checkout.checkout("HHHHHHHHHH"), equalTo(80));
        assertThat(checkout.checkout("KK"), equalTo(150));
        assertThat(checkout.checkout("PPPPP"), equalTo(200));
        assertThat(checkout.checkout("QQQ"), equalTo(80));
        assertThat(checkout.checkout("VV"), equalTo(90));
        assertThat(checkout.checkout("VVV"), equalTo(130));
    }

    @Test
    public void returnsCorrectFreeOfferPricesPerSKU() {
        assertThat(checkout.checkout("EE"), equalTo(80));
        assertThat(checkout.checkout("EEB"), equalTo(checkout.checkout("EE")));
        assertThat(checkout.checkout("FF"), equalTo(20));
        assertThat(checkout.checkout("FFF"), equalTo(checkout.checkout("FF")));
        assertThat(checkout.checkout("NNN"), equalTo(120));
        assertThat(checkout.checkout("NNNM"), equalTo(checkout.checkout("NNN")));
        assertThat(checkout.checkout("RRR"), equalTo(150));
        assertThat(checkout.checkout("RRRQ"), equalTo(checkout.checkout("RRR")));
        assertThat(checkout.checkout("UUU"), equalTo(120));
        assertThat(checkout.checkout("UUUU"), equalTo(checkout.checkout("UUU")));
    }

    @Test
    public void returnsCorrectBasketPrice() {
        assertThat(checkout.checkout("AABCD"), equalTo(165));
        assertThat(checkout.checkout("AAABBCD"), equalTo(210));
        assertThat(checkout.checkout("AAAAAABBCDEE"), equalTo(395));
        assertThat(checkout.checkout("AAAAAABBCDEEFFFF"), equalTo(425));
    }

    // TEST REMOVED DUE TO FAILING DEPLOYMENT REQUESTS
    // @Test
    // public void handlesLowercaseBasket() {
    //     assertThat(checkout.checkout("aaabbcd"), equalTo(checkout.checkout("AAABBCD")));
    // }

    @Test
    public void handlesWhitespaceBasket() {
        assertThat(checkout.checkout("A B C D E F"), equalTo(checkout.checkout("ABCDEF")));
    }

    @Test
    public void handlesEmptyBasketPrice() {
        assertThat(checkout.checkout(""), equalTo(0));
    }

    @Test
    public void rejectsInvalidItemType() {
        assertThat(checkout.checkout("*"), equalTo(-1));
        assertThat(checkout.checkout("a"), equalTo(-1));
    }
    
}

