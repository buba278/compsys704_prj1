import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import digitaltwin.PlantTwin;//sysj\sorterController.sysj line: 1, column: 1
import digitaltwin.TwinClient;//sysj\sorterController.sysj line: 2, column: 1

public class SorterController extends ClockDomain{
  public SorterController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtSorter = new Signal("bottleAtSorter", Signal.INPUT);
  public Signal bottleDefective = new Signal("bottleDefective", Signal.INPUT);
  public Signal pusherExtended = new Signal("pusherExtended", Signal.INPUT);
  public Signal pusherRetracted = new Signal("pusherRetracted", Signal.INPUT);
  public Signal extendPusher = new Signal("extendPusher", Signal.OUTPUT);
  public Signal sortedE = new Signal("sortedE", Signal.OUTPUT);
  public Signal rejectedE = new Signal("rejectedE", Signal.OUTPUT);
  public Signal sortComplete = new Signal("sortComplete", Signal.OUTPUT);
  private TwinClient twin_thread_1;//sysj\sorterController.sysj line: 17, column: 5
  private long t0_thread_1;//sysj\sorterController.sysj line: 63, column: 13
  private int S11897 = 1;
  private int S11123 = 1;
  private int S11170 = 1;
  private int S11126 = 1;
  private int S11152 = 1;
  
