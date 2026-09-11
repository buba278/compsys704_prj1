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
  private int S4500 = 1;
  private int S4365 = 1;
  private int S4364 = 1;
  private int S4338 = 1;
  private int S4373 = 1;
  private int S4367 = 1;
  private int S4381 = 1;
  private int S4415 = 1;
  private int S4389 = 1;
  private int S4397 = 1;
  private int S4405 = 1;
  private int S4413 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread4519(int [] tdone, int [] ends){
        switch(S4413){
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

  public void thread4518(int [] tdone, int [] ends){
        switch(S4405){
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

  public void thread4517(int [] tdone, int [] ends){
        switch(S4397){
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

  public void thread4516(int [] tdone, int [] ends){
        switch(S4389){
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

  public void thread4515(int [] tdone, int [] ends){
        switch(S4415){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        thread4516(tdone,ends);
        thread4517(tdone,ends);
        thread4518(tdone,ends);
        thread4519(tdone,ends);
        int biggest4520 = 0;
        if(ends[6]>=biggest4520){
          biggest4520=ends[6];
        }
        if(ends[7]>=biggest4520){
          biggest4520=ends[7];
        }
        if(ends[8]>=biggest4520){
          biggest4520=ends[8];
        }
        if(ends[9]>=biggest4520){
          biggest4520=ends[9];
        }
        if(biggest4520 == 1){
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        //FINXME code
        if(biggest4520 == 0){
          S4415=0;
          active[5]=0;
          ends[5]=0;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread4514(int [] tdone, int [] ends){
        switch(S4381){
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

  public void thread4513(int [] tdone, int [] ends){
        switch(S4373){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S4367){
          case 0 : 
            S4367=0;
            if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 30, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 31, column: 5
              currsigs.addElement(capOnBottleAtPos1);
              S4367=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S4367=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            S4367=1;
            S4367=0;
            if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 30, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 31, column: 5
              currsigs.addElement(capOnBottleAtPos1);
              S4367=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S4367=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread4512(int [] tdone, int [] ends){
        switch(S4365){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S4364){
          case 0 : 
            if(start.getprestatus()){//sysj\rotaryTablePlant.sysj line: 16, column: 9
              S4364=1;
              S4338=0;
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
            switch(S4338){
              case 0 : 
                if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 18, column: 10
                  S4338=1;
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
                S4338=1;
                S4338=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 2 : 
                S4338=2;
                S4338=3;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 3 : 
                S4338=3;
                S4338=0;
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

  public void thread4509(int [] tdone, int [] ends){
        S4413=1;
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

  public void thread4508(int [] tdone, int [] ends){
        S4405=1;
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

  public void thread4507(int [] tdone, int [] ends){
        S4397=1;
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

  public void thread4506(int [] tdone, int [] ends){
        S4389=1;
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

  public void thread4505(int [] tdone, int [] ends){
        S4415=1;
    thread4506(tdone,ends);
    thread4507(tdone,ends);
    thread4508(tdone,ends);
    thread4509(tdone,ends);
    int biggest4510 = 0;
    if(ends[6]>=biggest4510){
      biggest4510=ends[6];
    }
    if(ends[7]>=biggest4510){
      biggest4510=ends[7];
    }
    if(ends[8]>=biggest4510){
      biggest4510=ends[8];
    }
    if(ends[9]>=biggest4510){
      biggest4510=ends[9];
    }
    if(biggest4510 == 1){
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread4504(int [] tdone, int [] ends){
        S4381=1;
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

  public void thread4503(int [] tdone, int [] ends){
        S4373=1;
    S4367=0;
    if(capOnBottleAtPos1Toggle.getprestatus()){//sysj\rotaryTablePlant.sysj line: 30, column: 12
      capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 31, column: 5
      currsigs.addElement(capOnBottleAtPos1);
      S4367=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
    else {
      S4367=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
  }

  public void thread4502(int [] tdone, int [] ends){
        S4365=1;
    S4364=0;
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
      switch(S4500){
        case 0 : 
          S4500=0;
          break RUN;
        
        case 1 : 
          S4500=2;
          S4500=2;
          new Thread(new RotaryTableGUI()).start();//sysj\rotaryTablePlant.sysj line: 12, column: 2
          thread4502(tdone,ends);
          thread4503(tdone,ends);
          thread4504(tdone,ends);
          thread4505(tdone,ends);
          int biggest4511 = 0;
          if(ends[2]>=biggest4511){
            biggest4511=ends[2];
          }
          if(ends[3]>=biggest4511){
            biggest4511=ends[3];
          }
          if(ends[4]>=biggest4511){
            biggest4511=ends[4];
          }
          if(ends[5]>=biggest4511){
            biggest4511=ends[5];
          }
          if(biggest4511 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread4512(tdone,ends);
          thread4513(tdone,ends);
          thread4514(tdone,ends);
          thread4515(tdone,ends);
          int biggest4521 = 0;
          if(ends[2]>=biggest4521){
            biggest4521=ends[2];
          }
          if(ends[3]>=biggest4521){
            biggest4521=ends[3];
          }
          if(ends[4]>=biggest4521){
            biggest4521=ends[4];
          }
          if(ends[5]>=biggest4521){
            biggest4521=ends[5];
          }
          if(biggest4521 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest4521 == 0){
            S4500=0;
            active[1]=0;
            ends[1]=0;
            S4500=0;
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
