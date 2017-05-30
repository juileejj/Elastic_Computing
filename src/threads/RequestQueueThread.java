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
            //System.out.println("Request start time"+request.getStartTime());
        }
    }
}
