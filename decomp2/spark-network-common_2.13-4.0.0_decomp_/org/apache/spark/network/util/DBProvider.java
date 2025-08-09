package org.apache.spark.network.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.shuffledb.DB;
import org.apache.spark.network.shuffledb.DBBackend;
import org.apache.spark.network.shuffledb.LevelDB;
import org.apache.spark.network.shuffledb.StoreVersion;
import org.rocksdb.RocksDB;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class DBProvider {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(DBProvider.class);

   public static DB initDB(DBBackend dbBackend, File dbFile, StoreVersion version, ObjectMapper mapper) throws IOException {
      if (dbFile != null) {
         Object var10000;
         switch (dbBackend) {
            case LEVELDB:
               org.iq80.leveldb.DB levelDB = LevelDBProvider.initLevelDB(dbFile, version, mapper);
               logger.warn("The LEVELDB is deprecated. Please use ROCKSDB instead.");
               var10000 = levelDB != null ? new LevelDB(levelDB) : null;
               break;
            case ROCKSDB:
               RocksDB rocksDB = RocksDBProvider.initRockDB(dbFile, version, mapper);
               var10000 = rocksDB != null ? new org.apache.spark.network.shuffledb.RocksDB(rocksDB) : null;
               break;
            default:
               throw new IncompatibleClassChangeError();
         }

         return (DB)var10000;
      } else {
         return null;
      }
   }

   @VisibleForTesting
   public static DB initDB(DBBackend dbBackend, File file) throws IOException {
      if (file != null) {
         Object var10000;
         switch (dbBackend) {
            case LEVELDB:
               logger.warn("The LEVELDB is deprecated. Please use ROCKSDB instead.");
               var10000 = new LevelDB(LevelDBProvider.initLevelDB(file));
               break;
            case ROCKSDB:
               var10000 = new org.apache.spark.network.shuffledb.RocksDB(RocksDBProvider.initRocksDB(file));
               break;
            default:
               throw new IncompatibleClassChangeError();
         }

         return (DB)var10000;
      } else {
         return null;
      }
   }
}
