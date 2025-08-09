package org.apache.spark.scheduler.cluster;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.ExecutorAllocationClient;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskState$;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.deploy.security.HadoopDelegationTokenManager;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.ExecutorLogUrlHandler;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.resource.ResourceAllocator;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.rpc.IsolatedThreadSafeRpcEndpoint;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.scheduler.ExecutorDecommission;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.scheduler.ExecutorDecommissionInfo$;
import org.apache.spark.scheduler.ExecutorKilled$;
import org.apache.spark.scheduler.ExecutorLossReason;
import org.apache.spark.scheduler.ExecutorProcessLost;
import org.apache.spark.scheduler.ExecutorProcessLost$;
import org.apache.spark.scheduler.ExecutorResourceInfo;
import org.apache.spark.scheduler.ExecutorResourcesAmounts;
import org.apache.spark.scheduler.ExecutorResourcesAmounts$;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.LossReasonPending$;
import org.apache.spark.scheduler.MiscellaneousProcessDetails;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.SparkListenerMiscellaneousProcessAdded;
import org.apache.spark.scheduler.TaskDescription;
import org.apache.spark.scheduler.TaskDescription$;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.TaskSchedulerImpl$;
import org.apache.spark.scheduler.TaskSetManager;
import org.apache.spark.scheduler.WorkerOffer;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.SerializableBuffer;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Queue;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015EdaB>}\u0001\u0005\u0005\u0011Q\u0002\u0005\n\u007f\u0002\u0011\t\u0011)A\u0005\u0003sA!\"a\u0010\u0001\u0005\u000b\u0007I\u0011AA!\u0011)\ty\u0005\u0001B\u0001B\u0003%\u00111\t\u0005\b\u0003#\u0002A\u0011AA*\u0011%\ti\u0006\u0001b\u0001\n#\ty\u0006\u0003\u0005\u0002z\u0001\u0001\u000b\u0011BA1\u0011%\tY\b\u0001b\u0001\n#\ty\u0006\u0003\u0005\u0002~\u0001\u0001\u000b\u0011BA1\u0011%\ty\b\u0001b\u0001\n#\t\t\t\u0003\u0005\u0002\n\u0002\u0001\u000b\u0011BAB\u0011%\tY\t\u0001b\u0001\n\u0013\ti\t\u0003\u0005\u0002\u0016\u0002\u0001\u000b\u0011BAH\u0011%\t9\n\u0001b\u0001\n\u0013\tI\n\u0003\u0005\u0002\"\u0002\u0001\u000b\u0011BAN\u0011%\t\u0019\u000b\u0001b\u0001\n\u0013\t)\u000b\u0003\u0005\u0002.\u0002\u0001\u000b\u0011BAT\u0011%\ty\u000b\u0001b\u0001\n\u0013\t\t\f\u0003\u0005\u0002:\u0002\u0001\u000b\u0011BAZ\u0011%\tY\f\u0001b\u0001\n\u0013\t\t\f\u0003\u0005\u0002>\u0002\u0001\u000b\u0011BAZ\u0011%\ty\f\u0001b\u0001\n\u0013\t\t\r\u0003\u0005\u0002p\u0002\u0001\u000b\u0011BAb\u0011%\t\t\u0010\u0001b\u0001\n\u0013\t\u0019\u0010\u0003\u0005\u0003\u0004\u0001\u0001\u000b\u0011BA{\u0011%\u0011y\u0002\u0001b\u0001\n\u0013\u0011\t\u0003\u0003\u0005\u00032\u0001\u0001\u000b\u0011\u0002B\u0012\u0011%\u0011)\u0004\u0001b\u0001\n\u0013\u00119\u0004\u0003\u0005\u0003@\u0001\u0001\u000b\u0011\u0002B\u001d\u0011)\u0011\t\u0005\u0001b\u0001\n\u0003q(1\t\u0005\t\u0005\u001b\u0002\u0001\u0015!\u0003\u0003F!I!\u0011\u000b\u0001C\u0002\u0013E!1\u000b\u0005\t\u00057\u0002\u0001\u0015!\u0003\u0003V!I!Q\f\u0001C\u0002\u0013E!q\f\u0005\t\u0005S\u0002\u0001\u0015!\u0003\u0003b!I!1\u000e\u0001C\u0002\u0013E!Q\u000e\u0005\t\u0005\u001b\u0003\u0001\u0015!\u0003\u0003p!I!q\u0012\u0001A\u0002\u0013E!\u0011\u0013\u0005\n\u00057\u0003\u0001\u0019!C\t\u0005;C\u0001B!+\u0001A\u0003&!1\u0013\u0005\n\u0005[\u0003\u0001\u0019!C\t\u0005_C\u0011Ba/\u0001\u0001\u0004%\tB!0\t\u0011\t\u0005\u0007\u0001)Q\u0005\u0005cC\u0011B!2\u0001\u0001\u0004%\t\"!$\t\u0013\t\u001d\u0007\u00011A\u0005\u0012\t%\u0007\u0002\u0003Bg\u0001\u0001\u0006K!a$\t\u0013\t]\u0007\u00011A\u0005\n\te\u0007\"\u0003Bq\u0001\u0001\u0007I\u0011\u0002Br\u0011!\u00119\u000f\u0001Q!\n\tm\u0007\"\u0003Bv\u0001\t\u0007I\u0011\u0002Bw\u0011!\u0019\t\u0001\u0001Q\u0001\n\t=\b\"CB\u0002\u0001\u0001\u0007I\u0011BB\u0003\u0011%\u0019I\u0002\u0001a\u0001\n\u0013\u0019Y\u0002\u0003\u0005\u0004 \u0001\u0001\u000b\u0015BB\u0004\u0011%\u0019\t\u0003\u0001b\u0001\n\u0013\u0019\u0019\u0003\u0003\u0005\u0004.\u0001\u0001\u000b\u0011BB\u0013\u0011%\u0019y\u0003\u0001b\u0001\n\u0013\u0019\t\u0004\u0003\u0005\u00046\u0001\u0001\u000b\u0011BB\u001a\r\u0019\u00199\u0004\u0001\u0001\u0004:!9\u0011\u0011\u000b\u001e\u0005\u0002\r\u0005\u0003\"CA u\t\u0007I\u0011IA!\u0011!\tyE\u000fQ\u0001\n\u0005\r\u0003\"CB$u\t\u0007I\u0011CB%\u0011!\u0019\u0019F\u000fQ\u0001\n\r-\u0003BCB+u!\u0015\r\u0011\"\u0003\u0004X!I1\u0011\r\u001eC\u0002\u0013%11\r\u0005\t\u0007cR\u0004\u0015!\u0003\u0004f!911\u000f\u001e\u0005B\rU\u0004bBB<u\u0011\u00053\u0011\u0010\u0005\b\u0007\u000fSD\u0011IBE\u0011\u001d\u0019)J\u000fC\u0005\u0007kBqaa&;\t\u0013\u0019I\nC\u0004\u0004*j\"\tea+\t\u000f\rU%\b\"\u0003\u00042\"91Q\u0017\u001e\u0005\n\r]\u0006bBBlu\u0011%1\u0011\u001c\u0005\b\u0007OTD\u0011BBu\u0011\u001d\u00199P\u000fC\t\u0007sD\u0011b!@\u0001\u0005\u0004%\taa@\t\u0011\u0011\u001d\u0001\u0001)A\u0005\t\u0003Aq\u0001\"\u0003\u0001\t#\t)\u000bC\u0004\u0005\f\u0001!\t\u0005\"\u0004\t\u000f\u0011\u0005\u0002\u0001\"\u0011\u0004v!9A1\u0005\u0001\u0005\u0012\r\u0005\u0003b\u0002C\u0013\u0001\u0011\u00051Q\u000f\u0005\b\tO\u0001A\u0011IB;\u0011\u001d!I\u0003\u0001C!\tWA\u0001\u0002\"\r\u0001\t#q8Q\u000f\u0005\b\tg\u0001A\u0011IB;\u0011\u001d!)\u0004\u0001C!\toAq\u0001\"\u0012\u0001\t\u0003\"9\u0005C\u0004\u0004X\u0002!\t\u0002\"\u0013\t\u000f\r\u001d\b\u0001\"\u0005\u0005P!9Aq\u000b\u0001\u0005\u0002\u0011e\u0003b\u0002C.\u0001\u0011\u0005C\u0011\f\u0005\b\t;\u0002A\u0011\tC0\u0011\u001d!\t\u0007\u0001C\u0001\tGBq\u0001b\u001a\u0001\t\u0003\"I\u0007C\u0004\u0005p\u0001!\t\u0005\"\u001d\t\u000f\u0011]\u0004\u0001\"\u0001\u0005z!IAQ\u0011\u0001\u0005\u0002\u0005\u0005Aq\u0011\u0005\b\t\u001b\u0003A\u0011\u0001CH\u0011\u001d!\u0019\n\u0001C#\t+Cq\u0001b'\u0001\t\u000b\"i\nC\u0004\u0005,\u0002!I\u0001\",\t\u000f\u0011]\u0006\u0001\"\u0003\u0005:\"9A1\u0019\u0001\u0005\u0012\u0011\u0015\u0007b\u0002Cl\u0001\u0011%A\u0011\u001c\u0005\b\t?\u0004AQ\tCq\u0011\u001d!y\u000f\u0001C\t\tcDq\u0001\">\u0001\t\u000b\"9\u0010C\u0004\u0005|\u0002!)\u0005\"@\t\u000f\u0015\u0005\u0001\u0001\"\u0005\u0006\u0004!9QQ\u0001\u0001\u0005\u0012\u0015\u001d\u0001bBC\u0007\u0001\u0011EQq\u0002\u0005\b\u000b#\u0001A\u0011CC\n\u0011\u001d)Y\u0002\u0001C\u0005\u000b;Aq!b\u000f\u0001\t\u0003*idB\u0005\u0006ZqD\t!!\u0001\u0006\\\u0019A1\u0010 E\u0001\u0003\u0003)i\u0006C\u0004\u0002R]$\t!b\u0018\t\u0013\u0015\u0005tO1A\u0005\u0002\u0015\r\u0004\u0002CC8o\u0002\u0006I!\"\u001a\u0003;\r{\u0017M]:f\u000fJ\f\u0017N\\3e'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012T!! @\u0002\u000f\rdWo\u001d;fe*\u0019q0!\u0001\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(\u0002BA\u0002\u0003\u000b\tQa\u001d9be.TA!a\u0002\u0002\n\u00051\u0011\r]1dQ\u0016T!!a\u0003\u0002\u0007=\u0014xmE\u0005\u0001\u0003\u001f\tY\"a\t\u0002,A!\u0011\u0011CA\f\u001b\t\t\u0019B\u0003\u0002\u0002\u0016\u0005)1oY1mC&!\u0011\u0011DA\n\u0005\u0019\te.\u001f*fMB!\u0011QDA\u0010\u001b\t\t\t!\u0003\u0003\u0002\"\u0005\u0005!\u0001G#yK\u000e,Ho\u001c:BY2|7-\u0019;j_:\u001cE.[3oiB!\u0011QEA\u0014\u001b\u0005q\u0018bAA\u0015}\n\u00012k\u00195fIVdWM\u001d\"bG.,g\u000e\u001a\t\u0005\u0003[\t\u0019$\u0004\u0002\u00020)!\u0011\u0011GA\u0001\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA\u001b\u0003_\u0011q\u0001T8hO&twm\u0001\u0001\u0011\t\u0005\u0015\u00121H\u0005\u0004\u0003{q(!\u0005+bg.\u001c6\r[3ek2,'/S7qY\u00061!\u000f]2F]Z,\"!a\u0011\u0011\t\u0005\u0015\u00131J\u0007\u0003\u0003\u000fRA!!\u0013\u0002\u0002\u0005\u0019!\u000f]2\n\t\u00055\u0013q\t\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wA\u00051A(\u001b8jiz\"b!!\u0016\u0002Z\u0005m\u0003cAA,\u00015\tA\u0010\u0003\u0004\u0000\t\u0001\u0007\u0011\u0011\b\u0005\b\u0003\u007f!\u0001\u0019AA\"\u00039!x\u000e^1m\u0007>\u0014XmQ8v]R,\"!!\u0019\u0011\t\u0005\r\u0014QO\u0007\u0003\u0003KRA!a\u001a\u0002j\u00051\u0011\r^8nS\u000eTA!a\u001b\u0002n\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u0005=\u0014\u0011O\u0001\u0005kRLGN\u0003\u0002\u0002t\u0005!!.\u0019<b\u0013\u0011\t9(!\u001a\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u0003=!x\u000e^1m\u0007>\u0014XmQ8v]R\u0004\u0013\u0001\u0007;pi\u0006d'+Z4jgR,'/\u001a3Fq\u0016\u001cW\u000f^8sg\u0006IBo\u001c;bYJ+w-[:uKJ,G-\u0012=fGV$xN]:!\u0003\u0011\u0019wN\u001c4\u0016\u0005\u0005\r\u0005\u0003BA\u000f\u0003\u000bKA!a\"\u0002\u0002\tI1\u000b]1sW\u000e{gNZ\u0001\u0006G>tg\rI\u0001\u0012[\u0006D(\u000b]2NKN\u001c\u0018mZ3TSj,WCAAH!\u0011\t\t\"!%\n\t\u0005M\u00151\u0003\u0002\u0004\u0013:$\u0018AE7bqJ\u00038-T3tg\u0006<WmU5{K\u0002\n\u0011\u0003Z3gCVdG/Q:l)&lWm\\;u+\t\tY\n\u0005\u0003\u0002F\u0005u\u0015\u0002BAP\u0003\u000f\u0012!B\u00159d)&lWm\\;u\u0003I!WMZ1vYR\f5o\u001b+j[\u0016|W\u000f\u001e\u0011\u0002'}k\u0017N\u001c*fO&\u001cH/\u001a:fIJ\u000bG/[8\u0016\u0005\u0005\u001d\u0006\u0003BA\t\u0003SKA!a+\u0002\u0014\t1Ai\\;cY\u0016\fAcX7j]J+w-[:uKJ,GMU1uS>\u0004\u0013AG7bqJ+w-[:uKJ,GmV1ji&tw\rV5nK:\u001bXCAAZ!\u0011\t\t\"!.\n\t\u0005]\u00161\u0003\u0002\u0005\u0019>tw-A\u000enCb\u0014VmZ5ti\u0016\u0014X\rZ,bSRLgn\u001a+j[\u0016t5\u000fI\u0001\rGJ,\u0017\r^3US6,gj]\u0001\u000eGJ,\u0017\r^3US6,gj\u001d\u0011\u0002\u001f\u0015DXmY;u_J$\u0015\r^1NCB,\"!a1\u0011\u0011\u0005\u0015\u0017qZAj\u0003Sl!!a2\u000b\t\u0005%\u00171Z\u0001\b[V$\u0018M\u00197f\u0015\u0011\ti-a\u0005\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002R\u0006\u001d'a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0003+\f\u0019O\u0004\u0003\u0002X\u0006}\u0007\u0003BAm\u0003'i!!a7\u000b\t\u0005u\u0017qG\u0001\u0007yI|w\u000e\u001e \n\t\u0005\u0005\u00181C\u0001\u0007!J,G-\u001a4\n\t\u0005\u0015\u0018q\u001d\u0002\u0007'R\u0014\u0018N\\4\u000b\t\u0005\u0005\u00181\u0003\t\u0005\u0003/\nY/C\u0002\u0002nr\u0014A\"\u0012=fGV$xN\u001d#bi\u0006\f\u0001#\u001a=fGV$xN\u001d#bi\u0006l\u0015\r\u001d\u0011\u0002SI,\u0017/^3ti\u0016$Gk\u001c;bY\u0016CXmY;u_J\u001c\b+\u001a:SKN|WO]2f!J|g-\u001b7f+\t\t)\u0010\u0005\u0005\u0002F\u0006=\u0017q_AH!\u0011\tI0a@\u000e\u0005\u0005m(\u0002BA\u007f\u0003\u0003\t\u0001B]3t_V\u00148-Z\u0005\u0005\u0005\u0003\tYPA\bSKN|WO]2f!J|g-\u001b7f\u0003)\u0012X-];fgR,G\rV8uC2,\u00050Z2vi>\u00148\u000fU3s%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u0002Bs\u0001\u0007B\u0004\u00053\u0011Y\u0002\u0005\u0003\u0003\n\tUQB\u0001B\u0006\u0015\u0011\tYG!\u0004\u000b\t\t=!\u0011C\u0001\u000bC:tw\u000e^1uS>t'B\u0001B\n\u0003\u0015Q\u0017M^1y\u0013\u0011\u00119Ba\u0003\u0003\u0013\u001d+\u0018M\u001d3fI\nK\u0018!\u0002<bYV,\u0017E\u0001B\u000f\u0003\t\u001au.\u0019:tK\u001e\u0013\u0018-\u001b8fIN\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$g\u0006\u001e5jg\u0006\u0001R\r_3d%\u0016\fX/Z:u)&lWm]\u000b\u0003\u0005G\u0001\u0002\"!2\u0002P\u0006=%Q\u0005\t\u0007\u0003\u000b\u00149Ca\u000b\n\t\t%\u0012q\u0019\u0002\u0006#V,W/\u001a\t\t\u0003#\u0011i#a$\u00024&!!qFA\n\u0005\u0019!V\u000f\u001d7fe\u0005\tR\r_3d%\u0016\fX/Z:u)&lWm\u001d\u0011)\u000fi\u00119A!\u0007\u0003\u001c\u0005YA.[:uK:,'OQ;t+\t\u0011I\u0004\u0005\u0003\u0002&\tm\u0012b\u0001B\u001f}\nyA*\u001b<f\u0019&\u001cH/\u001a8fe\n+8/\u0001\u0007mSN$XM\\3s\u0005V\u001c\b%\u0001\rfq\u0016\u001cW\u000f^8sgB+g\u000eZ5oOR{'+Z7pm\u0016,\"A!\u0012\u0011\u0011\u0005\u0015\u0017qZAj\u0005\u000f\u0002B!!\u0005\u0003J%!!1JA\n\u0005\u001d\u0011un\u001c7fC:\f\u0011$\u001a=fGV$xN]:QK:$\u0017N\\4U_J+Wn\u001c<fA!:aDa\u0002\u0003\u001a\tm\u0011AG3yK\u000e,Ho\u001c:t!\u0016tG-\u001b8h\u0019>\u001c8OU3bg>tWC\u0001B+!\u0019\t)Ma\u0016\u0002T&!!\u0011LAd\u0005\u001dA\u0015m\u001d5TKR\f1$\u001a=fGV$xN]:QK:$\u0017N\\4M_N\u001c(+Z1t_:\u0004\u0013\u0001H3yK\u000e,Ho\u001c:t!\u0016tG-\u001b8h\t\u0016\u001cw.\\7jgNLwN\\\u000b\u0003\u0005C\u0002\u0002\"!2\u0002P\u0006M'1\r\t\u0005\u0003K\u0011)'C\u0002\u0003hy\u0014\u0001$\u0012=fGV$xN\u001d#fG>lW.[:tS>t\u0017J\u001c4p\u0003u)\u00070Z2vi>\u00148\u000fU3oI&tw\rR3d_6l\u0017n]:j_:\u0004\u0013aI;oW:|wO\\#yK\u000e,Ho\u001c:t!\u0016tG-\u001b8h\t\u0016\u001cw.\\7jgNLwN\\\u000b\u0003\u0005_\u0002\u0002B!\u001d\u0003\u0004\u0006M'qQ\u0007\u0003\u0005gRAA!\u001e\u0003x\u0005)1-Y2iK*!!\u0011\u0010B>\u0003\u0019\u0019w.\\7p]*!!Q\u0010B@\u0003\u00199wn\\4mK*\u0011!\u0011Q\u0001\u0004G>l\u0017\u0002\u0002BC\u0005g\u0012QaQ1dQ\u0016\u0004\"\"!\u0005\u0003\n\n\r$q\tB$\u0013\u0011\u0011Y)a\u0005\u0003\rQ+\b\u000f\\34\u0003\u0011*hn\u001b8po:,\u00050Z2vi>\u00148\u000fU3oI&tw\rR3d_6l\u0017n]:j_:\u0004\u0013A\u0006:q\u0011>\u001cH\u000fV8M_\u000e\fG\u000eV1tW\u000e{WO\u001c;\u0016\u0005\tM\u0005\u0003CAk\u0005+\u000byI!'\n\t\t]\u0015q\u001d\u0002\u0004\u001b\u0006\u0004\b\u0003CAk\u0005+\u000b\u0019.a$\u00025I\u0004\bj\\:u)>dunY1m)\u0006\u001c8nQ8v]R|F%Z9\u0015\t\t}%Q\u0015\t\u0005\u0003#\u0011\t+\u0003\u0003\u0003$\u0006M!\u0001B+oSRD\u0011Ba*'\u0003\u0003\u0005\rAa%\u0002\u0007a$\u0013'A\fsa\"{7\u000f\u001e+p\u0019>\u001c\u0017\r\u001c+bg.\u001cu.\u001e8uA!:qEa\u0002\u0003\u001a\tm\u0011!\u000b8v[2{7-\u00197jif\fu/\u0019:f)\u0006\u001c8n\u001d)feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE-\u0006\u0002\u00032BA!1\u0017B]\u0003\u001f\u000by)\u0004\u0002\u00036*!!qWAf\u0003%IW.\\;uC\ndW-\u0003\u0003\u0003\u0018\nU\u0016!\f8v[2{7-\u00197jif\fu/\u0019:f)\u0006\u001c8n\u001d)feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LEm\u0018\u0013fcR!!q\u0014B`\u0011%\u00119+KA\u0001\u0002\u0004\u0011\t,\u0001\u0016ok6dunY1mSRL\u0018i^1sKR\u000b7o[:QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0011)\u000f)\u00129A!\u0007\u0003\u001c\u0005A2-\u001e:sK:$X\t_3dkR|'/\u00133D_VtG/\u001a:\u00029\r,(O]3oi\u0016CXmY;u_JLEmQ8v]R,'o\u0018\u0013fcR!!q\u0014Bf\u0011%\u00119\u000bLA\u0001\u0002\u0004\ty)A\rdkJ\u0014XM\u001c;Fq\u0016\u001cW\u000f^8s\u0013\u0012\u001cu.\u001e8uKJ\u0004\u0003fA\u0017\u0003RB!\u0011\u0011\u0003Bj\u0013\u0011\u0011).a\u0005\u0003\u0011Y|G.\u0019;jY\u0016\fqbY;se\u0016tG\u000fT8h\u0019\u00164X\r\\\u000b\u0003\u00057\u0004b!!\u0005\u0003^\u0006M\u0017\u0002\u0002Bp\u0003'\u0011aa\u00149uS>t\u0017aE2veJ,g\u000e\u001e'pO2+g/\u001a7`I\u0015\fH\u0003\u0002BP\u0005KD\u0011Ba*0\u0003\u0003\u0005\rAa7\u0002!\r,(O]3oi2{w\rT3wK2\u0004\u0003f\u0001\u0019\u0003R\u0006\u0001B-\u001a7fO\u0006$\u0018n\u001c8U_.,gn]\u000b\u0003\u0005_\u0004b!a\u0019\u0003r\nU\u0018\u0002\u0002Bz\u0003K\u0012q\"\u0011;p[&\u001c'+\u001a4fe\u0016t7-\u001a\t\u0007\u0003#\u00119Pa?\n\t\te\u00181\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u0003#\u0011i0\u0003\u0003\u0003\u0000\u0006M!\u0001\u0002\"zi\u0016\f\u0011\u0003Z3mK\u001e\fG/[8o)>\\WM\\:!\u0003Y!W\r\\3hCRLwN\u001c+pW\u0016tW*\u00198bO\u0016\u0014XCAB\u0004!\u0019\t\tB!8\u0004\nA!11BB\u000b\u001b\t\u0019iA\u0003\u0003\u0004\u0010\rE\u0011\u0001C:fGV\u0014\u0018\u000e^=\u000b\t\rM\u0011\u0011A\u0001\u0007I\u0016\u0004Hn\\=\n\t\r]1Q\u0002\u0002\u001d\u0011\u0006$wn\u001c9EK2,w-\u0019;j_:$vn[3o\u001b\u0006t\u0017mZ3s\u0003i!W\r\\3hCRLwN\u001c+pW\u0016tW*\u00198bO\u0016\u0014x\fJ3r)\u0011\u0011yj!\b\t\u0013\t\u001dF'!AA\u0002\r\u001d\u0011a\u00063fY\u0016<\u0017\r^5p]R{7.\u001a8NC:\fw-\u001a:!\u00031\u0011XM^5wKRC'/Z1e+\t\u0019)\u0003\u0005\u0003\u0004(\r%RBAA5\u0013\u0011\u0019Y#!\u001b\u00031M\u001b\u0007.\u001a3vY\u0016$W\t_3dkR|'oU3sm&\u001cW-A\u0007sKZLg/\u001a+ie\u0016\fG\rI\u0001\u000fG2,\u0017M\\;q'\u0016\u0014h/[2f+\t\u0019\u0019\u0004\u0005\u0004\u0002\u0012\tu7QE\u0001\u0010G2,\u0017M\\;q'\u0016\u0014h/[2fA\tqAI]5wKJ,e\u000e\u001a9pS:$8c\u0002\u001e\u0002\u0010\rm\u00121\u0006\t\u0005\u0003\u000b\u001ai$\u0003\u0003\u0004@\u0005\u001d#!H%t_2\fG/\u001a3UQJ,\u0017\rZ*bM\u0016\u0014\u0006oY#oIB|\u0017N\u001c;\u0015\u0005\r\r\u0003cAB#u5\t\u0001!A\nbI\u0012\u0014Xm]:U_\u0016CXmY;u_JLE-\u0006\u0002\u0004LAA\u0011QYAh\u0007\u001b\n\u0019\u000e\u0005\u0003\u0002F\r=\u0013\u0002BB)\u0003\u000f\u0012!B\u00159d\u0003\u0012$'/Z:t\u0003Q\tG\r\u001a:fgN$v.\u0012=fGV$xN]%eA\u0005y1\u000f]1sWB\u0013x\u000e]3si&,7/\u0006\u0002\u0004ZA1!1WB.\u0007?JAa!\u0018\u00036\nA\u0011I\u001d:bsN+\u0017\u000f\u0005\u0005\u0002\u0012\t5\u00121[Aj\u00035awnZ+sY\"\u000bg\u000e\u001a7feV\u00111Q\r\t\u0005\u0007O\u001ai'\u0004\u0002\u0004j)!11NA\u0001\u0003!)\u00070Z2vi>\u0014\u0018\u0002BB8\u0007S\u0012Q#\u0012=fGV$xN\u001d'pOV\u0013H\u000eS1oI2,'/\u0001\bm_\u001e,&\u000f\u001c%b]\u0012dWM\u001d\u0011\u0002\u000f=t7\u000b^1siR\u0011!qT\u0001\be\u0016\u001cW-\u001b<f+\t\u0019Y\b\u0005\u0005\u0002\u0012\ru4\u0011\u0011BP\u0013\u0011\u0019y(a\u0005\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004B!!\u0005\u0004\u0004&!1QQA\n\u0005\r\te._\u0001\u0010e\u0016\u001cW-\u001b<f\u0003:$'+\u001a9msR!11PBF\u0011\u001d\u0019i)\u0012a\u0001\u0007\u001f\u000bqaY8oi\u0016DH\u000f\u0005\u0003\u0002F\rE\u0015\u0002BBJ\u0003\u000f\u0012aB\u00159d\u0007\u0006dGnQ8oi\u0016DH/\u0001\u0006nC.,wJ\u001a4feN\f\u0001CY;jY\u0012<vN]6fe>3g-\u001a:\u0015\r\rm5\u0011UBS!\u0011\t)c!(\n\u0007\r}ePA\u0006X_J\\WM](gM\u0016\u0014\bbBBR\u000f\u0002\u0007\u00111[\u0001\u000bKb,7-\u001e;pe&#\u0007bBBT\u000f\u0002\u0007\u0011\u0011^\u0001\rKb,7-\u001e;pe\u0012\u000bG/Y\u0001\u000f_:$\u0015n]2p]:,7\r^3e)\u0011\u0011yj!,\t\u000f\r=\u0006\n1\u0001\u0004N\u0005i!/Z7pi\u0016\fE\r\u001a:fgN$BAa(\u00044\"911U%A\u0002\u0005M\u0017a\u00037bk:\u001c\u0007\u000eV1tWN$BAa(\u0004:\"911\u0018&A\u0002\ru\u0016!\u0002;bg.\u001c\bCBB`\u0007\u0013\u001cyM\u0004\u0003\u0004B\u000e\u0015g\u0002BAm\u0007\u0007L!!!\u0006\n\t\r\u001d\u00171C\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0019Ym!4\u0003\u0007M+\u0017O\u0003\u0003\u0004H\u0006M\u0001CBB`\u0007\u0013\u001c\t\u000e\u0005\u0003\u0002&\rM\u0017bABk}\nyA+Y:l\t\u0016\u001c8M]5qi&|g.\u0001\bsK6|g/Z#yK\u000e,Ho\u001c:\u0015\r\t}51\\Bo\u0011\u001d\u0019\u0019k\u0013a\u0001\u0003'Dqaa8L\u0001\u0004\u0019\t/\u0001\u0004sK\u0006\u001cxN\u001c\t\u0005\u0003K\u0019\u0019/C\u0002\u0004fz\u0014!#\u0012=fGV$xN\u001d'pgN\u0014V-Y:p]\u0006a!/Z7pm\u0016<vN]6feRA!qTBv\u0007_\u001c\u0019\u0010C\u0004\u0004n2\u0003\r!a5\u0002\u0011]|'o[3s\u0013\u0012Dqa!=M\u0001\u0004\t\u0019.\u0001\u0003i_N$\bbBB{\u0019\u0002\u0007\u00111[\u0001\b[\u0016\u001c8/Y4f\u0003=!\u0017n]1cY\u0016,\u00050Z2vi>\u0014H\u0003\u0002B$\u0007wDqaa)N\u0001\u0004\t\u0019.\u0001\bee&4XM]#oIB|\u0017N\u001c;\u0016\u0005\u0011\u0005\u0001\u0003BA#\t\u0007IA\u0001\"\u0002\u0002H\tq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0017a\u00043sSZ,'/\u00128ea>Lg\u000e\u001e\u0011\u0002%5LgNU3hSN$XM]3e%\u0006$\u0018n\\\u0001\u0016I\u0016\u001cw.\\7jgNLwN\\#yK\u000e,Ho\u001c:t)!!y\u0001\"\u0005\u0005\u001a\u0011u\u0001CBB`\u0007\u0013\f\u0019\u000eC\u0004\u0005\u0014E\u0003\r\u0001\"\u0006\u0002+\u0015DXmY;u_J\u001c\u0018I\u001c3EK\u000e|W.\u00138g_B1\u0011\u0011\u0003B|\t/\u0001\u0002\"!\u0005\u0003.\u0005M'1\r\u0005\b\t7\t\u0006\u0019\u0001B$\u0003a\tGM[;tiR\u000b'oZ3u\u001dVlW\t_3dkR|'o\u001d\u0005\b\t?\t\u0006\u0019\u0001B$\u0003M!(/[4hKJ,GMQ=Fq\u0016\u001cW\u000f^8s\u0003\u0015\u0019H/\u0019:u\u0003Q\u0019'/Z1uK\u0012\u0013\u0018N^3s\u000b:$\u0007o\\5oi\u0006i1\u000f^8q\u000bb,7-\u001e;peN\fAa\u001d;pa\u00069R\u000f\u001d3bi\u0016,\u00050Z2vi>\u00148\u000fT8h\u0019\u00164X\r\u001c\u000b\u0005\u0005?#i\u0003C\u0004\u00050Y\u0003\r!a5\u0002\u00111|w\rT3wK2\fQA]3tKR\fAB]3wSZ,wJ\u001a4feN\f\u0001b[5mYR\u000b7o\u001b\u000b\u000b\u0005?#I\u0004\"\u0010\u0005@\u0011\r\u0003b\u0002C\u001e3\u0002\u0007\u00111W\u0001\u0007i\u0006\u001c8.\u00133\t\u000f\r\r\u0016\f1\u0001\u0002T\"9A\u0011I-A\u0002\t\u001d\u0013aD5oi\u0016\u0014(/\u001e9u)\"\u0014X-\u00193\t\u000f\r}\u0017\f1\u0001\u0002T\u0006\u0011B-\u001a4bk2$\b+\u0019:bY2,G.[:n)\t\ty\t\u0006\u0004\u0003 \u0012-CQ\n\u0005\b\u0007G[\u0006\u0019AAj\u0011\u001d\u0019yn\u0017a\u0001\u0007C$\u0002Ba(\u0005R\u0011MCQ\u000b\u0005\b\u0007[d\u0006\u0019AAj\u0011\u001d\u0019\t\u0010\u0018a\u0001\u0003'Dqa!>]\u0001\u0004\t\u0019.A\u000ftk\u001a4\u0017nY5f]R\u0014Vm]8ve\u000e,7OU3hSN$XM]3e)\t\u00119%A\u0004jgJ+\u0017\rZ=\u0002\u001d\u001d,G/\u0012=fGV$xN]%egR\u0011AqB\u0001\u001fO\u0016$X\t_3dkR|'o],ji\"\u0014VmZ5tiJ\fG/[8o)N$\"\u0001\"\u001a\u0011\u0011\u0005U'QSAj\u0003g\u000b\u0001#[:Fq\u0016\u001cW\u000f^8s\u0003\u000e$\u0018N^3\u0015\t\t\u001dC1\u000e\u0005\b\t[\n\u0007\u0019AAj\u0003\tIG-A\u000bnCbtU/\\\"p]\u000e,(O]3oiR\u000b7o[:\u0015\t\u0005=E1\u000f\u0005\b\tk\u0012\u0007\u0019AA|\u0003\t\u0011\b/A\u000fhKR,\u00050Z2vi>\u0014\u0018I^1jY\u0006\u0014G.\u001a*fg>,(oY3t)\u0011!Y\bb!\u0011\u0011\u0005U'QSAj\t{\u0002B!!\n\u0005\u0000%\u0019A\u0011\u0011@\u0003)\u0015CXmY;u_J\u0014Vm]8ve\u000e,\u0017J\u001c4p\u0011\u001d\u0019\u0019k\u0019a\u0001\u0003'\f\u0001dZ3u\u000bb,7-\u001e;pe\u00063\u0018-\u001b7bE2,7\t];t)\u0011!I\tb#\u0011\r\u0005E!Q\\AH\u0011\u001d\u0019\u0019\u000b\u001aa\u0001\u0003'\fAdZ3u\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE\r\u0006\u0003\u0002\u0010\u0012E\u0005bBBRK\u0002\u0007\u00111[\u0001\u0011e\u0016\fX/Z:u\u000bb,7-\u001e;peN$BAa\u0012\u0005\u0018\"9A\u0011\u00144A\u0002\u0005=\u0015A\u00068v[\u0006#G-\u001b;j_:\fG.\u0012=fGV$xN]:\u0002+I,\u0017/^3tiR{G/\u00197Fq\u0016\u001cW\u000f^8sgRA!q\tCP\tK#9\u000bC\u0004\u0005\"\u001e\u0004\r\u0001b)\u0002?I,7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE\rV8Ok6,\u00050Z2vi>\u00148\u000f\u0005\u0005\u0002V\nU\u0015qRAH\u0011\u001d\u0011ik\u001aa\u0001\tGCq\u0001\"+h\u0001\u0004\u0011\u0019*\u0001\u000bi_N$Hk\u001c'pG\u0006dG+Y:l\u0007>,h\u000e^\u0001\u0017kB$\u0017\r^3Fq\u0016\u001c'+Z9vKN$H+[7fgR1!q\u0014CX\tgCq\u0001\"-i\u0001\u0004!\u0019+\u0001\u0006pY\u0012\u0004&o\u001c4jY\u0016Dq\u0001\".i\u0001\u0004!\u0019+\u0001\u0006oK^\u0004&o\u001c4jY\u0016\fQ#\u001e9eCR,W\t_3d%\u0016\fX/Z:u)&lW\r\u0006\u0004\u0004\u0002\u0012mFq\u0018\u0005\b\t{K\u0007\u0019AAH\u0003%\u0001(o\u001c4jY\u0016LE\rC\u0004\u0005B&\u0004\r!a$\u0002\u000b\u0011,G\u000e^1\u0002/\u0011|'+Z9vKN$Hk\u001c;bY\u0016CXmY;u_J\u001cH\u0003\u0002Cd\t#\u0004b\u0001\"3\u0005N\n\u001dSB\u0001Cf\u0015\u0011\tY'a\u0005\n\t\u0011=G1\u001a\u0002\u0007\rV$XO]3\t\u000f\u0011M'\u000e1\u0001\u0005V\u0006Y\"/Z:pkJ\u001cW\r\u0015:pM&dW\rV8U_R\fG.\u0012=fGN\u0004\u0002\"!6\u0003\u0016\u0006]\u0018qR\u0001\u0010C\u0012TWo\u001d;Fq\u0016\u001cW\u000f^8sgR!Aq\u0019Cn\u0011\u001d!in\u001ba\u0001\t\u001f\t1\"\u001a=fGV$xN]%eg\u0006i1.\u001b7m\u000bb,7-\u001e;peN$\"\u0002b\u0004\u0005d\u0012\u0015Hq\u001dCv\u0011\u001d!i\u000e\u001ca\u0001\t\u001fAq\u0001b\u0007m\u0001\u0004\u00119\u0005C\u0004\u0005j2\u0004\rAa\u0012\u0002\u001b\r|WO\u001c;GC&dWO]3t\u0011%!i\u000f\u001cI\u0001\u0002\u0004\u00119%A\u0003g_J\u001cW-A\be_.KG\u000e\\#yK\u000e,Ho\u001c:t)\u0011!9\rb=\t\u000f\u0011uW\u000e1\u0001\u0005\u0010\u0005YB-Z2p[6L7o]5p]\u0016CXmY;u_J\u001cxJ\u001c%pgR$BAa\u0012\u0005z\"91\u0011\u001f8A\u0002\u0005M\u0017aE6jY2,\u00050Z2vi>\u00148o\u00148I_N$H\u0003\u0002B$\t\u007fDqa!=p\u0001\u0004\t\u0019.\u0001\nde\u0016\fG/\u001a+pW\u0016tW*\u00198bO\u0016\u0014HCAB\u0004\u0003Y)\b\u000fZ1uK\u0012+G.Z4bi&|g\u000eV8lK:\u001cH\u0003\u0002BP\u000b\u0013Aq!b\u0003r\u0001\u0004\u0011)0\u0001\u0004u_.,gn]\u0001\u0018GV\u0014(/\u001a8u\t\u0016dWmZ1uS>tGk\\6f]N,\"A!>\u0002%%\u001cX\t_3dkR|'/\u0012=dYV$W\r\u001a\u000b\u0007\u0005\u000f*)\"b\u0006\t\u000f\r\r6\u000f1\u0001\u0002T\"9Q\u0011D:A\u0002\u0005M\u0017\u0001\u00035pgRt\u0017-\\3\u0002\u0011]LG\u000f\u001b'pG.,B!b\b\u0006&Q!Q\u0011EC\u0019!\u0011)\u0019#\"\n\r\u0001\u00119Qq\u0005;C\u0002\u0015%\"!\u0001+\u0012\t\u0015-2\u0011\u0011\t\u0005\u0003#)i#\u0003\u0003\u00060\u0005M!a\u0002(pi\"Lgn\u001a\u0005\t\u000bg!H\u00111\u0001\u00066\u0005\u0011aM\u001c\t\u0007\u0003#)9$\"\t\n\t\u0015e\u00121\u0003\u0002\ty\tLh.Y7f}\u0005\tr-\u001a;UCN\\G\u000b\u001b:fC\u0012$U/\u001c9\u0015\r\u0015}RQKC,!\u0019\t\tB!8\u0006BA!Q1IC)\u001b\t))E\u0003\u0003\u0006H\u0015%\u0013A\u0001<2\u0015\u0011)Y%\"\u0014\u0002\u0007\u0005\u0004\u0018N\u0003\u0003\u0006P\u0005\u0005\u0011AB:uCR,8/\u0003\u0003\u0006T\u0015\u0015#\u0001\u0005+ie\u0016\fGm\u0015;bG.$&/Y2f\u0011\u001d!Y$\u001ea\u0001\u0003gCqaa)v\u0001\u0004\t\u0019.A\u000fD_\u0006\u00148/Z$sC&tW\rZ*dQ\u0016$W\u000f\\3s\u0005\u0006\u001c7.\u001a8e!\r\t9f^\n\u0004o\u0006=ACAC.\u00035)e\n\u0012)P\u0013:#vLT!N\u000bV\u0011QQ\r\t\u0005\u000bO*i'\u0004\u0002\u0006j)!Q1NA9\u0003\u0011a\u0017M\\4\n\t\u0005\u0015X\u0011N\u0001\u000f\u000b:#\u0005kT%O)~s\u0015)T#!\u0001"
)
public class CoarseGrainedSchedulerBackend implements ExecutorAllocationClient, SchedulerBackend, Logging {
   public final TaskSchedulerImpl org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler;
   private final RpcEnv rpcEnv;
   private final AtomicInteger totalCoreCount;
   private final AtomicInteger totalRegisteredExecutors;
   private final SparkConf conf;
   private final int org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$maxRpcMessageSize;
   private final RpcTimeout defaultAskTimeout;
   private final double _minRegisteredRatio;
   private final long maxRegisteredWaitingTimeNs;
   private final long createTimeNs;
   private final HashMap org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap;
   @GuardedBy("CoarseGrainedSchedulerBackend.this")
   private final HashMap requestedTotalExecutorsPerResourceProfile;
   @GuardedBy("CoarseGrainedSchedulerBackend.this")
   private final HashMap org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$execRequestTimes;
   private final LiveListenerBus org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus;
   @GuardedBy("CoarseGrainedSchedulerBackend.this")
   private final HashMap executorsPendingToRemove;
   private final HashSet executorsPendingLossReason;
   private final HashMap executorsPendingDecommission;
   private final Cache unknownExecutorsPendingDecommission;
   @GuardedBy("CoarseGrainedSchedulerBackend.this")
   private Map rpHostToLocalTaskCount;
   @GuardedBy("CoarseGrainedSchedulerBackend.this")
   private Map numLocalityAwareTasksPerResourceProfileId;
   private volatile int currentExecutorIdCounter;
   private volatile Option org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel;
   private final AtomicReference org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens;
   private Option delegationTokenManager;
   private final ScheduledExecutorService org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$reviveThread;
   private final Option cleanupService;
   private final RpcEndpointRef driverEndpoint;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private String org$apache$spark$scheduler$SchedulerBackend$$appId;

