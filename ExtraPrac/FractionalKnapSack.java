import java.util.*;

public class FractionalKnapSack {
    static class Item {
        int profit;
        int weight;

        Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }

        double getRatio() {
            return (double) profit / weight;
        }
    }

    public static void main(String[] args) {
        int capacity = 50;
        Item[] items = {
            new Item(100, 20), // Item 1
            new Item(60, 10),  // Item 2
            new Item(120, 30), // Item 3
            new Item(200, 200) // Item 4
        };

        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.getRatio(), a.getRatio()));

        double totalValue = 0;
        double remainingWeight = capacity;
        double[] fractionTaken = new double[items.length]; // 0.0 to 1.0

        for (int i = 0; i < items.length; i++) {
            if (remainingWeight >= items[i].weight) {
                // Take the full item
                totalValue += items[i].profit;
                remainingWeight -= items[i].weight;
                fractionTaken[i] = 1.0;
            } else {
                // Take a fraction of the item
                double fraction = remainingWeight / items[i].weight;
                totalValue += fraction * items[i].profit;
                fractionTaken[i] = fraction;
                remainingWeight = 0;
                break;
            }
        }

        System.out.printf("Total value of load: %.2f\n", totalValue);
        System.out.printf("Weight left behind: %.2f\n", 50 - (capacity - remainingWeight));

        // Item 3 is index 2 in original list
        int item3Index = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].profit == 120 && items[i].weight == 30) {
                item3Index = i;
                break;
            }
        }

        if (item3Index != -1) {
            double taken = fractionTaken[item3Index];
            double leftProfit = (1.0 - taken) * items[item3Index].profit;
            System.out.printf("Profit left behind from Item 3: %.2f\n", leftProfit);
            System.out.printf("Percentage of Item 3 taken: %.2f%%\n", taken * 100);
        }

        System.out.println("\nOrder of items placed in knapsack:");
        for (int i = 0; i < items.length; i++) {
            if (fractionTaken[i] > 0) {
                System.out.printf("Item with value %d and weight %d — %.0f%% taken\n",
                    items[i].profit, items[i].weight, fractionTaken[i] * 100);
            }
        }
    }
}
