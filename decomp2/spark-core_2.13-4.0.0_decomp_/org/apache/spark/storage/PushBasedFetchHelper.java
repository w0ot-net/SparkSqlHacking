package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.spark.MapOutputTracker;
import org.apache.spark.MapOutputTracker$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.network.shuffle.BlockStoreClient;
import org.apache.spark.network.shuffle.MergedBlockMeta;
import org.apache.spark.network.shuffle.MergedBlocksMetaListener;
import org.apache.spark.shuffle.ShuffleReadMetricsReporter;
import org.roaringbitmap.RoaringBitmap;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.LinkedHashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-h\u0001B\u000f\u001f\t\u001dB\u0001\u0002\u000e\u0001\u0003\u0006\u0004%I!\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005m!A1\b\u0001BC\u0002\u0013%A\b\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003>\u0011!1\u0005A!b\u0001\n\u00139\u0005\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u00111\u0003!Q1A\u0005\n5C\u0001B\u0015\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\t'\u0002\u0011)\u0019!C\u0005)\"A!\f\u0001B\u0001B\u0003%Q\u000bC\u0003\\\u0001\u0011\u0005A\f\u0003\u0004d\u0001\u0001\u0006I\u0001\u001a\u0005\tO\u0002\u0011\r\u0011\"\u0001\u001fQ\"1A\u000e\u0001Q\u0001\n%Da!\u001c\u0001!\u0002\u0013q\u0007BB@\u0001\t\u0003\t\t\u0001C\u0004\u0002\u000e\u0001!\t!a\u0004\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016!9\u0011\u0011\u0004\u0001\u0005\u0002\u0005m\u0001bBA\u0014\u0001\u0011\u0005\u0011\u0011\u0006\u0005\b\u0003c\u0001A\u0011AA\u001a\u0011\u001d\ti\u0004\u0001C\u0001\u0003\u007fAq!!\u0013\u0001\t\u0003\tY\u0005C\u0004\u0002z\u0001!\t!a\u001f\t\u000f\u0005\u0015\u0006\u0001\"\u0001\u0002(\"9\u00111\u0017\u0001\u0005\n\u0005U\u0006\u0002CAb\u0001\u0001&I!!2\t\u000f\u0005\r\b\u0001\"\u0001\u0002f\n!\u0002+^:i\u0005\u0006\u001cX\r\u001a$fi\u000eD\u0007*\u001a7qKJT!a\b\u0011\u0002\u000fM$xN]1hK*\u0011\u0011EI\u0001\u0006gB\f'o\u001b\u0006\u0003G\u0011\na!\u00199bG\",'\"A\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001Ac\u0006\u0005\u0002*Y5\t!FC\u0001,\u0003\u0015\u00198-\u00197b\u0013\ti#F\u0001\u0004B]f\u0014VM\u001a\t\u0003_Ij\u0011\u0001\r\u0006\u0003c\u0001\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003gA\u0012q\u0001T8hO&tw-\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u00051\u0004CA\u001c9\u001b\u0005q\u0012BA\u001d\u001f\u0005m\u0019\u0006.\u001e4gY\u0016\u0014En\\2l\r\u0016$8\r[3s\u0013R,'/\u0019;pe\u0006I\u0011\u000e^3sCR|'\u000fI\u0001\u000eg\",hM\u001a7f\u00072LWM\u001c;\u0016\u0003u\u0002\"AP\"\u000e\u0003}R!\u0001Q!\u0002\u000fMDWO\u001a4mK*\u0011!\tI\u0001\b]\u0016$xo\u001c:l\u0013\t!uH\u0001\tCY>\u001c7n\u0015;pe\u0016\u001cE.[3oi\u0006q1\u000f[;gM2,7\t\\5f]R\u0004\u0013\u0001\u00042m_\u000e\\W*\u00198bO\u0016\u0014X#\u0001%\u0011\u0005]J\u0015B\u0001&\u001f\u00051\u0011En\\2l\u001b\u0006t\u0017mZ3s\u00035\u0011Gn\\2l\u001b\u0006t\u0017mZ3sA\u0005\u0001R.\u00199PkR\u0004X\u000f\u001e+sC\u000e\\WM]\u000b\u0002\u001dB\u0011q\nU\u0007\u0002A%\u0011\u0011\u000b\t\u0002\u0011\u001b\u0006\u0004x*\u001e;qkR$&/Y2lKJ\f\u0011#\\1q\u001fV$\b/\u001e;Ue\u0006\u001c7.\u001a:!\u00039\u0019\b.\u001e4gY\u0016lU\r\u001e:jGN,\u0012!\u0016\t\u0003-bk\u0011a\u0016\u0006\u0003\u0001\u0002J!!W,\u00035MCWO\u001a4mKJ+\u0017\rZ'fiJL7m\u001d*fa>\u0014H/\u001a:\u0002\u001fMDWO\u001a4mK6+GO]5dg\u0002\na\u0001P5oSRtDCB/_?\u0002\f'\r\u0005\u00028\u0001!)Ag\u0003a\u0001m!)1h\u0003a\u0001{!)ai\u0003a\u0001\u0011\")Aj\u0003a\u0001\u001d\")1k\u0003a\u0001+\u0006Y1\u000f^1siRKW.\u001a(t!\tIS-\u0003\u0002gU\t!Aj\u001c8h\u0003qawnY1m'\",hM\u001a7f\u001b\u0016\u0014x-\u001a:CY>\u001c7.T4s\u0013\u0012,\u0012!\u001b\t\u0003o)L!a\u001b\u0010\u0003\u001d\tcwnY6NC:\fw-\u001a:JI\u0006iBn\\2bYNCWO\u001a4mK6+'oZ3s\u00052|7m['he&#\u0007%A\u0007dQVt7n]'fi\u0006l\u0015\r\u001d\t\u0005_R4\u00180D\u0001q\u0015\t\t(/A\u0004nkR\f'\r\\3\u000b\u0005MT\u0013AC2pY2,7\r^5p]&\u0011Q\u000f\u001d\u0002\b\u0011\u0006\u001c\b.T1q!\t9t/\u0003\u0002y=\t\u00192\u000b[;gM2,'\t\\8dW\u000eCWO\\6JIB\u0011!0`\u0007\u0002w*\u0011A\u0010J\u0001\u000ee>\f'/\u001b8hE&$X.\u00199\n\u0005y\\(!\u0004*pCJLgn\u001a\"ji6\f\u0007/A\u0010jgB+8\u000f['fe\u001e,Gm\u00155vM\u001adWM\u00117pG.\fE\r\u001a:fgN$B!a\u0001\u0002\nA\u0019\u0011&!\u0002\n\u0007\u0005\u001d!FA\u0004C_>dW-\u00198\t\r\u0005-\u0001\u00031\u0001j\u0003\u001d\tG\r\u001a:fgN\fa$[:SK6|G/\u001a)vg\"lUM]4fI\ncwnY6BI\u0012\u0014Xm]:\u0015\t\u0005\r\u0011\u0011\u0003\u0005\u0007\u0003\u0017\t\u0002\u0019A5\u0002;%\u001cHj\\2bYB+8\u000f['fe\u001e,GM\u00117pG.\fE\r\u001a:fgN$B!a\u0001\u0002\u0018!1\u00111\u0002\nA\u0002%\f1B]3n_Z,7\t[;oWR!\u0011QDA\u0012!\rI\u0013qD\u0005\u0004\u0003CQ#\u0001B+oSRDa!!\n\u0014\u0001\u00041\u0018a\u00022m_\u000e\\\u0017\nZ\u0001\tC\u0012$7\t[;oWR1\u0011QDA\u0016\u0003[Aa!!\n\u0015\u0001\u00041\bBBA\u0018)\u0001\u0007\u00110A\u0005dQVt7.T3uC\u0006\u0001r-\u001a;S_\u0006\u0014\u0018N\\4CSRl\u0015\r\u001d\u000b\u0005\u0003k\tY\u0004\u0005\u0003*\u0003oI\u0018bAA\u001dU\t1q\n\u001d;j_:Da!!\n\u0016\u0001\u00041\u0018AG4fiNCWO\u001a4mK\u000eCWO\\6DCJ$\u0017N\\1mSRLH\u0003BA!\u0003\u000f\u00022!KA\"\u0013\r\t)E\u000b\u0002\u0004\u0013:$\bBBA\u0013-\u0001\u0007a/A\u0013de\u0016\fG/Z\"ik:\\'\t\\8dW&sgm\\:Ge>lW*\u001a;b%\u0016\u001c\bo\u001c8tKRa\u0011QJA0\u0003G\n9'a\u001b\u0002pA)q.a\u0014\u0002T%\u0019\u0011\u0011\u000b9\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM\u001d\t\tS\u0005U\u0013\u0011\f3\u0002B%\u0019\u0011q\u000b\u0016\u0003\rQ+\b\u000f\\34!\r9\u00141L\u0005\u0004\u0003;r\"a\u0002\"m_\u000e\\\u0017\n\u001a\u0005\b\u0003C:\u0002\u0019AA!\u0003%\u0019\b.\u001e4gY\u0016LE\rC\u0004\u0002f]\u0001\r!!\u0011\u0002\u001dMDWO\u001a4mK6+'oZ3JI\"9\u0011\u0011N\fA\u0002\u0005\u0005\u0013\u0001\u0003:fIV\u001cW-\u00133\t\r\u00055t\u00031\u0001e\u0003%\u0011Gn\\2l'&TX\rC\u0004\u0002r]\u0001\r!a\u001d\u0002\u000f\tLG/\\1qgB!\u0011&!\u001ez\u0013\r\t9H\u000b\u0002\u0006\u0003J\u0014\u0018-_\u0001\u001dg\u0016tGMR3uG\"lUM]4fIN#\u0018\r^;t%\u0016\fX/Z:u)\u0011\ti\"! \t\u000f\u0005}\u0004\u00041\u0001\u0002\u0002\u0006\u0019!/Z9\u0011\t\u0005\r\u0015q\u0014\b\u0005\u0003\u000b\u000bYJ\u0004\u0003\u0002\b\u0006ee\u0002BAE\u0003/sA!a#\u0002\u0016:!\u0011QRAJ\u001b\t\tyIC\u0002\u0002\u0012\u001a\na\u0001\u0010:p_Rt\u0014\"A\u0013\n\u0005\r\"\u0013BA\u0011#\u0013\ty\u0002%C\u0002\u0002\u001ez\t1d\u00155vM\u001adWM\u00117pG.4U\r^2iKJLE/\u001a:bi>\u0014\u0018\u0002BAQ\u0003G\u0013ABR3uG\"\u0014V-];fgRT1!!(\u001f\u0003u1W\r^2i\u00032d\u0007+^:i\u001b\u0016\u0014x-\u001a3M_\u000e\fGN\u00117pG.\u001cH\u0003BA\u000f\u0003SCq!a+\u001a\u0001\u0004\ti+A\u000bqkNDW*\u001a:hK\u0012dunY1m\u00052|7m[:\u0011\u000b=\fy+!\u0017\n\u0007\u0005E\u0006OA\u0007MS:\\W\r\u001a%bg\"\u001cV\r^\u0001\u001bM\u0016$8\r\u001b)vg\"lUM]4fI2{7-\u00197CY>\u001c7n\u001d\u000b\u0007\u0003;\t9,!1\t\u000f\u0005e&\u00041\u0001\u0002<\u0006\u0019\u0002n\\:u\u0019>\u001c\u0017\r\u001c#je6\u000bg.Y4feB\u0019q'!0\n\u0007\u0005}fDA\nI_N$Hj\\2bY\u0012K'/T1oC\u001e,'\u000fC\u0004\u0002,j\u0001\r!!,\u00023\u0019,Go\u00195QkNDW*\u001a:hK\u0012dunY1m\u00052|7m\u001b\u000b\t\u0003;\t9-!3\u0002`\"9\u0011QE\u000eA\u0002\u0005e\u0003bBAf7\u0001\u0007\u0011QZ\u0001\nY>\u001c\u0017\r\u001c#jeN\u0004R!KA;\u0003\u001f\u0004B!!5\u0002Z:!\u00111[Ak!\r\tiIK\u0005\u0004\u0003/T\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\\\u0006u'AB*ue&twMC\u0002\u0002X*Ba!!9\u001c\u0001\u0004I\u0017A\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0001(S:LG/[1uK\u001a\u000bG\u000e\u001c2bG.4U\r^2i\r>\u0014\b+^:i\u001b\u0016\u0014x-\u001a3CY>\u001c7\u000e\u0006\u0004\u0002\u001e\u0005\u001d\u0018\u0011\u001e\u0005\b\u0003Ka\u0002\u0019AA-\u0011\u0019\tY\u0001\ba\u0001S\u0002"
)
public class PushBasedFetchHelper implements Logging {
   private final ShuffleBlockFetcherIterator org$apache$spark$storage$PushBasedFetchHelper$$iterator;
   private final BlockStoreClient shuffleClient;
   private final BlockManager blockManager;
   private final MapOutputTracker mapOutputTracker;
   private final ShuffleReadMetricsReporter shuffleMetrics;
   private final long startTimeNs;
   private final BlockManagerId localShuffleMergerBlockMgrId;
   private final HashMap chunksMetaMap;
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

