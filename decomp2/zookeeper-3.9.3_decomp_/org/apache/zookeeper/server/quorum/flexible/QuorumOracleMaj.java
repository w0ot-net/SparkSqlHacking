package org.apache.zookeeper.server.quorum.flexible;

import java.io.FileReader;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.FilenameUtils;
import org.apache.zookeeper.server.quorum.Leader;
import org.apache.zookeeper.server.quorum.LearnerHandler;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.SyncedLearnerTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuorumOracleMaj extends QuorumMaj {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumOracleMaj.class);
   private String oracle = null;
   private final AtomicBoolean needOracle = new AtomicBoolean(true);

   public QuorumOracleMaj(Map allMembers, String oraclePath) {
      super(allMembers);
      this.setOracle(oraclePath);
   }

   public QuorumOracleMaj(Properties props, String oraclePath) throws QuorumPeerConfig.ConfigException {
      super(props);
      this.setOracle(oraclePath);
   }

   private void setOracle(String path) {
      if (this.oracle == null) {
         this.oracle = path;
         LOG.info("Oracle is set to {}", path);
      } else {
         LOG.warn("Oracle is already set. Ignore:{}", path);
      }

   }

   public boolean updateNeedOracle(List forwardingFollowers) {
      this.needOracle.set(forwardingFollowers.isEmpty() && super.getVotingMembers().size() == 2);
      return this.needOracle.get();
   }

   public boolean askOracle() {
      FileReader fr = null;

      boolean var3;
      try {
         try {
            fr = new FileReader(FilenameUtils.getFullPath(this.oracle) + FilenameUtils.getName(this.oracle));
            int read = fr.read();
            LOG.debug("Oracle says:{}", (char)read);
            fr.close();
            var3 = (char)read == '1';
            return var3;
         } catch (Exception e) {
            e.printStackTrace();
            if (this.oracle == null) {
               LOG.error("Oracle is not set, return false");
            }
         }

         var3 = false;
      } finally {
         if (fr != null) {
            try {
               fr.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return var3;
   }

   public boolean getNeedOracle() {
      return this.needOracle.get();
   }

   public String getOraclePath() {
      return this.oracle;
   }

   public boolean overrideQuorumDecision(List forwardingFollowers) {
      return this.updateNeedOracle(forwardingFollowers) && this.askOracle();
   }

   public boolean revalidateOutstandingProp(Leader self, ArrayList outstandingProposal, long lastCommitted) {
      LOG.debug("Start Revalidation outstandingProposals");

      try {
         while(outstandingProposal.size() >= 1) {
            outstandingProposal.sort((o1, o2) -> (int)(o1.getZxid() - o2.getZxid()));
            int i = 0;

            while(i < outstandingProposal.size()) {
               Leader.Proposal p = (Leader.Proposal)outstandingProposal.get(i);
               if (p.getZxid() > lastCommitted) {
                  LOG.debug("Re-validate outstanding proposal: 0x{} size:{} lastCommitted:{}", new Object[]{Long.toHexString(p.getZxid()), outstandingProposal.size(), Long.toHexString(lastCommitted)});
                  if (!self.tryToCommit(p, p.getZxid(), (SocketAddress)null)) {
                     break;
                  }

                  lastCommitted = p.getZxid();
                  outstandingProposal.remove(p);
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }

      LOG.debug("Finish Revalidation outstandingProposals");
      return true;
   }

   public boolean revalidateVoteset(SyncedLearnerTracker voteSet, boolean timeout) {
      return voteSet != null && voteSet.hasAllQuorums() && timeout;
   }

   public boolean containsQuorum(Set ackSet) {
      if (this.oracle != null && this.getVotingMembers().size() <= 2) {
         if (!super.containsQuorum(ackSet)) {
            if (this.getNeedOracle()) {
               LOG.debug("We lose the quorum, but we do not have any valid followers Oracle:{}", this.askOracle());
               return this.askOracle();
            } else {
               return false;
            }
         } else {
            return true;
         }
      } else {
         return super.containsQuorum(ackSet);
      }
   }

   public boolean equals(Object o) {
      if (o != null && this.getClass() == o.getClass()) {
         QuorumOracleMaj qm = (QuorumOracleMaj)o;
         if (qm.getVersion() == super.getVersion()) {
            return true;
         } else if (super.getAllMembers().size() != qm.getAllMembers().size()) {
            return false;
         } else {
            for(QuorumPeer.QuorumServer qs : super.getAllMembers().values()) {
               QuorumPeer.QuorumServer qso = (QuorumPeer.QuorumServer)qm.getAllMembers().get(qs.id);
               if (qso == null || !qs.equals(qso)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      assert false : "hashCode not designed";

      return 43;
   }
}
