package tixi.p14GreedyAlgorithm2;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/26-15:25
 * @Version: 1.0.0
 */
public class Code04_IPO {

    /**
     * 输入: 正数数组costs、正数数组profits、正数K、正数M
     * costs[i]表示i号项目的花费
     * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
     * K表示你只能串行的最多做k个项目
     * M表示你初始的资金
     * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
     * 输出：你最后获得的最大钱数。
     */

    //就是游戏思路，一直选打得过的BOSS里收益最大的那个
    //小根堆 按花费排序，初始的时候把所有项目都塞进去
    //大根堆 按利润排序，初始空
    //然后把所有花费小于等于资金的 从小根堆取出，塞进大根堆
    //做大根堆堆顶的项目，一次循环结束

    public static class Program{
        int cost;
        int profit;
        public Program(int cost,int profit){
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class MinComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost-o2.cost;
        }
    }

    public static class MaxComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int findMaximizedCapital(int K, int money, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new MinComparator());
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new MaxComparator());
        for(int i=0;i<Profits.length;i++){
            minCostQueue.add(new Program(Capital[i],Profits[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= money) {
                maxProfitQueue.add(maxProfitQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                return money;
            }
            money += maxProfitQueue.poll().profit;
        }
        return money;
    }
}