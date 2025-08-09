package org.apache.zookeeper.server.quorum;

import java.net.SocketAddress;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AckRequestProcessor implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(AckRequestProcessor.class);
   Leader leader;

   AckRequestProcessor(Leader leader) {
      this.leader = leader;
   }

   public void processRequest(Request request) {
      QuorumPeer self = this.leader.self;
      if (self != null) {
         request.logLatency(ServerMetrics.getMetrics().PROPOSAL_ACK_CREATION_LATENCY);
         this.leader.processAck(self.getMyId(), request.zxid, (SocketAddress)null);
      } else {
         LOG.error("Null QuorumPeer");
      }

   }

   public void shutdown() {
   }
}
