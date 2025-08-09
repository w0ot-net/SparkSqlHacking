package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.AnyNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.CreateOrReplaceable;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.Filterable;
import io.fabric8.kubernetes.client.dsl.Gettable;
import io.fabric8.kubernetes.client.dsl.Listable;
import io.fabric8.kubernetes.client.dsl.Nameable;
import io.fabric8.kubernetes.client.dsl.Waitable;
import java.lang.invoke.SerializedLambda;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf$;
import org.apache.spark.deploy.k8s.KubernetesExecutorConf;
import org.apache.spark.deploy.k8s.KubernetesExecutorSpec;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.LinkedHashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r%b\u0001\u0002$H\u0001QC\u0001b\u0018\u0001\u0003\u0002\u0003\u0006I\u0001\u0019\u0005\tI\u0002\u0011\t\u0011)A\u0005K\"A\u0001\u000e\u0001B\u0001B\u0003%\u0011\u000e\u0003\u0005m\u0001\t\u0005\t\u0015!\u0003n\u0011!I\bA!A!\u0002\u0013Q\b\u0002C?\u0001\u0005\u0003\u0005\u000b\u0011\u0002@\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!I\u00111\u0004\u0001C\u0002\u0013E\u0011Q\u0004\u0005\t\u0003k\u0001\u0001\u0015!\u0003\u0002 !I\u0011q\u0007\u0001C\u0002\u0013E\u0011Q\u0004\u0005\t\u0003s\u0001\u0001\u0015!\u0003\u0002 !I\u00111\b\u0001C\u0002\u0013E\u0011Q\b\u0005\t\u0003\u0017\u0002\u0001\u0015!\u0003\u0002@!I\u0011Q\n\u0001C\u0002\u0013E\u0011q\n\u0005\t\u0003/\u0002\u0001\u0015!\u0003\u0002R!I\u0011\u0011\f\u0001C\u0002\u0013E\u00111\f\u0005\t\u0003K\u0002\u0001\u0015!\u0003\u0002^!I\u0011q\r\u0001C\u0002\u0013E\u0011\u0011\u000e\u0005\t\u0003\u000f\u0003\u0001\u0015!\u0003\u0002l!I\u0011\u0011\u0012\u0001C\u0002\u0013E\u0011Q\b\u0005\t\u0003\u0017\u0003\u0001\u0015!\u0003\u0002@!I\u0011Q\u0012\u0001C\u0002\u0013E\u0011q\u0012\u0005\t\u0003/\u0003\u0001\u0015!\u0003\u0002\u0012\"I\u0011\u0011\u0014\u0001C\u0002\u0013E\u0011Q\b\u0005\t\u00037\u0003\u0001\u0015!\u0003\u0002@!I\u0011Q\u0014\u0001C\u0002\u0013E\u0011q\u0012\u0005\t\u0003?\u0003\u0001\u0015!\u0003\u0002\u0012\"I\u0011\u0011\u0015\u0001C\u0002\u0013E\u0011q\u0012\u0005\t\u0003G\u0003\u0001\u0015!\u0003\u0002\u0012\"I\u0011Q\u0015\u0001C\u0002\u0013E\u0011q\u0012\u0005\t\u0003O\u0003\u0001\u0015!\u0003\u0002\u0012\"I\u0011\u0011\u0016\u0001C\u0002\u0013E\u00111\u0016\u0005\t\u0003\u0007\u0004\u0001\u0015!\u0003\u0002.\"I\u0011Q\u0019\u0001C\u0002\u0013E\u0011q\u0019\u0005\t\u0003\u001f\u0004\u0001\u0015!\u0003\u0002J\"I\u0011\u0011\u001b\u0001C\u0002\u0013E\u0011q\n\u0005\t\u0003'\u0004\u0001\u0015!\u0003\u0002R!I\u0011Q\u001b\u0001C\u0002\u0013\u0005\u0011q\u001b\u0005\t\u0003W\u0004\u0001\u0015!\u0003\u0002Z\"I\u0011Q\u001e\u0001C\u0002\u0013E\u0011q\u001e\u0005\t\u0003{\u0004\u0001\u0015!\u0003\u0002r\"I\u0011q \u0001C\u0002\u0013E!\u0011\u0001\u0005\t\u0005\u000b\u0001\u0001\u0015!\u0003\u0003\u0004!I!q\u0001\u0001C\u0002\u0013E\u0011q\n\u0005\t\u0005\u0013\u0001\u0001\u0015!\u0003\u0002R!I!1\u0002\u0001C\u0002\u0013\u0005\u0011Q\u0004\u0005\t\u0005\u001b\u0001\u0001\u0015!\u0003\u0002 !I!q\u0002\u0001A\u0002\u0013E!\u0011\u0003\u0005\n\u00053\u0001\u0001\u0019!C\t\u00057A\u0001Ba\n\u0001A\u0003&!1\u0003\u0005\f\u0005S\u0001\u0001\u0019!a\u0001\n#\tY\u000bC\u0006\u0003,\u0001\u0001\r\u00111A\u0005\u0012\t5\u0002b\u0003B\u0019\u0001\u0001\u0007\t\u0011)Q\u0005\u0003[C\u0011Ba\r\u0001\u0001\u0004%\tB!\u000e\t\u0013\t\r\u0003\u00011A\u0005\u0012\t\u0015\u0003\u0002\u0003B%\u0001\u0001\u0006KAa\u000e\t\u000f\tM\u0003\u0001\"\u0001\u0003V!9!Q\r\u0001\u0005\u0002\t\u001d\u0004b\u0002B:\u0001\u0011\u0005!Q\u000f\u0005\b\u0005w\u0002A\u0011\u0003B?\u0011\u001d\u0011I\n\u0001C\t\u00057CqA!-\u0001\t#\u0011\u0019\fC\u0004\u0003B\u0002!\tBa1\t\u000f\te\u0007\u0001\"\u0005\u0003\\\"9!1\u001e\u0001\u0005B\t5x\u0001\u0003By\u000f\"\u0005QJa=\u0007\u000f\u0019;\u0005\u0012A'\u0003v\"9\u0011\u0011B\"\u0005\u0002\tu\bb\u0002B\u0000\u0007\u0012\u00051\u0011\u0001\u0002\u0016\u000bb,7-\u001e;peB{Gm]!mY>\u001c\u0017\r^8s\u0015\tA\u0015*A\u0002lqMT!AS&\u0002\u000f\rdWo\u001d;fe*\u0011A*T\u0001\ng\u000eDW\rZ;mKJT!AT(\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\u000b\u0016AB1qC\u000eDWMC\u0001S\u0003\ry'oZ\u0002\u0001'\r\u0001Q+\u0017\t\u0003-^k\u0011aR\u0005\u00031\u001e\u0013Q#\u00112tiJ\f7\r\u001e)pIN\fE\u000e\\8dCR|'\u000f\u0005\u0002[;6\t1L\u0003\u0002]\u001b\u0006A\u0011N\u001c;fe:\fG.\u0003\u0002_7\n9Aj\\4hS:<\u0017\u0001B2p]\u001a\u0004\"!\u00192\u000e\u00035K!aY'\u0003\u0013M\u0003\u0018M]6D_:4\u0017AB:fG6;'\u000f\u0005\u0002bM&\u0011q-\u0014\u0002\u0010'\u0016\u001cWO]5us6\u000bg.Y4fe\u0006yQ\r_3dkR|'OQ;jY\u0012,'\u000f\u0005\u0002WU&\u00111n\u0012\u0002\u001a\u0017V\u0014WM\u001d8fi\u0016\u001cX\t_3dkR|'OQ;jY\u0012,'/\u0001\tlk\n,'O\\3uKN\u001cE.[3oiB\u0011an^\u0007\u0002_*\u0011\u0001/]\u0001\u0007G2LWM\u001c;\u000b\u0005I\u001c\u0018AC6vE\u0016\u0014h.\u001a;fg*\u0011A/^\u0001\bM\u0006\u0014'/[29\u0015\u00051\u0018AA5p\u0013\tAxN\u0001\tLk\n,'O\\3uKN\u001cE.[3oi\u0006q1O\\1qg\"|Go]*u_J,\u0007C\u0001,|\u0013\taxI\u0001\u000eFq\u0016\u001cW\u000f^8s!>$7o\u00158baNDw\u000e^:Ti>\u0014X-A\u0003dY>\u001c7\u000eE\u0002\u0000\u0003\u000bi!!!\u0001\u000b\u0007\u0005\rQ*\u0001\u0003vi&d\u0017\u0002BA\u0004\u0003\u0003\u0011Qa\u00117pG.\fa\u0001P5oSRtDCDA\u0007\u0003\u001f\t\t\"a\u0005\u0002\u0016\u0005]\u0011\u0011\u0004\t\u0003-\u0002AQaX\u0004A\u0002\u0001DQ\u0001Z\u0004A\u0002\u0015DQ\u0001[\u0004A\u0002%DQ\u0001\\\u0004A\u00025DQ!_\u0004A\u0002iDQ!`\u0004A\u0002y\f1#\u0012-F\u0007V#vJU0J\t~\u001bu*\u0016(U\u000bJ+\"!a\b\u0011\t\u0005\u0005\u0012\u0011G\u0007\u0003\u0003GQA!!\n\u0002(\u00051\u0011\r^8nS\u000eTA!!\u000b\u0002,\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u0005\r\u0011Q\u0006\u0006\u0003\u0003_\tAA[1wC&!\u00111GA\u0012\u00055\tEo\\7jG&sG/Z4fe\u0006!R\tW#D+R{%kX%E?\u000e{UK\u0014+F%\u0002\n1\u0002\u0015,D?\u000e{UK\u0014+F%\u0006a\u0001KV\"`\u0007>+f\nV#SA\u00059Q.\u0019=Q-\u000e\u001bXCAA !\u0011\t\t%a\u0012\u000e\u0005\u0005\r#BAA#\u0003\u0015\u00198-\u00197b\u0013\u0011\tI%a\u0011\u0003\u0007%sG/\u0001\u0005nCb\u0004fkQ:!\u00035\u0001x\u000eZ!mY>\u001cwJ\u001c)W\u0007V\u0011\u0011\u0011\u000b\t\u0005\u0003\u0003\n\u0019&\u0003\u0003\u0002V\u0005\r#a\u0002\"p_2,\u0017M\\\u0001\u000fa>$\u0017\t\u001c7pG>s\u0007KV\"!\u0003)\"x\u000e^1m\u000bb\u0004Xm\u0019;fI\u0016CXmY;u_J\u001c\b+\u001a:SKN|WO]2f!J|g-\u001b7f\u0013\u0012,\"!!\u0018\u0011\u0011\u0005}\u0013\u0011MA \u0003\u007fi!!a\n\n\t\u0005\r\u0014q\u0005\u0002\u0012\u0007>t7-\u001e:sK:$\b*Y:i\u001b\u0006\u0004\u0018a\u000b;pi\u0006dW\t\u001f9fGR,G-\u0012=fGV$xN]:QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0011\u0002+I\u0004\u0018\n\u001a+p%\u0016\u001cx.\u001e:dKB\u0013xNZ5mKV\u0011\u00111\u000e\t\t\u0003[\n9(a\u0010\u0002|5\u0011\u0011q\u000e\u0006\u0005\u0003c\n\u0019(A\u0004nkR\f'\r\\3\u000b\t\u0005U\u00141I\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA=\u0003_\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0003\u0002~\u0005\rUBAA@\u0015\r\t\t)T\u0001\te\u0016\u001cx.\u001e:dK&!\u0011QQA@\u0005=\u0011Vm]8ve\u000e,\u0007K]8gS2,\u0017A\u0006:q\u0013\u0012$vNU3t_V\u00148-\u001a)s_\u001aLG.\u001a\u0011\u0002#A|G-\u00117m_\u000e\fG/[8o'&TX-\u0001\nq_\u0012\fE\u000e\\8dCRLwN\\*ju\u0016\u0004\u0013A\u00059pI\u0006cGn\\2bi&|g\u000eR3mCf,\"!!%\u0011\t\u0005\u0005\u00131S\u0005\u0005\u0003+\u000b\u0019E\u0001\u0003M_:<\u0017a\u00059pI\u0006cGn\\2bi&|g\u000eR3mCf\u0004\u0013AD7bqB+g\u000eZ5oOB{Gm]\u0001\u0010[\u0006D\b+\u001a8eS:<\u0007k\u001c3tA\u0005\u0011\u0002o\u001c3De\u0016\fG/[8o)&lWm\\;u\u0003M\u0001x\u000eZ\"sK\u0006$\u0018n\u001c8US6,w.\u001e;!\u0003e!'/\u001b<feB{GMU3bI&tWm]:US6,w.\u001e;\u00025\u0011\u0014\u0018N^3s!>$'+Z1eS:,7o\u001d+j[\u0016|W\u000f\u001e\u0011\u0002'\u0015DXmY;u_JLE\r\\3US6,w.\u001e;\u0002)\u0015DXmY;u_JLE\r\\3US6,w.\u001e;!\u0003%q\u0017-\\3ta\u0006\u001cW-\u0006\u0002\u0002.B!\u0011qVA_\u001d\u0011\t\t,!/\u0011\t\u0005M\u00161I\u0007\u0003\u0003kS1!a.T\u0003\u0019a$o\\8u}%!\u00111XA\"\u0003\u0019\u0001&/\u001a3fM&!\u0011qXAa\u0005\u0019\u0019FO]5oO*!\u00111XA\"\u0003)q\u0017-\\3ta\u0006\u001cW\rI\u0001\u0018WV\u0014WM\u001d8fi\u0016\u001cHI]5wKJ\u0004v\u000e\u001a(b[\u0016,\"!!3\u0011\r\u0005\u0005\u00131ZAW\u0013\u0011\ti-a\u0011\u0003\r=\u0003H/[8o\u0003aYWOY3s]\u0016$Xm\u001d#sSZ,'\u000fU8e\u001d\u0006lW\rI\u0001\u0016g\"|W\u000f\u001c3EK2,G/Z#yK\u000e,Ho\u001c:t\u0003Y\u0019\bn\\;mI\u0012+G.\u001a;f\u000bb,7-\u001e;peN\u0004\u0013!\u00033sSZ,'\u000fU8e+\t\tI\u000e\u0005\u0004\u0002B\u0005-\u00171\u001c\t\u0005\u0003;\f9/\u0004\u0002\u0002`*!\u0011\u0011]Ar\u0003\u0015iw\u000eZ3m\u0015\r\t)/]\u0001\u0004CBL\u0017\u0002BAu\u0003?\u00141\u0001U8e\u0003)!'/\u001b<feB{G\rI\u0001\u0016]\u0016<H._\"sK\u0006$X\rZ#yK\u000e,Ho\u001c:t+\t\t\t\u0010\u0005\u0005\u0002n\u0005M\u0018\u0011SA|\u0013\u0011\t)0a\u001c\u0003\u001b1Kgn[3e\u0011\u0006\u001c\b.T1q!!\t\t%!?\u0002@\u0005E\u0015\u0002BA~\u0003\u0007\u0012a\u0001V;qY\u0016\u0014\u0014A\u00068fo2L8I]3bi\u0016$W\t_3dkR|'o\u001d\u0011\u0002?M\u001c\u0007.\u001a3vY\u0016\u00148J\\8x]:+w\u000f\\=De\u0016\fG/\u001a3Fq\u0016\u001c7/\u0006\u0002\u0003\u0004AA\u0011QNAz\u0003#\u000by$\u0001\u0011tG\",G-\u001e7fe.swn\u001e8OK^d\u0017p\u0011:fCR,G-\u0012=fGN\u0004\u0013\u0001\u00073z]\u0006l\u0017nY!mY>\u001c\u0017\r^5p]\u0016s\u0017M\u00197fI\u0006IB-\u001f8b[&\u001c\u0017\t\u001c7pG\u0006$\u0018n\u001c8F]\u0006\u0014G.\u001a3!\u0003IqW/\\(viN$\u0018M\u001c3j]\u001e\u0004v\u000eZ:\u0002'9,XnT;ugR\fg\u000eZ5oOB{Gm\u001d\u0011\u0002\u00191\f7\u000f^*oCB\u001c\bn\u001c;\u0016\u0005\tM\u0001c\u0001,\u0003\u0016%\u0019!qC$\u0003)\u0015CXmY;u_J\u0004v\u000eZ:T]\u0006\u00048\u000f[8u\u0003Aa\u0017m\u001d;T]\u0006\u00048\u000f[8u?\u0012*\u0017\u000f\u0006\u0003\u0003\u001e\t\r\u0002\u0003BA!\u0005?IAA!\t\u0002D\t!QK\\5u\u0011%\u0011)#MA\u0001\u0002\u0004\u0011\u0019\"A\u0002yIE\nQ\u0002\\1tiNs\u0017\r]:i_R\u0004\u0013!B1qa&#\u0017!C1qa&#w\fJ3r)\u0011\u0011iBa\f\t\u0013\t\u0015B'!AA\u0002\u00055\u0016AB1qa&#\u0007%\u0001\neK2,G/\u001a3Fq\u0016\u001cW\u000f^8s\u0013\u0012\u001cXC\u0001B\u001c!\u0019\u0011IDa\u0010\u0002\u00126\u0011!1\b\u0006\u0005\u0005{\t\u0019(A\u0005j[6,H/\u00192mK&!!\u0011\tB\u001e\u0005\r\u0019V\r^\u0001\u0017I\u0016dW\r^3e\u000bb,7-\u001e;pe&#7o\u0018\u0013fcR!!Q\u0004B$\u0011%\u0011)cNA\u0001\u0002\u0004\u00119$A\neK2,G/\u001a3Fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\b\u0005K\u00029\u0005\u001b\u0002B!!\u0011\u0003P%!!\u0011KA\"\u0005!1x\u000e\\1uS2,\u0017!B:uCJ$HC\u0002B\u000f\u0005/\u0012Y\u0006C\u0004\u0003Ze\u0002\r!!,\u0002\u001b\u0005\u0004\b\u000f\\5dCRLwN\\%e\u0011\u001d\u0011i&\u000fa\u0001\u0005?\n\u0001c]2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3\u0011\u0007Y\u0013\t'C\u0002\u0003d\u001d\u0013\u0011eS;cKJtW\r^3t\u00072,8\u000f^3s'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012\f\u0011d]3u)>$\u0018\r\\#ya\u0016\u001cG/\u001a3Fq\u0016\u001cW\u000f^8sgR!!Q\u0004B5\u0011\u001d\u0011YG\u000fa\u0001\u0005[\n1D]3t_V\u00148-\u001a)s_\u001aLG.\u001a+p)>$\u0018\r\\#yK\u000e\u001c\b\u0003CAX\u0005_\nY(a\u0010\n\t\tE\u0014\u0011\u0019\u0002\u0004\u001b\u0006\u0004\u0018!C5t\t\u0016dW\r^3e)\u0011\t\tFa\u001e\t\u000f\te4\b1\u0001\u0002.\u0006QQ\r_3dkR|'/\u00133\u0002\u001d=tg*Z<T]\u0006\u00048\u000f[8ugRA!Q\u0004B@\u0005\u0003\u0013\u0019\tC\u0004\u0003Zq\u0002\r!!,\t\u000f\tuC\b1\u0001\u0003`!9!Q\u0011\u001fA\u0002\t\u001d\u0015!C:oCB\u001c\bn\u001c;t!\u0019\u0011IIa%\u0003\u00149!!1\u0012BH\u001d\u0011\t\u0019L!$\n\u0005\u0005\u0015\u0013\u0002\u0002BI\u0003\u0007\nq\u0001]1dW\u0006<W-\u0003\u0003\u0003\u0016\n]%aA*fc*!!\u0011SA\"\u0003=9W\r\u001e*fkN\f'\r\\3Q-\u000e\u001bHC\u0002BO\u0005S\u0013Y\u000b\u0005\u0004\u0002n\t}%1U\u0005\u0005\u0005C\u000byG\u0001\u0004Ck\u001a4WM\u001d\t\u0005\u0003;\u0014)+\u0003\u0003\u0003(\u0006}'!\u0006)feNL7\u000f^3oiZ{G.^7f\u00072\f\u0017.\u001c\u0005\b\u00053j\u0004\u0019AAW\u0011\u001d\u0011i+\u0010a\u0001\u0005_\u000b\u0011\u0002\u001d<dg&sWk]3\u0011\r\t%%1SAW\u0003M\u0011X-];fgRtUm^#yK\u000e,Ho\u001c:t))\u0011iB!.\u0003:\nm&q\u0018\u0005\b\u0005os\u0004\u0019AA \u0003YqW/\\#yK\u000e,Ho\u001c:t)>\fE\u000e\\8dCR,\u0007b\u0002B-}\u0001\u0007\u0011Q\u0016\u0005\b\u0005{s\u0004\u0019AA \u0003E\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0005\b\u0005[s\u0004\u0019\u0001BX\u0003M\u0011X\r\u001d7bG\u0016\u0004fkQ:JM:+W\rZ3e)!\u0011)M!4\u0003R\nU\u0007C\u0002BE\u0005'\u00139\r\u0005\u0003\u0002^\n%\u0017\u0002\u0002Bf\u0003?\u00141\u0002S1t\u001b\u0016$\u0018\rZ1uC\"9!qZ A\u0002\u0005m\u0017a\u00019pI\"9!1[ A\u0002\t\u0015\u0017!\u0003:fg>,(oY3t\u0011\u001d\u00119n\u0010a\u0001\u0005;\u000bAB]3vg\u0006\u0014G.\u001a)W\u0007N\fa#[:Fq\u0016\u001cW\u000f^8s\u0013\u0012dW\rV5nK\u0012|U\u000f\u001e\u000b\u0007\u0003#\u0012iNa:\t\u000f\t}\u0007\t1\u0001\u0003b\u0006)1\u000f^1uKB\u0019aKa9\n\u0007\t\u0015xI\u0001\tFq\u0016\u001cW\u000f^8s!>$7\u000b^1uK\"9!\u0011\u001e!A\u0002\u0005E\u0015aC2veJ,g\u000e\u001e+j[\u0016\fAa\u001d;paR!!Q\u0004Bx\u0011\u001d\u0011I&\u0011a\u0001\u0003[\u000bQ#\u0012=fGV$xN\u001d)pIN\fE\u000e\\8dCR|'\u000f\u0005\u0002W\u0007N\u00191Ia>\u0011\t\u0005\u0005#\u0011`\u0005\u0005\u0005w\f\u0019E\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0005g\f!b\u001d9mSR\u001cFn\u001c;t+\u0011\u0019\u0019a!\u0004\u0015\r\r\u00151qDB\u0013!\u0019\u0011IIa%\u0004\bAA\u0011\u0011IA}\u0007\u0013\ty\u0004\u0005\u0003\u0004\f\r5A\u0002\u0001\u0003\b\u0007\u001f)%\u0019AB\t\u0005\u0005!\u0016\u0003BB\n\u00073\u0001B!!\u0011\u0004\u0016%!1qCA\"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!!\u0011\u0004\u001c%!1QDA\"\u0005\r\te.\u001f\u0005\b\u0007C)\u0005\u0019AB\u0012\u0003%\u0019wN\\:v[\u0016\u00148\u000f\u0005\u0004\u0003\n\nM5\u0011\u0002\u0005\b\u0007O)\u0005\u0019AA \u0003\u0015\u0019Hn\u001c;t\u0001"
)
public class ExecutorPodsAllocator extends AbstractPodsAllocator implements Logging {
   private final SparkConf conf;
   private final SecurityManager secMgr;
   private final KubernetesExecutorBuilder executorBuilder;
   private final KubernetesClient kubernetesClient;
   private final ExecutorPodsSnapshotsStore snapshotsStore;
   private final Clock clock;
   private final AtomicInteger EXECUTOR_ID_COUNTER;
   private final AtomicInteger PVC_COUNTER;
   private final int maxPVCs;
   private final boolean podAllocOnPVC;
   private final ConcurrentHashMap totalExpectedExecutorsPerResourceProfileId;
   private final HashMap rpIdToResourceProfile;
   private final int podAllocationSize;
   private final long podAllocationDelay;
   private final int maxPendingPods;
   private final long podCreationTimeout;
   private final long driverPodReadinessTimeout;
   private final long executorIdleTimeout;
   private final String namespace;
   private final Option kubernetesDriverPodName;
   private final boolean shouldDeleteExecutors;
   private final Option driverPod;
   private final LinkedHashMap newlyCreatedExecutors;
   private final LinkedHashMap schedulerKnownNewlyCreatedExecs;
   private final boolean dynamicAllocationEnabled;
   private final AtomicInteger numOutstandingPods;
   private ExecutorPodsSnapshot lastSnapshot;
   private String appId;
   private volatile Set deletedExecutorIds;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Seq splitSlots(final Seq consumers, final int slots) {
      return ExecutorPodsAllocator$.MODULE$.splitSlots(consumers, slots);
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

   public AtomicInteger EXECUTOR_ID_COUNTER() {
      return this.EXECUTOR_ID_COUNTER;
   }

   public AtomicInteger PVC_COUNTER() {
      return this.PVC_COUNTER;
   }

   public int maxPVCs() {
      return this.maxPVCs;
   }

   public boolean podAllocOnPVC() {
      return this.podAllocOnPVC;
   }

   public ConcurrentHashMap totalExpectedExecutorsPerResourceProfileId() {
      return this.totalExpectedExecutorsPerResourceProfileId;
   }

   public HashMap rpIdToResourceProfile() {
      return this.rpIdToResourceProfile;
   }

   public int podAllocationSize() {
      return this.podAllocationSize;
   }

   public long podAllocationDelay() {
      return this.podAllocationDelay;
   }

   public int maxPendingPods() {
      return this.maxPendingPods;
   }

   public long podCreationTimeout() {
      return this.podCreationTimeout;
   }

   public long driverPodReadinessTimeout() {
      return this.driverPodReadinessTimeout;
   }

   public long executorIdleTimeout() {
      return this.executorIdleTimeout;
   }

   public String namespace() {
      return this.namespace;
   }

   public Option kubernetesDriverPodName() {
      return this.kubernetesDriverPodName;
   }

   public boolean shouldDeleteExecutors() {
      return this.shouldDeleteExecutors;
   }

   public Option driverPod() {
      return this.driverPod;
   }

   public LinkedHashMap newlyCreatedExecutors() {
      return this.newlyCreatedExecutors;
   }

   public LinkedHashMap schedulerKnownNewlyCreatedExecs() {
      return this.schedulerKnownNewlyCreatedExecs;
   }

   public boolean dynamicAllocationEnabled() {
      return this.dynamicAllocationEnabled;
   }

   public AtomicInteger numOutstandingPods() {
      return this.numOutstandingPods;
   }

   public ExecutorPodsSnapshot lastSnapshot() {
      return this.lastSnapshot;
   }

   public void lastSnapshot_$eq(final ExecutorPodsSnapshot x$1) {
      this.lastSnapshot = x$1;
   }

   public String appId() {
      return this.appId;
   }

   public void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   public Set deletedExecutorIds() {
      return this.deletedExecutorIds;
   }

   public void deletedExecutorIds_$eq(final Set x$1) {
      this.deletedExecutorIds = x$1;
   }

   public void start(final String applicationId, final KubernetesClusterSchedulerBackend schedulerBackend) {
      this.appId_$eq(applicationId);
      this.driverPod().foreach((pod) -> {
         $anonfun$start$1(this, pod);
         return BoxedUnit.UNIT;
      });
      this.snapshotsStore.addSubscriber(this.podAllocationDelay(), (executorPodsSnapshot) -> {
         $anonfun$start$3(this, applicationId, schedulerBackend, executorPodsSnapshot);
         return BoxedUnit.UNIT;
      });
   }

   public void setTotalExpectedExecutors(final scala.collection.immutable.Map resourceProfileToTotalExecs) {
      resourceProfileToTotalExecs.foreach((x0$1) -> BoxesRunTime.boxToInteger($anonfun$setTotalExpectedExecutors$1(this, x0$1)));
      this.logDebug((Function0)(() -> "Set total expected execs to " + this.totalExpectedExecutorsPerResourceProfileId()));
      if (this.numOutstandingPods().get() == 0) {
         this.snapshotsStore.notifySubscribers();
      }
   }

   public boolean isDeleted(final String executorId) {
      return this.deletedExecutorIds().contains(BoxesRunTime.boxToLong(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(executorId))));
   }

