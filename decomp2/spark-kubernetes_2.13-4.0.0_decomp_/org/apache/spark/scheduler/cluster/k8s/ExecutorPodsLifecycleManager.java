package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.EditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.Nameable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.ExecutorFailureTracker;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.ExecutorExited;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.MapOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\t}d!B\u0017/\u0001QR\u0004\u0002C$\u0001\u0005\u000b\u0007I\u0011A%\t\u00119\u0003!\u0011!Q\u0001\n)C\u0001b\u0014\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\t9\u0002\u0011\t\u0011)A\u0005;\"A\u0011\r\u0001B\u0001B\u0003%!\rC\u0003i\u0001\u0011\u0005\u0011\u000e\u0003\u0005p\u0001!\u0015\r\u0011\"\u0003q\u0011!!\b\u0001#b\u0001\n\u0013)\b\u0002C=\u0001\u0011\u000b\u0007I\u0011\u0002>\t\u0011\u0005u\u0001\u00011A\u0005\nUD\u0011\"a\b\u0001\u0001\u0004%I!!\t\t\u000f\u00055\u0002\u0001)Q\u0005m\"I\u0011q\u0006\u0001C\u0002\u0013%\u0011\u0011\u0007\u0005\t\u0003\u0007\u0002\u0001\u0015!\u0003\u00024!I\u0011Q\t\u0001C\u0002\u0013%\u0011q\t\u0005\t\u0003?\u0002\u0001\u0015!\u0003\u0002J!I\u0011\u0011\r\u0001C\u0002\u0013%\u0011q\t\u0005\t\u0003G\u0002\u0001\u0015!\u0003\u0002J!I\u0011Q\r\u0001C\u0002\u0013E\u0011q\r\u0005\t\u0003_\u0002\u0001\u0015!\u0003\u0002j!I\u0011\u0011\u000f\u0001A\u0002\u0013%\u00111\u000f\u0005\n\u0003\u0003\u0003\u0001\u0019!C\u0005\u0003\u0007C\u0001\"a\"\u0001A\u0003&\u0011Q\u000f\u0005\n\u0003#\u0003!\u0019!C\t\u0003'C\u0001\"!)\u0001A\u0003%\u0011Q\u0013\u0005\t\u0003G\u0003A\u0011\u0003\u001b\u0002h!9\u0011Q\u0015\u0001\u0005\u0002\u0005\u001d\u0006\u0002CAZ\u0001\u0011\u0005a&!.\t\u000f\u0005m\u0006\u0001\"\u0003\u0002>\"9\u0011Q\u001c\u0001\u0005\n\u0005}\u0007bBA{\u0001\u0011%\u0011q\u001f\u0005\b\u0005\u001f\u0001A\u0011\u0002B\t\u0011\u001d\u0011I\u0002\u0001C\u0005\u00057AqA!\u000b\u0001\t\u0013\u0011Y\u0003C\u0004\u00034\u0001!IA!\u000e\t\u000f\te\u0002\u0001\"\u0003\u0003<\u001d9!\u0011\t\u0018\t\n\t\rcAB\u0017/\u0011\u0013\u0011)\u0005\u0003\u0004iM\u0011\u0005!q\t\u0005\n\u0005\u00132#\u0019!C\u0001\u0003OB\u0001Ba\u0013'A\u0003%\u0011\u0011\u000e\u0005\b\u0005\u001b2C\u0011\u0001B(\u0011\u001d\u0011)F\nC\u0001\u0005/B\u0011Ba\u001a'#\u0003%\tA!\u001b\u00039\u0015CXmY;u_J\u0004v\u000eZ:MS\u001a,7-_2mK6\u000bg.Y4fe*\u0011q\u0006M\u0001\u0004Wb\u001a(BA\u00193\u0003\u001d\u0019G.^:uKJT!a\r\u001b\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u001b7\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0004(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002s\u0005\u0019qN]4\u0014\u0007\u0001Y\u0014\t\u0005\u0002=\u007f5\tQHC\u0001?\u0003\u0015\u00198-\u00197b\u0013\t\u0001UH\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\tR\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\r\u000e\u0013q\u0001T8hO&tw-\u0001\u0003d_:47\u0001A\u000b\u0002\u0015B\u00111\nT\u0007\u0002i%\u0011Q\n\u000e\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\n\u0001c[;cKJtW\r^3t\u00072LWM\u001c;\u0011\u0005ESV\"\u0001*\u000b\u0005M#\u0016AB2mS\u0016tGO\u0003\u0002V-\u0006Q1.\u001e2fe:,G/Z:\u000b\u0005]C\u0016a\u00024bEJL7\r\u000f\u0006\u00023\u0006\u0011\u0011n\\\u0005\u00037J\u0013\u0001cS;cKJtW\r^3t\u00072LWM\u001c;\u0002\u001dMt\u0017\r]:i_R\u001c8\u000b^8sKB\u0011alX\u0007\u0002]%\u0011\u0001M\f\u0002\u001b\u000bb,7-\u001e;peB{Gm]*oCB\u001c\bn\u001c;t'R|'/Z\u0001\u0006G2|7m\u001b\t\u0003G\u001al\u0011\u0001\u001a\u0006\u0003KR\nA!\u001e;jY&\u0011q\r\u001a\u0002\u0006\u00072|7m[\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b)\\G.\u001c8\u0011\u0005y\u0003\u0001\"B$\u0007\u0001\u0004Q\u0005\"B(\u0007\u0001\u0004\u0001\u0006\"\u0002/\u0007\u0001\u0004i\u0006bB1\u0007!\u0003\u0005\rAY\u0001\u0016g\"|W\u000f\u001c3EK2,G/Z#yK\u000e,Ho\u001c:t+\u0005\t\bC\u0001\u001fs\u0013\t\u0019XHA\u0004C_>dW-\u00198\u0002+5L7o]5oOB{G\rR3uK\u000e$H)\u001a7uCV\ta\u000f\u0005\u0002=o&\u0011\u00010\u0010\u0002\u0005\u0019>tw-A\u000bsK6|g/\u001a3Fq\u0016\u001cW\u000f^8sg\u000e\u000b7\r[3\u0016\u0003m\u0004r\u0001`A\u0006\u0003\u001f\ty!D\u0001~\u0015\tqx0A\u0003dC\u000eDWM\u0003\u0003\u0002\u0002\u0005\r\u0011AB2p[6|gN\u0003\u0003\u0002\u0006\u0005\u001d\u0011AB4p_\u001edWM\u0003\u0002\u0002\n\u0005\u00191m\\7\n\u0007\u00055QPA\u0003DC\u000eDW\r\u0005\u0003\u0002\u0012\u0005mQBAA\n\u0015\u0011\t)\"a\u0006\u0002\t1\fgn\u001a\u0006\u0003\u00033\tAA[1wC&\u0019\u00010a\u0005\u0002%1\f7\u000f\u001e$vY2\u001cf.\u00199tQ>$Hk]\u0001\u0017Y\u0006\u001cHOR;mYNs\u0017\r]:i_R$6o\u0018\u0013fcR!\u00111EA\u0015!\ra\u0014QE\u0005\u0004\u0003Oi$\u0001B+oSRD\u0001\"a\u000b\f\u0003\u0003\u0005\rA^\u0001\u0004q\u0012\n\u0014a\u00057bgR4U\u000f\u001c7T]\u0006\u00048\u000f[8u)N\u0004\u0013aD5oC\u000e$\u0018N^1uK\u0012\u0004v\u000eZ:\u0016\u0005\u0005M\u0002#BA\u001b\u0003\u007f1XBAA\u001c\u0015\u0011\tI$a\u000f\u0002\u000f5,H/\u00192mK*\u0019\u0011QH\u001f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002B\u0005]\"a\u0002%bg\"\u001cV\r^\u0001\u0011S:\f7\r^5wCR,G\rU8eg\u0002\n\u0011B\\1nKN\u0004\u0018mY3\u0016\u0005\u0005%\u0003\u0003BA&\u00033rA!!\u0014\u0002VA\u0019\u0011qJ\u001f\u000e\u0005\u0005E#bAA*\u0011\u00061AH]8pizJ1!a\u0016>\u0003\u0019\u0001&/\u001a3fM&!\u00111LA/\u0005\u0019\u0019FO]5oO*\u0019\u0011qK\u001f\u0002\u00159\fW.Z:qC\u000e,\u0007%\u0001\nta\u0006\u00148nQ8oi\u0006Lg.\u001a:OC6,\u0017aE:qCJ\\7i\u001c8uC&tWM\u001d(b[\u0016\u0004\u0013AF7bq:+X.\u0012=fGV$xN\u001d$bS2,(/Z:\u0016\u0005\u0005%\u0004c\u0001\u001f\u0002l%\u0019\u0011QN\u001f\u0003\u0007%sG/A\fnCbtU/\\#yK\u000e,Ho\u001c:GC&dWO]3tA\u0005\tb-Y5mK\u0012,\u00050Z2vi>\u0014\u0018\nZ:\u0016\u0005\u0005U\u0004#BA<\u0003{2XBAA=\u0015\u0011\tY(a\u000f\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BA@\u0003s\u00121aU3u\u0003U1\u0017-\u001b7fI\u0016CXmY;u_JLEm]0%KF$B!a\t\u0002\u0006\"I\u00111\u0006\f\u0002\u0002\u0003\u0007\u0011QO\u0001\u0013M\u0006LG.\u001a3Fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\b\u0005K\u0002\u0018\u0003\u0017\u00032\u0001PAG\u0013\r\ty)\u0010\u0002\tm>d\u0017\r^5mK\u0006qa-Y5mkJ,GK]1dW\u0016\u0014XCAAK!\u0011\t9*!(\u000e\u0005\u0005e%bAANi\u00051A-\u001a9m_fLA!a(\u0002\u001a\n1R\t_3dkR|'OR1jYV\u0014X\r\u0016:bG.,'/A\bgC&dWO]3Ue\u0006\u001c7.\u001a:!\u0003U9W\r\u001e(v[\u0016CXmY;u_J\u001ch)Y5mK\u0012\fQa\u001d;beR$B!a\t\u0002*\"9\u00111V\u000eA\u0002\u00055\u0016\u0001E:dQ\u0016$W\u000f\\3s\u0005\u0006\u001c7.\u001a8e!\rq\u0016qV\u0005\u0004\u0003cs#!I&vE\u0016\u0014h.\u001a;fg\u000ecWo\u001d;feN\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$\u0017aD:u_B\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8\u0015\t\u0005\r\u0012q\u0017\u0005\b\u0003sc\u0002\u0019AA5\u0003!)\u00070\u001b;D_\u0012,\u0017AD8o\u001d\u0016<8K\\1qg\"|Go\u001d\u000b\u0007\u0003G\ty,!1\t\u000f\u0005-V\u00041\u0001\u0002.\"9\u00111Y\u000fA\u0002\u0005\u0015\u0017!C:oCB\u001c\bn\u001c;t!\u0019\t9-!5\u0002X:!\u0011\u0011ZAg\u001d\u0011\ty%a3\n\u0003yJ1!a4>\u0003\u001d\u0001\u0018mY6bO\u0016LA!a5\u0002V\n\u00191+Z9\u000b\u0007\u0005=W\bE\u0002_\u00033L1!a7/\u0005Q)\u00050Z2vi>\u0014\bk\u001c3t':\f\u0007o\u001d5pi\u00061rN\u001c$j]\u0006dgj\u001c8EK2,G/\u001a3Ti\u0006$X\rF\u0005r\u0003C\fY/a<\u0002r\"9\u00111\u001d\u0010A\u0002\u0005\u0015\u0018\u0001\u00039pIN#\u0018\r^3\u0011\u0007y\u000b9/C\u0002\u0002j:\u0012QBR5oC2\u0004v\u000eZ*uCR,\u0007BBAw=\u0001\u0007a/\u0001\u0004fq\u0016\u001c\u0017\n\u001a\u0005\b\u0003Ws\u0002\u0019AAW\u0011\u0019\t\u0019P\ba\u0001c\u0006iA-\u001a7fi\u00164%o\\7LqM\fQC]3n_Z,W\t_3dkR|'O\u0012:p[.C4\u000f\u0006\u0004\u0002$\u0005e\u00181 \u0005\u0007\u0003[|\u0002\u0019\u0001<\t\u000f\u0005ux\u00041\u0001\u0002\u0000\u0006QQ\u000f\u001d3bi\u0016$\u0007k\u001c3\u0011\t\t\u0005!1B\u0007\u0003\u0005\u0007QAA!\u0002\u0003\b\u0005)Qn\u001c3fY*\u0019!\u0011\u0002+\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0003\u000e\t\r!a\u0001)pI\u00069\"/Z7pm\u0016,\u00050Z2vi>\u0014hI]8n'B\f'o\u001b\u000b\bc\nM!Q\u0003B\f\u0011\u001d\tY\u000b\ta\u0001\u0003[Cq!a9!\u0001\u0004\t)\u000f\u0003\u0004\u0002n\u0002\u0002\rA^\u0001\u000fM&tG-\u0012=jiJ+\u0017m]8o)\u0019\u0011iB!\n\u0003(A!!q\u0004B\u0011\u001b\u0005\u0011\u0014b\u0001B\u0012e\tqQ\t_3dkR|'/\u0012=ji\u0016$\u0007bBArC\u0001\u0007\u0011Q\u001d\u0005\u0007\u0003[\f\u0003\u0019\u0001<\u0002#\u0015D\u0018\u000e\u001e*fCN|g.T3tg\u0006<W\r\u0006\u0005\u0002J\t5\"q\u0006B\u0019\u0011\u001d\t\u0019O\ta\u0001\u0003KDa!!<#\u0001\u00041\bbBA]E\u0001\u0007\u0011\u0011N\u0001\rM&tG-\u0012=ji\u000e{G-\u001a\u000b\u0005\u0003S\u00129\u0004C\u0004\u0002d\u000e\u0002\r!!:\u0002\u001b%\u001c\bk\u001c3J]\u0006\u001cG/\u001b<f)\r\t(Q\b\u0005\b\u0005\u007f!\u0003\u0019AA\u0000\u0003\r\u0001x\u000eZ\u0001\u001d\u000bb,7-\u001e;peB{Gm\u001d'jM\u0016\u001c\u0017p\u00197f\u001b\u0006t\u0017mZ3s!\tqfe\u0005\u0002'wQ\u0011!1I\u0001\u0012+:[ejT,O?\u0016C\u0016\nV0D\u001f\u0012+\u0015AE+O\u0017:{uKT0F1&#vlQ(E\u000b\u0002\n\u0001\u0003Z3tGJL'-Z#ySR\u001cu\u000eZ3\u0015\t\u0005%#\u0011\u000b\u0005\b\u0005'R\u0003\u0019AA5\u0003\u0011\u0019w\u000eZ3\u0002-\u0015DXmY;u_JLe.Y2uSZ\fG/[8o\r:,\"A!\u0017\u0011\r\tm#1MA\u0000\u001b\t\u0011iF\u0003\u0003\u0003`\t\u0005\u0014\u0001\u00034v]\u000e$\u0018n\u001c8\u000b\u0007\u0015\f9\"\u0003\u0003\u0003f\tu#!D+oCJLx\n]3sCR|'/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u000b\u0003\u0005WR3A\u0019B7W\t\u0011y\u0007\u0005\u0003\u0003r\tmTB\u0001B:\u0015\u0011\u0011)Ha\u001e\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B={\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tu$1\u000f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class ExecutorPodsLifecycleManager implements Logging {
   private boolean shouldDeleteExecutors;
   private long missingPodDetectDelta;
   private Cache removedExecutorsCache;
   private final SparkConf conf;
   private final KubernetesClient kubernetesClient;
   private final ExecutorPodsSnapshotsStore snapshotsStore;
   private long lastFullSnapshotTs;
   private final HashSet inactivatedPods;
   private final String namespace;
   private final String sparkContainerName;
   private final int maxNumExecutorFailures;
   private volatile Set failedExecutorIds;
   private final ExecutorFailureTracker failureTracker;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

   public static Clock $lessinit$greater$default$4() {
      return ExecutorPodsLifecycleManager$.MODULE$.$lessinit$greater$default$4();
   }

   public static UnaryOperator executorInactivationFn() {
      return ExecutorPodsLifecycleManager$.MODULE$.executorInactivationFn();
   }

   public static String describeExitCode(final int code) {
      return ExecutorPodsLifecycleManager$.MODULE$.describeExitCode(code);
   }

   public static int UNKNOWN_EXIT_CODE() {
      return ExecutorPodsLifecycleManager$.MODULE$.UNKNOWN_EXIT_CODE();
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

   public SparkConf conf() {
      return this.conf;
   }

   private boolean shouldDeleteExecutors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.shouldDeleteExecutors = BoxesRunTime.unboxToBoolean(this.conf().get(Config$.MODULE$.KUBERNETES_DELETE_EXECUTORS()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.shouldDeleteExecutors;
   }

   private boolean shouldDeleteExecutors() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.shouldDeleteExecutors$lzycompute() : this.shouldDeleteExecutors;
   }

   private long missingPodDetectDelta$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.missingPodDetectDelta = BoxesRunTime.unboxToLong(this.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_MISSING_POD_DETECT_DELTA()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.missingPodDetectDelta;
   }

   private long missingPodDetectDelta() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.missingPodDetectDelta$lzycompute() : this.missingPodDetectDelta;
   }

   private Cache removedExecutorsCache$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.removedExecutorsCache = CacheBuilder.newBuilder().expireAfterWrite(3L, TimeUnit.MINUTES).build();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.removedExecutorsCache;
   }

   private Cache removedExecutorsCache() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.removedExecutorsCache$lzycompute() : this.removedExecutorsCache;
   }

   private long lastFullSnapshotTs() {
      return this.lastFullSnapshotTs;
   }

   private void lastFullSnapshotTs_$eq(final long x$1) {
      this.lastFullSnapshotTs = x$1;
   }

   private HashSet inactivatedPods() {
      return this.inactivatedPods;
   }

   private String namespace() {
      return this.namespace;
   }

   private String sparkContainerName() {
      return this.sparkContainerName;
   }

   public int maxNumExecutorFailures() {
      return this.maxNumExecutorFailures;
   }

   private Set failedExecutorIds() {
      return this.failedExecutorIds;
   }

   private void failedExecutorIds_$eq(final Set x$1) {
      this.failedExecutorIds = x$1;
   }

   public ExecutorFailureTracker failureTracker() {
      return this.failureTracker;
   }

   public int getNumExecutorsFailed() {
      return this.failureTracker().numFailedExecutors();
   }

   public void start(final KubernetesClusterSchedulerBackend schedulerBackend) {
      long eventProcessingInterval = BoxesRunTime.unboxToLong(this.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_EVENT_PROCESSING_INTERVAL()));
      this.snapshotsStore.addSubscriber(eventProcessingInterval, (executorPodsSnapshot) -> {
         $anonfun$start$1(this, schedulerBackend, executorPodsSnapshot);
         return BoxedUnit.UNIT;
      });
   }

   public void stopApplication(final int exitCode) {
      throw .MODULE$.exit(exitCode);
   }

   private void onNewSnapshots(final KubernetesClusterSchedulerBackend schedulerBackend, final Seq snapshots) {
      HashSet execIdsRemovedInThisRound = scala.collection.mutable.HashSet..MODULE$.empty();
      snapshots.foreach((snapshot) -> {
         $anonfun$onNewSnapshots$1(this, schedulerBackend, execIdsRemovedInThisRound, snapshot);
         return BoxedUnit.UNIT;
      });
      if (this.inactivatedPods().nonEmpty() && snapshots.nonEmpty()) {
         this.inactivatedPods().filterInPlace((JFunction1.mcZJ.sp)(x$2) -> ((ExecutorPodsSnapshot)snapshots.last()).executorPods().contains(BoxesRunTime.boxToLong(x$2)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (snapshots.nonEmpty() && this.lastFullSnapshotTs() != ((ExecutorPodsSnapshot)snapshots.last()).fullSnapshotTs()) {
         this.lastFullSnapshotTs_$eq(((ExecutorPodsSnapshot)snapshots.last()).fullSnapshotTs());
         scala.collection.immutable.Map lostExecutorsWithRegistrationTs = (scala.collection.immutable.Map)((MapOps)schedulerBackend.getExecutorsWithRegistrationTs().map((t) -> new Tuple2.mcJJ.sp(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString((String)t._1())), t._2$mcJ$sp()))).$minus$minus(((ExecutorPodsSnapshot)snapshots.last()).executorPods().keySet()).$minus$minus(execIdsRemovedInThisRound);
         lostExecutorsWithRegistrationTs.foreach((x0$3) -> {
            $anonfun$onNewSnapshots$12(this, schedulerBackend, x0$3);
            return BoxedUnit.UNIT;
         });
      }
   }

   private boolean onFinalNonDeletedState(final FinalPodState podState, final long execId, final KubernetesClusterSchedulerBackend schedulerBackend, final boolean deleteFromK8s) {
      boolean deleted = this.removeExecutorFromSpark(schedulerBackend, podState, execId);
      if (deleteFromK8s) {
         this.removeExecutorFromK8s(execId, podState.pod());
      }

      return deleted;
   }

   private void removeExecutorFromK8s(final long execId, final Pod updatedPod) {
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
         if (this.shouldDeleteExecutors()) {
            PodResource podToDelete = (PodResource)((Nameable)this.kubernetesClient.pods().inNamespace(this.namespace())).withName(updatedPod.getMetadata().getName());
            if (podToDelete.get() != null) {
               podToDelete.delete();
            }
         } else if (!this.inactivatedPods().contains(BoxesRunTime.boxToLong(execId)) && !this.isPodInactive(updatedPod)) {
            this.logDebug((Function0)(() -> "Marking executor " + updatedPod.getMetadata().getName() + " as inactive since deletion is disabled."));
            ((EditReplacePatchable)((Nameable)this.kubernetesClient.pods().inNamespace(this.namespace())).withName(updatedPod.getMetadata().getName())).edit(ExecutorPodsLifecycleManager$.MODULE$.executorInactivationFn());
            this.inactivatedPods().$plus$eq(BoxesRunTime.boxToLong(execId));
         }
      });
   }

   private boolean removeExecutorFromSpark(final KubernetesClusterSchedulerBackend schedulerBackend, final FinalPodState podState, final long execId) {
      if (this.removedExecutorsCache().getIfPresent(BoxesRunTime.boxToLong(execId)) == null) {
         this.removedExecutorsCache().put(scala.Predef..MODULE$.long2Long(execId), scala.Predef..MODULE$.long2Long(execId));
         ExecutorExited exitReason = this.findExitReason(podState, execId);
         schedulerBackend.doRemoveExecutor(Long.toString(execId), exitReason);
         return true;
      } else {
         return false;
      }
   }

   private ExecutorExited findExitReason(final FinalPodState podState, final long execId) {
      int exitCode = this.findExitCode(podState);
      Tuple2 var10000;
      if (podState instanceof PodDeleted) {
         var10000 = new Tuple2(BoxesRunTime.boxToBoolean(false), "The executor with id " + execId + " was deleted by a user or the framework.");
      } else {
         String msg = this.exitReasonMessage(podState, execId, exitCode);
         var10000 = new Tuple2(BoxesRunTime.boxToBoolean(true), msg);
      }

      Tuple2 var8 = var10000;
      if (var8 != null) {
         boolean exitCausedByApp = var8._1$mcZ$sp();
         String exitMessage = (String)var8._2();
         Tuple2 var7 = new Tuple2(BoxesRunTime.boxToBoolean(exitCausedByApp), exitMessage);
         boolean exitCausedByApp = var7._1$mcZ$sp();
         String exitMessage = (String)var7._2();
         return new ExecutorExited(exitCode, exitCausedByApp, exitMessage);
      } else {
         throw new MatchError(var8);
      }
   }

   private String exitReasonMessage(final FinalPodState podState, final long execId, final int exitCode) {
      Pod pod = podState.pod();
      Option reason = scala.Option..MODULE$.apply(pod.getStatus().getReason());
      Option message = scala.Option..MODULE$.apply(pod.getStatus().getMessage());
      String explained = ExecutorPodsLifecycleManager$.MODULE$.describeExitCode(exitCode);
      String exitMsg = "The executor with id " + execId + " exited with exit code " + explained + ".";
      Option reasonStr = reason.map((r) -> "The API gave the following brief reason: " + r);
      Option msgStr = message.map((m) -> "The API gave the following message: " + m);
      return scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n       |" + exitMsg + "\n       |" + reasonStr.getOrElse(() -> "") + "\n       |" + msgStr.getOrElse(() -> "") + "\n       |\n       |The API gave the following container statuses:\n       |\n       |" + KubernetesUtils$.MODULE$.containersDescription(pod, KubernetesUtils$.MODULE$.containersDescription$default$2()) + "\n      "));
   }

   private int findExitCode(final FinalPodState podState) {
      return BoxesRunTime.unboxToInt(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(podState.pod().getStatus().getContainerStatuses()).asScala().find((containerStatus) -> BoxesRunTime.boxToBoolean($anonfun$findExitCode$1(this, containerStatus))).map((terminatedContainer) -> BoxesRunTime.boxToInteger($anonfun$findExitCode$2(terminatedContainer))).getOrElse((JFunction0.mcI.sp)() -> ExecutorPodsLifecycleManager$.MODULE$.UNKNOWN_EXIT_CODE()));
   }

   private boolean isPodInactive(final Pod pod) {
      boolean var3;
      label23: {
         Object var10000 = pod.getMetadata().getLabels().get(Constants$.MODULE$.SPARK_EXECUTOR_INACTIVE_LABEL());
         String var2 = "true";
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

   // $FF: synthetic method
   public static final void $anonfun$start$1(final ExecutorPodsLifecycleManager $this, final KubernetesClusterSchedulerBackend schedulerBackend$1, final Seq executorPodsSnapshot) {
      $this.onNewSnapshots(schedulerBackend$1, executorPodsSnapshot);
      if ($this.failureTracker().numFailedExecutors() > $this.maxNumExecutorFailures()) {
         $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Max number of executor failures "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") reached"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_EXECUTOR_FAILURES..MODULE$, BoxesRunTime.boxToInteger($this.maxNumExecutorFailures()))}))))));
         $this.stopApplication(org.apache.spark.util.SparkExitCode..MODULE$.EXCEED_MAX_EXECUTOR_FAILURES());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$2(final Tuple2 x0$1) {
      if (x0$1 != null) {
         ExecutorPodState var3 = (ExecutorPodState)x0$1._2();
         if (var3 instanceof PodFailed) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$onNewSnapshots$1(final ExecutorPodsLifecycleManager $this, final KubernetesClusterSchedulerBackend schedulerBackend$2, final HashSet execIdsRemovedInThisRound$1, final ExecutorPodsSnapshot snapshot) {
      Set currentFailedExecutorIds = ((MapOps)snapshot.executorPods().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$2(x0$1)))).keySet();
      Set newFailedExecutorIds = (Set)currentFailedExecutorIds.$minus$minus($this.failedExecutorIds());
      if (newFailedExecutorIds.nonEmpty()) {
         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " new failed executors."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(newFailedExecutorIds.size()))})))));
         newFailedExecutorIds.foreach((JFunction1.mcVJ.sp)(x$1) -> $this.failureTracker().registerExecutorFailure());
      }

      $this.failedExecutorIds_$eq((Set)$this.failedExecutorIds().$plus$plus(currentFailedExecutorIds));
      snapshot.executorPods().foreach((x0$2) -> {
         if (x0$2 != null) {
            long execId = x0$2._1$mcJ$sp();
            ExecutorPodState state = (ExecutorPodState)x0$2._2();
            if ($this.isPodInactive(state.pod())) {
               return $this.inactivatedPods().$minus$eq(BoxesRunTime.boxToLong(execId));
            } else if (state instanceof PodDeleted) {
               PodDeleted var11 = (PodDeleted)state;
               if ($this.removeExecutorFromSpark(schedulerBackend$2, var11, execId)) {
                  execIdsRemovedInThisRound$1.$plus$eq(BoxesRunTime.boxToLong(execId));
                  $this.logDebug((Function0)(() -> "Snapshot reported deleted executor with id " + execId + ", pod name " + state.pod().getMetadata().getName()));
               }

               return $this.inactivatedPods().$minus$eq(BoxesRunTime.boxToLong(execId));
            } else if (state instanceof PodFailed) {
               PodFailed var12 = (PodFailed)state;
               boolean deleteFromK8s = !execIdsRemovedInThisRound$1.contains(BoxesRunTime.boxToLong(execId));
               if ($this.onFinalNonDeletedState(var12, execId, schedulerBackend$2, deleteFromK8s)) {
                  execIdsRemovedInThisRound$1.$plus$eq(BoxesRunTime.boxToLong(execId));
                  $this.logDebug((Function0)(() -> "Snapshot reported failed executor with id " + execId + ", pod name " + state.pod().getMetadata().getName()));
                  return BoxedUnit.UNIT;
               } else {
                  return BoxedUnit.UNIT;
               }
            } else if (state instanceof PodSucceeded) {
               PodSucceeded var14 = (PodSucceeded)state;
               boolean deleteFromK8s = !execIdsRemovedInThisRound$1.contains(BoxesRunTime.boxToLong(execId));
               if ($this.onFinalNonDeletedState(var14, execId, schedulerBackend$2, deleteFromK8s)) {
                  execIdsRemovedInThisRound$1.$plus$eq(BoxesRunTime.boxToLong(execId));
                  if (schedulerBackend$2.isExecutorActive(Long.toString(execId))) {
                     $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Snapshot reported succeeded executor with id "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", even though the application has not "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, BoxesRunTime.boxToLong(execId))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requested for it to be removed."})))).log(scala.collection.immutable.Nil..MODULE$))));
                     return BoxedUnit.UNIT;
                  } else {
                     $this.logDebug((Function0)(() -> "Snapshot reported succeeded executor with id " + execId + ", pod name " + state.pod().getMetadata().getName() + "."));
                     return BoxedUnit.UNIT;
                  }
               } else {
                  return BoxedUnit.UNIT;
               }
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onNewSnapshots$12(final ExecutorPodsLifecycleManager $this, final KubernetesClusterSchedulerBackend schedulerBackend$2, final Tuple2 x0$3) {
      if (x0$3 != null) {
         long lostExecId = x0$3._1$mcJ$sp();
         long lostExecRegistrationTs = x0$3._2$mcJ$sp();
         if ($this.removedExecutorsCache().getIfPresent(BoxesRunTime.boxToLong(lostExecId)) == null && $this.lastFullSnapshotTs() - lostExecRegistrationTs > $this.missingPodDetectDelta()) {
            String exitReasonMessage = "The executor with ID " + lostExecId + " (registered at " + lostExecRegistrationTs + " ms) was not found in the cluster at the polling time (" + $this.lastFullSnapshotTs() + " ms) which is after the accepted detect delta time (" + $this.missingPodDetectDelta() + " ms) configured by `" + Config$.MODULE$.KUBERNETES_EXECUTOR_MISSING_POD_DETECT_DELTA().key() + "`. The executor may have been deleted but the driver missed the deletion event. Marking this executor as failed.";
            $this.logDebug((Function0)(() -> exitReasonMessage));
            ExecutorExited exitReason = new ExecutorExited(ExecutorPodsLifecycleManager$.MODULE$.UNKNOWN_EXIT_CODE(), false, exitReasonMessage);
            schedulerBackend$2.doRemoveExecutor(Long.toString(lostExecId), exitReason);
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findExitCode$1(final ExecutorPodsLifecycleManager $this, final ContainerStatus containerStatus) {
      boolean var3;
      label18: {
         String var10000 = containerStatus.getName();
         String var2 = $this.sparkContainerName();
         if (var10000 == null) {
            if (var2 != null) {
               break label18;
            }
         } else if (!var10000.equals(var2)) {
            break label18;
         }

         if (containerStatus.getState().getTerminated() != null) {
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   // $FF: synthetic method
   public static final int $anonfun$findExitCode$2(final ContainerStatus terminatedContainer) {
      return scala.Predef..MODULE$.Integer2int(terminatedContainer.getState().getTerminated().getExitCode());
   }

   public ExecutorPodsLifecycleManager(final SparkConf conf, final KubernetesClient kubernetesClient, final ExecutorPodsSnapshotsStore snapshotsStore, final Clock clock) {
      this.conf = conf;
      this.kubernetesClient = kubernetesClient;
      this.snapshotsStore = snapshotsStore;
      Logging.$init$(this);
      this.lastFullSnapshotTs = 0L;
      this.inactivatedPods = scala.collection.mutable.HashSet..MODULE$.empty();
      this.namespace = (String)conf.get(Config$.MODULE$.KUBERNETES_NAMESPACE());
      this.sparkContainerName = (String)((Option)conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME())).getOrElse(() -> Constants$.MODULE$.DEFAULT_EXECUTOR_CONTAINER_NAME());
      this.maxNumExecutorFailures = org.apache.spark.deploy.ExecutorFailureTracker..MODULE$.maxNumExecutorFailures(conf);
      this.failedExecutorIds = scala.Predef..MODULE$.Set().empty();
      this.failureTracker = new ExecutorFailureTracker(conf, clock);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
