package torea;

class Tree{
    public static class BTree<E>{
        public E payload;
        public BTree<E> rightChild;
        public BTree<E> leftChild;

        BTree(E something){
            payload = something;
        }
    }
}