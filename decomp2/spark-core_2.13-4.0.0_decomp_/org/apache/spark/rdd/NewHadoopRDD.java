package org.apache.spark.rdd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.BlockMissingException;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.InvalidInputException;
import org.apache.hadoop.mapreduce.task.JobContextImpl;
import org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl;
import org.apache.hadoop.security.AccessControlException;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.InputMetrics;
import org.apache.spark.internal.MDC;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.Utils$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
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
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.jdk.CollectionConverters.;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t%h\u0001\u0002\u001a4\u0001qB\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\tC\u0002\u0011\t\u0011)A\u0005E\"A!\u0010\u0001B\u0001B\u0003%1\u0010\u0003\u0005}\u0001\t\u0005\t\u0015!\u0003~\u0011!q\bA!b\u0001\n\u0013y\bBCA\u0007\u0001\t\u0005\t\u0015!\u0003\u0002\u0002!Q\u0011q\u0003\u0001\u0003\u0002\u0003\u0006I!!\u0007\t\u0015\u0005}\u0001A!A!\u0002\u0013\tI\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005\u0005\u0002\u0001\"\u0001\u0002>!I\u00111\u000b\u0001C\u0002\u0013%\u0011Q\u000b\u0005\t\u0003_\u0002\u0001\u0015!\u0003\u0002X!I\u0011\u0011\u000f\u0001C\u0002\u0013%\u00111\u000f\u0005\t\u0003w\u0002\u0001\u0015!\u0003\u0002v!I\u0011Q\u0010\u0001C\u0002\u0013E\u0011q\u0010\u0005\t\u0003\u000f\u0003\u0001\u0015!\u0003\u0002\u0002\"I\u00111\u0012\u0001C\u0002\u0013%\u0011Q\u0012\u0005\t\u0003\u001f\u0003\u0001\u0015!\u0003\u0002\u001a!I\u0011\u0011\u0013\u0001C\u0002\u0013%\u0011Q\u0012\u0005\t\u0003'\u0003\u0001\u0015!\u0003\u0002\u001a!1\u0011Q\u0013\u0001\u0005\u0002}Dq!a&\u0001\t\u0003\nI\nC\u0004\u0002(\u0002!\t%!+\t\u000f\u0005}\u0006\u0001\"\u0001\u0002B\"I!Q\u0003\u0001\u0012\u0002\u0013\u0005!q\u0003\u0005\b\u0005_\u0001A\u0011\tB\u0019\u0011\u001d\u0011i\u0004\u0001C!\u0005\u007f9\u0001B!\u00164\u0011\u0003)$q\u000b\u0004\beMB\t!\u000eB-\u0011\u001d\t\t#\bC\u0001\u0005cB\u0011Ba\u001d\u001e\u0005\u0004%\tA!\u001e\t\u0011\t\rU\u0004)A\u0005\u0005o2qA!\"\u001e\u0001U\u00129\t\u0003\u0006\u0003\u0012\u0006\u0012\t\u0011)A\u0005\u0005'C!\"!9\"\u0005\u0003\u0005\u000b\u0011\u0002BN\u0011)\u0011)!\tB\u0001B\u0003%\u0011\u0011\u0004\u0005\u000b\u0005C\u000b#1!Q\u0001\f\t\r\u0006B\u0003BSC\t\r\t\u0015a\u0003\u0003(\"9\u0011\u0011E\u0011\u0005\u0002\t%\u0006\"\u0003B^C\t\u0007I\u0011\tB_\u0011!\u0011Y-\tQ\u0001\n\t}\u0006bBALC\u0011\u0005\u0013\u0011\u0014\u0005\b\u0003O\u000bC\u0011\tBg\u000f)\u0011).HA\u0001\u0012\u0003)$q\u001b\u0004\u000b\u0005\u000bk\u0012\u0011!E\u0001k\te\u0007bBA\u0011[\u0011\u0005!1\u001c\u0005\n\u0005;l\u0013\u0013!C\u0001\u0005?D\u0011B!:.\u0003\u0003%IAa:\t\u0013\t\u0015X$!A\u0005\n\t\u001d(\u0001\u0004(fo\"\u000bGm\\8q%\u0012#%B\u0001\u001b6\u0003\r\u0011H\r\u001a\u0006\u0003m]\nQa\u001d9be.T!\u0001O\u001d\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0014aA8sO\u000e\u0001QcA\u001fK)N\u0019\u0001A\u0010,\u0011\u0007}\u0002%)D\u00014\u0013\t\t5GA\u0002S\t\u0012\u0003Ba\u0011$I'6\tAIC\u0001F\u0003\u0015\u00198-\u00197b\u0013\t9EI\u0001\u0004UkBdWM\r\t\u0003\u0013*c\u0001\u0001B\u0003L\u0001\t\u0007AJA\u0001L#\ti\u0005\u000b\u0005\u0002D\u001d&\u0011q\n\u0012\u0002\b\u001d>$\b.\u001b8h!\t\u0019\u0015+\u0003\u0002S\t\n\u0019\u0011I\\=\u0011\u0005%#F!B+\u0001\u0005\u0004a%!\u0001,\u0011\u0005]SV\"\u0001-\u000b\u0005e+\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005mC&a\u0002'pO\u001eLgnZ\u0001\u0003g\u000e\u0004\"AX0\u000e\u0003UJ!\u0001Y\u001b\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002!%t\u0007/\u001e;G_Jl\u0017\r^\"mCN\u001c\bGA2p!\r!7N\u001c\b\u0003K&\u0004\"A\u001a#\u000e\u0003\u001dT!\u0001[\u001e\u0002\rq\u0012xn\u001c;?\u0013\tQG)\u0001\u0004Qe\u0016$WMZ\u0005\u0003Y6\u0014Qa\u00117bgNT!A\u001b#\u0011\u0005%{G!\u00039\u0003\u0003\u0003\u0005\tQ!\u0001r\u0005\ryF%M\t\u0003\u001bJ\u0004Ba\u001d=I'6\tAO\u0003\u0002vm\u0006IQ.\u00199sK\u0012,8-\u001a\u0006\u0003o^\na\u0001[1e_>\u0004\u0018BA=u\u0005-Ie\u000e];u\r>\u0014X.\u0019;\u0002\u0011-,\u0017p\u00117bgN\u00042\u0001Z6I\u0003)1\u0018\r\\;f\u00072\f7o\u001d\t\u0004I.\u001c\u0016!B0d_:4WCAA\u0001!\u0011\t\u0019!!\u0003\u000e\u0005\u0005\u0015!bAA\u0004m\u0006!1m\u001c8g\u0013\u0011\tY!!\u0002\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0019y6m\u001c8gA!\u001aa!!\u0005\u0011\u0007\r\u000b\u0019\"C\u0002\u0002\u0016\u0011\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0002%%<gn\u001c:f\u0007>\u0014(/\u001e9u\r&dWm\u001d\t\u0004\u0007\u0006m\u0011bAA\u000f\t\n9!i\\8mK\u0006t\u0017AE5h]>\u0014X-T5tg&twMR5mKN\fa\u0001P5oSRtD\u0003EA\u0013\u0003O\tI#a\r\u00026\u0005]\u0012\u0011HA\u001e!\u0011y\u0004\u0001S*\t\u000bqK\u0001\u0019A/\t\r\u0005L\u0001\u0019AA\u0016a\u0011\ti#!\r\u0011\t\u0011\\\u0017q\u0006\t\u0004\u0013\u0006EBA\u00039\u0002*\u0005\u0005\t\u0011!B\u0001c\")!0\u0003a\u0001w\")A0\u0003a\u0001{\"1a0\u0003a\u0001\u0003\u0003Aq!a\u0006\n\u0001\u0004\tI\u0002C\u0004\u0002 %\u0001\r!!\u0007\u0015\u0019\u0005\u0015\u0012qHA!\u0003\u001b\ny%!\u0015\t\u000bqS\u0001\u0019A/\t\r\u0005T\u0001\u0019AA\"a\u0011\t)%!\u0013\u0011\t\u0011\\\u0017q\t\t\u0004\u0013\u0006%CaCA&\u0003\u0003\n\t\u0011!A\u0003\u0002E\u00141a\u0018\u00133\u0011\u0015Q(\u00021\u0001|\u0011\u0015a(\u00021\u0001~\u0011\u0019q(\u00021\u0001\u0002\u0002\u0005i1m\u001c8g\u0005J|\u0017\rZ2bgR,\"!a\u0016\u0011\r\u0005e\u0013qLA2\u001b\t\tYFC\u0002\u0002^U\n\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\t\u0005\u0005\u00141\f\u0002\n\u0005J|\u0017\rZ2bgR\u0004B!!\u001a\u0002l5\u0011\u0011q\r\u0006\u0004\u0003S*\u0014\u0001B;uS2LA!!\u001c\u0002h\tI2+\u001a:jC2L'0\u00192mK\u000e{gNZ5hkJ\fG/[8o\u00039\u0019wN\u001c4Ce>\fGmY1ti\u0002\nAB[8c)J\f7m[3s\u0013\u0012,\"!!\u001e\u0011\u0007\u0011\f9(C\u0002\u0002z5\u0014aa\u0015;sS:<\u0017!\u00046pER\u0013\u0018mY6fe&#\u0007%A\u0003k_\nLE-\u0006\u0002\u0002\u0002B\u00191/a!\n\u0007\u0005\u0015EOA\u0003K_\nLE)\u0001\u0004k_\nLE\r\t\u0015\u0004!\u0005E\u0011AE:i_VdGm\u00117p]\u0016TuNY\"p]\u001a,\"!!\u0007\u0002'MDw.\u001e7e\u00072|g.\u001a&pE\u000e{gN\u001a\u0011\u0002#%<gn\u001c:f\u000b6\u0004H/_*qY&$8/\u0001\njO:|'/Z#naRL8\u000b\u001d7jiN\u0004\u0013aB4fi\u000e{gNZ\u0001\u000eO\u0016$\b+\u0019:uSRLwN\\:\u0016\u0005\u0005m\u0005#B\"\u0002\u001e\u0006\u0005\u0016bAAP\t\n)\u0011I\u001d:bsB\u0019a,a)\n\u0007\u0005\u0015VGA\u0005QCJ$\u0018\u000e^5p]\u000691m\\7qkR,GCBAV\u0003c\u000b)\f\u0005\u0003_\u0003[\u0013\u0015bAAXk\t)\u0012J\u001c;feJ,\b\u000f^5cY\u0016LE/\u001a:bi>\u0014\bbBAZ/\u0001\u0007\u0011\u0011U\u0001\ti\",7\u000b\u001d7ji\"9\u0011qW\fA\u0002\u0005e\u0016aB2p]R,\u0007\u0010\u001e\t\u0004=\u0006m\u0016bAA_k\tYA+Y:l\u0007>tG/\u001a=u\u0003mi\u0017\r\u001d)beRLG/[8og^KG\u000f[%oaV$8\u000b\u001d7jiV!\u00111YAf)\u0019\t)-a8\u0003\u0004Q!\u0011qYAh!\u0011y\u0004)!3\u0011\u0007%\u000bY\r\u0002\u0004\u0002Nb\u0011\r\u0001\u0014\u0002\u0002+\"I\u0011\u0011\u001b\r\u0002\u0002\u0003\u000f\u00111[\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBAk\u00037\fI-\u0004\u0002\u0002X*\u0019\u0011\u0011\u001c#\u0002\u000fI,g\r\\3di&!\u0011Q\\Al\u0005!\u0019E.Y:t)\u0006<\u0007bBAq1\u0001\u0007\u00111]\u0001\u0002MBI1)!:\u0002j\u0006=(\u0011A\u0005\u0004\u0003O$%!\u0003$v]\u000e$\u0018n\u001c83!\r\u0019\u00181^\u0005\u0004\u0003[$(AC%oaV$8\u000b\u001d7jiB)\u0011\u0011_A~\u0005:!\u00111_A|\u001d\r1\u0017Q_\u0005\u0002\u000b&\u0019\u0011\u0011 #\u0002\u000fA\f7m[1hK&!\u0011Q`A\u0000\u0005!IE/\u001a:bi>\u0014(bAA}\tB1\u0011\u0011_A~\u0003\u0013D\u0011B!\u0002\u0019!\u0003\u0005\r!!\u0007\u0002+A\u0014Xm]3sm\u0016\u001c\b+\u0019:uSRLwN\\5oO\"\u001a\u0001D!\u0003\u0011\t\t-!\u0011C\u0007\u0003\u0005\u001bQ1Aa\u00046\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005'\u0011iA\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018.A\u0013nCB\u0004\u0016M\u001d;ji&|gn],ji\"Le\u000e];u'Bd\u0017\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%eU!!\u0011\u0004B\u0017+\t\u0011YB\u000b\u0003\u0002\u001a\tu1F\u0001B\u0010!\u0011\u0011\tC!\u000b\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0005O\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t=A)\u0003\u0003\u0003,\t\r\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00121\u0011QZ\rC\u00021\u000bQcZ3u!J,g-\u001a:sK\u0012dunY1uS>t7\u000f\u0006\u0003\u00034\te\u0002CBAy\u0005k\t)(\u0003\u0003\u00038\u0005}(aA*fc\"9!1\b\u000eA\u0002\u0005\u0005\u0016A\u00025ta2LG/A\u0004qKJ\u001c\u0018n\u001d;\u0015\t\t\u0005#1I\u0007\u0002\u0001!9!QI\u000eA\u0002\t\u001d\u0013\u0001D:u_J\fw-\u001a'fm\u0016d\u0007\u0003\u0002B%\u0005\u001fj!Aa\u0013\u000b\u0007\t5S'A\u0004ti>\u0014\u0018mZ3\n\t\tE#1\n\u0002\r'R|'/Y4f\u0019\u00164X\r\u001c\u0015\u0004\u0001\t%\u0011\u0001\u0004(fo\"\u000bGm\\8q%\u0012#\u0005CA \u001e'\u0015i\"1\fB1!\r\u0019%QL\u0005\u0004\u0005?\"%AB!osJ+g\r\u0005\u0003\u0003d\t5TB\u0001B3\u0015\u0011\u00119G!\u001b\u0002\u0005%|'B\u0001B6\u0003\u0011Q\u0017M^1\n\t\t=$Q\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0005/\n\u0001eQ(O\r&;UKU!U\u0013>su,\u0013(T)\u0006sE+S!U\u0013>su\fT(D\u0017V\u0011!q\u000f\t\u0005\u0005s\u0012y(\u0004\u0002\u0003|)!!Q\u0010B5\u0003\u0011a\u0017M\\4\n\t\t\u0005%1\u0010\u0002\u0007\u001f\nTWm\u0019;\u0002C\r{eJR%H+J\u000bE+S(O?&s5\u000bV!O)&\u000bE+S(O?2{5i\u0013\u0011\u0003E9+w\u000fS1e_>\u0004X*\u00199QCJ$\u0018\u000e^5p]N<\u0016\u000e\u001e5Ta2LGO\u0015#E+\u0019\u0011IIa$\u0003\u0018N\u0019\u0011Ea#\u0011\t}\u0002%Q\u0012\t\u0004\u0013\n=EABAgC\t\u0007A*\u0001\u0003qe\u00164\b\u0003B A\u0005+\u00032!\u0013BL\t\u0019\u0011I*\tb\u0001\u0019\n\tA\u000bE\u0005D\u0003K\fIO!(\u0003 B1\u0011\u0011_A~\u0005+\u0003b!!=\u0002|\n5\u0015AC3wS\u0012,gnY3%eA1\u0011Q[An\u0005\u001b\u000b!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\t).a7\u0003\u0016RA!1\u0016B[\u0005o\u0013I\f\u0006\u0004\u0003.\nE&1\u0017\t\b\u0005_\u000b#Q\u0012BK\u001b\u0005i\u0002b\u0002BQO\u0001\u000f!1\u0015\u0005\b\u0005K;\u00039\u0001BT\u0011\u001d\u0011\tj\na\u0001\u0005'Cq!!9(\u0001\u0004\u0011Y\nC\u0005\u0003\u0006\u001d\u0002\n\u00111\u0001\u0002\u001a\u0005Y\u0001/\u0019:uSRLwN\\3s+\t\u0011y\fE\u0003D\u0005\u0003\u0014)-C\u0002\u0003D\u0012\u0013aa\u00149uS>t\u0007c\u00010\u0003H&\u0019!\u0011Z\u001b\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\ra\u0006\u0014H/\u001b;j_:,'\u000f\t\u000b\u0007\u0005?\u0013yMa5\t\u000f\tE7\u00061\u0001\u0002\"\u0006)1\u000f\u001d7ji\"9\u0011qW\u0016A\u0002\u0005e\u0016A\t(fo\"\u000bGm\\8q\u001b\u0006\u0004\b+\u0019:uSRLwN\\:XSRD7\u000b\u001d7jiJ#E\tE\u0002\u000306\u001aR!\fB.\u0005C\"\"Aa6\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\u0019\u0011IB!9\u0003d\u00121\u0011QZ\u0018C\u00021#aA!'0\u0005\u0004a\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B<\u0001"
)
public class NewHadoopRDD extends RDD {
   public final Class org$apache$spark$rdd$NewHadoopRDD$$inputFormatClass;
   private final transient Configuration _conf;
   public final boolean org$apache$spark$rdd$NewHadoopRDD$$ignoreCorruptFiles;
   public final boolean org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles;
   private final Broadcast confBroadcast;
   private final String org$apache$spark$rdd$NewHadoopRDD$$jobTrackerId;
   private final transient JobID jobId;
   private final boolean shouldCloneJobConf;
   private final boolean ignoreEmptySplits;

