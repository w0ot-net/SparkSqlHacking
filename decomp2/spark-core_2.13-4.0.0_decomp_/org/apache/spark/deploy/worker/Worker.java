package org.apache.spark.deploy.worker;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.ApplicationDescription;
import org.apache.spark.deploy.Command;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.DriverDescription;
import org.apache.spark.deploy.ExecutorDescription;
import org.apache.spark.deploy.ExecutorState$;
import org.apache.spark.deploy.ExternalShuffleService;
import org.apache.spark.deploy.StandaloneResourceUtils;
import org.apache.spark.deploy.master.DriverState$;
import org.apache.spark.deploy.master.Master$;
import org.apache.spark.deploy.worker.ui.WorkerWebUI;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.metrics.MetricsSystem$;
import org.apache.spark.metrics.MetricsSystemInstances$;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointAddress;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.SignalUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.LinkedHashMap;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.ExecutionContext.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Random;
import scala.util.Success;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019-d!CA@\u0003\u0003\u0003\u0011QQAK\u0011)\tY\f\u0001BC\u0002\u0013\u0005\u0013q\u0018\u0005\u000b\u0003\u000f\u0004!\u0011!Q\u0001\n\u0005\u0005\u0007BCAe\u0001\t\u0005\t\u0015!\u0003\u0002L\"Q\u0011\u0011\u001b\u0001\u0003\u0002\u0003\u0006I!a3\t\u0015\u0005M\u0007A!A!\u0002\u0013\tY\r\u0003\u0006\u0002V\u0002\u0011\t\u0011)A\u0005\u0003/D!\"a9\u0001\u0005\u0003\u0005\u000b\u0011BAs\u0011)\tY\u0010\u0001B\u0001B\u0003%\u0011Q\u001d\u0005\u000b\u0003{\u0004!Q1A\u0005\u0002\u0005}\bB\u0003B\u0005\u0001\t\u0005\t\u0015!\u0003\u0003\u0002!Q!1\u0002\u0001\u0003\u0006\u0004%\tA!\u0004\t\u0015\tU\u0001A!A!\u0002\u0013\u0011y\u0001\u0003\u0006\u0003\u0018\u0001\u0011\t\u0011)A\u0005\u00053A!Ba\b\u0001\u0005\u0003\u0005\u000b\u0011\u0002B\u0011\u0011\u001d\u0011i\u0004\u0001C\u0001\u0005\u007fA\u0011Ba\u0017\u0001\u0005\u0004%IA!\u0018\t\u0011\t}\u0003\u0001)A\u0005\u0003KD\u0011B!\u0019\u0001\u0005\u0004%IAa\u0019\t\u0011\t\u0015\u0004\u0001)A\u0005\u0003\u0017D\u0011Ba\u001a\u0001\u0005\u0004%IA!\u0018\t\u0011\t%\u0004\u0001)A\u0005\u0003KD\u0011Ba\u001b\u0001\u0005\u0004%IA!\u001c\t\u0011\tm\u0004\u0001)A\u0005\u0005_B\u0011B! \u0001\u0005\u0004%IAa \t\u0011\t-\u0005\u0001)A\u0005\u0005\u0003C\u0011B!$\u0001\u0005\u0004%IAa$\t\u0011\t]\u0005\u0001)A\u0005\u0005#C\u0011B!'\u0001\u0005\u0004%IAa\u0019\t\u0011\tm\u0005\u0001)A\u0005\u0003\u0017D\u0011B!(\u0001\u0005\u0004%IAa\u0019\t\u0011\t}\u0005\u0001)A\u0005\u0003\u0017D\u0011B!)\u0001\u0005\u0004%IAa)\t\u0011\t-\u0006\u0001)A\u0005\u0005KC\u0011B!,\u0001\u0005\u0004%IAa)\t\u0011\t=\u0006\u0001)A\u0005\u0005KC\u0011B!-\u0001\u0005\u0004%IAa$\t\u0011\tM\u0006\u0001)A\u0005\u0005#C\u0011B!.\u0001\u0005\u0004%IAa$\t\u0011\t]\u0006\u0001)A\u0005\u0005#C\u0011B!/\u0001\u0005\u0004%IAa/\t\u0011\t\r\u0007\u0001)A\u0005\u0005{C\u0011B!2\u0001\u0005\u0004%IAa$\t\u0011\t\u001d\u0007\u0001)A\u0005\u0005#C\u0011B!3\u0001\u0005\u0004%IAa$\t\u0011\t-\u0007\u0001)A\u0005\u0005#C\u0011B!4\u0001\u0005\u0004%IAa/\t\u0011\t=\u0007\u0001)A\u0005\u0005{C\u0011B!5\u0001\u0001\u0004%IAa5\t\u0013\tu\u0007\u00011A\u0005\n\t}\u0007\u0002\u0003Bv\u0001\u0001\u0006KA!6\t\u0013\t5\bA1A\u0005\n\tm\u0006\u0002\u0003Bx\u0001\u0001\u0006IA!0\t\u0013\tE\b\u00011A\u0005\n\tM\b\"\u0003B|\u0001\u0001\u0007I\u0011\u0002B}\u0011!\u0011i\u0010\u0001Q!\n\tU\b\"\u0003B\u0000\u0001\u0001\u0007I\u0011\u0002B/\u0011%\u0019\t\u0001\u0001a\u0001\n\u0013\u0019\u0019\u0001\u0003\u0005\u0004\b\u0001\u0001\u000b\u0015BAs\u0011-\u0019I\u0001\u0001a\u0001\n\u0003\t\tI!\u0018\t\u0017\r-\u0001\u00011A\u0005\u0002\u0005\u00055Q\u0002\u0005\t\u0007#\u0001\u0001\u0015)\u0003\u0002f\"I11\u0003\u0001A\u0002\u0013%!Q\f\u0005\n\u0007+\u0001\u0001\u0019!C\u0005\u0007/A\u0001ba\u0007\u0001A\u0003&\u0011Q\u001d\u0005\n\u0007;\u0001!\u0019!C\u0005\u0007?A\u0001ba\u000b\u0001A\u0003%1\u0011\u0005\u0005\n\u0007[\u0001\u0001\u0019!C\u0005\u0005wC\u0011ba\f\u0001\u0001\u0004%Ia!\r\t\u0011\rU\u0002\u0001)Q\u0005\u0005{C\u0011ba\u000e\u0001\u0001\u0004%IAa/\t\u0013\re\u0002\u00011A\u0005\n\rm\u0002\u0002CB \u0001\u0001\u0006KA!0\t\u0013\r\u0005\u0003\u00011A\u0005\n\tm\u0006\"CB\"\u0001\u0001\u0007I\u0011BB#\u0011!\u0019I\u0005\u0001Q!\n\tu\u0006bCB&\u0001\t\u0007I\u0011AAE\u0005;B\u0001b!\u0014\u0001A\u0003%\u0011Q\u001d\u0005\n\u0007\u001f\u0002!\u0019!C\u0005\u0007#B\u0001ba\u0018\u0001A\u0003%11\u000b\u0005\n\u0007C\u0002\u0001\u0019!C\u0001\u0007#B\u0011ba\u0019\u0001\u0001\u0004%\ta!\u001a\t\u0011\r%\u0004\u0001)Q\u0005\u0007'B\u0011ba\u001b\u0001\u0005\u0004%\ta!\u001c\t\u0011\r\u0015\u0005\u0001)A\u0005\u0007_B\u0011ba\"\u0001\u0005\u0004%\ta!#\t\u0011\r]\u0005\u0001)A\u0005\u0007\u0017C\u0011b!'\u0001\u0005\u0004%\taa'\t\u0011\r}\u0005\u0001)A\u0005\u0007;C\u0011b!)\u0001\u0005\u0004%\taa)\t\u0011\r\u001d\u0006\u0001)A\u0005\u0007KC\u0011b!+\u0001\u0005\u0004%\taa+\t\u0011\r\u0005\u0007\u0001)A\u0005\u0007[C\u0011ba1\u0001\u0005\u0004%\ta!2\t\u0011\r5\u0007\u0001)A\u0005\u0007\u000fD\u0011ba4\u0001\u0005\u0004%Ia!5\t\u0011\rU\u0007\u0001)A\u0005\u0007'D!ba6\u0001\u0011\u000b\u0007I\u0011BBm\u0011%\u0019\t\u000f\u0001b\u0001\n\u0013\u0011\u0019\u0007\u0003\u0005\u0004d\u0002\u0001\u000b\u0011BAf\u0011%\u0019)\u000f\u0001b\u0001\n\u0013\u0011y\t\u0003\u0005\u0004h\u0002\u0001\u000b\u0011\u0002BI\u0011%\u0019I\u000f\u0001b\u0001\n\u0003\u0011\u0019\u0007\u0003\u0005\u0004l\u0002\u0001\u000b\u0011BAf\u0011%\u0019i\u000f\u0001b\u0001\n\u0003\u0011\u0019\u0007\u0003\u0005\u0004p\u0002\u0001\u000b\u0011BAf\u0011%\u0019\t\u0010\u0001b\u0001\n\u0013\u0019\u0019\u0010\u0003\u0005\u0004v\u0002\u0001\u000b\u0011\u0002B\u001b\u0011%\u00199\u0010\u0001b\u0001\n\u0013\u0011i\u0006\u0003\u0005\u0004z\u0002\u0001\u000b\u0011BAs\u0011%\u0019Y\u0010\u0001a\u0001\n\u0013\u0019i\u0010C\u0005\u0005\f\u0001\u0001\r\u0011\"\u0003\u0005\u000e!AA\u0011\u0003\u0001!B\u0013\u0019y\u0010C\u0005\u0005\u0014\u0001\u0001\r\u0011\"\u0003\u0003d!IAQ\u0003\u0001A\u0002\u0013%Aq\u0003\u0005\t\t7\u0001\u0001\u0015)\u0003\u0002L\"IAQ\u0004\u0001C\u0002\u0013%Aq\u0004\u0005\t\t[\u0001\u0001\u0015!\u0003\u0005\"!IAq\u0006\u0001C\u0002\u0013%A\u0011\u0007\u0005\t\ts\u0001\u0001\u0015!\u0003\u00054!IA1\b\u0001C\u0002\u0013\u0005!1\u0018\u0005\t\t{\u0001\u0001\u0015!\u0003\u0003>\"IAq\b\u0001A\u0002\u0013%A\u0011\t\u0005\n\tK\u0002\u0001\u0019!C\u0005\tOB\u0001\u0002\"\u0016\u0001A\u0003&A1\t\u0005\n\tk\u0002\u0001\u0019!C\u0005\toB\u0011\u0002b#\u0001\u0001\u0004%I\u0001\"$\t\u0011\u0011%\u0005\u0001)Q\u0005\tsB\u0011\u0002b'\u0001\u0005\u0004%I\u0001\"(\t\u0011\u0011\u0015\u0006\u0001)A\u0005\t?C1\u0002b*\u0001\u0001\u0004%\t!!\"\u0005*\"YAQ\u0018\u0001A\u0002\u0013\u0005\u0011Q\u0011C`\u0011!!\u0019\r\u0001Q!\n\u0011-\u0006\"\u0003Cc\u0001\u0001\u0007I\u0011\u0001B2\u0011%!9\r\u0001a\u0001\n\u0003!I\r\u0003\u0005\u0005N\u0002\u0001\u000b\u0015BAf\u0011%!y\r\u0001a\u0001\n\u0003\u0011\u0019\u0007C\u0005\u0005R\u0002\u0001\r\u0011\"\u0001\u0005T\"AAq\u001b\u0001!B\u0013\tY\rC\u0005\u0005Z\u0002\u0011\r\u0011\"\u0001\u0005\\\"AAQ \u0001!\u0002\u0013!i\u000eC\u0004\u0005\u0000\u0002!\tAa\u0019\t\u000f\u0015\u0005\u0001\u0001\"\u0001\u0003d!9Q1\u0001\u0001\u0005\n\u0015\u0015\u0001bBC\u0004\u0001\u0011\u0005SQ\u0001\u0005\b\u000b\u0013\u0001A\u0011BC\u0003\u0011\u001d)Y\u0001\u0001C\u0005\u000b\u001bAq!b\u0005\u0001\t\u0013))\u0002C\u0004\u0006\u001a\u0001!I!b\u0007\t\u000f\u0015%\u0002\u0001\"\u0003\u0006,!9Q\u0011\b\u0001\u0005\n\u0015\u0015\u0001bBC\u001e\u0001\u0011%QQ\u0001\u0005\b\u000b{\u0001A\u0011BC\u0003\u0011\u001d)y\u0004\u0001C\u0005\u000b\u000bAq!\"\u0011\u0001\t\u0013)\u0019\u0005C\u0004\u0006J\u0001!I!b\u0013\t\u000f\u0015}\u0003\u0001\"\u0011\u0006b!9Q\u0011\u000e\u0001\u0005B\u0015-\u0004bBC<\u0001\u0011\u0005S\u0011\u0010\u0005\b\u000b\u007f\u0002A\u0011BC\u0003\u0011\u001d)\t\t\u0001C\u0005\u000b\u0007Cq!\"#\u0001\t\u0013)Y\tC\u0004\u0006\u0012\u0002!I!b%\t\u000f\u0015}\u0005\u0001\"\u0003\u0006\"\"9Q1\u0015\u0001\u0005B\u0015\u0015\u0001bBCS\u0001\u0011%QQ\u0001\u0005\b\u000bO\u0003A\u0011BC\u0003\u0011%)I\u000b\u0001C\u0001\u0003\u000b+)\u0001C\u0005\u0006,\u0002!\t!!!\u0006.\"IQ\u0011\u0018\u0001\u0005\u0002\u0005\u0005U1X\u0004\u000b\u000b\u0003\f\t\t#\u0001\u0002\u0006\u0016\rgACA@\u0003\u0003C\t!!\"\u0006F\"A!QHA,\t\u0003)9\r\u0003\u0006\u0006J\u0006]#\u0019!C\u0001\u0007?A\u0011\"b3\u0002X\u0001\u0006Ia!\t\t\u0015\u00155\u0017q\u000bb\u0001\n\u0003\u0019y\u0002C\u0005\u0006P\u0006]\u0003\u0015!\u0003\u0004\"!QQ\u0011[A,\u0005\u0004%I!b5\t\u0013\u0015\r\u0018q\u000bQ\u0001\n\u0015U\u0007BCCs\u0003/\u0012\r\u0011\"\u0003\u0006h\"IQ\u0011`A,A\u0003%Q\u0011\u001e\u0005\t\u000bw\f9\u0006\"\u0001\u0006~\"AaQAA,\t\u000319\u0001\u0003\u0006\u0007$\u0005]\u0013\u0013!C\u0001\rKA!Bb\u000f\u0002XE\u0005I\u0011\u0001D\u001f\u0011)1\t%a\u0016\u0012\u0002\u0013\u0005a1\t\u0005\t\r\u000f\n9\u0006\"\u0001\u0007J!AaQKA,\t\u000319\u0006\u0003\u0006\u0007^\u0005]\u0013\u0013!C\u0001\r?B!Bb\u0019\u0002XE\u0005I\u0011\u0001D\"\u0011)1)'a\u0016\u0012\u0002\u0013\u0005aq\r\u0002\u0007/>\u00148.\u001a:\u000b\t\u0005\r\u0015QQ\u0001\u0007o>\u00148.\u001a:\u000b\t\u0005\u001d\u0015\u0011R\u0001\u0007I\u0016\u0004Hn\\=\u000b\t\u0005-\u0015QR\u0001\u0006gB\f'o\u001b\u0006\u0005\u0003\u001f\u000b\t*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0003\u0003'\u000b1a\u001c:h'\u001d\u0001\u0011qSAR\u0003_\u0003B!!'\u0002 6\u0011\u00111\u0014\u0006\u0003\u0003;\u000bQa]2bY\u0006LA!!)\u0002\u001c\n1\u0011I\\=SK\u001a\u0004B!!*\u0002,6\u0011\u0011q\u0015\u0006\u0005\u0003S\u000bI)A\u0002sa\u000eLA!!,\u0002(\n)B\u000b\u001b:fC\u0012\u001c\u0016MZ3Sa\u000e,e\u000e\u001a9pS:$\b\u0003BAY\u0003ok!!a-\u000b\t\u0005U\u0016\u0011R\u0001\tS:$XM\u001d8bY&!\u0011\u0011XAZ\u0005\u001daunZ4j]\u001e\faA\u001d9d\u000b:48\u0001A\u000b\u0003\u0003\u0003\u0004B!!*\u0002D&!\u0011QYAT\u0005\u0019\u0011\u0006oY#om\u00069!\u000f]2F]Z\u0004\u0013!C<fEVK\u0007k\u001c:u!\u0011\tI*!4\n\t\u0005=\u00171\u0014\u0002\u0004\u0013:$\u0018!B2pe\u0016\u001c\u0018AB7f[>\u0014\u00180\u0001\nnCN$XM\u001d*qG\u0006#GM]3tg\u0016\u001c\bCBAM\u00033\fi.\u0003\u0003\u0002\\\u0006m%!B!se\u0006L\b\u0003BAS\u0003?LA!!9\u0002(\nQ!\u000b]2BI\u0012\u0014Xm]:\u0002\u0019\u0015tG\r]8j]Rt\u0015-\\3\u0011\t\u0005\u001d\u0018Q\u001f\b\u0005\u0003S\f\t\u0010\u0005\u0003\u0002l\u0006mUBAAw\u0015\u0011\ty/!0\u0002\rq\u0012xn\u001c;?\u0013\u0011\t\u00190a'\u0002\rA\u0013X\rZ3g\u0013\u0011\t90!?\u0003\rM#(/\u001b8h\u0015\u0011\t\u00190a'\u0002\u0017]|'o\u001b#jeB\u000bG\u000f[\u0001\u0005G>tg-\u0006\u0002\u0003\u0002A!!1\u0001B\u0003\u001b\t\tI)\u0003\u0003\u0003\b\u0005%%!C*qCJ\\7i\u001c8g\u0003\u0015\u0019wN\u001c4!\u0003-\u0019XmY;sSRLXj\u001a:\u0016\u0005\t=\u0001\u0003\u0002B\u0002\u0005#IAAa\u0005\u0002\n\ny1+Z2ve&$\u00180T1oC\u001e,'/\u0001\u0007tK\u000e,(/\u001b;z\u001b\u001e\u0014\b%A\bsKN|WO]2f\r&dWm\u00149u!\u0019\tIJa\u0007\u0002f&!!QDAN\u0005\u0019y\u0005\u000f^5p]\u0006qR\r\u001f;fe:\fGn\u00155vM\u001adWmU3sm&\u001cWmU;qa2LWM\u001d\t\u0007\u0005G\u0011\tD!\u000e\u000e\u0005\t\u0015\"\u0002\u0002B\u0014\u0005S\t\u0001BZ;oGRLwN\u001c\u0006\u0005\u0005W\u0011i#\u0001\u0003vi&d'B\u0001B\u0018\u0003\u0011Q\u0017M^1\n\t\tM\"Q\u0005\u0002\t'V\u0004\b\u000f\\5feB!!q\u0007B\u001d\u001b\t\t))\u0003\u0003\u0003<\u0005\u0015%AF#yi\u0016\u0014h.\u00197TQV4g\r\\3TKJ4\u0018nY3\u0002\rqJg.\u001b;?)a\u0011\tE!\u0012\u0003H\t%#1\nB'\u0005\u001f\u0012\tFa\u0015\u0003V\t]#\u0011\f\t\u0004\u0005\u0007\u0002QBAAA\u0011\u001d\tYl\u0004a\u0001\u0003\u0003Dq!!3\u0010\u0001\u0004\tY\rC\u0004\u0002R>\u0001\r!a3\t\u000f\u0005Mw\u00021\u0001\u0002L\"9\u0011Q[\bA\u0002\u0005]\u0007bBAr\u001f\u0001\u0007\u0011Q\u001d\u0005\n\u0003w|\u0001\u0013!a\u0001\u0003KDq!!@\u0010\u0001\u0004\u0011\t\u0001C\u0004\u0003\f=\u0001\rAa\u0004\t\u0013\t]q\u0002%AA\u0002\te\u0001\"\u0003B\u0010\u001fA\u0005\t\u0019\u0001B\u0011\u0003\u0011Awn\u001d;\u0016\u0005\u0005\u0015\u0018!\u00025pgR\u0004\u0013\u0001\u00029peR,\"!a3\u0002\u000bA|'\u000f\u001e\u0011\u0002\u001f]|'o[3s\u0013\u0012\u0004\u0016\r\u001e;fe:\f\u0001c^8sW\u0016\u0014\u0018\n\u001a)biR,'O\u001c\u0011\u0002/\u0019|'o^1sI6+7o]1hKN\u001b\u0007.\u001a3vY\u0016\u0014XC\u0001B8!\u0011\u0011\tHa\u001e\u000e\u0005\tM$\u0002\u0002B;\u0005S\t!bY8oGV\u0014(/\u001a8u\u0013\u0011\u0011IHa\u001d\u00031M\u001b\u0007.\u001a3vY\u0016$W\t_3dkR|'oU3sm&\u001cW-\u0001\rg_J<\u0018M\u001d3NKN\u001c\u0018mZ3TG\",G-\u001e7fe\u0002\nQc\u00197fC:,\b\u000f\u00165sK\u0006$W\t_3dkR|'/\u0006\u0002\u0003\u0002B!!1\u0011BD\u001b\t\u0011)I\u0003\u0003\u0003v\u0005m\u0015\u0002\u0002BE\u0005\u000b\u0013q$\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;Fq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u0003Y\u0019G.Z1okB$\u0006N]3bI\u0016CXmY;u_J\u0004\u0013\u0001\u0005%F\u0003J#&)R!U?6KE\nT%T+\t\u0011\t\n\u0005\u0003\u0002\u001a\nM\u0015\u0002\u0002BK\u00037\u0013A\u0001T8oO\u0006\t\u0002*R!S)\n+\u0015\tV0N\u00132c\u0015j\u0015\u0011\u00029%s\u0015\nV%B\u0019~\u0013ViR%T)J\u000bE+S(O?J+EKU%F'\u0006i\u0012JT%U\u0013\u0006cuLU#H\u0013N#&+\u0011+J\u001f:{&+\u0012+S\u0013\u0016\u001b\u0006%\u0001\u000eU\u001fR\u000bEj\u0018*F\u000f&\u001bFKU!U\u0013>suLU#U%&+5+A\u000eU\u001fR\u000bEj\u0018*F\u000f&\u001bFKU!U\u0013>suLU#U%&+5\u000bI\u0001%\rVS&lX'V\u0019RK\u0005\u000bT%F%~Ke\nV#S-\u0006cu\fT(X\u000bJ{&iT+O\tV\u0011!Q\u0015\t\u0005\u00033\u00139+\u0003\u0003\u0003*\u0006m%A\u0002#pk\ndW-A\u0013G+jSv,T+M)&\u0003F*S#S?&sE+\u0012*W\u00032{FjT,F%~\u0013u*\u0016(EA\u0005\u0011#+R$J'R\u0013\u0016\tV%P\u001d~\u0013V\t\u0016*Z?\u001a+&LW0N+2#\u0016\n\u0015'J\u000bJ\u000b1EU#H\u0013N#&+\u0011+J\u001f:{&+\u0012+S3~3UK\u0017.`\u001bVcE+\u0013)M\u0013\u0016\u0013\u0006%A\u0016J\u001d&#\u0016*\u0011'`%\u0016;\u0015j\u0015+S\u0003RKuJT0S\u000bR\u0013\u0016lX%O)\u0016\u0013f+\u0011'`'\u0016\u001buJ\u0014#T\u00031Je*\u0013+J\u00032{&+R$J'R\u0013\u0016\tV%P\u001d~\u0013V\t\u0016*Z?&sE+\u0012*W\u00032{6+R\"P\u001d\u0012\u001b\u0006%A\u0017Q%>cuJT$F\t~\u0013ViR%T)J\u000bE+S(O?J+EKU-`\u0013:#VI\u0015,B\u0019~\u001bViQ(O\tN\u000ba\u0006\u0015*P\u0019>su)\u0012#`%\u0016;\u0015j\u0015+S\u0003RKuJT0S\u000bR\u0013\u0016lX%O)\u0016\u0013f+\u0011'`'\u0016\u001buJ\u0014#TA\u0005y1\tT#B\u001dV\u0003v,\u0012(B\u00052+E)\u0006\u0002\u0003>B!\u0011\u0011\u0014B`\u0013\u0011\u0011\t-a'\u0003\u000f\t{w\u000e\\3b]\u0006\u00012\tT#B\u001dV\u0003v,\u0012(B\u00052+E\tI\u0001\u0018\u00072+\u0015IT+Q?&sE+\u0012*W\u00032{V*\u0013'M\u0013N\u000b\u0001d\u0011'F\u0003:+\u0006kX%O)\u0016\u0013f+\u0011'`\u001b&cE*S*!\u0003i\t\u0005\u000bU0E\u0003R\u000buLU#U\u000b:#\u0016j\u0014(`'\u0016\u001buJ\u0014#T\u0003m\t\u0005\u000bU0E\u0003R\u000buLU#U\u000b:#\u0016j\u0014(`'\u0016\u001buJ\u0014#TA\u0005\t3\tT#B\u001dV\u0003vLR%M\u000bN{\u0016I\u0012+F%~+\u0005,R\"V)>\u0013v,\u0012-J)\u0006\u00113\tT#B\u001dV\u0003vLR%M\u000bN{\u0016I\u0012+F%~+\u0005,R\"V)>\u0013v,\u0012-J)\u0002\na!\\1ti\u0016\u0014XC\u0001Bk!\u0019\tIJa\u0007\u0003XB!\u0011Q\u0015Bm\u0013\u0011\u0011Y.a*\u0003\u001dI\u00038-\u00128ea>Lg\u000e\u001e*fM\u0006QQ.Y:uKJ|F%Z9\u0015\t\t\u0005(q\u001d\t\u0005\u00033\u0013\u0019/\u0003\u0003\u0003f\u0006m%\u0001B+oSRD\u0011B!;2\u0003\u0003\u0005\rA!6\u0002\u0007a$\u0013'A\u0004nCN$XM\u001d\u0011\u0002;A\u0014XMZ3s\u0007>tg-[4ve\u0016$W*Y:uKJ\fE\r\u001a:fgN\fa\u0004\u001d:fM\u0016\u00148i\u001c8gS\u001e,(/\u001a3NCN$XM]!eIJ,7o\u001d\u0011\u0002-5\f7\u000f^3s\u0003\u0012$'/Z:t)>\u001cuN\u001c8fGR,\"A!>\u0011\r\u0005e%1DAo\u0003ii\u0017m\u001d;fe\u0006#GM]3tgR{7i\u001c8oK\u000e$x\fJ3r)\u0011\u0011\tOa?\t\u0013\t%h'!AA\u0002\tU\u0018aF7bgR,'/\u00113ee\u0016\u001c8\u000fV8D_:tWm\u0019;!\u0003=\t7\r^5wK6\u000b7\u000f^3s+Jd\u0017aE1di&4X-T1ti\u0016\u0014XK\u001d7`I\u0015\fH\u0003\u0002Bq\u0007\u000bA\u0011B!;:\u0003\u0003\u0005\r!!:\u0002!\u0005\u001cG/\u001b<f\u001b\u0006\u001cH/\u001a:Ve2\u0004\u0013\u0001F1di&4X-T1ti\u0016\u0014x+\u001a2VSV\u0013H.\u0001\rbGRLg/Z'bgR,'oV3c+&,&\u000f\\0%KF$BA!9\u0004\u0010!I!\u0011\u001e\u001f\u0002\u0002\u0003\u0007\u0011Q]\u0001\u0016C\u000e$\u0018N^3NCN$XM],fEVKWK\u001d7!\u000399xN]6fe^+'-V5Ve2\f!c^8sW\u0016\u0014x+\u001a2VSV\u0013Hn\u0018\u0013fcR!!\u0011]B\r\u0011%\u0011IoPA\u0001\u0002\u0004\t)/A\bx_J\\WM],fEVKWK\u001d7!\u0003%9xN]6feV\u0013\u0018.\u0006\u0002\u0004\"A!11EB\u0015\u001b\t\u0019)C\u0003\u0003\u0004(\t5\u0012\u0001\u00027b]\u001eLA!a>\u0004&\u0005Qqo\u001c:lKJ,&/\u001b\u0011\u0002\u0015I,w-[:uKJ,G-\u0001\bsK\u001eL7\u000f^3sK\u0012|F%Z9\u0015\t\t\u000581\u0007\u0005\n\u0005S$\u0015\u0011!a\u0001\u0005{\u000b1B]3hSN$XM]3eA\u0005I1m\u001c8oK\u000e$X\rZ\u0001\u000eG>tg.Z2uK\u0012|F%Z9\u0015\t\t\u00058Q\b\u0005\n\u0005S<\u0015\u0011!a\u0001\u0005{\u000b!bY8o]\u0016\u001cG/\u001a3!\u00039!WmY8n[&\u001c8/[8oK\u0012\f!\u0003Z3d_6l\u0017n]:j_:,Gm\u0018\u0013fcR!!\u0011]B$\u0011%\u0011IOSA\u0001\u0002\u0004\u0011i,A\beK\u000e|W.\\5tg&|g.\u001a3!\u0003!9xN]6fe&#\u0017!C<pe.,'/\u00133!\u0003%\u0019\b/\u0019:l\u0011>lW-\u0006\u0002\u0004TA!1QKB.\u001b\t\u00199F\u0003\u0003\u0004Z\t5\u0012AA5p\u0013\u0011\u0019ifa\u0016\u0003\t\u0019KG.Z\u0001\u000bgB\f'o\u001b%p[\u0016\u0004\u0013aB<pe.$\u0015N]\u0001\fo>\u00148\u000eR5s?\u0012*\u0017\u000f\u0006\u0003\u0003b\u000e\u001d\u0004\"\u0003Bu#\u0006\u0005\t\u0019AB*\u0003!9xN]6ESJ\u0004\u0013!\u00054j]&\u001c\b.\u001a3Fq\u0016\u001cW\u000f^8sgV\u00111q\u000e\t\t\u0007c\u001aY(!:\u0004\u00005\u001111\u000f\u0006\u0005\u0007k\u001a9(A\u0004nkR\f'\r\\3\u000b\t\re\u00141T\u0001\u000bG>dG.Z2uS>t\u0017\u0002BB?\u0007g\u0012Q\u0002T5oW\u0016$\u0007*Y:i\u001b\u0006\u0004\b\u0003\u0002B\"\u0007\u0003KAaa!\u0002\u0002\nqQ\t_3dkR|'OU;o]\u0016\u0014\u0018A\u00054j]&\u001c\b.\u001a3Fq\u0016\u001cW\u000f^8sg\u0002\nq\u0001\u001a:jm\u0016\u00148/\u0006\u0002\u0004\fBA1\u0011OBG\u0003K\u001c\t*\u0003\u0003\u0004\u0010\u000eM$a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0005\u0007\u001a\u0019*\u0003\u0003\u0004\u0016\u0006\u0005%\u0001\u0004#sSZ,'OU;o]\u0016\u0014\u0018\u0001\u00033sSZ,'o\u001d\u0011\u0002\u0013\u0015DXmY;u_J\u001cXCABO!!\u0019\th!$\u0002f\u000e}\u0014AC3yK\u000e,Ho\u001c:tA\u0005ya-\u001b8jg\",G\r\u0012:jm\u0016\u00148/\u0006\u0002\u0004&BA1\u0011OB>\u0003K\u001c\t*\u0001\tgS:L7\u000f[3e\tJLg/\u001a:tA\u0005q\u0011\r\u001d9ESJ,7\r^8sS\u0016\u001cXCABW!!\u0019\th!$\u0002f\u000e=\u0006CBBY\u0007w\u000b)O\u0004\u0003\u00044\u000e]f\u0002BAv\u0007kK!!!(\n\t\re\u00161T\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0019ila0\u0003\u0007M+\u0017O\u0003\u0003\u0004:\u0006m\u0015aD1qa\u0012K'/Z2u_JLWm\u001d\u0011\u0002\u0019\u0019Lg.[:iK\u0012\f\u0005\u000f]:\u0016\u0005\r\u001d\u0007CBB9\u0007\u0013\f)/\u0003\u0003\u0004L\u000eM$a\u0002%bg\"\u001cV\r^\u0001\u000eM&t\u0017n\u001d5fI\u0006\u0003\bo\u001d\u0011\u0002A\u0015DXmY;u_J\u001cF/\u0019;f'ft7MR1jYV\u0014X-\u0011;uK6\u0004Ho]\u000b\u0003\u0007'\u0004\u0002b!\u001d\u0004\u000e\u0006\u0015\u00181Z\u0001\"Kb,7-\u001e;peN#\u0018\r^3Ts:\u001cg)Y5mkJ,\u0017\t\u001e;f[B$8\u000fI\u0001 Kb,7-\u001e;peN#\u0018\r^3Ts:\u001cg)Y5mkJ,\u0007*\u00198eY\u0016\u0014XCABn!\u0011\u0011\u0019i!8\n\t\r}'Q\u0011\u0002\u0019\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR,\u00050Z2vi>\u0014\u0018\u0001H3yK\u000e,Ho\u001c:Ti\u0006$XmU=oG6\u000b\u00070\u0011;uK6\u0004Ho]\u0001\u001eKb,7-\u001e;peN#\u0018\r^3Ts:\u001cW*\u0019=BiR,W\u000e\u001d;tA\u0005\tB-\u001a4bk2$\u0018i]6US6,w.\u001e;\u0002%\u0011,g-Y;mi\u0006\u001b8\u000eV5nK>,H\u000fI\u0001\u0012e\u0016$\u0018-\u001b8fI\u0016CXmY;u_J\u001c\u0018A\u0005:fi\u0006Lg.\u001a3Fq\u0016\u001cW\u000f^8sg\u0002\nqB]3uC&tW\r\u001a#sSZ,'o]\u0001\u0011e\u0016$\u0018-\u001b8fI\u0012\u0013\u0018N^3sg\u0002\nab\u001d5vM\u001adWmU3sm&\u001cW-\u0006\u0002\u00036\u0005y1\u000f[;gM2,7+\u001a:wS\u000e,\u0007%A\u0007qk\nd\u0017nY!eIJ,7o]\u0001\u000faV\u0014G.[2BI\u0012\u0014Xm]:!\u0003\u00159XMY+j+\t\u0019y\u0010\u0005\u0003\u0005\u0002\u0011\u001dQB\u0001C\u0002\u0015\u0011!)!!!\u0002\u0005UL\u0017\u0002\u0002C\u0005\t\u0007\u00111bV8sW\u0016\u0014x+\u001a2V\u0013\u0006Iq/\u001a2VS~#S-\u001d\u000b\u0005\u0005C$y\u0001C\u0005\u0003j>\f\t\u00111\u0001\u0004\u0000\u00061q/\u001a2VS\u0002\nacY8o]\u0016\u001cG/[8o\u0003R$X-\u001c9u\u0007>,h\u000e^\u0001\u001bG>tg.Z2uS>t\u0017\t\u001e;f[B$8i\\;oi~#S-\u001d\u000b\u0005\u0005C$I\u0002C\u0005\u0003jJ\f\t\u00111\u0001\u0002L\u000692m\u001c8oK\u000e$\u0018n\u001c8BiR,W\u000e\u001d;D_VtG\u000fI\u0001\u000e[\u0016$(/[2t'f\u001cH/Z7\u0016\u0005\u0011\u0005\u0002\u0003\u0002C\u0012\tSi!\u0001\"\n\u000b\t\u0011\u001d\u0012\u0011R\u0001\b[\u0016$(/[2t\u0013\u0011!Y\u0003\"\n\u0003\u001b5+GO]5dgNK8\u000f^3n\u00039iW\r\u001e:jGN\u001c\u0016p\u001d;f[\u0002\nAb^8sW\u0016\u00148k\\;sG\u0016,\"\u0001b\r\u0011\t\t\rCQG\u0005\u0005\to\t\tI\u0001\u0007X_J\\WM]*pkJ\u001cW-A\u0007x_J\\WM]*pkJ\u001cW\rI\u0001\re\u00164XM]:f!J|\u00070_\u0001\u000ee\u00164XM]:f!J|\u00070\u001f\u0011\u0002+I,w-[:uKJl\u0015m\u001d;fe\u001a+H/\u001e:fgV\u0011A1\t\t\u0007\u00033\u000bI\u000e\"\u00121\t\u0011\u001dC\u0011\u000b\t\u0007\u0005c\"I\u0005\"\u0014\n\t\u0011-#1\u000f\u0002\u0007\rV$XO]3\u0011\t\u0011=C\u0011\u000b\u0007\u0001\t-!\u0019\u0006`A\u0001\u0002\u0003\u0015\t\u0001b\u0016\u0003\u0007}#\u0013'\u0001\fsK\u001eL7\u000f^3s\u001b\u0006\u001cH/\u001a:GkR,(/Z:!#\u0011!I\u0006b\u0018\u0011\t\u0005eE1L\u0005\u0005\t;\nYJA\u0004O_RD\u0017N\\4\u0011\t\u0005eE\u0011M\u0005\u0005\tG\nYJA\u0002B]f\f\u0011D]3hSN$XM]'bgR,'OR;ukJ,7o\u0018\u0013fcR!!\u0011\u001dC5\u0011%\u0011Io_A\u0001\u0002\u0004!Y\u0007\u0005\u0004\u0002\u001a\u0006eGQ\u000e\u0019\u0005\t_\"\u0019\b\u0005\u0004\u0003r\u0011%C\u0011\u000f\t\u0005\t\u001f\"\u0019\b\u0002\u0007\u0005T\u0011%\u0014\u0011!A\u0001\u0006\u0003!9&\u0001\fsK\u001eL7\u000f\u001e:bi&|gNU3uef$\u0016.\\3s+\t!I\b\u0005\u0004\u0002\u001a\nmA1\u0010\u0019\u0005\t{\")\t\u0005\u0004\u0003r\u0011}D1Q\u0005\u0005\t\u0003\u0013\u0019HA\bTG\",G-\u001e7fI\u001a+H/\u001e:f!\u0011!y\u0005\"\"\u0005\u0017\u0011\u001du0!A\u0001\u0002\u000b\u0005Aq\u000b\u0002\u0004?\u0012\u0012\u0014a\u0006:fO&\u001cHO]1uS>t'+\u001a;ssRKW.\u001a:!\u0003i\u0011XmZ5tiJ\fG/[8o%\u0016$(/\u001f+j[\u0016\u0014x\fJ3r)\u0011\u0011\t\u000fb$\t\u0013\t%h0!AA\u0002\u0011E\u0005CBAM\u00057!\u0019\n\r\u0003\u0005\u0016\u0012e\u0005C\u0002B9\t\u007f\"9\n\u0005\u0003\u0005P\u0011eE\u0001\u0004CD\t\u001f\u000b\t\u0011!A\u0003\u0002\u0011]\u0013\u0001\u0007:fO&\u001cH/\u001a:NCN$XM\u001d+ie\u0016\fG\rU8pYV\u0011Aq\u0014\t\u0005\u0005c\"\t+\u0003\u0003\u0005$\nM$A\u0005+ie\u0016\fG\rU8pY\u0016CXmY;u_J\f\u0011D]3hSN$XM]'bgR,'\u000f\u00165sK\u0006$\u0007k\\8mA\u0005I!/Z:pkJ\u001cWm]\u000b\u0003\tW\u0003\u0002\"a:\u0005.\u0006\u0015H\u0011W\u0005\u0005\t_\u000bIPA\u0002NCB\u0004B\u0001b-\u0005:6\u0011AQ\u0017\u0006\u0005\to\u000bI)\u0001\u0005sKN|WO]2f\u0013\u0011!Y\f\".\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0002\u001bI,7o\\;sG\u0016\u001cx\fJ3r)\u0011\u0011\t\u000f\"1\t\u0015\t%\u0018qAA\u0001\u0002\u0004!Y+\u0001\u0006sKN|WO]2fg\u0002\n\u0011bY8sKN,6/\u001a3\u0002\u001b\r|'/Z:Vg\u0016$w\fJ3r)\u0011\u0011\t\u000fb3\t\u0015\t%\u0018QBA\u0001\u0002\u0004\tY-\u0001\u0006d_J,7/V:fI\u0002\n!\"\\3n_JLXk]3e\u00039iW-\\8ssV\u001bX\rZ0%KF$BA!9\u0005V\"Q!\u0011^A\n\u0003\u0003\u0005\r!a3\u0002\u00175,Wn\u001c:z+N,G\rI\u0001\u000ee\u0016\u001cx.\u001e:dKN,6/\u001a3\u0016\u0005\u0011u\u0007\u0003CB9\u0007\u001b\u000b)\u000fb8\u0011\t\u0011\u0005Hq\u001f\b\u0005\tG$\u0019P\u0004\u0003\u0005f\u0012Eh\u0002\u0002Ct\t_tA\u0001\";\u0005n:!\u00111\u001eCv\u0013\t\t\u0019*\u0003\u0003\u0002\u0010\u0006E\u0015\u0002BAF\u0003\u001bKA!a\"\u0002\n&!AQ_AC\u0003]\u0019F/\u00198eC2|g.\u001a*fg>,(oY3Vi&d7/\u0003\u0003\u0005z\u0012m(aE'vi\u0006\u0014G.\u001a*fg>,(oY3J]\u001a|'\u0002\u0002C{\u0003\u000b\u000baB]3t_V\u00148-Z:Vg\u0016$\u0007%A\u0005d_J,7O\u0012:fK\u0006QQ.Z7pef4%/Z3\u0002\u001b\r\u0014X-\u0019;f/>\u00148\u000eR5s)\t\u0011\t/A\u0004p]N#\u0018M\u001d;\u0002)M,G/\u001e9X_J\\WM\u001d*fg>,(oY3t\u0003A\tG\r\u001a*fg>,(oY3t+N,G\r\u0006\u0003\u0003b\u0016=\u0001\u0002CC\t\u0003K\u0001\r\u0001b+\u0002\u0013\u0011,G\u000e^1J]\u001a|\u0017a\u0005:f[>4XMU3t_V\u00148-Z:Vg\u0016$G\u0003\u0002Bq\u000b/A\u0001\"\"\u0005\u0002(\u0001\u0007A1V\u0001\rG\"\fgnZ3NCN$XM\u001d\u000b\t\u0005C,i\"\"\t\u0006&!AQqDA\u0015\u0001\u0004\u00119.A\u0005nCN$XM\u001d*fM\"AQ1EA\u0015\u0001\u0004\t)/A\u0003vSV\u0013H\u000e\u0003\u0005\u0006(\u0005%\u0002\u0019AAo\u00035i\u0017m\u001d;fe\u0006#GM]3tg\u0006)BO]=SK\u001eL7\u000f^3s\u00032dW*Y:uKJ\u001cHCAC\u0017!\u0019\tI*!7\u00060A\"Q\u0011GC\u001b!\u0019\u0011\t\b\"\u0013\u00064A!AqJC\u001b\t1)9$a\u000b\u0002\u0002\u0003\u0005)\u0011\u0001C,\u0005\ryFeM\u0001\u0015e\u0016\u0014XmZ5ti\u0016\u0014x+\u001b;i\u001b\u0006\u001cH/\u001a:\u00027\r\fgnY3m\u0019\u0006\u001cHOU3hSN$(/\u0019;j_:\u0014V\r\u001e:z\u0003I\u0011XmZ5ti\u0016\u0014x+\u001b;i\u001b\u0006\u001cH/\u001a:\u00027M$\u0018M\u001d;FqR,'O\\1m'\",hM\u001a7f'\u0016\u0014h/[2f\u0003m\u0019XM\u001c3SK\u001eL7\u000f^3s\u001b\u0016\u001c8/Y4f)>l\u0015m\u001d;feR!!\u0011]C#\u0011!)9%!\u000eA\u0002\t]\u0017AD7bgR,'/\u00128ea>Lg\u000e^\u0001\u0017Q\u0006tG\r\\3SK\u001eL7\u000f^3s%\u0016\u001c\bo\u001c8tKR!!\u0011]C'\u0011!)y%a\u000eA\u0002\u0015E\u0013aA7tOB!Q1KC-\u001d\u0011!\u0019/\"\u0016\n\t\u0015]\u0013QQ\u0001\u000f\t\u0016\u0004Hn\\=NKN\u001c\u0018mZ3t\u0013\u0011)Y&\"\u0018\u0003-I+w-[:uKJ<vN]6feJ+7\u000f]8og\u0016TA!b\u0016\u0002\u0006\u00069!/Z2fSZ,WCAC2!!\tI*\"\u001a\u0005`\t\u0005\u0018\u0002BC4\u00037\u0013q\u0002U1si&\fGNR;oGRLwN\\\u0001\u0010e\u0016\u001cW-\u001b<f\u0003:$'+\u001a9msR!Q1MC7\u0011!)y'a\u000fA\u0002\u0015E\u0014aB2p]R,\u0007\u0010\u001e\t\u0005\u0003K+\u0019(\u0003\u0003\u0006v\u0005\u001d&A\u0004*qG\u000e\u000bG\u000e\\\"p]R,\u0007\u0010^\u0001\u000f_:$\u0015n]2p]:,7\r^3e)\u0011\u0011\t/b\u001f\t\u0011\u0015u\u0014Q\ba\u0001\u0003;\fQB]3n_R,\u0017\t\u001a3sKN\u001c\u0018AE7bgR,'\u000fR5tG>tg.Z2uK\u0012\fq#\\1zE\u0016\u001cE.Z1okB\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8\u0015\t\t\u0005XQ\u0011\u0005\t\u000b\u000f\u000b\t\u00051\u0001\u0002f\u0006\u0011\u0011\u000eZ\u0001\rg\u0016tG\rV8NCN$XM\u001d\u000b\u0005\u0005C,i\t\u0003\u0005\u0006\u0010\u0006\r\u0003\u0019\u0001C0\u0003\u001diWm]:bO\u0016\f1d]=oG\u0016CXmY;u_J\u001cF/\u0019;f/&$\b.T1ti\u0016\u0014H\u0003\u0002Bq\u000b+C\u0001\"b&\u0002F\u0001\u0007Q\u0011T\u0001\t]\u0016<8\u000b^1uKB!Q1KCN\u0013\u0011)i*\"\u0018\u0003)\u0015CXmY;u_J\u001cF/\u0019;f\u0007\"\fgnZ3e\u0003A9WM\\3sCR,wk\u001c:lKJLE\r\u0006\u0002\u0002f\u00061qN\\*u_B\f\u0001\u0005\u001e:j[\u001aKg.[:iK\u0012,\u00050Z2vi>\u00148/\u00134OK\u000e,7o]1ss\u0006qBO]5n\r&t\u0017n\u001d5fI\u0012\u0013\u0018N^3sg&3g*Z2fgN\f'/_\u0001\u0011I\u0016\u001cw.\\7jgNLwN\\*fY\u001a\f\u0001\u0004[1oI2,GI]5wKJ\u001cF/\u0019;f\u0007\"\fgnZ3e)\u0011\u0011\t/b,\t\u0011\u0015E\u0016\u0011\u000ba\u0001\u000bg\u000b!\u0003\u001a:jm\u0016\u00148\u000b^1uK\u000eC\u0017M\\4fIB!Q1KC[\u0013\u0011)9,\"\u0018\u0003%\u0011\u0013\u0018N^3s'R\fG/Z\"iC:<W\rZ\u0001\u001bQ\u0006tG\r\\3Fq\u0016\u001cW\u000f^8s'R\fG/Z\"iC:<W\r\u001a\u000b\u0005\u0005C,i\f\u0003\u0005\u0006@\u0006M\u0003\u0019ACM\u0003Q)\u00070Z2vi>\u00148\u000b^1uK\u000eC\u0017M\\4fI\u00061qk\u001c:lKJ\u0004BAa\u0011\u0002XM1\u0011qKAL\u0003_#\"!b1\u0002\u0017MK6\u000bV#N?:\u000bU*R\u0001\r'f\u001bF+R'`\u001d\u0006kU\tI\u0001\u000e\u000b:#\u0005kT%O)~s\u0015)T#\u0002\u001d\u0015sE\tU(J\u001dR{f*Q'FA\u0005i2k\u0015'`\u001d>#Ui\u0018'P\u0007\u0006culQ(O\r&;u\fU!U)\u0016\u0013f*\u0006\u0002\u0006VB!Qq[Cp\u001b\t)IN\u0003\u0003\u0006\\\u0016u\u0017\u0001C7bi\u000eD\u0017N\\4\u000b\t\t-\u00121T\u0005\u0005\u000bC,INA\u0003SK\u001e,\u00070\u0001\u0010T'2{fj\u0014#F?2{5)\u0011'`\u0007>se)S$`!\u0006#F+\u0012*OA\u0005\u0019B)\u0011+F?RKU*R0G\u001fJk\u0015\t\u0016+F%V\u0011Q\u0011\u001e\t\u0005\u000bW,)0\u0004\u0002\u0006n*!Qq^Cy\u0003\u00191wN]7bi*!Q1\u001fB\u0017\u0003\u0011!\u0018.\\3\n\t\u0015]XQ\u001e\u0002\u0012\t\u0006$X\rV5nK\u001a{'/\\1ui\u0016\u0014\u0018\u0001\u0006#B)\u0016{F+S'F?\u001a{%+T!U)\u0016\u0013\u0006%\u0001\u0003nC&tG\u0003\u0002Bq\u000b\u007fD\u0001B\"\u0001\u0002l\u0001\u0007a1A\u0001\u000bCJ<7\u000b\u001e:j]\u001e\u001c\bCBAM\u00033\f)/\u0001\fti\u0006\u0014HO\u00159d\u000b:4\u0018I\u001c3F]\u0012\u0004x.\u001b8u)Y\t\tM\"\u0003\u0007\f\u00195aq\u0002D\t\r'19B\"\u0007\u0007 \u0019\u0005\u0002\u0002\u0003B.\u0003[\u0002\r!!:\t\u0011\t\u0005\u0014Q\u000ea\u0001\u0003\u0017D\u0001\"!3\u0002n\u0001\u0007\u00111\u001a\u0005\t\u0003#\fi\u00071\u0001\u0002L\"A\u00111[A7\u0001\u0004\tY\r\u0003\u0005\u0007\u0016\u00055\u0004\u0019\u0001D\u0002\u0003)i\u0017m\u001d;feV\u0013Hn\u001d\u0005\t\u0007C\ni\u00071\u0001\u0002f\"Qa1DA7!\u0003\u0005\rA\"\b\u0002\u0019]|'o[3s\u001dVl'-\u001a:\u0011\r\u0005e%1DAf\u0011)\ti0!\u001c\u0011\u0002\u0003\u0007!\u0011\u0001\u0005\u000b\u0005/\ti\u0007%AA\u0002\te\u0011\u0001I:uCJ$(\u000b]2F]Z\fe\u000eZ#oIB|\u0017N\u001c;%I\u00164\u0017-\u001e7uIa*\"Ab\n+\t\u0019ua\u0011F\u0016\u0003\rW\u0001BA\"\f\u000785\u0011aq\u0006\u0006\u0005\rc1\u0019$A\u0005v]\u000eDWmY6fI*!aQGAN\u0003)\tgN\\8uCRLwN\\\u0005\u0005\rs1yCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001e\u001d;beR\u0014\u0006oY#om\u0006sG-\u00128ea>Lg\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%sU\u0011aq\b\u0016\u0005\u0005\u00031I#A\u0011ti\u0006\u0014HO\u00159d\u000b:4\u0018I\u001c3F]\u0012\u0004x.\u001b8uI\u0011,g-Y;mi\u0012\n\u0004'\u0006\u0002\u0007F)\"!\u0011\u0004D\u0015\u0003]I7/V:f\u0019>\u001c\u0017\r\u001c(pI\u0016\u001c6\u000bT\"p]\u001aLw\r\u0006\u0003\u0003>\u001a-\u0003\u0002\u0003D'\u0003k\u0002\rAb\u0014\u0002\u0007\rlG\r\u0005\u0003\u00038\u0019E\u0013\u0002\u0002D*\u0003\u000b\u0013qaQ8n[\u0006tG-\u0001\fnCf\u0014W-\u00169eCR,7k\u0015'TKR$\u0018N\\4t)\u00191yE\"\u0017\u0007\\!AaQJA<\u0001\u00041y\u0005\u0003\u0005\u0002~\u0006]\u0004\u0019\u0001B\u0001\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%oU\u0011a\u0011\r\u0016\u0005\u0003K4I#\u0001\u000f%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\r\u0019\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132cU\u0011a\u0011\u000e\u0016\u0005\u0005C1I\u0003"
)
public class Worker implements ThreadSafeRpcEndpoint, Logging {
   private ExecutionContextExecutor executorStateSyncFailureHandler;
   private final RpcEnv rpcEnv;
   private final int webUiPort;
   public final int org$apache$spark$deploy$worker$Worker$$cores;
   public final int org$apache$spark$deploy$worker$Worker$$memory;
   private final RpcAddress[] masterRpcAddresses;
   private final String workDirPath;
   private final SparkConf conf;
   private final SecurityManager securityMgr;
   private final Option resourceFileOpt;
   private final String org$apache$spark$deploy$worker$Worker$$host;
   private final int org$apache$spark$deploy$worker$Worker$$port;
   private final String workerIdPattern;
   private final ScheduledExecutorService forwardMessageScheduler;
   private final ExecutionContextExecutorService org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor;
   private final long HEARTBEAT_MILLIS;
   private final int INITIAL_REGISTRATION_RETRIES;
   private final int TOTAL_REGISTRATION_RETRIES;
   private final double FUZZ_MULTIPLIER_INTERVAL_LOWER_BOUND;
   private final double REGISTRATION_RETRY_FUZZ_MULTIPLIER;
   private final long INITIAL_REGISTRATION_RETRY_INTERVAL_SECONDS;
   private final long PROLONGED_REGISTRATION_RETRY_INTERVAL_SECONDS;
   private final boolean CLEANUP_ENABLED;
   private final long CLEANUP_INTERVAL_MILLIS;
   private final long org$apache$spark$deploy$worker$Worker$$APP_DATA_RETENTION_SECONDS;
   private final boolean CLEANUP_FILES_AFTER_EXECUTOR_EXIT;
   private Option master;
   private final boolean preferConfiguredMasterAddress;
   private Option masterAddressToConnect;
   private String org$apache$spark$deploy$worker$Worker$$activeMasterUrl;
   private String activeMasterWebUiUrl;
   private String org$apache$spark$deploy$worker$Worker$$workerWebUiUrl;
   private final String org$apache$spark$deploy$worker$Worker$$workerUri;
   private boolean registered;
   private boolean org$apache$spark$deploy$worker$Worker$$connected;
   private boolean org$apache$spark$deploy$worker$Worker$$decommissioned;
   private final String workerId;
   private final File org$apache$spark$deploy$worker$Worker$$sparkHome;
   private File workDir;
   private final LinkedHashMap finishedExecutors;
   private final HashMap drivers;
   private final HashMap executors;
   private final LinkedHashMap finishedDrivers;
   private final HashMap appDirectories;
   private final HashSet finishedApps;
   private final HashMap executorStateSyncFailureAttempts;
   private final int executorStateSyncMaxAttempts;
   private final long defaultAskTimeout;
   private final int retainedExecutors;
   private final int retainedDrivers;
   private final ExternalShuffleService org$apache$spark$deploy$worker$Worker$$shuffleService;
   private final String org$apache$spark$deploy$worker$Worker$$publicAddress;
   private WorkerWebUI org$apache$spark$deploy$worker$Worker$$webUi;
   private int connectionAttemptCount;
   private final MetricsSystem metricsSystem;
   private final WorkerSource workerSource;
   private final boolean reverseProxy;
   private Future[] registerMasterFutures;
   private Option registrationRetryTimer;
   private final ThreadPoolExecutor registerMasterThreadPool;
   private Map resources;
   private int coresUsed;
   private int memoryUsed;
   private final HashMap resourcesUsed;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static Supplier $lessinit$greater$default$11() {
      return Worker$.MODULE$.$lessinit$greater$default$11();
   }

