package dataStructures.stack.chapterProblems;

public class Problem_22_to23 {

    public static void main(String[] args) {
        // Problem 22: Finding spans (of an array)
        // The span S[i] of A[i] is the maximum number of consecutive
        // elements A[j] immediately preceding A[i] such that A[j]<= A[i]
         int[] arr = new int[]{6,3,4,5,2};
        findingSpans(arr);
    }

    // Problem 22: Finding span for the array
    // Brute force: For each element, go down the array to find which A[j] <= A[i]
    // Time  complexity: O[N^2]
    // Space complexity: O[1]
    private static void findingSpans(int[] arr) {
        int[] span = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            int numSpans = 0;
            for(int j = i; j >= 0; j--){
                if(arr[j] <= arr[i])
                    numSpans += 1;
            }
            span[i] = numSpans;
        }
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i] + "\t" + span[i]);
        }
    }
}
