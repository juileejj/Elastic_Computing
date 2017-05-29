package server;

/**
 * Created by Juilee on 5/28/2017.
 */
public class ServerPool {

    private int poolSize;
    private ProcessServer[] servers;
    private static final ServerPool serverPool = new ServerPool(10);

    private ServerPool(int poolSize) {
        this.poolSize = poolSize;
        servers = new ProcessServer[poolSize];
    }

    public ServerPool getInstance()
    {
        return serverPool;
    }

    public ProcessServer[] getServers() {
        return servers;
    }
}
