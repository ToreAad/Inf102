package no.uib.ii.inf102.f18.mandatory1;

import java.util.stream.Stream;

public class TrollBook {

    static class Page implements Comparable<Page> {
        private String w;
        private int pgno;

        Page(String w, int pgno) {
            this.w = w;
            this.pgno = pgno;
        }

        public int compareTo(Page that) {
            return this.pgno - that.pgno;
        }
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        Page[] pages = new Page[n];

        for (int i = 0; i < n; i++) {
            pages[i] = new Page(io.getWord(), io.getInt());
        }

        IterativeQuick.sort(pages);

        String res = String.join(" ", Stream.of(pages).map(p -> p.w).toArray(String[]::new));
        System.out.println(res);
    }
}
