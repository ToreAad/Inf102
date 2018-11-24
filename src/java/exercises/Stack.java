package torea;

class Stack{
    public static class ListStack<E> implements IStack<E>
    {
        private Node top = null;
        private int size;
        
        private class Node
        {
            E reference;
            Node next;
        }

        ListStack(){}

        @Override
        public boolean isEmpty() {
            return top == null;
        }

        @Override
        public E pop() {
            E item = top.reference;
            top = top.next;
            size--;
            return item;
        }

        @Override
        public E peek() {
            return top.reference;
        }

        @Override
        public void push(E item) {
            Node old_top = top;
            top = new Node();
            top.reference = item;
            top.next = old_top;
            size++;
        }

        @Override
        public int size() {
            return size;
        }

    }
}