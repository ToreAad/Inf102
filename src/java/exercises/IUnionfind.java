package torea;

interface IUnionfind{
    int find(int a);
    boolean connected(int a, int b); 
    void union(int a, int b);
    int count();
}