import java.util.HashMap;

public class Solution {

    // Simple function that returns
    // count of subarrays of arr with
    // XOR value equals to m
    static long subarrayXorBruteApproach(int arr[],
            int n, int m) {

        // Initialize ans
        long ans = 0;

        // Pick starting point i of
        // subarrays
        for (int i = 0; i < n; i++) {

            // Store XOR of current
            // subarray
            int xorSum = 0;

            // Pick ending point j of
            // subarray for each i
            for (int j = i; j < n; j++) {

                // calculate xorSum
                xorSum = xorSum ^ arr[j];

                // If xorSum is equal to
                // given value, increase
                // ans by 1.
                if (xorSum == m)
                    ans++;
            }
        }

        return ans;
    }

    static long subarrayXorEfficientApproach(int arr[], int n, int m) {
        long ans = 0; // Initialize answer to be returned
 
        // Create a prefix xor-sum array such that
        // xorArr[i] has value equal to XOR
        // of all elements in arr[0 ..... i]
        int[] xorArr = new int[n];
 
        // Create map that stores number of prefix array
        // elements corresponding to a XOR value
        HashMap<Integer, Integer> mp
            = new HashMap<Integer, Integer>();
 
        // Initialize first element of prefix array
        xorArr[0] = arr[0];
 
        // Computing the prefix array.
        for (int i = 1; i < n; i++)
            xorArr[i] = xorArr[i - 1] ^ arr[i];
 
        // Calculate the answer
        for (int i = 0; i < n; i++) {
            // Find XOR of current prefix with m.
            int tmp = m ^ xorArr[i];
 
            // If above XOR exists in map, then there
            // is another previous prefix with same
            // XOR, i.e., there is a subarray ending
            // at i with XOR equal to m.
            ans = ans
                  + (mp.containsKey(tmp) == false
                         ? 0
                         : ((long)mp.get(tmp)));
 
            // If this subarray has XOR equal to m itself.
            if (xorArr[i] == m)
                ans++;
 
            // Add the XOR of this subarray to the map
            if (mp.containsKey(xorArr[i]))
                mp.put(xorArr[i], mp.get(xorArr[i]) + 1);
            else
                mp.put(xorArr[i], 1);
        }
 
        // Return total count of subarrays having XOR of
        // elements as given value m
        return ans;
    }

    // Driver code
    public static void main(String args[]) {

        int[] arr = { 4, 2, 2, 6, 4 };
        int n = arr.length;
        int m = 6;

        System.out.println("Number of subarrays"
                + " having given XOR is "
                + subarrayXorBruteApproach(arr, n, m));
        System.out.println("Number of subarrays"
                + " having given XOR is "
                + subarrayXorEfficientApproach(arr, n, m));
    }
}