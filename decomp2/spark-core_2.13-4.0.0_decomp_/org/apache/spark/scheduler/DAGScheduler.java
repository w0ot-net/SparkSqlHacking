package org.apache.spark.scheduler;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.Dependency;
import org.apache.spark.ExceptionFailure;
import org.apache.spark.ExecutorLostFailure;
import org.apache.spark.FetchFailed;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.JobArtifactSet$;
import org.apache.spark.MapOutputStatistics;
import org.apache.spark.MapOutputTrackerMaster;
import org.apache.spark.NarrowDependency;
import org.apache.spark.Partition;
import org.apache.spark.Resubmitted$;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkDriverExecutionException;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.SparkThrowable;
import org.apache.spark.Success$;
import org.apache.spark.TaskCommitDenied;
import org.apache.spark.TaskEndReason;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.TaskKilled;
import org.apache.spark.TaskResultLost$;
import org.apache.spark.UnknownReason$;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.executor.TaskMetrics$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.network.shuffle.BlockStoreClient;
import org.apache.spark.network.shuffle.MergeFinalizerListener;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.partial.ApproximateActionListener;
import org.apache.spark.partial.ApproximateEvaluator;
import org.apache.spark.partial.PartialResult;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDDCheckpointData$;
import org.apache.spark.resource.ExecutorResourceRequest;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.TaskResourceProfile;
import org.apache.spark.resource.TaskResourceRequest;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerId$;
import org.apache.spark.storage.BlockManagerMaster;
import org.apache.spark.storage.BlockManagerMessages;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.AccumulatorContext$;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CallSite;
import org.apache.spark.util.Clock;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.guava.util.concurrent.Futures;
import org.sparkproject.guava.util.concurrent.ListenableFuture;
import org.sparkproject.guava.util.concurrent.SettableFuture;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.None.;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ListBuffer;
import scala.concurrent.duration.FiniteDuration;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyLong;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005-\ra!CAO\u0003?\u0003\u00111UAX\u00111\tI\r\u0001BC\u0002\u0013\u0005\u0011qTAf\u0011)\t9\u000e\u0001B\u0001B\u0003%\u0011Q\u001a\u0005\r\u00033\u0004!Q1A\u0005\u0002\u0005}\u00151\u001c\u0005\u000b\u0003K\u0004!\u0011!Q\u0001\n\u0005u\u0007BCAt\u0001\t\u0005\t\u0015!\u0003\u0002j\"Q\u0011q\u001e\u0001\u0003\u0002\u0003\u0006I!!=\t\u0015\u0005]\bA!A!\u0002\u0013\tI\u0010\u0003\u0006\u0003\u0006\u0001\u0011\t\u0011)A\u0005\u0005\u000fA!B!\u0004\u0001\u0005\u0003\u0005\u000b\u0011\u0002B\b\u0011\u001d\u0011Y\u0002\u0001C\u0001\u0005;AqAa\u0007\u0001\t\u0003\u0011y\u0003C\u0004\u0003\u001c\u0001!\tA!\u000e\t\u0017\te\u0002A1A\u0005\u0002\u0005\r&1\b\u0005\t\u0005\u0007\u0002\u0001\u0015!\u0003\u0003>!Y!Q\t\u0001C\u0002\u0013\u0005\u0011q\u0014B$\u0011!\u0011y\u0006\u0001Q\u0001\n\t%\u0003\"\u0003B1\u0001\u0011\u0005\u0011q\u0014B2\u0011%\u0011Y\u0007\u0001b\u0001\n\u0013\u00119\u0005\u0003\u0005\u0003n\u0001\u0001\u000b\u0011\u0002B%\u0011-\u0011y\u0007\u0001b\u0001\n\u0003\tyJ!\u001d\t\u0011\t%\u0005\u0001)A\u0005\u0005gB1Ba#\u0001\u0005\u0004%\t!a(\u0003\u000e\"A!q\u0013\u0001!\u0002\u0013\u0011y\tC\u0006\u0003\u001a\u0002\u0011\r\u0011\"\u0001\u0002 \nm\u0005\u0002\u0003BS\u0001\u0001\u0006IA!(\t\u0017\t\u001d\u0006A1A\u0005\u0002\u0005}%\u0011\u0016\u0005\t\u0005g\u0003\u0001\u0015!\u0003\u0003,\"Y!Q\u0017\u0001C\u0002\u0013\u0005\u0011q\u0014B\\\u0011!\u0011Y\f\u0001Q\u0001\n\te\u0006b\u0003B_\u0001\t\u0007I\u0011AAP\u0005oC\u0001Ba0\u0001A\u0003%!\u0011\u0018\u0005\f\u0005\u0003\u0004!\u0019!C\u0001\u0003?\u00139\f\u0003\u0005\u0003D\u0002\u0001\u000b\u0011\u0002B]\u0011-\u0011)\r\u0001b\u0001\n\u0003\tyJa2\t\u0011\t-\u0007\u0001)A\u0005\u0005\u0013D1B!4\u0001\u0005\u0004%\t!a(\u0003P\"A!Q\u001e\u0001!\u0002\u0013\u0011\t\u000eC\u0005\u0003p\u0002\u0011\r\u0011\"\u0003\u0003r\"A11\u0003\u0001!\u0002\u0013\u0011\u0019\u0010C\u0005\u0004\u0016\u0001\u0011\r\u0011\"\u0003\u0004\u0018!A1\u0011\u0005\u0001!\u0002\u0013\u0019I\u0002C\u0005\u0004$\u0001\u0011\r\u0011\"\u0003\u0004\u0018!A1Q\u0005\u0001!\u0002\u0013\u0019I\u0002C\u0006\u0004(\u0001\u0011\r\u0011\"\u0001\u0002 \u000e%\u0002\u0002CB\u0019\u0001\u0001\u0006Iaa\u000b\t\u0013\rM\u0002A1A\u0005\n\rU\u0002\u0002CB\"\u0001\u0001\u0006Iaa\u000e\t\u0013\r\u0015\u0003A1A\u0005\n\r\u001d\u0003\u0002CB(\u0001\u0001\u0006Ia!\u0013\t\u0013\rE\u0003A1A\u0005\n\r\u001d\u0003\u0002CB*\u0001\u0001\u0006Ia!\u0013\t\u0017\rU\u0003A1A\u0005\u0002\u0005}5q\t\u0005\t\u0007/\u0002\u0001\u0015!\u0003\u0004J!Y1\u0011\f\u0001C\u0002\u0013\u0005\u0011q\u0014B2\u0011!\u0019Y\u0006\u0001Q\u0001\n\t\u0015\u0004bCB/\u0001\t\u0007I\u0011AAP\u0005GB\u0001ba\u0018\u0001A\u0003%!Q\r\u0005\f\u0007C\u0002!\u0019!C\u0001\u0003?\u001b9\u0005\u0003\u0005\u0004d\u0001\u0001\u000b\u0011BB%\u0011-\u0019)\u0007\u0001b\u0001\n\u0003\tyja\u001a\t\u0011\rE\u0004\u0001)A\u0005\u0007SB\u0011ba\u001d\u0001\u0005\u0004%Ia!\u001e\t\u0011\r]\u0004\u0001)A\u0005\u00077A\u0011b!\u001f\u0001\u0005\u0004%IAa\u0019\t\u0011\rm\u0004\u0001)A\u0005\u0005KB\u0011b! \u0001\u0005\u0004%Iaa \t\u0011\r\u001d\u0005\u0001)A\u0005\u0007\u0003C1b!#\u0001\u0001\u0004%\t!a)\u0004\f\"Y11\u0013\u0001A\u0002\u0013\u0005\u00111UBK\u0011!\u0019\t\u000b\u0001Q!\n\r5\u0005\"CBR\u0001\u0011\u0005\u00111UBS\u0011%\u0019Y\u000b\u0001b\u0001\n\u0013\u00199\u0005\u0003\u0005\u0004.\u0002\u0001\u000b\u0011BB%\u0011%\u0019y\u000b\u0001b\u0001\n\u0013\u0019\t\f\u0003\u0005\u0004B\u0002\u0001\u000b\u0011BBZ\u0011%\u0019\u0019\r\u0001b\u0001\n\u0013\u0019)\b\u0003\u0005\u0004F\u0002\u0001\u000b\u0011BB\u000e\u0011%\u00199\r\u0001b\u0001\n\u0013\u0019)\b\u0003\u0005\u0004J\u0002\u0001\u000b\u0011BB\u000e\u0011%\u0019Y\r\u0001b\u0001\n\u0013\u0019)\b\u0003\u0005\u0004N\u0002\u0001\u000b\u0011BB\u000e\u0011%\u0019y\r\u0001b\u0001\n\u0013\u0019\t\u000e\u0003\u0005\u0004Z\u0002\u0001\u000b\u0011BBj\u0011%\u0019Y\u000e\u0001b\u0001\n\u0013\u0011\u0019\u0007\u0003\u0005\u0004^\u0002\u0001\u000b\u0011\u0002B3\u0011%\u0019y\u000e\u0001b\u0001\n\u0013\u0011\u0019\u0007\u0003\u0005\u0004b\u0002\u0001\u000b\u0011\u0002B3\u0011)\u0019\u0019\u000f\u0001EC\u0002\u0013%1Q\u001d\u0005\n\u0007{\u0004!\u0019!C\u0005\u0007\u007fB\u0001ba@\u0001A\u0003%1\u0011\u0011\u0005\n\t\u0003\u0001!\u0019!C\u0005\t\u0007A\u0001\u0002b\u0003\u0001A\u0003%AQ\u0001\u0005\n\t\u001b\u0001!\u0019!C\u0005\u0007\u000fB\u0001\u0002b\u0004\u0001A\u0003%1\u0011\n\u0005\n\t#\u0001!\u0019!C\u0005\u0007\u000fB\u0001\u0002b\u0005\u0001A\u0003%1\u0011\n\u0005\b\t+\u0001A\u0011\u0001C\f\u0011\u001d!)\u0005\u0001C\u0001\t\u000fBq\u0001b\u0013\u0001\t\u0003!i\u0005C\u0004\u0005\u0010\u0002!\t\u0001\"%\t\u000f\u0011=\u0007\u0001\"\u0001\u0005R\"9AQ\u001c\u0001\u0005\u0002\u0011}\u0007b\u0002Cw\u0001\u0011\u0005Aq\u001e\u0005\b\tk\u0004A\u0011\u0001C|\u0011\u001d)\t\u0002\u0001C\u0001\u000b'Aq!\"\n\u0001\t\u0003)9\u0003C\u0004\u00062\u0001!\t!b\r\t\u0013\u0015e\u0002\u0001\"\u0001\u0002 \u0016m\u0002bBC*\u0001\u0011%QQ\u000b\u0005\b\u000b/\u0002A\u0011BC-\u0011\u001d)i\b\u0001C\u0005\u000b\u007fBq!\"%\u0001\t\u0003)\u0019\nC\u0004\u00062\u0002!I!b-\t\u000f\u0015\u0005\u0007\u0001\"\u0003\u0006D\"IQ\u0011\u001d\u0001\u0005\u0002\u0005}U1\u001d\u0005\n\u000bW\u0004A\u0011AAP\u000b[Dq!b>\u0001\t\u0013)I\u0010C\u0004\u0007F\u0001!IAb\u0012\t\u000f\u00195\u0004\u0001\"\u0003\u0007p!Ia\u0011\u0014\u0001\u0005\u0002\u0005}e1\u0014\u0005\b\r\u0007\u0004A\u0011\u0002Dc\u0011\u001d19\u000f\u0001C\u0005\rSDqAb<\u0001\t\u00131\t\u0010C\u0004\u0007\u0000\u0002!Ia\"\u0001\t\u000f\u001d\u001d\u0001\u0001\"\u0003\b\n!9qq\u0002\u0001\u0005\u0002\u001dE\u0001bBD%\u0001\u0011\u0005q1\n\u0005\b\u000fW\u0002A\u0011AD7\u0011\u001d99\u000b\u0001C\u0001\u000fSCqa\"5\u0001\t\u00039\u0019\u000eC\u0004\b\\\u0002!\ta\"8\t\u0013\u001d%\b!%A\u0005\u0002\u001d-\bb\u0002E\u0001\u0001\u0011\u0005\u00012\u0001\u0005\b\u00117\u0001A\u0011AC+\u0011%Ai\u0002\u0001C\u0001\u0003?+)\u0006C\u0004\t \u0001!\t\u0001#\t\t\u000f!\u001d\u0002\u0001\"\u0001\t*!9\u0001r\u0007\u0001\u0005\u0002!e\u0002\"\u0003E#\u0001\u0011\u0005\u0011qTC+\u0011\u001dA9\u0005\u0001C\u0005\u0011\u0013Bq\u0001c\u0014\u0001\t\u0013A\t\u0006C\u0005\tX\u0001!\t!a(\tZ!I\u0001\u0012\r\u0001\u0005\u0002\u0005}\u00052\r\u0005\n\u0011W\u0002A\u0011AAP\u0011[B\u0011\u0002# \u0001\t\u0003\ty\nc \t\u0013!=\u0005\u0001\"\u0001\u0002 \"E\u0005\"\u0003EL\u0001\u0011\u0005\u0011q\u0014EM\u0011%Ay\n\u0001C\u0001\u0003?C\t\u000bC\u0005\t*\u0002!\t!a(\t,\"I\u00012\u0017\u0001\u0005\u0002\u0005}UQ\u000b\u0005\n\u0011k\u0003A\u0011AAP\u0011oC\u0011\u0002c/\u0001\t\u0003\ty\n#0\t\u0013!}\b\u0001\"\u0001\u0002 &\u0005\u0001bBE\u0013\u0001\u0011%\u0011r\u0005\u0005\b\u0013W\u0001A\u0011BE\u0017\u0011\u001dI\u0019\u0004\u0001C\u0005\u0013kAq!#\u000f\u0001\t\u0013IY\u0004C\u0004\n@\u0001!I!#\u0011\t\u000f%\u001d\u0003\u0001\"\u0003\nJ!9\u0011R\u000b\u0001\u0005\n%]\u0003bBE.\u0001\u0011%\u0011R\f\u0005\n\u0013C\u0002A\u0011AAP\u0013GB\u0011\"#\u001b\u0001\t\u0003\ty*c\u001b\t\u000f%=\u0004\u0001\"\u0003\nr!9\u0011r\u000f\u0001\u0005\n%e\u0004\"CE@\u0001\u0011\u0005\u0011qTEA\u0011%II\t\u0001C\u0001\u0003?KY\tC\u0006\n\u0018\u0002\t\n\u0011\"\u0001\u0002 \u001e-\b\"CEM\u0001\u0011\u0005\u0011qTEN\u0011-I\t\u000bAI\u0001\n\u0003\tyjb;\t\u000f%\r\u0006\u0001\"\u0003\n&\"9\u0011r\u0018\u0001\u0005\n%\u0005\u0007\"CEc\u0001\u0011\u0005\u0011qTEd\u0011%II\u000e\u0001C\u0001\u0003?KY\u000eC\u0005\nb\u0002!\t!a(\nd\"9\u00112\u001e\u0001\u0005\n%5\b\"CE\u007f\u0001\u0011\u0005\u0011qTE\u0000\u0011%Q\u0019\u0001\u0001C\u0001\u0003?S)\u0001C\u0004\u000b\u000e\u0001!IAc\u0004\t\u0013)\u0015\u0002!%A\u0005\n)\u001d\u0002\"\u0003F\u0016\u0001E\u0005I\u0011BDv\u0011%Qi\u0003\u0001C\u0001\u0003?Sy\u0003C\u0005\u000b8\u0001!\t!a(\u000b:!I!r\b\u0001\u0005\u0002\u0005}%\u0012\t\u0005\n\u0015\u000f\u0002A\u0011AAP\u0015\u0013BqAc\u0014\u0001\t\u0013Q\t\u0006C\u0005\u000b^\u0001\t\n\u0011\"\u0003\u000b`!I!2\r\u0001\u0012\u0002\u0013%q1\u001e\u0005\n\u0015K\u0002A\u0011AAP\u0015OB\u0011B#\u001c\u0001\t\u0003\tyJc\u001c\t\u000f)e\u0004\u0001\"\u0003\u000b|!9!r\u0010\u0001\u0005\n)\u0005\u0005b\u0002FD\u0001\u0011%!\u0012\u0012\u0005\b\u0015/\u0003A\u0011\u0002FM\u0011%Q\t\u000b\u0001C\u0001\u0003GS\u0019\u000bC\u0004\u000b6\u0002!IAc.\t\u000f)e\u0007\u0001\"\u0001\u000b\\\"9!2\u001d\u0001\u0005\u0002)\u0015\b\"\u0003Fv\u0001E\u0005I\u0011\u0001Fw\u000f)Q\t0a(\t\u0002\u0005\r&2\u001f\u0004\u000b\u0003;\u000by\n#\u0001\u0002$*U\b\u0002\u0003B\u000e\u0003'#\tAc>\t\u0015)e\u00181\u0013b\u0001\n\u0003\u0011\u0019\u0007C\u0005\u000b|\u0006M\u0005\u0015!\u0003\u0003f!Q!R`AJ#\u0003%\tAc@\u0003\u0019\u0011\u000buiU2iK\u0012,H.\u001a:\u000b\t\u0005\u0005\u00161U\u0001\ng\u000eDW\rZ;mKJTA!!*\u0002(\u0006)1\u000f]1sW*!\u0011\u0011VAV\u0003\u0019\t\u0007/Y2iK*\u0011\u0011QV\u0001\u0004_J<7#\u0002\u0001\u00022\u0006u\u0006\u0003BAZ\u0003sk!!!.\u000b\u0005\u0005]\u0016!B:dC2\f\u0017\u0002BA^\u0003k\u0013a!\u00118z%\u00164\u0007\u0003BA`\u0003\u000bl!!!1\u000b\t\u0005\r\u00171U\u0001\tS:$XM\u001d8bY&!\u0011qYAa\u0005\u001daunZ4j]\u001e\f!a]2\u0016\u0005\u00055\u0007\u0003BAh\u0003#l!!a)\n\t\u0005M\u00171\u0015\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0002\u0001\u0003\r\u00198\rI\u0001\u000ei\u0006\u001c8nU2iK\u0012,H.\u001a:\u0016\u0005\u0005u\u0007\u0003BAp\u0003Cl!!a(\n\t\u0005\r\u0018q\u0014\u0002\u000e)\u0006\u001c8nU2iK\u0012,H.\u001a:\u0002\u001dQ\f7o[*dQ\u0016$W\u000f\\3sA\u0005YA.[:uK:,'OQ;t!\u0011\ty.a;\n\t\u00055\u0018q\u0014\u0002\u0010\u0019&4X\rT5ti\u0016tWM\u001d\"vg\u0006\u0001R.\u00199PkR\u0004X\u000f\u001e+sC\u000e\\WM\u001d\t\u0005\u0003\u001f\f\u00190\u0003\u0003\u0002v\u0006\r&AF'ba>+H\u000f];u)J\f7m[3s\u001b\u0006\u001cH/\u001a:\u0002%\tdwnY6NC:\fw-\u001a:NCN$XM\u001d\t\u0005\u0003w\u0014\t!\u0004\u0002\u0002~*!\u0011q`AR\u0003\u001d\u0019Ho\u001c:bO\u0016LAAa\u0001\u0002~\n\u0011\"\t\\8dW6\u000bg.Y4fe6\u000b7\u000f^3s\u0003\r)gN\u001e\t\u0005\u0003\u001f\u0014I!\u0003\u0003\u0003\f\u0005\r&\u0001C*qCJ\\WI\u001c<\u0002\u000b\rdwnY6\u0011\t\tE!qC\u0007\u0003\u0005'QAA!\u0006\u0002$\u0006!Q\u000f^5m\u0013\u0011\u0011IBa\u0005\u0003\u000b\rcwnY6\u0002\rqJg.\u001b;?)A\u0011yB!\t\u0003$\t\u0015\"q\u0005B\u0015\u0005W\u0011i\u0003E\u0002\u0002`\u0002Aq!!3\u000b\u0001\u0004\ti\rC\u0004\u0002Z*\u0001\r!!8\t\u000f\u0005\u001d(\u00021\u0001\u0002j\"9\u0011q\u001e\u0006A\u0002\u0005E\bbBA|\u0015\u0001\u0007\u0011\u0011 \u0005\b\u0005\u000bQ\u0001\u0019\u0001B\u0004\u0011%\u0011iA\u0003I\u0001\u0002\u0004\u0011y\u0001\u0006\u0004\u0003 \tE\"1\u0007\u0005\b\u0003\u0013\\\u0001\u0019AAg\u0011\u001d\tIn\u0003a\u0001\u0003;$BAa\b\u00038!9\u0011\u0011\u001a\u0007A\u0002\u00055\u0017!D7fiJL7m]*pkJ\u001cW-\u0006\u0002\u0003>A!\u0011q\u001cB \u0013\u0011\u0011\t%a(\u0003%\u0011\u000buiU2iK\u0012,H.\u001a:T_V\u00148-Z\u0001\u000f[\u0016$(/[2t'>,(oY3!\u0003%qW\r\u001f;K_\nLE-\u0006\u0002\u0003JA!!1\nB.\u001b\t\u0011iE\u0003\u0003\u0003P\tE\u0013AB1u_6L7M\u0003\u0003\u0003T\tU\u0013AC2p]\u000e,(O]3oi*!!Q\u0003B,\u0015\t\u0011I&\u0001\u0003kCZ\f\u0017\u0002\u0002B/\u0005\u001b\u0012Q\"\u0011;p[&\u001c\u0017J\u001c;fO\u0016\u0014\u0018A\u00038fqRTuNY%eA\u0005aa.^7U_R\fGNS8cgV\u0011!Q\r\t\u0005\u0003g\u00139'\u0003\u0003\u0003j\u0005U&aA%oi\u0006Ya.\u001a=u'R\fw-Z%e\u00031qW\r\u001f;Ti\u0006<W-\u00133!\u0003=QwNY%e)>\u001cF/Y4f\u0013\u0012\u001cXC\u0001B:!!\u0011)Ha \u0003f\t\rUB\u0001B<\u0015\u0011\u0011IHa\u001f\u0002\u000f5,H/\u00192mK*!!QPA[\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u0003\u00139HA\u0004ICNDW*\u00199\u0011\r\tU$Q\u0011B3\u0013\u0011\u00119Ia\u001e\u0003\u000f!\u000b7\u000f[*fi\u0006\u0001\"n\u001c2JIR{7\u000b^1hK&#7\u000fI\u0001\u000fgR\fw-Z%e)>\u001cF/Y4f+\t\u0011y\t\u0005\u0005\u0003v\t}$Q\rBI!\u0011\tyNa%\n\t\tU\u0015q\u0014\u0002\u0006'R\fw-Z\u0001\u0010gR\fw-Z%e)>\u001cF/Y4fA\u0005\u00192\u000f[;gM2,\u0017\n\u001a+p\u001b\u0006\u00048\u000b^1hKV\u0011!Q\u0014\t\t\u0005k\u0012yH!\u001a\u0003 B!\u0011q\u001cBQ\u0013\u0011\u0011\u0019+a(\u0003\u001fMCWO\u001a4mK6\u000b\u0007o\u0015;bO\u0016\fAc\u001d5vM\u001adW-\u00133U_6\u000b\u0007o\u0015;bO\u0016\u0004\u0013\u0001\u00056pE&#Gk\\!di&4XMS8c+\t\u0011Y\u000b\u0005\u0005\u0003v\t}$Q\rBW!\u0011\tyNa,\n\t\tE\u0016q\u0014\u0002\n\u0003\u000e$\u0018N^3K_\n\f\u0011C[8c\u0013\u0012$v.Q2uSZ,'j\u001c2!\u000359\u0018-\u001b;j]\u001e\u001cF/Y4fgV\u0011!\u0011\u0018\t\u0007\u0005k\u0012)I!%\u0002\u001d]\f\u0017\u000e^5oON#\u0018mZ3tA\u0005i!/\u001e8oS:<7\u000b^1hKN\faB];o]&twm\u0015;bO\u0016\u001c\b%\u0001\u0007gC&dW\rZ*uC\u001e,7/A\u0007gC&dW\rZ*uC\u001e,7\u000fI\u0001\u000bC\u000e$\u0018N^3K_\n\u001cXC\u0001Be!\u0019\u0011)H!\"\u0003.\u0006Y\u0011m\u0019;jm\u0016TuNY:!\u0003I\u0019\u0017M\\2fY2,GMS8c\u000fJ|W\u000f]:\u0016\u0005\tE\u0007CBAp\u0005'\u00149.\u0003\u0003\u0003V\u0006}%A\u0005'j[&$X\rZ*ju\u00164\u0015JR(TKR\u0004BA!7\u0003h:!!1\u001cBr!\u0011\u0011i.!.\u000e\u0005\t}'\u0002\u0002Bq\u0003+\fa\u0001\u0010:p_Rt\u0014\u0002\u0002Bs\u0003k\u000ba\u0001\u0015:fI\u00164\u0017\u0002\u0002Bu\u0005W\u0014aa\u0015;sS:<'\u0002\u0002Bs\u0003k\u000b1cY1oG\u0016dG.\u001a3K_\n<%o\\;qg\u0002\n\u0011bY1dQ\u0016dunY:\u0016\u0005\tM\b\u0003\u0003B;\u0005\u007f\u0012)G!>\u0011\r\t]8\u0011AB\u0004\u001d\u0011\u0011IP!@\u000f\t\tu'1`\u0005\u0003\u0003oKAAa@\u00026\u00069\u0001/Y2lC\u001e,\u0017\u0002BB\u0002\u0007\u000b\u0011!\"\u00138eKb,GmU3r\u0015\u0011\u0011y0!.\u0011\r\t]8\u0011BB\u0007\u0013\u0011\u0019Ya!\u0002\u0003\u0007M+\u0017\u000f\u0005\u0003\u0002`\u000e=\u0011\u0002BB\t\u0003?\u0013A\u0002V1tW2{7-\u0019;j_:\f!bY1dQ\u0016dunY:!\u0003Q)\u00070Z2vi>\u0014h)Y5mkJ,W\t]8dQV\u00111\u0011\u0004\t\t\u0005k\u0012yHa6\u0004\u001cA!\u00111WB\u000f\u0013\u0011\u0019y\"!.\u0003\t1{gnZ\u0001\u0016Kb,7-\u001e;pe\u001a\u000b\u0017\u000e\\;sK\u0016\u0003xn\u00195!\u0003Q\u0019\b.\u001e4gY\u00164\u0015\u000e\\3M_N$X\t]8dQ\u0006)2\u000f[;gM2,g)\u001b7f\u0019>\u001cH/\u00129pG\"\u0004\u0013aF8viB,HoQ8n[&$8i\\8sI&t\u0017\r^8s+\t\u0019Y\u0003\u0005\u0003\u0002`\u000e5\u0012\u0002BB\u0018\u0003?\u0013qcT;uaV$8i\\7nSR\u001cun\u001c:eS:\fGo\u001c:\u00021=,H\u000f];u\u0007>lW.\u001b;D_>\u0014H-\u001b8bi>\u0014\b%A\tdY>\u001cXO]3TKJL\u0017\r\\5{KJ,\"aa\u000e\u0011\t\re2qH\u0007\u0003\u0007wQAa!\u0010\u0002$\u0006Q1/\u001a:jC2L'0\u001a:\n\t\r\u000531\b\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018J\\:uC:\u001cW-\u0001\ndY>\u001cXO]3TKJL\u0017\r\\5{KJ\u0004\u0013!\u00073jg\u0006dGn\\<Ti\u0006<WMU3uef4uN\u001d+fgR,\"a!\u0013\u0011\t\u0005M61J\u0005\u0005\u0007\u001b\n)LA\u0004C_>dW-\u00198\u00025\u0011L7/\u00197m_^\u001cF/Y4f%\u0016$(/\u001f$peR+7\u000f\u001e\u0011\u00027MDw.\u001e7e\u001b\u0016\u0014x-\u001a*fg>,(oY3Qe>4\u0017\u000e\\3t\u0003q\u0019\bn\\;mI6+'oZ3SKN|WO]2f!J|g-\u001b7fg\u0002\nA%\u001e8SK\u001eL7\u000f^3s\u001fV$\b/\u001e;P]\"{7\u000f^(o\r\u0016$8\r\u001b$bS2,(/Z\u0001&k:\u0014VmZ5ti\u0016\u0014x*\u001e;qkR|e\u000eS8ti>sg)\u001a;dQ\u001a\u000b\u0017\u000e\\;sK\u0002\n1$\\1y\u0007>t7/Z2vi&4Xm\u0015;bO\u0016\fE\u000f^3naR\u001c\u0018\u0001H7bq\u000e{gn]3dkRLg/Z*uC\u001e,\u0017\t\u001e;f[B$8\u000fI\u0001\u0011[\u0006D8\u000b^1hK\u0006#H/Z7qiN\f\u0011#\\1y'R\fw-Z!ui\u0016l\u0007\u000f^:!\u0003yIwM\\8sK\u0012+7m\\7nSN\u001c\u0018n\u001c8GKR\u001c\u0007NR1jYV\u0014X-A\u0010jO:|'/\u001a#fG>lW.[:tS>tg)\u001a;dQ\u001a\u000b\u0017\u000e\\;sK\u0002\n1EY1se&,'OS8c\u0013\u0012$vNT;n)\u0006\u001c8n]\"iK\u000e\\g)Y5mkJ,7/\u0006\u0002\u0004jAA11NB7\u0005K\u0012)'\u0004\u0002\u0003R%!1q\u000eB)\u0005E\u0019uN\\2veJ,g\u000e\u001e%bg\"l\u0015\r]\u0001%E\u0006\u0014(/[3s\u0015>\u0014\u0017\n\u001a+p\u001dVlG+Y:lg\u000eCWmY6GC&dWO]3tA\u0005IB/[7f\u0013:$XM\u001d<bY:+X\u000eV1tWN\u001c\u0005.Z2l+\t\u0019Y\"\u0001\u000euS6,\u0017J\u001c;feZ\fGNT;n)\u0006\u001c8n]\"iK\u000e\\\u0007%A\fnCb4\u0015-\u001b7ve\u0016tU/\u001c+bg.\u001c8\t[3dW\u0006AR.\u0019=GC&dWO]3Ok6$\u0016m]6t\u0007\",7m\u001b\u0011\u0002!5,7o]1hKN\u001b\u0007.\u001a3vY\u0016\u0014XCABA!\u0011\u0019Yga!\n\t\r\u0015%\u0011\u000b\u0002\u0019'\u000eDW\rZ;mK\u0012,\u00050Z2vi>\u00148+\u001a:wS\u000e,\u0017!E7fgN\fw-Z*dQ\u0016$W\u000f\\3sA\u0005\u0001RM^3oiB\u0013xnY3tg2{w\u000e]\u000b\u0003\u0007\u001b\u0003B!a8\u0004\u0010&!1\u0011SAP\u0005q!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e)s_\u000e,7o\u001d'p_B\fA#\u001a<f]R\u0004&o\\2fgNdun\u001c9`I\u0015\fH\u0003BBL\u0007;\u0003B!a-\u0004\u001a&!11TA[\u0005\u0011)f.\u001b;\t\u0013\r}U)!AA\u0002\r5\u0015a\u0001=%c\u0005\tRM^3oiB\u0013xnY3tg2{w\u000e\u001d\u0011\u0002'M,G/\u0012<f]R\u0004&o\\2fgNdun\u001c9\u0015\t\r]5q\u0015\u0005\b\u0007S;\u0005\u0019ABG\u0003\u0011awn\u001c9\u0002/A,8\u000f\u001b\"bg\u0016$7\u000b[;gM2,WI\\1cY\u0016$\u0017\u0001\u00079vg\"\u0014\u0015m]3e'\",hM\u001a7f\u000b:\f'\r\\3eA\u0005A#\r\\8dW6\u000bg.Y4fe6\u000b7\u000f^3s\tJLg/\u001a:IK\u0006\u0014HOY3biRKW.Z8viV\u001111\u0017\t\u0005\u0007k\u001bi,\u0004\u0002\u00048*!1\u0011XB^\u0003!!WO]1uS>t'\u0002\u0002B*\u0003kKAaa0\u00048\nqa)\u001b8ji\u0016$UO]1uS>t\u0017!\u000b2m_\u000e\\W*\u00198bO\u0016\u0014X*Y:uKJ$%/\u001b<fe\"+\u0017M\u001d;cK\u0006$H+[7f_V$\b%A\u000ftQV4g\r\\3NKJ<WMU3tk2$8\u000fV5nK>,HoU3d\u0003y\u0019\b.\u001e4gY\u0016lUM]4f%\u0016\u001cX\u000f\u001c;t)&lWm\\;u'\u0016\u001c\u0007%A\u000etQV4g\r\\3NKJ<WMR5oC2L'0Z,bSR\u001cVmY\u0001\u001dg\",hM\u001a7f\u001b\u0016\u0014x-\u001a$j]\u0006d\u0017N_3XC&$8+Z2!\u0003\u0001\u001a\b.\u001e4gY\u0016lUM]4f/\u0006LG/T5o'&TX\r\u00165sKNDw\u000e\u001c3\u0002CMDWO\u001a4mK6+'oZ3XC&$X*\u001b8TSj,G\u000b\u001b:fg\"|G\u000e\u001a\u0011\u0002'MDWO\u001a4mKB+8\u000f['j]J\u000bG/[8\u0016\u0005\rM\u0007\u0003BAZ\u0007+LAaa6\u00026\n1Ai\\;cY\u0016\fAc\u001d5vM\u001adW\rU;tQ6KgNU1uS>\u0004\u0013AH:ik\u001a4G.Z'fe\u001e,g)\u001b8bY&TXMT;n)\"\u0014X-\u00193t\u0003}\u0019\b.\u001e4gY\u0016lUM]4f\r&t\u0017\r\\5{K:+X\u000e\u00165sK\u0006$7\u000fI\u0001\u001ag\",hM\u001a7f\r&t\u0017\r\\5{KJ\u00038\r\u00165sK\u0006$7/\u0001\u000etQV4g\r\\3GS:\fG.\u001b>f%B\u001cG\u000b\u001b:fC\u0012\u001c\b%A\u000bfqR,'O\\1m'\",hM\u001a7f\u00072LWM\u001c;\u0016\u0005\r\u001d\bCBAZ\u0007S\u001ci/\u0003\u0003\u0004l\u0006U&AB(qi&|g\u000e\u0005\u0003\u0004p\u000eeXBABy\u0015\u0011\u0019\u0019p!>\u0002\u000fMDWO\u001a4mK*!1q_AR\u0003\u001dqW\r^<pe.LAaa?\u0004r\n\u0001\"\t\\8dWN#xN]3DY&,g\u000e^\u0001\u001eg\",hM\u001a7f\u001b\u0016\u0014x-\u001a$j]\u0006d\u0017N_3TG\",G-\u001e7fe\u0006q2\u000f[;gM2,W*\u001a:hK\u001aKg.\u00197ju\u0016\u001c6\r[3ek2,'\u000fI\u0001\u001fg\",hM\u001a7f'\u0016tGMR5oC2L'0\u001a*qG\u0016CXmY;u_J,\"\u0001\"\u0002\u0011\t\r-DqA\u0005\u0005\t\u0013\u0011\tFA\bFq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u0003}\u0019\b.\u001e4gY\u0016\u001cVM\u001c3GS:\fG.\u001b>f%B\u001cW\t_3dkR|'\u000fI\u0001\u0018iJ\f7m[5oO\u000e\u000b7\r[3WSNL'-\u001b7jif\f\u0001\u0004\u001e:bG.LgnZ\"bG\",g+[:jE&d\u0017\u000e^=!\u0003yaWmZ1ds\u0006\u0013wN\u001d;Ti\u0006<W-\u00114uKJ\\\u0015\u000e\u001c7UCN\\7/A\u0010mK\u001e\f7-_!c_J$8\u000b^1hK\u00063G/\u001a:LS2dG+Y:lg\u0002\n1\u0002^1tWN#\u0018M\u001d;fIR11q\u0013C\r\twAq\u0001b\u0007b\u0001\u0004!i\"\u0001\u0003uCN\\\u0007\u0007\u0002C\u0010\tS\u0001b!a8\u0005\"\u0011\u0015\u0012\u0002\u0002C\u0012\u0003?\u0013A\u0001V1tWB!Aq\u0005C\u0015\u0019\u0001!A\u0002b\u000b\u0005\u001a\u0005\u0005\t\u0011!B\u0001\t[\u00111a\u0018\u00132#\u0011!y\u0003\"\u000e\u0011\t\u0005MF\u0011G\u0005\u0005\tg\t)LA\u0004O_RD\u0017N\\4\u0011\t\u0005MFqG\u0005\u0005\ts\t)LA\u0002B]fDq\u0001\"\u0010b\u0001\u0004!y$\u0001\u0005uCN\\\u0017J\u001c4p!\u0011\ty\u000e\"\u0011\n\t\u0011\r\u0013q\u0014\u0002\t)\u0006\u001c8.\u00138g_\u0006\tB/Y:l\u000f\u0016$H/\u001b8h%\u0016\u001cX\u000f\u001c;\u0015\t\r]E\u0011\n\u0005\b\t{\u0011\u0007\u0019\u0001C \u0003%!\u0018m]6F]\u0012,G\r\u0006\b\u0004\u0018\u0012=C1\fC3\tS\"\u0019\t\"$\t\u000f\u0011m1\r1\u0001\u0005RA\"A1\u000bC,!\u0019\ty\u000e\"\t\u0005VA!Aq\u0005C,\t1!I\u0006b\u0014\u0002\u0002\u0003\u0005)\u0011\u0001C\u0017\u0005\ryFE\r\u0005\b\t;\u001a\u0007\u0019\u0001C0\u0003\u0019\u0011X-Y:p]B!\u0011q\u001aC1\u0013\u0011!\u0019'a)\u0003\u001bQ\u000b7o[#oIJ+\u0017m]8o\u0011\u001d!9g\u0019a\u0001\tk\taA]3tk2$\bb\u0002C6G\u0002\u0007AQN\u0001\rC\u000e\u001cW/\\+qI\u0006$Xm\u001d\t\u0007\u0005o\u001cI\u0001b\u001c1\r\u0011ED\u0011\u0010C@!!\u0011\t\u0002b\u001d\u0005x\u0011u\u0014\u0002\u0002C;\u0005'\u0011Q\"Q2dk6,H.\u0019;peZ\u0013\u0004\u0003\u0002C\u0014\ts\"A\u0002b\u001f\u0005j\u0005\u0005\t\u0011!B\u0001\t[\u00111a\u0018\u00134!\u0011!9\u0003b \u0005\u0019\u0011\u0005E\u0011NA\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\u0007}#C\u0007C\u0004\u0005\u0006\u000e\u0004\r\u0001b\"\u0002\u00175,GO]5d!\u0016\f7n\u001d\t\u0007\u0003g#Iia\u0007\n\t\u0011-\u0015Q\u0017\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\t{\u0019\u0007\u0019\u0001C \u0003e)\u00070Z2vi>\u0014\b*Z1si\n,\u0017\r\u001e*fG\u0016Lg/\u001a3\u0015\u0015\r%C1\u0013CL\tS#\u0019\fC\u0004\u0005\u0016\u0012\u0004\rAa6\u0002\r\u0015DXmY%e\u0011\u001d!Y\u0007\u001aa\u0001\t3\u0003b!a-\u0005\n\u0012m\u0005\u0003DAZ\t;\u001bYB!\u001a\u0003f\u0011\u0005\u0016\u0002\u0002CP\u0003k\u0013a\u0001V;qY\u0016$\u0004C\u0002B|\u0007\u0013!\u0019\u000b\u0005\u0003\u0002`\u0012\u0015\u0016\u0002\u0002CT\u0003?\u0013q\"Q2dk6,H.\u00192mK&sgm\u001c\u0005\b\tW#\u0007\u0019\u0001CW\u00039\u0011Gn\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\u0004B!a?\u00050&!A\u0011WA\u007f\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012Dq\u0001\".e\u0001\u0004!9,A\bfq\u0016\u001cW\u000f^8s+B$\u0017\r^3t!!\u0011)\b\"/\u0005>\u0012\r\u0017\u0002\u0002C^\u0005o\u00121!T1q!!\t\u0019\fb0\u0003f\t\u0015\u0014\u0002\u0002Ca\u0003k\u0013a\u0001V;qY\u0016\u0014\u0004\u0003\u0002Cc\t\u0017l!\u0001b2\u000b\t\u0011%\u00171U\u0001\tKb,7-\u001e;pe&!AQ\u001aCd\u0005=)\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c\u0018\u0001D3yK\u000e,Ho\u001c:M_N$HCBBL\t'$)\u000eC\u0004\u0005\u0016\u0016\u0004\rAa6\t\u000f\u0011uS\r1\u0001\u0005XB!\u0011q\u001cCm\u0013\u0011!Y.a(\u0003%\u0015CXmY;u_Jdun]:SK\u0006\u001cxN\\\u0001\u000eo>\u00148.\u001a:SK6|g/\u001a3\u0015\u0011\r]E\u0011\u001dCs\tSDq\u0001b9g\u0001\u0004\u00119.\u0001\u0005x_J\\WM]%e\u0011\u001d!9O\u001aa\u0001\u0005/\fA\u0001[8ti\"9A1\u001e4A\u0002\t]\u0017aB7fgN\fw-Z\u0001\u000eKb,7-\u001e;pe\u0006#G-\u001a3\u0015\r\r]E\u0011\u001fCz\u0011\u001d!)j\u001aa\u0001\u0005/Dq\u0001b:h\u0001\u0004\u00119.A\u0007uCN\\7+\u001a;GC&dW\r\u001a\u000b\t\u0007/#I0b\u0001\u0006\u0006!9A1 5A\u0002\u0011u\u0018a\u0002;bg.\u001cV\r\u001e\t\u0005\u0003?$y0\u0003\u0003\u0006\u0002\u0005}%a\u0002+bg.\u001cV\r\u001e\u0005\b\t;B\u0007\u0019\u0001Bl\u0011\u001d)9\u0001\u001ba\u0001\u000b\u0013\t\u0011\"\u001a=dKB$\u0018n\u001c8\u0011\r\u0005M6\u0011^C\u0006!\u0011\u001190\"\u0004\n\t\u0015=1Q\u0001\u0002\n)\"\u0014xn^1cY\u0016\f\u0001d\u001d9fGVd\u0017\r^5wKR\u000b7o[*vE6LG\u000f^3e)\u0019\u00199*\"\u0006\u0006\"!9A1D5A\u0002\u0015]\u0001\u0007BC\r\u000b;\u0001b!a8\u0005\"\u0015m\u0001\u0003\u0002C\u0014\u000b;!A\"b\b\u0006\u0016\u0005\u0005\t\u0011!B\u0001\t[\u00111a\u0018\u00136\u0011\u001d)\u0019#\u001ba\u0001\u0005K\n\u0011\u0002^1tW&sG-\u001a=\u00023Ut7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u\u0003\u0012$W\r\u001a\u000b\u0007\u0007/+I#\"\f\t\u000f\u0015-\"\u000e1\u0001\u0003f\u000591\u000f^1hK&#\u0007bBC\u0018U\u0002\u0007!QM\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0003m)hn]2iK\u0012,H.\u00192mKR\u000b7o[*fiJ+Wn\u001c<fIR11qSC\u001b\u000boAq!b\u000bl\u0001\u0004\u0011)\u0007C\u0004\u00060-\u0004\rA!\u001a\u0002\u0019\u001d,GoQ1dQ\u0016dunY:\u0015\t\tUXQ\b\u0005\b\u000b\u007fa\u0007\u0019AC!\u0003\r\u0011H\r\u001a\u0019\u0005\u000b\u0007*y\u0005\u0005\u0004\u0006F\u0015%SQJ\u0007\u0003\u000b\u000fRA!b\u0010\u0002$&!Q1JC$\u0005\r\u0011F\t\u0012\t\u0005\tO)y\u0005\u0002\u0007\u0006R\u0015u\u0012\u0011!A\u0001\u0006\u0003!iCA\u0002`IY\nab\u00197fCJ\u001c\u0015m\u00195f\u0019>\u001c7\u000f\u0006\u0002\u0004\u0018\u0006Qr-\u001a;Pe\u000e\u0013X-\u0019;f'\",hM\u001a7f\u001b\u0006\u00048\u000b^1hKR1!qTC.\u000bsBq!\"\u0018o\u0001\u0004)y&\u0001\u0006tQV4g\r\\3EKB\u0004\u0004\"\"\u0019\u0006j\u0015=TQ\u000f\t\u000b\u0003\u001f,\u0019'b\u001a\u0006n\u0015M\u0014\u0002BC3\u0003G\u0013\u0011c\u00155vM\u001adW\rR3qK:$WM\\2z!\u0011!9#\"\u001b\u0005\u0019\u0015-T1LA\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\u0007}#s\u0007\u0005\u0003\u0005(\u0015=D\u0001DC9\u000b7\n\t\u0011!A\u0003\u0002\u00115\"aA0%qA!AqEC;\t1)9(b\u0017\u0002\u0002\u0003\u0005)\u0011\u0001C\u0017\u0005\ryF%\u000f\u0005\b\u000bwr\u0007\u0019\u0001B3\u0003)1\u0017N]:u\u0015>\u0014\u0017\nZ\u0001%G\",7m\u001b\"beJLWM]*uC\u001e,w+\u001b;i%\u0012#5\t[1j]B\u000bG\u000f^3s]R11qSCA\u000b\u001bCq!b\u0010p\u0001\u0004)\u0019\t\r\u0003\u0006\u0006\u0016%\u0005CBC#\u000b\u0013*9\t\u0005\u0003\u0005(\u0015%E\u0001DCF\u000b\u0003\u000b\t\u0011!A\u0003\u0002\u00115\"\u0001B0%cABq!b$p\u0001\u0004\u0011)'A\bok6$\u0016m]6t\u0013:\u001cF/Y4f\u0003U\u0019'/Z1uKNCWO\u001a4mK6\u000b\u0007o\u0015;bO\u0016,\u0002\"\"&\u0006\u001e\u0016\rV\u0011\u0016\u000b\u0007\u0005?+9*\",\t\u000f\u0015u\u0003\u000f1\u0001\u0006\u001aBQ\u0011qZC2\u000b7+\t+b*\u0011\t\u0011\u001dRQ\u0014\u0003\b\u000b?\u0003(\u0019\u0001C\u0017\u0005\u0005Y\u0005\u0003\u0002C\u0014\u000bG#q!\"*q\u0005\u0004!iCA\u0001W!\u0011!9#\"+\u0005\u000f\u0015-\u0006O1\u0001\u0005.\t\t1\tC\u0004\u00060B\u0004\rA!\u001a\u0002\u000b)|'-\u00133\u0002M\rDWmY6CCJ\u0014\u0018.\u001a:Ti\u0006<WmV5uQ\u0012Kh.Y7jG\u0006cGn\\2bi&|g\u000e\u0006\u0003\u0004\u0018\u0016U\u0006bBC c\u0002\u0007Qq\u0017\u0019\u0005\u000bs+i\f\u0005\u0004\u0006F\u0015%S1\u0018\t\u0005\tO)i\f\u0002\u0007\u0006@\u0016U\u0016\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`IE\u0012\u0014!H2iK\u000e\\')\u0019:sS\u0016\u00148\u000b^1hK^KG\u000f\u001b(v[Ncw\u000e^:\u0015\r\r]UQYCi\u0011\u001d)yD\u001da\u0001\u000b\u000f\u0004D!\"3\u0006NB1QQIC%\u000b\u0017\u0004B\u0001b\n\u0006N\u0012aQqZCc\u0003\u0003\u0005\tQ!\u0001\u0005.\t!q\fJ\u00194\u0011\u001d)\u0019N\u001da\u0001\u000b+\f!A\u001d9\u0011\t\u0015]WQ\\\u0007\u0003\u000b3TA!b7\u0002$\u0006A!/Z:pkJ\u001cW-\u0003\u0003\u0006`\u0016e'a\u0004*fg>,(oY3Qe>4\u0017\u000e\\3\u0002;5,'oZ3SKN|WO]2f!J|g-\u001b7fg\u001a{'o\u0015;bO\u0016$B!\"6\u0006f\"9Qq]:A\u0002\u0015%\u0018!F:uC\u001e,'+Z:pkJ\u001cW\r\u0015:pM&dWm\u001d\t\u0007\u0005k\u0012))\"6\u0002+5,'oZ3SKN|WO]2f!J|g-\u001b7fgR1QQ[Cx\u000bgDq!\"=u\u0001\u0004)).\u0001\u0002sc!9QQ\u001f;A\u0002\u0015U\u0017A\u0001:3\u0003E\u0019'/Z1uKJ+7/\u001e7u'R\fw-\u001a\u000b\r\u000bw4\tA\"\u0004\u00074\u0019eb1\b\t\u0005\u0003?,i0\u0003\u0003\u0006\u0000\u0006}%a\u0003*fgVdGo\u0015;bO\u0016Dq!b\u0010v\u0001\u00041\u0019\u0001\r\u0003\u0007\u0006\u0019%\u0001CBC#\u000b\u001329\u0001\u0005\u0003\u0005(\u0019%A\u0001\u0004D\u0006\r\u0003\t\t\u0011!A\u0003\u0002\u00115\"\u0001B0%cQBqAb\u0004v\u0001\u00041\t\"\u0001\u0003gk:\u001c\u0007\u0007\u0002D\n\r_\u0001\"\"a-\u0007\u0016\u0019eaq\u0004D\u0017\u0013\u001119\"!.\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004\u0003BAh\r7IAA\"\b\u0002$\nYA+Y:l\u0007>tG/\u001a=ua\u00111\tC\"\u000b\u0011\r\t]h1\u0005D\u0014\u0013\u00111)c!\u0002\u0003\u0011%#XM]1u_J\u0004B\u0001b\n\u0007*\u0011aa1\u0006D\u0007\u0003\u0003\u0005\tQ!\u0001\u0005.\t!q\fJ\u00196!\u0011!9Cb\f\u0005\u0019\u0019EbQBA\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\t}#\u0013G\u000e\u0005\b\rk)\b\u0019\u0001D\u001c\u0003)\u0001\u0018M\u001d;ji&|gn\u001d\t\u0007\u0003g#II!\u001a\t\u000f\u0015=V\u000f1\u0001\u0003f!9aQH;A\u0002\u0019}\u0012\u0001C2bY2\u001c\u0016\u000e^3\u0011\t\tEa\u0011I\u0005\u0005\r\u0007\u0012\u0019B\u0001\u0005DC2d7+\u001b;f\u0003]9W\r^(s\u0007J,\u0017\r^3QCJ,g\u000e^*uC\u001e,7\u000f\u0006\u0004\u0007J\u0019=c1\u000e\t\u0007\u0005o4YE!%\n\t\u001953Q\u0001\u0002\u0005\u0019&\u001cH\u000fC\u0004\u0007RY\u0004\rAb\u0015\u0002\u0017MDWO\u001a4mK\u0012+\u0007o\u001d\t\u0007\u0005k\u0012)I\"\u00161\u0011\u0019]c1\fD1\rO\u0002\"\"a4\u0006d\u0019ecq\fD3!\u0011!9Cb\u0017\u0005\u0019\u0019ucqJA\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\t}#\u0013g\u000e\t\u0005\tO1\t\u0007\u0002\u0007\u0007d\u0019=\u0013\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`IEB\u0004\u0003\u0002C\u0014\rO\"AB\"\u001b\u0007P\u0005\u0005\t\u0011!B\u0001\t[\u0011Aa\u0018\u00132s!9Q1\u0010<A\u0002\t\u0015\u0014!J4fi6K7o]5oO\u0006s7-Z:u_J\u001c\u0006.\u001e4gY\u0016$U\r]3oI\u0016t7-[3t)\u00111\tH\"$\u0011\r\tUd1\u000fD<\u0013\u00111)Ha\u001e\u0003\u00151K7\u000f\u001e\"vM\u001a,'\u000f\r\u0005\u0007z\u0019ud1\u0011DE!)\ty-b\u0019\u0007|\u0019\u0005eq\u0011\t\u0005\tO1i\bB\u0006\u0007\u0000]\f\t\u0011!A\u0003\u0002\u00115\"\u0001B0%eE\u0002B\u0001b\n\u0007\u0004\u0012YaQQ<\u0002\u0002\u0003\u0005)\u0011\u0001C\u0017\u0005\u0011yFE\r\u001a\u0011\t\u0011\u001db\u0011\u0012\u0003\f\r\u0017;\u0018\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`II\u001a\u0004bBC o\u0002\u0007aq\u0012\u0019\u0005\r#3)\n\u0005\u0004\u0006F\u0015%c1\u0013\t\u0005\tO1)\n\u0002\u0007\u0007\u0018\u001a5\u0015\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`II\u0002\u0014!K4fiNCWO\u001a4mK\u0012+\u0007/\u001a8eK:\u001c\u0017.Z:B]\u0012\u0014Vm]8ve\u000e,\u0007K]8gS2,7\u000f\u0006\u0003\u0007\u001e\u001a]\u0006\u0003CAZ\t\u007f3y*\";\u0011\r\tU$Q\u0011DQa!1\u0019Kb*\u0007.\u001aM\u0006CCAh\u000bG2)Kb+\u00072B!Aq\u0005DT\t-1I\u000b_A\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\t}#3\u0007\r\t\u0005\tO1i\u000bB\u0006\u00070b\f\t\u0011!A\u0003\u0002\u00115\"\u0001B0%gE\u0002B\u0001b\n\u00074\u0012YaQ\u0017=\u0002\u0002\u0003\u0005)\u0011\u0001C\u0017\u0005\u0011yFe\r\u001a\t\u000f\u0015}\u0002\u00101\u0001\u0007:B\"a1\u0018D`!\u0019))%\"\u0013\u0007>B!Aq\u0005D`\t11\tMb.\u0002\u0002\u0003\u0005)\u0011\u0001C\u0017\u0005\u0011yFEM\u001d\u0002;Q\u0014\u0018M^3sg\u0016\u0004\u0016M]3oiJ#Ei],ji\"Lgn\u0015;bO\u0016$ba!\u0013\u0007H\u001aM\u0007bBC s\u0002\u0007a\u0011\u001a\u0019\u0005\r\u00174y\r\u0005\u0004\u0006F\u0015%cQ\u001a\t\u0005\tO1y\r\u0002\u0007\u0007R\u001a\u001d\u0017\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`IMB\u0004b\u0002Dks\u0002\u0007aq[\u0001\naJ,G-[2bi\u0016\u0004\u0002\"a-\u0007Z\u001au7\u0011J\u0005\u0005\r7\f)LA\u0005Gk:\u001cG/[8ocA\"aq\u001cDr!\u0019))%\"\u0013\u0007bB!Aq\u0005Dr\t11)Ob5\u0002\u0002\u0003\u0005)\u0011\u0001C\u0017\u0005\u0011yFeM\u001d\u0002-\u001d,G/T5tg&tw\rU1sK:$8\u000b^1hKN$BA\"\u0013\u0007l\"9aQ\u001e>A\u0002\tE\u0015!B:uC\u001e,\u0017AK3bO\u0016\u0014H._\"p[B,H/\u001a)beRLG/[8og\u001a{'O\u00153e\u0003:$\u0017I\\2fgR|'o\u001d\u000b\u0005\u0007/3\u0019\u0010C\u0004\u0006@m\u0004\rA\">1\t\u0019]h1 \t\u0007\u000b\u000b*IE\"?\u0011\t\u0011\u001db1 \u0003\r\r{4\u00190!A\u0001\u0002\u000b\u0005AQ\u0006\u0002\u0005?\u0012\"T'\u0001\fva\u0012\fG/\u001a&pE&#7\u000b^1hK&#W*\u00199t)\u0019\u00199jb\u0001\b\u0006!9Qq\u0016?A\u0002\t\u0015\u0004b\u0002Dwy\u0002\u0007!\u0011S\u0001'G2,\u0017M\\;q'R\fG/\u001a$pe*{'-\u00118e\u0013:$W\r]3oI\u0016tGo\u0015;bO\u0016\u001cH\u0003BBL\u000f\u0017Aqa\"\u0004~\u0001\u0004\u0011i+A\u0002k_\n\f\u0011b];c[&$(j\u001c2\u0016\r\u001dMqqED\u000f)99)b\"\t\b,\u001dErQGD\u001c\u000f{\u0001b!a8\b\u0018\u001dm\u0011\u0002BD\r\u0003?\u0013\u0011BS8c/\u0006LG/\u001a:\u0011\t\u0011\u001drQ\u0004\u0003\b\u000f?q(\u0019\u0001C\u0017\u0005\u0005)\u0006bBC }\u0002\u0007q1\u0005\t\u0007\u000b\u000b*Ie\"\n\u0011\t\u0011\u001drq\u0005\u0003\b\u000fSq(\u0019\u0001C\u0017\u0005\u0005!\u0006b\u0002D\b}\u0002\u0007qQ\u0006\t\u000b\u0003g3)B\"\u0007\b0\u001dm\u0001C\u0002B|\rG9)\u0003C\u0004\u00076y\u0004\rab\r\u0011\r\t]8\u0011\u0002B3\u0011\u001d1iD a\u0001\r\u007fAqa\"\u000f\u007f\u0001\u00049Y$A\u0007sKN,H\u000e\u001e%b]\u0012dWM\u001d\t\u000b\u0003g3)B!\u001a\b\u001c\r]\u0005bBD }\u0002\u0007q\u0011I\u0001\u000baJ|\u0007/\u001a:uS\u0016\u001c\b\u0003BD\"\u000f\u000bj!A!\u0016\n\t\u001d\u001d#Q\u000b\u0002\u000b!J|\u0007/\u001a:uS\u0016\u001c\u0018A\u0002:v]*{'-\u0006\u0004\bN\u001dUsq\f\u000b\u000f\u0007/;yeb\u0016\bb\u001d\rtQMD5\u0011\u001d)yd a\u0001\u000f#\u0002b!\"\u0012\u0006J\u001dM\u0003\u0003\u0002C\u0014\u000f+\"qa\"\u000b\u0000\u0005\u0004!i\u0003C\u0004\u0007\u0010}\u0004\ra\"\u0017\u0011\u0015\u0005MfQ\u0003D\r\u000f7:i\u0006\u0005\u0004\u0003x\u001a\rr1\u000b\t\u0005\tO9y\u0006B\u0004\b }\u0014\r\u0001\"\f\t\u000f\u0019Ur\u00101\u0001\b4!9aQH@A\u0002\u0019}\u0002bBD\u001d\u007f\u0002\u0007qq\r\t\u000b\u0003g3)B!\u001a\b^\r]\u0005bBD \u007f\u0002\u0007q\u0011I\u0001\u0012eVt\u0017\t\u001d9s_bLW.\u0019;f\u0015>\u0014W\u0003CD8\u000f\u0013;\u0019jb \u0015\u001d\u001dEt1QDF\u000f+;yj\")\b&B1q1OD=\u000f{j!a\"\u001e\u000b\t\u001d]\u00141U\u0001\ba\u0006\u0014H/[1m\u0013\u00119Yh\"\u001e\u0003\u001bA\u000b'\u000f^5bYJ+7/\u001e7u!\u0011!9cb \u0005\u0011\u001d\u0005\u0015\u0011\u0001b\u0001\t[\u0011\u0011A\u0015\u0005\t\u000b\u007f\t\t\u00011\u0001\b\u0006B1QQIC%\u000f\u000f\u0003B\u0001b\n\b\n\u0012Aq\u0011FA\u0001\u0005\u0004!i\u0003\u0003\u0005\u0007\u0010\u0005\u0005\u0001\u0019ADG!)\t\u0019L\"\u0006\u0007\u001a\u001d=u\u0011\u0013\t\u0007\u0005o4\u0019cb\"\u0011\t\u0011\u001dr1\u0013\u0003\t\u000f?\t\tA1\u0001\u0005.!AqqSA\u0001\u0001\u00049I*A\u0005fm\u0006dW/\u0019;peBAq1ODN\u000f#;i(\u0003\u0003\b\u001e\u001eU$\u0001F!qaJ|\u00070[7bi\u0016,e/\u00197vCR|'\u000f\u0003\u0005\u0007>\u0005\u0005\u0001\u0019\u0001D \u0011!9\u0019+!\u0001A\u0002\rm\u0011a\u0002;j[\u0016|W\u000f\u001e\u0005\t\u000f\u007f\t\t\u00011\u0001\bB\u0005q1/\u001e2nSRl\u0015\r]*uC\u001e,W\u0003CDV\u000f{;\tm\"2\u0015\u0015\u001d5vQWDd\u000f\u001b<y\r\u0005\u0004\u0002`\u001e]qq\u0016\t\u0005\u0003\u001f<\t,\u0003\u0003\b4\u0006\r&aE'ba>+H\u000f];u'R\fG/[:uS\u000e\u001c\b\u0002CD\\\u0003\u0007\u0001\ra\"/\u0002\u0015\u0011,\u0007/\u001a8eK:\u001c\u0017\u0010\u0005\u0006\u0002P\u0016\rt1XD`\u000f\u0007\u0004B\u0001b\n\b>\u0012AQqTA\u0002\u0005\u0004!i\u0003\u0005\u0003\u0005(\u001d\u0005G\u0001CCS\u0003\u0007\u0011\r\u0001\"\f\u0011\t\u0011\u001drQ\u0019\u0003\t\u000bW\u000b\u0019A1\u0001\u0005.!Aq\u0011ZA\u0002\u0001\u00049Y-\u0001\u0005dC2d'-Y2l!!\t\u0019L\"7\b0\u000e]\u0005\u0002\u0003D\u001f\u0003\u0007\u0001\rAb\u0010\t\u0011\u001d}\u00121\u0001a\u0001\u000f\u0003\n\u0011bY1oG\u0016d'j\u001c2\u0015\r\r]uQ[Dl\u0011!)y+!\u0002A\u0002\t\u0015\u0004\u0002\u0003C/\u0003\u000b\u0001\ra\"7\u0011\r\u0005M6\u0011\u001eBl\u00039\u0019\u0017M\\2fY*{'m\u0012:pkB$\u0002ba&\b`\u001e\rxq\u001d\u0005\t\u000fC\f9\u00011\u0001\u0003X\u00069qM]8va&#\u0007BCDs\u0003\u000f\u0001\n\u00111\u0001\u0004J\u0005\u00012-\u00198dK24U\u000f^;sK*{'m\u001d\u0005\t\t;\n9\u00011\u0001\bZ\u0006A2-\u00198dK2TuNY$s_V\u0004H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u001d5(\u0006BB%\u000f_\\#a\"=\u0011\t\u001dMxQ`\u0007\u0003\u000fkTAab>\bz\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u000fw\f),\u0001\u0006b]:|G/\u0019;j_:LAab@\bv\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002#\r\fgnY3m\u0015>\u00147oV5uQR\u000bw\r\u0006\u0005\u0004\u0018\"\u0015\u0001\u0012\u0002E\u0006\u0011!A9!a\u0003A\u0002\t]\u0017a\u0001;bO\"AAQLA\u0006\u0001\u00049I\u000e\u0003\u0005\t\u000e\u0005-\u0001\u0019\u0001E\b\u00035\u0019\u0017M\\2fY2,GMS8cgB1\u00111WBu\u0011#\u0001b\u0001c\u0005\t\u0016!eQBAB^\u0013\u0011A9ba/\u0003\u000fA\u0013x.\\5tKB1!q_B\u0005\u0005[\u000bQbY1oG\u0016d\u0017\t\u001c7K_\n\u001c\u0018a\u00043p\u0007\u0006t7-\u001a7BY2TuNY:\u0002\u0017\r\fgnY3m'R\fw-\u001a\u000b\u0007\u0007/C\u0019\u0003#\n\t\u0011\u0015-\u0012\u0011\u0003a\u0001\u0005KB\u0001\u0002\"\u0018\u0002\u0012\u0001\u0007q\u0011\\\u0001\u0015g\",hM\u001a7f!V\u001c\bnQ8na2,G/\u001a3\u0015\u0011\r]\u00052\u0006E\u0018\u0011gA\u0001\u0002#\f\u0002\u0014\u0001\u0007!QM\u0001\ng\",hM\u001a7f\u0013\u0012D\u0001\u0002#\r\u0002\u0014\u0001\u0007!QM\u0001\u000fg\",hM\u001a7f\u001b\u0016\u0014x-Z%e\u0011!A)$a\u0005A\u0002\t\u0015\u0014\u0001C7ba&sG-\u001a=\u0002\u001f-LG\u000e\u001c+bg.\fE\u000f^3naR$\u0002b!\u0013\t<!}\u00022\t\u0005\t\u0011{\t)\u00021\u0001\u0004\u001c\u00051A/Y:l\u0013\u0012D\u0001\u0002#\u0011\u0002\u0016\u0001\u00071\u0011J\u0001\u0010S:$XM\u001d:vaR$\u0006N]3bI\"AAQLA\u000b\u0001\u0004\u00119.\u0001\u000bsKN,(-\\5u\r\u0006LG.\u001a3Ti\u0006<Wm]\u0001\u0019gV\u0014W.\u001b;XC&$\u0018N\\4DQ&dGm\u0015;bO\u0016\u001cH\u0003BBL\u0011\u0017B\u0001\u0002#\u0014\u0002\u001a\u0001\u0007!\u0011S\u0001\u0007a\u0006\u0014XM\u001c;\u0002#\u0005\u001cG/\u001b<f\u0015>\u0014gi\u001c:Ti\u0006<W\r\u0006\u0003\tT!U\u0003CBAZ\u0007S\u0014)\u0007\u0003\u0005\u0007n\u0006m\u0001\u0019\u0001BI\u0003]A\u0017M\u001c3mK*{'m\u0012:pkB\u001c\u0015M\\2fY2,G\r\u0006\u0005\u0004\u0018\"m\u0003R\fE0\u0011!9\t/!\bA\u0002\t]\u0007\u0002CDs\u0003;\u0001\ra!\u0013\t\u0011\u0011u\u0013Q\u0004a\u0001\u000f3\fQ\u0003[1oI2,'j\u001c2UC\u001e\u001c\u0015M\\2fY2,G\r\u0006\u0005\u0004\u0018\"\u0015\u0004r\rE5\u0011!A9!a\bA\u0002\t]\u0007\u0002\u0003C/\u0003?\u0001\ra\"7\t\u0011!5\u0011q\u0004a\u0001\u0011\u001f\t\u0001\u0003[1oI2,')Z4j]\u00163XM\u001c;\u0015\r\r]\u0005r\u000eE>\u0011!!Y\"!\tA\u0002!E\u0004\u0007\u0002E:\u0011o\u0002b!a8\u0005\"!U\u0004\u0003\u0002C\u0014\u0011o\"A\u0002#\u001f\tp\u0005\u0005\t\u0011!B\u0001\t[\u0011Aa\u0018\u00136g!AAQHA\u0011\u0001\u0004!y$\u0001\u0010iC:$G.Z*qK\u000e,H.\u0019;jm\u0016$\u0016m]6Tk\nl\u0017\u000e\u001e;fIR11q\u0013EA\u0011\u001bC\u0001\u0002b\u0007\u0002$\u0001\u0007\u00012\u0011\u0019\u0005\u0011\u000bCI\t\u0005\u0004\u0002`\u0012\u0005\u0002r\u0011\t\u0005\tOAI\t\u0002\u0007\t\f\"\u0005\u0015\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`IU\"\u0004\u0002CC\u0012\u0003G\u0001\rA!\u001a\u0002?!\fg\u000e\u001a7f+:\u001c8\r[3ek2\f'\r\\3UCN\\7+\u001a;BI\u0012,G\r\u0006\u0004\u0004\u0018\"M\u0005R\u0013\u0005\t\u000bW\t)\u00031\u0001\u0003f!AQqFA\u0013\u0001\u0004\u0011)'A\u0011iC:$G.Z+og\u000eDW\rZ;mC\ndW\rV1tWN+GOU3n_Z,G\r\u0006\u0004\u0004\u0018\"m\u0005R\u0014\u0005\t\u000bW\t9\u00031\u0001\u0003f!AQqFA\u0014\u0001\u0004\u0011)'A\tiC:$G.Z*uC\u001e,g)Y5mK\u0012$\u0002ba&\t$\"\u0015\u0006r\u0015\u0005\t\u000bW\tI\u00031\u0001\u0003f!AAQLA\u0015\u0001\u0004\u00119\u000e\u0003\u0005\u0006\b\u0005%\u0002\u0019AC\u0005\u0003MA\u0017M\u001c3mKR\u000b7o[*fi\u001a\u000b\u0017\u000e\\3e)!\u00199\n#,\t0\"E\u0006\u0002\u0003C~\u0003W\u0001\r\u0001\"@\t\u0011\u0011u\u00131\u0006a\u0001\u0005/D\u0001\"b\u0002\u0002,\u0001\u0007Q\u0011B\u0001\u001aG2,\u0017M\\+q\u0003\u001a$XM]*dQ\u0016$W\u000f\\3s'R|\u0007/A\niC:$G.Z$fiR\u000b7o\u001b*fgVdG\u000f\u0006\u0003\u0004\u0018\"e\u0006\u0002\u0003C\u001f\u0003_\u0001\r\u0001b\u0010\u0002%!\fg\u000e\u001a7f\u0015>\u00147+\u001e2nSR$X\r\u001a\u000b\u0013\u0007/Cy\f#1\tP\"\u0015\br\u001dEu\u0011gDi\u0010\u0003\u0005\u00060\u0006E\u0002\u0019\u0001B3\u0011!A\u0019-!\rA\u0002!\u0015\u0017\u0001\u00034j]\u0006d'\u000b\u0012#1\t!\u001d\u00072\u001a\t\u0007\u000b\u000b*I\u0005#3\u0011\t\u0011\u001d\u00022\u001a\u0003\r\u0011\u001bD\t-!A\u0001\u0002\u000b\u0005AQ\u0006\u0002\u0005?\u0012*T\u0007\u0003\u0005\u0007\u0010\u0005E\u0002\u0019\u0001Eia\u0011A\u0019\u000e#9\u0011\u0015\u0005MfQ\u0003D\r\u0011+Dy\u000e\r\u0003\tX\"m\u0007C\u0002B|\rGAI\u000e\u0005\u0003\u0005(!mG\u0001\u0004Eo\u0011\u001f\f\t\u0011!A\u0003\u0002\u00115\"\u0001B0%kY\u0002B\u0001b\n\tb\u0012a\u00012\u001dEh\u0003\u0003\u0005\tQ!\u0001\u0005.\t!q\fJ\u001b8\u0011!1)$!\rA\u0002\u0019]\u0002\u0002\u0003D\u001f\u0003c\u0001\rAb\u0010\t\u0011!-\u0018\u0011\u0007a\u0001\u0011[\f\u0001\u0002\\5ti\u0016tWM\u001d\t\u0005\u0003?Dy/\u0003\u0003\tr\u0006}%a\u0003&pE2K7\u000f^3oKJD\u0001\u0002#>\u00022\u0001\u0007\u0001r_\u0001\nCJ$\u0018NZ1diN\u0004B!a4\tz&!\u00012`AR\u00059QuNY!si&4\u0017m\u0019;TKRD\u0001bb\u0010\u00022\u0001\u0007q\u0011I\u0001\u0018Q\u0006tG\r\\3NCB\u001cF/Y4f'V\u0014W.\u001b;uK\u0012$bba&\n\u0004%\u0015\u0011RDE\u0010\u0013CI\u0019\u0003\u0003\u0005\u00060\u0006M\u0002\u0019\u0001B3\u0011!99,a\rA\u0002%\u001d\u0001\u0007CE\u0005\u0013\u001bI\u0019\"#\u0007\u0011\u0015\u0005=W1ME\u0006\u0013#I9\u0002\u0005\u0003\u0005(%5A\u0001DE\b\u0013\u000b\t\t\u0011!A\u0003\u0002\u00115\"\u0001B0%ka\u0002B\u0001b\n\n\u0014\u0011a\u0011RCE\u0003\u0003\u0003\u0005\tQ!\u0001\u0005.\t!q\fJ\u001b:!\u0011!9##\u0007\u0005\u0019%m\u0011RAA\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\t}#c\u0007\r\u0005\t\r{\t\u0019\u00041\u0001\u0007@!A\u00012^A\u001a\u0001\u0004Ai\u000f\u0003\u0005\tv\u0006M\u0002\u0019\u0001E|\u0011!9y$a\rA\u0002\u001d\u0005\u0013aC:vE6LGo\u0015;bO\u0016$Baa&\n*!AaQ^A\u001b\u0001\u0004\u0011\t*A\u000fbI\u0012\u0004\u0016p\u00159be.\u001cuN\u001c4jON$v\u000e\u0015:pa\u0016\u0014H/[3t)\u0019\u00199*c\f\n2!AaQ^A\u001c\u0001\u0004\u0011\t\n\u0003\u0005\b@\u0005]\u0002\u0019AD!\u0003!\u0002(/\u001a9be\u0016\u001c\u0006.\u001e4gY\u0016\u001cVM\u001d<jG\u0016\u001chi\u001c:TQV4g\r\\3NCB\u001cF/Y4f)\u0011\u00199*c\u000e\t\u0011\u00195\u0018\u0011\ba\u0001\u0005?\u000b1eY8oM&<WO]3TQV4g\r\\3QkNDW*\u001a:hKJdunY1uS>t7\u000f\u0006\u0003\u0004\u0018&u\u0002\u0002\u0003Dw\u0003w\u0001\rAa(\u0002%M,(-\\5u\u001b&\u001c8/\u001b8h)\u0006\u001c8n\u001d\u000b\u0007\u0007/K\u0019%#\u0012\t\u0011\u00195\u0018Q\ba\u0001\u0005#C\u0001\"b,\u0002>\u0001\u0007!QM\u0001\u0013kB$\u0017\r^3BG\u000e,X.\u001e7bi>\u00148\u000f\u0006\u0003\u0004\u0018&-\u0003\u0002CE'\u0003\u007f\u0001\r!c\u0014\u0002\u000b\u00154XM\u001c;\u0011\t\u0005}\u0017\u0012K\u0005\u0005\u0013'\nyJA\bD_6\u0004H.\u001a;j_:,e/\u001a8u\u0003-\u0001xn\u001d;UCN\\WI\u001c3\u0015\t\r]\u0015\u0012\f\u0005\t\u0013\u001b\n\t\u00051\u0001\nP\u0005I2\u000f[8vY\u0012Le\u000e^3seV\u0004H\u000fV1tWRC'/Z1e)\u0011\u0019I%c\u0018\t\u0011\u001d5\u00111\ta\u0001\u0005[\u000bAe\u00195fG.\fe\u000eZ*dQ\u0016$W\u000f\\3TQV4g\r\\3NKJ<WMR5oC2L'0\u001a\u000b\u0005\u0007/K)\u0007\u0003\u0005\nh\u0005\u0015\u0003\u0019\u0001BP\u00031\u0019\b.\u001e4gY\u0016\u001cF/Y4f\u0003QA\u0017M\u001c3mKR\u000b7o[\"p[BdW\r^5p]R!1qSE7\u0011!Ii%a\u0012A\u0002%=\u0013aF2pY2,7\r^*vG\u000e,W\rZ5oON#\u0018mZ3t)\u0011\u0011I,c\u001d\t\u0011%U\u0014\u0011\na\u0001\u0005?\u000b\u0001\"\\1q'R\fw-Z\u0001\u001eC\n|'\u000f^*uC\u001e,w+\u001b;i\u0013:4\u0018\r\\5e%>dGNQ1dWR!!\u0011XE>\u0011!Ii(a\u0013A\u0002\te\u0016\u0001E:uC\u001e,7\u000fV8S_2d'-Y2l\u0003%J7/\u0012=fGV$xN\u001d#fG>lW.[:tS>t\u0017N\\4Pe\u0012+7m\\7nSN\u001c\u0018n\u001c8fIR11\u0011JEB\u0013\u000bC\u0001\"!7\u0002N\u0001\u0007\u0011Q\u001c\u0005\t\u0013\u000f\u000bi\u00051\u0001\u0005.\u0006I!-\\!eIJ,7o]\u0001\u001dg\u000eDW\rZ;mKNCWO\u001a4mK6+'oZ3GS:\fG.\u001b>f)!\u00199*#$\n\u0010&M\u0005\u0002\u0003Dw\u0003\u001f\u0002\rAa(\t\u0011%E\u0015q\na\u0001\u00077\tQ\u0001Z3mCfD!\"#&\u0002PA\u0005\t\u0019AB%\u0003Q\u0011XmZ5ti\u0016\u0014X*\u001a:hKJ+7/\u001e7ug\u000613o\u00195fIVdWm\u00155vM\u001adW-T3sO\u00164\u0015N\\1mSj,G\u0005Z3gCVdG\u000fJ\u001a\u0002)\u0019Lg.\u00197ju\u0016\u001c\u0006.\u001e4gY\u0016lUM]4f)\u0019\u00199*#(\n \"AaQ^A*\u0001\u0004\u0011y\n\u0003\u0006\n\u0016\u0006M\u0003\u0013!a\u0001\u0007\u0013\naDZ5oC2L'0Z*ik\u001a4G.Z'fe\u001e,G\u0005Z3gCVdG\u000f\n\u001a\u0002C\r\fgnY3m\r&t\u0017\r\\5{KNCWO\u001a4mK6+'oZ3GkR,(/Z:\u0015\r\r]\u0015rUE^\u0011!II+a\u0016A\u0002%-\u0016a\u00024viV\u0014Xm\u001d\t\u0007\u0005o\u001cI!#,1\t%=\u0016r\u0017\t\u0007\u0007WJ\t,#.\n\t%M&\u0011\u000b\u0002\u0007\rV$XO]3\u0011\t\u0011\u001d\u0012r\u0017\u0003\r\u0013sK9+!A\u0001\u0002\u000b\u0005AQ\u0006\u0002\u0005?\u001224\u0007\u0003\u0005\n>\u0006]\u0003\u0019AB\u000e\u0003-!W\r\\1z\u0013:\u001cVmY:\u0002AA\u0014xnY3tgNCWO\u001a4mK6\u000b\u0007o\u0015;bO\u0016\u001cu.\u001c9mKRLwN\u001c\u000b\u0005\u0007/K\u0019\r\u0003\u0005\nh\u0005e\u0003\u0019\u0001BP\u0003mA\u0017M\u001c3mKJ+w-[:uKJlUM]4f'R\fG/^:fgR11qSEe\u0013\u0017D\u0001B\"<\u0002\\\u0001\u0007!q\u0014\u0005\t\u0013\u001b\fY\u00061\u0001\nP\u0006iQ.\u001a:hKN#\u0018\r^;tKN\u0004bAa>\u0004\n%E\u0007\u0003CAZ\t\u007f\u0013)'c5\u0011\t\u0005}\u0017R[\u0005\u0005\u0013/\fyJA\u0006NKJ<Wm\u0015;biV\u001c\u0018a\u00075b]\u0012dWm\u00155vM\u001adW-T3sO\u00164\u0015N\\1mSj,G\r\u0006\u0004\u0004\u0018&u\u0017r\u001c\u0005\t\r[\fi\u00061\u0001\u0003 \"A\u0001\u0012GA/\u0001\u0004\u0011)'\u0001\u000eiC:$G.Z*ik\u001a4G.\u001a)vg\"\u001cu.\u001c9mKR,G\r\u0006\u0005\u0004\u0018&\u0015\u0018r]Eu\u0011!Ai#a\u0018A\u0002\t\u0015\u0004\u0002\u0003E\u0019\u0003?\u0002\rA!\u001a\t\u0011!U\u0012q\fa\u0001\u0005K\n\u0001\u0004[1oI2,'+Z:vE6LG\u000f^3e\r\u0006LG.\u001e:f)\u0019\u00199*c<\n|\"AA1DA1\u0001\u0004I\t\u0010\r\u0003\nt&]\bCBAp\tCI)\u0010\u0005\u0003\u0005(%]H\u0001DE}\u0013_\f\t\u0011!A\u0003\u0002\u00115\"\u0001B0%mQB\u0001B\"<\u0002b\u0001\u0007!\u0011S\u0001\u001b[\u0006\u00148.T1q'R\fw-\u001a&pEN\f5OR5oSNDW\r\u001a\u000b\u0005\u0007/S\t\u0001\u0003\u0005\nh\u0005\r\u0004\u0019\u0001BP\u0003IA\u0017M\u001c3mK\u0016CXmY;u_Jdun\u001d;\u0015\r\r]%r\u0001F\u0005\u0011!!)*!\u001aA\u0002\t]\u0007\u0002\u0003F\u0006\u0003K\u0002\ra\"7\u0002\u0015]|'o[3s\u0011>\u001cH/\u0001\u0012sK6|g/Z#yK\u000e,Ho\u001c:B]\u0012,fN]3hSN$XM](viB,Ho\u001d\u000b\r\u0007/S\tBc\u0005\u000b\u0018)m!\u0012\u0005\u0005\t\t+\u000b9\u00071\u0001\u0003X\"A!RCA4\u0001\u0004\u0019I%\u0001\u0005gS2,Gj\\:u\u0011!QI\"a\u001aA\u0002\u001de\u0017a\u00065pgR$v.\u00168sK\u001eL7\u000f^3s\u001fV$\b/\u001e;t\u0011)Qi\"a\u001a\u0011\u0002\u0003\u0007!rD\u0001\u000b[\u0006L(-Z#q_\u000eD\u0007CBAZ\u0007S\u001cY\u0002\u0003\u0006\u000b$\u0005\u001d\u0004\u0013!a\u0001\u0007\u0013\n!$[4o_J,7\u000b[;gM2,g)\u001b7f\u0019>\u001cH/\u00129pG\"\fAF]3n_Z,W\t_3dkR|'/\u00118e+:\u0014XmZ5ti\u0016\u0014x*\u001e;qkR\u001cH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005)%\"\u0006\u0002F\u0010\u000f_\fAF]3n_Z,W\t_3dkR|'/\u00118e+:\u0014XmZ5ti\u0016\u0014x*\u001e;qkR\u001cH\u0005Z3gCVdG\u000fJ\u001b\u0002'!\fg\u000e\u001a7f/>\u00148.\u001a:SK6|g/\u001a3\u0015\u0011\r]%\u0012\u0007F\u001a\u0015kA\u0001\u0002b9\u0002n\u0001\u0007!q\u001b\u0005\t\tO\fi\u00071\u0001\u0003X\"AA1^A7\u0001\u0004\u00119.A\niC:$G.Z#yK\u000e,Ho\u001c:BI\u0012,G\r\u0006\u0004\u0004\u0018*m\"R\b\u0005\t\t+\u000by\u00071\u0001\u0003X\"AAq]A8\u0001\u0004\u00119.A\fiC:$G.Z*uC\u001e,7)\u00198dK2d\u0017\r^5p]R11q\u0013F\"\u0015\u000bB\u0001\"b\u000b\u0002r\u0001\u0007!Q\r\u0005\t\t;\n\t\b1\u0001\bZ\u0006)\u0002.\u00198eY\u0016TuNY\"b]\u000e,G\u000e\\1uS>tGCBBL\u0015\u0017Ri\u0005\u0003\u0005\u00060\u0006M\u0004\u0019\u0001B3\u0011!!i&a\u001dA\u0002\u001de\u0017aE7be.\u001cF/Y4f\u0003N4\u0015N\\5tQ\u0016$G\u0003CBL\u0015'R)F#\u0017\t\u0011\u00195\u0018Q\u000fa\u0001\u0005#C!Bc\u0016\u0002vA\u0005\t\u0019ADm\u00031)'O]8s\u001b\u0016\u001c8/Y4f\u0011)QY&!\u001e\u0011\u0002\u0003\u00071\u0011J\u0001\no&dGNU3uef\fQ$\\1sWN#\u0018mZ3Bg\u001aKg.[:iK\u0012$C-\u001a4bk2$HEM\u000b\u0003\u0015CRCa\"7\bp\u0006iR.\u0019:l'R\fw-Z!t\r&t\u0017n\u001d5fI\u0012\"WMZ1vYR$3'A\u0006ti\u0006<WMR1jY\u0016$GCBBL\u0015SRY\u0007\u0003\u0005\u0006,\u0005m\u0004\u0019\u0001B3\u0011!!i&a\u001fA\u0002\t]\u0017AC1c_J$8\u000b^1hKRA1q\u0013F9\u0015kR9\b\u0003\u0005\u000bt\u0005u\u0004\u0019\u0001BI\u0003-1\u0017-\u001b7fIN#\u0018mZ3\t\u0011\u0011u\u0013Q\u0010a\u0001\u0005/D\u0001\"b\u0002\u0002~\u0001\u0007Q\u0011B\u0001#kB$\u0017\r^3Ti\u0006<W-\u00138g_\u001a{'\u000fU;tQ\n\u000b7/\u001a3TQV4g\r\\3\u0015\t\r]%R\u0010\u0005\t\r[\fy\b1\u0001\u0003\u0012\u0006q2-\u00198dK2\u0014VO\u001c8j]\u001eLe\u000eZ3qK:$WM\u001c;Ti\u0006<Wm\u001d\u000b\u0007\u0007\u0013R\u0019I#\"\t\u0011\u001d5\u0011\u0011\u0011a\u0001\u0005[C\u0001\u0002\"\u0018\u0002\u0002\u0002\u0007!q[\u0001\u001cM\u0006LGNS8c\u0003:$\u0017J\u001c3fa\u0016tG-\u001a8u'R\fw-Z:\u0015\r\r]%2\u0012FG\u0011!9i!a!A\u0002\t5\u0006\u0002\u0003FH\u0003\u0007\u0003\rA#%\u0002\u000b\u0015\u0014(o\u001c:\u0011\t\t](2S\u0005\u0005\u0015+\u001b)AA\u0005Fq\u000e,\u0007\u000f^5p]\u0006q1\u000f^1hK\u0012+\u0007/\u001a8eg>sGCBB%\u00157Si\n\u0003\u0005\u0007n\u0006\u0015\u0005\u0019\u0001BI\u0011!Qy*!\"A\u0002\tE\u0015A\u0002;be\u001e,G/\u0001\thKR\u0004&/\u001a4feJ,G\rT8dgR11q\u0001FS\u0015cC\u0001\"b\u0010\u0002\b\u0002\u0007!r\u0015\u0019\u0005\u0015SSi\u000b\u0005\u0004\u0006F\u0015%#2\u0016\t\u0005\tOQi\u000b\u0002\u0007\u000b0*\u0015\u0016\u0011!A\u0001\u0006\u0003!iC\u0001\u0003`IYB\u0004\u0002\u0003FZ\u0003\u000f\u0003\rA!\u001a\u0002\u0013A\f'\u000f^5uS>t\u0017\u0001G4fiB\u0013XMZ3se\u0016$Gj\\2t\u0013:$XM\u001d8bYRA1q\u0001F]\u0015\u000bT9\r\u0003\u0005\u0006@\u0005%\u0005\u0019\u0001F^a\u0011QiL#1\u0011\r\u0015\u0015S\u0011\nF`!\u0011!9C#1\u0005\u0019)\r'\u0012XA\u0001\u0002\u0003\u0015\t\u0001\"\f\u0003\t}#c'\u000f\u0005\t\u0015g\u000bI\t1\u0001\u0003f!A!\u0012ZAE\u0001\u0004QY-A\u0004wSNLG/\u001a3\u0011\r\tU$Q\u0011Fg!!\t\u0019\fb0\u000bP\n\u0015\u0004\u0007\u0002Fi\u0015+\u0004b!\"\u0012\u0006J)M\u0007\u0003\u0002C\u0014\u0015+$ABc6\u000bH\u0006\u0005\t\u0011!B\u0001\t[\u0011Aa\u0018\u00138a\u0005IR.\u0019:l\u001b\u0006\u00048\u000b^1hK*{'-Q:GS:L7\u000f[3e)\u0019\u00199J#8\u000b`\"AqQBAF\u0001\u0004\u0011i\u000b\u0003\u0005\u000bb\u0006-\u0005\u0019ADX\u0003\u0015\u0019H/\u0019;t\u0003\u0011\u0019Ho\u001c9\u0015\t\r]%r\u001d\u0005\u000b\u0015S\fi\t%AA\u0002\t\u0015\u0014\u0001C3ySR\u001cu\u000eZ3\u0002\u001dM$x\u000e\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!r\u001e\u0016\u0005\u0005K:y/\u0001\u0007E\u0003\u001e\u001b6\r[3ek2,'\u000f\u0005\u0003\u0002`\u0006M5\u0003BAJ\u0003c#\"Ac=\u0002!I+5+\u0016\"N\u0013R{F+S'F\u001fV#\u0016!\u0005*F'V\u0013U*\u0013+`)&kUiT+UA\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uI]*\"a#\u0001+\t\t=qq\u001e"
)
public class DAGScheduler implements Logging {
   private Option externalShuffleClient;
   private final SparkContext sc;
   private final TaskScheduler taskScheduler;
   private final LiveListenerBus listenerBus;
   private final MapOutputTrackerMaster mapOutputTracker;
   private final BlockManagerMaster blockManagerMaster;
   private final SparkEnv env;
   private final Clock clock;
   private final DAGSchedulerSource metricsSource;
   private final AtomicInteger nextJobId;
   private final AtomicInteger nextStageId;
   private final HashMap jobIdToStageIds;
   private final HashMap stageIdToStage;
   private final HashMap shuffleIdToMapStage;
   private final HashMap jobIdToActiveJob;
   private final HashSet waitingStages;
   private final HashSet runningStages;
   private final HashSet failedStages;
   private final HashSet activeJobs;
   private final LimitedSizeFIFOSet cancelledJobGroups;
   private final HashMap cacheLocs;
   private final HashMap executorFailureEpoch;
   private final HashMap shuffleFileLostEpoch;
   private final OutputCommitCoordinator outputCommitCoordinator;
   private final SerializerInstance closureSerializer;
   private final boolean disallowStageRetryForTest;
   private final boolean shouldMergeResourceProfiles;
   private final boolean unRegisterOutputOnHostOnFetchFailure;
   private final int maxConsecutiveStageAttempts;
   private final int maxStageAttempts;
   private final boolean ignoreDecommissionFetchFailure;
   private final ConcurrentHashMap barrierJobIdToNumTasksCheckFailures;
   private final long timeIntervalNumTasksCheck;
   private final int maxFailureNumTasksCheck;
   private final ScheduledExecutorService messageScheduler;
   private DAGSchedulerEventProcessLoop eventProcessLoop;
   private final boolean pushBasedShuffleEnabled;
   private final FiniteDuration blockManagerMasterDriverHeartbeatTimeout;
   private final long shuffleMergeResultsTimeoutSec;
   private final long shuffleMergeFinalizeWaitSec;
   private final long shuffleMergeWaitMinSizeThreshold;
   private final double shufflePushMinRatio;
   private final int shuffleMergeFinalizeNumThreads;
   private final int shuffleFinalizeRpcThreads;
   private final ScheduledExecutorService shuffleMergeFinalizeScheduler;
   private final ExecutorService shuffleSendFinalizeRpcExecutor;
   private final boolean trackingCacheVisibility;
   private final boolean legacyAbortStageAfterKillTasks;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static Clock $lessinit$greater$default$7() {
      return DAGScheduler$.MODULE$.$lessinit$greater$default$7();
   }

