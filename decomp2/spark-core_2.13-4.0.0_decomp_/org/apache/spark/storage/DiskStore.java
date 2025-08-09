package org.apache.spark.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.security.CryptoStreamUtils$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.slf4j.Logger;
import org.sparkproject.guava.io.Closeables;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!\u0002\f\u0018\u0001ey\u0002\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011I\u0002!\u0011!Q\u0001\nMB\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\u0006w\u0001!\t\u0001\u0010\u0005\b\u0003\u0002\u0011\r\u0011\"\u0003C\u0011\u00191\u0005\u0001)A\u0005\u0007\"9q\t\u0001b\u0001\n\u0013\u0011\u0005B\u0002%\u0001A\u0003%1\tC\u0004J\u0001\t\u0007I\u0011\u0002&\t\ra\u0003\u0001\u0015!\u0003L\u0011\u001dI\u0006A1A\u0005\niCaA\u0018\u0001!\u0002\u0013Y\u0006\"B0\u0001\t\u0003\u0001\u0007\"B2\u0001\t\u0003!\u0007\"B<\u0001\t\u0003A\bbBA\u0004\u0001\u0011\u0005\u0011\u0011\u0002\u0005\b\u0003\u000f\u0001A\u0011AA\n\u0011\u001d\t9\u0003\u0001C\u0001\u0003SAq!!\f\u0001\t\u0003\ty\u0003C\u0004\u0002<\u0001!\t!!\u0010\t\u000f\u0005\u0005\u0003\u0001\"\u0003\u0002D\tIA)[:l'R|'/\u001a\u0006\u00031e\tqa\u001d;pe\u0006<WM\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h'\r\u0001\u0001E\n\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0005%J\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005-B#a\u0002'pO\u001eLgnZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005=\u0002T\"A\r\n\u0005EJ\"!C*qCJ\\7i\u001c8g\u0003-!\u0017n]6NC:\fw-\u001a:\u0011\u0005Q*T\"A\f\n\u0005Y:\"\u0001\u0005#jg.\u0014En\\2l\u001b\u0006t\u0017mZ3s\u0003=\u0019XmY;sSRLX*\u00198bO\u0016\u0014\bCA\u0018:\u0013\tQ\u0014DA\bTK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s\u0003\u0019a\u0014N\\5u}Q!QHP A!\t!\u0004\u0001C\u0003-\t\u0001\u0007a\u0006C\u00033\t\u0001\u00071\u0007C\u00038\t\u0001\u0007\u0001(A\tnS:lU-\\8ss6\u000b\u0007OQ=uKN,\u0012a\u0011\t\u0003C\u0011K!!\u0012\u0012\u0003\t1{gnZ\u0001\u0013[&tW*Z7pefl\u0015\r\u001d\"zi\u0016\u001c\b%A\tnCblU-\\8ss6\u000b\u0007OQ=uKN\f!#\\1y\u001b\u0016lwN]=NCB\u0014\u0015\u0010^3tA\u0005Q!\r\\8dWNK'0Z:\u0016\u0003-\u0003B\u0001T*V\u00076\tQJ\u0003\u0002O\u001f\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005A\u000b\u0016\u0001B;uS2T\u0011AU\u0001\u0005U\u00064\u0018-\u0003\u0002U\u001b\n\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\u0005Q2\u0016BA,\u0018\u0005\u001d\u0011En\\2l\u0013\u0012\f1B\u00197pG.\u001c\u0016N_3tA\u0005i2\u000f[;gM2,7+\u001a:wS\u000e,g)\u001a;dQJ#G-\u00128bE2,G-F\u0001\\!\t\tC,\u0003\u0002^E\t9!i\\8mK\u0006t\u0017AH:ik\u001a4G.Z*feZL7-\u001a$fi\u000eD'\u000b\u001a3F]\u0006\u0014G.\u001a3!\u0003\u001d9W\r^*ju\u0016$\"aQ1\t\u000b\tl\u0001\u0019A+\u0002\u000f\tdwnY6JI\u0006\u0019\u0001/\u001e;\u0015\u0005\u00154HC\u00014j!\t\ts-\u0003\u0002iE\t!QK\\5u\u0011\u0015Qg\u00021\u0001l\u0003%9(/\u001b;f\rVt7\r\u0005\u0003\"Y:4\u0017BA7#\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006A1\r[1o]\u0016d7O\u0003\u0002t#\u0006\u0019a.[8\n\u0005U\u0004(aE,sSR\f'\r\\3CsR,7\t[1o]\u0016d\u0007\"\u00022\u000f\u0001\u0004)\u0016\u0001\u00039vi\nKH/Z:\u0015\u0007\u0019L(\u0010C\u0003c\u001f\u0001\u0007Q\u000bC\u0003|\u001f\u0001\u0007A0A\u0003csR,7\u000fE\u0002~\u0003\u0007i\u0011A \u0006\u0004\u007f\u0006\u0005\u0011AA5p\u0015\t\u0001\u0016$C\u0002\u0002\u0006y\u0014\u0011c\u00115v].,GMQ=uK\n+hMZ3s\u0003!9W\r\u001e\"zi\u0016\u001cH\u0003BA\u0006\u0003#\u00012\u0001NA\u0007\u0013\r\tya\u0006\u0002\n\u00052|7m\u001b#bi\u0006DQA\u0019\tA\u0002U#b!a\u0003\u0002\u0016\u0005\r\u0002bBA\f#\u0001\u0007\u0011\u0011D\u0001\u0002MB!\u00111DA\u0010\u001b\t\tiB\u0003\u0002\u0000#&!\u0011\u0011EA\u000f\u0005\u00111\u0015\u000e\\3\t\r\u0005\u0015\u0012\u00031\u0001D\u0003%\u0011Gn\\2l'&TX-\u0001\u0004sK6|g/\u001a\u000b\u00047\u0006-\u0002\"\u00022\u0013\u0001\u0004)\u0016aD7pm\u00164\u0015\u000e\\3U_\ncwnY6\u0015\u000f\u0019\f\t$!\u000e\u00028!9\u00111G\nA\u0002\u0005e\u0011AC:pkJ\u001cWMR5mK\"1\u0011QE\nA\u0002\rCa!!\u000f\u0014\u0001\u0004)\u0016!\u0004;be\u001e,GO\u00117pG.LE-\u0001\u0005d_:$\u0018-\u001b8t)\rY\u0016q\b\u0005\u0006ER\u0001\r!V\u0001\r_B,gNR8s/JLG/\u001a\u000b\u0004]\u0006\u0015\u0003bBA$+\u0001\u0007\u0011\u0011D\u0001\u0005M&dW\r"
)
public class DiskStore implements Logging {
   private final SparkConf conf;
   private final DiskBlockManager diskManager;
   private final SecurityManager securityManager;
   private final long minMemoryMapBytes;
   private final long maxMemoryMapBytes;
   private final ConcurrentHashMap blockSizes;
   private final boolean shuffleServiceFetchRddEnabled;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private long minMemoryMapBytes() {
      return this.minMemoryMapBytes;
   }

