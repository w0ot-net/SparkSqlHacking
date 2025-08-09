package org.apache.zookeeper.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.compat.ProtocolManager;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.metrics.Counter;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerCnxn implements Stats, ServerWatcher {
   public static final Object me = new Object();
   private static final Logger LOG = LoggerFactory.getLogger(ServerCnxn.class);
   public final ProtocolManager protocolManager = new ProtocolManager();
   private final Set authInfo = Collections.newSetFromMap(new ConcurrentHashMap());
   private final AtomicLong outstandingCount = new AtomicLong();
   final ZooKeeperServer zkServer;
   private volatile boolean stale = false;
   private volatile boolean invalid = false;
   protected ZooKeeperSaslServer zooKeeperSaslServer = null;
   protected final Date established = new Date();
   protected final AtomicLong packetsReceived = new AtomicLong();
   protected final AtomicLong packetsSent = new AtomicLong();
   protected long minLatency;
   protected long maxLatency;
   protected String lastOp;
   protected long lastCxid;
   protected long lastZxid;
   protected long lastResponseTime;
   protected long lastLatency;
   protected long count;
   protected long totalLatency;
   protected DisconnectReason disconnectReason;

   public ServerCnxn(ZooKeeperServer zkServer) {
      this.disconnectReason = ServerCnxn.DisconnectReason.UNKNOWN;
      this.zkServer = zkServer;
   }

   abstract int getSessionTimeout();

   public void incrOutstandingAndCheckThrottle(RequestHeader h) {
      if (h.getXid() > 0) {
         if (this.zkServer.shouldThrottle(this.outstandingCount.incrementAndGet())) {
            this.disableRecv(false);
         }

      }
   }

   public void decrOutstandingAndCheckThrottle(ReplyHeader h) {
      if (h.getXid() > 0) {
         if (!this.zkServer.shouldThrottle(this.outstandingCount.decrementAndGet())) {
            this.enableRecv();
         }

      }
   }

   public abstract void close(DisconnectReason var1);

   public abstract int sendResponse(ReplyHeader var1, Record var2, String var3, String var4, Stat var5, int var6) throws IOException;

   public int sendResponse(ReplyHeader h, Record r, String tag) throws IOException {
      return this.sendResponse(h, r, tag, (String)null, (Stat)null, -1);
   }

   protected byte[] serializeRecord(Record record) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream(ZooKeeperServer.intBufferStartingSizeBytes);
      BinaryOutputArchive bos = BinaryOutputArchive.getArchive(baos);
      bos.writeRecord(record, (String)null);
      return baos.toByteArray();
   }

   protected ByteBuffer[] serialize(ReplyHeader h, Record r, String tag, String cacheKey, Stat stat, int opCode) throws IOException {
      byte[] header = this.serializeRecord(h);
      byte[] data = null;
      if (r != null) {
         ResponseCache cache = null;
         Counter cacheHit = null;
         Counter cacheMiss = null;
         switch (opCode) {
            case 4:
               cache = this.zkServer.getReadResponseCache();
               cacheHit = ServerMetrics.getMetrics().RESPONSE_PACKET_CACHE_HITS;
               cacheMiss = ServerMetrics.getMetrics().RESPONSE_PACKET_CACHE_MISSING;
               break;
            case 12:
               cache = this.zkServer.getGetChildrenResponseCache();
               cacheHit = ServerMetrics.getMetrics().RESPONSE_PACKET_GET_CHILDREN_CACHE_HITS;
               cacheMiss = ServerMetrics.getMetrics().RESPONSE_PACKET_GET_CHILDREN_CACHE_MISSING;
         }

         if (cache != null && stat != null && cacheKey != null && !cacheKey.endsWith("zookeeper_stats")) {
            data = cache.get(cacheKey, stat);
            if (data == null) {
               data = this.serializeRecord(r);
               cache.put(cacheKey, data, stat);
               cacheMiss.add(1L);
            } else {
               cacheHit.add(1L);
            }
         } else {
            data = this.serializeRecord(r);
         }
      }

      int dataLength = data == null ? 0 : data.length;
      int packetLength = header.length + dataLength;
      ServerStats serverStats = this.serverStats();
      if (serverStats != null) {
         serverStats.updateClientResponseSize(packetLength);
      }

      ByteBuffer lengthBuffer = ByteBuffer.allocate(4).putInt(packetLength);
      lengthBuffer.rewind();
      int bufferLen = data != null ? 3 : 2;
      ByteBuffer[] buffers = new ByteBuffer[bufferLen];
      buffers[0] = lengthBuffer;
      buffers[1] = ByteBuffer.wrap(header);
      if (data != null) {
         buffers[2] = ByteBuffer.wrap(data);
      }

      return buffers;
   }

   public abstract void sendCloseSession();

   public void process(WatchedEvent event) {
      this.process(event, (List)null);
   }

   public abstract void process(WatchedEvent var1, List var2);

   public abstract long getSessionId();

   abstract void setSessionId(long var1);

   public List getAuthInfo() {
      return Collections.unmodifiableList(new ArrayList(this.authInfo));
   }

   public void addAuthInfo(Id id) {
      this.authInfo.add(id);
   }

   public boolean removeAuthInfo(Id id) {
      return this.authInfo.remove(id);
   }

   abstract void sendBuffer(ByteBuffer... var1);

   abstract void enableRecv();

   void disableRecv() {
      this.disableRecv(true);
   }

   abstract void disableRecv(boolean var1);

   abstract void setSessionTimeout(int var1);

   public boolean isStale() {
      return this.stale;
   }

   public void setStale() {
      this.stale = true;
   }

   public boolean isInvalid() {
      return this.invalid;
   }

   public void setInvalid() {
      if (!this.invalid) {
         if (!this.stale) {
            this.sendCloseSession();
         }

         this.invalid = true;
      }

   }

   protected void packetReceived(long bytes) {
      this.incrPacketsReceived();
      ServerStats serverStats = this.serverStats();
      if (serverStats != null) {
         this.serverStats().incrementPacketsReceived();
      }

      ServerMetrics.getMetrics().BYTES_RECEIVED_COUNT.add(bytes);
   }

   protected void packetSent() {
      this.incrPacketsSent();
      ServerStats serverStats = this.serverStats();
      if (serverStats != null) {
         serverStats.incrementPacketsSent();
      }

   }

   protected abstract ServerStats serverStats();

   public synchronized void resetStats() {
      this.disconnectReason = ServerCnxn.DisconnectReason.RESET_COMMAND;
      this.packetsReceived.set(0L);
      this.packetsSent.set(0L);
      this.minLatency = Long.MAX_VALUE;
      this.maxLatency = 0L;
      this.lastOp = "NA";
      this.lastCxid = -1L;
      this.lastZxid = -1L;
      this.lastResponseTime = 0L;
      this.lastLatency = 0L;
      this.count = 0L;
      this.totalLatency = 0L;
   }

   protected long incrPacketsReceived() {
      return this.packetsReceived.incrementAndGet();
   }

   protected long incrPacketsSent() {
      return this.packetsSent.incrementAndGet();
   }

   protected synchronized void updateStatsForResponse(long cxid, long zxid, String op, long start, long end) {
      if (cxid >= 0L) {
         this.lastCxid = cxid;
      }

      this.lastZxid = zxid;
      this.lastOp = op;
      this.lastResponseTime = end;
      long elapsed = end - start;
      this.lastLatency = elapsed;
      if (elapsed < this.minLatency) {
         this.minLatency = elapsed;
      }

      if (elapsed > this.maxLatency) {
         this.maxLatency = elapsed;
      }

      ++this.count;
      this.totalLatency += elapsed;
   }

   public Date getEstablished() {
      return (Date)this.established.clone();
   }

   public long getOutstandingRequests() {
      return this.outstandingCount.longValue();
   }

   public long getPacketsReceived() {
      return this.packetsReceived.longValue();
   }

   public long getPacketsSent() {
      return this.packetsSent.longValue();
   }

   public synchronized long getMinLatency() {
      return this.minLatency == Long.MAX_VALUE ? 0L : this.minLatency;
   }

   public synchronized long getAvgLatency() {
      return this.count == 0L ? 0L : this.totalLatency / this.count;
   }

   public synchronized long getMaxLatency() {
      return this.maxLatency;
   }

   public synchronized String getLastOperation() {
      return this.lastOp;
   }

   public synchronized long getLastCxid() {
      return this.lastCxid;
   }

   public synchronized long getLastZxid() {
      return this.lastZxid;
   }

   public synchronized long getLastResponseTime() {
      return this.lastResponseTime;
   }

   public synchronized long getLastLatency() {
      return this.lastLatency;
   }

   public String toString() {
      StringWriter sw = new StringWriter();
      PrintWriter pwriter = new PrintWriter(sw);
      this.dumpConnectionInfo(pwriter, false);
      pwriter.flush();
      pwriter.close();
      return sw.toString();
   }

   public abstract InetSocketAddress getRemoteSocketAddress();

   public abstract int getInterestOps();

   public abstract boolean isSecure();

   public abstract Certificate[] getClientCertificateChain();

   public abstract void setClientCertificateChain(Certificate[] var1);

   public synchronized void dumpConnectionInfo(PrintWriter pwriter, boolean brief) {
      pwriter.print(" ");
      pwriter.print(this.getRemoteSocketAddress());
      pwriter.print("[");
      int interestOps = this.getInterestOps();
      pwriter.print(interestOps == 0 ? "0" : Integer.toHexString(interestOps));
      pwriter.print("](queued=");
      pwriter.print(this.getOutstandingRequests());
      pwriter.print(",recved=");
      pwriter.print(this.getPacketsReceived());
      pwriter.print(",sent=");
      pwriter.print(this.getPacketsSent());
      if (!brief) {
         long sessionId = this.getSessionId();
         if (sessionId != 0L) {
            pwriter.print(",sid=0x");
            pwriter.print(Long.toHexString(sessionId));
            pwriter.print(",lop=");
            pwriter.print(this.getLastOperation());
            pwriter.print(",est=");
            pwriter.print(this.getEstablished().getTime());
            pwriter.print(",to=");
            pwriter.print(this.getSessionTimeout());
            long lastCxid = this.getLastCxid();
            if (lastCxid >= 0L) {
               pwriter.print(",lcxid=0x");
               pwriter.print(Long.toHexString(lastCxid));
            }

            pwriter.print(",lzxid=0x");
            pwriter.print(Long.toHexString(this.getLastZxid()));
            pwriter.print(",lresp=");
            pwriter.print(this.getLastResponseTime());
            pwriter.print(",llat=");
            pwriter.print(this.getLastLatency());
            pwriter.print(",minlat=");
            pwriter.print(this.getMinLatency());
            pwriter.print(",avglat=");
            pwriter.print(this.getAvgLatency());
            pwriter.print(",maxlat=");
            pwriter.print(this.getMaxLatency());
         }
      }

      pwriter.print(")");
   }

   public synchronized Map getConnectionInfo(boolean brief) {
      Map<String, Object> info = new LinkedHashMap();
      info.put("remote_socket_address", this.getRemoteSocketAddress());
      info.put("interest_ops", this.getInterestOps());
      info.put("outstanding_requests", this.getOutstandingRequests());
      info.put("packets_received", this.getPacketsReceived());
      info.put("packets_sent", this.getPacketsSent());
      if (!brief) {
         info.put("session_id", this.getSessionId());
         info.put("last_operation", this.getLastOperation());
         info.put("established", this.getEstablished());
         info.put("session_timeout", this.getSessionTimeout());
         info.put("last_cxid", this.getLastCxid());
         info.put("last_zxid", this.getLastZxid());
         info.put("last_response_time", this.getLastResponseTime());
         info.put("last_latency", this.getLastLatency());
         info.put("min_latency", this.getMinLatency());
         info.put("avg_latency", this.getAvgLatency());
         info.put("max_latency", this.getMaxLatency());
      }

      return info;
   }

   public void cleanupWriterSocket(PrintWriter pwriter) {
      try {
         if (pwriter != null) {
            pwriter.flush();
            pwriter.close();
         }
      } catch (Exception e) {
         LOG.info("Error closing PrintWriter ", e);
      } finally {
         try {
            this.close(ServerCnxn.DisconnectReason.CLOSE_CONNECTION_COMMAND);
         } catch (Exception e) {
            LOG.error("Error closing a command socket ", e);
         }

      }

   }

   public boolean isZKServerRunning() {
      return this.zkServer != null && this.zkServer.isRunning();
   }

   public String getHostAddress() {
      InetSocketAddress remoteSocketAddress = this.getRemoteSocketAddress();
      if (remoteSocketAddress == null) {
         return "";
      } else {
         InetAddress address = remoteSocketAddress.getAddress();
         return address == null ? "" : address.getHostAddress();
      }
   }

   public String getSessionIdHex() {
      return "0x" + Long.toHexString(this.getSessionId());
   }

   public static enum DisconnectReason {
      UNKNOWN("unknown"),
      SERVER_SHUTDOWN("server_shutdown"),
      CLOSE_ALL_CONNECTIONS_FORCED("close_all_connections_forced"),
      CONNECTION_CLOSE_FORCED("connection_close_forced"),
      CONNECTION_EXPIRED("connection_expired"),
      CLIENT_CLOSED_CONNECTION("client_closed_connection"),
      CLIENT_CLOSED_SESSION("client_closed_session"),
      UNABLE_TO_READ_FROM_CLIENT("unable_to_read_from_client"),
      NOT_READ_ONLY_CLIENT("not_read_only_client"),
      CLIENT_ZXID_AHEAD("client_zxid_ahead"),
      INFO_PROBE("info_probe"),
      CLIENT_RECONNECT("client_reconnect"),
      CANCELLED_KEY_EXCEPTION("cancelled_key_exception"),
      IO_EXCEPTION("io_exception"),
      IO_EXCEPTION_IN_SESSION_INIT("io_exception_in_session_init"),
      BUFFER_UNDERFLOW_EXCEPTION("buffer_underflow_exception"),
      SASL_AUTH_FAILURE("sasl_auth_failure"),
      RESET_COMMAND("reset_command"),
      CLOSE_CONNECTION_COMMAND("close_connection_command"),
      CLEAN_UP("clean_up"),
      CONNECTION_MODE_CHANGED("connection_mode_changed"),
      RENEW_GLOBAL_SESSION_IN_RO_MODE("renew a global session in readonly mode"),
      CHANNEL_DISCONNECTED("channel disconnected"),
      CHANNEL_CLOSED_EXCEPTION("channel_closed_exception"),
      AUTH_PROVIDER_NOT_FOUND("auth provider not found"),
      FAILED_HANDSHAKE("Unsuccessful handshake"),
      CLIENT_RATE_LIMIT("Client hits rate limiting threshold"),
      CLIENT_CNX_LIMIT("Client hits connection limiting threshold");

      String disconnectReason;

      private DisconnectReason(String reason) {
         this.disconnectReason = reason;
      }

      public String toDisconnectReasonString() {
         return this.disconnectReason;
      }
   }

   public static class CloseRequestException extends IOException {
      private static final long serialVersionUID = -7854505709816442681L;
      private DisconnectReason reason;

      public CloseRequestException(String msg, DisconnectReason reason) {
         super(msg);
         this.reason = reason;
      }

      public DisconnectReason getReason() {
         return this.reason;
      }
   }

   protected static class EndOfStreamException extends IOException {
      private static final long serialVersionUID = -8255690282104294178L;
      private DisconnectReason reason;

      public EndOfStreamException(String msg, DisconnectReason reason) {
         super(msg);
         this.reason = reason;
      }

      public String toString() {
         return "EndOfStreamException: " + this.getMessage();
      }

      public DisconnectReason getReason() {
         return this.reason;
      }
   }
}
