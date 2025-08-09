package org.apache.spark.storage;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.network.shuffle.BlockStoreClient;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.util.Failure;
import scala.util.Success;

@ScalaSignature(
   bytes = "\u0006\u0005M4Q!\u0003\u0006\u0001\u0019IA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tI\u0001\u0011\t\u0011)A\u0005K!)Q\u0006\u0001C\u0001]!91\u0007\u0001b\u0001\n\u0013!\u0004BB(\u0001A\u0003%Q\u0007\u0003\u0004Q\u0001\u0011\u0005A\"\u0015\u0005\u0007+\u0002!\t\u0001\u0004,\t\rq\u0003A\u0011\u0001\u0007^\u0005MAun\u001d;M_\u000e\fG\u000eR5s\u001b\u0006t\u0017mZ3s\u0015\tYA\"A\u0004ti>\u0014\u0018mZ3\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c2\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!$H\u0007\u00027)\u0011A\u0004D\u0001\tS:$XM\u001d8bY&\u0011ad\u0007\u0002\b\u0019><w-\u001b8h\u0003%\u0019\u0017m\u00195f'&TXm\u0001\u0001\u0011\u0005Q\u0011\u0013BA\u0012\u0016\u0005\rIe\u000e^\u0001\u0011E2|7m[*u_J,7\t\\5f]R\u0004\"AJ\u0016\u000e\u0003\u001dR!\u0001K\u0015\u0002\u000fMDWO\u001a4mK*\u0011!\u0006D\u0001\b]\u0016$xo\u001c:l\u0013\tasE\u0001\tCY>\u001c7n\u0015;pe\u0016\u001cE.[3oi\u00061A(\u001b8jiz\"2aL\u00193!\t\u0001\u0004!D\u0001\u000b\u0011\u0015y2\u00011\u0001\"\u0011\u0015!3\u00011\u0001&\u0003i)\u00070Z2vi>\u0014\u0018\n\u001a+p\u0019>\u001c\u0017\r\u001c#jeN\u001c\u0015m\u00195f+\u0005)\u0004\u0003\u0002\u001c@\u00032k\u0011a\u000e\u0006\u0003qe\nQaY1dQ\u0016T!AO\u001e\u0002\r\r|W.\\8o\u0015\taT(\u0001\u0004h_><G.\u001a\u0006\u0002}\u0005\u00191m\\7\n\u0005\u0001;$!B\"bG\",\u0007C\u0001\"J\u001d\t\u0019u\t\u0005\u0002E+5\tQI\u0003\u0002GA\u00051AH]8pizJ!\u0001S\u000b\u0002\rA\u0013X\rZ3g\u0013\tQ5J\u0001\u0004TiJLgn\u001a\u0006\u0003\u0011V\u00012\u0001F'B\u0013\tqUCA\u0003BeJ\f\u00170A\u000efq\u0016\u001cW\u000f^8s\u0013\u0012$v\u000eT8dC2$\u0015N]:DC\u000eDW\rI\u0001\u0017O\u0016$8)Y2iK\u0012Dun\u001d;M_\u000e\fG\u000eR5sgV\t!\u000b\u0005\u0003C'\u0006c\u0015B\u0001+L\u0005\ri\u0015\r]\u0001\u001aO\u0016$8)Y2iK\u0012Dun\u001d;M_\u000e\fG\u000eR5sg\u001a{'\u000f\u0006\u0002X5B\u0019A\u0003\u0017'\n\u0005e+\"AB(qi&|g\u000eC\u0003\\\u000f\u0001\u0007\u0011)\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\f\u0001cZ3u\u0011>\u001cH\u000fT8dC2$\u0015N]:\u0015\tykw.\u001d\u000b\u0003?\n\u0004\"\u0001\u00061\n\u0005\u0005,\"\u0001B+oSRDQa\u0019\u0005A\u0002\u0011\f\u0001bY1mY\n\f7m\u001b\t\u0005)\u0015<w,\u0003\u0002g+\tIa)\u001e8di&|g.\r\t\u0004Q.\u0014V\"A5\u000b\u0005),\u0012\u0001B;uS2L!\u0001\\5\u0003\u0007Q\u0013\u0018\u0010C\u0003o\u0011\u0001\u0007\u0011)\u0001\u0003i_N$\b\"\u00029\t\u0001\u0004\t\u0013\u0001\u00029peRDQA\u001d\u0005A\u00021\u000b1\"\u001a=fGV$xN]%eg\u0002"
)
public class HostLocalDirManager implements Logging {
   private final BlockStoreClient blockStoreClient;
   private final Cache executorIdToLocalDirsCache;
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

   private Cache executorIdToLocalDirsCache() {
      return this.executorIdToLocalDirsCache;
   }

   public scala.collection.immutable.Map getCachedHostLocalDirs() {
      synchronized(this.executorIdToLocalDirsCache()){}

      scala.collection.immutable.Map var2;
      try {
         var2 = .MODULE$.ConcurrentMapHasAsScala(this.executorIdToLocalDirsCache().asMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public Option getCachedHostLocalDirsFor(final String executorId) {
      synchronized(this.executorIdToLocalDirsCache()){}

      Option var3;
      try {
         var3 = scala.Option..MODULE$.apply(this.executorIdToLocalDirsCache().getIfPresent(executorId));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public void getHostLocalDirs(final String host, final int port, final String[] executorIds, final Function1 callback) {
      CompletableFuture hostLocalDirsCompletable = new CompletableFuture();
      this.blockStoreClient.getHostLocalDirs(host, port, executorIds, hostLocalDirsCompletable);
      hostLocalDirsCompletable.whenComplete((hostLocalDirs, throwable) -> {
         if (hostLocalDirs != null) {
            callback.apply(new Success(.MODULE$.MapHasAsScala(hostLocalDirs).asScala().toMap(scala..less.colon.less..MODULE$.refl())));
            synchronized(this.executorIdToLocalDirsCache()){}

            try {
               this.executorIdToLocalDirsCache().putAll(hostLocalDirs);
            } catch (Throwable var6) {
               throw var6;
            }

         } else {
            callback.apply(new Failure(throwable));
         }
      });
   }

   public HostLocalDirManager(final int cacheSize, final BlockStoreClient blockStoreClient) {
      this.blockStoreClient = blockStoreClient;
      Logging.$init$(this);
      this.executorIdToLocalDirsCache = CacheBuilder.newBuilder().maximumSize((long)cacheSize).build();
   }
}
