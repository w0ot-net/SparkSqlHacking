package org.apache.spark.util.logging;

import java.lang.invoke.SerializedLambda;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma!B\u000b\u0017\u0001i\u0001\u0003\u0002C\u0019\u0001\u0005\u0003\u0007I\u0011A\u001a\t\u0011]\u0002!\u00111A\u0005\u0002aB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006K\u0001\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\")1\t\u0001C\u0001\t\"9\u0001\n\u0001a\u0001\n\u0013\u0019\u0004bB%\u0001\u0001\u0004%IA\u0013\u0005\u0007\u0019\u0002\u0001\u000b\u0015\u0002\u001b\t\u000fE\u0003!\u0019!C\u0001%\"11\f\u0001Q\u0001\nMCQ\u0001\u0018\u0001\u0005\u0002uCQ\u0001\u0019\u0001\u0005\u0002\u0005DQA\u0019\u0001\u0005\u0002\rDQA\u001a\u0001\u0005\u0002\u001d<aa\u001d\f\t\u0002i!hAB\u000b\u0017\u0011\u0003QR\u000fC\u0003D!\u0011\u0005a\u000fC\u0004x!\t\u0007I\u0011\u0001=\t\rq\u0004\u0002\u0015!\u0003z\u0011\u001di\b#%A\u0005\u0002y\u0014acU5{K\n\u000b7/\u001a3S_2d\u0017N\\4Q_2L7-\u001f\u0006\u0003/a\tq\u0001\\8hO&twM\u0003\u0002\u001a5\u0005!Q\u000f^5m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7\u0003\u0002\u0001\"O-\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u0015*\u001b\u00051\u0012B\u0001\u0016\u0017\u00055\u0011v\u000e\u001c7j]\u001e\u0004v\u000e\\5dsB\u0011AfL\u0007\u0002[)\u0011aFG\u0001\tS:$XM\u001d8bY&\u0011\u0001'\f\u0002\b\u0019><w-\u001b8h\u0003E\u0011x\u000e\u001c7pm\u0016\u00148+\u001b>f\u0005f$Xm]\u0002\u0001+\u0005!\u0004C\u0001\u00126\u0013\t14E\u0001\u0003M_:<\u0017!\u0006:pY2|g/\u001a:TSj,')\u001f;fg~#S-\u001d\u000b\u0003sq\u0002\"A\t\u001e\n\u0005m\u001a#\u0001B+oSRDq!\u0010\u0002\u0002\u0002\u0003\u0007A'A\u0002yIE\n!C]8mY>4XM]*ju\u0016\u0014\u0015\u0010^3tA\u0005\u00192\r[3dWNK'0Z\"p]N$(/Y5oiB\u0011!%Q\u0005\u0003\u0005\u000e\u0012qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0004\u000b\u001a;\u0005C\u0001\u0015\u0001\u0011\u0015\tT\u00011\u00015\u0011\u001dyT\u0001%AA\u0002\u0001\u000b\u0011DY=uKN<&/\u001b;uK:\u001c\u0016N\\2f%>dGn\u001c<fe\u0006i\"-\u001f;fg^\u0013\u0018\u000e\u001e;f]NKgnY3S_2dwN^3s?\u0012*\u0017\u000f\u0006\u0002:\u0017\"9QhBA\u0001\u0002\u0004!\u0014A\u00072zi\u0016\u001cxK]5ui\u0016t7+\u001b8dKJ{G\u000e\\8wKJ\u0004\u0003F\u0001\u0005O!\t\u0011s*\u0003\u0002QG\tAao\u001c7bi&dW-A\u0005g_Jl\u0017\r\u001e;feV\t1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006!A/\u001a=u\u0015\u0005A\u0016\u0001\u00026bm\u0006L!AW+\u0003!MKW\u000e\u001d7f\t\u0006$XMR8s[\u0006$\u0018A\u00034pe6\fG\u000f^3sA\u0005q1\u000f[8vY\u0012\u0014v\u000e\u001c7pm\u0016\u0014HC\u0001!_\u0011\u0015y6\u00021\u00015\u0003A\u0011\u0017\u0010^3t)>\u0014Um\u0016:jiR,g.\u0001\u0006s_2dW\rZ(wKJ$\u0012!O\u0001\rEf$Xm],sSR$XM\u001c\u000b\u0003s\u0011DQ!Z\u0007A\u0002Q\nQAY=uKN\fAdZ3oKJ\fG/\u001a*pY2,Gm\u0014<fe\u001aKG.Z*vM\u001aL\u0007\u0010F\u0001i!\tI\u0007O\u0004\u0002k]B\u00111nI\u0007\u0002Y*\u0011QNM\u0001\u0007yI|w\u000e\u001e \n\u0005=\u001c\u0013A\u0002)sK\u0012,g-\u0003\u0002re\n11\u000b\u001e:j]\u001eT!a\\\u0012\u0002-MK'0\u001a\"bg\u0016$'k\u001c7mS:<\u0007k\u001c7jGf\u0004\"\u0001\u000b\t\u0014\u0005A\tC#\u0001;\u0002%5Ke*S'V\u001b~\u001b\u0016JW#`\u0005f#ViU\u000b\u0002sB\u0011!E_\u0005\u0003w\u000e\u00121!\u00138u\u0003Mi\u0015JT%N+6{6+\u0013.F?\nKF+R*!\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\tqPK\u0002A\u0003\u0003Y#!a\u0001\u0011\t\u0005\u0015\u0011qB\u0007\u0003\u0003\u000fQA!!\u0003\u0002\f\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u001b\u0019\u0013AC1o]>$\u0018\r^5p]&!\u0011\u0011CA\u0004\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class SizeBasedRollingPolicy implements RollingPolicy, Logging {
   private long rolloverSizeBytes;
   private volatile long bytesWrittenSinceRollover;
   private final SimpleDateFormat formatter;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$2() {
      return SizeBasedRollingPolicy$.MODULE$.$lessinit$greater$default$2();
   }

   public static int MINIMUM_SIZE_BYTES() {
      return SizeBasedRollingPolicy$.MODULE$.MINIMUM_SIZE_BYTES();
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

   public long rolloverSizeBytes() {
      return this.rolloverSizeBytes;
   }

   public void rolloverSizeBytes_$eq(final long x$1) {
      this.rolloverSizeBytes = x$1;
   }

   private long bytesWrittenSinceRollover() {
      return this.bytesWrittenSinceRollover;
   }

   private void bytesWrittenSinceRollover_$eq(final long x$1) {
      this.bytesWrittenSinceRollover = x$1;
   }

   public SimpleDateFormat formatter() {
      return this.formatter;
   }

   public boolean shouldRollover(final long bytesToBeWritten) {
      this.logDebug((Function0)(() -> bytesToBeWritten + " + " + this.bytesWrittenSinceRollover() + " > " + this.rolloverSizeBytes()));
      return bytesToBeWritten + this.bytesWrittenSinceRollover() > this.rolloverSizeBytes();
   }

   public void rolledOver() {
      this.bytesWrittenSinceRollover_$eq(0L);
   }

   public void bytesWritten(final long bytes) {
      this.bytesWrittenSinceRollover_$eq(this.bytesWrittenSinceRollover() + bytes);
   }

   public String generateRolledOverFileSuffix() {
      return this.formatter().format(Calendar.getInstance().getTime());
   }

   public SizeBasedRollingPolicy(final long rolloverSizeBytes, final boolean checkSizeConstraint) {
      this.rolloverSizeBytes = rolloverSizeBytes;
      super();
      Logging.$init$(this);
      if (checkSizeConstraint && this.rolloverSizeBytes() < (long)SizeBasedRollingPolicy$.MODULE$.MINIMUM_SIZE_BYTES()) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling size [", " bytes] is too small. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(this.rolloverSizeBytes()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Setting the size to the acceptable minimum of ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SIZE..MODULE$, BoxesRunTime.boxToInteger(SizeBasedRollingPolicy$.MODULE$.MINIMUM_SIZE_BYTES()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"bytes."})))).log(scala.collection.immutable.Nil..MODULE$))));
         this.rolloverSizeBytes_$eq((long)SizeBasedRollingPolicy$.MODULE$.MINIMUM_SIZE_BYTES());
      }

      this.bytesWrittenSinceRollover = 0L;
      this.formatter = new SimpleDateFormat("--yyyy-MM-dd--HH-mm-ss--SSSS", Locale.US);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
