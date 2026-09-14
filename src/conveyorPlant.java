import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.ConveyorGUI;//sysj\conveyorPlant.sysj line: 1, column: 1

public class conveyorPlant extends ClockDomain{
  public conveyorPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal motConveyorOnOff = new Signal("motConveyorOnOff", Signal.INPUT);
  public Signal enable = new Signal("enable", Signal.INPUT);
  public Signal loadBottle = new Signal("loadBottle", Signal.INPUT);
  public Signal bottleFromTable = new Signal("bottleFromTable", Signal.INPUT);
  public Signal sortDone = new Signal("sortDone", Signal.INPUT);
  public Signal pos1TakenAck = new Signal("pos1TakenAck", Signal.INPUT);
  public Signal bottleAtPos1 = new Signal("bottleAtPos1", Signal.OUTPUT);
  public Signal bottleLeftPos5 = new Signal("bottleLeftPos5", Signal.OUTPUT);
  public Signal bottleAtPos1E = new Signal("bottleAtPos1E", Signal.OUTPUT);
  public Signal bottleLeftPos5E = new Signal("bottleLeftPos5E", Signal.OUTPUT);
  public Signal motorOnE = new Signal("motorOnE", Signal.OUTPUT);
  public Signal bottleEnteredE = new Signal("bottleEnteredE", Signal.OUTPUT);
  public Signal bottleReceivedE = new Signal("bottleReceivedE", Signal.OUTPUT);
  public Signal leftMovingE = new Signal("leftMovingE", Signal.OUTPUT);
  public Signal rightMovingE = new Signal("rightMovingE", Signal.OUTPUT);
  private Signal leftMoving_1;
  private Signal rightMoving_1;
  private long tEnter_thread_3;//sysj\conveyorPlant.sysj line: 44, column: 5
  private long tL1_thread_3;//sysj\conveyorPlant.sysj line: 62, column: 23
  private long tL2_thread_3;//sysj\conveyorPlant.sysj line: 63, column: 23
  private long tL3_thread_3;//sysj\conveyorPlant.sysj line: 64, column: 23
  private long tL4_thread_3;//sysj\conveyorPlant.sysj line: 65, column: 23
  private long tL5_thread_3;//sysj\conveyorPlant.sysj line: 66, column: 23
  private long tL6_thread_3;//sysj\conveyorPlant.sysj line: 67, column: 23
  private long tL7_thread_3;//sysj\conveyorPlant.sysj line: 68, column: 23
  private long tL8_thread_3;//sysj\conveyorPlant.sysj line: 69, column: 23
  private long tReceived_thread_4;//sysj\conveyorPlant.sysj line: 97, column: 5
  private long tR1_thread_4;//sysj\conveyorPlant.sysj line: 105, column: 24
  private long tR2_thread_4;//sysj\conveyorPlant.sysj line: 106, column: 24
  private long tR3_thread_4;//sysj\conveyorPlant.sysj line: 107, column: 24
  private long tR4_thread_4;//sysj\conveyorPlant.sysj line: 108, column: 24
  private long tR5_thread_4;//sysj\conveyorPlant.sysj line: 109, column: 24
  private long tR6_thread_4;//sysj\conveyorPlant.sysj line: 110, column: 24
  private long tR7_thread_4;//sysj\conveyorPlant.sysj line: 111, column: 24
  private long tR8_thread_4;//sysj\conveyorPlant.sysj line: 112, column: 24
  private int S5530 = 1;
  private int S3708 = 1;
  private int S3681 = 1;
  private int S4145 = 1;
  private int S3712 = 1;
  private int S4582 = 1;
  private int S4149 = 1;
  private int S4600 = 1;
  private int S4590 = 1;
  private int S4598 = 1;
  
  private int[] ends = new int[8];
  private int[] tdone = new int[8];
  
