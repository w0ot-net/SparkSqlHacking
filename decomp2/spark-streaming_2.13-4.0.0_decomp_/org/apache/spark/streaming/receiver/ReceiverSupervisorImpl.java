package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkException;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.scheduler.AddBlock;
import org.apache.spark.streaming.scheduler.DeregisterReceiver;
import org.apache.spark.streaming.scheduler.ReceivedBlockInfo;
import org.apache.spark.streaming.scheduler.RegisterReceiver;
import org.apache.spark.streaming.scheduler.ReportError;
import org.apache.spark.streaming.util.WriteAheadLogUtils$;
import org.sparkproject.guava.base.Throwables;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tec!\u0002\u0014(\u0001%\n\u0004\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011=\u0003!\u0011!Q\u0001\nAC\u0001\u0002\u0016\u0001\u0003\u0002\u0003\u0006I!\u0016\u0005\t;\u0002\u0011\t\u0011)A\u0005=\")A\u000e\u0001C\u0001[\"9q\u000f\u0001b\u0001\n\u0013A\bBB=\u0001A\u0003%\u0011\rC\u0004{\u0001\t\u0007I\u0011\u0002=\t\rm\u0004\u0001\u0015!\u0003b\u0011\u001da\bA1A\u0005\nuDq!a\u0001\u0001A\u0003%a\u0010C\u0005\u0002\u0006\u0001\u0011\r\u0011\"\u0003\u0002\b!A\u0011Q\u0003\u0001!\u0002\u0013\tI\u0001C\u0005\u0002\u0018\u0001\u0011\r\u0011\"\u0003\u0002\b!A\u0011\u0011\u0004\u0001!\u0002\u0013\tI\u0001C\u0005\u0002\u001c\u0001\u0011\r\u0011\"\u0003\u0002\u001e!A\u0011q\u0007\u0001!\u0002\u0013\ty\u0002C\u0005\u0002:\u0001\u0011\r\u0011\"\u0003\u0002<!A\u00111\n\u0001!\u0002\u0013\ti\u0004C\u0005\u0002N\u0001\u0011\r\u0011\"\u0003\u0002P!A\u0011q\u000b\u0001!\u0002\u0013\t\t\u0006C\u0005\u0002f\u0001\u0011\r\u0011\"\u0003\u0002h!A\u0011\u0011\u000e\u0001!\u0002\u0013\t)\u0005\u0003\u0005\u0002l\u0001!\t%KA7\u0011\u001d\t)\b\u0001C\u0001\u0003oBq!a!\u0001\t\u0003\t)\tC\u0004\u0002<\u0002!\t!!0\t\u000f\u0005\u0005\b\u0001\"\u0001\u0002d\"9\u0011\u0011 \u0001\u0005\u0002\u0005m\bb\u0002B\u0006\u0001\u0011\u0005!Q\u0002\u0005\b\u0005;\u0001A\u0011\u000bB\u0010\u0011\u001d\u0011\t\u0003\u0001C)\u0005GAqAa\u000b\u0001\t#\u0012i\u0003C\u0004\u00036\u0001!\tFa\u000e\t\u000f\tu\u0002\u0001\"\u0011\u0003@!9!Q\t\u0001\u0005\n\t\u001d\u0003b\u0002B%\u0001\u0011%!1\n\u0002\u0017%\u0016\u001cW-\u001b<feN+\b/\u001a:wSN|'/S7qY*\u0011\u0001&K\u0001\te\u0016\u001cW-\u001b<fe*\u0011!fK\u0001\ngR\u0014X-Y7j]\u001eT!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\n\u0004\u0001I2\u0004CA\u001a5\u001b\u00059\u0013BA\u001b(\u0005I\u0011VmY3jm\u0016\u00148+\u001e9feZL7o\u001c:\u0011\u0005]RT\"\u0001\u001d\u000b\u0005eZ\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005mB$a\u0002'pO\u001eLgnZ\u0002\u0001a\tq4\tE\u00024\u007f\u0005K!\u0001Q\u0014\u0003\u0011I+7-Z5wKJ\u0004\"AQ\"\r\u0001\u0011IA)AA\u0001\u0002\u0003\u0015\t!\u0012\u0002\u0004?\u0012\n\u0014C\u0001$M!\t9%*D\u0001I\u0015\u0005I\u0015!B:dC2\f\u0017BA&I\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aR'\n\u00059C%aA!os\u0006\u0019QM\u001c<\u0011\u0005E\u0013V\"A\u0016\n\u0005M[#\u0001C*qCJ\\WI\u001c<\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0002W76\tqK\u0003\u0002Y3\u0006!1m\u001c8g\u0015\tQV&\u0001\u0004iC\u0012|w\u000e]\u0005\u00039^\u0013QbQ8oM&<WO]1uS>t\u0017aE2iK\u000e\\\u0007o\\5oi\u0012K'o\u00149uS>t\u0007cA$`C&\u0011\u0001\r\u0013\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\tLgBA2h!\t!\u0007*D\u0001f\u0015\t1G(\u0001\u0004=e>|GOP\u0005\u0003Q\"\u000ba\u0001\u0015:fI\u00164\u0017B\u00016l\u0005\u0019\u0019FO]5oO*\u0011\u0001\u000eS\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b9|G/\u001e<\u0011\u0005M\u0002\u0001\"\u0002\u0015\u0006\u0001\u0004\u0001\bGA9t!\r\u0019tH\u001d\t\u0003\u0005N$\u0011\u0002R8\u0002\u0002\u0003\u0005)\u0011A#\t\u000b=+\u0001\u0019\u0001)\t\u000bQ+\u0001\u0019A+\t\u000bu+\u0001\u0019\u00010\u0002\t!|7\u000f^\u000b\u0002C\u0006)\u0001n\\:uA\u0005QQ\r_3dkR|'/\u00133\u0002\u0017\u0015DXmY;u_JLE\rI\u0001\u0015e\u0016\u001cW-\u001b<fI\ncwnY6IC:$G.\u001a:\u0016\u0003y\u0004\"aM@\n\u0007\u0005\u0005qE\u0001\u000bSK\u000e,\u0017N^3e\u00052|7m\u001b%b]\u0012dWM]\u0001\u0016e\u0016\u001cW-\u001b<fI\ncwnY6IC:$G.\u001a:!\u0003=!(/Y2lKJ,e\u000e\u001a9pS:$XCAA\u0005!\u0011\tY!!\u0005\u000e\u0005\u00055!bAA\bW\u0005\u0019!\u000f]2\n\t\u0005M\u0011Q\u0002\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003A!(/Y2lKJ,e\u000e\u001a9pS:$\b%\u0001\u0005f]\u0012\u0004x.\u001b8u\u0003%)g\u000e\u001a9pS:$\b%\u0001\u0006oK^\u0014En\\2l\u0013\u0012,\"!a\b\u0011\t\u0005\u0005\u00121G\u0007\u0003\u0003GQA!!\n\u0002(\u00051\u0011\r^8nS\u000eTA!!\u000b\u0002,\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u00055\u0012qF\u0001\u0005kRLGN\u0003\u0002\u00022\u0005!!.\u0019<b\u0013\u0011\t)$a\t\u0003\u0015\u0005#x.\\5d\u0019>tw-A\u0006oK^\u0014En\\2l\u0013\u0012\u0004\u0013!\u0007:fO&\u001cH/\u001a:fI\ncwnY6HK:,'/\u0019;peN,\"!!\u0010\u0011\r\u0005}\u0012\u0011IA#\u001b\t\t9#\u0003\u0003\u0002D\u0005\u001d\"!F\"p]\u000e,(O]3oi2Kgn[3e#V,W/\u001a\t\u0004g\u0005\u001d\u0013bAA%O\tq!\t\\8dW\u001e+g.\u001a:bi>\u0014\u0018A\u0007:fO&\u001cH/\u001a:fI\ncwnY6HK:,'/\u0019;peN\u0004\u0013!\b3fM\u0006,H\u000e\u001e\"m_\u000e\\w)\u001a8fe\u0006$xN\u001d'jgR,g.\u001a:\u0016\u0005\u0005E#CBA*\u00033\nyF\u0002\u0004\u0002VU\u0001\u0011\u0011\u000b\u0002\ryI,g-\u001b8f[\u0016tGOP\u0001\u001fI\u00164\u0017-\u001e7u\u00052|7m[$f]\u0016\u0014\u0018\r^8s\u0019&\u001cH/\u001a8fe\u0002\u00022aRA.\u0013\r\ti\u0006\u0013\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M\n\t'C\u0002\u0002d\u001d\u0012aC\u00117pG.<UM\\3sCR|'\u000fT5ti\u0016tWM]\u0001\u0016I\u00164\u0017-\u001e7u\u00052|7m[$f]\u0016\u0014\u0018\r^8s+\t\t)%\u0001\feK\u001a\fW\u000f\u001c;CY>\u001c7nR3oKJ\fGo\u001c:!\u0003M9W\r^\"veJ,g\u000e\u001e*bi\u0016d\u0015.\\5u+\t\ty\u0007E\u0002H\u0003cJ1!a\u001dI\u0005\u0011auN\\4\u0002\u0015A,8\u000f[*j]\u001edW\r\u0006\u0003\u0002z\u0005}\u0004cA$\u0002|%\u0019\u0011Q\u0010%\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003\u0003K\u0002\u0019\u0001'\u0002\t\u0011\fG/Y\u0001\u0010aV\u001c\b.\u0011:sCf\u0014UO\u001a4feRA\u0011\u0011PAD\u0003G\u000bI\u000bC\u0004\u0002\nj\u0001\r!a#\u0002\u0017\u0005\u0014(/Y=Ck\u001a4WM\u001d\u0019\u0005\u0003\u001b\u000by\n\u0005\u0004\u0002\u0010\u0006e\u0015QT\u0007\u0003\u0003#SA!a%\u0002\u0016\u00069Q.\u001e;bE2,'bAAL\u0011\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005m\u0015\u0011\u0013\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000fE\u0002C\u0003?#1\"!)\u0002\b\u0006\u0005\t\u0011!B\u0001\u000b\n\u0019q\fJ\u001a\t\u000f\u0005\u0015&\u00041\u0001\u0002(\u0006qQ.\u001a;bI\u0006$\u0018m\u00149uS>t\u0007cA$`\u0019\"9\u00111\u0016\u000eA\u0002\u00055\u0016!\u00042m_\u000e\\\u0017\nZ(qi&|g\u000e\u0005\u0003H?\u0006=\u0006\u0003BAY\u0003ok!!a-\u000b\u0007\u0005U6&A\u0004ti>\u0014\u0018mZ3\n\t\u0005e\u00161\u0017\u0002\u000e'R\u0014X-Y7CY>\u001c7.\u00133\u0002\u0019A,8\u000f[%uKJ\fGo\u001c:\u0015\u0011\u0005e\u0014qXAo\u0003?Dq!!1\u001c\u0001\u0004\t\u0019-\u0001\u0005ji\u0016\u0014\u0018\r^8sa\u0011\t)-!7\u0011\r\u0005\u001d\u0017\u0011[Al\u001d\u0011\tI-!4\u000f\u0007\u0011\fY-C\u0001J\u0013\r\ty\rS\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019.!6\u0003\u0011%#XM]1u_JT1!a4I!\r\u0011\u0015\u0011\u001c\u0003\f\u00037\fy,!A\u0001\u0002\u000b\u0005QIA\u0002`IQBq!!*\u001c\u0001\u0004\t9\u000bC\u0004\u0002,n\u0001\r!!,\u0002\u0013A,8\u000f\u001b\"zi\u0016\u001cH\u0003CA=\u0003K\f)0a>\t\u000f\u0005\u001dH\u00041\u0001\u0002j\u0006)!-\u001f;fgB!\u00111^Ay\u001b\t\tiO\u0003\u0003\u0002p\u0006=\u0012a\u00018j_&!\u00111_Aw\u0005)\u0011\u0015\u0010^3Ck\u001a4WM\u001d\u0005\b\u0003Kc\u0002\u0019AAT\u0011\u001d\tY\u000b\ba\u0001\u0003[\u000b!\u0003];tQ\u0006sGMU3q_J$(\t\\8dWRA\u0011\u0011PA\u007f\u0005\u000f\u0011I\u0001C\u0004\u0002\u0000v\u0001\rA!\u0001\u0002\u001bI,7-Z5wK\u0012\u0014En\\2l!\r\u0019$1A\u0005\u0004\u0005\u000b9#!\u0004*fG\u0016Lg/\u001a3CY>\u001c7\u000eC\u0004\u0002&v\u0001\r!a*\t\u000f\u0005-V\u00041\u0001\u0002.\u0006Y!/\u001a9peR,%O]8s)\u0019\tIHa\u0004\u0003\u0014!1!\u0011\u0003\u0010A\u0002\u0005\fq!\\3tg\u0006<W\rC\u0004\u0003\u0016y\u0001\rAa\u0006\u0002\u000b\u0015\u0014(o\u001c:\u0011\t\u0005\u001d'\u0011D\u0005\u0005\u00057\t)NA\u0005UQJ|w/\u00192mK\u00069qN\\*uCJ$HCAA=\u0003\u0019ygn\u0015;paR1\u0011\u0011\u0010B\u0013\u0005OAaA!\u0005!\u0001\u0004\t\u0007b\u0002B\u000bA\u0001\u0007!\u0011\u0006\t\u0005\u000f~\u00139\"A\bp]J+7-Z5wKJ\u001cF/\u0019:u)\t\u0011y\u0003E\u0002H\u0005cI1Aa\rI\u0005\u001d\u0011un\u001c7fC:\fab\u001c8SK\u000e,\u0017N^3s'R|\u0007\u000f\u0006\u0004\u0002z\te\"1\b\u0005\u0007\u0005#\u0011\u0003\u0019A1\t\u000f\tU!\u00051\u0001\u0003*\u0005!2M]3bi\u0016\u0014En\\2l\u000f\u0016tWM]1u_J$B!!\u0012\u0003B!9!1I\u0012A\u0002\u0005}\u0013A\u00062m_\u000e\\w)\u001a8fe\u0006$xN\u001d'jgR,g.\u001a:\u0002\u00179,\u0007\u0010\u001e\"m_\u000e\\\u0017\nZ\u000b\u0003\u0003_\u000b\u0001c\u00197fC:,\bo\u00147e\u00052|7m[:\u0015\t\u0005e$Q\n\u0005\b\u0005\u001f*\u0003\u0019\u0001B)\u0003E\u0019G.Z1okB$\u0006N]3tQRKW.\u001a\t\u0005\u0005'\u0012)&D\u0001*\u0013\r\u00119&\u000b\u0002\u0005)&lW\r"
)
public class ReceiverSupervisorImpl extends ReceiverSupervisor {
   private final Receiver receiver;
   public final SparkEnv org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$env;
   private final String host;
   private final String executorId;
   private final ReceivedBlockHandler receivedBlockHandler;
   private final RpcEndpointRef trackerEndpoint;
   private final RpcEndpointRef endpoint;
   private final AtomicLong newBlockId;
   private final ConcurrentLinkedQueue org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators;
   private final BlockGeneratorListener defaultBlockGeneratorListener;
   private final BlockGenerator defaultBlockGenerator;

