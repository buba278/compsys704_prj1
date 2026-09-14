import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

public class conveyorController extends ClockDomain{
  public conveyorController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtPos1 = new Signal("bottleAtPos1", Signal.INPUT);
  public Signal bottleLeftPos5 = new Signal("bottleLeftPos5", Signal.INPUT);
  public Signal mode = new Signal("mode", Signal.INPUT);
  public Signal conveyorM = new Signal("conveyorM", Signal.INPUT);
  public Signal motConveyorOnOff = new Signal("motConveyorOnOff", Signal.OUTPUT);
  private Signal autoMode_1;
  private Signal manualMode_1;
  private int currentMode_thread_2;//sysj\conveyorController.sysj line: 12, column: 3
  private int S3263 = 1;
  private int S2477 = 1;
  private int S2450 = 1;
  private int S3211 = 1;
  private int S2721 = 1;
  private int S2508 = 1;
  private int S2484 = 1;
  private int S2492 = 1;
  private int S3261 = 1;
  private int S3227 = 1;
  
  private int[] ends = new int[7];
  private int[] tdone = new int[7];
  
  public void thread3277(int [] tdone, int [] ends){
        switch(S3261){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S3227){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\conveyorController.sysj line: 61, column: 10
              S3227=1;
              if(conveyorM.getprestatus()){//sysj\conveyorController.sysj line: 64, column: 14
                motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 65, column: 7
                currsigs.addElement(motConveyorOnOff);
                active[6]=1;
                ends[6]=1;
                tdone[6]=1;
              }
              else {
                active[6]=1;
                ends[6]=1;
                tdone[6]=1;
              }
            }
            else {
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
            if(autoMode_1.getprestatus()){//sysj\conveyorController.sysj line: 62, column: 10
              S3227=0;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              if(conveyorM.getprestatus()){//sysj\conveyorController.sysj line: 64, column: 14
                motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 65, column: 7
                currsigs.addElement(motConveyorOnOff);
                active[6]=1;
                ends[6]=1;
                tdone[6]=1;
              }
              else {
                active[6]=1;
                ends[6]=1;
                tdone[6]=1;
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread3275(int [] tdone, int [] ends){
        switch(S2492){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        if(bottleAtPos1.getprestatus()){//sysj\conveyorController.sysj line: 47, column: 17
          System.out.printf("Conveyor: bottle at Pos 1 - handing to Rotary Table%n");//sysj\conveyorController.sysj line: 48, column: 10
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        else {
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread3274(int [] tdone, int [] ends){
        switch(S2484){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 42, column: 8
        currsigs.addElement(motConveyorOnOff);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
        break;
      
    }
  }

  public void thread3272(int [] tdone, int [] ends){
        S2492=1;
    if(bottleAtPos1.getprestatus()){//sysj\conveyorController.sysj line: 47, column: 17
      System.out.printf("Conveyor: bottle at Pos 1 - handing to Rotary Table%n");//sysj\conveyorController.sysj line: 48, column: 10
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
    else {
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread3271(int [] tdone, int [] ends){
        S2484=1;
    motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 42, column: 8
    currsigs.addElement(motConveyorOnOff);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread3270(int [] tdone, int [] ends){
        switch(S3211){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S2721){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\conveyorController.sysj line: 33, column: 10
              S2721=1;
              S2508=0;
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
          
          case 1 : 
            if(manualMode_1.getprestatus()){//sysj\conveyorController.sysj line: 34, column: 10
              S2721=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S2508){
                case 0 : 
                  if(!bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 37, column: 12
                    S2508=1;
                    thread3271(tdone,ends);
                    thread3272(tdone,ends);
                    int biggest3273 = 0;
                    if(ends[4]>=biggest3273){
                      biggest3273=ends[4];
                    }
                    if(ends[5]>=biggest3273){
                      biggest3273=ends[5];
                    }
                    if(biggest3273 == 1){
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
                  if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 40, column: 12
                    S2508=0;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    thread3274(tdone,ends);
                    thread3275(tdone,ends);
                    int biggest3276 = 0;
                    if(ends[4]>=biggest3276){
                      biggest3276=ends[4];
                    }
                    if(ends[5]>=biggest3276){
                      biggest3276=ends[5];
                    }
                    if(biggest3276 == 1){
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    //FINXME code
                    if(biggest3276 == 0){
                      S2508=0;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  break;
                
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread3269(int [] tdone, int [] ends){
        switch(S2477){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S2450){
          case 0 : 
            S2450=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 14, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 15, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S2450=1;
            S2450=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 14, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 15, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread3267(int [] tdone, int [] ends){
        S3261=1;
    S3227=0;
    active[6]=1;
    ends[6]=1;
    tdone[6]=1;
  }

  public void thread3266(int [] tdone, int [] ends){
        S3211=1;
    S2721=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread3265(int [] tdone, int [] ends){
        S2477=1;
    currentMode_thread_2 = 0;//sysj\conveyorController.sysj line: 12, column: 3
    S2450=0;
    if(mode.getprestatus()){//sysj\conveyorController.sysj line: 14, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 15, column: 5
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
        currsigs.addElement(manualMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
        currsigs.addElement(autoMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
        currsigs.addElement(manualMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
        currsigs.addElement(autoMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
  }

  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S3263){
        case 0 : 
          S3263=0;
          break RUN;
        
        case 1 : 
          S3263=2;
          S3263=2;
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          thread3265(tdone,ends);
          thread3266(tdone,ends);
          thread3267(tdone,ends);
          int biggest3268 = 0;
          if(ends[2]>=biggest3268){
            biggest3268=ends[2];
          }
          if(ends[3]>=biggest3268){
            biggest3268=ends[3];
          }
          if(ends[6]>=biggest3268){
            biggest3268=ends[6];
          }
          if(biggest3268 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          thread3269(tdone,ends);
          thread3270(tdone,ends);
          thread3277(tdone,ends);
          int biggest3278 = 0;
          if(ends[2]>=biggest3278){
            biggest3278=ends[2];
          }
          if(ends[3]>=biggest3278){
            biggest3278=ends[3];
          }
          if(ends[6]>=biggest3278){
            biggest3278=ends[6];
          }
          if(biggest3278 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest3278 == 0){
            S3263=0;
            active[1]=0;
            ends[1]=0;
            S3263=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0};
    paused = paused1;
    active = active1;
    suspended = suspended1;
    // Now instantiate all the local signals ONLY
    autoMode_1 = new Signal();
    manualMode_1 = new Signal();
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
          bottleAtPos1.gethook();
          bottleLeftPos5.gethook();
          mode.gethook();
          conveyorM.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtPos1.setpreclear();
      bottleLeftPos5.setpreclear();
      mode.setpreclear();
      conveyorM.setpreclear();
      motConveyorOnOff.setpreclear();
      autoMode_1.setpreclear();
      manualMode_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtPos1.getStatus() ? bottleAtPos1.setprepresent() : bottleAtPos1.setpreclear();
      bottleAtPos1.setpreval(bottleAtPos1.getValue());
      bottleAtPos1.setClear();
      dummyint = bottleLeftPos5.getStatus() ? bottleLeftPos5.setprepresent() : bottleLeftPos5.setpreclear();
      bottleLeftPos5.setpreval(bottleLeftPos5.getValue());
      bottleLeftPos5.setClear();
      dummyint = mode.getStatus() ? mode.setprepresent() : mode.setpreclear();
      mode.setpreval(mode.getValue());
      mode.setClear();
      dummyint = conveyorM.getStatus() ? conveyorM.setprepresent() : conveyorM.setpreclear();
      conveyorM.setpreval(conveyorM.getValue());
      conveyorM.setClear();
      motConveyorOnOff.sethook();
      motConveyorOnOff.setClear();
      autoMode_1.setClear();
      manualMode_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtPos1.gethook();
        bottleLeftPos5.gethook();
        mode.gethook();
        conveyorM.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
