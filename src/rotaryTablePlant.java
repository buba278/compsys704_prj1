import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.RotaryTableGUI;//sysj\rotaryTablePlant.sysj line: 1, column: 1
import run.RotaryConveyorBridge;//sysj\rotaryTablePlant.sysj line: 2, column: 1

public class rotaryTablePlant extends ClockDomain{
  public rotaryTablePlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal rotaryTableTrigger = new Signal("rotaryTableTrigger", Signal.INPUT);
  public Signal start = new Signal("start", Signal.INPUT);
  public Signal capOnBottleAtPos1Toggle = new Signal("capOnBottleAtPos1Toggle", Signal.INPUT);
  public Signal bottleAtPos5Toggle = new Signal("bottleAtPos5Toggle", Signal.INPUT);
  public Signal tableAlignedWithSensor = new Signal("tableAlignedWithSensor", Signal.OUTPUT);
  public Signal bottleAtPos5 = new Signal("bottleAtPos5", Signal.OUTPUT);
  public Signal capOnBottleAtPos1 = new Signal("capOnBottleAtPos1", Signal.OUTPUT);
  public Signal tableAlignedWithSensorE = new Signal("tableAlignedWithSensorE", Signal.OUTPUT);
  public Signal bottleAtPos5E = new Signal("bottleAtPos5E", Signal.OUTPUT);
  public Signal capOnBottleAtPos1E = new Signal("capOnBottleAtPos1E", Signal.OUTPUT);
  public Signal rotaryTableTriggerE = new Signal("rotaryTableTriggerE", Signal.OUTPUT);
  private long t0_thread_5;//sysj\rotaryTablePlant.sysj line: 61, column: 5
  private int S12594 = 1;
  private int S12215 = 1;
  private int S12214 = 1;
  private int S12188 = 1;
  private int S12223 = 1;
  private int S12217 = 1;
  private int S12231 = 1;
  private int S12353 = 1;
  private int S12271 = 1;
  private int S12247 = 1;
  private int S12387 = 1;
  private int S12361 = 1;
  private int S12369 = 1;
  private int S12377 = 1;
  private int S12385 = 1;
  
  private int[] ends = new int[11];
  private int[] tdone = new int[11];
  
