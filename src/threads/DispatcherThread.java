package threads;

import request.Request;
import request.RequestQueue;
import server.ProcessServer;

import java.util.Queue;
/**
 * Created by Juilee on 5/28/2017.
 */
public class DispatcherThread implements Runnable {

    private RequestQueue requestQueue;
    public DispatcherThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        try
        {
            Thread.sleep(2000);
            Queue<Request> rQueue = requestQueue.getRequestQueue();
            ProcessServer processServer = new ProcessServer();
            synchronized (rQueue) {
                while(rQueue.peek()!=null) {
                    long initialWaitingTime = System.currentTimeMillis() - rQueue.peek().getStartTime();
                    if (initialWaitingTime > rQueue.peek().getWaitingTime()) {
                        System.out.println("initialWaitingTime"+initialWaitingTime);
                        Request req = rQueue.poll();
                        processServer.getWaitingQueue().insert(req);
                        System.out.println(processServer.getWaitingQueue().peek());
                    }
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
