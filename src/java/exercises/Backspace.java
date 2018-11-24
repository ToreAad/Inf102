package torea;

// import java.util.Stack;
import java.lang.StringBuilder;

public class Backspace{
    public static void main(String[] args)
    {
        Kattio sc = new Kattio(System.in, System.out);
        String input = sc.getWord();
        String output = removeBackspaceAlt(input);
        sc.println(output);
        sc.close();
    }

    public static String removeBackspace(String phrase){
        int nrToErase = 0;
        StringBuilder res = new StringBuilder();
        for(int i = phrase.length()-1; i >= 0; i--){
            Character c = phrase.charAt(i);
            if (c == '<'){
                nrToErase++;
            } 
            else {
                if (nrToErase > 0){
                    nrToErase--;
                    continue;
                } 
                else {
                    // chrStack.push(c);
                    res.append(c);
                }
            }
        }
        return res.reverse().toString();
    }

    public static String removeBackspaceAlt(String phrase){
        StringBuilder res = new StringBuilder("");
        for(int i = 0; i < phrase.length(); i++){
            Character c = phrase.charAt(i);
            if (c == '<'){
                res.setLength(res.length()-1);
            } 
            else {
                res.append(c);
            }
        }
        return res.toString();
    }
}