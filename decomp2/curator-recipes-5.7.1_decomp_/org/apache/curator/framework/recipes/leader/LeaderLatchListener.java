package org.apache.curator.framework.recipes.leader;

public interface LeaderLatchListener {
   void isLeader();

   void notLeader();
}
