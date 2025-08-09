package org.apache.spark.deploy.client;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.ApplicationDescription;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.ExecutorState$;
import org.apache.spark.deploy.master.Master$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\t]f!B\u001d;\u0001y\"\u0005\u0002C)\u0001\u0005\u0003\u0005\u000b\u0011B*\t\u0011e\u0003!\u0011!Q\u0001\niC\u0001\u0002\u001b\u0001\u0003\u0002\u0003\u0006I!\u001b\u0005\t[\u0002\u0011\t\u0011)A\u0005]\"A!\u000f\u0001B\u0001B\u0003%1\u000fC\u0003x\u0001\u0011\u0005\u0001\u0010\u0003\u0005\u0000\u0001\t\u0007I\u0011BA\u0001\u0011!\tY\u0001\u0001Q\u0001\n\u0005\r\u0001\"CA\u0007\u0001\t\u0007I\u0011BA\b\u0011!\t9\u0002\u0001Q\u0001\n\u0005E\u0001\"CA\r\u0001\t\u0007I\u0011BA\b\u0011!\tY\u0002\u0001Q\u0001\n\u0005E\u0001\"CA\u000f\u0001\t\u0007I\u0011BA\u0010\u0011!\ty\u0004\u0001Q\u0001\n\u0005\u0005\u0002\"CA!\u0001\t\u0007I\u0011BA\"\u0011!\t9\u0005\u0001Q\u0001\n\u0005\u0015\u0003\"CA%\u0001\t\u0007I\u0011BA&\u0011!\t\u0019\u0006\u0001Q\u0001\n\u00055cABA+\u0001\u0011\t9\u0006C\u0005R'\t\u0015\r\u0011\"\u0011\u0002`!I\u0011\u0011M\n\u0003\u0002\u0003\u0006Ia\u0015\u0005\u0007oN!\t!a\u0019\t\u0013\u0005-4\u00031A\u0005\n\u00055\u0004\"CA;'\u0001\u0007I\u0011BA<\u0011!\t\u0019i\u0005Q!\n\u0005=\u0004\"CAC'\u0001\u0007I\u0011BAD\u0011%\tyi\u0005a\u0001\n\u0013\t\t\n\u0003\u0005\u0002\u0016N\u0001\u000b\u0015BAE\u0011%\t9j\u0005b\u0001\n\u0013\tY\u0005\u0003\u0005\u0002\u001aN\u0001\u000b\u0011BA'\u0011%\tYj\u0005b\u0001\n\u0013\ti\n\u0003\u0005\u00026N\u0001\u000b\u0011BAP\u0011%\t)m\u0005b\u0001\n\u0013\t9\r\u0003\u0005\u0002ZN\u0001\u000b\u0011BAe\u0011%\tYn\u0005b\u0001\n\u0013\ti\u000e\u0003\u0005\u0002fN\u0001\u000b\u0011BAp\u0011%\t9o\u0005b\u0001\n\u0013\tI\u000f\u0003\u0005\u0002rN\u0001\u000b\u0011BAv\u0011\u001d\t\u0019p\u0005C!\u0003kDq!a>\u0014\t\u0013\tI\u0010C\u0004\u0003\bM!IA!\u0003\t\u000f\t=1\u0003\"\u0003\u0003\u0012!9!qC\n\u0005\n\te\u0001b\u0002B\u0010'\u0011\u0005#\u0011\u0005\u0005\b\u0005S\u0019B\u0011\tB\u0016\u0011\u001d\u00119d\u0005C\u0005\u0005sAqA!\u0014\u0014\t\u0003\u0012y\u0005C\u0004\u0003VM!\tEa\u0016\t\u000f\tE4\u0003\"\u0001\u0002v\"9!1O\n\u0005\u0002\tU\u0004b\u0002B>'\u0011\u0005\u0013Q\u001f\u0005\b\u0005{\u0002A\u0011AA{\u0011\u001d\u0011y\b\u0001C\u0001\u0003kDqA!!\u0001\t\u0003\u0011\u0019\tC\u0004\u0003\u0002\u0002!\tA!%\t\u000f\t%\u0006\u0001\"\u0001\u0003,\n\u00192\u000b^1oI\u0006dwN\\3BaB\u001cE.[3oi*\u00111\bP\u0001\u0007G2LWM\u001c;\u000b\u0005ur\u0014A\u00023fa2|\u0017P\u0003\u0002@\u0001\u0006)1\u000f]1sW*\u0011\u0011IQ\u0001\u0007CB\f7\r[3\u000b\u0003\r\u000b1a\u001c:h'\r\u0001Qi\u0013\t\u0003\r&k\u0011a\u0012\u0006\u0002\u0011\u0006)1oY1mC&\u0011!j\u0012\u0002\u0007\u0003:L(+\u001a4\u0011\u00051{U\"A'\u000b\u00059s\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005Ak%a\u0002'pO\u001eLgnZ\u0001\u0007eB\u001cWI\u001c<\u0004\u0001A\u0011AkV\u0007\u0002+*\u0011aKP\u0001\u0004eB\u001c\u0017B\u0001-V\u0005\u0019\u0011\u0006oY#om\u0006QQ.Y:uKJ,&\u000f\\:\u0011\u0007\u0019[V,\u0003\u0002]\u000f\n)\u0011I\u001d:bsB\u0011a,\u001a\b\u0003?\u000e\u0004\"\u0001Y$\u000e\u0003\u0005T!A\u0019*\u0002\rq\u0012xn\u001c;?\u0013\t!w)\u0001\u0004Qe\u0016$WMZ\u0005\u0003M\u001e\u0014aa\u0015;sS:<'B\u00013H\u00039\t\u0007\u000f\u001d#fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004\"A[6\u000e\u0003qJ!\u0001\u001c\u001f\u0003-\u0005\u0003\b\u000f\\5dCRLwN\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:\f\u0001\u0002\\5ti\u0016tWM\u001d\t\u0003_Bl\u0011AO\u0005\u0003cj\u00121d\u0015;b]\u0012\fGn\u001c8f\u0003B\u00048\t\\5f]Rd\u0015n\u001d;f]\u0016\u0014\u0018\u0001B2p]\u001a\u0004\"\u0001^;\u000e\u0003yJ!A\u001e \u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u0004zundXP \t\u0003_\u0002AQ!\u0015\u0004A\u0002MCQ!\u0017\u0004A\u0002iCQ\u0001\u001b\u0004A\u0002%DQ!\u001c\u0004A\u00029DQA\u001d\u0004A\u0002M\f!#\\1ti\u0016\u0014(\u000b]2BI\u0012\u0014Xm]:fgV\u0011\u00111\u0001\t\u0005\rn\u000b)\u0001E\u0002U\u0003\u000fI1!!\u0003V\u0005)\u0011\u0006oY!eIJ,7o]\u0001\u0014[\u0006\u001cH/\u001a:Sa\u000e\fE\r\u001a:fgN,7\u000fI\u0001\u001d%\u0016;\u0015j\u0015+S\u0003RKuJT0U\u00136+u*\u0016+`'\u0016\u001buJ\u0014#T+\t\t\t\u0002E\u0002G\u0003'I1!!\u0006H\u0005\rIe\u000e^\u0001\u001e%\u0016;\u0015j\u0015+S\u0003RKuJT0U\u00136+u*\u0016+`'\u0016\u001buJ\u0014#TA\u0005!\"+R$J'R\u0013\u0016\tV%P\u001d~\u0013V\t\u0016*J\u000bN\u000bQCU#H\u0013N#&+\u0011+J\u001f:{&+\u0012+S\u0013\u0016\u001b\u0006%\u0001\u0005f]\u0012\u0004x.\u001b8u+\t\t\t\u0003\u0005\u0004\u0002$\u0005U\u0012\u0011H\u0007\u0003\u0003KQA!a\n\u0002*\u00051\u0011\r^8nS\u000eTA!a\u000b\u0002.\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u0005=\u0012\u0011G\u0001\u0005kRLGN\u0003\u0002\u00024\u0005!!.\u0019<b\u0013\u0011\t9$!\n\u0003\u001f\u0005#x.\\5d%\u00164WM]3oG\u0016\u00042\u0001VA\u001e\u0013\r\ti$\u0016\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003%)g\u000e\u001a9pS:$\b%A\u0003baBLE-\u0006\u0002\u0002FA)\u00111EA\u001b;\u00061\u0011\r\u001d9JI\u0002\n!B]3hSN$XM]3e+\t\ti\u0005\u0005\u0003\u0002$\u0005=\u0013\u0002BA)\u0003K\u0011Q\"\u0011;p[&\u001c'i\\8mK\u0006t\u0017a\u0003:fO&\u001cH/\u001a:fI\u0002\u0012ab\u00117jK:$XI\u001c3q_&tGoE\u0003\u0014\u000b\u0006e3\nE\u0002U\u00037J1!!\u0018V\u0005U!\u0006N]3bIN\u000bg-\u001a*qG\u0016sG\r]8j]R,\u0012aU\u0001\beB\u001cWI\u001c<!)\u0011\t)'!\u001b\u0011\u0007\u0005\u001d4#D\u0001\u0001\u0011\u0015\tf\u00031\u0001T\u0003\u0019i\u0017m\u001d;feV\u0011\u0011q\u000e\t\u0006\r\u0006E\u0014\u0011H\u0005\u0004\u0003g:%AB(qi&|g.\u0001\u0006nCN$XM]0%KF$B!!\u001f\u0002\u0000A\u0019a)a\u001f\n\u0007\u0005utI\u0001\u0003V]&$\b\"CAA1\u0005\u0005\t\u0019AA8\u0003\rAH%M\u0001\b[\u0006\u001cH/\u001a:!\u0003M\tGN]3bIf$\u0015n]2p]:,7\r^3e+\t\tI\tE\u0002G\u0003\u0017K1!!$H\u0005\u001d\u0011un\u001c7fC:\fq#\u00197sK\u0006$\u0017\u0010R5tG>tg.Z2uK\u0012|F%Z9\u0015\t\u0005e\u00141\u0013\u0005\n\u0003\u0003[\u0012\u0011!a\u0001\u0003\u0013\u000bA#\u00197sK\u0006$\u0017\u0010R5tG>tg.Z2uK\u0012\u0004\u0013aC1me\u0016\fG-\u001f#fC\u0012\fA\"\u00197sK\u0006$\u0017\u0010R3bI\u0002\nQC]3hSN$XM]'bgR,'OR;ukJ,7/\u0006\u0002\u0002 B1\u00111EA\u001b\u0003C\u0003BAR.\u0002$B\"\u0011QUAY!\u0019\t9+!+\u0002.6\u0011\u0011\u0011F\u0005\u0005\u0003W\u000bIC\u0001\u0004GkR,(/\u001a\t\u0005\u0003_\u000b\t\f\u0004\u0001\u0005\u0017\u0005M\u0006%!A\u0001\u0002\u000b\u0005\u0011q\u0017\u0002\u0004?\u0012\n\u0014A\u0006:fO&\u001cH/\u001a:NCN$XM\u001d$viV\u0014Xm\u001d\u0011\u0012\t\u0005e\u0016q\u0018\t\u0004\r\u0006m\u0016bAA_\u000f\n9aj\u001c;iS:<\u0007c\u0001$\u0002B&\u0019\u00111Y$\u0003\u0007\u0005s\u00170\u0001\fsK\u001eL7\u000f\u001e:bi&|gNU3uef$\u0016.\\3s+\t\tI\r\u0005\u0004\u0002$\u0005U\u00121\u001a\u0019\u0005\u0003\u001b\f)\u000e\u0005\u0004\u0002(\u0006=\u00171[\u0005\u0005\u0003#\fICA\bTG\",G-\u001e7fI\u001a+H/\u001e:f!\u0011\ty+!6\u0005\u0017\u0005]'%!A\u0001\u0002\u000b\u0005\u0011q\u0017\u0002\u0004?\u0012\u0012\u0014a\u0006:fO&\u001cHO]1uS>t'+\u001a;ssRKW.\u001a:!\u0003a\u0011XmZ5ti\u0016\u0014X*Y:uKJ$\u0006N]3bIB{w\u000e\\\u000b\u0003\u0003?\u0004B!a*\u0002b&!\u00111]A\u0015\u0005I!\u0006N]3bIB{w\u000e\\#yK\u000e,Ho\u001c:\u00023I,w-[:uKJl\u0015m\u001d;feRC'/Z1e!>|G\u000eI\u0001\u0018e\u0016<\u0017n\u001d;sCRLwN\u001c*fiJLH\u000b\u001b:fC\u0012,\"!a;\u0011\t\u0005\u001d\u0016Q^\u0005\u0005\u0003_\fIC\u0001\rTG\",G-\u001e7fI\u0016CXmY;u_J\u001cVM\u001d<jG\u0016\f\u0001D]3hSN$(/\u0019;j_:\u0014V\r\u001e:z)\"\u0014X-\u00193!\u0003\u001dygn\u0015;beR$\"!!\u001f\u0002+Q\u0014\u0018PU3hSN$XM]!mY6\u000b7\u000f^3sgR\u0011\u00111 \t\u0005\rn\u000bi\u0010\r\u0003\u0002\u0000\n\r\u0001CBAT\u0003S\u0013\t\u0001\u0005\u0003\u00020\n\rAa\u0003B\u0003Q\u0005\u0005\t\u0011!B\u0001\u0003o\u00131a\u0018\u00134\u0003I\u0011XmZ5ti\u0016\u0014x+\u001b;i\u001b\u0006\u001cH/\u001a:\u0015\t\u0005e$1\u0002\u0005\b\u0005\u001bI\u0003\u0019AA\t\u0003!qG\u000f\u001b*fiJL\u0018\u0001D:f]\u0012$v.T1ti\u0016\u0014H\u0003BA=\u0005'AqA!\u0006+\u0001\u0004\ty,A\u0004nKN\u001c\u0018mZ3\u0002!%\u001c\bk\\:tS\ndW-T1ti\u0016\u0014H\u0003BAE\u00057AqA!\b,\u0001\u0004\t)!A\u0007sK6|G/Z!eIJ,7o]\u0001\be\u0016\u001cW-\u001b<f+\t\u0011\u0019\u0003E\u0004G\u0005K\ty,!\u001f\n\u0007\t\u001drIA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u0003=\u0011XmY3jm\u0016\fe\u000e\u001a*fa2LH\u0003\u0002B\u0012\u0005[AqAa\f.\u0001\u0004\u0011\t$A\u0004d_:$X\r\u001f;\u0011\u0007Q\u0013\u0019$C\u0002\u00036U\u0013aB\u00159d\u0007\u0006dGnQ8oi\u0016DH/\u0001\tbg.\fe\u000e\u001a*fa2L\u0018i]=oGV!!1\bB%)!\tIH!\u0010\u0003B\t\r\u0003b\u0002B ]\u0001\u0007\u0011\u0011H\u0001\fK:$\u0007o\\5oiJ+g\rC\u0004\u000309\u0002\rA!\r\t\u000f\t\u0015c\u00061\u0001\u0003H\u0005\u0019Qn]4\u0011\t\u0005=&\u0011\n\u0003\b\u0005\u0017r#\u0019AA\\\u0005\u0005!\u0016AD8o\t&\u001c8m\u001c8oK\u000e$X\r\u001a\u000b\u0005\u0003s\u0012\t\u0006C\u0004\u0003T=\u0002\r!!\u0002\u0002\u000f\u0005$GM]3tg\u0006qqN\u001c(fi^|'o[#se>\u0014HCBA=\u00053\u0012y\u0007C\u0004\u0003\\A\u0002\rA!\u0018\u0002\u000b\r\fWo]3\u0011\t\t}#\u0011\u000e\b\u0005\u0005C\u0012)GD\u0002a\u0005GJ\u0011\u0001S\u0005\u0004\u0005O:\u0015a\u00029bG.\fw-Z\u0005\u0005\u0005W\u0012iGA\u0005UQJ|w/\u00192mK*\u0019!qM$\t\u000f\tM\u0003\u00071\u0001\u0002\u0006\u0005\u0001R.\u0019:l\t&\u001c8m\u001c8oK\u000e$X\rZ\u0001\t[\u0006\u00148\u000eR3bIR!\u0011\u0011\u0010B<\u0011\u0019\u0011IH\ra\u0001;\u00061!/Z1t_:\faa\u001c8Ti>\u0004\u0018!B:uCJ$\u0018\u0001B:u_B\fQC]3rk\u0016\u001cH\u000fV8uC2,\u00050Z2vi>\u00148\u000f\u0006\u0003\u0003\u0006\n5\u0005C\u0002BD\u0005\u0017\u000bI)\u0004\u0002\u0003\n*\u0019\u00111F$\n\t\u0005-&\u0011\u0012\u0005\b\u0005\u001f3\u0004\u0019AA\t\u00039\u0011X-];fgR,G\rV8uC2$BA!\"\u0003\u0014\"9!QS\u001cA\u0002\t]\u0015a\u0007:fg>,(oY3Qe>4\u0017\u000e\\3U_R{G/\u00197Fq\u0016\u001c7\u000fE\u0004_\u00053\u0013i*!\u0005\n\u0007\tmuMA\u0002NCB\u0004BAa(\u0003&6\u0011!\u0011\u0015\u0006\u0004\u0005Gs\u0014\u0001\u0003:fg>,(oY3\n\t\t\u001d&\u0011\u0015\u0002\u0010%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u0006i1.\u001b7m\u000bb,7-\u001e;peN$BA!\"\u0003.\"9!q\u0016\u001dA\u0002\tE\u0016aC3yK\u000e,Ho\u001c:JIN\u0004RAa\u0018\u00034vKAA!.\u0003n\t\u00191+Z9"
)
public class StandaloneAppClient implements Logging {
   private final RpcEnv rpcEnv;
   public final ApplicationDescription org$apache$spark$deploy$client$StandaloneAppClient$$appDescription;
   public final StandaloneAppClientListener org$apache$spark$deploy$client$StandaloneAppClient$$listener;
   private final SparkConf conf;
   private final RpcAddress[] org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses;
   private final int org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_TIMEOUT_SECONDS;
   private final int org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_RETRIES;
   private final AtomicReference endpoint;
   private final AtomicReference org$apache$spark$deploy$client$StandaloneAppClient$$appId;
   private final AtomicBoolean org$apache$spark$deploy$client$StandaloneAppClient$$registered;
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

