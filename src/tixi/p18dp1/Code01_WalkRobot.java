package tixi.p18dp1;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: jzh
 * @date: created in 2022/2/14
 * @description:
 * @version: 1.0
 */
public class Code01_WalkRobot {

//    假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
//    开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
//    如果机器人来到1位置，那么下一步只能往右来到2位置；
//    如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
//    如果机器人来到中间位置，那么下一步可以往左走或者往右走；
//    规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
//    给定四个参数 N、M、K、P，返回方法数。

    public static int ways1(int N,int start,int aim,int K){
        return process1(start,K,aim,N);
    }

    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    public static int process1(int cur,int rest,int aim,int N){
        if (rest == 0) {
            return aim == cur ? 1 : 0;
        }
        if(cur==1){
            return process1(2,rest-1,aim,N);
        }else if(cur==N){
            return process1(N-1,rest-1,aim,N);
        }else{
            return process1(cur+1,rest-1,aim,N)+
                    process1(cur-1,rest-1,aim,N);
        }
    }
    //子过程里出现重复的就能开始用动态规划
    //比如 rest相等 cur=2 朝右走， cur=4朝左走之后就会变成重复的

    //傻缓存,但是所有重复过程只算一次
    //也叫 从顶向下的动态规划/记忆化搜索
    // 找出哪些可变参数能决定返回值，缓存它
    //cur : 1~N
    //rest : 0~K
    public static int ways2(int N,int start,int aim,int K){
        int[][]dp = new int[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        //等于-1就代表之前没算过
        return process2(start,K,aim,N,dp);
    }


    public static int process2(int cur,int rest,int aim,int N,int[][]dp){
        if (dp[cur][rest] != -1) {//之前算过，直接返回
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = aim == cur ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }


    public static int ways3(int N,int start,int aim,int K){
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][]dp = new int[N+1][K+1];
        dp[aim][0] = 1;//填完第一行 00010000
        //                        00101000 然后下一行长这样
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest-1];//第一格只依赖右上
            for(int cur = 2;cur<N;cur++){
                dp[cur][rest] = dp[cur-1][rest-1]+dp[cur+1][rest-1];
            }
            dp[N][rest] = dp[N-1][rest-1];//最后一格只依赖左上
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5,2,4,6 ));
        System.out.println(ways2(4,2,4,4 ));
        System.out.println(ways3(5,2,4,6 ));
    }
}
