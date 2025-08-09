package org.apache.hadoop.hive.metastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hive.common.util.BloomFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AggregateStatsCache {
   private static final Logger LOG = LoggerFactory.getLogger(AggregateStatsCache.class.getName());
   private static AggregateStatsCache self = null;
   private final ConcurrentHashMap cacheStore;
   private final int maxCacheNodes;
   private final AtomicInteger currentNodes = new AtomicInteger(0);
   private final float maxFull;
   private final float cleanUntil;
   private final long timeToLiveMs;
   private final long maxWriterWaitTime;
   private final long maxReaderWaitTime;
   private final int maxPartsPerCacheNode;
   private final float falsePositiveProbability;
   private final float maxVariance;
   private boolean isCleaning = false;
   private final AtomicLong cacheHits = new AtomicLong(0L);
   private final AtomicLong cacheMisses = new AtomicLong(0L);
   int numRemovedTTL = 0;
   int numRemovedLRU = 0;

   private AggregateStatsCache(int maxCacheNodes, int maxPartsPerCacheNode, long timeToLiveMs, float falsePositiveProbability, float maxVariance, long maxWriterWaitTime, long maxReaderWaitTime, float maxFull, float cleanUntil) {
      this.maxCacheNodes = maxCacheNodes;
      this.maxPartsPerCacheNode = maxPartsPerCacheNode;
      this.timeToLiveMs = timeToLiveMs;
      this.falsePositiveProbability = falsePositiveProbability;
      this.maxVariance = maxVariance;
      this.maxWriterWaitTime = maxWriterWaitTime;
      this.maxReaderWaitTime = maxReaderWaitTime;
      this.maxFull = maxFull;
      this.cleanUntil = cleanUntil;
      this.cacheStore = new ConcurrentHashMap();
   }

   public static synchronized AggregateStatsCache getInstance(Configuration conf) {
      if (self == null) {
         int maxCacheNodes = HiveConf.getIntVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_SIZE);
         int maxPartitionsPerCacheNode = HiveConf.getIntVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_PARTITIONS);
         long timeToLiveMs = HiveConf.getTimeVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_TTL, TimeUnit.SECONDS) * 1000L;
         float falsePositiveProbability = HiveConf.getFloatVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_FPP);
         float maxVariance = HiveConf.getFloatVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_VARIANCE);
         long maxWriterWaitTime = HiveConf.getTimeVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_WRITER_WAIT, TimeUnit.MILLISECONDS);
         long maxReaderWaitTime = HiveConf.getTimeVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_READER_WAIT, TimeUnit.MILLISECONDS);
         float maxFull = HiveConf.getFloatVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_FULL);
         float cleanUntil = HiveConf.getFloatVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_CLEAN_UNTIL);
         self = new AggregateStatsCache(maxCacheNodes, maxPartitionsPerCacheNode, timeToLiveMs, falsePositiveProbability, maxVariance, maxWriterWaitTime, maxReaderWaitTime, maxFull, cleanUntil);
      }

      return self;
   }

   public int getMaxCacheNodes() {
      return this.maxCacheNodes;
   }

   public int getCurrentNodes() {
      return this.currentNodes.intValue();
   }

   public float getFullPercent() {
      return (float)this.currentNodes.intValue() / (float)this.maxCacheNodes * 100.0F;
   }

   public int getMaxPartsPerCacheNode() {
      return this.maxPartsPerCacheNode;
   }

   public float getFalsePositiveProbability() {
      return this.falsePositiveProbability;
   }

   public Float getHitRatio() {
      return this.cacheHits.longValue() + this.cacheMisses.longValue() > 0L ? (float)this.cacheHits.longValue() / (float)(this.cacheHits.longValue() + this.cacheMisses.longValue()) : null;
   }

   public AggrColStats get(String dbName, String tblName, String colName, List partNames) {
      Key key = new Key(dbName, tblName, colName);
      AggrColStatsList candidateList = (AggrColStatsList)this.cacheStore.get(key);
      if (candidateList != null && candidateList.nodes.size() != 0) {
         AggrColStats match = null;
         boolean isLocked = false;

         try {
            isLocked = candidateList.readLock.tryLock(this.maxReaderWaitTime, TimeUnit.MILLISECONDS);
            if (isLocked) {
               match = this.findBestMatch(partNames, candidateList.nodes);
            }

            if (match != null) {
               candidateList.updateLastAccessTime();
               this.cacheHits.incrementAndGet();
               LOG.info("Returning aggregate stats from the cache; total hits: " + this.cacheHits.longValue() + ", total misses: " + this.cacheMisses.longValue() + ", hit ratio: " + this.getHitRatio());
            } else {
               this.cacheMisses.incrementAndGet();
            }
         } catch (InterruptedException e) {
            LOG.debug("Interrupted Exception ignored ", e);
         } finally {
            if (isLocked) {
               candidateList.readLock.unlock();
            }

         }

         return match;
      } else {
         LOG.debug("No aggregate stats cached for " + key.toString());
         return null;
      }
   }

   private AggrColStats findBestMatch(List partNames, List candidates) {
      Map<AggrColStats, MatchStats> candidateMatchStats = new HashMap();
      AggrColStats bestMatch = null;
      int bestMatchHits = 0;
      int numPartsRequested = partNames.size();

      for(AggrColStats candidate : candidates) {
         if (!((float)Math.abs((candidate.getNumPartsCached() - (long)numPartsRequested) / (long)numPartsRequested) > this.maxVariance) && !this.isExpired(candidate)) {
            candidateMatchStats.put(candidate, new MatchStats(0, 0));
         }
      }

      int maxMisses = (int)this.maxVariance * numPartsRequested;

      for(String partName : partNames) {
         Iterator<Map.Entry<AggrColStats, MatchStats>> iterator = candidateMatchStats.entrySet().iterator();

         while(iterator.hasNext()) {
            Map.Entry<AggrColStats, MatchStats> entry = (Map.Entry)iterator.next();
            AggrColStats candidate = (AggrColStats)entry.getKey();
            MatchStats matchStats = (MatchStats)entry.getValue();
            if (candidate.getBloomFilter().test(partName.getBytes())) {
               ++matchStats.hits;
            } else {
               ++matchStats.misses;
            }

            if (matchStats.misses > maxMisses) {
               iterator.remove();
            } else if (matchStats.hits > bestMatchHits) {
               bestMatch = candidate;
            }
         }
      }

      if (bestMatch != null) {
         bestMatch.updateLastAccessTime();
      }

      return bestMatch;
   }

   public void add(String dbName, String tblName, String colName, long numPartsCached, ColumnStatisticsObj colStats, BloomFilter bloomFilter) {
      if ((float)(this.getCurrentNodes() / this.maxCacheNodes) > this.maxFull) {
         this.spawnCleaner();
      }

      Key key = new Key(dbName, tblName, colName);
      AggrColStats node = new AggrColStats(numPartsCached, bloomFilter, colStats);
      AggrColStatsList newNodeList = new AggrColStatsList();
      newNodeList.nodes = new ArrayList();
      AggrColStatsList nodeList = (AggrColStatsList)this.cacheStore.putIfAbsent(key, newNodeList);
      if (nodeList == null) {
         nodeList = newNodeList;
      }

      boolean isLocked = false;

      try {
         isLocked = nodeList.writeLock.tryLock(this.maxWriterWaitTime, TimeUnit.MILLISECONDS);
         if (isLocked) {
            nodeList.nodes.add(node);
            node.updateLastAccessTime();
            nodeList.updateLastAccessTime();
            this.currentNodes.getAndIncrement();
         }
      } catch (InterruptedException e) {
         LOG.debug("Interrupted Exception ignored ", e);
      } finally {
         if (isLocked) {
            nodeList.writeLock.unlock();
         }

      }

   }

   private void spawnCleaner() {
      synchronized(this) {
         if (this.isCleaning) {
            return;
         }

         this.isCleaning = true;
      }

      Thread cleaner = new Thread("AggregateStatsCache-CleanerThread") {
         public void run() {
            AggregateStatsCache.this.numRemovedTTL = 0;
            AggregateStatsCache.this.numRemovedLRU = 0;
            long cleanerStartTime = System.currentTimeMillis();
            AggregateStatsCache.LOG.info("AggregateStatsCache is " + AggregateStatsCache.this.getFullPercent() + "% full, with " + AggregateStatsCache.this.getCurrentNodes() + " nodes; starting cleaner thread");

            try {
               Iterator<Map.Entry<Key, AggrColStatsList>> mapIterator = AggregateStatsCache.this.cacheStore.entrySet().iterator();

               while(mapIterator.hasNext()) {
                  Map.Entry<Key, AggrColStatsList> pair = (Map.Entry)mapIterator.next();
                  AggrColStatsList candidateList = (AggrColStatsList)pair.getValue();
                  List<AggrColStats> nodes = candidateList.nodes;
                  if (nodes.size() == 0) {
                     mapIterator.remove();
                  } else {
                     boolean isLocked = false;

                     try {
                        isLocked = candidateList.writeLock.tryLock(AggregateStatsCache.this.maxWriterWaitTime, TimeUnit.MILLISECONDS);
                        if (isLocked) {
                           Iterator<AggrColStats> listIterator = nodes.iterator();

                           while(listIterator.hasNext()) {
                              AggrColStats node = (AggrColStats)listIterator.next();
                              if (AggregateStatsCache.this.isExpired(node)) {
                                 listIterator.remove();
                                 ++AggregateStatsCache.this.numRemovedTTL;
                                 AggregateStatsCache.this.currentNodes.getAndDecrement();
                              }
                           }
                        }
                     } catch (InterruptedException e) {
                        AggregateStatsCache.LOG.debug("Interrupted Exception ignored ", e);
                     } finally {
                        if (isLocked) {
                           candidateList.writeLock.unlock();
                        }

                     }

                     Thread.yield();
                  }
               }

               while((float)(AggregateStatsCache.this.getCurrentNodes() / AggregateStatsCache.this.maxCacheNodes) > AggregateStatsCache.this.cleanUntil) {
                  AggregateStatsCache.this.evictOneNode();
               }
            } finally {
               AggregateStatsCache.this.isCleaning = false;
               AggregateStatsCache.LOG.info("Stopping cleaner thread; AggregateStatsCache is now " + AggregateStatsCache.this.getFullPercent() + "% full, with " + AggregateStatsCache.this.getCurrentNodes() + " nodes");
               AggregateStatsCache.LOG.info("Number of expired nodes removed: " + AggregateStatsCache.this.numRemovedTTL);
               AggregateStatsCache.LOG.info("Number of LRU nodes removed: " + AggregateStatsCache.this.numRemovedLRU);
               AggregateStatsCache.LOG.info("Cleaner ran for: " + (System.currentTimeMillis() - cleanerStartTime) + "ms");
            }

         }
      };
      cleaner.setPriority(1);
      cleaner.setDaemon(true);
      cleaner.start();
   }

   private void evictOneNode() {
      Key lruKey = null;
      AggrColStatsList lruValue = null;

      for(Map.Entry entry : this.cacheStore.entrySet()) {
         Key key = (Key)entry.getKey();
         AggrColStatsList value = (AggrColStatsList)entry.getValue();
         if (lruKey == null) {
            lruKey = key;
            lruValue = value;
         } else if (value.lastAccessTime < lruValue.lastAccessTime && !value.nodes.isEmpty()) {
            lruKey = key;
            lruValue = value;
         }
      }

      AggrColStatsList candidateList = (AggrColStatsList)this.cacheStore.get(lruKey);
      boolean isLocked = false;

      try {
         isLocked = candidateList.writeLock.tryLock(this.maxWriterWaitTime, TimeUnit.MILLISECONDS);
         if (isLocked) {
            AggrColStats lruNode = null;
            int currentIndex = 0;
            int deleteIndex = 0;
            Iterator<AggrColStats> iterator = candidateList.nodes.iterator();

            while(iterator.hasNext()) {
               AggrColStats candidate = (AggrColStats)iterator.next();
               if (this.isExpired(candidate)) {
                  iterator.remove();
                  this.currentNodes.getAndDecrement();
                  ++this.numRemovedTTL;
                  return;
               }

               if (lruNode == null) {
                  lruNode = candidate;
                  ++currentIndex;
               } else if (lruNode != null && candidate.lastAccessTime < lruNode.lastAccessTime) {
                  lruNode = candidate;
                  deleteIndex = currentIndex;
               }
            }

            candidateList.nodes.remove(deleteIndex);
            this.currentNodes.getAndDecrement();
            ++this.numRemovedLRU;
         }
      } catch (InterruptedException e) {
         LOG.debug("Interrupted Exception ignored ", e);
      } finally {
         if (isLocked) {
            candidateList.writeLock.unlock();
         }

      }
   }

   private boolean isExpired(AggrColStats aggrColStats) {
      return System.currentTimeMillis() - aggrColStats.lastAccessTime > this.timeToLiveMs;
   }

   static class Key {
      private final String dbName;
      private final String tblName;
      private final String colName;

      Key(String db, String table, String col) {
         if (db != null && table != null && col != null) {
            this.dbName = db;
            this.tblName = table;
            this.colName = col;
         } else {
            throw new IllegalArgumentException("dbName, tblName, colName can't be null");
         }
      }

      public boolean equals(Object other) {
         if (other != null && other instanceof Key) {
            Key that = (Key)other;
            return this.dbName.equals(that.dbName) && this.tblName.equals(that.tblName) && this.colName.equals(that.colName);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.dbName.hashCode() * 31 + this.tblName.hashCode() * 31 + this.colName.hashCode();
      }

      public String toString() {
         return "database:" + this.dbName + ", table:" + this.tblName + ", column:" + this.colName;
      }
   }

   static class AggrColStatsList {
      private List nodes = new ArrayList();
      private final ReadWriteLock lock = new ReentrantReadWriteLock();
      private final Lock readLock;
      private final Lock writeLock;
      private volatile long lastAccessTime;

      AggrColStatsList() {
         this.readLock = this.lock.readLock();
         this.writeLock = this.lock.writeLock();
         this.lastAccessTime = 0L;
      }

      List getNodes() {
         return this.nodes;
      }

      void updateLastAccessTime() {
         this.lastAccessTime = System.currentTimeMillis();
      }
   }

   public static class AggrColStats {
      private final long numPartsCached;
      private final BloomFilter bloomFilter;
      private final ColumnStatisticsObj colStats;
      private volatile long lastAccessTime;

      public AggrColStats(long numPartsCached, BloomFilter bloomFilter, ColumnStatisticsObj colStats) {
         this.numPartsCached = numPartsCached;
         this.bloomFilter = bloomFilter;
         this.colStats = colStats;
         this.lastAccessTime = System.currentTimeMillis();
      }

      public long getNumPartsCached() {
         return this.numPartsCached;
      }

      public ColumnStatisticsObj getColStats() {
         return this.colStats;
      }

      public BloomFilter getBloomFilter() {
         return this.bloomFilter;
      }

      void updateLastAccessTime() {
         this.lastAccessTime = System.currentTimeMillis();
      }
   }

   private static class MatchStats {
      private int hits = 0;
      private int misses = 0;

      MatchStats(int hits, int misses) {
         this.hits = hits;
         this.misses = misses;
      }
   }
}
