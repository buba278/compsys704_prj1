import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.ConveyorGUI;//sysj\conveyorPlant.sysj line: 1, column: 1
import run.ConveyorLabellerBridge;//sysj\conveyorPlant.sysj line: 2, column: 1

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
  public Signal pos1TakenAck = new Signal("pos1TakenAck", Signal.INPUT);
  public Signal labellerTakenAck = new Signal("labellerTakenAck", Signal.INPUT);
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
  private long tEnter_thread_3;//sysj\conveyorPlant.sysj line: 46, column: 5
  private long tL1_thread_3;//sysj\conveyorPlant.sysj line: 64, column: 23
  private long tL2_thread_3;//sysj\conveyorPlant.sysj line: 65, column: 23
  private long tL3_thread_3;//sysj\conveyorPlant.sysj line: 66, column: 23
  private long tL4_thread_3;//sysj\conveyorPlant.sysj line: 67, column: 23
  private long tL5_thread_3;//sysj\conveyorPlant.sysj line: 68, column: 23
  private long tL6_thread_3;//sysj\conveyorPlant.sysj line: 69, column: 23
  private long tL7_thread_3;//sysj\conveyorPlant.sysj line: 70, column: 23
  private long tL8_thread_3;//sysj\conveyorPlant.sysj line: 71, column: 23
  private long tReceived_thread_4;//sysj\conveyorPlant.sysj line: 99, column: 5
  private long tR1_thread_4;//sysj\conveyorPlant.sysj line: 107, column: 24
  private long tR2_thread_4;//sysj\conveyorPlant.sysj line: 108, column: 24
  private long tR3_thread_4;//sysj\conveyorPlant.sysj line: 109, column: 24
  private long tR4_thread_4;//sysj\conveyorPlant.sysj line: 110, column: 24
  private long tR5_thread_4;//sysj\conveyorPlant.sysj line: 111, column: 24
  private long tR6_thread_4;//sysj\conveyorPlant.sysj line: 112, column: 24
  private long tR7_thread_4;//sysj\conveyorPlant.sysj line: 113, column: 24
  private long tR8_thread_4;//sysj\conveyorPlant.sysj line: 114, column: 24
  private long tLabel_thread_4;//sysj\conveyorPlant.sysj line: 130, column: 5
  private int S6965 = 1;
  private int S4975 = 1;
  private int S4948 = 1;
  private int S5412 = 1;
  private int S4979 = 1;
  private int S5933 = 1;
  private int S5416 = 1;
  private int S5951 = 1;
  private int S5941 = 1;
  private int S5949 = 1;
  
  private int[] ends = new int[8];
  private int[] tdone = new int[8];
  
  public void thread6980(int [] tdone, int [] ends){
        switch(S5949){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(bottleLeftPos5.getprestatus()){//sysj\conveyorPlant.sysj line: 145, column: 24
          bottleLeftPos5E.setPresent();//sysj\conveyorPlant.sysj line: 145, column: 40
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

  public void thread6979(int [] tdone, int [] ends){
        switch(S5941){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(bottleAtPos1.getprestatus()){//sysj\conveyorPlant.sysj line: 143, column: 24
          bottleAtPos1E.setPresent();//sysj\conveyorPlant.sysj line: 143, column: 38
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

  public void thread6978(int [] tdone, int [] ends){
        switch(S5951){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        thread6979(tdone,ends);
        thread6980(tdone,ends);
        int biggest6981 = 0;
        if(ends[6]>=biggest6981){
          biggest6981=ends[6];
        }
        if(ends[7]>=biggest6981){
          biggest6981=ends[7];
        }
        if(biggest6981 == 1){
          active[5]=1;
          ends[5]=1;
          tdone[5]=1;
        }
        //FINXME code
        if(biggest6981 == 0){
          S5951=0;
          active[5]=0;
          ends[5]=0;
          tdone[5]=1;
        }
        break;
      
    }
  }

  public void thread6977(int [] tdone, int [] ends){
        switch(S5933){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S5416){
          case 0 : 
            if(bottleFromTable.getprestatus()){//sysj\conveyorPlant.sysj line: 93, column: 10
              S5416=1;
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
            if(enable.getprestatus()){//sysj\conveyorPlant.sysj line: 94, column: 10
              tReceived_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 99, column: 5
              S5416=2;
              if(System.currentTimeMillis() - tReceived_thread_4 < 50){//sysj\conveyorPlant.sysj line: 100, column: 12
                bottleReceivedE.setPresent();//sysj\conveyorPlant.sysj line: 100, column: 59
                currsigs.addElement(bottleReceivedE);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\conveyorPlant.sysj line: 100, column: 5
                rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 4
                currsigs.addElement(rightMoving_1);
                tR1_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 107, column: 24
                S5416=3;
                if(System.currentTimeMillis() - tR1_thread_4 < 60){//sysj\conveyorPlant.sysj line: 107, column: 70
                  rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 111
                  currsigs.addElement(rightMovingE);
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
                else {
                  ends[4]=2;
                  ;//sysj\conveyorPlant.sysj line: 107, column: 63
                  S5416=4;
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
            if(System.currentTimeMillis() - tReceived_thread_4 < 50){//sysj\conveyorPlant.sysj line: 100, column: 12
              bottleReceivedE.setPresent();//sysj\conveyorPlant.sysj line: 100, column: 59
              currsigs.addElement(bottleReceivedE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 100, column: 5
              rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 4
              currsigs.addElement(rightMoving_1);
              tR1_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 107, column: 24
              S5416=3;
              if(System.currentTimeMillis() - tR1_thread_4 < 60){//sysj\conveyorPlant.sysj line: 107, column: 70
                rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 111
                currsigs.addElement(rightMovingE);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\conveyorPlant.sysj line: 107, column: 63
                S5416=4;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
          case 3 : 
            if(System.currentTimeMillis() - tR1_thread_4 < 60){//sysj\conveyorPlant.sysj line: 107, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 107, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 107, column: 63
              S5416=4;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 4 : 
            S5416=4;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 108, column: 4
            currsigs.addElement(rightMoving_1);
            tR2_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 108, column: 24
            S5416=5;
            if(System.currentTimeMillis() - tR2_thread_4 < 60){//sysj\conveyorPlant.sysj line: 108, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 108, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 108, column: 63
              S5416=6;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 5 : 
            if(System.currentTimeMillis() - tR2_thread_4 < 60){//sysj\conveyorPlant.sysj line: 108, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 108, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 108, column: 63
              S5416=6;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 6 : 
            S5416=6;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 109, column: 4
            currsigs.addElement(rightMoving_1);
            tR3_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 109, column: 24
            S5416=7;
            if(System.currentTimeMillis() - tR3_thread_4 < 60){//sysj\conveyorPlant.sysj line: 109, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 109, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 109, column: 63
              S5416=8;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 7 : 
            if(System.currentTimeMillis() - tR3_thread_4 < 60){//sysj\conveyorPlant.sysj line: 109, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 109, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 109, column: 63
              S5416=8;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 8 : 
            S5416=8;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 110, column: 4
            currsigs.addElement(rightMoving_1);
            tR4_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 110, column: 24
            S5416=9;
            if(System.currentTimeMillis() - tR4_thread_4 < 60){//sysj\conveyorPlant.sysj line: 110, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 110, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 110, column: 63
              S5416=10;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 9 : 
            if(System.currentTimeMillis() - tR4_thread_4 < 60){//sysj\conveyorPlant.sysj line: 110, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 110, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 110, column: 63
              S5416=10;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 10 : 
            S5416=10;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 111, column: 4
            currsigs.addElement(rightMoving_1);
            tR5_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 111, column: 24
            S5416=11;
            if(System.currentTimeMillis() - tR5_thread_4 < 60){//sysj\conveyorPlant.sysj line: 111, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 111, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 111, column: 63
              S5416=12;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 11 : 
            if(System.currentTimeMillis() - tR5_thread_4 < 60){//sysj\conveyorPlant.sysj line: 111, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 111, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 111, column: 63
              S5416=12;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 12 : 
            S5416=12;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 112, column: 4
            currsigs.addElement(rightMoving_1);
            tR6_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 112, column: 24
            S5416=13;
            if(System.currentTimeMillis() - tR6_thread_4 < 60){//sysj\conveyorPlant.sysj line: 112, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 112, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 112, column: 63
              S5416=14;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 13 : 
            if(System.currentTimeMillis() - tR6_thread_4 < 60){//sysj\conveyorPlant.sysj line: 112, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 112, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 112, column: 63
              S5416=14;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 14 : 
            S5416=14;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 113, column: 4
            currsigs.addElement(rightMoving_1);
            tR7_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 113, column: 24
            S5416=15;
            if(System.currentTimeMillis() - tR7_thread_4 < 60){//sysj\conveyorPlant.sysj line: 113, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 113, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 113, column: 63
              S5416=16;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 15 : 
            if(System.currentTimeMillis() - tR7_thread_4 < 60){//sysj\conveyorPlant.sysj line: 113, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 113, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 113, column: 63
              S5416=16;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 16 : 
            S5416=16;
            rightMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 114, column: 4
            currsigs.addElement(rightMoving_1);
            tR8_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 114, column: 24
            S5416=17;
            if(System.currentTimeMillis() - tR8_thread_4 < 60){//sysj\conveyorPlant.sysj line: 114, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 114, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 114, column: 63
              S5416=18;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 17 : 
            if(System.currentTimeMillis() - tR8_thread_4 < 60){//sysj\conveyorPlant.sysj line: 114, column: 70
              rightMovingE.setPresent();//sysj\conveyorPlant.sysj line: 114, column: 111
              currsigs.addElement(rightMovingE);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 114, column: 63
              S5416=18;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 18 : 
            S5416=18;
            S5416=19;
            tLabel_thread_4 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 130, column: 5
            if(System.currentTimeMillis() - tLabel_thread_4 < 30000){//sysj\conveyorPlant.sysj line: 131, column: 12
              bottleLeftPos5.setPresent();//sysj\conveyorPlant.sysj line: 132, column: 6
              currsigs.addElement(bottleLeftPos5);
              ConveyorLabellerBridge.setBottleFromConveyor(true);//sysj\conveyorPlant.sysj line: 133, column: 6
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              ends[4]=2;
              ;//sysj\conveyorPlant.sysj line: 131, column: 5
              ConveyorLabellerBridge.setBottleFromConveyor(false);//sysj\conveyorPlant.sysj line: 137, column: 4
              S5416=20;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 19 : 
            if(labellerTakenAck.getprestatus()){//sysj\conveyorPlant.sysj line: 129, column: 10
              ConveyorLabellerBridge.setBottleFromConveyor(false);//sysj\conveyorPlant.sysj line: 137, column: 4
              S5416=20;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(System.currentTimeMillis() - tLabel_thread_4 < 30000){//sysj\conveyorPlant.sysj line: 131, column: 12
                bottleLeftPos5.setPresent();//sysj\conveyorPlant.sysj line: 132, column: 6
                currsigs.addElement(bottleLeftPos5);
                ConveyorLabellerBridge.setBottleFromConveyor(true);//sysj\conveyorPlant.sysj line: 133, column: 6
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                ends[4]=2;
                ;//sysj\conveyorPlant.sysj line: 131, column: 5
                ConveyorLabellerBridge.setBottleFromConveyor(false);//sysj\conveyorPlant.sysj line: 137, column: 4
                S5416=20;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            break;
          
          case 20 : 
            if(!labellerTakenAck.getprestatus()){//sysj\conveyorPlant.sysj line: 138, column: 10
              S5416=0;
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

  public void thread6976(int [] tdone, int [] ends){
        switch(S5412){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S4979){
          case 0 : 
            if(loadBottle.getprestatus()){//sysj\conveyorPlant.sysj line: 38, column: 10
              S4979=1;
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
            if(enable.getprestatus()){//sysj\conveyorPlant.sysj line: 39, column: 10
              tEnter_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 46, column: 5
              S4979=2;
              if(System.currentTimeMillis() - tEnter_thread_3 < 50){//sysj\conveyorPlant.sysj line: 47, column: 12
                bottleEnteredE.setPresent();//sysj\conveyorPlant.sysj line: 47, column: 56
                currsigs.addElement(bottleEnteredE);
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\conveyorPlant.sysj line: 47, column: 5
                leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 4
                currsigs.addElement(leftMoving_1);
                tL1_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 64, column: 23
                S4979=3;
                if(System.currentTimeMillis() - tL1_thread_3 < 60){//sysj\conveyorPlant.sysj line: 64, column: 69
                  leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 110
                  currsigs.addElement(leftMovingE);
                  active[3]=1;
                  ends[3]=1;
                  tdone[3]=1;
                }
                else {
                  ends[3]=2;
                  ;//sysj\conveyorPlant.sysj line: 64, column: 62
                  S4979=4;
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
            if(System.currentTimeMillis() - tEnter_thread_3 < 50){//sysj\conveyorPlant.sysj line: 47, column: 12
              bottleEnteredE.setPresent();//sysj\conveyorPlant.sysj line: 47, column: 56
              currsigs.addElement(bottleEnteredE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 47, column: 5
              leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 4
              currsigs.addElement(leftMoving_1);
              tL1_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 64, column: 23
              S4979=3;
              if(System.currentTimeMillis() - tL1_thread_3 < 60){//sysj\conveyorPlant.sysj line: 64, column: 69
                leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 110
                currsigs.addElement(leftMovingE);
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
              else {
                ends[3]=2;
                ;//sysj\conveyorPlant.sysj line: 64, column: 62
                S4979=4;
                active[3]=1;
                ends[3]=1;
                tdone[3]=1;
              }
            }
            break;
          
          case 3 : 
            if(System.currentTimeMillis() - tL1_thread_3 < 60){//sysj\conveyorPlant.sysj line: 64, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 64, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 64, column: 62
              S4979=4;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 4 : 
            S4979=4;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 65, column: 4
            currsigs.addElement(leftMoving_1);
            tL2_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 65, column: 23
            S4979=5;
            if(System.currentTimeMillis() - tL2_thread_3 < 60){//sysj\conveyorPlant.sysj line: 65, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 65, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 65, column: 62
              S4979=6;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 5 : 
            if(System.currentTimeMillis() - tL2_thread_3 < 60){//sysj\conveyorPlant.sysj line: 65, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 65, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 65, column: 62
              S4979=6;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 6 : 
            S4979=6;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 66, column: 4
            currsigs.addElement(leftMoving_1);
            tL3_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 66, column: 23
            S4979=7;
            if(System.currentTimeMillis() - tL3_thread_3 < 60){//sysj\conveyorPlant.sysj line: 66, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 66, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 66, column: 62
              S4979=8;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 7 : 
            if(System.currentTimeMillis() - tL3_thread_3 < 60){//sysj\conveyorPlant.sysj line: 66, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 66, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 66, column: 62
              S4979=8;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 8 : 
            S4979=8;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 67, column: 4
            currsigs.addElement(leftMoving_1);
            tL4_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 67, column: 23
            S4979=9;
            if(System.currentTimeMillis() - tL4_thread_3 < 60){//sysj\conveyorPlant.sysj line: 67, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 67, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 67, column: 62
              S4979=10;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 9 : 
            if(System.currentTimeMillis() - tL4_thread_3 < 60){//sysj\conveyorPlant.sysj line: 67, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 67, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 67, column: 62
              S4979=10;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 10 : 
            S4979=10;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 68, column: 4
            currsigs.addElement(leftMoving_1);
            tL5_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 68, column: 23
            S4979=11;
            if(System.currentTimeMillis() - tL5_thread_3 < 60){//sysj\conveyorPlant.sysj line: 68, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 68, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 68, column: 62
              S4979=12;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 11 : 
            if(System.currentTimeMillis() - tL5_thread_3 < 60){//sysj\conveyorPlant.sysj line: 68, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 68, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 68, column: 62
              S4979=12;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 12 : 
            S4979=12;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 69, column: 4
            currsigs.addElement(leftMoving_1);
            tL6_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 69, column: 23
            S4979=13;
            if(System.currentTimeMillis() - tL6_thread_3 < 60){//sysj\conveyorPlant.sysj line: 69, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 69, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 69, column: 62
              S4979=14;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 13 : 
            if(System.currentTimeMillis() - tL6_thread_3 < 60){//sysj\conveyorPlant.sysj line: 69, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 69, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 69, column: 62
              S4979=14;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 14 : 
            S4979=14;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 70, column: 4
            currsigs.addElement(leftMoving_1);
            tL7_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 70, column: 23
            S4979=15;
            if(System.currentTimeMillis() - tL7_thread_3 < 60){//sysj\conveyorPlant.sysj line: 70, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 70, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 70, column: 62
              S4979=16;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 15 : 
            if(System.currentTimeMillis() - tL7_thread_3 < 60){//sysj\conveyorPlant.sysj line: 70, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 70, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 70, column: 62
              S4979=16;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 16 : 
            S4979=16;
            leftMoving_1.setPresent();//sysj\conveyorPlant.sysj line: 71, column: 4
            currsigs.addElement(leftMoving_1);
            tL8_thread_3 = System.currentTimeMillis();//sysj\conveyorPlant.sysj line: 71, column: 23
            S4979=17;
            if(System.currentTimeMillis() - tL8_thread_3 < 60){//sysj\conveyorPlant.sysj line: 71, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 71, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 71, column: 62
              S4979=18;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 17 : 
            if(System.currentTimeMillis() - tL8_thread_3 < 60){//sysj\conveyorPlant.sysj line: 71, column: 69
              leftMovingE.setPresent();//sysj\conveyorPlant.sysj line: 71, column: 110
              currsigs.addElement(leftMovingE);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              ends[3]=2;
              ;//sysj\conveyorPlant.sysj line: 71, column: 62
              S4979=18;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 18 : 
            S4979=18;
            S4979=19;
            bottleAtPos1.setPresent();//sysj\conveyorPlant.sysj line: 81, column: 5
            currsigs.addElement(bottleAtPos1);
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
          case 19 : 
            if(pos1TakenAck.getprestatus()){//sysj\conveyorPlant.sysj line: 80, column: 10
              S4979=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              bottleAtPos1.setPresent();//sysj\conveyorPlant.sysj line: 81, column: 5
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

  public void thread6975(int [] tdone, int [] ends){
        switch(S4975){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S4948){
          case 0 : 
            S4948=0;
            if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
              motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 31
              currsigs.addElement(motorOnE);
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 26, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 26, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            break;
          
          case 1 : 
            S4948=1;
            S4948=0;
            if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
              motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 31
              currsigs.addElement(motorOnE);
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 26, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
            }
            else {
              if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 12
                motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 26, column: 25
                currsigs.addElement(motorOnE);
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
              }
              else {
                if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
                  motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
                  currsigs.addElement(motorOnE);
                  S4948=1;
                  active[2]=1;
                  ends[2]=1;
                  tdone[2]=1;
                }
                else {
                  S4948=1;
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

  public void thread6972(int [] tdone, int [] ends){
        S5949=1;
    if(bottleLeftPos5.getprestatus()){//sysj\conveyorPlant.sysj line: 145, column: 24
      bottleLeftPos5E.setPresent();//sysj\conveyorPlant.sysj line: 145, column: 40
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

  public void thread6971(int [] tdone, int [] ends){
        S5941=1;
    if(bottleAtPos1.getprestatus()){//sysj\conveyorPlant.sysj line: 143, column: 24
      bottleAtPos1E.setPresent();//sysj\conveyorPlant.sysj line: 143, column: 38
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

  public void thread6970(int [] tdone, int [] ends){
        S5951=1;
    thread6971(tdone,ends);
    thread6972(tdone,ends);
    int biggest6973 = 0;
    if(ends[6]>=biggest6973){
      biggest6973=ends[6];
    }
    if(ends[7]>=biggest6973){
      biggest6973=ends[7];
    }
    if(biggest6973 == 1){
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread6969(int [] tdone, int [] ends){
        S5933=1;
    S5416=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread6968(int [] tdone, int [] ends){
        S5412=1;
    S4979=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread6967(int [] tdone, int [] ends){
        S4975=1;
    S4948=0;
    if(motConveyorOnOff.getprestatus()){//sysj\conveyorPlant.sysj line: 25, column: 12
      motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 25, column: 31
      currsigs.addElement(motorOnE);
      if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 12
        motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 26, column: 25
        currsigs.addElement(motorOnE);
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
          currsigs.addElement(motorOnE);
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
      else {
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
          currsigs.addElement(motorOnE);
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
    }
    else {
      if(leftMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 26, column: 12
        motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 26, column: 25
        currsigs.addElement(motorOnE);
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
          currsigs.addElement(motorOnE);
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
      }
      else {
        if(rightMoving_1.getprestatus()){//sysj\conveyorPlant.sysj line: 27, column: 12
          motorOnE.setPresent();//sysj\conveyorPlant.sysj line: 27, column: 26
          currsigs.addElement(motorOnE);
          S4948=1;
          active[2]=1;
          ends[2]=1;
          tdone[2]=1;
        }
        else {
          S4948=1;
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
      switch(S6965){
        case 0 : 
          S6965=0;
          break RUN;
        
        case 1 : 
          S6965=2;
          S6965=2;
          new Thread(new ConveyorGUI()).start();//sysj\conveyorPlant.sysj line: 15, column: 2
          leftMoving_1.setClear();//sysj\conveyorPlant.sysj line: 20, column: 2
          rightMoving_1.setClear();//sysj\conveyorPlant.sysj line: 20, column: 2
          thread6967(tdone,ends);
          thread6968(tdone,ends);
          thread6969(tdone,ends);
          thread6970(tdone,ends);
          int biggest6974 = 0;
          if(ends[2]>=biggest6974){
            biggest6974=ends[2];
          }
          if(ends[3]>=biggest6974){
            biggest6974=ends[3];
          }
          if(ends[4]>=biggest6974){
            biggest6974=ends[4];
          }
          if(ends[5]>=biggest6974){
            biggest6974=ends[5];
          }
          if(biggest6974 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          leftMoving_1.setClear();//sysj\conveyorPlant.sysj line: 20, column: 2
          rightMoving_1.setClear();//sysj\conveyorPlant.sysj line: 20, column: 2
          thread6975(tdone,ends);
          thread6976(tdone,ends);
          thread6977(tdone,ends);
          thread6978(tdone,ends);
          int biggest6982 = 0;
          if(ends[2]>=biggest6982){
            biggest6982=ends[2];
          }
          if(ends[3]>=biggest6982){
            biggest6982=ends[3];
          }
          if(ends[4]>=biggest6982){
            biggest6982=ends[4];
          }
          if(ends[5]>=biggest6982){
            biggest6982=ends[5];
          }
          if(biggest6982 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest6982 == 0){
            S6965=0;
            active[1]=0;
            ends[1]=0;
            S6965=0;
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
          pos1TakenAck.gethook();
          labellerTakenAck.gethook();
          df = true;
        }
        runClockDomain();
      }
      motConveyorOnOff.setpreclear();
      enable.setpreclear();
      loadBottle.setpreclear();
      bottleFromTable.setpreclear();
      pos1TakenAck.setpreclear();
      labellerTakenAck.setpreclear();
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
      dummyint = pos1TakenAck.getStatus() ? pos1TakenAck.setprepresent() : pos1TakenAck.setpreclear();
      pos1TakenAck.setpreval(pos1TakenAck.getValue());
      pos1TakenAck.setClear();
      dummyint = labellerTakenAck.getStatus() ? labellerTakenAck.setprepresent() : labellerTakenAck.setpreclear();
      labellerTakenAck.setpreval(labellerTakenAck.getValue());
      labellerTakenAck.setClear();
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
        pos1TakenAck.gethook();
        labellerTakenAck.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
