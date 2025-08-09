package org.apache.spark.ml.regression;

import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.util.MLUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q!\u0003\u0006\u0001\u0019QA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tO\u0001\u0011\t\u0011)A\u0005Q!A1\u0006\u0001B\u0001B\u0003%\u0001\u0006\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003\"\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u0015!\u0004\u0001\"\u00116\u0011\u0015Y\u0004\u0001\"\u0015=\u0011\u0015\u0001\u0005\u0001\"\u0015B\u0005\u0015bunZ5ti&\u001cg)Y2u_JL'0\u0019;j_:l\u0015m\u00195j]\u0016\u001cxI]1eS\u0016tGO\u0003\u0002\f\u0019\u0005Q!/Z4sKN\u001c\u0018n\u001c8\u000b\u00055q\u0011AA7m\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7c\u0001\u0001\u00163A\u0011acF\u0007\u0002\u0015%\u0011\u0001D\u0003\u0002\"\u0005\u0006\u001cXMR1di>\u0014\u0018N_1uS>tW*Y2iS:,7o\u0012:bI&,g\u000e\u001e\t\u00035ui\u0011a\u0007\u0006\u000399\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003=m\u0011q\u0001T8hO&tw-\u0001\u0006gC\u000e$xN]*ju\u0016\u001c\u0001\u0001\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13EA\u0002J]R\fABZ5u\u0013:$XM]2faR\u0004\"AI\u0015\n\u0005)\u001a#a\u0002\"p_2,\u0017M\\\u0001\nM&$H*\u001b8fCJ\f1B\\;n\r\u0016\fG/\u001e:fg\u00061A(\u001b8jiz\"Ra\f\u00192eM\u0002\"A\u0006\u0001\t\u000b})\u0001\u0019A\u0011\t\u000b\u001d*\u0001\u0019\u0001\u0015\t\u000b-*\u0001\u0019\u0001\u0015\t\u000b1*\u0001\u0019A\u0011\u0002\u001b\u001d,G\u000f\u0015:fI&\u001cG/[8o)\t1\u0014\b\u0005\u0002#o%\u0011\u0001h\t\u0002\u0007\t>,(\r\\3\t\u000bi2\u0001\u0019\u0001\u001c\u0002\u001bI\fw\u000f\u0015:fI&\u001cG/[8o\u000359W\r^'vYRL\u0007\u000f\\5feR\u0019a'\u0010 \t\u000bi:\u0001\u0019\u0001\u001c\t\u000b}:\u0001\u0019\u0001\u001c\u0002\u000b1\f'-\u001a7\u0002\u000f\u001d,G\u000fT8tgR\u0019aGQ\"\t\u000biB\u0001\u0019\u0001\u001c\t\u000b}B\u0001\u0019\u0001\u001c"
)
public class LogisticFactorizationMachinesGradient extends BaseFactorizationMachinesGradient implements Logging {
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
      return (double)1.0F / ((double)1.0F + .MODULE$.exp(-rawPrediction));
   }

   public double getMultiplier(final double rawPrediction, final double label) {
      return this.getPrediction(rawPrediction) - label;
   }

   public double getLoss(final double rawPrediction, final double label) {
      return label > (double)0 ? MLUtils$.MODULE$.log1pExp(-rawPrediction) : MLUtils$.MODULE$.log1pExp(rawPrediction);
   }

   public LogisticFactorizationMachinesGradient(final int factorSize, final boolean fitIntercept, final boolean fitLinear, final int numFeatures) {
      super(factorSize, fitIntercept, fitLinear, numFeatures);
      Logging.$init$(this);
   }
}
