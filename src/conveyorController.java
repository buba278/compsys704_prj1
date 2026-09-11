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
  private int S1980 = 1;
  private int S1194 = 1;
  private int S1167 = 1;
  private int S1928 = 1;
  private int S1438 = 1;
  private int S1225 = 1;
  private int S1201 = 1;
  private int S1209 = 1;
  private int S1978 = 1;
  private int S1944 = 1;
  
  private int[] ends = new int[7];
  private int[] tdone = new int[7];
  
  public void thread1994(int [] tdone, int [] ends){
        switch(S1978){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S1944){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\conveyorController.sysj line: 61, column: 10
              S1944=1;
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
              S1944=0;
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

  public void thread1992(int [] tdone, int [] ends){
        switch(S1209){
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

  public void thread1991(int [] tdone, int [] ends){
        switch(S1201){
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

  public void thread1989(int [] tdone, int [] ends){
        S1209=1;
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

  public void thread1988(int [] tdone, int [] ends){
        S1201=1;
    motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 42, column: 8
    currsigs.addElement(motConveyorOnOff);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread1987(int [] tdone, int [] ends){
        switch(S1928){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S1438){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\conveyorController.sysj line: 33, column: 10
              S1438=1;
              S1225=0;
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
              S1438=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S1225){
                case 0 : 
                  if(!bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 37, column: 12
                    S1225=1;
                    thread1988(tdone,ends);
                    thread1989(tdone,ends);
                    int biggest1990 = 0;
                    if(ends[4]>=biggest1990){
                      biggest1990=ends[4];
                    }
                    if(ends[5]>=biggest1990){
                      biggest1990=ends[5];
                    }
                    if(biggest1990 == 1){
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
                    S1225=0;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    thread1991(tdone,ends);
                    thread1992(tdone,ends);
                    int biggest1993 = 0;
                    if(ends[4]>=biggest1993){
                      biggest1993=ends[4];
                    }
                    if(ends[5]>=biggest1993){
                      biggest1993=ends[5];
                    }
                    if(biggest1993 == 1){
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    //FINXME code
                    if(biggest1993 == 0){
                      S1225=0;
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

  public void thread1986(int [] tdone, int [] ends){
        switch(S1194){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S1167){
          case 0 : 
            S1167=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 14, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 15, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S1167=1;
            S1167=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 14, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 15, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
                currsigs.addElement(manualMode_1);
                S1167=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
                currsigs.addElement(autoMode_1);
                S1167=1;
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

  public void thread1984(int [] tdone, int [] ends){
        S1978=1;
    S1944=0;
    active[6]=1;
    ends[6]=1;
    tdone[6]=1;
  }

  public void thread1983(int [] tdone, int [] ends){
        S1928=1;
    S1438=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread1982(int [] tdone, int [] ends){
        S1194=1;
    currentMode_thread_2 = 0;//sysj\conveyorController.sysj line: 12, column: 3
    S1167=0;
    if(mode.getprestatus()){//sysj\conveyorController.sysj line: 14, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 15, column: 5
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
        currsigs.addElement(manualMode_1);
        S1167=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
        currsigs.addElement(autoMode_1);
        S1167=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 17, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 18, column: 5
        currsigs.addElement(manualMode_1);
        S1167=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 21, column: 5
        currsigs.addElement(autoMode_1);
        S1167=1;
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
      switch(S1980){
        case 0 : 
          S1980=0;
          break RUN;
        
        case 1 : 
          S1980=2;
          S1980=2;
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          thread1982(tdone,ends);
          thread1983(tdone,ends);
          thread1984(tdone,ends);
          int biggest1985 = 0;
          if(ends[2]>=biggest1985){
            biggest1985=ends[2];
          }
          if(ends[3]>=biggest1985){
            biggest1985=ends[3];
          }
          if(ends[6]>=biggest1985){
            biggest1985=ends[6];
          }
          if(biggest1985 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 9, column: 2
          thread1986(tdone,ends);
          thread1987(tdone,ends);
          thread1994(tdone,ends);
          int biggest1995 = 0;
          if(ends[2]>=biggest1995){
            biggest1995=ends[2];
          }
          if(ends[3]>=biggest1995){
            biggest1995=ends[3];
          }
          if(ends[6]>=biggest1995){
            biggest1995=ends[6];
          }
          if(biggest1995 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest1995 == 0){
            S1980=0;
            active[1]=0;
            ends[1]=0;
            S1980=0;
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
