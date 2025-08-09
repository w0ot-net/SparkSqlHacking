package org.apache.spark.status.api.v1.streaming;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.spark.status.api.v1.ApiRequestContext;
import org.apache.spark.status.api.v1.BaseAppResource;
import org.apache.spark.status.api.v1.NotFoundException;
import org.apache.spark.status.api.v1.UIRoot;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.ui.BatchUIData;
import org.apache.spark.streaming.ui.OutputOpIdAndSparkJobId;
import org.apache.spark.streaming.ui.OutputOperationUIData;
import org.apache.spark.streaming.ui.StreamingJobProgressListener;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Iterable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Produces({"application/json"})
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f!B\u0006\r\u00019Q\u0002\"B\u0013\u0001\t\u00039\u0003\"B\u0015\u0001\t\u0003Q\u0003\"\u0002!\u0001\t\u0003\t\u0005\"B+\u0001\t\u00031\u0006\"B3\u0001\t\u00031\u0007bBA\u0002\u0001\u0011\u0005\u0011Q\u0001\u0005\b\u0003;\u0001A\u0011AA\u0010\u0011\u001d\t)\u0004\u0001C\u0001\u0003oAq!a\u001e\u0001\t\u0013\tI\bC\u0004\u0002\u000e\u0002!I!a$\u00031\u0005\u0003\u0018n\u0015;sK\u0006l\u0017N\\4S_>$(+Z:pkJ\u001cWM\u0003\u0002\u000e\u001d\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u001fA\t!A^\u0019\u000b\u0005E\u0011\u0012aA1qS*\u00111\u0003F\u0001\u0007gR\fG/^:\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c2\u0001A\u000e\"!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0011!eI\u0007\u0002\u0019%\u0011A\u0005\u0004\u0002\u0019\u0005\u0006\u001cXm\u0015;sK\u0006l\u0017N\\4BaB\u0014Vm]8ve\u000e,\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003!\u0002\"A\t\u0001\u0002'M$(/Z1nS:<7\u000b^1uSN$\u0018nY:\u0015\u0003-\u0002\"A\t\u0017\n\u00055b!aE*ue\u0016\fW.\u001b8h'R\fG/[:uS\u000e\u001c\bF\u0001\u00020!\t\u0001t'D\u00012\u0015\t\u00114'\u0001\u0002sg*\u0011A'N\u0001\u0003oNT\u0011AN\u0001\bU\u0006\\\u0017M\u001d;b\u0013\tA\u0014GA\u0002H\u000bRCCA\u0001\u001e>}A\u0011\u0001gO\u0005\u0003yE\u0012A\u0001U1uQ\u0006)a/\u00197vK\u0006\nq(\u0001\u0006ti\u0006$\u0018n\u001d;jGN\fQB]3dK&4XM]:MSN$H#\u0001\"\u0011\u0007\r[eJ\u0004\u0002E\u0013:\u0011Q\tS\u0007\u0002\r*\u0011qIJ\u0001\u0007yI|w\u000e\u001e \n\u0003yI!AS\u000f\u0002\u000fA\f7m[1hK&\u0011A*\u0014\u0002\u0004'\u0016\f(B\u0001&\u001e!\t\u0011s*\u0003\u0002Q\u0019\ta!+Z2fSZ,'/\u00138g_\"\u00121a\f\u0015\u0005\u0007ij4+I\u0001U\u0003%\u0011XmY3jm\u0016\u00148/A\u0006p]\u0016\u0014VmY3jm\u0016\u0014HC\u0001(X\u0011\u0015AF\u00011\u0001Z\u0003!\u0019HO]3b[&#\u0007C\u0001\u000f[\u0013\tYVDA\u0002J]RDCaV/>AB\u0011\u0001GX\u0005\u0003?F\u0012\u0011\u0002U1uQB\u000b'/Y7\"\u0003aC#\u0001B\u0018)\t\u0011QThY\u0011\u0002I\u0006I\"/Z2fSZ,'o]\u0018|gR\u0014X-Y7JIj\u0002C\fZ\u0016~\u0003-\u0011\u0017\r^2iKNd\u0015n\u001d;\u0015\u0005\u001d\\\u0007cA\"LQB\u0011!%[\u0005\u0003U2\u0011\u0011BQ1uG\"LeNZ8\t\u000b1,\u0001\u0019A7\u0002\u0019M$\u0018\r^;t!\u0006\u0014\u0018-\\:\u0011\u00079\u001cX/D\u0001p\u0015\t\u0001\u0018/\u0001\u0003vi&d'\"\u0001:\u0002\t)\fg/Y\u0005\u0003i>\u0014A\u0001T5tiB\u0011!E^\u0005\u0003o2\u00111BQ1uG\"\u001cF/\u0019;vg\"\"1._\u001f}!\t\u0001$0\u0003\u0002|c\tQ\u0011+^3ssB\u000b'/Y7\"\u0003MA#!B\u0018)\t\u0015QTh`\u0011\u0003\u0003\u0003\tqAY1uG\",7/\u0001\u0005p]\u0016\u0014\u0015\r^2i)\rA\u0017q\u0001\u0005\b\u0003\u00131\u0001\u0019AA\u0006\u0003\u001d\u0011\u0017\r^2i\u0013\u0012\u00042\u0001HA\u0007\u0013\r\ty!\b\u0002\u0005\u0019>tw\r\u000b\u0004\u0002\buk\u00141C\u0011\u0003\u0003\u0013A#AB\u0018)\u000b\u0019QT(!\u0007\"\u0005\u0005m\u0011A\u00062bi\u000eDWm]\u0018|E\u0006$8\r[%eu\u0001bFmK?\u0002\u001d=\u0004XM]1uS>t7\u000fT5tiR!\u0011\u0011EA\u0015!\u0011\u00195*a\t\u0011\u0007\t\n)#C\u0002\u0002(1\u00111cT;uaV$x\n]3sCRLwN\\%oM>Dq!!\u0003\b\u0001\u0004\tY\u0001\u000b\u0004\u0002*uk\u00141\u0003\u0015\u0003\u000f=BSa\u0002\u001e>\u0003c\t#!a\r\u0002C\t\fGo\u00195fg>Z(-\u0019;dQ&#'\b\t/eWu|s\u000e]3sCRLwN\\:\u0002\u0019=tWm\u00149fe\u0006$\u0018n\u001c8\u0015\r\u0005\r\u0012\u0011HA\u001f\u0011\u001d\tI\u0001\u0003a\u0001\u0003\u0017Ac!!\u000f^{\u0005M\u0001bBA \u0011\u0001\u0007\u0011\u0011I\u0001\u0005_BLE\r\u0005\u0003\u0002D\u0005\rd\u0002BA#\u0003;rA!a\u0012\u0002X9!\u0011\u0011JA+\u001d\u0011\tY%a\u0015\u000f\t\u00055\u0013\u0011\u000b\b\u0004\u000b\u0006=\u0013\"A\r\n\u0005]A\u0012BA\u000b\u0017\u0013\tiA#\u0003\u0003\u0002Z\u0005m\u0013AA;j\u0015\tiA#\u0003\u0003\u0002`\u0005\u0005\u0014\u0001H*ue\u0016\fW.\u001b8h\u0015>\u0014\u0007K]8he\u0016\u001c8\u000fT5ti\u0016tWM\u001d\u0006\u0005\u00033\nY&\u0003\u0003\u0002f\u0005\u001d$AC(viB,Ho\u00149JI*!\u0011qLA1Q\u0019\ti$X\u001f\u0002l\u0005\u0012\u0011QN\u0001\u000b_V$\b/\u001e;Pa&#\u0007F\u0001\u00050Q\u0015A!(PA:C\t\t)(A\u001acCR\u001c\u0007.Z:0w\n\fGo\u00195JIj\u0002C\fZ\u0016~_=\u0004XM]1uS>t7oL>pkR\u0004X\u000f^(q\u0013\u0012T\u0004\u0005\u00183,{\u00069\u0011M^4SCR,G\u0003BA>\u0003\u000f\u0003R\u0001HA?\u0003\u0003K1!a \u001e\u0005\u0019y\u0005\u000f^5p]B\u0019A$a!\n\u0007\u0005\u0015UD\u0001\u0004E_V\u0014G.\u001a\u0005\b\u0003\u0013K\u0001\u0019AAF\u0003\u0011!\u0017\r^1\u0011\t\r[\u0015\u0011Q\u0001\bCZ<G+[7f)\u0011\t\t*a%\u0011\u000bq\ti(a\u0003\t\u000f\u0005%%\u00021\u0001\u0002\u0016B!1iSA\u0006Q\u0019\u0001\u0011\u0011T\u001f\u0002 B\u0019\u0001'a'\n\u0007\u0005u\u0015G\u0001\u0005Qe>$WoY3tY\t\t\t+\t\u0002\u0002$\u0006\u0001\u0012\r\u001d9mS\u000e\fG/[8o_)\u001cxN\u001c"
)
public class ApiStreamingRootResource implements BaseStreamingAppResource {
   @PathParam("appId")
   private String appId;
   @PathParam("attemptId")
   private String attemptId;
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public Object withListener(final Function1 fn) {
      return BaseStreamingAppResource.withListener$(this, fn);
   }

