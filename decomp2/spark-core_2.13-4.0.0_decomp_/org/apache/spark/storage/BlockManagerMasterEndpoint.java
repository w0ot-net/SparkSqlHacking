package org.apache.spark.storage;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.spark.MapOutputTrackerMaster;
import org.apache.spark.ShuffleStatus;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.IsolatedThreadSafeRpcEndpoint;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.SparkListenerBlockManagerAdded;
import org.apache.spark.scheduler.SparkListenerBlockManagerRemoved;
import org.apache.spark.scheduler.SparkListenerBlockUpdated;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.scheduler.cluster.CoarseGrainedSchedulerBackend$;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.SetOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.Map;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyBoolean;
import scala.runtime.java8.JFunction0;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115c!B*U\u0001Yc\u0006\u0002C8\u0001\u0005\u000b\u0007I\u0011I9\t\u0011U\u0004!\u0011!Q\u0001\nID\u0001B\u001e\u0001\u0003\u0006\u0004%\ta\u001e\u0005\tw\u0002\u0011\t\u0011)A\u0005q\"AA\u0010\u0001B\u0001B\u0003%Q\u0010\u0003\b\u0002\u0004\u0001!\t\u0011!B\u0003\u0002\u0003\u0006I!!\u0002\t\u0015\u0005E\u0001A!A!\u0002\u0013\t\u0019\u0002\u0003\u0006\u0002*\u0001\u0011\t\u0011)A\u0005\u0003WA!\"!\u0013\u0001\u0005\u0003\u0005\u000b\u0011BA&\u0011)\t\t\u0006\u0001BC\u0002\u0013%\u00111\u000b\u0005\u000b\u0003?\u0002!\u0011!Q\u0001\n\u0005U\u0003\"CA1\u0001\t\u0005\t\u0015!\u0003y\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003KB!\"! \u0001\u0011\u000b\u0007I\u0011BA*\u0011%\ty\b\u0001b\u0001\n\u0013\t\t\t\u0003\u0005\u00028\u0002\u0001\u000b\u0011BAB\u0011%\tI\f\u0001b\u0001\n\u0013\tY\f\u0003\u0005\u0002J\u0002\u0001\u000b\u0011BA_\u0011%\tY\r\u0001b\u0001\n\u0013\ti\r\u0003\u0005\u0002R\u0002\u0001\u000b\u0011BAh\u0011%\t\u0019\u000e\u0001b\u0001\n\u0013\t)\u000e\u0003\u0005\u0002^\u0002\u0001\u000b\u0011BAl\u0011%\ty\u000e\u0001b\u0001\n\u0013\t\t\u000f\u0003\u0005\u0002x\u0002\u0001\u000b\u0011BAr\u0011%\tI\u0010\u0001b\u0001\n\u0013\tY\u0010\u0003\u0005\u0003\u000e\u0001\u0001\u000b\u0011BA\u007f\u0011%\u0011y\u0001\u0001b\u0001\n\u0013\u0011\t\u0002\u0003\u0005\u0003\u0014\u0001\u0001\u000b\u0011\u0002B\u0003\u0011%\u0011)\u0002\u0001b\u0001\n\u0013\u00119\u0002\u0003\u0005\u0003 \u0001\u0001\u000b\u0011\u0002B\r\u0011%\u0011\t\u0003\u0001b\u0001\n\u0013\u0011\u0019\u0003\u0003\u0005\u0003,\u0001\u0001\u000b\u0011\u0002B\u0013\u0011%\u0011i\u0003\u0001b\u0001\n\u0013\u0011y\u0003\u0003\u0005\u0003>\u0001\u0001\u000b\u0011\u0002B\u0019\u0011%\u0011y\u0004\u0001b\u0001\n\u0017\u0011\t\u0005\u0003\u0005\u0003N\u0001\u0001\u000b\u0011\u0002B\"\u0011%\u0011y\u0005\u0001b\u0001\n\u0013\u0011\t\u0006\u0003\u0005\u0003Z\u0001\u0001\u000b\u0011\u0002B*\u0011!\u0011Y\u0006\u0001b\u0001\n\u00039\bb\u0002B/\u0001\u0001\u0006I\u0001\u001f\u0005\n\u0005?\u0002!\u0019!C\u0001\u0005CB\u0001B!\u001b\u0001A\u0003%!1\r\u0005\t\u0005W\u0002!\u0019!C\u0005o\"9!Q\u000e\u0001!\u0002\u0013A\b\u0002\u0003B8\u0001\t\u0007I\u0011B<\t\u000f\tE\u0004\u0001)A\u0005q\"A!1\u000f\u0001C\u0002\u0013%q\u000fC\u0004\u0003v\u0001\u0001\u000b\u0011\u0002=\t\u0013\t]\u0004A1A\u0005\n\t\r\u0002\u0002\u0003B=\u0001\u0001\u0006IA!\n\t\u0015\tm\u0004\u0001#b\u0001\n\u0013\u0011i\b\u0003\u0005\u0003\u0006\u0002\u0011\r\u0011\"\u0003x\u0011\u001d\u00119\t\u0001Q\u0001\naDqA!#\u0001\t\u0003\u0012Y\tC\u0004\u0003*\u0002!IAa+\t\u000f\tE\u0006\u0001\"\u0003\u00034\"9!Q\u0018\u0001\u0005\n\t}\u0006b\u0002Bc\u0001\u0011%!q\u0019\u0005\b\u0005{\u0004A\u0011\u0002B\u0000\u0011\u001d\u0019\t\u0002\u0001C\u0005\u0007'Aqa!\b\u0001\t\u0013\u0019y\u0002C\u0004\u0004*\u0001!Iaa\u000b\t\u000f\rE\u0002\u0001\"\u0003\u00044!91q\u0007\u0001\u0005\n\re\u0002bBB \u0001\u0011%1\u0011\t\u0005\b\u0007K\u0002A\u0011BB4\u0011\u001d\u0019Y\u0007\u0001C\u0005\u0007[Bqa!\u001f\u0001\t\u0013\u0019Y\bC\u0004\u0004\u0006\u0002!Iaa\"\t\u000f\rm\u0005\u0001\"\u0003\u0004\u001e\"91q\u0016\u0001\u0005\n\rE\u0006bBB[\u0001\u0011%1q\u0017\u0005\b\u0007#\u0004A\u0011BBj\u0011\u001d\u0019Y\u000e\u0001C\u0005\u0007;Dqa!>\u0001\t\u0013\u00199\u0010C\u0004\u0004~\u0002!Iaa@\t\u000f\u0011=\u0001\u0001\"\u0003\u0005\u0012!9Aq\u0004\u0001\u0005\n\u0011\u0005\u0002b\u0002C\u0013\u0001\u0011%Aq\u0005\u0005\b\to\u0001A\u0011\u0002C\u001d\u0011\u001d!y\u0004\u0001C\u0005\t\u0003Bq\u0001\"\u0013\u0001\t\u0003\"YE\u0001\u000eCY>\u001c7.T1oC\u001e,'/T1ti\u0016\u0014XI\u001c3q_&tGO\u0003\u0002V-\u000691\u000f^8sC\u001e,'BA,Y\u0003\u0015\u0019\b/\u0019:l\u0015\tI&,\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0006\u0019qN]4\u0014\t\u0001i6-\u001b\t\u0003=\u0006l\u0011a\u0018\u0006\u0002A\u0006)1oY1mC&\u0011!m\u0018\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0011<W\"A3\u000b\u0005\u00194\u0016a\u0001:qG&\u0011\u0001.\u001a\u0002\u001e\u0013N|G.\u0019;fIRC'/Z1e'\u00064WM\u00159d\u000b:$\u0007o\\5oiB\u0011!.\\\u0007\u0002W*\u0011ANV\u0001\tS:$XM\u001d8bY&\u0011an\u001b\u0002\b\u0019><w-\u001b8h\u0003\u0019\u0011\boY#om\u000e\u0001Q#\u0001:\u0011\u0005\u0011\u001c\u0018B\u0001;f\u0005\u0019\u0011\u0006oY#om\u00069!\u000f]2F]Z\u0004\u0013aB5t\u0019>\u001c\u0017\r\\\u000b\u0002qB\u0011a,_\u0005\u0003u~\u0013qAQ8pY\u0016\fg.\u0001\u0005jg2{7-\u00197!\u0003\u0011\u0019wN\u001c4\u0011\u0005y|X\"\u0001,\n\u0007\u0005\u0005aKA\u0005Ta\u0006\u00148nQ8oM\u0006\u0001uN]4%CB\f7\r[3%gB\f'o\u001b\u0013ti>\u0014\u0018mZ3%\u00052|7m['b]\u0006<WM]'bgR,'/\u00128ea>Lg\u000e\u001e\u0013%Y&\u001cH/\u001a8fe\n+8\u000f\u0005\u0003\u0002\b\u00055QBAA\u0005\u0015\r\tYAV\u0001\ng\u000eDW\rZ;mKJLA!a\u0004\u0002\n\tyA*\u001b<f\u0019&\u001cH/\u001a8fe\n+8/\u0001\rfqR,'O\\1m\u00052|7m[*u_J,7\t\\5f]R\u0004RAXA\u000b\u00033I1!a\u0006`\u0005\u0019y\u0005\u000f^5p]B!\u00111DA\u0013\u001b\t\tiB\u0003\u0003\u0002 \u0005\u0005\u0012aB:ik\u001a4G.\u001a\u0006\u0004\u0003G1\u0016a\u00028fi^|'o[\u0005\u0005\u0003O\tiB\u0001\rFqR,'O\\1m\u00052|7m[*u_J,7\t\\5f]R\f\u0001C\u00197pG.l\u0015M\\1hKJLeNZ8\u0011\u0011\u00055\u0012qGA\u001e\u0003\u0007j!!a\f\u000b\t\u0005E\u00121G\u0001\b[V$\u0018M\u00197f\u0015\r\t)dX\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u001d\u0003_\u00111!T1q!\u0011\ti$a\u0010\u000e\u0003QK1!!\u0011U\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\u0004B!!\u0010\u0002F%\u0019\u0011q\t+\u0003!\tcwnY6NC:\fw-\u001a:J]\u001a|\u0017\u0001E7ba>+H\u000f];u)J\f7m[3s!\rq\u0018QJ\u0005\u0004\u0003\u001f2&AF'ba>+H\u000f];u)J\f7m[3s\u001b\u0006\u001cH/\u001a:\u0002\u001f}\u001b\b.\u001e4gY\u0016l\u0015M\\1hKJ,\"!!\u0016\u0011\t\u0005]\u00131L\u0007\u0003\u00033R1!a\bW\u0013\u0011\ti&!\u0017\u0003\u001dMCWO\u001a4mK6\u000bg.Y4fe\u0006\u0001rl\u001d5vM\u001adW-T1oC\u001e,'\u000fI\u0001\tSN$%/\u001b<fe\u00061A(\u001b8jiz\"B#a\u001a\u0002j\u0005-\u0014QNA8\u0003g\n)(a\u001e\u0002z\u0005m\u0004cAA\u001f\u0001!)q.\u0004a\u0001e\")a/\u0004a\u0001q\")A0\u0004a\u0001{\"9\u0011\u0011O\u0007A\u0002\u0005\u0015\u0011a\u00037jgR,g.\u001a:CkNDq!!\u0005\u000e\u0001\u0004\t\u0019\u0002C\u0004\u0002*5\u0001\r!a\u000b\t\u000f\u0005%S\u00021\u0001\u0002L!9\u0011\u0011K\u0007A\u0002\u0005U\u0003BBA1\u001b\u0001\u0007\u00010\u0001\btQV4g\r\\3NC:\fw-\u001a:\u0002+\u0015DXmY;u_JLE\rV8M_\u000e\fG\u000eR5sgV\u0011\u00111\u0011\t\t\u0003\u000b\u000b9*a'\u000226\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000bY)A\u0003dC\u000eDWM\u0003\u0003\u0002\u000e\u0006=\u0015AB2p[6|gN\u0003\u0003\u0002\u0012\u0006M\u0015AB4p_\u001edWM\u0003\u0002\u0002\u0016\u0006\u00191m\\7\n\t\u0005e\u0015q\u0011\u0002\u0006\u0007\u0006\u001c\u0007.\u001a\t\u0005\u0003;\u000bYK\u0004\u0003\u0002 \u0006\u001d\u0006cAAQ?6\u0011\u00111\u0015\u0006\u0004\u0003K\u0003\u0018A\u0002\u001fs_>$h(C\u0002\u0002*~\u000ba\u0001\u0015:fI\u00164\u0017\u0002BAW\u0003_\u0013aa\u0015;sS:<'bAAU?B)a,a-\u0002\u001c&\u0019\u0011QW0\u0003\u000b\u0005\u0013(/Y=\u0002-\u0015DXmY;u_JLE\rV8M_\u000e\fG\u000eR5sg\u0002\n1D\u00197pG.\u001cF/\u0019;vg\nK8\u000b[;gM2,7+\u001a:wS\u000e,WCAA_!!\ti#a0\u0002<\u0005\r\u0017\u0002BAa\u0003_\u0011q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0003\u0002>\u0005\u0015\u0017bAAd)\n)\"\t\\8dWN#\u0018\r^;t!\u0016\u0014(\t\\8dW&#\u0017\u0001\b2m_\u000e\\7\u000b^1ukN\u0014\u0015p\u00155vM\u001adWmU3sm&\u001cW\rI\u0001\u0019E2|7m['b]\u0006<WM]%e\u0005f,\u00050Z2vi>\u0014XCAAh!!\ti#a0\u0002\u001c\u0006m\u0012!\u00072m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\"z\u000bb,7-\u001e;pe\u0002\na\u0004Z3d_6l\u0017n]:j_:Lgn\u001a\"m_\u000e\\W*\u00198bO\u0016\u00148+\u001a;\u0016\u0005\u0005]\u0007CBA\u0017\u00033\fY$\u0003\u0003\u0002\\\u0006=\"a\u0002%bg\"\u001cV\r^\u0001 I\u0016\u001cw.\\7jgNLwN\\5oO\ncwnY6NC:\fw-\u001a:TKR\u0004\u0013A\u00042m_\u000e\\Gj\\2bi&|gn]\u000b\u0003\u0003G\u0004\u0002\"!:\u0002p\u0006E\u0018q[\u0007\u0003\u0003OTA!!;\u0002l\u0006!Q\u000f^5m\u0015\t\ti/\u0001\u0003kCZ\f\u0017\u0002BAa\u0003O\u0004B!!\u0010\u0002t&\u0019\u0011Q\u001f+\u0003\u000f\tcwnY6JI\u0006y!\r\\8dW2{7-\u0019;j_:\u001c\b%\u0001\tuS\u0012$vN\u00153e\u00052|7m[%egV\u0011\u0011Q \t\t\u0003[\ty,a@\u0003\u0006A\u0019aL!\u0001\n\u0007\t\rqL\u0001\u0003M_:<\u0007CBA\u0017\u00033\u00149\u0001\u0005\u0003\u0002>\t%\u0011b\u0001B\u0006)\nQ!\u000b\u0012#CY>\u001c7.\u00133\u0002#QLG\rV8SI\u0012\u0014En\\2l\u0013\u0012\u001c\b%\u0001\nj]ZL7/\u001b2mKJ#EI\u00117pG.\u001cXC\u0001B\u0003\u0003MIgN^5tS\ndWM\u0015#E\u00052|7m[:!\u0003Y\u0019\b.\u001e4gY\u0016lUM]4fe2{7-\u0019;j_:\u001cXC\u0001B\r!!\tiCa\u0007\u0002\u001c\u0006m\u0012\u0002\u0002B\u000f\u0003_\u0011Q\u0002T5oW\u0016$\u0007*Y:i\u001b\u0006\u0004\u0018aF:ik\u001a4G.Z'fe\u001e,'\u000fT8dCRLwN\\:!\u0003ii\u0017\r\u001f*fi\u0006Lg.\u001a3NKJ<WM\u001d'pG\u0006$\u0018n\u001c8t+\t\u0011)\u0003E\u0002_\u0005OI1A!\u000b`\u0005\rIe\u000e^\u0001\u001c[\u0006D(+\u001a;bS:,G-T3sO\u0016\u0014Hj\\2bi&|gn\u001d\u0011\u0002\u001b\u0005\u001c8\u000e\u00165sK\u0006$\u0007k\\8m+\t\u0011\t\u0004\u0005\u0003\u00034\teRB\u0001B\u001b\u0015\u0011\u00119$a:\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0003\u0003<\tU\"A\u0005+ie\u0016\fG\rU8pY\u0016CXmY;u_J\fa\"Y:l)\"\u0014X-\u00193Q_>d\u0007%A\nbg.,\u00050Z2vi&|gnQ8oi\u0016DH/\u0006\u0002\u0003DA!!Q\tB%\u001b\t\u00119EC\u0002\u00038}KAAa\u0013\u0003H\tyR\t_3dkRLwN\\\"p]R,\u0007\u0010^#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002)\u0005\u001c8.\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;!\u00039!x\u000e]8m_\u001eLX*\u00199qKJ,\"Aa\u0015\u0011\t\u0005u\"QK\u0005\u0004\u0005/\"&A\u0004+pa>dwnZ=NCB\u0004XM]\u0001\u0010i>\u0004x\u000e\\8hs6\u000b\u0007\u000f]3sA\u0005!\u0002O]8bGRLg/\u001a7z%\u0016\u0004H.[2bi\u0016\fQ\u0003\u001d:pC\u000e$\u0018N^3msJ+\u0007\u000f\\5dCR,\u0007%A\teK\u001a\fW\u000f\u001c;Sa\u000e$\u0016.\\3pkR,\"Aa\u0019\u0011\u0007\u0011\u0014)'C\u0002\u0003h\u0015\u0014!B\u00159d)&lWm\\;u\u0003I!WMZ1vYR\u0014\u0006o\u0019+j[\u0016|W\u000f\u001e\u0011\u0002/A,8\u000f\u001b\"bg\u0016$7\u000b[;gM2,WI\\1cY\u0016$\u0017\u0001\u00079vg\"\u0014\u0015m]3e'\",hM\u001a7f\u000b:\f'\r\\3eA\u0005QS\r\u001f;fe:\fGn\u00155vM\u001adWmU3sm&\u001cWMU3n_Z,7\u000b[;gM2,WI\\1cY\u0016$\u0017aK3yi\u0016\u0014h.\u00197TQV4g\r\\3TKJ4\u0018nY3SK6|g/Z*ik\u001a4G.Z#oC\ndW\r\u001a\u0011\u0002K\u0015DH/\u001a:oC2\u001c\u0006.\u001e4gY\u0016\u001cVM\u001d<jG\u0016\u0014F\r\u001a$fi\u000eDWI\\1cY\u0016$\u0017AJ3yi\u0016\u0014h.\u00197TQV4g\r\\3TKJ4\u0018nY3SI\u00124U\r^2i\u000b:\f'\r\\3eA\u0005QR\r\u001f;fe:\fGn\u00155vM\u001adWmU3sm&\u001cW\rU8si\u0006YR\r\u001f;fe:\fGn\u00155vM\u001adWmU3sm&\u001cW\rU8si\u0002\na\u0002\u001a:jm\u0016\u0014XI\u001c3q_&tG/\u0006\u0002\u0003\u0000A\u0019AM!!\n\u0007\t\rUM\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002/Q\u0014\u0018mY6j]\u001e\u001c\u0015m\u00195f-&\u001c\u0018NY5mSRL\u0018\u0001\u0007;sC\u000e\\\u0017N\\4DC\u000eDWMV5tS\nLG.\u001b;zA\u0005y!/Z2fSZ,\u0017I\u001c3SKBd\u0017\u0010\u0006\u0003\u0003\u000e\n}\u0005c\u00020\u0003\u0010\nM%\u0011T\u0005\u0004\u0005#{&a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0011\u0007y\u0013)*C\u0002\u0003\u0018~\u00131!\u00118z!\rq&1T\u0005\u0004\u0005;{&\u0001B+oSRDqA!)7\u0001\u0004\u0011\u0019+A\u0004d_:$X\r\u001f;\u0011\u0007\u0011\u0014)+C\u0002\u0003(\u0016\u0014aB\u00159d\u0007\u0006dGnQ8oi\u0016DH/A\tjgJ#EI\u00117pG.4\u0016n]5cY\u0016$2\u0001\u001fBW\u0011\u001d\u0011yk\u000ea\u0001\u0005\u000f\tqA\u00197pG.LE-\u0001\rva\u0012\fG/\u001a*E\t\ncwnY6WSNL'-\u001b7jif$bA!'\u00036\ne\u0006b\u0002B\\q\u0001\u0007\u0011q`\u0001\u0007i\u0006\u001c8.\u00133\t\r\tm\u0006\b1\u0001y\u0003\u001d1\u0018n]5cY\u0016\fa#\u001e9eCR,'\u000b\u0012#CY>\u001c7\u000eV1tW&sgm\u001c\u000b\u0007\u00053\u0013\tMa1\t\u000f\t=\u0016\b1\u0001\u0003\b!9!qW\u001dA\u0002\u0005}\u0018!\u00075b]\u0012dWM\u00117pG.\u0014V-\\8wC24\u0015-\u001b7ve\u0016,BA!3\u0003dRQ!1\u001aBx\u0005g\u0014)P!?\u0011\u000fy\u0013yI!4\u0003`B!!q\u001aBm\u001d\u0011\u0011\tN!6\u000f\t\u0005\u0005&1[\u0005\u0002A&\u0019!q[0\u0002\u000fA\f7m[1hK&!!1\u001cBo\u0005%!\u0006N]8xC\ndWMC\u0002\u0003X~\u0003BA!9\u0003d2\u0001Aa\u0002Bsu\t\u0007!q\u001d\u0002\u0002)F!!\u0011\u001eBJ!\rq&1^\u0005\u0004\u0005[|&a\u0002(pi\"Lgn\u001a\u0005\b\u0005cT\u0004\u0019AAN\u0003%\u0011Gn\\2l)f\u0004X\rC\u0004\u00030j\u0002\r!a'\t\u000f\t](\b1\u0001\u0002<\u0005!!-\\%e\u0011\u001d\u0011YP\u000fa\u0001\u0005?\fA\u0002Z3gCVdGOV1mk\u0016\f\u0011B]3n_Z,'\u000b\u001a3\u0015\t\r\u00051Q\u0002\t\u0007\u0005\u000b\u001a\u0019aa\u0002\n\t\r\u0015!q\t\u0002\u0007\rV$XO]3\u0011\r\t=7\u0011\u0002B\u0013\u0013\u0011\u0019YA!8\u0003\u0007M+\u0017\u000fC\u0004\u0004\u0010m\u0002\rA!\n\u0002\u000bI$G-\u00133\u0002\u001bI,Wn\u001c<f'\",hM\u001a7f)\u0011\u0019)b!\u0007\u0011\r\t\u001531AB\f!\u0015\u0011ym!\u0003y\u0011\u001d\u0019Y\u0002\u0010a\u0001\u0005K\t\u0011b\u001d5vM\u001adW-\u00133\u0002\u001fI,Wn\u001c<f\u0005J|\u0017\rZ2bgR$ba!\u0001\u0004\"\r\u0015\u0002bBB\u0012{\u0001\u0007\u0011q`\u0001\fEJ|\u0017\rZ2bgRLE\r\u0003\u0004\u0004(u\u0002\r\u0001_\u0001\u0011e\u0016lwN^3Ge>lGI]5wKJ\f!C]3n_Z,'\t\\8dW6\u000bg.Y4feR!!\u0011TB\u0017\u0011\u001d\u0019yC\u0010a\u0001\u0003w\taB\u00197pG.l\u0015M\\1hKJLE-A\tbI\u0012lUM]4fe2{7-\u0019;j_:$BA!'\u00046!91qF A\u0002\u0005m\u0012A\u0004:f[>4X-\u0012=fGV$xN\u001d\u000b\u0005\u00053\u001bY\u0004C\u0004\u0004>\u0001\u0003\r!a'\u0002\r\u0015DXmY%e\u0003q9W\r\u001e*fa2L7-\u0019;f\u0013:4wNR8s%\u0012#%\t\\8dWN$Baa\u0011\u0004dA1!qZB\u0005\u0007\u000b\u0002Baa\u0012\u0004^9!1\u0011JB-\u001d\u0011\u0019Yea\u0016\u000f\t\r53Q\u000b\b\u0005\u0007\u001f\u001a\u0019F\u0004\u0003\u0002\"\u000eE\u0013\"A.\n\u0005eS\u0016BA,Y\u0013\t)f+C\u0002\u0004\\Q\u000bAC\u00117pG.l\u0015M\\1hKJlUm]:bO\u0016\u001c\u0018\u0002BB0\u0007C\u0012aBU3qY&\u001c\u0017\r^3CY>\u001c7NC\u0002\u0004\\QCqaa\fB\u0001\u0004\tY$\u0001\fsK6|g/\u001a\"m_\u000e\\gI]8n/>\u00148.\u001a:t)\u0011\u0011Ij!\u001b\t\u000f\t=&\t1\u0001\u0002r\u0006aQ.Z7pef\u001cF/\u0019;vgV\u00111q\u000e\t\t\u0003;\u001b\t(a\u000f\u0004t%!\u0011\u0011HAX!\u001dq6QOA\u0000\u0003\u007fL1aa\u001e`\u0005\u0019!V\u000f\u001d7fe\u0005i1\u000f^8sC\u001e,7\u000b^1ukN,\"a! \u0011\u000by\u000b\u0019la \u0011\t\u0005u2\u0011Q\u0005\u0004\u0007\u0007#&!D*u_J\fw-Z*uCR,8/A\u0006cY>\u001c7n\u0015;biV\u001cHCBBE\u0007+\u001b9\n\u0005\u0005\u0002\u001e\u000eE\u00141HBF!\u0019\u0011)ea\u0001\u0004\u000eB)a,!\u0006\u0004\u0010B!\u0011QHBI\u0013\r\u0019\u0019\n\u0016\u0002\f\u00052|7m[*uCR,8\u000fC\u0004\u00030\u0016\u0003\r!!=\t\r\reU\t1\u0001y\u0003M\t7o[*u_J\fw-Z#oIB|\u0017N\u001c;t\u0003M9W\r^'bi\u000eD\u0017N\\4CY>\u001c7.\u00133t)\u0019\u0019yja)\u0004.B1!QIB\u0002\u0007C\u0003bAa4\u0004\n\u0005E\bbBBS\r\u0002\u00071qU\u0001\u0007M&dG/\u001a:\u0011\ry\u001bI+!=y\u0013\r\u0019Yk\u0018\u0002\n\rVt7\r^5p]FBaa!'G\u0001\u0004A\u0018AH3yi\u0016\u0014h.\u00197TQV4g\r\\3TKJ4\u0018nY3JI>s\u0007j\\:u)\u0011\tYda-\t\u000f\r=r\t1\u0001\u0002<\u0005A!/Z4jgR,'\u000f\u0006\b\u0002<\re6QXBa\u0007\u000b\u001cIm!4\t\u000f\rm\u0006\n1\u0001\u0002<\u0005)\u0012\u000eZ,ji\"|W\u000f\u001e+pa>dwnZ=J]\u001a|\u0007bBB`\u0011\u0002\u0007\u0011\u0011W\u0001\nY>\u001c\u0017\r\u001c#jeNDqaa1I\u0001\u0004\ty0\u0001\tnCb|e\u000eS3ba6+WnU5{K\"91q\u0019%A\u0002\u0005}\u0018!E7bq>3g\rS3ba6+WnU5{K\"911\u001a%A\u0002\t}\u0014aD:u_J\fw-Z#oIB|\u0017N\u001c;\t\r\r=\u0007\n1\u0001y\u00031I7OU3SK\u001eL7\u000f^3s\u0003Y)\b\u000fZ1uKNCWO\u001a4mK\ncwnY6J]\u001a|GCBBk\u0007/\u001cI\u000eE\u0003\u0003F\r\r\u0001\u0010C\u0004\u00030&\u0003\r!!=\t\u000f\r=\u0012\n1\u0001\u0002<\u0005yQ\u000f\u001d3bi\u0016\u0014En\\2l\u0013:4w\u000eF\u0006y\u0007?\u001c\toa9\u0004n\u000eE\bbBB\u0018\u0015\u0002\u0007\u00111\b\u0005\b\u0005_S\u0005\u0019AAy\u0011\u001d\u0019)O\u0013a\u0001\u0007O\fAb\u001d;pe\u0006<W\rT3wK2\u0004B!!\u0010\u0004j&\u001911\u001e+\u0003\u0019M#xN]1hK2+g/\u001a7\t\u000f\r=(\n1\u0001\u0002\u0000\u00069Q.Z7TSj,\u0007bBBz\u0015\u0002\u0007\u0011q`\u0001\tI&\u001c8nU5{K\u0006aq-\u001a;M_\u000e\fG/[8ogR!1\u0011`B~!\u0019\u0011ym!\u0003\u0002<!9!qV&A\u0002\u0005E\u0018!F4fi2{7-\u0019;j_:\u001c\u0018I\u001c3Ti\u0006$Xo\u001d\u000b\u0007\t\u0003!I\u0001b\u0003\u0011\u000by\u000b)\u0002b\u0001\u0011\t\r\u001dCQA\u0005\u0005\t\u000f\u0019\tGA\fCY>\u001c7\u000eT8dCRLwN\\:B]\u0012\u001cF/\u0019;vg\"9!q\u0016'A\u0002\u0005E\bb\u0002C\u0007\u0019\u0002\u0007\u00111T\u0001\u000ee\u0016\fX/Z:uKJDun\u001d;\u00029\u001d,G\u000fT8dCRLwN\\:Nk2$\u0018\u000e\u001d7f\u00052|7m[%egR!A1\u0003C\r!\u0019\u0011y\r\"\u0006\u0004z&!Aq\u0003Bo\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\b\t7i\u0005\u0019\u0001C\u000f\u0003!\u0011Gn\\2l\u0013\u0012\u001c\b#\u00020\u00024\u0006E\u0018\u0001C4fiB+WM]:\u0015\t\reH1\u0005\u0005\b\u0007_q\u0005\u0019AA\u001e\u0003u9W\r^*ik\u001a4G.\u001a)vg\"lUM]4fe2{7-\u0019;j_:\u001cHCBB}\tS!i\u0003C\u0004\u0005,=\u0003\rA!\n\u0002!9,X.T3sO\u0016\u00148OT3fI\u0016$\u0007b\u0002C\u0018\u001f\u0002\u0007A\u0011G\u0001\u000eQ>\u001cHo\u001d+p\r&dG/\u001a:\u0011\r\u0005uE1GAN\u0013\u0011!)$a,\u0003\u0007M+G/A\u0010sK6|g/Z*ik\u001a4G.\u001a)vg\"lUM]4fe2{7-\u0019;j_:$BA!'\u0005<!9AQ\b)A\u0002\u0005m\u0015\u0001\u00025pgR\facZ3u\u000bb,7-\u001e;pe\u0016sG\r]8j]R\u0014VM\u001a\u000b\u0005\t\u0007\")\u0005E\u0003_\u0003+\u0011y\bC\u0004\u0005HE\u0003\r!a'\u0002\u0015\u0015DXmY;u_JLE-\u0001\u0004p]N#x\u000e\u001d\u000b\u0003\u00053\u0003"
)
public class BlockManagerMasterEndpoint implements IsolatedThreadSafeRpcEndpoint, Logging {
   private ShuffleManager shuffleManager;
   private RpcEndpointRef org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint;
   private final RpcEnv rpcEnv;
   private final boolean isLocal;
   private final SparkConf conf;
   public final LiveListenerBus org$apache$spark$storage$BlockManagerMasterEndpoint$$listenerBus;
   private final Option externalBlockStoreClient;
   private final Map blockManagerInfo;
   private final MapOutputTrackerMaster mapOutputTracker;
   private ShuffleManager _shuffleManager;
   private final boolean isDriver;
   private final Cache executorIdToLocalDirs;
   private final HashMap blockStatusByShuffleService;
   private final HashMap org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor;
   private final HashSet org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet;
   private final java.util.HashMap blockLocations;
   private final HashMap tidToRddBlockIds;
   private final HashSet invisibleRDDBlocks;
   private final LinkedHashMap shuffleMergerLocations;
   private final int maxRetainedMergerLocations;
   private final ThreadPoolExecutor askThreadPool;
   private final ExecutionContextExecutorService org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext;
   private final TopologyMapper topologyMapper;
   private final boolean proactivelyReplicate;
   private final RpcTimeout defaultRpcTimeout;
   private final boolean pushBasedShuffleEnabled;
   private final boolean externalShuffleServiceRemoveShuffleEnabled;
   private final boolean externalShuffleServiceRddFetchEnabled;
   private final int externalShuffleServicePort;
   private final boolean trackingCacheVisibility;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

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

