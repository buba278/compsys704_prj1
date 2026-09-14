import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import digitaltwin.PlantTwin;//sysj\capperController.sysj line: 1, column: 1
import digitaltwin.TwinClient;//sysj\capperController.sysj line: 2, column: 1

public class CapperController extends ClockDomain{
  public CapperController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtPos4 = new Signal("bottleAtPos4", Signal.INPUT);
  public Signal gripperMaxLower = new Signal("gripperMaxLower", Signal.INPUT);
  public Signal gripperMaxLift = new Signal("gripperMaxLift", Signal.INPUT);
  public Signal capGripped = new Signal("capGripped", Signal.INPUT);
  public Signal gripperInitPos = new Signal("gripperInitPos", Signal.INPUT);
  public Signal gripperFullTwist = new Signal("gripperFullTwist", Signal.INPUT);
  public Signal sendGripperDown = new Signal("sendGripperDown", Signal.OUTPUT);
  public Signal sendGripperTwist = new Signal("sendGripperTwist", Signal.OUTPUT);
  public Signal sendGripperUntwist = new Signal("sendGripperUntwist", Signal.OUTPUT);
  public Signal sendGripCap = new Signal("sendGripCap", Signal.OUTPUT);
  public Signal sendClamp = new Signal("sendClamp", Signal.OUTPUT);
  private TwinClient twin_thread_1;//sysj\capperController.sysj line: 19, column: 2
  private String productId_thread_1;//sysj\capperController.sysj line: 28, column: 9
  private int S873 = 1;
  private int S3 = 1;
  private int S8 = 1;
  private int S16 = 1;
  private int S25 = 1;
  private int S18 = 1;
  private int S34 = 1;
  private int S27 = 1;
  private int S72 = 1;
  private int S80 = 1;
  
  private int[] ends = new int[8];
  private int[] tdone = new int[8];
  
  public void thread892(int [] tdone, int [] ends){
        switch(S80){
      case 0 : 
        active[7]=0;
        ends[7]=0;
        tdone[7]=1;
        break;
      
      case 1 : 
        sendGripperUntwist.setPresent();//sysj\capperController.sysj line: 73, column: 21
        currsigs.addElement(sendGripperUntwist);
        active[7]=1;
        ends[7]=1;
        tdone[7]=1;
        break;
      
    }
  }

  public void thread891(int [] tdone, int [] ends){
        switch(S72){
      case 0 : 
        active[6]=0;
        ends[6]=0;
        tdone[6]=1;
        break;
      
      case 1 : 
        sendClamp.setPresent();//sysj\capperController.sysj line: 66, column: 13
        currsigs.addElement(sendClamp);
        active[6]=1;
        ends[6]=1;
        tdone[6]=1;
        break;
      
    }
  }

  public void thread889(int [] tdone, int [] ends){
        S80=1;
    System.out.println("CapperController: Untwisting gripper to initial position...");//sysj\capperController.sysj line: 70, column: 17
    sendGripperUntwist.setPresent();//sysj\capperController.sysj line: 73, column: 21
    currsigs.addElement(sendGripperUntwist);
    active[7]=1;
    ends[7]=1;
    tdone[7]=1;
  }

  public void thread888(int [] tdone, int [] ends){
        S72=1;
    sendClamp.setPresent();//sysj\capperController.sysj line: 66, column: 13
    currsigs.addElement(sendClamp);
    active[6]=1;
    ends[6]=1;
    tdone[6]=1;
  }

