package torea;

import java.lang.StringBuilder;

public class UnionFindExercise{
    
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int N = io.getInt();
        int Q = io.getInt();

        UnionFind.QuickUnion qf = new UnionFind.QuickUnion(N);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< Q; i++){
            String command = io.getWord();
            if (command.equals("?")){
                int a = io.getInt();
                int b = io.getInt();
                if (qf.connected(a, b)) sb.append("yes").append("\n");
                else                    sb.append("no").append("\n");
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