package org.apache.zookeeper.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Supplier;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.metrics.Summary;
import org.apache.zookeeper.metrics.SummarySet;
import org.apache.zookeeper.server.persistence.Util;
import org.apache.zookeeper.server.quorum.LearnerHandler;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.AuthUtil;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request {
   private static final Logger LOG = LoggerFactory.getLogger(Request.class);
   public static final Request requestOfDeath = new Request((ServerCnxn)null, 0L, 0, 0, (RequestRecord)null, (List)null);
   private static volatile boolean staleConnectionCheck = Boolean.parseBoolean(System.getProperty("zookeeper.request_stale_connection_check", "true"));
   private static volatile boolean staleLatencyCheck = Boolean.parseBoolean(System.getProperty("zookeeper.request_stale_latency_check", "false"));
   public final long sessionId;
   public final int cxid;
   public final int type;
   private final RequestRecord request;
   public final ServerCnxn cnxn;
   private TxnHeader hdr;
   private Record txn;
   public long zxid = -1L;
   public final List authInfo;
   public final long createTime = Time.currentElapsedTime();
   public long prepQueueStartTime = -1L;
   public long prepStartTime = -1L;
   public long commitProcQueueStartTime = -1L;
   public long commitRecvTime = -1L;
   public long syncQueueStartTime;
   public long requestThrottleQueueTime;
   private Object owner;
   private KeeperException e;
   public QuorumVerifier qv = null;
   private TxnDigest txnDigest;
   private boolean isThrottledFlag = false;
   private boolean isLocalSession = false;
   private int largeRequestSize = -1;

   public Request(ServerCnxn cnxn, long sessionId, int xid, int type, RequestRecord request, List authInfo) {
      this.cnxn = cnxn;
      this.sessionId = sessionId;
      this.cxid = xid;
      this.type = type;
      this.request = request;
      this.authInfo = authInfo;
   }

   public Request(long sessionId, int xid, int type, TxnHeader hdr, Record txn, long zxid) {
      this.sessionId = sessionId;
      this.cxid = xid;
      this.type = type;
      this.hdr = hdr;
      this.txn = txn;
      this.zxid = zxid;
      this.request = null;
      this.cnxn = null;
      this.authInfo = null;
   }

   public Record readRequestRecord(Supplier constructor) throws IOException {
      if (this.request != null) {
         return this.request.readRecord(constructor);
      } else {
         throw new IOException(new NullPointerException("request"));
      }
   }

   public Record readRequestRecordNoException(Supplier constructor) {
      try {
         return this.readRequestRecord(constructor);
      } catch (IOException var3) {
         return null;
      }
   }

   public byte[] readRequestBytes() {
      return this.request != null ? this.request.readBytes() : null;
   }

   public String requestDigest() {
      if (this.request == null) {
         return "request buffer is null";
      } else {
         StringBuilder sb = new StringBuilder();
         byte[] payload = this.request.readBytes();

         for(byte b : payload) {
            sb.append(String.format("%02x", 255 & b));
         }

         return sb.toString();
      }
   }

   public boolean isThrottled() {
      return this.isThrottledFlag;
   }

   public void setIsThrottled(boolean val) {
      this.isThrottledFlag = val;
   }

   public boolean isThrottlable() {
      return this.type != 11 && this.type != -11 && this.type != -10;
   }

   public byte[] getSerializeData() {
      if (this.hdr == null) {
         return null;
      } else {
         try {
            return Util.marshallTxnEntry(this.hdr, this.txn, this.txnDigest);
         } catch (IOException e) {
            LOG.error("This really should be impossible.", e);
            return new byte[32];
         }
      }
   }

   public boolean isLocalSession() {
      return this.isLocalSession;
   }

   public void setLocalSession(boolean isLocalSession) {
      this.isLocalSession = isLocalSession;
   }

   public void setLargeRequestSize(int size) {
      this.largeRequestSize = size;
   }

   public int getLargeRequestSize() {
      return this.largeRequestSize;
   }

   public Object getOwner() {
      return this.owner;
   }

   public void setOwner(Object owner) {
      this.owner = owner;
   }

   public TxnHeader getHdr() {
      return this.hdr;
   }

   public void setHdr(TxnHeader hdr) {
      this.hdr = hdr;
   }

   public Record getTxn() {
      return this.txn;
   }

   public void setTxn(Record txn) {
      this.txn = txn;
   }

   public ServerCnxn getConnection() {
      return this.cnxn;
   }

   public static boolean getStaleLatencyCheck() {
      return staleLatencyCheck;
   }

   public static void setStaleLatencyCheck(boolean check) {
      staleLatencyCheck = check;
   }

   public static boolean getStaleConnectionCheck() {
      return staleConnectionCheck;
   }

   public static void setStaleConnectionCheck(boolean check) {
      staleConnectionCheck = check;
   }

   public boolean isStale() {
      if (this.cnxn == null) {
         return false;
      } else if (this.type == -11) {
         return false;
      } else if (!staleConnectionCheck || !this.cnxn.isStale() && !this.cnxn.isInvalid()) {
         if (staleLatencyCheck) {
            long currentTime = Time.currentElapsedTime();
            return currentTime - this.createTime > (long)this.cnxn.getSessionTimeout();
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public boolean mustDrop() {
      return this.cnxn != null && this.cnxn.isInvalid();
   }

   static boolean isValid(int type) {
      switch (type) {
         case -11:
         case -10:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 11:
         case 12:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 101:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
            return true;
         case -9:
         case -8:
         case -7:
         case -6:
         case -5:
         case -4:
         case -3:
         case -2:
         case -1:
         case 10:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 102:
         default:
            return false;
         case 0:
         case 13:
            return false;
      }
   }

   public boolean isQuorum() {
      switch (this.type) {
         case -11:
         case -10:
            return !this.isLocalSession;
         case -1:
         case 1:
         case 2:
         case 5:
         case 7:
         case 13:
         case 14:
         case 15:
         case 16:
         case 19:
         case 20:
         case 21:
            return true;
         case 3:
         case 4:
         case 6:
         case 8:
         case 12:
         case 22:
         case 103:
         case 104:
         case 107:
            return false;
         default:
            return false;
      }
   }

   public static String op2String(int op) {
      switch (op) {
         case -11:
            return "closeSession";
         case -10:
            return "createSession";
         case -9:
         case -8:
         case -7:
         case -6:
         case -5:
         case -4:
         case -3:
         case -2:
         case 10:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         default:
            return "unknown " + op;
         case -1:
            return "error";
         case 0:
            return "notification";
         case 1:
            return "create";
         case 2:
            return "delete";
         case 3:
            return "exists";
         case 4:
            return "getData";
         case 5:
            return "setData";
         case 6:
            return "getACL";
         case 7:
            return "setACL";
         case 8:
            return "getChildren";
         case 9:
            return "sync";
         case 11:
            return "ping";
         case 12:
            return "getChildren2";
         case 13:
            return "check";
         case 14:
            return "multi";
         case 15:
            return "create2";
         case 16:
            return "reconfig";
         case 17:
            return "checkWatches";
         case 18:
            return "removeWatches";
         case 19:
            return "createContainer";
         case 20:
            return "deleteContainer";
         case 21:
            return "createTTL";
         case 22:
            return "multiRead";
         case 100:
            return "auth";
         case 101:
            return "setWatches";
         case 102:
            return "sasl";
         case 103:
            return "getEphemerals";
         case 104:
            return "getAllChildrenNumber";
         case 105:
            return "setWatches2";
         case 106:
            return "addWatch";
         case 107:
            return "whoAmI";
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("sessionid:0x").append(Long.toHexString(this.sessionId)).append(" type:").append(op2String(this.type)).append(" cxid:0x").append(Long.toHexString((long)this.cxid)).append(" zxid:0x").append(Long.toHexString(this.hdr == null ? -2L : this.hdr.getZxid())).append(" txntype:").append(this.hdr == null ? "unknown" : "" + this.hdr.getType());
      String path = "n/a";
      if (this.type != -10 && this.type != 101 && this.type != 105 && this.type != -11 && this.request != null) {
         try {
            byte[] bytes = this.request.readBytes();
            if (bytes != null && bytes.length >= 4) {
               ByteBuffer buf = ByteBuffer.wrap(bytes);
               int pathLen = buf.getInt();
               if (pathLen >= 0 && pathLen < 4096 && buf.remaining() >= pathLen) {
                  byte[] b = new byte[pathLen];
                  buf.get(b);
                  path = new String(b, StandardCharsets.UTF_8);
               }
            }
         } catch (Exception var7) {
         }
      }

      sb.append(" reqpath:").append(path);
      return sb.toString();
   }

   public void setException(KeeperException e) {
      this.e = e;
   }

   public KeeperException getException() {
      return this.e;
   }

   public void logLatency(Summary metric) {
      this.logLatency(metric, Time.currentWallTime());
   }

   public void logLatency(Summary metric, long currentTime) {
      if (this.hdr != null) {
         long latency = currentTime - this.hdr.getTime();
         if (latency >= 0L) {
            metric.add(latency);
         }
      }

   }

   public void logLatency(SummarySet metric, String key, long currentTime) {
      if (this.hdr != null) {
         long latency = currentTime - this.hdr.getTime();
         if (latency >= 0L) {
            metric.add(key, latency);
         }
      }

   }

   public void logLatency(SummarySet metric, String key) {
      this.logLatency(metric, key, Time.currentWallTime());
   }

   public String getUsersForAudit() {
      return AuthUtil.getUsers(this.authInfo);
   }

   public TxnDigest getTxnDigest() {
      return this.txnDigest;
   }

   public void setTxnDigest(TxnDigest txnDigest) {
      this.txnDigest = txnDigest;
   }

   public boolean isFromLearner() {
      return this.owner instanceof LearnerHandler;
   }
}
