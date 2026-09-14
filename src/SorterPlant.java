import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.SorterGUI;//sysj\sorterPlant.sysj line: 1, column: 1
import run.LabellerSorterBridge;//sysj\sorterPlant.sysj line: 2, column: 1

public class SorterPlant extends ClockDomain{
  public SorterPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal extendPusher = new Signal("extendPusher", Signal.INPUT);
  public Signal bottleFromLabeller = new Signal("bottleFromLabeller", Signal.INPUT);
  public Signal bottleAtSorter = new Signal("bottleAtSorter", Signal.OUTPUT);
  public Signal bottleDefective = new Signal("bottleDefective", Signal.OUTPUT);
  public Signal pusherExtended = new Signal("pusherExtended", Signal.OUTPUT);
  public Signal pusherRetracted = new Signal("pusherRetracted", Signal.OUTPUT);
  public Signal bottleAtSorterE = new Signal("bottleAtSorterE", Signal.OUTPUT);
  public Signal bottleDefectiveE = new Signal("bottleDefectiveE", Signal.OUTPUT);
  public Signal pusherExtendedE = new Signal("pusherExtendedE", Signal.OUTPUT);
  public Signal pusherRetractedE = new Signal("pusherRetractedE", Signal.OUTPUT);
  private long t0_thread_2;//sysj\sorterPlant.sysj line: 37, column: 17
  private int S45241 = 1;
  private int S44754 = 1;
  private int S44616 = 1;
  private int S44852 = 1;
  private int S44778 = 1;
  private int S44766 = 1;
  private int S44893 = 1;
  private int S44857 = 1;
  private int S44927 = 1;
  private int S44901 = 1;
  private int S44895 = 1;
  private int S44909 = 1;
  private int S44917 = 1;
  private int S44925 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread45260(int [] tdone, int [] ends){
        switch(S44925){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(pusherRetracted.getprestatus()){//sysj\sorterPlant.sysj line: 105, column: 30
          pusherRetractedE.setPresent();//sysj\sorterPlant.sysj line: 105, column: 47
          currsigs.addElement(pusherRetractedE);
          active[9]=1;
          ends[9]=1;
          tdone[9]=1;
        }
        else {
          active[9]=1;
          ends[9]=1;
          tdone[9]=1;
        }
        break;
      
    }
  }

  public void thread45259(int [] tdone, int [] ends){
        switch(S44917){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(pusherExtended.getprestatus()){//sysj\sorterPlant.sysj line: 103, column: 30
          pusherExtendedE.setPresent();//sysj\sorterPlant.sysj line: 103, column: 46
          currsigs.addElement(pusherExtendedE);
          active[8]=1;
          ends[8]=1;
          tdone[8]=1;
        }
        else {
          active[8]=1;
          ends[8]=1;
          tdone[8]=1;
        }
        break;
      
    }
  }

  public void thread45258(int [] tdone, int [] ends){
        switch(S44909){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(bottleDefective.getprestatus()){//sysj\sorterPlant.sysj line: 101, column: 30
          bottleDefectiveE.setPresent();//sysj\sorterPlant.sysj line: 101, column: 47
          currsigs.addElement(bottleDefectiveE);
          active[7]=1;
          ends[7]=1;
          tdone[7]=1;
        }
        else {
          active[7]=1;
          ends[7]=1;
          tdone[7]=1;
        }
        break;
      
    }
  }