   public static Option $lessinit$greater$default$10() {
      return Worker$.MODULE$.$lessinit$greater$default$10();
   }

   public static String $lessinit$greater$default$7() {
      return Worker$.MODULE$.$lessinit$greater$default$7();
   }

   public static Command maybeUpdateSSLSettings(final Command cmd, final SparkConf conf) {
      return Worker$.MODULE$.maybeUpdateSSLSettings(cmd, conf);
   }

   public static boolean isUseLocalNodeSSLConfig(final Command cmd) {
      return Worker$.MODULE$.isUseLocalNodeSSLConfig(cmd);
   }

   public static Option startRpcEnvAndEndpoint$default$10() {
      return Worker$.MODULE$.startRpcEnvAndEndpoint$default$10();
   }

   public static SparkConf startRpcEnvAndEndpoint$default$9() {
      return Worker$.MODULE$.startRpcEnvAndEndpoint$default$9();
   }

   public static Option startRpcEnvAndEndpoint$default$8() {
      return Worker$.MODULE$.startRpcEnvAndEndpoint$default$8();
   }

   public static RpcEnv startRpcEnvAndEndpoint(final String host, final int port, final int webUiPort, final int cores, final int memory, final String[] masterUrls, final String workDir, final Option workerNumber, final SparkConf conf, final Option resourceFileOpt) {
      return Worker$.MODULE$.startRpcEnvAndEndpoint(host, port, webUiPort, cores, memory, masterUrls, workDir, workerNumber, conf, resourceFileOpt);
   }