   public static int RESUBMIT_TIMEOUT() {
      return DAGScheduler$.MODULE$.RESUBMIT_TIMEOUT();
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

   public SparkContext sc() {
      return this.sc;
   }

   public TaskScheduler taskScheduler() {
      return this.taskScheduler;
   }

   public DAGSchedulerSource metricsSource() {
      return this.metricsSource;
   }

   public AtomicInteger nextJobId() {
      return this.nextJobId;
   }

   public int numTotalJobs() {
      return this.nextJobId().get();
   }

   private AtomicInteger nextStageId() {
      return this.nextStageId;
   }

   public HashMap jobIdToStageIds() {
      return this.jobIdToStageIds;
   }

   public HashMap stageIdToStage() {
      return this.stageIdToStage;
   }

   public HashMap shuffleIdToMapStage() {
      return this.shuffleIdToMapStage;
   }

   public HashMap jobIdToActiveJob() {
      return this.jobIdToActiveJob;
   }

   public HashSet waitingStages() {
      return this.waitingStages;
   }

   public HashSet runningStages() {
      return this.runningStages;
   }

   public HashSet failedStages() {
      return this.failedStages;
   }

   public HashSet activeJobs() {
      return this.activeJobs;
   }

   public LimitedSizeFIFOSet cancelledJobGroups() {
      return this.cancelledJobGroups;
   }

   private HashMap cacheLocs() {
      return this.cacheLocs;
   }

   private HashMap executorFailureEpoch() {
      return this.executorFailureEpoch;
   }

   private HashMap shuffleFileLostEpoch() {
      return this.shuffleFileLostEpoch;
   }

   public OutputCommitCoordinator outputCommitCoordinator() {
      return this.outputCommitCoordinator;
   }

   private SerializerInstance closureSerializer() {
      return this.closureSerializer;
   }

   private boolean disallowStageRetryForTest() {
      return this.disallowStageRetryForTest;
   }

   private boolean shouldMergeResourceProfiles() {
      return this.shouldMergeResourceProfiles;
   }

   public boolean unRegisterOutputOnHostOnFetchFailure() {
      return this.unRegisterOutputOnHostOnFetchFailure;
   }

   public int maxConsecutiveStageAttempts() {
      return this.maxConsecutiveStageAttempts;
   }

   public int maxStageAttempts() {
      return this.maxStageAttempts;
   }

   public boolean ignoreDecommissionFetchFailure() {
      return this.ignoreDecommissionFetchFailure;
   }

   public ConcurrentHashMap barrierJobIdToNumTasksCheckFailures() {
      return this.barrierJobIdToNumTasksCheckFailures;
   }

   private long timeIntervalNumTasksCheck() {
      return this.timeIntervalNumTasksCheck;
   }

   private int maxFailureNumTasksCheck() {
      return this.maxFailureNumTasksCheck;
   }

   private ScheduledExecutorService messageScheduler() {
      return this.messageScheduler;
   }

   public DAGSchedulerEventProcessLoop eventProcessLoop() {
      return this.eventProcessLoop;
   }

   public void eventProcessLoop_$eq(final DAGSchedulerEventProcessLoop x$1) {
      this.eventProcessLoop = x$1;
   }

   public void setEventProcessLoop(final DAGSchedulerEventProcessLoop loop) {
      this.eventProcessLoop_$eq(loop);
   }

   private boolean pushBasedShuffleEnabled() {
      return this.pushBasedShuffleEnabled;
   }

   private FiniteDuration blockManagerMasterDriverHeartbeatTimeout() {
      return this.blockManagerMasterDriverHeartbeatTimeout;
   }

   private long shuffleMergeResultsTimeoutSec() {
      return this.shuffleMergeResultsTimeoutSec;
   }

   private long shuffleMergeFinalizeWaitSec() {
      return this.shuffleMergeFinalizeWaitSec;
   }

   private long shuffleMergeWaitMinSizeThreshold() {
      return this.shuffleMergeWaitMinSizeThreshold;
   }

   private double shufflePushMinRatio() {
      return this.shufflePushMinRatio;
   }

   private int shuffleMergeFinalizeNumThreads() {
      return this.shuffleMergeFinalizeNumThreads;
   }

   private int shuffleFinalizeRpcThreads() {
      return this.shuffleFinalizeRpcThreads;
   }

   private Option externalShuffleClient$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.externalShuffleClient = (Option)(this.pushBasedShuffleEnabled() ? new Some(this.env.blockManager().blockStoreClient()) : .MODULE$);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.externalShuffleClient;
   }

