package timer;

public abstract class TimerThread extends Thread{
	private boolean loop;
	private boolean running;
	
	public TimerThread() {
		super();
		this.loop = false;
		running = true;
	}
	
	public TimerThread(boolean loop) {
		super();
		this.loop = loop;
		running = true;
	}
	
	public void end() {
		running = false;
	}
	
	public void run() {
		if(loop) {
			while(running) {
				comp();
			}
		}
		else if(!loop) {
			if(running) {
				comp();
			}
		}
	}

	
	public abstract void comp();
}
