package no.uib.ii.inf102.f18.mandatory1;

class IndexMinPQ<Key extends Comparable<Key>> implements IIndexPQ<Key>{

    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    private void swim(int priority) {
        if (priority == 0) return;
        while (priority> 0 && larger((priority-1)/2, priority)){
            exch(priority, (priority-1)/2);
            priority = (priority-1)/2;
        }
    }

    private boolean larger(int priorityI, int priorityJ) {
        return keys[qp[priorityI]].compareTo(this.keys[qp[priorityJ]]) > 0;
    }

    private void sink(int priority) {
        while(2*priority + 1 < n){
            // j is left subnode.
            int j = 2*priority + 1;
            // Set j to largest subnode.
            if(j+1<n && larger(j, j+1)) j++; 
            if(!larger(priority, j)) break;
            exch(priority, j);
            priority = j;
        }
    }

    private void exch(int priorityI, int priorityJ) {
        int keyIndexI = qp[priorityI];
        int keyIndexJ = qp[priorityJ];
        pq[keyIndexI] = pq[keyIndexJ];
        pq[keyIndexJ] = priorityI; 
        qp[priorityI] = keyIndexJ;
        qp[priorityJ] = keyIndexI;
    }

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int maxN){
        keys = (Key[]) new Comparable[maxN];
        pq = new int[maxN];
        qp = new int[maxN];
        for(int i = 0; i<maxN; i++){
            pq[i] = -1;
            qp[i] = -1;
        }
    }

    @Override
    public void add(int keyIndex, Key key) {
        if (contains(keyIndex)) {
			throw new IllegalArgumentException("Index already in priority queue");
		}
        int priority = n;
        qp[priority] = keyIndex;
        keys[keyIndex] = key;
        pq[keyIndex] = priority;
        swim(priority);
        n++;
    }

    @Override
    public void changeKey(int keyIndex, Key key) {
        if (!contains(keyIndex)) {
			throw new IllegalArgumentException("Index not on priority queue");
		}
        keys[keyIndex] = key;
        swim(pq[keyIndex]);
        sink(pq[keyIndex]);
    }

    @Override
    public boolean contains(int keyIndex) {
        // If the key index has a priority then it exists.
        if (pq[keyIndex] != -1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void delete(int keyIndex) {
        if (!contains(keyIndex)) {
			throw new IllegalArgumentException("Index not on priority queue");
		}
        n--;
        int priority = pq[keyIndex];
        int priorityToDelete = n;
        exch(priority, priorityToDelete);
        keyIndex = qp[priorityToDelete];
        keys[keyIndex] = null;
        qp[priorityToDelete] = -1;
        pq[keyIndex] = -1;
        
        if(priority != priorityToDelete){
            swim(priority);
            sink(priority);
        }
    }

    @Override
    public Key getKey(int keyIndex) {
        // Returns the key at the key index.
        if (!contains(keyIndex)) {
			throw new IllegalArgumentException("Index not on priority queue");
		}
        return keys[keyIndex];
    }

    @Override
    public Key peekKey() {
        // Returns the key with highest priority
        if (peek() > -1){
            return keys[peek()];    
        }
        else{
            return null;
        }
    }

    @Override
    public int peek() {
        // Returns key index with highest priority
        if (isEmpty()) {
			throw new IllegalArgumentException("Priority queue is empty");
		}
        return qp[0];
    }

    @Override
    public int poll() {
        // Returns key index with highest priority and removes it from the PQ.
        int keyIndex = peek();
        delete(keyIndex);
        return keyIndex;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }
}