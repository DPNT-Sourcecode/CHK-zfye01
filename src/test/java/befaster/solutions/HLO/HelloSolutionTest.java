package befaster.solutions.HLO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;;

public class HelloSolutionTest {
    private HelloSolution hello;

    @BeforeEach
    public void setUp() {
        hello = new HelloSolution();
    }

    @Test
    public void returnsValidString() {
        assertThat(hello.hello("X"), not(isEmptyOrNullString()));
    }

    @Test
    public void returnsHelloFriend() {
        assertThat(hello.hello("FRIEND"), equalTo("Hello, FRIEND!"));
    }
}
