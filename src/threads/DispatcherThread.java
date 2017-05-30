package threads;

import request.Request;
import request.RequestQueue;
import server.InitialServerArray;
import server.ProcessServer;
import server.ServerPool;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Juilee on 5/28/2017.
 */
public class DispatcherThread implements Runnable {

    private RequestQueue requestQueue;
    private static ArrayList<ProcessServer> availableServers;
    private ServerPool serverPool;

    public DispatcherThread(RequestQueue requestQueue, ServerPool serverPool) {
        this.requestQueue = requestQueue;
        this.serverPool = serverPool;
        availableServers = new ArrayList<>();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            Queue<Request> rQueue = requestQueue.getRequestQueue();


            synchronized (rQueue) {
                while (rQueue.peek() != null) {
                    //long initialWaitingTime = System.currentTimeMillis() - rQueue.peek().getStartTime();
                    // if (initialWaitingTime <= rQueue.peek().getWaitingTime()) {

                    Request req = rQueue.poll();
                    Thread.sleep(1000);
                    for(ProcessServer server:availableServers)
                    {
                        if(!server.getWaitingQueue().isFull()) {
                            System.out.println("Server pool size" + serverPool.getServers().size());
                            server.getWaitingQueue().enqueue(req);
                            System.out.println("Peek Waiting queue:" + server.getWaitingQueue().peek());
                        }
                        else{
                            ProcessServer processServer = serverPool.getServers().get(0);
                            availableServers.add(processServer);
                            serverPool.getServers().remove(processServer);
                            server.getWaitingQueue().enqueue(req);
                            System.out.println("Inside else");
                        }
                    }
                    System.out.println("Server pool size:"+serverPool.getServers().size());


                    //  }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
