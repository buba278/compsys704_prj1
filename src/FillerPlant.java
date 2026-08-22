import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.FillerGUI;//sysj\fillerPlant.sysj line: 1, column: 1

public class FillerPlant extends ClockDomain{
  public FillerPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal valve1Open = new Signal("valve1Open", Signal.INPUT);
  public Signal valve2Open = new Signal("valve2Open", Signal.INPUT);
  public Signal doseTargetMl = new Signal("doseTargetMl", Signal.INPUT);
  public Signal bottleAtPos2 = new Signal("bottleAtPos2", Signal.INPUT);
  public Signal levelAtTarget = new Signal("levelAtTarget", Signal.OUTPUT);
  public Signal valve1OpenE = new Signal("valve1OpenE", Signal.OUTPUT);
  public Signal valve2OpenE = new Signal("valve2OpenE", Signal.OUTPUT);
  public Signal fillLevelE = new Signal("fillLevelE", Signal.OUTPUT);
  private Signal fillLevel_1;
  private int prevLvl_thread_2;//sysj\fillerPlant.sysj line: 26, column: 5
  private int target_thread_2;//sysj\fillerPlant.sysj line: 27, column: 5
  private int lvl_thread_2;//sysj\fillerPlant.sysj line: 28, column: 5
  private int S1573 = 1;
  private int S1305 = 1;
  private int S1322 = 1;
  
  private int[] ends = new int[4];
  private int[] tdone = new int[4];
  
