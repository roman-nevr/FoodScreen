package org.berendeev.roma.foodscreen;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueensTest {
    public static final int N = 8;

    @Test
    public void solve(){
        for (int i = 0; i < N; i++) {
            List<Queen> queens = new ArrayList<>();
            queens.add(new Queen(i, 0));
            int rest = 7;
//            findNextFreePlace(0, i, rest);
        }
    }

    private void findNextFreePlace(int x, int y, int rest, List<Queen> queens) {
        while (x < N && y < N){
            x++;
            if (x == N){
                x = 0;
                y++;
                if (y == N){
                    return;
                }
            }
//            if ()
        }
    }

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
    }
}
