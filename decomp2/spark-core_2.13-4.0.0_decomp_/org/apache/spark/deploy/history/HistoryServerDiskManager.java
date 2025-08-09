package org.apache.spark.deploy.history;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.History;
import org.apache.spark.internal.config.History$;
import org.apache.spark.status.KVUtils$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.KVStore;
import org.apache.spark.util.kvstore.KVStoreIterator;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}h\u0001\u0002\u0017.\taB\u0001\"\u0012\u0001\u0003\u0002\u0003\u0006IA\u0012\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u0017\"A1\u000b\u0001B\u0001B\u0003%A\u000b\u0003\u0005]\u0001\t\u0005\t\u0015!\u0003^\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u001dI\u0007A1A\u0005\n)Daa\u001b\u0001!\u0002\u0013Y\u0005b\u00027\u0001\u0005\u0004%I!\u001c\u0005\u0007i\u0002\u0001\u000b\u0011\u00028\t\u000fU\u0004!\u0019!C\u0005U\"1a\u000f\u0001Q\u0001\n-Cqa\u001e\u0001C\u0002\u0013%\u0001\u0010\u0003\u0004}\u0001\u0001\u0006I!\u001f\u0005\b{\u0002\u0011\r\u0011\"\u0003\u007f\u0011\u001d\t\t\u0002\u0001Q\u0001\n}D\u0001\"a\u0005\u0001\u0005\u0004%IA \u0005\b\u0003+\u0001\u0001\u0015!\u0003\u0000\u0011%\t9\u0002\u0001b\u0001\n\u0013\tI\u0002\u0003\u0005\u0002L\u0001\u0001\u000b\u0011BA\u000e\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u001fBq!a\u0016\u0001\t\u0003\tI\u0006C\u0005\u0002\u000e\u0002\t\n\u0011\"\u0001\u0002\u0010\"9\u0011Q\u0015\u0001\u0005\u0002\u0005\u001d\u0006bBAX\u0001\u0011\u0005\u0011\u0011\u0017\u0005\n\u0003w\u0003\u0011\u0013!C\u0001\u0003\u001fCq!!0\u0001\t\u0003\ty\fC\u0004\u0002F\u0002!\t!a2\t\u000f\u0005%\u0007\u0001\"\u0001\u0002H\"9\u00111\u001a\u0001\u0005\n\u00055\u0007bBAi\u0001\u0011%\u00111\u001b\u0005\t\u00033\u0004A\u0011A\u0017\u0002\\\"9\u0011\u0011\u001d\u0001\u0005\n\u0005\r\bbBAw\u0001\u0011%\u0011q\u001e\u0005\n\u0003o\u0004\u0011\u0013!C\u0005\u0003\u001fC\u0001\"!?\u0001\t\u0003i\u00131 \u0004\b\u0003?\u0002\u0001!LA1\u0011%\t\u0019\u0007\nBC\u0002\u0013\u0005!\u000eC\u0005\u0002f\u0011\u0012\t\u0011)A\u0005\u0017\"I\u0011q\r\u0013\u0003\u0006\u0004%I\u0001\u001f\u0005\n\u0003S\"#\u0011!Q\u0001\neDa!\u0019\u0013\u0005\u0002\u0005-\u0004bBA9I\u0011\u0005\u00111\u000f\u0005\b\u0003{\"C\u0011AA(\u0005aA\u0015n\u001d;pef\u001cVM\u001d<fe\u0012K7o['b]\u0006<WM\u001d\u0006\u0003]=\nq\u0001[5ti>\u0014\u0018P\u0003\u00021c\u00051A-\u001a9m_fT!AM\u001a\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q*\u0014AB1qC\u000eDWMC\u00017\u0003\ry'oZ\u0002\u0001'\r\u0001\u0011h\u0010\t\u0003uuj\u0011a\u000f\u0006\u0002y\u0005)1oY1mC&\u0011ah\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0001\u001bU\"A!\u000b\u0005\t\u000b\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0011\u000b%a\u0002'pO\u001eLgnZ\u0001\u0005G>tg\r\u0005\u0002H\u00116\t\u0011'\u0003\u0002Jc\tI1\u000b]1sW\u000e{gNZ\u0001\u0005a\u0006$\b\u000e\u0005\u0002M#6\tQJ\u0003\u0002O\u001f\u0006\u0011\u0011n\u001c\u0006\u0002!\u0006!!.\u0019<b\u0013\t\u0011VJ\u0001\u0003GS2,\u0017a\u00027jgRLgn\u001a\t\u0003+jk\u0011A\u0016\u0006\u0003/b\u000bqa\u001b<ti>\u0014XM\u0003\u0002Zc\u0005!Q\u000f^5m\u0013\tYfKA\u0004L-N#xN]3\u0002\u000b\rdwnY6\u0011\u0005y{V\"\u0001-\n\u0005\u0001D&!B\"m_\u000e\\\u0017A\u0002\u001fj]&$h\bF\u0003dK\u001a<\u0007\u000e\u0005\u0002e\u00015\tQ\u0006C\u0003F\u000b\u0001\u0007a\tC\u0003K\u000b\u0001\u00071\nC\u0003T\u000b\u0001\u0007A\u000bC\u0003]\u000b\u0001\u0007Q,A\u0006baB\u001cFo\u001c:f\t&\u0014X#A&\u0002\u0019\u0005\u0004\bo\u0015;pe\u0016$\u0015N\u001d\u0011\u0002\u0013\u0015DH/\u001a8tS>tW#\u00018\u0011\u0005=\u0014X\"\u00019\u000b\u0005E|\u0015\u0001\u00027b]\u001eL!a\u001d9\u0003\rM#(/\u001b8h\u0003))\u0007\u0010^3og&|g\u000eI\u0001\fi6\u00048\u000b^8sK\u0012K'/\u0001\u0007u[B\u001cFo\u001c:f\t&\u0014\b%\u0001\u0005nCb,6/Y4f+\u0005I\bC\u0001\u001e{\u0013\tY8H\u0001\u0003M_:<\u0017!C7bqV\u001b\u0018mZ3!\u00031\u0019WO\u001d:f]R,6/Y4f+\u0005y\b\u0003BA\u0001\u0003\u001bi!!a\u0001\u000b\t\u0005\u0015\u0011qA\u0001\u0007CR|W.[2\u000b\t\u0005%\u00111B\u0001\u000bG>t7-\u001e:sK:$(BA-P\u0013\u0011\ty!a\u0001\u0003\u0015\u0005#x.\\5d\u0019>tw-A\u0007dkJ\u0014XM\u001c;Vg\u0006<W\rI\u0001\u000fG>lW.\u001b;uK\u0012,6/Y4f\u0003=\u0019w.\\7jiR,G-V:bO\u0016\u0004\u0013AB1di&4X-\u0006\u0002\u0002\u001cA9\u0011QDA\u0014\u0003WIXBAA\u0010\u0015\u0011\t\t#a\t\u0002\u000f5,H/\u00192mK*\u0019\u0011QE\u001e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002*\u0005}!a\u0002%bg\"l\u0015\r\u001d\t\bu\u00055\u0012\u0011GA#\u0013\r\tyc\u000f\u0002\u0007)V\u0004H.\u001a\u001a\u0011\t\u0005M\u0012\u0011\t\b\u0005\u0003k\ti\u0004E\u0002\u00028mj!!!\u000f\u000b\u0007\u0005mr'\u0001\u0004=e>|GOP\u0005\u0004\u0003\u007fY\u0014A\u0002)sK\u0012,g-C\u0002t\u0003\u0007R1!a\u0010<!\u0015Q\u0014qIA\u0019\u0013\r\tIe\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u000f\u0005\u001cG/\u001b<fA\u0005Q\u0011N\\5uS\u0006d\u0017N_3\u0015\u0005\u0005E\u0003c\u0001\u001e\u0002T%\u0019\u0011QK\u001e\u0003\tUs\u0017\u000e^\u0001\u0006Y\u0016\f7/\u001a\u000b\u0007\u00037\ny(a!\u0011\u0007\u0005uC%D\u0001\u0001\u0005\u0015aU-Y:f'\t!\u0013(A\u0004u[B\u0004\u0016\r\u001e5\u0002\u0011Ql\u0007\u000fU1uQ\u0002\na\u0001\\3bg\u0016$\u0017a\u00027fCN,G\r\t\u000b\u0007\u00037\ni'a\u001c\t\r\u0005\r\u0014\u00061\u0001L\u0011\u0019\t9'\u000ba\u0001s\u000611m\\7nSR$RaSA;\u0003sBq!a\u001e+\u0001\u0004\t\t$A\u0003baBLE\rC\u0004\u0002|)\u0002\r!!\u0012\u0002\u0013\u0005$H/Z7qi&#\u0017\u0001\u0003:pY2\u0014\u0017mY6\t\r\u0005\u0005U\u00031\u0001z\u00031)g/\u001a8u\u0019><7+\u001b>f\u0011%\t))\u0006I\u0001\u0002\u0004\t9)\u0001\u0007jg\u000e{W\u000e\u001d:fgN,G\rE\u0002;\u0003\u0013K1!a#<\u0005\u001d\u0011un\u001c7fC:\fq\u0002\\3bg\u0016$C-\u001a4bk2$HEM\u000b\u0003\u0003#SC!a\"\u0002\u0014.\u0012\u0011Q\u0013\t\u0005\u0003/\u000b\t+\u0004\u0002\u0002\u001a*!\u00111TAO\u0003%)hn\u00195fG.,GMC\u0002\u0002 n\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019+!'\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0005pa\u0016t7\u000b^8sKR1\u0011\u0011VAV\u0003[\u0003BAOA$\u0017\"9\u0011qO\fA\u0002\u0005E\u0002bBA>/\u0001\u0007\u0011QI\u0001\be\u0016dW-Y:f)!\t\t&a-\u00026\u0006]\u0006bBA<1\u0001\u0007\u0011\u0011\u0007\u0005\b\u0003wB\u0002\u0019AA#\u0011%\tI\f\u0007I\u0001\u0002\u0004\t9)\u0001\u0004eK2,G/Z\u0001\u0012e\u0016dW-Y:fI\u0011,g-Y;mi\u0012\u001a\u0014aD1qaJ|\u00070[7bi\u0016\u001c\u0016N_3\u0015\u000be\f\t-a1\t\r\u0005\u0005%\u00041\u0001z\u0011\u001d\t)I\u0007a\u0001\u0003\u000f\u000bAA\u001a:fKR\t\u00110A\u0005d_6l\u0017\u000e\u001e;fI\u0006YA-\u001a7fi\u0016\u001cFo\u001c:f)\u0011\t\t&a4\t\u000b)k\u0002\u0019A&\u0002\u00115\f7.\u001a*p_6$B!!\u0015\u0002V\"1\u0011q\u001b\u0010A\u0002e\fAa]5{K\u0006a\u0011\r\u001d9Ti>\u0014X\rU1uQR)1*!8\u0002`\"9\u0011qO\u0010A\u0002\u0005E\u0002bBA>?\u0001\u0007\u0011QI\u0001\u001bkB$\u0017\r^3BaBd\u0017nY1uS>t7\u000b^8sK&sgm\u001c\u000b\t\u0003#\n)/a:\u0002j\"9\u0011q\u000f\u0011A\u0002\u0005E\u0002bBA>A\u0001\u0007\u0011Q\t\u0005\u0007\u0003W\u0004\u0003\u0019A=\u0002\u000f9,woU5{K\u0006YQ\u000f\u001d3bi\u0016,6/Y4f)\u0019\t\t&!=\u0002v\"1\u00111_\u0011A\u0002e\fQ\u0001Z3mi\u0006D\u0011\"!3\"!\u0003\u0005\r!a\"\u0002+U\u0004H-\u0019;f+N\fw-\u001a\u0013eK\u001a\fW\u000f\u001c;%e\u000511/\u001b>f\u001f\u001a$2!_A\u007f\u0011\u0015Q5\u00051\u0001L\u0001"
)
public class HistoryServerDiskManager implements Logging {
   private final KVStore listing;
   private final Clock clock;
   private final File appStoreDir;
   private final String extension;
   private final File tmpStoreDir;
   private final long org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage;
   private final AtomicLong currentUsage;
   private final AtomicLong org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage;
   private final HashMap org$apache$spark$deploy$history$HistoryServerDiskManager$$active;
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

