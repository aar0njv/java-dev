package fundamentals;
/*
- why not abstract class instead of interface: java support only single inheritance
- but multiple interfaces can be implemented

- abstract classes says "is a" (shared identity) while interfaces say "can do" (shared action)
*/

import java.util.*;

public interface Operable {
  void turnOn();
}

public class Television implements Operable {
  @Override
  public void turnOn() {
    System.out.println("TV screen lights up.");
  }
}

public class AirConditioner implements Operable {
  @Override
  public void turnOn() {
    System.out.println("Air Conditioner starts.");
  }
}

public class Main {
  public static void main(String[] args) {
    Operable myTv = new Television();
    Operable myAc = new AirConditioner();
    List<Operable> roomDevices = new ArrayList<>();
    roomDevices.add(myTv);
    roomDevices.add(myAc);

    System.out.println("--- Pressing 'Master Power' on Universal Remote ---");

    for (Operable device : roomDevices) {
        device.turnOn();
    }
  }
}

  
