/*
 * Interview Questions: Analysis of Algorithms
 * -------------------------------------------
 * Design an algorithm for the 3-SUM problem that takes time proportional to N2
 * in the worst case. You may assume that you can sort the N integers in time
 * proportional to N2 or better.
 */

import java.util.Arrays;

public class ThreeSum {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 1, 4, -2, 0};
        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 2; i++) {
            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];

                if (sum == 0) {
                    System.out.println(numbers[i] + ", " + numbers[left] + ", " + numbers[right]);
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; 
                } else {
                    right--; 
                }
            }
        }
    }
}
