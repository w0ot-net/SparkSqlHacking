package org.apache.spark.scheduler;

import java.io.NotSerializableException;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import org.apache.spark.ExceptionFailure;
import org.apache.spark.ExecutorLostFailure;
import org.apache.spark.FetchFailed;
import org.apache.spark.InternalAccumulator;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.MapOutputTrackerMaster;
import org.apache.spark.Resubmitted$;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.Success$;
import org.apache.spark.TaskEndReason;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.TaskKilled;
import org.apache.spark.TaskKilled$;
import org.apache.spark.TaskNotSerializableException;
import org.apache.spark.TaskOutputFileAlreadyExistException;
import org.apache.spark.TaskState$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.Clock;
import org.apache.spark.util.LongAccumulator;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.collection.PercentileHeap;
import org.apache.spark.util.collection.PercentileHeap$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyBoolean;
import scala.runtime.LongRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u001dmd!CAO\u0003?\u0003\u00111UAX\u0011)\t\t\u000e\u0001B\u0001B\u0003%\u0011Q\u001b\u0005\u000b\u00037\u0004!Q1A\u0005\u0002\u0005u\u0007BCAs\u0001\t\u0005\t\u0015!\u0003\u0002`\"Q\u0011q\u001d\u0001\u0003\u0006\u0004%\t!!;\t\u0015\u0005E\bA!A!\u0002\u0013\tY\u000f\u0003\u0006\u0002t\u0002\u0011\t\u0011)A\u0005\u0003kD!B!\u0001\u0001\u0005\u0003\u0005\u000b\u0011\u0002B\u0002\u0011\u001d\u0011y\u0001\u0001C\u0001\u0005#A\u0011Ba\b\u0001\u0005\u0004%IA!\t\t\u0011\t-\u0002\u0001)A\u0005\u0005GA\u0011B!\f\u0001\u0005\u0004%\tAa\f\t\u0011\t]\u0002\u0001)A\u0005\u0005cA\u0011B!\u000f\u0001\u0005\u0004%\tAa\u000f\t\u0011\t\r\u0003\u0001)A\u0005\u0005{A\u0011B!\u0012\u0001\u0005\u0004%\tAa\u0012\t\u0011\tU\u0003\u0001)A\u0005\u0005\u0013B\u0011Ba\u0016\u0001\u0005\u0004%\tA!\u0017\t\u0011\tM\u0004\u0001)A\u0005\u00057B\u0011Ba!\u0001\u0005\u0004%IA!\"\t\u0011\t5\u0005\u0001)A\u0005\u0005\u000fC\u0011Ba$\u0001\u0005\u0004%IA!%\t\u0011\tU\u0005\u0001)A\u0005\u0005'C1Ba&\u0001\u0005\u0004%\t!a(\u0003\u001a\"A!1\u0016\u0001!\u0002\u0013\u0011Y\nC\u0005\u0003.\u0002\u0011\r\u0011\"\u0001\u0002j\"A!q\u0016\u0001!\u0002\u0013\tY\u000fC\u0005\u00032\u0002\u0011\r\u0011\"\u0001\u00034\"A!q\u0017\u0001!\u0002\u0013\u0011)\fC\u0005\u0003:\u0002\u0011\r\u0011\"\u0001\u0003\u0006\"A!1\u0018\u0001!\u0002\u0013\u00119\tC\u0005\u0003>\u0002\u0011\r\u0011\"\u0003\u0003@\"A!q\u0019\u0001!\u0002\u0013\u0011\t\rC\u0005\u0003J\u0002\u0011\r\u0011\"\u0003\u0003@\"A!1\u001a\u0001!\u0002\u0013\u0011\t\rC\u0005\u0003N\u0002\u0011\r\u0011\"\u0001\u0003@\"A!q\u001a\u0001!\u0002\u0013\u0011\t\rC\u0005\u0003R\u0002\u0011\r\u0011\"\u0001\u0003@\"A!1\u001b\u0001!\u0002\u0013\u0011\t\rC\u0005\u0003V\u0002\u0011\r\u0011\"\u0001\u0002j\"A!q\u001b\u0001!\u0002\u0013\tY\u000fC\u0005\u0003Z\u0002\u0011\r\u0011\"\u0001\u0003\\\"A!q\u001c\u0001!\u0002\u0013\u0011i\u000eC\u0005\u0003b\u0002\u0011\r\u0011\"\u0003\u0003\u0006\"A!1\u001d\u0001!\u0002\u0013\u00119\tC\u0005\u0003f\u0002\u0011\r\u0011\"\u0001\u0003\u0006\"A!q\u001d\u0001!\u0002\u0013\u00119\tC\u0005\u0003j\u0002\u0011\r\u0011\"\u0003\u0003\\\"A!1\u001e\u0001!\u0002\u0013\u0011i\u000eC\u0006\u0003n\u0002\u0011\r\u0011\"\u0001\u0002 \n=\b\u0002CB5\u0001\u0001\u0006IA!=\t\u0013\r-\u0004A1A\u0005\u0002\r5\u0004\u0002CB9\u0001\u0001\u0006Iaa\u001c\t\u0013\rM\u0004A1A\u0005\n\tM\u0006\u0002CB;\u0001\u0001\u0006IA!.\t\u0013\r]\u0004A1A\u0005\n\re\u0004\u0002CBD\u0001\u0001\u0006Iaa\u001f\t\u0013\r%\u0005A1A\u0005\u0002\r-\u0005\u0002CBW\u0001\u0001\u0006Ia!$\t\u0017\r=\u0006\u00011A\u0005\u0002\u0005}\u0015\u0011\u001e\u0005\f\u0007c\u0003\u0001\u0019!C\u0001\u0003?\u001b\u0019\f\u0003\u0005\u00048\u0002\u0001\u000b\u0015BAv\u0011%\u0019I\f\u0001b\u0001\n\u0003\tI\u000f\u0003\u0005\u0004<\u0002\u0001\u000b\u0011BAv\u0011%\u0019i\f\u0001b\u0001\n\u0003\tI\u000f\u0003\u0005\u0004@\u0002\u0001\u000b\u0011BAv\u0011%\u0019\t\r\u0001a\u0001\n\u0003\tI\u000fC\u0005\u0004D\u0002\u0001\r\u0011\"\u0001\u0004F\"A1\u0011\u001a\u0001!B\u0013\tY\u000fC\u0005\u0004L\u0002\u0011\r\u0011\"\u0001\u0002j\"A1Q\u001a\u0001!\u0002\u0013\tY\u000fC\u0005\u0004P\u0002\u0011\r\u0011\"\u0001\u0004R\"A1q\u001c\u0001!\u0002\u0013\u0019\u0019\u000eC\u0005\u0004b\u0002\u0001\r\u0011\"\u0001\u0004d\"I11\u001e\u0001A\u0002\u0013\u00051Q\u001e\u0005\t\u0007c\u0004\u0001\u0015)\u0003\u0004f\"I11\u001f\u0001A\u0002\u0013%!q\u0006\u0005\n\u0007k\u0004\u0001\u0019!C\u0005\u0007oD\u0001ba?\u0001A\u0003&!\u0011\u0007\u0005\n\u0007{\u0004\u0001\u0019!C\u0005\u0003SD\u0011ba@\u0001\u0001\u0004%I\u0001\"\u0001\t\u0011\u0011\u0015\u0001\u0001)Q\u0005\u0003WD1\u0002b\u0002\u0001\u0005\u0004%\t!a(\u0005\n!AA1\u0003\u0001!\u0002\u0013!Y\u0001C\u0006\u0005\u0016\u0001\u0011\r\u0011\"\u0001\u0002 \u000ee\u0004\u0002\u0003C\f\u0001\u0001\u0006Iaa\u001f\t\u000f\u0011e\u0001\u0001\"\u0011\u0002j\"9A1\u0004\u0001\u0005\u0002\u0011u\u0001b\u0003C\u0012\u0001\u0001\u0007I\u0011AAP\u0005\u000bC1\u0002\"\n\u0001\u0001\u0004%\t!a(\u0005(!AA1\u0006\u0001!B\u0013\u00119\tC\u0005\u0005.\u0001!\t!a(\u0003\u0006\"YAq\u0006\u0001C\u0002\u0013\u0005\u0011q\u0014C\u0019\u0011!!y\u0004\u0001Q\u0001\n\u0011M\u0002b\u0003C!\u0001\u0001\u0007I\u0011AAP\u0005_A1\u0002b\u0011\u0001\u0001\u0004%\t!a(\u0005F!AA\u0011\n\u0001!B\u0013\u0011\t\u0004C\u0006\u0005L\u0001\u0011\r\u0011\"\u0001\u0002 \u00125\u0003\u0002\u0003C+\u0001\u0001\u0006I\u0001b\u0014\t\u0017\u0011]\u0003A1A\u0005\u0002\u0005}E\u0011\f\u0005\t\t;\u0002\u0001\u0015!\u0003\u0005\\!YAq\f\u0001C\u0002\u0013\u0005\u0011q\u0014C'\u0011!!\t\u0007\u0001Q\u0001\n\u0011=\u0003b\u0003C2\u0001\t\u0007I\u0011AAP\tKB\u0001\u0002\"\u001b\u0001A\u0003%Aq\r\u0005\n\tW\u0002!\u0019!C\u0001\t[B\u0001\u0002\"\u001f\u0001A\u0003%Aq\u000e\u0005\n\tw\u0002!\u0019!C\u0001\u0005_A\u0001\u0002\" \u0001A\u0003%!\u0011\u0007\u0005\n\t\u007f\u0002!\u0019!C\u0005\t\u0003C\u0001\u0002\"'\u0001A\u0003%A1\u0011\u0005\n\t7\u0003!\u0019!C\u0001\u0005_A\u0001\u0002\"(\u0001A\u0003%!\u0011\u0007\u0005\b\t?\u0003A\u0011\u0002CQ\u0011-!\u0019\u000b\u0001a\u0001\n\u0003\ty\n\"*\t\u0017\u0011]\u0006\u00011A\u0005\u0002\u0005}E\u0011\u0018\u0005\t\t{\u0003\u0001\u0015)\u0003\u0005(\"IAq\u0018\u0001C\u0002\u0013%!Q\u0011\u0005\t\t\u0003\u0004\u0001\u0015!\u0003\u0003\b\"IA1\u0019\u0001A\u0002\u0013%\u0011\u0011\u001e\u0005\n\t\u000b\u0004\u0001\u0019!C\u0005\t\u000fD\u0001\u0002b3\u0001A\u0003&\u00111\u001e\u0005\n\t\u001b\u0004\u0001\u0019!C\u0005\u0005_A\u0011\u0002b4\u0001\u0001\u0004%I\u0001\"5\t\u0011\u0011U\u0007\u0001)Q\u0005\u0005cA1\u0002b6\u0001\u0001\u0004%\t!a(\u0005Z\"YAQ\u001c\u0001A\u0002\u0013\u0005\u0011q\u0014Cp\u0011!!\u0019\u000f\u0001Q!\n\u0011m\u0007b\u0002Cs\u0001\u0011\u0005Cq\u001d\u0005\b\t_\u0004A\u0011\tCy\u0011-)\t\u0002\u0001a\u0001\n\u0003\tyJ!\"\t\u0017\u0015M\u0001\u00011A\u0005\u0002\u0005}UQ\u0003\u0005\t\u000b3\u0001\u0001\u0015)\u0003\u0003\b\"YQ1\u0004\u0001C\u0002\u0013\u0005\u0011q\u0014BC\u0011!)i\u0002\u0001Q\u0001\n\t\u001d\u0005\"CC\u0010\u0001\u0011\u0005\u00111UC\u0011\u0011-)y\u0003AI\u0001\n\u0003\t\u0019+\"\r\t\u0017\u0015\u001d\u0003!%A\u0005\u0002\u0005\rV\u0011\u0007\u0005\b\u000b\u0013\u0002A\u0011BC&\u0011%)\u0019\u0007AI\u0001\n\u0013)\t\u0004C\u0004\u0006f\u0001!I!b\u001a\t\u000f\u0015=\u0004\u0001\"\u0003\u0006r!9Q\u0011\u0010\u0001\u0005\n\u0015m\u0004bBCL\u0001\u0011EQ\u0011\u0014\u0005\n\u000bG\u0003A\u0011AAP\u000bKCq!\",\u0001\t\u0003)y\u000bC\u0005\u0007\b\u0001\t\n\u0011\"\u0001\u0007\n!IaQ\u0002\u0001\u0012\u0002\u0013\u0005aq\u0002\u0005\b\r'\u0001A\u0011\u0001D\u000b\u0011\u001d1Y\u0003\u0001C\u0001\r[AqA\"\r\u0001\t\u0013!\t\u000bC\u0004\u00074\u0001!IA\"\u000e\t\u000f\u0019m\u0002\u0001\"\u0001\u0007>!Ia1\t\u0001\u0005\u0002\u0005}eQ\t\u0005\n\r\u001f\u0002A\u0011AAP\r#BqAb\u0016\u0001\t\u00031I\u0006C\u0004\u0007^\u0001!\tAb\u0018\t\u000f\u0019\u0015\u0004\u0001\"\u0001\u0007h!9aQ\u000f\u0001\u0005\n\u0019]\u0004\"\u0003D\\\u0001\u0011\u0005\u0011q\u0014D]\u0011\u001d1y\f\u0001C\u0001\r\u0003DqAb8\u0001\t\u00031\t\u000fC\u0005\u0007p\u0002\t\n\u0011\"\u0001\u0007r\"9aQ\u001f\u0001\u0005\u0002\u0011\u0005\u0006b\u0002D|\u0001\u0011\u0005a\u0011 \u0005\b\r{\u0004A\u0011\u0001D\u0000\u0011\u001d9\u0019\u0001\u0001C!\u000f\u000bAqa\"\u0003\u0001\t\u0003\u0012)\tC\u0004\b\f\u0001!\te\"\u0004\t\u000f\u001dM\u0001\u0001\"\u0011\b\u0016!9q\u0011\u0004\u0001\u0005B\u001dm\u0001bBD\u0010\u0001\u0011\u0005s\u0011\u0005\u0005\b\u000f_\u0001A\u0011BD\u0019\u0011%9y\u0004AI\u0001\n\u0013)\t\u0004C\u0004\bB\u0001!\teb\u0011\t\u000f\u001d%\u0003\u0001\"\u0003\bL!9q\u0011\u000b\u0001\u0005\n\u001dM\u0003bBD+\u0001\u0011\u0005qq\u000b\u0005\b\u000f7\u0002A\u0011\u0001CQ\u0011\u001d9i\u0006\u0001C\u0001\tC3qAa>\u0001\u0001\u0001\u0011I\u0010\u0003\u0005\u0003\u0010\u0005%D\u0011\u0001B~\u0011)\u0011i0!\u001bA\u0002\u0013%!q\u0006\u0005\u000b\u0005\u007f\fI\u00071A\u0005\n\r\u0005\u0001\"CB\u0007\u0003S\u0002\u000b\u0015\u0002B\u0019\u0011)\u0019y!!\u001bA\u0002\u0013%!q\u0006\u0005\u000b\u0007#\tI\u00071A\u0005\n\rM\u0001\"CB\f\u0003S\u0002\u000b\u0015\u0002B\u0019\u0011)\u0019I\"!\u001bA\u0002\u0013%!q\u0018\u0005\u000b\u00077\tI\u00071A\u0005\n\ru\u0001\"CB\u0011\u0003S\u0002\u000b\u0015\u0002Ba\u0011)\u0019\u0019#!\u001bC\u0002\u0013%1Q\u0005\u0005\n\u0007s\tI\u0007)A\u0005\u0007OA!ba\u000f\u0002j\u0011\u0005\u0011qTB\u001f\u00115\u00199%!\u001b\u0003\u0002\u0003\u0005I\u0011\u0001\u0001\u0004J!i11JA5\u0005\u0003\u0005\t\u0011\"\u0001\u0001\u0007\u001bBQb!\u0015\u0002j\t\u0005\t\u0011!C\u0001\u0001\rMsACD0\u0003?C\t!a)\bb\u0019Q\u0011QTAP\u0011\u0003\t\u0019kb\u0019\t\u0011\t=\u0011Q\u0012C\u0001\u000fKB!bb\u001a\u0002\u000e\n\u0007I\u0011AAu\u0011%9I'!$!\u0002\u0013\tY\u000f\u0003\u0006\bl\u00055%\u0019!C\u0001\u0003SD\u0011b\"\u001c\u0002\u000e\u0002\u0006I!a;\t\u0015\u001d=\u0014QRI\u0001\n\u00039\t\b\u0003\u0006\bv\u00055\u0015\u0013!C\u0001\u000fo\u0012a\u0002V1tWN+G/T1oC\u001e,'O\u0003\u0003\u0002\"\u0006\r\u0016!C:dQ\u0016$W\u000f\\3s\u0015\u0011\t)+a*\u0002\u000bM\u0004\u0018M]6\u000b\t\u0005%\u00161V\u0001\u0007CB\f7\r[3\u000b\u0005\u00055\u0016aA8sON9\u0001!!-\u0002>\u0006\u0015\u0007\u0003BAZ\u0003sk!!!.\u000b\u0005\u0005]\u0016!B:dC2\f\u0017\u0002BA^\u0003k\u0013a!\u00118z%\u00164\u0007\u0003BA`\u0003\u0003l!!a(\n\t\u0005\r\u0017q\u0014\u0002\f'\u000eDW\rZ;mC\ndW\r\u0005\u0003\u0002H\u00065WBAAe\u0015\u0011\tY-a)\u0002\u0011%tG/\u001a:oC2LA!a4\u0002J\n9Aj\\4hS:<\u0017!B:dQ\u0016$7\u0001\u0001\t\u0005\u0003\u007f\u000b9.\u0003\u0003\u0002Z\u0006}%!\u0005+bg.\u001c6\r[3ek2,'/S7qY\u00069A/Y:l'\u0016$XCAAp!\u0011\ty,!9\n\t\u0005\r\u0018q\u0014\u0002\b)\u0006\u001c8nU3u\u0003!!\u0018m]6TKR\u0004\u0013aD7bqR\u000b7o\u001b$bS2,(/Z:\u0016\u0005\u0005-\b\u0003BAZ\u0003[LA!a<\u00026\n\u0019\u0011J\u001c;\u0002!5\f\u0007\u0010V1tW\u001a\u000b\u0017\u000e\\;sKN\u0004\u0013!\u00045fC2$\b\u000e\u0016:bG.,'\u000f\u0005\u0004\u00024\u0006]\u00181`\u0005\u0005\u0003s\f)L\u0001\u0004PaRLwN\u001c\t\u0005\u0003\u007f\u000bi0\u0003\u0003\u0002\u0000\u0006}%!\u0004%fC2$\b\u000e\u0016:bG.,'/A\u0003dY>\u001c7\u000e\u0005\u0003\u0003\u0006\t-QB\u0001B\u0004\u0015\u0011\u0011I!a)\u0002\tU$\u0018\u000e\\\u0005\u0005\u0005\u001b\u00119AA\u0003DY>\u001c7.\u0001\u0004=S:LGO\u0010\u000b\r\u0005'\u0011)Ba\u0006\u0003\u001a\tm!Q\u0004\t\u0004\u0003\u007f\u0003\u0001bBAi\u0011\u0001\u0007\u0011Q\u001b\u0005\b\u00037D\u0001\u0019AAp\u0011\u001d\t9\u000f\u0003a\u0001\u0003WD\u0011\"a=\t!\u0003\u0005\r!!>\t\u0013\t\u0005\u0001\u0002%AA\u0002\t\r\u0011\u0001B2p]\u001a,\"Aa\t\u0011\t\t\u0015\"qE\u0007\u0003\u0003GKAA!\u000b\u0002$\nI1\u000b]1sW\u000e{gNZ\u0001\u0006G>tg\rI\u0001\u000e[\u0006D(+Z:vYR\u001c\u0016N_3\u0016\u0005\tE\u0002\u0003BAZ\u0005gIAA!\u000e\u00026\n!Aj\u001c8h\u00039i\u0017\r\u001f*fgVdGoU5{K\u0002\n1!\u001a8w+\t\u0011i\u0004\u0005\u0003\u0003&\t}\u0012\u0002\u0002B!\u0003G\u0013\u0001b\u00159be.,eN^\u0001\u0005K:4\b%A\u0002tKJ,\"A!\u0013\u0011\t\t-#\u0011K\u0007\u0003\u0005\u001bRAAa\u0014\u0002$\u0006Q1/\u001a:jC2L'0\u001a:\n\t\tM#Q\n\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018J\\:uC:\u001cW-\u0001\u0003tKJ\u0004\u0013!\u0002;bg.\u001cXC\u0001B.!\u0019\t\u0019L!\u0018\u0003b%!!qLA[\u0005\u0015\t%O]1za\u0011\u0011\u0019G!\u001c\u0011\r\u0005}&Q\rB5\u0013\u0011\u00119'a(\u0003\tQ\u000b7o\u001b\t\u0005\u0005W\u0012i\u0007\u0004\u0001\u0005\u0017\t=\u0004!!A\u0001\u0002\u000b\u0005!Q\u000f\u0002\u0004?\u0012\n\u0014\u0002\u0002B:\u0003C\fa\u0001^1tWN\u0004\u0013\u0003\u0002B<\u0005{\u0002B!a-\u0003z%!!1PA[\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!a-\u0003\u0000%!!\u0011QA[\u0005\r\te._\u0001\u0012SN\u001c\u0006.\u001e4gY\u0016l\u0015\r\u001d+bg.\u001cXC\u0001BD!\u0011\t\u0019L!#\n\t\t-\u0015Q\u0017\u0002\b\u0005>|G.Z1o\u0003II7o\u00155vM\u001adW-T1q)\u0006\u001c8n\u001d\u0011\u0002\u0013MDWO\u001a4mK&#WC\u0001BJ!\u0019\t\u0019,a>\u0002l\u0006Q1\u000f[;gM2,\u0017\n\u001a\u0011\u0002!A\f'\u000f^5uS>tGk\\%oI\u0016DXC\u0001BN!!\u0011iJa*\u0002l\u0006-XB\u0001BP\u0015\u0011\u0011\tKa)\u0002\u0013%lW.\u001e;bE2,'\u0002\u0002BS\u0003k\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011IKa(\u0003\u00075\u000b\u0007/A\tqCJ$\u0018\u000e^5p]R{\u0017J\u001c3fq\u0002\n\u0001B\\;n)\u0006\u001c8n]\u0001\n]VlG+Y:lg\u0002\nQbY8qS\u0016\u001c(+\u001e8oS:<WC\u0001B[!\u0019\t\u0019L!\u0018\u0002l\u0006q1m\u001c9jKN\u0014VO\u001c8j]\u001e\u0004\u0013AE:qK\u000e,H.\u0019;j_:,e.\u00192mK\u0012\f1c\u001d9fGVd\u0017\r^5p]\u0016s\u0017M\u00197fI\u0002\na$\u001a4gS\u000eLWM\u001c;UCN\\\u0007K]8dKN\u001cX*\u001e7uSBd\u0017.\u001a:\u0016\u0005\t\u0005\u0007\u0003BAZ\u0005\u0007LAA!2\u00026\n1Ai\\;cY\u0016\fq$\u001a4gS\u000eLWM\u001c;UCN\\\u0007K]8dKN\u001cX*\u001e7uSBd\u0017.\u001a:!\u0003m)gMZ5dS\u0016tG\u000fV1tW\u0012+(/\u0019;j_:4\u0015m\u0019;pe\u0006aRM\u001a4jG&,g\u000e\u001e+bg.$UO]1uS>tg)Y2u_J\u0004\u0013aE:qK\u000e,H.\u0019;j_:\fV/\u00198uS2,\u0017\u0001F:qK\u000e,H.\u0019;j_:\fV/\u00198uS2,\u0007%A\u000bta\u0016\u001cW\u000f\\1uS>tW*\u001e7uSBd\u0017.\u001a:\u0002-M\u0004XmY;mCRLwN\\'vYRL\u0007\u000f\\5fe\u0002\n\u0011$\\5o\r&t\u0017n\u001d5fI\u001a{'o\u00159fGVd\u0017\r^5p]\u0006QR.\u001b8GS:L7\u000f[3e\r>\u00148\u000b]3dk2\fG/[8oA\u0005y2\u000f]3dk2\fG/[8o)\u0006\u001c8\u000eR;sCRLwN\u001c+ie\u0016\u001cx\n\u001d;\u0016\u0005\tu\u0007CBAZ\u0003o\u0014\t$\u0001\u0011ta\u0016\u001cW\u000f\\1uS>tG+Y:l\tV\u0014\u0018\r^5p]RC'/Z:PaR\u0004\u0013aH5t'B,7-\u001e7bi&|g\u000e\u00165sKNDw\u000e\u001c3Ta\u0016\u001c\u0017NZ5fI\u0006\u0001\u0013n]*qK\u000e,H.\u0019;j_:$\u0006N]3tQ>dGm\u00159fG&4\u0017.\u001a3!\u0003u\u0019\b/Z2vY\u0006$\u0018n\u001c8UCN\\7\u000fT3tg\u0016\u000bHk\\*m_R\u001c\u0018AH:qK\u000e,H.\u0019;j_:$\u0016m]6t\u0019\u0016\u001c8/R9U_Ncw\u000e^:!\u0003\u0001*\u00070Z2vi>\u0014H)Z2p[6L7o]5p].KG\u000e\\%oi\u0016\u0014h/\u00197\u0002C\u0015DXmY;u_J$UmY8n[&\u001c8/[8o\u0017&dG.\u00138uKJ4\u0018\r\u001c\u0011\u00023Q\f7o\u001b)s_\u000e,7o\u001d*bi\u0016\u001c\u0015\r\\2vY\u0006$xN]\u000b\u0003\u0005c\u0004b!a-\u0002x\nM\b\u0003\u0002B{\u0003Sj\u0011\u0001\u0001\u0002\u001a)\u0006\u001c8\u000e\u0015:pG\u0016\u001c8OU1uK\u000e\u000bGnY;mCR|'o\u0005\u0003\u0002j\u0005EFC\u0001Bz\u0003A!x\u000e^1m%\u0016\u001cwN\u001d3t%\u0016\fG-\u0001\u000bu_R\fGNU3d_J$7OU3bI~#S-\u001d\u000b\u0005\u0007\u0007\u0019I\u0001\u0005\u0003\u00024\u000e\u0015\u0011\u0002BB\u0004\u0003k\u0013A!\u00168ji\"Q11BA8\u0003\u0003\u0005\rA!\r\u0002\u0007a$\u0013'A\tu_R\fGNU3d_J$7OU3bI\u0002\nA\u0003^8uC2,\u00050Z2vi>\u0014(+\u001e8US6,\u0017\u0001\u0007;pi\u0006dW\t_3dkR|'OU;o)&lWm\u0018\u0013fcR!11AB\u000b\u0011)\u0019Y!!\u001e\u0002\u0002\u0003\u0007!\u0011G\u0001\u0016i>$\u0018\r\\#yK\u000e,Ho\u001c:Sk:$\u0016.\\3!\u0003I\tgo\u001a+bg.\u0004&o\\2fgN\u0014\u0016\r^3\u0002-\u00054x\rV1tWB\u0013xnY3tgJ\u000bG/Z0%KF$Baa\u0001\u0004 !Q11BA>\u0003\u0003\u0005\rA!1\u0002'\u00054x\rV1tWB\u0013xnY3tgJ\u000bG/\u001a\u0011\u0002/I,hN\\5oOR\u000b7o[:Qe>\u001cWm]:SCR,WCAB\u0014!!\u0019Ic!\u000e\u00032\t\u0005WBAB\u0016\u0015\u0011\u0019ica\f\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0003\u0003\n\rE\"BAB\u001a\u0003\u0011Q\u0017M^1\n\t\r]21\u0006\u0002\u0012\u0007>t7-\u001e:sK:$\b*Y:i\u001b\u0006\u0004\u0018\u0001\u0007:v]:Lgn\u001a+bg.\u001c\bK]8dKN\u001c(+\u0019;fA\u0005aR\u000f\u001d3bi\u0016\u0014VO\u001c8j]\u001e$\u0016m]6Qe>\u001cWm]:SCR,GCBB\u0002\u0007\u007f\u0019\u0019\u0005\u0003\u0005\u0004B\u0005\r\u0005\u0019\u0001B\u0019\u0003\u0019!\u0018m]6JI\"A1QIAB\u0001\u0004\u0011\t-A\buCN\\\u0007K]8dKN\u001c(+\u0019;f\u0003\u0001{'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ:dQ\u0016$W\u000f\\3sIQ\u000b7o[*fi6\u000bg.Y4fe\u0012\"s-\u001a;Bm\u001e$\u0016m]6Qe>\u001cWm]:SCR,GC\u0001Ba\u0003\u0015{'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ:dQ\u0016$W\u000f\\3sIQ\u000b7o[*fi6\u000bg.Y4fe\u0012\"s-\u001a;Sk:t\u0017N\\4UCN\\7\u000f\u0015:pG\u0016\u001c8OU1uKR!!\u0011YB(\u0011!\u0019\t%a\"A\u0002\tE\u0012aQ8sO\u0012\n\u0007/Y2iK\u0012\u001a\b/\u0019:lIM\u001c\u0007.\u001a3vY\u0016\u0014H\u0005V1tWN+G/T1oC\u001e,'\u000f\n\u0013va\u0012\fG/Z!wOR\u000b7o\u001b)s_\u000e,7o\u001d*bi\u0016$baa\u0001\u0004V\r]\u0003\u0002CB!\u0003\u0013\u0003\rA!\r\t\u0011\re\u0013\u0011\u0012a\u0001\u00077\naA]3tk2$\b\u0007BB/\u0007K\u0002b!a0\u0004`\r\r\u0014\u0002BB1\u0003?\u0013\u0001\u0003R5sK\u000e$H+Y:l%\u0016\u001cX\u000f\u001c;\u0011\t\t-4Q\r\u0003\r\u0007O\u001a9&!A\u0001\u0002\u000b\u0005!Q\u000f\u0002\u0004?\u0012:\u0014A\u0007;bg.\u0004&o\\2fgN\u0014\u0016\r^3DC2\u001cW\u000f\\1u_J\u0004\u0013AC:vG\u000e,7o\u001d4vYV\u00111q\u000e\t\u0007\u0003g\u0013iFa\"\u0002\u0017M,8mY3tg\u001a,H\u000eI\u0001\f]Vlg)Y5mkJ,7/\u0001\u0007ok64\u0015-\u001b7ve\u0016\u001c\b%\u0001\u000blS2dW\r\u001a\"z\u001fRDWM]!ui\u0016l\u0007\u000f^\u000b\u0003\u0007w\u0002ba! \u0004\u0004\nERBAB@\u0015\u0011\u0019\tIa)\u0002\u000f5,H/\u00192mK&!1QQB@\u0005\u001dA\u0015m\u001d5TKR\fQc[5mY\u0016$')_(uQ\u0016\u0014\u0018\t\u001e;f[B$\b%\u0001\u0007uCN\\\u0017\t\u001e;f[B$8/\u0006\u0002\u0004\u000eB1\u00111\u0017B/\u0007\u001f\u0003ba!%\u0004\"\u000e\u001df\u0002BBJ\u0007;sAa!&\u0004\u001c6\u00111q\u0013\u0006\u0005\u00073\u000b\u0019.\u0001\u0004=e>|GOP\u0005\u0003\u0003oKAaa(\u00026\u00069\u0001/Y2lC\u001e,\u0017\u0002BBR\u0007K\u0013A\u0001T5ti*!1qTA[!\u0011\tyl!+\n\t\r-\u0016q\u0014\u0002\t)\u0006\u001c8.\u00138g_\u0006iA/Y:l\u0003R$X-\u001c9ug\u0002\nq\u0002^1tWN\u001cVoY2fgN4W\u000f\\\u0001\u0014i\u0006\u001c8n]*vG\u000e,7o\u001d4vY~#S-\u001d\u000b\u0005\u0007\u0007\u0019)\fC\u0005\u0004\fq\n\t\u00111\u0001\u0002l\u0006\u0001B/Y:lgN+8mY3tg\u001a,H\u000eI\u0001\u0007o\u0016Lw\r\u001b;\u0002\u000f],\u0017n\u001a5uA\u0005AQ.\u001b8TQ\u0006\u0014X-A\u0005nS:\u001c\u0006.\u0019:fA\u0005A\u0001O]5pe&$\u00180\u0001\u0007qe&|'/\u001b;z?\u0012*\u0017\u000f\u0006\u0003\u0004\u0004\r\u001d\u0007\"CB\u0006\u0007\u0006\u0005\t\u0019AAv\u0003%\u0001(/[8sSRL\b%A\u0004ti\u0006<W-\u00133\u0002\u0011M$\u0018mZ3JI\u0002\nAA\\1nKV\u001111\u001b\t\u0005\u0007+\u001cY.\u0004\u0002\u0004X*!1\u0011\\B\u0019\u0003\u0011a\u0017M\\4\n\t\ru7q\u001b\u0002\u0007'R\u0014\u0018N\\4\u0002\u000b9\fW.\u001a\u0011\u0002\rA\f'/\u001a8u+\t\u0019)\u000f\u0005\u0003\u0002@\u000e\u001d\u0018\u0002BBu\u0003?\u0013A\u0001U8pY\u0006Q\u0001/\u0019:f]R|F%Z9\u0015\t\r\r1q\u001e\u0005\n\u0007\u0017Q\u0015\u0011!a\u0001\u0007K\fq\u0001]1sK:$\b%A\bu_R\fGNU3tk2$8+\u001b>f\u0003M!x\u000e^1m%\u0016\u001cX\u000f\u001c;TSj,w\fJ3r)\u0011\u0019\u0019a!?\t\u0013\r-Q*!AA\u0002\tE\u0012\u0001\u0005;pi\u0006d'+Z:vYR\u001c\u0016N_3!\u0003=\u0019\u0017\r\\2vY\u0006$X\r\u001a+bg.\u001c\u0018aE2bY\u000e,H.\u0019;fIR\u000b7o[:`I\u0015\fH\u0003BB\u0002\t\u0007A\u0011ba\u0003Q\u0003\u0003\u0005\r!a;\u0002!\r\fGnY;mCR,G\rV1tWN\u0004\u0013a\u0007;bg.\u001cV\r^#yG2,H-\u001a7jgRDU\r\u001c9fe>\u0003H/\u0006\u0002\u0005\fA1\u00111WA|\t\u001b\u0001B!a0\u0005\u0010%!A\u0011CAP\u0005I!\u0016m]6TKR,\u0005p\u00197vI\u0016d\u0017n\u001d;\u00029Q\f7o[*fi\u0016C8\r\\;eK2L7\u000f\u001e%fYB,'o\u00149uA\u0005y!/\u001e8oS:<G+Y:lgN+G/\u0001\tsk:t\u0017N\\4UCN\\7oU3uA\u0005a!/\u001e8oS:<G+Y:lg\u0006!2o\\7f\u0003R$X-\u001c9u'V\u001c7-Z3eK\u0012$BAa\"\u0005 !9A\u0011E,A\u0002\tE\u0012a\u0001;jI\u0006A\u0011n\u001d.p[\nLW-\u0001\u0007jgj{WNY5f?\u0012*\u0017\u000f\u0006\u0003\u0004\u0004\u0011%\u0002\"CB\u00063\u0006\u0005\t\u0019\u0001BD\u0003%I7OW8nE&,\u0007%A\u0005jg\n\u000b'O]5fe\u0006I\"-\u0019:sS\u0016\u0014\b+\u001a8eS:<G*Y;oG\"$\u0016m]6t+\t!\u0019\u0004\u0005\u0005\u0004~\u0011U\u00121\u001eC\u001d\u0013\u0011!9da \u0003\u000f!\u000b7\u000f['baB!\u0011q\u0018C\u001e\u0013\u0011!i$a(\u00031\t\u000b'O]5feB+g\u000eZ5oO2\u000bWO\\2i)\u0006\u001c8.\u0001\u000ecCJ\u0014\u0018.\u001a:QK:$\u0017N\\4MCVt7\r\u001b+bg.\u001c\b%\u0001\u000fmCN$(+Z:pkJ\u001cWm\u00144gKJ4\u0015-\u001b7M_\u001e$\u0016.\\3\u0002A1\f7\u000f\u001e*fg>,(oY3PM\u001a,'OR1jY2{w\rV5nK~#S-\u001d\u000b\u0005\u0007\u0007!9\u0005C\u0005\u0004\f}\u000b\t\u00111\u0001\u00032\u0005iB.Y:u%\u0016\u001cx.\u001e:dK>3g-\u001a:GC&dGj\\4US6,\u0007%\u0001\u0007qK:$\u0017N\\4UCN\\7/\u0006\u0002\u0005PA!\u0011q\u0018C)\u0013\u0011!\u0019&a(\u0003-A+g\u000eZ5oOR\u000b7o[:Cs2{7-\u00197jif\fQ\u0002]3oI&tw\rV1tWN\u0004\u0013!E:qK\u000e,H.\u0019;bE2,G+Y:lgV\u0011A1\f\t\u0007\u0007{\u001a\u0019)a;\u0002%M\u0004XmY;mCR\f'\r\\3UCN\\7\u000fI\u0001\u0019a\u0016tG-\u001b8h'B,7-\u001e7bi\u0006\u0014G.\u001a+bg.\u001c\u0018!\u00079f]\u0012LgnZ*qK\u000e,H.\u0019;bE2,G+Y:lg\u0002\n\u0011\u0002^1tW&sgm\\:\u0016\u0005\u0011\u001d\u0004\u0003CB?\tk\u0011\tda*\u0002\u0015Q\f7o[%oM>\u001c\b%A\ftk\u000e\u001cWm]:gk2$\u0016m]6EkJ\fG/[8ogV\u0011Aq\u000e\t\u0005\tc\")(\u0004\u0002\u0005t)!!Q\u0015B\u0004\u0013\u0011!9\bb\u001d\u0003\u001dA+'oY3oi&dW\rS3ba\u0006A2/^2dKN\u001ch-\u001e7UCN\\G)\u001e:bi&|gn\u001d\u0011\u00021\u0015C6)\u0012)U\u0013>su\f\u0015*J\u001dR{\u0016J\u0014+F%Z\u000bE*A\rF1\u000e+\u0005\u000bV%P\u001d~\u0003&+\u0013(U?&sE+\u0012*W\u00032\u0003\u0013\u0001\u0005:fG\u0016tG/\u0012=dKB$\u0018n\u001c8t+\t!\u0019\t\u0005\u0005\u0004~\u0011UBQ\u0011CJ!\u0011!9\tb$\u000f\t\u0011%E1\u0012\t\u0005\u0007+\u000b),\u0003\u0003\u0005\u000e\u0006U\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0004^\u0012E%\u0002\u0002CG\u0003k\u0003\u0002\"a-\u0005\u0016\u0006-(\u0011G\u0005\u0005\t/\u000b)L\u0001\u0004UkBdWMM\u0001\u0012e\u0016\u001cWM\u001c;Fq\u000e,\u0007\u000f^5p]N\u0004\u0013!B3q_\u000eD\u0017AB3q_\u000eD\u0007%A\bbI\u0012\u0004VM\u001c3j]\u001e$\u0016m]6t)\t\u0019\u0019!\u0001\tns2{7-\u00197jifdUM^3mgV\u0011Aq\u0015\t\u0007\u0003g\u0013i\u0006\"+\u0011\t\u0011-F\u0011\u0017\b\u0005\u0003\u007f#i+\u0003\u0003\u00050\u0006}\u0015\u0001\u0004+bg.dunY1mSRL\u0018\u0002\u0002CZ\tk\u0013A\u0002V1tW2{7-\u00197jifTA\u0001b,\u0002 \u0006!R.\u001f'pG\u0006d\u0017\u000e^=MKZ,Gn]0%KF$Baa\u0001\u0005<\"I11B:\u0002\u0002\u0003\u0007AqU\u0001\u0012[fdunY1mSRLH*\u001a<fYN\u0004\u0013a\u00067fO\u0006\u001c\u0017\u0010T8dC2LG/_,bSR\u0014Vm]3u\u0003aaWmZ1ds2{7-\u00197jif<\u0016-\u001b;SKN,G\u000fI\u0001\u0015GV\u0014(/\u001a8u\u0019>\u001c\u0017\r\\5us&sG-\u001a=\u00021\r,(O]3oi2{7-\u00197jifLe\u000eZ3y?\u0012*\u0017\u000f\u0006\u0003\u0004\u0004\u0011%\u0007\"CB\u0006q\u0006\u0005\t\u0019AAv\u0003U\u0019WO\u001d:f]RdunY1mSRL\u0018J\u001c3fq\u0002\n\u0011\u0004\\1ti2{7-\u00197jif<\u0016-\u001b;SKN,G\u000fV5nK\u0006iB.Y:u\u0019>\u001c\u0017\r\\5us^\u000b\u0017\u000e\u001e*fg\u0016$H+[7f?\u0012*\u0017\u000f\u0006\u0003\u0004\u0004\u0011M\u0007\"CB\u0006w\u0006\u0005\t\u0019\u0001B\u0019\u0003ia\u0017m\u001d;M_\u000e\fG.\u001b;z/\u0006LGOU3tKR$\u0016.\\3!\u00035awnY1mSRLx+Y5ugV\u0011A1\u001c\t\u0007\u0003g\u0013iF!\r\u0002#1|7-\u00197jif<\u0016-\u001b;t?\u0012*\u0017\u000f\u0006\u0003\u0004\u0004\u0011\u0005\b\"CB\u0006}\u0006\u0005\t\u0019\u0001Cn\u00039awnY1mSRLx+Y5ug\u0002\n\u0001c]2iK\u0012,H.\u00192mKF+X-^3\u0016\u0005\u0011%\bCBB\u0015\tW\fi,\u0003\u0003\u0005n\u000e-\"!F\"p]\u000e,(O]3oi2Kgn[3e#V,W/Z\u0001\u000fg\u000eDW\rZ;mS:<Wj\u001c3f+\t!\u0019\u0010\u0005\u0003\u0005v\u0016-a\u0002\u0002C|\u000b\u000fqA\u0001\"?\u0006\u00069!A1`C\u0002\u001d\u0011!i0\"\u0001\u000f\t\rUEq`\u0005\u0003\u0003[KA!!+\u0002,&!\u0011QUAT\u0013\u0011\t\t+a)\n\t\u0015%\u0011qT\u0001\u000f'\u000eDW\rZ;mS:<Wj\u001c3f\u0013\u0011)i!b\u0004\u0003\u001dM\u001b\u0007.\u001a3vY&tw-T8eK*!Q\u0011BAP\u0003Y)W.\u001b;uK\u0012$\u0016m]6TSj,w+\u0019:oS:<\u0017AG3nSR$X\r\u001a+bg.\u001c\u0016N_3XCJt\u0017N\\4`I\u0015\fH\u0003BB\u0002\u000b/A!ba\u0003\u0002\b\u0005\u0005\t\u0019\u0001BD\u0003])W.\u001b;uK\u0012$\u0016m]6TSj,w+\u0019:oS:<\u0007%\u0001\u0015ee>\u0004H+Y:l\u0013:4w.Q2dk6,H.\u00192mKN|e\u000eV1tW\u000e{W\u000e\u001d7fi&|g.A\u0015ee>\u0004H+Y:l\u0013:4w.Q2dk6,H.\u00192mKN|e\u000eV1tW\u000e{W\u000e\u001d7fi&|g\u000eI\u0001\u000fC\u0012$\u0007+\u001a8eS:<G+Y:l)!\u0019\u0019!b\t\u0006(\u0015-\u0002\u0002CC\u0013\u0003\u001f\u0001\r!a;\u0002\u000b%tG-\u001a=\t\u0015\u0015%\u0012q\u0002I\u0001\u0002\u0004\u00119)\u0001\u0007sKN|GN^3SC\u000e\\7\u000f\u0003\u0006\u0006.\u0005=\u0001\u0013!a\u0001\u0005\u000f\u000bAb\u001d9fGVd\u0017\r^1cY\u0016\f\u0001$\u00193e!\u0016tG-\u001b8h)\u0006\u001c8\u000e\n3fM\u0006,H\u000e\u001e\u00133+\t)\u0019D\u000b\u0003\u0003\b\u0016U2FAC\u001c!\u0011)I$b\u0011\u000e\u0005\u0015m\"\u0002BC\u001f\u000b\u007f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\t\u0015\u0005\u0013QW\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BC#\u000bw\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003a\tG\r\u001a)f]\u0012Lgn\u001a+bg.$C-\u001a4bk2$HeM\u0001\u0014I\u0016\fX/Z;f)\u0006\u001c8N\u0012:p[2K7\u000f\u001e\u000b\u000b\u0005'+i%\"\u0015\u0006V\u0015}\u0003\u0002CC(\u0003+\u0001\r\u0001\"\"\u0002\r\u0015DXmY%e\u0011!)\u0019&!\u0006A\u0002\u0011\u0015\u0015\u0001\u00025pgRD\u0001\"b\u0016\u0002\u0016\u0001\u0007Q\u0011L\u0001\u0005Y&\u001cH\u000f\u0005\u0004\u0004~\u0015m\u00131^\u0005\u0005\u000b;\u001ayHA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\bBCC1\u0003+\u0001\n\u00111\u0001\u0003\b\u0006Y1\u000f]3dk2\fG/\u001b<f\u0003u!W-];fk\u0016$\u0016m]6Ge>lG*[:uI\u0011,g-Y;mi\u0012\"\u0014\u0001\u00055bg\u0006#H/Z7qi>s\u0007j\\:u)\u0019\u00119)\"\u001b\u0006n!AQ1NA\r\u0001\u0004\tY/A\u0005uCN\\\u0017J\u001c3fq\"AQ1KA\r\u0001\u0004!))\u0001\u000fjgR\u000b7o[#yG2,H-\u001a3fI>sW\t_3d\u001fJtu\u000eZ3\u0015\u0011\t\u001dU1OC;\u000boB\u0001\"\"\n\u0002\u001c\u0001\u0007\u00111\u001e\u0005\t\u000b\u001f\nY\u00021\u0001\u0005\u0006\"AQ1KA\u000e\u0001\u0004!))A\u0006eKF,X-^3UCN\\G\u0003CC?\u000b\u001f+\t*b%\u0011\r\u0005M\u0016q_C@!)\t\u0019,\"!\u0002l\u0016\u0015%qQ\u0005\u0005\u000b\u0007\u000b)L\u0001\u0004UkBdWm\r\t\u0005\tW+9)\u0003\u0003\u0006\n\u0016-%!\u0002,bYV,\u0017\u0002BCG\u0003k\u00131\"\u00128v[\u0016\u0014\u0018\r^5p]\"AQqJA\u000f\u0001\u0004!)\t\u0003\u0005\u0006T\u0005u\u0001\u0019\u0001CC\u0011!))*!\bA\u0002\u0015\u0015\u0015aC7bq2{7-\u00197jif\f\u0011\u0003Z3rk\u0016,X\rV1tW\"+G\u000e]3s)))i(b'\u0006\u001e\u0016}U\u0011\u0015\u0005\t\u000b\u001f\ny\u00021\u0001\u0005\u0006\"AQ1KA\u0010\u0001\u0004!)\t\u0003\u0005\u0006\u0016\u0006}\u0001\u0019ACC\u0011!)\t'a\bA\u0002\t\u001d\u0015a\u0006:fg\u0016$H)\u001a7bsN\u001b\u0007.\u001a3vY\u0016$\u0016.\\3s)\u0011\u0019\u0019!b*\t\u0011\u0015%\u0016\u0011\u0005a\u0001\u000bW\u000b1\"\\5o\u0019>\u001c\u0017\r\\5usB1\u00111WA|\tS\u000bQB]3t_V\u00148-Z(gM\u0016\u0014H\u0003DCY\u000bw+i,b0\u0006B\u0016\u0015\u0007CCAZ\u000b\u0003+\u0019La\"\u0002lB1\u00111WA|\u000bk\u0003B!a0\u00068&!Q\u0011XAP\u0005=!\u0016m]6EKN\u001c'/\u001b9uS>t\u0007\u0002CC(\u0003G\u0001\r\u0001\"\"\t\u0011\u0015M\u00131\u0005a\u0001\t\u000bC\u0001\"\"&\u0002$\u0001\u0007A\u0011\u0016\u0005\u000b\u000b\u0007\f\u0019\u0003%AA\u0002\u0005-\u0018\u0001\u0003;bg.\u001c\u0005/^:\t\u0015\u0015\u001d\u00171\u0005I\u0001\u0002\u0004)I-A\fuCN\\'+Z:pkJ\u001cW-Q:tS\u001etW.\u001a8ugBAAqQCf\t\u000b+i-\u0003\u0003\u0003*\u0012E\u0005\u0003\u0003CD\u000b\u0017$)I!\r)\r\u0005\rR\u0011[Co!\u0019\t\u0019,b5\u0006X&!QQ[A[\u0005\u0019!\bN]8xgB!!QECm\u0013\u0011)Y.a)\u00039Q\u000b7o\u001b(piN+'/[1mSj\f'\r\\3Fq\u000e,\u0007\u000f^5p]F:a\u0004\"\"\u0006`\u001a\u0015\u0011'C\u0012\u0006b\u0016%X1`Cv+\u0011)\u0019/\":\u0016\u0005\u0011\u0015E\u0001CCt\u0003'\u0014\r!\"=\u0003\u0003QKA!b;\u0006n\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIERA!b<\u00026\u00061A\u000f\u001b:poN\fBAa\u001e\u0006tB!QQ_C|\u001d\u0011\t\u0019l!(\n\t\u0015e8Q\u0015\u0002\n)\"\u0014xn^1cY\u0016\f\u0014bIC\u007f\u000b\u007f4\t!b<\u000f\t\u0005MVq`\u0005\u0005\u000b_\f),M\u0004#\u0003g\u000b)Lb\u0001\u0003\u000bM\u001c\u0017\r\\12\u0007\u0019*9.A\fsKN|WO]2f\u001f\u001a4WM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011a1\u0002\u0016\u0005\u0003W,)$A\fsKN|WO]2f\u001f\u001a4WM\u001d\u0013eK\u001a\fW\u000f\u001c;%kU\u0011a\u0011\u0003\u0016\u0005\u000b\u0013,)$\u0001\u000bqe\u0016\u0004\u0018M]3MCVt7\r[5oOR\u000b7o\u001b\u000b\u0013\u000bk39B\"\u0007\u0007\u001c\u0019ua\u0011\u0005D\u0012\rK19\u0003\u0003\u0005\u0006P\u0005%\u0002\u0019\u0001CC\u0011!)\u0019&!\u000bA\u0002\u0011\u0015\u0005\u0002CC\u0013\u0003S\u0001\r!a;\t\u0011\u0019}\u0011\u0011\u0006a\u0001\u000b\u000b\u000bA\u0002^1tW2{7-\u00197jifD\u0001\"\"\u0019\u0002*\u0001\u0007!q\u0011\u0005\t\u000b\u0007\fI\u00031\u0001\u0002l\"AQqYA\u0015\u0001\u0004)I\r\u0003\u0005\u0007*\u0005%\u0002\u0019\u0001B\u0019\u0003)a\u0017-\u001e8dQRKW.Z\u0001\ti\u0006\u001c8NT1nKR!AQ\u0011D\u0018\u0011!!\t#a\u000bA\u0002\tE\u0012AE7bs\n,g)\u001b8jg\"$\u0016m]6TKR\fqcZ3u\u00032dwn^3e\u0019>\u001c\u0017\r\\5us2+g/\u001a7\u0015\t\u0011%fq\u0007\u0005\t\rs\ty\u00031\u0001\u00032\u000591-\u001e:US6,\u0017\u0001E4fi2{7-\u00197jifLe\u000eZ3y)\u0011\tYOb\u0010\t\u0011\u0019\u0005\u0013\u0011\u0007a\u0001\tS\u000b\u0001\u0002\\8dC2LG/_\u0001\u001fO\u0016$8i\\7qY\u0016$X\r\\=Fq\u000edW\u000fZ3e)\u0006\u001c8.\u00134B]f$BAa%\u0007H!Aa\u0011JA\u001a\u0001\u00041Y%A\bi_N$Hk\\#yK\u000e,Ho\u001c:t!!\u0019i\b\"\u000e\u0005\u0006\u001a5\u0003CBB?\u0007\u0007#))A\u0013bE>\u0014HoU5oG\u0016\u001cu.\u001c9mKR,G._#yG2,H-\u001a3P]\u001a\u000b\u0017\u000e\\;sKR!11\u0001D*\u0011!1)&!\u000eA\u0002\u0005-\u0018AD5oI\u0016D\u0018J\u001c+bg.\u001cV\r^\u0001\u0018Q\u0006tG\r\\3UCN\\w)\u001a;uS:<'+Z:vYR$Baa\u0001\u0007\\!AA\u0011EA\u001c\u0001\u0004\u0011\t$A\ndC:4U\r^2i\u001b>\u0014XMU3tk2$8\u000f\u0006\u0003\u0003\b\u001a\u0005\u0004\u0002\u0003D2\u0003s\u0001\rA!\r\u0002\tML'0Z\u0001\u0015Q\u0006tG\r\\3Tk\u000e\u001cWm]:gk2$\u0016m]6\u0015\r\r\ra\u0011\u000eD6\u0011!!\t#a\u000fA\u0002\tE\u0002\u0002CB-\u0003w\u0001\rA\"\u001c1\t\u0019=d1\u000f\t\u0007\u0003\u007f\u001byF\"\u001d\u0011\t\t-d1\u000f\u0003\r\u0005_2Y'!A\u0001\u0002\u000b\u0005!QO\u0001/K6\u0004H/\u001f+bg.LeNZ8BG\u000e,X.\u001e7bE2,7/\u00118e\u001d>$\u0018NZ=EC\u001e\u001c6\r[3ek2,'\u000f\u0006\b\u0004\u0004\u0019ed1\u0010DE\r'3)Jb-\t\u0011\r\u0005\u0013Q\ba\u0001\u0005cA\u0001B\" \u0002>\u0001\u0007aqP\u0001\u0005i\u0006\u001c8\u000e\r\u0003\u0007\u0002\u001a\u0015\u0005CBA`\u0005K2\u0019\t\u0005\u0003\u0003l\u0019\u0015E\u0001\u0004DD\rw\n\t\u0011!A\u0003\u0002\tU$aA0%e!Aa1RA\u001f\u0001\u00041i)\u0001\u0004sK\u0006\u001cxN\u001c\t\u0005\u0005K1y)\u0003\u0003\u0007\u0012\u0006\r&!\u0004+bg.,e\u000e\u001a*fCN|g\u000e\u0003\u0005\u0004Z\u0005u\u0002\u0019\u0001B?\u0011!19*!\u0010A\u0002\u0019e\u0015\u0001D1dGVlW\u000b\u001d3bi\u0016\u001c\bCBBI\r73y*\u0003\u0003\u0007\u001e\u000e\u0015&aA*fcB2a\u0011\u0015DU\r_\u0003\u0002B!\u0002\u0007$\u001a\u001dfQV\u0005\u0005\rK\u00139AA\u0007BG\u000e,X.\u001e7bi>\u0014hK\r\t\u0005\u0005W2I\u000b\u0002\u0007\u0007,\u001aU\u0015\u0011!A\u0001\u0006\u0003\u0011)HA\u0002`IM\u0002BAa\u001b\u00070\u0012aa\u0011\u0017DK\u0003\u0003\u0005\tQ!\u0001\u0003v\t\u0019q\f\n\u001b\t\u0011\u0019U\u0016Q\ba\u0001\t7\f1\"\\3ue&\u001c\u0007+Z1lg\u00061R.\u0019:l!\u0006\u0014H/\u001b;j_:\u001cu.\u001c9mKR,G\r\u0006\u0003\u0004\u0004\u0019m\u0006\u0002\u0003D_\u0003\u007f\u0001\r!a;\u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u0001\u0011Q\u0006tG\r\\3GC&dW\r\u001a+bg.$\u0002ba\u0001\u0007D\u001a\u0015gq\u001b\u0005\t\tC\t\t\u00051\u0001\u00032!AaqYA!\u0001\u00041I-A\u0003ti\u0006$X\r\u0005\u0003\u0007L\u001aEg\u0002\u0002C}\r\u001bLAAb4\u0002$\u0006IA+Y:l'R\fG/Z\u0005\u0005\r'4)NA\u0005UCN\\7\u000b^1uK*!aqZAR\u0011!1Y)!\u0011A\u0002\u0019e\u0007\u0003\u0002B\u0013\r7LAA\"8\u0002$\n\u0001B+Y:l\r\u0006LG.\u001a3SK\u0006\u001cxN\\\u0001\u0006C\n|'\u000f\u001e\u000b\u0007\u0007\u00071\u0019Ob:\t\u0011\u0019\u0015\u00181\ta\u0001\t\u000b\u000bq!\\3tg\u0006<W\r\u0003\u0006\u0007j\u0006\r\u0003\u0013!a\u0001\rW\f\u0011\"\u001a=dKB$\u0018n\u001c8\u0011\r\u0005M\u0016q\u001fDw!\u0011\u0019\t*b>\u0002\u001f\u0005\u0014wN\u001d;%I\u00164\u0017-\u001e7uII*\"Ab=+\t\u0019-XQG\u0001\bgV\u001c\b/\u001a8e\u00039\tG\r\u001a*v]:Lgn\u001a+bg.$Baa\u0001\u0007|\"AA\u0011EA%\u0001\u0004\u0011\t$A\tsK6|g/\u001a*v]:Lgn\u001a+bg.$Baa\u0001\b\u0002!AA\u0011EA&\u0001\u0004\u0011\t$\u0001\u000bhKR\u001c6\r[3ek2\f'\r\\3Cs:\u000bW.\u001a\u000b\u0005\u0003{;9\u0001\u0003\u0005\u0004P\u00065\u0003\u0019\u0001CC\u00035I7oU2iK\u0012,H.\u00192mK\u0006q\u0011\r\u001a3TG\",G-\u001e7bE2,G\u0003BB\u0002\u000f\u001fA\u0001b\"\u0005\u0002R\u0001\u0007\u0011QX\u0001\fg\u000eDW\rZ;mC\ndW-A\tsK6|g/Z*dQ\u0016$W\u000f\\1cY\u0016$Baa\u0001\b\u0018!Aq\u0011CA*\u0001\u0004\ti,A\u000bhKR\u001cvN\u001d;fIR\u000b7o[*fiF+X-^3\u0016\u0005\u001du\u0001CBB?\u000b7\u0012\u0019\"\u0001\u0007fq\u0016\u001cW\u000f^8s\u0019>\u001cH\u000f\u0006\u0005\u0004\u0004\u001d\rrQED\u0014\u0011!)y%a\u0016A\u0002\u0011\u0015\u0005\u0002CC*\u0003/\u0002\r\u0001\"\"\t\u0011\u0019-\u0015q\u000ba\u0001\u000fS\u0001B!a0\b,%!qQFAP\u0005I)\u00050Z2vi>\u0014Hj\\:t%\u0016\f7o\u001c8\u0002?\rDWmY6B]\u0012\u001cVOY7jiN\u0003XmY;mCR\f'\r\\3UCN\\7\u000f\u0006\u0005\u0003\b\u001eMrqGD\u001e\u0011!9)$!\u0017A\u0002\tE\u0012!E2veJ,g\u000e\u001e+j[\u0016l\u0015\u000e\u001c7jg\"Aq\u0011HA-\u0001\u0004\u0011\t-A\u0005uQJ,7\u000f[8mI\"QqQHA-!\u0003\u0005\rAa\"\u0002'\r,8\u000f^8nSj,G\r\u00165sKNDw\u000e\u001c3\u0002S\rDWmY6B]\u0012\u001cVOY7jiN\u0003XmY;mCR\f'\r\\3UCN\\7\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003Y\u0019\u0007.Z2l'B,7-\u001e7bi\u0006\u0014G.\u001a+bg.\u001cH\u0003\u0002BD\u000f\u000bB\u0001bb\u0012\u0002^\u0001\u0007!\u0011G\u0001\u0015[&tG+[7f)>\u001c\u0006/Z2vY\u0006$\u0018n\u001c8\u0002\u001f\u001d,G\u000fT8dC2LG/_,bSR$BA!\r\bN!AqqJA0\u0001\u0004!I+A\u0003mKZ,G.\u0001\u000ed_6\u0004X\u000f^3WC2LG\rT8dC2LG/\u001f'fm\u0016d7\u000f\u0006\u0002\u0005(\u0006!R\r_3dkR|'\u000fR3d_6l\u0017n]:j_:$Baa\u0001\bZ!AQqJA2\u0001\u0004!))A\tsK\u000e|W\u000e];uK2{7-\u00197jif\fQ\"\u001a=fGV$xN]!eI\u0016$\u0017A\u0004+bg.\u001cV\r^'b]\u0006<WM\u001d\t\u0005\u0003\u007f\u000bii\u0005\u0003\u0002\u000e\u0006EFCAD1\u0003U!\u0016iU&`'&SVi\u0018+P?^\u000b%KT0L\u0013\n\u000ba\u0003V!T\u0017~\u001b\u0016JW#`)>{v+\u0011*O?.K%\tI\u0001\u0019\u0005\u0006\u0013&+S#S?2{uiR%O\u000f~Ke\nV#S-\u0006c\u0015!\u0007\"B%JKUIU0M\u001f\u001e;\u0015JT$`\u0013:#VI\u0015,B\u0019\u0002\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"TCAD:U\u0011\t)0\"\u000e\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136+\t9IH\u000b\u0003\u0003\u0004\u0015U\u0002"
)
public class TaskSetManager implements Schedulable, Logging {
   public final TaskSchedulerImpl org$apache$spark$scheduler$TaskSetManager$$sched;
   private final TaskSet taskSet;
   private final int maxTaskFailures;
   private final Option healthTracker;
   private final Clock clock;
   private final SparkConf conf;
   private final long maxResultSize;
   private final SparkEnv env;
   private final SerializerInstance ser;
   private final Task[] tasks;
   private final boolean isShuffleMapTasks;
   private final Option shuffleId;
   private final Map partitionToIndex;
   private final int numTasks;
   private final int[] copiesRunning;
   private final boolean speculationEnabled;
   private final double efficientTaskProcessMultiplier;
   private final double efficientTaskDurationFactor;
   private final double speculationQuantile;
   private final double speculationMultiplier;
   private final int minFinishedForSpeculation;
   private final Option speculationTaskDurationThresOpt;
   private final boolean isSpeculationThresholdSpecified;
   private final boolean speculationTasksLessEqToSlots;
   private final Option executorDecommissionKillInterval;
   private final Option taskProcessRateCalculator;
   private final boolean[] successful;
   private final int[] numFailures;
   private final HashSet killedByOtherAttempt;
   private final List[] taskAttempts;
   private int tasksSuccessful;
   private final int weight;
   private final int minShare;
   private int priority;
   private final int stageId;
   private final String name;
   private Pool parent;
   private long totalResultSize;
   private int calculatedTasks;
   private final Option taskSetExcludelistHelperOpt;
   private final HashSet runningTasksSet;
   private boolean isZombie;
   private final HashMap barrierPendingLaunchTasks;
   private long lastResourceOfferFailLogTime;
   private final PendingTasksByLocality pendingTasks;
   private final HashSet speculatableTasks;
   private final PendingTasksByLocality pendingSpeculatableTasks;
   private final HashMap taskInfos;
   private final PercentileHeap successfulTaskDurations;
   private final long EXCEPTION_PRINT_INTERVAL;
   private final HashMap recentExceptions;
   private final long epoch;
   private Enumeration.Value[] myLocalityLevels;
   private final boolean legacyLocalityWaitReset;
   private int currentLocalityIndex;
   private long lastLocalityWaitResetTime;
   private long[] localityWaits;
   private boolean emittedTaskSizeWarning;
   private final boolean dropTaskInfoAccumulablesOnTaskCompletion;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Clock $lessinit$greater$default$5() {
      return TaskSetManager$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return TaskSetManager$.MODULE$.$lessinit$greater$default$4();
   }

