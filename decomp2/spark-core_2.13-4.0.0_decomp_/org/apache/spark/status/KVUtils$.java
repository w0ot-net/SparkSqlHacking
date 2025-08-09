package org.apache.spark.status;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.file.Files;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.history.FsHistoryProvider$;
import org.apache.spark.deploy.history.FsHistoryProviderMetadata;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.History;
import org.apache.spark.internal.config.History$;
import org.apache.spark.status.protobuf.KVStoreProtobufSerializer;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.InMemoryStore;
import org.apache.spark.util.kvstore.KVStore;
import org.apache.spark.util.kvstore.KVStoreIterator;
import org.apache.spark.util.kvstore.KVStoreView;
import org.apache.spark.util.kvstore.LevelDB;
import org.apache.spark.util.kvstore.RocksDB;
import org.apache.spark.util.kvstore.UnsupportedStoreVersionException;
import org.fusesource.leveldbjni.internal.NativeDB;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class KVUtils$ implements Logging {
   public static final KVUtils$ MODULE$ = new KVUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Enumeration.Value backend(final SparkConf conf, final boolean live) {
      return live ? History.HybridStoreDiskBackend$.MODULE$.ROCKSDB() : History.HybridStoreDiskBackend$.MODULE$.withName((String)conf.get(History$.MODULE$.HYBRID_STORE_DISK_BACKEND()));
   }

   private KVUtils.KVStoreScalaSerializer serializer(final SparkConf conf, final boolean live) {
      return (KVUtils.KVStoreScalaSerializer)(live ? new KVStoreProtobufSerializer() : this.serializerForHistoryServer(conf));
   }

   public KVStore open(final File path, final Object metadata, final SparkConf conf, final boolean live, final ClassTag evidence$1) {
      Object var14;
      label41: {
         KVUtils.KVStoreScalaSerializer kvSerializer;
         label47: {
            .MODULE$.require(metadata != null, () -> "Metadata is required.");
            kvSerializer = this.serializer(conf, live);
            Enumeration.Value var9 = this.backend(conf, live);
            Enumeration.Value var10000 = History.HybridStoreDiskBackend$.MODULE$.LEVELDB();
            if (var10000 == null) {
               if (var9 == null) {
                  break label47;
               }
            } else if (var10000.equals(var9)) {
               break label47;
            }

            var10000 = History.HybridStoreDiskBackend$.MODULE$.ROCKSDB();
            if (var10000 == null) {
               if (var9 != null) {
                  throw new MatchError(var9);
               }
            } else if (!var10000.equals(var9)) {
               throw new MatchError(var9);
            }

            var14 = new RocksDB(path, kvSerializer);
            break label41;
         }

         this.logWarning((Function0)(() -> "The LEVELDB is deprecated. Please use ROCKSDB instead."));
         var14 = new LevelDB(path, kvSerializer);
      }

      KVStore db = (KVStore)var14;
      Object dbMeta = db.getMetadata(scala.reflect.package..MODULE$.classTag(evidence$1).runtimeClass());
      if (dbMeta == null) {
         db.setMetadata(metadata);
      } else if (!BoxesRunTime.equals(dbMeta, metadata)) {
         db.close();
         throw new KVUtils.MetadataMismatchException();
      }

      return db;
   }

   public KVUtils.KVStoreScalaSerializer serializerForHistoryServer(final SparkConf conf) {
      Enumeration.Value var3 = History.LocalStoreSerializer$.MODULE$.withName((String)conf.get(History$.MODULE$.LOCAL_STORE_SERIALIZER()));
      Enumeration.Value var10000 = History.LocalStoreSerializer$.MODULE$.JSON();
      if (var10000 == null) {
         if (var3 == null) {
            return new KVUtils.KVStoreScalaSerializer();
         }
      } else if (var10000.equals(var3)) {
         return new KVUtils.KVStoreScalaSerializer();
      }

      var10000 = History.LocalStoreSerializer$.MODULE$.PROTOBUF();
      if (var10000 == null) {
         if (var3 == null) {
            return new KVStoreProtobufSerializer();
         }
      } else if (var10000.equals(var3)) {
         return new KVStoreProtobufSerializer();
      }

      throw new IllegalArgumentException("Unrecognized KV store serializer " + var3);
   }

   public KVStore createKVStore(final Option storePath, final boolean live, final SparkConf conf) {
      return (KVStore)storePath.map((path) -> {
         String var18;
         label70: {
            label74: {
               Enumeration.Value diskBackend = MODULE$.backend(conf, live);
               Enumeration.Value var10000 = History.HybridStoreDiskBackend$.MODULE$.LEVELDB();
               if (var10000 == null) {
                  if (diskBackend == null) {
                     break label74;
                  }
               } else if (var10000.equals(diskBackend)) {
                  break label74;
               }

               var10000 = History.HybridStoreDiskBackend$.MODULE$.ROCKSDB();
               if (var10000 == null) {
                  if (diskBackend != null) {
                     throw new MatchError(diskBackend);
                  }
               } else if (!var10000.equals(diskBackend)) {
                  throw new MatchError(diskBackend);
               }

               var18 = "listing.rdb";
               break label70;
            }

            var18 = "listing.ldb";
         }

         String dir = var18;
         File dbPath = Files.createDirectories((new File(path, dir)).toPath()).toFile();
         Utils$.MODULE$.chmod700(dbPath);
         FsHistoryProviderMetadata metadata = new FsHistoryProviderMetadata(FsHistoryProvider$.MODULE$.CURRENT_LISTING_VERSION(), AppStatusStore$.MODULE$.CURRENT_VERSION(), (String)conf.get(History$.MODULE$.HISTORY_LOG_DIR()));

         try {
            var19 = MODULE$.open(dbPath, metadata, conf, live, scala.reflect.ClassTag..MODULE$.apply(FsHistoryProviderMetadata.class));
         } catch (Throwable var16) {
            if (var16 instanceof UnsupportedStoreVersionException ? true : var16 instanceof KVUtils.MetadataMismatchException) {
               MODULE$.logInfo((Function0)(() -> "Detected incompatible DB versions, deleting..."));
               scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])path.listFiles()), (file) -> {
                  $anonfun$createKVStore$3(file);
                  return BoxedUnit.UNIT;
               });
               var19 = MODULE$.open(dbPath, metadata, conf, live, scala.reflect.ClassTag..MODULE$.apply(FsHistoryProviderMetadata.class));
            } else {
               if (!(var16 instanceof NativeDB.DBException ? true : var16 instanceof RocksDBException)) {
                  throw var16;
               }

               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to load disk store ", " :"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dbPath)})))), var16);
               Utils$.MODULE$.deleteRecursively(dbPath);
               var19 = MODULE$.open(dbPath, metadata, conf, live, scala.reflect.ClassTag..MODULE$.apply(FsHistoryProviderMetadata.class));
            }
         }

         return var19;
      }).getOrElse(() -> new InMemoryStore());
   }

   public Seq viewToSeq(final KVStoreView view, final int max, final Function1 filter) {
      KVStoreIterator iter = view.closeableIterator();

      List var10000;
      try {
         var10000 = scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().filter(filter).take(max).toList();
      } finally {
         iter.close();
      }

      return var10000;
   }

   public Seq viewToSeq(final KVStoreView view, final int from, final int until, final Function1 filter) {
      return (Seq)Utils$.MODULE$.tryWithResource(() -> view.closeableIterator(), (iter) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().filter(filter).slice(from, until).toList());
   }

   public Seq viewToSeq(final KVStoreView view) {
      return (Seq)Utils$.MODULE$.tryWithResource(() -> view.closeableIterator(), (iter) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().toList());
   }

   public int count(final KVStoreView view, final Function1 countFunc) {
      return BoxesRunTime.unboxToInt(Utils$.MODULE$.tryWithResource(() -> view.closeableIterator(), (iter) -> BoxesRunTime.boxToInteger($anonfun$count$2(countFunc, iter))));
   }

   public void foreach(final KVStoreView view, final Function1 foreachFunc) {
      Utils$.MODULE$.tryWithResource(() -> view.closeableIterator(), (iter) -> {
         $anonfun$foreach$2(foreachFunc, iter);
         return BoxedUnit.UNIT;
      });
   }

   public Seq mapToSeq(final KVStoreView view, final Function1 mapFunc) {
      return (Seq)Utils$.MODULE$.tryWithResource(() -> view.closeableIterator(), (iter) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().map(mapFunc).toList());
   }

   public int size(final KVStoreView view) {
      return BoxesRunTime.unboxToInt(Utils$.MODULE$.tryWithResource(() -> view.closeableIterator(), (iter) -> BoxesRunTime.boxToInteger($anonfun$size$2(iter))));
   }

   // $FF: synthetic method
   public static final void $anonfun$createKVStore$3(final File file) {
      Utils$.MODULE$.deleteRecursively(file);
   }

   // $FF: synthetic method
   public static final int $anonfun$count$2(final Function1 countFunc$1, final KVStoreIterator iter) {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().count(countFunc$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$foreach$2(final Function1 foreachFunc$1, final KVStoreIterator iter) {
      scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().foreach(foreachFunc$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$size$2(final KVStoreIterator iter) {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(iter).asScala().size();
   }

   private KVUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
