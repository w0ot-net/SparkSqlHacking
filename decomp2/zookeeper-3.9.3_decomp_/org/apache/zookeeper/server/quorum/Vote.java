package org.apache.zookeeper.server.quorum;

public class Vote {
   private final int version;
   private final long id;
   private final long zxid;
   private final long electionEpoch;
   private final long peerEpoch;
   private final QuorumPeer.ServerState state;

   public Vote(long id, long zxid) {
      this.version = 0;
      this.id = id;
      this.zxid = zxid;
      this.electionEpoch = -1L;
      this.peerEpoch = -1L;
      this.state = QuorumPeer.ServerState.LOOKING;
   }

   public Vote(long id, long zxid, long peerEpoch) {
      this.version = 0;
      this.id = id;
      this.zxid = zxid;
      this.electionEpoch = -1L;
      this.peerEpoch = peerEpoch;
      this.state = QuorumPeer.ServerState.LOOKING;
   }

   public Vote(long id, long zxid, long electionEpoch, long peerEpoch) {
      this.version = 0;
      this.id = id;
      this.zxid = zxid;
      this.electionEpoch = electionEpoch;
      this.peerEpoch = peerEpoch;
      this.state = QuorumPeer.ServerState.LOOKING;
   }

   public Vote(int version, long id, long zxid, long electionEpoch, long peerEpoch, QuorumPeer.ServerState state) {
      this.version = version;
      this.id = id;
      this.zxid = zxid;
      this.electionEpoch = electionEpoch;
      this.state = state;
      this.peerEpoch = peerEpoch;
   }

   public Vote(long id, long zxid, long electionEpoch, long peerEpoch, QuorumPeer.ServerState state) {
      this.id = id;
      this.zxid = zxid;
      this.electionEpoch = electionEpoch;
      this.state = state;
      this.peerEpoch = peerEpoch;
      this.version = 0;
   }

   public int getVersion() {
      return this.version;
   }

   public long getId() {
      return this.id;
   }

   public long getZxid() {
      return this.zxid;
   }

   public long getElectionEpoch() {
      return this.electionEpoch;
   }

   public long getPeerEpoch() {
      return this.peerEpoch;
   }

   public QuorumPeer.ServerState getState() {
      return this.state;
   }

   public boolean equals(Object o) {
      if (!(o instanceof Vote)) {
         return false;
      } else {
         Vote other = (Vote)o;
         if (this.state != QuorumPeer.ServerState.LOOKING && other.state != QuorumPeer.ServerState.LOOKING) {
            if (this.version > 0 ^ other.version > 0) {
               return this.id == other.id;
            } else {
               return this.id == other.id && this.peerEpoch == other.peerEpoch;
            }
         } else {
            return this.id == other.id && this.zxid == other.zxid && this.electionEpoch == other.electionEpoch && this.peerEpoch == other.peerEpoch;
         }
      }
   }

   public int hashCode() {
      return (int)(this.id & this.zxid);
   }

   public String toString() {
      return "(" + this.id + ", " + Long.toHexString(this.zxid) + ", " + Long.toHexString(this.peerEpoch) + ")";
   }
}
