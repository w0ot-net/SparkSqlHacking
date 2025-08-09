package org.apache.spark.ml.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.UUID;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.MLEvent;
import org.apache.spark.ml.MLEvents;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.param.Params;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.util.Utils.;
import org.json4s.JValue;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tec!\u0002\u001a4\u0001]j\u0004\"\u0002(\u0001\t\u0013\u0001\u0006bB*\u0001\u0005\u0004%I\u0001\u0016\u0005\u00079\u0002\u0001\u000b\u0011B+\t\u000fu\u0003!\u0019!C\u0005=\"1!\u000e\u0001Q\u0001\n}C\u0001b\u001b\u0001C\u0002\u0013\u00051\u0007\u001c\u0005\u0007e\u0002\u0001\u000b\u0011B7\t\u000bM\u0004A\u0011\u0001;\t\u000bu\u0004A\u0011\u0001@\t\ru\u0004A\u0011AA\u0014\u0011\u001d\ty\u0004\u0001C!\u0003\u0003Bq!!\u0014\u0001\t\u0003\ny\u0005C\u0004\u0002N\u0001!\t%a\u0015\t\u000f\u0005}\u0003\u0001\"\u0011\u0002b!9\u0011q\f\u0001\u0005B\u0005\u0015\u0004bBA5\u0001\u0011\u0005\u00131\u000e\u0005\b\u0003S\u0002A\u0011IA8\u0011\u001d\t\u0019\b\u0001C\u0001\u0003kBq!a(\u0001\t\u0003\t\t\u000bC\u0004\u0002.\u0002!\t!a,\t\u000f\u0005M\u0006\u0001\"\u0001\u00026\"9\u0011\u0011\u0018\u0001\u0005\u0002\u0005m\u0006bBAc\u0001\u0011\u0005\u0011q\u0019\u0005\b\u0003\u000b\u0004A\u0011AAi\u0011\u001d\t)\r\u0001C\u0001\u0003/Dq!!2\u0001\t\u0003\ti\u000eC\u0004\u0002F\u0002!\t!!;\t\u000f\u0005\u0015\u0007\u0001\"\u0001\u0002r\"9\u0011\u0011 \u0001\u0005\u0002\u0005m\bbBA\u007f\u0001\u0011\u0005\u0011q`\u0004\t\u0005/\u0019\u0004\u0012A\u001c\u0003\u001a\u00199!g\rE\u0001o\tm\u0001B\u0002(!\t\u0003\u0011ibB\u0004\u0003 \u0001B\tA!\t\u0007\u000f\t\u0015\u0002\u0005#\u0001\u0003(!1aj\tC\u0001\u0005SA\u0001Ba\u000b$\u0005\u0004%\t\u0001\u001c\u0005\b\u0005[\u0019\u0003\u0015!\u0003n\u0011!\u0011yc\tb\u0001\n\u0003a\u0007b\u0002B\u0019G\u0001\u0006I!\u001c\u0005\t\u0005g\u0019#\u0019!C\u0001Y\"9!QG\u0012!\u0002\u0013i\u0007\u0002\u0003B\u001cG\t\u0007I\u0011\u00017\t\u000f\te2\u0005)A\u0005[\"A!1H\u0012C\u0002\u0013\u0005A\u000eC\u0004\u0003>\r\u0002\u000b\u0011B7\t\u0011\t}2E1A\u0005\u00021DqA!\u0011$A\u0003%Q\u000eC\u0004\u0003D\u0001\"\tA!\u0012\u0003\u001f%s7\u000f\u001e:v[\u0016tG/\u0019;j_:T!\u0001N\u001b\u0002\tU$\u0018\u000e\u001c\u0006\u0003m]\n!!\u001c7\u000b\u0005aJ\u0014!B:qCJ\\'B\u0001\u001e<\u0003\u0019\t\u0007/Y2iK*\tA(A\u0002pe\u001e\u001cB\u0001\u0001 E\u0015B\u0011qHQ\u0007\u0002\u0001*\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\u0001\n1\u0011I\\=SK\u001a\u0004\"!\u0012%\u000e\u0003\u0019S!aR\u001c\u0002\u0011%tG/\u001a:oC2L!!\u0013$\u0003\u000f1{wmZ5oOB\u00111\nT\u0007\u0002k%\u0011Q*\u000e\u0002\t\u001b2+e/\u001a8ug\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001R!\t\u0011\u0006!D\u00014\u0003\tIG-F\u0001V!\t1&,D\u0001X\u0015\t!\u0004LC\u0001Z\u0003\u0011Q\u0017M^1\n\u0005m;&\u0001B+V\u0013\u0012\u000b1!\u001b3!\u0003\u001d\u0019\bn\u001c:u\u0013\u0012,\u0012a\u0018\t\u0003A\u001et!!Y3\u0011\u0005\t\u0004U\"A2\u000b\u0005\u0011|\u0015A\u0002\u001fs_>$h(\u0003\u0002g\u0001\u00061\u0001K]3eK\u001aL!\u0001[5\u0003\rM#(/\u001b8h\u0015\t1\u0007)\u0001\u0005tQ>\u0014H/\u00133!\u0003\u0019\u0001(/\u001a4jqV\tQ\u000e\u0005\u0002oc6\tqN\u0003\u0002q1\u0006!A.\u00198h\u0013\tAw.A\u0004qe\u00164\u0017\u000e\u001f\u0011\u0002!1|w\rU5qK2Lg.Z*uC\u001e,GCA;y!\tyd/\u0003\u0002x\u0001\n!QK\\5u\u0011\u0015I\b\u00021\u0001{\u0003\u0015\u0019H/Y4f!\tY50\u0003\u0002}k\ti\u0001+\u001b9fY&tWm\u0015;bO\u0016\f!\u0002\\8h\t\u0006$\u0018m]3u)\t)x\u0010C\u0004\u0002\u0002%\u0001\r!a\u0001\u0002\u000f\u0011\fG/Y:fiB\"\u0011QAA\u000b!\u0019\t9!!\u0004\u0002\u00125\u0011\u0011\u0011\u0002\u0006\u0004\u0003\u00179\u0014aA:rY&!\u0011qBA\u0005\u0005\u001d!\u0015\r^1tKR\u0004B!a\u0005\u0002\u00161\u0001AaCA\f\u007f\u0006\u0005\t\u0011!B\u0001\u00033\u00111a\u0018\u00132#\u0011\tY\"!\t\u0011\u0007}\ni\"C\u0002\u0002 \u0001\u0013qAT8uQ&tw\rE\u0002@\u0003GI1!!\nA\u0005\r\te.\u001f\u000b\u0004k\u0006%\u0002bBA\u0001\u0015\u0001\u0007\u00111\u0006\u0019\u0005\u0003[\tY\u0004\u0005\u0004\u00020\u0005U\u0012\u0011H\u0007\u0003\u0003cQ1!a\r8\u0003\r\u0011H\rZ\u0005\u0005\u0003o\t\tDA\u0002S\t\u0012\u0003B!a\u0005\u0002<\u0011a\u0011QHA\u0015\u0003\u0003\u0005\tQ!\u0001\u0002\u001a\t\u0019q\f\n\u001a\u0002\u00111|w\rR3ck\u001e$2!^A\"\u0011!\t)e\u0003CA\u0002\u0005\u001d\u0013aA7tOB!q(!\u0013`\u0013\r\tY\u0005\u0011\u0002\ty\tLh.Y7f}\u0005QAn\\4XCJt\u0017N\\4\u0015\u0007U\f\t\u0006\u0003\u0005\u0002F1!\t\u0019AA$)\r)\u0018Q\u000b\u0005\b\u0003/j\u0001\u0019AA-\u0003\u0015)g\u000e\u001e:z!\r)\u00151L\u0005\u0004\u0003;2%\u0001\u0003'pO\u0016sGO]=\u0002\u00111|w-\u0012:s_J$2!^A2\u0011!\t)E\u0004CA\u0002\u0005\u001dCcA;\u0002h!9\u0011qK\bA\u0002\u0005e\u0013a\u00027pO&sgm\u001c\u000b\u0004k\u00065\u0004\u0002CA#!\u0011\u0005\r!a\u0012\u0015\u0007U\f\t\bC\u0004\u0002XE\u0001\r!!\u0017\u0002\u00131|w\rU1sC6\u001cH#B;\u0002x\u0005\u001d\u0005bBA=%\u0001\u0007\u00111P\u0001\nQ\u0006\u001c\b+\u0019:b[N\u0004B!! \u0002\u00046\u0011\u0011q\u0010\u0006\u0004\u0003\u0003+\u0014!\u00029be\u0006l\u0017\u0002BAC\u0003\u007f\u0012a\u0001U1sC6\u001c\bbBAE%\u0001\u0007\u00111R\u0001\u0007a\u0006\u0014\u0018-\\:\u0011\u000b}\ni)!%\n\u0007\u0005=\u0005I\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002D!a%\u0002\u001cB1\u0011QPAK\u00033KA!a&\u0002\u0000\t)\u0001+\u0019:b[B!\u00111CAN\t1\ti*a\"\u0002\u0002\u0003\u0005)\u0011AA\r\u0005\ryFeM\u0001\u000fY><g*^7GK\u0006$XO]3t)\r)\u00181\u0015\u0005\b\u0003K\u001b\u0002\u0019AAT\u0003\rqW/\u001c\t\u0004\u007f\u0005%\u0016bAAV\u0001\n!Aj\u001c8h\u00035awn\u001a(v[\u000ec\u0017m]:fgR\u0019Q/!-\t\u000f\u0005\u0015F\u00031\u0001\u0002(\u0006qAn\\4Ok6,\u00050Y7qY\u0016\u001cHcA;\u00028\"9\u0011QU\u000bA\u0002\u0005\u001d\u0016a\u00047pON+Xn\u00144XK&<\u0007\u000e^:\u0015\u0007U\fi\fC\u0004\u0002&Z\u0001\r!a0\u0011\u0007}\n\t-C\u0002\u0002D\u0002\u0013a\u0001R8vE2,\u0017!\u00047pO:\u000bW.\u001a3WC2,X\rF\u0003v\u0003\u0013\fi\r\u0003\u0004\u0002L^\u0001\raX\u0001\u0005]\u0006lW\r\u0003\u0004\u0002P^\u0001\raX\u0001\u0006m\u0006dW/\u001a\u000b\u0006k\u0006M\u0017Q\u001b\u0005\u0007\u0003\u0017D\u0002\u0019A0\t\u000f\u0005=\u0007\u00041\u0001\u0002(R)Q/!7\u0002\\\"1\u00111Z\rA\u0002}Cq!a4\u001a\u0001\u0004\ty\fF\u0003v\u0003?\f\t\u000f\u0003\u0004\u0002Lj\u0001\ra\u0018\u0005\b\u0003\u001fT\u0002\u0019AAr!\u0011y\u0014Q]0\n\u0007\u0005\u001d\bIA\u0003BeJ\f\u0017\u0010F\u0003v\u0003W\fi\u000f\u0003\u0004\u0002Ln\u0001\ra\u0018\u0005\b\u0003\u001f\\\u0002\u0019AAx!\u0015y\u0014Q]AT)\u0015)\u00181_A{\u0011\u0019\tY\r\ba\u0001?\"9\u0011q\u001a\u000fA\u0002\u0005]\b#B \u0002f\u0006}\u0016A\u00037pON+8mY3tgR\tQ/\u0001\u0006m_\u001e4\u0015-\u001b7ve\u0016$2!\u001eB\u0001\u0011\u001d\u0011\u0019A\ba\u0001\u0005\u000b\t\u0011!\u001a\t\u0005\u0005\u000f\u0011\tB\u0004\u0003\u0003\n\t5ab\u00012\u0003\f%\t\u0011)C\u0002\u0003\u0010\u0001\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0003\u0014\tU!!\u0003+ie><\u0018M\u00197f\u0015\r\u0011y\u0001Q\u0001\u0010\u0013:\u001cHO];nK:$\u0018\r^5p]B\u0011!\u000bI\n\u0003Ay\"\"A!\u0007\u0002\u00151|wmZ3s)\u0006<7\u000fE\u0002\u0003$\rj\u0011\u0001\t\u0002\u000bY><w-\u001a:UC\u001e\u001c8CA\u0012?)\t\u0011\t#A\u0006ok64U-\u0019;ve\u0016\u001c\u0018\u0001\u00048v[\u001a+\u0017\r^;sKN\u0004\u0013A\u00038v[\u000ec\u0017m]:fg\u0006Ya.^7DY\u0006\u001c8/Z:!\u0003-qW/\\#yC6\u0004H.Z:\u0002\u00199,X.\u0012=b[BdWm\u001d\u0011\u0002\u00195,\u0017M\\(g\u0019\u0006\u0014W\r\\:\u0002\u001b5,\u0017M\\(g\u0019\u0006\u0014W\r\\:!\u0003A1\u0018M]5b]\u000e,wJ\u001a'bE\u0016d7/A\twCJL\u0017M\\2f\u001f\u001ad\u0015MY3mg\u0002\nAb];n\u001f\u001a<V-[4iiN\fQb];n\u001f\u001a<V-[4iiN\u0004\u0013\u0001D5ogR\u0014X/\\3oi\u0016$W\u0003\u0002B$\u0005\u0017\"BA!\u0013\u0003PA!\u00111\u0003B&\t\u001d\u0011i%\rb\u0001\u00033\u0011\u0011\u0001\u0016\u0005\b\u0005#\n\u0004\u0019\u0001B*\u0003\u0011\u0011w\u000eZ=\u0011\r}\u0012)&\u0015B%\u0013\r\u00119\u0006\u0011\u0002\n\rVt7\r^5p]F\u0002"
)
public class Instrumentation implements MLEvents {
   private final UUID id;
   private final String shortId;
   private final String prefix;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Object instrumented(final Function1 body) {
      return Instrumentation$.MODULE$.instrumented(body);
   }

