package knapsackgeneticalgorithm;

import java.util.Random;

public class Chromosome {

    int knapsakCapicity=0;
    int itemcount = 100;
    int weight[] = new int[itemcount];
    int value[] = new int[itemcount];
    boolean gen[] = new boolean[itemcount];

    public int fitness() {
        int fit = 0;
        for (int i = 0; i < itemcount; i++) {
            if (gen[i]) {
                fit += value[i];
            }
        }
        return fit;
    }

    public void mutation() {
        Random rand = new Random();
        int random = rand.nextInt(100);
        if (random < 5) {
            int r;
            do{
                r = rand.nextInt(itemcount);
            }while(!gen[r]);
            gen[r]=false;    
            int temp = 0;
            int check=0;
            while (true)
            {
                r = rand.nextInt(itemcount);
                if (!gen[r]) 
                {
                    if (temp + weight[r] <= knapsakCapicity)
                    {
                        gen[r] = true;
                        temp += weight[r];
                    } 
                    else if(check>5)
                    {
                        break;
                    }
                    else
                        check++;
                }

            }
        }
    }
    

    public int weight_of_chromosome() {
        int sum = 0;
        for (int i = 0; i < itemcount; i++) {
            if (gen[i]) {
                sum += weight[i];
            }
        }
        return sum;
    }

    public Chromosome(int w[], int v[], boolean x[], int y, int knapsakCapicity) {
        this.knapsakCapicity=knapsakCapicity;
        itemcount = y;
        for (int i = 0; i < itemcount; i++) {
            weight[i] = w[i];
            value[i] = v[i];
            gen[i] = x[i];
        }

    }

    public void print() {
        for (int i = 0; i < gen.length; i++) {
            if (gen[i]) {
                System.out.print(value[i] + "\t");
            }
        }
        System.out.println();
    }

}
