package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.BarrierCoordinator;
import org.apache.spark.InternalAccumulator;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.MapOutputTrackerMaster;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.TaskNotSerializableException;
import org.apache.spark.TaskState$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.Clock;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala..less.colon.less.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.SetOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019}h!CA\u001b\u0003o\u0001\u00111HA$\u0011)\tI\u0007\u0001BC\u0002\u0013\u0005\u0011Q\u000e\u0005\u000b\u0003o\u0002!\u0011!Q\u0001\n\u0005=\u0004BCA=\u0001\t\u0015\r\u0011\"\u0001\u0002|!Q\u00111\u0011\u0001\u0003\u0002\u0003\u0006I!! \t\u0015\u0005\u0015\u0005A!A!\u0002\u0013\t9\t\u0003\u0006\u0002\u000e\u0002\u0011\t\u0011)A\u0005\u0003\u001fCq!a'\u0001\t\u0003\ti\nC\u0004\u0002\u001c\u0002!\t!!+\t\u0019\u00055\u0006\u0001#b\u0001\n\u0003\t9$a,\t\u0013\u0005u\u0006A1A\u0005\u0002\u0005}\u0006\u0002CAd\u0001\u0001\u0006I!!1\t\u0013\u0005%\u0007A1A\u0005\u0002\u0005-\u0007\u0002CAj\u0001\u0001\u0006I!!4\t\u0013\u0005U\u0007A1A\u0005\u0002\u0005-\u0007\u0002CAl\u0001\u0001\u0006I!!4\t\u0017\u0005e\u0007A1A\u0005\u0002\u0005]\u00121\u001c\u0005\t\u0003;\u0004\u0001\u0015!\u0003\u0002\b\"I\u0011q\u001c\u0001C\u0002\u0013%\u0011\u0011\u001d\u0005\t\u0003k\u0004\u0001\u0015!\u0003\u0002d\"I\u0011q\u001f\u0001C\u0002\u0013\u0005\u00111\u001a\u0005\t\u0003s\u0004\u0001\u0015!\u0003\u0002N\"I\u00111 \u0001C\u0002\u0013\u0005\u00111\u0010\u0005\t\u0003{\u0004\u0001\u0015!\u0003\u0002~!I\u0011q \u0001C\u0002\u0013%!\u0011\u0001\u0005\t\u00057\u0001\u0001\u0015!\u0003\u0003\u0004!I!Q\u0004\u0001C\u0002\u0013%!q\u0004\u0005\t\u0005S\u0001\u0001\u0015!\u0003\u0003\"!I!1\u0006\u0001C\u0002\u0013%\u00111\u001c\u0005\t\u0005[\u0001\u0001\u0015!\u0003\u0002\b\"Y!q\u0006\u0001C\u0002\u0013\u0005\u0011q\u0007B\u0019\u0011!\u0011I\u0004\u0001Q\u0001\n\tM\u0002\"\u0003B\u001e\u0001\t\u0007I\u0011\u0001B\u001f\u0011!\u00119\u0006\u0001Q\u0001\n\t}\u0002\"\u0003B-\u0001\u0001\u0007I\u0011BAn\u0011%\u0011Y\u0006\u0001a\u0001\n\u0013\u0011i\u0006\u0003\u0005\u0003j\u0001\u0001\u000b\u0015BAD\u0011%\u0011\u0019\b\u0001a\u0001\n\u0013\tY\u000eC\u0005\u0003v\u0001\u0001\r\u0011\"\u0003\u0003x!A!1\u0010\u0001!B\u0013\t9\tC\u0005\u0003\u0000\u0001\u0011\r\u0011\"\u0003\u0002b\"A!\u0011\u0011\u0001!\u0002\u0013\t\u0019\u000fC\u0005\u0003\u0004\u0002\u0011\r\u0011\"\u0001\u0003\u0006\"A!1\u0013\u0001!\u0002\u0013\u00119\tC\u0005\u0003\u0016\u0002\u0011\r\u0011\"\u0003\u0003\u0018\"A!\u0011\u0015\u0001!\u0002\u0013\u0011I\nC\u0005\u0003$\u0002\u0011\r\u0011\"\u0001\u0003&\"A!q\u0016\u0001!\u0002\u0013\u00119\u000bC\u0005\u00032\u0002\u0011\r\u0011\"\u0001\u00034\"A!Q\u001a\u0001!\u0002\u0013\u0011)\fC\u0004\u0003P\u0002!\tA!5\t\u0013\te\u0007A1A\u0005\u0012\tm\u0007\u0002\u0003Bq\u0001\u0001\u0006IA!8\t\u0013\t\r\bA1A\u0005\u0012\tm\u0007\u0002\u0003Bs\u0001\u0001\u0006IA!8\t\u0013\t\u001d\bA1A\u0005\u0012\t%\b\u0002\u0003Bw\u0001\u0001\u0006IAa;\t\u0013\t=\bA1A\u0005\n\u0005\u0005\b\u0002\u0003By\u0001\u0001\u0006I!a9\t\u0013\tM\bA1A\u0005\u0002\tU\b\u0002\u0003B}\u0001\u0001\u0006IAa>\t\u0013\tm\b\u00011A\u0005\u0002\tu\b\"CB\u0003\u0001\u0001\u0007I\u0011AB\u0004\u0011!\u0019Y\u0001\u0001Q!\n\t}\b\"CB\u0007\u0001\u0001\u0007I\u0011AB\b\u0011%\u00199\u0002\u0001a\u0001\n\u0003\u0019I\u0002\u0003\u0005\u0004\u001e\u0001\u0001\u000b\u0015BB\t\u0011%\u0019y\u0002\u0001b\u0001\n\u0003\u0019\t\u0003\u0003\u0005\u0004*\u0001\u0001\u000b\u0011BB\u0012\u0011%\u0019Y\u0003\u0001a\u0001\n\u0013\u0019i\u0003C\u0005\u00046\u0001\u0001\r\u0011\"\u0003\u00048!A11\b\u0001!B\u0013\u0019y\u0003C\u0005\u0004>\u0001\u0011\r\u0011\"\u0003\u0004@!A1\u0011\t\u0001!\u0002\u0013\u0011\t\u0005C\u0005\u0004D\u0001\u0011\r\u0011\"\u0001\u0004F!A1Q\r\u0001!\u0002\u0013\u00199\u0005C\u0005\u0004h\u0001\u0011\r\u0011\"\u0001\u0004j!A1\u0011\u000f\u0001!\u0002\u0013\u0019Y\u0007C\u0006\u0004t\u0001\u0001\r\u0011\"\u0001\u0002<\rU\u0004bCB?\u0001\u0001\u0007I\u0011AA\u001e\u0007\u007fB\u0001ba!\u0001A\u0003&1q\u000f\u0005\u000b\u0007\u000b\u0003\u0001R1A\u0005\n\u0005-\u0007bCBD\u0001\u0001\u0007I\u0011AA\u001c\u0007\u0013C1ba&\u0001\u0001\u0004%\t!a\u000e\u0004\u001a\"A1Q\u0014\u0001!B\u0013\u0019Y\tC\u0005\u0004 \u0002\u0011\r\u0011\"\u0005\u0004\"\"A1Q\u0015\u0001!\u0002\u0013\u0019\u0019\u000bC\u0004\u0004(\u0002!Ia!+\t\u000f\r-\u0006\u0001\"\u0011\u0004.\"91\u0011\u0017\u0001\u0005\u0002\rM\u0006bBB\\\u0001\u0011\u00051\u0011\u0018\u0005\b\u0007w\u0003A\u0011IBU\u0011\u001d\u0019i\f\u0001C!\u0007SCqaa0\u0001\t\u0003\u001a\t\rC\u0005\u0004H\u0002!\t!a\u000e\u0004J\"91q\u001a\u0001\u0005B\rE\u0007bBBp\u0001\u0011\u00053\u0011\u001d\u0005\b\u0007W\u0004A\u0011IBw\u0011\u001d\u0019)\u0010\u0001C\u0001\u0007oDqa!@\u0001\t\u0013\u0019y\u0010C\u0004\u0005f\u0001!I\u0001b\u001a\t\u000f\u0011M\u0004\u0001\"\u0003\u0005v!9Aq\u0011\u0001\u0005\n\u0011%\u0005b\u0002CJ\u0001\u0011\u0005AQ\u0013\u0005\n\tK\u0003\u0011\u0013!C\u0001\tOCq\u0001\"0\u0001\t\u0013!y\fC\u0004\u0005H\u0002!I\u0001\"3\t\u000f\u0011]\u0007\u0001\"\u0005\u0005Z\"9AQ\u001c\u0001\u0005\u0002\u0011}\u0007bBC\u0003\u0001\u0011\u0005Sq\u0001\u0005\b\u000b?\u0002A\u0011BC1\u0011%)I\t\u0001C\u0001\u0003o)Y\tC\u0004\u0006\u0016\u0002!\t!b&\t\u000f\u0015}\u0005\u0001\"\u0001\u0006\"\"9Q\u0011\u0018\u0001\u0005\u0002\u0015m\u0006\"CCg\u0001\u0011\u0005\u0011qGCh\u0011\u001d))\u000e\u0001C\u0001\u000b/Dq!\"8\u0001\t\u0003*y\u000eC\u0005\u0006f\u0002\t\n\u0011\"\u0001\u0006h\"9Q1\u001e\u0001\u0005B\u00155\bbBCx\u0001\u0011\u00051\u0011\u0016\u0005\b\u000bc\u0004A\u0011ICz\u0011\u001d1\u0019\u0001\u0001C!\r\u000bAqAb\u0003\u0001\t\u00032i\u0001C\u0004\u0007\u001a\u0001!\tEb\u0007\t\u000f\u0019\u001d\u0002\u0001\"\u0003\u0007*!9a1\u0007\u0001\u0005\n\u0019U\u0002b\u0002D\u001d\u0001\u0011%a1\b\u0005\b\r\u007f\u0001A\u0011\u0002D!\u0011\u001d19\u0005\u0001C\u0001\r\u0013BqAb\u0014\u0001\t\u00031\t\u0006C\u0004\u0007^\u0001!\tAb\u0018\t\u000f\u0019\r\u0004\u0001\"\u0001\u0007f!9a1\u000e\u0001\u0005\u0002\u00195\u0004b\u0002D9\u0001\u0011\u0005a1\u000f\u0005\b\ro\u0002AQ\u0003D=\u0011\u001d1i\b\u0001C\u000b\r\u007fBqAb!\u0001\t\u00031)\tC\u0004\u0007\b\u0002!\tA\"#\t\u000f\u00195\u0005\u0001\"\u0001\u0007\u0010\"9a\u0011\u0014\u0001\u0005\n\r%\u0006b\u0002DN\u0001\u0011\u0005cQ\u0014\u0005\b\r?\u0003A\u0011\tDQ\u0011%1\u0019\u000b\u0001C\u0001\u0003o1)k\u0002\u0006\u00070\u0006]\u0002\u0012AA\u001e\rc3!\"!\u000e\u00028!\u0005\u00111\bDZ\u0011!\tY*a\t\u0005\u0002\u0019U\u0006B\u0003D\\\u0003G\u0011\r\u0011\"\u0001\u0004@!Ia\u0011XA\u0012A\u0003%!\u0011\t\u0005\t\rw\u000b\u0019\u0003\"\u0001\u0007>\"Aa\u0011[A\u0012\t\u00031\u0019\u000e\u0003\u0005\u0007r\u0006\rB\u0011\u0002Dz\u0011)190a\t\u0012\u0002\u0013\u0005Aq\u0015\u0005\u000b\rs\f\u0019#%A\u0005\u0002\u0019m(!\u0005+bg.\u001c6\r[3ek2,'/S7qY*!\u0011\u0011HA\u001e\u0003%\u00198\r[3ek2,'O\u0003\u0003\u0002>\u0005}\u0012!B:qCJ\\'\u0002BA!\u0003\u0007\na!\u00199bG\",'BAA#\u0003\ry'oZ\n\b\u0001\u0005%\u0013QKA/!\u0011\tY%!\u0015\u000e\u0005\u00055#BAA(\u0003\u0015\u00198-\u00197b\u0013\u0011\t\u0019&!\u0014\u0003\r\u0005s\u0017PU3g!\u0011\t9&!\u0017\u000e\u0005\u0005]\u0012\u0002BA.\u0003o\u0011Q\u0002V1tWN\u001b\u0007.\u001a3vY\u0016\u0014\b\u0003BA0\u0003Kj!!!\u0019\u000b\t\u0005\r\u00141H\u0001\tS:$XM\u001d8bY&!\u0011qMA1\u0005\u001daunZ4j]\u001e\f!a]2\u0004\u0001U\u0011\u0011q\u000e\t\u0005\u0003c\n\u0019(\u0004\u0002\u0002<%!\u0011QOA\u001e\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\r\u00198\rI\u0001\u0010[\u0006DH+Y:l\r\u0006LG.\u001e:fgV\u0011\u0011Q\u0010\t\u0005\u0003\u0017\ny(\u0003\u0003\u0002\u0002\u00065#aA%oi\u0006\u0001R.\u0019=UCN\\g)Y5mkJ,7\u000fI\u0001\bSNdunY1m!\u0011\tY%!#\n\t\u0005-\u0015Q\n\u0002\b\u0005>|G.Z1o\u0003\u0015\u0019Gn\\2l!\u0011\t\t*a&\u000e\u0005\u0005M%\u0002BAK\u0003w\tA!\u001e;jY&!\u0011\u0011TAJ\u0005\u0015\u0019En\\2l\u0003\u0019a\u0014N\\5u}QQ\u0011qTAQ\u0003G\u000b)+a*\u0011\u0007\u0005]\u0003\u0001C\u0004\u0002j\u001d\u0001\r!a\u001c\t\u000f\u0005et\u00011\u0001\u0002~!I\u0011QQ\u0004\u0011\u0002\u0003\u0007\u0011q\u0011\u0005\n\u0003\u001b;\u0001\u0013!a\u0001\u0003\u001f#B!a(\u0002,\"9\u0011\u0011\u000e\u0005A\u0002\u0005=\u0014\u0001\u00055fC2$\b\u000e\u0016:bG.,'o\u00149u+\t\t\t\f\u0005\u0004\u0002L\u0005M\u0016qW\u0005\u0005\u0003k\u000biE\u0001\u0004PaRLwN\u001c\t\u0005\u0003/\nI,\u0003\u0003\u0002<\u0006]\"!\u0004%fC2$\b\u000e\u0016:bG.,'/\u0001\u0003d_:4WCAAa!\u0011\t\t(a1\n\t\u0005\u0015\u00171\b\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\nqc\u0015)F\u0007Vc\u0015\tV%P\u001d~Ke\nV#S-\u0006cu,T*\u0016\u0005\u00055\u0007\u0003BA&\u0003\u001fLA!!5\u0002N\t!Aj\u001c8h\u0003a\u0019\u0006+R\"V\u0019\u0006#\u0016j\u0014(`\u0013:#VI\u0015,B\u0019~k5\u000bI\u0001\u0018\u001b&su\fV%N\u000b~#vjX*Q\u000b\u000e+F*\u0011+J\u001f:\u000b\u0001$T%O?RKU*R0U\u001f~\u001b\u0006+R\"V\u0019\u0006#\u0016j\u0014(!\u0003})gMZ5dS\u0016tG\u000fV1tW\u000e\u000bGnY;bYRLwN\\#oC\ndW\rZ\u000b\u0003\u0003\u000f\u000b\u0001%\u001a4gS\u000eLWM\u001c;UCN\\7)\u00197dk\u0006dG/[8o\u000b:\f'\r\\3eA\u0005!2\u000f]3dk2\fG/[8o'\u000eDW\rZ;mKJ,\"!a9\u0011\t\u0005\u0015\u0018\u0011_\u0007\u0003\u0003OTA!!;\u0002l\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u0005U\u0015Q\u001e\u0006\u0003\u0003_\fAA[1wC&!\u00111_At\u0005a\u00196\r[3ek2,G-\u0012=fGV$xN]*feZL7-Z\u0001\u0016gB,7-\u001e7bi&|gnU2iK\u0012,H.\u001a:!\u0003U\u0019F+\u0011*W\u0003RKuJT0U\u00136+u*\u0016+`\u001bN\u000bac\u0015+B%Z\u000bE+S(O?RKU*R(V)~k5\u000bI\u0001\u000e\u0007B+6k\u0018)F%~#\u0016iU&\u0002\u001d\r\u0003VkU0Q\u000bJ{F+Q*LA\u0005YB/Y:l'\u0016$8OQ=Ti\u0006<W-\u00133B]\u0012\fE\u000f^3naR,\"Aa\u0001\u0011\u0011\t\u0015!qBA?\u0005'i!Aa\u0002\u000b\t\t%!1B\u0001\b[V$\u0018M\u00197f\u0015\u0011\u0011i!!\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003\u0012\t\u001d!a\u0002%bg\"l\u0015\r\u001d\t\t\u0005\u000b\u0011y!! \u0003\u0016A!\u0011q\u000bB\f\u0013\u0011\u0011I\"a\u000e\u0003\u001dQ\u000b7o[*fi6\u000bg.Y4fe\u0006aB/Y:l'\u0016$8OQ=Ti\u0006<W-\u00133B]\u0012\fE\u000f^3naR\u0004\u0013a\u00068p%\u0016TWm\u0019;t'&t7-\u001a'bgR\u0014Vm]3u+\t\u0011\t\u0003\u0005\u0005\u0003\u0006\t=!1EAD!\u0011\t9F!\n\n\t\t\u001d\u0012q\u0007\u0002\b)\u0006\u001c8nU3u\u0003aqwNU3kK\u000e$8oU5oG\u0016d\u0015m\u001d;SKN,G\u000fI\u0001\u0018Y\u0016<\u0017mY=M_\u000e\fG.\u001b;z/\u0006LGOU3tKR\f\u0001\u0004\\3hC\u000eLHj\\2bY&$\u0018pV1jiJ+7/\u001a;!\u0003Y!\u0018m]6JIR{G+Y:l'\u0016$X*\u00198bO\u0016\u0014XC\u0001B\u001a!!\t)O!\u000e\u0002N\nU\u0011\u0002\u0002B\u001c\u0003O\u0014\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q\u0003]!\u0018m]6JIR{G+Y:l'\u0016$X*\u00198bO\u0016\u0014\b%\u0001\nuCN\\\u0017\n\u001a+p\u000bb,7-\u001e;pe&#WC\u0001B !!\u0011)Aa\u0004\u0002N\n\u0005\u0003\u0003\u0002B\"\u0005#rAA!\u0012\u0003NA!!qIA'\u001b\t\u0011IE\u0003\u0003\u0003L\u0005-\u0014A\u0002\u001fs_>$h(\u0003\u0003\u0003P\u00055\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0003T\tU#AB*ue&twM\u0003\u0003\u0003P\u00055\u0013a\u0005;bg.LE\rV8Fq\u0016\u001cW\u000f^8s\u0013\u0012\u0004\u0013a\u00045bgJ+7-Z5wK\u0012$\u0016m]6\u0002'!\f7OU3dK&4X\r\u001a+bg.|F%Z9\u0015\t\t}#Q\r\t\u0005\u0003\u0017\u0012\t'\u0003\u0003\u0003d\u00055#\u0001B+oSRD\u0011Ba\u001a$\u0003\u0003\u0005\r!a\"\u0002\u0007a$\u0013'\u0001\tiCN\u0014VmY3jm\u0016$G+Y:lA!\u001aAE!\u001c\u0011\t\u0005-#qN\u0005\u0005\u0005c\niE\u0001\u0005w_2\fG/\u001b7f\u0003=A\u0017m\u001d'bk:\u001c\u0007.\u001a3UCN\\\u0017a\u00055bg2\u000bWO\\2iK\u0012$\u0016m]6`I\u0015\fH\u0003\u0002B0\u0005sB\u0011Ba\u001a'\u0003\u0003\u0005\r!a\"\u0002!!\f7\u000fT1v]\u000eDW\r\u001a+bg.\u0004\u0003fA\u0014\u0003n\u0005y1\u000f^1sm\u0006$\u0018n\u001c8US6,'/\u0001\tti\u0006\u0014h/\u0019;j_:$\u0016.\\3sA\u0005Qa.\u001a=u)\u0006\u001c8.\u00133\u0016\u0005\t\u001d\u0005\u0003\u0002BE\u0005\u001fk!Aa#\u000b\t\t5\u0015q]\u0001\u0007CR|W.[2\n\t\tE%1\u0012\u0002\u000b\u0003R|W.[2M_:<\u0017a\u00038fqR$\u0016m]6JI\u0002\n!$\u001a=fGV$xN]%e)>\u0014VO\u001c8j]\u001e$\u0016m]6JIN,\"A!'\u0011\u0011\t\u0015!q\u0002B!\u00057\u0003bA!\u0002\u0003\u001e\u00065\u0017\u0002\u0002BP\u0005\u000f\u0011q\u0001S1tQN+G/A\u000efq\u0016\u001cW\u000f^8s\u0013\u0012$vNU;o]&tw\rV1tW&#7\u000fI\u0001\u001dKb,7-\u001e;peN\u0004VM\u001c3j]\u001e$UmY8n[&\u001c8/[8o+\t\u00119\u000b\u0005\u0005\u0003\u0006\t=!\u0011\tBU!\u0011\t9Fa+\n\t\t5\u0016q\u0007\u0002\u001a\u000bb,7-\u001e;pe\u0012+7m\\7nSN\u001c\u0018n\u001c8Ti\u0006$X-A\u000ffq\u0016\u001cW\u000f^8sgB+g\u000eZ5oO\u0012+7m\\7nSN\u001c\u0018n\u001c8!\u0003])\u00070Z2vi>\u00148OU3n_Z,GMQ=EK\u000e|W.\u0006\u0002\u00036BA!q\u0017Be\u0005\u0003\u0012I+\u0004\u0002\u0003:*!!1\u0018B_\u0003\u0015\u0019\u0017m\u00195f\u0015\u0011\u0011yL!1\u0002\r\r|W.\\8o\u0015\u0011\u0011\u0019M!2\u0002\r\u001d|wn\u001a7f\u0015\t\u00119-A\u0002d_6LAAa3\u0003:\n)1)Y2iK\u0006AR\r_3dkR|'o\u001d*f[>4X\r\u001a\"z\t\u0016\u001cw.\u001c\u0011\u0002/I,hN\\5oOR\u000b7o[:Cs\u0016CXmY;u_J\u001cXC\u0001Bj!!\u0011\u0019E!6\u0003B\u0005u\u0014\u0002\u0002Bl\u0005+\u00121!T1q\u0003=Awn\u001d;U_\u0016CXmY;u_J\u001cXC\u0001Bo!!\u0011)Aa\u0004\u0003B\t}\u0007C\u0002B\u0003\u0005;\u0013\t%\u0001\ti_N$Hk\\#yK\u000e,Ho\u001c:tA\u0005Y\u0001n\\:ug\nK(+Y2l\u00031Awn\u001d;t\u0005f\u0014\u0016mY6!\u0003A)\u00070Z2vi>\u0014\u0018\n\u001a+p\u0011>\u001cH/\u0006\u0002\u0003lBA!Q\u0001B\b\u0005\u0003\u0012\t%A\tfq\u0016\u001cW\u000f^8s\u0013\u0012$v\u000eS8ti\u0002\n!\"\u00192peR$\u0016.\\3s\u0003-\t'm\u001c:u)&lWM\u001d\u0011\u0002AUt7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u)>,\u0005\u0010]5ssRKW.Z\u000b\u0003\u0005o\u0004\u0002B!\u0002\u0003\u0010\tU\u0011QZ\u0001\"k:\u001c8\r[3ek2\f'\r\\3UCN\\7+\u001a;U_\u0016C\b/\u001b:z)&lW\rI\u0001\rI\u0006<7k\u00195fIVdWM]\u000b\u0003\u0005\u007f\u0004B!a\u0016\u0004\u0002%!11AA\u001c\u00051!\u0015iR*dQ\u0016$W\u000f\\3s\u0003A!\u0017mZ*dQ\u0016$W\u000f\\3s?\u0012*\u0017\u000f\u0006\u0003\u0003`\r%\u0001\"\u0003B4}\u0005\u0005\t\u0019\u0001B\u0000\u00035!\u0017mZ*dQ\u0016$W\u000f\\3sA\u00059!-Y2lK:$WCAB\t!\u0011\t9fa\u0005\n\t\rU\u0011q\u0007\u0002\u0011'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012\f1BY1dW\u0016tGm\u0018\u0013fcR!!qLB\u000e\u0011%\u00119'QA\u0001\u0002\u0004\u0019\t\"\u0001\u0005cC\u000e\\WM\u001c3!\u0003Ai\u0017\r](viB,H\u000f\u0016:bG.,'/\u0006\u0002\u0004$A!\u0011\u0011OB\u0013\u0013\u0011\u00199#a\u000f\u0003-5\u000b\u0007oT;uaV$HK]1dW\u0016\u0014X*Y:uKJ\f\u0011#\\1q\u001fV$\b/\u001e;Ue\u0006\u001c7.\u001a:!\u0003I\u00198\r[3ek2\f'\r\\3Ck&dG-\u001a:\u0016\u0005\r=\u0002\u0003BA,\u0007cIAaa\r\u00028\t\u00112k\u00195fIVd\u0017M\u00197f\u0005VLG\u000eZ3s\u0003Y\u00198\r[3ek2\f'\r\\3Ck&dG-\u001a:`I\u0015\fH\u0003\u0002B0\u0007sA\u0011Ba\u001aG\u0003\u0003\u0005\raa\f\u0002'M\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a\"vS2$WM\u001d\u0011\u0002%M\u001c\u0007.\u001a3vY&tw-T8eK\u000e{gNZ\u000b\u0003\u0005\u0003\n1c]2iK\u0012,H.\u001b8h\u001b>$WmQ8oM\u0002\nab]2iK\u0012,H.\u001b8h\u001b>$W-\u0006\u0002\u0004HA!1\u0011JB0\u001d\u0011\u0019Yea\u0017\u000f\t\r53\u0011\f\b\u0005\u0007\u001f\u001a9F\u0004\u0003\u0004R\rUc\u0002\u0002B$\u0007'J!!!\u0012\n\t\u0005\u0005\u00131I\u0005\u0005\u0003{\ty$\u0003\u0003\u0002:\u0005m\u0012\u0002BB/\u0003o\tabU2iK\u0012,H.\u001b8h\u001b>$W-\u0003\u0003\u0004b\r\r$AD*dQ\u0016$W\u000f\\5oO6{G-\u001a\u0006\u0005\u0007;\n9$A\btG\",G-\u001e7j]\u001elu\u000eZ3!\u0003!\u0011xn\u001c;Q_>dWCAB6!\u0011\t9f!\u001c\n\t\r=\u0014q\u0007\u0002\u0005!>|G.A\u0005s_>$\bk\\8mA\u0005\u0001B/Y:l%\u0016\u001cX\u000f\u001c;HKR$XM]\u000b\u0003\u0007o\u0002B!a\u0016\u0004z%!11PA\u001c\u0005A!\u0016m]6SKN,H\u000e^$fiR,'/\u0001\u000buCN\\'+Z:vYR<U\r\u001e;fe~#S-\u001d\u000b\u0005\u0005?\u001a\t\tC\u0005\u0003h=\u000b\t\u00111\u0001\u0004x\u0005\tB/Y:l%\u0016\u001cX\u000f\u001c;HKR$XM\u001d\u0011\u0002%\t\f'O]5feNKhn\u0019+j[\u0016|W\u000f^\u0001\u0013E\u0006\u0014(/[3s\u0007>|'\u000fZ5oCR|'/\u0006\u0002\u0004\fB!1QRBJ\u001b\t\u0019yI\u0003\u0003\u0004\u0012\u0006m\u0012a\u0001:qG&!1QSBH\u0005-\u0011\u0006oY#oIB|\u0017N\u001c;\u0002-\t\f'O]5fe\u000e{wN\u001d3j]\u0006$xN]0%KF$BAa\u0018\u0004\u001c\"I!qM*\u0002\u0002\u0003\u000711R\u0001\u0014E\u0006\u0014(/[3s\u0007>|'\u000fZ5oCR|'\u000fI\u0001\u0011I\u00164\u0017-\u001e7u%\u0006\u001c7NV1mk\u0016,\"aa)\u0011\r\u0005-\u00131\u0017B!\u0003E!WMZ1vYR\u0014\u0016mY6WC2,X\rI\u0001\u001c[\u0006L(-Z%oSR\u0014\u0015M\u001d:jKJ\u001cun\u001c:eS:\fGo\u001c:\u0015\u0005\t}\u0013aD:fi\u0012\u000buiU2iK\u0012,H.\u001a:\u0015\t\t}3q\u0016\u0005\b\u0005wD\u0006\u0019\u0001B\u0000\u0003)Ig.\u001b;jC2L'0\u001a\u000b\u0005\u0005?\u001a)\fC\u0004\u0004\u000ee\u0003\ra!\u0005\u0002\u00139,w\u000fV1tW&#GCAAg\u0003\u0015\u0019H/\u0019:u\u00035\u0001xn\u001d;Ti\u0006\u0014H\u000fS8pW\u0006Y1/\u001e2nSR$\u0016m]6t)\u0011\u0011yfa1\t\u000f\r\u0015W\f1\u0001\u0003$\u00059A/Y:l'\u0016$\u0018\u0001F2sK\u0006$X\rV1tWN+G/T1oC\u001e,'\u000f\u0006\u0004\u0003\u0016\r-7Q\u001a\u0005\b\u0007\u000bt\u0006\u0019\u0001B\u0012\u0011\u001d\tIH\u0018a\u0001\u0003{\n1c[5mY\u0006cG\u000eV1tW\u0006#H/Z7qiN$\u0002Ba\u0018\u0004T\u000e]71\u001c\u0005\b\u0007+|\u0006\u0019AA?\u0003\u001d\u0019H/Y4f\u0013\u0012Dqa!7`\u0001\u0004\t9)A\bj]R,'O];qiRC'/Z1e\u0011\u001d\u0019in\u0018a\u0001\u0005\u0003\naA]3bg>t\u0017aD6jY2$\u0016m]6BiR,W\u000e\u001d;\u0015\u0011\u0005\u001d51]Bt\u0007SDqa!:a\u0001\u0004\ti-\u0001\u0004uCN\\\u0017\n\u001a\u0005\b\u00073\u0004\u0007\u0019AAD\u0011\u001d\u0019i\u000e\u0019a\u0001\u0005\u0003\n\u0011D\\8uS\u001aL\b+\u0019:uSRLwN\\\"p[BdW\r^5p]R1!qLBx\u0007cDqa!6b\u0001\u0004\ti\bC\u0004\u0004t\u0006\u0004\r!! \u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u0001\u0010i\u0006\u001c8nU3u\r&t\u0017n\u001d5fIR!!qLB}\u0011\u001d\u0019YP\u0019a\u0001\u0005+\tq!\\1oC\u001e,'/\u0001\u000esKN|WO]2f\u001f\u001a4WM]*j]\u001edW\rV1tWN+G\u000f\u0006\b\u0005\u0002\u0011]A\u0011\u0004C\u000f\ts!\u0019\u0005b\u0014\u0011\u0011\u0005-C1AAD\t\u000fIA\u0001\"\u0002\u0002N\t1A+\u001e9mKJ\u0002b!a\u0013\u00024\u0012%\u0001\u0003\u0002C\u0006\t#qAaa\u0013\u0005\u000e%!AqBA\u001c\u00031!\u0016m]6M_\u000e\fG.\u001b;z\u0013\u0011!\u0019\u0002\"\u0006\u0003\u0019Q\u000b7o\u001b'pG\u0006d\u0017\u000e^=\u000b\t\u0011=\u0011q\u0007\u0005\b\u0007\u000b\u001c\u0007\u0019\u0001B\u000b\u0011\u001d!Yb\u0019a\u0001\t\u0013\t1\"\\1y\u0019>\u001c\u0017\r\\5us\"9AqD2A\u0002\u0011\u0005\u0012AD:ik\u001a4G.\u001a3PM\u001a,'o\u001d\t\u0007\tG!i\u0003b\r\u000f\t\u0011\u0015B\u0011\u0006\b\u0005\u0005\u000f\"9#\u0003\u0002\u0002P%!A1FA'\u0003\u001d\u0001\u0018mY6bO\u0016LA\u0001b\f\u00052\t\u00191+Z9\u000b\t\u0011-\u0012Q\n\t\u0005\u0003/\")$\u0003\u0003\u00058\u0005]\"aC,pe.,'o\u00144gKJDq\u0001b\u000fd\u0001\u0004!i$A\u0007bm\u0006LG.\u00192mK\u000e\u0003Xo\u001d\t\u0007\u0003\u0017\"y$! \n\t\u0011\u0005\u0013Q\n\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\t\u000b\u001a\u0007\u0019\u0001C$\u0003I\tg/Y5mC\ndWMU3t_V\u00148-Z:\u0011\r\u0005-Cq\bC%!\u0011\t9\u0006b\u0013\n\t\u00115\u0013q\u0007\u0002\u0019\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u001c\u0018)\\8v]R\u001c\bb\u0002C)G\u0002\u0007A1K\u0001\u0006i\u0006\u001c8n\u001d\t\u0007\tG!)\u0006\"\u0017\n\t\u0011]C\u0011\u0007\u0002\u000b\u0013:$W\r_3e'\u0016\f\bC\u0002B\u0003\t7\"y&\u0003\u0003\u0005^\t\u001d!aC!se\u0006L()\u001e4gKJ\u0004B!a\u0016\u0005b%!A1MA\u001c\u0005=!\u0016m]6EKN\u001c'/\u001b9uS>t\u0017AD1eIJ+hN\\5oOR\u000b7o\u001b\u000b\t\u0005?\"I\u0007\"\u001c\u0005r!9A1\u000e3A\u0002\u00055\u0017a\u0001;jI\"9Aq\u000e3A\u0002\t\u0005\u0013AB3yK\u000eLE\rC\u0004\u0004F\u0012\u0004\rA!\u0006\u0002;I,7o\\;sG\u0016\u001cX*Z3u)\u0006\u001c8NU3rk&\u0014X-\\3oiN$\u0002\u0002b\u001e\u0005~\u0011}D1\u0011\t\u0007\u0003\u0017\n\u0019\f\"\u001f\u0011\u0011\t\r#Q\u001bB!\tw\u0002\u0002Ba\u0011\u0003V\n\u0005\u0013Q\u001a\u0005\b\u0007\u000b,\u0007\u0019\u0001B\u000b\u0011\u001d!\t)\u001aa\u0001\u0003{\n\u0011\"\u0019<bS2\u001c\u0005/^:\t\u000f\u0011\u0015U\r1\u0001\u0005J\u0005!\u0012M^1jY^{'o[3s%\u0016\u001cx.\u001e:dKN\fq\"\\5o)\u0006\u001c8\u000eT8dC2LG/\u001f\u000b\u0007\t\u000f!Y\tb$\t\u000f\u00115e\r1\u0001\u0005\b\u0005\u0011A.\r\u0005\b\t#3\u0007\u0019\u0001C\u0004\u0003\ta''\u0001\bsKN|WO]2f\u001f\u001a4WM]:\u0015\r\u0011]E1\u0014CQ!\u0019!\u0019\u0003\"\f\u0005\u001aB1A1\u0005C\u0017\t?Bq\u0001\"(h\u0001\u0004!y*\u0001\u0004pM\u001a,'o\u001d\t\u0007\tG!)\u0006b\r\t\u0013\u0011\rv\r%AA\u0002\u0005\u001d\u0015AE5t\u00032dgI]3f%\u0016\u001cx.\u001e:dKN\f\u0001D]3t_V\u00148-Z(gM\u0016\u00148\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t!IK\u000b\u0003\u0002\b\u0012-6F\u0001CW!\u0011!y\u000b\"/\u000e\u0005\u0011E&\u0002\u0002CZ\tk\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\t\u0011]\u0016QJ\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002C^\tc\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003M*\b\u000fZ1uKVs7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u)&lWm\\;u\u0003:$7\u000b^1si\u0006\u0013wN\u001d;US6,'\u000f\u0006\u0004\u0003`\u0011\u0005G1\u0019\u0005\b\u0007\u000bL\u0007\u0019\u0001B\u000b\u0011\u001d!)-\u001ba\u0001\u0003{\n\u0011\u0002^1tW&sG-\u001a=\u0002I\r\u0014X-\u0019;f+:\u001c8\r[3ek2\f'\r\\3UCN\\7+\u001a;BE>\u0014H\u000fV5nKJ$b\u0001b3\u0005T\u0012U\u0007\u0003\u0002Cg\t\u001fl!!a;\n\t\u0011E\u00171\u001e\u0002\n)&lWM\u001d+bg.Dqa!2k\u0001\u0004\u0011)\u0002C\u0004\u0005F*\u0004\r!! \u0002\u001bMDWO\u001a4mK>3g-\u001a:t)\u0011!y\nb7\t\u000f\u0011u5\u000e1\u0001\u0005 \u0006a1\u000f^1ukN,\u0006\u000fZ1uKRA!q\fCq\tG$)\u0010C\u0004\u0005l1\u0004\r!!4\t\u000f\u0011\u0015H\u000e1\u0001\u0005h\u0006)1\u000f^1uKB!A\u0011\u001eCx\u001d\u0011\u0019i\u0005b;\n\t\u00115\u00181H\u0001\n)\u0006\u001c8n\u0015;bi\u0016LA\u0001\"=\u0005t\nIA+Y:l'R\fG/\u001a\u0006\u0005\t[\fY\u0004C\u0004\u0005x2\u0004\r\u0001\"?\u0002\u001dM,'/[1mSj,G\rR1uCB!A1`C\u0001\u001b\t!iP\u0003\u0003\u0005\u0000\u00065\u0018a\u00018j_&!Q1\u0001C\u007f\u0005)\u0011\u0015\u0010^3Ck\u001a4WM]\u0001\u001aKb,7-\u001e;pe\"+\u0017M\u001d;cK\u0006$(+Z2fSZ,G\r\u0006\u0006\u0002\b\u0016%Q1BC\u001d\u000b\u0013Bq\u0001b\u001cn\u0001\u0004\u0011\t\u0005C\u0004\u0006\u000e5\u0004\r!b\u0004\u0002\u0019\u0005\u001c7-^7Va\u0012\fG/Z:\u0011\r\u0005-CqHC\t!!\tY\u0005b\u0001\u0002N\u0016M\u0001C\u0002C\u0012\t[))\u0002\r\u0004\u0006\u0018\u0015\u0005RQ\u0007\t\t\u0003#+I\"\"\b\u00064%!Q1DAJ\u00055\t5mY;nk2\fGo\u001c:WeA!QqDC\u0011\u0019\u0001!A\"b\t\u0006\f\u0005\u0005\t\u0011!B\u0001\u000bK\u00111a\u0018\u00132#\u0011)9#\"\f\u0011\t\u0005-S\u0011F\u0005\u0005\u000bW\tiEA\u0004O_RD\u0017N\\4\u0011\t\u0005-SqF\u0005\u0005\u000bc\tiEA\u0002B]f\u0004B!b\b\u00066\u0011aQqGC\u0006\u0003\u0003\u0005\tQ!\u0001\u0006&\t\u0019q\f\n\u001a\t\u000f\u0015mR\u000e1\u0001\u0006>\u0005q!\r\\8dW6\u000bg.Y4fe&#\u0007\u0003BC \u000b\u000bj!!\"\u0011\u000b\t\u0015\r\u00131H\u0001\bgR|'/Y4f\u0013\u0011)9%\"\u0011\u0003\u001d\tcwnY6NC:\fw-\u001a:JI\"9Q1J7A\u0002\u00155\u0013aD3yK\u000e,Ho\u001c:Va\u0012\fG/Z:\u0011\u0011\t\u0015QqJC)\u000b'JAAa6\u0003\bAA\u00111\nC\u0002\u0003{\ni\b\u0005\u0003\u0006V\u0015mSBAC,\u0015\u0011)I&a\u000f\u0002\u0011\u0015DXmY;u_JLA!\"\u0018\u0006X\tyQ\t_3dkR|'/T3ue&\u001c7/A\u0013hKR$\u0016m]6BG\u000e,X.\u001e7bE2,\u0017J\u001c4pg\u0006sG\r\u0015:pG\u0016\u001c8OU1uKR!Q1MC:!!\tY\u0005b\u0001\u0006f\u00155\u0004C\u0002C\u0012\t[)9\u0007\u0005\u0003\u0002X\u0015%\u0014\u0002BC6\u0003o\u0011q\"Q2dk6,H.\u00192mK&sgm\u001c\t\u0005\u0003\u0017*y'\u0003\u0003\u0006r\u00055#A\u0002#pk\ndW\rC\u0004\u0006v9\u0004\r!b\u001e\u0002\u000fU\u0004H-\u0019;fgB1A1\u0005C\u0017\u000bs\u0002d!b\u001f\u0006\u0000\u0015\u0015\u0005\u0003CAI\u000b3)i(b!\u0011\t\u0015}Qq\u0010\u0003\r\u000b\u0003+\u0019(!A\u0001\u0002\u000b\u0005QQ\u0005\u0002\u0004?\u0012\u001a\u0004\u0003BC\u0010\u000b\u000b#A\"b\"\u0006t\u0005\u0005\t\u0011!B\u0001\u000bK\u00111a\u0018\u00135\u0003I9W\r\u001e+bg.\u0004&o\\2fgN\u0014\u0016\r^3\u0015\r\u00155TQRCI\u0011\u001d)yi\u001ca\u0001\u0003\u001b\f1B]3d_J$7OU3bI\"9Q1S8A\u0002\u00055\u0017aD3yK\u000e,Ho\u001c:Sk:$\u0016.\\3\u0002/!\fg\u000e\u001a7f)\u0006\u001c8nR3ui&twMU3tk2$HC\u0002B0\u000b3+i\nC\u0004\u0006\u001cB\u0004\rA!\u0006\u0002\u001dQ\f7o[*fi6\u000bg.Y4fe\"9A1\u000e9A\u0002\u00055\u0017\u0001\u00065b]\u0012dWmU;dG\u0016\u001c8OZ;m)\u0006\u001c8\u000e\u0006\u0005\u0003`\u0015\rVQUCT\u0011\u001d)Y*\u001da\u0001\u0005+Aq\u0001b\u001br\u0001\u0004\ti\rC\u0004\u0006*F\u0004\r!b+\u0002\u0015Q\f7o\u001b*fgVdG\u000f\r\u0003\u0006.\u0016U\u0006CBA,\u000b_+\u0019,\u0003\u0003\u00062\u0006]\"\u0001\u0005#je\u0016\u001cG\u000fV1tWJ+7/\u001e7u!\u0011)y\"\".\u0005\u0019\u0015]VqUA\u0001\u0002\u0003\u0015\t!\"\n\u0003\u0007}#S'\u0001\tiC:$G.\u001a$bS2,G\rV1tWRQ!qLC_\u000b\u007f+\t-\"2\t\u000f\u0015m%\u000f1\u0001\u0003\u0016!9A1\u000e:A\u0002\u00055\u0007bBCbe\u0002\u0007Aq]\u0001\ni\u0006\u001c8n\u0015;bi\u0016Dqa!8s\u0001\u0004)9\r\u0005\u0003\u0002r\u0015%\u0017\u0002BCf\u0003w\u0011\u0001\u0003V1tW\u001a\u000b\u0017\u000e\\3e%\u0016\f7o\u001c8\u00021!\fg\u000e\u001a7f!\u0006\u0014H/\u001b;j_:\u001cu.\u001c9mKR,G\r\u0006\u0004\u0003`\u0015EW1\u001b\u0005\b\u0007+\u001c\b\u0019AA?\u0011\u001d\u0019\u0019p\u001da\u0001\u0003{\nQ!\u001a:s_J$BAa\u0018\u0006Z\"9Q1\u001c;A\u0002\t\u0005\u0013aB7fgN\fw-Z\u0001\u0005gR|\u0007\u000f\u0006\u0003\u0003`\u0015\u0005\b\"CCrkB\u0005\t\u0019AA?\u0003!)\u00070\u001b;D_\u0012,\u0017AD:u_B$C-\u001a4bk2$H%M\u000b\u0003\u000bSTC!! \u0005,\u0006\u0011B-\u001a4bk2$\b+\u0019:bY2,G.[:n)\t\ti(\u0001\fdQ\u0016\u001c7n\u00159fGVd\u0017\r^1cY\u0016$\u0016m]6t\u0003Q)\u00070Z2vi>\u0014H)Z2p[6L7o]5p]R1!qLC{\u000bsDq!b>z\u0001\u0004\u0011\t%\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012Dq!b?z\u0001\u0004)i0\u0001\teK\u000e|W.\\5tg&|g.\u00138g_B!\u0011qKC\u0000\u0013\u00111\t!a\u000e\u00031\u0015CXmY;u_J$UmY8n[&\u001c8/[8o\u0013:4w.\u0001\u000fhKR,\u00050Z2vi>\u0014H)Z2p[6L7o]5p]N#\u0018\r^3\u0015\t\u0019\u001da\u0011\u0002\t\u0007\u0003\u0017\n\u0019L!+\t\u000f\u0015](\u00101\u0001\u0003B\u0005aQ\r_3dkR|'\u000fT8tiR1!q\fD\b\r#Aq!b>|\u0001\u0004\u0011\t\u0005C\u0004\u0004^n\u0004\rAb\u0005\u0011\t\u0005]cQC\u0005\u0005\r/\t9D\u0001\nFq\u0016\u001cW\u000f^8s\u0019>\u001c8OU3bg>t\u0017!D<pe.,'OU3n_Z,G\r\u0006\u0005\u0003`\u0019ua\u0011\u0005D\u0013\u0011\u001d1y\u0002 a\u0001\u0005\u0003\n\u0001b^8sW\u0016\u0014\u0018\n\u001a\u0005\b\rGa\b\u0019\u0001B!\u0003\u0011Awn\u001d;\t\u000f\u0015mG\u00101\u0001\u0003B\u0005yAn\\4Fq\u0016\u001cW\u000f^8s\u0019>\u001c8\u000f\u0006\u0005\u0003`\u0019-bQ\u0006D\u0019\u0011\u001d)90 a\u0001\u0005\u0003BqAb\f~\u0001\u0004\u0011\t%\u0001\u0005i_N$\bk\u001c:u\u0011\u001d\u0019i. a\u0001\r'\tqcZ3u\t\u0016\u001cw.\\7jgNLwN\u001c#ve\u0006$\u0018n\u001c8\u0015\t\t\u0005cq\u0007\u0005\b\u000bot\b\u0019\u0001B!\u0003A\u0019G.Z1okB$\u0016m]6Ti\u0006$X\r\u0006\u0003\u0003`\u0019u\u0002b\u0002C6\u007f\u0002\u0007\u0011QZ\u0001\u000fe\u0016lwN^3Fq\u0016\u001cW\u000f^8s)\u0019\u0011yFb\u0011\u0007F!AQq_A\u0001\u0001\u0004\u0011\t\u0005\u0003\u0005\u0004^\u0006\u0005\u0001\u0019\u0001D\n\u00035)\u00070Z2vi>\u0014\u0018\t\u001a3fIR1!q\fD&\r\u001bB\u0001\u0002b\u001c\u0002\u0004\u0001\u0007!\u0011\t\u0005\t\rG\t\u0019\u00011\u0001\u0003B\u00059r-\u001a;Fq\u0016\u001cW\u000f^8sg\u0006c\u0017N^3P]\"{7\u000f\u001e\u000b\u0005\r'2Y\u0006\u0005\u0004\u0002L\u0005MfQ\u000b\t\u0007\u0005\u000729F!\u0011\n\t\u0019e#Q\u000b\u0002\u0004'\u0016$\b\u0002\u0003D\u0012\u0003\u000b\u0001\rA!\u0011\u0002/!\f7/\u0012=fGV$xN]:BY&4Xm\u00148I_N$H\u0003BAD\rCB\u0001Bb\t\u0002\b\u0001\u0007!\u0011I\u0001\u0013Q\u0006\u001c\bj\\:u\u00032Lg/Z(o%\u0006\u001c7\u000e\u0006\u0003\u0002\b\u001a\u001d\u0004\u0002\u0003D5\u0003\u0013\u0001\rA!\u0011\u0002\tI\f7m[\u0001\u0010SN,\u00050Z2vi>\u0014\u0018\t\\5wKR!\u0011q\u0011D8\u0011!!y'a\u0003A\u0002\t\u0005\u0013AD5t\u000bb,7-\u001e;pe\n+8/\u001f\u000b\u0005\u0003\u000f3)\b\u0003\u0005\u0005p\u00055\u0001\u0019\u0001B!\u0003aI7/\u0012=fGV$xN\u001d#fG>lW.[:tS>tW\r\u001a\u000b\u0005\u0003\u000f3Y\b\u0003\u0005\u0005p\u0005=\u0001\u0019\u0001B!\u0003QI7\u000fS8ti\u0012+7m\\7nSN\u001c\u0018n\u001c8fIR!\u0011q\u0011DA\u0011!1\u0019#!\u0005A\u0002\t\u0005\u0013!D3yG2,H-\u001a3O_\u0012,7\u000f\u0006\u0002\u0007V\u0005qq-\u001a;SC\u000e\\gi\u001c:I_N$H\u0003BBR\r\u0017C\u0001Bb\t\u0002\u0016\u0001\u0007!\u0011I\u0001\u0011O\u0016$(+Y2lg\u001a{'\u000fS8tiN$BA\"%\u0007\u0014B1A1\u0005C\u0017\u0007GC\u0001B\"&\u0002\u0018\u0001\u0007aqS\u0001\u0006Q>\u001cHo\u001d\t\u0007\tG!iC!\u0011\u0002!]\f\u0017\u000e\u001e\"bG.,g\u000e\u001a*fC\u0012L\u0018!D1qa2L7-\u0019;j_:LE\r\u0006\u0002\u0003B\u0005!\u0012\r\u001d9mS\u000e\fG/[8o\u0003R$X-\u001c9u\u0013\u0012$\"aa)\u00021Q\f7o[*fi6\u000bg.Y4fe\u001a{'/\u0011;uK6\u0004H\u000f\u0006\u0004\u0007(\u001a%f1\u0016\t\u0007\u0003\u0017\n\u0019L!\u0006\t\u0011\rU\u0017q\u0004a\u0001\u0003{B\u0001B\",\u0002 \u0001\u0007\u0011QP\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0003E!\u0016m]6TG\",G-\u001e7fe&k\u0007\u000f\u001c\t\u0005\u0003/\n\u0019c\u0005\u0003\u0002$\u0005%CC\u0001DY\u0003]\u00196\tS#E+2+%kX'P\t\u0016{\u0006KU(Q\u000bJ#\u0016,\u0001\rT\u0007\"+E)\u0016'F%~ku\nR#`!J{\u0005+\u0012*U3\u0002\nqcY1mGVd\u0017\r^3Bm\u0006LG.\u00192mKNcw\u000e^:\u0015\u001d\u0005udq\u0018Da\r\u000749Mb3\u0007N\"A\u0011\u0011HA\u0016\u0001\u0004\ty\n\u0003\u0005\u0002>\u0006-\u0002\u0019AAa\u0011!1)-a\u000bA\u0002\u0005u\u0014\u0001\u0002:q\u0013\u0012D\u0001B\"3\u0002,\u0001\u0007AQH\u0001\u000fCZ\f\u0017\u000e\\1cY\u0016\u0014\u0006+\u00133t\u0011!!Y$a\u000bA\u0002\u0011u\u0002\u0002\u0003C#\u0003W\u0001\rAb4\u0011\r\u0005-Cq\bBj\u0003Q\u0001(/[8sSRL'0Z\"p]R\f\u0017N\\3sgV1aQ\u001bDv\r?$BAb6\u0007dB1A1\u0005Dm\r;LAAb7\u00052\t!A*[:u!\u0011)yBb8\u0005\u0011\u0019\u0005\u0018Q\u0006b\u0001\u000bK\u0011\u0011\u0001\u0016\u0005\t\rK\fi\u00031\u0001\u0007h\u0006\u0019Q.\u00199\u0011\u0011\t\u0015!q\u0002Du\r_\u0004B!b\b\u0007l\u0012AaQ^A\u0017\u0005\u0004))CA\u0001L!\u0019\u0011)\u0001b\u0017\u0007^\u0006AR.Y=cK\u000e\u0013X-\u0019;f\u0011\u0016\fG\u000e\u001e5Ue\u0006\u001c7.\u001a:\u0015\t\u0005EfQ\u001f\u0005\t\u0003S\ny\u00031\u0001\u0002p\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"TC\u0001D\u007fU\u0011\ty\tb+"
)
public class TaskSchedulerImpl implements TaskScheduler, Logging {
   private Option healthTrackerOpt;
   private long barrierSyncTimeout;
   private final SparkContext sc;
   private final int maxTaskFailures;
   private final boolean isLocal;
   public final Clock org$apache$spark$scheduler$TaskSchedulerImpl$$clock;
   private final SparkConf conf;
   private final long SPECULATION_INTERVAL_MS;
   private final long MIN_TIME_TO_SPECULATION;
   private final boolean efficientTaskCalcualtionEnabled;
   private final ScheduledExecutorService speculationScheduler;
   private final long STARVATION_TIMEOUT_MS;
   private final int CPUS_PER_TASK;
   private final HashMap taskSetsByStageIdAndAttempt;
   private final HashMap noRejectsSinceLastReset;
   private final boolean legacyLocalityWaitReset;
   private final ConcurrentHashMap taskIdToTaskSetManager;
   private final HashMap taskIdToExecutorId;
   private volatile boolean hasReceivedTask;
   private volatile boolean org$apache$spark$scheduler$TaskSchedulerImpl$$hasLaunchedTask;
   private final ScheduledExecutorService starvationTimer;
   private final AtomicLong nextTaskId;
   private final HashMap executorIdToRunningTaskIds;
   private final HashMap executorsPendingDecommission;
   private final Cache executorsRemovedByDecom;
   private final HashMap hostToExecutors;
   private final HashMap hostsByRack;
   private final HashMap executorIdToHost;
   private final ScheduledExecutorService abortTimer;
   private final HashMap unschedulableTaskSetToExpiryTime;
   private DAGScheduler dagScheduler;
   private SchedulerBackend backend;
   private final MapOutputTrackerMaster mapOutputTracker;
   private SchedulableBuilder schedulableBuilder;
   private final String schedulingModeConf;
   private final Enumeration.Value schedulingMode;
   private final Pool rootPool;
   private TaskResultGetter taskResultGetter;
   private RpcEndpoint barrierCoordinator;
   private final Option defaultRackValue;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private String org$apache$spark$scheduler$TaskScheduler$$appId;
   private volatile byte bitmap$0;

