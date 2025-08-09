package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MergeStatus;
import org.apache.spark.shuffle.MetadataFetchFailedException;
import org.apache.spark.util.KeyLock;
import org.apache.spark.util.Utils$;
import org.roaringbitmap.RoaringBitmap;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me!\u0002\f\u0018\u0001]i\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000f-\u0002!\u0019!C\u0001Y!1A\t\u0001Q\u0001\n5Bq!\u0012\u0001C\u0002\u0013\u0005a\t\u0003\u0004M\u0001\u0001\u0006Ia\u0012\u0005\t\u001b\u0002A)\u0019!C\u0005\u001d\"9!\u000b\u0001b\u0001\n\u0003\u0019\u0006BB6\u0001A\u0003%A\u000bC\u0004m\u0001\t\u0007I\u0011B7\t\rQ\u0004\u0001\u0015!\u0003o\u0011\u0015)\b\u0001\"\u0011w\u0011\u001d\t9\u0003\u0001C!\u0003SAq!a\u000f\u0001\t\u0013\ti\u0004C\u0004\u0002N\u0001!\t%a\u0014\t\u000f\u00055\u0003\u0001\"\u0011\u0002X!9\u0011Q\u000e\u0001\u0005B\u0005=\u0004bBA:\u0001\u0011%\u0011Q\u000f\u0005\b\u0003s\u0002A\u0011BA>\u0011\u001d\t9\t\u0001C\u0001\u0003\u0013Cq!a%\u0001\t\u0003\t)J\u0001\fNCB|U\u000f\u001e9viR\u0013\u0018mY6fe^{'o[3s\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<7C\u0001\u0001\u001f!\ty\u0002%D\u0001\u0018\u0013\t\tsC\u0001\tNCB|U\u000f\u001e9viR\u0013\u0018mY6fe\u0006!1m\u001c8g\u0007\u0001\u0001\"aH\u0013\n\u0005\u0019:\"!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011\u0011F\u000b\t\u0003?\u0001AQA\t\u0002A\u0002\u0011\n1\"\\1q'R\fG/^:fgV\tQ\u0006\u0005\u0003/k]ZT\"A\u0018\u000b\u0005A\n\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003eM\n!bY8mY\u0016\u001cG/[8o\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c0\u0005\ri\u0015\r\u001d\t\u0003qej\u0011aM\u0005\u0003uM\u00121!\u00138u!\rADHP\u0005\u0003{M\u0012Q!\u0011:sCf\u0004\"a\u0010\"\u000e\u0003\u0001S!!Q\f\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018BA\"A\u0005%i\u0015\r]*uCR,8/\u0001\u0007nCB\u001cF/\u0019;vg\u0016\u001c\b%A\u0007nKJ<Wm\u0015;biV\u001cXm]\u000b\u0002\u000fB!a&N\u001cI!\rAD(\u0013\t\u0003\u007f)K!a\u0013!\u0003\u00175+'oZ3Ti\u0006$Xo]\u0001\u000f[\u0016\u0014x-Z*uCR,8/Z:!\u0003A1W\r^2i\u001b\u0016\u0014x-\u001a*fgVdG/F\u0001P!\tA\u0004+\u0003\u0002Rg\t9!i\\8mK\u0006t\u0017AG:ik\u001a4G.\u001a)vg\"lUM]4fe2{7-\u0019;j_:\u001cX#\u0001+\u0011\tUCv'W\u0007\u0002-*\u0011q+M\u0001\u000bG>t7-\u001e:sK:$\u0018B\u0001\u001cW!\rQ&-\u001a\b\u00037\u0002t!\u0001X0\u000e\u0003uS!AX\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0014BA14\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u00193\u0003\u0007M+\u0017O\u0003\u0002bgA\u0011a-[\u0007\u0002O*\u0011\u0001nF\u0001\bgR|'/Y4f\u0013\tQwM\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\u00027MDWO\u001a4mKB+8\u000f['fe\u001e,'\u000fT8dCRLwN\\:!\u000311W\r^2iS:<Gj\\2l+\u0005q\u0007cA8so5\t\u0001O\u0003\u0002r/\u0005!Q\u000f^5m\u0013\t\u0019\bOA\u0004LKfdunY6\u0002\u001b\u0019,Go\u00195j]\u001edunY6!\u0003]9W\r^'baNK'0Z:Cs\u0016CXmY;u_JLE\rF\u0006x\u0003'\t9\"a\u0007\u0002 \u0005\r\u0002c\u0001.yu&\u0011\u0011\u0010\u001a\u0002\t\u0013R,'/\u0019;peB!\u0001h_3~\u0013\ta8G\u0001\u0004UkBdWM\r\t\u0005}~\f\t!D\u00012\u0013\t\u0019\u0017\u0007\u0005\u00059\u0003\u0007\t9!!\u00048\u0013\r\t)a\r\u0002\u0007)V\u0004H.Z\u001a\u0011\u0007\u0019\fI!C\u0002\u0002\f\u001d\u0014qA\u00117pG.LE\rE\u00029\u0003\u001fI1!!\u00054\u0005\u0011auN\\4\t\r\u0005UA\u00021\u00018\u0003%\u0019\b.\u001e4gY\u0016LE\r\u0003\u0004\u0002\u001a1\u0001\raN\u0001\u000egR\f'\u000f^'ba&sG-\u001a=\t\r\u0005uA\u00021\u00018\u0003-)g\u000eZ'ba&sG-\u001a=\t\r\u0005\u0005B\u00021\u00018\u00039\u0019H/\u0019:u!\u0006\u0014H/\u001b;j_:Da!!\n\r\u0001\u00049\u0014\u0001D3oIB\u000b'\u000f^5uS>t\u0017aJ4fiB+8\u000f\u001b\"bg\u0016$7\u000b[;gM2,W*\u00199TSj,7OQ=Fq\u0016\u001cW\u000f^8s\u0013\u0012$B\"a\u000b\u00022\u0005M\u0012QGA\u001c\u0003s\u00012aHA\u0017\u0013\r\tyc\u0006\u0002\u0015\u001b\u0006\u00048+\u001b>fg\nKX\t_3dkR|'/\u00133\t\r\u0005UQ\u00021\u00018\u0011\u0019\tI\"\u0004a\u0001o!1\u0011QD\u0007A\u0002]Ba!!\t\u000e\u0001\u00049\u0004BBA\u0013\u001b\u0001\u0007q'A\u000ehKRl\u0015\r]*ju\u0016\u001c()_#yK\u000e,Ho\u001c:JI&k\u0007\u000f\u001c\u000b\u000f\u0003W\ty$!\u0011\u0002D\u0005\u0015\u0013qIA%\u0011\u0019\t)B\u0004a\u0001o!1\u0011\u0011\u0004\bA\u0002]Ba!!\b\u000f\u0001\u00049\u0004BBA\u0011\u001d\u0001\u0007q\u0007\u0003\u0004\u0002&9\u0001\ra\u000e\u0005\u0007\u0003\u0017r\u0001\u0019A(\u0002\u001dU\u001cX-T3sO\u0016\u0014Vm];mi\u0006Ir-\u001a;NCB\u001c\u0016N_3t\r>\u0014X*\u001a:hKJ+7/\u001e7u)\u00159\u0018\u0011KA*\u0011\u0019\t)b\u0004a\u0001o!1\u0011QK\bA\u0002]\n1\u0002]1si&$\u0018n\u001c8JIR9q/!\u0017\u0002\\\u0005u\u0003BBA\u000b!\u0001\u0007q\u0007\u0003\u0004\u0002VA\u0001\ra\u000e\u0005\b\u0003?\u0002\u0002\u0019AA1\u00031\u0019\u0007.\u001e8l)J\f7m[3s!\u0011\t\u0019'!\u001b\u000e\u0005\u0005\u0015$bAA47\u0005i!o\\1sS:<'-\u001b;nCBLA!a\u001b\u0002f\ti!k\\1sS:<')\u001b;nCB\fQdZ3u'\",hM\u001a7f!V\u001c\b.T3sO\u0016\u0014Hj\\2bi&|gn\u001d\u000b\u00043\u0006E\u0004BBA\u000b#\u0001\u0007q'\u0001\nhKRlUM]4fe2{7-\u0019;j_:\u001cHcA-\u0002x!1\u0011Q\u0003\nA\u0002]\n1bZ3u'R\fG/^:fgRA\u0011QPA@\u0003\u0003\u000b\u0019\t\u0005\u00039wnB\u0005BBA\u000b'\u0001\u0007q\u0007C\u0003#'\u0001\u0007A\u0005\u0003\u0004\u0002\u0006N\u0001\raT\u0001\u0014G\u0006tg)\u001a;dQ6+'oZ3SKN,H\u000e^\u0001\u0012k:\u0014XmZ5ti\u0016\u00148\u000b[;gM2,G\u0003BAF\u0003#\u00032\u0001OAG\u0013\r\tyi\r\u0002\u0005+:LG\u000f\u0003\u0004\u0002\u0016Q\u0001\raN\u0001\fkB$\u0017\r^3Fa>\u001c\u0007\u000e\u0006\u0003\u0002\f\u0006]\u0005bBAM+\u0001\u0007\u0011QB\u0001\t]\u0016<X\t]8dQ\u0002"
)
public class MapOutputTrackerWorker extends MapOutputTracker {
   private boolean fetchMergeResult;
   private final SparkConf conf;
   private final Map mapStatuses;
   private final Map mergeStatuses;
   private final scala.collection.concurrent.Map shufflePushMergerLocations;
   private final KeyLock fetchingLock;
   private volatile boolean bitmap$0;