   private String host() {
      return this.host;
   }

   private String executorId() {
      return this.executorId;
   }

   private ReceivedBlockHandler receivedBlockHandler() {
      return this.receivedBlockHandler;
   }

   private RpcEndpointRef trackerEndpoint() {
      return this.trackerEndpoint;
   }

   private RpcEndpointRef endpoint() {
      return this.endpoint;
   }

   private AtomicLong newBlockId() {
      return this.newBlockId;
   }

   public ConcurrentLinkedQueue org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators() {
      return this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators;
   }

   private BlockGeneratorListener defaultBlockGeneratorListener() {
      return this.defaultBlockGeneratorListener;
   }

   private BlockGenerator defaultBlockGenerator() {
      return this.defaultBlockGenerator;
   }

   public long getCurrentRateLimit() {
      return this.defaultBlockGenerator().getCurrentLimit();
   }

   public void pushSingle(final Object data) {
      this.defaultBlockGenerator().addData(data);
   }

   public void pushArrayBuffer(final ArrayBuffer arrayBuffer, final Option metadataOption, final Option blockIdOption) {
      this.pushAndReportBlock(new ArrayBufferBlock(arrayBuffer), metadataOption, blockIdOption);
   }

   public void pushIterator(final Iterator iterator, final Option metadataOption, final Option blockIdOption) {
      this.pushAndReportBlock(new IteratorBlock(iterator), metadataOption, blockIdOption);
   }

