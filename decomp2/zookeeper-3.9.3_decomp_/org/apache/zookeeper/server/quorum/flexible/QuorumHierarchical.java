package org.apache.zookeeper.server.quorum.flexible;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuorumHierarchical implements QuorumVerifier {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumHierarchical.class);
   private HashMap serverWeight = new HashMap();
   private HashMap serverGroup = new HashMap();
   private HashMap groupWeight = new HashMap();
   private int numGroups = 0;
   private Map allMembers = new HashMap();
   private Map participatingMembers = new HashMap();
   private Map observingMembers = new HashMap();
   private long version = 0L;

   public int hashCode() {
      assert false : "hashCode not designed";

      return 42;
   }

   public boolean equals(Object o) {
      if (!(o instanceof QuorumHierarchical)) {
         return false;
      } else {
         QuorumHierarchical qm = (QuorumHierarchical)o;
         if (qm.getVersion() == this.version) {
            return true;
         } else if (this.allMembers.size() == qm.getAllMembers().size() && this.serverWeight.size() == qm.serverWeight.size() && this.groupWeight.size() == qm.groupWeight.size() && this.serverGroup.size() == qm.serverGroup.size()) {
            for(QuorumPeer.QuorumServer qs : this.allMembers.values()) {
               QuorumPeer.QuorumServer qso = (QuorumPeer.QuorumServer)qm.getAllMembers().get(qs.id);
               if (qso == null || !qs.equals(qso)) {
                  return false;
               }
            }

            for(Map.Entry entry : this.serverWeight.entrySet()) {
               if (!((Long)entry.getValue()).equals(qm.serverWeight.get(entry.getKey()))) {
                  return false;
               }
            }

            for(Map.Entry entry : this.groupWeight.entrySet()) {
               if (!((Long)entry.getValue()).equals(qm.groupWeight.get(entry.getKey()))) {
                  return false;
               }
            }

            for(Map.Entry entry : this.serverGroup.entrySet()) {
               if (!((Long)entry.getValue()).equals(qm.serverGroup.get(entry.getKey()))) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public QuorumHierarchical(String filename) throws QuorumPeerConfig.ConfigException {
      this.readConfigFile(filename);
   }

   public QuorumHierarchical(Properties qp) throws QuorumPeerConfig.ConfigException {
      this.parse(qp);
      LOG.info("{}, {}, {}", new Object[]{this.serverWeight.size(), this.serverGroup.size(), this.groupWeight.size()});
   }

   public long getWeight(long id) {
      return (Long)this.serverWeight.get(id);
   }

   private void readConfigFile(String filename) throws QuorumPeerConfig.ConfigException {
      File configFile = new File(filename);
      LOG.info("Reading configuration from: {}", configFile);

      try {
         if (!configFile.exists()) {
            throw new IllegalArgumentException(configFile.toString() + " file is missing");
         } else {
            Properties cfg = new Properties();
            FileInputStream in = new FileInputStream(configFile);

            try {
               cfg.load(in);
            } finally {
               in.close();
            }

            this.parse(cfg);
         }
      } catch (IOException e) {
         throw new QuorumPeerConfig.ConfigException("Error processing " + filename, e);
      } catch (IllegalArgumentException e) {
         throw new QuorumPeerConfig.ConfigException("Error processing " + filename, e);
      }
   }

   private void parse(Properties quorumProp) throws QuorumPeerConfig.ConfigException {
      for(Map.Entry entry : quorumProp.entrySet()) {
         String key = entry.getKey().toString();
         String value = entry.getValue().toString();
         if (key.startsWith("server.")) {
            int dot = key.indexOf(46);
            long sid = Long.parseLong(key.substring(dot + 1));
            QuorumPeer.QuorumServer qs = new QuorumPeer.QuorumServer(sid, value);
            this.allMembers.put(sid, qs);
            if (qs.type == QuorumPeer.LearnerType.PARTICIPANT) {
               this.participatingMembers.put(sid, qs);
            } else {
               this.observingMembers.put(sid, qs);
            }
         } else if (key.startsWith("group")) {
            int dot = key.indexOf(46);
            long gid = Long.parseLong(key.substring(dot + 1));
            ++this.numGroups;
            String[] parts = value.split(":");

            for(String s : parts) {
               long sid = Long.parseLong(s);
               if (this.serverGroup.containsKey(sid)) {
                  throw new QuorumPeerConfig.ConfigException("Server " + sid + "is in multiple groups");
               }

               this.serverGroup.put(sid, gid);
            }
         } else if (key.startsWith("weight")) {
            int dot = key.indexOf(46);
            long sid = Long.parseLong(key.substring(dot + 1));
            this.serverWeight.put(sid, Long.parseLong(value));
         } else if (key.equals("version")) {
            this.version = Long.parseLong(value, 16);
         }
      }

      for(QuorumPeer.QuorumServer qs : this.allMembers.values()) {
         Long id = qs.id;
         if (qs.type == QuorumPeer.LearnerType.PARTICIPANT) {
            if (!this.serverGroup.containsKey(id)) {
               throw new QuorumPeerConfig.ConfigException("Server " + id + "is not in a group");
            }

            if (!this.serverWeight.containsKey(id)) {
               this.serverWeight.put(id, 1L);
            }
         }
      }

      this.computeGroupWeight();
   }

   public Map getAllMembers() {
      return this.allMembers;
   }

   public String toString() {
      StringWriter sw = new StringWriter();

      for(QuorumPeer.QuorumServer member : this.getAllMembers().values()) {
         String key = "server." + member.id;
         String value = member.toString();
         sw.append(key);
         sw.append('=');
         sw.append(value);
         sw.append('\n');
      }

      Map<Long, String> groups = new HashMap();

      for(Map.Entry pair : this.serverGroup.entrySet()) {
         Long sid = (Long)pair.getKey();
         Long gid = (Long)pair.getValue();
         String str = (String)groups.get(gid);
         if (str == null) {
            str = sid.toString();
         } else {
            str = str.concat(":").concat(sid.toString());
         }

         groups.put(gid, str);
      }

      for(Map.Entry pair : groups.entrySet()) {
         Long gid = (Long)pair.getKey();
         String key = "group." + gid.toString();
         String value = (String)pair.getValue();
         sw.append(key);
         sw.append('=');
         sw.append(value);
         sw.append('\n');
      }

      for(Map.Entry pair : this.serverWeight.entrySet()) {
         Long sid = (Long)pair.getKey();
         String key = "weight." + sid.toString();
         String value = ((Long)pair.getValue()).toString();
         sw.append(key);
         sw.append('=');
         sw.append(value);
         sw.append('\n');
      }

      sw.append("version=" + Long.toHexString(this.version));
      return sw.toString();
   }

   private void computeGroupWeight() {
      for(Map.Entry entry : this.serverGroup.entrySet()) {
         Long sid = (Long)entry.getKey();
         Long gid = (Long)entry.getValue();
         if (!this.groupWeight.containsKey(gid)) {
            this.groupWeight.put(gid, (Long)this.serverWeight.get(sid));
         } else {
            long totalWeight = (Long)this.serverWeight.get(sid) + (Long)this.groupWeight.get(gid);
            this.groupWeight.put(gid, totalWeight);
         }
      }

      for(long weight : this.groupWeight.values()) {
         LOG.debug("Group weight: {}", weight);
         if (weight == 0L) {
            --this.numGroups;
            LOG.debug("One zero-weight group: 1, {}", this.numGroups);
         }
      }

   }

   public boolean containsQuorum(Set set) {
      HashMap<Long, Long> expansion = new HashMap();
      LOG.debug("Set size: {}", set.size());
      if (set.size() == 0) {
         return false;
      } else {
         for(long sid : set) {
            Long gid = (Long)this.serverGroup.get(sid);
            if (gid != null) {
               if (!expansion.containsKey(gid)) {
                  expansion.put(gid, (Long)this.serverWeight.get(sid));
               } else {
                  long totalWeight = (Long)this.serverWeight.get(sid) + (Long)expansion.get(gid);
                  expansion.put(gid, totalWeight);
               }
            }
         }

         int majGroupCounter = 0;

         for(Map.Entry entry : expansion.entrySet()) {
            Long gid = (Long)entry.getKey();
            LOG.debug("Group info: {}, {}, {}", new Object[]{entry.getValue(), gid, this.groupWeight.get(gid)});
            if ((Long)entry.getValue() > (Long)this.groupWeight.get(gid) / 2L) {
               ++majGroupCounter;
            }
         }

         LOG.debug("Majority group counter: {}, {}", majGroupCounter, this.numGroups);
         if (majGroupCounter > this.numGroups / 2) {
            LOG.debug("Positive set size: {}", set.size());
            return true;
         } else {
            LOG.debug("Negative set size: {}", set.size());
            return false;
         }
      }
   }

   public Map getVotingMembers() {
      return this.participatingMembers;
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
