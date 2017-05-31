package request;

/**
 * Created by Juilee on 5/28/2017.
 */
public class Request {

    private String requestName;

    private int processingTime;
    private long startTime;
    private int endTime;
    private String status;
    private static final int WAITINGTIME =5000;

    public Request(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public int getWaitingTime() {
        return WAITINGTIME;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
