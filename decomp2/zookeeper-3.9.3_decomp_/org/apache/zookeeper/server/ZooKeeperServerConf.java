package org.apache.zookeeper.server;

import java.util.LinkedHashMap;
import java.util.Map;

public class ZooKeeperServerConf {
   public static final String KEY_CLIENT_PORT = "client_port";
   public static final String KEY_DATA_DIR = "data_dir";
   public static final String KEY_DATA_LOG_DIR = "data_log_dir";
   public static final String KEY_TICK_TIME = "tick_time";
   public static final String KEY_MAX_CLIENT_CNXNS = "max_client_cnxns";
   public static final String KEY_MIN_SESSION_TIMEOUT = "min_session_timeout";
   public static final String KEY_MAX_SESSION_TIMEOUT = "max_session_timeout";
   public static final String KEY_SERVER_ID = "server_id";
   public static final String KEY_CLIENT_PORT_LISTEN_BACKLOG = "client_port_listen_backlog";
   private final int clientPort;
   private final String dataDir;
   private final String dataLogDir;
   private final int tickTime;
   private final int maxClientCnxnsPerHost;
   private final int minSessionTimeout;
   private final int maxSessionTimeout;
   private final long serverId;
   private final int clientPortListenBacklog;

   ZooKeeperServerConf(int clientPort, String dataDir, String dataLogDir, int tickTime, int maxClientCnxnsPerHost, int minSessionTimeout, int maxSessionTimeout, long serverId, int clientPortListenBacklog) {
      this.clientPort = clientPort;
      this.dataDir = dataDir;
      this.dataLogDir = dataLogDir;
      this.tickTime = tickTime;
      this.maxClientCnxnsPerHost = maxClientCnxnsPerHost;
      this.minSessionTimeout = minSessionTimeout;
      this.maxSessionTimeout = maxSessionTimeout;
      this.serverId = serverId;
      this.clientPortListenBacklog = clientPortListenBacklog;
   }

   public int getClientPort() {
      return this.clientPort;
   }

   public String getDataDir() {
      return this.dataDir;
   }

   public String getDataLogDir() {
      return this.dataLogDir;
   }

   public int getTickTime() {
      return this.tickTime;
   }

   public int getMaxClientCnxnsPerHost() {
      return this.maxClientCnxnsPerHost;
   }

   public int getMinSessionTimeout() {
      return this.minSessionTimeout;
   }

   public int getMaxSessionTimeout() {
      return this.maxSessionTimeout;
   }

   public long getServerId() {
      return this.serverId;
   }

   public int getClientPortListenBacklog() {
      return this.clientPortListenBacklog;
   }

   public Map toMap() {
      Map<String, Object> conf = new LinkedHashMap();
      conf.put("client_port", this.clientPort);
      conf.put("data_dir", this.dataDir);
      conf.put("data_log_dir", this.dataLogDir);
      conf.put("tick_time", this.tickTime);
      conf.put("max_client_cnxns", this.maxClientCnxnsPerHost);
      conf.put("min_session_timeout", this.minSessionTimeout);
      conf.put("max_session_timeout", this.maxSessionTimeout);
      conf.put("server_id", this.serverId);
      conf.put("client_port_listen_backlog", this.clientPortListenBacklog);
      return conf;
   }
}
