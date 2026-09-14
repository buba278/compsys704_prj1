import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.CapperGUI;//sysj\capperPlant.sysj line: 1, column: 1

public class CapperPlant extends ClockDomain{
  public CapperPlant(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal sendGripperDown = new Signal("sendGripperDown", Signal.INPUT);
  public Signal sendGripperTwist = new Signal("sendGripperTwist", Signal.INPUT);
  public Signal sendGripperUntwist = new Signal("sendGripperUntwist", Signal.INPUT);
  public Signal sendGripCap = new Signal("sendGripCap", Signal.INPUT);
  public Signal sendClamp = new Signal("sendClamp", Signal.INPUT);
  public Signal enable = new Signal("enable", Signal.INPUT);
  public Signal bottleAtPos4 = new Signal("bottleAtPos4", Signal.INPUT);
  public Signal gripperMaxLower = new Signal("gripperMaxLower", Signal.OUTPUT);
  public Signal gripperMaxLift = new Signal("gripperMaxLift", Signal.OUTPUT);
  public Signal capGripped = new Signal("capGripped", Signal.OUTPUT);
  public Signal gripperInitPos = new Signal("gripperInitPos", Signal.OUTPUT);
  public Signal gripperFullTwist = new Signal("gripperFullTwist", Signal.OUTPUT);
  public Signal bottleAtPos4E = new Signal("bottleAtPos4E", Signal.OUTPUT);
  public Signal bottleGoneE = new Signal("bottleGoneE", Signal.OUTPUT);
  public Signal gripperMaxLowerE = new Signal("gripperMaxLowerE", Signal.OUTPUT);
  public Signal gripperMaxLiftE = new Signal("gripperMaxLiftE", Signal.OUTPUT);
  public Signal gripperInitPosE = new Signal("gripperInitPosE", Signal.OUTPUT);
  public Signal gripperFullTwistE = new Signal("gripperFullTwistE", Signal.OUTPUT);
  private int S1247 = 1;
  private int S947 = 1;
  private int S901 = 1;
  private int S985 = 1;
  private int S949 = 1;
  private int S1038 = 1;
  private int S990 = 1;
  private int S1046 = 1;
  private int S1040 = 1;
  private int S1054 = 1;
  private int S1062 = 1;
  private int S1070 = 1;
  
  private int[] ends = new int[9];
  private int[] tdone = new int[9];
  
  public void thread1263(int [] tdone, int [] ends){
        switch(S1070){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(gripperFullTwist.getprestatus()){//sysj\capperPlant.sysj line: 96, column: 24
          gripperFullTwistE.setPresent();//sysj\capperPlant.sysj line: 96, column: 42
          currsigs.addElement(gripperFullTwistE);
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

  public void thread1262(int [] tdone, int [] ends){
        switch(S1062){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(gripperInitPos.getprestatus()){//sysj\capperPlant.sysj line: 94, column: 24
          gripperInitPosE.setPresent();//sysj\capperPlant.sysj line: 94, column: 40
          currsigs.addElement(gripperInitPosE);
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

  public void thread1261(int [] tdone, int [] ends){
        switch(S1054){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(gripperMaxLift.getprestatus()){//sysj\capperPlant.sysj line: 92, column: 24
          gripperMaxLiftE.setPresent();//sysj\capperPlant.sysj line: 92, column: 40
          currsigs.addElement(gripperMaxLiftE);
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

  public void thread1260(int [] tdone, int [] ends){
        switch(S1046){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S1040){
          case 0 : 
            S1040=0;
            if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 90, column: 24
              gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 90, column: 41
              currsigs.addElement(gripperMaxLowerE);
              S1040=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              S1040=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 1 : 
            S1040=1;
            S1040=0;
            if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 90, column: 24
              gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 90, column: 41
              currsigs.addElement(gripperMaxLowerE);
              S1040=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              S1040=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread1259(int [] tdone, int [] ends){
        switch(S1038){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S990){
          case 0 : 
            if(sendGripperTwist.getprestatus() && enable.getprestatus()){//sysj\capperPlant.sysj line: 70, column: 19
              System.out.println("CapperPlant [Rotation]: Twisting cap closed...");//sysj\capperPlant.sysj line: 73, column: 13
              S990=1;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 71, column: 17
              currsigs.addElement(gripperInitPos);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            if(!enable.getprestatus()){//sysj\capperPlant.sysj line: 74, column: 19
              System.out.println("CapperPlant [Rotation]: Full twist achieved.");//sysj\capperPlant.sysj line: 76, column: 13
              S990=2;
              gripperFullTwist.setPresent();//sysj\capperPlant.sysj line: 78, column: 17
              currsigs.addElement(gripperFullTwist);
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
          
          case 2 : 
            if(sendGripperUntwist.getprestatus() && enable.getprestatus()){//sysj\capperPlant.sysj line: 77, column: 19
              S990=3;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              gripperFullTwist.setPresent();//sysj\capperPlant.sysj line: 78, column: 17
              currsigs.addElement(gripperFullTwist);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 3 : 
            if(!enable.getprestatus()){//sysj\capperPlant.sysj line: 80, column: 19
              System.out.println("CapperPlant [Rotation]: Untwisting back to start position...");//sysj\capperPlant.sysj line: 81, column: 13
              S990=0;
              gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 71, column: 17
              currsigs.addElement(gripperInitPos);
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

  public void thread1258(int [] tdone, int [] ends){
        switch(S985){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S949){
          case 0 : 
            if(sendGripCap.getprestatus() && enable.getprestatus()){//sysj\capperPlant.sysj line: 56, column: 19
              System.out.println("CapperPlant [Gripper Jaw]: Closing jaws around cap...");//sysj\capperPlant.sysj line: 57, column: 13
              S949=1;
              capGripped.setPresent();//sysj\capperPlant.sysj line: 60, column: 17
              currsigs.addElement(capGripped);
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
            if(!sendGripCap.getprestatus() && enable.getprestatus()){//sysj\capperPlant.sysj line: 59, column: 19
              System.out.println("CapperPlant [Gripper Jaw]: Jaws opened, cap released.");//sysj\capperPlant.sysj line: 62, column: 13
              S949=2;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              capGripped.setPresent();//sysj\capperPlant.sysj line: 60, column: 17
              currsigs.addElement(capGripped);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            if(!enable.getprestatus()){//sysj\capperPlant.sysj line: 63, column: 19
              S949=0;
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

  public void thread1257(int [] tdone, int [] ends){
        switch(S947){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S901){
          case 0 : 
            if(sendGripperDown.getprestatus() && enable.getprestatus()){//sysj\capperPlant.sysj line: 34, column: 19
              S901=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 35, column: 17
              currsigs.addElement(gripperMaxLift);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            if(!enable.getprestatus()){//sysj\capperPlant.sysj line: 37, column: 19
              System.out.println("CapperPlant [Z-Axis]: Moving down...");//sysj\capperPlant.sysj line: 38, column: 13
              S901=2;
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
            S901=2;
            S901=3;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 3 : 
            S901=3;
            System.out.println("CapperPlant [Z-Axis]: Reached bottom sensor.");//sysj\capperPlant.sysj line: 42, column: 13
            S901=4;
            gripperMaxLower.setPresent();//sysj\capperPlant.sysj line: 44, column: 17
            currsigs.addElement(gripperMaxLower);
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 4 : 
            if(!sendGripperDown.getprestatus() && enable.getprestatus()){//sysj\capperPlant.sysj line: 43, column: 19
              S901=5;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              gripperMaxLower.setPresent();//sysj\capperPlant.sysj line: 44, column: 17
              currsigs.addElement(gripperMaxLower);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 5 : 
            if(!enable.getprestatus()){//sysj\capperPlant.sysj line: 46, column: 19
              System.out.println("CapperPlant [Z-Axis]: Moving back up...");//sysj\capperPlant.sysj line: 47, column: 13
              S901=0;
              gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 35, column: 17
              currsigs.addElement(gripperMaxLift);
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

  public void thread1255(int [] tdone, int [] ends){
        S1070=1;
    if(gripperFullTwist.getprestatus()){//sysj\capperPlant.sysj line: 96, column: 24
      gripperFullTwistE.setPresent();//sysj\capperPlant.sysj line: 96, column: 42
      currsigs.addElement(gripperFullTwistE);
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

  public void thread1254(int [] tdone, int [] ends){
        S1062=1;
    if(gripperInitPos.getprestatus()){//sysj\capperPlant.sysj line: 94, column: 24
      gripperInitPosE.setPresent();//sysj\capperPlant.sysj line: 94, column: 40
      currsigs.addElement(gripperInitPosE);
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

  public void thread1253(int [] tdone, int [] ends){
        S1054=1;
    if(gripperMaxLift.getprestatus()){//sysj\capperPlant.sysj line: 92, column: 24
      gripperMaxLiftE.setPresent();//sysj\capperPlant.sysj line: 92, column: 40
      currsigs.addElement(gripperMaxLiftE);
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

  public void thread1252(int [] tdone, int [] ends){
        S1046=1;
    S1040=0;
    if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 90, column: 24
      gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 90, column: 41
      currsigs.addElement(gripperMaxLowerE);
      S1040=1;
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
    else {
      S1040=1;
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread1251(int [] tdone, int [] ends){
        S1038=1;
    S990=0;
    gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 71, column: 17
    currsigs.addElement(gripperInitPos);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread1250(int [] tdone, int [] ends){
        S985=1;
    S949=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread1249(int [] tdone, int [] ends){
        S947=1;
    S901=0;
    gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 35, column: 17
    currsigs.addElement(gripperMaxLift);
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
      switch(S1247){
        case 0 : 
          S1247=0;
          break RUN;
        
        case 1 : 
          S1247=2;
          S1247=2;
          new Thread(new CapperGUI()).start();//sysj\capperPlant.sysj line: 27, column: 2
          System.out.println("CapperPlant: Subsystem initialized.");//sysj\capperPlant.sysj line: 28, column: 5
          thread1249(tdone,ends);
          thread1250(tdone,ends);
          thread1251(tdone,ends);
          thread1252(tdone,ends);
          thread1253(tdone,ends);
          thread1254(tdone,ends);
          thread1255(tdone,ends);
          int biggest1256 = 0;
          if(ends[2]>=biggest1256){
            biggest1256=ends[2];
          }
          if(ends[3]>=biggest1256){
            biggest1256=ends[3];
          }
          if(ends[4]>=biggest1256){
            biggest1256=ends[4];
          }
          if(ends[5]>=biggest1256){
            biggest1256=ends[5];
          }
          if(ends[6]>=biggest1256){
            biggest1256=ends[6];
          }
          if(ends[7]>=biggest1256){
            biggest1256=ends[7];
          }
          if(ends[8]>=biggest1256){
            biggest1256=ends[8];
          }
          if(biggest1256 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread1257(tdone,ends);
          thread1258(tdone,ends);
          thread1259(tdone,ends);
          thread1260(tdone,ends);
          thread1261(tdone,ends);
          thread1262(tdone,ends);
          thread1263(tdone,ends);
          int biggest1264 = 0;
          if(ends[2]>=biggest1264){
            biggest1264=ends[2];
          }
          if(ends[3]>=biggest1264){
            biggest1264=ends[3];
          }
          if(ends[4]>=biggest1264){
            biggest1264=ends[4];
          }
          if(ends[5]>=biggest1264){
            biggest1264=ends[5];
          }
          if(ends[6]>=biggest1264){
            biggest1264=ends[6];
          }
          if(ends[7]>=biggest1264){
            biggest1264=ends[7];
          }
          if(ends[8]>=biggest1264){
            biggest1264=ends[8];
          }
          if(biggest1264 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest1264 == 0){
            S1247=0;
            active[1]=0;
            ends[1]=0;
            S1247=0;
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
          sendGripperDown.gethook();
          sendGripperTwist.gethook();
          sendGripperUntwist.gethook();
          sendGripCap.gethook();
          sendClamp.gethook();
          enable.gethook();
          bottleAtPos4.gethook();
          df = true;
        }
        runClockDomain();
      }
      sendGripperDown.setpreclear();
      sendGripperTwist.setpreclear();
      sendGripperUntwist.setpreclear();
      sendGripCap.setpreclear();
      sendClamp.setpreclear();
      enable.setpreclear();
      bottleAtPos4.setpreclear();
      gripperMaxLower.setpreclear();
      gripperMaxLift.setpreclear();
      capGripped.setpreclear();
      gripperInitPos.setpreclear();
      gripperFullTwist.setpreclear();
      bottleAtPos4E.setpreclear();
      bottleGoneE.setpreclear();
      gripperMaxLowerE.setpreclear();
      gripperMaxLiftE.setpreclear();
      gripperInitPosE.setpreclear();
      gripperFullTwistE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = sendGripperDown.getStatus() ? sendGripperDown.setprepresent() : sendGripperDown.setpreclear();
      sendGripperDown.setpreval(sendGripperDown.getValue());
      sendGripperDown.setClear();
      dummyint = sendGripperTwist.getStatus() ? sendGripperTwist.setprepresent() : sendGripperTwist.setpreclear();
      sendGripperTwist.setpreval(sendGripperTwist.getValue());
      sendGripperTwist.setClear();
      dummyint = sendGripperUntwist.getStatus() ? sendGripperUntwist.setprepresent() : sendGripperUntwist.setpreclear();
      sendGripperUntwist.setpreval(sendGripperUntwist.getValue());
      sendGripperUntwist.setClear();
      dummyint = sendGripCap.getStatus() ? sendGripCap.setprepresent() : sendGripCap.setpreclear();
      sendGripCap.setpreval(sendGripCap.getValue());
      sendGripCap.setClear();
      dummyint = sendClamp.getStatus() ? sendClamp.setprepresent() : sendClamp.setpreclear();
      sendClamp.setpreval(sendClamp.getValue());
      sendClamp.setClear();
      dummyint = enable.getStatus() ? enable.setprepresent() : enable.setpreclear();
      enable.setpreval(enable.getValue());
      enable.setClear();
      dummyint = bottleAtPos4.getStatus() ? bottleAtPos4.setprepresent() : bottleAtPos4.setpreclear();
      bottleAtPos4.setpreval(bottleAtPos4.getValue());
      bottleAtPos4.setClear();
      gripperMaxLower.sethook();
      gripperMaxLower.setClear();
      gripperMaxLift.sethook();
      gripperMaxLift.setClear();
      capGripped.sethook();
      capGripped.setClear();
      gripperInitPos.sethook();
      gripperInitPos.setClear();
      gripperFullTwist.sethook();
      gripperFullTwist.setClear();
      bottleAtPos4E.sethook();
      bottleAtPos4E.setClear();
      bottleGoneE.sethook();
      bottleGoneE.setClear();
      gripperMaxLowerE.sethook();
      gripperMaxLowerE.setClear();
      gripperMaxLiftE.sethook();
      gripperMaxLiftE.setClear();
      gripperInitPosE.sethook();
      gripperInitPosE.setClear();
      gripperFullTwistE.sethook();
      gripperFullTwistE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        sendGripperDown.gethook();
        sendGripperTwist.gethook();
        sendGripperUntwist.gethook();
        sendGripCap.gethook();
        sendClamp.gethook();
        enable.gethook();
        bottleAtPos4.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
