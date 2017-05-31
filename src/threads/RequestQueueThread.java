package threads;

import request.Request;
import request.RequestQueue;

/**
 * Created by Juilee on 5/29/2017.
 */
public class RequestQueueThread implements Runnable {

    private RequestQueue requestQueue;

    public RequestQueueThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for(int i=0;i<50;i++)
        {
            Request request = new Request("Request "+i);
            request.setStatus("INCOMING");
            request.setProcessingTime(2000);
            request.setStartTime(System.currentTimeMillis());
            requestQueue.getRequestQueue().add(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