   private File appStoreDir() {
      return this.appStoreDir;
   }

   private String extension() {
      return this.extension;
   }

   private File tmpStoreDir() {
      return this.tmpStoreDir;
   }

   public long org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage() {
      return this.org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage;
   }

   private AtomicLong currentUsage() {
      return this.currentUsage;
   }

   public AtomicLong org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage() {
      return this.org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage;
   }

   public HashMap org$apache$spark$deploy$history$HistoryServerDiskManager$$active() {
      return this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active;
   }

   public void initialize() {
      this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(this.sizeOf(this.appStoreDir()), true);
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.tmpStoreDir().listFiles()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$initialize$1(x$1)));
      Tuple2 var3 = KVUtils$.MODULE$.viewToSeq(this.listing.view(ApplicationStoreInfo.class)).partition((info) -> BoxesRunTime.boxToBoolean($anonfun$initialize$2(info)));
      if (var3 != null) {
         Seq existences = (Seq)var3._1();
         Seq orphans = (Seq)var3._2();
         Tuple2 var2 = new Tuple2(existences, orphans);
         Seq existences = (Seq)var2._1();
         Seq orphans = (Seq)var2._2();
         orphans.foreach((info) -> {
            $anonfun$initialize$3(this, info);
            return BoxedUnit.UNIT;
         });
         existences.foreach((info) -> {
            $anonfun$initialize$4(this, info);
            return BoxedUnit.UNIT;
         });
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialized disk manager:"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" current usage = ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_CURRENT..MODULE$, Utils$.MODULE$.bytesToString(this.currentUsage().get()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" max usage = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_MAX..MODULE$, Utils$.MODULE$.bytesToString(this.org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage()))}))))));
      } else {
         throw new MatchError(var3);
      }
   }

   public Lease lease(final long eventLogSize, final boolean isCompressed) {
      long needed = this.approximateSize(eventLogSize, isCompressed);
      this.org$apache$spark$deploy$history$HistoryServerDiskManager$$makeRoom(needed);
      File tmp = Utils$.MODULE$.createTempDir(this.tmpStoreDir().getPath(), "appstore");
      Utils$.MODULE$.chmod700(tmp);
      this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(needed, this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage$default$2());
      long current = this.currentUsage().get();
      if (current > this.org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Lease of ", " may cause"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(needed))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" usage to exceed max (", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_CURRENT..MODULE$, Utils$.MODULE$.bytesToString(current))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" > ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_MAX..MODULE$, Utils$.MODULE$.bytesToString(this.org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage()))}))))));
      }

      return new Lease(tmp, needed);
   }

   public boolean lease$default$2() {
      return false;
   }

   public Option openStore(final String appId, final Option attemptId) {
      LongRef newSize = LongRef.create(0L);
      synchronized(this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active()){}

      Object var6;
      try {
         File path = this.appStorePath(appId, attemptId);
         Object var10000;
         if (path.isDirectory()) {
            newSize.elem = this.sizeOf(path);
            this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active().update(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId), BoxesRunTime.boxToLong(newSize.elem));
            var10000 = new Some(path);
         } else {
            var10000 = scala.None..MODULE$;
         }

         var6 = var10000;
      } catch (Throwable var9) {
         throw var9;
      }

      ((Option)var6).foreach((pathx) -> {
         $anonfun$openStore$1(this, appId, attemptId, newSize, pathx);
         return BoxedUnit.UNIT;
      });
      return (Option)var6;
   }

   public void release(final String appId, final Option attemptId, final boolean delete) {
      synchronized(this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active()){}

      Option var6;
      try {
         var6 = this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active().remove(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId));
      } catch (Throwable var8) {
         throw var8;
      }

      var6.foreach((JFunction1.mcVJ.sp)(oldSize) -> {
         File path = this.appStorePath(appId, attemptId);
         this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(-oldSize, true);
         if (path.isDirectory()) {
            if (delete) {
               this.org$apache$spark$deploy$history$HistoryServerDiskManager$$deleteStore(path);
            } else {
               long newSize = this.sizeOf(path);
               ApplicationStoreInfo qual$1 = (ApplicationStoreInfo)this.listing.read(ApplicationStoreInfo.class, path.getAbsolutePath());
               String x$2 = qual$1.copy$default$1();
               long x$3 = qual$1.copy$default$2();
               String x$4 = qual$1.copy$default$3();
               Option x$5 = qual$1.copy$default$4();
               ApplicationStoreInfo newInfo = qual$1.copy(x$2, x$3, x$4, x$5, newSize);
               this.listing.write(newInfo);
               this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(newSize, true);
            }
         }
      });
   }

   public boolean release$default$3() {
      return false;
   }

   public long approximateSize(final long eventLogSize, final boolean isCompressed) {
      return isCompressed ? eventLogSize * 2L : eventLogSize / 2L;
   }

   public long free() {
      return scala.math.package..MODULE$.max(this.org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage() - this.currentUsage().get(), 0L);
   }

   public long committed() {
      return this.org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage().get();
   }

   public void org$apache$spark$deploy$history$HistoryServerDiskManager$$deleteStore(final File path) {
      FileUtils.deleteDirectory(path);
      this.listing.delete(ApplicationStoreInfo.class, path.getAbsolutePath());
   }

   public void org$apache$spark$deploy$history$HistoryServerDiskManager$$makeRoom(final long size) {
      if (this.free() < size) {
         this.logDebug((Function0)(() -> "Not enough free space, looking at candidates for deletion..."));
         ListBuffer evicted = new ListBuffer();
         Utils$.MODULE$.tryWithResource(() -> this.listing.view(ApplicationStoreInfo.class).index("lastAccess").closeableIterator(), (iter) -> {
            $anonfun$makeRoom$3(this, size, evicted, iter);
            return BoxedUnit.UNIT;
         });
         if (evicted.nonEmpty()) {
            long freed = BoxesRunTime.unboxToLong(((IterableOnceOps)evicted.map((info) -> BoxesRunTime.boxToLong($anonfun$makeRoom$4(this, info)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleted ", " store(s)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_EVICTED..MODULE$, BoxesRunTime.boxToInteger(evicted.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to free ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_TO_FREE..MODULE$, Utils$.MODULE$.bytesToString(freed))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (target = ", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(size))}))))));
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to free any space to make room for "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(size))}))))));
         }
      }
   }

   public File appStorePath(final String appId, final Option attemptId) {
      String fileName = appId + attemptId.map((x$2) -> "_" + x$2).getOrElse(() -> "") + this.extension();
      return new File(this.appStoreDir(), fileName);
   }

   public void org$apache$spark$deploy$history$HistoryServerDiskManager$$updateApplicationStoreInfo(final String appId, final Option attemptId, final long newSize) {
      File path = this.appStorePath(appId, attemptId);
      ApplicationStoreInfo info = new ApplicationStoreInfo(path.getAbsolutePath(), this.clock.getTimeMillis(), appId, attemptId, newSize);
      this.listing.write(info);
   }

   public void org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(final long delta, final boolean committed) {
      long updated = this.currentUsage().addAndGet(delta);
      if (updated < 0L) {
         throw new IllegalStateException("Disk usage tracker went negative (now = " + updated + ", delta = " + delta + ")");
      } else if (committed) {
         long updatedCommitted = this.org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage().addAndGet(delta);
         if (updatedCommitted < 0L) {
            throw new IllegalStateException("Disk usage tracker went negative (now = " + updatedCommitted + ", delta = " + delta + ")");
         }
      }
   }

   public boolean org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage$default$2() {
      return false;
   }

   public long sizeOf(final File path) {
      return FileUtils.sizeOf(path);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$1(final File x$1) {
      return FileUtils.deleteQuietly(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$2(final ApplicationStoreInfo info) {
      return (new File(info.path())).exists();
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$3(final HistoryServerDiskManager $this, final ApplicationStoreInfo info) {
      $this.listing.delete(info.getClass(), info.path());
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$4(final HistoryServerDiskManager $this, final ApplicationStoreInfo info) {
      long fileSize = $this.sizeOf(new File(info.path()));
      if (fileSize != info.size()) {
         KVStore var10000 = $this.listing;
         String x$2 = info.copy$default$1();
         long x$3 = info.copy$default$2();
         String x$4 = info.copy$default$3();
         Option x$5 = info.copy$default$4();
         var10000.write(info.copy(x$2, x$3, x$4, x$5, fileSize));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$openStore$1(final HistoryServerDiskManager $this, final String appId$1, final Option attemptId$1, final LongRef newSize$1, final File path) {
      $this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateApplicationStoreInfo(appId$1, attemptId$1, newSize$1.elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$makeRoom$3(final HistoryServerDiskManager $this, final long size$1, final ListBuffer evicted$1, final KVStoreIterator iter) {
      long needed = size$1;

      while(needed > 0L && iter.hasNext()) {
         ApplicationStoreInfo info = (ApplicationStoreInfo)iter.next();
         synchronized($this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active()){}

         boolean var10;
         try {
            var10 = $this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active().contains(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(info.appId()), info.attemptId()));
         } catch (Throwable var12) {
            throw var12;
         }

         if (!var10) {
            evicted$1.$plus$eq(info);
            needed -= info.size();
         }
      }

   }

   // $FF: synthetic method
   public static final long $anonfun$makeRoom$4(final HistoryServerDiskManager $this, final ApplicationStoreInfo info) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting store for"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "/", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, info.appId()), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, info.attemptId())}))))));
      $this.org$apache$spark$deploy$history$HistoryServerDiskManager$$deleteStore(new File(info.path()));
      $this.org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(-info.size(), true);
      return info.size();
   }

   public HistoryServerDiskManager(final SparkConf conf, final File path, final KVStore listing, final Clock clock) {
      this.listing = listing;
      this.clock = clock;
      Logging.$init$(this);
      this.appStoreDir = new File(path, "apps");
      if (!this.appStoreDir().isDirectory() && !this.appStoreDir().mkdir()) {
         throw new IllegalArgumentException("Failed to create app directory (" + this.appStoreDir() + ").");
      } else {
         String var6;
         label28: {
            label27: {
               Object var10001 = conf.get(History$.MODULE$.HYBRID_STORE_DISK_BACKEND());
               String var5 = History.HybridStoreDiskBackend$.MODULE$.ROCKSDB().toString();
               if (var10001 == null) {
                  if (var5 == null) {
                     break label27;
                  }
               } else if (var10001.equals(var5)) {
                  break label27;
               }

               var6 = ".ldb";
               break label28;
            }

            var6 = ".rdb";
         }

         this.extension = var6;
         this.tmpStoreDir = new File(path, "temp");
         if (!this.tmpStoreDir().isDirectory() && !this.tmpStoreDir().mkdir()) {
            throw new IllegalArgumentException("Failed to create temp directory (" + this.tmpStoreDir() + ").");
         } else {
            this.org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage = BoxesRunTime.unboxToLong(conf.get(History$.MODULE$.MAX_LOCAL_DISK_USAGE()));
            this.currentUsage = new AtomicLong(0L);
            this.org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage = new AtomicLong(0L);
            this.org$apache$spark$deploy$history$HistoryServerDiskManager$$active = new HashMap();
         }
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Lease {
      private final File tmpPath;
      private final long leased;
      // $FF: synthetic field
      public final HistoryServerDiskManager $outer;

      public File tmpPath() {
         return this.tmpPath;
      }

      private long leased() {
         return this.leased;
      }

      public File commit(final String appId, final Option attemptId) {
         File dst = this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().appStorePath(appId, attemptId);
         synchronized(this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$active()){}

         try {
            scala.Predef..MODULE$.require(!this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$active().contains(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId)), () -> "Cannot commit lease for active application " + appId + " / " + attemptId);
            if (dst.isDirectory()) {
               long size = this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().sizeOf(dst);
               this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$deleteStore(dst);
               this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(-size, true);
            }
         } catch (Throwable var17) {
            throw var17;
         }

         this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(-this.leased(), this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage$default$2());
         long newSize = this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().sizeOf(this.tmpPath());
         this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$makeRoom(newSize);
         this.tmpPath().renameTo(dst);
         this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(newSize, true);
         if (this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage().get() > this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage()) {
            String current = Utils$.MODULE$.bytesToString(this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$committedUsage().get());
            String max = Utils$.MODULE$.bytesToString(this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$maxUsage());
            this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Commit of application ", " / "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " causes maximum disk usage to be "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attemptId)})))).$plus(this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exceeded (", " > ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, current), new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_MAX..MODULE$, max)}))))));
         }

         this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateApplicationStoreInfo(appId, attemptId, newSize);
         synchronized(this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$active()){}

         try {
            this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$active().update(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId), BoxesRunTime.boxToLong(newSize));
         } catch (Throwable var16) {
            throw var16;
         }

         return dst;
      }

      public void rollback() {
         this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage(-this.leased(), this.org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer().org$apache$spark$deploy$history$HistoryServerDiskManager$$updateUsage$default$2());
         FileUtils.deleteDirectory(this.tmpPath());
      }

      // $FF: synthetic method
      public HistoryServerDiskManager org$apache$spark$deploy$history$HistoryServerDiskManager$Lease$$$outer() {
         return this.$outer;
      }

      public Lease(final File tmpPath, final long leased) {
         this.tmpPath = tmpPath;
         this.leased = leased;
         if (HistoryServerDiskManager.this == null) {
            throw null;
         } else {
            this.$outer = HistoryServerDiskManager.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
