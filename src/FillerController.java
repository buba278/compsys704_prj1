import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.FillerFaultState;//sysj\fillerController.sysj line: 1, column: 1
import digitaltwin.PlantTwin;//sysj\fillerController.sysj line: 2, column: 1
import digitaltwin.TwinClient;//sysj\fillerController.sysj line: 3, column: 1

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
  private TwinClient twin_thread_2;//sysj\fillerController.sysj line: 27, column: 2
  private TwinClient twin_thread_5;//sysj\fillerController.sysj line: 141, column: 3
  private TwinClient twin_thread_8;//sysj\fillerController.sysj line: 186, column: 3
  private boolean stalling_thread_8;//sysj\fillerController.sysj line: 187, column: 3
  private boolean faulted_thread_8;//sysj\fillerController.sysj line: 188, column: 3
  private long stallStart_thread_8;//sysj\fillerController.sysj line: 189, column: 3
  private TwinClient twin_thread_9;//sysj\fillerController.sysj line: 218, column: 3
  private int aTarget_thread_2;//sysj\fillerController.sysj line: 34, column: 4
  private int bTarget_thread_2;//sysj\fillerController.sysj line: 35, column: 4
  private int ratio_thread_2;//sysj\fillerController.sysj line: 36, column: 4
  private String currentProductId_thread_2;//sysj\fillerController.sysj line: 37, column: 4
  private boolean faulted_thread_2;//sysj\fillerController.sysj line: 38, column: 4
  private boolean bottleLost_thread_2;//sysj\fillerController.sysj line: 39, column: 4
  private long tFillReady_thread_2;//sysj\fillerController.sysj line: 100, column: 5
  private TwinClient twin_thread_3;//sysj\fillerController.sysj line: 112, column: 4
  private long tFault_thread_4;//sysj\fillerController.sysj line: 130, column: 5
  private long tFillDoneCoord_thread_5;//sysj\fillerController.sysj line: 147, column: 5
  private int level_thread_7;//sysj\fillerController.sysj line: 171, column: 5
  private int target_thread_7;//sysj\fillerController.sysj line: 172, column: 5
  private int S15042 = 1;
  private int S14418 = 1;
  private int S13009 = 1;
  private int S13044 = 1;
  private int S13026 = 1;
  private int S14429 = 1;
  private int S14545 = 1;
  private int S14431 = 1;
  private int S14897 = 1;
  private int S14548 = 1;
  private int S14923 = 1;
  private int S14946 = 1;
  private int S15002 = 1;
  private int S15040 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread15060(int [] tdone, int [] ends){
        switch(S15040){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(clearFaultM.getprestatus()){//sysj\fillerController.sysj line: 220, column: 13
          twin_thread_9.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 221, column: 5
          FillerFaultState.clearStationFault();//sysj\fillerController.sysj line: 222, column: 5
          if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 224, column: 4
            stationFault_1.setPresent();//sysj\fillerController.sysj line: 225, column: 5
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
          if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 224, column: 4
            stationFault_1.setPresent();//sysj\fillerController.sysj line: 225, column: 5
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

  public void thread15059(int [] tdone, int [] ends){
        switch(S15002){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(levelAtTarget.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 191, column: 13
          stalling_thread_8 = false;//sysj\fillerController.sysj line: 192, column: 5
          faulted_thread_8 = false;//sysj\fillerController.sysj line: 193, column: 5
          active[8]=1;
          ends[8]=1;
          tdone[8]=1;
        }
        else {
          if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerController.sysj line: 195, column: 13
            if(!stalling_thread_8){//sysj\fillerController.sysj line: 196, column: 9
              stalling_thread_8 = true;//sysj\fillerController.sysj line: 197, column: 6
              stallStart_thread_8 = System.currentTimeMillis();//sysj\fillerController.sysj line: 198, column: 6
              active[8]=1;
              ends[8]=1;
              tdone[8]=1;
            }
            else {
              if(!faulted_thread_8 && System.currentTimeMillis() - stallStart_thread_8 > 8000){//sysj\fillerController.sysj line: 199, column: 16
                System.out.printf("[FC] FAULT: stall detected, valve held open >8000ms without reaching target%n");//sysj\fillerController.sysj line: 200, column: 6
                twin_thread_8.update(PlantTwin.State.ERROR, "Valve stall detected");//sysj\fillerController.sysj line: 201, column: 6
                fillFaultE.setPresent();//sysj\fillerController.sysj line: 202, column: 6
                currsigs.addElement(fillFaultE);
                FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 203, column: 6
                faulted_thread_8 = true;//sysj\fillerController.sysj line: 204, column: 6
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
            stalling_thread_8 = false;//sysj\fillerController.sysj line: 207, column: 5
            faulted_thread_8 = false;//sysj\fillerController.sysj line: 208, column: 5
            active[8]=1;
            ends[8]=1;
            tdone[8]=1;
          }
        }
        break;
      
    }
  }

  public void thread15058(int [] tdone, int [] ends){
        switch(S14946){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(doseTargetMl.getprestatus() && fillLevelMl.getprestatus()){//sysj\fillerController.sysj line: 170, column: 13
          level_thread_7 = (Integer)(fillLevelMl.getpreval() == null ? null : ((Integer)fillLevelMl.getpreval()));//sysj\fillerController.sysj line: 171, column: 5
          target_thread_7 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerController.sysj line: 172, column: 5
          if(level_thread_7 > target_thread_7 && !FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 173, column: 9
            System.out.printf("[FC] FAULT: overfill detected, level=%d target=%d%n", level_thread_7, target_thread_7);//sysj\fillerController.sysj line: 174, column: 6
            fillFaultE.setPresent();//sysj\fillerController.sysj line: 175, column: 6
            currsigs.addElement(fillFaultE);
            FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 176, column: 6
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

  public void thread15057(int [] tdone, int [] ends){
        switch(S14923){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(liquidARatio.getprestatus()){//sysj\fillerController.sysj line: 158, column: 13
          run.FillerRecipe.setRatio((Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())));//sysj\fillerController.sysj line: 158, column: 31
          if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 159, column: 13
            run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 160, column: 5
            totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 161, column: 5
            currsigs.addElement(totalVolumeMlE);
            totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 161, column: 5
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
          if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 159, column: 13
            run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 160, column: 5
            totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 161, column: 5
            currsigs.addElement(totalVolumeMlE);
            totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 161, column: 5
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

  public void thread15056(int [] tdone, int [] ends){
        switch(S14897){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S14548){
          case 0 : 
            if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 144, column: 11
              System.out.printf("[FC] fillDoneCoordE → coordinator (holding)%n");//sysj\fillerController.sysj line: 145, column: 4
              S14548=1;
              tFillDoneCoord_thread_5 = System.currentTimeMillis();//sysj\fillerController.sysj line: 147, column: 5
              if(System.currentTimeMillis() - tFillDoneCoord_thread_5 < 200){//sysj\fillerController.sysj line: 148, column: 12
                fillDoneCoordE.setPresent();//sysj\fillerController.sysj line: 149, column: 6
                currsigs.addElement(fillDoneCoordE);
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
              else {
                ends[5]=2;
                ;//sysj\fillerController.sysj line: 148, column: 5
                twin_thread_5.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 143, column: 4
                S14548=0;
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
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 146, column: 11
              twin_thread_5.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 143, column: 4
              S14548=0;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              if(System.currentTimeMillis() - tFillDoneCoord_thread_5 < 200){//sysj\fillerController.sysj line: 148, column: 12
                fillDoneCoordE.setPresent();//sysj\fillerController.sysj line: 149, column: 6
                currsigs.addElement(fillDoneCoordE);
                active[5]=1;
                ends[5]=1;
                tdone[5]=1;
              }
              else {
                ends[5]=2;
                ;//sysj\fillerController.sysj line: 148, column: 5
                twin_thread_5.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 143, column: 4
                S14548=0;
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

  public void thread15055(int [] tdone, int [] ends){
        switch(S14545){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S14431){
          case 0 : 
            if(stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 127, column: 11
              System.out.printf("[FC] fillFaultOut → coordinator (holding)%n");//sysj\fillerController.sysj line: 128, column: 4
              S14431=1;
              tFault_thread_4 = System.currentTimeMillis();//sysj\fillerController.sysj line: 130, column: 5
              if(System.currentTimeMillis() - tFault_thread_4 < 200){//sysj\fillerController.sysj line: 131, column: 12
                fillFaultOut.setPresent();//sysj\fillerController.sysj line: 132, column: 6
                currsigs.addElement(fillFaultOut);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\fillerController.sysj line: 131, column: 5
                S14431=2;
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
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 129, column: 11
              S14431=2;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(System.currentTimeMillis() - tFault_thread_4 < 200){//sysj\fillerController.sysj line: 131, column: 12
                fillFaultOut.setPresent();//sysj\fillerController.sysj line: 132, column: 6
                currsigs.addElement(fillFaultOut);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\fillerController.sysj line: 131, column: 5
                S14431=2;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
          case 2 : 
            if(!stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 136, column: 11
              S14431=0;
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

  public void thread15054(int [] tdone, int [] ends){
        switch(S14429){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        twin_thread_3 = new TwinClient("filler", "127.0.0.1", 9090);//sysj\fillerController.sysj line: 112, column: 4
        if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 113, column: 14
          fillDoneE.setPresent();//sysj\fillerController.sysj line: 114, column: 6
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

  public void thread15053(int [] tdone, int [] ends){
        switch(S14418){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S13009){
          case 0 : 
            if(!stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 31, column: 11
              S13009=1;
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
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 32, column: 11
              aTarget_thread_2 = run.FillerRecipe.getVolume() * run.FillerRecipe.getRatio() / 100;//sysj\fillerController.sysj line: 34, column: 4
              bTarget_thread_2 = run.FillerRecipe.getVolume();//sysj\fillerController.sysj line: 35, column: 4
              ratio_thread_2 = run.FillerRecipe.getRatio();//sysj\fillerController.sysj line: 36, column: 4
              currentProductId_thread_2 = twin_thread_2.createProduct(bTarget_thread_2, ratio_thread_2);//sysj\fillerController.sysj line: 37, column: 4
              faulted_thread_2 = false;//sysj\fillerController.sysj line: 38, column: 4
              bottleLost_thread_2 = false;//sysj\fillerController.sysj line: 39, column: 4
              S13009=2;
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
            if(!levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 41, column: 11
              twin_thread_2.update(PlantTwin.State.IN_PROGRESS);//sysj\fillerController.sysj line: 42, column: 4
              System.out.printf("phase 1 start: aTarget=%d bTarget=%d%n", aTarget_thread_2, bTarget_thread_2);//sysj\fillerController.sysj line: 43, column: 4
              S13009=3;
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 46, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 46, column: 6
              valve1Open.setPresent();//sysj\fillerController.sysj line: 47, column: 6
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
            if(levelAtTarget.getprestatus() || stationFault_1.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 44, column: 11
              S13009=4;
              if(stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 51, column: 13
                S13044=0;
                faulted_thread_2 = true;//sysj\fillerController.sysj line: 52, column: 5
                System.out.printf("[FC] phase 1 aborted by fault - valves closed, bottle abandoned%n");//sysj\fillerController.sysj line: 53, column: 5
                twin_thread_2.update(PlantTwin.State.ERROR);//sysj\fillerController.sysj line: 54, column: 5
                twin_thread_2.recordEvent("Fill aborted (Phase 1 fault)", currentProductId_thread_2);//sysj\fillerController.sysj line: 55, column: 5
                S13009=5;
                if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 85, column: 8
                  System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 86, column: 5
                  twin_thread_2.recordEvent("Bottle filled", currentProductId_thread_2);//sysj\fillerController.sysj line: 87, column: 5
                  fillDone.setPresent();//sysj\fillerController.sysj line: 88, column: 5
                  currsigs.addElement(fillDone);
                  S13009=6;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  if(faulted_thread_2){//sysj\fillerController.sysj line: 89, column: 11
                    System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 92, column: 5
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    S13009=6;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                }
              }
              else {
                S13044=1;
                if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 57, column: 13
                  bottleLost_thread_2 = true;//sysj\fillerController.sysj line: 58, column: 5
                  System.out.printf("[FC] phase 1 aborted: bottle no longer present - valves closed%n");//sysj\fillerController.sysj line: 59, column: 5
                  S13009=5;
                  if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 85, column: 8
                    System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 86, column: 5
                    twin_thread_2.recordEvent("Bottle filled", currentProductId_thread_2);//sysj\fillerController.sysj line: 87, column: 5
                    fillDone.setPresent();//sysj\fillerController.sysj line: 88, column: 5
                    currsigs.addElement(fillDone);
                    S13009=6;
                    active[2]=1;
                    ends[2]=1;
                    tdone[2]=1;
                  }
                  else {
                    if(faulted_thread_2){//sysj\fillerController.sysj line: 89, column: 11
                      System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 92, column: 5
                      active[2]=1;
                      ends[2]=1;
                      tdone[2]=1;
                    }
                    else {
                      S13009=6;
                      active[2]=1;
                      ends[2]=1;
                      tdone[2]=1;
                    }
                  }
                }
                else {
                  System.out.printf("phase 1 done, dosing to %d%n", bTarget_thread_2);//sysj\fillerController.sysj line: 61, column: 5
                  S13026=0;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              doseTargetMl.setPresent();//sysj\fillerController.sysj line: 46, column: 6
              currsigs.addElement(doseTargetMl);
              doseTargetMl.setValue(aTarget_thread_2);//sysj\fillerController.sysj line: 46, column: 6
              valve1Open.setPresent();//sysj\fillerController.sysj line: 47, column: 6
              currsigs.addElement(valve1Open);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 4 : 
            switch(S13044){
              case 0 : 
                twin_thread_2.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 30, column: 4
                S13009=0;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 1 : 
                switch(S13026){
                  case 0 : 
                    if(!levelAtTarget.getprestatus()){//sysj\fillerController.sysj line: 62, column: 12
                      S13026=1;
                      doseTargetMl.setPresent();//sysj\fillerController.sysj line: 65, column: 7
                      currsigs.addElement(doseTargetMl);
                      doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 65, column: 7
                      valve2Open.setPresent();//sysj\fillerController.sysj line: 66, column: 7
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
                    if(levelAtTarget.getprestatus() || stationFault_1.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 63, column: 12
                      if(stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 70, column: 14
                        faulted_thread_2 = true;//sysj\fillerController.sysj line: 71, column: 6
                        System.out.printf("[FC] phase 2 aborted by fault - valves closed, bottle abandoned%n");//sysj\fillerController.sysj line: 72, column: 6
                        twin_thread_2.update(PlantTwin.State.ERROR);//sysj\fillerController.sysj line: 73, column: 6
                        twin_thread_2.recordEvent("Fill aborted (Phase 2 fault)", currentProductId_thread_2);//sysj\fillerController.sysj line: 74, column: 6
                        S13009=5;
                        if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 85, column: 8
                          System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 86, column: 5
                          twin_thread_2.recordEvent("Bottle filled", currentProductId_thread_2);//sysj\fillerController.sysj line: 87, column: 5
                          fillDone.setPresent();//sysj\fillerController.sysj line: 88, column: 5
                          currsigs.addElement(fillDone);
                          S13009=6;
                          active[2]=1;
                          ends[2]=1;
                          tdone[2]=1;
                        }
                        else {
                          if(faulted_thread_2){//sysj\fillerController.sysj line: 89, column: 11
                            System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 92, column: 5
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                          else {
                            S13009=6;
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                        }
                      }
                      else {
                        if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 76, column: 14
                          bottleLost_thread_2 = true;//sysj\fillerController.sysj line: 77, column: 6
                          System.out.printf("[FC] phase 2 aborted: bottle no longer present - valves closed%n");//sysj\fillerController.sysj line: 78, column: 6
                          twin_thread_2.recordEvent("Bottle removed prematurely", currentProductId_thread_2);//sysj\fillerController.sysj line: 79, column: 6
                          S13009=5;
                          if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 85, column: 8
                            System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 86, column: 5
                            twin_thread_2.recordEvent("Bottle filled", currentProductId_thread_2);//sysj\fillerController.sysj line: 87, column: 5
                            fillDone.setPresent();//sysj\fillerController.sysj line: 88, column: 5
                            currsigs.addElement(fillDone);
                            S13009=6;
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                          else {
                            if(faulted_thread_2){//sysj\fillerController.sysj line: 89, column: 11
                              System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 92, column: 5
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                            else {
                              S13009=6;
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                          }
                        }
                        else {
                          S13009=5;
                          if(!faulted_thread_2 && !bottleLost_thread_2){//sysj\fillerController.sysj line: 85, column: 8
                            System.out.printf("phase 2 done, emitting fillDone, awaiting !bottleAtPos2%n");//sysj\fillerController.sysj line: 86, column: 5
                            twin_thread_2.recordEvent("Bottle filled", currentProductId_thread_2);//sysj\fillerController.sysj line: 87, column: 5
                            fillDone.setPresent();//sysj\fillerController.sysj line: 88, column: 5
                            currsigs.addElement(fillDone);
                            S13009=6;
                            active[2]=1;
                            ends[2]=1;
                            tdone[2]=1;
                          }
                          else {
                            if(faulted_thread_2){//sysj\fillerController.sysj line: 89, column: 11
                              System.out.printf("[FC] awaiting fault clear before returning to rotation%n");//sysj\fillerController.sysj line: 92, column: 5
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                            else {
                              S13009=6;
                              active[2]=1;
                              ends[2]=1;
                              tdone[2]=1;
                            }
                          }
                        }
                      }
                    }
                    else {
                      doseTargetMl.setPresent();//sysj\fillerController.sysj line: 65, column: 7
                      currsigs.addElement(doseTargetMl);
                      doseTargetMl.setValue(bTarget_thread_2);//sysj\fillerController.sysj line: 65, column: 7
                      valve2Open.setPresent();//sysj\fillerController.sysj line: 66, column: 7
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
            if(!stationFault_1.getprestatus()){//sysj\fillerController.sysj line: 93, column: 12
              System.out.printf("[FC] fault cleared, awaiting bottle removal%n");//sysj\fillerController.sysj line: 94, column: 5
              S13009=6;
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
            if(!bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 97, column: 11
              System.out.printf("[FC] !bottleAtPos2 seen, holding fillReady%n");//sysj\fillerController.sysj line: 98, column: 4
              S13009=7;
              tFillReady_thread_2 = System.currentTimeMillis();//sysj\fillerController.sysj line: 100, column: 5
              if(System.currentTimeMillis() - tFillReady_thread_2 < 200){//sysj\fillerController.sysj line: 101, column: 12
                fillReady.setPresent();//sysj\fillerController.sysj line: 102, column: 6
                currsigs.addElement(fillReady);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\fillerController.sysj line: 101, column: 5
                twin_thread_2.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 30, column: 4
                S13009=0;
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
            if(bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 99, column: 11
              twin_thread_2.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 30, column: 4
              S13009=0;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              if(System.currentTimeMillis() - tFillReady_thread_2 < 200){//sysj\fillerController.sysj line: 101, column: 12
                fillReady.setPresent();//sysj\fillerController.sysj line: 102, column: 6
                currsigs.addElement(fillReady);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                ends[2]=2;
                ;//sysj\fillerController.sysj line: 101, column: 5
                twin_thread_2.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 30, column: 4
                S13009=0;
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

  public void thread15051(int [] tdone, int [] ends){
        S15040=1;
    twin_thread_9 = new TwinClient("filler", "127.0.0.1", 9090);//sysj\fillerController.sysj line: 218, column: 3
    if(clearFaultM.getprestatus()){//sysj\fillerController.sysj line: 220, column: 13
      twin_thread_9.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 221, column: 5
      FillerFaultState.clearStationFault();//sysj\fillerController.sysj line: 222, column: 5
      if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 224, column: 4
        stationFault_1.setPresent();//sysj\fillerController.sysj line: 225, column: 5
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
      if(FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 224, column: 4
        stationFault_1.setPresent();//sysj\fillerController.sysj line: 225, column: 5
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

  public void thread15050(int [] tdone, int [] ends){
        S15002=1;
    twin_thread_8 = new TwinClient("filler", "127.0.0.1", 9090);//sysj\fillerController.sysj line: 186, column: 3
    stalling_thread_8 = false;//sysj\fillerController.sysj line: 187, column: 3
    faulted_thread_8 = false;//sysj\fillerController.sysj line: 188, column: 3
    stallStart_thread_8 = 0;//sysj\fillerController.sysj line: 189, column: 3
    if(levelAtTarget.getprestatus() || !bottleAtPos2.getprestatus()){//sysj\fillerController.sysj line: 191, column: 13
      stalling_thread_8 = false;//sysj\fillerController.sysj line: 192, column: 5
      faulted_thread_8 = false;//sysj\fillerController.sysj line: 193, column: 5
      active[8]=1;
      ends[8]=1;
      tdone[8]=1;
    }
    else {
      if(valve1Open.getprestatus() || valve2Open.getprestatus()){//sysj\fillerController.sysj line: 195, column: 13
        if(!stalling_thread_8){//sysj\fillerController.sysj line: 196, column: 9
          stalling_thread_8 = true;//sysj\fillerController.sysj line: 197, column: 6
          stallStart_thread_8 = System.currentTimeMillis();//sysj\fillerController.sysj line: 198, column: 6
          active[8]=1;
          ends[8]=1;
          tdone[8]=1;
        }
        else {
          if(!faulted_thread_8 && System.currentTimeMillis() - stallStart_thread_8 > 8000){//sysj\fillerController.sysj line: 199, column: 16
            System.out.printf("[FC] FAULT: stall detected, valve held open >8000ms without reaching target%n");//sysj\fillerController.sysj line: 200, column: 6
            twin_thread_8.update(PlantTwin.State.ERROR, "Valve stall detected");//sysj\fillerController.sysj line: 201, column: 6
            fillFaultE.setPresent();//sysj\fillerController.sysj line: 202, column: 6
            currsigs.addElement(fillFaultE);
            FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 203, column: 6
            faulted_thread_8 = true;//sysj\fillerController.sysj line: 204, column: 6
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
        stalling_thread_8 = false;//sysj\fillerController.sysj line: 207, column: 5
        faulted_thread_8 = false;//sysj\fillerController.sysj line: 208, column: 5
        active[8]=1;
        ends[8]=1;
        tdone[8]=1;
      }
    }
  }

  public void thread15049(int [] tdone, int [] ends){
        S14946=1;
    if(doseTargetMl.getprestatus() && fillLevelMl.getprestatus()){//sysj\fillerController.sysj line: 170, column: 13
      level_thread_7 = (Integer)(fillLevelMl.getpreval() == null ? null : ((Integer)fillLevelMl.getpreval()));//sysj\fillerController.sysj line: 171, column: 5
      target_thread_7 = (Integer)(doseTargetMl.getpreval() == null ? null : ((Integer)doseTargetMl.getpreval()));//sysj\fillerController.sysj line: 172, column: 5
      if(level_thread_7 > target_thread_7 && !FillerFaultState.isStationFaulted()){//sysj\fillerController.sysj line: 173, column: 9
        System.out.printf("[FC] FAULT: overfill detected, level=%d target=%d%n", level_thread_7, target_thread_7);//sysj\fillerController.sysj line: 174, column: 6
        fillFaultE.setPresent();//sysj\fillerController.sysj line: 175, column: 6
        currsigs.addElement(fillFaultE);
        FillerFaultState.raiseStationFault();//sysj\fillerController.sysj line: 176, column: 6
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

  public void thread15048(int [] tdone, int [] ends){
        S14923=1;
    if(liquidARatio.getprestatus()){//sysj\fillerController.sysj line: 158, column: 13
      run.FillerRecipe.setRatio((Integer)(liquidARatio.getpreval() == null ? null : ((Integer)liquidARatio.getpreval())));//sysj\fillerController.sysj line: 158, column: 31
      if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 159, column: 13
        run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 160, column: 5
        totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 161, column: 5
        currsigs.addElement(totalVolumeMlE);
        totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 161, column: 5
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
      if(targetVolumeMl.getprestatus()){//sysj\fillerController.sysj line: 159, column: 13
        run.FillerRecipe.setVolume((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 160, column: 5
        totalVolumeMlE.setPresent();//sysj\fillerController.sysj line: 161, column: 5
        currsigs.addElement(totalVolumeMlE);
        totalVolumeMlE.setValue((Integer)(targetVolumeMl.getpreval() == null ? null : ((Integer)targetVolumeMl.getpreval())));//sysj\fillerController.sysj line: 161, column: 5
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

  public void thread15047(int [] tdone, int [] ends){
        S14897=1;
    twin_thread_5 = new TwinClient("filler", "127.0.0.1", 9090);//sysj\fillerController.sysj line: 141, column: 3
    twin_thread_5.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 143, column: 4
    S14548=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread15046(int [] tdone, int [] ends){
        S14545=1;
    S14431=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread15045(int [] tdone, int [] ends){
        S14429=1;
    twin_thread_3 = new TwinClient("filler", "127.0.0.1", 9090);//sysj\fillerController.sysj line: 112, column: 4
    if(fillDone.getprestatus()){//sysj\fillerController.sysj line: 113, column: 14
      fillDoneE.setPresent();//sysj\fillerController.sysj line: 114, column: 6
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

  public void thread15044(int [] tdone, int [] ends){
        S14418=1;
    twin_thread_2 = new TwinClient("filler", "127.0.0.1", 9090);//sysj\fillerController.sysj line: 27, column: 2
    twin_thread_2.update(PlantTwin.State.IDLE);//sysj\fillerController.sysj line: 30, column: 4
    S13009=0;
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
      switch(S15042){
        case 0 : 
          S15042=0;
          break RUN;
        
        case 1 : 
          S15042=2;
          S15042=2;
          stationFault_1.setClear();//sysj\fillerController.sysj line: 24, column: 2
          thread15044(tdone,ends);
          thread15045(tdone,ends);
          thread15046(tdone,ends);
          thread15047(tdone,ends);
          thread15048(tdone,ends);
          thread15049(tdone,ends);
          thread15050(tdone,ends);
          thread15051(tdone,ends);
          int biggest15052 = 0;
          if(ends[2]>=biggest15052){
            biggest15052=ends[2];
          }
          if(ends[3]>=biggest15052){
            biggest15052=ends[3];
          }
          if(ends[4]>=biggest15052){
            biggest15052=ends[4];
          }
          if(ends[5]>=biggest15052){
            biggest15052=ends[5];
          }
          if(ends[6]>=biggest15052){
            biggest15052=ends[6];
          }
          if(ends[7]>=biggest15052){
            biggest15052=ends[7];
          }
          if(ends[8]>=biggest15052){
            biggest15052=ends[8];
          }
          if(ends[9]>=biggest15052){
            biggest15052=ends[9];
          }
          if(biggest15052 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          stationFault_1.setClear();//sysj\fillerController.sysj line: 24, column: 2
          thread15053(tdone,ends);
          thread15054(tdone,ends);
          thread15055(tdone,ends);
          thread15056(tdone,ends);
          thread15057(tdone,ends);
          thread15058(tdone,ends);
          thread15059(tdone,ends);
          thread15060(tdone,ends);
          int biggest15061 = 0;
          if(ends[2]>=biggest15061){
            biggest15061=ends[2];
          }
          if(ends[3]>=biggest15061){
            biggest15061=ends[3];
          }
          if(ends[4]>=biggest15061){
            biggest15061=ends[4];
          }
          if(ends[5]>=biggest15061){
            biggest15061=ends[5];
          }
          if(ends[6]>=biggest15061){
            biggest15061=ends[6];
          }
          if(ends[7]>=biggest15061){
            biggest15061=ends[7];
          }
          if(ends[8]>=biggest15061){
            biggest15061=ends[8];
          }
          if(ends[9]>=biggest15061){
            biggest15061=ends[9];
          }
          if(biggest15061 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest15061 == 0){
            S15042=0;
            active[1]=0;
            ends[1]=0;
            S15042=0;
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
