import java.util.concurrent.atomic.AtomicInteger;

public class Vehicle {
    private static final AtomicInteger count = new AtomicInteger(0);
    int id;
    String enter_time;
    Vehicle(String enter_time){
        this.enter_time = enter_time;
        id = count.incrementAndGet();
    }
}