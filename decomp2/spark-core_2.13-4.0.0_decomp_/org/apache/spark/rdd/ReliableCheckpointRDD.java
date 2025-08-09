package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.util.SerializableConfiguration;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001df!B\u0014)\u0001)\u0002\u0004\u0002C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002$\t\u0011)\u0003!Q1A\u0005\u0002-C\u0001b\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t1\u0002\u0011\t\u0011)A\u00053\"Aq\f\u0001B\u0002B\u0003-\u0001\rC\u0003g\u0001\u0011\u0005q\rC\u0004o\u0001\t\u0007I\u0011B8\t\ra\u0004\u0001\u0015!\u0003q\u0011\u001di\bA1A\u0005\nyDq!a\u0003\u0001A\u0003%q\u0010C\u0005\u0002\u0006\u0001\u0011\r\u0011\"\u0003\u0002\u0010!A\u0011q\u0003\u0001!\u0002\u0013\t\t\u0002C\u0005\u0002\u001c\u0001\u0011\r\u0011\"\u0003\u0002\u001e!A\u0011q\u0007\u0001!\u0002\u0013\ty\u0002C\u0005\u0002:\u0001\u0011\r\u0011\"\u0011\u0002<!A\u0011q\b\u0001!\u0002\u0013\ti\u0004C\u0005\u0002B\u0001\u0011\r\u0011\"\u0011\u0002D!9\u0011Q\t\u0001!\u0002\u0013I\u0006bBA$\u0001\u0011E\u0013\u0011\n\u0005\f\u0003/\u0002\u0001R1A\u0005\u0002)\nI\u0006C\u0004\u0002\b\u0002!I!!#\t\u0015\u0005=\u0005\u0001#b\u0001\n\u0013\t\t\nC\u0004\u0002\u001c\u0002!\t&!(\t\u000f\u0005\u0005\u0006\u0001\"\u0011\u0002$\u001eA\u0011q\u0017\u0015\t\u0002)\nILB\u0004(Q!\u0005!&a/\t\r\u0019TB\u0011AAp\u0011\u001d\t\tO\u0007C\u0005\u0003GDq!a<\u001b\t\u0013\t\t\u0010C\u0004\u0002tj!\t!!>\t\u0013\te!$%A\u0005\u0002\tm\u0001b\u0002B\u001b5\u0011\u0005!q\u0007\u0005\n\u0005CR\u0012\u0013!C\u0001\u0005GBqAa\u001a\u001b\t\u0013\u0011I\u0007C\u0004\u0003ti!IA!\u001e\t\u000f\tm$\u0004\"\u0001\u0003~!I!Q\u0012\u000e\u0012\u0002\u0013\u0005!q\u0012\u0005\n\u0005/S\u0012\u0011!C\u0005\u00053\u0013QCU3mS\u0006\u0014G.Z\"iK\u000e\\\u0007o\\5oiJ#EI\u0003\u0002*U\u0005\u0019!\u000f\u001a3\u000b\u0005-b\u0013!B:qCJ\\'BA\u0017/\u0003\u0019\t\u0007/Y2iK*\tq&A\u0002pe\u001e,\"!\r\u001d\u0014\u0005\u0001\u0011\u0004cA\u001a5m5\t\u0001&\u0003\u00026Q\ti1\t[3dWB|\u0017N\u001c;S\t\u0012\u0003\"a\u000e\u001d\r\u0001\u0011)\u0011\b\u0001b\u0001w\t\tAk\u0001\u0001\u0012\u0005q\u0012\u0005CA\u001fA\u001b\u0005q$\"A \u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005s$a\u0002(pi\"Lgn\u001a\t\u0003{\rK!\u0001\u0012 \u0003\u0007\u0005s\u00170\u0001\u0002tGB\u0011q\tS\u0007\u0002U%\u0011\u0011J\u000b\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u000fG\",7m\u001b9pS:$\b+\u0019;i+\u0005a\u0005CA'U\u001d\tq%\u000b\u0005\u0002P}5\t\u0001K\u0003\u0002Ru\u00051AH]8pizJ!a\u0015 \u0002\rA\u0013X\rZ3g\u0013\t)fK\u0001\u0004TiJLgn\u001a\u0006\u0003'z\nqb\u00195fG.\u0004x.\u001b8u!\u0006$\b\u000eI\u0001\r?B\f'\u000f^5uS>tWM\u001d\t\u0004{ic\u0016BA.?\u0005\u0019y\u0005\u000f^5p]B\u0011q)X\u0005\u0003=*\u00121\u0002U1si&$\u0018n\u001c8fe\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u0005$g'D\u0001c\u0015\t\u0019g(A\u0004sK\u001adWm\u0019;\n\u0005\u0015\u0014'\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\u0011A7\u000e\\7\u0015\u0005%T\u0007cA\u001a\u0001m!)qL\u0002a\u0002A\")QI\u0002a\u0001\r\")!J\u0002a\u0001\u0019\"9\u0001L\u0002I\u0001\u0002\u0004I\u0016A\u00035bI>|\u0007oQ8oMV\t\u0001\u000f\u0005\u0002rm6\t!O\u0003\u0002ti\u0006!1m\u001c8g\u0015\t)H&\u0001\u0004iC\u0012|w\u000e]\u0005\u0003oJ\u0014QbQ8oM&<WO]1uS>t\u0017a\u00035bI>|\u0007oQ8oM\u0002B#\u0001\u0003>\u0011\u0005uZ\u0018B\u0001??\u0005%!(/\u00198tS\u0016tG/A\u0003da\u0006$\b.F\u0001\u0000!\u0011\t\t!a\u0002\u000e\u0005\u0005\r!bAA\u0003i\u0006\u0011am]\u0005\u0005\u0003\u0013\t\u0019A\u0001\u0003QCRD\u0017AB2qCRD\u0007\u0005\u000b\u0002\u000buV\u0011\u0011\u0011\u0003\t\u0005\u0003\u0003\t\u0019\"\u0003\u0003\u0002\u0016\u0005\r!A\u0003$jY\u0016\u001c\u0016p\u001d;f[\u0006\u0019am\u001d\u0011)\u00051Q\u0018a\u00042s_\u0006$7-Y:uK\u0012\u001cuN\u001c4\u0016\u0005\u0005}\u0001CBA\u0011\u0003O\tY#\u0004\u0002\u0002$)\u0019\u0011Q\u0005\u0016\u0002\u0013\t\u0014x.\u00193dCN$\u0018\u0002BA\u0015\u0003G\u0011\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u0011\t\u00055\u00121G\u0007\u0003\u0003_Q1!!\r+\u0003\u0011)H/\u001b7\n\t\u0005U\u0012q\u0006\u0002\u001a'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\"p]\u001aLw-\u001e:bi&|g.\u0001\tce>\fGmY1ti\u0016$7i\u001c8gA\u0005\tr-\u001a;DQ\u0016\u001c7\u000e]8j]R4\u0015\u000e\\3\u0016\u0005\u0005u\u0002cA\u001f[\u0019\u0006\u0011r-\u001a;DQ\u0016\u001c7\u000e]8j]R4\u0015\u000e\\3!\u0003-\u0001\u0018M\u001d;ji&|g.\u001a:\u0016\u0003e\u000bA\u0002]1si&$\u0018n\u001c8fe\u0002\nQbZ3u!\u0006\u0014H/\u001b;j_:\u001cXCAA&!\u0015i\u0014QJA)\u0013\r\tyE\u0010\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\u000f\u0006M\u0013bAA+U\tI\u0001+\u0019:uSRLwN\\\u0001\u0019G\u0006\u001c\u0007.\u001a3Qe\u00164WM\u001d:fI2{7-\u0019;j_:\u001cXCAA.!!\ti&a\u001c\u0002R\u0005MTBAA0\u0015\u0011\t\t'a\u0019\u0002\u000b\r\f7\r[3\u000b\t\u0005\u0015\u0014qM\u0001\u0007G>lWn\u001c8\u000b\t\u0005%\u00141N\u0001\u0007O>|w\r\\3\u000b\u0005\u00055\u0014aA2p[&!\u0011\u0011OA0\u00051au.\u00193j]\u001e\u001c\u0015m\u00195f!\u0015\t)(a M\u001d\u0011\t9(a\u001f\u000f\u0007=\u000bI(C\u0001@\u0013\r\tiHP\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t)a!\u0003\u0007M+\u0017OC\u0002\u0002~yB#\u0001\u0006>\u00025\u001d,G\u000fU1si&$\u0018n\u001c8CY>\u001c7\u000eT8dCRLwN\\:\u0015\t\u0005M\u00141\u0012\u0005\b\u0003\u001b+\u0002\u0019AA)\u0003\u0015\u0019\b\u000f\\5u\u0003A\u0019\u0017m\u00195fI\u0016C\b/\u001b:f)&lW-\u0006\u0002\u0002\u0014B!QHWAK!\ri\u0014qS\u0005\u0004\u00033s$\u0001\u0002'p]\u001e\fQcZ3u!J,g-\u001a:sK\u0012dunY1uS>t7\u000f\u0006\u0003\u0002t\u0005}\u0005bBAG/\u0001\u0007\u0011\u0011K\u0001\bG>l\u0007/\u001e;f)\u0019\t)+a+\u0002.B)\u0011QOATm%!\u0011\u0011VAB\u0005!IE/\u001a:bi>\u0014\bbBAG1\u0001\u0007\u0011\u0011\u000b\u0005\b\u0003_C\u0002\u0019AAY\u0003\u001d\u0019wN\u001c;fqR\u00042aRAZ\u0013\r\t)L\u000b\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u000bSK2L\u0017M\u00197f\u0007\",7m\u001b9pS:$(\u000b\u0012#\u0011\u0005MR2c\u0002\u000e\u0002>\u0006\r\u0017q\u001a\t\u0004{\u0005}\u0016bAAa}\t1\u0011I\\=SK\u001a\u0004B!!2\u0002L6\u0011\u0011q\u0019\u0006\u0004\u0003\u0013T\u0013\u0001C5oi\u0016\u0014h.\u00197\n\t\u00055\u0017q\u0019\u0002\b\u0019><w-\u001b8h!\u0011\t\t.a7\u000e\u0005\u0005M'\u0002BAk\u0003/\f!![8\u000b\u0005\u0005e\u0017\u0001\u00026bm\u0006LA!!8\u0002T\na1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011X\u0001\u0013G\",7m\u001b9pS:$h)\u001b7f\u001d\u0006lW\rF\u0002M\u0003KDq!a:\u001d\u0001\u0004\tI/\u0001\bqCJ$\u0018\u000e^5p]&sG-\u001a=\u0011\u0007u\nY/C\u0002\u0002nz\u00121!\u00138u\u0003u\u0019\u0007.Z2la>Lg\u000e\u001e)beRLG/[8oKJ4\u0015\u000e\\3OC6,G#\u0001'\u0002;]\u0014\u0018\u000e^3S\t\u0012#vn\u00115fG.\u0004x.\u001b8u\t&\u0014Xm\u0019;pef,B!a>\u0002\u0000RA\u0011\u0011 B\u0004\u0005#\u0011)\u0002\u0006\u0003\u0002|\n\u0005\u0001\u0003B\u001a\u0001\u0003{\u00042aNA\u0000\t\u0015IdD1\u0001<\u0011%\u0011\u0019AHA\u0001\u0002\b\u0011)!\u0001\u0006fm&$WM\\2fII\u0002B!\u00193\u0002~\"9!\u0011\u0002\u0010A\u0002\t-\u0011aC8sS\u001eLg.\u00197S\t\u0012\u0003Ra\rB\u0007\u0003{L1Aa\u0004)\u0005\r\u0011F\t\u0012\u0005\u0007\u0005'q\u0002\u0019\u0001'\u0002\u001b\rDWmY6q_&tG\u000fR5s\u0011%\u00119B\bI\u0001\u0002\u0004\tI/A\u0005cY>\u001c7nU5{K\u00069sO]5uKJ#E\tV8DQ\u0016\u001c7\u000e]8j]R$\u0015N]3di>\u0014\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0011\u0011iBa\r\u0016\u0005\t}!\u0006BAu\u0005CY#Aa\t\u0011\t\t\u0015\"qF\u0007\u0003\u0005OQAA!\u000b\u0003,\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005[q\u0014AC1o]>$\u0018\r^5p]&!!\u0011\u0007B\u0014\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006s}\u0011\raO\u0001\u001foJLG/\u001a)beRLG/[8o)>\u001c\u0005.Z2la>Lg\u000e\u001e$jY\u0016,BA!\u000f\u0003NQA!1\bB-\u0005;\u0012y\u0006\u0006\u0004\u0003>\t=#1\u000b\u000b\u0005\u0005\u007f\u0011)\u0005E\u0002>\u0005\u0003J1Aa\u0011?\u0005\u0011)f.\u001b;\t\u0013\t\u001d\u0003%!AA\u0004\t%\u0013AC3wS\u0012,gnY3%gA!\u0011\r\u001aB&!\r9$Q\n\u0003\u0006s\u0001\u0012\ra\u000f\u0005\b\u0005#\u0002\u0003\u0019AAY\u0003\r\u0019G\u000f\u001f\u0005\b\u0005+\u0002\u0003\u0019\u0001B,\u0003!IG/\u001a:bi>\u0014\bCBA;\u0003O\u0013Y\u0005\u0003\u0004\u0003\\\u0001\u0002\r\u0001T\u0001\u0005a\u0006$\b\u000eC\u0004\u0002\u001c\u0001\u0002\r!a\b\t\u0013\t]\u0001\u0005%AA\u0002\u0005%\u0018\u0001K<sSR,\u0007+\u0019:uSRLwN\u001c+p\u0007\",7m\u001b9pS:$h)\u001b7fI\u0011,g-Y;mi\u0012\u001aT\u0003\u0002B\u000f\u0005K\"Q!O\u0011C\u0002m\nqd\u001e:ji\u0016\u0004\u0016M\u001d;ji&|g.\u001a:U_\u000eCWmY6q_&tG\u000fR5s)!\u0011yDa\u001b\u0003n\t=\u0004\"B##\u0001\u00041\u0005BBA!E\u0001\u0007A\f\u0003\u0004\u0003r\t\u0002\ra`\u0001\u0012G\",7m\u001b9pS:$H)\u001b:QCRD\u0017a\b:fC\u0012\u001c\u0005.Z2la>Lg\u000e^3e!\u0006\u0014H/\u001b;j_:,'OR5mKR)\u0011La\u001e\u0003z!)Qi\ta\u0001\r\"1!\u0011O\u0012A\u00021\u000b!C]3bI\u000eCWmY6q_&tGOR5mKV!!q\u0010BC)!\u0011\tIa\"\u0003\n\n-\u0005CBA;\u0003O\u0013\u0019\tE\u00028\u0005\u000b#Q!\u000f\u0013C\u0002mBaAa\u0017%\u0001\u0004y\bbBA\u000eI\u0001\u0007\u0011q\u0004\u0005\b\u0003_#\u0003\u0019AAY\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU!!\u0011\u0013BK+\t\u0011\u0019JK\u0002Z\u0005C!Q!O\u0013C\u0002m\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa'\u0011\t\tu%1U\u0007\u0003\u0005?SAA!)\u0002X\u0006!A.\u00198h\u0013\u0011\u0011)Ka(\u0003\r=\u0013'.Z2u\u0001"
)
public class ReliableCheckpointRDD extends CheckpointRDD {
   private transient LoadingCache cachedPreferredLocations;
   private Option cachedExpireTime;
   private final String checkpointPath;
   private final transient Configuration hadoopConf;
   private final transient Path cpath;
   private final transient FileSystem fs;
   private final Broadcast broadcastedConf;
   private final Option getCheckpointFile;
   private final Option partitioner;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static Option $lessinit$greater$default$3() {
      return ReliableCheckpointRDD$.MODULE$.$lessinit$greater$default$3();
   }

