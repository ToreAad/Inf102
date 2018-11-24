package torea;

import java.lang.StringBuilder;
import java.util.Iterator;

public class TrollBook{
    public static class Page implements Comparable<Page>
    {
        String page_content;
        int page_number;

        Page(String some_content, int some_number){
            page_content = some_content;
            page_number = some_number;
        }

        @Override
        public int compareTo(Page o) {
            return Integer.compare(this.page_number, o.page_number);
        }
    }

    public static class TrollBookProcessor
    {
        SortableLinkedList<Page> tb;

        TrollBookProcessor(){
            tb = new SortableLinkedList<Page>();
        }

        public void add(Page page){
            tb.add(page);
        }

        public String get(){
            StringBuilder sb = new StringBuilder();
            tb.sort();
            Iterator<Page> it = tb.iterator();
            sb.append(it.next().page_content);
            while(it.hasNext()){
                sb.append(" ").append(it.next().page_content);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args)
    {
        Kattio io = new Kattio(System.in, System.out);
        int nrPages = io.getInt();

        TrollBookProcessor tp = new TrollBookProcessor();

        for(int i = 0; i< nrPages; i++){
            String page_content = io.getWord();
            int page_number = io.getInt();
            tp.add(new Page(page_content, page_number));
        }

        io.print(tp.get());
        io.flush();
        io.close();
    }
}