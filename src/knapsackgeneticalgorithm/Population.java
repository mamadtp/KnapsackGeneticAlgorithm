package knapsackgeneticalgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    int populationSize = 0;
    List<Chromosome> chrome = new ArrayList<Chromosome>();
    int itemcount = 0;
    int weight_of_knapsack = 100;

    public void insert(Chromosome x) {
        chrome.add(x);
        populationSize++;
    }

    static public int random_selection(List<Chromosome> c, int si) {
        int sum = 0;
        for (int i = 0; i < si; i++) {
            sum += c.get(i).fitness();
        }
        Random rand = new Random();
        if (sum == 0) {
            return si - 1;
        }
        double r = rand.nextInt(sum);
        double s = 0;
        for (int i = 0; i < si; i++) {
            s += c.get(i).fitness();
            if (r <= s) {
                return i;
            }
        }
        return si - 1;
    }

    public Chromosome production(int w[], int v[]) {

        Random rand = new Random();

        Chromosome parent1 = chrome.get(Population.random_selection(chrome, populationSize));
        Chromosome parent2 = chrome.get(Population.random_selection(chrome, populationSize));

        int croos_over = rand.nextInt(itemcount - 1);

        boolean temp[] = new boolean[itemcount];

        for (int i = 0; i < croos_over; i++) {
            temp[i] = parent1.gen[i];
        }
        for (int j = croos_over; j < itemcount; j++) {
            temp[j] = parent2.gen[j];
        }
        Chromosome child = new Chromosome(w, v, temp, itemcount,weight_of_knapsack);

        child.mutation();
        return child;
    }

    public Population(int w[], int v[], int m, int weight_of_knapsack, int populationSize) {
        this.populationSize = populationSize;
        itemcount = m;
        Random rand = new Random();

        for (int i = 0; i < populationSize; i++) {
            boolean x[] = new boolean[itemcount];
            for (int k = 0; k < itemcount; k++) {
                x[k] = false;
            }
            int temp = 0;
            while (true) {
                int random = rand.nextInt(100);
                if (!x[random]) {
                    if (temp + w[random] <= weight_of_knapsack) {
                        x[random] = true;
                        temp += w[random];
                    } else {
                        break;
                    }
                }

            }
            Chromosome c = new Chromosome(w, v, x, itemcount,weight_of_knapsack);
            chrome.add(c);
        }
    }

}
