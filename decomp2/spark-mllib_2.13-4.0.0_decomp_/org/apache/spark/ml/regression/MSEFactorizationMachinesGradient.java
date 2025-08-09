package org.apache.spark.ml.regression;

import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q!\u0003\u0006\u0001\u0019QA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tO\u0001\u0011\t\u0011)A\u0005Q!A1\u0006\u0001B\u0001B\u0003%\u0001\u0006\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003\"\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u0015!\u0004\u0001\"\u00116\u0011\u0015Y\u0004\u0001\"\u0015=\u0011\u0015\u0001\u0005\u0001\"\u0015B\u0005\u0001j5+\u0012$bGR|'/\u001b>bi&|g.T1dQ&tWm]$sC\u0012LWM\u001c;\u000b\u0005-a\u0011A\u0003:fOJ,7o]5p]*\u0011QBD\u0001\u0003[2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\n\u0004\u0001UI\u0002C\u0001\f\u0018\u001b\u0005Q\u0011B\u0001\r\u000b\u0005\u0005\u0012\u0015m]3GC\u000e$xN]5{CRLwN\\'bG\"Lg.Z:He\u0006$\u0017.\u001a8u!\tQR$D\u0001\u001c\u0015\tab\"\u0001\u0005j]R,'O\\1m\u0013\tq2DA\u0004M_\u001e<\u0017N\\4\u0002\u0015\u0019\f7\r^8s'&TXm\u0001\u0001\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\u0007%sG/\u0001\u0007gSRLe\u000e^3sG\u0016\u0004H\u000f\u0005\u0002#S%\u0011!f\t\u0002\b\u0005>|G.Z1o\u0003%1\u0017\u000e\u001e'j]\u0016\f'/A\u0006ok64U-\u0019;ve\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u00030aE\u00124\u0007\u0005\u0002\u0017\u0001!)q$\u0002a\u0001C!)q%\u0002a\u0001Q!)1&\u0002a\u0001Q!)A&\u0002a\u0001C\u0005iq-\u001a;Qe\u0016$\u0017n\u0019;j_:$\"AN\u001d\u0011\u0005\t:\u0014B\u0001\u001d$\u0005\u0019!u.\u001e2mK\")!H\u0002a\u0001m\u0005i!/Y<Qe\u0016$\u0017n\u0019;j_:\fQbZ3u\u001bVdG/\u001b9mS\u0016\u0014Hc\u0001\u001c>}!)!h\u0002a\u0001m!)qh\u0002a\u0001m\u0005)A.\u00192fY\u00069q-\u001a;M_N\u001cHc\u0001\u001cC\u0007\")!\b\u0003a\u0001m!)q\b\u0003a\u0001m\u0001"
)
public class MSEFactorizationMachinesGradient extends BaseFactorizationMachinesGradient implements Logging {
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

   public double getPrediction(final double rawPrediction) {
      return rawPrediction;
   }

   public double getMultiplier(final double rawPrediction, final double label) {
      return (double)2 * (rawPrediction - label);
   }

   public double getLoss(final double rawPrediction, final double label) {
      return (rawPrediction - label) * (rawPrediction - label);
   }

   public MSEFactorizationMachinesGradient(final int factorSize, final boolean fitIntercept, final boolean fitLinear, final int numFeatures) {
      super(factorSize, fitIntercept, fitLinear, numFeatures);
      Logging.$init$(this);
   }
}