   public void logEvent(final MLEvent event) {
      MLEvents.logEvent$(this, event);
   }

   public Model withFitEvent(final Estimator estimator, final Dataset dataset, final Function0 func) {
      return MLEvents.withFitEvent$(this, estimator, dataset, func);
   }

   public Dataset withTransformEvent(final Transformer transformer, final Dataset input, final Function0 func) {
      return MLEvents.withTransformEvent$(this, transformer, input, func);
   }

   public Object withLoadInstanceEvent(final MLReader reader, final String path, final Function0 func) {
      return MLEvents.withLoadInstanceEvent$(this, reader, path, func);
   }

   public void withSaveInstanceEvent(final MLWriter writer, final String path, final Function0 func) {
      MLEvents.withSaveInstanceEvent$(this, writer, path, func);
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

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
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

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
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

   private UUID id() {
      return this.id;
   }

   private String shortId() {
      return this.shortId;
   }

   public String prefix() {
      return this.prefix;
   }

   public void logPipelineStage(final PipelineStage stage) {
      String className = .MODULE$.getSimpleName(stage.getClass());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stage class: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, className)})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stage uid: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PIPELINE_STAGE_UID..MODULE$, stage.uid())})))));
   }

   public void logDataset(final Dataset dataset) {
      this.logDataset(dataset.rdd());
   }

   public void logDataset(final RDD dataset) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"training: numPartitions=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PARTITIONS..MODULE$, BoxesRunTime.boxToInteger(dataset.partitions().length))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" storageLevel=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STORAGE_LEVEL..MODULE$, dataset.getStorageLevel())}))))));
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, () -> {
         String var10000 = this.prefix();
         return var10000 + msg.apply();
      });
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, () -> {
         String var10000 = this.prefix();
         return var10000 + msg.apply();
      });
   }

   public void logWarning(final LogEntry entry) {
      if (this.log().isWarnEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> {
            Logger var10000 = this.log();
            String var10001 = this.prefix();
            var10000.warn(var10001 + entry.message());
         });
      }
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, () -> {
         String var10000 = this.prefix();
         return var10000 + msg.apply();
      });
   }

   public void logError(final LogEntry entry) {
      if (this.log().isErrorEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> {
            Logger var10000 = this.log();
            String var10001 = this.prefix();
            var10000.error(var10001 + entry.message());
         });
      }
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, () -> {
         String var10000 = this.prefix();
         return var10000 + msg.apply();
      });
   }

   public void logInfo(final LogEntry entry) {
      if (this.log().isInfoEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> {
            Logger var10000 = this.log();
            String var10001 = this.prefix();
            var10000.info(var10001 + entry.message());
         });
      }
   }

   public void logParams(final Params hasParams, final Seq params) {
      Seq pairs = (Seq)params.flatMap((p) -> hasParams.get(p).map((value) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(p.name()), org.json4s.jackson.JsonMethods..MODULE$.parse(p.jsonEncode(value), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()))));
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.map2jvalue(pairs.toMap(scala..less.colon.less..MODULE$.refl()), scala.Predef..MODULE$.$conforms()), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logNumFeatures(final long num) {
      this.logNamedValue(Instrumentation.loggerTags$.MODULE$.numFeatures(), num);
   }

   public void logNumClasses(final long num) {
      this.logNamedValue(Instrumentation.loggerTags$.MODULE$.numClasses(), num);
   }

   public void logNumExamples(final long num) {
      this.logNamedValue(Instrumentation.loggerTags$.MODULE$.numExamples(), num);
   }

   public void logSumOfWeights(final double num) {
      this.logNamedValue(Instrumentation.loggerTags$.MODULE$.sumOfWeights(), num);
   }

   public void logNamedValue(final String name, final String value) {
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), value), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logNamedValue(final String name, final long value) {
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), BoxesRunTime.boxToLong(value)), (x) -> $anonfun$logNamedValue$4(BoxesRunTime.unboxToLong(x))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logNamedValue(final String name, final double value) {
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), BoxesRunTime.boxToDouble(value)), (x) -> $anonfun$logNamedValue$6(BoxesRunTime.unboxToDouble(x))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logNamedValue(final String name, final String[] value) {
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq(), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logNamedValue(final String name, final long[] value) {
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq(), (x) -> $anonfun$logNamedValue$11(BoxesRunTime.unboxToLong(x))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logNamedValue(final String name, final double[] value) {
      this.logInfo((Function0)(() -> org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(value).toImmutableArraySeq(), (x) -> $anonfun$logNamedValue$14(BoxesRunTime.unboxToDouble(x))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))));
   }

   public void logSuccess() {
      this.logInfo((Function0)(() -> "training finished"));
   }

   public void logFailure(final Throwable e) {
      StringWriter msg = new StringWriter();
      e.printStackTrace(new PrintWriter(msg));
      Logging.logError$(this, () -> msg.toString());
   }

   // $FF: synthetic method
   public static final JValue $anonfun$logNamedValue$4(final long x) {
      return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$logNamedValue$6(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$logNamedValue$11(final long x) {
      return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$logNamedValue$14(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
   }

   public Instrumentation() {
      Logging.$init$(this);
      MLEvents.$init$(this);
      this.id = UUID.randomUUID();
      this.shortId = scala.collection.StringOps..MODULE$.take$extension(scala.Predef..MODULE$.augmentString(this.id().toString()), 8);
      this.prefix = "[" + this.shortId() + "] ";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class loggerTags$ {
      public static final loggerTags$ MODULE$ = new loggerTags$();
      private static final String numFeatures = "numFeatures";
      private static final String numClasses = "numClasses";
      private static final String numExamples = "numExamples";
      private static final String meanOfLabels = "meanOfLabels";
      private static final String varianceOfLabels = "varianceOfLabels";
      private static final String sumOfWeights = "sumOfWeights";

      public String numFeatures() {
         return numFeatures;
      }

      public String numClasses() {
         return numClasses;
      }

      public String numExamples() {
         return numExamples;
      }

      public String meanOfLabels() {
         return meanOfLabels;
      }

      public String varianceOfLabels() {
         return varianceOfLabels;
      }

      public String sumOfWeights() {
         return sumOfWeights;
      }
   }
}
