public class Track {

  private static int leadLap = 0;
  private static int lapsInRace;
  
  public Track(int lapsinRace) {
    Track.lapsInRace = lapsinRace;
  }
  
  
  public String trackName() {
  
    return "The Track";
  }
  public static int getLapsInRace()
  {
    return lapsInRace;

  }

  public static int getLeadLap()
  {
    return leadLap;

  }
  public static void setLeadLap(int leadLap )
  {
    if(leadLap > Track.leadLap) 
    {
      Track.leadLap = leadLap;
    }
  }
  public String getPosition(double position) {
    if(position < .125) {
      return " towards turn one ";
    }
    else if(position < .250) {
      return " in turn one ";
    }
    else if(position < .375) {
      return " in turn two ";
    }
    else if(position < .625) {
      return " down the backstretch ";
    }
    else if(position < .750) {
      return " in turn three ";
    }
    else if(position < .825) {
      return " in turn four ";
    }
    else {
      return " toward the finish line ";
    }


  }
  public boolean pitStopArea(double position) {
    
    if(position > .25 && position < .5 ) {
      return true;
    }
    else {
      return false;
    }
  }

}

