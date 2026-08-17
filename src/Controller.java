import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

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
  private int modeVal_thread_2;//sysj\controller.sysj line: 12, column: 3
  private int S1147 = 1;
  private int S33 = 1;
  private int S6 = 1;
  private int S1145 = 1;
  private int S100 = 1;
  private int S36 = 1;
  private int S67 = 1;
  private int S72 = 1;
  
  private int[] ends = new int[6];
  private int[] tdone = new int[6];
  
  public void thread1158(int [] tdone, int [] ends){
        switch(S72){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        armDest.setPresent();//sysj\controller.sysj line: 54, column: 7
        currsigs.addElement(armDest);
        active[5]=1;
        ends[5]=1;
        tdone[5]=1;
        break;
      
    }
  }

  public void thread1157(int [] tdone, int [] ends){
        switch(S67){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        vacOn.setPresent();//sysj\controller.sysj line: 52, column: 7
        currsigs.addElement(vacOn);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
        break;
      
    }
  }

  public void thread1155(int [] tdone, int [] ends){
        S72=1;
    armDest.setPresent();//sysj\controller.sysj line: 54, column: 7
    currsigs.addElement(armDest);
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread1154(int [] tdone, int [] ends){
        S67=1;
    vacOn.setPresent();//sysj\controller.sysj line: 52, column: 7
    currsigs.addElement(vacOn);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread1153(int [] tdone, int [] ends){
        switch(S1145){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S100){
          case 0 : 
            if(manual_1.getprestatus()){//sysj\controller.sysj line: 30, column: 11
              S100=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              switch(S36){
                case 0 : 
                  if(request.getprestatus()){//sysj\controller.sysj line: 33, column: 12
                    System.out.printf("request received%n");//sysj\controller.sysj line: 34, column: 5
                    S36=1;
                    armDest.setPresent();//sysj\controller.sysj line: 36, column: 6
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
                  if(armAtDest.getprestatus()){//sysj\controller.sysj line: 35, column: 12
                    S36=2;
                    pusherExtend.setPresent();//sysj\controller.sysj line: 41, column: 6
                    currsigs.addElement(pusherExtend);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    armDest.setPresent();//sysj\controller.sysj line: 36, column: 6
                    currsigs.addElement(armDest);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 2 : 
                  if(pusherExtended.getprestatus()){//sysj\controller.sysj line: 40, column: 12
                    S36=3;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    pusherExtend.setPresent();//sysj\controller.sysj line: 41, column: 6
                    currsigs.addElement(pusherExtend);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 3 : 
                  if(pusherRetracted.getprestatus()){//sysj\controller.sysj line: 44, column: 12
                    S36=4;
                    armSource.setPresent();//sysj\controller.sysj line: 47, column: 6
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
                  if(armAtSource.getprestatus()){//sysj\controller.sysj line: 46, column: 12
                    S36=5;
                    thread1154(tdone,ends);
                    thread1155(tdone,ends);
                    int biggest1156 = 0;
                    if(ends[4]>=biggest1156){
                      biggest1156=ends[4];
                    }
                    if(ends[5]>=biggest1156){
                      biggest1156=ends[5];
                    }
                    if(biggest1156 == 1){
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  else {
                    armSource.setPresent();//sysj\controller.sysj line: 47, column: 6
                    currsigs.addElement(armSource);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
                case 5 : 
                  if(WPgripped.getprestatus() && armAtDest.getprestatus()){//sysj\controller.sysj line: 51, column: 12
                    S36=6;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    thread1157(tdone,ends);
                    thread1158(tdone,ends);
                    int biggest1159 = 0;
                    if(ends[4]>=biggest1159){
                      biggest1159=ends[4];
                    }
                    if(ends[5]>=biggest1159){
                      biggest1159=ends[5];
                    }
                    if(biggest1159 == 1){
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                    //FINXME code
                    if(biggest1159 == 0){
                      S36=6;
                      active[3]=1;
                      ends[3]=1;
                      tdone[3]=1;
                    }
                  }
                  break;
                
                case 6 : 
                  if(!WPgripped.getprestatus()){//sysj\controller.sysj line: 57, column: 11
                    S36=7;
                    armSource.setPresent();//sysj\controller.sysj line: 60, column: 6
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
                  if(armAtSource.getprestatus()){//sysj\controller.sysj line: 59, column: 12
                    S100=1;
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  else {
                    armSource.setPresent();//sysj\controller.sysj line: 60, column: 6
                    currsigs.addElement(armSource);
                    active[3]=1;
                    ends[3]=1;
                    tdone[3]=1;
                  }
                  break;
                
              }
            }
            break;
          
          case 1 : 
            if(manual_1.getprestatus()){//sysj\controller.sysj line: 63, column: 11
              System.out.printf("Manual: %d%n", 1);//sysj\controller.sysj line: 64, column: 4
              S100=2;
              if(pusherExtendM.getprestatus()){//sysj\controller.sysj line: 67, column: 15
                pusherExtend.setPresent();//sysj\controller.sysj line: 68, column: 7
                currsigs.addElement(pusherExtend);
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 70, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 71, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 70, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 71, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
            if(auto_1.getprestatus()){//sysj\controller.sysj line: 65, column: 11
              S100=3;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              if(pusherExtendM.getprestatus()){//sysj\controller.sysj line: 67, column: 15
                pusherExtend.setPresent();//sysj\controller.sysj line: 68, column: 7
                currsigs.addElement(pusherExtend);
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 70, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 71, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                if(vacOnM.getprestatus()){//sysj\controller.sysj line: 70, column: 15
                  vacOn.setPresent();//sysj\controller.sysj line: 71, column: 7
                  currsigs.addElement(vacOn);
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                  if(armSourceM.getprestatus()){//sysj\controller.sysj line: 73, column: 15
                    armSource.setPresent();//sysj\controller.sysj line: 74, column: 7
                    currsigs.addElement(armSource);
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
                    if(armDestM.getprestatus()){//sysj\controller.sysj line: 76, column: 15
                      armDest.setPresent();//sysj\controller.sysj line: 77, column: 7
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
            if(auto_1.getprestatus()){//sysj\controller.sysj line: 82, column: 11
              S100=0;
              System.out.printf("Auto: %d%n", 1);//sysj\controller.sysj line: 31, column: 5
              S36=0;
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

  public void thread1152(int [] tdone, int [] ends){
        switch(S33){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S6){
          case 0 : 
            S6=0;
            if(mode.getprestatus()){//sysj\controller.sysj line: 14, column: 13
              modeVal_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\controller.sysj line: 15, column: 5
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 17, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 18, column: 5
                currsigs.addElement(manual_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 21, column: 5
                currsigs.addElement(auto_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 17, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 18, column: 5
                currsigs.addElement(manual_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 21, column: 5
                currsigs.addElement(auto_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            break;
          
          case 1 : 
            S6=1;
            S6=0;
            if(mode.getprestatus()){//sysj\controller.sysj line: 14, column: 13
              modeVal_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\controller.sysj line: 15, column: 5
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 17, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 18, column: 5
                currsigs.addElement(manual_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 21, column: 5
                currsigs.addElement(auto_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
            }
            else {
              if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 17, column: 8
                manual_1.setPresent();//sysj\controller.sysj line: 18, column: 5
                currsigs.addElement(manual_1);
                S6=1;
                active[2]=1;
                ends[2]=1;
                tdone[2]=1;
              }
              else {
                auto_1.setPresent();//sysj\controller.sysj line: 21, column: 5
                currsigs.addElement(auto_1);
                S6=1;
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

  public void thread1150(int [] tdone, int [] ends){
        S1145=1;
    System.out.printf("main loop entered%n");//sysj\controller.sysj line: 28, column: 3
    S100=0;
    System.out.printf("Auto: %d%n", 1);//sysj\controller.sysj line: 31, column: 5
    S36=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread1149(int [] tdone, int [] ends){
        S33=1;
    modeVal_thread_2 = 0;//sysj\controller.sysj line: 12, column: 3
    S6=0;
    if(mode.getprestatus()){//sysj\controller.sysj line: 14, column: 13
      modeVal_thread_2 = (Integer)(mode.getpreval() == null ? null : ((Integer)mode.getpreval()));//sysj\controller.sysj line: 15, column: 5
      if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 17, column: 8
        manual_1.setPresent();//sysj\controller.sysj line: 18, column: 5
        currsigs.addElement(manual_1);
        S6=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        auto_1.setPresent();//sysj\controller.sysj line: 21, column: 5
        currsigs.addElement(auto_1);
        S6=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
    }
    else {
      if(modeVal_thread_2 == 1){//sysj\controller.sysj line: 17, column: 8
        manual_1.setPresent();//sysj\controller.sysj line: 18, column: 5
        currsigs.addElement(manual_1);
        S6=1;
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
      }
      else {
        auto_1.setPresent();//sysj\controller.sysj line: 21, column: 5
        currsigs.addElement(auto_1);
        S6=1;
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
      switch(S1147){
        case 0 : 
          S1147=0;
          break RUN;
        
        case 1 : 
          S1147=2;
          S1147=2;
          auto_1.setClear();//sysj\controller.sysj line: 9, column: 2
          manual_1.setClear();//sysj\controller.sysj line: 9, column: 2
          thread1149(tdone,ends);
          thread1150(tdone,ends);
          int biggest1151 = 0;
          if(ends[2]>=biggest1151){
            biggest1151=ends[2];
          }
          if(ends[3]>=biggest1151){
            biggest1151=ends[3];
          }
          if(biggest1151 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          auto_1.setClear();//sysj\controller.sysj line: 9, column: 2
          manual_1.setClear();//sysj\controller.sysj line: 9, column: 2
          thread1152(tdone,ends);
          thread1153(tdone,ends);
          int biggest1160 = 0;
          if(ends[2]>=biggest1160){
            biggest1160=ends[2];
          }
          if(ends[3]>=biggest1160){
            biggest1160=ends[3];
          }
          if(biggest1160 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest1160 == 0){
            S1147=0;
            active[1]=0;
            ends[1]=0;
            S1147=0;
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
