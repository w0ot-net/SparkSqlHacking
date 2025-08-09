package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.AnyNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.CreateOrReplaceable;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.EditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.Filterable;
import io.fabric8.kubernetes.client.dsl.Nameable;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf$;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.submit.KubernetesClientUtils$;
import org.apache.spark.deploy.security.HadoopDelegationTokenManager;
import org.apache.spark.internal.MDC;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.scheduler.ExecutorDecommission;
import org.apache.spark.scheduler.ExecutorLossReason;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.scheduler.cluster.CoarseGrainedSchedulerBackend;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t-f!\u0002\u001a4\u0001ez\u0004\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011%\u0003!\u0011!Q\u0001\n)C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0014\u0005\t7\u0002\u0011\t\u0011)A\u00059\"Aa\r\u0001B\u0001B\u0003%q\r\u0003\u0005l\u0001\t\u0005\t\u0015!\u0003m\u0011!y\u0007A!A!\u0002\u0013\u0001\b\u0002C:\u0001\u0005\u0003\u0005\u000b\u0011\u0002;\t\u0011]\u0004!\u0011!Q\u0001\naDQa\u001f\u0001\u0005\u0002qD\u0011\"a\u0004\u0001\u0005\u0004%I!!\u0005\t\u0011\u00055\u0002\u0001)A\u0005\u0003'A\u0011\"a\f\u0001\u0005\u0004%\t&!\r\t\u0011\u0005m\u0002\u0001)A\u0005\u0003gA\u0011\"!\u0010\u0001\u0005\u0004%I!a\u0010\t\u0011\u0005\u001d\u0003\u0001)A\u0005\u0003\u0003B\u0011\"!\u0013\u0001\u0005\u0004%I!a\u0013\t\u0011\u0005M\u0003\u0001)A\u0005\u0003\u001bB\u0011\"!\u0016\u0001\u0005\u0004%I!a\u0013\t\u0011\u0005]\u0003\u0001)A\u0005\u0003\u001bB\u0011\"!\u0017\u0001\u0005\u0004%I!a\u0017\t\u0011\u0005%\u0004\u0001)A\u0005\u0003;B\u0011\"a\u001b\u0001\u0005\u0004%I!!\u0005\t\u0011\u00055\u0004\u0001)A\u0005\u0003'A\u0001\"a\u001c\u0001\t\u0003\u0019\u0014\u0011\u000f\u0005\b\u0003\u000f\u0003A\u0011BAE\u0011\u001d\t)\u000b\u0001C!\u0003OCq!!+\u0001\t\u0003\nY\u000bC\u0004\u0002.\u0002!\t%a+\t\u000f\u0005=\u0006\u0001\"\u0011\u00022\"9\u0011q\u0019\u0001\u0005B\u0005%\u0007bBAf\u0001\u0011\u0005\u0013Q\u001a\u0005\b\u0003C\u0004A\u0011BAr\u0011\u001d\tI\u000f\u0001C!\u0003WDqAa\u0003\u0001\t\u0003\u0012i\u0001C\u0004\u0003\u0014\u0001!\tE!\u0006\t\u0013\t}\u0001A1A\u0005\u0002\t\u0005\u0002\u0002\u0003B\u0018\u0001\u0001\u0006IAa\t\t\u000f\tE\u0002\u0001\"\u0015\u00034!9!q\t\u0001\u0005R\t%cA\u0002B)\u0001\u0011\u0011\u0019\u0006\u0003\u0004|S\u0011\u0005!Q\u000b\u0005\n\u00053J#\u0019!C\t\u00057B\u0001B!\u001f*A\u0003%!Q\f\u0005\b\u0005wJC\u0011\u0002B?\u0011\u001d\u0011)*\u000bC\u0005\u0005/CqA!'*\t\u0003\u0012Y\nC\u0004\u0003 &\"\tE!)\t\u001d\t\u001d\u0006\u0001%A\u0002\u0002\u0003%I!a+\u0003*\n\t3*\u001e2fe:,G/Z:DYV\u001cH/\u001a:TG\",G-\u001e7fe\n\u000b7m[3oI*\u0011A'N\u0001\u0004Wb\u001a(B\u0001\u001c8\u0003\u001d\u0019G.^:uKJT!\u0001O\u001d\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u001e<\u0003\u0015\u0019\b/\u0019:l\u0015\taT(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002}\u0005\u0019qN]4\u0014\u0005\u0001\u0001\u0005CA!C\u001b\u0005)\u0014BA\"6\u0005u\u0019u.\u0019:tK\u001e\u0013\u0018-\u001b8fIN\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$7\u0001\u0001\t\u0003\r\u001ek\u0011aN\u0005\u0003\u0011^\u0012\u0011\u0003V1tWN\u001b\u0007.\u001a3vY\u0016\u0014\u0018*\u001c9m\u0003\t\u00198\r\u0005\u0002L\u00196\t\u0011(\u0003\u0002Ns\ta1\u000b]1sW\u000e{g\u000e^3yi\u0006\u00012.\u001e2fe:,G/Z:DY&,g\u000e\u001e\t\u0003!fk\u0011!\u0015\u0006\u0003%N\u000baa\u00197jK:$(B\u0001+V\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003-^\u000bqAZ1ce&\u001c\u0007HC\u0001Y\u0003\tIw.\u0003\u0002[#\n\u00012*\u001e2fe:,G/Z:DY&,g\u000e^\u0001\u0010Kb,7-\u001e;peN+'O^5dKB\u0011Q\fZ\u0007\u0002=*\u0011q\fY\u0001\u000bG>t7-\u001e:sK:$(BA1c\u0003\u0011)H/\u001b7\u000b\u0003\r\fAA[1wC&\u0011QM\u0018\u0002\u0019'\u000eDW\rZ;mK\u0012,\u00050Z2vi>\u00148+\u001a:wS\u000e,\u0017AD:oCB\u001c\bn\u001c;t'R|'/\u001a\t\u0003Q&l\u0011aM\u0005\u0003UN\u0012!$\u0012=fGV$xN\u001d)pIN\u001cf.\u00199tQ>$8o\u0015;pe\u0016\fA\u0002]8e\u00032dwnY1u_J\u0004\"\u0001[7\n\u00059\u001c$!F!cgR\u0014\u0018m\u0019;Q_\u0012\u001c\u0018\t\u001c7pG\u0006$xN]\u0001\u0016Y&4WmY=dY\u0016,e/\u001a8u\u0011\u0006tG\r\\3s!\tA\u0017/\u0003\u0002sg\taR\t_3dkR|'\u000fU8eg2Kg-Z2zG2,W*\u00198bO\u0016\u0014\u0018aC<bi\u000eDWI^3oiN\u0004\"\u0001[;\n\u0005Y\u001c$aH#yK\u000e,Ho\u001c:Q_\u0012\u001cx+\u0019;dQNs\u0017\r]:i_R\u001cv.\u001e:dK\u0006Q\u0001o\u001c7m\u000bZ,g\u000e^:\u0011\u0005!L\u0018B\u0001>4\u0005\u0005*\u00050Z2vi>\u0014\bk\u001c3t!>dG.\u001b8h':\f\u0007o\u001d5piN{WO]2f\u0003\u0019a\u0014N\\5u}Q\tRP`@\u0002\u0002\u0005\r\u0011QAA\u0004\u0003\u0013\tY!!\u0004\u0011\u0005!\u0004\u0001\"\u0002\u001d\u000b\u0001\u0004)\u0005\"B%\u000b\u0001\u0004Q\u0005\"\u0002(\u000b\u0001\u0004y\u0005\"B.\u000b\u0001\u0004a\u0006\"\u00024\u000b\u0001\u00049\u0007\"B6\u000b\u0001\u0004a\u0007\"B8\u000b\u0001\u0004\u0001\b\"B:\u000b\u0001\u0004!\b\"B<\u000b\u0001\u0004A\u0018!B1qa&#WCAA\n!\u0011\t)\"a\n\u000f\t\u0005]\u00111\u0005\t\u0005\u00033\ty\"\u0004\u0002\u0002\u001c)\u0019\u0011Q\u0004#\u0002\rq\u0012xn\u001c;?\u0015\t\t\t#A\u0003tG\u0006d\u0017-\u0003\u0003\u0002&\u0005}\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002*\u0005-\"AB*ue&twM\u0003\u0003\u0002&\u0005}\u0011AB1qa&#\u0007%\u0001\nnS:\u0014VmZ5ti\u0016\u0014X\r\u001a*bi&|WCAA\u001a!\u0011\t)$a\u000e\u000e\u0005\u0005}\u0011\u0002BA\u001d\u0003?\u0011a\u0001R8vE2,\u0017aE7j]J+w-[:uKJ,GMU1uS>\u0004\u0013\u0001E5oSRL\u0017\r\\#yK\u000e,Ho\u001c:t+\t\t\t\u0005\u0005\u0003\u00026\u0005\r\u0013\u0002BA#\u0003?\u00111!\u00138u\u0003EIg.\u001b;jC2,\u00050Z2vi>\u00148\u000fI\u0001\u001ag\"|W\u000f\u001c3EK2,G/\u001a#sSZ,'oU3sm&\u001cW-\u0006\u0002\u0002NA!\u0011QGA(\u0013\u0011\t\t&a\b\u0003\u000f\t{w\u000e\\3b]\u0006Q2\u000f[8vY\u0012$U\r\\3uK\u0012\u0013\u0018N^3s'\u0016\u0014h/[2fA\u0005)2\u000f[8vY\u0012$U\r\\3uK\u0016CXmY;u_J\u001c\u0018AF:i_VdG\rR3mKR,W\t_3dkR|'o\u001d\u0011\u0002\u001d\u0011,g-Y;miB\u0013xNZ5mKV\u0011\u0011Q\f\t\u0005\u0003?\n)'\u0004\u0002\u0002b)\u0019\u00111M\u001d\u0002\u0011I,7o\\;sG\u0016LA!a\u001a\u0002b\ty!+Z:pkJ\u001cW\r\u0015:pM&dW-A\beK\u001a\fW\u000f\u001c;Qe>4\u0017\u000e\\3!\u0003%q\u0017-\\3ta\u0006\u001cW-\u0001\u0006oC6,7\u000f]1dK\u0002\n\u0001\u0003Z8SK6|g/Z#yK\u000e,Ho\u001c:\u0015\r\u0005M\u0014\u0011PA?!\u0011\t)$!\u001e\n\t\u0005]\u0014q\u0004\u0002\u0005+:LG\u000fC\u0004\u0002|e\u0001\r!a\u0005\u0002\u0015\u0015DXmY;u_JLE\rC\u0004\u0002\u0000e\u0001\r!!!\u0002\rI,\u0017m]8o!\r1\u00151Q\u0005\u0004\u0003\u000b;$AE#yK\u000e,Ho\u001c:M_N\u001c(+Z1t_:\fac]3u+B,\u00050Z2vi>\u00148i\u001c8gS\u001el\u0015\r\u001d\u000b\u0005\u0003g\nY\tC\u0004\u0002\u000ej\u0001\r!a$\u0002\u0013\u0011\u0014\u0018N^3s!>$\u0007CBA\u001b\u0003#\u000b)*\u0003\u0003\u0002\u0014\u0006}!AB(qi&|g\u000e\u0005\u0003\u0002\u0018\u0006\u0005VBAAM\u0015\u0011\tY*!(\u0002\u000b5|G-\u001a7\u000b\u0007\u0005}5+A\u0002ba&LA!a)\u0002\u001a\n\u0019\u0001k\u001c3\u0002\u001b\u0005\u0004\b\u000f\\5dCRLwN\\%e)\t\t\u0019\"A\u0003ti\u0006\u0014H\u000f\u0006\u0002\u0002t\u0005!1\u000f^8q\u0003]!wNU3rk\u0016\u001cH\u000fV8uC2,\u00050Z2vi>\u00148\u000f\u0006\u0003\u00024\u0006u\u0006CBA[\u0003s\u000bi%\u0004\u0002\u00028*\u0019q,a\b\n\t\u0005m\u0016q\u0017\u0002\u0007\rV$XO]3\t\u000f\u0005}f\u00041\u0001\u0002B\u0006Y\"/Z:pkJ\u001cW\r\u0015:pM&dW\rV8U_R\fG.\u0012=fGN\u0004\u0002\"!\u0006\u0002D\u0006u\u0013\u0011I\u0005\u0005\u0003\u000b\fYCA\u0002NCB\fQd];gM&\u001c\u0017.\u001a8u%\u0016\u001cx.\u001e:dKN\u0014VmZ5ti\u0016\u0014X\r\u001a\u000b\u0003\u0003\u001b\nabZ3u\u000bb,7-\u001e;pe&#7\u000f\u0006\u0002\u0002PB1\u0011\u0011[An\u0003'qA!a5\u0002X:!\u0011\u0011DAk\u0013\t\t\t#\u0003\u0003\u0002Z\u0006}\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003;\fyNA\u0002TKFTA!!7\u0002 \u0005IB.\u00192fY\u0012+7m\\7nSN\u001c\u0018n\u001c8j]\u001e,\u00050Z2t)\u0011\t\u0019(!:\t\u000f\u0005\u001d\u0018\u00051\u0001\u0002P\u00069Q\r_3d\u0013\u0012\u001c\u0018!\u00063fG>lW.[:tS>tW\t_3dkR|'o\u001d\u000b\t\u0003\u001f\fiOa\u0001\u0003\b!9\u0011q\u001e\u0012A\u0002\u0005E\u0018!F3yK\u000e,Ho\u001c:t\u0003:$G)Z2p[&sgm\u001c\t\u0007\u0003k\t\u00190a>\n\t\u0005U\u0018q\u0004\u0002\u0006\u0003J\u0014\u0018-\u001f\t\t\u0003k\tI0a\u0005\u0002~&!\u00111`A\u0010\u0005\u0019!V\u000f\u001d7feA\u0019a)a@\n\u0007\t\u0005qG\u0001\rFq\u0016\u001cW\u000f^8s\t\u0016\u001cw.\\7jgNLwN\\%oM>DqA!\u0002#\u0001\u0004\ti%\u0001\rbI*,8\u000f\u001e+be\u001e,GOT;n\u000bb,7-\u001e;peNDqA!\u0003#\u0001\u0004\ti%A\nue&<w-\u001a:fI\nKX\t_3dkR|'/A\be_.KG\u000e\\#yK\u000e,Ho\u001c:t)\u0011\t\u0019La\u0004\t\u000f\tE1\u00051\u0001\u0002P\u0006YQ\r_3dkR|'/\u00133t\u0003Q\u0019'/Z1uK\u0012\u0013\u0018N^3s\u000b:$\u0007o\\5oiR\u0011!q\u0003\t\u0005\u00053\u0011Y\"D\u0001\u0001\u0013\r\u0011iB\u0011\u0002\u000f\tJLg/\u001a:F]\u0012\u0004x.\u001b8u\u0003\u0019)\u00070Z2JIV\u0011!1\u0005\t\u0005\u0005K\u0011Y#\u0004\u0002\u0003()\u0019!\u0011\u00060\u0002\r\u0005$x.\\5d\u0013\u0011\u0011iCa\n\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u0003\u001d)\u00070Z2JI\u0002\n!c\u0019:fCR,Gk\\6f]6\u000bg.Y4feR\u0011!Q\u0007\t\u0007\u0003k\t\tJa\u000e\u0011\t\te\"1I\u0007\u0003\u0005wQAA!\u0010\u0003@\u0005A1/Z2ve&$\u0018PC\u0002\u0003Be\na\u0001Z3qY>L\u0018\u0002\u0002B#\u0005w\u0011A\u0004S1e_>\u0004H)\u001a7fO\u0006$\u0018n\u001c8U_.,g.T1oC\u001e,'/\u0001\njg\u0016CXmY;u_J,\u0005p\u00197vI\u0016$GCBA'\u0005\u0017\u0012i\u0005C\u0004\u0002|!\u0002\r!a\u0005\t\u000f\t=\u0003\u00061\u0001\u0002\u0014\u0005A\u0001n\\:u]\u0006lWM\u0001\rLk\n,'O\\3uKN$%/\u001b<fe\u0016sG\r]8j]R\u001c2!\u000bB\f)\t\u00119\u0006E\u0002\u0003\u001a%\nq\"\u001a=fG&#%+Z9vKN$XM]\u000b\u0003\u0005;\u0002\u0002Ba\u0018\u0003j\t5\u00141C\u0007\u0003\u0005CRAAa\u0019\u0003f\u00059Q.\u001e;bE2,'\u0002\u0002B4\u0003?\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011YG!\u0019\u0003\u000f!\u000b7\u000f['baB!!q\u000eB;\u001b\t\u0011\tHC\u0002\u0003te\n1A\u001d9d\u0013\u0011\u00119H!\u001d\u0003\u0015I\u00038-\u00113ee\u0016\u001c8/\u0001\tfq\u0016\u001c\u0017\n\u0012*fcV,7\u000f^3sA\u0005qq-\u001a8fe\u0006$X-\u0012=fG&#E\u0003\u0002B@\u0005\u0017\u0003\u0002\"!\u000e\u0003\u0002\n\u0015\u00151O\u0005\u0005\u0005\u0007\u000byBA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o!\u0011\t)Da\"\n\t\t%\u0015q\u0004\u0002\u0004\u0003:L\bb\u0002BG[\u0001\u0007!qR\u0001\bG>tG/\u001a=u!\u0011\u0011yG!%\n\t\tM%\u0011\u000f\u0002\u000f%B\u001c7)\u00197m\u0007>tG/\u001a=u\u0003\u0019JwM\\8sKJ+w-[:uKJ,\u00050Z2vi>\u0014\u0018\t^*u_B\u0004X\rZ\"p]R,\u0007\u0010^\u000b\u0003\u0005\u007f\nqB]3dK&4X-\u00118e%\u0016\u0004H.\u001f\u000b\u0005\u0005\u007f\u0012i\nC\u0004\u0003\u000e>\u0002\rAa$\u0002\u001d=tG)[:d_:tWm\u0019;fIR!\u00111\u000fBR\u0011\u001d\u0011)\u000b\ra\u0001\u0005[\n!B\u001d9d\u0003\u0012$'/Z:t\u0003)\u0019X\u000f]3sIM$x\u000e]\u0005\u0004\u0003[\u0013\u0005"
)
public class KubernetesClusterSchedulerBackend extends CoarseGrainedSchedulerBackend {
   public final SparkContext org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$sc;
   public final KubernetesClient org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient;
   public final ScheduledExecutorService org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$executorService;
   private final ExecutorPodsSnapshotsStore snapshotsStore;
   private final AbstractPodsAllocator podAllocator;
   private final ExecutorPodsLifecycleManager lifecycleEventHandler;
   private final ExecutorPodsWatchSnapshotSource watchEvents;
   private final ExecutorPodsPollingSnapshotSource pollEvents;
   private final String appId;
   private final double minRegisteredRatio;
   private final int initialExecutors;
   private final boolean shouldDeleteDriverService;
   private final boolean shouldDeleteExecutors;
   private final ResourceProfile defaultProfile;
   private final String org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace;
   private final AtomicInteger execId;

