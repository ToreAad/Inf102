package no.uib.ii.inf102.f18.mandatory1;

class BSTDebugging{
    public static class Debugger{
        public int upperBound;
        public int lowerBound;
        public int val;
        public boolean possible;

        Debugger(int aval){
            val = aval;
            possible = true;
            upperBound = Integer.MAX_VALUE;
            lowerBound = Integer.MIN_VALUE;
        }

        public boolean possibleSearch(int search){
            if (!possible) return false;

            if(search <= lowerBound || search >= upperBound){
                possible = false;
                return false;
            }

            if(search > lowerBound && search < val){
                lowerBound = search;
            }

            if(search < upperBound && search > val){
                upperBound = search;
            }
            
            if (search == val){
                lowerBound = val;
                upperBound = val;
            }

            return possible;

        }
    }
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);

        int nrSearches = io.getInt();
        int searchValue = io.getInt();

        Debugger debugger = new Debugger(searchValue);

        while(debugger.possible && nrSearches > 0){
            debugger.possibleSearch(io.getInt());
            nrSearches--;
        }

        if(!debugger.possible){
            io.println("invalid");
        }
        else{
            io.println("valid");
        }
        io.close();
    }
}