package org.apache.spark.util.collection;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.zip.Checksum;
import org.apache.spark.Aggregator;
import org.apache.spark.ConstantPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.executor.ShuffleWriteMetrics;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.ShufflePartitionPairsWriter;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.shuffle.api.ShufflePartitionWriter;
import org.apache.spark.shuffle.checksum.ShuffleChecksumSupport;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.DiskBlockManager;
import org.apache.spark.storage.DiskBlockObjectWriter;
import org.apache.spark.storage.FileSegment;
import org.apache.spark.storage.ShuffleBlockId;
import org.apache.spark.storage.TempShuffleBlockId;
import org.apache.spark.util.CompletionIterator$;
import org.sparkproject.guava.io.ByteStreams;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Product2;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.None.;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.PriorityQueue;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019=b!CA/\u0003?\u0002\u0011qMA:\u0011)\t)\r\u0001B\u0001B\u0003%\u0011q\u0019\u0005\u000b\u0003\u001f\u0004!\u0011!Q\u0001\n\u0005E\u0007BCAr\u0001\t\u0005\t\u0015!\u0003\u0002f\"Q\u0011Q\u001e\u0001\u0003\u0002\u0003\u0006I!a<\t\u0015\t%\u0001A!A!\u0002\u0013\u0011Y\u0001C\u0004\u0003\u0016\u0001!\tAa\u0006\t\u0013\t\u0015\u0002A1A\u0005\n\t\u001d\u0002\u0002\u0003B\u0018\u0001\u0001\u0006IA!\u000b\t\u0013\tE\u0002A1A\u0005\n\tM\u0002\u0002\u0003B\u001e\u0001\u0001\u0006IA!\u000e\t\u0013\tu\u0002A1A\u0005\n\t}\u0002\u0002\u0003B!\u0001\u0001\u0006I!a:\t\u0013\t\r\u0003A1A\u0005\n\t\u0015\u0003\u0002\u0003B*\u0001\u0001\u0006IAa\u0012\t\u0013\tU\u0003A1A\u0005\n\t]\u0003\u0002\u0003B0\u0001\u0001\u0006IA!\u0017\t\u0013\t\u0005\u0004A1A\u0005\n\t\r\u0004\u0002\u0003B6\u0001\u0001\u0006IA!\u001a\t\u0013\t5\u0004A1A\u0005\n\t=\u0004\u0002\u0003B<\u0001\u0001\u0006IA!\u001d\t\u0013\te\u0004A1A\u0005\n\tM\u0002\u0002\u0003B>\u0001\u0001\u0006IA!\u000e\t\u0013\tu\u0004A1A\u0005\n\t}\u0004\u0002\u0003BD\u0001\u0001\u0006IA!!\t\u0013\t%\u0005\u00011A\u0005\n\t-\u0005\"\u0003BJ\u0001\u0001\u0007I\u0011\u0002BK\u0011!\u0011\t\u000b\u0001Q!\n\t5\u0005\"\u0003BV\u0001\u0001\u0007I\u0011\u0002BW\u0011%\u0011)\f\u0001a\u0001\n\u0013\u00119\f\u0003\u0005\u0003<\u0002\u0001\u000b\u0015\u0002BX\u0011%\u0011y\f\u0001a\u0001\n\u0013\u0011y\bC\u0005\u0003B\u0002\u0001\r\u0011\"\u0003\u0003D\"A!q\u0019\u0001!B\u0013\u0011\t\tC\u0004\u0003J\u0002!\tAa \t\u0013\t-\u0007\u00011A\u0005\n\t}\u0004\"\u0003Bg\u0001\u0001\u0007I\u0011\u0002Bh\u0011!\u0011\u0019\u000e\u0001Q!\n\t\u0005\u0005b\u0002Bk\u0001\u0011\u0005!q\u0010\u0005\n\u0005/\u0004\u0001\u0019!C\u0005\u00053D\u0011B!9\u0001\u0001\u0004%IAa9\t\u0011\t\u001d\b\u0001)Q\u0005\u00057D\u0011Ba;\u0001\u0005\u0004%IA!<\t\u0011\r\r\u0007\u0001)A\u0005\u0005_D\u0011b!2\u0001\u0001\u0004%Iaa2\t\u0013\u0011\r\u0002\u00011A\u0005\n\u0011\u0015\u0002\u0002\u0003C\u0015\u0001\u0001\u0006Ka!3\t\u0013\u00115\u0002A1A\u0005\n\u0011=\u0002\u0002\u0003C!\u0001\u0001\u0006I\u0001\"\r\t\u000f\u0011\r\u0003\u0001\"\u0001\u0004<!IAQ\t\u0001C\u0002\u0013%Aq\t\u0005\t\t#\u0002\u0001\u0015!\u0003\u0005J!9A1\u000b\u0001\u0005\n\u0011Uc\u0001CB\u0001\u0001\u0001\u0006Iia\u0001\t\u0015\r]QG!f\u0001\n\u0003\u0019I\u0002\u0003\u0006\u0004,U\u0012\t\u0012)A\u0005\u00077A!b!\f6\u0005+\u0007I\u0011AB\u0018\u0011)\u00199$\u000eB\tB\u0003%1\u0011\u0007\u0005\u000b\u0007s)$Q3A\u0005\u0002\rm\u0002BCB\"k\tE\t\u0015!\u0003\u0004>!Q1QI\u001b\u0003\u0016\u0004%\taa\u000f\t\u0015\r\u001dSG!E!\u0002\u0013\u0019i\u0004C\u0004\u0003\u0016U\"\ta!\u0013\t\u0013\rMS'!A\u0005\u0002\rU\u0003\"CB0kE\u0005I\u0011AB1\u0011%\u00199(NI\u0001\n\u0003\u0019I\bC\u0005\u0004~U\n\n\u0011\"\u0001\u0004\u0000!I11Q\u001b\u0012\u0002\u0013\u00051q\u0010\u0005\n\u0007\u000b+\u0014\u0011!C!\u0007\u000fC\u0011b!&6\u0003\u0003%\tAa\r\t\u0013\r]U'!A\u0005\u0002\re\u0005\"CBOk\u0005\u0005I\u0011IBP\u0011%\u0019I+NA\u0001\n\u0003\u0019Y\u000bC\u0005\u00040V\n\t\u0011\"\u0011\u00042\"I1QW\u001b\u0002\u0002\u0013\u00053q\u0017\u0005\n\u0007s+\u0014\u0011!C!\u0007wC\u0011b!06\u0003\u0003%\tea0\b\u0013\u0011e\u0003!!Q\t\n\u0011mc!CB\u0001\u0001\u0005\u0005\u000b\u0012\u0002C/\u0011\u001d\u0011)B\u0014C\u0001\t_B\u0011b!/O\u0003\u0003%)ea/\t\u0013\u0011Ed*!A\u0005\u0002\u0012M\u0004\"\u0003C?\u001d\u0006\u0005I\u0011\u0011C@\u0011%!i\t\u0001b\u0001\n\u0013\u0011i\u000f\u0003\u0005\u0005\u0010\u0002\u0001\u000b\u0011\u0002Bx\u0011%!\t\n\u0001C\u0001\u0003O\u0012\u0019\u0004C\u0004\u0005\u0014\u0002!\t\u0001\"&\t\u000f\u0011\r\u0006\u0001\"\u0003\u0005&\"AAq\u0003\u0001!\n#\"Y\u000b\u0003\u0005\u00050\u0002\u0001K\u0011\u000bC\r\u0011!!\t\f\u0001Q\u0005\n\u0011M\u0006b\u0002C`\u0001\u0011%A\u0011\u0019\u0005\b\t/\u0004A\u0011\u0002Cm\u0011\u001d!\u0019\u000f\u0001C\u0005\tK4\u0001\u0002\"?\u0001A\u0003%A1 \u0005\u000b\t/q&\u0011!Q\u0001\n\tu\bb\u0002B\u000b=\u0012\u0005AQ \u0005\n\u000b\u0007q&\u0019!C\u0001\u0007wA\u0001\"\"\u0002_A\u0003%1Q\b\u0005\n\u000b\u000fq\u0006\u0019!C\u0001\u0005gA\u0011\"\"\u0003_\u0001\u0004%\t!b\u0003\t\u0011\u0015=a\f)Q\u0005\u0005kA\u0011\"\"\u0005_\u0001\u0004%\tAa \t\u0013\u0015Ma\f1A\u0005\u0002\u0015U\u0001\u0002CC\r=\u0002\u0006KA!!\t\u0013\u0015ma\f1A\u0005\u0002\tM\u0002\"CC\u000f=\u0002\u0007I\u0011AC\u0010\u0011!)\u0019C\u0018Q!\n\tU\u0002\"CC\u0013=\u0002\u0007I\u0011\u0001B\u001a\u0011%)9C\u0018a\u0001\n\u0003)I\u0003\u0003\u0005\u0006.y\u0003\u000b\u0015\u0002B\u001b\u0011%)yC\u0018a\u0001\n\u0003\u0011\u0019\u0004C\u0005\u00062y\u0003\r\u0011\"\u0001\u00064!AQq\u00070!B\u0013\u0011)\u0004C\u0005\u0006:y\u0003\r\u0011\"\u0001\u0006<!IQ1\t0A\u0002\u0013\u0005QQ\t\u0005\t\u000b\u0013r\u0006\u0015)\u0003\u0006>!IQ1\n0A\u0002\u0013\u0005QQ\n\u0005\n\u000b+r\u0006\u0019!C\u0001\u000b/B\u0001\"b\u0017_A\u0003&Qq\n\u0005\n\u000b;r\u0006\u0019!C\u0001\u000b?B\u0011\"b\u0019_\u0001\u0004%\t!\"\u001a\t\u0011\u0015%d\f)Q\u0005\u000bCB\u0011\"b\u001b_\u0001\u0004%\tA!7\t\u0013\u00155d\f1A\u0005\u0002\u0015=\u0004\u0002CC:=\u0002\u0006KAa7\t\u000f\u0015Ud\f\"\u0001\u0006x!9Q\u0011\u00100\u0005\n\u0015m\u0004bBC?=\u0012%Qq\u0010\u0005\n\u000b\u0003s\u0006\u0019!C\u0001\u0005gA\u0011\"b!_\u0001\u0004%\t!\"\"\t\u0011\u0015%e\f)Q\u0005\u0005kAq!b#_\t\u0003)i\tC\u0004\u0006\u0010z#\t!b\u001f\t\u000f\u0015E\u0005\u0001\"\u0001\u0006\u0014\"9Q\u0011\u0014\u0001\u0005\u0002\u0015m\u0005bBCO\u0001\u0011\u0005Qq\u0014\u0005\b\u000bC\u0003A\u0011ACR\u0011\u001d)9\u000b\u0001C\u0001\u000bSCq!b4\u0001\t\u0003)Y\bC\u0004\u0006R\u0002!I!b5\u0007\u0011\u0015e\u0007\u0001)A\u0005\u000b7D1\"b\u0002\u0002\u001c\t\u0005\t\u0015!\u0003\u00036!YQq[A\u000e\u0005\u0003\u0005\u000b\u0011BCo\u0011!\u0011)\"a\u0007\u0005\u0002\u0015\r\b\u0002\u0003C\u0010\u00037!\tE!7\t\u0011\u0011\u0005\u00121\u0004C!\u000bW4\u0001ba3\u0001A\u0003%1Q\u001a\u0005\f\u00077\f9C!a\u0001\n\u0003\u0019i\u000eC\u0006\u0004`\u0006\u001d\"\u00111A\u0005\u0002\r\u0005\bbCBs\u0003O\u0011\t\u0011)Q\u0005\u0007\u001fD\u0001B!\u0006\u0002(\u0011\u00051q\u001d\u0005\u000b\u0007W\f9C1A\u0005\n\r5\b\"CB{\u0003O\u0001\u000b\u0011BBx\u0011)\u001990a\nA\u0002\u0013%1Q\u001c\u0005\u000b\u0007s\f9\u00031A\u0005\n\rm\b\"CB\u0000\u0003O\u0001\u000b\u0015BBh\u0011)!\t!a\nA\u0002\u0013%A1\u0001\u0005\u000b\t\u000b\t9\u00031A\u0005\n\u0011\u001d\u0001\"\u0003C\u0006\u0003O\u0001\u000b\u0015BBj\u0011)!i!a\nA\u0002\u0013%!\u0011\u001c\u0005\u000b\t\u001f\t9\u00031A\u0005\n\u0011E\u0001\"\u0003C\u000b\u0003O\u0001\u000b\u0015\u0002Bn\u0011!!9\"a\n\u0005\u0002\u0011e\u0001\u0002\u0003C\u000e\u0003O!\t\u0001\"\b\t\u0011\u0011}\u0011q\u0005C!\u00053D\u0001\u0002\"\t\u0002(\u0011\u0005CQD\u0004\r\u000b[\fy&!A\t\u0002\u0005\u001dTq\u001e\u0004\r\u0003;\ny&!A\t\u0002\u0005\u001dT\u0011\u001f\u0005\t\u0005+\t\t\u0006\"\u0001\u0006t\"QQQ_A)#\u0003%\t!b>\t\u0015\u0019%\u0011\u0011KI\u0001\n\u00031Y\u0001\u0003\u0006\u0007\u0018\u0005E\u0013\u0013!C\u0001\r3A!B\"\t\u0002RE\u0005I\u0011\u0001D\u0012\u00059)\u0005\u0010^3s]\u0006d7k\u001c:uKJTA!!\u0019\u0002d\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\t\u0005\u0015\u0014qM\u0001\u0005kRLGN\u0003\u0003\u0002j\u0005-\u0014!B:qCJ\\'\u0002BA7\u0003_\na!\u00199bG\",'BAA9\u0003\ry'oZ\u000b\t\u0003k\nI)a8\u0002&N9\u0001!a\u001e\u0002*\u0006U\u0006CBA=\u0003w\ny(\u0004\u0002\u0002`%!\u0011QPA0\u0005%\u0019\u0006/\u001b7mC\ndW\r\u0005\u0005\u0002z\u0005\u0005\u0015QQAR\u0013\u0011\t\u0019)a\u0018\u0003C]\u0013\u0018\u000e^1cY\u0016\u0004\u0016M\u001d;ji&|g.\u001a3QC&\u00148i\u001c7mK\u000e$\u0018n\u001c8\u0011\t\u0005\u001d\u0015\u0011\u0012\u0007\u0001\t\u001d\tY\t\u0001b\u0001\u0003\u001f\u0013\u0011aS\u0002\u0001#\u0011\t\t*!(\u0011\t\u0005M\u0015\u0011T\u0007\u0003\u0003+S!!a&\u0002\u000bM\u001c\u0017\r\\1\n\t\u0005m\u0015Q\u0013\u0002\b\u001d>$\b.\u001b8h!\u0011\t\u0019*a(\n\t\u0005\u0005\u0016Q\u0013\u0002\u0004\u0003:L\b\u0003BAD\u0003K#q!a*\u0001\u0005\u0004\tyIA\u0001D!\u0011\tY+!-\u000e\u0005\u00055&\u0002BAX\u0003O\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\u0003g\u000biKA\u0004M_\u001e<\u0017N\\4\u0011\t\u0005]\u0016\u0011Y\u0007\u0003\u0003sSA!a/\u0002>\u0006A1\r[3dWN,XN\u0003\u0003\u0002@\u0006\u001d\u0014aB:ik\u001a4G.Z\u0005\u0005\u0003\u0007\fIL\u0001\fTQV4g\r\\3DQ\u0016\u001c7n];n'V\u0004\bo\u001c:u\u0003\u001d\u0019wN\u001c;fqR\u0004B!!3\u0002L6\u0011\u0011qM\u0005\u0005\u0003\u001b\f9GA\u0006UCN\\7i\u001c8uKb$\u0018AC1hOJ,w-\u0019;peB1\u00111SAj\u0003/LA!!6\u0002\u0016\n1q\n\u001d;j_:\u0004\"\"!3\u0002Z\u0006\u0015\u0015Q\\AR\u0013\u0011\tY.a\u001a\u0003\u0015\u0005;wM]3hCR|'\u000f\u0005\u0003\u0002\b\u0006}GaBAq\u0001\t\u0007\u0011q\u0012\u0002\u0002-\u0006Y\u0001/\u0019:uSRLwN\\3s!\u0019\t\u0019*a5\u0002hB!\u0011\u0011ZAu\u0013\u0011\tY/a\u001a\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\t_J$WM]5oOB1\u00111SAj\u0003c\u0004b!a=\u0003\u0004\u0005\u0015e\u0002BA{\u0003\u007ftA!a>\u0002~6\u0011\u0011\u0011 \u0006\u0005\u0003w\fi)\u0001\u0004=e>|GOP\u0005\u0003\u0003/KAA!\u0001\u0002\u0016\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002B\u0003\u0005\u000f\u0011\u0001b\u0014:eKJLgn\u001a\u0006\u0005\u0005\u0003\t)*\u0001\u0006tKJL\u0017\r\\5{KJ\u0004BA!\u0004\u0003\u00125\u0011!q\u0002\u0006\u0005\u0005\u0013\t9'\u0003\u0003\u0003\u0014\t=!AC*fe&\fG.\u001b>fe\u00061A(\u001b8jiz\"BB!\u0007\u0003\u001c\tu!q\u0004B\u0011\u0005G\u0001\u0012\"!\u001f\u0001\u0003\u000b\u000bi.a)\t\u000f\u0005\u0015g\u00011\u0001\u0002H\"I\u0011q\u001a\u0004\u0011\u0002\u0003\u0007\u0011\u0011\u001b\u0005\n\u0003G4\u0001\u0013!a\u0001\u0003KD\u0011\"!<\u0007!\u0003\u0005\r!a<\t\u0013\t%a\u0001%AA\u0002\t-\u0011\u0001B2p]\u001a,\"A!\u000b\u0011\t\u0005%'1F\u0005\u0005\u0005[\t9GA\u0005Ta\u0006\u00148nQ8oM\u0006)1m\u001c8gA\u0005ia.^7QCJ$\u0018\u000e^5p]N,\"A!\u000e\u0011\t\u0005M%qG\u0005\u0005\u0005s\t)JA\u0002J]R\faB\\;n!\u0006\u0014H/\u001b;j_:\u001c\b%A\tbGR,\u0018\r\u001c)beRLG/[8oKJ,\"!a:\u0002%\u0005\u001cG/^1m!\u0006\u0014H/\u001b;j_:,'\u000fI\u0001\rE2|7m['b]\u0006<WM]\u000b\u0003\u0005\u000f\u0002BA!\u0013\u0003P5\u0011!1\n\u0006\u0005\u0005\u001b\n9'A\u0004ti>\u0014\u0018mZ3\n\t\tE#1\n\u0002\r\u00052|7m['b]\u0006<WM]\u0001\u000eE2|7m['b]\u0006<WM\u001d\u0011\u0002!\u0011L7o\u001b\"m_\u000e\\W*\u00198bO\u0016\u0014XC\u0001B-!\u0011\u0011IEa\u0017\n\t\tu#1\n\u0002\u0011\t&\u001c8N\u00117pG.l\u0015M\\1hKJ\f\u0011\u0003Z5tW\ncwnY6NC:\fw-\u001a:!\u0003E\u0019XM]5bY&TXM]'b]\u0006<WM]\u000b\u0003\u0005K\u0002BA!\u0004\u0003h%!!\u0011\u000eB\b\u0005E\u0019VM]5bY&TXM]'b]\u0006<WM]\u0001\u0013g\u0016\u0014\u0018.\u00197ju\u0016\u0014X*\u00198bO\u0016\u0014\b%A\u0006tKJLen\u001d;b]\u000e,WC\u0001B9!\u0011\u0011iAa\u001d\n\t\tU$q\u0002\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018J\\:uC:\u001cW-\u0001\u0007tKJLen\u001d;b]\u000e,\u0007%\u0001\bgS2,')\u001e4gKJ\u001c\u0016N_3\u0002\u001f\u0019LG.\u001a\"vM\u001a,'oU5{K\u0002\n1c]3sS\u0006d\u0017N_3s\u0005\u0006$8\r[*ju\u0016,\"A!!\u0011\t\u0005M%1Q\u0005\u0005\u0005\u000b\u000b)J\u0001\u0003M_:<\u0017\u0001F:fe&\fG.\u001b>fe\n\u000bGo\u00195TSj,\u0007%A\u0002nCB,\"A!$\u0011\u0011\u0005e$qRAC\u0003GKAA!%\u0002`\tA\u0002+\u0019:uSRLwN\\3e\u0003B\u0004XM\u001c3P]2LX*\u00199\u0002\u000f5\f\u0007o\u0018\u0013fcR!!q\u0013BO!\u0011\t\u0019J!'\n\t\tm\u0015Q\u0013\u0002\u0005+:LG\u000fC\u0005\u0003 j\t\t\u00111\u0001\u0003\u000e\u0006\u0019\u0001\u0010J\u0019\u0002\t5\f\u0007\u000f\t\u0015\u00047\t\u0015\u0006\u0003BAJ\u0005OKAA!+\u0002\u0016\nAao\u001c7bi&dW-\u0001\u0004ck\u001a4WM]\u000b\u0003\u0005_\u0003\u0002\"!\u001f\u00032\u0006\u0015\u00151U\u0005\u0005\u0005g\u000byFA\u000bQCJ$\u0018\u000e^5p]\u0016$\u0007+Y5s\u0005V4g-\u001a:\u0002\u0015\t,hMZ3s?\u0012*\u0017\u000f\u0006\u0003\u0003\u0018\ne\u0006\"\u0003BP;\u0005\u0005\t\u0019\u0001BX\u0003\u001d\u0011WO\u001a4fe\u0002B3A\bBS\u0003EyF-[:l\u0005f$Xm]*qS2dW\rZ\u0001\u0016?\u0012L7o\u001b\"zi\u0016\u001c8\u000b]5mY\u0016$w\fJ3r)\u0011\u00119J!2\t\u0013\t}\u0005%!AA\u0002\t\u0005\u0015AE0eSN\\')\u001f;fgN\u0003\u0018\u000e\u001c7fI\u0002\n\u0001\u0003Z5tW\nKH/Z:Ta&dG.\u001a3\u0002)}\u0003X-Y6NK6|'/_+tK\u0012\u0014\u0015\u0010^3t\u0003ay\u0006/Z1l\u001b\u0016lwN]=Vg\u0016$')\u001f;fg~#S-\u001d\u000b\u0005\u0005/\u0013\t\u000eC\u0005\u0003 \u0012\n\t\u00111\u0001\u0003\u0002\u0006)r\f]3bW6+Wn\u001c:z+N,GMQ=uKN\u0004\u0013a\u00059fC.lU-\\8ssV\u001bX\r\u001a\"zi\u0016\u001c\u0018!D5t'\",hM\u001a7f'>\u0014H/\u0006\u0002\u0003\\B!\u00111\u0013Bo\u0013\u0011\u0011y.!&\u0003\u000f\t{w\u000e\\3b]\u0006\t\u0012n]*ik\u001a4G.Z*peR|F%Z9\u0015\t\t]%Q\u001d\u0005\n\u0005?C\u0013\u0011!a\u0001\u00057\fa\"[:TQV4g\r\\3T_J$\b\u0005K\u0002*\u0005K\u000bqBZ8sG\u0016\u001c\u0006/\u001b7m\r&dWm]\u000b\u0003\u0005_\u0004bA!=\u0003z\nuXB\u0001Bz\u0015\u0011\u0011)Pa>\u0002\u000f5,H/\u00192mK*!\u0011\u0011MAK\u0013\u0011\u0011YPa=\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM\u001d\t\u0004\u0005\u007f,T\"\u0001\u0001\u0003\u0017M\u0003\u0018\u000e\u001c7fI\u001aKG.Z\n\bk\r\u001511BB\t!\u0011\t\u0019ja\u0002\n\t\r%\u0011Q\u0013\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005M5QB\u0005\u0005\u0007\u001f\t)JA\u0004Qe>$Wo\u0019;\u0011\t\u0005M81C\u0005\u0005\u0007+\u00119A\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0003gS2,WCAB\u000e!\u0011\u0019iba\n\u000e\u0005\r}!\u0002BB\u0011\u0007G\t!![8\u000b\u0005\r\u0015\u0012\u0001\u00026bm\u0006LAa!\u000b\u0004 \t!a)\u001b7f\u0003\u00151\u0017\u000e\\3!\u0003\u001d\u0011Gn\\2l\u0013\u0012,\"a!\r\u0011\t\t%31G\u0005\u0005\u0007k\u0011YEA\u0004CY>\u001c7.\u00133\u0002\u0011\tdwnY6JI\u0002\nAc]3sS\u0006d\u0017N_3s\u0005\u0006$8\r[*ju\u0016\u001cXCAB\u001f!\u0019\t\u0019ja\u0010\u0003\u0002&!1\u0011IAK\u0005\u0015\t%O]1z\u0003U\u0019XM]5bY&TXM\u001d\"bi\u000eD7+\u001b>fg\u0002\nA#\u001a7f[\u0016tGo\u001d)feB\u000b'\u000f^5uS>t\u0017!F3mK6,g\u000e^:QKJ\u0004\u0016M\u001d;ji&|g\u000e\t\u000b\u000b\u0005{\u001cYe!\u0014\u0004P\rE\u0003bBB\f}\u0001\u000711\u0004\u0005\b\u0007[q\u0004\u0019AB\u0019\u0011\u001d\u0019ID\u0010a\u0001\u0007{Aqa!\u0012?\u0001\u0004\u0019i$\u0001\u0003d_BLHC\u0003B\u007f\u0007/\u001aIfa\u0017\u0004^!I1qC \u0011\u0002\u0003\u000711\u0004\u0005\n\u0007[y\u0004\u0013!a\u0001\u0007cA\u0011b!\u000f@!\u0003\u0005\ra!\u0010\t\u0013\r\u0015s\b%AA\u0002\ru\u0012AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0007GRCaa\u0007\u0004f-\u00121q\r\t\u0005\u0007S\u001a\u0019(\u0004\u0002\u0004l)!1QNB8\u0003%)hn\u00195fG.,GM\u0003\u0003\u0004r\u0005U\u0015AC1o]>$\u0018\r^5p]&!1QOB6\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0019YH\u000b\u0003\u00042\r\u0015\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0007\u0003SCa!\u0010\u0004f\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0004\nB!11RBI\u001b\t\u0019iI\u0003\u0003\u0004\u0010\u000e\r\u0012\u0001\u00027b]\u001eLAaa%\u0004\u000e\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u001e\u000em\u0005\"\u0003BP\r\u0006\u0005\t\u0019\u0001B\u001b\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCABQ!\u0019\u0019\u0019k!*\u0002\u001e6\u0011!q_\u0005\u0005\u0007O\u00139P\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002Bn\u0007[C\u0011Ba(I\u0003\u0003\u0005\r!!(\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0007\u0013\u001b\u0019\fC\u0005\u0003 &\u000b\t\u00111\u0001\u00036\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u00036\u0005AAo\\*ue&tw\r\u0006\u0002\u0004\n\u00061Q-];bYN$BAa7\u0004B\"I!q\u0014'\u0002\u0002\u0003\u0007\u0011QT\u0001\u0011M>\u00148-Z*qS2dg)\u001b7fg\u0002\nqB]3bI&tw-\u0013;fe\u0006$xN]\u000b\u0003\u0007\u0013\u0004BAa@\u0002(\t\t2\u000b]5mY\u0006\u0014G.Z%uKJ\fGo\u001c:\u0014\r\u0005\u001d2QABh!\u0019\t\u0019p!5\u0004T&!1q\u0015B\u0004!!\t\u0019j!6\u0004Z\u0006\r\u0016\u0002BBl\u0003+\u0013a\u0001V;qY\u0016\u0014\u0004\u0003CAJ\u0007+\u0014)$!\"\u0002\u0011U\u00048\u000f\u001e:fC6,\"aa4\u0002\u0019U\u00048\u000f\u001e:fC6|F%Z9\u0015\t\t]51\u001d\u0005\u000b\u0005?\u000bY#!AA\u0002\r=\u0017!C;qgR\u0014X-Y7!)\u0011\u0019Im!;\t\u0011\rm\u0017q\u0006a\u0001\u0007\u001f\f!b\u0015)J\u00192{FjT\"L+\t\u0019y\u000f\u0005\u0003\u0004\f\u000eE\u0018\u0002BBz\u0007\u001b\u0013aa\u00142kK\u000e$\u0018aC*Q\u00132cu\fT(D\u0017\u0002\nAB\\3yiV\u00038\u000f\u001e:fC6\f\u0001C\\3yiV\u00038\u000f\u001e:fC6|F%Z9\u0015\t\t]5Q \u0005\u000b\u0005?\u000b9$!AA\u0002\r=\u0017!\u00048fqR,\u0006o\u001d;sK\u0006l\u0007%A\u0002dkJ,\"aa5\u0002\u000f\r,(o\u0018\u0013fcR!!q\u0013C\u0005\u0011)\u0011y*!\u0010\u0002\u0002\u0003\u000711[\u0001\u0005GV\u0014\b%\u0001\u0006iCN\u001c\u0006/\u001b7mK\u0012\fa\u0002[1t'BLG\u000e\\3e?\u0012*\u0017\u000f\u0006\u0003\u0003\u0018\u0012M\u0001B\u0003BP\u0003\u0007\n\t\u00111\u0001\u0003\\\u0006Y\u0001.Y:Ta&dG.\u001a3!\u0003\u0015\u0019\b/\u001b7m)\t\u0011Y.\u0001\u0005sK\u0006$g*\u001a=u)\t\u0019\u0019.A\u0004iCNtU\r\u001f;\u0002\t9,\u0007\u0010^\u0001\u0014e\u0016\fG-\u001b8h\u0013R,'/\u0019;pe~#S-\u001d\u000b\u0005\u0005/#9\u0003C\u0005\u0003 6\n\t\u00111\u0001\u0004J\u0006\u0001\"/Z1eS:<\u0017\n^3sCR|'\u000f\t\u0015\u0004]\t\u0015\u0016A\u00059beRLG/[8o\u0007\",7m[:v[N,\"\u0001\"\r\u0011\r\u0005M5q\bC\u001a!\u0011!)\u0004\"\u0010\u000e\u0005\u0011]\"\u0002\u0002C\u001d\tw\t1A_5q\u0015\u0011\t)ga\t\n\t\u0011}Bq\u0007\u0002\t\u0007\",7m[:v[\u0006\u0019\u0002/\u0019:uSRLwN\\\"iK\u000e\\7/^7tA\u0005aq-\u001a;DQ\u0016\u001c7n];ng\u0006i1.Z=D_6\u0004\u0018M]1u_J,\"\u0001\"\u0013\u0011\r\u0011-CQJAC\u001b\t!Y$\u0003\u0003\u0005P\u0011m\"AC\"p[B\f'/\u0019;pe\u0006q1.Z=D_6\u0004\u0018M]1u_J\u0004\u0013AC2p[B\f'/\u0019;peV\u0011Aq\u000b\t\u0007\u0003'\u000b\u0019\u000e\"\u0013\u0002\u0017M\u0003\u0018\u000e\u001c7fI\u001aKG.\u001a\t\u0004\u0005\u007ft5#\u0002(\u0005`\u0011-\u0004C\u0004C1\tO\u001aYb!\r\u0004>\ru\"Q`\u0007\u0003\tGRA\u0001\"\u001a\u0002\u0016\u00069!/\u001e8uS6,\u0017\u0002\u0002C5\tG\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011\u0019i\u0002\"\u001c\n\t\rU1q\u0004\u000b\u0003\t7\nQ!\u00199qYf$\"B!@\u0005v\u0011]D\u0011\u0010C>\u0011\u001d\u00199\"\u0015a\u0001\u00077Aqa!\fR\u0001\u0004\u0019\t\u0004C\u0004\u0004:E\u0003\ra!\u0010\t\u000f\r\u0015\u0013\u000b1\u0001\u0004>\u00059QO\\1qa2LH\u0003\u0002CA\t\u0013\u0003b!a%\u0002T\u0012\r\u0005\u0003DAJ\t\u000b\u001bYb!\r\u0004>\ru\u0012\u0002\u0002CD\u0003+\u0013a\u0001V;qY\u0016$\u0004\"\u0003CF%\u0006\u0005\t\u0019\u0001B\u007f\u0003\rAH\u0005M\u0001\u0007gBLG\u000e\\:\u0002\u000fM\u0004\u0018\u000e\u001c7tA\u0005Ia.^7Ta&dGn]\u0001\nS:\u001cXM\u001d;BY2$BAa&\u0005\u0018\"9A\u0011\u0014,A\u0002\u0011m\u0015a\u0002:fG>\u0014Hm\u001d\t\u0007\u0003g\u001c\t\u000e\"(\u0011\u0011\u0005MEqTAC\u0003;LA\u0001\")\u0002\u0016\nA\u0001K]8ek\u000e$('\u0001\u000bnCf\u0014Wm\u00159jY2\u001cu\u000e\u001c7fGRLwN\u001c\u000b\u0005\u0005/#9\u000bC\u0004\u0005*^\u0003\rAa7\u0002\u0011U\u001c\u0018N\\4NCB$BAa&\u0005.\"9\u0011\u0011\r-A\u0002\u0005}\u0014A\u00034pe\u000e,7\u000b]5mY\u0006I2\u000f]5mY6+Wn\u001c:z\u0013R,'/\u0019;peR{G)[:l)\u0011\u0011i\u0010\".\t\u000f\u0011]&\f1\u0001\u0005:\u0006\u0001\u0012N\\'f[>\u0014\u00180\u0013;fe\u0006$xN\u001d\t\t\u0003s\"Y,!\"\u0002$&!AQXA0\u0005m9&/\u001b;bE2,\u0007+\u0019:uSRLwN\\3e\u0013R,'/\u0019;pe\u0006)Q.\u001a:hKR1A1\u0019Cf\t'\u0004b!a=\u0004R\u0012\u0015\u0007\u0003CAJ\u0007+\u0014)\u0004b2\u0011\r\u0005M8\u0011\u001bCe!!\t\u0019\nb(\u0002\u0006\u0006\r\u0006b\u0002CG7\u0002\u0007AQ\u001a\t\u0007\u0003g$yM!@\n\t\u0011E'q\u0001\u0002\u0004'\u0016\f\bb\u0002Ck7\u0002\u00071qZ\u0001\tS:lU-\\8ss\u0006IQ.\u001a:hKN{'\u000f\u001e\u000b\u0007\t\u000f$Y\u000e\"9\t\u000f\u0011uG\f1\u0001\u0005`\u0006I\u0011\u000e^3sCR|'o\u001d\t\u0007\u0003g$y\rb2\t\u000f\u0011MC\f1\u0001\u0005J\u0005!R.\u001a:hK^KG\u000f[!hOJ,w-\u0019;j_:$\"\u0002b2\u0005h\u0012%H1\u001fC{\u0011\u001d!i.\u0018a\u0001\t?Dq\u0001b;^\u0001\u0004!i/\u0001\bnKJ<WmQ8nE&tWM]:\u0011\u0015\u0005MEq^AR\u0003G\u000b\u0019+\u0003\u0003\u0005r\u0006U%!\u0003$v]\u000e$\u0018n\u001c83\u0011\u001d!\u0019&\u0018a\u0001\t\u0013Bq\u0001b>^\u0001\u0004\u0011Y.\u0001\u0006u_R\fGn\u0014:eKJ\u00141b\u00159jY2\u0014V-\u00193feN\u0019al!\u0002\u0015\t\u0011}X\u0011\u0001\t\u0004\u0005\u007ft\u0006b\u0002C\fA\u0002\u0007!Q`\u0001\rE\u0006$8\r[(gMN,Go]\u0001\u000eE\u0006$8\r[(gMN,Go\u001d\u0011\u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u0001\u0010a\u0006\u0014H/\u001b;j_:LEm\u0018\u0013fcR!!qSC\u0007\u0011%\u0011y\nZA\u0001\u0002\u0004\u0011)$\u0001\u0007qCJ$\u0018\u000e^5p]&#\u0007%\u0001\tj]\u0012,\u00070\u00138QCJ$\u0018\u000e^5p]\u0006!\u0012N\u001c3fq&s\u0007+\u0019:uSRLwN\\0%KF$BAa&\u0006\u0018!I!qT4\u0002\u0002\u0003\u0007!\u0011Q\u0001\u0012S:$W\r_%o!\u0006\u0014H/\u001b;j_:\u0004\u0013a\u00022bi\u000eD\u0017\nZ\u0001\fE\u0006$8\r[%e?\u0012*\u0017\u000f\u0006\u0003\u0003\u0018\u0016\u0005\u0002\"\u0003BPU\u0006\u0005\t\u0019\u0001B\u001b\u0003!\u0011\u0017\r^2i\u0013\u0012\u0004\u0013\u0001D5oI\u0016D\u0018J\u001c\"bi\u000eD\u0017\u0001E5oI\u0016D\u0018J\u001c\"bi\u000eDw\fJ3r)\u0011\u00119*b\u000b\t\u0013\t}U.!AA\u0002\tU\u0012!D5oI\u0016D\u0018J\u001c\"bi\u000eD\u0007%A\bmCN$\b+\u0019:uSRLwN\\%e\u0003Ma\u0017m\u001d;QCJ$\u0018\u000e^5p]&#w\fJ3r)\u0011\u00119*\"\u000e\t\u0013\t}\u0005/!AA\u0002\tU\u0012\u0001\u00057bgR\u0004\u0016M\u001d;ji&|g.\u00133!\u0003)1\u0017\u000e\\3TiJ,\u0017-\\\u000b\u0003\u000b{\u0001Ba!\b\u0006@%!Q\u0011IB\u0010\u0005=1\u0015\u000e\\3J]B,Ho\u0015;sK\u0006l\u0017A\u00044jY\u0016\u001cFO]3b[~#S-\u001d\u000b\u0005\u0005/+9\u0005C\u0005\u0003 N\f\t\u00111\u0001\u0006>\u0005Ya-\u001b7f'R\u0014X-Y7!\u0003E!Wm]3sS\u0006d\u0017N_3TiJ,\u0017-\\\u000b\u0003\u000b\u001f\u0002BA!\u0004\u0006R%!Q1\u000bB\b\u0005U!Um]3sS\u0006d\u0017N_1uS>t7\u000b\u001e:fC6\fQ\u0003Z3tKJL\u0017\r\\5{KN#(/Z1n?\u0012*\u0017\u000f\u0006\u0003\u0003\u0018\u0016e\u0003\"\u0003BPm\u0006\u0005\t\u0019AC(\u0003I!Wm]3sS\u0006d\u0017N_3TiJ,\u0017-\u001c\u0011\u0002\u00119,\u0007\u0010^%uK6,\"!\"\u0019\u0011\u0011\u0005M5Q[AC\u0003G\u000bAB\\3yi&#X-\\0%KF$BAa&\u0006h!I!qT=\u0002\u0002\u0003\u0007Q\u0011M\u0001\n]\u0016DH/\u0013;f[\u0002\n\u0001BZ5oSNDW\rZ\u0001\rM&t\u0017n\u001d5fI~#S-\u001d\u000b\u0005\u0005/+\t\bC\u0005\u0003 r\f\t\u00111\u0001\u0003\\\u0006Ia-\u001b8jg\",G\rI\u0001\u0010]\u0016DHOQ1uG\"\u001cFO]3b[R\u0011QqJ\u0001\u0014g.L\u0007\u000fV8OKb$\b+\u0019:uSRLwN\u001c\u000b\u0003\u0005/\u000bAB]3bI:+\u0007\u0010^%uK6$\"!\"\u0019\u0002'9,\u0007\u0010\u001e)beRLG/[8o)>\u0014V-\u00193\u0002/9,\u0007\u0010\u001e)beRLG/[8o)>\u0014V-\u00193`I\u0015\fH\u0003\u0002BL\u000b\u000fC!Ba(\u0002\u0006\u0005\u0005\t\u0019\u0001B\u001b\u0003QqW\r\u001f;QCJ$\u0018\u000e^5p]R{'+Z1eA\u0005\t\"/Z1e\u001d\u0016DH\u000fU1si&$\u0018n\u001c8\u0015\u0005\u0011\u001d\u0017aB2mK\u0006tW\u000f]\u0001\u0014I\u0016\u001cHO];di&4X-\u0013;fe\u0006$xN\u001d\u000b\u0005\u0007\u001f,)\n\u0003\u0005\u0006\u0018\u00065\u0001\u0019ABh\u00039iW-\\8ss&#XM]1u_J\f1\u0003]1si&$\u0018n\u001c8fI&#XM]1u_J,\"\u0001b1\u0002\u0011%$XM]1u_J,\"\u0001b2\u00023%t7/\u001a:u\u00032d\u0017I\u001c3Va\u0012\fG/Z'fiJL7m\u001d\u000b\u0005\t\u000f,)\u000b\u0003\u0005\u0005\u001a\u0006M\u0001\u0019\u0001CN\u0003e9(/\u001b;f!\u0006\u0014H/\u001b;j_:,G-T1q\u001fV$\b/\u001e;\u0015\u0015\t]U1VCX\u000bg+\u0019\r\u0003\u0005\u0006.\u0006U\u0001\u0019\u0001B\u001b\u0003%\u0019\b.\u001e4gY\u0016LE\r\u0003\u0005\u00062\u0006U\u0001\u0019\u0001BA\u0003\u0015i\u0017\r]%e\u0011!)),!\u0006A\u0002\u0015]\u0016aD7ba>+H\u000f];u/JLG/\u001a:\u0011\t\u0015eVqX\u0007\u0003\u000bwSA!\"0\u0002>\u0006\u0019\u0011\r]5\n\t\u0015\u0005W1\u0018\u0002\u0017'\",hM\u001a7f\u001b\u0006\u0004x*\u001e;qkR<&/\u001b;fe\"AQQYA\u000b\u0001\u0004)9-\u0001\u0007xe&$X-T3ue&\u001c7\u000f\u0005\u0003\u0006J\u0016-WBAA_\u0013\u0011)i-!0\u00037MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018nY:SKB|'\u000f^3s\u0003\u0011\u0019Ho\u001c9\u0002!\u001d\u0014x.\u001e9CsB\u000b'\u000f^5uS>tG\u0003\u0002Cb\u000b+D\u0001\"b6\u0002\u001a\u0001\u00071qZ\u0001\u0005I\u0006$\u0018M\u0001\u000bJi\u0016\u0014\u0018\r^8s\r>\u0014\b+\u0019:uSRLwN\\\n\u0007\u00037\u0019)\u0001b2\u0011\r\r\rVq\\Bj\u0013\u0011)\tOa>\u0003!\t+hMZ3sK\u0012LE/\u001a:bi>\u0014HCBCs\u000bO,I\u000f\u0005\u0003\u0003\u0000\u0006m\u0001\u0002CC\u0004\u0003C\u0001\rA!\u000e\t\u0011\u0015]\u0017\u0011\u0005a\u0001\u000b;$\"\u0001\"3\u0002\u001d\u0015CH/\u001a:oC2\u001cvN\u001d;feB!\u0011\u0011PA)'\u0011\t\tf!\u0002\u0015\u0005\u0015=\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0005\u0006z\u001a\raQ\u0001D\u0004+\t)YP\u000b\u0003\u0006~\u000e\u0015d\u0002BAJ\u000b\u007fLAA\"\u0001\u0002\u0016\u0006!aj\u001c8f\t!\tY)!\u0016C\u0002\u0005=E\u0001CAq\u0003+\u0012\r!a$\u0005\u0011\u0005\u001d\u0016Q\u000bb\u0001\u0003\u001f\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aT\u0003\u0003D\u0007\r#1\u0019B\"\u0006\u0016\u0005\u0019=!\u0006BAs\u0007K\"\u0001\"a#\u0002X\t\u0007\u0011q\u0012\u0003\t\u0003C\f9F1\u0001\u0002\u0010\u0012A\u0011qUA,\u0005\u0004\ty)A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u000b\t\u000bs4YB\"\b\u0007 \u0011A\u00111RA-\u0005\u0004\ty\t\u0002\u0005\u0002b\u0006e#\u0019AAH\t!\t9+!\u0017C\u0002\u0005=\u0015a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0005\u0007&\u0019%b1\u0006D\u0017+\t19C\u000b\u0003\u0003\f\r\u0015D\u0001CAF\u00037\u0012\r!a$\u0005\u0011\u0005\u0005\u00181\fb\u0001\u0003\u001f#\u0001\"a*\u0002\\\t\u0007\u0011q\u0012"
)
public class ExternalSorter extends Spillable implements ShuffleChecksumSupport {
   private volatile SpilledFile$ SpilledFile$module;
   private final TaskContext context;
   private final Option aggregator;
   private final Option ordering;
   private final SparkConf conf;
   private final int org$apache$spark$util$collection$ExternalSorter$$numPartitions;
   private final Partitioner actualPartitioner;
   private final BlockManager blockManager;
   private final DiskBlockManager diskBlockManager;
   private final SerializerManager org$apache$spark$util$collection$ExternalSorter$$serializerManager;
   private final SerializerInstance org$apache$spark$util$collection$ExternalSorter$$serInstance;
   private final int fileBufferSize;
   private final long org$apache$spark$util$collection$ExternalSorter$$serializerBatchSize;
   private volatile PartitionedAppendOnlyMap map;
   private volatile PartitionedPairBuffer buffer;
   private long _diskBytesSpilled;
   private long _peakMemoryUsedBytes;
   private volatile boolean isShuffleSort;
   private final ArrayBuffer org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles;
   private volatile SpillableIterator readingIterator;
   private final Checksum[] partitionChecksums;
   private final Comparator keyComparator;
   private final ArrayBuffer spills;

