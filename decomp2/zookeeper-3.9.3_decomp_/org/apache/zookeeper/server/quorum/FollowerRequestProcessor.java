package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZooKeeperCriticalThread;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.txn.ErrorTxn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FollowerRequestProcessor extends ZooKeeperCriticalThread implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(FollowerRequestProcessor.class);
   public static final String SKIP_LEARNER_REQUEST_TO_NEXT_PROCESSOR = "zookeeper.follower.skipLearnerRequestToNextProcessor";
   private final boolean skipLearnerRequestToNextProcessor;
   FollowerZooKeeperServer zks;
   RequestProcessor nextProcessor;
   LinkedBlockingQueue queuedRequests = new LinkedBlockingQueue();
   boolean finished = false;

   public FollowerRequestProcessor(FollowerZooKeeperServer zks, RequestProcessor nextProcessor) {
      super("FollowerRequestProcessor:" + zks.getServerId(), zks.getZooKeeperServerListener());
      this.zks = zks;
      this.nextProcessor = nextProcessor;
      this.skipLearnerRequestToNextProcessor = Boolean.getBoolean("zookeeper.follower.skipLearnerRequestToNextProcessor");
      LOG.info("Initialized FollowerRequestProcessor with {} as {}", "zookeeper.follower.skipLearnerRequestToNextProcessor", this.skipLearnerRequestToNextProcessor);
   }

   public void run() {
      try {
         while(!this.finished) {
            ServerMetrics.getMetrics().LEARNER_REQUEST_PROCESSOR_QUEUE_SIZE.add((long)this.queuedRequests.size());
            Request request = (Request)this.queuedRequests.take();
            if (LOG.isTraceEnabled()) {
               ZooTrace.logRequest(LOG, 2L, 'F', request, "");
            }

            if (request == Request.requestOfDeath) {
               break;
            }

            if (this.zks.authWriteRequest(request)) {
               this.maybeSendRequestToNextProcessor(request);
               if (!request.isThrottled()) {
                  switch (request.type) {
                     case -11:
                     case -10:
                        if (!request.isLocalSession()) {
                           this.zks.getFollower().request(request);
                        }
                     case -9:
                     case -8:
                     case -7:
                     case -6:
                     case -5:
                     case -4:
                     case -3:
                     case -2:
                     case -1:
                     case 0:
                     case 3:
                     case 4:
                     case 6:
                     case 8:
                     case 10:
                     case 11:
                     case 12:
                     case 17:
                     case 18:
                     default:
                        break;
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
                        this.zks.getFollower().request(request);
                        break;
                     case 9:
                        this.zks.pendingSyncs.add(request);
                        this.zks.getFollower().request(request);
                  }
               }
            }
         }
      } catch (RuntimeException e) {
         this.handleException(this.getName(), e);
      } catch (Exception e) {
         this.handleException(this.getName(), e);
      }

      LOG.info("FollowerRequestProcessor exited loop!");
   }

   private void maybeSendRequestToNextProcessor(Request request) throws RequestProcessor.RequestProcessorException {
      if (this.skipLearnerRequestToNextProcessor && request.isFromLearner()) {
         ServerMetrics.getMetrics().SKIP_LEARNER_REQUEST_TO_NEXT_PROCESSOR_COUNT.add(1L);
      } else {
         this.nextProcessor.processRequest(request);
      }

   }

   public void processRequest(Request request) {
      this.processRequest(request, true);
   }

   void processRequest(Request request, boolean checkForUpgrade) {
      if (!this.finished) {
         if (checkForUpgrade) {
            Request upgradeRequest = null;

            try {
               upgradeRequest = this.zks.checkUpgradeSession(request);
            } catch (KeeperException var5) {
               if (request.getHdr() != null) {
                  request.getHdr().setType(-1);
                  request.setTxn(new ErrorTxn(var5.code().intValue()));
               }

               request.setException(var5);
               LOG.warn("Error creating upgrade request", var5);
            } catch (IOException ie) {
               LOG.error("Unexpected error in upgrade", ie);
            }

            if (upgradeRequest != null) {
               this.queuedRequests.add(upgradeRequest);
            }
         }

         this.queuedRequests.add(request);
      }

   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.finished = true;
      this.queuedRequests.clear();
      this.queuedRequests.add(Request.requestOfDeath);
      this.nextProcessor.shutdown();
   }
}
