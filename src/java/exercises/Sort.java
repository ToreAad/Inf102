package torea;

class Sort{
    public static void swap(Object[] array, int i, int j){
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T extends Comparable<T>> void StupidSort(T[] array, int array_start, int array_end){
        for(int i = array_start; i<array_end-1; i++){
            for(int j = i+1; j<array_end; j++){
                if (array[i].compareTo(array[j]) > 0 ){
                    swap(array, i,j);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void InsertionSort(T[] array, int array_start, int array_end){
        for(int i = array_start; i < array_end; i++){
            for(int m = i-1; m >= 0; m--){
                if (array[m].compareTo(array[m+1]) > 0 ){
                    swap(array, m, m+1);
                }
                else{
                    break;
                }
            }
        }
    }

    // public static <T extends Comparable<T>> void MergeSort(T[] array, T[] array_out, int array_start, int array_end){
    //     if (array_end - array_start <= 1) return;
    //     int mid = ((array_end - array_start)/2) + array_start;
    //     MergeSort(array, array_out, array_start, mid);
    //     MergeSort(array, array_out, mid, array_end);

    //     int k = array_start;
    //     int i = array_start;
    //     int j = mid;

    //     while(k < array_end){
    //         if( i == mid){
    //             while(j<array_end){
    //                 array_out[k++] = array[j++];
    //             }
    //             break;
    //         }
    //         if (j == array_end){
    //             while(i < mid){
    //                 array_out[k++] = array[i++];
    //             }
    //             break;
    //         }

    //         if( array[i].compareTo(array[j]) > 0){
    //             array_out[k++] = array[i++];
    //         }
    //         else{
    //             array_out[k++] = array[j++];
    //         }
    //     }

    //     for(int m = array_start; m < array_end-1; m++ ){
    //         array[m] = array_out[m];
    //     }

    //     return;
    // }
}