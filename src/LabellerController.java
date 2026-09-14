import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import digitaltwin.PlantTwin;//sysj\labellerController.sysj line: 1, column: 1
import digitaltwin.TwinClient;//sysj\labellerController.sysj line: 2, column: 1

public class LabellerController extends ClockDomain{
  public LabellerController(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal bottleAtLabeller = new Signal("bottleAtLabeller", Signal.INPUT);
  public Signal labelPrinted = new Signal("labelPrinted", Signal.INPUT);
  public Signal bottleClamped = new Signal("bottleClamped", Signal.INPUT);
  public Signal labelApplied = new Signal("labelApplied", Signal.INPUT);
  public Signal printLabel = new Signal("printLabel", Signal.OUTPUT);
  public Signal clampBottle = new Signal("clampBottle", Signal.OUTPUT);
  public Signal applyLabel = new Signal("applyLabel", Signal.OUTPUT);
  private TwinClient twin_thread_1;//sysj\labellerController.sysj line: 16, column: 2
  private String productId_thread_1;//sysj\labellerController.sysj line: 24, column: 3
  private long tPrint_thread_1;//sysj\labellerController.sysj line: 33, column: 3
  private long tClamp_thread_1;//sysj\labellerController.sysj line: 42, column: 3
  private long tApply_thread_1;//sysj\labellerController.sysj line: 51, column: 3
  private int S9755 = 1;
  private int S8963 = 1;
  
  private int[] ends = new int[2];
  private int[] tdone = new int[2];
  
  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S9755){
        case 0 : 
          S9755=0;
          break RUN;
        
        case 1 : 
          S9755=2;
          S9755=2;
          twin_thread_1 = new TwinClient("labeller", "127.0.0.1", 9090);//sysj\labellerController.sysj line: 16, column: 2
          System.out.println("LabelController: Initialized and running.");//sysj\labellerController.sysj line: 17, column: 2
          twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 20, column: 3
          System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 21, column: 3
          S8963=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S8963){
            case 0 : 
              if(bottleAtLabeller.getprestatus()){//sysj\labellerController.sysj line: 22, column: 10
                productId_thread_1 = twin_thread_1.getProductIdAtPosition(5);//sysj\labellerController.sysj line: 24, column: 3
                if(productId_thread_1 == null || productId_thread_1.isEmpty()) {//sysj\labellerController.sysj line: 25, column: 49
                  productId_thread_1 = "UNKNOWN";//sysj\labellerController.sysj line: 26, column: 4
                }
                System.out.println("LabelController: Bottle detected. Starting sequence...");//sysj\labellerController.sysj line: 29, column: 3
                twin_thread_1.update(PlantTwin.State.IN_PROGRESS);//sysj\labellerController.sysj line: 30, column: 3
                tPrint_thread_1 = System.currentTimeMillis();//sysj\labellerController.sysj line: 33, column: 3
                S8963=1;
                if(System.currentTimeMillis() - tPrint_thread_1 < 1000){//sysj\labellerController.sysj line: 34, column: 10
                  printLabel.setPresent();//sysj\labellerController.sysj line: 35, column: 4
                  currsigs.addElement(printLabel);
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                else {
                  ends[1]=2;
                  ;//sysj\labellerController.sysj line: 34, column: 3
                  System.out.println("LabelController: Label printed. Clamping bottle...");//sysj\labellerController.sysj line: 39, column: 3
                  tClamp_thread_1 = System.currentTimeMillis();//sysj\labellerController.sysj line: 42, column: 3
                  S8963=2;
                  if(System.currentTimeMillis() - tClamp_thread_1 < 1000){//sysj\labellerController.sysj line: 43, column: 10
                    clampBottle.setPresent();//sysj\labellerController.sysj line: 44, column: 4
                    currsigs.addElement(clampBottle);
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                  else {
                    ends[1]=2;
                    ;//sysj\labellerController.sysj line: 43, column: 3
                    System.out.println("LabelController: Bottle clamped. Applying label...");//sysj\labellerController.sysj line: 48, column: 3
                    tApply_thread_1 = System.currentTimeMillis();//sysj\labellerController.sysj line: 51, column: 3
                    S8963=3;
                    if(System.currentTimeMillis() - tApply_thread_1 < 1500){//sysj\labellerController.sysj line: 52, column: 10
                      clampBottle.setPresent();//sysj\labellerController.sysj line: 53, column: 4
                      currsigs.addElement(clampBottle);
                      applyLabel.setPresent();//sysj\labellerController.sysj line: 54, column: 4
                      currsigs.addElement(applyLabel);
                      active[1]=1;
                      ends[1]=1;
                      break RUN;
                    }
                    else {
                      ends[1]=2;
                      ;//sysj\labellerController.sysj line: 52, column: 3
                      twin_thread_1.recordEvent("Label applied", productId_thread_1);//sysj\labellerController.sysj line: 59, column: 3
                      System.out.println("LabelController: Label applied, Release complete.");//sysj\labellerController.sysj line: 60, column: 3
                      twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 62, column: 3
                      twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 20, column: 3
                      System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 21, column: 3
                      S8963=0;
                      active[1]=1;
                      ends[1]=1;
                      break RUN;
                    }
                  }
                }
              }
              else {
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 1 : 
              if(System.currentTimeMillis() - tPrint_thread_1 < 1000){//sysj\labellerController.sysj line: 34, column: 10
                printLabel.setPresent();//sysj\labellerController.sysj line: 35, column: 4
                currsigs.addElement(printLabel);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                ends[1]=2;
                ;//sysj\labellerController.sysj line: 34, column: 3
                System.out.println("LabelController: Label printed. Clamping bottle...");//sysj\labellerController.sysj line: 39, column: 3
                tClamp_thread_1 = System.currentTimeMillis();//sysj\labellerController.sysj line: 42, column: 3
                S8963=2;
                if(System.currentTimeMillis() - tClamp_thread_1 < 1000){//sysj\labellerController.sysj line: 43, column: 10
                  clampBottle.setPresent();//sysj\labellerController.sysj line: 44, column: 4
                  currsigs.addElement(clampBottle);
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                else {
                  ends[1]=2;
                  ;//sysj\labellerController.sysj line: 43, column: 3
                  System.out.println("LabelController: Bottle clamped. Applying label...");//sysj\labellerController.sysj line: 48, column: 3
                  tApply_thread_1 = System.currentTimeMillis();//sysj\labellerController.sysj line: 51, column: 3
                  S8963=3;
                  if(System.currentTimeMillis() - tApply_thread_1 < 1500){//sysj\labellerController.sysj line: 52, column: 10
                    clampBottle.setPresent();//sysj\labellerController.sysj line: 53, column: 4
                    currsigs.addElement(clampBottle);
                    applyLabel.setPresent();//sysj\labellerController.sysj line: 54, column: 4
                    currsigs.addElement(applyLabel);
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                  else {
                    ends[1]=2;
                    ;//sysj\labellerController.sysj line: 52, column: 3
                    twin_thread_1.recordEvent("Label applied", productId_thread_1);//sysj\labellerController.sysj line: 59, column: 3
                    System.out.println("LabelController: Label applied, Release complete.");//sysj\labellerController.sysj line: 60, column: 3
                    twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 62, column: 3
                    twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 20, column: 3
                    System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 21, column: 3
                    S8963=0;
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                }
              }
            
            case 2 : 
              if(System.currentTimeMillis() - tClamp_thread_1 < 1000){//sysj\labellerController.sysj line: 43, column: 10
                clampBottle.setPresent();//sysj\labellerController.sysj line: 44, column: 4
                currsigs.addElement(clampBottle);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                ends[1]=2;
                ;//sysj\labellerController.sysj line: 43, column: 3
                System.out.println("LabelController: Bottle clamped. Applying label...");//sysj\labellerController.sysj line: 48, column: 3
                tApply_thread_1 = System.currentTimeMillis();//sysj\labellerController.sysj line: 51, column: 3
                S8963=3;
                if(System.currentTimeMillis() - tApply_thread_1 < 1500){//sysj\labellerController.sysj line: 52, column: 10
                  clampBottle.setPresent();//sysj\labellerController.sysj line: 53, column: 4
                  currsigs.addElement(clampBottle);
                  applyLabel.setPresent();//sysj\labellerController.sysj line: 54, column: 4
                  currsigs.addElement(applyLabel);
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                else {
                  ends[1]=2;
                  ;//sysj\labellerController.sysj line: 52, column: 3
                  twin_thread_1.recordEvent("Label applied", productId_thread_1);//sysj\labellerController.sysj line: 59, column: 3
                  System.out.println("LabelController: Label applied, Release complete.");//sysj\labellerController.sysj line: 60, column: 3
                  twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 62, column: 3
                  twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 20, column: 3
                  System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 21, column: 3
                  S8963=0;
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
              }
            
            case 3 : 
              if(System.currentTimeMillis() - tApply_thread_1 < 1500){//sysj\labellerController.sysj line: 52, column: 10
                clampBottle.setPresent();//sysj\labellerController.sysj line: 53, column: 4
                currsigs.addElement(clampBottle);
                applyLabel.setPresent();//sysj\labellerController.sysj line: 54, column: 4
                currsigs.addElement(applyLabel);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                ends[1]=2;
                ;//sysj\labellerController.sysj line: 52, column: 3
                twin_thread_1.recordEvent("Label applied", productId_thread_1);//sysj\labellerController.sysj line: 59, column: 3
                System.out.println("LabelController: Label applied, Release complete.");//sysj\labellerController.sysj line: 60, column: 3
                twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 62, column: 3
                twin_thread_1.update(PlantTwin.State.IDLE);//sysj\labellerController.sysj line: 20, column: 3
                System.out.println("LabelController: Waiting for bottle at labeller...");//sysj\labellerController.sysj line: 21, column: 3
                S8963=0;
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
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
          bottleAtLabeller.gethook();
          labelPrinted.gethook();
          bottleClamped.gethook();
          labelApplied.gethook();
          df = true;
        }
        runClockDomain();
      }
      bottleAtLabeller.setpreclear();
      labelPrinted.setpreclear();
      bottleClamped.setpreclear();
      labelApplied.setpreclear();
      printLabel.setpreclear();
      clampBottle.setpreclear();
      applyLabel.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = bottleAtLabeller.getStatus() ? bottleAtLabeller.setprepresent() : bottleAtLabeller.setpreclear();
      bottleAtLabeller.setpreval(bottleAtLabeller.getValue());
      bottleAtLabeller.setClear();
      dummyint = labelPrinted.getStatus() ? labelPrinted.setprepresent() : labelPrinted.setpreclear();
      labelPrinted.setpreval(labelPrinted.getValue());
      labelPrinted.setClear();
      dummyint = bottleClamped.getStatus() ? bottleClamped.setprepresent() : bottleClamped.setpreclear();
      bottleClamped.setpreval(bottleClamped.getValue());
      bottleClamped.setClear();
      dummyint = labelApplied.getStatus() ? labelApplied.setprepresent() : labelApplied.setpreclear();
      labelApplied.setpreval(labelApplied.getValue());
      labelApplied.setClear();
      printLabel.sethook();
      printLabel.setClear();
      clampBottle.sethook();
      clampBottle.setClear();
      applyLabel.sethook();
      applyLabel.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        bottleAtLabeller.gethook();
        labelPrinted.gethook();
        bottleClamped.gethook();
        labelApplied.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