   public static Clock $lessinit$greater$default$4() {
      return TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$3();
   }

   public static List prioritizeContainers(final HashMap map) {
      return TaskSchedulerImpl$.MODULE$.prioritizeContainers(map);
   }

   public static int calculateAvailableSlots(final TaskSchedulerImpl scheduler, final SparkConf conf, final int rpId, final int[] availableRPIds, final int[] availableCpus, final Map[] availableResources) {
      return TaskSchedulerImpl$.MODULE$.calculateAvailableSlots(scheduler, conf, rpId, availableRPIds, availableCpus, availableResources);
   }

   public static String SCHEDULER_MODE_PROPERTY() {
      return TaskSchedulerImpl$.MODULE$.SCHEDULER_MODE_PROPERTY();
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

   public String org$apache$spark$scheduler$TaskScheduler$$appId() {
      return this.org$apache$spark$scheduler$TaskScheduler$$appId;
   }

   public final void org$apache$spark$scheduler$TaskScheduler$_setter_$org$apache$spark$scheduler$TaskScheduler$$appId_$eq(final String x$1) {
      this.org$apache$spark$scheduler$TaskScheduler$$appId = x$1;
   }

   public SparkContext sc() {
      return this.sc;
   }

   public int maxTaskFailures() {
      return this.maxTaskFailures;
   }

   private Option healthTrackerOpt$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.healthTrackerOpt = TaskSchedulerImpl$.MODULE$.org$apache$spark$scheduler$TaskSchedulerImpl$$maybeCreateHealthTracker(this.sc());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.healthTrackerOpt;
   }

