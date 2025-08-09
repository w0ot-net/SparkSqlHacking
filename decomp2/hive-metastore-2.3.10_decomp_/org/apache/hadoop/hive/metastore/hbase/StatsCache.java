package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.HiveStatsUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.hbase.stats.ColumnStatsAggregator;
import org.apache.hadoop.hive.metastore.hbase.stats.ColumnStatsAggregatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StatsCache {
   private static final Logger LOG = LoggerFactory.getLogger(StatsCache.class.getName());
   private static StatsCache self = null;
   private LoadingCache cache;
   private Invalidator invalidator;
   private long runInvalidatorEvery;
   private long maxTimeInCache;
   private boolean invalidatorHasRun;
   @VisibleForTesting
   Counter misses;
   @VisibleForTesting
   Counter hbaseHits;
   @VisibleForTesting
   Counter totalGets;

   static synchronized StatsCache getInstance(Configuration conf) {
      if (self == null) {
         self = new StatsCache(conf);
      }

      return self;
   }

   private StatsCache(final Configuration conf) {
      this.cache = CacheBuilder.newBuilder().maximumSize((long)HiveConf.getIntVar(conf, ConfVars.METASTORE_HBASE_AGGR_STATS_CACHE_ENTRIES)).expireAfterWrite(HiveConf.getTimeVar(conf, ConfVars.METASTORE_HBASE_AGGR_STATS_MEMORY_TTL, TimeUnit.SECONDS), TimeUnit.SECONDS).build(new CacheLoader() {
         public AggrStats load(StatsCacheKey key) throws Exception {
            int numBitVectors = HiveStatsUtils.getNumBitVectorsForNDVEstimation(conf);
            boolean useDensityFunctionForNDVEstimation = HiveConf.getBoolVar(conf, ConfVars.HIVE_METASTORE_STATS_NDV_DENSITY_FUNCTION);
            HBaseReadWrite hrw = HBaseReadWrite.getInstance();
            AggrStats aggrStats = hrw.getAggregatedStats(key.hashed);
            if (aggrStats == null) {
               StatsCache.this.misses.incr();
               ColumnStatsAggregator aggregator = null;
               aggrStats = new AggrStats();
               StatsCache.LOG.debug("Unable to find aggregated stats for " + key.colName + ", aggregating");
               List<ColumnStatistics> css = hrw.getPartitionStatistics(key.dbName, key.tableName, key.partNames, HBaseStore.partNameListToValsList(key.partNames), Collections.singletonList(key.colName));
               if (css != null && css.size() > 0) {
                  aggrStats.setPartsFound((long)css.size());
                  if (aggregator == null) {
                     aggregator = ColumnStatsAggregatorFactory.getColumnStatsAggregator((ColumnStatisticsData._Fields)((ColumnStatisticsObj)((ColumnStatistics)css.iterator().next()).getStatsObj().iterator().next()).getStatsData().getSetField(), numBitVectors, useDensityFunctionForNDVEstimation);
                  }

                  ColumnStatisticsObj statsObj = aggregator.aggregate(key.colName, key.partNames, css);
                  aggrStats.addToColStats(statsObj);
                  StatsCache.this.put(key, aggrStats);
               }
            } else {
               StatsCache.this.hbaseHits.incr();
            }

            return aggrStats;
         }
      });
      this.misses = new Counter("Stats cache table misses");
      this.hbaseHits = new Counter("Stats cache table hits");
      this.totalGets = new Counter("Total get calls to the stats cache");
      this.maxTimeInCache = HiveConf.getTimeVar(conf, ConfVars.METASTORE_HBASE_AGGR_STATS_HBASE_TTL, TimeUnit.SECONDS);
      this.runInvalidatorEvery = HiveConf.getTimeVar(conf, ConfVars.METASTORE_HBASE_AGGR_STATS_INVALIDATOR_FREQUENCY, TimeUnit.MILLISECONDS);
      this.invalidator = new Invalidator();
      this.invalidator.setDaemon(true);
      this.invalidator.start();
   }

   void put(StatsCacheKey key, AggrStats aggrStats) throws IOException {
      HBaseReadWrite.getInstance().putAggregatedStats(key.hashed, key.dbName, key.tableName, key.partNames, key.colName, aggrStats);
      this.cache.put(key, aggrStats);
   }

   AggrStats get(String dbName, String tableName, List partNames, String colName) throws IOException {
      this.totalGets.incr();
      StatsCacheKey key = new StatsCacheKey(dbName, tableName, partNames, colName);

      try {
         return (AggrStats)this.cache.get(key);
      } catch (ExecutionException e) {
         throw new IOException(e);
      }
   }

   void invalidate(String dbName, String tableName, String partName) throws IOException {
      this.invalidator.addToQueue(HbaseMetastoreProto.AggrStatsInvalidatorFilter.Entry.newBuilder().setDbName(ByteString.copyFrom(dbName.getBytes(HBaseUtils.ENCODING))).setTableName(ByteString.copyFrom(tableName.getBytes(HBaseUtils.ENCODING))).setPartName(ByteString.copyFrom(partName.getBytes(HBaseUtils.ENCODING))).build());
   }

   void dumpCounters() {
      LOG.debug(this.misses.dump());
      LOG.debug(this.hbaseHits.dump());
      LOG.debug(this.totalGets.dump());
   }

   @VisibleForTesting
   void flushMemory() throws IOException {
      this.cache.invalidateAll();
   }

   @VisibleForTesting
   void resetCounters() {
      this.misses.clear();
      this.hbaseHits.clear();
      this.totalGets.clear();
   }

   @VisibleForTesting
   void setRunInvalidatorEvery(long runEvery) {
      this.runInvalidatorEvery = runEvery;
   }

   @VisibleForTesting
   void setMaxTimeInCache(long maxTime) {
      this.maxTimeInCache = maxTime;
   }

   @VisibleForTesting
   void wakeInvalidator() throws InterruptedException {
      this.invalidatorHasRun = false;
      Thread.sleep(2L * this.runInvalidatorEvery);
      this.invalidator.interrupt();

      while(!this.invalidatorHasRun) {
         Thread.sleep(10L);
      }

   }

   static class StatsCacheKey {
      final byte[] hashed;
      String dbName;
      String tableName;
      List partNames;
      String colName;
      private MessageDigest md;

      StatsCacheKey(byte[] key) {
         this.hashed = key;
      }

      StatsCacheKey(String dbName, String tableName, List partNames, String colName) {
         this.dbName = dbName;
         this.tableName = tableName;
         this.partNames = partNames;
         this.colName = colName;

         try {
            this.md = MessageDigest.getInstance("MD5");
         } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
         }

         this.md.update(dbName.getBytes(HBaseUtils.ENCODING));
         this.md.update(tableName.getBytes(HBaseUtils.ENCODING));
         Collections.sort(this.partNames);

         for(String s : partNames) {
            this.md.update(s.getBytes(HBaseUtils.ENCODING));
         }

         this.md.update(colName.getBytes(HBaseUtils.ENCODING));
         this.hashed = this.md.digest();
      }

      public boolean equals(Object other) {
         if (other != null && other instanceof StatsCacheKey) {
            StatsCacheKey that = (StatsCacheKey)other;
            return Arrays.equals(this.hashed, that.hashed);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Arrays.hashCode(this.hashed);
      }
   }

   private class Invalidator extends Thread {
      private List entries;
      private Lock lock;

      private Invalidator() {
         this.entries = new ArrayList();
         this.lock = new ReentrantLock();
      }

      void addToQueue(HbaseMetastoreProto.AggrStatsInvalidatorFilter.Entry entry) {
         this.lock.lock();

         try {
            this.entries.add(entry);
         } finally {
            this.lock.unlock();
         }

      }

      public void run() {
         while(true) {
            long startedAt = System.currentTimeMillis();
            List<HbaseMetastoreProto.AggrStatsInvalidatorFilter.Entry> thisRun = null;
            this.lock.lock();

            try {
               if (this.entries.size() > 0) {
                  thisRun = this.entries;
                  this.entries = new ArrayList();
               }
            } finally {
               this.lock.unlock();
            }

            if (thisRun != null) {
               try {
                  HbaseMetastoreProto.AggrStatsInvalidatorFilter filter = HbaseMetastoreProto.AggrStatsInvalidatorFilter.newBuilder().setRunEvery(StatsCache.this.runInvalidatorEvery).setMaxCacheEntryLife(StatsCache.this.maxTimeInCache).addAllToInvalidate(thisRun).build();
                  List<StatsCacheKey> keys = HBaseReadWrite.getInstance().invalidateAggregatedStats(filter);
                  StatsCache.this.cache.invalidateAll(keys);
               } catch (IOException e) {
                  StatsCache.LOG.error("Caught error while invalidating entries in the cache", e);
               }
            }

            StatsCache.this.invalidatorHasRun = true;

            try {
               sleep(StatsCache.this.runInvalidatorEvery - (System.currentTimeMillis() - startedAt));
            } catch (InterruptedException e) {
               StatsCache.LOG.warn("Interupted while sleeping", e);
            }
         }
      }
   }
}
