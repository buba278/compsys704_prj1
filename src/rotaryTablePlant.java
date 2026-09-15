import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.RotaryTableGUI;//sysj\rotaryTablePlant.sysj line: 2, column: 1
import run.RotaryConveyorBridge;//sysj\rotaryTablePlant.sysj line: 3, column: 1
import run.RotaryCapperBridge;//sysj\rotaryTablePlant.sysj line: 4, column: 1
import run.RotaryFillerBridge;//sysj\rotaryTablePlant.sysj line: 5, column: 1
import run.RotaryLidBridge;//sysj\rotaryTablePlant.sysj line: 6, column: 1

public class rotaryTablePlant extends ClockDomain{
  public rotaryTablePlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal rotaryTableTrigger = new Signal("rotaryTableTrigger", Signal.INPUT);
  public Signal start = new Signal("start", Signal.INPUT);
  public Signal capperTakenAck = new Signal("capperTakenAck", Signal.INPUT);
  public Signal fillerTakenAck = new Signal("fillerTakenAck", Signal.INPUT);
  public Signal lidPlacedAck = new Signal("lidPlacedAck", Signal.INPUT);
  public Signal tableAlignedWithSensor = new Signal("tableAlignedWithSensor", Signal.OUTPUT);
  public Signal bottleAtPos5 = new Signal("bottleAtPos5", Signal.OUTPUT);
  public Signal capOnBottleAtPos1 = new Signal("capOnBottleAtPos1", Signal.OUTPUT);
  public Signal tableBusy = new Signal("tableBusy", Signal.OUTPUT);
  public Signal bottleStageE = new Signal("bottleStageE", Signal.OUTPUT);
  public Signal tableAlignedWithSensorE = new Signal("tableAlignedWithSensorE", Signal.OUTPUT);
  public Signal bottleAtPos5E = new Signal("bottleAtPos5E", Signal.OUTPUT);
  public Signal capOnBottleAtPos1E = new Signal("capOnBottleAtPos1E", Signal.OUTPUT);
  public Signal rotaryTableTriggerE = new Signal("rotaryTableTriggerE", Signal.OUTPUT);
  private long STAGE_VIEW_DELAY_MS_thread_3;//sysj\rotaryTablePlant.sysj line: 77, column: 3
  private long tFillStart_thread_3;//sysj\rotaryTablePlant.sysj line: 87, column: 4
  private long t2_thread_3;//sysj\rotaryTablePlant.sysj line: 89, column: 5
  private long tGap1_thread_3;//sysj\rotaryTablePlant.sysj line: 110, column: 5
  private long tLidStart_thread_3;//sysj\rotaryTablePlant.sysj line: 133, column: 4
  private long t5_thread_3;//sysj\rotaryTablePlant.sysj line: 135, column: 5
  private long sinceStart_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
  private boolean enableFlag_thread_3;//sysj\rotaryTablePlant.sysj line: 144, column: 6
  private long tGap2_thread_3;//sysj\rotaryTablePlant.sysj line: 161, column: 5
  private long tCapStart_thread_3;//sysj\rotaryTablePlant.sysj line: 171, column: 4
  private long t0_thread_3;//sysj\rotaryTablePlant.sysj line: 173, column: 5
  private long t1_thread_3;//sysj\rotaryTablePlant.sysj line: 188, column: 5
  private long tGap3_thread_3;//sysj\rotaryTablePlant.sysj line: 201, column: 5
  private long t3_thread_3;//sysj\rotaryTablePlant.sysj line: 213, column: 4
  private long t4_thread_3;//sysj\rotaryTablePlant.sysj line: 226, column: 5
  private int S44187 = 1;
  private int S19816 = 1;
  private int S19815 = 1;
  private int S19789 = 1;
  private int S31950 = 1;
  private int S19818 = 1;
  private int S31984 = 1;
  private int S31958 = 1;
  private int S31966 = 1;
  private int S31974 = 1;
  private int S31982 = 1;
  
  private int[] ends = new int[9];
  private int[] tdone = new int[9];
  
