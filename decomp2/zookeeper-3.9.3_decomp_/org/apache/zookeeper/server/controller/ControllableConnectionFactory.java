package org.apache.zookeeper.server.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.apache.zookeeper.server.NIOServerCnxn;
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressFBWarnings(
   value = {"SWL_SLEEP_WITH_LOCK_HELD"},
   justification = "no dead lock"
)
public class ControllableConnectionFactory extends NIOServerCnxnFactory {
   private static final Logger LOG = LoggerFactory.getLogger(ControllableConnectionFactory.class);
   private long responseDelayInMs = 0L;
   private long remainingRequestsToFail = 0L;
   private long remainingResponsesToHold = 0L;

   protected NIOServerCnxn createConnection(SocketChannel sock, SelectionKey sk, NIOServerCnxnFactory.SelectorThread selectorThread) throws IOException {
      return new ControllableConnection(this.zkServer, sock, sk, this, selectorThread);
   }

   public synchronized void delayRequestIfNeeded() {
      try {
         if (this.responseDelayInMs > 0L) {
            Thread.sleep(this.responseDelayInMs);
         }
      } catch (InterruptedException ex) {
         LOG.warn("Interrupted while delaying requests", ex);
      }

   }

   public synchronized boolean shouldFailNextRequest() {
      if (this.remainingRequestsToFail == 0L) {
         return false;
      } else {
         if (this.remainingRequestsToFail > 0L) {
            --this.remainingRequestsToFail;
         }

         return true;
      }
   }

   public synchronized boolean shouldSendResponse() {
      if (this.remainingResponsesToHold == 0L) {
         return true;
      } else {
         if (this.remainingResponsesToHold > 0L) {
            --this.remainingResponsesToHold;
         }

         return false;
      }
   }

   public synchronized void delayResponses(long delayInMs) {
      if (delayInMs < 0L) {
         throw new IllegalArgumentException("delay must be non-negative");
      } else {
         this.responseDelayInMs = delayInMs;
      }
   }

   public synchronized void resetBadBehavior() {
      this.responseDelayInMs = 0L;
      this.remainingRequestsToFail = 0L;
      this.remainingResponsesToHold = 0L;
   }

   public synchronized void failAllFutureRequests() {
      this.remainingRequestsToFail = -1L;
   }

   public synchronized void failFutureRequests(long requestsToFail) {
      this.remainingRequestsToFail = requestsToFail;
   }

   public synchronized void holdAllFutureResponses() {
      this.remainingResponsesToHold = -1L;
   }

   public synchronized void holdFutureResponses(long requestsToHold) {
      this.remainingResponsesToHold = requestsToHold;
   }
}