   public RpcAddress[] org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses() {
      return this.org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses;
   }

   public int org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_TIMEOUT_SECONDS() {
      return this.org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_TIMEOUT_SECONDS;
   }

   public int org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_RETRIES() {
      return this.org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_RETRIES;
   }

   private AtomicReference endpoint() {
      return this.endpoint;
   }

   public AtomicReference org$apache$spark$deploy$client$StandaloneAppClient$$appId() {
      return this.org$apache$spark$deploy$client$StandaloneAppClient$$appId;
   }

   public AtomicBoolean org$apache$spark$deploy$client$StandaloneAppClient$$registered() {
      return this.org$apache$spark$deploy$client$StandaloneAppClient$$registered;
   }

   public void start() {
      this.endpoint().set(this.rpcEnv.setupEndpoint("AppClient", new ClientEndpoint(this.rpcEnv)));
   }

   public void stop() {
      if (this.endpoint().get() != null) {
         try {
            RpcTimeout timeout = RpcUtils$.MODULE$.askRpcTimeout(this.conf);
            timeout.awaitResult(((RpcEndpointRef)this.endpoint().get()).ask(DeployMessages.StopAppClient$.MODULE$, .MODULE$.Boolean()));
         } catch (TimeoutException var3) {
            this.logInfo((Function0)(() -> "Stop request to Master timed out; it may already be shut down."));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.endpoint().set((Object)null);
      }
   }

   public Future requestTotalExecutors(final int requestedTotal) {
      return this.requestTotalExecutors((scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.org$apache$spark$deploy$client$StandaloneAppClient$$appDescription.defaultProfile()), BoxesRunTime.boxToInteger(requestedTotal))}))));
   }

   public Future requestTotalExecutors(final scala.collection.immutable.Map resourceProfileToTotalExecs) {
      if (this.endpoint().get() != null && this.org$apache$spark$deploy$client$StandaloneAppClient$$appId().get() != null) {
         return ((RpcEndpointRef)this.endpoint().get()).ask(new DeployMessages.RequestExecutors((String)this.org$apache$spark$deploy$client$StandaloneAppClient$$appId().get(), resourceProfileToTotalExecs), .MODULE$.Boolean());
      } else {
         this.logWarning((Function0)(() -> "Attempted to request executors before driver fully initialized."));
         return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
      }
   }

   public Future killExecutors(final Seq executorIds) {
      if (this.endpoint().get() != null && this.org$apache$spark$deploy$client$StandaloneAppClient$$appId().get() != null) {
         return ((RpcEndpointRef)this.endpoint().get()).ask(new DeployMessages.KillExecutors((String)this.org$apache$spark$deploy$client$StandaloneAppClient$$appId().get(), executorIds), .MODULE$.Boolean());
      } else {
         this.logWarning((Function0)(() -> "Attempted to kill executors before driver fully initialized."));
         return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
      }
   }

   public StandaloneAppClient(final RpcEnv rpcEnv, final String[] masterUrls, final ApplicationDescription appDescription, final StandaloneAppClientListener listener, final SparkConf conf) {
      this.rpcEnv = rpcEnv;
      this.org$apache$spark$deploy$client$StandaloneAppClient$$appDescription = appDescription;
      this.org$apache$spark$deploy$client$StandaloneAppClient$$listener = listener;
      this.conf = conf;
      Logging.$init$(this);
      this.org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses = (RpcAddress[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])masterUrls), (x$1) -> RpcAddress$.MODULE$.fromSparkURL(x$1), .MODULE$.apply(RpcAddress.class));
      this.org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_TIMEOUT_SECONDS = 20;
      this.org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_RETRIES = 3;
      this.endpoint = new AtomicReference();
      this.org$apache$spark$deploy$client$StandaloneAppClient$$appId = new AtomicReference();
      this.org$apache$spark$deploy$client$StandaloneAppClient$$registered = new AtomicBoolean(false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ClientEndpoint implements ThreadSafeRpcEndpoint, Logging {
      private final RpcEnv rpcEnv;
      private Option org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master;
      private boolean org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected;
      private final AtomicBoolean alreadyDead;
      private final AtomicReference org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures;
      private final AtomicReference registrationRetryTimer;
      private final ThreadPoolExecutor org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool;
      private final ScheduledExecutorService registrationRetryThread;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      // $FF: synthetic field
      public final StandaloneAppClient $outer;

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

      public final RpcEndpointRef self() {
         return RpcEndpoint.self$(this);
      }

      public void onError(final Throwable cause) {
         RpcEndpoint.onError$(this, cause);
      }

      public void onConnected(final RpcAddress remoteAddress) {
         RpcEndpoint.onConnected$(this, remoteAddress);
      }

      public final void stop() {
         RpcEndpoint.stop$(this);
      }

      public Logger org$apache$spark$internal$Logging$$log_() {
         return this.org$apache$spark$internal$Logging$$log_;
      }

      public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
         this.org$apache$spark$internal$Logging$$log_ = x$1;
      }

      public RpcEnv rpcEnv() {
         return this.rpcEnv;
      }

      public Option org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master() {
         return this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master;
      }

      public void org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master_$eq(final Option x$1) {
         this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master = x$1;
      }

      private boolean alreadyDisconnected() {
         return this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected;
      }

      public void org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected_$eq(final boolean x$1) {
         this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected = x$1;
      }

      private AtomicBoolean alreadyDead() {
         return this.alreadyDead;
      }

      public AtomicReference org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures() {
         return this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures;
      }

      private AtomicReference registrationRetryTimer() {
         return this.registrationRetryTimer;
      }

      public ThreadPoolExecutor org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool() {
         return this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool;
      }

      private ScheduledExecutorService registrationRetryThread() {
         return this.registrationRetryThread;
      }

      public void onStart() {
         try {
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerWithMaster(1);
         } catch (Exception var2) {
            this.logWarning((Function0)(() -> "Failed to connect to master"), var2);
            this.markDisconnected();
            this.stop();
         }

      }

      private java.util.concurrent.Future[] tryRegisterAllMasters() {
         return (java.util.concurrent.Future[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses()), (masterAddress) -> this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool().submit(new Runnable(masterAddress) {
               // $FF: synthetic field
               private final ClientEndpoint $outer;
               private final RpcAddress masterAddress$1;

               public void run() {
                  try {
                     if (this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$registered().get()) {
                        return;
                     }

                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to master ", "..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.masterAddress$1.toSparkURL())})))));
                     RpcEndpointRef masterRef = this.$outer.rpcEnv().setupEndpointRef(this.masterAddress$1, Master$.MODULE$.ENDPOINT_NAME());
                     masterRef.send(new DeployMessages.RegisterApplication(this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appDescription, this.$outer.self()));
                  } catch (Throwable var6) {
                     if (var6 instanceof InterruptedException) {
                        BoxedUnit var7 = BoxedUnit.UNIT;
                     } else {
                        if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
                           throw var6;
                        }

                        this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to connect to master "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.masterAddress$1)}))))), var6);
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }
                  }

               }

               public {
                  if (ClientEndpoint.this == null) {
                     throw null;
                  } else {
                     this.$outer = ClientEndpoint.this;
                     this.masterAddress$1 = masterAddress$1;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return Class.lambdaDeserialize<invokedynamic>(var0);
               }
            }), .MODULE$.apply(java.util.concurrent.Future.class));
      }

      public void org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerWithMaster(final int nthRetry) {
         this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures().set(this.tryRegisterAllMasters());
         this.registrationRetryTimer().set(this.registrationRetryThread().schedule(new Runnable(nthRetry) {
            // $FF: synthetic field
            private final ClientEndpoint $outer;
            private final int nthRetry$1;

            public void run() {
               if (this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$registered().get()) {
                  scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures().get()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$run$3(x$2)));
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool().shutdownNow();
               } else if (this.nthRetry$1 >= this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_RETRIES()) {
                  this.$outer.markDead("All masters are unresponsive! Giving up.");
               } else {
                  scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures().get()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$run$4(x$3)));
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerWithMaster(this.nthRetry$1 + 1);
               }
            }

            // $FF: synthetic method
            public static final boolean $anonfun$run$3(final java.util.concurrent.Future x$2) {
               return x$2.cancel(true);
            }

            // $FF: synthetic method
            public static final boolean $anonfun$run$4(final java.util.concurrent.Future x$3) {
               return x$3.cancel(true);
            }

            public {
               if (ClientEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = ClientEndpoint.this;
                  this.nthRetry$1 = nthRetry$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }, (long)this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$REGISTRATION_TIMEOUT_SECONDS(), TimeUnit.SECONDS));
      }

      public void org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$sendToMaster(final Object message) {
         Option var3 = this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master();
         if (var3 instanceof Some var4) {
            RpcEndpointRef masterRef = (RpcEndpointRef)var4.value();
            masterRef.send(message);
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else if (scala.None..MODULE$.equals(var3)) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Drop ", " because has not yet connected to master"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)})))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var3);
         }
      }

      private boolean isPossibleMaster(final RpcAddress remoteAddress) {
         return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses()), remoteAddress);
      }

      public PartialFunction receive() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final ClientEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof DeployMessages.RegisteredApplication var5) {
                  String appId_ = var5.appId();
                  RpcEndpointRef masterRef = var5.master();
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appId().set(appId_);
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$registered().set(true);
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master_$eq(new Some(masterRef));
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.connected((String)this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appId().get());
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.ApplicationRemoved var8) {
                  String message = var8.message();
                  this.$outer.markDead(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Master removed our application: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{message})));
                  this.$outer.stop();
                  return BoxedUnit.UNIT;
               } else {
                  if (x1 instanceof DeployMessages.ExecutorAdded var10) {
                     int id = var10.id();
                     String workerId = var10.workerId();
                     String hostPort = var10.hostPort();
                     int cores = var10.cores();
                     int memory = var10.memory();
                     if (true && workerId != null && hostPort != null && true && true) {
                        AtomicReference var37 = this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appId();
                        String fullId = var37 + "/" + id;
                        this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor added: ", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId), new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hostPort)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", " core(s)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CORES..MODULE$, BoxesRunTime.boxToInteger(cores))}))))));
                        this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.executorAdded(fullId, workerId, hostPort, cores, memory);
                        return BoxedUnit.UNIT;
                     }
                  }

                  if (x1 instanceof DeployMessages.ExecutorUpdated var22) {
                     int id = var22.id();
                     Enumeration.Value state = var22.state();
                     Option message = var22.message();
                     Option exitStatus = var22.exitStatus();
                     Option workerHost = var22.workerHost();
                     AtomicReference var10000 = this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appId();
                     String fullId = var10000 + "/" + id;
                     String messageText = (String)message.map((s) -> " (" + s + ")").getOrElse(() -> "");
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor updated: ", " is now "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, state), new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, messageText)}))))));
                     if (ExecutorState$.MODULE$.isFinished(state)) {
                        this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.executorRemoved(fullId, (String)message.getOrElse(() -> ""), exitStatus, workerHost);
                        return BoxedUnit.UNIT;
                     } else {
                        label57: {
                           Enumeration.Value var30 = ExecutorState$.MODULE$.DECOMMISSIONED();
                           if (state == null) {
                              if (var30 == null) {
                                 break label57;
                              }
                           } else if (state.equals(var30)) {
                              break label57;
                           }

                           return BoxedUnit.UNIT;
                        }

                        this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.executorDecommissioned(fullId, new ExecutorDecommissionInfo((String)message.getOrElse(() -> ""), workerHost));
                        return BoxedUnit.UNIT;
                     }
                  } else if (x1 instanceof DeployMessages.WorkerRemoved var31) {
                     String id = var31.id();
                     String host = var31.host();
                     String message = var31.message();
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Master removed worker ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, id)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))))));
                     this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.workerRemoved(id, host, message);
                     return BoxedUnit.UNIT;
                  } else if (x1 instanceof DeployMessages.MasterChanged var35) {
                     RpcEndpointRef masterRef = var35.master();
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Master has changed, new master is at "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterRef.address().toSparkURL())}))))));
                     this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master_$eq(new Some(masterRef));
                     this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected_$eq(false);
                     masterRef.send(new DeployMessages.MasterChangeAcknowledged((String)this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appId().get()));
                     return BoxedUnit.UNIT;
                  } else {
                     return default.apply(x1);
                  }
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof DeployMessages.RegisteredApplication) {
                  return true;
               } else if (x1 instanceof DeployMessages.ApplicationRemoved) {
                  return true;
               } else {
                  if (x1 instanceof DeployMessages.ExecutorAdded) {
                     DeployMessages.ExecutorAdded var4 = (DeployMessages.ExecutorAdded)x1;
                     String workerId = var4.workerId();
                     String hostPort = var4.hostPort();
                     if (true && workerId != null && hostPort != null && true && true) {
                        return true;
                     }
                  }

                  if (x1 instanceof DeployMessages.ExecutorUpdated) {
                     return true;
                  } else if (x1 instanceof DeployMessages.WorkerRemoved) {
                     return true;
                  } else {
                     return x1 instanceof DeployMessages.MasterChanged;
                  }
               }
            }

            public {
               if (ClientEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = ClientEndpoint.this;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public PartialFunction receiveAndReply(final RpcCallContext context) {
         return new Serializable(context) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final ClientEndpoint $outer;
            private final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (DeployMessages.StopAppClient$.MODULE$.equals(x1)) {
                  this.$outer.markDead("Application has been stopped.");
                  this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$sendToMaster(new DeployMessages.UnregisterApplication((String)this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$appId().get()));
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  this.$outer.stop();
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.RequestExecutors) {
                  DeployMessages.RequestExecutors var7 = (DeployMessages.RequestExecutors)x1;
                  Option var8 = this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master();
                  if (var8 instanceof Some) {
                     Some var9 = (Some)var8;
                     RpcEndpointRef m = (RpcEndpointRef)var9.value();
                     this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$askAndReplyAsync(m, this.context$1, var7);
                     BoxedUnit var16 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var8)) {
                        throw new MatchError(var8);
                     }

                     this.$outer.logWarning((Function0)(() -> "Attempted to request executors before registering with Master."));
                     this.context$1.reply(BoxesRunTime.boxToBoolean(false));
                     BoxedUnit var17 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.KillExecutors) {
                  DeployMessages.KillExecutors var11 = (DeployMessages.KillExecutors)x1;
                  Option var12 = this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master();
                  if (var12 instanceof Some) {
                     Some var13 = (Some)var12;
                     RpcEndpointRef m = (RpcEndpointRef)var13.value();
                     this.$outer.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$askAndReplyAsync(m, this.context$1, var11);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var12)) {
                        throw new MatchError(var12);
                     }

                     this.$outer.logWarning((Function0)(() -> "Attempted to kill executors before registering with Master."));
                     this.context$1.reply(BoxesRunTime.boxToBoolean(false));
                     BoxedUnit var15 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (DeployMessages.StopAppClient$.MODULE$.equals(x1)) {
                  return true;
               } else if (x1 instanceof DeployMessages.RequestExecutors) {
                  return true;
               } else {
                  return x1 instanceof DeployMessages.KillExecutors;
               }
            }

            public {
               if (ClientEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = ClientEndpoint.this;
                  this.context$1 = context$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public void org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$askAndReplyAsync(final RpcEndpointRef endpointRef, final RpcCallContext context, final Object msg) {
         endpointRef.ask(msg, .MODULE$.Boolean()).andThen(new Serializable(context) {
            private static final long serialVersionUID = 0L;
            private final RpcCallContext context$2;

            public final Object applyOrElse(final Try x1, final Function1 default) {
               boolean var4 = false;
               Failure var5 = null;
               if (x1 instanceof Success var7) {
                  boolean b = BoxesRunTime.unboxToBoolean(var7.value());
                  this.context$2.reply(BoxesRunTime.boxToBoolean(b));
                  return BoxedUnit.UNIT;
               } else {
                  if (x1 instanceof Failure) {
                     var4 = true;
                     var5 = (Failure)x1;
                     Throwable ie = var5.exception();
                     if (ie instanceof InterruptedException) {
                        return BoxedUnit.UNIT;
                     }
                  }

                  if (var4) {
                     Throwable var10 = var5.exception();
                     if (var10 != null) {
                        Option var11 = scala.util.control.NonFatal..MODULE$.unapply(var10);
                        if (!var11.isEmpty()) {
                           Throwable t = (Throwable)var11.get();
                           this.context$2.sendFailure(t);
                           return BoxedUnit.UNIT;
                        }
                     }
                  }

                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Try x1) {
               boolean var3 = false;
               Failure var4 = null;
               if (x1 instanceof Success) {
                  return true;
               } else {
                  if (x1 instanceof Failure) {
                     var3 = true;
                     var4 = (Failure)x1;
                     Throwable ie = var4.exception();
                     if (ie instanceof InterruptedException) {
                        return true;
                     }
                  }

                  if (var3) {
                     Throwable var7 = var4.exception();
                     if (var7 != null) {
                        Option var8 = scala.util.control.NonFatal..MODULE$.unapply(var7);
                        if (!var8.isEmpty()) {
                           return true;
                        }
                     }
                  }

                  return false;
               }
            }

            public {
               this.context$2 = context$2;
            }
         }, ThreadUtils$.MODULE$.sameThread());
      }

      public void onDisconnected(final RpcAddress address) {
         if (this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master().exists((x$4) -> BoxesRunTime.boxToBoolean($anonfun$onDisconnected$1(address, x$4)))) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connection to ", " failed; waiting for master to reconnect..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, address)})))));
            this.markDisconnected();
         }
      }

      public void onNetworkError(final Throwable cause, final RpcAddress address) {
         if (this.isPossibleMaster(address)) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not connect to ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, address)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, cause)}))))));
         }
      }

      public void markDisconnected() {
         if (!this.alreadyDisconnected()) {
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.disconnected();
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected_$eq(true);
         }
      }

      public void markDead(final String reason) {
         if (!this.alreadyDead().get()) {
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer().org$apache$spark$deploy$client$StandaloneAppClient$$listener.dead(reason);
            this.alreadyDead().set(true);
         }
      }

      public void onStop() {
         if (this.registrationRetryTimer().get() != null) {
            BoxesRunTime.boxToBoolean(((java.util.concurrent.Future)this.registrationRetryTimer().get()).cancel(true));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.registrationRetryThread().shutdownNow();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures().get()), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$onStop$1(x$5)));
         this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool().shutdownNow();
      }

      // $FF: synthetic method
      public StandaloneAppClient org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onDisconnected$1(final RpcAddress address$1, final RpcEndpointRef x$4) {
         boolean var3;
         label23: {
            RpcAddress var10000 = x$4.address();
            if (var10000 == null) {
               if (address$1 == null) {
                  break label23;
               }
            } else if (var10000.equals(address$1)) {
               break label23;
            }

            var3 = false;
            return var3;
         }

         var3 = true;
         return var3;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onStop$1(final java.util.concurrent.Future x$5) {
         return x$5.cancel(true);
      }

      public ClientEndpoint(final RpcEnv rpcEnv) {
         this.rpcEnv = rpcEnv;
         if (StandaloneAppClient.this == null) {
            throw null;
         } else {
            this.$outer = StandaloneAppClient.this;
            super();
            RpcEndpoint.$init$(this);
            Logging.$init$(this);
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$master = scala.None..MODULE$;
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$alreadyDisconnected = false;
            this.alreadyDead = new AtomicBoolean(false);
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterFutures = new AtomicReference();
            this.registrationRetryTimer = new AtomicReference();
            this.org$apache$spark$deploy$client$StandaloneAppClient$ClientEndpoint$$registerMasterThreadPool = ThreadUtils$.MODULE$.newDaemonCachedThreadPool("appclient-register-master-threadpool", StandaloneAppClient.this.org$apache$spark$deploy$client$StandaloneAppClient$$masterRpcAddresses().length, ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3());
            this.registrationRetryThread = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("appclient-registration-retry-thread");
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
