package threads;

import request.Request;
import server.ProcessServer;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Juilee on 5/30/2017.
 */
public class RequestProcessThread implements Runnable {

    private static CopyOnWriteArrayList<ProcessServer> availableServers;
    private ArrayList<Request> processedRequests;

    public RequestProcessThread(CopyOnWriteArrayList<ProcessServer> availableServers) {
        this.availableServers = availableServers;
        processedRequests = new ArrayList<>();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            while (availableServers != null) {

                for (ProcessServer server : availableServers) {
                    if (!server.getWaitingQueue().isEmpty()) {
                        Request request = server.getWaitingQueue().dequeue();
                        try {
                            Thread.sleep((long) request.getProcessingTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        request.setStatus("PROCESSED");
                        processedRequests.add(request);
                        System.out.println(request.getRequestName() + " " + request.getStatus());
                    }
                }
            }
    }
}
