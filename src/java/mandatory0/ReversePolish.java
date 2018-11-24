package no.uib.ii.inf102.f18.mandatory0;

public class ReversePolish{
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);
        List.ArrayList<String> tokens = new List.ArrayList<String>();
        
        while(io.hasMoreTokens()){
            tokens.add(io.getWord());
        }

        printInfixReverseGraph(io, tokens);
        io.flush();
        io.close();
    }


    public static boolean isOperator(String token){
        return token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/");
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

    public static Node getNodes(List.ArrayList<String> tokens, int end){
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


    public static void printGraph(Kattio io, Node node, List.ArrayList<String> tokens){

        if(!isOperator(tokens.get(node.tokenId))){
            io.print(tokens.get(node.tokenId));
        }
        else{
            io.print("(");
            printGraph(io, node.leftChild, tokens);
            io.print(tokens.get(node.tokenId));
            printGraph(io, node.rightChild, tokens);
            io.print(")");
        }
    }

    public static void printInfixReverseGraph(Kattio io,List.ArrayList<String> tokens){
        Node rootNode = getNodes( tokens, tokens.size()-1);
        printGraph(io, rootNode, tokens);
    }
}