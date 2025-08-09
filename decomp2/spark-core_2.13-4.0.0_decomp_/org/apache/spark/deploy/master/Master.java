package org.apache.spark.deploy.master;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.ApplicationDescription;
import org.apache.spark.deploy.Command;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.DriverDescription;
import org.apache.spark.deploy.ExecutorDescription;
import org.apache.spark.deploy.ExecutorState$;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.deploy.master.ui.MasterWebUI;
import org.apache.spark.deploy.rest.StandaloneRestServer;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Deploy;
import org.apache.spark.internal.config.Deploy$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.Worker$;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.metrics.MetricsSystem$;
import org.apache.spark.metrics.MetricsSystemInstances$;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.serializer.JavaSerializer;
import org.apache.spark.serializer.Serializer;
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
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019Ee!CA+\u0003/\u0002\u00111LA6\u0011)\tI\n\u0001BC\u0002\u0013\u0005\u0013Q\u0014\u0005\u000b\u0003K\u0003!\u0011!Q\u0001\n\u0005}\u0005BCAT\u0001\t\u0005\t\u0015!\u0003\u0002*\"Q\u0011q\u0016\u0001\u0003\u0002\u0003\u0006I!!-\t\u0015\u0005]\u0006A!b\u0001\n\u0003\tI\f\u0003\u0006\u0002D\u0002\u0011\t\u0011)A\u0005\u0003wC!\"!2\u0001\u0005\u000b\u0007I\u0011AAd\u0011)\ty\r\u0001B\u0001B\u0003%\u0011\u0011\u001a\u0005\b\u0003#\u0004A\u0011AAj\u0011%\t\t\u000f\u0001b\u0001\n\u0013\t\u0019\u000f\u0003\u0005\u0002z\u0002\u0001\u000b\u0011BAs\u0011%\tY\u0010\u0001b\u0001\n\u0013\ti\u0010\u0003\u0005\u0003\u0016\u0001\u0001\u000b\u0011BA\u0000\u0011%\u00119\u0002\u0001b\u0001\n\u0013\ti\u0010\u0003\u0005\u0003\u001a\u0001\u0001\u000b\u0011BA\u0000\u0011%\u0011Y\u0002\u0001b\u0001\n\u0013\u0011i\u0002\u0003\u0005\u0003&\u0001\u0001\u000b\u0011\u0002B\u0010\u0011%\u00119\u0003\u0001b\u0001\n\u0013\u0011I\u0003\u0003\u0005\u0003,\u0001\u0001\u000b\u0011BAY\u0011%\u0011i\u0003\u0001b\u0001\n\u0013\u0011I\u0003\u0003\u0005\u00030\u0001\u0001\u000b\u0011BAY\u0011%\u0011\t\u0004\u0001b\u0001\n\u0013\u0011I\u0003\u0003\u0005\u00034\u0001\u0001\u000b\u0011BAY\u0011%\u0011)\u0004\u0001b\u0001\n\u0013\u0011I\u0003\u0003\u0005\u00038\u0001\u0001\u000b\u0011BAY\u0011%\u0011I\u0004\u0001b\u0001\n\u0013\u0011i\u0002\u0003\u0005\u0003<\u0001\u0001\u000b\u0011\u0002B\u0010\u0011%\u0011i\u0004\u0001b\u0001\n\u0013\ti\u0010\u0003\u0005\u0003@\u0001\u0001\u000b\u0011BA\u0000\u0011%\u0011\t\u0005\u0001b\u0001\n\u0013\u0011I\u0003\u0003\u0005\u0003D\u0001\u0001\u000b\u0011BAY\u0011%\u0011)\u0005\u0001b\u0001\n\u0003\u00119\u0005\u0003\u0005\u0003`\u0001\u0001\u000b\u0011\u0002B%\u0011%\u0011\t\u0007\u0001b\u0001\n\u0003\u0011\u0019\u0007\u0003\u0005\u0003r\u0001\u0001\u000b\u0011\u0002B3\u0011%\u0011\u0019\b\u0001b\u0001\n\u0013\u0011)\b\u0003\u0005\u0003~\u0001\u0001\u000b\u0011\u0002B<\u0011%\u0011y\b\u0001b\u0001\n\u0003\u0011\t\t\u0003\u0005\u0003\u0006\u0002\u0001\u000b\u0011\u0002BB\u0011-\u00119\t\u0001b\u0001\n\u0003\t9F!#\t\u0011\t5\u0005\u0001)A\u0005\u0005\u0017C\u0011Ba$\u0001\u0005\u0004%IA!%\t\u0011\tU\u0005\u0001)A\u0005\u0005'C\u0011Ba&\u0001\u0005\u0004%IA!'\t\u0011\t\r\u0006\u0001)A\u0005\u00057C\u0011B!*\u0001\u0005\u0004%IAa*\t\u0011\t-\u0006\u0001)A\u0005\u0005SC\u0011B!,\u0001\u0005\u0004%IA!\u001e\t\u0011\t=\u0006\u0001)A\u0005\u0005oB\u0011B!-\u0001\u0001\u0004%IA!\u000b\t\u0013\tM\u0006\u00011A\u0005\n\tU\u0006\u0002\u0003Ba\u0001\u0001\u0006K!!-\t\u0013\t\r\u0007A1A\u0005\n\t%\u0002\u0002\u0003Bc\u0001\u0001\u0006I!!-\t\u0013\t\u001d\u0007A1A\u0005\n\t%\u0007\u0002\u0003Bj\u0001\u0001\u0006IAa3\t\u0013\tU\u0007A1A\u0005\n\t]\u0007\u0002\u0003Bn\u0001\u0001\u0006IA!7\t\u0013\tu\u0007A1A\u0005\n\t]\u0007\u0002\u0003Bp\u0001\u0001\u0006IA!7\t\u0013\t\u0005\b\u00011A\u0005\n\t%\u0002\"\u0003Br\u0001\u0001\u0007I\u0011\u0002Bs\u0011!\u0011I\u000f\u0001Q!\n\u0005E\u0006\"\u0003Bv\u0001\t\u0007I\u0011\u0002Bw\u0011!\u0011Y\u0010\u0001Q\u0001\n\t=\b\"\u0003B\u007f\u0001\t\u0007I\u0011\u0002Bw\u0011!\u0011y\u0010\u0001Q\u0001\n\t=\b\"CB\u0001\u0001\t\u0007I\u0011BB\u0002\u0011!\u0019Y\u0001\u0001Q\u0001\n\r\u0015\u0001\"CB\u0007\u0001\u0001\u0007I\u0011BB\b\u0011%\u0019i\u0002\u0001a\u0001\n\u0013\u0019y\u0002\u0003\u0005\u0004$\u0001\u0001\u000b\u0015BB\t\u0011%\u0019)\u0003\u0001b\u0001\n\u0013\ti\u0010\u0003\u0005\u0004(\u0001\u0001\u000b\u0011BA\u0000\u0011-\u0019I\u0003\u0001a\u0001\u0002\u0004%I!!@\t\u0017\r-\u0002\u00011AA\u0002\u0013%1Q\u0006\u0005\f\u0007c\u0001\u0001\u0019!A!B\u0013\ty\u0010C\u0006\u00044\u0001\u0001\r\u0011\"\u0001\u0002X\rU\u0002bCB$\u0001\u0001\u0007I\u0011AA,\u0007\u0013B\u0001b!\u0014\u0001A\u0003&1q\u0007\u0005\u000e\u0007\u001f\u0002\u0001\u0019!a\u0001\n\u0003\t9f!\u0015\t\u001b\re\u0003\u00011AA\u0002\u0013\u0005\u0011qKB.\u0011-\u0019y\u0006\u0001a\u0001\u0002\u0003\u0006Kaa\u0015\t\u0017\r\u0005\u0004\u00011AA\u0002\u0013%11\r\u0005\f\u0007W\u0002\u0001\u0019!a\u0001\n\u0013\u0019i\u0007C\u0006\u0004r\u0001\u0001\r\u0011!Q!\n\r\u0015\u0004bCB:\u0001\u0001\u0007\t\u0019!C\u0005\u0007kB1ba&\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0004\u001a\"Y1q\u0011\u0001A\u0002\u0003\u0005\u000b\u0015BB<\u0011-\u0019)\u000b\u0001a\u0001\u0002\u0004%Iaa*\t\u0017\rU\u0006\u00011AA\u0002\u0013%1q\u0017\u0005\f\u0007g\u0003\u0001\u0019!A!B\u0013\u0019I\u000bC\u0005\u0004D\u0002\u0011\r\u0011\"\u0003\u0004F\"A1Q\u001a\u0001!\u0002\u0013\u00199\rC\u0005\u0004P\u0002\u0011\r\u0011\"\u0003\u0004F\"A1\u0011\u001b\u0001!\u0002\u0013\u00199\rC\u0005\u0004T\u0002\u0011\r\u0011\"\u0003\u0004V\"A1q \u0001!\u0002\u0013\u00199\u000eC\u0005\u0005\u0002\u0001\u0011\r\u0011\"\u0003\u0003*!AA1\u0001\u0001!\u0002\u0013\t\t\fC\u0005\u0005\u0006\u0001\u0011\r\u0011\"\u0001\u0004F\"AAq\u0001\u0001!\u0002\u0013\u00199\rC\u0005\u0005\n\u0001\u0011\r\u0011\"\u0001\u0005\f!AA1\u0003\u0001!\u0002\u0013!i\u0001C\u0005\u0005\u0016\u0001\u0011\r\u0011\"\u0001\u0004F\"AAq\u0003\u0001!\u0002\u0013\u00199\rC\u0005\u0005\u001a\u0001\u0011\r\u0011\"\u0001\u0004F\"AA1\u0004\u0001!\u0002\u0013\u00199\rC\u0005\u0005\u001e\u0001\u0011\r\u0011\"\u0003\u0004F\"AAq\u0004\u0001!\u0002\u0013\u00199\rC\u0005\u0005\"\u0001\u0001\r\u0011\"\u0003\u0005$!IA1\u0007\u0001A\u0002\u0013%AQ\u0007\u0005\t\ts\u0001\u0001\u0015)\u0003\u0005&!IA1\b\u0001A\u0002\u0013%AQ\b\u0005\n\t\u0003\u0002\u0001\u0019!C\u0005\t\u0007B\u0001\u0002b\u0012\u0001A\u0003&Aq\b\u0005\b\t\u0013\u0002A\u0011\tC&\u0011\u001d!i\u0005\u0001C!\t\u0017Bq\u0001b\u0014\u0001\t\u0003\"Y\u0005C\u0004\u0005R\u0001!\t\u0005b\u0013\t\u000f\u0011M\u0003\u0001\"\u0011\u0005V!9AQ\f\u0001\u0005B\u0011}\u0003b\u0002C6\u0001\u0011\u0005CQ\u000e\u0005\b\tc\u0002A\u0011BBc\u0011%!\u0019\b\u0001a\u0001\n\u0013\u0011i\u0002C\u0005\u0005v\u0001\u0001\r\u0011\"\u0003\u0005x!AA1\u0010\u0001!B\u0013\u0011y\u0002C\u0004\u0005~\u0001!I\u0001b \t\u000f\u0011\r\u0006\u0001\"\u0003\u0005L!IAQ\u0015\u0001\u0005\u0002\u0005]Cq\u0015\u0005\b\t?\u0004A\u0011\u0002Cq\u0011\u001d)\u0019\u0001\u0001C\u0005\t\u0017Bq!\"\u0002\u0001\t\u0013)9\u0001C\u0004\u0006\u0018\u0001!I!\"\u0007\t\u000f\u0015E\u0002\u0001\"\u0003\u00064!9Q1\t\u0001\u0005\n\u0015\u0015\u0003bBC&\u0001\u0011%A1\n\u0005\b\u000b\u001b\u0002A\u0011BC(\u0011\u001d)i\u0006\u0001C\u0005\u000b?Bq!b\u0019\u0001\t\u0013))\u0007C\u0004\u0006z\u0001!I!b\u001f\t\u000f\u0015}\u0004\u0001\"\u0003\u0006\u0002\"9Q\u0011\u0012\u0001\u0005\n\u0015-\u0005bBCI\u0001\u0011%Q1\u0013\u0005\n\u000b?\u0003A\u0011AA,\u000bCCq!\"*\u0001\t\u0013)9\u000bC\u0004\u0006,\u0002!\t!\",\t\u000f\u0015m\u0006\u0001\"\u0003\u0006>\"9Qq\u001a\u0001\u0005\n\u0015E\u0007bBCn\u0001\u0011%QQ\u001c\u0005\b\u000bC\u0004A\u0011BCr\u0011\u001d)9\u000f\u0001C\u0005\u000bSDq!b>\u0001\t\u0013!Y\u0005C\u0004\u0006z\u0002!I!b?\t\u000f\u0015}\b\u0001\"\u0003\u0007\u0002!9a\u0011\u0002\u0001\u0005\n\u0019-\u0001b\u0002D\b\u0001\u0011%a\u0011\u0003\u0005\b\r/\u0001A\u0011\u0002D\r\u000f)1)%a\u0016\t\u0002\u0005mcq\t\u0004\u000b\u0003+\n9\u0006#\u0001\u0002\\\u0019%\u0003\u0002CAi\u0003\u0003\"\tAb\u0013\t\u0015\u00195\u0013\u0011\tb\u0001\n\u00031y\u0005C\u0005\u0007V\u0005\u0005\u0003\u0015!\u0003\u0007R!QaqKA!\u0005\u0004%\tAb\u0014\t\u0013\u0019e\u0013\u0011\tQ\u0001\n\u0019E\u0003B\u0003D.\u0003\u0003\u0012\r\u0011\"\u0003\u0007^!IaqNA!A\u0003%aq\f\u0005\t\rc\n\t\u0005\"\u0001\u0007t!Aa1PA!\t\u00031iH\u0001\u0004NCN$XM\u001d\u0006\u0005\u00033\nY&\u0001\u0004nCN$XM\u001d\u0006\u0005\u0003;\ny&\u0001\u0004eKBdw.\u001f\u0006\u0005\u0003C\n\u0019'A\u0003ta\u0006\u00148N\u0003\u0003\u0002f\u0005\u001d\u0014AB1qC\u000eDWM\u0003\u0002\u0002j\u0005\u0019qN]4\u0014\u0013\u0001\ti'!\u001f\u0002\u0006\u0006E\u0005\u0003BA8\u0003kj!!!\u001d\u000b\u0005\u0005M\u0014!B:dC2\f\u0017\u0002BA<\u0003c\u0012a!\u00118z%\u00164\u0007\u0003BA>\u0003\u0003k!!! \u000b\t\u0005}\u0014qL\u0001\u0004eB\u001c\u0017\u0002BAB\u0003{\u0012Q\u0003\u00165sK\u0006$7+\u00194f%B\u001cWI\u001c3q_&tG\u000f\u0005\u0003\u0002\b\u00065UBAAE\u0015\u0011\tY)a\u0018\u0002\u0011%tG/\u001a:oC2LA!a$\u0002\n\n9Aj\\4hS:<\u0007\u0003BAJ\u0003+k!!a\u0016\n\t\u0005]\u0015q\u000b\u0002\u0010\u0019\u0016\fG-\u001a:FY\u0016\u001cG/\u00192mK\u00061!\u000f]2F]Z\u001c\u0001!\u0006\u0002\u0002 B!\u00111PAQ\u0013\u0011\t\u0019+! \u0003\rI\u00038-\u00128w\u0003\u001d\u0011\boY#om\u0002\nq!\u00193ee\u0016\u001c8\u000f\u0005\u0003\u0002|\u0005-\u0016\u0002BAW\u0003{\u0012!B\u00159d\u0003\u0012$'/Z:t\u0003%9XMY+j!>\u0014H\u000f\u0005\u0003\u0002p\u0005M\u0016\u0002BA[\u0003c\u00121!\u00138u\u0003-\u0019XmY;sSRLXj\u001a:\u0016\u0005\u0005m\u0006\u0003BA_\u0003\u007fk!!a\u0018\n\t\u0005\u0005\u0017q\f\u0002\u0010'\u0016\u001cWO]5us6\u000bg.Y4fe\u0006a1/Z2ve&$\u00180T4sA\u0005!1m\u001c8g+\t\tI\r\u0005\u0003\u0002>\u0006-\u0017\u0002BAg\u0003?\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u000b\r|gN\u001a\u0011\u0002\rqJg.\u001b;?)1\t).a6\u0002Z\u0006m\u0017Q\\Ap!\r\t\u0019\n\u0001\u0005\b\u00033K\u0001\u0019AAP\u0011\u001d\t9+\u0003a\u0001\u0003SCq!a,\n\u0001\u0004\t\t\fC\u0004\u00028&\u0001\r!a/\t\u000f\u0005\u0015\u0017\u00021\u0001\u0002J\u0006!bm\u001c:xCJ$W*Z:tC\u001e,G\u000b\u001b:fC\u0012,\"!!:\u0011\t\u0005\u001d\u0018Q_\u0007\u0003\u0003STA!a;\u0002n\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u0005=\u0018\u0011_\u0001\u0005kRLGN\u0003\u0002\u0002t\u0006!!.\u0019<b\u0013\u0011\t90!;\u00031M\u001b\u0007.\u001a3vY\u0016$W\t_3dkR|'oU3sm&\u001cW-A\u000bg_J<\u0018M\u001d3NKN\u001c\u0018mZ3UQJ,\u0017\r\u001a\u0011\u0002\u001f\u0011\u0014\u0018N^3s\u0013\u0012\u0004\u0016\r\u001e;fe:,\"!a@\u0011\t\t\u0005!q\u0002\b\u0005\u0005\u0007\u0011Y\u0001\u0005\u0003\u0003\u0006\u0005ETB\u0001B\u0004\u0015\u0011\u0011I!a'\u0002\rq\u0012xn\u001c;?\u0013\u0011\u0011i!!\u001d\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011\tBa\u0005\u0003\rM#(/\u001b8h\u0015\u0011\u0011i!!\u001d\u0002!\u0011\u0014\u0018N^3s\u0013\u0012\u0004\u0016\r\u001e;fe:\u0004\u0013\u0001D1qa&#\u0007+\u0019;uKJt\u0017!D1qa&#\u0007+\u0019;uKJt\u0007%A\bx_J\\WM\u001d+j[\u0016|W\u000f^'t+\t\u0011y\u0002\u0005\u0003\u0002p\t\u0005\u0012\u0002\u0002B\u0012\u0003c\u0012A\u0001T8oO\u0006\u0001ro\u001c:lKJ$\u0016.\\3pkRl5\u000fI\u0001\u0015e\u0016$\u0018-\u001b8fI\u0006\u0003\b\u000f\\5dCRLwN\\:\u0016\u0005\u0005E\u0016!\u0006:fi\u0006Lg.\u001a3BaBd\u0017nY1uS>t7\u000fI\u0001\u0010e\u0016$\u0018-\u001b8fI\u0012\u0013\u0018N^3sg\u0006\u0001\"/\u001a;bS:,G\r\u0012:jm\u0016\u00148\u000fI\u0001\u000b[\u0006DHI]5wKJ\u001c\u0018aC7bq\u0012\u0013\u0018N^3sg\u0002\n\u0001C]3ba\u0016\u0014\u0018\n^3sCRLwN\\:\u0002#I,\u0017\r]3s\u0013R,'/\u0019;j_:\u001c\b%A\tsK\u000e|g/\u001a:z)&lWm\\;u\u001bN\f!C]3d_Z,'/\u001f+j[\u0016|W\u000f^'tA\u0005a!/Z2pm\u0016\u0014\u00180T8eK\u0006i!/Z2pm\u0016\u0014\u00180T8eK\u0002\n!#\\1y\u000bb,7-\u001e;peJ+GO]5fg\u0006\u0019R.\u0019=Fq\u0016\u001cW\u000f^8s%\u0016$(/[3tA\u00059qo\u001c:lKJ\u001cXC\u0001B%!\u0019\u0011YE!\u0016\u0003Z5\u0011!Q\n\u0006\u0005\u0005\u001f\u0012\t&A\u0004nkR\f'\r\\3\u000b\t\tM\u0013\u0011O\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B,\u0005\u001b\u0012q\u0001S1tQN+G\u000f\u0005\u0003\u0002\u0014\nm\u0013\u0002\u0002B/\u0003/\u0012!bV8sW\u0016\u0014\u0018J\u001c4p\u0003!9xN]6feN\u0004\u0013aB5e)>\f\u0005\u000f]\u000b\u0003\u0005K\u0002\u0002Ba\u0013\u0003h\u0005}(1N\u0005\u0005\u0005S\u0012iEA\u0004ICNDW*\u00199\u0011\t\u0005M%QN\u0005\u0005\u0005_\n9FA\bBaBd\u0017nY1uS>t\u0017J\u001c4p\u0003!IG\rV8BaB\u0004\u0013aC<bSRLgnZ!qaN,\"Aa\u001e\u0011\r\t-#\u0011\u0010B6\u0013\u0011\u0011YH!\u0014\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM]\u0001\ro\u0006LG/\u001b8h\u0003B\u00048\u000fI\u0001\u0005CB\u00048/\u0006\u0002\u0003\u0004B1!1\nB+\u0005W\nQ!\u00199qg\u0002\n!\"\u001b3U_^{'o[3s+\t\u0011Y\t\u0005\u0005\u0003L\t\u001d\u0014q B-\u0003-IG\rV8X_J\\WM\u001d\u0011\u0002\u001f\u0005$GM]3tgR{wk\u001c:lKJ,\"Aa%\u0011\u0011\t-#qMAU\u00053\n\u0001#\u00193ee\u0016\u001c8\u000fV8X_J\\WM\u001d\u0011\u0002\u001b\u0015tG\r]8j]R$v.\u00119q+\t\u0011Y\n\u0005\u0005\u0003L\t\u001d$Q\u0014B6!\u0011\tYHa(\n\t\t\u0005\u0016Q\u0010\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u00039)g\u000e\u001a9pS:$Hk\\!qa\u0002\nA\"\u00193ee\u0016\u001c8\u000fV8BaB,\"A!+\u0011\u0011\t-#qMAU\u0005W\nQ\"\u00193ee\u0016\u001c8\u000fV8BaB\u0004\u0013!D2p[BdW\r^3e\u0003B\u00048/\u0001\bd_6\u0004H.\u001a;fI\u0006\u0003\bo\u001d\u0011\u0002\u001b9,\u0007\u0010^!qa:+XNY3s\u0003EqW\r\u001f;BaBtU/\u001c2fe~#S-\u001d\u000b\u0005\u0005o\u0013i\f\u0005\u0003\u0002p\te\u0016\u0002\u0002B^\u0003c\u0012A!\u00168ji\"I!qX\u001a\u0002\u0002\u0003\u0007\u0011\u0011W\u0001\u0004q\u0012\n\u0014A\u00048fqR\f\u0005\u000f\u001d(v[\n,'\u000fI\u0001\u0010[>$W\u000f\\8BaBtU/\u001c2fe\u0006\u0001Rn\u001c3vY>\f\u0005\u000f\u001d(v[\n,'\u000fI\u0001\bIJLg/\u001a:t+\t\u0011Y\r\u0005\u0004\u0003L\tU#Q\u001a\t\u0005\u0003'\u0013y-\u0003\u0003\u0003R\u0006]#A\u0003#sSZ,'/\u00138g_\u0006AAM]5wKJ\u001c\b%\u0001\td_6\u0004H.\u001a;fI\u0012\u0013\u0018N^3sgV\u0011!\u0011\u001c\t\u0007\u0005\u0017\u0012IH!4\u0002#\r|W\u000e\u001d7fi\u0016$GI]5wKJ\u001c\b%\u0001\bxC&$\u0018N\\4Ee&4XM]:\u0002\u001f]\f\u0017\u000e^5oO\u0012\u0013\u0018N^3sg\u0002\n\u0001C\\3yi\u0012\u0013\u0018N^3s\u001dVl'-\u001a:\u0002)9,\u0007\u0010\u001e#sSZ,'OT;nE\u0016\u0014x\fJ3r)\u0011\u00119La:\t\u0013\t}f(!AA\u0002\u0005E\u0016!\u00058fqR$%/\u001b<fe:+XNY3sA\u0005\u0019R.Y:uKJlU\r\u001e:jGN\u001c\u0016p\u001d;f[V\u0011!q\u001e\t\u0005\u0005c\u001490\u0004\u0002\u0003t*!!Q_A0\u0003\u001diW\r\u001e:jGNLAA!?\u0003t\niQ*\u001a;sS\u000e\u001c8+_:uK6\fA#\\1ti\u0016\u0014X*\u001a;sS\u000e\u001c8+_:uK6\u0004\u0013\u0001G1qa2L7-\u0019;j_:lU\r\u001e:jGN\u001c\u0016p\u001d;f[\u0006I\u0012\r\u001d9mS\u000e\fG/[8o\u001b\u0016$(/[2t'f\u001cH/Z7!\u00031i\u0017m\u001d;feN{WO]2f+\t\u0019)\u0001\u0005\u0003\u0002\u0014\u000e\u001d\u0011\u0002BB\u0005\u0003/\u0012A\"T1ti\u0016\u00148k\\;sG\u0016\fQ\"\\1ti\u0016\u00148k\\;sG\u0016\u0004\u0013!B<fEVKWCAB\t!\u0011\u0019\u0019b!\u0007\u000e\u0005\rU!\u0002BB\f\u0003/\n!!^5\n\t\rm1Q\u0003\u0002\f\u001b\u0006\u001cH/\u001a:XK\n,\u0016*A\u0005xK\n,\u0016n\u0018\u0013fcR!!qWB\u0011\u0011%\u0011ylRA\u0001\u0002\u0004\u0019\t\"\u0001\u0004xK\n,\u0016\u000eI\u0001\n[\u0006\u001cH/\u001a:Ve2\f!\"\\1ti\u0016\u0014XK\u001d7!\u00039i\u0017m\u001d;fe^+'-V5Ve2\f!#\\1ti\u0016\u0014x+\u001a2VSV\u0013Hn\u0018\u0013fcR!!qWB\u0018\u0011%\u0011y\fTA\u0001\u0002\u0004\ty0A\bnCN$XM],fEVKWK\u001d7!\u0003\u0015\u0019H/\u0019;f+\t\u00199\u0004\u0005\u0003\u0004:\r}b\u0002BAJ\u0007wIAa!\u0010\u0002X\u0005i!+Z2pm\u0016\u0014\u0018p\u0015;bi\u0016LAa!\u0011\u0004D\t)a+\u00197vK&!1QIA9\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\u0013M$\u0018\r^3`I\u0015\fH\u0003\u0002B\\\u0007\u0017B\u0011Ba0P\u0003\u0003\u0005\raa\u000e\u0002\rM$\u0018\r^3!\u0003E\u0001XM]:jgR,gnY3F]\u001eLg.Z\u000b\u0003\u0007'\u0002B!a%\u0004V%!1qKA,\u0005E\u0001VM]:jgR,gnY3F]\u001eLg.Z\u0001\u0016a\u0016\u00148/[:uK:\u001cW-\u00128hS:,w\fJ3r)\u0011\u00119l!\u0018\t\u0013\t}&+!AA\u0002\rM\u0013A\u00059feNL7\u000f^3oG\u0016,enZ5oK\u0002\n1\u0003\\3bI\u0016\u0014X\t\\3di&|g.Q4f]R,\"a!\u001a\u0011\t\u0005M5qM\u0005\u0005\u0007S\n9FA\nMK\u0006$WM]#mK\u000e$\u0018n\u001c8BO\u0016tG/A\fmK\u0006$WM]#mK\u000e$\u0018n\u001c8BO\u0016tGo\u0018\u0013fcR!!qWB8\u0011%\u0011y,VA\u0001\u0002\u0004\u0019)'\u0001\u000bmK\u0006$WM]#mK\u000e$\u0018n\u001c8BO\u0016tG\u000fI\u0001\u0017e\u0016\u001cwN^3ss\u000e{W\u000e\u001d7fi&|g\u000eV1tWV\u00111q\u000f\u0019\u0005\u0007s\u001a\u0019\t\u0005\u0004\u0002h\u000em4qP\u0005\u0005\u0007{\nIOA\bTG\",G-\u001e7fI\u001a+H/\u001e:f!\u0011\u0019\tia!\r\u0001\u0011Y1QQ-\u0002\u0002\u0003\u0005)\u0011ABE\u0005\ryF%M\u0001\u0018e\u0016\u001cwN^3ss\u000e{W\u000e\u001d7fi&|g\u000eV1tW\u0002\nBaa#\u0004\u0012B!\u0011qNBG\u0013\u0011\u0019y)!\u001d\u0003\u000f9{G\u000f[5oOB!\u0011qNBJ\u0013\u0011\u0019)*!\u001d\u0003\u0007\u0005s\u00170\u0001\u000esK\u000e|g/\u001a:z\u0007>l\u0007\u000f\\3uS>tG+Y:l?\u0012*\u0017\u000f\u0006\u0003\u00038\u000em\u0005\"\u0003B`1\u0006\u0005\t\u0019ABOa\u0011\u0019yja)\u0011\r\u0005\u001d81PBQ!\u0011\u0019\tia)\u0005\u0019\r\u001551TA\u0001\u0002\u0003\u0015\ta!#\u00023\rDWmY6G_J<vN]6feRKW.Z(viR\u000b7o[\u000b\u0003\u0007S\u0003Daa+\u00040B1\u0011q]B>\u0007[\u0003Ba!!\u00040\u0012Y1\u0011\u0017/\u0002\u0002\u0003\u0005)\u0011ABE\u0005\ryFEM\u0001\u001bG\",7m\u001b$pe^{'o[3s)&lWmT;u)\u0006\u001c8\u000eI\u0001\u001eG\",7m\u001b$pe^{'o[3s)&lWmT;u)\u0006\u001c8n\u0018\u0013fcR!!qWB]\u0011%\u0011ylWA\u0001\u0002\u0004\u0019Y\f\r\u0003\u0004>\u000e\u0005\u0007CBAt\u0007w\u001ay\f\u0005\u0003\u0004\u0002\u000e\u0005G\u0001DBY\u0007s\u000b\t\u0011!A\u0003\u0002\r%\u0015\u0001E:qe\u0016\fGmT;u\tJLg/\u001a:t+\t\u00199\r\u0005\u0003\u0002p\r%\u0017\u0002BBf\u0003c\u0012qAQ8pY\u0016\fg.A\ttaJ,\u0017\rZ(vi\u0012\u0013\u0018N^3sg\u0002\nQb\u001d9sK\u0006$w*\u001e;BaB\u001c\u0018AD:qe\u0016\fGmT;u\u0003B\u00048\u000fI\u0001\u0016o>\u00148.\u001a:TK2,7\r^5p]B{G.[2z+\t\u00199\u000e\u0005\u0003\u0004Z\u000e}b\u0002BBn\u0007stAa!8\u0004t:!1q\\Bx\u001d\u0011\u0019\to!<\u000f\t\r\r81\u001e\b\u0005\u0007K\u001cIO\u0004\u0003\u0003\u0006\r\u001d\u0018BAA5\u0013\u0011\t)'a\u001a\n\t\u0005\u0005\u00141M\u0005\u0005\u0003\u0017\u000by&\u0003\u0003\u0004r\u0006%\u0015AB2p]\u001aLw-\u0003\u0003\u0004v\u000e]\u0018A\u0002#fa2|\u0017P\u0003\u0003\u0004r\u0006%\u0015\u0002BB~\u0007{\fQcV8sW\u0016\u00148+\u001a7fGRLwN\u001c)pY&\u001c\u0017P\u0003\u0003\u0004v\u000e]\u0018AF<pe.,'oU3mK\u000e$\u0018n\u001c8Q_2L7-\u001f\u0011\u0002\u0019\u0011,g-Y;mi\u000e{'/Z:\u0002\u001b\u0011,g-Y;mi\u000e{'/Z:!\u00031\u0011XM^3sg\u0016\u0004&o\u001c=z\u00035\u0011XM^3sg\u0016\u0004&o\u001c=zA\u0005\u0001\u0002.[:u_JL8+\u001a:wKJ,&\u000f\\\u000b\u0003\t\u001b\u0001b!a\u001c\u0005\u0010\u0005}\u0018\u0002\u0002C\t\u0003c\u0012aa\u00149uS>t\u0017!\u00055jgR|'/_*feZ,'/\u0016:mA\u0005\tRo]3BaBt\u0015-\\3Bg\u0006\u0003\b/\u00133\u0002%U\u001cX-\u00119q\u001d\u0006lW-Q:BaBLE\rI\u0001\u0015kN,GI]5wKJLE-Q:BaBt\u0015-\\3\u0002+U\u001cX\r\u0012:jm\u0016\u0014\u0018\nZ!t\u0003B\u0004h*Y7fA\u0005\t\"/Z:u'\u0016\u0014h/\u001a:F]\u0006\u0014G.\u001a3\u0002%I,7\u000f^*feZ,'/\u00128bE2,G\rI\u0001\u000be\u0016\u001cHoU3sm\u0016\u0014XC\u0001C\u0013!\u0019\ty\u0007b\u0004\u0005(A!A\u0011\u0006C\u0018\u001b\t!YC\u0003\u0003\u0005.\u0005m\u0013\u0001\u0002:fgRLA\u0001\"\r\u0005,\t!2\u000b^1oI\u0006dwN\\3SKN$8+\u001a:wKJ\faB]3tiN+'O^3s?\u0012*\u0017\u000f\u0006\u0003\u00038\u0012]\u0002\"\u0003B`a\u0006\u0005\t\u0019\u0001C\u0013\u0003-\u0011Xm\u001d;TKJ4XM\u001d\u0011\u0002'I,7\u000f^*feZ,'OQ8v]\u0012\u0004vN\u001d;\u0016\u0005\u0011}\u0002CBA8\t\u001f\t\t,A\fsKN$8+\u001a:wKJ\u0014u.\u001e8e!>\u0014Ho\u0018\u0013fcR!!q\u0017C#\u0011%\u0011yl]A\u0001\u0002\u0004!y$\u0001\u000bsKN$8+\u001a:wKJ\u0014u.\u001e8e!>\u0014H\u000fI\u0001\b_:\u001cF/\u0019:u)\t\u00119,\u0001\u0004p]N#x\u000e]\u0001\u000eK2,7\r^3e\u0019\u0016\fG-\u001a:\u0002#I,go\\6fI2+\u0017\rZ3sg\"L\u0007/A\u0004sK\u000e,\u0017N^3\u0016\u0005\u0011]\u0003\u0003CA8\t3\u001a\tJa.\n\t\u0011m\u0013\u0011\u000f\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\u0006y!/Z2fSZ,\u0017I\u001c3SKBd\u0017\u0010\u0006\u0003\u0005X\u0011\u0005\u0004b\u0002C2u\u0002\u0007AQM\u0001\bG>tG/\u001a=u!\u0011\tY\bb\u001a\n\t\u0011%\u0014Q\u0010\u0002\u000f%B\u001c7)\u00197m\u0007>tG/\u001a=u\u00039yg\u000eR5tG>tg.Z2uK\u0012$BAa.\u0005p!9\u0011qU>A\u0002\u0005%\u0016aE2b]\u000e{W\u000e\u001d7fi\u0016\u0014VmY8wKJL\u0018a\u0005:fG>4XM]=Ti\u0006\u0014H\u000fV5nK6\u001b\u0018a\u0006:fG>4XM]=Ti\u0006\u0014H\u000fV5nK6\u001bx\fJ3r)\u0011\u00119\f\"\u001f\t\u0013\t}f0!AA\u0002\t}\u0011\u0001\u0006:fG>4XM]=Ti\u0006\u0014H\u000fV5nK6\u001b\b%A\u0007cK\u001eLgNU3d_Z,'/\u001f\u000b\t\u0007\u000f$\t\tb&\u0005\u001e\"AA1QA\u0001\u0001\u0004!))\u0001\u0006ti>\u0014X\rZ!qaN\u0004b\u0001b\"\u0005\u0012\n-d\u0002\u0002CE\t\u001bsAA!\u0002\u0005\f&\u0011\u00111O\u0005\u0005\t\u001f\u000b\t(A\u0004qC\u000e\\\u0017mZ3\n\t\u0011MEQ\u0013\u0002\u0004'\u0016\f(\u0002\u0002CH\u0003cB\u0001\u0002\"'\u0002\u0002\u0001\u0007A1T\u0001\u000egR|'/\u001a3Ee&4XM]:\u0011\r\u0011\u001dE\u0011\u0013Bg\u0011!!y*!\u0001A\u0002\u0011\u0005\u0016!D:u_J,GmV8sW\u0016\u00148\u000f\u0005\u0004\u0005\b\u0012E%\u0011L\u0001\u0011G>l\u0007\u000f\\3uKJ+7m\u001c<fef\fA\u0003[1oI2,'+Z4jgR,'oV8sW\u0016\u0014H\u0003\u0006B\\\tS#i\u000b\"-\u00056\u0012eFQ\u0018Ca\t\u000b$I\r\u0003\u0005\u0005,\u0006\u0015\u0001\u0019AA\u0000\u0003\tIG\r\u0003\u0005\u00050\u0006\u0015\u0001\u0019AA\u0000\u0003)9xN]6fe\"{7\u000f\u001e\u0005\t\tg\u000b)\u00011\u0001\u00022\u0006Qqo\u001c:lKJ\u0004vN\u001d;\t\u0011\u0011]\u0016Q\u0001a\u0001\u0005;\u000b\u0011b^8sW\u0016\u0014(+\u001a4\t\u0011\u0011m\u0016Q\u0001a\u0001\u0003c\u000bQaY8sKND\u0001\u0002b0\u0002\u0006\u0001\u0007\u0011\u0011W\u0001\u0007[\u0016lwN]=\t\u0011\u0011\r\u0017Q\u0001a\u0001\u0003\u007f\fab^8sW\u0016\u0014x+\u001a2VSV\u0013H\u000e\u0003\u0005\u0005H\u0006\u0015\u0001\u0019AAU\u00035i\u0017m\u001d;fe\u0006#GM]3tg\"AA1ZA\u0003\u0001\u0004!i-A\u0005sKN|WO]2fgBA!\u0011\u0001Ch\u0003\u007f$\u0019.\u0003\u0003\u0005R\nM!aA'baB!AQ\u001bCn\u001b\t!9N\u0003\u0003\u0005Z\u0006}\u0013\u0001\u0003:fg>,(oY3\n\t\u0011uGq\u001b\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\\\u0001\u001bg\u000eDW\rZ;mK\u0016CXmY;u_J\u001cxJ\\,pe.,'o\u001d\u000b\r\tG$I\u000f\"<\u0005r\u0012mX\u0011\u0001\t\u0007\u0003_\")/!-\n\t\u0011\u001d\u0018\u0011\u000f\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\t\tW\f9\u00011\u0001\u0003l\u0005\u0019\u0011\r\u001d9\t\u0011\u0011=\u0018q\u0001a\u0001\u0003c\u000bAA\u001d9JI\"AA1_A\u0004\u0001\u0004!)0\u0001\u0007sKN|WO]2f\t\u0016\u001c8\r\u0005\u0003\u0002\u0014\u0012]\u0018\u0002\u0002C}\u0003/\u00121$\u0012=fGV$xN\u001d*fg>,(oY3EKN\u001c'/\u001b9uS>t\u0007\u0002\u0003C\u007f\u0003\u000f\u0001\r\u0001b@\u0002\u001bU\u001c\u0018M\u00197f/>\u00148.\u001a:t!\u0019\ty\u0007\":\u0003Z!A1qZA\u0004\u0001\u0004\u00199-A\fti\u0006\u0014H/\u0012=fGV$xN]:P]^{'o[3sg\u0006\t\u0013\r\u001c7pG\u0006$XmV8sW\u0016\u0014(+Z:pkJ\u001cW\rV8Fq\u0016\u001cW\u000f^8sgRa!qWC\u0005\u000b\u0017)y!\"\u0005\u0006\u0016!AA1^A\u0006\u0001\u0004\u0011Y\u0007\u0003\u0005\u0006\u000e\u0005-\u0001\u0019AAY\u00035\t7o]5h]\u0016$7i\u001c:fg\"AA1_A\u0006\u0001\u0004!)\u0010\u0003\u0005\u0006\u0014\u0005-\u0001\u0019\u0001B-\u0003\u00199xN]6fe\"AAq^A\u0006\u0001\u0004\t\t,A\u0005dC:d\u0015-\u001e8dQRQ1qYC\u000e\u000b;)\t#\"\n\t\u0011\u0015M\u0011Q\u0002a\u0001\u00053B\u0001\"b\b\u0002\u000e\u0001\u0007\u0011\u0011W\u0001\n[\u0016lwN]=SKFD\u0001\"b\t\u0002\u000e\u0001\u0007\u0011\u0011W\u0001\tG>\u0014Xm\u001d*fc\"AQqEA\u0007\u0001\u0004)I#\u0001\u000bsKN|WO]2f%\u0016\fX/\u001b:f[\u0016tGo\u001d\t\u0007\t\u000f#\t*b\u000b\u0011\t\u0011UWQF\u0005\u0005\u000b_!9NA\nSKN|WO]2f%\u0016\fX/\u001b:f[\u0016tG/A\bdC:d\u0015-\u001e8dQ\u0012\u0013\u0018N^3s)\u0019\u00199-\"\u000e\u00068!AQ1CA\b\u0001\u0004\u0011I\u0006\u0003\u0005\u0006:\u0005=\u0001\u0019AC\u001e\u0003\u0011!Wm]2\u0011\t\u0015uRqH\u0007\u0003\u00037JA!\"\u0011\u0002\\\t\tBI]5wKJ$Um]2sSB$\u0018n\u001c8\u0002#\r\fg\u000eT1v]\u000eDW\t_3dkR|'\u000f\u0006\u0004\u0004H\u0016\u001dS\u0011\n\u0005\t\u000b'\t\t\u00021\u0001\u0003Z!AA1_A\t\u0001\u0004!)0\u0001\u0005tG\",G-\u001e7f\u00039a\u0017-\u001e8dQ\u0016CXmY;u_J$bAa.\u0006R\u0015M\u0003\u0002CC\n\u0003+\u0001\rA!\u0017\t\u0011\u0015U\u0013Q\u0003a\u0001\u000b/\nA!\u001a=fGB!\u00111SC-\u0013\u0011)Y&a\u0016\u0003\u0019\u0015CXmY;u_J$Um]2\u0002\u001dI,w-[:uKJ<vN]6feR!1qYC1\u0011!)\u0019\"a\u0006A\u0002\te\u0013A\u00073fG>lW.[:tS>twk\u001c:lKJ\u001cxJ\u001c%pgR\u001cH\u0003BC4\u000bg\u0002B!\"\u001b\u0006p5\u0011Q1\u000e\u0006\u0005\u000b[\n\t0\u0001\u0003mC:<\u0017\u0002BC9\u000bW\u0012q!\u00138uK\u001e,'\u000f\u0003\u0005\u0006v\u0005e\u0001\u0019AC<\u0003%Awn\u001d;oC6,7\u000f\u0005\u0004\u0005\b\u0012E\u0015q`\u0001\u0013I\u0016\u001cw.\\7jgNLwN\\,pe.,'\u000f\u0006\u0003\u00038\u0016u\u0004\u0002CC\n\u00037\u0001\rA!\u0017\u0002\u0019I,Wn\u001c<f/>\u00148.\u001a:\u0015\r\t]V1QCC\u0011!)\u0019\"!\bA\u0002\te\u0003\u0002CCD\u0003;\u0001\r!a@\u0002\u00075\u001cx-\u0001\bsK2\fWO\\2i\tJLg/\u001a:\u0015\t\t]VQ\u0012\u0005\t\u000b\u001f\u000by\u00021\u0001\u0003N\u00061AM]5wKJ\f\u0011c\u0019:fCR,\u0017\t\u001d9mS\u000e\fG/[8o)\u0019\u0011Y'\"&\u0006\u001e\"AQ\u0011HA\u0011\u0001\u0004)9\n\u0005\u0003\u0006>\u0015e\u0015\u0002BCN\u00037\u0012a#\u00119qY&\u001c\u0017\r^5p]\u0012+7o\u0019:jaRLwN\u001c\u0005\t\u000b\u001f\u000b\t\u00031\u0001\u0003\u001e\u0006\u0019\"/Z4jgR,'/\u00119qY&\u001c\u0017\r^5p]R!!qWCR\u0011!!Y/a\tA\u0002\t-\u0014!\u00054j]&\u001c\b.\u00119qY&\u001c\u0017\r^5p]R!!qWCU\u0011!!Y/!\nA\u0002\t-\u0014!\u0005:f[>4X-\u00119qY&\u001c\u0017\r^5p]R1!qWCX\u000bcC\u0001\u0002b;\u0002(\u0001\u0007!1\u000e\u0005\t\u0007g\t9\u00031\u0001\u00064B!QQWB \u001d\u0011\t\u0019*b.\n\t\u0015e\u0016qK\u0001\u0011\u0003B\u0004H.[2bi&|gn\u0015;bi\u0016\fa\u0003[1oI2,'+Z9vKN$X\t_3dkR|'o\u001d\u000b\u0007\u0007\u000f,y,b1\t\u0011\u0015\u0005\u0017\u0011\u0006a\u0001\u0003\u007f\fQ!\u00199q\u0013\u0012D\u0001\"\"2\u0002*\u0001\u0007QqY\u0001\u001ce\u0016\u001cx.\u001e:dKB\u0013xNZ5mKR{Gk\u001c;bY\u0016CXmY:\u0011\u0011\t\u0005AqZCe\u0003c\u0003B\u0001\"6\u0006L&!QQ\u001aCl\u0005=\u0011Vm]8ve\u000e,\u0007K]8gS2,\u0017a\u00055b]\u0012dWmS5mY\u0016CXmY;u_J\u001cHCBBd\u000b',)\u000e\u0003\u0005\u0006B\u0006-\u0002\u0019AA\u0000\u0011!)9.a\u000bA\u0002\u0015e\u0017aC3yK\u000e,Ho\u001c:JIN\u0004b\u0001b\"\u0005\u0012\u0006E\u0016!\u00054pe6\fG/\u0012=fGV$xN]%egR!Q\u0011\\Cp\u0011!)9.!\fA\u0002\u0015]\u0014\u0001D6jY2,\u00050Z2vi>\u0014H\u0003\u0002B\\\u000bKD\u0001\"\"\u0016\u00020\u0001\u0007QqK\u0001\u0011]\u0016<\u0018\t\u001d9mS\u000e\fG/[8o\u0013\u0012$B!a@\u0006l\"AQQ^A\u0019\u0001\u0004)y/\u0001\u0006tk\nl\u0017\u000e\u001e#bi\u0016\u0004B!\"=\u0006t6\u0011\u0011Q^\u0005\u0005\u000bk\fiO\u0001\u0003ECR,\u0017A\u0005;j[\u0016|U\u000f\u001e#fC\u0012<vN]6feN\f1B\\3x\tJLg/\u001a:JIR!\u0011q`C\u007f\u0011!)i/!\u000eA\u0002\u0015=\u0018AE7bs\n,W\u000b\u001d3bi\u0016\f\u0005\u000f\u001d(b[\u0016$b!b\u000f\u0007\u0004\u0019\u0015\u0001\u0002CC\u001d\u0003o\u0001\r!b\u000f\t\u0011\u0019\u001d\u0011q\u0007a\u0001\u0003\u007f\fq!\u00199q\u001d\u0006lW-\u0001\u0007de\u0016\fG/\u001a#sSZ,'\u000f\u0006\u0003\u0003N\u001a5\u0001\u0002CC\u001d\u0003s\u0001\r!b\u000f\u0002\u00191\fWO\\2i\tJLg/\u001a:\u0015\r\t]f1\u0003D\u000b\u0011!)\u0019\"a\u000fA\u0002\te\u0003\u0002CCH\u0003w\u0001\rA!4\u0002\u0019I,Wn\u001c<f\tJLg/\u001a:\u0015\u0011\t]f1\u0004D\u0010\rsA\u0001B\"\b\u0002>\u0001\u0007\u0011q`\u0001\tIJLg/\u001a:JI\"Aa\u0011EA\u001f\u0001\u00041\u0019#\u0001\u0006gS:\fGn\u0015;bi\u0016\u0004BA\"\n\u000749!aq\u0005D\u0018\u001d\u00111IC\"\f\u000f\t\r\u0005h1F\u0005\u0005\u0003;\ny&\u0003\u0003\u0002Z\u0005m\u0013\u0002\u0002D\u0019\u0003/\n1\u0002\u0012:jm\u0016\u00148\u000b^1uK&!aQ\u0007D\u001c\u0005-!%/\u001b<feN#\u0018\r^3\u000b\t\u0019E\u0012q\u000b\u0005\t\rw\ti\u00041\u0001\u0007>\u0005IQ\r_2faRLwN\u001c\t\u0007\u0003_\"yAb\u0010\u0011\t\u0011\u001de\u0011I\u0005\u0005\r\u0007\")JA\u0005Fq\u000e,\u0007\u000f^5p]\u00061Q*Y:uKJ\u0004B!a%\u0002BM1\u0011\u0011IA7\u0003\u000b#\"Ab\u0012\u0002\u0017MK6\u000bV#N?:\u000bU*R\u000b\u0003\r#\u0002B!\"\u001b\u0007T%!!\u0011CC6\u00031\u0019\u0016l\u0015+F\u001b~s\u0015)T#!\u00035)e\n\u0012)P\u0013:#vLT!N\u000b\u0006qQI\u0014#Q\u001f&sEk\u0018(B\u001b\u0016\u0003\u0013a\u0005#B)\u0016{F+S'F?\u001a{%+T!U)\u0016\u0013VC\u0001D0!\u00111\tGb\u001b\u000e\u0005\u0019\r$\u0002\u0002D3\rO\naAZ8s[\u0006$(\u0002\u0002D5\u0003c\fA\u0001^5nK&!aQ\u000eD2\u0005E!\u0015\r^3US6,gi\u001c:nCR$XM]\u0001\u0015\t\u0006#Vi\u0018+J\u001b\u0016{fi\u0014*N\u0003R#VI\u0015\u0011\u0002\t5\f\u0017N\u001c\u000b\u0005\u0005o3)\b\u0003\u0005\u0007x\u0005E\u0003\u0019\u0001D=\u0003)\t'oZ*ue&twm\u001d\t\u0007\u0003_\")/a@\u0002-M$\u0018M\u001d;Sa\u000e,eN^!oI\u0016sG\r]8j]R$\"Bb \u0007\u0006\u001a%eQ\u0012DH!)\tyG\"!\u0002 \u0006EFqH\u0005\u0005\r\u0007\u000b\tH\u0001\u0004UkBdWm\r\u0005\t\r\u000f\u000b\u0019\u00061\u0001\u0002\u0000\u0006!\u0001n\\:u\u0011!1Y)a\u0015A\u0002\u0005E\u0016\u0001\u00029peRD\u0001\"a,\u0002T\u0001\u0007\u0011\u0011\u0017\u0005\t\u0003\u000b\f\u0019\u00061\u0001\u0002J\u0002"
)
public class Master implements ThreadSafeRpcEndpoint, Logging, LeaderElectable {
   private final RpcEnv rpcEnv;
   public final RpcAddress org$apache$spark$deploy$master$Master$$address;
   private final int webUiPort;
   private final SecurityManager securityMgr;
   private final SparkConf conf;
   private final ScheduledExecutorService org$apache$spark$deploy$master$Master$$forwardMessageThread;
   private final String driverIdPattern;
   private final String appIdPattern;
   private final long workerTimeoutMs;
   private final int retainedApplications;
   private final int retainedDrivers;
   private final int maxDrivers;
   private final int reaperIterations;
   private final long org$apache$spark$deploy$master$Master$$recoveryTimeoutMs;
   private final String recoveryMode;
   private final int org$apache$spark$deploy$master$Master$$maxExecutorRetries;
   private final HashSet workers;
   private final HashMap idToApp;
   private final ArrayBuffer waitingApps;
   private final HashSet apps;
   private final HashMap idToWorker;
   private final HashMap addressToWorker;
   private final HashMap endpointToApp;
   private final HashMap addressToApp;
   private final ArrayBuffer org$apache$spark$deploy$master$Master$$completedApps;
   private int nextAppNumber;
   private final int moduloAppNumber;
   private final HashSet org$apache$spark$deploy$master$Master$$drivers;
   private final ArrayBuffer org$apache$spark$deploy$master$Master$$completedDrivers;
   private final ArrayBuffer org$apache$spark$deploy$master$Master$$waitingDrivers;
   private int nextDriverNumber;
   private final MetricsSystem masterMetricsSystem;
   private final MetricsSystem applicationMetricsSystem;
   private final MasterSource masterSource;
   private MasterWebUI org$apache$spark$deploy$master$Master$$webUi;
   private final String org$apache$spark$deploy$master$Master$$masterUrl;
   private String masterWebUiUrl;
   private Enumeration.Value state;
   private PersistenceEngine persistenceEngine;
   private LeaderElectionAgent leaderElectionAgent;
   private ScheduledFuture org$apache$spark$deploy$master$Master$$recoveryCompletionTask;
   private ScheduledFuture checkForWorkerTimeOutTask;
   private final boolean spreadOutDrivers;
   private final boolean spreadOutApps;
   private final Enumeration.Value workerSelectionPolicy;
   private final int defaultCores;
   private final boolean reverseProxy;
   private final Option historyServerUrl;
   private final boolean useAppNameAsAppId;
   private final boolean useDriverIdAsAppName;
   private final boolean restServerEnabled;
   private Option restServer;
   private Option org$apache$spark$deploy$master$Master$$restServerBoundPort;
   private long recoveryStartTimeMs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Tuple3 startRpcEnvAndEndpoint(final String host, final int port, final int webUiPort, final SparkConf conf) {
      return Master$.MODULE$.startRpcEnvAndEndpoint(host, port, webUiPort, conf);
   }

