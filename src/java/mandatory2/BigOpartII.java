package no.uib.ii.inf102.f18.mandatory2;

import java.util.SplittableRandom;

/**
 * Here is a collection of functions; you should analyze the runtime of each of them in terms
 * of how many steps each function performs.
 *
 * @author Torstein Strømme
 */
@SuppressWarnings("WeakerAccess")
public class BigOpartII {
    private SplittableRandom random = new SplittableRandom(0);
    private int steps = 0;

    private void step() { this.steps++; }

    public int tally() {
        int res = this.steps;
        this.steps = 0;
        return res;
    }


    public void a(int n, int m) {
        for (int i = 0; i < 2*n; i++) {
            step();
        }
        for (int i = 0; i < m; i++) {
            step();
        }
    }

    public void b(int n, int m) {
        for (int i = 0; i < n; i++) {
            step();
            for (int j = 0; j < m; j++) {
                step();
            }
        }
    }

    public void c(int n, int m) {
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j < m) {
                step();
                j++;
            }
        }
    }

    public void d(int n, int m) {
        if (n > 0) {
            for (int i = 0; i < m; i++) {
                step();
            }
            d(n / 2, m);
        }
    }

    public void e(int n, int m) {
        if (n == 0 && m == 0) {
            step();
        }
        else if (n >= m) {
            e(n-1, m);
        }
        else {
            e(m, n);
        }
    }

    public void f(int n, int m) {
        for (int i = 0; i < n; i += 2) {
            step();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                step();
            }
        }
    }

    public void g(int n, int k) {
        boolean[] seen = new boolean[n];
        int[] queue = new int[n];

        seen[0] = true;
        int head = 0;
        int tail = 1;
        while (head < tail) {
            int u = queue[head++];
            step();
            for (int i = 0; i < k; i++) {
                step();
                int v = (u + 1 + 13 * i) % n;
                if (!seen[v]) {
                    seen[v] = true;
                    queue[tail++] = v;
                }
            }
        }
    }

    public void h(int n, int k) {
        if (n > 0 && k > 0) {
            step();
            step();
            h(n-1, k-1);
        }
        else if (n > 0) {
            step();
            h(n-1, k);
        }
        else if (k > 0) {
            step();
            h(n, k - 1);
        }
    }

    public void i(int n, int k) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int l = 0; l < k; l++) {
                    step();
                }
            }
        }
    }

    public void j(int n, int k) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2*n; j++) {
                step();
            }
            for (int j = 0; j < k; j++) {
                step();
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                step();
            }
            for (int j = 0; j < k; j++) {
                step();
            }
        }
    }

    public void k(int n, int m) {
        int[] a = new int[n];
        for (int i = 0; i < m; i++) {
            a[random.nextInt(n)] += 1;
        }

        for (int i = 0; i < n; i++) {
            step();
            for (int j = 0; j < a[i]; j++) {
                step();
            }
        }
    }

    public void l(int n, int m) {
        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            step();
            a[i] = i;
        }

        for (int i = 0; i < m; i++) {
            step();
            int j = random.nextInt(n/2+1, n+1);
            a[j] = -i;
            while (j > 0 && a[j] < a[j/2]) {
                int t = a[j];
                a[j] = a[j/2];
                a[j/2] = t;
                j /= 2;
            }
        }
    }

    public void m(int n, int m) {
        for (int i = 0; i < m; i += n) {
            step();
        }
    }

    public void n(int n, int k) {
        while (n > 0) {
            step();
            n /= k;
        }
    }

    public void o(int n, int k) {
        while (n > 0) {
            step();
            n -= 2 * k;
        }
    }

    public void p(int n, int k) {
        if (n > 0) {
            step();
            for (int i = 0; i < k; i++) {
                p(n-1, k);
            }
        }
    }
}
