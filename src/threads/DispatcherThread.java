package threads;

import request.Request;
import request.RequestQueue;
import server.ProcessServer;
import server.ServerPool;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Juilee on 5/28/2017.
 */
public class DispatcherThread implements Runnable {

    private RequestQueue requestQueue;
    private static CopyOnWriteArrayList<ProcessServer> availableServers;
    private ServerPool serverPool;

    public DispatcherThread(RequestQueue requestQueue, ServerPool serverPool,CopyOnWriteArrayList<ProcessServer> availableServers) {
        this.requestQueue = requestQueue;
        this.serverPool = serverPool;
        this.availableServers = availableServers;
        ProcessServer processServer = serverPool.getServers().get(0);
        availableServers.add(processServer);
        serverPool.getServers().remove(processServer);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            Queue<Request> rQueue = requestQueue.getRequestQueue();


            synchronized (rQueue) {

                while (rQueue.peek() != null) {

                    Request req = rQueue.poll();
                    Thread.sleep(1000);

                    for (ProcessServer pServer : availableServers) {
                        if (!pServer.getWaitingQueue().isFull()) {
                            req.setStatus("WAITING");
                            pServer.getWaitingQueue().enqueue(req);
                            System.out.println(req.getRequestName() + " " + req.getStatus());
                        }
                    }
                    if (!req.getStatus().equals("WAITING")) {
                        ProcessServer server = serverPool.getServers().get(0);
                        availableServers.add(server);
                        System.out.println(server.getName());
                        serverPool.getServers().remove(server);
                        req.setStatus("WAITING");
                        server.getWaitingQueue().enqueue(req);
                        System.out.println(req.getRequestName() + " " + req.getStatus());
                    }


                    System.out.println("Server pool size:" + serverPool.getServers().size());


                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startProcessing() throws InterruptedException {

        Thread requestProcessThread = new Thread(new RequestProcessThread(availableServers));
        requestProcessThread.start();
        System.out.println("Process started");
        requestProcessThread.join();

    }
}
