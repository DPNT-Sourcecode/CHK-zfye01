package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {


    public Integer checkout(String skus) {
        int[] itemCounts = new int[4]; // count of each item type (A, B, C, D)
        char[] skuChars = skus.toCharArray(); // characters in skus
        // Iterate over the skuChars to count to number of each item type
        for (char sku : skuChars) {
            // Switch statement to increment each item type count or invalid type
            switch (sku) {
                case 'A':
                    itemCounts[0]++;
                    break;
                case 'B':
                    itemCounts[1]++;
                    break;
                case 'C':
                    itemCounts[2]++;
                    break;
                case 'D':
                    itemCounts[3]++;
                    break;
                default: // invalid item type, immediately return
                    return -1;
            }
        }

        return -1;
    }
}

