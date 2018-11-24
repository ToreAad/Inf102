package torea;

class UnionFind
{
    public static class QuickFind implements IUnionfind{

        public int count;
        public int[] id;

        QuickFind(int some_count){
            count = some_count;
            id = new int[some_count];
            for(int i = 0; i<id.length; i++) id[i] = i;
        }

        @Override
        public int find(int a) {
            return id[a];
        }

        @Override
        public boolean connected(int a, int b) {
            return id[a] == id[b];
        }

        @Override
        public void union(int a, int b) {
            int id_a = find(a);
            int id_b = find(b);
            if (id_a == id_b) return;

            int min_id = (id_a <= id_b) ? id_a: id_b;
            int max_id = (id_a > id_b) ? id_a: id_b;

            for(int i = 0; i < id.length; i++){
                if (id[i] == max_id) 
                    id[i] = min_id;
            }

            count--;

        }

        @Override
        public int count() {
            return count;
        }
    }

    public static class QuickUnion implements IUnionfind{

        public int count;
        public int[] id;

        QuickUnion(int some_count){
            count = some_count;
            id = new int[some_count];
            for(int i = 0; i<id.length; i++) id[i] = i;
        }

        @Override
        public int find(int a) {
            if (a == id[a]) return a;
            id[a] = find(id[a]);
            return id[a];
            // while ( a != id[a]) a = id[a];
            // return a;
        }

        @Override
        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }

        @Override
        public void union(int a, int b) {
            int id_a = find(a);
            int id_b = find(b);
            if (id_a == id_b) return;

            int min_id = (id_a <= id_b) ? id_a: id_b;
            int max_id = (id_a > id_b) ? id_a: id_b;
            id[max_id] = min_id;

            count--;

        }

        @Override
        public int count() {
            return count;
        }
    }
}