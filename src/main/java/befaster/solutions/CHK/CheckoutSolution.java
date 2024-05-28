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
        HashMap<Character, Integer> itemCounts;
        try {
            itemCounts = getItemCounts(fixedSkus);
        } catch (Exception e) { // Catch exception, reveals that an invalid item type was given
            return -1;
        }

        return calculatePriceFromCounts(itemCounts);
    }

    // Returns an int[] containing the count of different items
    private HashMap<Character, Integer> getItemCounts(String fixedSkus) throws Exception{
        HashMap<Character, Integer> itemCounts =  new HashMap<>();

        char[] skuChars = fixedSkus.toCharArray(); // characters in skus
        // Iterate over the skuChars to count to number of each item type
        for (char sku : skuChars) {
            // Accepts any uppercase alphabetical character
            if (Character.isLetter(sku) && Character.isUpperCase(sku)) {
                Integer skuCount = itemCounts.get(sku);
                // If no entry exists, create one
                if (Objects.isNull(skuCount)) {
                    itemCounts.put(sku, 1);
                } else  { // Else increment existing entry
                    itemCounts.put(sku, skuCount + 1);
                }
            } else { // Invalid item type, throw exception
                throw new Exception("Invalid item type");
            }
        }
        return itemCounts;
    }

    private int getCountBySKU(HashMap<Character, Integer> itemCounts, char sku) {
        return itemCounts.getOrDefault(sku, 0);
    }

    private void setBySKU(HashMap<Character, Integer> itemCounts, char sku, int amount) {
        Integer skuCount = itemCounts.get(sku);
        // Set existing entry if exists
        if (Objects.nonNull(skuCount)) {
            itemCounts.put(sku, amount);
        }
    }

    private void decrementBySKU(HashMap<Character, Integer> itemCounts, char sku) {
        decrementBySKU(itemCounts, sku, 1);
    }

    private void decrementBySKU(HashMap<Character, Integer> itemCounts, char sku, int amount) {
        Integer skuCount = itemCounts.get(sku);
        // Decrement existing entry if exists
        if (Objects.nonNull(skuCount)) {
            itemCounts.put(sku, skuCount - amount);
        }
    }

    // Returns the overall basket price given a set of item counts
    private int calculatePriceFromCounts(HashMap<Character, Integer> itemCounts) {
        int runningSum = 0;

        // HANDLE SKUs WITH GROUP DISCOUNT OFFERS

        // Handle pricing S
        runningSum += getCountBySKU(itemCounts, 'S') * 20; // calculate regular pricing

        // Handle pricing T
        runningSum += getCountBySKU(itemCounts, 'T') * 20; // calculate regular pricing

        // Handle pricing X
        runningSum += getCountBySKU(itemCounts, 'X') * 17; // calculate regular pricing

        // Handle pricing Y
        runningSum += getCountBySKU(itemCounts, 'Y') * 20; // calculate regular pricing

        // Handle pricing Z
        runningSum += getCountBySKU(itemCounts, 'Z') * 21; // calculate regular pricing

        // HANDLE SKUs WITH CROSS-ITEM OFFERS (i.e. purchasing one type of SKU 1 gives a free SKU 2)
        /*
         * Cross-item offer priority:
         * 3R-1Q saves 50
         * 2E-1B saves 30
         * 3N-1M saves 15
         */

        // Handle pricing R (savings of 50)
        decrementBySKU(itemCounts, 'Q', Math.min(getCountBySKU(itemCounts, 'R') / 3, getCountBySKU(itemCounts, 'Q'))); // remove as many Qs as there are 3Rs as they become free
        runningSum += getCountBySKU(itemCounts, 'R') * 50; // calculate regular pricing

        // Handle pricing E (savings of 30)
        decrementBySKU(itemCounts, 'B', Math.min(getCountBySKU(itemCounts, 'E') / 2, getCountBySKU(itemCounts, 'B'))); // remove as many Bs as there are 2Es as they become free
        runningSum += getCountBySKU(itemCounts, 'E') * 40; // calculate regular pricing

        // Handle pricing N (savings of 15)
        decrementBySKU(itemCounts, 'M', Math.min(getCountBySKU(itemCounts, 'N') / 3, getCountBySKU(itemCounts, 'M'))); // remove as many Ms as there are 3Ns as they become free
        runningSum += getCountBySKU(itemCounts, 'N') * 40; // calculate regular pricing

        // HANDLE SKUs WITHOUT CROSS-ITEM OFFERS

        // Handle pricing A, prioritises 5A > 3A > A to benefit the customer (bigger offer discount first)
        runningSum += getCountBySKU(itemCounts, 'A') / 5 * 200; // calculate special offer for 5As
        setBySKU(itemCounts, 'A', getCountBySKU(itemCounts, 'A') % 5); // remove sets of 5A units from the count
        runningSum += getCountBySKU(itemCounts, 'A') / 3 * 130; // calculate special offers for 3As
        setBySKU(itemCounts, 'A', getCountBySKU(itemCounts, 'A') % 3); // remove sets of 3A units from the count
        runningSum += getCountBySKU(itemCounts, 'A') * 50; // calculate regular pricing

        // Handle pricing B
        runningSum += getCountBySKU(itemCounts, 'B') / 2 * 45; // calculate special offers
        runningSum += getCountBySKU(itemCounts, 'B') % 2 * 30; // calculate regular pricing

        // Handle pricing C
        runningSum += getCountBySKU(itemCounts, 'C') * 20; // calculate regular pricing

        // Handle pricing D
        runningSum += getCountBySKU(itemCounts, 'D') * 15; // calculate regular pricing

        // Handle pricing F
        decrementBySKU(itemCounts, 'F', getCountBySKU(itemCounts, 'F') / 3); // remove as many Fs as there are 3Fs (equivalent of buy 2F get 1F free)
        runningSum += getCountBySKU(itemCounts, 'F') * 10;

        // Handle pricing G
        runningSum += getCountBySKU(itemCounts, 'G') * 20; // calculate regular pricing

        // Handle pricing H, prioritises 10H > 5H > H to benefit the customer
        runningSum += getCountBySKU(itemCounts, 'H') / 10 * 80; // calculate special offer for 10Hs
        setBySKU(itemCounts, 'H', getCountBySKU(itemCounts, 'H') % 10); // remove sets of 10H units from the count
        runningSum += getCountBySKU(itemCounts, 'H') / 5 * 45; // calculate special offers for 5Hs
        setBySKU(itemCounts, 'H', getCountBySKU(itemCounts, 'H') % 5); // remove sets of 5H units from the count
        runningSum += getCountBySKU(itemCounts, 'H') * 10; // calculate regular pricing

        // Handle pricing I
        runningSum += getCountBySKU(itemCounts, 'I') * 35; // calculate regular pricing

        // Handle pricing J
        runningSum += getCountBySKU(itemCounts, 'J') * 60; // calculate regular pricing

        // Handle pricing K
        runningSum += getCountBySKU(itemCounts, 'K') / 2 * 150; // calculate special offers
        runningSum += getCountBySKU(itemCounts, 'K') % 2 * 80; // calculate regular pricing

        // Handle pricing L
        runningSum += getCountBySKU(itemCounts, 'L') * 90; // calculate regular pricing

        // Handle pricing M
        runningSum += getCountBySKU(itemCounts, 'M') * 15; // calculate regular pricing

        // Handle pricing O
        runningSum += getCountBySKU(itemCounts, 'O') * 10; // calculate regular pricing

        // Handle pricing P
        runningSum += getCountBySKU(itemCounts, 'P') / 5 * 200; // calculate special offers
        runningSum += getCountBySKU(itemCounts, 'P') % 5 * 50; // calculate regular pricing

        // Handle pricing Q
        runningSum += getCountBySKU(itemCounts, 'Q') / 3 * 80; // calculate special offers
        runningSum += getCountBySKU(itemCounts, 'Q') % 3 * 30; // calculate regular pricing

        // Handle pricing U
        decrementBySKU(itemCounts, 'U', getCountBySKU(itemCounts, 'U') / 4); // remove as many Us as there are 4Us (equivalent of buy 3Us get 1U free)
        runningSum += getCountBySKU(itemCounts, 'U') * 40;

        // Handle pricing V, prioritises 3V > 2V > V to benefit the customer
        runningSum += getCountBySKU(itemCounts, 'V') / 3 * 130; // calculate special offer for 3Vs
        setBySKU(itemCounts, 'V', getCountBySKU(itemCounts, 'V') % 3); // remove sets of 3V units from the count
        runningSum += getCountBySKU(itemCounts, 'V') / 2 * 90; // calculate special offers for 2Vs
        setBySKU(itemCounts, 'V', getCountBySKU(itemCounts, 'V') % 2); // remove sets of 2V units from the count
        runningSum += getCountBySKU(itemCounts, 'V') * 50; // calculate regular pricing

        // Handle pricing W
        runningSum += getCountBySKU(itemCounts, 'W') * 20; // calculate regular pricing

        return runningSum;
    }

}




