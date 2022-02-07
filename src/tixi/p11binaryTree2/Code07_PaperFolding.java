package tixi.p11binaryTree2;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/20-19:02
 * @Version: 1.0.0
 */
public class Code07_PaperFolding {


    /**
     * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，
     * 即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
     * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
     * 例如:N=1时，打印: down N=2时，打印: down down up
     *
     * N=1              down
     * N=2      down    down      up
     * N=3 down down up down down up up
     * 就是二叉树中序遍历，一个节点的左边是down，右边是up
     */


    public static void printAllFolds(int N){
        process(1,N,true);
    }

    public static void process(int i,int N,boolean down){
        if(i>N){
            return ;
        }
        process(i+1,N,true);
        System.out.print(down?"down ":"up ");
        process(i+1,N,false);
    }

    public static void main(String[] args) {
        printAllFolds(1);
        System.out.println();
        printAllFolds(2);
        System.out.println();
        printAllFolds(3);
    }
}