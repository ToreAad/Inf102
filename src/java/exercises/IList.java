package torea;

interface IList<E>{
    void add(E e);
    E get(int id);
    void insert(int id, E e);
    int size();
}