import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class RaceSimulator {
  
  private Track track;
  private ArrayList<RaceCar> cars; 
  private RaceCar leader;
  private int lapsinRace;
  private boolean raceOver = false;
  
  
  
  public RaceSimulator() {
    lapsinRace = 20;
    track = new Track(200);
  }
  
  public RaceSimulator(Track track) {
    lapsinRace = 20;
    this.track = track;
  }
  
  public boolean raceOver() {
    return raceOver;
  }
  public boolean loadCars(String fileName) {
    cars = new ArrayList<RaceCar>();
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
      return false;
    }
    if (!cars.isEmpty()) {
      leader = cars.get(0);
    }
    return true;
  
  }
 
  public void updateCars() {
    if(raceOver) {
      getWinner().addWins();
      getRunnerUp().addSeconds();
      for (RaceCar car : cars) {
        car.addRaces();
      }
    }
    
  }
  public void saveCars(String fileName) {
    
    
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
    
 
  
  
  public RaceCar getWinner() {
    RaceCar first = cars.get(0);
    for (RaceCar car : cars) {
      if(car.getLapCount() > first.getLapCount()) {
          Track.setLeadLap((int)car.getLapCount());
          first = car;
      }
    }
    return first;
    
  }
  public RaceCar getRunnerUp() {
    
    RaceCar first = getWinner();
    RaceCar second = first == cars.get(0) ? cars.get(1) : cars.get(0);
    for (RaceCar car : cars) {
     
        if(car.getLapCount() > second.getLapCount() && car != first ) {
          second = car;
      }
    }
    return second;
 
  }
  
  public void tick() {
    for (RaceCar car : cars) {
      car.tick();
      leader =  car.getLapCount() > leader.getLapCount() ? car : leader;   
      if (car.getLapCount() > lapsinRace) {
        raceOver = true;
      }
    }
  }
  public String carStates() {
    
    String allCars = new String();
    for (RaceCar car : cars) {
      allCars += String.format(" %s%n", car.toString());
    }
    return allCars;
  }
   
public String carStats() {
    
    String allStats = new String();
    for (RaceCar car : cars) {
      allStats += String.format("%s Wins %d Seconds %d Total Races %d Percent Wins %.2f%n", 
          car.getDriverName(), car.getWins(),car.getSeconds(),car.getRaces(), car.getWinPercent() );
    }
    return allStats;
  }
  public String toString()  {
    return String.format("Here at %s %s leads on lap %d ", track.trackName() ,leader.getDriverName(), (int)leader.getLapCount());
  }
  
  

}
