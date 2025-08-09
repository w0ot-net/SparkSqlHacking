package org.apache.spark.streaming.receiver;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala..less.colon.less.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.ExecutionContextExecutorService;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tEeAB\u00193\u0003\u0003!D\b\u0003\u00054\u0001\t\u0005\t\u0015!\u0003K\u0011!Q\u0006A!A!\u0002\u0013Y\u0006\"B0\u0001\t\u0003\u0001w!\u00025\u0001\u0011\u0003Ig!B6\u0001\u0011\u0003a\u0007\"B0\u0006\t\u0003\u0001X\u0001B9\u0006\u0001IDqA^\u0003C\u0002\u0013\u0005q\u000f\u0003\u0004y\u000b\u0001\u0006IA\u001d\u0005\bs\u0016\u0011\r\u0011\"\u0001x\u0011\u0019QX\u0001)A\u0005e\"910\u0002b\u0001\n\u00039\bB\u0002?\u0006A\u0003%!\u000fC\u0004~\u0001\t\u0007I\u0011\u0002@\t\u000f\u0005-\u0001\u0001)A\u0005\u007f\"I\u0011Q\u0002\u0001C\u0002\u0013E\u0011q\u0002\u0005\t\u0003/\u0001\u0001\u0015!\u0003\u0002\u0012!I\u0011\u0011\u0004\u0001C\u0002\u0013%\u00111\u0004\u0005\t\u0003_\u0001\u0001\u0015!\u0003\u0002\u001e!I\u0011\u0011\u0007\u0001C\u0002\u0013%\u0011q\u0002\u0005\t\u0003g\u0001\u0001\u0015!\u0003\u0002\u0012!A\u0011Q\u0007\u0001\u0005\u0002Q\n9\u0004C\u0005\u0002@\u0001\u0001\r\u0011\"\u0005\u0002B!I\u00111\f\u0001A\u0002\u0013E\u0011Q\f\u0005\t\u0003S\u0002\u0001\u0015)\u0003\u0002D!Q\u00111\u000f\u0001A\u0002\u0013\u0005A'!\u001e\t\u0015\u0005m\u0004\u00011A\u0005\u0002Q\ni\b\u0003\u0005\u0002\u0002\u0002\u0001\u000b\u0015BA<\u0011\u001d\t)\t\u0001D\u0001\u0003\u000fCq!!$\u0001\r\u0003\ty\tC\u0004\u0002>\u00021\t!a0\t\u000f\u0005]\u0007A\"\u0001\u0002Z\"9\u00111 \u0001\u0007\u0002\u0005u\bb\u0002B\b\u0001\u0019\u0005!\u0011\u0003\u0005\b\u0005W\u0001A\u0011\u0003B\u0017\u0011\u001d\u0011y\u0003\u0001C\t\u0005cAqAa\u000f\u0001\r#\u0011i\u0004C\u0004\u0003F\u0001!\tBa\u0012\t\u000f\t5\u0003\u0001\"\u0001\u0003.!9!q\n\u0001\u0005\u0002\tE\u0003b\u0002B,\u0001\u0011\u0005!Q\u0006\u0005\b\u00053\u0002A\u0011\u0001B.\u0011\u001d\u0011\t\u0007\u0001C\u0001\u0005GB\u0011B!\u001b\u0001#\u0003%\tAa\u001b\t\u000f\t\u0005\u0004\u0001\"\u0001\u0003\u0002\"9!1\u0012\u0001\u0005\u0002\tu\u0002b\u0002BG\u0001\u0011\u0005!Q\b\u0005\b\u0005\u001f\u0003A\u0011\u0001B\u0017\u0005I\u0011VmY3jm\u0016\u00148+\u001e9feZL7o\u001c:\u000b\u0005M\"\u0014\u0001\u0003:fG\u0016Lg/\u001a:\u000b\u0005U2\u0014!C:ue\u0016\fW.\u001b8h\u0015\t9\u0004(A\u0003ta\u0006\u00148N\u0003\u0002:u\u00051\u0011\r]1dQ\u0016T\u0011aO\u0001\u0004_J<7c\u0001\u0001>\u0007B\u0011a(Q\u0007\u0002\u007f)\t\u0001)A\u0003tG\u0006d\u0017-\u0003\u0002C\u007f\t1\u0011I\\=SK\u001a\u0004\"\u0001R$\u000e\u0003\u0015S!A\u0012\u001c\u0002\u0011%tG/\u001a:oC2L!\u0001S#\u0003\u000f1{wmZ5oO\u000e\u0001\u0001GA&R!\raUjT\u0007\u0002e%\u0011aJ\r\u0002\t%\u0016\u001cW-\u001b<feB\u0011\u0001+\u0015\u0007\u0001\t%\u0011\u0016!!A\u0001\u0002\u000b\u00051KA\u0002`IE\n\"\u0001V,\u0011\u0005y*\u0016B\u0001,@\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0010-\n\u0005e{$aA!os\u0006!1m\u001c8g!\taV,D\u00017\u0013\tqfGA\u0005Ta\u0006\u00148nQ8oM\u00061A(\u001b8jiz\"2!\u00192h!\ta\u0005\u0001C\u00034\u0007\u0001\u00071\r\r\u0002eMB\u0019A*T3\u0011\u0005A3G!\u0003*c\u0003\u0003\u0005\tQ!\u0001T\u0011\u0015Q6\u00011\u0001\\\u00035\u0011VmY3jm\u0016\u00148\u000b^1uKB\u0011!.B\u0007\u0002\u0001\ti!+Z2fSZ,'o\u0015;bi\u0016\u001c\"!B7\u0011\u0005yr\u0017BA8@\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0015\u0003%\u0014qb\u00115fG.\u0004x.\u001b8u'R\fG/\u001a\t\u0003gRl\u0011!B\u0005\u0003k:\u0014QAV1mk\u0016\f1\"\u00138ji&\fG.\u001b>fIV\t!/\u0001\u0007J]&$\u0018.\u00197ju\u0016$\u0007%A\u0004Ti\u0006\u0014H/\u001a3\u0002\u0011M#\u0018M\u001d;fI\u0002\nqa\u0015;paB,G-\u0001\u0005Ti>\u0004\b/\u001a3!\u0003Y1W\u000f^;sK\u0016CXmY;uS>t7i\u001c8uKb$X#A@\u0011\t\u0005\u0005\u0011qA\u0007\u0003\u0003\u0007Q1!!\u0002@\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\u0003\u0013\t\u0019AA\u0010Fq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yi\u0016CXmY;u_J\u001cVM\u001d<jG\u0016\fqCZ;ukJ,W\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e\u0011\u0002\u0011M$(/Z1n\u0013\u0012,\"!!\u0005\u0011\u0007y\n\u0019\"C\u0002\u0002\u0016}\u00121!\u00138u\u0003%\u0019HO]3b[&#\u0007%A\u0005ti>\u0004H*\u0019;dQV\u0011\u0011Q\u0004\t\u0005\u0003?\tY#\u0004\u0002\u0002\")!\u0011QAA\u0012\u0015\u0011\t)#a\n\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003S\tAA[1wC&!\u0011QFA\u0011\u00059\u0019u.\u001e8u\t><h\u000eT1uG\"\f!b\u001d;pa2\u000bGo\u00195!\u0003M!WMZ1vYR\u0014Vm\u001d;beR$U\r\\1z\u0003Q!WMZ1vYR\u0014Vm\u001d;beR$U\r\\1zA\u0005\u0019r-\u001a;DkJ\u0014XM\u001c;SCR,G*[7jiV\u0011\u0011\u0011\b\t\u0004}\u0005m\u0012bAA\u001f\u007f\t!Aj\u001c8h\u00035\u0019Ho\u001c9qS:<WI\u001d:peV\u0011\u00111\t\t\u0005\u0003\u000b\n)F\u0004\u0003\u0002H\u0005Ec\u0002BA%\u0003\u001fj!!a\u0013\u000b\u0007\u00055\u0013*\u0001\u0004=e>|GOP\u0005\u0002\u0001&\u0019\u00111K \u0002\u000fA\f7m[1hK&!\u0011qKA-\u0005%!\u0006N]8xC\ndWMC\u0002\u0002T}\n\u0011c\u001d;paBLgnZ#se>\u0014x\fJ3r)\u0011\ty&!\u001a\u0011\u0007y\n\t'C\u0002\u0002d}\u0012A!\u00168ji\"I\u0011q\r\r\u0002\u0002\u0003\u0007\u00111I\u0001\u0004q\u0012\n\u0014AD:u_B\u0004\u0018N\\4FeJ|'\u000f\t\u0015\u00043\u00055\u0004c\u0001 \u0002p%\u0019\u0011\u0011O \u0003\u0011Y|G.\u0019;jY\u0016\fQB]3dK&4XM]*uCR,WCAA<!\r\tI\b\u001e\b\u0003U\u0012\t\u0011C]3dK&4XM]*uCR,w\fJ3r)\u0011\ty&a \t\u0013\u0005\u001d4$!AA\u0002\u0005]\u0014A\u0004:fG\u0016Lg/\u001a:Ti\u0006$X\r\t\u0015\u00049\u00055\u0014A\u00039vg\"\u001c\u0016N\\4mKR!\u0011qLAE\u0011\u0019\tY)\ba\u0001/\u0006!A-\u0019;b\u0003%\u0001Xo\u001d5CsR,7\u000f\u0006\u0005\u0002`\u0005E\u0015\u0011UAV\u0011\u001d\t\u0019J\ba\u0001\u0003+\u000bQAY=uKN\u0004B!a&\u0002\u001e6\u0011\u0011\u0011\u0014\u0006\u0005\u00037\u000b9#A\u0002oS>LA!a(\u0002\u001a\nQ!)\u001f;f\u0005V4g-\u001a:\t\u000f\u0005\rf\u00041\u0001\u0002&\u0006\u0001r\u000e\u001d;j_:\fG.T3uC\u0012\fG/\u0019\t\u0005}\u0005\u001dv+C\u0002\u0002*~\u0012aa\u00149uS>t\u0007bBAW=\u0001\u0007\u0011qV\u0001\u0010_B$\u0018n\u001c8bY\ncwnY6JIB)a(a*\u00022B!\u00111WA]\u001b\t\t)LC\u0002\u00028Z\nqa\u001d;pe\u0006<W-\u0003\u0003\u0002<\u0006U&!D*ue\u0016\fWN\u00117pG.LE-\u0001\u0007qkND\u0017\n^3sCR|'\u000f\u0006\u0005\u0002`\u0005\u0005\u00171[Ak\u0011\u001d\t\u0019m\ba\u0001\u0003\u000b\f\u0001\"\u001b;fe\u0006$xN\u001d\u0019\u0005\u0003\u000f\fy\r\u0005\u0004\u0002F\u0005%\u0017QZ\u0005\u0005\u0003\u0017\fIF\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\r\u0001\u0016q\u001a\u0003\f\u0003#\f\t-!A\u0001\u0002\u000b\u00051KA\u0002`IIBq!a) \u0001\u0004\t)\u000bC\u0004\u0002.~\u0001\r!a,\u0002\u001fA,8\u000f[!se\u0006L()\u001e4gKJ$\u0002\"a\u0018\u0002\\\u0006]\u0018\u0011 \u0005\b\u0003;\u0004\u0003\u0019AAp\u0003-\t'O]1z\u0005V4g-\u001a:1\t\u0005\u0005\u00181\u001f\t\u0007\u0003G\fi/!=\u000e\u0005\u0005\u0015(\u0002BAt\u0003S\fq!\\;uC\ndWMC\u0002\u0002l~\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty/!:\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM\u001d\t\u0004!\u0006MHaCA{\u00037\f\t\u0011!A\u0003\u0002M\u00131a\u0018\u00134\u0011\u001d\t\u0019\u000b\ta\u0001\u0003KCq!!,!\u0001\u0004\ty+\u0001\u000bde\u0016\fG/\u001a\"m_\u000e\\w)\u001a8fe\u0006$xN\u001d\u000b\u0005\u0003\u007f\u0014)\u0001E\u0002M\u0005\u0003I1Aa\u00013\u00059\u0011En\\2l\u000f\u0016tWM]1u_JDqAa\u0002\"\u0001\u0004\u0011I!\u0001\fcY>\u001c7nR3oKJ\fGo\u001c:MSN$XM\\3s!\ra%1B\u0005\u0004\u0005\u001b\u0011$A\u0006\"m_\u000e\\w)\u001a8fe\u0006$xN\u001d'jgR,g.\u001a:\u0002\u0017I,\u0007o\u001c:u\u000bJ\u0014xN\u001d\u000b\u0007\u0003?\u0012\u0019Ba\n\t\u000f\tU!\u00051\u0001\u0003\u0018\u00059Q.Z:tC\u001e,\u0007\u0003\u0002B\r\u0005CqAAa\u0007\u0003\u001eA\u0019\u0011\u0011J \n\u0007\t}q(\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005G\u0011)C\u0001\u0004TiJLgn\u001a\u0006\u0004\u0005?y\u0004b\u0002B\u0015E\u0001\u0007\u00111I\u0001\ni\"\u0014xn^1cY\u0016\fqa\u001c8Ti\u0006\u0014H\u000f\u0006\u0002\u0002`\u00051qN\\*u_B$b!a\u0018\u00034\tU\u0002b\u0002B\u000bI\u0001\u0007!q\u0003\u0005\b\u0005o!\u0003\u0019\u0001B\u001d\u0003\u0015)'O]8s!\u0015q\u0014qUA\"\u0003=ygNU3dK&4XM]*uCJ$HC\u0001B !\rq$\u0011I\u0005\u0004\u0005\u0007z$a\u0002\"p_2,\u0017M\\\u0001\u000f_:\u0014VmY3jm\u0016\u00148\u000b^8q)\u0019\tyF!\u0013\u0003L!9!Q\u0003\u0014A\u0002\t]\u0001b\u0002B\u001cM\u0001\u0007!\u0011H\u0001\u0006gR\f'\u000f^\u0001\u0005gR|\u0007\u000f\u0006\u0004\u0002`\tM#Q\u000b\u0005\b\u0005+A\u0003\u0019\u0001B\f\u0011\u001d\u00119\u0004\u000ba\u0001\u0005s\tQb\u001d;beR\u0014VmY3jm\u0016\u0014\u0018\u0001D:u_B\u0014VmY3jm\u0016\u0014HCBA0\u0005;\u0012y\u0006C\u0004\u0003\u0016)\u0002\rAa\u0006\t\u000f\t]\"\u00061\u0001\u0003:\u0005y!/Z:uCJ$(+Z2fSZ,'\u000f\u0006\u0004\u0002`\t\u0015$q\r\u0005\b\u0005+Y\u0003\u0019\u0001B\f\u0011%\u00119d\u000bI\u0001\u0002\u0004\u0011I$A\rsKN$\u0018M\u001d;SK\u000e,\u0017N^3sI\u0011,g-Y;mi\u0012\u0012TC\u0001B7U\u0011\u0011IDa\u001c,\u0005\tE\u0004\u0003\u0002B:\u0005{j!A!\u001e\u000b\t\t]$\u0011P\u0001\nk:\u001c\u0007.Z2lK\u0012T1Aa\u001f@\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005\u007f\u0012)HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$\u0002\"a\u0018\u0003\u0004\n\u0015%q\u0011\u0005\b\u0005+i\u0003\u0019\u0001B\f\u0011\u001d\u00119$\fa\u0001\u0005sAqA!#.\u0001\u0004\t\t\"A\u0003eK2\f\u00170A\tjgJ+7-Z5wKJ\u001cF/\u0019:uK\u0012\f\u0011#[:SK\u000e,\u0017N^3s'R|\u0007\u000f]3e\u0003A\tw/Y5u)\u0016\u0014X.\u001b8bi&|g\u000e"
)
public abstract class ReceiverSupervisor implements Logging {
   private volatile ReceiverState$ ReceiverState$module;
   private final Receiver receiver;
   private final ExecutionContextExecutorService futureExecutionContext;
   private final int streamId;
   private final CountDownLatch stopLatch;
   private final int defaultRestartDelay;
   private volatile Throwable stoppingError;
   private volatile Enumeration.Value receiverState;
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

