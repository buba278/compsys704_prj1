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
  public Signal labelPrinted = new Signal("labelPrinted", Signal.OUTPUT);
  public Signal bottleClamped = new Signal("bottleClamped", Signal.OUTPUT);
  public Signal labelApplied = new Signal("labelApplied", Signal.OUTPUT);
  public Signal bottleAtLabeller = new Signal("bottleAtLabeller", Signal.OUTPUT);
  public Signal bottleAtLabellerE = new Signal("bottleAtLabellerE", Signal.OUTPUT);
  public Signal labelPrintedE = new Signal("labelPrintedE", Signal.OUTPUT);
  public Signal bottleClampedE = new Signal("bottleClampedE", Signal.OUTPUT);
  public Signal labelAppliedE = new Signal("labelAppliedE", Signal.OUTPUT);
  private int S10084 = 1;
  private int S9792 = 1;
  private int S9766 = 1;
  private int S9824 = 1;
  private int S9794 = 1;
  private int S9856 = 1;
  private int S9826 = 1;
  private int S9888 = 1;
  private int S9858 = 1;
  private int S9896 = 1;
  private int S9890 = 1;
  private int S9904 = 1;
  private int S9912 = 1;
  private int S9920 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread10102(int [] tdone, int [] ends){
        switch(S9920){
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

  public void thread10101(int [] tdone, int [] ends){
        switch(S9912){
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

  public void thread10100(int [] tdone, int [] ends){
        switch(S9904){
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

  public void thread10099(int [] tdone, int [] ends){
        switch(S9896){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S9890){
          case 0 : 
            S9890=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S9890=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S9890=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
            S9890=1;
            S9890=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S9890=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S9890=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread10098(int [] tdone, int [] ends){
        switch(S9888){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S9858){
          case 0 : 
            if(applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 69, column: 19
              System.out.println("LabellerPlant [Applicator]: Applying label...");//sysj\labellerPlant.sysj line: 70, column: 13
              S9858=1;
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
              S9858=2;
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
            S9858=2;
            S9858=0;
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread10097(int [] tdone, int [] ends){
        switch(S9856){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S9826){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 56, column: 10
              System.out.println("LabellerPlant [Clamp]: Clamping bottle...");//sysj\labellerPlant.sysj line: 57, column: 4
              S9826=1;
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
              S9826=2;
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
            S9826=2;
            S9826=0;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread10096(int [] tdone, int [] ends){
        switch(S9824){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S9794){
          case 0 : 
            if(printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 44, column: 19
              System.out.println("LabellerPlant [Printer]: Printing label...");//sysj\labellerPlant.sysj line: 45, column: 13
              S9794=1;
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
              S9794=2;
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
            S9794=2;
            S9794=0;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread10095(int [] tdone, int [] ends){
        switch(S9792){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S9766){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 27, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle clamped, holding position...");//sysj\labellerPlant.sysj line: 30, column: 4
              S9766=1;
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
              S9766=0;
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

  public void thread10093(int [] tdone, int [] ends){
        S9920=1;
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

  public void thread10092(int [] tdone, int [] ends){
        S9912=1;
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

  public void thread10091(int [] tdone, int [] ends){
        S9904=1;
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

  public void thread10090(int [] tdone, int [] ends){
        S9896=1;
    S9890=0;
    if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
      bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
      currsigs.addElement(bottleAtLabellerE);
      S9890=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
    else {
      S9890=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

  public void thread10089(int [] tdone, int [] ends){
        S9888=1;
    S9858=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread10088(int [] tdone, int [] ends){
        S9856=1;
    S9826=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread10087(int [] tdone, int [] ends){
        S9824=1;
    S9794=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread10086(int [] tdone, int [] ends){
        S9792=1;
    System.out.println("LabellerPlant [Conveyor]: Presenting bottle at labeller...");//sysj\labellerPlant.sysj line: 26, column: 4
    S9766=0;
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
      switch(S10084){
        case 0 : 
          S10084=0;
          break RUN;
        
        case 1 : 
          S10084=2;
          S10084=2;
          new Thread(new LabellerGUI()).start();//sysj\labellerPlant.sysj line: 20, column: 3
          System.out.println("LabellerPlant: Subsystem initialized.");//sysj\labellerPlant.sysj line: 21, column: 2
          thread10086(tdone,ends);
          thread10087(tdone,ends);
          thread10088(tdone,ends);
          thread10089(tdone,ends);
          thread10090(tdone,ends);
          thread10091(tdone,ends);
          thread10092(tdone,ends);
          thread10093(tdone,ends);
          int biggest10094 = 0;
          if(ends[2]>=biggest10094){
            biggest10094=ends[2];
          }
          if(ends[3]>=biggest10094){
            biggest10094=ends[3];
          }
          if(ends[4]>=biggest10094){
            biggest10094=ends[4];
          }
          if(ends[5]>=biggest10094){
            biggest10094=ends[5];
          }
          if(ends[6]>=biggest10094){
            biggest10094=ends[6];
          }
          if(ends[7]>=biggest10094){
            biggest10094=ends[7];
          }
          if(ends[8]>=biggest10094){
            biggest10094=ends[8];
          }
          if(ends[9]>=biggest10094){
            biggest10094=ends[9];
          }
          if(biggest10094 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread10095(tdone,ends);
          thread10096(tdone,ends);
          thread10097(tdone,ends);
          thread10098(tdone,ends);
          thread10099(tdone,ends);
          thread10100(tdone,ends);
          thread10101(tdone,ends);
          thread10102(tdone,ends);
          int biggest10103 = 0;
          if(ends[2]>=biggest10103){
            biggest10103=ends[2];
          }
          if(ends[3]>=biggest10103){
            biggest10103=ends[3];
          }
          if(ends[4]>=biggest10103){
            biggest10103=ends[4];
          }
          if(ends[5]>=biggest10103){
            biggest10103=ends[5];
          }
          if(ends[6]>=biggest10103){
            biggest10103=ends[6];
          }
          if(ends[7]>=biggest10103){
            biggest10103=ends[7];
          }
          if(ends[8]>=biggest10103){
            biggest10103=ends[8];
          }
          if(ends[9]>=biggest10103){
            biggest10103=ends[9];
          }
          if(biggest10103 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest10103 == 0){
            S10084=0;
            active[1]=0;
            ends[1]=0;
            S10084=0;
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
          df = true;
        }
        runClockDomain();
      }
      printLabel.setpreclear();
      clampBottle.setpreclear();
      applyLabel.setpreclear();
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
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
