package org.apache.zookeeper.server.quorum;

public class StateSummary {
   private long currentEpoch;
   private long lastZxid;

   public StateSummary(long currentEpoch, long lastZxid) {
      this.currentEpoch = currentEpoch;
      this.lastZxid = lastZxid;
   }

   public long getCurrentEpoch() {
      return this.currentEpoch;
   }

   public long getLastZxid() {
      return this.lastZxid;
   }

   public boolean isMoreRecentThan(StateSummary ss) {
      return this.currentEpoch > ss.currentEpoch || this.currentEpoch == ss.currentEpoch && this.lastZxid > ss.lastZxid;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof StateSummary)) {
         return false;
      } else {
         StateSummary ss = (StateSummary)obj;
         return this.currentEpoch == ss.currentEpoch && this.lastZxid == ss.lastZxid;
      }
   }

   public int hashCode() {
      return (int)(this.currentEpoch ^ this.lastZxid);
   }
}
