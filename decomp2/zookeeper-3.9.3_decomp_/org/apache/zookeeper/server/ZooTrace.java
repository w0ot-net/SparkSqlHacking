package org.apache.zookeeper.server;

import org.apache.zookeeper.server.quorum.LearnerHandler;
import org.apache.zookeeper.server.quorum.QuorumPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooTrace {
   public static final long CLIENT_REQUEST_TRACE_MASK = 2L;
   /** @deprecated */
   @Deprecated
   public static final long CLIENT_DATA_PACKET_TRACE_MASK = 4L;
   public static final long CLIENT_PING_TRACE_MASK = 8L;
   public static final long SERVER_PACKET_TRACE_MASK = 16L;
   public static final long SESSION_TRACE_MASK = 32L;
   public static final long EVENT_DELIVERY_TRACE_MASK = 64L;
   public static final long SERVER_PING_TRACE_MASK = 128L;
   public static final long WARNING_TRACE_MASK = 256L;
   /** @deprecated */
   @Deprecated
   public static final long JMX_TRACE_MASK = 512L;
   private static long traceMask = 306L;

   public static synchronized long getTextTraceLevel() {
      return traceMask;
   }

   public static synchronized void setTextTraceLevel(long mask) {
      traceMask = mask;
      Logger LOG = LoggerFactory.getLogger(ZooTrace.class);
      LOG.info("Set text trace mask to 0x{}", Long.toHexString(mask));
   }

   public static synchronized boolean isTraceEnabled(Logger log, long mask) {
      return log.isTraceEnabled() && (mask & traceMask) != 0L;
   }

   public static void logTraceMessage(Logger log, long mask, String msg) {
      if (isTraceEnabled(log, mask)) {
         log.trace(msg);
      }

   }

   public static void logQuorumPacket(Logger log, long mask, char direction, QuorumPacket qp) {
      if (isTraceEnabled(log, mask)) {
         logTraceMessage(log, mask, direction + " " + LearnerHandler.packetToString(qp));
      }

   }

   public static void logRequest(Logger log, long mask, char rp, Request request, String header) {
      if (isTraceEnabled(log, mask)) {
         log.trace(header + ":" + rp + request.toString());
      }

   }
}
