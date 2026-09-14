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
  private int S4932 = 1;
  private int S3744 = 1;
  private int S3717 = 1;
  private int S4880 = 1;
  private int S4122 = 1;
  private int S3767 = 1;
  private int S4930 = 1;
  private int S4896 = 1;
  
  private int[] ends = new int[5];
  private int[] tdone = new int[5];
  
  public void thread4940(int [] tdone, int [] ends){
        switch(S4930){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S4896){
          case 0 : 
            if(manualMode_1.getprestatus()){//sysj\conveyorController.sysj line: 86, column: 10
              S4896=1;
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
              S4896=0;
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

  public void thread4939(int [] tdone, int [] ends){
        switch(S4880){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S4122){
          case 0 : 
            if(autoMode_1.getprestatus()){//sysj\conveyorController.sysj line: 46, column: 10
              S4122=1;
              handoffPending_thread_3 = false;//sysj\conveyorController.sysj line: 48, column: 5
              S3767=0;
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
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                    else {
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S3767=1;
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
                      S3767=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S3767=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  else {
                    if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                      System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                      S3767=1;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      S3767=1;
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
                    S3767=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    S3767=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                }
                else {
                  if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                    System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                    S3767=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    S3767=1;
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
              S4122=0;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S3767){
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
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                    else {
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                  }
                  break;
                
                case 1 : 
                  S3767=1;
                  S3767=0;
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
                            S3767=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            S3767=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                        }
                        else {
                          if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                            System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                            S3767=1;
                            active[3]=1;
                            ends[3]=1;
                            tdone[3]=1;
                          }
                          else {
                            S3767=1;
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
                          S3767=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          S3767=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                      }
                      else {
                        if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                          System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                          S3767=1;
                          active[3]=1;
                          ends[3]=1;
                          tdone[3]=1;
                        }
                        else {
                          S3767=1;
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
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                    }
                    else {
                      if(bottleLeftPos5.getprestatus()){//sysj\conveyorController.sysj line: 75, column: 14
                        System.out.printf("Conveyor: bottle travelling to collection point%n");//sysj\conveyorController.sysj line: 76, column: 7
                        S3767=1;
                        active[3]=1;
                        ends[3]=1;
                        tdone[3]=1;
                      }
                      else {
                        S3767=1;
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

  public void thread4938(int [] tdone, int [] ends){
        switch(S3744){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S3717){
          case 0 : 
            S3717=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 31, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 32, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S3717=1;
            S3717=0;
            if(mode.getprestatus()){//sysj\conveyorController.sysj line: 31, column: 12
              currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 32, column: 5
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
                manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
                currsigs.addElement(manualMode_1);
                S3717=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
                currsigs.addElement(autoMode_1);
                S3717=1;
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

  public void thread4936(int [] tdone, int [] ends){
        S4930=1;
    S4896=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread4935(int [] tdone, int [] ends){
        S4880=1;
    S4122=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread4934(int [] tdone, int [] ends){
        S3744=1;
    currentMode_thread_2 = 0;//sysj\conveyorController.sysj line: 29, column: 3
    S3717=0;
    if(mode.getprestatus()){//sysj\conveyorController.sysj line: 31, column: 12
      currentMode_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\conveyorController.sysj line: 32, column: 5
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
        currsigs.addElement(manualMode_1);
        S3717=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
        currsigs.addElement(autoMode_1);
        S3717=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(currentMode_thread_2 == 1){//sysj\conveyorController.sysj line: 34, column: 8
        manualMode_1.setPresent();//sysj\conveyorController.sysj line: 35, column: 5
        currsigs.addElement(manualMode_1);
        S3717=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        autoMode_1.setPresent();//sysj\conveyorController.sysj line: 38, column: 5
        currsigs.addElement(autoMode_1);
        S3717=1;
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
      switch(S4932){
        case 0 : 
          S4932=0;
          break RUN;
        
        case 1 : 
          S4932=2;
          S4932=2;
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          thread4934(tdone,ends);
          thread4935(tdone,ends);
          thread4936(tdone,ends);
          int biggest4937 = 0;
          if(ends[2]>=biggest4937){
            biggest4937=ends[2];
          }
          if(ends[3]>=biggest4937){
            biggest4937=ends[3];
          }
          if(ends[4]>=biggest4937){
            biggest4937=ends[4];
          }
          if(biggest4937 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          autoMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          manualMode_1.setClear();//sysj\conveyorController.sysj line: 11, column: 2
          thread4938(tdone,ends);
          thread4939(tdone,ends);
          thread4940(tdone,ends);
          int biggest4941 = 0;
          if(ends[2]>=biggest4941){
            biggest4941=ends[2];
          }
          if(ends[3]>=biggest4941){
            biggest4941=ends[3];
          }
          if(ends[4]>=biggest4941){
            biggest4941=ends[4];
          }
          if(biggest4941 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest4941 == 0){
            S4932=0;
            active[1]=0;
            ends[1]=0;
            S4932=0;
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