   public Map mapStatuses() {
      return this.mapStatuses;
   }

   public Map mergeStatuses() {
      return this.mergeStatuses;
   }

   private boolean fetchMergeResult$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.fetchMergeResult = Utils$.MODULE$.isPushBasedShuffleEnabled(this.conf, false, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fetchMergeResult;
   }

   private boolean fetchMergeResult() {
      return !this.bitmap$0 ? this.fetchMergeResult$lzycompute() : this.fetchMergeResult;
   }

   public scala.collection.concurrent.Map shufflePushMergerLocations() {
      return this.shufflePushMergerLocations;
   }

   private KeyLock fetchingLock() {
      return this.fetchingLock;
   }

   public Iterator getMapSizesByExecutorId(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition) {
      MapSizesByExecutorId mapSizesByExecutorId = this.getMapSizesByExecutorIdImpl(shuffleId, startMapIndex, endMapIndex, startPartition, endPartition, false);
      .MODULE$.assert(mapSizesByExecutorId.enableBatchFetch());
      return mapSizesByExecutorId.iter();
   }

   public MapSizesByExecutorId getPushBasedShuffleMapSizesByExecutorId(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition) {
      return this.getMapSizesByExecutorIdImpl(shuffleId, startMapIndex, endMapIndex, startPartition, endPartition, true);
   }

