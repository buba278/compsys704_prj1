import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.RotaryTableGUI;//sysj\rotaryTablePlant.sysj line: 1, column: 1

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
  private int S10288 = 1;
  private int S10153 = 1;
  private int S10152 = 1;
  private int S10126 = 1;
  private int S10161 = 1;
  private int S10155 = 1;
  private int S10169 = 1;
  private int S10203 = 1;
  private int S10177 = 1;
  private int S10185 = 1;
  private int S10193 = 1;
  private int S10201 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread10307(int [] tdone, int [] ends){
        switch(S10201){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 55, column: 24
          rotaryTableTriggerE.setPresent();//sysj\rotaryTablePlant.sysj line: 55, column: 44
          currsigs.addElement(rotaryTableTriggerE);
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

  public void thread10306(int [] tdone, int [] ends){
        switch(S10193){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTablePlant.sysj line: 53, column: 24
          capOnBottleAtPos1E.setPresent();//sysj\rotaryTablePlant.sysj line: 53, column: 43
          currsigs.addElement(capOnBottleAtPos1E);
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

  public void thread10305(int [] tdone, int [] ends){
        switch(S10185){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 51, column: 24
          bottleAtPos5E.setPresent();//sysj\rotaryTablePlant.sysj line: 51, column: 38
          currsigs.addElement(bottleAtPos5E);
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

  public void thread10304(int [] tdone, int [] ends){
        switch(S10177){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 49, column: 24
          tableAlignedWithSensorE.setPresent();//sysj\rotaryTablePlant.sysj line: 49, column: 48
          currsigs.addElement(tableAlignedWithSensorE);
          active[6]=1;
          ends[6]=1;
          tdone[6]=1;
        }
        else {
          active[6]=1;
          ends[6]=1;
          tdone[6]=1;
        }
        break;
      
    }
  }

  public void thread10303(int [] tdone, int [] ends){
        switch(S10203){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        thread10304(tdone,ends);
        thread10305(tdone,ends);
        thread10306(tdone,ends);
        thread10307(tdone,ends);
        int biggest10308 = 0;
        if(ends[6]>=biggest10308){
          biggest10308=ends[6];
        }
        if(ends[7]>=biggest10308){
          biggest10308=ends[7];
        }
        if(ends[8]>=biggest10308){
          biggest10308=ends[8];
        }
        if(ends[9]>=biggest10308){
          biggest10308=ends[9];
        }
        if(biggest10308 == 1){
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        //FINXME code
        if(biggest10308 == 0){
          S10203=0;
          active[5]=0;
          ends[5]=0;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread10302(int [] tdone, int [] ends){
        switch(S10169){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        if(bottleAtPos5Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 41, column: 12
          bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 42, column: 5
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

  public void thread10301(int [] tdone, int [] ends){
        switch(S10161){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S10155){
          case 0 : 
            S10155=0;
            if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 30, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 31, column: 5
              currsigs.addElement(capOnBottleAtPos1);
              S10155=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S10155=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            S10155=1;
            S10155=0;
            if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 30, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 31, column: 5
              currsigs.addElement(capOnBottleAtPos1);
              S10155=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S10155=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread10300(int [] tdone, int [] ends){
        switch(S10153){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S10152){
          case 0 : 
            if(start.getprestatus()){//sysj\rotaryTablePlant.sysj line: 16, column: 9
              S10152=1;
              S10126=0;
              tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 19, column: 5
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
            switch(S10126){
              case 0 : 
                if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 18, column: 10
                  S10126=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 19, column: 5
                  currsigs.addElement(tableAlignedWithSensor);
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                break;
              
              case 1 : 
                S10126=1;
                S10126=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 2 : 
                S10126=2;
                S10126=3;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 3 : 
                S10126=3;
                S10126=0;
                tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 19, column: 5
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

  public void thread10297(int [] tdone, int [] ends){
        S10201=1;
    if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 55, column: 24
      rotaryTableTriggerE.setPresent();//sysj\rotaryTablePlant.sysj line: 55, column: 44
      currsigs.addElement(rotaryTableTriggerE);
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

  public void thread10296(int [] tdone, int [] ends){
        S10193=1;
    if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTablePlant.sysj line: 53, column: 24
      capOnBottleAtPos1E.setPresent();//sysj\rotaryTablePlant.sysj line: 53, column: 43
      currsigs.addElement(capOnBottleAtPos1E);
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

  public void thread10295(int [] tdone, int [] ends){
        S10185=1;
    if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 51, column: 24
      bottleAtPos5E.setPresent();//sysj\rotaryTablePlant.sysj line: 51, column: 38
      currsigs.addElement(bottleAtPos5E);
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

  public void thread10294(int [] tdone, int [] ends){
        S10177=1;
    if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 49, column: 24
      tableAlignedWithSensorE.setPresent();//sysj\rotaryTablePlant.sysj line: 49, column: 48
      currsigs.addElement(tableAlignedWithSensorE);
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

  public void thread10293(int [] tdone, int [] ends){
        S10203=1;
    thread10294(tdone,ends);
    thread10295(tdone,ends);
    thread10296(tdone,ends);
    thread10297(tdone,ends);
    int biggest10298 = 0;
    if(ends[6]>=biggest10298){
      biggest10298=ends[6];
    }
    if(ends[7]>=biggest10298){
      biggest10298=ends[7];
    }
    if(ends[8]>=biggest10298){
      biggest10298=ends[8];
    }
    if(ends[9]>=biggest10298){
      biggest10298=ends[9];
    }
    if(biggest10298 == 1){
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread10292(int [] tdone, int [] ends){
        S10169=1;
    if(bottleAtPos5Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 41, column: 12
      bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 42, column: 5
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

  public void thread10291(int [] tdone, int [] ends){
        S10161=1;
    S10155=0;
    if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 30, column: 12
      capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 31, column: 5
      currsigs.addElement(capOnBottleAtPos1);
      S10155=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
    else {
      S10155=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
  }

  public void thread10290(int [] tdone, int [] ends){
        S10153=1;
    S10152=0;
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
      switch(S10288){
        case 0 : 
          S10288=0;
          break RUN;
        
        case 1 : 
          S10288=2;
          S10288=2;
          new Thread(new RotaryTableGUI()).start();//sysj\rotaryTablePlant.sysj line: 12, column: 2
          thread10290(tdone,ends);
          thread10291(tdone,ends);
          thread10292(tdone,ends);
          thread10293(tdone,ends);
          int biggest10299 = 0;
          if(ends[2]>=biggest10299){
            biggest10299=ends[2];
          }
          if(ends[3]>=biggest10299){
            biggest10299=ends[3];
          }
          if(ends[4]>=biggest10299){
            biggest10299=ends[4];
          }
          if(ends[5]>=biggest10299){
            biggest10299=ends[5];
          }
          if(biggest10299 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread10300(tdone,ends);
          thread10301(tdone,ends);
          thread10302(tdone,ends);
          thread10303(tdone,ends);
          int biggest10309 = 0;
          if(ends[2]>=biggest10309){
            biggest10309=ends[2];
          }
          if(ends[3]>=biggest10309){
            biggest10309=ends[3];
          }
          if(ends[4]>=biggest10309){
            biggest10309=ends[4];
          }
          if(ends[5]>=biggest10309){
            biggest10309=ends[5];
          }
          if(biggest10309 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest10309 == 0){
            S10288=0;
            active[1]=0;
            ends[1]=0;
            S10288=0;
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
