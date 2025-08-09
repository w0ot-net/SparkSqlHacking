package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.zookeeper.server.ZooKeeperCriticalThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LearnerSender extends ZooKeeperCriticalThread {
   private static final Logger LOG = LoggerFactory.getLogger(LearnerSender.class);
   private final LinkedBlockingQueue queuedPackets = new LinkedBlockingQueue();
   private final QuorumPacket proposalOfDeath = new QuorumPacket();
   Learner learner;

   public LearnerSender(Learner learner) {
      super("LearnerSender:" + learner.zk.getServerId(), learner.zk.getZooKeeperServerListener());
      this.learner = learner;
   }

   public void run() {
      while(true) {
         try {
            QuorumPacket p = (QuorumPacket)this.queuedPackets.poll();
            if (p == null) {
               this.learner.bufferedOutput.flush();
               p = (QuorumPacket)this.queuedPackets.take();
            }

            if (p != this.proposalOfDeath) {
               this.learner.messageTracker.trackSent(p.getType());
               this.learner.leaderOs.writeRecord(p, "packet");
               continue;
            }
         } catch (IOException e) {
            this.handleException(this.getName(), e);
         } catch (InterruptedException e) {
            this.handleException(this.getName(), e);
         }

         LOG.info("LearnerSender exited");
         return;
      }
   }

   public void queuePacket(QuorumPacket pp) throws IOException {
      if (pp == null) {
         this.learner.bufferedOutput.flush();
      } else {
         this.queuedPackets.add(pp);
      }

   }

   public void shutdown() {
      LOG.info("Shutting down LearnerSender");
      this.queuedPackets.clear();
      this.queuedPackets.add(this.proposalOfDeath);
   }
}
