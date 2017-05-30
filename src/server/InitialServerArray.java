package server;

/**
 * Created by Juilee on 5/29/2017.
 */
public class InitialServerArray {
    private ProcessServer[] availableServers;
    private int size;

    public InitialServerArray(int size) {

        this.size =size;
        availableServers = new ProcessServer[size];
    }

    public ProcessServer[] getAvailableServers() {
        return availableServers;
    }

    public void addAvailableProcessors()
    {
        for(int i=0;i<size;i++)
        {
            ProcessServer processServer = new ProcessServer();
            availableServers[i]=processServer;
        }
    }

}
