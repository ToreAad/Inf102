package torea;

import java.lang.StringBuilder;

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

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nrUsers; i++){
            sb.append(qf.id[i]).append(" ");
            // if (i != nrUsers - 1) sb.append(" ");
        }
        io.print(sb);
        io.flush();
        io.close();
    }
}