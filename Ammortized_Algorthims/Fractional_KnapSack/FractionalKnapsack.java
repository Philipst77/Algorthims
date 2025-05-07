import java.io.*;
import java.util.*;

class FractionalKnapsack {

    static class Item {
        double v_i;
        double w_i;
        double valuePerPound;

        public Item(double v_i, double w_i) {
            this.v_i = v_i;
            this.w_i = w_i;
            this.valuePerPound = v_i / w_i;
        }
    }

    public static double fractionalKnapsack(List<Item> items, int W) {
        Collections.sort(items, (a, b) -> Double.compare(b.valuePerPound, a.valuePerPound));

        double totalValue = 0; 
        long currentWeight = 0; 

        for (Item item : items) {
            if (currentWeight + item.w_i <= W) {
                currentWeight += item.w_i;
                totalValue += item.v_i;
            } else {
                long remainingWeight = W - currentWeight;
                totalValue += item.valuePerPound * remainingWeight;  
                break;  
            }
        }

        return totalValue;
    }

    public static List<Item> parseInstance(String line) {
        String[] data = line.split("\t");
        List<Item> items = new ArrayList<>();
    
        for (int i = 1; i + 1 < data.length; i += 2) {
            double value = Double.parseDouble(data[i]);
            double weight = Double.parseDouble(data[i + 1]);
            items.add(new Item(value, weight));
        }
    
        return items;
    }
    

    public static void main(String[] args) {
        int W = 100;  

        try {
            BufferedReader br = new BufferedReader(new FileReader("instances_h4.txt"));
            String line;
            long startTime, endTime, execTime;
            
            while ((line = br.readLine()) != null) {
                List<Item> items = parseInstance(line);

                String[] parts = line.split("\t");
                String instanceID = parts[0];

                startTime = System.nanoTime();
                double totalValue = fractionalKnapsack(items, W);
                endTime = System.nanoTime();
                execTime = endTime - startTime;

                System.out.printf("%s\t$%.2f\t%d ns\n", instanceID, totalValue, execTime);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
