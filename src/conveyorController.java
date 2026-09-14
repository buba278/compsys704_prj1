import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.RotaryConveyorBridge;//sysj\conveyorController.sysj line: 1, column: 1

public class conveyorController extends ClockDomain{
  public conveyorController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtPos1 = new Signal("bottleAtPos1", Signal.INPUT);
  public Signal bottleLeftPos5 = new Signal("bottleLeftPos5", Signal.INPUT);
  public Signal pos1TakenAck = new Signal("pos1TakenAck", Signal.INPUT);
  public Signal mode = new Signal("mode", Signal.INPUT);
  public Signal conveyorM = new Signal("conveyorM", Signal.INPUT);
  public Signal motConveyorOnOff = new Signal("motConveyorOnOff", Signal.OUTPUT);
  private Signal autoMode_1;
  private Signal manualMode_1;
  private int currentMode_thread_2;//sysj\conveyorController.sysj line: 29, column: 3
  private boolean handoffPending_thread_3;//sysj\conveyorController.sysj line: 48, column: 5
  private long tArrive_thread_3;//sysj\conveyorController.sysj line: 60, column: 8
  private int S3665 = 1;
  private int S2477 = 1;
  private int S2450 = 1;
  private int S3613 = 1;
  private int S2855 = 1;
  private int S2500 = 1;
  private int S3663 = 1;
  private int S3629 = 1;
  
  private int[] ends = new int[5];
  private int[] tdone = new int[5];
  
