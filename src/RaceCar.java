
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;



public class RaceCar {
  /**
   * A random number generator for the car.
   */
   private Random randomGen = new Random(); 
  /**
   * Holds the strings values of the positions on the track
   */
  NavigableMap<Integer,String> map =  new TreeMap<Integer, String>();
  /**
   *  Indicates how worn are the tires.
   */
  private int tireCondition = 99;
  /**
   * Indicates the current gas left.
   */
  private int gasLevel = 99;
  /**
   * is this car in a pitStop.
   */
  private boolean pitStop = false;
  /**
   * The current distance the car has gone in laps
   */
  private double lapCount = 0;
  
  /**
   * the name of the person driving the car.
   */
  private String driverName = "";
  /**
   * The car's number
   */
  private int number = 0;
  
  private int wins = 0;
  /**
   * @return the wins
   */
  public int getWins() {
    return wins;
  }
  /**
   * @return the win win percent
   */
  public double getWinPercent() {
    return  races != 0 ? 100.0 * wins/races : 0;
  }
  
  /**
   * @param wins the wins to set
   */
  public void addWins() {
    this.wins++;
  }
  /**
   * @return the seconds
   */
  public int getSeconds() {
    return seconds;
  }
  /**
   * @param seconds the seconds to set
   */
  public void addSeconds() {
    this.seconds++;
  }
  /**
   * @return the races
   */
  public int getRaces() {
    return races;
  }
  /**
   * @param races the races to set
   */
  public void addRaces() {
    this.races++;
  }




  private int seconds = 0;
  private int races = 0;
  
  /**
   * Creates a new car with the name and number provided
   * @param number
   * @param driverName
   */
  private Track track;
  public RaceCar(int number, String driverName,Track track) {
    if (number >= 0 && number < 100) {
      this.number = number ;
      
    }
    this.track = track;
    this.driverName = driverName;
   
    
  }
  
  /**
   * Creates a new car with the name and number provided
   * @param number
   * @param driverName
   */
   public RaceCar(int number, String driverName,Track track,int wins, int seconds, int races) {
    if (number >= 0 && number < 100) {
      this.number = number ;
      
    }
    this.track = track;
    this.driverName = driverName;
    this.seconds = seconds;
    this.wins = wins;
    this.races = races;
   
    
  }
  
  /**
   * The amount of ware left on the tire.
   * @return the tireCondition
   */
  public int getTireCondition() {
    return tireCondition;
  }
 
  /**
   * The amount of gas in the car.
   * @return the gasLevel
   */
  public int getGasLevel() {
    return gasLevel;
  }
 
  /**
   * Indicates if the car is in the pitstop.
   * @return the pitStop
   */
  public boolean inPitStop() {
    return pitStop;
  }
 
  /**
   * The lap count for the race car. 
   * @return the lapCount
   */
  public double getLapCount() {
    return lapCount;
  }
  
  /**
   * The name of the driver of the race car.
   * @return the driverName
   */
  public String getDriverName() {
    return driverName;
  }
  
  /**
   * The number of the driver.
   * @return the number
   */
  public int getNumber() {
    return number;
  }
 
  /**
   * Restores the car to full tire and gas and turns off the pit stop indicator.
   * 
   */
  public void pitStop() {
    gasLevel = 99;
    tireCondition = 99;
    pitStop = false;
    
    
  }
  /**
   * 
   */
   public void tick() {
     
     if(!pitStop) {
       lapCount += .15 + randomGen.nextInt(11)/100.0;
       decreaseGas();
       decreaseTireCondition();
       if(randomGen.nextInt(10) == 1) {
         decreaseGas();
       }
       if(randomGen.nextInt(10) == 1) {
         decreaseTireCondition();
       }
       if (gasLevel < 10 || tireCondition < 10) {
         pitStop = true;
       }
       
     }
     else {
       pitStop(); 
     }
   }
  /**
   * Decreases the gas by one if it is positive.
   */
  public void decreaseGas() {
    if (gasLevel > 0) {
      gasLevel--; 
   }
    
  }
  /**
   * Decreases the tire condition by one if it is positive.
   */
  public void decreaseTireCondition() {
    if (gasLevel > 0) {
      tireCondition--; 
   }
    
  }
  
  

  
  /**
   * Returns a string the indicates the state of the car.
   * @return String for this object
   */
  public String toString() {
    if (pitStop) {
      return  String.format(" %s heads into the pit stop on lap %d ",driverName , (int)Math.floor(lapCount));
    }
    else {
    return String.format(" %s %s on lap %d  "
        ,driverName , track.getPosition(lapCount - (int) lapCount), (int)Math.floor(lapCount));
    }
  }
  
  
      

}
