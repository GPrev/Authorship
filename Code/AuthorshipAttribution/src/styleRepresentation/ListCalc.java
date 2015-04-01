package styleRepresentation;

import java.util.List;

public class ListCalc {
	public static int sum (List<Integer> a){
        if (a.size() > 0) {
            int sum = 0;
 
            for (Integer i : a) {
                sum += i;
            }
            return sum;
        }
        return 0;
    }
    public static double mean (List<Integer> a){
        int sum = sum(a);
        double mean = 0;
        mean = sum / (a.size() * 1.0);
        return mean;
    }
    public static double median (List<Integer> a){
        int middle = a.size()/2;
 
        if (a.size() % 2 == 1) {
            return a.get(middle);
        } else {
           return (a.get(middle-1) + a.get(middle)) / 2.0;
        }
    }
}
