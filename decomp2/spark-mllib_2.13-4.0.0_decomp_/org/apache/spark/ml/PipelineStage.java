package org.apache.spark.ml;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.Identifiable;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3Q!\u0002\u0004\u0002\u0002=AQA\t\u0001\u0005\u0002\rBQA\n\u0001\u0007\u0002\u001dBQA\n\u0001\u0005\u0012IBQ\u0001\u0011\u0001\u0007B\u0005\u0013Q\u0002U5qK2Lg.Z*uC\u001e,'BA\u0004\t\u0003\tiGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\t\u00179A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\u000e\u000e\u0003aQ!!\u0007\u0004\u0002\u000bA\f'/Y7\n\u0005mA\"A\u0002)be\u0006l7\u000f\u0005\u0002\u001eA5\taD\u0003\u0002 \u0011\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\"=\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$h\bF\u0001%!\t)\u0003!D\u0001\u0007\u0003=!(/\u00198tM>\u0014XnU2iK6\fGC\u0001\u00151!\tIc&D\u0001+\u0015\tYC&A\u0003usB,7O\u0003\u0002.\u0011\u0005\u00191/\u001d7\n\u0005=R#AC*ueV\u001cG\u000fV=qK\")\u0011G\u0001a\u0001Q\u000511o\u00195f[\u0006$2\u0001K\u001a5\u0011\u0015\t4\u00011\u0001)\u0011\u0015)4\u00011\u00017\u0003\u001dawnZ4j]\u001e\u0004\"!E\u001c\n\u0005a\u0012\"a\u0002\"p_2,\u0017M\u001c\u0015\u0003\u0007i\u0002\"a\u000f \u000e\u0003qR!!\u0010\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002@y\taA)\u001a<fY>\u0004XM]!qS\u0006!1m\u001c9z)\t!#\tC\u0003D\t\u0001\u0007A)A\u0003fqR\u0014\u0018\r\u0005\u0002\u0018\u000b&\u0011a\t\u0007\u0002\t!\u0006\u0014\u0018-\\'ba\u0002"
)
public abstract class PipelineStage implements Params, Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private Param[] params;
   private ParamMap paramMap;
   private ParamMap defaultParamMap;
   private volatile boolean bitmap$0;

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

   public String explainParam(final Param param) {
      return Params.explainParam$(this, param);
   }

   public String explainParams() {
      return Params.explainParams$(this);
   }

   public final boolean isSet(final Param param) {
      return Params.isSet$(this, param);
   }

   public final boolean isDefined(final Param param) {
      return Params.isDefined$(this, param);
   }

   public boolean hasParam(final String paramName) {
      return Params.hasParam$(this, paramName);
   }

   public Param getParam(final String paramName) {
      return Params.getParam$(this, paramName);
   }

   public final Params set(final Param param, final Object value) {
      return Params.set$(this, (Param)param, value);
   }

   public final Params set(final String param, final Object value) {
      return Params.set$(this, (String)param, value);
   }

   public final Params set(final ParamPair paramPair) {
      return Params.set$(this, paramPair);
   }

   public final Option get(final Param param) {
      return Params.get$(this, param);
   }

   public final Params clear(final Param param) {
      return Params.clear$(this, param);
   }

   public final Object getOrDefault(final Param param) {
      return Params.getOrDefault$(this, param);
   }

   public final Object $(final Param param) {
      return Params.$$(this, param);
   }

   public final Params setDefault(final Param param, final Object value) {
      return Params.setDefault$(this, param, value);
   }

   public final Params setDefault(final Seq paramPairs) {
      return Params.setDefault$(this, paramPairs);
   }

   public final Option getDefault(final Param param) {
      return Params.getDefault$(this, param);
   }

   public final boolean hasDefault(final Param param) {
      return Params.hasDefault$(this, param);
   }

   public final Params defaultCopy(final ParamMap extra) {
      return Params.defaultCopy$(this, extra);
   }

   public final ParamMap extractParamMap(final ParamMap extra) {
      return Params.extractParamMap$(this, extra);
   }

   public final ParamMap extractParamMap() {
      return Params.extractParamMap$(this);
   }

   public Params copyValues(final Params to, final ParamMap extra) {
      return Params.copyValues$(this, to, extra);
   }

   public ParamMap copyValues$default$2() {
      return Params.copyValues$default$2$(this);
   }

   public void onParamChange(final Param param) {
      Params.onParamChange$(this, param);
   }

   public String toString() {
      return Identifiable.toString$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Param[] params$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.params = Params.params$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.params;
   }

   public Param[] params() {
      return !this.bitmap$0 ? this.params$lzycompute() : this.params;
   }

   public ParamMap paramMap() {
      return this.paramMap;
   }

   public ParamMap defaultParamMap() {
      return this.defaultParamMap;
   }

   public void org$apache$spark$ml$param$Params$_setter_$paramMap_$eq(final ParamMap x$1) {
      this.paramMap = x$1;
   }

   public void org$apache$spark$ml$param$Params$_setter_$defaultParamMap_$eq(final ParamMap x$1) {
      this.defaultParamMap = x$1;
   }

   public abstract StructType transformSchema(final StructType schema);

   @DeveloperApi
   public StructType transformSchema(final StructType schema, final boolean logging) {
      if (logging) {
         this.logDebug((Function0)(() -> "Input schema: " + schema.json()));
      }

      StructType outputSchema = this.transformSchema(schema);
      if (logging) {
         this.logDebug((Function0)(() -> "Expected output schema: " + outputSchema.json()));
      }

      return outputSchema;
   }

   public abstract PipelineStage copy(final ParamMap extra);

   public PipelineStage() {
      Identifiable.$init$(this);
      Params.$init$(this);
      Logging.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