  private int[] ends = new int[2];
  private int[] tdone = new int[2];
  
  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S11897){
        case 0 : 
          S11897=0;
          break RUN;
        
        case 1 : 
          S11897=2;
          S11897=2;
          twin_thread_1 = new TwinClient("sorter", "127.0.0.1", 9090);//sysj\sorterController.sysj line: 17, column: 5
          System.out.println("SorterController: Initialized and running.");//sysj\sorterController.sysj line: 19, column: 5
          twin_thread_1.update(PlantTwin.State.IDLE);//sysj\sorterController.sysj line: 20, column: 5
          System.out.println("SorterController: Waiting for bottle at sorter...");//sysj\sorterController.sysj line: 23, column: 9
          S11123=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S11123){
            case 0 : 
              if(bottleAtSorter.getprestatus()){//sysj\sorterController.sysj line: 24, column: 16
                System.out.println("SorterController: Bottle detected. Inspecting...");//sysj\sorterController.sysj line: 25, column: 9
                twin_thread_1.update(PlantTwin.State.IN_PROGRESS);//sysj\sorterController.sysj line: 26, column: 9
                S11123=1;
                if(bottleDefective.getprestatus()){//sysj\sorterController.sysj line: 28, column: 18
                  S11170=0;
                  System.out.println("SorterController: Defect detected - diverting bottle.");//sysj\sorterController.sysj line: 29, column: 13
                  S11126=0;
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                else {
                  S11170=1;
                  System.out.println("SorterController: Bottle OK - passing through.");//sysj\sorterController.sysj line: 61, column: 13
                  t0_thread_1 = System.currentTimeMillis();//sysj\sorterController.sysj line: 63, column: 13
                  S11152=0;
                  if(System.currentTimeMillis() - t0_thread_1 < 300){//sysj\sorterController.sysj line: 64, column: 20
                    sortedE.setPresent();//sysj\sorterController.sysj line: 65, column: 17
                    currsigs.addElement(sortedE);
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                  else {
                    ends[1]=2;
                    ;//sysj\sorterController.sysj line: 64, column: 13
                    twin_thread_1.recordEvent("Sorted OK", null);//sysj\sorterController.sysj line: 70, column: 13
                    S11152=1;
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                }
              }
              else {
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 1 : 
              switch(S11170){
                case 0 : 
                  switch(S11126){
                    case 0 : 
                      if(!pusherExtended.getprestatus()){//sysj\sorterController.sysj line: 34, column: 20
                        S11126=1;
                        extendPusher.setPresent();//sysj\sorterController.sysj line: 36, column: 17
                        currsigs.addElement(extendPusher);
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                      else {
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                    
                    case 1 : 
                      if(pusherExtended.getprestatus()){//sysj\sorterController.sysj line: 35, column: 20
                        System.out.println("SorterController: Pusher confirmed extended. Holding to divert...");//sysj\sorterController.sysj line: 38, column: 13
                        t0_thread_1 = System.currentTimeMillis();//sysj\sorterController.sysj line: 43, column: 13
                        S11126=2;
                        if(System.currentTimeMillis() - t0_thread_1 < 300){//sysj\sorterController.sysj line: 44, column: 20
                          extendPusher.setPresent();//sysj\sorterController.sysj line: 45, column: 17
                          currsigs.addElement(extendPusher);
                          rejectedE.setPresent();//sysj\sorterController.sysj line: 46, column: 17
                          currsigs.addElement(rejectedE);
                          active[1]=1;
                          ends[1]=1;
                          break RUN;
                        }
                        else {
                          ends[1]=2;
                          ;//sysj\sorterController.sysj line: 44, column: 13
                          twin_thread_1.recordEvent("Rejected", null);//sysj\sorterController.sysj line: 56, column: 13
                          System.out.println("SorterController: Releasing pusher.");//sysj\sorterController.sysj line: 58, column: 13
                          S11126=3;
                          active[1]=1;
                          ends[1]=1;
                          break RUN;
                        }
                      }
                      else {
                        extendPusher.setPresent();//sysj\sorterController.sysj line: 36, column: 17
                        currsigs.addElement(extendPusher);
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                    
                    case 2 : 
                      if(System.currentTimeMillis() - t0_thread_1 < 300){//sysj\sorterController.sysj line: 44, column: 20
                        extendPusher.setPresent();//sysj\sorterController.sysj line: 45, column: 17
                        currsigs.addElement(extendPusher);
                        rejectedE.setPresent();//sysj\sorterController.sysj line: 46, column: 17
                        currsigs.addElement(rejectedE);
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                      else {
                        ends[1]=2;
                        ;//sysj\sorterController.sysj line: 44, column: 13
                        twin_thread_1.recordEvent("Rejected", null);//sysj\sorterController.sysj line: 56, column: 13
                        System.out.println("SorterController: Releasing pusher.");//sysj\sorterController.sysj line: 58, column: 13
                        S11126=3;
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                    
                    case 3 : 
                      if(!bottleAtSorter.getprestatus()){//sysj\sorterController.sysj line: 59, column: 20
                        System.out.println("SorterController: Cycle complete. Returning to idle.");//sysj\sorterController.sysj line: 76, column: 9
                        twin_thread_1.update(PlantTwin.State.IDLE);//sysj\sorterController.sysj line: 77, column: 9
                        System.out.println("SorterController: Waiting for bottle at sorter...");//sysj\sorterController.sysj line: 23, column: 9
                        S11123=0;
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                      else {
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                    
                  }
                  break;
                
                case 1 : 
                  switch(S11152){
                    case 0 : 
                      if(System.currentTimeMillis() - t0_thread_1 < 300){//sysj\sorterController.sysj line: 64, column: 20
                        sortedE.setPresent();//sysj\sorterController.sysj line: 65, column: 17
                        currsigs.addElement(sortedE);
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                      else {
                        ends[1]=2;
                        ;//sysj\sorterController.sysj line: 64, column: 13
                        twin_thread_1.recordEvent("Sorted OK", null);//sysj\sorterController.sysj line: 70, column: 13
                        S11152=1;
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                    
                    case 1 : 
                      if(!bottleAtSorter.getprestatus()){//sysj\sorterController.sysj line: 72, column: 20
                        System.out.println("SorterController: Cycle complete. Returning to idle.");//sysj\sorterController.sysj line: 76, column: 9
                        twin_thread_1.update(PlantTwin.State.IDLE);//sysj\sorterController.sysj line: 77, column: 9
                        System.out.println("SorterController: Waiting for bottle at sorter...");//sysj\sorterController.sysj line: 23, column: 9
                        S11123=0;
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                      else {
                        active[1]=1;
                        ends[1]=1;
                        break RUN;
                      }
                    
                  }
                  break;
                
              }
              break;
            
          }
        
      }
    }
  }

  public void init(){
    char [] active1 = {1, 1};
    char [] paused1 = {0, 0};
    char [] suspended1 = {0, 0};
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
          bottleAtSorter.gethook();
          bottleDefective.gethook();
          pusherExtended.gethook();
          pusherRetracted.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtSorter.setpreclear();
      bottleDefective.setpreclear();
      pusherExtended.setpreclear();
      pusherRetracted.setpreclear();
      extendPusher.setpreclear();
      sortedE.setpreclear();
      rejectedE.setpreclear();
      sortComplete.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtSorter.getStatus() ? bottleAtSorter.setprepresent() : bottleAtSorter.setpreclear();
      bottleAtSorter.setpreval(bottleAtSorter.getValue());
      bottleAtSorter.setClear();
      dummyint = bottleDefective.getStatus() ? bottleDefective.setprepresent() : bottleDefective.setpreclear();
      bottleDefective.setpreval(bottleDefective.getValue());
      bottleDefective.setClear();
      dummyint = pusherExtended.getStatus() ? pusherExtended.setprepresent() : pusherExtended.setpreclear();
      pusherExtended.setpreval(pusherExtended.getValue());
      pusherExtended.setClear();
      dummyint = pusherRetracted.getStatus() ? pusherRetracted.setprepresent() : pusherRetracted.setpreclear();
      pusherRetracted.setpreval(pusherRetracted.getValue());
      pusherRetracted.setClear();
      extendPusher.sethook();
      extendPusher.setClear();
      sortedE.sethook();
      sortedE.setClear();
      rejectedE.sethook();
      rejectedE.setClear();
      sortComplete.sethook();
      sortComplete.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtSorter.gethook();
        bottleDefective.gethook();
        pusherExtended.gethook();
        pusherRetracted.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
