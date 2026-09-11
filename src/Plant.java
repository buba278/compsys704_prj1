import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.GUI;//sysj\plant.sysj line: 1, column: 1

public class Plant extends ClockDomain{
  public Plant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal pusherExtend = new Signal("pusherExtend", Signal.INPUT);
  public Signal vacOn = new Signal("vacOn", Signal.INPUT);
  public Signal armSource = new Signal("armSource", Signal.INPUT);
  public Signal armDest = new Signal("armDest", Signal.INPUT);
  public Signal enable = new Signal("enable", Signal.INPUT);
  public Signal refill = new Signal("refill", Signal.INPUT);
  public Signal pusherRetracted = new Signal("pusherRetracted", Signal.OUTPUT);
  public Signal pusherExtended = new Signal("pusherExtended", Signal.OUTPUT);
  public Signal WPgripped = new Signal("WPgripped", Signal.OUTPUT);
  public Signal armAtSource = new Signal("armAtSource", Signal.OUTPUT);
  public Signal armAtDest = new Signal("armAtDest", Signal.OUTPUT);
  public Signal empty = new Signal("empty", Signal.OUTPUT);
  public Signal pusherRetractedE = new Signal("pusherRetractedE", Signal.OUTPUT);
  public Signal pusherExtendedE = new Signal("pusherExtendedE", Signal.OUTPUT);
  public Signal WPgrippedE = new Signal("WPgrippedE", Signal.OUTPUT);
  public Signal armAtSourceE = new Signal("armAtSourceE", Signal.OUTPUT);
  public Signal armAtDestE = new Signal("armAtDestE", Signal.OUTPUT);
  public Signal emptyE = new Signal("emptyE", Signal.OUTPUT);
  private Signal capDec_1;
  private Signal capPos_1;
  private int capcount_thread_6;//sysj\plant.sysj line: 82, column: 3
  private int S3966 = 1;
  private int S2693 = 1;
  private int S2647 = 1;
  private int S2746 = 1;
  private int S2700 = 1;
  private int S2790 = 1;
  private int S2760 = 1;
  private int S2755 = 1;
  private int S2858 = 1;
  private int S2812 = 1;
  private int S2794 = 1;
  private int S2920 = 1;
  private int S2970 = 1;
  private int S2928 = 1;
  private int S2936 = 1;
  private int S2944 = 1;
  private int S2952 = 1;
  private int S2960 = 1;
  private int S2968 = 1;
  
  private int[] ends = new int[14];
  private int[] tdone = new int[14];
  
  public void thread3993(int [] tdone, int [] ends){
        switch(S2968){
      case 0 : 
        active[13]=0;
        ends[13]=0;
        tdone[13]=1;
        break;
      
      case 1 : 
        if(empty.getprestatus()){//sysj\plant.sysj line: 110, column: 24
          emptyE.setPresent();//sysj\plant.sysj line: 110, column: 31
          currsigs.addElement(emptyE);
          active[13]=1;
          ends[13]=1;
          tdone[13]=1;
        }
        else {
          active[13]=1;
          ends[13]=1;
          tdone[13]=1;
        }
        break;
      
    }
  }

  public void thread3992(int [] tdone, int [] ends){
        switch(S2960){
      case 0 : 
        active[12]=0;
        ends[12]=0;
        tdone[12]=1;
        break;
      
      case 1 : 
        if(armAtDest.getprestatus()){//sysj\plant.sysj line: 108, column: 24
          armAtDestE.setPresent();//sysj\plant.sysj line: 108, column: 35
          currsigs.addElement(armAtDestE);
          active[12]=1;
          ends[12]=1;
          tdone[12]=1;
        }
        else {
          active[12]=1;
          ends[12]=1;
          tdone[12]=1;
        }
        break;
      
    }
  }

  public void thread3991(int [] tdone, int [] ends){
        switch(S2952){
      case 0 : 
        active[11]=0;
        ends[11]=0;
        tdone[11]=1;
        break;
      
      case 1 : 
        if(armAtSource.getprestatus()){//sysj\plant.sysj line: 106, column: 24
          armAtSourceE.setPresent();//sysj\plant.sysj line: 106, column: 37
          currsigs.addElement(armAtSourceE);
          active[11]=1;
          ends[11]=1;
          tdone[11]=1;
        }
        else {
          active[11]=1;
          ends[11]=1;
          tdone[11]=1;
        }
        break;
      
    }
  }

