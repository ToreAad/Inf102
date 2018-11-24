package torea;

public class RevPolToInfNot{

    public static void main(String[] args)
    {
        Kattio io = new Kattio(System.in, System.out);
        List.ArrayList<String> tokens = new List.ArrayList<String>();
        while(io.hasMoreTokens()){
            tokens.add(io.getWord());
        }

        String output = getInfixReverseGraph(tokens);
        io.println(output);
        io.flush();
        io.close();
    }

    public static String getInfixNotation(String query)
    {
        Stack.ListStack<String> stringStack = new Stack.ListStack<String>();

        for(String token: query.split(" ")){
            if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/"))
            {
                String frst = stringStack.pop();
                String scnd = stringStack.pop();
                stringStack.push("("+scnd + token + frst+")");
            } else {
                stringStack.push(token);
            }
        }
        return stringStack.pop();
    }

    public static String getInfixNotationSB(String query)
    {
        Stack.ListStack<StringBuilder> stringStack = new Stack.ListStack<StringBuilder>();

        for(String token: query.split(" ")){
            if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/"))
            {
                StringBuilder frst = stringStack.pop();
                StringBuilder scnd = stringStack.pop();
                stringStack.push(scnd.insert(0, "(").append(token).append(frst).append(")"));
            } else {
                stringStack.push(new StringBuilder(token));
            }
        }
        return stringStack.pop().toString();
    }

    public static boolean isOperator(String token){
        return token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/");
    }

    public static boolean isOperator(Character token){
        return token.equals('+')||token.equals('-')||token.equals('*')||token.equals('/');
    }

    public static String getInfixReverseEvaluation(List.ArrayList<String> tokens)
    {

        // Idea use one stack.
        // Add to it.
        // When two values have been added do operation.
        // After doing operation use peek to check if two values are adjacent.
        // if so repeat.

        Stack.ListStack<String> combStack = new Stack.ListStack<String>();

        for( int i = tokens.size()-1; i>=0; i--){
            String token = tokens.get(i);
            if(isOperator(token))
            {
                combStack.push(token);
            } else {
                if(isOperator(combStack.peek())){
                    combStack.push(token);
                } else {
                    while(!combStack.isEmpty() && !isOperator(combStack.peek()))
                    {
                        String frst = combStack.pop();
                        String op = combStack.pop();
                        token = "("+token + op + frst+")";
                    }
                    combStack.push(token);
                }
            }
        }
        return combStack.pop();
    }

    public static String getInfixReverseEvaluationRecursive(List.ArrayList<String> tokens)
    {

        return getExpression(tokens, tokens.size()-1).expression.toString();
    }

    public static class ExpressionHolder{
        public StringBuilder expression;
        public int startIndex;
        ExpressionHolder(StringBuilder myExpression, int myStartIndex){
            expression = myExpression;
            startIndex = myStartIndex;
        }
    }

    public static ExpressionHolder getExpression(List.ArrayList<String> tokens, int end)
    {
        StringBuilder exp = new StringBuilder("(");
        String op = tokens.get(end);
        StringBuilder frst;
        int frstStart;
        StringBuilder scnd;
        int scndStart;
        if(!isOperator(tokens.get(end-1))){
            frst = new StringBuilder(tokens.get(end-1));
            frstStart = end-1;
        }
        else{
            ExpressionHolder firstArgumentEH = getExpression(tokens, end-1);
            frstStart = firstArgumentEH.startIndex;
            frst = firstArgumentEH.expression;
        }
        if(!isOperator(tokens.get(frstStart - 1))){
            scnd = new StringBuilder(tokens.get(frstStart - 1));
            scndStart = frstStart - 1;
        }
        else{
            ExpressionHolder secondArgumentEH = getExpression(tokens, frstStart - 1);
            scndStart = secondArgumentEH.startIndex;
            scnd = secondArgumentEH.expression;
        }

        return new ExpressionHolder(exp.append(scnd).append(op).append(frst).append(")"), scndStart);
    }

    public static class Node{
        public int tokenId;
        public int startIndex;
        public Node rightChild;
        public Node leftChild;

        Node(int id){
            tokenId = id;
        }
    }

    public static Node getNodes(List.ArrayList<String> tokens, int end)
    {
        Node opNode = new Node(end);
        
        if(!isOperator(tokens.get(end-1))){
            opNode.rightChild = new Node(end-1);
            opNode.rightChild.startIndex = end-1;
        }
        else{
            opNode.rightChild = getNodes(tokens, end-1);
        }

        if(!isOperator(tokens.get(opNode.rightChild.startIndex - 1))){
            opNode.leftChild = new Node(opNode.rightChild.startIndex - 1);
            opNode.leftChild.startIndex = opNode.rightChild.startIndex - 1;
        }
        else{
            opNode.leftChild = getNodes(tokens, opNode.rightChild.startIndex - 1);
        }

        opNode.startIndex = opNode.leftChild.startIndex;

        return opNode;
    }

    public static void traverseGraph(StringBuilder sb, Node node, List.ArrayList<String> tokens)
    {

        if(!isOperator(tokens.get(node.tokenId)))
        {
            sb.append(tokens.get(node.tokenId));
        }
        else
        {
            sb.append("(");
            traverseGraph(sb, node.leftChild, tokens);
            sb.append(tokens.get(node.tokenId));
            traverseGraph(sb, node.rightChild, tokens);
            sb.append(")");
        }
        // return sb;
    }

    public static void printGraph(Kattio io, Node node, List.ArrayList<String> tokens)
    {

        if(!isOperator(tokens.get(node.tokenId)))
        {
            io.print(tokens.get(node.tokenId));
        }
        else
        {
            io.print("(");
            printGraph(io, node.leftChild, tokens);
            io.print(tokens.get(node.tokenId));
            printGraph(io, node.rightChild, tokens);
            io.print(")");
        }
    }

    public static void printInfixReverseGraph(Kattio io,List.ArrayList<String> tokens)
    {
        Node rootNode = getNodes( tokens, tokens.size()-1);
        printGraph(io, rootNode, tokens);

    }

    public static String getInfixReverseGraph(List.ArrayList<String> tokens)
    {
        Node rootNode = getNodes( tokens, tokens.size()-1);
        StringBuilder sb = new StringBuilder();
        traverseGraph(sb, rootNode, tokens);
        return sb.toString();
    }
}