   private MapSizesByExecutorId getMapSizesByExecutorIdImpl(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition, final boolean useMergeResult) {
      this.logDebug(() -> "Fetching outputs for shuffle " + shuffleId);
      Tuple2 var9 = this.getStatuses(shuffleId, this.conf, useMergeResult ? this.fetchMergeResult() : false);
      if (var9 != null) {
         MapStatus[] mapOutputStatuses = (MapStatus[])var9._1();
         MergeStatus[] mergedOutputStatuses = (MergeStatus[])var9._2();
         Tuple2 var8 = new Tuple2(mapOutputStatuses, mergedOutputStatuses);
         MapStatus[] mapOutputStatuses = (MapStatus[])var8._1();
         MergeStatus[] mergedOutputStatuses = (MergeStatus[])var8._2();

         try {
            int actualEndMapIndex = endMapIndex == Integer.MAX_VALUE ? mapOutputStatuses.length : endMapIndex;
            this.logDebug(() -> "Convert map statuses for shuffle " + shuffleId + ", mappers " + startMapIndex + "-" + actualEndMapIndex + ", partitions " + startPartition + "-" + endPartition);
            return MapOutputTracker$.MODULE$.convertMapStatuses(shuffleId, startPartition, endPartition, mapOutputStatuses, startMapIndex, actualEndMapIndex, scala.Option..MODULE$.apply(mergedOutputStatuses));
         } catch (MetadataFetchFailedException var16) {
            this.mapStatuses().clear();
            this.mergeStatuses().clear();
            throw var16;
         }
      } else {
         throw new MatchError(var9);
      }
   }

