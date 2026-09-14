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
<<<<<<< HEAD
  public Signal liquidARatio = new Signal("liquidARatio", Signal.INPUT);
  public Signal targetVolumeMl = new Signal("targetVolumeMl", Signal.INPUT);
=======
>>>>>>> main
  public Signal labelPrinted = new Signal("labelPrinted", Signal.OUTPUT);
  public Signal bottleClamped = new Signal("bottleClamped", Signal.OUTPUT);
  public Signal labelApplied = new Signal("labelApplied", Signal.OUTPUT);
  public Signal bottleAtLabeller = new Signal("bottleAtLabeller", Signal.OUTPUT);
  public Signal bottleAtLabellerE = new Signal("bottleAtLabellerE", Signal.OUTPUT);
  public Signal labelPrintedE = new Signal("labelPrintedE", Signal.OUTPUT);
  public Signal bottleClampedE = new Signal("bottleClampedE", Signal.OUTPUT);
  public Signal labelAppliedE = new Signal("labelAppliedE", Signal.OUTPUT);
<<<<<<< HEAD
  private int S10336 = 1;
  private int S10044 = 1;
  private int S10018 = 1;
  private int S10076 = 1;
  private int S10046 = 1;
  private int S10108 = 1;
  private int S10078 = 1;
  private int S10140 = 1;
  private int S10110 = 1;
  private int S10148 = 1;
  private int S10142 = 1;
  private int S10156 = 1;
  private int S10164 = 1;
  private int S10172 = 1;
