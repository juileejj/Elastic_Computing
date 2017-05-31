package server;

import dataStructures.CircularQueueArray;
import dataStructures.QueueArray;
import request.Request;

import java.util.*;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ProcessServer {

    private String name;
    private CircularQueueArray waitingQueue;

    public ProcessServer(String name) {
        this.name = name;
        this.waitingQueue = new CircularQueueArray(5);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularQueueArray getWaitingQueue() {
        return waitingQueue;
    }
}
