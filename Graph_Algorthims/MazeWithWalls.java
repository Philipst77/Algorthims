import java.util.*;

public class MazeWithWalls {

    static final int ROWS = 4;
    static final int COLS = 4;

    static class Cell {
        boolean topWall = false;
        boolean bottomWall = false;
        boolean leftWall = false;
        boolean rightWall = false;
        boolean isStart = false;
        boolean isGoal = false;
        int row, col;

        Cell(int r, int c) {
            this.row = r;
            this.col = c;
        }

        String getLabel() {
            if (isStart) return " S ";
            if (isGoal) return " G ";
            return String.format("%2d ", (row + 1) * 10 + (col + 1));
        }

        String getId() {
            return (row + 1) + "" + (col + 1); // e.g., "11", "23"
        }
    }

    static Cell[][] maze = new Cell[ROWS][COLS];
    static boolean[][] visitedDFS = new boolean[ROWS][COLS];
    static boolean[][] visitedBFS = new boolean[ROWS][COLS];
    static List<String> dfsPath = new ArrayList<>();
    static List<String> bfsPath = new ArrayList<>();

    public static void main(String[] args) {
        initMaze();
        addWalls();
        setStartGoal();
        printMaze();

        dfs(0, 0);
        System.out.println("DFS Traversal Order: " + dfsPath);

        bfs(0, 0);
        System.out.println("BFS Traversal Order: " + bfsPath);
    }

    static void initMaze() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    static void setStartGoal() {
        maze[0][0].isStart = true; // cell 11
        maze[1][2].isGoal = true;  // cell 23
    }

    static void addWalls() {
        maze[0][2].rightWall = true;
        maze[0][3].leftWall = true;

        maze[0][1].bottomWall = true;
        maze[1][1].topWall = true;

        maze[1][0].rightWall = true;
        maze[1][1].leftWall = true;

        maze[1][2].bottomWall = true;
        maze[2][2].topWall = true;

        maze[2][3].bottomWall = true;
        maze[3][3].topWall = true;

        maze[1][2].topWall = true;
        maze[1][2].rightWall = true;
        maze[1][3].leftWall = true;

        // Wall between 31 (2,0) and 32 (2,1)
        maze[2][0].rightWall = true;
        maze[2][1].leftWall = true;
    }

    static void dfs(int row, int col) {
        if (row < 0 || col < 0 || row >= ROWS || col >= COLS || visitedDFS[row][col]) return;

        visitedDFS[row][col] = true;
        dfsPath.add(maze[row][col].getId());

        if (maze[row][col].isGoal) return;

        // EAST
        if (col + 1 < COLS && !maze[row][col].rightWall && !maze[row][col + 1].leftWall)
            dfs(row, col + 1);
        // NORTH
        if (row - 1 >= 0 && !maze[row][col].topWall && !maze[row - 1][col].bottomWall)
            dfs(row - 1, col);
        // WEST
        if (col - 1 >= 0 && !maze[row][col].leftWall && !maze[row][col - 1].rightWall)
            dfs(row, col - 1);
        // SOUTH
        if (row + 1 < ROWS && !maze[row][col].bottomWall && !maze[row + 1][col].topWall)
            dfs(row + 1, col);
    }

    static void bfs(int startRow, int startCol) {
        Queue<Cell> queue = new LinkedList<>();
        queue.add(maze[startRow][startCol]);
        visitedBFS[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            bfsPath.add(current.getId());

            if (current.isGoal) break;

            int row = current.row;
            int col = current.col;

            // EAST
            if (col + 1 < COLS && !maze[row][col].rightWall && !maze[row][col + 1].leftWall && !visitedBFS[row][col + 1]) {
                visitedBFS[row][col + 1] = true;
                queue.add(maze[row][col + 1]);
            }
            // NORTH
            if (row - 1 >= 0 && !maze[row][col].topWall && !maze[row - 1][col].bottomWall && !visitedBFS[row - 1][col]) {
                visitedBFS[row - 1][col] = true;
                queue.add(maze[row - 1][col]);
            }
            // WEST
            if (col - 1 >= 0 && !maze[row][col].leftWall && !maze[row][col - 1].rightWall && !visitedBFS[row][col - 1]) {
                visitedBFS[row][col - 1] = true;
                queue.add(maze[row][col - 1]);
            }
            // SOUTH
            if (row + 1 < ROWS && !maze[row][col].bottomWall && !maze[row + 1][col].topWall && !visitedBFS[row + 1][col]) {
                visitedBFS[row + 1][col] = true;
                queue.add(maze[row + 1][col]);
            }
        }
    }

    static void printMaze() {
        for (int i = 0; i < ROWS; i++) {
            // Top walls
            for (int j = 0; j < COLS; j++) {
                System.out.print(maze[i][j].topWall ? "+---" : "+   ");
            }
            System.out.println("+");

            // Left wall + content
            for (int j = 0; j < COLS; j++) {
                System.out.print(maze[i][j].leftWall ? "|" : " ");
                System.out.print(maze[i][j].getLabel());
            }
            System.out.println("|");
        }

        // Bottom border
        for (int j = 0; j < COLS; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