   public static Object CONFIGURATION_INSTANTIATION_LOCK() {
      return NewHadoopRDD$.MODULE$.CONFIGURATION_INSTANTIATION_LOCK();
   }

   private Configuration _conf() {
      return this._conf;
   }

   private Broadcast confBroadcast() {
      return this.confBroadcast;
   }

   public String org$apache$spark$rdd$NewHadoopRDD$$jobTrackerId() {
      return this.org$apache$spark$rdd$NewHadoopRDD$$jobTrackerId;
   }

   public JobID jobId() {
      return this.jobId;
   }

   private boolean shouldCloneJobConf() {
      return this.shouldCloneJobConf;
   }

   private boolean ignoreEmptySplits() {
      return this.ignoreEmptySplits;
   }

   public Configuration getConf() {
      Configuration conf = ((SerializableConfiguration)this.confBroadcast().value()).value();
      if (this.shouldCloneJobConf()) {
         synchronized(NewHadoopRDD$.MODULE$.CONFIGURATION_INSTANTIATION_LOCK()){}

         Object var3;
         try {
            this.logDebug(() -> "Cloning Hadoop Configuration");
            var3 = conf instanceof JobConf ? new JobConf(conf) : new Configuration(conf);
         } catch (Throwable var5) {
            throw var5;
         }

         return (Configuration)var3;
      } else {
         return conf;
      }
   }