   public static Serializer $lessinit$greater$default$5() {
      return ExternalSorter$.MODULE$.$lessinit$greater$default$5();
   }

   public static None $lessinit$greater$default$4() {
      return ExternalSorter$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return ExternalSorter$.MODULE$.$lessinit$greater$default$3();
   }

   public static None $lessinit$greater$default$2() {
      return ExternalSorter$.MODULE$.$lessinit$greater$default$2();
   }

   public Checksum[] createPartitionChecksums(final int numPartitions, final SparkConf conf) {
      return ShuffleChecksumSupport.super.createPartitionChecksums(numPartitions, conf);
   }

   public long[] getChecksumValues(final Checksum[] partitionChecksums) {
      return ShuffleChecksumSupport.super.getChecksumValues(partitionChecksums);
   }

   private SpilledFile$ SpilledFile() {
      if (this.SpilledFile$module == null) {
         this.SpilledFile$lzycompute$1();
      }

      return this.SpilledFile$module;
   }

   private SparkConf conf() {
      return this.conf;
   }

   public int org$apache$spark$util$collection$ExternalSorter$$numPartitions() {
      return this.org$apache$spark$util$collection$ExternalSorter$$numPartitions;
   }

   private Partitioner actualPartitioner() {
      return this.actualPartitioner;
   }

