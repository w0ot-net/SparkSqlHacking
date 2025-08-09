package org.apache.spark.broadcast;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.collections4.map.ReferenceMap;
import org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength;
import org.apache.spark.SparkConf;
import org.apache.spark.api.python.PythonBroadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c!\u0002\u000b\u0016\u0001]i\u0002\u0002\u0003\u0016\u0001\u0005\u000b\u0007I\u0011\u0001\u0017\t\u0011A\u0002!\u0011!Q\u0001\n5B\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\u0006m\u0001!\ta\u000e\u0005\by\u0001\u0001\r\u0011\"\u0003-\u0011\u001di\u0004\u00011A\u0005\nyBa\u0001\u0012\u0001!B\u0013i\u0003bB#\u0001\u0001\u0004%IA\u0012\u0005\b\u0015\u0002\u0001\r\u0011\"\u0003L\u0011\u0019i\u0005\u0001)Q\u0005\u000f\")a\n\u0001C\u0005\u001f\")\u0001\u000b\u0001C\u0001\u001f\"9\u0011\u000b\u0001b\u0001\n\u0013\u0011\u0006BB0\u0001A\u0003%1\u000b\u0003\u0005a\u0001\t\u0007I\u0011A\u000bb\u0011\u0019I\u0007\u0001)A\u0005E\")!\u000e\u0001C\u0001W\"I\u0011q\u0002\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0003\u0005\b\u0003W\u0001A\u0011AA\u0017\u0005A\u0011%o\\1eG\u0006\u001cH/T1oC\u001e,'O\u0003\u0002\u0017/\u0005I!M]8bI\u000e\f7\u000f\u001e\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sON\u0019\u0001A\b\u0013\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\r\u0005s\u0017PU3g!\t)\u0003&D\u0001'\u0015\t9s#\u0001\u0005j]R,'O\\1m\u0013\tIcEA\u0004M_\u001e<\u0017N\\4\u0002\u0011%\u001cHI]5wKJ\u001c\u0001!F\u0001.!\tyb&\u0003\u00020A\t9!i\\8mK\u0006t\u0017!C5t\tJLg/\u001a:!\u0003\u0011\u0019wN\u001c4\u0011\u0005M\"T\"A\f\n\u0005U:\"!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0019\u0001HO\u001e\u0011\u0005e\u0002Q\"A\u000b\t\u000b)\"\u0001\u0019A\u0017\t\u000bE\"\u0001\u0019\u0001\u001a\u0002\u0017%t\u0017\u000e^5bY&TX\rZ\u0001\u0010S:LG/[1mSj,Gm\u0018\u0013fcR\u0011qH\u0011\t\u0003?\u0001K!!\u0011\u0011\u0003\tUs\u0017\u000e\u001e\u0005\b\u0007\u001a\t\t\u00111\u0001.\u0003\rAH%M\u0001\rS:LG/[1mSj,G\rI\u0001\u0011EJ|\u0017\rZ2bgR4\u0015m\u0019;pef,\u0012a\u0012\t\u0003s!K!!S\u000b\u0003!\t\u0013x.\u00193dCN$h)Y2u_JL\u0018\u0001\u00062s_\u0006$7-Y:u\r\u0006\u001cGo\u001c:z?\u0012*\u0017\u000f\u0006\u0002@\u0019\"91)CA\u0001\u0002\u00049\u0015!\u00052s_\u0006$7-Y:u\r\u0006\u001cGo\u001c:zA\u0005Q\u0011N\\5uS\u0006d\u0017N_3\u0015\u0003}\nAa\u001d;pa\u0006ya.\u001a=u\u0005J|\u0017\rZ2bgRLE-F\u0001T!\t!V,D\u0001V\u0015\t1v+\u0001\u0004bi>l\u0017n\u0019\u0006\u00031f\u000b!bY8oGV\u0014(/\u001a8u\u0015\tQ6,\u0001\u0003vi&d'\"\u0001/\u0002\t)\fg/Y\u0005\u0003=V\u0013!\"\u0011;p[&\u001cGj\u001c8h\u0003AqW\r\u001f;Ce>\fGmY1ti&#\u0007%\u0001\u0007dC\u000eDW\r\u001a,bYV,7/F\u0001c!\u0011\u0019GM\u001a4\u000e\u0003eK!!Z-\u0003\u00075\u000b\u0007\u000f\u0005\u0002 O&\u0011\u0001\u000e\t\u0002\u0004\u0003:L\u0018!D2bG\",GMV1mk\u0016\u001c\b%\u0001\u0007oK^\u0014%o\\1eG\u0006\u001cH/\u0006\u0002mgR9Q.a\u0001\u0002\b\u0005-AC\u00018z!\rIt.]\u0005\u0003aV\u0011\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u0011\u0005I\u001cH\u0002\u0001\u0003\u0006iF\u0011\r!\u001e\u0002\u0002)F\u0011aO\u001a\t\u0003?]L!\u0001\u001f\u0011\u0003\u000f9{G\u000f[5oO\"9!0EA\u0001\u0002\bY\u0018AC3wS\u0012,gnY3%cA\u0019Ap`9\u000e\u0003uT!A \u0011\u0002\u000fI,g\r\\3di&\u0019\u0011\u0011A?\u0003\u0011\rc\u0017m]:UC\u001eDa!!\u0002\u0012\u0001\u0004\t\u0018A\u0002<bYV,w\f\u0003\u0004\u0002\nE\u0001\r!L\u0001\bSNdunY1m\u0011!\ti!\u0005I\u0001\u0002\u0004i\u0013AD:fe&\fG.\u001b>fI>sG._\u0001\u0017]\u0016<(I]8bI\u000e\f7\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%gU!\u00111CA\u0015+\t\t)BK\u0002.\u0003/Y#!!\u0007\u0011\t\u0005m\u0011QE\u0007\u0003\u0003;QA!a\b\u0002\"\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003G\u0001\u0013AC1o]>$\u0018\r^5p]&!\u0011qEA\u000f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006iJ\u0011\r!^\u0001\fk:\u0014'o\\1eG\u0006\u001cH\u000fF\u0004@\u0003_\tI$!\u0010\t\u000f\u0005E2\u00031\u0001\u00024\u0005\u0011\u0011\u000e\u001a\t\u0004?\u0005U\u0012bAA\u001cA\t!Aj\u001c8h\u0011\u0019\tYd\u0005a\u0001[\u0005\u0001\"/Z7pm\u00164%o\\7Ee&4XM\u001d\u0005\u0007\u0003\u007f\u0019\u0002\u0019A\u0017\u0002\u0011\tdwnY6j]\u001e\u0004"
)
public class BroadcastManager implements Logging {
   private final boolean isDriver;
   private final SparkConf conf;
   private boolean initialized;
   private BroadcastFactory broadcastFactory;
   private final AtomicLong nextBroadcastId;
   private final Map cachedValues;
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

