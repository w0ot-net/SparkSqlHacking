package org.apache.spark.streaming.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Time;
import scala.Function4;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tmb!B\u0010!\u0001\tR\u0003\u0002\u0003&\u0001\u0005\u0003\u0007I\u0011B&\t\u00111\u0003!\u00111A\u0005\n5C\u0001b\u0015\u0001\u0003\u0002\u0003\u0006K\u0001\f\u0005\t)\u0002\u0011\t\u0019!C\u0005+\"AQ\f\u0001BA\u0002\u0013%a\f\u0003\u0005a\u0001\t\u0005\t\u0015)\u0003W\u0011!\t\u0007A!A!\u0002\u0013\u0011\u0007\u0002\u00039\u0001\u0005\u0003\u0005\u000b\u0011B3\t\u0011E\u0004!\u0011!Q\u0001\nID\u0001B\u001e\u0001\u0003\u0004\u0003\u0006Ya\u001e\u0005\t{\u0002\u0011\u0019\u0011)A\u0006}\"Iq\u0010\u0001B\u0002B\u0003-\u0011\u0011\u0001\u0005\u000b\u0003\u0007\u0001!1!Q\u0001\f\u0005\u0015\u0001bBA\u0004\u0001\u0011\u0005\u0011\u0011\u0002\u0005\n\u0003C\u0001\u0001\u0019!C\u0005\u0003GA\u0011\"a\u000b\u0001\u0001\u0004%I!!\f\t\u0011\u0005E\u0002\u0001)Q\u0005\u0003KA\u0011\"a\u000f\u0001\u0005\u0004%\t%!\u0010\t\u0011\u0005%\u0003\u0001)A\u0005\u0003\u007fAq!a\u0013\u0001\t\u0003\ni\u0005C\u0004\u0002P\u0001!\t%!\u0015\t\u000f\u0005}\u0004\u0001\"\u0015\u0002\u0002\"9\u0011\u0011\u0012\u0001\u0005B\u00055\u0003bBAF\u0001\u0011\u0005\u0011QJ\u0004\t\u0003\u001b\u0003\u0003\u0012\u0001\u0012\u0002\u0010\u001a9q\u0004\tE\u0001E\u0005E\u0005bBA\u00045\u0011\u0005\u0011\u0011\u0016\u0005\b\u0003WSB\u0011AAW\u0011\u001d\tYO\u0007C\u0001\u0003[D\u0011Ba\u000b\u001b\u0003\u0003%IA!\f\u0003\u001f5\u000b\u0007oV5uQN#\u0018\r^3S\t\u0012S!!\t\u0012\u0002\u0007I$GM\u0003\u0002$I\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003K\u0019\nQa\u001d9be.T!a\n\u0015\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0013aA8sOV)1fN.F\u0011N\u0011\u0001\u0001\f\t\u0004[=\nT\"\u0001\u0018\u000b\u0005\u0005\"\u0013B\u0001\u0019/\u0005\r\u0011F\t\u0012\t\u0006eM*DiR\u0007\u0002A%\u0011A\u0007\t\u0002\u0016\u001b\u0006\u0004x+\u001b;i'R\fG/\u001a*E\tJ+7m\u001c:e!\t1t\u0007\u0004\u0001\u0005\u000ba\u0002!\u0019\u0001\u001e\u0003\u0003-\u001b\u0001!\u0005\u0002<\u0003B\u0011AhP\u0007\u0002{)\ta(A\u0003tG\u0006d\u0017-\u0003\u0002A{\t9aj\u001c;iS:<\u0007C\u0001\u001fC\u0013\t\u0019UHA\u0002B]f\u0004\"AN#\u0005\u000b\u0019\u0003!\u0019\u0001\u001e\u0003\u0003M\u0003\"A\u000e%\u0005\u000b%\u0003!\u0019\u0001\u001e\u0003\u0003\u0015\u000bA\u0002\u001d:fmN#\u0018\r^3S\t\u0012+\u0012\u0001L\u0001\u0011aJ,go\u0015;bi\u0016\u0014F\tR0%KF$\"AT)\u0011\u0005qz\u0015B\u0001)>\u0005\u0011)f.\u001b;\t\u000fI\u0013\u0011\u0011!a\u0001Y\u0005\u0019\u0001\u0010J\u0019\u0002\u001bA\u0014XM^*uCR,'\u000b\u0012#!\u0003I\u0001\u0018M\u001d;ji&|g.\u001a3ECR\f'\u000b\u0012#\u0016\u0003Y\u00032!L\u0018X!\u0011a\u0004,\u000e.\n\u0005ek$A\u0002+va2,'\u0007\u0005\u000277\u0012)A\f\u0001b\u0001u\t\ta+\u0001\fqCJ$\u0018\u000e^5p]\u0016$G)\u0019;b%\u0012#u\fJ3r)\tqu\fC\u0004S\u000b\u0005\u0005\t\u0019\u0001,\u0002'A\f'\u000f^5uS>tW\r\u001a#bi\u0006\u0014F\t\u0012\u0011\u0002\u001f5\f\u0007\u000f]5oO\u001a+hn\u0019;j_:\u0004r\u0001P2fk%dw.\u0003\u0002e{\tIa)\u001e8di&|g\u000e\u000e\t\u0003M\u001el\u0011AI\u0005\u0003Q\n\u0012A\u0001V5nKB\u0019AH\u001b.\n\u0005-l$AB(qi&|g\u000eE\u0002g[\u0012K!A\u001c\u0012\u0003\u000bM#\u0018\r^3\u0011\u0007qRw)A\u0005cCR\u001c\u0007\u000eV5nK\u0006!B/[7f_V$H\u000b\u001b:fg\"|G\u000e\u001a+j[\u0016\u00042\u0001\u00106t!\taD/\u0003\u0002v{\t!Aj\u001c8h\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0004qn,T\"A=\u000b\u0005il\u0014a\u0002:fM2,7\r^\u0005\u0003yf\u0014\u0001b\u00117bgN$\u0016mZ\u0001\u000bKZLG-\u001a8dK\u00122\u0004c\u0001=|5\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\u0007a\\H)\u0001\u0006fm&$WM\\2fIa\u00022\u0001_>H\u0003\u0019a\u0014N\\5u}Qa\u00111BA\f\u00033\tY\"!\b\u0002 QQ\u0011QBA\b\u0003#\t\u0019\"!\u0006\u0011\rI\u0002QG\u0017#H\u0011\u00151h\u0002q\u0001x\u0011\u0015ih\u0002q\u0001\u007f\u0011\u0019yh\u0002q\u0001\u0002\u0002!9\u00111\u0001\bA\u0004\u0005\u0015\u0001\"\u0002&\u000f\u0001\u0004a\u0003\"\u0002+\u000f\u0001\u00041\u0006\"B1\u000f\u0001\u0004\u0011\u0007\"\u00029\u000f\u0001\u0004)\u0007\"B9\u000f\u0001\u0004\u0011\u0018A\u00033p\rVdGnU2b]V\u0011\u0011Q\u0005\t\u0004y\u0005\u001d\u0012bAA\u0015{\t9!i\\8mK\u0006t\u0017A\u00043p\rVdGnU2b]~#S-\u001d\u000b\u0004\u001d\u0006=\u0002\u0002\u0003*\u0011\u0003\u0003\u0005\r!!\n\u0002\u0017\u0011|g)\u001e7m'\u000e\fg\u000e\t\u0015\u0004#\u0005U\u0002c\u0001\u001f\u00028%\u0019\u0011\u0011H\u001f\u0003\u0011Y|G.\u0019;jY\u0016\f1\u0002]1si&$\u0018n\u001c8feV\u0011\u0011q\b\t\u0005y)\f\t\u0005\u0005\u0003\u0002D\u0005\u0015S\"\u0001\u0013\n\u0007\u0005\u001dCEA\u0006QCJ$\u0018\u000e^5p]\u0016\u0014\u0018\u0001\u00049beRLG/[8oKJ\u0004\u0013AC2iK\u000e\\\u0007o\\5oiR\ta*A\u0004d_6\u0004X\u000f^3\u0015\r\u0005M\u00131NA;!\u0015\t)&!\u001a2\u001d\u0011\t9&!\u0019\u000f\t\u0005e\u0013qL\u0007\u0003\u00037R1!!\u0018:\u0003\u0019a$o\\8u}%\ta(C\u0002\u0002du\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002h\u0005%$\u0001C%uKJ\fGo\u001c:\u000b\u0007\u0005\rT\bC\u0004\u0002nU\u0001\r!a\u001c\u0002\u0013A\f'\u000f^5uS>t\u0007\u0003BA\"\u0003cJ1!a\u001d%\u0005%\u0001\u0016M\u001d;ji&|g\u000eC\u0004\u0002xU\u0001\r!!\u001f\u0002\u000f\r|g\u000e^3yiB!\u00111IA>\u0013\r\ti\b\n\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u0007hKR\u0004\u0016M\u001d;ji&|gn]\u000b\u0003\u0003\u0007\u0003R\u0001PAC\u0003_J1!a\">\u0005\u0015\t%O]1z\u0003E\u0019G.Z1s\t\u0016\u0004XM\u001c3f]\u000eLWm]\u0001\fg\u0016$h)\u001e7m'\u000e\fg.A\bNCB<\u0016\u000e\u001e5Ti\u0006$XM\u0015#E!\t\u0011$dE\u0003\u001b\u0003'\u000bI\nE\u0002=\u0003+K1!a&>\u0005\u0019\te.\u001f*fMB!\u00111TAS\u001b\t\tiJ\u0003\u0003\u0002 \u0006\u0005\u0016AA5p\u0015\t\t\u0019+\u0001\u0003kCZ\f\u0017\u0002BAT\u0003;\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a$\u0002#\r\u0014X-\u0019;f\rJ|W\u000eU1jeJ#E)\u0006\u0006\u00020\u0006]\u00161XA`\u0003\u0007$\u0002\"!-\u0002^\u0006\u0015\u0018q\u001d\u000b\u000b\u0003g\u000b)-a3\u0002R\u0006]\u0007C\u0003\u001a\u0001\u0003k\u000bI,!0\u0002BB\u0019a'a.\u0005\u000bab\"\u0019\u0001\u001e\u0011\u0007Y\nY\fB\u0003]9\t\u0007!\bE\u00027\u0003\u007f#QA\u0012\u000fC\u0002i\u00022ANAb\t\u0015IED1\u0001;\u0011%\t9\rHA\u0001\u0002\b\tI-\u0001\u0006fm&$WM\\2fIe\u0002B\u0001_>\u00026\"I\u0011Q\u001a\u000f\u0002\u0002\u0003\u000f\u0011qZ\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007\u0005\u0003yw\u0006e\u0006\"CAj9\u0005\u0005\t9AAk\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\ta\\\u0018Q\u0018\u0005\n\u00033d\u0012\u0011!a\u0002\u00037\f1\"\u001a<jI\u0016t7-\u001a\u00132eA!\u0001p_Aa\u0011\u001d\ty\u000e\ba\u0001\u0003C\fq\u0001]1jeJ#E\t\u0005\u0003._\u0005\r\bC\u0002\u001fY\u0003k\u000bi\fC\u0004\u0002<q\u0001\r!!\u0011\t\r\u0005%H\u00041\u0001f\u0003))\b\u000fZ1uKRKW.Z\u0001\u000eGJ,\u0017\r^3Ge>l'\u000b\u0012#\u0016\u0015\u0005=\u0018q_A~\u0003\u007f\u0014\u0019\u0001\u0006\u0005\u0002r\nu!q\u0005B\u0015))\t\u0019P!\u0002\u0003\f\tE!q\u0003\t\u000be\u0001\t)0!?\u0002~\n\u0005\u0001c\u0001\u001c\u0002x\u0012)\u0001(\bb\u0001uA\u0019a'a?\u0005\u000bqk\"\u0019\u0001\u001e\u0011\u0007Y\ny\u0010B\u0003G;\t\u0007!\bE\u00027\u0005\u0007!Q!S\u000fC\u0002iB\u0011Ba\u0002\u001e\u0003\u0003\u0005\u001dA!\u0003\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\r\t\u0005qn\f)\u0010C\u0005\u0003\u000eu\t\t\u0011q\u0001\u0003\u0010\u0005YQM^5eK:\u001cW\rJ\u00195!\u0011A80!?\t\u0013\tMQ$!AA\u0004\tU\u0011aC3wS\u0012,gnY3%cU\u0002B\u0001_>\u0002~\"I!\u0011D\u000f\u0002\u0002\u0003\u000f!1D\u0001\fKZLG-\u001a8dK\u0012\nd\u0007\u0005\u0003yw\n\u0005\u0001BB\u0011\u001e\u0001\u0004\u0011y\u0002\u0005\u0003._\t\u0005\u0002\u0003\u0003\u001f\u0003$\u0005U\u0018Q`:\n\u0007\t\u0015RH\u0001\u0004UkBdWm\r\u0005\b\u0003wi\u0002\u0019AA!\u0011\u0019\tI/\ba\u0001K\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u0006\t\u0005\u0005c\u00119$\u0004\u0002\u00034)!!QGAQ\u0003\u0011a\u0017M\\4\n\t\te\"1\u0007\u0002\u0007\u001f\nTWm\u0019;"
)
public class MapWithStateRDD extends RDD {
   private RDD prevStateRDD;
   private RDD partitionedDataRDD;
   private final Function4 mappingFunction;
   private final Time batchTime;
   private final Option timeoutThresholdTime;
   private final ClassTag evidence$5;
   private final ClassTag evidence$6;
   private final ClassTag evidence$7;
   private final ClassTag evidence$8;
   private volatile boolean doFullScan;
   private final Option partitioner;

