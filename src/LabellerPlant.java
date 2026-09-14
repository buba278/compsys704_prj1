import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.LabellerGUI;//sysj\labellerPlant.sysj line: 1, column: 1
import run.ConveyorLabellerBridge;//sysj\labellerPlant.sysj line: 2, column: 1
import run.LabellerSorterBridge;//sysj\labellerPlant.sysj line: 3, column: 1

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
  public Signal bottleFromConveyor = new Signal("bottleFromConveyor", Signal.INPUT);
  public Signal sorterTakenAck = new Signal("sorterTakenAck", Signal.INPUT);
  public Signal labelPrinted = new Signal("labelPrinted", Signal.OUTPUT);
  public Signal bottleClamped = new Signal("bottleClamped", Signal.OUTPUT);
  public Signal labelApplied = new Signal("labelApplied", Signal.OUTPUT);
  public Signal bottleAtLabeller = new Signal("bottleAtLabeller", Signal.OUTPUT);
  public Signal bottleAtLabellerE = new Signal("bottleAtLabellerE", Signal.OUTPUT);
  public Signal labelPrintedE = new Signal("labelPrintedE", Signal.OUTPUT);
  public Signal bottleClampedE = new Signal("bottleClampedE", Signal.OUTPUT);
  public Signal labelAppliedE = new Signal("labelAppliedE", Signal.OUTPUT);
  private long tSort_thread_2;//sysj\labellerPlant.sysj line: 58, column: 5
  private int S17533 = 1;
  private int S17124 = 1;
  private int S16974 = 1;
  private int S17156 = 1;
  private int S17126 = 1;
  private int S17188 = 1;
  private int S17158 = 1;
  private int S17220 = 1;
  private int S17190 = 1;
  private int S17228 = 1;
  private int S17222 = 1;
  private int S17236 = 1;
  private int S17244 = 1;
  private int S17252 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread17551(int [] tdone, int [] ends){
        switch(S17252){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(labelApplied.getprestatus()){//sysj\labellerPlant.sysj line: 112, column: 26
          labelAppliedE.setPresent();//sysj\labellerPlant.sysj line: 112, column: 40
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

  public void thread17550(int [] tdone, int [] ends){
        switch(S17244){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(bottleClamped.getprestatus()){//sysj\labellerPlant.sysj line: 110, column: 26
          bottleClampedE.setPresent();//sysj\labellerPlant.sysj line: 110, column: 41
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

  public void thread17549(int [] tdone, int [] ends){
        switch(S17236){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(labelPrinted.getprestatus()){//sysj\labellerPlant.sysj line: 108, column: 26
          labelPrintedE.setPresent();//sysj\labellerPlant.sysj line: 108, column: 40
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

  public void thread17548(int [] tdone, int [] ends){
        switch(S17228){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S17222){
          case 0 : 
            S17222=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 106, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 106, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S17222=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S17222=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
            S17222=1;
            S17222=0;
            if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 106, column: 26
              bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 106, column: 44
              currsigs.addElement(bottleAtLabellerE);
              S17222=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              S17222=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread17547(int [] tdone, int [] ends){
        switch(S17220){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S17190){
          case 0 : 
            if(applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 97, column: 19
              System.out.println("LabellerPlant [Applicator]: Applying label...");//sysj\labellerPlant.sysj line: 98, column: 13
              S17190=1;
              labelApplied.setPresent();//sysj\labellerPlant.sysj line: 100, column: 17
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
            if(!applyLabel.getprestatus()){//sysj\labellerPlant.sysj line: 99, column: 19
              S17190=2;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              labelApplied.setPresent();//sysj\labellerPlant.sysj line: 100, column: 17
              currsigs.addElement(labelApplied);
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 2 : 
            S17190=2;
            S17190=0;
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread17546(int [] tdone, int [] ends){
        switch(S17188){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S17158){
          case 0 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 84, column: 10
              System.out.println("LabellerPlant [Clamp]: Clamping bottle...");//sysj\labellerPlant.sysj line: 85, column: 4
              S17158=1;
              bottleClamped.setPresent();//sysj\labellerPlant.sysj line: 87, column: 5
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
            if(!clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 86, column: 10
              System.out.println("LabellerPlant [Clamp]: Bottle released.");//sysj\labellerPlant.sysj line: 89, column: 4
              S17158=2;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              bottleClamped.setPresent();//sysj\labellerPlant.sysj line: 87, column: 5
              currsigs.addElement(bottleClamped);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 2 : 
            S17158=2;
            S17158=0;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread17545(int [] tdone, int [] ends){
        switch(S17156){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S17126){
          case 0 : 
            if(printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 72, column: 19
              System.out.println("LabellerPlant [Printer]: Printing label...");//sysj\labellerPlant.sysj line: 73, column: 13
              S17126=1;
              labelPrinted.setPresent();//sysj\labellerPlant.sysj line: 75, column: 17
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
            if(!printLabel.getprestatus()){//sysj\labellerPlant.sysj line: 74, column: 19
              S17126=2;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              labelPrinted.setPresent();//sysj\labellerPlant.sysj line: 75, column: 17
              currsigs.addElement(labelPrinted);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            S17126=2;
            S17126=0;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread17544(int [] tdone, int [] ends){
        switch(S17124){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S16974){
          case 0 : 
            if(bottleFromConveyor.getprestatus()){//sysj\labellerPlant.sysj line: 32, column: 10
              ConveyorLabellerBridge.setLabellerTakenAck(true);//sysj\labellerPlant.sysj line: 36, column: 4
              System.out.println("LabellerPlant [Conveyor]: Presenting bottle at labeller...");//sysj\labellerPlant.sysj line: 37, column: 4
              S16974=1;
              bottleAtLabeller.setPresent();//sysj\labellerPlant.sysj line: 39, column: 5
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
          
          case 1 : 
            if(clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 38, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle clamped, holding position...");//sysj\labellerPlant.sysj line: 41, column: 4
              S16974=2;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              bottleAtLabeller.setPresent();//sysj\labellerPlant.sysj line: 39, column: 5
              currsigs.addElement(bottleAtLabeller);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 2 : 
            if(!bottleFromConveyor.getprestatus()){//sysj\labellerPlant.sysj line: 44, column: 10
              ConveyorLabellerBridge.setLabellerTakenAck(false);//sysj\labellerPlant.sysj line: 45, column: 4
              S16974=3;
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
          
          case 3 : 
            if(!clampBottle.getprestatus()){//sysj\labellerPlant.sysj line: 51, column: 10
              System.out.println("LabellerPlant [Conveyor]: Bottle released, ready for next.");//sysj\labellerPlant.sysj line: 52, column: 4
              S16974=4;
              tSort_thread_2 = System.currentTimeMillis();//sysj\labellerPlant.sysj line: 58, column: 5
              if(System.currentTimeMillis() - tSort_thread_2 < 5000){//sysj\labellerPlant.sysj line: 59, column: 12
                LabellerSorterBridge.setBottleFromLabeller(true);//sysj\labellerPlant.sysj line: 60, column: 6
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\labellerPlant.sysj line: 59, column: 5
                LabellerSorterBridge.setBottleFromLabeller(false);//sysj\labellerPlant.sysj line: 64, column: 4
                S16974=5;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 4 : 
            if(sorterTakenAck.getprestatus()){//sysj\labellerPlant.sysj line: 57, column: 10
              LabellerSorterBridge.setBottleFromLabeller(false);//sysj\labellerPlant.sysj line: 64, column: 4
              S16974=5;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              if(System.currentTimeMillis() - tSort_thread_2 < 5000){//sysj\labellerPlant.sysj line: 59, column: 12
                LabellerSorterBridge.setBottleFromLabeller(true);//sysj\labellerPlant.sysj line: 60, column: 6
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\labellerPlant.sysj line: 59, column: 5
                LabellerSorterBridge.setBottleFromLabeller(false);//sysj\labellerPlant.sysj line: 64, column: 4
                S16974=5;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 5 : 
            if(!sorterTakenAck.getprestatus()){//sysj\labellerPlant.sysj line: 65, column: 10
              S16974=0;
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

  public void thread17542(int [] tdone, int [] ends){
        S17252=1;
    if(labelApplied.getprestatus()){//sysj\labellerPlant.sysj line: 112, column: 26
      labelAppliedE.setPresent();//sysj\labellerPlant.sysj line: 112, column: 40
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

  public void thread17541(int [] tdone, int [] ends){
        S17244=1;
    if(bottleClamped.getprestatus()){//sysj\labellerPlant.sysj line: 110, column: 26
      bottleClampedE.setPresent();//sysj\labellerPlant.sysj line: 110, column: 41
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

  public void thread17540(int [] tdone, int [] ends){
        S17236=1;
    if(labelPrinted.getprestatus()){//sysj\labellerPlant.sysj line: 108, column: 26
      labelPrintedE.setPresent();//sysj\labellerPlant.sysj line: 108, column: 40
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

  public void thread17539(int [] tdone, int [] ends){
        S17228=1;
    S17222=0;
    if(bottleAtLabeller.getprestatus()){//sysj\labellerPlant.sysj line: 106, column: 26
      bottleAtLabellerE.setPresent();//sysj\labellerPlant.sysj line: 106, column: 44
      currsigs.addElement(bottleAtLabellerE);
      S17222=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
    else {
      S17222=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

  public void thread17538(int [] tdone, int [] ends){
        S17220=1;
    S17190=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread17537(int [] tdone, int [] ends){
        S17188=1;
    S17158=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread17536(int [] tdone, int [] ends){
        S17156=1;
    S17126=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread17535(int [] tdone, int [] ends){
        S17124=1;
    S16974=0;
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
      switch(S17533){
        case 0 : 
          S17533=0;
          break RUN;
        
        case 1 : 
          S17533=2;
          S17533=2;
          new Thread(new LabellerGUI()).start();//sysj\labellerPlant.sysj line: 24, column: 3
          System.out.println("LabellerPlant: Subsystem initialized.");//sysj\labellerPlant.sysj line: 25, column: 2
          thread17535(tdone,ends);
          thread17536(tdone,ends);
          thread17537(tdone,ends);
          thread17538(tdone,ends);
          thread17539(tdone,ends);
          thread17540(tdone,ends);
          thread17541(tdone,ends);
          thread17542(tdone,ends);
          int biggest17543 = 0;
          if(ends[2]>=biggest17543){
            biggest17543=ends[2];
          }
          if(ends[3]>=biggest17543){
            biggest17543=ends[3];
          }
          if(ends[4]>=biggest17543){
            biggest17543=ends[4];
          }
          if(ends[5]>=biggest17543){
            biggest17543=ends[5];
          }
          if(ends[6]>=biggest17543){
            biggest17543=ends[6];
          }
          if(ends[7]>=biggest17543){
            biggest17543=ends[7];
          }
          if(ends[8]>=biggest17543){
            biggest17543=ends[8];
          }
          if(ends[9]>=biggest17543){
            biggest17543=ends[9];
          }
          if(biggest17543 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread17544(tdone,ends);
          thread17545(tdone,ends);
          thread17546(tdone,ends);
          thread17547(tdone,ends);
          thread17548(tdone,ends);
          thread17549(tdone,ends);
          thread17550(tdone,ends);
          thread17551(tdone,ends);
          int biggest17552 = 0;
          if(ends[2]>=biggest17552){
            biggest17552=ends[2];
          }
          if(ends[3]>=biggest17552){
            biggest17552=ends[3];
          }
          if(ends[4]>=biggest17552){
            biggest17552=ends[4];
          }
          if(ends[5]>=biggest17552){
            biggest17552=ends[5];
          }
          if(ends[6]>=biggest17552){
            biggest17552=ends[6];
          }
          if(ends[7]>=biggest17552){
            biggest17552=ends[7];
          }
          if(ends[8]>=biggest17552){
            biggest17552=ends[8];
          }
          if(ends[9]>=biggest17552){
            biggest17552=ends[9];
          }
          if(biggest17552 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest17552 == 0){
            S17533=0;
            active[1]=0;
            ends[1]=0;
            S17533=0;
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
          bottleFromConveyor.gethook();
          sorterTakenAck.gethook();
          df = true;
        }
        runClockDomain();
      }
      printLabel.setpreclear();
      clampBottle.setpreclear();
      applyLabel.setpreclear();
      liquidARatio.setpreclear();
      targetVolumeMl.setpreclear();
      bottleFromConveyor.setpreclear();
      sorterTakenAck.setpreclear();
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
      dummyint = bottleFromConveyor.getStatus() ? bottleFromConveyor.setprepresent() : bottleFromConveyor.setpreclear();
      bottleFromConveyor.setpreval(bottleFromConveyor.getValue());
      bottleFromConveyor.setClear();
      dummyint = sorterTakenAck.getStatus() ? sorterTakenAck.setprepresent() : sorterTakenAck.setpreclear();
      sorterTakenAck.setpreval(sorterTakenAck.getValue());
      sorterTakenAck.setClear();
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
        bottleFromConveyor.gethook();
        sorterTakenAck.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
