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
  private int S1486 = 1;
  private int S1218 = 1;
  private int S1182 = 1;
  private int S1250 = 1;
  private int S1220 = 1;
  private int S1291 = 1;
  private int S1255 = 1;
  private int S1299 = 1;
  private int S1293 = 1;
  private int S1307 = 1;
  private int S1315 = 1;
  private int S1323 = 1;
  private int S1331 = 1;
  
  private int[] ends = new int[10];
  private int[] tdone = new int[10];
  
  public void thread1504(int [] tdone, int [] ends){
        switch(S1331){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(gripperFullTwist.getprestatus()){//sysj\capperPlant.sysj line: 104, column: 24
          gripperFullTwistE.setPresent();//sysj\capperPlant.sysj line: 104, column: 42
          currsigs.addElement(gripperFullTwistE);
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

  public void thread1503(int [] tdone, int [] ends){
        switch(S1323){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(gripperInitPos.getprestatus()){//sysj\capperPlant.sysj line: 102, column: 24
          gripperInitPosE.setPresent();//sysj\capperPlant.sysj line: 102, column: 40
          currsigs.addElement(gripperInitPosE);
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

  public void thread1502(int [] tdone, int [] ends){
        switch(S1315){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(gripperMaxLift.getprestatus()){//sysj\capperPlant.sysj line: 100, column: 24
          gripperMaxLiftE.setPresent();//sysj\capperPlant.sysj line: 100, column: 40
          currsigs.addElement(gripperMaxLiftE);
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

  public void thread1501(int [] tdone, int [] ends){
        switch(S1307){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 98, column: 24
          gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 98, column: 41
          currsigs.addElement(gripperMaxLowerE);
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

  public void thread1500(int [] tdone, int [] ends){
        switch(S1299){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S1293){
          case 0 : 
            S1293=0;
            if(bottleAtPos4.getprestatus()){//sysj\capperPlant.sysj line: 89, column: 13
              bottleAtPos4E.setPresent();//sysj\capperPlant.sysj line: 90, column: 6
              currsigs.addElement(bottleAtPos4E);
              S1293=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              bottleGoneE.setPresent();//sysj\capperPlant.sysj line: 92, column: 6
              currsigs.addElement(bottleGoneE);
              S1293=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 1 : 
            S1293=1;
            S1293=0;
            if(bottleAtPos4.getprestatus()){//sysj\capperPlant.sysj line: 89, column: 13
              bottleAtPos4E.setPresent();//sysj\capperPlant.sysj line: 90, column: 6
              currsigs.addElement(bottleAtPos4E);
              S1293=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              bottleGoneE.setPresent();//sysj\capperPlant.sysj line: 92, column: 6
              currsigs.addElement(bottleGoneE);
              S1293=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread1499(int [] tdone, int [] ends){
        switch(S1291){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S1255){
          case 0 : 
            if(sendGripperTwist.getprestatus()){//sysj\capperPlant.sysj line: 68, column: 19
              System.out.println("CapperPlant [Rotation]: Twisting cap closed...");//sysj\capperPlant.sysj line: 71, column: 13
              S1255=1;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 69, column: 17
              currsigs.addElement(gripperInitPos);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 1 : 
            S1255=1;
            S1255=2;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 2 : 
            S1255=2;
            System.out.println("CapperPlant [Rotation]: Full twist achieved.");//sysj\capperPlant.sysj line: 75, column: 13
            S1255=3;
            gripperFullTwist.setPresent();//sysj\capperPlant.sysj line: 77, column: 17
            currsigs.addElement(gripperFullTwist);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 3 : 
            if(sendGripperUntwist.getprestatus()){//sysj\capperPlant.sysj line: 76, column: 19
              System.out.println("CapperPlant [Rotation]: Untwisting back to start position...");//sysj\capperPlant.sysj line: 79, column: 13
              S1255=4;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              gripperFullTwist.setPresent();//sysj\capperPlant.sysj line: 77, column: 17
              currsigs.addElement(gripperFullTwist);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 4 : 
            S1255=4;
            S1255=5;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
          case 5 : 
            S1255=5;
            S1255=0;
            gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 69, column: 17
            currsigs.addElement(gripperInitPos);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1498(int [] tdone, int [] ends){
        switch(S1250){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S1220){
          case 0 : 
            if(sendGripCap.getprestatus()){//sysj\capperPlant.sysj line: 54, column: 19
              System.out.println("CapperPlant [Gripper Jaw]: Closing jaws around cap...");//sysj\capperPlant.sysj line: 55, column: 13
              S1220=1;
              capGripped.setPresent();//sysj\capperPlant.sysj line: 58, column: 17
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
            if(!sendGripCap.getprestatus()){//sysj\capperPlant.sysj line: 57, column: 19
              System.out.println("CapperPlant [Gripper Jaw]: Jaws opened, cap released.");//sysj\capperPlant.sysj line: 60, column: 13
              S1220=2;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              capGripped.setPresent();//sysj\capperPlant.sysj line: 58, column: 17
              currsigs.addElement(capGripped);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 2 : 
            S1220=2;
            S1220=0;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1497(int [] tdone, int [] ends){
        switch(S1218){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S1182){
          case 0 : 
            if(sendGripperDown.getprestatus()){//sysj\capperPlant.sysj line: 33, column: 19
              System.out.println("CapperPlant [Z-Axis]: Moving down...");//sysj\capperPlant.sysj line: 36, column: 13
              S1182=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 34, column: 17
              currsigs.addElement(gripperMaxLift);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            S1182=1;
            S1182=2;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 2 : 
            S1182=2;
            System.out.println("CapperPlant [Z-Axis]: Reached bottom sensor.");//sysj\capperPlant.sysj line: 40, column: 13
            S1182=3;
            gripperMaxLower.setPresent();//sysj\capperPlant.sysj line: 42, column: 17
            currsigs.addElement(gripperMaxLower);
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 3 : 
            if(!sendGripperDown.getprestatus()){//sysj\capperPlant.sysj line: 41, column: 19
              System.out.println("CapperPlant [Z-Axis]: Moving back up...");//sysj\capperPlant.sysj line: 44, column: 13
              S1182=4;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              gripperMaxLower.setPresent();//sysj\capperPlant.sysj line: 42, column: 17
              currsigs.addElement(gripperMaxLower);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 4 : 
            S1182=4;
            S1182=5;
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
          case 5 : 
            S1182=5;
            S1182=0;
            gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 34, column: 17
            currsigs.addElement(gripperMaxLift);
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1495(int [] tdone, int [] ends){
        S1331=1;
    if(gripperFullTwist.getprestatus()){//sysj\capperPlant.sysj line: 104, column: 24
      gripperFullTwistE.setPresent();//sysj\capperPlant.sysj line: 104, column: 42
      currsigs.addElement(gripperFullTwistE);
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

  public void thread1494(int [] tdone, int [] ends){
        S1323=1;
    if(gripperInitPos.getprestatus()){//sysj\capperPlant.sysj line: 102, column: 24
      gripperInitPosE.setPresent();//sysj\capperPlant.sysj line: 102, column: 40
      currsigs.addElement(gripperInitPosE);
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

  public void thread1493(int [] tdone, int [] ends){
        S1315=1;
    if(gripperMaxLift.getprestatus()){//sysj\capperPlant.sysj line: 100, column: 24
      gripperMaxLiftE.setPresent();//sysj\capperPlant.sysj line: 100, column: 40
      currsigs.addElement(gripperMaxLiftE);
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

  public void thread1492(int [] tdone, int [] ends){
        S1307=1;
    if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 98, column: 24
      gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 98, column: 41
      currsigs.addElement(gripperMaxLowerE);
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

  public void thread1491(int [] tdone, int [] ends){
        S1299=1;
    S1293=0;
    if(bottleAtPos4.getprestatus()){//sysj\capperPlant.sysj line: 89, column: 13
      bottleAtPos4E.setPresent();//sysj\capperPlant.sysj line: 90, column: 6
      currsigs.addElement(bottleAtPos4E);
      S1293=1;
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
    else {
      bottleGoneE.setPresent();//sysj\capperPlant.sysj line: 92, column: 6
      currsigs.addElement(bottleGoneE);
      S1293=1;
      active[5]=1;
      ends[5]=1;
      tdone[5]=1;
    }
  }

  public void thread1490(int [] tdone, int [] ends){
        S1291=1;
    S1255=0;
    gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 69, column: 17
    currsigs.addElement(gripperInitPos);
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread1489(int [] tdone, int [] ends){
        S1250=1;
    S1220=0;
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread1488(int [] tdone, int [] ends){
        S1218=1;
    S1182=0;
    gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 34, column: 17
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
      switch(S1486){
        case 0 : 
          S1486=0;
          break RUN;
        
        case 1 : 
          S1486=2;
          S1486=2;
          new Thread(new CapperGUI()).start();//sysj\capperPlant.sysj line: 26, column: 2
          System.out.println("CapperPlant: Subsystem initialized.");//sysj\capperPlant.sysj line: 27, column: 5
          thread1488(tdone,ends);
          thread1489(tdone,ends);
          thread1490(tdone,ends);
          thread1491(tdone,ends);
          thread1492(tdone,ends);
          thread1493(tdone,ends);
          thread1494(tdone,ends);
          thread1495(tdone,ends);
          int biggest1496 = 0;
          if(ends[2]>=biggest1496){
            biggest1496=ends[2];
          }
          if(ends[3]>=biggest1496){
            biggest1496=ends[3];
          }
          if(ends[4]>=biggest1496){
            biggest1496=ends[4];
          }
          if(ends[5]>=biggest1496){
            biggest1496=ends[5];
          }
          if(ends[6]>=biggest1496){
            biggest1496=ends[6];
          }
          if(ends[7]>=biggest1496){
            biggest1496=ends[7];
          }
          if(ends[8]>=biggest1496){
            biggest1496=ends[8];
          }
          if(ends[9]>=biggest1496){
            biggest1496=ends[9];
          }
          if(biggest1496 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread1497(tdone,ends);
          thread1498(tdone,ends);
          thread1499(tdone,ends);
          thread1500(tdone,ends);
          thread1501(tdone,ends);
          thread1502(tdone,ends);
          thread1503(tdone,ends);
          thread1504(tdone,ends);
          int biggest1505 = 0;
          if(ends[2]>=biggest1505){
            biggest1505=ends[2];
          }
          if(ends[3]>=biggest1505){
            biggest1505=ends[3];
          }
          if(ends[4]>=biggest1505){
            biggest1505=ends[4];
          }
          if(ends[5]>=biggest1505){
            biggest1505=ends[5];
          }
          if(ends[6]>=biggest1505){
            biggest1505=ends[6];
          }
          if(ends[7]>=biggest1505){
            biggest1505=ends[7];
          }
          if(ends[8]>=biggest1505){
            biggest1505=ends[8];
          }
          if(ends[9]>=biggest1505){
            biggest1505=ends[9];
          }
          if(biggest1505 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest1505 == 0){
            S1486=0;
            active[1]=0;
            ends[1]=0;
            S1486=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