  public void thread12615(int [] tdone, int [] ends){
        switch(S12385){
      case 0 : 
        active[10]=0;
        ends[10]=0;
        tdone[10]=1;
        break;
      
      case 1 : 
        if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 87, column: 24
          rotaryTableTriggerE.setPresent();//sysj\rotaryTablePlant.sysj line: 87, column: 44
          currsigs.addElement(rotaryTableTriggerE);
          active[10]=1;
          ends[10]=1;
          tdone[10]=1;
        }
        else {
          active[10]=1;
          ends[10]=1;
          tdone[10]=1;
        }
        break;
      
    }
  }

  public void thread12614(int [] tdone, int [] ends){
        switch(S12377){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTablePlant.sysj line: 85, column: 24
          capOnBottleAtPos1E.setPresent();//sysj\rotaryTablePlant.sysj line: 85, column: 43
          currsigs.addElement(capOnBottleAtPos1E);
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

  public void thread12613(int [] tdone, int [] ends){
        switch(S12369){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 83, column: 24
          bottleAtPos5E.setPresent();//sysj\rotaryTablePlant.sysj line: 83, column: 38
          currsigs.addElement(bottleAtPos5E);
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

  public void thread12612(int [] tdone, int [] ends){
        switch(S12361){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 81, column: 24
          tableAlignedWithSensorE.setPresent();//sysj\rotaryTablePlant.sysj line: 81, column: 48
          currsigs.addElement(tableAlignedWithSensorE);
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

  public void thread12611(int [] tdone, int [] ends){
        switch(S12387){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        thread12612(tdone,ends);
        thread12613(tdone,ends);
        thread12614(tdone,ends);
        thread12615(tdone,ends);
        int biggest12616 = 0;
        if(ends[7]>=biggest12616){
          biggest12616=ends[7];
        }
        if(ends[8]>=biggest12616){
          biggest12616=ends[8];
        }
        if(ends[9]>=biggest12616){
          biggest12616=ends[9];
        }
        if(ends[10]>=biggest12616){
          biggest12616=ends[10];
        }
        if(biggest12616 == 1){
          active[6]=1;
          ends[6]=1;
          tdone[6]=1;
        }
        //FINXME code
        if(biggest12616 == 0){
          S12387=0;
          active[6]=0;
          ends[6]=0;
          tdone[6]=1;
        }
        break;
      
    }
  }

  public void thread12610(int [] tdone, int [] ends){
        switch(S12353){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S12271){
          case 0 : 
            if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 59, column: 10
              S12271=1;
              if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 60, column: 12
                t0_thread_5 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 61, column: 5
                S12247=0;
                if(System.currentTimeMillis() - t0_thread_5 < 200){//sysj\rotaryTablePlant.sysj line: 62, column: 12
                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 63, column: 6
                  active[5]=1;
                  ends[5]=1;
                  tdone[5]=1;
                }
                else {
                  ends[5]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 62, column: 5
                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 66, column: 5
                  S12247=1;
                  active[5]=1;
                  ends[5]=1;
                  tdone[5]=1;
                }
              }
              else {
                S12271=2;
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
            }
            else {
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 1 : 
            switch(S12247){
              case 0 : 
                if(System.currentTimeMillis() - t0_thread_5 < 200){//sysj\rotaryTablePlant.sysj line: 62, column: 12
                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 63, column: 6
                  active[5]=1;
                  ends[5]=1;
                  tdone[5]=1;
                }
                else {
                  ends[5]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 62, column: 5
                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 66, column: 5
                  S12247=1;
                  active[5]=1;
                  ends[5]=1;
                  tdone[5]=1;
                }
                break;
              
              case 1 : 
                if(!bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 74, column: 11
                  S12271=2;
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
            break;
          
          case 2 : 
            S12271=2;
            S12271=0;
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread12609(int [] tdone, int [] ends){
        switch(S12231){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        if(bottleAtPos5Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 42, column: 12
          bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 43, column: 5
          currsigs.addElement(bottleAtPos5);
          active[4]=1;
          ends[4]=1;
          tdone[4]=1;
        }
        else {
          active[4]=1;
          ends[4]=1;
          tdone[4]=1;
        }
        break;
      
    }
  }

  public void thread12608(int [] tdone, int [] ends){
        switch(S12223){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S12217){
          case 0 : 
            S12217=0;
            if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 31, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 32, column: 5
              currsigs.addElement(capOnBottleAtPos1);
              S12217=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S12217=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            S12217=1;
            S12217=0;
            if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 31, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 32, column: 5
              currsigs.addElement(capOnBottleAtPos1);
              S12217=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S12217=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread12607(int [] tdone, int [] ends){
        switch(S12215){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S12214){
          case 0 : 
            if(start.getprestatus()){//sysj\rotaryTablePlant.sysj line: 17, column: 9
              S12214=1;
              S12188=0;
              tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 20, column: 5
              currsigs.addElement(tableAlignedWithSensor);
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
          
          case 1 : 
            switch(S12188){
              case 0 : 
                if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 19, column: 10
                  S12188=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 20, column: 5
                  currsigs.addElement(tableAlignedWithSensor);
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                break;
              
              case 1 : 
                S12188=1;
                S12188=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 2 : 
                S12188=2;
                S12188=3;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 3 : 
                S12188=3;
                S12188=0;
                tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 20, column: 5
                currsigs.addElement(tableAlignedWithSensor);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread12604(int [] tdone, int [] ends){
        S12385=1;
    if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 87, column: 24
      rotaryTableTriggerE.setPresent();//sysj\rotaryTablePlant.sysj line: 87, column: 44
      currsigs.addElement(rotaryTableTriggerE);
      active[10]=1;
      ends[10]=1;
      tdone[10]=1;
    }
    else {
      active[10]=1;
      ends[10]=1;
      tdone[10]=1;
    }
  }

  public void thread12603(int [] tdone, int [] ends){
        S12377=1;
    if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTablePlant.sysj line: 85, column: 24
      capOnBottleAtPos1E.setPresent();//sysj\rotaryTablePlant.sysj line: 85, column: 43
      currsigs.addElement(capOnBottleAtPos1E);
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

  public void thread12602(int [] tdone, int [] ends){
        S12369=1;
    if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 83, column: 24
      bottleAtPos5E.setPresent();//sysj\rotaryTablePlant.sysj line: 83, column: 38
      currsigs.addElement(bottleAtPos5E);
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

  public void thread12601(int [] tdone, int [] ends){
        S12361=1;
    if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 81, column: 24
      tableAlignedWithSensorE.setPresent();//sysj\rotaryTablePlant.sysj line: 81, column: 48
      currsigs.addElement(tableAlignedWithSensorE);
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

  public void thread12600(int [] tdone, int [] ends){
        S12387=1;
    thread12601(tdone,ends);
    thread12602(tdone,ends);
    thread12603(tdone,ends);
    thread12604(tdone,ends);
    int biggest12605 = 0;
    if(ends[7]>=biggest12605){
      biggest12605=ends[7];
    }
    if(ends[8]>=biggest12605){
      biggest12605=ends[8];
    }
    if(ends[9]>=biggest12605){
      biggest12605=ends[9];
    }
    if(ends[10]>=biggest12605){
      biggest12605=ends[10];
    }
    if(biggest12605 == 1){
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

  public void thread12599(int [] tdone, int [] ends){
        S12353=1;
    S12271=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread12598(int [] tdone, int [] ends){
        S12231=1;
    if(bottleAtPos5Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 42, column: 12
      bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 43, column: 5
      currsigs.addElement(bottleAtPos5);
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

  public void thread12597(int [] tdone, int [] ends){
        S12223=1;
    S12217=0;
    if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 31, column: 12
      capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 32, column: 5
      currsigs.addElement(capOnBottleAtPos1);
      S12217=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
    else {
      S12217=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
  }

  public void thread12596(int [] tdone, int [] ends){
        S12215=1;
    S12214=0;
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
      switch(S12594){
        case 0 : 
          S12594=0;
          break RUN;
        
        case 1 : 
          S12594=2;
          S12594=2;
          new Thread(new RotaryTableGUI()).start();//sysj\rotaryTablePlant.sysj line: 13, column: 2
          thread12596(tdone,ends);
          thread12597(tdone,ends);
          thread12598(tdone,ends);
          thread12599(tdone,ends);
          thread12600(tdone,ends);
          int biggest12606 = 0;
          if(ends[2]>=biggest12606){
            biggest12606=ends[2];
          }
          if(ends[3]>=biggest12606){
            biggest12606=ends[3];
          }
          if(ends[4]>=biggest12606){
            biggest12606=ends[4];
          }
          if(ends[5]>=biggest12606){
            biggest12606=ends[5];
          }
          if(ends[6]>=biggest12606){
            biggest12606=ends[6];
          }
          if(biggest12606 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread12607(tdone,ends);
          thread12608(tdone,ends);
          thread12609(tdone,ends);
          thread12610(tdone,ends);
          thread12611(tdone,ends);
          int biggest12617 = 0;
          if(ends[2]>=biggest12617){
            biggest12617=ends[2];
          }
          if(ends[3]>=biggest12617){
            biggest12617=ends[3];
          }
          if(ends[4]>=biggest12617){
            biggest12617=ends[4];
          }
          if(ends[5]>=biggest12617){
            biggest12617=ends[5];
          }
          if(ends[6]>=biggest12617){
            biggest12617=ends[6];
          }
          if(biggest12617 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest12617 == 0){
            S12594=0;
            active[1]=0;
            ends[1]=0;
            S12594=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
          rotaryTableTrigger.gethook();
          start.gethook();
          capOnBottleAtPos1Toggle.gethook();
          bottleAtPos5Toggle.gethook();
          df = true;
        }
        runClockDomain();
      }
      rotaryTableTrigger.setpreclear();
      start.setpreclear();
      capOnBottleAtPos1Toggle.setpreclear();
      bottleAtPos5Toggle.setpreclear();
      tableAlignedWithSensor.setpreclear();
      bottleAtPos5.setpreclear();
      capOnBottleAtPos1.setpreclear();
      tableAlignedWithSensorE.setpreclear();
      bottleAtPos5E.setpreclear();
      capOnBottleAtPos1E.setpreclear();
      rotaryTableTriggerE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = rotaryTableTrigger.getStatus() ? rotaryTableTrigger.setprepresent() : rotaryTableTrigger.setpreclear();
      rotaryTableTrigger.setpreval(rotaryTableTrigger.getValue());
      rotaryTableTrigger.setClear();
      dummyint = start.getStatus() ? start.setprepresent() : start.setpreclear();
      start.setpreval(start.getValue());
      start.setClear();
      dummyint = capOnBottleAtPos1Toggle.getStatus() ? capOnBottleAtPos1Toggle.setprepresent() : capOnBottleAtPos1Toggle.setpreclear();
      capOnBottleAtPos1Toggle.setpreval(capOnBottleAtPos1Toggle.getValue());
      capOnBottleAtPos1Toggle.setClear();
      dummyint = bottleAtPos5Toggle.getStatus() ? bottleAtPos5Toggle.setprepresent() : bottleAtPos5Toggle.setpreclear();
      bottleAtPos5Toggle.setpreval(bottleAtPos5Toggle.getValue());
      bottleAtPos5Toggle.setClear();
      tableAlignedWithSensor.sethook();
      tableAlignedWithSensor.setClear();
      bottleAtPos5.sethook();
      bottleAtPos5.setClear();
      capOnBottleAtPos1.sethook();
      capOnBottleAtPos1.setClear();
      tableAlignedWithSensorE.sethook();
      tableAlignedWithSensorE.setClear();
      bottleAtPos5E.sethook();
      bottleAtPos5E.setClear();
      capOnBottleAtPos1E.sethook();
      capOnBottleAtPos1E.setClear();
      rotaryTableTriggerE.sethook();
      rotaryTableTriggerE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        rotaryTableTrigger.gethook();
        start.gethook();
        capOnBottleAtPos1Toggle.gethook();
        bottleAtPos5Toggle.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
