package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}f!B\u0015+\u00011\u0012\u0004\u0002C\"\u0001\u0005\u000b\u0007I\u0011A#\t\u0011E\u0003!\u0011!Q\u0001\n\u0019C\u0001B\u0015\u0001\u0003\u0006\u0004%\ta\u0015\u0005\tG\u0002\u0011\t\u0011)A\u0005)\"AA\r\u0001B\u0001B\u0003%Q\r\u0003\u0005i\u0001\t\u0005\t\u0015!\u0003f\u0011\u0015I\u0007\u0001\"\u0001k\u0011\u001d\u0001\bA1A\u0005\u0002EDa\u0001 \u0001!\u0002\u0013\u0011\bbB?\u0001\u0005\u0004%\tA \u0005\b\u0003\u000b\u0001\u0001\u0015!\u0003\u0000\u0011%\t9\u0001\u0001b\u0001\n\u0003\tI\u0001C\u0004\u0002\f\u0001\u0001\u000b\u0011B3\t\u0013\u00055\u0001A1A\u0005\u0002\u0005%\u0001bBA\b\u0001\u0001\u0006I!\u001a\u0005\n\u0003#\u0001\u0001\u0019!C\u0001\u0003\u0013A\u0011\"a\u0005\u0001\u0001\u0004%\t!!\u0006\t\u000f\u0005\u0005\u0002\u0001)Q\u0005K\"I\u00111\u0005\u0001C\u0002\u0013\u0005\u0011\u0011\u0002\u0005\b\u0003K\u0001\u0001\u0015!\u0003f\u0011%\t9\u0003\u0001a\u0001\n\u0003\tI\u0001C\u0005\u0002*\u0001\u0001\r\u0011\"\u0001\u0002,!9\u0011q\u0006\u0001!B\u0013)\u0007\u0002CA\u0019\u0001\t\u0007I\u0011A#\t\u000f\u0005M\u0002\u0001)A\u0005\r\"I\u0011Q\u0007\u0001A\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003s\u0001\u0001\u0019!C\u0001\u0003wAq!a\u0010\u0001A\u0003&1\u000eC\u0005\u0002B\u0001\u0011\r\u0011\"\u0003\u0002D!A\u00111\n\u0001!\u0002\u0013\t)\u0005C\u0004\u0002N\u0001!\t%a\u0014\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z!9\u0011q\f\u0001\u0005B\u0005\u0005\u0004bBA3\u0001\u0011\u0005\u0013q\r\u0005\b\u0003[\u0002A\u0011IA8\u0011\u001d\t\u0019\t\u0001C!\u0003\u000bCq!!#\u0001\t\u0003\nY\tC\u0004\u0002\u0018\u0002!\t%!'\t\u000f\u0005E\u0006\u0001\"\u0001\u00024\"9\u0011\u0011\u0018\u0001\u0005\u0002\u0005m&\u0001\u0002)p_2T!a\u000b\u0017\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0017/\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0003'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002c\u0005\u0019qN]4\u0014\t\u0001\u0019\u0014(\u0010\t\u0003i]j\u0011!\u000e\u0006\u0002m\u0005)1oY1mC&\u0011\u0001(\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iZT\"\u0001\u0016\n\u0005qR#aC*dQ\u0016$W\u000f\\1cY\u0016\u0004\"AP!\u000e\u0003}R!\u0001\u0011\u0017\u0002\u0011%tG/\u001a:oC2L!AQ \u0003\u000f1{wmZ5oO\u0006A\u0001o\\8m\u001d\u0006lWm\u0001\u0001\u0016\u0003\u0019\u0003\"a\u0012(\u000f\u0005!c\u0005CA%6\u001b\u0005Q%BA&E\u0003\u0019a$o\\8u}%\u0011Q*N\u0001\u0007!J,G-\u001a4\n\u0005=\u0003&AB*ue&twM\u0003\u0002Nk\u0005I\u0001o\\8m\u001d\u0006lW\rI\u0001\u000fg\u000eDW\rZ;mS:<Wj\u001c3f+\u0005!\u0006CA+a\u001d\t1fL\u0004\u0002X;:\u0011\u0001\f\u0018\b\u00033ns!!\u0013.\n\u0003EJ!a\f\u0019\n\u00055r\u0013BA\u0016-\u0013\ty&&\u0001\bTG\",G-\u001e7j]\u001elu\u000eZ3\n\u0005\u0005\u0014'AD*dQ\u0016$W\u000f\\5oO6{G-\u001a\u0006\u0003?*\nqb]2iK\u0012,H.\u001b8h\u001b>$W\rI\u0001\rS:LG/T5o'\"\f'/\u001a\t\u0003i\u0019L!aZ\u001b\u0003\u0007%sG/\u0001\u0006j]&$x+Z5hQR\fa\u0001P5oSRtD#B6m[:|\u0007C\u0001\u001e\u0001\u0011\u0015\u0019u\u00011\u0001G\u0011\u0015\u0011v\u00011\u0001U\u0011\u0015!w\u00011\u0001f\u0011\u0015Aw\u00011\u0001f\u0003A\u00198\r[3ek2\f'\r\\3Rk\u0016,X-F\u0001s!\r\u0019(0O\u0007\u0002i*\u0011QO^\u0001\u000bG>t7-\u001e:sK:$(BA<y\u0003\u0011)H/\u001b7\u000b\u0003e\fAA[1wC&\u00111\u0010\u001e\u0002\u0016\u0007>t7-\u001e:sK:$H*\u001b8lK\u0012\fV/Z;f\u0003E\u00198\r[3ek2\f'\r\\3Rk\u0016,X\rI\u0001\u001dg\u000eDW\rZ;mC\ndWMT1nKR{7k\u00195fIVd\u0017M\u00197f+\u0005y\b#B:\u0002\u0002\u0019K\u0014bAA\u0002i\n\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0002;M\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a(b[\u0016$vnU2iK\u0012,H.\u00192mK\u0002\naa^3jO\"$X#A3\u0002\u000f],\u0017n\u001a5uA\u0005AQ.\u001b8TQ\u0006\u0014X-A\u0005nS:\u001c\u0006.\u0019:fA\u0005a!/\u001e8oS:<G+Y:lg\u0006\u0001\"/\u001e8oS:<G+Y:lg~#S-\u001d\u000b\u0005\u0003/\ti\u0002E\u00025\u00033I1!a\u00076\u0005\u0011)f.\u001b;\t\u0011\u0005}\u0011#!AA\u0002\u0015\f1\u0001\u001f\u00132\u00035\u0011XO\u001c8j]\u001e$\u0016m]6tA\u0005A\u0001O]5pe&$\u00180A\u0005qe&|'/\u001b;zA\u000591\u000f^1hK&#\u0017aC:uC\u001e,\u0017\nZ0%KF$B!a\u0006\u0002.!A\u0011q\u0004\f\u0002\u0002\u0003\u0007Q-\u0001\u0005ti\u0006<W-\u00133!\u0003\u0011q\u0017-\\3\u0002\u000b9\fW.\u001a\u0011\u0002\rA\f'/\u001a8u+\u0005Y\u0017A\u00039be\u0016tGo\u0018\u0013fcR!\u0011qCA\u001f\u0011!\tybGA\u0001\u0002\u0004Y\u0017a\u00029be\u0016tG\u000fI\u0001\u001bi\u0006\u001c8nU3u'\u000eDW\rZ;mS:<\u0017\t\\4pe&$\b.\\\u000b\u0003\u0003\u000b\u00022AOA$\u0013\r\tIE\u000b\u0002\u0014'\u000eDW\rZ;mS:<\u0017\t\\4pe&$\b.\\\u0001\u001ci\u0006\u001c8nU3u'\u000eDW\rZ;mS:<\u0017\t\\4pe&$\b.\u001c\u0011\u0002\u001b%\u001c8k\u00195fIVd\u0017M\u00197f+\t\t\t\u0006E\u00025\u0003'J1!!\u00166\u0005\u001d\u0011un\u001c7fC:\fa\"\u00193e'\u000eDW\rZ;mC\ndW\r\u0006\u0003\u0002\u0018\u0005m\u0003BBA/A\u0001\u0007\u0011(A\u0006tG\",G-\u001e7bE2,\u0017!\u0005:f[>4XmU2iK\u0012,H.\u00192mKR!\u0011qCA2\u0011\u0019\ti&\ta\u0001s\u0005!r-\u001a;TG\",G-\u001e7bE2,')\u001f(b[\u0016$2!OA5\u0011\u0019\tYG\ta\u0001\r\u0006y1o\u00195fIVd\u0017M\u00197f\u001d\u0006lW-\u0001\u0007fq\u0016\u001cW\u000f^8s\u0019>\u001cH\u000f\u0006\u0005\u0002\u0018\u0005E\u0014QOA=\u0011\u0019\t\u0019h\ta\u0001\r\u0006QQ\r_3dkR|'/\u00133\t\r\u0005]4\u00051\u0001G\u0003\u0011Awn\u001d;\t\u000f\u0005m4\u00051\u0001\u0002~\u00051!/Z1t_:\u00042AOA@\u0013\r\t\tI\u000b\u0002\u0013\u000bb,7-\u001e;pe2{7o\u001d*fCN|g.\u0001\u000bfq\u0016\u001cW\u000f^8s\t\u0016\u001cw.\\7jgNLwN\u001c\u000b\u0005\u0003/\t9\t\u0003\u0004\u0002t\u0011\u0002\rAR\u0001\u0017G\",7m[*qK\u000e,H.\u0019;bE2,G+Y:lgR!\u0011\u0011KAG\u0011\u001d\ty)\na\u0001\u0003#\u000bA#\\5o)&lW\rV8Ta\u0016\u001cW\u000f\\1uS>t\u0007c\u0001\u001b\u0002\u0014&\u0019\u0011QS\u001b\u0003\t1{gnZ\u0001\u0016O\u0016$8k\u001c:uK\u0012$\u0016m]6TKR\fV/Z;f+\t\tY\n\u0005\u0004\u0002\u001e\u0006\u001d\u00161V\u0007\u0003\u0003?SA!!)\u0002$\u00069Q.\u001e;bE2,'bAASk\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005%\u0016q\u0014\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000fE\u0002;\u0003[K1!a,+\u00059!\u0016m]6TKRl\u0015M\\1hKJ\fA#\u001b8de\u0016\f7/\u001a*v]:Lgn\u001a+bg.\u001cH\u0003BA\f\u0003kCa!a.(\u0001\u0004)\u0017a\u0002;bg.tU/\\\u0001\u0015I\u0016\u001c'/Z1tKJ+hN\\5oOR\u000b7o[:\u0015\t\u0005]\u0011Q\u0018\u0005\u0007\u0003oC\u0003\u0019A3"
)
public class Pool implements Schedulable, Logging {
   private final String poolName;
   private final Enumeration.Value schedulingMode;
   private final ConcurrentLinkedQueue schedulableQueue;
   private final ConcurrentHashMap schedulableNameToSchedulable;
   private final int weight;
   private final int minShare;
   private int runningTasks;
   private final int priority;
   private int stageId;
   private final String name;
   private Pool parent;
   private final SchedulingAlgorithm taskSetSchedulingAlgorithm;
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