   public Option healthTrackerOpt() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.healthTrackerOpt$lzycompute() : this.healthTrackerOpt;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public long SPECULATION_INTERVAL_MS() {
      return this.SPECULATION_INTERVAL_MS;
   }

   public long MIN_TIME_TO_SPECULATION() {
      return this.MIN_TIME_TO_SPECULATION;
   }

   public boolean efficientTaskCalcualtionEnabled() {
      return this.efficientTaskCalcualtionEnabled;
   }

   private ScheduledExecutorService speculationScheduler() {
      return this.speculationScheduler;
   }

   public long STARVATION_TIMEOUT_MS() {
      return this.STARVATION_TIMEOUT_MS;
   }

   public int CPUS_PER_TASK() {
      return this.CPUS_PER_TASK;
   }

   private HashMap taskSetsByStageIdAndAttempt() {
      return this.taskSetsByStageIdAndAttempt;
   }

   private HashMap noRejectsSinceLastReset() {
      return this.noRejectsSinceLastReset;
   }

   private boolean legacyLocalityWaitReset() {
      return this.legacyLocalityWaitReset;
   }

   public ConcurrentHashMap taskIdToTaskSetManager() {
      return this.taskIdToTaskSetManager;
   }

   public HashMap taskIdToExecutorId() {
      return this.taskIdToExecutorId;
   }

