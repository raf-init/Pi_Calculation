import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
	    long stepsNum = 0L; //number of steps
	    int procNum = Runtime.getRuntime().availableProcessors(); //number of available processors of JVM
	    CalcThread[] threadsArray = new CalcThread[procNum]; //array of threads

	    if (args.length != 1) {
	    	System.out.println("arguments:  number_of_steps");
	    	System.exit(1);
	    }
	    try {
	    	stepsNum = Long.parseLong(args[0]);
	    } catch (NumberFormatException localNumberFormatException) {
	    	System.out.println("argument " + args[0] + " must be long int");
	    	System.exit(1);
	    }

	    /*
	     * set start and end of each thread
	     */
	    long foo = (stepsNum / procNum);
	    long start = 0L;
	    long stop = foo;

	    long startTime = System.currentTimeMillis();

	    for (int i = 0; i < procNum; i++) {
	    	threadsArray[i] = new CalcThread(stepsNum, start, stop);
	    	threadsArray[i].start();
	    	start = stop;
	    	stop += foo;
	    }

	    for (int j = 0; j < procNum; j++) {
	    	try {
	    		threadsArray[j].join();
	    	}catch (InterruptedException localInterruptedException) {}
	    }
	    double result = CalcThread.getPi();

	    long stopTime = System.currentTimeMillis();

	    System.out.printf("sequential program results with %d steps\n", new Object[] { Long.valueOf(stepsNum) });
	    System.out.printf("computed pi = %22.20f\n", new Object[] { Double.valueOf(result) });
	    System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", new Object[] { Double.valueOf(Math.abs(result - 3.141592653589793D)) });
	    System.out.printf("time to compute = %f seconds\n", new Object[] { Double.valueOf((stopTime - startTime) / 1000.0D) });
  }
}
