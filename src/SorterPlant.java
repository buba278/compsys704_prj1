import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

public class SorterPlant extends ClockDomain{
  public SorterPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal extendPusher = new Signal("extendPusher", Signal.INPUT);
  public Signal bottleAtSorter = new Signal("bottleAtSorter", Signal.OUTPUT);
  public Signal bottleDefective = new Signal("bottleDefective", Signal.OUTPUT);
  public Signal pusherExtended = new Signal("pusherExtended", Signal.OUTPUT);
  public Signal pusherRetracted = new Signal("pusherRetracted", Signal.OUTPUT);
  public Signal pusherExtendedE = new Signal("pusherExtendedE", Signal.OUTPUT);
  public Signal pusherRetractedE = new Signal("pusherRetractedE", Signal.OUTPUT);
  private int S12300 = 1;
  private int S11951 = 1;
  private int S11907 = 1;
  private int S12049 = 1;
  private int S11975 = 1;
  private int S11963 = 1;
  private int S12090 = 1;
  private int S12054 = 1;
  private int S12098 = 1;
  
  private int[] ends = new int[6];
  private int[] tdone = new int[6];
  
  public void thread12310(int [] tdone, int [] ends){
        switch(S12098){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        if(pusherExtended.getprestatus()){//sysj\sorterPlant.sysj line: 86, column: 22
          pusherExtendedE.setPresent();//sysj\sorterPlant.sysj line: 87, column: 17
          currsigs.addElement(pusherExtendedE);
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        else {
          pusherRetractedE.setPresent();//sysj\sorterPlant.sysj line: 89, column: 17
          currsigs.addElement(pusherRetractedE);
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread12309(int [] tdone, int [] ends){
        switch(S12090){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S12054){
          case 0 : 
            if(extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 63, column: 20
              System.out.println("SorterPlant [Pusher]: Extending...");//sysj\sorterPlant.sysj line: 66, column: 13
              S12054=1;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              pusherRetracted.setPresent();//sysj\sorterPlant.sysj line: 64, column: 17
              currsigs.addElement(pusherRetracted);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            S12054=1;
            S12054=2;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 2 : 
            S12054=2;
            System.out.println("SorterPlant [Pusher]: Fully extended.");//sysj\sorterPlant.sysj line: 70, column: 13
            S12054=3;
            pusherExtended.setPresent();//sysj\sorterPlant.sysj line: 72, column: 17
            currsigs.addElement(pusherExtended);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 3 : 
            if(!extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 71, column: 20
              System.out.println("SorterPlant [Pusher]: Retracting...");//sysj\sorterPlant.sysj line: 74, column: 13
              S12054=4;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              pusherExtended.setPresent();//sysj\sorterPlant.sysj line: 72, column: 17
              currsigs.addElement(pusherExtended);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 4 : 
            S12054=4;
            S12054=5;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 5 : 
            S12054=5;
            S12054=0;
            pusherRetracted.setPresent();//sysj\sorterPlant.sysj line: 64, column: 17
            currsigs.addElement(pusherRetracted);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread12308(int [] tdone, int [] ends){
        switch(S12049){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S11975){
          case 0 : 
            if(bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 48, column: 20
              S11975=1;
              if(Math.random() < 0.15){//sysj\sorterPlant.sysj line: 49, column: 17
                S11963=0;
                System.out.println("SorterPlant [Sensor]: Defect detected on this bottle.");//sysj\sorterPlant.sysj line: 50, column: 17
                bottleDefective.setPresent();//sysj\sorterPlant.sysj line: 52, column: 21
                currsigs.addElement(bottleDefective);
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                S11963=1;
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
            }
            else {
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            switch(S11963){
              case 0 : 
                if(!bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 51, column: 24
                  S11975=0;
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  bottleDefective.setPresent();//sysj\sorterPlant.sysj line: 52, column: 21
                  currsigs.addElement(bottleDefective);
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                break;
              
              case 1 : 
                if(!bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 55, column: 24
                  S11975=0;
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                break;
              
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread12307(int [] tdone, int [] ends){
        switch(S11951){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S11907){
          case 0 : 
            if(extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 23, column: 20
              S11907=1;
              if(extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 30, column: 22
                System.out.println("SorterPlant [Feed]: Bottle being diverted by pusher, waiting for release...");//sysj\sorterPlant.sysj line: 31, column: 17
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                System.out.println("SorterPlant [Feed]: Bottle passed through, unobstructed.");//sysj\sorterPlant.sysj line: 35, column: 17
                S11907=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              bottleAtSorter.setPresent();//sysj\sorterPlant.sysj line: 24, column: 17
              currsigs.addElement(bottleAtSorter);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            if(!extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 32, column: 24
              System.out.println("SorterPlant [Feed]: Bottle diverted.");//sysj\sorterPlant.sysj line: 33, column: 17
              S11907=2;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 2 : 
            S11907=2;
            S11907=3;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 3 : 
            S11907=3;
            System.out.println("SorterPlant [Feed]: Presenting bottle at sorter...");//sysj\sorterPlant.sysj line: 22, column: 13
            S11907=0;
            bottleAtSorter.setPresent();//sysj\sorterPlant.sysj line: 24, column: 17
            currsigs.addElement(bottleAtSorter);
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread12305(int [] tdone, int [] ends){
        S12098=1;
    if(pusherExtended.getprestatus()){//sysj\sorterPlant.sysj line: 86, column: 22
      pusherExtendedE.setPresent();//sysj\sorterPlant.sysj line: 87, column: 17
      currsigs.addElement(pusherExtendedE);
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
    else {
      pusherRetractedE.setPresent();//sysj\sorterPlant.sysj line: 89, column: 17
      currsigs.addElement(pusherRetractedE);
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread12304(int [] tdone, int [] ends){
        S12090=1;
    S12054=0;
    pusherRetracted.setPresent();//sysj\sorterPlant.sysj line: 64, column: 17
    currsigs.addElement(pusherRetracted);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread12303(int [] tdone, int [] ends){
        S12049=1;
    S11975=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread12302(int [] tdone, int [] ends){
        S11951=1;
    System.out.println("SorterPlant [Feed]: Presenting bottle at sorter...");//sysj\sorterPlant.sysj line: 22, column: 13
    S11907=0;
    bottleAtSorter.setPresent();//sysj\sorterPlant.sysj line: 24, column: 17
    currsigs.addElement(bottleAtSorter);
    active[2]=1;
    ends[2]=1;
    tdone[2]=1;
  }

  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S12300){
        case 0 : 
          S12300=0;
          break RUN;
        
        case 1 : 
          S12300=2;
          S12300=2;
          System.out.println("SorterPlant: Subsystem initialized.");//sysj\sorterPlant.sysj line: 17, column: 5
          thread12302(tdone,ends);
          thread12303(tdone,ends);
          thread12304(tdone,ends);
          thread12305(tdone,ends);
          int biggest12306 = 0;
          if(ends[2]>=biggest12306){
            biggest12306=ends[2];
          }
          if(ends[3]>=biggest12306){
            biggest12306=ends[3];
          }
          if(ends[4]>=biggest12306){
            biggest12306=ends[4];
          }
          if(ends[5]>=biggest12306){
            biggest12306=ends[5];
          }
          if(biggest12306 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread12307(tdone,ends);
          thread12308(tdone,ends);
          thread12309(tdone,ends);
          thread12310(tdone,ends);
          int biggest12311 = 0;
          if(ends[2]>=biggest12311){
            biggest12311=ends[2];
          }
          if(ends[3]>=biggest12311){
            biggest12311=ends[3];
          }
          if(ends[4]>=biggest12311){
            biggest12311=ends[4];
          }
          if(ends[5]>=biggest12311){
            biggest12311=ends[5];
          }
          if(biggest12311 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest12311 == 0){
            S12300=0;
            active[1]=0;
            ends[1]=0;
            S12300=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0};
    paused = paused1;
    active = active1;
    suspended = suspended1;
    // Now instantiate all the local signals ONLY
    // --------------------------------------------------
  }
  
  public void run(){
    while(active[1] != 0){
      int index = 1;
      if(paused[index]==1 || suspended[index]==1 || active[index] == 0){
        for(int h=1;h<paused.length;++h){
          paused[h]=0;
        }
      }
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        if(!df){
          extendPusher.gethook();
          df = true;
        }
        runClockDomain();
      }
      extendPusher.setpreclear();
      bottleAtSorter.setpreclear();
      bottleDefective.setpreclear();
      pusherExtended.setpreclear();
      pusherRetracted.setpreclear();
      pusherExtendedE.setpreclear();
      pusherRetractedE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = extendPusher.getStatus() ? extendPusher.setprepresent() : extendPusher.setpreclear();
      extendPusher.setpreval(extendPusher.getValue());
      extendPusher.setClear();
      bottleAtSorter.sethook();
      bottleAtSorter.setClear();
      bottleDefective.sethook();
      bottleDefective.setClear();
      pusherExtended.sethook();
      pusherExtended.setClear();
      pusherRetracted.sethook();
      pusherRetracted.setClear();
      pusherExtendedE.sethook();
      pusherExtendedE.setClear();
      pusherRetractedE.sethook();
      pusherRetractedE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        extendPusher.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
