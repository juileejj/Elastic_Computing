package server;

import dataStructures.CircularQueueArray;
import dataStructures.QueueArray;
import request.Request;

import java.util.*;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ProcessServer {

    private CircularQueueArray waitingQueue;

    public ProcessServer() {

        waitingQueue =  new CircularQueueArray(5);

    }

    public CircularQueueArray getWaitingQueue() {
        return waitingQueue;
    }
}
