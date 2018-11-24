package no.uib.ii.inf102.f18.mandatory2;

public class Wheresmyinternet{
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int nrHouses = io.getInt();
        int nrCables = io.getInt();
        
        UnionFind.QuickUnion uf = new UnionFind.QuickUnion(nrHouses);
        for(int i = 0; i<nrCables; i++){
            uf.union(io.getInt()-1, io.getInt()-1);
        }

        boolean all_connected = true;
        for(int i = 1; i<nrHouses; i++){
            if( !uf.connected(0, i)){
                all_connected = false;
                io.println(i+1);
            }
        }
        if(all_connected){
            io.print("Connected");
        }
        io.flush();
        io.close();
    }
}