   public static void main(final String[] argStrings) {
      Worker$.MODULE$.main(argStrings);
   }

   public static String ENDPOINT_NAME() {
      return Worker$.MODULE$.ENDPOINT_NAME();
   }

   public static String SYSTEM_NAME() {
      return Worker$.MODULE$.SYSTEM_NAME();
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

   public SparkConf conf() {
      return this.conf;
   }

   public SecurityManager securityMgr() {
      return this.securityMgr;
   }

   public String org$apache$spark$deploy$worker$Worker$$host() {
      return this.org$apache$spark$deploy$worker$Worker$$host;
   }

   public int org$apache$spark$deploy$worker$Worker$$port() {
      return this.org$apache$spark$deploy$worker$Worker$$port;
   }

   private String workerIdPattern() {
      return this.workerIdPattern;
   }

   private ScheduledExecutorService forwardMessageScheduler() {
      return this.forwardMessageScheduler;
   }

   public ExecutionContextExecutorService org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor() {
      return this.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor;
   }

   private long HEARTBEAT_MILLIS() {
      return this.HEARTBEAT_MILLIS;
   }

   private int INITIAL_REGISTRATION_RETRIES() {
      return this.INITIAL_REGISTRATION_RETRIES;
   }

   private int TOTAL_REGISTRATION_RETRIES() {
      return this.TOTAL_REGISTRATION_RETRIES;
   }

   private double FUZZ_MULTIPLIER_INTERVAL_LOWER_BOUND() {
      return this.FUZZ_MULTIPLIER_INTERVAL_LOWER_BOUND;
   }

   private double REGISTRATION_RETRY_FUZZ_MULTIPLIER() {
      return this.REGISTRATION_RETRY_FUZZ_MULTIPLIER;
   }

   private long INITIAL_REGISTRATION_RETRY_INTERVAL_SECONDS() {
      return this.INITIAL_REGISTRATION_RETRY_INTERVAL_SECONDS;
   }

   private long PROLONGED_REGISTRATION_RETRY_INTERVAL_SECONDS() {
      return this.PROLONGED_REGISTRATION_RETRY_INTERVAL_SECONDS;
   }

   private boolean CLEANUP_ENABLED() {
      return this.CLEANUP_ENABLED;
   }

   private long CLEANUP_INTERVAL_MILLIS() {
      return this.CLEANUP_INTERVAL_MILLIS;
   }

   public long org$apache$spark$deploy$worker$Worker$$APP_DATA_RETENTION_SECONDS() {
      return this.org$apache$spark$deploy$worker$Worker$$APP_DATA_RETENTION_SECONDS;
   }

   private boolean CLEANUP_FILES_AFTER_EXECUTOR_EXIT() {
      return this.CLEANUP_FILES_AFTER_EXECUTOR_EXIT;
   }

   private Option master() {
      return this.master;
   }

   private void master_$eq(final Option x$1) {
      this.master = x$1;
   }

   private boolean preferConfiguredMasterAddress() {
      return this.preferConfiguredMasterAddress;
   }

   private Option masterAddressToConnect() {
      return this.masterAddressToConnect;
   }

   private void masterAddressToConnect_$eq(final Option x$1) {
      this.masterAddressToConnect = x$1;
   }

   public String org$apache$spark$deploy$worker$Worker$$activeMasterUrl() {
      return this.org$apache$spark$deploy$worker$Worker$$activeMasterUrl;
   }

   private void activeMasterUrl_$eq(final String x$1) {
      this.org$apache$spark$deploy$worker$Worker$$activeMasterUrl = x$1;
   }

   public String activeMasterWebUiUrl() {
      return this.activeMasterWebUiUrl;
   }

   public void activeMasterWebUiUrl_$eq(final String x$1) {
      this.activeMasterWebUiUrl = x$1;
   }

   public String org$apache$spark$deploy$worker$Worker$$workerWebUiUrl() {
      return this.org$apache$spark$deploy$worker$Worker$$workerWebUiUrl;
   }

   private void workerWebUiUrl_$eq(final String x$1) {
      this.org$apache$spark$deploy$worker$Worker$$workerWebUiUrl = x$1;
   }

   public String org$apache$spark$deploy$worker$Worker$$workerUri() {
      return this.org$apache$spark$deploy$worker$Worker$$workerUri;
   }

   private boolean registered() {
      return this.registered;
   }

   private void registered_$eq(final boolean x$1) {
      this.registered = x$1;
   }

   public boolean org$apache$spark$deploy$worker$Worker$$connected() {
      return this.org$apache$spark$deploy$worker$Worker$$connected;
   }

   private void connected_$eq(final boolean x$1) {
      this.org$apache$spark$deploy$worker$Worker$$connected = x$1;
   }

   public boolean org$apache$spark$deploy$worker$Worker$$decommissioned() {
      return this.org$apache$spark$deploy$worker$Worker$$decommissioned;
   }

   private void decommissioned_$eq(final boolean x$1) {
      this.org$apache$spark$deploy$worker$Worker$$decommissioned = x$1;
   }

   public String workerId() {
      return this.workerId;
   }

   public File org$apache$spark$deploy$worker$Worker$$sparkHome() {
      return this.org$apache$spark$deploy$worker$Worker$$sparkHome;
   }

   public File workDir() {
      return this.workDir;
   }

   public void workDir_$eq(final File x$1) {
      this.workDir = x$1;
   }

   public LinkedHashMap finishedExecutors() {
      return this.finishedExecutors;
   }

   public HashMap drivers() {
      return this.drivers;
   }

   public HashMap executors() {
      return this.executors;
   }

   public LinkedHashMap finishedDrivers() {
      return this.finishedDrivers;
   }

   public HashMap appDirectories() {
      return this.appDirectories;
   }

   public HashSet finishedApps() {
      return this.finishedApps;
   }

   private HashMap executorStateSyncFailureAttempts() {
      return this.executorStateSyncFailureAttempts;
   }

   private ExecutionContextExecutor executorStateSyncFailureHandler$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.executorStateSyncFailureHandler = .MODULE$.fromExecutor(ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("executor-state-sync-failure-handler"));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.executorStateSyncFailureHandler;
   }