  public void thread44204(int [] tdone, int [] ends){
        switch(S31982){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 247, column: 24
          rotaryTableTriggerE.setPresent();//sysj\rotaryTablePlant.sysj line: 247, column: 44
          currsigs.addElement(rotaryTableTriggerE);
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

  public void thread44203(int [] tdone, int [] ends){
        switch(S31974){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTablePlant.sysj line: 245, column: 24
          capOnBottleAtPos1E.setPresent();//sysj\rotaryTablePlant.sysj line: 245, column: 43
          currsigs.addElement(capOnBottleAtPos1E);
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

  public void thread44202(int [] tdone, int [] ends){
        switch(S31966){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 243, column: 24
          bottleAtPos5E.setPresent();//sysj\rotaryTablePlant.sysj line: 243, column: 38
          currsigs.addElement(bottleAtPos5E);
          active[6]=1;
          ends[6]=1;
          tdone[6]=1;
        }
        else {
          active[6]=1;
          ends[6]=1;
          tdone[6]=1;
        }
        break;
      
    }
  }

  public void thread44201(int [] tdone, int [] ends){
        switch(S31958){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 241, column: 24
          tableAlignedWithSensorE.setPresent();//sysj\rotaryTablePlant.sysj line: 241, column: 48
          currsigs.addElement(tableAlignedWithSensorE);
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
      
    }
  }

  public void thread44200(int [] tdone, int [] ends){
        switch(S31984){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        thread44201(tdone,ends);
        thread44202(tdone,ends);
        thread44203(tdone,ends);
        thread44204(tdone,ends);
        int biggest44205 = 0;
        if(ends[5]>=biggest44205){
          biggest44205=ends[5];
        }
        if(ends[6]>=biggest44205){
          biggest44205=ends[6];
        }
        if(ends[7]>=biggest44205){
          biggest44205=ends[7];
        }
        if(ends[8]>=biggest44205){
          biggest44205=ends[8];
        }
        if(biggest44205 == 1){
          active[4]=1;
          ends[4]=1;
          tdone[4]=1;
        }
        //FINXME code
        if(biggest44205 == 0){
          S31984=0;
          active[4]=0;
          ends[4]=0;
          tdone[4]=1;
        }
        break;
      
    }
  }

  public void thread44199(int [] tdone, int [] ends){
        switch(S31950){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S19818){
          case 0 : 
            if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 79, column: 10
              System.out.printf("[RT] bottle aligned, offering to Filler%n");//sysj\rotaryTablePlant.sysj line: 80, column: 4
              tFillStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 87, column: 4
              S19818=1;
              t2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 89, column: 5
              if(System.currentTimeMillis() - t2_thread_3 < 30000){//sysj\rotaryTablePlant.sysj line: 90, column: 12
                RotaryFillerBridge.setBottleReadyForFiller(true);//sysj\rotaryTablePlant.sysj line: 91, column: 6
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 92, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 93, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 93, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 90, column: 5
                RotaryFillerBridge.setBottleReadyForFiller(false);//sysj\rotaryTablePlant.sysj line: 97, column: 4
                S19818=2;
                if(fillerTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 98, column: 12
                  System.out.printf("[RT] fillerTakenAck received after %dms%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 99, column: 5
                  tGap1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 110, column: 5
                  S19818=3;
                  if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
                    System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
                    tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
                    S19818=4;
                    t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
                    if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                      RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                      sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                      enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                      RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                      RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                      RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                      if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                        System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                else {
                  System.out.printf("[RT] TIMED OUT waiting for fillerTakenAck after %dms - moving on unfilled%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 101, column: 5
                  tGap1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 110, column: 5
                  S19818=3;
                  if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
                    System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
                    tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
                    S19818=4;
                    t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
                    if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                      RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                      sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                      enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                      RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                      RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                      RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                      if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                        System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            else {
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            if(fillerTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 88, column: 10
              RotaryFillerBridge.setBottleReadyForFiller(false);//sysj\rotaryTablePlant.sysj line: 97, column: 4
              S19818=2;
              if(fillerTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 98, column: 12
                System.out.printf("[RT] fillerTakenAck received after %dms%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 99, column: 5
                tGap1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 110, column: 5
                S19818=3;
                if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
                  System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
                  tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
                  S19818=4;
                  t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
                  if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                    RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                    sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                    enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                    RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                    RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                    RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                    if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                      System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                      tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                      S19818=5;
                      if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                        System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                        tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                        S19818=6;
                        t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                        if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                          RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                          RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                          S19818=7;
                          if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                            System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                            t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                            if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                              currsigs.addElement(capOnBottleAtPos1);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                          else {
                            System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                            tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                            S19818=8;
                            if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                              System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                              t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                              S19818=9;
                              if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                currsigs.addElement(bottleAtPos5);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                S19818=10;
                                if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                  System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                  S19818=11;
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                    else {
                      System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                      tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                      S19818=5;
                      if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                        System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                        tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                        S19818=6;
                        t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                        if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                          RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                          RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                          S19818=7;
                          if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                            System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                            t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                            if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                              currsigs.addElement(capOnBottleAtPos1);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                          else {
                            System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                            tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                            S19818=8;
                            if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                              System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                              t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                              S19818=9;
                              if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                currsigs.addElement(bottleAtPos5);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                S19818=10;
                                if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                  System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                  S19818=11;
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
              else {
                System.out.printf("[RT] TIMED OUT waiting for fillerTakenAck after %dms - moving on unfilled%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 101, column: 5
                tGap1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 110, column: 5
                S19818=3;
                if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
                  System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
                  tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
                  S19818=4;
                  t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
                  if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                    RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                    sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                    enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                    RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                    RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                    RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                    if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                      System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                      tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                      S19818=5;
                      if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                        System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                        tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                        S19818=6;
                        t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                        if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                          RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                          RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                          S19818=7;
                          if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                            System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                            t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                            if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                              currsigs.addElement(capOnBottleAtPos1);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                          else {
                            System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                            tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                            S19818=8;
                            if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                              System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                              t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                              S19818=9;
                              if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                currsigs.addElement(bottleAtPos5);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                S19818=10;
                                if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                  System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                  S19818=11;
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                    else {
                      System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                      tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                      S19818=5;
                      if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                        System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                        tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                        S19818=6;
                        t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                        if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                          RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                          RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                          S19818=7;
                          if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                            System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                            t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                            if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                              currsigs.addElement(capOnBottleAtPos1);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                          else {
                            System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                            tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                            S19818=8;
                            if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                              System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                              t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                              S19818=9;
                              if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                currsigs.addElement(bottleAtPos5);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                S19818=10;
                                if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                  System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                  S19818=11;
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            else {
              if(System.currentTimeMillis() - t2_thread_3 < 30000){//sysj\rotaryTablePlant.sysj line: 90, column: 12
                RotaryFillerBridge.setBottleReadyForFiller(true);//sysj\rotaryTablePlant.sysj line: 91, column: 6
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 92, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 93, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 93, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 90, column: 5
                RotaryFillerBridge.setBottleReadyForFiller(false);//sysj\rotaryTablePlant.sysj line: 97, column: 4
                S19818=2;
                if(fillerTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 98, column: 12
                  System.out.printf("[RT] fillerTakenAck received after %dms%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 99, column: 5
                  tGap1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 110, column: 5
                  S19818=3;
                  if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
                    System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
                    tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
                    S19818=4;
                    t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
                    if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                      RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                      sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                      enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                      RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                      RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                      RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                      if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                        System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                else {
                  System.out.printf("[RT] TIMED OUT waiting for fillerTakenAck after %dms - moving on unfilled%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 101, column: 5
                  tGap1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 110, column: 5
                  S19818=3;
                  if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
                    System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
                    tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
                    S19818=4;
                    t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
                    if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                      RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                      sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                      enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                      RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                      RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                      RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                      if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                        System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                        tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                        S19818=5;
                        if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                          System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                          tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                          S19818=6;
                          t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                          if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                            RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                            RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                            S19818=7;
                            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                              System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                              t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                              if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                                capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                                currsigs.addElement(capOnBottleAtPos1);
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                                S19818=8;
                                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                  S19818=9;
                                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                    currsigs.addElement(bottleAtPos5);
                                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                    currsigs.addElement(tableBusy);
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                    S19818=10;
                                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      currsigs.addElement(bottleStageE);
                                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                    else {
                                      ends[3]=2;
                                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                      S19818=11;
                                      active[3]=1;
                                      ends[3]=1;
                                      tdone[3]=1;
                                    }
                                  }
                                }
                              }
                            }
                            else {
                              System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                              S19818=8;
                              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                                currsigs.addElement(tableBusy);
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                                S19818=9;
                                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                                  currsigs.addElement(bottleAtPos5);
                                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                                  currsigs.addElement(tableBusy);
                                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  currsigs.addElement(bottleStageE);
                                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                                  active[3]=1;
                                  ends[3]=1;
                                  tdone[3]=1;
                                }
                                else {
                                  ends[3]=2;
                                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                                  S19818=10;
                                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    currsigs.addElement(bottleStageE);
                                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                  else {
                                    ends[3]=2;
                                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                    S19818=11;
                                    active[3]=1;
                                    ends[3]=1;
                                    tdone[3]=1;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            break;
          
          case 2 : 
            S19818=0;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
          case 3 : 
            if(System.currentTimeMillis() - tGap1_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 111, column: 12
              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 112, column: 6
              currsigs.addElement(tableBusy);
              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 113, column: 6
              currsigs.addElement(bottleStageE);
              bottleStageE.setValue(2);//sysj\rotaryTablePlant.sysj line: 113, column: 6
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\rotaryTablePlant.sysj line: 111, column: 5
              System.out.printf("[RT] offering to Lid Placer%n");//sysj\rotaryTablePlant.sysj line: 132, column: 4
              tLidStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 133, column: 4
              S19818=4;
              t5_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 135, column: 5
              if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                  System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                  tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                  S19818=5;
                  if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                    System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                    tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                    S19818=6;
                    t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                    if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                      RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                      RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                      S19818=7;
                      if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                        System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                        t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                        if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                          capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                          currsigs.addElement(capOnBottleAtPos1);
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                          tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                          S19818=8;
                          if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                            System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                            t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                            S19818=9;
                            if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                              bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                              currsigs.addElement(bottleAtPos5);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                              RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                              t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                              S19818=10;
                              if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                S19818=11;
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                        tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                        S19818=8;
                        if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                          System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                          t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                          S19818=9;
                          if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                            bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                            currsigs.addElement(bottleAtPos5);
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                            RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                            t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                            S19818=10;
                            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                              S19818=11;
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                          }
                        }
                      }
                    }
                  }
                }
                else {
                  System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                  tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                  S19818=5;
                  if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                    System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                    tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                    S19818=6;
                    t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                    if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                      RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                      RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                      S19818=7;
                      if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                        System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                        t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                        if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                          capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                          currsigs.addElement(capOnBottleAtPos1);
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                          tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                          S19818=8;
                          if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                            System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                            t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                            S19818=9;
                            if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                              bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                              currsigs.addElement(bottleAtPos5);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                              RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                              t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                              S19818=10;
                              if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                S19818=11;
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                        tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                        S19818=8;
                        if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                          System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                          t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                          S19818=9;
                          if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                            bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                            currsigs.addElement(bottleAtPos5);
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                            RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                            t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                            S19818=10;
                            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                              S19818=11;
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            break;
          
          case 4 : 
            if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 134, column: 10
              RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
              RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
              if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                S19818=5;
                if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                  System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                  tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                  S19818=6;
                  t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                  if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                    RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                    RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                    S19818=7;
                    if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                      System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                      t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                      if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                        capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                        currsigs.addElement(capOnBottleAtPos1);
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                        tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                        S19818=8;
                        if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                          System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                          t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                          S19818=9;
                          if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                            bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                            currsigs.addElement(bottleAtPos5);
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                            RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                            t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                            S19818=10;
                            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                              S19818=11;
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                          }
                        }
                      }
                    }
                    else {
                      System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                      tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                      S19818=8;
                      if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                        System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                        t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                        S19818=9;
                        if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                          bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                          currsigs.addElement(bottleAtPos5);
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                          RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                          RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                          t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                          S19818=10;
                          if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                            System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                            S19818=11;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                        }
                      }
                    }
                  }
                }
              }
              else {
                System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                S19818=5;
                if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                  System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                  tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                  S19818=6;
                  t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                  if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                    RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                    RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                    S19818=7;
                    if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                      System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                      t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                      if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                        capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                        currsigs.addElement(capOnBottleAtPos1);
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                        tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                        S19818=8;
                        if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                          System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                          t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                          S19818=9;
                          if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                            bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                            currsigs.addElement(bottleAtPos5);
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                            RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                            t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                            S19818=10;
                            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                              S19818=11;
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                          }
                        }
                      }
                    }
                    else {
                      System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                      tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                      S19818=8;
                      if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                        System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                        t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                        S19818=9;
                        if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                          bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                          currsigs.addElement(bottleAtPos5);
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                          RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                          RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                          t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                          S19818=10;
                          if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                            System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                            S19818=11;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            else {
              if(System.currentTimeMillis() - t5_thread_3 < 15000){//sysj\rotaryTablePlant.sysj line: 136, column: 12
                RotaryLidBridge.setRequest(true);//sysj\rotaryTablePlant.sysj line: 137, column: 6
                sinceStart_thread_3 = System.currentTimeMillis() - t5_thread_3;//sysj\rotaryTablePlant.sysj line: 143, column: 6
                enableFlag_thread_3 = (sinceStart_thread_3 % 200) < 100;//sysj\rotaryTablePlant.sysj line: 144, column: 6
                RotaryLidBridge.setEnable(enableFlag_thread_3);//sysj\rotaryTablePlant.sysj line: 145, column: 6
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 146, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 147, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 147, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 136, column: 5
                RotaryLidBridge.setRequest(false);//sysj\rotaryTablePlant.sysj line: 151, column: 4
                RotaryLidBridge.setEnable(false);//sysj\rotaryTablePlant.sysj line: 152, column: 4
                if(lidPlacedAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 153, column: 12
                  System.out.printf("[RT] lidPlacedAck received after %dms%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 154, column: 5
                  tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                  S19818=5;
                  if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                    System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                    tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                    S19818=6;
                    t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                    if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                      RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                      RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                      S19818=7;
                      if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                        System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                        t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                        if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                          capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                          currsigs.addElement(capOnBottleAtPos1);
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                          tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                          S19818=8;
                          if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                            System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                            t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                            S19818=9;
                            if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                              bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                              currsigs.addElement(bottleAtPos5);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                              RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                              t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                              S19818=10;
                              if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                S19818=11;
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                        tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                        S19818=8;
                        if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                          System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                          t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                          S19818=9;
                          if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                            bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                            currsigs.addElement(bottleAtPos5);
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                            RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                            t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                            S19818=10;
                            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                              S19818=11;
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                          }
                        }
                      }
                    }
                  }
                }
                else {
                  System.out.printf("[RT] TIMED OUT waiting for lidPlacedAck after %dms - moving on without a lid%n", System.currentTimeMillis() - tLidStart_thread_3);//sysj\rotaryTablePlant.sysj line: 156, column: 5
                  tGap2_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 161, column: 5
                  S19818=5;
                  if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
                    System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
                    tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
                    S19818=6;
                    t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
                    if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                      RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                      RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                      S19818=7;
                      if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                        System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                        t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                        if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                          capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                          currsigs.addElement(capOnBottleAtPos1);
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                          tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                          S19818=8;
                          if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                            System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                            t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                            S19818=9;
                            if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                              bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                              currsigs.addElement(bottleAtPos5);
                              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                              currsigs.addElement(tableBusy);
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                              RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                              RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                              t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                              S19818=10;
                              if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                currsigs.addElement(bottleStageE);
                                bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                              else {
                                ends[3]=2;
                                ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                                System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                                S19818=11;
                                active[3]=1;
                                ends[3]=1;
                                tdone[3]=1;
                              }
                            }
                          }
                        }
                      }
                      else {
                        System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                        tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                        S19818=8;
                        if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                          tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                          currsigs.addElement(tableBusy);
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                          System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                          t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                          S19818=9;
                          if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                            bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                            currsigs.addElement(bottleAtPos5);
                            tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                            currsigs.addElement(tableBusy);
                            bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            currsigs.addElement(bottleStageE);
                            bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                            RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            ends[3]=2;
                            ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                            RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                            t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                            S19818=10;
                            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              currsigs.addElement(bottleStageE);
                              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                            else {
                              ends[3]=2;
                              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                              S19818=11;
                              active[3]=1;
                              ends[3]=1;
                              tdone[3]=1;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            break;
          
          case 5 : 
            if(System.currentTimeMillis() - tGap2_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 162, column: 12
              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 163, column: 6
              currsigs.addElement(tableBusy);
              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 164, column: 6
              currsigs.addElement(bottleStageE);
              bottleStageE.setValue(4);//sysj\rotaryTablePlant.sysj line: 164, column: 6
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\rotaryTablePlant.sysj line: 162, column: 5
              System.out.printf("[RT] offering to Capper%n");//sysj\rotaryTablePlant.sysj line: 170, column: 4
              tCapStart_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 171, column: 4
              S19818=6;
              t0_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 173, column: 5
              if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                S19818=7;
                if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                  System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                  t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                  if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                    capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                    currsigs.addElement(capOnBottleAtPos1);
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                    tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                    S19818=8;
                    if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                      System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                      t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                      S19818=9;
                      if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                        bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                        currsigs.addElement(bottleAtPos5);
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                        RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                        RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                        t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                        S19818=10;
                        if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                          System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                          S19818=11;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                      }
                    }
                  }
                }
                else {
                  System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                  tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                  S19818=8;
                  if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                    System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                    t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                    S19818=9;
                    if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                      bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                      currsigs.addElement(bottleAtPos5);
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                      RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                      RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                      t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                      S19818=10;
                      if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                        System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                        S19818=11;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                }
              }
            }
            break;
          
          case 6 : 
            if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 172, column: 10
              RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
              S19818=7;
              if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                  capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                  currsigs.addElement(capOnBottleAtPos1);
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                  tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                  S19818=8;
                  if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                    System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                    t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                    S19818=9;
                    if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                      bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                      currsigs.addElement(bottleAtPos5);
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                      RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                      RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                      t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                      S19818=10;
                      if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                        System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                        S19818=11;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                }
              }
              else {
                System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                S19818=8;
                if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                  System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                  t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                  S19818=9;
                  if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                    bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                    currsigs.addElement(bottleAtPos5);
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                    RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                    RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                    t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                    S19818=10;
                    if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                      System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                      S19818=11;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                }
              }
            }
            else {
              if(System.currentTimeMillis() - t0_thread_3 < 10000){//sysj\rotaryTablePlant.sysj line: 174, column: 12
                RotaryCapperBridge.setBottleAtPos4(true);//sysj\rotaryTablePlant.sysj line: 175, column: 6
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 176, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 177, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 177, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 174, column: 5
                RotaryCapperBridge.setBottleAtPos4(false);//sysj\rotaryTablePlant.sysj line: 181, column: 4
                S19818=7;
                if(capperTakenAck.getprestatus()){//sysj\rotaryTablePlant.sysj line: 182, column: 12
                  System.out.printf("[RT] capperTakenAck received after %dms%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 183, column: 5
                  t1_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 188, column: 5
                  if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
                    capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
                    currsigs.addElement(capOnBottleAtPos1);
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
                    tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                    S19818=8;
                    if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                      System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                      t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                      S19818=9;
                      if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                        bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                        currsigs.addElement(bottleAtPos5);
                        tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                        currsigs.addElement(tableBusy);
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                        RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                        RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                        t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                        S19818=10;
                        if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                          bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                          currsigs.addElement(bottleStageE);
                          bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          ends[3]=2;
                          ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                          System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                          S19818=11;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                      }
                    }
                  }
                }
                else {
                  System.out.printf("[RT] TIMED OUT waiting for capperTakenAck after %dms - moving on uncapped%n", System.currentTimeMillis() - tCapStart_thread_3);//sysj\rotaryTablePlant.sysj line: 196, column: 5
                  tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
                  S19818=8;
                  if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                    tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                    currsigs.addElement(tableBusy);
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                    System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                    t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                    S19818=9;
                    if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                      bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                      currsigs.addElement(bottleAtPos5);
                      tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                      currsigs.addElement(tableBusy);
                      bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                      currsigs.addElement(bottleStageE);
                      bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                      RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                      RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                      t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                      S19818=10;
                      if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                        bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                        currsigs.addElement(bottleStageE);
                        bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                        System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                        S19818=11;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                }
              }
            }
            break;
          
          case 7 : 
            if(System.currentTimeMillis() - t1_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 189, column: 12
              capOnBottleAtPos1.setPresent();//sysj\rotaryTablePlant.sysj line: 190, column: 6
              currsigs.addElement(capOnBottleAtPos1);
              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 191, column: 6
              currsigs.addElement(tableBusy);
              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 192, column: 6
              currsigs.addElement(bottleStageE);
              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 192, column: 6
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\rotaryTablePlant.sysj line: 189, column: 5
              tGap3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 201, column: 5
              S19818=8;
              if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
                System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
                t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
                S19818=9;
                if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                  bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                  currsigs.addElement(bottleAtPos5);
                  tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                  currsigs.addElement(tableBusy);
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                  RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                  RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                  t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                  S19818=10;
                  if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                    bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                    currsigs.addElement(bottleStageE);
                    bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                    System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                    S19818=11;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                }
              }
            }
            break;
          
          case 8 : 
            if(System.currentTimeMillis() - tGap3_thread_3 < STAGE_VIEW_DELAY_MS_thread_3){//sysj\rotaryTablePlant.sysj line: 202, column: 12
              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 203, column: 6
              currsigs.addElement(tableBusy);
              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 204, column: 6
              currsigs.addElement(bottleStageE);
              bottleStageE.setValue(1);//sysj\rotaryTablePlant.sysj line: 204, column: 6
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\rotaryTablePlant.sysj line: 202, column: 5
              System.out.printf("[RT] handing off to Conveyor at Pos 5%n");//sysj\rotaryTablePlant.sysj line: 212, column: 4
              t3_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 213, column: 4
              S19818=9;
              if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
                bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
                currsigs.addElement(bottleAtPos5);
                tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
                currsigs.addElement(tableBusy);
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
                RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
                RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
                t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
                S19818=10;
                if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                  bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                  currsigs.addElement(bottleStageE);
                  bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                  System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                  S19818=11;
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
              }
            }
            break;
          
          case 9 : 
            if(System.currentTimeMillis() - t3_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 214, column: 11
              bottleAtPos5.setPresent();//sysj\rotaryTablePlant.sysj line: 215, column: 5
              currsigs.addElement(bottleAtPos5);
              tableBusy.setPresent();//sysj\rotaryTablePlant.sysj line: 216, column: 5
              currsigs.addElement(tableBusy);
              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 217, column: 5
              currsigs.addElement(bottleStageE);
              bottleStageE.setValue(3);//sysj\rotaryTablePlant.sysj line: 217, column: 5
              RotaryConveyorBridge.setBottleFromTable(true);//sysj\rotaryTablePlant.sysj line: 218, column: 5
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\rotaryTablePlant.sysj line: 214, column: 4
              RotaryConveyorBridge.setBottleFromTable(false);//sysj\rotaryTablePlant.sysj line: 221, column: 4
              t4_thread_3 = System.currentTimeMillis();//sysj\rotaryTablePlant.sysj line: 226, column: 5
              S19818=10;
              if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
                bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
                currsigs.addElement(bottleStageE);
                bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
                System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
                S19818=11;
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
            }
            break;
          
          case 10 : 
            if(System.currentTimeMillis() - t4_thread_3 < 200){//sysj\rotaryTablePlant.sysj line: 227, column: 12
              bottleStageE.setPresent();//sysj\rotaryTablePlant.sysj line: 228, column: 6
              currsigs.addElement(bottleStageE);
              bottleStageE.setValue(0);//sysj\rotaryTablePlant.sysj line: 228, column: 6
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\rotaryTablePlant.sysj line: 227, column: 5
              System.out.printf("[RT] bottle cycle complete, %dms total%n", System.currentTimeMillis() - tFillStart_thread_3);//sysj\rotaryTablePlant.sysj line: 232, column: 4
              S19818=11;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 11 : 
            if(!tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 236, column: 10
              S19818=0;
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
        break;
      
    }
  }

  public void thread44198(int [] tdone, int [] ends){
        switch(S19816){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S19815){
          case 0 : 
            if(start.getprestatus()){//sysj\rotaryTablePlant.sysj line: 25, column: 9
              S19815=1;
              S19789=0;
              tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 28, column: 5
              currsigs.addElement(tableAlignedWithSensor);
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
            switch(S19789){
              case 0 : 
                if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 27, column: 10
                  S19789=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 28, column: 5
                  currsigs.addElement(tableAlignedWithSensor);
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                break;
              
              case 1 : 
                S19789=1;
                S19789=2;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 2 : 
                S19789=2;
                S19789=3;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
              case 3 : 
                S19789=3;
                S19789=0;
                tableAlignedWithSensor.setPresent();//sysj\rotaryTablePlant.sysj line: 28, column: 5
                currsigs.addElement(tableAlignedWithSensor);
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
                break;
              
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread44195(int [] tdone, int [] ends){
        S31982=1;
    if(rotaryTableTrigger.getprestatus()){//sysj\rotaryTablePlant.sysj line: 247, column: 24
      rotaryTableTriggerE.setPresent();//sysj\rotaryTablePlant.sysj line: 247, column: 44
      currsigs.addElement(rotaryTableTriggerE);
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

  public void thread44194(int [] tdone, int [] ends){
        S31974=1;
    if(capOnBottleAtPos1.getprestatus()){//sysj\rotaryTablePlant.sysj line: 245, column: 24
      capOnBottleAtPos1E.setPresent();//sysj\rotaryTablePlant.sysj line: 245, column: 43
      currsigs.addElement(capOnBottleAtPos1E);
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

  public void thread44193(int [] tdone, int [] ends){
        S31966=1;
    if(bottleAtPos5.getprestatus()){//sysj\rotaryTablePlant.sysj line: 243, column: 24
      bottleAtPos5E.setPresent();//sysj\rotaryTablePlant.sysj line: 243, column: 38
      currsigs.addElement(bottleAtPos5E);
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

  public void thread44192(int [] tdone, int [] ends){
        S31958=1;
    if(tableAlignedWithSensor.getprestatus()){//sysj\rotaryTablePlant.sysj line: 241, column: 24
      tableAlignedWithSensorE.setPresent();//sysj\rotaryTablePlant.sysj line: 241, column: 48
      currsigs.addElement(tableAlignedWithSensorE);
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

  public void thread44191(int [] tdone, int [] ends){
        S31984=1;
    thread44192(tdone,ends);
    thread44193(tdone,ends);
    thread44194(tdone,ends);
    thread44195(tdone,ends);
    int biggest44196 = 0;
    if(ends[5]>=biggest44196){
      biggest44196=ends[5];
    }
    if(ends[6]>=biggest44196){
      biggest44196=ends[6];
    }
    if(ends[7]>=biggest44196){
      biggest44196=ends[7];
    }
    if(ends[8]>=biggest44196){
      biggest44196=ends[8];
    }
    if(biggest44196 == 1){
      active[4]=1;
      ends[4]=1;
      tdone[4]=1;
    }
  }

  public void thread44190(int [] tdone, int [] ends){
        S31950=1;
    STAGE_VIEW_DELAY_MS_thread_3 = 1500;//sysj\rotaryTablePlant.sysj line: 77, column: 3
    S19818=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread44189(int [] tdone, int [] ends){
        S19816=1;
    S19815=0;
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
      switch(S44187){
        case 0 : 
          S44187=0;
          break RUN;
        
        case 1 : 
          S44187=2;
          S44187=2;
          new Thread(new RotaryTableGUI()).start();//sysj\rotaryTablePlant.sysj line: 21, column: 2
          thread44189(tdone,ends);
          thread44190(tdone,ends);
          thread44191(tdone,ends);
          int biggest44197 = 0;
          if(ends[2]>=biggest44197){
            biggest44197=ends[2];
          }
          if(ends[3]>=biggest44197){
            biggest44197=ends[3];
          }
          if(ends[4]>=biggest44197){
            biggest44197=ends[4];
          }
          if(biggest44197 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread44198(tdone,ends);
          thread44199(tdone,ends);
          thread44200(tdone,ends);
          int biggest44206 = 0;
          if(ends[2]>=biggest44206){
            biggest44206=ends[2];
          }
          if(ends[3]>=biggest44206){
            biggest44206=ends[3];
          }
          if(ends[4]>=biggest44206){
            biggest44206=ends[4];
          }
          if(biggest44206 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest44206 == 0){
            S44187=0;
            active[1]=0;
            ends[1]=0;
            S44187=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
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
          rotaryTableTrigger.gethook();
          start.gethook();
          capperTakenAck.gethook();
          fillerTakenAck.gethook();
          lidPlacedAck.gethook();
          df = true;
        }
        runClockDomain();
      }
      rotaryTableTrigger.setpreclear();
      start.setpreclear();
      capperTakenAck.setpreclear();
      fillerTakenAck.setpreclear();
      lidPlacedAck.setpreclear();
      tableAlignedWithSensor.setpreclear();
      bottleAtPos5.setpreclear();
      capOnBottleAtPos1.setpreclear();
      tableBusy.setpreclear();
      bottleStageE.setpreclear();
      tableAlignedWithSensorE.setpreclear();
      bottleAtPos5E.setpreclear();
      capOnBottleAtPos1E.setpreclear();
      rotaryTableTriggerE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = rotaryTableTrigger.getStatus() ? rotaryTableTrigger.setprepresent() : rotaryTableTrigger.setpreclear();
      rotaryTableTrigger.setpreval(rotaryTableTrigger.getValue());
      rotaryTableTrigger.setClear();
      dummyint = start.getStatus() ? start.setprepresent() : start.setpreclear();
      start.setpreval(start.getValue());
      start.setClear();
      dummyint = capperTakenAck.getStatus() ? capperTakenAck.setprepresent() : capperTakenAck.setpreclear();
      capperTakenAck.setpreval(capperTakenAck.getValue());
      capperTakenAck.setClear();
      dummyint = fillerTakenAck.getStatus() ? fillerTakenAck.setprepresent() : fillerTakenAck.setpreclear();
      fillerTakenAck.setpreval(fillerTakenAck.getValue());
      fillerTakenAck.setClear();
      dummyint = lidPlacedAck.getStatus() ? lidPlacedAck.setprepresent() : lidPlacedAck.setpreclear();
      lidPlacedAck.setpreval(lidPlacedAck.getValue());
      lidPlacedAck.setClear();
      tableAlignedWithSensor.sethook();
      tableAlignedWithSensor.setClear();
      bottleAtPos5.sethook();
      bottleAtPos5.setClear();
      capOnBottleAtPos1.sethook();
      capOnBottleAtPos1.setClear();
      tableBusy.sethook();
      tableBusy.setClear();
      bottleStageE.sethook();
      bottleStageE.setClear();
      tableAlignedWithSensorE.sethook();
      tableAlignedWithSensorE.setClear();
      bottleAtPos5E.sethook();
      bottleAtPos5E.setClear();
      capOnBottleAtPos1E.sethook();
      capOnBottleAtPos1E.setClear();
      rotaryTableTriggerE.sethook();
      rotaryTableTriggerE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        rotaryTableTrigger.gethook();
        start.gethook();
        capperTakenAck.gethook();
        fillerTakenAck.gethook();
        lidPlacedAck.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
