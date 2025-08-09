package org.apache.hadoop.hive.metastore;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.LockComponent;
import org.apache.hadoop.hive.metastore.api.LockRequest;
import org.apache.hadoop.hive.metastore.api.LockType;

public class LockRequestBuilder {
   private LockRequest req;
   private LockTrie trie;
   private boolean userSet;

   /** @deprecated */
   public LockRequestBuilder() {
      this((String)null);
   }

   public LockRequestBuilder(String agentInfo) {
      this.req = new LockRequest();
      this.trie = new LockTrie();
      this.userSet = false;
      if (agentInfo != null) {
         this.req.setAgentInfo(agentInfo);
      }

   }

   public LockRequest build() {
      if (!this.userSet) {
         throw new RuntimeException("Cannot build a lock without giving a user");
      } else {
         this.trie.addLocksToRequest(this.req);

         try {
            this.req.setHostname(InetAddress.getLocalHost().getHostName());
         } catch (UnknownHostException var2) {
            throw new RuntimeException("Unable to determine our local host!");
         }

         return this.req;
      }
   }

   public LockRequestBuilder setTransactionId(long txnid) {
      this.req.setTxnid(txnid);
      return this;
   }

   public LockRequestBuilder setUser(String user) {
      if (user == null) {
         user = "unknown";
      }

      this.req.setUser(user);
      this.userSet = true;
      return this;
   }

   public LockRequestBuilder addLockComponent(LockComponent component) {
      this.trie.add(component);
      return this;
   }

   private static class LockTrie {
      Map trie = new LinkedHashMap();

      LockTrie() {
      }

      public void add(LockComponent comp) {
         TableTrie tabs = (TableTrie)this.trie.get(comp.getDbname());
         if (tabs == null) {
            tabs = new TableTrie();
            this.trie.put(comp.getDbname(), tabs);
         }

         this.setTable(comp, tabs);
      }

      public void addLocksToRequest(LockRequest request) {
         for(TableTrie tab : this.trie.values()) {
            for(PartTrie part : tab.values()) {
               for(LockComponent lock : part.values()) {
                  request.addToComponent(lock);
               }
            }
         }

      }

      private void setTable(LockComponent comp, TableTrie tabs) {
         PartTrie parts = (PartTrie)tabs.get(comp.getTablename());
         if (parts == null) {
            parts = new PartTrie();
            tabs.put(comp.getTablename(), parts);
         }

         this.setPart(comp, parts);
      }

      private void setPart(LockComponent comp, PartTrie parts) {
         LockComponent existing = (LockComponent)parts.get(comp.getPartitionname());
         if (existing == null) {
            parts.put(comp.getPartitionname(), comp);
         } else if (existing.getType() != LockType.EXCLUSIVE && (comp.getType() == LockType.EXCLUSIVE || comp.getType() == LockType.SHARED_WRITE)) {
            parts.put(comp.getPartitionname(), comp);
         }

      }

      private static class TableTrie extends LinkedHashMap {
         private TableTrie() {
         }
      }

      private static class PartTrie extends LinkedHashMap {
         private PartTrie() {
         }
      }
   }
}
