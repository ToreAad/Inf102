package exam;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Getshorty{
    static class WeightedVertex implements Comparable<WeightedVertex>{
        int vertex;
        double weight;
        WeightedVertex(int v, double w){
            vertex = v;
            weight = w;
        }

        @Override
        public int compareTo(WeightedVertex o) {
            return Integer.compare(vertex, o.vertex);
        }
    }

    static class TopologicalSort{
        LinkedList<WeightedVertex>[] al;
        boolean[] visited;
        LinkedList<Integer> res;

        TopologicalSort(LinkedList<WeightedVertex>[] al, int source){
            this.al = al;
            res = new LinkedList<>();
            visited = new boolean[al.length];
            visit(source);
        }

        void visit(int v){
            visited[v] = true;

            for(WeightedVertex d: al[v]){
                if(visited[d.vertex]) continue;
                visit(d.vertex);
            }
            res.push(v);
        }

        Iterable<Integer> sorted(){return res;}
    }

    static void relax(LinkedList<WeightedVertex>[] al, int i, double[] distTo){
        for(WeightedVertex d: al[i]){
            distTo[d.vertex] = Math.max(distTo[i]*d.weight, distTo[d.vertex]);
        }
    }

    static class SPDAG{
        LinkedList<WeightedVertex>[] al;
        double[] distTo;

        SPDAG(LinkedList<WeightedVertex>[] al){
            this.al = al;
            double[] distTo = new double[al.length];
            distTo[0] = 1;
            for(int i: new TopologicalSort(al, 0).sorted()){
                relax(al, i, distTo);
            }
        }

        public double distTo(int i){
            return distTo[i];
        }
    }

    static class LazyDijk{
        LinkedList<WeightedVertex>[] al;
        double[] distTo;
        boolean[] visited;
        int target;
        PriorityQueue<WeightedVertex> pq;

        void relax(int i){
            for(WeightedVertex d: al[i]){
                double new_dist = distTo[i] + d.weight;
                if(new_dist < distTo[d.vertex]){
                    distTo[d.vertex] = new_dist;
                    pq.add(new WeightedVertex(d.vertex, new_dist));
                }
                
            }
        }

        LazyDijk(LinkedList<WeightedVertex>[] al, int source, int target){
            this.al = al;
            this.target = target;

            distTo = new double[al.length];
            for(int i = 1; i<al.length; i++) distTo[i] = Double.POSITIVE_INFINITY;
            visited = new boolean[al.length];
            // distTo[0] = 1;

            pq = new PriorityQueue<>();
            pq.add(new WeightedVertex(source, 0));

            while(!pq.isEmpty()){
                WeightedVertex s = pq.poll();
                // if (s.vertex == target) break;
                relax(s.vertex);
            }
        }

        public double distTo(int i){
            return distTo[i];
        }
    }

    static public class BellsSP{
        LinkedList<WeightedVertex>[] al;
        double[] distTo;
        boolean[] onQueue;
        int target;
        LinkedList<Integer> queue;

        void relax(int i){
            for(WeightedVertex d: al[i]){
                double new_dist = distTo[i]+d.weight;
                if (new_dist >= distTo[d.vertex]) continue;
                distTo[d.vertex] = new_dist;

                if (onQueue[d.vertex]) continue;
                queue.add(d.vertex);
                onQueue[d.vertex] = true;

                
            }
        }

        BellsSP(LinkedList<WeightedVertex>[] al, int source, int target){
            this.al = al;
            this.target = target;

            distTo = new double[al.length];
            for(int i = 1; i<al.length; i++) distTo[i] = Double.POSITIVE_INFINITY;
            onQueue = new boolean[al.length];
            queue = new LinkedList<>();
            queue.add(source);
            onQueue[source] = true;

            while(!queue.isEmpty()){
                int v = queue.poll();
                onQueue[v] = false;
                relax(v);
            }
        }

        public double distTo(int i){
            return distTo[i];
        }
    }

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();

        while(n != 0){
            LinkedList<WeightedVertex>[] al = new LinkedList[n];
            for(int i = 0; i< n; i++) al[i] = new LinkedList<WeightedVertex>();
            for(int i = 0; i < m; i++) {
                int s = io.getInt();
                int d = io.getInt();
                double w = -1.0*Math.log(io.getDouble());
                // double w = io.getDouble();
                al[s].add(new WeightedVertex(d, w));
                al[d].add(new WeightedVertex(s, w));
            }
    
            LazyDijk bs = new LazyDijk(al, 0, n-1);
            io.print(String.format ("%.4f", Math.exp(-1.0*bs.distTo(n-1))));
            // io.print(String.format ("%.4f", bs.distTo(n-1)));
            io.print('\n');
            n = io.getInt();
            m = io.getInt();
        }
        io.flush();
        io.close();
    }
}