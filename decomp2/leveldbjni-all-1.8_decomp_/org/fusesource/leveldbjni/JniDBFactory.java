package org.fusesource.leveldbjni;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.fusesource.leveldbjni.internal.JniDB;
import org.fusesource.leveldbjni.internal.NativeBuffer;
import org.fusesource.leveldbjni.internal.NativeCache;
import org.fusesource.leveldbjni.internal.NativeComparator;
import org.fusesource.leveldbjni.internal.NativeCompressionType;
import org.fusesource.leveldbjni.internal.NativeDB;
import org.fusesource.leveldbjni.internal.NativeLogger;
import org.fusesource.leveldbjni.internal.NativeOptions;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBComparator;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Logger;
import org.iq80.leveldb.Options;

public class JniDBFactory implements DBFactory {
   public static final JniDBFactory factory = new JniDBFactory();
   public static final String VERSION;

   public static byte[] bytes(String value) {
      if (value == null) {
         return null;
      } else {
         try {
            return value.getBytes("UTF-8");
         } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public static String asString(byte[] value) {
      if (value == null) {
         return null;
      } else {
         try {
            return new String(value, "UTF-8");
         } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public DB open(File path, Options options) throws IOException {
      NativeDB db = null;
      OptionsResourceHolder holder = new OptionsResourceHolder();

      try {
         holder.init(options);
         db = NativeDB.open(holder.options, path);
      } finally {
         if (db == null) {
            holder.close();
         }

      }

      return new JniDB(db, holder.cache, holder.comparator, holder.logger);
   }

   public void destroy(File path, Options options) throws IOException {
      OptionsResourceHolder holder = new OptionsResourceHolder();

      try {
         holder.init(options);
         NativeDB.destroy(path, holder.options);
      } finally {
         holder.close();
      }

   }

   public void repair(File path, Options options) throws IOException {
      OptionsResourceHolder holder = new OptionsResourceHolder();

      try {
         holder.init(options);
         NativeDB.repair(path, holder.options);
      } finally {
         holder.close();
      }

   }

   public String toString() {
      return String.format("leveldbjni version %s", VERSION);
   }

   public static void pushMemoryPool(int size) {
      NativeBuffer.pushMemoryPool(size);
   }

   public static void popMemoryPool() {
      NativeBuffer.popMemoryPool();
   }

   static {
      NativeDB.LIBRARY.load();
      String v = "unknown";
      InputStream is = JniDBFactory.class.getResourceAsStream("version.txt");

      try {
         v = (new BufferedReader(new InputStreamReader(is, "UTF-8"))).readLine();
      } catch (Throwable var11) {
      } finally {
         try {
            is.close();
         } catch (Throwable var10) {
         }

      }

      VERSION = v;
   }

   private static class OptionsResourceHolder {
      NativeCache cache;
      NativeComparator comparator;
      NativeLogger logger;
      NativeOptions options;

      private OptionsResourceHolder() {
         this.cache = null;
         this.comparator = null;
         this.logger = null;
      }

      public void init(Options value) {
         this.options = new NativeOptions();
         this.options.blockRestartInterval(value.blockRestartInterval());
         this.options.blockSize((long)value.blockSize());
         this.options.createIfMissing(value.createIfMissing());
         this.options.errorIfExists(value.errorIfExists());
         this.options.maxOpenFiles(value.maxOpenFiles());
         this.options.paranoidChecks(value.paranoidChecks());
         this.options.writeBufferSize((long)value.writeBufferSize());
         switch (value.compressionType()) {
            case NONE:
               this.options.compression(NativeCompressionType.kNoCompression);
               break;
            case SNAPPY:
               this.options.compression(NativeCompressionType.kSnappyCompression);
         }

         if (value.cacheSize() > 0L) {
            this.cache = new NativeCache(value.cacheSize());
            this.options.cache(this.cache);
         }

         final DBComparator userComparator = value.comparator();
         if (userComparator != null) {
            this.comparator = new NativeComparator() {
               public int compare(byte[] key1, byte[] key2) {
                  return userComparator.compare(key1, key2);
               }

               public String name() {
                  return userComparator.name();
               }
            };
            this.options.comparator(this.comparator);
         }

         final Logger userLogger = value.logger();
         if (userLogger != null) {
            this.logger = new NativeLogger() {
               public void log(String message) {
                  userLogger.log(message);
               }
            };
            this.options.infoLog(this.logger);
         }

      }

      public void close() {
         if (this.cache != null) {
            this.cache.delete();
         }

         if (this.comparator != null) {
            this.comparator.delete();
         }

         if (this.logger != null) {
            this.logger.delete();
         }

      }
   }
}