   private Option externalShuffleClient() {
      return !this.bitmap$0 ? this.externalShuffleClient$lzycompute() : this.externalShuffleClient;
   }

   private ScheduledExecutorService shuffleMergeFinalizeScheduler() {
      return this.shuffleMergeFinalizeScheduler;
   }

   private ExecutorService shuffleSendFinalizeRpcExecutor() {
      return this.shuffleSendFinalizeRpcExecutor;
   }

   private boolean trackingCacheVisibility() {
      return this.trackingCacheVisibility;
   }

   private boolean legacyAbortStageAfterKillTasks() {
      return this.legacyAbortStageAfterKillTasks;
   }

   public void taskStarted(final Task task, final TaskInfo taskInfo) {
      this.eventProcessLoop().post(new BeginEvent(task, taskInfo));
   }

   public void taskGettingResult(final TaskInfo taskInfo) {
      this.eventProcessLoop().post(new GettingResultEvent(taskInfo));
   }

   public void taskEnded(final Task task, final TaskEndReason reason, final Object result, final Seq accumUpdates, final long[] metricPeaks, final TaskInfo taskInfo) {
      this.eventProcessLoop().post(new CompletionEvent(task, reason, result, accumUpdates, metricPeaks, taskInfo));
   }

   public boolean executorHeartbeatReceived(final String execId, final Tuple4[] accumUpdates, final BlockManagerId blockManagerId, final scala.collection.mutable.Map executorUpdates) {
      this.listenerBus.post(new SparkListenerExecutorMetricsUpdate(execId, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(accumUpdates).toImmutableArraySeq(), executorUpdates));
      return BoxesRunTime.unboxToBoolean(this.blockManagerMaster.driverHeartbeatEndPoint().askSync(new BlockManagerMessages.BlockManagerHeartbeat(blockManagerId), new RpcTimeout(this.blockManagerMasterDriverHeartbeatTimeout(), "BlockManagerHeartbeat"), scala.reflect.ClassTag..MODULE$.Boolean()));
   }

