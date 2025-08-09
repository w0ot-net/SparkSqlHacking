package org.apache.spark.streaming;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg!B\u0013'\u0001\u0019r\u0003\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011%\u0003!Q1A\u0005\u0002)C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0013\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\b)\u0002\u0011\r\u0011\"\u0001V\u0011\u0019\t\u0007\u0001)A\u0005-\"9!\r\u0001b\u0001\n\u0003)\u0006BB2\u0001A\u0003%a\u000bC\u0004e\u0001\t\u0007I\u0011A3\t\r=\u0004\u0001\u0015!\u0003g\u0011\u001d\u0001\bA1A\u0005\u0002EDa!\u001e\u0001!\u0002\u0013\u0011\bb\u0002<\u0001\u0005\u0004%\t!\u0016\u0005\u0007o\u0002\u0001\u000b\u0011\u0002,\t\u000fa\u0004!\u0019!C\u0001s\"1Q\u0010\u0001Q\u0001\niDqA \u0001C\u0002\u0013\u0005q\u0010\u0003\u0005\u0002\b\u0001\u0001\u000b\u0011BA\u0001\u0011%\tI\u0001\u0001b\u0001\n\u0003\tY\u0001\u0003\u0005\u0002\u0016\u0001\u0001\u000b\u0011BA\u0007\u0011\u001d\t9\u0002\u0001C\u0001\u00033Aq!a\t\u0001\t\u0003\t)c\u0002\u0005\u0002.\u0019B\tAJA\u0018\r\u001d)c\u0005#\u0001'\u0003cAaa\u0014\r\u0005\u0002\u0005M\u0002\"CA\u001b1\t\u0007I\u0011AA\u001c\u0011!\t\u0019\u0005\u0007Q\u0001\n\u0005e\u0002\"CA#1\t\u0007I\u0011AA$\u0011!\tI\u0006\u0007Q\u0001\n\u0005%\u0003bBA.1\u0011\u0005\u0011Q\f\u0005\b\u0003gBB\u0011AA;\u0011\u001d\tY\b\u0007C\u0001\u0003{B\u0011\"a%\u0019#\u0003%\t!!&\t\u000f\u0005-\u0006\u0004\"\u0001\u0002.\"9\u0011q\u0018\r\u0005\u0002\u0005\u0005\u0007\"CAh1\u0005\u0005I\u0011BAi\u0005)\u0019\u0005.Z2la>Lg\u000e\u001e\u0006\u0003O!\n\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005%R\u0013!B:qCJ\\'BA\u0016-\u0003\u0019\t\u0007/Y2iK*\tQ&A\u0002pe\u001e\u001cB\u0001A\u00186wA\u0011\u0001gM\u0007\u0002c)\t!'A\u0003tG\u0006d\u0017-\u0003\u00025c\t1\u0011I\\=SK\u001a\u0004\"AN\u001d\u000e\u0003]R!\u0001\u000f\u0015\u0002\u0011%tG/\u001a:oC2L!AO\u001c\u0003\u000f1{wmZ5oOB\u0011A(Q\u0007\u0002{)\u0011ahP\u0001\u0003S>T\u0011\u0001Q\u0001\u0005U\u00064\u0018-\u0003\u0002C{\ta1+\u001a:jC2L'0\u00192mK\u0006\u00191o]2\u0004\u0001A\u0011aiR\u0007\u0002M%\u0011\u0001J\n\u0002\u0011'R\u0014X-Y7j]\u001e\u001cuN\u001c;fqR\fab\u00195fG.\u0004x.\u001b8u)&lW-F\u0001L!\t1E*\u0003\u0002NM\t!A+[7f\u0003=\u0019\u0007.Z2la>Lg\u000e\u001e+j[\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0002R%N\u0003\"A\u0012\u0001\t\u000b\r#\u0001\u0019A#\t\u000b%#\u0001\u0019A&\u0002\r5\f7\u000f^3s+\u00051\u0006CA,_\u001d\tAF\f\u0005\u0002Zc5\t!L\u0003\u0002\\\t\u00061AH]8pizJ!!X\u0019\u0002\rA\u0013X\rZ3g\u0013\ty\u0006M\u0001\u0004TiJLgn\u001a\u0006\u0003;F\nq!\\1ti\u0016\u0014\b%A\u0005ge\u0006lWm^8sW\u0006QaM]1nK^|'o\u001b\u0011\u0002\t)\f'o]\u000b\u0002MB\u0019q\r\u001c,\u000f\u0005!TgBA-j\u0013\u0005\u0011\u0014BA62\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u001c8\u0003\u0007M+\u0017O\u0003\u0002lc\u0005)!.\u0019:tA\u0005)qM]1qQV\t!\u000f\u0005\u0002Gg&\u0011AO\n\u0002\r\tN#(/Z1n\u000fJ\f\u0007\u000f[\u0001\u0007OJ\f\u0007\u000f\u001b\u0011\u0002\u001b\rDWmY6q_&tG\u000fR5s\u00039\u0019\u0007.Z2la>Lg\u000e\u001e#je\u0002\n!c\u00195fG.\u0004x.\u001b8u\tV\u0014\u0018\r^5p]V\t!\u0010\u0005\u0002Gw&\u0011AP\n\u0002\t\tV\u0014\u0018\r^5p]\u0006\u00192\r[3dWB|\u0017N\u001c;EkJ\fG/[8oA\u0005a\u0001/\u001a8eS:<G+[7fgV\u0011\u0011\u0011\u0001\t\u0005a\u0005\r1*C\u0002\u0002\u0006E\u0012Q!\u0011:sCf\fQ\u0002]3oI&tw\rV5nKN\u0004\u0013AD:qCJ\\7i\u001c8g!\u0006L'o]\u000b\u0003\u0003\u001b\u0001R\u0001MA\u0002\u0003\u001f\u0001R\u0001MA\t-ZK1!a\u00052\u0005\u0019!V\u000f\u001d7fe\u0005y1\u000f]1sW\u000e{gN\u001a)bSJ\u001c\b%A\bde\u0016\fG/Z*qCJ\\7i\u001c8g)\t\tY\u0002\u0005\u0003\u0002\u001e\u0005}Q\"\u0001\u0015\n\u0007\u0005\u0005\u0002FA\u0005Ta\u0006\u00148nQ8oM\u0006Aa/\u00197jI\u0006$X\r\u0006\u0002\u0002(A\u0019\u0001'!\u000b\n\u0007\u0005-\u0012G\u0001\u0003V]&$\u0018AC\"iK\u000e\\\u0007o\\5oiB\u0011a\tG\n\u00051=*4\b\u0006\u0002\u00020\u00051\u0001KU#G\u0013b+\"!!\u000f\u0011\t\u0005m\u0012\u0011I\u0007\u0003\u0003{Q1!a\u0010@\u0003\u0011a\u0017M\\4\n\u0007}\u000bi$A\u0004Q%\u00163\u0015\n\u0017\u0011\u0002\u000bI+u)\u0012-\u0016\u0005\u0005%\u0003\u0003BA&\u0003+j!!!\u0014\u000b\t\u0005=\u0013\u0011K\u0001\t[\u0006$8\r[5oO*\u0019\u00111K\u0019\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003/\niEA\u0003SK\u001e,\u00070\u0001\u0004S\u000b\u001e+\u0005\fI\u0001\u000fG\",7m\u001b9pS:$h)\u001b7f)\u0019\ty&a\u001c\u0002rA!\u0011\u0011MA6\u001b\t\t\u0019G\u0003\u0003\u0002f\u0005\u001d\u0014A\u00014t\u0015\r\tIGK\u0001\u0007Q\u0006$wn\u001c9\n\t\u00055\u00141\r\u0002\u0005!\u0006$\b\u000eC\u0003w=\u0001\u0007a\u000bC\u0003J=\u0001\u00071*\u0001\u000bdQ\u0016\u001c7\u000e]8j]R\u0014\u0015mY6va\u001aKG.\u001a\u000b\u0007\u0003?\n9(!\u001f\t\u000bY|\u0002\u0019\u0001,\t\u000b%{\u0002\u0019A&\u0002%\u001d,Go\u00115fG.\u0004x.\u001b8u\r&dWm\u001d\u000b\u0007\u0003\u007f\n\t)a!\u0011\t\u001dd\u0017q\f\u0005\u0006m\u0002\u0002\rA\u0016\u0005\n\u0003\u000b\u0003\u0003\u0013!a\u0001\u0003\u000f\u000b\u0001BZ:PaRLwN\u001c\t\u0006a\u0005%\u0015QR\u0005\u0004\u0003\u0017\u000b$AB(qi&|g\u000e\u0005\u0003\u0002b\u0005=\u0015\u0002BAI\u0003G\u0012!BR5mKNK8\u000f^3n\u0003q9W\r^\"iK\u000e\\\u0007o\\5oi\u001aKG.Z:%I\u00164\u0017-\u001e7uII*\"!a&+\t\u0005\u001d\u0015\u0011T\u0016\u0003\u00037\u0003B!!(\u0002(6\u0011\u0011q\u0014\u0006\u0005\u0003C\u000b\u0019+A\u0005v]\u000eDWmY6fI*\u0019\u0011QU\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002*\u0006}%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006I1/\u001a:jC2L'0\u001a\u000b\u0007\u0003_\u000b9,a/\u0011\u000bA\n\u0019!!-\u0011\u0007A\n\u0019,C\u0002\u00026F\u0012AAQ=uK\"1\u0011\u0011\u0018\u0012A\u0002E\u000b!b\u00195fG.\u0004x.\u001b8u\u0011\u001d\tiL\ta\u0001\u00037\tAaY8oM\u0006YA-Z:fe&\fG.\u001b>f)\u0015\t\u00161YAg\u0011\u001d\t)m\ta\u0001\u0003\u000f\f1\"\u001b8qkR\u001cFO]3b[B\u0019A(!3\n\u0007\u0005-WHA\u0006J]B,Ho\u0015;sK\u0006l\u0007bBA_G\u0001\u0007\u00111D\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003'\u0004B!a\u000f\u0002V&!\u0011q[A\u001f\u0005\u0019y%M[3di\u0002"
)
public class Checkpoint implements Logging, Serializable {
   private final Time checkpointTime;
   private final String master;
   private final String framework;
   private final Seq jars;
   private final DStreamGraph graph;
   private final String checkpointDir;
   private final Duration checkpointDuration;
   private final Time[] pendingTimes;
   private final Tuple2[] sparkConfPairs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Checkpoint deserialize(final InputStream inputStream, final SparkConf conf) {
      return Checkpoint$.MODULE$.deserialize(inputStream, conf);
   }

