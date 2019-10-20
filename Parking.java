import java.util.*;
import java.lang.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parking{
    int capacity;
    int cost_per_hour;
    int all_day_profit;
    List<Vehicle> all_veh;

    Parking(int cap, int cost){
        this.capacity = cap;
        this.cost_per_hour = cost;
        all_veh = new ArrayList<Vehicle>();
    }

    public void entrance(String in_time){
        Vehicle new_veh = new Vehicle(in_time);
        all_veh.add(new_veh);
    }

    public void print_all_veh(){
        Iterator it = all_veh.iterator();

        while(it.hasNext()){
            Vehicle temp_veh = (Vehicle)it.next();
            System.out.println(temp_veh.id + " " + temp_veh.enter_time);
        }
    }

    public static void main(String args[])
    {
        String startTime = "10:15:20 am";
        String endTime = "10:30:20 am";

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        try {
            
            Date d1 = sdf.parse(startTime);
            
            Date d2 = sdf.parse(endTime);
  
            long elapsed = d2.getTime() - d1.getTime(); 
            
            int hours = (int) Math.floor(elapsed / 3600000);
            
            int minutes = (int) Math.floor((elapsed - hours * 3600000) / 60000);
            
            int seconds = (int) Math.floor((elapsed - hours * 3600000 - minutes * 60000) / 1000);
            
            System.out.format("From %s to %s%n", startTime, endTime);
                                    
            System.out.format("Time elapsed %d milliseconds%n", elapsed);
            
            System.out.format("%d hours %d minutes %d seconds%n", hours, minutes, seconds);


         } catch (ParseException e) {
              e.printStackTrace();
         }

        Parking new_park = new Parking(40, 1000);

        new_park.entrance("10:15:12 am");
        new_park.entrance("12:10:40 pm");
        new_park.print_all_veh();

    }

}