   private long maxMemoryMapBytes() {
      return this.maxMemoryMapBytes;
   }

   private ConcurrentHashMap blockSizes() {
      return this.blockSizes;
   }

   private boolean shuffleServiceFetchRddEnabled() {
      return this.shuffleServiceFetchRddEnabled;
   }

   public long getSize(final BlockId blockId) {
      return BoxesRunTime.unboxToLong(this.blockSizes().get(blockId));
   }

   public void put(final BlockId blockId, final Function1 writeFunc) {
      if (this.contains(blockId)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " is already present in the disk store"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));

         boolean var10000;
         try {
            var10000 = this.diskManager.getFile(blockId).delete();
         } catch (Exception var31) {
            throw org.apache.spark.SparkException..MODULE$.internalError("Block " + blockId + " is already present in the disk store and could not delete it " + var31, "STORAGE");
         }

         BoxesRunTime.boxToBoolean(var10000);
      } else {
         BoxedUnit var35 = BoxedUnit.UNIT;
      }

      this.logDebug((Function0)(() -> "Attempting to put block " + blockId));
      long startTimeNs = System.nanoTime();
      File file = this.diskManager.getFile(blockId);
      if (this.shuffleServiceFetchRddEnabled()) {
         this.diskManager.createWorldReadableFile(file);
      }

      CountingWritableChannel out = new CountingWritableChannel(this.openForWrite(file));
      boolean threwException = true;

      try {
         writeFunc.apply(out);
         this.blockSizes().put(blockId, BoxesRunTime.boxToLong(out.getCount()));
         threwException = false;
      } finally {
         try {
            out.close();
         } catch (IOException var32) {
            if (!threwException) {
               threwException = true;
               throw var32;
            }
         } finally {
            if (threwException) {
               this.remove(blockId);
            }

         }

      }

      this.logDebug((Function0)(() -> {
         String var10000 = file.getName();
         return "Block " + var10000 + " stored as " + Utils$.MODULE$.bytesToString(file.length()) + " file on disk in " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs) + " ms";
      }));
   }

   public void putBytes(final BlockId blockId, final ChunkedByteBuffer bytes) {
      this.put(blockId, (channel) -> {
         $anonfun$putBytes$1(bytes, channel);
         return BoxedUnit.UNIT;
      });
   }

   public BlockData getBytes(final BlockId blockId) {
      return this.getBytes(this.diskManager.getFile(blockId.name()), this.getSize(blockId));
   }

   public BlockData getBytes(final File f, final long blockSize) {
      Option var5 = this.securityManager.getIOEncryptionKey();
      if (var5 instanceof Some var6) {
         byte[] key = (byte[])var6.value();
         return new EncryptedBlockData(f, blockSize, this.conf, key);
      } else {
         return new DiskBlockData(this.minMemoryMapBytes(), this.maxMemoryMapBytes(), f, blockSize);
      }
   }

   public boolean remove(final BlockId blockId) {
      this.blockSizes().remove(blockId);
      File file = this.diskManager.getFile(blockId.name());
      if (file.exists()) {
         boolean ret = file.delete();
         if (!ret) {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file.getPath())})))));
         }

         return ret;
      } else {
         return false;
      }
   }

   public void moveFileToBlock(final File sourceFile, final long blockSize, final BlockId targetBlockId) {
      this.blockSizes().put(targetBlockId, BoxesRunTime.boxToLong(blockSize));
      File targetFile = this.diskManager.getFile(targetBlockId.name());
      this.logDebug((Function0)(() -> {
         String var10000 = sourceFile.getPath();
         return var10000 + " -> " + targetFile.getPath();
      }));
      FileUtils.moveFile(sourceFile, targetFile);
   }

   public boolean contains(final BlockId blockId) {
      return this.diskManager.containsBlock(blockId);
   }

   private WritableByteChannel openForWrite(final File file) {
      FileChannel out = (new FileOutputStream(file)).getChannel();

      try {
         return (WritableByteChannel)this.securityManager.getIOEncryptionKey().map((key) -> CryptoStreamUtils$.MODULE$.createWritableChannel(out, this.conf, key)).getOrElse(() -> out);
      } catch (Exception var4) {
         Closeables.close(out, true);
         file.delete();
         throw var4;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$putBytes$1(final ChunkedByteBuffer bytes$1, final WritableByteChannel channel) {
      bytes$1.writeFully(channel);
   }

   public DiskStore(final SparkConf conf, final DiskBlockManager diskManager, final SecurityManager securityManager) {
      this.conf = conf;
      this.diskManager = diskManager;
      this.securityManager = securityManager;
      Logging.$init$(this);
      this.minMemoryMapBytes = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.STORAGE_MEMORY_MAP_THRESHOLD()));
      this.maxMemoryMapBytes = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.MEMORY_MAP_LIMIT_FOR_TESTS()));
      this.blockSizes = new ConcurrentHashMap();
      this.shuffleServiceFetchRddEnabled = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_ENABLED())) && BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_FETCH_RDD_ENABLED()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
