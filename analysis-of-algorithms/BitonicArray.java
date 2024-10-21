/*
 * Interview Questions: Analysis of Algorithms
 * -------------------------------------------
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers. Write a program
 * that, given a bitonic array of N distinct integer values, determines whether
 * a given integer is in the array.
 *
 * Standard version: Use ~3lgN compares in the worst case.
 * Signing bonus: Use ~2lgN compares in the worst case (and prove that no
 * algorithm can guarantee to perform fewer than ~2lgN compares in the worst case).
 */

import java.util.Arrays;

public class BitonicArray {
    public static void main(String[] args) {
        int number = 2;
        int[] numbers = {1, 3, 8, 12, 4, 2};       
        int peakIndex = findPeakIndex(numbers);
        
        int[] leftArray = Arrays.copyOfRange(numbers, 0, peakIndex + 1);
        int[] rightArray = Arrays.copyOfRange(numbers, peakIndex + 1, numbers.length);

        // Perform binary search on both parts
        if (numberExists(number, leftArray, true)) {
            System.out.println(number + " exists in the array.");
        } else if (numberExists(number, rightArray, false)) {
            System.out.println(number + " exists in the array.");
        } else {
            System.out.println(number + " does not exist in the array.");
        }
    }

    private static int findPeakIndex(int[] numbers) {
        int left = 0;
        int right = numbers.length-1;
        
        while (left < right) {
            int mid = (right + left) / 2;

            if (numbers[mid] < numbers[mid + 1]) {
                left = mid + 1; // Move right
            } else {
                right = mid; // Move left
            }
        }
        return left;
    }

    private static boolean numberExists(int number, int[] numbers, boolean isIncreasing) {
        int left = 0;
        int right = numbers.length-1;

        while (left <= right) {
            int mid = (right + left) / 2;
            
            if (numbers[mid] == number) {
                return true;
            } 
            
            if (isIncreasing) {
                if (numbers[mid] > number) {
                    right = mid - 1; // Move left
                } else {
                    left = mid + 1; // Move right 
                }
            } else {
                if (numbers[mid] > number) {
                    left = mid + 1; // Move right
                } else {
                    right = mid - 1; // Move left 
                }
            }
        }
        return false;
    }
}