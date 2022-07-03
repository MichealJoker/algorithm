package tixi.p18dp1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author: jzh
 * @date: created in 2022/2/15
 * @description:
 * @version: 1.0
 */
public class Code02_CardsInLine {

//    给定一个整型数组arr，代表数值不同的纸牌排成一条线
//玩家A和玩家B依次拿走每张纸牌
//规定玩家A先拿，玩家B后拿
//但是每个玩家每次只能拿走最左或最右的纸牌
//玩家A和玩家B都绝顶聪明
//请返回最后获胜者的分数。
//[50,100,20,10] A会为了不让B拿100，先拿10


    public static int win1(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f(arr, 0, arr.length - 1);
        int second = g(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    //arr[L R] 先手获得最好的分数返回
    public static int f(int[] arr,int L,int R){
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L]+g(arr,L+1,R);
        int p2 = arr[R]+g(arr,L,R-1);
        return Math.max(p1,p2);
    }

    public static int g(int[] arr,int L,int R){
        if(L==R){
            return 0;
        }
        int p1 = f(arr,L+1,R);
        int p2 = f(arr,L,R-1);
        return Math.min(p1,p2);
    }


    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }

    // // arr[L..R]，后手获得的最好分数返回
    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
            int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }
    public static void main(String[] args) {

        int[] arr= {50,100,20,10};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }


    public static int win3(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        int N = arr.length;
        int[][] famp = new int[N][N];
        int[][] gamp = new int[N][N];
        for(int i=0;i<N;i++){
            famp[i][i]=arr[i];
        }
        for(int startCol = 1;startCol<N;startCol++){
            int row = 0;
            int col = startCol;
            while (col<N){
                famp[row][col] =  Math.max(arr[row]+ gamp[row+1][col],arr[col]+gamp[row][col-1]);
                gamp[row][col] =  Math.min(famp[row+1][col],famp[row][col-1]);
                row++;
                col++;
            }
        }
        return Math.max(famp[0][N-1],gamp[0][N-1]);
    }
}
