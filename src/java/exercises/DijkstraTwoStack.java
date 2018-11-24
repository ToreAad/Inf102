package torea;

import java.util.Stack;
import java.util.ArrayList;

public class DijkstraTwoStack{
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        ArrayList<String> tokens = new ArrayList<String>();

        while(io.hasMoreTokens()){
            tokens.add(io.getWord());
        }

        String output = doCalc(tokens);

        io.println(output);
        io.close();
    }

    public static long add(long x, long y) {return (x+y)%1000000007;}

    public static long sqr(long x) {return (x * x)%1000000007;}

    public static long min(long x, long y, long z){
        return Math.min(x, Math.min(y, z))%1000000007;
    }

    public static String doCalc(String tokenString){
        ArrayList<String> tokens = new ArrayList<String>();
        for(String token: tokenString.trim().split("\\s+")){
            tokens.add(token);
        }
        return doCalc(tokens);
    }


    public static String doCalc(ArrayList<String> tokens){
        Stack<String> opStack = new Stack<String>();
        Stack<Long> valStack = new Stack<Long>();

        for(String token : tokens){
            if(token.equals("add")||token.equals("sqr")||token.equals("min")){
                opStack.push(token);
            }
            else if(token.equals(")")){
                String cur_op = opStack.pop();
                if (cur_op.equals("add")){
                    long cur_val = add(valStack.pop(), valStack.pop());
                    valStack.push(cur_val);
                }
                else if (cur_op.equals("min")){
                    long cur_val = min(valStack.pop(), valStack.pop(), valStack.pop());
                    valStack.push(cur_val);
                }
                else if (cur_op.equals("sqr")){
                    long cur_val = sqr(valStack.pop());
                    valStack.push(cur_val);
                }
            }
            else if(token.equals("(")||token.equals(",")){
                continue;
            }
            else{
                valStack.push(Long.parseLong(token));
            }
        }
        return Long.toString(valStack.pop());
    }
}