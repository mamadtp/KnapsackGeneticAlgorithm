package knapsackgeneticalgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class KnapsackGeneticAlgorithm {

    private static final String FILEWEIGHT = "//home//mamadtp//weight.txt";
    private static final String FILEVALUE = "//home//mamadtp//value.txt";

    public static void main(String[] args) throws FileNotFoundException {
     
        int age = 1000000;
        int itemcount = 100;
        int weight_of_knapsack = 400;
        int populationSize = 800;

        
        
        
        
        
        
        int weights[] = new int[itemcount];
        int values[] = new int[itemcount];

        try {
            int i = 0;
            Scanner readerWeight = new Scanner(new File(FILEWEIGHT));

            while (readerWeight.hasNextInt()) {
                weights[i] = readerWeight.nextInt();
                i++;
            }
            i = 0;
            Scanner readerValue = new Scanner(new File(FILEVALUE));
            while (readerValue.hasNextInt()) {
                values[i] = readerValue.nextInt();
                i++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        boolean x[] = new boolean[itemcount];
        for (int i = 0; i < itemcount; i++) {
            x[i] = false;
        }

        System.out.println();
        int count = 0;
        Population population = new Population(weights, values, itemcount, weight_of_knapsack, populationSize);

        Chromosome best = new Chromosome(values, values, x, itemcount, weight_of_knapsack);
        while (count < age) {
            Population new_population = new Population(weights, values, itemcount, weight_of_knapsack, 0);
            for (int i = 0; i < populationSize;) {
                Chromosome c = population.production(weights, values);
                if (c.weight_of_chromosome() <= weight_of_knapsack) {
                    new_population.insert(c);
                    if (c.fitness() > best.fitness()) {
                        best = c;
                        System.out.println("The best Changed to:" + best.fitness());
                    }
                    i++;

                }
            }
            count++;
            population = new_population;
        }

        System.out.println("The best reasult is:" + best.fitness());
    }

}
