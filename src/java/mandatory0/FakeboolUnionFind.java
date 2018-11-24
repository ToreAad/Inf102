package no.uib.ii.inf102.f18.mandatory0;

public class FakeboolUnionFind{
    
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int nrUsers = io.getInt();
        int nrQueries = io.getInt();

        UnionFind.QuickUnion qf = new UnionFind.QuickUnion(nrUsers);
        
        for(int i = 0; i< nrQueries; i++){
            String command = io.getWord();
            if (command.equals("LEGAL")){
                int id = io.getInt();
                io.print(qf.find(id));
                io.print("\n");
            }
            else {
                int a = io.getInt();
                int b = io.getInt();
                qf.union(a, b);
            }
        }
        io.flush();
        io.close();
    }
}