package epi.searching;

import java.util.List;

public class Primer {


    public static int binarySearch(int key, List<Integer> elements){

        // L,U lower and upper indexes
        int L = 0 , U = elements.size() - 1;

        while (L <= U){
            int M = L + (U - L) / 2;
            if (elements.get(M).equals(key)) return M;
            if(elements.get(M) > key){
                U = M - 1;
            }else {
                L = M + 1;
            }
        }

        // not found
        return -1;

    }
}
