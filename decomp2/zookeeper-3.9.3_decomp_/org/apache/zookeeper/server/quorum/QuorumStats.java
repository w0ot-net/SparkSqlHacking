package org.apache.zookeeper.server.quorum;

public class QuorumStats {
   private final Provider provider;

   protected QuorumStats(Provider provider) {
      this.provider = provider;
   }

   public String getServerState() {
      return this.provider.getServerState();
   }

   public String[] getQuorumPeers() {
      return this.provider.getQuorumPeers();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(super.toString());
      String state = this.getServerState();
      if (state.equals("leading")) {
         sb.append("Followers:");

         for(String f : this.getQuorumPeers()) {
            sb.append(" ").append(f);
         }

         sb.append("\n");
      } else if (state.equals("following") || state.equals("observing")) {
         sb.append("Leader: ");
         String[] ldr = this.getQuorumPeers();
         if (ldr.length > 0) {
            sb.append(ldr[0]);
         } else {
            sb.append("not connected");
         }

         sb.append("\n");
      }

      return sb.toString();
   }

   public interface Provider {
      String UNKNOWN_STATE = "unknown";
      String LOOKING_STATE = "leaderelection";
      String LEADING_STATE = "leading";
      String FOLLOWING_STATE = "following";
      String OBSERVING_STATE = "observing";

      String[] getQuorumPeers();

      String getServerState();
   }
}