   public static Iterator readCheckpointFile(final Path path, final Broadcast broadcastedConf, final TaskContext context) {
      return ReliableCheckpointRDD$.MODULE$.readCheckpointFile(path, broadcastedConf, context);
   }

   public static int writePartitionToCheckpointFile$default$3() {
      return ReliableCheckpointRDD$.MODULE$.writePartitionToCheckpointFile$default$3();
   }

   public static void writePartitionToCheckpointFile(final String path, final Broadcast broadcastedConf, final int blockSize, final TaskContext ctx, final Iterator iterator, final ClassTag evidence$3) {
      ReliableCheckpointRDD$.MODULE$.writePartitionToCheckpointFile(path, broadcastedConf, blockSize, ctx, iterator, evidence$3);
   }

   public static int writeRDDToCheckpointDirectory$default$3() {
      return ReliableCheckpointRDD$.MODULE$.writeRDDToCheckpointDirectory$default$3();
   }

   public static ReliableCheckpointRDD writeRDDToCheckpointDirectory(final RDD originalRDD, final String checkpointDir, final int blockSize, final ClassTag evidence$2) {
      return ReliableCheckpointRDD$.MODULE$.writeRDDToCheckpointDirectory(originalRDD, checkpointDir, blockSize, evidence$2);
   }

