package org.apache.zookeeper.server.quorum.flexible;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuorumMaj implements QuorumVerifier {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumMaj.class);
   private Map allMembers = new HashMap();
   private Map votingMembers = new HashMap();
   private Map observingMembers = new HashMap();
   private long version = 0L;
   protected int half;

   public int hashCode() {
      assert false : "hashCode not designed";

      return 42;
   }

   public boolean equals(Object o) {
      if (!(o instanceof QuorumMaj)) {
         return false;
      } else {
         QuorumMaj qm = (QuorumMaj)o;
         if (qm.getVersion() == this.version) {
            return true;
         } else if (this.allMembers.size() != qm.getAllMembers().size()) {
            return false;
         } else {
            for(QuorumPeer.QuorumServer qs : this.allMembers.values()) {
               QuorumPeer.QuorumServer qso = (QuorumPeer.QuorumServer)qm.getAllMembers().get(qs.id);
               if (qso == null || !qs.equals(qso)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public QuorumMaj(Map allMembers) {
      this.allMembers = allMembers;

      for(QuorumPeer.QuorumServer qs : allMembers.values()) {
         if (qs.type == QuorumPeer.LearnerType.PARTICIPANT) {
            this.votingMembers.put(qs.id, qs);
         } else {
            this.observingMembers.put(qs.id, qs);
         }
      }

      this.half = this.votingMembers.size() / 2;
   }

   public QuorumMaj(Properties props) throws QuorumPeerConfig.ConfigException {
      for(Map.Entry entry : props.entrySet()) {
         String key = entry.getKey().toString();
         String value = entry.getValue().toString();
         if (key.startsWith("server.")) {
            int dot = key.indexOf(46);
            long sid = Long.parseLong(key.substring(dot + 1));
            QuorumPeer.QuorumServer qs = new QuorumPeer.QuorumServer(sid, value);
            this.allMembers.put(sid, qs);
            if (qs.type == QuorumPeer.LearnerType.PARTICIPANT) {
               this.votingMembers.put(sid, qs);
            } else {
               this.observingMembers.put(sid, qs);
            }
         } else if (key.equals("version")) {
            this.version = Long.parseLong(value, 16);
         }
      }

      this.half = this.votingMembers.size() / 2;
   }

   public long getWeight(long id) {
      return 1L;
   }

   public String toString() {
      StringBuilder sw = new StringBuilder();

      for(QuorumPeer.QuorumServer member : this.getAllMembers().values()) {
         String key = "server." + member.id;
         String value = member.toString();
         sw.append(key);
         sw.append('=');
         sw.append(value);
         sw.append('\n');
      }

      String hexVersion = Long.toHexString(this.version);
      sw.append("version=");
      sw.append(hexVersion);
      return sw.toString();
   }

   public boolean containsQuorum(Set ackSet) {
      return ackSet.size() > this.half;
   }

   public Map getAllMembers() {
      return this.allMembers;
   }

   public Map getVotingMembers() {
      return this.votingMembers;
   }

   public Map getObservingMembers() {
      return this.observingMembers;
   }

   public long getVersion() {
      return this.version;
   }

   public void setVersion(long ver) {
      this.version = ver;
   }
}