   public static int BARRIER_LOGGING_INTERVAL() {
      return TaskSetManager$.MODULE$.BARRIER_LOGGING_INTERVAL();
   }

   public static int TASK_SIZE_TO_WARN_KIB() {
      return TaskSetManager$.MODULE$.TASK_SIZE_TO_WARN_KIB();
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

   public TaskSet taskSet() {
      return this.taskSet;
   }

   public int maxTaskFailures() {
      return this.maxTaskFailures;
   }

   private SparkConf conf() {
      return this.conf;
   }

   public long maxResultSize() {
      return this.maxResultSize;
   }

   public SparkEnv env() {
      return this.env;
   }

   public SerializerInstance ser() {
      return this.ser;
   }

   public Task[] tasks() {
      return this.tasks;
   }

   private boolean isShuffleMapTasks() {
      return this.isShuffleMapTasks;
   }

   private Option shuffleId() {
      return this.shuffleId;
   }

   public Map partitionToIndex() {
      return this.partitionToIndex;
   }

   public int numTasks() {
      return this.numTasks;
   }

   public int[] copiesRunning() {
      return this.copiesRunning;
   }

   public boolean speculationEnabled() {
      return this.speculationEnabled;
   }

   private double efficientTaskProcessMultiplier() {
      return this.efficientTaskProcessMultiplier;
   }

   private double efficientTaskDurationFactor() {
      return this.efficientTaskDurationFactor;
   }

   public double speculationQuantile() {
      return this.speculationQuantile;
   }

   public double speculationMultiplier() {
      return this.speculationMultiplier;
   }

   public int minFinishedForSpeculation() {
      return this.minFinishedForSpeculation;
   }

   public Option speculationTaskDurationThresOpt() {
      return this.speculationTaskDurationThresOpt;
   }

   private boolean isSpeculationThresholdSpecified() {
      return this.isSpeculationThresholdSpecified;
   }

   public boolean speculationTasksLessEqToSlots() {
      return this.speculationTasksLessEqToSlots;
   }

   private Option executorDecommissionKillInterval() {
      return this.executorDecommissionKillInterval;
   }

   public Option taskProcessRateCalculator() {
      return this.taskProcessRateCalculator;
   }

   public boolean[] successful() {
      return this.successful;
   }

   private int[] numFailures() {
      return this.numFailures;
   }

   private HashSet killedByOtherAttempt() {
      return this.killedByOtherAttempt;
   }

   public List[] taskAttempts() {
      return this.taskAttempts;
   }

   public int tasksSuccessful() {
      return this.tasksSuccessful;
   }

   public void tasksSuccessful_$eq(final int x$1) {
      this.tasksSuccessful = x$1;
   }

   public int weight() {
      return this.weight;
   }

   public int minShare() {
      return this.minShare;
   }

   public int priority() {
      return this.priority;
   }

   public void priority_$eq(final int x$1) {
      this.priority = x$1;
   }

   public int stageId() {
      return this.stageId;
   }

   public String name() {
      return this.name;
   }

   public Pool parent() {
      return this.parent;
   }

   public void parent_$eq(final Pool x$1) {
      this.parent = x$1;
   }

   private long totalResultSize() {
      return this.totalResultSize;
   }

   private void totalResultSize_$eq(final long x$1) {
      this.totalResultSize = x$1;
   }

   private int calculatedTasks() {
      return this.calculatedTasks;
   }

   private void calculatedTasks_$eq(final int x$1) {
      this.calculatedTasks = x$1;
   }

   public Option taskSetExcludelistHelperOpt() {
      return this.taskSetExcludelistHelperOpt;
   }

   public HashSet runningTasksSet() {
      return this.runningTasksSet;
   }

   public int runningTasks() {
      return this.runningTasksSet().size();
   }

   public boolean someAttemptSucceeded(final long tid) {
      return this.successful()[((TaskInfo)this.taskInfos().apply(BoxesRunTime.boxToLong(tid))).index()];
   }

   public boolean isZombie() {
      return this.isZombie;
   }

   public void isZombie_$eq(final boolean x$1) {
      this.isZombie = x$1;
   }

   public boolean isBarrier() {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(this.taskSet().tasks())) && this.taskSet().tasks()[0].isBarrier();
   }