   public ShuffleBlockFetcherIterator org$apache$spark$storage$PushBasedFetchHelper$$iterator() {
      return this.org$apache$spark$storage$PushBasedFetchHelper$$iterator;
   }

   private BlockStoreClient shuffleClient() {
      return this.shuffleClient;
   }

   private BlockManager blockManager() {
      return this.blockManager;
   }

   private MapOutputTracker mapOutputTracker() {
      return this.mapOutputTracker;
   }

   private ShuffleReadMetricsReporter shuffleMetrics() {
      return this.shuffleMetrics;
   }

   public BlockManagerId localShuffleMergerBlockMgrId() {
      return this.localShuffleMergerBlockMgrId;
   }

   public boolean isPushMergedShuffleBlockAddress(final BlockManagerId address) {
      boolean var3;
      label23: {
         String var10000 = BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER();
         String var2 = address.executorId();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public boolean isRemotePushMergedBlockAddress(final BlockManagerId address) {
      boolean var3;
      label25: {
         if (this.isPushMergedShuffleBlockAddress(address)) {
            String var10000 = address.host();
            String var2 = this.blockManager().blockManagerId().host();
            if (var10000 == null) {
               if (var2 != null) {
                  break label25;
               }
            } else if (!var10000.equals(var2)) {
               break label25;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public boolean isLocalPushMergedBlockAddress(final BlockManagerId address) {
      boolean var3;
      label25: {
         if (this.isPushMergedShuffleBlockAddress(address)) {
            String var10000 = address.host();
            String var2 = this.blockManager().blockManagerId().host();
            if (var10000 == null) {
               if (var2 == null) {
                  break label25;
               }
            } else if (var10000.equals(var2)) {
               break label25;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public void removeChunk(final ShuffleBlockChunkId blockId) {
      this.chunksMetaMap.remove(blockId);
   }

   public void addChunk(final ShuffleBlockChunkId blockId, final RoaringBitmap chunkMeta) {
      this.chunksMetaMap.update(blockId, chunkMeta);
   }

   public Option getRoaringBitMap(final ShuffleBlockChunkId blockId) {
      return this.chunksMetaMap.get(blockId);
   }

   public int getShuffleChunkCardinality(final ShuffleBlockChunkId blockId) {
      return BoxesRunTime.unboxToInt(this.getRoaringBitMap(blockId).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$getShuffleChunkCardinality$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   public ArrayBuffer createChunkBlockInfosFromMetaResponse(final int shuffleId, final int shuffleMergeId, final int reduceId, final long blockSize, final RoaringBitmap[] bitmaps) {
      long approxChunkSize = blockSize / (long)bitmaps.length;
      ArrayBuffer blocksToFetch = new ArrayBuffer();
      .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])bitmaps)).foreach((i) -> $anonfun$createChunkBlockInfosFromMetaResponse$1(this, shuffleId, shuffleMergeId, reduceId, bitmaps, approxChunkSize, blocksToFetch, BoxesRunTime.unboxToInt(i)));
      return blocksToFetch;
   }

   public void sendFetchMergedStatusRequest(final ShuffleBlockFetcherIterator.FetchRequest req) {
      scala.collection.immutable.Map sizeMap = ((IterableOnceOps)req.blocks().map((x0$1) -> {
         if (x0$1 != null) {
            BlockId blockId = x0$1.blockId();
            long size = x0$1.size();
            ShuffleMergedBlockId shuffleBlockId = (ShuffleMergedBlockId)blockId;
            return new Tuple2(new Tuple2.mcII.sp(shuffleBlockId.shuffleId(), shuffleBlockId.reduceId()), BoxesRunTime.boxToLong(size));
         } else {
            throw new MatchError(x0$1);
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
      BlockManagerId address = req.address();
      MergedBlocksMetaListener mergedBlocksMetaListener = new MergedBlocksMetaListener(req, sizeMap, address) {
         // $FF: synthetic field
         private final PushBasedFetchHelper $outer;
         private final ShuffleBlockFetcherIterator.FetchRequest req$1;
         private final scala.collection.immutable.Map sizeMap$1;
         private final BlockManagerId address$1;

         public void onSuccess(final int shuffleId, final int shuffleMergeId, final int reduceId, final MergedBlockMeta meta) {
            this.$outer.logDebug((Function0)(() -> "Received the meta of push-merged block for (" + shuffleId + ", " + shuffleMergeId + ", " + reduceId + ") from " + this.req$1.address().host() + ":" + this.req$1.address().port()));

            try {
               this.$outer.org$apache$spark$storage$PushBasedFetchHelper$$iterator().addToResultsQueue(new ShuffleBlockFetcherIterator.PushMergedRemoteMetaFetchResult(shuffleId, shuffleMergeId, reduceId, BoxesRunTime.unboxToLong(this.sizeMap$1.apply(new Tuple2.mcII.sp(shuffleId, reduceId))), meta.readChunkBitmaps(), this.address$1));
            } catch (Exception var6) {
               this.$outer.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to parse the meta of push-merged block for ("})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId)), new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleMergeId))})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, BoxesRunTime.boxToInteger(reduceId)), new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.req$1.address().host())})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.req$1.address().port()))}))))), var6);
               this.$outer.org$apache$spark$storage$PushBasedFetchHelper$$iterator().addToResultsQueue(new ShuffleBlockFetcherIterator.PushMergedRemoteMetaFailedFetchResult(shuffleId, shuffleMergeId, reduceId, this.address$1));
            }

         }

         public void onFailure(final int shuffleId, final int shuffleMergeId, final int reduceId, final Throwable exception) {
            this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to get the meta of push-merged block for "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ", ", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId)), new MDC(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, BoxesRunTime.boxToInteger(reduceId))})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.req$1.address().host()), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.req$1.address().port()))}))))), exception);
            this.$outer.org$apache$spark$storage$PushBasedFetchHelper$$iterator().addToResultsQueue(new ShuffleBlockFetcherIterator.PushMergedRemoteMetaFailedFetchResult(shuffleId, shuffleMergeId, reduceId, this.address$1));
         }

         public {
            if (PushBasedFetchHelper.this == null) {
               throw null;
            } else {
               this.$outer = PushBasedFetchHelper.this;
               this.req$1 = req$1;
               this.sizeMap$1 = sizeMap$1;
               this.address$1 = address$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      req.blocks().foreach((block) -> {
         $anonfun$sendFetchMergedStatusRequest$2(this, address, mergedBlocksMetaListener, block);
         return BoxedUnit.UNIT;
      });
   }

   public void fetchAllPushMergedLocalBlocks(final LinkedHashSet pushMergedLocalBlocks) {
      if (pushMergedLocalBlocks.nonEmpty()) {
         this.blockManager().hostLocalDirManager().foreach((x$2) -> {
            $anonfun$fetchAllPushMergedLocalBlocks$1(this, pushMergedLocalBlocks, x$2);
            return BoxedUnit.UNIT;
         });
      }
   }

   private void fetchPushMergedLocalBlocks(final HostLocalDirManager hostLocalDirManager, final LinkedHashSet pushMergedLocalBlocks) {
      Option cachedPushedMergedDirs = hostLocalDirManager.getCachedHostLocalDirsFor(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER());
      if (cachedPushedMergedDirs.isDefined()) {
         this.logDebug((Function0)(() -> {
            ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(cachedPushedMergedDirs.get());
            return "Fetch the push-merged-local blocks with cached merged dirs: " + var10000.mkString(", ");
         }));
         pushMergedLocalBlocks.foreach((blockId) -> {
            $anonfun$fetchPushMergedLocalBlocks$2(this, cachedPushedMergedDirs, blockId);
            return BoxedUnit.UNIT;
         });
      } else {
         this.logDebug((Function0)(() -> "Asynchronous fetch the push-merged-local blocks without cached merged dirs from the external shuffle service"));
         hostLocalDirManager.getHostLocalDirs(this.blockManager().blockManagerId().host(), this.blockManager().externalShuffleServicePort(), (String[])((Object[])(new String[]{BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER()})), (x0$1) -> {
            $anonfun$fetchPushMergedLocalBlocks$4(this, pushMergedLocalBlocks, x0$1);
            return BoxedUnit.UNIT;
         });
      }
   }

   private void fetchPushMergedLocalBlock(final BlockId blockId, final String[] localDirs, final BlockManagerId blockManagerId) {
      try {
         ShuffleMergedBlockId shuffleBlockId = (ShuffleMergedBlockId)blockId;
         MergedBlockMeta chunksMeta = this.blockManager().getLocalMergedBlockMeta(shuffleBlockId, localDirs);
         this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().addToResultsQueue(new ShuffleBlockFetcherIterator.PushMergedLocalMetaFetchResult(shuffleBlockId.shuffleId(), shuffleBlockId.shuffleMergeId(), shuffleBlockId.reduceId(), chunksMeta.readChunkBitmaps(), localDirs));
      } catch (Exception var7) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error occurred while fetching push-merged-local meta, "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"prepare to fetch the original blocks"})))).log(scala.collection.immutable.Nil..MODULE$))), var7);
         this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().addToResultsQueue(new ShuffleBlockFetcherIterator.FallbackOnPushMergedFailureResult(blockId, blockManagerId, 0L, false));
      }

   }

   public void initiateFallbackFetchForPushMergedBlock(final BlockId blockId, final BlockManagerId address) {
      scala.Predef..MODULE$.assert(blockId instanceof ShuffleMergedBlockId || blockId instanceof ShuffleBlockChunkId);
      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Falling back to fetch the original blocks for push-merged block "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))))));
      this.shuffleMetrics().incMergedFetchFallbackCount(1L);
      Iterator var10000;
      if (blockId instanceof ShuffleMergedBlockId var6) {
         this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().decreaseNumBlocksToFetch(1);
         var10000 = this.mapOutputTracker().getMapSizesForMergeResult(var6.shuffleId(), var6.reduceId());
      } else {
         ShuffleBlockChunkId shuffleChunkId = (ShuffleBlockChunkId)blockId;
         RoaringBitmap chunkBitmap = (RoaringBitmap)this.chunksMetaMap.remove(shuffleChunkId).get();
         int blocksProcessed = 1;
         if (this.isRemotePushMergedBlockAddress(address)) {
            HashSet pendingShuffleChunks = this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().removePendingChunks(shuffleChunkId, address);
            pendingShuffleChunks.foreach((pendingBlockId) -> {
               $anonfun$initiateFallbackFetchForPushMergedBlock$2(this, chunkBitmap, pendingBlockId);
               return BoxedUnit.UNIT;
            });
            blocksProcessed += pendingShuffleChunks.size();
         }

         this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().decreaseNumBlocksToFetch(blocksProcessed);
         var10000 = this.mapOutputTracker().getMapSizesForMergeResult(shuffleChunkId.shuffleId(), shuffleChunkId.reduceId(), chunkBitmap);
      }

      Iterator fallbackBlocksByAddr = var10000;
      this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().fallbackFetch(fallbackBlocksByAddr);
   }

   // $FF: synthetic method
   public static final int $anonfun$getShuffleChunkCardinality$1(final RoaringBitmap x$1) {
      return x$1.getCardinality();
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$createChunkBlockInfosFromMetaResponse$1(final PushBasedFetchHelper $this, final int shuffleId$1, final int shuffleMergeId$1, final int reduceId$1, final RoaringBitmap[] bitmaps$1, final long approxChunkSize$1, final ArrayBuffer blocksToFetch$1, final int i) {
      ShuffleBlockChunkId blockChunkId = new ShuffleBlockChunkId(shuffleId$1, shuffleMergeId$1, reduceId$1, i);
      $this.chunksMetaMap.put(blockChunkId, bitmaps$1[i]);
      $this.logDebug((Function0)(() -> "adding block chunk " + blockChunkId + " of size " + approxChunkSize$1));
      return (ArrayBuffer)blocksToFetch$1.$plus$eq(new Tuple3(blockChunkId, BoxesRunTime.boxToLong(approxChunkSize$1), BoxesRunTime.boxToInteger(MapOutputTracker$.MODULE$.SHUFFLE_PUSH_MAP_ID())));
   }

   // $FF: synthetic method
   public static final void $anonfun$sendFetchMergedStatusRequest$2(final PushBasedFetchHelper $this, final BlockManagerId address$1, final MergedBlocksMetaListener mergedBlocksMetaListener$1, final ShuffleBlockFetcherIterator.FetchBlockInfo block) {
      ShuffleMergedBlockId shuffleBlockId = (ShuffleMergedBlockId)block.blockId();
      $this.shuffleClient().getMergedBlockMeta(address$1.host(), address$1.port(), shuffleBlockId.shuffleId(), shuffleBlockId.shuffleMergeId(), shuffleBlockId.reduceId(), mergedBlocksMetaListener$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchAllPushMergedLocalBlocks$1(final PushBasedFetchHelper $this, final LinkedHashSet pushMergedLocalBlocks$1, final HostLocalDirManager x$2) {
      $this.fetchPushMergedLocalBlocks(x$2, pushMergedLocalBlocks$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchPushMergedLocalBlocks$2(final PushBasedFetchHelper $this, final Option cachedPushedMergedDirs$1, final BlockId blockId) {
      $this.fetchPushMergedLocalBlock(blockId, (String[])cachedPushedMergedDirs$1.get(), $this.localShuffleMergerBlockMgrId());
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchPushMergedLocalBlocks$6(final PushBasedFetchHelper $this, final scala.collection.immutable.Map dirs$1, final BlockId blockId) {
      $this.logDebug((Function0)(() -> {
         Iterable var10000 = scala.Option..MODULE$.option2Iterable(dirs$1.get(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER()));
         return "Successfully fetched local dirs: " + var10000.mkString(", ");
      }));
      $this.fetchPushMergedLocalBlock(blockId, (String[])dirs$1.apply(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER()), $this.localShuffleMergerBlockMgrId());
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchPushMergedLocalBlocks$9(final PushBasedFetchHelper $this, final BlockId blockId) {
      $this.org$apache$spark$storage$PushBasedFetchHelper$$iterator().addToResultsQueue(new ShuffleBlockFetcherIterator.FallbackOnPushMergedFailureResult(blockId, $this.localShuffleMergerBlockMgrId(), 0L, false));
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchPushMergedLocalBlocks$4(final PushBasedFetchHelper $this, final LinkedHashSet pushMergedLocalBlocks$2, final Try x0$1) {
      if (x0$1 instanceof Success var5) {
         scala.collection.immutable.Map dirs = (scala.collection.immutable.Map)var5.value();
         $this.logDebug((Function0)(() -> {
            long var10000 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - $this.startTimeNs);
            return "Fetched merged dirs in " + var10000 + " ms";
         }));
         pushMergedLocalBlocks$2.foreach((blockId) -> {
            $anonfun$fetchPushMergedLocalBlocks$6($this, dirs, blockId);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (x0$1 instanceof Failure var7) {
         Throwable throwable = var7.exception();
         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error while fetching the merged dirs for push-merged-local "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"blocks: ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_IDS..MODULE$, pushMergedLocalBlocks$2.mkString(", "))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fetch the original blocks instead"})))).log(scala.collection.immutable.Nil..MODULE$))), throwable);
         pushMergedLocalBlocks$2.foreach((blockId) -> {
            $anonfun$fetchPushMergedLocalBlocks$9($this, blockId);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$initiateFallbackFetchForPushMergedBlock$2(final PushBasedFetchHelper $this, final RoaringBitmap chunkBitmap$1, final ShuffleBlockChunkId pendingBlockId) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Falling back immediately for shuffle chunk ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, pendingBlockId)})))));
      $this.shuffleMetrics().incMergedFetchFallbackCount(1L);
      RoaringBitmap bitmapOfPendingChunk = (RoaringBitmap)$this.chunksMetaMap.remove(pendingBlockId).get();
      chunkBitmap$1.or(bitmapOfPendingChunk);
   }

   public PushBasedFetchHelper(final ShuffleBlockFetcherIterator iterator, final BlockStoreClient shuffleClient, final BlockManager blockManager, final MapOutputTracker mapOutputTracker, final ShuffleReadMetricsReporter shuffleMetrics) {
      this.org$apache$spark$storage$PushBasedFetchHelper$$iterator = iterator;
      this.shuffleClient = shuffleClient;
      this.blockManager = blockManager;
      this.mapOutputTracker = mapOutputTracker;
      this.shuffleMetrics = shuffleMetrics;
      Logging.$init$(this);
      this.startTimeNs = System.nanoTime();
      this.localShuffleMergerBlockMgrId = BlockManagerId$.MODULE$.apply(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER(), blockManager.blockManagerId().host(), blockManager.blockManagerId().port(), blockManager.blockManagerId().topologyInfo());
      this.chunksMetaMap = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