   public static String ENDPOINT_NAME() {
      return CoarseGrainedSchedulerBackend$.MODULE$.ENDPOINT_NAME();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public void stop(final int exitCode) {
      SchedulerBackend.stop$(this, exitCode);
   }

   public String applicationId() {
      return SchedulerBackend.applicationId$(this);
   }

   public Option applicationAttemptId() {
      return SchedulerBackend.applicationAttemptId$(this);
   }

   public Option getDriverLogUrls() {
      return SchedulerBackend.getDriverLogUrls$(this);
   }

   public Option getDriverAttributes() {
      return SchedulerBackend.getDriverAttributes$(this);
   }

   public Seq getShufflePushMergerLocations(final int numPartitions, final int resourceProfileId) {
      return SchedulerBackend.getShufflePushMergerLocations$(this, numPartitions, resourceProfileId);
   }

   public boolean killExecutors$default$4() {
      return ExecutorAllocationClient.killExecutors$default$4$(this);
   }

   public final boolean decommissionExecutor(final String executorId, final ExecutorDecommissionInfo decommissionInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      return ExecutorAllocationClient.decommissionExecutor$(this, executorId, decommissionInfo, adjustTargetNumExecutors, triggeredByExecutor);
   }

   public final boolean decommissionExecutor$default$4() {
      return ExecutorAllocationClient.decommissionExecutor$default$4$(this);
   }

   public boolean killExecutor(final String executorId) {
      return ExecutorAllocationClient.killExecutor$(this, executorId);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String org$apache$spark$scheduler$SchedulerBackend$$appId() {
      return this.org$apache$spark$scheduler$SchedulerBackend$$appId;
   }

   public final void org$apache$spark$scheduler$SchedulerBackend$_setter_$org$apache$spark$scheduler$SchedulerBackend$$appId_$eq(final String x$1) {
      this.org$apache$spark$scheduler$SchedulerBackend$$appId = x$1;
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   public AtomicInteger totalCoreCount() {
      return this.totalCoreCount;
   }

   public AtomicInteger totalRegisteredExecutors() {
      return this.totalRegisteredExecutors;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public int org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$maxRpcMessageSize() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$maxRpcMessageSize;
   }

   private RpcTimeout defaultAskTimeout() {
      return this.defaultAskTimeout;
   }

   private double _minRegisteredRatio() {
      return this._minRegisteredRatio;
   }

   private long maxRegisteredWaitingTimeNs() {
      return this.maxRegisteredWaitingTimeNs;
   }

   private long createTimeNs() {
      return this.createTimeNs;
   }

   public HashMap org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap;
   }

   private HashMap requestedTotalExecutorsPerResourceProfile() {
      return this.requestedTotalExecutorsPerResourceProfile;
   }

   public HashMap org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$execRequestTimes() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$execRequestTimes;
   }

   public LiveListenerBus org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus;
   }

