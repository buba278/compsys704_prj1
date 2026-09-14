import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.ConveyorGUI;//sysj\conveyorPlant.sysj line: 1, column: 1

public class conveyorPlant extends ClockDomain{
  public conveyorPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal motConveyorOnOff = new Signal("motConveyorOnOff", Signal.INPUT);
  public Signal enable = new Signal("enable", Signal.INPUT);
  public Signal loadBottle = new Signal("loadBottle", Signal.INPUT);
  public Signal bottleFromTable = new Signal("bottleFromTable", Signal.INPUT);
  public Signal bottleAtPos1 = new Signal("bottleAtPos1", Signal.OUTPUT);
  public Signal bottleLeftPos5 = new Signal("bottleLeftPos5", Signal.OUTPUT);
  public Signal bottleAtPos1E = new Signal("bottleAtPos1E", Signal.OUTPUT);
  public Signal bottleLeftPos5E = new Signal("bottleLeftPos5E", Signal.OUTPUT);
  public Signal motorOnE = new Signal("motorOnE", Signal.OUTPUT);
  private int S3484 = 1;
  private int S3286 = 1;
  private int S3280 = 1;
  private int S3324 = 1;
  private int S3296 = 1;
  private int S3362 = 1;
  private int S3334 = 1;
  private int S3380 = 1;
  private int S3370 = 1;
  private int S3378 = 1;
  
  private int[] ends = new int[8];
  private int[] tdone = new int[8];
  
