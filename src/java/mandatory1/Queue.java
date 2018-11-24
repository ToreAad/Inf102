package no.uib.ii.inf102.f18.mandatory1;

import java.util.Iterator;

class Queue {
    public static class ArrayQueue<Item> implements Iterable<Item>, Iterator<Item>
    {
        private int head;
        private int tail;
        private int size;
        private Item[] array;

        ArrayQueue(){
            initializeArray(16);
        }

        @SuppressWarnings("unchecked")
        private void initializeArray(int max){
            array = (Item[]) new Object[max];
            head = 0;
            tail = 0;
            size = max;
        }

        @SuppressWarnings("unchecked")
        private void resize(int max){
            Item[] old_array = array;
            array = (Item[]) new Object[max];
            
            int elementsToMove = size - freeElements();
            for(int i = 0; i< elementsToMove; i++){
                int oldIndex = (tail+i)%size;
                array[i] = old_array[oldIndex];
            }
            tail = 0;
            head = elementsToMove;
            size = max;
        }
        
        private int freeElements(){
            if (head >= tail){
                return size - (head-tail);
            } else {
                return tail - head;
            }
        }

        public boolean empty() {
            return head == tail;
        }

        public Item dequeue() {
            if(empty()) return null;
            Item out = array[tail];
            array[tail] = null;
            tail = (tail+1)%size;
            
            if((size>16) && (4*freeElements() >= 3*size)){
                resize(size/2);
            }
            return out;
        }

        public Item peek() {
            return array[tail];
        }

        public void enqueue(Item item) {
            array[head] = item;
            head = (head+1)%size;
            if(freeElements()==1){
                resize(size * 2);
            }
        }

        @Override
        public Iterator<Item> iterator() {
			return this;
		}

        @Override
        public boolean hasNext() {
            return !empty();
        }

        @Override
        public Item next() {
            return dequeue();
        }
        

    }

    public static class LinkedListQueue<Item>
    {
        private Node first = null;
        private Node last = null;
        private int n;
        
        private class Node
        {
            Item item;
            Node next;
        }

        public boolean empty() {
            return first == null;
        }

        public Item dequeue() {
            Item item = first.item;
            first = first.next;
            n--;
            if(empty()) last = null;
            return item;
        }

        public Item peek() {
            return first.item;
        }

        public void enqueue(Item item) {
            Node old_last = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if(empty()) first = last;
            else old_last.next = last;
            n++;
        }

    }
}