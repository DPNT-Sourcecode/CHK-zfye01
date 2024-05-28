package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {


    public Integer checkout(String skus) {
        // Preprocessing
        String fixedSkus = skus.toUpperCase(); // set all to uppercase
        fixedSkus.replaceAll("\\s", ""); // remove whitespace

        // Count the items
        int[] itemCounts;
        try {
            itemCounts = getItemCounts(fixedSkus);
        } catch (Exception e) { // Catch exception, reveals that an invalid item type was given
            return -1;
        }

        return calculatePriceFromCounts(itemCounts);
    }

    // Returns an int[] containing the count of different items
    private int[] getItemCounts(String fixedSkus) throws Exception{
        int[] itemCounts = new int[4]; // count of each item type (A, B, C, D)
        char[] skuChars = fixedSkus.toCharArray(); // characters in skus
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
                default: // invalid item type, immediately throw exception
                    throw new Exception("Invalid item type");
            }
        }
        return itemCounts;
    }

    // Returns the overall basket price given a set of item counts
    private int calculatePriceFromCounts(int[] itemCounts) {
        int runningSum = 0;

        // Handle pricing A
        runningSum += itemCounts[0] / 3 * 130; // calculate special offers
        runningSum += itemCounts[0] % 3 * 50; // calculate regular pricing

        // Handle pricing B
        runningSum += itemCounts[1] / 2 * 45; // calculate special offers
        runningSum += itemCounts[1] % 2 * 30; // calculate regular pricing

        // Handle pricing C
        runningSum += itemCounts[2] * 20; // calculate regular pricing

        // Handle pricing D
        runningSum += itemCounts[3] * 15; // calculate regular pricing

        return runningSum;
    }

}




