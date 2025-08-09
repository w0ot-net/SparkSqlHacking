package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.txn.ErrorTxn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderRequestProcessor implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(LeaderRequestProcessor.class);
   private final LeaderZooKeeperServer lzks;
   private final RequestProcessor nextProcessor;

   public LeaderRequestProcessor(LeaderZooKeeperServer zks, RequestProcessor nextProcessor) {
      this.lzks = zks;
      this.nextProcessor = nextProcessor;
   }

   public void processRequest(Request request) throws RequestProcessor.RequestProcessorException {
      if (this.lzks.authWriteRequest(request)) {
         Request upgradeRequest = null;

         try {
            upgradeRequest = this.lzks.checkUpgradeSession(request);
         } catch (KeeperException var4) {
            if (request.getHdr() != null) {
               LOG.debug("Updating header");
               request.getHdr().setType(-1);
               request.setTxn(new ErrorTxn(var4.code().intValue()));
            }

            request.setException(var4);
            LOG.warn("Error creating upgrade request", var4);
         } catch (IOException ie) {
            LOG.error("Unexpected error in upgrade", ie);
         }

         if (upgradeRequest != null) {
            this.nextProcessor.processRequest(upgradeRequest);
         }

         this.nextProcessor.processRequest(request);
      }
   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.nextProcessor.shutdown();
   }
}
