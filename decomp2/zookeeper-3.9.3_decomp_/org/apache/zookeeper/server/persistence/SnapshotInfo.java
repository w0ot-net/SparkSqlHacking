package org.apache.zookeeper.server.persistence;

public class SnapshotInfo {
   public long zxid;
   public long timestamp;

   SnapshotInfo(long zxid, long timestamp) {
      this.zxid = zxid;
      this.timestamp = timestamp;
   }
}
