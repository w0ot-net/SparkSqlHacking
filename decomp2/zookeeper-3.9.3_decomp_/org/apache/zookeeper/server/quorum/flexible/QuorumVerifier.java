package org.apache.zookeeper.server.quorum.flexible;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.zookeeper.server.quorum.Leader;
import org.apache.zookeeper.server.quorum.LearnerHandler;
import org.apache.zookeeper.server.quorum.SyncedLearnerTracker;

public interface QuorumVerifier {
   long getWeight(long var1);

   boolean containsQuorum(Set var1);

   long getVersion();

   void setVersion(long var1);

   Map getAllMembers();

   Map getVotingMembers();

   Map getObservingMembers();

   boolean equals(Object var1);

   default boolean updateNeedOracle(List forwardingFollowers) {
      return false;
   }

   default boolean getNeedOracle() {
      return false;
   }

   default boolean askOracle() {
      return false;
   }

   default boolean overrideQuorumDecision(List forwardingFollowers) {
      return false;
   }

   default boolean revalidateOutstandingProp(Leader self, ArrayList outstandingProposal, long lastCommitted) {
      return false;
   }

   default boolean revalidateVoteset(SyncedLearnerTracker voteSet, boolean timeout) {
      return false;
   }

   default String getOraclePath() {
      return null;
   }

   String toString();
}
