package server;

import java.util.ArrayList;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ServerPool {

    private int poolSize;
    private ArrayList<ProcessServer> servers;


    public ServerPool(int poolSize) {
        this.poolSize = poolSize;
        servers = new ArrayList<>(poolSize);
        addServers();
    }

    public ArrayList<ProcessServer> getServers() {
        return servers;
    }

    private void addServers()
    {
        for(int i=0;i<poolSize;i++)
        {
            ProcessServer processServer = new ProcessServer();
            servers.add(processServer);
        }
    }


}
