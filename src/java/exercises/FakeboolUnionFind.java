package torea;

import java.lang.StringBuilder;

public class FakeboolUnionFind{
    
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int nrUsers = io.getInt();
        int nrQueries = io.getInt();

        UnionFind.QuickUnion qf = new UnionFind.QuickUnion(nrUsers);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< nrQueries; i++){
            String command = io.getWord();
            if (command.equals("LEGAL")){
                int id = io.getInt();
                sb.append(qf.find(id)).append("\n");
            }
            else {
                int a = io.getInt();
                int b = io.getInt();
                qf.union(a, b);
            }
        }
        io.print(sb);
        io.flush();
        io.close();
    }
}