   public Partition[] getPartitions() {
      InputFormat inputFormat = (InputFormat)this.org$apache$spark$rdd$NewHadoopRDD$$inputFormatClass.getConstructor().newInstance();
      this._conf().setIfUnset("mapreduce.input.fileinputformat.list-status.num-threads", Integer.toString(Runtime.getRuntime().availableProcessors()));
      if (inputFormat instanceof Configurable) {
         ((Configurable)inputFormat).setConf(this._conf());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var16 = BoxedUnit.UNIT;
      }

      Partition[] var17;
      try {
         Buffer allRowSplits = .MODULE$.ListHasAsScala(inputFormat.getSplits(new JobContextImpl(this._conf(), this.jobId()))).asScala();
         Buffer rawSplits = this.ignoreEmptySplits() ? (Buffer)allRowSplits.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$1(x$1))) : allRowSplits;
         if (rawSplits.length() == 1 && rawSplits.apply(0) instanceof FileSplit) {
            FileSplit fileSplit = (FileSplit)rawSplits.apply(0);
            Path path = fileSplit.getPath();
            if (fileSplit.getLength() > BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.IO_WARNING_LARGEFILETHRESHOLD()))) {
               CompressionCodecFactory codecFactory = new CompressionCodecFactory(this._conf());
               if (Utils$.MODULE$.isFileSplittable(path, codecFactory)) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Loading one large file ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path.toString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with only one partition, "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"we can increase partition numbers for improving performance."})))).log(scala.collection.immutable.Nil..MODULE$))));
               } else {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Loading one large unsplittable file ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path.toString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with only one "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"partition, because the file is compressed by unsplittable compression codec."})))).log(scala.collection.immutable.Nil..MODULE$))));
               }
            }
         }

         Partition[] result = new Partition[rawSplits.size()];
         rawSplits.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> result[i] = new NewHadoopPartition(this.id(), i, (InputSplit)rawSplits.apply(i)));
         var17 = result;
      } catch (Throwable var15) {
         if (var15 instanceof InvalidInputException var14) {
            if (this.org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this._conf().get("mapreduce.input.fileinputformat.inputdir"))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"doesn't exist and no partitions returned from this path."})))).log(scala.collection.immutable.Nil..MODULE$))), var14);
               var17 = (Partition[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
               return var17;
            }
         }

         throw var15;
      }

      return var17;
   }

   public InterruptibleIterator compute(final Partition theSplit, final TaskContext context) {
      Iterator iter = new Iterator(context, theSplit) {
         private final NewHadoopPartition split;
         private final Configuration conf;
         private final InputMetrics inputMetrics;
         private final long existingBytesRead;
         private final Option getBytesReadCallback;
         private final InputFormat format;
         private final TaskAttemptID attemptId;
         private final TaskAttemptContextImpl hadoopAttemptContext;
         private boolean finished;
         private RecordReader reader;
         private boolean havePair;
         // $FF: synthetic field
         private final NewHadoopRDD $outer;

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

         private NewHadoopPartition split() {
            return this.split;
         }

         private Configuration conf() {
            return this.conf;
         }

         private InputMetrics inputMetrics() {
            return this.inputMetrics;
         }

         private long existingBytesRead() {
            return this.existingBytesRead;
         }

         private Option getBytesReadCallback() {
            return this.getBytesReadCallback;
         }

         private void updateBytesRead() {
            this.getBytesReadCallback().foreach((getBytesRead) -> {
               $anonfun$updateBytesRead$1(this, getBytesRead);
               return BoxedUnit.UNIT;
            });
         }

         private InputFormat format() {
            return this.format;
         }

         private TaskAttemptID attemptId() {
            return this.attemptId;
         }

         private TaskAttemptContextImpl hadoopAttemptContext() {
            return this.hadoopAttemptContext;
         }

         private boolean finished() {
            return this.finished;
         }

         private void finished_$eq(final boolean x$1) {
            this.finished = x$1;
         }

         private RecordReader reader() {
            return this.reader;
         }

         private void reader_$eq(final RecordReader x$1) {
            this.reader = x$1;
         }

         private boolean havePair() {
            return this.havePair;
         }

         private void havePair_$eq(final boolean x$1) {
            this.havePair = x$1;
         }

         public boolean hasNext() {
            if (!this.finished() && !this.havePair()) {
               try {
                  this.finished_$eq(!this.reader().nextKeyValue());
               } catch (Throwable var8) {
                  label97: {
                     boolean var4 = false;
                     FileNotFoundException var5 = null;
                     if (var8 instanceof FileNotFoundException) {
                        var4 = true;
                        var5 = (FileNotFoundException)var8;
                        if (this.$outer.org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles) {
                           this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped missing file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().serializableHadoopSplit())})))), var5);
                           this.finished_$eq(true);
                           BoxedUnit var9 = BoxedUnit.UNIT;
                           break label97;
                        }
                     }

                     if (var4 && !this.$outer.org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles) {
                        throw var5;
                     }

                     if (var8 instanceof AccessControlException ? true : var8 instanceof BlockMissingException) {
                        throw var8;
                     }

                     if (var8 instanceof IOException) {
                        IOException var7 = (IOException)var8;
                        if (this.$outer.org$apache$spark$rdd$NewHadoopRDD$$ignoreCorruptFiles) {
                           this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped the rest content in the corrupted file: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().serializableHadoopSplit())}))))), var7);
                           this.finished_$eq(true);
                           BoxedUnit var10000 = BoxedUnit.UNIT;
                           break label97;
                        }
                     }

                     throw var8;
                  }
               }

               if (this.finished()) {
                  this.close();
               }

               this.havePair_$eq(!this.finished());
            }

            return !this.finished();
         }

         public Tuple2 next() {
            if (!this.hasNext()) {
               throw SparkCoreErrors$.MODULE$.endOfStreamError();
            } else {
               this.havePair_$eq(false);
               if (!this.finished()) {
                  this.inputMetrics().incRecordsRead(1L);
               }

               if (this.inputMetrics().recordsRead() % (long)SparkHadoopUtil$.MODULE$.UPDATE_INPUT_METRICS_INTERVAL_RECORDS() == 0L) {
                  this.updateBytesRead();
               }

               return new Tuple2(this.reader().getCurrentKey(), this.reader().getCurrentValue());
            }
         }

         private void close() {
            if (this.reader() != null) {
               InputFileBlockHolder$.MODULE$.unset();

               try {
                  this.reader().close();
               } catch (Exception var8) {
                  if (!ShutdownHookManager$.MODULE$.inShutdown()) {
                     this.$outer.logWarning(() -> "Exception in RecordReader.close()", var8);
                  }
               } finally {
                  this.reader_$eq((RecordReader)null);
               }

               if (this.getBytesReadCallback().isDefined()) {
                  this.updateBytesRead();
               } else if (this.split().serializableHadoopSplit().value() instanceof FileSplit || this.split().serializableHadoopSplit().value() instanceof CombineFileSplit) {
                  try {
                     this.inputMetrics().incBytesRead(((InputSplit)this.split().serializableHadoopSplit().value()).getLength());
                  } catch (IOException var7) {
                     this.$outer.logWarning(() -> "Unable to get input size to set InputMetrics for task", var7);
                  }

               }
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$updateBytesRead$1(final Object $this, final Function0 getBytesRead) {
            $this.inputMetrics().setBytesRead($this.existingBytesRead() + getBytesRead.apply$mcJ$sp());
         }

         // $FF: synthetic method
         private final RecordReader liftedTree1$1() {
            RecordReader var10000;
            try {
               var10000 = (RecordReader)Utils$.MODULE$.createResourceUninterruptiblyIfInTaskThread(() -> (RecordReader)Utils$.MODULE$.tryInitializeResource(() -> this.format().createRecordReader((InputSplit)this.split().serializableHadoopSplit().value(), this.hadoopAttemptContext()), (reader) -> {
                     reader.initialize((InputSplit)this.split().serializableHadoopSplit().value(), this.hadoopAttemptContext());
                     return reader;
                  }));
            } catch (Throwable var8) {
               boolean var4 = false;
               FileNotFoundException var5 = null;
               if (var8 instanceof FileNotFoundException) {
                  var4 = true;
                  var5 = (FileNotFoundException)var8;
                  if (this.$outer.org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles) {
                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped missing file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().serializableHadoopSplit())})))), var5);
                     this.finished_$eq(true);
                     var10000 = null;
                     return var10000;
                  }
               }

               if (var4 && !this.$outer.org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles) {
                  throw var5;
               }

               if (var8 instanceof AccessControlException ? true : var8 instanceof BlockMissingException) {
                  throw var8;
               }

               if (var8 instanceof IOException var7) {
                  if (this.$outer.org$apache$spark$rdd$NewHadoopRDD$$ignoreCorruptFiles) {
                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped the rest content in the corrupted file: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().serializableHadoopSplit())}))))), var7);
                     this.finished_$eq(true);
                     var10000 = null;
                     return var10000;
                  }
               }

               throw var8;
            }

            return var10000;
         }

         // $FF: synthetic method
         public static final void $anonfun$new$2(final Object $this, final TaskContext context) {
            $this.updateBytesRead();
            $this.close();
         }

         public {
            if (NewHadoopRDD.this == null) {
               throw null;
            } else {
               this.$outer = NewHadoopRDD.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.split = (NewHadoopPartition)theSplit$1;
               NewHadoopRDD.this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task (TID ", ") input split: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(context$1.taskAttemptId()))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INPUT_SPLIT..MODULE$, this.split().serializableHadoopSplit())}))))));
               this.conf = NewHadoopRDD.this.getConf();
               this.inputMetrics = context$1.taskMetrics().inputMetrics();
               this.existingBytesRead = this.inputMetrics().bytesRead();
               InputSplit var8 = (InputSplit)this.split().serializableHadoopSplit().value();
               if (var8 instanceof FileSplit) {
                  FileSplit var9 = (FileSplit)var8;
                  InputFileBlockHolder$.MODULE$.set(var9.getPath().toString(), var9.getStart(), var9.getLength());
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  InputFileBlockHolder$.MODULE$.unset();
                  BoxedUnit var13 = BoxedUnit.UNIT;
               }

               InputSplit var10 = (InputSplit)this.split().serializableHadoopSplit().value();
               this.getBytesReadCallback = (Option)((var10 instanceof FileSplit ? true : var10 instanceof CombineFileSplit) ? new Some(SparkHadoopUtil$.MODULE$.get().getFSBytesReadOnThreadCallback()) : scala.None..MODULE$);
               this.format = (InputFormat)NewHadoopRDD.this.org$apache$spark$rdd$NewHadoopRDD$$inputFormatClass.getConstructor().newInstance();
               InputFormat var11 = this.format();
               if (var11 instanceof Configurable) {
                  ((Configurable)var11).setConf(this.conf());
                  BoxedUnit var14 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var15 = BoxedUnit.UNIT;
               }

               this.attemptId = new TaskAttemptID(NewHadoopRDD.this.org$apache$spark$rdd$NewHadoopRDD$$jobTrackerId(), NewHadoopRDD.this.id(), TaskType.MAP, this.split().index(), 0);
               this.hadoopAttemptContext = new TaskAttemptContextImpl(this.conf(), this.attemptId());
               this.finished = false;
               this.reader = this.liftedTree1$1();
               context$1.addTaskCompletionListener((Function1)((context) -> {
                  $anonfun$new$2(this, context);
                  return BoxedUnit.UNIT;
               }));
               this.havePair = false;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      return new InterruptibleIterator(context, iter);
   }

   @DeveloperApi
   public RDD mapPartitionsWithInputSplit(final Function2 f, final boolean preservesPartitioning, final ClassTag evidence$1) {
      return new NewHadoopMapPartitionsWithSplitRDD(this, f, preservesPartitioning, evidence$1, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public boolean mapPartitionsWithInputSplit$default$2() {
      return false;
   }

   public Seq getPreferredLocations(final Partition hsplit) {
      InputSplit split = (InputSplit)((NewHadoopPartition)hsplit).serializableHadoopSplit().value();
      Option locs = HadoopRDD$.MODULE$.convertSplitLocationInfo(split.getLocationInfo());
      return (Seq)locs.getOrElse(() -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])split.getLocations()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$getPreferredLocations$2(x$2)))).toImmutableArraySeq());
   }

   public NewHadoopRDD persist(final StorageLevel storageLevel) {
      if (storageLevel.deserialized()) {
         this.logWarning(() -> "Caching NewHadoopRDDs as deserialized objects usually leads to undesired behavior because Hadoop's RecordReader reuses the same Writable object for all records. Use a map transformation to make copies of the records.");
      }

      return (NewHadoopRDD)super.persist(storageLevel);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$1(final InputSplit x$1) {
      return x$1.getLength() > 0L;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPreferredLocations$2(final String x$2) {
      boolean var10000;
      label23: {
         String var1 = "localhost";
         if (x$2 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$2.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public NewHadoopRDD(final SparkContext sc, final Class inputFormatClass, final Class keyClass, final Class valueClass, final Configuration _conf, final boolean ignoreCorruptFiles, final boolean ignoreMissingFiles) {
      super(sc, scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.org$apache$spark$rdd$NewHadoopRDD$$inputFormatClass = inputFormatClass;
      this._conf = _conf;
      this.org$apache$spark$rdd$NewHadoopRDD$$ignoreCorruptFiles = ignoreCorruptFiles;
      this.org$apache$spark$rdd$NewHadoopRDD$$ignoreMissingFiles = ignoreMissingFiles;
      this.confBroadcast = sc.broadcast(new SerializableConfiguration(_conf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.US).withZone(ZoneId.systemDefault());
      this.org$apache$spark$rdd$NewHadoopRDD$$jobTrackerId = dateTimeFormatter.format(Instant.now());
      this.jobId = new JobID(this.org$apache$spark$rdd$NewHadoopRDD$$jobTrackerId(), this.id());
      this.shouldCloneJobConf = this.sparkContext().conf().getBoolean("spark.hadoop.cloneConf", false);
      this.ignoreEmptySplits = BoxesRunTime.unboxToBoolean(this.sparkContext().conf().get(org.apache.spark.internal.config.package$.MODULE$.HADOOP_RDD_IGNORE_EMPTY_SPLITS()));
   }

   public NewHadoopRDD(final SparkContext sc, final Class inputFormatClass, final Class keyClass, final Class valueClass, final Configuration _conf) {
      this(sc, inputFormatClass, keyClass, valueClass, _conf, BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.IGNORE_CORRUPT_FILES())), BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.IGNORE_MISSING_FILES())));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class NewHadoopMapPartitionsWithSplitRDD$ implements Serializable {
      public static final NewHadoopMapPartitionsWithSplitRDD$ MODULE$ = new NewHadoopMapPartitionsWithSplitRDD$();

      public boolean $lessinit$greater$default$3() {
         return false;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NewHadoopMapPartitionsWithSplitRDD$.class);
      }
   }

   public static class NewHadoopMapPartitionsWithSplitRDD extends RDD {
      private final Function2 f;
      private final ClassTag evidence$3;
      private final Option partitioner;

      public Option partitioner() {
         return this.partitioner;
      }

      public Partition[] getPartitions() {
         return this.firstParent(this.evidence$3).partitions();
      }

      public Iterator compute(final Partition split, final TaskContext context) {
         NewHadoopPartition partition = (NewHadoopPartition)split;
         InputSplit inputSplit = (InputSplit)partition.serializableHadoopSplit().value();
         return (Iterator)this.f.apply(inputSplit, this.firstParent(this.evidence$3).iterator(split, context));
      }

      public NewHadoopMapPartitionsWithSplitRDD(final RDD prev, final Function2 f, final boolean preservesPartitioning, final ClassTag evidence$2, final ClassTag evidence$3) {
         super(prev, evidence$2);
         this.f = f;
         this.evidence$3 = evidence$3;
         this.partitioner = (Option)(preservesPartitioning ? this.firstParent(evidence$3).partitioner() : scala.None..MODULE$);
      }
   }
}
