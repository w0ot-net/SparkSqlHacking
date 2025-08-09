package org.apache.zookeeper.server.quorum;

import java.util.ArrayList;
import java.util.HashSet;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;

public class SyncedLearnerTracker {
   protected ArrayList qvAcksetPairs = new ArrayList();

   public void addQuorumVerifier(QuorumVerifier qv) {
      this.qvAcksetPairs.add(new QuorumVerifierAcksetPair(qv, new HashSet(qv.getVotingMembers().size())));
   }

   public boolean addAck(Long sid) {
      boolean change = false;

      for(QuorumVerifierAcksetPair qvAckset : this.qvAcksetPairs) {
         if (qvAckset.getQuorumVerifier().getVotingMembers().containsKey(sid)) {
            qvAckset.getAckset().add(sid);
            change = true;
         }
      }

      return change;
   }

   public boolean hasSid(long sid) {
      for(QuorumVerifierAcksetPair qvAckset : this.qvAcksetPairs) {
         if (!qvAckset.getQuorumVerifier().getVotingMembers().containsKey(sid)) {
            return false;
         }
      }

      return true;
   }

   public boolean hasAllQuorums() {
      for(QuorumVerifierAcksetPair qvAckset : this.qvAcksetPairs) {
         if (!qvAckset.getQuorumVerifier().containsQuorum(qvAckset.getAckset())) {
            return false;
         }
      }

      return true;
   }

   public String ackSetsToString() {
      StringBuilder sb = new StringBuilder();

      for(QuorumVerifierAcksetPair qvAckset : this.qvAcksetPairs) {
         sb.append(qvAckset.getAckset().toString()).append(",");
      }

      return sb.substring(0, sb.length() - 1);
   }

   public static class QuorumVerifierAcksetPair {
      private final QuorumVerifier qv;
      private final HashSet ackset;

      public QuorumVerifierAcksetPair(QuorumVerifier qv, HashSet ackset) {
         this.qv = qv;
         this.ackset = ackset;
      }

      public QuorumVerifier getQuorumVerifier() {
         return this.qv;
      }

      public HashSet getAckset() {
         return this.ackset;
      }
   }
}
