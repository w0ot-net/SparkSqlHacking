package org.apache.spark.network.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;
import org.apache.spark.network.shuffledb.StoreVersion;
import org.rocksdb.BlockBasedTableConfig;
import org.rocksdb.BloomFilter;
import org.rocksdb.CompressionType;
import org.rocksdb.InfoLogLevel;
import org.rocksdb.Logger;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.Status.Code;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class RocksDBProvider {
   private static final SparkLogger logger;

   public static RocksDB initRockDB(File dbFile, StoreVersion version, ObjectMapper mapper) throws IOException {
      RocksDB tmpDb = null;
      if (dbFile != null) {
         BloomFilter fullFilter = new BloomFilter((double)10.0F, false);
         BlockBasedTableConfig tableFormatConfig = (new BlockBasedTableConfig()).setFilterPolicy(fullFilter).setEnableIndexCompression(false).setIndexBlockRestartInterval(8).setFormatVersion(5);
         Options dbOptions = new Options();
         RocksDBLogger rocksDBLogger = new RocksDBLogger(dbOptions);
         dbOptions.setCreateIfMissing(false);
         dbOptions.setBottommostCompressionType(CompressionType.ZSTD_COMPRESSION);
         dbOptions.setCompressionType(CompressionType.LZ4_COMPRESSION);
         dbOptions.setTableFormatConfig(tableFormatConfig);
         dbOptions.setLogger(rocksDBLogger);

         try {
            tmpDb = RocksDB.open(dbOptions, dbFile.toString());
         } catch (RocksDBException e) {
            if (e.getStatus().getCode() == Code.NotFound) {
               logger.info("Creating state database at {}", new MDC[]{MDC.of(.MODULE$, dbFile)});
               dbOptions.setCreateIfMissing(true);

               try {
                  tmpDb = RocksDB.open(dbOptions, dbFile.toString());
               } catch (RocksDBException dbExc) {
                  throw new IOException("Unable to create state store", dbExc);
               }
            } else {
               logger.error("error opening rocksdb file {}. Creating new file, will not be able to recover state for existing applications", e, new MDC[]{MDC.of(.MODULE$, dbFile)});
               if (dbFile.isDirectory()) {
                  for(File f : (File[])Objects.requireNonNull(dbFile.listFiles())) {
                     if (!f.delete()) {
                        logger.warn("error deleting {}", new MDC[]{MDC.of(.MODULE$, f.getPath())});
                     }
                  }
               }

               if (!dbFile.delete()) {
                  logger.warn("error deleting {}", new MDC[]{MDC.of(.MODULE$, dbFile.getPath())});
               }

               dbOptions.setCreateIfMissing(true);

               try {
                  tmpDb = RocksDB.open(dbOptions, dbFile.toString());
               } catch (RocksDBException dbExc) {
                  throw new IOException("Unable to create state store", dbExc);
               }
            }
         }

         try {
            checkVersion(tmpDb, version, mapper);
         } catch (RocksDBException e) {
            tmpDb.close();
            throw new IOException(e.getMessage(), e);
         } catch (IOException ioe) {
            tmpDb.close();
            throw ioe;
         }
      }

      return tmpDb;
   }

   @VisibleForTesting
   static RocksDB initRocksDB(File file) throws IOException {
      BloomFilter fullFilter = new BloomFilter((double)10.0F, false);
      BlockBasedTableConfig tableFormatConfig = (new BlockBasedTableConfig()).setFilterPolicy(fullFilter).setEnableIndexCompression(false).setIndexBlockRestartInterval(8).setFormatVersion(5);
      Options dbOptions = new Options();
      dbOptions.setCreateIfMissing(true);
      dbOptions.setBottommostCompressionType(CompressionType.ZSTD_COMPRESSION);
      dbOptions.setCompressionType(CompressionType.LZ4_COMPRESSION);
      dbOptions.setTableFormatConfig(tableFormatConfig);

      try {
         return RocksDB.open(dbOptions, file.toString());
      } catch (RocksDBException e) {
         throw new IOException("Unable to open state store", e);
      }
   }

   public static void checkVersion(RocksDB db, StoreVersion newversion, ObjectMapper mapper) throws IOException, RocksDBException {
      byte[] bytes = db.get(StoreVersion.KEY);
      if (bytes == null) {
         storeVersion(db, newversion, mapper);
      } else {
         StoreVersion version = (StoreVersion)mapper.readValue(bytes, StoreVersion.class);
         if (version.major != newversion.major) {
            String var10002 = String.valueOf(version);
            throw new IOException("cannot read state DB with version " + var10002 + ", incompatible with current version " + String.valueOf(newversion));
         }

         storeVersion(db, newversion, mapper);
      }

   }

   public static void storeVersion(RocksDB db, StoreVersion version, ObjectMapper mapper) throws IOException, RocksDBException {
      db.put(StoreVersion.KEY, mapper.writeValueAsBytes(version));
   }

   static {
      RocksDB.loadLibrary();
      logger = SparkLoggerFactory.getLogger(RocksDBProvider.class);
   }

   private static class RocksDBLogger extends Logger {
      private static final SparkLogger LOG = SparkLoggerFactory.getLogger(RocksDBLogger.class);

      RocksDBLogger(Options options) {
         super(options.infoLogLevel());
      }

      protected void log(InfoLogLevel infoLogLevel, String message) {
         if (infoLogLevel == InfoLogLevel.INFO_LEVEL) {
            LOG.info(message);
         }

      }
   }
}
