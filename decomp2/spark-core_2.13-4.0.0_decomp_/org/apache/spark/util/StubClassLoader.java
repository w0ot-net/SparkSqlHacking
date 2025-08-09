package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4QAC\u0006\u0001\u001bMA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\u0006\u0005\tI\u0001\u0011\t\u0011)A\u0005K!)\u0011\b\u0001C\u0001u!)q\b\u0001C!\u0001\u001e1!k\u0003E\u0001\u001bM3aAC\u0006\t\u00025!\u0006\"B\u001d\u0007\t\u0003A\u0006\"B-\u0007\t\u0003Q\u0006\"B4\u0007\t\u0003A'aD*uk\n\u001cE.Y:t\u0019>\fG-\u001a:\u000b\u00051i\u0011\u0001B;uS2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\n\u0004\u0001Qa\u0002CA\u000b\u001b\u001b\u00051\"BA\f\u0019\u0003\u0011a\u0017M\\4\u000b\u0003e\tAA[1wC&\u00111D\u0006\u0002\f\u00072\f7o\u001d'pC\u0012,'\u000f\u0005\u0002\u001eA5\taD\u0003\u0002 \u001b\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\"=\t9Aj\\4hS:<\u0017A\u00029be\u0016tGo\u0001\u0001\u0002\u0015MDw.\u001e7e'R,(\r\u0005\u0003'S-2T\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u00174\u001d\ti\u0013\u0007\u0005\u0002/O5\tqF\u0003\u00021G\u00051AH]8pizJ!AM\u0014\u0002\rA\u0013X\rZ3g\u0013\t!TG\u0001\u0004TiJLgn\u001a\u0006\u0003e\u001d\u0002\"AJ\u001c\n\u0005a:#a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007mjd\b\u0005\u0002=\u00015\t1\u0002C\u0003#\u0007\u0001\u0007A\u0003C\u0003%\u0007\u0001\u0007Q%A\u0005gS:$7\t\\1tgR\u0011\u0011\t\u0015\u0019\u0003\u0005\u001e\u00032\u0001L\"F\u0013\t!UGA\u0003DY\u0006\u001c8\u000f\u0005\u0002G\u000f2\u0001A!\u0003%\u0005\u0003\u0003\u0005\tQ!\u0001J\u0005\ryF%M\t\u0003\u00156\u0003\"AJ&\n\u00051;#a\u0002(pi\"Lgn\u001a\t\u0003M9K!aT\u0014\u0003\u0007\u0005s\u0017\u0010C\u0003R\t\u0001\u00071&\u0001\u0003oC6,\u0017aD*uk\n\u001cE.Y:t\u0019>\fG-\u001a:\u0011\u0005q21C\u0001\u0004V!\t1c+\u0003\u0002XO\t1\u0011I\\=SK\u001a$\u0012aU\u0001\u0006CB\u0004H.\u001f\u000b\u0004wmc\u0006\"\u0002\u0012\t\u0001\u0004!\u0002\"B/\t\u0001\u0004q\u0016A\u00032j]\u0006\u0014\u0018PT1nKB\u0019q\fZ\u0016\u000f\u0005\u0001\u0014gB\u0001\u0018b\u0013\u0005A\u0013BA2(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u001a4\u0003\u0007M+\u0017O\u0003\u0002dO\u0005aq-\u001a8fe\u0006$Xm\u0015;vER\u0011\u0011n\u001c\t\u0004M)d\u0017BA6(\u0005\u0015\t%O]1z!\t1S.\u0003\u0002oO\t!!)\u001f;f\u0011\u0015i\u0016\u00021\u0001,\u0001"
)
public class StubClassLoader extends ClassLoader implements Logging {
   private final Function1 shouldStub;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static byte[] generateStub(final String binaryName) {
      return StubClassLoader$.MODULE$.generateStub(binaryName);
   }

   public static StubClassLoader apply(final ClassLoader parent, final Seq binaryName) {
      return StubClassLoader$.MODULE$.apply(parent, binaryName);
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

   public Class findClass(final String name) {
      if (!BoxesRunTime.unboxToBoolean(this.shouldStub.apply(name))) {
         throw new ClassNotFoundException(name);
      } else {
         this.logDebug((Function0)(() -> "Generating stub for " + name));
         byte[] bytes = StubClassLoader$.MODULE$.generateStub(name);
         return this.defineClass(name, bytes, 0, bytes.length);
      }
   }

   public StubClassLoader(final ClassLoader parent, final Function1 shouldStub) {
      super(parent);
      this.shouldStub = shouldStub;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
