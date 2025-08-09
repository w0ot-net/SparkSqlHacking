package org.apache.spark.storage;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.shuffle.IndexShuffleBlockResolver;
import org.apache.spark.shuffle.IndexShuffleBlockResolver$;
import org.apache.spark.shuffle.MigratableResolver;
import org.apache.spark.shuffle.ShuffleBlockInfo;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd!B\f\u0019\u0001a\u0001\u0003\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u000bM\u0002A\u0011\u0001\u001b\t\u000fa\u0002!\u0019!C\u0005s!1!\t\u0001Q\u0001\niBqa\u0011\u0001C\u0002\u0013%A\t\u0003\u0004K\u0001\u0001\u0006I!\u0012\u0005\b\u0017\u0002\u0011\r\u0011\"\u0003M\u0011\u0019\u0001\u0006\u0001)A\u0005\u001b\"9\u0011\u000b\u0001b\u0001\n\u0013\u0011\u0006B\u00020\u0001A\u0003%1\u000bC\u0003`\u0001\u0011\u0005\u0001\rC\u0003r\u0001\u0011\u0005!o\u0002\u0004~1!\u0005!D \u0004\u0007/aA\tAG@\t\rMrA\u0011AA\u0001\u0011%\t\u0019A\u0004b\u0001\n\u0003\t)\u0001\u0003\u0005\u0002\u000e9\u0001\u000b\u0011BA\u0004\u0011\u001d\tyA\u0004C\u0001\u0003#Aq!a\u0007\u000f\t\u0003\ti\u0002C\u0004\u0002,9!\t!!\f\t\u000f\u0005Mb\u0002\"\u0003\u00026!9\u0011q\n\b\u0005\u0002\u0005E#a\u0004$bY2\u0014\u0017mY6Ti>\u0014\u0018mZ3\u000b\u0005eQ\u0012aB:u_J\fw-\u001a\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sON\u0019\u0001!I\u0014\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\tA3&D\u0001*\u0015\tQ#$\u0001\u0005j]R,'O\\1m\u0013\ta\u0013FA\u0004M_\u001e<\u0017N\\4\u0002\t\r|gNZ\u0002\u0001!\t\u0001\u0014'D\u0001\u001b\u0013\t\u0011$DA\u0005Ta\u0006\u00148nQ8oM\u00061A(\u001b8jiz\"\"!N\u001c\u0011\u0005Y\u0002Q\"\u0001\r\t\u000b5\u0012\u0001\u0019A\u0018\u0002\u0019\u0019\fG\u000e\u001c2bG.\u0004\u0016\r\u001e5\u0016\u0003i\u0002\"a\u000f!\u000e\u0003qR!!\u0010 \u0002\u0005\u0019\u001c(BA \u001d\u0003\u0019A\u0017\rZ8pa&\u0011\u0011\t\u0010\u0002\u0005!\u0006$\b.A\u0007gC2d'-Y2l!\u0006$\b\u000eI\u0001\u000bQ\u0006$wn\u001c9D_:4W#A#\u0011\u0005\u0019CU\"A$\u000b\u00055r\u0014BA%H\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0006Y\u0001.\u00193p_B\u001cuN\u001c4!\u0003I1\u0017\r\u001c7cC\u000e\\g)\u001b7f'f\u001cH/Z7\u0016\u00035\u0003\"a\u000f(\n\u0005=c$A\u0003$jY\u0016\u001c\u0016p\u001d;f[\u0006\u0019b-\u00197mE\u0006\u001c7NR5mKNK8\u000f^3nA\u0005)\u0011\r\u001d9JIV\t1\u000b\u0005\u0002U7:\u0011Q+\u0017\t\u0003-\u000ej\u0011a\u0016\u0006\u00031:\na\u0001\u0010:p_Rt\u0014B\u0001.$\u0003\u0019\u0001&/\u001a3fM&\u0011A,\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i\u001b\u0013AB1qa&#\u0007%\u0001\u0003d_BLHcA1eYB\u0011!EY\u0005\u0003G\u000e\u0012A!\u00168ji\")Qm\u0003a\u0001M\u0006\u00012\u000f[;gM2,'\t\\8dW&sgm\u001c\t\u0003O*l\u0011\u0001\u001b\u0006\u0003Sj\tqa\u001d5vM\u001adW-\u0003\u0002lQ\n\u00012\u000b[;gM2,'\t\\8dW&sgm\u001c\u0005\u0006[.\u0001\rA\\\u0001\u0003E6\u0004\"AN8\n\u0005AD\"\u0001\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018AB3ySN$8\u000fF\u0002tmn\u0004\"A\t;\n\u0005U\u001c#a\u0002\"p_2,\u0017M\u001c\u0005\u0006o2\u0001\r\u0001_\u0001\ng\",hM\u001a7f\u0013\u0012\u0004\"AI=\n\u0005i\u001c#aA%oi\")A\u0010\u0004a\u0001'\u0006Aa-\u001b7f]\u0006lW-A\bGC2d'-Y2l'R|'/Y4f!\t1dbE\u0002\u000fC\u001d\"\u0012A`\u0001\u001a\r\u0006cEJQ!D\u0017~\u0013EjT\"L?6\u000be*Q$F%~KE)\u0006\u0002\u0002\bA\u0019a'!\u0003\n\u0007\u0005-\u0001D\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\u00025\u0019\u000bE\n\u0014\"B\u0007.{&\tT(D\u0017~k\u0015IT!H\u000bJ{\u0016\n\u0012\u0011\u0002%\u001d,GOR1mY\n\f7m[*u_J\fw-\u001a\u000b\u0005\u0003'\tI\u0002\u0005\u0003#\u0003+)\u0014bAA\fG\t1q\n\u001d;j_:DQ!\f\nA\u0002=\nAD]3hSN$XM\u001d\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018J\u001a(fK\u0012,G\rF\u0003b\u0003?\tI\u0003C\u0004\u0002\"M\u0001\r!a\t\u0002\r5\f7\u000f^3s!\r1\u0014QE\u0005\u0004\u0003OA\"A\u0005\"m_\u000e\\W*\u00198bO\u0016\u0014X*Y:uKJDQ!L\nA\u0002=\nqa\u00197fC:,\u0006\u000fF\u0003b\u0003_\t\t\u0004C\u0003.)\u0001\u0007q\u0006C\u0003D)\u0001\u0007Q)A\tsKB|'\u000f\u001e\"m_\u000e\\7\u000b^1ukN$ra]A\u001c\u0003w\t)\u0005\u0003\u0004\u0002:U\u0001\rA\\\u0001\rE2|7m['b]\u0006<WM\u001d\u0005\b\u0003{)\u0002\u0019AA \u0003\u001d\u0011Gn\\2l\u0013\u0012\u00042ANA!\u0013\r\t\u0019\u0005\u0007\u0002\b\u00052|7m[%e\u0011\u001d\t9%\u0006a\u0001\u0003\u0013\n!\u0002Z1uC2+gn\u001a;i!\r\u0011\u00131J\u0005\u0004\u0003\u001b\u001a#\u0001\u0002'p]\u001e\fAA]3bIR1\u00111KA2\u0003K\u0002B!!\u0016\u0002`5\u0011\u0011q\u000b\u0006\u0005\u00033\nY&\u0001\u0004ck\u001a4WM\u001d\u0006\u0004\u0003;R\u0012a\u00028fi^|'o[\u0005\u0005\u0003C\n9FA\u0007NC:\fw-\u001a3Ck\u001a4WM\u001d\u0005\u0006[Y\u0001\ra\f\u0005\b\u0003{1\u0002\u0019AA \u0001"
)
public class FallbackStorage implements Logging {
   private final Path fallbackPath;
   private final Configuration hadoopConf;
   private final FileSystem fallbackFileSystem;
   private final String appId;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static ManagedBuffer read(final SparkConf conf, final BlockId blockId) {
      return FallbackStorage$.MODULE$.read(conf, blockId);
   }