   public void pushBytes(final ByteBuffer bytes, final Option metadataOption, final Option blockIdOption) {
      this.pushAndReportBlock(new ByteBufferBlock(bytes), metadataOption, blockIdOption);
   }

   public void pushAndReportBlock(final ReceivedBlock receivedBlock, final Option metadataOption, final Option blockIdOption) {
      StreamBlockId blockId = (StreamBlockId)blockIdOption.getOrElse(() -> this.nextBlockId());
      long time = System.currentTimeMillis();
      ReceivedBlockStoreResult blockStoreResult = this.receivedBlockHandler().storeBlock(blockId, receivedBlock);
      this.logDebug(() -> "Pushed block " + blockId + " in " + (System.currentTimeMillis() - time) + " ms");
      Option numRecords = blockStoreResult.numRecords();
      ReceivedBlockInfo blockInfo = new ReceivedBlockInfo(this.streamId(), numRecords, metadataOption, blockStoreResult);
      if (!BoxesRunTime.unboxToBoolean(this.trackerEndpoint().askSync(new AddBlock(blockInfo), .MODULE$.Boolean()))) {
         throw new SparkException("Failed to add block to receiver tracker.");
      } else {
         this.logDebug(() -> "Reported block " + blockId);
      }
   }

   public void reportError(final String message, final Throwable error) {
      String errorString = (String)scala.Option..MODULE$.apply(error).map((throwable) -> Throwables.getStackTraceAsString(throwable)).getOrElse(() -> "");
      this.trackerEndpoint().send(new ReportError(this.streamId(), message, errorString));
      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reported error ", " - ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, error)})))));
   }

   public void onStart() {
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators()).asScala().foreach((x$1) -> {
         $anonfun$onStart$1(x$1);
         return BoxedUnit.UNIT;
      });
   }

   public void onStop(final String message, final Option error) {
      ReceivedBlockHandler var4 = this.receivedBlockHandler();
      if (var4 instanceof WriteAheadLogBasedBlockHandler var5) {
         var5.stop();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators()).asScala().foreach((x$2) -> {
         $anonfun$onStop$1(x$2);
         return BoxedUnit.UNIT;
      });
      this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$env.rpcEnv().stop(this.endpoint());
   }

   public boolean onReceiverStart() {
      RegisterReceiver msg = new RegisterReceiver(this.streamId(), this.receiver.getClass().getSimpleName(), this.host(), this.executorId(), this.endpoint());
      return BoxesRunTime.unboxToBoolean(this.trackerEndpoint().askSync(msg, .MODULE$.Boolean()));
   }

   public void onReceiverStop(final String message, final Option error) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deregistering receiver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(this.streamId()))})))));
      String errorString = (String)error.map((throwable) -> Throwables.getStackTraceAsString(throwable)).getOrElse(() -> "");
      this.trackerEndpoint().askSync(new DeregisterReceiver(this.streamId(), message, errorString), .MODULE$.Boolean());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stopped receiver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(this.streamId()))})))));
   }

   public BlockGenerator createBlockGenerator(final BlockGeneratorListener blockGeneratorListener) {
      Iterable stoppedGenerators = (Iterable)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators()).asScala().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$createBlockGenerator$1(x$3)));
      stoppedGenerators.foreach((x$4) -> BoxesRunTime.boxToBoolean($anonfun$createBlockGenerator$2(this, x$4)));
      BlockGenerator newBlockGenerator = new BlockGenerator(blockGeneratorListener, this.streamId(), this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$env.conf(), BlockGenerator$.MODULE$.$lessinit$greater$default$4());
      this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators().add(newBlockGenerator);
      return newBlockGenerator;
   }

   private StreamBlockId nextBlockId() {
      return new StreamBlockId(this.streamId(), this.newBlockId().getAndIncrement());
   }

   public void org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$cleanupOldBlocks(final Time cleanupThreshTime) {
      this.logDebug(() -> "Cleaning up blocks older than " + cleanupThreshTime);
      this.receivedBlockHandler().cleanupOldBlocks(cleanupThreshTime.milliseconds());
   }

   // $FF: synthetic method
   public static final void $anonfun$onStart$1(final BlockGenerator x$1) {
      x$1.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$onStop$1(final BlockGenerator x$2) {
      x$2.stop();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createBlockGenerator$1(final BlockGenerator x$3) {
      return x$3.isStopped();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createBlockGenerator$2(final ReceiverSupervisorImpl $this, final BlockGenerator x$4) {
      return $this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators().remove(x$4);
   }

   public ReceiverSupervisorImpl(final Receiver receiver, final SparkEnv env, final Configuration hadoopConf, final Option checkpointDirOption) {
      super(receiver, env.conf());
      this.receiver = receiver;
      this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$env = env;
      this.host = org.apache.spark.SparkEnv..MODULE$.get().blockManager().blockManagerId().host();
      this.executorId = org.apache.spark.SparkEnv..MODULE$.get().blockManager().blockManagerId().executorId();
      Object var10001;
      if (WriteAheadLogUtils$.MODULE$.enableReceiverLog(env.conf())) {
         if (checkpointDirOption.isEmpty()) {
            throw new SparkException("Cannot enable receiver write-ahead log without checkpoint directory set. Please use streamingContext.checkpoint() to set the checkpoint directory. See documentation for more details.");
         }

         var10001 = new WriteAheadLogBasedBlockHandler(env.blockManager(), env.serializerManager(), receiver.streamId(), receiver.storageLevel(), env.conf(), hadoopConf, (String)checkpointDirOption.get(), WriteAheadLogBasedBlockHandler$.MODULE$.$lessinit$greater$default$8());
      } else {
         var10001 = new BlockManagerBasedBlockHandler(env.blockManager(), receiver.storageLevel());
      }

      this.receivedBlockHandler = (ReceivedBlockHandler)var10001;
      this.trackerEndpoint = org.apache.spark.util.RpcUtils..MODULE$.makeDriverRef("ReceiverTracker", env.conf(), env.rpcEnv());
      this.endpoint = env.rpcEnv().setupEndpoint("Receiver-" + this.streamId() + "-" + System.currentTimeMillis(), new ThreadSafeRpcEndpoint() {
         private final RpcEnv rpcEnv;
         // $FF: synthetic field
         private final ReceiverSupervisorImpl $outer;

         public final RpcEndpointRef self() {
            return RpcEndpoint.self$(this);
         }

         public PartialFunction receiveAndReply(final RpcCallContext context) {
            return RpcEndpoint.receiveAndReply$(this, context);
         }

         public void onError(final Throwable cause) {
            RpcEndpoint.onError$(this, cause);
         }

         public void onConnected(final RpcAddress remoteAddress) {
            RpcEndpoint.onConnected$(this, remoteAddress);
         }

         public void onDisconnected(final RpcAddress remoteAddress) {
            RpcEndpoint.onDisconnected$(this, remoteAddress);
         }

         public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
            RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
         }

         public void onStart() {
            RpcEndpoint.onStart$(this);
         }

         public void onStop() {
            RpcEndpoint.onStop$(this);
         }

         public final void stop() {
            RpcEndpoint.stop$(this);
         }

         public RpcEnv rpcEnv() {
            return this.rpcEnv;
         }

         public PartialFunction receive() {
            return new Serializable() {
               private static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final Object applyOrElse(final Object x1, final Function1 default) {
                  if (StopReceiver$.MODULE$.equals(x1)) {
                     this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().logInfo(() -> "Received stop signal");
                     this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().stop("Stopped by driver", scala.None..MODULE$);
                     return BoxedUnit.UNIT;
                  } else if (x1 instanceof CleanupOldBlocks) {
                     CleanupOldBlocks var5 = (CleanupOldBlocks)x1;
                     Time threshTime = var5.threshTime();
                     this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().logDebug(() -> "Received delete old batch signal");
                     this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$cleanupOldBlocks(threshTime);
                     return BoxedUnit.UNIT;
                  } else if (x1 instanceof UpdateRateLimit) {
                     UpdateRateLimit var7 = (UpdateRateLimit)x1;
                     long eps = var7.elementsPerSecond();
                     this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received a new rate limit: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RATE_LIMIT..MODULE$, BoxesRunTime.boxToLong(eps))})))));
                     scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.$outer.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer().org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators()).asScala().foreach((bg) -> {
                        $anonfun$applyOrElse$4(eps, bg);
                        return BoxedUnit.UNIT;
                     });
                     return BoxedUnit.UNIT;
                  } else {
                     return default.apply(x1);
                  }
               }

               public final boolean isDefinedAt(final Object x1) {
                  if (StopReceiver$.MODULE$.equals(x1)) {
                     return true;
                  } else if (x1 instanceof CleanupOldBlocks) {
                     return true;
                  } else {
                     return x1 instanceof UpdateRateLimit;
                  }
               }

               // $FF: synthetic method
               public static final void $anonfun$applyOrElse$4(final long eps$1, final BlockGenerator bg) {
                  bg.updateRate(eps$1);
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return Class.lambdaDeserialize<invokedynamic>(var0);
               }
            };
         }

         // $FF: synthetic method
         public ReceiverSupervisorImpl org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (ReceiverSupervisorImpl.this == null) {
               throw null;
            } else {
               this.$outer = ReceiverSupervisorImpl.this;
               RpcEndpoint.$init$(this);
               this.rpcEnv = ReceiverSupervisorImpl.this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$env.rpcEnv();
            }
         }
      });
      this.newBlockId = new AtomicLong(System.currentTimeMillis());
      this.org$apache$spark$streaming$receiver$ReceiverSupervisorImpl$$registeredBlockGenerators = new ConcurrentLinkedQueue();
      this.defaultBlockGeneratorListener = new BlockGeneratorListener() {
         // $FF: synthetic field
         private final ReceiverSupervisorImpl $outer;

         public void onAddData(final Object data, final Object metadata) {
         }

         public void onGenerateBlock(final StreamBlockId blockId) {
         }

         public void onError(final String message, final Throwable throwable) {
            this.$outer.reportError(message, throwable);
         }

         public void onPushBlock(final StreamBlockId blockId, final ArrayBuffer arrayBuffer) {
            this.$outer.pushArrayBuffer(arrayBuffer, scala.None..MODULE$, new Some(blockId));
         }

         public {
            if (ReceiverSupervisorImpl.this == null) {
               throw null;
            } else {
               this.$outer = ReceiverSupervisorImpl.this;
            }
         }
      };
      this.defaultBlockGenerator = this.createBlockGenerator(this.defaultBlockGeneratorListener());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
