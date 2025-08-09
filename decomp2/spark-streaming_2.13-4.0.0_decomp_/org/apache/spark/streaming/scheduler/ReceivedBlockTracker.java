package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.util.WriteAheadLog;
import org.apache.spark.streaming.util.WriteAheadLogUtils$;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg!B\u0012%\u0001\u0019r\u0003\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011\u0005\u0003!\u0011!Q\u0001\n\tC\u0001\"\u0013\u0001\u0003\u0002\u0003\u0006IA\u0013\u0005\t3\u0002\u0011\t\u0011)A\u00055\"A\u0001\r\u0001B\u0001B\u0003%\u0011\r\u0003\u0005e\u0001\t\u0005\t\u0015!\u0003f\u0011\u0015\u0001\b\u0001\"\u0001r\u000b\u0011Q\b\u0001B>\t\u0013\u00055\u0001A1A\u0005\n\u0005=\u0001\u0002CA\u000e\u0001\u0001\u0006I!!\u0005\t\u0013\u0005u\u0001A1A\u0005\n\u0005}\u0001\u0002CA\u0019\u0001\u0001\u0006I!!\t\t\u0013\u0005M\u0002A1A\u0005\n\u0005U\u0002\u0002CA\"\u0001\u0001\u0006I!a\u000e\t\u0013\u0005\u0015\u0003\u00011A\u0005\n\u0005\u001d\u0003\"CA%\u0001\u0001\u0007I\u0011BA&\u0011!\t9\u0006\u0001Q!\n\u0005\r\u0002bBA-\u0001\u0011\u0005\u00111\f\u0005\b\u0003C\u0002A\u0011AA2\u0011\u001d\tI\u0007\u0001C\u0001\u0003WBq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002\u0002\u0002!\t!a!\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u00111\u0012\u0001\u0005\u0002\u00055\u0005bBAL\u0001\u0011\u0005\u0011\u0011\u0014\u0005\b\u00037\u0003A\u0011BAM\u0011!\ti\n\u0001C\u0001M\u0005}\u0005bBAV\u0001\u0011%\u0011Q\u0016\u0005\b\u0003c\u0003A\u0011BAZ\u0011!\t)\f\u0001C\u0001M\u0005\ru\u0001CA\\I!\u0005a%!/\u0007\u000f\r\"\u0003\u0012\u0001\u0014\u0002<\"1\u0001\u000f\tC\u0001\u0003{Cq!a0!\t\u0003\t\tM\u0001\u000bSK\u000e,\u0017N^3e\u00052|7m\u001b+sC\u000e\\WM\u001d\u0006\u0003K\u0019\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u001dB\u0013!C:ue\u0016\fW.\u001b8h\u0015\tI#&A\u0003ta\u0006\u00148N\u0003\u0002,Y\u00051\u0011\r]1dQ\u0016T\u0011!L\u0001\u0004_J<7c\u0001\u00010kA\u0011\u0001gM\u0007\u0002c)\t!'A\u0003tG\u0006d\u0017-\u0003\u00025c\t1\u0011I\\=SK\u001a\u0004\"AN\u001d\u000e\u0003]R!\u0001\u000f\u0015\u0002\u0011%tG/\u001a:oC2L!AO\u001c\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g\u0007\u0001\u0001\"AP \u000e\u0003!J!\u0001\u0011\u0015\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u00035bI>|\u0007oQ8oMB\u00111iR\u0007\u0002\t*\u00111(\u0012\u0006\u0003\r*\na\u0001[1e_>\u0004\u0018B\u0001%E\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0006I1\u000f\u001e:fC6LEm\u001d\t\u0004\u0017N3fB\u0001'R\u001d\ti\u0005+D\u0001O\u0015\tyE(\u0001\u0004=e>|GOP\u0005\u0002e%\u0011!+M\u0001\ba\u0006\u001c7.Y4f\u0013\t!VKA\u0002TKFT!AU\u0019\u0011\u0005A:\u0016B\u0001-2\u0005\rIe\u000e^\u0001\u0006G2|7m\u001b\t\u00037zk\u0011\u0001\u0018\u0006\u0003;\"\nA!\u001e;jY&\u0011q\f\u0018\u0002\u0006\u00072|7m[\u0001\u0019e\u0016\u001cwN^3s\rJ|Wn\u0016:ji\u0016\f\u0005.Z1e\u0019><\u0007C\u0001\u0019c\u0013\t\u0019\u0017GA\u0004C_>dW-\u00198\u0002'\rDWmY6q_&tG\u000fR5s\u001fB$\u0018n\u001c8\u0011\u0007A2\u0007.\u0003\u0002hc\t1q\n\u001d;j_:\u0004\"![7\u000f\u0005)\\\u0007CA'2\u0013\ta\u0017'\u0001\u0004Qe\u0016$WMZ\u0005\u0003]>\u0014aa\u0015;sS:<'B\u000172\u0003\u0019a\u0014N\\5u}Q9!\u000f^;wobL\bCA:\u0001\u001b\u0005!\u0003\"B\u001e\b\u0001\u0004i\u0004\"B!\b\u0001\u0004\u0011\u0005\"B%\b\u0001\u0004Q\u0005\"B-\b\u0001\u0004Q\u0006\"\u00021\b\u0001\u0004\t\u0007\"\u00023\b\u0001\u0004)'A\u0005*fG\u0016Lg/\u001a3CY>\u001c7.U;fk\u0016\u0004R\u0001`A\u0002\u0003\u000fi\u0011! \u0006\u0003}~\fq!\\;uC\ndWMC\u0002\u0002\u0002E\n!bY8mY\u0016\u001cG/[8o\u0013\r\t)! \u0002\u0006#V,W/\u001a\t\u0004g\u0006%\u0011bAA\u0006I\t\t\"+Z2fSZ,GM\u00117pG.LeNZ8\u0002AM$(/Z1n\u0013\u0012$v.\u00168bY2|7-\u0019;fI\ncwnY6Rk\u0016,Xm]\u000b\u0003\u0003#\u0001b\u0001`A\n-\u0006]\u0011bAA\u000b{\n9\u0001*Y:i\u001b\u0006\u0004\bcAA\r\u00115\t\u0001!A\u0011tiJ,\u0017-\\%e)>,f.\u00197m_\u000e\fG/\u001a3CY>\u001c7.U;fk\u0016\u001c\b%A\u000buS6,Gk\\!mY>\u001c\u0017\r^3e\u00052|7m[:\u0016\u0005\u0005\u0005\u0002c\u0002?\u0002\u0014\u0005\r\u00121\u0006\t\u0005\u0003K\t9#D\u0001'\u0013\r\tIC\n\u0002\u0005)&lW\rE\u0002t\u0003[I1!a\f%\u0005=\tE\u000e\\8dCR,GM\u00117pG.\u001c\u0018A\u0006;j[\u0016$v.\u00117m_\u000e\fG/\u001a3CY>\u001c7n\u001d\u0011\u0002']\u0014\u0018\u000e^3BQ\u0016\fG\rT8h\u001fB$\u0018n\u001c8\u0016\u0005\u0005]\u0002\u0003\u0002\u0019g\u0003s\u0001B!a\u000f\u0002@5\u0011\u0011Q\b\u0006\u0003;\u001aJA!!\u0011\u0002>\tiqK]5uK\u0006CW-\u00193M_\u001e\fAc\u001e:ji\u0016\f\u0005.Z1e\u0019><w\n\u001d;j_:\u0004\u0013A\u00067bgR\fE\u000e\\8dCR,GMQ1uG\"$\u0016.\\3\u0016\u0005\u0005\r\u0012A\u00077bgR\fE\u000e\\8dCR,GMQ1uG\"$\u0016.\\3`I\u0015\fH\u0003BA'\u0003'\u00022\u0001MA(\u0013\r\t\t&\r\u0002\u0005+:LG\u000fC\u0005\u0002VA\t\t\u00111\u0001\u0002$\u0005\u0019\u0001\u0010J\u0019\u0002/1\f7\u000f^!mY>\u001c\u0017\r^3e\u0005\u0006$8\r\u001b+j[\u0016\u0004\u0013\u0001C1eI\ncwnY6\u0015\u0007\u0005\fi\u0006C\u0004\u0002`I\u0001\r!a\u0002\u0002#I,7-Z5wK\u0012\u0014En\\2l\u0013:4w.A\u000bbY2|7-\u0019;f\u00052|7m[:U_\n\u000bGo\u00195\u0015\t\u00055\u0013Q\r\u0005\b\u0003O\u001a\u0002\u0019AA\u0012\u0003%\u0011\u0017\r^2i)&lW-\u0001\thKR\u0014En\\2lg>3')\u0019;dQR!\u0011QNA;!\u0019I\u0017q\u000e,\u0002t%\u0019\u0011\u0011O8\u0003\u00075\u000b\u0007\u000f\u0005\u0003L'\u0006\u001d\u0001bBA4)\u0001\u0007\u00111E\u0001\u001aO\u0016$(\t\\8dWN|eMQ1uG\"\fe\u000eZ*ue\u0016\fW\u000e\u0006\u0004\u0002t\u0005m\u0014Q\u0010\u0005\b\u0003O*\u0002\u0019AA\u0012\u0011\u0019\ty(\u0006a\u0001-\u0006A1\u000f\u001e:fC6LE-\u0001\u000fiCN,f.\u00197m_\u000e\fG/\u001a3SK\u000e,\u0017N^3e\u00052|7m[:\u0016\u0003\u0005\fAcZ3u+:\fG\u000e\\8dCR,GM\u00117pG.\u001cH\u0003BA:\u0003\u0013Ca!a \u0018\u0001\u00041\u0016!E2mK\u0006tW\u000f](mI\n\u000bGo\u00195fgR1\u0011QJAH\u0003'Cq!!%\u0019\u0001\u0004\t\u0019#A\tdY\u0016\fg.\u001e9UQJ,7\u000f\u001b+j[\u0016Da!!&\u0019\u0001\u0004\t\u0017!E<bSR4uN]\"p[BdW\r^5p]\u0006!1\u000f^8q)\t\ti%A\tsK\u000e|g/\u001a:QCN$XI^3oiN\f!b\u001e:ji\u0016$v\u000eT8h)\r\t\u0017\u0011\u0015\u0005\b\u0003G[\u0002\u0019AAS\u0003\u0019\u0011XmY8sIB\u00191/a*\n\u0007\u0005%FE\u0001\u000fSK\u000e,\u0017N^3e\u00052|7m\u001b+sC\u000e\\WM\u001d'pO\u00163XM\u001c;\u0002+\u001d,GOU3dK&4X\r\u001a\"m_\u000e\\\u0017+^3vKR!\u0011qCAX\u0011\u0019\ty\b\ba\u0001-\u0006\u00192M]3bi\u0016<&/\u001b;f\u0003\",\u0017\r\u001a'pOR\u0011\u0011qG\u0001\u0017SN<&/\u001b;f\u0003\",\u0017\r\u001a'pO\u0016s\u0017M\u00197fI\u0006!\"+Z2fSZ,GM\u00117pG.$&/Y2lKJ\u0004\"a\u001d\u0011\u0014\u0005\u0001zCCAA]\u0003U\u0019\u0007.Z2la>Lg\u000e\u001e#jeR{Gj\\4ESJ$2\u0001[Ab\u0011\u0019\t)M\ta\u0001Q\u0006i1\r[3dWB|\u0017N\u001c;ESJ\u0004"
)
public class ReceivedBlockTracker implements Logging {
   private final SparkConf conf;
   private final Configuration hadoopConf;
   private final Seq streamIds;
   private final Clock clock;
   private final Option checkpointDirOption;
   private final HashMap streamIdToUnallocatedBlockQueues;
   private final HashMap timeToAllocatedBlocks;
   private final Option writeAheadLogOption;
   private Time lastAllocatedBatchTime;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String checkpointDirToLogDir(final String checkpointDir) {
      return ReceivedBlockTracker$.MODULE$.checkpointDirToLogDir(checkpointDir);
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

   private HashMap streamIdToUnallocatedBlockQueues() {
      return this.streamIdToUnallocatedBlockQueues;
   }

   private HashMap timeToAllocatedBlocks() {
      return this.timeToAllocatedBlocks;
   }

   private Option writeAheadLogOption() {
      return this.writeAheadLogOption;
   }

   private Time lastAllocatedBatchTime() {
      return this.lastAllocatedBatchTime;
   }

   private void lastAllocatedBatchTime_$eq(final Time x$1) {
      this.lastAllocatedBatchTime = x$1;
   }

   public boolean addBlock(final ReceivedBlockInfo receivedBlockInfo) {
      boolean var10000;
      try {
         boolean writeResult = this.writeToLog(new BlockAdditionEvent(receivedBlockInfo));
         if (writeResult) {
            synchronized(this){}

            try {
               Queue var12 = (Queue)this.getReceivedBlockQueue(receivedBlockInfo.streamId()).$plus$eq(receivedBlockInfo);
            } catch (Throwable var10) {
               throw var10;
            }

            this.logDebug((Function0)(() -> {
               int var10000 = receivedBlockInfo.streamId();
               return "Stream " + var10000 + " received block " + receivedBlockInfo.blockStoreResult().blockId();
            }));
         } else {
            this.logDebug((Function0)(() -> {
               int var10000 = receivedBlockInfo.streamId();
               return "Failed to acknowledge stream " + var10000 + " receiving block " + receivedBlockInfo.blockStoreResult().blockId() + " in the Write Ahead Log.";
            }));
         }

         var10000 = writeResult;
      } catch (Throwable var11) {
         if (var11 == null || !.MODULE$.apply(var11)) {
            throw var11;
         }

         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error adding block ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RECEIVED_BLOCK_INFO..MODULE$, receivedBlockInfo)})))), var11);
         var10000 = false;
      }

      return var10000;
   }

   public synchronized void allocateBlocksToBatch(final Time batchTime) {
      if (this.lastAllocatedBatchTime() != null && !batchTime.$greater(this.lastAllocatedBatchTime())) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Possibly processed batch ", " needs to be processed "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BATCH_TIMESTAMP..MODULE$, batchTime)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"again in WAL recovery"})))).log(scala.collection.immutable.Nil..MODULE$))));
      } else {
         scala.collection.immutable.Map streamIdToBlocks = ((IterableOnceOps)this.streamIds.map((streamId) -> $anonfun$allocateBlocksToBatch$1(this, BoxesRunTime.unboxToInt(streamId)))).toMap(scala..less.colon.less..MODULE$.refl());
         AllocatedBlocks allocatedBlocks = new AllocatedBlocks(streamIdToBlocks);
         if (this.writeToLog(new BatchAllocationEvent(batchTime, allocatedBlocks))) {
            this.streamIds.foreach((JFunction1.mcVI.sp)(x$1) -> this.getReceivedBlockQueue(x$1).clear());
            this.timeToAllocatedBlocks().put(batchTime, allocatedBlocks);
            this.lastAllocatedBatchTime_$eq(batchTime);
         } else {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Possibly processed batch ", " needs to be "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BATCH_TIMESTAMP..MODULE$, batchTime)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"processed again in WAL recovery"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }
      }
   }

   public synchronized scala.collection.immutable.Map getBlocksOfBatch(final Time batchTime) {
      return (scala.collection.immutable.Map)this.timeToAllocatedBlocks().get(batchTime).map((x$2) -> x$2.streamIdToAllocatedBlocks()).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
   }

   public synchronized Seq getBlocksOfBatchAndStream(final Time batchTime, final int streamId) {
      return (Seq)this.timeToAllocatedBlocks().get(batchTime).map((x$3) -> x$3.getBlocksOfStream(streamId)).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
   }

   public synchronized boolean hasUnallocatedReceivedBlocks() {
      return !this.streamIdToUnallocatedBlockQueues().values().forall((x$4) -> BoxesRunTime.boxToBoolean($anonfun$hasUnallocatedReceivedBlocks$1(x$4)));
   }

   public synchronized Seq getUnallocatedBlocks(final int streamId) {
      return this.getReceivedBlockQueue(streamId).toSeq();
   }

   public synchronized void cleanupOldBatches(final Time cleanupThreshTime, final boolean waitForCompletion) {
      scala.Predef..MODULE$.require(cleanupThreshTime.milliseconds() < this.clock.getTimeMillis());
      Seq timesToCleanup = ((IterableOnceOps)this.timeToAllocatedBlocks().keys().filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$cleanupOldBatches$1(cleanupThreshTime, x$5)))).toSeq();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting batches: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, timesToCleanup.mkString(" "))})))));
      if (this.writeToLog(new BatchCleanupEvent(timesToCleanup))) {
         this.timeToAllocatedBlocks().$minus$minus$eq(timesToCleanup);
         this.writeAheadLogOption().foreach((x$6) -> {
            $anonfun$cleanupOldBatches$3(cleanupThreshTime, waitForCompletion, x$6);
            return BoxedUnit.UNIT;
         });
      } else {
         this.logWarning((Function0)(() -> "Failed to acknowledge batch clean up in the Write Ahead Log."));
      }
   }

   public void stop() {
      this.writeAheadLogOption().foreach((x$7) -> {
         $anonfun$stop$1(x$7);
         return BoxedUnit.UNIT;
      });
   }

   private synchronized void recoverPastEvents() {
      this.writeAheadLogOption().foreach((writeAheadLog) -> {
         $anonfun$recoverPastEvents$5(this, writeAheadLog);
         return BoxedUnit.UNIT;
      });
   }

   public boolean writeToLog(final ReceivedBlockTrackerLogEvent record) {
      if (this.isWriteAheadLogEnabled()) {
         this.logTrace((Function0)(() -> "Writing record: " + record));

         boolean var10000;
         try {
            ((WriteAheadLog)this.writeAheadLogOption().get()).write(ByteBuffer.wrap(org.apache.spark.util.Utils..MODULE$.serialize(record)), this.clock.getTimeMillis());
            var10000 = true;
         } catch (Throwable var6) {
            if (var6 == null || !.MODULE$.apply(var6)) {
               throw var6;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception thrown while writing record: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " to the WriteAheadLog."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RECEIVED_BLOCK_TRACKER_LOG_EVENT..MODULE$, record)}))))), var6);
            var10000 = false;
         }

         return var10000;
      } else {
         return true;
      }
   }

   private Queue getReceivedBlockQueue(final int streamId) {
      return (Queue)this.streamIdToUnallocatedBlockQueues().getOrElseUpdate(BoxesRunTime.boxToInteger(streamId), () -> new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1()));
   }

   private Option createWriteAheadLog() {
      return this.checkpointDirOption.map((checkpointDir) -> {
         String logDir = ReceivedBlockTracker$.MODULE$.checkpointDirToLogDir((String)this.checkpointDirOption.get());
         return WriteAheadLogUtils$.MODULE$.createLogForDriver(this.conf, logDir, this.hadoopConf);
      });
   }

   public boolean isWriteAheadLogEnabled() {
      return this.writeAheadLogOption().nonEmpty();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$allocateBlocksToBatch$1(final ReceivedBlockTracker $this, final int streamId) {
      ArrayBuffer blocks = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      blocks.$plus$plus$eq((IterableOnce)$this.getReceivedBlockQueue(streamId).clone());
      return new Tuple2(BoxesRunTime.boxToInteger(streamId), blocks.toSeq());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasUnallocatedReceivedBlocks$1(final Queue x$4) {
      return x$4.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupOldBatches$1(final Time cleanupThreshTime$1, final Time x$5) {
      return x$5.$less(cleanupThreshTime$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupOldBatches$3(final Time cleanupThreshTime$1, final boolean waitForCompletion$1, final WriteAheadLog x$6) {
      x$6.clean(cleanupThreshTime$1.milliseconds(), waitForCompletion$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final WriteAheadLog x$7) {
      x$7.close();
   }

   private final void insertAddedBlock$1(final ReceivedBlockInfo receivedBlockInfo) {
      this.logTrace((Function0)(() -> "Recovery: Inserting added block " + receivedBlockInfo));
      receivedBlockInfo.setBlockIdInvalid();
      this.getReceivedBlockQueue(receivedBlockInfo.streamId()).$plus$eq(receivedBlockInfo);
   }

   private final void insertAllocatedBatch$1(final Time batchTime, final AllocatedBlocks allocatedBlocks) {
      this.logTrace((Function0)(() -> "Recovery: Inserting allocated batch for time " + batchTime + " to " + allocatedBlocks.streamIdToAllocatedBlocks()));
      allocatedBlocks.streamIdToAllocatedBlocks().foreach((x0$1) -> {
         if (x0$1 != null) {
            int streamId = x0$1._1$mcI$sp();
            Seq allocatedBlocksInStream = (Seq)x0$1._2();
            return this.getReceivedBlockQueue(streamId).dequeueAll(allocatedBlocksInStream.toSet());
         } else {
            throw new MatchError(x0$1);
         }
      });
      this.timeToAllocatedBlocks().put(batchTime, allocatedBlocks);
      this.lastAllocatedBatchTime_$eq(batchTime);
   }

   private final void cleanupBatches$1(final Seq batchTimes) {
      this.logTrace((Function0)(() -> "Recovery: Cleaning up batches " + batchTimes));
      this.timeToAllocatedBlocks().$minus$minus$eq(batchTimes);
   }

   // $FF: synthetic method
   public static final void $anonfun$recoverPastEvents$7(final ReceivedBlockTracker $this, final ByteBuffer byteBuffer) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Recovering record ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BYTE_BUFFER..MODULE$, byteBuffer)})))));
      ReceivedBlockTrackerLogEvent var3 = (ReceivedBlockTrackerLogEvent)org.apache.spark.util.Utils..MODULE$.deserialize(JavaUtils.bufferToArray(byteBuffer), Thread.currentThread().getContextClassLoader());
      if (var3 instanceof BlockAdditionEvent var4) {
         ReceivedBlockInfo receivedBlockInfo = var4.receivedBlockInfo();
         $this.insertAddedBlock$1(receivedBlockInfo);
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (var3 instanceof BatchAllocationEvent var6) {
         Time time = var6.time();
         AllocatedBlocks allocatedBlocks = var6.allocatedBlocks();
         $this.insertAllocatedBatch$1(time, allocatedBlocks);
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (var3 instanceof BatchCleanupEvent var9) {
         Seq batchTimes = var9.times();
         $this.cleanupBatches$1(batchTimes);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$recoverPastEvents$5(final ReceivedBlockTracker $this, final WriteAheadLog writeAheadLog) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Recovering from write ahead logs in "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, $this.checkpointDirOption.get())}))))));
      scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(writeAheadLog.readAll()).asScala().foreach((byteBuffer) -> {
         $anonfun$recoverPastEvents$7($this, byteBuffer);
         return BoxedUnit.UNIT;
      });
   }

   public ReceivedBlockTracker(final SparkConf conf, final Configuration hadoopConf, final Seq streamIds, final Clock clock, final boolean recoverFromWriteAheadLog, final Option checkpointDirOption) {
      this.conf = conf;
      this.hadoopConf = hadoopConf;
      this.streamIds = streamIds;
      this.clock = clock;
      this.checkpointDirOption = checkpointDirOption;
      Logging.$init$(this);
      this.streamIdToUnallocatedBlockQueues = new HashMap();
      this.timeToAllocatedBlocks = new HashMap();
      this.writeAheadLogOption = this.createWriteAheadLog();
      this.lastAllocatedBatchTime = null;
      if (recoverFromWriteAheadLog) {
         this.recoverPastEvents();
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
