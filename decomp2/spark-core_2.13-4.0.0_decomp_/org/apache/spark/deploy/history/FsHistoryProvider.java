package org.apache.spark.deploy.history;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.SafeModeAction;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.security.AccessControlException;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.History;
import org.apache.spark.internal.config.History$;
import org.apache.spark.internal.config.Status$;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.scheduler.ReplayListenerBus;
import org.apache.spark.scheduler.ReplayListenerBus$;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.status.AppHistoryServerPlugin;
import org.apache.spark.status.AppStatusListener;
import org.apache.spark.status.AppStatusListener$;
import org.apache.spark.status.AppStatusStore$;
import org.apache.spark.status.AppStatusStoreMetadata;
import org.apache.spark.status.ElementTrackingStore;
import org.apache.spark.status.KVUtils$;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUI$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.InMemoryStore;
import org.apache.spark.util.kvstore.KVStore;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StringOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015MgAB?\u007f\u0001y\f\t\u0002\u0003\u0006\u0002(\u0001\u0011\t\u0011)A\u0005\u0003WA!\"a\r\u0001\u0005\u0003\u0005\u000b\u0011BA\u001b\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Bq!!\u0011\u0001\t\u0003\tY\u0005C\u0005\u0002P\u0001\u0011\r\u0011\"\u0003\u0002R!A\u0011q\f\u0001!\u0002\u0013\t\u0019\u0006C\u0005\u0002b\u0001\u0011\r\u0011\"\u0003\u0002R!A\u00111\r\u0001!\u0002\u0013\t\u0019\u0006C\u0005\u0002f\u0001\u0011\r\u0011\"\u0003\u0002R!A\u0011q\r\u0001!\u0002\u0013\t\u0019\u0006C\u0005\u0002j\u0001\u0011\r\u0011\"\u0003\u0002l!A\u00111\u000f\u0001!\u0002\u0013\ti\u0007C\u0005\u0002v\u0001\u0011\r\u0011\"\u0003\u0002x!A\u0011q\u0012\u0001!\u0002\u0013\tI\bC\u0005\u0002\u0012\u0002\u0011\r\u0011\"\u0003\u0002\u0014\"A\u00111\u0014\u0001!\u0002\u0013\t)\nC\u0005\u0002\u001e\u0002\u0011\r\u0011\"\u0003\u0002 \"A\u00111\u0017\u0001!\u0002\u0013\t\t\u000bC\u0005\u00026\u0002\u0011\r\u0011\"\u0003\u0002 \"A\u0011q\u0017\u0001!\u0002\u0013\t\t\u000bC\u0005\u0002:\u0002\u0011\r\u0011\"\u0003\u0002<\"A\u00111\u001a\u0001!\u0002\u0013\ti\f\u0003\u0006\u0002N\u0002\u0011\r\u0011\"\u0001\u007f\u0003\u001fD\u0001\"a7\u0001A\u0003%\u0011\u0011\u001b\u0005\n\u0003;\u0004!\u0019!C\u0005\u0003?D\u0001\"a=\u0001A\u0003%\u0011\u0011\u001d\u0005\n\u0003k\u0004!\u0019!C\u0005\u0003oD\u0001B!\u0002\u0001A\u0003%\u0011\u0011 \u0005\n\u0005\u000f\u0001!\u0019!C\u0005\u0005\u0013A\u0001B!\u0005\u0001A\u0003%!1\u0002\u0005\n\u0005'\u0001!\u0019!C\u0005\u0005+A\u0001B!\u000b\u0001A\u0003%!q\u0003\u0005\n\u0005W\u0001!\u0019!C\u0005\u0003'C\u0001B!\f\u0001A\u0003%\u0011Q\u0013\u0005\n\u0005_\u0001!\u0019!C\u0005\u0003'C\u0001B!\r\u0001A\u0003%\u0011Q\u0013\u0005\n\u0005g\u0001!\u0019!C\u0005\u0005kA\u0001Ba\u001a\u0001A\u0003%!q\u0007\u0005\u000b\u0005S\u0002!\u0019!C\u0001}\n-\u0004\u0002\u0003B=\u0001\u0001\u0006IA!\u001c\t\u0013\tm\u0004A1A\u0005\n\tu\u0004\u0002\u0003BD\u0001\u0001\u0006IAa \t\u0013\t%\u0005\u00011A\u0005\n\t-\u0005\"\u0003BJ\u0001\u0001\u0007I\u0011\u0002BK\u0011!\u0011\t\u000b\u0001Q!\n\t5\u0005\"\u0003BR\u0001\t\u0007I\u0011\u0002BS\u0011!\u0011i\u000b\u0001Q\u0001\n\t\u001d\u0006\"\u0003BX\u0001\t\u0007I\u0011\u0002BY\u0011!\u0011Y\r\u0001Q\u0001\n\tM\u0006b\u0002Bg\u0001\u0011%!q\u001a\u0005\b\u0005\u001b\u0004A\u0011\u0002Bn\u0011\u001d\u0011y\u000b\u0001C\u0005\u0005ODqAa;\u0001\t\u0013\u0011i\u000fC\u0005\u0003r\u0002\u0011\r\u0011\"\u0003\u0003t\"A!1 \u0001!\u0002\u0013\u0011)\u0010\u0003\u0005\u0003~\u0002!\tA B\u0000\u0011\u001d\u0019\u0019\u0001\u0001C\u0005\u0007\u000bAqa!\u0003\u0001\t\u0013\u0019Y\u0001C\u0005\u0004\u0012\u0001\u0011\r\u0011\"\u0003\u0004\u0014!A11\u0007\u0001!\u0002\u0013\u0019)\u0002C\u0004\u00046\u0001!Iaa\u000e\t\u0013\r%\u0003A1A\u0005\n\r-\u0003\u0002CB*\u0001\u0001\u0006Ia!\u0014\t\u0013\rU\u0003\u00011A\u0005\u0002\r]\u0003\"CB0\u0001\u0001\u0007I\u0011AB1\u0011!\u0019)\u0007\u0001Q!\n\re\u0003\u0002CB4\u0001\u0011\u0005ap!\u001b\t\u0011\r-\u0004\u0001\"\u0001\u007f\u0007[Bqaa!\u0001\t\u0013\u0019)\tC\u0004\u0004\b\u0002!\te!#\t\u000f\r\u0015\u0006\u0001\"\u0011\u0004(\"91q\u0016\u0001\u0005B\rE\u0006bBBZ\u0001\u0011\u00053Q\u0017\u0005\b\u0007o\u0003A\u0011BB]\u0011\u001d\u0019y\f\u0001C!\u0007\u0003Dqaa3\u0001\t\u0003\u001ai\rC\u0004\u0004^\u0002!\tea8\t\u000f\r\u001d\b\u0001\"\u0011\u0004\u0006\"91\u0011\u001e\u0001\u0005B\r\u0015\u0005bBBv\u0001\u0011\u00053Q\u001e\u0005\b\t\u0003\u0001A\u0011\tC\u0002\u0011!!i\u0001\u0001C\u0001}\u000e\u0015\u0005\u0002\u0003C\b\u0001\u0011\u0005a\u0010\"\u0005\t\u000f\u0011}\u0001\u0001\"\u0003\u0005\"!9A1\u0006\u0001\u0005B\u00115\u0002b\u0002C\"\u0001\u0011%AQ\t\u0005\t\t#\u0002A\u0011\u0001@\u0005T!9A\u0011\r\u0001\u0005\n\u0011\r\u0004b\u0002C>\u0001\u0011%AQ\u0010\u0005\b\t\u0003\u0003A\u0011\u0002CB\u0011!!I\t\u0001C\u0001}\u0012-\u0005\u0002\u0003CH\u0001\u0011\u0005ap!\"\t\u000f\u0011E\u0005\u0001\"\u0003\u0005\u0014\"AA1\u0017\u0001\u0005\u0002y\u001c)\tC\u0005\u00056\u0002!\t!!\u0002\u00058\"9A1\u0019\u0001\u0005\n\u0011\u0015\u0007\"\u0003C\u007f\u0001E\u0005I\u0011\u0002C\u0000\u0011!)\t\u0002\u0001C\u0001}\u0016M\u0001\u0002CC\t\u0001\u0011\u0005a0\"\u0006\t\u000f\u0015\u001d\u0002\u0001\"\u0011\u0006*!9Q1\u0006\u0001\u0005\n\u00155\u0002bBC\u0019\u0001\u0011%Q1\u0007\u0005\b\u000bo\u0001A\u0011BC\u001d\u0011\u001d))\u0005\u0001C\u0005\u000b\u000fBq!b\u0017\u0001\t\u0013)i\u0006C\u0004\u0006h\u0001!I!\"\u001b\t\u000f\u00155\u0004\u0001\"\u0003\u0006p!AQQ\u0010\u0001\u0005\u0002y,y\bC\u0004\u0006\u0006\u0002!I!b\"\t\u000f\u0015=\u0005\u0001\"\u0003\u0006\u0012\"9QQ\u0014\u0001\u0005\n\u0015}u!CCV}\"\u0005\u0011QACW\r!ih\u0010#\u0001\u0002\u0006\u0015=\u0006bBA!c\u0012\u0005Qq\u0017\u0005\n\u000bs\u000b(\u0019!C\u0005\u000bwC\u0001\"\"1rA\u0003%QQ\u0018\u0005\n\u000b\u0007\f(\u0019!C\u0005\u000bwC\u0001\"\"2rA\u0003%QQ\u0018\u0005\n\u000b\u000f\f(\u0019!C\u0005\u000bwC\u0001\"\"3rA\u0003%QQ\u0018\u0005\n\u000b\u0017\f(\u0019!C\u0005\u000bwC\u0001\"\"4rA\u0003%QQ\u0018\u0005\n\u000b\u001f\f(\u0019!C\u0001\u0003#B\u0001\"\"5rA\u0003%\u00111\u000b\u0002\u0012\rND\u0015n\u001d;pef\u0004&o\u001c<jI\u0016\u0014(bA@\u0002\u0002\u00059\u0001.[:u_JL(\u0002BA\u0002\u0003\u000b\ta\u0001Z3qY>L(\u0002BA\u0004\u0003\u0013\tQa\u001d9be.TA!a\u0003\u0002\u000e\u00051\u0011\r]1dQ\u0016T!!a\u0004\u0002\u0007=\u0014xmE\u0003\u0001\u0003'\tY\u0002\u0005\u0003\u0002\u0016\u0005]Q\"\u0001@\n\u0007\u0005eaP\u0001\u000eBaBd\u0017nY1uS>t\u0007*[:u_JL\bK]8wS\u0012,'\u000f\u0005\u0003\u0002\u001e\u0005\rRBAA\u0010\u0015\u0011\t\t#!\u0002\u0002\u0011%tG/\u001a:oC2LA!!\n\u0002 \t9Aj\\4hS:<\u0017\u0001B2p]\u001a\u001c\u0001\u0001\u0005\u0003\u0002.\u0005=RBAA\u0003\u0013\u0011\t\t$!\u0002\u0003\u0013M\u0003\u0018M]6D_:4\u0017!B2m_\u000e\\\u0007\u0003BA\u001c\u0003{i!!!\u000f\u000b\t\u0005m\u0012QA\u0001\u0005kRLG.\u0003\u0003\u0002@\u0005e\"!B\"m_\u000e\\\u0017A\u0002\u001fj]&$h\b\u0006\u0004\u0002F\u0005\u001d\u0013\u0011\n\t\u0004\u0003+\u0001\u0001bBA\u0014\u0007\u0001\u0007\u00111\u0006\u0005\b\u0003g\u0019\u0001\u0019AA\u001b)\u0011\t)%!\u0014\t\u000f\u0005\u001dB\u00011\u0001\u0002,\u0005I2+\u0011$F\u001b>#UiX\"I\u000b\u000e[u,\u0013(U\u000bJ3\u0016\tT0T+\t\t\u0019\u0006\u0005\u0003\u0002V\u0005mSBAA,\u0015\t\tI&A\u0003tG\u0006d\u0017-\u0003\u0003\u0002^\u0005]#\u0001\u0002'p]\u001e\f!dU!G\u000b6{E)R0D\u0011\u0016\u001b5jX%O)\u0016\u0013f+\u0011'`'\u0002\n\u0011#\u0016)E\u0003R+u,\u0013(U\u000bJ3\u0016\tT0T\u0003I)\u0006\u000bR!U\u000b~Ke\nV#S-\u0006cul\u0015\u0011\u0002!\rcU)\u0011(`\u0013:#VI\u0015,B\u0019~\u001b\u0016!E\"M\u000b\u0006su,\u0013(U\u000bJ3\u0016\tT0TA\u00051b*V'`!J{5)R*T\u0013:;u\f\u0016%S\u000b\u0006#5+\u0006\u0002\u0002nA!\u0011QKA8\u0013\u0011\t\t(a\u0016\u0003\u0007%sG/A\fO+6{\u0006KU(D\u000bN\u001b\u0016JT$`)\"\u0013V)\u0011#TA\u00051An\\4ESJ,\"!!\u001f\u0011\t\u0005m\u0014\u0011\u0012\b\u0005\u0003{\n)\t\u0005\u0003\u0002\u0000\u0005]SBAAA\u0015\u0011\t\u0019)!\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0011\t9)a\u0016\u0002\rA\u0013X\rZ3g\u0013\u0011\tY)!$\u0003\rM#(/\u001b8h\u0015\u0011\t9)a\u0016\u0002\u000f1|w\rR5sA\u0005\u0019\u0002.[:u_JLX+[!dYN,e.\u00192mKV\u0011\u0011Q\u0013\t\u0005\u0003+\n9*\u0003\u0003\u0002\u001a\u0006]#a\u0002\"p_2,\u0017M\\\u0001\u0015Q&\u001cHo\u001c:z+&\f5\r\\:F]\u0006\u0014G.\u001a\u0011\u0002%!L7\u000f^8ssVK\u0017\tZ7j]\u0006\u001bGn]\u000b\u0003\u0003C\u0003b!a)\u0002.\u0006ed\u0002BAS\u0003SsA!a \u0002(&\u0011\u0011\u0011L\u0005\u0005\u0003W\u000b9&A\u0004qC\u000e\\\u0017mZ3\n\t\u0005=\u0016\u0011\u0017\u0002\u0004'\u0016\f(\u0002BAV\u0003/\n1\u0003[5ti>\u0014\u00180V5BI6Lg.Q2mg\u0002\n\u0001\u0004[5ti>\u0014\u00180V5BI6Lg.Q2mg\u001e\u0013x.\u001e9t\u0003eA\u0017n\u001d;pef,\u0016.\u00113nS:\f5\r\\:He>,\bo\u001d\u0011\u0002\u0015!\fGm\\8q\u0007>tg-\u0006\u0002\u0002>B!\u0011qXAd\u001b\t\t\tM\u0003\u0003\u0002(\u0005\r'\u0002BAc\u0003\u0013\ta\u0001[1e_>\u0004\u0018\u0002BAe\u0003\u0003\u0014QbQ8oM&<WO]1uS>t\u0017a\u00035bI>|\u0007oQ8oM\u0002\n!AZ:\u0016\u0005\u0005E\u0007\u0003BAj\u0003/l!!!6\u000b\t\u00055\u00171Y\u0005\u0005\u00033\f)N\u0001\u0006GS2,7+_:uK6\f1AZ:!\u0003\u0011\u0001xn\u001c7\u0016\u0005\u0005\u0005\b\u0003BAr\u0003_l!!!:\u000b\t\u0005\u001d\u0018\u0011^\u0001\u000bG>t7-\u001e:sK:$(\u0002BA\u001e\u0003WT!!!<\u0002\t)\fg/Y\u0005\u0005\u0003c\f)O\u0001\rTG\",G-\u001e7fI\u0016CXmY;u_J\u001cVM\u001d<jG\u0016\fQ\u0001]8pY\u0002\nA\u0002\\1tiN\u001b\u0017M\u001c+j[\u0016,\"!!?\u0011\t\u0005m(\u0011A\u0007\u0003\u0003{TA!a@\u0002f\u00061\u0011\r^8nS\u000eLAAa\u0001\u0002~\nQ\u0011\t^8nS\u000eduN\\4\u0002\u001b1\f7\u000f^*dC:$\u0016.\\3!\u0003]\u0001XM\u001c3j]\u001e\u0014V\r\u001d7bsR\u000b7o[:D_VtG/\u0006\u0002\u0003\fA!\u00111 B\u0007\u0013\u0011\u0011y!!@\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u0003a\u0001XM\u001c3j]\u001e\u0014V\r\u001d7bsR\u000b7o[:D_VtG\u000fI\u0001\ngR|'/\u001a)bi\",\"Aa\u0006\u0011\r\u0005U#\u0011\u0004B\u000f\u0013\u0011\u0011Y\"a\u0016\u0003\r=\u0003H/[8o!\u0011\u0011yB!\n\u000e\u0005\t\u0005\"\u0002\u0002B\u0012\u0003W\f!![8\n\t\t\u001d\"\u0011\u0005\u0002\u0005\r&dW-\u0001\u0006ti>\u0014X\rU1uQ\u0002\nQCZ1ti&s\u0007K]8he\u0016\u001c8\u000fU1sg&tw-\u0001\fgCN$\u0018J\u001c)s_\u001e\u0014Xm]:QCJ\u001c\u0018N\\4!\u0003IA\u0017P\u0019:jIN#xN]3F]\u0006\u0014G.\u001a3\u0002'!L(M]5e'R|'/Z#oC\ndW\r\u001a\u0011\u0002-!L(M]5e'R|'/\u001a#jg.\u0014\u0015mY6f]\u0012,\"Aa\u000e\u0011\t\te\"q\f\b\u0005\u0005w\u0011IF\u0004\u0003\u0003>\tMc\u0002\u0002B \u0005\u001frAA!\u0011\u0003N9!!1\tB&\u001d\u0011\u0011)E!\u0013\u000f\t\u0005}$qI\u0005\u0003\u0003\u001fIA!a\u0003\u0002\u000e%!\u0011qAA\u0005\u0013\u0011\t\t#!\u0002\n\t\tE\u0013qD\u0001\u0007G>tg-[4\n\t\tU#qK\u0001\b\u0011&\u001cHo\u001c:z\u0015\u0011\u0011\t&a\b\n\t\tm#QL\u0001\u0017\u0011f\u0014'/\u001b3Ti>\u0014X\rR5tW\n\u000b7m[3oI*!!Q\u000bB,\u0013\u0011\u0011\tGa\u0019\u0003\u000bY\u000bG.^3\n\t\t\u0015\u0014q\u000b\u0002\f\u000b:,X.\u001a:bi&|g.A\fis\n\u0014\u0018\u000eZ*u_J,G)[:l\u0005\u0006\u001c7.\u001a8eA\u00059A.[:uS:<WC\u0001B7!\u0011\u0011yG!\u001e\u000e\u0005\tE$\u0002\u0002B:\u0003s\tqa\u001b<ti>\u0014X-\u0003\u0003\u0003x\tE$aB&W'R|'/Z\u0001\tY&\u001cH/\u001b8hA\u0005YA-[:l\u001b\u0006t\u0017mZ3s+\t\u0011y\b\u0005\u0004\u0002V\te!\u0011\u0011\t\u0005\u0003+\u0011\u0019)C\u0002\u0003\u0006z\u0014\u0001\u0004S5ti>\u0014\u0018pU3sm\u0016\u0014H)[:l\u001b\u0006t\u0017mZ3s\u00031!\u0017n]6NC:\fw-\u001a:!\u00035iW-\\8ss6\u000bg.Y4feV\u0011!Q\u0012\t\u0005\u0003+\u0011y)C\u0002\u0003\u0012z\u0014!\u0004S5ti>\u0014\u0018pU3sm\u0016\u0014X*Z7pefl\u0015M\\1hKJ\f\u0011#\\3n_JLX*\u00198bO\u0016\u0014x\fJ3r)\u0011\u00119J!(\u0011\t\u0005U#\u0011T\u0005\u0005\u00057\u000b9F\u0001\u0003V]&$\b\"\u0003BPY\u0005\u0005\t\u0019\u0001BG\u0003\rAH%M\u0001\u000f[\u0016lwN]=NC:\fw-\u001a:!\u000351\u0017\u000e\\3D_6\u0004\u0018m\u0019;peV\u0011!q\u0015\t\u0005\u0003+\u0011I+C\u0002\u0003,z\u0014Q#\u0012<f]Rdun\u001a$jY\u0016\u001cu.\u001c9bGR|'/\u0001\bgS2,7i\\7qC\u000e$xN\u001d\u0011\u0002\u0015A\u0014xnY3tg&tw-\u0006\u0002\u00034BA!Q\u0017B^\u0003s\u0012\tM\u0004\u0003\u0002d\n]\u0016\u0002\u0002B]\u0003K\f\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q\u0013\u0011\u0011iLa0\u0003\u0015-+\u0017pU3u-&,wO\u0003\u0003\u0003:\u0006\u0015\b\u0003\u0002Bb\u0005\u0013l!A!2\u000b\t\t\u001d\u00171^\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001a\n\u0015\u0017a\u00039s_\u000e,7o]5oO\u0002\nA\"[:Qe>\u001cWm]:j]\u001e$B!!&\u0003R\"9!1\u001b\u001aA\u0002\tU\u0017\u0001\u00029bi\"\u0004B!a5\u0003X&!!\u0011\\Ak\u0005\u0011\u0001\u0016\r\u001e5\u0015\t\u0005U%Q\u001c\u0005\b\u0005?\u001c\u0004\u0019\u0001Bq\u0003\u0011IgNZ8\u0011\t\u0005U!1]\u0005\u0004\u0005Kt(a\u0002'pO&sgm\u001c\u000b\u0005\u0005/\u0013I\u000fC\u0004\u0003TR\u0002\rA!6\u0002\u001b\u0015tG\r\u0015:pG\u0016\u001c8/\u001b8h)\u0011\u00119Ja<\t\u000f\tMW\u00071\u0001\u0003V\u0006\u0001\u0012N\\1dG\u0016\u001c8/\u001b2mK2K7\u000f^\u000b\u0003\u0005k\u0004\u0002\"a9\u0003x\u0006e\u00141K\u0005\u0005\u0005s\f)OA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\f\u0011#\u001b8bG\u000e,7o]5cY\u0016d\u0015n\u001d;!\u00031I7/Q2dKN\u001c\u0018N\u00197f)\u0011\t)j!\u0001\t\u000f\tM\u0007\b1\u0001\u0003V\u0006\u0001R.\u0019:l\u0013:\f7mY3tg&\u0014G.\u001a\u000b\u0005\u0005/\u001b9\u0001C\u0004\u0003Tf\u0002\rA!6\u0002+\rdW-\u0019:J]\u0006\u001c7-Z:tS\ndW\rT5tiR!!qSB\u0007\u0011\u001d\u0019yA\u000fa\u0001\u0003'\n1#\u001a=qSJ,G+[7f\u0013:\u001cVmY8oIN\f\u0011\"Y2uSZ,W+S:\u0016\u0005\rU\u0001\u0003CB\f\u0007C\u0019)c!\f\u000e\u0005\re!\u0002BB\u000e\u0007;\tq!\\;uC\ndWM\u0003\u0003\u0004 \u0005]\u0013AC2pY2,7\r^5p]&!11EB\r\u0005\u001dA\u0015m\u001d5NCB\u0004\u0002\"!\u0016\u0004(\u0005e41F\u0005\u0005\u0007S\t9F\u0001\u0004UkBdWM\r\t\u0007\u0003+\u0012I\"!\u001f\u0011\t\u0005U1qF\u0005\u0004\u0007cq(a\u0003'pC\u0012,G-\u00119q+&\u000b!\"Y2uSZ,W+S:!\u0003%9W\r\u001e*v]:,'\u000f\u0006\u0003\u0004:\r}\u0002\u0003\u0002Bb\u0007wIAa!\u0010\u0003F\nA!+\u001e8oC\ndW\rC\u0004\u0004Bu\u0002\raa\u0011\u0002\u0015=\u0004XM]1uK\u001a+h\u000e\u0005\u0004\u0002V\r\u0015#qS\u0005\u0005\u0007\u000f\n9FA\u0005Gk:\u001cG/[8oa\u0005q!/\u001a9mCf,\u00050Z2vi>\u0014XCAB'!\u0011\t\u0019oa\u0014\n\t\rE\u0013Q\u001d\u0002\u0010\u000bb,7-\u001e;peN+'O^5dK\u0006y!/\u001a9mCf,\u00050Z2vi>\u0014\b%\u0001\u0006j]&$H\u000b\u001b:fC\u0012,\"a!\u0017\u0011\t\t\r71L\u0005\u0005\u0007;\u0012)M\u0001\u0004UQJ,\u0017\rZ\u0001\u000fS:LG\u000f\u00165sK\u0006$w\fJ3r)\u0011\u00119ja\u0019\t\u0013\t}\u0015)!AA\u0002\re\u0013aC5oSR$\u0006N]3bI\u0002\n!\"\u001b8ji&\fG.\u001b>f)\t\u0019I&\u0001\rti\u0006\u0014HoU1gK6{G-Z\"iK\u000e\\G\u000b\u001b:fC\u0012$Ba!\u0017\u0004p!91\u0011\u000f#A\u0002\rM\u0014\u0001D3se>\u0014\b*\u00198eY\u0016\u0014\bCBA+\u00053\u0019)\b\u0005\u0003\u0004x\rud\u0002\u0002Bb\u0007sJAaa\u001f\u0003F\u00061A\u000b\u001b:fC\u0012LAaa \u0004\u0002\nARK\\2bk\u001eDG/\u0012=dKB$\u0018n\u001c8IC:$G.\u001a:\u000b\t\rm$QY\u0001\rgR\f'\u000f\u001e)pY2Lgn\u001a\u000b\u0003\u0005/\u000b!bZ3u\u0019&\u001cH/\u001b8h)\t\u0019Y\t\u0005\u0004\u0002$\u000e55\u0011S\u0005\u0005\u0007\u001f\u000b\tL\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\u0011\u0019\u0019j!)\u000e\u0005\rU%\u0002BBL\u00073\u000b!A^\u0019\u000b\t\rm5QT\u0001\u0004CBL'\u0002BBP\u0003\u000b\taa\u001d;biV\u001c\u0018\u0002BBR\u0007+\u0013q\"\u00119qY&\u001c\u0017\r^5p]&sgm\\\u0001\u0013O\u0016$\u0018\t\u001d9mS\u000e\fG/[8o\u0013:4w\u000e\u0006\u0003\u0004*\u000e-\u0006CBA+\u00053\u0019\t\nC\u0004\u0004.\u001e\u0003\r!!\u001f\u0002\u000b\u0005\u0004\b/\u00133\u00021\u001d,G/\u0012<f]RdunZ:V]\u0012,'\u000f\u0015:pG\u0016\u001c8\u000f\u0006\u0002\u0002n\u0005\u0011r-\u001a;MCN$X\u000b\u001d3bi\u0016$G+[7f)\t\t\u0019&A\u0006tiJLgn\u001a+p'\u0016\fH\u0003BAQ\u0007wCqa!0K\u0001\u0004\tI(\u0001\u0003mSN$\u0018\u0001C4fi\u0006\u0003\b/V%\u0015\r\r\r7QYBd!\u0019\t)F!\u0007\u0004.!91QV&A\u0002\u0005e\u0004bBBe\u0017\u0002\u000711F\u0001\nCR$X-\u001c9u\u0013\u0012\f1cZ3u\u000b6\u0004H/\u001f'jgRLgn\u001a%u[2$\"aa4\u0011\r\u0005\r\u0016QVBi!\u0011\u0019\u0019n!7\u000e\u0005\rU'\u0002BBl\u0003/\n1\u0001_7m\u0013\u0011\u0019Yn!6\u0003\t9{G-Z\u0001\nO\u0016$8i\u001c8gS\u001e$\"a!9\u0011\u0011\u0005m41]A=\u0003sJAa!:\u0002\u000e\n\u0019Q*\u00199\u0002\u000bM$\u0018M\u001d;\u0002\tM$x\u000e]\u0001\r_:,\u0016\nR3uC\u000eDW\r\u001a\u000b\t\u0005/\u001byo!=\u0004t\"91Q\u0016)A\u0002\u0005e\u0004bBBe!\u0002\u000711\u0006\u0005\b\u0007k\u0004\u0006\u0019AB|\u0003\t)\u0018\u000e\u0005\u0003\u0004z\u000euXBAB~\u0015\u0011\u0019)0!\u0002\n\t\r}81 \u0002\b'B\f'o[+J\u0003Y\u0019\u0007.Z2l+&3\u0016.Z<QKJl\u0017n]:j_:\u001cH\u0003CAK\t\u000b!9\u0001\"\u0003\t\u000f\r5\u0016\u000b1\u0001\u0002z!91\u0011Z)A\u0002\r-\u0002b\u0002C\u0006#\u0002\u0007\u0011\u0011P\u0001\u0005kN,'/\u0001\u0007dQ\u0016\u001c7NR8s\u0019><7/A\btQ>,H\u000e\u001a*fY>\fG\rT8h)\u0019\t)\nb\u0005\u0005\u0016!9!q\\*A\u0002\t\u0005\bb\u0002C\f'\u0002\u0007A\u0011D\u0001\u0007e\u0016\fG-\u001a:\u0011\t\u0005UA1D\u0005\u0004\t;q(AE#wK:$Hj\\4GS2,'+Z1eKJ\fAb\u00197fC:\f\u0005\u000f\u001d#bi\u0006$\u0002Ba&\u0005$\u0011\u0015Bq\u0005\u0005\b\u0007[#\u0006\u0019AA=\u0011\u001d\u0019I\r\u0016a\u0001\u0007WAq\u0001\"\u000bU\u0001\u0004\tI(A\u0004m_\u001e\u0004\u0016\r\u001e5\u0002\u001d]\u0014\u0018\u000e^3Fm\u0016tG\u000fT8hgRA!q\u0013C\u0018\tc!\u0019\u0004C\u0004\u0004.V\u0003\r!!\u001f\t\u000f\r%W\u000b1\u0001\u0004,!9AQG+A\u0002\u0011]\u0012!\u0003>jaN#(/Z1n!\u0011!I\u0004b\u0010\u000e\u0005\u0011m\"\u0002\u0002C\u001f\u0003S\f1A_5q\u0013\u0011!\t\u0005b\u000f\u0003\u001fiK\u0007oT;uaV$8\u000b\u001e:fC6\fq#\\3sO\u0016\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8MSN$\u0018N\\4\u0015\u0011\t]Eq\tC%\t\u001bBq\u0001b\u0006W\u0001\u0004!I\u0002C\u0004\u0005LY\u0003\r!a\u0015\u0002\u0011M\u001c\u0017M\u001c+j[\u0016Dq\u0001b\u0014W\u0001\u0004\t)*A\nf]\u0006\u0014G.Z(qi&l\u0017N_1uS>t7/A\re_6+'oZ3BaBd\u0017nY1uS>tG*[:uS:<GC\u0003BL\t+\"9\u0006\"\u0017\u0005\\!9AqC,A\u0002\u0011e\u0001b\u0002C&/\u0002\u0007\u00111\u000b\u0005\b\t\u001f:\u0006\u0019AAK\u0011\u001d!if\u0016a\u0001\t?\n!\u0004\\1ti\u00163\u0018\r\\;bi\u0016$gi\u001c:D_6\u0004\u0018m\u0019;j_:\u0004b!!\u0016\u0003\u001a\u0005M\u0013!\t3p\u001b\u0016\u0014x-Z!qa2L7-\u0019;j_:d\u0015n\u001d;j]\u001eLe\u000e^3s]\u0006dGC\u0003BL\tK\"9\u0007\"\u001b\u0005l!9Aq\u0003-A\u0002\u0011e\u0001b\u0002C&1\u0002\u0007\u00111\u000b\u0005\b\t\u001fB\u0006\u0019AAK\u0011\u001d!i\u0006\u0017a\u0001\t?B3\u0001\u0017C8!\u0011!\t\bb\u001e\u000e\u0005\u0011M$\u0002\u0002C;\u0003/\n!\"\u00198o_R\fG/[8o\u0013\u0011!I\bb\u001d\u0003\u000fQ\f\u0017\u000e\u001c:fG\u000691m\\7qC\u000e$H\u0003\u0002BL\t\u007fBq\u0001b\u0006Z\u0001\u0004!I\"\u0001\u0007j]Z\fG.\u001b3bi\u0016,\u0016\n\u0006\u0004\u0003\u0018\u0012\u0015Eq\u0011\u0005\b\u0007[S\u0006\u0019AA=\u0011\u001d\u0019IM\u0017a\u0001\u0007W\t\u0001c\u00195fG.\fe\u000eZ\"mK\u0006tGj\\4\u0015\t\t]EQ\u0012\u0005\b\tSY\u0006\u0019AA=\u0003%\u0019G.Z1o\u0019><7/A\teK2,G/Z!ui\u0016l\u0007\u000f\u001e'pON$\u0002\"!\u001c\u0005\u0016\u0012}Eq\u0016\u0005\b\t/k\u0006\u0019\u0001CM\u0003\r\t\u0007\u000f\u001d\t\u0005\u0003+!Y*C\u0002\u0005\u001ez\u0014a#\u00119qY&\u001c\u0017\r^5p]&sgm\\,sCB\u0004XM\u001d\u0005\b\tCk\u0006\u0019\u0001CR\u0003%\u0011X-\\1j]&tw\r\u0005\u0004\u0002$\u0012\u0015F\u0011V\u0005\u0005\tO\u000b\tL\u0001\u0003MSN$\b\u0003BA\u000b\tWK1\u0001\",\u007f\u0005I\tE\u000f^3naRLeNZ8Xe\u0006\u0004\b/\u001a:\t\u000f\u0011EV\f1\u0001\u0005$\u0006AAo\u001c#fY\u0016$X-A\bdY\u0016\fg\u000e\u0012:jm\u0016\u0014Hj\\4t\u0003=\u0011XMY;jY\u0012\f\u0005\u000f]*u_J,G\u0003\u0003BL\ts#i\fb0\t\u000f\u0011mv\f1\u0001\u0003n\u0005)1\u000f^8sK\"9AqC0A\u0002\u0011e\u0001b\u0002Ca?\u0002\u0007\u00111K\u0001\fY\u0006\u001cH/\u00169eCR,G-A\tqCJ\u001cX-\u00119q\u000bZ,g\u000e\u001e'pON$\"Ba&\u0005H\u0012MG1\u001dCt\u0011\u001d!I\r\u0019a\u0001\t\u0017\f\u0001\u0002\\8h\r&dWm\u001d\t\u0007\u0003G\u000bi\u000b\"4\u0011\t\u0005MGqZ\u0005\u0005\t#\f)N\u0001\u0006GS2,7\u000b^1ukNDq\u0001\"6a\u0001\u0004!9.A\u0005sKBd\u0017-\u001f\"vgB!A\u0011\u001cCp\u001b\t!YN\u0003\u0003\u0005^\u0006\u0015\u0011!C:dQ\u0016$W\u000f\\3s\u0013\u0011!\t\u000fb7\u0003#I+\u0007\u000f\\1z\u0019&\u001cH/\u001a8fe\n+8\u000fC\u0004\u0005f\u0002\u0004\r!!&\u0002\u001d5\f\u0017PY3UeVt7-\u0019;fI\"IA\u0011\u001e1\u0011\u0002\u0003\u0007A1^\u0001\rKZ,g\u000e^:GS2$XM\u001d\t\u0005\t[$9P\u0004\u0003\u0005p\u0012Mh\u0002\u0002B!\tcLA\u0001\"8\u0002\u0006%!AQ\u001fCn\u0003E\u0011V\r\u001d7bs2K7\u000f^3oKJ\u0014Uo]\u0005\u0005\ts$YP\u0001\nSKBd\u0017-_#wK:$8OR5mi\u0016\u0014(\u0002\u0002C{\t7\f1\u0004]1sg\u0016\f\u0005\u000f]#wK:$Hj\\4tI\u0011,g-Y;mi\u0012\"TCAC\u0001U\u0011!Y/b\u0001,\u0005\u0015\u0015\u0001\u0003BC\u0004\u000b\u001bi!!\"\u0003\u000b\t\u0015-A1O\u0001\nk:\u001c\u0007.Z2lK\u0012LA!b\u0004\u0006\n\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d%\u001chi]%o'\u00064W-T8eKR\u0011\u0011Q\u0013\u000b\u0005\u0003++9\u0002C\u0004\u0006\u001a\r\u0004\r!b\u0007\u0002\u0007\u001147\u000f\u0005\u0003\u0006\u001e\u0015\rRBAC\u0010\u0015\u0011)\t#a1\u0002\t!$gm]\u0005\u0005\u000bK)yBA\u000bESN$(/\u001b2vi\u0016$g)\u001b7f'f\u001cH/Z7\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u001f\u0002\t1|\u0017\r\u001a\u000b\u0005\t3+y\u0003C\u0004\u0004.\u0016\u0004\r!!\u001f\u0002\u0015\u0005$G\rT5ti&tw\r\u0006\u0003\u0003\u0018\u0016U\u0002b\u0002CLM\u0002\u0007A\u0011T\u0001\u000eY>\fG\rR5tWN#xN]3\u0015\u0011\t5T1HC \u000b\u0003Bq!\"\u0010h\u0001\u0004\u0011\t)\u0001\u0002e[\"91QV4A\u0002\u0005e\u0004bBC\"O\u0002\u0007A\u0011V\u0001\bCR$X-\u001c9u\u0003E\u0019'/Z1uK\"K(M]5e'R|'/\u001a\u000b\u000b\u0005[*I%b\u0013\u0006N\u0015=\u0003bBC\u001fQ\u0002\u0007!\u0011\u0011\u0005\b\u0007[C\u0007\u0019AA=\u0011\u001d)\u0019\u0005\u001ba\u0001\tSCq!\"\u0015i\u0001\u0004)\u0019&\u0001\u0005nKR\fG-\u0019;b!\u0011))&b\u0016\u000e\u0005\ru\u0015\u0002BC-\u0007;\u0013a#\u00119q'R\fG/^:Ti>\u0014X-T3uC\u0012\fG/Y\u0001\u0010GJ,\u0017\r^3ESN\\7\u000b^8sKRQ!QNC0\u000bC*\u0019'\"\u001a\t\u000f\u0015u\u0012\u000e1\u0001\u0003\u0002\"91QV5A\u0002\u0005e\u0004bBC\"S\u0002\u0007A\u0011\u0016\u0005\b\u000b#J\u0007\u0019AC*\u0003M\u0019'/Z1uK&sW*Z7pef\u001cFo\u001c:f)\u0011\u0011i'b\u001b\t\u000f\u0015\r#\u000e1\u0001\u0005*\u0006YAn\\1e!2,x-\u001b8t)\t)\t\b\u0005\u0004\u0002$\u0016MTqO\u0005\u0005\u000bk\n\tL\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\u0011))&\"\u001f\n\t\u0015m4Q\u0014\u0002\u0017\u0003B\u0004\b*[:u_JL8+\u001a:wKJ\u0004F.^4j]\u0006Qq-\u001a;BiR,W\u000e\u001d;\u0015\r\u0011%V\u0011QCB\u0011\u001d\u0019i\u000b\u001ca\u0001\u0003sBqa!3m\u0001\u0004\u0019Y#A\u0005eK2,G/\u001a'pOR1\u0011QSCE\u000b\u0017Cq!!4n\u0001\u0004\t\t\u000eC\u0004\u0006\u000e6\u0004\rA!6\u0002\u00071|w-\u0001\u000btk\nl\u0017\u000e\u001e'pOB\u0013xnY3tgR\u000b7o\u001b\u000b\u0005\u000b'+I\n\u0006\u0003\u0003\u0018\u0016U\u0005bBCL]\u0002\u00071\u0011H\u0001\u0005i\u0006\u001c8\u000eC\u0004\u0006\u001c:\u0004\rA!6\u0002\u0011I|w\u000e\u001e)bi\"\fQc\u0019:fCR,7+Z2ve&$\u00180T1oC\u001e,'\u000f\u0006\u0004\u0006\"\u0016\u001dV\u0011\u0016\t\u0005\u0003[)\u0019+\u0003\u0003\u0006&\u0006\u0015!aD*fGV\u0014\u0018\u000e^=NC:\fw-\u001a:\t\u000f\u0005\u001dr\u000e1\u0001\u0002,!9Q1I8A\u0002\u0011%\u0016!\u0005$t\u0011&\u001cHo\u001c:z!J|g/\u001b3feB\u0019\u0011QC9\u0014\u0007E,\t\f\u0005\u0003\u0002V\u0015M\u0016\u0002BC[\u0003/\u0012a!\u00118z%\u00164GCACW\u0003]\t\u0005\u000b\u0015'`'R\u000b%\u000bV0F-\u0016sEk\u0018)S\u000b\u001aK\u0005,\u0006\u0002\u0006>B!!1YC`\u0013\u0011\tYI!2\u00021\u0005\u0003\u0006\u000bT0T)\u0006\u0013FkX#W\u000b:#v\f\u0015*F\r&C\u0006%A\u000bB!Bcu,\u0012(E?\u00163VI\u0014+`!J+e)\u0013-\u0002-\u0005\u0003\u0006\u000bT0F\u001d\u0012{VIV#O)~\u0003&+\u0012$J1\u0002\na\u0003T(H?N#\u0016I\u0015+`\u000bZ+e\nV0Q%\u00163\u0015\nW\u0001\u0018\u0019>;ul\u0015+B%R{VIV#O)~\u0003&+\u0012$J1\u0002\nq#\u0012(W?V\u0003F)\u0011+F?\u00163VI\u0014+`!J+e)\u0013-\u00021\u0015sekX+Q\t\u0006#ViX#W\u000b:#v\f\u0015*F\r&C\u0006%A\fD+J\u0013VI\u0014+`\u0019&\u001bF+\u0013(H?Z+%kU%P\u001d\u0006A2)\u0016*S\u000b:#v\fT%T)&sui\u0018,F%NKuJ\u0014\u0011"
)
public class FsHistoryProvider extends ApplicationHistoryProvider implements Logging {
   public final SparkConf org$apache$spark$deploy$history$FsHistoryProvider$$conf;
   private final Clock clock;
   private final long SAFEMODE_CHECK_INTERVAL_S;
   private final long UPDATE_INTERVAL_S;
   private final long CLEAN_INTERVAL_S;
   private final int NUM_PROCESSING_THREADS;
   private final String logDir;
   private final boolean historyUiAclsEnable;
   private final Seq historyUiAdminAcls;
   private final Seq historyUiAdminAclsGroups;
   private final Configuration hadoopConf;
   private final FileSystem fs;
   private final ScheduledExecutorService pool;
   private final AtomicLong lastScanTime;
   private final AtomicInteger pendingReplayTasksCount;
   private final Option storePath;
   private final boolean fastInProgressParsing;
   private final boolean hybridStoreEnabled;
   private final Enumeration.Value hybridStoreDiskBackend;
   private final KVStore listing;
   private final Option diskManager;
   private HistoryServerMemoryManager org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager;
   private final EventLogFileCompactor fileCompactor;
   private final ConcurrentHashMap.KeySetView processing;
   private final ConcurrentHashMap inaccessibleList;
   private final HashMap activeUIs;
   private final ExecutorService replayExecutor;
   private Thread initThread;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static long CURRENT_LISTING_VERSION() {
      return FsHistoryProvider$.MODULE$.CURRENT_LISTING_VERSION();
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

   private long SAFEMODE_CHECK_INTERVAL_S() {
      return this.SAFEMODE_CHECK_INTERVAL_S;
   }

   private long UPDATE_INTERVAL_S() {
      return this.UPDATE_INTERVAL_S;
   }

   private long CLEAN_INTERVAL_S() {
      return this.CLEAN_INTERVAL_S;
   }

   private int NUM_PROCESSING_THREADS() {
      return this.NUM_PROCESSING_THREADS;
   }

   private String logDir() {
      return this.logDir;
   }

   private boolean historyUiAclsEnable() {
      return this.historyUiAclsEnable;
   }

   private Seq historyUiAdminAcls() {
      return this.historyUiAdminAcls;
   }

   private Seq historyUiAdminAclsGroups() {
      return this.historyUiAdminAclsGroups;
   }

   private Configuration hadoopConf() {
      return this.hadoopConf;
   }

   public FileSystem fs() {
      return this.fs;
   }

   private ScheduledExecutorService pool() {
      return this.pool;
   }

   private AtomicLong lastScanTime() {
      return this.lastScanTime;
   }

   private AtomicInteger pendingReplayTasksCount() {
      return this.pendingReplayTasksCount;
   }

   private Option storePath() {
      return this.storePath;
   }

   private boolean fastInProgressParsing() {
      return this.fastInProgressParsing;
   }

   private boolean hybridStoreEnabled() {
      return this.hybridStoreEnabled;
   }

   private Enumeration.Value hybridStoreDiskBackend() {
      return this.hybridStoreDiskBackend;
   }

   public KVStore listing() {
      return this.listing;
   }

   private Option diskManager() {
      return this.diskManager;
   }

   public HistoryServerMemoryManager org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager() {
      return this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager;
   }

   private void memoryManager_$eq(final HistoryServerMemoryManager x$1) {
      this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager = x$1;
   }

   private EventLogFileCompactor fileCompactor() {
      return this.fileCompactor;
   }

   private ConcurrentHashMap.KeySetView processing() {
      return this.processing;
   }

   private boolean isProcessing(final Path path) {
      return this.processing().contains(path.getName());
   }

   private boolean isProcessing(final LogInfo info) {
      return this.processing().contains(.MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])info.logPath().split("/"))));
   }

   private void processing(final Path path) {
      this.processing().add(path.getName());
   }

   private void endProcessing(final Path path) {
      this.processing().remove(path.getName());
   }

   private ConcurrentHashMap inaccessibleList() {
      return this.inaccessibleList;
   }

   public boolean isAccessible(final Path path) {
      return !this.inaccessibleList().containsKey(path.getName());
   }

   private void markInaccessible(final Path path) {
      this.inaccessibleList().put(path.getName(), BoxesRunTime.boxToLong(this.clock.getTimeMillis()));
   }

   private void clearInaccessibleList(final long expireTimeInSeconds) {
      long expiredThreshold = this.clock.getTimeMillis() - expireTimeInSeconds * 1000L;
      scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.inaccessibleList()).asScala().filterInPlace((x$2, creationTime) -> BoxesRunTime.boxToBoolean($anonfun$clearInaccessibleList$1(expiredThreshold, x$2, BoxesRunTime.unboxToLong(creationTime))));
   }

   private HashMap activeUIs() {
      return this.activeUIs;
   }

   private Runnable getRunner(final Function0 operateFun) {
      return () -> Utils$.MODULE$.tryOrExit(operateFun);
   }

   private ExecutorService replayExecutor() {
      return this.replayExecutor;
   }

   public Thread initThread() {
      return this.initThread;
   }

   public void initThread_$eq(final Thread x$1) {
      this.initThread = x$1;
   }

   public Thread initialize() {
      if (!this.isFsInSafeMode()) {
         this.startPolling();
         return null;
      } else {
         return this.startSafeModeCheckThread(scala.None..MODULE$);
      }
   }

   public Thread startSafeModeCheckThread(final Option errorHandler) {
      Thread initThread = new Thread(() -> {
         try {
            while(true) {
               if (this.isFsInSafeMode()) {
                  this.logInfo((Function0)(() -> "HDFS is still in safe mode. Waiting..."));
                  long deadline = this.clock.getTimeMillis() + TimeUnit.SECONDS.toMillis(this.SAFEMODE_CHECK_INTERVAL_S());
                  this.clock.waitTillTime(deadline);
               } else {
                  this.startPolling();
                  break;
               }
            }
         } catch (InterruptedException var3) {
         }

      });
      initThread.setDaemon(true);
      initThread.setName(this.getClass().getSimpleName() + "-init");
      initThread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)errorHandler.getOrElse(() -> (x$3, e) -> {
            this.logError((Function0)(() -> "Error initializing FsHistoryProvider."), e);
            System.exit(1);
         }));
      initThread.start();
      return initThread;
   }

   private void startPolling() {
      this.diskManager().foreach((x$4) -> {
         $anonfun$startPolling$1(x$4);
         return BoxedUnit.UNIT;
      });
      if (this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager() != null) {
         this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager().initialize();
      }

      Path path = new Path(this.logDir());

      try {
         if (!this.fs().getFileStatus(path).isDirectory()) {
            throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Logging directory specified is not a directory: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.logDir()})));
         }
      } catch (FileNotFoundException var5) {
         String msg = "Log directory specified does not exist: " + this.logDir();
         String var10000 = this.logDir();
         String var4 = History$.MODULE$.DEFAULT_LOG_DIR();
         if (var10000 == null) {
            if (var4 != null) {
               throw (new FileNotFoundException(msg)).initCause(var5);
            }
         } else if (!var10000.equals(var4)) {
            throw (new FileNotFoundException(msg)).initCause(var5);
         }

         msg = msg + " Did you configure the correct one through spark.history.fs.logDirectory?";
         throw (new FileNotFoundException(msg)).initCause(var5);
      }

      if (!this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.contains((ConfigEntry)Tests$.MODULE$.IS_TESTING())) {
         this.logDebug((Function0)(() -> "Scheduling update thread every " + this.UPDATE_INTERVAL_S() + " seconds"));
         this.pool().scheduleWithFixedDelay(this.getRunner((JFunction0.mcV.sp)() -> this.checkForLogs()), 0L, this.UPDATE_INTERVAL_S(), TimeUnit.SECONDS);
         if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.CLEANER_ENABLED()))) {
            this.pool().scheduleWithFixedDelay(this.getRunner((JFunction0.mcV.sp)() -> this.cleanLogs()), 0L, this.CLEAN_INTERVAL_S(), TimeUnit.SECONDS);
         } else {
            BoxedUnit var6 = BoxedUnit.UNIT;
         }

         if (this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.contains((ConfigEntry)package$.MODULE$.DRIVER_LOG_DFS_DIR()) && BoxesRunTime.unboxToBoolean(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.DRIVER_LOG_CLEANER_ENABLED()))) {
            this.pool().scheduleWithFixedDelay(this.getRunner((JFunction0.mcV.sp)() -> this.cleanDriverLogs()), 0L, BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.DRIVER_LOG_CLEANER_INTERVAL())), TimeUnit.SECONDS);
         }
      } else {
         this.logDebug((Function0)(() -> "Background update thread disabled for testing"));
      }
   }

   public Iterator getListing() {
      return KVUtils$.MODULE$.mapToSeq(this.listing().view(ApplicationInfoWrapper.class).index("endTime").reverse(), (x$5) -> x$5.toApplicationInfo()).iterator();
   }

   public Option getApplicationInfo(final String appId) {
      Object var10000;
      try {
         var10000 = new Some(this.load(appId).toApplicationInfo());
      } catch (NoSuchElementException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public int getEventLogsUnderProcess() {
      return this.pendingReplayTasksCount().get();
   }

   public long getLastUpdatedTime() {
      return this.lastScanTime().get();
   }

   private Seq stringToSeq(final String list) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(list), ',')), (x$6) -> x$6.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$stringToSeq$2(x$7)))).toImmutableArraySeq();
   }

   public Option getAppUI(final String appId, final Option attemptId) {
      ApplicationInfoWrapper var10000;
      try {
         var10000 = this.load(appId);
      } catch (NoSuchElementException var20) {
         return scala.None..MODULE$;
      }

      ApplicationInfoWrapper app = var10000;
      AttemptInfoWrapper attempt = (AttemptInfoWrapper)app.attempts().find((x$8) -> BoxesRunTime.boxToBoolean($anonfun$getAppUI$1(attemptId, x$8))).orNull(scala..less.colon.less..MODULE$.refl());
      if (attempt == null) {
         return scala.None..MODULE$;
      } else {
         SparkConf conf = this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.clone();
         SecurityManager secManager = this.createSecurityManager(conf, attempt);

         try {
            Option var9 = this.diskManager();
            if (var9 instanceof Some) {
               Some var10 = (Some)var9;
               HistoryServerDiskManager sm = (HistoryServerDiskManager)var10.value();
               var21 = this.loadDiskStore(sm, appId, attempt);
            } else {
               var21 = this.createInMemoryStore(attempt);
            }
         } catch (FileNotFoundException var19) {
            return scala.None..MODULE$;
         }

         KVStore kvstore = var21;
         SparkUI ui = SparkUI$.MODULE$.create(scala.None..MODULE$, new HistoryAppStatusStore(conf, kvstore), conf, secManager, app.info().name(), HistoryServer$.MODULE$.getAttemptURI(appId, attempt.info().attemptId()), attempt.info().startTime().getTime(), attempt.info().appSparkVersion());
         ((IterableOnceOps)this.loadPlugins().toSeq().sortBy((x$9) -> BoxesRunTime.boxToInteger($anonfun$getAppUI$2(x$9)), scala.math.Ordering.Int..MODULE$)).foreach((x$10) -> {
            $anonfun$getAppUI$3(ui, x$10);
            return BoxedUnit.UNIT;
         });
         LoadedAppUI loadedUI = new LoadedAppUI(ui);
         synchronized(this){}

         try {
            this.activeUIs().update(new Tuple2(appId, attemptId), loadedUI);
         } catch (Throwable var18) {
            throw var18;
         }

         return new Some(loadedUI);
      }
   }

   public Seq getEmptyListingHtml() {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      Did you specify the correct logging directory? Please verify your setting of\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var4 = new UnprefixedAttribute("style", new Text("font-style:italic"), $md);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("spark.history.fs.logDirectory"));
      $buf.$amp$plus(new Elem((String)null, "span", var4, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      listed above and whether you have the permissions to access it.\n      "));
      $buf.$amp$plus(new Elem((String)null, "br", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      It is also possible that your application did not run to\n      completion or did not stop the SparkContext.\n    "));
      return new Elem((String)null, "p", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public scala.collection.immutable.Map getConfig() {
      scala.collection.immutable.Map safeMode = this.isFsInSafeMode() ? (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("HDFS State"), "In safe mode, application logs not available.")}))) : (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      scala.collection.immutable.Map driverLog = this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.contains((ConfigEntry)package$.MODULE$.DRIVER_LOG_DFS_DIR()) ? (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("Driver log directory"), ((Option)this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_DFS_DIR())).get())}))) : (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      return (scala.collection.immutable.Map)((MapOps)((MapOps)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("Event log directory"), this.logDir())})))).$plus$plus(safeMode)).$plus$plus(driverLog);
   }

   public void start() {
      this.initThread_$eq(this.initialize());
   }

   public void stop() {
      try {
         if (this.initThread() != null && this.initThread().isAlive()) {
            this.initThread().interrupt();
            this.initThread().join();
         }

         (new scala.collection.immutable..colon.colon(this.pool(), new scala.collection.immutable..colon.colon(this.replayExecutor(), scala.collection.immutable.Nil..MODULE$))).foreach((executor) -> {
            executor.shutdown();
            return !executor.awaitTermination(5L, TimeUnit.SECONDS) ? executor.shutdownNow() : BoxedUnit.UNIT;
         });
      } finally {
         this.activeUIs().foreach((x0$1) -> {
            $anonfun$stop$2(x0$1);
            return BoxedUnit.UNIT;
         });
         this.activeUIs().clear();
         this.listing().close();
      }

   }

   public void onUIDetached(final String appId, final Option attemptId, final SparkUI ui) {
      synchronized(this){}

      Option var6;
      try {
         var6 = this.activeUIs().remove(new Tuple2(appId, attemptId));
      } catch (Throwable var8) {
         throw var8;
      }

      var6.foreach((loadedUI) -> {
         $anonfun$onUIDetached$1(this, appId, attemptId, loadedUI);
         return BoxedUnit.UNIT;
      });
   }

   public boolean checkUIViewPermissions(final String appId, final Option attemptId, final String user) {
      ApplicationInfoWrapper app = this.load(appId);
      AttemptInfoWrapper attempt = (AttemptInfoWrapper)app.attempts().find((x$11) -> BoxesRunTime.boxToBoolean($anonfun$checkUIViewPermissions$1(attemptId, x$11))).orNull(scala..less.colon.less..MODULE$.refl());
      if (attempt == null) {
         throw new NoSuchElementException();
      } else {
         SecurityManager secManager = this.createSecurityManager(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.clone(), attempt);
         return secManager.checkUIViewPermissions(user);
      }
   }

   public void checkForLogs() {
      IntRef count = IntRef.create(0);

      try {
         long newLastScanTime = this.clock.getTimeMillis();
         this.logDebug((Function0)(() -> {
            String var10000 = this.logDir();
            return "Scanning " + var10000 + " with lastScanTime==" + this.lastScanTime();
         }));
         HashSet notStale = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         Seq updated = (Seq)((IterableOps)((SeqOps)((IterableOps)((StrictOptimizedIterableOps)((StrictOptimizedIterableOps)((StrictOptimizedIterableOps)scala.Option..MODULE$.apply(this.fs().listStatus(new Path(this.logDir()))).map((x$12) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$12).toImmutableArraySeq()).getOrElse(() -> scala.collection.immutable.Nil..MODULE$)).filter((entry) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$4(this, entry)))).filter((entry) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$5(this, notStale, entry)))).flatMap((entry) -> EventLogFileReader$.MODULE$.apply(this.fs(), entry))).filter((reader) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$7(this, reader)))).sortWith((x0$1, x1$1) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$9(x0$1, x1$1)))).filter((reader) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$10(this, newLastScanTime, count, reader)));
         if (updated.nonEmpty()) {
            this.logDebug((Function0)(() -> {
               int var10000 = updated.size();
               return "New/updated attempts found: " + var10000 + " " + updated.map((x$13) -> x$13.rootPath());
            }));
         }

         updated.foreach((entry) -> {
            $anonfun$checkForLogs$17(this, newLastScanTime, entry);
            return BoxedUnit.UNIT;
         });
         synchronized(this.listing()){}

         Seq var8;
         try {
            var8 = KVUtils$.MODULE$.viewToSeq(this.listing().view(LogInfo.class).index("lastProcessed").last(BoxesRunTime.boxToLong(newLastScanTime - 1L)));
         } catch (Throwable var12) {
            throw var12;
         }

         ((IterableOnceOps)((IterableOps)var8.filterNot((info) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$19(this, info)))).filterNot((info) -> BoxesRunTime.boxToBoolean($anonfun$checkForLogs$20(notStale, info)))).foreach((log) -> {
            $anonfun$checkForLogs$21(this, log);
            return BoxedUnit.UNIT;
         });
         this.lastScanTime().set(newLastScanTime);
      } catch (Exception var13) {
         this.logError((Function0)(() -> "Exception in checking for event log updates"), var13);
      }

   }

   public boolean shouldReloadLog(final LogInfo info, final EventLogFileReader reader) {
      if (info.isComplete() != reader.completed()) {
         return true;
      } else {
         boolean var10000;
         if (info.lastIndex().isDefined()) {
            scala.Predef..MODULE$.require(reader.lastIndex().isDefined());
            var10000 = BoxesRunTime.unboxToLong(info.lastIndex().get()) < BoxesRunTime.unboxToLong(reader.lastIndex().get()) || info.fileSize() < reader.fileSizeForLastIndex();
         } else {
            var10000 = info.fileSize() < reader.fileSizeForLastIndex();
         }

         boolean result = var10000;
         if (!result && !reader.completed()) {
            try {
               result = reader.fileSizeForLastIndexForDFS().exists((JFunction1.mcZJ.sp)(x$14) -> info.fileSize() < x$14);
            } catch (Exception var5) {
               this.logDebug((Function0)(() -> "Failed to check the length for the file : " + info.logPath()), var5);
            }
         }

         return result;
      }
   }

   private void cleanAppData(final String appId, final Option attemptId, final String logPath) {
      try {
         boolean isStale = false;
         synchronized(this.listing()){}

         try {
            ApplicationInfoWrapper app = this.load(appId);
            Tuple2 var9 = app.attempts().partition((x$15) -> BoxesRunTime.boxToBoolean($anonfun$cleanAppData$1(attemptId, x$15)));
            if (var9 == null) {
               throw new MatchError(var9);
            }

            List attempt = (List)var9._1();
            List others = (List)var9._2();
            Tuple2 var8 = new Tuple2(attempt, others);
            List attempt = (List)var8._1();
            List others = (List)var8._2();
            scala.Predef..MODULE$.assert(attempt.isEmpty() || attempt.size() == 1);
            isStale = attempt.headOption().exists((a) -> BoxesRunTime.boxToBoolean($anonfun$cleanAppData$2(this, logPath, others, app, appId, a)));
         } catch (Throwable var24) {
            throw var24;
         }

         if (isStale) {
            synchronized(this){}

            Option var16;
            try {
               var16 = this.activeUIs().remove(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId));
            } catch (Throwable var23) {
               throw var23;
            }

            var16.foreach((ui) -> {
               $anonfun$cleanAppData$3(ui);
               return BoxedUnit.UNIT;
            });
            this.diskManager().foreach((x$17) -> {
               $anonfun$cleanAppData$4(appId, attemptId, x$17);
               return BoxedUnit.UNIT;
            });
         }
      } catch (NoSuchElementException var25) {
      }

   }

   public void writeEventLogs(final String appId, final Option attemptId, final ZipOutputStream zipStream) {
      ApplicationInfoWrapper var10000;
      try {
         var10000 = this.load(appId);
      } catch (NoSuchElementException var9) {
         throw new SparkException("Logs for " + appId + " not found.");
      }

      ApplicationInfoWrapper app = var10000;

      try {
         ((List)attemptId.map((id) -> app.attempts().filter((x$18) -> BoxesRunTime.boxToBoolean($anonfun$writeEventLogs$2(id, x$18)))).getOrElse(() -> app.attempts())).foreach((attempt) -> {
            $anonfun$writeEventLogs$4(this, zipStream, attempt);
            return BoxedUnit.UNIT;
         });
      } finally {
         zipStream.close();
      }

   }

   private void mergeApplicationListing(final EventLogFileReader reader, final long scanTime, final boolean enableOptimizations) {
      Path rootPath = reader.rootPath();
      boolean succeeded = false;

      try {
         Object var33;
         try {
            var33 = ((LogInfo)this.listing().read(LogInfo.class, rootPath.toString())).lastEvaluatedForCompaction();
         } catch (NoSuchElementException var27) {
            var33 = scala.None..MODULE$;
         }

         Option lastEvaluatedForCompaction = (Option)var33;
         this.pendingReplayTasksCount().incrementAndGet();
         this.doMergeApplicationListing(reader, scanTime, enableOptimizations, lastEvaluatedForCompaction);
         if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.CLEANER_ENABLED()))) {
            this.checkAndCleanLog(rootPath.toString());
         }

         succeeded = true;
      } catch (Throwable var28) {
         if (var28 instanceof InterruptedException var11) {
            throw var11;
         }

         if (var28 instanceof AccessControlException var12) {
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to read log ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, rootPath)})))), var12);
            this.markInaccessible(rootPath);
            synchronized(this.listing()){}

            try {
               this.listing().delete(LogInfo.class, rootPath.toString());
            } catch (Throwable var26) {
               throw var26;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else if (var28 instanceof FileNotFoundException && reader.rootPath().getName().endsWith(EventLogFileWriter$.MODULE$.IN_PROGRESS())) {
            String finalFileName = scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(reader.rootPath().getName()), EventLogFileWriter$.MODULE$.IN_PROGRESS());
            Path finalFilePath = new Path(reader.rootPath().getParent(), finalFileName);
            if (this.fs().exists(finalFilePath)) {
               BoxedUnit var31 = BoxedUnit.UNIT;
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"In-progress event log file does not exist: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"neither does the final event log file: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FINAL_PATH..MODULE$, finalFilePath)}))))));
               BoxedUnit var32 = BoxedUnit.UNIT;
            }
         } else {
            if (!(var28 instanceof Exception)) {
               throw var28;
            }

            Exception var16 = (Exception)var28;
            this.logError((Function0)(() -> "Exception while merging application listings"), var16);
            BoxedUnit var30 = BoxedUnit.UNIT;
         }
      } finally {
         this.endProcessing(rootPath);
         this.pendingReplayTasksCount().decrementAndGet();
         if (succeeded) {
            this.submitLogProcessTask(rootPath, () -> this.compact(reader));
         }

      }

   }

   public void doMergeApplicationListing(final EventLogFileReader reader, final long scanTime, final boolean enableOptimizations, final Option lastEvaluatedForCompaction) {
      this.doMergeApplicationListingInternal(reader, scanTime, enableOptimizations, lastEvaluatedForCompaction);
   }

   private void doMergeApplicationListingInternal(final EventLogFileReader reader, final long scanTime, final boolean enableOptimizations, final Option lastEvaluatedForCompaction) {
      while(true) {
         Function1 eventsFilter = (eventString) -> BoxesRunTime.boxToBoolean($anonfun$doMergeApplicationListingInternal$1(eventString));
         Path logPath = reader.rootPath();
         boolean appCompleted = reader.completed();
         long reparseChunkSize = BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.END_EVENT_REPARSE_CHUNK_SIZE()));
         boolean shouldHalt = enableOptimizations && (!appCompleted && this.fastInProgressParsing() || reparseChunkSize > 0L);
         ReplayListenerBus bus = new ReplayListenerBus();
         AppListingListener listener = new AppListingListener(reader, this.clock, shouldHalt);
         bus.addListener(listener);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Parsing ", " for listing data..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logPath)})))));
         Seq logFiles = reader.listEventLogFiles();
         this.parseAppEventLogs(logFiles, bus, !appCompleted, eventsFilter);
         boolean lookForEndEvent = shouldHalt && (appCompleted || !this.fastInProgressParsing());
         if (lookForEndEvent && listener.applicationInfo().isDefined()) {
            FileStatus lastFile = (FileStatus)logFiles.last();
            Utils$.MODULE$.tryWithResource(() -> EventLogFileReader$.MODULE$.openEventLog(lastFile.getPath(), this.fs()), (in) -> BoxesRunTime.boxToBoolean($anonfun$doMergeApplicationListingInternal$4(this, lastFile, reparseChunkSize, logPath, bus, appCompleted, eventsFilter, in)));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         label200: {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished parsing ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logPath)})))));
            boolean var19 = false;
            Object var20 = null;
            Option var21 = listener.applicationInfo();
            if (var21 instanceof Some) {
               var19 = true;
               Some var29 = (Some)var21;
               ApplicationInfoWrapper app = (ApplicationInfoWrapper)var29.value();
               if (!lookForEndEvent || ((AttemptInfoWrapper)app.attempts().head()).info().completed()) {
                  this.invalidateUI(app.info().id(), ((AttemptInfoWrapper)app.attempts().head()).info().attemptId());
                  this.addListing(app);
                  this.listing().write(new LogInfo(logPath.toString(), scanTime, LogType$.MODULE$.EventLogs(), new Some(app.info().id()), ((AttemptInfoWrapper)app.attempts().head()).info().attemptId(), reader.fileSizeForLastIndex(), reader.lastIndex(), lastEvaluatedForCompaction, reader.completed()));
                  if (appCompleted && reader.lastIndex().isEmpty()) {
                     String var32 = logPath.toString();
                     String inProgressLog = var32 + EventLogFileWriter$.MODULE$.IN_PROGRESS();

                     try {
                        this.listing().read(LogInfo.class, inProgressLog);
                        if (!SparkHadoopUtil$.MODULE$.isFile(this.fs(), new Path(inProgressLog))) {
                           synchronized(this.listing()){}

                           try {
                              this.listing().delete(LogInfo.class, inProgressLog);
                           } catch (Throwable var27) {
                              throw var27;
                           }

                           BoxedUnit var34 = BoxedUnit.UNIT;
                        } else {
                           BoxedUnit var35 = BoxedUnit.UNIT;
                        }
                     } catch (NoSuchElementException var28) {
                        BoxedUnit var33 = BoxedUnit.UNIT;
                     }
                     break label200;
                  }

                  BoxedUnit var31 = BoxedUnit.UNIT;
                  break label200;
               }
            }

            if (var19) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reparsing ", " since end event was not found."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logPath)})))));
               lastEvaluatedForCompaction = lastEvaluatedForCompaction;
               enableOptimizations = false;
               scanTime = scanTime;
               reader = reader;
               continue;
            }

            this.listing().write(new LogInfo(logPath.toString(), scanTime, LogType$.MODULE$.EventLogs(), scala.None..MODULE$, scala.None..MODULE$, reader.fileSizeForLastIndex(), reader.lastIndex(), lastEvaluatedForCompaction, reader.completed()));
            BoxedUnit var30 = BoxedUnit.UNIT;
         }

         BoxedUnit var36 = BoxedUnit.UNIT;
         return;
      }
   }

   private void compact(final EventLogFileReader reader) {
      Path rootPath = reader.rootPath();

      try {
         Option var4 = reader.lastIndex();
         if (var4 instanceof Some var5) {
            long lastIndex = BoxesRunTime.unboxToLong(var5.value());

            try {
               LogInfo info = (LogInfo)this.listing().read(LogInfo.class, reader.rootPath().toString());
               if (!info.lastEvaluatedForCompaction().isEmpty() && BoxesRunTime.unboxToLong(info.lastEvaluatedForCompaction().get()) >= lastIndex) {
                  BoxedUnit var36 = BoxedUnit.UNIT;
               } else {
                  this.fileCompactor().compact(reader.listEventLogFiles());
                  KVStore var34 = this.listing();
                  Some x$1 = new Some(BoxesRunTime.boxToLong(lastIndex));
                  String x$2 = info.copy$default$1();
                  long x$3 = info.copy$default$2();
                  Enumeration.Value x$4 = info.copy$default$3();
                  Option x$5 = info.copy$default$4();
                  Option x$6 = info.copy$default$5();
                  long x$7 = info.copy$default$6();
                  Option x$8 = info.copy$default$7();
                  boolean x$9 = info.copy$default$9();
                  var34.write(info.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$1, x$9));
                  BoxedUnit var35 = BoxedUnit.UNIT;
               }
            } catch (NoSuchElementException var29) {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            if (!scala.None..MODULE$.equals(var4)) {
               throw new MatchError(var4);
            }

            BoxedUnit var37 = BoxedUnit.UNIT;
         }
      } catch (InterruptedException var30) {
         throw var30;
      } catch (AccessControlException var31) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Insufficient permission while compacting log for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, rootPath)})))), var31);
      } catch (Exception var32) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while compacting log for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, rootPath)})))), var32);
      } finally {
         this.endProcessing(rootPath);
      }

   }

   private void invalidateUI(final String appId, final Option attemptId) {
      synchronized(this){}

      Option var5;
      try {
         var5 = this.activeUIs().get(new Tuple2(appId, attemptId));
      } catch (Throwable var7) {
         throw var7;
      }

      var5.foreach((ui) -> {
         $anonfun$invalidateUI$1(ui);
         return BoxedUnit.UNIT;
      });
   }

   public void checkAndCleanLog(final String logPath) {
      Utils$.MODULE$.tryLog((JFunction0.mcV.sp)() -> {
         long maxTime = this.clock.getTimeMillis() - BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.MAX_LOG_AGE_S())) * 1000L;
         LogInfo log = (LogInfo)this.listing().read(LogInfo.class, logPath);
         if (log.lastProcessed() <= maxTime && log.appId().isEmpty()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting invalid / corrupt event log ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, log.logPath())})))));
            this.deleteLog(this.fs(), new Path(log.logPath()));
            this.listing().delete(LogInfo.class, log.logPath());
         }

         log.appId().foreach((appId) -> {
            ApplicationInfoWrapper app = (ApplicationInfoWrapper)this.listing().read(ApplicationInfoWrapper.class, appId);
            if (app.oldestAttempt() <= maxTime) {
               Tuple2 var7 = app.attempts().partition((attempt) -> BoxesRunTime.boxToBoolean($anonfun$checkAndCleanLog$4(maxTime, attempt)));
               if (var7 != null) {
                  List remaining = (List)var7._1();
                  List toDelete = (List)var7._2();
                  Tuple2 var6 = new Tuple2(remaining, toDelete);
                  List remainingx = (List)var6._1();
                  List toDelete = (List)var6._2();
                  return BoxesRunTime.boxToInteger(this.deleteAttemptLogs(app, remainingx, toDelete));
               } else {
                  throw new MatchError(var7);
               }
            } else {
               return BoxedUnit.UNIT;
            }
         });
      });
   }

   public void cleanLogs() {
      Utils$.MODULE$.tryLog((JFunction0.mcV.sp)() -> {
         long maxTime = this.clock.getTimeMillis() - BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.MAX_LOG_AGE_S())) * 1000L;
         int maxNum = BoxesRunTime.unboxToInt(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.MAX_LOG_NUM()));
         Seq expired = KVUtils$.MODULE$.viewToSeq(this.listing().view(ApplicationInfoWrapper.class).index("oldestAttempt").reverse().first(BoxesRunTime.boxToLong(maxTime)));
         expired.foreach((app) -> BoxesRunTime.boxToInteger($anonfun$cleanLogs$2(this, maxTime, app)));
         Seq stale = KVUtils$.MODULE$.viewToSeq(this.listing().view(LogInfo.class).index("lastProcessed").reverse().first(BoxesRunTime.boxToLong(maxTime)), Integer.MAX_VALUE, (l) -> BoxesRunTime.boxToBoolean($anonfun$cleanLogs$4(l)));
         ((IterableOnceOps)stale.filterNot((info) -> BoxesRunTime.boxToBoolean($anonfun$cleanLogs$5(this, info)))).foreach((log) -> {
            $anonfun$cleanLogs$6(this, log);
            return BoxedUnit.UNIT;
         });
         int num = KVUtils$.MODULE$.size(this.listing().view(LogInfo.class).index("lastProcessed"));
         IntRef count = IntRef.create(num - maxNum);
         if (count.elem > 0) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Try to delete ", " old event logs"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FILES..MODULE$, BoxesRunTime.boxToInteger(count.elem))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to keep ", " logs in total."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_NUM_FILES..MODULE$, BoxesRunTime.boxToInteger(maxNum))}))))));
            KVUtils$.MODULE$.foreach(this.listing().view(ApplicationInfoWrapper.class).index("oldestAttempt"), (app) -> {
               $anonfun$cleanLogs$9(this, count, app);
               return BoxedUnit.UNIT;
            });
            if (count.elem > 0) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fail to clean up according to MAX_LOG_NUM policy "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_NUM_LOG_POLICY..MODULE$, BoxesRunTime.boxToInteger(maxNum))}))))));
            }
         }

         this.clearInaccessibleList(this.CLEAN_INTERVAL_S());
      });
   }

   private int deleteAttemptLogs(final ApplicationInfoWrapper app, final List remaining, final List toDelete) {
      if (remaining.nonEmpty()) {
         ApplicationInfoWrapper newApp = new ApplicationInfoWrapper(app.info(), remaining);
         this.listing().write(newApp);
      }

      IntRef countDeleted = IntRef.create(0);
      toDelete.foreach((attempt) -> {
         $anonfun$deleteAttemptLogs$1(this, app, countDeleted, attempt);
         return BoxedUnit.UNIT;
      });
      if (remaining.isEmpty()) {
         this.listing().delete(app.getClass(), app.id());
      }

      return countDeleted.elem;
   }

   public void cleanDriverLogs() {
      Utils$.MODULE$.tryLog((JFunction0.mcV.sp)() -> {
         String driverLogDir = (String)((Option)this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_DFS_DIR())).get();
         FileSystem driverLogFs = (new Path(driverLogDir)).getFileSystem(this.hadoopConf());
         long currentTime = this.clock.getTimeMillis();
         long maxTime = currentTime - BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.MAX_DRIVER_LOG_AGE_S())) * 1000L;
         FileStatus[] logFiles = driverLogFs.listStatus(new Path(driverLogDir));
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])logFiles), (f) -> {
            String logFileStr = f.getPath().toString();

            boolean var10000;
            try {
               LogInfo info = (LogInfo)this.listing().read(LogInfo.class, logFileStr);
               if (info.fileSize() >= f.getLen() && info.lastProcessed() >= f.getModificationTime()) {
                  var10000 = info.lastProcessed() <= maxTime;
               } else {
                  KVStore var23 = this.listing();
                  long x$2 = f.getLen();
                  String x$3 = info.copy$default$1();
                  Enumeration.Value x$4 = info.copy$default$3();
                  Option x$5 = info.copy$default$4();
                  Option x$6 = info.copy$default$5();
                  Option x$7 = info.copy$default$7();
                  Option x$8 = info.copy$default$8();
                  boolean x$9 = info.copy$default$9();
                  var23.write(info.copy(x$3, currentTime, x$4, x$5, x$6, x$2, x$7, x$8, x$9));
                  var10000 = false;
               }
            } catch (NoSuchElementException var22) {
               this.listing().write(new LogInfo(logFileStr, currentTime, LogType$.MODULE$.DriverLogs(), scala.None..MODULE$, scala.None..MODULE$, f.getLen(), scala.None..MODULE$, scala.None..MODULE$, false));
               var10000 = false;
            }

            boolean deleteFile = var10000;
            if (deleteFile) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting expired driver log for: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logFileStr)})))));
               this.listing().delete(LogInfo.class, logFileStr);
               return BoxesRunTime.boxToBoolean(this.deleteLog(driverLogFs, f.getPath()));
            } else {
               return BoxedUnit.UNIT;
            }
         });
         Seq stale = KVUtils$.MODULE$.viewToSeq(this.listing().view(LogInfo.class).index("lastProcessed").reverse().first(BoxesRunTime.boxToLong(maxTime)), Integer.MAX_VALUE, (l) -> BoxesRunTime.boxToBoolean($anonfun$cleanDriverLogs$4(l)));
         ((IterableOnceOps)stale.filterNot((info) -> BoxesRunTime.boxToBoolean($anonfun$cleanDriverLogs$5(this, info)))).foreach((log) -> BoxesRunTime.boxToBoolean($anonfun$cleanDriverLogs$6(this, driverLogFs, log)));
      });
   }

   public void rebuildAppStore(final KVStore store, final EventLogFileReader reader, final long lastUpdated) {
      SparkConf replayConf = this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.clone().set((ConfigEntry)Status$.MODULE$.ASYNC_TRACKING_ENABLED(), (Object)BoxesRunTime.boxToBoolean(false));
      ElementTrackingStore trackingStore = new ElementTrackingStore(store, replayConf);
      ReplayListenerBus replayBus = new ReplayListenerBus();
      boolean x$3 = false;
      Some x$4 = new Some(BoxesRunTime.boxToLong(lastUpdated));
      Option x$5 = AppStatusListener$.MODULE$.$lessinit$greater$default$4();
      AppStatusListener listener = new AppStatusListener(trackingStore, replayConf, false, x$5, x$4);
      replayBus.addListener(listener);
      this.loadPlugins().foreach((plugin) -> {
         $anonfun$rebuildAppStore$1(this, trackingStore, replayBus, plugin);
         return BoxedUnit.UNIT;
      });

      try {
         Seq eventLogFiles = reader.listEventLogFiles();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Parsing ", " to re-build UI..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath())})))));
         this.parseAppEventLogs(eventLogFiles, replayBus, !reader.completed(), this.parseAppEventLogs$default$4());
         trackingStore.close(false);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished parsing ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath())})))));
      } catch (Exception var16) {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> trackingStore.close());
         throw var16;
      }
   }

   private void parseAppEventLogs(final Seq logFiles, final ReplayListenerBus replayBus, final boolean maybeTruncated, final Function1 eventsFilter) {
      BooleanRef continueReplay = BooleanRef.create(true);
      logFiles.foreach((file) -> {
         $anonfun$parseAppEventLogs$1(this, continueReplay, replayBus, maybeTruncated, eventsFilter, file);
         return BoxedUnit.UNIT;
      });
   }

   private Function1 parseAppEventLogs$default$4() {
      return ReplayListenerBus$.MODULE$.SELECT_ALL_FILTER();
   }

   public boolean isFsInSafeMode() {
      FileSystem var2 = this.fs();
      if (var2 instanceof DistributedFileSystem var3) {
         return this.isFsInSafeMode(var3);
      } else {
         return false;
      }
   }

   public boolean isFsInSafeMode(final DistributedFileSystem dfs) {
      return dfs.setSafeMode(SafeModeAction.GET, true);
   }

   public String toString() {
      long count = this.listing().count(ApplicationInfoWrapper.class);
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      String var10002 = this.logDir();
      return var10000.stripMargin$extension(var10001.augmentString("|FsHistoryProvider{logdir=" + var10002 + ",\n        |  storedir=" + this.storePath() + ",\n        |  last scan time=" + this.lastScanTime() + "\n        |  application count=" + count + "}"));
   }

   private ApplicationInfoWrapper load(final String appId) {
      return (ApplicationInfoWrapper)this.listing().read(ApplicationInfoWrapper.class, appId);
   }

   private void addListing(final ApplicationInfoWrapper app) {
      synchronized(this.listing()){}

      try {
         AttemptInfoWrapper attempt = (AttemptInfoWrapper)app.attempts().head();
         ApplicationInfoWrapper oldApp = this.liftedTree1$1(app);
         List attempts = (List)oldApp.attempts().filter((x$23) -> BoxesRunTime.boxToBoolean($anonfun$addListing$1(attempt, x$23))).$plus$plus(new scala.collection.immutable..colon.colon(attempt, scala.collection.immutable.Nil..MODULE$));
         ApplicationInfoWrapper newAppInfo = new ApplicationInfoWrapper(app.info(), (List)attempts.sortWith((a1, a2) -> BoxesRunTime.boxToBoolean($anonfun$addListing$2(a1, a2))));
         this.listing().write(newAppInfo);
      } catch (Throwable var8) {
         throw var8;
      }

   }

   private KVStore loadDiskStore(final HistoryServerDiskManager dm, final String appId, final AttemptInfoWrapper attempt) {
      Object var5 = new Object();

      KVStore var10000;
      try {
         AppStatusStoreMetadata metadata = new AppStatusStoreMetadata(AppStatusStore$.MODULE$.CURRENT_VERSION());
         dm.openStore(appId, attempt.info().attemptId()).foreach((path) -> {
            $anonfun$loadDiskStore$1(this, var5, metadata, appId, attempt, dm, path);
            return BoxedUnit.UNIT;
         });
         if (this.hybridStoreEnabled()) {
            try {
               return this.createHybridStore(dm, appId, attempt, metadata);
            } catch (Throwable var12) {
               label51: {
                  if (var12 instanceof RuntimeException) {
                     RuntimeException var9 = (RuntimeException)var12;
                     if (var9.getMessage() != null && var9.getMessage().contains("Not enough memory to create hybrid")) {
                        this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create HybridStore for"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "/", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attempt.info().attemptId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Using ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HYBRID_STORE_DISK_BACKEND..MODULE$, this.hybridStoreDiskBackend())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXCEPTION..MODULE$, var9.getMessage())}))))));
                        BoxedUnit var15 = BoxedUnit.UNIT;
                        break label51;
                     }
                  }

                  if (!(var12 instanceof Exception)) {
                     throw var12;
                  }

                  Exception var10 = (Exception)var12;
                  this.logInfo((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create HybridStore for"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "/", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attempt.info().attemptId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Using ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HYBRID_STORE_DISK_BACKEND..MODULE$, this.hybridStoreDiskBackend())}))))), var10);
                  BoxedUnit var14 = BoxedUnit.UNIT;
               }
            }
         }

         var10000 = this.createDiskStore(dm, appId, attempt, metadata);
      } catch (NonLocalReturnControl var13) {
         if (var13.key() != var5) {
            throw var13;
         }

         var10000 = (KVStore)var13.value();
      }

      return var10000;
   }

   private KVStore createHybridStore(final HistoryServerDiskManager dm, final String appId, final AttemptInfoWrapper attempt, final AppStatusStoreMetadata metadata) {
      boolean retried = false;
      ObjectRef hybridStore = ObjectRef.create((Object)null);
      EventLogFileReader reader = EventLogFileReader$.MODULE$.apply(this.fs(), new Path(this.logDir(), attempt.logPath()), attempt.lastIndex());

      while((HybridStore)hybridStore.elem == null) {
         this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager().lease(appId, attempt.info().attemptId(), reader.totalSize(), reader.compressionCodec());
         HybridStore store = null;

         try {
            store = new HybridStore();
            this.rebuildAppStore(store, reader, attempt.info().lastUpdated().getTime());
            hybridStore.elem = store;
         } catch (Throwable var18) {
            if (var18 instanceof IOException var12) {
               if (!retried) {
                  this.logInfo((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception occurred while rebuilding log path "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " - "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, attempt.logPath())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"trying again..."})))).log(scala.collection.immutable.Nil..MODULE$))), var12);
                  store.close();
                  this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager().release(appId, attempt.info().attemptId());
                  retried = true;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  continue;
               }
            }

            if (var18 instanceof Exception var13) {
               store.close();
               this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager().release(appId, attempt.info().attemptId());
               throw var13;
            }

            throw var18;
         }
      }

      ObjectRef lease = ObjectRef.create((Object)null);

      try {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Leasing disk manager space for app"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " / ", "..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attempt.info().attemptId())}))))));
         lease.elem = dm.lease(reader.totalSize(), reader.compressionCodec().isDefined());
         KVStore diskStore = KVUtils$.MODULE$.open(((HistoryServerDiskManager.Lease)lease.elem).tmpPath(), metadata, this.org$apache$spark$deploy$history$FsHistoryProvider$$conf, false, scala.reflect.ClassTag..MODULE$.apply(AppStatusStoreMetadata.class));
         ((HybridStore)hybridStore.elem).setDiskStore(diskStore);
         ((HybridStore)hybridStore.elem).switchToDiskStore(new HybridStore.SwitchToDiskStoreListener(appId, attempt, diskStore, lease, hybridStore, metadata) {
            // $FF: synthetic field
            private final FsHistoryProvider $outer;
            private final String appId$4;
            private final AttemptInfoWrapper attempt$4;
            private final KVStore diskStore$1;
            private final ObjectRef lease$1;
            private final ObjectRef hybridStore$1;
            private final AppStatusStoreMetadata metadata$2;

            public void onSwitchToDiskStoreSuccess() {
               this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Completely switched to diskStore for app"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " / ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.appId$4), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, this.attempt$4.info().attemptId())}))))));
               this.diskStore$1.close();
               File newStorePath = ((HistoryServerDiskManager.Lease)this.lease$1.elem).commit(this.appId$4, this.attempt$4.info().attemptId());
               ((HybridStore)this.hybridStore$1.elem).setDiskStore(KVUtils$.MODULE$.open(newStorePath, this.metadata$2, this.$outer.org$apache$spark$deploy$history$FsHistoryProvider$$conf, false, scala.reflect.ClassTag..MODULE$.apply(AppStatusStoreMetadata.class)));
               this.$outer.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager().release(this.appId$4, this.attempt$4.info().attemptId());
            }

            public void onSwitchToDiskStoreFail(final Exception e) {
               this.$outer.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to switch to diskStore for app ", " / "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.appId$4)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, this.attempt$4.info().attemptId())}))))), e);
               this.diskStore$1.close();
               ((HistoryServerDiskManager.Lease)this.lease$1.elem).rollback();
            }

            public {
               if (FsHistoryProvider.this == null) {
                  throw null;
               } else {
                  this.$outer = FsHistoryProvider.this;
                  this.appId$4 = appId$4;
                  this.attempt$4 = attempt$4;
                  this.diskStore$1 = diskStore$1;
                  this.lease$1 = lease$1;
                  this.hybridStore$1 = hybridStore$1;
                  this.metadata$2 = metadata$2;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }, appId, attempt.info().attemptId());
      } catch (Exception var17) {
         ((HybridStore)hybridStore.elem).close();
         this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager().release(appId, attempt.info().attemptId());
         if ((HistoryServerDiskManager.Lease)lease.elem != null) {
            ((HistoryServerDiskManager.Lease)lease.elem).rollback();
         }

         throw var17;
      }

      return (HybridStore)hybridStore.elem;
   }

   private KVStore createDiskStore(final HistoryServerDiskManager dm, final String appId, final AttemptInfoWrapper attempt, final AppStatusStoreMetadata metadata) {
      boolean retried = false;
      File newStorePath = null;

      while(newStorePath == null) {
         EventLogFileReader reader = EventLogFileReader$.MODULE$.apply(this.fs(), new Path(this.logDir(), attempt.logPath()), attempt.lastIndex());
         boolean isCompressed = reader.compressionCodec().isDefined();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Leasing disk manager space for app"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " / ", "..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attempt.info().attemptId())}))))));
         HistoryServerDiskManager.Lease lease = dm.lease(reader.totalSize(), isCompressed);

         try {
            Utils$.MODULE$.tryWithResource(() -> KVUtils$.MODULE$.open(lease.tmpPath(), metadata, this.org$apache$spark$deploy$history$FsHistoryProvider$$conf, false, scala.reflect.ClassTag..MODULE$.apply(AppStatusStoreMetadata.class)), (store) -> {
               $anonfun$createDiskStore$3(this, reader, attempt, store);
               return BoxedUnit.UNIT;
            });
            newStorePath = lease.commit(appId, attempt.info().attemptId());
         } catch (Throwable var15) {
            if (var15 instanceof IOException var13) {
               if (!retried) {
                  this.logInfo((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception occurred while rebuilding app ", " - "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"trying again..."})))).log(scala.collection.immutable.Nil..MODULE$))), var13);
                  lease.rollback();
                  retried = true;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  continue;
               }
            }

            if (var15 instanceof Exception var14) {
               lease.rollback();
               throw var14;
            }

            throw var15;
         }
      }

      return KVUtils$.MODULE$.open(newStorePath, metadata, this.org$apache$spark$deploy$history$FsHistoryProvider$$conf, false, scala.reflect.ClassTag..MODULE$.apply(AppStatusStoreMetadata.class));
   }

   private KVStore createInMemoryStore(final AttemptInfoWrapper attempt) {
      boolean retried = false;
      KVStore store = null;

      while(store == null) {
         try {
            InMemoryStore s = new InMemoryStore();
            EventLogFileReader reader = EventLogFileReader$.MODULE$.apply(this.fs(), new Path(this.logDir(), attempt.logPath()), attempt.lastIndex());
            this.rebuildAppStore(s, reader, attempt.info().lastUpdated().getTime());
            store = s;
         } catch (Throwable var11) {
            if (var11 instanceof IOException var9) {
               if (!retried) {
                  this.logInfo((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception occurred while rebuilding log path "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " - trying again..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, attempt.logPath())}))))), var9);
                  retried = true;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  continue;
               }
            }

            if (var11 instanceof Exception var10) {
               throw var10;
            }

            throw var11;
         }
      }

      return store;
   }

   private Iterable loadPlugins() {
      return scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(ServiceLoader.load(AppHistoryServerPlugin.class, Utils$.MODULE$.getContextOrSparkClassLoader())).asScala();
   }

   public AttemptInfoWrapper getAttempt(final String appId, final Option attemptId) {
      return (AttemptInfoWrapper)this.load(appId).attempts().find((x$24) -> BoxesRunTime.boxToBoolean($anonfun$getAttempt$1(attemptId, x$24))).getOrElse(() -> {
         throw new NoSuchElementException("Cannot find attempt " + attemptId + " of " + appId + ".");
      });
   }

   private boolean deleteLog(final FileSystem fs, final Path log) {
      boolean deleted = false;
      if (!this.isAccessible(log)) {
         this.logDebug((Function0)(() -> "Skipping deleting " + log + " as we don't have permissions on it."));
      } else {
         try {
            deleted = fs.delete(log, true);
         } catch (AccessControlException var5) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No permission to delete ", ", ignoring."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, log)})))));
         } catch (IOException var6) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"IOException in cleaning ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, log)})))), var6);
         }
      }

      return deleted;
   }

   private void submitLogProcessTask(final Path rootPath, final Runnable task) {
      try {
         this.processing(rootPath);
         this.replayExecutor().submit(task);
      } catch (Exception var4) {
         this.logError((Function0)(() -> "Exception while submitting task"), var4);
         this.endProcessing(rootPath);
      }

   }

   private SecurityManager createSecurityManager(final SparkConf conf, final AttemptInfoWrapper attempt) {
      SecurityManager secManager = new SecurityManager(conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
      secManager.setAcls(this.historyUiAclsEnable());
      secManager.setAdminAcls((Seq)this.historyUiAdminAcls().$plus$plus(this.stringToSeq((String)attempt.adminAcls().getOrElse(() -> ""))));
      secManager.setViewAcls(attempt.info().sparkUser(), this.stringToSeq((String)attempt.viewAcls().getOrElse(() -> "")));
      secManager.setAdminAclsGroups((Seq)this.historyUiAdminAclsGroups().$plus$plus(this.stringToSeq((String)attempt.adminAclsGroups().getOrElse(() -> ""))));
      secManager.setViewAclsGroups(this.stringToSeq((String)attempt.viewAclsGroups().getOrElse(() -> "")));
      return secManager;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$clearInaccessibleList$1(final long expiredThreshold$1, final String x$2, final long creationTime) {
      return creationTime >= expiredThreshold$1;
   }

   // $FF: synthetic method
   public static final void $anonfun$startPolling$1(final HistoryServerDiskManager x$4) {
      x$4.initialize();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stringToSeq$2(final String x$7) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$7));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAppUI$1(final Option attemptId$1, final AttemptInfoWrapper x$8) {
      boolean var3;
      label23: {
         Option var10000 = x$8.info().attemptId();
         if (var10000 == null) {
            if (attemptId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(attemptId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final int $anonfun$getAppUI$2(final AppHistoryServerPlugin x$9) {
      return x$9.displayOrder();
   }

   // $FF: synthetic method
   public static final void $anonfun$getAppUI$3(final SparkUI ui$1, final AppHistoryServerPlugin x$10) {
      x$10.setupUI(ui$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final Tuple2 x0$1) {
      if (x0$1 != null) {
         LoadedAppUI loadedUI = (LoadedAppUI)x0$1._2();
         loadedUI.ui().store().close();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onUIDetached$2(final String appId$1, final Option attemptId$2, final LoadedAppUI loadedUI$1, final HistoryServerDiskManager dm) {
      dm.release(appId$1, attemptId$2, !loadedUI$1.valid());
   }

   // $FF: synthetic method
   public static final void $anonfun$onUIDetached$1(final FsHistoryProvider $this, final String appId$1, final Option attemptId$2, final LoadedAppUI loadedUI) {
      loadedUI.lock().writeLock().lock();

      try {
         loadedUI.ui().store().close();
      } finally {
         loadedUI.lock().writeLock().unlock();
      }

      $this.diskManager().foreach((dm) -> {
         $anonfun$onUIDetached$2(appId$1, attemptId$2, loadedUI, dm);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkUIViewPermissions$1(final Option attemptId$3, final AttemptInfoWrapper x$11) {
      boolean var3;
      label23: {
         Option var10000 = x$11.info().attemptId();
         if (var10000 == null) {
            if (attemptId$3 == null) {
               break label23;
            }
         } else if (var10000.equals(attemptId$3)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$4(final FsHistoryProvider $this, final FileStatus entry) {
      return $this.isAccessible(entry.getPath());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$5(final FsHistoryProvider $this, final HashSet notStale$1, final FileStatus entry) {
      if ($this.isProcessing(entry.getPath())) {
         notStale$1.add(entry.getPath().toString());
         return false;
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$7(final FsHistoryProvider $this, final EventLogFileReader reader) {
      boolean var10000;
      try {
         reader.modificationTime();
         var10000 = true;
      } catch (IllegalArgumentException var3) {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception in getting modificationTime of"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ". ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath().getName()), new MDC(org.apache.spark.internal.LogKeys.EXCEPTION..MODULE$, var3.toString())}))))));
         var10000 = false;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$9(final EventLogFileReader x0$1, final EventLogFileReader x1$1) {
      Tuple2 var3 = new Tuple2(x0$1, x1$1);
      if (var3 != null) {
         EventLogFileReader entry1 = (EventLogFileReader)var3._1();
         EventLogFileReader entry2 = (EventLogFileReader)var3._2();
         return entry1.modificationTime() > entry2.modificationTime();
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$10(final FsHistoryProvider $this, final long newLastScanTime$1, final IntRef count$1, final EventLogFileReader reader) {
      boolean var10000;
      try {
         LogInfo info = (LogInfo)$this.listing().read(LogInfo.class, reader.rootPath().toString());
         if (info.appId().isDefined()) {
            KVStore var30 = $this.listing();
            long x$2 = reader.fileSizeForLastIndex();
            Option x$3 = reader.lastIndex();
            boolean x$4 = reader.completed();
            String x$5 = info.copy$default$1();
            Enumeration.Value x$6 = info.copy$default$3();
            Option x$7 = info.copy$default$4();
            Option x$8 = info.copy$default$5();
            Option x$9 = info.copy$default$8();
            var30.write(info.copy(x$5, newLastScanTime$1, x$6, x$7, x$8, x$2, x$3, x$9, x$4));
         }

         if ($this.shouldReloadLog(info, reader)) {
            if (info.appId().isDefined() && reader.lastIndex().isEmpty() && $this.fastInProgressParsing()) {
               ApplicationInfoWrapper appInfo = (ApplicationInfoWrapper)$this.listing().read(ApplicationInfoWrapper.class, info.appId().get());
               List attemptList = appInfo.attempts().map((attempt) -> {
                  label14: {
                     Option var10000 = attempt.info().attemptId();
                     Option var4 = info.attemptId();
                     if (var10000 == null) {
                        if (var4 == null) {
                           break label14;
                        }
                     } else if (var10000.equals(var4)) {
                        break label14;
                     }

                     return attempt;
                  }

                  Date x$10 = new Date(newLastScanTime$1);
                  Option x$11 = attempt.info().copy$default$1();
                  Date x$12 = attempt.info().copy$default$2();
                  Date x$13 = attempt.info().copy$default$3();
                  long x$14 = attempt.info().copy$default$5();
                  String x$15 = attempt.info().copy$default$6();
                  boolean x$16 = attempt.info().copy$default$7();
                  String x$17 = attempt.info().copy$default$8();
                  return new AttemptInfoWrapper(attempt.info().copy(x$11, x$12, x$13, x$10, x$14, x$15, x$16, x$17), attempt.logPath(), attempt.fileSize(), attempt.lastIndex(), attempt.adminAcls(), attempt.viewAcls(), attempt.adminAclsGroups(), attempt.viewAclsGroups());
               });
               ApplicationInfoWrapper updatedAppInfo = new ApplicationInfoWrapper(appInfo.info(), attemptList);
               $this.listing().write(updatedAppInfo);
               $this.invalidateUI((String)info.appId().get(), info.attemptId());
               var10000 = false;
            } else {
               var10000 = true;
            }
         } else {
            var10000 = false;
         }
      } catch (Throwable var29) {
         if (var29 instanceof NoSuchElementException) {
            try {
               if (BoxesRunTime.unboxToBoolean($this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.CLEANER_ENABLED())) && reader.modificationTime() < $this.clock.getTimeMillis() - BoxesRunTime.unboxToLong($this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.MAX_LOG_AGE_S())) * 1000L) {
                  $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting expired event log ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath().toString())})))));
                  $this.deleteLog($this.fs(), reader.rootPath());
                  $this.listing().delete(LogInfo.class, reader.rootPath().toString());
                  var10000 = false;
               } else if (count$1.elem < BoxesRunTime.unboxToInt($this.org$apache$spark$deploy$history$FsHistoryProvider$$conf.get(History$.MODULE$.UPDATE_BATCHSIZE()))) {
                  $this.listing().write(new LogInfo(reader.rootPath().toString(), newLastScanTime$1, LogType$.MODULE$.EventLogs(), scala.None..MODULE$, scala.None..MODULE$, reader.fileSizeForLastIndex(), reader.lastIndex(), scala.None..MODULE$, reader.completed()));
                  ++count$1.elem;
                  var10000 = reader.fileSizeForLastIndex() > 0L;
               } else {
                  var10000 = false;
               }
            } catch (Throwable var28) {
               if (var28 instanceof FileNotFoundException) {
                  var10000 = false;
               } else if (var28 instanceof NoSuchElementException) {
                  var10000 = false;
               } else {
                  if (var28 == null || !scala.util.control.NonFatal..MODULE$.apply(var28)) {
                     throw var28;
                  }

                  $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error while reading new log "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath())}))))), var28);
                  var10000 = false;
               }
            }
         } else {
            if (var29 == null || !scala.util.control.NonFatal..MODULE$.apply(var29)) {
               throw var29;
            }

            $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error while filtering log ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, reader.rootPath())})))), var29);
            var10000 = false;
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$checkForLogs$17(final FsHistoryProvider $this, final long newLastScanTime$1, final EventLogFileReader entry) {
      $this.submitLogProcessTask(entry.rootPath(), () -> $this.mergeApplicationListing(entry, newLastScanTime$1, true));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$19(final FsHistoryProvider $this, final LogInfo info) {
      return $this.isProcessing(info);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkForLogs$20(final HashSet notStale$1, final LogInfo info) {
      return notStale$1.contains(info.logPath());
   }

   // $FF: synthetic method
   public static final void $anonfun$checkForLogs$22(final FsHistoryProvider $this, final LogInfo log$1, final String appId) {
      $this.cleanAppData(appId, log$1.attemptId(), log$1.logPath());
      $this.listing().delete(LogInfo.class, log$1.logPath());
   }

   // $FF: synthetic method
   public static final void $anonfun$checkForLogs$21(final FsHistoryProvider $this, final LogInfo log) {
      log.appId().foreach((appId) -> {
         $anonfun$checkForLogs$22($this, log, appId);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanAppData$1(final Option attemptId$4, final AttemptInfoWrapper x$15) {
      boolean var3;
      label23: {
         Option var10000 = x$15.info().attemptId();
         if (var10000 == null) {
            if (attemptId$4 == null) {
               break label23;
            }
         } else if (var10000.equals(attemptId$4)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanAppData$2(final FsHistoryProvider $this, final String logPath$1, final List others$1, final ApplicationInfoWrapper app$1, final String appId$2, final AttemptInfoWrapper a) {
      String var10000 = a.logPath();
      String var6 = (new Path(logPath$1)).getName();
      if (var10000 == null) {
         if (var6 != null) {
            return false;
         }
      } else if (!var10000.equals(var6)) {
         return false;
      }

      if (others$1.nonEmpty()) {
         ApplicationInfoWrapper newAppInfo = new ApplicationInfoWrapper(app$1.info(), others$1);
         $this.listing().write(newAppInfo);
      } else {
         $this.listing().delete(ApplicationInfoWrapper.class, appId$2);
      }

      return true;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanAppData$3(final LoadedAppUI ui) {
      ui.invalidate();
      ui.ui().store().close();
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanAppData$4(final String appId$2, final Option attemptId$4, final HistoryServerDiskManager x$17) {
      x$17.release(appId$2, attemptId$4, true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$writeEventLogs$2(final String id$1, final AttemptInfoWrapper x$18) {
      boolean var3;
      label23: {
         Option var10000 = x$18.info().attemptId();
         Some var2 = new Some(id$1);
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
   public static final void $anonfun$writeEventLogs$4(final FsHistoryProvider $this, final ZipOutputStream zipStream$1, final AttemptInfoWrapper attempt) {
      EventLogFileReader reader = EventLogFileReader$.MODULE$.apply($this.fs(), new Path($this.logDir(), attempt.logPath()), attempt.lastIndex());
      reader.zipEventLogFiles(zipStream$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doMergeApplicationListingInternal$1(final String eventString) {
      return eventString.startsWith(FsHistoryProvider$.MODULE$.org$apache$spark$deploy$history$FsHistoryProvider$$APPL_START_EVENT_PREFIX()) || eventString.startsWith(FsHistoryProvider$.MODULE$.org$apache$spark$deploy$history$FsHistoryProvider$$APPL_END_EVENT_PREFIX()) || eventString.startsWith(FsHistoryProvider$.MODULE$.org$apache$spark$deploy$history$FsHistoryProvider$$LOG_START_EVENT_PREFIX()) || eventString.startsWith(FsHistoryProvider$.MODULE$.org$apache$spark$deploy$history$FsHistoryProvider$$ENV_UPDATE_EVENT_PREFIX());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doMergeApplicationListingInternal$4(final FsHistoryProvider $this, final FileStatus lastFile$1, final long reparseChunkSize$1, final Path logPath$2, final ReplayListenerBus bus$1, final boolean appCompleted$1, final Function1 eventsFilter$1, final InputStream in) {
      long target = lastFile$1.getLen() - reparseChunkSize$1;
      if (target > 0L) {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Looking for end event; skipping ", " bytes"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(target))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", "..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logPath$2)}))))));

         for(long skipped = 0L; skipped < target; skipped += in.skip(target - skipped)) {
         }
      }

      Iterator source = scala.io.Source..MODULE$.fromInputStream(in, scala.io.Codec..MODULE$.UTF8()).getLines();
      if (target > 0L) {
         source.next();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return bus$1.replay(source, lastFile$1.getPath().toString(), !appCompleted$1, eventsFilter$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$invalidateUI$1(final LoadedAppUI ui) {
      ui.invalidate();
      ui.ui().store().close();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkAndCleanLog$4(final long maxTime$1, final AttemptInfoWrapper attempt) {
      return attempt.info().lastUpdated().getTime() >= maxTime$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanLogs$3(final long maxTime$2, final AttemptInfoWrapper attempt) {
      return attempt.info().lastUpdated().getTime() >= maxTime$2;
   }

   // $FF: synthetic method
   public static final int $anonfun$cleanLogs$2(final FsHistoryProvider $this, final long maxTime$2, final ApplicationInfoWrapper app) {
      Tuple2 var6 = app.attempts().partition((attempt) -> BoxesRunTime.boxToBoolean($anonfun$cleanLogs$3(maxTime$2, attempt)));
      if (var6 != null) {
         List remaining = (List)var6._1();
         List toDelete = (List)var6._2();
         Tuple2 var5 = new Tuple2(remaining, toDelete);
         List remaining = (List)var5._1();
         List toDelete = (List)var5._2();
         return $this.deleteAttemptLogs(app, remaining, toDelete);
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanLogs$4(final LogInfo l) {
      boolean var2;
      if (l.logType() != null) {
         label29: {
            Enumeration.Value var10000 = l.logType();
            Enumeration.Value var1 = LogType$.MODULE$.EventLogs();
            if (var10000 == null) {
               if (var1 == null) {
                  break label29;
               }
            } else if (var10000.equals(var1)) {
               break label29;
            }

            var2 = false;
            return var2;
         }
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanLogs$5(final FsHistoryProvider $this, final LogInfo info) {
      return $this.isProcessing(info);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanLogs$6(final FsHistoryProvider $this, final LogInfo log) {
      if (log.appId().isEmpty()) {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting invalid / corrupt event log ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, log.logPath())})))));
         $this.deleteLog($this.fs(), new Path(log.logPath()));
         $this.listing().delete(LogInfo.class, log.logPath());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanLogs$10(final AttemptInfoWrapper x$21) {
      return x$21.info().completed();
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanLogs$9(final FsHistoryProvider $this, final IntRef count$2, final ApplicationInfoWrapper app) {
      if (count$2.elem > 0) {
         Tuple2 var5 = app.attempts().partition((x$21) -> BoxesRunTime.boxToBoolean($anonfun$cleanLogs$10(x$21)));
         if (var5 != null) {
            List toDelete = (List)var5._1();
            List remaining = (List)var5._2();
            Tuple2 var4 = new Tuple2(toDelete, remaining);
            List toDelete = (List)var4._1();
            List remaining = (List)var4._2();
            count$2.elem -= $this.deleteAttemptLogs(app, remaining, toDelete);
         } else {
            throw new MatchError(var5);
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$deleteAttemptLogs$1(final FsHistoryProvider $this, final ApplicationInfoWrapper app$3, final IntRef countDeleted$1, final AttemptInfoWrapper attempt) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting expired event log for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, attempt.logPath())})))));
      Path logPath = new Path($this.logDir(), attempt.logPath());
      $this.listing().delete(LogInfo.class, logPath.toString());
      $this.cleanAppData(app$3.id(), attempt.info().attemptId(), logPath.toString());
      if ($this.deleteLog($this.fs(), logPath)) {
         ++countDeleted$1.elem;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanDriverLogs$4(final LogInfo l) {
      boolean var2;
      label25: {
         if (l.logType() != null) {
            Enumeration.Value var10000 = l.logType();
            Enumeration.Value var1 = LogType$.MODULE$.DriverLogs();
            if (var10000 == null) {
               if (var1 == null) {
                  break label25;
               }
            } else if (var10000.equals(var1)) {
               break label25;
            }
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanDriverLogs$5(final FsHistoryProvider $this, final LogInfo info) {
      return $this.isProcessing(info);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanDriverLogs$6(final FsHistoryProvider $this, final FileSystem driverLogFs$1, final LogInfo log) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting invalid driver log ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, log.logPath())})))));
      $this.listing().delete(LogInfo.class, log.logPath());
      return $this.deleteLog(driverLogFs$1, new Path(log.logPath()));
   }

   // $FF: synthetic method
   public static final void $anonfun$rebuildAppStore$2(final ReplayListenerBus replayBus$1, final SparkListener listener) {
      replayBus$1.addListener(listener);
   }

   // $FF: synthetic method
   public static final void $anonfun$rebuildAppStore$1(final FsHistoryProvider $this, final ElementTrackingStore trackingStore$1, final ReplayListenerBus replayBus$1, final AppHistoryServerPlugin plugin) {
      plugin.createListeners($this.org$apache$spark$deploy$history$FsHistoryProvider$$conf, trackingStore$1).foreach((listener) -> {
         $anonfun$rebuildAppStore$2(replayBus$1, listener);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$parseAppEventLogs$3(final BooleanRef continueReplay$1, final ReplayListenerBus replayBus$2, final FileStatus file$1, final boolean maybeTruncated$1, final Function1 eventsFilter$2, final InputStream in) {
      continueReplay$1.elem = replayBus$2.replay(in, file$1.getPath().toString(), maybeTruncated$1, eventsFilter$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$parseAppEventLogs$1(final FsHistoryProvider $this, final BooleanRef continueReplay$1, final ReplayListenerBus replayBus$2, final boolean maybeTruncated$1, final Function1 eventsFilter$2, final FileStatus file) {
      if (continueReplay$1.elem) {
         Utils$.MODULE$.tryWithResource(() -> EventLogFileReader$.MODULE$.openEventLog(file.getPath(), $this.fs()), (in) -> {
            $anonfun$parseAppEventLogs$3(continueReplay$1, replayBus$2, file, maybeTruncated$1, eventsFilter$2, in);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   private final ApplicationInfoWrapper liftedTree1$1(final ApplicationInfoWrapper app$4) {
      ApplicationInfoWrapper var10000;
      try {
         var10000 = this.load(app$4.id());
      } catch (NoSuchElementException var2) {
         var10000 = app$4;
      }

      return var10000;
   }

   private static final boolean compareAttemptInfo$1(final AttemptInfoWrapper a1, final AttemptInfoWrapper a2) {
      return a1.info().startTime().getTime() > a2.info().startTime().getTime();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addListing$1(final AttemptInfoWrapper attempt$2, final AttemptInfoWrapper x$23) {
      boolean var3;
      label23: {
         Option var10000 = x$23.info().attemptId();
         Option var2 = attempt$2.info().attemptId();
         if (var10000 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addListing$2(final AttemptInfoWrapper a1, final AttemptInfoWrapper a2) {
      return compareAttemptInfo$1(a1, a2);
   }

   // $FF: synthetic method
   public static final void $anonfun$loadDiskStore$1(final FsHistoryProvider $this, final Object nonLocalReturnKey1$1, final AppStatusStoreMetadata metadata$1, final String appId$3, final AttemptInfoWrapper attempt$3, final HistoryServerDiskManager dm$1, final File path) {
      try {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, KVUtils$.MODULE$.open(path, metadata$1, $this.org$apache$spark$deploy$history$FsHistoryProvider$$conf, false, scala.reflect.ClassTag..MODULE$.apply(AppStatusStoreMetadata.class)));
      } catch (Exception var8) {
         $this.logInfo((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to open existing store for"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "/", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId$3), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attempt$3.info().attemptId())}))))), var8);
         dm$1.release(appId$3, attempt$3.info().attemptId(), true);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createDiskStore$3(final FsHistoryProvider $this, final EventLogFileReader reader$5, final AttemptInfoWrapper attempt$5, final KVStore store) {
      $this.rebuildAppStore(store, reader$5, attempt$5.info().lastUpdated().getTime());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAttempt$1(final Option attemptId$5, final AttemptInfoWrapper x$24) {
      boolean var3;
      label23: {
         Option var10000 = x$24.info().attemptId();
         if (var10000 == null) {
            if (attemptId$5 == null) {
               break label23;
            }
         } else if (var10000.equals(attemptId$5)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public FsHistoryProvider(final SparkConf conf, final Clock clock) {
      this.org$apache$spark$deploy$history$FsHistoryProvider$$conf = conf;
      this.clock = clock;
      Logging.$init$(this);
      this.SAFEMODE_CHECK_INTERVAL_S = BoxesRunTime.unboxToLong(conf.get(History$.MODULE$.SAFEMODE_CHECK_INTERVAL_S()));
      this.UPDATE_INTERVAL_S = BoxesRunTime.unboxToLong(conf.get(History$.MODULE$.UPDATE_INTERVAL_S()));
      this.CLEAN_INTERVAL_S = BoxesRunTime.unboxToLong(conf.get(History$.MODULE$.CLEANER_INTERVAL_S()));
      this.NUM_PROCESSING_THREADS = BoxesRunTime.unboxToInt(conf.get(History$.MODULE$.NUM_REPLAY_THREADS()));
      this.logDir = (String)conf.get(History$.MODULE$.HISTORY_LOG_DIR());
      this.historyUiAclsEnable = BoxesRunTime.unboxToBoolean(conf.get(History$.MODULE$.HISTORY_SERVER_UI_ACLS_ENABLE()));
      this.historyUiAdminAcls = (Seq)conf.get(History$.MODULE$.HISTORY_SERVER_UI_ADMIN_ACLS());
      this.historyUiAdminAclsGroups = (Seq)conf.get(History$.MODULE$.HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"History server ui acls"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ACL_ENABLED..MODULE$, this.historyUiAclsEnable() ? "enabled" : "disabled")})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; users with admin permissions:"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ADMIN_ACLS..MODULE$, this.historyUiAdminAcls().mkString(","))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; groups with admin permissions:"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ADMIN_ACL_GROUPS..MODULE$, this.historyUiAdminAclsGroups().mkString(","))}))))));
      this.hadoopConf = SparkHadoopUtil$.MODULE$.get().newConfiguration(conf);
      this.fs = (new Path(this.logDir())).getFileSystem(this.hadoopConf());
      this.pool = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("spark-history-task-%d");
      this.lastScanTime = new AtomicLong(-1L);
      this.pendingReplayTasksCount = new AtomicInteger(0);
      this.storePath = ((Option)conf.get((ConfigEntry)History$.MODULE$.LOCAL_STORE_DIR())).map((x$1) -> new File(x$1));
      this.fastInProgressParsing = BoxesRunTime.unboxToBoolean(conf.get(History$.MODULE$.FAST_IN_PROGRESS_PARSING()));
      this.hybridStoreEnabled = BoxesRunTime.unboxToBoolean(conf.get(History$.MODULE$.HYBRID_STORE_ENABLED()));
      this.hybridStoreDiskBackend = History.HybridStoreDiskBackend$.MODULE$.withName((String)conf.get(History$.MODULE$.HYBRID_STORE_DISK_BACKEND()));
      this.listing = KVUtils$.MODULE$.createKVStore(this.storePath(), false, conf);
      this.diskManager = this.storePath().map((path) -> new HistoryServerDiskManager(this.org$apache$spark$deploy$history$FsHistoryProvider$$conf, path, this.listing(), this.clock));
      this.org$apache$spark$deploy$history$FsHistoryProvider$$memoryManager = null;
      if (this.hybridStoreEnabled()) {
         this.memoryManager_$eq(new HistoryServerMemoryManager(conf));
      }

      this.fileCompactor = new EventLogFileCompactor(conf, this.hadoopConf(), this.fs(), BoxesRunTime.unboxToInt(conf.get(History$.MODULE$.EVENT_LOG_ROLLING_MAX_FILES_TO_RETAIN())), BoxesRunTime.unboxToDouble(conf.get(History$.MODULE$.EVENT_LOG_COMPACTION_SCORE_THRESHOLD())));
      this.processing = ConcurrentHashMap.newKeySet();
      this.inaccessibleList = new ConcurrentHashMap();
      this.activeUIs = new HashMap();
      this.replayExecutor = (ExecutorService)(!Utils$.MODULE$.isTesting() ? ThreadUtils$.MODULE$.newDaemonFixedThreadPool(this.NUM_PROCESSING_THREADS(), "log-replay-executor") : ThreadUtils$.MODULE$.sameThreadExecutorService());
      this.initThread = null;
   }

   public FsHistoryProvider(final SparkConf conf) {
      this(conf, new SystemClock());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
