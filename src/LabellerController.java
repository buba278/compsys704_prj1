import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

public class LabellerController extends ClockDomain{
  public LabellerController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtLabeller = new Signal("bottleAtLabeller", Signal.INPUT);
  public Signal labelPrinted = new Signal("labelPrinted", Signal.INPUT);
  public Signal bottleClamped = new Signal("bottleClamped", Signal.INPUT);
  public Signal labelApplied = new Signal("labelApplied", Signal.INPUT);
  public Signal printLabel = new Signal("printLabel", Signal.OUTPUT);
  public Signal clampBottle = new Signal("clampBottle", Signal.OUTPUT);
  public Signal applyLabel = new Signal("applyLabel", Signal.OUTPUT);
  private int S7955 = 1;
  private int S7628 = 1;
  private int S7641 = 1;
  private int S7650 = 1;
  private int S7643 = 1;
  
  private int[] ends = new int[4];
  private int[] tdone = new int[4];
  
  public void thread7961(int [] tdone, int [] ends){
        switch(S7650){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S7643){
          case 0 : 
            if(bottleClamped.getprestatus()){//sysj\labellerController.sysj line: 30, column: 12
              System.out.println("LabelController: Bottle clamped. Applying label...");//sysj\labellerController.sysj line: 31, column: 14
              S7643=1;
              applyLabel.setPresent();//sysj\labellerController.sysj line: 32, column: 14
              currsigs.addElement(applyLabel);
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
            applyLabel.setPresent();//sysj\labellerController.sysj line: 32, column: 14
            currsigs.addElement(applyLabel);
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread7960(int [] tdone, int [] ends){
        switch(S7641){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        clampBottle.setPresent();//sysj\labellerController.sysj line: 26, column: 5
        currsigs.addElement(clampBottle);
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
        break;
      
    }
  }

  public void thread7958(int [] tdone, int [] ends){
        S7650=1;
    S7643=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread7957(int [] tdone, int [] ends){
        S7641=1;
    clampBottle.setPresent();//sysj\labellerController.sysj line: 26, column: 5
    currsigs.addElement(clampBottle);
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
      switch(S7955){
        case 0 : 
          S7955=0;
          break RUN;
        
        case 1 : 
          S7955=2;
          S7955=2;
          System.out.println("LabelController: Initialized and running.");//sysj\labellerController.sysj line: 14, column: 2
          System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 16, column: 3
          S7628=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S7628){
            case 0 : 
              if(bottleAtLabeller.getprestatus()){//sysj\labellerController.sysj line: 17, column: 10
                System.out.println("LabelController: Bottle detected. Sending print command...");//sysj\labellerController.sysj line: 18, column: 3
                S7628=1;
                printLabel.setPresent();//sysj\labellerController.sysj line: 20, column: 13
                currsigs.addElement(printLabel);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 1 : 
              if(labelPrinted.getprestatus()){//sysj\labellerController.sysj line: 19, column: 15
                System.out.println("LabelController: Label printed. Clamping bottle...");//sysj\labellerController.sysj line: 22, column: 9
                S7628=2;
                thread7957(tdone,ends);
                thread7958(tdone,ends);
                int biggest7959 = 0;
                if(ends[2]>=biggest7959){
                  biggest7959=ends[2];
                }
                if(ends[3]>=biggest7959){
                  biggest7959=ends[3];
                }
                if(biggest7959 == 1){
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
              }
              else {
                printLabel.setPresent();//sysj\labellerController.sysj line: 20, column: 13
                currsigs.addElement(printLabel);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 2 : 
              if(labelApplied.getprestatus()){//sysj\labellerController.sysj line: 24, column: 9
                System.out.println("LabelController: Label applied. Releasing clamp. Cycle complete.");//sysj\labellerController.sysj line: 35, column: 3
                System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 16, column: 3
                S7628=0;
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                thread7960(tdone,ends);
                thread7961(tdone,ends);
                int biggest7962 = 0;
                if(ends[2]>=biggest7962){
                  biggest7962=ends[2];
                }
                if(ends[3]>=biggest7962){
                  biggest7962=ends[3];
                }
                if(biggest7962 == 1){
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                //FINXME code
                if(biggest7962 == 0){
                  System.out.println("LabelController: Label applied. Releasing clamp. Cycle complete.");//sysj\labellerController.sysj line: 35, column: 3
                  System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 16, column: 3
                  S7628=0;
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
              }
            
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0};
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
          bottleAtLabeller.gethook();
          labelPrinted.gethook();
          bottleClamped.gethook();
          labelApplied.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtLabeller.setpreclear();
      labelPrinted.setpreclear();
      bottleClamped.setpreclear();
      labelApplied.setpreclear();
      printLabel.setpreclear();
      clampBottle.setpreclear();
      applyLabel.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtLabeller.getStatus() ? bottleAtLabeller.setprepresent() : bottleAtLabeller.setpreclear();
      bottleAtLabeller.setpreval(bottleAtLabeller.getValue());
      bottleAtLabeller.setClear();
      dummyint = labelPrinted.getStatus() ? labelPrinted.setprepresent() : labelPrinted.setpreclear();
      labelPrinted.setpreval(labelPrinted.getValue());
      labelPrinted.setClear();
      dummyint = bottleClamped.getStatus() ? bottleClamped.setprepresent() : bottleClamped.setpreclear();
      bottleClamped.setpreval(bottleClamped.getValue());
      bottleClamped.setClear();
      dummyint = labelApplied.getStatus() ? labelApplied.setprepresent() : labelApplied.setpreclear();
      labelApplied.setpreval(labelApplied.getValue());
      labelApplied.setClear();
      printLabel.sethook();
      printLabel.setClear();
      clampBottle.sethook();
      clampBottle.setClear();
      applyLabel.sethook();
      applyLabel.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtLabeller.gethook();
        labelPrinted.gethook();
        bottleClamped.gethook();
        labelApplied.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
