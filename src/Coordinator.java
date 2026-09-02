import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;

public class Coordinator extends ClockDomain{
  public Coordinator(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal orderReady = new Signal("orderReady", Signal.INPUT);
  public Signal orderLiquidARatio = new Signal("orderLiquidARatio", Signal.INPUT);
  public Signal orderTargetVolume = new Signal("orderTargetVolume", Signal.INPUT);
  public Signal orderQuantity = new Signal("orderQuantity", Signal.INPUT);
  public Signal fillDoneE = new Signal("fillDoneE", Signal.INPUT);
  public Signal fillReady = new Signal("fillReady", Signal.INPUT);
  public Signal liquidARatio = new Signal("liquidARatio", Signal.OUTPUT);
  public Signal targetVolumeMl = new Signal("targetVolumeMl", Signal.OUTPUT);
  public Signal bottleAtPos2 = new Signal("bottleAtPos2", Signal.OUTPUT);
  public Signal bottleAtPos2P = new Signal("bottleAtPos2P", Signal.OUTPUT);
  public Signal batchDone = new Signal("batchDone", Signal.OUTPUT);
  private int ratioA_thread_1;//sysj\coordinator.sysj line: 18, column: 9
  private int volume_thread_1;//sysj\coordinator.sysj line: 19, column: 9
  private int quantity_thread_1;//sysj\coordinator.sysj line: 20, column: 9
  private long start_thread_1;//sysj\coordinator.sysj line: 27, column: 9
  private int filled_thread_1;//sysj\coordinator.sysj line: 28, column: 9
  private int elapsed_thread_1;//sysj\coordinator.sysj line: 43, column: 9
  private int S1370 = 1;
  private int S1162 = 1;
  private int S1169 = 1;
  
  private int[] ends = new int[2];
  private int[] tdone = new int[2];
  
  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S1370){
        case 0 : 
          S1370=0;
          break RUN;
        
        case 1 : 
          S1370=2;
          S1370=2;
          S1162=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S1162){
            case 0 : 
              if(orderReady.getprestatus()){//sysj\coordinator.sysj line: 16, column: 16
                ratioA_thread_1 = (Integer)(orderLiquidARatio.getpreval() == null ? null : ((Integer)orderLiquidARatio.getpreval()));//sysj\coordinator.sysj line: 18, column: 9
                volume_thread_1 = (Integer)(orderTargetVolume.getpreval() == null ? null : ((Integer)orderTargetVolume.getpreval()));//sysj\coordinator.sysj line: 19, column: 9
                quantity_thread_1 = (Integer)(orderQuantity.getpreval() == null ? null : ((Integer)orderQuantity.getpreval()));//sysj\coordinator.sysj line: 20, column: 9
                System.out.printf("[Coordinator] order ratioA=%d vol=%d qty=%d%n", ratioA_thread_1, volume_thread_1, quantity_thread_1);//sysj\coordinator.sysj line: 21, column: 9
                liquidARatio.setPresent();//sysj\coordinator.sysj line: 23, column: 9
                currsigs.addElement(liquidARatio);
                liquidARatio.setValue(ratioA_thread_1);//sysj\coordinator.sysj line: 23, column: 9
                targetVolumeMl.setPresent();//sysj\coordinator.sysj line: 24, column: 9
                currsigs.addElement(targetVolumeMl);
                targetVolumeMl.setValue(volume_thread_1);//sysj\coordinator.sysj line: 24, column: 9
                S1162=1;
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
              S1162=1;
              start_thread_1 = System.currentTimeMillis();//sysj\coordinator.sysj line: 27, column: 9
              filled_thread_1 = 0;//sysj\coordinator.sysj line: 28, column: 9
              S1162=2;
              if(filled_thread_1 < quantity_thread_1){//sysj\coordinator.sysj line: 29, column: 16
                S1169=0;
                bottleAtPos2.setPresent();//sysj\coordinator.sysj line: 33, column: 21
                currsigs.addElement(bottleAtPos2);
                bottleAtPos2P.setPresent();//sysj\coordinator.sysj line: 34, column: 21
                currsigs.addElement(bottleAtPos2P);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                ends[1]=2;
                ;//sysj\coordinator.sysj line: 29, column: 9
                elapsed_thread_1 = (int)(System.currentTimeMillis() - start_thread_1);//sysj\coordinator.sysj line: 43, column: 9
                batchDone.setPresent();//sysj\coordinator.sysj line: 44, column: 9
                currsigs.addElement(batchDone);
                batchDone.setValue(elapsed_thread_1);//sysj\coordinator.sysj line: 44, column: 9
                System.out.printf("[Coordinator] batch complete in %d ms%n", elapsed_thread_1);//sysj\coordinator.sysj line: 45, column: 9
                S1162=0;
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 2 : 
              switch(S1169){
                case 0 : 
                  if(fillDoneE.getprestatus()){//sysj\coordinator.sysj line: 30, column: 20
                    S1169=1;
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                  else {
                    bottleAtPos2.setPresent();//sysj\coordinator.sysj line: 33, column: 21
                    currsigs.addElement(bottleAtPos2);
                    bottleAtPos2P.setPresent();//sysj\coordinator.sysj line: 34, column: 21
                    currsigs.addElement(bottleAtPos2P);
                    active[1]=1;
                    ends[1]=1;
                    break RUN;
                  }
                
                case 1 : 
                  if(fillReady.getprestatus()){//sysj\coordinator.sysj line: 38, column: 20
                    filled_thread_1 = filled_thread_1 + 1;//sysj\coordinator.sysj line: 39, column: 13
                    System.out.printf("[Coordinator] bottle %d/%d done%n", filled_thread_1, quantity_thread_1);//sysj\coordinator.sysj line: 40, column: 13
                    if(filled_thread_1 < quantity_thread_1){//sysj\coordinator.sysj line: 29, column: 16
                      S1169=0;
                      bottleAtPos2.setPresent();//sysj\coordinator.sysj line: 33, column: 21
                      currsigs.addElement(bottleAtPos2);
                      bottleAtPos2P.setPresent();//sysj\coordinator.sysj line: 34, column: 21
                      currsigs.addElement(bottleAtPos2P);
                      active[1]=1;
                      ends[1]=1;
                      break RUN;
                    }
                    else {
                      ends[1]=2;
                      ;//sysj\coordinator.sysj line: 29, column: 9
                      elapsed_thread_1 = (int)(System.currentTimeMillis() - start_thread_1);//sysj\coordinator.sysj line: 43, column: 9
                      batchDone.setPresent();//sysj\coordinator.sysj line: 44, column: 9
                      currsigs.addElement(batchDone);
                      batchDone.setValue(elapsed_thread_1);//sysj\coordinator.sysj line: 44, column: 9
                      System.out.printf("[Coordinator] batch complete in %d ms%n", elapsed_thread_1);//sysj\coordinator.sysj line: 45, column: 9
                      S1162=0;
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
          orderReady.gethook();
          orderLiquidARatio.gethook();
          orderTargetVolume.gethook();
          orderQuantity.gethook();
          fillDoneE.gethook();
          fillReady.gethook();
          df = true;
        }
        runClockDomain();
      }
      orderReady.setpreclear();
      orderLiquidARatio.setpreclear();
      orderTargetVolume.setpreclear();
      orderQuantity.setpreclear();
      fillDoneE.setpreclear();
      fillReady.setpreclear();
      liquidARatio.setpreclear();
      targetVolumeMl.setpreclear();
      bottleAtPos2.setpreclear();
      bottleAtPos2P.setpreclear();
      batchDone.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = orderReady.getStatus() ? orderReady.setprepresent() : orderReady.setpreclear();
      orderReady.setpreval(orderReady.getValue());
      orderReady.setClear();
      dummyint = orderLiquidARatio.getStatus() ? orderLiquidARatio.setprepresent() : orderLiquidARatio.setpreclear();
      orderLiquidARatio.setpreval(orderLiquidARatio.getValue());
      orderLiquidARatio.setClear();
      dummyint = orderTargetVolume.getStatus() ? orderTargetVolume.setprepresent() : orderTargetVolume.setpreclear();
      orderTargetVolume.setpreval(orderTargetVolume.getValue());
      orderTargetVolume.setClear();
      dummyint = orderQuantity.getStatus() ? orderQuantity.setprepresent() : orderQuantity.setpreclear();
      orderQuantity.setpreval(orderQuantity.getValue());
      orderQuantity.setClear();
      dummyint = fillDoneE.getStatus() ? fillDoneE.setprepresent() : fillDoneE.setpreclear();
      fillDoneE.setpreval(fillDoneE.getValue());
      fillDoneE.setClear();
      dummyint = fillReady.getStatus() ? fillReady.setprepresent() : fillReady.setpreclear();
      fillReady.setpreval(fillReady.getValue());
      fillReady.setClear();
      liquidARatio.sethook();
      liquidARatio.setClear();
      targetVolumeMl.sethook();
      targetVolumeMl.setClear();
      bottleAtPos2.sethook();
      bottleAtPos2.setClear();
      bottleAtPos2P.sethook();
      bottleAtPos2P.setClear();
      batchDone.sethook();
      batchDone.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        orderReady.gethook();
        orderLiquidARatio.gethook();
        orderTargetVolume.gethook();
        orderQuantity.gethook();
        fillDoneE.gethook();
        fillReady.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
