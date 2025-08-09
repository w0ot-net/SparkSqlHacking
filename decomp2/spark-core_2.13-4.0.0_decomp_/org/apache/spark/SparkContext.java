package org.apache.spark;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.Executor$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.executor.ExecutorMetrics$;
import org.apache.spark.executor.ExecutorMetricsSource;
import org.apache.spark.input.FixedLengthBinaryInputFormat;
import org.apache.spark.input.FixedLengthBinaryInputFormat$;
import org.apache.spark.input.PortableDataStream;
import org.apache.spark.input.StreamInputFormat;
import org.apache.spark.input.WholeTextFileInputFormat;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.plugin.PluginContainer;
import org.apache.spark.internal.plugin.PluginContainer$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.metrics.source.JVMCPUSource;
import org.apache.spark.partial.ApproximateEvaluator;
import org.apache.spark.partial.PartialResult;
import org.apache.spark.rdd.BinaryFileRDD;
import org.apache.spark.rdd.EmptyRDD;
import org.apache.spark.rdd.HadoopRDD;
import org.apache.spark.rdd.NewHadoopRDD;
import org.apache.spark.rdd.ParallelCollectionRDD;
import org.apache.spark.rdd.PartitionerAwareUnionRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDDOperationScope$;
import org.apache.spark.rdd.ReliableCheckpointRDD;
import org.apache.spark.rdd.ReliableCheckpointRDD$;
import org.apache.spark.rdd.UnionRDD;
import org.apache.spark.rdd.WholeTextFileRDD;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.ResourceProfileManager;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.scheduler.DAGScheduler;
import org.apache.spark.scheduler.EventLoggingListener;
import org.apache.spark.scheduler.EventLoggingListener$;
import org.apache.spark.scheduler.JobWaiter;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.LiveListenerBus$;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.SparkListenerApplicationEnd;
import org.apache.spark.scheduler.SparkListenerApplicationStart;
import org.apache.spark.scheduler.SparkListenerEnvironmentUpdate;
import org.apache.spark.scheduler.SparkListenerExecutorMetricsUpdate;
import org.apache.spark.scheduler.SparkListenerInterface;
import org.apache.spark.scheduler.SparkListenerUnpersistRDD;
import org.apache.spark.scheduler.TaskScheduler;
import org.apache.spark.shuffle.ShuffleDataIOUtils$;
import org.apache.spark.shuffle.api.ShuffleDriverComponents;
import org.apache.spark.status.AppStatusSource;
import org.apache.spark.status.AppStatusSource$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.AppStatusStore$;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.status.api.v1.ThreadStackTrace;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerMessages;
import org.apache.spark.storage.BlockManagerSource;
import org.apache.spark.storage.FallbackStorage$;
import org.apache.spark.storage.RDDInfo;
import org.apache.spark.storage.RDDInfo$;
import org.apache.spark.ui.ConsoleProgressBar;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUI$;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CallSite;
import org.apache.spark.util.CallSite$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.CollectionAccumulator;
import org.apache.spark.util.DependencyUtils$;
import org.apache.spark.util.DoubleAccumulator;
import org.apache.spark.util.LongAccumulator;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.SparkClosureCleaner$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.logging.DriverLogger;
import org.apache.spark.util.logging.DriverLogger$;
import org.slf4j.Logger;
import org.sparkproject.guava.collect.MapMaker;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.math.BigInt;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005Amha\u0002Bv\u0005[\u0004!1 \u0005\u000b\u0007+\u0001!\u0011!Q\u0001\n\r]\u0001bBB\u0010\u0001\u0011\u00051\u0011\u0005\u0005\n\u0007O\u0001!\u0019!C\u0005\u0007SA\u0001ba\u000e\u0001A\u0003%11\u0006\u0005\n\u0007s\u0001\u0001\u0019!C\u0005\u0007wA\u0011ba\u0011\u0001\u0001\u0004%Ia!\u0012\t\u0011\rE\u0003\u0001)Q\u0005\u0007{A\u0011ba\u0015\u0001\u0005\u0004%\ta!\u0016\t\u0011\ru\u0003\u0001)A\u0005\u0007/B1ba\u0018\u0001\u0005\u0004%\tA!<\u0004b!A1\u0011\u0010\u0001!\u0002\u0013\u0019\u0019\u0007C\u0005\u0004|\u0001!\tA!<\u0004~!91q\u0004\u0001\u0005\u0002\r}\u0004bBB\u0010\u0001\u0011\u00051\u0011\u0011\u0005\b\u0007?\u0001A\u0011ABS\u0011%\u0019y\u0002\u0001C\u0001\u0005[\u001c)\u000eC\u0005\u0004 \u0001!\tA!<\u0004\\\"I1q\u0004\u0001\u0005\u0002\t581\u001d\u0005\f\u0007[\u0004\u0001\u0019!a\u0001\n\u0013\u0019y\u000fC\u0006\u0004r\u0002\u0001\r\u00111A\u0005\n\rM\bbCB|\u0001\u0001\u0007\t\u0011)Q\u0005\u0007/A\u0011b!?\u0001\u0001\u0004%Iaa?\t\u0013\u0011-\u0001\u00011A\u0005\n\u00115\u0001\u0002\u0003C\t\u0001\u0001\u0006Ka!@\t\u0013\u0011M\u0001\u00011A\u0005\n\u0011U\u0001\"\u0003C\r\u0001\u0001\u0007I\u0011\u0002C\u000e\u0011!!y\u0002\u0001Q!\n\u0011]\u0001b\u0003C\u0011\u0001\u0001\u0007\t\u0019!C\u0005\tGA1\u0002\"\r\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u00054!YAq\u0007\u0001A\u0002\u0003\u0005\u000b\u0015\u0002C\u0013\u0011-!I\u0004\u0001a\u0001\u0002\u0004%I\u0001b\u000f\t\u0017\u0011\r\u0003\u00011AA\u0002\u0013%AQ\t\u0005\f\t\u0013\u0002\u0001\u0019!A!B\u0013!i\u0004C\u0006\u0005L\u0001\u0001\r\u00111A\u0005\n\u00115\u0003b\u0003C+\u0001\u0001\u0007\t\u0019!C\u0005\t/B1\u0002b\u0017\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0005P!IAQ\f\u0001A\u0002\u0013%Aq\f\u0005\n\t_\u0002\u0001\u0019!C\u0005\tcB\u0001\u0002\"\u001e\u0001A\u0003&A\u0011\r\u0005\n\to\u0002\u0001\u0019!C\u0005\tsB\u0011\u0002b!\u0001\u0001\u0004%I\u0001\"\"\t\u0011\u0011%\u0005\u0001)Q\u0005\twB1\u0002b#\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0005\u000e\"YAQ\u0014\u0001A\u0002\u0003\u0007I\u0011\u0002CP\u0011-!\u0019\u000b\u0001a\u0001\u0002\u0003\u0006K\u0001b$\t\u0017\u0011\u0015\u0006\u00011AA\u0002\u0013%Aq\u0015\u0005\f\t_\u0003\u0001\u0019!a\u0001\n\u0013!\t\fC\u0006\u00056\u0002\u0001\r\u0011!Q!\n\u0011%\u0006b\u0003C\\\u0001\u0001\u0007\t\u0019!C\u0005\tsC1\u0002\"1\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0005D\"YAq\u0019\u0001A\u0002\u0003\u0005\u000b\u0015\u0002C^\u0011-!I\r\u0001a\u0001\u0002\u0004%I\u0001b3\t\u0017\u0011M\u0007\u00011AA\u0002\u0013%AQ\u001b\u0005\f\t3\u0004\u0001\u0019!A!B\u0013!i\rC\u0006\u0005\\\u0002\u0001\r\u00111A\u0005\n\u0011u\u0007b\u0003Cv\u0001\u0001\u0007\t\u0019!C\u0005\t[D1\u0002\"=\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0005`\"YA1\u001f\u0001A\u0002\u0003\u0007I\u0011\u0002C{\u0011-!i\u0010\u0001a\u0001\u0002\u0004%I\u0001b@\t\u0017\u0015\r\u0001\u00011A\u0001B\u0003&Aq\u001f\u0005\f\u000b\u001b\u0001\u0001\u0019!a\u0001\n\u0013)y\u0001C\u0006\u0006\u0012\u0001\u0001\r\u00111A\u0005\n\u0015M\u0001bCC\f\u0001\u0001\u0007\t\u0011)Q\u0005\u0007\u000fC\u0011\"\"\u0007\u0001\u0001\u0004%I\u0001\"\u0006\t\u0013\u0015m\u0001\u00011A\u0005\n\u0015u\u0001\u0002CC\u0011\u0001\u0001\u0006K\u0001b\u0006\t\u0013\u0015\r\u0002\u00011A\u0005\n\u0015\u0015\u0002\"CC\u0018\u0001\u0001\u0007I\u0011BC\u0019\u0011!))\u0004\u0001Q!\n\u0015\u001d\u0002\"CC\u001c\u0001\u0001\u0007I\u0011BC\u001d\u0011%)I\u0005\u0001a\u0001\n\u0013)Y\u0005\u0003\u0005\u0006P\u0001\u0001\u000b\u0015BC\u001e\u0011%)\t\u0006\u0001a\u0001\n\u0013)\u0019\u0006C\u0005\u0006^\u0001\u0001\r\u0011\"\u0003\u0006`!AQ1\r\u0001!B\u0013))\u0006C\u0005\u0006f\u0001\u0001\r\u0011\"\u0003\u0006h!IQ\u0011\u000f\u0001A\u0002\u0013%Q1\u000f\u0005\t\u000bo\u0002\u0001\u0015)\u0003\u0006j!IQ\u0011\u0010\u0001A\u0002\u0013%Q1\u0010\u0005\n\u000b\u0007\u0003\u0001\u0019!C\u0005\u000b\u000bC\u0001\"\"#\u0001A\u0003&QQ\u0010\u0005\f\u000b\u0017\u0003\u0001\u0019!a\u0001\n\u0013)i\tC\u0006\u0006\u0010\u0002\u0001\r\u00111A\u0005\n\u0015E\u0005bCCK\u0001\u0001\u0007\t\u0011)Q\u0005\u0007gC1\"b&\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0006\u000e\"YQ\u0011\u0014\u0001A\u0002\u0003\u0007I\u0011BCN\u0011-)y\n\u0001a\u0001\u0002\u0003\u0006Kaa-\t\u0017\u0015\u0005\u0006\u00011AA\u0002\u0013%QQ\u0012\u0005\f\u000bG\u0003\u0001\u0019!a\u0001\n\u0013))\u000bC\u0006\u0006*\u0002\u0001\r\u0011!Q!\n\rM\u0006bCCV\u0001\u0001\u0007\t\u0019!C\u0005\u000b[C1\"b,\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u00062\"YQQ\u0017\u0001A\u0002\u0003\u0005\u000b\u0015\u0002B\u007f\u0011-)9\f\u0001a\u0001\u0002\u0004%I!\"/\t\u0017\u0015\u001d\u0007\u00011AA\u0002\u0013%Q\u0011\u001a\u0005\f\u000b\u001b\u0004\u0001\u0019!A!B\u0013)Y\fC\u0006\u0006P\u0002\u0001\r\u00111A\u0005\n\u0015E\u0007bCCm\u0001\u0001\u0007\t\u0019!C\u0005\u000b7D1\"b8\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0006T\"YQ\u0011\u001d\u0001A\u0002\u0003\u0007I\u0011BCr\u0011-)Y\u0010\u0001a\u0001\u0002\u0004%I!\"@\t\u0017\u0019\u0005\u0001\u00011A\u0001B\u0003&QQ\u001d\u0005\f\r\u0007\u0001\u0001\u0019!a\u0001\n\u00131)\u0001C\u0006\u0007\u0018\u0001\u0001\r\u00111A\u0005\n\u0019e\u0001b\u0003D\u000f\u0001\u0001\u0007\t\u0011)Q\u0005\r\u000fA\u0011Bb\b\u0001\u0001\u0004%IA\"\t\t\u0013\u0019E\u0002\u00011A\u0005\n\u0019M\u0002\u0002\u0003D\u001c\u0001\u0001\u0006KAb\t\t\u0017\u0019e\u0002\u00011AA\u0002\u0013%a1\b\u0005\f\r\u0007\u0002\u0001\u0019!a\u0001\n\u00131)\u0005C\u0006\u0007J\u0001\u0001\r\u0011!Q!\n\u0019u\u0002\"CBR\u0001\u0011\u0005!Q^Bx\u0011\u001d1Y\u0005\u0001C\u0001\r\u001bBqA\"\u0016\u0001\t\u0003\u0019y\u000fC\u0004\u0007X\u0001!\tA\"\u0017\t\u000f\rE\u0006\u0001\"\u0001\u0006\u000e\"9aQ\f\u0001\u0005\u0002\u00155\u0005b\u0002D0\u0001\u0011\u0005QQ\u0012\u0005\b\u0007\u000b\u0003A\u0011AC\b\u0011\u001d1\t\u0007\u0001C\u0001\u000b\u001fAqaa(\u0001\t\u0003)y\u0001C\u0005\u0007d\u0001!\tA!<\u0006|!IaQ\r\u0001\u0005\u0002\t581 \u0005\n\rO\u0002A\u0011\u0001Bw\t+AqA\"\u001b\u0001\t\u0003)Y\bC\u0004\u0007l\u0001!\t!b\u001f\t\u0013\u00195\u0004\u0001\"\u0001\u0003n\u0016e\u0006\"\u0003D8\u0001\u0011\u0005!Q\u001eC\u0012\u0011%1\t\b\u0001C\u0001\u0005[4\u0019\bC\u0005\u0007|\u0001!\tA!<\u0005<!YaQ\u0010\u0001C\u0002\u0013\u0005!Q\u001eD@\u0011!1Y\t\u0001Q\u0001\n\u0019\u0005\u0005b\u0003DG\u0001\t\u0007I\u0011\u0001Bw\r\u007fB\u0001Bb$\u0001A\u0003%a\u0011\u0011\u0005\f\r#\u0003!\u0019!C\u0001\u0005[4y\b\u0003\u0005\u0007\u0014\u0002\u0001\u000b\u0011\u0002DA\u0011%1)\n\u0001C\u0001\u0005[49\nC\u0005\u0007\u001c\u0002!\tA!<\u0007\u0018\"IaQ\u0014\u0001\u0005\u0002\t5hq\u0013\u0005\f\r?\u0003!\u0019!C\u0001\u0005[4\t\u000b\u0003\u0005\u0007@\u0002\u0001\u000b\u0011\u0002DR\u0011\u001d19\u000e\u0001C\u0001\t\u001bB\u0011B\"7\u0001\t\u0003\u0011i\u000fb\u0018\t\u0013\u0011%\u0004\u0001\"\u0001\u0003n\u0012e\u0004b\u0002Dn\u0001\u0011\u0005AQ\u0003\u0005\b\r;\u0004A\u0011\u0001CG\u0011%1y\u000e\u0001C\u0001\u0005[$9\u000bC\u0006\u0007b\u0002\u0011\r\u0011\"\u0001\u0003n\u001a\r\b\u0002\u0003Dy\u0001\u0001\u0006IA\":\t\u0013\u0019M\bA1A\u0005\u0002\u0015=\u0001\u0002\u0003D{\u0001\u0001\u0006Iaa\"\t\u0013\u0019]\b\u0001\"\u0001\u0003n\u0012e\u0006\"\u0003D}\u0001\u0011\u0005!Q\u001eCf\u0011%1Y\u0010\u0001C\u0001\u0005[4i\u0010C\u0005\b\u0004\u0001!\tA!<\u0005v\"IqQ\u0001\u0001\u0005\u0002\t5xq\u0001\u0005\n\u000f\u001b\u0001A\u0011\u0001Bw\r\u000bAqab\u0004\u0001\t\u0003)y\u0001C\u0004\b\u0012\u0001!\t\u0001\"\u0006\t\u0013\u001dM\u0001\u0001\"\u0001\u0003n\u0016\u0015\u0002\"CD\u000b\u0001\u0011\u0005!Q^C*\u0011%99\u0002\u0001C\u0001\u0005[4Y\u0004C\u0005\b\u001a\u0001!\tA!<\u0006h!Yq1\u0004\u0001A\u0002\u0013\u0005!Q\u001eC\u000b\u0011-9i\u0002\u0001a\u0001\n\u0003\u0011iob\b\t\u0011\u001d\r\u0002\u0001)Q\u0005\t/A1b\"\n\u0001\u0005\u0004%\tB!<\b(!AqQ\b\u0001!\u0002\u00139I\u0003C\u0004\b@\u0001!\ta\"\u0011\t\u0013\u001d\u001d\u0003\u0001\"\u0001\u0003n\u001e%\u0003\"CD3\u0001\u0011\u0005!Q^D4\u0011%9\t\b\u0001C\u0001\u0005[<\u0019\bC\u0005\b|\u0001!\tA!<\b~!Iqq\u0010\u0001\u0005\u0002\t5x\u0011\u0011\u0005\b\u000f\u000f\u0003A\u0011ADE\u0011\u001d9\u0019\n\u0001C\u0001\u000f+Cqa\"'\u0001\t\u00039Y\nC\u0004\b \u0002!\ta\")\t\u0013\u001d=\u0006!%A\u0005\u0002\u001dE\u0006bBDd\u0001\u0011\u00051Q\u0010\u0005\b\u000f\u0013\u0004A\u0011ADf\u0011\u001d9y\r\u0001C\u0001\u000f#Dqab6\u0001\t\u00039I\u000eC\u0004\bf\u0002!\tab:\t\u000f\u001d-\b\u0001\"\u0001\bn\"9q\u0011\u001f\u0001\u0005\u0002\u001dM\bbBD{\u0001\u0011\u00051Q\u0010\u0005\n\u000fo\u0004A\u0011\u0001Bw\u000fsDq\u0001#\u0004\u0001\t\u0003Ay\u0001C\u0005\t8\u0001\t\n\u0011\"\u0001\t:!9\u0001\u0012\t\u0001\u0005\u0002!\r\u0003\"\u0003E+\u0001E\u0005I\u0011\u0001E,\u0011%AY\u0006AI\u0001\n\u0003AY\u0004C\u0004\t^\u0001!\t\u0001c\u0018\t\u0013!]\u0004!%A\u0005\u0002!e\u0004b\u0002E/\u0001\u0011\u0005\u0001R\u0010\u0005\b\u00113\u0003A\u0011\u0001EN\u0011%A9\u000bAI\u0001\n\u0003AY\u0004C\u0004\t*\u0002!\t\u0001c+\t\u0013!U\u0006!%A\u0005\u0002!m\u0002b\u0002E\\\u0001\u0011\u0005\u0001\u0012\u0018\u0005\n\u0011\u001f\u0004\u0011\u0013!C\u0001\u0011wAq\u0001#5\u0001\t\u0003A\u0019\u000eC\u0005\th\u0002\t\n\u0011\"\u0001\tj\"9\u0001R\u001e\u0001\u0005\u0002!=\b\"CE\u001d\u0001E\u0005I\u0011AE\u001e\u0011\u001dI\t\u0005\u0001C\u0001\u0013\u0007B\u0011\"c\u001c\u0001#\u0003%\t!#\u001d\t\u000f%\u0005\u0003\u0001\"\u0001\nx!9\u0011\u0012\t\u0001\u0005\u0002%%\u0006bBEi\u0001\u0011\u0005\u00112\u001b\u0005\b\u0013#\u0004A\u0011\u0001F\u0002\u0011%Q\t\u0004AI\u0001\n\u0003Q\u0019\u0004C\u0004\u000bD\u0001!\tA#\u0012\t\u0013)-\u0004!%A\u0005\u0002)5\u0004b\u0002F?\u0001\u0011\u0005!r\u0010\u0005\b\u0015{\u0002A\u0011\u0001FN\u0011\u001dQi\b\u0001C\u0001\u0015kC\u0011Bc;\u0001#\u0003%\tA#<\t\u000f)M\b\u0001\"\u0001\u000bv\"I12\u0002\u0001\u0012\u0002\u0013\u00051R\u0002\u0005\n\u0017#\u0001A\u0011\u0003Bw\u0017'Aqac\n\u0001\t\u0003YI\u0003C\u0004\f(\u0001!\ta#\u0011\t\u000f-\u0005\u0004\u0001\"\u0001\fd!912\u000f\u0001\u0005\u0002-U\u0004bBF:\u0001\u0011\u00051r\u0012\u0005\b\u0017O\u0003A\u0011AFU\u0011\u001dY9\u000b\u0001C\u0001\u0017cCqa#.\u0001\t\u0003Y9\fC\u0004\f6\u0002!\tac0\t\u000f-\r\u0007\u0001\"\u0001\fF\"912\u0019\u0001\u0005\u0002-M\u0007bBFp\u0001\u0011\u00051\u0012\u001d\u0005\n\u0017{\u0004A\u0011\u0001Bw\u0017\u007fDq\u0001d\u0006\u0001\t\u0003aI\u0002C\u0004\r\u001e\u0001!\t\u0001d\b\t\u000f1\u0005\u0002\u0001\"\u0001\r$!9A2\u0007\u0001\u0005\u00021}\u0001b\u0002G\f\u0001\u0011\u0005Ar\u0007\u0005\b\u0019/\u0001A\u0011\u0002G \u0011%ai\u0005AI\u0001\n\u00139\t\fC\u0004\rP\u0001!\t\u0001$\u0015\t\u000f1\u0015\u0004\u0001\"\u0001\rh!IAR\u000e\u0001\u0005\u0002\t5Hr\u0004\u0005\n\u0019_\u0002A\u0011\u0001Bw\u0019cBq\u0001$ \u0001\t\u0003ay\bC\u0004\r\u0012\u0002!\t\u0001d%\t\u000f1m\u0005\u0001\"\u0001\r\u001e\"9AR\u0015\u0001\u0005\u00021\u001d\u0006\"\u0003GW\u0001\u0011\u0005!Q\u001eGX\u0011\u001da\u0019\f\u0001C\u0001\u000b\u001fAq\u0001$.\u0001\t\u0003a9\fC\u0004\r>\u0002!\t\u0001d0\t\u00131u\u0006\u0001\"\u0001\u0003n2E\u0007b\u0002Gt\u0001\u0011\u0005A\u0012\u001e\u0005\b\u0019o\u0004A\u0011\u0001G}\u0011\u001di)\u0001\u0001C\u0001\u001b\u000fAq!$\u0005\u0001\t\u0003i\u0019\u0002C\u0005\u000e$\u0001!\tA!<\u000e&!IQr\b\u0001\u0005\u0002\t5X\u0012\t\u0005\n\u001b\u001f\u0002A\u0011\u0001Bw\u001b#Bq!d\u0017\u0001\t\u0003ii\u0006C\u0004\u000e\\\u0001!I!$\u0019\t\u000f5\u001d\u0004\u0001\"\u0001\r !IQ\u0012\u000e\u0001\u0005\u0002\t58Q\u0010\u0005\b\u001bW\u0002A\u0011AB?\u0011\u001diY\u0007\u0001C\u0001\u001b[B\u0011\"d\u001d\u0001\t\u0003\u0011i/$\u001e\t\u000f5]\u0004\u0001\"\u0001\u000ez!IQr\u000f\u0001\u0005\u0002\t5Xr\u0010\u0005\b\u001b\u000b\u0003A\u0011AB?\u0011%i9\t\u0001C\u0001\u0005[lI\tC\u0004\u000e\f\u0002!\t!$$\t\u000f5-\u0005\u0001\"\u0001\u000eH\"9Q2\u0012\u0001\u0005\u00025%\bbBGF\u0001\u0011\u0005a2\u0002\u0005\b\u001b\u0017\u0003A\u0011\u0001H\u0016\u0011\u001diY\t\u0001C\u0001\u001d\u0017Bq!d#\u0001\t\u0003qy\u0007C\u0004\u000f\u0012\u0002!\tAd%\t\u000f9-\u0007\u0001\"\u0001\u000fN\"Ia\u0012 \u0001\u0005\u0002\t5h2 \u0005\b\u001f?\u0001A\u0011AH\u0011\u0011\u001dyy\u0002\u0001C\u0001\u001fSAqa$\f\u0001\t\u0003yy\u0003C\u0004\u0010.\u0001!\ta$\u000e\t\u0013=e\u0002\u0001\"\u0001\u0003n>m\u0002bBH*\u0001\u0011\u0005qR\u000b\u0005\b\u001f'\u0002A\u0011AH.\u0011\u001dyy\u0006\u0001C\u0001\u0007{Bqa$\u0019\u0001\t\u0003y\u0019\u0007C\u0004\u0010b\u0001!\tad\u001b\t\u000f==\u0004\u0001\"\u0001\u0010r!9qr\u000e\u0001\u0005\u0002=e\u0004bBH?\u0001\u0011\u0005qr\u0010\u0005\n\u001f\u0013\u0003\u0011\u0013!C\u0001\u000fcC\u0011bd#\u0001#\u0003%\ta$$\t\u0013=E\u0005\u0001\"\u0001\u0003n>M\u0005bCHS\u0001E\u0005I\u0011\u0001Bw\u001fOCqad+\u0001\t\u0003yi\u000bC\u0004\u00104\u0002!\t\u0001\"\u0006\t\u000f=U\u0006\u0001\"\u0001\u0005(\"9qr\u0017\u0001\u0005\u0002\u0011\u001d\u0006\"CH]\u0001\t\u0007I\u0011BH^\u0011!y\u0019\r\u0001Q\u0001\n=u\u0006\"CHc\u0001\u0011\u0005!Q^Hd\u0011%yI\r\u0001b\u0001\n\u0013yY\f\u0003\u0005\u0010L\u0002\u0001\u000b\u0011BH_\u0011%yi\r\u0001C\u0001\u0005[|9\rC\u0004\u0010P\u0002!Ia! \t\u000f=E\u0007\u0001\"\u0003\u0004~!9q2\u001b\u0001\u0005\n=U\u0007\"CHm\u0001\u0011\u0005!Q^B?\u0011\u001dyY\u000e\u0001C\u0005\u001f;<\u0001b$=\u0003n\"\u0005q2\u001f\u0004\t\u0005W\u0014i\u000f#\u0001\u0010v\"A1q\u0004B>\t\u0003y9\u0010\u0003\u0007\u0010z\nm$\u0019!C\u0001\u0005[|Y\u0010C\u0005\u0011\u0006\tm\u0004\u0015!\u0003\u0010~\"Q\u0001s\u0001B>\u0005\u0004%I\u0001%\u0003\t\u0013AE!1\u0010Q\u0001\nA-\u0001B\u0003I\n\u0005w\u0012\r\u0011\"\u0003\u0011\u0016!I\u0001S\u0004B>A\u0003%\u0001s\u0003\u0005\u000b!?\u0011Y\b1A\u0005\nA\u0005\u0002B\u0003I\u0013\u0005w\u0002\r\u0011\"\u0003\u0011(!I\u00013\u0006B>A\u0003&\u00013\u0005\u0005\t![\u0011Y\b\"\u0003\u00110!A\u0001S\u0007B>\t\u0013\u0019i\b\u0003\u0005\u00118\tmD\u0011\u0001I\u001d\u0011!\u0001:Da\u001f\u0005\u0002\r}\u0004B\u0003I\u001f\u0005w\"\tA!<\u0011\"!Q\u0001s\bB>\t\u0003\u0011i\u000f%\u0011\t\u0015A\u0015#1\u0010C\u0001\u0005[\u0004:\u0005\u0003\u0006\u0011L\tmD\u0011\u0001Bw\u0007{BA\u0002%\u0014\u0003|\t\u0007I\u0011\u0001Bw!\u001fB\u0011\u0002%\u0015\u0003|\u0001\u0006I\u0001%\u0001\t\u0019AM#1\u0010b\u0001\n\u0003\u0011i\u000fe\u0014\t\u0013AU#1\u0010Q\u0001\nA\u0005\u0001\u0002\u0004I,\u0005w\u0012\r\u0011\"\u0001\u0003nB=\u0003\"\u0003I-\u0005w\u0002\u000b\u0011\u0002I\u0001\u00111\u0001ZFa\u001fC\u0002\u0013\u0005!Q\u001eI(\u0011%\u0001jFa\u001f!\u0002\u0013\u0001\n\u0001\u0003\u0007\u0011`\tm$\u0019!C\u0001\u0005[\u0004z\u0005C\u0005\u0011b\tm\u0004\u0015!\u0003\u0011\u0002!a\u00013\rB>\u0005\u0004%\tA!<\u0011P!I\u0001S\rB>A\u0003%\u0001\u0013\u0001\u0005\r!O\u0012YH1A\u0005\u0002\t5\bs\n\u0005\n!S\u0012Y\b)A\u0005!\u0003AA\u0002e\u001b\u0003|\t\u0007I\u0011\u0001Bw!\u001fB\u0011\u0002%\u001c\u0003|\u0001\u0006I\u0001%\u0001\t\u0019A=$1\u0010b\u0001\n\u0003\u0011i\u000fe\u0014\t\u0013AE$1\u0010Q\u0001\nA\u0005\u0001B\u0003I:\u0005w\"\tA!<\u0011v!A\u0001\u0013\u0010B>\t\u0003\u0001Z\b\u0003\u0005\u0011\f\nmD\u0011\u0001IG\u0011)\u0001\u001aJa\u001f\u0005\u0002\t5\bS\u0013\u0005\r!G\u0013Y(%A\u0005\u0002\t5xR\u0012\u0005\r!K\u0013Y(%A\u0005\u0002\t5\bs\u0015\u0005\r!W\u0013Y(%A\u0005\u0002\t5\bS\u0016\u0005\u000b!c\u0013Y\b\"\u0001\u0003nBM\u0006B\u0003IY\u0005w\"\tA!<\u00118\"Q\u0001S\u0018B>\t\u0003\u0011i\u000fe0\t\u0011A\r'1\u0010C\u0005!\u000bD\u0001\u0002%3\u0003|\u0011%\u00013\u001a\u0005\t!'\u0014Y\b\"\u0003\u0011V\"A\u00013\u001dB>\t\u0013\u0001*\u000f\u0003\u0005\u0011j\nmD\u0011\u0002Iv\u0011!\u0001zOa\u001f\u0005\nAE\bB\u0003I{\u0005w\n\n\u0011\"\u0001\u0010\u000e\"Q\u0001s\u001fB>#\u0003%\t\u0001e*\t\u0015Ae(1PI\u0001\n\u0003\u0001jK\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DHO\u0003\u0003\u0003p\nE\u0018!B:qCJ\\'\u0002\u0002Bz\u0005k\fa!\u00199bG\",'B\u0001B|\u0003\ry'oZ\u0002\u0001'\u0015\u0001!Q`B\u0005!\u0011\u0011yp!\u0002\u000e\u0005\r\u0005!BAB\u0002\u0003\u0015\u00198-\u00197b\u0013\u0011\u00199a!\u0001\u0003\r\u0005s\u0017PU3g!\u0011\u0019Ya!\u0005\u000e\u0005\r5!\u0002BB\b\u0005[\f\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\u0007'\u0019iAA\u0004M_\u001e<\u0017N\\4\u0002\r\r|gNZ5h!\u0011\u0019Iba\u0007\u000e\u0005\t5\u0018\u0002BB\u000f\u0005[\u0014\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\u0011\u0019\u0019c!\n\u0011\u0007\re\u0001\u0001C\u0004\u0004\u0016\t\u0001\raa\u0006\u0002\u0019\r\u0014X-\u0019;j_:\u001c\u0016\u000e^3\u0016\u0005\r-\u0002\u0003BB\u0017\u0007gi!aa\f\u000b\t\rE\"Q^\u0001\u0005kRLG.\u0003\u0003\u00046\r=\"\u0001C\"bY2\u001c\u0016\u000e^3\u0002\u001b\r\u0014X-\u0019;j_:\u001c\u0016\u000e^3!\u0003!\u0019Ho\u001c9TSR,WCAB\u001f!\u0019\u0011ypa\u0010\u0004,%!1\u0011IB\u0001\u0005\u0019y\u0005\u000f^5p]\u0006a1\u000f^8q'&$Xm\u0018\u0013fcR!1qIB'!\u0011\u0011yp!\u0013\n\t\r-3\u0011\u0001\u0002\u0005+:LG\u000fC\u0005\u0004P\u0019\t\t\u00111\u0001\u0004>\u0005\u0019\u0001\u0010J\u0019\u0002\u0013M$x\u000e]*ji\u0016\u0004\u0013!C:uCJ$H+[7f+\t\u00199\u0006\u0005\u0003\u0003\u0000\u000ee\u0013\u0002BB.\u0007\u0003\u0011A\u0001T8oO\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\u000fM$x\u000e\u001d9fIV\u001111\r\t\u0005\u0007K\u001a)(\u0004\u0002\u0004h)!1\u0011NB6\u0003\u0019\tGo\\7jG*!1QNB8\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0007c\u0019\tH\u0003\u0002\u0004t\u0005!!.\u0019<b\u0013\u0011\u00199ha\u001a\u0003\u001b\u0005#x.\\5d\u0005>|G.Z1o\u0003!\u0019Ho\u001c9qK\u0012\u0004\u0013\u0001E1tg\u0016\u0014HOT8u'R|\u0007\u000f]3e)\t\u00199\u0005\u0006\u0002\u0004$QA11EBB\u0007;\u001b\t\u000bC\u0004\u0004\u0006:\u0001\raa\"\u0002\r5\f7\u000f^3s!\u0011\u0019Iia&\u000f\t\r-51\u0013\t\u0005\u0007\u001b\u001b\t!\u0004\u0002\u0004\u0010*!1\u0011\u0013B}\u0003\u0019a$o\\8u}%!1QSB\u0001\u0003\u0019\u0001&/\u001a3fM&!1\u0011TBN\u0005\u0019\u0019FO]5oO*!1QSB\u0001\u0011\u001d\u0019yJ\u0004a\u0001\u0007\u000f\u000bq!\u00199q\u001d\u0006lW\rC\u0004\u0004$:\u0001\raa\u0006\u0002\t\r|gN\u001a\u000b\r\u0007G\u00199k!+\u0004,\u000e=6Q\u0019\u0005\b\u0007\u000b{\u0001\u0019ABD\u0011\u001d\u0019yj\u0004a\u0001\u0007\u000fC\u0011b!,\u0010!\u0003\u0005\raa\"\u0002\u0013M\u0004\u0018M]6I_6,\u0007\"CBY\u001fA\u0005\t\u0019ABZ\u0003\u0011Q\u0017M]:\u0011\r\rU6qXBD\u001d\u0011\u00199la/\u000f\t\r55\u0011X\u0005\u0003\u0007\u0007IAa!0\u0004\u0002\u00059\u0001/Y2lC\u001e,\u0017\u0002BBa\u0007\u0007\u00141aU3r\u0015\u0011\u0019il!\u0001\t\u0013\r\u001dw\u0002%AA\u0002\r%\u0017aC3om&\u0014xN\\7f]R\u0004\u0002ba3\u0004R\u000e\u001d5qQ\u0007\u0003\u0007\u001bTAaa4\u0004\u0002\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\rM7Q\u001a\u0002\u0004\u001b\u0006\u0004HCBB\u0012\u0007/\u001cI\u000eC\u0004\u0004\u0006B\u0001\raa\"\t\u000f\r}\u0005\u00031\u0001\u0004\bRA11EBo\u0007?\u001c\t\u000fC\u0004\u0004\u0006F\u0001\raa\"\t\u000f\r}\u0015\u00031\u0001\u0004\b\"91QV\tA\u0002\r\u001dECCB\u0012\u0007K\u001c9o!;\u0004l\"91Q\u0011\nA\u0002\r\u001d\u0005bBBP%\u0001\u00071q\u0011\u0005\b\u0007[\u0013\u0002\u0019ABD\u0011\u001d\u0019\tL\u0005a\u0001\u0007g\u000bQaX2p]\u001a,\"aa\u0006\u0002\u0013}\u001bwN\u001c4`I\u0015\fH\u0003BB$\u0007kD\u0011ba\u0014\u0015\u0003\u0003\u0005\raa\u0006\u0002\r}\u001bwN\u001c4!\u00031yVM^3oi2{w\rR5s+\t\u0019i\u0010\u0005\u0004\u0003\u0000\u000e}2q \t\u0005\t\u0003!9!\u0004\u0002\u0005\u0004)!AQAB9\u0003\rqW\r^\u0005\u0005\t\u0013!\u0019AA\u0002V%&\u000b\u0001cX3wK:$Hj\\4ESJ|F%Z9\u0015\t\r\u001dCq\u0002\u0005\n\u0007\u001f:\u0012\u0011!a\u0001\u0007{\fQbX3wK:$Hj\\4ESJ\u0004\u0013AD0fm\u0016tG\u000fT8h\u0007>$WmY\u000b\u0003\t/\u0001bAa@\u0004@\r\u001d\u0015AE0fm\u0016tG\u000fT8h\u0007>$WmY0%KF$Baa\u0012\u0005\u001e!I1q\n\u000e\u0002\u0002\u0003\u0007AqC\u0001\u0010?\u00164XM\u001c;M_\u001e\u001cu\u000eZ3dA\u0005aq\f\\5ti\u0016tWM\u001d\"vgV\u0011AQ\u0005\t\u0005\tO!i#\u0004\u0002\u0005*)!A1\u0006Bw\u0003%\u00198\r[3ek2,'/\u0003\u0003\u00050\u0011%\"a\u0004'jm\u0016d\u0015n\u001d;f]\u0016\u0014()^:\u0002!}c\u0017n\u001d;f]\u0016\u0014()^:`I\u0015\fH\u0003BB$\tkA\u0011ba\u0014\u001e\u0003\u0003\u0005\r\u0001\"\n\u0002\u001b}c\u0017n\u001d;f]\u0016\u0014()^:!\u0003\u0011yVM\u001c<\u0016\u0005\u0011u\u0002\u0003BB\r\t\u007fIA\u0001\"\u0011\u0003n\nA1\u000b]1sW\u0016sg/\u0001\u0005`K:4x\fJ3r)\u0011\u00199\u0005b\u0012\t\u0013\r=\u0003%!AA\u0002\u0011u\u0012!B0f]Z\u0004\u0013AD0ti\u0006$Xo\u001d+sC\u000e\\WM]\u000b\u0003\t\u001f\u0002Ba!\u0007\u0005R%!A1\u000bBw\u0005I\u0019\u0006/\u0019:l'R\fG/^:Ue\u0006\u001c7.\u001a:\u0002%}\u001bH/\u0019;vgR\u0013\u0018mY6fe~#S-\u001d\u000b\u0005\u0007\u000f\"I\u0006C\u0005\u0004P\r\n\t\u00111\u0001\u0005P\u0005yql\u001d;biV\u001cHK]1dW\u0016\u0014\b%\u0001\u0007`aJ|wM]3tg\n\u000b'/\u0006\u0002\u0005bA1!q`B \tG\u0002B\u0001\"\u001a\u0005l5\u0011Aq\r\u0006\u0005\tS\u0012i/\u0001\u0002vS&!AQ\u000eC4\u0005I\u0019uN\\:pY\u0016\u0004&o\\4sKN\u001c()\u0019:\u0002!}\u0003(o\\4sKN\u001c()\u0019:`I\u0015\fH\u0003BB$\tgB\u0011ba\u0014'\u0003\u0003\u0005\r\u0001\"\u0019\u0002\u001b}\u0003(o\\4sKN\u001c()\u0019:!\u0003\ryV/[\u000b\u0003\tw\u0002bAa@\u0004@\u0011u\u0004\u0003\u0002C3\t\u007fJA\u0001\"!\u0005h\t91\u000b]1sWVK\u0015aB0vS~#S-\u001d\u000b\u0005\u0007\u000f\"9\tC\u0005\u0004P%\n\t\u00111\u0001\u0005|\u0005!q,^5!\u0003Qy\u0006.\u00193p_B\u001cuN\u001c4jOV\u0014\u0018\r^5p]V\u0011Aq\u0012\t\u0005\t##I*\u0004\u0002\u0005\u0014*!11\u0015CK\u0015\u0011!9J!=\u0002\r!\fGm\\8q\u0013\u0011!Y\nb%\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003ay\u0006.\u00193p_B\u001cuN\u001c4jOV\u0014\u0018\r^5p]~#S-\u001d\u000b\u0005\u0007\u000f\"\t\u000bC\u0005\u0004P1\n\t\u00111\u0001\u0005\u0010\u0006)r\f[1e_>\u00048i\u001c8gS\u001e,(/\u0019;j_:\u0004\u0013aD0fq\u0016\u001cW\u000f^8s\u001b\u0016lwN]=\u0016\u0005\u0011%\u0006\u0003\u0002B\u0000\tWKA\u0001\",\u0004\u0002\t\u0019\u0011J\u001c;\u0002'}+\u00070Z2vi>\u0014X*Z7pef|F%Z9\u0015\t\r\u001dC1\u0017\u0005\n\u0007\u001fz\u0013\u0011!a\u0001\tS\u000b\u0001cX3yK\u000e,Ho\u001c:NK6|'/\u001f\u0011\u0002#}\u001b8\r[3ek2,'OQ1dW\u0016tG-\u0006\u0002\u0005<B!Aq\u0005C_\u0013\u0011!y\f\"\u000b\u0003!M\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$\u0017!F0tG\",G-\u001e7fe\n\u000b7m[3oI~#S-\u001d\u000b\u0005\u0007\u000f\")\rC\u0005\u0004PI\n\t\u00111\u0001\u0005<\u0006\u0011rl]2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3!\u00039yF/Y:l'\u000eDW\rZ;mKJ,\"\u0001\"4\u0011\t\u0011\u001dBqZ\u0005\u0005\t#$ICA\u0007UCN\\7k\u00195fIVdWM]\u0001\u0013?R\f7o[*dQ\u0016$W\u000f\\3s?\u0012*\u0017\u000f\u0006\u0003\u0004H\u0011]\u0007\"CB(k\u0005\u0005\t\u0019\u0001Cg\u0003=yF/Y:l'\u000eDW\rZ;mKJ\u0004\u0013AE0iK\u0006\u0014HOY3biJ+7-Z5wKJ,\"\u0001b8\u0011\t\u0011\u0005Hq]\u0007\u0003\tGTA\u0001\":\u0003n\u0006\u0019!\u000f]2\n\t\u0011%H1\u001d\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003Yy\u0006.Z1si\n,\u0017\r\u001e*fG\u0016Lg/\u001a:`I\u0015\fH\u0003BB$\t_D\u0011ba\u00149\u0003\u0003\u0005\r\u0001b8\u0002'}CW-\u0019:uE\u0016\fGOU3dK&4XM\u001d\u0011\u0002\u001b}#\u0017mZ*dQ\u0016$W\u000f\\3s+\t!9\u0010\u0005\u0003\u0005(\u0011e\u0018\u0002\u0002C~\tS\u0011A\u0002R!H'\u000eDW\rZ;mKJ\f\u0011c\u00183bON\u001b\u0007.\u001a3vY\u0016\u0014x\fJ3r)\u0011\u00199%\"\u0001\t\u0013\r=3(!AA\u0002\u0011]\u0018AD0eC\u001e\u001c6\r[3ek2,'\u000f\t\u0015\u0004y\u0015\u001d\u0001\u0003\u0002B\u0000\u000b\u0013IA!b\u0003\u0004\u0002\tAao\u001c7bi&dW-\u0001\b`CB\u0004H.[2bi&|g.\u00133\u0016\u0005\r\u001d\u0015AE0baBd\u0017nY1uS>t\u0017\nZ0%KF$Baa\u0012\u0006\u0016!I1q\n \u0002\u0002\u0003\u00071qQ\u0001\u0010?\u0006\u0004\b\u000f\\5dCRLwN\\%eA\u0005)r,\u00199qY&\u001c\u0017\r^5p]\u0006#H/Z7qi&#\u0017!G0baBd\u0017nY1uS>t\u0017\t\u001e;f[B$\u0018\nZ0%KF$Baa\u0012\u0006 !I1qJ!\u0002\u0002\u0003\u0007AqC\u0001\u0017?\u0006\u0004\b\u000f\\5dCRLwN\\!ui\u0016l\u0007\u000f^%eA\u0005aq,\u001a<f]RdunZ4feV\u0011Qq\u0005\t\u0007\u0005\u007f\u001cy$\"\u000b\u0011\t\u0011\u001dR1F\u0005\u0005\u000b[!IC\u0001\u000bFm\u0016tG\u000fT8hO&tw\rT5ti\u0016tWM]\u0001\u0011?\u00164XM\u001c;M_\u001e<WM]0%KF$Baa\u0012\u00064!I1q\n#\u0002\u0002\u0003\u0007QqE\u0001\u000e?\u00164XM\u001c;M_\u001e<WM\u001d\u0011\u0002\u001b}#'/\u001b<fe2{wmZ3s+\t)Y\u0004\u0005\u0004\u0003\u0000\u000e}RQ\b\t\u0005\u000b\u007f))%\u0004\u0002\u0006B)!Q1IB\u0018\u0003\u001dawnZ4j]\u001eLA!b\u0012\u0006B\taAI]5wKJdunZ4fe\u0006\tr\f\u001a:jm\u0016\u0014Hj\\4hKJ|F%Z9\u0015\t\r\u001dSQ\n\u0005\n\u0007\u001f:\u0015\u0011!a\u0001\u000bw\tab\u00183sSZ,'\u000fT8hO\u0016\u0014\b%\u0001\u000e`Kb,7-\u001e;pe\u0006cGn\\2bi&|g.T1oC\u001e,'/\u0006\u0002\u0006VA1!q`B \u000b/\u0002Ba!\u0007\u0006Z%!Q1\fBw\u0005e)\u00050Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8NC:\fw-\u001a:\u0002=}+\u00070Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8NC:\fw-\u001a:`I\u0015\fH\u0003BB$\u000bCB\u0011ba\u0014K\u0003\u0003\u0005\r!\"\u0016\u00027}+\u00070Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8NC:\fw-\u001a:!\u0003!y6\r\\3b]\u0016\u0014XCAC5!\u0019\u0011ypa\u0010\u0006lA!1\u0011DC7\u0013\u0011)yG!<\u0003\u001d\r{g\u000e^3yi\u000ecW-\u00198fe\u0006aql\u00197fC:,'o\u0018\u0013fcR!1qIC;\u0011%\u0019y%TA\u0001\u0002\u0004)I'A\u0005`G2,\u0017M\\3sA\u0005\u0019r\f\\5ti\u0016tWM\u001d\"vgN#\u0018M\u001d;fIV\u0011QQ\u0010\t\u0005\u0005\u007f,y(\u0003\u0003\u0006\u0002\u000e\u0005!a\u0002\"p_2,\u0017M\\\u0001\u0018?2L7\u000f^3oKJ\u0014Uo]*uCJ$X\rZ0%KF$Baa\u0012\u0006\b\"I1q\n)\u0002\u0002\u0003\u0007QQP\u0001\u0015?2L7\u000f^3oKJ\u0014Uo]*uCJ$X\r\u001a\u0011\u0002\u000b}S\u0017M]:\u0016\u0005\rM\u0016!C0kCJ\u001cx\fJ3r)\u0011\u00199%b%\t\u0013\r=3+!AA\u0002\rM\u0016AB0kCJ\u001c\b%\u0001\u0004`M&dWm]\u0001\u000b?\u001aLG.Z:`I\u0015\fH\u0003BB$\u000b;C\u0011ba\u0014W\u0003\u0003\u0005\raa-\u0002\u000f}3\u0017\u000e\\3tA\u0005Iq,\u0019:dQ&4Xm]\u0001\u000e?\u0006\u00148\r[5wKN|F%Z9\u0015\t\r\u001dSq\u0015\u0005\n\u0007\u001fJ\u0016\u0011!a\u0001\u0007g\u000b!bX1sG\"Lg/Z:!\u0003Ay6\u000f[;uI><h\u000eS8pWJ+g-\u0006\u0002\u0003~\u0006!rl\u001d5vi\u0012|wO\u001c%p_.\u0014VMZ0%KF$Baa\u0012\u00064\"I1q\n/\u0002\u0002\u0003\u0007!Q`\u0001\u0012?NDW\u000f\u001e3po:Dun\\6SK\u001a\u0004\u0013\u0001D0ti\u0006$Xo]*u_J,WCAC^!\u0011)i,b1\u000e\u0005\u0015}&\u0002BCa\u0005[\faa\u001d;biV\u001c\u0018\u0002BCc\u000b\u007f\u0013a\"\u00119q'R\fG/^:Ti>\u0014X-\u0001\t`gR\fG/^:Ti>\u0014Xm\u0018\u0013fcR!1qICf\u0011%\u0019yeXA\u0001\u0002\u0004)Y,A\u0007`gR\fG/^:Ti>\u0014X\rI\u0001\r?\",\u0017M\u001d;cK\u0006$XM]\u000b\u0003\u000b'\u0004Ba!\u0007\u0006V&!Qq\u001bBw\u0005-AU-\u0019:uE\u0016\fG/\u001a:\u0002!}CW-\u0019:uE\u0016\fG/\u001a:`I\u0015\fH\u0003BB$\u000b;D\u0011ba\u0014c\u0003\u0003\u0005\r!b5\u0002\u001b}CW-\u0019:uE\u0016\fG/\u001a:!\u0003)y&/Z:pkJ\u001cWm]\u000b\u0003\u000bK\u0004\u0002\"b:\u0006n\u000e\u001dUq^\u0007\u0003\u000bSTA!b;\u0004N\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0007',I\u000f\u0005\u0003\u0006r\u0016]XBACz\u0015\u0011))P!<\u0002\u0011I,7o\\;sG\u0016LA!\"?\u0006t\n\u0019\"+Z:pkJ\u001cW-\u00138g_Jl\u0017\r^5p]\u0006qqL]3t_V\u00148-Z:`I\u0015\fH\u0003BB$\u000b\u007fD\u0011ba\u0014f\u0003\u0003\u0005\r!\":\u0002\u0017}\u0013Xm]8ve\u000e,7\u000fI\u0001\u0019?NDWO\u001a4mK\u0012\u0013\u0018N^3s\u0007>l\u0007o\u001c8f]R\u001cXC\u0001D\u0004!\u00111IAb\u0005\u000e\u0005\u0019-!\u0002\u0002D\u0007\r\u001f\t1!\u00199j\u0015\u00111\tB!<\u0002\u000fMDWO\u001a4mK&!aQ\u0003D\u0006\u0005]\u0019\u0006.\u001e4gY\u0016$%/\u001b<fe\u000e{W\u000e]8oK:$8/\u0001\u000f`g\",hM\u001a7f\tJLg/\u001a:D_6\u0004xN\\3oiN|F%Z9\u0015\t\r\u001dc1\u0004\u0005\n\u0007\u001fB\u0017\u0011!a\u0001\r\u000f\t\u0011dX:ik\u001a4G.\u001a#sSZ,'oQ8na>tWM\u001c;tA\u0005Aq\f\u001d7vO&t7/\u0006\u0002\u0007$A1!q`B \rK\u0001BAb\n\u0007.5\u0011a\u0011\u0006\u0006\u0005\rW\u0019i!\u0001\u0004qYV<\u0017N\\\u0005\u0005\r_1ICA\bQYV<\u0017N\\\"p]R\f\u0017N\\3s\u00031y\u0006\u000f\\;hS:\u001cx\fJ3r)\u0011\u00199E\"\u000e\t\u0013\r=3.!AA\u0002\u0019\r\u0012!C0qYV<\u0017N\\:!\u0003]y&/Z:pkJ\u001cW\r\u0015:pM&dW-T1oC\u001e,'/\u0006\u0002\u0007>A!Q\u0011\u001fD \u0013\u00111\t%b=\u0003-I+7o\\;sG\u0016\u0004&o\u001c4jY\u0016l\u0015M\\1hKJ\f1d\u0018:fg>,(oY3Qe>4\u0017\u000e\\3NC:\fw-\u001a:`I\u0015\fH\u0003BB$\r\u000fB\u0011ba\u0014o\u0003\u0003\u0005\rA\"\u0010\u00021}\u0013Xm]8ve\u000e,\u0007K]8gS2,W*\u00198bO\u0016\u0014\b%A\bhKR\u0014V-\u00193P]2L8i\u001c8g+\t1y\u0005\u0005\u0003\u0004\u001a\u0019E\u0013\u0002\u0002D*\u0005[\u0014\u0011CU3bI>sG._*qCJ\\7i\u001c8g\u0003\u001d9W\r^\"p]\u001a\f\u0011B]3t_V\u00148-Z:\u0016\u0005\u0019m\u0003\u0003CBf\u0007#\u001c9)b<\u0002\u000b\u0019LG.Z:\u0002\u0011\u0005\u00148\r[5wKN\f!\u0002Z3qY>LXj\u001c3f\u0003EI7/\u0012<f]RdunZ#oC\ndW\rZ\u0001\fKZ,g\u000e\u001e'pO\u0012K'/A\u0007fm\u0016tG\u000fT8h\u0007>$WmY\u0001\bSNdunY1m\u0003%I7o\u0015;paB,G-A\u0006ti\u0006$Xo]*u_J,\u0017a\u00037jgR,g.\u001a:CkN\fab\u0019:fCR,7\u000b]1sW\u0016sg\u000f\u0006\u0005\u0005>\u0019Udq\u000fD=\u0011!\u0019\u0019+a\u0001A\u0002\r]\u0001\u0002\u0003D5\u0003\u0007\u0001\r!\" \t\u0011\u0019=\u00141\u0001a\u0001\tK\t1!\u001a8w\u0003)\tG\rZ3e\r&dWm]\u000b\u0003\r\u0003\u0003\u0002Bb!\u0007\b\u000e\u001de\u0011R\u0007\u0003\r\u000bSAa!\u001c\u0004N&!11\u001bDC!!1\u0019Ib\"\u0004\b\u000e]\u0013aC1eI\u0016$g)\u001b7fg\u0002\nQ\"\u00193eK\u0012\f%o\u00195jm\u0016\u001c\u0018AD1eI\u0016$\u0017I]2iSZ,7\u000fI\u0001\nC\u0012$W\r\u001a&beN\f!\"\u00193eK\u0012T\u0015M]:!\u00035\tG\u000e\\!eI\u0016$g)\u001b7fgV\u0011a\u0011\u0014\t\t\u000bO,ioa\"\u0004X\u0005\u0001\u0012\r\u001c7BI\u0012,G-\u0011:dQ&4Xm]\u0001\rC2d\u0017\t\u001a3fI*\u000b'o]\u0001\u000fa\u0016\u00148/[:uK:$(\u000b\u001a3t+\t1\u0019\u000b\u0005\u0005\u0007\u0004\u001a\u001dE\u0011\u0016DSa\u001119Kb.\u0011\r\u0019%fq\u0016DZ\u001b\t1YK\u0003\u0003\u0007.\n5\u0018a\u0001:eI&!a\u0011\u0017DV\u0005\r\u0011F\t\u0012\t\u0005\rk39\f\u0004\u0001\u0005\u0019\u0019ef1XA\u0001\u0002\u0003\u0015\tA\"3\u0003\u0007}#\u0013\u0007C\u0004\u0007>\u0006m\u0001A\"1\u0002\u00075\f\u0007/A\bqKJ\u001c\u0018n\u001d;f]R\u0014F\rZ:!!!1\u0019M\"2\u0005*\u001a\u0015VBAB6\u0013\u001119ma\u001b\u0003\u001b\r{gnY;se\u0016tG/T1q#\u00111YM\"5\u0011\t\t}hQZ\u0005\u0005\r\u001f\u001c\tAA\u0004O_RD\u0017N\\4\u0011\t\t}h1[\u0005\u0005\r+\u001c\tAA\u0002B]f\fQb\u001d;biV\u001cHK]1dW\u0016\u0014\u0018a\u00039s_\u001e\u0014Xm]:CCJ\f\u0001\"^5XK\n,&\u000f\\\u0001\u0014Q\u0006$wn\u001c9D_:4\u0017nZ;sCRLwN\\\u0001\u000fKb,7-\u001e;pe6+Wn\u001c:z\u00031)\u00070Z2vi>\u0014XI\u001c<t+\t1)\u000f\u0005\u0005\u0007h\u001a58qQBD\u001b\t1IO\u0003\u0003\u0007l\u000e5\u0017aB7vi\u0006\u0014G.Z\u0005\u0005\r_4IOA\u0004ICNDW*\u00199\u0002\u001b\u0015DXmY;u_J,eN^:!\u0003%\u0019\b/\u0019:l+N,'/\u0001\u0006ta\u0006\u00148.V:fe\u0002\n\u0001c]2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3\u0002\u001bQ\f7o[*dQ\u0016$W\u000f\\3s\u0003E!\u0018m]6TG\",G-\u001e7fe~#S-\u001d\u000b\u0005\u0007\u000f2y\u0010\u0003\u0005\b\u0002\u0005U\u0002\u0019\u0001Cg\u0003\t!8/\u0001\u0007eC\u001e\u001c6\r[3ek2,'/\u0001\teC\u001e\u001c6\r[3ek2,'o\u0018\u0013fcR!1qID\u0005\u0011!9Y!!\u000fA\u0002\u0011]\u0018A\u00013t\u0003]\u0019\b.\u001e4gY\u0016$%/\u001b<fe\u000e{W\u000e]8oK:$8/A\u0007baBd\u0017nY1uS>t\u0017\nZ\u0001\u0015CB\u0004H.[2bi&|g.\u0011;uK6\u0004H/\u00133\u0002\u0017\u00154XM\u001c;M_\u001e<WM]\u0001\u001aKb,7-\u001e;pe\u0006cGn\\2bi&|g.T1oC\u001e,'/\u0001\fsKN|WO]2f!J|g-\u001b7f\u001b\u0006t\u0017mZ3s\u0003\u001d\u0019G.Z1oKJ\fQb\u00195fG.\u0004x.\u001b8u\t&\u0014\u0018!E2iK\u000e\\\u0007o\\5oi\u0012K'o\u0018\u0013fcR!1qID\u0011\u0011)\u0019y%a\u0013\u0002\u0002\u0003\u0007AqC\u0001\u000fG\",7m\u001b9pS:$H)\u001b:!\u0003=awnY1m!J|\u0007/\u001a:uS\u0016\u001cXCAD\u0015!\u00199Yc\"\r\b65\u0011qQ\u0006\u0006\u0005\u000f_\u0019\t(\u0001\u0003mC:<\u0017\u0002BD\u001a\u000f[\u0011a#\u00138iKJLG/\u00192mKRC'/Z1e\u0019>\u001c\u0017\r\u001c\t\u0005\u000fo9I$\u0004\u0002\u0004p%!q1HB8\u0005)\u0001&o\u001c9feRLWm]\u0001\u0011Y>\u001c\u0017\r\u001c)s_B,'\u000f^5fg\u0002\n1b]3u\u0019><G*\u001a<fYR!1qID\"\u0011!9)%a\u0015A\u0002\r\u001d\u0015\u0001\u00037pO2+g/\u001a7\u0002+\u001d,G/\u0012=fGV$xN\u001d+ie\u0016\fG\rR;naR!q1JD1!\u0019\u0011ypa\u0010\bNA1!q`D(\u000f'JAa\"\u0015\u0004\u0002\t)\u0011I\u001d:bsB!qQKD/\u001b\t99F\u0003\u0003\bZ\u001dm\u0013A\u0001<2\u0015\u00111i!b0\n\t\u001d}sq\u000b\u0002\u0011)\"\u0014X-\u00193Ti\u0006\u001c7\u000e\u0016:bG\u0016D\u0001bb\u0019\u0002V\u0001\u00071qQ\u0001\u000bKb,7-\u001e;pe&#\u0017!E4fiR\u000b7o\u001b+ie\u0016\fG\rR;naR1q\u0011ND6\u000f_\u0002bAa@\u0004@\u001dM\u0003\u0002CD7\u0003/\u0002\raa\u0016\u0002\rQ\f7o[%e\u0011!9\u0019'a\u0016A\u0002\r\u001d\u0015\u0001G4fi\u0016CXmY;u_JDU-\u00199ISN$xn\u001a:b[R!qQOD=!\u0019\u0011ypa\u0010\bxA1!q`D(\u0007\u000fC\u0001bb\u0019\u0002Z\u0001\u00071qQ\u0001\u0013O\u0016$Hj\\2bYB\u0013x\u000e]3si&,7/\u0006\u0002\b6\u0005\u00112/\u001a;M_\u000e\fG\u000e\u0015:pa\u0016\u0014H/[3t)\u0011\u00199eb!\t\u0011\u001d\u0015\u0015Q\fa\u0001\u000fk\tQ\u0001\u001d:paN\f\u0001c]3u\u0019>\u001c\u0017\r\u001c)s_B,'\u000f^=\u0015\r\r\u001ds1RDH\u0011!9i)a\u0018A\u0002\r\u001d\u0015aA6fs\"Aq\u0011SA0\u0001\u0004\u00199)A\u0003wC2,X-\u0001\thKRdunY1m!J|\u0007/\u001a:usR!1qQDL\u0011!9i)!\u0019A\u0002\r\u001d\u0015!E:fi*{'\rR3tGJL\u0007\u000f^5p]R!1qIDO\u0011!9\t*a\u0019A\u0002\r\u001d\u0015aC:fi*{'m\u0012:pkB$\u0002ba\u0012\b$\u001e\u001dv1\u0016\u0005\t\u000fK\u000b)\u00071\u0001\u0004\b\u00069qM]8va&#\u0007\u0002CDU\u0003K\u0002\raa\"\u0002\u0017\u0011,7o\u0019:jaRLwN\u001c\u0005\u000b\u000f[\u000b)\u0007%AA\u0002\u0015u\u0014!E5oi\u0016\u0014(/\u001e9u\u001f:\u001c\u0015M\\2fY\u0006)2/\u001a;K_\n<%o\\;qI\u0011,g-Y;mi\u0012\u001aTCADZU\u0011)ih\".,\u0005\u001d]\u0006\u0003BD]\u000f\u0007l!ab/\u000b\t\u001duvqX\u0001\nk:\u001c\u0007.Z2lK\u0012TAa\"1\u0004\u0002\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u001d\u0015w1\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!D2mK\u0006\u0014(j\u001c2He>,\b/\u0001\u000btKRLe\u000e^3seV\u0004Ho\u00148DC:\u001cW\r\u001c\u000b\u0005\u0007\u000f:i\r\u0003\u0005\b.\u0006-\u0004\u0019AC?\u0003%\tG\r\u001a&pER\u000bw\r\u0006\u0003\u0004H\u001dM\u0007\u0002CDk\u0003[\u0002\raa\"\u0002\u0007Q\fw-\u0001\u0006bI\u0012TuN\u0019+bON$Baa\u0012\b\\\"AqQ\\A8\u0001\u00049y.\u0001\u0003uC\u001e\u001c\bCBBE\u000fC\u001c9)\u0003\u0003\bd\u000em%aA*fi\u0006a!/Z7pm\u0016TuN\u0019+bOR!1qIDu\u0011!9).!\u001dA\u0002\r\u001d\u0015!\u0004:f[>4XMS8c)\u0006<7\u000f\u0006\u0003\u0004H\u001d=\b\u0002CDo\u0003g\u0002\rab8\u0002\u0015\u001d,GOS8c)\u0006<7\u000f\u0006\u0002\b`\u0006a1\r\\3be*{'\rV1hg\u0006Iq/\u001b;i'\u000e|\u0007/Z\u000b\u0005\u000fw<y\u0010\u0006\u0003\b~\"\r\u0001\u0003\u0002D[\u000f\u007f$\u0001\u0002#\u0001\u0002z\t\u0007a\u0011\u001a\u0002\u0002+\"I\u0001RAA=\t\u0003\u0007\u0001rA\u0001\u0005E>$\u0017\u0010\u0005\u0004\u0003\u0000\"%qQ`\u0005\u0005\u0011\u0017\u0019\tA\u0001\u0005=Eft\u0017-\\3?\u0003-\u0001\u0018M]1mY\u0016d\u0017N_3\u0016\t!E\u0001\u0012\u0004\u000b\u0007\u0011'Ai\u0003c\r\u0015\t!U\u0001R\u0004\t\u0007\rS3y\u000bc\u0006\u0011\t\u0019U\u0006\u0012\u0004\u0003\t\u00117\tYH1\u0001\u0007J\n\tA\u000b\u0003\u0006\t \u0005m\u0014\u0011!a\u0002\u0011C\t!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019A\u0019\u0003#\u000b\t\u00185\u0011\u0001R\u0005\u0006\u0005\u0011O\u0019\t!A\u0004sK\u001adWm\u0019;\n\t!-\u0002R\u0005\u0002\t\u00072\f7o\u001d+bO\"A\u0001rFA>\u0001\u0004A\t$A\u0002tKF\u0004ba!.\u0004@\"]\u0001B\u0003E\u001b\u0003w\u0002\n\u00111\u0001\u0005*\u0006Ia.^7TY&\u001cWm]\u0001\u0016a\u0006\u0014\u0018\r\u001c7fY&TX\r\n3fM\u0006,H\u000e\u001e\u00133+\u0011AY\u0004c\u0010\u0016\u0005!u\"\u0006\u0002CU\u000fk#\u0001\u0002c\u0007\u0002~\t\u0007a\u0011Z\u0001\u0006e\u0006tw-\u001a\u000b\u000b\u0011\u000bB9\u0005c\u0013\tP!M\u0003C\u0002DU\r_\u001b9\u0006\u0003\u0005\tJ\u0005}\u0004\u0019AB,\u0003\u0015\u0019H/\u0019:u\u0011!Ai%a A\u0002\r]\u0013aA3oI\"Q\u0001\u0012KA@!\u0003\u0005\raa\u0016\u0002\tM$X\r\u001d\u0005\u000b\u0011k\ty\b%AA\u0002\u0011%\u0016a\u0004:b]\u001e,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005!e#\u0006BB,\u000fk\u000bqB]1oO\u0016$C-\u001a4bk2$H\u0005N\u0001\b[\u0006\\WM\u0015#E+\u0011A\t\u0007#\u001b\u0015\r!\r\u0004\u0012\u000fE;)\u0011A)\u0007c\u001b\u0011\r\u0019%fq\u0016E4!\u00111)\f#\u001b\u0005\u0011!m\u0011Q\u0011b\u0001\r\u0013D!\u0002#\u001c\u0002\u0006\u0006\u0005\t9\u0001E8\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0011GAI\u0003c\u001a\t\u0011!=\u0012Q\u0011a\u0001\u0011g\u0002ba!.\u0004@\"\u001d\u0004B\u0003E\u001b\u0003\u000b\u0003\n\u00111\u0001\u0005*\u0006\tR.Y6f%\u0012#E\u0005Z3gCVdG\u000f\n\u001a\u0016\t!m\u00022\u0010\u0003\t\u00117\t9I1\u0001\u0007JV!\u0001r\u0010ED)\u0011A\t\tc$\u0015\t!\r\u0005\u0012\u0012\t\u0007\rS3y\u000b#\"\u0011\t\u0019U\u0006r\u0011\u0003\t\u00117\tII1\u0001\u0007J\"Q\u00012RAE\u0003\u0003\u0005\u001d\u0001#$\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\t$!%\u0002R\u0011\u0005\t\u0011_\tI\t1\u0001\t\u0012B11QWB`\u0011'\u0003\u0002Ba@\t\u0016\"\u001551W\u0005\u0005\u0011/\u001b\tA\u0001\u0004UkBdWMM\u0001\ti\u0016DHOR5mKR1\u0001R\u0014EP\u0011G\u0003bA\"+\u00070\u000e\u001d\u0005\u0002\u0003EQ\u0003\u0017\u0003\raa\"\u0002\tA\fG\u000f\u001b\u0005\u000b\u0011K\u000bY\t%AA\u0002\u0011%\u0016!D7j]B\u000b'\u000f^5uS>t7/\u0001\nuKb$h)\u001b7fI\u0011,g-Y;mi\u0012\u0012\u0014AD<i_2,G+\u001a=u\r&dWm\u001d\u000b\u0007\u0011[C\t\fc-\u0011\r\u0019%fq\u0016EX!!\u0011y\u0010#&\u0004\b\u000e\u001d\u0005\u0002\u0003EQ\u0003\u001f\u0003\raa\"\t\u0015!\u0015\u0016q\u0012I\u0001\u0002\u0004!I+\u0001\rxQ>dW\rV3yi\u001aKG.Z:%I\u00164\u0017-\u001e7uII\n1BY5oCJLh)\u001b7fgR1\u00012\u0018Ef\u0011\u001b\u0004bA\"+\u00070\"u\u0006\u0003\u0003B\u0000\u0011+\u001b9\tc0\u0011\t!\u0005\u0007rY\u0007\u0003\u0011\u0007TA\u0001#2\u0003n\u0006)\u0011N\u001c9vi&!\u0001\u0012\u001aEb\u0005I\u0001vN\u001d;bE2,G)\u0019;b'R\u0014X-Y7\t\u0011!\u0005\u00161\u0013a\u0001\u0007\u000fC!\u0002#*\u0002\u0014B\u0005\t\u0019\u0001CU\u0003U\u0011\u0017N\\1ss\u001aKG.Z:%I\u00164\u0017-\u001e7uII\nQBY5oCJL(+Z2pe\u0012\u001cH\u0003\u0003Ek\u0011?D\t\u000f#:\u0011\r\u0019%fq\u0016El!\u0019\u0011ypb\u0014\tZB!!q En\u0013\u0011Ain!\u0001\u0003\t\tKH/\u001a\u0005\t\u0011C\u000b9\n1\u0001\u0004\b\"A\u00012]AL\u0001\u0004!I+\u0001\u0007sK\u000e|'\u000f\u001a'f]\u001e$\b\u000e\u0003\u0006\u0004$\u0006]\u0005\u0013!a\u0001\t\u001f\u000bqCY5oCJL(+Z2pe\u0012\u001cH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005!-(\u0006\u0002CH\u000fk\u000b\u0011\u0002[1e_>\u0004(\u000b\u0012#\u0016\r!E\b\u0012 E\u0000)1A\u00190c\u0001\n\u0012%-\u0012\u0012GE\u001c!\u00191IKb,\tvBA!q EK\u0011oDi\u0010\u0005\u0003\u00076\"eH\u0001\u0003E~\u00037\u0013\rA\"3\u0003\u0003-\u0003BA\".\t\u0000\u0012A\u0011\u0012AAN\u0005\u00041IMA\u0001W\u0011!\u0019\u0019+a'A\u0002%\u0015\u0001\u0003BE\u0004\u0013\u001bi!!#\u0003\u000b\t%-AQS\u0001\u0007[\u0006\u0004(/\u001a3\n\t%=\u0011\u0012\u0002\u0002\b\u0015>\u00147i\u001c8g\u0011!I\u0019\"a'A\u0002%U\u0011\u0001E5oaV$hi\u001c:nCR\u001cE.Y:ta\u0011I9\"c\b\u0011\r\r%\u0015\u0012DE\u000f\u0013\u0011IYba'\u0003\u000b\rc\u0017m]:\u0011\t\u0019U\u0016r\u0004\u0003\r\u0013CI\t\"!A\u0001\u0002\u000b\u0005\u00112\u0005\u0002\u0004?\u0012\u001a\u0014\u0003\u0002Df\u0013K\u0001\u0002\"c\u0002\n(!]\bR`\u0005\u0005\u0013SIIAA\u0006J]B,HOR8s[\u0006$\b\u0002CE\u0017\u00037\u0003\r!c\f\u0002\u0011-,\u0017p\u00117bgN\u0004ba!#\n\u001a!]\b\u0002CE\u001a\u00037\u0003\r!#\u000e\u0002\u0015Y\fG.^3DY\u0006\u001c8\u000f\u0005\u0004\u0004\n&e\u0001R \u0005\u000b\u0011K\u000bY\n%AA\u0002\u0011%\u0016a\u00055bI>|\u0007O\u0015#EI\u0011,g-Y;mi\u0012*TC\u0002E\u001e\u0013{Iy\u0004\u0002\u0005\t|\u0006u%\u0019\u0001De\t!I\t!!(C\u0002\u0019%\u0017A\u00035bI>|\u0007OR5mKV1\u0011RIE'\u0013#\"B\"c\u0012\nT%U\u0013RME5\u0013[\u0002bA\"+\u00070&%\u0003\u0003\u0003B\u0000\u0011+KY%c\u0014\u0011\t\u0019U\u0016R\n\u0003\t\u0011w\fyJ1\u0001\u0007JB!aQWE)\t!I\t!a(C\u0002\u0019%\u0007\u0002\u0003EQ\u0003?\u0003\raa\"\t\u0011%M\u0011q\u0014a\u0001\u0013/\u0002D!#\u0017\n^A11\u0011RE\r\u00137\u0002BA\".\n^\u0011a\u0011rLE+\u0003\u0003\u0005\tQ!\u0001\nb\t\u0019q\f\n\u001b\u0012\t\u0019-\u00172\r\t\t\u0013\u000fI9#c\u0013\nP!A\u0011RFAP\u0001\u0004I9\u0007\u0005\u0004\u0004\n&e\u00112\n\u0005\t\u0013g\ty\n1\u0001\nlA11\u0011RE\r\u0013\u001fB!\u0002#*\u0002 B\u0005\t\u0019\u0001CU\u0003QA\u0017\rZ8pa\u001aKG.\u001a\u0013eK\u001a\fW\u000f\u001c;%kU1\u00012HE:\u0013k\"\u0001\u0002c?\u0002\"\n\u0007a\u0011\u001a\u0003\t\u0013\u0003\t\tK1\u0001\u0007JVA\u0011\u0012PEB\u0013\u000fKi\n\u0006\u0004\n|%\u0015\u0016r\u0015\u000b\t\u0013{JI)c$\n\u0016B1a\u0011\u0016DX\u0013\u007f\u0002\u0002Ba@\t\u0016&\u0005\u0015R\u0011\t\u0005\rkK\u0019\t\u0002\u0005\t|\u0006\r&\u0019\u0001De!\u00111),c\"\u0005\u0011%\u0005\u00111\u0015b\u0001\r\u0013D\u0001\"c#\u0002$\u0002\u000f\u0011RR\u0001\u0003W6\u0004b\u0001c\t\t*%\u0005\u0005\u0002CEI\u0003G\u0003\u001d!c%\u0002\u0005Yl\u0007C\u0002E\u0012\u0011SI)\t\u0003\u0005\n\u0018\u0006\r\u00069AEM\u0003\t1W\u000e\u0005\u0004\t$!%\u00122\u0014\t\u0005\rkKi\n\u0002\u0005\n \u0006\r&\u0019AEQ\u0005\u00051\u0015\u0003\u0002Df\u0013G\u0003\u0002\"c\u0002\n(%\u0005\u0015R\u0011\u0005\t\u0011C\u000b\u0019\u000b1\u0001\u0004\b\"A\u0001RUAR\u0001\u0004!I+\u0006\u0005\n,&U\u0016\u0012XEe)\u0011Ii+c4\u0015\u0011%=\u00162XE`\u0013\u0007\u0004bA\"+\u00070&E\u0006\u0003\u0003B\u0000\u0011+K\u0019,c.\u0011\t\u0019U\u0016R\u0017\u0003\t\u0011w\f)K1\u0001\u0007JB!aQWE]\t!I\t!!*C\u0002\u0019%\u0007\u0002CEF\u0003K\u0003\u001d!#0\u0011\r!\r\u0002\u0012FEZ\u0011!I\t*!*A\u0004%\u0005\u0007C\u0002E\u0012\u0011SI9\f\u0003\u0005\n\u0018\u0006\u0015\u00069AEc!\u0019A\u0019\u0003#\u000b\nHB!aQWEe\t!Iy*!*C\u0002%-\u0017\u0003\u0002Df\u0013\u001b\u0004\u0002\"c\u0002\n(%M\u0016r\u0017\u0005\t\u0011C\u000b)\u000b1\u0001\u0004\b\u0006\u0001b.Z<B!&C\u0015\rZ8pa\u001aKG.Z\u000b\t\u0013+Ly.c9\ntR!\u0011r\u001bF\u0001)!II.#:\nj&5\bC\u0002DU\r_KY\u000e\u0005\u0005\u0003\u0000\"U\u0015R\\Eq!\u00111),c8\u0005\u0011!m\u0018q\u0015b\u0001\r\u0013\u0004BA\".\nd\u0012A\u0011\u0012AAT\u0005\u00041I\r\u0003\u0005\n\f\u0006\u001d\u00069AEt!\u0019A\u0019\u0003#\u000b\n^\"A\u0011\u0012SAT\u0001\bIY\u000f\u0005\u0004\t$!%\u0012\u0012\u001d\u0005\t\u0013/\u000b9\u000bq\u0001\npB1\u00012\u0005E\u0015\u0013c\u0004BA\".\nt\u0012A\u0011rTAT\u0005\u0004I)0\u0005\u0003\u0007L&]\b\u0003CE}\u0013\u007fLi.#9\u000e\u0005%m(\u0002BE\u007f\t+\u000b\u0011\"\\1qe\u0016$WoY3\n\t%%\u00122 \u0005\t\u0011C\u000b9\u000b1\u0001\u0004\bVA!R\u0001F\u0007\u0015#Qi\u0002\u0006\u0007\u000b\b)M!R\u0003F\u0012\u0015SQy\u0003\u0005\u0004\u0007*\u001a=&\u0012\u0002\t\t\u0005\u007fD)Jc\u0003\u000b\u0010A!aQ\u0017F\u0007\t!AY0!+C\u0002\u0019%\u0007\u0003\u0002D[\u0015#!\u0001\"#\u0001\u0002*\n\u0007a\u0011\u001a\u0005\t\u0011C\u000bI\u000b1\u0001\u0004\b\"A!rCAU\u0001\u0004QI\"\u0001\u0004g\u00072\f7o\u001d\t\u0007\u0007\u0013KIBc\u0007\u0011\t\u0019U&R\u0004\u0003\t\u0013?\u000bIK1\u0001\u000b E!a1\u001aF\u0011!!II0c@\u000b\f)=\u0001\u0002\u0003F\u0013\u0003S\u0003\rAc\n\u0002\r-\u001cE.Y:t!\u0019\u0019I)#\u0007\u000b\f!A!2FAU\u0001\u0004Qi#\u0001\u0004w\u00072\f7o\u001d\t\u0007\u0007\u0013KIBc\u0004\t\u0015\r\r\u0016\u0011\u0016I\u0001\u0002\u0004!y)\u0001\u000eoK^\f\u0005+\u0013%bI>|\u0007OR5mK\u0012\"WMZ1vYR$S'\u0006\u0005\tj*U\"r\u0007F\u001d\t!AY0a+C\u0002\u0019%G\u0001CE\u0001\u0003W\u0013\rA\"3\u0005\u0011%}\u00151\u0016b\u0001\u0015w\tBAb3\u000b>AA\u0011\u0012`E\u0000\u0015\u007fQ\t\u0005\u0005\u0003\u00076*U\u0002\u0003\u0002D[\u0015o\tqB\\3x\u0003BK\u0005*\u00193p_B\u0014F\tR\u000b\t\u0015\u000fRyEc\u0015\u000b^QQ!\u0012\nF+\u0015/R\u0019Gc\u001a\u0011\r\u0019%fq\u0016F&!!\u0011y\u0010#&\u000bN)E\u0003\u0003\u0002D[\u0015\u001f\"\u0001\u0002c?\u0002.\n\u0007a\u0011\u001a\t\u0005\rkS\u0019\u0006\u0002\u0005\n\u0002\u00055&\u0019\u0001De\u0011)\u0019\u0019+!,\u0011\u0002\u0003\u0007Aq\u0012\u0005\t\u0015/\ti\u000b1\u0001\u000bZA11\u0011RE\r\u00157\u0002BA\".\u000b^\u0011A\u0011rTAW\u0005\u0004Qy&\u0005\u0003\u0007L*\u0005\u0004\u0003CE}\u0013\u007fTiE#\u0015\t\u0011)\u0015\u0012Q\u0016a\u0001\u0015K\u0002ba!#\n\u001a)5\u0003\u0002\u0003F\u0016\u0003[\u0003\rA#\u001b\u0011\r\r%\u0015\u0012\u0004F)\u0003eqWm^!Q\u0013\"\u000bGm\\8q%\u0012#E\u0005Z3gCVdG\u000fJ\u0019\u0016\u0011!%(r\u000eF9\u0015g\"\u0001\u0002c?\u00020\n\u0007a\u0011\u001a\u0003\t\u0013\u0003\tyK1\u0001\u0007J\u0012A\u0011rTAX\u0005\u0004Q)(\u0005\u0003\u0007L*]\u0004\u0003CE}\u0013\u007fTIHc\u001f\u0011\t\u0019U&r\u000e\t\u0005\rkS\t(\u0001\u0007tKF,XM\\2f\r&dW-\u0006\u0004\u000b\u0002*%%R\u0012\u000b\u000b\u0015\u0007SyI#%\u000b\u0016*e\u0005C\u0002DU\r_S)\t\u0005\u0005\u0003\u0000\"U%r\u0011FF!\u00111)L##\u0005\u0011!m\u0018\u0011\u0017b\u0001\r\u0013\u0004BA\".\u000b\u000e\u0012A\u0011\u0012AAY\u0005\u00041I\r\u0003\u0005\t\"\u0006E\u0006\u0019ABD\u0011!Ii#!-A\u0002)M\u0005CBBE\u00133Q9\t\u0003\u0005\n4\u0005E\u0006\u0019\u0001FL!\u0019\u0019I)#\u0007\u000b\f\"A\u0001RUAY\u0001\u0004!I+\u0006\u0004\u000b\u001e*\u0015&\u0012\u0016\u000b\t\u0015?SYK#,\u000b2B1a\u0011\u0016DX\u0015C\u0003\u0002Ba@\t\u0016*\r&r\u0015\t\u0005\rkS)\u000b\u0002\u0005\t|\u0006M&\u0019\u0001De!\u00111)L#+\u0005\u0011%\u0005\u00111\u0017b\u0001\r\u0013D\u0001\u0002#)\u00024\u0002\u00071q\u0011\u0005\t\u0013[\t\u0019\f1\u0001\u000b0B11\u0011RE\r\u0015GC\u0001\"c\r\u00024\u0002\u0007!2\u0017\t\u0007\u0007\u0013KIBc*\u0016\r)]&\u0012\u0019Fc)\u0019QILc:\u000bjRQ!2\u0018Fd\u0015\u0017TyMc8\u0011\r\u0019%fq\u0016F_!!\u0011y\u0010#&\u000b@*\r\u0007\u0003\u0002D[\u0015\u0003$\u0001\u0002c?\u00026\n\u0007a\u0011\u001a\t\u0005\rkS)\r\u0002\u0005\n\u0002\u0005U&\u0019\u0001De\u0011!IY)!.A\u0004)%\u0007C\u0002E\u0012\u0011SQy\f\u0003\u0005\n\u0012\u0006U\u00069\u0001Fg!\u0019A\u0019\u0003#\u000b\u000bD\"A!\u0012[A[\u0001\bQ\u0019.A\u0002lG\u001a\u0004bAa@\u000bV*e\u0017\u0002\u0002Fl\u0007\u0003\u0011\u0011BR;oGRLwN\u001c\u0019\u0011\r\re!2\u001cF`\u0013\u0011QiN!<\u0003#]\u0013\u0018\u000e^1cY\u0016\u001cuN\u001c<feR,'\u000f\u0003\u0005\u000bb\u0006U\u00069\u0001Fr\u0003\r18M\u001a\t\u0007\u0005\u007fT)N#:\u0011\r\re!2\u001cFb\u0011!A\t+!.A\u0002\r\u001d\u0005B\u0003ES\u0003k\u0003\n\u00111\u0001\u0005*\u000612/Z9vK:\u001cWMR5mK\u0012\"WMZ1vYR$#'\u0006\u0004\t<)=(\u0012\u001f\u0003\t\u0011w\f9L1\u0001\u0007J\u0012A\u0011\u0012AA\\\u0005\u00041I-\u0001\u0006pE*,7\r\u001e$jY\u0016,BAc>\u000b\u0000R1!\u0012`F\u0004\u0017\u0013!BAc?\f\u0002A1a\u0011\u0016DX\u0015{\u0004BA\".\u000b\u0000\u0012A\u00012DA]\u0005\u00041I\r\u0003\u0006\f\u0004\u0005e\u0016\u0011!a\u0002\u0017\u000b\t!\"\u001a<jI\u0016t7-\u001a\u00135!\u0019A\u0019\u0003#\u000b\u000b~\"A\u0001\u0012UA]\u0001\u0004\u00199\t\u0003\u0006\t&\u0006e\u0006\u0013!a\u0001\tS\u000bAc\u001c2kK\u000e$h)\u001b7fI\u0011,g-Y;mi\u0012\u0012T\u0003\u0002E\u001e\u0017\u001f!\u0001\u0002c\u0007\u0002<\n\u0007a\u0011Z\u0001\u000fG\",7m\u001b9pS:$h)\u001b7f+\u0011Y)b#\b\u0015\t-]1R\u0005\u000b\u0005\u00173Yy\u0002\u0005\u0004\u0007*\u001a=62\u0004\t\u0005\rk[i\u0002\u0002\u0005\t\u001c\u0005u&\u0019\u0001De\u0011)Y\t#!0\u0002\u0002\u0003\u000f12E\u0001\u000bKZLG-\u001a8dK\u0012*\u0004C\u0002E\u0012\u0011SYY\u0002\u0003\u0005\t\"\u0006u\u0006\u0019ABD\u0003\u0015)h.[8o+\u0011YYcc\r\u0015\t-522\b\u000b\u0005\u0017_Y)\u0004\u0005\u0004\u0007*\u001a=6\u0012\u0007\t\u0005\rk[\u0019\u0004\u0002\u0005\t\u001c\u0005}&\u0019\u0001De\u0011)Y9$a0\u0002\u0002\u0003\u000f1\u0012H\u0001\u000bKZLG-\u001a8dK\u00122\u0004C\u0002E\u0012\u0011SY\t\u0004\u0003\u0005\f>\u0005}\u0006\u0019AF \u0003\u0011\u0011H\rZ:\u0011\r\rU6qXF\u0018+\u0011Y\u0019ec\u0013\u0015\r-\u001532KF,)\u0011Y9e#\u0014\u0011\r\u0019%fqVF%!\u00111)lc\u0013\u0005\u0011!m\u0011\u0011\u0019b\u0001\r\u0013D!bc\u0014\u0002B\u0006\u0005\t9AF)\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0007\u0011GAIc#\u0013\t\u0011-U\u0013\u0011\u0019a\u0001\u0017\u000f\nQAZ5sgRD\u0001b#\u0017\u0002B\u0002\u000712L\u0001\u0005e\u0016\u001cH\u000f\u0005\u0004\u0003\u0000.u3rI\u0005\u0005\u0017?\u001a\tA\u0001\u0006=e\u0016\u0004X-\u0019;fIz\n\u0001\"Z7qif\u0014F\tR\u000b\u0005\u0017KZY\u0007\u0006\u0003\fh-5\u0004C\u0002DU\r_[I\u0007\u0005\u0003\u00076.-D\u0001\u0003E\u000e\u0003\u0007\u0014\rA\"3\t\u0015-=\u00141YA\u0001\u0002\bY\t(\u0001\u0006fm&$WM\\2fIa\u0002b\u0001c\t\t*-%\u0014\u0001\u0003:fO&\u001cH/\u001a:\u0015\t\r\u001d3r\u000f\u0005\t\u0017s\n)\r1\u0001\f|\u0005\u0019\u0011mY21\r-u4RQFF!!\u0019icc \f\u0004.%\u0015\u0002BFA\u0007_\u0011Q\"Q2dk6,H.\u0019;peZ\u0013\u0004\u0003\u0002D[\u0017\u000b#Abc\"\fx\u0005\u0005\t\u0011!B\u0001\r\u0013\u00141a\u0018\u00136!\u00111)lc#\u0005\u0019-55rOA\u0001\u0002\u0003\u0015\tA\"3\u0003\u0007}#c\u0007\u0006\u0004\u0004H-E52\u0015\u0005\t\u0017s\n9\r1\u0001\f\u0014B21RSFM\u0017?\u0003\u0002b!\f\f\u0000-]5R\u0014\t\u0005\rk[I\n\u0002\u0007\f\u001c.E\u0015\u0011!A\u0001\u0006\u00031IMA\u0002`I]\u0002BA\".\f \u0012a1\u0012UFI\u0003\u0003\u0005\tQ!\u0001\u0007J\n\u0019q\f\n\u001d\t\u0011-\u0015\u0016q\u0019a\u0001\u0007\u000f\u000bAA\\1nK\u0006yAn\u001c8h\u0003\u000e\u001cW/\\;mCR|'/\u0006\u0002\f,B!1QFFW\u0013\u0011Yyka\f\u0003\u001f1{gnZ!dGVlW\u000f\\1u_J$Bac+\f4\"A1RUAf\u0001\u0004\u00199)A\te_V\u0014G.Z!dGVlW\u000f\\1u_J,\"a#/\u0011\t\r522X\u0005\u0005\u0017{\u001byCA\tE_V\u0014G.Z!dGVlW\u000f\\1u_J$Ba#/\fB\"A1RUAh\u0001\u0004\u00199)A\u000bd_2dWm\u0019;j_:\f5mY;nk2\fGo\u001c:\u0016\t-\u001d7\u0012[\u000b\u0003\u0017\u0013\u0004ba!\f\fL.=\u0017\u0002BFg\u0007_\u0011QcQ8mY\u0016\u001cG/[8o\u0003\u000e\u001cW/\\;mCR|'\u000f\u0005\u0003\u00076.EG\u0001\u0003E\u000e\u0003#\u0014\rA\"3\u0016\t-U72\u001c\u000b\u0005\u0017/\\i\u000e\u0005\u0004\u0004.--7\u0012\u001c\t\u0005\rk[Y\u000e\u0002\u0005\t\u001c\u0005M'\u0019\u0001De\u0011!Y)+a5A\u0002\r\u001d\u0015!\u00032s_\u0006$7-Y:u+\u0011Y\u0019oc=\u0015\t-\u001582 \u000b\u0005\u0017O\\)\u0010\u0005\u0004\fj.58\u0012_\u0007\u0003\u0017WTAac8\u0003n&!1r^Fv\u0005%\u0011%o\\1eG\u0006\u001cH\u000f\u0005\u0003\u00076.MH\u0001\u0003E\u000e\u0003+\u0014\rA\"3\t\u0015-]\u0018Q[A\u0001\u0002\bYI0\u0001\u0006fm&$WM\\2fIe\u0002b\u0001c\t\t*-E\b\u0002CDI\u0003+\u0004\ra#=\u0002#\t\u0014x.\u00193dCN$\u0018J\u001c;fe:\fG.\u0006\u0003\r\u00021%AC\u0002G\u0002\u0019#a\u0019\u0002\u0006\u0003\r\u00061-\u0001CBFu\u0017[d9\u0001\u0005\u0003\u000762%A\u0001\u0003E\u000e\u0003/\u0014\rA\"3\t\u001515\u0011q[A\u0001\u0002\bay!A\u0006fm&$WM\\2fIE\u0002\u0004C\u0002E\u0012\u0011Sa9\u0001\u0003\u0005\b\u0012\u0006]\u0007\u0019\u0001G\u0004\u0011!a)\"a6A\u0002\u0015u\u0014AD:fe&\fG.\u001b>fI>sG._\u0001\bC\u0012$g)\u001b7f)\u0011\u00199\u0005d\u0007\t\u0011!\u0005\u0016\u0011\u001ca\u0001\u0007\u000f\u000b\u0011\u0002\\5ti\u001aKG.Z:\u0015\u0005\rM\u0016AC1eI\u0006\u00138\r[5wKR!1q\tG\u0013\u0011!A\t+!8A\u0002\r\u001d\u0005\u0006BAo\u0019S\u0001B\u0001d\u000b\r05\u0011AR\u0006\u0006\u0005\u000f\u0003\u0014i/\u0003\u0003\r215\"\u0001D#ya\u0016\u0014\u0018.\\3oi\u0006d\u0017\u0001\u00047jgR\f%o\u00195jm\u0016\u001c\b\u0006BAp\u0019S!baa\u0012\r:1m\u0002\u0002\u0003EQ\u0003C\u0004\raa\"\t\u00111u\u0012\u0011\u001da\u0001\u000b{\n\u0011B]3dkJ\u001c\u0018N^3\u0015\u0015\r\u001dC\u0012\tG\"\u0019\u000bbI\u0005\u0003\u0005\t\"\u0006\r\b\u0019ABD\u0011!ai$a9A\u0002\u0015u\u0004\u0002\u0003G$\u0003G\u0004\r!\" \u0002\u001b\u0005$G-\u001a3P]N+(-\\5u\u0011)aY%a9\u0011\u0002\u0003\u0007QQP\u0001\nSN\f%o\u00195jm\u0016\f\u0011#\u00193e\r&dW\r\n3fM\u0006,H\u000e\u001e\u00135\u0003A\tG\rZ*qCJ\\G*[:uK:,'\u000f\u0006\u0003\u0004H1M\u0003\u0002\u0003G+\u0003O\u0004\r\u0001d\u0016\u0002\u00111L7\u000f^3oKJ\u0004B\u0001b\n\rZ%!A2\fC\u0015\u0005Y\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe&sG/\u001a:gC\u000e,\u0007\u0006BAt\u0019?\u0002B\u0001d\u000b\rb%!A2\rG\u0017\u00051!UM^3m_B,'/\u00119j\u0003M\u0011X-\\8wKN\u0003\u0018M]6MSN$XM\\3s)\u0011\u00199\u0005$\u001b\t\u00111U\u0013\u0011\u001ea\u0001\u0019/BC!!;\r`\u0005qq-\u001a;Fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\u0018!F7bq:+XnQ8oGV\u0014(/\u001a8u)\u0006\u001c8n\u001d\u000b\u0005\tSc\u0019\b\u0003\u0005\rv\u00055\b\u0019\u0001G<\u0003\t\u0011\b\u000f\u0005\u0003\u0006r2e\u0014\u0002\u0002G>\u000bg\u0014qBU3t_V\u00148-\u001a)s_\u001aLG.Z\u0001\u0016e\u0016\fX/Z:u)>$\u0018\r\\#yK\u000e,Ho\u001c:t)!)i\b$!\r\u00062%\u0005\u0002\u0003GB\u0003_\u0004\r\u0001\"+\u0002\u00199,X.\u0012=fGV$xN]:\t\u00111\u001d\u0015q\u001ea\u0001\tS\u000b!\u0003\\8dC2LG/_!xCJ,G+Y:lg\"AA2RAx\u0001\u0004ai)\u0001\u000bi_N$Hk\u001c'pG\u0006dG+Y:l\u0007>,h\u000e\u001e\t\t\u000bO,ioa\"\u0005*\"\"\u0011q\u001eG0\u0003A\u0011X-];fgR,\u00050Z2vi>\u00148\u000f\u0006\u0003\u0006~1U\u0005\u0002\u0003GL\u0003c\u0004\r\u0001\"+\u0002-9,X.\u00113eSRLwN\\1m\u000bb,7-\u001e;peNDC!!=\r`\u0005i1.\u001b7m\u000bb,7-\u001e;peN$B!\" \r \"AA\u0012UAz\u0001\u0004\u0019\u0019,A\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\b\u0006BAz\u0019?\nAb[5mY\u0016CXmY;u_J$B!\" \r*\"Aq1MA{\u0001\u0004\u00199\t\u000b\u0003\u0002v2}\u0013AF6jY2\fe\u000e\u001a*fa2\f7-Z#yK\u000e,Ho\u001c:\u0015\t\u0015uD\u0012\u0017\u0005\t\u000fG\n9\u00101\u0001\u0004\b\u00069a/\u001a:tS>t\u0017aF4fi\u0016CXmY;u_JlU-\\8ssN#\u0018\r^;t+\taI\f\u0005\u0005\u0004L\u000eE7q\u0011G^!!\u0011y\u0010#&\u0004X\r]\u0013!E4fiJ#Ei\u0015;pe\u0006<W-\u00138g_V\u0011A\u0012\u0019\t\u0007\u0005\u007f<y\u0005d1\u0011\t1\u0015G2Z\u0007\u0003\u0019\u000fTA\u0001$3\u0003n\u000691\u000f^8sC\u001e,\u0017\u0002\u0002Gg\u0019\u000f\u0014qA\u0015#E\u0013:4w\u000e\u000b\u0003\u0002~2}C\u0003\u0002Ga\u0019'D\u0001\u0002$6\u0002\u0000\u0002\u0007Ar[\u0001\u0007M&dG/\u001a:\u0011\u0011\t}H\u0012\u001cGo\u000b{JA\u0001d7\u0004\u0002\tIa)\u001e8di&|g.\r\u0019\u0005\u0019?d\u0019\u000f\u0005\u0004\u0007*\u001a=F\u0012\u001d\t\u0005\rkc\u0019\u000f\u0002\u0007\rf2M\u0017\u0011!A\u0001\u0006\u00031IM\u0001\u0003`IE\u0002\u0014!E4fiB+'o]5ti\u0016tGO\u0015#EgV\u0011A2\u001e\t\t\u0007\u0017\u001c\t\u000e\"+\rnB\"Ar\u001eGz!\u00191IKb,\rrB!aQ\u0017Gz\t1a)P!\u0001\u0002\u0002\u0003\u0005)\u0011\u0001De\u0005\u0011yF%M\u0019\u0002\u0017\u001d,G/\u00117m!>|Gn]\u000b\u0003\u0019w\u0004ba!.\u0004@2u\b\u0003\u0002C\u0014\u0019\u007fLA!$\u0001\u0005*\tY1k\u00195fIVd\u0017M\u00197fQ\u0011\u0011\u0019\u0001d\u0018\u0002\u001d\u001d,G\u000fU8pY\u001a{'OT1nKR!Q\u0012BG\u0006!\u0019\u0011ypa\u0010\r~\"AQR\u0002B\u0003\u0001\u0004\u00199)\u0001\u0003q_>d\u0007\u0006\u0002B\u0003\u0019?\n\u0011cZ3u'\u000eDW\rZ;mS:<Wj\u001c3f+\ti)\u0002\u0005\u0003\u000e\u00185ua\u0002\u0002C\u0014\u001b3IA!d\u0007\u0005*\u0005q1k\u00195fIVd\u0017N\\4N_\u0012,\u0017\u0002BG\u0010\u001bC\u0011abU2iK\u0012,H.\u001b8h\u001b>$WM\u0003\u0003\u000e\u001c\u0011%\u0012\u0001E4fiB\u0013XMZ3se\u0016$Gj\\2t)\u0019i9#d\f\u000e<A11QWB`\u001bS\u0001B\u0001b\n\u000e,%!QR\u0006C\u0015\u00051!\u0016m]6M_\u000e\fG/[8o\u0011!1iK!\u0003A\u00025E\u0002\u0007BG\u001a\u001bo\u0001bA\"+\u000706U\u0002\u0003\u0002D[\u001bo!A\"$\u000f\u000e0\u0005\u0005\t\u0011!B\u0001\r\u0013\u0014Aa\u0018\u00132e!AQR\bB\u0005\u0001\u0004!I+A\u0005qCJ$\u0018\u000e^5p]\u0006Q\u0001/\u001a:tSN$(\u000b\u0012#\u0015\t\r\u001dS2\t\u0005\t\r[\u0013Y\u00011\u0001\u000eFA\"QrIG&!\u00191IKb,\u000eJA!aQWG&\t1ii%d\u0011\u0002\u0002\u0003\u0005)\u0011\u0001De\u0005\u0011yF%M\u001a\u0002\u0019Ut\u0007/\u001a:tSN$(\u000b\u0012#\u0015\r\r\u001dS2KG,\u0011!i)F!\u0004A\u0002\u0011%\u0016!\u0002:eI&#\u0007\u0002CG-\u0005\u001b\u0001\r!\" \u0002\u0011\tdwnY6j]\u001e\fa!\u00193e\u0015\u0006\u0014H\u0003BB$\u001b?B\u0001\u0002#)\u0003\u0010\u0001\u00071q\u0011\u000b\u0007\u0007\u000fj\u0019'$\u001a\t\u0011!\u0005&\u0011\u0003a\u0001\u0007\u000fC\u0001\u0002d\u0012\u0003\u0012\u0001\u0007QQP\u0001\tY&\u001cHOS1sg\u0006y1\u000f^8q\u0013:tUm\u001e+ie\u0016\fG-\u0001\u0003ti>\u0004H\u0003BB$\u001b_B\u0001\"$\u001d\u0003\u001a\u0001\u0007A\u0011V\u0001\tKbLGoQ8eK\u0006aq-\u001a;Ta\u0006\u00148\u000eS8nKR\u0011AqC\u0001\fg\u0016$8)\u00197m'&$X\r\u0006\u0003\u0004H5m\u0004\u0002CG?\u0005;\u0001\raa\"\u0002\u001bMDwN\u001d;DC2d7+\u001b;f)\u0011\u00199%$!\t\u00115\r%q\u0004a\u0001\u0007W\t\u0001bY1mYNKG/Z\u0001\u000eG2,\u0017M]\"bY2\u001c\u0016\u000e^3\u0002\u0017\u001d,GoQ1mYNKG/\u001a\u000b\u0003\u0007W\taA];o\u0015>\u0014WCBGH\u001bGkY\n\u0006\u0006\u000e\u00126uURUG^\u001b\u0003$Baa\u0012\u000e\u0014\"QQR\u0013B\u0013\u0003\u0003\u0005\u001d!d&\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0007\u0011GAI#$'\u0011\t\u0019UV2\u0014\u0003\t\u0011\u0003\u0011)C1\u0001\u0007J\"AaQ\u0016B\u0013\u0001\u0004iy\n\u0005\u0004\u0007*\u001a=V\u0012\u0015\t\u0005\rkk\u0019\u000b\u0002\u0005\t\u001c\t\u0015\"\u0019\u0001De\u0011!i9K!\nA\u00025%\u0016\u0001\u00024v]\u000e\u0004\"Ba@\u000e,6=VRWGM\u0013\u0011iik!\u0001\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004\u0003BB\r\u001bcKA!d-\u0003n\nYA+Y:l\u0007>tG/\u001a=u!\u0019\u0019),d.\u000e\"&!Q\u0012XBb\u0005!IE/\u001a:bi>\u0014\b\u0002CG_\u0005K\u0001\r!d0\u0002\u0015A\f'\u000f^5uS>t7\u000f\u0005\u0004\u00046\u000e}F\u0011\u0016\u0005\t\u001b\u0007\u0014)\u00031\u0001\u000eF\u0006i!/Z:vYRD\u0015M\u001c3mKJ\u0004\"Ba@\u000e,\u0012%V\u0012TB$+\u0019iI-d8\u000eRRAQ2ZGm\u001bCl9\u000f\u0006\u0003\u000eN6M\u0007C\u0002B\u0000\u000f\u001fjy\r\u0005\u0003\u000766EG\u0001\u0003E\u0001\u0005O\u0011\rA\"3\t\u00155U'qEA\u0001\u0002\bi9.A\u0006fm&$WM\\2fIE\u0012\u0004C\u0002E\u0012\u0011Siy\r\u0003\u0005\u0007.\n\u001d\u0002\u0019AGn!\u00191IKb,\u000e^B!aQWGp\t!AYBa\nC\u0002\u0019%\u0007\u0002CGT\u0005O\u0001\r!d9\u0011\u0015\t}X2VGX\u001bKly\r\u0005\u0004\u000466]VR\u001c\u0005\t\u001b{\u00139\u00031\u0001\u000e@V1Q2\u001eH\u0001\u001bg$\u0002\"$<\u000e|:\ra\u0012\u0002\u000b\u0005\u001b_l)\u0010\u0005\u0004\u0003\u0000\u001e=S\u0012\u001f\t\u0005\rkk\u0019\u0010\u0002\u0005\t\u0002\t%\"\u0019\u0001De\u0011)i9P!\u000b\u0002\u0002\u0003\u000fQ\u0012`\u0001\fKZLG-\u001a8dK\u0012\n4\u0007\u0005\u0004\t$!%R\u0012\u001f\u0005\t\r[\u0013I\u00031\u0001\u000e~B1a\u0011\u0016DX\u001b\u007f\u0004BA\".\u000f\u0002\u0011A\u00012\u0004B\u0015\u0005\u00041I\r\u0003\u0005\u000e(\n%\u0002\u0019\u0001H\u0003!!\u0011y\u0010$7\u000f\b5E\bCBB[\u001boky\u0010\u0003\u0005\u000e>\n%\u0002\u0019AG`+\u0019qiAd\t\u000f\u0016Q1ar\u0002H\u000f\u001dK!BA$\u0005\u000f\u0018A1!q`D(\u001d'\u0001BA\".\u000f\u0016\u0011A\u0001\u0012\u0001B\u0016\u0005\u00041I\r\u0003\u0006\u000f\u001a\t-\u0012\u0011!a\u0002\u001d7\t1\"\u001a<jI\u0016t7-\u001a\u00132iA1\u00012\u0005E\u0015\u001d'A\u0001B\",\u0003,\u0001\u0007ar\u0004\t\u0007\rS3yK$\t\u0011\t\u0019Uf2\u0005\u0003\t\u00117\u0011YC1\u0001\u0007J\"AQr\u0015B\u0016\u0001\u0004q9\u0003\u0005\u0006\u0003\u00006-Vr\u0016H\u0015\u001d'\u0001ba!.\u000e8:\u0005RC\u0002H\u0017\u001d\u0007r)\u0004\u0006\u0004\u000f09ubR\t\u000b\u0005\u001dcq9\u0004\u0005\u0004\u0003\u0000\u001e=c2\u0007\t\u0005\rks)\u0004\u0002\u0005\t\u0002\t5\"\u0019\u0001De\u0011)qID!\f\u0002\u0002\u0003\u000fa2H\u0001\fKZLG-\u001a8dK\u0012\nT\u0007\u0005\u0004\t$!%b2\u0007\u0005\t\r[\u0013i\u00031\u0001\u000f@A1a\u0011\u0016DX\u001d\u0003\u0002BA\".\u000fD\u0011A\u00012\u0004B\u0017\u0005\u00041I\r\u0003\u0005\u000e(\n5\u0002\u0019\u0001H$!!\u0011y\u0010$7\u000fJ9M\u0002CBB[\u001bos\t%\u0006\u0004\u000fN9\u0005d\u0012\f\u000b\t\u001d\u001frYFd\u0019\u000flQ!1q\tH)\u0011)q\u0019Fa\f\u0002\u0002\u0003\u000faRK\u0001\fKZLG-\u001a8dK\u0012\nd\u0007\u0005\u0004\t$!%br\u000b\t\u0005\rksI\u0006\u0002\u0005\t\u0002\t=\"\u0019\u0001De\u0011!1iKa\fA\u00029u\u0003C\u0002DU\r_sy\u0006\u0005\u0003\u00076:\u0005D\u0001\u0003E\u000e\u0005_\u0011\rA\"3\t\u00119\u0015$q\u0006a\u0001\u001dO\n\u0001\u0003\u001d:pG\u0016\u001c8\u000fU1si&$\u0018n\u001c8\u0011\u0015\t}X2VGX\u001dSr9\u0006\u0005\u0004\u000466]fr\f\u0005\t\u001b\u0007\u0014y\u00031\u0001\u000fnAQ!q`GV\tSs9fa\u0012\u0016\r9EdR\u0011H?)!q\u0019Hd \u000f\b:5E\u0003BB$\u001dkB!Bd\u001e\u00032\u0005\u0005\t9\u0001H=\u0003-)g/\u001b3f]\u000e,G%M\u001c\u0011\r!\r\u0002\u0012\u0006H>!\u00111)L$ \u0005\u0011!\u0005!\u0011\u0007b\u0001\r\u0013D\u0001B\",\u00032\u0001\u0007a\u0012\u0011\t\u0007\rS3yKd!\u0011\t\u0019UfR\u0011\u0003\t\u00117\u0011\tD1\u0001\u0007J\"AaR\rB\u0019\u0001\u0004qI\t\u0005\u0005\u0003\u00002eg2\u0012H>!\u0019\u0019),d.\u000f\u0004\"AQ2\u0019B\u0019\u0001\u0004qy\t\u0005\u0006\u0003\u00006-F\u0011\u0016H>\u0007\u000f\n\u0011C];o\u0003B\u0004(o\u001c=j[\u0006$XMS8c+!q)Jd,\u000f::\u0015FC\u0003HL\u001dSs\tLd/\u000fFB1a\u0012\u0014HP\u001dGk!Ad'\u000b\t9u%Q^\u0001\ba\u0006\u0014H/[1m\u0013\u0011q\tKd'\u0003\u001bA\u000b'\u000f^5bYJ+7/\u001e7u!\u00111)L$*\u0005\u00119\u001d&1\u0007b\u0001\r\u0013\u0014\u0011A\u0015\u0005\t\r[\u0013\u0019\u00041\u0001\u000f,B1a\u0011\u0016DX\u001d[\u0003BA\".\u000f0\u0012A\u00012\u0004B\u001a\u0005\u00041I\r\u0003\u0005\u000e(\nM\u0002\u0019\u0001HZ!)\u0011y0d+\u000e0:Ufr\u0017\t\u0007\u0007kk9L$,\u0011\t\u0019Uf\u0012\u0018\u0003\t\u0011\u0003\u0011\u0019D1\u0001\u0007J\"AaR\u0018B\u001a\u0001\u0004qy,A\u0005fm\u0006dW/\u0019;peBAa\u0012\u0014Ha\u001dos\u0019+\u0003\u0003\u000fD:m%\u0001F!qaJ|\u00070[7bi\u0016,e/\u00197vCR|'\u000f\u0003\u0005\u000fH\nM\u0002\u0019AB,\u0003\u001d!\u0018.\\3pkRDCAa\r\r`\u0005I1/\u001e2nSRTuNY\u000b\t\u001d\u001ft\tOd;\u000fZRaa\u0012\u001bHn\u001dGtiOd<\u000ftB11\u0011\u0004Hj\u001d/LAA$6\u0003n\n\u00112+[7qY\u00164U\u000f^;sK\u0006\u001bG/[8o!\u00111)L$7\u0005\u00119\u001d&Q\u0007b\u0001\r\u0013D\u0001B\",\u00036\u0001\u0007aR\u001c\t\u0007\rS3yKd8\u0011\t\u0019Uf\u0012\u001d\u0003\t\u00117\u0011)D1\u0001\u0007J\"AaR\rB\u001b\u0001\u0004q)\u000f\u0005\u0005\u0003\u00002egr\u001dHu!\u0019\u0019),d.\u000f`B!aQ\u0017Hv\t!A\tA!\u000eC\u0002\u0019%\u0007\u0002CG_\u0005k\u0001\r!d0\t\u00115\r'Q\u0007a\u0001\u001dc\u0004\"Ba@\u000e,\u0012%f\u0012^B$\u0011%q)P!\u000e\u0005\u0002\u0004q90\u0001\u0006sKN,H\u000e\u001e$v]\u000e\u0004bAa@\t\n9]\u0017AD:vE6LG/T1q'R\fw-Z\u000b\t\u001d{|\u0019bd\u0006\u0010\u001cQ!ar`H\u0004!\u0019\u0019IBd5\u0010\u0002A!1\u0011DH\u0002\u0013\u0011y)A!<\u0003'5\u000b\u0007oT;uaV$8\u000b^1uSN$\u0018nY:\t\u0011=%!q\u0007a\u0001\u001f\u0017\t!\u0002Z3qK:$WM\\2z!)\u0019Ib$\u0004\u0010\u0012=Uq\u0012D\u0005\u0005\u001f\u001f\u0011iOA\tTQV4g\r\\3EKB,g\u000eZ3oGf\u0004BA\".\u0010\u0014\u0011A\u00012 B\u001c\u0005\u00041I\r\u0005\u0003\u00076>]A\u0001CE\u0001\u0005o\u0011\rA\"3\u0011\t\u0019Uv2\u0004\u0003\t\u001f;\u00119D1\u0001\u0007J\n\t1)\u0001\bdC:\u001cW\r\u001c&pE\u001e\u0013x.\u001e9\u0015\r\r\u001ds2EH\u0013\u0011!9)K!\u000fA\u0002\r\u001d\u0005\u0002CH\u0014\u0005s\u0001\raa\"\u0002\rI,\u0017m]8o)\u0011\u00199ed\u000b\t\u0011\u001d\u0015&1\ba\u0001\u0007\u000f\u000b1dY1oG\u0016d'j\u001c2He>,\b/\u00118e\rV$XO]3K_\n\u001cHCBB$\u001fcy\u0019\u0004\u0003\u0005\b&\nu\u0002\u0019ABD\u0011!y9C!\u0010A\u0002\r\u001dE\u0003BB$\u001foA\u0001b\"*\u0003@\u0001\u00071qQ\u0001\u001cG\u0006t7-\u001a7K_\n\u001cx+\u001b;i)\u0006<w+\u001b;i\rV$XO]3\u0015\r=urrJH)!\u0019yydd\u0011\u0010H5\u0011q\u0012\t\u0006\u0005\u0007[\u001a\t!\u0003\u0003\u0010F=\u0005#A\u0002$viV\u0014X\r\u0005\u0004\u00046\u000e}v\u0012\n\t\u0005\tOyY%\u0003\u0003\u0010N\u0011%\"!C!di&4XMS8c\u0011!9)N!\u0011A\u0002\r\u001d\u0005\u0002CH\u0014\u0005\u0003\u0002\raa\"\u0002#\r\fgnY3m\u0015>\u00147oV5uQR\u000bw\r\u0006\u0004\u0004H=]s\u0012\f\u0005\t\u000f+\u0014\u0019\u00051\u0001\u0004\b\"Aqr\u0005B\"\u0001\u0004\u00199\t\u0006\u0003\u0004H=u\u0003\u0002CDk\u0005\u000b\u0002\raa\"\u0002\u001b\r\fgnY3m\u00032d'j\u001c2t\u0003%\u0019\u0017M\\2fY*{'\r\u0006\u0004\u0004H=\u0015t\u0012\u000e\u0005\t\u001fO\u0012I\u00051\u0001\u0005*\u0006)!n\u001c2JI\"Aqr\u0005B%\u0001\u0004\u00199\t\u0006\u0003\u0004H=5\u0004\u0002CH4\u0005\u0017\u0002\r\u0001\"+\u0002\u0017\r\fgnY3m'R\fw-\u001a\u000b\u0007\u0007\u000fz\u0019hd\u001e\t\u0011=U$Q\na\u0001\tS\u000bqa\u001d;bO\u0016LE\r\u0003\u0005\u0010(\t5\u0003\u0019ABD)\u0011\u00199ed\u001f\t\u0011=U$q\na\u0001\tS\u000bqb[5mYR\u000b7o[!ui\u0016l\u0007\u000f\u001e\u000b\t\u000b{z\tid!\u0010\b\"AqQ\u000eB)\u0001\u0004\u00199\u0006\u0003\u0006\u0010\u0006\nE\u0003\u0013!a\u0001\u000b{\nq\"\u001b8uKJ\u0014X\u000f\u001d;UQJ,\u0017\r\u001a\u0005\u000b\u001fO\u0011\t\u0006%AA\u0002\r\u001d\u0015!G6jY2$\u0016m]6BiR,W\u000e\u001d;%I\u00164\u0017-\u001e7uII\n\u0011d[5mYR\u000b7o[!ui\u0016l\u0007\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%gU\u0011qr\u0012\u0016\u0005\u0007\u000f;),A\u0003dY\u0016\fg.\u0006\u0003\u0010\u0016>eECBHL\u001f;{\t\u000b\u0005\u0003\u00076>eE\u0001CEP\u0005/\u0012\rad'\u0012\t\u0019-'Q \u0005\t\u001f?\u00139\u00061\u0001\u0010\u0018\u0006\ta\r\u0003\u0006\u0010$\n]\u0003\u0013!a\u0001\u000b{\n\u0011c\u00195fG.\u001cVM]5bY&T\u0018M\u00197f\u0003=\u0019G.Z1oI\u0011,g-Y;mi\u0012\u0012T\u0003BDY\u001fS#\u0001\"c(\u0003Z\t\u0007q2T\u0001\u0011g\u0016$8\t[3dWB|\u0017N\u001c;ESJ$Baa\u0012\u00100\"Aq\u0012\u0017B.\u0001\u0004\u00199)A\u0005eSJ,7\r^8ss\u0006\u0001r-\u001a;DQ\u0016\u001c7\u000e]8j]R$\u0015N]\u0001\u0013I\u00164\u0017-\u001e7u!\u0006\u0014\u0018\r\u001c7fY&\u001cX.\u0001\u000beK\u001a\fW\u000f\u001c;NS:\u0004\u0016M\u001d;ji&|gn]\u0001\u000e]\u0016DHo\u00155vM\u001adW-\u00133\u0016\u0005=u\u0006\u0003BB3\u001f\u007fKAa$1\u0004h\ti\u0011\t^8nS\u000eLe\u000e^3hKJ\faB\\3yiNCWO\u001a4mK&#\u0007%\u0001\u0007oK^\u001c\u0006.\u001e4gY\u0016LE\r\u0006\u0002\u0005*\u0006Ia.\u001a=u%\u0012$\u0017\nZ\u0001\u000b]\u0016DHO\u00153e\u0013\u0012\u0004\u0013\u0001\u00038foJ#G-\u00133\u00021M,G/\u001e9B]\u0012\u001cF/\u0019:u\u0019&\u001cH/\u001a8fe\n+8/\u0001\u000bq_N$\u0018\t\u001d9mS\u000e\fG/[8o'R\f'\u000f^\u0001\u0013a>\u001cH/\u00119qY&\u001c\u0017\r^5p]\u0016sG\r\u0006\u0003\u0004H=]\u0007\u0002CG9\u0005g\u0002\r\u0001\"+\u0002+A|7\u000f^#om&\u0014xN\\7f]R,\u0006\u000fZ1uK\u0006y!/\u001a9peRDU-\u0019:u\u0005\u0016\fG\u000f\u0006\u0003\u0004H=}\u0007\u0002CHq\u0005o\u0002\rad9\u0002+\u0015DXmY;u_JlU\r\u001e:jGN\u001cv.\u001e:dKB1!q`B \u001fK\u0004Bad:\u0010n6\u0011q\u0012\u001e\u0006\u0005\u001fW\u0014i/\u0001\u0005fq\u0016\u001cW\u000f^8s\u0013\u0011yyo$;\u0003+\u0015CXmY;u_JlU\r\u001e:jGN\u001cv.\u001e:dK\u0006a1\u000b]1sW\u000e{g\u000e^3yiB!1\u0011\u0004B>'\u0019\u0011YH!@\u0004\nQ\u0011q2_\u0001\u0011-\u0006c\u0015\nR0M\u001f\u001e{F*\u0012,F\u0019N+\"a$@\u0011\r\u0015\u001dxr I\u0001\u0013\u00119\u0019/\";\u0011\t\u001d-\u00023A\u0005\u0005\u00073;i#A\tW\u00032KEi\u0018'P\u000f~cUIV#M'\u0002\nad\u0015)B%.{6i\u0014(U\u000bb#vlQ(O'R\u0013Vk\u0011+P%~cujQ&\u0016\u0005A-\u0001\u0003BD\u0016!\u001bIA\u0001e\u0004\b.\t1qJ\u00196fGR\fqd\u0015)B%.{6i\u0014(U\u000bb#vlQ(O'R\u0013Vk\u0011+P%~cujQ&!\u00035\t7\r^5wK\u000e{g\u000e^3yiV\u0011\u0001s\u0003\t\u0007\u0007K\u0002Jba\t\n\tAm1q\r\u0002\u0010\u0003R|W.[2SK\u001a,'/\u001a8dK\u0006q\u0011m\u0019;jm\u0016\u001cuN\u001c;fqR\u0004\u0013aF2p]R,\u0007\u0010\u001e\"fS:<7i\u001c8tiJ,8\r^3e+\t\u0001\u001a\u0003\u0005\u0004\u0003\u0000\u000e}21E\u0001\u001cG>tG/\u001a=u\u0005\u0016LgnZ\"p]N$(/^2uK\u0012|F%Z9\u0015\t\r\u001d\u0003\u0013\u0006\u0005\u000b\u0007\u001f\u0012i)!AA\u0002A\r\u0012\u0001G2p]R,\u0007\u0010\u001e\"fS:<7i\u001c8tiJ,8\r^3eA\u0005i\u0012m]:feRtun\u0014;iKJ\u001cuN\u001c;fqRL5OU;o]&tw\r\u0006\u0003\u0004HAE\u0002\u0002\u0003I\u001a\u0005#\u0003\raa\t\u0002\u0005M\u001c\u0017AD1tg\u0016\u0014Ho\u00148Ee&4XM]\u0001\fO\u0016$xJ]\"sK\u0006$X\r\u0006\u0003\u0004$Am\u0002\u0002CB\u000b\u0005+\u0003\raa\u0006\u0002\u0013\u001d,G/Q2uSZ,\u0017\u0001G7be.\u0004\u0016M\u001d;jC2d\u0017pQ8ogR\u0014Xo\u0019;fIR!1q\tI\"\u0011!\u0001\u001aDa'A\u0002\r\r\u0012\u0001E:fi\u0006\u001bG/\u001b<f\u0007>tG/\u001a=u)\u0011\u00199\u0005%\u0013\t\u0011AM\"Q\u0014a\u0001\u0007G\t!c\u00197fCJ\f5\r^5wK\u000e{g\u000e^3yi\u0006)2\u000bU!S\u0017~SuJQ0E\u000bN\u001b%+\u0013)U\u0013>sUC\u0001I\u0001\u0003Y\u0019\u0006+\u0011*L?*{%i\u0018#F'\u000e\u0013\u0016\n\u0015+J\u001f:\u0003\u0013AE*Q\u0003J[uLS(C?\u001e\u0013v*\u0016)`\u0013\u0012\u000b1c\u0015)B%.{&j\u0014\"`\u000fJ{U\u000bU0J\t\u0002\nQd\u0015)B%.{&j\u0014\"`\u0013:#VI\u0015*V!R{vJT0D\u0003:\u001bU\tT\u0001\u001f'B\u000b%kS0K\u001f\n{\u0016J\u0014+F%J+\u0006\u000bV0P\u001d~\u001b\u0015IT\"F\u0019\u0002\nab\u0015)B%.{&j\u0014\"`)\u0006;5+A\bT!\u0006\u00136j\u0018&P\u0005~#\u0016iR*!\u0003Q\u0019\u0006+\u0011*L?N\u001b\u0005*\u0012#V\u0019\u0016\u0013v\fU(P\u0019\u0006)2\u000bU!S\u0017~\u001b6\tS#E+2+%k\u0018)P\u001f2\u0003\u0013!\u0004*E\t~\u001b6i\u0014)F?.+\u0015,\u0001\bS\t\u0012{6kQ(Q\u000b~[U)\u0017\u0011\u00023I#EiX*D\u001fB+uLT(`\u001fZ+%KU%E\u000b~[U)W\u0001\u001b%\u0012#ulU\"P!\u0016{fjT0P-\u0016\u0013&+\u0013#F?.+\u0015\fI\u0001\u0012\tJKe+\u0012*`\u0013\u0012+e\nV%G\u0013\u0016\u0013\u0016A\u0005#S\u0013Z+%kX%E\u000b:#\u0016JR%F%\u0002\n!c\u0015)B%.{&j\u0014\"`)\u0006;5kX*F!\u0006\u00192\u000bU!S\u0017~SuJQ0U\u0003\u001e\u001bvlU#QA\u0005\tB\u000f\u001b:po&3\u0017J\u001c<bY&$G+Y4\u0015\t\r\u001d\u0003s\u000f\u0005\t\u000f+\u0014)\r1\u0001\u0004\b\u0006Q!.\u0019:PM\u000ec\u0017m]:\u0015\t\u0011]\u0001S\u0010\u0005\t!\u007f\u00129\r1\u0001\u0011\u0002\u0006\u00191\r\\:1\tA\r\u0005s\u0011\t\u0007\u0007\u0013KI\u0002%\"\u0011\t\u0019U\u0006s\u0011\u0003\r!\u0013\u0003j(!A\u0001\u0002\u000b\u0005a\u0011\u001a\u0002\u0005?\u0012\nD'A\u0006kCJ|em\u00142kK\u000e$H\u0003\u0002C\f!\u001fC\u0001\u0002%%\u0003J\u0002\u0007!Q`\u0001\u0004_\nT\u0017aC;qI\u0006$X\rZ\"p]\u001a$bba\u0006\u0011\u0018Be\u00053\u0014IO!?\u0003\n\u000b\u0003\u0005\u0004$\n-\u0007\u0019AB\f\u0011!\u0019)Ia3A\u0002\r\u001d\u0005\u0002CBP\u0005\u0017\u0004\raa\"\t\u0015\r5&1\u001aI\u0001\u0002\u0004\u00199\t\u0003\u0006\u00042\n-\u0007\u0013!a\u0001\u0007gC!ba2\u0003LB\u0005\t\u0019ABe\u0003U)\b\u000fZ1uK\u0012\u001cuN\u001c4%I\u00164\u0017-\u001e7uIQ\nQ#\u001e9eCR,GmQ8oM\u0012\"WMZ1vYR$S'\u0006\u0002\u0011**\"11WD[\u0003U)\b\u000fZ1uK\u0012\u001cuN\u001c4%I\u00164\u0017-\u001e7uIY*\"\u0001e,+\t\r%wQW\u0001\u000f]VlGI]5wKJ\u001cuN]3t)\u0011!I\u000b%.\t\u0011\r\u0015%1\u001ba\u0001\u0007\u000f#b\u0001\"+\u0011:Bm\u0006\u0002CBC\u0005+\u0004\raa\"\t\u0011\r\r&Q\u001ba\u0001\u0007/\t!#\u001a=fGV$xN]'f[>\u0014\u00180\u00138NER!A\u0011\u0016Ia\u0011!\u0019\u0019Ka6A\u0002\r]\u0011\u0001D<be:\u001c\u0006/\u0019:l\u001b\u0016lG\u0003BBD!\u000fD\u0001b\"%\u0003Z\u0002\u00071qQ\u0001\u0014GJ,\u0017\r^3UCN\\7k\u00195fIVdWM\u001d\u000b\u0007!\u001b\u0004z\r%5\u0011\u0011\t}\bR\u0013C^\t\u001bD\u0001\u0002e\r\u0003\\\u0002\u000711\u0005\u0005\t\u0007\u000b\u0013Y\u000e1\u0001\u0004\b\u0006\tr-\u001a;DYV\u001cH/\u001a:NC:\fw-\u001a:\u0015\tA]\u0007s\u001c\t\u0007\u0005\u007f\u001cy\u0004%7\u0011\t\u0011\u001d\u00023\\\u0005\u0005!;$IC\u0001\fFqR,'O\\1m\u00072,8\u000f^3s\u001b\u0006t\u0017mZ3s\u0011!\u0001\nO!8A\u0002\r\u001d\u0015aA;sY\u00061c-\u001b7m\u001b&\u001c8/\u001b8h\u001b\u0006<\u0017nY\"p[6LG\u000f^3s\u0007>tgm]%g\u001d\u0016,G-\u001a3\u0015\t\r\u001d\u0003s\u001d\u0005\t\u0007G\u0013y\u000e1\u0001\u0004\u0018\u0005Y2/\u001e9qY\u0016lWM\u001c;KCZ\fWj\u001c3vY\u0016|\u0005\u000f^5p]N$Baa\u0012\u0011n\"A11\u0015Bq\u0001\u0004\u00199\"A\rtkB\u0004H.Z7f]RT\u0015M^1J!Z4t\n\u001d;j_:\u001cH\u0003BB$!gD\u0001ba)\u0003d\u0002\u00071qC\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0001"
)
public class SparkContext implements Logging {
   private final CallSite org$apache$spark$SparkContext$$creationSite;
   private Option stopSite;
   private final long startTime;
   private final AtomicBoolean stopped;
   private SparkConf _conf;
   private Option _eventLogDir;
   private Option _eventLogCodec;
   private LiveListenerBus _listenerBus;
   private SparkEnv _env;
   private SparkStatusTracker _statusTracker;
   private Option _progressBar;
   private Option _ui;
   private Configuration _hadoopConfiguration;
   private int _executorMemory;
   private SchedulerBackend _schedulerBackend;
   private TaskScheduler _taskScheduler;
   private RpcEndpointRef _heartbeatReceiver;
   private volatile DAGScheduler _dagScheduler;
   private String _applicationId;
   private Option _applicationAttemptId;
   private Option _eventLogger;
   private Option _driverLogger;
   private Option _executorAllocationManager;
   private Option _cleaner;
   private boolean _listenerBusStarted;
   private Seq _jars;
   private Seq _files;
   private Seq _archives;
   private Object _shutdownHookRef;
   private AppStatusStore _statusStore;
   private Heartbeater _heartbeater;
   private Map _resources;
   private ShuffleDriverComponents _shuffleDriverComponents;
   private Option _plugins;
   private ResourceProfileManager _resourceProfileManager;
   private final scala.collection.concurrent.Map addedFiles;
   private final scala.collection.concurrent.Map addedArchives;
   private final scala.collection.concurrent.Map addedJars;
   private final scala.collection.concurrent.Map persistentRdds;
   private final HashMap executorEnvs;
   private final String sparkUser;
   private Option checkpointDir;
   private final InheritableThreadLocal localProperties;
   private final AtomicInteger nextShuffleId;
   private final AtomicInteger nextRddId;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static scala.collection.Map $lessinit$greater$default$5() {
      return SparkContext$.MODULE$.$lessinit$greater$default$5();
   }

