package no.uib.ii.inf102.f18.mandatory0;

import java.util.Iterator;

public class TrollBook{
    public static class Page implements Comparable<Page>{
        String page_content;
        int page_number;

        Page(String some_content, int some_number){
            page_content = some_content;
            page_number = some_number;
        }

        public int compareTo(Page o) {
            return Integer.compare(this.page_number, o.page_number);
        }
    }

    public static class TrollBookProcessor{
        SortableLinkedList<Page> tb;

        TrollBookProcessor(){
            tb = new SortableLinkedList<Page>();
        }

        public void add(Page page){
            tb.add(page);
        }

        public String get(){
            String res = "";
            tb.sort();
            Iterator<Page> it = tb.iterator();
            res += it.next().page_content;
            while(it.hasNext()){
                res += " "+it.next().page_content;
            }
            return res;
        }

        public void print(Kattio io){
            tb.sort();
            Iterator<Page> it = tb.iterator();
            while(it.hasNext()){
                io.print(it.next().page_content+"\n");
            }
        }
    }

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        int nrPages = io.getInt();

        TrollBookProcessor tp = new TrollBookProcessor();

        for(int i = 0; i< nrPages; i++){
            String page_content = io.getWord();
            int page_number = io.getInt();
            tp.add(new Page(page_content, page_number));
        }

        tp.print(io);
        io.flush();
        io.close();
    }
}