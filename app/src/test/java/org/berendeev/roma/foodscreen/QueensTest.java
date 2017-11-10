package org.berendeev.roma.foodscreen;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class QueensTest {
    public static final int N = 8;
    private int number = 0;

    @Test
    public void solve() throws InterruptedException {
        Queen[] queens = new Queen[N];
        solve(queens, 0);
    }

    private String getThreadName(){
        return Thread.currentThread().getName();
    }

    private void solve(Queen[] queens, int iter){
        if (iter == N){
            number++;
            printSolution(queens);
            return;
        }
        int y = iter;
        for (int x = 0; x < N; x++) {
            if (isFreeCell(x, y, queens, iter)){
                queens[iter] = new Queen(x, y);
                solve(queens, iter + 1);
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

    private boolean isFreeCell(int px, int py, Queen[] queens, int number){
        for (int index = 0; index < number; index++) {
            Queen queen = queens[index];
            if (queen.x == px || queen.y == py) {
                return false;
            } else if (queen.xPlusY() == px + py) {
                return false;
            } else if (queen.xMinusY() == px - py) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(Queen[] queens){
        System.out.println("solution N: " + number);
        for (int i = 0; i < N; i++) {
            System.out.print("+-");
        }
        System.out.println("+");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (queens[y].x == x){
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
