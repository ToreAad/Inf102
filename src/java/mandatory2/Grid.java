package no.uib.ii.inf102.f18.mandatory2;

import java.util.LinkedList;
import java.util.Queue;

public class Grid{
    public static class Coordinate{
        final int row;
        final int col;
        final int steps;

        Coordinate(int row, int col, int steps){ this.row = row; this.col = col; this.steps = steps;}

        boolean equals(Coordinate other){
            return row==other.row && col==other.col;
        }

        Coordinate pluss(Coordinate other){
            return new Coordinate(row+other.row, col+other.col, steps+other.steps);
        }

        Coordinate multiply(int m){
            return new Coordinate(row*m, col*m, steps);
        }
    }

    public static boolean toVisit(Coordinate co, Coordinate t, int[][] visited){
        return (co.row>=0) && (co.col>=0) && (co.row <= t.row) && (co.col <= t.col) && (visited[co.row][co.col] != 1);
    }
    

    public static int solve(Integer[][] grid){
        int maxRow = grid.length;
        int maxCol = grid[0].length;
        int[][] visited = new int[maxRow][maxCol];
        visited[0][0] = 1;
        Queue<Coordinate> queue = new LinkedList<Coordinate>();

        Coordinate target = new Coordinate(maxRow-1, maxCol-1, 0);
        Coordinate up = new Coordinate(0, -1, 1);
        Coordinate down = new Coordinate(0, 1, 1);
        Coordinate left = new Coordinate(-1, 0, 1);
        Coordinate right = new Coordinate(1, 0, 1);

        queue.add(new Coordinate(0, 0, 0));
        while(!queue.isEmpty()){
            Coordinate cc = queue.poll();
            int step = grid[cc.row][cc.col];

            Coordinate[] steps = new Coordinate[]{
                cc.pluss(right.multiply(step)),
                cc.pluss(down.multiply(step)),
                cc.pluss(left.multiply(step)),
                cc.pluss(up.multiply(step))
            };
            
            for(Coordinate co: steps){
                if(!toVisit(co, target, visited)) continue;
                if (co.equals(target)) return co.steps;
                queue.add(co);
                visited[co.row][co.col] = 1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();
        Integer[][] grid = new Integer[n][m];

        for(int i =0; i<n; i++){
            String line = io.getWord();
            for(int j = 0; j < m; j++){
                grid[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        io.print(solve(grid));
        io.flush();
        io.close();
    }
}