import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

public class FillerController extends ClockDomain{
  public FillerController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtPos2 = new Signal("bottleAtPos2", Signal.INPUT);
  public Signal liquidARatio = new Signal("liquidARatio", Signal.INPUT);
  public Signal targetVolumeMl = new Signal("targetVolumeMl", Signal.INPUT);
  public Signal levelAtTarget = new Signal("levelAtTarget", Signal.INPUT);
  public Signal doseTargetMl = new Signal("doseTargetMl", Signal.OUTPUT);
  public Signal valve1Open = new Signal("valve1Open", Signal.OUTPUT);
  public Signal valve2Open = new Signal("valve2Open", Signal.OUTPUT);
  public Signal fillDone = new Signal("fillDone", Signal.OUTPUT);
  public Signal fillDoneE = new Signal("fillDoneE", Signal.OUTPUT);
  private int aTarget_thread_2;//sysj\fillerController.sysj line: 15, column: 4
  private int bTarget_thread_2;//sysj\fillerController.sysj line: 16, column: 4
  private int S2290 = 1;
  private int S2280 = 1;
  private int S2220 = 1;
  private int S2288 = 1;
  
  private int[] ends = new int[4];
  private int[] tdone = new int[4];
  
  public void thread2296(int [] tdone, int [] ends){
        switch(S2288){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 38, column: 14
          fillDoneE.setPresent();//sysj\fillerController.sysj line: 39, column: 6
          currsigs.addElement(fillDoneE);
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
  }

  public void thread2295(int [] tdone, int [] ends){
        switch(S2280){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S2220){
          case 0 : 
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 13, column: 11
              aTarget_thread_2 = ((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())) * (Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())) / 100);//sysj\fillerController.sysj line: 15, column: 4
              bTarget_thread_2 = (Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval()));//sysj\fillerController.sysj line: 16, column: 4
              System.out.printf("phase 1 start: aTarget=%d bTarget=%d%n", aTarget_thread_2, bTarget_thread_2);//sysj\fillerController.sysj line: 18, column: 4
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 19, column: 4
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 19, column: 4
              S2220=1;
              valve1Open.setPresent();//sysj\fillerController.sysj line: 21, column: 5
              currsigs.addElement(valve1Open);
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
            if(levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 20, column: 11
              System.out.printf("phase 1 done, dosing to %d%n", bTarget_thread_2);//sysj\fillerController.sysj line: 23, column: 4
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 24, column: 4
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 24, column: 4
              S2220=2;
              valve2Open.setPresent();//sysj\fillerController.sysj line: 26, column: 5
              currsigs.addElement(valve2Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              valve1Open.setPresent();//sysj\fillerController.sysj line: 21, column: 5
              currsigs.addElement(valve1Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 2 : 
            if(levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 25, column: 11
              System.out.printf("phase 2 done%n");//sysj\fillerController.sysj line: 28, column: 4
              fillDone.setPresent();//sysj\fillerController.sysj line: 30, column: 4
              currsigs.addElement(fillDone);
              S2220=3;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              valve2Open.setPresent();//sysj\fillerController.sysj line: 26, column: 5
              currsigs.addElement(valve2Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 3 : 
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 31, column: 11
              S2220=0;
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

  public void thread2293(int [] tdone, int [] ends){
        S2288=1;
    if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 38, column: 14
      fillDoneE.setPresent();//sysj\fillerController.sysj line: 39, column: 6
      currsigs.addElement(fillDoneE);
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
    else {
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
  }

  public void thread2292(int [] tdone, int [] ends){
        S2280=1;
    S2220=0;
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
      switch(S2290){
        case 0 : 
          S2290=0;
          break RUN;
        
        case 1 : 
          S2290=2;
          S2290=2;
          thread2292(tdone,ends);
          thread2293(tdone,ends);
          int biggest2294 = 0;
          if(ends[2]>=biggest2294){
            biggest2294=ends[2];
          }
          if(ends[3]>=biggest2294){
            biggest2294=ends[3];
          }
          if(biggest2294 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread2295(tdone,ends);
          thread2296(tdone,ends);
          int biggest2297 = 0;
          if(ends[2]>=biggest2297){
            biggest2297=ends[2];
          }
          if(ends[3]>=biggest2297){
            biggest2297=ends[3];
          }
          if(biggest2297 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest2297 == 0){
            S2290=0;
            active[1]=0;
            ends[1]=0;
            S2290=0;
            break RUN;
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
          bottleAtPos2.gethook();
          liquidARatio.gethook();
          targetVolumeMl.gethook();
          levelAtTarget.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtPos2.setpreclear();
      liquidARatio.setpreclear();
      targetVolumeMl.setpreclear();
      levelAtTarget.setpreclear();
      doseTargetMl.setpreclear();
      valve1Open.setpreclear();
      valve2Open.setpreclear();
      fillDone.setpreclear();
      fillDoneE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtPos2.getStatus() ? bottleAtPos2.setprepresent() : bottleAtPos2.setpreclear();
      bottleAtPos2.setpreval(bottleAtPos2.getValue());
      bottleAtPos2.setClear();
      dummyint = liquidARatio.getStatus() ? liquidARatio.setprepresent() : liquidARatio.setpreclear();
      liquidARatio.setpreval(liquidARatio.getValue());
      liquidARatio.setClear();
      dummyint = targetVolumeMl.getStatus() ? targetVolumeMl.setprepresent() : targetVolumeMl.setpreclear();
      targetVolumeMl.setpreval(targetVolumeMl.getValue());
      targetVolumeMl.setClear();
      dummyint = levelAtTarget.getStatus() ? levelAtTarget.setprepresent() : levelAtTarget.setpreclear();
      levelAtTarget.setpreval(levelAtTarget.getValue());
      levelAtTarget.setClear();
      doseTargetMl.sethook();
      doseTargetMl.setClear();
      valve1Open.sethook();
      valve1Open.setClear();
      valve2Open.sethook();
      valve2Open.setClear();
      fillDone.sethook();
      fillDone.setClear();
      fillDoneE.sethook();
      fillDoneE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtPos2.gethook();
        liquidARatio.gethook();
        targetVolumeMl.gethook();
        levelAtTarget.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
