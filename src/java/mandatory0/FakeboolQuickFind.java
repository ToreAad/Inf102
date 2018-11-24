package no.uib.ii.inf102.f18.mandatory0;

public class FakeboolQuickFind{
    
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int nrUsers = io.getInt();
        int nrUnions = io.getInt();

        UnionFind.QuickFind qf = new UnionFind.QuickFind(nrUsers);

        for(int i = 0; i< nrUnions; i++){
            int a = io.getInt();
            int b = io.getInt();
            qf.union(a, b);
        }

        for(int i = 0; i < nrUsers; i++){
            io.print(qf.id[i]);
            io.print("\n");
        }
        io.flush();
        io.close();
    }
}