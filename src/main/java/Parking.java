import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parking{
    Zone1 park1;
    Zone2 park2;
    Zone3 park3;

    Parking(){
        this.park1 = new Zone1();
        this.park2 = new Zone2();
        this.park3 = new Zone3();
    }

    public void enter(String level, String time){
        if (level.equals("1")){
            park1.entrance(time);
        }
        else if (level.equals("2")){
            park2.entrance(time);
        }
        else if (level.equals("3")){
            park3.entrance(time);
        }
    }

    public void exit(String id, String level, String time){
        if (level.equals("1")){
            park1.exit(Integer.parseInt(id),time);
        }
        else if (level.equals("2")){
            park2.exit(Integer.parseInt(id),time);
        }
        else if (level.equals("3")){
            park3.exit(Integer.parseInt(id),time);
        }
    }

    public void report(){
        System.out.println("Zone 1 Report: ");
        park1.report();
        System.out.println("Zone 2 Report: ");
        park2.report();
        System.out.println("Zone 3 Report: ");
        park3.report();
    }

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name of test file: ");
        String input_file = scan.nextLine();


        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            InputStream in = classLoader.getResourceAsStream(input_file);
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inr);
            String line;
            Parking new_park = new Parking();

            while((line = br.readLine()) != null){
                String[] word = line.split(";");
                //System.out.println(word[0] + " " + word[1]);
                if (word[0].equals("Enter")){
                    //System.out.print(word[1]);
                    String[] level = word[1].split("=");
                    new_park.enter(level[1], word[2]);
                }
                else if (word[0].equals("Exit")){
                    //System.out.print(word[1]);
                    String[] id = word[1].split("=");
                    String[] level = word[2].split("=");
                    new_park.exit(id[1], level[1], word[3]);
                }
            }
            new_park.report();
        }   
        catch(Exception event){
            event.printStackTrace();
        }
        
    }

}