   public static void cleanUp(final SparkConf conf, final Configuration hadoopConf) {
      FallbackStorage$.MODULE$.cleanUp(conf, hadoopConf);
   }

   public static void registerBlockManagerIfNeeded(final BlockManagerMaster master, final SparkConf conf) {
      FallbackStorage$.MODULE$.registerBlockManagerIfNeeded(master, conf);
   }

   public static Option getFallbackStorage(final SparkConf conf) {
      return FallbackStorage$.MODULE$.getFallbackStorage(conf);
   }

   public static BlockManagerId FALLBACK_BLOCK_MANAGER_ID() {
      return FallbackStorage$.MODULE$.FALLBACK_BLOCK_MANAGER_ID();
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
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Path fallbackPath() {
      return this.fallbackPath;
   }

   private Configuration hadoopConf() {
      return this.hadoopConf;
   }

   private FileSystem fallbackFileSystem() {
      return this.fallbackFileSystem;
   }

   private String appId() {
      return this.appId;
   }

   public void copy(final ShuffleBlockInfo shuffleBlockInfo, final BlockManager bm) {
      int shuffleId = shuffleBlockInfo.shuffleId();
      long mapId = shuffleBlockInfo.mapId();
      MigratableResolver var7 = bm.migratableResolver();
      if (var7 instanceof IndexShuffleBlockResolver var8) {
         File indexFile = var8.getIndexFile(shuffleId, mapId, var8.getIndexFile$default$3());
         if (indexFile.exists()) {
            int hash = JavaUtils.nonNegativeHash(indexFile.getName());
            this.fallbackFileSystem().copyFromLocalFile(new Path(Utils$.MODULE$.resolveURI(indexFile.getAbsolutePath())), new Path(this.fallbackPath(), this.appId() + "/" + shuffleId + "/" + hash + "/" + indexFile.getName()));
            File dataFile = var8.getDataFile(shuffleId, mapId);
            if (dataFile.exists()) {
               int hash = JavaUtils.nonNegativeHash(dataFile.getName());
               this.fallbackFileSystem().copyFromLocalFile(new Path(Utils$.MODULE$.resolveURI(dataFile.getAbsolutePath())), new Path(this.fallbackPath(), this.appId() + "/" + shuffleId + "/" + hash + "/" + dataFile.getName()));
            }

            int reduceId = IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID();
            ShuffleIndexBlockId indexBlockId = new ShuffleIndexBlockId(shuffleId, mapId, reduceId);
            FallbackStorage$.MODULE$.org$apache$spark$storage$FallbackStorage$$reportBlockStatus(bm, indexBlockId, indexFile.length());
            if (dataFile.exists()) {
               ShuffleDataBlockId dataBlockId = new ShuffleDataBlockId(shuffleId, mapId, reduceId);
               FallbackStorage$.MODULE$.org$apache$spark$storage$FallbackStorage$$reportBlockStatus(bm, dataBlockId, dataFile.length());
               BoxedUnit var18 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var17 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var16 = BoxedUnit.UNIT;
         }
      } else {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unsupported Resolver: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, var7.getClass().getName())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public boolean exists(final int shuffleId, final String filename) {
      int hash = JavaUtils.nonNegativeHash(filename);
      return this.fallbackFileSystem().exists(new Path(this.fallbackPath(), this.appId() + "/" + shuffleId + "/" + hash + "/" + filename));
   }

   public FallbackStorage(final SparkConf conf) {
      Logging.$init$(this);
      scala.Predef..MODULE$.require(conf.contains("spark.app.id"));
      scala.Predef..MODULE$.require(((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).isDefined());
      this.fallbackPath = new Path((String)((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).get());
      this.hadoopConf = SparkHadoopUtil$.MODULE$.get().newConfiguration(conf);
      this.fallbackFileSystem = FileSystem.get(this.fallbackPath().toUri(), this.hadoopConf());
      this.appId = conf.getAppId();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
