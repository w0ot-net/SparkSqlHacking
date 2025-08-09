package org.apache.zookeeper.server;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.server.util.AdHash;

public class NodeHashMapImpl implements NodeHashMap {
   private final ConcurrentHashMap nodes;
   private final boolean digestEnabled;
   private final DigestCalculator digestCalculator;
   private final AdHash hash;

   public NodeHashMapImpl(DigestCalculator digestCalculator) {
      this.digestCalculator = digestCalculator;
      this.nodes = new ConcurrentHashMap();
      this.hash = new AdHash();
      this.digestEnabled = ZooKeeperServer.isDigestEnabled();
   }

   public DataNode put(String path, DataNode node) {
      DataNode oldNode = (DataNode)this.nodes.put(path, node);
      this.addDigest(path, node);
      if (oldNode != null) {
         this.removeDigest(path, oldNode);
      }

      return oldNode;
   }

   public DataNode putWithoutDigest(String path, DataNode node) {
      return (DataNode)this.nodes.put(path, node);
   }

   public DataNode get(String path) {
      return (DataNode)this.nodes.get(path);
   }

   public DataNode remove(String path) {
      DataNode oldNode = (DataNode)this.nodes.remove(path);
      if (oldNode != null) {
         this.removeDigest(path, oldNode);
      }

      return oldNode;
   }

   public Set entrySet() {
      return this.nodes.entrySet();
   }

   public void clear() {
      this.nodes.clear();
      this.hash.clear();
   }

   public int size() {
      return this.nodes.size();
   }

   public void preChange(String path, DataNode node) {
      this.removeDigest(path, node);
   }

   public void postChange(String path, DataNode node) {
      node.digestCached = false;
      this.addDigest(path, node);
   }

   private void addDigest(String path, DataNode node) {
      if (!path.startsWith("/zookeeper/")) {
         if (this.digestEnabled) {
            this.hash.addDigest(this.digestCalculator.calculateDigest(path, node));
         }

      }
   }

   private void removeDigest(String path, DataNode node) {
      if (!path.startsWith("/zookeeper/")) {
         if (this.digestEnabled) {
            this.hash.removeDigest(this.digestCalculator.calculateDigest(path, node));
         }

      }
   }

   public long getDigest() {
      return this.hash.getHash();
   }
}
