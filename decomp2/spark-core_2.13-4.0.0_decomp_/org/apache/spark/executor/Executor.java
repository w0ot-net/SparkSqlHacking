package org.apache.spark.executor;

import java.io.File;
import java.io.NotSerializableException;
import java.lang.invoke.SerializedLambda;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.ExceptionFailure;
import org.apache.spark.Heartbeat;
import org.apache.spark.HeartbeatReceiver$;
import org.apache.spark.HeartbeatResponse;
import org.apache.spark.Heartbeater;
import org.apache.spark.JobArtifactState;
import org.apache.spark.MapOutputTrackerWorker;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkFiles$;
import org.apache.spark.TaskCommitDenied;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.TaskKilled;
import org.apache.spark.TaskKilledException;
import org.apache.spark.TaskState$;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.plugin.PluginContainer;
import org.apache.spark.internal.plugin.PluginContainer$;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.metrics.source.JVMCPUSource;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.scheduler.DirectTaskResult;
import org.apache.spark.scheduler.IndirectTaskResult;
import org.apache.spark.scheduler.Task;
import org.apache.spark.scheduler.TaskDescription;
import org.apache.spark.serializer.SerializerHelper$;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.shuffle.FetchFailedException;
import org.apache.spark.shuffle.ShuffleBlockPusher$;
import org.apache.spark.status.api.v1.ThreadStackTrace;
import org.apache.spark.storage.TaskResultBlockId;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CausedBy$;
import org.apache.spark.util.ChildFirstURLClassLoader;
import org.apache.spark.util.MutableURLClassLoader;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.StubClassLoader;
import org.apache.spark.util.StubClassLoader$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.UninterruptibleThread;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.RemovalListener;
import org.sparkproject.guava.cache.RemovalNotification;
import org.sparkproject.guava.util.concurrent.ThreadFactoryBuilder;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.None.;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00195e!CA3\u0003O\u0002\u00111NA<\u0011)\t\t\n\u0001B\u0001B\u0003%\u0011Q\u0013\u0005\u000b\u0003W\u0003!\u0011!Q\u0001\n\u0005U\u0005BCAW\u0001\t\u0005\t\u0015!\u0003\u00020\"Q\u0011q\u0017\u0001\u0003\u0002\u0003\u0006I!!/\t\u0015\u0005m\u0007A!A!\u0002\u0013\ti\u000e\u0003\u0006\u0002d\u0002\u0011\t\u0011)A\u0005\u0003KD!\"a@\u0001\u0005\u0003\u0005\u000b\u0011\u0002B\u0001\u0011\u001d\u0011i\u0002\u0001C\u0001\u0005?A\u0011Ba\r\u0001\u0005\u0004%IA!\u000e\t\u0011\t-\u0003\u0001)A\u0005\u0005oA\u0011B!\u0014\u0001\u0005\u0004%\tAa\u0014\t\u0011\tE\u0003\u0001)A\u0005\u0003sB\u0011Ba\u0015\u0001\u0005\u0004%IA!\u0016\t\u0011\t\r\u0004\u0001)A\u0005\u0005/B1B!\u001a\u0001\u0005\u0004%\t!a\u001a\u0003h!A!q\u000e\u0001!\u0002\u0013\u0011I\u0007C\u0006\u0003r\u0001\u0011\r\u0011\"\u0001\u0002h\tM\u0004\u0002\u0003B;\u0001\u0001\u0006I!!&\t\u0013\t]\u0004A1A\u0005\n\te\u0004\u0002\u0003BD\u0001\u0001\u0006IAa\u001f\t\u0017\t%\u0005A1A\u0005\u0002\u0005\u001d$1\u0012\u0005\t\u0005+\u0003\u0001\u0015!\u0003\u0003\u000e\"I!q\u0013\u0001C\u0002\u0013%!\u0011\u0014\u0005\t\u0005O\u0003\u0001\u0015!\u0003\u0003\u001c\"I!\u0011\u0016\u0001C\u0002\u0013%!1\u0016\u0005\t\u0005g\u0003\u0001\u0015!\u0003\u0003.\"I!Q\u0017\u0001C\u0002\u0013%!1\u0012\u0005\t\u0005o\u0003\u0001\u0015!\u0003\u0003\u000e\"I!\u0011\u0018\u0001C\u0002\u0013%!1\u0018\u0005\t\t\u0017\u0002\u0001\u0015!\u0003\u0003>\"IAQ\n\u0001C\u0002\u0013\u0005Aq\n\u0005\t\t3\u0002\u0001\u0015!\u0003\u0005R!IA1\f\u0001C\u0002\u0013%11\u000f\u0005\t\t;\u0002\u0001\u0015!\u0003\u0002^\"IAq\f\u0001C\u0002\u0013%11\u000f\u0005\t\tC\u0002\u0001\u0015!\u0003\u0002^\"IA1\r\u0001C\u0002\u0013%AQ\r\u0005\t\t[\u0002\u0001\u0015!\u0003\u0005h!IAq\u000e\u0001C\u0002\u0013%A\u0011\u000f\u0005\t\ts\u0002\u0001\u0015!\u0003\u0005t!9A1\u0010\u0001\u0005\n\u0011u\u0004b\u0002CH\u0001\u0011%A\u0011\u0013\u0005\b\t/\u0003A\u0011\u0002CM\u0011%!i\n\u0001b\u0001\n\u0003!y\n\u0003\u0005\u0005\"\u0002\u0001\u000b\u0011\u0002C@\u0011%!\u0019\u000b\u0001b\u0001\n\u0003!)\u000b\u0003\u0005\u0005@\u0002\u0001\u000b\u0011\u0002CT\u0011%!\t\r\u0001b\u0001\n\u0013\u00199\u0003\u0003\u0005\u0005D\u0002\u0001\u000b\u0011\u0002Be\u0011%!)\r\u0001b\u0001\n\u0013\u00199\u0003\u0003\u0005\u0005H\u0002\u0001\u000b\u0011\u0002Be\u0011-!I\r\u0001b\u0001\n\u0003\t9\u0007b3\t\u0011\u0011M\u0007\u0001)A\u0005\t\u001bD\u0011\u0002\"6\u0001\u0005\u0004%Iaa\n\t\u0011\u0011]\u0007\u0001)A\u0005\u0005\u0013D1\u0002\"7\u0001\u0005\u0004%\t!a\u001a\u0005\\\"AAQ\u001d\u0001!\u0002\u0013!i\u000eC\u0005\u0005h\u0002\u0011\r\u0011\"\u0003\u0005j\"AA\u0011\u001f\u0001!\u0002\u0013!Y\u000fC\u0005\u0005t\u0002\u0011\r\u0011\"\u0003\u0005v\"AAQ \u0001!\u0002\u0013!9\u0010C\u0005\u0005\u0000\u0002\u0011\r\u0011\"\u0003\u0005f!AQ\u0011\u0001\u0001!\u0002\u0013!9\u0007C\u0005\u0006\u0004\u0001\u0011\r\u0011\"\u0003\u0004t!AQQ\u0001\u0001!\u0002\u0013\ti\u000eC\u0005\u0006\b\u0001\u0011\r\u0011\"\u0003\u0004(!AQ\u0011\u0002\u0001!\u0002\u0013\u0011I\rC\u0005\u0006\f\u0001\u0011\r\u0011\"\u0003\u0004(!AQQ\u0002\u0001!\u0002\u0013\u0011I\rC\u0005\u0006\u0010\u0001\u0011\r\u0011\"\u0003\u0004t!AQ\u0011\u0003\u0001!\u0002\u0013\ti\u000eC\u0006\u0006\u0014\u0001\u0011\r\u0011\"\u0001\u0002h\u0015U\u0001\u0002CC\u000f\u0001\u0001\u0006I!b\u0006\t\u0013\u0015}\u0001A1A\u0005\n\u0015\u0005\u0002\u0002CC\u0015\u0001\u0001\u0006I!b\t\t\u0013\u0015-\u0002A1A\u0005\n\u00155\u0002\u0002CC\u001e\u0001\u0001\u0006I!b\f\t\u0013\u0015u\u0002\u00011A\u0005\n\u0011\u0015\u0004\"CC \u0001\u0001\u0007I\u0011BC!\u0011!))\u0005\u0001Q!\n\u0011\u001d\u0004\"CC$\u0001\u0001\u0007I\u0011BB:\u0011%)I\u0005\u0001a\u0001\n\u0013)Y\u0005\u0003\u0005\u0006P\u0001\u0001\u000b\u0015BAo\u0011%)\t\u0006\u0001b\u0001\n\u0013\u00199\u0003\u0003\u0005\u0006T\u0001\u0001\u000b\u0011\u0002Be\u00111))\u0006\u0001I\u0001\u0002\u0007\u0005\u000b\u0011BC,\u0011%)Y\u0006\u0001b\u0001\n\u0013)i\u0006\u0003\u0005\u0006`\u0001\u0001\u000b\u0011BC-\u0011%)\t\u0007\u0001b\u0001\n\u0013)i\u0006\u0003\u0005\u0006d\u0001\u0001\u000b\u0011BC-\u0011%))\u0007\u0001b\u0001\n\u0013)i\u0006\u0003\u0005\u0006h\u0001\u0001\u000b\u0011BC-\u0011%\u0019)\u0001\u0001b\u0001\n\u0013\u00199\u0001\u0003\u0005\u0004\u001c\u0001\u0001\u000b\u0011BB\u0005\u0011%)I\u0007\u0001C\u0001\u0003O\")\u0007C\u0005\u0006l\u0001!\t!a\u001b\u0004N\"IQQ\u000e\u0001\u0005\u0002\u0005\u001dTq\u000e\u0005\b\u000bo\u0002A\u0011AC=\u0011\u001d)y\b\u0001C\u0001\u000b\u0003Cq!\"#\u0001\t\u0003)Y\tC\u0004\u0006\u0012\u0002!\ta!4\t\u000f\u0015M\u0005\u0001\"\u0003\u0006\u0016\u001a1!q\u001d\u0001\u0001\u0005SD!Ba;h\u0005\u0003\u0005\u000b\u0011\u0002Bw\u0011)\u0011\u0019p\u001aBC\u0002\u0013\u0005!Q\u001f\u0005\u000b\u0007\u00079'\u0011!Q\u0001\n\t]\bBCB\u0003O\n\u0015\r\u0011\"\u0003\u0004\b!Q11D4\u0003\u0002\u0003\u0006Ia!\u0003\t\u000f\tuq\r\"\u0001\u0004\u001e!I1QE4C\u0002\u0013\u00051q\u0005\u0005\t\u0007S9\u0007\u0015!\u0003\u0003J\"I11F4C\u0002\u0013\u0005!1\u000f\u0005\t\u0007[9\u0007\u0015!\u0003\u0002\u0016\"I1qF4C\u0002\u0013\u00051\u0011\u0007\u0005\t\u0007g9\u0007\u0015!\u0003\u0003\"\"I1QG4C\u0002\u0013\u00051q\u0007\u0005\t\u0007\u0007:\u0007\u0015!\u0003\u0004:!I1QI4A\u0002\u0013%1q\t\u0005\n\u0007\u0017:\u0007\u0019!C\u0005\u0007\u001bB\u0001b!\u0017hA\u0003&1\u0011\n\u0005\n\u0007G:\u0007\u0019!C\u0005\u0007OA\u0011b!\u001ah\u0001\u0004%Iaa\u001a\t\u0011\r-t\r)Q\u0005\u0005\u0013Dqaa\u001ch\t\u0003\u00199\u0003C\u0005\u0004r\u001d\u0004\r\u0011\"\u0003\u0004t!I1QO4A\u0002\u0013%1q\u000f\u0005\t\u0007w:\u0007\u0015)\u0003\u0002^\"91qS4\u0005\u0002\rM\u0004bCBMO\u0002\u0007\t\u0019!C\u0001\u0007OA1ba'h\u0001\u0004\u0005\r\u0011\"\u0001\u0004\u001e\"Y1\u0011U4A\u0002\u0003\u0005\u000b\u0015\u0002Be\u0011-\u0019)k\u001aa\u0001\u0002\u0004%\taa*\t\u0017\rUv\r1AA\u0002\u0013\u00051q\u0017\u0005\f\u0007w;\u0007\u0019!A!B\u0013\u0019I\u000bC\u0004\u0004@\u001e$\ta!1\t\u000f\r-w\r\"\u0003\u0004N\"91qZ4\u0005\n\rE\u0007b\u0002C\u0006O\u0012\u00053Q\u001a\u0005\b\t\u001b9G\u0011\u0002C\b\u0011\u001d!ib\u001aC\u0005\u0007gB\u0011\u0002b\bh\t\u0003\t9\u0007\"\t\t\u000f\u0015]\u0005\u0001\"\u0003\u0006\u001a\"9Q1\u0015\u0001\u0005\n\u0015\u0015fA\u0002Bj\u0001\u0011\u0011)\u000eC\u0006\u0003d\u0006\u0005\"\u0011!Q\u0001\n\t\u0015\bbCBc\u0003C\u0011)\u0019!C\u0001\u0007gB1\u0002\"\u000f\u0002\"\t\u0005\t\u0015!\u0003\u0002^\"Y1\u0011ZA\u0011\u0005\u000b\u0007I\u0011\u0001B:\u0011-!Y$!\t\u0003\u0002\u0003\u0006I!!&\t\u0011\tu\u0011\u0011\u0005C\u0001\t{A\u0011b!\n\u0002\"\u0001\u0006IA!3\t\u0013\u0011\u0015\u0013\u0011\u0005Q\u0001\n\t%\u0007\"\u0003C$\u0003C\u0001\u000b\u0011\u0002Be\u0011%!I%!\t!\u0002\u0013\ti\u000e\u0003\u0005\u0005\f\u0005\u0005B\u0011IBg\u0011\u001d)Y\u000b\u0001C\u0005\u000b[Cq!b+\u0001\t\u0013)\u0019\rC\u0004\u0006,\u0002!I!b4\t\u000f\u0015U\u0007\u0001\"\u0003\u0006X\"9Q1\u001d\u0001\u0005\n\u0015\u0015\b\"CCz\u0001\u0011\u0005\u0011qMC{\u0011-1I\u0002AI\u0001\n\u0003\t9Gb\u0007\t\u0017\u0019=\u0002!%A\u0005\u0002\u0005\u001dd1\u0004\u0005\b\rc\u0001A\u0011BBg\u0011\u001d1\u0019\u0004\u0001C\u0001\rk9!B\"\u000f\u0002h!\u0005\u00111\u000eD\u001e\r)\t)'a\u001a\t\u0002\u0005-dQ\b\u0005\t\u0005;\ty\u0005\"\u0001\u0007@!Qa\u0011IA(\u0005\u0004%\tAb\u0011\t\u0013\u0019M\u0013q\nQ\u0001\n\u0019\u0015\u0003B\u0003D+\u0003\u001f\u0002\r\u0011\"\u0001\u0003,\"QaqKA(\u0001\u0004%\tA\"\u0017\t\u0013\u0019u\u0013q\nQ!\n\t5\u0006\u0002\u0003D0\u0003\u001f\"\tA\"\u0019\t\u0015\u0019m\u0014qJI\u0001\n\u00031i\b\u0003\u0006\u0007\u0002\u0006=\u0013\u0013!C\u0001\r\u0007C!Bb\"\u0002PE\u0005I\u0011\u0001DE\u0005!)\u00050Z2vi>\u0014(\u0002BA5\u0003W\n\u0001\"\u001a=fGV$xN\u001d\u0006\u0005\u0003[\ny'A\u0003ta\u0006\u00148N\u0003\u0003\u0002r\u0005M\u0014AB1qC\u000eDWM\u0003\u0002\u0002v\u0005\u0019qN]4\u0014\u000b\u0001\tI(!\"\u0011\t\u0005m\u0014\u0011Q\u0007\u0003\u0003{R!!a \u0002\u000bM\u001c\u0017\r\\1\n\t\u0005\r\u0015Q\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005\u001d\u0015QR\u0007\u0003\u0003\u0013SA!a#\u0002l\u0005A\u0011N\u001c;fe:\fG.\u0003\u0003\u0002\u0010\u0006%%a\u0002'pO\u001eLgnZ\u0001\u000bKb,7-\u001e;pe&#7\u0001\u0001\t\u0005\u0003/\u000b)K\u0004\u0003\u0002\u001a\u0006\u0005\u0006\u0003BAN\u0003{j!!!(\u000b\t\u0005}\u00151S\u0001\u0007yI|w\u000e\u001e \n\t\u0005\r\u0016QP\u0001\u0007!J,G-\u001a4\n\t\u0005\u001d\u0016\u0011\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\t\u0005\r\u0016QP\u0001\u0011Kb,7-\u001e;pe\"{7\u000f\u001e8b[\u0016\f1!\u001a8w!\u0011\t\t,a-\u000e\u0005\u0005-\u0014\u0002BA[\u0003W\u0012\u0001b\u00159be.,eN^\u0001\u000ekN,'o\u00117bgN\u0004\u0016\r\u001e5\u0011\r\u0005m\u0016QYAf\u001d\u0011\ti,!1\u000f\t\u0005m\u0015qX\u0005\u0003\u0003\u007fJA!a1\u0002~\u00059\u0001/Y2lC\u001e,\u0017\u0002BAd\u0003\u0013\u00141aU3r\u0015\u0011\t\u0019-! \u0011\t\u00055\u0017q[\u0007\u0003\u0003\u001fTA!!5\u0002T\u0006\u0019a.\u001a;\u000b\u0005\u0005U\u0017\u0001\u00026bm\u0006LA!!7\u0002P\n\u0019QK\u0015'\u0002\u000f%\u001cHj\\2bYB!\u00111PAp\u0013\u0011\t\t/! \u0003\u000f\t{w\u000e\\3b]\u0006ARO\\2bk\u001eDG/\u0012=dKB$\u0018n\u001c8IC:$G.\u001a:\u0011\t\u0005\u001d\u0018\u0011 \b\u0005\u0003S\f\u0019P\u0004\u0003\u0002l\u0006=h\u0002BAN\u0003[L!!!6\n\t\u0005E\u00181[\u0001\u0005Y\u0006tw-\u0003\u0003\u0002v\u0006]\u0018A\u0002+ie\u0016\fGM\u0003\u0003\u0002r\u0006M\u0017\u0002BA~\u0003{\u0014\u0001$\u00168dCV<\u0007\u000e^#yG\u0016\u0004H/[8o\u0011\u0006tG\r\\3s\u0015\u0011\t)0a>\u0002\u0013I,7o\\;sG\u0016\u001c\b\u0003\u0003B\u0002\u0005\u001b\t)J!\u0005\u000e\u0005\t\u0015!\u0002\u0002B\u0004\u0005\u0013\t\u0011\"[7nkR\f'\r\\3\u000b\t\t-\u0011QP\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\b\u0005\u000b\u00111!T1q!\u0011\u0011\u0019B!\u0007\u000e\u0005\tU!\u0002\u0002B\f\u0003W\n\u0001B]3t_V\u00148-Z\u0005\u0005\u00057\u0011)BA\nSKN|WO]2f\u0013:4wN]7bi&|g.\u0001\u0004=S:LGO\u0010\u000b\u0011\u0005C\u0011)Ca\n\u0003*\t-\"Q\u0006B\u0018\u0005c\u00012Aa\t\u0001\u001b\t\t9\u0007C\u0004\u0002\u0012\"\u0001\r!!&\t\u000f\u0005-\u0006\u00021\u0001\u0002\u0016\"9\u0011Q\u0016\u0005A\u0002\u0005=\u0006\"CA\\\u0011A\u0005\t\u0019AA]\u0011%\tY\u000e\u0003I\u0001\u0002\u0004\ti\u000eC\u0005\u0002d\"\u0001\n\u00111\u0001\u0002f\"9\u0011q \u0005A\u0002\t\u0005\u0011\u0001E3yK\u000e,Ho\u001c:TQV$Hm\\<o+\t\u00119\u0004\u0005\u0003\u0003:\t\u001dSB\u0001B\u001e\u0015\u0011\u0011iDa\u0010\u0002\r\u0005$x.\\5d\u0015\u0011\u0011\tEa\u0011\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0003\u0003F\u0005M\u0017\u0001B;uS2LAA!\u0013\u0003<\ti\u0011\t^8nS\u000e\u0014un\u001c7fC:\f\u0011#\u001a=fGV$xN]*ikR$wn\u001e8!\u0003E\u0019Ho\u001c9I_>\\'+\u001a4fe\u0016t7-Z\u000b\u0003\u0003s\n!c\u001d;pa\"{wn\u001b*fM\u0016\u0014XM\\2fA\u0005\tR)\u0014)U3~\u0013\u0015\fV#`\u0005V3e)\u0012*\u0016\u0005\t]\u0003\u0003\u0002B-\u0005?j!Aa\u0017\u000b\t\tu\u00131[\u0001\u0004]&|\u0017\u0002\u0002B1\u00057\u0012!BQ=uK\n+hMZ3s\u0003I)U\n\u0015+Z?\nKF+R0C+\u001a3UI\u0015\u0011\u0002\t\r|gNZ\u000b\u0003\u0005S\u0002B!!-\u0003l%!!QNA6\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0003d_:4\u0007%\u0001\buCN\\g*Y7f\u001b\u0012\u001b5*Z=\u0016\u0005\u0005U\u0015a\u0004;bg.t\u0015-\\3N\t\u000e[U-\u001f\u0011\u0002-U\u0004H-\u0019;f\t\u0016\u0004XM\u001c3f]\u000eLWm\u001d'pG.,\"Aa\u001f\u0011\t\tu$1Q\u0007\u0003\u0005\u007fRAA!!\u0003@\u0005)An\\2lg&!!Q\u0011B@\u00055\u0011V-\u001a8ue\u0006tG\u000fT8dW\u00069R\u000f\u001d3bi\u0016$U\r]3oI\u0016t7-[3t\u0019>\u001c7\u000eI\u0001\u000bi\"\u0014X-\u00193Q_>dWC\u0001BG!\u0011\u0011yI!%\u000e\u0005\t}\u0012\u0002\u0002BJ\u0005\u007f\u0011!\u0003\u00165sK\u0006$\u0007k\\8m\u000bb,7-\u001e;pe\u0006YA\u000f\u001b:fC\u0012\u0004vn\u001c7!\u0003\u001d\u00198\r[3nKN,\"Aa'\u0011\r\u0005m$Q\u0014BQ\u0013\u0011\u0011y*! \u0003\u000b\u0005\u0013(/Y=\u0011\t\t\r&QU\u0007\u0003\u0003oLA!a*\u0002x\u0006A1o\u00195f[\u0016\u001c\b%\u0001\bfq\u0016\u001cW\u000f^8s'>,(oY3\u0016\u0005\t5\u0006\u0003\u0002B\u0012\u0005_KAA!-\u0002h\tqQ\t_3dkR|'oU8ve\u000e,\u0017aD3yK\u000e,Ho\u001c:T_V\u00148-\u001a\u0011\u0002\u001dQ\f7o\u001b*fCB,'\u000fU8pY\u0006yA/Y:l%\u0016\f\u0007/\u001a:Q_>d\u0007%A\tuCN\\'+Z1qKJ4uN\u001d+bg.,\"A!0\u0011\u0011\t}&Q\u0019Be\u0005\u001fl!A!1\u000b\t\t\r'\u0011B\u0001\b[V$\u0018M\u00197f\u0013\u0011\u00119M!1\u0003\u000f!\u000b7\u000f['baB!\u00111\u0010Bf\u0013\u0011\u0011i-! \u0003\t1{gn\u001a\t\u0005\u0005#\f\t#D\u0001\u0001\u0005)!\u0016m]6SK\u0006\u0004XM]\n\u0007\u0003C\u00119N!8\u0011\t\t\r&\u0011\\\u0005\u0005\u00057\f9P\u0001\u0004PE*,7\r\u001e\t\u0005\u0005G\u0013y.\u0003\u0003\u0003b\u0006](\u0001\u0003*v]:\f'\r\\3\u0002\u0015Q\f7o\u001b*v]:,'\u000fE\u0002\u0003R\u001e\u0014!\u0002V1tWJ+hN\\3s'\u00159'q\u001bBo\u0003-)\u00070Z2CC\u000e\\WM\u001c3\u0011\t\t\r\"q^\u0005\u0005\u0005c\f9GA\bFq\u0016\u001cW\u000f^8s\u0005\u0006\u001c7.\u001a8e\u0003=!\u0018m]6EKN\u001c'/\u001b9uS>tWC\u0001B|!\u0011\u0011IPa@\u000e\u0005\tm(\u0002\u0002B\u007f\u0003W\n\u0011b]2iK\u0012,H.\u001a:\n\t\r\u0005!1 \u0002\u0010)\u0006\u001c8\u000eR3tGJL\u0007\u000f^5p]\u0006\u0001B/Y:l\t\u0016\u001c8M]5qi&|g\u000eI\u0001\ba2,x-\u001b8t+\t\u0019I\u0001\u0005\u0004\u0002|\r-1qB\u0005\u0005\u0007\u001b\tiH\u0001\u0004PaRLwN\u001c\t\u0005\u0007#\u00199\"\u0004\u0002\u0004\u0014)!1QCAE\u0003\u0019\u0001H.^4j]&!1\u0011DB\n\u0005=\u0001F.^4j]\u000e{g\u000e^1j]\u0016\u0014\u0018\u0001\u00039mk\u001eLgn\u001d\u0011\u0015\u0011\t\u00158qDB\u0011\u0007GAqAa;n\u0001\u0004\u0011i\u000fC\u0004\u0003t6\u0004\rAa>\t\u000f\r\u0015Q\u000e1\u0001\u0004\n\u00051A/Y:l\u0013\u0012,\"A!3\u0002\u000fQ\f7o[%eA\u0005AA/Y:l\u001d\u0006lW-A\u0005uCN\\g*Y7fA\u0005QA\u000f\u001b:fC\u0012t\u0015-\\3\u0016\u0005\t\u0005\u0016a\u0003;ie\u0016\fGMT1nK\u0002\nQ\"\u001c3d!J|\u0007/\u001a:uS\u0016\u001cXCAB\u001d!\u0019\u0011\u0019aa\u000f\u0004>%!\u0011q\u0019B\u0003!!\tYha\u0010\u0002\u0016\u0006U\u0015\u0002BB!\u0003{\u0012a\u0001V;qY\u0016\u0014\u0014AD7eGB\u0013x\u000e]3si&,7\u000fI\u0001\u000fe\u0016\f7o\u001c8JM.KG\u000e\\3e+\t\u0019I\u0005\u0005\u0004\u0002|\r-\u0011QS\u0001\u0013e\u0016\f7o\u001c8JM.KG\u000e\\3e?\u0012*\u0017\u000f\u0006\u0003\u0004P\rU\u0003\u0003BA>\u0007#JAaa\u0015\u0002~\t!QK\\5u\u0011%\u00199f^A\u0001\u0002\u0004\u0019I%A\u0002yIE\nqB]3bg>t\u0017JZ&jY2,G\r\t\u0015\u0004q\u000eu\u0003\u0003BA>\u0007?JAa!\u0019\u0002~\tAao\u001c7bi&dW-\u0001\u0005uQJ,\u0017\rZ%e\u00031!\bN]3bI&#w\fJ3r)\u0011\u0019ye!\u001b\t\u0013\r]#0!AA\u0002\t%\u0017!\u0003;ie\u0016\fG-\u00133!Q\rY8QL\u0001\fO\u0016$H\u000b\u001b:fC\u0012LE-\u0001\u0005gS:L7\u000f[3e+\t\ti.\u0001\u0007gS:L7\u000f[3e?\u0012*\u0017\u000f\u0006\u0003\u0004P\re\u0004\"CB,}\u0006\u0005\t\u0019AAo\u0003%1\u0017N\\5tQ\u0016$\u0007\u0005K\u0004\u0000\u0007\u007f\u001a\tja%\u0011\t\r\u00055QR\u0007\u0003\u0007\u0007SAA!\u0011\u0004\u0006*!1qQBE\u0003)\tgN\\8uCRLwN\u001c\u0006\u0003\u0007\u0017\u000bQA[1wCbLAaa$\u0004\u0004\nIq)^1sI\u0016$')_\u0001\u0006m\u0006dW/Z\u0011\u0003\u0007+\u000bq\u0002V1tWJ+hN\\3s]QD\u0017n]\u0001\u000bSN4\u0015N\\5tQ\u0016$\u0017aC:uCJ$xi\u0011+j[\u0016\fqb\u001d;beR<5\tV5nK~#S-\u001d\u000b\u0005\u0007\u001f\u001ay\n\u0003\u0006\u0004X\u0005\u0015\u0011\u0011!a\u0001\u0005\u0013\fAb\u001d;beR<5\tV5nK\u0002BC!a\u0002\u0004^\u0005!A/Y:l+\t\u0019I\u000b\u0005\u0004\u0003z\u000e-6qV\u0005\u0005\u0007[\u0013YP\u0001\u0003UCN\\\u0007\u0003BA>\u0007cKAaa-\u0002~\t\u0019\u0011I\\=\u0002\u0011Q\f7o[0%KF$Baa\u0014\u0004:\"Q1qKA\u0006\u0003\u0003\u0005\ra!+\u0002\u000bQ\f7o\u001b\u0011)\t\u000551QL\u0001\u0005W&dG\u000e\u0006\u0004\u0004P\r\r7q\u0019\u0005\t\u0007\u000b\fy\u00011\u0001\u0002^\u0006y\u0011N\u001c;feJ,\b\u000f\u001e+ie\u0016\fG\r\u0003\u0005\u0004J\u0006=\u0001\u0019AAK\u0003\u0019\u0011X-Y:p]\u000613/\u001a;UCN\\g)\u001b8jg\",G-\u00118e\u00072,\u0017M]%oi\u0016\u0014(/\u001e9u'R\fG/^:\u0015\u0005\r=\u0013AK2pY2,7\r^!dGVlW\u000f\\1u_J\u001c\u0018I\u001c3SKN,Go\u0015;biV\u001cxJ\u001c$bS2,(/\u001a\u000b\u0005\u0007'$9\u0001\u0005\u0005\u0002|\r}2Q[B\u0000!\u0019\u0011\u0019aa\u000f\u0004XB21\u0011\\Bt\u0007w\u0004\u0002ba7\u0004`\u000e\r8\u0011`\u0007\u0003\u0007;TAA!\u0012\u0002l%!1\u0011]Bo\u00055\t5mY;nk2\fGo\u001c:WeA!1Q]Bt\u0019\u0001!Ab!;\u0004l\u0006\u0005\t\u0011!B\u0001\u0007c\u00141a\u0018\u00132\u0011\u001d\u0019i/a\u0005\u0001\u0007_\fa!Y2dk6\u001c\bCBA^\u0003\u000b\u001c9.\u0005\u0003\u0004t\u000e=\u0006\u0003BA>\u0007kLAaa>\u0002~\t9aj\u001c;iS:<\u0007\u0003BBs\u0007w$Ab!@\u0004l\u0006\u0005\t\u0011!B\u0001\u0007c\u00141a\u0018\u00133!\u0019\u0011\u0019aa\u000f\u0005\u0002A!!\u0011 C\u0002\u0013\u0011!)Aa?\u0003\u001f\u0005\u001b7-^7vY\u0006\u0014G.Z%oM>D\u0001\u0002\"\u0003\u0002\u0014\u0001\u0007!\u0011Z\u0001\u0010i\u0006\u001c8n\u0015;beR$\u0016.\\3Og\u0006\u0019!/\u001e8\u0002/%t7M]3nK:$8\u000b[;gM2,W*\u001a;sS\u000e\u001cHCBB(\t#!\u0019\u0002\u0003\u0005\u0003*\u0006]\u0001\u0019\u0001BW\u0011!!)\"a\u0006A\u0002\u0011]\u0011aB7fiJL7m\u001d\t\u0005\u0005G!I\"\u0003\u0003\u0005\u001c\u0005\u001d$a\u0003+bg.lU\r\u001e:jGN\fq\u0002[1t\r\u0016$8\r\u001b$bS2,(/Z\u0001\ni\",\u0017\r\u001a#v[B$\"\u0001b\t\u0011\r\u0005m41\u0002C\u0013!\u0011!9\u0003\"\u000e\u000e\u0005\u0011%\"\u0002\u0002C\u0016\t[\t!A^\u0019\u000b\t\u0011=B\u0011G\u0001\u0004CBL'\u0002\u0002C\u001a\u0003W\naa\u001d;biV\u001c\u0018\u0002\u0002C\u001c\tS\u0011\u0001\u0003\u00165sK\u0006$7\u000b^1dWR\u0013\u0018mY3\u0002!%tG/\u001a:skB$H\u000b\u001b:fC\u0012\u0004\u0013a\u0002:fCN|g\u000e\t\u000b\t\u0005\u001f$y\u0004\"\u0011\u0005D!A!1]A\u0017\u0001\u0004\u0011)\u000f\u0003\u0005\u0004F\u00065\u0002\u0019AAo\u0011!\u0019I-!\fA\u0002\u0005U\u0015!F6jY2\u0004v\u000e\u001c7j]\u001eLe\u000e^3sm\u0006dWj]\u0001\u000eW&dG\u000eV5nK>,HOT:\u0002\u001dQ\f7.\u001a+ie\u0016\fG\rR;na\u0006\u0011B/Y:l%\u0016\f\u0007/\u001a:G_J$\u0016m]6!\u0003U)\u00070Z2vi>\u0014X*\u001a;sS\u000e\u001c8k\\;sG\u0016,\"\u0001\"\u0015\u0011\r\u0005m41\u0002C*!\u0011\u0011\u0019\u0003\"\u0016\n\t\u0011]\u0013q\r\u0002\u0016\u000bb,7-\u001e;pe6+GO]5dgN{WO]2f\u0003Y)\u00070Z2vi>\u0014X*\u001a;sS\u000e\u001c8k\\;sG\u0016\u0004\u0013AE;tKJ\u001cE.Y:t!\u0006$\bNR5sgR\f1#^:fe\u000ec\u0017m]:QCRDg)\u001b:ti\u0002\n\u0011\u0003^1tWJ+\u0017\r]3s\u000b:\f'\r\\3e\u0003I!\u0018m]6SK\u0006\u0004XM]#oC\ndW\r\u001a\u0011\u0002+-LG\u000e\\(o\r\u0006$\u0018\r\\#se>\u0014H)\u001a9uQV\u0011Aq\r\t\u0005\u0003w\"I'\u0003\u0003\u0005l\u0005u$aA%oi\u000612.\u001b7m\u001f:4\u0015\r^1m\u000bJ\u0014xN\u001d#faRD\u0007%\u0001\u0007tsN$X-\u001c'pC\u0012,'/\u0006\u0002\u0005tA!!1\u0015C;\u0013\u0011!9(a>\u0003\u0017\rc\u0017m]:M_\u0006$WM]\u0001\u000egf\u001cH/Z7M_\u0006$WM\u001d\u0011\u0002\u001f9,woU3tg&|gn\u0015;bi\u0016$B\u0001b \u0005\u0006B!!1\u0005CA\u0013\u0011!\u0019)a\u001a\u0003)%\u001bx\u000e\\1uK\u0012\u001cVm]:j_:\u001cF/\u0019;f\u0011\u001d!9)\u000ba\u0001\t\u0013\u000b\u0001C[8c\u0003J$\u0018NZ1diN#\u0018\r^3\u0011\t\u0005EF1R\u0005\u0005\t\u001b\u000bYG\u0001\tK_\n\f%\u000f^5gC\u000e$8\u000b^1uK\u0006I\u0012n]*uk\n\u0014\u0017N\\4F]\u0006\u0014G.\u001a3G_J\u001cF/\u0019;f)\u0011\ti\u000eb%\t\u000f\u0011U%\u00061\u0001\u0002\u0016\u0006!a.Y7f\u00039I7\u000fR3gCVdGo\u0015;bi\u0016$B!!8\u0005\u001c\"9AQS\u0016A\u0002\u0005U\u0015a\u00053fM\u0006,H\u000e^*fgNLwN\\*uCR,WC\u0001C@\u0003Q!WMZ1vYR\u001cVm]:j_:\u001cF/\u0019;fA\u0005!\u0012n]8mCR,GmU3tg&|gnQ1dQ\u0016,\"\u0001b*\u0011\u0011\u0011%F1XAK\t\u007fj!\u0001b+\u000b\t\u00115FqV\u0001\u0006G\u0006\u001c\u0007.\u001a\u0006\u0005\tc#\u0019,\u0001\u0004d_6lwN\u001c\u0006\u0005\tk#9,\u0001\u0004h_><G.\u001a\u0006\u0003\ts\u000b1aY8n\u0013\u0011!i\fb+\u0003\u000b\r\u000b7\r[3\u0002+%\u001cx\u000e\\1uK\u0012\u001cVm]:j_:\u001c\u0015m\u00195fA\u0005\u0019R.\u0019=ESJ,7\r\u001e*fgVdGoU5{K\u0006!R.\u0019=ESJ,7\r\u001e*fgVdGoU5{K\u0002\nQ\"\\1y%\u0016\u001cX\u000f\u001c;TSj,\u0017AD7bqJ+7/\u001e7u'&TX\rI\u0001\reVtg.\u001b8h)\u0006\u001c8n]\u000b\u0003\t\u001b\u0004\u0002Ba$\u0005P\n%'Q]\u0005\u0005\t#\u0014yDA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\fQB];o]&tw\rV1tWN\u0004\u0013\u0001E&J\u00192{V*\u0011*L?R#FjX'T\u0003EY\u0015\n\u0014'`\u001b\u0006\u00136j\u0018+U\u0019~k5\u000bI\u0001\nW&dG.T1sWN,\"\u0001\"8\u0011\u0011\t=Eq\u001aBe\t?\u0004\"\"a\u001f\u0005b\u0006u\u0017Q\u0013Be\u0013\u0011!\u0019/! \u0003\rQ+\b\u000f\\34\u0003)Y\u0017\u000e\u001c7NCJ\\7\u000fI\u0001\u0014W&dG.T1sW\u000ecW-\u00198vaR\u000b7o[\u000b\u0003\tW\u0014b\u0001\"<\u0003X\nugA\u0002Cxw\u0001!YO\u0001\u0007=e\u00164\u0017N\\3nK:$h(\u0001\u000blS2dW*\u0019:l\u00072,\u0017M\\;q)\u0006\u001c8\u000eI\u0001\u0017W&dG.T1sW\u000ecW-\u00198vaN+'O^5dKV\u0011Aq\u001f\t\u0005\u0005\u001f#I0\u0003\u0003\u0005|\n}\"\u0001G*dQ\u0016$W\u000f\\3e\u000bb,7-\u001e;peN+'O^5dK\u000692.\u001b7m\u001b\u0006\u00148n\u00117fC:,\boU3sm&\u001cW\rI\u0001\u0017\u0011\u0016\u000b%\u000b\u0016\"F\u0003R{V*\u0011-`\r\u0006KE*\u0016*F'\u00069\u0002*R!S)\n+\u0015\tV0N\u0003b{f)Q%M+J+5\u000bI\u0001\u0016\u0011\u0016\u000b%\u000b\u0016\"F\u0003R{FIU(Q?j+%kT#T\u0003YAU)\u0011*U\u0005\u0016\u000bEk\u0018#S\u001fB{&,\u0012*P\u000bN\u0003\u0013!\u0006%F\u0003J#&)R!U?&sE+\u0012*W\u00032{VjU\u0001\u0017\u0011\u0016\u000b%\u000b\u0016\"F\u0003R{\u0016J\u0014+F%Z\u000bEjX'TA\u0005YR*\u0012+S\u0013\u000e\u001bv\fU(M\u0019&suiX%O)\u0016\u0013f+\u0011'`\u001bN\u000bA$T#U%&\u001b5k\u0018)P\u00192KejR0J\u001dR+%KV!M?6\u001b\u0006%A\bq_2dwJ\u001c%fCJ$(-Z1u\u0003A\u0001x\u000e\u001c7P]\"+\u0017M\u001d;cK\u0006$\b%A\u0007nKR\u0014\u0018nY:Q_2dWM]\u000b\u0003\u000b/\u0001BAa\t\u0006\u001a%!Q1DA4\u0005U)\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c\bk\u001c7mKJ\fa\"\\3ue&\u001c7\u000fU8mY\u0016\u0014\b%A\u0006iK\u0006\u0014HOY3bi\u0016\u0014XCAC\u0012!\u0011\t\t,\"\n\n\t\u0015\u001d\u00121\u000e\u0002\f\u0011\u0016\f'\u000f\u001e2fCR,'/\u0001\u0007iK\u0006\u0014HOY3bi\u0016\u0014\b%\u0001\u000biK\u0006\u0014HOY3biJ+7-Z5wKJ\u0014VMZ\u000b\u0003\u000b_\u0001B!\"\r\u000685\u0011Q1\u0007\u0006\u0005\u000bk\tY'A\u0002sa\u000eLA!\"\u000f\u00064\tq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0017!\u00065fCJ$(-Z1u%\u0016\u001cW-\u001b<feJ+g\rI\u0001\u0012Q\u0016\f'\u000f\u001e2fCR4\u0015-\u001b7ve\u0016\u001c\u0018!\u00065fCJ$(-Z1u\r\u0006LG.\u001e:fg~#S-\u001d\u000b\u0005\u0007\u001f*\u0019\u0005C\u0005\u0004X=\u000b\t\u00111\u0001\u0005h\u0005\u0011\u0002.Z1si\n,\u0017\r\u001e$bS2,(/Z:!\u00039!WmY8n[&\u001c8/[8oK\u0012\f!\u0003Z3d_6l\u0017n]:j_:,Gm\u0018\u0013fcR!1qJC'\u0011%\u00199FUA\u0001\u0002\u0004\ti.A\beK\u000e|W.\\5tg&|g.\u001a3!\u00031\t\u0007\u000f]*uCJ$H+[7f\u00035\t\u0007\u000f]*uCJ$H+[7fA\u0005\u0019\u0001\u0010\n\u001b\u0011\u0015\u0005mD\u0011]C-\u000b3*I\u0006\u0005\u0005\u0003\u0004\t5!\u0011\u0015Be\u0003=Ig.\u001b;jC2,6/\u001a:KCJ\u001cXCAC-\u0003AIg.\u001b;jC2,6/\u001a:KCJ\u001c\b%\u0001\tj]&$\u0018.\u00197Vg\u0016\u0014h)\u001b7fg\u0006\t\u0012N\\5uS\u0006dWk]3s\r&dWm\u001d\u0011\u0002'%t\u0017\u000e^5bYV\u001bXM]!sG\"Lg/Z:\u0002)%t\u0017\u000e^5bYV\u001bXM]!sG\"Lg/Z:!\u0003=qW/\u001c*v]:Lgn\u001a+bg.\u001c\u0018\u0001\u00043fG>lW.[:tS>t\u0017\u0001E2sK\u0006$X\rV1tWJ+hN\\3s)\u0019\u0011)/\"\u001d\u0006v!9Q1O1A\u0002\t5\u0018aB2p]R,\u0007\u0010\u001e\u0005\b\u0005g\f\u0007\u0019\u0001B|\u0003)a\u0017-\u001e8dQR\u000b7o\u001b\u000b\u0007\u0007\u001f*Y(\" \t\u000f\u0015M$\r1\u0001\u0003n\"9!1\u001f2A\u0002\t]\u0018\u0001C6jY2$\u0016m]6\u0015\u0011\r=S1QCC\u000b\u000fCqa!\nd\u0001\u0004\u0011I\rC\u0004\u0004F\u000e\u0004\r!!8\t\u000f\r%7\r1\u0001\u0002\u0016\u0006a1.\u001b7m\u00032dG+Y:lgR11qJCG\u000b\u001fCqa!2e\u0001\u0004\ti\u000eC\u0004\u0004J\u0012\u0004\r!!&\u0002\tM$x\u000e]\u0001\u0013G>l\u0007/\u001e;f)>$\u0018\r\\$d)&lW\r\u0006\u0002\u0003J\u0006i1/\u001a;N\t\u000e3uN\u001d+bg.$baa\u0014\u0006\u001c\u0016u\u0005\u0002CB\u0016\u0003;\u0001\r!!&\t\u0011\u0015}\u0015Q\u0004a\u0001\u000bC\u000b1!\u001c3d!\u0019\tY,!2\u0004>\u0005y1\r\\3b]6#5IR8s)\u0006\u001c8\u000e\u0006\u0004\u0004P\u0015\u001dV\u0011\u0016\u0005\t\u0007W\ty\u00021\u0001\u0002\u0016\"AQqTA\u0010\u0001\u0004)\t+A\tde\u0016\fG/Z\"mCN\u001cHj\\1eKJ$\u0002\"b,\u00066\u0016mVq\u0018\t\u0005\u00077,\t,\u0003\u0003\u00064\u000eu'!F'vi\u0006\u0014G.Z+S\u0019\u000ec\u0017m]:M_\u0006$WM\u001d\u0005\t\u000bo\u000bI\u00041\u0001\u0006:\u0006Y1-\u001e:sK:$(*\u0019:t!!\u0011yL!2\u0002\u0016\n%\u0007\u0002CC_\u0003s\u0001\r!!8\u0002\u000fU\u001cXm\u0015;vE\"AQ\u0011YA\u001d\u0001\u0004\ti.\u0001\tjg\u0012+g-Y;miN+7o]5p]RAQqVCc\u000b\u0017,i\r\u0003\u0005\u0006H\u0006m\u0002\u0019ACe\u0003\u0011)(\u000f\\:\u0011\r\u0005m$QTAf\u0011!)i,a\u000fA\u0002\u0005u\u0007\u0002CCa\u0003w\u0001\r!!8\u0015\r\u0015=V\u0011[Cj\u0011!)9-!\u0010A\u0002\u0015%\u0007\u0002CCa\u0003{\u0001\r!!8\u00023\r\u0014X-\u0019;f\u00072\f7o\u001d'pC\u0012,'oV5uQN#XO\u0019\u000b\t\u000b_+I.b7\u0006b\"AQqYA \u0001\u0004)I\r\u0003\u0005\u0006^\u0006}\u0002\u0019ACp\u0003)\u0011\u0017N\\1ss:\u000bW.\u001a\t\u0007\u0003w\u000b)-!&\t\u0011\u0015\u0005\u0017q\ba\u0001\u0003;\f!$\u00193e%\u0016\u0004Hn\u00117bgNdu.\u00193fe&3g*Z3eK\u0012$\u0002\u0002b\u001d\u0006h\u0016-Xq\u001e\u0005\t\u000bS\f\t\u00051\u0001\u0005t\u00051\u0001/\u0019:f]RD\u0001\"\"<\u0002B\u0001\u00071\u0011J\u0001\u0010g\u0016\u001c8/[8o\u00072\f7o]+sS\"AQ\u0011_A!\u0001\u0004\t)*A\u0006tKN\u001c\u0018n\u001c8V+&#\u0015AE;qI\u0006$X\rR3qK:$WM\\2jKN$bba\u0014\u0006x\u0016uh\u0011\u0001D\u0003\r\u00131)\u0002\u0003\u0005\u0006z\u0006\r\u0003\u0019AC~\u0003!qWm\u001e$jY\u0016\u001c\b\u0003\u0003B\u0002\u0005\u001b\t)J!3\t\u0011\u0015}\u00181\ta\u0001\u000bw\fqA\\3x\u0015\u0006\u00148\u000f\u0003\u0005\u0007\u0004\u0005\r\u0003\u0019AC~\u0003-qWm^!sG\"Lg/Z:\t\u0011\u0019\u001d\u00111\ta\u0001\t\u007f\nQa\u001d;bi\u0016D!Bb\u0003\u0002DA\u0005\t\u0019\u0001D\u0007\u00039!Xm\u001d;Ti\u0006\u0014H\u000fT1uG\"\u0004b!a\u001f\u0004\f\u0019=\u0001\u0003\u0002BH\r#IAAb\u0005\u0003@\tq1i\\;oi\u0012{wO\u001c'bi\u000eD\u0007B\u0003D\f\u0003\u0007\u0002\n\u00111\u0001\u0007\u000e\u0005aA/Z:u\u000b:$G*\u0019;dQ\u0006aR\u000f\u001d3bi\u0016$U\r]3oI\u0016t7-[3tI\u0011,g-Y;mi\u0012*TC\u0001D\u000fU\u00111iAb\b,\u0005\u0019\u0005\u0002\u0003\u0002D\u0012\rWi!A\"\n\u000b\t\u0019\u001db\u0011F\u0001\nk:\u001c\u0007.Z2lK\u0012TAaa\"\u0002~%!aQ\u0006D\u0013\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001dkB$\u0017\r^3EKB,g\u000eZ3oG&,7\u000f\n3fM\u0006,H\u000e\u001e\u00137\u0003=\u0011X\r]8si\"+\u0017M\u001d;CK\u0006$\u0018!E4fiR\u000b7o\u001b+ie\u0016\fG\rR;naR!A1\u0005D\u001c\u0011!\u0019)#a\u0013A\u0002\t%\u0017\u0001C#yK\u000e,Ho\u001c:\u0011\t\t\r\u0012qJ\n\u0005\u0003\u001f\nI\b\u0006\u0002\u0007<\u0005AB/Y:l\t\u0016\u001cXM]5bY&T\u0018\r^5p]B\u0013x\u000e]:\u0016\u0005\u0019\u0015\u0003C\u0002BR\r\u000f2Y%\u0003\u0003\u0007J\u0005](a\u0003+ie\u0016\fG\rT8dC2\u0004BA\"\u0014\u0007P5\u0011!1I\u0005\u0005\r#\u0012\u0019E\u0001\u0006Qe>\u0004XM\u001d;jKN\f\u0011\u0004^1tW\u0012+7/\u001a:jC2L'0\u0019;j_:\u0004&o\u001c9tA\u0005YR\r_3dkR|'oU8ve\u000e,Gj\\2bY6{G-Z(oYf\fq$\u001a=fGV$xN]*pkJ\u001cW\rT8dC2lu\u000eZ3P]2Lx\fJ3r)\u0011\u0019yEb\u0017\t\u0015\r]\u0013\u0011LA\u0001\u0002\u0004\u0011i+\u0001\u000ffq\u0016\u001cW\u000f^8s'>,(oY3M_\u000e\fG.T8eK>sG.\u001f\u0011\u0002\u0019%\u001ch)\u0019;bY\u0016\u0013(o\u001c:\u0015\r\u0005ug1\rD7\u0011!1)'!\u0018A\u0002\u0019\u001d\u0014!\u0001;\u0011\t\u0005mf\u0011N\u0005\u0005\rW\nIMA\u0005UQJ|w/\u00192mK\"AaqNA/\u0001\u0004!9'\u0001\u0007eKB$\b\u000eV8DQ\u0016\u001c7\u000e\u000b\u0003\u0002^\u0019M\u0004\u0003\u0002D;\roj!A\"\u000b\n\t\u0019ed\u0011\u0006\u0002\bi\u0006LGN]3d\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011aq\u0010\u0016\u0005\u0003s3y\"A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0003\r\u000bSC!!8\u0007 \u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY*\"Ab#+\t\u0005\u0015hq\u0004"
)
public class Executor implements Logging {
   private final String executorId;
   private final String executorHostname;
   public final SparkEnv org$apache$spark$executor$Executor$$env;
   private final Seq userClassPath;
   public final boolean org$apache$spark$executor$Executor$$isLocal;
   public final Thread.UncaughtExceptionHandler org$apache$spark$executor$Executor$$uncaughtExceptionHandler;
   private final Map resources;
   private final AtomicBoolean executorShutdown;
   private final Object stopHookReference;
   private final ByteBuffer org$apache$spark$executor$Executor$$EMPTY_BYTE_BUFFER;
   private final SparkConf conf;
   private final String taskNameMDCKey;
   private final ReentrantLock updateDependenciesLock;
   private final ThreadPoolExecutor threadPool;
   private final String[] schemes;
   private final ExecutorSource org$apache$spark$executor$Executor$$executorSource;
   private final ThreadPoolExecutor taskReaperPool;
   private final HashMap org$apache$spark$executor$Executor$$taskReaperForTask;
   private final Option executorMetricsSource;
   private final boolean userClassPathFirst;
   private final boolean taskReaperEnabled;
   private final int org$apache$spark$executor$Executor$$killOnFatalErrorDepth;
   private final ClassLoader systemLoader;
   private final IsolatedSessionState defaultSessionState;
   private final Cache isolatedSessionCache;
   private final long org$apache$spark$executor$Executor$$maxDirectResultSize;
   private final long org$apache$spark$executor$Executor$$maxResultSize;
   private final ConcurrentHashMap runningTasks;
   private final long org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS;
   private final ConcurrentHashMap killMarks;
   private final Runnable killMarkCleanupTask;
   private final ScheduledExecutorService killMarkCleanupService;
   private final int HEARTBEAT_MAX_FAILURES;
   private final boolean HEARTBEAT_DROP_ZEROES;
   private final long HEARTBEAT_INTERVAL_MS;
   private final long METRICS_POLLING_INTERVAL_MS;
   private final boolean pollOnHeartbeat;
   private final ExecutorMetricsPoller metricsPoller;
   private final Heartbeater heartbeater;
   private final RpcEndpointRef heartbeatReceiverRef;
   private int heartbeatFailures;
   private boolean decommissioned;
   private final long appStartTime;
   // $FF: synthetic field
   private final Tuple3 x$4;
   private final Map initialUserJars;
   private final Map initialUserFiles;
   private final Map initialUserArchives;
   private final Option plugins;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Thread.UncaughtExceptionHandler $lessinit$greater$default$6() {
      return Executor$.MODULE$.$lessinit$greater$default$6();
   }