   public static MapWithStateRDD createFromRDD(final RDD rdd, final Partitioner partitioner, final Time updateTime, final ClassTag evidence$13, final ClassTag evidence$14, final ClassTag evidence$15, final ClassTag evidence$16) {
      return MapWithStateRDD$.MODULE$.createFromRDD(rdd, partitioner, updateTime, evidence$13, evidence$14, evidence$15, evidence$16);
   }

   public static MapWithStateRDD createFromPairRDD(final RDD pairRDD, final Partitioner partitioner, final Time updateTime, final ClassTag evidence$9, final ClassTag evidence$10, final ClassTag evidence$11, final ClassTag evidence$12) {
      return MapWithStateRDD$.MODULE$.createFromPairRDD(pairRDD, partitioner, updateTime, evidence$9, evidence$10, evidence$11, evidence$12);
   }

   private RDD prevStateRDD() {
      return this.prevStateRDD;
   }

   private void prevStateRDD_$eq(final RDD x$1) {
      this.prevStateRDD = x$1;
   }

   private RDD partitionedDataRDD() {
      return this.partitionedDataRDD;
   }

   private void partitionedDataRDD_$eq(final RDD x$1) {
      this.partitionedDataRDD = x$1;
   }

   private boolean doFullScan() {
      return this.doFullScan;
   }