=======
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
>>>>>>> main
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
<<<<<<< HEAD
  public void thread10354(int [] tdone, int [] ends){
        switch(S10172){
=======
  public void thread10102(int [] tdone, int [] ends){
        switch(S9920){
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10353(int [] tdone, int [] ends){
        switch(S10164){
=======
  public void thread10101(int [] tdone, int [] ends){
        switch(S9912){
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10352(int [] tdone, int [] ends){
        switch(S10156){
=======
  public void thread10100(int [] tdone, int [] ends){
        switch(S9904){
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10351(int [] tdone, int [] ends){
        switch(S10148){
=======
  public void thread10099(int [] tdone, int [] ends){
        switch(S9896){
>>>>>>> main
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
<<<<<<< HEAD
        switch(S10142){
          case 0 : 
            S10142=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S10142=1;
=======
        switch(S9890){
          case 0 : 
            S9890=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S9890=1;
>>>>>>> main
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
<<<<<<< HEAD
              S10142=1;
=======
              S9890=1;
>>>>>>> main
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
<<<<<<< HEAD
            S10142=1;
            S10142=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S10142=1;
=======
            S9890=1;
            S9890=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S9890=1;
>>>>>>> main
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
<<<<<<< HEAD
              S10142=1;
=======
              S9890=1;
>>>>>>> main
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
        }
        break;
      
    }
  }

<<<<<<< HEAD
  public void thread10350(int [] tdone, int [] ends){
        switch(S10140){
=======
  public void thread10098(int [] tdone, int [] ends){
        switch(S9888){
>>>>>>> main
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
<<<<<<< HEAD
        switch(S10110){
          case 0 : 
            if(applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 69, column: 19
              System.out.println("LabellerPlant [Applicator]: Applying label...");//sysj\labellerPlant.sysj line: 70, column: 13
              S10110=1;
=======
        switch(S9858){
          case 0 : 
            if(applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 69, column: 19
              System.out.println("LabellerPlant [Applicator]: Applying label...");//sysj\labellerPlant.sysj line: 70, column: 13
              S9858=1;
>>>>>>> main
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
<<<<<<< HEAD
              S10110=2;
=======
              S9858=2;
>>>>>>> main
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
<<<<<<< HEAD
            S10110=2;
            S10110=0;
=======
            S9858=2;
            S9858=0;
>>>>>>> main
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

<<<<<<< HEAD
  public void thread10349(int [] tdone, int [] ends){
        switch(S10108){
=======
  public void thread10097(int [] tdone, int [] ends){
        switch(S9856){
>>>>>>> main
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
<<<<<<< HEAD
        switch(S10078){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 56, column: 10
              System.out.println("LabellerPlant [Clamp]: Clamping bottle...");//sysj\labellerPlant.sysj line: 57, column: 4
              S10078=1;
=======
        switch(S9826){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 56, column: 10
              System.out.println("LabellerPlant [Clamp]: Clamping bottle...");//sysj\labellerPlant.sysj line: 57, column: 4
              S9826=1;
>>>>>>> main
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
<<<<<<< HEAD
              S10078=2;
=======
              S9826=2;
>>>>>>> main
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
<<<<<<< HEAD
            S10078=2;
            S10078=0;
=======
            S9826=2;
            S9826=0;
>>>>>>> main
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

<<<<<<< HEAD
  public void thread10348(int [] tdone, int [] ends){
        switch(S10076){
=======
  public void thread10096(int [] tdone, int [] ends){
        switch(S9824){
>>>>>>> main
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
<<<<<<< HEAD
        switch(S10046){
          case 0 : 
            if(printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 44, column: 19
              System.out.println("LabellerPlant [Printer]: Printing label...");//sysj\labellerPlant.sysj line: 45, column: 13
              S10046=1;
=======
        switch(S9794){
          case 0 : 
            if(printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 44, column: 19
              System.out.println("LabellerPlant [Printer]: Printing label...");//sysj\labellerPlant.sysj line: 45, column: 13
              S9794=1;
>>>>>>> main
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
<<<<<<< HEAD
              S10046=2;
=======
              S9794=2;
>>>>>>> main
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
<<<<<<< HEAD
            S10046=2;
            S10046=0;
=======
            S9794=2;
            S9794=0;
>>>>>>> main
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

<<<<<<< HEAD
  public void thread10347(int [] tdone, int [] ends){
        switch(S10044){
=======
  public void thread10095(int [] tdone, int [] ends){
        switch(S9792){
>>>>>>> main
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
<<<<<<< HEAD
        switch(S10018){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 27, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle clamped, holding position...");//sysj\labellerPlant.sysj line: 30, column: 4
              S10018=1;
=======
        switch(S9766){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 27, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle clamped, holding position...");//sysj\labellerPlant.sysj line: 30, column: 4
              S9766=1;
>>>>>>> main
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
<<<<<<< HEAD
              S10018=0;
=======
              S9766=0;
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10345(int [] tdone, int [] ends){
        S10172=1;
=======
  public void thread10093(int [] tdone, int [] ends){
        S9920=1;
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10344(int [] tdone, int [] ends){
        S10164=1;
=======
  public void thread10092(int [] tdone, int [] ends){
        S9912=1;
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10343(int [] tdone, int [] ends){
        S10156=1;
=======
  public void thread10091(int [] tdone, int [] ends){
        S9904=1;
>>>>>>> main
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

<<<<<<< HEAD
  public void thread10342(int [] tdone, int [] ends){
        S10148=1;
    S10142=0;
    if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
      bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
      currsigs.addElement(bottleAtLabellerE);
      S10142=1;
=======
  public void thread10090(int [] tdone, int [] ends){
        S9896=1;
    S9890=0;
    if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 78, column: 26
      bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 78, column: 44
      currsigs.addElement(bottleAtLabellerE);
      S9890=1;
>>>>>>> main
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
    else {
<<<<<<< HEAD
      S10142=1;
=======
      S9890=1;
>>>>>>> main
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

<<<<<<< HEAD
  public void thread10341(int [] tdone, int [] ends){
        S10140=1;
    S10110=0;
=======
  public void thread10089(int [] tdone, int [] ends){
        S9888=1;
    S9858=0;
>>>>>>> main
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

<<<<<<< HEAD
  public void thread10340(int [] tdone, int [] ends){
        S10108=1;
    S10078=0;
=======
  public void thread10088(int [] tdone, int [] ends){
        S9856=1;
    S9826=0;
>>>>>>> main
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

<<<<<<< HEAD
  public void thread10339(int [] tdone, int [] ends){
        S10076=1;
    S10046=0;
=======
  public void thread10087(int [] tdone, int [] ends){
        S9824=1;
    S9794=0;
>>>>>>> main
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

<<<<<<< HEAD
  public void thread10338(int [] tdone, int [] ends){
        S10044=1;
    System.out.println("LabellerPlant [Conveyor]: Presenting bottle at labeller...");//sysj\labellerPlant.sysj line: 26, column: 4
    S10018=0;
=======
  public void thread10086(int [] tdone, int [] ends){
        S9792=1;
    System.out.println("LabellerPlant [Conveyor]: Presenting bottle at labeller...");//sysj\labellerPlant.sysj line: 26, column: 4
    S9766=0;
>>>>>>> main
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
<<<<<<< HEAD
      switch(S10336){
        case 0 : 
          S10336=0;
          break RUN;
        
        case 1 : 
          S10336=2;
          S10336=2;
          new Thread(new LabellerGUI()).start();//sysj\labellerPlant.sysj line: 20, column: 3
          System.out.println("LabellerPlant: Subsystem initialized.");//sysj\labellerPlant.sysj line: 21, column: 2
          thread10338(tdone,ends);
          thread10339(tdone,ends);
          thread10340(tdone,ends);
          thread10341(tdone,ends);
          thread10342(tdone,ends);
          thread10343(tdone,ends);
          thread10344(tdone,ends);
          thread10345(tdone,ends);
          int biggest10346 = 0;
          if(ends[2]>=biggest10346){
            biggest10346=ends[2];
          }
          if(ends[3]>=biggest10346){
            biggest10346=ends[3];
          }
          if(ends[4]>=biggest10346){
            biggest10346=ends[4];
          }
          if(ends[5]>=biggest10346){
            biggest10346=ends[5];
          }
          if(ends[6]>=biggest10346){
            biggest10346=ends[6];
          }
          if(ends[7]>=biggest10346){
            biggest10346=ends[7];
          }
          if(ends[8]>=biggest10346){
            biggest10346=ends[8];
          }
          if(ends[9]>=biggest10346){
            biggest10346=ends[9];
          }
          if(biggest10346 == 1){
=======
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
>>>>>>> main
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
<<<<<<< HEAD
          thread10347(tdone,ends);
          thread10348(tdone,ends);
          thread10349(tdone,ends);
          thread10350(tdone,ends);
          thread10351(tdone,ends);
          thread10352(tdone,ends);
          thread10353(tdone,ends);
          thread10354(tdone,ends);
          int biggest10355 = 0;
          if(ends[2]>=biggest10355){
            biggest10355=ends[2];
          }
          if(ends[3]>=biggest10355){
            biggest10355=ends[3];
          }
          if(ends[4]>=biggest10355){
            biggest10355=ends[4];
          }
          if(ends[5]>=biggest10355){
            biggest10355=ends[5];
          }
          if(ends[6]>=biggest10355){
            biggest10355=ends[6];
          }
          if(ends[7]>=biggest10355){
            biggest10355=ends[7];
          }
          if(ends[8]>=biggest10355){
            biggest10355=ends[8];
          }
          if(ends[9]>=biggest10355){
            biggest10355=ends[9];
          }
          if(biggest10355 == 1){
=======
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
>>>>>>> main
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
<<<<<<< HEAD
          if(biggest10355 == 0){
            S10336=0;
            active[1]=0;
            ends[1]=0;
            S10336=0;
=======
          if(biggest10103 == 0){
            S10084=0;
            active[1]=0;
            ends[1]=0;
            S10084=0;
>>>>>>> main
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
<<<<<<< HEAD
          liquidARatio.gethook();
          targetVolumeMl.gethook();
=======
>>>>>>> main
          df = true;
        }
        runClockDomain();
      }
      printLabel.setpreclear();
      clampBottle.setpreclear();
      applyLabel.setpreclear();
<<<<<<< HEAD
      liquidARatio.setpreclear();
      targetVolumeMl.setpreclear();
=======
>>>>>>> main
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
<<<<<<< HEAD
      dummyint = liquidARatio.getStatus() ? liquidARatio.setprepresent() : liquidARatio.setpreclear();
      liquidARatio.setpreval(liquidARatio.getValue());
      liquidARatio.setClear();
      dummyint = targetVolumeMl.getStatus() ? targetVolumeMl.setprepresent() : targetVolumeMl.setpreclear();
      targetVolumeMl.setpreval(targetVolumeMl.getValue());
      targetVolumeMl.setClear();
=======
>>>>>>> main
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
<<<<<<< HEAD
        liquidARatio.gethook();
        targetVolumeMl.gethook();
=======
>>>>>>> main
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
