package no.uib.ii.inf102.f18.mandatory2;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Builddeps{

    public static String solve(int n, String[] rules, String changed){
        StringTokenizer[] dependencies = new StringTokenizer[n];
        HashMap<String, Integer> str2int = new HashMap<String, Integer>();
        String[] lut = new String[n];
        
        for (int i = 0; i<n; i++){
            String line = rules[i];
            int colPos = line.indexOf(':');
            if (colPos+1 != line.length()) dependencies[i] = new StringTokenizer(line.substring(colPos+1));
            lut[i] = line.substring(0, colPos);;
            str2int.put(lut[i], i);
        }

        Graphs.Digraph g = new Graphs.Digraph(n);
        for (int i = 0; i<n; i++){
            if(dependencies[i] == null) continue;
            StringTokenizer st = dependencies[i];
            while(st.hasMoreTokens()){
                String dep = st.nextToken();
                int w = str2int.get(dep);
                g.addEdge( w, i);
            }
        }

        Graphs.DepthFirstOrder dfs = new Graphs.DepthFirstOrder(g, str2int.get(changed));

        StringBuilder sb = new StringBuilder();
        for(int i: dfs.postorder()){
            sb.append(lut[i]).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int n = Integer.parseInt(io.getLine());
        String[] rules = new String[n];

        for(int i = 0; i< n; i++){
            rules[i] = io.getLine();
        }

        String changed = io.getLine();

        io.print(solve(n, rules, changed));
        io.flush();
        io.close();
    }

}