   public PartialFunction receive() {
      return RpcEndpoint.receive$(this);
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

   public boolean isLocal() {
      return this.isLocal;
   }

   private ShuffleManager _shuffleManager() {
      return this._shuffleManager;
   }

   private ShuffleManager shuffleManager$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.shuffleManager = (ShuffleManager).MODULE$.apply(this._shuffleManager()).getOrElse(() -> SparkEnv$.MODULE$.get().shuffleManager());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this._shuffleManager = null;
      return this.shuffleManager;
   }

   private ShuffleManager shuffleManager() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.shuffleManager$lzycompute() : this.shuffleManager;
   }

   private Cache executorIdToLocalDirs() {
      return this.executorIdToLocalDirs;
   }

   private HashMap blockStatusByShuffleService() {
      return this.blockStatusByShuffleService;
   }

   public HashMap org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor() {
      return this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor;
   }

   public HashSet org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet() {
      return this.org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet;
   }

   private java.util.HashMap blockLocations() {
      return this.blockLocations;
   }

   private HashMap tidToRddBlockIds() {
      return this.tidToRddBlockIds;
   }

   private HashSet invisibleRDDBlocks() {
      return this.invisibleRDDBlocks;
   }

   private LinkedHashMap shuffleMergerLocations() {
      return this.shuffleMergerLocations;
   }

   private int maxRetainedMergerLocations() {
      return this.maxRetainedMergerLocations;
   }

   private ThreadPoolExecutor askThreadPool() {
      return this.askThreadPool;
   }

   public ExecutionContextExecutorService org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext() {
      return this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext;
   }

   private TopologyMapper topologyMapper() {
      return this.topologyMapper;
   }

   public boolean proactivelyReplicate() {
      return this.proactivelyReplicate;
   }

   public RpcTimeout defaultRpcTimeout() {
      return this.defaultRpcTimeout;
   }

   private boolean pushBasedShuffleEnabled() {
      return this.pushBasedShuffleEnabled;
   }

   private boolean externalShuffleServiceRemoveShuffleEnabled() {
      return this.externalShuffleServiceRemoveShuffleEnabled;
   }

   private boolean externalShuffleServiceRddFetchEnabled() {
      return this.externalShuffleServiceRddFetchEnabled;
   }

   private int externalShuffleServicePort() {
      return this.externalShuffleServicePort;
   }

   private RpcEndpointRef driverEndpoint$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint = RpcUtils$.MODULE$.makeDriverRef(CoarseGrainedSchedulerBackend$.MODULE$.ENDPOINT_NAME(), this.conf, this.rpcEnv());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint;
   }

   public RpcEndpointRef org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.driverEndpoint$lzycompute() : this.org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint;
   }

   private boolean trackingCacheVisibility() {
      return this.trackingCacheVisibility;
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final BlockManagerMasterEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof BlockManagerMessages.RegisterBlockManager var5) {
               BlockManagerId id = var5.blockManagerId();
               String[] localDirs = var5.localDirs();
               long maxOnHeapMemSize = var5.maxOnHeapMemSize();
               long maxOffHeapMemSize = var5.maxOffHeapMemSize();
               RpcEndpointRef endpoint = var5.sender();
               boolean isReRegister = var5.isReRegister();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$register(id, localDirs, maxOnHeapMemSize, maxOffHeapMemSize, endpoint, isReRegister));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.UpdateBlockInfo var14) {
               BlockManagerId blockManagerId = var14.blockManagerId();
               BlockId blockId = var14.blockId();
               StorageLevel storageLevel = var14.storageLevel();
               long deserializedSize = var14.memSize();
               long size = var14.diskSize();
               if (blockId.isShuffle()) {
                  this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$updateShuffleBlockInfo(blockId, blockManagerId).foreach((success) -> {
                     $anonfun$applyOrElse$1(this, var14, BoxesRunTime.unboxToBoolean(success));
                     return BoxedUnit.UNIT;
                  }, this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
                  return BoxedUnit.UNIT;
               } else {
                  this.handleResult$1(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$updateBlockInfo(blockManagerId, blockId, storageLevel, deserializedSize, size), var14);
                  return BoxedUnit.UNIT;
               }
            } else if (x1 instanceof BlockManagerMessages.GetLocations var22) {
               BlockId blockId = var22.blockId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocations(blockId));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetLocationsAndStatus var24) {
               BlockId blockId = var24.blockId();
               String requesterHost = var24.requesterHost();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocationsAndStatus(blockId, requesterHost));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetLocationsMultipleBlockIds var27) {
               BlockId[] blockIds = var27.blockIds();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocationsMultipleBlockIds(blockIds));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetPeers var29) {
               BlockManagerId blockManagerId = var29.blockManagerId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getPeers(blockManagerId));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetExecutorEndpointRef var31) {
               String executorId = var31.executorId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getExecutorEndpointRef(executorId));
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.GetMemoryStatus$.MODULE$.equals(x1)) {
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$memoryStatus());
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.GetStorageStatus$.MODULE$.equals(x1)) {
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$storageStatus());
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetBlockStatus) {
               BlockManagerMessages.GetBlockStatus var33 = (BlockManagerMessages.GetBlockStatus)x1;
               BlockId blockId = var33.blockId();
               boolean askStorageEndpoints = var33.askStorageEndpoints();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockStatus(blockId, askStorageEndpoints));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetShufflePushMergerLocations) {
               BlockManagerMessages.GetShufflePushMergerLocations var36 = (BlockManagerMessages.GetShufflePushMergerLocations)x1;
               int numMergersNeeded = var36.numMergersNeeded();
               Set hostsToFilter = var36.hostsToFilter();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getShufflePushMergerLocations(numMergersNeeded, hostsToFilter));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveShufflePushMergerLocation) {
               BlockManagerMessages.RemoveShufflePushMergerLocation var39 = (BlockManagerMessages.RemoveShufflePushMergerLocation)x1;
               String host = var39.host();
               RpcCallContext var74 = this.context$1;
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeShufflePushMergerLocation(host);
               var74.reply(BoxedUnit.UNIT);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.IsExecutorAlive) {
               BlockManagerMessages.IsExecutorAlive var41 = (BlockManagerMessages.IsExecutorAlive)x1;
               String executorId = var41.executorId();
               this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().contains(executorId)));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetMatchingBlockIds) {
               BlockManagerMessages.GetMatchingBlockIds var43 = (BlockManagerMessages.GetMatchingBlockIds)x1;
               Function1 filter = var43.filter();
               boolean askStorageEndpoints = var43.askStorageEndpoints();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getMatchingBlockIds(filter, askStorageEndpoints));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveRdd) {
               BlockManagerMessages.RemoveRdd var46 = (BlockManagerMessages.RemoveRdd)x1;
               int rddId = var46.rddId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeRdd(rddId));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveShuffle) {
               BlockManagerMessages.RemoveShuffle var48 = (BlockManagerMessages.RemoveShuffle)x1;
               int shuffleId = var48.shuffleId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeShuffle(shuffleId));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveBroadcast) {
               BlockManagerMessages.RemoveBroadcast var50 = (BlockManagerMessages.RemoveBroadcast)x1;
               long broadcastId = var50.broadcastId();
               boolean removeFromDriver = var50.removeFromDriver();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeBroadcast(broadcastId, removeFromDriver));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveBlock) {
               BlockManagerMessages.RemoveBlock var54 = (BlockManagerMessages.RemoveBlock)x1;
               BlockId blockId = var54.blockId();
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeBlockFromWorkers(blockId);
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveExecutor) {
               BlockManagerMessages.RemoveExecutor var56 = (BlockManagerMessages.RemoveExecutor)x1;
               String execId = var56.execId();
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeExecutor(execId);
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.DecommissionBlockManagers) {
               BlockManagerMessages.DecommissionBlockManagers var58 = (BlockManagerMessages.DecommissionBlockManagers)x1;
               Seq executorIds = var58.executorIds();
               Seq bms = (Seq)executorIds.flatMap((key) -> this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().get(key));
               this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Mark BlockManagers (", ") as "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_IDS..MODULE$, bms.mkString(", "))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"being decommissioning."})))).log(scala.collection.immutable.Nil..MODULE$))));
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet().$plus$plus$eq(bms);
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetReplicateInfoForRDDBlocks) {
               BlockManagerMessages.GetReplicateInfoForRDDBlocks var61 = (BlockManagerMessages.GetReplicateInfoForRDDBlocks)x1;
               BlockManagerId blockManagerId = var61.blockManagerId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$getReplicateInfoForRDDBlocks(blockManagerId));
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.StopBlockManagerMaster$.MODULE$.equals(x1)) {
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               this.$outer.stop();
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.UpdateRDDBlockTaskInfo) {
               BlockManagerMessages.UpdateRDDBlockTaskInfo var63 = (BlockManagerMessages.UpdateRDDBlockTaskInfo)x1;
               RDDBlockId blockId = var63.blockId();
               long taskId = var63.taskId();
               RpcCallContext var73 = this.context$1;
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$updateRDDBlockTaskInfo(blockId, taskId);
               var73.reply(BoxedUnit.UNIT);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetRDDBlockVisibility) {
               BlockManagerMessages.GetRDDBlockVisibility var67 = (BlockManagerMessages.GetRDDBlockVisibility)x1;
               RDDBlockId blockId = var67.blockId();
               this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$isRDDBlockVisible(blockId)));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.UpdateRDDBlockVisibility) {
               BlockManagerMessages.UpdateRDDBlockVisibility var69 = (BlockManagerMessages.UpdateRDDBlockVisibility)x1;
               long taskId = var69.taskId();
               boolean visible = var69.visible();
               RpcCallContext var10000 = this.context$1;
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$updateRDDBlockVisibility(taskId, visible);
               var10000.reply(BoxedUnit.UNIT);
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof BlockManagerMessages.RegisterBlockManager) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.UpdateBlockInfo) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetLocations) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetLocationsAndStatus) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetLocationsMultipleBlockIds) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetPeers) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetExecutorEndpointRef) {
               return true;
            } else if (BlockManagerMessages.GetMemoryStatus$.MODULE$.equals(x1)) {
               return true;
            } else if (BlockManagerMessages.GetStorageStatus$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetBlockStatus) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetShufflePushMergerLocations) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveShufflePushMergerLocation) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.IsExecutorAlive) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetMatchingBlockIds) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveRdd) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveShuffle) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveBroadcast) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveBlock) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveExecutor) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.DecommissionBlockManagers) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetReplicateInfoForRDDBlocks) {
               return true;
            } else if (BlockManagerMessages.StopBlockManagerMaster$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.UpdateRDDBlockTaskInfo) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetRDDBlockVisibility) {
               return true;
            } else {
               return x1 instanceof BlockManagerMessages.UpdateRDDBlockVisibility;
            }
         }

         private final void handleResult$1(final boolean success, final BlockManagerMessages.UpdateBlockInfo x3$1) {
            if (success) {
               this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$listenerBus.post(new SparkListenerBlockUpdated(BlockUpdatedInfo$.MODULE$.apply(x3$1)));
            }

            this.context$1.reply(BoxesRunTime.boxToBoolean(success));
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$1(final Object $this, final BlockManagerMessages.UpdateBlockInfo x3$1, final boolean success) {
            $this.handleResult$1(success, x3$1);
         }

         public {
            if (BlockManagerMasterEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerMasterEndpoint.this;
               this.context$1 = context$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public boolean org$apache$spark$storage$BlockManagerMasterEndpoint$$isRDDBlockVisible(final RDDBlockId blockId) {
      if (!this.trackingCacheVisibility()) {
         return true;
      } else {
         return this.blockLocations().containsKey(blockId) && ((IterableOnceOps)this.blockLocations().get(blockId)).nonEmpty() && !this.invisibleRDDBlocks().contains(blockId);
      }
   }

   public void org$apache$spark$storage$BlockManagerMasterEndpoint$$updateRDDBlockVisibility(final long taskId, final boolean visible) {
      if (this.trackingCacheVisibility()) {
         if (visible) {
            this.tidToRddBlockIds().get(BoxesRunTime.boxToLong(taskId)).foreach((blockIds) -> {
               $anonfun$updateRDDBlockVisibility$1(this, blockIds);
               return BoxedUnit.UNIT;
            });
         }

         this.tidToRddBlockIds().remove(BoxesRunTime.boxToLong(taskId));
      }
   }

   public void org$apache$spark$storage$BlockManagerMasterEndpoint$$updateRDDBlockTaskInfo(final RDDBlockId blockId, final long taskId) {
      if (this.trackingCacheVisibility()) {
         ((HashSet)this.tidToRddBlockIds().getOrElseUpdate(BoxesRunTime.boxToLong(taskId), () -> new HashSet())).add(blockId);
      }
   }

   private PartialFunction handleBlockRemovalFailure(final String blockType, final String blockId, final BlockManagerId bmId, final Object defaultValue) {
      return new Serializable(blockType, blockId, bmId, defaultValue) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final BlockManagerMasterEndpoint $outer;
         private final String blockType$1;
         private final String blockId$1;
         private final BlockManagerId bmId$1;
         private final Object defaultValue$1;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 instanceof IOException var6) {
               if (!BoxesRunTime.unboxToBoolean(SparkContext$.MODULE$.getActive().map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$4(x$1))).getOrElse((JFunction0.mcZ.sp)() -> true))) {
                  this.$outer.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error trying to remove ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_TYPE..MODULE$, this.blockType$1)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, this.blockId$1)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from block manager ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, this.bmId$1)}))))), var6);
               }

               return this.defaultValue$1;
            } else if (x1 instanceof TimeoutException var7) {
               String executorId = this.bmId$1.executorId();

               boolean var10000;
               try {
                  var10000 = BoxesRunTime.unboxToBoolean(this.$outer.org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint().askSync(new CoarseGrainedClusterMessages.IsExecutorAlive(executorId), scala.reflect.ClassTag..MODULE$.Boolean()));
               } catch (Throwable var13) {
                  if (var13 == null || !scala.util.control.NonFatal..MODULE$.apply(var13)) {
                     throw var13;
                  }

                  this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot determine whether executor "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is alive or not."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))), var13);
                  var10000 = false;
               }

               boolean isAlive = var10000;
               if (!isAlive) {
                  this.$outer.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error trying to remove ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_TYPE..MODULE$, this.blockType$1)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, this.blockId$1)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The executor ", " may have been lost."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))), var7);
                  return this.defaultValue$1;
               } else {
                  throw var7;
               }
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            if (x1 instanceof IOException) {
               return true;
            } else {
               return x1 instanceof TimeoutException;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$4(final SparkContext x$1) {
            return x$1.isStopped();
         }

         public {
            if (BlockManagerMasterEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerMasterEndpoint.this;
               this.blockType$1 = blockType$1;
               this.blockId$1 = blockId$1;
               this.bmId$1 = bmId$1;
               this.defaultValue$1 = defaultValue$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Future org$apache$spark$storage$BlockManagerMasterEndpoint$$removeRdd(final int rddId) {
      BlockManagerMessages.RemoveRdd removeMsg = new BlockManagerMessages.RemoveRdd(rddId);
      Iterable blocks = (Iterable)((IterableOps)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.blockLocations()).asScala().keys().flatMap((x$2) -> x$2.asRDDId())).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$removeRdd$2(rddId, x$3)));
      HashMap blocksToDeleteByShuffleService = new HashMap();
      blocks.foreach((blockId) -> {
         $anonfun$removeRdd$3(this, blocksToDeleteByShuffleService, blockId);
         return BoxedUnit.UNIT;
      });
      Seq removeRddFromExecutorsFutures = ((IterableOnceOps)this.blockManagerInfo.values().map((bmInfo) -> bmInfo.storageEndpoint().ask(removeMsg, scala.reflect.ClassTag..MODULE$.Int()).recover(this.handleBlockRemovalFailure("RDD", Integer.toString(rddId), bmInfo.blockManagerId(), BoxesRunTime.boxToInteger(0)), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext()))).toSeq();
      Iterable removeRddBlockViaExtShuffleServiceFutures = this.externalShuffleServiceRddFetchEnabled() ? (Iterable)this.externalBlockStoreClient.map((shuffleClient) -> (scala.collection.mutable.Iterable)blocksToDeleteByShuffleService.map((x0$1) -> {
            if (x0$1 != null) {
               BlockManagerId bmId = (BlockManagerId)x0$1._1();
               HashSet blockIds = (HashSet)x0$1._2();
               return scala.concurrent.Future..MODULE$.apply((JFunction0.mcI.sp)() -> {
                  java.util.concurrent.Future numRemovedBlocks = shuffleClient.removeBlocks(bmId.host(), bmId.port(), bmId.executorId(), (String[])((IterableOnceOps)blockIds.map((x$7) -> x$7.toString())).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
                  return scala.Predef..MODULE$.Integer2int((Integer)numRemovedBlocks.get(this.defaultRpcTimeout().duration().toSeconds(), TimeUnit.SECONDS));
               }, this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
            } else {
               throw new MatchError(x0$1);
            }
         })).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty()) : (Iterable)scala.package..MODULE$.Seq().empty();
      return scala.concurrent.Future..MODULE$.sequence((IterableOnce)removeRddFromExecutorsFutures.$plus$plus(removeRddBlockViaExtShuffleServiceFutures), scala.collection.BuildFrom..MODULE$.buildFromIterableOps(), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
   }

   public Future org$apache$spark$storage$BlockManagerMasterEndpoint$$removeShuffle(final int shuffleId) {
      HashMap blocksToDeleteByShuffleService = new HashMap();
      if (this.externalShuffleServiceRemoveShuffleEnabled()) {
         this.mapOutputTracker.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).foreach((shuffleStatus) -> {
            $anonfun$removeShuffle$1(this, shuffleId, blocksToDeleteByShuffleService, shuffleStatus);
            return BoxedUnit.UNIT;
         });
      }

      Iterable removeShuffleFromShuffleServicesFutures = (Iterable)this.externalBlockStoreClient.map((shuffleClient) -> (scala.collection.mutable.Iterable)blocksToDeleteByShuffleService.map((x0$1) -> {
            if (x0$1 != null) {
               BlockManagerId bmId = (BlockManagerId)x0$1._1();
               HashSet blockIds = (HashSet)x0$1._2();
               return scala.concurrent.Future..MODULE$.apply((JFunction0.mcZ.sp)() -> {
                  java.util.concurrent.Future numRemovedBlocks = shuffleClient.removeBlocks(bmId.host(), bmId.port(), bmId.executorId(), (String[])((IterableOnceOps)blockIds.map((x$9) -> x$9.toString())).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
                  return BoxesRunTime.equals(numRemovedBlocks.get(this.defaultRpcTimeout().duration().toSeconds(), TimeUnit.SECONDS), BoxesRunTime.boxToInteger(blockIds.size()));
               }, this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
            } else {
               throw new MatchError(x0$1);
            }
         })).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      Seq removeShuffleMergeFromShuffleServicesFutures = (Seq)this.externalBlockStoreClient.map((shuffleClient) -> {
         Seq mergerLocations = Utils$.MODULE$.isPushBasedShuffleEnabled(this.conf, this.isDriver, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3()) ? this.mapOutputTracker.getShufflePushMergerLocations(shuffleId) : (Seq)scala.package..MODULE$.Seq().empty();
         return (Seq)mergerLocations.map((bmId) -> scala.concurrent.Future..MODULE$.apply((JFunction0.mcZ.sp)() -> shuffleClient.removeShuffleMerge(bmId.host(), bmId.port(), shuffleId, -1), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext()));
      }).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      BlockManagerMessages.RemoveShuffle removeMsg = new BlockManagerMessages.RemoveShuffle(shuffleId);
      Seq removeShuffleFromExecutorsFutures = ((IterableOnceOps)this.blockManagerInfo.values().map((bm) -> bm.storageEndpoint().ask(removeMsg, scala.reflect.ClassTag..MODULE$.Boolean()).recover(this.handleBlockRemovalFailure("shuffle", Integer.toString(shuffleId), bm.blockManagerId(), BoxesRunTime.boxToBoolean(false)), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext()))).toSeq();
      return scala.concurrent.Future..MODULE$.sequence((IterableOnce)((IterableOps)removeShuffleFromExecutorsFutures.$plus$plus(removeShuffleFromShuffleServicesFutures)).$plus$plus(removeShuffleMergeFromShuffleServicesFutures), scala.collection.BuildFrom..MODULE$.buildFromIterableOps(), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
   }

   public Future org$apache$spark$storage$BlockManagerMasterEndpoint$$removeBroadcast(final long broadcastId, final boolean removeFromDriver) {
      BlockManagerMessages.RemoveBroadcast removeMsg = new BlockManagerMessages.RemoveBroadcast(broadcastId, removeFromDriver);
      Iterable requiredBlockManagers = (Iterable)this.blockManagerInfo.values().filter((info) -> BoxesRunTime.boxToBoolean($anonfun$removeBroadcast$1(removeFromDriver, info)));
      Seq futures = ((IterableOnceOps)requiredBlockManagers.map((bm) -> bm.storageEndpoint().ask(removeMsg, scala.reflect.ClassTag..MODULE$.Int()).recover(this.handleBlockRemovalFailure("broadcast", Long.toString(broadcastId), bm.blockManagerId(), BoxesRunTime.boxToInteger(0)), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext()))).toSeq();
      return scala.concurrent.Future..MODULE$.sequence(futures, scala.collection.BuildFrom..MODULE$.buildFromIterableOps(), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
   }

   private void removeBlockManager(final BlockManagerId blockManagerId) {
      BlockManagerInfo info = (BlockManagerInfo)this.blockManagerInfo.apply(blockManagerId);
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().$minus$eq(blockManagerId.executorId());
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet().remove(blockManagerId);
      this.blockManagerInfo.remove(blockManagerId);

      for(BlockId blockId : info.blocks().keySet()) {
         HashSet locations = (HashSet)this.blockLocations().get(blockId);
         locations.$minus$eq(blockManagerId);
         if (locations.isEmpty()) {
            this.blockLocations().remove(blockId);
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No more replicas available for ", "!"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
         } else if (this.proactivelyReplicate() && (blockId.isRDD() || blockId instanceof TestBlockId)) {
            int maxReplicas = locations.size() + 1;
            int i = (new Random(blockId.hashCode())).nextInt(locations.size());
            Seq blockLocations = locations.toSeq();
            BlockManagerId candidateBMId = (BlockManagerId)blockLocations.apply(i);
            this.blockManagerInfo.get(candidateBMId).foreach((bm) -> {
               Seq remainingLocations = (Seq)locations.toSeq().filter((bmx) -> BoxesRunTime.boxToBoolean($anonfun$removeBlockManager$3(candidateBMId, bmx)));
               BlockManagerMessages.ReplicateBlock replicateMsg = new BlockManagerMessages.ReplicateBlock(blockId, remainingLocations, maxReplicas);
               return bm.storageEndpoint().ask(replicateMsg, scala.reflect.ClassTag..MODULE$.Boolean());
            });
         }
      }

      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$listenerBus.post(new SparkListenerBlockManagerRemoved(System.currentTimeMillis(), blockManagerId));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing block manager ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, blockManagerId)})))));
   }

   private void addMergerLocation(final BlockManagerId blockManagerId) {
      if (!blockManagerId.isDriver() && !this.shuffleMergerLocations().contains(blockManagerId.host())) {
         BlockManagerId shuffleServerId = BlockManagerId$.MODULE$.apply(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER(), blockManagerId.host(), this.externalShuffleServicePort(), BlockManagerId$.MODULE$.apply$default$4());
         if (this.shuffleMergerLocations().size() >= this.maxRetainedMergerLocations()) {
            this.shuffleMergerLocations().$minus$eq(this.shuffleMergerLocations().head()._1());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.shuffleMergerLocations().update(shuffleServerId.host(), shuffleServerId);
      }
   }

   public void org$apache$spark$storage$BlockManagerMasterEndpoint$$removeExecutor(final String execId) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Trying to remove executor ", " from BlockManagerMaster."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)})))));
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().get(execId).foreach((blockManagerId) -> {
         $anonfun$removeExecutor$2(this, blockManagerId);
         return BoxedUnit.UNIT;
      });
   }

   public Seq org$apache$spark$storage$BlockManagerMasterEndpoint$$getReplicateInfoForRDDBlocks(final BlockManagerId blockManagerId) {
      Seq var10000;
      try {
         BlockManagerInfo info = (BlockManagerInfo)this.blockManagerInfo.apply(blockManagerId);
         scala.collection.mutable.Set rddBlocks = (scala.collection.mutable.Set)scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(info.blocks().keySet()).asScala().filter((x$10) -> BoxesRunTime.boxToBoolean($anonfun$getReplicateInfoForRDDBlocks$1(x$10)));
         var10000 = ((IterableOnceOps)rddBlocks.map((blockId) -> {
            HashSet currentBlockLocations = (HashSet)this.blockLocations().get(blockId);
            int maxReplicas = currentBlockLocations.size() + 1;
            Seq remainingLocations = (Seq)currentBlockLocations.toSeq().filter((bm) -> BoxesRunTime.boxToBoolean($anonfun$getReplicateInfoForRDDBlocks$3(blockManagerId, bm)));
            BlockManagerMessages.ReplicateBlock replicateMsg = new BlockManagerMessages.ReplicateBlock(blockId, remainingLocations, maxReplicas);
            return replicateMsg;
         })).toSeq();
      } catch (NoSuchElementException var4) {
         var10000 = (Seq)scala.package..MODULE$.Seq().empty();
      }

      return var10000;
   }

   public void org$apache$spark$storage$BlockManagerMasterEndpoint$$removeBlockFromWorkers(final BlockId blockId) {
      HashSet locations = (HashSet)this.blockLocations().get(blockId);
      if (locations != null) {
         locations.foreach((blockManagerId) -> {
            $anonfun$removeBlockFromWorkers$1(this, blockId, blockManagerId);
            return BoxedUnit.UNIT;
         });
      }
   }

   public scala.collection.immutable.Map org$apache$spark$storage$BlockManagerMasterEndpoint$$memoryStatus() {
      return this.blockManagerInfo.map((x0$1) -> {
         if (x0$1 != null) {
            BlockManagerId blockManagerId = (BlockManagerId)x0$1._1();
            BlockManagerInfo info = (BlockManagerInfo)x0$1._2();
            return new Tuple2(blockManagerId, new Tuple2.mcJJ.sp(info.maxMem(), info.remainingMem()));
         } else {
            throw new MatchError(x0$1);
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public StorageStatus[] org$apache$spark$storage$BlockManagerMasterEndpoint$$storageStatus() {
      return (StorageStatus[])((IterableOnceOps)this.blockManagerInfo.map((x0$1) -> {
         if (x0$1 != null) {
            BlockManagerId blockManagerId = (BlockManagerId)x0$1._1();
            BlockManagerInfo info = (BlockManagerInfo)x0$1._2();
            return new StorageStatus(blockManagerId, info.maxMem(), new Some(BoxesRunTime.boxToLong(info.maxOnHeapMem())), new Some(BoxesRunTime.boxToLong(info.maxOffHeapMem())), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(info.blocks()).asScala());
         } else {
            throw new MatchError(x0$1);
         }
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(StorageStatus.class));
   }

   public scala.collection.immutable.Map org$apache$spark$storage$BlockManagerMasterEndpoint$$blockStatus(final BlockId blockId, final boolean askStorageEndpoints) {
      BlockManagerMessages.GetBlockStatus getBlockStatus = new BlockManagerMessages.GetBlockStatus(blockId, BlockManagerMessages.GetBlockStatus$.MODULE$.apply$default$2());
      return ((IterableOnceOps)this.blockManagerInfo.values().map((info) -> {
         Future blockStatusFuture = askStorageEndpoints ? info.storageEndpoint().ask(getBlockStatus, scala.reflect.ClassTag..MODULE$.apply(Option.class)) : scala.concurrent.Future..MODULE$.apply(() -> info.getStatus(blockId), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
         return new Tuple2(info.blockManagerId(), blockStatusFuture);
      })).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Future org$apache$spark$storage$BlockManagerMasterEndpoint$$getMatchingBlockIds(final Function1 filter, final boolean askStorageEndpoints) {
      BlockManagerMessages.GetMatchingBlockIds getMatchingBlockIds = new BlockManagerMessages.GetMatchingBlockIds(filter, BlockManagerMessages.GetMatchingBlockIds$.MODULE$.apply$default$2());
      return scala.concurrent.Future..MODULE$.sequence((IterableOnce)this.blockManagerInfo.values().map((info) -> {
         Future future = askStorageEndpoints ? info.storageEndpoint().ask(getMatchingBlockIds, scala.reflect.ClassTag..MODULE$.apply(Seq.class)) : scala.concurrent.Future..MODULE$.apply(() -> ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(info.blocks()).asScala().keys().filter(filter)).toSeq(), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
         return future;
      }), scala.collection.BuildFrom..MODULE$.buildFromIterableOps(), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext()).map((x$11) -> ((IterableOnceOps)x$11.flatten(scala.Predef..MODULE$.$conforms())).toSeq(), this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
   }

   private BlockManagerId externalShuffleServiceIdOnHost(final BlockManagerId blockManagerId) {
      return BlockManagerId$.MODULE$.apply(blockManagerId.executorId(), blockManagerId.host(), this.externalShuffleServicePort(), BlockManagerId$.MODULE$.apply$default$4());
   }

   public BlockManagerId org$apache$spark$storage$BlockManagerMasterEndpoint$$register(final BlockManagerId idWithoutTopologyInfo, final String[] localDirs, final long maxOnHeapMemSize, final long maxOffHeapMemSize, final RpcEndpointRef storageEndpoint, final boolean isReRegister) {
      LazyBoolean isExecutorAlive$lzy = new LazyBoolean();
      BlockManagerId id = BlockManagerId$.MODULE$.apply(idWithoutTopologyInfo.executorId(), idWithoutTopologyInfo.host(), idWithoutTopologyInfo.port(), this.topologyMapper().getTopologyForHost(idWithoutTopologyInfo.host()));
      long time = System.currentTimeMillis();
      this.executorIdToLocalDirs().put(id.executorId(), localDirs);
      if (!this.blockManagerInfo.contains(id) && (!isReRegister || this.isExecutorAlive$1(isExecutorAlive$lzy, id))) {
         Option var14 = this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().get(id.executorId());
         if (var14 instanceof Some) {
            Some var15 = (Some)var14;
            BlockManagerId oldId = (BlockManagerId)var15.value();
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got two different block manager registrations on same executor - "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" will replace old one ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OLD_BLOCK_MANAGER_ID..MODULE$, oldId)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with new one ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, id)}))))));
            this.org$apache$spark$storage$BlockManagerMasterEndpoint$$removeExecutor(id.executorId());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(var14)) {
               throw new MatchError(var14);
            }

            BoxedUnit var20 = BoxedUnit.UNIT;
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering block manager ", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, id.hostPort())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " RAM, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(maxOnHeapMemSize + maxOffHeapMemSize))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, id)}))))));
         this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().update(id.executorId(), id);
         Object var21;
         if (this.externalShuffleServiceRddFetchEnabled()) {
            BlockStatusPerBlockId externalShuffleServiceBlocks = (BlockStatusPerBlockId)this.blockStatusByShuffleService().getOrElseUpdate(this.externalShuffleServiceIdOnHost(id), () -> new BlockStatusPerBlockId());
            var21 = new Some(externalShuffleServiceBlocks);
         } else {
            var21 = scala.None..MODULE$;
         }

         Option externalShuffleServiceBlockStatus = (Option)var21;
         this.blockManagerInfo.update(id, new BlockManagerInfo(id, System.currentTimeMillis(), maxOnHeapMemSize, maxOffHeapMemSize, storageEndpoint, externalShuffleServiceBlockStatus));
         if (this.pushBasedShuffleEnabled()) {
            this.addMergerLocation(id);
         }

         this.org$apache$spark$storage$BlockManagerMasterEndpoint$$listenerBus.post(new SparkListenerBlockManagerAdded(time, id, maxOnHeapMemSize + maxOffHeapMemSize, new Some(BoxesRunTime.boxToLong(maxOnHeapMemSize)), new Some(BoxesRunTime.boxToLong(maxOffHeapMemSize))));
      }

      BlockManagerId var22;
      if (isReRegister && !this.isExecutorAlive$1(isExecutorAlive$lzy, id)) {
         scala.Predef..MODULE$.assert(!this.blockManagerInfo.contains(id), () -> "BlockManager re-registration shouldn't succeed when the executor is lost");
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"BlockManager (", ") re-registration is rejected since "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, id)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the executor (", ") has been lost"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, id.executorId())}))))));
         var22 = BlockManagerId$.MODULE$.apply(BlockManagerId$.MODULE$.INVALID_EXECUTOR_ID(), id.host(), id.port(), id.topologyInfo());
      } else {
         var22 = id;
      }

      BlockManagerId updatedId = var22;
      return updatedId;
   }

   public Future org$apache$spark$storage$BlockManagerMasterEndpoint$$updateShuffleBlockInfo(final BlockId blockId, final BlockManagerId blockManagerId) {
      if (blockId instanceof ShuffleIndexBlockId var5) {
         int shuffleId = var5.shuffleId();
         long mapId = var5.mapId();
         return scala.concurrent.Future..MODULE$.apply((JFunction0.mcZ.sp)() -> {
            this.logDebug((Function0)(() -> "Received shuffle index block update for " + shuffleId + " " + mapId + ", updating."));
            this.mapOutputTracker.updateMapOutput(shuffleId, mapId, blockManagerId);
            return true;
         }, this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext());
      } else {
         if (blockId instanceof ShuffleDataBlockId var9) {
            int shuffleId = var9.shuffleId();
            long mapId = var9.mapId();
            if (true && true && true) {
               this.logDebug((Function0)(() -> "Received shuffle data block update for " + shuffleId + " " + mapId + ", ignore."));
               return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(true));
            }
         }

         this.logDebug((Function0)(() -> "Unexpected shuffle block type " + blockId + "as " + blockId.getClass().getSimpleName()));
         return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
      }
   }

   public boolean org$apache$spark$storage$BlockManagerMasterEndpoint$$updateBlockInfo(final BlockManagerId blockManagerId, final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      this.logDebug((Function0)(() -> "Updating block info on master " + blockId + " for " + blockManagerId));
      if (!this.blockManagerInfo.contains(blockManagerId)) {
         return blockManagerId.isDriver() && !this.isLocal();
      } else if (blockId == null) {
         ((BlockManagerInfo)this.blockManagerInfo.apply(blockManagerId)).updateLastSeenMs();
         return true;
      } else {
         ((BlockManagerInfo)this.blockManagerInfo.apply(blockManagerId)).updateBlockInfo(blockId, storageLevel, memSize, diskSize);
         HashSet locations = null;
         if (this.blockLocations().containsKey(blockId)) {
            locations = (HashSet)this.blockLocations().get(blockId);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            locations = new HashSet();
            this.blockLocations().put(blockId, locations);
         }

         if (storageLevel.isValid()) {
            boolean firstBlock = locations.isEmpty();
            locations.add(blockManagerId);
            blockId.asRDDId().foreach((rddBlockId) -> {
               Tuple2.mcZZ.sp var5 = new Tuple2.mcZZ.sp(this.trackingCacheVisibility(), firstBlock);
               if (var5 != null) {
                  boolean var6 = ((Tuple2)var5)._1$mcZ$sp();
                  boolean var7 = ((Tuple2)var5)._2$mcZ$sp();
                  if (var6 && var7) {
                     return BoxesRunTime.boxToBoolean(this.invisibleRDDBlocks().add(rddBlockId));
                  }
               }

               if (var5 != null) {
                  boolean var8 = ((Tuple2)var5)._1$mcZ$sp();
                  boolean var9 = ((Tuple2)var5)._2$mcZ$sp();
                  if (var8 && !var9 && !this.invisibleRDDBlocks().contains(rddBlockId)) {
                     return ((BlockManagerInfo)this.blockManagerInfo.apply(blockManagerId)).storageEndpoint().ask(new BlockManagerMessages.MarkRDDBlockAsVisible(rddBlockId), scala.reflect.ClassTag..MODULE$.Unit());
                  }
               }

               return BoxedUnit.UNIT;
            });
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else {
            BoxesRunTime.boxToBoolean(locations.remove(blockManagerId));
         }

         if (blockId.isRDD() && storageLevel.useDisk() && this.externalShuffleServiceRddFetchEnabled()) {
            BlockManagerId externalShuffleServiceId = this.externalShuffleServiceIdOnHost(blockManagerId);
            BoxesRunTime.boxToBoolean(storageLevel.isValid() ? locations.add(externalShuffleServiceId) : locations.remove(externalShuffleServiceId));
         } else {
            BoxedUnit var13 = BoxedUnit.UNIT;
         }

         if (locations.isEmpty()) {
            this.blockLocations().remove(blockId);
         } else {
            BoxedUnit var14 = BoxedUnit.UNIT;
         }

         return true;
      }
   }

   public Seq org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocations(final BlockId blockId) {
      return this.blockLocations().containsKey(blockId) ? ((IterableOnceOps)this.blockLocations().get(blockId)).toSeq() : (Seq)scala.package..MODULE$.Seq().empty();
   }

   public Option org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocationsAndStatus(final BlockId blockId, final String requesterHost) {
      Seq allLocations = (Seq).MODULE$.apply(this.blockLocations().get(blockId)).map((x$12) -> x$12.toSeq()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      Option var10000;
      if (this.externalShuffleServiceRddFetchEnabled() && blockId.isRDD()) {
         Option hostLocalLocations = allLocations.find((bmId) -> BoxesRunTime.boxToBoolean($anonfun$getLocationsAndStatus$3(this, requesterHost, bmId)));
         Option location = hostLocalLocations.orElse(() -> allLocations.find((x$13) -> BoxesRunTime.boxToBoolean($anonfun$getLocationsAndStatus$5(this, x$13))));
         var10000 = location.flatMap((x$14) -> this.blockStatusByShuffleService().get(x$14).flatMap((x$15) -> x$15.get(blockId))).zip(location);
      } else {
         var10000 = ((IterableOnce)allLocations.filter((x$16) -> BoxesRunTime.boxToBoolean($anonfun$getLocationsAndStatus$8(requesterHost, x$16)))).iterator().flatMap((bmId) -> this.blockManagerInfo.get(bmId).flatMap((blockInfo) -> blockInfo.getStatus(blockId).map((x$17) -> new Tuple2(x$17, bmId)))).find((x$18) -> BoxesRunTime.boxToBoolean($anonfun$getLocationsAndStatus$12(x$18)));
      }

      Option blockStatusWithBlockManagerId = var10000.orElse(() -> {
         Option location = allLocations.headOption();
         return location.flatMap((x$19) -> this.blockManagerInfo.get(x$19)).flatMap((x$20) -> x$20.getStatus(blockId)).zip(location);
      });
      this.logDebug((Function0)(() -> "Identified block: " + blockStatusWithBlockManagerId));
      return blockStatusWithBlockManagerId.map((x0$1) -> {
         if (x0$1 != null) {
            BlockStatus blockStatus = (BlockStatus)x0$1._1();
            BlockManagerId bmId = (BlockManagerId)x0$1._2();
            if (blockStatus != null && bmId != null) {
               String var10000 = bmId.host();
               if (var10000 == null) {
                  if (requesterHost != null) {
                     return new BlockManagerMessages.BlockLocationsAndStatus(allLocations, blockStatus, scala.None..MODULE$);
                  }
               } else if (!var10000.equals(requesterHost)) {
                  return new BlockManagerMessages.BlockLocationsAndStatus(allLocations, blockStatus, scala.None..MODULE$);
               }

               if (blockStatus.storageLevel().useDisk()) {
                  return new BlockManagerMessages.BlockLocationsAndStatus(allLocations, blockStatus, .MODULE$.apply(this.executorIdToLocalDirs().getIfPresent(bmId.executorId())));
               }

               return new BlockManagerMessages.BlockLocationsAndStatus(allLocations, blockStatus, scala.None..MODULE$);
            }
         }

         throw new MatchError(x0$1);
      }).orElse(() -> scala.None..MODULE$);
   }

   public IndexedSeq org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocationsMultipleBlockIds(final BlockId[] blockIds) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(blockIds), (blockId) -> this.org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocations(blockId), scala.reflect.ClassTag..MODULE$.apply(Seq.class))).toImmutableArraySeq();
   }

   public Seq org$apache$spark$storage$BlockManagerMasterEndpoint$$getPeers(final BlockManagerId blockManagerId) {
      scala.collection.Set blockManagerIds = this.blockManagerInfo.keySet();
      return blockManagerIds.contains(blockManagerId) ? ((SetOps)((IterableOps)blockManagerIds.filterNot((x$21) -> BoxesRunTime.boxToBoolean($anonfun$getPeers$1(x$21)))).filterNot((x$22) -> BoxesRunTime.boxToBoolean($anonfun$getPeers$2(blockManagerId, x$22)))).diff(this.org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet()).toSeq() : (Seq)scala.package..MODULE$.Seq().empty();
   }

   public Seq org$apache$spark$storage$BlockManagerMasterEndpoint$$getShufflePushMergerLocations(final int numMergersNeeded, final Set hostsToFilter) {
      Set blockManagerHosts = ((IterableOnceOps)((MapOps)this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().filterNot((x$23) -> BoxesRunTime.boxToBoolean($anonfun$getShufflePushMergerLocations$1(x$23)))).values().map((x$24) -> x$24.host())).toSet();
      Set filteredBlockManagerHosts = (Set)blockManagerHosts.diff(hostsToFilter);
      Set filteredMergersWithExecutors = (Set)filteredBlockManagerHosts.map((x$25) -> BlockManagerId$.MODULE$.apply(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER(), x$25, this.externalShuffleServicePort(), BlockManagerId$.MODULE$.apply$default$4()));
      if (filteredMergersWithExecutors.size() >= numMergersNeeded) {
         return filteredMergersWithExecutors.toSeq();
      } else {
         Set filteredMergersWithExecutorsHosts = (Set)filteredMergersWithExecutors.map((x$26) -> x$26.host());
         Iterable filteredMergersWithoutExecutors = (Iterable)((IterableOps)this.shuffleMergerLocations().values().filterNot((x) -> BoxesRunTime.boxToBoolean($anonfun$getShufflePushMergerLocations$5(hostsToFilter, x)))).filterNot((x) -> BoxesRunTime.boxToBoolean($anonfun$getShufflePushMergerLocations$6(filteredMergersWithExecutorsHosts, x)));
         Iterable randomFilteredMergersLocations = filteredMergersWithoutExecutors.size() > numMergersNeeded - filteredMergersWithExecutors.size() ? (Iterable)Utils$.MODULE$.randomize(filteredMergersWithoutExecutors, scala.reflect.ClassTag..MODULE$.apply(BlockManagerId.class)).take(numMergersNeeded - filteredMergersWithExecutors.size()) : filteredMergersWithoutExecutors;
         return (Seq)filteredMergersWithExecutors.toSeq().$plus$plus(randomFilteredMergersLocations);
      }
   }

   public void org$apache$spark$storage$BlockManagerMasterEndpoint$$removeShufflePushMergerLocation(final String host) {
      if (this.shuffleMergerLocations().contains(host)) {
         this.shuffleMergerLocations().remove(host);
      }
   }

   public Option org$apache$spark$storage$BlockManagerMasterEndpoint$$getExecutorEndpointRef(final String executorId) {
      return this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().get(executorId).flatMap((blockManagerId) -> this.blockManagerInfo.get(blockManagerId).map((info) -> info.storageEndpoint()));
   }

   public void onStop() {
      this.askThreadPool().shutdownNow();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlockVisibility$2(final BlockManagerMasterEndpoint $this, final RDDBlockId blockId) {
      $this.invisibleRDDBlocks().remove(blockId);
      BlockManagerMessages.MarkRDDBlockAsVisible msg = new BlockManagerMessages.MarkRDDBlockAsVisible(blockId);
      ((IterableOnceOps)$this.org$apache$spark$storage$BlockManagerMasterEndpoint$$getLocations(blockId).flatMap((key) -> $this.blockManagerInfo.get(key))).foreach((managerInfo) -> managerInfo.storageEndpoint().ask(msg, scala.reflect.ClassTag..MODULE$.Unit()));
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlockVisibility$1(final BlockManagerMasterEndpoint $this, final HashSet blockIds) {
      blockIds.foreach((blockId) -> {
         $anonfun$updateRDDBlockVisibility$2($this, blockId);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeRdd$2(final int rddId$1, final RDDBlockId x$3) {
      return x$3.rddId() == rddId$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeRdd$4(final BlockManagerMasterEndpoint $this, final BlockManagerId x$4) {
      return x$4.port() == $this.externalShuffleServicePort();
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$8(final RDDBlockId blockId$2, final BlockStatusPerBlockId blockStatusForId) {
      blockStatusForId.remove(blockId$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$6(final BlockManagerMasterEndpoint $this, final Set liveExecutorsForBlock$1, final HashMap blocksToDeleteByShuffleService$1, final RDDBlockId blockId$2, final BlockManagerId bmIdForShuffleService) {
      if (!liveExecutorsForBlock$1.contains(bmIdForShuffleService.executorId())) {
         HashSet blockIdsToDel = (HashSet)blocksToDeleteByShuffleService$1.getOrElseUpdate(bmIdForShuffleService, () -> new HashSet());
         blockIdsToDel.$plus$eq(blockId$2);
         $this.blockStatusByShuffleService().get(bmIdForShuffleService).foreach((blockStatusForId) -> {
            $anonfun$removeRdd$8(blockId$2, blockStatusForId);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$10(final RDDBlockId blockId$2, final BlockManagerInfo bmInfo) {
      bmInfo.removeBlock(blockId$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$9(final BlockManagerMasterEndpoint $this, final RDDBlockId blockId$2, final BlockManagerId bmId) {
      $this.blockManagerInfo.get(bmId).foreach((bmInfo) -> {
         $anonfun$removeRdd$10(blockId$2, bmInfo);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$3(final BlockManagerMasterEndpoint $this, final HashMap blocksToDeleteByShuffleService$1, final RDDBlockId blockId) {
      HashSet bms = (HashSet)$this.blockLocations().remove(blockId);
      if ($this.trackingCacheVisibility()) {
         BoxesRunTime.boxToBoolean($this.invisibleRDDBlocks().remove(blockId));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      Tuple2 var6 = bms.partition((x$4) -> BoxesRunTime.boxToBoolean($anonfun$removeRdd$4($this, x$4)));
      if (var6 != null) {
         HashSet bmIdsExtShuffle = (HashSet)var6._1();
         HashSet bmIdsExecutor = (HashSet)var6._2();
         Tuple2 var5 = new Tuple2(bmIdsExtShuffle, bmIdsExecutor);
         HashSet bmIdsExtShuffle = (HashSet)var5._1();
         HashSet bmIdsExecutor = (HashSet)var5._2();
         Set liveExecutorsForBlock = ((IterableOnceOps)bmIdsExecutor.map((x$6) -> x$6.executorId())).toSet();
         bmIdsExtShuffle.foreach((bmIdForShuffleService) -> {
            $anonfun$removeRdd$6($this, liveExecutorsForBlock, blocksToDeleteByShuffleService$1, blockId, bmIdForShuffleService);
            return BoxedUnit.UNIT;
         });
         bmIdsExecutor.foreach((bmId) -> {
            $anonfun$removeRdd$9($this, blockId, bmId);
            return BoxedUnit.UNIT;
         });
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeShuffle$3(final MapStatus x$8) {
      return x$8 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$removeShuffle$2(final BlockManagerMasterEndpoint $this, final int shuffleId$1, final HashMap blocksToDeleteByShuffleService$2, final MapStatus[] mapStatuses) {
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(mapStatuses), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$removeShuffle$3(x$8)))), (mapStatus) -> {
         if (!$this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor().contains(mapStatus.location().executorId())) {
            Seq blocksToDel = $this.shuffleManager().shuffleBlockResolver().getBlocksForShuffle(shuffleId$1, mapStatus.mapId());
            if (blocksToDel.nonEmpty()) {
               HashSet blocks = (HashSet)blocksToDeleteByShuffleService$2.getOrElseUpdate(mapStatus.location(), () -> new HashSet());
               return blocks.$plus$plus$eq(blocksToDel);
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            return BoxedUnit.UNIT;
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$removeShuffle$1(final BlockManagerMasterEndpoint $this, final int shuffleId$1, final HashMap blocksToDeleteByShuffleService$2, final ShuffleStatus shuffleStatus) {
      shuffleStatus.withMapStatuses((mapStatuses) -> {
         $anonfun$removeShuffle$2($this, shuffleId$1, blocksToDeleteByShuffleService$2, mapStatuses);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeBroadcast$1(final boolean removeFromDriver$1, final BlockManagerInfo info) {
      return removeFromDriver$1 || !info.blockManagerId().isDriver();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeBlockManager$3(final BlockManagerId candidateBMId$1, final BlockManagerId bm) {
      boolean var10000;
      label23: {
         if (bm == null) {
            if (candidateBMId$1 != null) {
               break label23;
            }
         } else if (!bm.equals(candidateBMId$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutor$2(final BlockManagerMasterEndpoint $this, final BlockManagerId blockManagerId) {
      $this.removeBlockManager(blockManagerId);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getReplicateInfoForRDDBlocks$1(final BlockId x$10) {
      return x$10.isRDD();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getReplicateInfoForRDDBlocks$3(final BlockManagerId blockManagerId$2, final BlockManagerId bm) {
      boolean var10000;
      label23: {
         if (bm == null) {
            if (blockManagerId$2 != null) {
               break label23;
            }
         } else if (!bm.equals(blockManagerId$2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$removeBlockFromWorkers$1(final BlockManagerMasterEndpoint $this, final BlockId blockId$4, final BlockManagerId blockManagerId) {
      Option blockManager = $this.blockManagerInfo.get(blockManagerId);
      blockManager.foreach((bm) -> bm.storageEndpoint().ask(new BlockManagerMessages.RemoveBlock(blockId$4), scala.reflect.ClassTag..MODULE$.Boolean()).recover($this.handleBlockRemovalFailure("block", blockId$4.toString(), bm.blockManagerId(), BoxesRunTime.boxToBoolean(false)), $this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext()));
   }

   // $FF: synthetic method
   private final boolean isExecutorAlive$lzycompute$1(final LazyBoolean isExecutorAlive$lzy$1, final BlockManagerId id$1) {
      synchronized(isExecutorAlive$lzy$1){}

      boolean var4;
      try {
         var4 = isExecutorAlive$lzy$1.initialized() ? isExecutorAlive$lzy$1.value() : isExecutorAlive$lzy$1.initialize(BoxesRunTime.unboxToBoolean(this.org$apache$spark$storage$BlockManagerMasterEndpoint$$driverEndpoint().askSync(new CoarseGrainedClusterMessages.IsExecutorAlive(id$1.executorId()), scala.reflect.ClassTag..MODULE$.Boolean())));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private final boolean isExecutorAlive$1(final LazyBoolean isExecutorAlive$lzy$1, final BlockManagerId id$1) {
      return isExecutorAlive$lzy$1.initialized() ? isExecutorAlive$lzy$1.value() : this.isExecutorAlive$lzycompute$1(isExecutorAlive$lzy$1, id$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLocationsAndStatus$3(final BlockManagerMasterEndpoint $this, final String requesterHost$1, final BlockManagerId bmId) {
      boolean var4;
      label18: {
         String var10000 = bmId.host();
         if (var10000 == null) {
            if (requesterHost$1 != null) {
               break label18;
            }
         } else if (!var10000.equals(requesterHost$1)) {
            break label18;
         }

         if (bmId.port() == $this.externalShuffleServicePort()) {
            var4 = true;
            return var4;
         }
      }

      var4 = false;
      return var4;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLocationsAndStatus$5(final BlockManagerMasterEndpoint $this, final BlockManagerId x$13) {
      return x$13.port() == $this.externalShuffleServicePort();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLocationsAndStatus$8(final String requesterHost$1, final BlockManagerId x$16) {
      boolean var3;
      label23: {
         String var10000 = x$16.host();
         if (var10000 == null) {
            if (requesterHost$1 == null) {
               break label23;
            }
         } else if (var10000.equals(requesterHost$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLocationsAndStatus$12(final Tuple2 x$18) {
      return ((BlockStatus)x$18._1()).storageLevel().useDisk();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPeers$1(final BlockManagerId x$21) {
      return x$21.isDriver();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPeers$2(final BlockManagerId blockManagerId$5, final BlockManagerId x$22) {
      boolean var10000;
      label23: {
         if (x$22 == null) {
            if (blockManagerId$5 == null) {
               break label23;
            }
         } else if (x$22.equals(blockManagerId$5)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getShufflePushMergerLocations$1(final Tuple2 x$23) {
      return ((BlockManagerId)x$23._2()).isDriver();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getShufflePushMergerLocations$5(final Set hostsToFilter$1, final BlockManagerId x) {
      return hostsToFilter$1.contains(x.host());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getShufflePushMergerLocations$6(final Set filteredMergersWithExecutorsHosts$1, final BlockManagerId x) {
      return filteredMergersWithExecutorsHosts$1.contains(x.host());
   }

   public BlockManagerMasterEndpoint(final RpcEnv rpcEnv, final boolean isLocal, final SparkConf conf, final LiveListenerBus listenerBus, final Option externalBlockStoreClient, final Map blockManagerInfo, final MapOutputTrackerMaster mapOutputTracker, final ShuffleManager _shuffleManager, final boolean isDriver) {
      this.rpcEnv = rpcEnv;
      this.isLocal = isLocal;
      this.conf = conf;
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$listenerBus = listenerBus;
      this.externalBlockStoreClient = externalBlockStoreClient;
      this.blockManagerInfo = blockManagerInfo;
      this.mapOutputTracker = mapOutputTracker;
      this._shuffleManager = _shuffleManager;
      this.isDriver = isDriver;
      super();
      RpcEndpoint.$init$(this);
      IsolatedThreadSafeRpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.executorIdToLocalDirs = CacheBuilder.newBuilder().maximumSize((long)BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.STORAGE_LOCAL_DISK_BY_EXECUTORS_CACHE_SIZE()))).build();
      this.blockStatusByShuffleService = new HashMap();
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$blockManagerIdByExecutor = new HashMap();
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$decommissioningBlockManagerSet = new HashSet();
      this.blockLocations = new java.util.HashMap();
      this.tidToRddBlockIds = new HashMap();
      this.invisibleRDDBlocks = new HashSet();
      this.shuffleMergerLocations = new LinkedHashMap();
      this.maxRetainedMergerLocations = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.SHUFFLE_MERGER_MAX_RETAINED_LOCATIONS()));
      this.askThreadPool = ThreadUtils$.MODULE$.newDaemonCachedThreadPool("block-manager-ask-thread-pool", 100, ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3());
      this.org$apache$spark$storage$BlockManagerMasterEndpoint$$askExecutionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(this.askThreadPool());
      String topologyMapperClassName = (String)conf.get(package$.MODULE$.STORAGE_REPLICATION_TOPOLOGY_MAPPER());
      Class clazz = Utils$.MODULE$.classForName(topologyMapperClassName, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      TopologyMapper mapper = (TopologyMapper)clazz.getConstructor(SparkConf.class).newInstance(conf);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using ", " for getting topology information"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, topologyMapperClassName)})))));
      this.topologyMapper = mapper;
      this.proactivelyReplicate = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_REPLICATION_PROACTIVE()));
      this.defaultRpcTimeout = RpcUtils$.MODULE$.askRpcTimeout(conf);
      this.pushBasedShuffleEnabled = Utils$.MODULE$.isPushBasedShuffleEnabled(conf, isDriver, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3());
      this.logInfo((Function0)(() -> "BlockManagerMasterEndpoint up"));
      this.externalShuffleServiceRemoveShuffleEnabled = externalBlockStoreClient.isDefined() && BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_REMOVE_SHUFFLE_ENABLED()));
      this.externalShuffleServiceRddFetchEnabled = externalBlockStoreClient.isDefined() && BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_FETCH_RDD_ENABLED()));
      this.externalShuffleServicePort = StorageUtils$.MODULE$.externalShuffleServicePort(conf);
      this.trackingCacheVisibility = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.RDD_CACHE_VISIBILITY_TRACKING_ENABLED()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