   // $FF: synthetic method
   private void super$stop() {
      super.stop();
   }

   private String appId() {
      return this.appId;
   }

   public double minRegisteredRatio() {
      return this.minRegisteredRatio;
   }

   private int initialExecutors() {
      return this.initialExecutors;
   }

   private boolean shouldDeleteDriverService() {
      return this.shouldDeleteDriverService;
   }

   private boolean shouldDeleteExecutors() {
      return this.shouldDeleteExecutors;
   }

   private ResourceProfile defaultProfile() {
      return this.defaultProfile;
   }

   public String org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace() {
      return this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace;
   }

   public void doRemoveExecutor(final String executorId, final ExecutorLossReason reason) {
      this.removeExecutor(executorId, reason);
   }

   private void setUpExecutorConfigMap(final Option driverPod) {
      String configMapName = KubernetesClientUtils$.MODULE$.configMapNameExecutor();
      Map resolvedExecutorProperties = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Config$.MODULE$.KUBERNETES_NAMESPACE().key()), this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())})));
      Map confFilesMap = (Map)KubernetesClientUtils$.MODULE$.buildSparkConfDirFilesMap(configMapName, this.conf(), resolvedExecutorProperties).$plus$plus(resolvedExecutorProperties);
      Map labels = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_APP_ID_LABEL()), this.applicationId()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_ROLE_LABEL()), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())})));
      ConfigMap configMap = KubernetesClientUtils$.MODULE$.buildConfigMap(configMapName, confFilesMap, labels);
      KubernetesUtils$.MODULE$.addOwnerReference((Pod)driverPod.orNull(scala..less.colon.less..MODULE$.refl()), new scala.collection.immutable..colon.colon(configMap, scala.collection.immutable.Nil..MODULE$));
      ((CreateOrReplaceable)((AnyNamespaceOperation)this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.configMaps().inNamespace(this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).resource(configMap)).create();
   }

   public String applicationId() {
      return (String)this.conf().getOption("spark.app.id").getOrElse(() -> this.appId());
   }

   public void start() {
      super.start();
      this.podAllocator.start(this.applicationId(), this);
      Map initExecs = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.defaultProfile()), BoxesRunTime.boxToInteger(this.initialExecutors()))})));
      this.podAllocator.setTotalExpectedExecutors(initExecs);
      this.lifecycleEventHandler.start(this);
      this.watchEvents.start(this.applicationId());
      this.pollEvents.start(this.applicationId());
      if (!BoxesRunTime.unboxToBoolean(this.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP()))) {
         this.setUpExecutorConfigMap(this.podAllocator.driverPod());
      }
   }

   public void stop() {
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.super$stop());
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.snapshotsStore.stop());
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.watchEvents.stop());
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.pollEvents.stop());
      if (this.shouldDeleteDriverService()) {
         org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Deletable)((Filterable)this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.services().inNamespace(this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.applicationId())).delete());
      }

      if (BoxesRunTime.unboxToBoolean(this.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_OWN_PVC()))) {
         org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Deletable)((Filterable)this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.persistentVolumeClaims().inNamespace(this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.applicationId())).delete());
      }

      if (this.shouldDeleteExecutors()) {
         this.podAllocator.stop(this.applicationId());
         if (!BoxesRunTime.unboxToBoolean(this.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP()))) {
            org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Deletable)((Filterable)((Filterable)this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.configMaps().inNamespace(this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.applicationId())).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).delete());
         }
      }

      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> org.apache.spark.util.ThreadUtils..MODULE$.shutdown(this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$executorService, org.apache.spark.util.ThreadUtils..MODULE$.shutdown$default$2()));
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.close());
   }

   public Future doRequestTotalExecutors(final Map resourceProfileToTotalExecs) {
      this.podAllocator.setTotalExpectedExecutors(resourceProfileToTotalExecs);
      return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(true));
   }

   public boolean sufficientResourcesRegistered() {
      return (double)this.totalRegisteredExecutors().get() >= (double)this.initialExecutors() * this.minRegisteredRatio();
   }

   public synchronized Seq getExecutorIds() {
      return super.getExecutorIds();
   }

   private void labelDecommissioningExecs(final Seq execIds) {
      ((Option)this.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_DECOMMISSION_LABEL())).foreach((label) -> {
         $anonfun$labelDecommissioningExecs$1(this, execIds, label);
         return BoxedUnit.UNIT;
      });
   }

   public Seq decommissionExecutors(final Tuple2[] executorsAndDecomInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      if (!triggeredByExecutor) {
         this.labelDecommissioningExecs(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])executorsAndDecomInfo), (x$1) -> (String)x$1._1(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq());
      }

      return super.decommissionExecutors(executorsAndDecomInfo, adjustTargetNumExecutors, triggeredByExecutor);
   }

   public Future doKillExecutors(final Seq executorIds) {
      this.labelDecommissioningExecs(executorIds);
      executorIds.foreach((id) -> {
         $anonfun$doKillExecutors$1(this, id);
         return BoxedUnit.UNIT;
      });
      Runnable killTask = new Runnable(executorIds) {
         // $FF: synthetic field
         private final KubernetesClusterSchedulerBackend $outer;
         private final Seq executorIds$1;

         public void run() {
            org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
               FilterWatchListDeletable running = (FilterWatchListDeletable)((Filterable)((Filterable)((Filterable)((Filterable)this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.pods().inNamespace(this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).withField("status.phase", "Running")).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.$outer.applicationId())).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).withLabelIn(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL(), (String[])this.executorIds$1.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
               if (!((PodList)running.list()).getItems().isEmpty()) {
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Forcefully deleting ", " pods "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(((PodList)running.list()).getItems().size()))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(out of ", ") that are still running after graceful "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL..MODULE$, BoxesRunTime.boxToInteger(this.executorIds$1.size()))})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"shutdown period."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  running.delete();
               }
            });
         }

         public {
            if (KubernetesClusterSchedulerBackend.this == null) {
               throw null;
            } else {
               this.$outer = KubernetesClusterSchedulerBackend.this;
               this.executorIds$1 = executorIds$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$executorService.schedule(killTask, BoxesRunTime.unboxToLong(this.conf().get(Config$.MODULE$.KUBERNETES_DYN_ALLOC_KILL_GRACE_PERIOD())), TimeUnit.MILLISECONDS);
      return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(true));
   }

   public CoarseGrainedSchedulerBackend.DriverEndpoint createDriverEndpoint() {
      return new KubernetesDriverEndpoint();
   }

   public AtomicInteger execId() {
      return this.execId;
   }

   public Option createTokenManager() {
      return new Some(new HadoopDelegationTokenManager(this.conf(), this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$sc.hadoopConfiguration(), this.driverEndpoint()));
   }

   public boolean isExecutorExcluded(final String executorId, final String hostname) {
      return this.podAllocator.isDeleted(executorId);
   }

   // $FF: synthetic method
   public static final void $anonfun$labelDecommissioningExecs$1(final KubernetesClusterSchedulerBackend $this, final Seq execIds$1, final String label) {
      Runnable labelTask = new Runnable(execIds$1, label) {
         // $FF: synthetic field
         private final KubernetesClusterSchedulerBackend $outer;
         private final Seq execIds$1;
         private final String label$1;

         public void run() {
            org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((FilterWatchListDeletable)((Filterable)((Filterable)((Filterable)this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.pods().inNamespace(this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.$outer.applicationId())).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).withLabelIn(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL(), (String[])this.execIds$1.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)))).resources().forEach((podResource) -> podResource.edit((p) -> ((PodBuilder)((PodFluent.MetadataNested)(new PodBuilder(p)).editOrNewMetadata().addToLabels(this.label$1, (String)((Option)this.$outer.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_DECOMMISSION_LABEL_VALUE())).getOrElse(() -> ""))).endMetadata()).build())));
         }

         public {
            if (KubernetesClusterSchedulerBackend.this == null) {
               throw null;
            } else {
               this.$outer = KubernetesClusterSchedulerBackend.this;
               this.execIds$1 = execIds$1;
               this.label$1 = label$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      $this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$executorService.execute(labelTask);
   }

   // $FF: synthetic method
   public static final void $anonfun$doKillExecutors$1(final KubernetesClusterSchedulerBackend $this, final String id) {
      $this.removeExecutor(id, org.apache.spark.scheduler.ExecutorKilled..MODULE$);
   }

   public KubernetesClusterSchedulerBackend(final TaskSchedulerImpl scheduler, final SparkContext sc, final KubernetesClient kubernetesClient, final ScheduledExecutorService executorService, final ExecutorPodsSnapshotsStore snapshotsStore, final AbstractPodsAllocator podAllocator, final ExecutorPodsLifecycleManager lifecycleEventHandler, final ExecutorPodsWatchSnapshotSource watchEvents, final ExecutorPodsPollingSnapshotSource pollEvents) {
      super(scheduler, sc.env().rpcEnv());
      this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$sc = sc;
      this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient = kubernetesClient;
      this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$executorService = executorService;
      this.snapshotsStore = snapshotsStore;
      this.podAllocator = podAllocator;
      this.lifecycleEventHandler = lifecycleEventHandler;
      this.watchEvents = watchEvents;
      this.pollEvents = pollEvents;
      this.appId = KubernetesConf$.MODULE$.getKubernetesAppId();
      this.minRegisteredRatio = ((Option)this.conf().get(org.apache.spark.internal.config.package..MODULE$.SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO())).isEmpty() ? 0.8 : super.minRegisteredRatio();
      this.initialExecutors = org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber(this.conf(), org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber$default$2());
      this.shouldDeleteDriverService = BoxesRunTime.unboxToBoolean(this.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_DELETE_ON_TERMINATION()));
      this.shouldDeleteExecutors = BoxesRunTime.unboxToBoolean(this.conf().get(Config$.MODULE$.KUBERNETES_DELETE_EXECUTORS()));
      this.defaultProfile = scheduler.sc().resourceProfileManager().defaultResourceProfile();
      this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace = (String)this.conf().get(Config$.MODULE$.KUBERNETES_NAMESPACE());
      this.execId = new AtomicInteger(0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class KubernetesDriverEndpoint extends CoarseGrainedSchedulerBackend.DriverEndpoint {
      private final HashMap execIDRequester = new HashMap();

      public HashMap execIDRequester() {
         return this.execIDRequester;
      }

      private PartialFunction generateExecID(final RpcCallContext context) {
         return new Serializable(context) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final KubernetesDriverEndpoint $outer;
            private final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof GenerateExecID var5) {
                  String newId = Integer.toString(this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().execId().incrementAndGet());
                  this.context$1.reply(newId);
                  RpcAddress executorAddress = this.context$1.senderAddress();
                  this.$outer.execIDRequester().update(executorAddress, newId);
                  Runnable labelTask = new Runnable(var5, newId) {
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final GenerateExecID x2$1;
                     private final String newId$1;

                     public void run() {
                        org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((EditReplacePatchable)((Nameable)this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$anonfun$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$kubernetesClient.pods().inNamespace(this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$anonfun$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$namespace())).withName(this.x2$1.podName())).edit((p) -> ((PodBuilder)((PodFluent.MetadataNested)(new PodBuilder(p)).editMetadata().addToLabels(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL(), this.newId$1)).endMetadata()).build()));
                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.x2$1 = x2$1;
                           this.newId$1 = newId$1;
                        }
                     }

                     // $FF: synthetic method
                     private static Object $deserializeLambda$(SerializedLambda var0) {
                        return var0.lambdaDeserialize<invokedynamic>(var0);
                     }
                  };
                  this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$executorService.execute(labelTask);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               return x1 instanceof GenerateExecID;
            }

            // $FF: synthetic method
            public KubernetesDriverEndpoint org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$anonfun$$$outer() {
               return this.$outer;
            }

            public {
               if (KubernetesDriverEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = KubernetesDriverEndpoint.this;
                  this.context$1 = context$1;
               }
            }
         };
      }

      private PartialFunction ignoreRegisterExecutorAtStoppedContext() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final KubernetesDriverEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               return x1 instanceof CoarseGrainedClusterMessages.RegisterExecutor && this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$sc.isStopped() ? BoxedUnit.UNIT : default.apply(x1);
            }

            public final boolean isDefinedAt(final Object x1) {
               return x1 instanceof CoarseGrainedClusterMessages.RegisterExecutor && this.$outer.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$$sc.isStopped();
            }

            public {
               if (KubernetesDriverEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = KubernetesDriverEndpoint.this;
               }
            }
         };
      }

      public PartialFunction receiveAndReply(final RpcCallContext context) {
         return this.generateExecID(context).orElse(this.ignoreRegisterExecutorAtStoppedContext().orElse(super.receiveAndReply(context)));
      }

      public void onDisconnected(final RpcAddress rpcAddress) {
         Option execId = this.addressToExecutorId().get(rpcAddress);
         if (execId instanceof Some var7) {
            String id = (String)var7.value();
            Option var9 = this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().executorsPendingDecommission().get(id);
            if (var9 instanceof Some) {
               this.org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer().removeExecutor(id, new ExecutorDecommission(scala.None..MODULE$, org.apache.spark.scheduler.ExecutorDecommission..MODULE$.apply$default$2()));
               BoxedUnit var14 = BoxedUnit.UNIT;
            } else {
               this.disableExecutor(id);
               BoxedUnit var15 = BoxedUnit.UNIT;
            }

            BoxedUnit var16 = BoxedUnit.UNIT;
         } else {
            Option newExecId = this.execIDRequester().get(rpcAddress);
            if (newExecId instanceof Some) {
               this.execIDRequester().$minus$eq(rpcAddress);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               this.logDebug(() -> "No executor found for " + rpcAddress);
               BoxedUnit var12 = BoxedUnit.UNIT;
            }

            BoxedUnit var13 = BoxedUnit.UNIT;
         }
      }

      // $FF: synthetic method
      public KubernetesClusterSchedulerBackend org$apache$spark$scheduler$cluster$k8s$KubernetesClusterSchedulerBackend$KubernetesDriverEndpoint$$$outer() {
         return (KubernetesClusterSchedulerBackend)this.$outer;
      }

      public KubernetesDriverEndpoint() {
         super(KubernetesClusterSchedulerBackend.this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
