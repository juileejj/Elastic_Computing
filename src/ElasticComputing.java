import jdk.nashorn.internal.objects.annotations.Property;
import request.RequestQueue;
import server.ProcessServer;
import server.ServerPool;
import threads.DispatcherThread;
import threads.RequestProcessThread;
import threads.RequestQueueThread;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ElasticComputing {

    public static void main(String args[])
    {
        RequestQueue requestQueue = new RequestQueue();
        ServerPool serverPool = new ServerPool(5);
        CopyOnWriteArrayList<ProcessServer> availableServers = new CopyOnWriteArrayList<>();
        Thread requestQueueThread = new Thread(new RequestQueueThread(requestQueue));
        DispatcherThread dispatcherRunnable = new DispatcherThread(requestQueue,serverPool,availableServers);
        Thread dispatcher = new Thread(dispatcherRunnable);
        requestQueueThread.start();
        dispatcher.start();
        try {
            dispatcherRunnable.startProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            requestQueueThread.join();
            dispatcher.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
