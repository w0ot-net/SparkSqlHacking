package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.ShuffleOutputStatus;
import org.roaringbitmap.RoaringBitmap;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t%fAB\u0014)\u0003\u0003Ac\u0006\u0003\u0005<\u0001\t\u0005\t\u0015!\u0003>\u0011\u0015\t\u0005\u0001\"\u0001C\u0011%)\u0005\u00011AA\u0002\u0013\u0005a\tC\u0005N\u0001\u0001\u0007\t\u0019!C\u0001\u001d\"IA\u000b\u0001a\u0001\u0002\u0003\u0006Ka\u0012\u0005\b+\u0002\u0001\r\u0011\"\u0005W\u0011\u001dQ\u0006\u00011A\u0005\u0012mCa!\u0018\u0001!B\u00139\u0006b\u00020\u0001\u0005\u0004%\tb\u0018\u0005\u0007Q\u0002\u0001\u000b\u0011\u00021\t\u000b%\u0004A\u0011\u00036\t\u000f\u0005\u0015\u0001\u0001\"\u0005\u0002\b!9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA0\u0001\u0011\u0005\u0011\u0011\r\u0005\b\u0003\u0017\u0001a\u0011AA7\u0011\u001d\ty\u0006\u0001D\u0001\u0003\u0003Cq!!$\u0001\r\u0003\ty\tC\u0004\u0002\u000e\u00021\t!a&\t\u000f\u00055\u0006A\"\u0001\u00020\"9\u0011q\u0017\u0001\u0007\u0002\u0005e\u0006bBA_\u0001\u0011\u0005\u0011qX\u0004\t\u0003\u0003D\u0003\u0012\u0001\u0015\u0002D\u001a9q\u0005\u000bE\u0001Q\u0005\u0015\u0007BB!\u0018\t\u0003\t9\rC\u0005\u0002J^\u0011\r\u0011\"\u0001\u0002L\"A\u00111[\f!\u0002\u0013\ti\rC\u0005\u0002V^\u0011\r\u0011\"\u0003\u0002X\"A\u0011\u0011\\\f!\u0002\u0013\t\t\u0006C\u0005\u0002\\^\u0011\r\u0011\"\u0003\u0002X\"A\u0011Q\\\f!\u0002\u0013\t\t\u0006C\u0005\u0002`^\u0011\r\u0011\"\u0001\u0002X\"A\u0011\u0011]\f!\u0002\u0013\t\t\u0006C\u0004\u0002d^!\t!!:\t\u000f\t]r\u0003\"\u0001\u0003:!9!\u0011J\f\u0005\u0002\t-\u0003\"\u0003B;/E\u0005I\u0011\u0001B<\u0011\u001d\u0011ii\u0006C\u0001\u0005\u001fCqAa'\u0018\t\u0003\u0011iJ\u0001\tNCB|U\u000f\u001e9viR\u0013\u0018mY6fe*\u0011\u0011FK\u0001\u0006gB\f'o\u001b\u0006\u0003W1\na!\u00199bG\",'\"A\u0017\u0002\u0007=\u0014xmE\u0002\u0001_U\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u0012a!\u00118z%\u00164\u0007C\u0001\u001c:\u001b\u00059$B\u0001\u001d)\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001e8\u0005\u001daunZ4j]\u001e\fAaY8oM\u000e\u0001\u0001C\u0001 @\u001b\u0005A\u0013B\u0001!)\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0007\u0012\u0003\"A\u0010\u0001\t\u000bm\u0012\u0001\u0019A\u001f\u0002\u001fQ\u0014\u0018mY6fe\u0016sG\r]8j]R,\u0012a\u0012\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015\"\n1A\u001d9d\u0013\ta\u0015J\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002'Q\u0014\u0018mY6fe\u0016sG\r]8j]R|F%Z9\u0015\u0005=\u0013\u0006C\u0001\u0019Q\u0013\t\t\u0016G\u0001\u0003V]&$\bbB*\u0005\u0003\u0003\u0005\raR\u0001\u0004q\u0012\n\u0014\u0001\u0005;sC\u000e\\WM]#oIB|\u0017N\u001c;!\u0003\u0015)\u0007o\\2i+\u00059\u0006C\u0001\u0019Y\u0013\tI\u0016G\u0001\u0003M_:<\u0017!C3q_\u000eDw\fJ3r)\tyE\fC\u0004T\u000f\u0005\u0005\t\u0019A,\u0002\r\u0015\u0004xn\u00195!\u0003%)\u0007o\\2i\u0019>\u001c7.F\u0001a!\t\tg-D\u0001c\u0015\t\u0019G-\u0001\u0003mC:<'\"A3\u0002\t)\fg/Y\u0005\u0003O\n\u0014aa\u00142kK\u000e$\u0018AC3q_\u000eDGj\\2lA\u0005Q\u0011m]6Ue\u0006\u001c7.\u001a:\u0016\u0005-|Gc\u00017\u0002\u0002Q\u0011Q\u000e\u001f\t\u0003]>d\u0001\u0001B\u0003q\u0017\t\u0007\u0011OA\u0001U#\t\u0011X\u000f\u0005\u00021g&\u0011A/\r\u0002\b\u001d>$\b.\u001b8h!\t\u0001d/\u0003\u0002xc\t\u0019\u0011I\\=\t\u000fe\\\u0011\u0011!a\u0002u\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007mtX.D\u0001}\u0015\ti\u0018'A\u0004sK\u001adWm\u0019;\n\u0005}d(\u0001C\"mCN\u001cH+Y4\t\r\u0005\r1\u00021\u0001v\u0003\u001diWm]:bO\u0016\f1b]3oIR\u0013\u0018mY6feR\u0019q*!\u0003\t\r\u0005\rA\u00021\u0001v\u0003]9W\r^'baNK'0Z:Cs\u0016CXmY;u_JLE\r\u0006\u0004\u0002\u0010\u0005]\u00131\f\t\u0007\u0003#\t\t#a\n\u000f\t\u0005M\u0011Q\u0004\b\u0005\u0003+\tY\"\u0004\u0002\u0002\u0018)\u0019\u0011\u0011\u0004\u001f\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0014bAA\u0010c\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0012\u0003K\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003?\t\u0004c\u0002\u0019\u0002*\u00055\u0012\u0011H\u0005\u0004\u0003W\t$A\u0002+va2,'\u0007\u0005\u0003\u00020\u0005URBAA\u0019\u0015\r\t\u0019\u0004K\u0001\bgR|'/Y4f\u0013\u0011\t9$!\r\u0003\u001d\tcwnY6NC:\fw-\u001a:JIB1\u00111HA!\u0003\u000bj!!!\u0010\u000b\u0007\u0005}\u0012'\u0001\u0006d_2dWm\u0019;j_:LA!a\u0011\u0002>\t\u00191+Z9\u0011\u0011A\n9%a\u0013X\u0003#J1!!\u00132\u0005\u0019!V\u000f\u001d7fgA!\u0011qFA'\u0013\u0011\ty%!\r\u0003\u000f\tcwnY6JIB\u0019\u0001'a\u0015\n\u0007\u0005U\u0013GA\u0002J]RDq!!\u0017\u000e\u0001\u0004\t\t&A\u0005tQV4g\r\\3JI\"9\u0011QL\u0007A\u0002\u0005E\u0013\u0001\u0003:fIV\u001cW-\u00133\u0002O\u001d,G\u000fU;tQ\n\u000b7/\u001a3TQV4g\r\\3NCB\u001c\u0016N_3t\u0005f,\u00050Z2vi>\u0014\u0018\n\u001a\u000b\u0007\u0003G\nI'a\u001b\u0011\u0007y\n)'C\u0002\u0002h!\u0012A#T1q'&TXm\u001d\"z\u000bb,7-\u001e;pe&#\u0007bBA-\u001d\u0001\u0007\u0011\u0011\u000b\u0005\b\u0003;r\u0001\u0019AA))1\ty!a\u001c\u0002r\u0005U\u0014\u0011PA?\u0011\u001d\tIf\u0004a\u0001\u0003#Bq!a\u001d\u0010\u0001\u0004\t\t&A\u0007ti\u0006\u0014H/T1q\u0013:$W\r\u001f\u0005\b\u0003oz\u0001\u0019AA)\u0003-)g\u000eZ'ba&sG-\u001a=\t\u000f\u0005mt\u00021\u0001\u0002R\u0005q1\u000f^1siB\u000b'\u000f^5uS>t\u0007bBA@\u001f\u0001\u0007\u0011\u0011K\u0001\rK:$\u0007+\u0019:uSRLwN\u001c\u000b\r\u0003G\n\u0019)!\"\u0002\b\u0006%\u00151\u0012\u0005\b\u00033\u0002\u0002\u0019AA)\u0011\u001d\t\u0019\b\u0005a\u0001\u0003#Bq!a\u001e\u0011\u0001\u0004\t\t\u0006C\u0004\u0002|A\u0001\r!!\u0015\t\u000f\u0005}\u0004\u00031\u0001\u0002R\u0005Ir-\u001a;NCB\u001c\u0016N_3t\r>\u0014X*\u001a:hKJ+7/\u001e7u)\u0019\ty!!%\u0002\u0014\"9\u0011\u0011L\tA\u0002\u0005E\u0003bBAK#\u0001\u0007\u0011\u0011K\u0001\fa\u0006\u0014H/\u001b;j_:LE\r\u0006\u0005\u0002\u0010\u0005e\u00151TAO\u0011\u001d\tIF\u0005a\u0001\u0003#Bq!!&\u0013\u0001\u0004\t\t\u0006C\u0004\u0002 J\u0001\r!!)\u0002\u0017\rDWO\\6CSRl\u0017\r\u001d\t\u0005\u0003G\u000bI+\u0004\u0002\u0002&*\u0019\u0011q\u0015\u0017\u0002\u001bI|\u0017M]5oO\nLG/\\1q\u0013\u0011\tY+!*\u0003\u001bI{\u0017M]5oO\nKG/\\1q\u0003u9W\r^*ik\u001a4G.\u001a)vg\"lUM]4fe2{7-\u0019;j_:\u001cH\u0003BAY\u0003k\u0003b!!\u0005\u00024\u00065\u0012\u0002BA\"\u0003KAq!!\u0017\u0014\u0001\u0004\t\t&A\tv]J,w-[:uKJ\u001c\u0006.\u001e4gY\u0016$2aTA^\u0011\u001d\tI\u0006\u0006a\u0001\u0003#\nAa\u001d;paR\tq*\u0001\tNCB|U\u000f\u001e9viR\u0013\u0018mY6feB\u0011ahF\n\u0004/=*DCAAb\u00035)e\n\u0012)P\u0013:#vLT!N\u000bV\u0011\u0011Q\u001a\t\u0004C\u0006=\u0017bAAiE\n11\u000b\u001e:j]\u001e\fa\"\u0012(E!>Ke\nV0O\u00036+\u0005%\u0001\u0004E\u0013J+5\tV\u000b\u0003\u0003#\nq\u0001R%S\u000b\u000e#\u0006%A\u0005C%>\u000bEiQ!T)\u0006Q!IU(B\t\u000e\u000b5\u000b\u0016\u0011\u0002'MCUK\u0012$M\u000b~\u0003Vk\u0015%`\u001b\u0006\u0003v,\u0013#\u0002)MCUK\u0012$M\u000b~\u0003Vk\u0015%`\u001b\u0006\u0003v,\u0013#!\u0003]\u0019XM]5bY&TXmT;uaV$8\u000b^1ukN,7/\u0006\u0003\u0002h\n5A\u0003DAu\u0005\u000b\u0011iBa\n\u00032\tU\u0002c\u0002\u0019\u0002*\u0005-\u0018q\u001f\t\u0006a\u00055\u0018\u0011_\u0005\u0004\u0003_\f$!B!se\u0006L\bc\u0001\u0019\u0002t&\u0019\u0011Q_\u0019\u0003\t\tKH/\u001a\t\u0007\u0003s\fyPa\u0001\u000e\u0005\u0005m(bAA\u007fQ\u0005I!M]8bI\u000e\f7\u000f^\u0005\u0005\u0005\u0003\tYPA\u0005Ce>\fGmY1tiB)\u0001'!<\u0002l\"9!qA\u0011A\u0002\t%\u0011\u0001C:uCR,8/Z:\u0011\u000bA\niOa\u0003\u0011\u00079\u0014i\u0001\u0002\u0004qC\t\u0007!qB\t\u0004e\nE\u0001\u0003\u0002B\n\u00053i!A!\u0006\u000b\u0007\t]\u0001&A\u0005tG\",G-\u001e7fe&!!1\u0004B\u000b\u0005M\u0019\u0006.\u001e4gY\u0016|U\u000f\u001e9viN#\u0018\r^;t\u0011\u001d\u0011y\"\ta\u0001\u0005C\t\u0001C\u0019:pC\u0012\u001c\u0017m\u001d;NC:\fw-\u001a:\u0011\t\u0005e(1E\u0005\u0005\u0005K\tYP\u0001\tCe>\fGmY1ti6\u000bg.Y4fe\"9!\u0011F\u0011A\u0002\t-\u0012aB5t\u0019>\u001c\u0017\r\u001c\t\u0004a\t5\u0012b\u0001B\u0018c\t9!i\\8mK\u0006t\u0007b\u0002B\u001aC\u0001\u0007\u0011\u0011K\u0001\u0011[&t'I]8bI\u000e\f7\u000f^*ju\u0016DQaO\u0011A\u0002u\n\u0011\u0004Z3tKJL\u0017\r\\5{K>+H\u000f];u'R\fG/^:fgV!!1\bB!)\u0019\u0011iDa\u0011\u0003HA)\u0001'!<\u0003@A\u0019aN!\u0011\u0005\rA\u0014#\u0019\u0001B\b\u0011\u001d\u0011)E\ta\u0001\u0003W\fQAY=uKNDQa\u000f\u0012A\u0002u\n!cY8om\u0016\u0014H/T1q'R\fG/^:fgR\u0001\u00121\rB'\u0005\u001f\u0012\tFa\u0015\u0003`\t\u0005$1\r\u0005\b\u00033\u001a\u0003\u0019AA)\u0011\u001d\tYh\ta\u0001\u0003#Bq!a $\u0001\u0004\t\t\u0006C\u0004\u0003V\r\u0002\rAa\u0016\u0002\u00175\f\u0007o\u0015;biV\u001cXm\u001d\t\u0006a\u00055(\u0011\f\t\u0005\u0005'\u0011Y&\u0003\u0003\u0003^\tU!!C'baN#\u0018\r^;t\u0011\u001d\t\u0019h\ta\u0001\u0003#Bq!a\u001e$\u0001\u0004\t\t\u0006C\u0005\u0003f\r\u0002\n\u00111\u0001\u0003h\u0005\u0001R.\u001a:hKN#\u0018\r^;tKN|\u0005\u000f\u001e\t\u0006a\t%$QN\u0005\u0004\u0005W\n$AB(qi&|g\u000eE\u00031\u0003[\u0014y\u0007\u0005\u0003\u0003\u0014\tE\u0014\u0002\u0002B:\u0005+\u00111\"T3sO\u0016\u001cF/\u0019;vg\u0006a2m\u001c8wKJ$X*\u00199Ti\u0006$Xo]3tI\u0011,g-Y;mi\u0012:TC\u0001B=U\u0011\u00119Ga\u001f,\u0005\tu\u0004\u0003\u0002B@\u0005\u0013k!A!!\u000b\t\t\r%QQ\u0001\nk:\u001c\u0007.Z2lK\u0012T1Aa\"2\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005\u0017\u0013\tIA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAdZ3u\u001b\u0006\u00048\u000b^1ukN,7OR8s\u001b\u0016\u0014x-Z*uCR,8\u000f\u0006\u0006\u0002\u0010\tE%1\u0013BK\u0005/Cq!!\u0017&\u0001\u0004\t\t\u0006C\u0004\u0002\u0016\u0016\u0002\r!!\u0015\t\u000f\tUS\u00051\u0001\u0003X!9!\u0011T\u0013A\u0002\u0005\u0005\u0016a\u0002;sC\u000e\\WM]\u0001\u000fm\u0006d\u0017\u000eZ1uKN#\u0018\r^;t)\u001dy%q\u0014BR\u0005KCqA!)'\u0001\u0004\u0011\t\"\u0001\u0004ti\u0006$Xo\u001d\u0005\b\u000332\u0003\u0019AA)\u0011\u001d\u00119K\na\u0001\u0003#\n\u0011\u0002]1si&$\u0018n\u001c8"
)
public abstract class MapOutputTracker implements Logging {
   private RpcEndpointRef trackerEndpoint;
   private long epoch;
   private final Object epochLock;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void validateStatus(final ShuffleOutputStatus status, final int shuffleId, final int partition) {
      MapOutputTracker$.MODULE$.validateStatus(status, shuffleId, partition);
   }

