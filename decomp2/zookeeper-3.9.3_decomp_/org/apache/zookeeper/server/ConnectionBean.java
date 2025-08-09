package org.apache.zookeeper.server;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;
import javax.management.ObjectName;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.jmx.ZKMBeanInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionBean implements ConnectionMXBean, ZKMBeanInfo {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectionBean.class);
   private final ServerCnxn connection;
   private final Stats stats;
   private final ZooKeeperServer zk;
   private final String remoteIP;
   private final long sessionId;

   public ConnectionBean(ServerCnxn connection, ZooKeeperServer zk) {
      this.connection = connection;
      this.stats = connection;
      this.zk = zk;
      InetSocketAddress sockAddr = connection.getRemoteSocketAddress();
      if (sockAddr == null) {
         this.remoteIP = "Unknown";
      } else {
         InetAddress addr = sockAddr.getAddress();
         if (addr instanceof Inet6Address) {
            this.remoteIP = ObjectName.quote(addr.getHostAddress());
         } else {
            this.remoteIP = addr.getHostAddress();
         }
      }

      this.sessionId = connection.getSessionId();
   }

   public String getSessionId() {
      return "0x" + Long.toHexString(this.sessionId);
   }

   public String getSourceIP() {
      InetSocketAddress sockAddr = this.connection.getRemoteSocketAddress();
      return sockAddr == null ? null : sockAddr.getAddress().getHostAddress() + ":" + sockAddr.getPort();
   }

   public String getName() {
      return MBeanRegistry.getInstance().makeFullPath("Connections", this.remoteIP, this.getSessionId());
   }

   public boolean isHidden() {
      return false;
   }

   public String[] getEphemeralNodes() {
      if (this.zk.getZKDatabase() != null) {
         String[] res = (String[])this.zk.getZKDatabase().getEphemerals(this.sessionId).toArray(new String[0]);
         Arrays.sort(res);
         return res;
      } else {
         return null;
      }
   }

   public String getStartedTime() {
      return this.stats.getEstablished().toString();
   }

   public void terminateSession() {
      try {
         this.zk.closeSession(this.sessionId);
      } catch (Exception e) {
         LOG.warn("Unable to closeSession() for session: 0x{}", this.getSessionId(), e);
      }

   }

   public void terminateConnection() {
      this.connection.sendCloseSession();
   }

   public void resetCounters() {
      this.stats.resetStats();
   }

   public String toString() {
      return "ConnectionBean{ClientIP=" + ObjectName.quote(this.getSourceIP()) + ",SessionId=0x" + this.getSessionId() + "}";
   }

   public long getOutstandingRequests() {
      return this.stats.getOutstandingRequests();
   }

   public long getPacketsReceived() {
      return this.stats.getPacketsReceived();
   }

   public long getPacketsSent() {
      return this.stats.getPacketsSent();
   }

   public int getSessionTimeout() {
      return this.connection.getSessionTimeout();
   }

   public long getMinLatency() {
      return this.stats.getMinLatency();
   }

   public long getAvgLatency() {
      return this.stats.getAvgLatency();
   }

   public long getMaxLatency() {
      return this.stats.getMaxLatency();
   }

   public String getLastOperation() {
      return this.stats.getLastOperation();
   }

   public String getLastCxid() {
      return "0x" + Long.toHexString(this.stats.getLastCxid());
   }

   public String getLastZxid() {
      return "0x" + Long.toHexString(this.stats.getLastZxid());
   }

   public String getLastResponseTime() {
      return Time.elapsedTimeToDate(this.stats.getLastResponseTime()).toString();
   }

   public long getLastLatency() {
      return this.stats.getLastLatency();
   }
}
