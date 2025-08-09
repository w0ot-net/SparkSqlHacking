package org.apache.spark.network.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;
import org.apache.spark.network.shuffledb.StoreVersion;
import org.fusesource.leveldbjni.JniDBFactory;
import org.fusesource.leveldbjni.internal.NativeDB;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Logger;
import org.iq80.leveldb.Options;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class LevelDBProvider {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(LevelDBProvider.class);

   public static DB initLevelDB(File dbFile, StoreVersion version, ObjectMapper mapper) throws IOException {
      DB tmpDb = null;
      if (dbFile != null) {
         Options options = new Options();
         options.createIfMissing(false);
         options.logger(new LevelDBLogger());

         try {
            tmpDb = JniDBFactory.factory.open(dbFile, options);
         } catch (NativeDB.DBException e) {
            if (!e.isNotFound() && !e.getMessage().contains(" does not exist ")) {
               logger.error("error opening leveldb file {}. Creating new file, will not be able to recover state for existing applications", e, new MDC[]{MDC.of(.MODULE$, dbFile)});
               if (dbFile.isDirectory()) {
                  for(File f : dbFile.listFiles()) {
                     if (!f.delete()) {
                        logger.warn("error deleting {}", new MDC[]{MDC.of(.MODULE$, f.getPath())});
                     }
                  }
               }

               if (!dbFile.delete()) {
                  logger.warn("error deleting {}", new MDC[]{MDC.of(.MODULE$, dbFile.getPath())});
               }

               options.createIfMissing(true);

               try {
                  tmpDb = JniDBFactory.factory.open(dbFile, options);
               } catch (NativeDB.DBException dbExc) {
                  throw new IOException("Unable to create state store", dbExc);
               }
            } else {
               logger.info("Creating state database at {}", new MDC[]{MDC.of(.MODULE$, dbFile)});
               options.createIfMissing(true);

               try {
                  tmpDb = JniDBFactory.factory.open(dbFile, options);
               } catch (NativeDB.DBException dbExc) {
                  throw new IOException("Unable to create state store", dbExc);
               }
            }
         }

         try {
            checkVersion(tmpDb, version, mapper);
         } catch (IOException ioe) {
            tmpDb.close();
            throw ioe;
         }
      }

      return tmpDb;
   }

   @VisibleForTesting
   static DB initLevelDB(File file) throws IOException {
      Options options = new Options();
      options.createIfMissing(true);
      JniDBFactory factory = new JniDBFactory();
      return factory.open(file, options);
   }

   public static void checkVersion(DB db, StoreVersion newversion, ObjectMapper mapper) throws IOException {
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

   public static void storeVersion(DB db, StoreVersion version, ObjectMapper mapper) throws IOException {
      db.put(StoreVersion.KEY, mapper.writeValueAsBytes(version));
   }

   private static class LevelDBLogger implements Logger {
      private static final SparkLogger LOG = SparkLoggerFactory.getLogger(LevelDBLogger.class);

      public void log(String message) {
         LOG.info(message);
      }
   }
}
