import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Zone3 extends Zone {
    Zone3(){
        this.capacity = 5;
        this.cost_per_hour = 1000;
        this.cost_over_4h = 5000;
        this.veh_count = 0;
        this.all_day_veh = 0;
        this.all_veh = new ArrayList<Vehicle>();
    }
}

