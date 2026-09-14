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
  private int capcount_thread_6;//sysj\plant.sysj line: 83, column: 3
  private int S18880 = 1;
  private int S17607 = 1;
  private int S17561 = 1;
  private int S17660 = 1;
  private int S17614 = 1;
  private int S17704 = 1;
  private int S17674 = 1;
  private int S17669 = 1;
  private int S17772 = 1;
  private int S17726 = 1;
  private int S17708 = 1;
  private int S17834 = 1;
  private int S17884 = 1;
  private int S17842 = 1;
  private int S17850 = 1;
  private int S17858 = 1;
  private int S17866 = 1;
  private int S17874 = 1;
  private int S17882 = 1;
  
  private int[] ends = new int[14];
  private int[] tdone = new int[14];
  
  public void thread18907(int [] tdone, int [] ends){
        switch(S17882){
      case 0 : 
        active[13]=0;
        ends[13]=0;
        tdone[13]=1;
        break;
      
      case 1 : 
        if(empty.getprestatus()){//sysj\plant.sysj line: 111, column: 24
          emptyE.setPresent();//sysj\plant.sysj line: 111, column: 31
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

  public void thread18906(int [] tdone, int [] ends){
        switch(S17874){
      case 0 : 
        active[12]=0;
        ends[12]=0;
        tdone[12]=1;
        break;
      
      case 1 : 
        if(armAtDest.getprestatus()){//sysj\plant.sysj line: 109, column: 24
          armAtDestE.setPresent();//sysj\plant.sysj line: 109, column: 35
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

  public void thread18905(int [] tdone, int [] ends){
        switch(S17866){
      case 0 : 
        active[11]=0;
        ends[11]=0;
        tdone[11]=1;
        break;
      
      case 1 : 
        if(armAtSource.getprestatus()){//sysj\plant.sysj line: 107, column: 24
          armAtSourceE.setPresent();//sysj\plant.sysj line: 107, column: 37
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

  public void thread18904(int [] tdone, int [] ends){
        switch(S17858){
      case 0 : 
        active[10]=0;
        ends[10]=0;
        tdone[10]=1;
        break;
      
      case 1 : 
        if(WPgripped.getprestatus()){//sysj\plant.sysj line: 105, column: 24
          WPgrippedE.setPresent();//sysj\plant.sysj line: 105, column: 35
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

  public void thread18903(int [] tdone, int [] ends){
        switch(S17850){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(pusherExtended.getprestatus()){//sysj\plant.sysj line: 103, column: 24
          pusherExtendedE.setPresent();//sysj\plant.sysj line: 103, column: 40
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

  public void thread18902(int [] tdone, int [] ends){
        switch(S17842){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 101, column: 24
          pusherRetractedE.setPresent();//sysj\plant.sysj line: 101, column: 41
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

  public void thread18901(int [] tdone, int [] ends){
        switch(S17884){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        thread18902(tdone,ends);
        thread18903(tdone,ends);
        thread18904(tdone,ends);
        thread18905(tdone,ends);
        thread18906(tdone,ends);
        thread18907(tdone,ends);
        int biggest18908 = 0;
        if(ends[8]>=biggest18908){
          biggest18908=ends[8];
        }
        if(ends[9]>=biggest18908){
          biggest18908=ends[9];
        }
        if(ends[10]>=biggest18908){
          biggest18908=ends[10];
        }
        if(ends[11]>=biggest18908){
          biggest18908=ends[11];
        }
        if(ends[12]>=biggest18908){
          biggest18908=ends[12];
        }
        if(ends[13]>=biggest18908){
          biggest18908=ends[13];
        }
        if(biggest18908 == 1){
          active[7]=1;
          ends[7]=1;
          tdone[7]=1;
        }
        //FINXME code
        if(biggest18908 == 0){
          S17884=0;
          active[7]=0;
          ends[7]=0;
          tdone[7]=1;
        }
        break;
      
    }
  }

  public void thread18900(int [] tdone, int [] ends){
        switch(S17834){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(capDec_1.getprestatus()){//sysj\plant.sysj line: 85, column: 12
          if(capcount_thread_6 > 0) {//sysj\plant.sysj line: 86, column: 5
            capcount_thread_6 = capcount_thread_6 - 1;//sysj\plant.sysj line: 87, column: 6
          }
          if(refill.getprestatus()){//sysj\plant.sysj line: 89, column: 12
            capcount_thread_6 = 5;//sysj\plant.sysj line: 90, column: 5
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
              empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
              empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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
          if(refill.getprestatus()){//sysj\plant.sysj line: 89, column: 12
            capcount_thread_6 = 5;//sysj\plant.sysj line: 90, column: 5
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
              empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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
            if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
              empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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

  public void thread18899(int [] tdone, int [] ends){
        switch(S17772){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S17726){
          case 0 : 
            if(empty.getprestatus()){//sysj\plant.sysj line: 68, column: 10
              S17726=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              switch(S17708){
                case 0 : 
                  if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 70, column: 13
                    S17708=1;
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
                  if(pusherExtended.getprestatus()){//sysj\plant.sysj line: 71, column: 13
                    capPos_1.setPresent();//sysj\plant.sysj line: 73, column: 7
                    currsigs.addElement(capPos_1);
                    capPos_1.setValue(1);//sysj\plant.sysj line: 73, column: 7
                    S17708=2;
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
                  if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 74, column: 13
                    capDec_1.setPresent();//sysj\plant.sysj line: 75, column: 7
                    currsigs.addElement(capDec_1);
                    S17708=0;
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
            if(refill.getprestatus()){//sysj\plant.sysj line: 78, column: 10
              S17726=0;
              S17708=0;
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

  public void thread18898(int [] tdone, int [] ends){
        switch(S17704){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S17674){
          case 0 : 
            switch(S17669){
              case 0 : 
                if(!vacOn.getprestatus()){//sysj\plant.sysj line: 48, column: 12
                  S17669=1;
                  if(armAtSource.getprestatus()){//sysj\plant.sysj line: 51, column: 14
                    capPos_1.setPresent();//sysj\plant.sysj line: 52, column: 7
                    currsigs.addElement(capPos_1);
                    capPos_1.setValue(1);//sysj\plant.sysj line: 52, column: 7
                    S17674=1;
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                  else {
                    S17674=1;
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                }
                else {
                  WPgripped.setPresent();//sysj\plant.sysj line: 49, column: 7
                  currsigs.addElement(WPgripped);
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
                break;
              
              case 1 : 
                S17674=0;
                if(vacOn.getprestatus() && armAtSource.getprestatus()){//sysj\plant.sysj line: 45, column: 12
                  if((Integer)(capPos_1.getpreval() == null ? 0 : ((Integer)capPos_1.getpreval()).intValue()) == 1){//sysj\plant.sysj line: 46, column: 8
                    capPos_1.setPresent();//sysj\plant.sysj line: 47, column: 6
                    currsigs.addElement(capPos_1);
                    capPos_1.setValue(0);//sysj\plant.sysj line: 47, column: 6
                    S17669=0;
                    WPgripped.setPresent();//sysj\plant.sysj line: 49, column: 7
                    currsigs.addElement(WPgripped);
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                  else {
                    S17674=1;
                    active[4]=1;
                    ends[4]=1;
                    tdone[4]=1;
                  }
                }
                else {
                  S17674=1;
                  active[4]=1;
                  ends[4]=1;
                  tdone[4]=1;
                }
                break;
              
            }
            break;
          
          case 1 : 
            S17674=1;
            S17674=0;
            if(vacOn.getprestatus() && armAtSource.getprestatus()){//sysj\plant.sysj line: 45, column: 12
              if((Integer)(capPos_1.getpreval() == null ? 0 : ((Integer)capPos_1.getpreval()).intValue()) == 1){//sysj\plant.sysj line: 46, column: 8
                capPos_1.setPresent();//sysj\plant.sysj line: 47, column: 6
                currsigs.addElement(capPos_1);
                capPos_1.setValue(0);//sysj\plant.sysj line: 47, column: 6
                S17669=0;
                WPgripped.setPresent();//sysj\plant.sysj line: 49, column: 7
                currsigs.addElement(WPgripped);
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
              else {
                S17674=1;
                active[4]=1;
                ends[4]=1;
                tdone[4]=1;
              }
            }
            else {
              S17674=1;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread18897(int [] tdone, int [] ends){
        switch(S17660){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S17614){
          case 0 : 
            if(pusherExtend.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 32, column: 10
              S17614=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              pusherRetracted.setPresent();//sysj\plant.sysj line: 33, column: 5
              currsigs.addElement(pusherRetracted);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 35, column: 10
              S17614=2;
              pusherExtended.setPresent();//sysj\plant.sysj line: 37, column: 5
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
            if(!pusherExtend.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 36, column: 10
              S17614=3;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              pusherExtended.setPresent();//sysj\plant.sysj line: 37, column: 5
              currsigs.addElement(pusherExtended);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 3 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 39, column: 10
              S17614=0;
              pusherRetracted.setPresent();//sysj\plant.sysj line: 33, column: 5
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

  public void thread18896(int [] tdone, int [] ends){
        switch(S17607){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S17561){
          case 0 : 
            if(armSource.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 19, column: 10
              S17561=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              armAtDest.setPresent();//sysj\plant.sysj line: 20, column: 5
              currsigs.addElement(armAtDest);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 22, column: 10
              S17561=2;
              armAtSource.setPresent();//sysj\plant.sysj line: 24, column: 5
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
            if(armDest.getprestatus() && enable.getprestatus()){//sysj\plant.sysj line: 23, column: 10
              S17561=3;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              armAtSource.setPresent();//sysj\plant.sysj line: 24, column: 5
              currsigs.addElement(armAtSource);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 3 : 
            if(!enable.getprestatus()){//sysj\plant.sysj line: 26, column: 10
              S17561=0;
              armAtDest.setPresent();//sysj\plant.sysj line: 20, column: 5
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

  public void thread18893(int [] tdone, int [] ends){
        S17882=1;
    if(empty.getprestatus()){//sysj\plant.sysj line: 111, column: 24
      emptyE.setPresent();//sysj\plant.sysj line: 111, column: 31
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

  public void thread18892(int [] tdone, int [] ends){
        S17874=1;
    if(armAtDest.getprestatus()){//sysj\plant.sysj line: 109, column: 24
      armAtDestE.setPresent();//sysj\plant.sysj line: 109, column: 35
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

  public void thread18891(int [] tdone, int [] ends){
        S17866=1;
    if(armAtSource.getprestatus()){//sysj\plant.sysj line: 107, column: 24
      armAtSourceE.setPresent();//sysj\plant.sysj line: 107, column: 37
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

  public void thread18890(int [] tdone, int [] ends){
        S17858=1;
    if(WPgripped.getprestatus()){//sysj\plant.sysj line: 105, column: 24
      WPgrippedE.setPresent();//sysj\plant.sysj line: 105, column: 35
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

  public void thread18889(int [] tdone, int [] ends){
        S17850=1;
    if(pusherExtended.getprestatus()){//sysj\plant.sysj line: 103, column: 24
      pusherExtendedE.setPresent();//sysj\plant.sysj line: 103, column: 40
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

  public void thread18888(int [] tdone, int [] ends){
        S17842=1;
    if(pusherRetracted.getprestatus()){//sysj\plant.sysj line: 101, column: 24
      pusherRetractedE.setPresent();//sysj\plant.sysj line: 101, column: 41
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

  public void thread18887(int [] tdone, int [] ends){
        S17884=1;
    thread18888(tdone,ends);
    thread18889(tdone,ends);
    thread18890(tdone,ends);
    thread18891(tdone,ends);
    thread18892(tdone,ends);
    thread18893(tdone,ends);
    int biggest18894 = 0;
    if(ends[8]>=biggest18894){
      biggest18894=ends[8];
    }
    if(ends[9]>=biggest18894){
      biggest18894=ends[9];
    }
    if(ends[10]>=biggest18894){
      biggest18894=ends[10];
    }
    if(ends[11]>=biggest18894){
      biggest18894=ends[11];
    }
    if(ends[12]>=biggest18894){
      biggest18894=ends[12];
    }
    if(ends[13]>=biggest18894){
      biggest18894=ends[13];
    }
    if(biggest18894 == 1){
      active[7]=1;
      ends[7]=1;
      tdone[7]=1;
    }
  }

  public void thread18886(int [] tdone, int [] ends){
        S17834=1;
    capcount_thread_6 = 5;//sysj\plant.sysj line: 83, column: 3
    if(capDec_1.getprestatus()){//sysj\plant.sysj line: 85, column: 12
      if(capcount_thread_6 > 0) {//sysj\plant.sysj line: 86, column: 5
        capcount_thread_6 = capcount_thread_6 - 1;//sysj\plant.sysj line: 87, column: 6
      }
      if(refill.getprestatus()){//sysj\plant.sysj line: 89, column: 12
        capcount_thread_6 = 5;//sysj\plant.sysj line: 90, column: 5
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
          empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
          empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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
      if(refill.getprestatus()){//sysj\plant.sysj line: 89, column: 12
        capcount_thread_6 = 5;//sysj\plant.sysj line: 90, column: 5
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
          empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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
        if(capcount_thread_6 == 0){//sysj\plant.sysj line: 93, column: 8
          empty.setPresent();//sysj\plant.sysj line: 94, column: 6
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

  public void thread18885(int [] tdone, int [] ends){
        S17772=1;
    S17726=0;
    S17708=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread18884(int [] tdone, int [] ends){
        S17704=1;
    S17674=0;
    if(vacOn.getprestatus() && armAtSource.getprestatus()){//sysj\plant.sysj line: 45, column: 12
      if((Integer)(capPos_1.getpreval() == null ? 0 : ((Integer)capPos_1.getpreval()).intValue()) == 1){//sysj\plant.sysj line: 46, column: 8
        capPos_1.setPresent();//sysj\plant.sysj line: 47, column: 6
        currsigs.addElement(capPos_1);
        capPos_1.setValue(0);//sysj\plant.sysj line: 47, column: 6
        S17669=0;
        WPgripped.setPresent();//sysj\plant.sysj line: 49, column: 7
        currsigs.addElement(WPgripped);
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
      else {
        S17674=1;
        active[4]=1;
        ends[4]=1;
        tdone[4]=1;
      }
    }
    else {
      S17674=1;
      active[4]=1;
      ends[4]=1;
      tdone[4]=1;
    }
  }

  public void thread18883(int [] tdone, int [] ends){
        S17660=1;
    S17614=0;
    pusherRetracted.setPresent();//sysj\plant.sysj line: 33, column: 5
    currsigs.addElement(pusherRetracted);
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread18882(int [] tdone, int [] ends){
        S17607=1;
    S17561=0;
    armAtDest.setPresent();//sysj\plant.sysj line: 20, column: 5
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
      switch(S18880){
        case 0 : 
          S18880=0;
          break RUN;
        
        case 1 : 
          S18880=2;
          S18880=2;
          new Thread(new GUI()).start();//sysj\plant.sysj line: 11, column: 2
          capDec_1.setClear();//sysj\plant.sysj line: 14, column: 2
          capPos_1.setClear();//sysj\plant.sysj line: 15, column: 2
          capPos_1.setPresent();//sysj\plant.sysj line: 16, column: 2
          currsigs.addElement(capPos_1);
          capPos_1.setValue(0);//sysj\plant.sysj line: 16, column: 2
          thread18882(tdone,ends);
          thread18883(tdone,ends);
          thread18884(tdone,ends);
          thread18885(tdone,ends);
          thread18886(tdone,ends);
          thread18887(tdone,ends);
          int biggest18895 = 0;
          if(ends[2]>=biggest18895){
            biggest18895=ends[2];
          }
          if(ends[3]>=biggest18895){
            biggest18895=ends[3];
          }
          if(ends[4]>=biggest18895){
            biggest18895=ends[4];
          }
          if(ends[5]>=biggest18895){
            biggest18895=ends[5];
          }
          if(ends[6]>=biggest18895){
            biggest18895=ends[6];
          }
          if(ends[7]>=biggest18895){
            biggest18895=ends[7];
          }
          if(biggest18895 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          capDec_1.setClear();//sysj\plant.sysj line: 14, column: 2
          capPos_1.setClear();//sysj\plant.sysj line: 15, column: 2
          thread18896(tdone,ends);
          thread18897(tdone,ends);
          thread18898(tdone,ends);
          thread18899(tdone,ends);
          thread18900(tdone,ends);
          thread18901(tdone,ends);
          int biggest18909 = 0;
          if(ends[2]>=biggest18909){
            biggest18909=ends[2];
          }
          if(ends[3]>=biggest18909){
            biggest18909=ends[3];
          }
          if(ends[4]>=biggest18909){
            biggest18909=ends[4];
          }
          if(ends[5]>=biggest18909){
            biggest18909=ends[5];
          }
          if(ends[6]>=biggest18909){
            biggest18909=ends[6];
          }
          if(ends[7]>=biggest18909){
            biggest18909=ends[7];
          }
          if(biggest18909 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest18909 == 0){
            S18880=0;
            active[1]=0;
            ends[1]=0;
            S18880=0;
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
