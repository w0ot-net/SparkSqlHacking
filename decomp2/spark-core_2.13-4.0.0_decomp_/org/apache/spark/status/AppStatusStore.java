package org.apache.spark.status;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.internal.Logging;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationEnvironmentInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.ExecutorMetricsDistributions;
import org.apache.spark.status.api.v1.ExecutorPeakMetricsDistributions;
import org.apache.spark.status.api.v1.ExecutorStageSummary;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.status.api.v1.InputMetricDistributions;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.status.api.v1.OutputMetricDistributions;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.status.api.v1.ShufflePushReadMetricDistributions;
import org.apache.spark.status.api.v1.ShuffleReadMetricDistributions;
import org.apache.spark.status.api.v1.ShuffleWriteMetricDistributions;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.TaskData;
import org.apache.spark.status.api.v1.TaskMetricDistributions;
import org.apache.spark.status.api.v1.TaskSorting;
import org.apache.spark.storage.FallbackStorage$;
import org.apache.spark.ui.scope.RDDOperationGraph;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.KVStore;
import org.apache.spark.util.kvstore.KVStoreIterator;
import org.apache.spark.util.kvstore.KVStoreView;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\rc!\u0002'N\u0001=+\u0006\u0002\u0003/\u0001\u0005\u000b\u0007I\u0011\u00010\t\u0011\u001d\u0004!\u0011!Q\u0001\n}C\u0001\u0002\u001b\u0001\u0003\u0006\u0004%\t!\u001b\u0005\tc\u0002\u0011\t\u0011)A\u0005U\"A!\u000f\u0001BC\u0002\u0013\u00051\u000f\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003u\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\tI\u0001\u0001C\u0001\u0003\u0017Aq!!\b\u0001\t\u0003\ty\u0002C\u0004\u0002(\u0001!\t!!\u000b\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u00111\u000e\u0001\u0005\u0002\u00055\u0004bBA=\u0001\u0011\u0005\u00111\u0010\u0005\b\u0003\u001b\u0003A\u0011AAH\u0011\u001d\t\u0019\u000b\u0001C\u0005\u0003KCq!a+\u0001\t\u0013\ti\u000bC\u0004\u0002<\u0002!I!!0\t\u000f\u0005\u0005\u0007\u0001\"\u0003\u0002D\"9\u0011Q\u0019\u0001\u0005\u0002\u0005\u001d\u0007bBAj\u0001\u0011\u0005\u0011Q\u001b\u0005\b\u0003W\u0004A\u0011AAw\u0011\u001d\t9\u0010\u0001C\u0001\u0003sD\u0011B!\u000b\u0001#\u0003%\tAa\u000b\t\u0013\t\u0005\u0003!%A\u0005\u0002\t-\u0002\"\u0003B\"\u0001E\u0005I\u0011\u0001B#\u0011%\u0011I\u0005AI\u0001\n\u0003\u0011Y\u0005C\u0004\u0003P\u0001!\tA!\u0015\t\u0013\t}\u0003!%A\u0005\u0002\t-\u0002\"\u0003B1\u0001E\u0005I\u0011\u0001B&\u0011%\u0011\u0019\u0007AI\u0001\n\u0003\u0011Y\u0003C\u0005\u0003f\u0001\t\n\u0011\"\u0001\u0003F!9!q\r\u0001\u0005\u0002\t%\u0004b\u0002B7\u0001\u0011\u0005!q\u000e\u0005\n\u0005\u0007\u0003\u0011\u0013!C\u0001\u0005WA\u0011B!\"\u0001#\u0003%\tAa\u0013\t\u0013\t\u001d\u0005!%A\u0005\u0002\t-\u0002\"\u0003BE\u0001E\u0005I\u0011\u0001B#\u0011\u001d\u0011Y\t\u0001C\u0001\u0005\u001bCqAa%\u0001\t\u0003\u0011)\nC\u0004\u0003\"\u0002!\tAa)\t\u000f\tM\u0006\u0001\"\u0003\u00036\"9!1\u0018\u0001\u0005\n\tu\u0006b\u0002Ba\u0001\u0011\u0005!1\u0019\u0005\b\u0005\u0003\u0004A\u0011\u0001Bk\u0011\u001d\u0011\t\r\u0001C\u0001\u0005_D\u0011ba\u0001\u0001#\u0003%\tAa\u0013\t\u000f\u0005M\u0007\u0001\"\u0001\u0004\u0006!91Q\u0003\u0001\u0005\u0002\r]\u0001bBB\u0013\u0001\u0011\u00051q\u0005\u0005\n\u0007k\u0001\u0011\u0013!C\u0001\u0005WAqaa\u000e\u0001\t\u0003\u0019I\u0004C\u0004\u0004`\u0001!\ta!\u0019\t\u0013\rE\u0004!%A\u0005\u0002\t-\u0002\"CB:\u0001E\u0005I\u0011\u0001B&\u0011%\u0019)\bAI\u0001\n\u0003\u0011Y\u0003C\u0005\u0004x\u0001\t\n\u0011\"\u0001\u0003F!91\u0011\u0010\u0001\u0005\u0002\rm\u0004bBBF\u0001\u0011\u00051Q\u0012\u0005\b\u0007'\u0003A\u0011ABK\u0011\u001d\u0019y\n\u0001C\u0001\u0007CCqa!.\u0001\t\u0003\u00199\fC\u0004\u0004F\u0002!\taa2\t\u000f\rM\u0007\u0001\"\u0001\u0004V\"91Q\u001c\u0001\u0005\u0002\r}\u0007bBBt\u0001\u0011%1q\u001c\u0005\b\u0007S\u0004A\u0011ABv\u000f!\u0019i0\u0014E\u0001\u001f\u000e}ha\u0002'N\u0011\u0003yE\u0011\u0001\u0005\u0007}\u0012#\t\u0001b\u0004\t\u0013\u0011EAI1A\u0005\u0002\u0011M\u0001\u0002\u0003C\u000b\t\u0002\u0006I!!\"\t\u000f\u0011]A\t\"\u0001\u0005\u001a!IA\u0011\u0007#\u0012\u0002\u0013\u0005A1\u0007\u0005\n\to!\u0015\u0013!C\u0001\tsA\u0011\u0002\"\u0010E#\u0003%\t\u0001b\u0010\u0003\u001d\u0005\u0003\bo\u0015;biV\u001c8\u000b^8sK*\u0011ajT\u0001\u0007gR\fG/^:\u000b\u0005A\u000b\u0016!B:qCJ\\'B\u0001*T\u0003\u0019\t\u0007/Y2iK*\tA+A\u0002pe\u001e\u001c\"\u0001\u0001,\u0011\u0005]SV\"\u0001-\u000b\u0003e\u000bQa]2bY\u0006L!a\u0017-\u0003\r\u0005s\u0017PU3g\u0003\u0015\u0019Ho\u001c:f\u0007\u0001)\u0012a\u0018\t\u0003A\u0016l\u0011!\u0019\u0006\u0003E\u000e\fqa\u001b<ti>\u0014XM\u0003\u0002e\u001f\u0006!Q\u000f^5m\u0013\t1\u0017MA\u0004L-N#xN]3\u0002\rM$xN]3!\u0003!a\u0017n\u001d;f]\u0016\u0014X#\u00016\u0011\u0007][W.\u0003\u0002m1\n1q\n\u001d;j_:\u0004\"A\\8\u000e\u00035K!\u0001]'\u0003#\u0005\u0003\bo\u0015;biV\u001cH*[:uK:,'/A\u0005mSN$XM\\3sA\u0005I1\u000f^8sKB\u000bG\u000f[\u000b\u0002iB\u0019qk[;\u0011\u0005Y\\X\"A<\u000b\u0005aL\u0018AA5p\u0015\u0005Q\u0018\u0001\u00026bm\u0006L!\u0001`<\u0003\t\u0019KG.Z\u0001\u000bgR|'/\u001a)bi\"\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0005\u0002\u0002\u0005\r\u0011QAA\u0004!\tq\u0007\u0001C\u0003]\u000f\u0001\u0007q\fC\u0004i\u000fA\u0005\t\u0019\u00016\t\u000fI<\u0001\u0013!a\u0001i\u0006y\u0011\r\u001d9mS\u000e\fG/[8o\u0013:4w\u000e\u0006\u0002\u0002\u000eA!\u0011qBA\r\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011A\u0001<2\u0015\r\t9\"T\u0001\u0004CBL\u0017\u0002BA\u000e\u0003#\u0011q\"\u00119qY&\u001c\u0017\r^5p]&sgm\\\u0001\u0010K:4\u0018N]8o[\u0016tG/\u00138g_R\u0011\u0011\u0011\u0005\t\u0005\u0003\u001f\t\u0019#\u0003\u0003\u0002&\u0005E!AG!qa2L7-\u0019;j_:,eN^5s_:lWM\u001c;J]\u001a|\u0017a\u0005:fg>,(oY3Qe>4\u0017\u000e\\3J]\u001a|GCAA\u0016!\u0019\ti#!\u0010\u0002D9!\u0011qFA\u001d\u001d\u0011\t\t$a\u000e\u000e\u0005\u0005M\"bAA\u001b;\u00061AH]8pizJ\u0011!W\u0005\u0004\u0003wA\u0016a\u00029bG.\fw-Z\u0005\u0005\u0003\u007f\t\tEA\u0002TKFT1!a\u000fY!\u0011\ty!!\u0012\n\t\u0005\u001d\u0013\u0011\u0003\u0002\u0014%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&sgm\\\u0001\tU>\u00147\u000fT5tiR!\u0011QJA+!\u0019\ti#!\u0010\u0002PA!\u0011qBA)\u0013\u0011\t\u0019&!\u0005\u0003\u000f){'\rR1uC\"9\u0011qK\u0006A\u0002\u0005e\u0013\u0001C:uCR,8/Z:\u0011\r\u0005m\u0013qLA2\u001b\t\tiF\u0003\u0002es&!\u0011\u0011MA/\u0005\u0011a\u0015n\u001d;\u0011\t\u0005\u0015\u0014qM\u0007\u0002\u001f&\u0019\u0011\u0011N(\u0003%){'-\u0012=fGV$\u0018n\u001c8Ti\u0006$Xo]\u0001\u0004U>\u0014G\u0003BA(\u0003_Bq!!\u001d\r\u0001\u0004\t\u0019(A\u0003k_\nLE\rE\u0002X\u0003kJ1!a\u001eY\u0005\rIe\u000e^\u0001\u0015U>\u0014w+\u001b;i\u0003N\u001cxnY5bi\u0016$7+\u001d7\u0015\t\u0005u\u00141\u0012\t\b/\u0006}\u0014qJAB\u0013\r\t\t\t\u0017\u0002\u0007)V\u0004H.\u001a\u001a\u0011\t][\u0017Q\u0011\t\u0004/\u0006\u001d\u0015bAAE1\n!Aj\u001c8h\u0011\u001d\t\t(\u0004a\u0001\u0003g\nA\"\u001a=fGV$xN\u001d'jgR$B!!%\u0002\u001aB1\u0011QFA\u001f\u0003'\u0003B!a\u0004\u0002\u0016&!\u0011qSA\t\u0005=)\u00050Z2vi>\u00148+^7nCJL\bbBAN\u001d\u0001\u0007\u0011QT\u0001\u000bC\u000e$\u0018N^3P]2L\bcA,\u0002 &\u0019\u0011\u0011\u0015-\u0003\u000f\t{w\u000e\\3b]\u0006Y!/\u001a9mC\u000e,W\t_3d)\u0011\t\u0019*a*\t\u000f\u0005%v\u00021\u0001\u0002\u0014\u00061qN]5hS:\f1C]3qY\u0006\u001cW\r\u0012:jm\u0016\u0014xi\u0019+j[\u0016$\u0002\"a%\u00020\u0006M\u0016q\u0017\u0005\b\u0003c\u0003\u0002\u0019AAJ\u0003\u0019\u0019x.\u001e:dK\"9\u0011Q\u0017\tA\u0002\u0005\r\u0015a\u0003;pi\u0006dwi\u0019+j[\u0016Dq!!/\u0011\u0001\u0004\t\u0019)\u0001\u0007u_R\fG.\u00119q)&lW-A\u0007fqR\u0014\u0018m\u0019;HGRKW.\u001a\u000b\u0005\u0003\u0007\u000by\fC\u0004\u00022F\u0001\r!a%\u0002\u001d\u0015DHO]1di\u0006\u0003\b\u000fV5nKV\u0011\u00111Q\u0001\u0019[&\u001c8-\u001a7mC:,w.^:Qe>\u001cWm]:MSN$H\u0003BAe\u0003#\u0004b!!\f\u0002>\u0005-\u0007\u0003BA\b\u0003\u001bLA!a4\u0002\u0012\tq\u0001K]8dKN\u001c8+^7nCJL\bbBAN'\u0001\u0007\u0011QT\u0001\u0010Kb,7-\u001e;peN+X.\\1ssR!\u00111SAl\u0011\u001d\tI\u000e\u0006a\u0001\u00037\f!\"\u001a=fGV$xN]%e!\u0011\ti.!:\u000f\t\u0005}\u0017\u0011\u001d\t\u0004\u0003cA\u0016bAAr1\u00061\u0001K]3eK\u001aLA!a:\u0002j\n11\u000b\u001e:j]\u001eT1!a9Y\u00031\t7\r^5wKN#\u0018mZ3t)\t\ty\u000f\u0005\u0004\u0002.\u0005u\u0012\u0011\u001f\t\u0005\u0003\u001f\t\u00190\u0003\u0003\u0002v\u0006E!!C*uC\u001e,G)\u0019;b\u0003%\u0019H/Y4f\u0019&\u001cH\u000f\u0006\u0007\u0002p\u0006m(Q\u0001B\u0005\u0005\u001b\u0011i\u0002C\u0004\u0002XY\u0001\r!!@\u0011\r\u0005m\u0013qLA\u0000!\u0011\tyA!\u0001\n\t\t\r\u0011\u0011\u0003\u0002\f'R\fw-Z*uCR,8\u000fC\u0005\u0003\bY\u0001\n\u00111\u0001\u0002\u001e\u00069A-\u001a;bS2\u001c\b\"\u0003B\u0006-A\u0005\t\u0019AAO\u000359\u0018\u000e\u001e5Tk6l\u0017M]5fg\"I!q\u0002\f\u0011\u0002\u0003\u0007!\u0011C\u0001\u0012k:\u001cxN\u001d;fIF+\u0018M\u001c;jY\u0016\u001c\b#B,\u0003\u0014\t]\u0011b\u0001B\u000b1\n)\u0011I\u001d:bsB\u0019qK!\u0007\n\u0007\tm\u0001L\u0001\u0004E_V\u0014G.\u001a\u0005\n\u0005?1\u0002\u0013!a\u0001\u0005C\t!\u0002^1tWN#\u0018\r^;t!\u0019\tY&a\u0018\u0003$A!\u0011q\u0002B\u0013\u0013\u0011\u00119#!\u0005\u0003\u0015Q\u000b7o[*uCR,8/A\nti\u0006<W\rT5ti\u0012\"WMZ1vYR$#'\u0006\u0002\u0003.)\"\u0011Q\u0014B\u0018W\t\u0011\t\u0004\u0005\u0003\u00034\tuRB\u0001B\u001b\u0015\u0011\u00119D!\u000f\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B\u001e1\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t}\"Q\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017aE:uC\u001e,G*[:uI\u0011,g-Y;mi\u0012\u001a\u0014aE:uC\u001e,G*[:uI\u0011,g-Y;mi\u0012\"TC\u0001B$U\u0011\u0011\tBa\f\u0002'M$\u0018mZ3MSN$H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\t5#\u0006\u0002B\u0011\u0005_\t\u0011b\u001d;bO\u0016$\u0015\r^1\u0015\u0019\u0005=(1\u000bB,\u00053\u0012YF!\u0018\t\u000f\tU3\u00041\u0001\u0002t\u000591\u000f^1hK&#\u0007\"\u0003B\u00047A\u0005\t\u0019AAO\u0011%\u0011yb\u0007I\u0001\u0002\u0004\u0011\t\u0003C\u0005\u0003\fm\u0001\n\u00111\u0001\u0002\u001e\"I!qB\u000e\u0011\u0002\u0003\u0007!\u0011C\u0001\u0014gR\fw-\u001a#bi\u0006$C-\u001a4bk2$HEM\u0001\u0014gR\fw-\u001a#bi\u0006$C-\u001a4bk2$HeM\u0001\u0014gR\fw-\u001a#bi\u0006$C-\u001a4bk2$H\u0005N\u0001\u0014gR\fw-\u001a#bi\u0006$C-\u001a4bk2$H%N\u0001\u0011Y\u0006\u001cHo\u0015;bO\u0016\fE\u000f^3naR$B!!=\u0003l!9!Q\u000b\u0011A\u0002\u0005M\u0014\u0001D:uC\u001e,\u0017\t\u001e;f[B$HC\u0004B9\u0005k\u00129Ha\u001f\u0003~\t}$\u0011\u0011\t\b/\u0006}\u0014\u0011\u001fB:!\u0019\ti#!\u0010\u0002t!9!QK\u0011A\u0002\u0005M\u0004b\u0002B=C\u0001\u0007\u00111O\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0011%\u00119!\tI\u0001\u0002\u0004\ti\nC\u0005\u0003 \u0005\u0002\n\u00111\u0001\u0003\"!I!1B\u0011\u0011\u0002\u0003\u0007\u0011Q\u0014\u0005\n\u0005\u001f\t\u0003\u0013!a\u0001\u0005#\tac\u001d;bO\u0016\fE\u000f^3naR$C-\u001a4bk2$HeM\u0001\u0017gR\fw-Z!ui\u0016l\u0007\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%i\u000512\u000f^1hK\u0006#H/Z7qi\u0012\"WMZ1vYR$S'\u0001\fti\u0006<W-\u0011;uK6\u0004H\u000f\n3fM\u0006,H\u000e\u001e\u00137\u0003%!\u0018m]6D_VtG\u000f\u0006\u0004\u0002\u0006\n=%\u0011\u0013\u0005\b\u0005+2\u0003\u0019AA:\u0011\u001d\u0011IH\na\u0001\u0003g\nq\u0002\\8dC2LG/_*v[6\f'/\u001f\u000b\u0007\u0005/\u0013iJa(\u0011\u0011\u0005u'\u0011TAn\u0003\u000bKAAa'\u0002j\n\u0019Q*\u00199\t\u000f\tUs\u00051\u0001\u0002t!9!\u0011P\u0014A\u0002\u0005M\u0014a\u0003;bg.\u001cV/\\7bef$\u0002B!*\u0003.\n=&\u0011\u0017\t\u0005/.\u00149\u000b\u0005\u0003\u0002\u0010\t%\u0016\u0002\u0002BV\u0003#\u0011q\u0003V1tW6+GO]5d\t&\u001cHO]5ckRLwN\\:\t\u000f\tU\u0003\u00061\u0001\u0002t!9!\u0011\u0010\u0015A\u0002\u0005M\u0004b\u0002B\bQ\u0001\u0007!\u0011C\u0001\u0014g\"|W\u000f\u001c3DC\u000eDW-U;b]RLG.\u001a\u000b\u0005\u0003;\u00139\fC\u0004\u0003:&\u0002\rAa\u0006\u0002\u0003E\f\u0001#];b]RLG.\u001a+p'R\u0014\u0018N\\4\u0015\t\u0005m'q\u0018\u0005\b\u0005sS\u0003\u0019\u0001B\f\u0003!!\u0018m]6MSN$H\u0003\u0003Bc\u0005\u001b\u0014yM!5\u0011\r\u00055\u0012Q\bBd!\u0011\tyA!3\n\t\t-\u0017\u0011\u0003\u0002\t)\u0006\u001c8\u000eR1uC\"9!QK\u0016A\u0002\u0005M\u0004b\u0002B=W\u0001\u0007\u00111\u000f\u0005\b\u0005'\\\u0003\u0019AA:\u0003!i\u0017\r\u001f+bg.\u001cHC\u0004Bc\u0005/\u0014INa7\u0003`\n\r(Q\u001e\u0005\b\u0005+b\u0003\u0019AA:\u0011\u001d\u0011I\b\fa\u0001\u0003gBqA!8-\u0001\u0004\t\u0019(\u0001\u0004pM\u001a\u001cX\r\u001e\u0005\b\u0005Cd\u0003\u0019AA:\u0003\u0019aWM\\4uQ\"9!Q\u001d\u0017A\u0002\t\u001d\u0018AB:peR\u0014\u0015\u0010\u0005\u0003\u0002\u0010\t%\u0018\u0002\u0002Bv\u0003#\u00111\u0002V1tWN{'\u000f^5oO\"9\u0011q\u000b\u0017A\u0002\t\u0005B\u0003\u0005Bc\u0005c\u0014\u0019P!>\u0003x\ne(Q`B\u0001\u0011\u001d\u0011)&\fa\u0001\u0003gBqA!\u001f.\u0001\u0004\t\u0019\bC\u0004\u0003^6\u0002\r!a\u001d\t\u000f\t\u0005X\u00061\u0001\u0002t!9!Q]\u0017A\u0002\tm\b\u0003B,l\u00037DqAa@.\u0001\u0004\ti*A\u0005bg\u000e,g\u000eZ5oO\"I\u0011qK\u0017\u0011\u0002\u0003\u0007!\u0011E\u0001\u0013i\u0006\u001c8\u000eT5ti\u0012\"WMZ1vYR$s\u0007\u0006\u0004\u0004\b\r=1\u0011\u0003\t\t\u0003;\u0014I*a7\u0004\nA!\u0011qBB\u0006\u0013\u0011\u0019i!!\u0005\u0003)\u0015CXmY;u_J\u001cF/Y4f'VlW.\u0019:z\u0011\u001d\u0011)f\fa\u0001\u0003gBqaa\u00050\u0001\u0004\t\u0019(A\u0005biR,W\u000e\u001d;JI\u0006\u00112\u000f]3dk2\fG/[8o'VlW.\u0019:z)\u0019\u0019Ib!\t\u0004$A!qk[B\u000e!\u0011\tya!\b\n\t\r}\u0011\u0011\u0003\u0002\u0018'B,7-\u001e7bi&|gn\u0015;bO\u0016\u001cV/\\7befDqA!\u00161\u0001\u0004\t\u0019\bC\u0004\u0004\u0014A\u0002\r!a\u001d\u0002\u000fI$G\rT5tiR!1\u0011FB\u0019!\u0019\ti#!\u0010\u0004,A!\u0011qBB\u0017\u0013\u0011\u0019y#!\u0005\u0003\u001dI#Ei\u0015;pe\u0006<W-\u00138g_\"I11G\u0019\u0011\u0002\u0003\u0007\u0011QT\u0001\u000bG\u0006\u001c\u0007.\u001a3P]2L\u0018!\u0005:eI2K7\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%c\u0005A\u0011m](qi&|g.\u0006\u0003\u0004<\r\rC\u0003BB\u001f\u0007+\u0002BaV6\u0004@A!1\u0011IB\"\u0019\u0001!qa!\u00124\u0005\u0004\u00199EA\u0001U#\u0011\u0019Iea\u0014\u0011\u0007]\u001bY%C\u0002\u0004Na\u0013qAT8uQ&tw\rE\u0002X\u0007#J1aa\u0015Y\u0005\r\te.\u001f\u0005\t\u0007/\u001aD\u00111\u0001\u0004Z\u0005\u0011aM\u001c\t\u0006/\u000em3qH\u0005\u0004\u0007;B&\u0001\u0003\u001fcs:\fW.\u001a \u0002\u00199,wo\u0015;bO\u0016$\u0015\r^1\u0015\u0019\u0005E81MB4\u0007W\u001aiga\u001c\t\u000f\r\u0015D\u00071\u0001\u0002r\u0006)1\u000f^1hK\"I1\u0011\u000e\u001b\u0011\u0002\u0003\u0007\u0011QT\u0001\u000bo&$\b\u000eR3uC&d\u0007\"\u0003B\u0010iA\u0005\t\u0019\u0001B\u0011\u0011%\u0011Y\u0001\u000eI\u0001\u0002\u0004\ti\nC\u0005\u0003\u0010Q\u0002\n\u00111\u0001\u0003\u0012\u00051b.Z<Ti\u0006<W\rR1uC\u0012\"WMZ1vYR$#'\u0001\foK^\u001cF/Y4f\t\u0006$\u0018\r\n3fM\u0006,H\u000e\u001e\u00134\u0003YqWm^*uC\u001e,G)\u0019;bI\u0011,g-Y;mi\u0012\"\u0014A\u00068foN#\u0018mZ3ECR\fG\u0005Z3gCVdG\u000fJ\u001b\u0002)M$\u0018mZ3Fq\u0016\u001cW\u000f^8s'VlW.\u0019:z)!\u0019ih!\"\u0004\b\u000e%\u0005\u0003B,l\u0007\u007f\u0002B!a\u0004\u0004\u0002&!11QA\t\u0005q)\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cH)[:ue&\u0014W\u000f^5p]NDqA!\u0016:\u0001\u0004\t\u0019\bC\u0004\u0003ze\u0002\r!a\u001d\t\u000f\t=\u0011\b1\u0001\u0003\u0012\u0005\u0019!\u000f\u001a3\u0015\t\r-2q\u0012\u0005\b\u0007#S\u0004\u0019AA:\u0003\u0015\u0011H\rZ%e\u0003A\u0019HO]3b[\ncwnY6t\u0019&\u001cH\u000f\u0006\u0002\u0004\u0018B1\u0011QFA\u001f\u00073\u00032A\\BN\u0013\r\u0019i*\u0014\u0002\u0010'R\u0014X-Y7CY>\u001c7\u000eR1uC\u00061r\u000e]3sCRLwN\\$sCBDgi\u001c:Ti\u0006<W\r\u0006\u0003\u0004$\u000eM\u0006\u0003BBS\u0007_k!aa*\u000b\t\r%61V\u0001\u0006g\u000e|\u0007/\u001a\u0006\u0004\u0007[{\u0015AA;j\u0013\u0011\u0019\tla*\u0003#I#Ei\u00149fe\u0006$\u0018n\u001c8He\u0006\u0004\b\u000eC\u0004\u0003Vq\u0002\r!a\u001d\u0002)=\u0004XM]1uS>twI]1qQ\u001a{'OS8c)\u0011\u0019Ila1\u0011\r\rm6\u0011YBR\u001b\t\u0019iLC\u0002\u0004@b\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\tyd!0\t\u000f\u0005ET\b1\u0001\u0002t\u0005!\u0001o\\8m)\u0011\u0019Ima4\u0011\u00079\u001cY-C\u0002\u0004N6\u0013\u0001\u0002U8pY\u0012\u000bG/\u0019\u0005\b\u0007#t\u0004\u0019AAn\u0003\u0011q\u0017-\\3\u0002\u0015\u0005\u0004\boU;n[\u0006\u0014\u0018\u0010\u0006\u0002\u0004XB\u0019an!7\n\u0007\rmWJ\u0001\u0006BaB\u001cV/\\7bef\fQa\u00197pg\u0016$\"a!9\u0011\u0007]\u001b\u0019/C\u0002\u0004fb\u0013A!\u00168ji\u0006\u00012\r\\3b]V\u00038\u000b^8sKB\u000bG\u000f[\u0001\u0016G>t7\u000f\u001e:vGR$\u0016m]6ECR\fG*[:u)\u0011\u0011)m!<\t\u000f\r=(\t1\u0001\u0004r\u0006\u0019B/Y:l\t\u0006$\u0018m\u0016:baB,'/\u0013;feB1\u0011QFBz\u0007oLAa!>\u0002B\tA\u0011\n^3sC\ndW\rE\u0002o\u0007sL1aa?N\u0005=!\u0016m]6ECR\fwK]1qa\u0016\u0014\u0018AD!qaN#\u0018\r^;t'R|'/\u001a\t\u0003]\u0012\u001bB\u0001\u0012,\u0005\u0004A!AQ\u0001C\u0006\u001b\t!9AC\u0002\u0005\n=\u000b\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\t\u001b!9AA\u0004M_\u001e<\u0017N\\4\u0015\u0005\r}\u0018aD\"V%J+e\nV0W\u000bJ\u001b\u0016j\u0014(\u0016\u0005\u0005\u0015\u0015\u0001E\"V%J+e\nV0W\u000bJ\u001b\u0016j\u0014(!\u0003=\u0019'/Z1uK2Kg/Z*u_J,GCBA\u0001\t7!)\u0003C\u0004\u0005\u001e!\u0003\r\u0001b\b\u0002\t\r|gN\u001a\t\u0005\u0003K\"\t#C\u0002\u0005$=\u0013\u0011b\u00159be.\u001cuN\u001c4\t\u0013\u0011\u001d\u0002\n%AA\u0002\u0011%\u0012aD1qaN#\u0018\r^;t'>,(oY3\u0011\t][G1\u0006\t\u0004]\u00125\u0012b\u0001C\u0018\u001b\ny\u0011\t\u001d9Ti\u0006$Xo]*pkJ\u001cW-A\rde\u0016\fG/\u001a'jm\u0016\u001cFo\u001c:fI\u0011,g-Y;mi\u0012\u0012TC\u0001C\u001bU\u0011!ICa\f\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t!YDK\u0002k\u0005_\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTC\u0001C!U\r!(q\u0006"
)
public class AppStatusStore {
   private final KVStore store;
   private final Option listener;
   private final Option storePath;

