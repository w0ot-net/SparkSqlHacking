package org.apache.spark.streaming.ui;

import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUITab;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3Q!\u0004\b\u0001%aA\u0001\u0002\n\u0001\u0003\u0006\u0004%\tA\n\u0005\tW\u0001\u0011\t\u0011)A\u0005O!AA\u0006\u0001B\u0001B\u0003%Q\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00047\u0001\t\u0007I\u0011B\u001c\t\r\u0001\u0003\u0001\u0015!\u00039\u0011\u001d\t\u0005A1A\u0005\u0002\tCaa\u0011\u0001!\u0002\u0013i\u0003b\u0002#\u0001\u0005\u0004%\t!\u0012\u0005\u0007\u0013\u0002\u0001\u000b\u0011\u0002$\t\u000b)\u0003A\u0011A&\t\u000bI\u0003A\u0011A&\u0003\u0019M#(/Z1nS:<G+\u00192\u000b\u0005=\u0001\u0012AA;j\u0015\t\t\"#A\u0005tiJ,\u0017-\\5oO*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xmE\u0002\u00013y\u0001\"A\u0007\u000f\u000e\u0003mQ!a\u0004\n\n\u0005uY\"AC*qCJ\\W+\u0013+bEB\u0011qDI\u0007\u0002A)\u0011\u0011EE\u0001\tS:$XM\u001d8bY&\u00111\u0005\t\u0002\b\u0019><w-\u001b8h\u0003\r\u00198oY\u0002\u0001+\u00059\u0003C\u0001\u0015*\u001b\u0005\u0001\u0012B\u0001\u0016\u0011\u0005A\u0019FO]3b[&twmQ8oi\u0016DH/\u0001\u0003tg\u000e\u0004\u0013aB:qCJ\\W+\u0013\t\u000359J!aL\u000e\u0003\u000fM\u0003\u0018M]6V\u0013\u00061A(\u001b8jiz\"2A\r\u001b6!\t\u0019\u0004!D\u0001\u000f\u0011\u0015!C\u00011\u0001(\u0011\u0015aC\u00011\u0001.\u0003M\u0019F+\u0011+J\u0007~\u0013ViU(V%\u000e+u\fR%S+\u0005A\u0004CA\u001d?\u001b\u0005Q$BA\u001e=\u0003\u0011a\u0017M\\4\u000b\u0003u\nAA[1wC&\u0011qH\u000f\u0002\u0007'R\u0014\u0018N\\4\u0002)M#\u0016\tV%D?J+5kT+S\u0007\u0016{F)\u0013*!\u0003\u0019\u0001\u0018M]3oiV\tQ&A\u0004qCJ,g\u000e\u001e\u0011\u0002\u00111L7\u000f^3oKJ,\u0012A\u0012\t\u0003g\u001dK!\u0001\u0013\b\u00039M#(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8fe\u0006IA.[:uK:,'\u000fI\u0001\u0007CR$\u0018m\u00195\u0015\u00031\u0003\"!\u0014)\u000e\u00039S\u0011aT\u0001\u0006g\u000e\fG.Y\u0005\u0003#:\u0013A!\u00168ji\u00061A-\u001a;bG\"\u0004"
)
public class StreamingTab extends SparkUITab implements Logging {
   private final StreamingContext ssc;
   private final String STATIC_RESOURCE_DIR;
   private final SparkUI parent;
   private final StreamingJobProgressListener listener;
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

   public StreamingContext ssc() {
      return this.ssc;
   }

   private String STATIC_RESOURCE_DIR() {
      return this.STATIC_RESOURCE_DIR;
   }

   public SparkUI parent() {
      return this.parent;
   }

   public StreamingJobProgressListener listener() {
      return this.listener;
   }

   public void attach() {
      this.parent().attachTab(this);
      this.parent().addStaticHandler(this.STATIC_RESOURCE_DIR(), "/static/streaming");
   }

   public void detach() {
      this.parent().detachTab(this);
      this.parent().detachHandler("/static/streaming");
   }

   public StreamingTab(final StreamingContext ssc, final SparkUI sparkUI) {
      super(sparkUI, "streaming");
      this.ssc = ssc;
      Logging.$init$(this);
      this.STATIC_RESOURCE_DIR = "org/apache/spark/ui/static";
      this.parent = sparkUI;
      this.listener = ssc.progressListener();
      this.attachPage(new StreamingPage(this));
      this.attachPage(new BatchPage(this));
   }
}