   public String checkpointPath() {
      return this.checkpointPath;
   }

   private Configuration hadoopConf() {
      return this.hadoopConf;
   }

   private Path cpath() {
      return this.cpath;
   }

   private FileSystem fs() {
      return this.fs;
   }

   private Broadcast broadcastedConf() {
      return this.broadcastedConf;
   }

   public Option getCheckpointFile() {
      return this.getCheckpointFile;
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Partition[] getPartitions() {
      Path[] inputFiles = (Path[]).MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.fs().listStatus(this.cpath())), (x$1) -> x$1.getPath(), scala.reflect.ClassTag..MODULE$.apply(Path.class))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$2(x$2)))), (x$3) -> BoxesRunTime.boxToInteger($anonfun$getPartitions$3(x$3)), scala.math.Ordering.Int..MODULE$);
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputFiles))), (x0$1) -> {
         $anonfun$getPartitions$4(x0$1);
         return BoxedUnit.UNIT;
      });
      return (Partition[])scala.Array..MODULE$.tabulate(inputFiles.length, (i) -> $anonfun$getPartitions$5(BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   private LoadingCache cachedPreferredLocations$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.cachedPreferredLocations = CacheBuilder.newBuilder().expireAfterWrite(BoxesRunTime.unboxToLong(((Option)SparkEnv$.MODULE$.get().conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.CACHE_CHECKPOINT_PREFERRED_LOCS_EXPIRE_TIME())).get()), TimeUnit.MINUTES).build(new CacheLoader() {
               // $FF: synthetic field
               private final ReliableCheckpointRDD $outer;

               public Seq load(final Partition split) {
                  return this.$outer.org$apache$spark$rdd$ReliableCheckpointRDD$$getPartitionBlockLocations(split);
               }

               public {
                  if (ReliableCheckpointRDD.this == null) {
                     throw null;
                  } else {
                     this.$outer = ReliableCheckpointRDD.this;
                  }
               }
            });
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cachedPreferredLocations;
   }

   public LoadingCache cachedPreferredLocations() {
      return !this.bitmap$trans$0 ? this.cachedPreferredLocations$lzycompute() : this.cachedPreferredLocations;
   }

   public Seq org$apache$spark$rdd$ReliableCheckpointRDD$$getPartitionBlockLocations(final Partition split) {
      FileStatus status = this.fs().getFileStatus(new Path(this.checkpointPath(), ReliableCheckpointRDD$.MODULE$.org$apache$spark$rdd$ReliableCheckpointRDD$$checkpointFileName(split.index())));
      BlockLocation[] locations = this.fs().getFileBlockLocations(status, 0L, status.getLen());
      return .MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])locations)).toList().flatMap((x$4) -> scala.Predef..MODULE$.wrapRefArray((Object[])x$4.getHosts())).filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$getPartitionBlockLocations$2(x$5)));
   }

   private Option cachedExpireTime$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.cachedExpireTime = (Option)SparkEnv$.MODULE$.get().conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.CACHE_CHECKPOINT_PREFERRED_LOCS_EXPIRE_TIME());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cachedExpireTime;
   }

   private Option cachedExpireTime() {
      return !this.bitmap$0 ? this.cachedExpireTime$lzycompute() : this.cachedExpireTime;
   }

   public Seq getPreferredLocations(final Partition split) {
      return this.cachedExpireTime().isDefined() && BoxesRunTime.unboxToLong(this.cachedExpireTime().get()) > 0L ? (Seq)this.cachedPreferredLocations().get(split) : this.org$apache$spark$rdd$ReliableCheckpointRDD$$getPartitionBlockLocations(split);
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      Path file = new Path(this.checkpointPath(), ReliableCheckpointRDD$.MODULE$.org$apache$spark$rdd$ReliableCheckpointRDD$$checkpointFileName(split.index()));
      return ReliableCheckpointRDD$.MODULE$.readCheckpointFile(file, this.broadcastedConf(), context);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$2(final Path x$2) {
      return x$2.getName().startsWith("part-");
   }

   // $FF: synthetic method
   public static final int $anonfun$getPartitions$3(final Path x$3) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(x$3.getName()), "part-")));
   }

   // $FF: synthetic method
   public static final void $anonfun$getPartitions$4(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         Path path = (Path)x0$1._1();
         int i = x0$1._2$mcI$sp();
         String var10000 = path.getName();
         String var5 = ReliableCheckpointRDD$.MODULE$.org$apache$spark$rdd$ReliableCheckpointRDD$$checkpointFileName(i);
         if (var10000 == null) {
            if (var5 != null) {
               throw SparkCoreErrors$.MODULE$.invalidCheckpointFileError(path);
            }
         } else if (!var10000.equals(var5)) {
            throw SparkCoreErrors$.MODULE$.invalidCheckpointFileError(path);
         }

         BoxedUnit var6 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final CheckpointRDDPartition $anonfun$getPartitions$5(final int i) {
      return new CheckpointRDDPartition(i);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitionBlockLocations$2(final String x$5) {
      boolean var10000;
      label23: {
         String var1 = "localhost";
         if (x$5 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$5.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public ReliableCheckpointRDD(final SparkContext sc, final String checkpointPath, final Option _partitioner, final ClassTag evidence$1) {
      super(sc, evidence$1);
      this.checkpointPath = checkpointPath;
      this.hadoopConf = sc.hadoopConfiguration();
      this.cpath = new Path(checkpointPath);
      this.fs = this.cpath().getFileSystem(this.hadoopConf());
      this.broadcastedConf = sc.broadcast(new SerializableConfiguration(this.hadoopConf()), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      scala.Predef..MODULE$.require(this.fs().exists(this.cpath()), () -> "Checkpoint directory does not exist: " + this.checkpointPath());
      this.getCheckpointFile = new Some(checkpointPath);
      this.partitioner = _partitioner.orElse(() -> ReliableCheckpointRDD$.MODULE$.org$apache$spark$rdd$ReliableCheckpointRDD$$readCheckpointedPartitionerFile(this.context(), this.checkpointPath()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
