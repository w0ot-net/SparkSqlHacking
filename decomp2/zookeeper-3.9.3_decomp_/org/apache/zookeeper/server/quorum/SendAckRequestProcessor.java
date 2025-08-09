package org.apache.zookeeper.server.quorum;

import java.io.Flushable;
import java.io.IOException;
import java.util.List;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendAckRequestProcessor implements RequestProcessor, Flushable {
   private static final Logger LOG = LoggerFactory.getLogger(SendAckRequestProcessor.class);
   Learner learner;

   SendAckRequestProcessor(Learner peer) {
      this.learner = peer;
   }

   public void processRequest(Request si) {
      if (si.type != 9) {
         QuorumPacket qp = new QuorumPacket(3, si.getHdr().getZxid(), (byte[])null, (List)null);

         try {
            si.logLatency(ServerMetrics.getMetrics().PROPOSAL_ACK_CREATION_LATENCY);
            this.learner.writePacket(qp, false);
         } catch (IOException e) {
            LOG.warn("Closing connection to leader, exception during packet send", e);
            this.learner.closeSocket();
         }
      }

   }

   public void flush() throws IOException {
      try {
         this.learner.writePacket((QuorumPacket)null, true);
      } catch (IOException e) {
         LOG.warn("Closing connection to leader, exception during packet send", e);
         this.learner.closeSocket();
      }

   }

   public void shutdown() {
   }
}
