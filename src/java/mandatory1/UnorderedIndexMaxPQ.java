package no.uib.ii.inf102.f18.mandatory1;

import java.util.NoSuchElementException;

/**
 * A (silly) implementation of the IIndexPQ interface. This is just for your
 * reference, you do not need to touch it unless you really want to.
 * 
 * 
 * @author Torstein Strømme
 *
 * @param <Key>
 */
public class UnorderedIndexMaxPQ<Key extends Comparable<Key>> implements IIndexPQ<Key> {
	private int size = 0;
    @SuppressWarnings("unchecked")
    private Key[] keys = (Key[]) new Comparable[100];
	private int[] indices = new int[100];

	public void add(int index, Key key) {
		if (this.contains(index)) {
			throw new IllegalArgumentException("Index already in priority queue");
		}
		this.keys[this.size] = key;
		this.indices[this.size++] = index;
	}

	public boolean contains(int index) {
		return findIndex(index) >= 0;
	}
	
	private int findIndex(int index) {
		for (int i = 0; i < this.size; i++) {
			if (this.indices[i] == index) {
				return i;
			}
		}
		return -1;
	}

	public void changeKey(int index, Key key) {
		int i = findIndex(index);
		if (i < 0) {
			throw new IllegalArgumentException("Index not on priority queue");
		}

		this.keys[i] = key;
	}

	public void delete(int index) {
		int i = findIndex(index);
		if (i < 0) {
			throw new IllegalArgumentException("Index not on priority queue");
		}
		
		swap(i, this.size---1); // Don't ever do this...
		this.keys[this.size] = null;
		this.indices[this.size] = -1;
	}

	private void swap(int i, int j) {
		int tmp_index = this.indices[i];
		this.indices[i] = this.indices[j];
		this.indices[j] = tmp_index;
		
		Key tmp_key = this.keys[i];
		this.keys[i] = this.keys[j];
		this.keys[j] = tmp_key;
	}

	public Key getKey(int index) {
		int i = findIndex(index);
		if (i < 0) {
			throw new IllegalArgumentException("Index not in priority queue");
		}
		return this.keys[i];
	}

	public Key peekKey() {
		int i = findMax();
		return this.keys[i];
	}
	
	private int findMax() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Priority queue is empty");
		}
		
		int besti = 0;
		for (int i = 1; i < this.size; i++) {
			if (less(besti, i)) {
				besti = i;
			}
		}

		return besti;
	}

	private boolean less(int i, int j) {
		return this.keys[i].compareTo(this.keys[j]) < 0;
	}

	public int peek() {
		int i = findMax();
		return this.indices[i];
	}

	public int poll() {
		int res = peek();
		delete(res);
		return res;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

}
