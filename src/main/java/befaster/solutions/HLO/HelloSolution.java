package befaster.solutions.HLO;

import befaster.runner.SolutionNotImplementedException;

public class HelloSolution {
    public String hello(String friendName) {
        StringBuilder sb = new StringBuilder("Hello, ");
        sb.append(friendName);
        sb.append("!");
        return sb.toString();
    }
}
