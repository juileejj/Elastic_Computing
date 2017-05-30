import request.Request;
import request.RequestQueue;
import server.InitialServerArray;
import server.ServerPool;
import threads.DispatcherThread;
import threads.RequestQueueThread;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ElasticComputing {

    public static void main(String args[])
    {
        RequestQueue requestQueue = new RequestQueue();
        ServerPool serverPool = new ServerPool(10);
        Thread requestQueueThread = new Thread(new RequestQueueThread(requestQueue));
        Thread dispatcher = new Thread(new DispatcherThread(requestQueue,serverPool));
        requestQueueThread.start();
        dispatcher.start();
        try {
            requestQueueThread.join();
            dispatcher.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
