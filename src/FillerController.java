import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.FillerFaultState;//sysj\fillerController.sysj line: 1, column: 1

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
  public Signal fillLevelMl = new Signal("fillLevelMl", Signal.INPUT);
  public Signal clearFaultM = new Signal("clearFaultM", Signal.INPUT);
  public Signal doseTargetMl = new Signal("doseTargetMl", Signal.OUTPUT);
  public Signal valve1Open = new Signal("valve1Open", Signal.OUTPUT);
  public Signal valve2Open = new Signal("valve2Open", Signal.OUTPUT);
  public Signal fillDone = new Signal("fillDone", Signal.OUTPUT);
  public Signal fillDoneE = new Signal("fillDoneE", Signal.OUTPUT);
  public Signal totalVolumeMlE = new Signal("totalVolumeMlE", Signal.OUTPUT);
  public Signal fillFaultE = new Signal("fillFaultE", Signal.OUTPUT);
  public Signal fillDoneCoordE = new Signal("fillDoneCoordE", Signal.OUTPUT);
  public Signal fillReady = new Signal("fillReady", Signal.OUTPUT);
  public Signal fillFaultOut = new Signal("fillFaultOut", Signal.OUTPUT);
  private Signal stationFault_1;
  private boolean stalling_thread_8;//sysj\fillerController.sysj line: 169, column: 3
  private boolean faulted_thread_8;//sysj\fillerController.sysj line: 170, column: 3
  private long stallStart_thread_8;//sysj\fillerController.sysj line: 171, column: 3
  private int aTarget_thread_2;//sysj\fillerController.sysj line: 29, column: 4
  private int bTarget_thread_2;//sysj\fillerController.sysj line: 30, column: 4
  private boolean faulted_thread_2;//sysj\fillerController.sysj line: 31, column: 4
  private boolean bottleLost_thread_2;//sysj\fillerController.sysj line: 32, column: 4
  private long tFillReady_thread_2;//sysj\fillerController.sysj line: 86, column: 5
  private long tFault_thread_4;//sysj\fillerController.sysj line: 115, column: 5
  private long tFillDoneCoord_thread_5;//sysj\fillerController.sysj line: 130, column: 5
  private int level_thread_7;//sysj\fillerController.sysj line: 154, column: 5
  private int target_thread_7;//sysj\fillerController.sysj line: 155, column: 5
  private int S6125 = 1;
  private int S5705 = 1;
  private int S5007 = 1;
  private int S5040 = 1;
  private int S5022 = 1;
  private int S5713 = 1;
  private int S5829 = 1;
  private int S5715 = 1;
  private int S6001 = 1;
  private int S5831 = 1;
  private int S6027 = 1;
  private int S6050 = 1;
  private int S6106 = 1;
  private int S6123 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread6143(int [] tdone, int [] ends){
        switch(S6123){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(clearFaultM.getprestatus()){//sysj\fillerController.sysj line: 199, column: 13
          FillerFaultState.clearStationFault();//sysj\fillerController.sysj line: 200, column: 5
          if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 202, column: 4
            stationFault_1.setPresent();//sysj\fillerController.sysj line: 203, column: 5
            currsigs.addElement(stationFault_1);
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
        else {
          if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 202, column: 4
            stationFault_1.setPresent();//sysj\fillerController.sysj line: 203, column: 5
            currsigs.addElement(stationFault_1);
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
        break;
      
    }
  }

  public void thread6142(int [] tdone, int [] ends){
        switch(S6106){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(levelAtTarget.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 173, column: 13
          stalling_thread_8 = false;//sysj\fillerController.sysj line: 174, column: 5
          faulted_thread_8 = false;//sysj\fillerController.sysj line: 175, column: 5
          active[8]=1;
          ends[8]=1;
          tdone[8]=1;
        }
        else {
          if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerController.sysj line: 177, column: 13
            if(!stalling_thread_8){//sysj\fillerController.sysj line: 178, column: 9
              stalling_thread_8 = true;//sysj\fillerController.sysj line: 179, column: 6
              stallStart_thread_8 = System.currentTimeMillis();//sysj\fillerController.sysj line: 180, column: 6
              active[8]=1;
              ends[8]=1;
              tdone[8]=1;
            }
            else {
              if(!faulted_thread_8 && System.currentTimeMillis() - stallStart_thread_8 > 8000){//sysj\fillerController.sysj line: 181, column: 16
                System.out.printf("[FC] FAULT: stall detected, valve held open >8000ms without reaching target%n");//sysj\fillerController.sysj line: 182, column: 6
                fillFaultE.setPresent();//sysj\fillerController.sysj line: 183, column: 6
                currsigs.addElement(fillFaultE);
                FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 184, column: 6
                faulted_thread_8 = true;//sysj\fillerController.sysj line: 185, column: 6
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
          }
          else {
            stalling_thread_8 = false;//sysj\fillerController.sysj line: 188, column: 5
            faulted_thread_8 = false;//sysj\fillerController.sysj line: 189, column: 5
            active[8]=1;
            ends[8]=1;
            tdone[8]=1;
          }
        }
        break;
      
    }
  }

  public void thread6141(int [] tdone, int [] ends){
        switch(S6050){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(doseTargetMl.getprestatus() && fillLevelMl.getprestatus()){//sysj\fillerController.sysj line: 153, column: 13
          level_thread_7 = (Integer)(fillLevelMl.getpreval() == null ? null : ((Integer)fillLevelMl.getpreval()));//sysj\fillerController.sysj line: 154, column: 5
          target_thread_7 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerController.sysj line: 155, column: 5
          if(level_thread_7 > target_thread_7 && !FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 156, column: 9
            System.out.printf("[FC] FAULT: overfill detected, level=%d target=%d%n", level_thread_7, target_thread_7);//sysj\fillerController.sysj line: 157, column: 6
            fillFaultE.setPresent();//sysj\fillerController.sysj line: 158, column: 6
            currsigs.addElement(fillFaultE);
            FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 159, column: 6
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
        else {
          active[7]=1;
          ends[7]=1;
          tdone[7]=1;
        }
        break;
      
    }
  }

  public void thread6140(int [] tdone, int [] ends){
        switch(S6027){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(liquidARatio.getprestatus()){//sysj\fillerController.sysj line: 141, column: 13
          run.FillerRecipe.setRatio((Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())));//sysj\fillerController.sysj line: 141, column: 31
          if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 142, column: 13
            run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 143, column: 5
            totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 144, column: 5
            currsigs.addElement(totalVolumeMlE);
            totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 144, column: 5
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
        else {
          if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 142, column: 13
            run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 143, column: 5
            totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 144, column: 5
            currsigs.addElement(totalVolumeMlE);
            totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 144, column: 5
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
        break;
      
    }
  }

  public void thread6139(int [] tdone, int [] ends){
        switch(S6001){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S5831){
          case 0 : 
            if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 127, column: 11
              System.out.printf("[FC] fillDoneCoordE → coordinator (holding)%n");//sysj\fillerController.sysj line: 128, column: 4
              S5831=1;
              tFillDoneCoord_thread_5 = System.currentTimeMillis();//sysj\fillerController.sysj line: 130, column: 5
              if(System.currentTimeMillis() - tFillDoneCoord_thread_5 < 200){//sysj\fillerController.sysj line: 131, column: 12
                fillDoneCoordE.setPresent();//sysj\fillerController.sysj line: 132, column: 6
                currsigs.addElement(fillDoneCoordE);
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
              else {
                ends[5]=2;
                ;//sysj\fillerController.sysj line: 131, column: 5
                S5831=0;
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
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 129, column: 11
              S5831=0;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              if(System.currentTimeMillis() - tFillDoneCoord_thread_5 < 200){//sysj\fillerController.sysj line: 131, column: 12
                fillDoneCoordE.setPresent();//sysj\fillerController.sysj line: 132, column: 6
                currsigs.addElement(fillDoneCoordE);
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
              else {
                ends[5]=2;
                ;//sysj\fillerController.sysj line: 131, column: 5
                S5831=0;
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread6138(int [] tdone, int [] ends){
        switch(S5829){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S5715){
          case 0 : 
            if(stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 112, column: 11
              System.out.printf("[FC] fillFaultOut → coordinator (holding)%n");//sysj\fillerController.sysj line: 113, column: 4
              S5715=1;
              tFault_thread_4 = System.currentTimeMillis();//sysj\fillerController.sysj line: 115, column: 5
              if(System.currentTimeMillis() - tFault_thread_4 < 200){//sysj\fillerController.sysj line: 116, column: 12
                fillFaultOut.setPresent();//sysj\fillerController.sysj line: 117, column: 6
                currsigs.addElement(fillFaultOut);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\fillerController.sysj line: 116, column: 5
                S5715=2;
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
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 114, column: 11
              S5715=2;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(System.currentTimeMillis() - tFault_thread_4 < 200){//sysj\fillerController.sysj line: 116, column: 12
                fillFaultOut.setPresent();//sysj\fillerController.sysj line: 117, column: 6
                currsigs.addElement(fillFaultOut);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\fillerController.sysj line: 116, column: 5
                S5715=2;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
          case 2 : 
            if(!stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 121, column: 11
              S5715=0;
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

  public void thread6137(int [] tdone, int [] ends){
        switch(S5713){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 98, column: 14
          fillDoneE.setPresent();//sysj\fillerController.sysj line: 99, column: 6
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

  public void thread6136(int [] tdone, int [] ends){
        switch(S5705){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S5007){
          case 0 : 
            if(!stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 26, column: 11
              S5007=1;
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
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 27, column: 11
              aTarget_thread_2 = run.FillerRecipe.getVolume() * run.FillerRecipe.getRatio() / 100;//sysj\fillerController.sysj line: 29, column: 4
              bTarget_thread_2 = run.FillerRecipe.getVolume();//sysj\fillerController.sysj line: 30, column: 4
              faulted_thread_2 = false;//sysj\fillerController.sysj line: 31, column: 4
              bottleLost_thread_2 = false;//sysj\fillerController.sysj line: 32, column: 4
              S5007=2;
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
          
          case 2 : 
            if(!levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 34, column: 11
              System.out.printf("phase 1 start: aTarget=%d bTarget=%d%n", aTarget_thread_2, bTarget_thread_2);//sysj\fillerController.sysj line: 35, column: 4
              S5007=3;
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 38, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 38, column: 6
              valve1Open.setPresent();//sysj\fillerController.sysj line: 39, column: 6
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
          
          case 3 : 
            if(levelAtTarget.getprestatus() || stationFault_1.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 36, column: 11
              S5007=4;
              if(stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 43, column: 13
                S5040=0;
                faulted_thread_2 = true;//sysj\fillerController.sysj line: 44, column: 5
                System.out.printf("[FC] phase 1 aborted by fault - valves closed, bottle abandoned%n");//sysj\fillerController.sysj line: 45, column: 5
                S5007=5;
                if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 72, column: 8
                  System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 73, column: 5
                  fillDone.setPresent();//sysj\fillerController.sysj line: 74, column: 5
                  currsigs.addElement(fillDone);
                  S5007=6;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  if(faulted_thread_2){//sysj\fillerController.sysj line: 75, column: 11
                    System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 78, column: 5
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    S5007=6;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                }
              }
              else {
                S5040=1;
                if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 47, column: 13
                  bottleLost_thread_2 = true;//sysj\fillerController.sysj line: 48, column: 5
                  System.out.printf("[FC] phase 1 aborted: bottle no longer present - valves closed%n");//sysj\fillerController.sysj line: 49, column: 5
                  S5007=5;
                  if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 72, column: 8
                    System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 73, column: 5
                    fillDone.setPresent();//sysj\fillerController.sysj line: 74, column: 5
                    currsigs.addElement(fillDone);
                    S5007=6;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    if(faulted_thread_2){//sysj\fillerController.sysj line: 75, column: 11
                      System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 78, column: 5
                      active[2]=1;
                      ends[2]=1;
                      tdone[2]=1;
                    }
                    else {
                      S5007=6;
                      active[2]=1;
                      ends[2]=1;
                      tdone[2]=1;
                    }
                  }
                }
                else {
                  System.out.printf("phase 1 done, dosing to %d%n", bTarget_thread_2);//sysj\fillerController.sysj line: 51, column: 5
                  S5022=0;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 38, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 38, column: 6
              valve1Open.setPresent();//sysj\fillerController.sysj line: 39, column: 6
              currsigs.addElement(valve1Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 4 : 
            switch(S5040){
              case 0 : 
                S5007=0;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 1 : 
                switch(S5022){
                  case 0 : 
                    if(!levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 52, column: 12
                      S5022=1;
                      doseTargetMl.setPresent();//sysj\fillerController.sysj line: 55, column: 7
                      currsigs.addElement(doseTargetMl);
                      doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 55, column: 7
                      valve2Open.setPresent();//sysj\fillerController.sysj line: 56, column: 7
                      currsigs.addElement(valve2Open);
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
                    if(levelAtTarget.getprestatus() || stationFault_1.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 53, column: 12
                      if(stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 60, column: 14
                        faulted_thread_2 = true;//sysj\fillerController.sysj line: 61, column: 6
                        System.out.printf("[FC] phase 2 aborted by fault - valves closed, bottle abandoned%n");//sysj\fillerController.sysj line: 62, column: 6
                        S5007=5;
                        if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 72, column: 8
                          System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 73, column: 5
                          fillDone.setPresent();//sysj\fillerController.sysj line: 74, column: 5
                          currsigs.addElement(fillDone);
                          S5007=6;
                          active[2]=1;
                          ends[2]=1;
                          tdone[2]=1;
                        }
                        else {
                          if(faulted_thread_2){//sysj\fillerController.sysj line: 75, column: 11
                            System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 78, column: 5
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                          else {
                            S5007=6;
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                        }
                      }
                      else {
                        if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 64, column: 14
                          bottleLost_thread_2 = true;//sysj\fillerController.sysj line: 65, column: 6
                          System.out.printf("[FC] phase 2 aborted: bottle no longer present - valves closed%n");//sysj\fillerController.sysj line: 66, column: 6
                          S5007=5;
                          if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 72, column: 8
                            System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 73, column: 5
                            fillDone.setPresent();//sysj\fillerController.sysj line: 74, column: 5
                            currsigs.addElement(fillDone);
                            S5007=6;
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                          else {
                            if(faulted_thread_2){//sysj\fillerController.sysj line: 75, column: 11
                              System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 78, column: 5
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                            else {
                              S5007=6;
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                          }
                        }
                        else {
                          S5007=5;
                          if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 72, column: 8
                            System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 73, column: 5
                            fillDone.setPresent();//sysj\fillerController.sysj line: 74, column: 5
                            currsigs.addElement(fillDone);
                            S5007=6;
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                          else {
                            if(faulted_thread_2){//sysj\fillerController.sysj line: 75, column: 11
                              System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 78, column: 5
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                            else {
                              S5007=6;
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                          }
                        }
                      }
                    }
                    else {
                      doseTargetMl.setPresent();//sysj\fillerController.sysj line: 55, column: 7
                      currsigs.addElement(doseTargetMl);
                      doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 55, column: 7
                      valve2Open.setPresent();//sysj\fillerController.sysj line: 56, column: 7
                      currsigs.addElement(valve2Open);
                      active[2]=1;
                      ends[2]=1;
                      tdone[2]=1;
                    }
                    break;
                  
                }
                break;
              
            }
            break;
          
          case 5 : 
            if(!stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 79, column: 12
              System.out.printf("[FC] fault cleared, awaiting bottle removal%n");//sysj\fillerController.sysj line: 80, column: 5
              S5007=6;
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
          
          case 6 : 
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 83, column: 11
              System.out.printf("[FC] !bottleAtPos2 seen, holding fillReady%n");//sysj\fillerController.sysj line: 84, column: 4
              S5007=7;
              tFillReady_thread_2 = System.currentTimeMillis();//sysj\fillerController.sysj line: 86, column: 5
              if(System.currentTimeMillis() - tFillReady_thread_2 < 200){//sysj\fillerController.sysj line: 87, column: 12
                fillReady.setPresent();//sysj\fillerController.sysj line: 88, column: 6
                currsigs.addElement(fillReady);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\fillerController.sysj line: 87, column: 5
                S5007=0;
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
          
          case 7 : 
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 85, column: 11
              S5007=0;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              if(System.currentTimeMillis() - tFillReady_thread_2 < 200){//sysj\fillerController.sysj line: 87, column: 12
                fillReady.setPresent();//sysj\fillerController.sysj line: 88, column: 6
                currsigs.addElement(fillReady);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\fillerController.sysj line: 87, column: 5
                S5007=0;
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

  public void thread6134(int [] tdone, int [] ends){
        S6123=1;
    if(clearFaultM.getprestatus()){//sysj\fillerController.sysj line: 199, column: 13
      FillerFaultState.clearStationFault();//sysj\fillerController.sysj line: 200, column: 5
      if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 202, column: 4
        stationFault_1.setPresent();//sysj\fillerController.sysj line: 203, column: 5
        currsigs.addElement(stationFault_1);
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
    else {
      if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 202, column: 4
        stationFault_1.setPresent();//sysj\fillerController.sysj line: 203, column: 5
        currsigs.addElement(stationFault_1);
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
  }

  public void thread6133(int [] tdone, int [] ends){
        S6106=1;
    stalling_thread_8 = false;//sysj\fillerController.sysj line: 169, column: 3
    faulted_thread_8 = false;//sysj\fillerController.sysj line: 170, column: 3
    stallStart_thread_8 = 0;//sysj\fillerController.sysj line: 171, column: 3
    if(levelAtTarget.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 173, column: 13
      stalling_thread_8 = false;//sysj\fillerController.sysj line: 174, column: 5
      faulted_thread_8 = false;//sysj\fillerController.sysj line: 175, column: 5
      active[8]=1;
      ends[8]=1;
      tdone[8]=1;
    }
    else {
      if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerController.sysj line: 177, column: 13
        if(!stalling_thread_8){//sysj\fillerController.sysj line: 178, column: 9
          stalling_thread_8 = true;//sysj\fillerController.sysj line: 179, column: 6
          stallStart_thread_8 = System.currentTimeMillis();//sysj\fillerController.sysj line: 180, column: 6
          active[8]=1;
          ends[8]=1;
          tdone[8]=1;
        }
        else {
          if(!faulted_thread_8 && System.currentTimeMillis() - stallStart_thread_8 > 8000){//sysj\fillerController.sysj line: 181, column: 16
            System.out.printf("[FC] FAULT: stall detected, valve held open >8000ms without reaching target%n");//sysj\fillerController.sysj line: 182, column: 6
            fillFaultE.setPresent();//sysj\fillerController.sysj line: 183, column: 6
            currsigs.addElement(fillFaultE);
            FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 184, column: 6
            faulted_thread_8 = true;//sysj\fillerController.sysj line: 185, column: 6
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
      }
      else {
        stalling_thread_8 = false;//sysj\fillerController.sysj line: 188, column: 5
        faulted_thread_8 = false;//sysj\fillerController.sysj line: 189, column: 5
        active[8]=1;
        ends[8]=1;
        tdone[8]=1;
      }
    }
  }

  public void thread6132(int [] tdone, int [] ends){
        S6050=1;
    if(doseTargetMl.getprestatus() && fillLevelMl.getprestatus()){//sysj\fillerController.sysj line: 153, column: 13
      level_thread_7 = (Integer)(fillLevelMl.getpreval() == null ? null : ((Integer)fillLevelMl.getpreval()));//sysj\fillerController.sysj line: 154, column: 5
      target_thread_7 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerController.sysj line: 155, column: 5
      if(level_thread_7 > target_thread_7 && !FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 156, column: 9
        System.out.printf("[FC] FAULT: overfill detected, level=%d target=%d%n", level_thread_7, target_thread_7);//sysj\fillerController.sysj line: 157, column: 6
        fillFaultE.setPresent();//sysj\fillerController.sysj line: 158, column: 6
        currsigs.addElement(fillFaultE);
        FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 159, column: 6
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
    else {
      active[7]=1;
      ends[7]=1;
      tdone[7]=1;
    }
  }

  public void thread6131(int [] tdone, int [] ends){
        S6027=1;
    if(liquidARatio.getprestatus()){//sysj\fillerController.sysj line: 141, column: 13
      run.FillerRecipe.setRatio((Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())));//sysj\fillerController.sysj line: 141, column: 31
      if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 142, column: 13
        run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 143, column: 5
        totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 144, column: 5
        currsigs.addElement(totalVolumeMlE);
        totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 144, column: 5
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
    else {
      if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 142, column: 13
        run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 143, column: 5
        totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 144, column: 5
        currsigs.addElement(totalVolumeMlE);
        totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 144, column: 5
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
  }

  public void thread6130(int [] tdone, int [] ends){
        S6001=1;
    S5831=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread6129(int [] tdone, int [] ends){
        S5829=1;
    S5715=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread6128(int [] tdone, int [] ends){
        S5713=1;
    if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 98, column: 14
      fillDoneE.setPresent();//sysj\fillerController.sysj line: 99, column: 6
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

  public void thread6127(int [] tdone, int [] ends){
        S5705=1;
    S5007=0;
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
      switch(S6125){
        case 0 : 
          S6125=0;
          break RUN;
        
        case 1 : 
          S6125=2;
          S6125=2;
          stationFault_1.setClear();//sysj\fillerController.sysj line: 22, column: 2
          thread6127(tdone,ends);
          thread6128(tdone,ends);
          thread6129(tdone,ends);
          thread6130(tdone,ends);
          thread6131(tdone,ends);
          thread6132(tdone,ends);
          thread6133(tdone,ends);
          thread6134(tdone,ends);
          int biggest6135 = 0;
          if(ends[2]>=biggest6135){
            biggest6135=ends[2];
          }
          if(ends[3]>=biggest6135){
            biggest6135=ends[3];
          }
          if(ends[4]>=biggest6135){
            biggest6135=ends[4];
          }
          if(ends[5]>=biggest6135){
            biggest6135=ends[5];
          }
          if(ends[6]>=biggest6135){
            biggest6135=ends[6];
          }
          if(ends[7]>=biggest6135){
            biggest6135=ends[7];
          }
          if(ends[8]>=biggest6135){
            biggest6135=ends[8];
          }
          if(ends[9]>=biggest6135){
            biggest6135=ends[9];
          }
          if(biggest6135 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          stationFault_1.setClear();//sysj\fillerController.sysj line: 22, column: 2
          thread6136(tdone,ends);
          thread6137(tdone,ends);
          thread6138(tdone,ends);
          thread6139(tdone,ends);
          thread6140(tdone,ends);
          thread6141(tdone,ends);
          thread6142(tdone,ends);
          thread6143(tdone,ends);
          int biggest6144 = 0;
          if(ends[2]>=biggest6144){
            biggest6144=ends[2];
          }
          if(ends[3]>=biggest6144){
            biggest6144=ends[3];
          }
          if(ends[4]>=biggest6144){
            biggest6144=ends[4];
          }
          if(ends[5]>=biggest6144){
            biggest6144=ends[5];
          }
          if(ends[6]>=biggest6144){
            biggest6144=ends[6];
          }
          if(ends[7]>=biggest6144){
            biggest6144=ends[7];
          }
          if(ends[8]>=biggest6144){
            biggest6144=ends[8];
          }
          if(ends[9]>=biggest6144){
            biggest6144=ends[9];
          }
          if(biggest6144 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest6144 == 0){
            S6125=0;
            active[1]=0;
            ends[1]=0;
            S6125=0;
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
    stationFault_1 = new Signal();
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
          fillLevelMl.gethook();
          clearFaultM.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtPos2.setpreclear();
      liquidARatio.setpreclear();
      targetVolumeMl.setpreclear();
      levelAtTarget.setpreclear();
      fillLevelMl.setpreclear();
      clearFaultM.setpreclear();
      doseTargetMl.setpreclear();
      valve1Open.setpreclear();
      valve2Open.setpreclear();
      fillDone.setpreclear();
      fillDoneE.setpreclear();
      totalVolumeMlE.setpreclear();
      fillFaultE.setpreclear();
      fillDoneCoordE.setpreclear();
      fillReady.setpreclear();
      fillFaultOut.setpreclear();
      stationFault_1.setpreclear();
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
      dummyint = fillLevelMl.getStatus() ? fillLevelMl.setprepresent() : fillLevelMl.setpreclear();
      fillLevelMl.setpreval(fillLevelMl.getValue());
      fillLevelMl.setClear();
      dummyint = clearFaultM.getStatus() ? clearFaultM.setprepresent() : clearFaultM.setpreclear();
      clearFaultM.setpreval(clearFaultM.getValue());
      clearFaultM.setClear();
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
      fillFaultE.sethook();
      fillFaultE.setClear();
      fillDoneCoordE.sethook();
      fillDoneCoordE.setClear();
      fillReady.sethook();
      fillReady.setClear();
      fillFaultOut.sethook();
      fillFaultOut.setClear();
      stationFault_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtPos2.gethook();
        liquidARatio.gethook();
        targetVolumeMl.gethook();
        levelAtTarget.gethook();
        fillLevelMl.gethook();
        clearFaultM.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
