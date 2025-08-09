package org.apache.spark.mllib.feature;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005)3AAB\u0004\u0001%!Aq\u0004\u0001B\u0001B\u0003%\u0001\u0005\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003!\u0011\u0015!\u0003\u0001\"\u0001&\u0011\u0015!\u0003\u0001\"\u00014\u0011\u0015)\u0004\u0001\"\u00017\u00059\u0019F/\u00198eCJ$7kY1mKJT!\u0001C\u0005\u0002\u000f\u0019,\u0017\r^;sK*\u0011!bC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sO\u000e\u00011c\u0001\u0001\u00143A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\u0006\u0002\u0011%tG/\u001a:oC2L!AH\u000e\u0003\u000f1{wmZ5oO\u0006Aq/\u001b;i\u001b\u0016\fg\u000e\u0005\u0002\u0015C%\u0011!%\u0006\u0002\b\u0005>|G.Z1o\u0003\u001d9\u0018\u000e\u001e5Ti\u0012\fa\u0001P5oSRtDc\u0001\u0014)SA\u0011q\u0005A\u0007\u0002\u000f!)qd\u0001a\u0001A!)1e\u0001a\u0001A!\u001a1aK\u0019\u0011\u00051zS\"A\u0017\u000b\u00059Z\u0011AC1o]>$\u0018\r^5p]&\u0011\u0001'\f\u0002\u0006'&t7-Z\u0011\u0002e\u0005)\u0011GL\u0019/aQ\ta\u0005K\u0002\u0005WE\n1AZ5u)\t9$\b\u0005\u0002(q%\u0011\u0011h\u0002\u0002\u0014'R\fg\u000eZ1sIN\u001b\u0017\r\\3s\u001b>$W\r\u001c\u0005\u0006w\u0015\u0001\r\u0001P\u0001\u0005I\u0006$\u0018\rE\u0002>\u0001\nk\u0011A\u0010\u0006\u0003\u007f-\t1A\u001d3e\u0013\t\teHA\u0002S\t\u0012\u0003\"a\u0011$\u000e\u0003\u0011S!!R\u0005\u0002\r1Lg.\u00197h\u0013\t9EI\u0001\u0004WK\u000e$xN\u001d\u0015\u0004\u000b-\n\u0004f\u0001\u0001,c\u0001"
)
public class StandardScaler implements Logging {
   private final boolean withMean;
   private final boolean withStd;
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

   public StandardScalerModel fit(final RDD data) {
      SummarizerBuffer summary = Statistics$.MODULE$.colStats(data.map((x$1) -> new Tuple2(x$1, BoxesRunTime.boxToDouble((double)1.0F)), .MODULE$.apply(Tuple2.class)), new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("std", scala.collection.immutable.Nil..MODULE$)));
      return new StandardScalerModel(Vectors$.MODULE$.fromML(summary.std()), Vectors$.MODULE$.fromML(summary.mean()), this.withStd, this.withMean);
   }

   public StandardScaler(final boolean withMean, final boolean withStd) {
      this.withMean = withMean;
      this.withStd = withStd;
      Logging.$init$(this);
      if (!withMean && !withStd) {
         this.logWarning((Function0)(() -> "Both withMean and withStd are false. The model does nothing."));
      }

   }

   public StandardScaler() {
      this(false, true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
