/*
 * Threads ipologismou tou pi
 */
public class CalcThread extends Thread { 
	private static volatile double pi = 0.0D;
	private double stepsNum;
	private long start;
	private long stop;
	private double sum;
  
	public CalcThread(long stepsNum, long start, long stop) {
		this.stepsNum = (1.0D / stepsNum);
		this.start = start;
		this.stop = stop;
	}
  
	public void run() {
		for (long l = start; l < stop; ++l) {
			double d = ((double)l + 0.5D) * stepsNum;
			sum += 4.0D / (1.0D + d * d);
		} 
		updatePi();
	}
  
	private synchronized void updatePi() {
		pi += stepsNum * sum;
	}
  
	static double getPi() {
		return pi;
	}
}
