package com.leechi.vendorclient.net;


import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

public class IdleConnectionMonitorThread extends  Thread {
    private  PoolingHttpClientConnectionManager connMgr;
    private volatile boolean shutdown;
    private int timeout_ms;

    public IdleConnectionMonitorThread(int timeout_ms, PoolingHttpClientConnectionManager connMgr) {
        super();
        this.timeout_ms = timeout_ms;
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        while(true) {
            if (shutdown) {
                break;
            }
            try {
                wait(timeout_ms);
            } catch (Exception ex) {
            }

            synchronized (connMgr) {
                connMgr.closeExpiredConnections();
                connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
            }
        }
    }


    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }
}


