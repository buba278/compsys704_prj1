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
  public Signal bottleAtPos4 = new Signal("bottleAtPos4", Signal.OUTPUT);
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
  private int S1261 = 1;
  private int S923 = 1;
  private int S903 = 1;
  private int S964 = 1;
  private int S928 = 1;
  private int S996 = 1;
  private int S966 = 1;
  private int S1037 = 1;
  private int S1001 = 1;
  private int S1045 = 1;
  private int S1039 = 1;
  private int S1053 = 1;
  private int S1061 = 1;
  private int S1069 = 1;
  private int S1077 = 1;
  
  private int[] ends = new int[11];
  private int[] tdone = new int[11];
  
  public void thread1281(int [] tdone, int [] ends){
        switch(S1077){
      case 0 : 
        active[10]=0;
        ends[10]=0;
        tdone[10]=1;
        break;
      
      case 1 : 
        if(gripperFullTwist.getprestatus()){//sysj\capperPlant.sysj line: 121, column: 24
          gripperFullTwistE.setPresent();//sysj\capperPlant.sysj line: 121, column: 42
          currsigs.addElement(gripperFullTwistE);
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

  public void thread1280(int [] tdone, int [] ends){
        switch(S1069){
      case 0 : 
        active[9]=0;
        ends[9]=0;
        tdone[9]=1;
        break;
      
      case 1 : 
        if(gripperInitPos.getprestatus()){//sysj\capperPlant.sysj line: 119, column: 24
          gripperInitPosE.setPresent();//sysj\capperPlant.sysj line: 119, column: 40
          currsigs.addElement(gripperInitPosE);
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

  public void thread1279(int [] tdone, int [] ends){
        switch(S1061){
      case 0 : 
        active[8]=0;
        ends[8]=0;
        tdone[8]=1;
        break;
      
      case 1 : 
        if(gripperMaxLift.getprestatus()){//sysj\capperPlant.sysj line: 117, column: 24
          gripperMaxLiftE.setPresent();//sysj\capperPlant.sysj line: 117, column: 40
          currsigs.addElement(gripperMaxLiftE);
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

  public void thread1278(int [] tdone, int [] ends){
        switch(S1053){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 115, column: 24
          gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 115, column: 41
          currsigs.addElement(gripperMaxLowerE);
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

  public void thread1277(int [] tdone, int [] ends){
        switch(S1045){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        switch(S1039){
          case 0 : 
            S1039=0;
            if(bottleAtPos4.getprestatus()){//sysj\capperPlant.sysj line: 106, column: 13
              bottleAtPos4E.setPresent();//sysj\capperPlant.sysj line: 107, column: 6
              currsigs.addElement(bottleAtPos4E);
              S1039=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              bottleGoneE.setPresent();//sysj\capperPlant.sysj line: 109, column: 6
              currsigs.addElement(bottleGoneE);
              S1039=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
          case 1 : 
            S1039=1;
            S1039=0;
            if(bottleAtPos4.getprestatus()){//sysj\capperPlant.sysj line: 106, column: 13
              bottleAtPos4E.setPresent();//sysj\capperPlant.sysj line: 107, column: 6
              currsigs.addElement(bottleAtPos4E);
              S1039=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            else {
              bottleGoneE.setPresent();//sysj\capperPlant.sysj line: 109, column: 6
              currsigs.addElement(bottleGoneE);
              S1039=1;
              active[6]=1;
              ends[6]=1;
              tdone[6]=1;
            }
            break;
          
        }
        break;
      
    }
  }

  public void thread1276(int [] tdone, int [] ends){
        switch(S1037){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S1001){
          case 0 : 
            if(sendGripperTwist.getprestatus()){//sysj\capperPlant.sysj line: 81, column: 19
              System.out.println("CapperPlant [Rotation]: Twisting cap closed...");//sysj\capperPlant.sysj line: 84, column: 13
              S1001=1;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 82, column: 17
              currsigs.addElement(gripperInitPos);
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 1 : 
            S1001=1;
            S1001=2;
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
          case 2 : 
            S1001=2;
            System.out.println("CapperPlant [Rotation]: Full twist achieved.");//sysj\capperPlant.sysj line: 88, column: 13
            S1001=3;
            gripperFullTwist.setPresent();//sysj\capperPlant.sysj line: 90, column: 17
            currsigs.addElement(gripperFullTwist);
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
          case 3 : 
            if(sendGripperUntwist.getprestatus()){//sysj\capperPlant.sysj line: 89, column: 19
              System.out.println("CapperPlant [Rotation]: Untwisting back to start position...");//sysj\capperPlant.sysj line: 92, column: 13
              S1001=4;
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            else {
              gripperFullTwist.setPresent();//sysj\capperPlant.sysj line: 90, column: 17
              currsigs.addElement(gripperFullTwist);
              active[5]=1;
              ends[5]=1;
              tdone[5]=1;
            }
            break;
          
          case 4 : 
            S1001=4;
            S1001=5;
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
          case 5 : 
            S1001=5;
            S1001=0;
            gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 82, column: 17
            currsigs.addElement(gripperInitPos);
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1275(int [] tdone, int [] ends){
        switch(S996){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S966){
          case 0 : 
            if(sendGripCap.getprestatus()){//sysj\capperPlant.sysj line: 67, column: 19
              System.out.println("CapperPlant [Gripper Jaw]: Closing jaws around cap...");//sysj\capperPlant.sysj line: 68, column: 13
              S966=1;
              capGripped.setPresent();//sysj\capperPlant.sysj line: 71, column: 17
              currsigs.addElement(capGripped);
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
            if(!sendGripCap.getprestatus()){//sysj\capperPlant.sysj line: 70, column: 19
              System.out.println("CapperPlant [Gripper Jaw]: Jaws opened, cap released.");//sysj\capperPlant.sysj line: 73, column: 13
              S966=2;
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            else {
              capGripped.setPresent();//sysj\capperPlant.sysj line: 71, column: 17
              currsigs.addElement(capGripped);
              active[4]=1;
              ends[4]=1;
              tdone[4]=1;
            }
            break;
          
          case 2 : 
            S966=2;
            S966=0;
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1274(int [] tdone, int [] ends){
        switch(S964){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        switch(S928){
          case 0 : 
            if(sendGripperDown.getprestatus()){//sysj\capperPlant.sysj line: 46, column: 19
              System.out.println("CapperPlant [Z-Axis]: Moving down...");//sysj\capperPlant.sysj line: 49, column: 13
              S928=1;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 47, column: 17
              currsigs.addElement(gripperMaxLift);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 1 : 
            S928=1;
            S928=2;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
          case 2 : 
            S928=2;
            System.out.println("CapperPlant [Z-Axis]: Reached bottom sensor.");//sysj\capperPlant.sysj line: 53, column: 13
            S928=3;
            gripperMaxLower.setPresent();//sysj\capperPlant.sysj line: 55, column: 17
            currsigs.addElement(gripperMaxLower);
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
          case 3 : 
            if(!sendGripperDown.getprestatus()){//sysj\capperPlant.sysj line: 54, column: 19
              System.out.println("CapperPlant [Z-Axis]: Moving back up...");//sysj\capperPlant.sysj line: 57, column: 13
              S928=4;
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            else {
              gripperMaxLower.setPresent();//sysj\capperPlant.sysj line: 55, column: 17
              currsigs.addElement(gripperMaxLower);
              active[3]=1;
              ends[3]=1;
              tdone[3]=1;
            }
            break;
          
          case 4 : 
            S928=4;
            S928=5;
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
          case 5 : 
            S928=5;
            S928=0;
            gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 47, column: 17
            currsigs.addElement(gripperMaxLift);
            active[3]=1;
            ends[3]=1;
            tdone[3]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1273(int [] tdone, int [] ends){
        switch(S923){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        switch(S903){
          case 0 : 
            if(sendClamp.getprestatus()){//sysj\capperPlant.sysj line: 33, column: 19
              System.out.println("CapperPlant [Turntable]: Clamp released. Indexing turntable...");//sysj\capperPlant.sysj line: 37, column: 13
              S903=1;
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            else {
              bottleAtPos4.setPresent();//sysj\capperPlant.sysj line: 34, column: 17
              currsigs.addElement(bottleAtPos4);
              active[2]=1;
              ends[2]=1;
              tdone[2]=1;
            }
            break;
          
          case 1 : 
            S903=1;
            System.out.println("CapperPlant [Turntable]: Delivering new bottle to Position 4...");//sysj\capperPlant.sysj line: 32, column: 13
            S903=0;
            bottleAtPos4.setPresent();//sysj\capperPlant.sysj line: 34, column: 17
            currsigs.addElement(bottleAtPos4);
            active[2]=1;
            ends[2]=1;
            tdone[2]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread1271(int [] tdone, int [] ends){
        S1077=1;
    if(gripperFullTwist.getprestatus()){//sysj\capperPlant.sysj line: 121, column: 24
      gripperFullTwistE.setPresent();//sysj\capperPlant.sysj line: 121, column: 42
      currsigs.addElement(gripperFullTwistE);
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

  public void thread1270(int [] tdone, int [] ends){
        S1069=1;
    if(gripperInitPos.getprestatus()){//sysj\capperPlant.sysj line: 119, column: 24
      gripperInitPosE.setPresent();//sysj\capperPlant.sysj line: 119, column: 40
      currsigs.addElement(gripperInitPosE);
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

  public void thread1269(int [] tdone, int [] ends){
        S1061=1;
    if(gripperMaxLift.getprestatus()){//sysj\capperPlant.sysj line: 117, column: 24
      gripperMaxLiftE.setPresent();//sysj\capperPlant.sysj line: 117, column: 40
      currsigs.addElement(gripperMaxLiftE);
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

  public void thread1268(int [] tdone, int [] ends){
        S1053=1;
    if(gripperMaxLower.getprestatus()){//sysj\capperPlant.sysj line: 115, column: 24
      gripperMaxLowerE.setPresent();//sysj\capperPlant.sysj line: 115, column: 41
      currsigs.addElement(gripperMaxLowerE);
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

  public void thread1267(int [] tdone, int [] ends){
        S1045=1;
    S1039=0;
    if(bottleAtPos4.getprestatus()){//sysj\capperPlant.sysj line: 106, column: 13
      bottleAtPos4E.setPresent();//sysj\capperPlant.sysj line: 107, column: 6
      currsigs.addElement(bottleAtPos4E);
      S1039=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
    else {
      bottleGoneE.setPresent();//sysj\capperPlant.sysj line: 109, column: 6
      currsigs.addElement(bottleGoneE);
      S1039=1;
      active[6]=1;
      ends[6]=1;
      tdone[6]=1;
    }
  }

  public void thread1266(int [] tdone, int [] ends){
        S1037=1;
    S1001=0;
    gripperInitPos.setPresent();//sysj\capperPlant.sysj line: 82, column: 17
    currsigs.addElement(gripperInitPos);
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread1265(int [] tdone, int [] ends){
        S996=1;
    S966=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread1264(int [] tdone, int [] ends){
        S964=1;
    S928=0;
    gripperMaxLift.setPresent();//sysj\capperPlant.sysj line: 47, column: 17
    currsigs.addElement(gripperMaxLift);
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread1263(int [] tdone, int [] ends){
        S923=1;
    System.out.println("CapperPlant [Turntable]: Delivering new bottle to Position 4...");//sysj\capperPlant.sysj line: 32, column: 13
    S903=0;
    bottleAtPos4.setPresent();//sysj\capperPlant.sysj line: 34, column: 17
    currsigs.addElement(bottleAtPos4);
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
      switch(S1261){
        case 0 : 
          S1261=0;
          break RUN;
        
        case 1 : 
          S1261=2;
          S1261=2;
          new Thread(new CapperGUI()).start();//sysj\capperPlant.sysj line: 26, column: 2
          System.out.println("CapperPlant: Subsystem initialized.");//sysj\capperPlant.sysj line: 27, column: 5
          thread1263(tdone,ends);
          thread1264(tdone,ends);
          thread1265(tdone,ends);
          thread1266(tdone,ends);
          thread1267(tdone,ends);
          thread1268(tdone,ends);
          thread1269(tdone,ends);
          thread1270(tdone,ends);
          thread1271(tdone,ends);
          int biggest1272 = 0;
          if(ends[2]>=biggest1272){
            biggest1272=ends[2];
          }
          if(ends[3]>=biggest1272){
            biggest1272=ends[3];
          }
          if(ends[4]>=biggest1272){
            biggest1272=ends[4];
          }
          if(ends[5]>=biggest1272){
            biggest1272=ends[5];
          }
          if(ends[6]>=biggest1272){
            biggest1272=ends[6];
          }
          if(ends[7]>=biggest1272){
            biggest1272=ends[7];
          }
          if(ends[8]>=biggest1272){
            biggest1272=ends[8];
          }
          if(ends[9]>=biggest1272){
            biggest1272=ends[9];
          }
          if(ends[10]>=biggest1272){
            biggest1272=ends[10];
          }
          if(biggest1272 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
        
        case 2 : 
          thread1273(tdone,ends);
          thread1274(tdone,ends);
          thread1275(tdone,ends);
          thread1276(tdone,ends);
          thread1277(tdone,ends);
          thread1278(tdone,ends);
          thread1279(tdone,ends);
          thread1280(tdone,ends);
          thread1281(tdone,ends);
          int biggest1282 = 0;
          if(ends[2]>=biggest1282){
            biggest1282=ends[2];
          }
          if(ends[3]>=biggest1282){
            biggest1282=ends[3];
          }
          if(ends[4]>=biggest1282){
            biggest1282=ends[4];
          }
          if(ends[5]>=biggest1282){
            biggest1282=ends[5];
          }
          if(ends[6]>=biggest1282){
            biggest1282=ends[6];
          }
          if(ends[7]>=biggest1282){
            biggest1282=ends[7];
          }
          if(ends[8]>=biggest1282){
            biggest1282=ends[8];
          }
          if(ends[9]>=biggest1282){
            biggest1282=ends[9];
          }
          if(ends[10]>=biggest1282){
            biggest1282=ends[10];
          }
          if(biggest1282 == 1){
            active[1]=1;
            ends[1]=1;
            break RUN;
          }
          //FINXME code
          if(biggest1282 == 0){
            S1261=0;
            active[1]=0;
            ends[1]=0;
            S1261=0;
            break RUN;
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    char [] paused1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    char [] suspended1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
      bottleAtPos4.sethook();
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
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