   private ExecutionContextExecutor executorStateSyncFailureHandler() {
      return !this.bitmap$0 ? this.executorStateSyncFailureHandler$lzycompute() : this.executorStateSyncFailureHandler;
   }

   private int executorStateSyncMaxAttempts() {
      return this.executorStateSyncMaxAttempts;
   }

   private long defaultAskTimeout() {
      return this.defaultAskTimeout;
   }

   public int retainedExecutors() {
      return this.retainedExecutors;
   }

   public int retainedDrivers() {
      return this.retainedDrivers;
   }

   public ExternalShuffleService org$apache$spark$deploy$worker$Worker$$shuffleService() {
      return this.org$apache$spark$deploy$worker$Worker$$shuffleService;
   }

   public String org$apache$spark$deploy$worker$Worker$$publicAddress() {
      return this.org$apache$spark$deploy$worker$Worker$$publicAddress;
   }

   public WorkerWebUI org$apache$spark$deploy$worker$Worker$$webUi() {
      return this.org$apache$spark$deploy$worker$Worker$$webUi;
   }

   private void webUi_$eq(final WorkerWebUI x$1) {
      this.org$apache$spark$deploy$worker$Worker$$webUi = x$1;
   }

   private int connectionAttemptCount() {
      return this.connectionAttemptCount;
   }