   private boolean hasReceivedTask() {
      return this.hasReceivedTask;
   }

   private void hasReceivedTask_$eq(final boolean x$1) {
      this.hasReceivedTask = x$1;
   }

   public boolean org$apache$spark$scheduler$TaskSchedulerImpl$$hasLaunchedTask() {
      return this.org$apache$spark$scheduler$TaskSchedulerImpl$$hasLaunchedTask;
   }

   private void hasLaunchedTask_$eq(final boolean x$1) {
      this.org$apache$spark$scheduler$TaskSchedulerImpl$$hasLaunchedTask = x$1;
   }

   private ScheduledExecutorService starvationTimer() {
      return this.starvationTimer;
   }

   public AtomicLong nextTaskId() {
      return this.nextTaskId;
   }

   private HashMap executorIdToRunningTaskIds() {
      return this.executorIdToRunningTaskIds;
   }

   public HashMap executorsPendingDecommission() {
      return this.executorsPendingDecommission;
   }

   public Cache executorsRemovedByDecom() {
      return this.executorsRemovedByDecom;
   }

   public synchronized Map runningTasksByExecutors() {
      return (Map)this.executorIdToRunningTaskIds().toMap(.MODULE$.refl()).transform((x$1, v) -> BoxesRunTime.boxToInteger($anonfun$runningTasksByExecutors$1(x$1, v)));
   }

   public HashMap hostToExecutors() {
      return this.hostToExecutors;
   }

   public HashMap hostsByRack() {
      return this.hostsByRack;
   }

   public HashMap executorIdToHost() {
      return this.executorIdToHost;
   }

   private ScheduledExecutorService abortTimer() {
      return this.abortTimer;
   }

   public HashMap unschedulableTaskSetToExpiryTime() {
      return this.unschedulableTaskSetToExpiryTime;
   }

   public DAGScheduler dagScheduler() {
      return this.dagScheduler;
   }

   public void dagScheduler_$eq(final DAGScheduler x$1) {
      this.dagScheduler = x$1;
   }

   public SchedulerBackend backend() {
      return this.backend;
   }

   public void backend_$eq(final SchedulerBackend x$1) {
      this.backend = x$1;
   }

   public MapOutputTrackerMaster mapOutputTracker() {
      return this.mapOutputTracker;
   }

   private SchedulableBuilder schedulableBuilder() {
      return this.schedulableBuilder;
   }

   private void schedulableBuilder_$eq(final SchedulableBuilder x$1) {
      this.schedulableBuilder = x$1;
   }

   private String schedulingModeConf() {
      return this.schedulingModeConf;
   }

