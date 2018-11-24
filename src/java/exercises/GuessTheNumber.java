package torea;

class GuessTheNumber
{

    public static class Guesser{
        public int max;
        public int min;
        public int mid;

        Guesser(int amin, int amax){
            min = amin;
            max = amax;
            setMid();
        }

        public String makeGuess()
        {
            return Integer.toString(mid);
        }

        public void toHigh()
        {
            max = mid;
            setMid();
        }

        public void toLow()
        {
            min = mid;
            setMid();
        }

        public void correctGuess()
        {
            min = mid;
            max = mid;
        }


        public void setMid(){
            mid = (max-min)/2+min;
        }

        public Boolean decided(){
            return max == min;
        }
    }
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);

        Guesser guesser = new Guesser(1, 1001);

        while(!guesser.decided()){
            io.println(guesser.makeGuess());
            io.flush();
            
            String response = io.getWord();
            if(response.equals("correct")){
                break;
            }
            else if (response.equals("lower")){
                guesser.toHigh();
            }
            else if (response.equals("higher")){
                guesser.toLow();
            }
        }
        
        io.close();
    }
}