   public Object withUI(final Function1 fn) {
      return BaseAppResource.withUI$(this, fn);
   }

   public void checkUIViewPermissions() {
      BaseAppResource.checkUIViewPermissions$(this);
   }

   public UIRoot uiRoot() {
      return ApiRequestContext.uiRoot$(this);
   }

   public String appId() {
      return this.appId;
   }

   public void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   public String attemptId() {
      return this.attemptId;
   }

   public void attemptId_$eq(final String x$1) {
      this.attemptId = x$1;
   }

   public ServletContext servletContext() {
      return this.servletContext;
   }

   public void servletContext_$eq(final ServletContext x$1) {
      this.servletContext = x$1;
   }

   public HttpServletRequest httpRequest() {
      return this.httpRequest;
   }

   public void httpRequest_$eq(final HttpServletRequest x$1) {
      this.httpRequest = x$1;
   }

   @GET
   @Path("statistics")
   public StreamingStatistics streamingStatistics() {
      return (StreamingStatistics)this.withListener((listener) -> {
         Seq batches = listener.retainedBatches();
         Option avgInputRate = this.avgRate((Seq)batches.map((x$1) -> BoxesRunTime.boxToDouble($anonfun$streamingStatistics$2(listener, x$1))));
         Option avgSchedulingDelay = this.avgTime((Seq)batches.flatMap((x$2) -> x$2.schedulingDelay()));
         Option avgProcessingTime = this.avgTime((Seq)batches.flatMap((x$3) -> x$3.processingDelay()));
         Option avgTotalDelay = this.avgTime((Seq)batches.flatMap((x$4) -> x$4.totalDelay()));
         return new StreamingStatistics(new Date(listener.startTime()), listener.batchDuration(), listener.numReceivers(), listener.numActiveReceivers(), listener.numInactiveReceivers(), listener.numTotalCompletedBatches(), (long)listener.retainedCompletedBatches().size(), listener.numUnprocessedBatches(), listener.numTotalProcessedRecords(), listener.numTotalReceivedRecords(), avgInputRate, avgSchedulingDelay, avgProcessingTime, avgTotalDelay);
      });
   }

