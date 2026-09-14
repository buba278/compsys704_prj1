import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

public class rotaryTableController extends ClockDomain{
  public rotaryTableController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal tableAlignedWithSensor = new Signal("tableAlignedWithSensor", Signal.INPUT);
  public Signal bottleAtPos5 = new Signal("bottleAtPos5", Signal.INPUT);
  public Signal capOnBottleAtPos1 = new Signal("capOnBottleAtPos1", Signal.INPUT);
  public Signal readyToRotate = new Signal("readyToRotate", Signal.INPUT);
  public Signal mode = new Signal("mode", Signal.INPUT);
  public Signal rotateM = new Signal("rotateM", Signal.INPUT);
  public Signal rotaryTableTrigger = new Signal("rotaryTableTrigger", Signal.OUTPUT);
  public Signal tableRotationComplete = new Signal("tableRotationComplete", Signal.OUTPUT);
  private Signal autoMode_1;
  private Signal manualMode_1;
  private int currentMode_thread_2;//sysj\rotaryTableController.sysj line: 13, column: 3
  private int S10111 = 1;
  private int S9817 = 1;
  private int S9790 = 1;
  private int S10059 = 1;
  private int S9877 = 1;
  private int S9824 = 1;
  private int S10109 = 1;
  private int S10075 = 1;
  
  private int[] ends = new int[5];
  private int[] tdone = new int[5];
  