   private void connectionAttemptCount_$eq(final int x$1) {
      this.connectionAttemptCount = x$1;
   }

   private MetricsSystem metricsSystem() {
      return this.metricsSystem;
   }

   private WorkerSource workerSource() {
      return this.workerSource;
   }

   public boolean reverseProxy() {
      return this.reverseProxy;
   }

   private Future[] registerMasterFutures() {
      return this.registerMasterFutures;
   }

   private void registerMasterFutures_$eq(final Future[] x$1) {
      this.registerMasterFutures = x$1;
   }

   private Option registrationRetryTimer() {
      return this.registrationRetryTimer;
   }

   private void registrationRetryTimer_$eq(final Option x$1) {
      this.registrationRetryTimer = x$1;
   }

   private ThreadPoolExecutor registerMasterThreadPool() {
      return this.registerMasterThreadPool;
   }

   public Map resources() {
      return this.resources;
   }

   public void resources_$eq(final Map x$1) {
      this.resources = x$1;
   }

   public int coresUsed() {
      return this.coresUsed;
   }

   public void coresUsed_$eq(final int x$1) {
      this.coresUsed = x$1;
   }

   public int memoryUsed() {
      return this.memoryUsed;
   }

   public void memoryUsed_$eq(final int x$1) {
      this.memoryUsed = x$1;
   }

   public HashMap resourcesUsed() {
      return this.resourcesUsed;
   }

   public int coresFree() {
      return this.org$apache$spark$deploy$worker$Worker$$cores - this.coresUsed();
   }

   public int memoryFree() {
      return this.org$apache$spark$deploy$worker$Worker$$memory - this.memoryUsed();
   }

   private void createWorkDir() {
      this.workDir_$eq((File)scala.Option..MODULE$.apply(this.workDirPath).map((x$1) -> new File(x$1)).getOrElse(() -> new File(this.org$apache$spark$deploy$worker$Worker$$sparkHome(), "work")));
      if (!Utils$.MODULE$.createDirectory(this.workDir())) {
         System.exit(1);
      }
   }

   public void onStart() {
      scala.Predef..MODULE$.assert(!this.registered());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting Spark worker ", ":", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.org$apache$spark$deploy$worker$Worker$$host()), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$deploy$worker$Worker$$port()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", " cores, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CORES..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$deploy$worker$Worker$$cores))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " RAM"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.megabytesToString((long)this.org$apache$spark$deploy$worker$Worker$$memory))}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Running Spark version ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_VERSION..MODULE$, package$.MODULE$.SPARK_VERSION())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark home: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.org$apache$spark$deploy$worker$Worker$$sparkHome())})))));
      this.createWorkDir();
      this.startExternalShuffleService();
      this.setupWorkerResources();
      this.webUi_$eq(new WorkerWebUI(this, this.workDir(), this.webUiPort));
      this.org$apache$spark$deploy$worker$Worker$$webUi().bind();
      String var10001 = this.org$apache$spark$deploy$worker$Worker$$webUi().scheme();
      this.workerWebUiUrl_$eq(var10001 + this.org$apache$spark$deploy$worker$Worker$$publicAddress() + ":" + this.org$apache$spark$deploy$worker$Worker$$webUi().boundPort());
      this.org$apache$spark$deploy$worker$Worker$$registerWithMaster();
      this.metricsSystem().registerSource(this.workerSource());
      this.metricsSystem().start(this.metricsSystem().start$default$1());
      ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
      Object var2 = scala.Predef..MODULE$.refArrayOps(this.metricsSystem().getServletHandlers());
      WorkerWebUI var1 = this.org$apache$spark$deploy$worker$Worker$$webUi();
      var10000.foreach$extension(var2, (handler) -> {
         $anonfun$onStart$4(var1, handler);
         return BoxedUnit.UNIT;
      });
   }

   private void setupWorkerResources() {
      try {
         this.resources_$eq(ResourceUtils$.MODULE$.getOrDiscoverAllResources(this.conf(), org.apache.spark.internal.config.Worker$.MODULE$.SPARK_WORKER_PREFIX(), this.resourceFileOpt));
         ResourceUtils$.MODULE$.logResourceInfo(org.apache.spark.internal.config.Worker$.MODULE$.SPARK_WORKER_PREFIX(), this.resources());
      } catch (Exception var2) {
         this.logError((Function0)(() -> "Failed to setup worker resources: "), var2);
         if (!Utils$.MODULE$.isTesting()) {
            System.exit(1);
         }
      }

      this.resources().keys().foreach((rName) -> {
         $anonfun$setupWorkerResources$2(this, rName);
         return BoxedUnit.UNIT;
      });
   }