   public Enumeration.Value schedulingMode() {
      return this.schedulingMode;
   }

   public Pool rootPool() {
      return this.rootPool;
   }

   public TaskResultGetter taskResultGetter() {
      return this.taskResultGetter;
   }

   public void taskResultGetter_$eq(final TaskResultGetter x$1) {
      this.taskResultGetter = x$1;
   }

   private long barrierSyncTimeout$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.barrierSyncTimeout = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.BARRIER_SYNC_TIMEOUT()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.barrierSyncTimeout;
   }

   private long barrierSyncTimeout() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.barrierSyncTimeout$lzycompute() : this.barrierSyncTimeout;
   }

   public RpcEndpoint barrierCoordinator() {
      return this.barrierCoordinator;
   }

   public void barrierCoordinator_$eq(final RpcEndpoint x$1) {
      this.barrierCoordinator = x$1;
   }

   public Option defaultRackValue() {
      return this.defaultRackValue;
   }

   private void maybeInitBarrierCoordinator() {
      if (this.barrierCoordinator() == null) {
         this.barrierCoordinator_$eq(new BarrierCoordinator(this.barrierSyncTimeout(), this.sc().listenerBus(), this.sc().env().rpcEnv()));
         this.sc().env().rpcEnv().setupEndpoint("barrierSync", this.barrierCoordinator());
         this.logInfo((Function0)(() -> "Registered BarrierCoordinator endpoint"));
      }
   }

   public void setDAGScheduler(final DAGScheduler dagScheduler) {
      this.dagScheduler_$eq(dagScheduler);
   }

   public void initialize(final SchedulerBackend backend) {
      label30: {
         Object var7;
         label29: {
            label32: {
               this.backend_$eq(backend);
               Enumeration.Value var3 = this.schedulingMode();
               Enumeration.Value var10001 = SchedulingMode$.MODULE$.FIFO();
               if (var10001 == null) {
                  if (var3 == null) {
                     break label32;
                  }
               } else if (var10001.equals(var3)) {
                  break label32;
               }

               var10001 = SchedulingMode$.MODULE$.FAIR();
               if (var10001 == null) {
                  if (var3 != null) {
                     break label30;
                  }
               } else if (!var10001.equals(var3)) {
                  break label30;
               }

               var7 = new FairSchedulableBuilder(this.rootPool(), this.sc());
               break label29;
            }

            var7 = new FIFOSchedulableBuilder(this.rootPool());
         }

         this.schedulableBuilder_$eq((SchedulableBuilder)var7);
         this.schedulableBuilder().buildPools();
         return;
      }

      String var10003 = TaskSchedulerImpl$.MODULE$.SCHEDULER_MODE_PROPERTY();
      throw new IllegalArgumentException("Unsupported " + var10003 + ": " + this.schedulingMode());
   }

   public long newTaskId() {
      return this.nextTaskId().getAndIncrement();
   }

   public void start() {
      this.backend().start();
      if (!this.isLocal && BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_ENABLED()))) {
         this.logInfo((Function0)(() -> "Starting speculative execution thread"));
         this.speculationScheduler().scheduleWithFixedDelay(() -> Utils$.MODULE$.tryOrStopSparkContext(this.sc(), (JFunction0.mcV.sp)() -> this.checkSpeculatableTasks()), this.SPECULATION_INTERVAL_MS(), this.SPECULATION_INTERVAL_MS(), TimeUnit.MILLISECONDS);
      }
   }

   public void postStartHook() {
      this.waitBackendReady();
   }

   public void submitTasks(final TaskSet taskSet) {
      Task[] tasks = taskSet.tasks();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Adding task set "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(taskSet.logId()).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with ", " tasks resource profile "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(tasks.length))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.resourceProfileId()))}))))));
      synchronized(this){}

      try {
         TaskSetManager manager = this.createTaskSetManager(taskSet, this.maxTaskFailures());
         int stage = taskSet.stageId();
         HashMap stageTaskSets = (HashMap)this.taskSetsByStageIdAndAttempt().getOrElseUpdate(BoxesRunTime.boxToInteger(stage), () -> new HashMap());
         stageTaskSets.foreach((x0$1) -> {
            $anonfun$submitTasks$3(x0$1);
            return BoxedUnit.UNIT;
         });
         stageTaskSets.update(BoxesRunTime.boxToInteger(taskSet.stageAttemptId()), manager);
         this.schedulableBuilder().addTaskSetManager(manager, manager.taskSet().properties());
         if (!this.isLocal && !this.hasReceivedTask()) {
            this.starvationTimer().scheduleAtFixedRate(new TimerTask() {
               // $FF: synthetic field
               private final TaskSchedulerImpl $outer;

               public void run() {
                  if (!this.$outer.org$apache$spark$scheduler$TaskSchedulerImpl$$hasLaunchedTask()) {
                     this.$outer.logWarning((Function0)(() -> "Initial job has not accepted any resources; check your cluster UI to ensure that workers are registered and have sufficient resources"));
                  } else {
                     this.cancel();
                  }
               }

               public {
                  if (TaskSchedulerImpl.this == null) {
                     throw null;
                  } else {
                     this.$outer = TaskSchedulerImpl.this;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return var0.lambdaDeserialize<invokedynamic>(var0);
               }
            }, this.STARVATION_TIMEOUT_MS(), this.STARVATION_TIMEOUT_MS(), TimeUnit.MILLISECONDS);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.hasReceivedTask_$eq(true);
      } catch (Throwable var8) {
         throw var8;
      }

      this.backend().reviveOffers();
   }

   public TaskSetManager createTaskSetManager(final TaskSet taskSet, final int maxTaskFailures) {
      return new TaskSetManager(this, taskSet, maxTaskFailures, this.healthTrackerOpt(), this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock);
   }

   public synchronized void killAllTaskAttempts(final int stageId, final boolean interruptThread, final String reason) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Canceling stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killing all running tasks in stage ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)}))))));
      this.taskSetsByStageIdAndAttempt().get(BoxesRunTime.boxToInteger(stageId)).foreach((attempts) -> {
         $anonfun$killAllTaskAttempts$3(this, interruptThread, reason, stageId, attempts);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized boolean killTaskAttempt(final long taskId, final boolean interruptThread, final String reason) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killing task ", ": ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId)), new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)})))));
      Option execId = this.taskIdToExecutorId().get(BoxesRunTime.boxToLong(taskId));
      if (execId.isDefined()) {
         this.backend().killTask(taskId, (String)execId.get(), interruptThread, reason);
         return true;
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not kill task ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because no task with that ID was found."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return false;
      }
   }

   public void notifyPartitionCompletion(final int stageId, final int partitionId) {
      this.taskResultGetter().enqueuePartitionCompletionNotification(stageId, partitionId);
   }

   public synchronized void taskSetFinished(final TaskSetManager manager) {
      this.taskSetsByStageIdAndAttempt().get(BoxesRunTime.boxToInteger(manager.taskSet().stageId())).foreach((taskSetsForStage) -> {
         taskSetsForStage.$minus$eq(BoxesRunTime.boxToInteger(manager.taskSet().stageAttemptId()));
         return taskSetsForStage.isEmpty() ? this.taskSetsByStageIdAndAttempt().$minus$eq(BoxesRunTime.boxToInteger(manager.taskSet().stageId())) : BoxedUnit.UNIT;
      });
      this.noRejectsSinceLastReset().$minus$eq(manager.taskSet());
      manager.parent().removeSchedulable(manager);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removed TaskSet "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(manager.taskSet().logId()).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" whose tasks have all completed, from pool ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, manager.parent().name())}))))));
   }

   private Tuple2 resourceOfferSingleTaskSet(final TaskSetManager taskSet, final Enumeration.Value maxLocality, final Seq shuffledOffers, final int[] availableCpus, final ExecutorResourcesAmounts[] availableResources, final IndexedSeq tasks) {
      Object var7 = new Object();

      Tuple2 var10000;
      try {
         BooleanRef noDelayScheduleRejects = BooleanRef.create(true);
         ObjectRef minLaunchedLocality = ObjectRef.create(scala.None..MODULE$);
         shuffledOffers.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            String execId = ((WorkerOffer)shuffledOffers.apply(i)).executorId();
            String host = ((WorkerOffer)shuffledOffers.apply(i)).host();
            int taskSetRpID = taskSet.taskSet().resourceProfileId();
            if (this.sc().resourceProfileManager().canBeScheduled(taskSetRpID, ((WorkerOffer)shuffledOffers.apply(i)).resourceProfileId())) {
               Option taskResAssignmentsOpt = this.resourcesMeetTaskRequirements(taskSet, availableCpus[i], availableResources[i]);
               taskResAssignmentsOpt.foreach((taskResAssignments) -> {
                  $anonfun$resourceOfferSingleTaskSet$2(this, taskSetRpID, taskSet, execId, host, maxLocality, noDelayScheduleRejects, tasks, i, minLaunchedLocality, availableCpus, availableResources, var7, taskResAssignments);
                  return BoxedUnit.UNIT;
               });
            }
         });
         var10000 = new Tuple2(BoxesRunTime.boxToBoolean(noDelayScheduleRejects.elem), (Option)minLaunchedLocality.elem);
      } catch (NonLocalReturnControl var11) {
         if (var11.key() != var7) {
            throw var11;
         }

         var10000 = (Tuple2)var11.value();
      }

      return var10000;
   }

   private void addRunningTask(final long tid, final String execId, final TaskSetManager taskSet) {
      this.taskIdToTaskSetManager().put(BoxesRunTime.boxToLong(tid), taskSet);
      this.taskIdToExecutorId().update(BoxesRunTime.boxToLong(tid), execId);
      ((HashSet)this.executorIdToRunningTaskIds().apply(execId)).add(BoxesRunTime.boxToLong(tid));
   }

   private Option resourcesMeetTaskRequirements(final TaskSetManager taskSet, final int availCpus, final ExecutorResourcesAmounts availWorkerResources) {
      int rpId = taskSet.taskSet().resourceProfileId();
      ResourceProfile taskSetProf = this.sc().resourceProfileManager().resourceProfileFromId(rpId);
      int taskCpus = ResourceProfile$.MODULE$.getTaskCpusOrDefaultForProfile(taskSetProf, this.conf());
      return (Option)(availCpus < taskCpus ? scala.None..MODULE$ : availWorkerResources.assignAddressesCustomResources(taskSetProf));
   }

   private Option minTaskLocality(final Option l1, final Option l2) {
      if (l1.isEmpty()) {
         return l2;
      } else if (l2.isEmpty()) {
         return l1;
      } else {
         return ((Ordered)l1.get()).$less(l2.get()) ? l1 : l2;
      }
   }

   public synchronized Seq resourceOffers(final IndexedSeq offers, final boolean isAllFreeResources) {
      BooleanRef newExecAvail = BooleanRef.create(false);
      offers.foreach((o) -> {
         $anonfun$resourceOffers$1(this, newExecAvail, o);
         return BoxedUnit.UNIT;
      });
      IndexedSeq hosts = (IndexedSeq)((SeqOps)offers.map((x$4) -> x$4.host())).distinct();
      ((IterableOps)hosts.zip(this.getRacksForHosts(hosts))).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$resourceOffers$3(check$ifrefutable$1))).foreach((x$5) -> {
         if (x$5 != null) {
            String host = (String)x$5._1();
            Option var5 = (Option)x$5._2();
            if (var5 instanceof Some) {
               Some var6 = (Some)var5;
               String rack = (String)var6.value();
               return (HashSet)((Growable)this.hostsByRack().getOrElseUpdate(rack, () -> new HashSet())).$plus$eq(host);
            }
         }

         throw new MatchError(x$5);
      });
      this.healthTrackerOpt().foreach((x$6) -> {
         $anonfun$resourceOffers$6(x$6);
         return BoxedUnit.UNIT;
      });
      IndexedSeq filteredOffers = (IndexedSeq)this.healthTrackerOpt().map((healthTracker) -> (IndexedSeq)offers.filter((offer) -> BoxesRunTime.boxToBoolean($anonfun$resourceOffers$8(healthTracker, offer)))).getOrElse(() -> offers);
      IndexedSeq shuffledOffers = this.shuffleOffers(filteredOffers);
      IndexedSeq tasks = (IndexedSeq)shuffledOffers.map((o) -> new ArrayBuffer(o.cores() / this.CPUS_PER_TASK()));
      ExecutorResourcesAmounts[] availableResources = (ExecutorResourcesAmounts[])((IterableOnceOps)shuffledOffers.map((x$7) -> x$7.resources())).toArray(scala.reflect.ClassTag..MODULE$.apply(ExecutorResourcesAmounts.class));
      int[] availableCpus = (int[])((IterableOnceOps)shuffledOffers.map((o) -> BoxesRunTime.boxToInteger($anonfun$resourceOffers$12(o)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
      int[] resourceProfileIds = (int[])((IterableOnceOps)shuffledOffers.map((o) -> BoxesRunTime.boxToInteger($anonfun$resourceOffers$13(o)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
      ArrayBuffer sortedTaskSets = this.rootPool().getSortedTaskSetQueue();
      sortedTaskSets.foreach((taskSet) -> {
         $anonfun$resourceOffers$14(this, newExecAvail, taskSet);
         return BoxedUnit.UNIT;
      });
      sortedTaskSets.foreach((taskSet) -> {
         $anonfun$resourceOffers$16(this, availableResources, resourceProfileIds, availableCpus, shuffledOffers, tasks, isAllFreeResources, taskSet);
         return BoxedUnit.UNIT;
      });
      if (tasks.nonEmpty()) {
         this.hasLaunchedTask_$eq(true);
      }

      return (Seq)tasks.map((x$13) -> x$13.toSeq());
   }

   public boolean resourceOffers$default$2() {
      return true;
   }

   private void updateUnschedulableTaskSetTimeoutAndStartAbortTimer(final TaskSetManager taskSet, final int taskIndex) {
      long timeout = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.UNSCHEDULABLE_TASKSET_TIMEOUT())) * 1000L;
      this.unschedulableTaskSetToExpiryTime().update(taskSet, BoxesRunTime.boxToLong(this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock.getTimeMillis() + timeout));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Waiting for ", " ms for completely "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(timeout))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"excluded task to be schedulable again before aborting stage ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.stageId()))}))))));
      this.abortTimer().schedule(this.createUnschedulableTaskSetAbortTimer(taskSet, taskIndex), timeout, TimeUnit.MILLISECONDS);
   }

   private TimerTask createUnschedulableTaskSetAbortTimer(final TaskSetManager taskSet, final int taskIndex) {
      return new TimerTask(taskSet, taskIndex) {
         // $FF: synthetic field
         private final TaskSchedulerImpl $outer;
         private final TaskSetManager taskSet$6;
         private final int taskIndex$1;

         public void run() {
            synchronized(this.$outer){}

            try {
               if (this.$outer.unschedulableTaskSetToExpiryTime().contains(this.taskSet$6) && BoxesRunTime.unboxToLong(this.$outer.unschedulableTaskSetToExpiryTime().apply(this.taskSet$6)) <= this.$outer.org$apache$spark$scheduler$TaskSchedulerImpl$$clock.getTimeMillis()) {
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot schedule any task because all executors excluded due to failures. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Wait time for scheduling expired. Aborting stage ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(this.taskSet$6.stageId()))}))))));
                  this.taskSet$6.abortSinceCompletelyExcludedOnFailure(this.taskIndex$1);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxesRunTime.boxToBoolean(this.cancel());
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         public {
            if (TaskSchedulerImpl.this == null) {
               throw null;
            } else {
               this.$outer = TaskSchedulerImpl.this;
               this.taskSet$6 = taskSet$6;
               this.taskIndex$1 = taskIndex$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public IndexedSeq shuffleOffers(final IndexedSeq offers) {
      return (IndexedSeq)scala.util.Random..MODULE$.shuffle(offers, scala.collection.BuildFrom..MODULE$.buildFromIterableOps());
   }

   public void statusUpdate(final long tid, final Enumeration.Value state, final ByteBuffer serializedData) {
      ObjectRef failedExecutor = ObjectRef.create(scala.None..MODULE$);
      ObjectRef reason = ObjectRef.create(scala.None..MODULE$);
      synchronized(this){}

      try {
         this.liftedTree2$1(tid, state, reason, failedExecutor, serializedData);
      } catch (Throwable var9) {
         throw var9;
      }

      if (((Option)failedExecutor.elem).isDefined()) {
         scala.Predef..MODULE$.assert(((Option)reason.elem).isDefined());
         this.dagScheduler().executorLost((String)((Option)failedExecutor.elem).get(), (ExecutorLossReason)((Option)reason.elem).get());
         this.backend().reviveOffers();
      }
   }

   public boolean executorHeartbeatReceived(final String execId, final Tuple2[] accumUpdates, final BlockManagerId blockManagerId, final scala.collection.mutable.Map executorUpdates) {
      Tuple4[] accumUpdatesWithTaskIds = (Tuple4[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])accumUpdates), (x0$1) -> {
         if (x0$1 != null) {
            long id = x0$1._1$mcJ$sp();
            Seq updates = (Seq)x0$1._2();
            return scala.Option..MODULE$.apply(this.taskIdToTaskSetManager().get(BoxesRunTime.boxToLong(id))).map((taskSetMgr) -> {
               Tuple2 var7 = this.getTaskAccumulableInfosAndProcessRate(updates);
               if (var7 != null) {
                  Seq accInfos = (Seq)var7._1();
                  double taskProcessRate = var7._2$mcD$sp();
                  Tuple2 var6 = new Tuple2(accInfos, BoxesRunTime.boxToDouble(taskProcessRate));
                  Seq accInfos = (Seq)var6._1();
                  double taskProcessRate = var6._2$mcD$sp();
                  if (this.efficientTaskCalcualtionEnabled() && taskProcessRate > (double)0.0F) {
                     taskSetMgr.taskProcessRateCalculator().foreach((x$15) -> {
                        $anonfun$executorHeartbeatReceived$3(id, taskProcessRate, x$15);
                        return BoxedUnit.UNIT;
                     });
                  }

                  return new Tuple4(BoxesRunTime.boxToLong(id), BoxesRunTime.boxToInteger(taskSetMgr.stageId()), BoxesRunTime.boxToInteger(taskSetMgr.taskSet().stageAttemptId()), accInfos);
               } else {
                  throw new MatchError(var7);
               }
            });
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
      return this.dagScheduler().executorHeartbeatReceived(execId, accumUpdatesWithTaskIds, blockManagerId, executorUpdates);
   }

   private Tuple2 getTaskAccumulableInfosAndProcessRate(final Seq updates) {
      LongRef recordsRead = LongRef.create(0L);
      LongRef executorRunTime = LongRef.create(0L);
      Seq accInfos = (Seq)updates.map((acc) -> {
         if (this.efficientTaskCalcualtionEnabled() && acc.name().isDefined()) {
            label49: {
               String name = (String)acc.name().get();
               String var5 = InternalAccumulator.shuffleRead$.MODULE$.RECORDS_READ();
               if (name == null) {
                  if (var5 == null) {
                     break label49;
                  }
               } else if (name.equals(var5)) {
                  break label49;
               }

               String var6 = InternalAccumulator.input$.MODULE$.RECORDS_READ();
               if (name == null) {
                  if (var6 == null) {
                     break label49;
                  }
               } else if (name.equals(var6)) {
                  break label49;
               }

               String var7 = InternalAccumulator$.MODULE$.EXECUTOR_RUN_TIME();
               if (name == null) {
                  if (var7 != null) {
                     return acc.toInfoUpdate();
                  }
               } else if (!name.equals(var7)) {
                  return acc.toInfoUpdate();
               }

               executorRunTime.elem = BoxesRunTime.unboxToLong(acc.value());
               return acc.toInfoUpdate();
            }

            recordsRead.elem += BoxesRunTime.unboxToLong(acc.value());
         }

         return acc.toInfoUpdate();
      });
      double taskProcessRate = this.efficientTaskCalcualtionEnabled() ? this.getTaskProcessRate(recordsRead.elem, executorRunTime.elem) : (double)0.0F;
      return new Tuple2(accInfos, BoxesRunTime.boxToDouble(taskProcessRate));
   }

   public double getTaskProcessRate(final long recordsRead, final long executorRunTime) {
      return executorRunTime > 0L && recordsRead > 0L ? (double)recordsRead / ((double)executorRunTime / (double)1000.0F) : (double)0.0F;
   }

   public synchronized void handleTaskGettingResult(final TaskSetManager taskSetManager, final long tid) {
      taskSetManager.handleTaskGettingResult(tid);
   }

   public synchronized void handleSuccessfulTask(final TaskSetManager taskSetManager, final long tid, final DirectTaskResult taskResult) {
      taskSetManager.handleSuccessfulTask(tid, taskResult);
   }

   public synchronized void handleFailedTask(final TaskSetManager taskSetManager, final long tid, final Enumeration.Value taskState, final TaskFailedReason reason) {
      taskSetManager.handleFailedTask(tid, taskState, reason);
      if (!taskSetManager.isZombie() && !taskSetManager.someAttemptSucceeded(tid)) {
         this.backend().reviveOffers();
      }
   }

   public synchronized void handlePartitionCompleted(final int stageId, final int partitionId) {
      this.taskSetsByStageIdAndAttempt().get(BoxesRunTime.boxToInteger(stageId)).foreach((x$16) -> {
         $anonfun$handlePartitionCompleted$1(partitionId, x$16);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized void error(final String message) {
      if (this.taskSetsByStageIdAndAttempt().nonEmpty()) {
         this.taskSetsByStageIdAndAttempt().values().foreach((attempts) -> {
            $anonfun$error$1(this, message, attempts);
            return BoxedUnit.UNIT;
         });
      } else {
         throw SparkCoreErrors$.MODULE$.clusterSchedulerError(message);
      }
   }

   public void stop(final int exitCode) {
      Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.speculationScheduler().shutdown());
      if (this.backend() != null) {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.backend().stop(exitCode));
      }

      if (this.taskResultGetter() != null) {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.taskResultGetter().stop());
      }

      if (this.barrierCoordinator() != null) {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.barrierCoordinator().stop());
      }

      ThreadUtils$.MODULE$.shutdown(this.starvationTimer(), ThreadUtils$.MODULE$.shutdown$default$2());
      ThreadUtils$.MODULE$.shutdown(this.abortTimer(), ThreadUtils$.MODULE$.shutdown$default$2());
   }

   public int stop$default$1() {
      return 0;
   }

   public int defaultParallelism() {
      return this.backend().defaultParallelism();
   }

   public void checkSpeculatableTasks() {
      boolean shouldRevive = false;
      synchronized(this){}

      try {
         shouldRevive = this.rootPool().checkSpeculatableTasks(this.MIN_TIME_TO_SPECULATION());
      } catch (Throwable var4) {
         throw var4;
      }

      if (shouldRevive) {
         this.backend().reviveOffers();
      }
   }

   public void executorDecommission(final String executorId, final ExecutorDecommissionInfo decommissionInfo) {
      synchronized(this){}

      try {
         if (this.executorIdToHost().contains(executorId)) {
            this.executorsPendingDecommission().update(executorId, new ExecutorDecommissionState(this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock.getTimeMillis(), decommissionInfo.workerHost()));
         }
      } catch (Throwable var5) {
         throw var5;
      }

      this.rootPool().executorDecommission(executorId);
      this.backend().reviveOffers();
   }

   public synchronized Option getExecutorDecommissionState(final String executorId) {
      return this.executorsPendingDecommission().get(executorId).orElse(() -> scala.Option..MODULE$.apply(this.executorsRemovedByDecom().getIfPresent(executorId)));
   }

   public void executorLost(final String executorId, final ExecutorLossReason reason) {
      Option failedExecutor = scala.None..MODULE$;
      synchronized(this){}

      try {
         if (this.executorIdToRunningTaskIds().contains(executorId)) {
            String hostPort = (String)this.executorIdToHost().apply(executorId);
            this.logExecutorLoss(executorId, hostPort, reason);
            this.removeExecutor(executorId, reason);
            failedExecutor = new Some(executorId);
         } else {
            Option var7 = this.executorIdToHost().get(executorId);
            if (var7 instanceof Some) {
               Some var8 = (Some)var7;
               String hostPort = (String)var8.value();
               this.logExecutorLoss(executorId, hostPort, reason);
               this.removeExecutor(executorId, reason);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               if (!scala.None..MODULE$.equals(var7)) {
                  throw new MatchError(var7);
               }

               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Lost an executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(already removed): ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)}))))));
               BoxedUnit var12 = BoxedUnit.UNIT;
            }
         }
      } catch (Throwable var11) {
         throw var11;
      }

      if (failedExecutor.isDefined()) {
         this.dagScheduler().executorLost((String)failedExecutor.get(), reason);
         this.backend().reviveOffers();
      }
   }

   public void workerRemoved(final String workerId, final String host, final String message) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Handle removed worker ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))))));
      this.dagScheduler().workerRemoved(workerId, host, message);
   }

   private void logExecutorLoss(final String executorId, final String hostPort, final ExecutorLossReason reason) {
      if (LossReasonPending$.MODULE$.equals(reason)) {
         this.logDebug((Function0)(() -> "Executor " + executorId + " on " + hostPort + " lost, but reason not yet known."));
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (ExecutorKilled$.MODULE$.equals(reason)) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " killed by driver."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hostPort)}))))));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (reason instanceof ExecutorDecommission) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is decommissioned"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hostPort)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, this.getDecommissionDuration(executorId))}))))));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Lost executor ", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, hostPort), new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private String getDecommissionDuration(final String executorId) {
      return (String)this.executorsPendingDecommission().get(executorId).map((s) -> {
         String var10000 = Utils$.MODULE$.msDurationToString(this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock.getTimeMillis() - s.startTime());
         return " after " + var10000;
      }).getOrElse(() -> "");
   }

   private void cleanupTaskState(final long tid) {
      this.taskIdToTaskSetManager().remove(BoxesRunTime.boxToLong(tid));
      this.taskIdToExecutorId().remove(BoxesRunTime.boxToLong(tid)).foreach((executorId) -> {
         $anonfun$cleanupTaskState$1(this, tid, executorId);
         return BoxedUnit.UNIT;
      });
   }

   private void removeExecutor(final String executorId, final ExecutorLossReason reason) {
      this.executorIdToRunningTaskIds().remove(executorId).foreach((taskIds) -> {
         $anonfun$removeExecutor$1(this, executorId, taskIds);
         return BoxedUnit.UNIT;
      });
      String host = (String)this.executorIdToHost().apply(executorId);
      HashSet execs = (HashSet)this.hostToExecutors().getOrElse(host, () -> new HashSet());
      execs.$minus$eq(executorId);
      if (execs.isEmpty()) {
         this.hostToExecutors().$minus$eq(host);
         this.getRackForHost(host).foreach((rack) -> {
            $anonfun$removeExecutor$5(this, host, rack);
            return BoxedUnit.UNIT;
         });
      }

      label17: {
         this.executorsPendingDecommission().remove(executorId).foreach((x$19) -> {
            $anonfun$removeExecutor$7(this, executorId, x$19);
            return BoxedUnit.UNIT;
         });
         LossReasonPending$ var5 = LossReasonPending$.MODULE$;
         if (reason == null) {
            if (var5 == null) {
               break label17;
            }
         } else if (reason.equals(var5)) {
            break label17;
         }

         this.executorIdToHost().$minus$eq(executorId);
         this.rootPool().executorLost(executorId, host, reason);
      }

      this.healthTrackerOpt().foreach((x$20) -> {
         $anonfun$removeExecutor$8(executorId, x$20);
         return BoxedUnit.UNIT;
      });
   }

   public void executorAdded(final String execId, final String host) {
      this.dagScheduler().executorAdded(execId, host);
   }

   public synchronized Option getExecutorsAliveOnHost(final String host) {
      return this.hostToExecutors().get(host).map((x$21) -> (HashSet)x$21.filterNot((execId) -> BoxesRunTime.boxToBoolean($anonfun$getExecutorsAliveOnHost$2(this, execId)))).map((x$22) -> x$22.toSet());
   }

   public synchronized boolean hasExecutorsAliveOnHost(final String host) {
      return this.hostToExecutors().get(host).exists((executors) -> BoxesRunTime.boxToBoolean($anonfun$hasExecutorsAliveOnHost$1(this, executors)));
   }

   public synchronized boolean hasHostAliveOnRack(final String rack) {
      return this.hostsByRack().get(rack).exists((hosts) -> BoxesRunTime.boxToBoolean($anonfun$hasHostAliveOnRack$1(this, hosts)));
   }

   public synchronized boolean isExecutorAlive(final String execId) {
      return this.executorIdToRunningTaskIds().contains(execId) && !this.isExecutorDecommissioned(execId);
   }

   public synchronized boolean isExecutorBusy(final String execId) {
      return this.executorIdToRunningTaskIds().get(execId).exists((x$23) -> BoxesRunTime.boxToBoolean($anonfun$isExecutorBusy$1(x$23)));
   }

   public final boolean isExecutorDecommissioned(final String execId) {
      return this.getExecutorDecommissionState(execId).isDefined();
   }

   public final boolean isHostDecommissioned(final String host) {
      return this.hostToExecutors().get(host).exists((executors) -> BoxesRunTime.boxToBoolean($anonfun$isHostDecommissioned$1(this, executors)));
   }

   public Set excludedNodes() {
      return (Set)this.healthTrackerOpt().map((x$25) -> x$25.excludedNodeList()).getOrElse(() -> scala.Predef..MODULE$.Set().empty());
   }

   public Option getRackForHost(final String host) {
      return (Option)this.getRacksForHosts(new scala.collection.immutable..colon.colon(host, scala.collection.immutable.Nil..MODULE$)).head();
   }

   public Seq getRacksForHosts(final Seq hosts) {
      return (Seq)hosts.map((x$26) -> this.defaultRackValue());
   }

   private void waitBackendReady() {
      if (!this.backend().isReady()) {
         while(!this.backend().isReady()) {
            if (this.sc().stopped().get()) {
               throw new IllegalStateException("Spark context stopped while waiting for backend");
            }

            synchronized(this){}

            try {
               this.wait(100L);
            } catch (Throwable var3) {
               throw var3;
            }
         }

      }
   }

   public String applicationId() {
      return this.backend().applicationId();
   }

   public Option applicationAttemptId() {
      return this.backend().applicationAttemptId();
   }

   public synchronized Option taskSetManagerForAttempt(final int stageId, final int stageAttemptId) {
      return this.taskSetsByStageIdAndAttempt().get(BoxesRunTime.boxToInteger(stageId)).flatMap((attempts) -> attempts.get(BoxesRunTime.boxToInteger(stageAttemptId)).map((manager) -> manager));
   }

   // $FF: synthetic method
   public static final int $anonfun$runningTasksByExecutors$1(final String x$1, final HashSet v) {
      return v.size();
   }

   // $FF: synthetic method
   private final Enumeration.Value liftedTree1$1() {
      try {
         return SchedulingMode$.MODULE$.withName(this.schedulingModeConf());
      } catch (NoSuchElementException var2) {
         throw SparkCoreErrors$.MODULE$.unrecognizedSchedulerModePropertyError(TaskSchedulerImpl$.MODULE$.SCHEDULER_MODE_PROPERTY(), this.schedulingModeConf());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$submitTasks$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         TaskSetManager ts = (TaskSetManager)x0$1._2();
         ts.isZombie_$eq(true);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$killAllTaskAttempts$6(final TaskSchedulerImpl $this, final long tid$1, final boolean interruptThread$1, final String reason$1, final String execId) {
      $this.backend().killTask(tid$1, execId, interruptThread$1, "Stage cancelled: " + reason$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$killAllTaskAttempts$4(final TaskSchedulerImpl $this, final boolean interruptThread$1, final String reason$1, final int stageId$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         TaskSetManager tsm = (TaskSetManager)x0$1._2();
         tsm.runningTasksSet().foreach((JFunction1.mcVJ.sp)(tid) -> $this.taskIdToExecutorId().get(BoxesRunTime.boxToLong(tid)).foreach((execId) -> {
               $anonfun$killAllTaskAttempts$6($this, tid, interruptThread$1, reason$1, execId);
               return BoxedUnit.UNIT;
            }));
         tsm.suspend();
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stage ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stageId$1))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " was cancelled"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(tsm.taskSet().stageAttemptId()))}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$killAllTaskAttempts$3(final TaskSchedulerImpl $this, final boolean interruptThread$1, final String reason$1, final int stageId$1, final HashMap attempts) {
      attempts.foreach((x0$1) -> {
         $anonfun$killAllTaskAttempts$4($this, interruptThread$1, reason$1, stageId$1, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOfferSingleTaskSet$3(final TaskSchedulerImpl $this, final IndexedSeq tasks$2, final int i$1, final String execId$1, final TaskSetManager taskSet$2, final int index$1, final int taskCpus$1, final ObjectRef minLaunchedLocality$1, final int[] availableCpus$1, final ExecutorResourcesAmounts[] availableResources$1, final TaskDescription task) {
      Tuple2 var10000;
      if (task != null) {
         ((Growable)tasks$2.apply(i$1)).$plus$eq(task);
         $this.addRunningTask(task.taskId(), execId$1, taskSet$2);
         var10000 = new Tuple2(((TaskInfo)taskSet$2.taskInfos().apply(BoxesRunTime.boxToLong(task.taskId()))).taskLocality(), task.resources());
      } else {
         scala.Predef..MODULE$.assert(taskSet$2.isBarrier(), () -> "TaskDescription can only be null for barrier task");
         BarrierPendingLaunchTask barrierTask = (BarrierPendingLaunchTask)taskSet$2.barrierPendingLaunchTasks().apply(BoxesRunTime.boxToInteger(index$1));
         barrierTask.assignedOfferIndex_$eq(i$1);
         barrierTask.assignedCores_$eq(taskCpus$1);
         var10000 = new Tuple2(barrierTask.taskLocality(), barrierTask.assignedResources());
      }

      Tuple2 var13 = var10000;
      if (var13 != null) {
         Enumeration.Value locality = (Enumeration.Value)var13._1();
         Map resources = (Map)var13._2();
         Tuple2 var12 = new Tuple2(locality, resources);
         Enumeration.Value locality = (Enumeration.Value)var12._1();
         Map resources = (Map)var12._2();
         minLaunchedLocality$1.elem = $this.minTaskLocality((Option)minLaunchedLocality$1.elem, new Some(locality));
         availableCpus$1[i$1] -= taskCpus$1;
         scala.Predef..MODULE$.assert(availableCpus$1[i$1] >= 0);
         availableResources$1[i$1].acquire(resources);
      } else {
         throw new MatchError(var13);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOfferSingleTaskSet$2(final TaskSchedulerImpl $this, final int taskSetRpID$1, final TaskSetManager taskSet$2, final String execId$1, final String host$1, final Enumeration.Value maxLocality$1, final BooleanRef noDelayScheduleRejects$1, final IndexedSeq tasks$2, final int i$1, final ObjectRef minLaunchedLocality$1, final int[] availableCpus$1, final ExecutorResourcesAmounts[] availableResources$1, final Object nonLocalReturnKey1$1, final Map taskResAssignments) {
      try {
         ResourceProfile prof = $this.sc().resourceProfileManager().resourceProfileFromId(taskSetRpID$1);
         int taskCpus = ResourceProfile$.MODULE$.getTaskCpusOrDefaultForProfile(prof, $this.conf());
         Tuple3 var18 = taskSet$2.resourceOffer(execId$1, host$1, maxLocality$1, taskCpus, taskResAssignments);
         if (var18 != null) {
            Option taskDescOption = (Option)var18._1();
            boolean didReject = BoxesRunTime.unboxToBoolean(var18._2());
            int index = BoxesRunTime.unboxToInt(var18._3());
            Tuple3 var17 = new Tuple3(taskDescOption, BoxesRunTime.boxToBoolean(didReject), BoxesRunTime.boxToInteger(index));
            Option taskDescOption = (Option)var17._1();
            boolean didReject = BoxesRunTime.unboxToBoolean(var17._2());
            int index = BoxesRunTime.unboxToInt(var17._3());
            noDelayScheduleRejects$1.elem &= !didReject;
            taskDescOption.foreach((task) -> {
               $anonfun$resourceOfferSingleTaskSet$3($this, tasks$2, i$1, execId$1, taskSet$2, index, taskCpus, minLaunchedLocality$1, availableCpus$1, availableResources$1, task);
               return BoxedUnit.UNIT;
            });
         } else {
            throw new MatchError(var18);
         }
      } catch (TaskNotSerializableException var26) {
         $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Resource offer failed, task set "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " was not serializable"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_SET_NAME..MODULE$, taskSet$2.name())}))))));
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Tuple2(BoxesRunTime.boxToBoolean(noDelayScheduleRejects$1.elem), (Option)minLaunchedLocality$1.elem));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$1(final TaskSchedulerImpl $this, final BooleanRef newExecAvail$1, final WorkerOffer o) {
      if (!$this.hostToExecutors().contains(o.host())) {
         $this.hostToExecutors().update(o.host(), new HashSet());
      }

      if (!$this.executorIdToRunningTaskIds().contains(o.executorId())) {
         ((Growable)$this.hostToExecutors().apply(o.host())).$plus$eq(o.executorId());
         $this.executorAdded(o.executorId(), o.host());
         $this.executorIdToHost().update(o.executorId(), o.host());
         $this.executorIdToRunningTaskIds().update(o.executorId(), scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
         newExecAvail$1.elem = true;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resourceOffers$3(final Tuple2 check$ifrefutable$1) {
      if (check$ifrefutable$1 != null) {
         Option var3 = (Option)check$ifrefutable$1._2();
         if (var3 instanceof Some) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$6(final HealthTracker x$6) {
      x$6.applyExcludeOnFailureTimeout();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resourceOffers$8(final HealthTracker healthTracker$1, final WorkerOffer offer) {
      return !healthTracker$1.isNodeExcluded(offer.host()) && !healthTracker$1.isExecutorExcluded(offer.executorId());
   }

   // $FF: synthetic method
   public static final int $anonfun$resourceOffers$12(final WorkerOffer o) {
      return o.cores();
   }

   // $FF: synthetic method
   public static final int $anonfun$resourceOffers$13(final WorkerOffer o) {
      return o.resourceProfileId();
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$14(final TaskSchedulerImpl $this, final BooleanRef newExecAvail$1, final TaskSetManager taskSet) {
      $this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("parentName: %s, name: %s, runningTasks: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{taskSet.parent().name(), taskSet.name(), BoxesRunTime.boxToInteger(taskSet.runningTasks())}))));
      if (newExecAvail$1.elem) {
         taskSet.executorAdded();
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$19(final TaskSchedulerImpl $this, final TaskSetManager taskSet$4, final IndexedSeq shuffledOffers$2, final int[] availableCpus$2, final ExecutorResourcesAmounts[] availableResources$2, final IndexedSeq tasks$3, final BooleanRef launchedAnyTask$1, final BooleanRef noDelaySchedulingRejects$1, final ObjectRef globalMinLocality$1, final Enumeration.Value currentMaxLocality) {
      boolean launchedTaskAtCurrentMaxLocality = false;

      do {
         Tuple2 var13 = $this.resourceOfferSingleTaskSet(taskSet$4, currentMaxLocality, shuffledOffers$2, availableCpus$2, availableResources$2, tasks$3);
         if (var13 == null) {
            throw new MatchError(var13);
         }

         boolean noDelayScheduleReject = var13._1$mcZ$sp();
         Option minLocality = (Option)var13._2();
         Tuple2 var12 = new Tuple2(BoxesRunTime.boxToBoolean(noDelayScheduleReject), minLocality);
         boolean noDelayScheduleReject = var12._1$mcZ$sp();
         Option minLocality = (Option)var12._2();
         launchedTaskAtCurrentMaxLocality = minLocality.isDefined();
         launchedAnyTask$1.elem |= launchedTaskAtCurrentMaxLocality;
         noDelaySchedulingRejects$1.elem &= noDelayScheduleReject;
         globalMinLocality$1.elem = $this.minTaskLocality((Option)globalMinLocality$1.elem, minLocality);
      } while(launchedTaskAtCurrentMaxLocality);

   }

   // $FF: synthetic method
   public static final boolean $anonfun$resourceOffers$22(final TaskSchedulerImpl $this, final Tuple2 x) {
      return !$this.isExecutorBusy((String)x._1());
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$23(final String executorId$1, final HealthTracker blt) {
      blt.killExcludedIdleExecutor(executorId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$29(final int[] availableCpus$2, final ExecutorResourcesAmounts[] availableResources$2, final TaskSetManager taskSet$4, final BarrierPendingLaunchTask task) {
      int var4 = task.assignedOfferIndex();
      availableCpus$2[var4] += task.assignedCores();
      availableResources$2[task.assignedOfferIndex()].release(task.assignedResources());
      taskSet$4.addPendingTask(task.index(), taskSet$4.addPendingTask$default$2(), taskSet$4.addPendingTask$default$3());
   }

   // $FF: synthetic method
   public static final int $anonfun$resourceOffers$31(final Tuple2 x$10) {
      return ((TaskDescription)x$10._2()).partitionId();
   }

   // $FF: synthetic method
   public static final void $anonfun$resourceOffers$16(final TaskSchedulerImpl $this, final ExecutorResourcesAmounts[] availableResources$2, final int[] resourceProfileIds$1, final int[] availableCpus$2, final IndexedSeq shuffledOffers$2, final IndexedSeq tasks$3, final boolean isAllFreeResources$1, final TaskSetManager taskSet) {
      int var10000;
      if (taskSet.isBarrier()) {
         int rpId = taskSet.taskSet().resourceProfileId();
         Map[] resAmounts = (Map[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(availableResources$2), (x$8) -> x$8.resourceAddressAmount(), scala.reflect.ClassTag..MODULE$.apply(Map.class));
         var10000 = TaskSchedulerImpl$.MODULE$.calculateAvailableSlots($this, $this.conf(), rpId, resourceProfileIds$1, availableCpus$2, resAmounts);
      } else {
         var10000 = -1;
      }

      int numBarrierSlotsAvailable = var10000;
      if (taskSet.isBarrier() && numBarrierSlotsAvailable < taskSet.numTasks()) {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skip current round of resource offers for barrier stage "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " because the barrier taskSet requires "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.stageId()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " slots, while the total "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_SET_NAME..MODULE$, BoxesRunTime.boxToInteger(taskSet.numTasks()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of available slots is ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_SLOTS..MODULE$, BoxesRunTime.boxToInteger(numBarrierSlotsAvailable))}))))));
      } else {
         BooleanRef launchedAnyTask = BooleanRef.create(false);
         BooleanRef noDelaySchedulingRejects = BooleanRef.create(true);
         ObjectRef globalMinLocality = ObjectRef.create(scala.None..MODULE$);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])taskSet.myLocalityLevels()), (currentMaxLocality) -> {
            $anonfun$resourceOffers$19($this, taskSet, shuffledOffers$2, availableCpus$2, availableResources$2, tasks$3, launchedAnyTask, noDelaySchedulingRejects, globalMinLocality, currentMaxLocality);
            return BoxedUnit.UNIT;
         });
         if (!$this.legacyLocalityWaitReset()) {
            if (noDelaySchedulingRejects.elem) {
               if (launchedAnyTask.elem && (isAllFreeResources$1 || BoxesRunTime.unboxToBoolean($this.noRejectsSinceLastReset().getOrElse(taskSet.taskSet(), (JFunction0.mcZ.sp)() -> true)))) {
                  taskSet.resetDelayScheduleTimer((Option)globalMinLocality.elem);
                  $this.noRejectsSinceLastReset().update(taskSet.taskSet(), BoxesRunTime.boxToBoolean(true));
               }
            } else {
               $this.noRejectsSinceLastReset().update(taskSet.taskSet(), BoxesRunTime.boxToBoolean(false));
            }
         }

         if (!launchedAnyTask.elem) {
            taskSet.getCompletelyExcludedTaskIfAny($this.hostToExecutors()).foreach((JFunction1.mcVI.sp)(taskIndex) -> {
               Option var4 = $this.executorIdToRunningTaskIds().find((x) -> BoxesRunTime.boxToBoolean($anonfun$resourceOffers$22($this, x)));
               if (var4 instanceof Some var5) {
                  Tuple2 var6 = (Tuple2)var5.value();
                  if (var6 != null) {
                     String executorId = (String)var6._1();
                     if (!$this.unschedulableTaskSetToExpiryTime().contains(taskSet)) {
                        $this.healthTrackerOpt().foreach((blt) -> {
                           $anonfun$resourceOffers$23(executorId, blt);
                           return BoxedUnit.UNIT;
                        });
                        $this.updateUnschedulableTaskSetTimeoutAndStartAbortTimer(taskSet, taskIndex);
                        BoxedUnit var11 = BoxedUnit.UNIT;
                        return;
                     }

                     BoxedUnit var10 = BoxedUnit.UNIT;
                     return;
                  }
               }

               if (scala.None..MODULE$.equals(var4)) {
                  if (Utils$.MODULE$.isDynamicAllocationEnabled($this.conf())) {
                     if (!$this.unschedulableTaskSetToExpiryTime().contains(taskSet)) {
                        $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Notifying ExecutorAllocationManager to allocate more executors to"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" schedule the unschedulable task before aborting"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" stage ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.stageId()))}))))));
                        $this.dagScheduler().unschedulableTaskSetAdded(taskSet.taskSet().stageId(), taskSet.taskSet().stageAttemptId());
                        $this.updateUnschedulableTaskSetTimeoutAndStartAbortTimer(taskSet, taskIndex);
                        BoxedUnit var9 = BoxedUnit.UNIT;
                     } else {
                        BoxedUnit var8 = BoxedUnit.UNIT;
                     }
                  } else {
                     $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot schedule any task because all executors excluded from "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"failures. No idle executors can be found to kill. Aborting stage "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.stageId()))}))))));
                     taskSet.abortSinceCompletelyExcludedOnFailure(taskIndex);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }
               } else {
                  throw new MatchError(var4);
               }
            });
         } else if ($this.unschedulableTaskSetToExpiryTime().nonEmpty()) {
            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Clearing the expiry times for all unschedulable taskSets as a task "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was recently scheduled."})))).log(scala.collection.immutable.Nil..MODULE$))));
            $this.dagScheduler().unschedulableTaskSetRemoved(taskSet.taskSet().stageId(), taskSet.taskSet().stageAttemptId());
            $this.unschedulableTaskSetToExpiryTime().clear();
         }

         if (launchedAnyTask.elem && taskSet.isBarrier()) {
            BarrierPendingLaunchTask[] barrierPendingLaunchTasks = (BarrierPendingLaunchTask[])taskSet.barrierPendingLaunchTasks().values().toArray(scala.reflect.ClassTag..MODULE$.apply(BarrierPendingLaunchTask.class));
            if (barrierPendingLaunchTasks.length != taskSet.numTasks()) {
               if ($this.legacyLocalityWaitReset()) {
                  MessageWithContext logMsg = $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fail resource offers for barrier stage "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " because only "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.stageId()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PENDING_LAUNCH_TASKS..MODULE$, BoxesRunTime.boxToInteger(barrierPendingLaunchTasks.length))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"out of a total number "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"of ", " tasks got resource offers. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(taskSet.numTasks()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"We highly recommend you to use the non-legacy delay scheduling by setting "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " to false "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.LEGACY_LOCALITY_WAIT_RESET().key())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to get rid of this error."})))).log(scala.collection.immutable.Nil..MODULE$));
                  $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> logMsg));
                  taskSet.abort(logMsg.message(), taskSet.abort$default$2());
                  throw SparkCoreErrors$.MODULE$.sparkError(logMsg.message());
               }

               long curTime = $this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock.getTimeMillis();
               if (curTime - taskSet.lastResourceOfferFailLogTime() > (long)TaskSetManager$.MODULE$.BARRIER_LOGGING_INTERVAL()) {
                  $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Releasing the assigned resource offers since only partial tasks can "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"be launched. Waiting for later round resource offers."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  taskSet.lastResourceOfferFailLogTime_$eq(curTime);
               }

               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(barrierPendingLaunchTasks), (task) -> {
                  $anonfun$resourceOffers$29(availableCpus$2, availableResources$2, taskSet, task);
                  return BoxedUnit.UNIT;
               });
            } else {
               long launchTime = $this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock.getTimeMillis();
               Tuple2[] addressesWithDescs = (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(barrierPendingLaunchTasks), (task) -> {
                  TaskDescription taskDesc = taskSet.prepareLaunchingTask(task.execId(), task.host(), task.index(), task.taskLocality(), false, task.assignedCores(), task.assignedResources(), launchTime);
                  $this.addRunningTask(taskDesc.taskId(), taskDesc.executorId(), taskSet);
                  ((Growable)tasks$3.apply(task.assignedOfferIndex())).$plus$eq(taskDesc);
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(((WorkerOffer)shuffledOffers$2.apply(task.assignedOfferIndex())).address().get()), taskDesc);
               }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
               $this.maybeInitBarrierCoordinator();
               String addressesStr = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])addressesWithDescs), (x$10) -> BoxesRunTime.boxToInteger($anonfun$resourceOffers$31(x$10)), scala.math.Ordering.Int..MODULE$)), (x$11) -> (String)x$11._1(), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(",");
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])addressesWithDescs), (x$12) -> ((TaskDescription)x$12._2()).properties().setProperty("addresses", addressesStr));
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Successfully scheduled all the ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(addressesWithDescs.length))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"tasks for barrier stage ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(taskSet.stageId()))}))))));
            }

            taskSet.barrierPendingLaunchTasks().clear();
         }
      }
   }

   // $FF: synthetic method
   private final void liftedTree2$1(final long tid$2, final Enumeration.Value state$1, final ObjectRef reason$3, final ObjectRef failedExecutor$1, final ByteBuffer serializedData$1) {
      try {
         Option var8 = scala.Option..MODULE$.apply(this.taskIdToTaskSetManager().get(BoxesRunTime.boxToLong(tid$2)));
         if (var8 instanceof Some var9) {
            TaskSetManager taskSet;
            label63: {
               taskSet = (TaskSetManager)var9.value();
               Enumeration.Value var11 = TaskState$.MODULE$.LOST();
               if (state$1 == null) {
                  if (var11 != null) {
                     break label63;
                  }
               } else if (!state$1.equals(var11)) {
                  break label63;
               }

               String execId = (String)this.taskIdToExecutorId().getOrElse(BoxesRunTime.boxToLong(tid$2), () -> {
                  String errorMsg = "taskIdToTaskSetManager.contains(tid) <=> taskIdToExecutorId.contains(tid)";
                  taskSet.abort(errorMsg, taskSet.abort$default$2());
                  throw new SparkException("taskIdToTaskSetManager.contains(tid) <=> taskIdToExecutorId.contains(tid)");
               });
               if (this.executorIdToRunningTaskIds().contains(execId)) {
                  reason$3.elem = new Some(new ExecutorProcessLost("Task " + tid$2 + " was lost, so marking the executor as lost as well.", ExecutorProcessLost$.MODULE$.apply$default$2(), ExecutorProcessLost$.MODULE$.apply$default$3()));
                  this.removeExecutor(execId, (ExecutorLossReason)((Option)reason$3.elem).get());
                  failedExecutor$1.elem = new Some(execId);
               }
            }

            if (TaskState$.MODULE$.isFinished(state$1)) {
               label70: {
                  label55: {
                     this.cleanupTaskState(tid$2);
                     taskSet.removeRunningTask(tid$2);
                     Enumeration.Value var13 = TaskState$.MODULE$.FINISHED();
                     if (state$1 == null) {
                        if (var13 == null) {
                           break label55;
                        }
                     } else if (state$1.equals(var13)) {
                        break label55;
                     }

                     if (((SetOps)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Enumeration.Value[]{TaskState$.MODULE$.FAILED(), TaskState$.MODULE$.KILLED(), TaskState$.MODULE$.LOST()})))).contains(state$1)) {
                        this.taskResultGetter().enqueueFailedTask(taskSet, tid$2, state$1, serializedData$1);
                     }
                     break label70;
                  }

                  this.taskResultGetter().enqueueSuccessfulTask(taskSet, tid$2, serializedData$1);
               }
            }

            label75: {
               Enumeration.Value var14 = TaskState$.MODULE$.RUNNING();
               if (state$1 == null) {
                  if (var14 == null) {
                     break label75;
                  }
               } else if (state$1.equals(var14)) {
                  break label75;
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
               return;
            }

            ((TaskInfo)taskSet.taskInfos().apply(BoxesRunTime.boxToLong(tid$2))).launchSucceeded();
            BoxedUnit var17 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(var8)) {
               throw new MatchError(var8);
            }

            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring update with state ", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_STATE..MODULE$, state$1)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"TID ", " because its task set is gone (this is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(tid$2))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"likely the result of receiving duplicate task finished status updates) or its "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor has been marked as failed."})))).log(scala.collection.immutable.Nil..MODULE$))));
            BoxedUnit var18 = BoxedUnit.UNIT;
         }
      } catch (Exception var16) {
         this.logError((Function0)(() -> "Exception in statusUpdate"), var16);
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$executorHeartbeatReceived$3(final long id$1, final double taskProcessRate$1, final TaskSetManager.TaskProcessRateCalculator x$15) {
      x$15.updateRunningTaskProcessRate(id$1, taskProcessRate$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handlePartitionCompleted$2(final TaskSetManager x$17) {
      return !x$17.isZombie();
   }

   // $FF: synthetic method
   public static final void $anonfun$handlePartitionCompleted$3(final int partitionId$1, final TaskSetManager tsm) {
      tsm.markPartitionCompleted(partitionId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$handlePartitionCompleted$1(final int partitionId$1, final HashMap x$16) {
      ((IterableOnceOps)x$16.values().filter((x$17) -> BoxesRunTime.boxToBoolean($anonfun$handlePartitionCompleted$2(x$17)))).foreach((tsm) -> {
         $anonfun$handlePartitionCompleted$3(partitionId$1, tsm);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$error$2(final TaskSchedulerImpl $this, final String message$1, final TaskSetManager manager) {
      try {
         manager.abort(message$1, manager.abort$default$2());
      } catch (Exception var4) {
         $this.logError((Function0)(() -> "Exception in error callback"), var4);
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$error$1(final TaskSchedulerImpl $this, final String message$1, final HashMap attempts) {
      attempts.values().foreach((manager) -> {
         $anonfun$error$2($this, message$1, manager);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupTaskState$2(final long tid$3, final HashSet x$18) {
      return x$18.remove(BoxesRunTime.boxToLong(tid$3));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupTaskState$1(final TaskSchedulerImpl $this, final long tid$3, final String executorId) {
      $this.executorIdToRunningTaskIds().get(executorId).foreach((x$18) -> BoxesRunTime.boxToBoolean($anonfun$cleanupTaskState$2(tid$3, x$18)));
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutor$1(final TaskSchedulerImpl $this, final String executorId$5, final HashSet taskIds) {
      $this.logDebug((Function0)(() -> {
         String var10000 = taskIds.mkString("[", ",", "]");
         return "Cleaning up TaskScheduler state for tasks " + var10000 + " on failed executor " + executorId$5;
      }));
      taskIds.foreach((JFunction1.mcVJ.sp)(tid) -> $this.cleanupTaskState(tid));
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutor$5(final TaskSchedulerImpl $this, final String host$2, final String rack) {
      $this.hostsByRack().get(rack).foreach((hosts) -> {
         hosts.$minus$eq(host$2);
         return hosts.isEmpty() ? $this.hostsByRack().$minus$eq(rack) : BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutor$7(final TaskSchedulerImpl $this, final String executorId$5, final ExecutorDecommissionState x$19) {
      $this.executorsRemovedByDecom().put(executorId$5, x$19);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutor$8(final String executorId$5, final HealthTracker x$20) {
      x$20.handleRemovedExecutor(executorId$5);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getExecutorsAliveOnHost$2(final TaskSchedulerImpl $this, final String execId) {
      return $this.isExecutorDecommissioned(execId);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasExecutorsAliveOnHost$2(final TaskSchedulerImpl $this, final String e) {
      return !$this.isExecutorDecommissioned(e);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasExecutorsAliveOnHost$1(final TaskSchedulerImpl $this, final HashSet executors) {
      return executors.exists((e) -> BoxesRunTime.boxToBoolean($anonfun$hasExecutorsAliveOnHost$2($this, e)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasHostAliveOnRack$2(final TaskSchedulerImpl $this, final String h) {
      return !$this.isHostDecommissioned(h);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasHostAliveOnRack$1(final TaskSchedulerImpl $this, final HashSet hosts) {
      return hosts.exists((h) -> BoxesRunTime.boxToBoolean($anonfun$hasHostAliveOnRack$2($this, h)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isExecutorBusy$1(final HashSet x$23) {
      return x$23.nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isHostDecommissioned$3(final ExecutorDecommissionState x$24) {
      return x$24.workerHost().isDefined();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isHostDecommissioned$2(final TaskSchedulerImpl $this, final String e) {
      return $this.getExecutorDecommissionState(e).exists((x$24) -> BoxesRunTime.boxToBoolean($anonfun$isHostDecommissioned$3(x$24)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isHostDecommissioned$1(final TaskSchedulerImpl $this, final HashSet executors) {
      return executors.exists((e) -> BoxesRunTime.boxToBoolean($anonfun$isHostDecommissioned$2($this, e)));
   }

   public TaskSchedulerImpl(final SparkContext sc, final int maxTaskFailures, final boolean isLocal, final Clock clock) {
      this.sc = sc;
      this.maxTaskFailures = maxTaskFailures;
      this.isLocal = isLocal;
      this.org$apache$spark$scheduler$TaskSchedulerImpl$$clock = clock;
      TaskScheduler.$init$(this);
      Logging.$init$(this);
      this.conf = sc.conf();
      this.SPECULATION_INTERVAL_MS = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_INTERVAL()));
      this.MIN_TIME_TO_SPECULATION = BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_MIN_THRESHOLD()));
      this.efficientTaskCalcualtionEnabled = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_ENABLED())) && BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SPECULATION_EFFICIENCY_ENABLE()));
      this.speculationScheduler = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("task-scheduler-speculation");
      this.STARVATION_TIMEOUT_MS = this.conf().getTimeAsMs("spark.starvation.timeout", "15s");
      this.CPUS_PER_TASK = BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.CPUS_PER_TASK()));
      this.taskSetsByStageIdAndAttempt = new HashMap();
      this.noRejectsSinceLastReset = new HashMap();
      this.legacyLocalityWaitReset = BoxesRunTime.unboxToBoolean(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.LEGACY_LOCALITY_WAIT_RESET()));
      this.taskIdToTaskSetManager = new ConcurrentHashMap();
      this.taskIdToExecutorId = new HashMap();
      this.hasReceivedTask = false;
      this.org$apache$spark$scheduler$TaskSchedulerImpl$$hasLaunchedTask = false;
      this.starvationTimer = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("task-starvation-timer");
      this.nextTaskId = new AtomicLong(0L);
      this.executorIdToRunningTaskIds = new HashMap();
      this.executorsPendingDecommission = new HashMap();
      this.executorsRemovedByDecom = CacheBuilder.newBuilder().maximumSize((long)BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_MAX_RETAINED_REMOVED_EXECUTORS()))).build();
      this.hostToExecutors = new HashMap();
      this.hostsByRack = new HashMap();
      this.executorIdToHost = new HashMap();
      this.abortTimer = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("task-abort-timer");
      this.unschedulableTaskSetToExpiryTime = new HashMap();
      this.dagScheduler = null;
      this.backend = null;
      this.mapOutputTracker = (MapOutputTrackerMaster)SparkEnv$.MODULE$.get().mapOutputTracker();
      this.schedulableBuilder = null;
      this.schedulingModeConf = (String)this.conf().get(org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_MODE());
      this.schedulingMode = this.liftedTree1$1();
      this.rootPool = new Pool("", this.schedulingMode(), 0, 0);
      this.taskResultGetter = new TaskResultGetter(sc.env(), this);
      this.barrierCoordinator = null;
      this.defaultRackValue = scala.None..MODULE$;
      Statics.releaseFence();
   }

   public TaskSchedulerImpl(final SparkContext sc) {
      this(sc, BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.TASK_MAX_FAILURES())), TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$3(), TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$4());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
