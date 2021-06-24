package life;

import static life.Universe.printUniverse;

public class Generation {
    private static int[][] universe = Universe.getUniverse();
    private static int size = Universe.getUniverse_size();

    public Generation(){
        Universe.setUniverse(evolution());
        universe = Universe.getUniverse();
    }

    public static void setUniverse(int[][] universe) {
        Generation.universe = universe;
    }

    public static int countAlive(){
        int alive = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(universe[i][j] == 0){
                    alive++;
                }
            }
        }
        return alive;
    }

    private int[][] evolution(){
        int[][] newUniverse = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int[] cell = new int[]{i, j};
                int neighboursAlive = cellDetector(cell);
                //System.out.println("for cell: "+ Arrays.toString(cell)+" "+ neighboursAlive); //test with 3 36 err here
                if(cellCondition(neighboursAlive, cell)){
                    newUniverse[i][j] = 0;//alive
                }else{
                    newUniverse[i][j] = 1;//dead
                }
            }
        }
        return newUniverse;
    }
    private boolean cellCondition(int neighboursAlive, int[] cell){
        int cellinUniverse = universe[cell[0]][cell[1]];
        if(cellinUniverse == 0 && (neighboursAlive == 2 || neighboursAlive == 3)){
            return true;
        }else if(cellinUniverse == 1 && neighboursAlive == 3){
            return true;
        }
        return false;
    }
    //correct
    private int cellDetector(int[] cell){
        //if not border cell
        int i = cell[0];
        int j = cell[1];
        int neighboursAlive = 0;
        if(i < size - 1 && i > 0
                && j < size - 1 && j > 0){
            if(universe[i-1][j] == 0) neighboursAlive++;//N
            if(universe[i-1][j+1] == 0) neighboursAlive++;//NE
            if(universe[i+1][j] == 0) neighboursAlive++;//S
            if(universe[i][j+1] == 0) neighboursAlive++;//E
            if(universe[i+1][j+1] == 0) neighboursAlive++;//SE
            if(universe[i-1][j-1] == 0) neighboursAlive++;//NW
            if(universe[i][j-1] == 0) neighboursAlive++;//W
            if(universe[i+1][j-1] == 0) neighboursAlive++;//SW

        }else if(i == 0 && j > 0 && j < size - 1){
            //top row
            if(universe[i][j-1] == 0) neighboursAlive++;//W
            if(universe[i+1][j-1] == 0) neighboursAlive++;//SW
            if(universe[i+1][j] == 0) neighboursAlive++;//S
            if(universe[i+1][j+1] == 0) neighboursAlive++;//SE
            if(universe[i][j+1] == 0) neighboursAlive++;//E

            if(universe[size-1][j] == 0) neighboursAlive++;//N
            if(universe[size-1][j + 1] == 0) neighboursAlive++;//NE
            if(universe[size-1][j - 1] == 0) neighboursAlive++;//NW

        }else if(i == size - 1 && j > 0 && j < size - 1){
            //bottom row
            if(universe[i-1][j] == 0) neighboursAlive++;//N
            if(universe[i-1][j-1] == 0) neighboursAlive++;//NW
            if(universe[i-1][j+1] == 0) neighboursAlive++;//NE
            if(universe[i][j+1] == 0) neighboursAlive++;//E
            if(universe[i][j-1] == 0) neighboursAlive++;//W

            if(universe[0][j] == 0) neighboursAlive++;//S
            if(universe[0][j + 1] == 0) neighboursAlive++;//SE
            if(universe[0][j - 1] == 0) neighboursAlive++;//SW

        }else if(i > 0 && i < size - 1 && j == 0){
            //first column
            if(universe[i+1][j] == 0) neighboursAlive++;//S
            if(universe[i-1][j] == 0) neighboursAlive++;//N
            if(universe[i+1][j+1] == 0) neighboursAlive++;//SE
            if(universe[i-1][j+1] == 0) neighboursAlive++;//NE
            if(universe[i][j+1] == 0) neighboursAlive++;//E

            if(universe[i+1][size-1] == 0) neighboursAlive++;//SW
            if(universe[i][size-1] == 0) neighboursAlive++;//W
            if(universe[i-1][size-1] == 0) neighboursAlive++;//NW

        }else if(i > 0 && i < size - 1 && j == size - 1){
            //last column
            if(universe[i+1][j] == 0) neighboursAlive++;//S
            if(universe[i-1][j] == 0) neighboursAlive++;//N
            if(universe[i-1][j-1] == 0) neighboursAlive++;//NW
            if(universe[i+1][j-1] == 0) neighboursAlive++;//SW
            if(universe[i][j-1] == 0) neighboursAlive++;//W

            if(universe[i+1][0] == 0) neighboursAlive++;//SE
            if(universe[i][0] == 0) neighboursAlive++;//E
            if(universe[i-1][0] == 0) neighboursAlive++;//NE

        }else if(i == 0 && j == 0){
            //up left corner
            if(universe[i+1][j] == 0) neighboursAlive++;//S
            if(universe[i+1][j+1] == 0) neighboursAlive++;//SE
            if(universe[i][j+1] == 0) neighboursAlive++;//E

            if(universe[size-1][0] == 0) neighboursAlive++;//N
            if(universe[size-1][1] == 0) neighboursAlive++;//NE
            if(universe[size-1][size-1] == 0) neighboursAlive++;//NW
            if(universe[i][size-1] == 0) neighboursAlive++;//W
            if(universe[i+1][size-1] == 0) neighboursAlive++;//SW

        }else if(i == 0 && j == size - 1){
            //up right corner
            if(universe[i][j-1] == 0) neighboursAlive++;//W
            if(universe[i+1][j-1] == 0) neighboursAlive++;//SW
            if(universe[i+1][j] == 0) neighboursAlive++;//S

            if(universe[0][0] == 0) neighboursAlive++;//E
            if(universe[1][0] == 0) neighboursAlive++;//SE
            if(universe[size-1][size-1] == 0) neighboursAlive++;//N
            if(universe[size-1][size-2] == 0) neighboursAlive++;//NW
            if(universe[size-1][0] == 0) neighboursAlive++;//NE

        }else if(i == size - 1 && j == 0){
            //bottom left corner
            if(universe[i-1][j+1] == 0) neighboursAlive++;//NE
            if(universe[i][j+1] == 0) neighboursAlive++;//E
            if(universe[i-1][j] == 0) neighboursAlive++;//N

            if(universe[0][size-1] == 0) neighboursAlive++;//SW
            if(universe[0][0] == 0) neighboursAlive++;//S
            if(universe[0][1] == 0) neighboursAlive++;//SE
            if(universe[size-2][size-1] == 0) neighboursAlive++;//NW
            if(universe[size-1][size-1] == 0) neighboursAlive++;//W

        }else if(i == size - 1 && j == size - 1){
            //bottom right corner
            if(universe[i-1][j] == 0) neighboursAlive++;//N
            if(universe[i-1][j-1] == 0) neighboursAlive++;//NW
            if(universe[i][j-1] == 0) neighboursAlive++;//W

            if(universe[0][size-2] == 0) neighboursAlive++;//SW
            if(universe[0][size-1] == 0) neighboursAlive++;//S
            if(universe[0][0] == 0) neighboursAlive++;//SE
            if(universe[size-2][0] == 0) neighboursAlive++;//NE
            if(universe[size-1][0] == 0) neighboursAlive++;//E
        }
        return neighboursAlive;
    }
}