  public void thread3990(int [] tdone, int [] ends){
        switch(S2944){
      case 0 : 
        active[10]=0;
        ends[10]=0;
        tdone[10]=1;
        break;
      
      case 1 : 
        if(WPgripped.getprestatus()){//sysj\plant.sysj line: 104, column: 24
          WPgrippedE.setPresent();//sysj\plant.sysj line: 104, column: 35
          currsigs.addElement(WPgrippedE);
          active[10]=1;
          ends[10]=1;
          tdone[10]=1;
        }
        else {
          active[10]=1;
          ends[10]=1;
          tdone[10]=1;
        }
        break;
      
    }
  }

  public void thread3989(int [] tdone, int [] ends){
        switch(S2936){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(pusherExtended.getprestatus()){//sysj\plant.sysj line: 102, column: 24
          pusherExtendedE.setPresent();//sysj\plant.sysj line: 102, column: 40
          currsigs.addElement(pusherExtendedE);
          active[9]=1;
          ends[9]=1;
          tdone[9]=1;
        }
        else {
          active[9]=1;
          ends[9]=1;
          tdone[9]=1;
        }
        break;
      
    }
  }

  public void thread3988(int [] tdone, int [] ends){
        switch(S2928){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 100, column: 24
          pusherRetractedE.setPresent();//sysj\plant.sysj line: 100, column: 41
          currsigs.addElement(pusherRetractedE);
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

  public void thread3987(int [] tdone, int [] ends){
        switch(S2970){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        thread3988(tdone,ends);
        thread3989(tdone,ends);
        thread3990(tdone,ends);
        thread3991(tdone,ends);
        thread3992(tdone,ends);
        thread3993(tdone,ends);
        int biggest3994 = 0;
        if(ends[8]>=biggest3994){
          biggest3994=ends[8];
        }
        if(ends[9]>=biggest3994){
          biggest3994=ends[9];
        }
        if(ends[10]>=biggest3994){
          biggest3994=ends[10];
        }
        if(ends[11]>=biggest3994){
          biggest3994=ends[11];
        }
        if(ends[12]>=biggest3994){
          biggest3994=ends[12];
        }
        if(ends[13]>=biggest3994){
          biggest3994=ends[13];
        }
        if(biggest3994 == 1){
          active[7]=1;
          ends[7]=1;
          tdone[7]=1;
        }
        //FINXME code
        if(biggest3994 == 0){
          S2970=0;
          active[7]=0;
          ends[7]=0;
          tdone[7]=1;
        }
        break;
      
    }
  }

  public void thread3986(int [] tdone, int [] ends){
        switch(S2920){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(capDec_1.getprestatus()){//sysj\plant.sysj line: 84, column: 12
          if(capcount_thread_6 > 0) {//sysj\plant.sysj line: 85, column: 5
            capcount_thread_6 = capcount_thread_6 - 1;//sysj\plant.sysj line: 86, column: 6
          }
          if(refill.getprestatus()){//sysj\plant.sysj line: 88, column: 12
            capcount_thread_6 = 5;//sysj\plant.sysj line: 89, column: 5
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
              empty.setPresent();//sysj\plant.sysj line: 93, column: 6
              currsigs.addElement(empty);
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
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
              empty.setPresent();//sysj\plant.sysj line: 93, column: 6
              currsigs.addElement(empty);
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
        else {
          if(refill.getprestatus()){//sysj\plant.sysj line: 88, column: 12
            capcount_thread_6 = 5;//sysj\plant.sysj line: 89, column: 5
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
              empty.setPresent();//sysj\plant.sysj line: 93, column: 6
              currsigs.addElement(empty);
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
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
              empty.setPresent();//sysj\plant.sysj line: 93, column: 6
              currsigs.addElement(empty);
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
        break;
      
    }
  }

  public void thread3985(int [] tdone, int [] ends){
        switch(S2858){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S2812){
          case 0 : 
            if(empty.getprestatus()){//sysj\plant.sysj line: 67, column: 10
              S2812=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              switch(S2794){
                case 0 : 
                  if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 69, column: 13
                    S2794=1;
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
                
                case 1 : 
                  if(pusherExtended.getprestatus()){//sysj\plant.sysj line: 70, column: 13
                    capPos_1.setPresent();//sysj\plant.sysj line: 72, column: 7
                    currsigs.addElement(capPos_1);
                    capPos_1.setValue(1);//sysj\plant.sysj line: 72, column: 7
                    S2794=2;
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
                
                case 2 : 
                  if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 73, column: 13
                    capDec_1.setPresent();//sysj\plant.sysj line: 74, column: 7
                    currsigs.addElement(capDec_1);
                    S2794=0;
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
            break;
          
          case 1 : 
            if(refill.getprestatus()){//sysj\plant.sysj line: 77, column: 10
              S2812=0;
              S2794=0;
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
        break;
      
    }
  }

  public void thread3984(int [] tdone, int [] ends){
        switch(S2790){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S2760){
          case 0 : 
            switch(S2755){
              case 0 : 
                if(!vacOn.getprestatus()){//sysj\plant.sysj line: 47, column: 12
                  S2755=1;
                  if(armAtSource.getprestatus()){//sysj\plant.sysj line: 50, column: 14
                    capPos_1.setPresent();//sysj\plant.sysj line: 51, column: 7
                    currsigs.addElement(capPos_1);
                    capPos_1.setValue(1);//sysj\plant.sysj line: 51, column: 7
                    S2760=1;
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                  else {
                    S2760=1;
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                }
                else {
                  WPgripped.setPresent();//sysj\plant.sysj line: 48, column: 7
                  currsigs.addElement(WPgripped);
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
                break;
              
              case 1 : 
                S2760=0;
                if(vacOn.getprestatus() && armAtSource.getprestatus()){//sysj\plant.sysj line: 44, column: 12
                  if((Integer)(capPos_1.getpreval() == null ? 0 : ((Integer)capPos_1.getpreval()).intValue()) == 1){//sysj\plant.sysj line: 45, column: 8
                    capPos_1.setPresent();//sysj\plant.sysj line: 46, column: 6
                    currsigs.addElement(capPos_1);
                    capPos_1.setValue(0);//sysj\plant.sysj line: 46, column: 6
                    S2755=0;
                    WPgripped.setPresent();//sysj\plant.sysj line: 48, column: 7
                    currsigs.addElement(WPgripped);
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                  else {
                    S2760=1;
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                }
                else {
                  S2760=1;
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
                break;
              
            }
            break;
          
          case 1 : 
            S2760=1;
            S2760=0;
            if(vacOn.getprestatus() && armAtSource.getprestatus()){//sysj\plant.sysj line: 44, column: 12
              if((Integer)(capPos_1.getpreval() == null ? 0 : ((Integer)capPos_1.getpreval()).intValue()) == 1){//sysj\plant.sysj line: 45, column: 8
                capPos_1.setPresent();//sysj\plant.sysj line: 46, column: 6
                currsigs.addElement(capPos_1);
                capPos_1.setValue(0);//sysj\plant.sysj line: 46, column: 6
                S2755=0;
                WPgripped.setPresent();//sysj\plant.sysj line: 48, column: 7
                currsigs.addElement(WPgripped);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                S2760=1;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            else {
              S2760=1;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread3983(int [] tdone, int [] ends){
        switch(S2746){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S2700){
          case 0 : 
            if(pusherExtend.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 31, column: 10
              S2700=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              pusherRetracted.setPresent();//sysj\plant.sysj line: 32, column: 5
              currsigs.addElement(pusherRetracted);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 34, column: 10
              S2700=2;
              pusherExtended.setPresent();//sysj\plant.sysj line: 36, column: 5
              currsigs.addElement(pusherExtended);
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
          
          case 2 : 
            if(!pusherExtend.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 35, column: 10
              S2700=3;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              pusherExtended.setPresent();//sysj\plant.sysj line: 36, column: 5
              currsigs.addElement(pusherExtended);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 3 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 38, column: 10
              S2700=0;
              pusherRetracted.setPresent();//sysj\plant.sysj line: 32, column: 5
              currsigs.addElement(pusherRetracted);
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

  public void thread3982(int [] tdone, int [] ends){
        switch(S2693){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S2647){
          case 0 : 
            if(armSource.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 18, column: 10
              S2647=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              armAtDest.setPresent();//sysj\plant.sysj line: 19, column: 5
              currsigs.addElement(armAtDest);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 21, column: 10
              S2647=2;
              armAtSource.setPresent();//sysj\plant.sysj line: 23, column: 5
              currsigs.addElement(armAtSource);
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
            if(armDest.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 22, column: 10
              S2647=3;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              armAtSource.setPresent();//sysj\plant.sysj line: 23, column: 5
              currsigs.addElement(armAtSource);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 3 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 25, column: 10
              S2647=0;
              armAtDest.setPresent();//sysj\plant.sysj line: 19, column: 5
              currsigs.addElement(armAtDest);
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
          
        }
        break;
      
    }
  }

  public void thread3979(int [] tdone, int [] ends){
        S2968=1;
    if(empty.getprestatus()){//sysj\plant.sysj line: 110, column: 24
      emptyE.setPresent();//sysj\plant.sysj line: 110, column: 31
      currsigs.addElement(emptyE);
      active[13]=1;
      ends[13]=1;
      tdone[13]=1;
    }
    else {
      active[13]=1;
      ends[13]=1;
      tdone[13]=1;
    }
  }

  public void thread3978(int [] tdone, int [] ends){
        S2960=1;
    if(armAtDest.getprestatus()){//sysj\plant.sysj line: 108, column: 24
      armAtDestE.setPresent();//sysj\plant.sysj line: 108, column: 35
      currsigs.addElement(armAtDestE);
      active[12]=1;
      ends[12]=1;
      tdone[12]=1;
    }
    else {
      active[12]=1;
      ends[12]=1;
      tdone[12]=1;
    }
  }

  public void thread3977(int [] tdone, int [] ends){
        S2952=1;
    if(armAtSource.getprestatus()){//sysj\plant.sysj line: 106, column: 24
      armAtSourceE.setPresent();//sysj\plant.sysj line: 106, column: 37
      currsigs.addElement(armAtSourceE);
      active[11]=1;
      ends[11]=1;
      tdone[11]=1;
    }
    else {
      active[11]=1;
      ends[11]=1;
      tdone[11]=1;
    }
  }

  public void thread3976(int [] tdone, int [] ends){
        S2944=1;
    if(WPgripped.getprestatus()){//sysj\plant.sysj line: 104, column: 24
      WPgrippedE.setPresent();//sysj\plant.sysj line: 104, column: 35
      currsigs.addElement(WPgrippedE);
      active[10]=1;
      ends[10]=1;
      tdone[10]=1;
    }
    else {
      active[10]=1;
      ends[10]=1;
      tdone[10]=1;
    }
  }

  public void thread3975(int [] tdone, int [] ends){
        S2936=1;
    if(pusherExtended.getprestatus()){//sysj\plant.sysj line: 102, column: 24
      pusherExtendedE.setPresent();//sysj\plant.sysj line: 102, column: 40
      currsigs.addElement(pusherExtendedE);
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

  public void thread3974(int [] tdone, int [] ends){
        S2928=1;
    if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 100, column: 24
      pusherRetractedE.setPresent();//sysj\plant.sysj line: 100, column: 41
      currsigs.addElement(pusherRetractedE);
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

  public void thread3973(int [] tdone, int [] ends){
        S2970=1;
    thread3974(tdone,ends);
    thread3975(tdone,ends);
    thread3976(tdone,ends);
    thread3977(tdone,ends);
    thread3978(tdone,ends);
    thread3979(tdone,ends);
    int biggest3980 = 0;
    if(ends[8]>=biggest3980){
      biggest3980=ends[8];
    }
    if(ends[9]>=biggest3980){
      biggest3980=ends[9];
    }
    if(ends[10]>=biggest3980){
      biggest3980=ends[10];
    }
    if(ends[11]>=biggest3980){
      biggest3980=ends[11];
    }
    if(ends[12]>=biggest3980){
      biggest3980=ends[12];
    }
    if(ends[13]>=biggest3980){
      biggest3980=ends[13];
    }
    if(biggest3980 == 1){
      active[7]=1;
      ends[7]=1;
      tdone[7]=1;
    }
  }

  public void thread3972(int [] tdone, int [] ends){
        S2920=1;
    capcount_thread_6 = 5;//sysj\plant.sysj line: 82, column: 3
    if(capDec_1.getprestatus()){//sysj\plant.sysj line: 84, column: 12
      if(capcount_thread_6 > 0) {//sysj\plant.sysj line: 85, column: 5
        capcount_thread_6 = capcount_thread_6 - 1;//sysj\plant.sysj line: 86, column: 6
      }
      if(refill.getprestatus()){//sysj\plant.sysj line: 88, column: 12
        capcount_thread_6 = 5;//sysj\plant.sysj line: 89, column: 5
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
          empty.setPresent();//sysj\plant.sysj line: 93, column: 6
          currsigs.addElement(empty);
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
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
          empty.setPresent();//sysj\plant.sysj line: 93, column: 6
          currsigs.addElement(empty);
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
    else {
      if(refill.getprestatus()){//sysj\plant.sysj line: 88, column: 12
        capcount_thread_6 = 5;//sysj\plant.sysj line: 89, column: 5
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
          empty.setPresent();//sysj\plant.sysj line: 93, column: 6
          currsigs.addElement(empty);
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
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 92, column: 8
          empty.setPresent();//sysj\plant.sysj line: 93, column: 6
          currsigs.addElement(empty);
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
  }

  public void thread3971(int [] tdone, int [] ends){
        S2858=1;
    S2812=0;
    S2794=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread3970(int [] tdone, int [] ends){
        S2790=1;
    S2760=0;
    if(vacOn.getprestatus() && armAtSource.getprestatus()){//sysj\plant.sysj line: 44, column: 12
      if((Integer)(capPos_1.getpreval() == null ? 0 : ((Integer)capPos_1.getpreval()).intValue()) == 1){//sysj\plant.sysj line: 45, column: 8
        capPos_1.setPresent();//sysj\plant.sysj line: 46, column: 6
        currsigs.addElement(capPos_1);
        capPos_1.setValue(0);//sysj\plant.sysj line: 46, column: 6
        S2755=0;
        WPgripped.setPresent();//sysj\plant.sysj line: 48, column: 7
        currsigs.addElement(WPgripped);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
      else {
        S2760=1;
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
    }
    else {
      S2760=1;
      active[4]=1;
      ends[4]=1;
      tdone[4]=1;
    }
  }

  public void thread3969(int [] tdone, int [] ends){
        S2746=1;
    S2700=0;
    pusherRetracted.setPresent();//sysj\plant.sysj line: 32, column: 5
    currsigs.addElement(pusherRetracted);
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread3968(int [] tdone, int [] ends){
        S2693=1;
    S2647=0;
    armAtDest.setPresent();//sysj\plant.sysj line: 19, column: 5
    currsigs.addElement(armAtDest);
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
      switch(S3966){
        case 0 : 
          S3966=0;
          break RUN;
        
        case 1 : 
          S3966=2;
          S3966=2;
          new Thread(new GUI()).start();//sysj\plant.sysj line: 11, column: 2
          capDec_1.setClear();//sysj\plant.sysj line: 13, column: 2
          capPos_1.setClear();//sysj\plant.sysj line: 14, column: 2
          capPos_1.setPresent();//sysj\plant.sysj line: 15, column: 2
          currsigs.addElement(capPos_1);
          capPos_1.setValue(0);//sysj\plant.sysj line: 15, column: 2
          thread3968(tdone,ends);
          thread3969(tdone,ends);
          thread3970(tdone,ends);
          thread3971(tdone,ends);
          thread3972(tdone,ends);
          thread3973(tdone,ends);
          int biggest3981 = 0;
          if(ends[2]>=biggest3981){
            biggest3981=ends[2];
          }
          if(ends[3]>=biggest3981){
            biggest3981=ends[3];
          }
          if(ends[4]>=biggest3981){
            biggest3981=ends[4];
          }
          if(ends[5]>=biggest3981){
            biggest3981=ends[5];
          }
          if(ends[6]>=biggest3981){
            biggest3981=ends[6];
          }
          if(ends[7]>=biggest3981){
            biggest3981=ends[7];
          }
          if(biggest3981 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          capDec_1.setClear();//sysj\plant.sysj line: 13, column: 2
          capPos_1.setClear();//sysj\plant.sysj line: 14, column: 2
          thread3982(tdone,ends);
          thread3983(tdone,ends);
          thread3984(tdone,ends);
          thread3985(tdone,ends);
          thread3986(tdone,ends);
          thread3987(tdone,ends);
          int biggest3995 = 0;
          if(ends[2]>=biggest3995){
            biggest3995=ends[2];
          }
          if(ends[3]>=biggest3995){
            biggest3995=ends[3];
          }
          if(ends[4]>=biggest3995){
            biggest3995=ends[4];
          }
          if(ends[5]>=biggest3995){
            biggest3995=ends[5];
          }
          if(ends[6]>=biggest3995){
            biggest3995=ends[6];
          }
          if(ends[7]>=biggest3995){
            biggest3995=ends[7];
          }
          if(biggest3995 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest3995 == 0){
            S3966=0;
            active[1]=0;
            ends[1]=0;
            S3966=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    paused = paused1;
    active = active1;
    suspended = suspended1;
    // Now instantiate all the local signals ONLY
    capDec_1 = new Signal();
    capPos_1 = new Signal();
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
          pusherExtend.gethook();
          vacOn.gethook();
          armSource.gethook();
          armDest.gethook();
          enable.gethook();
          refill.gethook();
          df = true;
        }
        runClockDomain();
      }
      pusherExtend.setpreclear();
      vacOn.setpreclear();
      armSource.setpreclear();
      armDest.setpreclear();
      enable.setpreclear();
      refill.setpreclear();
      pusherRetracted.setpreclear();
      pusherExtended.setpreclear();
      WPgripped.setpreclear();
      armAtSource.setpreclear();
      armAtDest.setpreclear();
      empty.setpreclear();
      pusherRetractedE.setpreclear();
      pusherExtendedE.setpreclear();
      WPgrippedE.setpreclear();
      armAtSourceE.setpreclear();
      armAtDestE.setpreclear();
      emptyE.setpreclear();
      capDec_1.setpreclear();
      capPos_1.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = pusherExtend.getStatus() ? pusherExtend.setprepresent() : pusherExtend.setpreclear();
      pusherExtend.setpreval(pusherExtend.getValue());
      pusherExtend.setClear();
      dummyint = vacOn.getStatus() ? vacOn.setprepresent() : vacOn.setpreclear();
      vacOn.setpreval(vacOn.getValue());
      vacOn.setClear();
      dummyint = armSource.getStatus() ? armSource.setprepresent() : armSource.setpreclear();
      armSource.setpreval(armSource.getValue());
      armSource.setClear();
      dummyint = armDest.getStatus() ? armDest.setprepresent() : armDest.setpreclear();
      armDest.setpreval(armDest.getValue());
      armDest.setClear();
      dummyint = enable.getStatus() ? enable.setprepresent() : enable.setpreclear();
      enable.setpreval(enable.getValue());
      enable.setClear();
      dummyint = refill.getStatus() ? refill.setprepresent() : refill.setpreclear();
      refill.setpreval(refill.getValue());
      refill.setClear();
      pusherRetracted.sethook();
      pusherRetracted.setClear();
      pusherExtended.sethook();
      pusherExtended.setClear();
      WPgripped.sethook();
      WPgripped.setClear();
      armAtSource.sethook();
      armAtSource.setClear();
      armAtDest.sethook();
      armAtDest.setClear();
      empty.sethook();
      empty.setClear();
      pusherRetractedE.sethook();
      pusherRetractedE.setClear();
      pusherExtendedE.sethook();
      pusherExtendedE.setClear();
      WPgrippedE.sethook();
      WPgrippedE.setClear();
      armAtSourceE.sethook();
      armAtSourceE.setClear();
      armAtDestE.sethook();
      armAtDestE.setClear();
      emptyE.sethook();
      emptyE.setClear();
      capDec_1.setClear();
      capPos_1.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        pusherExtend.gethook();
        vacOn.gethook();
        armSource.gethook();
        armDest.gethook();
        enable.gethook();
        refill.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