   public Iterator getMapSizesForMergeResult(final int shuffleId, final int partitionId) {
      this.logDebug(() -> "Fetching backup outputs for shuffle " + shuffleId + ", partition " + partitionId);
      Tuple2 var5 = this.getStatuses(shuffleId, this.conf, this.fetchMergeResult());
      if (var5 != null) {
         MapStatus[] mapOutputStatuses = (MapStatus[])var5._1();
         MergeStatus[] mergeResultStatuses = (MergeStatus[])var5._2();
         Tuple2 var4 = new Tuple2(mapOutputStatuses, mergeResultStatuses);
         MapStatus[] mapOutputStatuses = (MapStatus[])var4._1();
         MergeStatus[] mergeResultStatuses = (MergeStatus[])var4._2();

         try {
            MergeStatus mergeStatus = mergeResultStatuses[partitionId];
            MapOutputTracker$.MODULE$.validateStatus(mergeStatus, shuffleId, partitionId);
            return MapOutputTracker$.MODULE$.getMapStatusesForMergeStatus(shuffleId, partitionId, mapOutputStatuses, mergeStatus.tracker());
         } catch (MetadataFetchFailedException var12) {
            this.mapStatuses().clear();
            this.mergeStatuses().clear();
            throw var12;
         }
      } else {
         throw new MatchError(var5);
      }
   }

   public Iterator getMapSizesForMergeResult(final int shuffleId, final int partitionId, final RoaringBitmap chunkTracker) {
      this.logDebug(() -> "Fetching backup outputs for shuffle " + shuffleId + ", partition " + partitionId);
      Tuple2 var6 = this.getStatuses(shuffleId, this.conf, this.fetchMergeResult());
      if (var6 != null) {
         MapStatus[] mapOutputStatuses = (MapStatus[])var6._1();
         MapStatus[] mapOutputStatuses = mapOutputStatuses;

         try {
            return MapOutputTracker$.MODULE$.getMapStatusesForMergeStatus(shuffleId, partitionId, mapOutputStatuses, chunkTracker);
         } catch (MetadataFetchFailedException var9) {
            this.mapStatuses().clear();
            this.mergeStatuses().clear();
            throw var9;
         }
      } else {
         throw new MatchError(var6);
      }
   }

   public Seq getShufflePushMergerLocations(final int shuffleId) {
      return (Seq)this.shufflePushMergerLocations().getOrElse(BoxesRunTime.boxToInteger(shuffleId), () -> this.getMergerLocations(shuffleId));
   }

