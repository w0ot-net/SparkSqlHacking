package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.spark.ExecutorAllocationClient;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.streaming.StreamingConf$;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.python.PythonDStream$;
import org.apache.spark.streaming.dstream.InputDStream;
import org.apache.spark.util.Clock;
import org.apache.spark.util.EventLoop;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\tUb!B\u00193\u0001Qb\u0004\u0002C%\u0001\u0005\u000b\u0007I\u0011A&\t\u0011A\u0003!\u0011!Q\u0001\n1CQ!\u0015\u0001\u0005\u0002ICqA\u0016\u0001C\u0002\u0013%q\u000b\u0003\u0004g\u0001\u0001\u0006I\u0001\u0017\u0005\bO\u0002\u0011\r\u0011\"\u0003i\u0011\u0019a\u0007\u0001)A\u0005S\"9Q\u000e\u0001b\u0001\n\u0013q\u0007BB;\u0001A\u0003%q\u000e\u0003\u0005w\u0001\t\u0007I\u0011\u0001\u001bx\u0011\u0019Y\b\u0001)A\u0005q\"9A\u0010\u0001b\u0001\n\u0003i\bbBA\u0004\u0001\u0001\u0006IA \u0005\n\u0003\u0013\u0001!\u0019!C\u0001\u0003\u0017A\u0001\"a\u0005\u0001A\u0003%\u0011Q\u0002\u0005\n\u0003+\u0001\u0001\u0019!C\u0001\u0003/A\u0011\"a\b\u0001\u0001\u0004%\t!!\t\t\u0011\u00055\u0002\u0001)Q\u0005\u00033A\u0011\"a\f\u0001\u0001\u0004%\t!!\r\t\u0013\u0005e\u0002\u00011A\u0005\u0002\u0005m\u0002\u0002CA \u0001\u0001\u0006K!a\r\t\u0013\u0005\u0005\u0003\u00011A\u0005\n\u0005\r\u0003\"CA)\u0001\u0001\u0007I\u0011BA*\u0011!\t9\u0006\u0001Q!\n\u0005\u0015\u0003\"CA-\u0001\u0001\u0007I\u0011BA.\u0011%\tI\u0007\u0001a\u0001\n\u0013\tY\u0007\u0003\u0005\u0002p\u0001\u0001\u000b\u0015BA/\u0011\u001d\t\t\b\u0001C\u0001\u0003gBq!!\u001e\u0001\t\u0003\t9\bC\u0004\u0002\u0004\u0002!\t!!\"\t\u000f\u0005-\u0005\u0001\"\u0001\u0002\u000e\"9\u0011q\u0015\u0001\u0005\u0002\u0005%\u0006bBAe\u0001\u0011\u0005\u00111\u001a\u0005\b\u0003\u001b\u0004A\u0011BAh\u0011\u001d\t)\u000e\u0001C\u0005\u0003/Dq!!<\u0001\t\u0013\ty\u000fC\u0004\u0002x\u0002!I!!?\u0007\r\u0005}\b\u0001\u0002B\u0001\u0011)\tYN\nB\u0001B\u0003%\u0011Q\u001c\u0005\u0007#\u001a\"\tA!\u0006\t\u000f\tua\u0005\"\u0001\u0002t\u001dA!q\u0004\u001a\t\u0002Q\u0012\tCB\u00042e!\u0005AGa\t\t\rE[C\u0011\u0001B\u0013\u0011%\u00119c\u000bb\u0001\n\u0003\u0011I\u0003\u0003\u0005\u00030-\u0002\u000b\u0011\u0002B\u0016\u0011%\u0011\td\u000bb\u0001\n\u0003\u0011I\u0003\u0003\u0005\u00034-\u0002\u000b\u0011\u0002B\u0016\u00051QuNY*dQ\u0016$W\u000f\\3s\u0015\t\u0019D'A\u0005tG\",G-\u001e7fe*\u0011QGN\u0001\ngR\u0014X-Y7j]\u001eT!a\u000e\u001d\u0002\u000bM\u0004\u0018M]6\u000b\u0005eR\u0014AB1qC\u000eDWMC\u0001<\u0003\ry'oZ\n\u0004\u0001u\u001a\u0005C\u0001 B\u001b\u0005y$\"\u0001!\u0002\u000bM\u001c\u0017\r\\1\n\u0005\t{$AB!osJ+g\r\u0005\u0002E\u000f6\tQI\u0003\u0002Gm\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002I\u000b\n9Aj\\4hS:<\u0017aA:tG\u000e\u0001Q#\u0001'\u0011\u00055sU\"\u0001\u001b\n\u0005=#$\u0001E*ue\u0016\fW.\u001b8h\u0007>tG/\u001a=u\u0003\u0011\u00198o\u0019\u0011\u0002\rqJg.\u001b;?)\t\u0019V\u000b\u0005\u0002U\u00015\t!\u0007C\u0003J\u0007\u0001\u0007A*A\u0004k_\n\u001cV\r^:\u0016\u0003a\u0003B!\u00170aG6\t!L\u0003\u0002\\9\u0006!Q\u000f^5m\u0015\u0005i\u0016\u0001\u00026bm\u0006L!a\u0018.\u0003\u00075\u000b\u0007\u000f\u0005\u0002NC&\u0011!\r\u000e\u0002\u0005)&lW\r\u0005\u0002UI&\u0011QM\r\u0002\u0007\u0015>\u00147+\u001a;\u0002\u0011)|'mU3ug\u0002\n\u0011C\\;n\u0007>t7-\u001e:sK:$(j\u001c2t+\u0005I\u0007C\u0001 k\u0013\tYwHA\u0002J]R\f!C\\;n\u0007>t7-\u001e:sK:$(j\u001c2tA\u0005Y!n\u001c2Fq\u0016\u001cW\u000f^8s+\u0005y\u0007C\u00019t\u001b\u0005\t(B\u0001:[\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0003iF\u0014!\u0003\u00165sK\u0006$\u0007k\\8m\u000bb,7-\u001e;pe\u0006a!n\u001c2Fq\u0016\u001cW\u000f^8sA\u0005a!n\u001c2HK:,'/\u0019;peV\t\u0001\u0010\u0005\u0002Us&\u0011!P\r\u0002\r\u0015>\u0014w)\u001a8fe\u0006$xN]\u0001\u000eU>\u0014w)\u001a8fe\u0006$xN\u001d\u0011\u0002\u000b\rdwnY6\u0016\u0003y\u00042a`A\u0002\u001b\t\t\tA\u0003\u0002\\m%!\u0011QAA\u0001\u0005\u0015\u0019En\\2l\u0003\u0019\u0019Gn\\2lA\u0005YA.[:uK:,'OQ;t+\t\ti\u0001E\u0002U\u0003\u001fI1!!\u00053\u0005Q\u0019FO]3b[&tw\rT5ti\u0016tWM\u001d\"vg\u0006aA.[:uK:,'OQ;tA\u0005y!/Z2fSZ,'\u000f\u0016:bG.,'/\u0006\u0002\u0002\u001aA\u0019A+a\u0007\n\u0007\u0005u!GA\bSK\u000e,\u0017N^3s)J\f7m[3s\u0003M\u0011XmY3jm\u0016\u0014HK]1dW\u0016\u0014x\fJ3r)\u0011\t\u0019#!\u000b\u0011\u0007y\n)#C\u0002\u0002(}\u0012A!\u00168ji\"I\u00111F\t\u0002\u0002\u0003\u0007\u0011\u0011D\u0001\u0004q\u0012\n\u0014\u0001\u0005:fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001a:!\u0003AIg\u000e];u\u0013:4w\u000e\u0016:bG.,'/\u0006\u0002\u00024A\u0019A+!\u000e\n\u0007\u0005]\"G\u0001\tJ]B,H/\u00138g_R\u0013\u0018mY6fe\u0006!\u0012N\u001c9vi&sgm\u001c+sC\u000e\\WM]0%KF$B!a\t\u0002>!I\u00111\u0006\u000b\u0002\u0002\u0003\u0007\u00111G\u0001\u0012S:\u0004X\u000f^%oM>$&/Y2lKJ\u0004\u0013!G3yK\u000e,Ho\u001c:BY2|7-\u0019;j_:l\u0015M\\1hKJ,\"!!\u0012\u0011\u000by\n9%a\u0013\n\u0007\u0005%sH\u0001\u0004PaRLwN\u001c\t\u0004)\u00065\u0013bAA(e\tIR\t_3dkR|'/\u00117m_\u000e\fG/[8o\u001b\u0006t\u0017mZ3s\u0003u)\u00070Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8NC:\fw-\u001a:`I\u0015\fH\u0003BA\u0012\u0003+B\u0011\"a\u000b\u0018\u0003\u0003\u0005\r!!\u0012\u00025\u0015DXmY;u_J\fE\u000e\\8dCRLwN\\'b]\u0006<WM\u001d\u0011\u0002\u0013\u00154XM\u001c;M_>\u0004XCAA/!\u0015y\u0018qLA2\u0013\u0011\t\t'!\u0001\u0003\u0013\u00153XM\u001c;M_>\u0004\bc\u0001+\u0002f%\u0019\u0011q\r\u001a\u0003#){'mU2iK\u0012,H.\u001a:Fm\u0016tG/A\u0007fm\u0016tG\u000fT8pa~#S-\u001d\u000b\u0005\u0003G\ti\u0007C\u0005\u0002,i\t\t\u00111\u0001\u0002^\u0005QQM^3oi2{w\u000e\u001d\u0011\u0002\u000bM$\u0018M\u001d;\u0015\u0005\u0005\r\u0012\u0001B:u_B$B!a\t\u0002z!9\u00111P\u000fA\u0002\u0005u\u0014A\u00069s_\u000e,7o]!mYJ+7-Z5wK\u0012$\u0015\r^1\u0011\u0007y\ny(C\u0002\u0002\u0002~\u0012qAQ8pY\u0016\fg.\u0001\u0007tk\nl\u0017\u000e\u001e&pEN+G\u000f\u0006\u0003\u0002$\u0005\u001d\u0005BBAE=\u0001\u00071-\u0001\u0004k_\n\u001cV\r^\u0001\u0010O\u0016$\b+\u001a8eS:<G+[7fgR\u0011\u0011q\u0012\t\u0006\u0003#\u000b\t\u000b\u0019\b\u0005\u0003'\u000biJ\u0004\u0003\u0002\u0016\u0006mUBAAL\u0015\r\tIJS\u0001\u0007yI|w\u000e\u001e \n\u0003\u0001K1!a(@\u0003\u001d\u0001\u0018mY6bO\u0016LA!a)\u0002&\n\u00191+Z9\u000b\u0007\u0005}u(A\u0006sKB|'\u000f^#se>\u0014HCBA\u0012\u0003W\u000by\fC\u0004\u0002.\u0002\u0002\r!a,\u0002\u00075\u001cx\r\u0005\u0003\u00022\u0006ef\u0002BAZ\u0003k\u00032!!&@\u0013\r\t9lP\u0001\u0007!J,G-\u001a4\n\t\u0005m\u0016Q\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005]v\bC\u0004\u0002B\u0002\u0002\r!a1\u0002\u0003\u0015\u0004B!!%\u0002F&!\u0011qYAS\u0005%!\u0006N]8xC\ndW-A\u0005jgN#\u0018M\u001d;fIR\u0011\u0011QP\u0001\raJ|7-Z:t\u000bZ,g\u000e\u001e\u000b\u0005\u0003G\t\t\u000eC\u0004\u0002T\n\u0002\r!a\u0019\u0002\u000b\u00154XM\u001c;\u0002\u001d!\fg\u000e\u001a7f\u0015>\u00147\u000b^1siR1\u00111EAm\u0003GDq!a7$\u0001\u0004\ti.A\u0002k_\n\u00042\u0001VAp\u0013\r\t\tO\r\u0002\u0004\u0015>\u0014\u0007bBAsG\u0001\u0007\u0011q]\u0001\ngR\f'\u000f\u001e+j[\u0016\u00042APAu\u0013\r\tYo\u0010\u0002\u0005\u0019>tw-A\niC:$G.\u001a&pE\u000e{W\u000e\u001d7fi&|g\u000e\u0006\u0004\u0002$\u0005E\u00181\u001f\u0005\b\u00037$\u0003\u0019AAo\u0011\u001d\t)\u0010\na\u0001\u0003O\fQbY8na2,G/\u001a3US6,\u0017a\u00035b]\u0012dW-\u0012:s_J$b!a\t\u0002|\u0006u\bbBAWK\u0001\u0007\u0011q\u0016\u0005\b\u0003\u0003,\u0003\u0019AAb\u0005)QuN\u0019%b]\u0012dWM]\n\u0007M\t\r!qB\"\u0011\t\t\u0015!1B\u0007\u0003\u0005\u000fQ1A!\u0003]\u0003\u0011a\u0017M\\4\n\t\t5!q\u0001\u0002\u0007\u001f\nTWm\u0019;\u0011\t\t\u0015!\u0011C\u0005\u0005\u0005'\u00119A\u0001\u0005Sk:t\u0017M\u00197f)\u0011\u00119Ba\u0007\u0011\u0007\tea%D\u0001\u0001\u0011\u001d\tY\u000e\u000ba\u0001\u0003;\f1A];o\u00031QuNY*dQ\u0016$W\u000f\\3s!\t!6f\u0005\u0002,{Q\u0011!\u0011E\u0001\u0018\u0005\u0006#6\tS0U\u00136+u\f\u0015*P!\u0016\u0013F+W0L\u000bf+\"Aa\u000b\u0011\t\t\u0015!QF\u0005\u0005\u0003w\u00139!\u0001\rC\u0003R\u001b\u0005j\u0018+J\u001b\u0016{\u0006KU(Q\u000bJ#\u0016lX&F3\u0002\n\u0011dT+U!V#vl\u0014)`\u0013\u0012{\u0006KU(Q\u000bJ#\u0016lX&F3\u0006Qr*\u0016+Q+R{v\nU0J\t~\u0003&k\u0014)F%RKvlS#ZA\u0001"
)
public class JobScheduler implements Logging {
   private final StreamingContext ssc;
   private final Map jobSets;
   private final int numConcurrentJobs;
   private final ThreadPoolExecutor jobExecutor;
   private final JobGenerator jobGenerator;
   private final Clock clock;
   private final StreamingListenerBus listenerBus;
   private ReceiverTracker receiverTracker;
   private InputInfoTracker inputInfoTracker;
   private Option executorAllocationManager;
   private EventLoop org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String OUTPUT_OP_ID_PROPERTY_KEY() {
      return JobScheduler$.MODULE$.OUTPUT_OP_ID_PROPERTY_KEY();
   }

