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
  public Signal batchDone = new Signal("batchDone", Signal.INPUT);
  public Signal orderReady = new Signal("orderReady", Signal.OUTPUT);
  public Signal batchDoneE = new Signal("batchDoneE", Signal.OUTPUT);
  public Signal completionTimeE = new Signal("completionTimeE", Signal.OUTPUT);
  private long tOrderReady_thread_1;//sysj\pos.sysj line: 19, column: 9
  private int t_thread_1;//sysj\pos.sysj line: 25, column: 9
  private int S5160 = 1;
  private int S5046 = 1;
  
  private int[] ends = new int[2];
  private int[] tdone = new int[2];
  
  public void runClockDomain(){
    for(int i=0;i<ends.length;i++){
      ends[i] = 0;
      tdone[i] = 0;
    }
    
    RUN: while(true){
      switch(S5160){
        case 0 : 
          S5160=0;
          break RUN;
        
        case 1 : 
          S5160=2;
          S5160=2;
          new Thread(new PosGUI()).start();//sysj\pos.sysj line: 14, column: 5
          S5046=0;
          active[1]=1;
          ends[1]=1;
          break RUN;
        
        case 2 : 
          switch(S5046){
            case 0 : 
              if(submit.getprestatus()){//sysj\pos.sysj line: 17, column: 16
                System.out.printf("[POS] order submitted%n");//sysj\pos.sysj line: 18, column: 9
                tOrderReady_thread_1 = System.currentTimeMillis();//sysj\pos.sysj line: 19, column: 9
                S5046=1;
                if(System.currentTimeMillis() - tOrderReady_thread_1 < 50){//sysj\pos.sysj line: 20, column: 16
                  orderReady.setPresent();//sysj\pos.sysj line: 21, column: 13
                  currsigs.addElement(orderReady);
                  active[1]=1;
                  ends[1]=1;
                  break RUN;
                }
                else {
                  ends[1]=2;
                  ;//sysj\pos.sysj line: 20, column: 9
                  S5046=2;
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
              if(System.currentTimeMillis() - tOrderReady_thread_1 < 50){//sysj\pos.sysj line: 20, column: 16
                orderReady.setPresent();//sysj\pos.sysj line: 21, column: 13
                currsigs.addElement(orderReady);
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
              else {
                ends[1]=2;
                ;//sysj\pos.sysj line: 20, column: 9
                S5046=2;
                active[1]=1;
                ends[1]=1;
                break RUN;
              }
            
            case 2 : 
              if(batchDone.getprestatus()){//sysj\pos.sysj line: 24, column: 16
                t_thread_1 = (Integer)(batchDone.getpreval() == null ? null : ((Integer)batchDone.getpreval()));//sysj\pos.sysj line: 25, column: 9
                completionTimeE.setPresent();//sysj\pos.sysj line: 26, column: 9
                currsigs.addElement(completionTimeE);
                completionTimeE.setValue(t_thread_1);//sysj\pos.sysj line: 26, column: 9
                batchDoneE.setPresent();//sysj\pos.sysj line: 27, column: 9
                currsigs.addElement(batchDoneE);
                System.out.printf("[POS] batch complete in %d ms%n", t_thread_1);//sysj\pos.sysj line: 28, column: 9
                S5046=0;
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
          batchDone.gethook();
          df = true;
        }
        runClockDomain();
      }
      submit.setpreclear();
      batchDone.setpreclear();
      orderReady.setpreclear();
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
      dummyint = batchDone.getStatus() ? batchDone.setprepresent() : batchDone.setpreclear();
      batchDone.setpreval(batchDone.getValue());
      batchDone.setClear();
      orderReady.sethook();
      orderReady.setClear();
      batchDoneE.sethook();
      batchDoneE.setClear();
      completionTimeE.sethook();
      completionTimeE.setClear();
      if(paused[1]!=0 || suspended[1]!=0 || active[1]!=1);
      else{
        submit.gethook();
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