   public void org$apache$spark$deploy$worker$Worker$$addResourcesUsed(final Map deltaInfo) {
      deltaInfo.foreach((x0$1) -> {
         $anonfun$addResourcesUsed$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private void removeResourcesUsed(final Map deltaInfo) {
      deltaInfo.foreach((x0$1) -> {
         $anonfun$removeResourcesUsed$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void org$apache$spark$deploy$worker$Worker$$changeMaster(final RpcEndpointRef masterRef, final String uiUrl, final RpcAddress masterAddress) {
      this.activeMasterUrl_$eq(masterRef.address().toSparkURL());
      this.activeMasterWebUiUrl_$eq(uiUrl);
      this.masterAddressToConnect_$eq(new Some(masterAddress));
      this.master_$eq(new Some(masterRef));
      this.connected_$eq(true);
      if (this.reverseProxy()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"WorkerWebUI is available at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEB_URL..MODULE$, scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.activeMasterWebUiUrl()), "/"))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"/proxy/", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, this.workerId())}))))));
         String proxyUrl = scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.conf().get(UI$.MODULE$.UI_REVERSE_PROXY_URL().key(), "")), "/");
         System.setProperty("spark.ui.proxyBase", proxyUrl);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.cancelLastRegistrationRetry();
   }

   private Future[] tryRegisterAllMasters() {
      return (Future[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.masterRpcAddresses), (masterAddress) -> this.registerMasterThreadPool().submit(new Runnable(masterAddress) {
            // $FF: synthetic field
            private final Worker $outer;
            private final RpcAddress masterAddress$1;

            public void run() {
               try {
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to master ", "..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.masterAddress$1)})))));
                  RpcEndpointRef masterEndpoint = this.$outer.rpcEnv().setupEndpointRef(this.masterAddress$1, Master$.MODULE$.ENDPOINT_NAME());
                  this.$outer.org$apache$spark$deploy$worker$Worker$$sendRegisterMessageToMaster(masterEndpoint);
               } catch (Throwable var6) {
                  if (var6 instanceof InterruptedException) {
                     BoxedUnit var7 = BoxedUnit.UNIT;
                  } else {
                     if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
                        throw var6;
                     }

                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to connect to master ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.masterAddress$1)})))), var6);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }
               }

            }

            public {
               if (Worker.this == null) {
                  throw null;
               } else {
                  this.$outer = Worker.this;
                  this.masterAddress$1 = masterAddress$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }), scala.reflect.ClassTag..MODULE$.apply(Future.class));
   }

   public void org$apache$spark$deploy$worker$Worker$$reregisterWithMaster() {
      Utils$.MODULE$.tryOrExit((JFunction0.mcV.sp)() -> {
         this.connectionAttemptCount_$eq(this.connectionAttemptCount() + 1);
         if (this.registered()) {
            this.cancelLastRegistrationRetry();
         } else if (this.connectionAttemptCount() <= this.TOTAL_REGISTRATION_RETRIES()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Retrying connection to master (attempt # "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(this.connectionAttemptCount()))}))))));
            Option var2 = this.master();
            if (var2 instanceof Some) {
               Some var3 = (Some)var2;
               RpcEndpointRef masterRef = (RpcEndpointRef)var3.value();
               if (this.registerMasterFutures() != null) {
                  scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.registerMasterFutures()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$reregisterWithMaster$3(x$2)));
               }

               RpcAddress masterAddress = this.preferConfiguredMasterAddress() ? (RpcAddress)this.masterAddressToConnect().get() : masterRef.address();
               this.registerMasterFutures_$eq((Future[])((Object[])(new Future[]{this.registerMasterThreadPool().submit(new Runnable(masterAddress) {
                  // $FF: synthetic field
                  private final Worker $outer;
                  private final RpcAddress masterAddress$2;

                  public void run() {
                     try {
                        this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to master ", "..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.masterAddress$2)})))));
                        RpcEndpointRef masterEndpoint = this.$outer.rpcEnv().setupEndpointRef(this.masterAddress$2, Master$.MODULE$.ENDPOINT_NAME());
                        this.$outer.org$apache$spark$deploy$worker$Worker$$sendRegisterMessageToMaster(masterEndpoint);
                     } catch (Throwable var6) {
                        if (var6 instanceof InterruptedException) {
                           BoxedUnit var7 = BoxedUnit.UNIT;
                        } else {
                           if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
                              throw var6;
                           }

                           this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to connect to master "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.masterAddress$2)}))))), var6);
                           BoxedUnit var10000 = BoxedUnit.UNIT;
                        }
                     }

                  }

                  public {
                     if (Worker.this == null) {
                        throw null;
                     } else {
                        this.$outer = Worker.this;
                        this.masterAddress$2 = masterAddress$2;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return Class.lambdaDeserialize<invokedynamic>(var0);
                  }
               })})));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               if (!scala.None..MODULE$.equals(var2)) {
                  throw new MatchError(var2);
               }

               if (this.registerMasterFutures() != null) {
                  scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.registerMasterFutures()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$reregisterWithMaster$4(x$3)));
               }

               this.registerMasterFutures_$eq(this.tryRegisterAllMasters());
               BoxedUnit var6 = BoxedUnit.UNIT;
            }

            if (this.connectionAttemptCount() == this.INITIAL_REGISTRATION_RETRIES()) {
               this.registrationRetryTimer().foreach((x$4) -> BoxesRunTime.boxToBoolean($anonfun$reregisterWithMaster$5(x$4)));
               this.registrationRetryTimer_$eq(new Some(this.forwardMessageScheduler().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.self().send(DeployMessages.ReregisterWithMaster$.MODULE$)), this.PROLONGED_REGISTRATION_RETRY_INTERVAL_SECONDS(), this.PROLONGED_REGISTRATION_RETRY_INTERVAL_SECONDS(), TimeUnit.SECONDS)));
            }
         } else {
            this.logError((Function0)(() -> "All masters are unresponsive! Giving up."));
            System.exit(1);
         }
      });
   }

   private void cancelLastRegistrationRetry() {
      if (this.registerMasterFutures() != null) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.registerMasterFutures()), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$cancelLastRegistrationRetry$1(x$5)));
         this.registerMasterFutures_$eq((Future[])null);
      }

      this.registrationRetryTimer().foreach((x$6) -> BoxesRunTime.boxToBoolean($anonfun$cancelLastRegistrationRetry$2(x$6)));
      this.registrationRetryTimer_$eq(scala.None..MODULE$);
   }

   public void org$apache$spark$deploy$worker$Worker$$registerWithMaster() {
      Option var2 = this.registrationRetryTimer();
      if (scala.None..MODULE$.equals(var2)) {
         this.registered_$eq(false);
         this.registerMasterFutures_$eq(this.tryRegisterAllMasters());
         this.connectionAttemptCount_$eq(0);
         this.registrationRetryTimer_$eq(new Some(this.forwardMessageScheduler().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> scala.Option..MODULE$.apply(this.self()).foreach((x$7) -> {
                  $anonfun$registerWithMaster$3(x$7);
                  return BoxedUnit.UNIT;
               })), this.INITIAL_REGISTRATION_RETRY_INTERVAL_SECONDS(), this.INITIAL_REGISTRATION_RETRY_INTERVAL_SECONDS(), TimeUnit.SECONDS)));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else if (var2 instanceof Some) {
         this.logInfo((Function0)(() -> "Not spawning another attempt to register with the master, since there is an attempt scheduled already."));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var2);
      }
   }

   private void startExternalShuffleService() {
      try {
         this.org$apache$spark$deploy$worker$Worker$$shuffleService().startIfEnabled();
      } catch (Exception var2) {
         this.logError((Function0)(() -> "Failed to start external shuffle service"), var2);
         System.exit(1);
      }

   }

   public void org$apache$spark$deploy$worker$Worker$$sendRegisterMessageToMaster(final RpcEndpointRef masterEndpoint) {
      masterEndpoint.send(new DeployMessages.RegisterWorker(this.workerId(), this.org$apache$spark$deploy$worker$Worker$$host(), this.org$apache$spark$deploy$worker$Worker$$port(), this.self(), this.org$apache$spark$deploy$worker$Worker$$cores, this.org$apache$spark$deploy$worker$Worker$$memory, this.org$apache$spark$deploy$worker$Worker$$workerWebUiUrl(), masterEndpoint.address(), this.resources()));
   }

   public synchronized void org$apache$spark$deploy$worker$Worker$$handleRegisterResponse(final DeployMessages.RegisterWorkerResponse msg) {
      if (msg instanceof DeployMessages.RegisteredWorker var4) {
         RpcEndpointRef masterRef = var4.master();
         String masterWebUiUrl = var4.masterWebUiUrl();
         RpcAddress masterAddress = var4.masterAddress();
         boolean duplicate = var4.duplicate();
         String preferredMasterAddress = this.preferConfiguredMasterAddress() ? masterAddress.toSparkURL() : masterRef.address().toSparkURL();
         if (duplicate) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Duplicate registration at master "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, preferredMasterAddress)}))))));
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Successfully registered with master ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, preferredMasterAddress)})))));
         this.registered_$eq(true);
         this.org$apache$spark$deploy$worker$Worker$$changeMaster(masterRef, masterWebUiUrl, masterAddress);
         this.forwardMessageScheduler().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.self().send(DeployMessages.SendHeartbeat$.MODULE$)), 0L, this.HEARTBEAT_MILLIS(), TimeUnit.MILLISECONDS);
         if (this.CLEANUP_ENABLED()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker cleanup enabled; old application directories will be deleted in: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.workDir())}))))));
            this.forwardMessageScheduler().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.self().send(DeployMessages.WorkDirCleanup$.MODULE$)), this.CLEANUP_INTERVAL_MILLIS(), this.CLEANUP_INTERVAL_MILLIS(), TimeUnit.MILLISECONDS);
         } else {
            BoxedUnit var15 = BoxedUnit.UNIT;
         }

         Iterable execs = (Iterable)this.executors().values().map((e) -> new ExecutorDescription(e.appId(), e.execId(), e.rpId(), e.cores(), e.memory(), e.state()));
         masterRef.send(new DeployMessages.WorkerLatestState(this.workerId(), execs.toList(), this.drivers().keys().toSeq()));
         BoxedUnit var16 = BoxedUnit.UNIT;
      } else if (msg instanceof DeployMessages.RegisterWorkerFailed var11) {
         String message = var11.message();
         if (!this.registered()) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker registration failed: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, message)})))));
            System.exit(1);
            BoxedUnit var14 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var13 = BoxedUnit.UNIT;
         }
      } else if (DeployMessages.MasterInStandby$.MODULE$.equals(msg)) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(msg);
      }
   }

   public synchronized PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final Worker $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof DeployMessages.RegisterWorkerResponse var8) {
               this.$outer.org$apache$spark$deploy$worker$Worker$$handleRegisterResponse(var8);
               return BoxedUnit.UNIT;
            } else if (DeployMessages.SendHeartbeat$.MODULE$.equals(x1)) {
               if (this.$outer.org$apache$spark$deploy$worker$Worker$$connected()) {
                  this.$outer.org$apache$spark$deploy$worker$Worker$$sendToMaster(new DeployMessages.Heartbeat(this.$outer.workerId(), this.$outer.self()));
                  return BoxedUnit.UNIT;
               } else {
                  return BoxedUnit.UNIT;
               }
            } else if (DeployMessages.WorkDirCleanup$.MODULE$.equals(x1)) {
               Set appIds = ((IterableOnceOps)((IterableOps)this.$outer.executors().values().map((x$8x) -> x$8x.appId())).$plus$plus((IterableOnce)this.$outer.drivers().values().map((x$9x) -> x$9x.driverId()))).toSet();

               BoxedUnit var80;
               try {
                  scala.concurrent.Future cleanupFuture = scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> {
                     File[] appDirs = this.$outer.workDir().listFiles();
                     if (appDirs == null) {
                        throw new IOException("ERROR: Failed to list files in " + this.$outer.workDir());
                     } else {
                        scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])appDirs), (dir) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$4(this, appIds, dir)))), (dir) -> {
                           $anonfun$applyOrElse$5(this, dir);
                           return BoxedUnit.UNIT;
                        });
                     }
                  }, this.$outer.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor());
                  cleanupFuture.failed().foreach((e) -> {
                     $anonfun$applyOrElse$7(this, e);
                     return BoxedUnit.UNIT;
                  }, this.$outer.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor());
                  var80 = BoxedUnit.UNIT;
               } catch (Throwable var72) {
                  if (!(var72 instanceof RejectedExecutionException) || !this.$outer.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor().isShutdown()) {
                     throw var72;
                  }

                  this.$outer.logWarning((Function0)(() -> "Failed to cleanup work dir as executor pool was shutdown"));
                  var80 = BoxedUnit.UNIT;
                  var80 = BoxedUnit.UNIT;
               }

               return var80;
            } else if (x1 instanceof DeployMessages.MasterChanged) {
               DeployMessages.MasterChanged var13 = (DeployMessages.MasterChanged)x1;
               RpcEndpointRef masterRef = var13.master();
               String masterWebUiUrl = var13.masterWebUiUrl();
               this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Master has changed, new master is at "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterRef.address().toSparkURL())}))))));
               this.$outer.org$apache$spark$deploy$worker$Worker$$changeMaster(masterRef, masterWebUiUrl, masterRef.address());
               Iterable executorResponses = (Iterable)this.$outer.executors().values().map((e) -> new DeployMessages.WorkerExecutorStateResponse(new ExecutorDescription(e.appId(), e.execId(), e.rpId(), e.cores(), e.memory(), e.state()), e.resources()));
               Iterable driverResponses = (Iterable)this.$outer.drivers().keys().map((idx) -> new DeployMessages.WorkerDriverStateResponse(idx, ((DriverRunner)this.$outer.drivers().apply(idx)).resources()));
               masterRef.send(new DeployMessages.WorkerSchedulerStateResponse(this.$outer.workerId(), executorResponses.toList(), driverResponses.toSeq()));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.ReconnectWorker) {
               DeployMessages.ReconnectWorker var18 = (DeployMessages.ReconnectWorker)x1;
               String masterUrl = var18.masterUrl();
               this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Master with url ", " requested this worker to reconnect."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterUrl)})))));
               this.$outer.org$apache$spark$deploy$worker$Worker$$registerWithMaster();
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.LaunchExecutor) {
               String masterUrl;
               label173: {
                  DeployMessages.LaunchExecutor var20 = (DeployMessages.LaunchExecutor)x1;
                  masterUrl = var20.masterUrl();
                  String appId = var20.appId();
                  int execId = var20.execId();
                  int rpId = var20.rpId();
                  ApplicationDescription appDesc = var20.appDesc();
                  int cores_ = var20.cores();
                  int memory_ = var20.memory();
                  Map resources_ = var20.resources();
                  String var29 = this.$outer.org$apache$spark$deploy$worker$Worker$$activeMasterUrl();
                  if (masterUrl == null) {
                     if (var29 != null) {
                        break label173;
                     }
                  } else if (!masterUrl.equals(var29)) {
                     break label173;
                  }

                  if (this.$outer.org$apache$spark$deploy$worker$Worker$$decommissioned()) {
                     this.$outer.logWarning((Function0)(() -> "Asked to launch an executor while decommissioned. Not launching executor."));
                     return BoxedUnit.UNIT;
                  }

                  BoxedUnit var78;
                  try {
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to launch executor ", "/", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, BoxesRunTime.boxToInteger(execId))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_DESC..MODULE$, appDesc.name())}))))));
                     File executorDir = new File(this.$outer.workDir(), appId + "/" + execId);
                     if (!executorDir.mkdirs()) {
                        throw new IOException("Failed to create directory " + executorDir);
                     }

                     Seq appLocalDirs = (Seq)this.$outer.appDirectories().getOrElse(appId, () -> {
                        String[] localRootDirs = Utils$.MODULE$.getOrCreateLocalRootDirs(this.$outer.conf());
                        ArraySeq dirs = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])localRootDirs), (dir) -> {
                           Object var10000;
                           try {
                              File appDir = Utils$.MODULE$.createDirectory(dir, "executor");
                              Utils$.MODULE$.chmod700(appDir);
                              var10000 = new Some(appDir.getAbsolutePath());
                           } catch (IOException var4) {
                              this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Ignoring this directory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var4.getMessage())})))));
                              var10000 = scala.None..MODULE$;
                           }

                           return (Option)var10000;
                        }, scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq();
                        if (dirs.isEmpty()) {
                           scala.collection.mutable.ArraySeq.ofRef var10002 = scala.Predef..MODULE$.wrapRefArray((Object[])localRootDirs);
                           throw new IOException("No subfolder can be created in " + var10002.mkString(",") + ".");
                        } else {
                           return dirs;
                        }
                     });
                     this.$outer.appDirectories().update(appId, appLocalDirs);
                     Command x$1 = Worker$.MODULE$.maybeUpdateSSLSettings(appDesc.command(), this.$outer.conf());
                     String x$2 = appDesc.copy$default$1();
                     Option x$3 = appDesc.copy$default$2();
                     String x$4 = appDesc.copy$default$4();
                     ResourceProfile x$5 = appDesc.copy$default$5();
                     Option x$6 = appDesc.copy$default$6();
                     Option x$7 = appDesc.copy$default$7();
                     Option x$8 = appDesc.copy$default$8();
                     String x$9 = appDesc.copy$default$9();
                     ExecutorRunner manager = new ExecutorRunner(appId, execId, appDesc.copy(x$2, x$3, x$1, x$4, x$5, x$6, x$7, x$8, x$9), cores_, memory_, this.$outer.self(), this.$outer.workerId(), this.$outer.org$apache$spark$deploy$worker$Worker$$webUi().scheme(), this.$outer.org$apache$spark$deploy$worker$Worker$$host(), this.$outer.org$apache$spark$deploy$worker$Worker$$webUi().boundPort(), this.$outer.org$apache$spark$deploy$worker$Worker$$publicAddress(), this.$outer.org$apache$spark$deploy$worker$Worker$$sparkHome(), executorDir, this.$outer.org$apache$spark$deploy$worker$Worker$$workerUri(), this.$outer.conf(), appLocalDirs, ExecutorState$.MODULE$.LAUNCHING(), rpId, resources_);
                     this.$outer.executors().update(appId + "/" + execId, manager);
                     manager.start();
                     this.$outer.coresUsed_$eq(this.$outer.coresUsed() + cores_);
                     this.$outer.memoryUsed_$eq(this.$outer.memoryUsed() + memory_);
                     this.$outer.org$apache$spark$deploy$worker$Worker$$addResourcesUsed(resources_);
                     var78 = BoxedUnit.UNIT;
                  } catch (Exception var73) {
                     this.$outer.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to launch executor ", "/"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " for ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, BoxesRunTime.boxToInteger(execId)), new MDC(org.apache.spark.internal.LogKeys.APP_DESC..MODULE$, appDesc.name())}))))), var73);
                     if (this.$outer.executors().contains(appId + "/" + execId)) {
                        ((ExecutorRunner)this.$outer.executors().apply(appId + "/" + execId)).kill();
                        this.$outer.executors().$minus$eq(appId + "/" + execId);
                     } else {
                        var78 = BoxedUnit.UNIT;
                     }

                     this.$outer.org$apache$spark$deploy$worker$Worker$$syncExecutorStateWithMaster(new DeployMessages.ExecutorStateChanged(appId, execId, ExecutorState$.MODULE$.FAILED(), new Some(var73.toString()), scala.None..MODULE$));
                     var78 = BoxedUnit.UNIT;
                  }

                  return var78;
               }

               this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Invalid Master (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterUrl)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"attempted to launch executor."})))).log(scala.collection.immutable.Nil..MODULE$))));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.ExecutorStateChanged) {
               DeployMessages.ExecutorStateChanged var43 = (DeployMessages.ExecutorStateChanged)x1;
               this.$outer.handleExecutorStateChanged(var43);
               return BoxedUnit.UNIT;
            } else if (!(x1 instanceof DeployMessages.KillExecutor)) {
               if (x1 instanceof DeployMessages.LaunchDriver) {
                  DeployMessages.LaunchDriver var53 = (DeployMessages.LaunchDriver)x1;
                  String driverId = var53.driverId();
                  DriverDescription driverDesc = var53.driverDesc();
                  Map resources_ = var53.resources();
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to launch driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
                  SparkConf var10002 = this.$outer.conf();
                  File var10004 = this.$outer.workDir();
                  File var10005 = this.$outer.org$apache$spark$deploy$worker$Worker$$sparkHome();
                  Command x$10 = Worker$.MODULE$.maybeUpdateSSLSettings(driverDesc.command(), this.$outer.conf());
                  String x$11 = driverDesc.copy$default$1();
                  int x$12 = driverDesc.copy$default$2();
                  int x$13 = driverDesc.copy$default$3();
                  boolean x$14 = driverDesc.copy$default$4();
                  Seq x$15 = driverDesc.copy$default$6();
                  DriverRunner driver = new DriverRunner(var10002, driverId, var10004, var10005, driverDesc.copy(x$11, x$12, x$13, x$14, x$10, x$15), this.$outer.self(), this.$outer.org$apache$spark$deploy$worker$Worker$$workerUri(), this.$outer.org$apache$spark$deploy$worker$Worker$$workerWebUiUrl(), this.$outer.securityMgr(), resources_);
                  this.$outer.drivers().update(driverId, driver);
                  driver.start();
                  this.$outer.coresUsed_$eq(this.$outer.coresUsed() + driverDesc.cores());
                  this.$outer.memoryUsed_$eq(this.$outer.memoryUsed() + driverDesc.mem());
                  this.$outer.org$apache$spark$deploy$worker$Worker$$addResourcesUsed(resources_);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.KillDriver) {
                  DeployMessages.KillDriver var64 = (DeployMessages.KillDriver)x1;
                  String driverId = var64.driverId();
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to kill driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
                  Option var66 = this.$outer.drivers().get(driverId);
                  if (var66 instanceof Some) {
                     Some var67 = (Some)var66;
                     DriverRunner runner = (DriverRunner)var67.value();
                     runner.kill();
                     BoxedUnit var75 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var66)) {
                        throw new MatchError(var66);
                     }

                     this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to kill unknown driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
                     BoxedUnit var76 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.DriverStateChanged) {
                  DeployMessages.DriverStateChanged var69 = (DeployMessages.DriverStateChanged)x1;
                  this.$outer.handleDriverStateChanged(var69);
                  return BoxedUnit.UNIT;
               } else if (DeployMessages.ReregisterWithMaster$.MODULE$.equals(x1)) {
                  this.$outer.org$apache$spark$deploy$worker$Worker$$reregisterWithMaster();
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.ApplicationFinished) {
                  DeployMessages.ApplicationFinished var70 = (DeployMessages.ApplicationFinished)x1;
                  String id = var70.id();
                  this.$outer.finishedApps().$plus$eq(id);
                  this.$outer.org$apache$spark$deploy$worker$Worker$$maybeCleanupApplication(id);
                  return BoxedUnit.UNIT;
               } else if (DeployMessages.DecommissionWorker$.MODULE$.equals(x1)) {
                  this.$outer.decommissionSelf();
                  return BoxedUnit.UNIT;
               } else if (DeployMessages.WorkerDecommissionSigReceived$.MODULE$.equals(x1)) {
                  this.$outer.decommissionSelf();
                  this.$outer.org$apache$spark$deploy$worker$Worker$$sendToMaster(new DeployMessages.WorkerDecommissioning(this.$outer.workerId(), this.$outer.self()));
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            } else {
               String masterUrl;
               int execId;
               label175: {
                  DeployMessages.KillExecutor var44 = (DeployMessages.KillExecutor)x1;
                  masterUrl = var44.masterUrl();
                  String appId = var44.appId();
                  execId = var44.execId();
                  String var48 = this.$outer.org$apache$spark$deploy$worker$Worker$$activeMasterUrl();
                  if (masterUrl == null) {
                     if (var48 != null) {
                        break label175;
                     }
                  } else if (!masterUrl.equals(var48)) {
                     break label175;
                  }

                  String fullId = appId + "/" + execId;
                  Option var50 = this.$outer.executors().get(fullId);
                  if (var50 instanceof Some) {
                     Some var51 = (Some)var50;
                     ExecutorRunner executor = (ExecutorRunner)var51.value();
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to kill executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)})))));
                     executor.kill();
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var50)) {
                        throw new MatchError(var50);
                     }

                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to kill unknown executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)})))));
                     BoxedUnit var74 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               }

               this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Invalid Master (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterUrl)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"attempted to kill executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, BoxesRunTime.boxToInteger(execId))}))))));
               return BoxedUnit.UNIT;
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof DeployMessages.RegisterWorkerResponse) {
               return true;
            } else if (DeployMessages.SendHeartbeat$.MODULE$.equals(x1)) {
               return true;
            } else if (DeployMessages.WorkDirCleanup$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof DeployMessages.MasterChanged) {
               return true;
            } else if (x1 instanceof DeployMessages.ReconnectWorker) {
               return true;
            } else if (x1 instanceof DeployMessages.LaunchExecutor) {
               return true;
            } else if (x1 instanceof DeployMessages.ExecutorStateChanged) {
               return true;
            } else if (x1 instanceof DeployMessages.KillExecutor) {
               return true;
            } else if (x1 instanceof DeployMessages.LaunchDriver) {
               return true;
            } else if (x1 instanceof DeployMessages.KillDriver) {
               return true;
            } else if (x1 instanceof DeployMessages.DriverStateChanged) {
               return true;
            } else if (DeployMessages.ReregisterWithMaster$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof DeployMessages.ApplicationFinished) {
               return true;
            } else if (DeployMessages.DecommissionWorker$.MODULE$.equals(x1)) {
               return true;
            } else {
               return DeployMessages.WorkerDecommissionSigReceived$.MODULE$.equals(x1);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$4(final Object $this, final Set appIds$1, final File dir) {
            String appIdFromDir = dir.getName();
            boolean isAppStillRunning = appIds$1.contains(appIdFromDir);
            return dir.isDirectory() && !isAppStillRunning && !Utils$.MODULE$.doesDirectoryContainAnyNewFiles(dir, $this.$outer.org$apache$spark$deploy$worker$Worker$$APP_DATA_RETENTION_SECONDS());
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$5(final Object $this, final File dir) {
            $this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing directory: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dir.getPath())})))));
            Utils$.MODULE$.deleteRecursively(dir);
            if (BoxesRunTime.unboxToBoolean($this.$outer.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_DB_ENABLED())) && BoxesRunTime.unboxToBoolean($this.$outer.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED()))) {
               $this.$outer.org$apache$spark$deploy$worker$Worker$$shuffleService().applicationRemoved(dir.getName());
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$7(final Object $this, final Throwable e) {
            $this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"App dir cleanup failed: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e.getMessage())})))), e);
         }

         public {
            if (Worker.this == null) {
               throw null;
            } else {
               this.$outer = Worker.this;
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
         private final Worker $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (DeployMessages.RequestWorkerState$.MODULE$.equals(x1)) {
               this.context$1.reply(new DeployMessages.WorkerStateResponse(this.$outer.org$apache$spark$deploy$worker$Worker$$host(), this.$outer.org$apache$spark$deploy$worker$Worker$$port(), this.$outer.workerId(), this.$outer.executors().values().toList(), this.$outer.finishedExecutors().values().toList(), this.$outer.drivers().values().toList(), this.$outer.finishedDrivers().values().toList(), this.$outer.org$apache$spark$deploy$worker$Worker$$activeMasterUrl(), this.$outer.org$apache$spark$deploy$worker$Worker$$cores, this.$outer.org$apache$spark$deploy$worker$Worker$$memory, this.$outer.coresUsed(), this.$outer.memoryUsed(), this.$outer.activeMasterWebUiUrl(), this.$outer.resources(), (Map)this.$outer.resourcesUsed().toMap(scala..less.colon.less..MODULE$.refl()).map((x0$1) -> {
                  if (x0$1 != null) {
                     String k = (String)x0$1._1();
                     StandaloneResourceUtils.MutableResourceInfo v = (StandaloneResourceUtils.MutableResourceInfo)x0$1._2();
                     return new Tuple2(k, v.toResourceInformation());
                  } else {
                     throw new MatchError(x0$1);
                  }
               })));
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            return DeployMessages.RequestWorkerState$.MODULE$.equals(x1);
         }

         public {
            if (Worker.this == null) {
               throw null;
            } else {
               this.$outer = Worker.this;
               this.context$1 = context$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void onDisconnected(final RpcAddress remoteAddress) {
      if (this.master().exists((x$10) -> BoxesRunTime.boxToBoolean($anonfun$onDisconnected$1(remoteAddress, x$10))) || this.masterAddressToConnect().contains(remoteAddress)) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " Disassociated !"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REMOTE_ADDRESS..MODULE$, remoteAddress)})))));
         this.masterDisconnected();
      }
   }

   private void masterDisconnected() {
      this.logError((Function0)(() -> "Connection to master failed! Waiting for master to reconnect..."));
      this.connected_$eq(false);
      this.org$apache$spark$deploy$worker$Worker$$registerWithMaster();
   }

   public void org$apache$spark$deploy$worker$Worker$$maybeCleanupApplication(final String id) {
      boolean shouldCleanup = this.finishedApps().contains(id) && !this.executors().values().exists((x$11) -> BoxesRunTime.boxToBoolean($anonfun$maybeCleanupApplication$1(id, x$11)));
      if (shouldCleanup) {
         this.finishedApps().$minus$eq(id);

         try {
            this.appDirectories().remove(id).foreach((dirList) -> {
               $anonfun$maybeCleanupApplication$2(this, id, dirList);
               return BoxedUnit.UNIT;
            });
         } catch (Throwable var6) {
            if (!(var6 instanceof RejectedExecutionException) || !this.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor().isShutdown()) {
               throw var6;
            }

            this.logWarning((Function0)(() -> "Failed to cleanup application as executor pool was shutdown"));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.org$apache$spark$deploy$worker$Worker$$shuffleService().applicationRemoved(id);
      }
   }

   public void org$apache$spark$deploy$worker$Worker$$sendToMaster(final Object message) {
      Option var3 = this.master();
      if (var3 instanceof Some var4) {
         RpcEndpointRef masterRef = (RpcEndpointRef)var4.value();
         masterRef.send(message);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropping ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because the connection to master has not yet been established"})))).log(scala.collection.immutable.Nil..MODULE$))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public void org$apache$spark$deploy$worker$Worker$$syncExecutorStateWithMaster(final DeployMessages.ExecutorStateChanged newState) {
      Option var3 = this.master();
      if (var3 instanceof Some var4) {
         RpcEndpointRef masterRef = (RpcEndpointRef)var4.value();
         String var7 = newState.appId();
         String fullId = var7 + "/" + newState.execId();
         masterRef.ask(newState, scala.reflect.ClassTag..MODULE$.Boolean()).onComplete((x0$1) -> {
            if (x0$1 instanceof Success) {
               return this.executorStateSyncFailureAttempts().remove(fullId);
            } else if (x0$1 instanceof Failure) {
               Failure var7 = (Failure)x0$1;
               Throwable t = var7.exception();
               int failures = BoxesRunTime.unboxToInt(this.executorStateSyncFailureAttempts().getOrElse(fullId, (JFunction0.mcI.sp)() -> 0)) + 1;
               if (failures < this.executorStateSyncMaxAttempts()) {
                  this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to send ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, newState)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to Master ", ", will retry "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterRef)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "/"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILURES..MODULE$, BoxesRunTime.boxToInteger(failures))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.executorStateSyncMaxAttempts()))}))))), t);
                  this.executorStateSyncFailureAttempts().update(fullId, BoxesRunTime.boxToInteger(failures));
                  if (!(t instanceof TimeoutException)) {
                     try {
                        Thread.sleep(this.defaultAskTimeout());
                     } catch (InterruptedException var10) {
                     }
                  }

                  this.self().send(newState);
                  return BoxedUnit.UNIT;
               } else {
                  this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to send ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, newState)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to Master ", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterRef)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " times. Giving up."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.executorStateSyncMaxAttempts()))}))))));
                  System.exit(1);
                  return BoxedUnit.UNIT;
               }
            } else {
               throw new MatchError(x0$1);
            }
         }, this.executorStateSyncFailureHandler());
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropping ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NEW_STATE..MODULE$, newState)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because the connection to master has not yet been established"})))).log(scala.collection.immutable.Nil..MODULE$))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private String generateWorkerId() {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(this.workerIdPattern()), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{Worker$.MODULE$.org$apache$spark$deploy$worker$Worker$$DATE_TIME_FORMATTER().format(Instant.now()), this.org$apache$spark$deploy$worker$Worker$$host(), BoxesRunTime.boxToInteger(this.org$apache$spark$deploy$worker$Worker$$port())}));
   }

   public void onStop() {
      this.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor().shutdownNow();
      this.metricsSystem().report();
      this.cancelLastRegistrationRetry();
      this.forwardMessageScheduler().shutdownNow();
      this.registerMasterThreadPool().shutdownNow();
      this.executors().values().foreach((x$12) -> {
         $anonfun$onStop$1(x$12);
         return BoxedUnit.UNIT;
      });
      this.drivers().values().foreach((x$13) -> {
         $anonfun$onStop$2(x$13);
         return BoxedUnit.UNIT;
      });
      this.org$apache$spark$deploy$worker$Worker$$shuffleService().stop();
      this.org$apache$spark$deploy$worker$Worker$$webUi().stop();
      this.metricsSystem().stop();
   }

   private void trimFinishedExecutorsIfNecessary() {
      if (this.finishedExecutors().size() > this.retainedExecutors()) {
         ((LinkedHashMap)this.finishedExecutors().take(scala.math.package..MODULE$.max(this.finishedExecutors().size() / 10, 1))).foreach((x0$1) -> {
            if (x0$1 != null) {
               String executorId = (String)x0$1._1();
               return this.finishedExecutors().remove(executorId);
            } else {
               throw new MatchError(x0$1);
            }
         });
      }
   }

   private void trimFinishedDriversIfNecessary() {
      if (this.finishedDrivers().size() > this.retainedDrivers()) {
         ((LinkedHashMap)this.finishedDrivers().take(scala.math.package..MODULE$.max(this.finishedDrivers().size() / 10, 1))).foreach((x0$1) -> {
            if (x0$1 != null) {
               String driverId = (String)x0$1._1();
               return this.finishedDrivers().remove(driverId);
            } else {
               throw new MatchError(x0$1);
            }
         });
      }
   }

   public void decommissionSelf() {
      if (BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED())) && !this.org$apache$spark$deploy$worker$Worker$$decommissioned()) {
         this.decommissioned_$eq(true);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommission worker ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, this.workerId())})))));
      } else if (this.org$apache$spark$deploy$worker$Worker$$decommissioned()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker ", " already started decommissioning."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, this.workerId())})))));
      } else {
         this.logWarning((Function0)(() -> "Receive decommission request, but decommission feature is disabled."));
      }
   }

   public void handleDriverStateChanged(final DeployMessages.DriverStateChanged driverStateChanged) {
      String driverId;
      label63: {
         Option exception;
         label65: {
            driverId = driverStateChanged.driverId();
            exception = driverStateChanged.exception();
            Enumeration.Value state = driverStateChanged.state();
            Enumeration.Value var10000 = DriverState$.MODULE$.ERROR();
            if (var10000 == null) {
               if (state == null) {
                  break label65;
               }
            } else if (var10000.equals(state)) {
               break label65;
            }

            label66: {
               var10000 = DriverState$.MODULE$.FAILED();
               if (var10000 == null) {
                  if (state == null) {
                     break label66;
                  }
               } else if (var10000.equals(state)) {
                  break label66;
               }

               label67: {
                  var10000 = DriverState$.MODULE$.FINISHED();
                  if (var10000 == null) {
                     if (state == null) {
                        break label67;
                     }
                  } else if (var10000.equals(state)) {
                     break label67;
                  }

                  label41: {
                     var10000 = DriverState$.MODULE$.KILLED();
                     if (var10000 == null) {
                        if (state == null) {
                           break label41;
                        }
                     } else if (var10000.equals(state)) {
                        break label41;
                     }

                     this.logDebug((Function0)(() -> "Driver " + driverId + " changed state to " + state));
                     BoxedUnit var17 = BoxedUnit.UNIT;
                     break label63;
                  }

                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " was killed by user"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
                  BoxedUnit var18 = BoxedUnit.UNIT;
                  break label63;
               }

               Option var11 = this.registrationRetryTimer();
               if (var11 instanceof Some) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exited successfully while master is disconnected."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  BoxedUnit var19 = BoxedUnit.UNIT;
               } else {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " exited successfully"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
                  BoxedUnit var20 = BoxedUnit.UNIT;
               }

               BoxedUnit var21 = BoxedUnit.UNIT;
               break label63;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " exited with failure"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
            BoxedUnit var22 = BoxedUnit.UNIT;
            break label63;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"failed with unrecoverable exception: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, exception.get())}))))));
         BoxedUnit var23 = BoxedUnit.UNIT;
      }

      this.org$apache$spark$deploy$worker$Worker$$sendToMaster(driverStateChanged);
      DriverRunner driver = (DriverRunner)this.drivers().remove(driverId).get();
      this.finishedDrivers().update(driverId, driver);
      this.trimFinishedDriversIfNecessary();
      this.memoryUsed_$eq(this.memoryUsed() - driver.driverDesc().mem());
      this.coresUsed_$eq(this.coresUsed() - driver.driverDesc().cores());
      this.removeResourcesUsed(driver.resources());
   }

   public void handleExecutorStateChanged(final DeployMessages.ExecutorStateChanged executorStateChanged) {
      this.org$apache$spark$deploy$worker$Worker$$syncExecutorStateWithMaster(executorStateChanged);
      Enumeration.Value state = executorStateChanged.state();
      if (ExecutorState$.MODULE$.isFinished(state)) {
         String appId = executorStateChanged.appId();
         String fullId = appId + "/" + executorStateChanged.execId();
         Option var9 = executorStateChanged.message();
         MessageWithContext var10000;
         if (var9 instanceof Some) {
            Some var10 = (Some)var9;
            String msg = (String)var10.value();
            var10000 = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" message ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, msg)})));
         } else {
            if (!scala.None..MODULE$.equals(var9)) {
               throw new MatchError(var9);
            }

            var10000 = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$);
         }

         MessageWithContext message = var10000;
         Option var13 = executorStateChanged.exitStatus();
         if (var13 instanceof Some) {
            Some var14 = (Some)var13;
            int status = BoxesRunTime.unboxToInt(var14.value());
            var10000 = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" exitStatus ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(status))})));
         } else {
            if (!scala.None..MODULE$.equals(var13)) {
               throw new MatchError(var13);
            }

            var10000 = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$);
         }

         MessageWithContext exitStatus = var10000;
         Option var16 = this.executors().get(fullId);
         if (var16 instanceof Some) {
            Some var17 = (Some)var16;
            ExecutorRunner executor = (ExecutorRunner)var17.value();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " finished with state "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, state)})))).$plus(message).$plus(exitStatus)));
            this.executors().$minus$eq(fullId);
            this.finishedExecutors().update(fullId, executor);
            this.trimFinishedExecutorsIfNecessary();
            this.coresUsed_$eq(this.coresUsed() - executor.cores());
            this.memoryUsed_$eq(this.memoryUsed() - executor.memory());
            this.removeResourcesUsed(executor.resources());
            if (this.CLEANUP_FILES_AFTER_EXECUTOR_EXIT()) {
               this.org$apache$spark$deploy$worker$Worker$$shuffleService().executorRemoved(Integer.toString(executorStateChanged.execId()), appId);
               BoxedUnit var20 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var21 = BoxedUnit.UNIT;
            }
         } else {
            if (!scala.None..MODULE$.equals(var16)) {
               throw new MatchError(var16);
            }

            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unknown Executor ", " finished with state "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, state)})))).$plus(message).$plus(exitStatus)));
            BoxedUnit var22 = BoxedUnit.UNIT;
         }

         this.org$apache$spark$deploy$worker$Worker$$maybeCleanupApplication(appId);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onStart$4(final WorkerWebUI eta$0$1$1, final ServletContextHandler handler) {
      eta$0$1$1.attachHandler(handler);
   }

   // $FF: synthetic method
   public static final void $anonfun$setupWorkerResources$2(final Worker $this, final String rName) {
      $this.resourcesUsed().update(rName, new StandaloneResourceUtils.MutableResourceInfo(rName, new HashSet()));
   }

   // $FF: synthetic method
   public static final void $anonfun$addResourcesUsed$1(final Worker $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         ResourceInformation rInfo = (ResourceInformation)x0$1._2();
         $this.resourcesUsed().update(rName, ((StandaloneResourceUtils.MutableResourceInfo)$this.resourcesUsed().apply(rName)).$plus(rInfo));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$removeResourcesUsed$1(final Worker $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         ResourceInformation rInfo = (ResourceInformation)x0$1._2();
         $this.resourcesUsed().update(rName, ((StandaloneResourceUtils.MutableResourceInfo)$this.resourcesUsed().apply(rName)).$minus(rInfo));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reregisterWithMaster$3(final Future x$2) {
      return x$2.cancel(true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reregisterWithMaster$4(final Future x$3) {
      return x$3.cancel(true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reregisterWithMaster$5(final ScheduledFuture x$4) {
      return x$4.cancel(true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cancelLastRegistrationRetry$1(final Future x$5) {
      return x$5.cancel(true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cancelLastRegistrationRetry$2(final ScheduledFuture x$6) {
      return x$6.cancel(true);
   }

   // $FF: synthetic method
   public static final void $anonfun$registerWithMaster$3(final RpcEndpointRef x$7) {
      x$7.send(DeployMessages.ReregisterWithMaster$.MODULE$);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onDisconnected$1(final RpcAddress remoteAddress$1, final RpcEndpointRef x$10) {
      boolean var3;
      label23: {
         RpcAddress var10000 = x$10.address();
         if (var10000 == null) {
            if (remoteAddress$1 == null) {
               break label23;
            }
         } else if (var10000.equals(remoteAddress$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$maybeCleanupApplication$1(final String id$1, final ExecutorRunner x$11) {
      boolean var3;
      label23: {
         String var10000 = x$11.appId();
         if (var10000 == null) {
            if (id$1 == null) {
               break label23;
            }
         } else if (var10000.equals(id$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$maybeCleanupApplication$5(final String dir) {
      Utils$.MODULE$.deleteRecursively(new File(dir));
   }

   // $FF: synthetic method
   public static final void $anonfun$maybeCleanupApplication$6(final Worker $this, final Seq dirList$1, final Throwable e) {
      $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Clean up app dir ", " failed"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, dirList$1)})))), e);
   }

   // $FF: synthetic method
   public static final void $anonfun$maybeCleanupApplication$2(final Worker $this, final String id$1, final Seq dirList) {
      scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cleaning up local directories for application ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, id$1)})))));
         dirList.foreach((dir) -> {
            $anonfun$maybeCleanupApplication$5(dir);
            return BoxedUnit.UNIT;
         });
      }, $this.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor()).failed().foreach((e) -> {
         $anonfun$maybeCleanupApplication$6($this, dirList, e);
         return BoxedUnit.UNIT;
      }, $this.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor());
   }

   // $FF: synthetic method
   public static final void $anonfun$onStop$1(final ExecutorRunner x$12) {
      x$12.kill();
   }

   // $FF: synthetic method
   public static final void $anonfun$onStop$2(final DriverRunner x$13) {
      x$13.kill();
   }

   public Worker(final RpcEnv rpcEnv, final int webUiPort, final int cores, final int memory, final RpcAddress[] masterRpcAddresses, final String endpointName, final String workDirPath, final SparkConf conf, final SecurityManager securityMgr, final Option resourceFileOpt, final Supplier externalShuffleServiceSupplier) {
      this.rpcEnv = rpcEnv;
      this.webUiPort = webUiPort;
      this.org$apache$spark$deploy$worker$Worker$$cores = cores;
      this.org$apache$spark$deploy$worker$Worker$$memory = memory;
      this.masterRpcAddresses = masterRpcAddresses;
      this.workDirPath = workDirPath;
      this.conf = conf;
      this.securityMgr = securityMgr;
      this.resourceFileOpt = resourceFileOpt;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.org$apache$spark$deploy$worker$Worker$$host = rpcEnv.address().host();
      this.org$apache$spark$deploy$worker$Worker$$port = rpcEnv.address().port();
      this.workerIdPattern = (String)conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_ID_PATTERN());
      Utils$.MODULE$.checkHost(this.org$apache$spark$deploy$worker$Worker$$host());
      scala.Predef..MODULE$.assert(this.org$apache$spark$deploy$worker$Worker$$port() > 0);
      if (BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED()))) {
         String signal = (String)conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_DECOMMISSION_SIGNAL());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering SIG", " handler to trigger decommissioning."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGNAL..MODULE$, signal)})))));
         SignalUtils$.MODULE$.register(signal, this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to register SIG", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGNAL..MODULE$, signal)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"handler - disabling worker decommission feature."})))).log(scala.collection.immutable.Nil..MODULE$)), SignalUtils$.MODULE$.register$default$3(), (JFunction0.mcZ.sp)() -> {
            this.self().send(DeployMessages.WorkerDecommissionSigReceived$.MODULE$);
            return true;
         });
      } else {
         this.logInfo((Function0)(() -> "Worker decommissioning not enabled."));
      }

      this.forwardMessageScheduler = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("worker-forward-message-scheduler");
      this.org$apache$spark$deploy$worker$Worker$$cleanupThreadExecutor = .MODULE$.fromExecutorService(ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("worker-cleanup-thread"));
      this.HEARTBEAT_MILLIS = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_TIMEOUT())) * 1000L / 4L;
      this.INITIAL_REGISTRATION_RETRIES = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_INITIAL_REGISTRATION_RETRIES()));
      this.TOTAL_REGISTRATION_RETRIES = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_MAX_REGISTRATION_RETRIES()));
      if (this.INITIAL_REGISTRATION_RETRIES() > this.TOTAL_REGISTRATION_RETRIES()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.Worker$.MODULE$.WORKER_INITIAL_REGISTRATION_RETRIES().key())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") is capped by "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, BoxesRunTime.boxToInteger(this.INITIAL_REGISTRATION_RETRIES()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.Worker$.MODULE$.WORKER_MAX_REGISTRATION_RETRIES().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.TOTAL_REGISTRATION_RETRIES()))}))))));
      }

      this.FUZZ_MULTIPLIER_INTERVAL_LOWER_BOUND = (double)0.5F;
      Random randomNumberGenerator = new Random(UUID.randomUUID().getMostSignificantBits());
      this.REGISTRATION_RETRY_FUZZ_MULTIPLIER = randomNumberGenerator.nextDouble() + this.FUZZ_MULTIPLIER_INTERVAL_LOWER_BOUND();
      this.INITIAL_REGISTRATION_RETRY_INTERVAL_SECONDS = scala.math.package..MODULE$.round((double)10 * this.REGISTRATION_RETRY_FUZZ_MULTIPLIER());
      this.PROLONGED_REGISTRATION_RETRY_INTERVAL_SECONDS = scala.math.package..MODULE$.round((double)60 * this.REGISTRATION_RETRY_FUZZ_MULTIPLIER());
      this.CLEANUP_ENABLED = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_CLEANUP_ENABLED()));
      this.CLEANUP_INTERVAL_MILLIS = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_CLEANUP_INTERVAL())) * 1000L;
      this.org$apache$spark$deploy$worker$Worker$$APP_DATA_RETENTION_SECONDS = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.APP_DATA_RETENTION()));
      this.CLEANUP_FILES_AFTER_EXECUTOR_EXIT = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_CLEANUP_FILES_AFTER_EXECUTOR_EXIT()));
      this.master = scala.None..MODULE$;
      this.preferConfiguredMasterAddress = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.PREFER_CONFIGURED_MASTER_ADDRESS()));
      this.masterAddressToConnect = scala.None..MODULE$;
      this.org$apache$spark$deploy$worker$Worker$$activeMasterUrl = "";
      this.activeMasterWebUiUrl = "";
      this.org$apache$spark$deploy$worker$Worker$$workerWebUiUrl = "";
      this.org$apache$spark$deploy$worker$Worker$$workerUri = (new RpcEndpointAddress(rpcEnv.address(), endpointName)).toString();
      this.registered = false;
      this.org$apache$spark$deploy$worker$Worker$$connected = false;
      this.org$apache$spark$deploy$worker$Worker$$decommissioned = false;
      this.workerId = this.generateWorkerId();
      File var10001;
      if (scala.sys.package..MODULE$.props().contains(Tests$.MODULE$.IS_TESTING().key())) {
         scala.Predef..MODULE$.assert(scala.sys.package..MODULE$.props().contains("spark.test.home"), () -> "spark.test.home is not set!");
         var10001 = new File((String)scala.sys.package..MODULE$.props().apply("spark.test.home"));
      } else {
         var10001 = new File((String)scala.sys.package..MODULE$.env().getOrElse("SPARK_HOME", () -> "."));
      }

      this.org$apache$spark$deploy$worker$Worker$$sparkHome = var10001;
      this.workDir = null;
      this.finishedExecutors = new LinkedHashMap();
      this.drivers = new HashMap();
      this.executors = new HashMap();
      this.finishedDrivers = new LinkedHashMap();
      this.appDirectories = new HashMap();
      this.finishedApps = new HashSet();
      this.executorStateSyncFailureAttempts = new HashMap();
      this.executorStateSyncMaxAttempts = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_STATE_SYNC_MAX_ATTEMPTS()));
      this.defaultAskTimeout = RpcUtils$.MODULE$.askRpcTimeout(conf).duration().toMillis();
      this.retainedExecutors = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_UI_RETAINED_EXECUTORS()));
      this.retainedDrivers = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_UI_RETAINED_DRIVERS()));
      this.org$apache$spark$deploy$worker$Worker$$shuffleService = externalShuffleServiceSupplier != null ? (ExternalShuffleService)externalShuffleServiceSupplier.get() : new ExternalShuffleService(conf, securityMgr);
      String envVar = conf.getenv("SPARK_PUBLIC_DNS");
      this.org$apache$spark$deploy$worker$Worker$$publicAddress = envVar != null ? envVar : this.org$apache$spark$deploy$worker$Worker$$host();
      this.org$apache$spark$deploy$worker$Worker$$webUi = null;
      this.connectionAttemptCount = 0;
      this.metricsSystem = MetricsSystem$.MODULE$.createMetricsSystem(MetricsSystemInstances$.MODULE$.WORKER(), conf);
      this.workerSource = new WorkerSource(this);
      this.reverseProxy = BoxesRunTime.unboxToBoolean(conf.get(UI$.MODULE$.UI_REVERSE_PROXY()));
      this.registerMasterFutures = null;
      this.registrationRetryTimer = scala.None..MODULE$;
      this.registerMasterThreadPool = ThreadUtils$.MODULE$.newDaemonCachedThreadPool("worker-register-master-threadpool", masterRpcAddresses.length, ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3());
      this.resources = scala.Predef..MODULE$.Map().empty();
      this.coresUsed = 0;
      this.memoryUsed = 0;
      this.resourcesUsed = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
