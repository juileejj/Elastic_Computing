package server;

import dataStructures.QueueArray;
import request.Request;

import java.util.*;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ProcessServer {

    private QueueArray waitingQueue;

    public ProcessServer() {

        waitingQueue =  new QueueArray(10);

    }

    public QueueArray getWaitingQueue() {
        return waitingQueue;
    }
}