  public void thread3499(int [] tdone, int [] ends){
        switch(S3378){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(bottleLeftPos5.getprestatus()){//sysj\conveyorPlant.sysj line: 50, column: 24
          bottleLeftPos5E.setPresent();//sysj\conveyorPlant.sysj line: 50, column: 40
          currsigs.addElement(bottleLeftPos5E);
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

  public void thread3498(int [] tdone, int [] ends){
        switch(S3370){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(bottleAtPos1.getprestatus()){//sysj\conveyorPlant.sysj line: 48, column: 24
          bottleAtPos1E.setPresent();//sysj\conveyorPlant.sysj line: 48, column: 38
          currsigs.addElement(bottleAtPos1E);
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

  public void thread3497(int [] tdone, int [] ends){
        switch(S3380){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        thread3498(tdone,ends);
        thread3499(tdone,ends);
        int biggest3500 = 0;
        if(ends[6]>=biggest3500){
          biggest3500=ends[6];
        }
        if(ends[7]>=biggest3500){
          biggest3500=ends[7];
        }
        if(biggest3500 == 1){
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        //FINXME code
        if(biggest3500 == 0){
          S3380=0;
          active[5]=0;
          ends[5]=0;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread3496(int [] tdone, int [] ends){
        switch(S3362){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S3334){
          case 0 : 
            if(bottleFromTable.getprestatus()){//sysj\conveyorPlant.sysj line: 39, column: 10
              S3334=1;
              bottleLeftPos5.setPresent();//sysj\conveyorPlant.sysj line: 41, column: 5
              currsigs.addElement(bottleLeftPos5);
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
          
          case 1 : 
            if(motConveyorOnOff.getprestatus() && enable.getprestatus()){//sysj\conveyorPlant.sysj line: 40, column: 10
              S3334=2;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              bottleLeftPos5.setPresent();//sysj\conveyorPlant.sysj line: 41, column: 5
              currsigs.addElement(bottleLeftPos5);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 2 : 
            if(!enable.getprestatus()){//sysj\conveyorPlant.sysj line: 43, column: 10
              S3334=0;
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
        break;
      
    }
  }

  public void thread3495(int [] tdone, int [] ends){
        switch(S3324){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S3296){
          case 0 : 
            if(loadBottle.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 10
              S3296=1;
              bottleAtPos1.setPresent();//sysj\conveyorPlant.sysj line: 28, column: 5
              currsigs.addElement(bottleAtPos1);
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
            if(motConveyorOnOff.getprestatus() && enable.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 10
              S3296=2;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              bottleAtPos1.setPresent();//sysj\conveyorPlant.sysj line: 28, column: 5
              currsigs.addElement(bottleAtPos1);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            if(!enable.getprestatus()){//sysj\conveyorPlant.sysj line: 30, column: 10
              S3296=0;
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
  }

  public void thread3494(int [] tdone, int [] ends){
        switch(S3286){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S3280){
          case 0 : 
            S3280=0;
            if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 15, column: 12
              motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 15, column: 31
              currsigs.addElement(motorOnE);
              S3280=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              S3280=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            S3280=1;
            S3280=0;
            if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 15, column: 12
              motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 15, column: 31
              currsigs.addElement(motorOnE);
              S3280=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              S3280=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread3491(int [] tdone, int [] ends){
        S3378=1;
    if(bottleLeftPos5.getprestatus()){//sysj\conveyorPlant.sysj line: 50, column: 24
      bottleLeftPos5E.setPresent();//sysj\conveyorPlant.sysj line: 50, column: 40
      currsigs.addElement(bottleLeftPos5E);
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

  public void thread3490(int [] tdone, int [] ends){
        S3370=1;
    if(bottleAtPos1.getprestatus()){//sysj\conveyorPlant.sysj line: 48, column: 24
      bottleAtPos1E.setPresent();//sysj\conveyorPlant.sysj line: 48, column: 38
      currsigs.addElement(bottleAtPos1E);
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

  public void thread3489(int [] tdone, int [] ends){
        S3380=1;
    thread3490(tdone,ends);
    thread3491(tdone,ends);
    int biggest3492 = 0;
    if(ends[6]>=biggest3492){
      biggest3492=ends[6];
    }
    if(ends[7]>=biggest3492){
      biggest3492=ends[7];
    }
    if(biggest3492 == 1){
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread3488(int [] tdone, int [] ends){
        S3362=1;
    S3334=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread3487(int [] tdone, int [] ends){
        S3324=1;
    S3296=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread3486(int [] tdone, int [] ends){
        S3286=1;
    S3280=0;
    if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 15, column: 12
      motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 15, column: 31
      currsigs.addElement(motorOnE);
      S3280=1;
      active[2]=1;
      ends[2]=1;
      tdone[2]=1;
    }
    else {
      S3280=1;
      active[2]=1;
      ends[2]=1;
      tdone[2]=1;
    }
  }

  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S3484){
        case 0 : 
          S3484=0;
          break RUN;
        
        case 1 : 
          S3484=2;
          S3484=2;
          new Thread(new ConveyorGUI()).start();//sysj\conveyorPlant.sysj line: 11, column: 2
          thread3486(tdone,ends);
          thread3487(tdone,ends);
          thread3488(tdone,ends);
          thread3489(tdone,ends);
          int biggest3493 = 0;
          if(ends[2]>=biggest3493){
            biggest3493=ends[2];
          }
          if(ends[3]>=biggest3493){
            biggest3493=ends[3];
          }
          if(ends[4]>=biggest3493){
            biggest3493=ends[4];
          }
          if(ends[5]>=biggest3493){
            biggest3493=ends[5];
          }
          if(biggest3493 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread3494(tdone,ends);
          thread3495(tdone,ends);
          thread3496(tdone,ends);
          thread3497(tdone,ends);
          int biggest3501 = 0;
          if(ends[2]>=biggest3501){
            biggest3501=ends[2];
          }
          if(ends[3]>=biggest3501){
            biggest3501=ends[3];
          }
          if(ends[4]>=biggest3501){
            biggest3501=ends[4];
          }
          if(ends[5]>=biggest3501){
            biggest3501=ends[5];
          }
          if(biggest3501 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest3501 == 0){
            S3484=0;
            active[1]=0;
            ends[1]=0;
            S3484=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0};
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
          motConveyorOnOff.gethook();
          enable.gethook();
          loadBottle.gethook();
          bottleFromTable.gethook();
          df = true;
        }
        runClockDomain();
      }
      motConveyorOnOff.setpreclear();
      enable.setpreclear();
      loadBottle.setpreclear();
      bottleFromTable.setpreclear();
      bottleAtPos1.setpreclear();
      bottleLeftPos5.setpreclear();
      bottleAtPos1E.setpreclear();
      bottleLeftPos5E.setpreclear();
      motorOnE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = motConveyorOnOff.getStatus() ? motConveyorOnOff.setprepresent() : motConveyorOnOff.setpreclear();
      motConveyorOnOff.setpreval(motConveyorOnOff.getValue());
      motConveyorOnOff.setClear();
      dummyint = enable.getStatus() ? enable.setprepresent() : enable.setpreclear();
      enable.setpreval(enable.getValue());
      enable.setClear();
      dummyint = loadBottle.getStatus() ? loadBottle.setprepresent() : loadBottle.setpreclear();
      loadBottle.setpreval(loadBottle.getValue());
      loadBottle.setClear();
      dummyint = bottleFromTable.getStatus() ? bottleFromTable.setprepresent() : bottleFromTable.setpreclear();
      bottleFromTable.setpreval(bottleFromTable.getValue());
      bottleFromTable.setClear();
      bottleAtPos1.sethook();
      bottleAtPos1.setClear();
      bottleLeftPos5.sethook();
      bottleLeftPos5.setClear();
      bottleAtPos1E.sethook();
      bottleAtPos1E.setClear();
      bottleLeftPos5E.sethook();
      bottleLeftPos5E.setClear();
      motorOnE.sethook();
      motorOnE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        motConveyorOnOff.gethook();
        enable.gethook();
        loadBottle.gethook();
        bottleFromTable.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