   public boolean isDriver() {
      return this.isDriver;
   }

   private boolean initialized() {
      return this.initialized;
   }

   private void initialized_$eq(final boolean x$1) {
      this.initialized = x$1;
   }

   private BroadcastFactory broadcastFactory() {
      return this.broadcastFactory;
   }

   private void broadcastFactory_$eq(final BroadcastFactory x$1) {
      this.broadcastFactory = x$1;
   }

   private synchronized void initialize() {
      if (!this.initialized()) {
         this.broadcastFactory_$eq(new TorrentBroadcastFactory());
         this.broadcastFactory().initialize(this.isDriver(), this.conf);
         this.initialized_$eq(true);
      }
   }

   public void stop() {
      this.broadcastFactory().stop();
   }

   private AtomicLong nextBroadcastId() {
      return this.nextBroadcastId;
   }

   public Map cachedValues() {
      return this.cachedValues;
   }

   public Broadcast newBroadcast(final Object value_, final boolean isLocal, final boolean serializedOnly, final ClassTag evidence$1) {
      long bid = this.nextBroadcastId().getAndIncrement();
      if (value_ instanceof PythonBroadcast var9) {
         var9.setBroadcastId(bid);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10 = BoxedUnit.UNIT;
      }

      return this.broadcastFactory().newBroadcast(value_, isLocal, bid, serializedOnly, evidence$1);
   }

   public boolean newBroadcast$default$3() {
      return false;
   }

   public void unbroadcast(final long id, final boolean removeFromDriver, final boolean blocking) {
      this.broadcastFactory().unbroadcast(id, removeFromDriver, blocking);
   }

   public BroadcastManager(final boolean isDriver, final SparkConf conf) {
      this.isDriver = isDriver;
      this.conf = conf;
      Logging.$init$(this);
      this.initialized = false;
      this.broadcastFactory = null;
      this.initialize();
      this.nextBroadcastId = new AtomicLong(0L);
      this.cachedValues = Collections.synchronizedMap(new ReferenceMap(ReferenceStrength.HARD, ReferenceStrength.WEAK));
   }
}
