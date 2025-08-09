package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.util.HexFormat;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.StringContext;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.math.package.;
import scala.runtime.BoxesRunTime;

public final class SparkStringUtils$ implements Logging {
   public static final SparkStringUtils$ MODULE$ = new SparkStringUtils$();
   private static HexFormat SPACE_DELIMITED_UPPERCASE_HEX;
   private static final AtomicBoolean truncationWarningPrinted;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      truncationWarningPrinted = new AtomicBoolean(false);
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private AtomicBoolean truncationWarningPrinted() {
      return truncationWarningPrinted;
   }

   public String truncatedString(final Seq seq, final String start, final String sep, final String end, final int maxFields, final Option customToString) {
      if (seq.length() > maxFields) {
         if (this.truncationWarningPrinted().compareAndSet(false, true)) {
            this.logWarning((Function0)(() -> "Truncated the string representation of a plan since it was too large. This behavior can be adjusted by setting 'spark.sql.debug.maxToStringFields'."));
         }

         int numFields = .MODULE$.max(0, maxFields);
         int restNum = seq.length() - numFields;
         String ending = (numFields == 0 ? "" : sep) + (restNum == 0 ? "" : "... " + restNum + " more fields") + end;
         return customToString.isDefined() ? ((IterableOnceOps)((IterableOps)seq.take(numFields)).map((Function1)customToString.get())).mkString(start, sep, ending) : ((IterableOnceOps)seq.take(numFields)).mkString(start, sep, ending);
      } else {
         return customToString.isDefined() ? ((IterableOnceOps)seq.map((Function1)customToString.get())).mkString(start, sep, end) : seq.mkString(start, sep, end);
      }
   }

   public String truncatedString(final Seq seq, final String sep, final int maxFields) {
      return this.truncatedString(seq, "", sep, "", maxFields, this.truncatedString$default$6());
   }

   public None truncatedString$default$6() {
      return scala.None..MODULE$;
   }

   private HexFormat SPACE_DELIMITED_UPPERCASE_HEX$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            SPACE_DELIMITED_UPPERCASE_HEX = HexFormat.of().withDelimiter(" ").withUpperCase();
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return SPACE_DELIMITED_UPPERCASE_HEX;
   }

   private final HexFormat SPACE_DELIMITED_UPPERCASE_HEX() {
      return !bitmap$0 ? this.SPACE_DELIMITED_UPPERCASE_HEX$lzycompute() : SPACE_DELIMITED_UPPERCASE_HEX;
   }

   public String getHexString(final byte[] bytes) {
      return "[" + this.SPACE_DELIMITED_UPPERCASE_HEX().formatHex(bytes) + "]";
   }

   public Seq sideBySide(final String left, final String right) {
      return this.sideBySide((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(left.split("\n")).toImmutableArraySeq(), (Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(right.split("\n")).toImmutableArraySeq());
   }

   public Seq sideBySide(final Seq left, final Seq right) {
      int maxLeftSize = BoxesRunTime.unboxToInt(((IterableOnceOps)left.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$sideBySide$1(x$1)))).max(scala.math.Ordering.Int..MODULE$));
      Seq leftPadded = (Seq)left.$plus$plus((IterableOnce)scala.package..MODULE$.Seq().fill(.MODULE$.max(right.size() - left.size(), 0), () -> ""));
      Seq rightPadded = (Seq)right.$plus$plus((IterableOnce)scala.package..MODULE$.Seq().fill(.MODULE$.max(left.size() - right.size(), 0), () -> ""));
      return (Seq)((IterableOps)leftPadded.zip(rightPadded)).map((x0$1) -> {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            String l;
            String r;
            String var10000;
            label30: {
               l = (String)x0$1._1();
               r = (String)x0$1._2();
               if (l == null) {
                  if (r == null) {
                     break label30;
                  }
               } else if (l.equals(r)) {
                  break label30;
               }

               var10000 = "!";
               return var10000 + l + scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), maxLeftSize - l.length() + 3) + r;
            }

            var10000 = " ";
            return var10000 + l + scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), maxLeftSize - l.length() + 3) + r;
         }
      });
   }

   // $FF: synthetic method
   public static final int $anonfun$sideBySide$1(final String x$1) {
      return x$1.length();
   }

   private SparkStringUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
