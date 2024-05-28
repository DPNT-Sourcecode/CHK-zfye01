package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {


    public Integer checkout(String skus) {
        // Preprocessing

        // String fixedSkus = skus.toUpperCase(); // set all to uppercase, REMOVED DUE TO FAILING TEST

        String fixedSkus = skus.replaceAll("\\s", ""); // remove whitespace
        System.out.println(fixedSkus);

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
        int[] itemCounts = new int[6]; // count of each item type (A, B, C, D)
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
                case 'E':
                    itemCounts[4]++;
                    break;
                case 'F':
                    itemCounts[5]++;
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

        // Handle pricing A, prioritises 5A > 3A > A to benefit the customer (bigger offer discount first)
        int runningACount = itemCounts[0];
        runningSum += runningACount / 5 * 200; // calculate special offer for 5As
        runningACount = runningACount % 5; // remove 5A units from the count
        runningSum += runningACount / 3 * 130; // calculate special offers for 3As
        runningACount = runningACount % 3; // remove 3A units from the count
        runningSum += runningACount % 3 * 50; // calculate regular pricing

        // Handle pricing E (before B, apply 2E discount before 2B discount)
        int runningBCount = itemCounts[1];
        runningBCount -= Math.min(itemCounts[4] / 2, runningBCount); // remove as many Bs as there are 2Es as they become free
        runningSum += itemCounts[4] * 40; // calculate regular pricing

        // Handle pricing B
        runningSum += runningBCount / 2 * 45; // calculate special offers
        runningSum += runningBCount % 2 * 30; // calculate regular pricing

        // Handle pricing C
        runningSum += itemCounts[2] * 20; // calculate regular pricing

        // Handle pricing D
        runningSum += itemCounts[3] * 15; // calculate regular pricing

        // Handle pricing F
        int runningFCount = itemCounts[5];
        runningFCount -= runningFCount / 3; // remove as many Fs as there are 3Fs (equivalent of buy 2F get 1F free)
        runningSum += runningFCount * 10;

        return runningSum;
    }

}