   public void executorLost(final String execId, final ExecutorLossReason reason) {
      this.eventProcessLoop().post(new ExecutorLost(execId, reason));
   }

   public void workerRemoved(final String workerId, final String host, final String message) {
      this.eventProcessLoop().post(new WorkerRemoved(workerId, host, message));
   }

   public void executorAdded(final String execId, final String host) {
      this.eventProcessLoop().post(new ExecutorAdded(execId, host));
   }

   public void taskSetFailed(final TaskSet taskSet, final String reason, final Option exception) {
      this.eventProcessLoop().post(new TaskSetFailed(taskSet, reason, exception));
   }

   public void speculativeTaskSubmitted(final Task task, final int taskIndex) {
      this.eventProcessLoop().post(new SpeculativeTaskSubmitted(task, taskIndex));
   }

   public void unschedulableTaskSetAdded(final int stageId, final int stageAttemptId) {
      this.eventProcessLoop().post(new UnschedulableTaskSetAdded(stageId, stageAttemptId));
   }

   public void unschedulableTaskSetRemoved(final int stageId, final int stageAttemptId) {
      this.eventProcessLoop().post(new UnschedulableTaskSetRemoved(stageId, stageAttemptId));
   }

   public IndexedSeq getCacheLocs(final RDD rdd) {
      IndexedSeq var3;
      synchronized(rdd.stateLock()) {
         synchronized(this.cacheLocs()){}

         IndexedSeq var5;
         try {
            if (!this.cacheLocs().contains(BoxesRunTime.boxToInteger(rdd.id()))) {
               IndexedSeq var13;
               label68: {
                  label67: {
                     StorageLevel var10000 = rdd.getStorageLevel();
                     StorageLevel var7 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
                     if (var10000 == null) {
                        if (var7 == null) {
                           break label67;
                        }
                     } else if (var10000.equals(var7)) {
                        break label67;
                     }

                     BlockId[] blockIds = (BlockId[])scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions())).map((index) -> $anonfun$getCacheLocs$2(rdd, BoxesRunTime.unboxToInt(index))).toArray(scala.reflect.ClassTag..MODULE$.apply(BlockId.class));
                     var13 = (IndexedSeq)this.blockManagerMaster.getLocations(blockIds).map((bms) -> (Seq)bms.map((bm) -> TaskLocation$.MODULE$.apply(bm.host(), bm.executorId())));
                     break label68;
                  }

                  var13 = (IndexedSeq)scala.package..MODULE$.IndexedSeq().fill(rdd.partitions().length, () -> scala.collection.immutable.Nil..MODULE$);
               }

               IndexedSeq locs = var13;
               this.cacheLocs().update(BoxesRunTime.boxToInteger(rdd.id()), locs);
            }

            var5 = (IndexedSeq)this.cacheLocs().apply(BoxesRunTime.boxToInteger(rdd.id()));
         } catch (Throwable var11) {
            throw var11;
         }

