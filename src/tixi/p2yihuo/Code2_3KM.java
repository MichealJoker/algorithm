package tixi.p2yihuo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 只有一种数出现了K次，其他数都出现M次
 * @author: 姜志豪
 * @date: 2021/12/15-18:33
 * @Version: 1.0.0
 */
public class Code2_3KM {

    //只有一种数出现了K次，其他数都出现M次
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                t[i] +=  ((num >> i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0 && t[i] % m != k) {
                return -1;
            }
            if (t[i] % m != 0) {
                ans += (1<<i);
            }
        }
        return ans;
    }


    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    public static int[] randomArray(int maxkinds, int range, int k, int m) {
        int kTimeNum = randomNumber(range);
        int kinds = (int) (Math.random() * maxkinds) + 2;
        int[] arr = new int[k + (kinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            arr[index] = kTimeNum;
        }
        kinds--;
        Set<Integer> set = new HashSet<>();
        set.add(kTimeNum);
        while (kinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            kinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static int randomNumber(int range) {
        return (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);
    }

    public static void main(String[] args) {
        int maxKinds = 10;
        int range = 200;
        int testTime = 10000;
        int max = 9;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1;
            int b = (int) (Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(maxKinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了");
                return;
            }
        }

    }
}