
public class RunSimulator {

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    RaceSimulator sim = new RaceSimulator();
    
    sim.loadCars("racers2");
    
    System.out.println(sim); 
    System.out.println(sim.carStats());
    while (!sim.raceOver()) {
      sim.tick();
      System.out.println("___________________________________________");
      System.out.println(sim);
      System.out.println(sim.carStates());
    }
    System.out.println(sim.getWinner());
    System.out.println(sim.getRunnerUp());
    sim.updateCars();
    System.out.println(sim.carStats());
   
    sim.saveCars("racers2");
  }
  
}
