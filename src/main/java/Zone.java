import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Zone{
    int capacity;
    int cost_per_hour;
    int cost_over_4h;
    int all_day_profit;
    int veh_count;
    int all_day_veh;
    List<Vehicle> all_veh;

    Zone(){
        this.capacity = 5;
        this.cost_per_hour = 1000;
        this.cost_over_4h = 5000;
        this.veh_count = 0;
        this.all_day_veh = 0;
        this.all_veh = new ArrayList<Vehicle>();
    }

    public void entrance(String in_time){
        if(veh_count < capacity){
            Vehicle new_veh = new Vehicle(in_time);
            all_veh.add(new_veh);
            veh_count++;
            all_day_veh++;
        }
        else{
            System.out.println("Out of Space, Please go back later");
        }

    }

    public int findID(int out_id){
        int index = -1;
        Vehicle temp_veh;
        for (int i= 0; i < all_veh.size(); i++){
            temp_veh = all_veh.get(i);
            if (temp_veh.id == out_id){
                index = i;
                break;
            }
        }
        return index;
    }

    public void exit(int out_id, String out_time){
        int out_veh_id = findID(out_id);
        SimpleDateFormat date_format = new SimpleDateFormat("hh:mm:ss a");
        if (out_veh_id != -1){
            int fee = 0;
            Vehicle temp_veh = all_veh.get(out_veh_id);
            try{
                Date start_time = date_format.parse(temp_veh.enter_time);
                Date end_time = date_format.parse(out_time);

                long elapsed = end_time.getTime() - start_time.getTime();

                int hours = (int) Math.floor(elapsed / 3600000);

                int minutes = (int) Math.floor((elapsed - hours * 3600000) / 60000);

                int seconds = (int) Math.floor((elapsed - hours * 3600000 - minutes * 60000) / 1000);

                //System.out.format("%d hours %d minutes %d seconds%n", hours, minutes, seconds);

                if (hours > 0){
                    if (hours <= 4 && minutes <= 59){
                        if (minutes < 15){
                            fee = hours*cost_per_hour;
                        }
                        else{
                            fee = (hours+1)*cost_per_hour;
                        }
                    }
                    else if (hours > 4){
                        fee = cost_over_4h;
                    }
                }

                all_day_profit += fee;
                veh_count--;
                all_veh.remove(out_veh_id);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("This Vehicle " + out_id + " did not park");
        }

    }

    public void print_all_veh(){
        Iterator it = all_veh.iterator();

        while(it.hasNext()){
            Vehicle temp_veh = (Vehicle)it.next();
            System.out.println(temp_veh.id + " " + temp_veh.enter_time);
        }
    }


    public void report(){
        DecimalFormat df = new DecimalFormat("#.00");
        double profit = all_day_profit;
        profit = profit/100.00;

        System.out.println("Number of Vehicle: " + all_day_veh + ".\nProfit: " + df.format(profit) + " dollars." );
    }

}
