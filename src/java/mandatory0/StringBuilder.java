package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

public class StringBuilder{
    // Using this implementation of StringBuilder did not speed my code up.
        SortableLinkedList<String> sll;
        int size;

        StringBuilder(){
            size = 0;
            sll = new SortableLinkedList<String>();
        }

        public void append(String str){
            size += str.length();
            sll.add(str);
        }

        public String toString(){
            char[] char_array = new char[size];
            int index = 0;

            Iterator<String> it = sll.iterator();

            while(it.hasNext()){
                String cur_str = it.next();
                int cur_size = cur_str.length();
                System.arraycopy(cur_str.toCharArray(), 0, char_array, index, cur_size);
                index += cur_size;
            }
            return String.valueOf(char_array);
        }
}