   public static Option $lessinit$greater$default$3() {
      return AppStatusStore$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return AppStatusStore$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option createLiveStore$default$2() {
      return AppStatusStore$.MODULE$.createLiveStore$default$2();
   }

   public static AppStatusStore createLiveStore(final SparkConf conf, final Option appStatusSource) {
      return AppStatusStore$.MODULE$.createLiveStore(conf, appStatusSource);
   }

   public static long CURRENT_VERSION() {
      return AppStatusStore$.MODULE$.CURRENT_VERSION();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return AppStatusStore$.MODULE$.LogStringContext(sc);
   }

   public KVStore store() {
      return this.store;
   }

   public Option listener() {
      return this.listener;
   }

   public Option storePath() {
      return this.storePath;
   }

   public ApplicationInfo applicationInfo() {
      try {
         return (ApplicationInfo)Utils$.MODULE$.tryWithResource(() -> this.store().view(ApplicationInfoWrapper.class).max(1L).closeableIterator(), (it) -> ((ApplicationInfoWrapper)it.next()).info());
      } catch (NoSuchElementException var1) {
         throw new NoSuchElementException("Failed to get the application information. If you are starting up Spark, please wait a while until it's ready.");
      }
   }

   public ApplicationEnvironmentInfo environmentInfo() {
      Class klass = ApplicationEnvironmentInfoWrapper.class;
      return ((ApplicationEnvironmentInfoWrapper)this.store().read(klass, klass.getName())).info();
   }

   public Seq resourceProfileInfo() {
      return KVUtils$.MODULE$.mapToSeq(this.store().view(ResourceProfileWrapper.class), (x$1) -> x$1.rpInfo());
   }

   public Seq jobsList(final List statuses) {
      Seq it = KVUtils$.MODULE$.mapToSeq(this.store().view(JobDataWrapper.class).reverse(), (x$2) -> x$2.info());
      return statuses != null && !statuses.isEmpty() ? (Seq)it.filter((job) -> BoxesRunTime.boxToBoolean($anonfun$jobsList$2(statuses, job))) : it;
   }

   public JobData job(final int jobId) {
      return ((JobDataWrapper)this.store().read(JobDataWrapper.class, BoxesRunTime.boxToInteger(jobId))).info();
   }

   public Tuple2 jobWithAssociatedSql(final int jobId) {
      JobDataWrapper data = (JobDataWrapper)this.store().read(JobDataWrapper.class, BoxesRunTime.boxToInteger(jobId));
      return new Tuple2(data.info(), data.sqlExecutionId());
   }

   public Seq executorList(final boolean activeOnly) {
      KVStoreView base = this.store().view(ExecutorSummaryWrapper.class);
      KVStoreView filtered = activeOnly ? base.index("active").reverse().first(BoxesRunTime.boxToBoolean(true)).last(BoxesRunTime.boxToBoolean(true)) : base;
      return (Seq)((IterableOps)KVUtils$.MODULE$.mapToSeq(filtered, (x$3) -> x$3.info()).filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$executorList$2(x$4)))).map((origin) -> this.replaceExec(origin));
   }

   private ExecutorSummary replaceExec(final ExecutorSummary origin) {
      String var10000 = origin.id();
      String var2 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
      if (var10000 == null) {
         if (var2 == null) {
            return this.replaceDriverGcTime(origin, this.extractGcTime(origin), this.extractAppTime());
         }
      } else if (var10000.equals(var2)) {
         return this.replaceDriverGcTime(origin, this.extractGcTime(origin), this.extractAppTime());
      }

      return origin;
   }

   private ExecutorSummary replaceDriverGcTime(final ExecutorSummary source, final Option totalGcTime, final Option totalAppTime) {
      return new ExecutorSummary(source.id(), source.hostPort(), source.isActive(), source.rddBlocks(), source.memoryUsed(), source.diskUsed(), source.totalCores(), source.maxTasks(), source.activeTasks(), source.failedTasks(), source.completedTasks(), source.totalTasks(), BoxesRunTime.unboxToLong(totalAppTime.getOrElse((JFunction0.mcJ.sp)() -> source.totalDuration())), BoxesRunTime.unboxToLong(totalGcTime.getOrElse((JFunction0.mcJ.sp)() -> source.totalGCTime())), source.totalInputBytes(), source.totalShuffleRead(), source.totalShuffleWrite(), source.isBlacklisted(), source.maxMemory(), source.addTime(), source.removeTime(), source.removeReason(), source.executorLogs(), source.memoryMetrics(), source.blacklistedInStages(), source.peakMemoryMetrics(), source.attributes(), source.resources(), source.resourceProfileId(), source.isExcluded(), source.excludedInStages());
   }

   private Option extractGcTime(final ExecutorSummary source) {
      return source.peakMemoryMetrics().map((x$5) -> BoxesRunTime.boxToLong($anonfun$extractGcTime$1(x$5)));
   }

   private Option extractAppTime() {
      long startTime = 0L;
      long endTime = 0L;

      try {
         ApplicationInfo appInfo = this.applicationInfo();
         startTime = ((ApplicationAttemptInfo)appInfo.attempts().head()).startTime().getTime();
         endTime = ((ApplicationAttemptInfo)appInfo.attempts().head()).endTime().getTime();
      } catch (NoSuchElementException var6) {
      }

      if (endTime == 0L) {
         return .MODULE$;
      } else {
         return endTime < 0L ? scala.Option..MODULE$.apply(BoxesRunTime.boxToLong(System.currentTimeMillis() - startTime)) : scala.Option..MODULE$.apply(BoxesRunTime.boxToLong(endTime - startTime));
      }
   }

   public Seq miscellaneousProcessList(final boolean activeOnly) {
      KVStoreView base = this.store().view(ProcessSummaryWrapper.class);
      KVStoreView filtered = activeOnly ? base.index("active").reverse().first(BoxesRunTime.boxToBoolean(true)).last(BoxesRunTime.boxToBoolean(true)) : base;
      return KVUtils$.MODULE$.mapToSeq(filtered, (x$6) -> x$6.info());
   }

   public ExecutorSummary executorSummary(final String executorId) {
      return ((ExecutorSummaryWrapper)this.store().read(ExecutorSummaryWrapper.class, executorId)).info();
   }

   public Seq activeStages() {
      return (Seq)this.listener().map((x$7) -> x$7.activeStages()).getOrElse(() -> scala.collection.immutable.Nil..MODULE$);
   }

   public Seq stageList(final List statuses, final boolean details, final boolean withSummaries, final double[] unsortedQuantiles, final List taskStatus) {
      double[] quantiles = (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps(unsortedQuantiles), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      Seq it = KVUtils$.MODULE$.mapToSeq(this.store().view(StageDataWrapper.class).reverse(), (x$8) -> x$8.info());
      Seq ret = statuses != null && !statuses.isEmpty() ? (Seq)it.filter((s) -> BoxesRunTime.boxToBoolean($anonfun$stageList$2(statuses, s))) : it;
      return (Seq)ret.map((s) -> this.newStageData(s, details, taskStatus, withSummaries, quantiles));
   }

   public boolean stageList$default$2() {
      return false;
   }

   public boolean stageList$default$3() {
      return false;
   }

   public double[] stageList$default$4() {
      return (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double());
   }

   public List stageList$default$5() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.collection.immutable.Nil..MODULE$).asJava();
   }

   public Seq stageData(final int stageId, final boolean details, final List taskStatus, final boolean withSummaries, final double[] unsortedQuantiles) {
      return KVUtils$.MODULE$.mapToSeq(this.store().view(StageDataWrapper.class).index("stageId").first(BoxesRunTime.boxToInteger(stageId)).last(BoxesRunTime.boxToInteger(stageId)), (s) -> this.newStageData(s.info(), details, taskStatus, withSummaries, unsortedQuantiles));
   }

   public boolean stageData$default$2() {
      return false;
   }

   public List stageData$default$3() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.collection.immutable.Nil..MODULE$).asJava();
   }

   public boolean stageData$default$4() {
      return false;
   }

   public double[] stageData$default$5() {
      return (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double());
   }

   public StageData lastStageAttempt(final int stageId) {
      KVStoreIterator it = this.store().view(StageDataWrapper.class).index("stageId").reverse().first(BoxesRunTime.boxToInteger(stageId)).last(BoxesRunTime.boxToInteger(stageId)).closeableIterator();

      try {
         if (!it.hasNext()) {
            throw new NoSuchElementException("No stage with id " + stageId);
         }

         ((StageDataWrapper)it.next()).info();
      } finally {
         it.close();
      }

      return it;
   }

   public Tuple2 stageAttempt(final int stageId, final int stageAttemptId, final boolean details, final List taskStatus, final boolean withSummaries, final double[] unsortedQuantiles) {
      int[] stageKey = new int[]{stageId, stageAttemptId};
      StageDataWrapper stageDataWrapper = (StageDataWrapper)this.store().read(StageDataWrapper.class, stageKey);
      StageData stage = this.newStageData(stageDataWrapper.info(), details, taskStatus, withSummaries, unsortedQuantiles);
      return new Tuple2(stage, stageDataWrapper.jobIds().toSeq());
   }

   public boolean stageAttempt$default$3() {
      return false;
   }

   public List stageAttempt$default$4() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.collection.immutable.Nil..MODULE$).asJava();
   }

   public boolean stageAttempt$default$5() {
      return false;
   }

   public double[] stageAttempt$default$6() {
      return (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double());
   }

   public long taskCount(final int stageId, final int stageAttemptId) {
      return this.store().count(TaskDataWrapper.class, "stage", new int[]{stageId, stageAttemptId});
   }

   public Map localitySummary(final int stageId, final int stageAttemptId) {
      return ((StageDataWrapper)this.store().read(StageDataWrapper.class, new int[]{stageId, stageAttemptId})).locality();
   }

   public Option taskSummary(final int stageId, final int stageAttemptId, final double[] unsortedQuantiles) {
      int[] stageKey = new int[]{stageId, stageAttemptId};
      ArraySeq quantiles = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps(unsortedQuantiles), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)).toImmutableArraySeq();
      long count = BoxesRunTime.unboxToLong(Utils$.MODULE$.tryWithResource(() -> this.store().view(TaskDataWrapper.class).parent(stageKey).index("ert").first(BoxesRunTime.boxToLong(0L)).closeableIterator(), (it) -> BoxesRunTime.boxToLong($anonfun$taskSummary$2(it))));
      if (count <= 0L) {
         return .MODULE$;
      } else {
         ArraySeq cachedQuantiles = (ArraySeq)((StrictOptimizedIterableOps)quantiles.filter((JFunction1.mcZD.sp)(q) -> this.shouldCacheQuantile(q))).flatMap((q) -> $anonfun$taskSummary$4(this, stageId, stageAttemptId, count, BoxesRunTime.unboxToDouble(q)));
         if (cachedQuantiles.size() == quantiles.size()) {
            TaskMetricDistributions distributions = new TaskMetricDistributions(quantiles, toValues$1((x$10) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$7(x$10)), cachedQuantiles), toValues$1((x$11) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$8(x$11)), cachedQuantiles), toValues$1((x$12) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$9(x$12)), cachedQuantiles), toValues$1((x$13) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$10(x$13)), cachedQuantiles), toValues$1((x$14) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$11(x$14)), cachedQuantiles), toValues$1((x$15) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$12(x$15)), cachedQuantiles), toValues$1((x$16) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$13(x$16)), cachedQuantiles), toValues$1((x$17) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$14(x$17)), cachedQuantiles), toValues$1((x$18) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$15(x$18)), cachedQuantiles), toValues$1((x$19) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$16(x$19)), cachedQuantiles), toValues$1((x$20) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$17(x$20)), cachedQuantiles), toValues$1((x$21) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$18(x$21)), cachedQuantiles), toValues$1((x$22) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$19(x$22)), cachedQuantiles), new InputMetricDistributions(toValues$1((x$23) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$20(x$23)), cachedQuantiles), toValues$1((x$24) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$21(x$24)), cachedQuantiles)), new OutputMetricDistributions(toValues$1((x$25) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$22(x$25)), cachedQuantiles), toValues$1((x$26) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$23(x$26)), cachedQuantiles)), new ShuffleReadMetricDistributions(toValues$1((x$27) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$24(x$27)), cachedQuantiles), toValues$1((x$28) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$25(x$28)), cachedQuantiles), toValues$1((x$29) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$26(x$29)), cachedQuantiles), toValues$1((x$30) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$27(x$30)), cachedQuantiles), toValues$1((x$31) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$28(x$31)), cachedQuantiles), toValues$1((x$32) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$29(x$32)), cachedQuantiles), toValues$1((x$33) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$30(x$33)), cachedQuantiles), toValues$1((x$34) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$31(x$34)), cachedQuantiles), toValues$1((x$35) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$32(x$35)), cachedQuantiles), new ShufflePushReadMetricDistributions(toValues$1((x$36) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$33(x$36)), cachedQuantiles), toValues$1((x$37) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$34(x$37)), cachedQuantiles), toValues$1((x$38) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$35(x$38)), cachedQuantiles), toValues$1((x$39) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$36(x$39)), cachedQuantiles), toValues$1((x$40) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$37(x$40)), cachedQuantiles), toValues$1((x$41) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$38(x$41)), cachedQuantiles), toValues$1((x$42) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$39(x$42)), cachedQuantiles), toValues$1((x$43) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$40(x$43)), cachedQuantiles), toValues$1((x$44) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$41(x$44)), cachedQuantiles))), new ShuffleWriteMetricDistributions(toValues$1((x$45) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$42(x$45)), cachedQuantiles), toValues$1((x$46) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$43(x$46)), cachedQuantiles), toValues$1((x$47) -> BoxesRunTime.boxToDouble($anonfun$taskSummary$44(x$47)), cachedQuantiles)));
            return new Some(distributions);
         } else {
            ArraySeq indices = quantiles.map((JFunction1.mcJD.sp)(q) -> scala.math.package..MODULE$.min((long)(q * (double)count), count - 1L));
            TaskMetricDistributions computedQuantiles = new TaskMetricDistributions(quantiles, this.scanTasks$1("dur", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$49(t)), stageKey, indices), this.scanTasks$1("des", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$50(t)), stageKey, indices), this.scanTasks$1("dct", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$51(t)), stageKey, indices), this.scanTasks$1("ert", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$52(t)), stageKey, indices), this.scanTasks$1("ect", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$53(t)), stageKey, indices), this.scanTasks$1("rs", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$54(t)), stageKey, indices), this.scanTasks$1("gc", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$55(t)), stageKey, indices), this.scanTasks$1("rst", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$56(t)), stageKey, indices), this.scanTasks$1("grt", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$57(t)), stageKey, indices), this.scanTasks$1("dly", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$58(t)), stageKey, indices), this.scanTasks$1("pem", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$59(t)), stageKey, indices), this.scanTasks$1("mbs", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$60(t)), stageKey, indices), this.scanTasks$1("dbs", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$61(t)), stageKey, indices), new InputMetricDistributions(this.scanTasks$1("is", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$62(t)), stageKey, indices), this.scanTasks$1("ir", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$63(t)), stageKey, indices)), new OutputMetricDistributions(this.scanTasks$1("os", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$64(t)), stageKey, indices), this.scanTasks$1("or", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$65(t)), stageKey, indices)), new ShuffleReadMetricDistributions(this.scanTasks$1("stby", (m) -> BoxesRunTime.boxToLong($anonfun$taskSummary$66(m)), stageKey, indices), this.scanTasks$1("srr", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$67(t)), stageKey, indices), this.scanTasks$1("srbl", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$68(t)), stageKey, indices), this.scanTasks$1("slbl", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$69(t)), stageKey, indices), this.scanTasks$1("srt", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$70(t)), stageKey, indices), this.scanTasks$1("srby", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$71(t)), stageKey, indices), this.scanTasks$1("srbd", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$72(t)), stageKey, indices), this.scanTasks$1("stbl", (m) -> BoxesRunTime.boxToLong($anonfun$taskSummary$73(m)), stageKey, indices), this.scanTasks$1("srrd", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$74(t)), stageKey, indices), new ShufflePushReadMetricDistributions(this.scanTasks$1("spcmbc", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$75(t)), stageKey, indices), this.scanTasks$1("spmffc", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$76(t)), stageKey, indices), this.scanTasks$1("spmrb", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$77(t)), stageKey, indices), this.scanTasks$1("spmlb", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$78(t)), stageKey, indices), this.scanTasks$1("spmrc", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$79(t)), stageKey, indices), this.scanTasks$1("spmlc", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$80(t)), stageKey, indices), this.scanTasks$1("spmrr", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$81(t)), stageKey, indices), this.scanTasks$1("spmlr", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$82(t)), stageKey, indices), this.scanTasks$1("spmrrd", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$83(t)), stageKey, indices))), new ShuffleWriteMetricDistributions(this.scanTasks$1("sws", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$84(t)), stageKey, indices), this.scanTasks$1("swr", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$85(t)), stageKey, indices), this.scanTasks$1("swt", (t) -> BoxesRunTime.boxToLong($anonfun$taskSummary$86(t)), stageKey, indices)));
            ((IterableOnceOps)((IterableOps)computedQuantiles.quantiles().zipWithIndex()).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$taskSummary$87(this, quantiles, x0$1)))).foreach((x0$2) -> {
               $anonfun$taskSummary$88(this, stageId, stageAttemptId, count, computedQuantiles, x0$2);
               return BoxedUnit.UNIT;
            });
            return new Some(computedQuantiles);
         }
      }
   }

   private boolean shouldCacheQuantile(final double q) {
      return scala.math.package..MODULE$.round(q * (double)100) % 5L == 0L;
   }

   private String quantileToString(final double q) {
      return Long.toString(scala.math.package..MODULE$.round(q * (double)100));
   }

   public Seq taskList(final int stageId, final int stageAttemptId, final int maxTasks) {
      int[] stageKey = new int[]{stageId, stageAttemptId};
      Seq taskDataWrapperSeq = KVUtils$.MODULE$.viewToSeq(this.store().view(TaskDataWrapper.class).index("stage").first(stageKey).last(stageKey).reverse().max((long)maxTasks));
      return (Seq)this.constructTaskDataList(taskDataWrapperSeq).reverse();
   }

   public Seq taskList(final int stageId, final int stageAttemptId, final int offset, final int length, final TaskSorting sortBy, final List statuses) {
      Tuple2 var10000;
      if (TaskSorting.ID.equals(sortBy)) {
         var10000 = new Tuple2(.MODULE$, BoxesRunTime.boxToBoolean(true));
      } else if (TaskSorting.INCREASING_RUNTIME.equals(sortBy)) {
         var10000 = new Tuple2(new Some("ert"), BoxesRunTime.boxToBoolean(true));
      } else {
         if (!TaskSorting.DECREASING_RUNTIME.equals(sortBy)) {
            throw new MatchError(sortBy);
         }

         var10000 = new Tuple2(new Some("ert"), BoxesRunTime.boxToBoolean(false));
      }

      Tuple2 var10 = var10000;
      if (var10 != null) {
         Option indexName = (Option)var10._1();
         boolean ascending = var10._2$mcZ$sp();
         Tuple2 var9 = new Tuple2(indexName, BoxesRunTime.boxToBoolean(ascending));
         Option indexName = (Option)var9._1();
         boolean ascending = var9._2$mcZ$sp();
         return this.taskList(stageId, stageAttemptId, offset, length, indexName, ascending, statuses);
      } else {
         throw new MatchError(var10);
      }
   }

   public Seq taskList(final int stageId, final int stageAttemptId, final int offset, final int length, final Option sortBy, final boolean ascending, final List statuses) {
      int[] stageKey = new int[]{stageId, stageAttemptId};
      KVStoreView base = this.store().view(TaskDataWrapper.class);
      KVStoreView var10000;
      if (sortBy instanceof Some var13) {
         String index = (String)var13.value();
         var10000 = base.index(index).parent(stageKey);
      } else {
         var10000 = base.index("stage").first(stageKey).last(stageKey);
      }

      KVStoreView indexed = var10000;
      KVStoreView ordered = ascending ? indexed : indexed.reverse();
      Seq var18;
      if (statuses != null && !statuses.isEmpty()) {
         Set statusesStr = ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(statuses).asScala().map((x$49) -> x$49.toString())).toSet();
         var18 = KVUtils$.MODULE$.viewToSeq(ordered, offset, offset + length, (s) -> BoxesRunTime.boxToBoolean($anonfun$taskList$2(statusesStr, s)));
      } else {
         var18 = KVUtils$.MODULE$.viewToSeq(ordered.skip((long)offset).max((long)length));
      }

      Seq taskDataWrapperSeq = var18;
      return this.constructTaskDataList(taskDataWrapperSeq);
   }

   public List taskList$default$7() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.collection.immutable.Nil..MODULE$).asJava();
   }

   public Map executorSummary(final int stageId, final int attemptId) {
      int[] stageKey = new int[]{stageId, attemptId};
      return KVUtils$.MODULE$.mapToSeq(this.store().view(ExecutorStageSummaryWrapper.class).index("stage").first(stageKey).last(stageKey), (exec) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(exec.executorId()), exec.info())).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Option speculationSummary(final int stageId, final int attemptId) {
      int[] stageKey = new int[]{stageId, attemptId};
      return this.asOption(() -> ((SpeculationStageSummaryWrapper)this.store().read(SpeculationStageSummaryWrapper.class, stageKey)).info());
   }

   public Seq rddList(final boolean cachedOnly) {
      return (Seq)KVUtils$.MODULE$.mapToSeq(this.store().view(RDDStorageInfoWrapper.class), (x$50) -> x$50.info()).filter((rdd) -> BoxesRunTime.boxToBoolean($anonfun$rddList$2(cachedOnly, rdd)));
   }

   public Option asOption(final Function0 fn) {
      Object var10000;
      try {
         var10000 = new Some(fn.apply());
      } catch (NoSuchElementException var2) {
         var10000 = .MODULE$;
      }

      return (Option)var10000;
   }

   public StageData newStageData(final StageData stage, final boolean withDetail, final List taskStatus, final boolean withSummaries, final double[] unsortedQuantiles) {
      if (!withDetail && !withSummaries) {
         return stage;
      } else {
         double[] quantiles = (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps(unsortedQuantiles), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
         Object var10000;
         if (withDetail) {
            Map tasks = ((IterableOnceOps)this.taskList(stage.stageId(), stage.attemptId(), 0, Integer.MAX_VALUE, .MODULE$, false, taskStatus).map((t) -> new Tuple2(BoxesRunTime.boxToLong(t.taskId()), t))).toMap(scala..less.colon.less..MODULE$.refl());
            var10000 = new Some(tasks);
         } else {
            var10000 = .MODULE$;
         }

         Option tasks = (Option)var10000;
         Option executorSummaries = (Option)(withDetail ? new Some(this.executorSummary(stage.stageId(), stage.attemptId())) : .MODULE$);
         Option taskMetricsDistribution = (Option)(withSummaries ? this.taskSummary(stage.stageId(), stage.attemptId(), quantiles) : .MODULE$);
         Option executorMetricsDistributions = (Option)(withSummaries ? this.stageExecutorSummary(stage.stageId(), stage.attemptId(), quantiles) : .MODULE$);
         Option speculationStageSummary = (Option)(withDetail ? this.speculationSummary(stage.stageId(), stage.attemptId()) : .MODULE$);
         return new StageData(stage.status(), stage.stageId(), stage.attemptId(), stage.numTasks(), stage.numActiveTasks(), stage.numCompleteTasks(), stage.numFailedTasks(), stage.numKilledTasks(), stage.numCompletedIndices(), stage.submissionTime(), stage.firstTaskLaunchedTime(), stage.completionTime(), stage.failureReason(), stage.executorDeserializeTime(), stage.executorDeserializeCpuTime(), stage.executorRunTime(), stage.executorCpuTime(), stage.resultSize(), stage.jvmGcTime(), stage.resultSerializationTime(), stage.memoryBytesSpilled(), stage.diskBytesSpilled(), stage.peakExecutionMemory(), stage.inputBytes(), stage.inputRecords(), stage.outputBytes(), stage.outputRecords(), stage.shuffleRemoteBlocksFetched(), stage.shuffleLocalBlocksFetched(), stage.shuffleFetchWaitTime(), stage.shuffleRemoteBytesRead(), stage.shuffleRemoteBytesReadToDisk(), stage.shuffleLocalBytesRead(), stage.shuffleReadBytes(), stage.shuffleReadRecords(), stage.shuffleCorruptMergedBlockChunks(), stage.shuffleMergedFetchFallbackCount(), stage.shuffleMergedRemoteBlocksFetched(), stage.shuffleMergedLocalBlocksFetched(), stage.shuffleMergedRemoteChunksFetched(), stage.shuffleMergedLocalChunksFetched(), stage.shuffleMergedRemoteBytesRead(), stage.shuffleMergedLocalBytesRead(), stage.shuffleRemoteReqsDuration(), stage.shuffleMergedRemoteReqsDuration(), stage.shuffleWriteBytes(), stage.shuffleWriteTime(), stage.shuffleWriteRecords(), stage.name(), stage.description(), stage.details(), stage.schedulingPool(), stage.rddIds(), stage.accumulatorUpdates(), tasks, executorSummaries, speculationStageSummary, stage.killedTasksSummary(), stage.resourceProfileId(), stage.peakExecutorMetrics(), taskMetricsDistribution, executorMetricsDistributions, stage.isShufflePushEnabled(), stage.shuffleMergersCount());
      }
   }

   public boolean newStageData$default$2() {
      return false;
   }

   public List newStageData$default$3() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.collection.immutable.Nil..MODULE$).asJava();
   }

   public boolean newStageData$default$4() {
      return false;
   }

   public double[] newStageData$default$5() {
      return (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double());
   }

   public Option stageExecutorSummary(final int stageId, final int stageAttemptId, final double[] unsortedQuantiles) {
      double[] quantiles = (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps(unsortedQuantiles), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      Map summary = this.executorSummary(stageId, stageAttemptId);
      if (summary.isEmpty()) {
         return .MODULE$;
      } else {
         IndexedSeq values = summary.values().toIndexedSeq();
         return new Some(new ExecutorMetricsDistributions(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(quantiles).toImmutableArraySeq(), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$51) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$1(x$51)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$52) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$2(x$52)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$53) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$3(x$53)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$54) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$4(x$54)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$55) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$5(x$55)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$56) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$6(x$56)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$57) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$7(x$57)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$58) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$8(x$58)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$59) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$9(x$59)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$60) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$10(x$60)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$61) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$11(x$61)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$62) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$12(x$62)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$63) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$13(x$63)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), AppStatusUtils$.MODULE$.getQuantilesValue((IndexedSeq)((SeqOps)values.map((x$64) -> BoxesRunTime.boxToDouble($anonfun$stageExecutorSummary$14(x$64)))).sorted(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$), quantiles), new ExecutorPeakMetricsDistributions(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(quantiles).toImmutableArraySeq(), (IndexedSeq)values.flatMap((x$65) -> x$65.peakMemoryMetrics()))));
      }
   }

   public RDDStorageInfo rdd(final int rddId) {
      return ((RDDStorageInfoWrapper)this.store().read(RDDStorageInfoWrapper.class, BoxesRunTime.boxToInteger(rddId))).info();
   }

   public boolean rddList$default$1() {
      return true;
   }

   public Seq streamBlocksList() {
      return KVUtils$.MODULE$.viewToSeq(this.store().view(StreamBlockData.class));
   }

   public RDDOperationGraph operationGraphForStage(final int stageId) {
      return ((RDDOperationGraphWrapper)this.store().read(RDDOperationGraphWrapper.class, BoxesRunTime.boxToInteger(stageId))).toRDDOperationGraph();
   }

   public scala.collection.Seq operationGraphForJob(final int jobId) {
      JobDataWrapper job = (JobDataWrapper)this.store().read(JobDataWrapper.class, BoxesRunTime.boxToInteger(jobId));
      scala.collection.Seq stages = (scala.collection.Seq)job.info().stageIds().sorted(scala.math.Ordering.Int..MODULE$);
      return (scala.collection.Seq)stages.map((id) -> $anonfun$operationGraphForJob$1(this, job, BoxesRunTime.unboxToInt(id)));
   }

   public PoolData pool(final String name) {
      return (PoolData)this.store().read(PoolData.class, name);
   }

   public AppSummary appSummary() {
      try {
         return (AppSummary)this.store().read(AppSummary.class, AppSummary.class.getName());
      } catch (NoSuchElementException var1) {
         throw new NoSuchElementException("Failed to get the application summary. If you are starting up Spark, please wait a while until it's ready.");
      }
   }

   public void close() {
      this.store().close();
      this.cleanUpStorePath();
   }

   private void cleanUpStorePath() {
      this.storePath().foreach((file) -> {
         $anonfun$cleanUpStorePath$1(file);
         return BoxedUnit.UNIT;
      });
   }

   public Seq constructTaskDataList(final Iterable taskDataWrapperIter) {
      HashMap executorIdToLogs = new HashMap();
      return ((IterableOnceOps)taskDataWrapperIter.map((taskDataWrapper) -> {
         TaskData taskDataOld = taskDataWrapper.toApi();
         Map executorLogs = (Map)executorIdToLogs.getOrElseUpdate(taskDataOld.executorId(), () -> {
            Map var10000;
            try {
               var10000 = this.executorSummary(taskDataOld.executorId()).executorLogs();
            } catch (NoSuchElementException var3) {
               var10000 = scala.Predef..MODULE$.Map().empty();
            }

            return var10000;
         });
         return new TaskData(taskDataOld.taskId(), taskDataOld.index(), taskDataOld.attempt(), taskDataOld.partitionId(), taskDataOld.launchTime(), taskDataOld.resultFetchStart(), taskDataOld.duration(), taskDataOld.executorId(), taskDataOld.host(), taskDataOld.status(), taskDataOld.taskLocality(), taskDataOld.speculative(), taskDataOld.accumulatorUpdates(), taskDataOld.errorMessage(), taskDataOld.taskMetrics(), executorLogs, AppStatusUtils$.MODULE$.schedulerDelay(taskDataOld), AppStatusUtils$.MODULE$.gettingResultTime(taskDataOld));
      })).toSeq();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$jobsList$2(final List statuses$1, final JobData job) {
      return statuses$1.contains(job.status());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorList$2(final ExecutorSummary x$4) {
      boolean var2;
      label23: {
         String var10000 = x$4.id();
         String var1 = FallbackStorage$.MODULE$.FALLBACK_BLOCK_MANAGER_ID().executorId();
         if (var10000 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final long $anonfun$extractGcTime$1(final ExecutorMetrics x$5) {
      return x$5.getMetricValue("TotalGCTime");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stageList$2(final List statuses$2, final StageData s) {
      return statuses$2.contains(s.status());
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$2(final KVStoreIterator it) {
      long _count = 0L;

      while(it.hasNext()) {
         ++_count;
         it.skip(1L);
      }

      return _count;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$taskSummary$6(final long count$1, final CachedQuantile x$9) {
      return x$9.taskCount() == count$1;
   }

   // $FF: synthetic method
   public static final Option $anonfun$taskSummary$4(final AppStatusStore $this, final int stageId$1, final int stageAttemptId$1, final long count$1, final double q) {
      Object[] qkey = new Object[]{BoxesRunTime.boxToInteger(stageId$1), BoxesRunTime.boxToInteger(stageAttemptId$1), $this.quantileToString(q)};
      return $this.asOption(() -> (CachedQuantile)$this.store().read(CachedQuantile.class, qkey)).filter((x$9) -> BoxesRunTime.boxToBoolean($anonfun$taskSummary$6(count$1, x$9)));
   }

   private static final IndexedSeq toValues$1(final Function1 fn, final ArraySeq cachedQuantiles$1) {
      return cachedQuantiles$1.map(fn);
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$7(final CachedQuantile x$10) {
      return x$10.duration();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$8(final CachedQuantile x$11) {
      return x$11.executorDeserializeTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$9(final CachedQuantile x$12) {
      return x$12.executorDeserializeCpuTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$10(final CachedQuantile x$13) {
      return x$13.executorRunTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$11(final CachedQuantile x$14) {
      return x$14.executorCpuTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$12(final CachedQuantile x$15) {
      return x$15.resultSize();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$13(final CachedQuantile x$16) {
      return x$16.jvmGcTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$14(final CachedQuantile x$17) {
      return x$17.resultSerializationTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$15(final CachedQuantile x$18) {
      return x$18.gettingResultTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$16(final CachedQuantile x$19) {
      return x$19.schedulerDelay();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$17(final CachedQuantile x$20) {
      return x$20.peakExecutionMemory();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$18(final CachedQuantile x$21) {
      return x$21.memoryBytesSpilled();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$19(final CachedQuantile x$22) {
      return x$22.diskBytesSpilled();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$20(final CachedQuantile x$23) {
      return x$23.bytesRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$21(final CachedQuantile x$24) {
      return x$24.recordsRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$22(final CachedQuantile x$25) {
      return x$25.bytesWritten();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$23(final CachedQuantile x$26) {
      return x$26.recordsWritten();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$24(final CachedQuantile x$27) {
      return x$27.shuffleReadBytes();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$25(final CachedQuantile x$28) {
      return x$28.shuffleRecordsRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$26(final CachedQuantile x$29) {
      return x$29.shuffleRemoteBlocksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$27(final CachedQuantile x$30) {
      return x$30.shuffleLocalBlocksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$28(final CachedQuantile x$31) {
      return x$31.shuffleFetchWaitTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$29(final CachedQuantile x$32) {
      return x$32.shuffleRemoteBytesRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$30(final CachedQuantile x$33) {
      return x$33.shuffleRemoteBytesReadToDisk();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$31(final CachedQuantile x$34) {
      return x$34.shuffleTotalBlocksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$32(final CachedQuantile x$35) {
      return x$35.shuffleRemoteReqsDuration();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$33(final CachedQuantile x$36) {
      return x$36.shuffleCorruptMergedBlockChunks();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$34(final CachedQuantile x$37) {
      return x$37.shuffleMergedFetchFallbackCount();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$35(final CachedQuantile x$38) {
      return x$38.shuffleMergedRemoteBlocksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$36(final CachedQuantile x$39) {
      return x$39.shuffleMergedLocalBlocksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$37(final CachedQuantile x$40) {
      return x$40.shuffleMergedRemoteChunksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$38(final CachedQuantile x$41) {
      return x$41.shuffleMergedLocalChunksFetched();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$39(final CachedQuantile x$42) {
      return x$42.shuffleMergedRemoteBytesRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$40(final CachedQuantile x$43) {
      return x$43.shuffleMergedLocalBytesRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$41(final CachedQuantile x$44) {
      return x$44.shuffleMergedRemoteReqsDuration();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$42(final CachedQuantile x$45) {
      return x$45.shuffleWriteBytes();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$43(final CachedQuantile x$46) {
      return x$46.shuffleWriteRecords();
   }

   // $FF: synthetic method
   public static final double $anonfun$taskSummary$44(final CachedQuantile x$47) {
      return x$47.shuffleWriteTime();
   }

   private final IndexedSeq scanTasks$1(final String index, final Function1 fn, final int[] stageKey$1, final ArraySeq indices$1) {
      return (IndexedSeq)Utils$.MODULE$.tryWithResource(() -> this.store().view(TaskDataWrapper.class).parent(stageKey$1).index(index).first(BoxesRunTime.boxToLong(0L)).closeableIterator(), (it) -> {
         DoubleRef last = DoubleRef.create(Double.NaN);
         LongRef currentIdx = LongRef.create(-1L);
         return indices$1.map((JFunction1.mcDJ.sp)(idx) -> {
            if (idx == currentIdx.elem) {
               return last.elem;
            } else {
               long diff = idx - currentIdx.elem;
               currentIdx.elem = idx;
               if (it.skip(diff - 1L)) {
                  last.elem = (double)BoxesRunTime.unboxToLong(fn.apply(it.next()));
                  return last.elem;
               } else {
                  return Double.NaN;
               }
            }
         });
      });
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$49(final TaskDataWrapper t) {
      return t.duration();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$50(final TaskDataWrapper t) {
      return t.executorDeserializeTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$51(final TaskDataWrapper t) {
      return t.executorDeserializeCpuTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$52(final TaskDataWrapper t) {
      return t.executorRunTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$53(final TaskDataWrapper t) {
      return t.executorCpuTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$54(final TaskDataWrapper t) {
      return t.resultSize();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$55(final TaskDataWrapper t) {
      return t.jvmGcTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$56(final TaskDataWrapper t) {
      return t.resultSerializationTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$57(final TaskDataWrapper t) {
      return t.gettingResultTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$58(final TaskDataWrapper t) {
      return t.schedulerDelay();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$59(final TaskDataWrapper t) {
      return t.peakExecutionMemory();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$60(final TaskDataWrapper t) {
      return t.memoryBytesSpilled();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$61(final TaskDataWrapper t) {
      return t.diskBytesSpilled();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$62(final TaskDataWrapper t) {
      return t.inputBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$63(final TaskDataWrapper t) {
      return t.inputRecordsRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$64(final TaskDataWrapper t) {
      return t.outputBytesWritten();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$65(final TaskDataWrapper t) {
      return t.outputRecordsWritten();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$66(final TaskDataWrapper m) {
      return m.shuffleLocalBytesRead() + m.shuffleRemoteBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$67(final TaskDataWrapper t) {
      return t.shuffleRecordsRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$68(final TaskDataWrapper t) {
      return t.shuffleRemoteBlocksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$69(final TaskDataWrapper t) {
      return t.shuffleLocalBlocksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$70(final TaskDataWrapper t) {
      return t.shuffleFetchWaitTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$71(final TaskDataWrapper t) {
      return t.shuffleRemoteBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$72(final TaskDataWrapper t) {
      return t.shuffleRemoteBytesReadToDisk();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$73(final TaskDataWrapper m) {
      return m.shuffleLocalBlocksFetched() + m.shuffleRemoteBlocksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$74(final TaskDataWrapper t) {
      return t.shuffleRemoteReqsDuration();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$75(final TaskDataWrapper t) {
      return t.shuffleCorruptMergedBlockChunks();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$76(final TaskDataWrapper t) {
      return t.shuffleMergedFetchFallbackCount();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$77(final TaskDataWrapper t) {
      return t.shuffleMergedRemoteBlocksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$78(final TaskDataWrapper t) {
      return t.shuffleMergedLocalBlocksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$79(final TaskDataWrapper t) {
      return t.shuffleMergedRemoteChunksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$80(final TaskDataWrapper t) {
      return t.shuffleMergedLocalChunksFetched();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$81(final TaskDataWrapper t) {
      return t.shuffleMergedRemoteBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$82(final TaskDataWrapper t) {
      return t.shuffleMergedLocalBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$83(final TaskDataWrapper t) {
      return t.shuffleMergedRemoteReqDuration();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$84(final TaskDataWrapper t) {
      return t.shuffleBytesWritten();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$85(final TaskDataWrapper t) {
      return t.shuffleRecordsWritten();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskSummary$86(final TaskDataWrapper t) {
      return t.shuffleWriteTime();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$taskSummary$87(final AppStatusStore $this, final ArraySeq quantiles$2, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         double q = x0$1._1$mcD$sp();
         return quantiles$2.contains(BoxesRunTime.boxToDouble(q)) && $this.shouldCacheQuantile(q);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$taskSummary$88(final AppStatusStore $this, final int stageId$1, final int stageAttemptId$1, final long count$1, final TaskMetricDistributions computedQuantiles$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         double q = x0$2._1$mcD$sp();
         int idx = x0$2._2$mcI$sp();
         CachedQuantile cached = new CachedQuantile(stageId$1, stageAttemptId$1, $this.quantileToString(q), count$1, BoxesRunTime.unboxToDouble(computedQuantiles$1.duration().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.executorDeserializeTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.executorDeserializeCpuTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.executorRunTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.executorCpuTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.resultSize().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.jvmGcTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.resultSerializationTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.gettingResultTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.schedulerDelay().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.peakExecutionMemory().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.memoryBytesSpilled().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.diskBytesSpilled().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.inputMetrics().bytesRead().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.inputMetrics().recordsRead().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.outputMetrics().bytesWritten().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.outputMetrics().recordsWritten().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().readBytes().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().readRecords().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().remoteBlocksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().localBlocksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().fetchWaitTime().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().remoteBytesRead().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().remoteBytesReadToDisk().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().totalBlocksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().corruptMergedBlockChunks().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().mergedFetchFallbackCount().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().remoteMergedBlocksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().localMergedBlocksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().remoteMergedChunksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().localMergedChunksFetched().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().remoteMergedBytesRead().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().localMergedBytesRead().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().remoteReqsDuration().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleReadMetrics().shufflePushReadMetricsDist().remoteMergedReqsDuration().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleWriteMetrics().writeBytes().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleWriteMetrics().writeRecords().apply(idx)), BoxesRunTime.unboxToDouble(computedQuantiles$1.shuffleWriteMetrics().writeTime().apply(idx)));
         $this.store().write(cached);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$taskList$2(final Set statusesStr$1, final TaskDataWrapper s) {
      return statusesStr$1.contains(s.status());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$rddList$2(final boolean cachedOnly$1, final RDDStorageInfo rdd) {
      return !cachedOnly$1 || rdd.numCachedPartitions() > 0;
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$1(final ExecutorStageSummary x$51) {
      return (double)x$51.taskTime();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$2(final ExecutorStageSummary x$52) {
      return (double)x$52.failedTasks();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$3(final ExecutorStageSummary x$53) {
      return (double)x$53.succeededTasks();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$4(final ExecutorStageSummary x$54) {
      return (double)x$54.killedTasks();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$5(final ExecutorStageSummary x$55) {
      return (double)x$55.inputBytes();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$6(final ExecutorStageSummary x$56) {
      return (double)x$56.inputRecords();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$7(final ExecutorStageSummary x$57) {
      return (double)x$57.outputBytes();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$8(final ExecutorStageSummary x$58) {
      return (double)x$58.outputRecords();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$9(final ExecutorStageSummary x$59) {
      return (double)x$59.shuffleRead();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$10(final ExecutorStageSummary x$60) {
      return (double)x$60.shuffleReadRecords();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$11(final ExecutorStageSummary x$61) {
      return (double)x$61.shuffleWrite();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$12(final ExecutorStageSummary x$62) {
      return (double)x$62.shuffleWriteRecords();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$13(final ExecutorStageSummary x$63) {
      return (double)x$63.memoryBytesSpilled();
   }

   // $FF: synthetic method
   public static final double $anonfun$stageExecutorSummary$14(final ExecutorStageSummary x$64) {
      return (double)x$64.diskBytesSpilled();
   }

   // $FF: synthetic method
   public static final RDDOperationGraph $anonfun$operationGraphForJob$1(final AppStatusStore $this, final JobDataWrapper job$1, final int id) {
      RDDOperationGraph g = ((RDDOperationGraphWrapper)$this.store().read(RDDOperationGraphWrapper.class, BoxesRunTime.boxToInteger(id))).toRDDOperationGraph();
      if (job$1.skippedStages().contains(BoxesRunTime.boxToInteger(id)) && !g.rootCluster().name().contains("skipped")) {
         g.rootCluster().setName(g.rootCluster().name() + " (skipped)");
      }

      return g;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanUpStorePath$1(final File file) {
      Utils$.MODULE$.deleteRecursively(file);
   }

   public AppStatusStore(final KVStore store, final Option listener, final Option storePath) {
      this.store = store;
      this.listener = listener;
      this.storePath = storePath;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