  public void thread1579(int [] tdone, int [] ends){
        switch(S1322){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        if(valve1Open.getprestatus()){//sysj\fillerPlant.sysj line: 43, column: 22
          valve1OpenE.setPresent();//sysj\fillerPlant.sysj line: 44, column: 14
          currsigs.addElement(valve1OpenE);
          if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 46, column: 22
            valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 47, column: 14
            currsigs.addElement(valve2OpenE);
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
        else {
          if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 46, column: 22
            valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 47, column: 14
            currsigs.addElement(valve2OpenE);
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
        break;
      
    }
  }

  public void thread1578(int [] tdone, int [] ends){
        switch(S1305){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 20, column: 12
          fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 21, column: 5
          currsigs.addElement(fillLevel_1);
          fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 21, column: 5
          fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 22, column: 5
          currsigs.addElement(fillLevelE);
          fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 22, column: 5
          if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 24, column: 13
            prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 26, column: 5
            target_thread_2 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerPlant.sysj line: 27, column: 5
            lvl_thread_2 = Math.min(prevLvl_thread_2 + 1, target_thread_2);//sysj\fillerPlant.sysj line: 28, column: 5
            fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 29, column: 5
            currsigs.addElement(fillLevel_1);
            fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 29, column: 5
            fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 30, column: 5
            currsigs.addElement(fillLevelE);
            fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 30, column: 5
            System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 31, column: 5
            if(prevLvl_thread_2 < target_thread_2 && lvl_thread_2 >= target_thread_2){//sysj\fillerPlant.sysj line: 32, column: 9
              System.out.printf("target reached at lvl=%d%n", lvl_thread_2);//sysj\fillerPlant.sysj line: 33, column: 6
              levelAtTarget.setPresent();//sysj\fillerPlant.sysj line: 34, column: 6
              currsigs.addElement(levelAtTarget);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
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
        }
        else {
          if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 24, column: 13
            prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 26, column: 5
            target_thread_2 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerPlant.sysj line: 27, column: 5
            lvl_thread_2 = Math.min(prevLvl_thread_2 + 1, target_thread_2);//sysj\fillerPlant.sysj line: 28, column: 5
            fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 29, column: 5
            currsigs.addElement(fillLevel_1);
            fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 29, column: 5
            fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 30, column: 5
            currsigs.addElement(fillLevelE);
            fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 30, column: 5
            System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 31, column: 5
            if(prevLvl_thread_2 < target_thread_2 && lvl_thread_2 >= target_thread_2){//sysj\fillerPlant.sysj line: 32, column: 9
              System.out.printf("target reached at lvl=%d%n", lvl_thread_2);//sysj\fillerPlant.sysj line: 33, column: 6
              levelAtTarget.setPresent();//sysj\fillerPlant.sysj line: 34, column: 6
              currsigs.addElement(levelAtTarget);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
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
        }
        break;
      
    }
  }

  public void thread1576(int [] tdone, int [] ends){
        S1322=1;
    if(valve1Open.getprestatus()){//sysj\fillerPlant.sysj line: 43, column: 22
      valve1OpenE.setPresent();//sysj\fillerPlant.sysj line: 44, column: 14
      currsigs.addElement(valve1OpenE);
      if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 46, column: 22
        valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 47, column: 14
        currsigs.addElement(valve2OpenE);
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
    else {
      if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 46, column: 22
        valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 47, column: 14
        currsigs.addElement(valve2OpenE);
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
  }

  public void thread1575(int [] tdone, int [] ends){
        S1305=1;
    if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 20, column: 12
      fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 21, column: 5
      currsigs.addElement(fillLevel_1);
      fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 21, column: 5
      fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 22, column: 5
      currsigs.addElement(fillLevelE);
      fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 22, column: 5
      if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 24, column: 13
        prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 26, column: 5
        target_thread_2 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerPlant.sysj line: 27, column: 5
        lvl_thread_2 = Math.min(prevLvl_thread_2 + 1, target_thread_2);//sysj\fillerPlant.sysj line: 28, column: 5
        fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 29, column: 5
        currsigs.addElement(fillLevel_1);
        fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 29, column: 5
        fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 30, column: 5
        currsigs.addElement(fillLevelE);
        fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 30, column: 5
        System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 31, column: 5
        if(prevLvl_thread_2 < target_thread_2 && lvl_thread_2 >= target_thread_2){//sysj\fillerPlant.sysj line: 32, column: 9
          System.out.printf("target reached at lvl=%d%n", lvl_thread_2);//sysj\fillerPlant.sysj line: 33, column: 6
          levelAtTarget.setPresent();//sysj\fillerPlant.sysj line: 34, column: 6
          currsigs.addElement(levelAtTarget);
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
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
    }
    else {
      if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 24, column: 13
        prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 26, column: 5
        target_thread_2 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerPlant.sysj line: 27, column: 5
        lvl_thread_2 = Math.min(prevLvl_thread_2 + 1, target_thread_2);//sysj\fillerPlant.sysj line: 28, column: 5
        fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 29, column: 5
        currsigs.addElement(fillLevel_1);
        fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 29, column: 5
        fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 30, column: 5
        currsigs.addElement(fillLevelE);
        fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 30, column: 5
        System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 31, column: 5
        if(prevLvl_thread_2 < target_thread_2 && lvl_thread_2 >= target_thread_2){//sysj\fillerPlant.sysj line: 32, column: 9
          System.out.printf("target reached at lvl=%d%n", lvl_thread_2);//sysj\fillerPlant.sysj line: 33, column: 6
          levelAtTarget.setPresent();//sysj\fillerPlant.sysj line: 34, column: 6
          currsigs.addElement(levelAtTarget);
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
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
    }
  }

  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S1573){
        case 0 : 
          S1573=0;
          break RUN;
        
        case 1 : 
          S1573=2;
          S1573=2;
          new Thread(new FillerGUI()).start();//sysj\fillerPlant.sysj line: 13, column: 2
          fillLevel_1.setClear();//sysj\fillerPlant.sysj line: 15, column: 2
          fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 16, column: 2
          currsigs.addElement(fillLevel_1);
          fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 16, column: 2
          thread1575(tdone,ends);
          thread1576(tdone,ends);
          int biggest1577 = 0;
          if(ends[2]>=biggest1577){
            biggest1577=ends[2];
          }
          if(ends[3]>=biggest1577){
            biggest1577=ends[3];
          }
          if(biggest1577 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          fillLevel_1.setClear();//sysj\fillerPlant.sysj line: 15, column: 2
          thread1578(tdone,ends);
          thread1579(tdone,ends);
          int biggest1580 = 0;
          if(ends[2]>=biggest1580){
            biggest1580=ends[2];
          }
          if(ends[3]>=biggest1580){
            biggest1580=ends[3];
          }
          if(biggest1580 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest1580 == 0){
            S1573=0;
            active[1]=0;
            ends[1]=0;
            S1573=0;
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
    fillLevel_1 = new Signal();
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
          valve1Open.gethook();
          valve2Open.gethook();
          doseTargetMl.gethook();
          bottleAtPos2.gethook();
          df = true;
        }
        runClockDomain();
      }
      valve1Open.setpreclear();
      valve2Open.setpreclear();
      doseTargetMl.setpreclear();
      bottleAtPos2.setpreclear();
      levelAtTarget.setpreclear();
      valve1OpenE.setpreclear();
      valve2OpenE.setpreclear();
      fillLevelE.setpreclear();
      fillLevel_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = valve1Open.getStatus() ? valve1Open.setprepresent() : valve1Open.setpreclear();
      valve1Open.setpreval(valve1Open.getValue());
      valve1Open.setClear();
      dummyint = valve2Open.getStatus() ? valve2Open.setprepresent() : valve2Open.setpreclear();
      valve2Open.setpreval(valve2Open.getValue());
      valve2Open.setClear();
      dummyint = doseTargetMl.getStatus() ? doseTargetMl.setprepresent() : doseTargetMl.setpreclear();
      doseTargetMl.setpreval(doseTargetMl.getValue());
      doseTargetMl.setClear();
      dummyint = bottleAtPos2.getStatus() ? bottleAtPos2.setprepresent() : bottleAtPos2.setpreclear();
      bottleAtPos2.setpreval(bottleAtPos2.getValue());
      bottleAtPos2.setClear();
      levelAtTarget.sethook();
      levelAtTarget.setClear();
      valve1OpenE.sethook();
      valve1OpenE.setClear();
      valve2OpenE.sethook();
      valve2OpenE.setClear();
      fillLevelE.sethook();
      fillLevelE.setClear();
      fillLevel_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        valve1Open.gethook();
        valve2Open.gethook();
        doseTargetMl.gethook();
        bottleAtPos2.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
