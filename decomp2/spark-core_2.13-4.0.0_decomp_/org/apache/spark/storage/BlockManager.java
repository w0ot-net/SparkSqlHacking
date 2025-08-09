package org.apache.spark.storage;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.esotericsoftware.kryo.KryoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.apache.spark.MapOutputTracker;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.DataReadMethod$;
import org.apache.spark.executor.ExecutorExitCode$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.metrics.source.Source;
import org.apache.spark.network.BlockDataManager;
import org.apache.spark.network.BlockTransferService;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.shuffle.BlockStoreClient;
import org.apache.spark.network.shuffle.DownloadFile;
import org.apache.spark.network.shuffle.DownloadFileManager;
import org.apache.spark.network.shuffle.DownloadFileWritableChannel;
import org.apache.spark.network.shuffle.ExecutorDiskUtils;
import org.apache.spark.network.shuffle.ExternalBlockStoreClient;
import org.apache.spark.network.shuffle.MergedBlockMeta;
import org.apache.spark.network.shuffle.SimpleDownloadFile;
import org.apache.spark.network.shuffle.checksum.Cause;
import org.apache.spark.network.shuffle.checksum.ShuffleChecksumHelper;
import org.apache.spark.network.shuffle.protocol.ExecutorShuffleInfo;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.IndexShuffleBlockResolver;
import org.apache.spark.shuffle.MigratableResolver;
import org.apache.spark.shuffle.ShuffleBlockResolver;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.shuffle.ShuffleManager$;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.storage.memory.BlockEvictionHandler;
import org.apache.spark.storage.memory.MemoryStore;
import org.apache.spark.storage.memory.PartiallySerializedBlock;
import org.apache.spark.storage.memory.PartiallyUnrolledIterator;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.util.CompletionIterator;
import org.apache.spark.util.CompletionIterator$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.apache.spark.util.io.ChunkedByteBuffer$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple6;
import scala.Tuple7;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashSet;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u00055-b!\u0003BE\u0005\u0017\u0003!q\u0012BN\u0011)\u0011i\r\u0001BC\u0002\u0013\u0005!\u0011\u001b\u0005\u000b\u0005S\u0004!\u0011!Q\u0001\n\tM\u0007B\u0003Bv\u0001\t\u0005\t\u0015!\u0003\u0003n\"Q!\u0011 \u0001\u0003\u0006\u0004%\tAa?\t\u0015\r\u0015\u0001A!A!\u0002\u0013\u0011i\u0010\u0003\u0006\u0004\b\u0001\u0011)\u0019!C\u0001\u0007\u0013A!ba\u0006\u0001\u0005\u0003\u0005\u000b\u0011BB\u0006\u0011)\u0019I\u0002\u0001BC\u0002\u0013\u000511\u0004\u0005\u000b\u0007K\u0001!\u0011!Q\u0001\n\ru\u0001BCB\u0014\u0001\t\u0015\r\u0011\"\u0003\u0004*!Q1Q\u0007\u0001\u0003\u0002\u0003\u0006Iaa\u000b\t\u0015\r]\u0002A!A!\u0002\u0013\u0019I\u0004\u0003\u0006\u0004@\u0001\u0011)\u0019!C\u0005\u0007\u0003B!ba\u0014\u0001\u0005\u0003\u0005\u000b\u0011BB\"\u0011)\u0019\t\u0006\u0001BC\u0002\u0013\u000511\u000b\u0005\u000b\u00077\u0002!\u0011!Q\u0001\n\rU\u0003BCB/\u0001\t\u0005\t\u0015!\u0003\u0004`!Q1Q\r\u0001\u0003\u0002\u0003\u0006Iaa\u001a\t\u000f\r]\u0004\u0001\"\u0001\u0004z!Q11\u0013\u0001\t\u0006\u0004%Ia!\u0011\t\u0019\rU\u0005\u0001#b\u0001\n\u0003\u0011yi!\u000b\t\u0017\r]\u0005A1A\u0005\u0002\t=5\u0011\u0014\u0005\t\u0007C\u0003\u0001\u0015!\u0003\u0004\u001c\"I11\u0015\u0001C\u0002\u0013%1\u0011\u0014\u0005\t\u0007K\u0003\u0001\u0015!\u0003\u0004\u001c\"I1q\u0015\u0001C\u0002\u0013%1\u0011\u0014\u0005\t\u0007S\u0003\u0001\u0015!\u0003\u0004\u001c\"Y11\u0016\u0001C\u0002\u0013\u0005!qRBW\u0011!\u0019)\f\u0001Q\u0001\n\r=\u0006\"CB\\\u0001\t\u0007I\u0011AB]\u0011!\u0019\t\r\u0001Q\u0001\n\rm\u0006\"CBb\u0001\t\u0007I\u0011BBM\u0011!\u0019)\r\u0001Q\u0001\n\rm\u0005bCBd\u0001\t\u0007I\u0011\u0001BF\u0007\u0013D\u0001b!5\u0001A\u0003%11\u001a\u0005\n\u0007'\u0004!\u0019!C\u0005\u0007+D\u0001ba9\u0001A\u0003%1q\u001b\u0005\r\u0007K\u0004\u0001R1A\u0005\u0002\t=5q\u001d\u0005\f\u0007_\u0004!\u0019!C\u0001\u0005\u001f\u001b\t\u0010\u0003\u0005\u0004z\u0002\u0001\u000b\u0011BBz\u0011)\u0019Y\u0010\u0001EC\u0002\u0013%1Q \u0005\u000b\t\u000b\u0001\u0001R1A\u0005\n\ru\bb\u0003C\u0004\u0001\t\u0007I\u0011\u0001BH\u0007[C\u0001\u0002\"\u0003\u0001A\u0003%1q\u0016\u0005\f\t\u0017\u0001\u0001\u0019!a\u0001\n\u0003!i\u0001C\u0006\u0005\u0016\u0001\u0001\r\u00111A\u0005\u0002\u0011]\u0001b\u0003C\u0012\u0001\u0001\u0007\t\u0011)Q\u0005\t\u001fAQ\u0002\"\n\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0003\u0010\u00125\u0001\"\u0004C\u0014\u0001\u0001\u0007\t\u0019!C\u0001\u0005\u001f#I\u0003C\u0006\u0005.\u0001\u0001\r\u0011!Q!\n\u0011=\u0001b\u0003C\u0018\u0001\t\u0007I\u0011\u0001BH\tcA\u0001\u0002\"\u000f\u0001A\u0003%A1\u0007\u0005\n\tw\u0001!\u0019!C\u0005\u0007[C\u0001\u0002\"\u0010\u0001A\u0003%1q\u0016\u0005\n\t\u007f\u0001!\u0019!C\u0005\t\u0003B\u0001\u0002\"\u0013\u0001A\u0003%A1\t\u0005\n\t\u0017\u0002\u0001\u0019!C\u0005\t\u001bB\u0011\u0002\"\u0016\u0001\u0001\u0004%I\u0001b\u0016\t\u0011\u0011m\u0003\u0001)Q\u0005\t\u001fB\u0011\u0002\"\u0018\u0001\u0005\u0004%I\u0001b\u0018\t\u0011\u0011E\u0004\u0001)A\u0005\tCB1\u0002b\u001d\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0005v!YA\u0011\u0012\u0001A\u0002\u0003\u0007I\u0011\u0002CF\u0011-!y\t\u0001a\u0001\u0002\u0003\u0006K\u0001b\u001e\t\u0013\u0011e\u0005A1A\u0005\n\u0011}\u0003\u0002\u0003CN\u0001\u0001\u0006I\u0001\"\u0019\t\u0013\u0011u\u0005\u00011A\u0005\n\ru\b\"\u0003CP\u0001\u0001\u0007I\u0011\u0002CQ\u0011!!)\u000b\u0001Q!\n\r}\bb\u0003CT\u0001\u0001\u0007\t\u0019!C\u0005\tSC1\u0002\"-\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u00054\"YAq\u0017\u0001A\u0002\u0003\u0005\u000b\u0015\u0002CV\u0011-!I\f\u0001a\u0001\n\u0003\u0011y\tb/\t\u0017\u0011\u0015\u0007\u00011A\u0005\u0002\t=Eq\u0019\u0005\t\t\u0017\u0004\u0001\u0015)\u0003\u0005>\"YAq\u001a\u0001C\u0002\u0013\u0005!1\u0012Ci\u0011!1Y\b\u0001Q\u0001\n\u0011M\u0007\"\u0003D?\u0001\t\u0007I\u0011BB\u007f\u0011!1y\b\u0001Q\u0001\n\r}\b\"\u0003DA\u0001\u0001\u0007I\u0011\u0001DB\u0011%1i\t\u0001a\u0001\n\u00031y\t\u0003\u0005\u0007\u0014\u0002\u0001\u000b\u0015\u0002DC\u001191)\n\u0001C\u0001\u0002\u000b\u0005\t\u0011!C\u0007\r[AqAb(\u0001\t\u001b1\t\u000b\u0003\u0006\u0007*\u0002A)\u0019!C\u0001\rWCqAb-\u0001\t\u00032)\fC\u0004\u0007:\u0002!\tEb/\u0007\u0013\u0019M\u0007!!\u0001\u0003\u0010\u001aU\u0007B\u0003Dm1\n\u0005\t\u0015!\u0003\u0004\u0000\"QaQ\u0015-\u0003\u0002\u0003\u0006I\u0001b?\t\u0015\u0019m\u0007L!A!\u0002\u00131i\u000e\u0003\u0006\u0007db\u0013\t\u0011)A\u0005\rKD!bb\u0002Y\u0005\u0003\u0005\u000b\u0011BBN\u0011)9I\u0001\u0017B\u0001B\u0003%11\u0014\u0005\b\u0007oBF\u0011AD\u0006\u0011\u001d9i\u0002\u0017D\t\u000f?Aqab\u000bY\r#9i\u0003C\u0004\b6a3\t\"b3\t\u000f\u001d]\u0002\f\"\u0003\b:!9qQ\t-\u0005\n\u001d\u001d\u0003bBD'1\u0012\u0005aQ\u0006\u0004\t\u000f\u001f\u0002\u0001Ia$\bR!QaQ\u00154\u0003\u0016\u0004%\tab\u001a\t\u0015\u001d%dM!E!\u0002\u0013!Y\u0010\u0003\u0006\u0007\\\u001a\u0014)\u001a!C\u0001\u000fWB!b\"\u001cg\u0005#\u0005\u000b\u0011\u0002Do\u0011)1\u0019O\u001aBK\u0002\u0013\u0005qq\u000e\u0005\u000b\u000fg2'\u0011#Q\u0001\n\u001dE\u0004BCD&M\nU\r\u0011\"\u0001\bv!Qqq\u000f4\u0003\u0012\u0003\u0006Ia\"\t\t\u0015\u001d\u001daM!f\u0001\n\u0003\u0019I\n\u0003\u0006\bz\u0019\u0014\t\u0012)A\u0005\u00077C!b\"\u0003g\u0005+\u0007I\u0011ABM\u0011)9YH\u001aB\tB\u0003%11\u0014\u0005\b\u0007o2G\u0011AD?\u0011\u001d9iB\u001aC!\u000f?Aqab\u000bg\t\u0003:i\u0003C\u0004\b6\u0019$\t%b3\t\u0013\u001d5e-!A\u0005\u0002\u001d=\u0005\"CDTMF\u0005I\u0011ADU\u0011%9\tLZI\u0001\n\u00039\u0019\fC\u0005\b<\u001a\f\n\u0011\"\u0001\b>\"IqQ\u00194\u0012\u0002\u0013\u0005qq\u0019\u0005\n\u000f\u001f4\u0017\u0013!C\u0001\u000f#D\u0011b\"7g#\u0003%\tab7\t\u0013\u001d}g-!A\u0005B\u0015\u0005\u0007\"CDqM\u0006\u0005I\u0011ABW\u0011%9\u0019OZA\u0001\n\u00039)\u000fC\u0005\bj\u001a\f\t\u0011\"\u0011\bl\"Iq\u0011 4\u0002\u0002\u0013\u0005q1 \u0005\n\u000f\u007f4\u0017\u0011!C!\u0011\u0003A\u0011\u0002#\u0002g\u0003\u0003%\t\u0005c\u0002\t\u0013!%a-!A\u0005B!-\u0001\"\u0003E\u0007M\u0006\u0005I\u0011\tE\b\u000f-A\u0019\u0002AA\u0001\u0012\u0003\u0011y\t#\u0006\u0007\u0017\u001d=\u0003!!A\t\u0002\t=\u0005r\u0003\u0005\t\u0007o\n\t\u0002\"\u0001\t\u001e!Q\u0001\u0012BA\t\u0003\u0003%)\u0005c\u0003\t\u0015!}\u0011\u0011CA\u0001\n\u0003C\t\u0003\u0003\u0006\t:\u0005E\u0011\u0013!C\u0001\u0011wA!\u0002c\u0010\u0002\u0012E\u0005I\u0011\u0001E!\u0011)A)%!\u0005\u0002\u0002\u0013\u0005\u0005r\t\u0005\u000b\u0011?\n\t\"%A\u0005\u0002!\u0005\u0004B\u0003E3\u0003#\t\n\u0011\"\u0001\th\u0019A\u00012\u000e\u0001A\u0005\u001fCi\u0007C\u0006\u0007&\u0006\r\"Q3A\u0005\u0002\u001d\u001d\u0004bCD5\u0003G\u0011\t\u0012)A\u0005\twD1Bb7\u0002$\tU\r\u0011\"\u0001\bl!YqQNA\u0012\u0005#\u0005\u000b\u0011\u0002Do\u0011-1\u0019/a\t\u0003\u0016\u0004%\t\u0001c\u001e\t\u0017\u001dM\u00141\u0005B\tB\u0003%\u0001\u0012\u0010\u0005\f\u0011w\n\u0019C!f\u0001\n\u0003Ai\bC\u0006\t\u0000\u0005\r\"\u0011#Q\u0001\n\u0019E\u0001b\u0003Dm\u0003G\u0011)\u001a!C\u0001\u0007{D1\u0002#!\u0002$\tE\t\u0015!\u0003\u0004\u0000\"YqqAA\u0012\u0005+\u0007I\u0011ABM\u0011-9I(a\t\u0003\u0012\u0003\u0006Iaa'\t\u0017\u001d%\u00111\u0005BK\u0002\u0013\u00051\u0011\u0014\u0005\f\u000fw\n\u0019C!E!\u0002\u0013\u0019Y\n\u0003\u0005\u0004x\u0005\rB\u0011\u0001EB\u0011!9i\"a\t\u0005B\u001d}\u0001\u0002CD\u0016\u0003G!\te\"\f\t\u0011\u001dU\u00121\u0005C!\u000b\u0017D\u0001b\"\u0014\u0002$\u0011\u0005cQ\u0006\u0005\u000b\u000f\u001b\u000b\u0019#!A\u0005\u0002!U\u0005BCDT\u0003G\t\n\u0011\"\u0001\t0\"Qq\u0011WA\u0012#\u0003%\t\u0001c-\t\u0015\u001dm\u00161EI\u0001\n\u0003A9\f\u0003\u0006\bF\u0006\r\u0012\u0013!C\u0001\u0011\u007fC!bb4\u0002$E\u0005I\u0011\u0001Ed\u0011)9I.a\t\u0012\u0002\u0013\u0005\u0001r\u001a\u0005\u000b\u0011'\f\u0019#%A\u0005\u0002!U\u0007BCDp\u0003G\t\t\u0011\"\u0011\u0006B\"Qq\u0011]A\u0012\u0003\u0003%\ta!,\t\u0015\u001d\r\u00181EA\u0001\n\u0003AI\u000e\u0003\u0006\bj\u0006\r\u0012\u0011!C!\u000fWD!b\"?\u0002$\u0005\u0005I\u0011\u0001Eo\u0011)9y0a\t\u0002\u0002\u0013\u0005\u0003\u0012\u001d\u0005\u000b\u0011\u000b\t\u0019#!A\u0005B!\u001d\u0001B\u0003E\u0005\u0003G\t\t\u0011\"\u0011\t\f!Q\u0001RBA\u0012\u0003\u0003%\t\u0005#:\b\u0017!%\b!!A\t\u0002\t=\u00052\u001e\u0004\f\u0011W\u0002\u0011\u0011!E\u0001\u0005\u001fCi\u000f\u0003\u0005\u0004x\u0005=D\u0011\u0001Ex\u0011)AI!a\u001c\u0002\u0002\u0013\u0015\u00032\u0002\u0005\u000b\u0011?\ty'!A\u0005\u0002\"E\bB\u0003E \u0003_\n\n\u0011\"\u0001\n\f!Q\u0011rBA8#\u0003%\t!#\u0005\t\u0015!\u0015\u0013qNA\u0001\n\u0003K)\u0002\u0003\u0006\tf\u0005=\u0014\u0013!C\u0001\u0013WA!\"c\f\u0002pE\u0005I\u0011AE\u0019\u0011\u001dI)\u0004\u0001C\u0001\u0013oAq!#\u0010\u0001\t\u0003Iy\u0004C\u0004\nB\u0001!I!b3\t\u000f%\r\u0003\u0001\"\u0003\u0006L\"9\u0011R\t\u0001\u0005\u0002\u0015-\u0007bBE$\u0001\u0011%Q1\u001a\u0005\b\u0013\u0013\u0002A\u0011ACf\u0011\u001dIY\u0005\u0001C!\u0013\u001bBq!#\u0016\u0001\t\u0003J9\u0006C\u0004\n\\\u0001!\t%#\u0018\t\u000f%M\u0004\u0001\"\u0011\nv!9\u00112\u0013\u0001\u0005\u0002%U\u0005bBER\u0001\u0011\u0005\u0011R\u0015\u0005\b\u0013c\u0003A\u0011AEZ\u0011\u001dIy\f\u0001C\u0001\u0013\u0003D\u0011\"c4\u0001\t\u0003\u0011y)#5\t\u0017%u\u0007!%A\u0005\u0002\t=\u0005\u0012\u001a\u0005\b\u0013?\u0004A\u0011BEq\u0011%II\u000fAI\u0001\n\u0013AI\rC\u0004\nl\u0002!I!#<\t\u000f%m\b\u0001\"\u0003\n~\"9!2\u0001\u0001\u0005\n)\u0015\u0001b\u0002F\u0005\u0001\u0011%!2\u0002\u0005\b\u0015/\u0001A\u0011\u0001F\r\u0011\u001dQ)\u0003\u0001C\u0005\u0015OAqAc\f\u0001\t\u0003Q\t\u0004C\u0004\u000b8\u0001!IA#\u000f\t\u0013)}\u0002\u0001\"\u0001\u0003\u0010*\u0005\u0003\"\u0003F*\u0001\u0011\u0005!q\u0012F+\u0011\u001dQ9\u0007\u0001C\u0005\u0015SB\u0011Bc\u001c\u0001\t\u0003\u0011yI#\u001d\t\u000f)U\u0004\u0001\"\u0003\u000bx!I!\u0012\u0013\u0001\u0005\u0002\t=%2\u0013\u0005\b\u0015;\u0003A\u0011\u0001FP\u0011\u001dQ)\u000b\u0001C\u0001\u0015OCqA#/\u0001\t\u0003QY\fC\u0004\u000b@\u0002!\tA#1\t\u0013)E\u0007!%A\u0005\u0002)M\u0007b\u0002Fl\u0001\u0011\u0005!\u0012\u001c\u0005\b\u0015?\u0004A\u0011\u0001Fq\u0011\u001dQ)\u000f\u0001C\u0001\u0015ODqa#\u0006\u0001\t\u0013Y9\u0002C\u0004\f4\u0001!\ta#\u000e\t\u0013-E\u0003!%A\u0005\u0002-M\u0003bBF,\u0001\u0011\u00051\u0012\f\u0005\b\u0017{\u0002A\u0011AF@\u0011%Y9\nAI\u0001\n\u0003YI\nC\u0005\f\u001e\u0002!\tAa$\f \"912\u0015\u0001\u0005\n-\u0015\u0006bBFf\u0001\u0011%1R\u001a\u0005\n\u0017c\u0004\u0011\u0013!C\u0005\u0017gD\u0011bc>\u0001#\u0003%Ia#?\t\u000f-u\b\u0001\"\u0003\f\u0000\"9AR\u0002\u0001\u0005\n1=\u0001\"\u0003G\u0012\u0001\u0011\u0005!1\u0012G\u0013\u0011\u001daY\u0003\u0001C\u0001\u0019[A\u0011\u0002d\u0011\u0001#\u0003%\t\u0001$\u0012\t\u000f1%\u0003\u0001\"\u0003\rL!IA2\r\u0001\u0012\u0002\u0013%AR\r\u0005\n\u0019S\u0002\u0011\u0013!C\u0005\u0019\u000bBq\u0001d\u001b\u0001\t\u0003ai\u0007C\u0004\r\u0002\u0002!\t\u0001d!\t\u00131u\u0005!%A\u0005\u00021}\u0005\"\u0003GR\u0001\u0011\u0005#1\u0012GS\u0011\u001day\f\u0001C\u0001\u0019\u0003Dq\u0001d2\u0001\t\u0003)Y\rC\u0005\rJ\u0002!\tAa$\u0006L\"IA2\u001a\u0001\u0005\u0002\t=ER\u001a\u0005\n\u0019+\u0004A\u0011\u0001BF\u0019/Dq\u0001d=\u0001\t\u0003a)\u0010C\u0004\r~\u0002!\t\u0001d@\t\u000f5\u0015\u0001\u0001\"\u0001\u000e\b!IQR\u0002\u0001\u0012\u0002\u0013\u0005q1\u001b\u0005\b\u001b\u001f\u0001A\u0011BG\t\u0011\u001di9\u0002\u0001C\u0005\u001b3Aq!d\b\u0001\t\u0003i\t\u0003C\u0005\u000e*\u0001\t\n\u0011\"\u0001\u000bT\"9a\u0011\u0002\u0001\u0005\u0002\u0015-wA\u0003Cl\u0005\u0017C\tAa$\u0005Z\u001aQ!\u0011\u0012BF\u0011\u0003\u0011y\tb7\t\u0011\r]$q\u0004C\u0001\t;D!\u0002b8\u0003 \t\u0007I\u0011\u0002Cq\u0011%!yOa\b!\u0002\u0013!\u0019\u000f\u0003\u0005\u0005r\n}A\u0011\u0001Cz\u0011))YBa\b\u0012\u0002\u0013\u0005QQ\u0004\u0004\b\u000bg\u0011y\u0002BC\u001b\u0011-)9Ea\u000b\u0003\u0006\u0004%\tE!5\t\u0017\u0015%#1\u0006B\u0001B\u0003%!1\u001b\u0005\f\u000b\u0017\u0012YC!A!\u0002\u0013)i\u0005\u0003\u0005\u0004x\t-B\u0011AC0\u0011))IGa\u000bC\u0002\u0013\u0005S1\u000e\u0005\n\u000bg\u0012Y\u0003)A\u0005\u000b[2q!\"\u001e\u0003 \u0001)9\bC\u0006\u0006\u0000\te\"\u0011!Q\u0001\n\rm\u0004bCCA\u0005s\u0011\t\u0011)A\u0005\u000b\u0007C\u0001ba\u001e\u0003:\u0011\u0005QQ\u0012\u0004\b\u000b+\u0013I\u0004BCL\u0011-)YK!\u0011\u0003\u0002\u0003\u0006I!\"*\t\u0017\u00155&\u0011\tB\u0001B\u0003%Qq\u0016\u0005\t\u0007o\u0012\t\u0005\"\u0001\u00066\"QQq\u0018B!\u0005\u0004%\t!\"1\t\u0013\u0015\u001d'\u0011\tQ\u0001\n\u0015\r\u0007\u0002CCe\u0005\u0003\"\t!b3\t\u0015\u00155&\u0011\bb\u0001\n\u0013)i\rC\u0005\u0006P\ne\u0002\u0015!\u0003\u00060\"QQ\u0011\u001bB\u001d\u0005\u0004%I!b5\t\u0013\u0015}'\u0011\bQ\u0001\n\u0015U\u0007BCCq\u0005s\u0011\r\u0011\"\u0003\u0004.\"IQ1\u001dB\u001dA\u0003%1q\u0016\u0005\u000b\u000bK\u0014ID1A\u0005\n\u0015\u001d\b\"CCx\u0005s\u0001\u000b\u0011BCu\u0011!)\tP!\u000f\u0005B\u0015M\b\u0002\u0003D\u0002\u0005s!\tE\"\u0002\t\u0011\u0019%!\u0011\bC\u0001\u000b\u0017D\u0001Bb\u0003\u0003:\u0011%Q1\u001a\u0004\b\r\u001b\u0011y\u0002\u0002D\b\u0011-)YKa\u001a\u0003\u0002\u0003\u0006IA\"\u0005\t\u0017\u0019u!q\rB\u0001B\u0003%QQ\u0011\u0005\t\u0007o\u00129\u0007\"\u0001\u0007 !QQq\u0002B4\u0005\u0004%IAb\n\t\u0013\u0019%\"q\rQ\u0001\n\u0015E\u0001\u0002\u0003D\u0016\u0005O\"\tE\"\f\t\u0011\u0019=\"q\rC!\rcA\u0001B\"\u000f\u0003h\u0011\u0005c1\b\u0004\b\r{\u00119\u0007\u0002D \u0011!\u00199H!\u001f\u0005\u0002\u0019\u0005\u0003B\u0003D$\u0005s\u0012\r\u0011\"\u0003\u0007J!Ia\u0011\u000bB=A\u0003%a1\n\u0005\t\r'\u0012I\b\"\u0011\u0007V!Aa1\rB=\t\u00032)\u0007\u0003\u0005\u0007x\teD\u0011\tD\u0017\u0011!1IH!\u001f\u0005B\u0015-'\u0001\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014(\u0002\u0002BG\u0005\u001f\u000bqa\u001d;pe\u0006<WM\u0003\u0003\u0003\u0012\nM\u0015!B:qCJ\\'\u0002\u0002BK\u0005/\u000ba!\u00199bG\",'B\u0001BM\u0003\ry'oZ\n\n\u0001\tu%\u0011\u0016B[\u0005\u0003\u0004BAa(\u0003&6\u0011!\u0011\u0015\u0006\u0003\u0005G\u000bQa]2bY\u0006LAAa*\u0003\"\n1\u0011I\\=SK\u001a\u0004BAa+\u000326\u0011!Q\u0016\u0006\u0005\u0005_\u0013y)A\u0004oKR<xN]6\n\t\tM&Q\u0016\u0002\u0011\u00052|7m\u001b#bi\u0006l\u0015M\\1hKJ\u0004BAa.\u0003>6\u0011!\u0011\u0018\u0006\u0005\u0005w\u0013Y)\u0001\u0004nK6|'/_\u0005\u0005\u0005\u007f\u0013IL\u0001\u000bCY>\u001c7.\u0012<jGRLwN\u001c%b]\u0012dWM\u001d\t\u0005\u0005\u0007\u0014I-\u0004\u0002\u0003F*!!q\u0019BH\u0003!Ig\u000e^3s]\u0006d\u0017\u0002\u0002Bf\u0005\u000b\u0014q\u0001T8hO&tw-\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\u0001!\u0006\u0002\u0003TB!!Q\u001bBr\u001d\u0011\u00119Na8\u0011\t\te'\u0011U\u0007\u0003\u00057TAA!8\u0003P\u00061AH]8pizJAA!9\u0003\"\u00061\u0001K]3eK\u001aLAA!:\u0003h\n11\u000b\u001e:j]\u001eTAA!9\u0003\"\u0006YQ\r_3dkR|'/\u00133!\u0003\u0019\u0011\boY#omB!!q\u001eB{\u001b\t\u0011\tP\u0003\u0003\u0003t\n=\u0015a\u0001:qG&!!q\u001fBy\u0005\u0019\u0011\u0006oY#om\u00061Q.Y:uKJ,\"A!@\u0011\t\t}8\u0011A\u0007\u0003\u0005\u0017KAaa\u0001\u0003\f\n\u0011\"\t\\8dW6\u000bg.Y4fe6\u000b7\u000f^3s\u0003\u001di\u0017m\u001d;fe\u0002\n\u0011c]3sS\u0006d\u0017N_3s\u001b\u0006t\u0017mZ3s+\t\u0019Y\u0001\u0005\u0003\u0004\u000e\rMQBAB\b\u0015\u0011\u0019\tBa$\u0002\u0015M,'/[1mSj,'/\u0003\u0003\u0004\u0016\r=!!E*fe&\fG.\u001b>fe6\u000bg.Y4fe\u0006\u00112/\u001a:jC2L'0\u001a:NC:\fw-\u001a:!\u0003\u0011\u0019wN\u001c4\u0016\u0005\ru\u0001\u0003BB\u0010\u0007Ci!Aa$\n\t\r\r\"q\u0012\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\nabX7f[>\u0014\u00180T1oC\u001e,'/\u0006\u0002\u0004,A!1QFB\u0019\u001b\t\u0019yC\u0003\u0003\u0003<\n=\u0015\u0002BB\u001a\u0007_\u0011Q\"T3n_JLX*\u00198bO\u0016\u0014\u0018aD0nK6|'/_'b]\u0006<WM\u001d\u0011\u0002!5\f\u0007oT;uaV$HK]1dW\u0016\u0014\b\u0003BB\u0010\u0007wIAa!\u0010\u0003\u0010\n\u0001R*\u00199PkR\u0004X\u000f\u001e+sC\u000e\\WM]\u0001\u0010?NDWO\u001a4mK6\u000bg.Y4feV\u001111\t\t\u0005\u0007\u000b\u001aY%\u0004\u0002\u0004H)!1\u0011\nBH\u0003\u001d\u0019\b.\u001e4gY\u0016LAa!\u0014\u0004H\tq1\u000b[;gM2,W*\u00198bO\u0016\u0014\u0018\u0001E0tQV4g\r\\3NC:\fw-\u001a:!\u0003Q\u0011Gn\\2l)J\fgn\u001d4feN+'O^5dKV\u00111Q\u000b\t\u0005\u0005W\u001b9&\u0003\u0003\u0004Z\t5&\u0001\u0006\"m_\u000e\\GK]1og\u001a,'oU3sm&\u001cW-A\u000bcY>\u001c7\u000e\u0016:b]N4WM]*feZL7-\u001a\u0011\u0002\u001fM,7-\u001e:jifl\u0015M\\1hKJ\u0004Baa\b\u0004b%!11\rBH\u0005=\u0019VmY;sSRLX*\u00198bO\u0016\u0014\u0018\u0001G3yi\u0016\u0014h.\u00197CY>\u001c7n\u0015;pe\u0016\u001cE.[3oiB1!qTB5\u0007[JAaa\u001b\u0003\"\n1q\n\u001d;j_:\u0004Baa\u001c\u0004t5\u00111\u0011\u000f\u0006\u0005\u0007\u0013\u0012i+\u0003\u0003\u0004v\rE$\u0001G#yi\u0016\u0014h.\u00197CY>\u001c7n\u0015;pe\u0016\u001cE.[3oi\u00061A(\u001b8jiz\"\u0002da\u001f\u0004~\r}4\u0011QBB\u0007\u000b\u001b9i!#\u0004\f\u000e55qRBI!\r\u0011y\u0010\u0001\u0005\b\u0005\u001b\u001c\u0002\u0019\u0001Bj\u0011\u001d\u0011Yo\u0005a\u0001\u0005[DqA!?\u0014\u0001\u0004\u0011i\u0010C\u0004\u0004\bM\u0001\raa\u0003\t\u000f\re1\u00031\u0001\u0004\u001e!91qE\nA\u0002\r-\u0002bBB\u001c'\u0001\u00071\u0011\b\u0005\b\u0007\u007f\u0019\u0002\u0019AB\"\u0011\u001d\u0019\tf\u0005a\u0001\u0007+Bqa!\u0018\u0014\u0001\u0004\u0019y\u0006C\u0004\u0004fM\u0001\raa\u001a\u0002\u001dMDWO\u001a4mK6\u000bg.Y4fe\u0006iQ.Z7pefl\u0015M\\1hKJ\fQ$\u001a=uKJt\u0017\r\\*ik\u001a4G.Z*feZL7-Z#oC\ndW\rZ\u000b\u0003\u00077\u0003BAa(\u0004\u001e&!1q\u0014BQ\u0005\u001d\u0011un\u001c7fC:\fa$\u001a=uKJt\u0017\r\\*ik\u001a4G.Z*feZL7-Z#oC\ndW\r\u001a\u0011\u0002\u0011%\u001cHI]5wKJ\f\u0011\"[:Ee&4XM\u001d\u0011\u0002;I,Wn\u001c;f%\u0016\fGMT5p\u0005V4g-\u001a:D_:4XM]:j_:\faD]3n_R,'+Z1e\u001d&|')\u001e4gKJ\u001cuN\u001c<feNLwN\u001c\u0011\u0002%M,(\rR5sgB+'\u000fT8dC2$\u0015N]\u000b\u0003\u0007_\u0003BAa(\u00042&!11\u0017BQ\u0005\rIe\u000e^\u0001\u0014gV\u0014G)\u001b:t!\u0016\u0014Hj\\2bY\u0012K'\u000fI\u0001\u0011I&\u001c8N\u00117pG.l\u0015M\\1hKJ,\"aa/\u0011\t\t}8QX\u0005\u0005\u0007\u007f\u0013YI\u0001\tESN\\'\t\\8dW6\u000bg.Y4fe\u0006\tB-[:l\u00052|7m['b]\u0006<WM\u001d\u0011\u0002/Q\u0014\u0018mY6j]\u001e\u001c\u0015m\u00195f-&\u001c\u0018NY5mSRL\u0018\u0001\u0007;sC\u000e\\\u0017N\\4DC\u000eDWMV5tS\nLG.\u001b;zA\u0005\u0001\"\r\\8dW&sgm\\'b]\u0006<WM]\u000b\u0003\u0007\u0017\u0004BAa@\u0004N&!1q\u001aBF\u0005A\u0011En\\2l\u0013:4w.T1oC\u001e,'/A\tcY>\u001c7.\u00138g_6\u000bg.Y4fe\u0002\naCZ;ukJ,W\t_3dkRLwN\\\"p]R,\u0007\u0010^\u000b\u0003\u0007/\u0004Ba!7\u0004`6\u001111\u001c\u0006\u0005\u0007;\u0014\t+\u0001\u0006d_:\u001cWO\u001d:f]RLAa!9\u0004\\\nyR\t_3dkRLwN\\\"p]R,\u0007\u0010^#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002/\u0019,H/\u001e:f\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR\u0004\u0013aC7f[>\u0014\u0018p\u0015;pe\u0016,\"a!;\u0011\t\t]61^\u0005\u0005\u0007[\u0014ILA\u0006NK6|'/_*u_J,\u0017!\u00033jg.\u001cFo\u001c:f+\t\u0019\u0019\u0010\u0005\u0003\u0003\u0000\u000eU\u0018\u0002BB|\u0005\u0017\u0013\u0011\u0002R5tWN#xN]3\u0002\u0015\u0011L7o[*u_J,\u0007%A\bnCb|e\u000eS3ba6+Wn\u001c:z+\t\u0019y\u0010\u0005\u0003\u0003 \u0012\u0005\u0011\u0002\u0002C\u0002\u0005C\u0013A\u0001T8oO\u0006\u0001R.\u0019=PM\u001aDU-\u00199NK6|'/_\u0001\u001bKb$XM\u001d8bYNCWO\u001a4mKN+'O^5dKB{'\u000f^\u0001\u001cKb$XM\u001d8bYNCWO\u001a4mKN+'O^5dKB{'\u000f\u001e\u0011\u0002\u001d\tdwnY6NC:\fw-\u001a:JIV\u0011Aq\u0002\t\u0005\u0005\u007f$\t\"\u0003\u0003\u0005\u0014\t-%A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0001\u0013E2|7m['b]\u0006<WM]%e?\u0012*\u0017\u000f\u0006\u0003\u0005\u001a\u0011}\u0001\u0003\u0002BP\t7IA\u0001\"\b\u0003\"\n!QK\\5u\u0011%!\tCLA\u0001\u0002\u0004!y!A\u0002yIE\nqB\u00197pG.l\u0015M\\1hKJLE\rI\u0001\u0010g\",hM\u001a7f'\u0016\u0014h/\u001a:JI\u0006\u00192\u000f[;gM2,7+\u001a:wKJLEm\u0018\u0013fcR!A\u0011\u0004C\u0016\u0011%!\t#MA\u0001\u0002\u0004!y!\u0001\ttQV4g\r\\3TKJ4XM]%eA\u0005\u0001\"\r\\8dWN#xN]3DY&,g\u000e^\u000b\u0003\tg\u0001Baa\u001c\u00056%!AqGB9\u0005A\u0011En\\2l'R|'/Z\"mS\u0016tG/A\tcY>\u001c7n\u0015;pe\u0016\u001cE.[3oi\u0002\n\u0001%\\1y\r\u0006LG.\u001e:fg\n+gm\u001c:f\u0019>\u001c\u0017\r^5p]J+gM]3tQ\u0006\tS.\u0019=GC&dWO]3t\u0005\u00164wN]3M_\u000e\fG/[8o%\u00164'/Z:iA\u0005y1\u000f^8sC\u001e,WI\u001c3q_&tG/\u0006\u0002\u0005DA!!q\u001eC#\u0013\u0011!9E!=\u0003\u001dI\u00038-\u00128ea>Lg\u000e\u001e*fM\u0006\u00012\u000f^8sC\u001e,WI\u001c3q_&tG\u000fI\u0001\u0014CNLhn\u0019*fe\u0016<\u0017n\u001d;feR\u000b7o[\u000b\u0003\t\u001f\u0002ba!7\u0005R\u0011e\u0011\u0002\u0002C*\u00077\u0014aAR;ukJ,\u0017aF1ts:\u001c'+\u001a:fO&\u001cH/\u001a:UCN\\w\fJ3r)\u0011!I\u0002\"\u0017\t\u0013\u0011\u0005\"(!AA\u0002\u0011=\u0013\u0001F1ts:\u001c'+\u001a:fO&\u001cH/\u001a:UCN\\\u0007%A\nbgft7MU3sK\u001eL7\u000f^3s\u0019>\u001c7.\u0006\u0002\u0005bA!A1\rC7\u001b\t!)G\u0003\u0003\u0005h\u0011%\u0014\u0001\u00027b]\u001eT!\u0001b\u001b\u0002\t)\fg/Y\u0005\u0005\t_\")G\u0001\u0004PE*,7\r^\u0001\u0015CNLhn\u0019*fe\u0016<\u0017n\u001d;fe2{7m\u001b\u0011\u0002\u0017\r\f7\r[3e!\u0016,'o]\u000b\u0003\to\u0002b\u0001\"\u001f\u0005\u0004\u0012=a\u0002\u0002C>\t\u007frAA!7\u0005~%\u0011!1U\u0005\u0005\t\u0003\u0013\t+A\u0004qC\u000e\\\u0017mZ3\n\t\u0011\u0015Eq\u0011\u0002\u0004'\u0016\f(\u0002\u0002CA\u0005C\u000bqbY1dQ\u0016$\u0007+Z3sg~#S-\u001d\u000b\u0005\t3!i\tC\u0005\u0005\"}\n\t\u00111\u0001\u0005x\u0005a1-Y2iK\u0012\u0004V-\u001a:tA!\u001a\u0001\tb%\u0011\t\t}EQS\u0005\u0005\t/\u0013\tK\u0001\u0005w_2\fG/\u001b7f\u00035\u0001X-\u001a:GKR\u001c\u0007\u000eT8dW\u0006q\u0001/Z3s\r\u0016$8\r\u001b'pG.\u0004\u0013a\u00057bgR\u0004V-\u001a:GKR\u001c\u0007\u000eV5nK:\u001b\u0018a\u00067bgR\u0004V-\u001a:GKR\u001c\u0007\u000eV5nK:\u001bx\fJ3r)\u0011!I\u0002b)\t\u0013\u0011\u0005B)!AA\u0002\r}\u0018\u0001\u00067bgR\u0004V-\u001a:GKR\u001c\u0007\u000eV5nK:\u001b\b%\u0001\fcY>\u001c7NU3qY&\u001c\u0017\r^5p]B{G.[2z+\t!Y\u000b\u0005\u0003\u0003\u0000\u00125\u0016\u0002\u0002CX\u0005\u0017\u0013aC\u00117pG.\u0014V\r\u001d7jG\u0006$\u0018n\u001c8Q_2L7-_\u0001\u001bE2|7m\u001b*fa2L7-\u0019;j_:\u0004v\u000e\\5ds~#S-\u001d\u000b\u0005\t3!)\fC\u0005\u0005\"\u001d\u000b\t\u00111\u0001\u0005,\u00069\"\r\\8dWJ+\u0007\u000f\\5dCRLwN\u001c)pY&\u001c\u0017\u0010I\u0001\u000fI\u0016\u001cw.\\7jgNLwN\\3s+\t!i\f\u0005\u0004\u0003 \u000e%Dq\u0018\t\u0005\u0005\u007f$\t-\u0003\u0003\u0005D\n-%A\u0007\"m_\u000e\\W*\u00198bO\u0016\u0014H)Z2p[6L7o]5p]\u0016\u0014\u0018A\u00053fG>lW.[:tS>tWM]0%KF$B\u0001\"\u0007\u0005J\"IA\u0011\u0005&\u0002\u0002\u0003\u0007AQX\u0001\u0010I\u0016\u001cw.\\7jgNLwN\\3sA!\u001a1\nb%\u00025I,Wn\u001c;f\u00052|7m\u001b+f[B4\u0015\u000e\\3NC:\fw-\u001a:\u0016\u0005\u0011M\u0007\u0003\u0002Ck\u0005sqAAa@\u0003\u001e\u0005a!\t\\8dW6\u000bg.Y4feB!!q B\u0010'\u0011\u0011yB!(\u0015\u0005\u0011e\u0017\u0001D%E?\u001e+e*\u0012*B)>\u0013VC\u0001Cr!\u0011!)\u000fb;\u000e\u0005\u0011\u001d(\u0002\u0002Cu\u0005\u001f\u000bA!\u001e;jY&!AQ\u001eCt\u0005-IEmR3oKJ\fGo\u001c:\u0002\u001b%#ulR#O\u000bJ\u000bEk\u0014*!\u0003M\u0011Gn\\2l\u0013\u0012\u001cHk\u001c'pG\u0006$\u0018n\u001c8t)!!)0b\u0001\u0006\u000e\u0015]\u0001\u0003\u0003Bk\to$Y0\"\u0001\n\t\u0011e(q\u001d\u0002\u0004\u001b\u0006\u0004\b\u0003\u0002B\u0000\t{LA\u0001b@\u0003\f\n9!\t\\8dW&#\u0007C\u0002C=\t\u0007\u0013\u0019\u000e\u0003\u0005\u0006\u0006\t\u001d\u0002\u0019AC\u0004\u0003!\u0011Gn\\2l\u0013\u0012\u001c\bC\u0002BP\u000b\u0013!Y0\u0003\u0003\u0006\f\t\u0005&!B!se\u0006L\b\u0002CC\b\u0005O\u0001\r!\"\u0005\u0002\u0007\u0015tg\u000f\u0005\u0003\u0004 \u0015M\u0011\u0002BC\u000b\u0005\u001f\u0013\u0001b\u00159be.,eN\u001e\u0005\u000b\u000b3\u00119\u0003%AA\u0002\tu\u0018A\u00052m_\u000e\\W*\u00198bO\u0016\u0014X*Y:uKJ\fQD\u00197pG.LEm\u001d+p\u0019>\u001c\u0017\r^5p]N$C-\u001a4bk2$HeM\u000b\u0003\u000b?QCA!@\u0006\"-\u0012Q1\u0005\t\u0005\u000bK)y#\u0004\u0002\u0006()!Q\u0011FC\u0016\u0003%)hn\u00195fG.,GM\u0003\u0003\u0006.\t\u0005\u0016AC1o]>$\u0018\r^5p]&!Q\u0011GC\u0014\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0002\u0015'\",hM\u001a7f\u001b\u0016$(/[2t'>,(oY3\u0014\r\t-\"QTC\u001c!\u0011)I$b\u0011\u000e\u0005\u0015m\"\u0002BC\u001f\u000b\u007f\taa]8ve\u000e,'\u0002BC!\u0005\u001f\u000bq!\\3ue&\u001c7/\u0003\u0003\u0006F\u0015m\"AB*pkJ\u001cW-\u0001\u0006t_V\u00148-\u001a(b[\u0016\f1b]8ve\u000e,g*Y7fA\u0005IQ.\u001a;sS\u000e\u001cV\r\u001e\t\u0005\u000b\u001f*Y&\u0004\u0002\u0006R)!Q\u0011IC*\u0015\u0011))&b\u0016\u0002\u0011\r|G-\u00195bY\u0016T!!\"\u0017\u0002\u0007\r|W.\u0003\u0003\u0006^\u0015E#!C'fiJL7mU3u)\u0019)\t'\"\u001a\u0006hA!Q1\rB\u0016\u001b\t\u0011y\u0002\u0003\u0005\u0006H\tM\u0002\u0019\u0001Bj\u0011!)YEa\rA\u0002\u00155\u0013AD7fiJL7MU3hSN$(/_\u000b\u0003\u000b[\u0002B!b\u0014\u0006p%!Q\u0011OC)\u00059iU\r\u001e:jGJ+w-[:uef\fq\"\\3ue&\u001c'+Z4jgR\u0014\u0018\u0010\t\u0002\u001f%\u0016lw\u000e^3CY>\u001c7\u000eR8x]2|\u0017\r\u001a$jY\u0016l\u0015M\\1hKJ\u001c\u0002B!\u000f\u0005b\u0015e$\u0011\u0019\t\u0005\u0007_*Y(\u0003\u0003\u0006~\rE$a\u0005#po:dw.\u00193GS2,W*\u00198bO\u0016\u0014\u0018\u0001\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018!D3oGJL\b\u000f^5p].+\u0017\u0010\u0005\u0004\u0003 \u000e%TQ\u0011\t\u0007\u0005?+I!b\"\u0011\t\t}U\u0011R\u0005\u0005\u000b\u0017\u0013\tK\u0001\u0003CsR,GCBCH\u000b#+\u0019\n\u0005\u0003\u0006d\te\u0002\u0002CC@\u0005\u007f\u0001\raa\u001f\t\u0011\u0015\u0005%q\ba\u0001\u000b\u0007\u0013ACU3gKJ,gnY3XSRD7\t\\3b]V\u00048\u0003\u0002B!\u000b3\u0003b!b'\u0006\"\u0016\u0015VBACO\u0015\u0011)y\n\"\u001a\u0002\u0007I,g-\u0003\u0003\u0006$\u0016u%!D,fC.\u0014VMZ3sK:\u001cW\r\u0005\u0003\u0004p\u0015\u001d\u0016\u0002BCU\u0007c\u0012A\u0002R8x]2|\u0017\r\u001a$jY\u0016\fAAZ5mK\u0006q!/\u001a4fe\u0016t7-Z)vKV,\u0007CBCN\u000bc+)+\u0003\u0003\u00064\u0016u%A\u0004*fM\u0016\u0014XM\\2f#V,W/\u001a\u000b\u0007\u000bo+Y,\"0\u0011\t\u0015e&\u0011I\u0007\u0003\u0005sA\u0001\"b+\u0003H\u0001\u0007QQ\u0015\u0005\t\u000b[\u00139\u00051\u0001\u00060\u0006Aa-\u001b7f!\u0006$\b.\u0006\u0002\u0006DB!A1MCc\u0013\u0011\u0011)\u000f\"\u001a\u0002\u0013\u0019LG.\u001a)bi\"\u0004\u0013aB2mK\u0006tW\u000b\u001d\u000b\u0003\t3)\"!b,\u0002\u001fI,g-\u001a:f]\u000e,\u0017+^3vK\u0002\nqB]3gKJ,gnY3Ck\u001a4WM]\u000b\u0003\u000b+\u0004b!b6\u0006\\\u0016]VBACm\u0015\u0011!I\u000f\"\u001b\n\t\u0015uW\u0011\u001c\u0002\u0004'\u0016$\u0018\u0001\u0005:fM\u0016\u0014XM\\2f\u0005V4g-\u001a:!\u00031\u0001v\n\u0014'`)&kUiT+U\u00035\u0001v\n\u0014'`)&kUiT+UA\u0005q1\r\\3b]&tw\r\u00165sK\u0006$WCACu!\u0011!\u0019'b;\n\t\u00155HQ\r\u0002\u0007)\"\u0014X-\u00193\u0002\u001f\rdW-\u00198j]\u001e$\u0006N]3bI\u0002\nab\u0019:fCR,G+Z7q\r&dW\r\u0006\u0003\u0006&\u0016U\b\u0002CC|\u0005?\u0002\r!\"?\u0002\u001bQ\u0014\u0018M\\:q_J$8i\u001c8g!\u0011)Y0b@\u000e\u0005\u0015u(\u0002\u0002Cu\u0005[KAA\"\u0001\u0006~\niAK]1ogB|'\u000f^\"p]\u001a\fqC]3hSN$XM\u001d+f[B4\u0015\u000e\\3U_\u000ecW-\u00198\u0015\t\rmeq\u0001\u0005\t\u000bW\u0013\t\u00071\u0001\u0006&\u0006!1\u000f^8q\u00031YW-\u001a9DY\u0016\fg.\u001b8h\u0005U)en\u0019:zaR,G\rR8x]2|\u0017\r\u001a$jY\u0016\u001cbAa\u001a\u0005b\u0015\u0015\u0006\u0003\u0002D\n\r3i!A\"\u0006\u000b\t\u0019]A\u0011N\u0001\u0003S>LAAb\u0007\u0007\u0016\t!a)\u001b7f\u0003\rYW-\u001f\u000b\u0007\rC1\u0019C\"\n\u0011\t\u0015\r$q\r\u0005\t\u000bW\u0013i\u00071\u0001\u0007\u0012!AaQ\u0004B7\u0001\u0004)))\u0006\u0002\u0006\u0012\u0005!QM\u001c<!\u0003\u0019!W\r\\3uKR\u001111T\u0001\u000f_B,gNR8s/JLG/\u001b8h)\t1\u0019\u0004\u0005\u0003\u0004p\u0019U\u0012\u0002\u0002D\u001c\u0007c\u00121\u0004R8x]2|\u0017\r\u001a$jY\u0016<&/\u001b;bE2,7\t[1o]\u0016d\u0017\u0001\u00029bi\"$\"Aa5\u0003A\u0015s7M]=qi\u0016$Gi\\<oY>\fGm\u0016:ji\u0006\u0014G.Z\"iC:tW\r\\\n\u0007\u0005s\"\tGb\r\u0015\u0005\u0019\r\u0003\u0003\u0002D#\u0005sj!Aa\u001a\u0002\u001d\r|WO\u001c;j]\u001e|U\u000f\u001e9viV\u0011a1\n\t\u0005\u0005\u007f4i%\u0003\u0003\u0007P\t-%aF\"pk:$\u0018N\\4Xe&$\u0018M\u00197f\u0007\"\fgN\\3m\u0003=\u0019w.\u001e8uS:<w*\u001e;qkR\u0004\u0013\u0001D2m_N,\u0017I\u001c3SK\u0006$GC\u0001D,!\u00111IFb\u0018\u000e\u0005\u0019m#\u0002\u0002D/\u0005[\u000baAY;gM\u0016\u0014\u0018\u0002\u0002D1\r7\u0012Q\"T1oC\u001e,GMQ;gM\u0016\u0014\u0018!B<sSR,G\u0003BBX\rOB\u0001B\"\u001b\u0003\u0004\u0002\u0007a1N\u0001\u0004gJ\u001c\u0007\u0003\u0002D7\rgj!Ab\u001c\u000b\t\u0019ED\u0011N\u0001\u0004]&|\u0017\u0002\u0002D;\r_\u0012!BQ=uK\n+hMZ3s\u0003\u0019I7o\u00149f]\u0006)1\r\\8tK\u0006Y\"/Z7pi\u0016\u0014En\\2l)\u0016l\u0007OR5mK6\u000bg.Y4fe\u0002\n1#\\1y%\u0016lw\u000e^3CY>\u001c7\u000eV8NK6\fA#\\1y%\u0016lw\u000e^3CY>\u001c7\u000eV8NK6\u0004\u0013a\u00055pgRdunY1m\t&\u0014X*\u00198bO\u0016\u0014XC\u0001DC!\u0019\u0011yj!\u001b\u0007\bB!!q DE\u0013\u00111YIa#\u0003'!{7\u000f\u001e'pG\u0006dG)\u001b:NC:\fw-\u001a:\u0002/!|7\u000f\u001e'pG\u0006dG)\u001b:NC:\fw-\u001a:`I\u0015\fH\u0003\u0002C\r\r#C\u0011\u0002\"\tR\u0003\u0003\u0005\rA\"\"\u0002)!|7\u000f\u001e'pG\u0006dG)\u001b:NC:\fw-\u001a:!\u0003az'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ:u_J\fw-\u001a\u0013CY>\u001c7.T1oC\u001e,'\u000f\n\u0013jg\u0012+7m\\7nSN\u001c\u0018n\u001c8j]\u001eD3a\u0015DM!\u0011\u0011yJb'\n\t\u0019u%\u0011\u0015\u0002\u0007S:d\u0017N\\3\u0002!\rDWmY6TQ>,H\u000eZ*u_J,G\u0003\u0002C\r\rGCqA\"*U\u0001\u0004!Y0A\u0004cY>\u001c7.\u00133)\u0007Q3I*\u0001\nnS\u001e\u0014\u0018\r^1cY\u0016\u0014Vm]8mm\u0016\u0014XC\u0001DW!\u0011\u0019)Eb,\n\t\u0019E6q\t\u0002\u0013\u001b&<'/\u0019;bE2,'+Z:pYZ,'/\u0001\thKRdunY1m\t&\u001c8\u000eR5sgV\u0011aq\u0017\t\u0007\u0005?+IAa5\u0002=\u0011L\u0017m\u001a8pg\u0016\u001c\u0006.\u001e4gY\u0016\u0014En\\2l\u0007>\u0014(/\u001e9uS>tG\u0003\u0003D_\r\u00134YMb4\u0011\t\u0019}fQY\u0007\u0003\r\u0003TAAb1\u0004r\u0005A1\r[3dWN,X.\u0003\u0003\u0007H\u001a\u0005'!B\"bkN,\u0007b\u0002DS/\u0002\u0007A1 \u0005\b\r\u001b<\u0006\u0019AB\u0000\u0003A\u0019\u0007.Z2lgVl')\u001f*fC\u0012,'\u000fC\u0004\u0007R^\u0003\rAa5\u0002\u0013\u0005dwm\u001c:ji\"l'!\u0005\"m_\u000e\\7\u000b^8sKV\u0003H-\u0019;feV!aq\u001bD{'\rA&QT\u0001\nE2|7m[*ju\u0016\fQ\u0001\\3wK2\u0004BAa@\u0007`&!a\u0011\u001dBF\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0003!\u0019G.Y:t)\u0006<\u0007C\u0002Dt\r[4\t0\u0004\u0002\u0007j*!a1\u001eBQ\u0003\u001d\u0011XM\u001a7fGRLAAb<\u0007j\nA1\t\\1tgR\u000bw\r\u0005\u0003\u0007t\u001aUH\u0002\u0001\u0003\b\roD&\u0019\u0001D}\u0005\u0005!\u0016\u0003\u0002D~\u000f\u0003\u0001BAa(\u0007~&!aq BQ\u0005\u001dqu\u000e\u001e5j]\u001e\u0004BAa(\b\u0004%!qQ\u0001BQ\u0005\r\te._\u0001\u000bi\u0016dG.T1ti\u0016\u0014\u0018\u0001D6fKB\u0014V-\u00193M_\u000e\\GCDD\u0007\u000f#9\u0019b\"\u0006\b\u0018\u001deq1\u0004\t\u0006\u000f\u001fAf\u0011_\u0007\u0002\u0001!9a\u0011\\0A\u0002\r}\bb\u0002DS?\u0002\u0007A1 \u0005\b\r7|\u0006\u0019\u0001Do\u0011\u001d1\u0019o\u0018a\u0001\rKDqab\u0002`\u0001\u0004\u0019Y\nC\u0004\b\n}\u0003\raa'\u0002!I,\u0017\r\u001a+p\u0005f$XMQ;gM\u0016\u0014HCAD\u0011!\u00119\u0019cb\n\u000e\u0005\u001d\u0015\"\u0002\u0002D\f\tOLAa\"\u000b\b&\t\t2\t[;oW\u0016$')\u001f;f\u0005V4g-\u001a:\u0002\u0013\tdwnY6ECR\fGCAD\u0018!\u0011\u0011yp\"\r\n\t\u001dM\"1\u0012\u0002\n\u00052|7m\u001b#bi\u0006\fqb]1wKR{G)[:l'R|'/Z\u0001$g\u00064X\rR3tKJL\u0017\r\\5{K\u00124\u0016\r\\;fgR{W*Z7pef\u001cFo\u001c:f)\u0011\u0019Yjb\u000f\t\u000f\u001du2\r1\u0001\b@\u0005Y\u0011N\u001c9viN#(/Z1n!\u00111\u0019b\"\u0011\n\t\u001d\rcQ\u0003\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW.A\u0011tCZ,7+\u001a:jC2L'0\u001a3WC2,Xm\u001d+p\u001b\u0016lwN]=Ti>\u0014X\r\u0006\u0003\u0004\u001c\u001e%\u0003bBD&I\u0002\u0007q\u0011E\u0001\u0006Ef$Xm]\u0001\u0005g\u00064XMA\u000eCsR,')\u001e4gKJ\u0014En\\2l'R|'/Z+qI\u0006$XM]\u000b\u0005\u000f':IfE\u0004g\u000f+:Yf\"\u0019\u0011\u000b\u001d=\u0001lb\u0016\u0011\t\u0019Mx\u0011\f\u0003\b\ro4'\u0019\u0001D}!\u0011\u0011yj\"\u0018\n\t\u001d}#\u0011\u0015\u0002\b!J|G-^2u!\u0011!Ihb\u0019\n\t\u001d\u0015Dq\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u000b\u0003\tw\f\u0001B\u00197pG.LE\rI\u000b\u0003\r;\fa\u0001\\3wK2\u0004SCAD9!\u001919O\"<\bX\u0005I1\r\\1tgR\u000bw\rI\u000b\u0003\u000fC\taAY=uKN\u0004\u0013a\u0003;fY2l\u0015m\u001d;fe\u0002\nQb[3faJ+\u0017\r\u001a'pG.\u0004CCDD@\u000f\u0003;\u0019i\"\"\b\b\u001e%u1\u0012\t\u0006\u000f\u001f1wq\u000b\u0005\b\rK\u001b\b\u0019\u0001C~\u0011\u001d1Yn\u001da\u0001\r;DqAb9t\u0001\u00049\t\bC\u0004\bLM\u0004\ra\"\t\t\u0013\u001d\u001d1\u000f%AA\u0002\rm\u0005\"CD\u0005gB\u0005\t\u0019ABN\u0003\u0011\u0019w\u000e]=\u0016\t\u001dEuq\u0013\u000b\u000f\u000f';Ijb'\b\u001e\u001e\u0005v1UDS!\u00159yAZDK!\u00111\u0019pb&\u0005\u000f\u0019]xO1\u0001\u0007z\"IaQU<\u0011\u0002\u0003\u0007A1 \u0005\n\r7<\b\u0013!a\u0001\r;D\u0011Bb9x!\u0003\u0005\rab(\u0011\r\u0019\u001dhQ^DK\u0011%9Ye\u001eI\u0001\u0002\u00049\t\u0003C\u0005\b\b]\u0004\n\u00111\u0001\u0004\u001c\"Iq\u0011B<\u0011\u0002\u0003\u000711T\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00119Ykb,\u0016\u0005\u001d5&\u0006\u0002C~\u000bC!qAb>y\u0005\u00041I0\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u001dUv\u0011X\u000b\u0003\u000foSCA\"8\u0006\"\u00119aq_=C\u0002\u0019e\u0018AD2paf$C-\u001a4bk2$HeM\u000b\u0005\u000f\u007f;\u0019-\u0006\u0002\bB*\"q\u0011OC\u0011\t\u001d19P\u001fb\u0001\rs\fabY8qs\u0012\"WMZ1vYR$C'\u0006\u0003\bJ\u001e5WCADfU\u00119\t#\"\t\u0005\u000f\u0019]8P1\u0001\u0007z\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012*T\u0003BDj\u000f/,\"a\"6+\t\rmU\u0011\u0005\u0003\b\rod(\u0019\u0001D}\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*Bab5\b^\u00129aq_?C\u0002\u0019e\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u001d\u0005qq\u001d\u0005\u000b\tC\t\t!!AA\u0002\r=\u0016a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u001d5\bCBDx\u000fk<\t!\u0004\u0002\br*!q1\u001fBQ\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u000fo<\tP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BBN\u000f{D!\u0002\"\t\u0002\u0006\u0005\u0005\t\u0019AD\u0001\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0015\r\u00072\u0001\u0005\u000b\tC\t9!!AA\u0002\r=\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\r=\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0015\r\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0004\u001c\"E\u0001B\u0003C\u0011\u0003\u001b\t\t\u00111\u0001\b\u0002\u0005Y\")\u001f;f\u0005V4g-\u001a:CY>\u001c7n\u0015;pe\u0016,\u0006\u000fZ1uKJ\u0004Bab\u0004\u0002\u0012M1\u0011\u0011\u0003BO\u00113\u0001BAb\u0005\t\u001c%!qQ\rD\u000b)\tA)\"A\u0003baBd\u00170\u0006\u0003\t$!%BC\u0004E\u0013\u0011WAi\u0003c\f\t4!U\u0002r\u0007\t\u0006\u000f\u001f1\u0007r\u0005\t\u0005\rgDI\u0003\u0002\u0005\u0007x\u0006]!\u0019\u0001D}\u0011!1)+a\u0006A\u0002\u0011m\b\u0002\u0003Dn\u0003/\u0001\rA\"8\t\u0011\u0019\r\u0018q\u0003a\u0001\u0011c\u0001bAb:\u0007n\"\u001d\u0002\u0002CD&\u0003/\u0001\ra\"\t\t\u0015\u001d\u001d\u0011q\u0003I\u0001\u0002\u0004\u0019Y\n\u0003\u0006\b\n\u0005]\u0001\u0013!a\u0001\u00077\u000bq\"\u00199qYf$C-\u001a4bk2$H%N\u000b\u0005\u000f'Di\u0004\u0002\u0005\u0007x\u0006e!\u0019\u0001D}\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122T\u0003BDj\u0011\u0007\"\u0001Bb>\u0002\u001c\t\u0007a\u0011`\u0001\bk:\f\u0007\u000f\u001d7z+\u0011AI\u0005c\u0016\u0015\t!-\u0003\u0012\f\t\u0007\u0005?\u001bI\u0007#\u0014\u0011!\t}\u0005r\nC~\r;D\u0019f\"\t\u0004\u001c\u000em\u0015\u0002\u0002E)\u0005C\u0013a\u0001V;qY\u00164\u0004C\u0002Dt\r[D)\u0006\u0005\u0003\u0007t\"]C\u0001\u0003D|\u0003;\u0011\rA\"?\t\u0015!m\u0013QDA\u0001\u0002\u0004Ai&A\u0002yIA\u0002Rab\u0004g\u0011+\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012*T\u0003BDj\u0011G\"\u0001Bb>\u0002 \t\u0007a\u0011`\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0016\t\u001dM\u0007\u0012\u000e\u0003\t\ro\f\tC1\u0001\u0007z\nqB+Z7q\r&dWMQ1tK\u0012\u0014En\\2l'R|'/Z+qI\u0006$XM]\u000b\u0005\u0011_B)h\u0005\u0005\u0002$!Et1LD1!\u00159y\u0001\u0017E:!\u00111\u0019\u0010#\u001e\u0005\u0011\u0019]\u00181\u0005b\u0001\rs,\"\u0001#\u001f\u0011\r\u0019\u001dhQ\u001eE:\u0003\u001d!X\u000e\u001d$jY\u0016,\"A\"\u0005\u0002\u0011Ql\u0007OR5mK\u0002\n!B\u00197pG.\u001c\u0016N_3!)AA)\tc\"\t\n\"-\u0005R\u0012EH\u0011#C\u0019\n\u0005\u0004\b\u0010\u0005\r\u00022\u000f\u0005\t\rK\u000b\t\u00051\u0001\u0005|\"Aa1\\A!\u0001\u00041i\u000e\u0003\u0005\u0007d\u0006\u0005\u0003\u0019\u0001E=\u0011!AY(!\u0011A\u0002\u0019E\u0001\u0002\u0003Dm\u0003\u0003\u0002\raa@\t\u0015\u001d\u001d\u0011\u0011\tI\u0001\u0002\u0004\u0019Y\n\u0003\u0006\b\n\u0005\u0005\u0003\u0013!a\u0001\u00077+B\u0001c&\t\u001eR\u0001\u0002\u0012\u0014EP\u0011CC\u0019\u000bc*\t*\"-\u0006R\u0016\t\u0007\u000f\u001f\t\u0019\u0003c'\u0011\t\u0019M\bR\u0014\u0003\t\ro\fYE1\u0001\u0007z\"QaQUA&!\u0003\u0005\r\u0001b?\t\u0015\u0019m\u00171\nI\u0001\u0002\u00041i\u000e\u0003\u0006\u0007d\u0006-\u0003\u0013!a\u0001\u0011K\u0003bAb:\u0007n\"m\u0005B\u0003E>\u0003\u0017\u0002\n\u00111\u0001\u0007\u0012!Qa\u0011\\A&!\u0003\u0005\raa@\t\u0015\u001d\u001d\u00111\nI\u0001\u0002\u0004\u0019Y\n\u0003\u0006\b\n\u0005-\u0003\u0013!a\u0001\u00077+Bab+\t2\u0012Aaq_A'\u0005\u00041I0\u0006\u0003\b6\"UF\u0001\u0003D|\u0003\u001f\u0012\rA\"?\u0016\t!e\u0006RX\u000b\u0003\u0011wSC\u0001#\u001f\u0006\"\u0011Aaq_A)\u0005\u00041I0\u0006\u0003\tB\"\u0015WC\u0001EbU\u00111\t\"\"\t\u0005\u0011\u0019]\u00181\u000bb\u0001\rs,B\u0001#3\tNV\u0011\u00012\u001a\u0016\u0005\u0007\u007f,\t\u0003\u0002\u0005\u0007x\u0006U#\u0019\u0001D}+\u00119\u0019\u000e#5\u0005\u0011\u0019]\u0018q\u000bb\u0001\rs\fabY8qs\u0012\"WMZ1vYR$s'\u0006\u0003\bT\"]G\u0001\u0003D|\u00033\u0012\rA\"?\u0015\t\u001d\u0005\u00012\u001c\u0005\u000b\tC\ty&!AA\u0002\r=F\u0003BBN\u0011?D!\u0002\"\t\u0002d\u0005\u0005\t\u0019AD\u0001)\u0011)\u0019\rc9\t\u0015\u0011\u0005\u0012QMA\u0001\u0002\u0004\u0019y\u000b\u0006\u0003\u0004\u001c\"\u001d\bB\u0003C\u0011\u0003W\n\t\u00111\u0001\b\u0002\u0005qB+Z7q\r&dWMQ1tK\u0012\u0014En\\2l'R|'/Z+qI\u0006$XM\u001d\t\u0005\u000f\u001f\tyg\u0005\u0004\u0002p\tu\u0005\u0012\u0004\u000b\u0003\u0011W,B\u0001c=\tzR\u0001\u0002R\u001fE~\u0011{Dy0c\u0001\n\u0006%\u001d\u0011\u0012\u0002\t\u0007\u000f\u001f\t\u0019\u0003c>\u0011\t\u0019M\b\u0012 \u0003\t\ro\f)H1\u0001\u0007z\"AaQUA;\u0001\u0004!Y\u0010\u0003\u0005\u0007\\\u0006U\u0004\u0019\u0001Do\u0011!1\u0019/!\u001eA\u0002%\u0005\u0001C\u0002Dt\r[D9\u0010\u0003\u0005\t|\u0005U\u0004\u0019\u0001D\t\u0011!1I.!\u001eA\u0002\r}\bBCD\u0004\u0003k\u0002\n\u00111\u0001\u0004\u001c\"Qq\u0011BA;!\u0003\u0005\raa'\u0016\t\u001dM\u0017R\u0002\u0003\t\ro\f9H1\u0001\u0007z\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$s'\u0006\u0003\bT&MA\u0001\u0003D|\u0003s\u0012\rA\"?\u0016\t%]\u0011R\u0005\u000b\u0005\u00133I9\u0003\u0005\u0004\u0003 \u000e%\u00142\u0004\t\u0013\u0005?Ki\u0002b?\u0007^&\u0005b\u0011CB\u0000\u00077\u001bY*\u0003\u0003\n \t\u0005&A\u0002+va2,w\u0007\u0005\u0004\u0007h\u001a5\u00182\u0005\t\u0005\rgL)\u0003\u0002\u0005\u0007x\u0006m$\u0019\u0001D}\u0011)AY&a\u001f\u0002\u0002\u0003\u0007\u0011\u0012\u0006\t\u0007\u000f\u001f\t\u0019#c\t\u0016\t\u001dM\u0017R\u0006\u0003\t\ro\fiH1\u0001\u0007z\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uI]*Bab5\n4\u0011Aaq_A@\u0005\u00041I0\u0001\u0006j]&$\u0018.\u00197ju\u0016$B\u0001\"\u0007\n:!A\u00112HAA\u0001\u0004\u0011\u0019.A\u0003baBLE-\u0001\u000btQV4g\r\\3NKR\u0014\u0018nY:T_V\u00148-Z\u000b\u0003\u000bo\t\u0011E]3hSN$XM],ji\",\u0005\u0010^3s]\u0006d7\u000b[;gM2,7+\u001a:wKJ\fqB]3q_J$\u0018\t\u001c7CY>\u001c7n]\u0001\u000be\u0016\u0014XmZ5ti\u0016\u0014\u0018aD1ts:\u001c'+\u001a:fO&\u001cH/\u001a:\u0002-]\f\u0017\u000e\u001e$pe\u0006\u001b\u0018P\\2SKJ,w-[:uKJ\fqcZ3u\u0011>\u001cH\u000fT8dC2\u001c\u0006.\u001e4gY\u0016$\u0015\r^1\u0015\r\u0019]\u0013rJE)\u0011!1)+a$A\u0002\u0011m\b\u0002CE*\u0003\u001f\u0003\rAb.\u0002\t\u0011L'o]\u0001\u0012O\u0016$Hj\\2bY\ncwnY6ECR\fG\u0003\u0002D,\u00133B\u0001B\"*\u0002\u0012\u0002\u0007A1`\u0001\raV$(\t\\8dW\u0012\u000bG/\u0019\u000b\u000b\u00077Ky&#\u0019\nf%\u001d\u0004\u0002\u0003DS\u0003'\u0003\r\u0001b?\t\u0011%\r\u00141\u0013a\u0001\r/\nA\u0001Z1uC\"Aa1\\AJ\u0001\u00041i\u000e\u0003\u0005\u0007d\u0006M\u0005\u0019AE5a\u0011IY'c\u001c\u0011\r\u0019\u001dhQ^E7!\u00111\u00190c\u001c\u0005\u0019%E\u0014rMA\u0001\u0002\u0003\u0015\tA\"?\u0003\u0007}#\u0013'\u0001\u000bqkR\u0014En\\2l\t\u0006$\u0018-Q:TiJ,\u0017-\u001c\u000b\t\u0013oJ\u0019)#\"\n\bB!\u0011\u0012PE@\u001b\tIYH\u0003\u0003\n~\t5\u0016AB2mS\u0016tG/\u0003\u0003\n\u0002&m$\u0001F*ue\u0016\fWnQ1mY\n\f7m[,ji\"LE\t\u0003\u0005\u0007&\u0006U\u0005\u0019\u0001C~\u0011!1Y.!&A\u0002\u0019u\u0007\u0002\u0003Dr\u0003+\u0003\r!##1\t%-\u0015r\u0012\t\u0007\rO4i/#$\u0011\t\u0019M\u0018r\u0012\u0003\r\u0013#K9)!A\u0001\u0002\u000b\u0005a\u0011 \u0002\u0004?\u0012\u0012\u0014aF4fi2{7-\u00197NKJ<W\r\u001a\"m_\u000e\\G)\u0019;b)\u0019I9*#'\n\"B1A\u0011\u0010CB\r/B\u0001B\"*\u0002\u0018\u0002\u0007\u00112\u0014\t\u0005\u0005\u007fLi*\u0003\u0003\n \n-%\u0001F*ik\u001a4G.Z'fe\u001e,GM\u00117pG.LE\r\u0003\u0005\nT\u0005]\u0005\u0019\u0001D\\\u0003]9W\r\u001e'pG\u0006dW*\u001a:hK\u0012\u0014En\\2l\u001b\u0016$\u0018\r\u0006\u0004\n(&5\u0016r\u0016\t\u0005\u0007_JI+\u0003\u0003\n,\u000eE$aD'fe\u001e,GM\u00117pG.lU\r^1\t\u0011\u0019\u0015\u0016\u0011\u0014a\u0001\u00137C\u0001\"c\u0015\u0002\u001a\u0002\u0007aqW\u0001\nO\u0016$8\u000b^1ukN$B!#.\n>B1!qTB5\u0013o\u0003BAa@\n:&!\u00112\u0018BF\u0005-\u0011En\\2l'R\fG/^:\t\u0011\u0019\u0015\u00161\u0014a\u0001\tw\f1cZ3u\u001b\u0006$8\r[5oO\ncwnY6JIN$B!c1\nFB1A\u0011\u0010CB\twD\u0001\"c2\u0002\u001e\u0002\u0007\u0011\u0012Z\u0001\u0007M&dG/\u001a:\u0011\u0011\t}\u00152\u001aC~\u00077KA!#4\u0003\"\nIa)\u001e8di&|g.M\u0001\u0012e\u0016\u0004xN\u001d;CY>\u001c7n\u0015;biV\u001cH\u0003\u0003C\r\u0013'L).#7\t\u0011\u0019\u0015\u0016q\u0014a\u0001\twD\u0001\"c6\u0002 \u0002\u0007\u0011rW\u0001\u0007gR\fG/^:\t\u0015%m\u0017q\u0014I\u0001\u0002\u0004\u0019y0A\tee>\u0004\b/\u001a3NK6|'/_*ju\u0016\f1D]3q_J$(\t\\8dWN#\u0018\r^;tI\u0011,g-Y;mi\u0012\u001a\u0014A\u0006;ssR{'+\u001a9peR\u0014En\\2l'R\fG/^:\u0015\u0011\rm\u00152]Es\u0013OD\u0001B\"*\u0002$\u0002\u0007A1 \u0005\t\u0013/\f\u0019\u000b1\u0001\n8\"Q\u00112\\AR!\u0003\u0005\raa@\u0002AQ\u0014\u0018\u0010V8SKB|'\u000f\u001e\"m_\u000e\\7\u000b^1ukN$C-\u001a4bk2$HeM\u0001\u0016O\u0016$8)\u001e:sK:$(\t\\8dWN#\u0018\r^;t)\u0019I9,c<\nr\"AaQUAT\u0001\u0004!Y\u0010\u0003\u0005\nt\u0006\u001d\u0006\u0019AE{\u0003\u0011IgNZ8\u0011\t\t}\u0018r_\u0005\u0005\u0013s\u0014YIA\u0005CY>\u001c7.\u00138g_\u0006\u0019r-\u001a;M_\u000e\fG/[8o\u00052|7m[%egR!\u0011r F\u0001!\u0019\u0011y*\"\u0003\u0005x!AQQAAU\u0001\u0004)9!\u0001\fiC:$G.\u001a'pG\u0006d'+Z1e\r\u0006LG.\u001e:f)\u00111YPc\u0002\t\u0011\u0019\u0015\u00161\u0016a\u0001\tw\fA#[:J\u001fJ+G.\u0019;fI\u0016C8-\u001a9uS>tG\u0003BBN\u0015\u001bA\u0001Bc\u0004\u0002.\u0002\u0007!\u0012C\u0001\u0002iB!A\u0011\u0010F\n\u0013\u0011Q)\u0002b\"\u0003\u0013QC'o\\<bE2,\u0017AD4fi2{7-\u00197WC2,Xm\u001d\u000b\u0005\u00157Q\u0019\u0003\u0005\u0004\u0003 \u000e%$R\u0004\t\u0005\u0005\u007fTy\"\u0003\u0003\u000b\"\t-%a\u0003\"m_\u000e\\'+Z:vYRD\u0001B\"*\u00020\u0002\u0007A1`\u0001\u001eKb$XM\u001c3NKN\u001c\u0018mZ3XSRD'\t\\8dW\u0012+G/Y5mgR1!1\u001bF\u0015\u0015[A\u0001Bc\u000b\u00022\u0002\u0007!1[\u0001\u0004[N<\u0007\u0002\u0003DS\u0003c\u0003\r\u0001b?\u0002\u001b\u001d,G\u000fT8dC2\u0014\u0015\u0010^3t)\u0011Q\u0019D#\u000e\u0011\r\t}5\u0011ND\u0018\u0011!1)+a-A\u0002\u0011m\u0018a\u00043p\u000f\u0016$Hj\\2bY\nKH/Z:\u0015\r\u001d=\"2\bF\u001f\u0011!1)+!.A\u0002\u0011m\b\u0002CEz\u0003k\u0003\r!#>\u0002\u001f\u001d,GOU3n_R,g+\u00197vKN,BAc\u0011\u000bPQ!!R\tF))\u0011QYBc\u0012\t\u0015)%\u0013qWA\u0001\u0002\bQY%\u0001\u0006fm&$WM\\2fIE\u0002bAb:\u0007n*5\u0003\u0003\u0002Dz\u0015\u001f\"\u0001Bb>\u00028\n\u0007a\u0011 \u0005\t\rK\u000b9\f1\u0001\u0005|\u0006qq-\u001a;SK6|G/\u001a\"m_\u000e\\W\u0003\u0002F,\u0015;\"bA#\u0017\u000b`)\u0005\u0004C\u0002BP\u0007SRY\u0006\u0005\u0003\u0007t*uC\u0001\u0003D|\u0003s\u0013\rA\"?\t\u0011\u0019\u0015\u0016\u0011\u0018a\u0001\twD\u0001Bc\u0019\u0002:\u0002\u0007!RM\u0001\u0012EV4g-\u001a:Ue\u0006t7OZ8s[\u0016\u0014\b\u0003\u0003BP\u0013\u001749Fc\u0017\u0002\u001fA\u0014XMZ3s\u000bb,7-\u001e;peN$B\u0001b\u001e\u000bl!A!RNA^\u0001\u0004!9(A\u0005m_\u000e\fG/[8og\u0006i1o\u001c:u\u0019>\u001c\u0017\r^5p]N$B\u0001b\u001e\u000bt!A!RNA_\u0001\u0004!9(\u0001\rgKR\u001c\u0007NU3n_R,W*\u00198bO\u0016$')\u001e4gKJ$\u0002B#\u001f\u000b|)u$r\u0010\t\u0007\u0005?\u001bIGb\u0016\t\u0011\u0019\u0015\u0016q\u0018a\u0001\twD\u0001B\"7\u0002@\u0002\u00071q \u0005\t\u0015\u0003\u000by\f1\u0001\u000b\u0004\u0006\u0011Bn\\2bi&|gn]!oIN#\u0018\r^;t!\u0011Q)Ic#\u000f\t\t}(rQ\u0005\u0005\u0015\u0013\u0013Y)\u0001\u000bCY>\u001c7.T1oC\u001e,'/T3tg\u0006<Wm]\u0005\u0005\u0015\u001bSyIA\fCY>\u001c7\u000eT8dCRLwN\\:B]\u0012\u001cF/\u0019;vg*!!\u0012\u0012BF\u0003\u0005\u0012X-\u00193ESN\\'\t\\8dW\u001a\u0013x.\\*b[\u0016Dun\u001d;Fq\u0016\u001cW\u000f^8s)!QIH#&\u000b\u0018*m\u0005\u0002\u0003DS\u0003\u0003\u0004\r\u0001b?\t\u0011)e\u0015\u0011\u0019a\u0001\ro\u000b\u0011\u0002\\8dC2$\u0015N]:\t\u0011\u0019e\u0017\u0011\u0019a\u0001\u0007\u007f\fabZ3u%\u0016lw\u000e^3CsR,7\u000f\u0006\u0003\u000b\"*\r\u0006C\u0002BP\u0007S:\t\u0003\u0003\u0005\u0007&\u0006\r\u0007\u0019\u0001C~\u0003\r9W\r^\u000b\u0005\u0015SS)\f\u0006\u0003\u000b,*]F\u0003\u0002F\u000e\u0015[C!Bc,\u0002F\u0006\u0005\t9\u0001FY\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\rO4iOc-\u0011\t\u0019M(R\u0017\u0003\t\ro\f)M1\u0001\u0007z\"AaQUAc\u0001\u0004!Y0A\u0007e_^twM]1eK2{7m\u001b\u000b\u0005\t3Qi\f\u0003\u0005\u0007&\u0006\u001d\u0007\u0019\u0001C~\u0003-\u0011X\r\\3bg\u0016dunY6\u0015\r\u0011e!2\u0019Fc\u0011!1)+!3A\u0002\u0011m\bB\u0003Fd\u0003\u0013\u0004\n\u00111\u0001\u000bJ\u0006YA/Y:l\u0007>tG/\u001a=u!\u0019\u0011yj!\u001b\u000bLB!1q\u0004Fg\u0013\u0011QyMa$\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010^\u0001\u0016e\u0016dW-Y:f\u0019>\u001c7\u000e\n3fM\u0006,H\u000e\u001e\u00133+\tQ)N\u000b\u0003\u000bJ\u0016\u0005\u0012\u0001\u0004:fO&\u001cH/\u001a:UCN\\G\u0003\u0002C\r\u00157D\u0001B#8\u0002N\u0002\u00071q`\u0001\u000ei\u0006\u001c8.\u0011;uK6\u0004H/\u00133\u0002-I,G.Z1tK\u0006cG\u000eT8dWN4uN\u001d+bg.$B!c1\u000bd\"A!R\\Ah\u0001\u0004\u0019y0A\fhKR|%/\u00127tKV\u0003H-\u0019;f%\u0012#%\t\\8dWV!!\u0012\u001eF|)1QYO#?\u000b~.\u00151rAF\u0006!!!IH#<\u000b\u001e)E\u0018\u0002\u0002Fx\t\u000f\u0013a!R5uQ\u0016\u0014\bC\u0002C=\u0015gT)0\u0003\u0003\bx\u0012\u001d\u0005\u0003\u0002Dz\u0015o$\u0001Bb>\u0002R\n\u0007a\u0011 \u0005\t\u0015w\f\t\u000e1\u0001\u0004\u0000\u00061A/Y:l\u0013\u0012D\u0001B\"*\u0002R\u0002\u0007!r \t\u0005\u0005\u007f\\\t!\u0003\u0003\f\u0004\t-%A\u0003*E\t\ncwnY6JI\"Aa1\\Ai\u0001\u00041i\u000e\u0003\u0005\u0007d\u0006E\u0007\u0019AF\u0005!\u001919O\"<\u000bv\"A1RBAi\u0001\u0004Yy!\u0001\u0007nC.,\u0017\n^3sCR|'\u000f\u0005\u0004\u0003 .E!\u0012_\u0005\u0005\u0017'\u0011\tKA\u0005Gk:\u001cG/[8oa\u0005yq-\u001a;Pe\u0016c7/Z+qI\u0006$X-\u0006\u0003\f\u001a-\u0005B\u0003DF\u000e\u0017GY)cc\n\f,-=\u0002\u0003\u0003C=\u0015[Tib#\b\u0011\r\u0011e$2_F\u0010!\u00111\u0019p#\t\u0005\u0011\u0019]\u00181\u001bb\u0001\rsD\u0001B\"*\u0002T\u0002\u0007A1 \u0005\t\r7\f\u0019\u000e1\u0001\u0007^\"Aa1]Aj\u0001\u0004YI\u0003\u0005\u0004\u0007h\u001a58r\u0004\u0005\t\u0017\u001b\t\u0019\u000e1\u0001\f.A1!qTF\t\u0017;A\u0001b#\r\u0002T\u0002\u000711T\u0001\u000fSN\u001c\u0015m\u00195f-&\u001c\u0018N\u00197f\u0003-\u0001X\u000f^%uKJ\fGo\u001c:\u0016\t-]22\t\u000b\u000b\u0017sY)ec\u0012\fN-=C\u0003BBN\u0017wA!b#\u0010\u0002V\u0006\u0005\t9AF \u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\rO4io#\u0011\u0011\t\u0019M82\t\u0003\t\ro\f)N1\u0001\u0007z\"AaQUAk\u0001\u0004!Y\u0010\u0003\u0005\fJ\u0005U\u0007\u0019AF&\u0003\u00191\u0018\r\\;fgB1A\u0011\u0010Fz\u0017\u0003B\u0001Bb7\u0002V\u0002\u0007aQ\u001c\u0005\u000b\u000f\u000f\t)\u000e%AA\u0002\rm\u0015!\u00069vi&#XM]1u_J$C-\u001a4bk2$H\u0005N\u000b\u0005\u000f'\\)\u0006\u0002\u0005\u0007x\u0006]'\u0019\u0001D}\u000359W\r\u001e#jg.<&/\u001b;feRa12LF1\u0017GZ)gc\u001c\ftA!!q`F/\u0013\u0011YyFa#\u0003+\u0011K7o\u001b\"m_\u000e\\wJ\u00196fGR<&/\u001b;fe\"AaQUAm\u0001\u0004!Y\u0010\u0003\u0005\u0006,\u0006e\u0007\u0019\u0001D\t\u0011!Y9'!7A\u0002-%\u0014AE:fe&\fG.\u001b>fe&s7\u000f^1oG\u0016\u0004Ba!\u0004\fl%!1RNB\b\u0005I\u0019VM]5bY&TXM]%ogR\fgnY3\t\u0011-E\u0014\u0011\u001ca\u0001\u0007_\u000b!BY;gM\u0016\u00148+\u001b>f\u0011!Y)(!7A\u0002-]\u0014\u0001D<sSR,W*\u001a;sS\u000e\u001c\b\u0003BB#\u0017sJAac\u001f\u0004H\tY2\u000b[;gM2,wK]5uK6+GO]5dgJ+\u0007o\u001c:uKJ\f\u0001\u0002];u\u0005f$Xm]\u000b\u0005\u0017\u0003[i\t\u0006\u0006\f\u0004.=5\u0012SFJ\u0017+#Baa'\f\u0006\"Q1rQAn\u0003\u0003\u0005\u001da##\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0004\u0007h\u001a582\u0012\t\u0005\rg\\i\t\u0002\u0005\u0007x\u0006m'\u0019\u0001D}\u0011!1)+a7A\u0002\u0011m\b\u0002CD&\u00037\u0004\ra\"\t\t\u0011\u0019m\u00171\u001ca\u0001\r;D!bb\u0002\u0002\\B\u0005\t\u0019ABN\u0003I\u0001X\u000f\u001e\"zi\u0016\u001cH\u0005Z3gCVdG\u000f\n\u001b\u0016\t\u001dM72\u0014\u0003\t\ro\fiN1\u0001\u0007z\u0006\t\u0012n\u001d*E\t\ncwnY6WSNL'\r\\3\u0015\t\rm5\u0012\u0015\u0005\t\rK\u000by\u000e1\u0001\u000b\u0000\u0006)Am\u001c)viV!1rUFX)1YIkc.\f:.m6rYFe)\u0011YYk#-\u0011\r\t}5\u0011NFW!\u00111\u0019pc,\u0005\u0011\u0019]\u0018\u0011\u001db\u0001\rsD\u0001bc-\u0002b\u0002\u00071RW\u0001\baV$(i\u001c3z!!\u0011y*c3\nv.-\u0006\u0002\u0003DS\u0003C\u0004\r\u0001b?\t\u0011\u0019m\u0017\u0011\u001da\u0001\r;D\u0001Bb9\u0002b\u0002\u00071R\u0018\u0019\u0005\u0017\u007f[\u0019\r\u0005\u0004\u0007h\u001a58\u0012\u0019\t\u0005\rg\\\u0019\r\u0002\u0007\fF.m\u0016\u0011!A\u0001\u0006\u00031IPA\u0002`IMB\u0001bb\u0002\u0002b\u0002\u000711\u0014\u0005\t\u000f\u0013\t\t\u000f1\u0001\u0004\u001c\u0006iAm\u001c)vi&#XM]1u_J,Bac4\f\\Rq1\u0012[Fo\u0017?\\9o#;\fn.=\bC\u0002BP\u0007SZ\u0019\u000e\u0005\u0004\u00038.U7\u0012\\\u0005\u0005\u0017/\u0014ILA\rQCJ$\u0018.\u00197msVs'o\u001c7mK\u0012LE/\u001a:bi>\u0014\b\u0003\u0002Dz\u00177$\u0001Bb>\u0002d\n\u0007a\u0011 \u0005\t\rK\u000b\u0019\u000f1\u0001\u0005|\"A1\u0012]Ar\u0001\u0004Y\u0019/\u0001\u0005ji\u0016\u0014\u0018\r^8s!\u0019\u0011yj#\u0005\ffB1A\u0011\u0010Fz\u00173D\u0001Bb7\u0002d\u0002\u0007aQ\u001c\u0005\t\rG\f\u0019\u000f1\u0001\flB1aq\u001dDw\u00173D!bb\u0002\u0002dB\u0005\t\u0019ABN\u0011)9I!a9\u0011\u0002\u0003\u000711T\u0001\u0018I>\u0004V\u000f^%uKJ\fGo\u001c:%I\u00164\u0017-\u001e7uIU*Bab5\fv\u0012Aaq_As\u0005\u00041I0A\fe_B+H/\u0013;fe\u0006$xN\u001d\u0013eK\u001a\fW\u000f\u001c;%mU!q1[F~\t!190a:C\u0002\u0019e\u0018aG7bs\n,7)Y2iK\u0012K7o\u001b\"zi\u0016\u001c\u0018J\\'f[>\u0014\u0018\u0010\u0006\u0006\u000b\"2\u0005AR\u0001G\u0004\u0019\u0013A\u0001\u0002d\u0001\u0002j\u0002\u0007\u0011R_\u0001\nE2|7m[%oM>D\u0001B\"*\u0002j\u0002\u0007A1 \u0005\t\r7\fI\u000f1\u0001\u0007^\"AA2BAu\u0001\u00049y#\u0001\u0005eSN\\G)\u0019;b\u0003qi\u0017-\u001f2f\u0007\u0006\u001c\u0007.\u001a#jg.4\u0016\r\\;fg&sW*Z7pef,B\u0001$\u0005\r\u0018QQA2\u0003G\r\u00197ai\u0002d\b\u0011\r\u0011e$2\u001fG\u000b!\u00111\u0019\u0010d\u0006\u0005\u0011\u0019]\u00181\u001eb\u0001\rsD\u0001\u0002d\u0001\u0002l\u0002\u0007\u0011R\u001f\u0005\t\rK\u000bY\u000f1\u0001\u0005|\"Aa1\\Av\u0001\u00041i\u000e\u0003\u0005\r\"\u0005-\b\u0019\u0001G\n\u00031!\u0017n]6Ji\u0016\u0014\u0018\r^8s\u0003!9W\r\u001e)fKJ\u001cH\u0003\u0002C<\u0019OA\u0001\u0002$\u000b\u0002n\u0002\u000711T\u0001\u000bM>\u00148-\u001a$fi\u000eD\u0017A\u0004:fa2L7-\u0019;f\u00052|7m\u001b\u000b\u000b\u00077cy\u0003$\r\r:1u\u0002\u0002\u0003DS\u0003_\u0004\r\u0001b?\t\u00111M\u0012q\u001ea\u0001\u0019k\t\u0001#\u001a=jgRLgn\u001a*fa2L7-Y:\u0011\r\tUGr\u0007C\b\u0013\u0011)iNa:\t\u00111m\u0012q\u001ea\u0001\u0007_\u000b1\"\\1y%\u0016\u0004H.[2bg\"QArHAx!\u0003\u0005\r\u0001$\u0011\u0002-5\f\u0007PU3qY&\u001c\u0017\r^5p]\u001a\u000b\u0017\u000e\\;sKN\u0004bAa(\u0004j\r=\u0016\u0001\u0007:fa2L7-\u0019;f\u00052|7m\u001b\u0013eK\u001a\fW\u000f\u001c;%iU\u0011Ar\t\u0016\u0005\u0019\u0003*\t#A\u0005sKBd\u0017nY1uKRq11\u0014G'\u0019\u001fb\t\u0006d\u0015\r`1\u0005\u0004\u0002\u0003DS\u0003g\u0004\r\u0001b?\t\u0011%\r\u00141\u001fa\u0001\u000f_A\u0001Bb7\u0002t\u0002\u0007aQ\u001c\u0005\t\rG\f\u0019\u00101\u0001\rVA\"Ar\u000bG.!\u001919O\"<\rZA!a1\u001fG.\t1ai\u0006d\u0015\u0002\u0002\u0003\u0005)\u0011\u0001D}\u0005\ryF\u0005\u000e\u0005\u000b\u0019g\t\u0019\u0010%AA\u00021U\u0002B\u0003G \u0003g\u0004\n\u00111\u0001\rB\u0005\u0019\"/\u001a9mS\u000e\fG/\u001a\u0013eK\u001a\fW\u000f\u001c;%kU\u0011Ar\r\u0016\u0005\u0019k)\t#A\nsKBd\u0017nY1uK\u0012\"WMZ1vYR$c'A\u0005hKR\u001c\u0016N\\4mKV!Ar\u000eG<)\u0011a\t\bd \u0015\t1MD\u0012\u0010\t\u0007\u0005?\u001bI\u0007$\u001e\u0011\t\u0019MHr\u000f\u0003\t\ro\fIP1\u0001\u0007z\"QA2PA}\u0003\u0003\u0005\u001d\u0001$ \u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0007h\u001a5HR\u000f\u0005\t\rK\u000bI\u00101\u0001\u0005|\u0006I\u0001/\u001e;TS:<G.Z\u000b\u0005\u0019\u000bc\t\n\u0006\u0006\r\b2MER\u0013GM\u00197#Baa'\r\n\"QA2RA~\u0003\u0003\u0005\u001d\u0001$$\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0004\u0007h\u001a5Hr\u0012\t\u0005\rgd\t\n\u0002\u0005\u0007x\u0006m(\u0019\u0001D}\u0011!1)+a?A\u0002\u0011m\b\u0002\u0003GL\u0003w\u0004\r\u0001d$\u0002\u000bY\fG.^3\t\u0011\u0019m\u00171 a\u0001\r;D!bb\u0002\u0002|B\u0005\t\u0019ABN\u0003M\u0001X\u000f^*j]\u001edW\r\n3fM\u0006,H\u000e\u001e\u00135+\u00119\u0019\u000e$)\u0005\u0011\u0019]\u0018Q b\u0001\rs\fa\u0002\u001a:pa\u001a\u0013x.\\'f[>\u0014\u00180\u0006\u0003\r(2MFC\u0002GU\u0019kc9\f\u0006\u0003\u0007^2-\u0006B\u0003GW\u0003\u007f\f\t\u0011q\u0001\r0\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\r\u0019\u001dhQ\u001eGY!\u00111\u0019\u0010d-\u0005\u0011\u0019]\u0018q b\u0001\rsD\u0001B\"*\u0002\u0000\u0002\u0007A1 \u0005\t\u0013G\ny\u00101\u0001\r:B1!qTF\t\u0019w\u0003\u0002\u0002\"\u001f\u000bn2uv\u0011\u0005\t\u0007\u0005?+I\u0001$-\u0002\u0013I,Wn\u001c<f%\u0012$G\u0003BBX\u0019\u0007D\u0001\u0002$2\u0003\u0002\u0001\u00071qV\u0001\u0006e\u0012$\u0017\nZ\u0001\u0019I\u0016\u001cw.\\7jgNLwN\u001c\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\u0001\u00053fG>lW.[:tS>t7+\u001a7g\u0003Ea\u0017m\u001d;NS\u001e\u0014\u0018\r^5p]&sgm\u001c\u000b\u0003\u0019\u001f\u0004\u0002Ba(\rR\u000e}81T\u0005\u0005\u0019'\u0014\tK\u0001\u0004UkBdWMM\u0001\u0017O\u0016$X*[4sCR\f'\r\\3S\t\u0012\u0013En\\2lgR\u0011A\u0012\u001c\t\u0007\ts\"\u0019\td7\u0011\t1uGr\u001e\b\u0005\u0019?T9I\u0004\u0003\rb25h\u0002\u0002Gr\u0019WtA\u0001$:\rj:!!\u0011\u001cGt\u0013\t\u0011I*\u0003\u0003\u0003\u0016\n]\u0015\u0002\u0002BI\u0005'KAA!$\u0003\u0010&!A\u0012\u001fFH\u00059\u0011V\r\u001d7jG\u0006$XM\u00117pG.\fqB]3n_Z,'I]8bI\u000e\f7\u000f\u001e\u000b\u0007\u0007_c9\u0010d?\t\u00111e(1\u0002a\u0001\u0007\u007f\f1B\u0019:pC\u0012\u001c\u0017m\u001d;JI\"Aqq\u0001B\u0006\u0001\u0004\u0019Y*A\u0006sK6|g/Z\"bG\",G\u0003BBX\u001b\u0003A\u0001\"d\u0001\u0003\u000e\u0001\u0007!1[\u0001\fg\u0016\u001c8/[8o+VKE)A\u0006sK6|g/\u001a\"m_\u000e\\GC\u0002C\r\u001b\u0013iY\u0001\u0003\u0005\u0007&\n=\u0001\u0019\u0001C~\u0011)99Aa\u0004\u0011\u0002\u0003\u000711T\u0001\u0016e\u0016lwN^3CY>\u001c7\u000e\n3fM\u0006,H\u000e\u001e\u00133\u0003M\u0011X-\\8wK\ncwnY6J]R,'O\\1m)\u0019!I\"d\u0005\u000e\u0016!AaQ\u0015B\n\u0001\u0004!Y\u0010\u0003\u0005\b\b\tM\u0001\u0019ABN\u0003\t\nG\rZ+qI\u0006$X\r\u001a\"m_\u000e\\7\u000b^1ukN$v\u000eV1tW6+GO]5dgR1A\u0011DG\u000e\u001b;A\u0001B\"*\u0003\u0016\u0001\u0007A1 \u0005\t\u0013/\u0014)\u00021\u0001\n8\u0006)\"/\u001a7fCN,Gj\\2l\u0003:$G)[:q_N,G\u0003\u0003C\r\u001bGi)#d\n\t\u0011\u0019\u0015&q\u0003a\u0001\twD\u0001\"c\u0019\u0003\u0018\u0001\u0007qq\u0006\u0005\u000b\u0015\u000f\u00149\u0002%AA\u0002)%\u0017a\b:fY\u0016\f7/\u001a'pG.\fe\u000e\u001a#jgB|7/\u001a\u0013eK\u001a\fW\u000f\u001c;%g\u0001"
)
public class BlockManager implements BlockDataManager, BlockEvictionHandler, Logging {
   private ShuffleManager shuffleManager;
   private MemoryManager memoryManager;
   private MemoryStore memoryStore;
   private long maxOnHeapMemory;
   private long maxOffHeapMemory;
   private MigratableResolver migratableResolver;
   private volatile ByteBufferBlockStoreUpdater$ ByteBufferBlockStoreUpdater$module;
   private volatile TempFileBasedBlockStoreUpdater$ TempFileBasedBlockStoreUpdater$module;
   private final String executorId;
   private final RpcEnv rpcEnv;
   private final BlockManagerMaster master;
   private final SerializerManager serializerManager;
   private final SparkConf conf;
   private MemoryManager _memoryManager;
   private ShuffleManager _shuffleManager;
   private final BlockTransferService blockTransferService;
   private final SecurityManager securityManager;
   private final Option externalBlockStoreClient;
   private final boolean externalShuffleServiceEnabled;
   private final boolean isDriver;
   private final boolean remoteReadNioBufferConversion;
   private final int subDirsPerLocalDir;
   private final DiskBlockManager diskBlockManager;
   private final boolean trackingCacheVisibility;
   private final BlockInfoManager blockInfoManager;
   private final ExecutionContextExecutorService org$apache$spark$storage$BlockManager$$futureExecutionContext;
   private final DiskStore diskStore;
   private final int externalShuffleServicePort;
   private BlockManagerId blockManagerId;
   private BlockManagerId shuffleServerId;
   private final BlockStoreClient blockStoreClient;
   private final int maxFailuresBeforeLocationRefresh;
   private final RpcEndpointRef storageEndpoint;
   private Future asyncReregisterTask;
   private final Object asyncReregisterLock;
   private volatile Seq cachedPeers;
   private final Object peerFetchLock;
   private long lastPeerFetchTimeNs;
   private BlockReplicationPolicy blockReplicationPolicy;
   private volatile Option decommissioner;
   private final RemoteBlockDownloadFileManager remoteBlockTempFileManager;
   private final long maxRemoteBlockToMem;
   private Option hostLocalDirManager;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