   public HashMap executorsPendingToRemove() {
      return this.executorsPendingToRemove;
   }

   public HashSet executorsPendingLossReason() {
      return this.executorsPendingLossReason;
   }

   public HashMap executorsPendingDecommission() {
      return this.executorsPendingDecommission;
   }

   public Cache unknownExecutorsPendingDecommission() {
      return this.unknownExecutorsPendingDecommission;
   }

   public Map rpHostToLocalTaskCount() {
      return this.rpHostToLocalTaskCount;
   }

   public void rpHostToLocalTaskCount_$eq(final Map x$1) {
      this.rpHostToLocalTaskCount = x$1;
   }

   public Map numLocalityAwareTasksPerResourceProfileId() {
      return this.numLocalityAwareTasksPerResourceProfileId;
   }

   public void numLocalityAwareTasksPerResourceProfileId_$eq(final Map x$1) {
      this.numLocalityAwareTasksPerResourceProfileId = x$1;
   }

   public int currentExecutorIdCounter() {
      return this.currentExecutorIdCounter;
   }

   public void currentExecutorIdCounter_$eq(final int x$1) {
      this.currentExecutorIdCounter = x$1;
   }

   public Option org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel;
   }

   public void org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel_$eq(final Option x$1) {
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel = x$1;
   }

   public AtomicReference org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens;
   }

   private Option delegationTokenManager() {
      return this.delegationTokenManager;
   }

   private void delegationTokenManager_$eq(final Option x$1) {
      this.delegationTokenManager = x$1;
   }

   public ScheduledExecutorService org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$reviveThread() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$reviveThread;
   }

   private Option cleanupService() {
      return this.cleanupService;
   }

   public RpcEndpointRef driverEndpoint() {
      return this.driverEndpoint;
   }

   public double minRegisteredRatio() {
      return this._minRegisteredRatio();
   }

   public Seq decommissionExecutors(final Tuple2[] executorsAndDecomInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      Object var4 = new Object();

      Seq var10000;
      try {
         var10000 = (Seq)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock(() -> {
            String[] executorsToDecommission = (String[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])executorsAndDecomInfo), (x0$1) -> {
               if (x0$1 != null) {
                  String executorId = (String)x0$1._1();
                  ExecutorDecommissionInfo decomInfo = (ExecutorDecommissionInfo)x0$1._2();
                  if (this.isExecutorActive(executorId)) {
                     this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.executorDecommission(executorId, decomInfo);
                     this.executorsPendingDecommission().update(executorId, decomInfo);
                     return new Some(executorId);
                  } else {
                     this.unknownExecutorsPendingDecommission().put(executorId, new Tuple3(decomInfo, BoxesRunTime.boxToBoolean(adjustTargetNumExecutors), BoxesRunTime.boxToBoolean(triggeredByExecutor)));
                     return scala.None..MODULE$;
                  }
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(String.class));
            if (.MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])executorsToDecommission))) {
               throw new NonLocalReturnControl(var4, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(executorsToDecommission).toImmutableArraySeq());
            } else {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommission executors: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])executorsToDecommission).mkString(", "))}))))));
               if (adjustTargetNumExecutors) {
                  this.adjustExecutors(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(executorsToDecommission).toImmutableArraySeq());
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().env().blockManager().master().decommissionBlockManagers(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(executorsToDecommission).toImmutableArraySeq());
               if (!triggeredByExecutor) {
                  .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])executorsToDecommission), (executorId) -> {
                     $anonfun$decommissionExecutors$4(this, executorId);
                     return BoxedUnit.UNIT;
                  });
               }

               ((Option)this.conf().get((ConfigEntry)package$.MODULE$.EXECUTOR_DECOMMISSION_FORCE_KILL_TIMEOUT())).map((cleanupInterval) -> $anonfun$decommissionExecutors$6(this, executorsToDecommission, BoxesRunTime.unboxToLong(cleanupInterval)));
               return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(executorsToDecommission).toImmutableArraySeq();
            }
         });
      } catch (NonLocalReturnControl var6) {
         if (var6.key() != var4) {
            throw var6;
         }

         var10000 = (Seq)var6.value();
      }

      return var10000;
   }

   public void start() {
      if (UserGroupInformation.isSecurityEnabled()) {
         this.delegationTokenManager_$eq(this.createTokenManager());
         this.delegationTokenManager().foreach((dtm) -> {
            $anonfun$start$1(this, dtm);
            return BoxedUnit.UNIT;
         });
      }
   }

   public DriverEndpoint createDriverEndpoint() {
      return new DriverEndpoint();
   }

   public void stopExecutors() {
      try {
         if (this.driverEndpoint() != null) {
            this.logInfo((Function0)(() -> "Shutting down all executors"));
            this.driverEndpoint().askSync(CoarseGrainedClusterMessages.StopExecutors$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean());
         }

      } catch (Exception var2) {
         throw SparkCoreErrors$.MODULE$.askStandaloneSchedulerToShutDownExecutorsError(var2);
      }
   }

   public void stop() {
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$reviveThread().shutdownNow();
      this.cleanupService().foreach((x$8) -> x$8.shutdownNow());
      this.stopExecutors();
      this.delegationTokenManager().foreach((x$9) -> {
         $anonfun$stop$2(x$9);
         return BoxedUnit.UNIT;
      });

      try {
         if (this.driverEndpoint() != null) {
            this.driverEndpoint().askSync(CoarseGrainedClusterMessages.StopDriver$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean());
         }

      } catch (Exception var2) {
         throw SparkCoreErrors$.MODULE$.stopStandaloneSchedulerDriverEndpointError(var2);
      }
   }

   public void updateExecutorsLogLevel(final String logLevel) {
      if (this.driverEndpoint() != null) {
         this.driverEndpoint().ask(new CoarseGrainedClusterMessages.UpdateExecutorsLogLevel(logLevel), scala.reflect.ClassTag..MODULE$.Boolean());
      }
   }

   public void reset() {
      synchronized(this){}

      Set var3;
      try {
         this.requestedTotalExecutorsPerResourceProfile().clear();
         var3 = this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().keys().toSet();
      } catch (Throwable var5) {
         throw var5;
      }

      var3.foreach((eid) -> {
         $anonfun$reset$1(this, eid);
         return BoxedUnit.UNIT;
      });
   }

   public void reviveOffers() {
      Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.driverEndpoint().send(CoarseGrainedClusterMessages.ReviveOffers$.MODULE$));
   }

   public void killTask(final long taskId, final String executorId, final boolean interruptThread, final String reason) {
      this.driverEndpoint().send(new CoarseGrainedClusterMessages.KillTask(taskId, executorId, interruptThread, reason));
   }

   public int defaultParallelism() {
      return this.conf().getInt(package$.MODULE$.DEFAULT_PARALLELISM().key(), scala.math.package..MODULE$.max(this.totalCoreCount().get(), 2));
   }

   public void removeExecutor(final String executorId, final ExecutorLossReason reason) {
      this.driverEndpoint().send(new CoarseGrainedClusterMessages.RemoveExecutor(executorId, reason));
   }

   public void removeWorker(final String workerId, final String host, final String message) {
      this.driverEndpoint().send(new CoarseGrainedClusterMessages.RemoveWorker(workerId, host, message));
   }

   public boolean sufficientResourcesRegistered() {
      return true;
   }

   public boolean isReady() {
      if (this.sufficientResourcesRegistered()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SchedulerBackend is ready for scheduling beginning after "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"reached minRegisteredResourcesRatio: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SIZE..MODULE$, BoxesRunTime.boxToDouble(this.minRegisteredRatio()))}))))));
         return true;
      } else if (System.nanoTime() - this.createTimeNs() >= this.maxRegisteredWaitingTimeNs()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SchedulerBackend is ready for scheduling beginning after waiting "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"maxRegisteredResourcesWaitingTime: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "(ms)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToDouble((double)this.maxRegisteredWaitingTimeNs() / (double)1000000.0F))}))))));
         return true;
      } else {
         return false;
      }
   }

   public synchronized Seq getExecutorIds() {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().keySet().toSeq();
   }

   public synchronized Map getExecutorsWithRegistrationTs() {
      return (Map)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$10, v) -> BoxesRunTime.boxToLong($anonfun$getExecutorsWithRegistrationTs$1(x$10, v)));
   }

   public synchronized boolean isExecutorActive(final String id) {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().contains(id) && !this.executorsPendingToRemove().contains(id) && !this.executorsPendingLossReason().contains(id) && !this.executorsPendingDecommission().contains(id);
   }

   public synchronized int maxNumConcurrentTasks(final ResourceProfile rp) {
      Tuple3 var4 = .MODULE$.unzip3$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(((MapOps)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$maxNumConcurrentTasks$1(this, x0$1)))).values().toArray(scala.reflect.ClassTag..MODULE$.apply(ExecutorData.class))), (executor) -> new Tuple3(BoxesRunTime.boxToInteger(executor.resourceProfileId()), BoxesRunTime.boxToInteger(executor.totalCores()), executor.resourcesInfo().map((x0$2) -> {
            if (x0$2 != null) {
               String name = (String)x0$2._1();
               ExecutorResourceInfo rInfo = (ExecutorResourceInfo)x0$2._2();
               return new Tuple2(name, BoxesRunTime.boxToInteger(rInfo.totalAddressesAmount()));
            } else {
               throw new MatchError(x0$2);
            }
         })), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Map.class));
      if (var4 != null) {
         int[] rpIds = (int[])var4._1();
         int[] cpus = (int[])var4._2();
         Map[] resources = (Map[])var4._3();
         Tuple3 var3 = new Tuple3(rpIds, cpus, resources);
         int[] rpIds = (int[])var3._1();
         int[] cpus = (int[])var3._2();
         Map[] resources = (Map[])var3._3();
         return TaskSchedulerImpl$.MODULE$.calculateAvailableSlots(this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler, this.conf(), rp.id(), rpIds, cpus, resources);
      } else {
         throw new MatchError(var4);
      }
   }

   public synchronized Map getExecutorAvailableResources(final String executorId) {
      return (Map)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId).map((x$12) -> x$12.resourcesInfo()).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
   }

   public synchronized Option getExecutorAvailableCpus(final String executorId) {
      return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId).map((x$13) -> BoxesRunTime.boxToInteger($anonfun$getExecutorAvailableCpus$1(x$13)));
   }

   public synchronized int getExecutorResourceProfileId(final String executorId) {
      Option execDataOption = this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId);
      return BoxesRunTime.unboxToInt(execDataOption.map((x$14) -> BoxesRunTime.boxToInteger($anonfun$getExecutorResourceProfileId$1(x$14))).getOrElse((JFunction0.mcI.sp)() -> ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID()));
   }

   public final boolean requestExecutors(final int numAdditionalExecutors) {
      if (numAdditionalExecutors < 0) {
         throw new IllegalArgumentException("Attempted to request a negative number of additional executor(s) " + numAdditionalExecutors + " from the cluster manager. Please specify a positive number!");
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requesting ", " additional "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS..MODULE$, BoxesRunTime.boxToInteger(numAdditionalExecutors))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor(s) from the cluster manager"})))).log(scala.collection.immutable.Nil..MODULE$))));
         synchronized(this){}

         Future var4;
         try {
            ResourceProfile defaultProf = this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().resourceProfileManager().defaultResourceProfile();
            int numExisting = BoxesRunTime.unboxToInt(this.requestedTotalExecutorsPerResourceProfile().getOrElse(defaultProf, (JFunction0.mcI.sp)() -> 0));
            this.requestedTotalExecutorsPerResourceProfile().update(defaultProf, BoxesRunTime.boxToInteger(numExisting + numAdditionalExecutors));
            this.updateExecRequestTime(defaultProf.id(), numAdditionalExecutors);
            var4 = this.doRequestTotalExecutors(this.requestedTotalExecutorsPerResourceProfile().toMap(scala..less.colon.less..MODULE$.refl()));
         } catch (Throwable var8) {
            throw var8;
         }

         return BoxesRunTime.unboxToBoolean(this.defaultAskTimeout().awaitResult(var4));
      }
   }

   public final boolean requestTotalExecutors(final Map resourceProfileIdToNumExecutors, final Map numLocalityAwareTasksPerResourceProfileId, final Map hostToLocalTaskCount) {
      int totalExecs = BoxesRunTime.unboxToInt(resourceProfileIdToNumExecutors.values().sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      if (totalExecs < 0) {
         throw new IllegalArgumentException("Attempted to request a negative number of executor(s) " + totalExecs + " from the cluster manager. Please specify a positive number!");
      } else {
         Map resourceProfileToNumExecutors = (Map)resourceProfileIdToNumExecutors.map((x0$1) -> {
            if (x0$1 != null) {
               int rpid = x0$1._1$mcI$sp();
               int num = x0$1._2$mcI$sp();
               return new Tuple2(this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().resourceProfileManager().resourceProfileFromId(rpid), BoxesRunTime.boxToInteger(num));
            } else {
               throw new MatchError(x0$1);
            }
         });
         synchronized(this){}

         Future var8;
         try {
            Map oldResourceProfileToNumExecutors = this.requestedTotalExecutorsPerResourceProfile().map((x0$2) -> {
               if (x0$2 != null) {
                  ResourceProfile rp = (ResourceProfile)x0$2._1();
                  int num = x0$2._2$mcI$sp();
                  return new Tuple2.mcII.sp(rp.id(), num);
               } else {
                  throw new MatchError(x0$2);
               }
            }).toMap(scala..less.colon.less..MODULE$.refl());
            this.requestedTotalExecutorsPerResourceProfile().clear();
            this.requestedTotalExecutorsPerResourceProfile().$plus$plus$eq(resourceProfileToNumExecutors);
            this.numLocalityAwareTasksPerResourceProfileId_$eq(numLocalityAwareTasksPerResourceProfileId);
            this.rpHostToLocalTaskCount_$eq(hostToLocalTaskCount);
            this.updateExecRequestTimes(oldResourceProfileToNumExecutors, resourceProfileIdToNumExecutors);
            var8 = this.doRequestTotalExecutors(this.requestedTotalExecutorsPerResourceProfile().toMap(scala..less.colon.less..MODULE$.refl()));
         } catch (Throwable var11) {
            throw var11;
         }

         return BoxesRunTime.unboxToBoolean(this.defaultAskTimeout().awaitResult(var8));
      }
   }

   private void updateExecRequestTimes(final Map oldProfile, final Map newProfile) {
      newProfile.map((x0$1) -> {
         if (x0$1 != null) {
            int k = x0$1._1$mcI$sp();
            int v = x0$1._2$mcI$sp();
            int delta = v - BoxesRunTime.unboxToInt(oldProfile.getOrElse(BoxesRunTime.boxToInteger(k), (JFunction0.mcI.sp)() -> 0));
            return delta != 0 ? this.updateExecRequestTime(k, delta) : BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private Object updateExecRequestTime(final int profileId, final int delta) {
      Queue times = (Queue)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$execRequestTimes().getOrElseUpdate(BoxesRunTime.boxToInteger(profileId), () -> (Queue)scala.collection.mutable.Queue..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      if (delta > 0) {
         return times.$plus$eq(new Tuple2.mcIJ.sp(delta, System.currentTimeMillis()));
      } else if (delta >= 0) {
         return BoxedUnit.UNIT;
      } else {
         int toConsume = -delta;

         while(toConsume > 0 && times.nonEmpty()) {
            Tuple2 h = (Tuple2)times.dequeue();
            if (h._1$mcI$sp() > toConsume) {
               Tuple2.mcIJ.sp var6 = new Tuple2.mcIJ.sp(h._1$mcI$sp() - toConsume, h._2$mcJ$sp());
               times.$plus$eq$colon(var6);
               toConsume = 0;
            } else {
               toConsume -= h._1$mcI$sp();
            }
         }

         return BoxedUnit.UNIT;
      }
   }

   public Future doRequestTotalExecutors(final Map resourceProfileToTotalExecs) {
      return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
   }

   private Future adjustExecutors(final Seq executorIds) {
      if (executorIds.nonEmpty()) {
         executorIds.foreach((exec) -> {
            $anonfun$adjustExecutors$1(this, exec);
            return BoxedUnit.UNIT;
         });
         return this.doRequestTotalExecutors(this.requestedTotalExecutorsPerResourceProfile().toMap(scala..less.colon.less..MODULE$.refl()));
      } else {
         return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(true));
      }
   }

   public final Seq killExecutors(final Seq executorIds, final boolean adjustTargetNumExecutors, final boolean countFailures, final boolean force) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requesting to kill executor(s) ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorIds.mkString(", "))})))));
      Future response = (Future)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock(() -> {
         Tuple2 var7 = executorIds.partition((key) -> BoxesRunTime.boxToBoolean($anonfun$killExecutors$3(this, key)));
         if (var7 != null) {
            Seq knownExecutors = (Seq)var7._1();
            Seq unknownExecutors = (Seq)var7._2();
            Tuple2 var6 = new Tuple2(knownExecutors, unknownExecutors);
            Seq knownExecutorsx = (Seq)var6._1();
            Seq unknownExecutors = (Seq)var6._2();
            unknownExecutors.foreach((id) -> {
               $anonfun$killExecutors$4(this, id);
               return BoxedUnit.UNIT;
            });
            Seq executorsToKill = (Seq)((IterableOps)knownExecutorsx.filter((id) -> BoxesRunTime.boxToBoolean($anonfun$killExecutors$6(this, id)))).filter((id) -> BoxesRunTime.boxToBoolean($anonfun$killExecutors$7(this, force, id)));
            executorsToKill.foreach((id) -> {
               $anonfun$killExecutors$8(this, countFailures, id);
               return BoxedUnit.UNIT;
            });
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Actual list of executor(s) to be killed is "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorsToKill.mkString(", "))}))))));
            Future adjustTotalExecutors = adjustTargetNumExecutors ? this.adjustExecutors(executorsToKill) : scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(true));
            Function1 killExecutors = executorsToKill.nonEmpty() ? (x$16) -> $anonfun$killExecutors$10(this, executorsToKill, BoxesRunTime.unboxToBoolean(x$16)) : (x$17) -> $anonfun$killExecutors$11(BoxesRunTime.unboxToBoolean(x$17));
            Future killResponse = adjustTotalExecutors.flatMap(killExecutors, ThreadUtils$.MODULE$.sameThread());
            return killResponse.flatMap((killSuccessful) -> $anonfun$killExecutors$12(executorsToKill, BoxesRunTime.unboxToBoolean(killSuccessful)), ThreadUtils$.MODULE$.sameThread());
         } else {
            throw new MatchError(var7);
         }
      });
      return (Seq)this.defaultAskTimeout().awaitResult(response);
   }

   public Future doKillExecutors(final Seq executorIds) {
      return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
   }

   public final boolean decommissionExecutorsOnHost(final String host) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requesting to kill any and all executors on host ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)})))));
      this.driverEndpoint().send(new CoarseGrainedClusterMessages.DecommissionExecutorsOnHost(host));
      return true;
   }

   public final boolean killExecutorsOnHost(final String host) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requesting to kill any and all executors on host ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)})))));
      this.driverEndpoint().send(new CoarseGrainedClusterMessages.KillExecutorsOnHost(host));
      return true;
   }

   public Option createTokenManager() {
      return scala.None..MODULE$;
   }

   public void updateDelegationTokens(final byte[] tokens) {
      SparkHadoopUtil$.MODULE$.get().addDelegationTokens(tokens, this.conf());
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens().set(tokens);
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().values().foreach((ed) -> {
         $anonfun$updateDelegationTokens$1(tokens, ed);
         return BoxedUnit.UNIT;
      });
   }

   public byte[] currentDelegationTokens() {
      return (byte[])this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens().get();
   }

   public boolean isExecutorExcluded(final String executorId, final String hostname) {
      return false;
   }

   public Object org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock(final Function0 fn) {
      Object var3;
      synchronized(this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler) {
         synchronized(this){}

         Object var5;
         try {
            var5 = fn.apply();
         } catch (Throwable var8) {
            throw var8;
         }

         var3 = var5;
      }

      return var3;
   }

   public Option getTaskThreadDump(final long taskId, final String executorId) {
      return (Option)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock(() -> {
         if (this.isExecutorActive(executorId)) {
            ExecutorData executorData = (ExecutorData)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().apply(executorId);
            return (Option)executorData.executorEndpoint().askSync(new CoarseGrainedClusterMessages.TaskThreadDump(taskId), scala.reflect.ClassTag..MODULE$.apply(Option.class));
         } else {
            return scala.None..MODULE$;
         }
      });
   }

   // $FF: synthetic method
   public static final ScheduledExecutorService $anonfun$cleanupService$1(final long x$1) {
      return ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("cleanup-decommission-execs");
   }

   // $FF: synthetic method
   public static final void $anonfun$decommissionExecutors$4(final CoarseGrainedSchedulerBackend $this, final String executorId) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Notify executor ", " to decommission."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))));
      ((ExecutorData)$this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().apply(executorId)).executorEndpoint().send(CoarseGrainedClusterMessages.DecommissionExecutor$.MODULE$);
   }

   // $FF: synthetic method
   public static final Option $anonfun$decommissionExecutors$6(final CoarseGrainedSchedulerBackend $this, final String[] executorsToDecommission$1, final long cleanupInterval) {
      Runnable cleanupTask = new Runnable(executorsToDecommission$1, cleanupInterval) {
         // $FF: synthetic field
         private final CoarseGrainedSchedulerBackend $outer;
         private final String[] executorsToDecommission$1;
         private final long cleanupInterval$1;

         public void run() {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
               synchronized(this.$outer){}

               String[] var3;
               try {
                  var3 = (String[]).MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.executorsToDecommission$1), (key) -> BoxesRunTime.boxToBoolean($anonfun$run$2(this, key)));
               } catch (Throwable var5) {
                  throw var5;
               }

               if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])var3))) {
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " failed to decommission in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])var3).toList())}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", killing."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INTERVAL..MODULE$, BoxesRunTime.boxToLong(this.cleanupInterval$1))}))))));
                  this.$outer.killExecutors(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(var3).toImmutableArraySeq(), false, false, true);
               }
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$run$2(final Object $this, final String key) {
            return $this.$outer.executorsPendingDecommission().contains(key);
         }

         public {
            if (CoarseGrainedSchedulerBackend.this == null) {
               throw null;
            } else {
               this.$outer = CoarseGrainedSchedulerBackend.this;
               this.executorsToDecommission$1 = executorsToDecommission$1;
               this.cleanupInterval$1 = cleanupInterval$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      return $this.cleanupService().map((x$7) -> x$7.schedule(cleanupTask, cleanupInterval, TimeUnit.SECONDS));
   }

   // $FF: synthetic method
   public static final void $anonfun$start$1(final CoarseGrainedSchedulerBackend $this, final HadoopDelegationTokenManager dtm) {
      UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
      byte[] var10000;
      if (dtm.renewalEnabled()) {
         var10000 = dtm.start();
      } else {
         Credentials creds = ugi.getCredentials();
         dtm.obtainDelegationTokens(creds);
         var10000 = creds.numberOfTokens() <= 0 && creds.numberOfSecretKeys() <= 0 ? null : SparkHadoopUtil$.MODULE$.get().serialize(creds);
      }

      byte[] tokens = var10000;
      if (tokens != null) {
         $this.updateDelegationTokens(tokens);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final HadoopDelegationTokenManager x$9) {
      x$9.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$reset$1(final CoarseGrainedSchedulerBackend $this, final String eid) {
      $this.removeExecutor(eid, new ExecutorProcessLost("Stale executor after cluster manager re-registered.", ExecutorProcessLost$.MODULE$.apply$default$2(), ExecutorProcessLost$.MODULE$.apply$default$3()));
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorsWithRegistrationTs$1(final String x$10, final ExecutorData v) {
      return v.registrationTs();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$maxNumConcurrentTasks$1(final CoarseGrainedSchedulerBackend $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String id = (String)x0$1._1();
         return $this.isExecutorActive(id);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$getExecutorAvailableCpus$1(final ExecutorData x$13) {
      return x$13.freeCores();
   }

   // $FF: synthetic method
   public static final int $anonfun$getExecutorResourceProfileId$1(final ExecutorData x$14) {
      return x$14.resourceProfileId();
   }

   // $FF: synthetic method
   public static final void $anonfun$adjustExecutors$1(final CoarseGrainedSchedulerBackend $this, final String exec) {
      $this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock((JFunction0.mcV.sp)() -> {
         int rpId = ((ExecutorData)$this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().apply(exec)).resourceProfileId();
         ResourceProfile rp = $this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().resourceProfileManager().resourceProfileFromId(rpId);
         if ($this.requestedTotalExecutorsPerResourceProfile().isEmpty()) {
            $this.requestedTotalExecutorsPerResourceProfile().update(rp, BoxesRunTime.boxToInteger(0));
         } else {
            int requestedTotalForRp = BoxesRunTime.unboxToInt($this.requestedTotalExecutorsPerResourceProfile().apply(rp));
            $this.requestedTotalExecutorsPerResourceProfile().update(rp, BoxesRunTime.boxToInteger(scala.math.package..MODULE$.max(requestedTotalForRp - 1, 0)));
         }
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$killExecutors$3(final CoarseGrainedSchedulerBackend $this, final String key) {
      return $this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().contains(key);
   }

   // $FF: synthetic method
   public static final void $anonfun$killExecutors$4(final CoarseGrainedSchedulerBackend $this, final String id) {
      $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor to kill ", " does not exist!"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, id)})))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$killExecutors$6(final CoarseGrainedSchedulerBackend $this, final String id) {
      return !$this.executorsPendingToRemove().contains(id);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$killExecutors$7(final CoarseGrainedSchedulerBackend $this, final boolean force$1, final String id) {
      return force$1 || !$this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.isExecutorBusy(id);
   }

   // $FF: synthetic method
   public static final void $anonfun$killExecutors$8(final CoarseGrainedSchedulerBackend $this, final boolean countFailures$1, final String id) {
      $this.executorsPendingToRemove().update(id, BoxesRunTime.boxToBoolean(!countFailures$1));
   }

   // $FF: synthetic method
   public static final Future $anonfun$killExecutors$10(final CoarseGrainedSchedulerBackend $this, final Seq executorsToKill$1, final boolean x$16) {
      return $this.doKillExecutors(executorsToKill$1);
   }

   // $FF: synthetic method
   public static final Future $anonfun$killExecutors$11(final boolean x$17) {
      return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
   }

   // $FF: synthetic method
   public static final Future $anonfun$killExecutors$12(final Seq executorsToKill$1, final boolean killSuccessful) {
      return scala.concurrent.Future..MODULE$.successful(killSuccessful ? executorsToKill$1 : scala.package..MODULE$.Seq().empty());
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDelegationTokens$1(final byte[] tokens$1, final ExecutorData ed) {
      ed.executorEndpoint().send(new CoarseGrainedClusterMessages.UpdateDelegationTokens(tokens$1));
   }

   public CoarseGrainedSchedulerBackend(final TaskSchedulerImpl scheduler, final RpcEnv rpcEnv) {
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler = scheduler;
      this.rpcEnv = rpcEnv;
      ExecutorAllocationClient.$init$(this);
      SchedulerBackend.$init$(this);
      Logging.$init$(this);
      this.totalCoreCount = new AtomicInteger(0);
      this.totalRegisteredExecutors = new AtomicInteger(0);
      this.conf = scheduler.sc().conf();
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$maxRpcMessageSize = RpcUtils$.MODULE$.maxMessageSizeBytes(this.conf());
      this.defaultAskTimeout = RpcUtils$.MODULE$.askRpcTimeout(this.conf());
      this._minRegisteredRatio = scala.math.package..MODULE$.min((double)1.0F, BoxesRunTime.unboxToDouble(((Option)this.conf().get((ConfigEntry)package$.MODULE$.SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO())).getOrElse((JFunction0.mcD.sp)() -> (double)0.0F)));
      this.maxRegisteredWaitingTimeNs = TimeUnit.MILLISECONDS.toNanos(BoxesRunTime.unboxToLong(this.conf().get(package$.MODULE$.SCHEDULER_MAX_REGISTERED_RESOURCE_WAITING_TIME())));
      this.createTimeNs = System.nanoTime();
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap = new HashMap();
      this.requestedTotalExecutorsPerResourceProfile = new HashMap();
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$execRequestTimes = new HashMap();
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus = scheduler.sc().listenerBus();
      this.executorsPendingToRemove = new HashMap();
      this.executorsPendingLossReason = new HashSet();
      this.executorsPendingDecommission = new HashMap();
      this.unknownExecutorsPendingDecommission = CacheBuilder.newBuilder().maximumSize((long)BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.SCHEDULER_MAX_RETAINED_UNKNOWN_EXECUTORS()))).build();
      this.rpHostToLocalTaskCount = scala.Predef..MODULE$.Map().empty();
      this.numLocalityAwareTasksPerResourceProfileId = scala.Predef..MODULE$.Map().empty();
      this.currentExecutorIdCounter = 0;
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel = scala.None..MODULE$;
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens = new AtomicReference();
      this.delegationTokenManager = scala.None..MODULE$;
      this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$reviveThread = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("driver-revive-thread");
      this.cleanupService = ((Option)this.conf().get((ConfigEntry)package$.MODULE$.EXECUTOR_DECOMMISSION_FORCE_KILL_TIMEOUT())).map((x$1) -> $anonfun$cleanupService$1(BoxesRunTime.unboxToLong(x$1)));
      this.driverEndpoint = rpcEnv.setupEndpoint(CoarseGrainedSchedulerBackend$.MODULE$.ENDPOINT_NAME(), this.createDriverEndpoint());
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class DriverEndpoint implements IsolatedThreadSafeRpcEndpoint, Logging {
      private ArraySeq org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$sparkProperties;
      private final RpcEnv rpcEnv;
      private final HashMap addressToExecutorId;
      private final ExecutorLogUrlHandler org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$logUrlHandler;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final CoarseGrainedSchedulerBackend $outer;

      public String logName() {
         return Logging.logName$(this);
      }

      public Logger log() {
         return Logging.log$(this);
      }

      public Logging.LogStringContext LogStringContext(final StringContext sc) {
         return Logging.LogStringContext$(this, sc);
      }

      public void withLogContext(final java.util.Map context, final Function0 body) {
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

      public final int threadCount() {
         return IsolatedThreadSafeRpcEndpoint.threadCount$(this);
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

      public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
         RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
      }

      public void onStop() {
         RpcEndpoint.onStop$(this);
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

      public HashMap addressToExecutorId() {
         return this.addressToExecutorId;
      }

      private ArraySeq sparkProperties$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$sparkProperties = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().conf().getAll()), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$sparkProperties$1(x0$1)))).toImmutableArraySeq();
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$sparkProperties;
      }

      public ArraySeq org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$sparkProperties() {
         return !this.bitmap$0 ? this.sparkProperties$lzycompute() : this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$sparkProperties;
      }

      public ExecutorLogUrlHandler org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$logUrlHandler() {
         return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$logUrlHandler;
      }

      public void onStart() {
         long reviveIntervalMs = BoxesRunTime.unboxToLong(((Option)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().conf().get((ConfigEntry)package$.MODULE$.SCHEDULER_REVIVE_INTERVAL())).getOrElse((JFunction0.mcJ.sp)() -> 1000L));
         this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$reviveThread().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> scala.Option..MODULE$.apply(this.self()).foreach((x$2) -> {
                  $anonfun$onStart$4(x$2);
                  return BoxedUnit.UNIT;
               })), 0L, reviveIntervalMs, TimeUnit.MILLISECONDS);
      }

      public PartialFunction receive() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final DriverEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof CoarseGrainedClusterMessages.StatusUpdate var7) {
                  String executorId = var7.executorId();
                  long taskId = var7.taskId();
                  Enumeration.Value state = var7.state();
                  SerializableBuffer data = var7.data();
                  int taskCpus = var7.taskCpus();
                  Map resources = var7.resources();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.statusUpdate(taskId, state, data.value());
                  if (TaskState$.MODULE$.isFinished(state)) {
                     Option var15 = this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId);
                     if (var15 instanceof Some) {
                        Some var16 = (Some)var15;
                        ExecutorData executorInfo = (ExecutorData)var16.value();
                        executorInfo.freeCores_$eq(executorInfo.freeCores() + taskCpus);
                        resources.foreach((x0$1) -> {
                           $anonfun$applyOrElse$1(executorInfo, x0$1);
                           return BoxedUnit.UNIT;
                        });
                        this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$makeOffers(executorId);
                        BoxedUnit var57 = BoxedUnit.UNIT;
                     } else {
                        if (!scala.None..MODULE$.equals(var15)) {
                           throw new MatchError(var15);
                        }

                        this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignored task status update (", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"state ", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_STATE..MODULE$, state)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from unknown executor with ID ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))));
                        BoxedUnit var58 = BoxedUnit.UNIT;
                     }

                     return BoxedUnit.UNIT;
                  } else {
                     return BoxedUnit.UNIT;
                  }
               } else if (x1 instanceof CoarseGrainedClusterMessages.ShufflePushCompletion var18) {
                  int shuffleId = var18.shuffleId();
                  int shuffleMergeId = var18.shuffleMergeId();
                  int mapIndex = var18.mapIndex();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.dagScheduler().shufflePushCompleted(shuffleId, shuffleMergeId, mapIndex);
                  return BoxedUnit.UNIT;
               } else if (CoarseGrainedClusterMessages.ReviveOffers$.MODULE$.equals(x1)) {
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$makeOffers();
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillTask) {
                  CoarseGrainedClusterMessages.KillTask var22 = (CoarseGrainedClusterMessages.KillTask)x1;
                  long taskId = var22.taskId();
                  String executorId = var22.executor();
                  boolean interruptThread = var22.interruptThread();
                  String reason = var22.reason();
                  Option var28 = this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId);
                  if (var28 instanceof Some) {
                     Some var29 = (Some)var28;
                     ExecutorData executorInfo = (ExecutorData)var29.value();
                     executorInfo.executorEndpoint().send(new CoarseGrainedClusterMessages.KillTask(taskId, executorId, interruptThread, reason));
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var28)) {
                        throw new MatchError(var28);
                     }

                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to kill task ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for unknown executor ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))));
                     BoxedUnit var56 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillExecutorsOnHost) {
                  CoarseGrainedClusterMessages.KillExecutorsOnHost var31 = (CoarseGrainedClusterMessages.KillExecutorsOnHost)x1;
                  String host = var31.host();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.getExecutorsAliveOnHost(host).foreach((execs) -> this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().killExecutors(execs.toSeq(), false, false, true));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.DecommissionExecutorsOnHost) {
                  CoarseGrainedClusterMessages.DecommissionExecutorsOnHost var33 = (CoarseGrainedClusterMessages.DecommissionExecutorsOnHost)x1;
                  String host = var33.host();
                  ExecutorDecommissionInfo reason = new ExecutorDecommissionInfo("Decommissioning all executors on " + host + ".", ExecutorDecommissionInfo$.MODULE$.apply$default$2());
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.getExecutorsAliveOnHost(host).foreach((execs) -> {
                     Tuple2[] execsWithReasons = (Tuple2[])((IterableOnceOps)execs.map((exec) -> new Tuple2(exec, reason))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
                     return this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().decommissionExecutors(execsWithReasons, false, false);
                  });
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateDelegationTokens) {
                  CoarseGrainedClusterMessages.UpdateDelegationTokens var36 = (CoarseGrainedClusterMessages.UpdateDelegationTokens)x1;
                  byte[] newDelegationTokens = var36.tokens();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().updateDelegationTokens(newDelegationTokens);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveExecutor) {
                  CoarseGrainedClusterMessages.RemoveExecutor var38 = (CoarseGrainedClusterMessages.RemoveExecutor)x1;
                  String executorId = var38.executorId();
                  ExecutorLossReason reason = var38.reason();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId).foreach((x$3) -> {
                     $anonfun$applyOrElse$8(x$3);
                     return BoxedUnit.UNIT;
                  });
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$removeExecutor(executorId, reason);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveWorker) {
                  CoarseGrainedClusterMessages.RemoveWorker var41 = (CoarseGrainedClusterMessages.RemoveWorker)x1;
                  String workerId = var41.workerId();
                  String host = var41.host();
                  String message = var41.message();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$removeWorker(workerId, host, message);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.LaunchedExecutor) {
                  CoarseGrainedClusterMessages.LaunchedExecutor var45 = (CoarseGrainedClusterMessages.LaunchedExecutor)x1;
                  String executorId = var45.executorId();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId).foreach((datax) -> {
                     $anonfun$applyOrElse$9(datax);
                     return BoxedUnit.UNIT;
                  });
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$makeOffers(executorId);
                  return BoxedUnit.UNIT;
               } else {
                  if (x1 instanceof CoarseGrainedClusterMessages.MiscellaneousProcessAdded) {
                     CoarseGrainedClusterMessages.MiscellaneousProcessAdded var47 = (CoarseGrainedClusterMessages.MiscellaneousProcessAdded)x1;
                     long time = var47.time();
                     String processId = var47.processId();
                     MiscellaneousProcessDetails info = var47.info();
                     if (true && processId != null && info != null) {
                        this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus().post(new SparkListenerMiscellaneousProcessAdded(time, processId, info));
                        return BoxedUnit.UNIT;
                     }
                  }

                  this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received unexpected message. ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, x1)})))));
                  return BoxedUnit.UNIT;
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof CoarseGrainedClusterMessages.StatusUpdate) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.ShufflePushCompletion) {
                  return true;
               } else if (CoarseGrainedClusterMessages.ReviveOffers$.MODULE$.equals(x1)) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillTask) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillExecutorsOnHost) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.DecommissionExecutorsOnHost) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateDelegationTokens) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveExecutor) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveWorker) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.LaunchedExecutor) {
                  return true;
               } else {
                  if (x1 instanceof CoarseGrainedClusterMessages.MiscellaneousProcessAdded) {
                     CoarseGrainedClusterMessages.MiscellaneousProcessAdded var4 = (CoarseGrainedClusterMessages.MiscellaneousProcessAdded)x1;
                     String processId = var4.processId();
                     MiscellaneousProcessDetails info = var4.info();
                     if (true && processId != null && info != null) {
                        return true;
                     }
                  }

                  return true;
               }
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$2(final Map addressAmount$1, final ExecutorResourceInfo r) {
               r.release(addressAmount$1);
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$1(final ExecutorData executorInfo$1, final Tuple2 x0$1) {
               if (x0$1 != null) {
                  String rName = (String)x0$1._1();
                  Map addressAmount = (Map)x0$1._2();
                  executorInfo$1.resourcesInfo().get(rName).foreach((r) -> {
                     $anonfun$applyOrElse$2(addressAmount, r);
                     return BoxedUnit.UNIT;
                  });
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(x0$1);
               }
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$8(final ExecutorData x$3) {
               x$3.executorEndpoint().send(CoarseGrainedClusterMessages.StopExecutor$.MODULE$);
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$9(final ExecutorData data) {
               data.freeCores_$eq(data.totalCores());
            }

            public {
               if (DriverEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = DriverEndpoint.this;
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
            private final DriverEndpoint $outer;
            private final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof CoarseGrainedClusterMessages.RegisterExecutor var5) {
                  String executorId = var5.executorId();
                  RpcEndpointRef executorRef = var5.executorRef();
                  String hostname = var5.hostname();
                  int cores = var5.cores();
                  Map logUrls = var5.logUrls();
                  Map attributes = var5.attributes();
                  Map resources = var5.resources();
                  int resourceProfileId = var5.resourceProfileId();
                  if (this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().contains(executorId)) {
                     this.context$1.sendFailure(new IllegalStateException("Duplicate executor ID: " + executorId));
                     return BoxedUnit.UNIT;
                  } else if (!this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.excludedNodes().contains(hostname) && !this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().isExecutorExcluded(executorId, hostname)) {
                     RpcAddress executorAddress = executorRef.address() != null ? executorRef.address() : this.context$1.senderAddress();
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registered executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ENDPOINT_REF..MODULE$, executorRef)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, executorAddress)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ID ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ResourceProfileId ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(resourceProfileId))}))))));
                     this.$outer.addressToExecutorId().update(executorAddress, executorId);
                     this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().totalCoreCount().addAndGet(cores);
                     this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().totalRegisteredExecutors().addAndGet(1);
                     Map resourcesInfo = (Map)resources.map((x0$1) -> {
                        if (x0$1 != null) {
                           ResourceInformation info = (ResourceInformation)x0$1._2();
                           return new Tuple2(info.name(), new ExecutorResourceInfo(info.name(), .MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])info.addresses()))));
                        } else {
                           throw new MatchError(x0$1);
                        }
                     });
                     synchronized(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer()){}

                     Option var18;
                     try {
                        var18 = this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$execRequestTimes().get(BoxesRunTime.boxToInteger(resourceProfileId)).flatMap((times) -> times.headOption().map((h) -> BoxesRunTime.boxToLong($anonfun$applyOrElse$15(times, h))));
                     } catch (Throwable var39) {
                        throw var39;
                     }

                     ExecutorData data = new ExecutorData(executorRef, executorAddress, hostname, 0, cores, this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$logUrlHandler().applyPattern(logUrls, attributes), attributes, resourcesInfo, resourceProfileId, System.currentTimeMillis(), var18);
                     synchronized(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer()){}

                     try {
                        this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().put(executorId, data);
                        if (this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().currentExecutorIdCounter() < scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(executorId))) {
                           this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().currentExecutorIdCounter_$eq(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(executorId)));
                        }
                     } catch (Throwable var40) {
                        throw var40;
                     }

                     this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus().post(new SparkListenerExecutorAdded(System.currentTimeMillis(), executorId, data));
                     scala.Option..MODULE$.apply(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().unknownExecutorsPendingDecommission().getIfPresent(executorId)).foreach((v) -> {
                        $anonfun$applyOrElse$16(this, executorId, v);
                        return BoxedUnit.UNIT;
                     });
                     this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                     return BoxedUnit.UNIT;
                  } else {
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rejecting ", " as it has been excluded."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))));
                     this.context$1.sendFailure(new IllegalStateException("Executor is excluded due to failures: " + executorId));
                     return BoxedUnit.UNIT;
                  }
               } else if (CoarseGrainedClusterMessages.StopDriver$.MODULE$.equals(x1)) {
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  this.$outer.stop();
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateExecutorsLogLevel) {
                  CoarseGrainedClusterMessages.UpdateExecutorsLogLevel var21 = (CoarseGrainedClusterMessages.UpdateExecutorsLogLevel)x1;
                  String logLevel = var21.logLevel();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel_$eq(new Some(logLevel));
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asking each executor to refresh the log level to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LOG_LEVEL..MODULE$, logLevel)}))))));
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$18(check$ifrefutable$1))).foreach((x$4) -> {
                     $anonfun$applyOrElse$19(logLevel, x$4);
                     return BoxedUnit.UNIT;
                  });
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               } else if (CoarseGrainedClusterMessages.StopExecutors$.MODULE$.equals(x1)) {
                  this.$outer.logInfo((Function0)(() -> "Asking each executor to shut down"));
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$21(check$ifrefutable$2))).foreach((x$5) -> {
                     $anonfun$applyOrElse$22(x$5);
                     return BoxedUnit.UNIT;
                  });
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveWorker) {
                  CoarseGrainedClusterMessages.RemoveWorker var23 = (CoarseGrainedClusterMessages.RemoveWorker)x1;
                  String workerId = var23.workerId();
                  String host = var23.host();
                  String message = var23.message();
                  this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$removeWorker(workerId, host, message);
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.ExecutorDecommissioning) {
                  CoarseGrainedClusterMessages.ExecutorDecommissioning var27 = (CoarseGrainedClusterMessages.ExecutorDecommissioning)x1;
                  String executorId = var27.executorId();
                  this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"decommissioned message"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().decommissionExecutor(executorId, new ExecutorDecommissionInfo("Executor " + executorId + " is decommissioned.", ExecutorDecommissionInfo$.MODULE$.apply$default$2()), false, true)));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RetrieveSparkAppConfig) {
                  CoarseGrainedClusterMessages.RetrieveSparkAppConfig var29 = (CoarseGrainedClusterMessages.RetrieveSparkAppConfig)x1;
                  int resourceProfileId = var29.resourceProfileId();
                  ResourceProfile rp = this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().resourceProfileManager().resourceProfileFromId(resourceProfileId);
                  CoarseGrainedClusterMessages.SparkAppConfig reply = new CoarseGrainedClusterMessages.SparkAppConfig(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$sparkProperties(), SparkEnv$.MODULE$.get().securityManager().getIOEncryptionKey(), scala.Option..MODULE$.apply(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$delegationTokens().get()), rp, this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$currentLogLevel());
                  this.context$1.reply(reply);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.IsExecutorAlive) {
                  CoarseGrainedClusterMessages.IsExecutorAlive var33 = (CoarseGrainedClusterMessages.IsExecutorAlive)x1;
                  String executorId = var33.executorId();
                  this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().isExecutorActive(executorId)));
                  return BoxedUnit.UNIT;
               } else {
                  this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received unexpected ask ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, x1)})))));
                  return BoxedUnit.UNIT;
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof CoarseGrainedClusterMessages.RegisterExecutor) {
                  return true;
               } else if (CoarseGrainedClusterMessages.StopDriver$.MODULE$.equals(x1)) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateExecutorsLogLevel) {
                  return true;
               } else if (CoarseGrainedClusterMessages.StopExecutors$.MODULE$.equals(x1)) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveWorker) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.ExecutorDecommissioning) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RetrieveSparkAppConfig) {
                  return true;
               } else {
                  return x1 instanceof CoarseGrainedClusterMessages.IsExecutorAlive ? true : true;
               }
            }

            // $FF: synthetic method
            public static final long $anonfun$applyOrElse$15(final Queue times$1, final Tuple2 h) {
               times$1.dequeue();
               if (h._1$mcI$sp() > 1) {
                  Tuple2.mcIJ.sp var2 = new Tuple2.mcIJ.sp(h._1$mcI$sp() - 1, h._2$mcJ$sp());
                  times$1.$plus$eq$colon(var2);
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               return h._2$mcJ$sp();
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$16(final Object $this, final String executorId$3, final Tuple3 v) {
               $this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().decommissionExecutors((Tuple2[])((Object[])(new Tuple2[]{new Tuple2(executorId$3, v._1())})), BoxesRunTime.unboxToBoolean(v._2()), BoxesRunTime.unboxToBoolean(v._3()));
               $this.$outer.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().unknownExecutorsPendingDecommission().invalidate(executorId$3);
            }

            // $FF: synthetic method
            public static final boolean $anonfun$applyOrElse$18(final Tuple2 check$ifrefutable$1) {
               return check$ifrefutable$1 != null;
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$19(final String logLevel$1, final Tuple2 x$4) {
               if (x$4 != null) {
                  ExecutorData executorData = (ExecutorData)x$4._2();
                  executorData.executorEndpoint().send(new CoarseGrainedClusterMessages.UpdateExecutorLogLevel(logLevel$1));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(x$4);
               }
            }

            // $FF: synthetic method
            public static final boolean $anonfun$applyOrElse$21(final Tuple2 check$ifrefutable$2) {
               return check$ifrefutable$2 != null;
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$22(final Tuple2 x$5) {
               if (x$5 != null) {
                  ExecutorData executorData = (ExecutorData)x$5._2();
                  executorData.executorEndpoint().send(CoarseGrainedClusterMessages.StopExecutor$.MODULE$);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(x$5);
               }
            }

            public {
               if (DriverEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = DriverEndpoint.this;
                  this.context$1 = context$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public void org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$makeOffers() {
         Seq taskDescs = (Seq)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock(() -> {
            HashMap activeExecutors = (HashMap)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$makeOffers$2(this, x0$1)));
            IndexedSeq workOffers = ((IterableOnceOps)activeExecutors.map((x0$2) -> {
               if (x0$2 != null) {
                  String id = (String)x0$2._1();
                  ExecutorData executorData = (ExecutorData)x0$2._2();
                  return this.buildWorkerOffer(id, executorData);
               } else {
                  throw new MatchError(x0$2);
               }
            })).toIndexedSeq();
            return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.resourceOffers(workOffers, true);
         });
         if (taskDescs.nonEmpty()) {
            this.launchTasks(taskDescs);
         }
      }

      private WorkerOffer buildWorkerOffer(final String executorId, final ExecutorData executorData) {
         ExecutorResourcesAmounts resources = ExecutorResourcesAmounts$.MODULE$.apply(executorData.resourcesInfo());
         return new WorkerOffer(executorId, executorData.executorHost(), executorData.freeCores(), new Some(executorData.executorAddress().hostPort()), resources, executorData.resourceProfileId());
      }

      public void onDisconnected(final RpcAddress remoteAddress) {
         this.addressToExecutorId().get(remoteAddress).foreach((x$6) -> {
            $anonfun$onDisconnected$1(this, x$6);
            return BoxedUnit.UNIT;
         });
      }

      public void org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$makeOffers(final String executorId) {
         Seq taskDescs = (Seq)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$withLock(() -> {
            if (this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().isExecutorActive(executorId)) {
               ExecutorData executorData = (ExecutorData)this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().apply(executorId);
               IndexedSeq workOffers = (IndexedSeq)scala.package..MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new WorkerOffer[]{this.buildWorkerOffer(executorId, executorData)}));
               return this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.resourceOffers(workOffers, false);
            } else {
               return (Seq)scala.package..MODULE$.Seq().empty();
            }
         });
         if (taskDescs.nonEmpty()) {
            this.launchTasks(taskDescs);
         }
      }

      private void launchTasks(final Seq tasks) {
         ((IterableOnceOps)tasks.flatten(scala.Predef..MODULE$.$conforms())).foreach((task) -> {
            $anonfun$launchTasks$1(this, task);
            return BoxedUnit.UNIT;
         });
      }

      public void org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$removeExecutor(final String executorId, final ExecutorLossReason reason) {
         this.logDebug((Function0)(() -> "Asked to remove executor " + executorId + " with reason " + reason));
         Option var4 = this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().get(executorId);
         if (var4 instanceof Some var5) {
            ExecutorData executorInfo = (ExecutorData)var5.value();
            synchronized(this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer()){}

            Object var9;
            try {
               this.addressToExecutorId().$minus$eq(executorInfo.executorAddress());
               this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().$minus$eq(executorId);
               this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().executorsPendingLossReason().$minus$eq(executorId);
               boolean killedByDriver = BoxesRunTime.unboxToBoolean(this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().executorsPendingToRemove().remove(executorId).getOrElse((JFunction0.mcZ.sp)() -> false));
               Option decommissionInfoOpt = this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().executorsPendingDecommission().remove(executorId);
               Object var15;
               if (killedByDriver) {
                  var15 = ExecutorKilled$.MODULE$;
               } else if (decommissionInfoOpt.isDefined()) {
                  ExecutorDecommissionInfo decommissionInfo = (ExecutorDecommissionInfo)decommissionInfoOpt.get();
                  var15 = new ExecutorDecommission(decommissionInfo.workerHost(), decommissionInfo.message());
               } else {
                  var15 = reason;
               }

               var9 = var15;
            } catch (Throwable var14) {
               throw var14;
            }

            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().totalCoreCount().addAndGet(-executorInfo.totalCores());
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().totalRegisteredExecutors().addAndGet(-1);
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.executorLost(executorId, (ExecutorLossReason)var9);
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus().post(new SparkListenerExecutorRemoved(System.currentTimeMillis(), executorId, ((ExecutorLossReason)var9).toString()));
            BoxedUnit var16 = BoxedUnit.UNIT;
         } else if (scala.None..MODULE$.equals(var4)) {
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.sc().env().blockManager().master().removeExecutorAsync(executorId);
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$listenerBus().post(new SparkListenerExecutorRemoved(System.currentTimeMillis(), executorId, reason.toString()));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to remove non-existent executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var4);
         }
      }

      public void org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$removeWorker(final String workerId, final String host, final String message) {
         this.logDebug((Function0)(() -> "Asked to remove worker " + workerId + " with reason " + message));
         this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.workerRemoved(workerId, host, message);
      }

      public boolean disableExecutor(final String executorId) {
         synchronized(this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer()){}

         boolean var4;
         try {
            boolean var10000;
            if (this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().isExecutorActive(executorId)) {
               this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().executorsPendingLossReason().$plus$eq(executorId);
               var10000 = true;
            } else {
               var10000 = this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().executorsPendingToRemove().contains(executorId);
            }

            var4 = var10000;
         } catch (Throwable var6) {
            throw var6;
         }

         if (var4) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Disabling executor ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))));
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.executorLost(executorId, LossReasonPending$.MODULE$);
         }

         return var4;
      }

      // $FF: synthetic method
      public CoarseGrainedSchedulerBackend org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$sparkProperties$1(final Tuple2 x0$1) {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            return k.startsWith("spark.");
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$onStart$4(final RpcEndpointRef x$2) {
         x$2.send(CoarseGrainedClusterMessages.ReviveOffers$.MODULE$);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$makeOffers$2(final DriverEndpoint $this, final Tuple2 x0$1) {
         if (x0$1 != null) {
            String id = (String)x0$1._1();
            return $this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().isExecutorActive(id);
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$onDisconnected$1(final DriverEndpoint $this, final String x$6) {
         $this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$removeExecutor(x$6, new ExecutorProcessLost("Remote RPC client disassociated. Likely due to containers exceeding thresholds, or network issues. Check driver logs for WARN messages.", ExecutorProcessLost$.MODULE$.apply$default$2(), ExecutorProcessLost$.MODULE$.apply$default$3()));
      }

      // $FF: synthetic method
      public static final void $anonfun$launchTasks$2(final DriverEndpoint $this, final TaskDescription task$1, final ByteBuffer serializedTask$1, final TaskSetManager taskSetMgr) {
         try {
            String var10000 = Network$.MODULE$.RPC_MESSAGE_MAX_SIZE().key();
            String msg = "Serialized task %s:%d was %d bytes, which exceeds max allowed: " + var10000 + " (%d bytes). Consider increasing " + Network$.MODULE$.RPC_MESSAGE_MAX_SIZE().key() + " or using broadcast variables for large values.";
            msg = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(msg), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(task$1.taskId()), BoxesRunTime.boxToInteger(task$1.index()), BoxesRunTime.boxToInteger(serializedTask$1.limit()), BoxesRunTime.boxToInteger($this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$maxRpcMessageSize())}));
            taskSetMgr.abort(msg, taskSetMgr.abort$default$2());
         } catch (Exception var6) {
            $this.logError((Function0)(() -> "Exception in error callback"), var6);
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$launchTasks$4(final ExecutorData executorData$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            Map addressAmounts = (Map)x0$1._2();
            ((ResourceAllocator)executorData$1.resourcesInfo().apply(rName)).acquire(addressAmounts);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$launchTasks$1(final DriverEndpoint $this, final TaskDescription task) {
         ByteBuffer serializedTask = TaskDescription$.MODULE$.encode(task);
         if (serializedTask.limit() >= $this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$maxRpcMessageSize()) {
            scala.Option..MODULE$.apply($this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$scheduler.taskIdToTaskSetManager().get(BoxesRunTime.boxToLong(task.taskId()))).foreach((taskSetMgr) -> {
               $anonfun$launchTasks$2($this, task, serializedTask, taskSetMgr);
               return BoxedUnit.UNIT;
            });
         } else {
            ExecutorData executorData = (ExecutorData)$this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$$executorDataMap().apply(task.executorId());
            executorData.freeCores_$eq(executorData.freeCores() - task.cpus());
            task.resources().foreach((x0$1) -> {
               $anonfun$launchTasks$4(executorData, x0$1);
               return BoxedUnit.UNIT;
            });
            $this.logDebug((Function0)(() -> {
               long var10000 = task.taskId();
               return "Launching task " + var10000 + " on executor id: " + task.executorId() + " hostname: " + executorData.executorHost() + ".";
            }));
            executorData.executorEndpoint().send(new CoarseGrainedClusterMessages.LaunchTask(new SerializableBuffer(serializedTask)));
         }
      }

      public DriverEndpoint() {
         if (CoarseGrainedSchedulerBackend.this == null) {
            throw null;
         } else {
            this.$outer = CoarseGrainedSchedulerBackend.this;
            super();
            RpcEndpoint.$init$(this);
            IsolatedThreadSafeRpcEndpoint.$init$(this);
            Logging.$init$(this);
            this.rpcEnv = CoarseGrainedSchedulerBackend.this.rpcEnv();
            this.addressToExecutorId = new HashMap();
            this.org$apache$spark$scheduler$cluster$CoarseGrainedSchedulerBackend$DriverEndpoint$$logUrlHandler = new ExecutorLogUrlHandler((Option)CoarseGrainedSchedulerBackend.this.conf().get((ConfigEntry)UI$.MODULE$.CUSTOM_EXECUTOR_LOG_URL()));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
