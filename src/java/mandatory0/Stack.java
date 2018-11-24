package no.uib.ii.inf102.f18.mandatory0;

class Stack{
    public static class ListStack<E>{
        private Node top = null;
        private int size;
        
        private class Node{
            E reference;
            Node next;
        }

        ListStack(){}

        public boolean isEmpty() {
            return top == null;
        }

        public E pop() {
            E item = top.reference;
            top = top.next;
            size--;
            return item;
        }

        public E peek() {
            return top.reference;
        }

        public void push(E item) {
            Node old_top = top;
            top = new Node();
            top.reference = item;
            top.next = old_top;
            size++;
        }

        public int size() {
            return size;
        }

    }
}