   public static Seq $lessinit$greater$default$4() {
      return SparkContext$.MODULE$.$lessinit$greater$default$4();
   }

   public static String $lessinit$greater$default$3() {
      return SparkContext$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option jarOfObject(final Object obj) {
      return SparkContext$.MODULE$.jarOfObject(obj);
   }

   public static Option jarOfClass(final Class cls) {
      return SparkContext$.MODULE$.jarOfClass(cls);
   }

   public static SparkContext getOrCreate() {
      return SparkContext$.MODULE$.getOrCreate();
   }

   public static SparkContext getOrCreate(final SparkConf config) {
      return SparkContext$.MODULE$.getOrCreate(config);
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

   public CallSite org$apache$spark$SparkContext$$creationSite() {
      return this.org$apache$spark$SparkContext$$creationSite;
   }

   private Option stopSite() {
      return this.stopSite;
   }

   private void stopSite_$eq(final Option x$1) {
      this.stopSite = x$1;
   }

   public long startTime() {
      return this.startTime;
   }

   public AtomicBoolean stopped() {
      return this.stopped;
   }

   public void assertNotStopped() {
      if (this.stopped().get()) {
         SparkContext activeContext = (SparkContext)SparkContext$.MODULE$.org$apache$spark$SparkContext$$activeContext().get();
         String activeCreationSite = activeContext == null ? "(No active SparkContext.)" : activeContext.org$apache$spark$SparkContext$$creationSite().longForm();
         StringOps var10002 = .MODULE$;
         Predef var10003 = scala.Predef..MODULE$;
         String var10004 = this.org$apache$spark$SparkContext$$creationSite().longForm();
         throw new IllegalStateException(var10002.stripMargin$extension(var10003.augmentString("Cannot call methods on a stopped SparkContext.\n           |This stopped SparkContext was created at:\n           |\n           |" + var10004 + "\n           |\n           |And it was stopped at:\n           |\n           |" + ((CallSite)this.stopSite().getOrElse(() -> CallSite$.MODULE$.empty())).longForm() + "\n           |\n           |The currently active SparkContext was created at:\n           |\n           |" + activeCreationSite + "\n         ")));
      }
   }

   private SparkConf _conf() {
      return this._conf;
   }

   private void _conf_$eq(final SparkConf x$1) {
      this._conf = x$1;
   }

   private Option _eventLogDir() {
      return this._eventLogDir;
   }

   private void _eventLogDir_$eq(final Option x$1) {
      this._eventLogDir = x$1;
   }

   private Option _eventLogCodec() {
      return this._eventLogCodec;
   }

   private void _eventLogCodec_$eq(final Option x$1) {
      this._eventLogCodec = x$1;
   }

   private LiveListenerBus _listenerBus() {
      return this._listenerBus;
   }

   private void _listenerBus_$eq(final LiveListenerBus x$1) {
      this._listenerBus = x$1;
   }

   private SparkEnv _env() {
      return this._env;
   }

   private void _env_$eq(final SparkEnv x$1) {
      this._env = x$1;
   }

   private SparkStatusTracker _statusTracker() {
      return this._statusTracker;
   }

   private void _statusTracker_$eq(final SparkStatusTracker x$1) {
      this._statusTracker = x$1;
   }

   private Option _progressBar() {
      return this._progressBar;
   }

   private void _progressBar_$eq(final Option x$1) {
      this._progressBar = x$1;
   }

   private Option _ui() {
      return this._ui;
   }

   private void _ui_$eq(final Option x$1) {
      this._ui = x$1;
   }

   private Configuration _hadoopConfiguration() {
      return this._hadoopConfiguration;
   }

   private void _hadoopConfiguration_$eq(final Configuration x$1) {
      this._hadoopConfiguration = x$1;
   }

   private int _executorMemory() {
      return this._executorMemory;
   }

   private void _executorMemory_$eq(final int x$1) {
      this._executorMemory = x$1;
   }

   private SchedulerBackend _schedulerBackend() {
      return this._schedulerBackend;
   }

   private void _schedulerBackend_$eq(final SchedulerBackend x$1) {
      this._schedulerBackend = x$1;
   }

   private TaskScheduler _taskScheduler() {
      return this._taskScheduler;
   }

   private void _taskScheduler_$eq(final TaskScheduler x$1) {
      this._taskScheduler = x$1;
   }

   private RpcEndpointRef _heartbeatReceiver() {
      return this._heartbeatReceiver;
   }

   private void _heartbeatReceiver_$eq(final RpcEndpointRef x$1) {
      this._heartbeatReceiver = x$1;
   }

   private DAGScheduler _dagScheduler() {
      return this._dagScheduler;
   }

   private void _dagScheduler_$eq(final DAGScheduler x$1) {
      this._dagScheduler = x$1;
   }

   private String _applicationId() {
      return this._applicationId;
   }

   private void _applicationId_$eq(final String x$1) {
      this._applicationId = x$1;
   }

   private Option _applicationAttemptId() {
      return this._applicationAttemptId;
   }

   private void _applicationAttemptId_$eq(final Option x$1) {
      this._applicationAttemptId = x$1;
   }

   private Option _eventLogger() {
      return this._eventLogger;
   }

   private void _eventLogger_$eq(final Option x$1) {
      this._eventLogger = x$1;
   }

   private Option _driverLogger() {
      return this._driverLogger;
   }

   private void _driverLogger_$eq(final Option x$1) {
      this._driverLogger = x$1;
   }

   private Option _executorAllocationManager() {
      return this._executorAllocationManager;
   }

   private void _executorAllocationManager_$eq(final Option x$1) {
      this._executorAllocationManager = x$1;
   }

   private Option _cleaner() {
      return this._cleaner;
   }

   private void _cleaner_$eq(final Option x$1) {
      this._cleaner = x$1;
   }

   private boolean _listenerBusStarted() {
      return this._listenerBusStarted;
   }

   private void _listenerBusStarted_$eq(final boolean x$1) {
      this._listenerBusStarted = x$1;
   }

   private Seq _jars() {
      return this._jars;
   }

   private void _jars_$eq(final Seq x$1) {
      this._jars = x$1;
   }

   private Seq _files() {
      return this._files;
   }

   private void _files_$eq(final Seq x$1) {
      this._files = x$1;
   }

   private Seq _archives() {
      return this._archives;
   }

   private void _archives_$eq(final Seq x$1) {
      this._archives = x$1;
   }

   private Object _shutdownHookRef() {
      return this._shutdownHookRef;
   }

   private void _shutdownHookRef_$eq(final Object x$1) {
      this._shutdownHookRef = x$1;
   }

   private AppStatusStore _statusStore() {
      return this._statusStore;
   }

   private void _statusStore_$eq(final AppStatusStore x$1) {
      this._statusStore = x$1;
   }

   private Heartbeater _heartbeater() {
      return this._heartbeater;
   }

   private void _heartbeater_$eq(final Heartbeater x$1) {
      this._heartbeater = x$1;
   }

   private Map _resources() {
      return this._resources;
   }

   private void _resources_$eq(final Map x$1) {
      this._resources = x$1;
   }

   private ShuffleDriverComponents _shuffleDriverComponents() {
      return this._shuffleDriverComponents;
   }

   private void _shuffleDriverComponents_$eq(final ShuffleDriverComponents x$1) {
      this._shuffleDriverComponents = x$1;
   }

   private Option _plugins() {
      return this._plugins;
   }

   private void _plugins_$eq(final Option x$1) {
      this._plugins = x$1;
   }

   private ResourceProfileManager _resourceProfileManager() {
      return this._resourceProfileManager;
   }

   private void _resourceProfileManager_$eq(final ResourceProfileManager x$1) {
      this._resourceProfileManager = x$1;
   }

   public SparkConf conf() {
      return this._conf();
   }

   public ReadOnlySparkConf getReadOnlyConf() {
      return this._conf();
   }

   public SparkConf getConf() {
      return this.conf().clone();
   }

   public scala.collection.Map resources() {
      return this._resources();
   }

   public Seq jars() {
      return this._jars();
   }

   public Seq files() {
      return this._files();
   }

   public Seq archives() {
      return this._archives();
   }

   public String master() {
      return this._conf().get("spark.master");
   }

   public String deployMode() {
      return (String)this._conf().get(org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE());
   }

   public String appName() {
      return this._conf().get("spark.app.name");
   }

   public boolean isEventLogEnabled() {
      return BoxesRunTime.unboxToBoolean(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_ENABLED()));
   }

   public Option eventLogDir() {
      return this._eventLogDir();
   }

   public Option eventLogCodec() {
      return this._eventLogCodec();
   }

   public boolean isLocal() {
      return Utils$.MODULE$.isLocalMaster(this._conf());
   }

   public boolean isStopped() {
      return this.stopped().get();
   }

   public AppStatusStore statusStore() {
      return this._statusStore();
   }

   public LiveListenerBus listenerBus() {
      return this._listenerBus();
   }

   public SparkEnv createSparkEnv(final SparkConf conf, final boolean isLocal, final LiveListenerBus listenerBus) {
      return SparkEnv$.MODULE$.createDriverEnv(conf, isLocal, listenerBus, SparkContext$.MODULE$.numDriverCores(this.master(), conf), SparkEnv$.MODULE$.createDriverEnv$default$5());
   }

   public SparkEnv env() {
      return this._env();
   }

   public scala.collection.concurrent.Map addedFiles() {
      return this.addedFiles;
   }

   public scala.collection.concurrent.Map addedArchives() {
      return this.addedArchives;
   }

   public scala.collection.concurrent.Map addedJars() {
      return this.addedJars;
   }

   public Map allAddedFiles() {
      return ((IterableOnceOps)this.addedFiles().values().flatten(scala.Predef..MODULE$.$conforms())).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Map allAddedArchives() {
      return ((IterableOnceOps)this.addedArchives().values().flatten(scala.Predef..MODULE$.$conforms())).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Map allAddedJars() {
      return ((IterableOnceOps)this.addedJars().values().flatten(scala.Predef..MODULE$.$conforms())).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public scala.collection.concurrent.Map persistentRdds() {
      return this.persistentRdds;
   }

   public SparkStatusTracker statusTracker() {
      return this._statusTracker();
   }

   public Option progressBar() {
      return this._progressBar();
   }

   public Option ui() {
      return this._ui();
   }

   public Option uiWebUrl() {
      return this._ui().map((x$21) -> x$21.webUrl());
   }

   public Configuration hadoopConfiguration() {
      return this._hadoopConfiguration();
   }

   public int executorMemory() {
      return this._executorMemory();
   }

   public HashMap executorEnvs() {
      return this.executorEnvs;
   }

   public String sparkUser() {
      return this.sparkUser;
   }

   public SchedulerBackend schedulerBackend() {
      return this._schedulerBackend();
   }

   public TaskScheduler taskScheduler() {
      return this._taskScheduler();
   }

   public void taskScheduler_$eq(final TaskScheduler ts) {
      this._taskScheduler_$eq(ts);
   }

   public DAGScheduler dagScheduler() {
      return this._dagScheduler();
   }

   public void dagScheduler_$eq(final DAGScheduler ds) {
      this._dagScheduler_$eq(ds);
   }

   public ShuffleDriverComponents shuffleDriverComponents() {
      return this._shuffleDriverComponents();
   }

   public String applicationId() {
      return this._applicationId();
   }

   public Option applicationAttemptId() {
      return this._applicationAttemptId();
   }

   public Option eventLogger() {
      return this._eventLogger();
   }

   public Option executorAllocationManager() {
      return this._executorAllocationManager();
   }

   public ResourceProfileManager resourceProfileManager() {
      return this._resourceProfileManager();
   }

   public Option cleaner() {
      return this._cleaner();
   }

   public Option checkpointDir() {
      return this.checkpointDir;
   }

   public void checkpointDir_$eq(final Option x$1) {
      this.checkpointDir = x$1;
   }

   public InheritableThreadLocal localProperties() {
      return this.localProperties;
   }

   public void setLogLevel(final String logLevel) {
      String upperCased = logLevel.toUpperCase(Locale.ROOT);
      scala.Predef..MODULE$.require(SparkContext$.MODULE$.VALID_LOG_LEVELS().contains(upperCased), () -> "Supplied level " + logLevel + " did not match one of: " + SparkContext$.MODULE$.VALID_LOG_LEVELS().mkString(","));
      Utils$.MODULE$.setLogLevelIfNeeded(upperCased);
      if (BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ALLOW_SYNC_LOG_LEVEL())) && this._schedulerBackend() != null) {
         this._schedulerBackend().updateExecutorsLogLevel(upperCased);
      }
   }

   public Option getExecutorThreadDump(final String executorId) {
      Object var10000;
      try {
         label36: {
            String var3 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (executorId == null) {
               if (var3 == null) {
                  break label36;
               }
            } else if (executorId.equals(var3)) {
               break label36;
            }

            Option var4 = this.env().blockManager().master().getExecutorEndpointRef(executorId);
            if (var4 instanceof Some var5) {
               RpcEndpointRef endpointRef = (RpcEndpointRef)var5.value();
               var10000 = new Some(endpointRef.askSync(BlockManagerMessages.TriggerThreadDump$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ThreadStackTrace.class))));
            } else {
               if (!scala.None..MODULE$.equals(var4)) {
                  throw new MatchError(var4);
               }

               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"might already have stopped and can not request thread dump from it."})))).log(scala.collection.immutable.Nil..MODULE$))));
               var10000 = scala.None..MODULE$;
            }

            return (Option)var10000;
         }

         var10000 = new Some(Utils$.MODULE$.getThreadDump());
      } catch (Exception var8) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception getting thread dump from executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))), var8);
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public Option getTaskThreadDump(final long taskId, final String executorId) {
      return this.schedulerBackend().getTaskThreadDump(taskId, executorId);
   }

   public Option getExecutorHeapHistogram(final String executorId) {
      Object var10000;
      try {
         label36: {
            String var3 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (executorId == null) {
               if (var3 == null) {
                  break label36;
               }
            } else if (executorId.equals(var3)) {
               break label36;
            }

            Option var4 = this.env().blockManager().master().getExecutorEndpointRef(executorId);
            if (var4 instanceof Some var5) {
               RpcEndpointRef endpointRef = (RpcEndpointRef)var5.value();
               var10000 = new Some(endpointRef.askSync(BlockManagerMessages.TriggerHeapHistogram$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class))));
            } else {
               if (!scala.None..MODULE$.equals(var4)) {
                  throw new MatchError(var4);
               }

               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"might already have stopped and can not request heap histogram from it."})))).log(scala.collection.immutable.Nil..MODULE$))));
               var10000 = scala.None..MODULE$;
            }

            return (Option)var10000;
         }

         var10000 = new Some(Utils$.MODULE$.getHeapHistogram());
      } catch (Exception var8) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception getting heap histogram from "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))), var8);
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public Properties getLocalProperties() {
      return (Properties)this.localProperties().get();
   }

   public void setLocalProperties(final Properties props) {
      this.localProperties().set(props);
   }

   public void setLocalProperty(final String key, final String value) {
      if (value == null) {
         ((Properties)this.localProperties().get()).remove(key);
      } else {
         ((Properties)this.localProperties().get()).setProperty(key, value);
      }
   }

   public String getLocalProperty(final String key) {
      return (String)scala.Option..MODULE$.apply(this.localProperties().get()).map((x$36) -> x$36.getProperty(key)).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public void setJobDescription(final String value) {
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION(), value);
   }

   public void setJobGroup(final String groupId, final String description, final boolean interruptOnCancel) {
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION(), description);
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_GROUP_ID(), groupId);
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_INTERRUPT_ON_CANCEL(), Boolean.toString(interruptOnCancel));
   }

   public boolean setJobGroup$default$3() {
      return false;
   }

   public void clearJobGroup() {
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION(), (String)null);
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_GROUP_ID(), (String)null);
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_INTERRUPT_ON_CANCEL(), (String)null);
   }

   public void setInterruptOnCancel(final boolean interruptOnCancel) {
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_INTERRUPT_ON_CANCEL(), Boolean.toString(interruptOnCancel));
   }

   public void addJobTag(final String tag) {
      this.addJobTags((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{tag}))));
   }

   public void addJobTags(final Set tags) {
      tags.foreach((tag) -> {
         $anonfun$addJobTags$1(tag);
         return BoxedUnit.UNIT;
      });
      Set existingTags = this.getJobTags();
      String newTags = existingTags.$plus$plus(tags).mkString(SparkContext$.MODULE$.SPARK_JOB_TAGS_SEP());
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_TAGS(), newTags);
   }

   public void removeJobTag(final String tag) {
      this.removeJobTags((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{tag}))));
   }

   public void removeJobTags(final Set tags) {
      tags.foreach((tag) -> {
         $anonfun$removeJobTags$1(tag);
         return BoxedUnit.UNIT;
      });
      Set existingTags = this.getJobTags();
      String newTags = existingTags.$minus$minus(tags).mkString(SparkContext$.MODULE$.SPARK_JOB_TAGS_SEP());
      if (newTags.isEmpty()) {
         this.clearJobTags();
      } else {
         this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_TAGS(), newTags);
      }
   }

   public Set getJobTags() {
      return (Set)((IterableOps)scala.Option..MODULE$.apply(this.getLocalProperty(SparkContext$.MODULE$.SPARK_JOB_TAGS())).map((x$37) -> scala.Predef..MODULE$.wrapRefArray((Object[])x$37.split(SparkContext$.MODULE$.SPARK_JOB_TAGS_SEP())).toSet()).getOrElse(() -> (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$))).filter((x$38) -> BoxesRunTime.boxToBoolean($anonfun$getJobTags$3(x$38)));
   }

   public void clearJobTags() {
      this.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_TAGS(), (String)null);
   }

   public Object withScope(final Function0 body) {
      return RDDOperationScope$.MODULE$.withScope(this, RDDOperationScope$.MODULE$.withScope$default$2(), body);
   }

   public RDD parallelize(final Seq seq, final int numSlices, final ClassTag evidence$1) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         return new ParallelCollectionRDD(this, seq, numSlices, (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$), evidence$1);
      });
   }

   public int parallelize$default$2() {
      return this.defaultParallelism();
   }

   public RDD range(final long start, final long end, final long step, final int numSlices) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         scala.Predef..MODULE$.require(step != 0L, () -> "step cannot be 0");
         BigInt safeStart = scala.package..MODULE$.BigInt().apply(start);
         BigInt safeEnd = scala.package..MODULE$.BigInt().apply(end);
         BigInt numElements = !BoxesRunTime.equalsNumObject(safeEnd.$minus(safeStart).$percent(scala.math.BigInt..MODULE$.long2bigInt(step)), BoxesRunTime.boxToInteger(0)) && safeEnd.$greater(safeStart) == step > 0L ? safeEnd.$minus(safeStart).$div(scala.math.BigInt..MODULE$.long2bigInt(step)).$plus(scala.math.BigInt..MODULE$.int2bigInt(1)) : safeEnd.$minus(safeStart).$div(scala.math.BigInt..MODULE$.long2bigInt(step));
         RDD qual$1 = this.parallelize(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numSlices), numSlices, scala.reflect.ClassTag..MODULE$.Int());
         Function2 x$1 = (i, x$39) -> $anonfun$range$3(numElements, numSlices, step, start, BoxesRunTime.unboxToInt(i), x$39);
         boolean x$2 = qual$1.mapPartitionsWithIndex$default$2();
         return qual$1.mapPartitionsWithIndex(x$1, x$2, scala.reflect.ClassTag..MODULE$.Long());
      });
   }

   public long range$default$3() {
      return 1L;
   }

   public int range$default$4() {
      return this.defaultParallelism();
   }

   public RDD makeRDD(final Seq seq, final int numSlices, final ClassTag evidence$2) {
      return (RDD)this.withScope(() -> this.parallelize(seq, numSlices, evidence$2));
   }

   public RDD makeRDD(final Seq seq, final ClassTag evidence$3) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         Map indexToPrefs = ((IterableOnceOps)((IterableOps)seq.zipWithIndex()).map((t) -> new Tuple2(BoxesRunTime.boxToInteger(t._2$mcI$sp()), ((Tuple2)t._1())._2()))).toMap(scala..less.colon.less..MODULE$.refl());
         return new ParallelCollectionRDD(this, (Seq)seq.map((x$40) -> x$40._1()), scala.math.package..MODULE$.max(seq.size(), 1), indexToPrefs, evidence$3);
      });
   }

   public int makeRDD$default$2() {
      return this.defaultParallelism();
   }

   public RDD textFile(final String path, final int minPartitions) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         return this.hadoopFile(path, TextInputFormat.class, LongWritable.class, Text.class, minPartitions).map((pair) -> ((Text)pair._2()).toString(), scala.reflect.ClassTag..MODULE$.apply(String.class)).setName(path);
      });
   }

   public int textFile$default$2() {
      return this.defaultMinPartitions();
   }

   public RDD wholeTextFiles(final String path, final int minPartitions) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         Job job = Job.getInstance(this.hadoopConfiguration());
         FileInputFormat.setInputPaths(job, path);
         Configuration updateConf = job.getConfiguration();
         return (new WholeTextFileRDD(this, WholeTextFileInputFormat.class, Text.class, Text.class, updateConf, minPartitions)).map((record) -> new Tuple2(((Text)record._1()).toString(), ((Text)record._2()).toString()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName(path);
      });
   }

   public int wholeTextFiles$default$2() {
      return this.defaultMinPartitions();
   }

   public RDD binaryFiles(final String path, final int minPartitions) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         Job job = Job.getInstance(this.hadoopConfiguration());
         FileInputFormat.setInputPaths(job, path);
         Configuration updateConf = job.getConfiguration();
         return (BinaryFileRDD)(new BinaryFileRDD(this, StreamInputFormat.class, String.class, PortableDataStream.class, updateConf, minPartitions)).setName(path);
      });
   }

   public int binaryFiles$default$2() {
      return this.defaultMinPartitions();
   }

   public RDD binaryRecords(final String path, final int recordLength, final Configuration conf) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         conf.setInt(FixedLengthBinaryInputFormat$.MODULE$.RECORD_LENGTH_PROPERTY(), recordLength);
         RDD br = this.newAPIHadoopFile(path, FixedLengthBinaryInputFormat.class, LongWritable.class, BytesWritable.class, conf);
         return br.map((x0$1) -> {
            if (x0$1 != null) {
               BytesWritable v = (BytesWritable)x0$1._2();
               byte[] bytes = v.copyBytes();
               scala.Predef..MODULE$.assert(bytes.length == recordLength, () -> "Byte array does not have correct length");
               return bytes;
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      });
   }

   public Configuration binaryRecords$default$3() {
      return this.hadoopConfiguration();
   }

   public RDD hadoopRDD(final JobConf conf, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         FileSystem.getLocal(conf);
         SparkHadoopUtil$.MODULE$.get().addCredentials(conf);
         return new HadoopRDD(this, conf, inputFormatClass, keyClass, valueClass, minPartitions);
      });
   }

   public int hadoopRDD$default$5() {
      return this.defaultMinPartitions();
   }

   public RDD hadoopFile(final String path, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         FileSystem.getLocal(this.hadoopConfiguration());
         Broadcast confBroadcast = this.broadcast(new SerializableConfiguration(this.hadoopConfiguration()), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
         Function1 setInputPathsFunc = (jobConf) -> {
            $anonfun$hadoopFile$2(path, jobConf);
            return BoxedUnit.UNIT;
         };
         return (HadoopRDD)(new HadoopRDD(this, confBroadcast, new Some(setInputPathsFunc), inputFormatClass, keyClass, valueClass, minPartitions)).setName(path);
      });
   }

   public RDD hadoopFile(final String path, final int minPartitions, final ClassTag km, final ClassTag vm, final ClassTag fm) {
      return (RDD)this.withScope(() -> this.hadoopFile(path, fm.runtimeClass(), km.runtimeClass(), vm.runtimeClass(), minPartitions));
   }

   public RDD hadoopFile(final String path, final ClassTag km, final ClassTag vm, final ClassTag fm) {
      return (RDD)this.withScope(() -> this.hadoopFile(path, this.defaultMinPartitions(), km, vm, fm));
   }

   public int hadoopFile$default$5() {
      return this.defaultMinPartitions();
   }

   public RDD newAPIHadoopFile(final String path, final ClassTag km, final ClassTag vm, final ClassTag fm) {
      return (RDD)this.withScope(() -> this.newAPIHadoopFile(path, fm.runtimeClass(), km.runtimeClass(), vm.runtimeClass(), this.newAPIHadoopFile$default$5()));
   }

   public RDD newAPIHadoopFile(final String path, final Class fClass, final Class kClass, final Class vClass, final Configuration conf) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         FileSystem.getLocal(this.hadoopConfiguration());
         Job job = Job.getInstance(conf);
         FileInputFormat.setInputPaths(job, path);
         Configuration updatedConf = job.getConfiguration();
         return (NewHadoopRDD)(new NewHadoopRDD(this, fClass, kClass, vClass, updatedConf)).setName(path);
      });
   }

   public Configuration newAPIHadoopFile$default$5() {
      return this.hadoopConfiguration();
   }

   public RDD newAPIHadoopRDD(final Configuration conf, final Class fClass, final Class kClass, final Class vClass) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         FileSystem.getLocal(conf);
         JobConf jconf = new JobConf(conf);
         SparkHadoopUtil$.MODULE$.get().addCredentials(jconf);
         return new NewHadoopRDD(this, fClass, kClass, vClass, jconf);
      });
   }

   public Configuration newAPIHadoopRDD$default$1() {
      return this.hadoopConfiguration();
   }

   public RDD sequenceFile(final String path, final Class keyClass, final Class valueClass, final int minPartitions) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         Class inputFormatClass = SequenceFileInputFormat.class;
         return this.hadoopFile(path, inputFormatClass, keyClass, valueClass, minPartitions);
      });
   }

   public RDD sequenceFile(final String path, final Class keyClass, final Class valueClass) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         return this.sequenceFile(path, keyClass, valueClass, this.defaultMinPartitions());
      });
   }

   public RDD sequenceFile(final String path, final int minPartitions, final ClassTag km, final ClassTag vm, final Function0 kcf, final Function0 vcf) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         WritableConverter kc = (WritableConverter)((Function0)this.clean(kcf, this.clean$default$2())).apply();
         WritableConverter vc = (WritableConverter)((Function0)this.clean(vcf, this.clean$default$2())).apply();
         Class format = SequenceFileInputFormat.class;
         RDD writables = this.hadoopFile(path, format, (Class)kc.writableClass().apply(km), (Class)vc.writableClass().apply(vm), minPartitions);
         return writables.map((x0$1) -> {
            if (x0$1 != null) {
               Writable k = (Writable)x0$1._1();
               Writable v = (Writable)x0$1._2();
               return new Tuple2(kc.convert().apply(k), vc.convert().apply(v));
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      });
   }

   public int sequenceFile$default$2() {
      return this.defaultMinPartitions();
   }

   public RDD objectFile(final String path, final int minPartitions, final ClassTag evidence$4) {
      return (RDD)this.withScope(() -> {
         this.assertNotStopped();
         return this.sequenceFile(path, NullWritable.class, BytesWritable.class, minPartitions).flatMap((x) -> scala.Predef..MODULE$.genericWrapArray(Utils$.MODULE$.deserialize(((BytesWritable)x._2()).getBytes(), Utils$.MODULE$.getContextOrSparkClassLoader())), evidence$4);
      });
   }

   public int objectFile$default$2() {
      return this.defaultMinPartitions();
   }

   public RDD checkpointFile(final String path, final ClassTag evidence$5) {
      return (RDD)this.withScope(() -> new ReliableCheckpointRDD(this, path, ReliableCheckpointRDD$.MODULE$.$lessinit$greater$default$3(), evidence$5));
   }

   public RDD union(final Seq rdds, final ClassTag evidence$6) {
      return (RDD)this.withScope(() -> {
         Seq nonEmptyRdds = (Seq)rdds.filter((x$41) -> BoxesRunTime.boxToBoolean($anonfun$union$2(x$41)));
         Set partitioners = ((IterableOnceOps)nonEmptyRdds.flatMap((x$42) -> x$42.partitioner())).toSet();
         return (RDD)(nonEmptyRdds.forall((x$43) -> BoxesRunTime.boxToBoolean($anonfun$union$4(x$43))) && partitioners.size() == 1 ? new PartitionerAwareUnionRDD(this, nonEmptyRdds, evidence$6) : new UnionRDD(this, nonEmptyRdds, evidence$6));
      });
   }

   public RDD union(final RDD first, final Seq rest, final ClassTag evidence$7) {
      return (RDD)this.withScope(() -> this.union((Seq)(new scala.collection.immutable..colon.colon(first, scala.collection.immutable.Nil..MODULE$)).$plus$plus(rest), evidence$7));
   }

   public RDD emptyRDD(final ClassTag evidence$8) {
      return new EmptyRDD(this, evidence$8);
   }

   public void register(final AccumulatorV2 acc) {
      acc.register(this, acc.register$default$2(), acc.register$default$3());
   }

   public void register(final AccumulatorV2 acc, final String name) {
      acc.register(this, scala.Option..MODULE$.apply(name), acc.register$default$3());
   }

   public LongAccumulator longAccumulator() {
      LongAccumulator acc = new LongAccumulator();
      this.register(acc);
      return acc;
   }

   public LongAccumulator longAccumulator(final String name) {
      LongAccumulator acc = new LongAccumulator();
      this.register(acc, name);
      return acc;
   }

   public DoubleAccumulator doubleAccumulator() {
      DoubleAccumulator acc = new DoubleAccumulator();
      this.register(acc);
      return acc;
   }

   public DoubleAccumulator doubleAccumulator(final String name) {
      DoubleAccumulator acc = new DoubleAccumulator();
      this.register(acc, name);
      return acc;
   }

   public CollectionAccumulator collectionAccumulator() {
      CollectionAccumulator acc = new CollectionAccumulator();
      this.register(acc);
      return acc;
   }

   public CollectionAccumulator collectionAccumulator(final String name) {
      CollectionAccumulator acc = new CollectionAccumulator();
      this.register(acc, name);
      return acc;
   }

   public Broadcast broadcast(final Object value, final ClassTag evidence$9) {
      return this.broadcastInternal(value, false, evidence$9);
   }

   public Broadcast broadcastInternal(final Object value, final boolean serializedOnly, final ClassTag evidence$10) {
      this.assertNotStopped();
      scala.Predef..MODULE$.require(!RDD.class.isAssignableFrom(scala.reflect.package..MODULE$.classTag(evidence$10).runtimeClass()), () -> "Can not directly broadcast RDDs; instead, call collect() and broadcast the result.");
      Broadcast bc = this.env().broadcastManager().newBroadcast(value, this.isLocal(), serializedOnly, evidence$10);
      CallSite callSite = this.getCallSite();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created broadcast ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, BoxesRunTime.boxToLong(bc.id()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())}))))));
      this.cleaner().foreach((x$44) -> {
         $anonfun$broadcastInternal$3(bc, x$44);
         return BoxedUnit.UNIT;
      });
      return bc;
   }

   public void addFile(final String path) {
      this.addFile(path, false, false, this.addFile$default$4());
   }

   public Seq listFiles() {
      return this.allAddedFiles().keySet().toSeq();
   }

   @Experimental
   public void addArchive(final String path) {
      this.addFile(path, false, false, true);
   }

   @Experimental
   public Seq listArchives() {
      return this.allAddedArchives().keySet().toSeq();
   }

   public void addFile(final String path, final boolean recursive) {
      this.addFile(path, recursive, false, this.addFile$default$4());
   }

   private void addFile(final String path, final boolean recursive, final boolean addedOnSubmit, final boolean isArchive) {
      LazyRef root$lzy;
      String jobArtifactUUID;
      URI uri;
      URI var10000;
      label111: {
         root$lzy = new LazyRef();
         jobArtifactUUID = (String)JobArtifactSet$.MODULE$.getCurrentJobArtifactState().map((x$45) -> x$45.uuid()).getOrElse(() -> "default");
         uri = Utils$.MODULE$.resolveURI(path);
         String var10 = uri.getScheme();
         switch (var10 == null ? 0 : var10.hashCode()) {
            case 0:
               if (var10 == null) {
                  var10000 = (new File(path)).getCanonicalFile().toURI();
                  break label111;
               }
               break;
            case 103145323:
               if ("local".equals(var10)) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"File with 'local' scheme ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is not supported to add to file server, "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"since it is already available on every node."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  return;
               }
         }

         var10000 = uri;
      }

      URI schemeCorrectedURI = var10000;
      Path hadoopPath = new Path(schemeCorrectedURI);
      String scheme = schemeCorrectedURI.getScheme();
      if (!scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"http", "https", "ftp", "spark"})), scheme) && !isArchive) {
         FileSystem fs = hadoopPath.getFileSystem(this.hadoopConfiguration());
         boolean isDir = fs.getFileStatus(hadoopPath).isDirectory();
         if (!this.isLocal()) {
            label117: {
               String var15 = "file";
               if (scheme == null) {
                  if (var15 != null) {
                     break label117;
                  }
               } else if (!scheme.equals(var15)) {
                  break label117;
               }

               if (isDir) {
                  throw SparkCoreErrors$.MODULE$.addLocalDirectoryError(hadoopPath);
               }
            }
         }

         if (!recursive && isDir) {
            throw SparkCoreErrors$.MODULE$.addDirectoryError(hadoopPath);
         }
      } else {
         Utils$.MODULE$.validateURL(uri);
      }

      label91: {
         label90: {
            if (!this.isLocal()) {
               String var17 = "file";
               if (scheme == null) {
                  if (var17 == null) {
                     break label90;
                  }
               } else if (scheme.equals(var17)) {
                  break label90;
               }
            }

            var25 = uri.getScheme() == null ? schemeCorrectedURI.toString() : uri.toString();
            break label91;
         }

         var25 = this.env().rpcEnv().fileServer().addFile(new File(uri.getPath()));
      }

      String key = var25;
      long timestamp = addedOnSubmit ? this.startTime() : System.currentTimeMillis();
      if (!isArchive && ((scala.collection.concurrent.Map)this.addedFiles().getOrElseUpdate(jobArtifactUUID, () -> scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala())).putIfAbsent(key, BoxesRunTime.boxToLong(timestamp)).isEmpty()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added file ", " at ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path), new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, key)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" timestamp ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMESTAMP..MODULE$, BoxesRunTime.boxToLong(timestamp))}))))));
         Utils$.MODULE$.fetchFile(uri.toString(), root$1(root$lzy, jobArtifactUUID), this.conf(), this.hadoopConfiguration(), timestamp, false, Utils$.MODULE$.fetchFile$default$7());
         this.postEnvironmentUpdate();
      } else if (isArchive && ((scala.collection.concurrent.Map)this.addedArchives().getOrElseUpdate(jobArtifactUUID, () -> scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala())).putIfAbsent(Utils$.MODULE$.getUriBuilder(new URI(key)).fragment(uri.getFragment()).build(new Object[0]).toString(), BoxesRunTime.boxToLong(timestamp)).isEmpty()) {
         label78: {
            label77: {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added archive ", " at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path), new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, key)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with timestamp ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMESTAMP..MODULE$, BoxesRunTime.boxToLong(timestamp))}))))));
               if (!this.isLocal()) {
                  String var21 = "file";
                  if (scheme == null) {
                     if (var21 == null) {
                        break label77;
                     }
                  } else if (scheme.equals(var21)) {
                     break label77;
                  }
               }

               var10000 = new URI(key);
               break label78;
            }

            var10000 = uri;
         }

         URI uriToUse = var10000;
         URI uriToDownload = Utils$.MODULE$.getUriBuilder(uriToUse).fragment((String)null).build(new Object[0]);
         File source = Utils$.MODULE$.fetchFile(uriToDownload.toString(), Utils$.MODULE$.createTempDir(), this.conf(), this.hadoopConfiguration(), timestamp, false, false);
         File dest = new File(root$1(root$lzy, jobArtifactUUID), uri.getFragment() != null ? uri.getFragment() : source.getName());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unpacking an archive ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (", " bytes)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BYTE_SIZE..MODULE$, BoxesRunTime.boxToLong(source.length()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SOURCE_PATH..MODULE$, source.getAbsolutePath())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESTINATION_PATH..MODULE$, dest.getAbsolutePath())}))))));
         Utils$.MODULE$.deleteRecursively(dest);
         Utils$.MODULE$.unpack(source, dest);
         this.postEnvironmentUpdate();
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The path ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"has been added already. Overwriting of added paths "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is not supported in the current version."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   private boolean addFile$default$4() {
      return false;
   }

   @DeveloperApi
   public void addSparkListener(final SparkListenerInterface listener) {
      this.listenerBus().addToSharedQueue(listener);
   }

   @DeveloperApi
   public void removeSparkListener(final SparkListenerInterface listener) {
      this.listenerBus().removeListener(listener);
   }

   public Seq getExecutorIds() {
      SchedulerBackend var2 = this.schedulerBackend();
      if (var2 instanceof ExecutorAllocationClient) {
         return ((ExecutorAllocationClient)var2).getExecutorIds();
      } else {
         this.logWarning((Function0)(() -> "Requesting executors is not supported by current scheduler."));
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   public int maxNumConcurrentTasks(final ResourceProfile rp) {
      return this.schedulerBackend().maxNumConcurrentTasks(rp);
   }

   @DeveloperApi
   public boolean requestTotalExecutors(final int numExecutors, final int localityAwareTasks, final Map hostToLocalTaskCount) {
      SchedulerBackend var5 = this.schedulerBackend();
      if (var5 instanceof ExecutorAllocationClient) {
         int defaultProfId = this.resourceProfileManager().defaultResourceProfile().id();
         return ((ExecutorAllocationClient)var5).requestTotalExecutors((Map)scala.collection.immutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(defaultProfId)), BoxesRunTime.boxToInteger(numExecutors))}))), (Map)scala.collection.immutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(localityAwareTasks)), BoxesRunTime.boxToInteger(defaultProfId))}))), (Map)scala.collection.immutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(defaultProfId)), hostToLocalTaskCount)}))));
      } else {
         this.logWarning((Function0)(() -> "Requesting executors is not supported by current scheduler."));
         return false;
      }
   }

   @DeveloperApi
   public boolean requestExecutors(final int numAdditionalExecutors) {
      SchedulerBackend var3 = this.schedulerBackend();
      if (var3 instanceof ExecutorAllocationClient) {
         return ((ExecutorAllocationClient)var3).requestExecutors(numAdditionalExecutors);
      } else {
         this.logWarning((Function0)(() -> "Requesting executors is not supported by current scheduler."));
         return false;
      }
   }

   @DeveloperApi
   public boolean killExecutors(final Seq executorIds) {
      SchedulerBackend var3 = this.schedulerBackend();
      if (var3 instanceof ExecutorAllocationClient) {
         scala.Predef..MODULE$.require(this.executorAllocationManager().isEmpty(), () -> "killExecutors() unsupported with Dynamic Allocation turned on");
         return ((ExecutorAllocationClient)var3).killExecutors(executorIds, true, false, true).nonEmpty();
      } else {
         this.logWarning((Function0)(() -> "Killing executors is not supported by current scheduler."));
         return false;
      }
   }

   @DeveloperApi
   public boolean killExecutor(final String executorId) {
      return this.killExecutors(new scala.collection.immutable..colon.colon(executorId, scala.collection.immutable.Nil..MODULE$));
   }

   public boolean killAndReplaceExecutor(final String executorId) {
      SchedulerBackend var3 = this.schedulerBackend();
      if (var3 instanceof ExecutorAllocationClient) {
         return ((ExecutorAllocationClient)var3).killExecutors(new scala.collection.immutable..colon.colon(executorId, scala.collection.immutable.Nil..MODULE$), false, true, true).nonEmpty();
      } else {
         this.logWarning((Function0)(() -> "Killing executors is not supported by current scheduler."));
         return false;
      }
   }

   public String version() {
      return package$.MODULE$.SPARK_VERSION();
   }

   public scala.collection.Map getExecutorMemoryStatus() {
      this.assertNotStopped();
      return (scala.collection.Map)this.env().blockManager().master().getMemoryStatus().map((x0$1) -> {
         if (x0$1 != null) {
            BlockManagerId blockManagerId = (BlockManagerId)x0$1._1();
            Tuple2 mem = (Tuple2)x0$1._2();
            return new Tuple2(blockManagerId.host() + ":" + blockManagerId.port(), mem);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   @DeveloperApi
   public RDDInfo[] getRDDStorageInfo() {
      return this.getRDDStorageInfo((x$46) -> BoxesRunTime.boxToBoolean($anonfun$getRDDStorageInfo$1(x$46)));
   }

   public RDDInfo[] getRDDStorageInfo(final Function1 filter) {
      this.assertNotStopped();
      RDDInfo[] rddInfos = (RDDInfo[])((IterableOnceOps)((IterableOps)this.persistentRdds().values().filter(filter)).map((rdd) -> RDDInfo$.MODULE$.fromRdd(rdd))).toArray(scala.reflect.ClassTag..MODULE$.apply(RDDInfo.class));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(rddInfos), (rddInfo) -> {
         $anonfun$getRDDStorageInfo$3(this, rddInfo);
         return BoxedUnit.UNIT;
      });
      return (RDDInfo[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(rddInfos), (x$50) -> BoxesRunTime.boxToBoolean($anonfun$getRDDStorageInfo$11(x$50)));
   }

   public scala.collection.Map getPersistentRDDs() {
      return this.persistentRdds().toMap(scala..less.colon.less..MODULE$.refl());
   }

   @DeveloperApi
   public Seq getAllPools() {
      this.assertNotStopped();
      return scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.taskScheduler().rootPool().schedulableQueue()).asScala().toSeq();
   }

   @DeveloperApi
   public Option getPoolForName(final String pool) {
      this.assertNotStopped();
      return scala.Option..MODULE$.apply(this.taskScheduler().rootPool().schedulableNameToSchedulable().get(pool));
   }

   public Enumeration.Value getSchedulingMode() {
      this.assertNotStopped();
      return this.taskScheduler().schedulingMode();
   }

   public Seq getPreferredLocs(final RDD rdd, final int partition) {
      return this.dagScheduler().getPreferredLocs(rdd, partition);
   }

   public void persistRDD(final RDD rdd) {
      this.persistentRdds().update(BoxesRunTime.boxToInteger(rdd.id()), rdd);
   }

   public void unpersistRDD(final int rddId, final boolean blocking) {
      this.env().blockManager().master().removeRdd(rddId, blocking);
      this.persistentRdds().remove(BoxesRunTime.boxToInteger(rddId));
      this.listenerBus().post(new SparkListenerUnpersistRDD(rddId));
   }

   public void addJar(final String path) {
      this.addJar(path, false);
   }

   private void addJar(final String path, final boolean addedOnSubmit) {
      String jobArtifactUUID = (String)JobArtifactSet$.MODULE$.getCurrentJobArtifactState().map((x$51) -> x$51.uuid()).getOrElse(() -> "default");
      if (path != null && !path.isEmpty()) {
         Tuple2 var29;
         if (path.contains("\\") && Utils$.MODULE$.isWindows()) {
            var29 = new Tuple2(this.addLocalJarFile$1(new File(path), path), "local");
         } else {
            String uriScheme;
            label96: {
               URI uri = Utils$.MODULE$.resolveURI(path);
               Utils$.MODULE$.validateURL(uri);
               uriScheme = uri.getScheme();
               switch (uriScheme == null ? 0 : uriScheme.hashCode()) {
                  case 0:
                     if (uriScheme == null) {
                        var10000 = this.addLocalJarFile$1(new File(uri.getPath()), path);
                        break label96;
                     }
                     break;
                  case 104684:
                     if ("ivy".equals(uriScheme)) {
                        var10000 = (Seq)DependencyUtils$.MODULE$.resolveMavenDependencies(URI.create(path)).flatMap((jar) -> this.addLocalJarFile$1(new File(jar), path));
                        break label96;
                     }
                     break;
                  case 3143036:
                     if ("file".equals(uriScheme)) {
                        var10000 = this.addLocalJarFile$1(new File(uri.getPath()), path);
                        break label96;
                     }
                     break;
                  case 103145323:
                     if ("local".equals(uriScheme)) {
                        var10000 = new scala.collection.immutable..colon.colon("file:" + uri.getPath(), scala.collection.immutable.Nil..MODULE$);
                        break label96;
                     }
               }

               var10000 = this.checkRemoteJarFile$1(path);
            }

            Seq jarPaths = (Seq)var10000;
            var29 = new Tuple2(jarPaths, uriScheme);
         }

         Tuple2 var8 = var29;
         if (var8 == null) {
            throw new MatchError(var8);
         } else {
            Seq keys = (Seq)var8._1();
            String scheme = (String)var8._2();
            Tuple2 var7 = new Tuple2(keys, scheme);
            Seq keys = (Seq)var7._1();
            String scheme = (String)var7._2();
            if (keys.nonEmpty()) {
               long timestamp = addedOnSubmit ? this.startTime() : System.currentTimeMillis();
               Tuple2 var20 = keys.partition((x$53) -> BoxesRunTime.boxToBoolean($anonfun$addJar$7(this, jobArtifactUUID, timestamp, x$53)));
               if (var20 == null) {
                  throw new MatchError(var20);
               } else {
                  Seq added = (Seq)var20._1();
                  Seq existed = (Seq)var20._2();
                  Tuple2 var19 = new Tuple2(added, existed);
                  Seq added = (Seq)var19._1();
                  Seq existed = (Seq)var19._2();
                  if (added.nonEmpty()) {
                     label80: {
                        label79: {
                           String var26 = "ivy";
                           if (scheme == null) {
                              if (var26 != null) {
                                 break label79;
                              }
                           } else if (!scheme.equals(var26)) {
                              break label79;
                           }

                           var30 = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added dependency jars of Ivy URI"})))).log(scala.collection.immutable.Nil..MODULE$);
                           break label80;
                        }

                        var30 = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added JAR"})))).log(scala.collection.immutable.Nil..MODULE$);
                     }

                     MessageWithContext jarMessage = var30;
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> jarMessage.$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ADDED_JARS..MODULE$, added.mkString(","))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with timestamp ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMESTAMP..MODULE$, BoxesRunTime.boxToLong(timestamp))}))))));
                     this.postEnvironmentUpdate();
                  }

                  if (existed.nonEmpty()) {
                     label71: {
                        label70: {
                           String var28 = "ivy";
                           if (scheme == null) {
                              if (var28 != null) {
                                 break label70;
                              }
                           } else if (!scheme.equals(var28)) {
                              break label70;
                           }

                           var31 = "dependency jars of Ivy URI";
                           break label71;
                        }

                        var31 = "JAR";
                     }

                     String jarMessage = var31;
                     this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The ", " ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JAR_MESSAGE..MODULE$, jarMessage), new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"at ", " has been added already."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXISTING_PATH..MODULE$, existed.mkString(","))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Overwriting of added jar is not supported in the current version."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  }
               }
            }
         }
      } else {
         this.logWarning((Function0)(() -> "null or empty path specified as parameter to addJar"));
      }
   }

   public Seq listJars() {
      return this.allAddedJars().keySet().toSeq();
   }

   public void stopInNewThread() {
      (new Thread() {
         // $FF: synthetic field
         private final SparkContext $outer;

         public void run() {
            try {
               this.$outer.stop();
            } catch (Throwable var2) {
               this.$outer.logError((Function0)(() -> var2.getMessage()), var2);
               throw var2;
            }
         }

         public {
            if (SparkContext.this == null) {
               throw null;
            } else {
               this.$outer = SparkContext.this;
               this.setDaemon(true);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      }).start();
   }

   public void stop() {
      this.stop(0);
   }

   public void stop(final int exitCode) {
      this.stopSite_$eq(new Some(this.getCallSite()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SparkContext is stopping with exitCode ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(exitCode))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STOP_SITE_SHORT_FORM..MODULE$, ((CallSite)this.stopSite().get()).shortForm())}))))));
      if (BoxesRunTime.unboxToBoolean(LiveListenerBus$.MODULE$.withinListenerThread().value())) {
         throw new SparkException("Cannot stop SparkContext within listener bus thread.");
      } else if (!this.stopped().compareAndSet(false, true)) {
         this.logInfo((Function0)(() -> "SparkContext already stopped."));
      } else {
         if (this._shutdownHookRef() != null) {
            BoxesRunTime.boxToBoolean(ShutdownHookManager$.MODULE$.removeShutdownHook(this._shutdownHookRef()));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (this.listenerBus() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.postApplicationEnd(exitCode));
         }

         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._driverLogger().foreach((x$55) -> {
               $anonfun$stop$5(x$55);
               return BoxedUnit.UNIT;
            }));
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._ui().foreach((x$56) -> {
               $anonfun$stop$7(x$56);
               return BoxedUnit.UNIT;
            }));
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._cleaner().foreach((x$57) -> {
               $anonfun$stop$9(x$57);
               return BoxedUnit.UNIT;
            }));
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._executorAllocationManager().foreach((x$58) -> {
               $anonfun$stop$11(x$58);
               return BoxedUnit.UNIT;
            }));
         if (this._dagScheduler() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._dagScheduler().stop(exitCode));
            this._dagScheduler_$eq((DAGScheduler)null);
         }

         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._plugins().foreach((x$59) -> {
               $anonfun$stop$14(x$59);
               return BoxedUnit.UNIT;
            }));
         if (this._listenerBusStarted()) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
               this.listenerBus().stop();
               this._listenerBusStarted_$eq(false);
            });
         }

         if (this.env() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.env().metricsSystem().report());
         }

         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> FallbackStorage$.MODULE$.cleanUp(this._conf(), this._hadoopConfiguration()));
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._eventLogger().foreach((x$60) -> {
               $anonfun$stop$19(x$60);
               return BoxedUnit.UNIT;
            }));
         if (this._shuffleDriverComponents() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._shuffleDriverComponents().cleanupApplication());
         }

         if (this._heartbeater() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._heartbeater().stop());
            this._heartbeater_$eq((Heartbeater)null);
         }

         if (this.env() != null && this._heartbeatReceiver() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.env().rpcEnv().stop(this._heartbeatReceiver()));
         }

         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._progressBar().foreach((x$61) -> {
               $anonfun$stop$24(x$61);
               return BoxedUnit.UNIT;
            }));
         this._taskScheduler_$eq((TaskScheduler)null);
         if (this._env() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this._env().stop());
            SparkEnv$.MODULE$.set((SparkEnv)null);
         }

         if (this._statusStore() != null) {
            this._statusStore().close();
         }

         this.localProperties().remove();
         ResourceProfile$.MODULE$.clearDefaultProfile();
         SparkContext$.MODULE$.clearActiveContext();
         this.logInfo((Function0)(() -> "Successfully stopped SparkContext"));
      }
   }

   public Option getSparkHome() {
      return this.conf().getOption("spark.home").orElse(() -> scala.Option..MODULE$.apply(System.getenv("SPARK_HOME")));
   }

   public void setCallSite(final String shortCallSite) {
      this.setLocalProperty(CallSite$.MODULE$.SHORT_FORM(), shortCallSite);
   }

   public void setCallSite(final CallSite callSite) {
      this.setLocalProperty(CallSite$.MODULE$.SHORT_FORM(), callSite.shortForm());
      this.setLocalProperty(CallSite$.MODULE$.LONG_FORM(), callSite.longForm());
   }

   public void clearCallSite() {
      this.setLocalProperty(CallSite$.MODULE$.SHORT_FORM(), (String)null);
      this.setLocalProperty(CallSite$.MODULE$.LONG_FORM(), (String)null);
   }

   public CallSite getCallSite() {
      LazyRef callSite$lzy = new LazyRef();
      return new CallSite((String)scala.Option..MODULE$.apply(this.getLocalProperty(CallSite$.MODULE$.SHORT_FORM())).getOrElse(() -> callSite$2(callSite$lzy).shortForm()), (String)scala.Option..MODULE$.apply(this.getLocalProperty(CallSite$.MODULE$.LONG_FORM())).getOrElse(() -> callSite$2(callSite$lzy).longForm()));
   }

   public void runJob(final RDD rdd, final Function2 func, final Seq partitions, final Function2 resultHandler, final ClassTag evidence$11) {
      if (this.stopped().get()) {
         throw new IllegalStateException("SparkContext has been shutdown");
      } else {
         CallSite callSite = this.getCallSite();
         Function2 cleanedFunc = (Function2)this.clean(func, this.clean$default$2());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting job: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())})))));
         if (this.conf().getBoolean("spark.logLineage", false)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"RDD's recursive dependencies:\\n"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_DEBUG_STRING..MODULE$, rdd.toDebugString())}))))));
         }

         this.dagScheduler().runJob(rdd, cleanedFunc, partitions, callSite, resultHandler, (Properties)this.localProperties().get());
         this.progressBar().foreach((x$62) -> {
            $anonfun$runJob$3(x$62);
            return BoxedUnit.UNIT;
         });
         rdd.doCheckpoint();
      }
   }

   public Object runJob(final RDD rdd, final Function2 func, final Seq partitions, final ClassTag evidence$12) {
      Object results = evidence$12.newArray(partitions.size());
      this.runJob(rdd, func, partitions, (index, res) -> {
         $anonfun$runJob$4(results, BoxesRunTime.unboxToInt(index), res);
         return BoxedUnit.UNIT;
      }, evidence$12);
      return results;
   }

   public Object runJob(final RDD rdd, final Function1 func, final Seq partitions, final ClassTag evidence$13) {
      Function1 cleanedFunc = (Function1)this.clean(func, this.clean$default$2());
      return this.runJob(rdd, (Function2)((ctx, it) -> cleanedFunc.apply(it)), (Seq)partitions, evidence$13);
   }

   public Object runJob(final RDD rdd, final Function2 func, final ClassTag evidence$14) {
      return this.runJob(rdd, (Function2)func, (Seq)scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions())), evidence$14);
   }

   public Object runJob(final RDD rdd, final Function1 func, final ClassTag evidence$15) {
      return this.runJob(rdd, (Function1)func, (Seq)scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions())), evidence$15);
   }

   public void runJob(final RDD rdd, final Function2 processPartition, final Function2 resultHandler, final ClassTag evidence$16) {
      this.runJob(rdd, processPartition, scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions())), resultHandler, evidence$16);
   }

   public void runJob(final RDD rdd, final Function1 processPartition, final Function2 resultHandler, final ClassTag evidence$17) {
      Function2 processFunc = (context, iter) -> processPartition.apply(iter);
      this.runJob(rdd, processFunc, scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions())), resultHandler, evidence$17);
   }

   @DeveloperApi
   public PartialResult runApproximateJob(final RDD rdd, final Function2 func, final ApproximateEvaluator evaluator, final long timeout) {
      this.assertNotStopped();
      CallSite callSite = this.getCallSite();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting job: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())})))));
      long start = System.currentTimeMillis();
      Function2 cleanedFunc = (Function2)this.clean(func, this.clean$default$2());
      PartialResult result = this.dagScheduler().runApproximateJob(rdd, cleanedFunc, evaluator, callSite, timeout, (Properties)this.localProperties().get());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Job finished: ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, callSite.shortForm())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" took ", "ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(System.currentTimeMillis() - start))}))))));
      return result;
   }

   public SimpleFutureAction submitJob(final RDD rdd, final Function1 processPartition, final Seq partitions, final Function2 resultHandler, final Function0 resultFunc) {
      this.assertNotStopped();
      Function1 cleanF = (Function1)this.clean(processPartition, this.clean$default$2());
      CallSite callSite = this.getCallSite();
      JobWaiter waiter = this.dagScheduler().submitJob(rdd, (context, iter) -> cleanF.apply(iter), partitions, callSite, resultHandler, (Properties)this.localProperties().get());
      return new SimpleFutureAction(waiter, resultFunc);
   }

   public SimpleFutureAction submitMapStage(final ShuffleDependency dependency) {
      this.assertNotStopped();
      CallSite callSite = this.getCallSite();
      ObjectRef result = ObjectRef.create((Object)null);
      JobWaiter waiter = this.dagScheduler().submitMapStage(dependency, (r) -> {
         $anonfun$submitMapStage$1(result, r);
         return BoxedUnit.UNIT;
      }, callSite, (Properties)this.localProperties().get());
      return new SimpleFutureAction(waiter, () -> (MapOutputStatistics)result.elem);
   }

   public void cancelJobGroup(final String groupId, final String reason) {
      this.assertNotStopped();
      this.dagScheduler().cancelJobGroup(groupId, false, scala.Option..MODULE$.apply(reason));
   }

   public void cancelJobGroup(final String groupId) {
      this.assertNotStopped();
      this.dagScheduler().cancelJobGroup(groupId, false, scala.None..MODULE$);
   }

   public void cancelJobGroupAndFutureJobs(final String groupId, final String reason) {
      this.assertNotStopped();
      this.dagScheduler().cancelJobGroup(groupId, true, scala.Option..MODULE$.apply(reason));
   }

   public void cancelJobGroupAndFutureJobs(final String groupId) {
      this.assertNotStopped();
      this.dagScheduler().cancelJobGroup(groupId, true, scala.None..MODULE$);
   }

   public Future cancelJobsWithTagWithFuture(final String tag, final String reason) {
      SparkContext$.MODULE$.throwIfInvalidTag(tag);
      this.assertNotStopped();
      Promise cancelledJobs = scala.concurrent.Promise..MODULE$.apply();
      this.dagScheduler().cancelJobsWithTag(tag, new Some(reason), new Some(cancelledJobs));
      return cancelledJobs.future();
   }

   public void cancelJobsWithTag(final String tag, final String reason) {
      SparkContext$.MODULE$.throwIfInvalidTag(tag);
      this.assertNotStopped();
      this.dagScheduler().cancelJobsWithTag(tag, scala.Option..MODULE$.apply(reason), scala.None..MODULE$);
   }

   public void cancelJobsWithTag(final String tag) {
      SparkContext$.MODULE$.throwIfInvalidTag(tag);
      this.assertNotStopped();
      this.dagScheduler().cancelJobsWithTag(tag, scala.None..MODULE$, scala.None..MODULE$);
   }

   public void cancelAllJobs() {
      this.assertNotStopped();
      this.dagScheduler().cancelAllJobs();
   }

   public void cancelJob(final int jobId, final String reason) {
      this.dagScheduler().cancelJob(jobId, scala.Option..MODULE$.apply(reason));
   }

   public void cancelJob(final int jobId) {
      this.dagScheduler().cancelJob(jobId, scala.None..MODULE$);
   }

   public void cancelStage(final int stageId, final String reason) {
      this.dagScheduler().cancelStage(stageId, scala.Option..MODULE$.apply(reason));
   }

   public void cancelStage(final int stageId) {
      this.dagScheduler().cancelStage(stageId, scala.None..MODULE$);
   }

   public boolean killTaskAttempt(final long taskId, final boolean interruptThread, final String reason) {
      return this.dagScheduler().killTaskAttempt(taskId, interruptThread, reason);
   }

   public boolean killTaskAttempt$default$2() {
      return true;
   }

   public String killTaskAttempt$default$3() {
      return "killed via SparkContext.killTaskAttempt";
   }

   public Object clean(final Object f, final boolean checkSerializable) {
      SparkClosureCleaner$.MODULE$.clean(f, checkSerializable, SparkClosureCleaner$.MODULE$.clean$default$3());
      return f;
   }

   public boolean clean$default$2() {
      return true;
   }

   public void setCheckpointDir(final String directory) {
      if (!this.isLocal() && scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])Utils$.MODULE$.nonLocalPaths(directory, Utils$.MODULE$.nonLocalPaths$default$2())))) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark is not running in local mode, therefore the checkpoint directory "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"must not be on the local filesystem. Directory '", "' "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, directory)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"appears to be on the local filesystem."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      this.checkpointDir_$eq(scala.Option..MODULE$.apply(directory).map((dir) -> {
         Path path = new Path(dir, UUID.randomUUID().toString());
         FileSystem fs = path.getFileSystem(this.hadoopConfiguration());
         fs.mkdirs(path);
         return fs.getFileStatus(path).getPath().toString();
      }));
   }

   public Option getCheckpointDir() {
      return this.checkpointDir();
   }

   public int defaultParallelism() {
      this.assertNotStopped();
      return this.taskScheduler().defaultParallelism();
   }

   public int defaultMinPartitions() {
      return scala.math.package..MODULE$.min(this.defaultParallelism(), 2);
   }

   private AtomicInteger nextShuffleId() {
      return this.nextShuffleId;
   }

   public int newShuffleId() {
      return this.nextShuffleId().getAndIncrement();
   }

   private AtomicInteger nextRddId() {
      return this.nextRddId;
   }

   public int newRddId() {
      return this.nextRddId().getAndIncrement();
   }

   private void setupAndStartListenerBus() {
      label31: {
         try {
            ((Option)this.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXTRA_LISTENERS())).foreach((classNames) -> {
               $anonfun$setupAndStartListenerBus$1(this, classNames);
               return BoxedUnit.UNIT;
            });
         } catch (Exception var6) {
            try {
               this.stop();
            } finally {
               throw new SparkException("Exception when registering SparkListener", var6);
            }
         }

         this.listenerBus().start(this, this._env().metricsSystem());
         this._listenerBusStarted_$eq(true);
      }
   }

   private void postApplicationStart() {
      this.listenerBus().post(new SparkListenerApplicationStart(this.appName(), new Some(this.applicationId()), this.startTime(), this.sparkUser(), this.applicationAttemptId(), this.schedulerBackend().getDriverLogUrls(), this.schedulerBackend().getDriverAttributes()));
      this._driverLogger().foreach((x$63) -> {
         $anonfun$postApplicationStart$1(this, x$63);
         return BoxedUnit.UNIT;
      });
   }

   private void postApplicationEnd(final int exitCode) {
      this.listenerBus().post(new SparkListenerApplicationEnd(System.currentTimeMillis(), new Some(BoxesRunTime.boxToInteger(exitCode))));
   }

   public void postEnvironmentUpdate() {
      if (this.taskScheduler() != null) {
         String schedulingMode = this.getSchedulingMode().toString();
         Seq addedJarPaths = this.allAddedJars().keys().toSeq();
         Seq addedFilePaths = this.allAddedFiles().keys().toSeq();
         Seq addedArchivePaths = this.allAddedArchives().keys().toSeq();
         Map environmentDetails = SparkEnv$.MODULE$.environmentDetails(this.conf(), this.hadoopConfiguration(), schedulingMode, addedJarPaths, addedFilePaths, addedArchivePaths, scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(this.env().metricsSystem().metricsProperties()).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
         SparkListenerEnvironmentUpdate environmentUpdate = new SparkListenerEnvironmentUpdate(environmentDetails);
         this.listenerBus().post(environmentUpdate);
      }
   }

   private void reportHeartBeat(final Option executorMetricsSource) {
      long[] currentMetrics = ExecutorMetrics$.MODULE$.getCurrentMetrics(this.env().memoryManager());
      executorMetricsSource.foreach((x$64) -> {
         $anonfun$reportHeartBeat$1(currentMetrics, x$64);
         return BoxedUnit.UNIT;
      });
      HashMap driverUpdates = new HashMap();
      driverUpdates.put(EventLoggingListener$.MODULE$.DRIVER_STAGE_KEY(), new ExecutorMetrics(currentMetrics));
      ArraySeq accumUpdates = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(new Tuple4[0]).toImmutableArraySeq();
      this.listenerBus().post(new SparkListenerExecutorMetricsUpdate("driver", accumUpdates, driverUpdates));
   }

   // $FF: synthetic method
   public static final void $anonfun$new$4(final SparkContext $this, final String level) {
      if (org.apache.spark.internal.Logging..MODULE$.setLogLevelPrinted()) {
         System.err.printf("Setting Spark log level to \"%s\".\n", level);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      $this.setLogLevel(level);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$9(final String x$24) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$24));
   }

   // $FF: synthetic method
   public static final void $anonfun$new$14(final SparkUI x$25) {
      x$25.bind();
   }

   // $FF: synthetic method
   public static final void $anonfun$new$15(final SparkContext $this, final String jar) {
      $this.addJar(jar, true);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$16(final SparkContext $this, final String file) {
      $this.addFile(file, false, true, $this.addFile$default$4());
   }

   // $FF: synthetic method
   public static final void $anonfun$new$17(final SparkContext $this, final String file) {
      $this.addFile(file, false, true, true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$18(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$new$21(final SparkContext $this, final String envKey$1, final String value) {
      $this.executorEnvs().update(envKey$1, value);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$19(final SparkContext $this, final Tuple2 x$26) {
      if (x$26 != null) {
         String envKey = (String)x$26._1();
         String propKey = (String)x$26._2();
         scala.Option..MODULE$.apply(System.getenv(envKey)).orElse(() -> scala.Option..MODULE$.apply(System.getProperty(propKey))).foreach((value) -> {
            $anonfun$new$21($this, envKey, value);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$26);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$new$22(final SparkContext $this, final String v) {
      $this.executorEnvs().update("SPARK_PREPEND_CLASSES", v);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$23(final SparkContext $this, final String logLevel) {
      $this._schedulerBackend().updateExecutorsLogLevel(logLevel);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$24(final SparkContext $this, final String directory) {
      $this.setCheckpointDir(directory);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$26(final SparkContext $this, final String attemptId) {
      $this._conf().set((OptionalConfigEntry)org.apache.spark.internal.config.package$.MODULE$.APP_ATTEMPT_ID(), (Object)attemptId);
      $this._env().blockManager().blockStoreClient().setAppAttemptId(attemptId);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$29(final SparkContext $this, final SparkUI x$28) {
      x$28.setAppId($this._applicationId());
   }

   // $FF: synthetic method
   public static final void $anonfun$new$30(final ContextCleaner x$29) {
      x$29.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$new$31(final ExecutorAllocationManager x$30) {
      x$30.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$new$32(final SparkUI x$31) {
      x$31.attachAllHandlers();
   }

   // $FF: synthetic method
   public static final void $anonfun$new$34(final ServletContextHandler handler$1, final SparkUI x$32) {
      x$32.attachHandler(handler$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$33(final SparkContext $this, final ServletContextHandler handler) {
      $this.ui().foreach((x$32) -> {
         $anonfun$new$34(handler, x$32);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$new$39(final SparkContext $this, final ExecutorMetricsSource x$33) {
      x$33.register($this._env().metricsSystem());
   }

   // $FF: synthetic method
   public static final void $anonfun$new$40(final SparkContext $this, final ExecutorAllocationManager e) {
      $this._env().metricsSystem().registerSource(e.executorAllocationManagerSource());
   }

   // $FF: synthetic method
   public static final void $anonfun$new$41(final SparkContext $this, final AppStatusSource x$34) {
      $this._env().metricsSystem().registerSource(x$34);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$42(final SparkContext $this, final PluginContainer x$35) {
      x$35.registerMetrics($this.applicationId());
   }

   // $FF: synthetic method
   public static final void $anonfun$addJobTags$1(final String tag) {
      SparkContext$.MODULE$.throwIfInvalidTag(tag);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeJobTags$1(final String tag) {
      SparkContext$.MODULE$.throwIfInvalidTag(tag);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getJobTags$3(final String x$38) {
      return !x$38.isEmpty();
   }

   private static final long getSafeMargin$1(final BigInt bi) {
      if (bi.isValidLong()) {
         return bi.toLong();
      } else {
         return bi.$greater(scala.math.BigInt..MODULE$.int2bigInt(0)) ? Long.MAX_VALUE : Long.MIN_VALUE;
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$range$3(final BigInt numElements$1, final int numSlices$2, final long step$1, final long start$1, final int i, final Iterator x$39) {
      BigInt partitionStart = scala.math.BigInt..MODULE$.int2bigInt(i).$times(numElements$1).$div(scala.math.BigInt..MODULE$.int2bigInt(numSlices$2)).$times(scala.math.BigInt..MODULE$.long2bigInt(step$1)).$plus(scala.math.BigInt..MODULE$.long2bigInt(start$1));
      BigInt partitionEnd = scala.math.BigInt..MODULE$.int2bigInt(i + 1).$times(numElements$1).$div(scala.math.BigInt..MODULE$.int2bigInt(numSlices$2)).$times(scala.math.BigInt..MODULE$.long2bigInt(step$1)).$plus(scala.math.BigInt..MODULE$.long2bigInt(start$1));
      long safePartitionStart = getSafeMargin$1(partitionStart);
      long safePartitionEnd = getSafeMargin$1(partitionEnd);
      return new Iterator(safePartitionStart, step$1, safePartitionEnd) {
         private long number;
         private boolean overflow;
         private final long step$1;
         private final long safePartitionEnd$1;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         public boolean hasNext() {
            if (!this.overflow) {
               if (this.step$1 > 0L) {
                  return this.number < this.safePartitionEnd$1;
               } else {
                  return this.number > this.safePartitionEnd$1;
               }
            } else {
               return false;
            }
         }

         public long next() {
            long ret = this.number;
            this.number += this.step$1;
            if (this.number < ret ^ this.step$1 < 0L) {
               this.overflow = true;
            }

            return ret;
         }

         public {
            this.step$1 = step$1;
            this.safePartitionEnd$1 = safePartitionEnd$1;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.number = safePartitionStart$1;
            this.overflow = false;
         }
      };
   }

   // $FF: synthetic method
   public static final void $anonfun$hadoopFile$2(final String path$5, final JobConf jobConf) {
      org.apache.hadoop.mapred.FileInputFormat.setInputPaths(jobConf, path$5);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$union$2(final RDD x$41) {
      return !scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(x$41.partitions()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$union$4(final RDD x$43) {
      return x$43.partitioner().isDefined();
   }

   // $FF: synthetic method
   public static final void $anonfun$broadcastInternal$3(final Broadcast bc$1, final ContextCleaner x$44) {
      x$44.registerBroadcastForCleanup(bc$1);
   }

   // $FF: synthetic method
   private static final File root$lzycompute$1(final LazyRef root$lzy$1, final String jobArtifactUUID$1) {
      synchronized(root$lzy$1){}

      File var3;
      try {
         File var10000;
         if (root$lzy$1.initialized()) {
            var10000 = (File)root$lzy$1.value();
         } else {
            File var10001;
            label60: {
               label59: {
                  String var4 = "default";
                  if (jobArtifactUUID$1 == null) {
                     if (var4 != null) {
                        break label59;
                     }
                  } else if (!jobArtifactUUID$1.equals(var4)) {
                     break label59;
                  }

                  var10001 = new File(SparkFiles$.MODULE$.getRootDirectory());
                  break label60;
               }

               File newDest = new File(SparkFiles$.MODULE$.getRootDirectory(), jobArtifactUUID$1);
               newDest.mkdir();
               var10001 = newDest;
            }

            var10000 = (File)root$lzy$1.initialize(var10001);
         }

         var3 = var10000;
      } catch (Throwable var7) {
         throw var7;
      }

      return var3;
   }

   private static final File root$1(final LazyRef root$lzy$1, final String jobArtifactUUID$1) {
      return root$lzy$1.initialized() ? (File)root$lzy$1.value() : root$lzycompute$1(root$lzy$1, jobArtifactUUID$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getRDDStorageInfo$1(final RDD x$46) {
      return true;
   }

   // $FF: synthetic method
   public static final int $anonfun$getRDDStorageInfo$5(final RDDStorageInfo x$47) {
      return x$47.numCachedPartitions();
   }

   // $FF: synthetic method
   public static final long $anonfun$getRDDStorageInfo$7(final RDDStorageInfo x$48) {
      return x$48.memoryUsed();
   }

   // $FF: synthetic method
   public static final long $anonfun$getRDDStorageInfo$9(final RDDStorageInfo x$49) {
      return x$49.diskUsed();
   }

   // $FF: synthetic method
   public static final void $anonfun$getRDDStorageInfo$3(final SparkContext $this, final RDDInfo rddInfo) {
      int rddId = rddInfo.id();
      Option rddStorageInfo = $this.statusStore().asOption(() -> $this.statusStore().rdd(rddId));
      rddInfo.numCachedPartitions_$eq(BoxesRunTime.unboxToInt(rddStorageInfo.map((x$47) -> BoxesRunTime.boxToInteger($anonfun$getRDDStorageInfo$5(x$47))).getOrElse((JFunction0.mcI.sp)() -> 0)));
      rddInfo.memSize_$eq(BoxesRunTime.unboxToLong(rddStorageInfo.map((x$48) -> BoxesRunTime.boxToLong($anonfun$getRDDStorageInfo$7(x$48))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      rddInfo.diskSize_$eq(BoxesRunTime.unboxToLong(rddStorageInfo.map((x$49) -> BoxesRunTime.boxToLong($anonfun$getRDDStorageInfo$9(x$49))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getRDDStorageInfo$11(final RDDInfo x$50) {
      return x$50.isCached();
   }

   private final Seq addLocalJarFile$1(final File file, final String path$16) {
      Object var10000;
      try {
         if (!file.exists()) {
            throw new FileNotFoundException("Jar " + file.getAbsolutePath() + " not found");
         }

         if (file.isDirectory()) {
            throw new IllegalArgumentException("Directory " + file.getAbsoluteFile() + " is not allowed for addJar");
         }

         var10000 = new scala.collection.immutable..colon.colon(this.env().rpcEnv().fileServer().addJar(file), scala.collection.immutable.Nil..MODULE$);
      } catch (Throwable var7) {
         if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
            throw var7;
         }

         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to add ", " to Spark environment"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path$16)})))), var7);
         var10000 = scala.collection.immutable.Nil..MODULE$;
      }

      return (Seq)var10000;
   }

   private final Seq checkRemoteJarFile$1(final String path) {
      Path hadoopPath = new Path(path);
      String scheme = hadoopPath.toUri().getScheme();
      if (!scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"http", "https", "ftp", "spark"})), scheme)) {
         Object var10000;
         try {
            FileSystem fs = hadoopPath.getFileSystem(this.hadoopConfiguration());
            if (!fs.exists(hadoopPath)) {
               throw new FileNotFoundException("Jar " + path + " not found");
            }

            if (fs.getFileStatus(hadoopPath).isDirectory()) {
               throw new IllegalArgumentException("Directory " + path + " is not allowed for addJar");
            }

            var10000 = new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$);
         } catch (Throwable var9) {
            if (var9 == null || !scala.util.control.NonFatal..MODULE$.apply(var9)) {
               throw var9;
            }

            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to add ", " to Spark environment"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))), var9);
            var10000 = scala.collection.immutable.Nil..MODULE$;
         }

         return (Seq)var10000;
      } else {
         return new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addJar$7(final SparkContext $this, final String jobArtifactUUID$2, final long timestamp$2, final String x$53) {
      return ((scala.collection.concurrent.Map)$this.addedJars().getOrElseUpdate(jobArtifactUUID$2, () -> scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala())).putIfAbsent(x$53, BoxesRunTime.boxToLong(timestamp$2)).isEmpty();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$5(final DriverLogger x$55) {
      x$55.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$7(final SparkUI x$56) {
      x$56.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$9(final ContextCleaner x$57) {
      x$57.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$11(final ExecutorAllocationManager x$58) {
      x$58.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$14(final PluginContainer x$59) {
      x$59.shutdown();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$19(final EventLoggingListener x$60) {
      x$60.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$24(final ConsoleProgressBar x$61) {
      x$61.stop();
   }

   // $FF: synthetic method
   private static final CallSite callSite$lzycompute$1(final LazyRef callSite$lzy$1) {
      synchronized(callSite$lzy$1){}

      CallSite var2;
      try {
         var2 = callSite$lzy$1.initialized() ? (CallSite)callSite$lzy$1.value() : (CallSite)callSite$lzy$1.initialize(Utils$.MODULE$.getCallSite(Utils$.MODULE$.getCallSite$default$1()));
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private static final CallSite callSite$2(final LazyRef callSite$lzy$1) {
      return callSite$lzy$1.initialized() ? (CallSite)callSite$lzy$1.value() : callSite$lzycompute$1(callSite$lzy$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$runJob$3(final ConsoleProgressBar x$62) {
      x$62.finishAll();
   }

   // $FF: synthetic method
   public static final void $anonfun$runJob$4(final Object results$1, final int index, final Object res) {
      scala.runtime.ScalaRunTime..MODULE$.array_update(results$1, index, res);
   }

   // $FF: synthetic method
   public static final void $anonfun$submitMapStage$1(final ObjectRef result$1, final MapOutputStatistics r) {
      result$1.elem = r;
   }

   // $FF: synthetic method
   public static final void $anonfun$setupAndStartListenerBus$2(final SparkContext $this, final SparkListenerInterface listener) {
      $this.listenerBus().addToSharedQueue(listener);
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registered listener"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, listener.getClass().getName())}))))));
   }

   // $FF: synthetic method
   public static final void $anonfun$setupAndStartListenerBus$1(final SparkContext $this, final Seq classNames) {
      Seq listeners = Utils$.MODULE$.loadExtensions(SparkListenerInterface.class, classNames, $this.conf());
      listeners.foreach((listener) -> {
         $anonfun$setupAndStartListenerBus$2($this, listener);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$postApplicationStart$1(final SparkContext $this, final DriverLogger x$63) {
      x$63.startSync($this._hadoopConfiguration());
   }

   // $FF: synthetic method
   public static final void $anonfun$reportHeartBeat$1(final long[] currentMetrics$1, final ExecutorMetricsSource x$64) {
      x$64.updateMetricsSnapshot(currentMetrics$1);
   }

   public SparkContext(final SparkConf config) {
      Logging.$init$(this);
      this.org$apache$spark$SparkContext$$creationSite = Utils$.MODULE$.getCallSite(Utils$.MODULE$.getCallSite$default$1());
      this.stopSite = scala.None..MODULE$;
      if (!BoxesRunTime.unboxToBoolean(config.get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ALLOW_SPARK_CONTEXT()))) {
         SparkContext$.MODULE$.org$apache$spark$SparkContext$$assertOnDriver();
      }

      SparkContext$.MODULE$.markPartiallyConstructed(this);
      this.startTime = System.currentTimeMillis();
      this.stopped = new AtomicBoolean(false);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Running Spark version ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_VERSION..MODULE$, package$.MODULE$.SPARK_VERSION())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"OS info ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OS_NAME..MODULE$, System.getProperty("os.name"))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OS_VERSION..MODULE$, System.getProperty("os.version"))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OS_ARCH..MODULE$, System.getProperty("os.arch"))}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Java version ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JAVA_VERSION..MODULE$, System.getProperty("java.version"))})))));
      this._eventLogDir = scala.None..MODULE$;
      this._eventLogCodec = scala.None..MODULE$;
      this._progressBar = scala.None..MODULE$;
      this._ui = scala.None..MODULE$;
      this._applicationAttemptId = scala.None..MODULE$;
      this._eventLogger = scala.None..MODULE$;
      this._driverLogger = scala.None..MODULE$;
      this._executorAllocationManager = scala.None..MODULE$;
      this._cleaner = scala.None..MODULE$;
      this._listenerBusStarted = false;
      this._plugins = scala.None..MODULE$;
      this.addedFiles = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      this.addedArchives = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      this.addedJars = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      ConcurrentMap map = (new MapMaker()).weakValues().makeMap();
      this.persistentRdds = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(map).asScala();
      this.executorEnvs = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.sparkUser = Utils$.MODULE$.getCurrentUserName();
      this.checkpointDir = scala.None..MODULE$;
      this.localProperties = new InheritableThreadLocal() {
         public Properties childValue(final Properties parent) {
            return Utils$.MODULE$.cloneProperties(parent);
         }

         public Properties initialValue() {
            return new Properties();
         }
      };

      try {
         this._conf_$eq(config.clone());
         ((Option)this._conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SPARK_LOG_LEVEL())).foreach((level) -> {
            $anonfun$new$4(this, level);
            return BoxedUnit.UNIT;
         });
         this._conf().validateSettings();
         this._conf().set("spark.app.startTime", Long.toString(this.startTime()));
         if (!this._conf().contains("spark.master")) {
            throw new SparkException("A master URL must be set in your configuration");
         }

         if (!this._conf().contains("spark.app.name")) {
            throw new SparkException("An application name must be set in your configuration");
         }

         label381: {
            this.conf().setIfMissing("spark.hadoop.fs.s3a.vectored.read.min.seek.size", "128K");
            this.conf().setIfMissing("spark.hadoop.fs.s3a.vectored.read.max.merged.size", "2M");
            SparkContext$.MODULE$.org$apache$spark$SparkContext$$fillMissingMagicCommitterConfsIfNeeded(this._conf());
            SparkContext$.MODULE$.org$apache$spark$SparkContext$$supplementJavaModuleOptions(this._conf());
            SparkContext$.MODULE$.org$apache$spark$SparkContext$$supplementJavaIPv6Options(this._conf());
            this._driverLogger_$eq(DriverLogger$.MODULE$.apply(this._conf()));
            Option resourcesFileOpt = (Option)this.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.DRIVER_RESOURCES_FILE());
            this._resources_$eq(ResourceUtils$.MODULE$.getOrDiscoverAllResources(this._conf(), org.apache.spark.internal.config.package$.MODULE$.SPARK_DRIVER_PREFIX(), resourcesFileOpt));
            ResourceUtils$.MODULE$.logResourceInfo(org.apache.spark.internal.config.package$.MODULE$.SPARK_DRIVER_PREFIX(), this._resources());
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitted application: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_NAME..MODULE$, this.appName())})))));
            String var46 = this.master();
            String var8 = "yarn";
            if (var46 == null) {
               if (var8 != null) {
                  break label381;
               }
            } else if (!var46.equals(var8)) {
               break label381;
            }

            var46 = this.deployMode();
            String var9 = "cluster";
            if (var46 == null) {
               if (var9 != null) {
                  break label381;
               }
            } else if (!var46.equals(var9)) {
               break label381;
            }

            if (!this._conf().contains("spark.yarn.app.id")) {
               throw new SparkException("Detected yarn cluster mode, but isn't running on a cluster. Deployment to YARN is not supported directly by SparkContext. Please use spark-submit.");
            }
         }

         if (this._conf().getBoolean("spark.logConf", false)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark configuration:\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, this._conf().toDebugString())})))));
         }

         this._conf().set(org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS(), this._conf().get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS()));
         this._conf().setIfMissing((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.DRIVER_PORT(), (Object)BoxesRunTime.boxToInteger(0));
         this._conf().set((OptionalConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ID(), (Object)SparkContext$.MODULE$.DRIVER_IDENTIFIER());
         this._jars_$eq(Utils$.MODULE$.getUserJars(this._conf()));
         this._files_$eq((Seq)scala.Option..MODULE$.option2Iterable(this._conf().getOption(org.apache.spark.internal.config.package$.MODULE$.FILES().key()).map((x$22) -> x$22.split(",")).map((x$23) -> (String[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$23), (x$24) -> BoxesRunTime.boxToBoolean($anonfun$new$9(x$24))))).toSeq().flatten((xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs)));
         this._archives_$eq((Seq)scala.Option..MODULE$.option2Iterable(this._conf().getOption(org.apache.spark.internal.config.package$.MODULE$.ARCHIVES().key()).map((str) -> Utils$.MODULE$.stringToSeq(str))).toSeq().flatten(scala.Predef..MODULE$.$conforms()));
         Object var10001;
         if (this.isEventLogEnabled()) {
            String unresolvedDir = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString((String)this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_DIR())), "/");
            var10001 = new Some(Utils$.MODULE$.resolveURI(unresolvedDir));
         } else {
            var10001 = scala.None..MODULE$;
         }

         this._eventLogDir_$eq((Option)var10001);
         boolean compress = BoxesRunTime.unboxToBoolean(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_COMPRESS())) && !((String)this._conf().get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_COMPRESSION_CODEC())).equalsIgnoreCase("none");
         this._eventLogCodec_$eq((Option)(compress && this.isEventLogEnabled() ? (new Some(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_COMPRESSION_CODEC()))).map((codecName) -> CompressionCodec$.MODULE$.getShortName(codecName)) : scala.None..MODULE$));
         this._listenerBus_$eq(new LiveListenerBus(this._conf()));
         this._resourceProfileManager_$eq(new ResourceProfileManager(this._conf(), this._listenerBus()));
         Option appStatusSource = AppStatusSource$.MODULE$.createSource(this.conf());
         this._statusStore_$eq(AppStatusStore$.MODULE$.createLiveStore(this.conf(), appStatusSource));
         this.listenerBus().addToStatusQueue((SparkListenerInterface)this._statusStore().listener().get());
         this._env_$eq(this.createSparkEnv(this._conf(), this.isLocal(), this.listenerBus()));
         SparkEnv$.MODULE$.set(this._env());
         this._conf().getOption("spark.repl.class.outputDir").foreach((path) -> {
            String replUri = this._env().rpcEnv().fileServer().addDirectory("/classes", new File(path));
            return this._conf().set("spark.repl.class.uri", replUri);
         });
         this._statusTracker_$eq(new SparkStatusTracker(this, this._statusStore()));
         this._progressBar_$eq((Option)(BoxesRunTime.unboxToBoolean(this._conf().get(UI$.MODULE$.UI_SHOW_CONSOLE_PROGRESS())) ? new Some(new ConsoleProgressBar(this)) : scala.None..MODULE$));
         this._ui_$eq((Option)(BoxesRunTime.unboxToBoolean(this.conf().get(UI$.MODULE$.UI_ENABLED())) ? new Some(SparkUI$.MODULE$.create(new Some(this), this._statusStore(), this._conf(), this._env().securityManager(), this.appName(), "", this.startTime(), SparkUI$.MODULE$.create$default$8())) : scala.None..MODULE$));
         this._ui().foreach((x$25) -> {
            $anonfun$new$14(x$25);
            return BoxedUnit.UNIT;
         });
         this._hadoopConfiguration_$eq(SparkHadoopUtil$.MODULE$.get().newConfiguration(this._conf()));
         this._hadoopConfiguration().size();
         if (this.jars() != null) {
            this.jars().foreach((jar) -> {
               $anonfun$new$15(this, jar);
               return BoxedUnit.UNIT;
            });
            if (this.allAddedJars().nonEmpty()) {
               this._conf().set("spark.app.initial.jar.urls", this.allAddedJars().keys().toSeq().mkString(","));
            } else {
               BoxedUnit var48 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var49 = BoxedUnit.UNIT;
         }

         if (this.files() != null) {
            this.files().foreach((file) -> {
               $anonfun$new$16(this, file);
               return BoxedUnit.UNIT;
            });
            if (this.allAddedFiles().nonEmpty()) {
               this._conf().set("spark.app.initial.file.urls", this.allAddedFiles().keys().toSeq().mkString(","));
            } else {
               BoxedUnit var50 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var51 = BoxedUnit.UNIT;
         }

         if (this.archives() != null) {
            this.archives().foreach((file) -> {
               $anonfun$new$17(this, file);
               return BoxedUnit.UNIT;
            });
            if (this.allAddedArchives().nonEmpty()) {
               this._conf().set("spark.app.initial.archive.urls", this.allAddedArchives().keys().toSeq().mkString(","));
            } else {
               BoxedUnit var52 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var53 = BoxedUnit.UNIT;
         }

         this._executorMemory_$eq(SparkContext$.MODULE$.executorMemoryInMb(this._conf()));
         (new scala.collection.immutable..colon.colon(new Tuple2("SPARK_TESTING", Tests$.MODULE$.IS_TESTING().key()), scala.collection.immutable.Nil..MODULE$)).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$new$18(check$ifrefutable$1))).foreach((x$26) -> {
            $anonfun$new$19(this, x$26);
            return BoxedUnit.UNIT;
         });
         scala.Option..MODULE$.apply(System.getenv("SPARK_PREPEND_CLASSES")).foreach((v) -> {
            $anonfun$new$22(this, v);
            return BoxedUnit.UNIT;
         });
         this.executorEnvs().$plus$plus$eq(this._conf().getExecutorEnv());
         this.executorEnvs().update("SPARK_USER", this.sparkUser());
         if (this._conf().getOption("spark.executorEnv.OMP_NUM_THREADS").isEmpty()) {
            this.executorEnvs().put("OMP_NUM_THREADS", this._conf().get("spark.task.cpus", "1"));
         } else {
            BoxedUnit var54 = BoxedUnit.UNIT;
         }

         this._heartbeatReceiver_$eq(this.env().rpcEnv().setupEndpoint(HeartbeatReceiver$.MODULE$.ENDPOINT_NAME(), new HeartbeatReceiver(this)));
         this._plugins_$eq(PluginContainer$.MODULE$.apply(this, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this._resources()).asJava()));
         this._env().initializeShuffleManager();
         this._env().initializeMemoryManager(SparkContext$.MODULE$.numDriverCores(this.master(), this.conf()));
         Tuple2 var14 = SparkContext$.MODULE$.org$apache$spark$SparkContext$$createTaskScheduler(this, this.master());
         if (var14 == null) {
            throw new MatchError(var14);
         }

         SchedulerBackend sched = (SchedulerBackend)var14._1();
         TaskScheduler ts = (TaskScheduler)var14._2();
         Tuple2 var13 = new Tuple2(sched, ts);
         SchedulerBackend sched = (SchedulerBackend)var13._1();
         TaskScheduler ts = (TaskScheduler)var13._2();
         this._schedulerBackend_$eq(sched);
         this._taskScheduler_$eq(ts);
         this._dagScheduler_$eq(new DAGScheduler(this));
         this._heartbeatReceiver().ask(TaskSchedulerIsSet$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean());
         if (BoxesRunTime.unboxToBoolean(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ALLOW_SYNC_LOG_LEVEL()))) {
            ((Option)this._conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SPARK_LOG_LEVEL())).foreach((logLevel) -> {
               $anonfun$new$23(this, logLevel);
               return BoxedUnit.UNIT;
            });
         }

         ((Option)this._conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.CHECKPOINT_DIR())).foreach((directory) -> {
            $anonfun$new$24(this, directory);
            return BoxedUnit.UNIT;
         });
         Option _executorMetricsSource = (Option)(BoxesRunTime.unboxToBoolean(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.METRICS_EXECUTORMETRICS_SOURCE_ENABLED())) ? new Some(new ExecutorMetricsSource()) : scala.None..MODULE$);
         this._heartbeater_$eq(new Heartbeater((JFunction0.mcV.sp)() -> this.reportHeartBeat(_executorMetricsSource), "driver-heartbeater", BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL()))));
         this._heartbeater().start();
         this._taskScheduler().start();
         this._applicationId_$eq(this._taskScheduler().applicationId());
         this._applicationAttemptId_$eq(this._taskScheduler().applicationAttemptId());
         this._conf().set("spark.app.id", this._applicationId());
         this._applicationAttemptId().foreach((attemptId) -> {
            $anonfun$new$26(this, attemptId);
            return BoxedUnit.UNIT;
         });
         this._shuffleDriverComponents_$eq(ShuffleDataIOUtils$.MODULE$.loadShuffleDataIO(this._conf()).driver());
         scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this._shuffleDriverComponents().initializeApplication()).asScala().foreach((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return this._conf().set(ShuffleDataIOUtils$.MODULE$.SHUFFLE_SPARK_CONF_PREFIX() + k, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
         if (BoxesRunTime.unboxToBoolean(this._conf().get(UI$.MODULE$.UI_REVERSE_PROXY()))) {
            String proxyUrl = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString((String)((Option)this._conf().get((ConfigEntry)UI$.MODULE$.UI_REVERSE_PROXY_URL())).getOrElse(() -> "")), "/");
            System.setProperty("spark.ui.proxyBase", proxyUrl + "/proxy/" + this._applicationId());
         } else {
            BoxedUnit var55 = BoxedUnit.UNIT;
         }

         this._ui().foreach((x$28) -> {
            $anonfun$new$29(this, x$28);
            return BoxedUnit.UNIT;
         });
         this._env().blockManager().initialize(this._applicationId());
         FallbackStorage$.MODULE$.registerBlockManagerIfNeeded(this._env().blockManager().master(), this._conf());
         this._env().metricsSystem().start(BoxesRunTime.unboxToBoolean(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.METRICS_STATIC_SOURCES_ENABLED())));
         if (this.isEventLogEnabled()) {
            EventLoggingListener logger = new EventLoggingListener(this._applicationId(), this._applicationAttemptId(), (URI)this._eventLogDir().get(), this._conf(), this._hadoopConfiguration());
            logger.start();
            this.listenerBus().addToEventLogQueue(logger);
            var10001 = new Some(logger);
         } else {
            var10001 = scala.None..MODULE$;
         }

         this._eventLogger_$eq((Option)var10001);
         this._cleaner_$eq((Option)(BoxesRunTime.unboxToBoolean(this._conf().get(org.apache.spark.internal.config.package$.MODULE$.CLEANER_REFERENCE_TRACKING())) ? new Some(new ContextCleaner(this, this._shuffleDriverComponents())) : scala.None..MODULE$));
         this._cleaner().foreach((x$29) -> {
            $anonfun$new$30(x$29);
            return BoxedUnit.UNIT;
         });
         boolean dynamicAllocationEnabled = Utils$.MODULE$.isDynamicAllocationEnabled(this._conf());
         if (dynamicAllocationEnabled) {
            SchedulerBackend var23 = this.schedulerBackend();
            if (var23 instanceof ExecutorAllocationClient) {
               ExecutorAllocationClient x$1 = (ExecutorAllocationClient)this.schedulerBackend();
               LiveListenerBus x$2 = this.listenerBus();
               SparkConf x$3 = this._conf();
               Option x$4 = this.cleaner();
               ResourceProfileManager x$5 = this.resourceProfileManager();
               boolean x$6 = this._shuffleDriverComponents().supportsReliableStorage();
               Clock x$7 = ExecutorAllocationManager$.MODULE$.$lessinit$greater$default$5();
               var10001 = new Some(new ExecutorAllocationManager(x$1, x$2, x$3, x$4, x$7, x$5, x$6));
            } else {
               var10001 = scala.None..MODULE$;
            }
         } else {
            var10001 = scala.None..MODULE$;
         }

         this._executorAllocationManager_$eq((Option)var10001);
         this._executorAllocationManager().foreach((x$30) -> {
            $anonfun$new$31(x$30);
            return BoxedUnit.UNIT;
         });
         this.setupAndStartListenerBus();
         this.postEnvironmentUpdate();
         this.postApplicationStart();
         this._ui().foreach((x$31) -> {
            $anonfun$new$32(x$31);
            return BoxedUnit.UNIT;
         });
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this._env().metricsSystem().getServletHandlers()), (handler) -> {
            $anonfun$new$33(this, handler);
            return BoxedUnit.UNIT;
         });
         this.logDebug((Function0)(() -> "Adding shutdown hook"));
         this._shutdownHookRef_$eq(ShutdownHookManager$.MODULE$.addShutdownHook(ShutdownHookManager$.MODULE$.SPARK_CONTEXT_SHUTDOWN_PRIORITY(), (JFunction0.mcV.sp)() -> {
            this.logInfo((Function0)(() -> "Invoking stop() from shutdown hook"));

            try {
               this.stop();
            } catch (Throwable var2) {
               this.logWarning((Function0)(() -> "Ignoring Exception while stopping SparkContext from shutdown hook"), var2);
            }

         }));
         this._taskScheduler().postStartHook();
         if (this.isLocal()) {
            this._env().metricsSystem().registerSource(Executor$.MODULE$.executorSourceLocalModeOnly());
         }

         this._env().metricsSystem().registerSource(this._dagScheduler().metricsSource());
         this._env().metricsSystem().registerSource(new BlockManagerSource(this._env().blockManager()));
         this._env().metricsSystem().registerSource(new JVMCPUSource());
         _executorMetricsSource.foreach((x$33) -> {
            $anonfun$new$39(this, x$33);
            return BoxedUnit.UNIT;
         });
         this._executorAllocationManager().foreach((e) -> {
            $anonfun$new$40(this, e);
            return BoxedUnit.UNIT;
         });
         appStatusSource.foreach((x$34) -> {
            $anonfun$new$41(this, x$34);
            return BoxedUnit.UNIT;
         });
         this._plugins().foreach((x$35) -> {
            $anonfun$new$42(this, x$35);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var43) {
         if (var43 != null && scala.util.control.NonFatal..MODULE$.apply(var43)) {
            this.logError((Function0)(() -> "Error initializing SparkContext."), var43);

            try {
               this.stop();
               BoxedUnit var45 = BoxedUnit.UNIT;
            } catch (Throwable var41) {
               if (var41 == null || !scala.util.control.NonFatal..MODULE$.apply(var41)) {
                  throw var41;
               }

               this.logError((Function0)(() -> "Error stopping SparkContext after init error."), var41);
               BoxedUnit var10000 = BoxedUnit.UNIT;
               var10000 = BoxedUnit.UNIT;
            } finally {
               ;
            }

            throw var43;
         }

         throw var43;
      }

      this.nextShuffleId = new AtomicInteger(0);
      this.nextRddId = new AtomicInteger(0);
      SparkContext$.MODULE$.setActiveContext(this);
   }

   public SparkContext() {
      this(new SparkConf());
   }

   public SparkContext(final String master, final String appName, final SparkConf conf) {
      this(SparkContext$.MODULE$.updatedConf(conf, master, appName, SparkContext$.MODULE$.updatedConf$default$4(), SparkContext$.MODULE$.updatedConf$default$5(), SparkContext$.MODULE$.updatedConf$default$6()));
   }

   public SparkContext(final String master, final String appName, final String sparkHome, final Seq jars, final scala.collection.Map environment) {
      this(SparkContext$.MODULE$.updatedConf(new SparkConf(), master, appName, sparkHome, jars, environment));
   }

   public SparkContext(final String master, final String appName) {
      this(master, appName, (String)null, scala.collection.immutable.Nil..MODULE$, (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   public SparkContext(final String master, final String appName, final String sparkHome) {
      this(master, appName, sparkHome, scala.collection.immutable.Nil..MODULE$, (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   public SparkContext(final String master, final String appName, final String sparkHome, final Seq jars) {
      this(master, appName, sparkHome, jars, (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
