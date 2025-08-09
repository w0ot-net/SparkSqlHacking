package org.apache.spark.streaming.receiver;

import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.StreamingConf$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong.;

@ScalaSignature(
   bytes = "\u0006\u000593aAC\u0006\u0002\u0002-)\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u000b!\u0002A\u0011A\u0015\t\u000f5\u0002!\u0019!C\u0005]!1!\u0007\u0001Q\u0001\n=B\u0001b\r\u0001\t\u0006\u0004%I\u0001\u000e\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u000f\u0002!\tA\f\u0005\u0007\u0011\u0002!\taC%\t\u000b1\u0003A\u0011B'\u0003\u0017I\u000bG/\u001a'j[&$XM\u001d\u0006\u0003\u00195\t\u0001B]3dK&4XM\u001d\u0006\u0003\u001d=\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0011Q\u0004I\u0007\u0002=)\u0011qdD\u0001\tS:$XM\u001d8bY&\u0011\u0011E\b\u0002\b\u0019><w-\u001b8h\u0003\u0011\u0019wN\u001c4\u0004\u0001A\u0011QEJ\u0007\u0002\u001f%\u0011qe\u0004\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDC\u0001\u0016-!\tY\u0003!D\u0001\f\u0011\u0015\u0011#\u00011\u0001%\u00031i\u0017\r\u001f*bi\u0016d\u0015.\\5u+\u0005y\u0003CA\f1\u0013\t\t\u0004D\u0001\u0003M_:<\u0017!D7bqJ\u000bG/\u001a'j[&$\b%A\u0006sCR,G*[7ji\u0016\u0014X#A\u001b\u0011\u0005Y\nU\"A\u001c\u000b\u0005aJ\u0014AC2p]\u000e,(O]3oi*\u0011!hO\u0001\u0005kRLGN\u0003\u0002={\u000511m\\7n_:T!AP \u0002\r\u001d|wn\u001a7f\u0015\u0005\u0001\u0015aA2p[&\u0011!bN\u0001\u000bo\u0006LG\u000fV8QkNDG#\u0001#\u0011\u0005])\u0015B\u0001$\u0019\u0005\u0011)f.\u001b;\u0002\u001f\u001d,GoQ;se\u0016tG\u000fT5nSR\f!\"\u001e9eCR,'+\u0019;f)\t!%\nC\u0003L\u0011\u0001\u0007q&A\u0004oK^\u0014\u0016\r^3\u0002'\u001d,G/\u00138ji&\fGNU1uK2KW.\u001b;\u0015\u0003=\u0002"
)
public abstract class RateLimiter implements Logging {
   private org.sparkproject.guava.util.concurrent.RateLimiter rateLimiter;
   private final SparkConf conf;
   private final long maxRateLimit;
   private transient Logger org$apache$spark$internal$Logging$$log_;
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private long maxRateLimit() {
      return this.maxRateLimit;
   }

   private org.sparkproject.guava.util.concurrent.RateLimiter rateLimiter$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.rateLimiter = org.sparkproject.guava.util.concurrent.RateLimiter.create((double)this.getInitialRateLimit());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rateLimiter;
   }

   private org.sparkproject.guava.util.concurrent.RateLimiter rateLimiter() {
      return !this.bitmap$0 ? this.rateLimiter$lzycompute() : this.rateLimiter;
   }

   public void waitToPush() {
      this.rateLimiter().acquire();
   }

   public long getCurrentLimit() {
      return (long)this.rateLimiter().getRate();
   }

   public void updateRate(final long newRate) {
      if (newRate > 0L) {
         if (this.maxRateLimit() > 0L) {
            this.rateLimiter().setRate((double).MODULE$.min$extension(scala.Predef..MODULE$.longWrapper(newRate), this.maxRateLimit()));
         } else {
            this.rateLimiter().setRate((double)newRate);
         }
      }
   }

   private long getInitialRateLimit() {
      return scala.math.package..MODULE$.min(BoxesRunTime.unboxToLong(this.conf.get(StreamingConf$.MODULE$.BACKPRESSURE_INITIAL_RATE())), this.maxRateLimit());
   }

   public RateLimiter(final SparkConf conf) {
      this.conf = conf;
      Logging.$init$(this);
      this.maxRateLimit = BoxesRunTime.unboxToLong(conf.get(StreamingConf$.MODULE$.RECEIVER_MAX_RATE()));
   }
}