   public static BlockManagerMaster blockIdsToLocations$default$3() {
      return BlockManager$.MODULE$.blockIdsToLocations$default$3();
   }

   public static Map blockIdsToLocations(final BlockId[] blockIds, final SparkEnv env, final BlockManagerMaster blockManagerMaster) {
      return BlockManager$.MODULE$.blockIdsToLocations(blockIds, env, blockManagerMaster);
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

   public ByteBufferBlockStoreUpdater$ ByteBufferBlockStoreUpdater() {
      if (this.ByteBufferBlockStoreUpdater$module == null) {
         this.ByteBufferBlockStoreUpdater$lzycompute$1();
      }

      return this.ByteBufferBlockStoreUpdater$module;
   }

   public TempFileBasedBlockStoreUpdater$ TempFileBasedBlockStoreUpdater() {
      if (this.TempFileBasedBlockStoreUpdater$module == null) {
         this.TempFileBasedBlockStoreUpdater$lzycompute$1();
      }

      return this.TempFileBasedBlockStoreUpdater$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String executorId() {
      return this.executorId;
   }

   public BlockManagerMaster master() {
      return this.master;
   }

   public SerializerManager serializerManager() {
      return this.serializerManager;
   }

   public SparkConf conf() {
      return this.conf;
   }

   private MemoryManager _memoryManager() {
      return this._memoryManager;
   }

   private ShuffleManager _shuffleManager() {
      return this._shuffleManager;
   }

   public BlockTransferService blockTransferService() {
      return this.blockTransferService;
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

   private MemoryManager memoryManager$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.memoryManager = (MemoryManager).MODULE$.apply(this._memoryManager()).getOrElse(() -> SparkEnv$.MODULE$.get().memoryManager());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this._memoryManager = null;
      return this.memoryManager;
   }

   public MemoryManager memoryManager() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.memoryManager$lzycompute() : this.memoryManager;
   }

   public boolean externalShuffleServiceEnabled() {
      return this.externalShuffleServiceEnabled;
   }

   private boolean isDriver() {
      return this.isDriver;
   }

   private boolean remoteReadNioBufferConversion() {
      return this.remoteReadNioBufferConversion;
   }

   public int subDirsPerLocalDir() {
      return this.subDirsPerLocalDir;
   }

   public DiskBlockManager diskBlockManager() {
      return this.diskBlockManager;
   }

   private boolean trackingCacheVisibility() {
      return this.trackingCacheVisibility;
   }

   public BlockInfoManager blockInfoManager() {
      return this.blockInfoManager;
   }

   public ExecutionContextExecutorService org$apache$spark$storage$BlockManager$$futureExecutionContext() {
      return this.org$apache$spark$storage$BlockManager$$futureExecutionContext;
   }

   private MemoryStore memoryStore$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            MemoryStore store = new MemoryStore(this.conf(), this.blockInfoManager(), this.serializerManager(), this.memoryManager(), this);
            this.memoryManager().setMemoryStore(store);
            this.memoryStore = store;
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.memoryStore;
   }

