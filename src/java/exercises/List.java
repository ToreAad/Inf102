package torea;

public class List{
    public static class ArrayList<E> implements IList<E>
    {
        private int top;
        private int size;
        private E[] array;
        private boolean fixed_size = false;

        ArrayList(){
            top = 0;
            resize(16);
        }
        
        ArrayList(int size){
            top = 0;
            resize(size);
            fixed_size = true;
        }

        @SuppressWarnings("unchecked")
        private void resize(int max){
            if(!fixed_size){
                E[] old_array = array;
                array = (E[]) new Object[max];
                if(old_array != null){
                    System.arraycopy(old_array, 0, array, 0, top+1);
                }
                size = max;
            }
        }

        @Override
        public void add(E e) {
            if (size == top){
                resize(2*size);
            }
            array[top++] = e;
        }

        @Override
        public E get(int id) {
            return array[id];
        }

        @Override
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

        @Override
        public int size() {
            return top;
        }

    }
}