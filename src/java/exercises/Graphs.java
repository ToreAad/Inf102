package exam;
import java.util.Deque;
import java.util.LinkedList;

import java.util.Stack;
import java.util.Queue;

public class Graphs{
    public static class Unigraph{
        private final int V;
        private int E;
        private LinkedList<Integer>[] adj;

        @SuppressWarnings("unchecked")
        Unigraph(int v){
            V = v;
            adj =  (LinkedList<Integer>[]) new LinkedList[v];
            for(int i = 0; i< V; i++) adj[i] = new LinkedList<Integer>();
        }

        public int V(){return V;}

        public int E(){return E;}

        public void addEdge(int v, int w){
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        Iterable<Integer> adj(int v){
            return adj[v];
        }
    }

    public static class Digraph{
        private final int V;
        private int E;
        private LinkedList<Integer>[] adj;

        @SuppressWarnings("unchecked")
        Digraph(int v){
            V = v;
            adj =  (LinkedList<Integer>[]) new LinkedList[v];
            for(int i = 0; i< V; i++) adj[i] = new LinkedList<Integer>();
        }

        public int V(){return V;}

        public int E(){return E;}

        public void addEdge(int v, int w){
            adj[v].add(w);
            E++;
        }

        Iterable<Integer> adj(int v){
            return adj[v];
        }
        
        public Digraph reverse(){
            Digraph r = new Digraph(V);
            for (int v = 0; v <V; v++){
                for(int w: adj(v)){
                    r.addEdge(w, v);
                }
            }
            return r;
        }
    }

    public static class Edge implements Comparable<Edge>{
        private final int v;
        private final int w;
        private final double weight;

        Edge(int v, int w, double weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        double weight(){return weight;}

        int either(){return v;}

        int other(int v){
            if (this.v == v){
                return w;
            }
            else if (this.w == v) {
                return this.v;
            }
            else{
                throw new IllegalArgumentException();
            }
        }

        @Override
        public int compareTo(Edge that) {
            if (this.weight < that.weight) return -1;
            else if (this.weight > that.weight) return 1;
            else return 0;
        }
    }

    public static class WeightedUnigraph{
        private final int V;
        private int E;
        private LinkedList<Edge>[] adj;

        @SuppressWarnings("unchecked")
        WeightedUnigraph(int v){
            V = v;
            adj =  (LinkedList<Edge>[]) new LinkedList[v];
            for(int i = 0; i< V; i++) adj[i] = new LinkedList<Edge>();
        }

        public int V(){return V;}

        public int E(){return E;}

        public void addEdge(Edge e){
            int v = e.either();
            int w = e.other(v);
            adj[v].add(e);
            adj[w].add(e);
            E++;
        }

        public Iterable<Edge> adj(int v){
            return adj[v];
        }

        public Iterable<Edge> edges(){
            LinkedList<Edge> ll = new LinkedList<Edge>();
            for(int v=0; v<V; v++){
                for(Edge e: adj[v]){
                    if(e.other(v)>v){
                        ll.add(e);
                    }
                }
            }
            return ll;
        }
    }

    public static class Diedge{
        private final int v;
        private final int w;
        private final long weight;

        Diedge(int v, int w, long weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        long weight(){return weight;}

        int from(){return v;}

        int to(){return w;}
    }

    public static class WeightedDigraph{
        private final int V;
        private int E;
        private LinkedList<Diedge>[] adj;

        @SuppressWarnings("unchecked")
        WeightedDigraph(int v){
            V = v;
            adj =  (LinkedList<Diedge>[]) new LinkedList[v];
            for(int i = 0; i< V; i++) adj[i] = new LinkedList<Diedge>();
        }

        public int V(){return V;}

        public int E(){return E;}

        public void addEdge(Diedge e){
            adj[e.from()].add(e);
            E++;
        }

        public Iterable<Diedge> adj(int v){
            return adj[v];
        }

        public Iterable<Diedge> edges(){
            LinkedList<Diedge> ll = new LinkedList<Diedge>();
            for(int v=0; v<V; v++){
                for(Diedge e: adj[v]){
                    ll.add(e);
                }
            }
            return ll;
        }
    }

    public static class DepthFirstPaths{
        private boolean[] marked;
        private int[] edgeTo;
        private final int s;

        public DepthFirstPaths(Unigraph g, int s){
            marked = new boolean[g.V()];
            edgeTo = new int[g.V()];
            this.s = s;
            dfs(g, s);
        }

        private void dfs(Unigraph g, int v){
            marked[v] = true;
            for (int w: g.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    dfs(g, w);
                }
            }
        }

        public DepthFirstPaths(Digraph g, int s){
            marked = new boolean[g.V()];
            edgeTo = new int[g.V()];
            this.s = s;
            dfs(g, s);
        }

        private void dfs(Digraph g, int v){
            marked[v] = true;
            for (int w: g.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    dfs(g, w);
                }
            }
        }

        public DepthFirstPaths(Digraph g, int s, int t){
            marked = new boolean[g.V()];
            edgeTo = new int[g.V()];
            this.s = s;
            dfs(g, s, t);
        }

        private void dfs(Digraph g, int v, int t){
            marked[v] = true;
            for (int w: g.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    if ( w!=t ) dfs(g, w);
                }
            }
        }

        public boolean hasPathTo(int v){return marked[v];}

