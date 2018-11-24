package no.uib.ii.inf102.f18.mandatory2;

public class Bumped{

    public static long solve(Graphs.WeightedDigraph roads,
                            int[][] flights, 
                            int start, int end){   
        Graphs.ShortestPathDijkstra toAirport = new Graphs.ShortestPathDijkstra(roads, start, end);
        long minDistance = toAirport.distTo(end);
        for(int[] con: flights){
            if (toAirport.distTo(con[0])>=minDistance) continue;
            Graphs.ShortestPathDijkstra fromAirport = new Graphs.ShortestPathDijkstra(roads, con[1], end);
            long dist = toAirport.distTo(con[0]) + fromAirport.distTo(end);
            if(dist<0) continue; // Integer overflow :(
            minDistance = (dist < minDistance) ? dist : minDistance;
        }
        return minDistance;                            
    };

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();
        int f = io.getInt();
        int s = io.getInt();
        int t = io.getInt();

        Graphs.WeightedDigraph roads = new Graphs.WeightedDigraph(n);
        for(int i =0; i<m; i++){
            int v = io.getInt();
            int w = io.getInt();
            int weight = io.getInt();
            roads.addEdge(new Graphs.Diedge(v, w, weight));
            roads.addEdge(new Graphs.Diedge(w, v, weight));
        }

        int[][] flights = new int[f][2];
        for(int i=0; i<flights.length; i++){
            flights[i][0] = io.getInt();
            flights[i][1] = io.getInt();
        }

        io.print(solve(roads, flights, s, t ));
        io.flush();
        io.close();
    }
}