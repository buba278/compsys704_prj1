import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.LabellerGUI;//sysj\labellerPlant.sysj line: 1, column: 1

public class LabellerPlant extends ClockDomain{
  public LabellerPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal printLabel = new Signal("printLabel", Signal.INPUT);
  public Signal clampBottle = new Signal("clampBottle", Signal.INPUT);
  public Signal applyLabel = new Signal("applyLabel", Signal.INPUT);
  public Signal liquidARatio = new Signal("liquidARatio", Signal.INPUT);
  public Signal targetVolumeMl = new Signal("targetVolumeMl", Signal.INPUT);
  public Signal labelPrinted = new Signal("labelPrinted", Signal.OUTPUT);
  public Signal bottleClamped = new Signal("bottleClamped", Signal.OUTPUT);
  public Signal labelApplied = new Signal("labelApplied", Signal.OUTPUT);
  public Signal bottleAtLabeller = new Signal("bottleAtLabeller", Signal.OUTPUT);
  public Signal bottleAtLabellerE = new Signal("bottleAtLabellerE", Signal.OUTPUT);
  public Signal labelPrintedE = new Signal("labelPrintedE", Signal.OUTPUT);
  public Signal bottleClampedE = new Signal("bottleClampedE", Signal.OUTPUT);
  public Signal labelAppliedE = new Signal("labelAppliedE", Signal.OUTPUT);
  private int S8290 = 1;
  private int S7998 = 1;
  private int S7972 = 1;
  private int S8030 = 1;
  private int S8000 = 1;
  private int S8062 = 1;
  private int S8032 = 1;
  private int S8094 = 1;
  private int S8064 = 1;
  private int S8102 = 1;
  private int S8096 = 1;
  private int S8110 = 1;
  private int S8118 = 1;
  private int S8126 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread8308(int [] tdone, int [] ends){
        switch(S8126){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(labelApplied.getprestatus()){//sysj\labellerPlant.sysj line: 84, column: 26
          labelAppliedE.setPresent();//sysj\labellerPlant.sysj line: 84, column: 40
          currsigs.addElement(labelAppliedE);
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

  public void thread8307(int [] tdone, int [] ends){
        switch(S8118){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(bottleClamped.getprestatus()){//sysj\labellerPlant.sysj line: 82, column: 26
          bottleClampedE.setPresent();//sysj\labellerPlant.sysj line: 82, column: 41
          currsigs.addElement(bottleClampedE);
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

  public void thread8306(int [] tdone, int [] ends){
        switch(S8110){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(labelPrinted.getprestatus()){//sysj\labellerPlant.sysj line: 80, column: 26
          labelPrintedE.setPresent();//sysj\labellerPlant.sysj line: 80, column: 40
          currsigs.addElement(labelPrintedE);
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

  public void thread8305(int [] tdone, int [] ends){
        switch(S8102){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S8096){
          case 0 : 
            S8096=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S8096=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S8096=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
            S8096=1;
            S8096=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S8096=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S8096=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread8304(int [] tdone, int [] ends){
        switch(S8094){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S8064){
          case 0 : 
            if(applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 69, column: 19
              System.out.println("LabellerPlant [Applicator]: Applying label...");//sysj\labellerPlant.sysj line: 70, column: 13
              S8064=1;
              labelApplied.setPresent();//sysj\labellerPlant.sysj line: 72, column: 17
              currsigs.addElement(labelApplied);
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
          
          case 1 : 
            if(!applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 71, column: 19
              S8064=2;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              labelApplied.setPresent();//sysj\labellerPlant.sysj line: 72, column: 17
              currsigs.addElement(labelApplied);
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 2 : 
            S8064=2;
            S8064=0;
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread8303(int [] tdone, int [] ends){
        switch(S8062){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S8032){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 56, column: 10
              System.out.println("LabellerPlant [Clamp]: Clamping bottle...");//sysj\labellerPlant.sysj line: 57, column: 4
              S8032=1;
              bottleClamped.setPresent();//sysj\labellerPlant.sysj line: 59, column: 5
              currsigs.addElement(bottleClamped);
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
            if(!clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 58, column: 10
              System.out.println("LabellerPlant [Clamp]: Bottle released.");//sysj\labellerPlant.sysj line: 61, column: 4
              S8032=2;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              bottleClamped.setPresent();//sysj\labellerPlant.sysj line: 59, column: 5
              currsigs.addElement(bottleClamped);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 2 : 
            S8032=2;
            S8032=0;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread8302(int [] tdone, int [] ends){
        switch(S8030){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S8000){
          case 0 : 
            if(printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 44, column: 19
              System.out.println("LabellerPlant [Printer]: Printing label...");//sysj\labellerPlant.sysj line: 45, column: 13
              S8000=1;
              labelPrinted.setPresent();//sysj\labellerPlant.sysj line: 47, column: 17
              currsigs.addElement(labelPrinted);
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
            if(!printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 46, column: 19
              S8000=2;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              labelPrinted.setPresent();//sysj\labellerPlant.sysj line: 47, column: 17
              currsigs.addElement(labelPrinted);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            S8000=2;
            S8000=0;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread8301(int [] tdone, int [] ends){
        switch(S7998){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S7972){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 27, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle clamped, holding position...");//sysj\labellerPlant.sysj line: 30, column: 4
              S7972=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              bottleAtLabeller.setPresent();//sysj\labellerPlant.sysj line: 28, column: 5
              currsigs.addElement(bottleAtLabeller);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            if(!clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 36, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle released, ready for next.");//sysj\labellerPlant.sysj line: 37, column: 4
              System.out.println("LabellerPlant [Conveyor]: Presenting bottle at labeller...");//sysj\labellerPlant.sysj line: 26, column: 4
              S7972=0;
              bottleAtLabeller.setPresent();//sysj\labellerPlant.sysj line: 28, column: 5
              currsigs.addElement(bottleAtLabeller);
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
          
        }
        break;
      
    }
  }

  public void thread8299(int [] tdone, int [] ends){
        S8126=1;
    if(labelApplied.getprestatus()){//sysj\labellerPlant.sysj line: 84, column: 26
      labelAppliedE.setPresent();//sysj\labellerPlant.sysj line: 84, column: 40
      currsigs.addElement(labelAppliedE);
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

  public void thread8298(int [] tdone, int [] ends){
        S8118=1;
    if(bottleClamped.getprestatus()){//sysj\labellerPlant.sysj line: 82, column: 26
      bottleClampedE.setPresent();//sysj\labellerPlant.sysj line: 82, column: 41
      currsigs.addElement(bottleClampedE);
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

  public void thread8297(int [] tdone, int [] ends){
        S8110=1;
    if(labelPrinted.getprestatus()){//sysj\labellerPlant.sysj line: 80, column: 26
      labelPrintedE.setPresent();//sysj\labellerPlant.sysj line: 80, column: 40
      currsigs.addElement(labelPrintedE);
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

  public void thread8296(int [] tdone, int [] ends){
        S8102=1;
    S8096=0;
    if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
      bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
      currsigs.addElement(bottleAtLabellerE);
      S8096=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
    else {
      S8096=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

  public void thread8295(int [] tdone, int [] ends){
        S8094=1;
    S8064=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread8294(int [] tdone, int [] ends){
        S8062=1;
    S8032=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread8293(int [] tdone, int [] ends){
        S8030=1;
    S8000=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread8292(int [] tdone, int [] ends){
        S7998=1;
    System.out.println("LabellerPlant [Conveyor]: Presenting bottle at labeller...");//sysj\labellerPlant.sysj line: 26, column: 4
    S7972=0;
    bottleAtLabeller.setPresent();//sysj\labellerPlant.sysj line: 28, column: 5
    currsigs.addElement(bottleAtLabeller);
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
      switch(S8290){
        case 0 : 
          S8290=0;
          break RUN;
        
        case 1 : 
          S8290=2;
          S8290=2;
          new Thread(new LabellerGUI()).start();//sysj\labellerPlant.sysj line: 20, column: 3
          System.out.println("LabellerPlant: Subsystem initialized.");//sysj\labellerPlant.sysj line: 21, column: 2
          thread8292(tdone,ends);
          thread8293(tdone,ends);
          thread8294(tdone,ends);
          thread8295(tdone,ends);
          thread8296(tdone,ends);
          thread8297(tdone,ends);
          thread8298(tdone,ends);
          thread8299(tdone,ends);
          int biggest8300 = 0;
          if(ends[2]>=biggest8300){
            biggest8300=ends[2];
          }
          if(ends[3]>=biggest8300){
            biggest8300=ends[3];
          }
          if(ends[4]>=biggest8300){
            biggest8300=ends[4];
          }
          if(ends[5]>=biggest8300){
            biggest8300=ends[5];
          }
          if(ends[6]>=biggest8300){
            biggest8300=ends[6];
          }
          if(ends[7]>=biggest8300){
            biggest8300=ends[7];
          }
          if(ends[8]>=biggest8300){
            biggest8300=ends[8];
          }
          if(ends[9]>=biggest8300){
            biggest8300=ends[9];
          }
          if(biggest8300 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread8301(tdone,ends);
          thread8302(tdone,ends);
          thread8303(tdone,ends);
          thread8304(tdone,ends);
          thread8305(tdone,ends);
          thread8306(tdone,ends);
          thread8307(tdone,ends);
          thread8308(tdone,ends);
          int biggest8309 = 0;
          if(ends[2]>=biggest8309){
            biggest8309=ends[2];
          }
          if(ends[3]>=biggest8309){
            biggest8309=ends[3];
          }
          if(ends[4]>=biggest8309){
            biggest8309=ends[4];
          }
          if(ends[5]>=biggest8309){
            biggest8309=ends[5];
          }
          if(ends[6]>=biggest8309){
            biggest8309=ends[6];
          }
          if(ends[7]>=biggest8309){
            biggest8309=ends[7];
          }
          if(ends[8]>=biggest8309){
            biggest8309=ends[8];
          }
          if(ends[9]>=biggest8309){
            biggest8309=ends[9];
          }
          if(biggest8309 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest8309 == 0){
            S8290=0;
            active[1]=0;
            ends[1]=0;
            S8290=0;
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
          printLabel.gethook();
          clampBottle.gethook();
          applyLabel.gethook();
          liquidARatio.gethook();
          targetVolumeMl.gethook();
          df = true;
        }
        runClockDomain();
      }
      printLabel.setpreclear();
      clampBottle.setpreclear();
      applyLabel.setpreclear();
      liquidARatio.setpreclear();
      targetVolumeMl.setpreclear();
      labelPrinted.setpreclear();
      bottleClamped.setpreclear();
      labelApplied.setpreclear();
      bottleAtLabeller.setpreclear();
      bottleAtLabellerE.setpreclear();
      labelPrintedE.setpreclear();
      bottleClampedE.setpreclear();
      labelAppliedE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = printLabel.getStatus() ? printLabel.setprepresent() : printLabel.setpreclear();
      printLabel.setpreval(printLabel.getValue());
      printLabel.setClear();
      dummyint = clampBottle.getStatus() ? clampBottle.setprepresent() : clampBottle.setpreclear();
      clampBottle.setpreval(clampBottle.getValue());
      clampBottle.setClear();
      dummyint = applyLabel.getStatus() ? applyLabel.setprepresent() : applyLabel.setpreclear();
      applyLabel.setpreval(applyLabel.getValue());
      applyLabel.setClear();
      dummyint = liquidARatio.getStatus() ? liquidARatio.setprepresent() : liquidARatio.setpreclear();
      liquidARatio.setpreval(liquidARatio.getValue());
      liquidARatio.setClear();
      dummyint = targetVolumeMl.getStatus() ? targetVolumeMl.setprepresent() : targetVolumeMl.setpreclear();
      targetVolumeMl.setpreval(targetVolumeMl.getValue());
      targetVolumeMl.setClear();
      labelPrinted.sethook();
      labelPrinted.setClear();
      bottleClamped.sethook();
      bottleClamped.setClear();
      labelApplied.sethook();
      labelApplied.setClear();
      bottleAtLabeller.sethook();
      bottleAtLabeller.setClear();
      bottleAtLabellerE.sethook();
      bottleAtLabellerE.setClear();
      labelPrintedE.sethook();
      labelPrintedE.setClear();
      bottleClampedE.sethook();
      bottleClampedE.setClear();
      labelAppliedE.sethook();
      labelAppliedE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        printLabel.gethook();
        clampBottle.gethook();
        applyLabel.gethook();
        liquidARatio.gethook();
        targetVolumeMl.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