   public void onNewSnapshots(final String applicationId, final KubernetesClusterSchedulerBackend schedulerBackend, final Seq snapshots) {
      this.logDebug((Function0)(() -> "Received " + snapshots.size() + " snapshots"));
      Seq k8sKnownExecIds = (Seq)((SeqOps)snapshots.flatMap((x$2) -> x$2.executorPods().keys())).distinct();
      this.newlyCreatedExecutors().$minus$minus$eq(k8sKnownExecIds);
      this.schedulerKnownNewlyCreatedExecs().$minus$minus$eq(k8sKnownExecIds);
      Seq k8sKnownPVCNames = (Seq)((SeqOps)((IterableOps)snapshots.flatMap((x$3) -> (Iterable)x$3.executorPods().values().map((x$4) -> x$4.pod()))).flatMap((pod) -> (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(pod.getSpec().getVolumes()).asScala().flatMap((v) -> scala.Option..MODULE$.apply(v.getPersistentVolumeClaim()).map((x$5) -> x$5.getClaimName())))).distinct();
      Set schedulerKnownExecs = ((IterableOnceOps)schedulerBackend.getExecutorIds().map((x$6) -> BoxesRunTime.boxToLong($anonfun$onNewSnapshots$8(x$6)))).toSet();
      this.schedulerKnownNewlyCreatedExecs().$plus$plus$eq(((StrictOptimizedMapOps)this.newlyCreatedExecutors().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$9(schedulerKnownExecs, x0$1)))).map((x0$2) -> {
         if (x0$2 != null) {
            long k = x0$2._1$mcJ$sp();
            Tuple2 v = (Tuple2)x0$2._2();
            return new Tuple2.mcJI.sp(k, v._1$mcI$sp());
         } else {
            throw new MatchError(x0$2);
         }
      }));
      this.newlyCreatedExecutors().$minus$minus$eq(this.schedulerKnownNewlyCreatedExecs().keySet());
      long currentTime = this.clock.getTimeMillis();
      scala.collection.mutable.Iterable timedOut = (scala.collection.mutable.Iterable)this.newlyCreatedExecutors().flatMap((x0$3) -> {
         if (x0$3 != null) {
            long execId = x0$3._1$mcJ$sp();
            Tuple2 var8 = (Tuple2)x0$3._2();
            if (var8 != null) {
               long timeCreated = var8._2$mcJ$sp();
               if (currentTime - timeCreated > this.podCreationTimeout()) {
                  return new Some(BoxesRunTime.boxToLong(execId));
               }

               this.logDebug((Function0)(() -> "Executor with id " + execId + " was not found in the Kubernetes cluster since it was created " + (currentTime - timeCreated) + " milliseconds ago."));
               return scala.None..MODULE$;
            }
         }

         throw new MatchError(x0$3);
      });
      if (timedOut.nonEmpty()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executors with ids ", "} "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, timedOut.mkString(","))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"were not detected in the Kubernetes cluster after "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms despite the fact that a previous "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(this.podCreationTimeout()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"allocation attempt tried to create them. The executors may have been deleted but the "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"application missed the deletion event."})))).log(scala.collection.immutable.Nil..MODULE$))));
         this.newlyCreatedExecutors().$minus$minus$eq(timedOut);
         if (this.shouldDeleteExecutors()) {
            org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Deletable)((Filterable)((Filterable)((Filterable)this.kubernetesClient.pods().inNamespace(this.namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), applicationId)).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).withLabelIn(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL(), (String[])((IterableOnceOps)timedOut.toSeq().map((x$7) -> $anonfun$onNewSnapshots$15(BoxesRunTime.unboxToLong(x$7)))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)))).delete());
         }
      }

      if (snapshots.nonEmpty()) {
         this.lastSnapshot_$eq((ExecutorPodsSnapshot)snapshots.last());
      }

      ObjectRef _deletedExecutorIds = ObjectRef.create(this.deletedExecutorIds());
      if (snapshots.nonEmpty()) {
         Set existingExecs = this.lastSnapshot().executorPods().keySet();
         _deletedExecutorIds.elem = (Set)((Set)_deletedExecutorIds.elem).intersect(existingExecs);
      }

      scala.collection.immutable.Map notDeletedPods = (scala.collection.immutable.Map)this.lastSnapshot().executorPods().filter((x0$4) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$16(_deletedExecutorIds, x0$4)));
      HashMap rpIdToExecsAndPodState = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      if (this.totalExpectedExecutorsPerResourceProfileId().size() <= 1) {
         rpIdToExecsAndPodState.update(BoxesRunTime.boxToInteger(org.apache.spark.resource.ResourceProfile..MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), scala.collection.mutable.HashMap..MODULE$.empty().$plus$plus$eq(notDeletedPods));
      } else {
         notDeletedPods.foreach((x0$5) -> {
            $anonfun$onNewSnapshots$17(rpIdToExecsAndPodState, x0$5);
            return BoxedUnit.UNIT;
         });
      }

      IntRef totalPendingCount = IntRef.create(0);
      IntRef totalNotRunningPodCount = IntRef.create(0);
      Seq podsToAllocateWithRpId = (Seq)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.totalExpectedExecutorsPerResourceProfileId()).asScala().toSeq().sortBy((x$8) -> BoxesRunTime.boxToInteger($anonfun$onNewSnapshots$19(x$8)), scala.math.Ordering.Int..MODULE$)).flatMap((x0$6) -> {
         if (x0$6 != null) {
            int rpId = x0$6._1$mcI$sp();
            int targetNum = x0$6._2$mcI$sp();
            HashMap podsForRpId = (HashMap)rpIdToExecsAndPodState.getOrElse(BoxesRunTime.boxToInteger(rpId), () -> scala.collection.mutable.HashMap..MODULE$.empty());
            int currentRunningCount = podsForRpId.values().count((x0$7) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$22(x0$7)));
            Tuple2 var19 = ((StrictOptimizedIterableOps)podsForRpId.filter((x0$8) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$23(x0$8)))).partition((x0$9) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$24(schedulerKnownExecs, x0$9)));
            if (var19 == null) {
               throw new MatchError(var19);
            } else {
               HashMap schedulerKnownPendingExecsForRpId = (HashMap)var19._1();
               HashMap currentPendingExecutorsForRpId = (HashMap)var19._2();
               Tuple2 var18 = new Tuple2(schedulerKnownPendingExecsForRpId, currentPendingExecutorsForRpId);
               HashMap schedulerKnownPendingExecsForRpId = (HashMap)var18._1();
               HashMap currentPendingExecutorsForRpId = (HashMap)var18._2();
               IntRef pendingCountForRpId = IntRef.create(currentPendingExecutorsForRpId.size());
               LinkedHashMap newlyCreatedExecutorsForRpId = (LinkedHashMap)this.newlyCreatedExecutors().filter((x0$10) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$25(rpId, x0$10)));
               LinkedHashMap schedulerKnownNewlyCreatedExecsForRpId = (LinkedHashMap)this.schedulerKnownNewlyCreatedExecs().filter((x0$11) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$26(rpId, x0$11)));
               if (podsForRpId.nonEmpty()) {
                  this.logDebug((Function0)(() -> "ResourceProfile Id: " + rpId + " (pod allocation status: " + currentRunningCount + " running, " + currentPendingExecutorsForRpId.size() + " unknown pending, " + schedulerKnownPendingExecsForRpId.size() + " scheduler backend known pending, " + newlyCreatedExecutorsForRpId.size() + " unknown newly created, " + schedulerKnownNewlyCreatedExecsForRpId.size() + " scheduler backend known newly created)"));
               }

               IntRef notRunningPodCountForRpId = IntRef.create(currentPendingExecutorsForRpId.size() + schedulerKnownPendingExecsForRpId.size() + newlyCreatedExecutorsForRpId.size() + schedulerKnownNewlyCreatedExecsForRpId.size());
               int podCountForRpId = currentRunningCount + notRunningPodCountForRpId.elem;
               if (podCountForRpId > targetNum) {
                  int excess = podCountForRpId - targetNum;
                  List newlyCreatedToDelete = ((IterableOnceOps)((MapOps)newlyCreatedExecutorsForRpId.filter((x0$12) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$28(this, currentTime, x0$12)))).keys().take(excess)).toList();
                  scala.collection.mutable.Iterable pendingToDelete = (scala.collection.mutable.Iterable)((StrictOptimizedIterableOps)((IterableOps)currentPendingExecutorsForRpId.filter((x) -> BoxesRunTime.boxToBoolean($anonfun$onNewSnapshots$29(this, currentTime, x)))).take(excess - newlyCreatedToDelete.size())).map((x0$13) -> BoxesRunTime.boxToLong($anonfun$onNewSnapshots$30(x0$13)));
                  List toDelete = (List)newlyCreatedToDelete.$plus$plus(pendingToDelete);
                  if (toDelete.nonEmpty()) {
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting ", " excess pod requests "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(toDelete.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_IDS..MODULE$, toDelete.mkString(","))}))))));
                     _deletedExecutorIds.elem = (Set)((Set)_deletedExecutorIds.elem).$plus$plus(toDelete);
                     org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
                        ((Deletable)((Filterable)((Filterable)((Filterable)((Filterable)this.kubernetesClient.pods().inNamespace(this.namespace())).withField("status.phase", "Pending")).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), applicationId)).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).withLabelIn(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL(), (String[])((List)toDelete.sorted(scala.math.Ordering.Long..MODULE$)).map((x$10) -> $anonfun$onNewSnapshots$33(BoxesRunTime.unboxToLong(x$10))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)))).delete();
                        this.newlyCreatedExecutors().$minus$minus$eq(newlyCreatedToDelete);
                        pendingCountForRpId.elem -= pendingToDelete.size();
                        notRunningPodCountForRpId.elem -= toDelete.size();
                     });
                  }
               }

               totalPendingCount.elem += pendingCountForRpId.elem;
               totalNotRunningPodCount.elem += notRunningPodCountForRpId.elem;
               if (this.log().isDebugEnabled() && snapshots.nonEmpty()) {
                  if (currentRunningCount >= targetNum && !this.dynamicAllocationEnabled()) {
                     this.logDebug((Function0)(() -> "Current number of running executors for ResourceProfile Id " + rpId + " is equal to the number of requested executors. Not scaling up further."));
                  } else if (newlyCreatedExecutorsForRpId.nonEmpty()) {
                     this.logDebug((Function0)(() -> {
                        int var10000 = newlyCreatedExecutorsForRpId.size();
                        return "Still waiting for " + var10000 + " executors for ResourceProfile Id " + rpId + " before requesting more.";
                     }));
                  }
               }

               return (Option)(newlyCreatedExecutorsForRpId.isEmpty() && podCountForRpId < targetNum ? new Some(new Tuple3(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(podCountForRpId), BoxesRunTime.boxToInteger(targetNum))) : scala.None..MODULE$);
            }
         } else {
            throw new MatchError(x0$6);
         }
      });
      int remainingSlotFromPendingPods = this.maxPendingPods() - totalNotRunningPodCount.elem;
      if (remainingSlotFromPendingPods > 0 && podsToAllocateWithRpId.size() > 0 && (!snapshots.isEmpty() || !this.podAllocOnPVC() || this.maxPVCs() > this.PVC_COUNTER().get())) {
         ExecutorPodsAllocator$.MODULE$.splitSlots(podsToAllocateWithRpId, remainingSlotFromPendingPods).foreach((x0$14) -> {
            $anonfun$onNewSnapshots$36(this, applicationId, k8sKnownPVCNames, x0$14);
            return BoxedUnit.UNIT;
         });
      }

      this.deletedExecutorIds_$eq((Set)_deletedExecutorIds.elem);
      this.numOutstandingPods().set(totalPendingCount.elem + this.newlyCreatedExecutors().size());
   }

   public Buffer getReusablePVCs(final String applicationId, final Seq pvcsInUse) {
      if (BoxesRunTime.unboxToBoolean(this.conf.get(Config$.MODULE$.KUBERNETES_DRIVER_OWN_PVC())) && BoxesRunTime.unboxToBoolean(this.conf.get(Config$.MODULE$.KUBERNETES_DRIVER_REUSE_PVC())) && this.driverPod().nonEmpty()) {
         Buffer var10000;
         try {
            Buffer createdPVCs = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(((PersistentVolumeClaimList)((Listable)((Filterable)this.kubernetesClient.persistentVolumeClaims().inNamespace(this.namespace())).withLabel("spark-app-selector", applicationId)).list()).getItems()).asScala();
            long now = Instant.now().toEpochMilli();
            Buffer reusablePVCs = (Buffer)((IterableOps)createdPVCs.filterNot((pvc) -> BoxesRunTime.boxToBoolean($anonfun$getReusablePVCs$1(pvcsInUse, pvc)))).filter((pvc) -> BoxesRunTime.boxToBoolean($anonfun$getReusablePVCs$2(this, now, pvc)));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Found ", " reusable PVCs from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(reusablePVCs.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " PVCs"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL..MODULE$, BoxesRunTime.boxToInteger(createdPVCs.size()))}))))));
            var10000 = reusablePVCs;
         } catch (KubernetesClientException var7) {
            this.logInfo((Function0)(() -> "Cannot list PVC resources. Please check account permissions."));
            var10000 = (Buffer)scala.collection.mutable.Buffer..MODULE$.empty();
         }

         return var10000;
      } else {
         return (Buffer)scala.collection.mutable.Buffer..MODULE$.empty();
      }
   }

   public void requestNewExecutors(final int numExecutorsToAllocate, final String applicationId, final int resourceProfileId, final Seq pvcsInUse) {
      Object var5 = new Object();

      try {
         Buffer reusablePVCs = this.getReusablePVCs(applicationId, pvcsInUse);
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numExecutorsToAllocate).foreach$mVc$sp((JFunction1.mcVI.sp)(x$12) -> {
            if (reusablePVCs.isEmpty() && this.podAllocOnPVC() && this.maxPVCs() <= this.PVC_COUNTER().get()) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Wait to reuse one of the existing ", " PVCs."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(this.PVC_COUNTER().get()))})))));
               throw new NonLocalReturnControl.mcV.sp(var5, BoxedUnit.UNIT);
            } else {
               int newExecutorId = this.EXECUTOR_ID_COUNTER().incrementAndGet();
               KubernetesExecutorConf executorConf = KubernetesConf$.MODULE$.createExecutorConf(this.conf, Integer.toString(newExecutorId), applicationId, this.driverPod(), resourceProfileId);
               KubernetesExecutorSpec resolvedExecutorSpec = this.executorBuilder.buildFromFeatures(executorConf, this.secMgr, this.kubernetesClient, (ResourceProfile)this.rpIdToResourceProfile().apply(BoxesRunTime.boxToInteger(resourceProfileId)));
               SparkPod executorPod = resolvedExecutorSpec.pod();
               Pod podWithAttachedContainer = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(executorPod.pod())).editOrNewSpec().addToContainers(new Container[]{executorPod.container()})).endSpec()).build();
               Seq resources = this.replacePVCsIfNeeded(podWithAttachedContainer, resolvedExecutorSpec.executorKubernetesResources(), reusablePVCs);
               Pod createdExecutorPod = (Pod)((CreateOrReplaceable)((AnyNamespaceOperation)this.kubernetesClient.pods().inNamespace(this.namespace())).resource(podWithAttachedContainer)).create();

               try {
                  KubernetesUtils$.MODULE$.addOwnerReference(createdExecutorPod, resources);
                  ((IterableOnceOps)resources.filter((x$11) -> BoxesRunTime.boxToBoolean($anonfun$requestNewExecutors$3(x$11)))).foreach((resource) -> BoxesRunTime.boxToInteger($anonfun$requestNewExecutors$4(this, resource)));
                  this.newlyCreatedExecutors().update(BoxesRunTime.boxToLong((long)newExecutorId), new Tuple2.mcIJ.sp(resourceProfileId, this.clock.getTimeMillis()));
                  this.logDebug((Function0)(() -> "Requested executor with id " + newExecutorId + " from Kubernetes."));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } catch (Throwable var19) {
                  if (var19 != null && scala.util.control.NonFatal..MODULE$.apply(var19)) {
                     ((Deletable)((AnyNamespaceOperation)this.kubernetesClient.pods().inNamespace(this.namespace())).resource(createdExecutorPod)).delete();
                     throw var19;
                  }

                  throw var19;
               }

            }
         });
      } catch (NonLocalReturnControl var8) {
         if (var8.key() != var5) {
            throw var8;
         }

         var8.value$mcV$sp();
      }

   }

   public Seq replacePVCsIfNeeded(final Pod pod, final Seq resources, final Buffer reusablePVCs) {
      scala.collection.mutable.Set replacedResources = (scala.collection.mutable.Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      resources.foreach((x0$1) -> {
         $anonfun$replacePVCsIfNeeded$1(this, reusablePVCs, pod, replacedResources, x0$1);
         return BoxedUnit.UNIT;
      });
      return (Seq)resources.filterNot((elem) -> BoxesRunTime.boxToBoolean($anonfun$replacePVCsIfNeeded$5(replacedResources, elem)));
   }

   public boolean isExecutorIdleTimedOut(final ExecutorPodState state, final long currentTime) {
      boolean var10000;
      try {
         long creationTime = Instant.parse(state.pod().getMetadata().getCreationTimestamp()).toEpochMilli();
         var10000 = currentTime - creationTime > this.executorIdleTimeout();
      } catch (Exception var7) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot get the creationTimestamp of the pod: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POD_ID..MODULE$, state.pod())}))))), var7);
         var10000 = true;
      }

      return var10000;
   }

   public void stop(final String applicationId) {
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Deletable)((Filterable)((Filterable)this.kubernetesClient.pods().inNamespace(this.namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), applicationId)).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).delete());
   }

   // $FF: synthetic method
   public static final void $anonfun$start$1(final ExecutorPodsAllocator $this, final Pod pod) {
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Waitable)((Nameable)$this.kubernetesClient.pods().inNamespace($this.namespace())).withName(pod.getMetadata().getName())).waitUntilReady($this.driverPodReadinessTimeout(), TimeUnit.SECONDS));
   }

   // $FF: synthetic method
   public static final void $anonfun$start$3(final ExecutorPodsAllocator $this, final String applicationId$1, final KubernetesClusterSchedulerBackend schedulerBackend$1, final Seq executorPodsSnapshot) {
      $this.onNewSnapshots(applicationId$1, schedulerBackend$1, executorPodsSnapshot);
   }

   // $FF: synthetic method
   public static final int $anonfun$setTotalExpectedExecutors$1(final ExecutorPodsAllocator $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         ResourceProfile rp = (ResourceProfile)x0$1._1();
         int numExecs = x0$1._2$mcI$sp();
         $this.rpIdToResourceProfile().getOrElseUpdate(BoxesRunTime.boxToInteger(rp.id()), () -> rp);
         return BoxesRunTime.unboxToInt($this.totalExpectedExecutorsPerResourceProfileId().put(BoxesRunTime.boxToInteger(rp.id()), BoxesRunTime.boxToInteger(numExecs)));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$onNewSnapshots$8(final String x$6) {
      return .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$6));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$9(final Set schedulerKnownExecs$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long k = x0$1._1$mcJ$sp();
         return schedulerKnownExecs$1.contains(BoxesRunTime.boxToLong(k));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$onNewSnapshots$15(final long x$7) {
      return Long.toString(x$7);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$16(final ObjectRef _deletedExecutorIds$1, final Tuple2 x0$4) {
      if (x0$4 != null) {
         long k = x0$4._1$mcJ$sp();
         return !((Set)_deletedExecutorIds$1.elem).contains(BoxesRunTime.boxToLong(k));
      } else {
         throw new MatchError(x0$4);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onNewSnapshots$17(final HashMap rpIdToExecsAndPodState$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         long execId = x0$5._1$mcJ$sp();
         ExecutorPodState execPodState = (ExecutorPodState)x0$5._2();
         int rpId = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)execPodState.pod().getMetadata().getLabels().get(Constants$.MODULE$.SPARK_RESOURCE_PROFILE_ID_LABEL())));
         HashMap execPods = (HashMap)rpIdToExecsAndPodState$1.getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), () -> (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
         execPods.update(BoxesRunTime.boxToLong(execId), execPodState);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$5);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$onNewSnapshots$19(final Tuple2 x$8) {
      return x$8._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$22(final ExecutorPodState x0$7) {
      return x0$7 instanceof PodRunning;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$23(final Tuple2 x0$8) {
      if (x0$8 != null) {
         ExecutorPodState var3 = (ExecutorPodState)x0$8._2();
         if (var3 instanceof PodPending) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$24(final Set schedulerKnownExecs$1, final Tuple2 x0$9) {
      if (x0$9 != null) {
         long k = x0$9._1$mcJ$sp();
         return schedulerKnownExecs$1.contains(BoxesRunTime.boxToLong(k));
      } else {
         throw new MatchError(x0$9);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$25(final int rpId$1, final Tuple2 x0$10) {
      if (x0$10 != null) {
         Tuple2 var4 = (Tuple2)x0$10._2();
         if (var4 != null) {
            int waitingRpId = var4._1$mcI$sp();
            return rpId$1 == waitingRpId;
         }
      }

      throw new MatchError(x0$10);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$26(final int rpId$1, final Tuple2 x0$11) {
      if (x0$11 != null) {
         int waitingRpId = x0$11._2$mcI$sp();
         return rpId$1 == waitingRpId;
      } else {
         throw new MatchError(x0$11);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$28(final ExecutorPodsAllocator $this, final long currentTime$1, final Tuple2 x0$12) {
      if (x0$12 != null) {
         Tuple2 var6 = (Tuple2)x0$12._2();
         if (var6 != null) {
            long createTime = var6._2$mcJ$sp();
            return currentTime$1 - createTime > $this.executorIdleTimeout();
         }
      }

      throw new MatchError(x0$12);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onNewSnapshots$29(final ExecutorPodsAllocator $this, final long currentTime$1, final Tuple2 x) {
      return $this.isExecutorIdleTimedOut((ExecutorPodState)x._2(), currentTime$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$onNewSnapshots$30(final Tuple2 x0$13) {
      if (x0$13 != null) {
         long id = x0$13._1$mcJ$sp();
         return id;
      } else {
         throw new MatchError(x0$13);
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$onNewSnapshots$33(final long x$10) {
      return Long.toString(x$10);
   }

   // $FF: synthetic method
   public static final void $anonfun$onNewSnapshots$36(final ExecutorPodsAllocator $this, final String applicationId$2, final Seq k8sKnownPVCNames$1, final Tuple2 x0$14) {
      if (x0$14 != null) {
         Tuple3 var6 = (Tuple3)x0$14._1();
         int sharedSlotFromPendingPods = x0$14._2$mcI$sp();
         if (var6 != null) {
            int rpId = BoxesRunTime.unboxToInt(var6._1());
            int podCountForRpId = BoxesRunTime.unboxToInt(var6._2());
            int targetNum = BoxesRunTime.unboxToInt(var6._3());
            int numMissingPodsForRpId = targetNum - podCountForRpId;
            int numExecutorsToAllocate = scala.math.package..MODULE$.min(scala.math.package..MODULE$.min(numMissingPodsForRpId, $this.podAllocationSize()), sharedSlotFromPendingPods);
            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Going to request ", " executors from"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(numExecutorsToAllocate))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Kubernetes for ResourceProfile Id: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rpId))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"target: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_POD_TARGET..MODULE$, BoxesRunTime.boxToInteger(targetNum))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"known: ", ", sharedSlotFromPendingPods: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_POD..MODULE$, BoxesRunTime.boxToInteger(podCountForRpId))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_POD_SHARED_SLOT..MODULE$, BoxesRunTime.boxToInteger(sharedSlotFromPendingPods))}))))));
            $this.requestNewExecutors(numExecutorsToAllocate, applicationId$2, rpId, k8sKnownPVCNames$1);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$14);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getReusablePVCs$1(final Seq pvcsInUse$1, final PersistentVolumeClaim pvc) {
      return pvcsInUse$1.contains(pvc.getMetadata().getName());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getReusablePVCs$2(final ExecutorPodsAllocator $this, final long now$1, final PersistentVolumeClaim pvc) {
      return now$1 - Instant.parse(pvc.getMetadata().getCreationTimestamp()).toEpochMilli() > $this.podCreationTimeout();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$requestNewExecutors$3(final HasMetadata x$11) {
      boolean var2;
      label23: {
         String var10000 = x$11.getKind();
         String var1 = "PersistentVolumeClaim";
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

   // $FF: synthetic method
   public static final int $anonfun$requestNewExecutors$4(final ExecutorPodsAllocator $this, final HasMetadata resource) {
      if (BoxesRunTime.unboxToBoolean($this.conf.get(Config$.MODULE$.KUBERNETES_DRIVER_OWN_PVC())) && $this.driverPod().nonEmpty()) {
         KubernetesUtils$.MODULE$.addOwnerReference((Pod)$this.driverPod().get(), new scala.collection.immutable..colon.colon(resource, scala.collection.immutable.Nil..MODULE$));
      }

      PersistentVolumeClaim pvc = (PersistentVolumeClaim)resource;
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Trying to create PersistentVolumeClaim "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PVC_METADATA_NAME..MODULE$, pvc.getMetadata().getName())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"StorageClass ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, pvc.getSpec().getStorageClassName())}))))));
      ((CreateOrReplaceable)((AnyNamespaceOperation)$this.kubernetesClient.persistentVolumeClaims().inNamespace($this.namespace())).resource(pvc)).create();
      return $this.PVC_COUNTER().incrementAndGet();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replacePVCsIfNeeded$2(final PersistentVolumeClaim x2$1, final PersistentVolumeClaim p) {
      boolean var3;
      label18: {
         String var10000 = p.getSpec().getStorageClassName();
         String var2 = x2$1.getSpec().getStorageClassName();
         if (var10000 == null) {
            if (var2 != null) {
               break label18;
            }
         } else if (!var10000.equals(var2)) {
            break label18;
         }

         if (BoxesRunTime.equals(p.getSpec().getResources().getRequests().get("storage"), x2$1.getSpec().getResources().getRequests().get("storage"))) {
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replacePVCsIfNeeded$3(final PersistentVolumeClaim x2$1, final Volume v) {
      boolean var3;
      label25: {
         if (v.getPersistentVolumeClaim() != null) {
            String var10000 = v.getPersistentVolumeClaim().getClaimName();
            String var2 = x2$1.getMetadata().getName();
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

   // $FF: synthetic method
   public static final void $anonfun$replacePVCsIfNeeded$1(final ExecutorPodsAllocator $this, final Buffer reusablePVCs$3, final Pod pod$2, final scala.collection.mutable.Set replacedResources$1, final HasMetadata x0$1) {
      if (x0$1 instanceof PersistentVolumeClaim var7) {
         int index = reusablePVCs$3.indexWhere((p) -> BoxesRunTime.boxToBoolean($anonfun$replacePVCsIfNeeded$2(var7, p)));
         if (index >= 0) {
            Option volume = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(pod$2.getSpec().getVolumes()).asScala().find((v) -> BoxesRunTime.boxToBoolean($anonfun$replacePVCsIfNeeded$3(var7, v)));
            if (volume.nonEmpty()) {
               PersistentVolumeClaim matchedPVC = (PersistentVolumeClaim)reusablePVCs$3.remove(index);
               replacedResources$1.add(var7);
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reuse PersistentVolumeClaim "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PVC_METADATA_NAME..MODULE$, matchedPVC.getMetadata().getName())}))))));
               ((Volume)volume.get()).getPersistentVolumeClaim().setClaimName(matchedPVC.getMetadata().getName());
               BoxedUnit var13 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var12 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replacePVCsIfNeeded$5(final scala.collection.mutable.Set replacedResources$1, final HasMetadata elem) {
      return replacedResources$1.contains(elem);
   }

   public ExecutorPodsAllocator(final SparkConf conf, final SecurityManager secMgr, final KubernetesExecutorBuilder executorBuilder, final KubernetesClient kubernetesClient, final ExecutorPodsSnapshotsStore snapshotsStore, final Clock clock) {
      this.conf = conf;
      this.secMgr = secMgr;
      this.executorBuilder = executorBuilder;
      this.kubernetesClient = kubernetesClient;
      this.snapshotsStore = snapshotsStore;
      this.clock = clock;
      Logging.$init$(this);
      this.EXECUTOR_ID_COUNTER = new AtomicInteger(0);
      this.PVC_COUNTER = new AtomicInteger(0);
      this.maxPVCs = org.apache.spark.util.Utils..MODULE$.isDynamicAllocationEnabled(conf) ? BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package..MODULE$.DYN_ALLOCATION_MAX_EXECUTORS())) : conf.getInt(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_INSTANCES().key(), org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.DEFAULT_NUMBER_EXECUTORS());
      this.podAllocOnPVC = BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_DRIVER_OWN_PVC())) && BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_DRIVER_REUSE_PVC())) && BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_DRIVER_WAIT_TO_REUSE_PVC()));
      this.totalExpectedExecutorsPerResourceProfileId = new ConcurrentHashMap();
      this.rpIdToResourceProfile = new HashMap();
      this.podAllocationSize = BoxesRunTime.unboxToInt(conf.get(Config$.MODULE$.KUBERNETES_ALLOCATION_BATCH_SIZE()));
      this.podAllocationDelay = BoxesRunTime.unboxToLong(conf.get(Config$.MODULE$.KUBERNETES_ALLOCATION_BATCH_DELAY()));
      this.maxPendingPods = BoxesRunTime.unboxToInt(conf.get(Config$.MODULE$.KUBERNETES_MAX_PENDING_PODS()));
      this.podCreationTimeout = scala.math.package..MODULE$.max(this.podAllocationDelay() * 5L, BoxesRunTime.unboxToLong(conf.get(Config$.MODULE$.KUBERNETES_ALLOCATION_EXECUTOR_TIMEOUT())));
      this.driverPodReadinessTimeout = BoxesRunTime.unboxToLong(conf.get(Config$.MODULE$.KUBERNETES_ALLOCATION_DRIVER_READINESS_TIMEOUT()));
      this.executorIdleTimeout = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package..MODULE$.DYN_ALLOCATION_EXECUTOR_IDLE_TIMEOUT())) * 1000L;
      this.namespace = (String)conf.get(Config$.MODULE$.KUBERNETES_NAMESPACE());
      this.kubernetesDriverPodName = (Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_POD_NAME());
      this.shouldDeleteExecutors = BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_DELETE_EXECUTORS()));
      this.driverPod = this.kubernetesDriverPodName().map((name) -> (Pod)scala.Option..MODULE$.apply(((Gettable)((Nameable)this.kubernetesClient.pods().inNamespace(this.namespace())).withName(name)).get()).getOrElse(() -> {
            throw new SparkException("No pod was found named " + name + " in the cluster in the namespace " + this.namespace() + " (this was supposed to be the driver pod.).");
         }));
      this.newlyCreatedExecutors = scala.collection.mutable.LinkedHashMap..MODULE$.empty();
      this.schedulerKnownNewlyCreatedExecs = scala.collection.mutable.LinkedHashMap..MODULE$.empty();
      this.dynamicAllocationEnabled = org.apache.spark.util.Utils..MODULE$.isDynamicAllocationEnabled(conf);
      this.numOutstandingPods = new AtomicInteger();
      this.lastSnapshot = ExecutorPodsSnapshot$.MODULE$.apply();
      this.deletedExecutorIds = scala.Predef..MODULE$.Set().empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