   public static byte[] serialize(final Checkpoint checkpoint, final SparkConf conf) {
      return Checkpoint$.MODULE$.serialize(checkpoint, conf);
   }

   public static Option getCheckpointFiles$default$2() {
      return Checkpoint$.MODULE$.getCheckpointFiles$default$2();
   }

   public static Seq getCheckpointFiles(final String checkpointDir, final Option fsOption) {
      return Checkpoint$.MODULE$.getCheckpointFiles(checkpointDir, fsOption);
   }

   public static Path checkpointBackupFile(final String checkpointDir, final Time checkpointTime) {
      return Checkpoint$.MODULE$.checkpointBackupFile(checkpointDir, checkpointTime);
   }

   public static Path checkpointFile(final String checkpointDir, final Time checkpointTime) {
      return Checkpoint$.MODULE$.checkpointFile(checkpointDir, checkpointTime);
   }

   public static Regex REGEX() {
      return Checkpoint$.MODULE$.REGEX();
   }

   public static String PREFIX() {
      return Checkpoint$.MODULE$.PREFIX();
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

   public Time checkpointTime() {
      return this.checkpointTime;
   }

   public String master() {
      return this.master;
   }

   public String framework() {
      return this.framework;
   }

   public Seq jars() {
      return this.jars;
   }

   public DStreamGraph graph() {
      return this.graph;
   }

   public String checkpointDir() {
      return this.checkpointDir;
   }

   public Duration checkpointDuration() {
      return this.checkpointDuration;
   }

   public Time[] pendingTimes() {
      return this.pendingTimes;
   }

   public Tuple2[] sparkConfPairs() {
      return this.sparkConfPairs;
   }

   public SparkConf createSparkConf() {
      List propertiesToReload = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"spark.yarn.app.id", "spark.yarn.app.attemptId", "spark.driver.host", "spark.driver.bindAddress", "spark.driver.port", "spark.master", "spark.ui.port", "spark.blockManager.port", "spark.kubernetes.driver.pod.name", "spark.kubernetes.executor.podNamePrefix", "spark.yarn.jars", "spark.yarn.keytab", "spark.yarn.principal", "spark.kerberos.keytab", "spark.kerberos.principal", org.apache.spark.internal.config.UI..MODULE$.UI_FILTERS().key()})));
      SparkConf newSparkConf = (new SparkConf(false)).setAll(scala.Predef..MODULE$.wrapRefArray((Object[])this.sparkConfPairs())).remove("spark.driver.host").remove("spark.driver.bindAddress").remove("spark.driver.port").remove("spark.ui.port").remove("spark.blockManager.port").remove("spark.kubernetes.driver.pod.name").remove("spark.kubernetes.executor.podNamePrefix");
      SparkConf newReloadConf = new SparkConf(true);
      propertiesToReload.foreach((prop) -> {
         $anonfun$createSparkConf$1(newReloadConf, newSparkConf, prop);
         return BoxedUnit.UNIT;
      });
      String filter = "org.apache.spark.deploy.yarn.AmIpFilter";
      String filterPrefix = "spark." + filter + ".param.";
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])newReloadConf.getAll()), (x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return k.startsWith(filterPrefix) && k.length() > filterPrefix.length() ? newSparkConf.set(k, v) : BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      });
      return newSparkConf;
   }

   public void validate() {
      scala.Predef..MODULE$.assert(this.master() != null, () -> "Checkpoint.master is null");
      scala.Predef..MODULE$.assert(this.framework() != null, () -> "Checkpoint.framework is null");
      scala.Predef..MODULE$.assert(this.graph() != null, () -> "Checkpoint.graph is null");
      scala.Predef..MODULE$.assert(this.checkpointTime() != null, () -> "Checkpoint.checkpointTime is null");
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpoint for time ", " validated"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_TIME..MODULE$, this.checkpointTime())})))));
   }

   // $FF: synthetic method
   public static final void $anonfun$createSparkConf$1(final SparkConf newReloadConf$1, final SparkConf newSparkConf$1, final String prop) {
      newReloadConf$1.getOption(prop).foreach((value) -> newSparkConf$1.set(prop, value));
   }

   public Checkpoint(final StreamingContext ssc, final Time checkpointTime) {
      this.checkpointTime = checkpointTime;
      Logging.$init$(this);
      this.master = ssc.sc().master();
      this.framework = ssc.sc().appName();
      this.jars = ssc.sc().jars();
      this.graph = ssc.graph();
      this.checkpointDir = ssc.checkpointDir();
      this.checkpointDuration = ssc.checkpointDuration();
      this.pendingTimes = (Time[])ssc.scheduler().getPendingTimes().toArray(scala.reflect.ClassTag..MODULE$.apply(Time.class));
      this.sparkConfPairs = ssc.conf().getAll();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
