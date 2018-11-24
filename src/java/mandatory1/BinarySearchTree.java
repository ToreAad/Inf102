package no.uib.ii.inf102.f18.mandatory1;


/**
 * This is the BST implementation from lecture.
 *
 * @author Øyvind
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements ISymTable<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int size;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }

    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = insert(node.left, key, value);
        } else if (compare > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public Value get(Key key) {
        Node node = root;
        while(node != null){
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;        
    }

    // private Value find(Node node, Key key) {
    //     if (node == null) {
    //         return null;
    //     }

    //     int compare = key.compareTo(node.key);
    //     if (compare < 0) {
    //         return find(node.left, key);
    //     } else if (compare > 0) {
    //         return find(node.right, key);
    //     } else {
    //         return node.value;
    //     }
    // }

    @Override
    public boolean containsKey(Key key) {
        return get(key) != null;
    }


    private Node minimum(Node node) {
        if (node == null) {
            // this is a bad situation
            throw new IllegalArgumentException("Can't find minimum in a non-existing tree!");
        }
        if (node.left == null) {
            return node; // we have no left children, so this must be the smallest
        }
        return minimum(node.left);
    }

    private Node deleteMinimum(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Can't delete minimum in a non-existing tree!");
        }
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMinimum(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void delete(Key key) {
        root = deleteKey(root, key);
    }

    private Node deleteKey(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = deleteKey(node.left, key);
        } else if (compare > 0) {
            node.right = deleteKey(node.right, key);
        } else {
            // this is the node that we want to delete
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // we have children, so we need to promote a child into this spot
            // our choices are either the smallest node in the right subtree,
            // or the biggest node in the left subtree (can you see why?)


            // To "move" the smallest right node into this position, we
            // 1. find it
            // 2. delete it from the subtree
            // 3. fix the links so the tree remains intact
            Node minRight = minimum(node.right);
            minRight.right = deleteMinimum(node.right);
            minRight.left = node.left;

            // finally we set the node we return to be the minRight node
            node = minRight;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Iterable<Key> keys() {
        Queue.ArrayQueue<Key> queue = new Queue.ArrayQueue<>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node node, Queue.ArrayQueue<Key> queue) {
        if (node == null) return;
        keys(node.left, queue);
        queue.enqueue(node.key);
        keys(node.right, queue);
    }

}