   private void doFullScan_$eq(final boolean x$1) {
      this.doFullScan = x$1;
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public void checkpoint() {
      super.checkpoint();
      this.doFullScan_$eq(true);
   }

   public Iterator compute(final Partition partition, final TaskContext context) {
      MapWithStateRDDPartition stateRDDPartition = (MapWithStateRDDPartition)partition;
      Iterator prevStateRDDIterator = this.prevStateRDD().iterator(stateRDDPartition.previousSessionRDDPartition(), context);
      Iterator dataIterator = this.partitionedDataRDD().iterator(stateRDDPartition.partitionedDataRDDPartition(), context);
      Option prevRecord = (Option)(prevStateRDDIterator.hasNext() ? new Some(prevStateRDDIterator.next()) : .MODULE$);
      MapWithStateRDDRecord newRecord = MapWithStateRDDRecord$.MODULE$.updateRecordWithData(prevRecord, dataIterator, this.mappingFunction, this.batchTime, this.timeoutThresholdTime, this.doFullScan(), this.evidence$5, this.evidence$6, this.evidence$7, this.evidence$8);
      return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MapWithStateRDDRecord[]{newRecord}));
   }

   public Partition[] getPartitions() {
      return (Partition[])scala.Array..MODULE$.tabulate(this.prevStateRDD().partitions().length, (i) -> $anonfun$getPartitions$1(this, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.prevStateRDD_$eq((RDD)null);
      this.partitionedDataRDD_$eq((RDD)null);
   }

   public void setFullScan() {
      this.doFullScan_$eq(true);
   }

   // $FF: synthetic method
   public static final MapWithStateRDDPartition $anonfun$getPartitions$1(final MapWithStateRDD $this, final int i) {
      return new MapWithStateRDDPartition(i, $this.prevStateRDD(), $this.partitionedDataRDD());
   }

   public MapWithStateRDD(final RDD prevStateRDD, final RDD partitionedDataRDD, final Function4 mappingFunction, final Time batchTime, final Option timeoutThresholdTime, final ClassTag evidence$5, final ClassTag evidence$6, final ClassTag evidence$7, final ClassTag evidence$8) {
      boolean var11;
      Predef var10000;
      label17: {
         label16: {
            this.prevStateRDD = prevStateRDD;
            this.partitionedDataRDD = partitionedDataRDD;
            this.mappingFunction = mappingFunction;
            this.batchTime = batchTime;
            this.timeoutThresholdTime = timeoutThresholdTime;
            this.evidence$5 = evidence$5;
            this.evidence$6 = evidence$6;
            this.evidence$7 = evidence$7;
            this.evidence$8 = evidence$8;
            super(partitionedDataRDD.sparkContext(), new scala.collection.immutable..colon.colon(new OneToOneDependency(prevStateRDD), new scala.collection.immutable..colon.colon(new OneToOneDependency(partitionedDataRDD), scala.collection.immutable.Nil..MODULE$)), scala.reflect.ClassTag..MODULE$.apply(MapWithStateRDDRecord.class));
            this.doFullScan = false;
            scala.Predef..MODULE$.require(this.prevStateRDD().partitioner().nonEmpty());
            var10000 = scala.Predef..MODULE$;
            Option var10001 = this.partitionedDataRDD().partitioner();
            Option var10 = this.prevStateRDD().partitioner();
            if (var10001 == null) {
               if (var10 == null) {
                  break label16;
               }
            } else if (var10001.equals(var10)) {
               break label16;
            }

            var11 = false;
            break label17;
         }

         var11 = true;
      }

      var10000.require(var11);
      this.partitioner = this.prevStateRDD().partitioner();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
