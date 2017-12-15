package timer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
	long millisecondTime;
	boolean running;
	Timer timer;
	ArrayList<TimerThread> threadList;
	
	public GameTimer(){
		millisecondTime = 0;
		threadList = new ArrayList<>();
	}
	
	public void startTimer() {
		timer = new Timer("game timer",false);
		running = true;
		timer.schedule(new TimerTask() {
			public void run() {
				if(running) {
					millisecondTime+=1;
				}
				else {
					timer.cancel();
				}
			}
		},0, 1);

	}
	
	public void stopTimer() {
		timer.cancel();
		for(TimerThread t: threadList) {
			t.end();
		}
	}
	
	public int getTime() {
		return (int) (millisecondTime/1000);
	}
	
	public long milliTime() {
		return millisecondTime;
	}
	
	public void gameResume() {
		this.running = true;
		stopTimer();
		startTimer();
	}
	
	public void gamePause() {
		this.running = false;
	}
	
	public void reset() {
		millisecondTime = 0;
		this.running = true;
	}
	
	public void delay(long delay) {
		long runTime = millisecondTime + delay;
		Thread thread = new Thread() {
			public void run() {
				gamePause();
				while(millisecondTime < runTime) {
					try {
						sleep(500);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				gameResume();
			}
		};
		thread.start();
	}
	
	public void runDelay(TimerThread t,long delay) {
		long runTime = millisecondTime + delay;
		threadList.add(t);
		
		Thread thread = new Thread() {
			public void run() {
				while(millisecondTime <= runTime) {
					try {
						this.sleep(500);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				t.start();
			}
		};
		
		thread.start();
	}
	
	public void runInTimeRange(TimerThread t,long start,long stop) {
	    long runTime = millisecondTime + start;
	    long endTime = millisecondTime + stop;
		threadList.add(t);
	    
	    Thread thread = new Thread() {
	    	public void run() {
	    		while(millisecondTime < runTime) {
	    			try {
	    				this.sleep(500);
	    			}
	    			catch(InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    		t.start();
	    		while(t.isAlive()) {
	    			if(!running) {
	    				try {
		    				t.sleep(500);
		    			}
		    			catch(InterruptedException e) {
		    				e.printStackTrace();
		    			}
	    			}
	    			if(millisecondTime > endTime) {
	    				t.end();
	    				threadList.remove(t);
	    				break;
	    			}
	    		}
	    	}
	    };
	    
	    thread.start();
	}
	
}
