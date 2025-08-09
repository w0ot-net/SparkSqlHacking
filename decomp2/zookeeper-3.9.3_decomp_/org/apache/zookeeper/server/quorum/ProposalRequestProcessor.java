package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.SyncRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProposalRequestProcessor implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(ProposalRequestProcessor.class);
   LeaderZooKeeperServer zks;
   RequestProcessor nextProcessor;
   SyncRequestProcessor syncProcessor;
   public static final String FORWARD_LEARNER_REQUESTS_TO_COMMIT_PROCESSOR_DISABLED = "zookeeper.forward_learner_requests_to_commit_processor_disabled";
   private final boolean forwardLearnerRequestsToCommitProcessorDisabled;

   public ProposalRequestProcessor(LeaderZooKeeperServer zks, RequestProcessor nextProcessor) {
      this.zks = zks;
      this.nextProcessor = nextProcessor;
      AckRequestProcessor ackProcessor = new AckRequestProcessor(zks.getLeader());
      this.syncProcessor = new SyncRequestProcessor(zks, ackProcessor);
      this.forwardLearnerRequestsToCommitProcessorDisabled = Boolean.getBoolean("zookeeper.forward_learner_requests_to_commit_processor_disabled");
      LOG.info("{} = {}", "zookeeper.forward_learner_requests_to_commit_processor_disabled", this.forwardLearnerRequestsToCommitProcessorDisabled);
   }

   public void initialize() {
      this.syncProcessor.start();
   }

   public void processRequest(Request request) throws RequestProcessor.RequestProcessorException {
      if (request instanceof LearnerSyncRequest) {
         this.zks.getLeader().processSync((LearnerSyncRequest)request);
      } else {
         if (this.shouldForwardToNextProcessor(request)) {
            this.nextProcessor.processRequest(request);
         }

         if (request.getHdr() != null) {
            try {
               this.zks.getLeader().propose(request);
            } catch (Leader.XidRolloverException e) {
               throw new RequestProcessor.RequestProcessorException(e.getMessage(), e);
            }

            this.syncProcessor.processRequest(request);
         }
      }

   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.nextProcessor.shutdown();
      this.syncProcessor.shutdown();
   }

   private boolean shouldForwardToNextProcessor(Request request) {
      if (!this.forwardLearnerRequestsToCommitProcessorDisabled) {
         return true;
      } else if (request.getOwner() instanceof LearnerHandler) {
         ServerMetrics.getMetrics().REQUESTS_NOT_FORWARDED_TO_COMMIT_PROCESSOR.add(1L);
         return false;
      } else {
         return true;
      }
   }
}