   public static String BATCH_TIME_PROPERTY_KEY() {
      return JobScheduler$.MODULE$.BATCH_TIME_PROPERTY_KEY();
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

   public StreamingContext ssc() {
      return this.ssc;
   }

   private Map jobSets() {
      return this.jobSets;
   }

   private int numConcurrentJobs() {
      return this.numConcurrentJobs;
   }

   private ThreadPoolExecutor jobExecutor() {
      return this.jobExecutor;
   }

   public JobGenerator jobGenerator() {
      return this.jobGenerator;
   }

   public Clock clock() {
      return this.clock;
   }

   public StreamingListenerBus listenerBus() {
      return this.listenerBus;
   }

   public ReceiverTracker receiverTracker() {
      return this.receiverTracker;
   }

   public void receiverTracker_$eq(final ReceiverTracker x$1) {
      this.receiverTracker = x$1;
   }

   public InputInfoTracker inputInfoTracker() {
      return this.inputInfoTracker;
   }

   public void inputInfoTracker_$eq(final InputInfoTracker x$1) {
      this.inputInfoTracker = x$1;
   }

   private Option executorAllocationManager() {
      return this.executorAllocationManager;
   }

   private void executorAllocationManager_$eq(final Option x$1) {
      this.executorAllocationManager = x$1;
   }

   public EventLoop org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop() {
      return this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop;
   }

   private void eventLoop_$eq(final EventLoop x$1) {
      this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop = x$1;
   }

   public synchronized void start() {
      if (this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop() == null) {
         this.logDebug((Function0)(() -> "Starting JobScheduler"));
         this.eventLoop_$eq(new EventLoop() {
            // $FF: synthetic field
            private final JobScheduler $outer;

            public void onReceive(final JobSchedulerEvent event) {
               this.$outer.org$apache$spark$streaming$scheduler$JobScheduler$$processEvent(event);
            }

            public void onError(final Throwable e) {
               this.$outer.reportError("Error in job scheduler", e);
            }

            public {
               if (JobScheduler.this == null) {
                  throw null;
               } else {
                  this.$outer = JobScheduler.this;
               }
            }
         });
         this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop().start();
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.ssc().graph().getInputStreams()), (inputDStream) -> {
            $anonfun$start$2(this, inputDStream);
            return BoxedUnit.UNIT;
         });
         this.listenerBus().start();
         this.receiverTracker_$eq(new ReceiverTracker(this.ssc(), ReceiverTracker$.MODULE$.$lessinit$greater$default$2()));
         this.inputInfoTracker_$eq(new InputInfoTracker(this.ssc()));
         SchedulerBackend var3 = this.ssc().sparkContext().schedulerBackend();
         ExecutorAllocationClient executorAllocClient = var3 instanceof ExecutorAllocationClient ? (ExecutorAllocationClient)var3 : null;
         this.executorAllocationManager_$eq(ExecutorAllocationManager$.MODULE$.createIfEnabled(executorAllocClient, this.receiverTracker(), this.ssc().conf(), this.ssc().graph().batchDuration().milliseconds(), this.clock()));
         this.executorAllocationManager().foreach((streamingListener) -> {
            $anonfun$start$4(this, streamingListener);
            return BoxedUnit.UNIT;
         });
         this.receiverTracker().start();
         this.jobGenerator().start();
         this.executorAllocationManager().foreach((x$1) -> {
            $anonfun$start$5(x$1);
            return BoxedUnit.UNIT;
         });
         this.logInfo((Function0)(() -> "Started JobScheduler"));
      }
   }

   public synchronized void stop(final boolean processAllReceivedData) {
      if (this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop() != null) {
         this.logDebug((Function0)(() -> "Stopping JobScheduler"));
         if (this.receiverTracker() != null) {
            this.receiverTracker().stop(processAllReceivedData);
         }

         if (this.executorAllocationManager() != null) {
            this.executorAllocationManager().foreach((x$2) -> {
               $anonfun$stop$2(x$2);
               return BoxedUnit.UNIT;
            });
         }

         this.jobGenerator().stop(processAllReceivedData);
         this.logDebug((Function0)(() -> "Stopping job executor"));
         if (processAllReceivedData) {
            org.apache.spark.util.ThreadUtils..MODULE$.shutdown(this.jobExecutor(), scala.concurrent.duration.FiniteDuration..MODULE$.apply(1L, TimeUnit.HOURS));
         } else {
            org.apache.spark.util.ThreadUtils..MODULE$.shutdown(this.jobExecutor(), scala.concurrent.duration.FiniteDuration..MODULE$.apply(2L, TimeUnit.SECONDS));
         }

         this.logDebug((Function0)(() -> "Stopped job executor"));
         this.listenerBus().stop();
         this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop().stop();
         this.eventLoop_$eq((EventLoop)null);
         this.logInfo((Function0)(() -> "Stopped JobScheduler"));
      }
   }

   public void submitJobSet(final JobSet jobSet) {
      if (jobSet.jobs().isEmpty()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No jobs added for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, jobSet.time())})))));
      } else {
         this.listenerBus().post(new StreamingListenerBatchSubmitted(jobSet.toBatchInfo()));
         this.jobSets().put(jobSet.time(), jobSet);
         jobSet.jobs().foreach((job) -> {
            $anonfun$submitJobSet$2(this, job);
            return BoxedUnit.UNIT;
         });
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added jobs for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, jobSet.time())})))));
      }
   }

   public Seq getPendingTimes() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.jobSets()).asScala().keys().toSeq();
   }

   public void reportError(final String msg, final Throwable e) {
      this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop().post(new ErrorReported(msg, e));
   }

   public synchronized boolean isStarted() {
      return this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop() != null;
   }

   public void org$apache$spark$streaming$scheduler$JobScheduler$$processEvent(final JobSchedulerEvent event) {
      try {
         if (event instanceof JobStarted var4) {
            Job job = var4.job();
            long startTime = var4.startTime();
            this.handleJobStart(job, startTime);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else if (event instanceof JobCompleted var8) {
            Job job = var8.job();
            long completedTime = var8.completedTime();
            this.handleJobCompletion(job, completedTime);
            BoxedUnit var17 = BoxedUnit.UNIT;
         } else {
            if (!(event instanceof ErrorReported)) {
               throw new MatchError(event);
            }

            ErrorReported var12 = (ErrorReported)event;
            String m = var12.msg();
            Throwable e = var12.e();
            this.handleError(m, e);
            BoxedUnit var18 = BoxedUnit.UNIT;
         }
      } catch (Throwable var16) {
         this.reportError("Error in job scheduler", var16);
      }

   }

   private void handleJobStart(final Job job, final long startTime) {
      JobSet jobSet = (JobSet)this.jobSets().get(job.time());
      boolean isFirstJobOfJobSet = !jobSet.hasStarted();
      jobSet.handleJobStart(job);
      if (isFirstJobOfJobSet) {
         this.listenerBus().post(new StreamingListenerBatchStarted(jobSet.toBatchInfo()));
      }

      job.setStartTime(startTime);
      this.listenerBus().post(new StreamingListenerOutputOperationStarted(job.toOutputOperationInfo()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting job ", " from job set of time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, job.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, jobSet.time())}))))));
   }

   private void handleJobCompletion(final Job job, final long completedTime) {
      JobSet jobSet = (JobSet)this.jobSets().get(job.time());
      jobSet.handleJobCompletion(job);
      job.setEndTime(completedTime);
      this.listenerBus().post(new StreamingListenerOutputOperationCompleted(job.toOutputOperationInfo()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished job ", " from job set of time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, job.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, jobSet.time())}))))));
      if (jobSet.hasCompleted()) {
         this.listenerBus().post(new StreamingListenerBatchCompleted(jobSet.toBatchInfo()));
      }

      Try var6 = job.result();
      if (var6 instanceof Failure var7) {
         Throwable e = var7.exception();
         this.reportError("Error running job " + job, e);
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else if (jobSet.hasCompleted()) {
         this.jobSets().remove(jobSet.time());
         this.jobGenerator().onBatchCompletion(jobSet.time());
         this.logInfo((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Total delay: %.3f s for time %s (execution: %.3f s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble((double)jobSet.totalDelay() / (double)1000.0F), jobSet.time().toString(), BoxesRunTime.boxToDouble((double)jobSet.processingDelay() / (double)1000.0F)}))));
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private void handleError(final String msg, final Throwable e) {
      this.logError((Function0)(() -> msg), e);
      this.ssc().waiter().notifyError(e);
      PythonDStream$.MODULE$.stopStreamingContextIfPythonProcessIsDead(e);
   }

   // $FF: synthetic method
   public static final void $anonfun$start$3(final JobScheduler $this, final RateController rateController) {
      $this.ssc().addStreamingListener(rateController);
   }

   // $FF: synthetic method
   public static final void $anonfun$start$2(final JobScheduler $this, final InputDStream inputDStream) {
      inputDStream.rateController().foreach((rateController) -> {
         $anonfun$start$3($this, rateController);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$start$4(final JobScheduler $this, final StreamingListener streamingListener) {
      $this.ssc().addStreamingListener(streamingListener);
   }

   // $FF: synthetic method
   public static final void $anonfun$start$5(final ExecutorAllocationManager x$1) {
      x$1.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final ExecutorAllocationManager x$2) {
      x$2.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$submitJobSet$2(final JobScheduler $this, final Job job) {
      $this.jobExecutor().execute($this.new JobHandler(job));
   }

   public JobScheduler(final StreamingContext ssc) {
      this.ssc = ssc;
      Logging.$init$(this);
      this.jobSets = new ConcurrentHashMap();
      this.numConcurrentJobs = BoxesRunTime.unboxToInt(ssc.conf().get(StreamingConf$.MODULE$.CONCURRENT_JOBS()));
      this.jobExecutor = org.apache.spark.util.ThreadUtils..MODULE$.newDaemonFixedThreadPool(this.numConcurrentJobs(), "streaming-job-executor");
      this.jobGenerator = new JobGenerator(this);
      this.clock = this.jobGenerator().clock();
      this.listenerBus = new StreamingListenerBus(ssc.sparkContext().listenerBus());
      this.receiverTracker = null;
      this.inputInfoTracker = null;
      this.executorAllocationManager = scala.None..MODULE$;
      this.org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class JobHandler implements Runnable, Logging {
      private final Job job;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      // $FF: synthetic field
      public final JobScheduler $outer;

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

      public void run() {
         Properties oldProps = this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sparkContext().getLocalProperties();

         try {
            this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sparkContext().setLocalProperties(org.apache.spark.util.Utils..MODULE$.cloneProperties((Properties)this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().savedProperties().get()));
            String formattedTime = org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime(this.job.time().milliseconds(), this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().graph().batchDuration().milliseconds(), false, org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime$default$4());
            String batchUrl = "/streaming/batch/?id=" + this.job.time().milliseconds();
            int var10000 = this.job.outputOpId();
            String batchLinkText = "[output operation " + var10000 + ", batch time " + formattedTime + "]";
            this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sc().setJobDescription("Streaming job from <a href=\"" + batchUrl + "\">" + batchLinkText + "</a>");
            this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sc().setLocalProperty(JobScheduler$.MODULE$.BATCH_TIME_PROPERTY_KEY(), Long.toString(this.job.time().milliseconds()));
            this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sc().setLocalProperty(JobScheduler$.MODULE$.OUTPUT_OP_ID_PROPERTY_KEY(), Integer.toString(this.job.outputOpId()));
            this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sparkContext().setLocalProperty(org.apache.spark.rdd.RDD..MODULE$.CHECKPOINT_ALL_MARKED_ANCESTORS(), "true");
            EventLoop _eventLoop = this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop();
            if (_eventLoop != null) {
               _eventLoop.post(new JobStarted(this.job, this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().clock().getTimeMillis()));
               org.apache.spark.internal.io.SparkHadoopWriterUtils..MODULE$.disableOutputSpecValidation().withValue(BoxesRunTime.boxToBoolean(true), (JFunction0.mcV.sp)() -> this.job.run());
               _eventLoop = this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().org$apache$spark$streaming$scheduler$JobScheduler$$eventLoop();
               if (_eventLoop != null) {
                  _eventLoop.post(new JobCompleted(this.job, this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().clock().getTimeMillis()));
               }
            }
         } finally {
            this.org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer().ssc().sparkContext().setLocalProperties(oldProps);
         }

      }

      // $FF: synthetic method
      public JobScheduler org$apache$spark$streaming$scheduler$JobScheduler$JobHandler$$$outer() {
         return this.$outer;
      }

      public JobHandler(final Job job) {
         this.job = job;
         if (JobScheduler.this == null) {
            throw null;
         } else {
            this.$outer = JobScheduler.this;
            super();
            Logging.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
