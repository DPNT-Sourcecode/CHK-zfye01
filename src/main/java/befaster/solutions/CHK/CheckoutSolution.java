package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Objects;

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
        int[] itemCounts = new int[26]; // count of each item type (A, B, C, D)
        HashMap<Character, Integer> itemCountsM =  new HashMap<>();

        char[] skuChars = fixedSkus.toCharArray(); // characters in skus
        // Iterate over the skuChars to count to number of each item type
        for (char sku : skuChars) {
            // Switch statement to increment each item type count or invalid type

            // Accepts any uppercase alphabetical character
            if (Character.isLetter(sku) && Character.isUpperCase(sku)) {
                Integer skuCount = itemCountsM.get(sku);
                // If no entry exists, create one
                if (Objects.isNull(skuCount)) {
                    itemCountsM.put(sku, 0);
                } else  { // Else increment existing entry
                    itemCountsM.put(sku, skuCount + 1);
                }
            }

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
                case 'G':
                    itemCounts[6]++;
                    break;
                case 'H':
                    itemCounts[7]++;
                    break;
                case 'I':
                    itemCounts[8]++;
                    break;
                case 'J':
                    itemCounts[9]++;
                    break;
                case 'K':
                    itemCounts[10]++;
                    break;
                case 'L':
                    itemCounts[11]++;
                    break;
                case 'M':
                    itemCounts[12]++;
                    break;
                case 'N':
                    itemCounts[13]++;
                    break;
                case 'O':
                    itemCounts[14]++;
                    break;
                case 'P':
                    itemCounts[15]++;
                    break;
                case 'Q':
                    itemCounts[16]++;
                    break;
                case 'R':
                    itemCounts[17]++;
                    break;
                case 'S':
                    itemCounts[18]++;
                    break;
                case 'T':
                    itemCounts[19]++;
                    break;
                case 'U':
                    itemCounts[20]++;
                    break;
                case 'V':
                    itemCounts[21]++;
                    break;
                case 'W':
                    itemCounts[22]++;
                    break;
                case 'X':
                    itemCounts[23]++;
                    break;
                case 'Y':
                    itemCounts[24]++;
                    break;
                case 'Z':
                    itemCounts[25]++;
                    break;
                default: // invalid item type, immediately throw exception
                    throw new Exception("Invalid item type");
            }
        }
        return itemCounts;
    }

    // Returns the overall basket price given a set of item counts
    private int calculatePriceFromCounts(int[] itemCounts) {
        /*
         * Free cross-item priority:
         * 3R-1Q saves 50
         * 2E-1B saves 30
         * 3N-1M saves 15
         */

        int runningSum = 0;

        // HANDLE SKUs WITH CROSS-ITEM OFFERS (i.e. purchasing one type of SKU 1 gives a free SKU 2)

        // Handle pricing R (savings of 50)

        // Handle pricing E (savings of 30)
        itemCounts[1] -= Math.min(itemCounts[4] / 2, itemCounts[1]); // remove as many Bs as there are 2Es as they become free
        runningSum += itemCounts[4] * 40; // calculate regular pricing

        // Handle pricing N (savings of 15)


        // HANDLE SKUs WITHOUT CROSS-ITEM OFFERS

        // Handle pricing A, prioritises 5A > 3A > A to benefit the customer (bigger offer discount first)
        runningSum += itemCounts[0] / 5 * 200; // calculate special offer for 5As
        itemCounts[0] = itemCounts[0] % 5; // remove 5A units from the count
        runningSum += itemCounts[0] / 3 * 130; // calculate special offers for 3As
        itemCounts[0] = itemCounts[0] % 3; // remove 3A units from the count
        runningSum += itemCounts[0] % 3 * 50; // calculate regular pricing

        // Handle pricing B
        runningSum += itemCounts[1] / 2 * 45; // calculate special offers
        runningSum += itemCounts[1] % 2 * 30; // calculate regular pricing

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




