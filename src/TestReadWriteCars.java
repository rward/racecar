import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class TestReadWriteCars {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Track track = new Track(10);
    //load cars from file
    ArrayList<RaceCar> cars = loadCars("racers", track);
   
    //print out what they are
    for (RaceCar car : cars) {
      System.out.println(String.format("%s Wins %d Seconds %d Total Races %d Percent Wins %.2f%n", 
        car.getDriverName(), car.getWins(),car.getSeconds(),car.getRaces(), car.getWinPercent() ));
    }
    
    // add a races to one cars
    cars.get(0).addRaces();
    //check that is changed
    for (RaceCar car : cars) {
      System.out.println(String.format("%s Wins %d Seconds %d Total Races %d Percent Wins %.2f%n", 
        car.getDriverName(), car.getWins(),car.getSeconds(),car.getRaces(), car.getWinPercent() ));
    }
  
    //save the current list
    saveCars("racers", cars);
     
    //reload it from the file (overwrite the current set of cars
    cars = loadCars("racers", track);
    //check that is show the update
    for (RaceCar car : cars) {
      System.out.println(String.format("%s Wins %d Seconds %d Total Races %d Percent Wins %.2f%n", 
        car.getDriverName(), car.getWins(),car.getSeconds(),car.getRaces(), car.getWinPercent() ));
    }
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