   public HashMap barrierPendingLaunchTasks() {
      return this.barrierPendingLaunchTasks;
   }

   public long lastResourceOfferFailLogTime() {
      return this.lastResourceOfferFailLogTime;
   }

   public void lastResourceOfferFailLogTime_$eq(final long x$1) {
      this.lastResourceOfferFailLogTime = x$1;
   }

   public PendingTasksByLocality pendingTasks() {
      return this.pendingTasks;
   }

   public HashSet speculatableTasks() {
      return this.speculatableTasks;
   }

   public PendingTasksByLocality pendingSpeculatableTasks() {
      return this.pendingSpeculatableTasks;
   }

   public HashMap taskInfos() {
      return this.taskInfos;
   }

   public PercentileHeap successfulTaskDurations() {
      return this.successfulTaskDurations;
   }

   public long EXCEPTION_PRINT_INTERVAL() {
      return this.EXCEPTION_PRINT_INTERVAL;
   }

   private HashMap recentExceptions() {
      return this.recentExceptions;
   }

   public long epoch() {
      return this.epoch;
   }

   private void addPendingTasks() {
      Tuple2 var5 = Utils$.MODULE$.timeTakenMs((JFunction0.mcV.sp)() -> {
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.numTasks()).reverse().foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> this.addPendingTask(i, false, this.addPendingTask$default$3()));
         Tuple2 var3 = this.pendingTasks().forHost().toSeq().unzip(scala.Predef..MODULE$.$conforms());
         if (var3 != null) {
            Seq hosts = (Seq)var3._1();
            Seq indicesForHosts = (Seq)var3._2();
            Tuple2 var2 = new Tuple2(hosts, indicesForHosts);
            Seq hosts = (Seq)var2._1();
            Seq indicesForHosts = (Seq)var2._2();
            Seq racks = this.org$apache$spark$scheduler$TaskSetManager$$sched.getRacksForHosts(hosts);
            ((IterableOnceOps)racks.zip(indicesForHosts)).foreach((x0$1) -> {
               if (x0$1 != null) {
                  Option var4 = (Option)x0$1._1();
                  ArrayBuffer indices = (ArrayBuffer)x0$1._2();
                  if (var4 instanceof Some) {
                     Some var6 = (Some)var4;
                     String rack = (String)var6.value();
                     return ((Growable)this.pendingTasks().forRack().getOrElseUpdate(rack, () -> new ArrayBuffer())).$plus$plus$eq(indices);
                  }
               }

               if (x0$1 != null) {
                  Option var8 = (Option)x0$1._1();
                  if (scala.None..MODULE$.equals(var8)) {
                     return BoxedUnit.UNIT;
                  }
               }

               throw new MatchError(x0$1);
            });
         } else {
            throw new MatchError(var3);
         }
      });
      if (var5 != null) {
         long duration = var5._2$mcJ$sp();
         this.logDebug((Function0)(() -> "Adding pending tasks took " + duration + " ms"));
      } else {
         throw new MatchError(var5);
      }
   }

   public Enumeration.Value[] myLocalityLevels() {
      return this.myLocalityLevels;
   }

   public void myLocalityLevels_$eq(final Enumeration.Value[] x$1) {
      this.myLocalityLevels = x$1;
   }

   private boolean legacyLocalityWaitReset() {
      return this.legacyLocalityWaitReset;
   }

   private int currentLocalityIndex() {
      return this.currentLocalityIndex;
   }

   private void currentLocalityIndex_$eq(final int x$1) {
      this.currentLocalityIndex = x$1;
   }

   private long lastLocalityWaitResetTime() {
      return this.lastLocalityWaitResetTime;
   }

   private void lastLocalityWaitResetTime_$eq(final long x$1) {
      this.lastLocalityWaitResetTime = x$1;
   }

   public long[] localityWaits() {
      return this.localityWaits;
   }

   public void localityWaits_$eq(final long[] x$1) {
      this.localityWaits = x$1;
   }

   public ConcurrentLinkedQueue schedulableQueue() {
      return null;
   }

   public Enumeration.Value schedulingMode() {
      return SchedulingMode$.MODULE$.NONE();
   }

   public boolean emittedTaskSizeWarning() {
      return this.emittedTaskSizeWarning;
   }

   public void emittedTaskSizeWarning_$eq(final boolean x$1) {
      this.emittedTaskSizeWarning = x$1;
   }

   public boolean dropTaskInfoAccumulablesOnTaskCompletion() {
      return this.dropTaskInfoAccumulablesOnTaskCompletion;
   }

   public void addPendingTask(final int index, final boolean resolveRacks, final boolean speculatable) {
      if (!this.isZombie()) {
         PendingTasksByLocality pendingTaskSetToAddTo;
         label23: {
            label22: {
               pendingTaskSetToAddTo = speculatable ? this.pendingSpeculatableTasks() : this.pendingTasks();
               this.tasks()[index].preferredLocations().foreach((loc) -> {
                  $anonfun$addPendingTask$1(this, pendingTaskSetToAddTo, index, resolveRacks, loc);
                  return BoxedUnit.UNIT;
               });
               Seq var10000 = this.tasks()[index].preferredLocations();
               Nil var5 = scala.collection.immutable.Nil..MODULE$;
               if (var10000 == null) {
                  if (var5 == null) {
                     break label22;
                  }
               } else if (var10000.equals(var5)) {
                  break label22;
               }

               BoxedUnit var6 = BoxedUnit.UNIT;
               break label23;
            }

            pendingTaskSetToAddTo.noPrefs().$plus$eq(BoxesRunTime.boxToInteger(index));
         }

         pendingTaskSetToAddTo.all().$plus$eq(BoxesRunTime.boxToInteger(index));
      }
   }

   public boolean addPendingTask$default$2() {
      return true;
   }

   public boolean addPendingTask$default$3() {
      return false;
   }

   private Option dequeueTaskFromList(final String execId, final String host, final ArrayBuffer list, final boolean speculative) {
      int indexOffset = list.size();

      while(indexOffset > 0) {
         --indexOffset;
         int index = BoxesRunTime.unboxToInt(list.apply(indexOffset));
         if (!this.isTaskExcludededOnExecOrNode(index, execId, host) && (!speculative || !this.hasAttemptOnHost(index, host))) {
            list.remove(indexOffset);
            if (!this.successful()[index]) {
               if (this.copiesRunning()[index] == 0 && !this.barrierPendingLaunchTasks().contains(BoxesRunTime.boxToInteger(index))) {
                  return new Some(BoxesRunTime.boxToInteger(index));
               }

               if (speculative && this.copiesRunning()[index] == 1) {
                  return new Some(BoxesRunTime.boxToInteger(index));
               }
            }
         }
      }

      return scala.None..MODULE$;
   }

   private boolean hasAttemptOnHost(final int taskIndex, final String host) {
      return this.taskAttempts()[taskIndex].exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$hasAttemptOnHost$1(host, x$3)));
   }

   private boolean isTaskExcludededOnExecOrNode(final int index, final String execId, final String host) {
      return this.taskSetExcludelistHelperOpt().exists((excludeList) -> BoxesRunTime.boxToBoolean($anonfun$isTaskExcludededOnExecOrNode$1(host, index, execId, excludeList)));
   }

   private Option dequeueTask(final String execId, final String host, final Enumeration.Value maxLocality) {
      return this.dequeueTaskHelper(execId, host, maxLocality, false).orElse(() -> this.dequeueTaskHelper(execId, host, maxLocality, true));
   }

   private boolean dequeueTaskFromList$default$4() {
      return false;
   }

   public Option dequeueTaskHelper(final String execId, final String host, final Enumeration.Value maxLocality, final boolean speculative) {
      Object var5 = new Object();

      Object var10000;
      try {
         if (speculative && this.speculatableTasks().isEmpty()) {
            return scala.None..MODULE$;
         }

         PendingTasksByLocality pendingTaskSetToUse = speculative ? this.pendingSpeculatableTasks() : this.pendingTasks();
         this.dequeue$1((ArrayBuffer)pendingTaskSetToUse.forExecutor().getOrElse(execId, () -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)), execId, host, speculative).foreach((index) -> $anonfun$dequeueTaskHelper$2(var5, speculative, BoxesRunTime.unboxToInt(index)));
         if (TaskLocality$.MODULE$.isAllowed(maxLocality, TaskLocality$.MODULE$.NODE_LOCAL())) {
            this.dequeue$1((ArrayBuffer)pendingTaskSetToUse.forHost().getOrElse(host, () -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)), execId, host, speculative).foreach((index) -> $anonfun$dequeueTaskHelper$4(var5, speculative, BoxesRunTime.unboxToInt(index)));
         }

         if (TaskLocality$.MODULE$.isAllowed(maxLocality, TaskLocality$.MODULE$.NO_PREF())) {
            this.dequeue$1(pendingTaskSetToUse.noPrefs(), execId, host, speculative).foreach((index) -> $anonfun$dequeueTaskHelper$5(var5, speculative, BoxesRunTime.unboxToInt(index)));
         }

         if (TaskLocality$.MODULE$.isAllowed(maxLocality, TaskLocality$.MODULE$.RACK_LOCAL())) {
            this.org$apache$spark$scheduler$TaskSetManager$$sched.getRackForHost(host).foreach((rack) -> {
               $anonfun$dequeueTaskHelper$6(this, pendingTaskSetToUse, var5, speculative, execId, host, rack);
               return BoxedUnit.UNIT;
            });
         }

         if (TaskLocality$.MODULE$.isAllowed(maxLocality, TaskLocality$.MODULE$.ANY())) {
            this.dequeue$1(pendingTaskSetToUse.all(), execId, host, speculative).foreach((index) -> $anonfun$dequeueTaskHelper$9(var5, speculative, BoxesRunTime.unboxToInt(index)));
         }

         var10000 = scala.None..MODULE$;
      } catch (NonLocalReturnControl var8) {
         if (var8.key() != var5) {
            throw var8;
         }

         var10000 = (Option)var8.value();
      }

      return (Option)var10000;
   }

   public void resetDelayScheduleTimer(final Option minLocality) {
      this.lastLocalityWaitResetTime_$eq(this.clock.getTimeMillis());
      minLocality.foreach((locality) -> {
         $anonfun$resetDelayScheduleTimer$1(this, locality);
         return BoxedUnit.UNIT;
      });
   }

   public Tuple3 resourceOffer(final String execId, final String host, final Enumeration.Value maxLocality, final int taskCpus, final Map taskResourceAssignments) throws TaskNotSerializableException {
      boolean offerExcluded = this.taskSetExcludelistHelperOpt().exists((excludeList) -> BoxesRunTime.boxToBoolean($anonfun$resourceOffer$1(host, execId, excludeList)));
      if (!this.isZombie() && !offerExcluded) {
         long curTime;
         Enumeration.Value allowedLocality;
         label47: {
            curTime = this.clock.getTimeMillis();
            allowedLocality = maxLocality;
            Enumeration.Value var10 = TaskLocality$.MODULE$.NO_PREF();
            if (maxLocality == null) {
               if (var10 == null) {
                  break label47;
               }
            } else if (maxLocality.equals(var10)) {
               break label47;
            }

            allowedLocality = this.getAllowedLocalityLevel(curTime);
            if (allowedLocality.$greater(maxLocality)) {
               allowedLocality = maxLocality;
            }
         }

         ObjectRef dequeuedTaskIndex;
         Option taskDescription;
         boolean var10000;
         label36: {
            dequeuedTaskIndex = ObjectRef.create(scala.None..MODULE$);
            taskDescription = this.dequeueTask(execId, host, allowedLocality).map((x0$1) -> {
               if (x0$1 == null) {
                  throw new MatchError(x0$1);
               } else {
                  int index = BoxesRunTime.unboxToInt(x0$1._1());
                  Enumeration.Value taskLocality = (Enumeration.Value)x0$1._2();
                  boolean speculative = BoxesRunTime.unboxToBoolean(x0$1._3());
                  dequeuedTaskIndex.elem = new Some(BoxesRunTime.boxToInteger(index));
                  if (this.legacyLocalityWaitReset()) {
                     label23: {
                        Enumeration.Value var15 = TaskLocality$.MODULE$.NO_PREF();
                        if (maxLocality == null) {
                           if (var15 == null) {
                              break label23;
                           }
                        } else if (maxLocality.equals(var15)) {
                           break label23;
                        }

                        this.resetDelayScheduleTimer(new Some(taskLocality));
                     }
                  }

                  if (this.isBarrier()) {
                     this.barrierPendingLaunchTasks().update(BoxesRunTime.boxToInteger(index), new BarrierPendingLaunchTask(execId, host, index, taskLocality, taskResourceAssignments));
                     return null;
                  } else {
                     return this.prepareLaunchingTask(execId, host, index, taskLocality, speculative, taskCpus, taskResourceAssignments, curTime);
                  }
               }
            });
            boolean hasPendingTasks = this.pendingTasks().all().nonEmpty() || this.pendingSpeculatableTasks().all().nonEmpty();
            if (taskDescription.isEmpty()) {
               label34: {
                  Enumeration.Value var15 = TaskLocality$.MODULE$.ANY();
                  if (maxLocality == null) {
                     if (var15 != null) {
                        break label34;
                     }
                  } else if (!maxLocality.equals(var15)) {
                     break label34;
                  }

                  if (hasPendingTasks) {
                     var10000 = true;
                     break label36;
                  }
               }
            }

            var10000 = false;
         }

         boolean hasScheduleDelayReject = var10000;
         return new Tuple3(taskDescription, BoxesRunTime.boxToBoolean(hasScheduleDelayReject), ((Option)dequeuedTaskIndex.elem).getOrElse((JFunction0.mcI.sp)() -> -1));
      } else {
         return new Tuple3(scala.None..MODULE$, BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(-1));
      }
   }

   public int resourceOffer$default$4() {
      return this.org$apache$spark$scheduler$TaskSetManager$$sched.CPUS_PER_TASK();
   }

   public Map resourceOffer$default$5() {
      return scala.Predef..MODULE$.Map().empty();
   }

   public TaskDescription prepareLaunchingTask(final String execId, final String host, final int index, final Enumeration.Value taskLocality, final boolean speculative, final int taskCpus, final Map taskResourceAssignments, final long launchTime) {
      Task task = this.tasks()[index];
      long taskId = this.org$apache$spark$scheduler$TaskSetManager$$sched.newTaskId();
      ++this.copiesRunning()[index];
      int attemptNum = this.taskAttempts()[index].size();
      TaskInfo info = new TaskInfo(taskId, index, attemptNum, task.partitionId(), launchTime, execId, host, taskLocality, speculative);
      this.taskInfos().update(BoxesRunTime.boxToLong(taskId), info);
      this.taskAttempts()[index] = this.taskAttempts()[index].$colon$colon(info);

      ByteBuffer var10000;
      try {
         var10000 = this.ser().serialize(task, scala.reflect.ClassTag..MODULE$.apply(Task.class));
      } catch (Throwable var23) {
         if (var23 != null && scala.util.control.NonFatal..MODULE$.apply(var23)) {
            MessageWithContext msg = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to serialize task ", ", not attempting to retry it."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))})));
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg), var23);
            String var10001 = msg.message();
            this.abort(var10001 + " Exception during serialization: " + var23, this.abort$default$2());
            throw SparkCoreErrors$.MODULE$.failToSerializeTaskError(var23);
         }

         throw var23;
      }

      ByteBuffer serializedTask = var10000;
      if (serializedTask.limit() > TaskSetManager$.MODULE$.TASK_SIZE_TO_WARN_KIB() * 1024 && !this.emittedTaskSizeWarning()) {
         this.emittedTaskSizeWarning_$eq(true);
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stage ", " contains a task of very large size "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(task.stageId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " KiB). "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToInteger(serializedTask.limit() / 1024))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The maximum recommended task size is "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " KiB."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_TO_WARN..MODULE$, BoxesRunTime.boxToInteger(TaskSetManager$.MODULE$.TASK_SIZE_TO_WARN_KIB()))}))))));
      }

      this.addRunningTask(taskId);
      String tName = this.taskName(taskId);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting ", " (", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, tName), new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, info.executorId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"partition ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITION_ID..MODULE$, BoxesRunTime.boxToInteger(task.partitionId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_LOCALITY..MODULE$, taskLocality)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " bytes) "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIZE..MODULE$, BoxesRunTime.boxToInteger(serializedTask.limit()))})))).$plus(taskResourceAssignments.nonEmpty() ? this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"taskResourceAssignments ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_RESOURCE_ASSIGNMENTS..MODULE$, taskResourceAssignments)}))) : this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$))));
      this.org$apache$spark$scheduler$TaskSetManager$$sched.dagScheduler().taskStarted(task, info);
      return new TaskDescription(taskId, attemptNum, execId, tName, index, task.partitionId(), task.artifacts(), task.localProperties(), taskCpus, taskResourceAssignments, serializedTask);
   }

   public String taskName(final long tid) {
      Option info = this.taskInfos().get(BoxesRunTime.boxToLong(tid));
      scala.Predef..MODULE$.assert(info.isDefined(), () -> "Can not find TaskInfo for task (TID " + tid + ")");
      String var10000 = ((TaskInfo)info.get()).id();
      return "task " + var10000 + " in stage " + this.taskSet().id() + " (TID " + tid + ")";
   }

   private void maybeFinishTaskSet() {
      if (this.isZombie() && this.runningTasks() == 0) {
         this.org$apache$spark$scheduler$TaskSetManager$$sched.taskSetFinished(this);
         if (this.tasksSuccessful() == this.numTasks()) {
            this.healthTracker.foreach((x$4) -> {
               $anonfun$maybeFinishTaskSet$1(this, x$4);
               return BoxedUnit.UNIT;
            });
         }
      }
   }

   private Enumeration.Value getAllowedLocalityLevel(final long curTime) {
      while(this.currentLocalityIndex() < this.myLocalityLevels().length - 1) {
         boolean var13;
         label68: {
            label75: {
               Enumeration.Value var5 = this.myLocalityLevels()[this.currentLocalityIndex()];
               Enumeration.Value var10000 = TaskLocality$.MODULE$.PROCESS_LOCAL();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label75;
                  }
               } else if (var10000.equals(var5)) {
                  break label75;
               }

               label76: {
                  var10000 = TaskLocality$.MODULE$.NODE_LOCAL();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label76;
                     }
                  } else if (var10000.equals(var5)) {
                     break label76;
                  }

                  label77: {
                     var10000 = TaskLocality$.MODULE$.NO_PREF();
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label77;
                        }
                     } else if (var10000.equals(var5)) {
                        break label77;
                     }

                     var10000 = TaskLocality$.MODULE$.RACK_LOCAL();
                     if (var10000 == null) {
                        if (var5 != null) {
                           throw new MatchError(var5);
                        }
                     } else if (!var10000.equals(var5)) {
                        throw new MatchError(var5);
                     }

                     var13 = this.moreTasksToRunIn$1(this.pendingTasks().forRack());
                     break label68;
                  }

                  var13 = this.pendingTasks().noPrefs().nonEmpty();
                  break label68;
               }

               var13 = this.moreTasksToRunIn$1(this.pendingTasks().forHost());
               break label68;
            }

            var13 = this.moreTasksToRunIn$1(this.pendingTasks().forExecutor());
         }

         boolean moreTasks = var13;
         if (!moreTasks) {
            this.lastLocalityWaitResetTime_$eq(curTime);
            this.logDebug((Function0)(() -> {
               Enumeration.Value var10000 = this.myLocalityLevels()[this.currentLocalityIndex()];
               return "No tasks for locality level " + var10000 + ", so moving to locality level " + this.myLocalityLevels()[this.currentLocalityIndex() + 1];
            }));
            this.currentLocalityIndex_$eq(this.currentLocalityIndex() + 1);
         } else {
            if (curTime - this.lastLocalityWaitResetTime() < this.localityWaits()[this.currentLocalityIndex()]) {
               return this.myLocalityLevels()[this.currentLocalityIndex()];
            }

            this.lastLocalityWaitResetTime_$eq(this.lastLocalityWaitResetTime() + this.localityWaits()[this.currentLocalityIndex()]);
            this.logDebug((Function0)(() -> {
               Enumeration.Value var10000 = this.myLocalityLevels()[this.currentLocalityIndex() + 1];
               return "Moving to " + var10000 + " after waiting for " + this.localityWaits()[this.currentLocalityIndex()] + "ms";
            }));
            this.currentLocalityIndex_$eq(this.currentLocalityIndex() + 1);
         }
      }

      return this.myLocalityLevels()[this.currentLocalityIndex()];
   }

   public int getLocalityIndex(final Enumeration.Value locality) {
      int index;
      for(index = 0; locality.$greater(this.myLocalityLevels()[index]); ++index) {
      }

      return index;
   }

   public Option getCompletelyExcludedTaskIfAny(final HashMap hostToExecutors) {
      return this.taskSetExcludelistHelperOpt().flatMap((taskSetExcludelist) -> {
         if (hostToExecutors.nonEmpty()) {
            int indexOffset = this.pendingTasks().all().lastIndexWhere((JFunction1.mcZI.sp)(indexInTaskSet) -> this.copiesRunning()[indexInTaskSet] == 0 && !this.successful()[indexInTaskSet]);
            Option pendingTask = (Option)(indexOffset == -1 ? scala.None..MODULE$ : new Some(this.pendingTasks().all().apply(indexOffset)));
            return scala.Option..MODULE$.option2Iterable(pendingTask).find((JFunction1.mcZI.sp)(indexInTaskSet) -> hostToExecutors.forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getCompletelyExcludedTaskIfAny$4(this, taskSetExcludelist, indexInTaskSet, x0$1))));
         } else {
            return scala.None..MODULE$;
         }
      });
   }

   public void abortSinceCompletelyExcludedOnFailure(final int indexInTaskSet) {
      this.taskSetExcludelistHelperOpt().foreach((taskSetExcludelist) -> {
         $anonfun$abortSinceCompletelyExcludedOnFailure$1(this, indexInTaskSet, taskSetExcludelist);
         return BoxedUnit.UNIT;
      });
   }

   public void handleTaskGettingResult(final long tid) {
      TaskInfo info = (TaskInfo)this.taskInfos().apply(BoxesRunTime.boxToLong(tid));
      info.markGettingResult(this.clock.getTimeMillis());
      this.org$apache$spark$scheduler$TaskSetManager$$sched.dagScheduler().taskGettingResult(info);
   }

   public boolean canFetchMoreResults(final long size) {
      synchronized(this.org$apache$spark$scheduler$TaskSetManager$$sched){}

      boolean var4;
      try {
         this.totalResultSize_$eq(this.totalResultSize() + size);
         this.calculatedTasks_$eq(this.calculatedTasks() + 1);
         boolean var10000;
         if (!this.isShuffleMapTasks() && this.maxResultSize() > 0L && this.totalResultSize() > this.maxResultSize()) {
            MessageWithContext msg = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Total size of serialized results of ", " tasks "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(this.calculatedTasks()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") is bigger than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.totalResultSize()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.MAX_RESULT_SIZE().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.maxResultSize()))}))));
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg));
            this.abort(msg.message(), this.abort$default$2());
            var10000 = false;
         } else {
            var10000 = true;
         }

         var4 = var10000;
      } catch (Throwable var7) {
         throw var7;
      }

      return var4;
   }

   public void handleSuccessfulTask(final long tid, final DirectTaskResult result) {
      TaskInfo info = (TaskInfo)this.taskInfos().apply(BoxesRunTime.boxToLong(tid));
      if (info.finished()) {
         if (this.dropTaskInfoAccumulablesOnTaskCompletion()) {
            info.setAccumulables(scala.collection.immutable.Nil..MODULE$);
         }

      } else {
         int index = info.index();
         if (this.successful()[index] && this.killedByOtherAttempt().contains(BoxesRunTime.boxToLong(tid))) {
            this.calculatedTasks_$eq(this.calculatedTasks() - 1);
            Option resultSizeAcc = result.accumUpdates().find((a) -> BoxesRunTime.boxToBoolean($anonfun$handleSuccessfulTask$1(a)));
            if (resultSizeAcc.isDefined()) {
               this.totalResultSize_$eq(this.totalResultSize() - scala.Predef..MODULE$.Long2long(((LongAccumulator)resultSizeAcc.get()).value()));
            }

            this.handleFailedTask(tid, TaskState$.MODULE$.KILLED(), new TaskKilled("Finish but did not commit due to another attempt succeeded", TaskKilled$.MODULE$.apply$default$2(), TaskKilled$.MODULE$.apply$default$3(), TaskKilled$.MODULE$.apply$default$4()));
         } else {
            info.markFinished(TaskState$.MODULE$.FINISHED(), this.clock.getTimeMillis());
            if (this.speculationEnabled()) {
               this.successfulTaskDurations().insert((double)info.duration());
               this.taskProcessRateCalculator().foreach((x$7) -> {
                  $anonfun$handleSuccessfulTask$2(tid, result, x$7);
                  return BoxedUnit.UNIT;
               });
            }

            this.removeRunningTask(tid);
            this.taskAttempts()[index].withFilter((attemptInfo) -> BoxesRunTime.boxToBoolean($anonfun$handleSuccessfulTask$3(attemptInfo))).foreach((attemptInfo) -> {
               $anonfun$handleSuccessfulTask$4(this, info, attemptInfo);
               return BoxedUnit.UNIT;
            });
            if (!this.successful()[index]) {
               this.tasksSuccessful_$eq(this.tasksSuccessful() + 1);
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished ", " in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName(info.taskId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms on ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, BoxesRunTime.boxToLong(info.duration())), new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, info.host())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(executor ", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, info.executorId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "/", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_SUCCESSFUL_TASKS..MODULE$, BoxesRunTime.boxToInteger(this.tasksSuccessful())), new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(this.numTasks()))}))))));
               this.successful()[index] = true;
               this.numFailures()[index] = 0;
               if (this.tasksSuccessful() == this.numTasks()) {
                  this.isZombie_$eq(true);
               }
            } else {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring task-finished event for "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName(info.taskId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because it has already completed successfully"})))).log(scala.collection.immutable.Nil..MODULE$))));
            }

            this.emptyTaskInfoAccumulablesAndNotifyDagScheduler(tid, this.tasks()[index], Success$.MODULE$, result.value(result.value$default$1()), result.accumUpdates(), result.metricPeaks());
            this.maybeFinishTaskSet();
         }
      }
   }

   private void emptyTaskInfoAccumulablesAndNotifyDagScheduler(final long taskId, final Task task, final TaskEndReason reason, final Object result, final Seq accumUpdates, final long[] metricPeaks) {
      TaskInfo taskInfoWithAccumulables = (TaskInfo)this.taskInfos().apply(BoxesRunTime.boxToLong(taskId));
      if (this.dropTaskInfoAccumulablesOnTaskCompletion()) {
         int index = taskInfoWithAccumulables.index();
         TaskInfo clonedTaskInfo = taskInfoWithAccumulables.cloneWithEmptyAccumulables();
         this.taskAttempts()[index] = this.taskAttempts()[index].map((i) -> i == taskInfoWithAccumulables ? clonedTaskInfo : i);
         this.taskInfos().update(BoxesRunTime.boxToLong(taskId), clonedTaskInfo);
      }

      this.org$apache$spark$scheduler$TaskSetManager$$sched.dagScheduler().taskEnded(task, reason, result, accumUpdates, metricPeaks, taskInfoWithAccumulables);
   }

   public void markPartitionCompleted(final int partitionId) {
      this.partitionToIndex().get(BoxesRunTime.boxToInteger(partitionId)).foreach((JFunction1.mcVI.sp)(index) -> {
         if (!this.successful()[index]) {
            this.tasksSuccessful_$eq(this.tasksSuccessful() + 1);
            this.successful()[index] = true;
            this.numFailures()[index] = 0;
            if (this.tasksSuccessful() == this.numTasks()) {
               this.isZombie_$eq(true);
            }

            this.maybeFinishTaskSet();
         }
      });
   }

   public void handleFailedTask(final long tid, final Enumeration.Value state, final TaskFailedReason reason) {
      TaskInfo info = (TaskInfo)this.taskInfos().apply(BoxesRunTime.boxToLong(tid));
      if (info.finished()) {
         if (this.dropTaskInfoAccumulablesOnTaskCompletion()) {
            info.setAccumulables(scala.collection.immutable.Nil..MODULE$);
         }

      } else {
         int index;
         Seq accumUpdates;
         long[] metricPeaks;
         ExceptionFailure var17;
         String task;
         label123: {
            this.removeRunningTask(tid);
            info.markFinished(state, this.clock.getTimeMillis());
            index = info.index();
            --this.copiesRunning()[index];
            accumUpdates = (Seq)scala.package..MODULE$.Seq().empty();
            metricPeaks = (long[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Long());
            MessageWithContext failureReason = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Lost ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName(tid))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, info.host())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor ", "): "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, info.executorId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, reason.toErrorString())}))));
            String var10000 = this.taskName(tid);
            String failureReasonString = "Lost " + var10000 + " (" + info.host() + " executor " + info.executorId() + "): " + reason.toErrorString();
            Object var40;
            if (reason instanceof FetchFailed) {
               FetchFailed var16 = (FetchFailed)reason;
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> failureReason));
               if (!this.successful()[index]) {
                  this.successful()[index] = true;
                  this.tasksSuccessful_$eq(this.tasksSuccessful() + 1);
               }

               this.isZombie_$eq(true);
               if (var16.bmAddress() != null) {
                  this.healthTracker.foreach((x$8) -> {
                     $anonfun$handleFailedTask$2(var16, x$8);
                     return BoxedUnit.UNIT;
                  });
               }

               var40 = scala.None..MODULE$;
            } else if (reason instanceof ExceptionFailure) {
               label125: {
                  var17 = (ExceptionFailure)reason;
                  accumUpdates = var17.accums();
                  metricPeaks = (long[])var17.metricPeaks().toArray(scala.reflect.ClassTag..MODULE$.Long());
                  task = this.taskName(tid);
                  String var41 = var17.className();
                  String var19 = NotSerializableException.class.getName();
                  if (var41 == null) {
                     if (var19 == null) {
                        break label123;
                     }
                  } else if (var41.equals(var19)) {
                     break label123;
                  }

                  label126: {
                     var41 = var17.className();
                     String var20 = TaskOutputFileAlreadyExistException.class.getName();
                     if (var41 == null) {
                        if (var20 == null) {
                           break label126;
                        }
                     } else if (var41.equals(var20)) {
                        break label126;
                     }

                     String key = var17.description();
                     long now = this.clock.getTimeMillis();
                     Tuple2.mcZI.sp var43;
                     if (this.recentExceptions().contains(key)) {
                        Tuple2 var27 = (Tuple2)this.recentExceptions().apply(key);
                        if (var27 == null) {
                           throw new MatchError(var27);
                        }

                        int dupCount = var27._1$mcI$sp();
                        long printTime = var27._2$mcJ$sp();
                        Tuple2.mcIJ.sp var26 = new Tuple2.mcIJ.sp(dupCount, printTime);
                        int dupCount = ((Tuple2)var26)._1$mcI$sp();
                        long printTime = ((Tuple2)var26)._2$mcJ$sp();
                        if (now - printTime > this.EXCEPTION_PRINT_INTERVAL()) {
                           this.recentExceptions().update(key, new Tuple2.mcIJ.sp(0, now));
                           var43 = new Tuple2.mcZI.sp(true, 0);
                        } else {
                           this.recentExceptions().update(key, new Tuple2.mcIJ.sp(dupCount + 1, printTime));
                           var43 = new Tuple2.mcZI.sp(false, dupCount + 1);
                        }
                     } else {
                        this.recentExceptions().update(key, new Tuple2.mcIJ.sp(0, now));
                        var43 = new Tuple2.mcZI.sp(true, 0);
                     }

                     Tuple2.mcZI.sp var25 = var43;
                     if (var25 == null) {
                        throw new MatchError(var25);
                     }

                     boolean printFull = ((Tuple2)var25)._1$mcZ$sp();
                     int dupCount = ((Tuple2)var25)._2$mcI$sp();
                     Tuple2.mcZI.sp var24 = new Tuple2.mcZI.sp(printFull, dupCount);
                     boolean printFull = ((Tuple2)var24)._1$mcZ$sp();
                     int dupCount = ((Tuple2)var24)._2$mcI$sp();
                     if (printFull) {
                        this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> failureReason));
                     } else {
                        this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Lost ", " on ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, task), new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, info.host())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, info.executorId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, var17.className())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") [duplicate ", "]"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESCRIPTION..MODULE$, var17.description()), new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(dupCount))}))))));
                     }

                     var40 = var17.exception();
                     break label125;
                  }

                  this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", ".", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_INDEX..MODULE$, BoxesRunTime.boxToInteger(info.index())), new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(info.attemptNumber()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in stage ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(this.taskSet().stageId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (TID ", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(this.taskSet().stageAttemptId())), new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(tid))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"can not write to output file: ", "; not retrying"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var17.description())}))))));
                  this.emptyTaskInfoAccumulablesAndNotifyDagScheduler(tid, this.tasks()[index], reason, (Object)null, accumUpdates, metricPeaks);
                  this.abort(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Task %s in stage %s (TID %d) can not write to output file: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{info.id(), this.taskSet().id(), BoxesRunTime.boxToLong(tid), var17.description()})), this.abort$default$2());
                  return;
               }
            } else if (reason instanceof TaskKilled) {
               TaskKilled var38 = (TaskKilled)reason;
               accumUpdates = var38.accums();
               metricPeaks = (long[])var38.metricPeaks().toArray(scala.reflect.ClassTag..MODULE$.Long());
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> failureReason));
               var40 = scala.None..MODULE$;
            } else {
               label127: {
                  if (reason instanceof ExecutorLostFailure) {
                     ExecutorLostFailure var39 = (ExecutorLostFailure)reason;
                     if (!var39.exitCausedByApp()) {
                        this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " failed because while it was being computed,"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName(tid))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" its executor exited for a reason unrelated to the task. "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not counting this failure towards the maximum number of failures for the task."})))).log(scala.collection.immutable.Nil..MODULE$))));
                        var40 = scala.None..MODULE$;
                        break label127;
                     }
                  }

                  if (reason == null) {
                     throw new MatchError(reason);
                  }

                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> failureReason));
                  var40 = scala.None..MODULE$;
               }
            }

            Option failureException = (Option)var40;
            if (this.tasks()[index].isBarrier()) {
               this.isZombie_$eq(true);
            }

            this.emptyTaskInfoAccumulablesAndNotifyDagScheduler(tid, this.tasks()[index], reason, (Object)null, accumUpdates, metricPeaks);
            if (!this.isZombie() && reason.countTowardsTaskFailures()) {
               scala.Predef..MODULE$.assert(failureReason != null);
               this.taskSetExcludelistHelperOpt().foreach((x$11) -> {
                  $anonfun$handleFailedTask$10(info, index, failureReasonString, x$11);
                  return BoxedUnit.UNIT;
               });
               ++this.numFailures()[index];
               if (this.numFailures()[index] >= this.maxTaskFailures()) {
                  this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", " in stage "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_INDEX..MODULE$, BoxesRunTime.boxToInteger(index))}))).$plus(this.taskSet().logId()).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" failed ", " times; aborting job"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.maxTaskFailures()))}))))));
                  this.abort(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Task %d in stage %s failed %d times, most recent failure: %s\nDriver stacktrace:"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(index), this.taskSet().id(), BoxesRunTime.boxToInteger(this.maxTaskFailures()), failureReasonString})), failureException);
                  return;
               }
            }

            if (this.successful()[index]) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " failed, but the task will not"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, this.taskName(info.taskId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" be re-executed (either because the task failed with a shuffle data fetch failure,"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" so the previous stage needs to be re-run, or because a different copy of the task"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" has already succeeded)."})))).log(scala.collection.immutable.Nil..MODULE$))));
            } else {
               this.addPendingTask(index, this.addPendingTask$default$2(), this.addPendingTask$default$3());
            }

            this.maybeFinishTaskSet();
            return;
         }

         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " had a not serializable result: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, task)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "; not retrying"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var17.description())}))))));
         this.emptyTaskInfoAccumulablesAndNotifyDagScheduler(tid, this.tasks()[index], reason, (Object)null, accumUpdates, metricPeaks);
         this.abort(task + " had a not serializable result: " + var17.description(), this.abort$default$2());
      }
   }

   public void abort(final String message, final Option exception) {
      synchronized(this.org$apache$spark$scheduler$TaskSetManager$$sched){}

      try {
         this.org$apache$spark$scheduler$TaskSetManager$$sched.dagScheduler().taskSetFailed(this.taskSet(), message, exception);
         this.isZombie_$eq(true);
         this.maybeFinishTaskSet();
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public Option abort$default$2() {
      return scala.None..MODULE$;
   }

   public void suspend() {
      this.isZombie_$eq(true);
      this.maybeFinishTaskSet();
   }

   public void addRunningTask(final long tid) {
      if (this.runningTasksSet().add(BoxesRunTime.boxToLong(tid)) && this.parent() != null) {
         this.parent().increaseRunningTasks(1);
      }
   }

   public void removeRunningTask(final long tid) {
      if (this.runningTasksSet().remove(BoxesRunTime.boxToLong(tid)) && this.parent() != null) {
         this.parent().decreaseRunningTasks(1);
      }
   }

   public Schedulable getSchedulableByName(final String name) {
      return null;
   }

   public boolean isSchedulable() {
      return !this.isZombie() && (this.pendingTasks().all().nonEmpty() || this.pendingSpeculatableTasks().all().nonEmpty());
   }

   public void addSchedulable(final Schedulable schedulable) {
   }

   public void removeSchedulable(final Schedulable schedulable) {
   }

   public ArrayBuffer getSortedTaskSetQueue() {
      ArrayBuffer sortedTaskSetQueue = new ArrayBuffer();
      sortedTaskSetQueue.$plus$eq(this);
      return sortedTaskSetQueue;
   }

   public void executorLost(final String execId, final String host, final ExecutorLossReason reason) {
      boolean maybeShuffleMapOutputLoss = this.isShuffleMapTasks() && !this.org$apache$spark$scheduler$TaskSetManager$$sched.sc().shuffleDriverComponents().supportsReliableStorage() && (reason instanceof ExecutorDecommission || !this.env().blockManager().externalShuffleServiceEnabled());
      if (maybeShuffleMapOutputLoss && !this.isZombie()) {
         this.taskInfos().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$executorLost$1(check$ifrefutable$1))).withFilter((x$13) -> BoxesRunTime.boxToBoolean($anonfun$executorLost$2(execId, x$13))).foreach((x$14) -> {
            $anonfun$executorLost$3(this, reason, host, x$14);
            return BoxedUnit.UNIT;
         });
      }

      this.taskInfos().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$executorLost$5(check$ifrefutable$2))).withFilter((x$15) -> BoxesRunTime.boxToBoolean($anonfun$executorLost$6(execId, x$15))).foreach((x$16) -> {
         $anonfun$executorLost$7(this, reason, x$16);
         return BoxedUnit.UNIT;
      });
      this.recomputeLocality();
   }

   private boolean checkAndSubmitSpeculatableTasks(final long currentTimeMillis, final double threshold, final boolean customizedThreshold) {
      BooleanRef foundTasksResult = BooleanRef.create(false);
      this.runningTasksSet().foreach((JFunction1.mcVJ.sp)(tid) -> {
         TaskInfo info = (TaskInfo)this.taskInfos().apply(BoxesRunTime.boxToLong(tid));
         int index = info.index();
         if (!this.successful()[index] && this.copiesRunning()[index] == 1 && !this.speculatableTasks().contains(BoxesRunTime.boxToInteger(index))) {
            long runtimeMs = info.timeRunning(currentTimeMillis);
            boolean speculated = (double)runtimeMs > threshold && this.checkMaySpeculate$1(customizedThreshold, runtimeMs, threshold, tid) || this.shouldSpeculateForExecutorDecommissioning$1(customizedThreshold, info);
            if (speculated) {
               boolean x$2 = true;
               boolean x$3 = this.addPendingTask$default$2();
               this.addPendingTask(index, x$3, true);
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Marking task ", " in stage "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_INDEX..MODULE$, BoxesRunTime.boxToInteger(index))}))).$plus(this.taskSet().logId()).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (on ", ") as speculatable because it ran more than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, info.host())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms(", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToDouble(threshold)), new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(this.speculatableTasks().size() + 1))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"speculatable tasks in this taskset now)"})))).log(scala.collection.immutable.Nil..MODULE$))));
               this.speculatableTasks().$plus$eq(BoxesRunTime.boxToInteger(index));
               this.org$apache$spark$scheduler$TaskSetManager$$sched.dagScheduler().speculativeTaskSubmitted(this.tasks()[index], index);
            }

            foundTasksResult.elem |= speculated;
         }
      });
      return foundTasksResult.elem;
   }

   private boolean checkAndSubmitSpeculatableTasks$default$3() {
      return false;
   }

   public boolean checkSpeculatableTasks(final long minTimeToSpeculation) {
      if (!this.isZombie() && !this.isBarrier() && (this.numTasks() != 1 || this.isSpeculationThresholdSpecified())) {
         boolean foundTasks = false;
         this.logDebug((Function0)(() -> "Checking for speculative tasks: minFinished = " + this.minFinishedForSpeculation()));
         int numSuccessfulTasks = this.successfulTaskDurations().size();
         long timeMs = this.clock.getTimeMillis();
         if (numSuccessfulTasks >= this.minFinishedForSpeculation()) {
            double medianDuration = this.successfulTaskDurations().percentile();
            double threshold = scala.math.package..MODULE$.max(this.speculationMultiplier() * medianDuration, (double)minTimeToSpeculation);
            this.logDebug((Function0)(() -> "Task length threshold for speculation: " + threshold));
            foundTasks = this.checkAndSubmitSpeculatableTasks(timeMs, threshold, this.checkAndSubmitSpeculatableTasks$default$3());
         } else if (this.isSpeculationThresholdSpecified() && this.speculationTasksLessEqToSlots()) {
            long threshold = BoxesRunTime.unboxToLong(this.speculationTaskDurationThresOpt().get());
            this.logDebug((Function0)(() -> "Tasks taking longer time than provided speculation threshold: " + threshold));
            foundTasks = this.checkAndSubmitSpeculatableTasks(timeMs, (double)threshold, true);
         }

         if (foundTasks) {
            long elapsedMs = this.clock.getTimeMillis() - timeMs;
            if (elapsedMs > minTimeToSpeculation) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Time to checkSpeculatableTasks ", "ms > "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(elapsedMs))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_TIME..MODULE$, BoxesRunTime.boxToLong(minTimeToSpeculation))}))))));
            }
         }

         return foundTasks;
      } else {
         return false;
      }
   }

   private long getLocalityWait(final Enumeration.Value level) {
      if (this.legacyLocalityWaitReset() && this.isBarrier()) {
         return 0L;
      } else {
         ConfigEntry var10;
         label53: {
            label57: {
               Enumeration.Value var10000 = TaskLocality$.MODULE$.PROCESS_LOCAL();
               if (var10000 == null) {
                  if (level == null) {
                     break label57;
                  }
               } else if (var10000.equals(level)) {
                  break label57;
               }

               label58: {
                  var10000 = TaskLocality$.MODULE$.NODE_LOCAL();
                  if (var10000 == null) {
                     if (level == null) {
                        break label58;
                     }
                  } else if (var10000.equals(level)) {
                     break label58;
                  }

                  label38: {
                     var10000 = TaskLocality$.MODULE$.RACK_LOCAL();
                     if (var10000 == null) {
                        if (level == null) {
                           break label38;
                        }
                     } else if (var10000.equals(level)) {
                        break label38;
                     }

                     var10 = null;
                     break label53;
                  }

                  var10 = org.apache.spark.internal.config.package$.MODULE$.LOCALITY_WAIT_RACK();
                  break label53;
               }

               var10 = org.apache.spark.internal.config.package$.MODULE$.LOCALITY_WAIT_NODE();
               break label53;
            }

            var10 = org.apache.spark.internal.config.package$.MODULE$.LOCALITY_WAIT_PROCESS();
         }

         ConfigEntry localityWait = var10;
         return localityWait != null ? BoxesRunTime.unboxToLong(this.conf().get(localityWait)) : 0L;
      }
   }

   private Enumeration.Value[] computeValidLocalityLevels() {
      ArrayBuffer levels = new ArrayBuffer();
      if (!this.pendingTasks().forExecutor().isEmpty() && this.pendingTasks().forExecutor().keySet().exists((x$17) -> BoxesRunTime.boxToBoolean($anonfun$computeValidLocalityLevels$1(this, x$17)))) {
         levels.$plus$eq(TaskLocality$.MODULE$.PROCESS_LOCAL());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (!this.pendingTasks().forHost().isEmpty() && this.pendingTasks().forHost().keySet().exists((x$18) -> BoxesRunTime.boxToBoolean($anonfun$computeValidLocalityLevels$2(this, x$18)))) {
         levels.$plus$eq(TaskLocality$.MODULE$.NODE_LOCAL());
      } else {
         BoxedUnit var2 = BoxedUnit.UNIT;
      }

      if (!this.pendingTasks().noPrefs().isEmpty()) {
         levels.$plus$eq(TaskLocality$.MODULE$.NO_PREF());
      } else {
         BoxedUnit var3 = BoxedUnit.UNIT;
      }

      if (!this.pendingTasks().forRack().isEmpty() && this.pendingTasks().forRack().keySet().exists((x$19) -> BoxesRunTime.boxToBoolean($anonfun$computeValidLocalityLevels$3(this, x$19)))) {
         levels.$plus$eq(TaskLocality$.MODULE$.RACK_LOCAL());
      } else {
         BoxedUnit var4 = BoxedUnit.UNIT;
      }

      levels.$plus$eq(TaskLocality$.MODULE$.ANY());
      this.logDebug((Function0)(() -> {
         TaskSet var10000 = this.taskSet();
         return "Valid locality levels for " + var10000 + ": " + levels.mkString(", ");
      }));
      return (Enumeration.Value[])levels.toArray(scala.reflect.ClassTag..MODULE$.apply(Enumeration.Value.class));
   }

   public void executorDecommission(final String execId) {
      this.recomputeLocality();
   }

   public void recomputeLocality() {
      if (!this.isZombie()) {
         int previousLocalityIndex = this.currentLocalityIndex();
         Enumeration.Value previousLocalityLevel = this.myLocalityLevels()[this.currentLocalityIndex()];
         Enumeration.Value[] previousMyLocalityLevels = this.myLocalityLevels();
         this.myLocalityLevels_$eq(this.computeValidLocalityLevels());
         this.localityWaits_$eq((long[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.myLocalityLevels()), (level) -> BoxesRunTime.boxToLong($anonfun$recomputeLocality$1(this, level)), scala.reflect.ClassTag..MODULE$.Long()));
         this.currentLocalityIndex_$eq(this.getLocalityIndex(previousLocalityLevel));
         if (this.currentLocalityIndex() > previousLocalityIndex) {
            this.currentLocalityIndex_$eq(this.getLocalityIndex((Enumeration.Value).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.diff$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.myLocalityLevels()), scala.Predef..MODULE$.wrapRefArray((Object[])previousMyLocalityLevels))))));
         }
      }
   }

   public void executorAdded() {
      this.recomputeLocality();
   }

   // $FF: synthetic method
   public static final void $anonfun$new$2(final TaskSetManager $this, final Task t) {
      t.epoch_$eq($this.epoch());
   }

   // $FF: synthetic method
   public static final long $anonfun$localityWaits$1(final TaskSetManager $this, final Enumeration.Value level) {
      return $this.getLocalityWait(level);
   }

   // $FF: synthetic method
   public static final void $anonfun$addPendingTask$1(final TaskSetManager $this, final PendingTasksByLocality pendingTaskSetToAddTo$1, final int index$1, final boolean resolveRacks$1, final TaskLocation loc) {
      if (loc instanceof ExecutorCacheTaskLocation var8) {
         ((Growable)pendingTaskSetToAddTo$1.forExecutor().getOrElseUpdate(var8.executorId(), () -> new ArrayBuffer())).$plus$eq(BoxesRunTime.boxToInteger(index$1));
      } else if (loc instanceof HDFSCacheTaskLocation var9) {
         Option exe = $this.org$apache$spark$scheduler$TaskSetManager$$sched.getExecutorsAliveOnHost(loc.host());
         if (exe instanceof Some var12) {
            Set set = (Set)var12.value();
            set.foreach((e) -> (ArrayBuffer)((Growable)pendingTaskSetToAddTo$1.forExecutor().getOrElseUpdate(e, () -> new ArrayBuffer())).$plus$eq(BoxesRunTime.boxToInteger(index$1)));
            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Pending task ", " has a cached location at "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INDEX..MODULE$, BoxesRunTime.boxToInteger(index$1))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", where there are executors "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, var9.host())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, set.mkString(","))}))))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(exe)) {
               throw new MatchError(exe);
            }

            $this.logDebug((Function0)(() -> "Pending task " + index$1 + " has a cached location at " + var9.host() + " , but there are no executors alive there."));
            BoxedUnit var14 = BoxedUnit.UNIT;
         }

         BoxedUnit var15 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var16 = BoxedUnit.UNIT;
      }

      ((Growable)pendingTaskSetToAddTo$1.forHost().getOrElseUpdate(loc.host(), () -> new ArrayBuffer())).$plus$eq(BoxesRunTime.boxToInteger(index$1));
      if (resolveRacks$1) {
         $this.org$apache$spark$scheduler$TaskSetManager$$sched.getRackForHost(loc.host()).foreach((rack) -> (ArrayBuffer)((Growable)pendingTaskSetToAddTo$1.forRack().getOrElseUpdate(rack, () -> new ArrayBuffer())).$plus$eq(BoxesRunTime.boxToInteger(index$1)));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasAttemptOnHost$1(final String host$1, final TaskInfo x$3) {
      boolean var3;
      label23: {
         String var10000 = x$3.host();
         if (var10000 == null) {
            if (host$1 == null) {
               break label23;
            }
         } else if (var10000.equals(host$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isTaskExcludededOnExecOrNode$1(final String host$2, final int index$2, final String execId$1, final TaskSetExcludelist excludeList) {
      return excludeList.isNodeExcludedForTask(host$2, index$2) || excludeList.isExecutorExcludedForTask(execId$1, index$2);
   }

   private final Option dequeue$1(final ArrayBuffer list, final String execId$3, final String host$4, final boolean speculative$1) {
      Option task = this.dequeueTaskFromList(execId$3, host$4, list, speculative$1);
      if (speculative$1 && task.isDefined()) {
         this.speculatableTasks().$minus$eq(task.get());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return task;
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$dequeueTaskHelper$2(final Object nonLocalReturnKey1$1, final boolean speculative$1, final int index) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Some(new Tuple3(BoxesRunTime.boxToInteger(index), TaskLocality$.MODULE$.PROCESS_LOCAL(), BoxesRunTime.boxToBoolean(speculative$1))));
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$dequeueTaskHelper$4(final Object nonLocalReturnKey1$1, final boolean speculative$1, final int index) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Some(new Tuple3(BoxesRunTime.boxToInteger(index), TaskLocality$.MODULE$.NODE_LOCAL(), BoxesRunTime.boxToBoolean(speculative$1))));
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$dequeueTaskHelper$5(final Object nonLocalReturnKey1$1, final boolean speculative$1, final int index) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Some(new Tuple3(BoxesRunTime.boxToInteger(index), TaskLocality$.MODULE$.PROCESS_LOCAL(), BoxesRunTime.boxToBoolean(speculative$1))));
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$dequeueTaskHelper$8(final Object nonLocalReturnKey1$1, final boolean speculative$1, final int index) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Some(new Tuple3(BoxesRunTime.boxToInteger(index), TaskLocality$.MODULE$.RACK_LOCAL(), BoxesRunTime.boxToBoolean(speculative$1))));
   }

   // $FF: synthetic method
   public static final void $anonfun$dequeueTaskHelper$6(final TaskSetManager $this, final PendingTasksByLocality pendingTaskSetToUse$1, final Object nonLocalReturnKey1$1, final boolean speculative$1, final String execId$3, final String host$4, final String rack) {
      $this.dequeue$1((ArrayBuffer)pendingTaskSetToUse$1.forRack().getOrElse(rack, () -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)), execId$3, host$4, speculative$1).foreach((index) -> $anonfun$dequeueTaskHelper$8(nonLocalReturnKey1$1, speculative$1, BoxesRunTime.unboxToInt(index)));
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$dequeueTaskHelper$9(final Object nonLocalReturnKey1$1, final boolean speculative$1, final int index) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Some(new Tuple3(BoxesRunTime.boxToInteger(index), TaskLocality$.MODULE$.ANY(), BoxesRunTime.boxToBoolean(speculative$1))));
   }

   // $FF: synthetic method
   public static final void $anonfun$resetDelayScheduleTimer$1(final TaskSetManager $this, final Enumeration.Value locality) {
      $this.currentLocalityIndex_$eq($this.getLocalityIndex(locality));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resourceOffer$1(final String host$5, final String execId$4, final TaskSetExcludelist excludeList) {
      return excludeList.isNodeExcludedForTaskSet(host$5) || excludeList.isExecutorExcludedForTaskSet(execId$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$maybeFinishTaskSet$1(final TaskSetManager $this, final HealthTracker x$4) {
      x$4.updateExcludedForSuccessfulTaskSet($this.taskSet().stageId(), $this.taskSet().stageAttemptId(), ((TaskSetExcludelist)$this.taskSetExcludelistHelperOpt().get()).execToFailures());
   }

   private final boolean tasksNeedToBeScheduledFrom$1(final ArrayBuffer pendingTaskIds) {
      int indexOffset = pendingTaskIds.size();

      while(indexOffset > 0) {
         --indexOffset;
         int index = BoxesRunTime.unboxToInt(pendingTaskIds.apply(indexOffset));
         if (this.copiesRunning()[index] == 0 && !this.successful()[index]) {
            return true;
         }

         pendingTaskIds.remove(indexOffset);
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAllowedLocalityLevel$1(final TaskSetManager $this, final ArrayBuffer emptyKeys$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String id = (String)x0$1._1();
         ArrayBuffer tasks = (ArrayBuffer)x0$1._2();
         if (id != null && tasks != null) {
            if ($this.tasksNeedToBeScheduledFrom$1(tasks)) {
               return true;
            }

            emptyKeys$1.$plus$eq(id);
            return false;
         }
      }

      throw new MatchError(x0$1);
   }

   private final boolean moreTasksToRunIn$1(final HashMap pendingTasks) {
      ArrayBuffer emptyKeys = new ArrayBuffer();
      boolean hasTasks = pendingTasks.exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getAllowedLocalityLevel$1(this, emptyKeys, x0$1)));
      emptyKeys.foreach((id) -> pendingTasks.remove(id));
      return hasTasks;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCompletelyExcludedTaskIfAny$5(final String host$7, final HealthTracker x$5) {
      return x$5.isNodeExcluded(host$7);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCompletelyExcludedTaskIfAny$7(final String exec$1, final HealthTracker x$6) {
      return x$6.isExecutorExcluded(exec$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCompletelyExcludedTaskIfAny$6(final TaskSetManager $this, final TaskSetExcludelist taskSetExcludelist$1, final int indexInTaskSet$1, final String exec) {
      return $this.healthTracker.exists((x$6) -> BoxesRunTime.boxToBoolean($anonfun$getCompletelyExcludedTaskIfAny$7(exec, x$6))) || taskSetExcludelist$1.isExecutorExcludedForTaskSet(exec) || taskSetExcludelist$1.isExecutorExcludedForTask(exec, indexInTaskSet$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCompletelyExcludedTaskIfAny$4(final TaskSetManager $this, final TaskSetExcludelist taskSetExcludelist$1, final int indexInTaskSet$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         String host = (String)x0$1._1();
         HashSet execsOnHost = (HashSet)x0$1._2();
         boolean nodeExcluded = $this.healthTracker.exists((x$5) -> BoxesRunTime.boxToBoolean($anonfun$getCompletelyExcludedTaskIfAny$5(host, x$5))) || taskSetExcludelist$1.isNodeExcludedForTaskSet(host) || taskSetExcludelist$1.isNodeExcludedForTask(host, indexInTaskSet$1);
         return nodeExcluded ? true : execsOnHost.forall((exec) -> BoxesRunTime.boxToBoolean($anonfun$getCompletelyExcludedTaskIfAny$6($this, taskSetExcludelist$1, indexInTaskSet$1, exec)));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$abortSinceCompletelyExcludedOnFailure$1(final TaskSetManager $this, final int indexInTaskSet$2, final TaskSetExcludelist taskSetExcludelist) {
      int partition = $this.tasks()[indexInTaskSet$2].partitionId();
      $this.abort(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n         |Aborting " + $this.taskSet() + " because task " + indexInTaskSet$2 + " (partition " + partition + ")\n         |cannot run anywhere due to node and executor excludeOnFailure.\n         |Most recent failure:\n         |" + taskSetExcludelist.getLatestFailureReason() + "\n         |\n         |ExcludeOnFailure behavior can be configured via spark.excludeOnFailure.*.\n         |")), $this.abort$default$2());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleSuccessfulTask$1(final AccumulatorV2 a) {
      boolean var2;
      label23: {
         Option var10000 = a.name();
         Some var1 = new Some(InternalAccumulator$.MODULE$.RESULT_SIZE());
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
   public static final void $anonfun$handleSuccessfulTask$2(final long tid$2, final DirectTaskResult result$1, final TaskProcessRateCalculator x$7) {
      x$7.org$apache$spark$scheduler$TaskSetManager$$updateAvgTaskProcessRate(tid$2, result$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleSuccessfulTask$3(final TaskInfo attemptInfo) {
      return attemptInfo.running();
   }

   // $FF: synthetic method
   public static final void $anonfun$handleSuccessfulTask$4(final TaskSetManager $this, final TaskInfo info$2, final TaskInfo attemptInfo) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killing attempt ", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(attemptInfo.attemptNumber()))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, $this.taskName(attemptInfo.taskId()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " as the attempt "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, attemptInfo.host())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " succeeded on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(info$2.attemptNumber()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, info$2.host())}))))));
      $this.killedByOtherAttempt().$plus$eq(BoxesRunTime.boxToLong(attemptInfo.taskId()));
      $this.org$apache$spark$scheduler$TaskSetManager$$sched.backend().killTask(attemptInfo.taskId(), attemptInfo.executorId(), true, "another attempt succeeded");
   }

   // $FF: synthetic method
   public static final void $anonfun$handleFailedTask$2(final FetchFailed x2$1, final HealthTracker x$8) {
      x$8.updateExcludedForFetchFailure(x2$1.bmAddress().host(), x2$1.bmAddress().executorId());
   }

   // $FF: synthetic method
   public static final void $anonfun$handleFailedTask$10(final TaskInfo info$3, final int index$3, final String failureReasonString$1, final TaskSetExcludelist x$11) {
      x$11.updateExcludedForFailedTask(info$3.host(), info$3.executorId(), index$3, failureReasonString$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorLost$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorLost$2(final String execId$5, final Tuple2 x$13) {
      if (x$13 == null) {
         throw new MatchError(x$13);
      } else {
         boolean var6;
         label30: {
            TaskInfo info = (TaskInfo)x$13._2();
            String var10000 = info.executorId();
            if (var10000 == null) {
               if (execId$5 == null) {
                  break label30;
               }
            } else if (var10000.equals(execId$5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorLost$4(final String host$8, final BlockManagerId x$12) {
      boolean var3;
      label23: {
         String var10000 = x$12.host();
         if (var10000 == null) {
            if (host$8 != null) {
               break label23;
            }
         } else if (!var10000.equals(host$8)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   private final boolean isShuffleMapOutputAvailable$lzycompute$1(final LazyBoolean isShuffleMapOutputAvailable$lzy$1, final ExecutorLossReason reason$1, final TaskInfo info$4, final long tid$4, final String host$8) {
      synchronized(isShuffleMapOutputAvailable$lzy$1){}

      boolean var9;
      try {
         boolean var10000;
         if (isShuffleMapOutputAvailable$lzy$1.initialized()) {
            var10000 = isShuffleMapOutputAvailable$lzy$1.value();
         } else {
            boolean var10001;
            if (reason$1 instanceof ExecutorDecommission) {
               long mapId = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_USE_OLD_FETCH_PROTOCOL())) ? (long)info$4.partitionId() : tid$4;
               Option locationOpt = ((MapOutputTrackerMaster)this.env().mapOutputTracker()).getMapOutputLocation(BoxesRunTime.unboxToInt(this.shuffleId().get()), mapId);
               var10001 = locationOpt.exists((x$12) -> BoxesRunTime.boxToBoolean($anonfun$executorLost$4(host$8, x$12)));
            } else {
               var10001 = false;
            }

            var10000 = isShuffleMapOutputAvailable$lzy$1.initialize(var10001);
         }

         var9 = var10000;
      } catch (Throwable var15) {
         throw var15;
      }

      return var9;
   }

   private final boolean isShuffleMapOutputAvailable$1(final LazyBoolean isShuffleMapOutputAvailable$lzy$1, final ExecutorLossReason reason$1, final TaskInfo info$4, final long tid$4, final String host$8) {
      return isShuffleMapOutputAvailable$lzy$1.initialized() ? isShuffleMapOutputAvailable$lzy$1.value() : this.isShuffleMapOutputAvailable$lzycompute$1(isShuffleMapOutputAvailable$lzy$1, reason$1, info$4, tid$4, host$8);
   }

   // $FF: synthetic method
   public static final void $anonfun$executorLost$3(final TaskSetManager $this, final ExecutorLossReason reason$1, final String host$8, final Tuple2 x$14) {
      if (x$14 != null) {
         long tid = x$14._1$mcJ$sp();
         TaskInfo info = (TaskInfo)x$14._2();
         LazyBoolean isShuffleMapOutputAvailable$lzy = new LazyBoolean();
         int index = info.index();
         if ($this.successful()[index] && !info.running() && !$this.killedByOtherAttempt().contains(BoxesRunTime.boxToLong(tid)) && !$this.isShuffleMapOutputAvailable$1(isShuffleMapOutputAvailable$lzy, reason$1, info, tid, host$8)) {
            $this.successful()[index] = false;
            --$this.copiesRunning()[index];
            $this.tasksSuccessful_$eq($this.tasksSuccessful() - 1);
            $this.addPendingTask(index, $this.addPendingTask$default$2(), $this.addPendingTask$default$3());
            $this.emptyTaskInfoAccumulablesAndNotifyDagScheduler(tid, $this.tasks()[index], Resubmitted$.MODULE$, (Object)null, (Seq)scala.package..MODULE$.Seq().empty(), (long[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Long()));
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$14);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorLost$5(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorLost$6(final String execId$5, final Tuple2 x$15) {
      if (x$15 == null) {
         throw new MatchError(x$15);
      } else {
         boolean var6;
         label32: {
            TaskInfo info = (TaskInfo)x$15._2();
            if (info.running()) {
               String var10000 = info.executorId();
               if (var10000 == null) {
                  if (execId$5 == null) {
                     break label32;
                  }
               } else if (var10000.equals(execId$5)) {
                  break label32;
               }
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executorLost$7(final TaskSetManager $this, final ExecutorLossReason reason$1, final Tuple2 x$16) {
      if (x$16 == null) {
         throw new MatchError(x$16);
      } else {
         long tid;
         TaskInfo info;
         boolean var10000;
         label49: {
            tid = x$16._1$mcJ$sp();
            info = (TaskInfo)x$16._2();
            if (reason$1 instanceof ExecutorExited) {
               ExecutorExited var12 = (ExecutorExited)reason$1;
               boolean var13 = var12.exitCausedByApp();
               if (!var13) {
                  var10000 = false;
                  break label49;
               }
            }

            if (ExecutorKilled$.MODULE$.equals(reason$1) ? true : reason$1 instanceof ExecutorDecommission) {
               var10000 = false;
            } else {
               label39: {
                  if (reason$1 instanceof ExecutorProcessLost) {
                     ExecutorProcessLost var14 = (ExecutorProcessLost)reason$1;
                     boolean var15 = var14.causedByApp();
                     if (!var15) {
                        var10000 = false;
                        break label39;
                     }
                  }

                  var10000 = !info.launching();
               }
            }
         }

         boolean exitCausedByApp = var10000;
         $this.handleFailedTask(tid, TaskState$.MODULE$.FAILED(), new ExecutorLostFailure(info.executorId(), exitCausedByApp, new Some(reason$1.toString())));
         BoxedUnit var16 = BoxedUnit.UNIT;
      }
   }

   private final boolean checkMaySpeculate$1(final boolean customizedThreshold$1, final long runtimeMs$1, final double threshold$1, final long tid$5) {
      return !customizedThreshold$1 && !this.taskProcessRateCalculator().isEmpty() ? this.isInefficient$1(runtimeMs$1, threshold$1, tid$5) : true;
   }

   private final boolean isInefficient$1(final long runtimeMs$1, final double threshold$1, final long tid$5) {
      return (double)runtimeMs$1 > this.efficientTaskDurationFactor() * threshold$1 || this.taskProcessRateIsInefficient$1(tid$5);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkAndSubmitSpeculatableTasks$2(final TaskSetManager $this, final long tid$5, final TaskProcessRateCalculator calculator) {
      return calculator.org$apache$spark$scheduler$TaskSetManager$$getRunningTasksProcessRate(tid$5) < calculator.org$apache$spark$scheduler$TaskSetManager$$getAvgTaskProcessRate() * $this.efficientTaskProcessMultiplier();
   }

   private final boolean taskProcessRateIsInefficient$1(final long tid$5) {
      return this.taskProcessRateCalculator().forall((calculator) -> BoxesRunTime.boxToBoolean($anonfun$checkAndSubmitSpeculatableTasks$2(this, tid$5, calculator)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkAndSubmitSpeculatableTasks$3(final TaskSetManager $this, final TaskInfo info$5, final ExecutorDecommissionState decomState) {
      double taskEndTimeBasedOnMedianDuration = (double)info$5.launchTime() + $this.successfulTaskDurations().percentile();
      long executorDecomTime = decomState.startTime() + BoxesRunTime.unboxToLong($this.executorDecommissionKillInterval().get());
      return (double)executorDecomTime < taskEndTimeBasedOnMedianDuration;
   }

   private final boolean shouldSpeculateForExecutorDecommissioning$1(final boolean customizedThreshold$1, final TaskInfo info$5) {
      return !customizedThreshold$1 && this.executorDecommissionKillInterval().isDefined() && !this.successfulTaskDurations().isEmpty() && this.org$apache$spark$scheduler$TaskSetManager$$sched.getExecutorDecommissionState(info$5.executorId()).exists((decomState) -> BoxesRunTime.boxToBoolean($anonfun$checkAndSubmitSpeculatableTasks$3(this, info$5, decomState)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$computeValidLocalityLevels$1(final TaskSetManager $this, final String x$17) {
      return $this.org$apache$spark$scheduler$TaskSetManager$$sched.isExecutorAlive(x$17);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$computeValidLocalityLevels$2(final TaskSetManager $this, final String x$18) {
      return $this.org$apache$spark$scheduler$TaskSetManager$$sched.hasExecutorsAliveOnHost(x$18);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$computeValidLocalityLevels$3(final TaskSetManager $this, final String x$19) {
      return $this.org$apache$spark$scheduler$TaskSetManager$$sched.hasHostAliveOnRack(x$19);
   }

   // $FF: synthetic method
   public static final long $anonfun$recomputeLocality$1(final TaskSetManager $this, final Enumeration.Value level) {
      return $this.getLocalityWait(level);
   }

   public TaskSetManager(final TaskSchedulerImpl sched, final TaskSet taskSet, final int maxTaskFailures, final Option healthTracker, final Clock clock) {
      this.org$apache$spark$scheduler$TaskSetManager$$sched = sched;
      this.taskSet = taskSet;
      this.maxTaskFailures = maxTaskFailures;
      this.healthTracker = healthTracker;
      this.clock = clock;
      Logging.$init$(this);
      this.conf = sched.sc().conf();
      this.maxResultSize = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.MAX_RESULT_SIZE()));
      this.env = SparkEnv$.MODULE$.get();
      this.ser = this.env().closureSerializer().newInstance();
      this.tasks = taskSet.tasks();
      this.isShuffleMapTasks = this.tasks()[0] instanceof ShuffleMapTask;
      this.shuffleId = taskSet.shuffleId();
      this.partitionToIndex = scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(this.tasks()))), (x0$1) -> {
         if (x0$1 != null) {
            Task t = (Task)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(t.partitionId())), BoxesRunTime.boxToInteger(idx));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      this.numTasks = this.tasks().length;
      this.copiesRunning = new int[this.numTasks()];
      this.speculationEnabled = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_ENABLED()));
      this.efficientTaskProcessMultiplier = BoxesRunTime.unboxToDouble(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_EFFICIENCY_TASK_PROCESS_RATE_MULTIPLIER()));
      this.efficientTaskDurationFactor = BoxesRunTime.unboxToDouble(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_EFFICIENCY_TASK_DURATION_FACTOR()));
      this.speculationQuantile = BoxesRunTime.unboxToDouble(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_QUANTILE()));
      this.speculationMultiplier = BoxesRunTime.unboxToDouble(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_MULTIPLIER()));
      this.minFinishedForSpeculation = scala.math.package..MODULE$.max((int)scala.runtime.RichDouble..MODULE$.floor$extension(scala.Predef..MODULE$.doubleWrapper(this.speculationQuantile() * (double)this.numTasks())), 1);
      this.speculationTaskDurationThresOpt = (Option)this.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SPECULATION_TASK_DURATION_THRESHOLD());
      this.isSpeculationThresholdSpecified = this.speculationTaskDurationThresOpt().exists((JFunction1.mcZJ.sp)(x$1) -> x$1 > 0L);
      int rpId = taskSet.resourceProfileId();
      ResourceProfile resourceProfile = sched.sc().resourceProfileManager().resourceProfileFromId(rpId);
      int slots = !resourceProfile.isCoresLimitKnown() ? 1 : resourceProfile.maxTasksPerExecutor(this.conf());
      this.speculationTasksLessEqToSlots = this.numTasks() <= slots;
      this.executorDecommissionKillInterval = ((Option)this.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_DECOMMISSION_KILL_INTERVAL())).map((JFunction1.mcJJ.sp)(x$1) -> TimeUnit.SECONDS.toMillis(x$1));
      this.taskProcessRateCalculator = (Option)(sched.efficientTaskCalcualtionEnabled() ? new Some(new TaskProcessRateCalculator()) : scala.None..MODULE$);
      this.successful = new boolean[this.numTasks()];
      this.numFailures = new int[this.numTasks()];
      this.killedByOtherAttempt = new HashSet();
      this.taskAttempts = (List[])scala.Array..MODULE$.fill(this.numTasks(), () -> scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(List.class));
      this.tasksSuccessful = 0;
      this.weight = 1;
      this.minShare = 0;
      this.priority = taskSet.priority();
      this.stageId = taskSet.stageId();
      this.name = "TaskSet_" + taskSet.id();
      this.parent = null;
      this.totalResultSize = 0L;
      this.calculatedTasks = 0;
      this.taskSetExcludelistHelperOpt = (Option)(TaskSetExcludelist$.MODULE$.isExcludeOnFailureEnabled(this.conf()) ? new Some(new TaskSetExcludelist(sched.sc().listenerBus(), this.conf(), this.stageId(), taskSet.stageAttemptId(), clock, TaskSetExcludelist$.MODULE$.$lessinit$greater$default$6())) : (healthTracker.isDefined() ? new Some(new TaskSetExcludelist(sched.sc().listenerBus(), this.conf(), this.stageId(), taskSet.stageAttemptId(), clock, true)) : scala.None..MODULE$));
      this.runningTasksSet = new HashSet();
      this.isZombie = false;
      this.barrierPendingLaunchTasks = new HashMap();
      this.lastResourceOfferFailLogTime = clock.getTimeMillis();
      this.pendingTasks = new PendingTasksByLocality();
      this.speculatableTasks = new HashSet();
      this.pendingSpeculatableTasks = new PendingTasksByLocality();
      this.taskInfos = new HashMap();
      this.successfulTaskDurations = new PercentileHeap(PercentileHeap$.MODULE$.$lessinit$greater$default$1());
      this.EXCEPTION_PRINT_INTERVAL = this.conf().getLong("spark.logging.exceptionPrintInterval", 10000L);
      this.recentExceptions = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.epoch = sched.mapOutputTracker().getEpoch();
      this.logDebug((Function0)(() -> {
         TaskSet var10000 = this.taskSet();
         return "Epoch for " + var10000 + ": " + this.epoch();
      }));
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.tasks()), (t) -> {
         $anonfun$new$2(this, t);
         return BoxedUnit.UNIT;
      });
      this.addPendingTasks();
      this.myLocalityLevels = this.computeValidLocalityLevels();
      this.legacyLocalityWaitReset = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.LEGACY_LOCALITY_WAIT_RESET()));
      this.currentLocalityIndex = 0;
      this.lastLocalityWaitResetTime = clock.getTimeMillis();
      this.localityWaits = (long[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.myLocalityLevels()), (level) -> BoxesRunTime.boxToLong($anonfun$localityWaits$1(this, level)), scala.reflect.ClassTag..MODULE$.Long());
      this.emittedTaskSizeWarning = false;
      this.dropTaskInfoAccumulablesOnTaskCompletion = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.DROP_TASK_INFO_ACCUMULABLES_ON_TASK_COMPLETION()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class TaskProcessRateCalculator {
      private long totalRecordsRead;
      private long totalExecutorRunTime;
      private double avgTaskProcessRate;
      private final ConcurrentHashMap runningTasksProcessRate;
      // $FF: synthetic field
      public final TaskSetManager $outer;

      private long totalRecordsRead() {
         return this.totalRecordsRead;
      }

      private void totalRecordsRead_$eq(final long x$1) {
         this.totalRecordsRead = x$1;
      }

      private long totalExecutorRunTime() {
         return this.totalExecutorRunTime;
      }

      private void totalExecutorRunTime_$eq(final long x$1) {
         this.totalExecutorRunTime = x$1;
      }

      private double avgTaskProcessRate() {
         return this.avgTaskProcessRate;
      }

      private void avgTaskProcessRate_$eq(final double x$1) {
         this.avgTaskProcessRate = x$1;
      }

      private ConcurrentHashMap runningTasksProcessRate() {
         return this.runningTasksProcessRate;
      }

      public double org$apache$spark$scheduler$TaskSetManager$$getAvgTaskProcessRate() {
         return this.avgTaskProcessRate();
      }

      public double org$apache$spark$scheduler$TaskSetManager$$getRunningTasksProcessRate(final long taskId) {
         return BoxesRunTime.unboxToDouble(this.runningTasksProcessRate().getOrDefault(BoxesRunTime.boxToLong(taskId), BoxesRunTime.boxToDouble((double)0.0F)));
      }

      public void org$apache$spark$scheduler$TaskSetManager$$updateAvgTaskProcessRate(final long taskId, final DirectTaskResult result) {
         LongRef recordsRead = LongRef.create(0L);
         LongRef executorRunTime = LongRef.create(0L);
         result.accumUpdates().foreach((a) -> {
            $anonfun$org$apache$spark$scheduler$TaskSetManager$$updateAvgTaskProcessRate$1(recordsRead, executorRunTime, a);
            return BoxedUnit.UNIT;
         });
         this.totalRecordsRead_$eq(this.totalRecordsRead() + recordsRead.elem);
         this.totalExecutorRunTime_$eq(this.totalExecutorRunTime() + executorRunTime.elem);
         if (this.totalRecordsRead() > 0L && this.totalExecutorRunTime() > 0L) {
            this.avgTaskProcessRate_$eq(this.org$apache$spark$scheduler$TaskSetManager$TaskProcessRateCalculator$$$outer().org$apache$spark$scheduler$TaskSetManager$$sched.getTaskProcessRate(this.totalRecordsRead(), this.totalExecutorRunTime()));
         }

         this.runningTasksProcessRate().remove(BoxesRunTime.boxToLong(taskId));
      }

      public void updateRunningTaskProcessRate(final long taskId, final double taskProcessRate) {
         this.runningTasksProcessRate().put(BoxesRunTime.boxToLong(taskId), BoxesRunTime.boxToDouble(taskProcessRate));
      }

      // $FF: synthetic method
      public TaskSetManager org$apache$spark$scheduler$TaskSetManager$TaskProcessRateCalculator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$org$apache$spark$scheduler$TaskSetManager$$updateAvgTaskProcessRate$1(final LongRef recordsRead$1, final LongRef executorRunTime$1, final AccumulatorV2 a) {
         label36: {
            Option var10000 = a.name();
            Some var3 = new Some(InternalAccumulator.shuffleRead$.MODULE$.RECORDS_READ());
            if (var10000 == null) {
               if (var3 == null) {
                  break label36;
               }
            } else if (var10000.equals(var3)) {
               break label36;
            }

            var10000 = a.name();
            Some var4 = new Some(InternalAccumulator.input$.MODULE$.RECORDS_READ());
            if (var10000 == null) {
               if (var4 == null) {
                  break label36;
               }
            } else if (var10000.equals(var4)) {
               break label36;
            }

            label24: {
               var10000 = a.name();
               Some var6 = new Some(InternalAccumulator$.MODULE$.EXECUTOR_RUN_TIME());
               if (var10000 == null) {
                  if (var6 == null) {
                     break label24;
                  }
               } else if (var10000.equals(var6)) {
                  break label24;
               }

               return;
            }

            LongAccumulator acc = (LongAccumulator)a;
            executorRunTime$1.elem = scala.Predef..MODULE$.Long2long(acc.value());
            return;
         }

         LongAccumulator acc = (LongAccumulator)a;
         recordsRead$1.elem += scala.Predef..MODULE$.Long2long(acc.value());
      }

      public TaskProcessRateCalculator() {
         if (TaskSetManager.this == null) {
            throw null;
         } else {
            this.$outer = TaskSetManager.this;
            super();
            this.totalRecordsRead = 0L;
            this.totalExecutorRunTime = 0L;
            this.avgTaskProcessRate = Double.MAX_VALUE;
            this.runningTasksProcessRate = new ConcurrentHashMap();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