   public static void main(final String[] argStrings) {
      Master$.MODULE$.main(argStrings);
   }

   public static String ENDPOINT_NAME() {
      return Master$.MODULE$.ENDPOINT_NAME();
   }

   public static String SYSTEM_NAME() {
      return Master$.MODULE$.SYSTEM_NAME();
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

   public SecurityManager securityMgr() {
      return this.securityMgr;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public ScheduledExecutorService org$apache$spark$deploy$master$Master$$forwardMessageThread() {
      return this.org$apache$spark$deploy$master$Master$$forwardMessageThread;
   }

   private String driverIdPattern() {
      return this.driverIdPattern;
   }

   private String appIdPattern() {
      return this.appIdPattern;
   }

   private long workerTimeoutMs() {
      return this.workerTimeoutMs;
   }

   private int retainedApplications() {
      return this.retainedApplications;
   }

   private int retainedDrivers() {
      return this.retainedDrivers;
   }

   private int maxDrivers() {
      return this.maxDrivers;
   }

   private int reaperIterations() {
      return this.reaperIterations;
   }

   public long org$apache$spark$deploy$master$Master$$recoveryTimeoutMs() {
      return this.org$apache$spark$deploy$master$Master$$recoveryTimeoutMs;
   }

   private String recoveryMode() {
      return this.recoveryMode;
   }

   public int org$apache$spark$deploy$master$Master$$maxExecutorRetries() {
      return this.org$apache$spark$deploy$master$Master$$maxExecutorRetries;
   }

   public HashSet workers() {
      return this.workers;
   }

   public HashMap idToApp() {
      return this.idToApp;
   }

   private ArrayBuffer waitingApps() {
      return this.waitingApps;
   }

   public HashSet apps() {
      return this.apps;
   }

   public HashMap idToWorker() {
      return this.idToWorker;
   }

   private HashMap addressToWorker() {
      return this.addressToWorker;
   }

   private HashMap endpointToApp() {
      return this.endpointToApp;
   }

   private HashMap addressToApp() {
      return this.addressToApp;
   }

   public ArrayBuffer org$apache$spark$deploy$master$Master$$completedApps() {
      return this.org$apache$spark$deploy$master$Master$$completedApps;
   }

   private int nextAppNumber() {
      return this.nextAppNumber;
   }

   private void nextAppNumber_$eq(final int x$1) {
      this.nextAppNumber = x$1;
   }

   private int moduloAppNumber() {
      return this.moduloAppNumber;
   }

   public HashSet org$apache$spark$deploy$master$Master$$drivers() {
      return this.org$apache$spark$deploy$master$Master$$drivers;
   }

   public ArrayBuffer org$apache$spark$deploy$master$Master$$completedDrivers() {
      return this.org$apache$spark$deploy$master$Master$$completedDrivers;
   }

   public ArrayBuffer org$apache$spark$deploy$master$Master$$waitingDrivers() {
      return this.org$apache$spark$deploy$master$Master$$waitingDrivers;
   }

   private int nextDriverNumber() {
      return this.nextDriverNumber;
   }

   private void nextDriverNumber_$eq(final int x$1) {
      this.nextDriverNumber = x$1;
   }

   private MetricsSystem masterMetricsSystem() {
      return this.masterMetricsSystem;
   }

   private MetricsSystem applicationMetricsSystem() {
      return this.applicationMetricsSystem;
   }

   private MasterSource masterSource() {
      return this.masterSource;
   }

   public MasterWebUI org$apache$spark$deploy$master$Master$$webUi() {
      return this.org$apache$spark$deploy$master$Master$$webUi;
   }

   private void webUi_$eq(final MasterWebUI x$1) {
      this.org$apache$spark$deploy$master$Master$$webUi = x$1;
   }

   public String org$apache$spark$deploy$master$Master$$masterUrl() {
      return this.org$apache$spark$deploy$master$Master$$masterUrl;
   }

   private String masterWebUiUrl() {
      return this.masterWebUiUrl;
   }

   private void masterWebUiUrl_$eq(final String x$1) {
      this.masterWebUiUrl = x$1;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public PersistenceEngine persistenceEngine() {
      return this.persistenceEngine;
   }

   public void persistenceEngine_$eq(final PersistenceEngine x$1) {
      this.persistenceEngine = x$1;
   }

   private LeaderElectionAgent leaderElectionAgent() {
      return this.leaderElectionAgent;
   }

   private void leaderElectionAgent_$eq(final LeaderElectionAgent x$1) {
      this.leaderElectionAgent = x$1;
   }

   private ScheduledFuture recoveryCompletionTask() {
      return this.org$apache$spark$deploy$master$Master$$recoveryCompletionTask;
   }

   public void org$apache$spark$deploy$master$Master$$recoveryCompletionTask_$eq(final ScheduledFuture x$1) {
      this.org$apache$spark$deploy$master$Master$$recoveryCompletionTask = x$1;
   }

   private ScheduledFuture checkForWorkerTimeOutTask() {
      return this.checkForWorkerTimeOutTask;
   }

   private void checkForWorkerTimeOutTask_$eq(final ScheduledFuture x$1) {
      this.checkForWorkerTimeOutTask = x$1;
   }

   private boolean spreadOutDrivers() {
      return this.spreadOutDrivers;
   }

   private boolean spreadOutApps() {
      return this.spreadOutApps;
   }

   private Enumeration.Value workerSelectionPolicy() {
      return this.workerSelectionPolicy;
   }

   private int defaultCores() {
      return this.defaultCores;
   }

   public boolean reverseProxy() {
      return this.reverseProxy;
   }

   public Option historyServerUrl() {
      return this.historyServerUrl;
   }

   public boolean useAppNameAsAppId() {
      return this.useAppNameAsAppId;
   }

   public boolean useDriverIdAsAppName() {
      return this.useDriverIdAsAppName;
   }

   private boolean restServerEnabled() {
      return this.restServerEnabled;
   }

   private Option restServer() {
      return this.restServer;
   }

   private void restServer_$eq(final Option x$1) {
      this.restServer = x$1;
   }

   public Option org$apache$spark$deploy$master$Master$$restServerBoundPort() {
      return this.org$apache$spark$deploy$master$Master$$restServerBoundPort;
   }

   private void restServerBoundPort_$eq(final Option x$1) {
      this.org$apache$spark$deploy$master$Master$$restServerBoundPort = x$1;
   }

   public void onStart() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting Spark master at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.org$apache$spark$deploy$master$Master$$masterUrl())})))));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Running Spark version"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_VERSION..MODULE$, package$.MODULE$.SPARK_VERSION())}))))));
      this.webUi_$eq(new MasterWebUI(this, this.webUiPort));
      this.org$apache$spark$deploy$master$Master$$webUi().bind();
      this.masterWebUiUrl_$eq(this.org$apache$spark$deploy$master$Master$$webUi().webUrl());
      if (this.reverseProxy()) {
         Option uiReverseProxyUrl = ((Option)this.conf().get((ConfigEntry)UI$.MODULE$.UI_REVERSE_PROXY_URL())).map((x$7) -> scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(x$7), "/"));
         if (uiReverseProxyUrl.nonEmpty()) {
            System.setProperty("spark.ui.proxyBase", (String)uiReverseProxyUrl.get());
            this.masterWebUiUrl_$eq((String)uiReverseProxyUrl.get() + "/");
         }

         this.org$apache$spark$deploy$master$Master$$webUi().addProxy();
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark Master is acting as a reverse proxy. Master, Workers and "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Applications UIs are available at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEB_URL..MODULE$, this.masterWebUiUrl())}))))));
      }

      this.checkForWorkerTimeOutTask_$eq(this.org$apache$spark$deploy$master$Master$$forwardMessageThread().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.self().send(MasterMessages.CheckForWorkerTimeOut$.MODULE$)), 0L, this.workerTimeoutMs(), TimeUnit.MILLISECONDS));
      if (this.restServerEnabled()) {
         int port = BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.MASTER_REST_SERVER_PORT()));
         String host = (String)((Option)this.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.MASTER_REST_SERVER_HOST())).getOrElse(() -> this.org$apache$spark$deploy$master$Master$$address.host());
         this.restServer_$eq(new Some(new StandaloneRestServer(host, port, this.conf(), this.self(), this.org$apache$spark$deploy$master$Master$$masterUrl())));
      }

      Tuple2 var22;
      label46: {
         this.restServerBoundPort_$eq(this.restServer().map((x$8) -> BoxesRunTime.boxToInteger($anonfun$onStart$8(x$8))));
         this.masterMetricsSystem().registerSource(this.masterSource());
         this.masterMetricsSystem().start(this.masterMetricsSystem().start$default$1());
         this.applicationMetricsSystem().start(this.applicationMetricsSystem().start$default$1());
         ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
         Object var10001 = scala.Predef..MODULE$.refArrayOps(this.masterMetricsSystem().getServletHandlers());
         MasterWebUI var6 = this.org$apache$spark$deploy$master$Master$$webUi();
         var10000.foreach$extension(var10001, (handler) -> {
            $anonfun$onStart$9(var6, handler);
            return BoxedUnit.UNIT;
         });
         var10000 = scala.collection.ArrayOps..MODULE$;
         var10001 = scala.Predef..MODULE$.refArrayOps(this.applicationMetricsSystem().getServletHandlers());
         MasterWebUI var7 = this.org$apache$spark$deploy$master$Master$$webUi();
         var10000.foreach$extension(var10001, (handler) -> {
            $anonfun$onStart$10(var7, handler);
            return BoxedUnit.UNIT;
         });
         JavaSerializer serializer = new JavaSerializer(this.conf());
         String var11 = this.recoveryMode();
         switch (var11 == null ? 0 : var11.hashCode()) {
            case -1087420757:
               if ("FILESYSTEM".equals(var11)) {
                  FileSystemRecoveryModeFactory fsFactory = new FileSystemRecoveryModeFactory(this.conf(), serializer);
                  var22 = new Tuple2(fsFactory.createPersistenceEngine(), fsFactory.createLeaderElectionAgent(this));
                  break label46;
               }
               break;
            case 92309292:
               if ("ZOOKEEPER".equals(var11)) {
                  this.logInfo((Function0)(() -> "Persisting recovery state to ZooKeeper"));
                  ZooKeeperRecoveryModeFactory zkFactory = new ZooKeeperRecoveryModeFactory(this.conf(), serializer);
                  var22 = new Tuple2(zkFactory.createPersistenceEngine(), zkFactory.createLeaderElectionAgent(this));
                  break label46;
               }
               break;
            case 1999208305:
               if ("CUSTOM".equals(var11)) {
                  Class clazz = Utils$.MODULE$.classForName((String)this.conf().get(Deploy$.MODULE$.RECOVERY_MODE_FACTORY()), Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
                  StandaloneRecoveryModeFactory factory = (StandaloneRecoveryModeFactory)clazz.getConstructor(SparkConf.class, Serializer.class).newInstance(this.conf(), serializer);
                  var22 = new Tuple2(factory.createPersistenceEngine(), factory.createLeaderElectionAgent(this));
                  break label46;
               }
               break;
            case 2086752908:
               if ("ROCKSDB".equals(var11)) {
                  RocksDBRecoveryModeFactory rdbFactory = new RocksDBRecoveryModeFactory(this.conf(), serializer);
                  var22 = new Tuple2(rdbFactory.createPersistenceEngine(), rdbFactory.createLeaderElectionAgent(this));
                  break label46;
               }
         }

         var22 = new Tuple2(new BlackHolePersistenceEngine(), new MonarchyLeaderAgent(this));
      }

      Tuple2 var10 = var22;
      if (var10 != null) {
         PersistenceEngine persistenceEngine_ = (PersistenceEngine)var10._1();
         LeaderElectionAgent leaderElectionAgent_ = (LeaderElectionAgent)var10._2();
         Tuple2 var9 = new Tuple2(persistenceEngine_, leaderElectionAgent_);
         PersistenceEngine persistenceEngine_ = (PersistenceEngine)var9._1();
         LeaderElectionAgent leaderElectionAgent_ = (LeaderElectionAgent)var9._2();
         this.persistenceEngine_$eq(persistenceEngine_);
         this.leaderElectionAgent_$eq(leaderElectionAgent_);
      } else {
         throw new MatchError(var10);
      }
   }

   public void onStop() {
      this.masterMetricsSystem().report();
      this.applicationMetricsSystem().report();
      if (this.recoveryCompletionTask() != null) {
         BoxesRunTime.boxToBoolean(this.recoveryCompletionTask().cancel(true));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (this.checkForWorkerTimeOutTask() != null) {
         BoxesRunTime.boxToBoolean(this.checkForWorkerTimeOutTask().cancel(true));
      } else {
         BoxedUnit var1 = BoxedUnit.UNIT;
      }

      this.org$apache$spark$deploy$master$Master$$forwardMessageThread().shutdownNow();
      this.org$apache$spark$deploy$master$Master$$webUi().stop();
      this.restServer().foreach((x$10) -> {
         $anonfun$onStop$1(x$10);
         return BoxedUnit.UNIT;
      });
      this.masterMetricsSystem().stop();
      this.applicationMetricsSystem().stop();
      this.persistenceEngine().close();
      this.leaderElectionAgent().stop();
   }

   public void electedLeader() {
      this.self().send(MasterMessages.ElectedLeader$.MODULE$);
   }

   public void revokedLeadership() {
      this.self().send(MasterMessages.RevokedLeadership$.MODULE$);
   }

   public PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final Master $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (MasterMessages.ElectedLeader$.MODULE$.equals(x1)) {
               Tuple3 var13 = this.$outer.persistenceEngine().readPersistedData(this.$outer.rpcEnv());
               if (var13 == null) {
                  throw new MatchError(var13);
               } else {
                  Seq storedApps = (Seq)var13._1();
                  Seq storedDrivers = (Seq)var13._2();
                  Seq storedWorkers = (Seq)var13._3();
                  Tuple3 var12 = new Tuple3(storedApps, storedDrivers, storedWorkers);
                  Seq storedAppsx = (Seq)var12._1();
                  Seq storedDriversx = (Seq)var12._2();
                  Seq storedWorkersx = (Seq)var12._3();
                  this.$outer.state_$eq(storedAppsx.isEmpty() && storedDriversx.isEmpty() && storedWorkersx.isEmpty() ? RecoveryState$.MODULE$.ALIVE() : RecoveryState$.MODULE$.RECOVERING());
                  this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"I have been elected leader! New state: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RECOVERY_STATE..MODULE$, this.$outer.state())})))));
                  Enumeration.Value var97 = this.$outer.state();
                  Enumeration.Value var20 = RecoveryState$.MODULE$.RECOVERING();
                  if (var97 == null) {
                     if (var20 != null) {
                        return BoxedUnit.UNIT;
                     }
                  } else if (!var97.equals(var20)) {
                     return BoxedUnit.UNIT;
                  }

                  if (this.$outer.org$apache$spark$deploy$master$Master$$beginRecovery(storedAppsx, storedDriversx, storedWorkersx)) {
                     this.$outer.org$apache$spark$deploy$master$Master$$recoveryCompletionTask_$eq(this.$outer.org$apache$spark$deploy$master$Master$$forwardMessageThread().schedule(new Runnable() {
                        // $FF: synthetic field
                        private final <undefinedtype> $outer;

                        public void run() {
                           Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.$outer.org$apache$spark$deploy$master$Master$$anonfun$$$outer().self().send(MasterMessages.CompleteRecovery$.MODULE$));
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
                     }, this.$outer.org$apache$spark$deploy$master$Master$$recoveryTimeoutMs(), TimeUnit.MILLISECONDS));
                     return BoxedUnit.UNIT;
                  } else {
                     return BoxedUnit.UNIT;
                  }
               }
            } else if (MasterMessages.CompleteRecovery$.MODULE$.equals(x1)) {
               this.$outer.org$apache$spark$deploy$master$Master$$completeRecovery();
               return BoxedUnit.UNIT;
            } else if (MasterMessages.RevokedLeadership$.MODULE$.equals(x1)) {
               this.$outer.logError((Function0)(() -> "Leadership has been revoked -- master shutting down."));
               System.exit(0);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.WorkerDecommissioning) {
               RpcEndpointRef workerRef;
               label203: {
                  DeployMessages.WorkerDecommissioning var21 = (DeployMessages.WorkerDecommissioning)x1;
                  String id = var21.id();
                  workerRef = var21.workerRef();
                  Enumeration.Value var96 = this.$outer.state();
                  Enumeration.Value var24 = RecoveryState$.MODULE$.STANDBY();
                  if (var96 == null) {
                     if (var24 == null) {
                        break label203;
                     }
                  } else if (var96.equals(var24)) {
                     break label203;
                  }

                  this.$outer.idToWorker().get(id).foreach((workerxxx) -> {
                     $anonfun$applyOrElse$3(this, workerxxx);
                     return BoxedUnit.UNIT;
                  });
                  return BoxedUnit.UNIT;
               }

               workerRef.send(DeployMessages.MasterInStandby$.MODULE$);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.DecommissionWorkers) {
               Seq ids;
               Predef var95;
               boolean var98;
               label213: {
                  label212: {
                     DeployMessages.DecommissionWorkers var25 = (DeployMessages.DecommissionWorkers)x1;
                     ids = var25.ids();
                     var95 = scala.Predef..MODULE$;
                     Enumeration.Value var10001 = this.$outer.state();
                     Enumeration.Value var27 = RecoveryState$.MODULE$.STANDBY();
                     if (var10001 == null) {
                        if (var27 != null) {
                           break label212;
                        }
                     } else if (!var10001.equals(var27)) {
                        break label212;
                     }

                     var98 = false;
                     break label213;
                  }

                  var98 = true;
               }

               var95.assert(var98);
               ids.foreach((idxx) -> {
                  $anonfun$applyOrElse$4(this, idxx);
                  return BoxedUnit.UNIT;
               });
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.RegisterWorker) {
               DeployMessages.RegisterWorker var28 = (DeployMessages.RegisterWorker)x1;
               String id = var28.id();
               String workerHost = var28.host();
               int workerPort = var28.port();
               RpcEndpointRef workerRef = var28.worker();
               int cores = var28.cores();
               int memory = var28.memory();
               String workerWebUiUrl = var28.workerWebUiUrl();
               RpcAddress masterAddress = var28.masterAddress();
               scala.collection.immutable.Map resources = var28.resources();
               this.$outer.handleRegisterWorker(id, workerHost, workerPort, workerRef, cores, memory, workerWebUiUrl, masterAddress, resources);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.RegisterApplication) {
               DeployMessages.RegisterApplication var38 = (DeployMessages.RegisterApplication)x1;
               ApplicationDescription description = var38.appDescription();
               RpcEndpointRef driver = var38.driver();
               Enumeration.Value var94 = this.$outer.state();
               Enumeration.Value var41 = RecoveryState$.MODULE$.STANDBY();
               if (var94 == null) {
                  if (var41 == null) {
                     return BoxedUnit.UNIT;
                  }
               } else if (var94.equals(var41)) {
                  return BoxedUnit.UNIT;
               }

               this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering app ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_NAME..MODULE$, description.name())})))));
               ApplicationInfo app = this.$outer.org$apache$spark$deploy$master$Master$$createApplication(description, driver);
               this.$outer.registerApplication(app);
               this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registered app ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_NAME..MODULE$, description.name())}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ID ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, app.id())}))))));
               this.$outer.persistenceEngine().addApplication(app);
               driver.send(new DeployMessages.RegisteredApplication(app.id(), this.$outer.self()));
               this.$outer.org$apache$spark$deploy$master$Master$$schedule();
               return BoxedUnit.UNIT;
            } else if (!(x1 instanceof DeployMessages.DriverStateChanged)) {
               if (x1 instanceof DeployMessages.Heartbeat) {
                  DeployMessages.Heartbeat var52 = (DeployMessages.Heartbeat)x1;
                  String workerId = var52.workerId();
                  RpcEndpointRef worker = var52.worker();
                  Option var55 = this.$outer.idToWorker().get(workerId);
                  if (var55 instanceof Some) {
                     Some var56 = (Some)var55;
                     WorkerInfo workerInfo = (WorkerInfo)var56.value();
                     workerInfo.lastHeartbeat_$eq(System.currentTimeMillis());
                     BoxedUnit var91 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var55)) {
                        throw new MatchError(var55);
                     }

                     if (((HashSet)this.$outer.workers().map((x$12) -> x$12.id())).contains(workerId)) {
                        this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got heartbeat from unregistered worker "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Asking it to re-register."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)}))))));
                        worker.send(new DeployMessages.ReconnectWorker(this.$outer.org$apache$spark$deploy$master$Master$$masterUrl()));
                        BoxedUnit var92 = BoxedUnit.UNIT;
                     } else {
                        this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got heartbeat from unregistered worker "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"This worker was never registered, so ignoring the heartbeat."})))).log(scala.collection.immutable.Nil..MODULE$))));
                        BoxedUnit var93 = BoxedUnit.UNIT;
                     }
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.MasterChangeAcknowledged) {
                  DeployMessages.MasterChangeAcknowledged var58 = (DeployMessages.MasterChangeAcknowledged)x1;
                  String appId = var58.appId();
                  Option var60 = this.$outer.idToApp().get(appId);
                  if (var60 instanceof Some) {
                     Some var61 = (Some)var60;
                     ApplicationInfo app = (ApplicationInfo)var61.value();
                     this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application has been re-registered: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)})))));
                     app.state_$eq(ApplicationState$.MODULE$.WAITING());
                     BoxedUnit var89 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var60)) {
                        throw new MatchError(var60);
                     }

                     this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Master change ack from unknown app: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)})))));
                     BoxedUnit var90 = BoxedUnit.UNIT;
                  }

                  if (this.$outer.org$apache$spark$deploy$master$Master$$canCompleteRecovery()) {
                     this.$outer.org$apache$spark$deploy$master$Master$$completeRecovery();
                     return BoxedUnit.UNIT;
                  } else {
                     return BoxedUnit.UNIT;
                  }
               } else if (x1 instanceof DeployMessages.WorkerSchedulerStateResponse) {
                  DeployMessages.WorkerSchedulerStateResponse var63 = (DeployMessages.WorkerSchedulerStateResponse)x1;
                  String workerId = var63.id();
                  List execResponses = var63.execResponses();
                  Seq driverResponses = var63.driverResponses();
                  Option var67 = this.$outer.idToWorker().get(workerId);
                  if (var67 instanceof Some) {
                     Some var68 = (Some)var67;
                     WorkerInfo worker = (WorkerInfo)var68.value();
                     this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker has been re-registered: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)})))));
                     worker.state_$eq(WorkerState$.MODULE$.ALIVE());
                     List validExecutors = execResponses.filter((exec) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$14(this, exec)));
                     validExecutors.foreach((exec) -> {
                        $anonfun$applyOrElse$15(this, worker, exec);
                        return BoxedUnit.UNIT;
                     });
                     driverResponses.foreach((driverx) -> {
                        $anonfun$applyOrElse$16(this, worker, driverx);
                        return BoxedUnit.UNIT;
                     });
                     BoxedUnit var87 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var67)) {
                        throw new MatchError(var67);
                     }

                     this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Scheduler state from unknown worker: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)})))));
                     BoxedUnit var88 = BoxedUnit.UNIT;
                  }

                  if (this.$outer.org$apache$spark$deploy$master$Master$$canCompleteRecovery()) {
                     this.$outer.org$apache$spark$deploy$master$Master$$completeRecovery();
                     return BoxedUnit.UNIT;
                  } else {
                     return BoxedUnit.UNIT;
                  }
               } else if (x1 instanceof DeployMessages.WorkerLatestState) {
                  DeployMessages.WorkerLatestState var71 = (DeployMessages.WorkerLatestState)x1;
                  String workerId = var71.id();
                  Seq executors = var71.executors();
                  Seq driverIds = var71.driverIds();
                  Option var75 = this.$outer.idToWorker().get(workerId);
                  if (var75 instanceof Some) {
                     Some var76 = (Some)var75;
                     WorkerInfo worker = (WorkerInfo)var76.value();
                     executors.foreach((exec) -> {
                        $anonfun$applyOrElse$20(this, worker, exec);
                        return BoxedUnit.UNIT;
                     });
                     driverIds.foreach((driverIdx) -> {
                        $anonfun$applyOrElse$22(worker, driverIdx);
                        return BoxedUnit.UNIT;
                     });
                     BoxedUnit var85 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var75)) {
                        throw new MatchError(var75);
                     }

                     this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker state from unknown worker: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)})))));
                     BoxedUnit var86 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.UnregisterApplication) {
                  DeployMessages.UnregisterApplication var78 = (DeployMessages.UnregisterApplication)x1;
                  String applicationId = var78.appId();
                  this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received unregister request from application"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, applicationId)}))))));
                  this.$outer.idToApp().get(applicationId).foreach((appxx) -> {
                     $anonfun$applyOrElse$26(this, appxx);
                     return BoxedUnit.UNIT;
                  });
                  return BoxedUnit.UNIT;
               } else if (MasterMessages.CheckForWorkerTimeOut$.MODULE$.equals(x1)) {
                  this.$outer.org$apache$spark$deploy$master$Master$$timeOutDeadWorkers();
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            } else {
               String driverId;
               Enumeration.Value state;
               Option exception;
               boolean var83;
               label249: {
                  label267: {
                     DeployMessages.DriverStateChanged var43 = (DeployMessages.DriverStateChanged)x1;
                     driverId = var43.driverId();
                     state = var43.state();
                     exception = var43.exception();
                     Enumeration.Value var10000 = DriverState$.MODULE$.ERROR();
                     if (var10000 == null) {
                        if (state == null) {
                           break label267;
                        }
                     } else if (var10000.equals(state)) {
                        break label267;
                     }

                     label268: {
                        var10000 = DriverState$.MODULE$.FINISHED();
                        if (var10000 == null) {
                           if (state == null) {
                              break label268;
                           }
                        } else if (var10000.equals(state)) {
                           break label268;
                        }

                        label269: {
                           var10000 = DriverState$.MODULE$.KILLED();
                           if (var10000 == null) {
                              if (state == null) {
                                 break label269;
                              }
                           } else if (var10000.equals(state)) {
                              break label269;
                           }

                           label227: {
                              var10000 = DriverState$.MODULE$.FAILED();
                              if (var10000 == null) {
                                 if (state == null) {
                                    break label227;
                                 }
                              } else if (var10000.equals(state)) {
                                 break label227;
                              }

                              var83 = false;
                              break label249;
                           }

                           var83 = true;
                           break label249;
                        }

                        var83 = true;
                        break label249;
                     }

                     var83 = true;
                     break label249;
                  }

                  var83 = true;
               }

               if (var83) {
                  this.$outer.org$apache$spark$deploy$master$Master$$removeDriver(driverId, state, exception);
                  BoxedUnit var84 = BoxedUnit.UNIT;
                  return BoxedUnit.UNIT;
               } else {
                  throw new Exception("Received unexpected state update for driver " + driverId + ": " + state);
               }
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (MasterMessages.ElectedLeader$.MODULE$.equals(x1)) {
               return true;
            } else if (MasterMessages.CompleteRecovery$.MODULE$.equals(x1)) {
               return true;
            } else if (MasterMessages.RevokedLeadership$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof DeployMessages.WorkerDecommissioning) {
               return true;
            } else if (x1 instanceof DeployMessages.DecommissionWorkers) {
               return true;
            } else if (x1 instanceof DeployMessages.RegisterWorker) {
               return true;
            } else if (x1 instanceof DeployMessages.RegisterApplication) {
               return true;
            } else if (x1 instanceof DeployMessages.DriverStateChanged) {
               return true;
            } else if (x1 instanceof DeployMessages.Heartbeat) {
               return true;
            } else if (x1 instanceof DeployMessages.MasterChangeAcknowledged) {
               return true;
            } else if (x1 instanceof DeployMessages.WorkerSchedulerStateResponse) {
               return true;
            } else if (x1 instanceof DeployMessages.WorkerLatestState) {
               return true;
            } else if (x1 instanceof DeployMessages.UnregisterApplication) {
               return true;
            } else {
               return MasterMessages.CheckForWorkerTimeOut$.MODULE$.equals(x1);
            }
         }

         // $FF: synthetic method
         public Master org$apache$spark$deploy$master$Master$$anonfun$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$3(final Object $this, final WorkerInfo worker) {
            $this.$outer.org$apache$spark$deploy$master$Master$$decommissionWorker(worker);
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$5(final Object $this, final WorkerInfo w) {
            $this.$outer.org$apache$spark$deploy$master$Master$$decommissionWorker(w);
            w.endpoint().send(DeployMessages.DecommissionWorker$.MODULE$);
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$4(final Object $this, final String id) {
            $this.$outer.idToWorker().get(id).foreach((w) -> {
               $anonfun$applyOrElse$5($this, w);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$14(final Object $this, final DeployMessages.WorkerExecutorStateResponse exec) {
            return $this.$outer.idToApp().get(exec.desc().appId()).isDefined();
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$15(final Object $this, final WorkerInfo worker$1, final DeployMessages.WorkerExecutorStateResponse exec) {
            Tuple2 var5 = new Tuple2(exec.desc(), exec.resources());
            if (var5 != null) {
               ExecutorDescription execDesc = (ExecutorDescription)var5._1();
               scala.collection.immutable.Map execResources = (scala.collection.immutable.Map)var5._2();
               Tuple2 var4 = new Tuple2(execDesc, execResources);
               ExecutorDescription execDescx = (ExecutorDescription)var4._1();
               scala.collection.immutable.Map execResourcesx = (scala.collection.immutable.Map)var4._2();
               ApplicationInfo app = (ApplicationInfo)$this.$outer.idToApp().apply(execDescx.appId());
               ExecutorDesc execInfo = app.addExecutor(worker$1, execDescx.cores(), execDescx.memoryMb(), execResourcesx, execDescx.rpId(), new Some(BoxesRunTime.boxToInteger(execDescx.execId())));
               worker$1.addExecutor(execInfo);
               worker$1.recoverResources(execResourcesx);
               execInfo.copyState(execDescx);
            } else {
               throw new MatchError(var5);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$17(final String driverId$1, final DriverInfo x$15) {
            boolean var3;
            label23: {
               String var10000 = x$15.id();
               if (var10000 == null) {
                  if (driverId$1 == null) {
                     break label23;
                  }
               } else if (var10000.equals(driverId$1)) {
                  break label23;
               }

               var3 = false;
               return var3;
            }

            var3 = true;
            return var3;
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$18(final WorkerInfo worker$1, final scala.collection.immutable.Map driverResource$1, final DriverInfo driver) {
            driver.worker_$eq(new Some(worker$1));
            driver.state_$eq(DriverState$.MODULE$.RUNNING());
            driver.withResources(driverResource$1);
            worker$1.recoverResources(driverResource$1);
            worker$1.addDriver(driver);
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$16(final Object $this, final WorkerInfo worker$1, final DeployMessages.WorkerDriverStateResponse driver) {
            Tuple2 var5 = new Tuple2(driver.driverId(), driver.resources());
            if (var5 != null) {
               String driverId = (String)var5._1();
               scala.collection.immutable.Map driverResource = (scala.collection.immutable.Map)var5._2();
               Tuple2 var4 = new Tuple2(driverId, driverResource);
               String driverIdx = (String)var4._1();
               scala.collection.immutable.Map driverResourcex = (scala.collection.immutable.Map)var4._2();
               $this.$outer.org$apache$spark$deploy$master$Master$$drivers().find((x$15) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$17(driverIdx, x$15))).foreach((driverx) -> {
                  $anonfun$applyOrElse$18(worker$1, driverResourcex, driverx);
                  return BoxedUnit.UNIT;
               });
            } else {
               throw new MatchError(var5);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$21(final ExecutorDescription exec$1, final Tuple2 x0$1) {
            if (x0$1 == null) {
               throw new MatchError(x0$1);
            } else {
               boolean var6;
               label22: {
                  ExecutorDesc e = (ExecutorDesc)x0$1._2();
                  String var10000 = e.application().id();
                  String var5 = exec$1.appId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label22;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label22;
                  }

                  if (e.id() == exec$1.execId()) {
                     var6 = true;
                     return var6;
                  }
               }

               var6 = false;
               return var6;
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$20(final Object $this, final WorkerInfo worker$2, final ExecutorDescription exec) {
            boolean executorMatches = worker$2.executors().exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$21(exec, x0$1)));
            if (!executorMatches) {
               worker$2.endpoint().send(new DeployMessages.KillExecutor($this.$outer.org$apache$spark$deploy$master$Master$$masterUrl(), exec.appId(), exec.execId()));
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$23(final String driverId$2, final Tuple2 x0$2) {
            if (x0$2 == null) {
               throw new MatchError(x0$2);
            } else {
               boolean var10000;
               label30: {
                  String id = (String)x0$2._1();
                  if (id == null) {
                     if (driverId$2 == null) {
                        break label30;
                     }
                  } else if (id.equals(driverId$2)) {
                     break label30;
                  }

                  var10000 = false;
                  return var10000;
               }

               var10000 = true;
               return var10000;
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$22(final WorkerInfo worker$2, final String driverId) {
            boolean driverMatches = worker$2.drivers().exists((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$23(driverId, x0$2)));
            if (!driverMatches) {
               worker$2.endpoint().send(new DeployMessages.KillDriver(driverId));
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$26(final Object $this, final ApplicationInfo app) {
            $this.$outer.org$apache$spark$deploy$master$Master$$finishApplication(app);
         }

         public {
            if (Master.this == null) {
               throw null;
            } else {
               this.$outer = Master.this;
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
         private final Master $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof DeployMessages.RequestSubmitDriver var8) {
               label156: {
                  DriverDescription description = var8.driverDescription();
                  Enumeration.Value var79 = this.$outer.state();
                  Enumeration.Value var10 = RecoveryState$.MODULE$.ALIVE();
                  if (var79 == null) {
                     if (var10 != null) {
                        break label156;
                     }
                  } else if (!var79.equals(var10)) {
                     break label156;
                  }

                  this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver submitted ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, description.command().mainClass())})))));
                  DriverInfo driver = this.$outer.org$apache$spark$deploy$master$Master$$createDriver(description);
                  this.$outer.persistenceEngine().addDriver(driver);
                  this.$outer.org$apache$spark$deploy$master$Master$$waitingDrivers().$plus$eq(driver);
                  this.$outer.org$apache$spark$deploy$master$Master$$drivers().add(driver);
                  this.$outer.org$apache$spark$deploy$master$Master$$schedule();
                  this.context$1.reply(new DeployMessages.SubmitDriverResponse(this.$outer.self(), true, new Some(driver.id()), "Driver successfully submitted as " + driver.id()));
                  return BoxedUnit.UNIT;
               }

               String var80 = Utils$.MODULE$.BACKUP_STANDALONE_MASTER_PREFIX();
               String msg = var80 + ": " + this.$outer.state() + ". Can only accept driver submissions in ALIVE state.";
               this.context$1.reply(new DeployMessages.SubmitDriverResponse(this.$outer.self(), false, scala.None..MODULE$, msg));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.RequestKillDriver var13) {
               String driverId;
               label249: {
                  driverId = var13.driverId();
                  Enumeration.Value var75 = this.$outer.state();
                  Enumeration.Value var15 = RecoveryState$.MODULE$.ALIVE();
                  if (var75 == null) {
                     if (var15 != null) {
                        break label249;
                     }
                  } else if (!var75.equals(var15)) {
                     break label249;
                  }

                  this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to kill driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
                  Option driver = this.$outer.org$apache$spark$deploy$master$Master$$drivers().find((x$16) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$29(driverId, x$16)));
                  if (driver instanceof Some var19) {
                     DriverInfo d = (DriverInfo)var19.value();
                     if (this.$outer.org$apache$spark$deploy$master$Master$$waitingDrivers().contains(d)) {
                        this.$outer.org$apache$spark$deploy$master$Master$$waitingDrivers().$minus$eq(d);
                        this.$outer.self().send(new DeployMessages.DriverStateChanged(driverId, DriverState$.MODULE$.KILLED(), scala.None..MODULE$));
                     } else {
                        d.worker().foreach((w) -> {
                           $anonfun$applyOrElse$30(driverId, w);
                           return BoxedUnit.UNIT;
                        });
                     }

                     MessageWithContext msg = this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Kill request for ", " submitted"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})));
                     this.$outer.logInfo(.MODULE$.from(() -> msg));
                     this.context$1.reply(new DeployMessages.KillDriverResponse(this.$outer.self(), driverId, true, msg.message()));
                     BoxedUnit var76 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(driver)) {
                        throw new MatchError(driver);
                     }

                     String msg = "Driver " + driverId + " has already finished or does not exist";
                     this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"has already finished or does not exist"})))).log(scala.collection.immutable.Nil..MODULE$))));
                     this.context$1.reply(new DeployMessages.KillDriverResponse(this.$outer.self(), driverId, false, msg));
                     BoxedUnit var77 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               }

               String var78 = Utils$.MODULE$.BACKUP_STANDALONE_MASTER_PREFIX();
               String msg = var78 + ": " + this.$outer.state() + ". Can only kill drivers in ALIVE state.";
               this.context$1.reply(new DeployMessages.KillDriverResponse(this.$outer.self(), driverId, false, msg));
               return BoxedUnit.UNIT;
            } else if (DeployMessages.RequestKillAllDrivers$.MODULE$.equals(x1)) {
               label173: {
                  Enumeration.Value var73 = this.$outer.state();
                  Enumeration.Value var23 = RecoveryState$.MODULE$.ALIVE();
                  if (var73 == null) {
                     if (var23 != null) {
                        break label173;
                     }
                  } else if (!var73.equals(var23)) {
                     break label173;
                  }

                  this.$outer.logInfo((Function0)(() -> "Asked to kill all drivers"));
                  this.$outer.org$apache$spark$deploy$master$Master$$drivers().foreach((dx) -> {
                     $anonfun$applyOrElse$34(this, dx);
                     return BoxedUnit.UNIT;
                  });
                  this.context$1.reply(new DeployMessages.KillAllDriversResponse(this.$outer.self(), true, "Kill request for all drivers submitted"));
                  return BoxedUnit.UNIT;
               }

               String var74 = Utils$.MODULE$.BACKUP_STANDALONE_MASTER_PREFIX();
               String msg = var74 + ": " + this.$outer.state() + ". Can only kill drivers in ALIVE state.";
               this.context$1.reply(new DeployMessages.KillAllDriversResponse(this.$outer.self(), false, msg));
               return BoxedUnit.UNIT;
            } else if (DeployMessages.RequestClearCompletedDriversAndApps$.MODULE$.equals(x1)) {
               int numDrivers = this.$outer.org$apache$spark$deploy$master$Master$$completedDrivers().length();
               int numApps = this.$outer.org$apache$spark$deploy$master$Master$$completedApps().length();
               this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to clear ", " completed drivers and"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_DRIVERS..MODULE$, BoxesRunTime.boxToInteger(numDrivers))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " completed apps."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_APPS..MODULE$, BoxesRunTime.boxToInteger(numApps))}))))));
               this.$outer.org$apache$spark$deploy$master$Master$$completedDrivers().clear();
               this.$outer.org$apache$spark$deploy$master$Master$$completedApps().clear();
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof DeployMessages.RequestDriverStatus) {
               label250: {
                  DeployMessages.RequestDriverStatus var27 = (DeployMessages.RequestDriverStatus)x1;
                  String driverId = var27.driverId();
                  Enumeration.Value var69 = this.$outer.state();
                  Enumeration.Value var29 = RecoveryState$.MODULE$.ALIVE();
                  if (var69 == null) {
                     if (var29 != null) {
                        break label250;
                     }
                  } else if (!var69.equals(var29)) {
                     break label250;
                  }

                  Option var31 = this.$outer.org$apache$spark$deploy$master$Master$$drivers().$plus$plus(this.$outer.org$apache$spark$deploy$master$Master$$completedDrivers()).find((x$17) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$38(driverId, x$17)));
                  if (var31 instanceof Some) {
                     Some var32 = (Some)var31;
                     DriverInfo driver = (DriverInfo)var32.value();
                     this.context$1.reply(new DeployMessages.DriverStatusResponse(true, new Some(driver.state()), driver.worker().map((x$18) -> x$18.id()), driver.worker().map((x$19) -> x$19.hostPort()), driver.exception()));
                     BoxedUnit var70 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var31)) {
                        throw new MatchError(var31);
                     }

                     this.context$1.reply(new DeployMessages.DriverStatusResponse(false, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$));
                     BoxedUnit var71 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               }

               String var72 = Utils$.MODULE$.BACKUP_STANDALONE_MASTER_PREFIX();
               String msg = var72 + ": " + this.$outer.state() + ". Can only request driver status in ALIVE state.";
               this.context$1.reply(new DeployMessages.DriverStatusResponse(false, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, new Some(new Exception(msg))));
               return BoxedUnit.UNIT;
            } else if (DeployMessages.RequestMasterState$.MODULE$.equals(x1)) {
               this.context$1.reply(new DeployMessages.MasterStateResponse(this.$outer.org$apache$spark$deploy$master$Master$$address.host(), this.$outer.org$apache$spark$deploy$master$Master$$address.port(), this.$outer.org$apache$spark$deploy$master$Master$$restServerBoundPort(), (WorkerInfo[])this.$outer.workers().toArray(scala.reflect.ClassTag..MODULE$.apply(WorkerInfo.class)), (ApplicationInfo[])this.$outer.apps().toArray(scala.reflect.ClassTag..MODULE$.apply(ApplicationInfo.class)), (ApplicationInfo[])this.$outer.org$apache$spark$deploy$master$Master$$completedApps().toArray(scala.reflect.ClassTag..MODULE$.apply(ApplicationInfo.class)), (DriverInfo[])this.$outer.org$apache$spark$deploy$master$Master$$drivers().toArray(scala.reflect.ClassTag..MODULE$.apply(DriverInfo.class)), (DriverInfo[])this.$outer.org$apache$spark$deploy$master$Master$$completedDrivers().toArray(scala.reflect.ClassTag..MODULE$.apply(DriverInfo.class)), this.$outer.state()));
               return BoxedUnit.UNIT;
            } else if (DeployMessages.RequestReadyz$.MODULE$.equals(x1)) {
               RpcCallContext var68;
               boolean var82;
               label188: {
                  label187: {
                     var68 = this.context$1;
                     Enumeration.Value var81 = this.$outer.state();
                     Enumeration.Value var34 = RecoveryState$.MODULE$.STANDBY();
                     if (var81 == null) {
                        if (var34 != null) {
                           break label187;
                        }
                     } else if (!var81.equals(var34)) {
                        break label187;
                     }

                     var82 = false;
                     break label188;
                  }

                  var82 = true;
               }

               var68.reply(BoxesRunTime.boxToBoolean(var82));
               return BoxedUnit.UNIT;
            } else if (MasterMessages.BoundPortsRequest$.MODULE$.equals(x1)) {
               this.context$1.reply(new MasterMessages.BoundPortsResponse(this.$outer.org$apache$spark$deploy$master$Master$$address.port(), this.$outer.org$apache$spark$deploy$master$Master$$webUi().boundPort(), this.$outer.org$apache$spark$deploy$master$Master$$restServerBoundPort()));
               return BoxedUnit.UNIT;
            } else {
               if (x1 instanceof DeployMessages.RequestExecutors) {
                  DeployMessages.RequestExecutors var35 = (DeployMessages.RequestExecutors)x1;
                  String appId = var35.appId();
                  scala.collection.immutable.Map resourceProfileToTotalExecs = var35.resourceProfileToTotalExecs();
                  if (resourceProfileToTotalExecs != null) {
                     this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$master$Master$$handleRequestExecutors(appId, resourceProfileToTotalExecs)));
                     return BoxedUnit.UNIT;
                  }
               }

               if (x1 instanceof DeployMessages.KillExecutors) {
                  DeployMessages.KillExecutors var39 = (DeployMessages.KillExecutors)x1;
                  String appId = var39.appId();
                  Seq executorIds = var39.executorIds();
                  Seq formattedExecutorIds = this.$outer.org$apache$spark$deploy$master$Master$$formatExecutorIds(executorIds);
                  this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$master$Master$$handleKillExecutors(appId, formattedExecutorIds)));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof DeployMessages.DecommissionWorkersOnHosts) {
                  Seq hostnames;
                  label195: {
                     DeployMessages.DecommissionWorkersOnHosts var43 = (DeployMessages.DecommissionWorkersOnHosts)x1;
                     hostnames = var43.hostnames();
                     Enumeration.Value var67 = this.$outer.state();
                     Enumeration.Value var45 = RecoveryState$.MODULE$.STANDBY();
                     if (var67 == null) {
                        if (var45 != null) {
                           break label195;
                        }
                     } else if (!var67.equals(var45)) {
                        break label195;
                     }

                     this.context$1.reply(BoxesRunTime.boxToInteger(0));
                     return BoxedUnit.UNIT;
                  }

                  this.context$1.reply(this.$outer.org$apache$spark$deploy$master$Master$$decommissionWorkersOnHosts(hostnames));
                  return BoxedUnit.UNIT;
               } else if (!(x1 instanceof DeployMessages.ExecutorStateChanged)) {
                  return default.apply(x1);
               } else {
                  DeployMessages.ExecutorStateChanged var46 = (DeployMessages.ExecutorStateChanged)x1;
                  String appId = var46.appId();
                  int execId = var46.execId();
                  Enumeration.Value state = var46.state();
                  Option message = var46.message();
                  Option exitStatus = var46.exitStatus();
                  Option execOption = this.$outer.idToApp().get(appId).flatMap((app) -> app.executors().get(BoxesRunTime.boxToInteger(execId)));
                  if (execOption instanceof Some) {
                     ExecutorDesc exec;
                     ApplicationInfo appInfo;
                     Enumeration.Value oldState;
                     label252: {
                        Some var54 = (Some)execOption;
                        exec = (ExecutorDesc)var54.value();
                        appInfo = (ApplicationInfo)this.$outer.idToApp().apply(appId);
                        oldState = exec.state();
                        exec.state_$eq(state);
                        Enumeration.Value var58 = ExecutorState$.MODULE$.RUNNING();
                        if (state == null) {
                           if (var58 != null) {
                              break label252;
                           }
                        } else if (!state.equals(var58)) {
                           break label252;
                        }

                        Predef var10000;
                        boolean var10001;
                        label226: {
                           label225: {
                              var10000 = scala.Predef..MODULE$;
                              Enumeration.Value var59 = ExecutorState$.MODULE$.LAUNCHING();
                              if (oldState == null) {
                                 if (var59 == null) {
                                    break label225;
                                 }
                              } else if (oldState.equals(var59)) {
                                 break label225;
                              }

                              var10001 = false;
                              break label226;
                           }

                           var10001 = true;
                        }

                        var10000.assert(var10001, () -> "executor " + execId + " state transfer from " + oldState + " to RUNNING is illegal");
                        appInfo.resetRetryCount();
                     }

                     exec.application().driver().send(new DeployMessages.ExecutorUpdated(execId, state, message, exitStatus, scala.None..MODULE$));
                     if (ExecutorState$.MODULE$.isFinished(state)) {
                        this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, exec.fullId())}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" because it is ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, state)}))))));
                        if (!appInfo.isFinished()) {
                           appInfo.removeExecutor(exec);
                        }

                        boolean var64;
                        label212: {
                           label211: {
                              exec.worker().removeExecutor(exec);
                              Some var61 = new Some(BoxesRunTime.boxToInteger(0));
                              if (exitStatus == null) {
                                 if (var61 == null) {
                                    break label211;
                                 }
                              } else if (exitStatus.equals(var61)) {
                                 break label211;
                              }

                              var64 = false;
                              break label212;
                           }

                           var64 = true;
                        }

                        boolean normalExit = var64;
                        if (!normalExit) {
                           label266: {
                              Enumeration.Value var62 = ExecutorState$.MODULE$.DECOMMISSIONED();
                              if (oldState == null) {
                                 if (var62 == null) {
                                    break label266;
                                 }
                              } else if (oldState.equals(var62)) {
                                 break label266;
                              }

                              if (appInfo.incrementRetryCount() >= this.$outer.org$apache$spark$deploy$master$Master$$maxExecutorRetries() && this.$outer.org$apache$spark$deploy$master$Master$$maxExecutorRetries() >= 0) {
                                 Iterable execs = appInfo.executors().values();
                                 if (!execs.exists((x$20) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$44(x$20)))) {
                                    this.$outer.logError(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_DESC..MODULE$, appInfo.desc().name())}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ID ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appInfo.id())})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"failed ", " times; removing it"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, BoxesRunTime.boxToInteger(appInfo.retryCount()))}))))));
                                    this.$outer.removeApplication(appInfo, ApplicationState$.MODULE$.FAILED());
                                 }
                              }
                           }
                        }
                     }

                     this.$outer.org$apache$spark$deploy$master$Master$$schedule();
                     BoxedUnit var65 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(execOption)) {
                        throw new MatchError(execOption);
                     }

                     this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got status update for unknown executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"/", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, BoxesRunTime.boxToInteger(execId))}))))));
                     BoxedUnit var66 = BoxedUnit.UNIT;
                  }

                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               }
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof DeployMessages.RequestSubmitDriver) {
               return true;
            } else if (x1 instanceof DeployMessages.RequestKillDriver) {
               return true;
            } else if (DeployMessages.RequestKillAllDrivers$.MODULE$.equals(x1)) {
               return true;
            } else if (DeployMessages.RequestClearCompletedDriversAndApps$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof DeployMessages.RequestDriverStatus) {
               return true;
            } else if (DeployMessages.RequestMasterState$.MODULE$.equals(x1)) {
               return true;
            } else if (DeployMessages.RequestReadyz$.MODULE$.equals(x1)) {
               return true;
            } else if (MasterMessages.BoundPortsRequest$.MODULE$.equals(x1)) {
               return true;
            } else {
               if (x1 instanceof DeployMessages.RequestExecutors) {
                  DeployMessages.RequestExecutors var4 = (DeployMessages.RequestExecutors)x1;
                  scala.collection.immutable.Map resourceProfileToTotalExecs = var4.resourceProfileToTotalExecs();
                  if (resourceProfileToTotalExecs != null) {
                     return true;
                  }
               }

               if (x1 instanceof DeployMessages.KillExecutors) {
                  return true;
               } else if (x1 instanceof DeployMessages.DecommissionWorkersOnHosts) {
                  return true;
               } else {
                  return x1 instanceof DeployMessages.ExecutorStateChanged;
               }
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$29(final String driverId$3, final DriverInfo x$16) {
            boolean var3;
            label23: {
               String var10000 = x$16.id();
               if (var10000 == null) {
                  if (driverId$3 == null) {
                     break label23;
                  }
               } else if (var10000.equals(driverId$3)) {
                  break label23;
               }

               var3 = false;
               return var3;
            }

            var3 = true;
            return var3;
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$30(final String driverId$3, final WorkerInfo w) {
            w.endpoint().send(new DeployMessages.KillDriver(driverId$3));
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$35(final String driverId$4, final WorkerInfo w) {
            w.endpoint().send(new DeployMessages.KillDriver(driverId$4));
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$34(final Object $this, final DriverInfo d) {
            String driverId = d.id();
            if ($this.$outer.org$apache$spark$deploy$master$Master$$waitingDrivers().contains(d)) {
               $this.$outer.org$apache$spark$deploy$master$Master$$waitingDrivers().$minus$eq(d);
               $this.$outer.self().send(new DeployMessages.DriverStateChanged(driverId, DriverState$.MODULE$.KILLED(), scala.None..MODULE$));
            } else {
               d.worker().foreach((w) -> {
                  $anonfun$applyOrElse$35(driverId, w);
                  return BoxedUnit.UNIT;
               });
            }

            $this.$outer.logInfo(.MODULE$.from(() -> $this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Kill request for ", " submitted"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$38(final String driverId$5, final DriverInfo x$17) {
            boolean var3;
            label23: {
               String var10000 = x$17.id();
               if (var10000 == null) {
                  if (driverId$5 == null) {
                     break label23;
                  }
               } else if (var10000.equals(driverId$5)) {
                  break label23;
               }

               var3 = false;
               return var3;
            }

            var3 = true;
            return var3;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$44(final ExecutorDesc x$20) {
            boolean var2;
            label23: {
               Enumeration.Value var10000 = x$20.state();
               Enumeration.Value var1 = ExecutorState$.MODULE$.RUNNING();
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

         public {
            if (Master.this == null) {
               throw null;
            } else {
               this.$outer = Master.this;
               this.context$1 = context$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void onDisconnected(final RpcAddress address) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " got disassociated, removing it."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, address)})))));
      this.addressToWorker().get(address).foreach((x$21) -> {
         $anonfun$onDisconnected$2(this, address, x$21);
         return BoxedUnit.UNIT;
      });
      this.addressToApp().get(address).foreach((app) -> {
         $anonfun$onDisconnected$3(this, app);
         return BoxedUnit.UNIT;
      });
      Enumeration.Value var10000 = this.state();
      Enumeration.Value var2 = RecoveryState$.MODULE$.RECOVERING();
      if (var10000 == null) {
         if (var2 != null) {
            return;
         }
      } else if (!var10000.equals(var2)) {
         return;
      }

      if (this.org$apache$spark$deploy$master$Master$$canCompleteRecovery()) {
         this.org$apache$spark$deploy$master$Master$$completeRecovery();
      }
   }

   public boolean org$apache$spark$deploy$master$Master$$canCompleteRecovery() {
      return this.workers().count((x$22) -> BoxesRunTime.boxToBoolean($anonfun$canCompleteRecovery$1(x$22))) == 0 && this.apps().count((x$23) -> BoxesRunTime.boxToBoolean($anonfun$canCompleteRecovery$2(x$23))) == 0;
   }

   private long recoveryStartTimeMs() {
      return this.recoveryStartTimeMs;
   }

   private void recoveryStartTimeMs_$eq(final long x$1) {
      this.recoveryStartTimeMs = x$1;
   }

   public boolean org$apache$spark$deploy$master$Master$$beginRecovery(final Seq storedApps, final Seq storedDrivers, final Seq storedWorkers) {
      this.recoveryStartTimeMs_$eq(System.currentTimeMillis());
      storedApps.foreach((app) -> {
         $anonfun$beginRecovery$1(this, app);
         return BoxedUnit.UNIT;
      });
      storedDrivers.foreach((driver) -> (HashSet)this.org$apache$spark$deploy$master$Master$$drivers().$plus$eq(driver));
      storedWorkers.foreach((worker) -> {
         $anonfun$beginRecovery$5(this, worker);
         return BoxedUnit.UNIT;
      });
      if (this.org$apache$spark$deploy$master$Master$$canCompleteRecovery()) {
         this.org$apache$spark$deploy$master$Master$$completeRecovery();
         return false;
      } else {
         return true;
      }
   }

   public void org$apache$spark$deploy$master$Master$$completeRecovery() {
      Enumeration.Value var10000 = this.state();
      Enumeration.Value var1 = RecoveryState$.MODULE$.RECOVERING();
      if (var10000 == null) {
         if (var1 != null) {
            return;
         }
      } else if (!var10000.equals(var1)) {
         return;
      }

      this.state_$eq(RecoveryState$.MODULE$.COMPLETING_RECOVERY());
      ((HashSet)this.workers().filter((x$24) -> BoxesRunTime.boxToBoolean($anonfun$completeRecovery$1(x$24)))).foreach((x$25) -> {
         $anonfun$completeRecovery$2(this, x$25);
         return BoxedUnit.UNIT;
      });
      ((HashSet)this.apps().filter((x$26) -> BoxesRunTime.boxToBoolean($anonfun$completeRecovery$3(x$26)))).foreach((app) -> {
         $anonfun$completeRecovery$4(this, app);
         return BoxedUnit.UNIT;
      });
      ((HashSet)this.apps().filter((x$27) -> BoxesRunTime.boxToBoolean($anonfun$completeRecovery$5(x$27)))).foreach((x$28) -> {
         $anonfun$completeRecovery$6(x$28);
         return BoxedUnit.UNIT;
      });
      ((HashSet)this.org$apache$spark$deploy$master$Master$$drivers().filter((x$29) -> BoxesRunTime.boxToBoolean($anonfun$completeRecovery$7(x$29)))).foreach((d) -> {
         $anonfun$completeRecovery$8(this, d);
         return BoxedUnit.UNIT;
      });
      this.state_$eq(RecoveryState$.MODULE$.ALIVE());
      this.org$apache$spark$deploy$master$Master$$schedule();
      long timeTakenMs = System.currentTimeMillis() - this.recoveryStartTimeMs();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Recovery complete in ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(timeTakenMs))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" - resuming operations!"})))).log(scala.collection.immutable.Nil..MODULE$))));
   }

   public void handleRegisterWorker(final String id, final String workerHost, final int workerPort, final RpcEndpointRef workerRef, final int cores, final int memory, final String workerWebUiUrl, final RpcAddress masterAddress, final scala.collection.immutable.Map resources) {
      label35: {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering worker"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_HOST..MODULE$, workerHost), new MDC(org.apache.spark.internal.LogKeys.WORKER_PORT..MODULE$, BoxesRunTime.boxToInteger(workerPort))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with ", " cores,"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CORES..MODULE$, BoxesRunTime.boxToInteger(cores))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " RAM"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.megabytesToString((long)memory))}))))));
         Enumeration.Value var10000 = this.state();
         Enumeration.Value var10 = RecoveryState$.MODULE$.STANDBY();
         if (var10000 == null) {
            if (var10 == null) {
               break label35;
            }
         } else if (var10000.equals(var10)) {
            break label35;
         }

         if (!this.idToWorker().contains(id)) {
            scala.collection.immutable.Map workerResources = (scala.collection.immutable.Map)resources.map((r) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(r._1()), new WorkerResourceInfo((String)r._1(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(((ResourceInformation)r._2()).addresses()).toImmutableArraySeq())));
            WorkerInfo worker = new WorkerInfo(id, workerHost, workerPort, cores, memory, workerRef, workerWebUiUrl, workerResources);
            if (this.registerWorker(worker)) {
               this.persistenceEngine().addWorker(worker);
               workerRef.send(new DeployMessages.RegisteredWorker(this.self(), this.masterWebUiUrl(), masterAddress, false));
               this.org$apache$spark$deploy$master$Master$$schedule();
               return;
            }

            RpcAddress workerAddress = worker.endpoint().address();
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker registration failed. Attempted to re-register worker at same "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"address: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_URL..MODULE$, workerAddress)}))))));
            workerRef.send(new DeployMessages.RegisterWorkerFailed("Attempted to re-register worker at same address: " + workerAddress));
            return;
         }

         label26: {
            var10000 = ((WorkerInfo)this.idToWorker().apply(id)).state();
            Enumeration.Value var11 = WorkerState$.MODULE$.UNKNOWN();
            if (var10000 == null) {
               if (var11 != null) {
                  break label26;
               }
            } else if (!var10000.equals(var11)) {
               break label26;
            }

            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker has been re-registered: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, id)})))));
            ((WorkerInfo)this.idToWorker().apply(id)).state_$eq(WorkerState$.MODULE$.ALIVE());
         }

         workerRef.send(new DeployMessages.RegisteredWorker(this.self(), this.masterWebUiUrl(), masterAddress, true));
         return;
      }

      workerRef.send(DeployMessages.MasterInStandby$.MODULE$);
   }

   private int[] scheduleExecutorsOnWorkers(final ApplicationInfo app, final int rpId, final ExecutorResourceDescription resourceDesc, final WorkerInfo[] usableWorkers, final boolean spreadOutApps) {
      Option coresPerExecutor = resourceDesc.coresPerExecutor();
      int minCoresPerExecutor = BoxesRunTime.unboxToInt(coresPerExecutor.getOrElse((JFunction0.mcI.sp)() -> 1));
      boolean oneExecutorPerWorker = coresPerExecutor.isEmpty();
      int memoryPerExecutor = resourceDesc.memoryMbPerExecutor();
      Seq resourceReqsPerExecutor = resourceDesc.customResourcesPerExecutor();
      int numUsable = usableWorkers.length;
      int[] assignedCores = new int[numUsable];
      int[] assignedExecutors = new int[numUsable];
      IntRef coresToAssign = IntRef.create(scala.math.package..MODULE$.min(app.coresLeft(), BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(usableWorkers), (x$30) -> BoxesRunTime.boxToInteger($anonfun$scheduleExecutorsOnWorkers$2(x$30)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$))));

      for(IndexedSeq freeWorkers = (IndexedSeq)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numUsable).filter((JFunction1.mcZI.sp)(pos) -> canLaunchExecutorForApp$1(pos, coresToAssign, minCoresPerExecutor, usableWorkers, assignedCores, assignedExecutors, oneExecutorPerWorker, memoryPerExecutor, resourceReqsPerExecutor, app, rpId)); freeWorkers.nonEmpty(); freeWorkers = (IndexedSeq)freeWorkers.filter((JFunction1.mcZI.sp)(pos) -> canLaunchExecutorForApp$1(pos, coresToAssign, minCoresPerExecutor, usableWorkers, assignedCores, assignedExecutors, oneExecutorPerWorker, memoryPerExecutor, resourceReqsPerExecutor, app, rpId))) {
         freeWorkers.foreach((JFunction1.mcVI.sp)(pos) -> {
            boolean keepScheduling = true;

            while(keepScheduling && canLaunchExecutorForApp$1(pos, coresToAssign, minCoresPerExecutor, usableWorkers, assignedCores, assignedExecutors, oneExecutorPerWorker, memoryPerExecutor, resourceReqsPerExecutor, app, rpId)) {
               coresToAssign.elem -= minCoresPerExecutor;
               assignedCores[pos] += minCoresPerExecutor;
               if (oneExecutorPerWorker) {
                  assignedExecutors[pos] = 1;
               } else {
                  int var10002 = assignedExecutors[pos]++;
               }

               if (spreadOutApps) {
                  keepScheduling = false;
               }
            }

         });
      }

      return assignedCores;
   }

   private void startExecutorsOnWorkers() {
      this.waitingApps().foreach((app) -> {
         $anonfun$startExecutorsOnWorkers$1(this, app);
         return BoxedUnit.UNIT;
      });
   }

   private void allocateWorkerResourceToExecutors(final ApplicationInfo app, final int assignedCores, final ExecutorResourceDescription resourceDesc, final WorkerInfo worker, final int rpId) {
      Option coresPerExecutor = resourceDesc.coresPerExecutor();
      int numExecutors = BoxesRunTime.unboxToInt(coresPerExecutor.map((JFunction1.mcII.sp)(x$34) -> assignedCores / x$34).getOrElse((JFunction0.mcI.sp)() -> 1));
      int coresToAssign = BoxesRunTime.unboxToInt(coresPerExecutor.getOrElse((JFunction0.mcI.sp)() -> assignedCores));
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), numExecutors).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         scala.collection.immutable.Map allocated = worker.acquireResources(resourceDesc.customResourcesPerExecutor());
         ExecutorDesc exec = app.addExecutor(worker, coresToAssign, resourceDesc.memoryMbPerExecutor(), allocated, rpId, app.addExecutor$default$6());
         this.launchExecutor(worker, exec);
         app.state_$eq(ApplicationState$.MODULE$.RUNNING());
      });
   }

   private boolean canLaunch(final WorkerInfo worker, final int memoryReq, final int coresReq, final Seq resourceRequirements) {
      boolean enoughMem = worker.memoryFree() >= memoryReq;
      boolean enoughCores = worker.coresFree() >= coresReq;
      boolean enoughResources = ResourceUtils$.MODULE$.resourcesMeetRequirements(worker.resourcesAmountFree(), resourceRequirements);
      return enoughMem && enoughCores && enoughResources;
   }

   private boolean canLaunchDriver(final WorkerInfo worker, final DriverDescription desc) {
      return this.canLaunch(worker, desc.mem(), desc.cores(), desc.resourceReqs());
   }

   private boolean canLaunchExecutor(final WorkerInfo worker, final ExecutorResourceDescription resourceDesc) {
      return this.canLaunch(worker, resourceDesc.memoryMbPerExecutor(), BoxesRunTime.unboxToInt(resourceDesc.coresPerExecutor().getOrElse((JFunction0.mcI.sp)() -> 1)), resourceDesc.customResourcesPerExecutor());
   }

   public void org$apache$spark$deploy$master$Master$$schedule() {
      Enumeration.Value var10000 = this.state();
      Enumeration.Value var1 = RecoveryState$.MODULE$.ALIVE();
      if (var10000 == null) {
         if (var1 != null) {
            return;
         }
      } else if (!var10000.equals(var1)) {
         return;
      }

      if (this.spreadOutDrivers()) {
         Seq shuffledAliveWorkers = (Seq)scala.util.Random..MODULE$.shuffle((IterableOnce)this.workers().toSeq().filter((x$35) -> BoxesRunTime.boxToBoolean($anonfun$schedule$1(x$35))), scala.collection.BuildFrom..MODULE$.buildFromIterableOps());
         int numWorkersAlive = shuffledAliveWorkers.size();
         IntRef curPos = IntRef.create(0);
         this.org$apache$spark$deploy$master$Master$$waitingDrivers().toList().foreach((driver) -> {
            $anonfun$schedule$2(this, numWorkersAlive, shuffledAliveWorkers, curPos, driver);
            return BoxedUnit.UNIT;
         });
      } else {
         Seq aliveWorkers = (Seq)((SeqOps)this.workers().toSeq().filter((x$36) -> BoxesRunTime.boxToBoolean($anonfun$schedule$4(x$36)))).sortBy((x$37) -> x$37.id(), scala.math.Ordering.String..MODULE$);
         this.org$apache$spark$deploy$master$Master$$waitingDrivers().toList().foreach((driver) -> {
            if (this.org$apache$spark$deploy$master$Master$$drivers().size() - this.org$apache$spark$deploy$master$Master$$waitingDrivers().size() < this.maxDrivers()) {
               Option var4 = aliveWorkers.find((x$38) -> BoxesRunTime.boxToBoolean($anonfun$schedule$7(this, driver, x$38)));
               if (var4 instanceof Some) {
                  Some var5 = (Some)var4;
                  WorkerInfo worker = (WorkerInfo)var5.value();
                  driver.withResources(worker.acquireResources(driver.desc().resourceReqs()));
                  this.launchDriver(worker, driver);
                  return this.org$apache$spark$deploy$master$Master$$waitingDrivers().$minus$eq(driver);
               } else {
                  this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driver.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requires more resource than any of Workers could have."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  return BoxedUnit.UNIT;
               }
            } else {
               return BoxedUnit.UNIT;
            }
         });
      }

      this.startExecutorsOnWorkers();
   }

   private void launchExecutor(final WorkerInfo worker, final ExecutorDesc exec) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Launching executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, exec.fullId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" on worker ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())}))))));
      worker.addExecutor(exec);
      worker.endpoint().send(new DeployMessages.LaunchExecutor(this.org$apache$spark$deploy$master$Master$$masterUrl(), exec.application().id(), exec.id(), exec.rpId(), exec.application().desc(), exec.cores(), exec.memory(), exec.resources()));
      exec.application().driver().send(new DeployMessages.ExecutorAdded(exec.id(), worker.id(), worker.hostPort(), exec.cores(), exec.memory()));
   }

   private boolean registerWorker(final WorkerInfo worker) {
      RpcAddress workerAddress;
      label18: {
         ((HashSet)this.workers().filter((w) -> BoxesRunTime.boxToBoolean($anonfun$registerWorker$1(worker, w)))).foreach((w) -> (HashSet)this.workers().$minus$eq(w));
         workerAddress = worker.endpoint().address();
         if (this.addressToWorker().contains(workerAddress)) {
            WorkerInfo oldWorker = (WorkerInfo)this.addressToWorker().apply(workerAddress);
            Enumeration.Value var10000 = oldWorker.state();
            Enumeration.Value var4 = WorkerState$.MODULE$.UNKNOWN();
            if (var10000 == null) {
               if (var4 != null) {
                  break label18;
               }
            } else if (!var10000.equals(var4)) {
               break label18;
            }

            this.removeWorker(oldWorker, "Worker replaced by a new worker with same address");
         }

         this.workers().$plus$eq(worker);
         this.idToWorker().update(worker.id(), worker);
         this.addressToWorker().update(workerAddress, worker);
         return true;
      }

      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to re-register worker at same address:"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, workerAddress)}))))));
      return false;
   }

   public Integer org$apache$spark$deploy$master$Master$$decommissionWorkersOnHosts(final Seq hostnames) {
      Set hostnamesSet = ((IterableOnceOps)hostnames.map((x$39) -> x$39.toLowerCase(Locale.ROOT))).toSet();
      Iterable workersToRemove = ((MapOps)this.addressToWorker().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$decommissionWorkersOnHosts$2(hostnamesSet, x0$1)))).values();
      Iterable workersToRemoveHostPorts = (Iterable)workersToRemove.map((x$40) -> x$40.hostPort());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommissioning the workers with host:ports"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, workersToRemoveHostPorts)}))))));
      this.self().send(new DeployMessages.DecommissionWorkers(((IterableOnceOps)workersToRemove.map((x$41) -> x$41.id())).toSeq()));
      return scala.Predef..MODULE$.int2Integer(workersToRemove.size());
   }

   public void org$apache$spark$deploy$master$Master$$decommissionWorker(final WorkerInfo worker) {
      label14: {
         Enumeration.Value var10000 = worker.state();
         Enumeration.Value var2 = WorkerState$.MODULE$.DECOMMISSIONED();
         if (var10000 == null) {
            if (var2 != null) {
               break label14;
            }
         } else if (!var10000.equals(var2)) {
            break label14;
         }

         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipping decommissioning worker ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"on ", ":"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_HOST..MODULE$, worker.host())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " as worker is already decommissioned"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_PORT..MODULE$, BoxesRunTime.boxToInteger(worker.port()))}))))));
         return;
      }

      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommissioning worker ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" on ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_HOST..MODULE$, worker.host()), new MDC(org.apache.spark.internal.LogKeys.WORKER_PORT..MODULE$, BoxesRunTime.boxToInteger(worker.port()))}))))));
      worker.setState(WorkerState$.MODULE$.DECOMMISSIONED());
      worker.executors().values().foreach((exec) -> {
         $anonfun$decommissionWorker$2(this, worker, exec);
         return BoxedUnit.UNIT;
      });
      this.persistenceEngine().removeWorker(worker);
   }

   private void removeWorker(final WorkerInfo worker, final String msg) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing worker ", " on"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_HOST..MODULE$, worker.host()), new MDC(org.apache.spark.internal.LogKeys.WORKER_PORT..MODULE$, BoxesRunTime.boxToInteger(worker.port()))}))))));
      worker.setState(WorkerState$.MODULE$.DEAD());
      this.idToWorker().$minus$eq(worker.id());
      this.addressToWorker().$minus$eq(worker.endpoint().address());
      worker.executors().values().foreach((exec) -> {
         $anonfun$removeWorker$2(this, msg, worker, exec);
         return BoxedUnit.UNIT;
      });
      worker.drivers().values().foreach((driver) -> {
         $anonfun$removeWorker$4(this, driver);
         return BoxedUnit.UNIT;
      });
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Telling app of lost worker: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())})))));
      ((HashSet)this.apps().filterNot((x$42) -> BoxesRunTime.boxToBoolean($anonfun$removeWorker$8(this, x$42)))).foreach((app) -> {
         $anonfun$removeWorker$9(worker, msg, app);
         return BoxedUnit.UNIT;
      });
      this.persistenceEngine().removeWorker(worker);
      this.org$apache$spark$deploy$master$Master$$schedule();
   }

   private void relaunchDriver(final DriverInfo driver) {
      this.org$apache$spark$deploy$master$Master$$removeDriver(driver.id(), DriverState$.MODULE$.RELAUNCHING(), scala.None..MODULE$);
      DriverInfo newDriver = this.org$apache$spark$deploy$master$Master$$createDriver(driver.desc());
      this.persistenceEngine().addDriver(newDriver);
      this.org$apache$spark$deploy$master$Master$$drivers().add(newDriver);
      this.org$apache$spark$deploy$master$Master$$waitingDrivers().$plus$eq(newDriver);
      this.org$apache$spark$deploy$master$Master$$schedule();
   }

   public ApplicationInfo org$apache$spark$deploy$master$Master$$createApplication(final ApplicationDescription desc, final RpcEndpointRef driver) {
      long now = System.currentTimeMillis();
      Date date = new Date(now);
      String appId = this.useAppNameAsAppId() ? desc.name().toLowerCase().replaceAll("\\s+", "") : this.newApplicationId(date);
      return new ApplicationInfo(now, appId, desc, date, driver, this.defaultCores());
   }

   public void registerApplication(final ApplicationInfo app) {
      RpcAddress appAddress = app.driver().address();
      if (this.addressToApp().contains(appAddress)) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to re-register application at same"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" address: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, appAddress)}))))));
      } else {
         this.applicationMetricsSystem().registerSource(app.appSource());
         this.apps().$plus$eq(app);
         this.idToApp().update(app.id(), app);
         this.endpointToApp().update(app.driver(), app);
         this.addressToApp().update(appAddress, app);
         this.waitingApps().$plus$eq(app);
      }
   }

   public void org$apache$spark$deploy$master$Master$$finishApplication(final ApplicationInfo app) {
      this.removeApplication(app, ApplicationState$.MODULE$.FINISHED());
   }

   public void removeApplication(final ApplicationInfo app, final Enumeration.Value state) {
      if (this.apps().contains(app)) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing app ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, app.id())})))));
         this.apps().$minus$eq(app);
         this.idToApp().$minus$eq(app.id());
         this.endpointToApp().$minus$eq(app.driver());
         this.addressToApp().$minus$eq(app.driver().address());
         if (this.org$apache$spark$deploy$master$Master$$completedApps().size() >= this.retainedApplications()) {
            int toRemove = scala.math.package..MODULE$.max(this.retainedApplications() / 10, 1);
            ((IterableOnceOps)this.org$apache$spark$deploy$master$Master$$completedApps().take(toRemove)).foreach((a) -> {
               $anonfun$removeApplication$2(this, a);
               return BoxedUnit.UNIT;
            });
            this.org$apache$spark$deploy$master$Master$$completedApps().dropInPlace(toRemove);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         label20: {
            this.org$apache$spark$deploy$master$Master$$completedApps().$plus$eq(app);
            this.waitingApps().$minus$eq(app);
            app.executors().values().foreach((exec) -> {
               $anonfun$removeApplication$3(this, exec);
               return BoxedUnit.UNIT;
            });
            app.markFinished(state);
            Enumeration.Value var4 = ApplicationState$.MODULE$.FINISHED();
            if (state == null) {
               if (var4 == null) {
                  break label20;
               }
            } else if (state.equals(var4)) {
               break label20;
            }

            app.driver().send(new DeployMessages.ApplicationRemoved(state.toString()));
         }

         this.persistenceEngine().removeApplication(app);
         this.org$apache$spark$deploy$master$Master$$schedule();
         this.workers().foreach((w) -> {
            $anonfun$removeApplication$4(app, w);
            return BoxedUnit.UNIT;
         });
      }
   }

   public boolean org$apache$spark$deploy$master$Master$$handleRequestExecutors(final String appId, final scala.collection.immutable.Map resourceProfileToTotalExecs) {
      Option var4 = this.idToApp().get(appId);
      if (var4 instanceof Some var5) {
         ApplicationInfo appInfo = (ApplicationInfo)var5.value();
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " requested executors:"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_TO_TOTAL_EXECS..MODULE$, resourceProfileToTotalExecs)}))))));
         appInfo.requestExecutors(resourceProfileToTotalExecs);
         this.org$apache$spark$deploy$master$Master$$schedule();
         return true;
      } else if (scala.None..MODULE$.equals(var4)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unknown application "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " requested executors:"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_TO_TOTAL_EXECS..MODULE$, resourceProfileToTotalExecs)}))))));
         return false;
      } else {
         throw new MatchError(var4);
      }
   }

   public boolean org$apache$spark$deploy$master$Master$$handleKillExecutors(final String appId, final Seq executorIds) {
      Option var5 = this.idToApp().get(appId);
      if (var5 instanceof Some var6) {
         ApplicationInfo appInfo = (ApplicationInfo)var6.value();
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " requests to kill"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" executors: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorIds.mkString(", "))}))))));
         HashMap var10 = appInfo.executors();
         Tuple2 var9 = executorIds.partition((JFunction1.mcZI.sp)(key) -> var10.contains(BoxesRunTime.boxToInteger(key)));
         if (var9 != null) {
            Seq known = (Seq)var9._1();
            Seq unknown = (Seq)var9._2();
            Tuple2 var8 = new Tuple2(known, unknown);
            Seq known = (Seq)var8._1();
            Seq unknown = (Seq)var8._2();
            known.foreach((JFunction1.mcVI.sp)(executorId) -> {
               ExecutorDesc desc = (ExecutorDesc)appInfo.executors().apply(BoxesRunTime.boxToInteger(executorId));
               appInfo.removeExecutor(desc);
               this.killExecutor(desc);
            });
            if (unknown.nonEmpty()) {
               this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " attempted to kill "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"non-existent executors: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, unknown.mkString(", "))}))))));
            }

            this.org$apache$spark$deploy$master$Master$$schedule();
            return true;
         } else {
            throw new MatchError(var9);
         }
      } else if (scala.None..MODULE$.equals(var5)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unregistered application ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requested us to kill executors!"})))).log(scala.collection.immutable.Nil..MODULE$))));
         return false;
      } else {
         throw new MatchError(var5);
      }
   }

   public Seq org$apache$spark$deploy$master$Master$$formatExecutorIds(final Seq executorIds) {
      return (Seq)executorIds.flatMap((executorId) -> {
         Object var10000;
         try {
            var10000 = new Some(BoxesRunTime.boxToInteger(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(executorId))));
         } catch (NumberFormatException var3) {
            this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Encountered executor with a non-integer ID: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Ignoring"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))));
            var10000 = scala.None..MODULE$;
         }

         return (IterableOnce)var10000;
      });
   }

   private void killExecutor(final ExecutorDesc exec) {
      exec.worker().removeExecutor(exec);
      exec.worker().endpoint().send(new DeployMessages.KillExecutor(this.org$apache$spark$deploy$master$Master$$masterUrl(), exec.application().id(), exec.id()));
      exec.state_$eq(ExecutorState$.MODULE$.KILLED());
   }

   private String newApplicationId(final Date submitDate) {
      String appId = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(this.appIdPattern()), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{Master$.MODULE$.org$apache$spark$deploy$master$Master$$DATE_TIME_FORMATTER().format(submitDate.toInstant()), BoxesRunTime.boxToInteger(this.nextAppNumber())}));
      this.nextAppNumber_$eq(this.nextAppNumber() + 1);
      if (this.moduloAppNumber() > 0) {
         this.nextAppNumber_$eq(this.nextAppNumber() % this.moduloAppNumber());
      }

      return appId;
   }

   public void org$apache$spark$deploy$master$Master$$timeOutDeadWorkers() {
      long currentTime = System.currentTimeMillis();
      WorkerInfo[] toRemove = (WorkerInfo[])((IterableOnceOps)this.workers().filter((x$44) -> BoxesRunTime.boxToBoolean($anonfun$timeOutDeadWorkers$1(this, currentTime, x$44)))).toArray(scala.reflect.ClassTag..MODULE$.apply(WorkerInfo.class));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(toRemove), (worker) -> {
         label20: {
            Enumeration.Value var10000 = worker.state();
            Enumeration.Value var4 = WorkerState$.MODULE$.DEAD();
            if (var10000 == null) {
               if (var4 != null) {
                  break label20;
               }
            } else if (!var10000.equals(var4)) {
               break label20;
            }

            if (worker.lastHeartbeat() < currentTime - (long)(this.reaperIterations() + 1) * this.workerTimeoutMs()) {
               return this.workers().$minus$eq(worker);
            }

            return BoxedUnit.UNIT;
         }

         long workerTimeoutSecs = TimeUnit.MILLISECONDS.toSeconds(this.workerTimeoutMs());
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing ", " because we got no heartbeat "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(this.workerTimeoutMs()))}))))));
         this.removeWorker(worker, "Not receiving heartbeat for " + workerTimeoutSecs + " seconds");
         return BoxedUnit.UNIT;
      });
   }

   private String newDriverId(final Date submitDate) {
      String appId = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(this.driverIdPattern()), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{Master$.MODULE$.org$apache$spark$deploy$master$Master$$DATE_TIME_FORMATTER().format(submitDate.toInstant()), BoxesRunTime.boxToInteger(this.nextDriverNumber())}));
      this.nextDriverNumber_$eq(this.nextDriverNumber() + 1);
      return appId;
   }

   private DriverDescription maybeUpdateAppName(final DriverDescription desc, final String appName) {
      if (!this.useDriverIdAsAppName()) {
         return desc;
      } else {
         String config = "spark.app.name=" + appName;
         Seq javaOpts = (Seq)((SeqOps)desc.command().javaOpts().filter((opt) -> BoxesRunTime.boxToBoolean($anonfun$maybeUpdateAppName$1(opt)))).$colon$plus("-D" + config);
         Seq args = desc.command().arguments();
         Seq arguments = ((String)args.apply(2)).equals(SparkSubmit.class.getName()) ? (args.length() > 4 && ((String)args.apply(4)).startsWith("spark.app.name=") ? (Seq)args.updated(4, config) : (Seq)args.patch(3, new scala.collection.immutable..colon.colon("-c", new scala.collection.immutable..colon.colon(config, scala.collection.immutable.Nil..MODULE$)), 0)) : args;
         String x$3 = desc.command().copy$default$1();
         scala.collection.Map x$4 = desc.command().copy$default$3();
         Seq x$5 = desc.command().copy$default$4();
         Seq x$6 = desc.command().copy$default$5();
         Command x$7 = desc.command().copy(x$3, arguments, x$4, x$5, x$6, javaOpts);
         String x$8 = desc.copy$default$1();
         int x$9 = desc.copy$default$2();
         int x$10 = desc.copy$default$3();
         boolean x$11 = desc.copy$default$4();
         Seq x$12 = desc.copy$default$6();
         return desc.copy(x$8, x$9, x$10, x$11, x$7, x$12);
      }
   }

   public DriverInfo org$apache$spark$deploy$master$Master$$createDriver(final DriverDescription desc) {
      long now = System.currentTimeMillis();
      Date date = new Date(now);
      String id = this.newDriverId(date);
      return new DriverInfo(now, id, this.maybeUpdateAppName(desc, id), date);
   }

   private void launchDriver(final WorkerInfo worker, final DriverInfo driver) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Launching driver ", " on worker ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driver.id()), new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())})))));
      worker.addDriver(driver);
      driver.worker_$eq(new Some(worker));
      worker.endpoint().send(new DeployMessages.LaunchDriver(driver.id(), driver.desc(), driver.resources()));
      driver.state_$eq(DriverState$.MODULE$.RUNNING());
   }

   public void org$apache$spark$deploy$master$Master$$removeDriver(final String driverId, final Enumeration.Value finalState, final Option exception) {
      Option var5 = this.org$apache$spark$deploy$master$Master$$drivers().find((d) -> BoxesRunTime.boxToBoolean($anonfun$removeDriver$1(driverId, d)));
      if (var5 instanceof Some var6) {
         DriverInfo driver = (DriverInfo)var6.value();
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing driver: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_STATE..MODULE$, finalState)}))))));
         this.org$apache$spark$deploy$master$Master$$drivers().$minus$eq(driver);
         if (this.org$apache$spark$deploy$master$Master$$completedDrivers().size() >= this.retainedDrivers()) {
            int toRemove = scala.math.package..MODULE$.max(this.retainedDrivers() / 10, 1);
            this.org$apache$spark$deploy$master$Master$$completedDrivers().dropInPlace(toRemove);
         } else {
            BoxedUnit var9 = BoxedUnit.UNIT;
         }

         this.org$apache$spark$deploy$master$Master$$completedDrivers().$plus$eq(driver);
         this.persistenceEngine().removeDriver(driver);
         driver.state_$eq(finalState);
         driver.exception_$eq(exception);
         driver.worker().foreach((w) -> {
            $anonfun$removeDriver$3(driver, w);
            return BoxedUnit.UNIT;
         });
         this.org$apache$spark$deploy$master$Master$$schedule();
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var5)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to remove unknown driver: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driverId)})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$onStart$8(final StandaloneRestServer x$8) {
      return x$8.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$onStart$9(final MasterWebUI eta$0$1$1, final ServletContextHandler handler) {
      eta$0$1$1.attachHandler(handler);
   }

   // $FF: synthetic method
   public static final void $anonfun$onStart$10(final MasterWebUI eta$0$2$1, final ServletContextHandler handler) {
      eta$0$2$1.attachHandler(handler);
   }

   // $FF: synthetic method
   public static final void $anonfun$onStop$1(final StandaloneRestServer x$10) {
      x$10.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$onDisconnected$2(final Master $this, final RpcAddress address$1, final WorkerInfo x$21) {
      $this.removeWorker(x$21, address$1 + " got disassociated");
   }

   // $FF: synthetic method
   public static final void $anonfun$onDisconnected$3(final Master $this, final ApplicationInfo app) {
      $this.org$apache$spark$deploy$master$Master$$finishApplication(app);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$canCompleteRecovery$1(final WorkerInfo x$22) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$22.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.UNKNOWN();
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
   public static final boolean $anonfun$canCompleteRecovery$2(final ApplicationInfo x$23) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$23.state();
         Enumeration.Value var1 = ApplicationState$.MODULE$.UNKNOWN();
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
   public static final void $anonfun$beginRecovery$1(final Master $this, final ApplicationInfo app) {
      $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Trying to recover app: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, app.id())})))));

      try {
         $this.registerApplication(app);
         app.state_$eq(ApplicationState$.MODULE$.UNKNOWN());
         app.driver().send(new DeployMessages.MasterChanged($this.self(), $this.masterWebUiUrl()));
      } catch (Exception var3) {
         $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"App ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, app.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" had exception on reconnect"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$beginRecovery$5(final Master $this, final WorkerInfo worker) {
      $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Trying to recover worker: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())})))));

      try {
         $this.registerWorker(worker);
         worker.state_$eq(WorkerState$.MODULE$.UNKNOWN());
         worker.endpoint().send(new DeployMessages.MasterChanged($this.self(), $this.masterWebUiUrl()));
      } catch (Exception var3) {
         $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, worker.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" had exception on reconnect"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$completeRecovery$1(final WorkerInfo x$24) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$24.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.UNKNOWN();
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
   public static final void $anonfun$completeRecovery$2(final Master $this, final WorkerInfo x$25) {
      $this.removeWorker(x$25, "Not responding for recovery");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$completeRecovery$3(final ApplicationInfo x$26) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$26.state();
         Enumeration.Value var1 = ApplicationState$.MODULE$.UNKNOWN();
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
   public static final void $anonfun$completeRecovery$4(final Master $this, final ApplicationInfo app) {
      $this.org$apache$spark$deploy$master$Master$$finishApplication(app);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$completeRecovery$5(final ApplicationInfo x$27) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$27.state();
         Enumeration.Value var1 = ApplicationState$.MODULE$.WAITING();
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
   public static final void $anonfun$completeRecovery$6(final ApplicationInfo x$28) {
      x$28.state_$eq(ApplicationState$.MODULE$.RUNNING());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$completeRecovery$7(final DriverInfo x$29) {
      return x$29.worker().isEmpty();
   }

   // $FF: synthetic method
   public static final void $anonfun$completeRecovery$8(final Master $this, final DriverInfo d) {
      $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, d.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was not found after master recovery"})))).log(scala.collection.immutable.Nil..MODULE$))));
      if (d.desc().supervise()) {
         $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Re-launching ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, d.id())})))));
         $this.relaunchDriver(d);
      } else {
         $this.org$apache$spark$deploy$master$Master$$removeDriver(d.id(), DriverState$.MODULE$.ERROR(), scala.None..MODULE$);
         $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Did not re-launch "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " because it was not supervised"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, d.id())}))))));
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$scheduleExecutorsOnWorkers$2(final WorkerInfo x$30) {
      return x$30.coresFree();
   }

   private static final boolean canLaunchExecutorForApp$1(final int pos, final IntRef coresToAssign$1, final int minCoresPerExecutor$1, final WorkerInfo[] usableWorkers$1, final int[] assignedCores$1, final int[] assignedExecutors$1, final boolean oneExecutorPerWorker$1, final int memoryPerExecutor$1, final Seq resourceReqsPerExecutor$1, final ApplicationInfo app$3, final int rpId$1) {
      boolean keepScheduling = coresToAssign$1.elem >= minCoresPerExecutor$1;
      boolean enoughCores = usableWorkers$1[pos].coresFree() - assignedCores$1[pos] >= minCoresPerExecutor$1;
      int assignedExecutorNum = assignedExecutors$1[pos];
      boolean launchingNewExecutor = !oneExecutorPerWorker$1 || assignedExecutorNum == 0;
      if (launchingNewExecutor) {
         int assignedMemory = assignedExecutorNum * memoryPerExecutor$1;
         boolean enoughMemory = usableWorkers$1[pos].memoryFree() - assignedMemory >= memoryPerExecutor$1;
         scala.collection.immutable.Map assignedResources = ((IterableOnceOps)resourceReqsPerExecutor$1.map((req) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(req.resourceName()), BoxesRunTime.boxToInteger(req.amount() * assignedExecutorNum)))).toMap(scala..less.colon.less..MODULE$.refl());
         scala.collection.immutable.Map resourcesFree = (scala.collection.immutable.Map)usableWorkers$1[pos].resourcesAmountFree().map((x0$1) -> {
            if (x0$1 != null) {
               String rName = (String)x0$1._1();
               int free = x0$1._2$mcI$sp();
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), BoxesRunTime.boxToInteger(free - BoxesRunTime.unboxToInt(assignedResources.getOrElse(rName, (JFunction0.mcI.sp)() -> 0))));
            } else {
               throw new MatchError(x0$1);
            }
         });
         boolean enoughResources = ResourceUtils$.MODULE$.resourcesMeetRequirements(resourcesFree, resourceReqsPerExecutor$1);
         int executorNum = app$3.getOrUpdateExecutorsForRPId(rpId$1).size();
         int executorLimit = app$3.getTargetExecutorNumForRPId(rpId$1);
         boolean underLimit = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray(assignedExecutors$1).sum(scala.math.Numeric.IntIsIntegral..MODULE$)) + executorNum < executorLimit;
         return keepScheduling && enoughCores && enoughMemory && enoughResources && underLimit;
      } else {
         return keepScheduling && enoughCores;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$startExecutorsOnWorkers$5(final WorkerInfo x$31) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$31.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.ALIVE();
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
   public static final boolean $anonfun$startExecutorsOnWorkers$6(final Master $this, final ExecutorResourceDescription resourceDesc$1, final WorkerInfo x$32) {
      return $this.canLaunchExecutor(x$32, resourceDesc$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$startExecutorsOnWorkers$1(final Master $this, final ApplicationInfo app) {
      app.getRequestedRPIds().foreach((JFunction1.mcVI.sp)(rpId) -> {
         $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Start scheduling for app ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, app.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" rpId: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rpId))}))))));
         ExecutorResourceDescription resourceDesc = app.getResourceDescriptionForRpId(rpId);
         int coresPerExecutor = BoxesRunTime.unboxToInt(resourceDesc.coresPerExecutor().getOrElse((JFunction0.mcI.sp)() -> 1));
         if (app.coresLeft() >= coresPerExecutor) {
            WorkerInfo[] var20;
            label87: {
               WorkerInfo[] aliveWorkers;
               label93: {
                  aliveWorkers = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps($this.workers().toArray(scala.reflect.ClassTag..MODULE$.apply(WorkerInfo.class))), (x$31) -> BoxesRunTime.boxToBoolean($anonfun$startExecutorsOnWorkers$5(x$31)))), (x$32) -> BoxesRunTime.boxToBoolean($anonfun$startExecutorsOnWorkers$6($this, resourceDesc, x$32)));
                  Enumeration.Value var8 = $this.workerSelectionPolicy();
                  Enumeration.Value var10000 = Deploy.WorkerSelectionPolicy$.MODULE$.CORES_FREE_ASC();
                  if (var10000 == null) {
                     if (var8 == null) {
                        break label93;
                     }
                  } else if (var10000.equals(var8)) {
                     break label93;
                  }

                  label94: {
                     var10000 = Deploy.WorkerSelectionPolicy$.MODULE$.CORES_FREE_DESC();
                     if (var10000 == null) {
                        if (var8 == null) {
                           break label94;
                        }
                     } else if (var10000.equals(var8)) {
                        break label94;
                     }

                     label95: {
                        var10000 = Deploy.WorkerSelectionPolicy$.MODULE$.MEMORY_FREE_ASC();
                        if (var10000 == null) {
                           if (var8 == null) {
                              break label95;
                           }
                        } else if (var10000.equals(var8)) {
                           break label95;
                        }

                        label96: {
                           var10000 = Deploy.WorkerSelectionPolicy$.MODULE$.MEMORY_FREE_DESC();
                           if (var10000 == null) {
                              if (var8 == null) {
                                 break label96;
                              }
                           } else if (var10000.equals(var8)) {
                              break label96;
                           }

                           var10000 = Deploy.WorkerSelectionPolicy$.MODULE$.WORKER_ID();
                           if (var10000 == null) {
                              if (var8 != null) {
                                 throw new MatchError(var8);
                              }
                           } else if (!var10000.equals(var8)) {
                              throw new MatchError(var8);
                           }

                           var20 = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$33) -> x$33.id(), scala.math.Ordering.String..MODULE$);
                           break label87;
                        }

                        var20 = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (w) -> new Tuple2(BoxesRunTime.boxToInteger(w.memoryFree()), w.id()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.String..MODULE$))));
                        break label87;
                     }

                     var20 = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (w) -> new Tuple2(BoxesRunTime.boxToInteger(w.memoryFree()), w.id()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.String..MODULE$));
                     break label87;
                  }

                  var20 = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (w) -> new Tuple2(BoxesRunTime.boxToInteger(w.coresFree()), w.id()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.String..MODULE$))));
                  break label87;
               }

               var20 = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (w) -> new Tuple2(BoxesRunTime.boxToInteger(w.coresFree()), w.id()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.String..MODULE$));
            }

            WorkerInfo[] usableWorkers = var20;
            boolean appMayHang = $this.waitingApps().length() == 1 && ((ApplicationInfo)$this.waitingApps().head()).executors().isEmpty() && scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(usableWorkers));
            if (appMayHang) {
               $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"App ", " requires more resource "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, app.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"than any of Workers could have."})))).log(scala.collection.immutable.Nil..MODULE$))));
            }

            int[] assignedCores = $this.scheduleExecutorsOnWorkers(app, rpId, resourceDesc, usableWorkers, $this.spreadOutApps());
            scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(usableWorkers)).withFilter((JFunction1.mcZI.sp)(pos) -> assignedCores[pos] > 0).foreach((JFunction1.mcVI.sp)(pos) -> $this.allocateWorkerResourceToExecutors(app, assignedCores[pos], resourceDesc, usableWorkers[pos], rpId));
         }
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$schedule$1(final WorkerInfo x$35) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$35.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.ALIVE();
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
   public static final void $anonfun$schedule$2(final Master $this, final int numWorkersAlive$1, final Seq shuffledAliveWorkers$1, final IntRef curPos$1, final DriverInfo driver) {
      boolean launched = $this.org$apache$spark$deploy$master$Master$$drivers().size() - $this.org$apache$spark$deploy$master$Master$$waitingDrivers().size() >= $this.maxDrivers();
      boolean isClusterIdle = !launched;

      for(int numWorkersVisited = 0; numWorkersVisited < numWorkersAlive$1 && !launched; curPos$1.elem = (curPos$1.elem + 1) % numWorkersAlive$1) {
         WorkerInfo worker = (WorkerInfo)shuffledAliveWorkers$1.apply(curPos$1.elem);
         isClusterIdle = worker.drivers().isEmpty() && worker.executors().isEmpty();
         ++numWorkersVisited;
         if ($this.canLaunchDriver(worker, driver.desc())) {
            scala.collection.immutable.Map allocated = worker.acquireResources(driver.desc().resourceReqs());
            driver.withResources(allocated);
            $this.launchDriver(worker, driver);
            $this.org$apache$spark$deploy$master$Master$$waitingDrivers().$minus$eq(driver);
            launched = true;
         }
      }

      if (!launched && isClusterIdle) {
         $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driver.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requires more resource than any of Workers could have."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$schedule$4(final WorkerInfo x$36) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$36.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.ALIVE();
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
   public static final boolean $anonfun$schedule$7(final Master $this, final DriverInfo driver$2, final WorkerInfo x$38) {
      return $this.canLaunchDriver(x$38, driver$2.desc());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$registerWorker$1(final WorkerInfo worker$6, final WorkerInfo w) {
      boolean var5;
      label34: {
         label26: {
            String var10000 = w.host();
            String var2 = worker$6.host();
            if (var10000 == null) {
               if (var2 != null) {
                  break label26;
               }
            } else if (!var10000.equals(var2)) {
               break label26;
            }

            if (w.port() == worker$6.port()) {
               Enumeration.Value var4 = w.state();
               Enumeration.Value var3 = WorkerState$.MODULE$.DEAD();
               if (var4 == null) {
                  if (var3 == null) {
                     break label34;
                  }
               } else if (var4.equals(var3)) {
                  break label34;
               }
            }
         }

         var5 = false;
         return var5;
      }

      var5 = true;
      return var5;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$decommissionWorkersOnHosts$2(final Set hostnamesSet$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         RpcAddress addr = (RpcAddress)x0$1._1();
         return hostnamesSet$1.contains(addr.host().toLowerCase(Locale.ROOT));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$decommissionWorker$2(final Master $this, final WorkerInfo worker$7, final ExecutorDesc exec) {
      $this.logInfo((Function0)(() -> "Telling app of decommission executors"));
      exec.application().driver().send(new DeployMessages.ExecutorUpdated(exec.id(), ExecutorState$.MODULE$.DECOMMISSIONED(), new Some("worker decommissioned"), scala.None..MODULE$, new Some(worker$7.host())));
      exec.state_$eq(ExecutorState$.MODULE$.DECOMMISSIONED());
      exec.application().removeExecutor(exec);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeWorker$2(final Master $this, final String msg$2, final WorkerInfo worker$8, final ExecutorDesc exec) {
      $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Telling app of lost executor: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, BoxesRunTime.boxToInteger(exec.id()))})))));
      exec.application().driver().send(new DeployMessages.ExecutorUpdated(exec.id(), ExecutorState$.MODULE$.LOST(), new Some("worker lost: " + msg$2), scala.None..MODULE$, new Some(worker$8.host())));
      exec.state_$eq(ExecutorState$.MODULE$.LOST());
      exec.application().removeExecutor(exec);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeWorker$4(final Master $this, final DriverInfo driver) {
      if (driver.desc().supervise()) {
         $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Re-launching ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driver.id())})))));
         $this.relaunchDriver(driver);
      } else {
         $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not re-launching ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, driver.id())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" because it was not supervised"})))).log(scala.collection.immutable.Nil..MODULE$))));
         $this.org$apache$spark$deploy$master$Master$$removeDriver(driver.id(), DriverState$.MODULE$.ERROR(), scala.None..MODULE$);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeWorker$8(final Master $this, final ApplicationInfo x$42) {
      return $this.org$apache$spark$deploy$master$Master$$completedApps().contains(x$42);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeWorker$9(final WorkerInfo worker$8, final String msg$2, final ApplicationInfo app) {
      app.driver().send(new DeployMessages.WorkerRemoved(worker$8.id(), worker$8.host(), msg$2));
   }

   // $FF: synthetic method
   public static final void $anonfun$removeApplication$2(final Master $this, final ApplicationInfo a) {
      $this.applicationMetricsSystem().removeSource(a.appSource());
   }

   // $FF: synthetic method
   public static final void $anonfun$removeApplication$3(final Master $this, final ExecutorDesc exec) {
      $this.killExecutor(exec);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeApplication$4(final ApplicationInfo app$6, final WorkerInfo w) {
      w.endpoint().send(new DeployMessages.ApplicationFinished(app$6.id()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$timeOutDeadWorkers$1(final Master $this, final long currentTime$1, final WorkerInfo x$44) {
      return x$44.lastHeartbeat() < currentTime$1 - $this.workerTimeoutMs();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$maybeUpdateAppName$1(final String opt) {
      return !opt.startsWith("-Dspark.app.name=");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeDriver$1(final String driverId$6, final DriverInfo d) {
      boolean var3;
      label23: {
         String var10000 = d.id();
         if (var10000 == null) {
            if (driverId$6 == null) {
               break label23;
            }
         } else if (var10000.equals(driverId$6)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$removeDriver$3(final DriverInfo driver$5, final WorkerInfo w) {
      w.removeDriver(driver$5);
   }

   public Master(final RpcEnv rpcEnv, final RpcAddress address, final int webUiPort, final SecurityManager securityMgr, final SparkConf conf) {
      this.rpcEnv = rpcEnv;
      this.org$apache$spark$deploy$master$Master$$address = address;
      this.webUiPort = webUiPort;
      this.securityMgr = securityMgr;
      this.conf = conf;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.org$apache$spark$deploy$master$Master$$forwardMessageThread = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("master-forward-message-thread");
      this.driverIdPattern = (String)conf.get(Deploy$.MODULE$.DRIVER_ID_PATTERN());
      this.appIdPattern = (String)conf.get(Deploy$.MODULE$.APP_ID_PATTERN());
      this.workerTimeoutMs = BoxesRunTime.unboxToLong(conf.get(Worker$.MODULE$.WORKER_TIMEOUT())) * 1000L;
      this.retainedApplications = BoxesRunTime.unboxToInt(conf.get(Deploy$.MODULE$.RETAINED_APPLICATIONS()));
      this.retainedDrivers = BoxesRunTime.unboxToInt(conf.get(Deploy$.MODULE$.RETAINED_DRIVERS()));
      this.maxDrivers = BoxesRunTime.unboxToInt(conf.get(Deploy$.MODULE$.MAX_DRIVERS()));
      this.reaperIterations = BoxesRunTime.unboxToInt(conf.get(Deploy$.MODULE$.REAPER_ITERATIONS()));
      this.org$apache$spark$deploy$master$Master$$recoveryTimeoutMs = BoxesRunTime.unboxToLong(((Option)conf.get((ConfigEntry)Deploy$.MODULE$.RECOVERY_TIMEOUT())).map((JFunction1.mcJJ.sp)(x$1) -> x$1 * 1000L).getOrElse((JFunction0.mcJ.sp)() -> this.workerTimeoutMs()));
      this.recoveryMode = (String)conf.get(Deploy$.MODULE$.RECOVERY_MODE());
      this.org$apache$spark$deploy$master$Master$$maxExecutorRetries = BoxesRunTime.unboxToInt(conf.get(Deploy$.MODULE$.MAX_EXECUTOR_RETRIES()));
      this.workers = new HashSet();
      this.idToApp = new HashMap();
      this.waitingApps = new ArrayBuffer();
      this.apps = new HashSet();
      this.idToWorker = new HashMap();
      this.addressToWorker = new HashMap();
      this.endpointToApp = new HashMap();
      this.addressToApp = new HashMap();
      this.org$apache$spark$deploy$master$Master$$completedApps = new ArrayBuffer();
      this.nextAppNumber = 0;
      this.moduloAppNumber = BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)Deploy$.MODULE$.APP_NUMBER_MODULO())).getOrElse((JFunction0.mcI.sp)() -> 0));
      this.org$apache$spark$deploy$master$Master$$drivers = new HashSet();
      this.org$apache$spark$deploy$master$Master$$completedDrivers = new ArrayBuffer();
      this.org$apache$spark$deploy$master$Master$$waitingDrivers = new ArrayBuffer();
      this.nextDriverNumber = 0;
      Utils$.MODULE$.checkHost(address.host());
      this.masterMetricsSystem = MetricsSystem$.MODULE$.createMetricsSystem(MetricsSystemInstances$.MODULE$.MASTER(), conf);
      this.applicationMetricsSystem = MetricsSystem$.MODULE$.createMetricsSystem(MetricsSystemInstances$.MODULE$.APPLICATIONS(), conf);
      this.masterSource = new MasterSource(this);
      this.org$apache$spark$deploy$master$Master$$webUi = null;
      this.org$apache$spark$deploy$master$Master$$masterUrl = address.toSparkURL();
      this.state = RecoveryState$.MODULE$.STANDBY();
      this.spreadOutDrivers = BoxesRunTime.unboxToBoolean(conf.get(Deploy$.MODULE$.SPREAD_OUT_DRIVERS()));
      this.spreadOutApps = BoxesRunTime.unboxToBoolean(conf.get(Deploy$.MODULE$.SPREAD_OUT_APPS()));
      this.workerSelectionPolicy = Deploy.WorkerSelectionPolicy$.MODULE$.withName((String)conf.get(Deploy$.MODULE$.WORKER_SELECTION_POLICY()));
      this.defaultCores = BoxesRunTime.unboxToInt(conf.get(Deploy$.MODULE$.DEFAULT_CORES()));
      this.reverseProxy = BoxesRunTime.unboxToBoolean(conf.get(UI$.MODULE$.UI_REVERSE_PROXY()));
      this.historyServerUrl = (Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.MASTER_UI_HISTORY_SERVER_URL());
      this.useAppNameAsAppId = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.MASTER_USE_APP_NAME_AS_APP_ID()));
      this.useDriverIdAsAppName = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.MASTER_USE_DRIVER_ID_AS_APP_NAME()));
      this.restServerEnabled = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.MASTER_REST_SERVER_ENABLED()));
      this.restServer = scala.None..MODULE$;
      this.org$apache$spark$deploy$master$Master$$restServerBoundPort = scala.None..MODULE$;
      String authKey = SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF();
      scala.Predef..MODULE$.require(conf.getOption(authKey).isEmpty() || !this.restServerEnabled(), () -> "The RestSubmissionServer does not support authentication via " + authKey + ".  Either turn off the RestSubmissionServer with spark.master.rest.enabled=false, or do not use authentication.");
      this.recoveryStartTimeMs = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
