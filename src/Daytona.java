import java.util.NavigableMap;
import java.util.TreeMap;


public class Daytona extends Track {  
  
  
  /**
   * Holds the strings values of the positions on the track
   */
  NavigableMap<Integer,String> map =  new TreeMap<Integer, String>();
  
  
  public Daytona(int lapsInRace) {
    
    super(lapsInRace);
    map.put(0, "down the front toward turn one");
    map.put(1900, "slams into turn one");
    map.put(2700, "flys out of turn one");
    map.put(3500, "rips into turn two");
    map.put(4300, "screams out of turn two");
    map.put(5100, "picks up speed down the back stretch");
    map.put(8100, "dives into turn three");
    map.put(8900, "screetches out of turn three");
    map.put(9700, "explodes into turn four");
    map.put(10500, "careens out of turn four");
    map.put(11300, "flashes down the front stretch toward the finish line");
  }
  @Override
  public String trackName() {
    
    return "Daytona";
  }

  /**
   * Cool method for getting position on track. 
   * @param percentPosition the percent the car is around the current lap from 0-100
   * @return the string that indicates position the car is on the track.
   */
  @Override
  public String getPosition(double percentPosition) {
    
    int trackPosition = (int) (percentPosition/100 * 13200);
    
    return map.get(map.floorKey(trackPosition)) ;
    
    
  }
  
}