  public void thread886(int [] tdone, int [] ends){
        switch(S34){
      case 0 : 
        active[5]=0;
        ends[5]=0;
        tdone[5]=1;
        break;
      
      case 1 : 
        switch(S27){
          case 0 : 
            if(capGripped.getprestatus()){//sysj\capperController.sysj line: 49, column: 23
              System.out.println("CapperController: Cap secured. Twisting gripper...");//sysj\capperController.sysj line: 50, column: 17
              S27=1;
              sendGripperTwist.setPresent();//sysj\capperController.sysj line: 57, column: 21
              currsigs.addElement(sendGripperTwist);
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
            sendGripperTwist.setPresent();//sysj\capperController.sysj line: 57, column: 21
            currsigs.addElement(sendGripperTwist);
            active[5]=1;
            ends[5]=1;
            tdone[5]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread885(int [] tdone, int [] ends){
        switch(S25){
      case 0 : 
        active[4]=0;
        ends[4]=0;
        tdone[4]=1;
        break;
      
      case 1 : 
        switch(S18){
          case 0 : 
            if(gripperMaxLower.getprestatus()){//sysj\capperController.sysj line: 43, column: 23
              System.out.println("CapperController: Gripper reached lowest position. Gripping cap...");//sysj\capperController.sysj line: 44, column: 17
              S18=1;
              sendGripCap.setPresent();//sysj\capperController.sysj line: 45, column: 17
              currsigs.addElement(sendGripCap);
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
            sendGripCap.setPresent();//sysj\capperController.sysj line: 45, column: 17
            currsigs.addElement(sendGripCap);
            active[4]=1;
            ends[4]=1;
            tdone[4]=1;
            break;
          
        }
        break;
      
    }
  }

  public void thread884(int [] tdone, int [] ends){
        switch(S16){
      case 0 : 
        active[3]=0;
        ends[3]=0;
        tdone[3]=1;
        break;
      
      case 1 : 
        sendGripperDown.setPresent();//sysj\capperController.sysj line: 39, column: 17
        currsigs.addElement(sendGripperDown);
        active[3]=1;
        ends[3]=1;
        tdone[3]=1;
        break;
      
    }
  }

  public void thread883(int [] tdone, int [] ends){
        switch(S8){
      case 0 : 
        active[2]=0;
        ends[2]=0;
        tdone[2]=1;
        break;
      
      case 1 : 
        sendClamp.setPresent();//sysj\capperController.sysj line: 34, column: 17
        currsigs.addElement(sendClamp);
        active[2]=1;
        ends[2]=1;
        tdone[2]=1;
        break;
      
    }
  }

  public void thread881(int [] tdone, int [] ends){
        S80=1;
    System.out.println("CapperController: Untwisting gripper to initial position...");//sysj\capperController.sysj line: 70, column: 17
    sendGripperUntwist.setPresent();//sysj\capperController.sysj line: 73, column: 21
    currsigs.addElement(sendGripperUntwist);
    active[7]=1;
    ends[7]=1;
    tdone[7]=1;
  }

  public void thread880(int [] tdone, int [] ends){
        S72=1;
    sendClamp.setPresent();//sysj\capperController.sysj line: 66, column: 13
    currsigs.addElement(sendClamp);
    active[6]=1;
    ends[6]=1;
    tdone[6]=1;
  }

  public void thread878(int [] tdone, int [] ends){
        S34=1;
    S27=0;
    active[5]=1;
    ends[5]=1;
    tdone[5]=1;
  }

  public void thread877(int [] tdone, int [] ends){
        S25=1;
    S18=0;
    active[4]=1;
    ends[4]=1;
    tdone[4]=1;
  }

  public void thread876(int [] tdone, int [] ends){
        S16=1;
    System.out.println("CapperController: Lowering gripper...");//sysj\capperController.sysj line: 38, column: 17
    sendGripperDown.setPresent();//sysj\capperController.sysj line: 39, column: 17
    currsigs.addElement(sendGripperDown);
    active[3]=1;
    ends[3]=1;
    tdone[3]=1;
  }

  public void thread875(int [] tdone, int [] ends){
        S8=1;
    sendClamp.setPresent();//sysj\capperController.sysj line: 34, column: 17
    currsigs.addElement(sendClamp);
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
      switch(S873){
        case 0 : 
          S873=0;
          break RUN;
        
        case 1 : 
          S873=2;
          S873=2;
          twin_thread_1 = new TwinClient("cap-screwer", "127.0.0.1", 9090);//sysj\capperController.sysj line: 19, column: 2
          System.out.println("CapperController: Initialized and running.");//sysj\capperController.sysj line: 21, column: 5
          twin_thread_1.update(PlantTwin.State.IDLE);//sysj\capperController.sysj line: 22, column: 5
          System.out.println("CapperController: Waiting for bottle at Position 4...");//sysj\capperController.sysj line: 26, column: 9
          S3=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S3){
            case 0 : 
              if(bottleAtPos4.getprestatus()){//sysj\capperController.sysj line: 27, column: 16
                productId_thread_1 = twin_thread_1.getProductIdAtPosition(4);//sysj\capperController.sysj line: 28, column: 9
                System.out.println("CapperController: Bottle detected. Starting capping sequence.");//sysj\capperController.sysj line: 29, column: 9
                twin_thread_1.update(PlantTwin.State.IN_PROGRESS);//sysj\capperController.sysj line: 30, column: 9
                S3=1;
                thread875(tdone,ends);
                thread876(tdone,ends);
                thread877(tdone,ends);
                thread878(tdone,ends);
                int biggest879 = 0;
                if(ends[2]>=biggest879){
                  biggest879=ends[2];
                }
                if(ends[3]>=biggest879){
                  biggest879=ends[3];
                }
                if(ends[4]>=biggest879){
                  biggest879=ends[4];
                }
                if(ends[5]>=biggest879){
                  biggest879=ends[5];
                }
                if(biggest879 == 1){
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
              }
              else {
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 1 : 
              if(gripperFullTwist.getprestatus()){//sysj\capperController.sysj line: 32, column: 15
                System.out.println("CapperController: Gripper full twist achieved or aborted. Releasing clamp & resetting.");//sysj\capperController.sysj line: 62, column: 9
                twin_thread_1.recordEvent("Cap screwed on", productId_thread_1);//sysj\capperController.sysj line: 63, column: 7
                S3=2;
                thread880(tdone,ends);
                thread881(tdone,ends);
                int biggest882 = 0;
                if(ends[6]>=biggest882){
                  biggest882=ends[6];
                }
                if(ends[7]>=biggest882){
                  biggest882=ends[7];
                }
                if(biggest882 == 1){
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
              }
              else {
                thread883(tdone,ends);
                thread884(tdone,ends);
                thread885(tdone,ends);
                thread886(tdone,ends);
                int biggest887 = 0;
                if(ends[2]>=biggest887){
                  biggest887=ends[2];
                }
                if(ends[3]>=biggest887){
                  biggest887=ends[3];
                }
                if(ends[4]>=biggest887){
                  biggest887=ends[4];
                }
                if(ends[5]>=biggest887){
                  biggest887=ends[5];
                }
                if(biggest887 == 1){
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                //FINXME code
                if(biggest887 == 0){
                  System.out.println("CapperController: Gripper full twist achieved or aborted. Releasing clamp & resetting.");//sysj\capperController.sysj line: 62, column: 9
                  twin_thread_1.recordEvent("Cap screwed on", productId_thread_1);//sysj\capperController.sysj line: 63, column: 7
                  S3=2;
                  thread888(tdone,ends);
                  thread889(tdone,ends);
                  int biggest890 = 0;
                  if(ends[6]>=biggest890){
                    biggest890=ends[6];
                  }
                  if(ends[7]>=biggest890){
                    biggest890=ends[7];
                  }
                  if(biggest890 == 1){
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                }
              }
            
            case 2 : 
              if(gripperInitPos.getprestatus()){//sysj\capperController.sysj line: 64, column: 15
                System.out.println("CapperController: Gripper returned to initial position. Cycle complete.");//sysj\capperController.sysj line: 78, column: 9
                twin_thread_1.update(PlantTwin.State.IDLE);//sysj\capperController.sysj line: 79, column: 9
                System.out.println("CapperController: Waiting for bottle at Position 4...");//sysj\capperController.sysj line: 26, column: 9
                S3=0;
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                thread891(tdone,ends);
                thread892(tdone,ends);
                int biggest893 = 0;
                if(ends[6]>=biggest893){
                  biggest893=ends[6];
                }
                if(ends[7]>=biggest893){
                  biggest893=ends[7];
                }
                if(biggest893 == 1){
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                //FINXME code
                if(biggest893 == 0){
                  System.out.println("CapperController: Gripper returned to initial position. Cycle complete.");//sysj\capperController.sysj line: 78, column: 9
                  twin_thread_1.update(PlantTwin.State.IDLE);//sysj\capperController.sysj line: 79, column: 9
                  System.out.println("CapperController: Waiting for bottle at Position 4...");//sysj\capperController.sysj line: 26, column: 9
                  S3=0;
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
              }
            
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
          bottleAtPos4.gethook();
          gripperMaxLower.gethook();
          gripperMaxLift.gethook();
          capGripped.gethook();
          gripperInitPos.gethook();
          gripperFullTwist.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtPos4.setpreclear();
      gripperMaxLower.setpreclear();
      gripperMaxLift.setpreclear();
      capGripped.setpreclear();
      gripperInitPos.setpreclear();
      gripperFullTwist.setpreclear();
      sendGripperDown.setpreclear();
      sendGripperTwist.setpreclear();
      sendGripperUntwist.setpreclear();
      sendGripCap.setpreclear();
      sendClamp.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtPos4.getStatus() ? bottleAtPos4.setprepresent() : bottleAtPos4.setpreclear();
      bottleAtPos4.setpreval(bottleAtPos4.getValue());
      bottleAtPos4.setClear();
      dummyint = gripperMaxLower.getStatus() ? gripperMaxLower.setprepresent() : gripperMaxLower.setpreclear();
      gripperMaxLower.setpreval(gripperMaxLower.getValue());
      gripperMaxLower.setClear();
      dummyint = gripperMaxLift.getStatus() ? gripperMaxLift.setprepresent() : gripperMaxLift.setpreclear();
      gripperMaxLift.setpreval(gripperMaxLift.getValue());
      gripperMaxLift.setClear();
      dummyint = capGripped.getStatus() ? capGripped.setprepresent() : capGripped.setpreclear();
      capGripped.setpreval(capGripped.getValue());
      capGripped.setClear();
      dummyint = gripperInitPos.getStatus() ? gripperInitPos.setprepresent() : gripperInitPos.setpreclear();
      gripperInitPos.setpreval(gripperInitPos.getValue());
      gripperInitPos.setClear();
      dummyint = gripperFullTwist.getStatus() ? gripperFullTwist.setprepresent() : gripperFullTwist.setpreclear();
      gripperFullTwist.setpreval(gripperFullTwist.getValue());
      gripperFullTwist.setClear();
      sendGripperDown.sethook();
      sendGripperDown.setClear();
      sendGripperTwist.sethook();
      sendGripperTwist.setClear();
      sendGripperUntwist.sethook();
      sendGripperUntwist.setClear();
      sendGripCap.sethook();
      sendGripCap.setClear();
      sendClamp.sethook();
      sendClamp.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtPos4.gethook();
        gripperMaxLower.gethook();
        gripperMaxLift.gethook();
        capGripped.gethook();
        gripperInitPos.gethook();
        gripperFullTwist.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