   private Seq getMergerLocations(final int shuffleId) {
      return (Seq)this.fetchingLock().withLock(BoxesRunTime.boxToInteger(shuffleId), () -> {
         Seq fetchedMergers = (Seq)this.shufflePushMergerLocations().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
         if (fetchedMergers == null) {
            fetchedMergers = (Seq)this.askTracker(new GetShufflePushMergerLocations(shuffleId), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
            if (fetchedMergers.nonEmpty()) {
               this.shufflePushMergerLocations().update(BoxesRunTime.boxToInteger(shuffleId), fetchedMergers);
            } else {
               fetchedMergers = (Seq)scala.package..MODULE$.Seq().empty();
            }
         }

         return fetchedMergers;
      });
   }

   private Tuple2 getStatuses(final int shuffleId, final SparkConf conf, final boolean canFetchMergeResult) {
      if (canFetchMergeResult) {
         MapStatus[] mapOutputStatuses = (MapStatus[])this.mapStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
         MergeStatus[] mergeOutputStatuses = (MergeStatus[])this.mergeStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
         if (mapOutputStatuses != null && mergeOutputStatuses != null) {
            return new Tuple2(mapOutputStatuses, mergeOutputStatuses);
         } else {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Don't have map/merge outputs for"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" shuffle ", ", fetching them"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))}))))));
            long startTimeNs = System.nanoTime();
            return (Tuple2)this.fetchingLock().withLock(BoxesRunTime.boxToInteger(shuffleId), () -> {
               MapStatus[] fetchedMapStatuses = (MapStatus[])this.mapStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
               MergeStatus[] fetchedMergeStatuses = (MergeStatus[])this.mergeStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
               if (fetchedMapStatuses != null && fetchedMergeStatuses != null) {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Doing the fetch; tracker endpoint = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ENDPOINT_REF..MODULE$, this.trackerEndpoint())}))))));
                  Tuple2 fetchedBytes = (Tuple2)this.askTracker(new GetMapAndMergeResultStatuses(shuffleId), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));

                  try {
                     fetchedMapStatuses = (MapStatus[])MapOutputTracker$.MODULE$.deserializeOutputStatuses((byte[])fetchedBytes._1(), conf);
                     fetchedMergeStatuses = (MergeStatus[])MapOutputTracker$.MODULE$.deserializeOutputStatuses((byte[])fetchedBytes._2(), conf);
                  } catch (SparkException var9) {
                     throw new MetadataFetchFailedException(shuffleId, -1, "Unable to deserialize broadcasted map/merge statuses for shuffle " + shuffleId + ": " + var9.getCause());
                  }

                  this.logInfo(() -> "Got the map/merge output locations");
                  this.mapStatuses().put(BoxesRunTime.boxToInteger(shuffleId), fetchedMapStatuses);
                  this.mergeStatuses().put(BoxesRunTime.boxToInteger(shuffleId), fetchedMergeStatuses);
               }

               this.logDebug(() -> "Fetching map/merge output statuses for shuffle " + shuffleId + " took " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs) + " ms");
               return new Tuple2(fetchedMapStatuses, fetchedMergeStatuses);
            });
         }
      } else {
         MapStatus[] statuses = (MapStatus[])this.mapStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
         if (statuses == null) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Don't have map outputs for shuffle ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" fetching them"})))).log(scala.collection.immutable.Nil..MODULE$))));
            long startTimeNs = System.nanoTime();
            return (Tuple2)this.fetchingLock().withLock(BoxesRunTime.boxToInteger(shuffleId), () -> {
               MapStatus[] fetchedStatuses = (MapStatus[])this.mapStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
               if (fetchedStatuses == null) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Doing the fetch; tracker endpoint ="})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ENDPOINT_REF..MODULE$, this.trackerEndpoint())}))))));
                  byte[] fetchedBytes = (byte[])this.askTracker(new GetMapOutputStatuses(shuffleId), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));

                  try {
                     fetchedStatuses = (MapStatus[])MapOutputTracker$.MODULE$.deserializeOutputStatuses(fetchedBytes, conf);
                  } catch (SparkException var8) {
                     throw new MetadataFetchFailedException(shuffleId, -1, "Unable to deserialize broadcasted map statuses for shuffle " + shuffleId + ": " + var8.getCause());
                  }

                  this.logInfo(() -> "Got the map output locations");
                  this.mapStatuses().put(BoxesRunTime.boxToInteger(shuffleId), fetchedStatuses);
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               this.logDebug(() -> "Fetching map output statuses for shuffle " + shuffleId + " took " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs) + " ms");
               return new Tuple2(fetchedStatuses, (Object)null);
            });
         } else {
            return new Tuple2(statuses, (Object)null);
         }
      }
   }

   public void unregisterShuffle(final int shuffleId) {
      this.mapStatuses().remove(BoxesRunTime.boxToInteger(shuffleId));
      this.mergeStatuses().remove(BoxesRunTime.boxToInteger(shuffleId));
      this.shufflePushMergerLocations().remove(BoxesRunTime.boxToInteger(shuffleId));
   }

   public void updateEpoch(final long newEpoch) {
      synchronized(this.epochLock()){}

      try {
         if (newEpoch > this.epoch()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updating epoch to ", " and clearing cache"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EPOCH..MODULE$, BoxesRunTime.boxToLong(newEpoch))})))));
            this.epoch_$eq(newEpoch);
            this.mapStatuses().clear();
            this.mergeStatuses().clear();
            this.shufflePushMergerLocations().clear();
         }
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public MapOutputTrackerWorker(final SparkConf conf) {
      super(conf);
      this.conf = conf;
      this.mapStatuses = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      this.mergeStatuses = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      this.shufflePushMergerLocations = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      this.fetchingLock = new KeyLock();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
