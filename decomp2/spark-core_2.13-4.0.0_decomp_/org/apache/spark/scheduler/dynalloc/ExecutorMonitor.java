package org.apache.spark.scheduler.dynalloc;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.CleanerListener;
import org.apache.spark.ExecutorAllocationClient;
import org.apache.spark.ExecutorAllocationManagerSource;
import org.apache.spark.SparkConf;
import org.apache.spark.Success$;
import org.apache.spark.TaskEndReason;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.scheduler.ExecutorDecommission$;
import org.apache.spark.scheduler.ExecutorLossMessage$;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerBlockUpdated;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.SparkListenerJobEnd;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.SparkListenerTaskStart;
import org.apache.spark.scheduler.SparkListenerUnpersistRDD;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.ShuffleDataBlockId;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Eh!CA\n\u0003+\u0001\u0011QDA\u0015\u0011)\t9\u0005\u0001B\u0001B\u0003%\u00111\n\u0005\u000b\u0003#\u0002!\u0011!Q\u0001\n\u0005M\u0003BCA-\u0001\t\u0005\t\u0015!\u0003\u0002\\!Q\u0011\u0011\r\u0001\u0003\u0002\u0003\u0006I!a\u0019\t\u0015\u0005=\u0004A!A!\u0002\u0013\t\t\bC\u0004\u0002x\u0001!\t!!\u001f\t\u0013\u0005%\u0005A1A\u0005\n\u0005-\u0005\u0002CAM\u0001\u0001\u0006I!!$\t\u0013\u0005m\u0005A1A\u0005\n\u0005-\u0005\u0002CAO\u0001\u0001\u0006I!!$\t\u0013\u0005}\u0005A1A\u0005\n\u0005-\u0005\u0002CAQ\u0001\u0001\u0006I!!$\t\u0013\u0005\r\u0006A1A\u0005\n\u0005\u0015\u0006\u0002CAW\u0001\u0001\u0006I!a*\t\u0013\u0005=\u0006A1A\u0005\n\u0005\u0015\u0006\u0002CAY\u0001\u0001\u0006I!a*\t\u0013\u0005M\u0006A1A\u0005\n\u0005U\u0006\u0002\u0003B]\u0001\u0001\u0006I!a.\t\u0013\tm\u0006A1A\u0005\n\tu\u0006\u0002\u0003Ba\u0001\u0001\u0006IAa0\t\u0013\t\r\u0007A1A\u0005\n\t\u0015\u0007\u0002\u0003Bj\u0001\u0001\u0006IAa2\t\u0013\tU\u0007\u00011A\u0005\n\t]\u0007\"\u0003Bv\u0001\u0001\u0007I\u0011\u0002Bw\u0011!\u0011\t\u0010\u0001Q!\n\te\u0007\"\u0003Bz\u0001\t\u0007I\u0011\u0002B{\u0011!\u0011y\u0010\u0001Q\u0001\n\t]\b\"CB\u0001\u0001\t\u0007I\u0011BB\u0002\u0011!\u00199\u0001\u0001Q\u0001\n\r\u0015\u0001\"CB\u0005\u0001\t\u0007I\u0011BB\u0006\u0011!\u0019\u0019\u0002\u0001Q\u0001\n\r5\u0001bBB\u000b\u0001\u0011\u0005!q\u0012\u0005\b\u0007/\u0001A\u0011AB\r\u0011\u001d\u0019i\u0002\u0001C\u0001\u0007?A\u0011b!\n\u0001\t\u0003\tiba\n\t\u000f\r-\u0002\u0001\"\u0001\u0002p\"91Q\u0006\u0001\u0005\u0002\r=\u0002bBB\u001a\u0001\u0011\u00051Q\u0007\u0005\b\u0007w\u0001A\u0011AAx\u0011\u001d\u0019i\u0004\u0001C\u0001\u0007\u007fAqaa\u0011\u0001\t\u0003\ty\u000fC\u0004\u0004F\u0001!\taa\u0012\t\u000f\r-\u0003\u0001\"\u0011\u0004N!91\u0011\f\u0001\u0005B\rm\u0003bBB3\u0001\u0011\u00053q\r\u0005\b\u0007c\u0002A\u0011IB:\u0011\u001d\u0019i\b\u0001C!\u0007\u007fBqa!#\u0001\t\u0013\u0019Y\tC\u0004\u0004\u0012\u0002!\tea%\t\u000f\ru\u0005\u0001\"\u0011\u0004 \"91\u0011\u0016\u0001\u0005B\r-\u0006bBB[\u0001\u0011\u00053q\u0017\u0005\b\u0007\u0003\u0004A\u0011IBb\u0011\u001d\u0019I\r\u0001C!\u0007\u0017Dqa!5\u0001\t\u0003\u001a\u0019\u000eC\u0004\u0004Z\u0002!\tea7\t\u000f\r\u0005\b\u0001\"\u0011\u0004d\"I1q\u001d\u0001\u0005\u0002\u0005e1\u0011\u001e\u0005\n\u0007/\u0001A\u0011AA\u000b\u0007[D\u0011ba=\u0001\t\u0003\tib!>\t\u0013\ru\b\u0001\"\u0001\u0002\u001e\rU\b\"CB\u0000\u0001\u0011\u0005\u0011\u0011\u0004C\u0001\u0011\u001d!9\u0001\u0001C\u0005\t\u0013Aq\u0001b\u0004\u0001\t\u0013!\tB\u0002\u0005\u0002d\u0002\u0001\u0011\u0011DAs\u0011)\ti/\u0011BA\u0002\u0013\u0005\u0011q\u001e\u0005\u000b\u0003o\f%\u00111A\u0005\u0002\u0005e\bB\u0003B\u0003\u0003\n\u0005\t\u0015)\u0003\u0002r\"9\u0011qO!\u0005\u0002\t\u001d\u0001\"\u0003B\u0006\u0003\u0002\u0007I\u0011AAF\u0011%\u0011i!\u0011a\u0001\n\u0003\u0011y\u0001\u0003\u0005\u0003\u0014\u0005\u0003\u000b\u0015BAG\u0011%\u0011i\"\u0011a\u0001\n\u0003\t)\u000bC\u0005\u0003 \u0005\u0003\r\u0011\"\u0001\u0003\"!A!QE!!B\u0013\t9\u000bC\u0005\u0003*\u0005\u0003\r\u0011\"\u0001\u0002&\"I!1F!A\u0002\u0013\u0005!Q\u0006\u0005\t\u0005c\t\u0005\u0015)\u0003\u0002(\"I!1G!A\u0002\u0013\u0005\u0011Q\u0015\u0005\n\u0005k\t\u0005\u0019!C\u0001\u0005oA\u0001Ba\u000fBA\u0003&\u0011q\u0015\u0005\n\u0005{\t\u0005\u0019!C\u0001\u0003KC\u0011Ba\u0010B\u0001\u0004%\tA!\u0011\t\u0011\t\u0015\u0013\t)Q\u0005\u0003OC\u0011Ba\u0012B\u0001\u0004%I!a#\t\u0013\t%\u0013\t1A\u0005\n\t-\u0003\u0002\u0003B(\u0003\u0002\u0006K!!$\t\u0013\tE\u0013\t1A\u0005\n\u0005=\b\"\u0003B*\u0003\u0002\u0007I\u0011\u0002B+\u0011!\u0011I&\u0011Q!\n\u0005E\b\"\u0003B.\u0003\n\u0007I\u0011\u0001B/\u0011!\u0011)(\u0011Q\u0001\n\t}\u0003\"\u0003B<\u0003\n\u0007I\u0011\u0002B=\u0011!\u0011\t)\u0011Q\u0001\n\tm\u0004b\u0002BB\u0003\u0012\u0005\u0011Q\u0015\u0005\b\u0005\u000b\u000bE\u0011\u0001BD\u0011\u001d\u0011i)\u0011C\u0001\u0005\u001fCqA!%B\t\u0003\u0011\u0019\nC\u0004\u0003\u001a\u0006#\tAa'\t\u000f\t}\u0015\t\"\u0001\u0003\"\u001a1AQ\u0003\u0001E\t/A!Ba&f\u0005+\u0007I\u0011AAx\u0011)!)#\u001aB\tB\u0003%\u0011\u0011\u001f\u0005\b\u0003o*G\u0011\u0001C\u0014\u0011%!i#\u001aC)\u0003;\t)\u000bC\u0005\u00050\u0015\f\t\u0011\"\u0001\u00052!IAQG3\u0012\u0002\u0013\u0005Aq\u0007\u0005\n\t\u001b*\u0017\u0011!C!\t\u001fB\u0011\u0002b\u0017f\u0003\u0003%\t!a<\t\u0013\u0011uS-!A\u0005\u0002\u0011}\u0003\"\u0003C5K\u0006\u0005I\u0011\tC6\u0011%!)(ZA\u0001\n\u0003!9\bC\u0005\u0005|\u0015\f\t\u0011\"\u0011\u0005~!IA\u0011Q3\u0002\u0002\u0013\u0005C1\u0011\u0005\n\t\u000b+\u0017\u0011!C!\t\u000fC\u0011\u0002\"#f\u0003\u0003%\t\u0005b#\b\u0013\u0011=\u0005!!A\t\n\u0011Ee!\u0003C\u000b\u0001\u0005\u0005\t\u0012\u0002CJ\u0011\u001d\t9H\u001eC\u0001\tWC\u0011\u0002\"\"w\u0003\u0003%)\u0005b\"\t\u0013\u00115f/!A\u0005\u0002\u0012=\u0006\"\u0003CZm\u0006\u0005I\u0011\u0011C[\r\u0019!\t\r\u0001\u0003\u0005D\"9\u0011qO>\u0005\u0002\u0011\u0015\u0007\"\u0003BSw\n\u0007I\u0011\u0002Ce\u0011!!im\u001fQ\u0001\n\u0011-\u0007\"\u0003Chw\u0002\u0007I\u0011BAx\u0011%!\tn\u001fa\u0001\n\u0013!\u0019\u000e\u0003\u0005\u0005Xn\u0004\u000b\u0015BAy\u0011\u001d!In\u001fC\u0001\t7Dq\u0001b8|\t\u0003\t)\u000bC\u0004\u0005\u0006n$\t\u0005\"9\b\u0019\u0011\r\u0018QCA\u0001\u0012\u0003\ti\u0002\":\u0007\u0019\u0005M\u0011QCA\u0001\u0012\u0003\ti\u0002b:\t\u0011\u0005]\u0014Q\u0002C\u0001\tSD!\u0002b;\u0002\u000eE\u0005I\u0011\u0001Cw\u0005=)\u00050Z2vi>\u0014Xj\u001c8ji>\u0014(\u0002BA\f\u00033\t\u0001\u0002Z=oC2dwn\u0019\u0006\u0005\u00037\ti\"A\u0005tG\",G-\u001e7fe*!\u0011qDA\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\u0011\t\u0019#!\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\t\t9#A\u0002pe\u001e\u001cr\u0001AA\u0016\u0003g\tY\u0004\u0005\u0003\u0002.\u0005=RBAA\r\u0013\u0011\t\t$!\u0007\u0003\u001bM\u0003\u0018M]6MSN$XM\\3s!\u0011\t)$a\u000e\u000e\u0005\u0005u\u0011\u0002BA\u001d\u0003;\u0011qb\u00117fC:,'\u000fT5ti\u0016tWM\u001d\t\u0005\u0003{\t\u0019%\u0004\u0002\u0002@)!\u0011\u0011IA\u000f\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA#\u0003\u007f\u0011q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0005\u0003k\ti%\u0003\u0003\u0002P\u0005u!!C*qCJ\\7i\u001c8g\u0003\u0019\u0019G.[3oiB!\u0011QGA+\u0013\u0011\t9&!\b\u00031\u0015CXmY;u_J\fE\u000e\\8dCRLwN\\\"mS\u0016tG/A\u0006mSN$XM\\3s\u0005V\u001c\b\u0003BA\u0017\u0003;JA!a\u0018\u0002\u001a\tyA*\u001b<f\u0019&\u001cH/\u001a8fe\n+8/A\u0003dY>\u001c7\u000e\u0005\u0003\u0002f\u0005-TBAA4\u0015\u0011\tI'!\b\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003[\n9GA\u0003DY>\u001c7.A\u0004nKR\u0014\u0018nY:\u0011\t\u0005U\u00121O\u0005\u0005\u0003k\niBA\u0010Fq\u0016\u001cW\u000f^8s\u00032dwnY1uS>tW*\u00198bO\u0016\u00148k\\;sG\u0016\fa\u0001P5oSRtD\u0003DA>\u0003\u007f\n\t)a!\u0002\u0006\u0006\u001d\u0005cAA?\u00015\u0011\u0011Q\u0003\u0005\b\u0003\u000f2\u0001\u0019AA&\u0011\u001d\t\tF\u0002a\u0001\u0003'Bq!!\u0017\u0007\u0001\u0004\tY\u0006C\u0004\u0002b\u0019\u0001\r!a\u0019\t\u0013\u0005=d\u0001%AA\u0002\u0005E\u0014!D5eY\u0016$\u0016.\\3pkRt5/\u0006\u0002\u0002\u000eB!\u0011qRAK\u001b\t\t\tJ\u0003\u0002\u0002\u0014\u0006)1oY1mC&!\u0011qSAI\u0005\u0011auN\\4\u0002\u001d%$G.\u001a+j[\u0016|W\u000f\u001e(tA\u0005\u00012\u000f^8sC\u001e,G+[7f_V$hj]\u0001\u0012gR|'/Y4f)&lWm\\;u\u001dN\u0004\u0013\u0001E:ik\u001a4G.\u001a+j[\u0016|W\u000f\u001e(t\u0003E\u0019\b.\u001e4gY\u0016$\u0016.\\3pkRt5\u000fI\u0001\u001bM\u0016$8\r\u001b$s_6\u001c\u0006.\u001e4gY\u0016\u001cfoY#oC\ndW\rZ\u000b\u0003\u0003O\u0003B!a$\u0002*&!\u00111VAI\u0005\u001d\u0011un\u001c7fC:\f1DZ3uG\"4%o\\7TQV4g\r\\3Tm\u000e,e.\u00192mK\u0012\u0004\u0013AF:ik\u001a4G.\u001a+sC\u000e\\\u0017N\\4F]\u0006\u0014G.\u001a3\u0002/MDWO\u001a4mKR\u0013\u0018mY6j]\u001e,e.\u00192mK\u0012\u0004\u0013!C3yK\u000e,Ho\u001c:t+\t\t9\f\u0005\u0005\u0002:\u0006\u0015\u0017\u0011ZAp\u001b\t\tYL\u0003\u0003\u0002>\u0006}\u0016AC2p]\u000e,(O]3oi*!\u0011\u0011NAa\u0015\t\t\u0019-\u0001\u0003kCZ\f\u0017\u0002BAd\u0003w\u0013\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q!\u0011\tY-!7\u000f\t\u00055\u0017Q\u001b\t\u0005\u0003\u001f\f\t*\u0004\u0002\u0002R*!\u00111[A%\u0003\u0019a$o\\8u}%!\u0011q[AI\u0003\u0019\u0001&/\u001a3fM&!\u00111\\Ao\u0005\u0019\u0019FO]5oO*!\u0011q[AI!\r\t\t/Q\u0007\u0002\u0001\t9AK]1dW\u0016\u00148cA!\u0002hB!\u0011qRAu\u0013\u0011\tY/!%\u0003\r\u0005s\u0017PU3g\u0003E\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0017\nZ\u000b\u0003\u0003c\u0004B!a$\u0002t&!\u0011Q_AI\u0005\rIe\u000e^\u0001\u0016e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#w\fJ3r)\u0011\tYP!\u0001\u0011\t\u0005=\u0015Q`\u0005\u0005\u0003\u007f\f\tJ\u0001\u0003V]&$\b\"\u0003B\u0002\u0007\u0006\u0005\t\u0019AAy\u0003\rAH%M\u0001\u0013e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0007\u0005\u0006\u0003\u0002`\n%\u0001bBAw\u000b\u0002\u0007\u0011\u0011_\u0001\ni&lWm\\;u\u0003R\fQ\u0002^5nK>,H/\u0011;`I\u0015\fH\u0003BA~\u0005#A\u0011Ba\u0001H\u0003\u0003\u0005\r!!$\u0002\u0015QLW.Z8vi\u0006#\b\u0005K\u0002I\u0005/\u0001B!a$\u0003\u001a%!!1DAI\u0005!1x\u000e\\1uS2,\u0017\u0001\u0003;j[\u0016$w*\u001e;\u0002\u0019QLW.\u001a3PkR|F%Z9\u0015\t\u0005m(1\u0005\u0005\n\u0005\u0007Q\u0015\u0011!a\u0001\u0003O\u000b\u0011\u0002^5nK\u0012|U\u000f\u001e\u0011)\u0007-\u00139\"\u0001\bqK:$\u0017N\\4SK6|g/\u00197\u0002%A,g\u000eZ5oOJ+Wn\u001c<bY~#S-\u001d\u000b\u0005\u0003w\u0014y\u0003C\u0005\u0003\u00045\u000b\t\u00111\u0001\u0002(\u0006y\u0001/\u001a8eS:<'+Z7pm\u0006d\u0007%A\beK\u000e|W.\\5tg&|g.\u001b8h\u0003M!WmY8n[&\u001c8/[8oS:<w\fJ3r)\u0011\tYP!\u000f\t\u0013\t\r\u0001+!AA\u0002\u0005\u001d\u0016\u0001\u00053fG>lW.[:tS>t\u0017N\\4!\u0003AA\u0017m]!di&4Xm\u00155vM\u001adW-\u0001\u000biCN\f5\r^5wKNCWO\u001a4mK~#S-\u001d\u000b\u0005\u0003w\u0014\u0019\u0005C\u0005\u0003\u0004M\u000b\t\u00111\u0001\u0002(\u0006\t\u0002.Y:BGRLg/Z*ik\u001a4G.\u001a\u0011\u0002\u0013%$G.Z*uCJ$\u0018!D5eY\u0016\u001cF/\u0019:u?\u0012*\u0017\u000f\u0006\u0003\u0002|\n5\u0003\"\u0003B\u0002-\u0006\u0005\t\u0019AAG\u0003)IG\r\\3Ti\u0006\u0014H\u000fI\u0001\reVtg.\u001b8h)\u0006\u001c8n]\u0001\u0011eVtg.\u001b8h)\u0006\u001c8n]0%KF$B!a?\u0003X!I!1A-\u0002\u0002\u0003\u0007\u0011\u0011_\u0001\u000eeVtg.\u001b8h)\u0006\u001c8n\u001d\u0011\u0002\u0019\r\f7\r[3e\u00052|7m[:\u0016\u0005\t}\u0003\u0003\u0003B1\u0005W\n\tPa\u001c\u000e\u0005\t\r$\u0002\u0002B3\u0005O\nq!\\;uC\ndWM\u0003\u0003\u0003j\u0005E\u0015AC2pY2,7\r^5p]&!!Q\u000eB2\u0005\u001dA\u0015m\u001d5NCB\u0004BA!\u0019\u0003r%!!1\u000fB2\u0005\u0019\u0011\u0015\u000e^*fi\u0006i1-Y2iK\u0012\u0014En\\2lg\u0002\n!b\u001d5vM\u001adW-\u00133t+\t\u0011Y\b\u0005\u0004\u0003b\tu\u0014\u0011_\u0005\u0005\u0005\u007f\u0012\u0019GA\u0004ICND7+\u001a;\u0002\u0017MDWO\u001a4mK&#7\u000fI\u0001\u0007SNLE\r\\3\u0002%U\u0004H-\u0019;f%Vtg.\u001b8h)\u0006\u001c8n\u001d\u000b\u0005\u0003w\u0014I\tC\u0004\u0003\f\u0002\u0004\r!!=\u0002\u000b\u0011,G\u000e^1\u0002\u001bU\u0004H-\u0019;f)&lWm\\;u)\t\tY0\u0001\u0006bI\u0012\u001c\u0006.\u001e4gY\u0016$B!a?\u0003\u0016\"9!q\u00132A\u0002\u0005E\u0018AA5e\u00035\u0011X-\\8wKNCWO\u001a4mKR!\u00111 BO\u0011\u001d\u00119j\u0019a\u0001\u0003c\fA#\u001e9eCR,\u0017i\u0019;jm\u0016\u001c\u0006.\u001e4gY\u0016\u001cH\u0003BA~\u0005GCqA!*e\u0001\u0004\u00119+A\u0002jIN\u0004bA!+\u00034\u0006Eh\u0002\u0002BV\u0005_sA!a4\u0003.&\u0011\u00111S\u0005\u0005\u0005c\u000b\t*A\u0004qC\u000e\\\u0017mZ3\n\t\tU&q\u0017\u0002\t\u0013R,'/\u00192mK*!!\u0011WAI\u0003))\u00070Z2vi>\u00148\u000fI\u0001\u0019Kb,7MU3t_V\u00148-\u001a)s_\u001aLG.Z\"pk:$XC\u0001B`!!\tI,!2\u0002r\u0006E\u0018!G3yK\u000e\u0014Vm]8ve\u000e,\u0007K]8gS2,7i\\;oi\u0002\n1B\\3yiRKW.Z8viV\u0011!q\u0019\t\u0005\u0005\u0013\u0014y-\u0004\u0002\u0003L*!!QZA^\u0003\u0019\tGo\\7jG&!!\u0011\u001bBf\u0005)\tEo\\7jG2{gnZ\u0001\r]\u0016DH\u000fV5nK>,H\u000fI\u0001\u000ei&lW\rZ(vi\u0016CXmY:\u0016\u0005\te\u0007C\u0002Bn\u0005C\u0014)/\u0004\u0002\u0003^*!!q\u001cB4\u0003%IW.\\;uC\ndW-\u0003\u0003\u0003d\nu'aA*fcBA\u0011q\u0012Bt\u0003\u0013\f\t0\u0003\u0003\u0003j\u0006E%A\u0002+va2,''A\tuS6,GmT;u\u000bb,7m]0%KF$B!a?\u0003p\"I!1\u0001\r\u0002\u0002\u0003\u0007!\u0011\\\u0001\u000fi&lW\rZ(vi\u0016CXmY:!\u0003M\u0019\b.\u001e4gY\u0016$v.Q2uSZ,'j\u001c2t+\t\u00119\u0010\u0005\u0005\u0003b\t-\u0014\u0011\u001fB}!\u0019\u0011\tGa?\u0002r&!!Q B2\u0005-\t%O]1z\u0005V4g-\u001a:\u0002)MDWO\u001a4mKR{\u0017i\u0019;jm\u0016TuNY:!\u0003A\u0019H/Y4f)>\u001c\u0006.\u001e4gY\u0016LE)\u0006\u0002\u0004\u0006AA!\u0011\rB6\u0003c\f\t0A\tti\u0006<W\rV8TQV4g\r\\3J\t\u0002\nQB[8c)>\u001cF/Y4f\u0013\u0012\u001bXCAB\u0007!!\u0011\tGa\u001b\u0002r\u000e=\u0001C\u0002BU\u0007#\t\t0\u0003\u0003\u0003d\n]\u0016A\u00046pER{7\u000b^1hK&#5\u000fI\u0001\u0006e\u0016\u001cX\r^\u0001\u0012i&lW\rZ(vi\u0016CXmY;u_J\u001cHCAB\u000e!\u0019\u0011Ik!\u0005\u0003f\u0006yQ\r_3dkR|'o]&jY2,G\r\u0006\u0003\u0002|\u000e\u0005\u0002b\u0002BSE\u0001\u000711\u0005\t\u0007\u0005S\u001b\t\"!3\u0002/\u0015DXmY;u_J\u001cH)Z2p[6L7o]5p]\u0016$G\u0003BA~\u0007SAqA!*$\u0001\u0004\u0019\u0019#A\u0007fq\u0016\u001cW\u000f^8s\u0007>,h\u000e^\u0001!Kb,7-\u001e;pe\u000e{WO\u001c;XSRD'+Z:pkJ\u001cW\r\u0015:pM&dW\r\u0006\u0003\u0002r\u000eE\u0002b\u0002BLK\u0001\u0007\u0011\u0011_\u0001\u0015O\u0016$(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133\u0015\t\u0005E8q\u0007\u0005\b\u0007s1\u0003\u0019AAe\u0003))\u00070Z2vi>\u0014\u0018\nZ\u0001\u0014a\u0016tG-\u001b8h%\u0016lwN^1m\u0007>,h\u000e^\u0001(a\u0016tG-\u001b8h%\u0016lwN^1m\u0007>,h\u000e\u001e)feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE\r\u0006\u0003\u0002r\u000e\u0005\u0003b\u0002BLQ\u0001\u0007\u0011\u0011_\u0001\u0015I\u0016\u001cw.\\7jgNLwN\\5oO\u000e{WO\u001c;\u0002G\u0011,7m\\7nSN\u001c\u0018n\u001c8j]\u001e\u0004VM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3JIR!\u0011\u0011_B%\u0011\u001d\u00119J\u000ba\u0001\u0003c\f!b\u001c8K_\n\u001cF/\u0019:u)\u0011\tYpa\u0014\t\u000f\rE3\u00061\u0001\u0004T\u0005)QM^3oiB!\u0011QFB+\u0013\u0011\u00199&!\u0007\u0003+M\u0003\u0018M]6MSN$XM\\3s\u0015>\u00147\u000b^1si\u0006AqN\u001c&pE\u0016sG\r\u0006\u0003\u0002|\u000eu\u0003bBB)Y\u0001\u00071q\f\t\u0005\u0003[\u0019\t'\u0003\u0003\u0004d\u0005e!aE*qCJ\\G*[:uK:,'OS8c\u000b:$\u0017aC8o)\u0006\u001c8n\u0015;beR$B!a?\u0004j!91\u0011K\u0017A\u0002\r-\u0004\u0003BA\u0017\u0007[JAaa\u001c\u0002\u001a\t12\u000b]1sW2K7\u000f^3oKJ$\u0016m]6Ti\u0006\u0014H/A\u0005p]R\u000b7o[#oIR!\u00111`B;\u0011\u001d\u0019\tF\fa\u0001\u0007o\u0002B!!\f\u0004z%!11PA\r\u0005Q\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8feR\u000b7o[#oI\u0006yqN\\#yK\u000e,Ho\u001c:BI\u0012,G\r\u0006\u0003\u0002|\u000e\u0005\u0005bBB)_\u0001\u000711\u0011\t\u0005\u0003[\u0019))\u0003\u0003\u0004\b\u0006e!AG*qCJ\\G*[:uK:,'/\u0012=fGV$xN]!eI\u0016$\u0017!\t3fGJ,W.\u001a8u\u000bb,7MU3t_V\u00148-\u001a)s_\u001aLG.Z\"pk:$H\u0003BA~\u0007\u001bCqaa$1\u0001\u0004\t\t0\u0001\u0003sa&#\u0017!E8o\u000bb,7-\u001e;peJ+Wn\u001c<fIR!\u00111`BK\u0011\u001d\u0019\t&\ra\u0001\u0007/\u0003B!!\f\u0004\u001a&!11TA\r\u0005q\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J\u0014V-\\8wK\u0012\fab\u001c8CY>\u001c7.\u00169eCR,G\r\u0006\u0003\u0002|\u000e\u0005\u0006bBB)e\u0001\u000711\u0015\t\u0005\u0003[\u0019)+\u0003\u0003\u0004(\u0006e!!G*qCJ\\G*[:uK:,'O\u00117pG.,\u0006\u000fZ1uK\u0012\fab\u001c8V]B,'o]5tiJ#E\t\u0006\u0003\u0002|\u000e5\u0006bBB)g\u0001\u00071q\u0016\t\u0005\u0003[\u0019\t,\u0003\u0003\u00044\u0006e!!G*qCJ\\G*[:uK:,'/\u00168qKJ\u001c\u0018n\u001d;S\t\u0012\u000bAb\u001c8Pi\",'/\u0012<f]R$B!a?\u0004:\"91\u0011\u000b\u001bA\u0002\rm\u0006\u0003BA\u0017\u0007{KAaa0\u0002\u001a\t\u00112\u000b]1sW2K7\u000f^3oKJ,e/\u001a8u\u0003)\u0011H\rZ\"mK\u0006tW\r\u001a\u000b\u0005\u0003w\u001c)\rC\u0004\u0004HV\u0002\r!!=\u0002\u000bI$G-\u00133\u0002\u001dMDWO\u001a4mK\u000ecW-\u00198fIR!\u00111`Bg\u0011\u001d\u0019yM\u000ea\u0001\u0003c\f\u0011b\u001d5vM\u001adW-\u00133\u0002!\t\u0014x.\u00193dCN$8\t\\3b]\u0016$G\u0003BA~\u0007+Dqaa68\u0001\u0004\ti)A\u0006ce>\fGmY1ti&#\u0017\u0001D1dGVl7\t\\3b]\u0016$G\u0003BA~\u0007;Dqaa89\u0001\u0004\ti)A\u0003bG\u000eLE-A\tdQ\u0016\u001c7\u000e]8j]R\u001cE.Z1oK\u0012$B!a?\u0004f\"91qY\u001dA\u0002\u00055\u0015AD5t\u000bb,7-\u001e;pe&#G.\u001a\u000b\u0005\u0003O\u001bY\u000fC\u0004\u0003\u0018j\u0002\r!!3\u0015\t\r\r2q\u001e\u0005\b\u0007c\\\u0004\u0019AAG\u0003\u00119\b.\u001a8\u00021\u0015DXmY;u_J\u001c\b+\u001a8eS:<Gk\u001c*f[>4X\r\u0006\u0002\u0004xB1\u00111ZB}\u0003\u0013LAaa?\u0002^\n\u00191+\u001a;\u00021\u0015DXmY;u_J\u001cH)Z2p[6L7o]5p]&tw-A\ff]N,(/Z#yK\u000e,Ho\u001c:JgR\u0013\u0018mY6fIR1\u0011q\u001cC\u0002\t\u000bAqAa&?\u0001\u0004\tI\rC\u0004\u0002nz\u0002\r!!=\u0002#U\u0004H-\u0019;f\u001d\u0016DH\u000fV5nK>,H\u000f\u0006\u0003\u0002|\u0012-\u0001b\u0002C\u0007\u007f\u0001\u0007\u0011QR\u0001\t]\u0016<h+\u00197vK\u0006q1\r\\3b]V\u00048\u000b[;gM2,G\u0003BA~\t'AqAa&A\u0001\u0004\t\tPA\nTQV4g\r\\3DY\u0016\fg.\u001a3Fm\u0016tGoE\u0005f\u0003O\u001cY\f\"\u0007\u0005 A!\u0011q\u0012C\u000e\u0013\u0011!i\"!%\u0003\u000fA\u0013x\u000eZ;diB!!\u0011\u0016C\u0011\u0013\u0011!\u0019Ca.\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\u0007%$\u0007\u0005\u0006\u0003\u0005*\u0011-\u0002cAAqK\"9!q\u00135A\u0002\u0005E\u0018\u0001\u00037pO\u00163XM\u001c;\u0002\t\r|\u0007/\u001f\u000b\u0005\tS!\u0019\u0004C\u0005\u0003\u0018*\u0004\n\u00111\u0001\u0002r\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001C\u001dU\u0011\t\t\u0010b\u000f,\u0005\u0011u\u0002\u0003\u0002C \t\u0013j!\u0001\"\u0011\u000b\t\u0011\rCQI\u0001\nk:\u001c\u0007.Z2lK\u0012TA\u0001b\u0012\u0002\u0012\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0011-C\u0011\t\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0005RA!A1\u000bC-\u001b\t!)F\u0003\u0003\u0005X\u0005\u0005\u0017\u0001\u00027b]\u001eLA!a7\u0005V\u0005a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003\u0002C1\tO\u0002B!a$\u0005d%!AQMAI\u0005\r\te.\u001f\u0005\n\u0005\u0007q\u0017\u0011!a\u0001\u0003c\fq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\t[\u0002b\u0001b\u001c\u0005r\u0011\u0005TB\u0001B4\u0013\u0011!\u0019Ha\u001a\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003O#I\bC\u0005\u0003\u0004A\f\t\u00111\u0001\u0005b\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011!\t\u0006b \t\u0013\t\r\u0011/!AA\u0002\u0005E\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005E\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0011E\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002(\u00125\u0005\"\u0003B\u0002i\u0006\u0005\t\u0019\u0001C1\u0003M\u0019\u0006.\u001e4gY\u0016\u001cE.Z1oK\u0012,e/\u001a8u!\r\t\tO^\n\u0006m\u0012UE\u0011\u0015\t\t\t/#i*!=\u0005*5\u0011A\u0011\u0014\u0006\u0005\t7\u000b\t*A\u0004sk:$\u0018.\\3\n\t\u0011}E\u0011\u0014\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003\u0002CR\tSk!\u0001\"*\u000b\t\u0011\u001d\u0016\u0011Y\u0001\u0003S>LA\u0001b\t\u0005&R\u0011A\u0011S\u0001\u0006CB\u0004H.\u001f\u000b\u0005\tS!\t\fC\u0004\u0003\u0018f\u0004\r!!=\u0002\u000fUt\u0017\r\u001d9msR!Aq\u0017C_!\u0019\ty\t\"/\u0002r&!A1XAI\u0005\u0019y\u0005\u000f^5p]\"IAq\u0018>\u0002\u0002\u0003\u0007A\u0011F\u0001\u0004q\u0012\u0002$aE#yK\u000e,Ho\u001c:JI\u000e{G\u000e\\3di>\u00148cA>\u0002hR\u0011Aq\u0019\t\u0004\u0003C\\XC\u0001Cf!\u0019\u0011\tGa?\u0002J\u0006!\u0011\u000eZ:!\u0003\u0019)\u0007pY3tg\u0006QQ\r_2fgN|F%Z9\u0015\t\u0005mHQ\u001b\u0005\u000b\u0005\u0007\t\t!!AA\u0002\u0005E\u0018aB3yG\u0016\u001c8\u000fI\u0001\u0004C\u0012$G\u0003BA~\t;D\u0001Ba&\u0002\u0006\u0001\u0007\u0011\u0011Z\u0001\t]>tW)\u001c9usR\u0011\u0011\u0011Z\u0001\u0010\u000bb,7-\u001e;pe6{g.\u001b;peB!\u0011QPA\u0007'\u0011\ti!a:\u0015\u0005\u0011\u0015\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0002\u0005p*\"\u0011\u0011\u000fC\u001e\u0001"
)
public class ExecutorMonitor extends SparkListener implements CleanerListener, Logging {
   private volatile ShuffleCleanedEvent$ ShuffleCleanedEvent$module;
   private final ExecutorAllocationClient client;
   private final LiveListenerBus listenerBus;
   public final Clock org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$clock;
   private final ExecutorAllocationManagerSource metrics;
   private final long org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$idleTimeoutNs;
   private final long org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$storageTimeoutNs;
   private final long org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTimeoutNs;
   private final boolean fetchFromShuffleSvcEnabled;
   private final boolean org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled;
   private final ConcurrentHashMap executors;
   private final ConcurrentHashMap execResourceProfileCount;
   private final AtomicLong org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout;
   private Seq timedOutExecs;
   private final HashMap shuffleToActiveJobs;
   private final HashMap stageToShuffleID;
   private final HashMap jobToStageIDs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static ExecutorAllocationManagerSource $lessinit$greater$default$5() {
      return ExecutorMonitor$.MODULE$.$lessinit$greater$default$5();
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

   private ShuffleCleanedEvent$ ShuffleCleanedEvent() {
      if (this.ShuffleCleanedEvent$module == null) {
         this.ShuffleCleanedEvent$lzycompute$1();
      }

      return this.ShuffleCleanedEvent$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public long org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$idleTimeoutNs() {
      return this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$idleTimeoutNs;
   }

   public long org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$storageTimeoutNs() {
      return this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$storageTimeoutNs;
   }

   public long org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTimeoutNs() {
      return this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTimeoutNs;
   }

   private boolean fetchFromShuffleSvcEnabled() {
      return this.fetchFromShuffleSvcEnabled;
   }

   public boolean org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled() {
      return this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled;
   }

   private ConcurrentHashMap executors() {
      return this.executors;
   }

   private ConcurrentHashMap execResourceProfileCount() {
      return this.execResourceProfileCount;
   }

   public AtomicLong org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout() {
      return this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout;
   }

   private Seq timedOutExecs() {
      return this.timedOutExecs;
   }

   private void timedOutExecs_$eq(final Seq x$1) {
      this.timedOutExecs = x$1;
   }

   private HashMap shuffleToActiveJobs() {
      return this.shuffleToActiveJobs;
   }

   private HashMap stageToShuffleID() {
      return this.stageToShuffleID;
   }

   private HashMap jobToStageIDs() {
      return this.jobToStageIDs;
   }

   public void reset() {
      this.executors().clear();
      this.execResourceProfileCount().clear();
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MAX_VALUE);
      this.timedOutExecs_$eq(.MODULE$);
   }

   public Seq timedOutExecutors() {
      long now = this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$clock.nanoTime();
      if (now >= this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().get()) {
         this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MAX_VALUE);
         LongRef newNextTimeout = LongRef.create(Long.MAX_VALUE);
         this.timedOutExecs_$eq(((MapOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$timedOutExecutors$1(x0$1)))).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$timedOutExecutors$2(now, newNextTimeout, x0$2)))).map((x0$3) -> {
            if (x0$3 != null) {
               String name = (String)x0$3._1();
               Tracker exec = (Tracker)x0$3._2();
               return new Tuple2(name, BoxesRunTime.boxToInteger(exec.resourceProfileId()));
            } else {
               throw new MatchError(x0$3);
            }
         }).toSeq());
         this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$updateNextTimeout(newNextTimeout.elem);
      }

      return (Seq)this.timedOutExecs().sortBy((x$1) -> (String)x$1._1(), scala.math.Ordering.String..MODULE$);
   }

   public void executorsKilled(final Seq ids) {
      ids.foreach((id) -> {
         $anonfun$executorsKilled$1(this, id);
         return BoxedUnit.UNIT;
      });
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MIN_VALUE);
   }

   public void executorsDecommissioned(final Seq ids) {
      ids.foreach((id) -> {
         $anonfun$executorsDecommissioned$1(this, id);
         return BoxedUnit.UNIT;
      });
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MIN_VALUE);
   }

   public int executorCount() {
      return this.executors().size();
   }

   public int executorCountWithResourceProfile(final int id) {
      return BoxesRunTime.unboxToInt(this.execResourceProfileCount().getOrDefault(BoxesRunTime.boxToInteger(id), BoxesRunTime.boxToInteger(0)));
   }

   public int getResourceProfileId(final String executorId) {
      Tracker execTrackingInfo = (Tracker)this.executors().get(executorId);
      return execTrackingInfo != null ? execTrackingInfo.resourceProfileId() : ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID();
   }

   public int pendingRemovalCount() {
      return scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().count((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$pendingRemovalCount$1(x0$1)));
   }

   public int pendingRemovalCountPerResourceProfileId(final int id) {
      return scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().count((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$pendingRemovalCountPerResourceProfileId$1(id, x0$1)));
   }

   public int decommissioningCount() {
      return scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().count((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$decommissioningCount$1(x0$1)));
   }

   public int decommissioningPerResourceProfileId(final int id) {
      return scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().count((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$decommissioningPerResourceProfileId$1(id, x0$1)));
   }

   public void onJobStart(final SparkListenerJobStart event) {
      if (this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled()) {
         Seq shuffleStages = (Seq)event.stageInfos().flatMap((s) -> (Seq)scala.Option..MODULE$.option2Iterable(s.shuffleDepId()).toSeq().map((shuffleId) -> $anonfun$onJobStart$2(s, BoxesRunTime.unboxToInt(shuffleId))));
         BooleanRef updateExecutors = BooleanRef.create(false);
         shuffleStages.foreach((x0$1) -> {
            if (x0$1 != null) {
               int stageId = x0$1._1$mcI$sp();
               int shuffle = x0$1._2$mcI$sp();
               Option var10 = this.shuffleToActiveJobs().get(BoxesRunTime.boxToInteger(shuffle));
               ArrayBuffer var10000;
               if (var10 instanceof Some) {
                  Some var11 = (Some)var10;
                  ArrayBuffer jobs = (ArrayBuffer)var11.value();
                  this.logDebug((Function0)(() -> "Reusing shuffle " + shuffle + " in job " + event.jobId() + "."));
                  updateExecutors.elem = true;
                  var10000 = jobs;
               } else {
                  this.logDebug((Function0)(() -> "Registered new shuffle " + shuffle + " (from stage " + stageId + ")."));
                  ArrayBuffer jobs = new ArrayBuffer();
                  this.shuffleToActiveJobs().update(BoxesRunTime.boxToInteger(shuffle), jobs);
                  var10000 = jobs;
               }

               ArrayBuffer jobIDs = var10000;
               return (ArrayBuffer)jobIDs.$plus$eq(BoxesRunTime.boxToInteger(event.jobId()));
            } else {
               throw new MatchError(x0$1);
            }
         });
         if (updateExecutors.elem) {
            Seq activeShuffleIds = (Seq)shuffleStages.map((x$2) -> BoxesRunTime.boxToInteger($anonfun$onJobStart$6(x$2)));
            BooleanRef needTimeoutUpdate = BooleanRef.create(false);
            ExecutorIdCollector activatedExecs = new ExecutorIdCollector();
            scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().foreach((x0$2) -> {
               $anonfun$onJobStart$7(activeShuffleIds, needTimeoutUpdate, activatedExecs, x0$2);
               return BoxedUnit.UNIT;
            });
            if (activatedExecs.nonEmpty()) {
               this.logDebug((Function0)(() -> "Activated executors " + activatedExecs + " due to shuffle data needed by new job" + event.jobId() + "."));
            }

            if (needTimeoutUpdate.elem) {
               this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MIN_VALUE);
            }
         }

         this.stageToShuffleID().$plus$plus$eq(shuffleStages);
         this.jobToStageIDs().update(BoxesRunTime.boxToInteger(event.jobId()), shuffleStages.map((x$3) -> BoxesRunTime.boxToInteger($anonfun$onJobStart$9(x$3))));
      }
   }

   public void onJobEnd(final SparkListenerJobEnd event) {
      if (this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled()) {
         BooleanRef updateExecutors = BooleanRef.create(false);
         ArrayBuffer activeShuffles = new ArrayBuffer();
         this.shuffleToActiveJobs().foreach((x0$1) -> {
            if (x0$1 != null) {
               int shuffleId = x0$1._1$mcI$sp();
               ArrayBuffer jobs = (ArrayBuffer)x0$1._2();
               jobs.$minus$eq(BoxesRunTime.boxToInteger(event.jobId()));
               if (jobs.nonEmpty()) {
                  return activeShuffles.$plus$eq(BoxesRunTime.boxToInteger(shuffleId));
               } else {
                  updateExecutors.elem = true;
                  return BoxedUnit.UNIT;
               }
            } else {
               throw new MatchError(x0$1);
            }
         });
         if (updateExecutors.elem) {
            if (this.log().isDebugEnabled()) {
               if (activeShuffles.nonEmpty()) {
                  this.logDebug((Function0)(() -> {
                     int var10000 = event.jobId();
                     return "Job " + var10000 + " ended, shuffles " + activeShuffles.mkString(",") + " still active.";
                  }));
               } else {
                  this.logDebug((Function0)(() -> "Job " + event.jobId() + " ended, no active shuffles remain."));
               }
            }

            ExecutorIdCollector deactivatedExecs = new ExecutorIdCollector();
            scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().foreach((x0$2) -> {
               $anonfun$onJobEnd$4(activeShuffles, deactivatedExecs, x0$2);
               return BoxedUnit.UNIT;
            });
            if (deactivatedExecs.nonEmpty()) {
               this.logDebug((Function0)(() -> "Executors " + deactivatedExecs + " do not have active shuffle data after job " + event.jobId() + " finished."));
            }
         }

         this.jobToStageIDs().remove(BoxesRunTime.boxToInteger(event.jobId())).foreach((stages) -> {
            $anonfun$onJobEnd$6(this, stages);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void onTaskStart(final SparkListenerTaskStart event) {
      String executorId = event.taskInfo().executorId();
      if (this.client.isExecutorActive(executorId)) {
         Tracker exec = this.ensureExecutorIsTracked(executorId, ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID());
         exec.updateRunningTasks(1);
      }
   }

   public void onTaskEnd(final SparkListenerTaskEnd event) {
      String executorId = event.taskInfo().executorId();
      Tracker exec = (Tracker)this.executors().get(executorId);
      if (exec != null) {
         if (this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled()) {
            label18: {
               TaskEndReason var10000 = event.reason();
               Success$ var4 = Success$.MODULE$;
               if (var10000 == null) {
                  if (var4 != null) {
                     break label18;
                  }
               } else if (!var10000.equals(var4)) {
                  break label18;
               }

               this.stageToShuffleID().get(BoxesRunTime.boxToInteger(event.stageId())).foreach((JFunction1.mcVI.sp)(shuffleId) -> exec.addShuffle(shuffleId));
            }
         }

         exec.updateRunningTasks(-1);
      }
   }

   public void onExecutorAdded(final SparkListenerExecutorAdded event) {
      Tracker exec = this.ensureExecutorIsTracked(event.executorId(), event.executorInfo().resourceProfileId());
      exec.updateRunningTasks(0);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"New executor ", " has registered "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, event.executorId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(new total is ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(this.executors().size()))}))))));
   }

   private void decrementExecResourceProfileCount(final int rpId) {
      int count = BoxesRunTime.unboxToInt(this.execResourceProfileCount().getOrDefault(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(0)));
      this.execResourceProfileCount().replace(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(count), BoxesRunTime.boxToInteger(count - 1));
      this.execResourceProfileCount().remove(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(0));
   }

   public void onExecutorRemoved(final SparkListenerExecutorRemoved event) {
      Tracker removed = (Tracker)this.executors().remove(event.executorId());
      if (removed != null) {
         label37: {
            label41: {
               this.decrementExecResourceProfileCount(removed.resourceProfileId());
               String var10000 = event.reason();
               String var3 = ExecutorLossMessage$.MODULE$.decommissionFinished();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label41;
                  }
               } else if (var10000.equals(var3)) {
                  break label41;
               }

               if (event.reason() == null || !event.reason().startsWith(ExecutorDecommission$.MODULE$.msgPrefix())) {
                  if (removed.decommissioning()) {
                     this.metrics.decommissionUnfinished().inc();
                  } else if (removed.pendingRemoval()) {
                     this.metrics.driverKilled().inc();
                  } else {
                     this.metrics.exitedUnexpectedly().inc();
                  }
                  break label37;
               }
            }

            this.metrics.gracefullyDecommissioned().inc();
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " is removed. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, event.executorId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Remove reason statistics: (gracefully decommissioned: "})))).log(.MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_DECOMMISSIONED..MODULE$, BoxesRunTime.boxToLong(this.metrics.gracefullyDecommissioned().getCount()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"decommission unfinished: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_UNFINISHED_DECOMMISSIONED..MODULE$, BoxesRunTime.boxToLong(this.metrics.decommissionUnfinished().getCount()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"driver killed: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS_KILLED..MODULE$, BoxesRunTime.boxToLong(this.metrics.driverKilled().getCount()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"unexpectedly exited: ", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS_EXITED..MODULE$, BoxesRunTime.boxToLong(this.metrics.exitedUnexpectedly().getCount()))}))))));
         if (!removed.pendingRemoval() || !removed.decommissioning()) {
            this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MIN_VALUE);
         }
      }
   }

   public void onBlockUpdated(final SparkListenerBlockUpdated event) {
      if (this.client.isExecutorActive(event.blockUpdatedInfo().blockManagerId().executorId())) {
         Tracker exec = this.ensureExecutorIsTracked(event.blockUpdatedInfo().blockManagerId().executorId(), ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID());
         if (!(event.blockUpdatedInfo().blockId() instanceof RDDBlockId)) {
            if (event.blockUpdatedInfo().blockId() instanceof ShuffleDataBlockId && this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled()) {
               BlockId var4 = event.blockUpdatedInfo().blockId();
               if (var4 instanceof ShuffleDataBlockId) {
                  ShuffleDataBlockId var5 = (ShuffleDataBlockId)var4;
                  int shuffleId = var5.shuffleId();
                  exec.addShuffle(shuffleId);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var11 = BoxedUnit.UNIT;
               }
            }

         } else {
            StorageLevel storageLevel = event.blockUpdatedInfo().storageLevel();
            RDDBlockId blockId = (RDDBlockId)event.blockUpdatedInfo().blockId();
            if (storageLevel.isValid() && (!this.fetchFromShuffleSvcEnabled() || !storageLevel.useDisk())) {
               boolean hadCachedBlocks = exec.cachedBlocks().nonEmpty();
               BitSet blocks = (BitSet)exec.cachedBlocks().getOrElseUpdate(BoxesRunTime.boxToInteger(blockId.rddId()), () -> new BitSet(blockId.splitIndex()));
               blocks.$plus$eq(BoxesRunTime.boxToInteger(blockId.splitIndex()));
               if (!hadCachedBlocks) {
                  exec.updateTimeout();
               }
            } else {
               exec.cachedBlocks().get(BoxesRunTime.boxToInteger(blockId.rddId())).foreach((blocksx) -> {
                  $anonfun$onBlockUpdated$2(blockId, exec, blocksx);
                  return BoxedUnit.UNIT;
               });
            }
         }
      }
   }

   public void onUnpersistRDD(final SparkListenerUnpersistRDD event) {
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.executors().values()).asScala().foreach((exec) -> {
         $anonfun$onUnpersistRDD$1(event, exec);
         return BoxedUnit.UNIT;
      });
   }

   public void onOtherEvent(final SparkListenerEvent event) {
      if (event instanceof ShuffleCleanedEvent var4 && ((ShuffleCleanedEvent)event).org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ShuffleCleanedEvent$$$outer() == this) {
         int id = var4.id();
         this.cleanupShuffle(id);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void rddCleaned(final int rddId) {
   }

   public void shuffleCleaned(final int shuffleId) {
      if (this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled()) {
         this.listenerBus.post(new ShuffleCleanedEvent(shuffleId));
      }
   }

   public void broadcastCleaned(final long broadcastId) {
   }

   public void accumCleaned(final long accId) {
   }

   public void checkpointCleaned(final long rddId) {
   }

   public boolean isExecutorIdle(final String id) {
      return BoxesRunTime.unboxToBoolean(scala.Option..MODULE$.apply(this.executors().get(id)).map((x$4) -> BoxesRunTime.boxToBoolean($anonfun$isExecutorIdle$1(x$4))).getOrElse(() -> {
         throw SparkCoreErrors$.MODULE$.noExecutorIdleError(id);
      }));
   }

   public Seq timedOutExecutors(final long when) {
      return ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().flatMap((x0$1) -> {
         if (x0$1 != null) {
            String id = (String)x0$1._1();
            Tracker tracker = (Tracker)x0$1._2();
            return (Option)(tracker.isIdle() && tracker.timeoutAt() <= when ? new Some(id) : scala.None..MODULE$);
         } else {
            throw new MatchError(x0$1);
         }
      })).toSeq();
   }

   public Set executorsPendingToRemove() {
      return ((MapOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$executorsPendingToRemove$1(x0$1)))).keys().toSet();
   }

   public Set executorsDecommissioning() {
      return ((MapOps)scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$executorsDecommissioning$1(x0$1)))).keys().toSet();
   }

   public Tracker ensureExecutorIsTracked(final String id, final int resourceProfileId) {
      int numExecsWithRpId = BoxesRunTime.unboxToInt(this.execResourceProfileCount().computeIfAbsent(BoxesRunTime.boxToInteger(resourceProfileId), (x$5) -> BoxesRunTime.boxToInteger($anonfun$ensureExecutorIsTracked$1(BoxesRunTime.unboxToInt(x$5)))));
      Tracker execTracker = (Tracker)this.executors().computeIfAbsent(id, (x$6) -> {
         int newcount = numExecsWithRpId + 1;
         this.execResourceProfileCount().put(BoxesRunTime.boxToInteger(resourceProfileId), BoxesRunTime.boxToInteger(newcount));
         this.logDebug((Function0)(() -> "Executor added with ResourceProfile id: " + resourceProfileId + " count is now " + newcount));
         return this.new Tracker(resourceProfileId);
      });
      if (execTracker.resourceProfileId() == ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID() && resourceProfileId != ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID()) {
         this.logDebug((Function0)(() -> "Executor: " + id + ", resource profile id was unknown, setting it to " + resourceProfileId));
         execTracker.resourceProfileId_$eq(resourceProfileId);
         this.execResourceProfileCount().put(BoxesRunTime.boxToInteger(resourceProfileId), BoxesRunTime.boxToInteger(numExecsWithRpId + 1));
         this.decrementExecResourceProfileCount(ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID());
      }

      return execTracker;
   }

   public void org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$updateNextTimeout(final long newValue) {
      long current;
      do {
         current = this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().get();
      } while(newValue < current && !this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().compareAndSet(current, newValue));

   }

   private void cleanupShuffle(final int id) {
      this.logDebug((Function0)(() -> "Cleaning up state related to shuffle " + id + "."));
      this.shuffleToActiveJobs().$minus$eq(BoxesRunTime.boxToInteger(id));
      scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.executors()).asScala().foreach((x0$1) -> {
         $anonfun$cleanupShuffle$2(id, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private final void ShuffleCleanedEvent$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ShuffleCleanedEvent$module == null) {
            this.ShuffleCleanedEvent$module = new ShuffleCleanedEvent$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$timedOutExecutors$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         Tracker exec = (Tracker)x0$1._2();
         return !exec.pendingRemoval() && !exec.hasActiveShuffle() && !exec.decommissioning();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$timedOutExecutors$2(final long now$1, final LongRef newNextTimeout$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tracker exec = (Tracker)x0$2._2();
         long deadline = exec.timeoutAt();
         if (deadline > now$1) {
            newNextTimeout$1.elem = scala.math.package..MODULE$.min(newNextTimeout$1.elem, deadline);
            exec.timedOut_$eq(false);
            return false;
         } else {
            exec.timedOut_$eq(true);
            return true;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executorsKilled$1(final ExecutorMonitor $this, final String id) {
      Tracker tracker = (Tracker)$this.executors().get(id);
      if (tracker != null) {
         tracker.pendingRemoval_$eq(true);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executorsDecommissioned$1(final ExecutorMonitor $this, final String id) {
      Tracker tracker = (Tracker)$this.executors().get(id);
      if (tracker != null) {
         tracker.decommissioning_$eq(true);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$pendingRemovalCount$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tracker exec = (Tracker)x0$1._2();
         return exec.pendingRemoval();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$pendingRemovalCountPerResourceProfileId$1(final int id$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         Tracker v = (Tracker)x0$1._2();
         return v.resourceProfileId() == id$1 && v.pendingRemoval();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$decommissioningCount$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tracker exec = (Tracker)x0$1._2();
         return exec.decommissioning();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$decommissioningPerResourceProfileId$1(final int id$2, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         Tracker v = (Tracker)x0$1._2();
         return v.resourceProfileId() == id$2 && v.decommissioning();
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$onJobStart$2(final StageInfo s$1, final int shuffleId) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(s$1.stageId())), BoxesRunTime.boxToInteger(shuffleId));
   }

   // $FF: synthetic method
   public static final int $anonfun$onJobStart$6(final Tuple2 x$2) {
      return x$2._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobStart$7(final Seq activeShuffleIds$1, final BooleanRef needTimeoutUpdate$1, final ExecutorIdCollector activatedExecs$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String id = (String)x0$2._1();
         Tracker exec = (Tracker)x0$2._2();
         if (!exec.hasActiveShuffle()) {
            exec.updateActiveShuffles(activeShuffleIds$1);
            if (exec.hasActiveShuffle()) {
               needTimeoutUpdate$1.elem = true;
               activatedExecs$1.add(id);
               BoxedUnit var9 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var8 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$onJobStart$9(final Tuple2 x$3) {
      return x$3._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$4(final ArrayBuffer activeShuffles$1, final ExecutorIdCollector deactivatedExecs$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String id = (String)x0$2._1();
         Tracker exec = (Tracker)x0$2._2();
         if (exec.hasActiveShuffle()) {
            exec.updateActiveShuffles(activeShuffles$1);
            if (!exec.hasActiveShuffle()) {
               deactivatedExecs$1.add(id);
               BoxedUnit var8 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var7 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final HashMap $anonfun$onJobEnd$7(final ExecutorMonitor $this, final int id) {
      return (HashMap)$this.stageToShuffleID().$minus$eq(BoxesRunTime.boxToInteger(id));
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$6(final ExecutorMonitor $this, final Seq stages) {
      stages.foreach((id) -> $anonfun$onJobEnd$7($this, BoxesRunTime.unboxToInt(id)));
   }

   // $FF: synthetic method
   public static final void $anonfun$onBlockUpdated$2(final RDDBlockId blockId$1, final Tracker exec$2, final BitSet blocks) {
      blocks.$minus$eq(BoxesRunTime.boxToInteger(blockId$1.splitIndex()));
      if (blocks.isEmpty()) {
         exec$2.cachedBlocks().$minus$eq(BoxesRunTime.boxToInteger(blockId$1.rddId()));
         if (exec$2.cachedBlocks().isEmpty()) {
            exec$2.updateTimeout();
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$1(final SparkListenerUnpersistRDD event$5, final Tracker exec) {
      exec.cachedBlocks().$minus$eq(BoxesRunTime.boxToInteger(event$5.rddId()));
      if (exec.cachedBlocks().isEmpty()) {
         exec.updateTimeout();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isExecutorIdle$1(final Tracker x$4) {
      return x$4.isIdle();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorsPendingToRemove$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tracker exec = (Tracker)x0$1._2();
         return exec.pendingRemoval();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorsDecommissioning$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tracker exec = (Tracker)x0$1._2();
         return exec.decommissioning();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$ensureExecutorIsTracked$1(final int x$5) {
      return 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupShuffle$2(final int id$5, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tracker exec = (Tracker)x0$1._2();
         exec.removeShuffle(id$5);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ExecutorMonitor(final SparkConf conf, final ExecutorAllocationClient client, final LiveListenerBus listenerBus, final Clock clock, final ExecutorAllocationManagerSource metrics) {
      this.client = client;
      this.listenerBus = listenerBus;
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$clock = clock;
      this.metrics = metrics;
      Logging.$init$(this);
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$idleTimeoutNs = TimeUnit.SECONDS.toNanos(BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.DYN_ALLOCATION_EXECUTOR_IDLE_TIMEOUT())));
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$storageTimeoutNs = TimeUnit.SECONDS.toNanos(BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.DYN_ALLOCATION_CACHED_EXECUTOR_IDLE_TIMEOUT())));
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTimeoutNs = TimeUnit.MILLISECONDS.toNanos(BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.DYN_ALLOCATION_SHUFFLE_TRACKING_TIMEOUT())));
      this.fetchFromShuffleSvcEnabled = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_ENABLED())) && BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_FETCH_RDD_ENABLED()));
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled = !BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_ENABLED())) && BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED()));
      this.executors = new ConcurrentHashMap();
      this.execResourceProfileCount = new ConcurrentHashMap();
      this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout = new AtomicLong(Long.MAX_VALUE);
      this.timedOutExecs = (Seq)scala.package..MODULE$.Seq().empty();
      this.shuffleToActiveJobs = new HashMap();
      this.stageToShuffleID = new HashMap();
      this.jobToStageIDs = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Tracker {
      private int resourceProfileId;
      private volatile long timeoutAt;
      private volatile boolean timedOut;
      private boolean pendingRemoval;
      private boolean decommissioning;
      private boolean hasActiveShuffle;
      private long idleStart;
      private int runningTasks;
      private final HashMap cachedBlocks;
      private final HashSet shuffleIds;
      // $FF: synthetic field
      public final ExecutorMonitor $outer;

      public int resourceProfileId() {
         return this.resourceProfileId;
      }

      public void resourceProfileId_$eq(final int x$1) {
         this.resourceProfileId = x$1;
      }

      public long timeoutAt() {
         return this.timeoutAt;
      }

      public void timeoutAt_$eq(final long x$1) {
         this.timeoutAt = x$1;
      }

      public boolean timedOut() {
         return this.timedOut;
      }

      public void timedOut_$eq(final boolean x$1) {
         this.timedOut = x$1;
      }

      public boolean pendingRemoval() {
         return this.pendingRemoval;
      }

      public void pendingRemoval_$eq(final boolean x$1) {
         this.pendingRemoval = x$1;
      }

      public boolean decommissioning() {
         return this.decommissioning;
      }

      public void decommissioning_$eq(final boolean x$1) {
         this.decommissioning = x$1;
      }

      public boolean hasActiveShuffle() {
         return this.hasActiveShuffle;
      }

      public void hasActiveShuffle_$eq(final boolean x$1) {
         this.hasActiveShuffle = x$1;
      }

      private long idleStart() {
         return this.idleStart;
      }

      private void idleStart_$eq(final long x$1) {
         this.idleStart = x$1;
      }

      private int runningTasks() {
         return this.runningTasks;
      }

      private void runningTasks_$eq(final int x$1) {
         this.runningTasks = x$1;
      }

      public HashMap cachedBlocks() {
         return this.cachedBlocks;
      }

      private HashSet shuffleIds() {
         return this.shuffleIds;
      }

      public boolean isIdle() {
         return this.idleStart() >= 0L && !this.hasActiveShuffle();
      }

      public void updateRunningTasks(final int delta) {
         this.runningTasks_$eq(scala.math.package..MODULE$.max(0, this.runningTasks() + delta));
         this.idleStart_$eq(this.runningTasks() == 0 ? this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer().org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$clock.nanoTime() : -1L);
         this.updateTimeout();
      }

      public void updateTimeout() {
         long oldDeadline = this.timeoutAt();
         long var10000;
         if (this.idleStart() >= 0L) {
            long _cacheTimeout = this.cachedBlocks().nonEmpty() ? this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer().org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$storageTimeoutNs() : 0L;
            long _shuffleTimeout = this.shuffleIds() != null && this.shuffleIds().nonEmpty() ? this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer().org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTimeoutNs() : 0L;
            long timeout = BoxesRunTime.unboxToLong(scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapLongArray(new long[]{_cacheTimeout, _shuffleTimeout, this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer().org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$idleTimeoutNs()})).max(scala.math.Ordering.Long..MODULE$));
            long deadline = this.idleStart() + timeout;
            var10000 = deadline >= 0L ? deadline : Long.MAX_VALUE;
         } else {
            var10000 = Long.MAX_VALUE;
         }

         long newDeadline = var10000;
         this.timeoutAt_$eq(newDeadline);
         if (newDeadline > oldDeadline && this.timedOut()) {
            this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer().org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$nextTimeout().set(Long.MIN_VALUE);
         } else {
            this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer().org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$updateNextTimeout(newDeadline);
         }
      }

      public void addShuffle(final int id) {
         if (this.shuffleIds().add(BoxesRunTime.boxToInteger(id))) {
            this.hasActiveShuffle_$eq(true);
         }
      }

      public void removeShuffle(final int id) {
         if (this.shuffleIds().remove(BoxesRunTime.boxToInteger(id)) && this.shuffleIds().isEmpty()) {
            this.hasActiveShuffle_$eq(false);
            if (this.isIdle()) {
               this.updateTimeout();
            }
         }
      }

      public void updateActiveShuffles(final Iterable ids) {
         boolean hadActiveShuffle = this.hasActiveShuffle();
         this.hasActiveShuffle_$eq(ids.exists((JFunction1.mcZI.sp)(elem) -> this.shuffleIds().contains(BoxesRunTime.boxToInteger(elem))));
         if (hadActiveShuffle && this.isIdle()) {
            this.updateTimeout();
         }
      }

      // $FF: synthetic method
      public ExecutorMonitor org$apache$spark$scheduler$dynalloc$ExecutorMonitor$Tracker$$$outer() {
         return this.$outer;
      }

      public Tracker(final int resourceProfileId) {
         this.resourceProfileId = resourceProfileId;
         if (ExecutorMonitor.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMonitor.this;
            super();
            this.timeoutAt = Long.MAX_VALUE;
            this.timedOut = false;
            this.pendingRemoval = false;
            this.decommissioning = false;
            this.hasActiveShuffle = false;
            this.idleStart = -1L;
            this.runningTasks = 0;
            this.cachedBlocks = new HashMap();
            this.shuffleIds = ExecutorMonitor.this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$$shuffleTrackingEnabled() ? new HashSet() : null;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class ShuffleCleanedEvent implements SparkListenerEvent, Product, Serializable {
      private final int id;
      // $FF: synthetic field
      public final ExecutorMonitor $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int id() {
         return this.id;
      }

      public boolean logEvent() {
         return false;
      }

      public ShuffleCleanedEvent copy(final int id) {
         return this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ShuffleCleanedEvent$$$outer().new ShuffleCleanedEvent(id);
      }

      public int copy$default$1() {
         return this.id();
      }

      public String productPrefix() {
         return "ShuffleCleanedEvent";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.id());
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
         return x$1 instanceof ShuffleCleanedEvent;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "id";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.id());
         return Statics.finalizeHash(var1, 1);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label41: {
               if (x$1 instanceof ShuffleCleanedEvent && ((ShuffleCleanedEvent)x$1).org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ShuffleCleanedEvent$$$outer() == this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ShuffleCleanedEvent$$$outer()) {
                  ShuffleCleanedEvent var4 = (ShuffleCleanedEvent)x$1;
                  if (this.id() == var4.id() && var4.canEqual(this)) {
                     break label41;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public ExecutorMonitor org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ShuffleCleanedEvent$$$outer() {
         return this.$outer;
      }

      public ShuffleCleanedEvent(final int id) {
         this.id = id;
         if (ExecutorMonitor.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMonitor.this;
            super();
            SparkListenerEvent.$init$(this);
            Product.$init$(this);
         }
      }
   }

   private class ShuffleCleanedEvent$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final ExecutorMonitor $outer;

      public final String toString() {
         return "ShuffleCleanedEvent";
      }

      public ShuffleCleanedEvent apply(final int id) {
         return this.$outer.new ShuffleCleanedEvent(id);
      }

      public Option unapply(final ShuffleCleanedEvent x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.id())));
      }

      public ShuffleCleanedEvent$() {
         if (ExecutorMonitor.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMonitor.this;
            super();
         }
      }
   }

   private class ExecutorIdCollector {
      private final ArrayBuffer ids;
      private int excess;
      // $FF: synthetic field
      public final ExecutorMonitor $outer;

      private ArrayBuffer ids() {
         return this.ids;
      }

      private int excess() {
         return this.excess;
      }

      private void excess_$eq(final int x$1) {
         this.excess = x$1;
      }

      public void add(final String id) {
         if (this.org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ExecutorIdCollector$$$outer().log().isDebugEnabled()) {
            if (this.ids().size() < 10) {
               this.ids().$plus$eq(id);
            } else {
               this.excess_$eq(this.excess() + 1);
            }
         }
      }

      public boolean nonEmpty() {
         return this.ids() != null && this.ids().nonEmpty();
      }

      public String toString() {
         String var10000 = this.ids().mkString(",");
         return var10000 + (this.excess() > 0 ? " (and " + this.excess() + " more)" : "");
      }

      // $FF: synthetic method
      public ExecutorMonitor org$apache$spark$scheduler$dynalloc$ExecutorMonitor$ExecutorIdCollector$$$outer() {
         return this.$outer;
      }

      public ExecutorIdCollector() {
         if (ExecutorMonitor.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMonitor.this;
            super();
            this.ids = ExecutorMonitor.this.log().isDebugEnabled() ? new ArrayBuffer() : null;
            this.excess = 0;
         }
      }
   }
}
