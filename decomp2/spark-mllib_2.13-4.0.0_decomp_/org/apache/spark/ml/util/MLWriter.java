package org.apache.spark.ml.util;

import java.io.IOException;
import java.util.Locale;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Predef.;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a!\u0002\u0007\u000e\u0003\u0003A\u0002\"B\u0015\u0001\t\u0003Q\u0003b\u0002\u0017\u0001\u0001\u0004%\t\"\f\u0005\bc\u0001\u0001\r\u0011\"\u00053\u0011\u0019A\u0004\u0001)Q\u0005]!)\u0011\b\u0001C\u0001u!)q\f\u0001D\tA\")1\r\u0001C\u0001I\"9q\r\u0001b\u0001\n#A\u0007BB9\u0001A\u0003%\u0011\u000eC\u0003s\u0001\u0011\u00051\u000fC\u0003|\u0001\u0011\u0005CP\u0001\u0005N\u0019^\u0013\u0018\u000e^3s\u0015\tqq\"\u0001\u0003vi&d'B\u0001\t\u0012\u0003\tiGN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001\u0019B\u0001A\r GA\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\u0004\"\u0001I\u0011\u000e\u00035I!AI\u0007\u0003\u001b\t\u000b7/\u001a*fC\u0012<&/\u001b;f!\t!s%D\u0001&\u0015\t1\u0013#\u0001\u0005j]R,'O\\1m\u0013\tASEA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?)\u0005Y\u0003C\u0001\u0011\u0001\u0003=\u0019\bn\\;mI>3XM]<sSR,W#\u0001\u0018\u0011\u0005iy\u0013B\u0001\u0019\u001c\u0005\u001d\u0011un\u001c7fC:\f1c\u001d5pk2$wJ^3soJLG/Z0%KF$\"a\r\u001c\u0011\u0005i!\u0014BA\u001b\u001c\u0005\u0011)f.\u001b;\t\u000f]\u001a\u0011\u0011!a\u0001]\u0005\u0019\u0001\u0010J\u0019\u0002!MDw.\u001e7e\u001fZ,'o\u001e:ji\u0016\u0004\u0013\u0001B:bm\u0016$\"aM\u001e\t\u000bq*\u0001\u0019A\u001f\u0002\tA\fG\u000f\u001b\t\u0003}\u0015s!aP\"\u0011\u0005\u0001[R\"A!\u000b\u0005\t;\u0012A\u0002\u001fs_>$h(\u0003\u0002E7\u00051\u0001K]3eK\u001aL!AR$\u0003\rM#(/\u001b8h\u0015\t!5\u0004K\u0002\u0006\u0013>\u0003\"AS'\u000e\u0003-S!\u0001T\t\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002O\u0017\n)1+\u001b8dK\u0006\n\u0001+A\u00032]Yr\u0003\u0007K\u0002\u0006%v\u00032AG*V\u0013\t!6D\u0001\u0004uQJ|wo\u001d\t\u0003-nk\u0011a\u0016\u0006\u00031f\u000b!![8\u000b\u0003i\u000bAA[1wC&\u0011Al\u0016\u0002\f\u0013>+\u0005pY3qi&|g.I\u0001_\u0003yJe\r\t;iK\u0002Jg\u000e];uAA\fG\u000f\u001b\u0011bYJ,\u0017\rZ=!KbL7\u000f^:!EV$\be\u001c<fe^\u0014\u0018\u000e^3!SN\u0004cn\u001c;!K:\f'\r\\3e]\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u00024C\")AH\u0002a\u0001{!\u001aa!S(\u0002\u0013=4XM]<sSR,G#A3\u000e\u0003\u0001A3aB%P\u0003%y\u0007\u000f^5p]6\u000b\u0007/F\u0001j!\u0011Qw.P\u001f\u000e\u0003-T!\u0001\\7\u0002\u000f5,H/\u00192mK*\u0011anG\u0001\u000bG>dG.Z2uS>t\u0017B\u00019l\u0005\ri\u0015\r]\u0001\u000b_B$\u0018n\u001c8NCB\u0004\u0013AB8qi&|g\u000eF\u0002fiZDQ!\u001e\u0006A\u0002u\n1a[3z\u0011\u00159(\u00021\u0001>\u0003\u00151\u0018\r\\;fQ\rQ\u0011*_\u0011\u0002u\u0006)!GL\u001a/a\u000591/Z:tS>tGCA3~\u0011\u0015q8\u00021\u0001\u0000\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\u0011\t\t!a\u0002\u000e\u0005\u0005\r!bAA\u0003#\u0005\u00191/\u001d7\n\t\u0005%\u00111\u0001\u0002\r'B\f'o[*fgNLwN\u001c\u0015\u0004\u0017%{\u0005f\u0001\u0001J\u001f\u0002"
)
public abstract class MLWriter implements BaseReadWrite, Logging {
   private boolean shouldOverwrite;
   private final Map optionMap;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private Option org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public final SparkSession sparkSession() {
      return BaseReadWrite.sparkSession$(this);
   }

   public final SQLContext sqlContext() {
      return BaseReadWrite.sqlContext$(this);
   }

   public final SparkContext sc() {
      return BaseReadWrite.sc$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Option org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession() {
      return this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession;
   }

   public void org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession_$eq(final Option x$1) {
      this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession = x$1;
   }

   public boolean shouldOverwrite() {
      return this.shouldOverwrite;
   }

   public void shouldOverwrite_$eq(final boolean x$1) {
      this.shouldOverwrite = x$1;
   }

   public void save(final String path) throws IOException {
      (new FileSystemOverwrite()).handleOverwrite(path, this.shouldOverwrite(), this.sparkSession());
      this.saveImpl(path);
   }

   public abstract void saveImpl(final String path);

   public MLWriter overwrite() {
      this.shouldOverwrite_$eq(true);
      return this;
   }

   public Map optionMap() {
      return this.optionMap;
   }

   public MLWriter option(final String key, final String value) {
      .MODULE$.require(key != null && !key.isEmpty());
      this.optionMap().put(key.toLowerCase(Locale.ROOT), value);
      return this;
   }

   public MLWriter session(final SparkSession sparkSession) {
      return (MLWriter)BaseReadWrite.session$(this, sparkSession);
   }

   public MLWriter() {
      BaseReadWrite.$init$(this);
      Logging.$init$(this);
      this.shouldOverwrite = false;
      this.optionMap = new HashMap();
   }
}