   @GET
   @Path("receivers")
   public Seq receiversList() {
      return (Seq)this.withListener((listener) -> (Seq)((IterableOnceOps)listener.receivedRecordRateWithBatchTime().map((x0$1) -> {
            if (x0$1 != null) {
               int streamId = x0$1._1$mcI$sp();
               Seq eventRates = (Seq)x0$1._2();
               Option receiverInfo = listener.receiverInfo(streamId);
               String streamName = (String)receiverInfo.map((x$5) -> x$5.name()).orElse(() -> listener.streamName(streamId)).getOrElse(() -> "Stream-" + streamId);
               Option avgEventRate = (Option)(eventRates.isEmpty() ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(((IterableOnceOps)eventRates.map((x$6) -> BoxesRunTime.boxToDouble($anonfun$receiversList$6(x$6)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)eventRates.size())));
               Tuple3 var10000;
               if (.MODULE$.equals(receiverInfo)) {
                  var10000 = new Tuple3(.MODULE$, .MODULE$, .MODULE$);
               } else {
                  if (!(receiverInfo instanceof Some)) {
                     throw new MatchError(receiverInfo);
                  }

                  Some var14 = (Some)receiverInfo;
                  org.apache.spark.streaming.scheduler.ReceiverInfo info = (org.apache.spark.streaming.scheduler.ReceiverInfo)var14.value();
                  Option someTime = (Option)(info.lastErrorTime() >= 0L ? new Some(new Date(info.lastErrorTime())) : .MODULE$);
                  Option someMessage = (Option)(info.lastErrorMessage().length() > 0 ? new Some(info.lastErrorMessage()) : .MODULE$);
                  Option someError = (Option)(info.lastError().length() > 0 ? new Some(info.lastError()) : .MODULE$);
                  var10000 = new Tuple3(someTime, someMessage, someError);
               }

               Tuple3 var12 = var10000;
               if (var12 != null) {
                  Option errorTime = (Option)var12._1();
                  Option errorMessage = (Option)var12._2();
                  Option error = (Option)var12._3();
                  Tuple3 var11 = new Tuple3(errorTime, errorMessage, error);
                  Option errorTime = (Option)var11._1();
                  Option errorMessage = (Option)var11._2();
                  Option error = (Option)var11._3();
                  return new ReceiverInfo(streamId, streamName, receiverInfo.map((x$8) -> BoxesRunTime.boxToBoolean($anonfun$receiversList$7(x$8))), receiverInfo.map((x$9) -> x$9.executorId()), receiverInfo.map((x$10) -> x$10.location()), errorTime, errorMessage, error, avgEventRate, eventRates);
               } else {
                  throw new MatchError(var12);
               }
            } else {
               throw new MatchError(x0$1);
            }
         })).toSeq().sortBy((x$11) -> BoxesRunTime.boxToInteger($anonfun$receiversList$10(x$11)), scala.math.Ordering.Int..MODULE$));
   }

   @GET
   @Path("receivers/{streamId: \\d+}")
   public ReceiverInfo oneReceiver(@PathParam("streamId") final int streamId) {
      return (ReceiverInfo)this.receiversList().find((x$12) -> BoxesRunTime.boxToBoolean($anonfun$oneReceiver$1(streamId, x$12))).getOrElse(() -> {
         throw new NotFoundException("unknown receiver: " + streamId);
      });
   }

   @GET
   @Path("batches")
   public Seq batchesList(@QueryParam("status") final List statusParams) {
      return (Seq)this.withListener((listener) -> {
         List statuses = statusParams.isEmpty() ? Arrays.asList((Object[])BatchStatus.values()) : statusParams;
         Seq statusToBatches = new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BatchStatus.COMPLETED), listener.retainedCompletedBatches()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BatchStatus.QUEUED), listener.waitingBatches()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BatchStatus.PROCESSING), listener.runningBatches()), scala.collection.immutable.Nil..MODULE$)));
         Seq batchInfos = (Seq)statusToBatches.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$batchesList$2(check$ifrefutable$1))).flatMap((x$14) -> {
            if (x$14 != null) {
               BatchStatus status = (BatchStatus)x$14._1();
               Seq batches = (Seq)x$14._2();
               return (Seq)batches.withFilter((batch) -> BoxesRunTime.boxToBoolean($anonfun$batchesList$4(statuses, status, batch))).map((batch) -> {
                  long batchId = batch.batchTime().milliseconds();
                  Option firstFailureReason = ((IterableOps)batch.outputOperations().flatMap((x$13) -> ((OutputOperationUIData)x$13._2()).failureReason())).headOption();
                  return new BatchInfo(batchId, new Date(batchId), status.toString(), listener.batchDuration(), batch.numRecords(), batch.schedulingDelay(), batch.processingDelay(), batch.totalDelay(), batch.numActiveOutputOp(), batch.numCompletedOutputOp(), batch.numFailedOutputOp(), batch.outputOperations().size(), firstFailureReason);
               });
            } else {
               throw new MatchError(x$14);
            }
         });
         return (Seq)batchInfos.sortBy((x$15) -> BoxesRunTime.boxToLong($anonfun$batchesList$7(x$15)), scala.math.Ordering.Long..MODULE$);
      });
   }

   @GET
   @Path("batches/{batchId: \\d+}")
   public BatchInfo oneBatch(@PathParam("batchId") final long batchId) {
      return (BatchInfo)this.batchesList(Collections.emptyList()).find((x$16) -> BoxesRunTime.boxToBoolean($anonfun$oneBatch$1(batchId, x$16))).getOrElse(() -> {
         throw new NotFoundException("unknown batch: " + batchId);
      });
   }

   @GET
   @Path("batches/{batchId: \\d+}/operations")
   public Seq operationsList(@PathParam("batchId") final long batchId) {
      return (Seq)this.withListener((listener) -> {
         Option var5 = listener.getBatchUIData(new Time(batchId));
         if (var5 instanceof Some var6) {
            BatchUIData batch = (BatchUIData)var6.value();
            Iterable ops = (Iterable)batch.outputOperations().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$operationsList$2(check$ifrefutable$2))).map((x$21) -> {
               if (x$21 != null) {
                  int opId = x$21._1$mcI$sp();
                  OutputOperationUIData op = (OutputOperationUIData)x$21._2();
                  Seq jobIds = (Seq)((IterableOnceOps)((IterableOps)batch.outputOpIdSparkJobIdPairs().filter((x$17) -> BoxesRunTime.boxToBoolean($anonfun$operationsList$4(opId, x$17)))).map((x$18) -> BoxesRunTime.boxToInteger($anonfun$operationsList$5(x$18)))).toSeq().sorted(scala.math.Ordering.Int..MODULE$);
                  return new OutputOperationInfo(opId, op.name(), op.description(), op.startTime().map((x$19) -> $anonfun$operationsList$6(BoxesRunTime.unboxToLong(x$19))), op.endTime().map((x$20) -> $anonfun$operationsList$7(BoxesRunTime.unboxToLong(x$20))), op.duration(), op.failureReason(), jobIds);
               } else {
                  throw new MatchError(x$21);
               }
            });
            return ops.toSeq();
         } else if (.MODULE$.equals(var5)) {
            throw new NotFoundException("unknown batch: " + batchId);
         } else {
            throw new MatchError(var5);
         }
      });
   }

   @GET
   @Path("batches/{batchId: \\d+}/operations/{outputOpId: \\d+}")
   public OutputOperationInfo oneOperation(@PathParam("batchId") final long batchId, @PathParam("outputOpId") final int opId) {
      return (OutputOperationInfo)this.operationsList(batchId).find((x$22) -> BoxesRunTime.boxToBoolean($anonfun$oneOperation$1(opId, x$22))).getOrElse(() -> {
         throw new NotFoundException("unknown output operation: " + opId);
      });
   }

   private Option avgRate(final Seq data) {
      return (Option)(data.isEmpty() ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(data.sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)data.size())));
   }

   private Option avgTime(final Seq data) {
      return (Option)(data.isEmpty() ? .MODULE$ : new Some(BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(data.sum(scala.math.Numeric.LongIsIntegral..MODULE$)) / (long)data.size())));
   }

   // $FF: synthetic method
   public static final double $anonfun$streamingStatistics$2(final StreamingJobProgressListener listener$1, final BatchUIData x$1) {
      return (double)x$1.numRecords() * (double)1000.0F / (double)listener$1.batchDuration();
   }

   // $FF: synthetic method
   public static final double $anonfun$receiversList$6(final Tuple2 x$6) {
      return x$6._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$receiversList$7(final org.apache.spark.streaming.scheduler.ReceiverInfo x$8) {
      return x$8.active();
   }

   // $FF: synthetic method
   public static final int $anonfun$receiversList$10(final ReceiverInfo x$11) {
      return x$11.streamId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$oneReceiver$1(final int streamId$2, final ReceiverInfo x$12) {
      return x$12.streamId() == streamId$2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$batchesList$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$batchesList$4(final List statuses$1, final BatchStatus status$1, final BatchUIData batch) {
      return statuses$1.contains(status$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$batchesList$7(final BatchInfo x$15) {
      return -x$15.batchId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$oneBatch$1(final long batchId$1, final BatchInfo x$16) {
      return x$16.batchId() == batchId$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$operationsList$2(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$operationsList$4(final int opId$1, final OutputOpIdAndSparkJobId x$17) {
      return x$17.outputOpId() == opId$1;
   }

   // $FF: synthetic method
   public static final int $anonfun$operationsList$5(final OutputOpIdAndSparkJobId x$18) {
      return x$18.sparkJobId();
   }

   // $FF: synthetic method
   public static final Date $anonfun$operationsList$6(final long x$19) {
      return new Date(x$19);
   }

   // $FF: synthetic method
   public static final Date $anonfun$operationsList$7(final long x$20) {
      return new Date(x$20);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$oneOperation$1(final int opId$2, final OutputOperationInfo x$22) {
      return x$22.outputOpId() == opId$2;
   }

   public ApiStreamingRootResource() {
      ApiRequestContext.$init$(this);
      BaseAppResource.$init$(this);
      BaseStreamingAppResource.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
