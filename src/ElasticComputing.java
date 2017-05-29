import request.Request;
import request.RequestQueue;
import threads.DispatcherThread;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ElasticComputing {

    public static void main(String args[])
    {
        RequestQueue requestQueue = new RequestQueue();

        for(int i=0;i<10;i++)
        {
            Request request = new Request();
            request.setStartTime(System.currentTimeMillis());
            requestQueue.getRequestQueue().add(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Request start time"+request.getStartTime());
        }
        System.out.println("Req Queue size:"+requestQueue.getRequestQueue().size());
        Thread dispatcher = new Thread(new DispatcherThread(requestQueue));
        dispatcher.start();
    }
}