  public void thread5545(int [] tdone, int [] ends){
        switch(S4598){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(bottleLeftPos5.getprestatus()){//sysj\conveyorPlant.sysj line: 125, column: 24
          bottleLeftPos5E.setPresent();//sysj\conveyorPlant.sysj line: 125, column: 40
          currsigs.addElement(bottleLeftPos5E);
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

  public void thread5544(int [] tdone, int [] ends){
        switch(S4590){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(bottleAtPos1.getprestatus()){//sysj\conveyorPlant.sysj line: 123, column: 24
          bottleAtPos1E.setPresent();//sysj\conveyorPlant.sysj line: 123, column: 38
          currsigs.addElement(bottleAtPos1E);
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

  public void thread5543(int [] tdone, int [] ends){
        switch(S4600){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        thread5544(tdone,ends);
        thread5545(tdone,ends);
        int biggest5546 = 0;
        if(ends[6]>=biggest5546){
          biggest5546=ends[6];
        }
        if(ends[7]>=biggest5546){
          biggest5546=ends[7];
        }
        if(biggest5546 == 1){
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        //FINXME code
        if(biggest5546 == 0){
          S4600=0;
          active[5]=0;
          ends[5]=0;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread5542(int [] tdone, int [] ends){
        switch(S4582){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S4149){
          case 0 : 
            if(bottleFromTable.getprestatus()){//sysj\conveyorPlant.sysj line: 91, column: 10
              S4149=1;
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
            if(enable.getprestatus()){//sysj\conveyorPlant.sysj line: 92, column: 10
              tReceived_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 97, column: 5
              S4149=2;
              if(System.currentTimeMillis() - tReceived_thread_4 < 50){//sysj\conveyorPlant.sysj line: 98, column: 12
                bottleReceivedE.setPresent();//sysj\conveyorPlant.sysj line: 98, column: 59
                currsigs.addElement(bottleReceivedE);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\conveyorPlant.sysj line: 98, column: 5
                rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 105, column: 4
                currsigs.addElement(rightMoving_1);
                tR1_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 105, column: 24
                S4149=3;
                if(System.currentTimeMillis() - tR1_thread_4 < 60){//sysj\conveyorPlant.sysj line: 105, column: 70
                  rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 105, column: 111
                  currsigs.addElement(rightMovingE);
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
                else {
                  ends[4]=2;
                  ;//sysj\conveyorPlant.sysj line: 105, column: 63
                  S4149=4;
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
              }
            }
            else {
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 2 : 
            if(System.currentTimeMillis() - tReceived_thread_4 < 50){//sysj\conveyorPlant.sysj line: 98, column: 12
              bottleReceivedE.setPresent();//sysj\conveyorPlant.sysj line: 98, column: 59
              currsigs.addElement(bottleReceivedE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 98, column: 5
              rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 105, column: 4
              currsigs.addElement(rightMoving_1);
              tR1_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 105, column: 24
              S4149=3;
              if(System.currentTimeMillis() - tR1_thread_4 < 60){//sysj\conveyorPlant.sysj line: 105, column: 70
                rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 105, column: 111
                currsigs.addElement(rightMovingE);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\conveyorPlant.sysj line: 105, column: 63
                S4149=4;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
          case 3 : 
            if(System.currentTimeMillis() - tR1_thread_4 < 60){//sysj\conveyorPlant.sysj line: 105, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 105, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 105, column: 63
              S4149=4;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 4 : 
            S4149=4;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 106, column: 4
            currsigs.addElement(rightMoving_1);
            tR2_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 106, column: 24
            S4149=5;
            if(System.currentTimeMillis() - tR2_thread_4 < 60){//sysj\conveyorPlant.sysj line: 106, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 106, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 106, column: 63
              S4149=6;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 5 : 
            if(System.currentTimeMillis() - tR2_thread_4 < 60){//sysj\conveyorPlant.sysj line: 106, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 106, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 106, column: 63
              S4149=6;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 6 : 
            S4149=6;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 4
            currsigs.addElement(rightMoving_1);
            tR3_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 107, column: 24
            S4149=7;
            if(System.currentTimeMillis() - tR3_thread_4 < 60){//sysj\conveyorPlant.sysj line: 107, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 107, column: 63
              S4149=8;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 7 : 
            if(System.currentTimeMillis() - tR3_thread_4 < 60){//sysj\conveyorPlant.sysj line: 107, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 107, column: 63
              S4149=8;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 8 : 
            S4149=8;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 108, column: 4
            currsigs.addElement(rightMoving_1);
            tR4_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 108, column: 24
            S4149=9;
            if(System.currentTimeMillis() - tR4_thread_4 < 60){//sysj\conveyorPlant.sysj line: 108, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 108, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 108, column: 63
              S4149=10;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 9 : 
            if(System.currentTimeMillis() - tR4_thread_4 < 60){//sysj\conveyorPlant.sysj line: 108, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 108, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 108, column: 63
              S4149=10;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 10 : 
            S4149=10;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 109, column: 4
            currsigs.addElement(rightMoving_1);
            tR5_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 109, column: 24
            S4149=11;
            if(System.currentTimeMillis() - tR5_thread_4 < 60){//sysj\conveyorPlant.sysj line: 109, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 109, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 109, column: 63
              S4149=12;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 11 : 
            if(System.currentTimeMillis() - tR5_thread_4 < 60){//sysj\conveyorPlant.sysj line: 109, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 109, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 109, column: 63
              S4149=12;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 12 : 
            S4149=12;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 110, column: 4
            currsigs.addElement(rightMoving_1);
            tR6_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 110, column: 24
            S4149=13;
            if(System.currentTimeMillis() - tR6_thread_4 < 60){//sysj\conveyorPlant.sysj line: 110, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 110, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 110, column: 63
              S4149=14;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 13 : 
            if(System.currentTimeMillis() - tR6_thread_4 < 60){//sysj\conveyorPlant.sysj line: 110, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 110, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 110, column: 63
              S4149=14;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 14 : 
            S4149=14;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 111, column: 4
            currsigs.addElement(rightMoving_1);
            tR7_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 111, column: 24
            S4149=15;
            if(System.currentTimeMillis() - tR7_thread_4 < 60){//sysj\conveyorPlant.sysj line: 111, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 111, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 111, column: 63
              S4149=16;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 15 : 
            if(System.currentTimeMillis() - tR7_thread_4 < 60){//sysj\conveyorPlant.sysj line: 111, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 111, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 111, column: 63
              S4149=16;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 16 : 
            S4149=16;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 112, column: 4
            currsigs.addElement(rightMoving_1);
            tR8_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 112, column: 24
            S4149=17;
            if(System.currentTimeMillis() - tR8_thread_4 < 60){//sysj\conveyorPlant.sysj line: 112, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 112, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 112, column: 63
              S4149=18;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 17 : 
            if(System.currentTimeMillis() - tR8_thread_4 < 60){//sysj\conveyorPlant.sysj line: 112, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 112, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 112, column: 63
              S4149=18;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 18 : 
            S4149=18;
            S4149=19;
            bottleLeftPos5.setPresent();//sysj\conveyorPlant.sysj line: 117, column: 5
            currsigs.addElement(bottleLeftPos5);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 19 : 
            if(sortDone.getprestatus()){//sysj\conveyorPlant.sysj line: 116, column: 10
              S4149=0;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              bottleLeftPos5.setPresent();//sysj\conveyorPlant.sysj line: 117, column: 5
              currsigs.addElement(bottleLeftPos5);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread5541(int [] tdone, int [] ends){
        switch(S4145){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S3712){
          case 0 : 
            if(loadBottle.getprestatus()){//sysj\conveyorPlant.sysj line: 36, column: 10
              S3712=1;
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
            if(enable.getprestatus()){//sysj\conveyorPlant.sysj line: 37, column: 10
              tEnter_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 44, column: 5
              S3712=2;
              if(System.currentTimeMillis() - tEnter_thread_3 < 50){//sysj\conveyorPlant.sysj line: 45, column: 12
                bottleEnteredE.setPresent();//sysj\conveyorPlant.sysj line: 45, column: 56
                currsigs.addElement(bottleEnteredE);
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\conveyorPlant.sysj line: 45, column: 5
                leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 62, column: 4
                currsigs.addElement(leftMoving_1);
                tL1_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 62, column: 23
                S3712=3;
                if(System.currentTimeMillis() - tL1_thread_3 < 60){//sysj\conveyorPlant.sysj line: 62, column: 69
                  leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 62, column: 110
                  currsigs.addElement(leftMovingE);
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\conveyorPlant.sysj line: 62, column: 62
                  S3712=4;
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
              }
            }
            else {
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            if(System.currentTimeMillis() - tEnter_thread_3 < 50){//sysj\conveyorPlant.sysj line: 45, column: 12
              bottleEnteredE.setPresent();//sysj\conveyorPlant.sysj line: 45, column: 56
              currsigs.addElement(bottleEnteredE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 45, column: 5
              leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 62, column: 4
              currsigs.addElement(leftMoving_1);
              tL1_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 62, column: 23
              S3712=3;
              if(System.currentTimeMillis() - tL1_thread_3 < 60){//sysj\conveyorPlant.sysj line: 62, column: 69
                leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 62, column: 110
                currsigs.addElement(leftMovingE);
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\conveyorPlant.sysj line: 62, column: 62
                S3712=4;
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
            }
            break;
          
          case 3 : 
            if(System.currentTimeMillis() - tL1_thread_3 < 60){//sysj\conveyorPlant.sysj line: 62, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 62, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 62, column: 62
              S3712=4;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 4 : 
            S3712=4;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 63, column: 4
            currsigs.addElement(leftMoving_1);
            tL2_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 63, column: 23
            S3712=5;
            if(System.currentTimeMillis() - tL2_thread_3 < 60){//sysj\conveyorPlant.sysj line: 63, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 63, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 63, column: 62
              S3712=6;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 5 : 
            if(System.currentTimeMillis() - tL2_thread_3 < 60){//sysj\conveyorPlant.sysj line: 63, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 63, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 63, column: 62
              S3712=6;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 6 : 
            S3712=6;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 4
            currsigs.addElement(leftMoving_1);
            tL3_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 64, column: 23
            S3712=7;
            if(System.currentTimeMillis() - tL3_thread_3 < 60){//sysj\conveyorPlant.sysj line: 64, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 64, column: 62
              S3712=8;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 7 : 
            if(System.currentTimeMillis() - tL3_thread_3 < 60){//sysj\conveyorPlant.sysj line: 64, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 64, column: 62
              S3712=8;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 8 : 
            S3712=8;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 65, column: 4
            currsigs.addElement(leftMoving_1);
            tL4_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 65, column: 23
            S3712=9;
            if(System.currentTimeMillis() - tL4_thread_3 < 60){//sysj\conveyorPlant.sysj line: 65, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 65, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 65, column: 62
              S3712=10;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 9 : 
            if(System.currentTimeMillis() - tL4_thread_3 < 60){//sysj\conveyorPlant.sysj line: 65, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 65, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 65, column: 62
              S3712=10;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 10 : 
            S3712=10;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 66, column: 4
            currsigs.addElement(leftMoving_1);
            tL5_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 66, column: 23
            S3712=11;
            if(System.currentTimeMillis() - tL5_thread_3 < 60){//sysj\conveyorPlant.sysj line: 66, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 66, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 66, column: 62
              S3712=12;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 11 : 
            if(System.currentTimeMillis() - tL5_thread_3 < 60){//sysj\conveyorPlant.sysj line: 66, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 66, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 66, column: 62
              S3712=12;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 12 : 
            S3712=12;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 67, column: 4
            currsigs.addElement(leftMoving_1);
            tL6_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 67, column: 23
            S3712=13;
            if(System.currentTimeMillis() - tL6_thread_3 < 60){//sysj\conveyorPlant.sysj line: 67, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 67, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 67, column: 62
              S3712=14;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 13 : 
            if(System.currentTimeMillis() - tL6_thread_3 < 60){//sysj\conveyorPlant.sysj line: 67, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 67, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 67, column: 62
              S3712=14;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 14 : 
            S3712=14;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 68, column: 4
            currsigs.addElement(leftMoving_1);
            tL7_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 68, column: 23
            S3712=15;
            if(System.currentTimeMillis() - tL7_thread_3 < 60){//sysj\conveyorPlant.sysj line: 68, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 68, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 68, column: 62
              S3712=16;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 15 : 
            if(System.currentTimeMillis() - tL7_thread_3 < 60){//sysj\conveyorPlant.sysj line: 68, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 68, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 68, column: 62
              S3712=16;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 16 : 
            S3712=16;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 69, column: 4
            currsigs.addElement(leftMoving_1);
            tL8_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 69, column: 23
            S3712=17;
            if(System.currentTimeMillis() - tL8_thread_3 < 60){//sysj\conveyorPlant.sysj line: 69, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 69, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 69, column: 62
              S3712=18;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 17 : 
            if(System.currentTimeMillis() - tL8_thread_3 < 60){//sysj\conveyorPlant.sysj line: 69, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 69, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 69, column: 62
              S3712=18;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 18 : 
            S3712=18;
            S3712=19;
            bottleAtPos1.setPresent();//sysj\conveyorPlant.sysj line: 79, column: 5
            currsigs.addElement(bottleAtPos1);
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
          case 19 : 
            if(pos1TakenAck.getprestatus()){//sysj\conveyorPlant.sysj line: 78, column: 10
              S3712=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              bottleAtPos1.setPresent();//sysj\conveyorPlant.sysj line: 79, column: 5
              currsigs.addElement(bottleAtPos1);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread5540(int [] tdone, int [] ends){
        switch(S3708){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S3681){
          case 0 : 
            S3681=0;
            if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 23, column: 12
              motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 23, column: 31
              currsigs.addElement(motorOnE);
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 24, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 24, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 24, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 24, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            break;
          
          case 1 : 
            S3681=1;
            S3681=0;
            if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 23, column: 12
              motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 23, column: 31
              currsigs.addElement(motorOnE);
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 24, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 24, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 24, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 24, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
                  currsigs.addElement(motorOnE);
                  S3681=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S3681=1;
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

  public void thread5537(int [] tdone, int [] ends){
        S4598=1;
    if(bottleLeftPos5.getprestatus()){//sysj\conveyorPlant.sysj line: 125, column: 24
      bottleLeftPos5E.setPresent();//sysj\conveyorPlant.sysj line: 125, column: 40
      currsigs.addElement(bottleLeftPos5E);
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

  public void thread5536(int [] tdone, int [] ends){
        S4590=1;
    if(bottleAtPos1.getprestatus()){//sysj\conveyorPlant.sysj line: 123, column: 24
      bottleAtPos1E.setPresent();//sysj\conveyorPlant.sysj line: 123, column: 38
      currsigs.addElement(bottleAtPos1E);
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

  public void thread5535(int [] tdone, int [] ends){
        S4600=1;
    thread5536(tdone,ends);
    thread5537(tdone,ends);
    int biggest5538 = 0;
    if(ends[6]>=biggest5538){
      biggest5538=ends[6];
    }
    if(ends[7]>=biggest5538){
      biggest5538=ends[7];
    }
    if(biggest5538 == 1){
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread5534(int [] tdone, int [] ends){
        S4582=1;
    S4149=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread5533(int [] tdone, int [] ends){
        S4145=1;
    S3712=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread5532(int [] tdone, int [] ends){
        S3708=1;
    S3681=0;
    if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 23, column: 12
      motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 23, column: 31
      currsigs.addElement(motorOnE);
      if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 24, column: 12
        motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 24, column: 25
        currsigs.addElement(motorOnE);
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
          currsigs.addElement(motorOnE);
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
      else {
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
          currsigs.addElement(motorOnE);
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
    }
    else {
      if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 24, column: 12
        motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 24, column: 25
        currsigs.addElement(motorOnE);
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
          currsigs.addElement(motorOnE);
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
      else {
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 26
          currsigs.addElement(motorOnE);
          S3681=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S3681=1;
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
      switch(S5530){
        case 0 : 
          S5530=0;
          break RUN;
        
        case 1 : 
          S5530=2;
          S5530=2;
          new Thread(new ConveyorGUI()).start();//sysj\conveyorPlant.sysj line: 13, column: 2
          leftMoving_1.setClear();//sysj\conveyorPlant.sysj line: 18, column: 2
          rightMoving_1.setClear();//sysj\conveyorPlant.sysj line: 18, column: 2
          thread5532(tdone,ends);
          thread5533(tdone,ends);
          thread5534(tdone,ends);
          thread5535(tdone,ends);
          int biggest5539 = 0;
          if(ends[2]>=biggest5539){
            biggest5539=ends[2];
          }
          if(ends[3]>=biggest5539){
            biggest5539=ends[3];
          }
          if(ends[4]>=biggest5539){
            biggest5539=ends[4];
          }
          if(ends[5]>=biggest5539){
            biggest5539=ends[5];
          }
          if(biggest5539 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          leftMoving_1.setClear();//sysj\conveyorPlant.sysj line: 18, column: 2
          rightMoving_1.setClear();//sysj\conveyorPlant.sysj line: 18, column: 2
          thread5540(tdone,ends);
          thread5541(tdone,ends);
          thread5542(tdone,ends);
          thread5543(tdone,ends);
          int biggest5547 = 0;
          if(ends[2]>=biggest5547){
            biggest5547=ends[2];
          }
          if(ends[3]>=biggest5547){
            biggest5547=ends[3];
          }
          if(ends[4]>=biggest5547){
            biggest5547=ends[4];
          }
          if(ends[5]>=biggest5547){
            biggest5547=ends[5];
          }
          if(biggest5547 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest5547 == 0){
            S5530=0;
            active[1]=0;
            ends[1]=0;
            S5530=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0};
    paused = paused1;
    active = active1;
    suspended = suspended1;
    // Now instantiate all the local signals ONLY
    leftMoving_1 = new Signal();
    rightMoving_1 = new Signal();
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
          motConveyorOnOff.gethook();
          enable.gethook();
          loadBottle.gethook();
          bottleFromTable.gethook();
          sortDone.gethook();
          pos1TakenAck.gethook();
          df = true;
        }
        runClockDomain();
      }
      motConveyorOnOff.setpreclear();
      enable.setpreclear();
      loadBottle.setpreclear();
      bottleFromTable.setpreclear();
      sortDone.setpreclear();
      pos1TakenAck.setpreclear();
      bottleAtPos1.setpreclear();
      bottleLeftPos5.setpreclear();
      bottleAtPos1E.setpreclear();
      bottleLeftPos5E.setpreclear();
      motorOnE.setpreclear();
      bottleEnteredE.setpreclear();
      bottleReceivedE.setpreclear();
      leftMovingE.setpreclear();
      rightMovingE.setpreclear();
      leftMoving_1.setpreclear();
      rightMoving_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = motConveyorOnOff.getStatus() ? motConveyorOnOff.setprepresent() : motConveyorOnOff.setpreclear();
      motConveyorOnOff.setpreval(motConveyorOnOff.getValue());
      motConveyorOnOff.setClear();
      dummyint = enable.getStatus() ? enable.setprepresent() : enable.setpreclear();
      enable.setpreval(enable.getValue());
      enable.setClear();
      dummyint = loadBottle.getStatus() ? loadBottle.setprepresent() : loadBottle.setpreclear();
      loadBottle.setpreval(loadBottle.getValue());
      loadBottle.setClear();
      dummyint = bottleFromTable.getStatus() ? bottleFromTable.setprepresent() : bottleFromTable.setpreclear();
      bottleFromTable.setpreval(bottleFromTable.getValue());
      bottleFromTable.setClear();
      dummyint = sortDone.getStatus() ? sortDone.setprepresent() : sortDone.setpreclear();
      sortDone.setpreval(sortDone.getValue());
      sortDone.setClear();
      dummyint = pos1TakenAck.getStatus() ? pos1TakenAck.setprepresent() : pos1TakenAck.setpreclear();
      pos1TakenAck.setpreval(pos1TakenAck.getValue());
      pos1TakenAck.setClear();
      bottleAtPos1.sethook();
      bottleAtPos1.setClear();
      bottleLeftPos5.sethook();
      bottleLeftPos5.setClear();
      bottleAtPos1E.sethook();
      bottleAtPos1E.setClear();
      bottleLeftPos5E.sethook();
      bottleLeftPos5E.setClear();
      motorOnE.sethook();
      motorOnE.setClear();
      bottleEnteredE.sethook();
      bottleEnteredE.setClear();
      bottleReceivedE.sethook();
      bottleReceivedE.setClear();
      leftMovingE.sethook();
      leftMovingE.setClear();
      rightMovingE.sethook();
      rightMovingE.setClear();
      leftMoving_1.setClear();
      rightMoving_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        motConveyorOnOff.gethook();
        enable.gethook();
        loadBottle.gethook();
        bottleFromTable.gethook();
        sortDone.gethook();
        pos1TakenAck.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
