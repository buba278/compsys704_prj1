import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.FillerGUI;//sysj\fillerPlant.sysj line: 1, column: 1
import run.FillerFaultState;//sysj\fillerPlant.sysj line: 2, column: 1

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
  public Signal overfillM = new Signal("overfillM", Signal.INPUT);
  public Signal stallM = new Signal("stallM", Signal.INPUT);
  public Signal levelAtTarget = new Signal("levelAtTarget", Signal.OUTPUT);
  public Signal valve1OpenE = new Signal("valve1OpenE", Signal.OUTPUT);
  public Signal valve2OpenE = new Signal("valve2OpenE", Signal.OUTPUT);
  public Signal fillLevelE = new Signal("fillLevelE", Signal.OUTPUT);
  public Signal fillLevelMl = new Signal("fillLevelMl", Signal.OUTPUT);
  private Signal fillLevel_1;
  private Signal doseTarget_1;
  private Signal levelAtTargetPulse_1;
  private int prevLvl_thread_2;//sysj\fillerPlant.sysj line: 38, column: 5
  private int target_thread_2;//sysj\fillerPlant.sysj line: 39, column: 5
  private int stopAt_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
  private int lvl_thread_2;//sysj\fillerPlant.sysj line: 41, column: 5
  private long t0_thread_5;//sysj\fillerPlant.sysj line: 87, column: 4
  private int S16531 = 1;
  private int S15144 = 1;
  private int S15090 = 1;
  private int S15161 = 1;
  private int S15178 = 1;
  private int S15244 = 1;
  private int S15180 = 1;
  
  private int[] ends = new int[6];
  private int[] tdone = new int[6];
  
  public void thread16541(int [] tdone, int [] ends){
        switch(S15244){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S15180){
          case 0 : 
            if(levelAtTargetPulse_1.getprestatus()){//sysj\fillerPlant.sysj line: 86, column: 11
              t0_thread_5 = System.currentTimeMillis();//sysj\fillerPlant.sysj line: 87, column: 4
              S15180=1;
              if(System.currentTimeMillis() - t0_thread_5 < 200){//sysj\fillerPlant.sysj line: 88, column: 11
                levelAtTarget.setPresent();//sysj\fillerPlant.sysj line: 89, column: 5
                currsigs.addElement(levelAtTarget);
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
              else {
                ends[5]=2;
                ;//sysj\fillerPlant.sysj line: 88, column: 4
                S15180=0;
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
            }
            else {
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 1 : 
            if(System.currentTimeMillis() - t0_thread_5 < 200){//sysj\fillerPlant.sysj line: 88, column: 11
              levelAtTarget.setPresent();//sysj\fillerPlant.sysj line: 89, column: 5
              currsigs.addElement(levelAtTarget);
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              ends[5]=2;
              ;//sysj\fillerPlant.sysj line: 88, column: 4
              S15180=0;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread16540(int [] tdone, int [] ends){
        switch(S15178){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        if(valve1Open.getprestatus()){//sysj\fillerPlant.sysj line: 72, column: 22
          valve1OpenE.setPresent();//sysj\fillerPlant.sysj line: 73, column: 14
          currsigs.addElement(valve1OpenE);
          if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 75, column: 22
            valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 76, column: 14
            currsigs.addElement(valve2OpenE);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
          }
          else {
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
          }
        }
        else {
          if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 75, column: 22
            valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 76, column: 14
            currsigs.addElement(valve2OpenE);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
          }
          else {
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
          }
        }
        break;
      
    }
  }

  public void thread16539(int [] tdone, int [] ends){
        switch(S15161){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        if(overfillM.getprestatus()){//sysj\fillerPlant.sysj line: 60, column: 13
          FillerFaultState.armOverfill();//sysj\fillerPlant.sysj line: 61, column: 5
          if(stallM.getprestatus()){//sysj\fillerPlant.sysj line: 63, column: 13
            FillerFaultState.armStall();//sysj\fillerPlant.sysj line: 64, column: 5
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
          if(stallM.getprestatus()){//sysj\fillerPlant.sysj line: 63, column: 13
            FillerFaultState.armStall();//sysj\fillerPlant.sysj line: 64, column: 5
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

  public void thread16538(int [] tdone, int [] ends){
        switch(S15144){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S15090){
          case 0 : 
            S15090=0;
            if(doseTargetMl.getprestatus()){//sysj\fillerPlant.sysj line: 27, column: 13
              doseTarget_1.setPresent();//sysj\fillerPlant.sysj line: 28, column: 5
              currsigs.addElement(doseTarget_1);
              doseTarget_1.setValue((Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval())));//sysj\fillerPlant.sysj line: 28, column: 5
              if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 30, column: 12
                fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 31, column: 5
                currsigs.addElement(fillLevel_1);
                fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 31, column: 5
                fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 32, column: 5
                currsigs.addElement(fillLevelE);
                fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 32, column: 5
                fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 33, column: 5
                currsigs.addElement(fillLevelMl);
                fillLevelMl.setValue(0);//sysj\fillerPlant.sysj line: 33, column: 5
                FillerFaultState.clearOverfill();//sysj\fillerPlant.sysj line: 34, column: 5
                FillerFaultState.clearStall();//sysj\fillerPlant.sysj line: 35, column: 5
                S15090=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 37, column: 13
                  prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 38, column: 5
                  target_thread_2 = (Integer)(doseTarget_1.getpreval() == null ? 0 : ((Integer)doseTarget_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 39, column: 5
                  stopAt_thread_2 = FillerFaultState.isOverfillArmed() ? target_thread_2 + 8 : target_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
                  lvl_thread_2 = FillerFaultState.isStallArmed() ? prevLvl_thread_2 : Math.min(prevLvl_thread_2 + 1, stopAt_thread_2);//sysj\fillerPlant.sysj line: 41, column: 5
                  fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 42, column: 5
                  currsigs.addElement(fillLevel_1);
                  fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 42, column: 5
                  fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 43, column: 5
                  currsigs.addElement(fillLevelE);
                  fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 43, column: 5
                  fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 44, column: 5
                  currsigs.addElement(fillLevelMl);
                  fillLevelMl.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 44, column: 5
                  System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 45, column: 5
                  if(prevLvl_thread_2 < stopAt_thread_2 && lvl_thread_2 >= stopAt_thread_2){//sysj\fillerPlant.sysj line: 46, column: 9
                    System.out.printf("target reached at lvl=%d (nominal target=%d)%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 47, column: 6
                    levelAtTargetPulse_1.setPresent();//sysj\fillerPlant.sysj line: 48, column: 6
                    currsigs.addElement(levelAtTargetPulse_1);
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                }
                else {
                  S15090=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 30, column: 12
                fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 31, column: 5
                currsigs.addElement(fillLevel_1);
                fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 31, column: 5
                fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 32, column: 5
                currsigs.addElement(fillLevelE);
                fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 32, column: 5
                fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 33, column: 5
                currsigs.addElement(fillLevelMl);
                fillLevelMl.setValue(0);//sysj\fillerPlant.sysj line: 33, column: 5
                FillerFaultState.clearOverfill();//sysj\fillerPlant.sysj line: 34, column: 5
                FillerFaultState.clearStall();//sysj\fillerPlant.sysj line: 35, column: 5
                S15090=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 37, column: 13
                  prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 38, column: 5
                  target_thread_2 = (Integer)(doseTarget_1.getpreval() == null ? 0 : ((Integer)doseTarget_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 39, column: 5
                  stopAt_thread_2 = FillerFaultState.isOverfillArmed() ? target_thread_2 + 8 : target_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
                  lvl_thread_2 = FillerFaultState.isStallArmed() ? prevLvl_thread_2 : Math.min(prevLvl_thread_2 + 1, stopAt_thread_2);//sysj\fillerPlant.sysj line: 41, column: 5
                  fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 42, column: 5
                  currsigs.addElement(fillLevel_1);
                  fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 42, column: 5
                  fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 43, column: 5
                  currsigs.addElement(fillLevelE);
                  fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 43, column: 5
                  fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 44, column: 5
                  currsigs.addElement(fillLevelMl);
                  fillLevelMl.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 44, column: 5
                  System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 45, column: 5
                  if(prevLvl_thread_2 < stopAt_thread_2 && lvl_thread_2 >= stopAt_thread_2){//sysj\fillerPlant.sysj line: 46, column: 9
                    System.out.printf("target reached at lvl=%d (nominal target=%d)%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 47, column: 6
                    levelAtTargetPulse_1.setPresent();//sysj\fillerPlant.sysj line: 48, column: 6
                    currsigs.addElement(levelAtTargetPulse_1);
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                }
                else {
                  S15090=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            break;
          
          case 1 : 
            S15090=1;
            S15090=0;
            if(doseTargetMl.getprestatus()){//sysj\fillerPlant.sysj line: 27, column: 13
              doseTarget_1.setPresent();//sysj\fillerPlant.sysj line: 28, column: 5
              currsigs.addElement(doseTarget_1);
              doseTarget_1.setValue((Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval())));//sysj\fillerPlant.sysj line: 28, column: 5
              if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 30, column: 12
                fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 31, column: 5
                currsigs.addElement(fillLevel_1);
                fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 31, column: 5
                fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 32, column: 5
                currsigs.addElement(fillLevelE);
                fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 32, column: 5
                fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 33, column: 5
                currsigs.addElement(fillLevelMl);
                fillLevelMl.setValue(0);//sysj\fillerPlant.sysj line: 33, column: 5
                FillerFaultState.clearOverfill();//sysj\fillerPlant.sysj line: 34, column: 5
                FillerFaultState.clearStall();//sysj\fillerPlant.sysj line: 35, column: 5
                S15090=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 37, column: 13
                  prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 38, column: 5
                  target_thread_2 = (Integer)(doseTarget_1.getpreval() == null ? 0 : ((Integer)doseTarget_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 39, column: 5
                  stopAt_thread_2 = FillerFaultState.isOverfillArmed() ? target_thread_2 + 8 : target_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
                  lvl_thread_2 = FillerFaultState.isStallArmed() ? prevLvl_thread_2 : Math.min(prevLvl_thread_2 + 1, stopAt_thread_2);//sysj\fillerPlant.sysj line: 41, column: 5
                  fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 42, column: 5
                  currsigs.addElement(fillLevel_1);
                  fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 42, column: 5
                  fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 43, column: 5
                  currsigs.addElement(fillLevelE);
                  fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 43, column: 5
                  fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 44, column: 5
                  currsigs.addElement(fillLevelMl);
                  fillLevelMl.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 44, column: 5
                  System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 45, column: 5
                  if(prevLvl_thread_2 < stopAt_thread_2 && lvl_thread_2 >= stopAt_thread_2){//sysj\fillerPlant.sysj line: 46, column: 9
                    System.out.printf("target reached at lvl=%d (nominal target=%d)%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 47, column: 6
                    levelAtTargetPulse_1.setPresent();//sysj\fillerPlant.sysj line: 48, column: 6
                    currsigs.addElement(levelAtTargetPulse_1);
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                }
                else {
                  S15090=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 30, column: 12
                fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 31, column: 5
                currsigs.addElement(fillLevel_1);
                fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 31, column: 5
                fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 32, column: 5
                currsigs.addElement(fillLevelE);
                fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 32, column: 5
                fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 33, column: 5
                currsigs.addElement(fillLevelMl);
                fillLevelMl.setValue(0);//sysj\fillerPlant.sysj line: 33, column: 5
                FillerFaultState.clearOverfill();//sysj\fillerPlant.sysj line: 34, column: 5
                FillerFaultState.clearStall();//sysj\fillerPlant.sysj line: 35, column: 5
                S15090=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 37, column: 13
                  prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 38, column: 5
                  target_thread_2 = (Integer)(doseTarget_1.getpreval() == null ? 0 : ((Integer)doseTarget_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 39, column: 5
                  stopAt_thread_2 = FillerFaultState.isOverfillArmed() ? target_thread_2 + 8 : target_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
                  lvl_thread_2 = FillerFaultState.isStallArmed() ? prevLvl_thread_2 : Math.min(prevLvl_thread_2 + 1, stopAt_thread_2);//sysj\fillerPlant.sysj line: 41, column: 5
                  fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 42, column: 5
                  currsigs.addElement(fillLevel_1);
                  fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 42, column: 5
                  fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 43, column: 5
                  currsigs.addElement(fillLevelE);
                  fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 43, column: 5
                  fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 44, column: 5
                  currsigs.addElement(fillLevelMl);
                  fillLevelMl.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 44, column: 5
                  System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 45, column: 5
                  if(prevLvl_thread_2 < stopAt_thread_2 && lvl_thread_2 >= stopAt_thread_2){//sysj\fillerPlant.sysj line: 46, column: 9
                    System.out.printf("target reached at lvl=%d (nominal target=%d)%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 47, column: 6
                    levelAtTargetPulse_1.setPresent();//sysj\fillerPlant.sysj line: 48, column: 6
                    currsigs.addElement(levelAtTargetPulse_1);
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    S15090=1;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                }
                else {
                  S15090=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread16536(int [] tdone, int [] ends){
        S15244=1;
    S15180=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread16535(int [] tdone, int [] ends){
        S15178=1;
    if(valve1Open.getprestatus()){//sysj\fillerPlant.sysj line: 72, column: 22
      valve1OpenE.setPresent();//sysj\fillerPlant.sysj line: 73, column: 14
      currsigs.addElement(valve1OpenE);
      if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 75, column: 22
        valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 76, column: 14
        currsigs.addElement(valve2OpenE);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
      else {
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
    }
    else {
      if(valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 75, column: 22
        valve2OpenE.setPresent();//sysj\fillerPlant.sysj line: 76, column: 14
        currsigs.addElement(valve2OpenE);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
      else {
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
    }
  }

  public void thread16534(int [] tdone, int [] ends){
        S15161=1;
    if(overfillM.getprestatus()){//sysj\fillerPlant.sysj line: 60, column: 13
      FillerFaultState.armOverfill();//sysj\fillerPlant.sysj line: 61, column: 5
      if(stallM.getprestatus()){//sysj\fillerPlant.sysj line: 63, column: 13
        FillerFaultState.armStall();//sysj\fillerPlant.sysj line: 64, column: 5
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
      if(stallM.getprestatus()){//sysj\fillerPlant.sysj line: 63, column: 13
        FillerFaultState.armStall();//sysj\fillerPlant.sysj line: 64, column: 5
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

  public void thread16533(int [] tdone, int [] ends){
        S15144=1;
    S15090=0;
    if(doseTargetMl.getprestatus()){//sysj\fillerPlant.sysj line: 27, column: 13
      doseTarget_1.setPresent();//sysj\fillerPlant.sysj line: 28, column: 5
      currsigs.addElement(doseTarget_1);
      doseTarget_1.setValue((Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval())));//sysj\fillerPlant.sysj line: 28, column: 5
      if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 30, column: 12
        fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 31, column: 5
        currsigs.addElement(fillLevel_1);
        fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 31, column: 5
        fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 32, column: 5
        currsigs.addElement(fillLevelE);
        fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 32, column: 5
        fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 33, column: 5
        currsigs.addElement(fillLevelMl);
        fillLevelMl.setValue(0);//sysj\fillerPlant.sysj line: 33, column: 5
        FillerFaultState.clearOverfill();//sysj\fillerPlant.sysj line: 34, column: 5
        FillerFaultState.clearStall();//sysj\fillerPlant.sysj line: 35, column: 5
        S15090=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 37, column: 13
          prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 38, column: 5
          target_thread_2 = (Integer)(doseTarget_1.getpreval() == null ? 0 : ((Integer)doseTarget_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 39, column: 5
          stopAt_thread_2 = FillerFaultState.isOverfillArmed() ? target_thread_2 + 8 : target_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
          lvl_thread_2 = FillerFaultState.isStallArmed() ? prevLvl_thread_2 : Math.min(prevLvl_thread_2 + 1, stopAt_thread_2);//sysj\fillerPlant.sysj line: 41, column: 5
          fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 42, column: 5
          currsigs.addElement(fillLevel_1);
          fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 42, column: 5
          fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 43, column: 5
          currsigs.addElement(fillLevelE);
          fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 43, column: 5
          fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 44, column: 5
          currsigs.addElement(fillLevelMl);
          fillLevelMl.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 44, column: 5
          System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 45, column: 5
          if(prevLvl_thread_2 < stopAt_thread_2 && lvl_thread_2 >= stopAt_thread_2){//sysj\fillerPlant.sysj line: 46, column: 9
            System.out.printf("target reached at lvl=%d (nominal target=%d)%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 47, column: 6
            levelAtTargetPulse_1.setPresent();//sysj\fillerPlant.sysj line: 48, column: 6
            currsigs.addElement(levelAtTargetPulse_1);
            S15090=1;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
          }
          else {
            S15090=1;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
          }
        }
        else {
          S15090=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
    }
    else {
      if(!bottleAtPos2.getprestatus()){//sysj\fillerPlant.sysj line: 30, column: 12
        fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 31, column: 5
        currsigs.addElement(fillLevel_1);
        fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 31, column: 5
        fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 32, column: 5
        currsigs.addElement(fillLevelE);
        fillLevelE.setValue(0);//sysj\fillerPlant.sysj line: 32, column: 5
        fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 33, column: 5
        currsigs.addElement(fillLevelMl);
        fillLevelMl.setValue(0);//sysj\fillerPlant.sysj line: 33, column: 5
        FillerFaultState.clearOverfill();//sysj\fillerPlant.sysj line: 34, column: 5
        FillerFaultState.clearStall();//sysj\fillerPlant.sysj line: 35, column: 5
        S15090=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerPlant.sysj line: 37, column: 13
          prevLvl_thread_2 = (Integer)(fillLevel_1.getpreval() == null ? 0 : ((Integer)fillLevel_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 38, column: 5
          target_thread_2 = (Integer)(doseTarget_1.getpreval() == null ? 0 : ((Integer)doseTarget_1.getpreval()).intValue());//sysj\fillerPlant.sysj line: 39, column: 5
          stopAt_thread_2 = FillerFaultState.isOverfillArmed() ? target_thread_2 + 8 : target_thread_2;//sysj\fillerPlant.sysj line: 40, column: 5
          lvl_thread_2 = FillerFaultState.isStallArmed() ? prevLvl_thread_2 : Math.min(prevLvl_thread_2 + 1, stopAt_thread_2);//sysj\fillerPlant.sysj line: 41, column: 5
          fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 42, column: 5
          currsigs.addElement(fillLevel_1);
          fillLevel_1.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 42, column: 5
          fillLevelE.setPresent();//sysj\fillerPlant.sysj line: 43, column: 5
          currsigs.addElement(fillLevelE);
          fillLevelE.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 43, column: 5
          fillLevelMl.setPresent();//sysj\fillerPlant.sysj line: 44, column: 5
          currsigs.addElement(fillLevelMl);
          fillLevelMl.setValue(lvl_thread_2);//sysj\fillerPlant.sysj line: 44, column: 5
          System.out.printf("lvl=%d doseTarget=%d%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 45, column: 5
          if(prevLvl_thread_2 < stopAt_thread_2 && lvl_thread_2 >= stopAt_thread_2){//sysj\fillerPlant.sysj line: 46, column: 9
            System.out.printf("target reached at lvl=%d (nominal target=%d)%n", lvl_thread_2, target_thread_2);//sysj\fillerPlant.sysj line: 47, column: 6
            levelAtTargetPulse_1.setPresent();//sysj\fillerPlant.sysj line: 48, column: 6
            currsigs.addElement(levelAtTargetPulse_1);
            S15090=1;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
          }
          else {
            S15090=1;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
          }
        }
        else {
          S15090=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
    }
  }

  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S16531){
        case 0 : 
          S16531=0;
          break RUN;
        
        case 1 : 
          S16531=2;
          S16531=2;
          new Thread(new FillerGUI()).start();//sysj\fillerPlant.sysj line: 17, column: 2
          fillLevel_1.setClear();//sysj\fillerPlant.sysj line: 19, column: 2
          fillLevel_1.setPresent();//sysj\fillerPlant.sysj line: 20, column: 2
          currsigs.addElement(fillLevel_1);
          fillLevel_1.setValue(0);//sysj\fillerPlant.sysj line: 20, column: 2
          doseTarget_1.setClear();//sysj\fillerPlant.sysj line: 21, column: 2
          doseTarget_1.setPresent();//sysj\fillerPlant.sysj line: 22, column: 2
          currsigs.addElement(doseTarget_1);
          doseTarget_1.setValue(0);//sysj\fillerPlant.sysj line: 22, column: 2
          levelAtTargetPulse_1.setClear();//sysj\fillerPlant.sysj line: 23, column: 2
          thread16533(tdone,ends);
          thread16534(tdone,ends);
          thread16535(tdone,ends);
          thread16536(tdone,ends);
          int biggest16537 = 0;
          if(ends[2]>=biggest16537){
            biggest16537=ends[2];
          }
          if(ends[3]>=biggest16537){
            biggest16537=ends[3];
          }
          if(ends[4]>=biggest16537){
            biggest16537=ends[4];
          }
          if(ends[5]>=biggest16537){
            biggest16537=ends[5];
          }
          if(biggest16537 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          fillLevel_1.setClear();//sysj\fillerPlant.sysj line: 19, column: 2
          doseTarget_1.setClear();//sysj\fillerPlant.sysj line: 21, column: 2
          levelAtTargetPulse_1.setClear();//sysj\fillerPlant.sysj line: 23, column: 2
          thread16538(tdone,ends);
          thread16539(tdone,ends);
          thread16540(tdone,ends);
          thread16541(tdone,ends);
          int biggest16542 = 0;
          if(ends[2]>=biggest16542){
            biggest16542=ends[2];
          }
          if(ends[3]>=biggest16542){
            biggest16542=ends[3];
          }
          if(ends[4]>=biggest16542){
            biggest16542=ends[4];
          }
          if(ends[5]>=biggest16542){
            biggest16542=ends[5];
          }
          if(biggest16542 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest16542 == 0){
            S16531=0;
            active[1]=0;
            ends[1]=0;
            S16531=0;
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
    fillLevel_1 = new Signal();
    doseTarget_1 = new Signal();
    levelAtTargetPulse_1 = new Signal();
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
          overfillM.gethook();
          stallM.gethook();
          df = true;
        }
        runClockDomain();
      }
      valve1Open.setpreclear();
      valve2Open.setpreclear();
      doseTargetMl.setpreclear();
      bottleAtPos2.setpreclear();
      overfillM.setpreclear();
      stallM.setpreclear();
      levelAtTarget.setpreclear();
      valve1OpenE.setpreclear();
      valve2OpenE.setpreclear();
      fillLevelE.setpreclear();
      fillLevelMl.setpreclear();
      fillLevel_1.setpreclear();
      doseTarget_1.setpreclear();
      levelAtTargetPulse_1.setpreclear();
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
      dummyint = overfillM.getStatus() ? overfillM.setprepresent() : overfillM.setpreclear();
      overfillM.setpreval(overfillM.getValue());
      overfillM.setClear();
      dummyint = stallM.getStatus() ? stallM.setprepresent() : stallM.setpreclear();
      stallM.setpreval(stallM.getValue());
      stallM.setClear();
      levelAtTarget.sethook();
      levelAtTarget.setClear();
      valve1OpenE.sethook();
      valve1OpenE.setClear();
      valve2OpenE.sethook();
      valve2OpenE.setClear();
      fillLevelE.sethook();
      fillLevelE.setClear();
      fillLevelMl.sethook();
      fillLevelMl.setClear();
      fillLevel_1.setClear();
      doseTarget_1.setClear();
      levelAtTargetPulse_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        valve1Open.gethook();
        valve2Open.gethook();
        doseTargetMl.gethook();
        bottleAtPos2.gethook();
        overfillM.gethook();
        stallM.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
