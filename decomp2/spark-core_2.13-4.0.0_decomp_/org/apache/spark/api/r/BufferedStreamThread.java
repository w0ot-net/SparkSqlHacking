package org.apache.spark.api.r;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.IndexedSeqOps;
import scala.collection.IterableOnceOps;
import scala.io.Source.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005q3Q\u0001D\u0007\u0001#]A\u0001B\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005_!AA\b\u0001B\u0001B\u0003%Q\bC\u0003B\u0001\u0011\u0005!\tC\u0004I\u0001\t\u0007I\u0011A%\t\r5\u0003\u0001\u0015!\u0003K\u0011\u001dq\u0005\u00011A\u0005\u0002=Cq\u0001\u0015\u0001A\u0002\u0013\u0005\u0011\u000b\u0003\u0004X\u0001\u0001\u0006K!\u0010\u0005\u00061\u0002!\t%\u0017\u0005\u00065\u0002!\ta\u0017\u0002\u0015\u0005V4g-\u001a:fIN#(/Z1n)\"\u0014X-\u00193\u000b\u00059y\u0011!\u0001:\u000b\u0005A\t\u0012aA1qS*\u0011!cE\u0001\u0006gB\f'o\u001b\u0006\u0003)U\ta!\u00199bG\",'\"\u0001\f\u0002\u0007=\u0014xmE\u0002\u00011\u0001\u0002\"!\u0007\u0010\u000e\u0003iQ!a\u0007\u000f\u0002\t1\fgn\u001a\u0006\u0002;\u0005!!.\u0019<b\u0013\ty\"D\u0001\u0004UQJ,\u0017\r\u001a\t\u0003C\u0011j\u0011A\t\u0006\u0003GE\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003K\t\u0012q\u0001T8hO&tw-\u0001\u0002j]\u000e\u0001\u0001CA\u0015-\u001b\u0005Q#BA\u0016\u001d\u0003\tIw.\u0003\u0002.U\tY\u0011J\u001c9viN#(/Z1n\u0003\u0011q\u0017-\\3\u0011\u0005AJdBA\u00198!\t\u0011T'D\u00014\u0015\t!t%\u0001\u0004=e>|GO\u0010\u0006\u0002m\u0005)1oY1mC&\u0011\u0001(N\u0001\u0007!J,G-\u001a4\n\u0005iZ$AB*ue&twM\u0003\u00029k\u0005iQM\u001d:Ck\u001a4WM]*ju\u0016\u0004\"AP \u000e\u0003UJ!\u0001Q\u001b\u0003\u0007%sG/\u0001\u0004=S:LGO\u0010\u000b\u0005\u0007\u00163u\t\u0005\u0002E\u00015\tQ\u0002C\u0003'\t\u0001\u0007\u0001\u0006C\u0003/\t\u0001\u0007q\u0006C\u0003=\t\u0001\u0007Q(A\u0003mS:,7/F\u0001K!\rq4jL\u0005\u0003\u0019V\u0012Q!\u0011:sCf\fa\u0001\\5oKN\u0004\u0013a\u00027j]\u0016LE\r_\u000b\u0002{\u0005YA.\u001b8f\u0013\u0012Dx\fJ3r)\t\u0011V\u000b\u0005\u0002?'&\u0011A+\u000e\u0002\u0005+:LG\u000fC\u0004W\u0011\u0005\u0005\t\u0019A\u001f\u0002\u0007a$\u0013'\u0001\u0005mS:,\u0017\n\u001a=!\u0003\r\u0011XO\u001c\u000b\u0002%\u0006Aq-\u001a;MS:,7\u000fF\u00010\u0001"
)
public class BufferedStreamThread extends Thread implements Logging {
   private final InputStream in;
   private final int errBufferSize;
   private final String[] lines;
   private int lineIdx;
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

   public String[] lines() {
      return this.lines;
   }

   public int lineIdx() {
      return this.lineIdx;
   }

   public void lineIdx_$eq(final int x$1) {
      this.lineIdx = x$1;
   }

   public void run() {
      .MODULE$.fromInputStream(this.in, scala.io.Codec..MODULE$.fallbackSystemCodec()).getLines().foreach((line) -> {
         $anonfun$run$4(this, line);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized String getLines() {
      return ((IterableOnceOps)((IndexedSeqOps)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.errBufferSize).filter((JFunction1.mcZI.sp)(x) -> this.lines()[(x + this.lineIdx()) % this.errBufferSize] != null)).map((x) -> $anonfun$getLines$2(this, BoxesRunTime.unboxToInt(x)))).mkString("\n");
   }

   // $FF: synthetic method
   public static final void $anonfun$run$4(final BufferedStreamThread $this, final String line) {
      synchronized($this){}

      try {
         $this.lines()[$this.lineIdx()] = line;
         $this.lineIdx_$eq(($this.lineIdx() + 1) % $this.errBufferSize);
      } catch (Throwable var4) {
         throw var4;
      }

      $this.logInfo((Function0)(() -> line));
   }

   // $FF: synthetic method
   public static final String $anonfun$getLines$2(final BufferedStreamThread $this, final int x) {
      return $this.lines()[(x + $this.lineIdx()) % $this.errBufferSize];
   }

   public BufferedStreamThread(final InputStream in, final String name, final int errBufferSize) {
      super(name);
      this.in = in;
      this.errBufferSize = errBufferSize;
      Logging.$init$(this);
      this.lines = new String[errBufferSize];
      this.lineIdx = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
