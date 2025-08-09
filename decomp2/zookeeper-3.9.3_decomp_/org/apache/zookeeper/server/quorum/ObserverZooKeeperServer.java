package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import org.apache.zookeeper.server.FinalRequestProcessor;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.SyncRequestProcessor;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverZooKeeperServer extends LearnerZooKeeperServer {
   private static final Logger LOG = LoggerFactory.getLogger(ObserverZooKeeperServer.class);
   private final boolean syncRequestProcessorEnabled;
   ConcurrentLinkedQueue pendingSyncs;

   ObserverZooKeeperServer(FileTxnSnapLog logFactory, QuorumPeer self, ZKDatabase zkDb) throws IOException {
      super(logFactory, self.tickTime, self.minSessionTimeout, self.maxSessionTimeout, self.clientPortListenBacklog, zkDb, self);
      this.syncRequestProcessorEnabled = this.self.getSyncEnabled();
      this.pendingSyncs = new ConcurrentLinkedQueue();
      LOG.info("syncEnabled ={}", this.syncRequestProcessorEnabled);
   }

   public Observer getObserver() {
      return this.self.observer;
   }

   public Learner getLearner() {
      return this.self.observer;
   }

   public void commitRequest(Request request) {
      if (this.syncRequestProcessorEnabled) {
         this.syncProcessor.processRequest(request);
      }

      this.commitProcessor.commit(request);
   }

   protected void setupRequestProcessors() {
      RequestProcessor finalProcessor = new FinalRequestProcessor(this);
      this.commitProcessor = new CommitProcessor(finalProcessor, Long.toString(this.getServerId()), true, this.getZooKeeperServerListener());
      this.commitProcessor.start();
      this.firstProcessor = new ObserverRequestProcessor(this, this.commitProcessor);
      ((ObserverRequestProcessor)this.firstProcessor).start();
      if (this.syncRequestProcessorEnabled) {
         this.syncProcessor = new SyncRequestProcessor(this, (RequestProcessor)null);
         this.syncProcessor.start();
      }

   }

   public synchronized void sync() {
      if (this.pendingSyncs.size() == 0) {
         LOG.warn("Not expecting a sync.");
      } else {
         Request r = (Request)this.pendingSyncs.remove();
         this.commitProcessor.commit(r);
      }
   }

   public String getState() {
      return "observer";
   }

   public void dumpMonitorValues(BiConsumer response) {
      super.dumpMonitorValues(response);
      response.accept("observer_master_id", this.getObserver().getLearnerMasterId());
   }
}