   public static Iterator getMapStatusesForMergeStatus(final int shuffleId, final int partitionId, final MapStatus[] mapStatuses, final RoaringBitmap tracker) {
      return MapOutputTracker$.MODULE$.getMapStatusesForMergeStatus(shuffleId, partitionId, mapStatuses, tracker);
   }

   public static Option convertMapStatuses$default$7() {
      return MapOutputTracker$.MODULE$.convertMapStatuses$default$7();
   }

   public static MapSizesByExecutorId convertMapStatuses(final int shuffleId, final int startPartition, final int endPartition, final MapStatus[] mapStatuses, final int startMapIndex, final int endMapIndex, final Option mergeStatusesOpt) {
      return MapOutputTracker$.MODULE$.convertMapStatuses(shuffleId, startPartition, endPartition, mapStatuses, startMapIndex, endMapIndex, mergeStatusesOpt);
   }

   public static ShuffleOutputStatus[] deserializeOutputStatuses(final byte[] bytes, final SparkConf conf) {
      return MapOutputTracker$.MODULE$.deserializeOutputStatuses(bytes, conf);
   }

   public static Tuple2 serializeOutputStatuses(final ShuffleOutputStatus[] statuses, final BroadcastManager broadcastManager, final boolean isLocal, final int minBroadcastSize, final SparkConf conf) {
      return MapOutputTracker$.MODULE$.serializeOutputStatuses(statuses, broadcastManager, isLocal, minBroadcastSize, conf);
   }

