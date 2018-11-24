package no.uib.ii.inf102.f18.mandatory1;

/**
 * Insertion sort has a worst-case runtime of O(n^2), however if the array is "almost sorted", the
 * runtime is linear; i.e. if the array has k inversion, then the runtime is O(n+k). Insertion sort
 * is the preferred sorting method if the array is either short (<= 15 elements) or has a small
 * number of inversions.
 *
 * @author Torstein Strømme
 */
@SuppressWarnings("unchecked")
public class Insertion {

    /**
     * Sort the array using insertion sort.
     *
     * @param arr the array to be sorted.
     */
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length);
    }

    /**
     * Sort the sub-array between lb and ub using insertion sort.
     *
     * @param arr array to be sorted
     * @param lb lowerbound (inclusive)
     * @param ub upperbound (exclusive)
     */
    public static void sort(Comparable[] arr, int lb, int ub) {
        for (int i = lb; i < ub; i++) {
            /*
            Invariant: everything to the left of i is sorted
            Want to insert e = arr[i] in correct spot
            Strategy: move everything bigger than i up by one spot,
            insert e in the available slot.
            */
            Comparable e = arr[i];
            int j = i;
            while (--j >= lb && e.compareTo(arr[j]) < 0) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = e;
        }
    }
}
