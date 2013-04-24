

import java.util.ArrayList;
import java.util.Random;

public class RaceCarSimulator {

  
  public static void main(String[] args) {
    
    //replace by track variable
    //double totalLaps = 200;
    boolean raceOver = false;
    ArrayList<RaceCar> cars = new ArrayList<RaceCar>();
    Track track;
    if ((new Random()).nextInt(2) == 1)
    {
      track = new Daytona(20);
      
    }
    else
    {
      track = new Track(10);
      
    }
    //add track
    cars.add(new RaceCar(33, "Bob",track));
    cars.add(new RaceCar(44, "Fred",track));
    cars.add(new RaceCar(19, "Alex",track));
    cars.add(new RaceCar(17, "Danica",track));
    
    System.out.println("Welcome to " + track.trackName());
    int printlap = 0;
    
    while (!raceOver) {
      
      for (RaceCar car : cars) {
        
        car.tick();
        
        //Static variable with inheritance one per class family
        //track.setLeadLap((int)car.getLapCount());
        Track.setLeadLap((int)car.getLapCount());
        //versus
        Daytona.setLeadLap((int)car.getLapCount());
        
        //based on set lead lap not needed
//        if(car.getLapCount() > track.getLeadLap()) {
//          track.setLeadLap((int)car.getLapCount());
//        }
        //again  track or Track work
        if(car.getLapCount() >= Track.getLapsInRace()) {
          raceOver = true;
        }
        
      }
      //only print every 10th lead lap
      if (!raceOver  && Track.getLeadLap() == printlap ) {
        for (RaceCar car : cars) {
          System.out.println(car);
         }
        System.out.println("___________________________________________");
        System.out.println();
        printlap = printlap +10;
      }
      
      
    }
    RaceCar first = cars.get(0);
    for (RaceCar car : cars) {
      System.out.println(car); 
        if(car.getLapCount() > first.getLapCount()) {
          first = car;
      }
    }
    RaceCar second;
    System.out.println(first.getDriverName() + " wins!"); 
    if(cars.get(0) != first) {
      second = cars.get(0);
    }
    else{
      second = cars.get(1);
    }
      
    
    for (RaceCar car : cars) {
        if(car.getLapCount() > second.getLapCount() && car != first) {
          second = car;
      }
    }
   
    System.out.println(second.getDriverName() + " is the runner up!"); 
    
  }

}
