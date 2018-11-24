package no.uib.ii.inf102.f18.mandatory2;

public class PerfectStringHash{
    public static Integer hash(String s, int M, int a){
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (a * hash + s.charAt(i)) % M;
        }
        return hash;
    }

    public static int[] getPerfectHash(String[] arr){
        int[] marr = new int[500];
        int[] mempty = new int[500];

        for( int M = arr.length; M<=500; M++){
            outer: for (int a = 1; a < M; a++){
                System.arraycopy(mempty, 0, marr, 0, M);
                for(String s: arr){
                    int h = hash(s, M, a);
                    if(marr[h] != 0){
                        continue outer;
                    }
                    else marr[h] = -1;
                }
                return new int[]{a, M};
            }
        }
        return new int[]{0,0};
    }

    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        
        int nrStr = io.getInt();
        String[] arrStr = new String[nrStr];

        for(int i = 0; i<nrStr; i++){
            arrStr[i] = io.getLine();
        }

        int[] ph = getPerfectHash(arrStr);

        io.println(String.format("%d %d", ph[0], ph[1]));
        io.flush();
        io.close();
    }
}