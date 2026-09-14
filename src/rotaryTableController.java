import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.RotaryConveyorBridge;//sysj\rotaryTableController.sysj line: 1, column: 1

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
  public Signal tableBusy = new Signal("tableBusy", Signal.INPUT);
  public Signal mode = new Signal("mode", Signal.INPUT);
  public Signal rotateM = new Signal("rotateM", Signal.INPUT);
  public Signal rotaryTableTrigger = new Signal("rotaryTableTrigger", Signal.OUTPUT);
  public Signal tableRotationComplete = new Signal("tableRotationComplete", Signal.OUTPUT);
  private Signal autoMode_1;
  private Signal manualMode_1;
  private int currentMode_thread_2;//sysj\rotaryTableController.sysj line: 31, column: 3
  private int S19402 = 1;
  private int S19060 = 1;
  private int S19033 = 1;
  private int S19350 = 1;
  private int S19132 = 1;
  private int S19067 = 1;
  private int S19400 = 1;
  private int S19366 = 1;
  
  private int[] ends = new int[5];
  private int[] tdone = new int[5];
  
  public void thread19410(int [] tdone, int [] ends){
        switch(S19400){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S19366){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 106, column: 10
              S19366=1;
              if(rotateM.getprestatus()){//sysj\rotaryTableController.sysj line: 110, column: 14
                rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 111, column: 7
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
            if(autoMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 108, column: 10
              S19366=0;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(rotateM.getprestatus()){//sysj\rotaryTableController.sysj line: 110, column: 14
                rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 111, column: 7
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

  public void thread19409(int [] tdone, int [] ends){
        switch(S19350){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S19132){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 51, column: 10
              S19132=1;
              S19067=0;
              if(tableBusy.getprestatus()){//sysj\rotaryTableController.sysj line: 62, column: 13
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                S19067=1;
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
            if(manualMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 53, column: 10
              S19132=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S19067){
                case 0 : 
                  if(!tableBusy.getprestatus()){//sysj\rotaryTableController.sysj line: 63, column: 12
                    S19067=1;
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
                  if(readyToRotate.getprestatus()){//sysj\rotaryTableController.sysj line: 66, column: 11
                    S19067=2;
                    if(bottleAtPos5.getprestatus()){//sysj\rotaryTableController.sysj line: 70, column: 13
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S19067=3;
                      if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 74, column: 13
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        RotaryConveyorBridge.setPos1AckToController(true);//sysj\rotaryTableController.sysj line: 79, column: 5
                        RotaryConveyorBridge.setPos1AckToPlant(true);//sysj\rotaryTableController.sysj line: 80, column: 5
                        S19067=4;
                        rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 84, column: 7
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
                
                case 2 : 
                  if(!bottleAtPos5.getprestatus()){//sysj\rotaryTableController.sysj line: 71, column: 12
                    S19067=3;
                    if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 74, column: 13
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      RotaryConveyorBridge.setPos1AckToController(true);//sysj\rotaryTableController.sysj line: 79, column: 5
                      RotaryConveyorBridge.setPos1AckToPlant(true);//sysj\rotaryTableController.sysj line: 80, column: 5
                      S19067=4;
                      rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 84, column: 7
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
                
                case 3 : 
                  if(!capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 75, column: 12
                    RotaryConveyorBridge.setPos1AckToController(true);//sysj\rotaryTableController.sysj line: 79, column: 5
                    RotaryConveyorBridge.setPos1AckToPlant(true);//sysj\rotaryTableController.sysj line: 80, column: 5
                    S19067=4;
                    rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 84, column: 7
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
                
                case 4 : 
                  if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTableController.sysj line: 82, column: 11
                    tableRotationComplete.setPresent();//sysj\rotaryTableController.sysj line: 89, column: 5
                    currsigs.addElement(tableRotationComplete);
                    S19067=5;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 84, column: 7
                    currsigs.addElement(rotaryTableTrigger);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 5 : 
                  if(!readyToRotate.getprestatus()){//sysj\rotaryTableController.sysj line: 95, column: 11
                    RotaryConveyorBridge.setPos1AckToController(false);//sysj\rotaryTableController.sysj line: 96, column: 5
                    RotaryConveyorBridge.setPos1AckToPlant(false);//sysj\rotaryTableController.sysj line: 97, column: 5
                    S19067=6;
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
                
                case 6 : 
                  S19067=6;
                  S19132=0;
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

  public void thread19408(int [] tdone, int [] ends){
        switch(S19060){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S19033){
          case 0 : 
            S19033=0;
            if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 34, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 35, column: 5
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 37, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 38, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 41, column: 5
                currsigs.addElement(autoMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 37, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 38, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 41, column: 5
                currsigs.addElement(autoMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S19033=1;
            S19033=0;
            if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 34, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 35, column: 5
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 37, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 38, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 41, column: 5
                currsigs.addElement(autoMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 37, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 38, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 41, column: 5
                currsigs.addElement(autoMode_1);
                S19033=1;
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

  public void thread19406(int [] tdone, int [] ends){
        S19400=1;
    S19366=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread19405(int [] tdone, int [] ends){
        S19350=1;
    S19132=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread19404(int [] tdone, int [] ends){
        S19060=1;
    currentMode_thread_2 = 0;//sysj\rotaryTableController.sysj line: 31, column: 3
    S19033=0;
    if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 34, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 35, column: 5
      if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 37, column: 8
        manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 38, column: 5
        currsigs.addElement(manualMode_1);
        S19033=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 41, column: 5
        currsigs.addElement(autoMode_1);
        S19033=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 37, column: 8
        manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 38, column: 5
        currsigs.addElement(manualMode_1);
        S19033=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 41, column: 5
        currsigs.addElement(autoMode_1);
        S19033=1;
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
      switch(S19402){
        case 0 : 
          S19402=0;
          break RUN;
        
        case 1 : 
          S19402=2;
          S19402=2;
          autoMode_1.setClear();//sysj\rotaryTableController.sysj line: 12, column: 2
          manualMode_1.setClear();//sysj\rotaryTableController.sysj line: 13, column: 2
          thread19404(tdone,ends);
          thread19405(tdone,ends);
          thread19406(tdone,ends);
          int biggest19407 = 0;
          if(ends[2]>=biggest19407){
            biggest19407=ends[2];
          }
          if(ends[3]>=biggest19407){
            biggest19407=ends[3];
          }
          if(ends[4]>=biggest19407){
            biggest19407=ends[4];
          }
          if(biggest19407 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\rotaryTableController.sysj line: 12, column: 2
          manualMode_1.setClear();//sysj\rotaryTableController.sysj line: 13, column: 2
          thread19408(tdone,ends);
          thread19409(tdone,ends);
          thread19410(tdone,ends);
          int biggest19411 = 0;
          if(ends[2]>=biggest19411){
            biggest19411=ends[2];
          }
          if(ends[3]>=biggest19411){
            biggest19411=ends[3];
          }
          if(ends[4]>=biggest19411){
            biggest19411=ends[4];
          }
          if(biggest19411 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest19411 == 0){
            S19402=0;
            active[1]=0;
            ends[1]=0;
            S19402=0;
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
          tableBusy.gethook();
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
      tableBusy.setpreclear();
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
      dummyint = tableBusy.getStatus() ? tableBusy.setprepresent() : tableBusy.setpreclear();
      tableBusy.setpreval(tableBusy.getValue());
      tableBusy.setClear();
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
        tableBusy.gethook();
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
