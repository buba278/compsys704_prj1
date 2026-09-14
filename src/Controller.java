import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import digitaltwin.PlantTwin;//sysj\controller.sysj line: 1, column: 1
import digitaltwin.TwinClient;//sysj\controller.sysj line: 2, column: 1
import run.RotaryLidBridge;//sysj\controller.sysj line: 3, column: 1

public class Controller extends ClockDomain{
  public Controller(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal pusherRetracted = new Signal("pusherRetracted", Signal.INPUT);
  public Signal pusherExtended = new Signal("pusherExtended", Signal.INPUT);
  public Signal WPgripped = new Signal("WPgripped", Signal.INPUT);
  public Signal armAtSource = new Signal("armAtSource", Signal.INPUT);
  public Signal armAtDest = new Signal("armAtDest", Signal.INPUT);
  public Signal empty = new Signal("empty", Signal.INPUT);
  public Signal request = new Signal("request", Signal.INPUT);
  public Signal mode = new Signal("mode", Signal.INPUT);
  public Signal pusherExtendM = new Signal("pusherExtendM", Signal.INPUT);
  public Signal vacOnM = new Signal("vacOnM", Signal.INPUT);
  public Signal armSourceM = new Signal("armSourceM", Signal.INPUT);
  public Signal armDestM = new Signal("armDestM", Signal.INPUT);
  public Signal pusherExtend = new Signal("pusherExtend", Signal.OUTPUT);
  public Signal vacOn = new Signal("vacOn", Signal.OUTPUT);
  public Signal armSource = new Signal("armSource", Signal.OUTPUT);
  public Signal armDest = new Signal("armDest", Signal.OUTPUT);
  private Signal auto_1;
  private Signal manual_1;
  private int modeVal_thread_2;//sysj\controller.sysj line: 16, column: 3
  private TwinClient twin_thread_3;//sysj\controller.sysj line: 32, column: 3
  private long tAck_thread_3;//sysj\controller.sysj line: 87, column: 7
  private int S3697 = 1;
  private int S1539 = 1;
  private int S1512 = 1;
  private int S3695 = 1;
  private int S1781 = 1;
  private int S1541 = 1;
  private int S1574 = 1;
  private int S1579 = 1;
  
  private int[] ends = new int[6];
  private int[] tdone = new int[6];
  
  public void thread3708(int [] tdone, int [] ends){
        switch(S1579){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        armDest.setPresent();//sysj\controller.sysj line: 70, column: 8
        currsigs.addElement(armDest);
        active[5]=1;
        ends[5]=1;
        tdone[5]=1;
        break;
      
    }
  }

  public void thread3707(int [] tdone, int [] ends){
        switch(S1574){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        vacOn.setPresent();//sysj\controller.sysj line: 68, column: 8
        currsigs.addElement(vacOn);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
        break;
      
    }
  }

  public void thread3705(int [] tdone, int [] ends){
        S1579=1;
    armDest.setPresent();//sysj\controller.sysj line: 70, column: 8
    currsigs.addElement(armDest);
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread3704(int [] tdone, int [] ends){
        S1574=1;
    vacOn.setPresent();//sysj\controller.sysj line: 68, column: 8
    currsigs.addElement(vacOn);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread3703(int [] tdone, int [] ends){
        switch(S3695){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S1781){
          case 0 : 
            if(manual_1.getprestatus()){//sysj\controller.sysj line: 36, column: 11
              S1781=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S1541){
                case 0 : 
                  if(request.getprestatus()){//sysj\controller.sysj line: 48, column: 13
                    System.out.printf("request received%n");//sysj\controller.sysj line: 49, column: 6
                    twin_thread_3.update(PlantTwin.State.IN_PROGRESS);//sysj\controller.sysj line: 50, column: 6
                    S1541=1;
                    armDest.setPresent();//sysj\controller.sysj line: 52, column: 7
                    currsigs.addElement(armDest);
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
                  if(armAtDest.getprestatus()){//sysj\controller.sysj line: 51, column: 13
                    S1541=2;
                    pusherExtend.setPresent();//sysj\controller.sysj line: 57, column: 7
                    currsigs.addElement(pusherExtend);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    armDest.setPresent();//sysj\controller.sysj line: 52, column: 7
                    currsigs.addElement(armDest);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 2 : 
                  if(pusherExtended.getprestatus()){//sysj\controller.sysj line: 56, column: 13
                    S1541=3;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    pusherExtend.setPresent();//sysj\controller.sysj line: 57, column: 7
                    currsigs.addElement(pusherExtend);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 3 : 
                  if(pusherRetracted.getprestatus()){//sysj\controller.sysj line: 60, column: 13
                    S1541=4;
                    armSource.setPresent();//sysj\controller.sysj line: 63, column: 7
                    currsigs.addElement(armSource);
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
                
                case 4 : 
                  if(armAtSource.getprestatus()){//sysj\controller.sysj line: 62, column: 13
                    S1541=5;
                    thread3704(tdone,ends);
                    thread3705(tdone,ends);
                    int biggest3706 = 0;
                    if(ends[4]>=biggest3706){
                      biggest3706=ends[4];
                    }
                    if(ends[5]>=biggest3706){
                      biggest3706=ends[5];
                    }
                    if(biggest3706 == 1){
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  else {
                    armSource.setPresent();//sysj\controller.sysj line: 63, column: 7
                    currsigs.addElement(armSource);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 5 : 
                  if(WPgripped.getprestatus() && armAtDest.getprestatus()){//sysj\controller.sysj line: 67, column: 13
                    S1541=6;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    thread3707(tdone,ends);
                    thread3708(tdone,ends);
                    int biggest3709 = 0;
                    if(ends[4]>=biggest3709){
                      biggest3709=ends[4];
                    }
                    if(ends[5]>=biggest3709){
                      biggest3709=ends[5];
                    }
                    if(biggest3709 == 1){
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    //FINXME code
                    if(biggest3709 == 0){
                      S1541=6;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  break;
                
                case 6 : 
                  if(!WPgripped.getprestatus()){//sysj\controller.sysj line: 73, column: 12
                    S1541=7;
                    armSource.setPresent();//sysj\controller.sysj line: 76, column: 7
                    currsigs.addElement(armSource);
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
                
                case 7 : 
                  if(armAtSource.getprestatus()){//sysj\controller.sysj line: 75, column: 13
                    twin_thread_3.recordEvent("lid placed");//sysj\controller.sysj line: 78, column: 6
                    twin_thread_3.update(PlantTwin.State.IDLE);//sysj\controller.sysj line: 79, column: 6
                    tAck_thread_3 = System.currentTimeMillis();//sysj\controller.sysj line: 87, column: 7
                    S1541=8;
                    if(System.currentTimeMillis() - tAck_thread_3 < 200){//sysj\controller.sysj line: 88, column: 14
                      RotaryLidBridge.setLidPlacedAck(true);//sysj\controller.sysj line: 89, column: 8
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    else {
                      ends[3]=2;
                      ;//sysj\controller.sysj line: 88, column: 7
                      RotaryLidBridge.setLidPlacedAck(false);//sysj\controller.sysj line: 92, column: 7
                      S1541=9;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  else {
                    armSource.setPresent();//sysj\controller.sysj line: 76, column: 7
                    currsigs.addElement(armSource);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 8 : 
                  if(System.currentTimeMillis() - tAck_thread_3 < 200){//sysj\controller.sysj line: 88, column: 14
                    RotaryLidBridge.setLidPlacedAck(true);//sysj\controller.sysj line: 89, column: 8
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    ends[3]=2;
                    ;//sysj\controller.sysj line: 88, column: 7
                    RotaryLidBridge.setLidPlacedAck(false);//sysj\controller.sysj line: 92, column: 7
                    S1541=9;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 9 : 
                  if(!request.getprestatus()){//sysj\controller.sysj line: 101, column: 13
                    System.out.printf("Auto: %d%n", 1);//sysj\controller.sysj line: 45, column: 6
                    twin_thread_3.update(PlantTwin.State.IDLE);//sysj\controller.sysj line: 46, column: 6
                    S1541=0;
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
            break;
          
          case 1 : 
            if(manual_1.getprestatus()){//sysj\controller.sysj line: 104, column: 11
              System.out.printf("Manual: %d%n", 1);//sysj\controller.sysj line: 105, column: 4
              S1781=2;
              if(pusherExtendM.getprestatus()){//sysj\controller.sysj line: 108, column: 15
                pusherExtend.setPresent();//sysj\controller.sysj line: 109, column: 7
                currsigs.addElement(pusherExtend);
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 111, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 112, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                else {
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
              }
              else {
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 111, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 112, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                else {
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
              }
            }
            else {
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            if(auto_1.getprestatus()){//sysj\controller.sysj line: 106, column: 11
              S1781=3;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              if(pusherExtendM.getprestatus()){//sysj\controller.sysj line: 108, column: 15
                pusherExtend.setPresent();//sysj\controller.sysj line: 109, column: 7
                currsigs.addElement(pusherExtend);
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 111, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 112, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                else {
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
              }
              else {
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 111, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 112, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                else {
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 114, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 115, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 117, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 118, column: 7
                      currsigs.addElement(armDest);
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
              }
            }
            break;
          
          case 3 : 
            if(auto_1.getprestatus()){//sysj\controller.sysj line: 123, column: 11
              S1781=0;
              System.out.printf("Auto: %d%n", 1);//sysj\controller.sysj line: 45, column: 6
              twin_thread_3.update(PlantTwin.State.IDLE);//sysj\controller.sysj line: 46, column: 6
              S1541=0;
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

  public void thread3702(int [] tdone, int [] ends){
        switch(S1539){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S1512){
          case 0 : 
            S1512=0;
            if(mode.getprestatus()){//sysj\controller.sysj line: 18, column: 13
              modeVal_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\controller.sysj line: 19, column: 5
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 21, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 22, column: 5
                currsigs.addElement(manual_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 25, column: 5
                currsigs.addElement(auto_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 21, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 22, column: 5
                currsigs.addElement(manual_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 25, column: 5
                currsigs.addElement(auto_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S1512=1;
            S1512=0;
            if(mode.getprestatus()){//sysj\controller.sysj line: 18, column: 13
              modeVal_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\controller.sysj line: 19, column: 5
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 21, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 22, column: 5
                currsigs.addElement(manual_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 25, column: 5
                currsigs.addElement(auto_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 21, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 22, column: 5
                currsigs.addElement(manual_1);
                S1512=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 25, column: 5
                currsigs.addElement(auto_1);
                S1512=1;
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

  public void thread3700(int [] tdone, int [] ends){
        S3695=1;
    twin_thread_3 = new TwinClient("lid-placer", "127.0.0.1", 9090);//sysj\controller.sysj line: 32, column: 3
    twin_thread_3.update(PlantTwin.State.IDLE);//sysj\controller.sysj line: 33, column: 3
    System.out.printf("main loop entered%n");//sysj\controller.sysj line: 34, column: 3
    S1781=0;
    System.out.printf("Auto: %d%n", 1);//sysj\controller.sysj line: 45, column: 6
    twin_thread_3.update(PlantTwin.State.IDLE);//sysj\controller.sysj line: 46, column: 6
    S1541=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread3699(int [] tdone, int [] ends){
        S1539=1;
    modeVal_thread_2 = 0;//sysj\controller.sysj line: 16, column: 3
    S1512=0;
    if(mode.getprestatus()){//sysj\controller.sysj line: 18, column: 13
      modeVal_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\controller.sysj line: 19, column: 5
      if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 21, column: 8
        manual_1.setPresent();//sysj\controller.sysj line: 22, column: 5
        currsigs.addElement(manual_1);
        S1512=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        auto_1.setPresent();//sysj\controller.sysj line: 25, column: 5
        currsigs.addElement(auto_1);
        S1512=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 21, column: 8
        manual_1.setPresent();//sysj\controller.sysj line: 22, column: 5
        currsigs.addElement(manual_1);
        S1512=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        auto_1.setPresent();//sysj\controller.sysj line: 25, column: 5
        currsigs.addElement(auto_1);
        S1512=1;
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
      switch(S3697){
        case 0 : 
          S3697=0;
          break RUN;
        
        case 1 : 
          S3697=2;
          S3697=2;
          auto_1.setClear();//sysj\controller.sysj line: 13, column: 2
          manual_1.setClear();//sysj\controller.sysj line: 13, column: 2
          thread3699(tdone,ends);
          thread3700(tdone,ends);
          int biggest3701 = 0;
          if(ends[2]>=biggest3701){
            biggest3701=ends[2];
          }
          if(ends[3]>=biggest3701){
            biggest3701=ends[3];
          }
          if(biggest3701 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          auto_1.setClear();//sysj\controller.sysj line: 13, column: 2
          manual_1.setClear();//sysj\controller.sysj line: 13, column: 2
          thread3702(tdone,ends);
          thread3703(tdone,ends);
          int biggest3710 = 0;
          if(ends[2]>=biggest3710){
            biggest3710=ends[2];
          }
          if(ends[3]>=biggest3710){
            biggest3710=ends[3];
          }
          if(biggest3710 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest3710 == 0){
            S3697=0;
            active[1]=0;
            ends[1]=0;
            S3697=0;
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
    auto_1 = new Signal();
    manual_1 = new Signal();
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
          pusherRetracted.gethook();
          pusherExtended.gethook();
          WPgripped.gethook();
          armAtSource.gethook();
          armAtDest.gethook();
          empty.gethook();
          request.gethook();
          mode.gethook();
          pusherExtendM.gethook();
          vacOnM.gethook();
          armSourceM.gethook();
          armDestM.gethook();
          df = true;
        }
        runClockDomain();
      }
      pusherRetracted.setpreclear();
      pusherExtended.setpreclear();
      WPgripped.setpreclear();
      armAtSource.setpreclear();
      armAtDest.setpreclear();
      empty.setpreclear();
      request.setpreclear();
      mode.setpreclear();
      pusherExtendM.setpreclear();
      vacOnM.setpreclear();
      armSourceM.setpreclear();
      armDestM.setpreclear();
      pusherExtend.setpreclear();
      vacOn.setpreclear();
      armSource.setpreclear();
      armDest.setpreclear();
      auto_1.setpreclear();
      manual_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = pusherRetracted.getStatus() ? pusherRetracted.setprepresent() : pusherRetracted.setpreclear();
      pusherRetracted.setpreval(pusherRetracted.getValue());
      pusherRetracted.setClear();
      dummyint = pusherExtended.getStatus() ? pusherExtended.setprepresent() : pusherExtended.setpreclear();
      pusherExtended.setpreval(pusherExtended.getValue());
      pusherExtended.setClear();
      dummyint = WPgripped.getStatus() ? WPgripped.setprepresent() : WPgripped.setpreclear();
      WPgripped.setpreval(WPgripped.getValue());
      WPgripped.setClear();
      dummyint = armAtSource.getStatus() ? armAtSource.setprepresent() : armAtSource.setpreclear();
      armAtSource.setpreval(armAtSource.getValue());
      armAtSource.setClear();
      dummyint = armAtDest.getStatus() ? armAtDest.setprepresent() : armAtDest.setpreclear();
      armAtDest.setpreval(armAtDest.getValue());
      armAtDest.setClear();
      dummyint = empty.getStatus() ? empty.setprepresent() : empty.setpreclear();
      empty.setpreval(empty.getValue());
      empty.setClear();
      dummyint = request.getStatus() ? request.setprepresent() : request.setpreclear();
      request.setpreval(request.getValue());
      request.setClear();
      dummyint = mode.getStatus() ? mode.setprepresent() : mode.setpreclear();
      mode.setpreval(mode.getValue());
      mode.setClear();
      dummyint = pusherExtendM.getStatus() ? pusherExtendM.setprepresent() : pusherExtendM.setpreclear();
      pusherExtendM.setpreval(pusherExtendM.getValue());
      pusherExtendM.setClear();
      dummyint = vacOnM.getStatus() ? vacOnM.setprepresent() : vacOnM.setpreclear();
      vacOnM.setpreval(vacOnM.getValue());
      vacOnM.setClear();
      dummyint = armSourceM.getStatus() ? armSourceM.setprepresent() : armSourceM.setpreclear();
      armSourceM.setpreval(armSourceM.getValue());
      armSourceM.setClear();
      dummyint = armDestM.getStatus() ? armDestM.setprepresent() : armDestM.setpreclear();
      armDestM.setpreval(armDestM.getValue());
      armDestM.setClear();
      pusherExtend.sethook();
      pusherExtend.setClear();
      vacOn.sethook();
      vacOn.setClear();
      armSource.sethook();
      armSource.setClear();
      armDest.sethook();
      armDest.setClear();
      auto_1.setClear();
      manual_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        pusherRetracted.gethook();
        pusherExtended.gethook();
        WPgripped.gethook();
        armAtSource.gethook();
        armAtDest.gethook();
        empty.gethook();
        request.gethook();
        mode.gethook();
        pusherExtendM.gethook();
        vacOnM.gethook();
        armSourceM.gethook();
        armDestM.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
