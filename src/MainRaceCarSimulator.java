

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainRaceCarSimulator {

  
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
    cars = loadCars("racers", track);
    
    
    System.out.println("Welcome to " + track.trackName());
    int printlap = 0;
    
    for (RaceCar car : cars) {
      System.out.println(String.format("%s Wins %d Seconds %d Total Races %d Percent Wins %.2f%n", 
          car.getDriverName(), car.getWins(),car.getSeconds(),car.getRaces(), car.getWinPercent() ));
    }
    
 
    while (!raceOver) {
      
      for (RaceCar car : cars) {
        
        car.tick();
        Track.setLeadLap((int)car.getLapCount());
        
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
    first.addWins();
    second.addSeconds();
    for (RaceCar car : cars) {
      car.addRaces();
    }
    for (RaceCar car : cars) {
      System.out.println(String.format("%s Wins %d Seconds %d Total Races %d Percent Wins %.2f%n", 
          car.getDriverName(), car.getWins(),car.getSeconds(),car.getRaces(), car.getWinPercent() ));
    }
    saveCars("racers", cars);
  }

public static void saveCars(String fileName,ArrayList<RaceCar> cars) {
    
    
    File loadFile = new File(fileName);
    PrintWriter printFile;
    try {
      printFile = new PrintWriter(loadFile);
      for (RaceCar car : cars) {
        printFile.print(car.getDriverName() + ",");
        printFile.print(car.getNumber() + ",");
        printFile.print(car.getWins() + ",");
        printFile.print(car.getSeconds() + ",");
        printFile.println(car.getRaces());
      }
      printFile.close();
    }
    catch (FileNotFoundException e) {
     
      e.printStackTrace();
    }
     
  }

  public static  ArrayList<RaceCar> loadCars(String fileName , Track track) {
    ArrayList<RaceCar> cars = new ArrayList<RaceCar>();
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(fileName));
      String line;
      while ((line = br.readLine()) != null) {
         // spilt the line into arguments for the racecar constructor.
        ArrayList<String> arguments = 
            new  ArrayList<String>(Arrays.asList(line.split(",")));
        cars.add(
            new RaceCar(Integer.parseInt(arguments.get(1).trim()), arguments.get(0).trim(),track, 
            Integer.parseInt(arguments.get(2).trim()),Integer.parseInt(arguments.get(3).trim()),
            Integer.parseInt(arguments.get(4).trim()) ));
      }
      br.close();
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
   
    return cars;
  
  }
}
