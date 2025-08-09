package org.apache.zookeeper.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.security.cert.Certificate;
import java.util.List;
import org.apache.jute.Record;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.ReplyHeader;

public class DumbWatcher extends ServerCnxn {
   private long sessionId;
   private String mostRecentPath;
   private Watcher.Event.EventType mostRecentEventType;
   private long mostRecentZxid;

   public DumbWatcher() {
      this(0L);
   }

   public DumbWatcher(long sessionId) {
      super((ZooKeeperServer)null);
      this.mostRecentZxid = -1L;
      this.sessionId = sessionId;
   }

   void setSessionTimeout(int sessionTimeout) {
   }

   public void process(WatchedEvent event, List znodeAcl) {
      this.mostRecentEventType = event.getType();
      this.mostRecentZxid = event.getZxid();
      this.mostRecentPath = event.getPath();
   }

   public String getMostRecentPath() {
      return this.mostRecentPath;
   }

   public Watcher.Event.EventType getMostRecentEventType() {
      return this.mostRecentEventType;
   }

   public long getMostRecentZxid() {
      return this.mostRecentZxid;
   }

   int getSessionTimeout() {
      return 0;
   }

   public void close(ServerCnxn.DisconnectReason reason) {
   }

   public int sendResponse(ReplyHeader h, Record r, String tag, String cacheKey, Stat stat, int opCode) throws IOException {
      return 0;
   }

   public void sendCloseSession() {
   }

   public long getSessionId() {
      return this.sessionId;
   }

   void setSessionId(long sessionId) {
   }

   void sendBuffer(ByteBuffer... closeConn) {
   }

   void enableRecv() {
   }

   void disableRecv(boolean waitDisableRecv) {
   }

   protected ServerStats serverStats() {
      return null;
   }

   public long getOutstandingRequests() {
      return 0L;
   }

   public InetSocketAddress getRemoteSocketAddress() {
      return null;
   }

   public int getInterestOps() {
      return 0;
   }

   public boolean isSecure() {
      return false;
   }

   public Certificate[] getClientCertificateChain() {
      return null;
   }

   public void setClientCertificateChain(Certificate[] chain) {
   }
}
