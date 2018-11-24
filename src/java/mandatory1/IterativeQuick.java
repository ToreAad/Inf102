package no.uib.ii.inf102.f18.mandatory1;

class IterativeQuick extends Quick{

    public static void sort(Comparable[] arr, int lb, int ub) {
        shuffle(arr);
        quickSort(arr, lb, ub);
    }

    public static void sort(Comparable[] arr) {
        shuffle(arr);
        quickSort(arr, 0, arr.length);
    }

    protected static void quickSort(Comparable[] arr,  int lb, int ub) {
        Stack.ListStack<Integer[]> sortStack = new Stack.ListStack<Integer[]>();
        sortStack.push(new Integer[]{lb, ub});

        while(!sortStack.isEmpty()){
            Integer[] bounds = sortStack.pop();
            int pivotPosition = partition(arr, bounds[0], bounds[1]);
            if (pivotPosition - bounds[0] > 1){
                sortStack.push(new Integer[]{bounds[0], pivotPosition});
            }
            if (bounds[1] - pivotPosition > 1){
                sortStack.push(new Integer[]{pivotPosition + 1, bounds[1]});
            } 
        }
    }
}