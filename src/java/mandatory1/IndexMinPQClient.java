package no.uib.ii.inf102.f18.mandatory1;

/**
 * A client for solving the problem https://uib.kattis.com/problems/uib.indexpq
 * using an implementation of the IIndexPQ interface (implementation MUST be a min-PQ)
 *
 * @author Torstein Strømme
 */
public class IndexMinPQClient {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        solve(io);
        io.close();
    }

    private static void solve(Kattio io) {
        final int m = io.getInt();
        final int q = io.getInt();
        int idx, key;

        // Change the line below to initialize with your IndexMinPQ class
        IIndexPQ<Integer> pq = new IndexMinPQ<>(m); // new IndexMinPQ<>(m);

        for (int i = 0; i < q; i++) {
            switch (io.getWord().charAt(0)) {
                case 'A': // add
                    idx = io.getInt();
                    key = io.getInt();
                    if (pq.contains(idx)) {
                        io.print("error\n");
                        return;
                    }
                    pq.add(idx, key);
                    break;

                case 'C': // changeKey
                    idx = io.getInt();
                    key = io.getInt();
                    if (!pq.contains(idx)) {
                        io.print("error\n");
                        return;
                    }
                    pq.changeKey(idx, key);
                    break;

                case 'E': // peek
                    if (pq.isEmpty()) {
                        io.print("error\n");
                        return;
                    }
                    io.print(String.format("%d\n", pq.peek()));
                    break;

                case 'G': // getKey
                    idx = io.getInt();
                    if (!pq.contains(idx)) {
                        io.print("error\n");
                        return;
                    }
                    io.print(String.format("%d\n", pq.getKey(idx)));
                    break;

                case 'R': // remove (delete)
                    idx = io.getInt();
                    if (!pq.contains(idx)) {
                        io.print("error\n");
                        return;
                    }
                    pq.delete(idx);
                    break;

                case 'P': // poll
                    if (pq.isEmpty()) {
                        io.print("error\n");
                        return;
                    }
                    io.print(String.format("%d\n", pq.poll()));
                    break;

                case 'S': // size
                    io.print(String.format("%d\n", pq.size()));
                    break;

                default:
                    throw new RuntimeException("Unknown operator");
            }
        }
    }
}
