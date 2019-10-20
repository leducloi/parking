import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parking{
    int capacity;
    int cost_per_hour;
    int cost_over_4h;
    int all_day_profit;
    int veh_count;
    int all_day_veh;
    List<Vehicle> all_veh;

    Parking(int cap, int cost, int over_4h){
        this.capacity = cap;
        this.cost_per_hour = cost;
        this.cost_over_4h = over_4h;
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
            System.out.println("This Vehicle did not park");
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

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name of test file: ");
        String input_file = scan.nextLine();

        try {
            BufferedReader br = new BufferedReader(new FileReader(input_file));
            String line;

            line = br.readLine();
            String[] word1 = line.split(";");
            int cost = Integer.parseInt(word1[1]);

            line = br.readLine();
            String[] word2 = line.split(";");
            int cost4h = Integer.parseInt(word2[1]);

            line = br.readLine();
            String[] word3 = line.split(";");
            int cap = Integer.parseInt(word3[1]);

            Parking new_park = new Parking(cap, cost, cost4h);

            while((line = br.readLine()) != null){
                String[] word = line.split(";");
                //System.out.println(word[0] + " " + word[1]);
                if (word[0].equals("Enter")){
                    //System.out.print(word[1]);
                    new_park.entrance(word[1]);
                }
                else if (word[0].equals("Exit")){
                    //System.out.print(word[1]);
                    new_park.exit(Integer.parseInt(word[1]), word[2]);
                }
            }

            new_park.report();
        }   
        catch(Exception event){
            event.printStackTrace();
        }
        
    }

}