  public void thread45257(int [] tdone, int [] ends){
        switch(S44901){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S44895){
          case 0 : 
            S44895=0;
            if(bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 99, column: 30
              bottleAtSorterE.setPresent();//sysj\sorterPlant.sysj line: 99, column: 46
              currsigs.addElement(bottleAtSorterE);
              S44895=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S44895=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
            S44895=1;
            S44895=0;
            if(bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 99, column: 30
              bottleAtSorterE.setPresent();//sysj\sorterPlant.sysj line: 99, column: 46
              currsigs.addElement(bottleAtSorterE);
              S44895=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S44895=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread45256(int [] tdone, int [] ends){
        switch(S44927){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        thread45257(tdone,ends);
        thread45258(tdone,ends);
        thread45259(tdone,ends);
        thread45260(tdone,ends);
        int biggest45261 = 0;
        if(ends[6]>=biggest45261){
          biggest45261=ends[6];
        }
        if(ends[7]>=biggest45261){
          biggest45261=ends[7];
        }
        if(ends[8]>=biggest45261){
          biggest45261=ends[8];
        }
        if(ends[9]>=biggest45261){
          biggest45261=ends[9];
        }
        if(biggest45261 == 1){
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        //FINXME code
        if(biggest45261 == 0){
          S44927=0;
          active[5]=0;
          ends[5]=0;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread45255(int [] tdone, int [] ends){
        switch(S44893){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S44857){
          case 0 : 
            if(extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 80, column: 20
              System.out.println("SorterPlant [Pusher]: Extending...");//sysj\sorterPlant.sysj line: 83, column: 13
              S44857=1;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              pusherRetracted.setPresent();//sysj\sorterPlant.sysj line: 81, column: 17
              currsigs.addElement(pusherRetracted);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            S44857=1;
            S44857=2;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 2 : 
            S44857=2;
            System.out.println("SorterPlant [Pusher]: Fully extended.");//sysj\sorterPlant.sysj line: 87, column: 13
            S44857=3;
            pusherExtended.setPresent();//sysj\sorterPlant.sysj line: 89, column: 17
            currsigs.addElement(pusherExtended);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 3 : 
            if(!extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 88, column: 20
              System.out.println("SorterPlant [Pusher]: Retracting...");//sysj\sorterPlant.sysj line: 91, column: 13
              S44857=4;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              pusherExtended.setPresent();//sysj\sorterPlant.sysj line: 89, column: 17
              currsigs.addElement(pusherExtended);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 4 : 
            S44857=4;
            S44857=5;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 5 : 
            S44857=5;
            S44857=0;
            pusherRetracted.setPresent();//sysj\sorterPlant.sysj line: 81, column: 17
            currsigs.addElement(pusherRetracted);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread45254(int [] tdone, int [] ends){
        switch(S44852){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S44778){
          case 0 : 
            if(bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 65, column: 20
              S44778=1;
              if(Math.random() < 0.15){//sysj\sorterPlant.sysj line: 66, column: 17
                S44766=0;
                System.out.println("SorterPlant [Sensor]: Defect detected on this bottle.");//sysj\sorterPlant.sysj line: 67, column: 17
                bottleDefective.setPresent();//sysj\sorterPlant.sysj line: 69, column: 21
                currsigs.addElement(bottleDefective);
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                S44766=1;
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
            switch(S44766){
              case 0 : 
                if(!bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 68, column: 24
                  S44778=0;
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  bottleDefective.setPresent();//sysj\sorterPlant.sysj line: 69, column: 21
                  currsigs.addElement(bottleDefective);
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                break;
              
              case 1 : 
                if(!bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 72, column: 24
                  S44778=0;
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

  public void thread45253(int [] tdone, int [] ends){
        switch(S44754){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S44616){
          case 0 : 
            if(bottleFromLabeller.getprestatus()){//sysj\sorterPlant.sysj line: 28, column: 19
              LabellerSorterBridge.setSorterTakenAck(true);//sysj\sorterPlant.sysj line: 30, column: 13
              System.out.println("SorterPlant [Feed]: Presenting bottle at sorter...");//sysj\sorterPlant.sysj line: 31, column: 13
              S44616=1;
              t0_thread_2 = System.currentTimeMillis();//sysj\sorterPlant.sysj line: 37, column: 17
              if(System.currentTimeMillis() - t0_thread_2 < 300){//sysj\sorterPlant.sysj line: 38, column: 24
                bottleAtSorter.setPresent();//sysj\sorterPlant.sysj line: 39, column: 21
                currsigs.addElement(bottleAtSorter);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\sorterPlant.sysj line: 38, column: 17
                S44616=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            if(extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 36, column: 20
              S44616=2;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              if(System.currentTimeMillis() - t0_thread_2 < 300){//sysj\sorterPlant.sysj line: 38, column: 24
                bottleAtSorter.setPresent();//sysj\sorterPlant.sysj line: 39, column: 21
                currsigs.addElement(bottleAtSorter);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\sorterPlant.sysj line: 38, column: 17
                S44616=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 2 : 
            if(!bottleFromLabeller.getprestatus()){//sysj\sorterPlant.sysj line: 46, column: 19
              LabellerSorterBridge.setSorterTakenAck(false);//sysj\sorterPlant.sysj line: 47, column: 13
              S44616=3;
              if(extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 49, column: 22
                System.out.println("SorterPlant [Feed]: Bottle being diverted by pusher, waiting for release...");//sysj\sorterPlant.sysj line: 50, column: 17
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                System.out.println("SorterPlant [Feed]: Bottle passed through, unobstructed.");//sysj\sorterPlant.sysj line: 54, column: 17
                S44616=4;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 3 : 
            if(!extendPusher.getprestatus()){//sysj\sorterPlant.sysj line: 51, column: 24
              System.out.println("SorterPlant [Feed]: Bottle diverted.");//sysj\sorterPlant.sysj line: 52, column: 17
              S44616=4;
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
          
          case 4 : 
            S44616=4;
            S44616=5;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 5 : 
            S44616=5;
            S44616=0;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread45250(int [] tdone, int [] ends){
        S44925=1;
    if(pusherRetracted.getprestatus()){//sysj\sorterPlant.sysj line: 105, column: 30
      pusherRetractedE.setPresent();//sysj\sorterPlant.sysj line: 105, column: 47
      currsigs.addElement(pusherRetractedE);
      active[9]=1;
      ends[9]=1;
      tdone[9]=1;
    }
    else {
      active[9]=1;
      ends[9]=1;
      tdone[9]=1;
    }
  }

  public void thread45249(int [] tdone, int [] ends){
        S44917=1;
    if(pusherExtended.getprestatus()){//sysj\sorterPlant.sysj line: 103, column: 30
      pusherExtendedE.setPresent();//sysj\sorterPlant.sysj line: 103, column: 46
      currsigs.addElement(pusherExtendedE);
      active[8]=1;
      ends[8]=1;
      tdone[8]=1;
    }
    else {
      active[8]=1;
      ends[8]=1;
      tdone[8]=1;
    }
  }

  public void thread45248(int [] tdone, int [] ends){
        S44909=1;
    if(bottleDefective.getprestatus()){//sysj\sorterPlant.sysj line: 101, column: 30
      bottleDefectiveE.setPresent();//sysj\sorterPlant.sysj line: 101, column: 47
      currsigs.addElement(bottleDefectiveE);
      active[7]=1;
      ends[7]=1;
      tdone[7]=1;
    }
    else {
      active[7]=1;
      ends[7]=1;
      tdone[7]=1;
    }
  }

  public void thread45247(int [] tdone, int [] ends){
        S44901=1;
    S44895=0;
    if(bottleAtSorter.getprestatus()){//sysj\sorterPlant.sysj line: 99, column: 30
      bottleAtSorterE.setPresent();//sysj\sorterPlant.sysj line: 99, column: 46
      currsigs.addElement(bottleAtSorterE);
      S44895=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
    else {
      S44895=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

  public void thread45246(int [] tdone, int [] ends){
        S44927=1;
    thread45247(tdone,ends);
    thread45248(tdone,ends);
    thread45249(tdone,ends);
    thread45250(tdone,ends);
    int biggest45251 = 0;
    if(ends[6]>=biggest45251){
      biggest45251=ends[6];
    }
    if(ends[7]>=biggest45251){
      biggest45251=ends[7];
    }
    if(ends[8]>=biggest45251){
      biggest45251=ends[8];
    }
    if(ends[9]>=biggest45251){
      biggest45251=ends[9];
    }
    if(biggest45251 == 1){
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread45245(int [] tdone, int [] ends){
        S44893=1;
    S44857=0;
    pusherRetracted.setPresent();//sysj\sorterPlant.sysj line: 81, column: 17
    currsigs.addElement(pusherRetracted);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread45244(int [] tdone, int [] ends){
        S44852=1;
    S44778=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread45243(int [] tdone, int [] ends){
        S44754=1;
    S44616=0;
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
      switch(S45241){
        case 0 : 
          S45241=0;
          break RUN;
        
        case 1 : 
          S45241=2;
          S45241=2;
          System.out.println("SorterPlant: Subsystem initialized.");//sysj\sorterPlant.sysj line: 20, column: 5
          new Thread(new SorterGUI()).start();//sysj\sorterPlant.sysj line: 21, column: 5
          thread45243(tdone,ends);
          thread45244(tdone,ends);
          thread45245(tdone,ends);
          thread45246(tdone,ends);
          int biggest45252 = 0;
          if(ends[2]>=biggest45252){
            biggest45252=ends[2];
          }
          if(ends[3]>=biggest45252){
            biggest45252=ends[3];
          }
          if(ends[4]>=biggest45252){
            biggest45252=ends[4];
          }
          if(ends[5]>=biggest45252){
            biggest45252=ends[5];
          }
          if(biggest45252 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread45253(tdone,ends);
          thread45254(tdone,ends);
          thread45255(tdone,ends);
          thread45256(tdone,ends);
          int biggest45262 = 0;
          if(ends[2]>=biggest45262){
            biggest45262=ends[2];
          }
          if(ends[3]>=biggest45262){
            biggest45262=ends[3];
          }
          if(ends[4]>=biggest45262){
            biggest45262=ends[4];
          }
          if(ends[5]>=biggest45262){
            biggest45262=ends[5];
          }
          if(biggest45262 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest45262 == 0){
            S45241=0;
            active[1]=0;
            ends[1]=0;
            S45241=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
          bottleFromLabeller.gethook();
          df = true;
        }
        runClockDomain();
      }
      extendPusher.setpreclear();
      bottleFromLabeller.setpreclear();
      bottleAtSorter.setpreclear();
      bottleDefective.setpreclear();
      pusherExtended.setpreclear();
      pusherRetracted.setpreclear();
      bottleAtSorterE.setpreclear();
      bottleDefectiveE.setpreclear();
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
      dummyint = bottleFromLabeller.getStatus() ? bottleFromLabeller.setprepresent() : bottleFromLabeller.setpreclear();
      bottleFromLabeller.setpreval(bottleFromLabeller.getValue());
      bottleFromLabeller.setClear();
      bottleAtSorter.sethook();
      bottleAtSorter.setClear();
      bottleDefective.sethook();
      bottleDefective.setClear();
      pusherExtended.sethook();
      pusherExtended.setClear();
      pusherRetracted.sethook();
      pusherRetracted.setClear();
      bottleAtSorterE.sethook();
      bottleAtSorterE.setClear();
      bottleDefectiveE.sethook();
      bottleDefectiveE.setClear();
      pusherExtendedE.sethook();
      pusherExtendedE.setClear();
      pusherRetractedE.sethook();
      pusherRetractedE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        extendPusher.gethook();
        bottleFromLabeller.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
