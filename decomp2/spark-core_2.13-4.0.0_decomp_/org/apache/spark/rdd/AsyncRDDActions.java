package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.ComplexFutureAction;
import org.apache.spark.FutureAction;
import org.apache.spark.JobSubmitter;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.CallSite;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.Range;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b\u0001B\b\u0011\u0001eA\u0001b\r\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\t\u0007\u0002\u0011\u0019\u0011)A\u0006\t\")!\n\u0001C\u0001\u0017\")\u0001\u000b\u0001C\u0001#\")\u0011\f\u0001C\u00015\")q\f\u0001C\u0001A\")a\r\u0001C\u0001O\")\u0011\u000f\u0001C\u0001e\u001e)\u0001\u0010\u0005E\u0005s\u001a)q\u0002\u0005E\u0005u\"1!J\u0003C\u0001\u0003\u000bA\u0011\"a\u0002\u000b\u0005\u0004%\t!!\u0003\t\u0011\u0005]!\u0002)A\u0005\u0003\u0017A\u0011\"!\u0007\u000b\u0003\u0003%I!a\u0007\u0003\u001f\u0005\u001b\u0018P\\2S\t\u0012\u000b5\r^5p]NT!!\u0005\n\u0002\u0007I$GM\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h\u0007\u0001)\"A\u0007\u001e\u0014\t\u0001Y\u0012%\f\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\tRcBA\u0012)\u001d\t!s%D\u0001&\u0015\t1\u0003$\u0001\u0004=e>|GOP\u0005\u0002=%\u0011\u0011&H\u0001\ba\u0006\u001c7.Y4f\u0013\tYCF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002*;A\u0011a&M\u0007\u0002_)\u0011\u0001GE\u0001\tS:$XM\u001d8bY&\u0011!g\f\u0002\b\u0019><w-\u001b8h\u0003\u0011\u0019X\r\u001c4\u0011\u0007U2\u0004(D\u0001\u0011\u0013\t9\u0004CA\u0002S\t\u0012\u0003\"!\u000f\u001e\r\u0001\u0011)1\b\u0001b\u0001y\t\tA+\u0005\u0002>\u0001B\u0011ADP\u0005\u0003\u007fu\u0011qAT8uQ&tw\r\u0005\u0002\u001d\u0003&\u0011!)\b\u0002\u0004\u0003:L\u0018AC3wS\u0012,gnY3%cA\u0019Q\t\u0013\u001d\u000e\u0003\u0019S!aR\u000f\u0002\u000fI,g\r\\3di&\u0011\u0011J\u0012\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"\"\u0001T(\u0015\u00055s\u0005cA\u001b\u0001q!)1i\u0001a\u0002\t\")1g\u0001a\u0001i\u0005Q1m\\;oi\u0006\u001b\u0018P\\2\u0015\u0003I\u00032a\u0015+W\u001b\u0005\u0011\u0012BA+\u0013\u000511U\u000f^;sK\u0006\u001bG/[8o!\tar+\u0003\u0002Y;\t!Aj\u001c8h\u00031\u0019w\u000e\u001c7fGR\f5/\u001f8d)\u0005Y\u0006cA*U9B\u0019!%\u0018\u001d\n\u0005yc#aA*fc\u0006IA/Y6f\u0003NLhn\u0019\u000b\u00037\u0006DQA\u0019\u0004A\u0002\r\f1A\\;n!\taB-\u0003\u0002f;\t\u0019\u0011J\u001c;\u0002\u0019\u0019|'/Z1dQ\u0006\u001b\u0018P\\2\u0015\u0005!d\u0007cA*USB\u0011AD[\u0005\u0003Wv\u0011A!\u00168ji\")Qn\u0002a\u0001]\u0006\ta\r\u0005\u0003\u001d_bJ\u0017B\u00019\u001e\u0005%1UO\\2uS>t\u0017'A\u000bg_J,\u0017m\u00195QCJ$\u0018\u000e^5p]\u0006\u001b\u0018P\\2\u0015\u0005!\u001c\b\"B7\t\u0001\u0004!\b\u0003\u0002\u000fpk&\u00042A\t<9\u0013\t9HF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003=\t5/\u001f8d%\u0012#\u0015i\u0019;j_:\u001c\bCA\u001b\u000b'\rQ1d\u001f\t\u0004y\u0006\rQ\"A?\u000b\u0005y|\u0018AA5p\u0015\t\t\t!\u0001\u0003kCZ\f\u0017BA\u0016~)\u0005I\u0018A\u00064viV\u0014X-\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;\u0016\u0005\u0005-\u0001\u0003BA\u0007\u0003'i!!a\u0004\u000b\u0007\u0005EQ$\u0001\u0006d_:\u001cWO\u001d:f]RLA!!\u0006\u0002\u0010\tyR\t_3dkRLwN\\\"p]R,\u0007\u0010^#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002/\u0019,H/\u001e:f\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u000f!\u0011\ty\"!\n\u000e\u0005\u0005\u0005\"bAA\u0012\u007f\u0006!A.\u00198h\u0013\u0011\t9#!\t\u0003\r=\u0013'.Z2u\u0001"
)
public class AsyncRDDActions implements Serializable, Logging {
   private final RDD self;
   private final ClassTag evidence$1;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static ExecutionContextExecutorService futureExecutionContext() {
      return AsyncRDDActions$.MODULE$.futureExecutionContext();
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

   public FutureAction countAsync() {
      return (FutureAction)this.self.withScope(() -> {
         AtomicLong totalCount = new AtomicLong();
         return this.self.context().submitJob(this.self, (iter) -> BoxesRunTime.boxToLong($anonfun$countAsync$2(iter)), .MODULE$.Range().apply(0, this.self.partitions().length), (JFunction2.mcVIJ.sp)(index, data) -> totalCount.addAndGet(data), (JFunction0.mcJ.sp)() -> totalCount.get());
      });
   }

   public FutureAction collectAsync() {
      return (FutureAction)this.self.withScope(() -> {
         Object[] results = scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(this.evidence$1.runtimeClass())).newArray(this.self.partitions().length);
         return this.self.context().submitJob(this.self, (x$1) -> x$1.toArray(this.evidence$1), .MODULE$.Range().apply(0, this.self.partitions().length), (index, data) -> {
            $anonfun$collectAsync$3(results, BoxesRunTime.unboxToInt(index), data);
            return BoxedUnit.UNIT;
         }, () -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(results), (xs) -> scala.Predef..MODULE$.genericWrapArray(xs), this.evidence$1)).toImmutableArraySeq());
      });
   }

   public FutureAction takeAsync(final int num) {
      return (FutureAction)this.self.withScope(() -> {
         CallSite callSite = this.self.context().getCallSite();
         Properties localProperties = this.self.context().getLocalProperties();
         ExecutionContextExecutorService executionContext = AsyncRDDActions$.MODULE$.futureExecutionContext();
         ArrayBuffer results = new ArrayBuffer();
         int totalParts = this.self.partitions().length;
         int scaleUpFactor = Math.max(BoxesRunTime.unboxToInt(this.self.conf().get(org.apache.spark.internal.config.package$.MODULE$.RDD_LIMIT_SCALE_UP_FACTOR())), 2);
         return new ComplexFutureAction((x$4) -> this.continue$1(0, x$4, results, num, totalParts, scaleUpFactor, callSite, localProperties, executionContext));
      });
   }

   public FutureAction foreachAsync(final Function1 f) {
      return (FutureAction)this.self.withScope(() -> {
         SparkContext qual$1 = this.self.context();
         boolean x$2 = qual$1.clean$default$2();
         Function1 cleanF = (Function1)qual$1.clean(f, x$2);
         return this.self.context().submitJob(this.self, (x$5) -> {
            $anonfun$foreachAsync$2(cleanF, x$5);
            return BoxedUnit.UNIT;
         }, .MODULE$.Range().apply(0, this.self.partitions().length), (index, data) -> {
            $anonfun$foreachAsync$3(BoxesRunTime.unboxToInt(index), data);
            return BoxedUnit.UNIT;
         }, (JFunction0.mcV.sp)() -> {
         });
      });
   }

   public FutureAction foreachPartitionAsync(final Function1 f) {
      return (FutureAction)this.self.withScope(() -> this.self.context().submitJob(this.self, f, .MODULE$.Range().apply(0, this.self.partitions().length), (index, data) -> {
            $anonfun$foreachPartitionAsync$2(BoxesRunTime.unboxToInt(index), data);
            return BoxedUnit.UNIT;
         }, (JFunction0.mcV.sp)() -> {
         }));
   }

   // $FF: synthetic method
   public static final long $anonfun$countAsync$2(final Iterator iter) {
      long result = 0L;

      while(iter.hasNext()) {
         ++result;
         iter.next();
      }

      return result;
   }

   // $FF: synthetic method
   public static final void $anonfun$collectAsync$3(final Object[] results$1, final int index, final Object data) {
      results$1[index] = data;
   }

   // $FF: synthetic method
   public static final void $anonfun$takeAsync$3(final Object[] buf$1, final int index, final Object data) {
      buf$1[index] = data;
   }

   private final Future continue$1(final int partsScanned, final JobSubmitter jobSubmitter, final ArrayBuffer results$2, final int num$1, final int totalParts$1, final int scaleUpFactor$1, final CallSite callSite$1, final Properties localProperties$1, final ExecutionContextExecutorService executionContext$1) {
      if (results$2.size() < num$1 && partsScanned < totalParts$1) {
         int numPartsToTry = BoxesRunTime.unboxToInt(this.self.conf().get(org.apache.spark.internal.config.package$.MODULE$.RDD_LIMIT_INITIAL_NUM_PARTITIONS()));
         if (partsScanned > 0) {
            if (results$2.isEmpty()) {
               numPartsToTry = partsScanned * scaleUpFactor$1;
            } else {
               numPartsToTry = Math.max(1, (int)((double)1.5F * (double)num$1 * (double)partsScanned / (double)results$2.size()) - partsScanned);
               numPartsToTry = Math.min(numPartsToTry, partsScanned * scaleUpFactor$1);
            }
         }

         int left = num$1 - results$2.size();
         Range p = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(partsScanned), scala.math.package..MODULE$.min(partsScanned + numPartsToTry, totalParts$1));
         Object[] buf = scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(this.evidence$1.runtimeClass())).newArray(p.size());
         this.self.context().setCallSite(callSite$1);
         this.self.context().setLocalProperties(localProperties$1);
         FutureAction job = jobSubmitter.submitJob(this.self, (it) -> it.take(left).toArray(this.evidence$1), p, (index, data) -> {
            $anonfun$takeAsync$3(buf, BoxesRunTime.unboxToInt(index), data);
            return BoxedUnit.UNIT;
         }, (JFunction0.mcV.sp)() -> {
         });
         return job.flatMap((x$2) -> {
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(buf), (x$3) -> (ArrayBuffer)results$2.$plus$plus$eq(scala.Predef..MODULE$.genericWrapArray(scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.genericArrayOps(x$3), num$1 - results$2.size()))));
            return this.continue$1(partsScanned + p.size(), jobSubmitter, results$2, num$1, totalParts$1, scaleUpFactor$1, callSite$1, localProperties$1, executionContext$1);
         }, executionContext$1);
      } else {
         return scala.concurrent.Future..MODULE$.successful(results$2.toSeq());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachAsync$2(final Function1 cleanF$1, final Iterator x$5) {
      x$5.foreach(cleanF$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachAsync$3(final int index, final BoxedUnit data) {
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachPartitionAsync$2(final int index, final BoxedUnit data) {
   }

   public AsyncRDDActions(final RDD self, final ClassTag evidence$1) {
      this.self = self;
      this.evidence$1 = evidence$1;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
