package exam;

import java.util.HashMap;


class Grandpabernie{
    public static class RBBST{
        Node root;

        class Node{
            Node left, right;
            int key;
            int val;
            int size;
            boolean color;
    
            Node(int key, int val, int s, boolean color){
                this.key = key;
                this.color = color;
                this.val = val;
                size = s;
            }

            public int index(){
                if( right != null){
                    return size - right.size;
                }
                return size;
            }
        }

        public int get_key(int i){
            return get_key(root, i);
        }

        public int get_key(Node h, int i){
            int lb, ub;
            if (h.left != null){
                lb = h.left.size+1;
            }
            else{
                lb = i;
            }
            ub = lb + h.val;

            if( i >= lb && i < ub){
                return h.key;
            }

            if( i < h.index()){
                return get_key(h.left, i);
            }
            else{
                return get_key(h.right, i-h.index());
            }
        }

        boolean isRed(Node h){
            if(h==null) return false;
            return h.color == true;

        }
        
        Node rotateLeft(Node h){
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = true;
            x.size = h.size;
            h.size = h.val + size(h.left) + size(h.right);
            return x;
        }
        
        int size(Node h){
            if (h==null) return 0;
            return h.size;
        }

        Node rotateRight(Node h){
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = true;
            x.size = h.size;
            h.size = h.val + size(h.left) + size(h.right);
            return x;
        }

        void flipColors(Node h){
            h.color = true;
            h.left.color = false;
            h.right.color = false;
        }

        public void insert(int ins_key, int val){
            root = insert(root, ins_key, val);
            root.color = false;
        }

        RBBST(int ins_key){
            insert(ins_key, 1);
        }

        public Node insert(Node h, int ins_key, int val){
            if(h==null) return new Node(ins_key, val, 1, true);

            int cmp = Integer.compare(ins_key, h.key);
            if(cmp<0) h.left = insert(h.left, ins_key, val);
            else if(cmp>0) h.right = insert(h.right, ins_key, val);
            else h.val += val;

            if( isRed(h.right) && ! isRed(h.left)) h = rotateLeft(h);
            if( h.left != null && isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            if( isRed(h.right) && isRed(h.left)) flipColors(h);

            h.size = size(h.left) + size(h.right) + h.val;

            return h;
        }
    }

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();

        HashMap<String, RBBST> h = new HashMap<>(n);

        for(int i = 0; i < n; i++){
            String country = io.getWord();
            int year = io.getInt();
            if(h.containsKey(country)){
                h.get(country).insert(year, 1);
            }
            else{
                RBBST temp = new RBBST(year);
                h.put(country, temp);
            }
        }

        int q = io.getInt();
        for(int i = 0; i<q; i++){
            String country = io.getWord();
            int kthTrip = io.getInt();
            io.print(h.get(country).get_key(kthTrip));
            io.print('\n');
        }
        io.flush();
        io.close();

    }
}