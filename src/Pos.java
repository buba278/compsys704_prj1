import java.util.*;
import com.systemj.ClockDomain;
import com.systemj.Signal;
import com.systemj.input_Channel;
import com.systemj.output_Channel;
import run.PosGUI;//sysj\pos.sysj line: 1, column: 1

public class Pos extends ClockDomain{
  public Pos(String name){super(name);}
  Vector currsigs = new Vector();
  private boolean df = false;
  private char [] active;
  private char [] paused;
  private char [] suspended;
  public Signal submit = new Signal("submit", Signal.INPUT);
  public Signal formLiquidARatio = new Signal("formLiquidARatio", Signal.INPUT);
  public Signal formTargetVolume = new Signal("formTargetVolume", Signal.INPUT);
  public Signal formQuantity = new Signal("formQuantity", Signal.INPUT);
  public Signal batchDone = new Signal("batchDone", Signal.INPUT);
  public Signal orderReady = new Signal("orderReady", Signal.OUTPUT);
  public Signal orderLiquidARatio = new Signal("orderLiquidARatio", Signal.OUTPUT);
  public Signal orderTargetVolume = new Signal("orderTargetVolume", Signal.OUTPUT);
  public Signal orderQuantity = new Signal("orderQuantity", Signal.OUTPUT);
  public Signal batchDoneE = new Signal("batchDoneE", Signal.OUTPUT);
  public Signal completionTimeE = new Signal("completionTimeE", Signal.OUTPUT);
  private int ratioA_thread_1;//sysj\pos.sysj line: 23, column: 9
  private int volume_thread_1;//sysj\pos.sysj line: 24, column: 9
  private int quantity_thread_1;//sysj\pos.sysj line: 25, column: 9
  private int t_thread_1;//sysj\pos.sysj line: 35, column: 9
  private int S3174 = 1;
  private int S3150 = 1;
  
  private int[] ends = new int[2];
  private int[] tdone = new int[2];
  
  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S3174){
        case 0 : 
          S3174=0;
          break RUN;
        
        case 1 : 
          S3174=2;
          S3174=2;
          new Thread(new PosGUI()).start();//sysj\pos.sysj line: 18, column: 5
          S3150=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S3150){
            case 0 : 
              if(submit.getprestatus()){//sysj\pos.sysj line: 21, column: 16
                ratioA_thread_1 = (Integer)(formLiquidARatio.getpreval() == null ? null : ((Integer)formLiquidARatio.getpreval()));//sysj\pos.sysj line: 23, column: 9
                volume_thread_1 = (Integer)(formTargetVolume.getpreval() == null ? null : ((Integer)formTargetVolume.getpreval()));//sysj\pos.sysj line: 24, column: 9
                quantity_thread_1 = (Integer)(formQuantity.getpreval() == null ? null : ((Integer)formQuantity.getpreval()));//sysj\pos.sysj line: 25, column: 9
                orderLiquidARatio.setPresent();//sysj\pos.sysj line: 27, column: 9
                currsigs.addElement(orderLiquidARatio);
                orderLiquidARatio.setValue(ratioA_thread_1);//sysj\pos.sysj line: 27, column: 9
                orderTargetVolume.setPresent();//sysj\pos.sysj line: 28, column: 9
                currsigs.addElement(orderTargetVolume);
                orderTargetVolume.setValue(volume_thread_1);//sysj\pos.sysj line: 28, column: 9
                orderQuantity.setPresent();//sysj\pos.sysj line: 29, column: 9
                currsigs.addElement(orderQuantity);
                orderQuantity.setValue(quantity_thread_1);//sysj\pos.sysj line: 29, column: 9
                S3150=1;
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
              S3150=1;
              orderReady.setPresent();//sysj\pos.sysj line: 31, column: 9
              currsigs.addElement(orderReady);
              System.out.printf("[POS] submitted ratioA=%d vol=%d qty=%d%n", ratioA_thread_1, volume_thread_1, quantity_thread_1);//sysj\pos.sysj line: 32, column: 9
              S3150=2;
              active[1]=1;
              ends[1]=1;
              break RUN;
            
            case 2 : 
              if(batchDone.getprestatus()){//sysj\pos.sysj line: 34, column: 16
                t_thread_1 = (Integer)(batchDone.getpreval() == null ? null : ((Integer)batchDone.getpreval()));//sysj\pos.sysj line: 35, column: 9
                completionTimeE.setPresent();//sysj\pos.sysj line: 36, column: 9
                currsigs.addElement(completionTimeE);
                completionTimeE.setValue(t_thread_1);//sysj\pos.sysj line: 36, column: 9
                batchDoneE.setPresent();//sysj\pos.sysj line: 37, column: 9
                currsigs.addElement(batchDoneE);
                System.out.printf("[POS] batch complete in %d ms%n", t_thread_1);//sysj\pos.sysj line: 38, column: 9
                S3150=0;
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
          submit.gethook();
          formLiquidARatio.gethook();
          formTargetVolume.gethook();
          formQuantity.gethook();
          batchDone.gethook();
          df = true;
        }
        runClockDomain();
      }
      submit.setpreclear();
      formLiquidARatio.setpreclear();
      formTargetVolume.setpreclear();
      formQuantity.setpreclear();
      batchDone.setpreclear();
      orderReady.setpreclear();
      orderLiquidARatio.setpreclear();
      orderTargetVolume.setpreclear();
      orderQuantity.setpreclear();
      batchDoneE.setpreclear();
      completionTimeE.setpreclear();
      int dummyint = 0;
      for(int qw=0;qw<currsigs.size();++qw){
        dummyint = ((Signal)currsigs.elementAt(qw)).getStatus() ? ((Signal)currsigs.elementAt(qw)).setprepresent() : ((Signal)currsigs.elementAt(qw)).setpreclear();
        ((Signal)currsigs.elementAt(qw)).setpreval(((Signal)currsigs.elementAt(qw)).getValue());
      }
      currsigs.removeAllElements();
      dummyint = submit.getStatus() ? submit.setprepresent() : submit.setpreclear();
      submit.setpreval(submit.getValue());
      submit.setClear();
      dummyint = formLiquidARatio.getStatus() ? formLiquidARatio.setprepresent() : formLiquidARatio.setpreclear();
      formLiquidARatio.setpreval(formLiquidARatio.getValue());
      formLiquidARatio.setClear();
      dummyint = formTargetVolume.getStatus() ? formTargetVolume.setprepresent() : formTargetVolume.setpreclear();
      formTargetVolume.setpreval(formTargetVolume.getValue());
      formTargetVolume.setClear();
      dummyint = formQuantity.getStatus() ? formQuantity.setprepresent() : formQuantity.setpreclear();
      formQuantity.setpreval(formQuantity.getValue());
      formQuantity.setClear();
      dummyint = batchDone.getStatus() ? batchDone.setprepresent() : batchDone.setpreclear();
      batchDone.setpreval(batchDone.getValue());
      batchDone.setClear();
      orderReady.sethook();
      orderReady.setClear();
      orderLiquidARatio.sethook();
      orderLiquidARatio.setClear();
      orderTargetVolume.sethook();
      orderTargetVolume.setClear();
      orderQuantity.sethook();
      orderQuantity.setClear();
      batchDoneE.sethook();
      batchDoneE.setClear();
      completionTimeE.sethook();
      completionTimeE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        submit.gethook();
        formLiquidARatio.gethook();
        formTargetVolume.gethook();
        formQuantity.gethook();
        batchDone.gethook();
      }
      runFinisher();
      if(active[1] == 0){
      	this.terminated = true;
      }
      if(!threaded) break;
    }
  }
}
