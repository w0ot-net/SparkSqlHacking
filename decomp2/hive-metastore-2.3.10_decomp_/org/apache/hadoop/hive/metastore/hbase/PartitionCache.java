package org.apache.hadoop.hive.metastore.hbase;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.ObjectPair;
import org.apache.hadoop.hive.metastore.api.Partition;

class PartitionCache {
   private Map cache;
   private final int maxSize;
   private int cacheSize;
   private Counter misses;
   private Counter hits;
   private Counter overflows;

   PartitionCache(int max, Counter hits, Counter misses, Counter overflows) {
      this.maxSize = max;
      this.cache = new HashMap();
      this.cacheSize = 0;
      this.hits = hits;
      this.misses = misses;
      this.overflows = overflows;
   }

   void put(String dbName, String tableName, Partition part) {
      if (this.cacheSize < this.maxSize) {
         ObjectPair<String, String> key = new ObjectPair(dbName, tableName);
         TrieValue entry = (TrieValue)this.cache.get(key);
         if (entry == null) {
            entry = new TrieValue(false);
            this.cache.put(key, entry);
         }

         entry.map.put(part.getValues(), part);
         ++this.cacheSize;
      } else {
         this.overflows.incr();
      }

   }

   void put(String dbName, String tableName, List parts, boolean allForTable) {
      if (this.cacheSize + parts.size() < this.maxSize) {
         ObjectPair<String, String> key = new ObjectPair(dbName, tableName);
         TrieValue entry = (TrieValue)this.cache.get(key);
         if (entry == null) {
            entry = new TrieValue(allForTable);
            this.cache.put(key, entry);
         }

         for(Partition part : parts) {
            entry.map.put(part.getValues(), part);
         }

         this.cacheSize += parts.size();
      } else {
         this.overflows.incr();
      }

   }

   Collection getAllForTable(String dbName, String tableName) {
      TrieValue entry = (TrieValue)this.cache.get(new ObjectPair(dbName, tableName));
      if (entry != null && entry.hasAllPartitionsForTable) {
         this.hits.incr();
         return entry.map.values();
      } else {
         this.misses.incr();
         return null;
      }
   }

   Partition get(String dbName, String tableName, List partVals) {
      TrieValue entry = (TrieValue)this.cache.get(new ObjectPair(dbName, tableName));
      if (entry != null) {
         this.hits.incr();
         return (Partition)entry.map.get(partVals);
      } else {
         this.misses.incr();
         return null;
      }
   }

   void remove(String dbName, String tableName) {
      ObjectPair<String, String> key = new ObjectPair(dbName, tableName);
      TrieValue entry = (TrieValue)this.cache.get(key);
      if (entry != null) {
         this.cacheSize -= entry.map.size();
         this.cache.remove(key);
      }

   }

   void remove(String dbName, String tableName, List partVals) {
      ObjectPair<String, String> key = new ObjectPair(dbName, tableName);
      TrieValue entry = (TrieValue)this.cache.get(key);
      if (entry != null && entry.map.remove(partVals) != null) {
         --this.cacheSize;
         entry.hasAllPartitionsForTable = false;
      }

   }

   void flush() {
      this.cache.clear();
      this.cacheSize = 0;
   }

   static class TrieValue {
      boolean hasAllPartitionsForTable;
      Map map;

      TrieValue(boolean hasAll) {
         this.hasAllPartitionsForTable = hasAll;
         this.map = new HashMap();
      }
   }
}
