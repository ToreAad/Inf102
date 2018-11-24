package torea;

import java.util.Iterator;
// import java.util.Stack;

public class SortableLinkedList<E extends Comparable<E>> implements ISortableList<E> {

    public Node<E> first = null;
    public Node<E> last = null;
    public int size = 0;

    public static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        E reference;
        Node<E> next;

        Node(E some_reference) {
            reference = some_reference;
        }

        public void linkTo(Node<E> other) {
            next = other;
        }

        public void placeBehind(Node<E> other) {
            linkTo(other.next);
            other.linkTo(this);
        }

        @Override
        public int compareTo(Node<E> o) {
            return reference.compareTo(o.reference);
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    public class NodeIterator implements Iterator<E> {
        Node<E> current;

        NodeIterator(Node<E> some_current) {
            current = some_current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E reference = current.reference;
            current = current.next;
            return reference;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new NodeIterator(first);
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    public void appendNode(Node<E> new_node) {
        insertNode(size, new_node);
    }

    public void insertNode(int index, Node<E> new_node) {
        Node<E> old_node;

        if (index == 0) {
            old_node = first;
            new_node.linkTo(old_node);
            first = new_node;
        } else {
            old_node = getNode(index - 1);
            new_node.linkTo(old_node.next);
            old_node.linkTo(new_node);
        }

        if (old_node == last) {
            last = new_node;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        Node<E> new_node = new Node<E>(element);
        insertNode(index, new_node);
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public Node<E> getNode(int index) {
        if (index == 0)
            return first;
        if (index == size - 1)
            return last;
        Node<E> next = first.next;
        for (int i = 1; i <= index; i++) {
            if (i == index)
                return next;
            next = next.next;
        }
        return null;
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index);
        if (node != null) {
            return node.reference;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Node<E> removeNode(int index) {
        Node<E> remove_node = null;

        if (index == 0) {
            remove_node = first;

            if (remove_node == last) {
                last = null;
                first = null;
            } else {
                first = remove_node.next;
            }
        } else {
            Node<E> front_node = getNode(index - 1);
            remove_node = front_node.next;
            front_node.linkTo(remove_node.next);
            if (remove_node == last) {
                last = front_node;
            }
        }
        size--;
        return remove_node;
    }

    @Override
    public E remove(int index) {
        E return_reference = null;
        Node<E> remove_node = null;
        remove_node = removeNode(index);

        if (remove_node != null) {
            return_reference = remove_node.reference;
        }

        return return_reference;
    }

    @Override
    public int size() {
        return size;
    }

    public static <E extends Comparable<E>> boolean less(Node<E> a, Node<E> b) {
        return a.compareTo(b) < 0;
    }

    public void merge(SortableLinkedList<E> other) {
        size = size + other.size;

        Node<E> na = first;
        Node<E> nb = other.first;

        if (less(this.last, other.last)) {
            last = other.last;
        }

        Node<E> current_node = new Node<E>(null);
        first = current_node;

        while (na != null && nb != null) {
            if (less(na, nb)) {
                current_node.linkTo(na);
                na = na.getNext();
            } else {
                current_node.linkTo(nb);
                nb = nb.getNext();
            }
            current_node = current_node.next;
        }
        if (na != null) {
            current_node.linkTo(na);
        } else if (nb != null) {
            current_node.linkTo(nb);
        }

        first = first.next;

        other.clear();
    }

    public boolean hasNext() {
        return first.next != null;
    }

    public void swap(Node<E> a, Node<E> b) {
        E ref = a.reference;
        a.reference = b.reference;
        b.reference = ref;
    }

    public static <E extends Comparable<E>> void addToStack(Stack.ListStack<SortableLinkedList<E>> ssStack,
            SortableLinkedList<E> new_sequence) {
        while ((!ssStack.isEmpty()) && (ssStack.peek().size == new_sequence.size)) {
            SortableLinkedList<E> stack_top = ssStack.pop();
            new_sequence.merge(stack_top);
        }
        ssStack.push(new_sequence);
    }

    @Override
    public void sort() {

        Stack.ListStack<SortableLinkedList<E>> ssStack = new Stack.ListStack<SortableLinkedList<E>>();
        Node<E> current_node = first;

        while (current_node != null && current_node.next != null) {
            if (less(current_node.next, current_node)) {
                swap(current_node.next, current_node);
            }
            SortableLinkedList<E> current_sequence = new SortableLinkedList<E>();
            current_sequence.first = current_node;
            current_sequence.last = current_node.next;
            current_node = current_node.next.next;
            current_sequence.last.next = null;
            current_sequence.size = 2;
            addToStack(ssStack, current_sequence);
        }

        if (current_node != null) {
            SortableLinkedList<E> current_sequence = new SortableLinkedList<E>();
            current_sequence.first = current_node;
            current_sequence.last = current_node;
            current_sequence.size = 1;
            addToStack(ssStack, current_sequence);
        }

        while (ssStack.size() > 1) {
            SortableLinkedList<E> current_sequence = ssStack.pop();
            current_sequence.merge(ssStack.pop());
            ssStack.push(current_sequence);
        }

        SortableLinkedList<E> final_sorted_sequence = ssStack.pop();

        first = final_sorted_sequence.first;
        last = final_sorted_sequence.last;
        size = final_sorted_sequence.size;
    }

    @Override
    public E[] toArray(E[] a) {
        E[] arr = (E[]) new Object[size];

        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }

}