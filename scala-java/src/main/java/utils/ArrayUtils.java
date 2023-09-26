package utils;

public class ArrayUtils {
    public static void printArray(int[] arr){
        System.out.println(getArrayAsString(arr));
    }

    public static String getArrayAsString(int[] arr){
        StringBuilder msg = null;
        if(arr != null){
            msg = new StringBuilder("Array: [");
            for(int i = 0; i < arr.length; i++) {
                int temp = arr[i];
                if(i < arr.length - 1)
                    msg.append(temp + ", ");
                else
                    msg.append(temp + "]");
            }
        }
        else
            msg = new StringBuilder("[NULL ARRAY]");
        return msg.toString();
    }
}