   public String poolName() {
      return this.poolName;
   }

   public Enumeration.Value schedulingMode() {
      return this.schedulingMode;
   }

   public ConcurrentLinkedQueue schedulableQueue() {
      return this.schedulableQueue;
   }

   public ConcurrentHashMap schedulableNameToSchedulable() {
      return this.schedulableNameToSchedulable;
   }

   public int weight() {
      return this.weight;
   }

   public int minShare() {
      return this.minShare;
   }

   public int runningTasks() {
      return this.runningTasks;
   }

   public void runningTasks_$eq(final int x$1) {
      this.runningTasks = x$1;
   }

   public int priority() {
      return this.priority;
   }

   public int stageId() {
      return this.stageId;
   }

   public void stageId_$eq(final int x$1) {
      this.stageId = x$1;
   }

   public String name() {
      return this.name;
   }

   public Pool parent() {
      return this.parent;
   }

   public void parent_$eq(final Pool x$1) {
      this.parent = x$1;
   }

   private SchedulingAlgorithm taskSetSchedulingAlgorithm() {
      return this.taskSetSchedulingAlgorithm;
   }

   public boolean isSchedulable() {
      return true;
   }

   public void addSchedulable(final Schedulable schedulable) {
      .MODULE$.require(schedulable != null);
      this.schedulableQueue().add(schedulable);
      this.schedulableNameToSchedulable().put(schedulable.name(), schedulable);
      schedulable.parent_$eq(this);
   }