   public MemoryStore memoryStore() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.memoryStore$lzycompute() : this.memoryStore;
   }

   public DiskStore diskStore() {
      return this.diskStore;
   }

   private long maxOnHeapMemory$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.maxOnHeapMemory = this.memoryManager().maxOnHeapStorageMemory();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.maxOnHeapMemory;
   }

   private long maxOnHeapMemory() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.maxOnHeapMemory$lzycompute() : this.maxOnHeapMemory;
   }

   private long maxOffHeapMemory$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.maxOffHeapMemory = this.memoryManager().maxOffHeapStorageMemory();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.maxOffHeapMemory;
   }

   private long maxOffHeapMemory() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.maxOffHeapMemory$lzycompute() : this.maxOffHeapMemory;
   }

   public int externalShuffleServicePort() {
      return this.externalShuffleServicePort;
   }

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public void blockManagerId_$eq(final BlockManagerId x$1) {
      this.blockManagerId = x$1;
   }

   public BlockManagerId shuffleServerId() {
      return this.shuffleServerId;
   }

   public void shuffleServerId_$eq(final BlockManagerId x$1) {
      this.shuffleServerId = x$1;
   }

   public BlockStoreClient blockStoreClient() {
      return this.blockStoreClient;
   }

   private int maxFailuresBeforeLocationRefresh() {
      return this.maxFailuresBeforeLocationRefresh;
   }

   private RpcEndpointRef storageEndpoint() {
      return this.storageEndpoint;
   }

   private Future asyncReregisterTask() {
      return this.asyncReregisterTask;
   }

   private void asyncReregisterTask_$eq(final Future x$1) {
      this.asyncReregisterTask = x$1;
   }

   private Object asyncReregisterLock() {
      return this.asyncReregisterLock;
   }

   private Seq cachedPeers() {
      return this.cachedPeers;
   }

   private void cachedPeers_$eq(final Seq x$1) {
      this.cachedPeers = x$1;
   }

   private Object peerFetchLock() {
      return this.peerFetchLock;
   }

   private long lastPeerFetchTimeNs() {
      return this.lastPeerFetchTimeNs;
   }

   private void lastPeerFetchTimeNs_$eq(final long x$1) {
      this.lastPeerFetchTimeNs = x$1;
   }

   private BlockReplicationPolicy blockReplicationPolicy() {
      return this.blockReplicationPolicy;
   }

   private void blockReplicationPolicy_$eq(final BlockReplicationPolicy x$1) {
      this.blockReplicationPolicy = x$1;
   }

   public Option decommissioner() {
      return this.decommissioner;
   }

   public void decommissioner_$eq(final Option x$1) {
      this.decommissioner = x$1;
   }

   public RemoteBlockDownloadFileManager remoteBlockTempFileManager() {
      return this.remoteBlockTempFileManager;
   }

   private long maxRemoteBlockToMem() {
      return this.maxRemoteBlockToMem;
   }

   public Option hostLocalDirManager() {
      return this.hostLocalDirManager;
   }

   public void hostLocalDirManager_$eq(final Option x$1) {
      this.hostLocalDirManager = x$1;
   }

   public final boolean org$apache$spark$storage$BlockManager$$isDecommissioning() {
      return this.decommissioner().isDefined();
   }

   private final void checkShouldStore(final BlockId blockId) {
      if (this.org$apache$spark$storage$BlockManager$$isDecommissioning() && !blockId.isBroadcast()) {
         throw SparkCoreErrors$.MODULE$.cannotSaveBlockOnDecommissionedExecutorError(blockId);
      }
   }

   private MigratableResolver migratableResolver$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.migratableResolver = (MigratableResolver)this.shuffleManager().shuffleBlockResolver();
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.migratableResolver;
   }

   public MigratableResolver migratableResolver() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.migratableResolver$lzycompute() : this.migratableResolver;
   }

   public String[] getLocalDiskDirs() {
      return this.diskBlockManager().localDirsString();
   }

   public Cause diagnoseShuffleBlockCorruption(final BlockId blockId, final long checksumByReader, final String algorithm) {
      scala.Predef..MODULE$.assert(blockId instanceof ShuffleBlockId, () -> "Corruption diagnosis only supports shuffle block yet, but got " + blockId);
      ShuffleBlockId shuffleBlock = (ShuffleBlockId)blockId;
      IndexShuffleBlockResolver resolver = (IndexShuffleBlockResolver)this.shuffleManager().shuffleBlockResolver();
      File checksumFile = resolver.getChecksumFile(shuffleBlock.shuffleId(), shuffleBlock.mapId(), algorithm, resolver.getChecksumFile$default$4());
      int reduceId = shuffleBlock.reduceId();
      return ShuffleChecksumHelper.diagnoseCorruption(algorithm, checksumFile, reduceId, resolver.getBlockData(shuffleBlock, resolver.getBlockData$default$2()), checksumByReader);
   }

   public void initialize(final String appId) {
      this.blockTransferService().init(this);
      this.externalBlockStoreClient.foreach((blockStoreClient) -> {
         $anonfun$initialize$1(appId, blockStoreClient);
         return BoxedUnit.UNIT;
      });
      String priorityClass = (String)this.conf().get(package$.MODULE$.STORAGE_REPLICATION_POLICY());
      Class clazz = Utils$.MODULE$.classForName(priorityClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      BlockReplicationPolicy ret = (BlockReplicationPolicy)clazz.getConstructor().newInstance();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using ", " for block replication policy"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, priorityClass)})))));
      this.blockReplicationPolicy_$eq(ret);
      if (this.externalShuffleServiceEnabled()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"external shuffle service port = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.externalShuffleServicePort()))})))));
         this.shuffleServerId_$eq(BlockManagerId$.MODULE$.apply(this.executorId(), this.blockTransferService().hostName(), this.externalShuffleServicePort(), BlockManagerId$.MODULE$.apply$default$4()));
         if (!this.isDriver() && (!Utils$.MODULE$.isTesting() || !BoxesRunTime.unboxToBoolean(this.conf().get(Tests$.MODULE$.TEST_SKIP_ESS_REGISTER())))) {
            this.registerWithExternalShuffleServer();
         }
      }

      BlockManagerId id = BlockManagerId$.MODULE$.apply(this.executorId(), this.blockTransferService().hostName(), this.blockTransferService().port(), scala.None..MODULE$);
      BlockManagerId idFromMaster = this.master().registerBlockManager(id, this.diskBlockManager().localDirsString(), this.maxOnHeapMemory(), this.maxOffHeapMemory(), this.storageEndpoint(), this.master().registerBlockManager$default$6());
      this.blockManagerId_$eq(idFromMaster != null ? idFromMaster : id);
      if (!this.externalShuffleServiceEnabled()) {
         this.shuffleServerId_$eq(this.blockManagerId());
      }

      this.hostLocalDirManager_$eq((Option)((!BoxesRunTime.unboxToBoolean(this.conf().get(package$.MODULE$.SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED())) || BoxesRunTime.unboxToBoolean(this.conf().get(package$.MODULE$.SHUFFLE_USE_OLD_FETCH_PROTOCOL()))) && !Utils$.MODULE$.isPushBasedShuffleEnabled(this.conf(), this.isDriver(), Utils$.MODULE$.isPushBasedShuffleEnabled$default$3()) ? scala.None..MODULE$ : new Some(new HostLocalDirManager(BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.STORAGE_LOCAL_DISK_BY_EXECUTORS_CACHE_SIZE())), this.blockStoreClient()))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialized BlockManager: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, this.blockManagerId())})))));
   }

   public Source shuffleMetricsSource() {
      return this.externalShuffleServiceEnabled() ? new ShuffleMetricsSource("ExternalShuffle", this.blockStoreClient().shuffleMetrics()) : new ShuffleMetricsSource("NettyBlockTransfer", this.blockStoreClient().shuffleMetrics());
   }

   private void registerWithExternalShuffleServer() {
      Object var1 = new Object();

      try {
         this.logInfo((Function0)(() -> "Registering executor with local external shuffle service."));
         String shuffleMgrClass = ShuffleManager$.MODULE$.getShuffleManagerClassName(this.conf());
         String shuffleManagerMeta = Utils$.MODULE$.isPushBasedShuffleEnabled(this.conf(), this.isDriver(), false) ? shuffleMgrClass + ":" + this.diskBlockManager().getMergeDirectoryAndAttemptIDJsonString() + "}}" : shuffleMgrClass;
         ExecutorShuffleInfo shuffleConfig = new ExecutorShuffleInfo(this.diskBlockManager().localDirsString(), this.diskBlockManager().subDirsPerLocalDir(), shuffleManagerMeta);
         int MAX_ATTEMPTS = BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.SHUFFLE_REGISTRATION_MAX_ATTEMPTS()));
         int SLEEP_TIME_MS = 5000;
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), MAX_ATTEMPTS).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            try {
               ((ExternalBlockStoreClient)this.blockStoreClient()).registerWithShuffleServer(this.shuffleServerId().host(), this.shuffleServerId().port(), this.shuffleServerId().executorId(), shuffleConfig);
               throw new NonLocalReturnControl.mcV.sp(var1, BoxedUnit.UNIT);
            } catch (Throwable var11) {
               if (var11 instanceof Exception var9) {
                  if (i < MAX_ATTEMPTS) {
                     this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to connect to external shuffle server, will retry "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " more times after waiting "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(MAX_ATTEMPTS - i))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLEEP_TIME..MODULE$, BoxesRunTime.boxToInteger(SLEEP_TIME_MS))}))))), var9);
                     Thread.sleep((long)SLEEP_TIME_MS);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                     return;
                  }
               }

               if (var11 != null && scala.util.control.NonFatal..MODULE$.apply(var11)) {
                  throw SparkCoreErrors$.MODULE$.unableToRegisterWithExternalShuffleServerError(var11);
               } else {
                  throw var11;
               }
            }
         });
      } catch (NonLocalReturnControl var8) {
         if (var8.key() != var1) {
            throw var8;
         }

         var8.value$mcV$sp();
      }

   }

   private void reportAllBlocks() {
      Object var1 = new Object();

      try {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reporting ", " blocks to the master."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BLOCKS..MODULE$, BoxesRunTime.boxToInteger(this.blockInfoManager().size()))})))));
         this.blockInfoManager().entries().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$reportAllBlocks$2(check$ifrefutable$1))).foreach((x$6) -> {
            $anonfun$reportAllBlocks$3(this, var1, x$6);
            return BoxedUnit.UNIT;
         });
      } catch (NonLocalReturnControl var3) {
         if (var3.key() != var1) {
            throw var3;
         }

         var3.value$mcV$sp();
      }

   }

   public void reregister() {
      label14: {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"BlockManager ", " re-registering with master"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, this.blockManagerId())})))));
         BlockManagerId id = this.master().registerBlockManager(this.blockManagerId(), this.diskBlockManager().localDirsString(), this.maxOnHeapMemory(), this.maxOffHeapMemory(), this.storageEndpoint(), true);
         String var10000 = id.executorId();
         String var2 = BlockManagerId$.MODULE$.INVALID_EXECUTOR_ID();
         if (var10000 == null) {
            if (var2 != null) {
               break label14;
            }
         } else if (!var10000.equals(var2)) {
            break label14;
         }

         this.logError((Function0)(() -> "Exiting executor due to block manager re-registration failure"));
         System.exit(ExecutorExitCode$.MODULE$.BLOCK_MANAGER_REREGISTRATION_FAILED());
         return;
      }

      this.reportAllBlocks();
   }

   private void asyncReregister() {
      synchronized(this.asyncReregisterLock()){}

      try {
         if (this.asyncReregisterTask() == null) {
            this.asyncReregisterTask_$eq(scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> {
               this.reregister();
               synchronized(this.asyncReregisterLock()){}

               try {
                  this.asyncReregisterTask_$eq((Future)null);
               } catch (Throwable var3) {
                  throw var3;
               }

            }, this.org$apache$spark$storage$BlockManager$$futureExecutionContext()));
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public void waitForAsyncReregister() {
      Future task = this.asyncReregisterTask();
      if (task != null) {
         try {
            ThreadUtils$.MODULE$.awaitReady(task, scala.concurrent.duration.Duration..MODULE$.Inf());
         } catch (Throwable var6) {
            if (var6 != null && scala.util.control.NonFatal..MODULE$.apply(var6)) {
               throw SparkCoreErrors$.MODULE$.waitingForAsyncReregistrationError(var6);
            } else {
               throw var6;
            }
         }
      }
   }

   public ManagedBuffer getHostLocalShuffleData(final BlockId blockId, final String[] dirs) {
      return this.shuffleManager().shuffleBlockResolver().getBlockData(blockId, new Some(dirs));
   }

   public ManagedBuffer getLocalBlockData(final BlockId blockId) {
      if (blockId.isShuffle()) {
         this.logDebug((Function0)(() -> "Getting local shuffle block " + blockId));

         ManagedBuffer var10000;
         try {
            ShuffleBlockResolver qual$1 = this.shuffleManager().shuffleBlockResolver();
            Option x$2 = qual$1.getBlockData$default$2();
            var10000 = qual$1.getBlockData(blockId, x$2);
         } catch (IOException var10) {
            if (!((Option)this.conf().get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).isDefined()) {
               throw var10;
            }

            var10000 = FallbackStorage$.MODULE$.read(this.conf(), blockId);
         }

         return var10000;
      } else {
         Option var7 = this.getLocalBytes(blockId);
         if (var7 instanceof Some) {
            Some var8 = (Some)var7;
            BlockData blockData = (BlockData)var8.value();
            return new BlockManagerManagedBuffer(this.blockInfoManager(), blockId, blockData, true, BlockManagerManagedBuffer$.MODULE$.$lessinit$greater$default$5());
         } else if (scala.None..MODULE$.equals(var7)) {
            this.reportBlockStatus(blockId, BlockStatus$.MODULE$.empty(), this.reportBlockStatus$default$3());
            throw SparkCoreErrors$.MODULE$.blockNotFoundError(blockId);
         } else {
            throw new MatchError(var7);
         }
      }
   }

   public boolean putBlockData(final BlockId blockId, final ManagedBuffer data, final StorageLevel level, final ClassTag classTag) {
      return this.putBytes(blockId, new ChunkedByteBuffer(data.nioByteBuffer()), level, this.putBytes$default$4(), classTag);
   }

   public StreamCallbackWithID putBlockDataAsStream(final BlockId blockId, final StorageLevel level, final ClassTag classTag) {
      this.checkShouldStore(blockId);
      if (blockId.isShuffle()) {
         this.logDebug((Function0)(() -> "Putting shuffle block " + blockId));

         try {
            return this.migratableResolver().putShuffleBlockAsStream(blockId, this.serializerManager());
         } catch (ClassCastException var9) {
            throw SparkCoreErrors$.MODULE$.unexpectedShuffleBlockWithUnsupportedResolverError(this.shuffleManager(), blockId);
         }
      } else {
         this.logDebug((Function0)(() -> "Putting regular block " + blockId));
         Tuple2 var6 = this.diskBlockManager().createTempLocalBlock();
         if (var6 != null) {
            File tmpFile = (File)var6._2();
            CountingWritableChannel channel = new CountingWritableChannel(Channels.newChannel(this.serializerManager().wrapForEncryption((OutputStream)(new FileOutputStream(tmpFile)))));
            this.logTrace((Function0)(() -> "Streaming block " + blockId + " to tmp file " + tmpFile));
            return new StreamCallbackWithID(blockId, channel, level, classTag, tmpFile) {
               // $FF: synthetic field
               private final BlockManager $outer;
               private final BlockId blockId$4;
               private final CountingWritableChannel channel$1;
               private final StorageLevel level$1;
               private final ClassTag classTag$1;
               private final File tmpFile$1;

               public ByteBuffer getCompletionResponse() {
                  return super.getCompletionResponse();
               }

               public String getID() {
                  return this.blockId$4.name();
               }

               public void onData(final String streamId, final ByteBuffer buf) {
                  while(buf.hasRemaining()) {
                     this.channel$1.write(buf);
                  }

               }

               public void onComplete(final String streamId) {
                  this.$outer.logTrace((Function0)(() -> "Done receiving block " + this.blockId$4 + ", now putting into local blockManager"));
                  this.channel$1.close();
                  long blockSize = this.channel$1.getCount();
                  boolean blockStored = (this.$outer.new TempFileBasedBlockStoreUpdater(this.blockId$4, this.level$1, this.classTag$1, this.tmpFile$1, blockSize, this.$outer.TempFileBasedBlockStoreUpdater().apply$default$6(), this.$outer.TempFileBasedBlockStoreUpdater().apply$default$7())).save();
                  if (!blockStored) {
                     throw SparkCoreErrors$.MODULE$.failToStoreBlockOnBlockManagerError(this.$outer.blockManagerId(), this.blockId$4);
                  }
               }

               public void onFailure(final String streamId, final Throwable cause) {
                  this.channel$1.close();
                  this.tmpFile$1.delete();
               }

               public {
                  if (BlockManager.this == null) {
                     throw null;
                  } else {
                     this.$outer = BlockManager.this;
                     this.blockId$4 = blockId$4;
                     this.channel$1 = channel$1;
                     this.level$1 = level$1;
                     this.classTag$1 = classTag$1;
                     this.tmpFile$1 = tmpFile$1;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return var0.lambdaDeserialize<invokedynamic>(var0);
               }
            };
         } else {
            throw new MatchError(var6);
         }
      }
   }

   public Seq getLocalMergedBlockData(final ShuffleMergedBlockId blockId, final String[] dirs) {
      return this.shuffleManager().shuffleBlockResolver().getMergedBlockData(blockId, new Some(dirs));
   }

   public MergedBlockMeta getLocalMergedBlockMeta(final ShuffleMergedBlockId blockId, final String[] dirs) {
      return this.shuffleManager().shuffleBlockResolver().getMergedBlockMeta(blockId, new Some(dirs));
   }

   public Option getStatus(final BlockId blockId) {
      return this.blockInfoManager().get(blockId).map((info) -> {
         long memSize = this.memoryStore().contains(blockId) ? this.memoryStore().getSize(blockId) : 0L;
         long diskSize = this.diskStore().contains(blockId) ? this.diskStore().getSize(blockId) : 0L;
         return new BlockStatus(info.level(), memSize, diskSize);
      });
   }

   public Seq getMatchingBlockIds(final Function1 filter) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.blockInfoManager().entries().map((x$7) -> (BlockId)x$7._1()).$plus$plus(() -> this.diskBlockManager().getAllBlocks()).filter(filter).toArray(scala.reflect.ClassTag..MODULE$.apply(BlockId.class))).toImmutableArraySeq();
   }

   public void reportBlockStatus(final BlockId blockId, final BlockStatus status, final long droppedMemorySize) {
      boolean needReregister = !this.tryToReportBlockStatus(blockId, status, droppedMemorySize);
      if (needReregister) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got told to re-register updating block ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
         this.asyncReregister();
      }

      this.logDebug((Function0)(() -> "Told master about block " + blockId));
   }

   public long reportBlockStatus$default$3() {
      return 0L;
   }

   private boolean tryToReportBlockStatus(final BlockId blockId, final BlockStatus status, final long droppedMemorySize) {
      StorageLevel storageLevel = status.storageLevel();
      long inMemSize = Math.max(status.memSize(), droppedMemorySize);
      long onDiskSize = status.diskSize();
      BlockManagerId bmId = blockId.isShuffle() ? this.shuffleServerId() : this.blockManagerId();
      return this.master().updateBlockInfo(bmId, blockId, storageLevel, inMemSize, onDiskSize);
   }

   private long tryToReportBlockStatus$default$3() {
      return 0L;
   }

   public BlockStatus org$apache$spark$storage$BlockManager$$getCurrentBlockStatus(final BlockId blockId, final BlockInfo info) {
      synchronized(info){}

      BlockStatus var5;
      try {
         StorageLevel var6 = info.level();
         BlockStatus var10000;
         if (var6 == null) {
            var10000 = BlockStatus$.MODULE$.empty();
         } else {
            boolean inMem = var6.useMemory() && this.memoryStore().contains(blockId);
            boolean onDisk = var6.useDisk() && this.diskStore().contains(blockId);
            boolean deserialized = inMem ? var6.deserialized() : false;
            int replication = !inMem && !onDisk ? 1 : var6.replication();
            StorageLevel storageLevel = org.apache.spark.storage.StorageLevel..MODULE$.apply(onDisk, inMem, var6.useOffHeap(), deserialized, replication);
            long memSize = inMem ? this.memoryStore().getSize(blockId) : 0L;
            long diskSize = onDisk ? this.diskStore().getSize(blockId) : 0L;
            var10000 = new BlockStatus(storageLevel, memSize, diskSize);
         }

         var5 = var10000;
      } catch (Throwable var17) {
         throw var17;
      }

      return var5;
   }

   public Seq[] org$apache$spark$storage$BlockManager$$getLocationBlockIds(final BlockId[] blockIds) {
      long startTimeNs = System.nanoTime();
      Seq[] locations = (Seq[])this.master().getLocations(blockIds).toArray(scala.reflect.ClassTag..MODULE$.apply(Seq.class));
      this.logDebug((Function0)(() -> "Got multiple block location in " + Utils$.MODULE$.getUsedTimeNs(startTimeNs)));
      return locations;
   }

   private Nothing handleLocalReadFailure(final BlockId blockId) {
      this.releaseLock(blockId, this.releaseLock$default$2());
      this.removeBlock(blockId, this.removeBlock$default$2());
      throw SparkCoreErrors$.MODULE$.readLockedBlockNotFoundError(blockId);
   }

   private boolean isIORelatedException(final Throwable t) {
      return t instanceof IOException || t instanceof KryoException && t.getCause() instanceof IOException;
   }

   public Option getLocalValues(final BlockId blockId) {
      this.logDebug((Function0)(() -> "Getting local block " + blockId));
      Option var3 = this.blockInfoManager().lockForReading(blockId, this.blockInfoManager().lockForReading$default$2());
      if (scala.None..MODULE$.equals(var3)) {
         this.logDebug((Function0)(() -> "Block " + blockId + " was not found"));
         return scala.None..MODULE$;
      } else if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         BlockInfo info = (BlockInfo)var4.value();
         StorageLevel level = info.level();
         this.logDebug((Function0)(() -> "Level for block " + blockId + " is " + level));
         Option taskContext = .MODULE$.apply(TaskContext$.MODULE$.get());
         if (level.useMemory() && this.memoryStore().contains(blockId)) {
            Iterator var19;
            if (level.deserialized()) {
               var19 = (Iterator)this.memoryStore().getValues(blockId).get();
            } else {
               SerializerManager var20 = this.serializerManager();
               ChunkedByteBuffer qual$1 = (ChunkedByteBuffer)this.memoryStore().getBytes(blockId).get();
               boolean x$1 = qual$1.toInputStream$default$1();
               var19 = var20.dataDeserializeStream(blockId, qual$1.toInputStream(x$1), info.classTag());
            }

            Iterator iter = var19;
            CompletionIterator ci = CompletionIterator$.MODULE$.apply(iter, (JFunction0.mcV.sp)() -> this.releaseLock(blockId, taskContext));
            return new Some(new BlockResult(ci, DataReadMethod$.MODULE$.Memory(), info.size()));
         } else if (level.useDisk() && this.diskStore().contains(blockId)) {
            ObjectRef diskData = ObjectRef.create((Object)null);

            try {
               diskData.elem = this.diskStore().getBytes(blockId);
               Iterator var10000;
               if (level.deserialized()) {
                  Iterator diskValues = this.serializerManager().dataDeserializeStream(blockId, ((BlockData)diskData.elem).toInputStream(), info.classTag());
                  var10000 = this.maybeCacheDiskValuesInMemory(info, blockId, level, diskValues);
               } else {
                  InputStream stream = (InputStream)this.maybeCacheDiskBytesInMemory(info, blockId, level, (BlockData)diskData.elem).map((x$8) -> x$8.toInputStream(x$8.toInputStream$default$1())).getOrElse(() -> ((BlockData)diskData.elem).toInputStream());
                  var10000 = this.serializerManager().dataDeserializeStream(blockId, stream, info.classTag());
               }

               Iterator iterToReturn = var10000;
               CompletionIterator ci = CompletionIterator$.MODULE$.apply(iterToReturn, (JFunction0.mcV.sp)() -> this.releaseLockAndDispose(blockId, (BlockData)diskData.elem, taskContext));
               return new Some(new BlockResult(ci, DataReadMethod$.MODULE$.Disk(), info.size()));
            } catch (Throwable var18) {
               if ((BlockData)diskData.elem != null) {
                  ((BlockData)diskData.elem).dispose();
                  diskData.elem = null;
               }

               this.releaseLock(blockId, taskContext);
               if (this.isIORelatedException(var18)) {
                  this.logInfo((Function0)(() -> this.org$apache$spark$storage$BlockManager$$extendMessageWithBlockDetails(var18.getMessage(), blockId)));
                  this.removeBlock(blockId, this.removeBlock$default$2());
               }

               throw var18;
            }
         } else {
            throw this.handleLocalReadFailure(blockId);
         }
      } else {
         throw new MatchError(var3);
      }
   }

   public String org$apache$spark$storage$BlockManager$$extendMessageWithBlockDetails(final String msg, final BlockId blockId) {
      String message = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s. %s - blockId: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{msg, this.blockManagerId().toString(), blockId}));
      File file = this.diskBlockManager().getFile(blockId);
      return file.exists() ? scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s - blockDiskPath: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{message, file.getAbsolutePath()})) : message;
   }

   public Option getLocalBytes(final BlockId blockId) {
      this.logDebug((Function0)(() -> "Getting local block " + blockId + " as bytes"));
      scala.Predef..MODULE$.assert(!blockId.isShuffle(), () -> "Unexpected ShuffleBlockId " + blockId);
      return this.blockInfoManager().lockForReading(blockId, this.blockInfoManager().lockForReading$default$2()).map((info) -> this.doGetLocalBytes(blockId, info));
   }

   private BlockData doGetLocalBytes(final BlockId blockId, final BlockInfo info) {
      StorageLevel level = info.level();
      this.logDebug((Function0)(() -> "Level for block " + blockId + " is " + level));
      if (level.deserialized()) {
         if (level.useDisk() && this.diskStore().contains(blockId)) {
            return this.diskStore().getBytes(blockId);
         } else if (level.useMemory() && this.memoryStore().contains(blockId)) {
            return new ByteBufferBlockData(this.serializerManager().dataSerializeWithExplicitClassTag(blockId, (Iterator)this.memoryStore().getValues(blockId).get(), info.classTag()), true);
         } else {
            throw this.handleLocalReadFailure(blockId);
         }
      } else if (level.useMemory() && this.memoryStore().contains(blockId)) {
         return new ByteBufferBlockData((ChunkedByteBuffer)this.memoryStore().getBytes(blockId).get(), false);
      } else if (level.useDisk() && this.diskStore().contains(blockId)) {
         BlockData diskData = this.diskStore().getBytes(blockId);
         return (BlockData)this.maybeCacheDiskBytesInMemory(info, blockId, level, diskData).map((x$9) -> new ByteBufferBlockData(x$9, false)).getOrElse(() -> diskData);
      } else {
         throw this.handleLocalReadFailure(blockId);
      }
   }

   public Option getRemoteValues(final BlockId blockId, final ClassTag evidence$1) {
      ClassTag ct = (ClassTag)scala.Predef..MODULE$.implicitly(evidence$1);
      return this.getRemoteBlock(blockId, (data) -> {
         Iterator values = this.serializerManager().dataDeserializeStream(blockId, data.createInputStream(), ct);
         return new BlockResult(values, DataReadMethod$.MODULE$.Network(), data.size());
      });
   }

   public Option getRemoteBlock(final BlockId blockId, final Function1 bufferTransformer) {
      this.logDebug((Function0)(() -> "Getting remote block " + blockId));
      scala.Predef..MODULE$.require(blockId != null, () -> "BlockId is null");
      Option locationsAndStatusOption = this.master().getLocationsAndStatus(blockId, this.blockManagerId().host());
      if (locationsAndStatusOption.isEmpty()) {
         this.logDebug((Function0)(() -> "Block " + blockId + " is unknown by block manager master"));
         return scala.None..MODULE$;
      } else {
         BlockManagerMessages.BlockLocationsAndStatus locationsAndStatus = (BlockManagerMessages.BlockLocationsAndStatus)locationsAndStatusOption.get();
         long blockSize = scala.runtime.RichLong..MODULE$.max$extension(scala.Predef..MODULE$.longWrapper(locationsAndStatus.status().diskSize()), locationsAndStatus.status().memSize());
         return locationsAndStatus.localDirs().flatMap((localDirs) -> {
            Option blockDataOption = this.readDiskBlockFromSameHostExecutor(blockId, localDirs, locationsAndStatus.status().diskSize());
            Option res = blockDataOption.flatMap((blockData) -> {
               Object var10000;
               try {
                  var10000 = new Some(bufferTransformer.apply(blockData));
               } catch (Throwable var7) {
                  if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
                     throw var7;
                  }

                  this.logDebug((Function0)(() -> "Block from the same host executor cannot be opened: "), var7);
                  var10000 = scala.None..MODULE$;
               }

               return (Option)var10000;
            });
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Read ", " from the disk of a same host executor is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATUS..MODULE$, res.isDefined() ? "successful." : "failed.")}))))));
            return res;
         }).orElse(() -> this.fetchRemoteManagedBuffer(blockId, blockSize, locationsAndStatus).map(bufferTransformer));
      }
   }

   private Seq preferExecutors(final Seq locations) {
      Tuple2 var4 = locations.partition((x$10) -> BoxesRunTime.boxToBoolean($anonfun$preferExecutors$1(this, x$10)));
      if (var4 != null) {
         Seq executors = (Seq)var4._1();
         Seq shuffleServers = (Seq)var4._2();
         Tuple2 var3 = new Tuple2(executors, shuffleServers);
         Seq executors = (Seq)var3._1();
         Seq shuffleServers = (Seq)var3._2();
         return (Seq)executors.$plus$plus(shuffleServers);
      } else {
         throw new MatchError(var4);
      }
   }

   public Seq sortLocations(final Seq locations) {
      Seq locs = (Seq)scala.util.Random..MODULE$.shuffle(locations, scala.collection.BuildFrom..MODULE$.buildFromIterableOps());
      Tuple2 var7 = locs.partition((x$12) -> BoxesRunTime.boxToBoolean($anonfun$sortLocations$1(this, x$12)));
      if (var7 != null) {
         Seq preferredLocs = (Seq)var7._1();
         Seq otherLocs = (Seq)var7._2();
         Tuple2 var6 = new Tuple2(preferredLocs, otherLocs);
         Seq preferredLocs = (Seq)var6._1();
         Seq otherLocs = (Seq)var6._2();
         Option var13 = this.blockManagerId().topologyInfo();
         scala.collection.immutable..colon.colon var10000;
         if (scala.None..MODULE$.equals(var13)) {
            var10000 = new scala.collection.immutable..colon.colon(preferredLocs, new scala.collection.immutable..colon.colon(otherLocs, scala.collection.immutable.Nil..MODULE$));
         } else {
            if (!(var13 instanceof Some)) {
               throw new MatchError(var13);
            }

            Tuple2 var15 = otherLocs.partition((loc) -> BoxesRunTime.boxToBoolean($anonfun$sortLocations$2(this, loc)));
            if (var15 == null) {
               throw new MatchError(var15);
            }

            Seq sameRackLocs = (Seq)var15._1();
            Seq differentRackLocs = (Seq)var15._2();
            Tuple2 var14 = new Tuple2(sameRackLocs, differentRackLocs);
            Seq sameRackLocs = (Seq)var14._1();
            Seq differentRackLocs = (Seq)var14._2();
            var10000 = new scala.collection.immutable..colon.colon(preferredLocs, new scala.collection.immutable..colon.colon(sameRackLocs, new scala.collection.immutable..colon.colon(differentRackLocs, scala.collection.immutable.Nil..MODULE$)));
         }

         Seq orderedParts = var10000;
         return (Seq)((IterableOnceOps)orderedParts.map((locationsx) -> this.preferExecutors(locationsx))).reduce((x$15, x$16) -> (Seq)x$15.$plus$plus(x$16));
      } else {
         throw new MatchError(var7);
      }
   }

   private Option fetchRemoteManagedBuffer(final BlockId blockId, final long blockSize, final BlockManagerMessages.BlockLocationsAndStatus locationsAndStatus) {
      RemoteBlockDownloadFileManager tempFileManager = blockSize > this.maxRemoteBlockToMem() ? this.remoteBlockTempFileManager() : null;
      IntRef runningFailureCount = IntRef.create(0);
      IntRef totalFailureCount = IntRef.create(0);
      Seq locations = this.sortLocations(locationsAndStatus.locations());
      int maxFetchFailures = locations.size();
      Iterator locationIterator = locations.iterator();

      while(locationIterator.hasNext()) {
         BlockManagerId loc = (BlockManagerId)locationIterator.next();
         this.logDebug((Function0)(() -> "Getting remote block " + blockId + " from " + loc));

         ManagedBuffer var10000;
         try {
            ManagedBuffer buf = this.blockTransferService().fetchBlockSync(loc.host(), loc.port(), loc.executorId(), blockId.toString(), tempFileManager);
            if (blockSize > 0L && buf.size() == 0L) {
               throw org.apache.spark.SparkException..MODULE$.internalError("Empty buffer received for non empty block when fetching remote block " + blockId + " from " + loc, "STORAGE");
            }

            var10000 = buf;
         } catch (Throwable var18) {
            if (var18 == null || !scala.util.control.NonFatal..MODULE$.apply(var18)) {
               throw var18;
            }

            ++runningFailureCount.elem;
            ++totalFailureCount.elem;
            if (totalFailureCount.elem >= maxFetchFailures) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to fetch remote block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from [", "] "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_IDS..MODULE$, locations.mkString(", "))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"after ", " fetch failures. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FAILURES..MODULE$, BoxesRunTime.boxToInteger(totalFailureCount.elem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Most recent failure cause:"})))).log(scala.collection.immutable.Nil..MODULE$))), var18);
               return scala.None..MODULE$;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to fetch remote block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, loc)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(failed attempt ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FAILURES..MODULE$, BoxesRunTime.boxToInteger(runningFailureCount.elem))}))))), var18);
            if (runningFailureCount.elem >= this.maxFailuresBeforeLocationRefresh()) {
               locationIterator = this.sortLocations(this.master().getLocations(blockId)).iterator();
               this.logDebug((Function0)(() -> "Refreshed locations from the driver after " + runningFailureCount.elem + " fetch failures."));
               runningFailureCount.elem = 0;
            }

            var10000 = null;
         }

         ManagedBuffer data = var10000;
         if (data != null) {
            scala.Predef..MODULE$.assert(!(data instanceof BlockManagerManagedBuffer));
            return new Some(data);
         }

         this.logDebug((Function0)(() -> "The value of block " + blockId + " is null"));
      }

      this.logDebug((Function0)(() -> "Block " + blockId + " not found"));
      return scala.None..MODULE$;
   }

   public Option readDiskBlockFromSameHostExecutor(final BlockId blockId, final String[] localDirs, final long blockSize) {
      File file = new File(ExecutorDiskUtils.getFilePath(localDirs, this.subDirsPerLocalDir(), blockId.name()));
      if (file.exists()) {
         Option var8 = this.securityManager.getIOEncryptionKey();
         Object var10000;
         if (var8 instanceof Some) {
            Some var9 = (Some)var8;
            byte[] key = (byte[])var9.value();
            var10000 = new EncryptedManagedBuffer(new EncryptedBlockData(file, blockSize, this.conf(), key));
         } else {
            SparkConf x$1 = this.conf();
            String x$2 = "shuffle";
            Some x$3 = new Some(this.securityManager.getRpcSSLOptions());
            int x$4 = SparkTransportConf$.MODULE$.fromSparkConf$default$3();
            Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
            TransportConf transportConf = SparkTransportConf$.MODULE$.fromSparkConf(x$1, "shuffle", x$4, x$5, x$3);
            var10000 = new FileSegmentManagedBuffer(transportConf, file, 0L, file.length());
         }

         ManagedBuffer managedBuffer = (ManagedBuffer)var10000;
         return new Some(managedBuffer);
      } else {
         return scala.None..MODULE$;
      }
   }

   public Option getRemoteBytes(final BlockId blockId) {
      return this.getRemoteBlock(blockId, (data) -> this.remoteReadNioBufferConversion() ? new ChunkedByteBuffer(data.nioByteBuffer()) : ChunkedByteBuffer$.MODULE$.fromManagedBuffer(data));
   }

   public Option get(final BlockId blockId, final ClassTag evidence$2) {
      Option local = this.getLocalValues(blockId);
      if (local.isDefined()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Found block ", " locally"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
         return local;
      } else {
         Option remote = this.getRemoteValues(blockId, evidence$2);
         if (remote.isDefined()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Found block ", " remotely"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
            return remote;
         } else {
            return scala.None..MODULE$;
         }
      }
   }

   public void downgradeLock(final BlockId blockId) {
      this.blockInfoManager().downgradeLock(blockId);
   }

   public void releaseLock(final BlockId blockId, final Option taskContext) {
      Option taskAttemptId = taskContext.map((x$17) -> BoxesRunTime.boxToLong($anonfun$releaseLock$1(x$17)));
      if (taskContext.isDefined() && ((TaskContext)taskContext.get()).isCompleted()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, taskAttemptId.get())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"already completed, not releasing lock for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))))));
      } else {
         this.blockInfoManager().unlock(blockId, taskAttemptId);
      }
   }

   public Option releaseLock$default$2() {
      return scala.None..MODULE$;
   }

   public void registerTask(final long taskAttemptId) {
      this.blockInfoManager().registerTask(taskAttemptId);
   }

   public Seq releaseAllLocksForTask(final long taskAttemptId) {
      return this.blockInfoManager().releaseAllLocksForTask(taskAttemptId);
   }

   public Either getOrElseUpdateRDDBlock(final long taskId, final RDDBlockId blockId, final StorageLevel level, final ClassTag classTag, final Function0 makeIterator) {
      boolean isCacheVisible = this.isRDDBlockVisible(blockId);
      Either res = this.getOrElseUpdate(blockId, level, classTag, makeIterator, isCacheVisible);
      if (res.isLeft() && !isCacheVisible) {
         this.master().updateRDDBlockTaskInfo(blockId, taskId);
      }

      return res;
   }

   private Either getOrElseUpdate(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final Function0 makeIterator, final boolean isCacheVisible) {
      BooleanRef computed = BooleanRef.create(false);
      Function0 iterator = () -> {
         computed.elem = true;
         return (Iterator)makeIterator.apply();
      };
      if (isCacheVisible) {
         Option var10 = this.get(blockId, classTag);
         if (var10 instanceof Some) {
            Some var11 = (Some)var10;
            BlockResult block = (BlockResult)var11.value();
            return new Left(block);
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      boolean x$5 = true;
      boolean x$6 = this.doPutIterator$default$5();
      Option var13 = this.doPutIterator(blockId, iterator, level, classTag, x$6, true);
      if (!scala.None..MODULE$.equals(var13)) {
         if (var13 instanceof Some) {
            Some var21 = (Some)var13;
            PartiallyUnrolledIterator iter = (PartiallyUnrolledIterator)var21.value();
            return new Right(iter);
         } else {
            throw new MatchError(var13);
         }
      } else {
         if (!isCacheVisible && !computed.elem) {
            BoxesRunTime.boxToLong(Utils$.MODULE$.getIteratorSize((Iterator)makeIterator.apply()));
         } else {
            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         BlockResult blockResult = (BlockResult)this.getLocalValues(blockId).getOrElse(() -> {
            this.releaseLock(blockId, this.releaseLock$default$2());
            throw SparkCoreErrors$.MODULE$.failToGetBlockWithLockError(blockId);
         });
         this.releaseLock(blockId, this.releaseLock$default$2());
         return new Left(blockResult);
      }
   }

   public boolean putIterator(final BlockId blockId, final Iterator values, final StorageLevel level, final boolean tellMaster, final ClassTag evidence$3) {
      scala.Predef..MODULE$.require(values != null, () -> "Values is null");
      Option var7 = this.doPutIterator(blockId, () -> values, level, (ClassTag)scala.Predef..MODULE$.implicitly(evidence$3), tellMaster, this.doPutIterator$default$6());
      if (scala.None..MODULE$.equals(var7)) {
         return true;
      } else if (var7 instanceof Some) {
         Some var8 = (Some)var7;
         PartiallyUnrolledIterator iter = (PartiallyUnrolledIterator)var8.value();
         iter.close();
         return false;
      } else {
         throw new MatchError(var7);
      }
   }

   public boolean putIterator$default$4() {
      return true;
   }

   public DiskBlockObjectWriter getDiskWriter(final BlockId blockId, final File file, final SerializerInstance serializerInstance, final int bufferSize, final ShuffleWriteMetricsReporter writeMetrics) {
      boolean syncWrites = BoxesRunTime.unboxToBoolean(this.conf().get(package$.MODULE$.SHUFFLE_SYNC()));
      return new DiskBlockObjectWriter(file, this.serializerManager(), serializerInstance, bufferSize, syncWrites, writeMetrics, blockId);
   }

   public boolean putBytes(final BlockId blockId, final ChunkedByteBuffer bytes, final StorageLevel level, final boolean tellMaster, final ClassTag evidence$4) {
      scala.Predef..MODULE$.require(bytes != null, () -> "Bytes is null");
      ByteBufferBlockStoreUpdater blockStoreUpdater = new ByteBufferBlockStoreUpdater(blockId, level, (ClassTag)scala.Predef..MODULE$.implicitly(evidence$4), bytes, tellMaster, this.ByteBufferBlockStoreUpdater().apply$default$6());
      return blockStoreUpdater.save();
   }

   public boolean putBytes$default$4() {
      return true;
   }

   public boolean isRDDBlockVisible(final RDDBlockId blockId) {
      if (!this.trackingCacheVisibility()) {
         return true;
      } else if (this.blockInfoManager().isRDDBlockVisible(blockId)) {
         return true;
      } else if (this.master().isRDDBlockVisible(blockId)) {
         this.blockInfoManager().tryMarkBlockAsVisible(blockId);
         return true;
      } else {
         return false;
      }
   }

   public Option org$apache$spark$storage$BlockManager$$doPut(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final boolean tellMaster, final boolean keepReadLock, final Function1 putBody) {
      scala.Predef..MODULE$.require(blockId != null, () -> "BlockId is null");
      scala.Predef..MODULE$.require(level != null && level.isValid(), () -> "StorageLevel is null or invalid");
      this.checkShouldStore(blockId);
      BlockInfo newInfo = new BlockInfo(level, classTag, tellMaster);
      if (this.blockInfoManager().lockNewBlockForWriting(blockId, newInfo, keepReadLock)) {
         BlockInfo putBlockInfo = newInfo;
         long startTimeNs = System.nanoTime();
         Object exceptionWasThrown = true;

         BlockManager var10000;
         try {
            Option res = (Option)putBody.apply(putBlockInfo);
            exceptionWasThrown = false;
            if (res.isEmpty()) {
               if (keepReadLock) {
                  this.blockInfoManager().downgradeLock(blockId);
               } else {
                  this.blockInfoManager().unlock(blockId, this.blockInfoManager().unlock$default$2());
               }
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Putting block ", " failed"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
               this.removeBlockInternal(blockId, false);
            }
         } catch (Throwable var22) {
            if (var22 != null && scala.util.control.NonFatal..MODULE$.apply(var22)) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Putting block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"failed due to exception ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var22)}))))));
               throw var22;
            }

            throw var22;
         } finally {
            var10000 = (BlockManager)exceptionWasThrown;
            if (exceptionWasThrown != false) {
               this.removeBlockInternal(blockId, tellMaster);
               var10000 = this;
               this.org$apache$spark$storage$BlockManager$$addUpdatedBlockStatusToTaskMetrics(blockId, BlockStatus$.MODULE$.empty());
            }

         }

         Option result = var10000;
         String usedTimeMs = Utils$.MODULE$.getUsedTimeNs(startTimeNs);
         if (level.replication() > 1) {
            this.logDebug((Function0)(() -> "Putting block " + blockId + " with replication took " + usedTimeMs));
         } else {
            this.logDebug((Function0)(() -> "Putting block " + blockId + " without replication took " + usedTimeMs));
         }

         return result;
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"already exists on this machine; not re-adding it"})))).log(scala.collection.immutable.Nil..MODULE$))));
         return scala.None..MODULE$;
      }
   }

   private Option doPutIterator(final BlockId blockId, final Function0 iterator, final StorageLevel level, final ClassTag classTag, final boolean tellMaster, final boolean keepReadLock) {
      return this.org$apache$spark$storage$BlockManager$$doPut(blockId, level, classTag, tellMaster, keepReadLock, (info) -> {
         long startTimeNs = System.nanoTime();
         Option iteratorFromFailedMemoryStorePut = scala.None..MODULE$;
         long size = 0L;
         if (level.useMemory()) {
            if (level.deserialized()) {
               Either var14 = this.memoryStore().putIteratorAsValues(blockId, (Iterator)iterator.apply(), level.memoryMode(), classTag);
               if (var14 instanceof Right) {
                  Right var15 = (Right)var14;
                  long s = BoxesRunTime.unboxToLong(var15.value());
                  size = s;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  if (!(var14 instanceof Left)) {
                     throw new MatchError(var14);
                  }

                  Left var18 = (Left)var14;
                  PartiallyUnrolledIterator iter = (PartiallyUnrolledIterator)var18.value();
                  if (level.useDisk()) {
                     this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting block ", " to disk instead."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
                     this.diskStore().put(blockId, (channel) -> {
                        $anonfun$doPutIterator$3(this, blockId, iter, classTag, channel);
                        return BoxedUnit.UNIT;
                     });
                     size = this.diskStore().getSize(blockId);
                     BoxedUnit var34 = BoxedUnit.UNIT;
                  } else {
                     iteratorFromFailedMemoryStorePut = new Some(iter);
                     BoxedUnit var35 = BoxedUnit.UNIT;
                  }
               }
            } else {
               Either var20 = this.memoryStore().putIteratorAsBytes(blockId, (Iterator)iterator.apply(), classTag, level.memoryMode());
               if (var20 instanceof Right) {
                  Right var21 = (Right)var20;
                  long s = BoxesRunTime.unboxToLong(var21.value());
                  size = s;
                  BoxedUnit var36 = BoxedUnit.UNIT;
               } else {
                  if (!(var20 instanceof Left)) {
                     throw new MatchError(var20);
                  }

                  Left var24 = (Left)var20;
                  PartiallySerializedBlock partiallySerializedValues = (PartiallySerializedBlock)var24.value();
                  if (level.useDisk()) {
                     this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting block ", " to disk instead."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
                     this.diskStore().put(blockId, (channel) -> {
                        $anonfun$doPutIterator$5(partiallySerializedValues, channel);
                        return BoxedUnit.UNIT;
                     });
                     size = this.diskStore().getSize(blockId);
                     BoxedUnit var37 = BoxedUnit.UNIT;
                  } else {
                     iteratorFromFailedMemoryStorePut = new Some(partiallySerializedValues.valuesIterator());
                     BoxedUnit var38 = BoxedUnit.UNIT;
                  }
               }
            }
         } else if (level.useDisk()) {
            this.diskStore().put(blockId, (channel) -> {
               $anonfun$doPutIterator$6(this, blockId, iterator, classTag, channel);
               return BoxedUnit.UNIT;
            });
            size = this.diskStore().getSize(blockId);
         }

         BlockStatus putBlockStatus = this.org$apache$spark$storage$BlockManager$$getCurrentBlockStatus(blockId, info);
         boolean blockWasSuccessfullyStored = putBlockStatus.storageLevel().isValid();
         if (blockWasSuccessfullyStored) {
            info.size_$eq(size);
            if (tellMaster && info.tellMaster()) {
               this.reportBlockStatus(blockId, putBlockStatus, this.reportBlockStatus$default$3());
            }

            this.org$apache$spark$storage$BlockManager$$addUpdatedBlockStatusToTaskMetrics(blockId, putBlockStatus);
            this.logDebug((Function0)(() -> "Put block " + blockId + " locally took " + Utils$.MODULE$.getUsedTimeNs(startTimeNs)));
            if (level.replication() > 1) {
               long remoteStartTimeNs = System.nanoTime();
               BlockData bytesToReplicate = this.doGetLocalBytes(blockId, info);

               try {
                  this.org$apache$spark$storage$BlockManager$$replicate(blockId, bytesToReplicate, level, classTag, this.org$apache$spark$storage$BlockManager$$replicate$default$5(), this.org$apache$spark$storage$BlockManager$$replicate$default$6());
               } finally {
                  bytesToReplicate.dispose();
               }

               this.logDebug((Function0)(() -> "Put block " + blockId + " remotely took " + Utils$.MODULE$.getUsedTimeNs(remoteStartTimeNs)));
            }
         }

         scala.Predef..MODULE$.assert(blockWasSuccessfullyStored == iteratorFromFailedMemoryStorePut.isEmpty());
         return iteratorFromFailedMemoryStorePut;
      });
   }

   private boolean doPutIterator$default$5() {
      return true;
   }

   private boolean doPutIterator$default$6() {
      return false;
   }

   private Option maybeCacheDiskBytesInMemory(final BlockInfo blockInfo, final BlockId blockId, final StorageLevel level, final BlockData diskData) {
      scala.Predef..MODULE$.require(!level.deserialized());
      if (level.useMemory()) {
         synchronized(blockInfo){}

         Object var7;
         try {
            Object var10000;
            if (this.memoryStore().contains(blockId)) {
               diskData.dispose();
               var10000 = new Some(this.memoryStore().getBytes(blockId).get());
            } else {
               MemoryMode var9 = level.memoryMode();
               Function1 var13;
               if (MemoryMode.ON_HEAP.equals(var9)) {
                  var13 = (x$1) -> $anonfun$maybeCacheDiskBytesInMemory$1(BoxesRunTime.unboxToInt(x$1));
               } else {
                  if (!MemoryMode.OFF_HEAP.equals(var9)) {
                     throw new MatchError(var9);
                  }

                  var13 = (x$1) -> $anonfun$maybeCacheDiskBytesInMemory$2(BoxesRunTime.unboxToInt(x$1));
               }

               Function1 allocator = var13;
               boolean putSucceeded = this.memoryStore().putBytes(blockId, diskData.size(), level.memoryMode(), () -> diskData.toChunkedByteBuffer(allocator), scala.reflect.ClassTag..MODULE$.Nothing());
               if (putSucceeded) {
                  diskData.dispose();
                  var10000 = new Some(this.memoryStore().getBytes(blockId).get());
               } else {
                  var10000 = scala.None..MODULE$;
               }
            }

            var7 = var10000;
         } catch (Throwable var12) {
            throw var12;
         }

         return (Option)var7;
      } else {
         return scala.None..MODULE$;
      }
   }

   private Iterator maybeCacheDiskValuesInMemory(final BlockInfo blockInfo, final BlockId blockId, final StorageLevel level, final Iterator diskIterator) {
      scala.Predef..MODULE$.require(level.deserialized());
      ClassTag classTag = blockInfo.classTag();
      if (level.useMemory()) {
         synchronized(blockInfo){}

         Object var8;
         try {
            Object var10000;
            if (this.memoryStore().contains(blockId)) {
               var10000 = (Iterator)this.memoryStore().getValues(blockId).get();
            } else {
               Either var9 = this.memoryStore().putIteratorAsValues(blockId, diskIterator, level.memoryMode(), classTag);
               if (var9 instanceof Left) {
                  Left var10 = (Left)var9;
                  PartiallyUnrolledIterator iter = (PartiallyUnrolledIterator)var10.value();
                  var10000 = iter;
               } else {
                  if (!(var9 instanceof Right)) {
                     throw new MatchError(var9);
                  }

                  var10000 = (Iterator)this.memoryStore().getValues(blockId).get();
               }
            }

            var8 = var10000;
         } catch (Throwable var13) {
            throw var13;
         }

         return (Iterator)var8;
      } else {
         return diskIterator;
      }
   }

   public Seq getPeers(final boolean forceFetch) {
      synchronized(this.peerFetchLock()){}

      Object var3;
      try {
         int cachedPeersTtl = BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.STORAGE_CACHED_PEERS_TTL()));
         long diff = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.lastPeerFetchTimeNs());
         boolean timeout = diff > (long)cachedPeersTtl;
         if (this.cachedPeers() == null || forceFetch || timeout) {
            this.cachedPeers_$eq((Seq)this.master().getPeers(this.blockManagerId()).sortBy((x$18) -> BoxesRunTime.boxToInteger($anonfun$getPeers$1(x$18)), scala.math.Ordering.Int..MODULE$));
            this.lastPeerFetchTimeNs_$eq(System.nanoTime());
            this.logDebug((Function0)(() -> {
               Seq var10000 = this.cachedPeers();
               return "Fetched peers from master: " + var10000.mkString("[", ",", "]");
            }));
         }

         var3 = this.cachedPeers().isEmpty() && ((Option)this.conf().get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).isDefined() ? new scala.collection.immutable..colon.colon(FallbackStorage$.MODULE$.FALLBACK_BLOCK_MANAGER_ID(), scala.collection.immutable.Nil..MODULE$) : this.cachedPeers();
      } catch (Throwable var9) {
         throw var9;
      }

      return (Seq)var3;
   }

   public boolean replicateBlock(final BlockId blockId, final Set existingReplicas, final int maxReplicas, final Option maxReplicationFailures) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using ", " to pro-actively replicate "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, this.blockManagerId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))))));
      return this.blockInfoManager().lockForReading(blockId, this.blockInfoManager().lockForReading$default$2()).forall((info) -> BoxesRunTime.boxToBoolean($anonfun$replicateBlock$2(this, blockId, maxReplicas, existingReplicas, maxReplicationFailures, info)));
   }

   public boolean org$apache$spark$storage$BlockManager$$replicate(final BlockId blockId, final BlockData data, final StorageLevel level, final ClassTag classTag, final Set existingReplicas, final Option maxReplicationFailures) {
      int maxReplicationFailureCount = BoxesRunTime.unboxToInt(maxReplicationFailures.getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.STORAGE_MAX_REPLICATION_FAILURE()))));
      StorageLevel tLevel = org.apache.spark.storage.StorageLevel..MODULE$.apply(level.useDisk(), level.useMemory(), level.useOffHeap(), level.deserialized(), 1);
      int numPeersToReplicateTo = level.replication() - 1;
      long startTime = System.nanoTime();
      HashSet peersReplicatedTo = (HashSet)scala.collection.mutable.HashSet..MODULE$.empty().$plus$plus(existingReplicas);
      HashSet peersFailedToReplicateTo = scala.collection.mutable.HashSet..MODULE$.empty();
      IntRef numFailures = IntRef.create(0);
      Seq initialPeers = (Seq)this.getPeers(false).filterNot((elem) -> BoxesRunTime.boxToBoolean($anonfun$replicate$2(existingReplicas, elem)));
      List peersForReplication = this.blockReplicationPolicy().prioritize(this.blockManagerId(), initialPeers, peersReplicatedTo, blockId, numPeersToReplicateTo);

      while(numFailures.elem <= maxReplicationFailureCount && peersForReplication.nonEmpty() && peersReplicatedTo.size() < numPeersToReplicateTo) {
         BlockManagerId peer = (BlockManagerId)peersForReplication.head();

         try {
            long onePeerStartTime = System.nanoTime();
            this.logTrace((Function0)(() -> "Trying to replicate " + blockId + " of " + data.size() + " bytes to " + peer));
            BlockManagerManagedBuffer buffer = new BlockManagerManagedBuffer(this.blockInfoManager(), blockId, data, false, false);
            this.blockTransferService().uploadBlockSync(peer.host(), peer.port(), peer.executorId(), blockId, buffer, tLevel, classTag);
            this.logTrace((Function0)(() -> "Replicated " + blockId + " of " + data.size() + " bytes to " + peer + " in " + (double)(System.nanoTime() - onePeerStartTime) / (double)1000000.0F + " ms"));
            peersForReplication = (List)peersForReplication.tail();
            peersReplicatedTo.$plus$eq(peer);
         } catch (Throwable var27) {
            if (var27 instanceof InterruptedException var24) {
               throw var24;
            }

            if (var27 == null || !scala.util.control.NonFatal..MODULE$.apply(var27)) {
               throw var27;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to replicate ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PEER..MODULE$, peer)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"failure #", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FAILURES..MODULE$, BoxesRunTime.boxToInteger(numFailures.elem))}))))), var27);
            peersFailedToReplicateTo.$plus$eq(peer);
            Seq filteredPeers = (Seq)this.getPeers(true).filter((p) -> BoxesRunTime.boxToBoolean($anonfun$replicate$6(peersFailedToReplicateTo, peersReplicatedTo, p)));
            ++numFailures.elem;
            peersForReplication = this.blockReplicationPolicy().prioritize(this.blockManagerId(), filteredPeers, peersReplicatedTo, blockId, numPeersToReplicateTo - peersReplicatedTo.size());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      this.logDebug((Function0)(() -> "Replicating " + blockId + " of " + data.size() + " bytes to " + peersReplicatedTo.size() + " peer(s) took " + (double)(System.nanoTime() - startTime) / (double)1000000.0F + " ms"));
      if (peersReplicatedTo.size() < numPeersToReplicateTo) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " replicated to only "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " peer(s) "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PEERS_REPLICATED_TO..MODULE$, BoxesRunTime.boxToInteger(peersReplicatedTo.size()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"instead of ", " peers"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PEERS_TO_REPLICATE_TO..MODULE$, BoxesRunTime.boxToInteger(numPeersToReplicateTo))}))))));
         return false;
      } else {
         this.logDebug((Function0)(() -> "block " + blockId + " replicated to " + peersReplicatedTo.mkString(", ")));
         return true;
      }
   }

   public Option replicateBlock$default$4() {
      return scala.None..MODULE$;
   }

   public Set org$apache$spark$storage$BlockManager$$replicate$default$5() {
      return scala.Predef..MODULE$.Set().empty();
   }

   public Option org$apache$spark$storage$BlockManager$$replicate$default$6() {
      return scala.None..MODULE$;
   }

   public Option getSingle(final BlockId blockId, final ClassTag evidence$5) {
      return this.get(blockId, evidence$5).map((x$19) -> x$19.data().next());
   }

   public boolean putSingle(final BlockId blockId, final Object value, final StorageLevel level, final boolean tellMaster, final ClassTag evidence$6) {
      return this.putIterator(blockId, scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{value})), level, tellMaster, evidence$6);
   }

   public boolean putSingle$default$4() {
      return true;
   }

   public StorageLevel dropFromMemory(final BlockId blockId, final Function0 data, final ClassTag evidence$7) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropping block ", " from memory"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
      BlockInfo info = this.blockInfoManager().assertBlockIsLockedForWriting(blockId);
      boolean blockIsUpdated = false;
      StorageLevel level = info.level();
      if (level.useDisk() && !this.diskStore().contains(blockId)) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Writing block ", " to disk"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
         Either var8 = (Either)data.apply();
         if (var8 instanceof Left) {
            Left var9 = (Left)var8;
            Object elements = var9.value();
            this.diskStore().put(blockId, (channel) -> {
               $anonfun$dropFromMemory$3(this, blockId, elements, info, channel);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!(var8 instanceof Right)) {
               throw new MatchError(var8);
            }

            Right var11 = (Right)var8;
            ChunkedByteBuffer bytes = (ChunkedByteBuffer)var11.value();
            this.diskStore().putBytes(blockId, bytes);
            BoxedUnit var17 = BoxedUnit.UNIT;
         }

         blockIsUpdated = true;
      }

      long droppedMemorySize = this.memoryStore().contains(blockId) ? this.memoryStore().getSize(blockId) : 0L;
      boolean blockIsRemoved = this.memoryStore().remove(blockId);
      if (blockIsRemoved) {
         blockIsUpdated = true;
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"could not be dropped from memory as it does not exist"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      BlockStatus status = this.org$apache$spark$storage$BlockManager$$getCurrentBlockStatus(blockId, info);
      if (info.tellMaster()) {
         this.reportBlockStatus(blockId, status, droppedMemorySize);
      }

      if (blockIsUpdated) {
         this.org$apache$spark$storage$BlockManager$$addUpdatedBlockStatusToTaskMetrics(blockId, status);
      }

      return status.storageLevel();
   }

   public int removeRdd(final int rddId) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing RDD ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(rddId))})))));
      Iterator blocksToRemove = this.blockInfoManager().entries().flatMap((x$20) -> ((BlockId)x$20._1()).asRDDId()).filter((x$21) -> BoxesRunTime.boxToBoolean($anonfun$removeRdd$3(rddId, x$21)));
      blocksToRemove.foreach((blockId) -> {
         $anonfun$removeRdd$4(this, blockId);
         return BoxedUnit.UNIT;
      });
      return blocksToRemove.size();
   }

   public void decommissionBlockManager() {
      this.storageEndpoint().ask(BlockManagerMessages.DecommissionBlockManager$.MODULE$, scala.reflect.ClassTag..MODULE$.Nothing());
   }

   public synchronized void decommissionSelf() {
      Option var2 = this.decommissioner();
      if (scala.None..MODULE$.equals(var2)) {
         this.logInfo((Function0)(() -> "Starting block manager decommissioning process..."));
         this.decommissioner_$eq(new Some(new BlockManagerDecommissioner(this.conf(), this)));
         this.decommissioner().foreach((x$22) -> {
            $anonfun$decommissionSelf$2(x$22);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else if (var2 instanceof Some) {
         this.logDebug((Function0)(() -> "Block manager already in decommissioning state"));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var2);
      }
   }

   public Tuple2 lastMigrationInfo() {
      return (Tuple2)this.decommissioner().map((x$23) -> x$23.lastMigrationInfo()).getOrElse(() -> new Tuple2.mcJZ.sp(0L, false));
   }

   public Seq getMigratableRDDBlocks() {
      return this.master().getReplicateInfoForRDDBlocks(this.blockManagerId());
   }

   public int removeBroadcast(final long broadcastId, final boolean tellMaster) {
      this.logDebug((Function0)(() -> "Removing broadcast " + broadcastId));
      Iterator blocksToRemove = this.blockInfoManager().entries().map((x$24) -> (BlockId)x$24._1()).collect(new Serializable(broadcastId) {
         private static final long serialVersionUID = 0L;
         private final long broadcastId$1;

         public final Object applyOrElse(final BlockId x1, final Function1 default) {
            if (x1 instanceof BroadcastBlockId var5) {
               long var6 = var5.broadcastId();
               if (this.broadcastId$1 == var6) {
                  return var5;
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final BlockId x1) {
            if (x1 instanceof BroadcastBlockId var4) {
               long var5 = var4.broadcastId();
               if (this.broadcastId$1 == var5) {
                  return true;
               }
            }

            return false;
         }

         public {
            this.broadcastId$1 = broadcastId$1;
         }
      });
      blocksToRemove.foreach((blockId) -> {
         $anonfun$removeBroadcast$3(this, tellMaster, blockId);
         return BoxedUnit.UNIT;
      });
      return blocksToRemove.size();
   }

   public int removeCache(final String sessionUUID) {
      this.logDebug((Function0)(() -> "Removing cache of spark session with UUID: " + sessionUUID));
      Iterator blocksToRemove = this.blockInfoManager().entries().map((x$25) -> (BlockId)x$25._1()).collect(new Serializable(sessionUUID) {
         private static final long serialVersionUID = 0L;
         private final String sessionUUID$1;

         public final Object applyOrElse(final BlockId x1, final Function1 default) {
            if (x1 instanceof CacheId var5) {
               String var10000 = var5.sessionUUID();
               String var6 = this.sessionUUID$1;
               if (var10000 == null) {
                  if (var6 == null) {
                     return var5;
                  }
               } else if (var10000.equals(var6)) {
                  return var5;
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final BlockId x1) {
            if (x1 instanceof CacheId var4) {
               String var10000 = var4.sessionUUID();
               String var5 = this.sessionUUID$1;
               if (var10000 == null) {
                  if (var5 == null) {
                     return true;
                  }
               } else if (var10000.equals(var5)) {
                  return true;
               }
            }

            return false;
         }

         public {
            this.sessionUUID$1 = sessionUUID$1;
         }
      });
      blocksToRemove.foreach((blockId) -> {
         $anonfun$removeCache$3(this, blockId);
         return BoxedUnit.UNIT;
      });
      return blocksToRemove.size();
   }

   public void removeBlock(final BlockId blockId, final boolean tellMaster) {
      this.logDebug((Function0)(() -> "Removing block " + blockId));
      Option var4 = this.blockInfoManager().lockForWriting(blockId, this.blockInfoManager().lockForWriting$default$2());
      if (scala.None..MODULE$.equals(var4)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to remove block ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"which does not exist"})))).log(scala.collection.immutable.Nil..MODULE$))));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (!(var4 instanceof Some)) {
         throw new MatchError(var4);
      } else {
         Some var5 = (Some)var4;
         BlockInfo info = (BlockInfo)var5.value();
         this.removeBlockInternal(blockId, tellMaster && info.tellMaster());
         this.org$apache$spark$storage$BlockManager$$addUpdatedBlockStatusToTaskMetrics(blockId, BlockStatus$.MODULE$.empty());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public boolean removeBlock$default$2() {
      return true;
   }

   private void removeBlockInternal(final BlockId blockId, final boolean tellMaster) {
      boolean hasRemoveBlock = false;

      try {
         Object var10000;
         if (tellMaster) {
            BlockInfo blockInfo = this.blockInfoManager().assertBlockIsLockedForWriting(blockId);
            var10000 = new Some(this.org$apache$spark$storage$BlockManager$$getCurrentBlockStatus(blockId, blockInfo));
         } else {
            var10000 = scala.None..MODULE$;
         }

         Option blockStatus = (Option)var10000;
         boolean removedFromMemory = this.memoryStore().remove(blockId);
         boolean removedFromDisk = this.diskStore().remove(blockId);
         if (!removedFromMemory && !removedFromDisk) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"could not be removed as it was not found on disk or in memory"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         this.blockInfoManager().removeBlock(blockId);
         hasRemoveBlock = true;
         if (tellMaster) {
            StorageLevel storageLevel = org.apache.spark.storage.StorageLevel..MODULE$.apply(((BlockStatus)blockStatus.get()).storageLevel().toInt(), 0);
            BlockStatus qual$1 = (BlockStatus)blockStatus.get();
            long x$2 = qual$1.copy$default$2();
            long x$3 = qual$1.copy$default$3();
            this.reportBlockStatus(blockId, qual$1.copy(storageLevel, x$2, x$3), this.reportBlockStatus$default$3());
         }
      } finally {
         if (!hasRemoveBlock) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " was not removed normally."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
            this.blockInfoManager().removeBlock(blockId);
         }

      }

   }

   public void org$apache$spark$storage$BlockManager$$addUpdatedBlockStatusToTaskMetrics(final BlockId blockId, final BlockStatus status) {
      if (BoxesRunTime.unboxToBoolean(this.conf().get(package$.MODULE$.TASK_METRICS_TRACK_UPDATED_BLOCK_STATUSES()))) {
         .MODULE$.apply(TaskContext$.MODULE$.get()).foreach((c) -> {
            $anonfun$addUpdatedBlockStatusToTaskMetrics$1(blockId, status, c);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void releaseLockAndDispose(final BlockId blockId, final BlockData data, final Option taskContext) {
      this.releaseLock(blockId, taskContext);
      data.dispose();
   }

   public Option releaseLockAndDispose$default$3() {
      return scala.None..MODULE$;
   }

   public void stop() {
      this.decommissioner().foreach((x$26) -> {
         $anonfun$stop$1(x$26);
         return BoxedUnit.UNIT;
      });
      this.blockTransferService().close();
      if (this.blockStoreClient() != this.blockTransferService()) {
         this.blockStoreClient().close();
      }

      this.remoteBlockTempFileManager().stop();
      this.diskBlockManager().stop();
      this.rpcEnv.stop(this.storageEndpoint());
      this.blockInfoManager().clear();
      if (this.memoryManager() != null) {
         this.memoryStore().clear();
      }

      this.org$apache$spark$storage$BlockManager$$futureExecutionContext().shutdownNow();
      this.logInfo((Function0)(() -> "BlockManager stopped"));
   }

   private final void ByteBufferBlockStoreUpdater$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ByteBufferBlockStoreUpdater$module == null) {
            this.ByteBufferBlockStoreUpdater$module = new ByteBufferBlockStoreUpdater$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void TempFileBasedBlockStoreUpdater$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TempFileBasedBlockStoreUpdater$module == null) {
            this.TempFileBasedBlockStoreUpdater$module = new TempFileBasedBlockStoreUpdater$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$1(final String appId$1, final ExternalBlockStoreClient blockStoreClient) {
      blockStoreClient.init(appId$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reportAllBlocks$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$reportAllBlocks$3(final BlockManager $this, final Object nonLocalReturnKey2$1, final Tuple2 x$6) {
      if (x$6 != null) {
         BlockId blockId = (BlockId)x$6._1();
         BlockInfo info = (BlockInfo)x$6._2();
         BlockStatus status = $this.org$apache$spark$storage$BlockManager$$getCurrentBlockStatus(blockId, info);
         if (info.tellMaster() && !$this.tryToReportBlockStatus(blockId, status, $this.tryToReportBlockStatus$default$3())) {
            $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to report ", " to master; giving up."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
            throw new NonLocalReturnControl.mcV.sp(nonLocalReturnKey2$1, BoxedUnit.UNIT);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$preferExecutors$1(final BlockManager $this, final BlockManagerId x$10) {
      return x$10.port() != $this.externalShuffleServicePort();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sortLocations$1(final BlockManager $this, final BlockManagerId x$12) {
      boolean var3;
      label23: {
         String var10000 = x$12.host();
         String var2 = $this.blockManagerId().host();
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
   public static final boolean $anonfun$sortLocations$2(final BlockManager $this, final BlockManagerId loc) {
      boolean var3;
      label23: {
         Option var10000 = $this.blockManagerId().topologyInfo();
         Option var2 = loc.topologyInfo();
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
   public static final long $anonfun$releaseLock$1(final TaskContext x$17) {
      return x$17.taskAttemptId();
   }

   // $FF: synthetic method
   public static final void $anonfun$doPutIterator$3(final BlockManager $this, final BlockId blockId$17, final PartiallyUnrolledIterator iter$1, final ClassTag classTag$2, final WritableByteChannel channel) {
      OutputStream out = Channels.newOutputStream(channel);
      $this.serializerManager().dataSerializeStream(blockId$17, out, iter$1, classTag$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$doPutIterator$5(final PartiallySerializedBlock partiallySerializedValues$1, final WritableByteChannel channel) {
      OutputStream out = Channels.newOutputStream(channel);
      partiallySerializedValues$1.finishWritingToStream(out);
   }

   // $FF: synthetic method
   public static final void $anonfun$doPutIterator$6(final BlockManager $this, final BlockId blockId$17, final Function0 iterator$1, final ClassTag classTag$2, final WritableByteChannel channel) {
      OutputStream out = Channels.newOutputStream(channel);
      $this.serializerManager().dataSerializeStream(blockId$17, out, (Iterator)iterator$1.apply(), classTag$2);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$maybeCacheDiskBytesInMemory$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$maybeCacheDiskBytesInMemory$2(final int x$1) {
      return Platform.allocateDirectBuffer(x$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$getPeers$1(final BlockManagerId x$18) {
      return x$18.hashCode();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replicateBlock$2(final BlockManager $this, final BlockId blockId$18, final int maxReplicas$1, final Set existingReplicas$1, final Option maxReplicationFailures$1, final BlockInfo info) {
      BlockData data = $this.doGetLocalBytes(blockId$18, info);
      StorageLevel storageLevel = org.apache.spark.storage.StorageLevel..MODULE$.apply(info.level().useDisk(), info.level().useMemory(), info.level().useOffHeap(), info.level().deserialized(), maxReplicas$1);
      $this.getPeers(true);

      boolean var10000;
      try {
         var10000 = $this.org$apache$spark$storage$BlockManager$$replicate(blockId$18, data, storageLevel, info.classTag(), existingReplicas$1, maxReplicationFailures$1);
      } finally {
         $this.logDebug((Function0)(() -> "Releasing lock for " + blockId$18));
         $this.releaseLockAndDispose(blockId$18, data, $this.releaseLockAndDispose$default$3());
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replicate$2(final Set existingReplicas$2, final BlockManagerId elem) {
      return existingReplicas$2.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replicate$6(final HashSet peersFailedToReplicateTo$1, final HashSet peersReplicatedTo$1, final BlockManagerId p) {
      return !peersFailedToReplicateTo$1.contains(p) && !peersReplicatedTo$1.contains(p);
   }

   // $FF: synthetic method
   public static final void $anonfun$dropFromMemory$3(final BlockManager $this, final BlockId blockId$20, final Object elements$1, final BlockInfo info$1, final WritableByteChannel channel) {
      OutputStream out = Channels.newOutputStream(channel);
      $this.serializerManager().dataSerializeStream(blockId$20, out, scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(elements$1)), info$1.classTag());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeRdd$3(final int rddId$1, final RDDBlockId x$21) {
      return x$21.rddId() == rddId$1;
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$4(final BlockManager $this, final RDDBlockId blockId) {
      $this.removeBlock(blockId, false);
   }

   // $FF: synthetic method
   public static final void $anonfun$decommissionSelf$2(final BlockManagerDecommissioner x$22) {
      x$22.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$removeBroadcast$3(final BlockManager $this, final boolean tellMaster$2, final BroadcastBlockId blockId) {
      $this.removeBlock(blockId, tellMaster$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeCache$3(final BlockManager $this, final CacheId blockId) {
      $this.removeBlock(blockId, $this.removeBlock$default$2());
   }

   // $FF: synthetic method
   public static final void $anonfun$addUpdatedBlockStatusToTaskMetrics$1(final BlockId blockId$23, final BlockStatus status$1, final TaskContext c) {
      c.taskMetrics().incUpdatedBlockStatuses(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(blockId$23), status$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final BlockManagerDecommissioner x$26) {
      x$26.stop();
   }

   public BlockManager(final String executorId, final RpcEnv rpcEnv, final BlockManagerMaster master, final SerializerManager serializerManager, final SparkConf conf, final MemoryManager _memoryManager, final MapOutputTracker mapOutputTracker, final ShuffleManager _shuffleManager, final BlockTransferService blockTransferService, final SecurityManager securityManager, final Option externalBlockStoreClient) {
      boolean var10001;
      label26: {
         label25: {
            this.executorId = executorId;
            this.rpcEnv = rpcEnv;
            this.master = master;
            this.serializerManager = serializerManager;
            this.conf = conf;
            this._memoryManager = _memoryManager;
            this._shuffleManager = _shuffleManager;
            this.blockTransferService = blockTransferService;
            this.securityManager = securityManager;
            this.externalBlockStoreClient = externalBlockStoreClient;
            super();
            Logging.$init$(this);
            this.externalShuffleServiceEnabled = externalBlockStoreClient.isDefined();
            String var12 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (executorId == null) {
               if (var12 == null) {
                  break label25;
               }
            } else if (executorId.equals(var12)) {
               break label25;
            }

            var10001 = false;
            break label26;
         }

         var10001 = true;
      }

      this.isDriver = var10001;
      this.remoteReadNioBufferConversion = BoxesRunTime.unboxToBoolean(conf.get(Network$.MODULE$.NETWORK_REMOTE_READ_NIO_BUFFER_CONVERSION()));
      this.subDirsPerLocalDir = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.DISKSTORE_SUB_DIRECTORIES()));
      boolean deleteFilesOnStop = !this.externalShuffleServiceEnabled() || this.isDriver();
      this.diskBlockManager = new DiskBlockManager(conf, deleteFilesOnStop, this.isDriver());
      this.trackingCacheVisibility = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.RDD_CACHE_VISIBILITY_TRACKING_ENABLED()));
      this.blockInfoManager = new BlockInfoManager(this.trackingCacheVisibility());
      this.org$apache$spark$storage$BlockManager$$futureExecutionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(ThreadUtils$.MODULE$.newDaemonCachedThreadPool("block-manager-future", 128, ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3()));
      this.diskStore = new DiskStore(conf, this.diskBlockManager(), securityManager);
      this.externalShuffleServicePort = StorageUtils$.MODULE$.externalShuffleServicePort(conf);
      this.blockStoreClient = (BlockStoreClient)externalBlockStoreClient.getOrElse(() -> this.blockTransferService());
      this.maxFailuresBeforeLocationRefresh = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.BLOCK_FAILURES_BEFORE_LOCATION_REFRESH()));
      this.storageEndpoint = rpcEnv.setupEndpoint("BlockManagerEndpoint" + BlockManager$.MODULE$.org$apache$spark$storage$BlockManager$$ID_GENERATOR().next(), new BlockManagerStorageEndpoint(rpcEnv, this, mapOutputTracker));
      this.asyncReregisterTask = null;
      this.asyncReregisterLock = new Object();
      this.peerFetchLock = new Object();
      this.lastPeerFetchTimeNs = 0L;
      this.decommissioner = scala.None..MODULE$;
      this.remoteBlockTempFileManager = new RemoteBlockDownloadFileManager(this, securityManager.getIOEncryptionKey());
      this.maxRemoteBlockToMem = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM()));
      this.hostLocalDirManager = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class BlockStoreUpdater {
      private final long blockSize;
      private final BlockId blockId;
      private final StorageLevel level;
      private final ClassTag classTag;
      private final boolean tellMaster;
      private final boolean keepReadLock;
      // $FF: synthetic field
      public final BlockManager $outer;

      public abstract ChunkedByteBuffer readToByteBuffer();

      public abstract BlockData blockData();

      public abstract void saveToDiskStore();

      private boolean saveDeserializedValuesToMemoryStore(final InputStream inputStream) {
         try {
            Iterator values = this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().serializerManager().dataDeserializeStream(this.blockId, inputStream, this.classTag);
            Either var5 = this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().memoryStore().putIteratorAsValues(this.blockId, values, this.level.memoryMode(), this.classTag);
            if (var5 instanceof Right) {
               boolean var10000 = true;
            } else {
               if (!(var5 instanceof Left)) {
                  throw new MatchError(var5);
               }

               Left var6 = (Left)var5;
               PartiallyUnrolledIterator iter = (PartiallyUnrolledIterator)var6.value();
               iter.close();
               boolean var16 = false;
            }
         } catch (Throwable var14) {
            if (var14 instanceof KryoException var10) {
               if (var10.getCause() instanceof IOException) {
                  this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().logInfo((Function0)(() -> this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$extendMessageWithBlockDetails(var10.getMessage(), this.blockId)));
                  throw var10;
               }
            }

            throw var14;
         } finally {
            IOUtils.closeQuietly(inputStream);
         }

         return (boolean)inputStream;
      }

      private boolean saveSerializedValuesToMemoryStore(final ChunkedByteBuffer bytes) {
         MemoryMode memoryMode = this.level.memoryMode();
         return this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().memoryStore().putBytes(this.blockId, this.blockSize, memoryMode, () -> {
            MemoryMode var2 = MemoryMode.OFF_HEAP;
            if (memoryMode == null) {
               if (var2 != null) {
                  return bytes;
               }
            } else if (!memoryMode.equals(var2)) {
               return bytes;
            }

            if (scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps((Object[])bytes.chunks()), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$saveSerializedValuesToMemoryStore$2(x$5)))) {
               return bytes.copy((x$1) -> $anonfun$saveSerializedValuesToMemoryStore$3(BoxesRunTime.unboxToInt(x$1)));
            } else {
               return bytes;
            }
         }, scala.reflect.ClassTag..MODULE$.Nothing());
      }

      public boolean save() {
         return this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$doPut(this.blockId, this.level, this.classTag, this.tellMaster, this.keepReadLock, (info) -> {
            long startTimeNs = System.nanoTime();
            Future replicationFuture = this.level.replication() > 1 ? scala.concurrent.Future..MODULE$.apply((JFunction0.mcZ.sp)() -> this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$replicate(this.blockId, this.blockData(), this.level, this.classTag, this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$replicate$default$5(), this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$replicate$default$6()), this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$futureExecutionContext()) : null;
            if (this.level.useMemory()) {
               boolean putSucceeded = this.level.deserialized() ? this.saveDeserializedValuesToMemoryStore(this.blockData().toInputStream()) : this.saveSerializedValuesToMemoryStore(this.readToByteBuffer());
               if (!putSucceeded && this.level.useDisk()) {
                  this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting block ", " to disk instead."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, this.blockId)})))));
                  this.saveToDiskStore();
               }
            } else if (this.level.useDisk()) {
               this.saveToDiskStore();
            }

            BlockStatus putBlockStatus = this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$getCurrentBlockStatus(this.blockId, info);
            boolean blockWasSuccessfullyStored = putBlockStatus.storageLevel().isValid();
            if (blockWasSuccessfullyStored) {
               info.size_$eq(this.blockSize);
               if (this.tellMaster && info.tellMaster()) {
                  this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().reportBlockStatus(this.blockId, putBlockStatus, this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().reportBlockStatus$default$3());
               }

               this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().org$apache$spark$storage$BlockManager$$addUpdatedBlockStatusToTaskMetrics(this.blockId, putBlockStatus);
            }

            this.org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer().logDebug((Function0)(() -> {
               BlockId var10000 = this.blockId;
               return "Put block " + var10000 + " locally took " + Utils$.MODULE$.getUsedTimeNs(startTimeNs);
            }));
            if (this.level.replication() > 1) {
               try {
                  ThreadUtils$.MODULE$.awaitReady(replicationFuture, scala.concurrent.duration.Duration..MODULE$.Inf());
               } catch (Throwable var12) {
                  if (var12 != null && scala.util.control.NonFatal..MODULE$.apply(var12)) {
                     throw SparkCoreErrors$.MODULE$.waitingForReplicationToFinishError(var12);
                  }

                  throw var12;
               }
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return (Option)(blockWasSuccessfullyStored ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToLong(this.blockSize)));
         }).isEmpty();
      }

      // $FF: synthetic method
      public BlockManager org$apache$spark$storage$BlockManager$BlockStoreUpdater$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$saveSerializedValuesToMemoryStore$2(final ByteBuffer x$5) {
         return !x$5.isDirect();
      }

      // $FF: synthetic method
      public static final ByteBuffer $anonfun$saveSerializedValuesToMemoryStore$3(final int x$1) {
         return Platform.allocateDirectBuffer(x$1);
      }

      public BlockStoreUpdater(final long blockSize, final BlockId blockId, final StorageLevel level, final ClassTag classTag, final boolean tellMaster, final boolean keepReadLock) {
         this.blockSize = blockSize;
         this.blockId = blockId;
         this.level = level;
         this.classTag = classTag;
         this.tellMaster = tellMaster;
         this.keepReadLock = keepReadLock;
         if (BlockManager.this == null) {
            throw null;
         } else {
            this.$outer = BlockManager.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ByteBufferBlockStoreUpdater extends BlockStoreUpdater implements Product, Serializable {
      private final BlockId blockId;
      private final StorageLevel level;
      private final ClassTag classTag;
      private final ChunkedByteBuffer bytes;
      private final boolean tellMaster;
      private final boolean keepReadLock;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public BlockId blockId() {
         return this.blockId;
      }

      public StorageLevel level() {
         return this.level;
      }

      public ClassTag classTag() {
         return this.classTag;
      }

      public ChunkedByteBuffer bytes() {
         return this.bytes;
      }

      public boolean tellMaster() {
         return this.tellMaster;
      }

      public boolean keepReadLock() {
         return this.keepReadLock;
      }

      public ChunkedByteBuffer readToByteBuffer() {
         return this.bytes();
      }

      public BlockData blockData() {
         return new ByteBufferBlockData(this.bytes(), false);
      }

      public void saveToDiskStore() {
         this.org$apache$spark$storage$BlockManager$ByteBufferBlockStoreUpdater$$$outer().diskStore().putBytes(this.blockId(), this.bytes());
      }

      public ByteBufferBlockStoreUpdater copy(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final ChunkedByteBuffer bytes, final boolean tellMaster, final boolean keepReadLock) {
         return this.org$apache$spark$storage$BlockManager$ByteBufferBlockStoreUpdater$$$outer().new ByteBufferBlockStoreUpdater(blockId, level, classTag, bytes, tellMaster, keepReadLock);
      }

      public BlockId copy$default$1() {
         return this.blockId();
      }

      public StorageLevel copy$default$2() {
         return this.level();
      }

      public ClassTag copy$default$3() {
         return this.classTag();
      }

      public ChunkedByteBuffer copy$default$4() {
         return this.bytes();
      }

      public boolean copy$default$5() {
         return this.tellMaster();
      }

      public boolean copy$default$6() {
         return this.keepReadLock();
      }

      public String productPrefix() {
         return "ByteBufferBlockStoreUpdater";
      }

      public int productArity() {
         return 6;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.blockId();
            }
            case 1 -> {
               return this.level();
            }
            case 2 -> {
               return this.classTag();
            }
            case 3 -> {
               return this.bytes();
            }
            case 4 -> {
               return BoxesRunTime.boxToBoolean(this.tellMaster());
            }
            case 5 -> {
               return BoxesRunTime.boxToBoolean(this.keepReadLock());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ByteBufferBlockStoreUpdater;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "blockId";
            }
            case 1 -> {
               return "level";
            }
            case 2 -> {
               return "classTag";
            }
            case 3 -> {
               return "bytes";
            }
            case 4 -> {
               return "tellMaster";
            }
            case 5 -> {
               return "keepReadLock";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.blockId()));
         var1 = Statics.mix(var1, Statics.anyHash(this.level()));
         var1 = Statics.mix(var1, Statics.anyHash(this.classTag()));
         var1 = Statics.mix(var1, Statics.anyHash(this.bytes()));
         var1 = Statics.mix(var1, this.tellMaster() ? 1231 : 1237);
         var1 = Statics.mix(var1, this.keepReadLock() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 6);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var12;
         if (this != x$1) {
            label84: {
               if (x$1 instanceof ByteBufferBlockStoreUpdater && ((ByteBufferBlockStoreUpdater)x$1).org$apache$spark$storage$BlockManager$ByteBufferBlockStoreUpdater$$$outer() == this.org$apache$spark$storage$BlockManager$ByteBufferBlockStoreUpdater$$$outer()) {
                  ByteBufferBlockStoreUpdater var4 = (ByteBufferBlockStoreUpdater)x$1;
                  if (this.tellMaster() == var4.tellMaster() && this.keepReadLock() == var4.keepReadLock()) {
                     label74: {
                        BlockId var10000 = this.blockId();
                        BlockId var5 = var4.blockId();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label74;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label74;
                        }

                        StorageLevel var9 = this.level();
                        StorageLevel var6 = var4.level();
                        if (var9 == null) {
                           if (var6 != null) {
                              break label74;
                           }
                        } else if (!var9.equals(var6)) {
                           break label74;
                        }

                        ClassTag var10 = this.classTag();
                        ClassTag var7 = var4.classTag();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label74;
                           }
                        } else if (!var10.equals(var7)) {
                           break label74;
                        }

                        ChunkedByteBuffer var11 = this.bytes();
                        ChunkedByteBuffer var8 = var4.bytes();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label74;
                           }
                        } else if (!var11.equals(var8)) {
                           break label74;
                        }

                        if (var4.canEqual(this)) {
                           break label84;
                        }
                     }
                  }
               }

               var12 = false;
               return var12;
            }
         }

         var12 = true;
         return var12;
      }

      // $FF: synthetic method
      public BlockManager org$apache$spark$storage$BlockManager$ByteBufferBlockStoreUpdater$$$outer() {
         return this.$outer;
      }

      public ByteBufferBlockStoreUpdater(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final ChunkedByteBuffer bytes, final boolean tellMaster, final boolean keepReadLock) {
         super(bytes.size(), blockId, level, classTag, tellMaster, keepReadLock);
         this.blockId = blockId;
         this.level = level;
         this.classTag = classTag;
         this.bytes = bytes;
         this.tellMaster = tellMaster;
         this.keepReadLock = keepReadLock;
         Product.$init$(this);
      }
   }

   public class ByteBufferBlockStoreUpdater$ implements Serializable {
      // $FF: synthetic field
      private final BlockManager $outer;

      public boolean $lessinit$greater$default$5() {
         return true;
      }

      public boolean $lessinit$greater$default$6() {
         return false;
      }

      public final String toString() {
         return "ByteBufferBlockStoreUpdater";
      }

      public ByteBufferBlockStoreUpdater apply(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final ChunkedByteBuffer bytes, final boolean tellMaster, final boolean keepReadLock) {
         return this.$outer.new ByteBufferBlockStoreUpdater(blockId, level, classTag, bytes, tellMaster, keepReadLock);
      }

      public boolean apply$default$5() {
         return true;
      }

      public boolean apply$default$6() {
         return false;
      }

      public Option unapply(final ByteBufferBlockStoreUpdater x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple6(x$0.blockId(), x$0.level(), x$0.classTag(), x$0.bytes(), BoxesRunTime.boxToBoolean(x$0.tellMaster()), BoxesRunTime.boxToBoolean(x$0.keepReadLock()))));
      }

      public ByteBufferBlockStoreUpdater$() {
         if (BlockManager.this == null) {
            throw null;
         } else {
            this.$outer = BlockManager.this;
            super();
         }
      }
   }

   public class TempFileBasedBlockStoreUpdater extends BlockStoreUpdater implements Product, Serializable {
      private final BlockId blockId;
      private final StorageLevel level;
      private final ClassTag classTag;
      private final File tmpFile;
      private final long blockSize;
      private final boolean tellMaster;
      private final boolean keepReadLock;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public BlockId blockId() {
         return this.blockId;
      }

      public StorageLevel level() {
         return this.level;
      }

      public ClassTag classTag() {
         return this.classTag;
      }

      public File tmpFile() {
         return this.tmpFile;
      }

      public long blockSize() {
         return this.blockSize;
      }

      public boolean tellMaster() {
         return this.tellMaster;
      }

      public boolean keepReadLock() {
         return this.keepReadLock;
      }

      public ChunkedByteBuffer readToByteBuffer() {
         MemoryMode var3 = this.level().memoryMode();
         Function1 var10000;
         if (MemoryMode.ON_HEAP.equals(var3)) {
            var10000 = (x$1) -> $anonfun$readToByteBuffer$1(BoxesRunTime.unboxToInt(x$1));
         } else {
            if (!MemoryMode.OFF_HEAP.equals(var3)) {
               throw new MatchError(var3);
            }

            var10000 = (x$1) -> $anonfun$readToByteBuffer$2(BoxesRunTime.unboxToInt(x$1));
         }

         Function1 allocator = var10000;
         return this.blockData().toChunkedByteBuffer(allocator);
      }

      public BlockData blockData() {
         return this.org$apache$spark$storage$BlockManager$TempFileBasedBlockStoreUpdater$$$outer().diskStore().getBytes(this.tmpFile(), this.blockSize());
      }

      public void saveToDiskStore() {
         this.org$apache$spark$storage$BlockManager$TempFileBasedBlockStoreUpdater$$$outer().diskStore().moveFileToBlock(this.tmpFile(), this.blockSize(), this.blockId());
      }

      public boolean save() {
         boolean res = super.save();
         this.tmpFile().delete();
         return res;
      }

      public TempFileBasedBlockStoreUpdater copy(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final File tmpFile, final long blockSize, final boolean tellMaster, final boolean keepReadLock) {
         return this.org$apache$spark$storage$BlockManager$TempFileBasedBlockStoreUpdater$$$outer().new TempFileBasedBlockStoreUpdater(blockId, level, classTag, tmpFile, blockSize, tellMaster, keepReadLock);
      }

      public BlockId copy$default$1() {
         return this.blockId();
      }

      public StorageLevel copy$default$2() {
         return this.level();
      }

      public ClassTag copy$default$3() {
         return this.classTag();
      }

      public File copy$default$4() {
         return this.tmpFile();
      }

      public long copy$default$5() {
         return this.blockSize();
      }

      public boolean copy$default$6() {
         return this.tellMaster();
      }

      public boolean copy$default$7() {
         return this.keepReadLock();
      }

      public String productPrefix() {
         return "TempFileBasedBlockStoreUpdater";
      }

      public int productArity() {
         return 7;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.blockId();
            }
            case 1 -> {
               return this.level();
            }
            case 2 -> {
               return this.classTag();
            }
            case 3 -> {
               return this.tmpFile();
            }
            case 4 -> {
               return BoxesRunTime.boxToLong(this.blockSize());
            }
            case 5 -> {
               return BoxesRunTime.boxToBoolean(this.tellMaster());
            }
            case 6 -> {
               return BoxesRunTime.boxToBoolean(this.keepReadLock());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof TempFileBasedBlockStoreUpdater;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "blockId";
            }
            case 1 -> {
               return "level";
            }
            case 2 -> {
               return "classTag";
            }
            case 3 -> {
               return "tmpFile";
            }
            case 4 -> {
               return "blockSize";
            }
            case 5 -> {
               return "tellMaster";
            }
            case 6 -> {
               return "keepReadLock";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.blockId()));
         var1 = Statics.mix(var1, Statics.anyHash(this.level()));
         var1 = Statics.mix(var1, Statics.anyHash(this.classTag()));
         var1 = Statics.mix(var1, Statics.anyHash(this.tmpFile()));
         var1 = Statics.mix(var1, Statics.longHash(this.blockSize()));
         var1 = Statics.mix(var1, this.tellMaster() ? 1231 : 1237);
         var1 = Statics.mix(var1, this.keepReadLock() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 7);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var12;
         if (this != x$1) {
            label88: {
               if (x$1 instanceof TempFileBasedBlockStoreUpdater && ((TempFileBasedBlockStoreUpdater)x$1).org$apache$spark$storage$BlockManager$TempFileBasedBlockStoreUpdater$$$outer() == this.org$apache$spark$storage$BlockManager$TempFileBasedBlockStoreUpdater$$$outer()) {
                  TempFileBasedBlockStoreUpdater var4 = (TempFileBasedBlockStoreUpdater)x$1;
                  if (this.blockSize() == var4.blockSize() && this.tellMaster() == var4.tellMaster() && this.keepReadLock() == var4.keepReadLock()) {
                     label78: {
                        BlockId var10000 = this.blockId();
                        BlockId var5 = var4.blockId();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label78;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label78;
                        }

                        StorageLevel var9 = this.level();
                        StorageLevel var6 = var4.level();
                        if (var9 == null) {
                           if (var6 != null) {
                              break label78;
                           }
                        } else if (!var9.equals(var6)) {
                           break label78;
                        }

                        ClassTag var10 = this.classTag();
                        ClassTag var7 = var4.classTag();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label78;
                           }
                        } else if (!var10.equals(var7)) {
                           break label78;
                        }

                        File var11 = this.tmpFile();
                        File var8 = var4.tmpFile();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label78;
                           }
                        } else if (!var11.equals(var8)) {
                           break label78;
                        }

                        if (var4.canEqual(this)) {
                           break label88;
                        }
                     }
                  }
               }

               var12 = false;
               return var12;
            }
         }

         var12 = true;
         return var12;
      }

      // $FF: synthetic method
      public BlockManager org$apache$spark$storage$BlockManager$TempFileBasedBlockStoreUpdater$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final ByteBuffer $anonfun$readToByteBuffer$1(final int x$1) {
         return ByteBuffer.allocate(x$1);
      }

      // $FF: synthetic method
      public static final ByteBuffer $anonfun$readToByteBuffer$2(final int x$1) {
         return Platform.allocateDirectBuffer(x$1);
      }

      public TempFileBasedBlockStoreUpdater(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final File tmpFile, final long blockSize, final boolean tellMaster, final boolean keepReadLock) {
         super(blockSize, blockId, level, classTag, tellMaster, keepReadLock);
         this.blockId = blockId;
         this.level = level;
         this.classTag = classTag;
         this.tmpFile = tmpFile;
         this.blockSize = blockSize;
         this.tellMaster = tellMaster;
         this.keepReadLock = keepReadLock;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class TempFileBasedBlockStoreUpdater$ implements Serializable {
      // $FF: synthetic field
      private final BlockManager $outer;

      public boolean $lessinit$greater$default$6() {
         return true;
      }

      public boolean $lessinit$greater$default$7() {
         return false;
      }

      public final String toString() {
         return "TempFileBasedBlockStoreUpdater";
      }

      public TempFileBasedBlockStoreUpdater apply(final BlockId blockId, final StorageLevel level, final ClassTag classTag, final File tmpFile, final long blockSize, final boolean tellMaster, final boolean keepReadLock) {
         return this.$outer.new TempFileBasedBlockStoreUpdater(blockId, level, classTag, tmpFile, blockSize, tellMaster, keepReadLock);
      }

      public boolean apply$default$6() {
         return true;
      }

      public boolean apply$default$7() {
         return false;
      }

      public Option unapply(final TempFileBasedBlockStoreUpdater x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(x$0.blockId(), x$0.level(), x$0.classTag(), x$0.tmpFile(), BoxesRunTime.boxToLong(x$0.blockSize()), BoxesRunTime.boxToBoolean(x$0.tellMaster()), BoxesRunTime.boxToBoolean(x$0.keepReadLock()))));
      }

      public TempFileBasedBlockStoreUpdater$() {
         if (BlockManager.this == null) {
            throw null;
         } else {
            this.$outer = BlockManager.this;
            super();
         }
      }
   }

   private static class ShuffleMetricsSource implements Source {
      private final String sourceName;
      private final MetricRegistry metricRegistry;

      public String sourceName() {
         return this.sourceName;
      }

      public MetricRegistry metricRegistry() {
         return this.metricRegistry;
      }

      public ShuffleMetricsSource(final String sourceName, final MetricSet metricSet) {
         this.sourceName = sourceName;
         this.metricRegistry = new MetricRegistry();
         this.metricRegistry().registerAll(metricSet);
      }
   }

   public static class RemoteBlockDownloadFileManager implements DownloadFileManager, Logging {
      private final BlockManager blockManager;
      private final Option encryptionKey;
      private final ReferenceQueue referenceQueue;
      private final java.util.Set referenceBuffer;
      private final int POLL_TIMEOUT;
      private final Thread cleaningThread;
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

      private ReferenceQueue referenceQueue() {
         return this.referenceQueue;
      }

      private java.util.Set referenceBuffer() {
         return this.referenceBuffer;
      }

      private int POLL_TIMEOUT() {
         return this.POLL_TIMEOUT;
      }

      private Thread cleaningThread() {
         return this.cleaningThread;
      }

      public DownloadFile createTempFile(final TransportConf transportConf) {
         File file = (File)this.blockManager.diskBlockManager().createTempLocalBlock()._2();
         Option var4 = this.encryptionKey;
         if (var4 instanceof Some var5) {
            byte[] key = (byte[])var5.value();
            return new EncryptedDownloadFile(file, key);
         } else if (scala.None..MODULE$.equals(var4)) {
            return new SimpleDownloadFile(file, transportConf);
         } else {
            throw new MatchError(var4);
         }
      }

      public boolean registerTempFileToClean(final DownloadFile file) {
         return this.referenceBuffer().add(new ReferenceWithCleanup(file, this.referenceQueue()));
      }

      public void stop() {
         this.cleaningThread().interrupt();
         this.cleaningThread().join();
      }

      public void org$apache$spark$storage$BlockManager$RemoteBlockDownloadFileManager$$keepCleaning() {
         boolean running = true;

         while(running) {
            try {
               .MODULE$.apply(this.referenceQueue().remove((long)this.POLL_TIMEOUT())).map((x$27) -> (ReferenceWithCleanup)x$27).foreach((ref) -> {
                  $anonfun$keepCleaning$2(this, ref);
                  return BoxedUnit.UNIT;
               });
            } catch (Throwable var6) {
               if (var6 instanceof InterruptedException) {
                  running = false;
                  BoxedUnit var7 = BoxedUnit.UNIT;
               } else {
                  if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
                     throw var6;
                  }

                  this.logError((Function0)(() -> "Error in cleaning thread"), var6);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$keepCleaning$2(final RemoteBlockDownloadFileManager $this, final ReferenceWithCleanup ref) {
         $this.referenceBuffer().remove(ref);
         ref.cleanUp();
      }

      public RemoteBlockDownloadFileManager(final BlockManager blockManager, final Option encryptionKey) {
         this.blockManager = blockManager;
         this.encryptionKey = encryptionKey;
         Logging.$init$(this);
         this.referenceQueue = new ReferenceQueue();
         this.referenceBuffer = Collections.newSetFromMap(new ConcurrentHashMap());
         this.POLL_TIMEOUT = 1000;
         this.cleaningThread = new Thread() {
            // $FF: synthetic field
            private final RemoteBlockDownloadFileManager $outer;

            public void run() {
               this.$outer.org$apache$spark$storage$BlockManager$RemoteBlockDownloadFileManager$$keepCleaning();
            }

            public {
               if (RemoteBlockDownloadFileManager.this == null) {
                  throw null;
               } else {
                  this.$outer = RemoteBlockDownloadFileManager.this;
               }
            }
         };
         this.cleaningThread().setDaemon(true);
         this.cleaningThread().setName("RemoteBlock-temp-file-clean-thread");
         this.cleaningThread().start();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class ReferenceWithCleanup extends WeakReference {
         private final String filePath;
         // $FF: synthetic field
         public final RemoteBlockDownloadFileManager $outer;

         public String filePath() {
            return this.filePath;
         }

         public void cleanUp() {
            this.org$apache$spark$storage$BlockManager$RemoteBlockDownloadFileManager$ReferenceWithCleanup$$$outer().logDebug((Function0)(() -> "Clean up file " + this.filePath()));
            if (!(new File(this.filePath())).delete()) {
               this.org$apache$spark$storage$BlockManager$RemoteBlockDownloadFileManager$ReferenceWithCleanup$$$outer().logDebug((Function0)(() -> "Fail to delete file " + this.filePath()));
            }
         }

         // $FF: synthetic method
         public RemoteBlockDownloadFileManager org$apache$spark$storage$BlockManager$RemoteBlockDownloadFileManager$ReferenceWithCleanup$$$outer() {
            return this.$outer;
         }

         public ReferenceWithCleanup(final DownloadFile file, final ReferenceQueue referenceQueue) {
            if (RemoteBlockDownloadFileManager.this == null) {
               throw null;
            } else {
               this.$outer = RemoteBlockDownloadFileManager.this;
               super(file, referenceQueue);
               this.filePath = file.path();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }
   }

   private static class EncryptedDownloadFile implements DownloadFile {
      public final File org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$file;
      public final byte[] org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$key;
      private final SparkEnv org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$env;

      public SparkEnv org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$env() {
         return this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$env;
      }

      public boolean delete() {
         return this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$file.delete();
      }

      public DownloadFileWritableChannel openForWriting() {
         return new EncryptedDownloadWritableChannel();
      }

      public String path() {
         return this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$file.getAbsolutePath();
      }

      public EncryptedDownloadFile(final File file, final byte[] key) {
         this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$file = file;
         this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$key = key;
         this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$env = SparkEnv$.MODULE$.get();
      }

      private class EncryptedDownloadWritableChannel implements DownloadFileWritableChannel {
         private final CountingWritableChannel countingOutput;
         // $FF: synthetic field
         public final EncryptedDownloadFile $outer;

         private CountingWritableChannel countingOutput() {
            return this.countingOutput;
         }

         public ManagedBuffer closeAndRead() {
            this.countingOutput().close();
            long size = this.countingOutput().getCount();
            return new EncryptedManagedBuffer(new EncryptedBlockData(this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$EncryptedDownloadWritableChannel$$$outer().org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$file, size, this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$EncryptedDownloadWritableChannel$$$outer().org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$env().conf(), this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$EncryptedDownloadWritableChannel$$$outer().org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$key));
         }

         public int write(final ByteBuffer src) {
            return this.countingOutput().write(src);
         }

         public boolean isOpen() {
            return this.countingOutput().isOpen();
         }

         public void close() {
            this.countingOutput().close();
         }

         // $FF: synthetic method
         public EncryptedDownloadFile org$apache$spark$storage$BlockManager$EncryptedDownloadFile$EncryptedDownloadWritableChannel$$$outer() {
            return this.$outer;
         }

         public EncryptedDownloadWritableChannel() {
            if (EncryptedDownloadFile.this == null) {
               throw null;
            } else {
               this.$outer = EncryptedDownloadFile.this;
               super();
               this.countingOutput = new CountingWritableChannel(Channels.newChannel(EncryptedDownloadFile.this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$env().serializerManager().wrapForEncryption((OutputStream)(new FileOutputStream(EncryptedDownloadFile.this.org$apache$spark$storage$BlockManager$EncryptedDownloadFile$$file)))));
            }
         }
      }
   }
}
