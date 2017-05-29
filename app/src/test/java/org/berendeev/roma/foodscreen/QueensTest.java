package org.berendeev.roma.foodscreen;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueensTest {
    public static final int N = 9;

    @Test
    public void solve(){
        List<Queen> queens = new ArrayList<>();
        solve(queens);
    }

    private void solve(List<Queen> queens){
        int size = queens.size();
        if (size == N){
            printSolution(queens);
            return;
        }
        int y = size;
        for (int x = 0; x < N; x++) {
            if (isFreeCell(x, y, queens)){
                queens.add(new Queen(x, y));
                solve(queens);
                queens.remove(size);
            }
        }
    }

//    private void findNextFreePlace(int x, int y, int rest, List<Queen> queens) {
//        while (x < N && y < N){
//            x++;
//            if (x == N){
//                x = 0;
//                y++;
//                if (y == N){
//                    return;
//                }
//            }
//            if (isFreeCell(x, y, queens)){
//                queens.add(new Queen(x, y));
//                rest--;
//                if (rest == 0){
//                    System.out.println(queens);
//                }else {
//                    findNextFreePlace(x, y, rest, queens);
//                }
//            }
//        }
//    }

    private boolean isFreeCell(int px, int py, List<Queen> queens){
        boolean isFree = true;
        for (Queen queen : queens) {
            if (queen.x == px || queen.y == py) {
                isFree = false;
            } else if (queen.xPlusY() == px + py) {
                isFree = false;
            } else if (queen.xMinusY() == px - py) {
                isFree = false;
            }
        }
        return isFree;
    }

    private void printSolution(List<Queen> queens){
        for (int i = 0; i < N; i++) {
            System.out.print("+-");
        }
        System.out.println("+");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (queens.get(y).x == x){
                    System.out.print("|W");
                }else {
                    System.out.print("|o");
                }
            }
            System.out.println("|");
        }
        for (int i = 0; i < N; i++) {
            System.out.print("+-");
        }
        System.out.println("+");
    }

    class Queen{
        int x, y;

        public Queen(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int xMinusY(){
            return x - y;
        }

        public int xPlusY(){
            return x + y;
        }

        @Override public String toString() {
            return "x = " + x + ", y = " + y;
        }
    }
}