  public void thread3673(int [] tdone, int [] ends){
        switch(S3663){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S3629){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\conveyorController.sysj line: 86, column: 10
              S3629=1;
              if(conveyorM.getprestatus()){//sysj\conveyorController.sysj line: 89, column: 14
                motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 90, column: 7
                currsigs.addElement(motConveyorOnOff);
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
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            if(autoMode_1.getprestatus()){//sysj\conveyorController.sysj line: 87, column: 10
              S3629=0;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              if(conveyorM.getprestatus()){//sysj\conveyorController.sysj line: 89, column: 14
                motConveyorOnOff.setPresent();//sysj\conveyorController.sysj line: 90, column: 7
                currsigs.addElement(motConveyorOnOff);
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
        break;
      
    }
  }

  public void thread3672(int [] tdone, int [] ends){
        switch(S3613){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S2855){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\conveyorController.sysj line: 46, column: 10
              S2855=1;
              handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 48, column: 5
              S2500=0;
              if(bottleAtPos1.getprestatus()){//sysj\conveyorController.sysj line: 50, column: 14
                if(!handoffPending_thread_3){//sysj\conveyorController.sysj line: 58, column: 11
                  handoffPending_thread_3 = true;//sysj\conveyorController.sysj line: 59, column: 8
                  tArrive_thread_3 = System.currentTimeMillis();//sysj\conveyorController.sysj line: 60, column: 8
                  if(System.currentTimeMillis() - tArrive_thread_3 < 200){//sysj\conveyorController.sysj line: 61, column: 15
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\conveyorController.sysj line: 61, column: 8
                    RotaryConveyorBridge.setReadyToRotate(true);//sysj\conveyorController.sysj line: 62, column: 8
                    System.out.printf("Conveyor: bottle at Pos 1 - handing to Rotary Table%n");//sysj\conveyorController.sysj line: 63, column: 8
                    if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                      handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                      RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                      System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                    else {
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                }
                else {
                  if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                    handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                    RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                    System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                    if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                      System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                      S2500=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S2500=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  else {
                    if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                      System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                      S2500=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S2500=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                }
              }
              else {
                if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                  handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                  RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                  System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                  if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                    System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                    S2500=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    S2500=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                }
                else {
                  if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                    System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                    S2500=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    S2500=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
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
            if(manualMode_1.getprestatus()){//sysj\conveyorController.sysj line: 47, column: 10
              S2855=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S2500){
                case 0 : 
                  if(System.currentTimeMillis() - tArrive_thread_3 < 200){//sysj\conveyorController.sysj line: 61, column: 15
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\conveyorController.sysj line: 61, column: 8
                    RotaryConveyorBridge.setReadyToRotate(true);//sysj\conveyorController.sysj line: 62, column: 8
                    System.out.printf("Conveyor: bottle at Pos 1 - handing to Rotary Table%n");//sysj\conveyorController.sysj line: 63, column: 8
                    if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                      handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                      RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                      System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                    else {
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                  break;
                
                case 1 : 
                  S2500=1;
                  S2500=0;
                  if(bottleAtPos1.getprestatus()){//sysj\conveyorController.sysj line: 50, column: 14
                    if(!handoffPending_thread_3){//sysj\conveyorController.sysj line: 58, column: 11
                      handoffPending_thread_3 = true;//sysj\conveyorController.sysj line: 59, column: 8
                      tArrive_thread_3 = System.currentTimeMillis();//sysj\conveyorController.sysj line: 60, column: 8
                      if(System.currentTimeMillis() - tArrive_thread_3 < 200){//sysj\conveyorController.sysj line: 61, column: 15
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        ends[3]=2;
                        ;//sysj\conveyorController.sysj line: 61, column: 8
                        RotaryConveyorBridge.setReadyToRotate(true);//sysj\conveyorController.sysj line: 62, column: 8
                        System.out.printf("Conveyor: bottle at Pos 1 - handing to Rotary Table%n");//sysj\conveyorController.sysj line: 63, column: 8
                        if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                          handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                          RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                          System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                          if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                            System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                            S2500=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            S2500=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                        }
                        else {
                          if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                            System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                            S2500=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            S2500=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                        }
                      }
                    }
                    else {
                      if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                        handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                        RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                        System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                        if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                          System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                          S2500=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          S2500=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                      }
                      else {
                        if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                          System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                          S2500=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          S2500=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                      }
                    }
                  }
                  else {
                    if(pos1TakenAck.getprestatus()){//sysj\conveyorController.sysj line: 66, column: 14
                      handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 71, column: 7
                      RotaryConveyorBridge.setReadyToRotate(false);//sysj\conveyorController.sysj line: 72, column: 7
                      System.out.printf("Conveyor: Rotary Table has taken the bottle from Pos 1%n");//sysj\conveyorController.sysj line: 73, column: 7
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                    else {
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S2500=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                  break;
                
              }
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread3671(int [] tdone, int [] ends){
        switch(S2477){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S2450){
          case 0 : 
            S2450=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 31, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 32, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S2450=1;
            S2450=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 31, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 32, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S2450=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S2450=1;
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

  public void thread3669(int [] tdone, int [] ends){
        S3663=1;
    S3629=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread3668(int [] tdone, int [] ends){
        S3613=1;
    S2855=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread3667(int [] tdone, int [] ends){
        S2477=1;
    currentMode_thread_2 = 0;//sysj\conveyorController.sysj line: 29, column: 3
    S2450=0;
    if(mode.getprestatus()){//sysj\conveyorController.sysj line: 31, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 32, column: 5
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
        currsigs.addElement(manualMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
        currsigs.addElement(autoMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
        currsigs.addElement(manualMode_1);
        S2450=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
        currsigs.addElement(autoMode_1);
        S2450=1;
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
      switch(S3665){
        case 0 : 
          S3665=0;
          break RUN;
        
        case 1 : 
          S3665=2;
          S3665=2;
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          thread3667(tdone,ends);
          thread3668(tdone,ends);
          thread3669(tdone,ends);
          int biggest3670 = 0;
          if(ends[2]>=biggest3670){
            biggest3670=ends[2];
          }
          if(ends[3]>=biggest3670){
            biggest3670=ends[3];
          }
          if(ends[4]>=biggest3670){
            biggest3670=ends[4];
          }
          if(biggest3670 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          thread3671(tdone,ends);
          thread3672(tdone,ends);
          thread3673(tdone,ends);
          int biggest3674 = 0;
          if(ends[2]>=biggest3674){
            biggest3674=ends[2];
          }
          if(ends[3]>=biggest3674){
            biggest3674=ends[3];
          }
          if(ends[4]>=biggest3674){
            biggest3674=ends[4];
          }
          if(biggest3674 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest3674 == 0){
            S3665=0;
            active[1]=0;
            ends[1]=0;
            S3665=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0};
    paused = paused1;
    active = active1;
    suspended = suspended1;
    // Now instantiate all the local signals ONLY
    autoMode_1 = new Signal();
    manualMode_1 = new Signal();
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
          bottleAtPos1.gethook();
          bottleLeftPos5.gethook();
          pos1TakenAck.gethook();
          mode.gethook();
          conveyorM.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtPos1.setpreclear();
      bottleLeftPos5.setpreclear();
      pos1TakenAck.setpreclear();
      mode.setpreclear();
      conveyorM.setpreclear();
      motConveyorOnOff.setpreclear();
      autoMode_1.setpreclear();
      manualMode_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtPos1.getStatus() ? bottleAtPos1.setprepresent() : bottleAtPos1.setpreclear();
      bottleAtPos1.setpreval(bottleAtPos1.getValue());
      bottleAtPos1.setClear();
      dummyint = bottleLeftPos5.getStatus() ? bottleLeftPos5.setprepresent() : bottleLeftPos5.setpreclear();
      bottleLeftPos5.setpreval(bottleLeftPos5.getValue());
      bottleLeftPos5.setClear();
      dummyint = pos1TakenAck.getStatus() ? pos1TakenAck.setprepresent() : pos1TakenAck.setpreclear();
      pos1TakenAck.setpreval(pos1TakenAck.getValue());
      pos1TakenAck.setClear();
      dummyint = mode.getStatus() ? mode.setprepresent() : mode.setpreclear();
      mode.setpreval(mode.getValue());
      mode.setClear();
      dummyint = conveyorM.getStatus() ? conveyorM.setprepresent() : conveyorM.setpreclear();
      conveyorM.setpreval(conveyorM.getValue());
      conveyorM.setClear();
      motConveyorOnOff.sethook();
      motConveyorOnOff.setClear();
      autoMode_1.setClear();
      manualMode_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtPos1.gethook();
        bottleLeftPos5.gethook();
        pos1TakenAck.gethook();
        mode.gethook();
        conveyorM.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
