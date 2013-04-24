import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


public class TestReadFile {

  @Test
  public void test() {
    Track track = new Track(10);
    //load cars from file
    ArrayList<RaceCar> cars =  MainRaceCarSimulator.loadCars("racers", track);
    //Contains
    //Fred,22,16,14,42
    //Bill,24,15,14,42
    //Sally,25,11,14,42
     
    assertEquals(cars.get(0).getDriverName(), "Fred");
    assertEquals(cars.get(1).getDriverName(), "Bill");
    assertEquals(cars.get(2).getDriverName(), "Sally");
  }

}