  public void thread10119(int [] tdone, int [] ends){
        switch(S10109){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S10075){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 63, column: 10
              S10075=1;
              if(rotateM.getprestatus()){//sysj\rotaryTableController.sysj line: 67, column: 14
                rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 68, column: 7
                currsigs.addElement(rotaryTableTrigger);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            else {
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            if(autoMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 65, column: 10
              S10075=0;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(rotateM.getprestatus()){//sysj\rotaryTableController.sysj line: 67, column: 14
                rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 68, column: 7
                currsigs.addElement(rotaryTableTrigger);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread10118(int [] tdone, int [] ends){
        switch(S10059){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S9877){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 33, column: 10
              S9877=1;
              S9824=0;
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
            if(manualMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 35, column: 10
              S9877=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S9824){
                case 0 : 
                  if(readyToRotate.getprestatus()){//sysj\rotaryTableController.sysj line: 36, column: 11
                    S9824=1;
                    if(bottleAtPos5.getprestatus()){//sysj\rotaryTableController.sysj line: 40, column: 13
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S9824=2;
                      if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 44, column: 13
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S9824=3;
                        rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 50, column: 7
                        currsigs.addElement(rotaryTableTrigger);
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                  else {
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 1 : 
                  if(!bottleAtPos5.getprestatus()){//sysj\rotaryTableController.sysj line: 41, column: 12
                    S9824=2;
                    if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 44, column: 13
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S9824=3;
                      rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 50, column: 7
                      currsigs.addElement(rotaryTableTrigger);
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
                
                case 2 : 
                  if(!capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 45, column: 12
                    S9824=3;
                    rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 50, column: 7
                    currsigs.addElement(rotaryTableTrigger);
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
                
                case 3 : 
                  if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTableController.sysj line: 48, column: 11
                    tableRotationComplete.setPresent();//sysj\rotaryTableController.sysj line: 55, column: 5
                    currsigs.addElement(tableRotationComplete);
                    S9824=4;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 50, column: 7
                    currsigs.addElement(rotaryTableTrigger);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 4 : 
                  S9824=4;
                  S9877=0;
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                  break;
                
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread10117(int [] tdone, int [] ends){
        switch(S9817){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S9790){
          case 0 : 
            S9790=0;
            if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 16, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 17, column: 5
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 19, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 20, column: 5
                currsigs.addElement(manualMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 23, column: 5
                currsigs.addElement(autoMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 19, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 20, column: 5
                currsigs.addElement(manualMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 23, column: 5
                currsigs.addElement(autoMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S9790=1;
            S9790=0;
            if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 16, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 17, column: 5
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 19, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 20, column: 5
                currsigs.addElement(manualMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 23, column: 5
                currsigs.addElement(autoMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 19, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 20, column: 5
                currsigs.addElement(manualMode_1);
                S9790=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 23, column: 5
                currsigs.addElement(autoMode_1);
                S9790=1;
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

  public void thread10115(int [] tdone, int [] ends){
        S10109=1;
    S10075=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread10114(int [] tdone, int [] ends){
        S10059=1;
    S9877=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread10113(int [] tdone, int [] ends){
        S9817=1;
    currentMode_thread_2 = 0;//sysj\rotaryTableController.sysj line: 13, column: 3
    S9790=0;
    if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 16, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 17, column: 5
      if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 19, column: 8
        manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 20, column: 5
        currsigs.addElement(manualMode_1);
        S9790=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 23, column: 5
        currsigs.addElement(autoMode_1);
        S9790=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 19, column: 8
        manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 20, column: 5
        currsigs.addElement(manualMode_1);
        S9790=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 23, column: 5
        currsigs.addElement(autoMode_1);
        S9790=1;
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
      switch(S10111){
        case 0 : 
          S10111=0;
          break RUN;
        
        case 1 : 
          S10111=2;
          S10111=2;
          autoMode_1.setClear();//sysj\rotaryTableController.sysj line: 9, column: 2
          manualMode_1.setClear();//sysj\rotaryTableController.sysj line: 10, column: 2
          thread10113(tdone,ends);
          thread10114(tdone,ends);
          thread10115(tdone,ends);
          int biggest10116 = 0;
          if(ends[2]>=biggest10116){
            biggest10116=ends[2];
          }
          if(ends[3]>=biggest10116){
            biggest10116=ends[3];
          }
          if(ends[4]>=biggest10116){
            biggest10116=ends[4];
          }
          if(biggest10116 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\rotaryTableController.sysj line: 9, column: 2
          manualMode_1.setClear();//sysj\rotaryTableController.sysj line: 10, column: 2
          thread10117(tdone,ends);
          thread10118(tdone,ends);
          thread10119(tdone,ends);
          int biggest10120 = 0;
          if(ends[2]>=biggest10120){
            biggest10120=ends[2];
          }
          if(ends[3]>=biggest10120){
            biggest10120=ends[3];
          }
          if(ends[4]>=biggest10120){
            biggest10120=ends[4];
          }
          if(biggest10120 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest10120 == 0){
            S10111=0;
            active[1]=0;
            ends[1]=0;
            S10111=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0};
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
          tableAlignedWithSensor.gethook();
          bottleAtPos5.gethook();
          capOnBottleAtPos1.gethook();
          readyToRotate.gethook();
          mode.gethook();
          rotateM.gethook();
          df = true;
        }
        runClockDomain();
      }
      tableAlignedWithSensor.setpreclear();
      bottleAtPos5.setpreclear();
      capOnBottleAtPos1.setpreclear();
      readyToRotate.setpreclear();
      mode.setpreclear();
      rotateM.setpreclear();
      rotaryTableTrigger.setpreclear();
      tableRotationComplete.setpreclear();
      autoMode_1.setpreclear();
      manualMode_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = tableAlignedWithSensor.getStatus() ? tableAlignedWithSensor.setprepresent() : tableAlignedWithSensor.setpreclear();
      tableAlignedWithSensor.setpreval(tableAlignedWithSensor.getValue());
      tableAlignedWithSensor.setClear();
      dummyint = bottleAtPos5.getStatus() ? bottleAtPos5.setprepresent() : bottleAtPos5.setpreclear();
      bottleAtPos5.setpreval(bottleAtPos5.getValue());
      bottleAtPos5.setClear();
      dummyint = capOnBottleAtPos1.getStatus() ? capOnBottleAtPos1.setprepresent() : capOnBottleAtPos1.setpreclear();
      capOnBottleAtPos1.setpreval(capOnBottleAtPos1.getValue());
      capOnBottleAtPos1.setClear();
      dummyint = readyToRotate.getStatus() ? readyToRotate.setprepresent() : readyToRotate.setpreclear();
      readyToRotate.setpreval(readyToRotate.getValue());
      readyToRotate.setClear();
      dummyint = mode.getStatus() ? mode.setprepresent() : mode.setpreclear();
      mode.setpreval(mode.getValue());
      mode.setClear();
      dummyint = rotateM.getStatus() ? rotateM.setprepresent() : rotateM.setpreclear();
      rotateM.setpreval(rotateM.getValue());
      rotateM.setClear();
      rotaryTableTrigger.sethook();
      rotaryTableTrigger.setClear();
      tableRotationComplete.sethook();
      tableRotationComplete.setClear();
      autoMode_1.setClear();
      manualMode_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        tableAlignedWithSensor.gethook();
        bottleAtPos5.gethook();
        capOnBottleAtPos1.gethook();
        readyToRotate.gethook();
        mode.gethook();
        rotateM.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
