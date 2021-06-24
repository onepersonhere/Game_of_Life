package life;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Universe {
    private static int[][] universe;
    private static int[][] originalUniverse;
    private static int universe_size;
    private static long seed;
    private static int M;

    public Universe(){
        setup();
        populateUniverse();
        originalUniverse = universe;
    }

    private static void setup(){
        //System.out.println("Please input universe parameters:");
        Scanner scanner = new Scanner(System.in);
        universe_size = 100; //scanner.nextInt();
        //seed = scanner.nextLong();
        //M = scanner.nextInt(); //num of gens
        universe = new int[universe_size][universe_size];
        for(int[] i: universe){
            Arrays.fill(i, 1);
        }
    }
    private static void populateUniverse(){
        Random rand = new Random();
        for(int i = 0; i < universe_size; i++) {
            for (int j = 0; j < universe_size; j++) {
                if(rand.nextBoolean()){
                    universe[i][j] = 0;
                }
            }
        }
    }
    public static void printUniverse(){
        for(int i = 0; i < universe_size; i++){
            for (int j = 0; j < universe_size; j++){
                if(universe[i][j] == 0){
                    System.out.print("O");//alive
                }else if(universe[i][j] == 1){
                    System.out.print(" ");//dead
                }
            }
            System.out.println();
        }
    }

    public static int[][] getUniverse() {
        return universe;
    }

    //public static int getM() {
    //    return M;
    //}

    public static int getUniverse_size() {
        return universe_size;
    }

    public static void setUniverse(int[][] universe) {
        Universe.universe = universe;
    }

    public static int[][] getOriginalUniverse() {
        return originalUniverse;
    }
}
