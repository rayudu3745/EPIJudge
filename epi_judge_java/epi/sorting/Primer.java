package epi.sorting;

import java.util.Random;
import java.util.stream.IntStream;

public class Primer {

    // time - O(n2) space - O(1)
    public static void selectionSort(int[] arr){
        for (int i = 0 ; i < arr.length; i++){
            for (int j = i; j < arr.length; j++){
                if(arr[j] < arr[i]){
                    swap(arr,i,j);
                }
            }
        }
    }

    // time - O(n2) space - O(1) advantage for partially sorted arrays
    public static void insertionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j > 0 ; j--){
                if(arr[j] < arr[j-1]){
                    swap(arr,j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    // time - O(n2) space - O(1)
    // same logic as insertion sort with better practical performance
    public static void shellSort(int[] arr){
        int h = 1;
        while (h < arr.length / 3){
            h = 3 * h + 1;
        }

        while (h >= 1){
            for(int i = h; i < arr.length; i++){
                for(int j = i; j - h >=0 ; j = j - h){
                    if(arr[j] < arr[j-h]){
                        swap(arr,j,j-h);
                    }else {
                        break;
                    }
                }
            }
            h = h / 3 ;
        }
    }

    private static void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void printArray(int[] arr){
        for (int a : arr){
          System.out.print(a + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(int[] arr){
        if(arr.length < 2) return true;
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] < arr[i-1]) return false;
        }
        return true;
    }

    public static void shuffle(int[] array){
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }



    public static void main(String... args){
        int[] sample = IntStream.rangeClosed(-200,200).toArray();
        System.out.println(isSorted(sample));
        shuffle(sample);
        System.out.println(isSorted(sample));
        shellSort(sample);
        System.out.println(isSorted(sample));
        printArray(sample);
    }


}