   public ReceiverState$ ReceiverState() {
      if (this.ReceiverState$module == null) {
         this.ReceiverState$lzycompute$1();
      }

      return this.ReceiverState$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ExecutionContextExecutorService futureExecutionContext() {
      return this.futureExecutionContext;
   }

   public int streamId() {
      return this.streamId;
   }

   private CountDownLatch stopLatch() {
      return this.stopLatch;
   }

   private int defaultRestartDelay() {
      return this.defaultRestartDelay;
   }

   public long getCurrentRateLimit() {
      return Long.MAX_VALUE;
   }

   public Throwable stoppingError() {
      return this.stoppingError;
   }

   public void stoppingError_$eq(final Throwable x$1) {
      this.stoppingError = x$1;
   }

   public Enumeration.Value receiverState() {
      return this.receiverState;
   }

   public void receiverState_$eq(final Enumeration.Value x$1) {
      this.receiverState = x$1;
   }

   public abstract void pushSingle(final Object data);

   public abstract void pushBytes(final ByteBuffer bytes, final Option optionalMetadata, final Option optionalBlockId);

   public abstract void pushIterator(final Iterator iterator, final Option optionalMetadata, final Option optionalBlockId);

   public abstract void pushArrayBuffer(final ArrayBuffer arrayBuffer, final Option optionalMetadata, final Option optionalBlockId);

   public abstract BlockGenerator createBlockGenerator(final BlockGeneratorListener blockGeneratorListener);

   public abstract void reportError(final String message, final Throwable throwable);

   public void onStart() {
   }

   public void onStop(final String message, final Option error) {
   }

   public abstract boolean onReceiverStart();

   public void onReceiverStop(final String message, final Option error) {
   }

   public void start() {
      this.onStart();
      this.startReceiver();
   }

   public void stop(final String message, final Option error) {
      this.stoppingError_$eq((Throwable)error.orNull(.MODULE$.refl()));
      this.stopReceiver(message, error);
      this.onStop(message, error);
      this.futureExecutionContext().shutdownNow();
      this.stopLatch().countDown();
   }

   public synchronized void startReceiver() {
      try {
         if (this.onReceiverStart()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting receiver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(this.streamId()))})))));
            this.receiverState_$eq(this.ReceiverState().Started());
            this.receiver.onStart();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Called receiver ", " onStart"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(this.streamId()))})))));
         } else {
            this.stop("Registered unsuccessfully because Driver refused to start receiver " + this.streamId(), scala.None..MODULE$);
         }
      } catch (Throwable var5) {
         if (var5 == null || !scala.util.control.NonFatal..MODULE$.apply(var5)) {
            throw var5;
         }

         this.stop("Error starting receiver " + this.streamId(), new Some(var5));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public synchronized void stopReceiver(final String message, final Option error) {
      try {
         label74: {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stopping receiver with message: ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, error.getOrElse(() -> ""))}))))));
            Enumeration.Value var5 = this.receiverState();
            Enumeration.Value var13 = this.ReceiverState().Initialized();
            if (var13 == null) {
               if (var5 == null) {
                  break label74;
               }
            } else if (var13.equals(var5)) {
               break label74;
            }

            label75: {
               var13 = this.ReceiverState().Started();
               if (var13 == null) {
                  if (var5 == null) {
                     break label75;
                  }
               } else if (var13.equals(var5)) {
                  break label75;
               }

               label45: {
                  var13 = this.ReceiverState().Stopped();
                  if (var13 == null) {
                     if (var5 == null) {
                        break label45;
                     }
                  } else if (var13.equals(var5)) {
                     break label45;
                  }

                  throw new MatchError(var5);
               }

               this.logWarning((Function0)(() -> "Receiver has been stopped"));
               BoxedUnit var16 = BoxedUnit.UNIT;
               return;
            }

            this.receiverState_$eq(this.ReceiverState().Stopped());
            this.receiver.onStop();
            this.logInfo((Function0)(() -> "Called receiver onStop"));
            this.onReceiverStop(message, error);
            BoxedUnit var17 = BoxedUnit.UNIT;
            return;
         }

         this.logWarning((Function0)(() -> "Skip stopping receiver because it has not yet stared"));
         BoxedUnit var18 = BoxedUnit.UNIT;
      } catch (Throwable var12) {
         if (var12 == null || !scala.util.control.NonFatal..MODULE$.apply(var12)) {
            throw var12;
         }

         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error stopping receiver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(this.streamId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, org.apache.spark.util.Utils..MODULE$.exceptionString(var12))}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public void restartReceiver(final String message, final Option error) {
      this.restartReceiver(message, error, this.defaultRestartDelay());
   }

   public void restartReceiver(final String message, final Option error, final int delay) {
      scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Restarting receiver with delay ", " ms: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DELAY..MODULE$, BoxesRunTime.boxToInteger(delay))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))))), (Throwable)error.orNull(.MODULE$.refl()));
         this.stopReceiver("Restarting receiver with delay " + delay + "ms: " + message, error);
         this.logDebug((Function0)(() -> "Sleeping for " + delay));
         Thread.sleep((long)delay);
         this.logInfo((Function0)(() -> "Starting receiver again"));
         this.startReceiver();
         this.logInfo((Function0)(() -> "Receiver started again"));
      }, this.futureExecutionContext());
   }

   public Option restartReceiver$default$2() {
      return scala.None..MODULE$;
   }

   public boolean isReceiverStarted() {
      boolean var2;
      label23: {
         this.logDebug((Function0)(() -> "state = " + this.receiverState()));
         Enumeration.Value var10000 = this.receiverState();
         Enumeration.Value var1 = this.ReceiverState().Started();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public boolean isReceiverStopped() {
      boolean var2;
      label23: {
         this.logDebug((Function0)(() -> "state = " + this.receiverState()));
         Enumeration.Value var10000 = this.receiverState();
         Enumeration.Value var1 = this.ReceiverState().Stopped();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public void awaitTermination() {
      this.logInfo((Function0)(() -> "Waiting for receiver to be stopped"));
      this.stopLatch().await();
      if (this.stoppingError() != null) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stopped receiver with error: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, this.stoppingError())})))));
         throw this.stoppingError();
      } else {
         this.logInfo((Function0)(() -> "Stopped receiver without error"));
      }
   }

   private final void ReceiverState$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ReceiverState$module == null) {
            this.ReceiverState$module = new ReceiverState$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public ReceiverSupervisor(final Receiver receiver, final SparkConf conf) {
      this.receiver = receiver;
      Logging.$init$(this);
      receiver.attachSupervisor(this);
      this.futureExecutionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(org.apache.spark.util.ThreadUtils..MODULE$.newDaemonCachedThreadPool("receiver-supervisor-future", 128, org.apache.spark.util.ThreadUtils..MODULE$.newDaemonCachedThreadPool$default$3()));
      this.streamId = receiver.streamId();
      this.stopLatch = new CountDownLatch(1);
      this.defaultRestartDelay = conf.getInt("spark.streaming.receiverRestartDelay", 2000);
      this.stoppingError = null;
      this.receiverState = this.ReceiverState().Initialized();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ReceiverState$ extends Enumeration {
      private final Enumeration.Value Initialized = this.Value();
      private final Enumeration.Value Started = this.Value();
      private final Enumeration.Value Stopped = this.Value();

      public Enumeration.Value Initialized() {
         return this.Initialized;
      }

      public Enumeration.Value Started() {
         return this.Started;
      }

      public Enumeration.Value Stopped() {
         return this.Stopped;
      }
   }
}
