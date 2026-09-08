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
  public Signal totalVolumeMlE = new Signal("totalVolumeMlE", Signal.OUTPUT);
  public Signal fillDoneCoordE = new Signal("fillDoneCoordE", Signal.OUTPUT);
  public Signal fillReady = new Signal("fillReady", Signal.OUTPUT);
  private int aTarget_thread_2;//sysj\fillerController.sysj line: 19, column: 4
  private int bTarget_thread_2;//sysj\fillerController.sysj line: 20, column: 4
  private long tFillReady_thread_2;//sysj\fillerController.sysj line: 44, column: 5
  private long tFillDoneCoord_thread_4;//sysj\fillerController.sysj line: 69, column: 5
  private int S2605 = 1;
  private int S2397 = 1;
  private int S2137 = 1;
  private int S2405 = 1;
  private int S2399 = 1;
  private int S2577 = 1;
  private int S2407 = 1;
  private int S2603 = 1;
  
  private int[] ends = new int[6];
  private int[] tdone = new int[6];
  
  public void thread2615(int [] tdone, int [] ends){
        switch(S2603){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        if(liquidARatio.getprestatus()){//sysj\fillerController.sysj line: 81, column: 13
          run.FillerRecipe.setRatio((Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())));//sysj\fillerController.sysj line: 81, column: 31
          if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 82, column: 13
            run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 83, column: 5
            totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 84, column: 5
            currsigs.addElement(totalVolumeMlE);
            totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 84, column: 5
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
        else {
          if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 82, column: 13
            run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 83, column: 5
            totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 84, column: 5
            currsigs.addElement(totalVolumeMlE);
            totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 84, column: 5
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
        break;
      
    }
  }

  public void thread2614(int [] tdone, int [] ends){
        switch(S2577){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S2407){
          case 0 : 
            if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 66, column: 11
              System.out.printf("[FC] fillDoneCoordE → coordinator (holding)%n");//sysj\fillerController.sysj line: 67, column: 4
              S2407=1;
              tFillDoneCoord_thread_4 = System.currentTimeMillis();//sysj\fillerController.sysj line: 69, column: 5
              if(System.currentTimeMillis() - tFillDoneCoord_thread_4 < 200){//sysj\fillerController.sysj line: 70, column: 12
                fillDoneCoordE.setPresent();//sysj\fillerController.sysj line: 71, column: 6
                currsigs.addElement(fillDoneCoordE);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\fillerController.sysj line: 70, column: 5
                S2407=0;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            else {
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 68, column: 11
              S2407=0;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(System.currentTimeMillis() - tFillDoneCoord_thread_4 < 200){//sysj\fillerController.sysj line: 70, column: 12
                fillDoneCoordE.setPresent();//sysj\fillerController.sysj line: 71, column: 6
                currsigs.addElement(fillDoneCoordE);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\fillerController.sysj line: 70, column: 5
                S2407=0;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread2613(int [] tdone, int [] ends){
        switch(S2405){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S2399){
          case 0 : 
            S2399=0;
            if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 56, column: 14
              fillDoneE.setPresent();//sysj\fillerController.sysj line: 57, column: 6
              currsigs.addElement(fillDoneE);
              S2399=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S2399=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            S2399=1;
            S2399=0;
            if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 56, column: 14
              fillDoneE.setPresent();//sysj\fillerController.sysj line: 57, column: 6
              currsigs.addElement(fillDoneE);
              S2399=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              S2399=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread2612(int [] tdone, int [] ends){
        switch(S2397){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S2137){
          case 0 : 
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 16, column: 11
              aTarget_thread_2 = run.FillerRecipe.getVolume() * run.FillerRecipe.getRatio() / 100;//sysj\fillerController.sysj line: 19, column: 4
              bTarget_thread_2 = run.FillerRecipe.getVolume();//sysj\fillerController.sysj line: 20, column: 4
              System.out.printf("phase 1 start: aTarget=%d bTarget=%d%n", aTarget_thread_2, bTarget_thread_2);//sysj\fillerController.sysj line: 22, column: 4
              S2137=1;
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 25, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 25, column: 6
              valve1Open.setPresent();//sysj\fillerController.sysj line: 26, column: 6
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
            if(levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 23, column: 11
              System.out.printf("phase 1 done, dosing to %d%n", bTarget_thread_2);//sysj\fillerController.sysj line: 30, column: 4
              S2137=2;
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 33, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 33, column: 6
              valve2Open.setPresent();//sysj\fillerController.sysj line: 34, column: 6
              currsigs.addElement(valve2Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 25, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 25, column: 6
              valve1Open.setPresent();//sysj\fillerController.sysj line: 26, column: 6
              currsigs.addElement(valve1Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 2 : 
            if(levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 31, column: 11
              System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 38, column: 4
              fillDone.setPresent();//sysj\fillerController.sysj line: 40, column: 4
              currsigs.addElement(fillDone);
              S2137=3;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 33, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 33, column: 6
              valve2Open.setPresent();//sysj\fillerController.sysj line: 34, column: 6
              currsigs.addElement(valve2Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 3 : 
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 41, column: 11
              System.out.printf("[FC] !bottleAtPos2 seen, holding fillReady%n");//sysj\fillerController.sysj line: 42, column: 4
              S2137=4;
              tFillReady_thread_2 = System.currentTimeMillis();//sysj\fillerController.sysj line: 44, column: 5
              if(System.currentTimeMillis() - tFillReady_thread_2 < 200){//sysj\fillerController.sysj line: 45, column: 12
                fillReady.setPresent();//sysj\fillerController.sysj line: 46, column: 6
                currsigs.addElement(fillReady);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\fillerController.sysj line: 45, column: 5
                S2137=0;
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
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 43, column: 11
              S2137=0;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              if(System.currentTimeMillis() - tFillReady_thread_2 < 200){//sysj\fillerController.sysj line: 45, column: 12
                fillReady.setPresent();//sysj\fillerController.sysj line: 46, column: 6
                currsigs.addElement(fillReady);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\fillerController.sysj line: 45, column: 5
                S2137=0;
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

  public void thread2610(int [] tdone, int [] ends){
        S2603=1;
    if(liquidARatio.getprestatus()){//sysj\fillerController.sysj line: 81, column: 13
      run.FillerRecipe.setRatio((Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())));//sysj\fillerController.sysj line: 81, column: 31
      if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 82, column: 13
        run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 83, column: 5
        totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 84, column: 5
        currsigs.addElement(totalVolumeMlE);
        totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 84, column: 5
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
    else {
      if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 82, column: 13
        run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 83, column: 5
        totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 84, column: 5
        currsigs.addElement(totalVolumeMlE);
        totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 84, column: 5
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
  }

  public void thread2609(int [] tdone, int [] ends){
        S2577=1;
    S2407=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread2608(int [] tdone, int [] ends){
        S2405=1;
    S2399=0;
    if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 56, column: 14
      fillDoneE.setPresent();//sysj\fillerController.sysj line: 57, column: 6
      currsigs.addElement(fillDoneE);
      S2399=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
    else {
      S2399=1;
      active[3]=1;
      ends[3]=1;
      tdone[3]=1;
    }
  }

  public void thread2607(int [] tdone, int [] ends){
        S2397=1;
    S2137=0;
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
      switch(S2605){
        case 0 : 
          S2605=0;
          break RUN;
        
        case 1 : 
          S2605=2;
          S2605=2;
          thread2607(tdone,ends);
          thread2608(tdone,ends);
          thread2609(tdone,ends);
          thread2610(tdone,ends);
          int biggest2611 = 0;
          if(ends[2]>=biggest2611){
            biggest2611=ends[2];
          }
          if(ends[3]>=biggest2611){
            biggest2611=ends[3];
          }
          if(ends[4]>=biggest2611){
            biggest2611=ends[4];
          }
          if(ends[5]>=biggest2611){
            biggest2611=ends[5];
          }
          if(biggest2611 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread2612(tdone,ends);
          thread2613(tdone,ends);
          thread2614(tdone,ends);
          thread2615(tdone,ends);
          int biggest2616 = 0;
          if(ends[2]>=biggest2616){
            biggest2616=ends[2];
          }
          if(ends[3]>=biggest2616){
            biggest2616=ends[3];
          }
          if(ends[4]>=biggest2616){
            biggest2616=ends[4];
          }
          if(ends[5]>=biggest2616){
            biggest2616=ends[5];
          }
          if(biggest2616 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest2616 == 0){
            S2605=0;
            active[1]=0;
            ends[1]=0;
            S2605=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0};
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
      totalVolumeMlE.setpreclear();
      fillDoneCoordE.setpreclear();
      fillReady.setpreclear();
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
      totalVolumeMlE.sethook();
      totalVolumeMlE.setClear();
      fillDoneCoordE.sethook();
      fillDoneCoordE.setClear();
      fillReady.sethook();
      fillReady.setClear();
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