   public void removeSchedulable(final Schedulable schedulable) {
      this.schedulableQueue().remove(schedulable);
      this.schedulableNameToSchedulable().remove(schedulable.name());
   }

   public Schedulable getSchedulableByName(final String schedulableName) {
      Object var2 = new Object();

      Schedulable var10000;
      try {
         if (this.schedulableNameToSchedulable().containsKey(schedulableName)) {
            return (Schedulable)this.schedulableNameToSchedulable().get(schedulableName);
         }

         scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.schedulableQueue()).asScala().foreach((schedulable) -> {
            $anonfun$getSchedulableByName$1(schedulableName, var2, schedulable);
            return BoxedUnit.UNIT;
         });
         var10000 = null;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = (Schedulable)var4.value();
      }

      return var10000;
   }

   public void executorLost(final String executorId, final String host, final ExecutorLossReason reason) {
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.schedulableQueue()).asScala().foreach((x$1) -> {
         $anonfun$executorLost$1(executorId, host, reason, x$1);
         return BoxedUnit.UNIT;
      });
   }

   public void executorDecommission(final String executorId) {
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.schedulableQueue()).asScala().foreach((x$2) -> {
         $anonfun$executorDecommission$1(executorId, x$2);
         return BoxedUnit.UNIT;
      });
   }

   public boolean checkSpeculatableTasks(final long minTimeToSpeculation) {
      BooleanRef shouldRevive = BooleanRef.create(false);
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.schedulableQueue()).asScala().foreach((schedulable) -> {
         $anonfun$checkSpeculatableTasks$1(shouldRevive, minTimeToSpeculation, schedulable);
         return BoxedUnit.UNIT;
      });
      return shouldRevive.elem;
   }

   public ArrayBuffer getSortedTaskSetQueue() {
      ArrayBuffer sortedTaskSetQueue = new ArrayBuffer();
      Seq sortedSchedulableQueue = (Seq)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.schedulableQueue()).asScala().toSeq().sortWith((s1, s2) -> BoxesRunTime.boxToBoolean($anonfun$getSortedTaskSetQueue$1(this, s1, s2)));
      sortedSchedulableQueue.foreach((schedulable) -> (ArrayBuffer)sortedTaskSetQueue.$plus$plus$eq((IterableOnce)schedulable.getSortedTaskSetQueue().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getSortedTaskSetQueue$3(x$3)))));
      return sortedTaskSetQueue;
   }

   public void increaseRunningTasks(final int taskNum) {
      this.runningTasks_$eq(this.runningTasks() + taskNum);
      if (this.parent() != null) {
         this.parent().increaseRunningTasks(taskNum);
      }
   }

   public void decreaseRunningTasks(final int taskNum) {
      this.runningTasks_$eq(this.runningTasks() - taskNum);
      if (this.parent() != null) {
         this.parent().decreaseRunningTasks(taskNum);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$getSchedulableByName$1(final String schedulableName$1, final Object nonLocalReturnKey1$1, final Schedulable schedulable) {
      Schedulable sched = schedulable.getSchedulableByName(schedulableName$1);
      if (sched != null) {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, sched);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executorLost$1(final String executorId$1, final String host$1, final ExecutorLossReason reason$1, final Schedulable x$1) {
      x$1.executorLost(executorId$1, host$1, reason$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$executorDecommission$1(final String executorId$2, final Schedulable x$2) {
      x$2.executorDecommission(executorId$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$checkSpeculatableTasks$1(final BooleanRef shouldRevive$1, final long minTimeToSpeculation$1, final Schedulable schedulable) {
      shouldRevive$1.elem |= schedulable.checkSpeculatableTasks(minTimeToSpeculation$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSortedTaskSetQueue$1(final Pool $this, final Schedulable s1, final Schedulable s2) {
      return $this.taskSetSchedulingAlgorithm().comparator(s1, s2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSortedTaskSetQueue$3(final TaskSetManager x$3) {
      return x$3.isSchedulable();
   }

   public Pool(final String poolName, final Enumeration.Value schedulingMode, final int initMinShare, final int initWeight) {
      label30: {
         Object var11;
         label29: {
            label32: {
               this.poolName = poolName;
               this.schedulingMode = schedulingMode;
               super();
               Logging.$init$(this);
               this.schedulableQueue = new ConcurrentLinkedQueue();
               this.schedulableNameToSchedulable = new ConcurrentHashMap();
               this.weight = initWeight;
               this.minShare = initMinShare;
               this.runningTasks = 0;
               this.priority = 0;
               this.stageId = -1;
               this.name = poolName;
               this.parent = null;
               Enumeration.Value var10001 = SchedulingMode$.MODULE$.FAIR();
               if (var10001 == null) {
                  if (schedulingMode == null) {
                     break label32;
                  }
               } else if (var10001.equals(schedulingMode)) {
                  break label32;
               }

               var10001 = SchedulingMode$.MODULE$.FIFO();
               if (var10001 == null) {
                  if (schedulingMode != null) {
                     break label30;
                  }
               } else if (!var10001.equals(schedulingMode)) {
                  break label30;
               }

               var11 = new FIFOSchedulingAlgorithm();
               break label29;
            }

            var11 = new FairSchedulingAlgorithm();
         }

         this.taskSetSchedulingAlgorithm = (SchedulingAlgorithm)var11;
         return;
      }

      String msg = "Unsupported scheduling mode: " + schedulingMode + ". Use FAIR or FIFO instead.";
      throw new IllegalArgumentException(msg);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