         var3 = var5;
      }

      return var3;
   }

   private void clearCacheLocs() {
      synchronized(this.cacheLocs()){}

      try {
         this.cacheLocs().clear();
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private ShuffleMapStage getOrCreateShuffleMapStage(final ShuffleDependency shuffleDep, final int firstJobId) {
      Option var4 = this.shuffleIdToMapStage().get(BoxesRunTime.boxToInteger(shuffleDep.shuffleId()));
      if (var4 instanceof Some var5) {
         ShuffleMapStage stage = (ShuffleMapStage)var5.value();
         return stage;
      } else if (.MODULE$.equals(var4)) {
         this.getMissingAncestorShuffleDependencies(shuffleDep.rdd()).foreach((dep) -> !this.shuffleIdToMapStage().contains(BoxesRunTime.boxToInteger(dep.shuffleId())) ? this.createShuffleMapStage(dep, firstJobId) : BoxedUnit.UNIT);
         return this.createShuffleMapStage(shuffleDep, firstJobId);
      } else {
         throw new MatchError(var4);
      }
   }

   private void checkBarrierStageWithRDDChainPattern(final RDD rdd, final int numTasksInStage) {
      if (rdd.isBarrier() && !this.traverseParentRDDsWithinStage(rdd, (r) -> BoxesRunTime.boxToBoolean($anonfun$checkBarrierStageWithRDDChainPattern$1(numTasksInStage, r)))) {
         throw SparkCoreErrors$.MODULE$.barrierStageWithRDDChainPatternError();
      }
   }

   public ShuffleMapStage createShuffleMapStage(final ShuffleDependency shuffleDep, final int jobId) {
      RDD rdd = shuffleDep.rdd();
      Tuple2 var6 = this.getShuffleDependenciesAndResourceProfiles(rdd);
      if (var6 != null) {
         HashSet shuffleDeps = (HashSet)var6._1();
         HashSet resourceProfiles = (HashSet)var6._2();
         Tuple2 var5 = new Tuple2(shuffleDeps, resourceProfiles);
         HashSet shuffleDeps = (HashSet)var5._1();
         HashSet resourceProfiles = (HashSet)var5._2();
         ResourceProfile resourceProfile = this.mergeResourceProfilesForStage(resourceProfiles);
         this.checkBarrierStageWithDynamicAllocation(rdd);
         this.checkBarrierStageWithNumSlots(rdd, resourceProfile);
         this.checkBarrierStageWithRDDChainPattern(rdd, rdd.getNumPartitions());
         int numTasks = rdd.partitions().length;
         List parents = this.getOrCreateParentStages(shuffleDeps, jobId);
         int id = this.nextStageId().getAndIncrement();
         ShuffleMapStage stage = new ShuffleMapStage(id, rdd, numTasks, parents, jobId, rdd.creationSite(), shuffleDep, this.mapOutputTracker, resourceProfile.id());
         this.stageIdToStage().update(BoxesRunTime.boxToInteger(id), stage);
         this.shuffleIdToMapStage().update(BoxesRunTime.boxToInteger(shuffleDep.shuffleId()), stage);
         this.updateJobIdStageIdMaps(jobId, stage);
         if (!this.mapOutputTracker.containsShuffle(shuffleDep.shuffleId())) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering RDD ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(rdd.id()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") as input to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CREATION_SITE..MODULE$, rdd.getCreationSite())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"shuffle ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleDep.shuffleId()))}))))));
            this.mapOutputTracker.registerShuffle(shuffleDep.shuffleId(), rdd.partitions().length, shuffleDep.partitioner().numPartitions());
         }

         return stage;
      } else {
         throw new MatchError(var6);
      }
   }

   private void checkBarrierStageWithDynamicAllocation(final RDD rdd) {
      if (rdd.isBarrier() && Utils$.MODULE$.isDynamicAllocationEnabled(this.sc().conf())) {
         throw SparkCoreErrors$.MODULE$.barrierStageWithDynamicAllocationError();
      }
   }

   private void checkBarrierStageWithNumSlots(final RDD rdd, final ResourceProfile rp) {
      if (rdd.isBarrier()) {
         int numPartitions = rdd.getNumPartitions();
         int maxNumConcurrentTasks = this.sc().maxNumConcurrentTasks(rp);
         if (numPartitions > maxNumConcurrentTasks) {
            throw SparkCoreErrors$.MODULE$.numPartitionsGreaterThanMaxNumConcurrentTasksError(numPartitions, maxNumConcurrentTasks);
         }
      }
   }

   public ResourceProfile mergeResourceProfilesForStage(final HashSet stageResourceProfiles) {
      this.logDebug((Function0)(() -> "Merging stage rdd profiles: " + stageResourceProfiles));
      ResourceProfile var10000;
      if (stageResourceProfiles.size() > 1) {
         if (!this.shouldMergeResourceProfiles()) {
            throw new IllegalArgumentException("Multiple ResourceProfiles specified in the RDDs for this stage, either resolve the conflicting ResourceProfiles yourself or enable " + org.apache.spark.internal.config.package$.MODULE$.RESOURCE_PROFILE_MERGE_CONFLICTS().key() + " and understand how Spark handles the merging them.");
         }

         ResourceProfile startResourceProfile = (ResourceProfile)stageResourceProfiles.head();
         ResourceProfile mergedProfile = (ResourceProfile)((IterableOnceOps)stageResourceProfiles.drop(1)).foldLeft(startResourceProfile, (a, b) -> this.mergeResourceProfiles(a, b));
         Option resProfile = this.sc().resourceProfileManager().getEquivalentProfile(mergedProfile);
         if (resProfile instanceof Some) {
            Some var8 = (Some)resProfile;
            ResourceProfile existingRp = (ResourceProfile)var8.value();
            var10000 = existingRp;
         } else {
            if (!.MODULE$.equals(resProfile)) {
               throw new MatchError(resProfile);
            }

            this.sc().resourceProfileManager().addResourceProfile(mergedProfile);
            var10000 = mergedProfile;
         }
      } else {
         var10000 = stageResourceProfiles.size() == 1 ? (ResourceProfile)stageResourceProfiles.head() : this.sc().resourceProfileManager().defaultResourceProfile();
      }

      ResourceProfile resourceProfile = var10000;
      return resourceProfile;
   }

   public ResourceProfile mergeResourceProfiles(final ResourceProfile r1, final ResourceProfile r2) {
      scala.collection.immutable.Map mergedExecKeys = (scala.collection.immutable.Map)r1.executorResources().$plus$plus(r2.executorResources());
      scala.collection.immutable.Map mergedExecReq = (scala.collection.immutable.Map)mergedExecKeys.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            ExecutorResourceRequest v = (ExecutorResourceRequest)x0$1._2();
            ExecutorResourceRequest larger = (ExecutorResourceRequest)r1.executorResources().get(k).map((x) -> x.amount() > v.amount() ? x : v).getOrElse(() -> v);
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), larger);
         } else {
            throw new MatchError(x0$1);
         }
      });
      scala.collection.immutable.Map mergedTaskKeys = (scala.collection.immutable.Map)r1.taskResources().$plus$plus(r2.taskResources());
      scala.collection.immutable.Map mergedTaskReq = (scala.collection.immutable.Map)mergedTaskKeys.map((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            TaskResourceRequest v = (TaskResourceRequest)x0$2._2();
            TaskResourceRequest larger = (TaskResourceRequest)r1.taskResources().get(k).map((x) -> x.amount() > v.amount() ? x : v).getOrElse(() -> v);
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), larger);
         } else {
            throw new MatchError(x0$2);
         }
      });
      return (ResourceProfile)(mergedExecReq.isEmpty() ? new TaskResourceProfile(mergedTaskReq) : new ResourceProfile(mergedExecReq, mergedTaskReq));
   }

   private ResultStage createResultStage(final RDD rdd, final Function2 func, final int[] partitions, final int jobId, final CallSite callSite) {
      Tuple2 var8 = this.getShuffleDependenciesAndResourceProfiles(rdd);
      if (var8 != null) {
         HashSet shuffleDeps = (HashSet)var8._1();
         HashSet resourceProfiles = (HashSet)var8._2();
         Tuple2 var7 = new Tuple2(shuffleDeps, resourceProfiles);
         HashSet shuffleDeps = (HashSet)var7._1();
         HashSet resourceProfiles = (HashSet)var7._2();
         ResourceProfile resourceProfile = this.mergeResourceProfilesForStage(resourceProfiles);
         this.checkBarrierStageWithDynamicAllocation(rdd);
         this.checkBarrierStageWithNumSlots(rdd, resourceProfile);
         this.checkBarrierStageWithRDDChainPattern(rdd, scala.Predef..MODULE$.wrapIntArray(partitions).toSet().size());
         List parents = this.getOrCreateParentStages(shuffleDeps, jobId);
         int id = this.nextStageId().getAndIncrement();
         ResultStage stage = new ResultStage(id, rdd, func, partitions, parents, jobId, callSite, resourceProfile.id());
         this.stageIdToStage().update(BoxesRunTime.boxToInteger(id), stage);
         this.updateJobIdStageIdMaps(jobId, stage);
         return stage;
      } else {
         throw new MatchError(var8);
      }
   }

   private List getOrCreateParentStages(final HashSet shuffleDeps, final int firstJobId) {
      return ((IterableOnceOps)shuffleDeps.map((shuffleDep) -> this.getOrCreateShuffleMapStage(shuffleDep, firstJobId))).toList();
   }

   private ListBuffer getMissingAncestorShuffleDependencies(final RDD rdd) {
      ListBuffer ancestors = new ListBuffer();
      HashSet visited = new HashSet();
      ListBuffer waitingForVisit = new ListBuffer();
      waitingForVisit.$plus$eq(rdd);

      while(waitingForVisit.nonEmpty()) {
         RDD toVisit = (RDD)waitingForVisit.remove(0);
         if (!visited.apply(toVisit)) {
            visited.$plus$eq(toVisit);
            Tuple2 var8 = this.getShuffleDependenciesAndResourceProfiles(toVisit);
            if (var8 == null) {
               throw new MatchError(var8);
            }

            HashSet shuffleDeps = (HashSet)var8._1();
            shuffleDeps.foreach((shuffleDep) -> {
               if (!this.shuffleIdToMapStage().contains(BoxesRunTime.boxToInteger(shuffleDep.shuffleId()))) {
                  ancestors.prepend(shuffleDep);
                  return waitingForVisit.prepend(shuffleDep.rdd());
               } else {
                  return BoxedUnit.UNIT;
               }
            });
         }
      }

      return ancestors;
   }

   public Tuple2 getShuffleDependenciesAndResourceProfiles(final RDD rdd) {
      HashSet parents = new HashSet();
      HashSet resourceProfiles = new HashSet();
      HashSet visited = new HashSet();
      ListBuffer waitingForVisit = new ListBuffer();
      waitingForVisit.$plus$eq(rdd);

      while(waitingForVisit.nonEmpty()) {
         RDD toVisit = (RDD)waitingForVisit.remove(0);
         if (!visited.apply(toVisit)) {
            visited.$plus$eq(toVisit);
            scala.Option..MODULE$.apply(toVisit.getResourceProfile()).foreach((x$4) -> (HashSet)resourceProfiles.$plus$eq(x$4));
            toVisit.dependencies().foreach((x0$1) -> {
               if (x0$1 instanceof ShuffleDependency var5) {
                  return (AbstractIterable)parents.$plus$eq(var5);
               } else {
                  return waitingForVisit.prepend(x0$1.rdd());
               }
            });
         }
      }

      return new Tuple2(parents, resourceProfiles);
   }

   private boolean traverseParentRDDsWithinStage(final RDD rdd, final Function1 predicate) {
      HashSet visited = new HashSet();
      ListBuffer waitingForVisit = new ListBuffer();
      waitingForVisit.$plus$eq(rdd);

      while(waitingForVisit.nonEmpty()) {
         RDD toVisit = (RDD)waitingForVisit.remove(0);
         if (!visited.apply(toVisit)) {
            if (!BoxesRunTime.unboxToBoolean(predicate.apply(toVisit))) {
               return false;
            }

            visited.$plus$eq(toVisit);
            toVisit.dependencies().foreach((x0$1) -> x0$1 instanceof ShuffleDependency ? BoxedUnit.UNIT : waitingForVisit.prepend(x0$1.rdd()));
         }
      }

      return true;
   }

   private List getMissingParentStages(final Stage stage) {
      HashSet missing = new HashSet();
      HashSet visited = new HashSet();
      ListBuffer waitingForVisit = new ListBuffer();
      waitingForVisit.$plus$eq(stage.rdd());

      while(waitingForVisit.nonEmpty()) {
         this.visit$1((RDD)waitingForVisit.remove(0), visited, stage, missing, waitingForVisit);
      }

      return missing.toList();
   }

   private void eagerlyComputePartitionsForRddAndAncestors(final RDD rdd) {
      long startTime = System.nanoTime();
      HashSet visitedRdds = new HashSet();
      ListBuffer waitingForVisit = new ListBuffer();
      waitingForVisit.$plus$eq(rdd);

      while(waitingForVisit.nonEmpty()) {
         visit$2((RDD)waitingForVisit.remove(0), visitedRdds, waitingForVisit);
      }

      this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("eagerlyComputePartitionsForRddAndAncestors for RDD %d took %f seconds"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(rdd.id()), BoxesRunTime.boxToDouble((double)(System.nanoTime() - startTime) / (double)1.0E9F)}))));
   }

   private void updateJobIdStageIdMaps(final int jobId, final Stage stage) {
      this.updateJobIdStageIdMapsList$1(new scala.collection.immutable..colon.colon(stage, scala.collection.immutable.Nil..MODULE$), jobId);
   }

   private void cleanupStateForJobAndIndependentStages(final ActiveJob job) {
      Option registeredStages = this.jobIdToStageIds().get(BoxesRunTime.boxToInteger(job.jobId()));
      if (!registeredStages.isEmpty() && !((HashSet)registeredStages.get()).isEmpty()) {
         ((HashMap)this.stageIdToStage().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$cleanupStateForJobAndIndependentStages$2(registeredStages, x0$1)))).foreach((x0$2) -> {
            $anonfun$cleanupStateForJobAndIndependentStages$3(this, job, x0$2);
            return BoxedUnit.UNIT;
         });
      } else {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No stages registered for job ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job.jobId()))})))));
      }

      this.jobIdToStageIds().$minus$eq(BoxesRunTime.boxToInteger(job.jobId()));
      this.jobIdToActiveJob().$minus$eq(BoxesRunTime.boxToInteger(job.jobId()));
      this.activeJobs().$minus$eq(job);
      Stage var4 = job.finalStage();
      if (var4 instanceof ResultStage var5) {
         var5.removeActiveJob();
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (var4 instanceof ShuffleMapStage var6) {
         var6.removeActiveJob(job);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var4);
      }
   }

   public JobWaiter submitJob(final RDD rdd, final Function2 func, final Seq partitions, final CallSite callSite, final Function2 resultHandler, final Properties properties) {
      int maxPartitions = rdd.partitions().length;
      partitions.find((JFunction1.mcZI.sp)(p) -> p >= maxPartitions || p < 0).foreach((p) -> $anonfun$submitJob$2(maxPartitions, BoxesRunTime.unboxToInt(p)));
      this.eagerlyComputePartitionsForRddAndAncestors(rdd);
      int jobId = this.nextJobId().getAndIncrement();
      if (partitions.isEmpty()) {
         Properties clonedProperties = Utils$.MODULE$.cloneProperties(properties);
         if (this.sc().getLocalProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION()) == null) {
            clonedProperties.setProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION(), callSite.shortForm());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         long time = this.clock.getTimeMillis();
         this.listenerBus.post(new SparkListenerJobStart(jobId, time, (Seq)scala.package..MODULE$.Seq().empty(), clonedProperties));
         this.listenerBus.post(new SparkListenerJobEnd(jobId, time, JobSucceeded$.MODULE$));
         return new JobWaiter(this, jobId, 0, resultHandler);
      } else {
         scala.Predef..MODULE$.assert(partitions.nonEmpty());
         JobWaiter waiter = new JobWaiter(this, jobId, partitions.size(), resultHandler);
         this.eventProcessLoop().post(new JobSubmitted(jobId, rdd, func, (int[])partitions.toArray(scala.reflect.ClassTag..MODULE$.Int()), callSite, waiter, JobArtifactSet$.MODULE$.getActiveOrDefault(this.sc()), Utils$.MODULE$.cloneProperties(properties)));
         return waiter;
      }
   }

   public void runJob(final RDD rdd, final Function2 func, final Seq partitions, final CallSite callSite, final Function2 resultHandler, final Properties properties) {
      long start = System.nanoTime();
      JobWaiter waiter = this.submitJob(rdd, func, partitions, callSite, resultHandler, properties);
      ThreadUtils$.MODULE$.awaitReady(waiter.completionFuture(), scala.concurrent.duration.Duration..MODULE$.Inf());
      Try var11 = (Try)waiter.completionFuture().value().get();
      if (var11 instanceof Success) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Job ", " finished: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(waiter.jobId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", took "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToDouble((double)(System.nanoTime() - start) / (double)1000000.0F))}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (var11 instanceof Failure) {
         Failure var12 = (Failure)var11;
         Throwable exception = var12.exception();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Job ", " failed: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(waiter.jobId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", took "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToDouble((double)(System.nanoTime() - start) / (double)1000000.0F))}))))));
         StackTraceElement[] callerStackTrace = (StackTraceElement[])scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()));
         exception.setStackTrace((StackTraceElement[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])exception.getStackTrace()), callerStackTrace, scala.reflect.ClassTag..MODULE$.apply(StackTraceElement.class)));
         throw exception;
      } else {
         throw new MatchError(var11);
      }
   }

   public PartialResult runApproximateJob(final RDD rdd, final Function2 func, final ApproximateEvaluator evaluator, final CallSite callSite, final long timeout, final Properties properties) {
      int jobId = this.nextJobId().getAndIncrement();
      Properties clonedProperties = Utils$.MODULE$.cloneProperties(properties);
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions()))) {
         long time = this.clock.getTimeMillis();
         this.listenerBus.post(new SparkListenerJobStart(jobId, time, (Seq)scala.collection.immutable.Nil..MODULE$, clonedProperties));
         this.listenerBus.post(new SparkListenerJobEnd(jobId, time, JobSucceeded$.MODULE$));
         return new PartialResult(evaluator.currentResult(), true);
      } else {
         this.eagerlyComputePartitionsForRddAndAncestors(rdd);
         ApproximateActionListener listener = new ApproximateActionListener(rdd, func, evaluator, timeout);
         this.eventProcessLoop().post(new JobSubmitted(jobId, rdd, func, (int[])scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions())).toArray(scala.reflect.ClassTag..MODULE$.Int()), callSite, listener, JobArtifactSet$.MODULE$.getActiveOrDefault(this.sc()), clonedProperties));
         return listener.awaitResult();
      }
   }

   public JobWaiter submitMapStage(final ShuffleDependency dependency, final Function1 callback, final CallSite callSite, final Properties properties) {
      RDD rdd = dependency.rdd();
      int jobId = this.nextJobId().getAndIncrement();
      if (rdd.partitions().length == 0) {
         throw SparkCoreErrors$.MODULE$.cannotRunSubmitMapStageOnZeroPartitionRDDError();
      } else {
         this.eagerlyComputePartitionsForRddAndAncestors(rdd);
         JobWaiter waiter = new JobWaiter(this, jobId, 1, (x$8, r) -> {
            $anonfun$submitMapStage$1(callback, BoxesRunTime.unboxToInt(x$8), r);
            return BoxedUnit.UNIT;
         });
         this.eventProcessLoop().post(new MapStageSubmitted(jobId, dependency, callSite, waiter, JobArtifactSet$.MODULE$.getActiveOrDefault(this.sc()), Utils$.MODULE$.cloneProperties(properties)));
         return waiter;
      }
   }

   public void cancelJob(final int jobId, final Option reason) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to cancel job ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(jobId))})))));
      this.eventProcessLoop().post(new JobCancelled(jobId, reason));
   }

   public void cancelJobGroup(final String groupId, final boolean cancelFutureJobs, final Option reason) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to cancel job group ", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.GROUP_ID..MODULE$, groupId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"cancelFutureJobs=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CANCEL_FUTURE_JOBS..MODULE$, BoxesRunTime.boxToBoolean(cancelFutureJobs))}))))));
      this.eventProcessLoop().post(new JobGroupCancelled(groupId, cancelFutureJobs, reason));
   }

   public boolean cancelJobGroup$default$2() {
      return false;
   }

   public void cancelJobsWithTag(final String tag, final Option reason, final Option cancelledJobs) {
      SparkContext$.MODULE$.throwIfInvalidTag(tag);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to cancel jobs with tag ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TAG..MODULE$, tag)})))));
      this.eventProcessLoop().post(new JobTagCancelled(tag, reason, cancelledJobs));
   }

   public void cancelAllJobs() {
      this.eventProcessLoop().post(AllJobsCancelled$.MODULE$);
   }

   public void doCancelAllJobs() {
      ((HashSet)this.runningStages().map((x$9) -> BoxesRunTime.boxToInteger($anonfun$doCancelAllJobs$1(x$9)))).foreach((JFunction1.mcVI.sp)(x$10) -> this.handleJobCancellation(x$10, scala.Option..MODULE$.apply("as part of cancellation of all jobs")));
      this.activeJobs().clear();
      this.jobIdToActiveJob().clear();
   }

   public void cancelStage(final int stageId, final Option reason) {
      this.eventProcessLoop().post(new StageCancelled(stageId, reason));
   }

   public void shufflePushCompleted(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      this.eventProcessLoop().post(new ShufflePushCompleted(shuffleId, shuffleMergeId, mapIndex));
   }

   public boolean killTaskAttempt(final long taskId, final boolean interruptThread, final String reason) {
      return this.taskScheduler().killTaskAttempt(taskId, interruptThread, reason);
   }

   public void resubmitFailedStages() {
      if (this.failedStages().nonEmpty()) {
         this.logInfo((Function0)(() -> "Resubmitting failed stages"));
         this.clearCacheLocs();
         Stage[] failedStagesCopy = (Stage[])this.failedStages().toArray(scala.reflect.ClassTag..MODULE$.apply(Stage.class));
         this.failedStages().clear();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(failedStagesCopy), (x$11) -> BoxesRunTime.boxToInteger($anonfun$resubmitFailedStages$2(x$11)), scala.math.Ordering.Int..MODULE$)), (stage) -> {
            $anonfun$resubmitFailedStages$3(this, stage);
            return BoxedUnit.UNIT;
         });
      }
   }

   private void submitWaitingChildStages(final Stage parent) {
      this.logTrace((Function0)(() -> "Checking if any dependencies of " + parent + " are now runnable"));
      this.logTrace((Function0)(() -> "running: " + this.runningStages()));
      this.logTrace((Function0)(() -> "waiting: " + this.waitingStages()));
      this.logTrace((Function0)(() -> "failed: " + this.failedStages()));
      Stage[] childStages = (Stage[])((IterableOnceOps)this.waitingStages().filter((x$12) -> BoxesRunTime.boxToBoolean($anonfun$submitWaitingChildStages$5(parent, x$12)))).toArray(scala.reflect.ClassTag..MODULE$.apply(Stage.class));
      this.waitingStages().$minus$minus$eq(scala.Predef..MODULE$.wrapRefArray(childStages));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(childStages), (x$13) -> BoxesRunTime.boxToInteger($anonfun$submitWaitingChildStages$6(x$13)), scala.math.Ordering.Int..MODULE$)), (stage) -> {
         $anonfun$submitWaitingChildStages$7(this, stage);
         return BoxedUnit.UNIT;
      });
   }

   private Option activeJobForStage(final Stage stage) {
      int[] jobsThatUseStage = (int[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps((int[])stage.jobIds().toArray(scala.reflect.ClassTag..MODULE$.Int())), scala.math.Ordering.Int..MODULE$);
      return scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(jobsThatUseStage), (JFunction1.mcZI.sp)(key) -> this.jobIdToActiveJob().contains(BoxesRunTime.boxToInteger(key)));
   }

   public void handleJobGroupCancelled(final String groupId, final boolean cancelFutureJobs, final Option reason) {
      if (cancelFutureJobs) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Add job group ", " into cancelled job groups"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.GROUP_ID..MODULE$, groupId)})))));
         this.cancelledJobGroups().add(groupId);
      }

      HashSet activeInGroup = (HashSet)this.activeJobs().filter((activeJob) -> BoxesRunTime.boxToBoolean($anonfun$handleJobGroupCancelled$2(groupId, activeJob)));
      if (activeInGroup.isEmpty() && !cancelFutureJobs) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to cancel job group ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.GROUP_ID..MODULE$, groupId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot find active jobs for it."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      HashSet jobIds = (HashSet)activeInGroup.map((x$15) -> BoxesRunTime.boxToInteger($anonfun$handleJobGroupCancelled$5(x$15)));
      String updatedReason = (String)reason.getOrElse(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("part of cancelled job group %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{groupId})));
      jobIds.foreach((JFunction1.mcVI.sp)(x$16) -> this.handleJobCancellation(x$16, scala.Option..MODULE$.apply(updatedReason)));
   }

   public void handleJobTagCancelled(final String tag, final Option reason, final Option cancelledJobs) {
      HashSet jobsToBeCancelled = (HashSet)this.activeJobs().filter((activeJob) -> BoxesRunTime.boxToBoolean($anonfun$handleJobTagCancelled$1(tag, activeJob)));
      String updatedReason = (String)reason.getOrElse(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("part of cancelled job tags %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tag})));
      ((HashSet)jobsToBeCancelled.map((x$18) -> BoxesRunTime.boxToInteger($anonfun$handleJobTagCancelled$6(x$18)))).foreach((JFunction1.mcVI.sp)(x$19) -> this.handleJobCancellation(x$19, scala.Option..MODULE$.apply(updatedReason)));
      cancelledJobs.map((x$20) -> x$20.success(jobsToBeCancelled.toSeq()));
   }

   public void handleBeginEvent(final Task task, final TaskInfo taskInfo) {
      this.listenerBus.post(new SparkListenerTaskStart(task.stageId(), task.stageAttemptId(), taskInfo));
   }

   public void handleSpeculativeTaskSubmitted(final Task task, final int taskIndex) {
      SparkListenerSpeculativeTaskSubmitted speculativeTaskSubmittedEvent = new SparkListenerSpeculativeTaskSubmitted(task.stageId(), task.stageAttemptId(), taskIndex, task.partitionId());
      this.listenerBus.post(speculativeTaskSubmittedEvent);
   }

   public void handleUnschedulableTaskSetAdded(final int stageId, final int stageAttemptId) {
      this.listenerBus.post(new SparkListenerUnschedulableTaskSetAdded(stageId, stageAttemptId));
   }

   public void handleUnschedulableTaskSetRemoved(final int stageId, final int stageAttemptId) {
      this.listenerBus.post(new SparkListenerUnschedulableTaskSetRemoved(stageId, stageAttemptId));
   }

   public void handleStageFailed(final int stageId, final String reason, final Option exception) {
      this.stageIdToStage().get(BoxesRunTime.boxToInteger(stageId)).foreach((x$21) -> {
         $anonfun$handleStageFailed$1(this, reason, exception, x$21);
         return BoxedUnit.UNIT;
      });
   }

   public void handleTaskSetFailed(final TaskSet taskSet, final String reason, final Option exception) {
      this.stageIdToStage().get(BoxesRunTime.boxToInteger(taskSet.stageId())).foreach((x$22) -> {
         $anonfun$handleTaskSetFailed$1(this, reason, exception, x$22);
         return BoxedUnit.UNIT;
      });
   }

   public void cleanUpAfterSchedulerStop() {
      this.activeJobs().foreach((job) -> {
         $anonfun$cleanUpAfterSchedulerStop$1(this, job);
         return BoxedUnit.UNIT;
      });
   }

   public void handleGetTaskResult(final TaskInfo taskInfo) {
      this.listenerBus.post(new SparkListenerTaskGettingResult(taskInfo));
   }

   public void handleJobSubmitted(final int jobId, final RDD finalRDD, final Function2 func, final int[] partitions, final CallSite callSite, final JobListener listener, final JobArtifactSet artifacts, final Properties properties) {
      Option jobGroupIdOpt = scala.Option..MODULE$.apply(properties).map((x$23) -> x$23.getProperty(SparkContext$.MODULE$.SPARK_JOB_GROUP_ID()));
      if (jobGroupIdOpt.exists((x$24) -> BoxesRunTime.boxToBoolean($anonfun$handleJobSubmitted$2(this, x$24)))) {
         listener.jobFailed(SparkCoreErrors$.MODULE$.sparkJobCancelledAsPartOfJobGroupError(jobId, (String)jobGroupIdOpt.get()));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skip running a job that belongs to the cancelled job group ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.GROUP_ID..MODULE$, jobGroupIdOpt.get())})))));
      } else {
         ObjectRef finalStage = ObjectRef.create((Object)null);

         try {
            finalStage.elem = this.createResultStage(finalRDD, func, partitions, jobId, callSite);
         } catch (BarrierJobSlotsNumberCheckFailed var19) {
            int numCheckFailures = BoxesRunTime.unboxToInt(this.barrierJobIdToNumTasksCheckFailures().compute(BoxesRunTime.boxToInteger(jobId), (x$25, value) -> BoxesRunTime.boxToInteger($anonfun$handleJobSubmitted$4(BoxesRunTime.unboxToInt(x$25), BoxesRunTime.unboxToInt(value)))));
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Barrier stage in job ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(jobId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requires ", " slots, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_SLOTS..MODULE$, BoxesRunTime.boxToInteger(var19.requiredConcurrentTasks()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but only ", " are available. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SLOTS..MODULE$, BoxesRunTime.boxToInteger(var19.maxConcurrentTasks()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Will retry up to ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RETRIES..MODULE$, BoxesRunTime.boxToInteger(this.maxFailureNumTasksCheck() - numCheckFailures + 1))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"more times"})))).log(scala.collection.immutable.Nil..MODULE$))));
            if (numCheckFailures <= this.maxFailureNumTasksCheck()) {
               this.messageScheduler().schedule(new Runnable(jobId, finalRDD, func, partitions, callSite, listener, artifacts, properties) {
                  // $FF: synthetic field
                  private final DAGScheduler $outer;
                  private final int jobId$3;
                  private final RDD finalRDD$1;
                  private final Function2 func$1;
                  private final int[] partitions$1;
                  private final CallSite callSite$2;
                  private final JobListener listener$1;
                  private final JobArtifactSet artifacts$1;
                  private final Properties properties$1;

                  public void run() {
                     this.$outer.eventProcessLoop().post(new JobSubmitted(this.jobId$3, this.finalRDD$1, this.func$1, this.partitions$1, this.callSite$2, this.listener$1, this.artifacts$1, this.properties$1));
                  }

                  public {
                     if (DAGScheduler.this == null) {
                        throw null;
                     } else {
                        this.$outer = DAGScheduler.this;
                        this.jobId$3 = jobId$3;
                        this.finalRDD$1 = finalRDD$1;
                        this.func$1 = func$1;
                        this.partitions$1 = partitions$1;
                        this.callSite$2 = callSite$2;
                        this.listener$1 = listener$1;
                        this.artifacts$1 = artifacts$1;
                        this.properties$1 = properties$1;
                     }
                  }
               }, this.timeIntervalNumTasksCheck(), TimeUnit.SECONDS);
               return;
            }

            this.barrierJobIdToNumTasksCheckFailures().remove(BoxesRunTime.boxToInteger(jobId));
            listener.jobFailed(var19);
            return;
         } catch (Exception var20) {
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Creating new stage failed due to exception - job: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(jobId))})))), var20);
            listener.jobFailed(var20);
            return;
         }

         this.barrierJobIdToNumTasksCheckFailures().remove(BoxesRunTime.boxToInteger(jobId));
         ActiveJob job = new ActiveJob(jobId, (ResultStage)finalStage.elem, callSite, listener, artifacts, properties);
         this.clearCacheLocs();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got job ", " (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job.jobId())), new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", " output partitions"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PARTITIONS..MODULE$, BoxesRunTime.boxToInteger(partitions.length))}))))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Final stage: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, (ResultStage)finalStage.elem)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, ((ResultStage)finalStage.elem).name())}))))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Parents of final stage: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGES..MODULE$, ((ResultStage)finalStage.elem).parents())})))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Missing parents: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MISSING_PARENT_STAGES..MODULE$, this.getMissingParentStages((ResultStage)finalStage.elem))})))));
         long jobSubmissionTime = this.clock.getTimeMillis();
         this.jobIdToActiveJob().update(BoxesRunTime.boxToInteger(jobId), job);
         this.activeJobs().$plus$eq(job);
         ((ResultStage)finalStage.elem).setActiveJob(job);
         int[] stageIds = (int[])((IterableOnceOps)this.jobIdToStageIds().apply(BoxesRunTime.boxToInteger(jobId))).toArray(scala.reflect.ClassTag..MODULE$.Int());
         ArraySeq stageInfos = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.intArrayOps(stageIds), (id) -> $anonfun$handleJobSubmitted$11(this, BoxesRunTime.unboxToInt(id)), scala.reflect.ClassTag..MODULE$.apply(StageInfo.class))).toImmutableArraySeq();
         this.listenerBus.post(new SparkListenerJobStart(job.jobId(), jobSubmissionTime, stageInfos, Utils$.MODULE$.cloneProperties(properties)));
         this.submitStage((ResultStage)finalStage.elem);
      }
   }

   public void handleMapStageSubmitted(final int jobId, final ShuffleDependency dependency, final CallSite callSite, final JobListener listener, final JobArtifactSet artifacts, final Properties properties) {
      ObjectRef finalStage = ObjectRef.create((Object)null);

      try {
         finalStage.elem = this.getOrCreateShuffleMapStage(dependency, jobId);
      } catch (Exception var14) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Creating new stage failed due to exception - job: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(jobId))})))), var14);
         listener.jobFailed(var14);
         return;
      }

      ActiveJob job = new ActiveJob(jobId, (ShuffleMapStage)finalStage.elem, callSite, listener, artifacts, properties);
      this.clearCacheLocs();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got map stage job ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(jobId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " output partitions"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PARTITIONS..MODULE$, BoxesRunTime.boxToInteger(dependency.rdd().partitions().length))}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Final stage: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, (ShuffleMapStage)finalStage.elem)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, ((ShuffleMapStage)finalStage.elem).name())}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Parents of final stage: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARENT_STAGES..MODULE$, ((ShuffleMapStage)finalStage.elem).parents().toString())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Missing parents: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MISSING_PARENT_STAGES..MODULE$, this.getMissingParentStages((ShuffleMapStage)finalStage.elem))})))));
      long jobSubmissionTime = this.clock.getTimeMillis();
      this.jobIdToActiveJob().update(BoxesRunTime.boxToInteger(jobId), job);
      this.activeJobs().$plus$eq(job);
      ((ShuffleMapStage)finalStage.elem).addActiveJob(job);
      int[] stageIds = (int[])((IterableOnceOps)this.jobIdToStageIds().apply(BoxesRunTime.boxToInteger(jobId))).toArray(scala.reflect.ClassTag..MODULE$.Int());
      ArraySeq stageInfos = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.intArrayOps(stageIds), (id) -> $anonfun$handleMapStageSubmitted$6(this, BoxesRunTime.unboxToInt(id)), scala.reflect.ClassTag..MODULE$.apply(StageInfo.class))).toImmutableArraySeq();
      this.listenerBus.post(new SparkListenerJobStart(job.jobId(), jobSubmissionTime, stageInfos, Utils$.MODULE$.cloneProperties(properties)));
      this.submitStage((ShuffleMapStage)finalStage.elem);
      if (((ShuffleMapStage)finalStage.elem).isAvailable()) {
         this.markMapStageJobAsFinished(job, this.mapOutputTracker.getStatistics(dependency));
      }
   }

   private void submitStage(final Stage stage) {
      Option jobId = this.activeJobForStage(stage);
      if (jobId.isDefined()) {
         this.logDebug((Function0)(() -> "submitStage(" + stage + " (name=" + stage.name() + ";jobs=" + ((IterableOnceOps)stage.jobIds().toSeq().sorted(scala.math.Ordering.Int..MODULE$)).mkString(",") + "))"));
         if (!this.waitingStages().apply(stage) && !this.runningStages().apply(stage) && !this.failedStages().apply(stage)) {
            if (stage.getNextAttemptId() >= this.maxStageAttempts()) {
               String reason = stage + " (name=" + stage.name() + ") has been resubmitted for the maximum allowable number of times: " + this.maxStageAttempts() + ", which is the max value of config `" + org.apache.spark.internal.config.package$.MODULE$.STAGE_MAX_ATTEMPTS().key() + "` and `" + org.apache.spark.internal.config.package$.MODULE$.STAGE_MAX_CONSECUTIVE_ATTEMPTS().key() + "`.";
               this.abortStage(stage, reason, .MODULE$);
            } else {
               List missing = (List)this.getMissingParentStages(stage).sortBy((x$28) -> BoxesRunTime.boxToInteger($anonfun$submitStage$2(x$28)), scala.math.Ordering.Int..MODULE$);
               this.logDebug((Function0)(() -> "missing: " + missing));
               if (missing.isEmpty()) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting ", " (", "), "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, stage.rdd())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"which has no missing parents"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  this.submitMissingTasks(stage, BoxesRunTime.unboxToInt(jobId.get()));
               } else {
                  missing.foreach((parent) -> {
                     $anonfun$submitStage$5(this, parent);
                     return BoxedUnit.UNIT;
                  });
                  this.waitingStages().$plus$eq(stage);
               }
            }
         }
      } else {
         this.abortStage(stage, "No active job for stage " + stage.id(), .MODULE$);
      }
   }

   private void addPySparkConfigsToProperties(final Stage stage, final Properties properties) {
      ResourceProfile rp = this.sc().resourceProfileManager().resourceProfileFromId(stage.resourceProfileId());
      Option pysparkMem = rp.getPySparkMemory();
      Option var10000;
      if (rp.id() == ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()) {
         var10000 = this.sc().conf().getOption(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_CORES().key());
      } else {
         Option profCores = rp.getExecutorCores().map((x$29) -> $anonfun$addPySparkConfigsToProperties$1(BoxesRunTime.unboxToInt(x$29)));
         var10000 = profCores.isEmpty() ? this.sc().conf().getOption(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_CORES().key()) : profCores;
      }

      Option execCores = var10000;
      pysparkMem.map((mem) -> $anonfun$addPySparkConfigsToProperties$2(properties, BoxesRunTime.unboxToLong(mem)));
      execCores.map((cores) -> properties.setProperty(ResourceProfile$.MODULE$.EXECUTOR_CORES_LOCAL_PROPERTY(), cores));
   }

   private void prepareShuffleServicesForShuffleMapStage(final ShuffleMapStage stage) {
      scala.Predef..MODULE$.assert(stage.shuffleDep().shuffleMergeAllowed() && !stage.shuffleDep().isShuffleMergeFinalizedMarked());
      this.configureShufflePushMergerLocations(stage);
      int shuffleId = stage.shuffleDep().shuffleId();
      int shuffleMergeId = stage.shuffleDep().shuffleMergeId();
      if (stage.shuffleDep().shuffleMergeEnabled()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle merge enabled before starting the stage for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with shuffle ", " and shuffle merge"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleMergeId))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " merger locations"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_MERGER_LOCATIONS..MODULE$, Integer.toString(stage.shuffleDep().getMergerLocs().size()))}))))));
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle merge disabled for ", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"shuffle ", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"shuffle merge ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleMergeId))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but can get enabled later adaptively once enough "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mergers are available"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   private void configureShufflePushMergerLocations(final ShuffleMapStage stage) {
      if (!stage.shuffleDep().getMergerLocs().nonEmpty()) {
         Seq mergerLocs = this.sc().schedulerBackend().getShufflePushMergerLocations(stage.shuffleDep().partitioner().numPartitions(), stage.resourceProfileId());
         if (mergerLocs.nonEmpty()) {
            stage.shuffleDep().setMergerLocs(mergerLocs);
            this.mapOutputTracker.registerShufflePushMergerLocations(stage.shuffleDep().shuffleId(), mergerLocs);
            this.logDebug((Function0)(() -> {
               int var10000 = stage.shuffleDep().shuffleId();
               return "Shuffle merge locations for shuffle " + var10000 + " with shuffle merge " + stage.shuffleDep().shuffleMergeId() + " is " + ((IterableOnceOps)stage.shuffleDep().getMergerLocs().map((x$30) -> x$30.host())).mkString(", ");
            }));
         }
      }
   }

   private void submitMissingTasks(final Stage stage, final int jobId) {
      label300: {
         this.logDebug((Function0)(() -> "submitMissingTasks(" + stage + ")"));
         if (stage instanceof ShuffleMapStage var13) {
            if (stage.isIndeterminate() && !var13.isAvailable()) {
               if (var13.getNextAttemptId() > 0) {
                  HashSet stagesToRollback = this.collectSucceedingStages(var13);
                  this.abortStageWithInvalidRollBack(stagesToRollback);
                  int numActiveJobsWithStageAfterRollback = this.activeJobs().count((job) -> BoxesRunTime.boxToBoolean($anonfun$submitMissingTasks$2(stagesToRollback, job)));
                  if (numActiveJobsWithStageAfterRollback == 0) {
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"All jobs depending on the indeterminate stage "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") were aborted so this stage is not needed anymore."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stage.id()))}))))));
                     return;
                  }
               }

               this.mapOutputTracker.unregisterAllMapAndMergeOutput(var13.shuffleDep().shuffleId());
               var13.shuffleDep().newShuffleMergeState();
               BoxedUnit var61 = BoxedUnit.UNIT;
               break label300;
            }
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      Seq partitionsToCompute = stage.findMissingPartitions();
      Properties properties = ((ActiveJob)this.jobIdToActiveJob().apply(BoxesRunTime.boxToInteger(jobId))).properties();
      this.addPySparkConfigsToProperties(stage, properties);
      this.runningStages().$plus$eq(stage);
      if (stage instanceof ShuffleMapStage var19) {
         this.outputCommitCoordinator().stageStart(var19.id(), var19.numPartitions() - 1);
         if (var19.shuffleDep().shuffleMergeAllowed()) {
            if (!var19.shuffleDep().isShuffleMergeFinalizedMarked()) {
               this.prepareShuffleServicesForShuffleMapStage(var19);
               BoxedUnit var62 = BoxedUnit.UNIT;
            } else {
               var19.shuffleDep().setShuffleMergeAllowed(false);
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Push-based shuffle disabled for ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") since it is already shuffle merge finalized"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))))));
               BoxedUnit var63 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var64 = BoxedUnit.UNIT;
         }
      } else {
         if (!(stage instanceof ResultStage)) {
            throw new MatchError(stage);
         }

         ResultStage var20 = (ResultStage)stage;
         this.outputCommitCoordinator().stageStart(var20.id(), var20.rdd().partitions().length - 1);
         BoxedUnit var65 = BoxedUnit.UNIT;
      }

      scala.collection.immutable.Map var66;
      try {
         if (stage instanceof ShuffleMapStage) {
            var66 = ((IterableOnceOps)partitionsToCompute.map((id) -> $anonfun$submitMissingTasks$5(this, stage, BoxesRunTime.unboxToInt(id)))).toMap(scala..less.colon.less..MODULE$.refl());
         } else {
            if (!(stage instanceof ResultStage)) {
               throw new MatchError(stage);
            }

            ResultStage var23 = (ResultStage)stage;
            var66 = ((IterableOnceOps)partitionsToCompute.map((id) -> $anonfun$submitMissingTasks$6(this, var23, stage, BoxesRunTime.unboxToInt(id)))).toMap(scala..less.colon.less..MODULE$.refl());
         }
      } catch (Throwable var58) {
         if (var58 != null && scala.util.control.NonFatal..MODULE$.apply(var58)) {
            stage.makeNewStageAttempt(partitionsToCompute.size(), stage.makeNewStageAttempt$default$2());
            this.listenerBus.post(new SparkListenerStageSubmitted(stage.latestInfo(), Utils$.MODULE$.cloneProperties(properties)));
            this.abortStage(stage, "Task creation failed: " + var58 + "\n" + Utils$.MODULE$.exceptionString(var58), new Some(var58));
            this.runningStages().$minus$eq(stage);
            return;
         }

         throw var58;
      }

      scala.collection.Map taskIdToLocations = var66;
      stage.makeNewStageAttempt(partitionsToCompute.size(), taskIdToLocations.values().toSeq());
      if (partitionsToCompute.nonEmpty()) {
         stage.latestInfo().submissionTime_$eq(new Some(BoxesRunTime.boxToLong(this.clock.getTimeMillis())));
      }

      this.listenerBus.post(new SparkListenerStageSubmitted(stage.latestInfo(), Utils$.MODULE$.cloneProperties(properties)));
      ObjectRef taskBinary = ObjectRef.create((Object)null);
      ObjectRef partitions = ObjectRef.create((Object)null);

      try {
         ObjectRef taskBinaryBytes = ObjectRef.create((Object)null);
         synchronized(RDDCheckpointData$.MODULE$){}

         try {
            byte[] var10001;
            if (stage instanceof ShuffleMapStage var32) {
               var10001 = JavaUtils.bufferToArray(this.closureSerializer().serialize(new Tuple2(var32.rdd(), var32.shuffleDep()), scala.reflect.ClassTag..MODULE$.AnyRef()));
            } else {
               if (!(stage instanceof ResultStage)) {
                  throw new MatchError(stage);
               }

               ResultStage var33 = (ResultStage)stage;
               var10001 = JavaUtils.bufferToArray(this.closureSerializer().serialize(new Tuple2(var33.rdd(), var33.func()), scala.reflect.ClassTag..MODULE$.AnyRef()));
            }

            taskBinaryBytes.elem = var10001;
            partitions.elem = stage.rdd().partitions();
         } catch (Throwable var56) {
            throw var56;
         }

         if (((byte[])taskBinaryBytes.elem).length > TaskSetManager$.MODULE$.TASK_SIZE_TO_WARN_KIB() * 1024) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Broadcasting large task binary with size "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString((long)((byte[])taskBinaryBytes.elem).length))}))))));
         }

         taskBinary.elem = this.sc().broadcast((byte[])taskBinaryBytes.elem, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      } catch (NotSerializableException var59) {
         this.abortStage(stage, "Task not serializable: " + var59.toString(), new Some(var59));
         this.runningStages().$minus$eq(stage);
         return;
      } catch (Throwable var60) {
         this.abortStage(stage, "Task serialization failed: " + var60 + "\n" + Utils$.MODULE$.exceptionString(var60), new Some(var60));
         this.runningStages().$minus$eq(stage);
         return;
      }

      JobArtifactSet artifacts = ((ActiveJob)this.jobIdToActiveJob().apply(BoxesRunTime.boxToInteger(jobId))).artifacts();

      try {
         byte[] serializedTaskMetrics = this.closureSerializer().serialize(stage.latestInfo().taskMetrics(), scala.reflect.ClassTag..MODULE$.apply(TaskMetrics.class)).array();
         if (stage instanceof ShuffleMapStage var40) {
            var40.pendingPartitions().clear();
            var67 = (Seq)partitionsToCompute.map((id) -> $anonfun$submitMissingTasks$8(this, taskIdToLocations, partitions, var40, taskBinary, artifacts, properties, serializedTaskMetrics, jobId, BoxesRunTime.unboxToInt(id)));
         } else {
            if (!(stage instanceof ResultStage)) {
               throw new MatchError(stage);
            }

            ResultStage var41 = (ResultStage)stage;
            var67 = (Seq)partitionsToCompute.map((id) -> $anonfun$submitMissingTasks$9(this, var41, partitions, taskIdToLocations, taskBinary, artifacts, properties, serializedTaskMetrics, jobId, BoxesRunTime.unboxToInt(id)));
         }
      } catch (Throwable var57) {
         if (var57 != null && scala.util.control.NonFatal..MODULE$.apply(var57)) {
            this.abortStage(stage, "Task creation failed: " + var57 + "\n" + Utils$.MODULE$.exceptionString(var57), new Some(var57));
            this.runningStages().$minus$eq(stage);
            return;
         }

         throw var57;
      }

      Seq tasks = var67;
      if (tasks.nonEmpty()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting ", " missing tasks from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(tasks.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") (first 15 tasks are "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, stage.rdd())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for partitions ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITION_IDS..MODULE$, ((IterableOps)tasks.take(15)).map((x$31) -> BoxesRunTime.boxToInteger($anonfun$submitMissingTasks$11(x$31))))}))))));
         Object var71;
         if (stage instanceof ShuffleMapStage) {
            ShuffleMapStage var47 = (ShuffleMapStage)stage;
            var71 = new Some(BoxesRunTime.boxToInteger(var47.shuffleDep().shuffleId()));
         } else {
            if (!(stage instanceof ResultStage)) {
               throw new MatchError(stage);
            }

            var71 = .MODULE$;
         }

         Option shuffleId = (Option)var71;
         this.taskScheduler().submitTasks(new TaskSet((Task[])tasks.toArray(scala.reflect.ClassTag..MODULE$.apply(Task.class)), stage.id(), stage.latestInfo().attemptNumber(), jobId, properties, stage.resourceProfileId(), shuffleId));
      } else if (stage instanceof ShuffleMapStage) {
         ShuffleMapStage var49 = (ShuffleMapStage)stage;
         this.logDebug((Function0)(() -> "Stage " + var49 + " is actually done; (available: " + var49.isAvailable() + ",available outputs: " + var49.numAvailableOutputs() + ",partitions: " + var49.numPartitions() + ")"));
         if (!var49.shuffleDep().isShuffleMergeFinalizedMarked() && var49.shuffleDep().getMergerLocs().nonEmpty()) {
            this.checkAndScheduleShuffleMergeFinalize(var49);
            BoxedUnit var70 = BoxedUnit.UNIT;
         } else {
            this.processShuffleMapStageCompletion(var49);
            BoxedUnit var69 = BoxedUnit.UNIT;
         }
      } else if (stage instanceof ResultStage) {
         ResultStage var50 = (ResultStage)stage;
         this.logDebug((Function0)(() -> "Stage " + var50 + " is actually done; (partitions: " + var50.numPartitions() + ")"));
         this.markStageAsFinished(var50, this.markStageAsFinished$default$2(), this.markStageAsFinished$default$3());
         this.submitWaitingChildStages(var50);
         BoxedUnit var68 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(stage);
      }
   }

   private void updateAccumulators(final CompletionEvent event) {
      Task task = event.task();
      Stage stage = (Stage)this.stageIdToStage().apply(BoxesRunTime.boxToInteger(task.stageId()));
      event.accumUpdates().foreach((updates) -> {
         $anonfun$updateAccumulators$1(this, stage, event, task, updates);
         return BoxedUnit.UNIT;
      });
   }

   private void postTaskEnd(final CompletionEvent event) {
      TaskMetrics var10000;
      if (event.accumUpdates().nonEmpty()) {
         try {
            var10000 = TaskMetrics$.MODULE$.fromAccumulators(event.accumUpdates());
         } catch (Throwable var9) {
            if (var9 == null || !scala.util.control.NonFatal..MODULE$.apply(var9)) {
               throw var9;
            }

            long taskId = event.taskInfo().taskId();
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error when attempting to reconstruct metrics for task ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))})))), var9);
            var10000 = null;
         }
      } else {
         var10000 = null;
      }

      TaskMetrics taskMetrics = var10000;
      this.listenerBus.post(new SparkListenerTaskEnd(event.task().stageId(), event.task().stageAttemptId(), Utils$.MODULE$.getFormattedClassName(event.task()), event.reason(), event.taskInfo(), new ExecutorMetrics(event.metricPeaks()), taskMetrics));
   }

   private boolean shouldInterruptTaskThread(final ActiveJob job) {
      if (job.properties() == null) {
         return false;
      } else {
         String shouldInterruptThread = job.properties().getProperty(SparkContext$.MODULE$.SPARK_JOB_INTERRUPT_ON_CANCEL(), "false");

         boolean var10000;
         try {
            var10000 = scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(shouldInterruptThread));
         } catch (IllegalArgumentException var4) {
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, SparkContext$.MODULE$.SPARK_JOB_INTERRUPT_ON_CANCEL())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in Job ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job.jobId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is invalid: ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, shouldInterruptThread)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using 'false' instead"})))).log(scala.collection.immutable.Nil..MODULE$))), var4);
            var10000 = false;
         }

         return var10000;
      }
   }

   public void checkAndScheduleShuffleMergeFinalize(final ShuffleMapStage shuffleStage) {
      if (shuffleStage.shuffleDep().getFinalizeTask().isEmpty()) {
         LazyLong computedTotalSize$lzy = new LazyLong();
         long totalSize = shuffleStage.isAvailable() ? this.computedTotalSize$1(computedTotalSize$lzy, shuffleStage) : (shuffleStage.isIndeterminate() ? 0L : this.computedTotalSize$1(computedTotalSize$lzy, shuffleStage));
         if (totalSize < this.shuffleMergeWaitMinSizeThreshold()) {
            this.scheduleShuffleMergeFinalize(shuffleStage, 0L, false);
         } else {
            this.scheduleShuffleMergeFinalize(shuffleStage, this.shuffleMergeFinalizeWaitSec(), this.scheduleShuffleMergeFinalize$default$3());
         }
      }
   }

   public void handleTaskCompletion(final CompletionEvent event) {
      Task task = event.task();
      int stageId = task.stageId();
      this.outputCommitCoordinator().taskCompleted(stageId, task.stageAttemptId(), task.partitionId(), event.taskInfo().attemptNumber(), event.reason());
      if (!this.stageIdToStage().contains(BoxesRunTime.boxToInteger(task.stageId()))) {
         this.postTaskEnd(event);
      } else {
         Stage stage = (Stage)this.stageIdToStage().apply(BoxesRunTime.boxToInteger(task.stageId()));
         TaskEndReason var18 = event.reason();
         if (Success$.MODULE$.equals(var18)) {
            if (task instanceof ResultTask) {
               ResultTask var20 = (ResultTask)task;
               ResultStage resultStage = (ResultStage)stage;
               Option var22 = resultStage.activeJob();
               if (var22 instanceof Some) {
                  Some var23 = (Some)var22;
                  ActiveJob job = (ActiveJob)var23.value();
                  if (!job.finished()[var20.outputId()]) {
                     this.updateAccumulators(event);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     BoxedUnit var82 = BoxedUnit.UNIT;
                  }
               } else {
                  if (!.MODULE$.equals(var22)) {
                     throw new MatchError(var22);
                  }

                  BoxedUnit var83 = BoxedUnit.UNIT;
               }

               BoxedUnit var84 = BoxedUnit.UNIT;
            } else {
               this.updateAccumulators(event);
               BoxedUnit var85 = BoxedUnit.UNIT;
            }

            BoxedUnit var86 = BoxedUnit.UNIT;
         } else if (var18 instanceof ExceptionFailure ? true : var18 instanceof TaskKilled) {
            this.updateAccumulators(event);
            BoxedUnit var87 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var88 = BoxedUnit.UNIT;
         }

         if (this.trackingCacheVisibility()) {
            BlockManagerMaster var89;
            boolean var121;
            long var10001;
            label391: {
               label390: {
                  var89 = this.blockManagerMaster;
                  var10001 = event.taskInfo().taskId();
                  TaskEndReason var10002 = event.reason();
                  Success$ var25 = Success$.MODULE$;
                  if (var10002 == null) {
                     if (var25 == null) {
                        break label390;
                     }
                  } else if (var10002.equals(var25)) {
                     break label390;
                  }

                  var121 = false;
                  break label391;
               }

               var121 = true;
            }

            var89.updateRDDBlockVisibility(var10001, var121);
         }

         this.postTaskEnd(event);
         TaskEndReason var26 = event.reason();
         if (Success$.MODULE$.equals(var26)) {
            if (!stage.isIndeterminate() && task.stageAttemptId() < stage.latestInfo().attemptNumber()) {
               this.taskScheduler().notifyPartitionCompletion(stageId, task.partitionId());
            }

            if (!(task instanceof ResultTask)) {
               if (!(task instanceof ShuffleMapTask)) {
                  throw new MatchError(task);
               }

               ShuffleMapTask var37 = (ShuffleMapTask)task;
               ShuffleMapStage shuffleStage = (ShuffleMapStage)stage;
               boolean ignoreIndeterminate = stage.isIndeterminate() && task.stageAttemptId() < stage.latestInfo().attemptNumber();
               if (!ignoreIndeterminate) {
                  shuffleStage.pendingPartitions().$minus$eq(BoxesRunTime.boxToInteger(task.partitionId()));
                  MapStatus status = (MapStatus)event.result();
                  String execId = status.location().executorId();
                  this.logDebug((Function0)(() -> "ShuffleMapTask finished on " + execId));
                  if (this.executorFailureEpoch().contains(execId) && var37.epoch() <= BoxesRunTime.unboxToLong(this.executorFailureEpoch().apply(execId))) {
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring possibly bogus ", " completion from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, var37)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)}))))));
                  } else {
                     this.mapOutputTracker.registerMapOutput(shuffleStage.shuffleDep().shuffleId(), var37.partitionId(), status);
                  }
               } else {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring ", " completion from an older attempt of indeterminate stage"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, var37)})))));
               }

               if (this.runningStages().contains(shuffleStage) && shuffleStage.pendingPartitions().isEmpty()) {
                  if (!shuffleStage.shuffleDep().isShuffleMergeFinalizedMarked() && shuffleStage.shuffleDep().getMergerLocs().nonEmpty()) {
                     this.checkAndScheduleShuffleMergeFinalize(shuffleStage);
                     BoxedUnit var119 = BoxedUnit.UNIT;
                  } else {
                     this.processShuffleMapStageCompletion(shuffleStage);
                     BoxedUnit var118 = BoxedUnit.UNIT;
                  }
               } else {
                  BoxedUnit var117 = BoxedUnit.UNIT;
               }
            } else {
               ResultTask var28 = (ResultTask)task;
               ResultStage resultStage = (ResultStage)stage;
               Option var30 = resultStage.activeJob();
               if (var30 instanceof Some) {
                  Some var31 = (Some)var30;
                  ActiveJob job = (ActiveJob)var31.value();
                  if (job.finished()[var28.outputId()]) {
                     BoxedUnit var115 = BoxedUnit.UNIT;
                  } else {
                     job.finished()[var28.outputId()] = true;
                     job.numFinished_$eq(job.numFinished() + 1);
                     if (job.numFinished() == job.numPartitions()) {
                        this.markStageAsFinished(resultStage, this.markStageAsFinished$default$2(), this.markStageAsFinished$default$3());
                        this.cancelRunningIndependentStages(job, "Job " + job.jobId() + " is finished.");
                        this.cleanupStateForJobAndIndependentStages(job);

                        try {
                           this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Job ", " is finished. Cancelling "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job.jobId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"potential speculative or zombie tasks for this job"})))).log(scala.collection.immutable.Nil..MODULE$))));
                           this.taskScheduler().killAllTaskAttempts(stageId, this.shouldInterruptTaskThread(job), "Stage finished");
                        } catch (UnsupportedOperationException var79) {
                           this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not cancel tasks "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, BoxesRunTime.boxToInteger(stageId))}))))), var79);
                        }

                        this.listenerBus.post(new SparkListenerJobEnd(job.jobId(), this.clock.getTimeMillis(), JobSucceeded$.MODULE$));
                     }

                     try {
                        job.listener().taskSucceeded(var28.outputId(), event.result());
                        BoxedUnit var114 = BoxedUnit.UNIT;
                     } catch (Throwable var81) {
                        if (var81 == null || Utils$.MODULE$.isFatalError(var81)) {
                           throw var81;
                        }

                        job.listener().jobFailed(new SparkDriverExecutionException(var81));
                        BoxedUnit var112 = BoxedUnit.UNIT;
                        var112 = BoxedUnit.UNIT;
                     }
                  }
               } else {
                  if (!.MODULE$.equals(var30)) {
                     throw new MatchError(var30);
                  }

                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring result from ", " because its job has finished"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESULT..MODULE$, var28)})))));
                  BoxedUnit var111 = BoxedUnit.UNIT;
               }

               BoxedUnit var116 = BoxedUnit.UNIT;
            }

            BoxedUnit var120 = BoxedUnit.UNIT;
         } else if (var26 instanceof FetchFailed) {
            FetchFailed var42 = (FetchFailed)var26;
            BlockManagerId bmAddress = var42.bmAddress();
            int shuffleId = var42.shuffleId();
            int mapIndex = var42.mapIndex();
            int reduceId = var42.reduceId();
            String failureMessage = var42.message();
            Stage failedStage = (Stage)this.stageIdToStage().apply(BoxesRunTime.boxToInteger(task.stageId()));
            ShuffleMapStage mapStage = (ShuffleMapStage)this.shuffleIdToMapStage().apply(BoxesRunTime.boxToInteger(shuffleId));
            if (failedStage.latestInfo().attemptNumber() != task.stageAttemptId()) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring fetch failure from "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " as it's from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, task)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " attempt "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and there is a more recent attempt for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(task.stageAttemptId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"that stage (attempt "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") running"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(failedStage.latestInfo().attemptNumber()))}))))));
               BoxedUnit var110 = BoxedUnit.UNIT;
            } else {
               boolean ignoreStageFailure = this.ignoreDecommissionFetchFailure() && this.isExecutorDecommissioningOrDecommissioned(this.taskScheduler(), bmAddress);
               if (ignoreStageFailure) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring fetch failure from ", " of "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, task)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " attempt "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " when count "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(task.stageAttemptId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, org.apache.spark.internal.config.package$.MODULE$.STAGE_MAX_CONSECUTIVE_ATTEMPTS().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"as executor ", " is decommissioned and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, bmAddress.executorId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "=true"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE().key())}))))));
                  BoxedUnit var103 = BoxedUnit.UNIT;
               } else {
                  BoxesRunTime.boxToBoolean(failedStage.failedAttemptIds().add(BoxesRunTime.boxToInteger(task.stageAttemptId())));
               }

               boolean shouldAbortStage = failedStage.failedAttemptIds().size() >= this.maxConsecutiveStageAttempts() || this.disallowStageRetryForTest();
               if (this.runningStages().contains(failedStage)) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Marking ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") as failed "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE_NAME..MODULE$, failedStage.name())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"due to a fetch failure from ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, mapStage)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, mapStage.name())}))))));
                  this.markStageAsFinished(failedStage, new Some(failureMessage), !shouldAbortStage);
               } else {
                  this.logDebug((Function0)(() -> "Received fetch failure from " + task + ", but it's from " + failedStage + " which is no longer running"));
               }

               if (mapStage.rdd().isBarrier()) {
                  this.mapOutputTracker.unregisterAllMapAndMergeOutput(shuffleId);
               } else if (mapIndex != -1) {
                  this.mapOutputTracker.unregisterMapOutput(shuffleId, mapIndex, bmAddress);
                  if (this.pushBasedShuffleEnabled()) {
                     this.mapOutputTracker.unregisterMergeResult(shuffleId, reduceId, bmAddress, scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(mapIndex)));
                  }
               } else if (bmAddress != null && bmAddress.executorId().equals(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER())) {
                  scala.Predef..MODULE$.assert(this.pushBasedShuffleEnabled(), () -> "Push based shuffle expected to be enabled when handling merge block fetch failure.");
                  this.mapOutputTracker.unregisterMergeResult(shuffleId, reduceId, bmAddress, .MODULE$);
               }

               if (failedStage.rdd().isBarrier()) {
                  if (failedStage instanceof ShuffleMapStage) {
                     ShuffleMapStage var53 = (ShuffleMapStage)failedStage;
                     this.mapOutputTracker.unregisterAllMapAndMergeOutput(var53.shuffleDep().shuffleId());
                     BoxedUnit var104 = BoxedUnit.UNIT;
                  } else {
                     if (!(failedStage instanceof ResultStage)) {
                        throw new MatchError(failedStage);
                     }

                     ResultStage var54 = (ResultStage)failedStage;
                     String reason = "Could not recover from a failed barrier ResultStage. Most recent failure reason: " + failureMessage;
                     this.abortStage(var54, reason, .MODULE$);
                     BoxedUnit var105 = BoxedUnit.UNIT;
                  }
               }

               if (shouldAbortStage) {
                  String abortMessage = this.disallowStageRetryForTest() ? "Fetch failure will not retry stage due to testing config" : failedStage + " (" + failedStage.name() + ") has failed the maximum allowable number of times: " + this.maxConsecutiveStageAttempts() + ". Most recent failure reason:\n" + failureMessage;
                  this.abortStage(failedStage, abortMessage, .MODULE$);
                  BoxedUnit var106 = BoxedUnit.UNIT;
               } else {
                  boolean noResubmitEnqueued = !this.failedStages().contains(failedStage);
                  this.failedStages().$plus$eq(failedStage);
                  this.failedStages().$plus$eq(mapStage);
                  if (noResubmitEnqueued) {
                     if (mapStage.isIndeterminate()) {
                        HashSet stagesToRollback = this.collectSucceedingStages(mapStage);
                        HashSet rollingBackStages = this.abortStageWithInvalidRollBack(stagesToRollback);
                        this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The shuffle map stage ", " with indeterminate output was failed, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, mapStage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"we will roll back and rerun below stages which include itself and all its "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"indeterminate child stages: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGES..MODULE$, rollingBackStages)}))))));
                     }

                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Resubmitting ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, mapStage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") and ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, mapStage.name()), new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") due to fetch failure"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE_NAME..MODULE$, failedStage.name())}))))));
                     this.messageScheduler().schedule(new Runnable() {
                        // $FF: synthetic field
                        private final DAGScheduler $outer;

                        public void run() {
                           this.$outer.eventProcessLoop().post(ResubmitFailedStages$.MODULE$);
                        }

                        public {
                           if (DAGScheduler.this == null) {
                              throw null;
                           } else {
                              this.$outer = DAGScheduler.this;
                           }
                        }
                     }, (long)DAGScheduler$.MODULE$.RESUBMIT_TIMEOUT(), TimeUnit.MILLISECONDS);
                  } else {
                     BoxedUnit var107 = BoxedUnit.UNIT;
                  }
               }

               if (bmAddress == null) {
                  BoxedUnit var109 = BoxedUnit.UNIT;
               } else {
                  boolean externalShuffleServiceEnabled = this.env.blockManager().externalShuffleServiceEnabled();
                  boolean isHostDecommissioned = this.taskScheduler().getExecutorDecommissionState(bmAddress.executorId()).exists((x$33) -> BoxesRunTime.boxToBoolean($anonfun$handleTaskCompletion$14(x$33)));
                  boolean shuffleOutputOfEntireHostLost = externalShuffleServiceEnabled || isHostDecommissioned;
                  Option hostToUnregisterOutputs = (Option)(shuffleOutputOfEntireHostLost && this.unRegisterOutputOnHostOnFetchFailure() ? new Some(bmAddress.host()) : .MODULE$);
                  this.removeExecutorAndUnregisterOutputs(bmAddress.executorId(), true, hostToUnregisterOutputs, new Some(BoxesRunTime.boxToLong(task.epoch())), isHostDecommissioned);
                  BoxedUnit var108 = BoxedUnit.UNIT;
               }
            }
         } else {
            if (var26 instanceof TaskFailedReason) {
               TaskFailedReason var64 = (TaskFailedReason)var26;
               if (task.isBarrier()) {
                  if (Resubmitted$.MODULE$.equals(var64)) {
                     this.handleResubmittedFailure(task, stage);
                     BoxedUnit var95 = BoxedUnit.UNIT;
                  } else {
                     BoxedUnit var96 = BoxedUnit.UNIT;
                  }

                  Stage failedStage = (Stage)this.stageIdToStage().apply(BoxesRunTime.boxToInteger(task.stageId()));
                  if (failedStage.latestInfo().attemptNumber() != task.stageAttemptId()) {
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring task failure from ", " as it's from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, task)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " attempt ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage), new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(task.stageAttemptId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"and there is a more recent attempt for that stage (attempt "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") running"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(failedStage.latestInfo().attemptNumber()))}))))));
                     BoxedUnit var102 = BoxedUnit.UNIT;
                     return;
                  }

                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Marking ", " (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(failedStage.id())), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, failedStage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"as failed due to a barrier task failed."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  String message = "Stage failed because barrier task " + task + " finished unsuccessfully.\n" + var64.toErrorString();

                  try {
                     String reason = "Task " + task + " from barrier stage " + failedStage + " (" + failedStage.name() + ") failed.";
                     Option job = this.jobIdToActiveJob().get(BoxesRunTime.boxToInteger(failedStage.firstJobId()));
                     boolean shouldInterrupt = job.exists((j) -> BoxesRunTime.boxToBoolean($anonfun$handleTaskCompletion$17(this, j)));
                     this.taskScheduler().killAllTaskAttempts(stageId, shouldInterrupt, reason);
                  } catch (UnsupportedOperationException var80) {
                     this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not kill all tasks for stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))})))), var80);
                     this.abortStage(failedStage, "Could not kill zombie barrier tasks for stage " + failedStage + " (" + failedStage.name() + ")", new Some(var80));
                  }

                  this.markStageAsFinished(failedStage, new Some(message), this.markStageAsFinished$default$3());
                  failedStage.failedAttemptIds().add(BoxesRunTime.boxToInteger(task.stageAttemptId()));
                  boolean shouldAbortStage = failedStage.failedAttemptIds().size() >= this.maxConsecutiveStageAttempts() || this.disallowStageRetryForTest();
                  if (shouldAbortStage) {
                     String abortMessage = this.disallowStageRetryForTest() ? "Barrier stage will not retry stage due to testing config. Most recent failure reason: " + message : failedStage + " (" + failedStage.name() + ") has failed the maximum allowable number of times: " + this.maxConsecutiveStageAttempts() + ". Most recent failure reason: " + message;
                     this.abortStage(failedStage, abortMessage, .MODULE$);
                     BoxedUnit var101 = BoxedUnit.UNIT;
                     return;
                  }

                  if (failedStage instanceof ShuffleMapStage) {
                     ShuffleMapStage var75 = (ShuffleMapStage)failedStage;
                     this.mapOutputTracker.unregisterAllMapAndMergeOutput(var75.shuffleDep().shuffleId());
                     BoxedUnit var97 = BoxedUnit.UNIT;
                  } else {
                     if (!(failedStage instanceof ResultStage)) {
                        throw new MatchError(failedStage);
                     }

                     ResultStage var76 = (ResultStage)failedStage;
                     String reason = "Could not recover from a failed barrier ResultStage. Most recent failure reason: " + message;
                     this.abortStage(var76, reason, .MODULE$);
                     BoxedUnit var98 = BoxedUnit.UNIT;
                  }

                  boolean noResubmitEnqueued = !this.failedStages().contains(failedStage);
                  this.failedStages().$plus$eq(failedStage);
                  if (noResubmitEnqueued) {
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Resubmitting ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") due to barrier stage failure."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE_NAME..MODULE$, failedStage.name())}))))));
                     this.messageScheduler().schedule(new Runnable() {
                        // $FF: synthetic field
                        private final DAGScheduler $outer;

                        public void run() {
                           this.$outer.eventProcessLoop().post(ResubmitFailedStages$.MODULE$);
                        }

                        public {
                           if (DAGScheduler.this == null) {
                              throw null;
                           } else {
                              this.$outer = DAGScheduler.this;
                           }
                        }
                     }, (long)DAGScheduler$.MODULE$.RESUBMIT_TIMEOUT(), TimeUnit.MILLISECONDS);
                     BoxedUnit var100 = BoxedUnit.UNIT;
                     return;
                  }

                  BoxedUnit var99 = BoxedUnit.UNIT;
                  return;
               }
            }

            if (Resubmitted$.MODULE$.equals(var26)) {
               this.handleResubmittedFailure(task, stage);
               BoxedUnit var94 = BoxedUnit.UNIT;
            } else if (var26 instanceof TaskCommitDenied) {
               BoxedUnit var93 = BoxedUnit.UNIT;
            } else if (var26 instanceof ExceptionFailure ? true : var26 instanceof TaskKilled) {
               BoxedUnit var92 = BoxedUnit.UNIT;
            } else if (TaskResultLost$.MODULE$.equals(var26)) {
               BoxedUnit var91 = BoxedUnit.UNIT;
            } else if (var26 instanceof ExecutorLostFailure ? true : UnknownReason$.MODULE$.equals(var26)) {
               BoxedUnit var90 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var26);
            }
         }
      }
   }

   private HashSet collectSucceedingStages(final ShuffleMapStage mapStage) {
      HashSet succeedingStages = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Stage[]{mapStage}));
      this.activeJobs().foreach((job) -> {
         $anonfun$collectSucceedingStages$3(succeedingStages, job);
         return BoxedUnit.UNIT;
      });
      return succeedingStages;
   }

   private HashSet abortStageWithInvalidRollBack(final HashSet stagesToRollback) {
      HashSet rollingBackStages = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      stagesToRollback.foreach((x0$1) -> {
         if (x0$1 instanceof ShuffleMapStage var5) {
            if (var5.numAvailableOutputs() > 0) {
               if (BoxesRunTime.unboxToBoolean(this.sc().conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_USE_OLD_FETCH_PROTOCOL()))) {
                  String reason = "A shuffle map stage with indeterminate output was failed and retried. However, Spark can only do this while using the new shuffle block fetching protocol. Please check the config 'spark.shuffle.useOldFetchProtocol', see more detail in SPARK-27665 and SPARK-25341.";
                  this.abortStage(var5, reason, .MODULE$);
                  return BoxedUnit.UNIT;
               } else {
                  return rollingBackStages.$plus$eq(var5);
               }
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            if (x0$1 instanceof ResultStage var7) {
               if (var7.activeJob().isDefined()) {
                  int numMissingPartitions = var7.findMissingPartitions().length();
                  if (numMissingPartitions < var7.numTasks()) {
                     this.abortStage(var7, generateErrorMessage$1(var7), .MODULE$);
                     return BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               }
            }

            return BoxedUnit.UNIT;
         }
      });
      return rollingBackStages;
   }

   public boolean isExecutorDecommissioningOrDecommissioned(final TaskScheduler taskScheduler, final BlockManagerId bmAddress) {
      return bmAddress != null ? taskScheduler.getExecutorDecommissionState(bmAddress.executorId()).nonEmpty() : false;
   }

   public void scheduleShuffleMergeFinalize(final ShuffleMapStage stage, final long delay, final boolean registerMergeResults) {
      ShuffleDependency shuffleDep = stage.shuffleDep();
      Option scheduledTask = shuffleDep.getFinalizeTask();
      if (!(scheduledTask instanceof Some var9)) {
         if (.MODULE$.equals(scheduledTask)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") scheduled for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"finalizing shuffle merge in ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DELAY..MODULE$, BoxesRunTime.boxToLong(delay * 1000L))}))))));
            shuffleDep.setFinalizeTask(this.shuffleMergeFinalizeScheduler().schedule(new Runnable(stage, registerMergeResults) {
               // $FF: synthetic field
               private final DAGScheduler $outer;
               private final ShuffleMapStage stage$8;
               private final boolean registerMergeResults$1;

               public void run() {
                  this.$outer.finalizeShuffleMerge(this.stage$8, this.registerMergeResults$1);
               }

               public {
                  if (DAGScheduler.this == null) {
                     throw null;
                  } else {
                     this.$outer = DAGScheduler.this;
                     this.stage$8 = stage$8;
                     this.registerMergeResults$1 = registerMergeResults$1;
                  }
               }
            }, delay, TimeUnit.SECONDS));
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(scheduledTask);
         }
      } else {
         ScheduledFuture task = (ScheduledFuture)var9.value();
         scala.Predef..MODULE$.assert(delay == 0L && registerMergeResults);
         if (task.getDelay(TimeUnit.NANOSECONDS) > 0L && task.cancel(false)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") scheduled "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for finalizing shuffle merge immediately after cancelling previously scheduled task."})))).log(scala.collection.immutable.Nil..MODULE$))));
            shuffleDep.setFinalizeTask(this.shuffleMergeFinalizeScheduler().schedule(new Runnable(stage, registerMergeResults) {
               // $FF: synthetic field
               private final DAGScheduler $outer;
               private final ShuffleMapStage stage$8;
               private final boolean registerMergeResults$1;

               public void run() {
                  this.$outer.finalizeShuffleMerge(this.stage$8, this.registerMergeResults$1);
               }

               public {
                  if (DAGScheduler.this == null) {
                     throw null;
                  } else {
                     this.$outer = DAGScheduler.this;
                     this.stage$8 = stage$8;
                     this.registerMergeResults$1 = registerMergeResults$1;
                  }
               }
            }, 0L, TimeUnit.SECONDS));
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") existing scheduled task "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for finalizing shuffle merge would either be in-progress or finished. "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No need to schedule shuffle merge finalization again."})))).log(scala.collection.immutable.Nil..MODULE$))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }
   }

   public boolean scheduleShuffleMergeFinalize$default$3() {
      return true;
   }

   public void finalizeShuffleMerge(final ShuffleMapStage stage, final boolean registerMergeResults) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") finalizing the shuffle merge with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" registering merge results set to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REGISTER_MERGE_RESULTS..MODULE$, BoxesRunTime.boxToBoolean(registerMergeResults))}))))));
      int shuffleId = stage.shuffleDep().shuffleId();
      int shuffleMergeId = stage.shuffleDep().shuffleMergeId();
      int numMergers = stage.shuffleDep().getMergerLocs().length();
      IndexedSeq results = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numMergers).map((x$34) -> $anonfun$finalizeShuffleMerge$2(BoxesRunTime.unboxToInt(x$34)));
      this.externalShuffleClient().foreach((shuffleClient) -> {
         Seq var10000;
         if (!registerMergeResults) {
            results.foreach((x$35) -> BoxesRunTime.boxToBoolean($anonfun$finalizeShuffleMerge$4(x$35)));
            var10000 = (Seq)stage.shuffleDep().getMergerLocs().map((x0$1) -> this.shuffleSendFinalizeRpcExecutor().submit(new Runnable(shuffleClient, x0$1, shuffleId, shuffleMergeId) {
                  private final BlockStoreClient shuffleClient$1;
                  private final BlockManagerId x1$1;
                  private final int shuffleId$2;
                  private final int shuffleMergeId$2;

                  public void run() {
                     this.shuffleClient$1.finalizeShuffleMerge(this.x1$1.host(), this.x1$1.port(), this.shuffleId$2, this.shuffleMergeId$2, new MergeFinalizerListener() {
                        public void onShuffleMergeSuccess(final MergeStatuses statuses) {
                        }

                        public void onShuffleMergeFailure(final Throwable e) {
                        }
                     });
                  }

                  public {
                     this.shuffleClient$1 = shuffleClient$1;
                     this.x1$1 = x1$1;
                     this.shuffleId$2 = shuffleId$2;
                     this.shuffleMergeId$2 = shuffleMergeId$2;
                  }
               }));
         } else {
            var10000 = (Seq)((IterableOps)stage.shuffleDep().getMergerLocs().zipWithIndex()).map((x0$2) -> {
               if (x0$2 != null) {
                  BlockManagerId shuffleServiceLoc = (BlockManagerId)x0$2._1();
                  int index = x0$2._2$mcI$sp();
                  return this.shuffleSendFinalizeRpcExecutor().submit(new Runnable(shuffleClient, shuffleServiceLoc, shuffleId, shuffleMergeId, stage, results, index) {
                     // $FF: synthetic field
                     private final DAGScheduler $outer;
                     private final BlockStoreClient shuffleClient$1;
                     public final BlockManagerId shuffleServiceLoc$1;
                     public final int shuffleId$2;
                     private final int shuffleMergeId$2;
                     public final ShuffleMapStage stage$9;
                     public final IndexedSeq results$1;
                     public final int index$1;

                     public void run() {
                        this.shuffleClient$1.finalizeShuffleMerge(this.shuffleServiceLoc$1.host(), this.shuffleServiceLoc$1.port(), this.shuffleId$2, this.shuffleMergeId$2, new MergeFinalizerListener() {
                           // $FF: synthetic field
                           private final <undefinedtype> $outer;

                           public void onShuffleMergeSuccess(final MergeStatuses statuses) {
                              scala.Predef..MODULE$.assert(this.$outer.shuffleId$2 == statuses.shuffleId);
                              this.$outer.org$apache$spark$scheduler$DAGScheduler$$anon$$$outer().eventProcessLoop().post(new RegisterMergeStatuses(this.$outer.stage$9, MergeStatus$.MODULE$.convertMergeStatusesToMergeStatusArr(statuses, this.$outer.shuffleServiceLoc$1)));
                              ((SettableFuture)this.$outer.results$1.apply(this.$outer.index$1)).set(BoxesRunTime.boxToBoolean(true));
                           }

                           public void onShuffleMergeFailure(final Throwable e) {
                              this.$outer.org$apache$spark$scheduler$DAGScheduler$$anon$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.org$apache$spark$scheduler$DAGScheduler$$anon$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception encountered when trying to finalize shuffle "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.org$apache$spark$scheduler$DAGScheduler$$anon$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"merge on ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.$outer.shuffleServiceLoc$1.host())})))).$plus(this.$outer.org$apache$spark$scheduler$DAGScheduler$$anon$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for shuffle ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(this.$outer.shuffleId$2))}))))), e);
                              ((SettableFuture)this.$outer.results$1.apply(this.$outer.index$1)).set(BoxesRunTime.boxToBoolean(false));
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
                              return var0.lambdaDeserialize<invokedynamic>(var0);
                           }
                        });
                     }

                     // $FF: synthetic method
                     public DAGScheduler org$apache$spark$scheduler$DAGScheduler$$anon$$$outer() {
                        return this.$outer;
                     }

                     public {
                        if (DAGScheduler.this == null) {
                           throw null;
                        } else {
                           this.$outer = DAGScheduler.this;
                           this.shuffleClient$1 = shuffleClient$1;
                           this.shuffleServiceLoc$1 = shuffleServiceLoc$1;
                           this.shuffleId$2 = shuffleId$2;
                           this.shuffleMergeId$2 = shuffleMergeId$2;
                           this.stage$9 = stage$9;
                           this.results$1 = results$1;
                           this.index$1 = index$1;
                        }
                     }
                  });
               } else {
                  throw new MatchError(x0$2);
               }
            });
         }

         Seq scheduledFutures = var10000;
         boolean timedOut = false;

         try {
            var15 = Futures.allAsList((ListenableFuture[])results.toArray(scala.reflect.ClassTag..MODULE$.apply(SettableFuture.class))).get(this.shuffleMergeResultsTimeoutSec(), TimeUnit.SECONDS);
         } catch (TimeoutException var13) {
            timedOut = true;
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Timed out on waiting for merge results from all "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " mergers for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_MERGERS..MODULE$, BoxesRunTime.boxToInteger(numMergers))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"shuffle ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))}))))));
            var15 = BoxedUnit.UNIT;
         } finally {
            if (timedOut || !registerMergeResults) {
               this.cancelFinalizeShuffleMergeFutures(scheduledFutures, timedOut ? 0L : this.shuffleMergeResultsTimeoutSec());
            }

            this.eventProcessLoop().post(new ShuffleMergeFinalized(stage));
         }

         return var15;
      });
   }

   public boolean finalizeShuffleMerge$default$2() {
      return true;
   }

   private void cancelFinalizeShuffleMergeFutures(final Seq futures, final long delayInSecs) {
      if (delayInSecs > 0L) {
         this.shuffleMergeFinalizeScheduler().schedule(new Runnable(futures) {
            // $FF: synthetic field
            private final DAGScheduler $outer;
            private final Seq futures$1;

            public void run() {
               DAGScheduler.org$apache$spark$scheduler$DAGScheduler$$cancelFutures$1(this.futures$1);
            }

            public {
               if (DAGScheduler.this == null) {
                  throw null;
               } else {
                  this.$outer = DAGScheduler.this;
                  this.futures$1 = futures$1;
               }
            }
         }, delayInSecs, TimeUnit.SECONDS);
      } else {
         org$apache$spark$scheduler$DAGScheduler$$cancelFutures$1(futures);
      }
   }

   private void processShuffleMapStageCompletion(final ShuffleMapStage shuffleStage) {
      this.markStageAsFinished(shuffleStage, this.markStageAsFinished$default$2(), this.markStageAsFinished$default$3());
      this.logInfo((Function0)(() -> "looking for newly runnable stages"));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"running: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGES..MODULE$, this.runningStages())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"waiting: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGES..MODULE$, this.waitingStages())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"failed: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGES..MODULE$, this.failedStages())})))));
      this.mapOutputTracker.incrementEpoch();
      this.clearCacheLocs();
      if (!shuffleStage.isAvailable()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Resubmitting ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, shuffleStage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, shuffleStage.name())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because some of its tasks had failed: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITION_IDS..MODULE$, shuffleStage.findMissingPartitions().mkString(", "))}))))));
         this.submitStage(shuffleStage);
      } else {
         this.markMapStageJobsAsFinished(shuffleStage);
         this.submitWaitingChildStages(shuffleStage);
      }
   }

   public void handleRegisterMergeStatuses(final ShuffleMapStage stage, final Seq mergeStatuses) {
      if (this.runningStages().contains(stage) && !stage.shuffleDep().isShuffleMergeFinalizedMarked()) {
         this.mapOutputTracker.registerMergeResults(stage.shuffleDep().shuffleId(), mergeStatuses);
      }
   }

   public void handleShuffleMergeFinalized(final ShuffleMapStage stage, final int shuffleMergeId) {
      if (stage.shuffleDep().shuffleMergeId() == shuffleMergeId) {
         stage.shuffleDep().markShuffleMergeFinalized();
         if (stage.pendingPartitions().isEmpty()) {
            if (this.runningStages().contains(stage)) {
               this.processShuffleMapStageCompletion(stage);
            } else if (stage.isIndeterminate()) {
               this.mapOutputTracker.unregisterAllMergeResult(stage.shuffleDep().shuffleId());
            }
         }
      }
   }

   public void handleShufflePushCompleted(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      Option var5 = this.shuffleIdToMapStage().get(BoxesRunTime.boxToInteger(shuffleId));
      if (var5 instanceof Some var6) {
         ShuffleMapStage mapStage = (ShuffleMapStage)var6.value();
         ShuffleDependency shuffleDep = mapStage.shuffleDep();
         if (shuffleDep.shuffleMergeId() == shuffleMergeId) {
            if (!shuffleDep.isShuffleMergeFinalizedMarked() && (double)shuffleDep.incPushCompleted(mapIndex) / (double)shuffleDep.rdd().partitions().length >= this.shufflePushMinRatio()) {
               this.scheduleShuffleMergeFinalize(mapStage, 0L, this.scheduleShuffleMergeFinalize$default$3());
               BoxedUnit var11 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var9 = BoxedUnit.UNIT;
         }
      } else if (.MODULE$.equals(var5)) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var5);
      }
   }

   private void handleResubmittedFailure(final Task task, final Stage stage) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Resubmitted ", ", so marking it as still running."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, task)})))));
      if (stage instanceof ShuffleMapStage var5) {
         var5.pendingPartitions().$plus$eq(BoxesRunTime.boxToInteger(task.partitionId()));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw SparkCoreErrors$.MODULE$.sendResubmittedTaskStatusForShuffleMapStagesOnlyError();
      }
   }

   public void markMapStageJobsAsFinished(final ShuffleMapStage shuffleStage) {
      if (shuffleStage.isAvailable() && shuffleStage.mapStageJobs().nonEmpty()) {
         MapOutputStatistics stats = this.mapOutputTracker.getStatistics(shuffleStage.shuffleDep());
         shuffleStage.mapStageJobs().foreach((job) -> {
            $anonfun$markMapStageJobsAsFinished$1(this, stats, job);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void handleExecutorLost(final String execId, final Option workerHost) {
      boolean fileLost = !this.sc().shuffleDriverComponents().supportsReliableStorage() && (workerHost.isDefined() || !this.env.blockManager().externalShuffleServiceEnabled());
      this.removeExecutorAndUnregisterOutputs(execId, fileLost, workerHost, .MODULE$, this.removeExecutorAndUnregisterOutputs$default$5());
   }

   private void removeExecutorAndUnregisterOutputs(final String execId, final boolean fileLost, final Option hostToUnregisterOutputs, final Option maybeEpoch, final boolean ignoreShuffleFileLostEpoch) {
      long currentEpoch = BoxesRunTime.unboxToLong(maybeEpoch.getOrElse((JFunction0.mcJ.sp)() -> this.mapOutputTracker.getEpoch()));
      this.logDebug((Function0)(() -> "Considering removal of executor " + execId + "; fileLost: " + fileLost + ", currentEpoch: " + currentEpoch));
      boolean isShuffleMerger = execId.equals(BlockManagerId$.MODULE$.SHUFFLE_MERGER_IDENTIFIER());
      if (isShuffleMerger && this.pushBasedShuffleEnabled()) {
         hostToUnregisterOutputs.foreach((hostx) -> {
            $anonfun$removeExecutorAndUnregisterOutputs$3(this, hostx);
            return BoxedUnit.UNIT;
         });
      }

      if (!isShuffleMerger && (!this.executorFailureEpoch().contains(execId) || BoxesRunTime.unboxToLong(this.executorFailureEpoch().apply(execId)) < currentEpoch)) {
         this.executorFailureEpoch().update(execId, BoxesRunTime.boxToLong(currentEpoch));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor lost: ", " (epoch ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId), new MDC(org.apache.spark.internal.LogKeys.EPOCH..MODULE$, BoxesRunTime.boxToLong(currentEpoch))})))));
         if (this.pushBasedShuffleEnabled()) {
            hostToUnregisterOutputs.foreach((hostx) -> {
               $anonfun$removeExecutorAndUnregisterOutputs$5(this, hostx);
               return BoxedUnit.UNIT;
            });
         }

         this.blockManagerMaster.removeExecutor(execId);
         this.clearCacheLocs();
      }

      if (fileLost) {
         boolean var10000;
         if (ignoreShuffleFileLostEpoch) {
            var10000 = true;
         } else if (this.shuffleFileLostEpoch().contains(execId) && BoxesRunTime.unboxToLong(this.shuffleFileLostEpoch().apply(execId)) >= currentEpoch) {
            var10000 = false;
         } else {
            this.shuffleFileLostEpoch().update(execId, BoxesRunTime.boxToLong(currentEpoch));
            var10000 = true;
         }

         boolean remove = var10000;
         if (remove) {
            if (hostToUnregisterOutputs instanceof Some) {
               Some var12 = (Some)hostToUnregisterOutputs;
               String host = (String)var12.value();
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle files lost for host: ", " (epoch "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EPOCH..MODULE$, BoxesRunTime.boxToLong(currentEpoch))}))))));
               this.mapOutputTracker.removeOutputsOnHost(host);
               BoxedUnit var15 = BoxedUnit.UNIT;
            } else if (.MODULE$.equals(hostToUnregisterOutputs)) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle files lost for executor: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(epoch ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EPOCH..MODULE$, BoxesRunTime.boxToLong(currentEpoch))}))))));
               this.mapOutputTracker.removeOutputsOnExecutor(execId);
               BoxedUnit var14 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(hostToUnregisterOutputs);
            }
         }
      }
   }

   private Option removeExecutorAndUnregisterOutputs$default$4() {
      return .MODULE$;
   }

   private boolean removeExecutorAndUnregisterOutputs$default$5() {
      return false;
   }

   public void handleWorkerRemoved(final String workerId, final String host, final String message) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle files lost for worker ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"on host ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)}))))));
      this.mapOutputTracker.removeOutputsOnHost(host);
      this.clearCacheLocs();
   }

   public void handleExecutorAdded(final String execId, final String host) {
      if (this.executorFailureEpoch().contains(execId)) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Host added was in lost list earlier: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)})))));
         this.executorFailureEpoch().$minus$eq(execId);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.shuffleFileLostEpoch().$minus$eq(execId);
      if (this.pushBasedShuffleEnabled()) {
         ((HashMap)this.shuffleIdToMapStage().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$handleExecutorAdded$2(this, x0$1)))).foreach((x0$2) -> {
            $anonfun$handleExecutorAdded$3(this, x0$2);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void handleStageCancellation(final int stageId, final Option reason) {
      Option var4 = this.stageIdToStage().get(BoxesRunTime.boxToInteger(stageId));
      if (var4 instanceof Some var5) {
         Stage stage = (Stage)var5.value();
         int[] jobsThatUseStage = (int[])stage.jobIds().toArray(scala.reflect.ClassTag..MODULE$.Int());
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.intArrayOps(jobsThatUseStage), (JFunction1.mcVI.sp)(jobId) -> {
            String var10000;
            if (reason instanceof Some var7) {
               String originalReason = (String)var7.value();
               var10000 = "because " + originalReason;
            } else {
               if (!.MODULE$.equals(reason)) {
                  throw new MatchError(reason);
               }

               var10000 = "because Stage " + stageId + " was cancelled";
            }

            String reasonStr = var10000;
            this.handleJobCancellation(jobId, scala.Option..MODULE$.apply(reasonStr));
         });
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var4)) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No active jobs to kill for Stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var4);
      }
   }

   public void handleJobCancellation(final int jobId, final Option reason) {
      if (!this.jobIdToStageIds().contains(BoxesRunTime.boxToInteger(jobId))) {
         this.logDebug((Function0)(() -> "Trying to cancel unregistered job " + jobId));
      } else {
         this.failJobAndIndependentStages((ActiveJob)this.jobIdToActiveJob().apply(BoxesRunTime.boxToInteger(jobId)), SparkCoreErrors$.MODULE$.sparkJobCancelled(jobId, (String)reason.getOrElse(() -> ""), (Exception)null));
      }
   }

   private void markStageAsFinished(final Stage stage, final Option errorMessage, final boolean willRetry) {
      Option var6 = stage.latestInfo().submissionTime();
      Object var10000;
      if (var6 instanceof Some var7) {
         long t = BoxesRunTime.unboxToLong(var7.value());
         var10000 = BoxesRunTime.boxToLong(this.clock.getTimeMillis() - t);
      } else {
         var10000 = "Unknown";
      }

      Object serviceTime = var10000;
      if (errorMessage.isEmpty()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"finished in ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, serviceTime)}))))));
         stage.latestInfo().completionTime_$eq(new Some(BoxesRunTime.boxToLong(this.clock.getTimeMillis())));
         stage.clearFailures();
      } else {
         stage.latestInfo().stageFailed((String)errorMessage.get());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (", ") failed in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage), new MDC(org.apache.spark.internal.LogKeys.STAGE_NAME..MODULE$, stage.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms due to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, serviceTime), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, errorMessage.get())}))))));
      }

      this.updateStageInfoForPushBasedShuffle(stage);
      if (!willRetry) {
         this.outputCommitCoordinator().stageEnd(stage.id());
      }

      this.listenerBus.post(new SparkListenerStageCompleted(stage.latestInfo()));
      this.runningStages().$minus$eq(stage);
   }

   private Option markStageAsFinished$default$2() {
      return .MODULE$;
   }

   private boolean markStageAsFinished$default$3() {
      return false;
   }

   public void stageFailed(final int stageId, final String reason) {
      this.eventProcessLoop().post(new StageFailed(stageId, reason, .MODULE$));
   }

   public void abortStage(final Stage failedStage, final String reason, final Option exception) {
      if (this.stageIdToStage().contains(BoxesRunTime.boxToInteger(failedStage.id()))) {
         Seq dependentJobs = ((IterableOnceOps)this.activeJobs().filter((job) -> BoxesRunTime.boxToBoolean($anonfun$abortStage$1(this, failedStage, job)))).toSeq();
         failedStage.latestInfo().completionTime_$eq(new Some(BoxesRunTime.boxToLong(this.clock.getTimeMillis())));
         this.updateStageInfoForPushBasedShuffle(failedStage);
         dependentJobs.foreach((job) -> {
            $anonfun$abortStage$2(this, exception, reason, job);
            return BoxedUnit.UNIT;
         });
         if (dependentJobs.isEmpty()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring failure of ", " because all jobs "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILED_STAGE..MODULE$, failedStage)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"depending on it are done"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }
      }
   }

   private void updateStageInfoForPushBasedShuffle(final Stage stage) {
      if (stage instanceof ShuffleMapStage var4) {
         stage.latestInfo().setPushBasedShuffleEnabled(var4.shuffleDep().shuffleMergeEnabled());
         if (var4.shuffleDep().shuffleMergeEnabled()) {
            stage.latestInfo().setShuffleMergerCount(var4.shuffleDep().getMergerLocs().size());
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var5 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private boolean cancelRunningIndependentStages(final ActiveJob job, final String reason) {
      BooleanRef ableToCancelStages = BooleanRef.create(true);
      HashSet stages = (HashSet)this.jobIdToStageIds().apply(BoxesRunTime.boxToInteger(job.jobId()));
      if (stages.isEmpty()) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No stages registered for job ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job.jobId()))})))));
      }

      stages.foreach((JFunction1.mcVI.sp)(stageId) -> {
         Option jobsForStage = this.stageIdToStage().get(BoxesRunTime.boxToInteger(stageId)).map((x$37) -> x$37.jobIds());
         if (!jobsForStage.isEmpty() && ((HashSet)jobsForStage.get()).contains(BoxesRunTime.boxToInteger(job.jobId()))) {
            if (((HashSet)jobsForStage.get()).size() == 1) {
               if (!this.stageIdToStage().contains(BoxesRunTime.boxToInteger(stageId))) {
                  this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Missing Stage for stage with id ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))})))));
               } else {
                  Stage stage = (Stage)this.stageIdToStage().apply(BoxesRunTime.boxToInteger(stageId));
                  if (this.runningStages().contains(stage) || stage.failedAttemptIds().nonEmpty()) {
                     try {
                        this.taskScheduler().killAllTaskAttempts(stageId, this.shouldInterruptTaskThread(job), reason);
                        if (this.legacyAbortStageAfterKillTasks()) {
                           this.stageFailed(stageId, reason);
                        }

                        this.markStageAsFinished(stage, new Some(reason), this.markStageAsFinished$default$3());
                     } catch (UnsupportedOperationException var8) {
                        this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not cancel tasks for stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))})))), var8);
                        ableToCancelStages.elem = false;
                     }

                  }
               }
            }
         } else {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Job ", " not registered for stage "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job.jobId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " even though that stage was registered for the job"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))}))))));
         }
      });
      return ableToCancelStages.elem;
   }

   private void failJobAndIndependentStages(final ActiveJob job, final Exception error) {
      if (this.cancelRunningIndependentStages(job, error.getMessage())) {
         this.cleanupStateForJobAndIndependentStages(job);
         job.listener().jobFailed(error);
         this.listenerBus.post(new SparkListenerJobEnd(job.jobId(), this.clock.getTimeMillis(), new JobFailed(error)));
      }
   }

   private boolean stageDependsOn(final Stage stage, final Stage target) {
      if (stage == null) {
         if (target == null) {
            return true;
         }
      } else if (stage.equals(target)) {
         return true;
      }

      HashSet visitedRdds = new HashSet();
      ListBuffer waitingForVisit = new ListBuffer();
      waitingForVisit.$plus$eq(stage.rdd());

      while(waitingForVisit.nonEmpty()) {
         this.visit$3((RDD)waitingForVisit.remove(0), visitedRdds, stage, waitingForVisit);
      }

      return visitedRdds.contains(target.rdd());
   }

   public Seq getPreferredLocs(final RDD rdd, final int partition) {
      return this.getPreferredLocsInternal(rdd, partition, new HashSet());
   }

   private Seq getPreferredLocsInternal(final RDD rdd, final int partition, final HashSet visited) {
      Object var4 = new Object();

      Object var10000;
      try {
         if (!visited.add(new Tuple2(rdd, BoxesRunTime.boxToInteger(partition)))) {
            return scala.collection.immutable.Nil..MODULE$;
         }

         Seq cached = (Seq)this.getCacheLocs(rdd).apply(partition);
         if (cached.nonEmpty()) {
            return cached;
         }

         List rddPrefs = rdd.preferredLocations(rdd.partitions()[partition]).toList();
         if (rddPrefs.nonEmpty()) {
            return rddPrefs.filter((x$38) -> BoxesRunTime.boxToBoolean($anonfun$getPreferredLocsInternal$1(x$38))).map((x$39) -> TaskLocation$.MODULE$.apply(x$39));
         }

         rdd.dependencies().foreach((x0$1) -> {
            $anonfun$getPreferredLocsInternal$3(this, partition, visited, var4, x0$1);
            return BoxedUnit.UNIT;
         });
         var10000 = scala.collection.immutable.Nil..MODULE$;
      } catch (NonLocalReturnControl var8) {
         if (var8.key() != var4) {
            throw var8;
         }

         var10000 = (Seq)var8.value();
      }

      return (Seq)var10000;
   }

   public void markMapStageJobAsFinished(final ActiveJob job, final MapOutputStatistics stats) {
      job.finished()[0] = true;
      job.numFinished_$eq(job.numFinished() + 1);
      job.listener().taskSucceeded(0, stats);
      this.cleanupStateForJobAndIndependentStages(job);
      this.listenerBus.post(new SparkListenerJobEnd(job.jobId(), this.clock.getTimeMillis(), JobSucceeded$.MODULE$));
   }

   public void stop(final int exitCode) {
      Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.messageScheduler().shutdownNow());
      Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.shuffleMergeFinalizeScheduler().shutdownNow());
      Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.eventProcessLoop().stop());
      Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.taskScheduler().stop(exitCode));
   }

   public int stop$default$1() {
      return 0;
   }

   // $FF: synthetic method
   public static final RDDBlockId $anonfun$getCacheLocs$2(final RDD rdd$1, final int index) {
      return new RDDBlockId(rdd$1.id(), index);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkBarrierStageWithRDDChainPattern$2(final Dependency x$1) {
      return x$1.rdd().isBarrier();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkBarrierStageWithRDDChainPattern$1(final int numTasksInStage$1, final RDD r) {
      return r.getNumPartitions() == numTasksInStage$1 && r.dependencies().count((x$1) -> BoxesRunTime.boxToBoolean($anonfun$checkBarrierStageWithRDDChainPattern$2(x$1))) <= 1;
   }

   private final void visit$1(final RDD rdd, final HashSet visited$1, final Stage stage$1, final HashSet missing$1, final ListBuffer waitingForVisit$4) {
      if (!visited$1.apply(rdd)) {
         visited$1.$plus$eq(rdd);
         boolean rddHasUncachedPartitions = this.getCacheLocs(rdd).contains(scala.collection.immutable.Nil..MODULE$);
         if (rddHasUncachedPartitions) {
            rdd.dependencies().foreach((dep) -> {
               if (dep instanceof ShuffleDependency var7) {
                  ShuffleMapStage mapStage = this.getOrCreateShuffleMapStage(var7, stage$1.firstJobId());
                  if (mapStage.isAvailable() && mapStage.shuffleDep().shuffleMergeFinalized()) {
                     mapStage.increaseAttemptIdOnFirstSkip();
                     return BoxedUnit.UNIT;
                  } else {
                     return missing$1.$plus$eq(mapStage);
                  }
               } else if (dep instanceof NarrowDependency var9) {
                  return waitingForVisit$4.prepend(var9.rdd());
               } else {
                  throw new MatchError(dep);
               }
            });
         }
      }
   }

   private static final void visit$2(final RDD rdd, final HashSet visitedRdds$1, final ListBuffer waitingForVisit$5) {
      if (!visitedRdds$1.apply(rdd)) {
         visitedRdds$1.$plus$eq(rdd);
         rdd.partitions();
         rdd.dependencies().foreach((dep) -> waitingForVisit$5.prepend(dep.rdd()));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateJobIdStageIdMaps$2(final int jobId$1, final Stage x$5) {
      return !x$5.jobIds().contains(BoxesRunTime.boxToInteger(jobId$1));
   }

   private final void updateJobIdStageIdMapsList$1(final List stages, final int jobId$1) {
      while(stages.nonEmpty()) {
         Stage s = (Stage)stages.head();
         s.jobIds().$plus$eq(BoxesRunTime.boxToInteger(jobId$1));
         ((Growable)this.jobIdToStageIds().getOrElseUpdate(BoxesRunTime.boxToInteger(jobId$1), () -> new HashSet())).$plus$eq(BoxesRunTime.boxToInteger(s.id()));
         List parentsWithoutThisJobId = s.parents().filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$updateJobIdStageIdMaps$2(jobId$1, x$5)));
         stages = (List)parentsWithoutThisJobId.$plus$plus((IterableOnce)stages.tail());
      }

      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupStateForJobAndIndependentStages$2(final Option registeredStages$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int stageId = x0$1._1$mcI$sp();
         return ((HashSet)registeredStages$1.get()).contains(BoxesRunTime.boxToInteger(stageId));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupStateForJobAndIndependentStages$7(final Stage stage$2, final Tuple2 x$6) {
      boolean var3;
      label23: {
         Object var10000 = x$6._2();
         if (var10000 == null) {
            if (stage$2 == null) {
               break label23;
            }
         } else if (var10000.equals(stage$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupStateForJobAndIndependentStages$8(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   private final void removeStage$1(final int stageId) {
      this.stageIdToStage().get(BoxesRunTime.boxToInteger(stageId)).foreach((stage) -> {
         if (this.runningStages().contains(stage)) {
            this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Removing running stage %d"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(stageId)}))));
            this.runningStages().$minus$eq(stage);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.shuffleIdToMapStage().find((x$6) -> BoxesRunTime.boxToBoolean($anonfun$cleanupStateForJobAndIndependentStages$7(stage, x$6))).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$cleanupStateForJobAndIndependentStages$8(check$ifrefutable$1))).foreach((x$7) -> {
            if (x$7 != null) {
               int k = x$7._1$mcI$sp();
               return this.shuffleIdToMapStage().remove(BoxesRunTime.boxToInteger(k));
            } else {
               throw new MatchError(x$7);
            }
         });
         if (this.waitingStages().contains(stage)) {
            this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Removing stage %d from waiting set."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(stageId)}))));
            this.waitingStages().$minus$eq(stage);
         } else {
            BoxedUnit var3 = BoxedUnit.UNIT;
         }

         if (this.failedStages().contains(stage)) {
            this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Removing stage %d from failed set."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(stageId)}))));
            return this.failedStages().$minus$eq(stage);
         } else {
            return BoxedUnit.UNIT;
         }
      });
      this.stageIdToStage().$minus$eq(BoxesRunTime.boxToInteger(stageId));
      this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("After removal of stage %d, remaining stages = %d"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(stageId), BoxesRunTime.boxToInteger(this.stageIdToStage().size())}))));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupStateForJobAndIndependentStages$3(final DAGScheduler $this, final ActiveJob job$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int stageId = x0$2._1$mcI$sp();
         Stage stage = (Stage)x0$2._2();
         HashSet jobSet = stage.jobIds();
         if (!jobSet.contains(BoxesRunTime.boxToInteger(job$1.jobId()))) {
            $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Job ", " not registered for stage ", " even though that stage was registered for the job"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, BoxesRunTime.boxToInteger(job$1.jobId())), new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))})))));
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            jobSet.$minus$eq(BoxesRunTime.boxToInteger(job$1.jobId()));
            if (jobSet.isEmpty()) {
               $this.removeStage$1(stageId);
               BoxedUnit var8 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$submitJob$2(final int maxPartitions$1, final int p) {
      throw new IllegalArgumentException("Attempting to access a non-existent partition: " + p + ". Total number of partitions: " + maxPartitions$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$submitMapStage$1(final Function1 callback$1, final int x$8, final MapOutputStatistics r) {
      callback$1.apply(r);
   }

   // $FF: synthetic method
   public static final int $anonfun$doCancelAllJobs$1(final Stage x$9) {
      return x$9.firstJobId();
   }

   // $FF: synthetic method
   public static final int $anonfun$resubmitFailedStages$2(final Stage x$11) {
      return x$11.firstJobId();
   }

   // $FF: synthetic method
   public static final void $anonfun$resubmitFailedStages$3(final DAGScheduler $this, final Stage stage) {
      $this.submitStage(stage);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$submitWaitingChildStages$5(final Stage parent$1, final Stage x$12) {
      return x$12.parents().contains(parent$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$submitWaitingChildStages$6(final Stage x$13) {
      return x$13.firstJobId();
   }

   // $FF: synthetic method
   public static final void $anonfun$submitWaitingChildStages$7(final DAGScheduler $this, final Stage stage) {
      $this.submitStage(stage);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleJobGroupCancelled$3(final String groupId$2, final Properties x$14) {
      boolean var3;
      label23: {
         String var10000 = x$14.getProperty(SparkContext$.MODULE$.SPARK_JOB_GROUP_ID());
         if (var10000 == null) {
            if (groupId$2 == null) {
               break label23;
            }
         } else if (var10000.equals(groupId$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleJobGroupCancelled$2(final String groupId$2, final ActiveJob activeJob) {
      return scala.Option..MODULE$.apply(activeJob.properties()).exists((x$14) -> BoxesRunTime.boxToBoolean($anonfun$handleJobGroupCancelled$3(groupId$2, x$14)));
   }

   // $FF: synthetic method
   public static final int $anonfun$handleJobGroupCancelled$5(final ActiveJob x$15) {
      return x$15.jobId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleJobTagCancelled$4(final String x$17) {
      return !x$17.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleJobTagCancelled$2(final String tag$2, final Properties properties) {
      return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String)scala.Option..MODULE$.apply(properties.getProperty(SparkContext$.MODULE$.SPARK_JOB_TAGS())).getOrElse(() -> "")).split(SparkContext$.MODULE$.SPARK_JOB_TAGS_SEP())), (x$17) -> BoxesRunTime.boxToBoolean($anonfun$handleJobTagCancelled$4(x$17)))).toSet().contains(tag$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleJobTagCancelled$1(final String tag$2, final ActiveJob activeJob) {
      return scala.Option..MODULE$.apply(activeJob.properties()).exists((properties) -> BoxesRunTime.boxToBoolean($anonfun$handleJobTagCancelled$2(tag$2, properties)));
   }

   // $FF: synthetic method
   public static final int $anonfun$handleJobTagCancelled$6(final ActiveJob x$18) {
      return x$18.jobId();
   }

   // $FF: synthetic method
   public static final void $anonfun$handleStageFailed$1(final DAGScheduler $this, final String reason$1, final Option exception$1, final Stage x$21) {
      $this.abortStage(x$21, reason$1, exception$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$handleTaskSetFailed$1(final DAGScheduler $this, final String reason$2, final Option exception$2, final Stage x$22) {
      $this.abortStage(x$22, reason$2, exception$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanUpAfterSchedulerStop$2(final DAGScheduler $this, final String stageFailedMessage$1, final Stage stage) {
      $this.markStageAsFinished(stage, new Some(stageFailedMessage$1), $this.markStageAsFinished$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanUpAfterSchedulerStop$1(final DAGScheduler $this, final ActiveJob job) {
      SparkException error = new SparkException("Job " + job.jobId() + " cancelled because SparkContext was shut down");
      job.listener().jobFailed(error);
      String stageFailedMessage = "Stage cancelled because SparkContext was shut down";
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps($this.runningStages().toArray(scala.reflect.ClassTag..MODULE$.apply(Stage.class))), (stage) -> {
         $anonfun$cleanUpAfterSchedulerStop$2($this, stageFailedMessage, stage);
         return BoxedUnit.UNIT;
      });
      $this.listenerBus.post(new SparkListenerJobEnd(job.jobId(), $this.clock.getTimeMillis(), new JobFailed(error)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleJobSubmitted$2(final DAGScheduler $this, final String x$24) {
      return $this.cancelledJobGroups().contains(x$24);
   }

   // $FF: synthetic method
   public static final int $anonfun$handleJobSubmitted$4(final int x$25, final int value) {
      return value + 1;
   }

   // $FF: synthetic method
   public static final Option $anonfun$handleJobSubmitted$11(final DAGScheduler $this, final int id) {
      return $this.stageIdToStage().get(BoxesRunTime.boxToInteger(id)).map((x$26) -> x$26.latestInfo());
   }

   // $FF: synthetic method
   public static final Option $anonfun$handleMapStageSubmitted$6(final DAGScheduler $this, final int id) {
      return $this.stageIdToStage().get(BoxesRunTime.boxToInteger(id)).map((x$27) -> x$27.latestInfo());
   }

   // $FF: synthetic method
   public static final int $anonfun$submitStage$2(final Stage x$28) {
      return x$28.id();
   }

   // $FF: synthetic method
   public static final void $anonfun$submitStage$5(final DAGScheduler $this, final Stage parent) {
      $this.submitStage(parent);
   }

   // $FF: synthetic method
   public static final String $anonfun$addPySparkConfigsToProperties$1(final int x$29) {
      return Integer.toString(x$29);
   }

   // $FF: synthetic method
   public static final Object $anonfun$addPySparkConfigsToProperties$2(final Properties properties$2, final long mem) {
      return properties$2.setProperty(ResourceProfile$.MODULE$.PYSPARK_MEMORY_LOCAL_PROPERTY(), Long.toString(mem));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$submitMissingTasks$2(final HashSet stagesToRollback$1, final ActiveJob job) {
      return stagesToRollback$1.contains(job.finalStage());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$submitMissingTasks$5(final DAGScheduler $this, final Stage stage$6, final int id) {
      return new Tuple2(BoxesRunTime.boxToInteger(id), $this.getPreferredLocs(stage$6.rdd(), id));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$submitMissingTasks$6(final DAGScheduler $this, final ResultStage x3$1, final Stage stage$6, final int id) {
      int p = x3$1.partitions()[id];
      return new Tuple2(BoxesRunTime.boxToInteger(id), $this.getPreferredLocs(stage$6.rdd(), p));
   }

   // $FF: synthetic method
   public static final ShuffleMapTask $anonfun$submitMissingTasks$8(final DAGScheduler $this, final scala.collection.Map taskIdToLocations$1, final ObjectRef partitions$2, final ShuffleMapStage x2$1, final ObjectRef taskBinary$1, final JobArtifactSet artifacts$2, final Properties properties$3, final byte[] serializedTaskMetrics$1, final int jobId$5, final int id) {
      Seq locs = (Seq)taskIdToLocations$1.apply(BoxesRunTime.boxToInteger(id));
      Partition part = ((Partition[])partitions$2.elem)[id];
      x2$1.pendingPartitions().$plus$eq(BoxesRunTime.boxToInteger(id));
      return new ShuffleMapTask(x2$1.id(), x2$1.latestInfo().attemptNumber(), (Broadcast)taskBinary$1.elem, part, x2$1.numPartitions(), locs, artifacts$2, properties$3, serializedTaskMetrics$1, scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(jobId$5)), scala.Option..MODULE$.apply($this.sc().applicationId()), $this.sc().applicationAttemptId(), x2$1.rdd().isBarrier());
   }

   // $FF: synthetic method
   public static final ResultTask $anonfun$submitMissingTasks$9(final DAGScheduler $this, final ResultStage x3$2, final ObjectRef partitions$2, final scala.collection.Map taskIdToLocations$1, final ObjectRef taskBinary$1, final JobArtifactSet artifacts$2, final Properties properties$3, final byte[] serializedTaskMetrics$1, final int jobId$5, final int id) {
      int p = x3$2.partitions()[id];
      Partition part = ((Partition[])partitions$2.elem)[p];
      Seq locs = (Seq)taskIdToLocations$1.apply(BoxesRunTime.boxToInteger(id));
      return new ResultTask(x3$2.id(), x3$2.latestInfo().attemptNumber(), (Broadcast)taskBinary$1.elem, part, x3$2.numPartitions(), locs, id, artifacts$2, properties$3, serializedTaskMetrics$1, scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(jobId$5)), scala.Option..MODULE$.apply($this.sc().applicationId()), $this.sc().applicationAttemptId(), x3$2.rdd().isBarrier());
   }

   // $FF: synthetic method
   public static final int $anonfun$submitMissingTasks$11(final Task x$31) {
      return x$31.partitionId();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateAccumulators$1(final DAGScheduler $this, final Stage stage$7, final CompletionEvent event$1, final Task task$1, final AccumulatorV2 updates) {
      long id = updates.id();

      try {
         Option var11 = AccumulatorContext$.MODULE$.get(id);
         if (!(var11 instanceof Some)) {
            if (.MODULE$.equals(var11)) {
               throw SparkCoreErrors$.MODULE$.accessNonExistentAccumulatorError(id);
            }

            throw new MatchError(var11);
         }

         Some var12 = (Some)var11;
         AccumulatorV2 accum = (AccumulatorV2)var12.value();
         accum.merge(updates);
         if (accum.name().isDefined() && !updates.isZero()) {
            stage$7.latestInfo().accumulables().update(BoxesRunTime.boxToLong(id), accum.toInfo(.MODULE$, new Some(accum.value())));
            TaskInfo var24 = event$1.taskInfo();
            AccumulableInfo var14 = accum.toInfo(new Some(updates.value()), new Some(accum.value()));
            var24.setAccumulables((Seq)event$1.taskInfo().accumulables().$plus$colon(var14));
         }
      } catch (Throwable var22) {
         if (var22 == null || !scala.util.control.NonFatal..MODULE$.apply(var22)) {
            throw var22;
         }

         Option var19 = AccumulatorContext$.MODULE$.get(id);
         String var10000;
         if (var19 instanceof Some var20) {
            AccumulatorV2 accum = (AccumulatorV2)var20.value();
            var10000 = accum.getClass().getName();
         } else {
            if (!.MODULE$.equals(var19)) {
               throw new MatchError(var19);
            }

            var10000 = "Unknown class";
         }

         String accumClassName = var10000;
         $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to update accumulator ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ACCUMULATOR_ID..MODULE$, BoxesRunTime.boxToLong(id))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") for task "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, accumClassName)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITION_ID..MODULE$, BoxesRunTime.boxToInteger(task$1.partitionId()))}))))), var22);
         BoxedUnit var23 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   private final long computedTotalSize$lzycompute$1(final LazyLong computedTotalSize$lzy$1, final ShuffleMapStage shuffleStage$1) {
      synchronized(computedTotalSize$lzy$1){}

      long var4;
      try {
         var4 = computedTotalSize$lzy$1.initialized() ? computedTotalSize$lzy$1.value() : computedTotalSize$lzy$1.initialize(BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.longArrayOps(this.mapOutputTracker.getStatistics(shuffleStage$1.shuffleDep()).bytesByPartitionId()), (JFunction1.mcZJ.sp)(x$32) -> x$32 > 0L)).sum(scala.math.Numeric.LongIsIntegral..MODULE$)));
      } catch (Throwable var7) {
         throw var7;
      }

      return var4;
   }

   private final long computedTotalSize$1(final LazyLong computedTotalSize$lzy$1, final ShuffleMapStage shuffleStage$1) {
      return computedTotalSize$lzy$1.initialized() ? computedTotalSize$lzy$1.value() : this.computedTotalSize$lzycompute$1(computedTotalSize$lzy$1, shuffleStage$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleTaskCompletion$14(final ExecutorDecommissionState x$33) {
      return x$33.workerHost().isDefined();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleTaskCompletion$17(final DAGScheduler $this, final ActiveJob j) {
      return $this.shouldInterruptTaskThread(j);
   }

   // $FF: synthetic method
   public static final void $anonfun$collectSucceedingStages$2(final List stageChain$1, final HashSet succeedingStages$1, final Stage s) {
      collectSucceedingStagesInternal$1(stageChain$1.$colon$colon(s), succeedingStages$1);
   }

   private static final void collectSucceedingStagesInternal$1(final List stageChain, final HashSet succeedingStages$1) {
      if (succeedingStages$1.contains(stageChain.head())) {
         ((List)stageChain.drop(1)).foreach((s) -> (HashSet)succeedingStages$1.$plus$eq(s));
      } else {
         ((Stage)stageChain.head()).parents().foreach((s) -> {
            $anonfun$collectSucceedingStages$2(stageChain, succeedingStages$1, s);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$collectSucceedingStages$3(final HashSet succeedingStages$1, final ActiveJob job) {
      Stage var2 = job.finalStage();
      collectSucceedingStagesInternal$1(scala.collection.immutable.Nil..MODULE$.$colon$colon(var2), succeedingStages$1);
   }

   private static final String generateErrorMessage$1(final Stage stage) {
      return "A shuffle map stage with indeterminate output was failed and retried. However, Spark cannot rollback the " + stage + " to re-process the input data, and has to fail this job. Please eliminate the indeterminacy by checkpointing the RDD before repartition and try again.";
   }

   // $FF: synthetic method
   public static final SettableFuture $anonfun$finalizeShuffleMerge$2(final int x$34) {
      return SettableFuture.create();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$finalizeShuffleMerge$4(final SettableFuture x$35) {
      return x$35.set(BoxesRunTime.boxToBoolean(true));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cancelFinalizeShuffleMergeFutures$1(final Future x$36) {
      return x$36.cancel(true);
   }

   public static final void org$apache$spark$scheduler$DAGScheduler$$cancelFutures$1(final Seq futures$1) {
      futures$1.foreach((x$36) -> BoxesRunTime.boxToBoolean($anonfun$cancelFinalizeShuffleMergeFutures$1(x$36)));
   }

   // $FF: synthetic method
   public static final void $anonfun$markMapStageJobsAsFinished$1(final DAGScheduler $this, final MapOutputStatistics stats$1, final ActiveJob job) {
      $this.markMapStageJobAsFinished(job, stats$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutorAndUnregisterOutputs$3(final DAGScheduler $this, final String host) {
      $this.blockManagerMaster.removeShufflePushMergerLocation(host);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutorAndUnregisterOutputs$5(final DAGScheduler $this, final String host) {
      $this.blockManagerMaster.removeShufflePushMergerLocation(host);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleExecutorAdded$2(final DAGScheduler $this, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         ShuffleMapStage stage = (ShuffleMapStage)x0$1._2();
         return stage.shuffleDep().shuffleMergeAllowed() && stage.shuffleDep().getMergerLocs().isEmpty() && $this.runningStages().contains(stage);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$handleExecutorAdded$3(final DAGScheduler $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         ShuffleMapStage stage = (ShuffleMapStage)x0$2._2();
         if (stage != null) {
            $this.configureShufflePushMergerLocations(stage);
            if (stage.shuffleDep().getMergerLocs().nonEmpty()) {
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle merge enabled adaptively for ", " with shuffle"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, stage)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " and shuffle merge"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(stage.shuffleDep().shuffleId()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, BoxesRunTime.boxToInteger(stage.shuffleDep().shuffleMergeId()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " merger locations"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_MERGER_LOCATIONS..MODULE$, BoxesRunTime.boxToInteger(stage.shuffleDep().getMergerLocs().size()))}))))));
               BoxedUnit var6 = BoxedUnit.UNIT;
               return;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$abortStage$1(final DAGScheduler $this, final Stage failedStage$3, final ActiveJob job) {
      return $this.stageDependsOn(job.finalStage(), failedStage$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$abortStage$2(final DAGScheduler $this, final Option exception$3, final String reason$4, final ActiveJob job) {
      Exception finalException = (Exception)exception$3.collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 instanceof Exception var5 && x1 instanceof SparkThrowable) {
               if (((SparkThrowable)var5).getCondition() != null && !org.apache.spark.SparkThrowableHelper..MODULE$.isInternalError(((SparkThrowable)var5).getCondition())) {
                  return var5;
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final Throwable x1) {
            if (x1 instanceof Exception var4 && x1 instanceof SparkThrowable) {
               if (((SparkThrowable)var4).getCondition() != null && !org.apache.spark.SparkThrowableHelper..MODULE$.isInternalError(((SparkThrowable)var4).getCondition())) {
                  return true;
               }
            }

            return false;
         }
      }).getOrElse(() -> new SparkException("Job aborted due to stage failure: " + reason$4, (Throwable)exception$3.orNull(scala..less.colon.less..MODULE$.refl())));
      $this.failJobAndIndependentStages(job, finalException);
   }

   private final void visit$3(final RDD rdd, final HashSet visitedRdds$2, final Stage stage$11, final ListBuffer waitingForVisit$6) {
      if (!visitedRdds$2.apply(rdd)) {
         visitedRdds$2.$plus$eq(rdd);
         rdd.dependencies().foreach((dep) -> {
            if (dep instanceof ShuffleDependency var6) {
               ShuffleMapStage mapStage = this.getOrCreateShuffleMapStage(var6, stage$11.firstJobId());
               return !mapStage.isAvailable() ? waitingForVisit$6.prepend(mapStage.rdd()) : BoxedUnit.UNIT;
            } else if (dep instanceof NarrowDependency var8) {
               return waitingForVisit$6.prepend(var8.rdd());
            } else {
               throw new MatchError(dep);
            }
         });
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPreferredLocsInternal$1(final String x$38) {
      return x$38 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$getPreferredLocsInternal$3(final DAGScheduler $this, final int partition$1, final HashSet visited$2, final Object nonLocalReturnKey1$1, final Dependency x0$1) {
      if (x0$1 instanceof NarrowDependency var7) {
         var7.getParents(partition$1).foreach((JFunction1.mcVI.sp)(inPart) -> {
            Seq locs = $this.getPreferredLocsInternal(var7.rdd(), inPart, visited$2);
            Nil var6 = scala.collection.immutable.Nil..MODULE$;
            if (locs == null) {
               if (var6 != null) {
                  throw new NonLocalReturnControl(nonLocalReturnKey1$1, locs);
               }
            } else if (!locs.equals(var6)) {
               throw new NonLocalReturnControl(nonLocalReturnKey1$1, locs);
            }

         });
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public DAGScheduler(final SparkContext sc, final TaskScheduler taskScheduler, final LiveListenerBus listenerBus, final MapOutputTrackerMaster mapOutputTracker, final BlockManagerMaster blockManagerMaster, final SparkEnv env, final Clock clock) {
      this.sc = sc;
      this.taskScheduler = taskScheduler;
      this.listenerBus = listenerBus;
      this.mapOutputTracker = mapOutputTracker;
      this.blockManagerMaster = blockManagerMaster;
      this.env = env;
      this.clock = clock;
      Logging.$init$(this);
      this.metricsSource = new DAGSchedulerSource(this);
      this.nextJobId = new AtomicInteger(0);
      this.nextStageId = new AtomicInteger(0);
      this.jobIdToStageIds = new HashMap();
      this.stageIdToStage = new HashMap();
      this.shuffleIdToMapStage = new HashMap();
      this.jobIdToActiveJob = new HashMap();
      this.waitingStages = new HashSet();
      this.runningStages = new HashSet();
      this.failedStages = new HashSet();
      this.activeJobs = new HashSet();
      this.cancelledJobGroups = new LimitedSizeFIFOSet(BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.NUM_CANCELLED_JOB_GROUPS_TO_TRACK())));
      this.cacheLocs = new HashMap();
      this.executorFailureEpoch = new HashMap();
      this.shuffleFileLostEpoch = new HashMap();
      this.outputCommitCoordinator = env.outputCommitCoordinator();
      this.closureSerializer = SparkEnv$.MODULE$.get().closureSerializer().newInstance();
      this.disallowStageRetryForTest = BoxesRunTime.unboxToBoolean(sc.conf().get(Tests$.MODULE$.TEST_NO_STAGE_RETRY()));
      this.shouldMergeResourceProfiles = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.RESOURCE_PROFILE_MERGE_CONFLICTS()));
      this.unRegisterOutputOnHostOnFetchFailure = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.UNREGISTER_OUTPUT_ON_HOST_ON_FETCH_FAILURE()));
      this.maxConsecutiveStageAttempts = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.STAGE_MAX_CONSECUTIVE_ATTEMPTS()));
      this.maxStageAttempts = Math.max(this.maxConsecutiveStageAttempts(), BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.STAGE_MAX_ATTEMPTS())));
      this.ignoreDecommissionFetchFailure = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE()));
      this.barrierJobIdToNumTasksCheckFailures = new ConcurrentHashMap();
      this.timeIntervalNumTasksCheck = BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.BARRIER_MAX_CONCURRENT_TASKS_CHECK_INTERVAL()));
      this.maxFailureNumTasksCheck = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.BARRIER_MAX_CONCURRENT_TASKS_CHECK_MAX_FAILURES()));
      this.messageScheduler = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("dag-scheduler-message");
      this.eventProcessLoop = new DAGSchedulerEventProcessLoop(this);
      taskScheduler.setDAGScheduler(this);
      this.pushBasedShuffleEnabled = Utils$.MODULE$.isPushBasedShuffleEnabled(sc.conf(), true, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3());
      this.blockManagerMasterDriverHeartbeatTimeout = (new scala.concurrent.duration.package.DurationLong(scala.concurrent.duration.package..MODULE$.DurationLong(BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_BLOCKMANAGER_MASTER_DRIVER_HEARTBEAT_TIMEOUT()))))).millis();
      this.shuffleMergeResultsTimeoutSec = BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.PUSH_BASED_SHUFFLE_MERGE_RESULTS_TIMEOUT()));
      this.shuffleMergeFinalizeWaitSec = BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.PUSH_BASED_SHUFFLE_MERGE_FINALIZE_TIMEOUT()));
      this.shuffleMergeWaitMinSizeThreshold = BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.PUSH_BASED_SHUFFLE_SIZE_MIN_SHUFFLE_SIZE_TO_WAIT()));
      this.shufflePushMinRatio = BoxesRunTime.unboxToDouble(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.PUSH_BASED_SHUFFLE_MIN_PUSH_RATIO()));
      this.shuffleMergeFinalizeNumThreads = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.PUSH_BASED_SHUFFLE_MERGE_FINALIZE_THREADS()));
      this.shuffleFinalizeRpcThreads = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.PUSH_SHUFFLE_FINALIZE_RPC_THREADS()));
      this.shuffleMergeFinalizeScheduler = ThreadUtils$.MODULE$.newDaemonThreadPoolScheduledExecutor("shuffle-merge-finalizer", this.shuffleMergeFinalizeNumThreads());
      this.shuffleSendFinalizeRpcExecutor = ThreadUtils$.MODULE$.newDaemonFixedThreadPool(this.shuffleFinalizeRpcThreads(), "shuffle-merge-finalize-rpc");
      this.trackingCacheVisibility = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.RDD_CACHE_VISIBILITY_TRACKING_ENABLED()));
      this.legacyAbortStageAfterKillTasks = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.LEGACY_ABORT_STAGE_AFTER_KILL_TASKS()));
      this.eventProcessLoop().start();
   }

   public DAGScheduler(final SparkContext sc, final TaskScheduler taskScheduler) {
      this(sc, taskScheduler, sc.listenerBus(), (MapOutputTrackerMaster)sc.env().mapOutputTracker(), sc.env().blockManager().master(), sc.env(), DAGScheduler$.MODULE$.$lessinit$greater$default$7());
   }

   public DAGScheduler(final SparkContext sc) {
      this(sc, sc.taskScheduler());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
