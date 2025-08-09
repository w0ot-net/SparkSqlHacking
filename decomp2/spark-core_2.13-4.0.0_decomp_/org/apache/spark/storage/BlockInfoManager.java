package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import org.sparkproject.guava.collect.ConcurrentHashMultiset;
import org.sparkproject.guava.collect.ImmutableMultiset;
import org.sparkproject.guava.collect.Multiset;
import org.sparkproject.guava.util.concurrent.Striped;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tub!\u0002\u0014(\u0001\u001dz\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u000b\u0005\u0003A\u0011\u0001\"\u0006\t\u0019\u0003Aa\u0012\u0005\u0007\u0015\u0002\u0001\u000b\u0011B&\t\rm\u0003\u0001\u0015!\u0003]\u0011\u00199\u0007\u0001)A\u0005Q\"1\u0011\u0010\u0001Q\u0001\niD\u0001\"a\u0001\u0001A\u0003%\u0011Q\u0001\u0005\t\u0003'\u0001A\u0011A\u0014\u0002\u0016!A\u00111\u0004\u0001\u0005\u0002%\ni\u0002\u0003\u0005\u0002\"\u0001!\t!KA\u0012\u0011\u001d\ti\u0003\u0001C\u0001\u0003_Aq!!\u000e\u0001\t\u0013\t9\u0004C\u0004\u0002:\u0001!I!a\u000f\t\u000f\u0005m\u0003\u0001\"\u0003\u0002^!9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-\u0005\"CAI\u0001E\u0005I\u0011AAJ\u0011\u001d\tI\u000b\u0001C\u0001\u0003WC\u0011\"!-\u0001#\u0003%\t!a%\t\u000f\u0005M\u0006\u0001\"\u0001\u00026\"A\u0011\u0011\u0018\u0001\u0005\u0002\u001d\nY\fC\u0004\u0002@\u0002!\t!!1\t\u000f\u0005\u0015\u0007\u0001\"\u0001\u0002H\"I\u0011\u0011\u001b\u0001\u0012\u0002\u0013\u0005\u00111\u001b\u0005\b\u0003/\u0004A\u0011AAm\u0011%\t)\u000fAI\u0001\n\u0003\t\u0019\nC\u0004\u0002h\u0002!\t!!;\t\u0011\t\u0015\u0001\u0001\"\u0001(\u0005\u000fAqA!\u0005\u0001\t\u0003\u0011\u0019\u0002\u0003\u0005\u0003\u0016\u0001!\ta\nB\f\u0011\u001d\u0011I\u0002\u0001C\u0001\u00057AqA!\u000b\u0001\t\u0003\u0011Y\u0003C\u0004\u00030\u0001!\tA!\r\b\u0015\tMr%!A\t\u0002\u001d\u0012)DB\u0005'O\u0005\u0005\t\u0012A\u0014\u00038!1\u0011i\tC\u0001\u0005sA\u0011Ba\u000f$#\u0003%\t!a%\u0003!\tcwnY6J]\u001a|W*\u00198bO\u0016\u0014(B\u0001\u0015*\u0003\u001d\u0019Ho\u001c:bO\u0016T!AK\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u00051j\u0013AB1qC\u000eDWMC\u0001/\u0003\ry'oZ\n\u0004\u0001A2\u0004CA\u00195\u001b\u0005\u0011$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012$AB!osJ+g\r\u0005\u00028u5\t\u0001H\u0003\u0002:S\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002<q\t9Aj\\4hS:<\u0017a\u0006;sC\u000e\\\u0017N\\4DC\u000eDWMV5tS\nLG.\u001b;z\u0007\u0001\u0001\"!M \n\u0005\u0001\u0013$a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\r+\u0005C\u0001#\u0001\u001b\u00059\u0003b\u0002\u001f\u0003!\u0003\u0005\rA\u0010\u0002\u000e)\u0006\u001c8.\u0011;uK6\u0004H/\u00133\u0011\u0005EB\u0015BA%3\u0005\u0011auN\\4\u0002#\tdwnY6J]\u001a|wK]1qa\u0016\u00148\u000f\u0005\u0003M'VCV\"A'\u000b\u00059{\u0015AC2p]\u000e,(O]3oi*\u0011\u0001+U\u0001\u0005kRLGNC\u0001S\u0003\u0011Q\u0017M^1\n\u0005Qk%!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB\u0011AIV\u0005\u0003/\u001e\u0012qA\u00117pG.LE\r\u0005\u0002E3&\u0011!l\n\u0002\u0011\u00052|7m[%oM><&/\u00199qKJ\f!#\u001b8wSNL'\r\\3S\t\u0012\u0013En\\2lgB\u0019QL\u00193\u000e\u0003yS!a\u00181\u0002\u000f5,H/\u00192mK*\u0011\u0011MM\u0001\u000bG>dG.Z2uS>t\u0017BA2_\u0005\u001dA\u0015m\u001d5TKR\u0004\"\u0001R3\n\u0005\u0019<#A\u0003*E\t\ncwnY6JI\u0006)An\\2lgB\u0019\u0011N\u001d;\u000e\u0003)T!AT6\u000b\u0005Ac'BA7o\u0003\u0019\u0019w.\\7p]*\u0011q\u000e]\u0001\u0007O>|w\r\\3\u000b\u0003E\f1aY8n\u0013\t\u0019(NA\u0004TiJL\u0007/\u001a3\u0011\u0005U<X\"\u0001<\u000b\u0005\u001dl\u0015B\u0001=w\u0005\u0011aunY6\u0002!]\u0014\u0018\u000e^3M_\u000e\\7OQ=UCN\\\u0007\u0003\u0002'Twv\u0004\"\u0001`\u0002\u000e\u0003\u0001\u00012A`@V\u001b\u0005y\u0015bAA\u0001\u001f\n\u00191+\u001a;\u0002\u001fI,\u0017\r\u001a'pG.\u001c()\u001f+bg.\u0004R\u0001T*|\u0003\u000f\u0001R!!\u0003\u0002\u0010Uk!!a\u0003\u000b\u0007\u00055A.A\u0004d_2dWm\u0019;\n\t\u0005E\u00111\u0002\u0002\u0017\u0007>t7-\u001e:sK:$\b*Y:i\u001bVdG/[:fi\u0006I2m\u001c8uC&t7/\u00138wSNL'\r\\3S\t\u0012\u0013En\\2l)\rq\u0014q\u0003\u0005\u0007\u00033I\u0001\u0019\u00013\u0002\u000f\tdwnY6JI\u0006\t\u0012n\u001d*E\t\ncwnY6WSNL'\r\\3\u0015\u0007y\ny\u0002\u0003\u0004\u0002\u001a)\u0001\r\u0001Z\u0001\u0016iJLX*\u0019:l\u00052|7m[!t-&\u001c\u0018N\u00197f)\u0011\t)#a\u000b\u0011\u0007E\n9#C\u0002\u0002*I\u0012A!\u00168ji\"1\u0011\u0011D\u0006A\u0002\u0011\fAB]3hSN$XM\u001d+bg.$B!!\n\u00022!1\u00111\u0007\u0007A\u0002m\fQ\u0002^1tW\u0006#H/Z7qi&#\u0017\u0001F2veJ,g\u000e\u001e+bg.\fE\u000f^3naRLE-F\u0001|\u0003-\t7-];je\u0016dunY6\u0015\r\u0005u\u0012QKA,)\u0011\ty$a\u0013\u0011\u000bE\n\t%!\u0012\n\u0007\u0005\r#G\u0001\u0004PaRLwN\u001c\t\u0004\t\u0006\u001d\u0013bAA%O\tI!\t\\8dW&sgm\u001c\u0005\b\u0003\u001br\u0001\u0019AA(\u0003\u00051\u0007CB\u0019\u0002R\u0005\u0015c(C\u0002\u0002TI\u0012\u0011BR;oGRLwN\\\u0019\t\r\u0005ea\u00021\u0001V\u0011\u0019\tIF\u0004a\u0001}\u0005A!\r\\8dW&tw-A\u0005cY>\u001c7.\u00138g_V!\u0011qLA4)\u0011\t\t'a\"\u0015\t\u0005\r\u0014\u0011\u0010\t\u0005\u0003K\n9\u0007\u0004\u0001\u0005\u000f\u0005%tB1\u0001\u0002l\t\tA+\u0005\u0003\u0002n\u0005M\u0004cA\u0019\u0002p%\u0019\u0011\u0011\u000f\u001a\u0003\u000f9{G\u000f[5oOB\u0019\u0011'!\u001e\n\u0007\u0005]$GA\u0002B]fDq!!\u0014\u0010\u0001\u0004\tY\bE\u00052\u0003{\n)%!!\u0002d%\u0019\u0011q\u0010\u001a\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004cA;\u0002\u0004&\u0019\u0011Q\u0011<\u0003\u0013\r{g\u000eZ5uS>t\u0007BBA\r\u001f\u0001\u0007Q+\u0001\bm_\u000e\\gi\u001c:SK\u0006$\u0017N\\4\u0015\r\u0005}\u0012QRAH\u0011\u0019\tI\u0002\u0005a\u0001+\"A\u0011\u0011\f\t\u0011\u0002\u0003\u0007a(\u0001\rm_\u000e\\gi\u001c:SK\u0006$\u0017N\\4%I\u00164\u0017-\u001e7uII*\"!!&+\u0007y\n9j\u000b\u0002\u0002\u001aB!\u00111TAS\u001b\t\tiJ\u0003\u0003\u0002 \u0006\u0005\u0016!C;oG\",7m[3e\u0015\r\t\u0019KM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAT\u0003;\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039awnY6G_J<&/\u001b;j]\u001e$b!a\u0010\u0002.\u0006=\u0006BBA\r%\u0001\u0007Q\u000b\u0003\u0005\u0002ZI\u0001\n\u00111\u0001?\u0003aawnY6G_J<&/\u001b;j]\u001e$C-\u001a4bk2$HEM\u0001\u001eCN\u001cXM\u001d;CY>\u001c7.S:M_\u000e\\W\r\u001a$pe^\u0013\u0018\u000e^5oOR!\u0011QIA\\\u0011\u0019\tI\u0002\u0006a\u0001+\u0006\u0019q-\u001a;\u0015\t\u0005}\u0012Q\u0018\u0005\u0007\u00033)\u0002\u0019A+\u0002\u001b\u0011|wO\\4sC\u0012,Gj\\2l)\u0011\t)#a1\t\r\u0005ea\u00031\u0001V\u0003\u0019)h\u000e\\8dWR1\u0011QEAe\u0003\u0017Da!!\u0007\u0018\u0001\u0004)\u0006\"CAg/A\u0005\t\u0019AAh\u0003M!\u0018m]6BiR,W\u000e\u001d;JI>\u0003H/[8o!\u0011\t\u0014\u0011I>\u0002!UtGn\\2lI\u0011,g-Y;mi\u0012\u0012TCAAkU\u0011\ty-a&\u0002-1|7m\u001b(fo\ncwnY6G_J<&/\u001b;j]\u001e$rAPAn\u0003;\f\t\u000f\u0003\u0004\u0002\u001ae\u0001\r!\u0016\u0005\b\u0003?L\u0002\u0019AA#\u00031qWm\u001e\"m_\u000e\\\u0017J\u001c4p\u0011!\t\u0019/\u0007I\u0001\u0002\u0004q\u0014\u0001D6fKB\u0014V-\u00193M_\u000e\\\u0017\u0001\t7pG.tUm\u001e\"m_\u000e\\gi\u001c:Xe&$\u0018N\\4%I\u00164\u0017-\u001e7uIM\naC]3mK\u0006\u001cX-\u00117m\u0019>\u001c7n\u001d$peR\u000b7o\u001b\u000b\u0005\u0003W\u0014\u0019\u0001E\u0003\u0002n\u0006uXK\u0004\u0003\u0002p\u0006eh\u0002BAy\u0003ol!!a=\u000b\u0007\u0005UX(\u0001\u0004=e>|GOP\u0005\u0002g%\u0019\u00111 \u001a\u0002\u000fA\f7m[1hK&!\u0011q B\u0001\u0005\r\u0019V-\u001d\u0006\u0004\u0003w\u0014\u0004BBA\u001a7\u0001\u000710\u0001\thKR$\u0016m]6M_\u000e\\7i\\;oiR!!\u0011\u0002B\b!\r\t$1B\u0005\u0004\u0005\u001b\u0011$aA%oi\"1\u00111\u0007\u000fA\u0002m\fAa]5{KV\u0011!\u0011B\u0001\u0016O\u0016$h*^7cKJ|e-T1q\u000b:$(/[3t+\u00059\u0015aB3oiJLWm]\u000b\u0003\u0005;\u0001b!!<\u0003 \t\r\u0012\u0002\u0002B\u0011\u0005\u0003\u0011\u0001\"\u0013;fe\u0006$xN\u001d\t\u0007c\t\u0015R+!\u0012\n\u0007\t\u001d\"G\u0001\u0004UkBdWMM\u0001\fe\u0016lwN^3CY>\u001c7\u000e\u0006\u0003\u0002&\t5\u0002BBA\rA\u0001\u0007Q+A\u0003dY\u0016\f'\u000f\u0006\u0002\u0002&\u0005\u0001\"\t\\8dW&sgm\\'b]\u0006<WM\u001d\t\u0003\t\u000e\u001a\"a\t\u0019\u0015\u0005\tU\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013\u0007"
)
public class BlockInfoManager implements Logging {
   private final boolean trackingCacheVisibility;
   private final ConcurrentHashMap blockInfoWrappers;
   private final HashSet invisibleRDDBlocks;
   private final Striped locks;
   private final ConcurrentHashMap writeLocksByTask;
   private final ConcurrentHashMap readLocksByTask;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$1() {
      return BlockInfoManager$.MODULE$.$lessinit$greater$default$1();
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

   public boolean containsInvisibleRDDBlock(final RDDBlockId blockId) {
      synchronized(this.invisibleRDDBlocks){}

      boolean var3;
      try {
         var3 = this.invisibleRDDBlocks.contains(blockId);
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public boolean isRDDBlockVisible(final RDDBlockId blockId) {
      if (this.trackingCacheVisibility) {
         synchronized(this.invisibleRDDBlocks){}

         boolean var3;
         try {
            var3 = this.blockInfoWrappers.containsKey(blockId) && !this.invisibleRDDBlocks.contains(blockId);
         } catch (Throwable var5) {
            throw var5;
         }

         return var3;
      } else {
         return true;
      }
   }

   public void tryMarkBlockAsVisible(final RDDBlockId blockId) {
      if (this.trackingCacheVisibility) {
         synchronized(this.invisibleRDDBlocks){}

         try {
            this.invisibleRDDBlocks.remove(blockId);
         } catch (Throwable var4) {
            throw var4;
         }

      }
   }

   public void registerTask(final long taskAttemptId) {
      this.writeLocksByTask.putIfAbsent(BoxesRunTime.boxToLong(taskAttemptId), Collections.synchronizedSet(new java.util.HashSet()));
      this.readLocksByTask.putIfAbsent(BoxesRunTime.boxToLong(taskAttemptId), ConcurrentHashMultiset.create());
   }

   private long currentTaskAttemptId() {
      return BoxesRunTime.unboxToLong(.MODULE$.apply(TaskContext$.MODULE$.get()).map((x$1) -> BoxesRunTime.boxToLong($anonfun$currentTaskAttemptId$1(x$1))).getOrElse((JFunction0.mcJ.sp)() -> BlockInfo$.MODULE$.NON_TASK_WRITER()));
   }

   private Option acquireLock(final BlockId blockId, final boolean blocking, final Function1 f) {
      BooleanRef done = BooleanRef.create(false);
      ObjectRef result = ObjectRef.create(scala.None..MODULE$);

      while(!done.elem) {
         BlockInfoWrapper wrapper = (BlockInfoWrapper)this.blockInfoWrappers.get(blockId);
         if (wrapper == null) {
            done.elem = true;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            wrapper.withLock((info, condition) -> {
               $anonfun$acquireLock$1(f, result, done, blocking, info, condition);
               return BoxedUnit.UNIT;
            });
         }
      }

      return (Option)result.elem;
   }

   private Object blockInfo(final BlockId blockId, final Function2 f) {
      BlockInfoWrapper wrapper = (BlockInfoWrapper)this.blockInfoWrappers.get(blockId);
      if (wrapper == null) {
         throw SparkCoreErrors$.MODULE$.blockDoesNotExistError(blockId);
      } else {
         return wrapper.withLock(f);
      }
   }

   public Option lockForReading(final BlockId blockId, final boolean blocking) {
      long taskAttemptId = this.currentTaskAttemptId();
      this.logTrace((Function0)(() -> "Task " + taskAttemptId + " trying to acquire read lock for " + blockId));
      return this.acquireLock(blockId, blocking, (info) -> BoxesRunTime.boxToBoolean($anonfun$lockForReading$2(this, taskAttemptId, blockId, info)));
   }

   public boolean lockForReading$default$2() {
      return true;
   }

   public Option lockForWriting(final BlockId blockId, final boolean blocking) {
      long taskAttemptId = this.currentTaskAttemptId();
      this.logTrace((Function0)(() -> "Task " + taskAttemptId + " trying to acquire write lock for " + blockId));
      return this.acquireLock(blockId, blocking, (info) -> BoxesRunTime.boxToBoolean($anonfun$lockForWriting$2(this, taskAttemptId, blockId, info)));
   }

   public boolean lockForWriting$default$2() {
      return true;
   }

   public BlockInfo assertBlockIsLockedForWriting(final BlockId blockId) {
      long taskAttemptId = this.currentTaskAttemptId();
      return (BlockInfo)this.blockInfo(blockId, (info, x$2) -> {
         if (info.writerTask() != taskAttemptId) {
            throw SparkCoreErrors$.MODULE$.taskHasNotLockedBlockError(this.currentTaskAttemptId(), blockId);
         } else {
            return info;
         }
      });
   }

   public Option get(final BlockId blockId) {
      BlockInfoWrapper wrapper = (BlockInfoWrapper)this.blockInfoWrappers.get(blockId);
      return (Option)(wrapper != null ? new Some(wrapper.info()) : scala.None..MODULE$);
   }

   public void downgradeLock(final BlockId blockId) {
      long taskAttemptId = this.currentTaskAttemptId();
      this.logTrace((Function0)(() -> "Task " + taskAttemptId + " downgrading write lock for " + blockId));
      this.blockInfo(blockId, (info, x$3) -> {
         $anonfun$downgradeLock$2(this, taskAttemptId, blockId, info, x$3);
         return BoxedUnit.UNIT;
      });
   }

   public void unlock(final BlockId blockId, final Option taskAttemptIdOption) {
      long taskAttemptId = BoxesRunTime.unboxToLong(taskAttemptIdOption.getOrElse((JFunction0.mcJ.sp)() -> this.currentTaskAttemptId()));
      this.logTrace((Function0)(() -> "Task " + taskAttemptId + " releasing lock for " + blockId));
      this.blockInfo(blockId, (info, condition) -> {
         $anonfun$unlock$3(this, taskAttemptId, blockId, info, condition);
         return BoxedUnit.UNIT;
      });
   }

   public Option unlock$default$2() {
      return scala.None..MODULE$;
   }

   public boolean lockNewBlockForWriting(final BlockId blockId, final BlockInfo newBlockInfo, final boolean keepReadLock) {
      this.logTrace((Function0)(() -> {
         long var10000 = this.currentTaskAttemptId();
         return "Task " + var10000 + " trying to put " + blockId;
      }));
      Lock lock = (Lock)this.locks.get(blockId);
      lock.lock();

      boolean var11;
      try {
         BlockInfoWrapper wrapper = new BlockInfoWrapper(newBlockInfo, lock);

         while(true) {
            BlockInfoWrapper var10000;
            if (this.trackingCacheVisibility) {
               synchronized(this.invisibleRDDBlocks){}

               BlockInfoWrapper var8;
               try {
                  BlockInfoWrapper res = (BlockInfoWrapper)this.blockInfoWrappers.putIfAbsent(blockId, wrapper);
                  if (res == null) {
                     blockId.asRDDId().foreach((elem) -> BoxesRunTime.boxToBoolean($anonfun$lockNewBlockForWriting$2(this, elem)));
                  }

                  var8 = res;
               } catch (Throwable var17) {
                  throw var17;
               }

               var10000 = var8;
            } else {
               var10000 = (BlockInfoWrapper)this.blockInfoWrappers.putIfAbsent(blockId, wrapper);
            }

            BlockInfoWrapper previous = var10000;
            if (previous == null) {
               Option result = this.lockForWriting(blockId, false);
               scala.Predef..MODULE$.assert(result.isDefined());
               var11 = true;
               break;
            }

            if (!keepReadLock) {
               var11 = false;
               break;
            }

            if (this.lockForReading(blockId, this.lockForReading$default$2()).isDefined()) {
               var11 = false;
               break;
            }
         }
      } finally {
         lock.unlock();
      }

      return var11;
   }

   public boolean lockNewBlockForWriting$default$3() {
      return true;
   }

   public Seq releaseAllLocksForTask(final long taskAttemptId) {
      ArrayBuffer blocksWithReleasedLocks = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Set writeLocks = (Set).MODULE$.apply(this.writeLocksByTask.remove(BoxesRunTime.boxToLong(taskAttemptId))).getOrElse(() -> Collections.emptySet());
      writeLocks.forEach((blockId) -> {
         this.blockInfo(blockId, (info, condition) -> {
            $anonfun$releaseAllLocksForTask$3(taskAttemptId, info, condition);
            return BoxedUnit.UNIT;
         });
         blocksWithReleasedLocks.$plus$eq(blockId);
      });
      AbstractCollection readLocks = (AbstractCollection).MODULE$.apply(this.readLocksByTask.remove(BoxesRunTime.boxToLong(taskAttemptId))).getOrElse(() -> ImmutableMultiset.of());
      ((Multiset)readLocks).entrySet().forEach((entry) -> {
         BlockId blockId = (BlockId)entry.getElement();
         int lockCount = entry.getCount();
         blocksWithReleasedLocks.$plus$eq(blockId);
         this.blockInfo(blockId, (info, condition) -> {
            $anonfun$releaseAllLocksForTask$6(lockCount, info, condition);
            return BoxedUnit.UNIT;
         });
      });
      return blocksWithReleasedLocks.toSeq();
   }

   public int getTaskLockCount(final long taskAttemptId) {
      return BoxesRunTime.unboxToInt(.MODULE$.apply(this.readLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId))).map((x$4) -> BoxesRunTime.boxToInteger($anonfun$getTaskLockCount$1(x$4))).getOrElse((JFunction0.mcI.sp)() -> 0)) + BoxesRunTime.unboxToInt(.MODULE$.apply(this.writeLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId))).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$getTaskLockCount$3(x$5))).getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   public int size() {
      return this.blockInfoWrappers.size();
   }

   public long getNumberOfMapEntries() {
      return (long)(this.size() + this.readLocksByTask.size() + BoxesRunTime.unboxToInt(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.readLocksByTask).asScala().map((x$6) -> BoxesRunTime.boxToInteger($anonfun$getNumberOfMapEntries$1(x$6)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$)) + this.writeLocksByTask.size() + BoxesRunTime.unboxToInt(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.writeLocksByTask).asScala().map((x$7) -> BoxesRunTime.boxToInteger($anonfun$getNumberOfMapEntries$2(x$7)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$)));
   }

   public Iterator entries() {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(this.blockInfoWrappers.entrySet().iterator()).asScala().map((kv) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(kv.getKey()), ((BlockInfoWrapper)kv.getValue()).info()));
   }

   public void removeBlock(final BlockId blockId) {
      long taskAttemptId = this.currentTaskAttemptId();
      this.logTrace((Function0)(() -> "Task " + taskAttemptId + " trying to remove block " + blockId));
      this.blockInfo(blockId, (info, condition) -> {
         $anonfun$removeBlock$2(this, taskAttemptId, blockId, info, condition);
         return BoxedUnit.UNIT;
      });
   }

   public void clear() {
      this.blockInfoWrappers.values().forEach((wrapper) -> wrapper.tryLock((info, condition) -> {
            $anonfun$clear$2(info, condition);
            return BoxedUnit.UNIT;
         }));
      this.blockInfoWrappers.clear();
      this.readLocksByTask.clear();
      this.writeLocksByTask.clear();
      synchronized(this.invisibleRDDBlocks){}

      try {
         this.invisibleRDDBlocks.clear();
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final long $anonfun$currentTaskAttemptId$1(final TaskContext x$1) {
      return x$1.taskAttemptId();
   }

   // $FF: synthetic method
   public static final void $anonfun$acquireLock$1(final Function1 f$1, final ObjectRef result$1, final BooleanRef done$1, final boolean blocking$1, final BlockInfo info, final Condition condition) {
      if (BoxesRunTime.unboxToBoolean(f$1.apply(info))) {
         result$1.elem = new Some(info);
         done$1.elem = true;
      } else if (!blocking$1) {
         done$1.elem = true;
      } else {
         condition.await();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$lockForReading$2(final BlockInfoManager $this, final long taskAttemptId$1, final BlockId blockId$1, final BlockInfo info) {
      boolean acquire = info.writerTask() == BlockInfo$.MODULE$.NO_WRITER();
      if (acquire) {
         info.readerCount_$eq(info.readerCount() + 1);
         ((ConcurrentHashMultiset)$this.readLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId$1))).add(blockId$1);
         $this.logTrace((Function0)(() -> "Task " + taskAttemptId$1 + " acquired read lock for " + blockId$1));
      }

      return acquire;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$lockForWriting$2(final BlockInfoManager $this, final long taskAttemptId$2, final BlockId blockId$2, final BlockInfo info) {
      boolean acquire = info.writerTask() == BlockInfo$.MODULE$.NO_WRITER() && info.readerCount() == 0;
      if (acquire) {
         info.writerTask_$eq(taskAttemptId$2);
         ((Set)$this.writeLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId$2))).add(blockId$2);
         $this.logTrace((Function0)(() -> "Task " + taskAttemptId$2 + " acquired write lock for " + blockId$2));
      }

      return acquire;
   }

   // $FF: synthetic method
   public static final void $anonfun$downgradeLock$2(final BlockInfoManager $this, final long taskAttemptId$4, final BlockId blockId$4, final BlockInfo info, final Condition x$3) {
      scala.Predef..MODULE$.require(info.writerTask() == taskAttemptId$4, () -> "Task " + taskAttemptId$4 + " tried to downgrade a write lock that it does not hold on block " + blockId$4);
      $this.unlock(blockId$4, $this.unlock$default$2());
      Option lockOutcome = $this.lockForReading(blockId$4, false);
      scala.Predef..MODULE$.assert(lockOutcome.isDefined());
   }

   // $FF: synthetic method
   public static final void $anonfun$unlock$3(final BlockInfoManager $this, final long taskAttemptId$5, final BlockId blockId$5, final BlockInfo info, final Condition condition) {
      if (info.writerTask() != BlockInfo$.MODULE$.NO_WRITER()) {
         info.writerTask_$eq(BlockInfo$.MODULE$.NO_WRITER());
         BoxesRunTime.boxToBoolean(((Set)$this.writeLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId$5))).remove(blockId$5));
      } else {
         ConcurrentHashMultiset countsForTask = (ConcurrentHashMultiset)$this.readLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId$5));
         if (countsForTask != null) {
            scala.Predef..MODULE$.assert(info.readerCount() > 0, () -> "Block " + blockId$5 + " is not locked for reading");
            info.readerCount_$eq(info.readerCount() - 1);
            int newPinCountForTask = countsForTask.remove(blockId$5, 1) - 1;
            scala.Predef..MODULE$.assert(newPinCountForTask >= 0, () -> "Task " + taskAttemptId$5 + " release lock on block " + blockId$5 + " more times than it acquired it");
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      condition.signalAll();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$lockNewBlockForWriting$2(final BlockInfoManager $this, final RDDBlockId elem) {
      return $this.invisibleRDDBlocks.add(elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$releaseAllLocksForTask$3(final long taskAttemptId$6, final BlockInfo info, final Condition condition) {
      scala.Predef..MODULE$.assert(info.writerTask() == taskAttemptId$6);
      info.writerTask_$eq(BlockInfo$.MODULE$.NO_WRITER());
      condition.signalAll();
   }

   // $FF: synthetic method
   public static final void $anonfun$releaseAllLocksForTask$6(final int lockCount$1, final BlockInfo info, final Condition condition) {
      info.readerCount_$eq(info.readerCount() - lockCount$1);
      scala.Predef..MODULE$.assert(info.readerCount() >= 0);
      condition.signalAll();
   }

   // $FF: synthetic method
   public static final int $anonfun$getTaskLockCount$1(final ConcurrentHashMultiset x$4) {
      return x$4.size();
   }

   // $FF: synthetic method
   public static final int $anonfun$getTaskLockCount$3(final Set x$5) {
      return x$5.size();
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumberOfMapEntries$1(final Tuple2 x$6) {
      return ((ConcurrentHashMultiset)x$6._2()).size();
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumberOfMapEntries$2(final Tuple2 x$7) {
      return ((Set)x$7._2()).size();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeBlock$3(final BlockInfoManager $this, final RDDBlockId elem) {
      return $this.invisibleRDDBlocks.remove(elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeBlock$2(final BlockInfoManager $this, final long taskAttemptId$7, final BlockId blockId$7, final BlockInfo info, final Condition condition) {
      if (info.writerTask() != taskAttemptId$7) {
         throw org.apache.spark.SparkException..MODULE$.internalError("Task " + taskAttemptId$7 + " called remove() on block " + blockId$7 + " without a write lock", "STORAGE");
      } else {
         synchronized($this.invisibleRDDBlocks){}

         try {
            $this.blockInfoWrappers.remove(blockId$7);
            blockId$7.asRDDId().foreach((elem) -> BoxesRunTime.boxToBoolean($anonfun$removeBlock$3($this, elem)));
         } catch (Throwable var8) {
            throw var8;
         }

         info.readerCount_$eq(0);
         info.writerTask_$eq(BlockInfo$.MODULE$.NO_WRITER());
         ((Set)$this.writeLocksByTask.get(BoxesRunTime.boxToLong(taskAttemptId$7))).remove(blockId$7);
         condition.signalAll();
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$clear$2(final BlockInfo info, final Condition condition) {
      info.readerCount_$eq(0);
      info.writerTask_$eq(BlockInfo$.MODULE$.NO_WRITER());
      condition.signalAll();
   }

   public BlockInfoManager(final boolean trackingCacheVisibility) {
      this.trackingCacheVisibility = trackingCacheVisibility;
      Logging.$init$(this);
      this.blockInfoWrappers = new ConcurrentHashMap();
      this.invisibleRDDBlocks = new HashSet();
      this.locks = Striped.lock(1024);
      this.writeLocksByTask = new ConcurrentHashMap();
      this.readLocksByTask = new ConcurrentHashMap();
      this.registerTask(BlockInfo$.MODULE$.NON_TASK_WRITER());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