   public static int SHUFFLE_PUSH_MAP_ID() {
      return MapOutputTracker$.MODULE$.SHUFFLE_PUSH_MAP_ID();
   }

   public static String ENDPOINT_NAME() {
      return MapOutputTracker$.MODULE$.ENDPOINT_NAME();
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

   public RpcEndpointRef trackerEndpoint() {
      return this.trackerEndpoint;
   }

   public void trackerEndpoint_$eq(final RpcEndpointRef x$1) {
      this.trackerEndpoint = x$1;
   }

   public long epoch() {
      return this.epoch;
   }

   public void epoch_$eq(final long x$1) {
      this.epoch = x$1;
   }

   public Object epochLock() {
      return this.epochLock;
   }

   public Object askTracker(final Object message, final ClassTag evidence$1) {
      try {
         return this.trackerEndpoint().askSync(message, evidence$1);
      } catch (Exception var4) {
         this.logError((Function0)(() -> "Error communicating with MapOutputTracker"), var4);
         throw new SparkException("Error communicating with MapOutputTracker", var4);
      }
   }

   public void sendTracker(final Object message) {
      boolean response = BoxesRunTime.unboxToBoolean(this.askTracker(message, .MODULE$.Boolean()));
      if (!response) {
         throw new SparkException("Error reply received from MapOutputTracker. Expecting true, got " + Boolean.toString(response));
      }
   }

   public Iterator getMapSizesByExecutorId(final int shuffleId, final int reduceId) {
      return this.getMapSizesByExecutorId(shuffleId, 0, Integer.MAX_VALUE, reduceId, reduceId + 1);
   }

   public MapSizesByExecutorId getPushBasedShuffleMapSizesByExecutorId(final int shuffleId, final int reduceId) {
      return this.getPushBasedShuffleMapSizesByExecutorId(shuffleId, 0, Integer.MAX_VALUE, reduceId, reduceId + 1);
   }

   public abstract Iterator getMapSizesByExecutorId(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition);

   public abstract MapSizesByExecutorId getPushBasedShuffleMapSizesByExecutorId(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition);

   public abstract Iterator getMapSizesForMergeResult(final int shuffleId, final int partitionId);

   public abstract Iterator getMapSizesForMergeResult(final int shuffleId, final int partitionId, final RoaringBitmap chunkBitmap);

   public abstract Seq getShufflePushMergerLocations(final int shuffleId);

   public abstract void unregisterShuffle(final int shuffleId);

   public void stop() {
   }

   public MapOutputTracker(final SparkConf conf) {
      Logging.$init$(this);
      this.epoch = 0L;
      this.epochLock = new Object();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