   public static boolean $lessinit$greater$default$5() {
      return Executor$.MODULE$.$lessinit$greater$default$5();
   }

   public static Seq $lessinit$greater$default$4() {
      return Executor$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean isFatalError(final Throwable t, final int depthToCheck) {
      return Executor$.MODULE$.isFatalError(t, depthToCheck);
   }

   public static void executorSourceLocalModeOnly_$eq(final ExecutorSource x$1) {
      Executor$.MODULE$.executorSourceLocalModeOnly_$eq(x$1);
   }

   public static ExecutorSource executorSourceLocalModeOnly() {
      return Executor$.MODULE$.executorSourceLocalModeOnly();
   }

   public static ThreadLocal taskDeserializationProps() {
      return Executor$.MODULE$.taskDeserializationProps();
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private AtomicBoolean executorShutdown() {
      return this.executorShutdown;
   }

   public Object stopHookReference() {
      return this.stopHookReference;
   }

   public ByteBuffer org$apache$spark$executor$Executor$$EMPTY_BYTE_BUFFER() {
      return this.org$apache$spark$executor$Executor$$EMPTY_BYTE_BUFFER;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public String taskNameMDCKey() {
      return this.taskNameMDCKey;
   }

   private ReentrantLock updateDependenciesLock() {
      return this.updateDependenciesLock;
   }

   public ThreadPoolExecutor threadPool() {
      return this.threadPool;
   }

   private String[] schemes() {
      return this.schemes;
   }

   public ExecutorSource org$apache$spark$executor$Executor$$executorSource() {
      return this.org$apache$spark$executor$Executor$$executorSource;
   }

   private ThreadPoolExecutor taskReaperPool() {
      return this.taskReaperPool;
   }

   public HashMap org$apache$spark$executor$Executor$$taskReaperForTask() {
      return this.org$apache$spark$executor$Executor$$taskReaperForTask;
   }

   public Option executorMetricsSource() {
      return this.executorMetricsSource;
   }

   private boolean userClassPathFirst() {
      return this.userClassPathFirst;
   }

   private boolean taskReaperEnabled() {
      return this.taskReaperEnabled;
   }

   public int org$apache$spark$executor$Executor$$killOnFatalErrorDepth() {
      return this.org$apache$spark$executor$Executor$$killOnFatalErrorDepth;
   }

   private ClassLoader systemLoader() {
      return this.systemLoader;
   }

   public IsolatedSessionState org$apache$spark$executor$Executor$$newSessionState(final JobArtifactState jobArtifactState) {
      HashMap currentFiles = new HashMap();
      HashMap currentJars = new HashMap();
      HashMap currentArchives = new HashMap();
      MutableURLClassLoader urlClassLoader = this.createClassLoader(currentJars, this.isStubbingEnabledForState(jobArtifactState.uuid()), this.org$apache$spark$executor$Executor$$isDefaultState(jobArtifactState.uuid()));
      ClassLoader replClassLoader = this.addReplClassLoaderIfNeeded(urlClassLoader, jobArtifactState.replClassDirUri(), jobArtifactState.uuid());
      return new IsolatedSessionState(jobArtifactState.uuid(), urlClassLoader, replClassLoader, currentFiles, currentJars, currentArchives, jobArtifactState.replClassDirUri());
   }

   private boolean isStubbingEnabledForState(final String name) {
      return !this.org$apache$spark$executor$Executor$$isDefaultState(name) && ((IterableOnceOps)this.conf().get(org.apache.spark.internal.config.package$.MODULE$.CONNECT_SCALA_UDF_STUB_PREFIXES())).nonEmpty();
   }

   public boolean org$apache$spark$executor$Executor$$isDefaultState(final String name) {
      boolean var10000;
      label23: {
         String var2 = "default";
         if (name == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (name.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public IsolatedSessionState defaultSessionState() {
      return this.defaultSessionState;
   }

   public Cache isolatedSessionCache() {
      return this.isolatedSessionCache;
   }

   public long org$apache$spark$executor$Executor$$maxDirectResultSize() {
      return this.org$apache$spark$executor$Executor$$maxDirectResultSize;
   }

   public long org$apache$spark$executor$Executor$$maxResultSize() {
      return this.org$apache$spark$executor$Executor$$maxResultSize;
   }

   public ConcurrentHashMap runningTasks() {
      return this.runningTasks;
   }

   public long org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS() {
      return this.org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS;
   }

   public ConcurrentHashMap killMarks() {
      return this.killMarks;
   }

   private Runnable killMarkCleanupTask() {
      return this.killMarkCleanupTask;
   }

   private ScheduledExecutorService killMarkCleanupService() {
      return this.killMarkCleanupService;
   }

   private int HEARTBEAT_MAX_FAILURES() {
      return this.HEARTBEAT_MAX_FAILURES;
   }

   private boolean HEARTBEAT_DROP_ZEROES() {
      return this.HEARTBEAT_DROP_ZEROES;
   }

   private long HEARTBEAT_INTERVAL_MS() {
      return this.HEARTBEAT_INTERVAL_MS;
   }

   private long METRICS_POLLING_INTERVAL_MS() {
      return this.METRICS_POLLING_INTERVAL_MS;
   }

   private boolean pollOnHeartbeat() {
      return this.pollOnHeartbeat;
   }

   public ExecutorMetricsPoller metricsPoller() {
      return this.metricsPoller;
   }

   private Heartbeater heartbeater() {
      return this.heartbeater;
   }

   private RpcEndpointRef heartbeatReceiverRef() {
      return this.heartbeatReceiverRef;
   }

   private int heartbeatFailures() {
      return this.heartbeatFailures;
   }

   private void heartbeatFailures_$eq(final int x$1) {
      this.heartbeatFailures = x$1;
   }

   private boolean decommissioned() {
      return this.decommissioned;
   }

   private void decommissioned_$eq(final boolean x$1) {
      this.decommissioned = x$1;
   }

   private long appStartTime() {
      return this.appStartTime;
   }

   private Map initialUserJars() {
      return this.initialUserJars;
   }

   private Map initialUserFiles() {
      return this.initialUserFiles;
   }

   private Map initialUserArchives() {
      return this.initialUserArchives;
   }

   private Option plugins() {
      return this.plugins;
   }

   public int numRunningTasks() {
      return this.runningTasks().size();
   }

   public void decommission() {
      this.decommissioned_$eq(true);
   }

   public TaskRunner createTaskRunner(final ExecutorBackend context, final TaskDescription taskDescription) {
      return new TaskRunner(context, taskDescription, this.plugins());
   }

   public void launchTask(final ExecutorBackend context, final TaskDescription taskDescription) {
      long taskId = taskDescription.taskId();
      TaskRunner tr = this.createTaskRunner(context, taskDescription);
      this.runningTasks().put(BoxesRunTime.boxToLong(taskId), tr);
      Tuple3 killMark = (Tuple3)this.killMarks().get(BoxesRunTime.boxToLong(taskId));
      if (killMark != null) {
         tr.kill(BoxesRunTime.unboxToBoolean(killMark._1()), (String)killMark._2());
         this.killMarks().remove(BoxesRunTime.boxToLong(taskId));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.threadPool().execute(tr);
      if (this.decommissioned()) {
         this.log().error("Launching a task while in decommissioned state.");
      }
   }

   public void killTask(final long taskId, final boolean interruptThread, final String reason) {
      this.killMarks().put(BoxesRunTime.boxToLong(taskId), new Tuple3(BoxesRunTime.boxToBoolean(interruptThread), reason, BoxesRunTime.boxToLong(System.currentTimeMillis())));
      TaskRunner taskRunner = (TaskRunner)this.runningTasks().get(BoxesRunTime.boxToLong(taskId));
      if (taskRunner != null) {
         if (this.taskReaperEnabled()) {
            synchronized(this.org$apache$spark$executor$Executor$$taskReaperForTask()){}

            Object var9;
            try {
               Option var11 = this.org$apache$spark$executor$Executor$$taskReaperForTask().get(BoxesRunTime.boxToLong(taskId));
               boolean var10000;
               if (.MODULE$.equals(var11)) {
                  var10000 = true;
               } else {
                  if (!(var11 instanceof Some)) {
                     throw new MatchError(var11);
                  }

                  Some var12 = (Some)var11;
                  TaskReaper existingReaper = (TaskReaper)var12.value();
                  var10000 = interruptThread && !existingReaper.interruptThread();
               }

               boolean shouldCreateReaper = var10000;
               Object var17;
               if (shouldCreateReaper) {
                  TaskReaper taskReaper = new TaskReaper(taskRunner, interruptThread, reason);
                  this.org$apache$spark$executor$Executor$$taskReaperForTask().update(BoxesRunTime.boxToLong(taskId), taskReaper);
                  var17 = new Some(taskReaper);
               } else {
                  var17 = .MODULE$;
               }

               var9 = var17;
            } catch (Throwable var16) {
               throw var16;
            }

            ((Option)var9).foreach((x$1) -> {
               $anonfun$killTask$1(this, x$1);
               return BoxedUnit.UNIT;
            });
         } else {
            taskRunner.kill(interruptThread, reason);
         }

         this.killMarks().remove(BoxesRunTime.boxToLong(taskId));
      }
   }

   public void killAllTasks(final boolean interruptThread, final String reason) {
      scala.jdk.CollectionConverters..MODULE$.EnumerationHasAsScala(this.runningTasks().keys()).asScala().foreach((JFunction1.mcVJ.sp)(t) -> this.killTask(t, interruptThread, reason));
   }

   public void stop() {
      if (!this.executorShutdown().getAndSet(true)) {
         ShutdownHookManager$.MODULE$.removeShutdownHook(this.stopHookReference());
         this.org$apache$spark$executor$Executor$$env.metricsSystem().report();

         try {
            if (this.metricsPoller() != null) {
               this.metricsPoller().stop();
            }
         } catch (Throwable var10) {
            if (var10 == null || !scala.util.control.NonFatal..MODULE$.apply(var10)) {
               throw var10;
            }

            this.logWarning((Function0)(() -> "Unable to stop executor metrics poller"), var10);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         try {
            if (this.heartbeater() != null) {
               this.heartbeater().stop();
            }
         } catch (Throwable var9) {
            if (var9 == null || !scala.util.control.NonFatal..MODULE$.apply(var9)) {
               throw var9;
            }

            this.logWarning((Function0)(() -> "Unable to stop heartbeater"), var9);
            BoxedUnit var11 = BoxedUnit.UNIT;
         }

         ShuffleBlockPusher$.MODULE$.stop();
         if (this.threadPool() != null) {
            this.threadPool().shutdown();
         }

         if (this.killMarkCleanupService() != null) {
            this.killMarkCleanupService().shutdown();
         }

         if (this.defaultSessionState() != null && this.plugins() != null) {
            Utils$.MODULE$.withContextClassLoader(this.defaultSessionState().replClassLoader(), (JFunction0.mcV.sp)() -> this.plugins().foreach((x$5) -> {
                  $anonfun$stop$4(x$5);
                  return BoxedUnit.UNIT;
               }));
         } else {
            BoxedUnit var12 = BoxedUnit.UNIT;
         }

         if (!this.org$apache$spark$executor$Executor$$isLocal) {
            this.org$apache$spark$executor$Executor$$env.stop();
         }
      }
   }

   public long org$apache$spark$executor$Executor$$computeTotalGcTime() {
      return BoxesRunTime.unboxToLong(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(ManagementFactory.getGarbageCollectorMXBeans()).asScala().map((x$6) -> BoxesRunTime.boxToLong($anonfun$computeTotalGcTime$1(x$6)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   public void org$apache$spark$executor$Executor$$setMDCForTask(final String taskName, final Seq mdc) {
      try {
         mdc.foreach((x0$1) -> {
            $anonfun$setMDCForTask$1(x0$1);
            return BoxedUnit.UNIT;
         });
         MDC.put(this.taskNameMDCKey(), taskName);
      } catch (NoSuchFieldError var3) {
         this.logInfo((Function0)(() -> "MDC is not supported."));
      }

   }

   public void org$apache$spark$executor$Executor$$cleanMDCForTask(final String taskName, final Seq mdc) {
      try {
         mdc.foreach((x0$1) -> {
            $anonfun$cleanMDCForTask$1(x0$1);
            return BoxedUnit.UNIT;
         });
         MDC.remove(this.taskNameMDCKey());
      } catch (NoSuchFieldError var3) {
         this.logInfo((Function0)(() -> "MDC is not supported."));
      }

   }

   private MutableURLClassLoader createClassLoader(final HashMap currentJars, final boolean useStub, final boolean isDefaultSession) {
      long now = System.currentTimeMillis();
      this.userClassPath.foreach((url) -> {
         $anonfun$createClassLoader$1(currentJars, now, url);
         return BoxedUnit.UNIT;
      });
      URL[] urls = (URL[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(this.userClassPath.toArray(scala.reflect.ClassTag..MODULE$.apply(URL.class))), (IterableOnce)currentJars.keySet().map((uri) -> (new File((String)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])uri.split("/"))))).toURI().toURL()), scala.reflect.ClassTag..MODULE$.apply(URL.class));
      return this.createClassLoader(urls, useStub, isDefaultSession);
   }

   private MutableURLClassLoader createClassLoader(final URL[] urls, final boolean useStub, final boolean isDefaultSession) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting executor with user classpath"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (userClassPathFirst ="})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "): "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.EXECUTOR_USER_CLASS_PATH_FIRST..MODULE$, BoxesRunTime.boxToBoolean(this.userClassPathFirst()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.URLS..MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])urls).mkString("'", ",", "'"))}))))));
      return useStub ? this.createClassLoaderWithStub(urls, (Seq)this.conf().get(org.apache.spark.internal.config.package$.MODULE$.CONNECT_SCALA_UDF_STUB_PREFIXES()), isDefaultSession) : this.createClassLoader(urls, isDefaultSession);
   }

   private MutableURLClassLoader createClassLoader(final URL[] urls, final boolean isDefaultSession) {
      ClassLoader loader = isDefaultSession ? this.systemLoader() : this.defaultSessionState().replClassLoader();
      return (MutableURLClassLoader)(this.userClassPathFirst() ? new ChildFirstURLClassLoader(urls, loader) : new MutableURLClassLoader(urls, loader));
   }

   private MutableURLClassLoader createClassLoaderWithStub(final URL[] urls, final Seq binaryName, final boolean isDefaultSession) {
      ClassLoader loader = isDefaultSession ? this.systemLoader() : this.defaultSessionState().replClassLoader();
      if (this.userClassPathFirst()) {
         StubClassLoader stubClassLoader = StubClassLoader$.MODULE$.apply(loader, binaryName);
         return new ChildFirstURLClassLoader(urls, stubClassLoader);
      } else {
         StubClassLoader stubClassLoader = StubClassLoader$.MODULE$.apply((ClassLoader)null, binaryName);
         return new ChildFirstURLClassLoader(urls, stubClassLoader, loader);
      }
   }

   private ClassLoader addReplClassLoaderIfNeeded(final ClassLoader parent, final Option sessionClassUri, final String sessionUUID) {
      String classUri = (String)sessionClassUri.getOrElse(() -> this.conf().get("spark.repl.class.uri", (String)null));
      Object var10000;
      if (classUri != null) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using REPL class URI: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, classUri)})))));
         var10000 = new ExecutorClassLoader(this.conf(), this.org$apache$spark$executor$Executor$$env, classUri, parent, this.userClassPathFirst());
      } else {
         var10000 = parent;
      }

      ClassLoader classLoader = (ClassLoader)var10000;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created or updated repl class loader ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.CLASS_LOADER..MODULE$, classLoader)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" for ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.SESSION_ID..MODULE$, sessionUUID)}))))));
      return classLoader;
   }

   public void updateDependencies(final Map newFiles, final Map newJars, final Map newArchives, final IsolatedSessionState state, final Option testStartLatch, final Option testEndLatch) {
      LazyRef hadoopConf$lzy = new LazyRef();
      BooleanRef renewClassLoader = BooleanRef.create(false);
      this.updateDependenciesLock().lockInterruptibly();

      try {
         LazyRef root$lzy = new LazyRef();
         testStartLatch.foreach((x$21) -> {
            $anonfun$updateDependencies$1(x$21);
            return BoxedUnit.UNIT;
         });
         newFiles.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$updateDependencies$2(check$ifrefutable$1))).withFilter((x$22) -> BoxesRunTime.boxToBoolean($anonfun$updateDependencies$3(state, x$22))).foreach((x$23) -> {
            $anonfun$updateDependencies$5(this, state, root$lzy, hadoopConf$lzy, x$23);
            return BoxedUnit.UNIT;
         });
         newArchives.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$updateDependencies$7(check$ifrefutable$2))).withFilter((x$24) -> BoxesRunTime.boxToBoolean($anonfun$updateDependencies$8(state, x$24))).foreach((x$25) -> {
            $anonfun$updateDependencies$10(this, state, hadoopConf$lzy, root$lzy, x$25);
            return BoxedUnit.UNIT;
         });
         newJars.withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$updateDependencies$13(check$ifrefutable$3))).foreach((x$26) -> {
            $anonfun$updateDependencies$14(this, state, renewClassLoader, root$lzy, hadoopConf$lzy, x$26);
            return BoxedUnit.UNIT;
         });
         if (renewClassLoader.elem) {
            state.urlClassLoader_$eq(this.createClassLoader(state.urlClassLoader().getURLs(), true, this.org$apache$spark$executor$Executor$$isDefaultState(state.sessionUUID())));
            state.replClassLoader_$eq(this.addReplClassLoaderIfNeeded(state.urlClassLoader(), state.replClassDirUri(), state.sessionUUID()));
         }

         testEndLatch.foreach((x$27) -> {
            $anonfun$updateDependencies$19(x$27);
            return BoxedUnit.UNIT;
         });
      } finally {
         this.updateDependenciesLock().unlock();
      }

   }

   public Option updateDependencies$default$5() {
      return .MODULE$;
   }

   public Option updateDependencies$default$6() {
      return .MODULE$;
   }

   private void reportHeartBeat() {
      ArrayBuffer accumUpdates = new ArrayBuffer();
      long curGCTime = this.org$apache$spark$executor$Executor$$computeTotalGcTime();
      if (this.pollOnHeartbeat()) {
         this.metricsPoller().poll();
      }

      HashMap executorUpdates = this.metricsPoller().getExecutorUpdates();
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.runningTasks().values()).asScala().foreach((taskRunner) -> {
         if (taskRunner.task() != null) {
            taskRunner.task().metrics().mergeShuffleReadMetrics();
            taskRunner.task().metrics().setJvmGCTime(curGCTime - taskRunner.startGCTime());
            Seq accumulatorsToReport = (Seq)((IterableOps)(this.HEARTBEAT_DROP_ZEROES() ? taskRunner.task().metrics().accumulators().filterNot((x$28) -> BoxesRunTime.boxToBoolean($anonfun$reportHeartBeat$2(x$28))) : taskRunner.task().metrics().accumulators())).filterNot((x$29) -> BoxesRunTime.boxToBoolean($anonfun$reportHeartBeat$3(x$29)));
            return accumUpdates.$plus$eq(new Tuple2(BoxesRunTime.boxToLong(taskRunner.taskId()), accumulatorsToReport));
         } else {
            return BoxedUnit.UNIT;
         }
      });
      Heartbeat message = new Heartbeat(this.executorId, (Tuple2[])accumUpdates.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.org$apache$spark$executor$Executor$$env.blockManager().blockManagerId(), executorUpdates);

      try {
         HeartbeatResponse response = (HeartbeatResponse)this.heartbeatReceiverRef().askSync(message, new RpcTimeout((new scala.concurrent.duration.package.DurationLong(scala.concurrent.duration.package..MODULE$.DurationLong(this.HEARTBEAT_INTERVAL_MS()))).millis(), org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL().key()), scala.reflect.ClassTag..MODULE$.apply(HeartbeatResponse.class));
         if (!this.executorShutdown().get() && response.reregisterBlockManager()) {
            this.logInfo((Function0)(() -> "Told to re-register on heartbeat"));
            this.org$apache$spark$executor$Executor$$env.blockManager().reregister();
         }

         this.heartbeatFailures_$eq(0);
      } catch (Throwable var11) {
         if (var11 == null || !scala.util.control.NonFatal..MODULE$.apply(var11)) {
            throw var11;
         }

         this.logWarning((Function0)(() -> "Issue communicating with driver in heartbeater"), var11);
         this.heartbeatFailures_$eq(this.heartbeatFailures() + 1);
         if (this.heartbeatFailures() >= this.HEARTBEAT_MAX_FAILURES()) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exit as unable to send heartbeats to driver "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"more than ", " times"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.HEARTBEAT_MAX_FAILURES()))}))))));
            System.exit(ExecutorExitCode$.MODULE$.HEARTBEAT_FAILURE());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var12 = BoxedUnit.UNIT;
         }
      }

   }

   public Option getTaskThreadDump(final long taskId) {
      TaskRunner runner = (TaskRunner)this.runningTasks().get(BoxesRunTime.boxToLong(taskId));
      if (runner != null) {
         return runner.theadDump();
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to dump thread for task ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))})))));
         return .MODULE$;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$schemes$2(final String x$2) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final void $anonfun$new$4(final Executor $this, final ExecutorMetricsSource x$3) {
      x$3.register($this.org$apache$spark$executor$Executor$$env.metricsSystem());
   }

   // $FF: synthetic method
   public static final void $anonfun$killTask$1(final Executor $this, final Runnable x$1) {
      $this.taskReaperPool().execute(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$4(final PluginContainer x$5) {
      x$5.shutdown();
   }

   // $FF: synthetic method
   public static final long $anonfun$computeTotalGcTime$1(final GarbageCollectorMXBean x$6) {
      return x$6.getCollectionTime();
   }

   // $FF: synthetic method
   public static final void $anonfun$setMDCForTask$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         String value = (String)x0$1._2();
         MDC.put(key, value);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanMDCForTask$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         MDC.remove(key);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createClassLoader$1(final HashMap currentJars$1, final long now$1, final URL url) {
      currentJars$1.update(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])url.getPath().split("/"))), BoxesRunTime.boxToLong(now$1));
   }

   // $FF: synthetic method
   private final Configuration hadoopConf$lzycompute$1(final LazyRef hadoopConf$lzy$1) {
      synchronized(hadoopConf$lzy$1){}

      Configuration var3;
      try {
         var3 = hadoopConf$lzy$1.initialized() ? (Configuration)hadoopConf$lzy$1.value() : (Configuration)hadoopConf$lzy$1.initialize(SparkHadoopUtil$.MODULE$.get().newConfiguration(this.conf()));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private final Configuration hadoopConf$1(final LazyRef hadoopConf$lzy$1) {
      return hadoopConf$lzy$1.initialized() ? (Configuration)hadoopConf$lzy$1.value() : this.hadoopConf$lzycompute$1(hadoopConf$lzy$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDependencies$1(final CountDownLatch x$21) {
      x$21.countDown();
   }

   // $FF: synthetic method
   private final File root$lzycompute$1(final LazyRef root$lzy$1, final IsolatedSessionState state$2) {
      synchronized(root$lzy$1){}

      File var4;
      try {
         File var10000;
         if (root$lzy$1.initialized()) {
            var10000 = (File)root$lzy$1.value();
         } else {
            File var10001;
            if (!this.org$apache$spark$executor$Executor$$isDefaultState(state$2.sessionUUID())) {
               File newDest = new File(SparkFiles$.MODULE$.getRootDirectory(), state$2.sessionUUID());
               newDest.mkdir();
               var10001 = newDest;
            } else {
               var10001 = new File(SparkFiles$.MODULE$.getRootDirectory());
            }

            var10000 = (File)root$lzy$1.initialize(var10001);
         }

         var4 = var10000;
      } catch (Throwable var7) {
         throw var7;
      }

      return var4;
   }

   private final File root$1(final LazyRef root$lzy$1, final IsolatedSessionState state$2) {
      return root$lzy$1.initialized() ? (File)root$lzy$1.value() : this.root$lzycompute$1(root$lzy$1, state$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateDependencies$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateDependencies$3(final IsolatedSessionState state$2, final Tuple2 x$22) {
      if (x$22 != null) {
         String name = (String)x$22._1();
         long timestamp = x$22._2$mcJ$sp();
         return BoxesRunTime.unboxToLong(state$2.currentFiles().getOrElse(name, (JFunction0.mcJ.sp)() -> -1L)) < timestamp;
      } else {
         throw new MatchError(x$22);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDependencies$5(final Executor $this, final IsolatedSessionState state$2, final LazyRef root$lzy$1, final LazyRef hadoopConf$lzy$1, final Tuple2 x$23) {
      if (x$23 != null) {
         String name = (String)x$23._1();
         long timestamp = x$23._2$mcJ$sp();
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fetching ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, name)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" timestamp ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TIMESTAMP..MODULE$, BoxesRunTime.boxToLong(timestamp))}))))));
         Utils$.MODULE$.fetchFile(name, $this.root$1(root$lzy$1, state$2), $this.conf(), $this.hadoopConf$1(hadoopConf$lzy$1), timestamp, !$this.org$apache$spark$executor$Executor$$isLocal, Utils$.MODULE$.fetchFile$default$7());
         state$2.currentFiles().update(name, BoxesRunTime.boxToLong(timestamp));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$23);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateDependencies$7(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateDependencies$8(final IsolatedSessionState state$2, final Tuple2 x$24) {
      if (x$24 != null) {
         String name = (String)x$24._1();
         long timestamp = x$24._2$mcJ$sp();
         return BoxesRunTime.unboxToLong(state$2.currentArchives().getOrElse(name, (JFunction0.mcJ.sp)() -> -1L)) < timestamp;
      } else {
         throw new MatchError(x$24);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDependencies$10(final Executor $this, final IsolatedSessionState state$2, final LazyRef hadoopConf$lzy$1, final LazyRef root$lzy$1, final Tuple2 x$25) {
      if (x$25 != null) {
         String name = (String)x$25._1();
         long timestamp = x$25._2$mcJ$sp();
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fetching ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.ARCHIVE_NAME..MODULE$, name)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" timestamp ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TIMESTAMP..MODULE$, BoxesRunTime.boxToLong(timestamp))}))))));
         URI sourceURI = new URI(name);
         URI uriToDownload = Utils$.MODULE$.getUriBuilder(sourceURI).fragment((String)null).build(new Object[0]);
         File source = Utils$.MODULE$.fetchFile(uriToDownload.toString(), Utils$.MODULE$.createTempDir(), $this.conf(), $this.hadoopConf$1(hadoopConf$lzy$1), timestamp, !$this.org$apache$spark$executor$Executor$$isLocal, false);
         File dest = new File($this.root$1(root$lzy$1, state$2), sourceURI.getFragment() != null ? sourceURI.getFragment() : source.getName());
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unpacking an archive ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.ARCHIVE_NAME..MODULE$, name)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (", " bytes)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.BYTE_SIZE..MODULE$, BoxesRunTime.boxToLong(source.length()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.SOURCE_PATH..MODULE$, source.getAbsolutePath())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.DESTINATION_PATH..MODULE$, dest.getAbsolutePath())}))))));
         Utils$.MODULE$.deleteRecursively(dest);
         Utils$.MODULE$.unpack(source, dest);
         state$2.currentArchives().update(name, BoxesRunTime.boxToLong(timestamp));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$25);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateDependencies$13(final Tuple2 check$ifrefutable$3) {
      return check$ifrefutable$3 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDependencies$14(final Executor $this, final IsolatedSessionState state$2, final BooleanRef renewClassLoader$1, final LazyRef root$lzy$1, final LazyRef hadoopConf$lzy$1, final Tuple2 x$26) {
      if (x$26 != null) {
         String name = (String)x$26._1();
         long timestamp = x$26._2$mcJ$sp();
         String localName = (String)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new URI(name)).getPath().split("/")));
         long currentTimeStamp = BoxesRunTime.unboxToLong(state$2.currentJars().get(name).orElse(() -> state$2.currentJars().get(localName)).getOrElse((JFunction0.mcJ.sp)() -> -1L));
         if (currentTimeStamp < timestamp) {
            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fetching ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.JAR_URL..MODULE$, name)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" timestamp ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TIMESTAMP..MODULE$, BoxesRunTime.boxToLong(timestamp))}))))));
            Utils$.MODULE$.fetchFile(name, $this.root$1(root$lzy$1, state$2), $this.conf(), $this.hadoopConf$1(hadoopConf$lzy$1), timestamp, !$this.org$apache$spark$executor$Executor$$isLocal, Utils$.MODULE$.fetchFile$default$7());
            state$2.currentJars().update(name, BoxesRunTime.boxToLong(timestamp));
            URL url = (new File($this.root$1(root$lzy$1, state$2), localName)).toURI().toURL();
            if (!scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])state$2.urlClassLoader().getURLs()), url)) {
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Adding ", " to"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.URL..MODULE$, url)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" class loader ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, state$2.sessionUUID())}))))));
               state$2.urlClassLoader().addURL(url);
               if ($this.isStubbingEnabledForState(state$2.sessionUUID())) {
                  renewClassLoader$1.elem = true;
                  BoxedUnit var17 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var16 = BoxedUnit.UNIT;
               }
            } else {
               BoxedUnit var15 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$26);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDependencies$19(final CountDownLatch x$27) {
      x$27.await();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reportHeartBeat$2(final AccumulatorV2 x$28) {
      return x$28.isZero();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reportHeartBeat$3(final AccumulatorV2 x$29) {
      return x$29.excludeFromHeartbeat();
   }

   public Executor(final String executorId, final String executorHostname, final SparkEnv env, final Seq userClassPath, final boolean isLocal, final Thread.UncaughtExceptionHandler uncaughtExceptionHandler, final Map resources) {
      this.executorId = executorId;
      this.executorHostname = executorHostname;
      this.org$apache$spark$executor$Executor$$env = env;
      this.userClassPath = userClassPath;
      this.org$apache$spark$executor$Executor$$isLocal = isLocal;
      this.org$apache$spark$executor$Executor$$uncaughtExceptionHandler = uncaughtExceptionHandler;
      this.resources = resources;
      Logging.$init$(this);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting executor ID ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, this.executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" on host ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.executorHostname)}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"OS info ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.OS_NAME..MODULE$, System.getProperty("os.name"))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.OS_VERSION..MODULE$, System.getProperty("os.version"))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.OS_ARCH..MODULE$, System.getProperty("os.arch"))}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Java version ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.JAVA_VERSION..MODULE$, System.getProperty("java.version"))})))));
      this.executorShutdown = new AtomicBoolean(false);
      this.stopHookReference = ShutdownHookManager$.MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> this.stop());
      this.org$apache$spark$executor$Executor$$EMPTY_BYTE_BUFFER = ByteBuffer.wrap(new byte[0]);
      this.conf = env.conf();
      this.taskNameMDCKey = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.LEGACY_TASK_NAME_MDC_ENABLED())) ? "mdc.taskName" : org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$.name();
      this.updateDependenciesLock = new ReentrantLock();
      Utils$.MODULE$.checkHost(executorHostname);
      scala.Predef..MODULE$.assert(0 == Utils$.MODULE$.parseHostPort(executorHostname)._2$mcI$sp());
      Utils$.MODULE$.setCustomHostname(executorHostname);
      if (!isLocal) {
         Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
      }

      ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("Executor task launch worker-%d").setThreadFactory((r) -> new UninterruptibleThread(r, "unused")).build();
      this.threadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool(threadFactory);
      this.schemes = (String[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String)this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_METRICS_FILESYSTEM_SCHEMES())).toLowerCase(Locale.ROOT).split(",")), (x$1) -> x$1.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$schemes$2(x$2)));
      this.org$apache$spark$executor$Executor$$executorSource = new ExecutorSource(this.threadPool(), executorId, this.schemes());
      this.taskReaperPool = ThreadUtils$.MODULE$.newDaemonCachedThreadPool("Task reaper");
      this.org$apache$spark$executor$Executor$$taskReaperForTask = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.executorMetricsSource = (Option)(BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.METRICS_EXECUTORMETRICS_SOURCE_ENABLED())) ? new Some(new ExecutorMetricsSource()) : .MODULE$);
      if (!isLocal) {
         env.blockManager().initialize(this.conf().getAppId());
         env.metricsSystem().registerSource(this.org$apache$spark$executor$Executor$$executorSource());
         env.metricsSystem().registerSource(new JVMCPUSource());
         this.executorMetricsSource().foreach((x$3) -> {
            $anonfun$new$4(this, x$3);
            return BoxedUnit.UNIT;
         });
         env.metricsSystem().registerSource(env.blockManager().shuffleMetricsSource());
      } else {
         Executor$.MODULE$.executorSourceLocalModeOnly_$eq(this.org$apache$spark$executor$Executor$$executorSource());
      }

      this.userClassPathFirst = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_USER_CLASS_PATH_FIRST()));
      this.taskReaperEnabled = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.TASK_REAPER_ENABLED()));
      this.org$apache$spark$executor$Executor$$killOnFatalErrorDepth = BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.KILL_ON_FATAL_ERROR_DEPTH()));
      this.systemLoader = Utils$.MODULE$.getContextOrSparkClassLoader();
      this.defaultSessionState = this.org$apache$spark$executor$Executor$$newSessionState(new JobArtifactState("default", .MODULE$));
      this.isolatedSessionCache = CacheBuilder.newBuilder().maximumSize(100L).expireAfterAccess(30L, TimeUnit.MINUTES).removalListener(new RemovalListener() {
         // $FF: synthetic field
         private final Executor $outer;

         public void onRemoval(final RemovalNotification notification) {
            IsolatedSessionState state = (IsolatedSessionState)notification.getValue();
            scala.Predef..MODULE$.assert(!this.$outer.org$apache$spark$executor$Executor$$isDefaultState(state.sessionUUID()));
            File sessionBasedRoot = new File(SparkFiles$.MODULE$.getRootDirectory(), state.sessionUUID());
            if (sessionBasedRoot.isDirectory() && sessionBasedRoot.exists()) {
               Utils$.MODULE$.deleteRecursively(sessionBasedRoot);
            }

            this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Session evicted: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.SESSION_ID..MODULE$, state.sessionUUID())})))));
         }

         public {
            if (Executor.this == null) {
               throw null;
            } else {
               this.$outer = Executor.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      }).build();
      env.serializer().setDefaultClassLoader(this.defaultSessionState().replClassLoader());
      env.serializerManager().setDefaultClassLoader(this.defaultSessionState().replClassLoader());
      this.org$apache$spark$executor$Executor$$maxDirectResultSize = Math.min(BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.TASK_MAX_DIRECT_RESULT_SIZE())), (long)RpcUtils$.MODULE$.maxMessageSizeBytes(this.conf()));
      this.org$apache$spark$executor$Executor$$maxResultSize = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.MAX_RESULT_SIZE()));
      this.runningTasks = new ConcurrentHashMap();
      this.org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS = 10000L;
      this.killMarks = new ConcurrentHashMap();
      this.killMarkCleanupTask = new Runnable() {
         // $FF: synthetic field
         private final Executor $outer;

         public void run() {
            long oldest = System.currentTimeMillis() - this.$outer.org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS();
            Iterator iter = this.$outer.killMarks().entrySet().iterator();

            while(iter.hasNext()) {
               if (BoxesRunTime.unboxToLong(((Tuple3)((java.util.Map.Entry)iter.next()).getValue())._3()) < oldest) {
                  iter.remove();
               }
            }

         }

         public {
            if (Executor.this == null) {
               throw null;
            } else {
               this.$outer = Executor.this;
            }
         }
      };
      this.killMarkCleanupService = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("executor-kill-mark-cleanup");
      this.killMarkCleanupService().scheduleAtFixedRate(this.killMarkCleanupTask(), this.org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS(), this.org$apache$spark$executor$Executor$$KILL_MARK_TTL_MS(), TimeUnit.MILLISECONDS);
      this.HEARTBEAT_MAX_FAILURES = BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_MAX_FAILURES()));
      this.HEARTBEAT_DROP_ZEROES = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_DROP_ZERO_ACCUMULATOR_UPDATES()));
      this.HEARTBEAT_INTERVAL_MS = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL()));
      this.METRICS_POLLING_INTERVAL_MS = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_METRICS_POLLING_INTERVAL()));
      this.pollOnHeartbeat = this.METRICS_POLLING_INTERVAL_MS() <= 0L;
      this.metricsPoller = new ExecutorMetricsPoller(env.memoryManager(), this.METRICS_POLLING_INTERVAL_MS(), this.executorMetricsSource());
      this.heartbeater = new Heartbeater((JFunction0.mcV.sp)() -> this.reportHeartBeat(), "executor-heartbeater", this.HEARTBEAT_INTERVAL_MS());
      this.heartbeatReceiverRef = RpcUtils$.MODULE$.makeDriverRef(HeartbeatReceiver$.MODULE$.ENDPOINT_NAME(), this.conf(), env.rpcEnv());
      this.heartbeatFailures = 0;
      this.decommissioned = false;
      this.heartbeater().start();
      this.appStartTime = this.conf().getLong("spark.app.startTime", 0L);
      Seq var10 = (Seq)(new scala.collection.immutable..colon.colon("jar", new scala.collection.immutable..colon.colon("file", new scala.collection.immutable..colon.colon("archive", scala.collection.immutable.Nil..MODULE$)))).map((key) -> (Map)this.conf().getOption("spark.app.initial." + key + ".urls").map((urls) -> (Map)scala.collection.immutable.Map..MODULE$.apply(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])urls.split(",")), (url) -> new Tuple2(url, BoxesRunTime.boxToLong(this.appStartTime())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toImmutableArraySeq())).getOrElse(() -> scala.collection.immutable.Map..MODULE$.empty()));
      if (var10 != null) {
         SeqOps var11 = scala.package..MODULE$.Seq().unapplySeq(var10);
         if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var11) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11), 3) == 0) {
            Map initialUserJars = (Map)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11), 0);
            Map initialUserFiles = (Map)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11), 1);
            Map initialUserArchives = (Map)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11), 2);
            this.x$4 = new Tuple3(initialUserJars, initialUserFiles, initialUserArchives);
            this.initialUserJars = (Map)this.x$4._1();
            this.initialUserFiles = (Map)this.x$4._2();
            this.initialUserArchives = (Map)this.x$4._3();
            this.updateDependencies(this.initialUserFiles(), this.initialUserJars(), this.initialUserArchives(), this.defaultSessionState(), this.updateDependencies$default$5(), this.updateDependencies$default$6());
            this.plugins = (Option)Utils$.MODULE$.withContextClassLoader(this.defaultSessionState().replClassLoader(), () -> PluginContainer$.MODULE$.apply(this.org$apache$spark$executor$Executor$$env, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.resources).asJava()));
            if (!isLocal) {
               Utils$.MODULE$.withContextClassLoader(this.defaultSessionState().replClassLoader(), (JFunction0.mcV.sp)() -> this.org$apache$spark$executor$Executor$$env.initializeShuffleManager());
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            this.metricsPoller().start();
            return;
         }
      }

      throw new MatchError(var10);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class TaskRunner implements Runnable {
      private final ExecutorBackend execBackend;
      private final TaskDescription taskDescription;
      private final Option plugins;
      private final long taskId;
      private final String taskName;
      private final String threadName;
      private final Seq mdcProperties;
      private volatile Option reasonIfKilled;
      private volatile long threadId;
      @GuardedBy("TaskRunner.this")
      private boolean finished;
      private volatile long startGCTime;
      private volatile Task task;
      // $FF: synthetic field
      public final Executor $outer;

      public TaskDescription taskDescription() {
         return this.taskDescription;
      }

      private Option plugins() {
         return this.plugins;
      }

      public long taskId() {
         return this.taskId;
      }

      public String taskName() {
         return this.taskName;
      }

      public String threadName() {
         return this.threadName;
      }

      public Seq mdcProperties() {
         return this.mdcProperties;
      }

      private Option reasonIfKilled() {
         return this.reasonIfKilled;
      }

      private void reasonIfKilled_$eq(final Option x$1) {
         this.reasonIfKilled = x$1;
      }

      private long threadId() {
         return this.threadId;
      }

      private void threadId_$eq(final long x$1) {
         this.threadId = x$1;
      }

      public long getThreadId() {
         return this.threadId();
      }

      private boolean finished() {
         return this.finished;
      }

      private void finished_$eq(final boolean x$1) {
         this.finished = x$1;
      }

      public synchronized boolean isFinished() {
         return this.finished();
      }

      public long startGCTime() {
         return this.startGCTime;
      }

      public void startGCTime_$eq(final long x$1) {
         this.startGCTime = x$1;
      }

      public Task task() {
         return this.task;
      }

      public void task_$eq(final Task x$1) {
         this.task = x$1;
      }

      public void kill(final boolean interruptThread, final String reason) {
         this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor is trying to kill ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"interruptThread: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.INTERRUPT_THREAD..MODULE$, BoxesRunTime.boxToBoolean(interruptThread))})))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"reason: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)}))))));
         this.reasonIfKilled_$eq(new Some(reason));
         if (this.task() != null) {
            synchronized(this){}

            try {
               if (!this.finished()) {
                  this.task().kill(interruptThread, reason);
               }
            } catch (Throwable var5) {
               throw var5;
            }

         }
      }

      private synchronized void setTaskFinishedAndClearInterruptStatus() {
         this.finished_$eq(true);
         Thread.interrupted();
         this.notifyAll();
      }

      private Tuple2 collectAccumulatorsAndResetStatusOnFailure(final long taskStartTimeNs) {
         scala.Option..MODULE$.apply(this.task()).foreach((t) -> {
            $anonfun$collectAccumulatorsAndResetStatusOnFailure$1(this, taskStartTimeNs, t);
            return BoxedUnit.UNIT;
         });
         Seq accums = (Seq)scala.Option..MODULE$.apply(this.task()).map((x$10) -> x$10.collectAccumulatorUpdates(true)).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
         Seq accUpdates = (Seq)accums.map((acc) -> acc.toInfoUpdate());
         this.setTaskFinishedAndClearInterruptStatus();
         return new Tuple2(accums, accUpdates);
      }

      public void run() {
         Option var9 = this.taskDescription().artifacts().state();
         IsolatedSessionState var10000;
         if (var9 instanceof Some var10) {
            JobArtifactState jobArtifactState = (JobArtifactState)var10.value();
            var10000 = (IsolatedSessionState)this.org$apache$spark$executor$Executor$TaskRunner$$$outer().isolatedSessionCache().get(jobArtifactState.uuid(), () -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$newSessionState(jobArtifactState));
         } else {
            var10000 = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().defaultSessionState();
         }

         IsolatedSessionState isolatedSession = var10000;
         this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$setMDCForTask(this.taskName(), this.mdcProperties());
         this.threadId_$eq(Thread.currentThread().getId());
         Thread.currentThread().setName(this.threadName());
         ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
         TaskMemoryManager taskMemoryManager = new TaskMemoryManager(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.memoryManager(), this.taskId());
         long deserializeStartTimeNs = System.nanoTime();
         long deserializeStartCpuTime = threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() : 0L;
         Thread.currentThread().setContextClassLoader(isolatedSession.replClassLoader());
         SerializerInstance ser = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.closureSerializer().newInstance();
         this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Running ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())})))));
         this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.RUNNING(), this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$EMPTY_BYTE_BUFFER());
         long taskStartTimeNs = 0L;
         long taskStartCpu = 0L;
         this.startGCTime_$eq(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$computeTotalGcTime());
         boolean taskStarted = false;

         try {
            Executor$.MODULE$.taskDeserializationProps().set(this.taskDescription().properties());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().updateDependencies(this.taskDescription().artifacts().files(), this.taskDescription().artifacts().jars(), this.taskDescription().artifacts().archives(), isolatedSession, this.org$apache$spark$executor$Executor$TaskRunner$$$outer().updateDependencies$default$5(), this.org$apache$spark$executor$Executor$TaskRunner$$$outer().updateDependencies$default$6());
            Thread.currentThread().setContextClassLoader(isolatedSession.replClassLoader());
            this.task_$eq((Task)ser.deserialize(this.taskDescription().serializedTask(), Thread.currentThread().getContextClassLoader(), scala.reflect.ClassTag..MODULE$.apply(Task.class)));
            this.task().localProperties_$eq(this.taskDescription().properties());
            this.task().setTaskMemoryManager(taskMemoryManager);
            Option killReason = this.reasonIfKilled();
            if (killReason.isDefined()) {
               throw new TaskKilledException((String)killReason.get());
            }

            if (!this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$isLocal) {
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logDebug((Function0)(() -> {
                  String var10000 = this.taskName();
                  return var10000 + "'s epoch is " + this.task().epoch();
               }));
               ((MapOutputTrackerWorker)this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.mapOutputTracker()).updateEpoch(this.task().epoch());
            }

            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().metricsPoller().onTaskStart(this.taskId(), this.task().stageId(), this.task().stageAttemptId());
            taskStarted = true;
            taskStartTimeNs = System.nanoTime();
            taskStartCpu = threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() : 0L;
            BooleanRef threwException = BooleanRef.create(true);
            Map resources = (Map)this.taskDescription().resources().map((x0$1) -> {
               if (x0$1 != null) {
                  String rName = (String)x0$1._1();
                  Map addressesAmounts = (Map)x0$1._2();
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), new ResourceInformation(rName, (String[])((IterableOnceOps)addressesAmounts.keys().toSeq().sorted(scala.math.Ordering.String..MODULE$)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))));
               } else {
                  throw new MatchError(x0$1);
               }
            });
            Object value = Utils$.MODULE$.tryWithSafeFinally(() -> {
               Object res = this.task().run(this.taskId(), this.taskDescription().attemptNumber(), this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.metricsSystem(), this.taskDescription().cpus(), resources, this.plugins());
               threwException.elem = false;
               return res;
            }, (JFunction0.mcV.sp)() -> {
               Seq releasedLocks = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.blockManager().releaseAllLocksForTask(this.taskId());
               long freedMemory = taskMemoryManager.cleanUpAllAllocatedMemory();
               if (freedMemory > 0L && !threwException.elem) {
                  MessageWithContext errMsg = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Managed memory leak detected; size = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " bytes, ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(freedMemory)), new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))));
                  if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().conf().get(org.apache.spark.internal.config.package$.MODULE$.UNSAFE_EXCEPTION_ON_MEMORY_LEAK()))) {
                     throw org.apache.spark.SparkException..MODULE$.internalError(errMsg.message(), "EXECUTOR");
                  }

                  this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> errMsg));
               }

               if (releasedLocks.nonEmpty() && !threwException.elem) {
                  MessageWithContext errMsg = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " block locks"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.NUM_RELEASED_LOCKS..MODULE$, BoxesRunTime.boxToInteger(releasedLocks.size()))}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" were not released by ", "\\n"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())})))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.RELEASED_LOCKS..MODULE$, releasedLocks.mkString("[", ", ", "]"))}))));
                  if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().conf().get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_EXCEPTION_PIN_LEAK()))) {
                     throw org.apache.spark.SparkException..MODULE$.internalError(errMsg.message(), "EXECUTOR");
                  } else {
                     this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> errMsg));
                  }
               }
            });
            this.task().context().fetchFailed().foreach((fetchFailure) -> {
               $anonfun$run$9(this, fetchFailure);
               return BoxedUnit.UNIT;
            });
            long taskFinishNs = System.nanoTime();
            long taskFinishCpu = threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() : 0L;
            this.task().context().killTaskIfInterrupted();
            SerializerInstance resultSer = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.serializer().newInstance();
            long beforeSerializationNs = System.nanoTime();
            ChunkedByteBuffer valueByteBuffer = SerializerHelper$.MODULE$.serializeToChunkedBuffer(resultSer, value, SerializerHelper$.MODULE$.serializeToChunkedBuffer$default$3(), scala.reflect.ClassTag..MODULE$.Any());
            long afterSerializationNs = System.nanoTime();
            this.task().metrics().setExecutorDeserializeTime(TimeUnit.NANOSECONDS.toMillis(taskStartTimeNs - deserializeStartTimeNs + this.task().executorDeserializeTimeNs()));
            this.task().metrics().setExecutorDeserializeCpuTime(taskStartCpu - deserializeStartCpuTime + this.task().executorDeserializeCpuTime());
            this.task().metrics().setExecutorRunTime(TimeUnit.NANOSECONDS.toMillis((taskFinishNs - taskStartTimeNs) * (long)this.taskDescription().cpus() - this.task().executorDeserializeTimeNs()));
            this.task().metrics().setExecutorCpuTime(taskFinishCpu - taskStartCpu - this.task().executorDeserializeCpuTime());
            this.task().metrics().setJvmGCTime(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$computeTotalGcTime() - this.startGCTime());
            this.task().metrics().setResultSerializationTime(TimeUnit.NANOSECONDS.toMillis(afterSerializationNs - beforeSerializationNs));
            this.task().metrics().setPeakOnHeapExecutionMemory(taskMemoryManager.getPeakOnHeapExecutionMemory());
            this.task().metrics().setPeakOffHeapExecutionMemory(taskMemoryManager.getPeakOffHeapExecutionMemory());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_CPU_TIME().inc(this.task().metrics().executorCpuTime());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_RUN_TIME().inc(this.task().metrics().executorRunTime());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_JVM_GC_TIME().inc(this.task().metrics().jvmGCTime());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_DESERIALIZE_TIME().inc(this.task().metrics().executorDeserializeTime());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_DESERIALIZE_CPU_TIME().inc(this.task().metrics().executorDeserializeCpuTime());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_RESULT_SERIALIZE_TIME().inc(this.task().metrics().resultSerializationTime());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_INPUT_BYTES_READ().inc(this.task().metrics().inputMetrics().bytesRead());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_INPUT_RECORDS_READ().inc(this.task().metrics().inputMetrics().recordsRead());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_OUTPUT_BYTES_WRITTEN().inc(this.task().metrics().outputMetrics().bytesWritten());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_OUTPUT_RECORDS_WRITTEN().inc(this.task().metrics().outputMetrics().recordsWritten());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_RESULT_SIZE().inc(this.task().metrics().resultSize());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_DISK_BYTES_SPILLED().inc(this.task().metrics().diskBytesSpilled());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().METRIC_MEMORY_BYTES_SPILLED().inc(this.task().metrics().memoryBytesSpilled());
            this.incrementShuffleMetrics(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource(), this.task().metrics());
            Task qual$1 = this.task();
            boolean x$1 = qual$1.collectAccumulatorUpdates$default$1();
            Seq accumUpdates = qual$1.collectAccumulatorUpdates(x$1);
            long[] metricPeaks = this.org$apache$spark$executor$Executor$TaskRunner$$$outer().metricsPoller().getTaskMetricPeaks(this.taskId());
            DirectTaskResult directResult = new DirectTaskResult(valueByteBuffer, accumUpdates, metricPeaks);
            ChunkedByteBuffer serializedDirectResult = SerializerHelper$.MODULE$.serializeToChunkedBuffer(ser, directResult, valueByteBuffer.size() + (long)(accumUpdates.size() * 32) + (long)(metricPeaks.length * 8), scala.reflect.ClassTag..MODULE$.apply(DirectTaskResult.class));
            long resultSize = serializedDirectResult.size();
            ByteBuffer var105;
            if (this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$maxResultSize() > 0L && resultSize > this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$maxResultSize()) {
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Result is larger than maxResultSize "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " > "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.RESULT_SIZE_BYTES..MODULE$, Utils$.MODULE$.bytesToString(resultSize))})))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "), "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.RESULT_SIZE_BYTES_MAX..MODULE$, Utils$.MODULE$.bytesToString(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$maxResultSize()))})))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"dropping it."})))).log(scala.collection.immutable.Nil..MODULE$))));
               var105 = ser.serialize(new IndirectTaskResult(new TaskResultBlockId(this.taskId()), resultSize), scala.reflect.ClassTag..MODULE$.apply(IndirectTaskResult.class));
            } else if (resultSize > this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$maxDirectResultSize()) {
               TaskResultBlockId blockId = new TaskResultBlockId(this.taskId());
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.blockManager().putBytes(blockId, serializedDirectResult, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK_SER(), this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.blockManager().putBytes$default$4(), scala.reflect.ClassTag..MODULE$.Nothing());
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " bytes result sent via BlockManager)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(resultSize))}))))));
               var105 = ser.serialize(new IndirectTaskResult(blockId, resultSize), scala.reflect.ClassTag..MODULE$.apply(IndirectTaskResult.class));
            } else {
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " bytes result sent to driver"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(resultSize))}))))));
               var105 = serializedDirectResult.toByteBuffer();
            }

            ByteBuffer serializedResult = var105;
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$executorSource().SUCCEEDED_TASKS().inc(1L);
            this.setTaskFinishedAndClearInterruptStatus();
            this.plugins().foreach((x$11) -> {
               $anonfun$run$14(x$11);
               return BoxedUnit.UNIT;
            });
            this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.FINISHED(), serializedResult);
         } catch (Throwable var93) {
            if (var93 instanceof TaskKilledException var50) {
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor killed ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" reason: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, var50.reason())}))))));
               Tuple2 var52 = this.collectAccumulatorsAndResetStatusOnFailure(taskStartTimeNs);
               if (var52 == null) {
                  throw new MatchError(var52);
               }

               Seq accums = (Seq)var52._1();
               Seq accUpdates = (Seq)var52._2();
               Tuple2 var51 = new Tuple2(accums, accUpdates);
               Seq accumsx = (Seq)var51._1();
               Seq accUpdatesx = (Seq)var51._2();
               ArraySeq metricPeaks = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().metricsPoller().getTaskMetricPeaks(this.taskId())).toImmutableArraySeq();
               TaskKilled reason = new TaskKilled(var50.reason(), accUpdatesx, accumsx, metricPeaks);
               this.plugins().foreach((x$13) -> {
                  $anonfun$run$16(reason, x$13);
                  return BoxedUnit.UNIT;
               });
               this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.KILLED(), ser.serialize(reason, scala.reflect.ClassTag..MODULE$.apply(TaskKilled.class)));
               BoxedUnit var104 = BoxedUnit.UNIT;
            } else {
               boolean var97;
               if (var93 instanceof InterruptedException) {
                  var97 = true;
               } else {
                  label327: {
                     if (var93 != null) {
                        Option var59 = scala.util.control.NonFatal..MODULE$.unapply(var93);
                        if (!var59.isEmpty()) {
                           var97 = true;
                           break label327;
                        }
                     }

                     var97 = false;
                  }
               }

               if (var97 && this.task() != null && this.task().reasonIfKilled().isDefined()) {
                  String killReason = (String)this.task().reasonIfKilled().getOrElse(() -> "unknown reason");
                  this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor interrupted and killed ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" reason: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, killReason)}))))));
                  Tuple2 var62 = this.collectAccumulatorsAndResetStatusOnFailure(taskStartTimeNs);
                  if (var62 == null) {
                     throw new MatchError(var62);
                  }

                  Seq accums = (Seq)var62._1();
                  Seq accUpdates = (Seq)var62._2();
                  Tuple2 var61 = new Tuple2(accums, accUpdates);
                  Seq accumsxxx = (Seq)var61._1();
                  Seq accUpdatesxxx = (Seq)var61._2();
                  ArraySeq metricPeaksx = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().metricsPoller().getTaskMetricPeaks(this.taskId())).toImmutableArraySeq();
                  TaskKilled reasonx = new TaskKilled(killReason, accUpdatesxxx, accumsxxx, metricPeaksx);
                  this.plugins().foreach((x$15) -> {
                     $anonfun$run$19(reasonx, x$15);
                     return BoxedUnit.UNIT;
                  });
                  this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.KILLED(), ser.serialize(reasonx, scala.reflect.ClassTag..MODULE$.apply(TaskKilled.class)));
                  BoxedUnit var103 = BoxedUnit.UNIT;
               } else if (var93 != null && this.hasFetchFailure() && !Executor$.MODULE$.isFatalError(var93, this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$killOnFatalErrorDepth())) {
                  TaskFailedReason reason = ((FetchFailedException)this.task().context().fetchFailed().get()).toTaskFailedReason();
                  if (!(var93 instanceof FetchFailedException)) {
                     this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " encountered a "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())}))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, FetchFailedException.class.getName())})))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"and failed, but the "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, FetchFailedException.class.getName())})))).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was hidden by another exception. Spark is handling this like a fetch failure "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"and ignoring the other exception: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var93)}))))));
                  }

                  this.setTaskFinishedAndClearInterruptStatus();
                  this.plugins().foreach((x$16) -> {
                     $anonfun$run$21(reason, x$16);
                     return BoxedUnit.UNIT;
                  });
                  this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.FAILED(), ser.serialize(reason, scala.reflect.ClassTag..MODULE$.apply(TaskFailedReason.class)));
                  BoxedUnit var102 = BoxedUnit.UNIT;
               } else {
                  if (var93 != null) {
                     Option var71 = CausedBy$.MODULE$.unapply(var93);
                     if (!var71.isEmpty()) {
                        Throwable cDE = (Throwable)var71.get();
                        if (cDE instanceof CommitDeniedException) {
                           CommitDeniedException var73 = (CommitDeniedException)cDE;
                           TaskCommitDenied reason = var73.toTaskCommitDeniedReason();
                           this.setTaskFinishedAndClearInterruptStatus();
                           this.plugins().foreach((x$17) -> {
                              $anonfun$run$22(reason, x$17);
                              return BoxedUnit.UNIT;
                           });
                           this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.KILLED(), ser.serialize(reason, scala.reflect.ClassTag..MODULE$.apply(TaskCommitDenied.class)));
                           BoxedUnit var101 = BoxedUnit.UNIT;
                           return;
                        }
                     }
                  }

                  if (var93 != null && this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$env.isStopped()) {
                     this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception in ", ": ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName()), new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var93.getMessage())})))));
                     BoxedUnit var100 = BoxedUnit.UNIT;
                  } else {
                     if (var93 == null) {
                        throw var93;
                     }

                     this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception in ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName())})))), var93);
                     if (!ShutdownHookManager$.MODULE$.inShutdown()) {
                        Tuple2 var78 = this.collectAccumulatorsAndResetStatusOnFailure(taskStartTimeNs);
                        if (var78 == null) {
                           throw new MatchError(var78);
                        }

                        Seq accums = (Seq)var78._1();
                        Seq accUpdates = (Seq)var78._2();
                        Tuple2 var77 = new Tuple2(accums, accUpdates);
                        Seq accumsxxxxx = (Seq)var77._1();
                        Seq accUpdatesxxxxx = (Seq)var77._2();
                        ArraySeq metricPeaksxx = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.org$apache$spark$executor$Executor$TaskRunner$$$outer().metricsPoller().getTaskMetricPeaks(this.taskId())).toImmutableArraySeq();
                        Tuple2 var85 = liftedTree1$1(var93, accUpdatesxxxxx, accumsxxxxx, metricPeaksxx, ser);
                        if (var85 == null) {
                           throw new MatchError(var85);
                        }

                        ExceptionFailure taskFailureReason = (ExceptionFailure)var85._1();
                        ByteBuffer serializedTaskFailureReason = (ByteBuffer)var85._2();
                        Tuple2 var84 = new Tuple2(taskFailureReason, serializedTaskFailureReason);
                        ExceptionFailure taskFailureReasonx = (ExceptionFailure)var84._1();
                        ByteBuffer serializedTaskFailureReasonx = (ByteBuffer)var84._2();
                        this.setTaskFinishedAndClearInterruptStatus();
                        this.plugins().foreach((x$20) -> {
                           $anonfun$run$25(taskFailureReasonx, x$20);
                           return BoxedUnit.UNIT;
                        });
                        this.execBackend.statusUpdate(this.taskId(), TaskState$.MODULE$.FAILED(), serializedTaskFailureReasonx);
                     } else {
                        this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logInfo((Function0)(() -> "Not reporting error to driver during JVM shutdown."));
                     }

                     if (Executor$.MODULE$.isFatalError(var93, this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$killOnFatalErrorDepth())) {
                        this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), var93);
                        BoxedUnit var98 = BoxedUnit.UNIT;
                     } else {
                        BoxedUnit var99 = BoxedUnit.UNIT;
                     }
                  }
               }
            }
         } finally {
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$cleanMDCForTask(this.taskName(), this.mdcProperties());
            this.org$apache$spark$executor$Executor$TaskRunner$$$outer().runningTasks().remove(BoxesRunTime.boxToLong(this.taskId()));
            if (taskStarted) {
               this.org$apache$spark$executor$Executor$TaskRunner$$$outer().metricsPoller().onTaskCompletion(this.taskId(), this.task().stageId(), this.task().stageAttemptId());
            }

         }

      }

      private void incrementShuffleMetrics(final ExecutorSource executorSource, final TaskMetrics metrics) {
         executorSource.METRIC_SHUFFLE_FETCH_WAIT_TIME().inc(metrics.shuffleReadMetrics().fetchWaitTime());
         executorSource.METRIC_SHUFFLE_WRITE_TIME().inc(metrics.shuffleWriteMetrics().writeTime());
         executorSource.METRIC_SHUFFLE_TOTAL_BYTES_READ().inc(metrics.shuffleReadMetrics().totalBytesRead());
         executorSource.METRIC_SHUFFLE_REMOTE_BYTES_READ().inc(metrics.shuffleReadMetrics().remoteBytesRead());
         executorSource.METRIC_SHUFFLE_REMOTE_BYTES_READ_TO_DISK().inc(metrics.shuffleReadMetrics().remoteBytesReadToDisk());
         executorSource.METRIC_SHUFFLE_LOCAL_BYTES_READ().inc(metrics.shuffleReadMetrics().localBytesRead());
         executorSource.METRIC_SHUFFLE_RECORDS_READ().inc(metrics.shuffleReadMetrics().recordsRead());
         executorSource.METRIC_SHUFFLE_REMOTE_BLOCKS_FETCHED().inc(metrics.shuffleReadMetrics().remoteBlocksFetched());
         executorSource.METRIC_SHUFFLE_LOCAL_BLOCKS_FETCHED().inc(metrics.shuffleReadMetrics().localBlocksFetched());
         executorSource.METRIC_SHUFFLE_REMOTE_REQS_DURATION().inc(metrics.shuffleReadMetrics().remoteReqsDuration());
         executorSource.METRIC_SHUFFLE_BYTES_WRITTEN().inc(metrics.shuffleWriteMetrics().bytesWritten());
         executorSource.METRIC_SHUFFLE_RECORDS_WRITTEN().inc(metrics.shuffleWriteMetrics().recordsWritten());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_CORRUPT_MERGED_BLOCK_CHUNKS().inc(metrics.shuffleReadMetrics().corruptMergedBlockChunks());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_FETCH_FALLBACK_COUNT().inc(metrics.shuffleReadMetrics().mergedFetchFallbackCount());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BLOCKS_FETCHED().inc(metrics.shuffleReadMetrics().remoteMergedBlocksFetched());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BLOCKS_FETCHED().inc(metrics.shuffleReadMetrics().localMergedBlocksFetched());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_CHUNKS_FETCHED().inc(metrics.shuffleReadMetrics().remoteMergedChunksFetched());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_CHUNKS_FETCHED().inc(metrics.shuffleReadMetrics().localMergedChunksFetched());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BYTES_READ().inc(metrics.shuffleReadMetrics().remoteMergedBytesRead());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BYTES_READ().inc(metrics.shuffleReadMetrics().localMergedBytesRead());
         executorSource.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_REQS_DURATION().inc(metrics.shuffleReadMetrics().remoteMergedReqsDuration());
      }

      private boolean hasFetchFailure() {
         return this.task() != null && this.task().context() != null && this.task().context().fetchFailed().isDefined();
      }

      public Option theadDump() {
         return Utils$.MODULE$.getThreadDumpForThread(this.getThreadId());
      }

      // $FF: synthetic method
      public Executor org$apache$spark$executor$Executor$TaskRunner$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$mdcProperties$1(final Tuple2 x$7) {
         return ((String)x$7._1()).startsWith("mdc.");
      }

      // $FF: synthetic method
      public static final void $anonfun$collectAccumulatorsAndResetStatusOnFailure$1(final TaskRunner $this, final long taskStartTimeNs$1, final Task t) {
         t.metrics().setExecutorRunTime(TimeUnit.NANOSECONDS.toMillis(taskStartTimeNs$1 > 0L ? (System.nanoTime() - taskStartTimeNs$1) * (long)$this.taskDescription().cpus() : 0L));
         t.metrics().setJvmGCTime($this.org$apache$spark$executor$Executor$TaskRunner$$$outer().org$apache$spark$executor$Executor$$computeTotalGcTime() - $this.startGCTime());
      }

      // $FF: synthetic method
      public static final void $anonfun$run$9(final TaskRunner $this, final FetchFailedException fetchFailure) {
         $this.org$apache$spark$executor$Executor$TaskRunner$$$outer().logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " completed successfully though internally "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, $this.taskName())}))).$plus($this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"it encountered unrecoverable fetch failures! Most likely this means user code "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is incorrectly swallowing Spark's internal "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.org$apache$spark$executor$Executor$TaskRunner$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, FetchFailedException.class)}))))), fetchFailure);
      }

      // $FF: synthetic method
      public static final void $anonfun$run$14(final PluginContainer x$11) {
         x$11.onTaskSucceeded();
      }

      // $FF: synthetic method
      public static final void $anonfun$run$16(final TaskKilled reason$3, final PluginContainer x$13) {
         x$13.onTaskFailed(reason$3);
      }

      // $FF: synthetic method
      public static final void $anonfun$run$19(final TaskKilled reason$4, final PluginContainer x$15) {
         x$15.onTaskFailed(reason$4);
      }

      // $FF: synthetic method
      public static final void $anonfun$run$21(final TaskFailedReason reason$5, final PluginContainer x$16) {
         x$16.onTaskFailed(reason$5);
      }

      // $FF: synthetic method
      public static final void $anonfun$run$22(final TaskCommitDenied reason$6, final PluginContainer x$17) {
         x$17.onTaskFailed(reason$6);
      }

      // $FF: synthetic method
      private static final Tuple2 liftedTree1$1(final Throwable x26$1, final Seq accUpdates$1, final Seq accums$1, final ArraySeq metricPeaks$1, final SerializerInstance ser$1) {
         Tuple2 var10000;
         try {
            ExceptionFailure ef = (new ExceptionFailure(x26$1, accUpdates$1)).withAccums(accums$1).withMetricPeaks(metricPeaks$1);
            var10000 = new Tuple2(ef, ser$1.serialize(ef, scala.reflect.ClassTag..MODULE$.apply(ExceptionFailure.class)));
         } catch (NotSerializableException var7) {
            ExceptionFailure ef = (new ExceptionFailure(x26$1, accUpdates$1, false)).withAccums(accums$1).withMetricPeaks(metricPeaks$1);
            var10000 = new Tuple2(ef, ser$1.serialize(ef, scala.reflect.ClassTag..MODULE$.apply(ExceptionFailure.class)));
         }

         return var10000;
      }

      // $FF: synthetic method
      public static final void $anonfun$run$25(final ExceptionFailure taskFailureReason$1, final PluginContainer x$20) {
         x$20.onTaskFailed(taskFailureReason$1);
      }

      public TaskRunner(final ExecutorBackend execBackend, final TaskDescription taskDescription, final Option plugins) {
         this.execBackend = execBackend;
         this.taskDescription = taskDescription;
         this.plugins = plugins;
         if (Executor.this == null) {
            throw null;
         } else {
            this.$outer = Executor.this;
            super();
            this.taskId = taskDescription.taskId();
            this.taskName = taskDescription.name();
            this.threadName = "Executor task launch worker for " + this.taskName();
            this.mdcProperties = ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(taskDescription.properties()).asScala().filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$mdcProperties$1(x$7)))).toSeq();
            this.reasonIfKilled = .MODULE$;
            this.threadId = -1L;
            this.finished = false;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class TaskReaper implements Runnable {
      private final TaskRunner taskRunner;
      private final boolean interruptThread;
      private final String reason;
      private final long taskId;
      private final long killPollingIntervalMs;
      private final long killTimeoutNs;
      private final boolean takeThreadDump;
      // $FF: synthetic field
      public final Executor $outer;

      public boolean interruptThread() {
         return this.interruptThread;
      }

      public String reason() {
         return this.reason;
      }

      public void run() {
         this.org$apache$spark$executor$Executor$TaskReaper$$$outer().org$apache$spark$executor$Executor$$setMDCForTask(this.taskRunner.taskName(), this.taskRunner.mdcProperties());
         long startTimeNs = System.nanoTime();

         try {
            this.taskRunner.kill(this.interruptThread(), this.reason());
            boolean finished = false;

            while(!finished && !this.timeoutExceeded$1(startTimeNs)) {
               synchronized(this.taskRunner){}

               try {
                  if (this.taskRunner.isFinished()) {
                     finished = true;
                  } else {
                     this.taskRunner.wait(this.killPollingIntervalMs);
                  }
               } catch (Throwable var36) {
                  throw var36;
               }

               if (this.taskRunner.isFinished()) {
                  finished = true;
               } else {
                  long elapsedTimeMs = TimeUnit.NANOSECONDS.toMillis(elapsedTimeNs$1(startTimeNs));
                  this.org$apache$spark$executor$Executor$TaskReaper$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killed task ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(this.taskId))}))).$plus(this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is still running after ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(elapsedTimeMs))}))))));
                  if (this.takeThreadDump) {
                     try {
                        this.taskRunner.theadDump().foreach((thread) -> {
                           $anonfun$run$28(this, thread);
                           return BoxedUnit.UNIT;
                        });
                     } catch (Throwable var37) {
                        if (var37 == null || !scala.util.control.NonFatal..MODULE$.apply(var37)) {
                           throw var37;
                        }

                        this.org$apache$spark$executor$Executor$TaskReaper$$$outer().logWarning((Function0)(() -> "Exception thrown while obtaining thread dump: "), var37);
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }
                  }
               }
            }

            if (!this.taskRunner.isFinished() && this.timeoutExceeded$1(startTimeNs)) {
               long killTimeoutMs = TimeUnit.NANOSECONDS.toMillis(this.killTimeoutNs);
               if (!this.org$apache$spark$executor$Executor$TaskReaper$$$outer().org$apache$spark$executor$Executor$$isLocal) {
                  throw new KilledByTaskReaperException("Killing executor JVM because killed task " + this.taskId + " could not be stopped within " + killTimeoutMs + " ms.");
               }

               this.org$apache$spark$executor$Executor$TaskReaper$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killed task ", " could not be stopped within "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(this.taskId))}))).$plus(this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms; "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(killTimeoutMs))})))).$plus(this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"not killing JVM because we are running in local mode."})))).log(scala.collection.immutable.Nil..MODULE$))));
            }
         } finally {
            this.org$apache$spark$executor$Executor$TaskReaper$$$outer().org$apache$spark$executor$Executor$$cleanMDCForTask(this.taskRunner.taskName(), this.taskRunner.mdcProperties());
            synchronized(this.org$apache$spark$executor$Executor$TaskReaper$$$outer().org$apache$spark$executor$Executor$$taskReaperForTask()){}

            try {
               this.org$apache$spark$executor$Executor$TaskReaper$$$outer().org$apache$spark$executor$Executor$$taskReaperForTask().get(BoxesRunTime.boxToLong(this.taskId)).foreach((taskReaperInMap) -> taskReaperInMap == this ? this.org$apache$spark$executor$Executor$TaskReaper$$$outer().org$apache$spark$executor$Executor$$taskReaperForTask().remove(BoxesRunTime.boxToLong(this.taskId)) : BoxedUnit.UNIT);
            } catch (Throwable var35) {
               throw var35;
            }

         }

      }

      // $FF: synthetic method
      public Executor org$apache$spark$executor$Executor$TaskReaper$$$outer() {
         return this.$outer;
      }

      private static final long elapsedTimeNs$1(final long startTimeNs$1) {
         return System.nanoTime() - startTimeNs$1;
      }

      private final boolean timeoutExceeded$1(final long startTimeNs$1) {
         return this.killTimeoutNs > 0L && elapsedTimeNs$1(startTimeNs$1) > this.killTimeoutNs;
      }

      // $FF: synthetic method
      public static final void $anonfun$run$28(final TaskReaper $this, final ThreadStackTrace thread) {
         label14: {
            String var10000 = thread.threadName();
            String var2 = $this.taskRunner.threadName();
            if (var10000 == null) {
               if (var2 == null) {
                  break label14;
               }
            } else if (var10000.equals(var2)) {
               break label14;
            }

            return;
         }

         $this.org$apache$spark$executor$Executor$TaskReaper$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Thread dump from task ", ":\\n"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong($this.taskId))}))).$plus($this.org$apache$spark$executor$Executor$TaskReaper$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new org.apache.spark.internal.MDC[]{new org.apache.spark.internal.MDC(org.apache.spark.internal.LogKeys.THREAD..MODULE$, thread.toString())}))))));
      }

      public TaskReaper(final TaskRunner taskRunner, final boolean interruptThread, final String reason) {
         this.taskRunner = taskRunner;
         this.interruptThread = interruptThread;
         this.reason = reason;
         if (Executor.this == null) {
            throw null;
         } else {
            this.$outer = Executor.this;
            super();
            this.taskId = taskRunner.taskId();
            this.killPollingIntervalMs = BoxesRunTime.unboxToLong(Executor.this.conf().get(org.apache.spark.internal.config.package$.MODULE$.TASK_REAPER_POLLING_INTERVAL()));
            this.killTimeoutNs = TimeUnit.MILLISECONDS.toNanos(BoxesRunTime.unboxToLong(Executor.this.conf().get(org.apache.spark.internal.config.package$.MODULE$.TASK_REAPER_KILL_TIMEOUT())));
            this.takeThreadDump = BoxesRunTime.unboxToBoolean(Executor.this.conf().get(org.apache.spark.internal.config.package$.MODULE$.TASK_REAPER_THREAD_DUMP()));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
