package no.uib.ii.inf102.f18.mandatory1;


public class List{
    public static class ArrayList<E>{
        private int top;
        private int size;
        private E[] array;

        ArrayList(){
            top = 0;
            resize(16);
        }

        @SuppressWarnings("unchecked")
        private void resize(int max){
            E[] old_array = array;
            array = (E[]) new Object[max];
            if(old_array != null){
                System.arraycopy(old_array, 0, array, 0, top);
            }
            size = max;
        }

        public void add(E e) {
            if (size == top){
                resize(2*size);
            }
            array[top++] = e;
        }

        public E get(int id) {
            return array[id];
        }

        public void insert(int id, E e) {
            if (size == top){
                resize(2*size);
            }
            for (int i = top; i>=id; i--){    
                array[i] = array[i+1];
            }
            array[id] = e;
            top++;
        }

        public void remove(int id){
            if (size == top){
                resize(2*size);
            }
            for (int i = top; i>id; i--){    
                array[i-1] = array[i];
            }
            top--;
        }

        public int size() {
            return top;
        }

    }
}