        public Iterable<Integer> reachable(){
            LinkedList<Integer> r = new LinkedList<>();

            for(int i=0; i<marked.length; i++) if (marked[i]) r.add(i);

            return r;
        }

        public boolean[] marked(){
            return marked;
        }

        public Iterable<Integer> pathTo(int v){
            if(!hasPathTo(v)) return null;
            LinkedList<Integer> path = new LinkedList<>();
            for(int x = v; x != s; x = edgeTo[x]) path.push(x);
            path.push(s);
            return path;
        }
    }

    public static class ShortestPathDijkstra{
        private Diedge[] edgeTo;
        private long[] distTo;
        private IndexMinPQ<Long> pq;

        public ShortestPathDijkstra(WeightedDigraph g, int s){
            edgeTo = new Diedge[g.V()];
            distTo = new long[g.V()];
            pq = new IndexMinPQ<Long>(g.V());
            for(int v = 0; v < g.V(); v++) distTo[v] = Long.MAX_VALUE;
            distTo[s] = 0;

            pq.add(s, 0L);
            while(!pq.isEmpty()) relax(g, pq.poll());
        }

        private void relax( WeightedDigraph g, int v){
            for (Diedge e : g.adj(v)){
                int w = e.to();
                if(distTo[w] > distTo[v] + e.weight()){
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                    else pq.add(w, distTo[w]);
                }
            }
        }

        public ShortestPathDijkstra(WeightedDigraph g, int s, int t){
            edgeTo = new Diedge[g.V()];
            distTo = new long[g.V()];
            pq = new IndexMinPQ<Long>(g.V());
            for(int v = 0; v < g.V(); v++) distTo[v] = Long.MAX_VALUE;
            distTo[s] = 0;

            pq.add(s, 0L);
            while(!pq.isEmpty()) relax(g, pq.poll(), t);
        }

        private void relax( WeightedDigraph g, int v, int t){
            for (Diedge e : g.adj(v)){
                int w = e.to();
                
                if(distTo[v] + e.weight() > distTo[t]) continue;

                if(distTo[w] > distTo[v] + e.weight()){
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                    else if(w != t) pq.add(w, distTo[w]);
                }
            }
        }


        public long distTo(int v){ return distTo[v]; }

        public boolean hasPathTo(int v){ return distTo(v) < Long.MAX_VALUE; }
    }

    public static class DepthFirstOrder{
        private boolean[] marked;

        Deque<Integer> pre;
        Deque<Integer> post;
        Deque<Integer> reversePost;

        private void initialize(int v){
            pre = new LinkedList<Integer>();
            post = new LinkedList<Integer>();
            reversePost = new LinkedList<Integer>();
            marked = new boolean[v];
        }

        public DepthFirstOrder(Digraph g){
            initialize(g.V());
            for (int v = 0; v < g.V(); v++) if (!marked[v]) dfs(g, v);
        }

        public DepthFirstOrder(Digraph g, Iterable<Integer> s){
            initialize(g.V());
            for (int v: s) if (!marked[v]) dfs(g, v);
        }

        public DepthFirstOrder(Digraph g, int s){
            initialize(g.V());
            dfs(g, s);
        }

        public DepthFirstOrder(Digraph g, boolean[] reachable){
            initialize(g.V());
            for (int v = 0; v < g.V(); v++) if (!marked[v] && reachable[v]) dfs(g, v);
        }

        private void dfs(Digraph g, int v){
            pre.addFirst(v);
            marked[v] = true;
            for(int w: g.adj(v)) if (!marked[w]) dfs(g, w);
            post.addFirst(v);
            reversePost.addLast(v);
        }

        public Iterable<Integer> preorder(){ return pre; }

        public Iterable<Integer> postorder(){ return post; }

        public Iterable<Integer> reversePostorder(){ return reversePost; }
    }

    public class DirectedCycle{
        private boolean[] marked;
        private int[] edgeTo;
        private Stack<Integer> cycle;
        private boolean[] onStack;

        private void initialize(int v){
            onStack = new boolean[v];
            edgeTo = new int[v];
            marked = new boolean[v];
        }

        public DirectedCycle(Digraph g){
            initialize(g.V());
            for(int v = 0; v < g.V(); v++) if (!marked[v]) dfs(g, v);
        }


        private void dfs(Digraph g, int v){
            onStack[v] = true;
            marked[v] = true;
            for (int w: g.adj(v)){
                if (this.hasCycle()) return;
                else if(!marked[w]){ 
                    edgeTo[w] = v; 
                    dfs(g, w);
                }
                else if(onStack[w]){
                    cycle = new Stack<Integer>();
                    for(int x = v; x != w; x = edgeTo[x]) cycle.push(x);
                    cycle.push(w);
                    cycle.push(v);
                }
            }
            onStack[v] = false;
        }

        public boolean hasCycle(){
            return cycle != null;
        }

    }

    public class TopologicalSort{
        private Iterable<Integer> order;
        
        public TopologicalSort(Digraph g){
            DirectedCycle cyclefinder = new DirectedCycle(g);
            if(!cyclefinder.hasCycle()){
                DepthFirstOrder dfs = new DepthFirstOrder(g);
                order = dfs.reversePostorder();
            }
        }

        public Iterable<Integer> order(){return order;}

        public boolean hasOrder(){return order != null;}
    }
}