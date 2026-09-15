import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import digitaltwin.PlantTwin;//sysj\rotaryTableController.sysj line: 1, column: 1
import digitaltwin.TwinClient;//sysj\rotaryTableController.sysj line: 2, column: 1
import run.RotaryConveyorBridge;//sysj\rotaryTableController.sysj line: 4, column: 1

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
  private int currentMode_thread_2;//sysj\rotaryTableController.sysj line: 35, column: 3
  private TwinClient twin_thread_3;//sysj\rotaryTableController.sysj line: 52, column: 2
  private TwinClient twin_thread_4;//sysj\rotaryTableController.sysj line: 113, column: 2
  private int S19774 = 1;
  private int S19060 = 1;
  private int S19033 = 1;
  private int S19638 = 1;
  private int S19132 = 1;
  private int S19067 = 1;
  private int S19772 = 1;
  private int S19660 = 1;
  
  private int[] ends = new int[5];
  private int[] tdone = new int[5];
  
  public void thread19782(int [] tdone, int [] ends){
        switch(S19772){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S19660){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 115, column: 10
              S19660=1;
              if(rotateM.getprestatus()){//sysj\rotaryTableController.sysj line: 119, column: 14
                rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 120, column: 7
                currsigs.addElement(rotaryTableTrigger);
                twin_thread_4.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 121, column: 7
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
            if(autoMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 117, column: 10
              S19660=0;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(rotateM.getprestatus()){//sysj\rotaryTableController.sysj line: 119, column: 14
                rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 120, column: 7
                currsigs.addElement(rotaryTableTrigger);
                twin_thread_4.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 121, column: 7
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

  public void thread19781(int [] tdone, int [] ends){
        switch(S19638){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S19132){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 56, column: 10
              S19132=1;
              S19067=0;
              if(tableBusy.getprestatus()){//sysj\rotaryTableController.sysj line: 67, column: 13
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
            if(manualMode_1.getprestatus()){//sysj\rotaryTableController.sysj line: 58, column: 10
              S19132=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S19067){
                case 0 : 
                  if(!tableBusy.getprestatus()){//sysj\rotaryTableController.sysj line: 68, column: 12
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
                  if(readyToRotate.getprestatus()){//sysj\rotaryTableController.sysj line: 71, column: 11
                    S19067=2;
                    if(bottleAtPos5.getprestatus()){//sysj\rotaryTableController.sysj line: 75, column: 13
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S19067=3;
                      if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 79, column: 13
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        RotaryConveyorBridge.setPos1AckToController(true);//sysj\rotaryTableController.sysj line: 84, column: 5
                        RotaryConveyorBridge.setPos1AckToPlant(true);//sysj\rotaryTableController.sysj line: 85, column: 5
                        S19067=4;
                        rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 89, column: 7
                        currsigs.addElement(rotaryTableTrigger);
                        twin_thread_3.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 90, column: 7
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
                  if(!bottleAtPos5.getprestatus()){//sysj\rotaryTableController.sysj line: 76, column: 12
                    S19067=3;
                    if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 79, column: 13
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      RotaryConveyorBridge.setPos1AckToController(true);//sysj\rotaryTableController.sysj line: 84, column: 5
                      RotaryConveyorBridge.setPos1AckToPlant(true);//sysj\rotaryTableController.sysj line: 85, column: 5
                      S19067=4;
                      rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 89, column: 7
                      currsigs.addElement(rotaryTableTrigger);
                      twin_thread_3.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 90, column: 7
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
                  if(!capOnBottleAtPos1.getprestatus()){//sysj\rotaryTableController.sysj line: 80, column: 12
                    RotaryConveyorBridge.setPos1AckToController(true);//sysj\rotaryTableController.sysj line: 84, column: 5
                    RotaryConveyorBridge.setPos1AckToPlant(true);//sysj\rotaryTableController.sysj line: 85, column: 5
                    S19067=4;
                    rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 89, column: 7
                    currsigs.addElement(rotaryTableTrigger);
                    twin_thread_3.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 90, column: 7
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
                  if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTableController.sysj line: 87, column: 11
                    tableRotationComplete.setPresent();//sysj\rotaryTableController.sysj line: 96, column: 5
                    currsigs.addElement(tableRotationComplete);
                    twin_thread_3.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 97, column: 5
                    S19067=5;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    rotaryTableTrigger.setPresent();//sysj\rotaryTableController.sysj line: 89, column: 7
                    currsigs.addElement(rotaryTableTrigger);
                    twin_thread_3.reportRotaryTurn();//sysj\rotaryTableController.sysj line: 90, column: 7
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 5 : 
                  if(!readyToRotate.getprestatus()){//sysj\rotaryTableController.sysj line: 103, column: 11
                    RotaryConveyorBridge.setPos1AckToController(false);//sysj\rotaryTableController.sysj line: 104, column: 5
                    RotaryConveyorBridge.setPos1AckToPlant(false);//sysj\rotaryTableController.sysj line: 105, column: 5
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

  public void thread19780(int [] tdone, int [] ends){
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
            if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 38, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 39, column: 5
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 41, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 42, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 45, column: 5
                currsigs.addElement(autoMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 41, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 42, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 45, column: 5
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
            if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 38, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 39, column: 5
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 41, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 42, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 45, column: 5
                currsigs.addElement(autoMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 41, column: 8
                manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 42, column: 5
                currsigs.addElement(manualMode_1);
                S19033=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 45, column: 5
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

  public void thread19778(int [] tdone, int [] ends){
        S19772=1;
    twin_thread_4 = new TwinClient("rotary", "127.0.0.1", 9090);//sysj\rotaryTableController.sysj line: 113, column: 2
    S19660=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread19777(int [] tdone, int [] ends){
        S19638=1;
    twin_thread_3 = new TwinClient("rotary", "127.0.0.1", 9090);//sysj\rotaryTableController.sysj line: 52, column: 2
    S19132=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread19776(int [] tdone, int [] ends){
        S19060=1;
    currentMode_thread_2 = 0;//sysj\rotaryTableController.sysj line: 35, column: 3
    S19033=0;
    if(mode.getprestatus()){//sysj\rotaryTableController.sysj line: 38, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\rotaryTableController.sysj line: 39, column: 5
      if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 41, column: 8
        manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 42, column: 5
        currsigs.addElement(manualMode_1);
        S19033=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 45, column: 5
        currsigs.addElement(autoMode_1);
        S19033=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\rotaryTableController.sysj line: 41, column: 8
        manualMode_1.setPresent();//sysj\rotaryTableController.sysj line: 42, column: 5
        currsigs.addElement(manualMode_1);
        S19033=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\rotaryTableController.sysj line: 45, column: 5
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
      switch(S19774){
        case 0 : 
          S19774=0;
          break RUN;
        
        case 1 : 
          S19774=2;
          S19774=2;
          autoMode_1.setClear();//sysj\rotaryTableController.sysj line: 15, column: 2
          manualMode_1.setClear();//sysj\rotaryTableController.sysj line: 16, column: 2
          thread19776(tdone,ends);
          thread19777(tdone,ends);
          thread19778(tdone,ends);
          int biggest19779 = 0;
          if(ends[2]>=biggest19779){
            biggest19779=ends[2];
          }
          if(ends[3]>=biggest19779){
            biggest19779=ends[3];
          }
          if(ends[4]>=biggest19779){
            biggest19779=ends[4];
          }
          if(biggest19779 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\rotaryTableController.sysj line: 15, column: 2
          manualMode_1.setClear();//sysj\rotaryTableController.sysj line: 16, column: 2
          thread19780(tdone,ends);
          thread19781(tdone,ends);
          thread19782(tdone,ends);
          int biggest19783 = 0;
          if(ends[2]>=biggest19783){
            biggest19783=ends[2];
          }
          if(ends[3]>=biggest19783){
            biggest19783=ends[3];
          }
          if(ends[4]>=biggest19783){
            biggest19783=ends[4];
          }
          if(biggest19783 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest19783 == 0){
            S19774=0;
            active[1]=0;
            ends[1]=0;
            S19774=0;
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
