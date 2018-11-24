package torea;
import java.util.ArrayList;;

public class Racetrack{
    public static void main(String[] arg){
        Kattio io = new Kattio(System.in, System.out);
        StringBuilder sb = new StringBuilder();

        int n = io.getInt();

        ArrayList<Car> adCars = new ArrayList<Car>();

        for (int i =0; i< n; i++){
            long laps = io.getLong();
            long time = io.getLong();
            adCars.add(new Car(laps, time));
        }

        io.print(sb);
        io.close();
    }

    public static String doRace(ArrayList<Car> adCar)
    {

        // long bottleneck = 0;
        // for( int i = adCar.size()-1; i>=0; i--){
        //     long tpl = adCar.get(i).timePerLap;
        //     long nl = adCar.get(i).nrLap;
        //     long temp = nl*tpl;
        //     for (Car otherCar : adCar.subList(0, i)){
        //         ;
        //     }
        // }

        return "";
    }

    public static class Car{
        long timePerLap;
        long nrLap;
        long totalTime = 0;

        Car(long tpl, long nl){
            timePerLap = tpl;
            nrLap = nl;
        }
    }
}