   private BlockManager blockManager() {
      return this.blockManager;
   }

   private DiskBlockManager diskBlockManager() {
      return this.diskBlockManager;
   }

   public SerializerManager org$apache$spark$util$collection$ExternalSorter$$serializerManager() {
      return this.org$apache$spark$util$collection$ExternalSorter$$serializerManager;
   }

   public SerializerInstance org$apache$spark$util$collection$ExternalSorter$$serInstance() {
      return this.org$apache$spark$util$collection$ExternalSorter$$serInstance;
   }

   private int fileBufferSize() {
      return this.fileBufferSize;
   }

   public long org$apache$spark$util$collection$ExternalSorter$$serializerBatchSize() {
      return this.org$apache$spark$util$collection$ExternalSorter$$serializerBatchSize;
   }

   private PartitionedAppendOnlyMap map() {
      return this.map;
   }

   private void map_$eq(final PartitionedAppendOnlyMap x$1) {
      this.map = x$1;
   }

   private PartitionedPairBuffer buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final PartitionedPairBuffer x$1) {
      this.buffer = x$1;
   }

   private long _diskBytesSpilled() {
      return this._diskBytesSpilled;
   }

   private void _diskBytesSpilled_$eq(final long x$1) {
      this._diskBytesSpilled = x$1;
   }

   public long diskBytesSpilled() {
      return this._diskBytesSpilled();
   }

   private long _peakMemoryUsedBytes() {
      return this._peakMemoryUsedBytes;
   }

   private void _peakMemoryUsedBytes_$eq(final long x$1) {
      this._peakMemoryUsedBytes = x$1;
   }

   public long peakMemoryUsedBytes() {
      return this._peakMemoryUsedBytes();
   }

   private boolean isShuffleSort() {
      return this.isShuffleSort;
   }

   private void isShuffleSort_$eq(final boolean x$1) {
      this.isShuffleSort = x$1;
   }

   public ArrayBuffer org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles() {
      return this.org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles;
   }

   private SpillableIterator readingIterator() {
      return this.readingIterator;
   }

   private void readingIterator_$eq(final SpillableIterator x$1) {
      this.readingIterator = x$1;
   }

   private Checksum[] partitionChecksums() {
      return this.partitionChecksums;
   }

   public long[] getChecksums() {
      return this.getChecksumValues(this.partitionChecksums());
   }

   private Comparator keyComparator() {
      return this.keyComparator;
   }

   private Option comparator() {
      return (Option)(!this.ordering.isDefined() && !this.aggregator.isDefined() ? .MODULE$ : new Some(this.keyComparator()));
   }

   private ArrayBuffer spills() {
      return this.spills;
   }

   public int numSpills() {
      return this.spills().size();
   }

   public void insertAll(final Iterator records) {
      boolean shouldCombine = this.aggregator.isDefined();
      if (shouldCombine) {
         Function2 mergeValue = ((Aggregator)this.aggregator.get()).mergeValue();
         Function1 createCombiner = ((Aggregator)this.aggregator.get()).createCombiner();
         ObjectRef kv = ObjectRef.create((Object)null);
         Function2 update = (hadValue, oldValue) -> $anonfun$insertAll$1(mergeValue, kv, createCombiner, BoxesRunTime.unboxToBoolean(hadValue), oldValue);

         while(records.hasNext()) {
            this.addElementsRead();
            kv.elem = (Product2)records.next();
            this.map().changeValue(new Tuple2(BoxesRunTime.boxToInteger(this.actualPartitioner().getPartition(((Product2)kv.elem)._1())), ((Product2)kv.elem)._1()), update);
            this.maybeSpillCollection(true);
         }

      } else {
         while(records.hasNext()) {
            this.addElementsRead();
            Product2 kv = (Product2)records.next();
            this.buffer().insert(this.actualPartitioner().getPartition(kv._1()), kv._1(), kv._2());
            this.maybeSpillCollection(false);
         }

      }
   }

   private void maybeSpillCollection(final boolean usingMap) {
      long estimatedSize = 0L;
      if (usingMap) {
         estimatedSize = this.map().estimateSize();
         if (this.maybeSpill(this.map(), estimatedSize)) {
            this.map_$eq(new PartitionedAppendOnlyMap());
         }
      } else {
         estimatedSize = this.buffer().estimateSize();
         if (this.maybeSpill(this.buffer(), estimatedSize)) {
            this.buffer_$eq(new PartitionedPairBuffer(PartitionedPairBuffer$.MODULE$.$lessinit$greater$default$1()));
         }
      }

      if (estimatedSize > this._peakMemoryUsedBytes()) {
         this._peakMemoryUsedBytes_$eq(estimatedSize);
      }
   }

   public void spill(final WritablePartitionedPairCollection collection) {
      WritablePartitionedIterator inMemoryIterator = collection.destructiveSortedWritablePartitionedIterator(this.comparator());
      SpilledFile spillFile = this.org$apache$spark$util$collection$ExternalSorter$$spillMemoryIteratorToDisk(inMemoryIterator);
      this.spills().$plus$eq(spillFile);
   }

   public boolean forceSpill() {
      if (this.isShuffleSort()) {
         return false;
      } else {
         scala.Predef..MODULE$.assert(this.readingIterator() != null);
         boolean isSpilled = this.readingIterator().spill();
         if (isSpilled) {
            this.map_$eq((PartitionedAppendOnlyMap)null);
            this.buffer_$eq((PartitionedPairBuffer)null);
         }

         return isSpilled;
      }
   }

   public SpilledFile org$apache$spark$util$collection$ExternalSorter$$spillMemoryIteratorToDisk(final WritablePartitionedIterator inMemoryIterator) {
      Tuple2 var4 = this.diskBlockManager().createTempShuffleBlock();
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         TempShuffleBlockId blockId = (TempShuffleBlockId)var4._1();
         File file = (File)var4._2();
         Tuple2 var3 = new Tuple2(blockId, file);
         TempShuffleBlockId blockId = (TempShuffleBlockId)var3._1();
         File file = (File)var3._2();
         LongRef objectsWritten = LongRef.create(0L);
         ShuffleWriteMetrics spillMetrics = new ShuffleWriteMetrics();
         DiskBlockObjectWriter writer = this.blockManager().getDiskWriter(blockId, file, this.org$apache$spark$util$collection$ExternalSorter$$serInstance(), this.fileBufferSize(), spillMetrics);
         ArrayBuffer batchSizes = new ArrayBuffer();
         long[] elementsPerPartition = new long[this.org$apache$spark$util$collection$ExternalSorter$$numPartitions()];
         boolean success = false;

         try {
            while(inMemoryIterator.hasNext()) {
               int partitionId = inMemoryIterator.nextPartition();
               scala.Predef..MODULE$.require(partitionId >= 0 && partitionId < this.org$apache$spark$util$collection$ExternalSorter$$numPartitions(), () -> "partition Id: " + partitionId + " should be in the range [0, " + this.org$apache$spark$util$collection$ExternalSorter$$numPartitions() + ")");
               inMemoryIterator.writeNext(writer);
               int var10002 = elementsPerPartition[partitionId]++;
               ++objectsWritten.elem;
               if (objectsWritten.elem == this.org$apache$spark$util$collection$ExternalSorter$$serializerBatchSize()) {
                  this.flush$1(writer, batchSizes, objectsWritten);
               }
            }

            if (objectsWritten.elem > 0L) {
               this.flush$1(writer, batchSizes, objectsWritten);
               writer.close();
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               writer.revertPartialWritesAndClose();
            }

            success = true;
         } finally {
            if (!success) {
               writer.closeAndDelete();
            }

         }

         return new SpilledFile(file, blockId, (long[])batchSizes.toArray(scala.reflect.ClassTag..MODULE$.Long()), elementsPerPartition);
      }
   }

   private Iterator merge(final Seq spills, final Iterator inMemory) {
      Seq readers = (Seq)spills.map((x$3) -> this.new SpillReader(x$3));
      BufferedIterator inMemBuffered = inMemory.buffered();
      return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.org$apache$spark$util$collection$ExternalSorter$$numPartitions()).iterator().map((p) -> $anonfun$merge$2(this, inMemBuffered, readers, BoxesRunTime.unboxToInt(p)));
   }

   public Iterator org$apache$spark$util$collection$ExternalSorter$$mergeSort(final Seq iterators, final Comparator comparator) {
      Seq bufferedIters = (Seq)((IterableOps)iterators.filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$mergeSort$1(x$5)))).map((x$6) -> x$6.buffered());
      PriorityQueue heap = new PriorityQueue(new Ordering(comparator) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final ExternalSorter $outer;
         private final Comparator comparator$1;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return Ordering.lteq$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return Ordering.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Ordering.lt$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Ordering.gt$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public final int compare(final BufferedIterator x, final BufferedIterator y) {
            return ExternalSorter.org$apache$spark$util$collection$ExternalSorter$$$anonfun$mergeSort$3(x, y, this.comparator$1);
         }

         public {
            if (ExternalSorter.this == null) {
               throw null;
            } else {
               this.$outer = ExternalSorter.this;
               this.comparator$1 = comparator$1;
               PartialOrdering.$init$(this);
               Ordering.$init$(this);
            }
         }
      });
      heap.enqueue(bufferedIters);
      return new Iterator(heap) {
         private final PriorityQueue heap$1;

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
            return this.heap$1.nonEmpty();
         }

         public Product2 next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               BufferedIterator firstBuf = (BufferedIterator)this.heap$1.dequeue();
               Product2 firstPair = (Product2)firstBuf.next();
               if (firstBuf.hasNext()) {
                  this.heap$1.enqueue(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new BufferedIterator[]{firstBuf})));
               }

               return firstPair;
            }
         }

         public {
            this.heap$1 = heap$1;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
         }
      };
   }

   private Iterator mergeWithAggregation(final Seq iterators, final Function2 mergeCombiners, final Comparator comparator, final boolean totalOrder) {
      if (!totalOrder) {
         Iterator it = new Iterator(iterators, comparator, mergeCombiners) {
            private final BufferedIterator sorted;
            private final ArrayBuffer keys;
            private final ArrayBuffer combiners;
            private final Comparator comparator$2;
            private final Function2 mergeCombiners$1;

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

            public BufferedIterator sorted() {
               return this.sorted;
            }

            public ArrayBuffer keys() {
               return this.keys;
            }

            public ArrayBuffer combiners() {
               return this.combiners;
            }

            public boolean hasNext() {
               return this.sorted().hasNext();
            }

            public Iterator next() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  this.keys().clear();
                  this.combiners().clear();
                  Product2 firstPair = (Product2)this.sorted().next();
                  this.keys().$plus$eq(firstPair._1());
                  this.combiners().$plus$eq(firstPair._2());
                  Object key = firstPair._1();

                  while(this.sorted().hasNext() && this.comparator$2.compare(((Product2)this.sorted().head())._1(), key) == 0) {
                     Product2 pair = (Product2)this.sorted().next();
                     int i = 0;

                     boolean foundKey;
                     for(foundKey = false; i < this.keys().size() && !foundKey; ++i) {
                        if (BoxesRunTime.equals(this.keys().apply(i), pair._1())) {
                           this.combiners().update(i, this.mergeCombiners$1.apply(this.combiners().apply(i), pair._2()));
                           foundKey = true;
                        }
                     }

                     if (!foundKey) {
                        this.keys().$plus$eq(pair._1());
                        this.combiners().$plus$eq(pair._2());
                     } else {
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }
                  }

                  return this.keys().iterator().zip(this.combiners().iterator());
               }
            }

            public {
               this.comparator$2 = comparator$2;
               this.mergeCombiners$1 = mergeCombiners$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.sorted = ExternalSorter.this.org$apache$spark$util$collection$ExternalSorter$$mergeSort(iterators$1, comparator$2).buffered();
               this.keys = new ArrayBuffer();
               this.combiners = new ArrayBuffer();
            }
         };
         return it.flatten(scala.Predef..MODULE$.$conforms());
      } else {
         return new Iterator(iterators, comparator, mergeCombiners) {
            private final BufferedIterator sorted;
            private final Function2 mergeCombiners$1;

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

            private BufferedIterator sorted() {
               return this.sorted;
            }

            public boolean hasNext() {
               return this.sorted().hasNext();
            }

            public Product2 next() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  Product2 elem = (Product2)this.sorted().next();
                  Object k = elem._1();

                  Object c;
                  Product2 pair;
                  for(c = elem._2(); this.sorted().hasNext() && BoxesRunTime.equals(((Product2)this.sorted().head())._1(), k); c = this.mergeCombiners$1.apply(c, pair._2())) {
                     pair = (Product2)this.sorted().next();
                  }

                  return new Tuple2(k, c);
               }
            }

            public {
               this.mergeCombiners$1 = mergeCombiners$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.sorted = ExternalSorter.this.org$apache$spark$util$collection$ExternalSorter$$mergeSort(iterators$1, comparator$2).buffered();
            }
         };
      }
   }

   public Iterator destructiveIterator(final Iterator memoryIterator) {
      if (this.isShuffleSort()) {
         return memoryIterator;
      } else {
         this.readingIterator_$eq(new SpillableIterator(memoryIterator));
         return this.readingIterator();
      }
   }

   public Iterator partitionedIterator() {
      boolean usingMap = this.aggregator.isDefined();
      WritablePartitionedPairCollection collection = (WritablePartitionedPairCollection)(usingMap ? this.map() : this.buffer());
      if (this.spills().isEmpty()) {
         return this.ordering.isEmpty() ? this.groupByPartition(this.destructiveIterator(collection.partitionedDestructiveSortedIterator(.MODULE$))) : this.groupByPartition(this.destructiveIterator(collection.partitionedDestructiveSortedIterator(new Some(this.keyComparator()))));
      } else {
         return this.merge(this.spills().toSeq(), this.destructiveIterator(collection.partitionedDestructiveSortedIterator(this.comparator())));
      }
   }

   public Iterator iterator() {
      this.isShuffleSort_$eq(false);
      return this.partitionedIterator().flatMap((pair) -> (Iterator)pair._2());
   }

   public Iterator insertAllAndUpdateMetrics(final Iterator records) {
      this.insertAll(records);
      this.context.taskMetrics().incMemoryBytesSpilled(this.memoryBytesSpilled());
      this.context.taskMetrics().incDiskBytesSpilled(this.diskBytesSpilled());
      this.context.taskMetrics().incPeakExecutionMemory(this.peakMemoryUsedBytes());
      this.context.addTaskCompletionListener((Function1)((x$9) -> {
         $anonfun$insertAllAndUpdateMetrics$1(this, x$9);
         return BoxedUnit.UNIT;
      }));
      return CompletionIterator$.MODULE$.apply(this.iterator(), (JFunction0.mcV.sp)() -> this.stop());
   }

   public void writePartitionedMapOutput(final int shuffleId, final long mapId, final ShuffleMapOutputWriter mapOutputWriter, final ShuffleWriteMetricsReporter writeMetrics) {
      if (this.spills().isEmpty()) {
         SizeTracker collection = (SizeTracker)(this.aggregator.isDefined() ? this.map() : this.buffer());
         WritablePartitionedIterator it = ((WritablePartitionedPairCollection)collection).destructiveSortedWritablePartitionedIterator(this.comparator());

         while(it.hasNext()) {
            int partitionId = it.nextPartition();
            ObjectRef partitionWriter = ObjectRef.create((Object)null);
            ObjectRef partitionPairsWriter = ObjectRef.create((Object)null);
            org.apache.spark.util.Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
               partitionWriter.elem = mapOutputWriter.getPartitionWriter(partitionId);
               ShuffleBlockId blockId = new ShuffleBlockId(shuffleId, mapId, partitionId);
               partitionPairsWriter.elem = new ShufflePartitionPairsWriter((ShufflePartitionWriter)partitionWriter.elem, this.org$apache$spark$util$collection$ExternalSorter$$serializerManager(), this.org$apache$spark$util$collection$ExternalSorter$$serInstance(), blockId, writeMetrics, scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.partitionChecksums())) ? this.partitionChecksums()[partitionId] : null);

               while(it.hasNext() && it.nextPartition() == partitionId) {
                  it.writeNext((ShufflePartitionPairsWriter)partitionPairsWriter.elem);
               }

            }, (JFunction0.mcV.sp)() -> {
               if ((ShufflePartitionPairsWriter)partitionPairsWriter.elem != null) {
                  ((ShufflePartitionPairsWriter)partitionPairsWriter.elem).close();
               }
            });
         }
      } else {
         this.partitionedIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$writePartitionedMapOutput$3(check$ifrefutable$1))).foreach((x$10) -> {
            $anonfun$writePartitionedMapOutput$4(this, shuffleId, mapId, mapOutputWriter, writeMetrics, x$10);
            return BoxedUnit.UNIT;
         });
      }

      this.context.taskMetrics().incMemoryBytesSpilled(this.memoryBytesSpilled());
      this.context.taskMetrics().incDiskBytesSpilled(this.diskBytesSpilled());
      this.context.taskMetrics().incPeakExecutionMemory(this.peakMemoryUsedBytes());
   }

   public void stop() {
      this.spills().foreach((s) -> BoxesRunTime.boxToBoolean($anonfun$stop$1(s)));
      this.spills().clear();
      this.org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles().foreach((s) -> BoxesRunTime.boxToBoolean($anonfun$stop$2(s)));
      this.org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles().clear();
      if (this.map() != null || this.buffer() != null || this.readingIterator() != null) {
         this.map_$eq((PartitionedAppendOnlyMap)null);
         this.buffer_$eq((PartitionedPairBuffer)null);
         this.readingIterator_$eq((SpillableIterator)null);
         this.releaseMemory();
      }
   }

   private Iterator groupByPartition(final Iterator data) {
      BufferedIterator buffered = data.buffered();
      return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.org$apache$spark$util$collection$ExternalSorter$$numPartitions()).iterator().map((p) -> $anonfun$groupByPartition$1(this, buffered, BoxesRunTime.unboxToInt(p)));
   }

   private final void SpilledFile$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SpilledFile$module == null) {
            this.SpilledFile$module = new SpilledFile$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final int $anonfun$numPartitions$1(final Partitioner x$1) {
      return x$1.numPartitions();
   }

   // $FF: synthetic method
   public static final Object $anonfun$insertAll$1(final Function2 mergeValue$1, final ObjectRef kv$1, final Function1 createCombiner$1, final boolean hadValue, final Object oldValue) {
      return hadValue ? mergeValue$1.apply(oldValue, ((Product2)kv$1.elem)._2()) : createCombiner$1.apply(((Product2)kv$1.elem)._2());
   }

   private final void flush$1(final DiskBlockObjectWriter writer$1, final ArrayBuffer batchSizes$1, final LongRef objectsWritten$1) {
      FileSegment segment = writer$1.commitAndGet();
      batchSizes$1.$plus$eq(BoxesRunTime.boxToLong(segment.length()));
      this._diskBytesSpilled_$eq(this._diskBytesSpilled() + segment.length());
      objectsWritten$1.elem = 0L;
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$merge$2(final ExternalSorter $this, final BufferedIterator inMemBuffered$1, final Seq readers$1, final int p) {
      IteratorForPartition inMemIterator = $this.new IteratorForPartition(p, inMemBuffered$1);
      Seq iterators = (Seq)((IterableOps)readers$1.map((x$4) -> x$4.readNextPartition())).$plus$plus(new scala.collection.immutable..colon.colon(inMemIterator, scala.collection.immutable.Nil..MODULE$));
      if ($this.aggregator.isDefined()) {
         return new Tuple2(BoxesRunTime.boxToInteger(p), $this.mergeWithAggregation(iterators, ((Aggregator)$this.aggregator.get()).mergeCombiners(), $this.keyComparator(), $this.ordering.isDefined()));
      } else {
         return $this.ordering.isDefined() ? new Tuple2(BoxesRunTime.boxToInteger(p), $this.org$apache$spark$util$collection$ExternalSorter$$mergeSort(iterators, (Comparator)$this.ordering.get())) : new Tuple2(BoxesRunTime.boxToInteger(p), iterators.iterator().flatten(scala.Predef..MODULE$.$conforms()));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeSort$1(final Iterator x$5) {
      return x$5.hasNext();
   }

   // $FF: synthetic method
   public static final int org$apache$spark$util$collection$ExternalSorter$$$anonfun$mergeSort$3(final BufferedIterator x, final BufferedIterator y, final Comparator comparator$1) {
      return comparator$1.compare(((Product2)y.head())._1(), ((Product2)x.head())._1());
   }

   // $FF: synthetic method
   public static final void $anonfun$insertAllAndUpdateMetrics$1(final ExternalSorter $this, final TaskContext x$9) {
      $this.stop();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$writePartitionedMapOutput$3(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$writePartitionedMapOutput$6(final ObjectRef partitionPairsWriter$2, final Product2 elem) {
      ((ShufflePartitionPairsWriter)partitionPairsWriter$2.elem).write(elem._1(), elem._2());
   }

   // $FF: synthetic method
   public static final void $anonfun$writePartitionedMapOutput$4(final ExternalSorter $this, final int shuffleId$1, final long mapId$1, final ShuffleMapOutputWriter mapOutputWriter$1, final ShuffleWriteMetricsReporter writeMetrics$1, final Tuple2 x$10) {
      if (x$10 != null) {
         int id = x$10._1$mcI$sp();
         Iterator elements = (Iterator)x$10._2();
         ShuffleBlockId blockId = new ShuffleBlockId(shuffleId$1, mapId$1, id);
         ObjectRef partitionWriter = ObjectRef.create((Object)null);
         ObjectRef partitionPairsWriter = ObjectRef.create((Object)null);
         BoxedUnit var10000 = (BoxedUnit)org.apache.spark.util.Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
            partitionWriter.elem = mapOutputWriter$1.getPartitionWriter(id);
            partitionPairsWriter.elem = new ShufflePartitionPairsWriter((ShufflePartitionWriter)partitionWriter.elem, $this.org$apache$spark$util$collection$ExternalSorter$$serializerManager(), $this.org$apache$spark$util$collection$ExternalSorter$$serInstance(), blockId, writeMetrics$1, scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])$this.partitionChecksums())) ? $this.partitionChecksums()[id] : null);
            if (elements.hasNext()) {
               elements.foreach((elem) -> {
                  $anonfun$writePartitionedMapOutput$6(partitionPairsWriter, elem);
                  return BoxedUnit.UNIT;
               });
            }
         }, (JFunction0.mcV.sp)() -> {
            if ((ShufflePartitionPairsWriter)partitionPairsWriter.elem != null) {
               ((ShufflePartitionPairsWriter)partitionPairsWriter.elem).close();
            }
         });
      } else {
         throw new MatchError(x$10);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stop$1(final SpilledFile s) {
      return s.file().delete();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stop$2(final SpilledFile s) {
      return s.file().delete();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$groupByPartition$1(final ExternalSorter $this, final BufferedIterator buffered$1, final int p) {
      return new Tuple2(BoxesRunTime.boxToInteger(p), $this.new IteratorForPartition(p, buffered$1));
   }

   public ExternalSorter(final TaskContext context, final Option aggregator, final Option partitioner, final Option ordering, final Serializer serializer) {
      super(context.taskMemoryManager());
      this.context = context;
      this.aggregator = aggregator;
      this.ordering = ordering;
      this.conf = SparkEnv$.MODULE$.get().conf();
      this.org$apache$spark$util$collection$ExternalSorter$$numPartitions = BoxesRunTime.unboxToInt(partitioner.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$numPartitions$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 1));
      this.actualPartitioner = (Partitioner)(this.org$apache$spark$util$collection$ExternalSorter$$numPartitions() > 1 ? (Partitioner)partitioner.get() : new ConstantPartitioner());
      this.blockManager = SparkEnv$.MODULE$.get().blockManager();
      this.diskBlockManager = this.blockManager().diskBlockManager();
      this.org$apache$spark$util$collection$ExternalSorter$$serializerManager = SparkEnv$.MODULE$.get().serializerManager();
      this.org$apache$spark$util$collection$ExternalSorter$$serInstance = serializer.newInstance();
      this.fileBufferSize = (int)BoxesRunTime.unboxToLong(this.conf().get(package$.MODULE$.SHUFFLE_FILE_BUFFER_SIZE())) * 1024;
      this.org$apache$spark$util$collection$ExternalSorter$$serializerBatchSize = BoxesRunTime.unboxToLong(this.conf().get(package$.MODULE$.SHUFFLE_SPILL_BATCH_SIZE()));
      this.map = new PartitionedAppendOnlyMap();
      this.buffer = new PartitionedPairBuffer(PartitionedPairBuffer$.MODULE$.$lessinit$greater$default$1());
      this._diskBytesSpilled = 0L;
      this._peakMemoryUsedBytes = 0L;
      this.isShuffleSort = true;
      this.org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles = new ArrayBuffer();
      this.readingIterator = null;
      this.partitionChecksums = this.createPartitionChecksums(this.org$apache$spark$util$collection$ExternalSorter$$numPartitions(), this.conf());
      this.keyComparator = (Comparator)ordering.getOrElse(() -> (a, b) -> {
            int h1 = a == null ? 0 : a.hashCode();
            int h2 = b == null ? 0 : b.hashCode();
            if (h1 < h2) {
               return -1;
            } else {
               return h1 == h2 ? 0 : 1;
            }
         });
      this.spills = new ArrayBuffer();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class SpilledFile implements Product, Serializable {
      private final File file;
      private final BlockId blockId;
      private final long[] serializerBatchSizes;
      private final long[] elementsPerPartition;
      // $FF: synthetic field
      public final ExternalSorter $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public File file() {
         return this.file;
      }

      public BlockId blockId() {
         return this.blockId;
      }

      public long[] serializerBatchSizes() {
         return this.serializerBatchSizes;
      }

      public long[] elementsPerPartition() {
         return this.elementsPerPartition;
      }

      public SpilledFile copy(final File file, final BlockId blockId, final long[] serializerBatchSizes, final long[] elementsPerPartition) {
         return this.org$apache$spark$util$collection$ExternalSorter$SpilledFile$$$outer().new SpilledFile(file, blockId, serializerBatchSizes, elementsPerPartition);
      }

      public File copy$default$1() {
         return this.file();
      }

      public BlockId copy$default$2() {
         return this.blockId();
      }

      public long[] copy$default$3() {
         return this.serializerBatchSizes();
      }

      public long[] copy$default$4() {
         return this.elementsPerPartition();
      }

      public String productPrefix() {
         return "SpilledFile";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.file();
            }
            case 1 -> {
               return this.blockId();
            }
            case 2 -> {
               return this.serializerBatchSizes();
            }
            case 3 -> {
               return this.elementsPerPartition();
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
         return x$1 instanceof SpilledFile;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "file";
            }
            case 1 -> {
               return "blockId";
            }
            case 2 -> {
               return "serializerBatchSizes";
            }
            case 3 -> {
               return "elementsPerPartition";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label64: {
               if (x$1 instanceof SpilledFile && ((SpilledFile)x$1).org$apache$spark$util$collection$ExternalSorter$SpilledFile$$$outer() == this.org$apache$spark$util$collection$ExternalSorter$SpilledFile$$$outer()) {
                  label54: {
                     SpilledFile var4 = (SpilledFile)x$1;
                     File var10000 = this.file();
                     File var5 = var4.file();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     BlockId var7 = this.blockId();
                     BlockId var6 = var4.blockId();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (this.serializerBatchSizes() == var4.serializerBatchSizes() && this.elementsPerPartition() == var4.elementsPerPartition() && var4.canEqual(this)) {
                        break label64;
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      // $FF: synthetic method
      public ExternalSorter org$apache$spark$util$collection$ExternalSorter$SpilledFile$$$outer() {
         return this.$outer;
      }

      public SpilledFile(final File file, final BlockId blockId, final long[] serializerBatchSizes, final long[] elementsPerPartition) {
         this.file = file;
         this.blockId = blockId;
         this.serializerBatchSizes = serializerBatchSizes;
         this.elementsPerPartition = elementsPerPartition;
         if (ExternalSorter.this == null) {
            throw null;
         } else {
            this.$outer = ExternalSorter.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class SpilledFile$ extends AbstractFunction4 implements Serializable {
      // $FF: synthetic field
      private final ExternalSorter $outer;

      public final String toString() {
         return "SpilledFile";
      }

      public SpilledFile apply(final File file, final BlockId blockId, final long[] serializerBatchSizes, final long[] elementsPerPartition) {
         return this.$outer.new SpilledFile(file, blockId, serializerBatchSizes, elementsPerPartition);
      }

      public Option unapply(final SpilledFile x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.file(), x$0.blockId(), x$0.serializerBatchSizes(), x$0.elementsPerPartition())));
      }

      public SpilledFile$() {
         if (ExternalSorter.this == null) {
            throw null;
         } else {
            this.$outer = ExternalSorter.this;
            super();
         }
      }
   }

   private class SpillReader {
      private final SpilledFile spill;
      private final long[] batchOffsets;
      private int partitionId;
      private long indexInPartition;
      private int batchId;
      private int indexInBatch;
      private int lastPartitionId;
      private FileInputStream fileStream;
      private DeserializationStream deserializeStream;
      private Tuple2 nextItem;
      private boolean finished;
      private int nextPartitionToRead;
      // $FF: synthetic field
      public final ExternalSorter $outer;

      public long[] batchOffsets() {
         return this.batchOffsets;
      }

      public int partitionId() {
         return this.partitionId;
      }

      public void partitionId_$eq(final int x$1) {
         this.partitionId = x$1;
      }

      public long indexInPartition() {
         return this.indexInPartition;
      }

      public void indexInPartition_$eq(final long x$1) {
         this.indexInPartition = x$1;
      }

      public int batchId() {
         return this.batchId;
      }

      public void batchId_$eq(final int x$1) {
         this.batchId = x$1;
      }

      public int indexInBatch() {
         return this.indexInBatch;
      }

      public void indexInBatch_$eq(final int x$1) {
         this.indexInBatch = x$1;
      }

      public int lastPartitionId() {
         return this.lastPartitionId;
      }

      public void lastPartitionId_$eq(final int x$1) {
         this.lastPartitionId = x$1;
      }

      public FileInputStream fileStream() {
         return this.fileStream;
      }

      public void fileStream_$eq(final FileInputStream x$1) {
         this.fileStream = x$1;
      }

      public DeserializationStream deserializeStream() {
         return this.deserializeStream;
      }

      public void deserializeStream_$eq(final DeserializationStream x$1) {
         this.deserializeStream = x$1;
      }

      public Tuple2 nextItem() {
         return this.nextItem;
      }

      public void nextItem_$eq(final Tuple2 x$1) {
         this.nextItem = x$1;
      }

      public boolean finished() {
         return this.finished;
      }

      public void finished_$eq(final boolean x$1) {
         this.finished = x$1;
      }

      public DeserializationStream nextBatchStream() {
         if (this.batchId() < this.batchOffsets().length - 1) {
            if (this.deserializeStream() != null) {
               this.deserializeStream().close();
               this.fileStream().close();
               this.deserializeStream_$eq((DeserializationStream)null);
               this.fileStream_$eq((FileInputStream)null);
            }

            long start = this.batchOffsets()[this.batchId()];
            this.fileStream_$eq(new FileInputStream(this.spill.file()));
            this.fileStream().getChannel().position(start);
            this.batchId_$eq(this.batchId() + 1);
            long end = this.batchOffsets()[this.batchId()];
            scala.Predef..MODULE$.assert(end >= start, () -> "start = " + start + ", end = " + end + ", batchOffsets = " + scala.Predef..MODULE$.wrapLongArray(this.batchOffsets()).mkString("[", ", ", "]"));
            BufferedInputStream bufferedStream = new BufferedInputStream(ByteStreams.limit(this.fileStream(), end - start));
            InputStream wrappedStream = this.org$apache$spark$util$collection$ExternalSorter$SpillReader$$$outer().org$apache$spark$util$collection$ExternalSorter$$serializerManager().wrapStream(this.spill.blockId(), (InputStream)bufferedStream);
            return this.org$apache$spark$util$collection$ExternalSorter$SpillReader$$$outer().org$apache$spark$util$collection$ExternalSorter$$serInstance().deserializeStream(wrappedStream);
         } else {
            this.cleanup();
            return null;
         }
      }

      private void skipToNextPartition() {
         while(this.partitionId() < this.org$apache$spark$util$collection$ExternalSorter$SpillReader$$$outer().org$apache$spark$util$collection$ExternalSorter$$numPartitions() && this.indexInPartition() == this.spill.elementsPerPartition()[this.partitionId()]) {
            this.partitionId_$eq(this.partitionId() + 1);
            this.indexInPartition_$eq(0L);
         }

      }

      public Tuple2 org$apache$spark$util$collection$ExternalSorter$SpillReader$$readNextItem() {
         if (!this.finished() && this.deserializeStream() != null) {
            Object k = this.deserializeStream().readKey(scala.reflect.ClassTag..MODULE$.Nothing());
            Object c = this.deserializeStream().readValue(scala.reflect.ClassTag..MODULE$.Nothing());
            this.lastPartitionId_$eq(this.partitionId());
            this.indexInBatch_$eq(this.indexInBatch() + 1);
            if ((long)this.indexInBatch() == this.org$apache$spark$util$collection$ExternalSorter$SpillReader$$$outer().org$apache$spark$util$collection$ExternalSorter$$serializerBatchSize()) {
               this.indexInBatch_$eq(0);
               this.deserializeStream_$eq(this.nextBatchStream());
            }

            this.indexInPartition_$eq(this.indexInPartition() + 1L);
            this.skipToNextPartition();
            if (this.partitionId() == this.org$apache$spark$util$collection$ExternalSorter$SpillReader$$$outer().org$apache$spark$util$collection$ExternalSorter$$numPartitions()) {
               this.finished_$eq(true);
               if (this.deserializeStream() != null) {
                  this.deserializeStream().close();
               }
            }

            return new Tuple2(k, c);
         } else {
            return null;
         }
      }

      public int nextPartitionToRead() {
         return this.nextPartitionToRead;
      }

      public void nextPartitionToRead_$eq(final int x$1) {
         this.nextPartitionToRead = x$1;
      }

      public Iterator readNextPartition() {
         return new Iterator() {
            private final int myPartition;
            // $FF: synthetic field
            private final SpillReader $outer;

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

            private int myPartition() {
               return this.myPartition;
            }

            public boolean hasNext() {
               if (this.$outer.nextItem() == null) {
                  this.$outer.nextItem_$eq(this.$outer.org$apache$spark$util$collection$ExternalSorter$SpillReader$$readNextItem());
                  if (this.$outer.nextItem() == null) {
                     return false;
                  }
               }

               scala.Predef..MODULE$.assert(this.$outer.lastPartitionId() >= this.myPartition());
               return this.$outer.lastPartitionId() == this.myPartition();
            }

            public Product2 next() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  Tuple2 item = this.$outer.nextItem();
                  this.$outer.nextItem_$eq((Tuple2)null);
                  return item;
               }
            }

            public {
               if (SpillReader.this == null) {
                  throw null;
               } else {
                  this.$outer = SpillReader.this;
                  IterableOnce.$init$(this);
                  IterableOnceOps.$init$(this);
                  Iterator.$init$(this);
                  this.myPartition = SpillReader.this.nextPartitionToRead();
                  SpillReader.this.nextPartitionToRead_$eq(SpillReader.this.nextPartitionToRead() + 1);
               }
            }
         };
      }

      public void cleanup() {
         this.batchId_$eq(this.batchOffsets().length);
         DeserializationStream ds = this.deserializeStream();
         this.deserializeStream_$eq((DeserializationStream)null);
         this.fileStream_$eq((FileInputStream)null);
         if (ds != null) {
            ds.close();
         }
      }

      // $FF: synthetic method
      public ExternalSorter org$apache$spark$util$collection$ExternalSorter$SpillReader$$$outer() {
         return this.$outer;
      }

      public SpillReader(final SpilledFile spill) {
         this.spill = spill;
         if (ExternalSorter.this == null) {
            throw null;
         } else {
            this.$outer = ExternalSorter.this;
            super();
            this.batchOffsets = (long[])scala.collection.ArrayOps..MODULE$.scanLeft$extension(scala.Predef..MODULE$.longArrayOps(spill.serializerBatchSizes()), BoxesRunTime.boxToLong(0L), (JFunction2.mcJJJ.sp)(x$7, x$8) -> x$7 + x$8, scala.reflect.ClassTag..MODULE$.Long());
            this.partitionId = 0;
            this.indexInPartition = 0L;
            this.batchId = 0;
            this.indexInBatch = 0;
            this.lastPartitionId = 0;
            this.skipToNextPartition();
            this.fileStream = null;
            this.deserializeStream = this.nextBatchStream();
            this.nextItem = null;
            this.finished = false;
            this.nextPartitionToRead = 0;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class IteratorForPartition implements Iterator {
      private final int partitionId;
      private final BufferedIterator data;
      // $FF: synthetic field
      public final ExternalSorter $outer;

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
         return this.data.hasNext() && ((Tuple2)((Tuple2)this.data.head())._1())._1$mcI$sp() == this.partitionId;
      }

      public Product2 next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            Tuple2 elem = (Tuple2)this.data.next();
            return new Tuple2(((Tuple2)elem._1())._2(), elem._2());
         }
      }

      // $FF: synthetic method
      public ExternalSorter org$apache$spark$util$collection$ExternalSorter$IteratorForPartition$$$outer() {
         return this.$outer;
      }

      public IteratorForPartition(final int partitionId, final BufferedIterator data) {
         this.partitionId = partitionId;
         this.data = data;
         if (ExternalSorter.this == null) {
            throw null;
         } else {
            this.$outer = ExternalSorter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
         }
      }
   }

   private class SpillableIterator implements Iterator {
      private Iterator upstream;
      private final Object SPILL_LOCK;
      private Iterator nextUpstream;
      private Tuple2 cur;
      private boolean hasSpilled;
      // $FF: synthetic field
      public final ExternalSorter $outer;

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

      public Iterator upstream() {
         return this.upstream;
      }

      public void upstream_$eq(final Iterator x$1) {
         this.upstream = x$1;
      }

      private Object SPILL_LOCK() {
         return this.SPILL_LOCK;
      }

      private Iterator nextUpstream() {
         return this.nextUpstream;
      }

      private void nextUpstream_$eq(final Iterator x$1) {
         this.nextUpstream = x$1;
      }

      private Tuple2 cur() {
         return this.cur;
      }

      private void cur_$eq(final Tuple2 x$1) {
         this.cur = x$1;
      }

      private boolean hasSpilled() {
         return this.hasSpilled;
      }

      private void hasSpilled_$eq(final boolean x$1) {
         this.hasSpilled = x$1;
      }

      public boolean spill() {
         synchronized(this.SPILL_LOCK()){}

         boolean var2;
         try {
            boolean var10000;
            if (this.hasSpilled()) {
               var10000 = false;
            } else {
               WritablePartitionedIterator inMemoryIterator = new WritablePartitionedIterator(this.upstream());
               this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToLong(TaskContext$.MODULE$.get().taskAttemptId()))}))).$plus(this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" force spilling in-memory map to disk and it will release"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " memory"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, org.apache.spark.util.Utils$.MODULE$.bytesToString(this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().getUsed()))}))))));
               SpilledFile spillFile = this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().org$apache$spark$util$collection$ExternalSorter$$spillMemoryIteratorToDisk(inMemoryIterator);
               this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().org$apache$spark$util$collection$ExternalSorter$$forceSpillFiles().$plus$eq(spillFile);
               SpillReader spillReader = this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().new SpillReader(spillFile);
               this.nextUpstream_$eq(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer().org$apache$spark$util$collection$ExternalSorter$$numPartitions()).iterator().flatMap((p) -> $anonfun$spill$2(spillReader, BoxesRunTime.unboxToInt(p))));
               this.hasSpilled_$eq(true);
               var10000 = true;
            }

            var2 = var10000;
         } catch (Throwable var7) {
            throw var7;
         }

         return var2;
      }

      public Tuple2 readNext() {
         synchronized(this.SPILL_LOCK()){}

         Tuple2 var2;
         try {
            if (this.nextUpstream() != null) {
               this.upstream_$eq(this.nextUpstream());
               this.nextUpstream_$eq((Iterator)null);
            }

            var2 = this.upstream().hasNext() ? (Tuple2)this.upstream().next() : null;
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }

      public boolean hasNext() {
         return this.cur() != null;
      }

      public Tuple2 next() {
         Tuple2 r = this.cur();
         this.cur_$eq(this.readNext());
         return r;
      }

      // $FF: synthetic method
      public ExternalSorter org$apache$spark$util$collection$ExternalSorter$SpillableIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Iterator $anonfun$spill$2(final SpillReader spillReader$1, final int p) {
         Iterator iterator = spillReader$1.readNextPartition();
         return iterator.map((cur) -> new Tuple2(new Tuple2(BoxesRunTime.boxToInteger(p), cur._1()), cur._2()));
      }

      public SpillableIterator(final Iterator upstream) {
         this.upstream = upstream;
         if (ExternalSorter.this == null) {
            throw null;
         } else {
            this.$outer = ExternalSorter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.SPILL_LOCK = new Object();
            this.nextUpstream = null;
            this.cur = this.readNext();
            this.hasSpilled = false;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
