package org.apache.spark;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553Qa\u0002\u0005\u0001\u00119A\u0001\u0002\u000b\u0001\u0003\u0002\u0004%\t!\u000b\u0005\t[\u0001\u0011\t\u0019!C\u0001]!AA\u0007\u0001B\u0001B\u0003&!\u0006C\u00036\u0001\u0011\u0005a\u0007C\u0003;\u0001\u0011%1\bC\u0003G\u0001\u0011%qIA\u000fUQJ|w/\u00192mKN+'/[1mSj\fG/[8o/J\f\u0007\u000f]3s\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7\u0003\u0002\u0001\u0010+\t\u0002\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f \u001d\t9RD\u0004\u0002\u001995\t\u0011D\u0003\u0002\u001b7\u00051AH]8piz\u001a\u0001!C\u0001\u0013\u0013\tq\u0012#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0001\n#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0010\u0012!\t\u0019c%D\u0001%\u0015\t)\u0003\"\u0001\u0005j]R,'O\\1m\u0013\t9CEA\u0004M_\u001e<\u0017N\\4\u0002\u0013\u0015D8-\u001a9uS>tW#\u0001\u0016\u0011\u0005YY\u0013B\u0001\u0017\"\u0005%!\u0006N]8xC\ndW-A\u0007fq\u000e,\u0007\u000f^5p]~#S-\u001d\u000b\u0003_I\u0002\"\u0001\u0005\u0019\n\u0005E\n\"\u0001B+oSRDqa\r\u0002\u0002\u0002\u0003\u0007!&A\u0002yIE\n!\"\u001a=dKB$\u0018n\u001c8!\u0003\u0019a\u0014N\\5u}Q\u0011q'\u000f\t\u0003q\u0001i\u0011\u0001\u0003\u0005\u0006Q\u0011\u0001\rAK\u0001\foJLG/Z(cU\u0016\u001cG\u000f\u0006\u00020y!)Q(\u0002a\u0001}\u0005\u0019q.\u001e;\u0011\u0005}\"U\"\u0001!\u000b\u0005\u0005\u0013\u0015AA5p\u0015\u0005\u0019\u0015\u0001\u00026bm\u0006L!!\u0012!\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u000be\u0016\fGm\u00142kK\u000e$HCA\u0018I\u0011\u0015Ie\u00011\u0001K\u0003\tIg\u000e\u0005\u0002@\u0017&\u0011A\n\u0011\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007"
)
public class ThrowableSerializationWrapper implements Serializable, Logging {
   private Throwable exception;
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

   public Throwable exception() {
      return this.exception;
   }

   public void exception_$eq(final Throwable x$1) {
      this.exception = x$1;
   }

   private void writeObject(final ObjectOutputStream out) {
      out.writeObject(this.exception());
   }

   private void readObject(final ObjectInputStream in) {
      try {
         this.exception_$eq((Throwable)in.readObject());
      } catch (Exception var3) {
         this.log().warn("Task exception could not be deserialized", var3);
      }

   }

   public ThrowableSerializationWrapper(final Throwable exception) {
      this.exception = exception;
      super();
      Logging.$init$(this);
   }
}
