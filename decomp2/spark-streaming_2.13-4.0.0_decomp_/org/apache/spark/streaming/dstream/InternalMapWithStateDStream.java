package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.rdd.EmptyRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StateSpecImpl;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.rdd.MapWithStateRDD;
import org.apache.spark.streaming.rdd.MapWithStateRDD$;
import org.apache.spark.streaming.rdd.MapWithStateRDDRecord;
import scala.Function4;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e!\u0002\r\u001a\u0001m\u0019\u0003\u0002\u0003#\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u00111\u0003!\u0011!Q\u0001\n5C\u0001\"\u0015\u0001\u0003\u0004\u0003\u0006YA\u0015\u0005\t1\u0002\u0011\u0019\u0011)A\u00063\"A!\f\u0001B\u0002B\u0003-1\f\u0003\u0005]\u0001\t\r\t\u0015a\u0003^\u0011\u0015q\u0006\u0001\"\u0001`\u0011\u001dA\u0007A1A\u0005\n%DaA\u001c\u0001!\u0002\u0013Q\u0007bB8\u0001\u0005\u0004%I\u0001\u001d\u0005\u0007}\u0002\u0001\u000b\u0011B9\t\r}\u0004A\u0011IA\u0001\u0011\u001d\tI\u0001\u0001C!\u0003\u0017A\u0011\"a\f\u0001\u0005\u0004%\t%!\r\t\u0011\u0005e\u0002\u0001)A\u0005\u0003gAq!a\u000f\u0001\t\u0003\ni\u0004C\u0004\u0002J\u0001!\t%a\u0013\b\u0011\u0005u\u0013\u0004#\u0001\u001c\u0003?2q\u0001G\r\t\u0002m\t\t\u0007\u0003\u0004_'\u0011\u0005\u0011\u0011\u0010\u0005\n\u0003w\u001a\"\u0019!C\u0005\u0003{B\u0001\"!\"\u0014A\u0003%\u0011q\u0010\u0005\n\u0003\u000f\u001b\u0012\u0011!C\u0005\u0003\u0013\u00131$\u00138uKJt\u0017\r\\'ba^KG\u000f[*uCR,Gi\u0015;sK\u0006l'B\u0001\u000e\u001c\u0003\u001d!7\u000f\u001e:fC6T!\u0001H\u000f\u0002\u0013M$(/Z1nS:<'B\u0001\u0010 \u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0013%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0005\u0019qN]4\u0016\u000b\u0011\n$j\u0010\"\u0014\u0005\u0001)\u0003c\u0001\u0014(S5\t\u0011$\u0003\u0002)3\t9Ai\u0015;sK\u0006l\u0007#\u0002\u0016._y\nU\"A\u0016\u000b\u00051Z\u0012a\u0001:eI&\u0011af\u000b\u0002\u0016\u001b\u0006\u0004x+\u001b;i'R\fG/\u001a*E\tJ+7m\u001c:e!\t\u0001\u0014\u0007\u0004\u0001\u0005\u000bI\u0002!\u0019\u0001\u001b\u0003\u0003-\u001b\u0001!\u0005\u00026wA\u0011a'O\u0007\u0002o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\t9aj\u001c;iS:<\u0007C\u0001\u001c=\u0013\titGA\u0002B]f\u0004\"\u0001M \u0005\u000b\u0001\u0003!\u0019\u0001\u001b\u0003\u0003M\u0003\"\u0001\r\"\u0005\u000b\r\u0003!\u0019\u0001\u001b\u0003\u0003\u0015\u000ba\u0001]1sK:$\bc\u0001\u0014(\rB!agR\u0018J\u0013\tAuG\u0001\u0004UkBdWM\r\t\u0003a)#Qa\u0013\u0001C\u0002Q\u0012\u0011AV\u0001\u0005gB,7\r\u0005\u0004O\u001f>Je(Q\u0007\u00027%\u0011\u0001k\u0007\u0002\u000e'R\fG/Z*qK\u000eLU\u000e\u001d7\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007E\u0002T->j\u0011\u0001\u0016\u0006\u0003+^\nqA]3gY\u0016\u001cG/\u0003\u0002X)\nA1\t\\1tgR\u000bw-\u0001\u0006fm&$WM\\2fI]\u00022a\u0015,J\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0004'Zs\u0014AC3wS\u0012,gnY3%sA\u00191KV!\u0002\rqJg.\u001b;?)\r\u0001gm\u001a\u000b\u0006C\n\u001cG-\u001a\t\u0007M\u0001y\u0013JP!\t\u000bE;\u00019\u0001*\t\u000ba;\u00019A-\t\u000bi;\u00019A.\t\u000bq;\u00019A/\t\u000b\u0011;\u0001\u0019A#\t\u000b1;\u0001\u0019A'\u0002\u0017A\f'\u000f^5uS>tWM]\u000b\u0002UB\u00111\u000e\\\u0007\u0002;%\u0011Q.\b\u0002\f!\u0006\u0014H/\u001b;j_:,'/\u0001\u0007qCJ$\u0018\u000e^5p]\u0016\u0014\b%A\bnCB\u0004\u0018N\\4Gk:\u001cG/[8o+\u0005\t\bc\u0002\u001csi>:(0`\u0005\u0003g^\u0012\u0011BR;oGRLwN\u001c\u001b\u0011\u00059+\u0018B\u0001<\u001c\u0005\u0011!\u0016.\\3\u0011\u0007YB\u0018*\u0003\u0002zo\t1q\n\u001d;j_:\u00042AT>?\u0013\ta8DA\u0003Ti\u0006$X\rE\u00027q\u0006\u000b\u0001#\\1qa&twMR;oGRLwN\u001c\u0011\u0002\u001bMd\u0017\u000eZ3EkJ\fG/[8o+\t\t\u0019\u0001E\u0002O\u0003\u000bI1!a\u0002\u001c\u0005!!UO]1uS>t\u0017\u0001\u00043fa\u0016tG-\u001a8dS\u0016\u001cXCAA\u0007!\u0019\ty!a\b\u0002&9!\u0011\u0011CA\u000e\u001d\u0011\t\u0019\"!\u0007\u000e\u0005\u0005U!bAA\fg\u00051AH]8pizJ\u0011\u0001O\u0005\u0004\u0003;9\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003C\t\u0019C\u0001\u0003MSN$(bAA\u000foA\"\u0011qEA\u0016!\u00111s%!\u000b\u0011\u0007A\nY\u0003\u0002\u0006\u0002.5\t\t\u0011!A\u0003\u0002Q\u00121a\u0018\u00137\u00039iWo\u001d;DQ\u0016\u001c7\u000e]8j]R,\"!a\r\u0011\u0007Y\n)$C\u0002\u00028]\u0012qAQ8pY\u0016\fg.A\bnkN$8\t[3dWB|\u0017N\u001c;!\u0003)Ig.\u001b;jC2L'0\u001a\u000b\u0005\u0003\u007f\t)\u0005E\u00027\u0003\u0003J1!a\u00118\u0005\u0011)f.\u001b;\t\r\u0005\u001d\u0003\u00031\u0001u\u0003\u0011!\u0018.\\3\u0002\u000f\r|W\u000e];uKR!\u0011QJA-!\u00111\u00040a\u0014\u0011\u000b\u0005E\u0013QK\u0015\u000e\u0005\u0005M#B\u0001\u0017\u001e\u0013\u0011\t9&a\u0015\u0003\u0007I#E\t\u0003\u0004\u0002\\E\u0001\r\u0001^\u0001\nm\u0006d\u0017\u000e\u001a+j[\u0016\f1$\u00138uKJt\u0017\r\\'ba^KG\u000f[*uCR,Gi\u0015;sK\u0006l\u0007C\u0001\u0014\u0014'\u0015\u0019\u00121MA5!\r1\u0014QM\u0005\u0004\u0003O:$AB!osJ+g\r\u0005\u0003\u0002l\u0005UTBAA7\u0015\u0011\ty'!\u001d\u0002\u0005%|'BAA:\u0003\u0011Q\u0017M^1\n\t\u0005]\u0014Q\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003?\na\u0005R#G\u0003VcEkX\"I\u000b\u000e[\u0005kT%O)~#UKU!U\u0013>su,T+M)&\u0003F*S#S+\t\ty\bE\u00027\u0003\u0003K1!a!8\u0005\rIe\u000e^\u0001(\t\u00163\u0015)\u0016'U?\u000eCUiQ&Q\u001f&sEk\u0018#V%\u0006#\u0016j\u0014(`\u001bVcE+\u0013)M\u0013\u0016\u0013\u0006%\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\fB!\u0011QRAJ\u001b\t\tyI\u0003\u0003\u0002\u0012\u0006E\u0014\u0001\u00027b]\u001eLA!!&\u0002\u0010\n1qJ\u00196fGR\u0004"
)
public class InternalMapWithStateDStream extends DStream {
   private final DStream parent;
   private final StateSpecImpl spec;
   private final ClassTag evidence$6;
   private final ClassTag evidence$7;
   private final ClassTag evidence$8;
   private final ClassTag evidence$9;
   private final Partitioner partitioner;
   private final Function4 mappingFunction;
   private final boolean mustCheckpoint;

   private Partitioner partitioner() {
      return this.partitioner;
   }

   private Function4 mappingFunction() {
      return this.mappingFunction;
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public boolean mustCheckpoint() {
      return this.mustCheckpoint;
   }

   public void initialize(final Time time) {
      if (this.checkpointDuration() == null) {
         this.checkpointDuration_$eq(this.slideDuration().$times(InternalMapWithStateDStream$.MODULE$.org$apache$spark$streaming$dstream$InternalMapWithStateDStream$$DEFAULT_CHECKPOINT_DURATION_MULTIPLIER()));
      }

      super.initialize(time);
   }

   public Option compute(final Time validTime) {
      Option var4 = this.getOrCompute(validTime.$minus(this.slideDuration()));
      Object var15;
      if (var4 instanceof Some var5) {
         label25: {
            RDD rdd;
            label24: {
               rdd = (RDD)var5.value();
               Option var10000 = rdd.partitioner();
               Some var7 = new Some(this.partitioner());
               if (var10000 == null) {
                  if (var7 != null) {
                     break label24;
                  }
               } else if (!var10000.equals(var7)) {
                  break label24;
               }

               var15 = rdd;
               break label25;
            }

            var15 = MapWithStateRDD$.MODULE$.createFromRDD(rdd.flatMap((x$4x) -> x$4x.stateMap().getAll(), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)), this.partitioner(), validTime, this.evidence$6, this.evidence$7, this.evidence$8, this.evidence$9);
         }
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         var15 = MapWithStateRDD$.MODULE$.createFromPairRDD((RDD)this.spec.getInitialStateRDD().getOrElse(() -> new EmptyRDD(this.ssc().sparkContext(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), this.partitioner(), validTime, this.evidence$6, this.evidence$7, this.evidence$8, this.evidence$9);
      }

      RDD prevStateRDD = (RDD)var15;
      RDD dataRDD = (RDD)this.parent.getOrCompute(validTime).getOrElse(() -> this.context().sparkContext().emptyRDD(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
      ClassTag x$2 = this.evidence$6;
      ClassTag x$3 = this.evidence$7;
      Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(dataRDD);
      RDD partitionedDataRDD = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(dataRDD, x$2, x$3, (Ordering)null).partitionBy(this.partitioner());
      Option timeoutThresholdTime = this.spec.getTimeoutInterval().map((interval) -> BoxesRunTime.boxToLong($anonfun$compute$6(validTime, interval)));
      return new Some(new MapWithStateRDD(prevStateRDD, partitionedDataRDD, this.mappingFunction(), validTime, timeoutThresholdTime, this.evidence$6, this.evidence$7, this.evidence$8, this.evidence$9));
   }

   // $FF: synthetic method
   public static final long $anonfun$compute$6(final Time validTime$1, final Duration interval) {
      return validTime$1.$minus(interval).milliseconds();
   }

   public InternalMapWithStateDStream(final DStream parent, final StateSpecImpl spec, final ClassTag evidence$6, final ClassTag evidence$7, final ClassTag evidence$8, final ClassTag evidence$9) {
      super(parent.context(), scala.reflect.ClassTag..MODULE$.apply(MapWithStateRDDRecord.class));
      this.parent = parent;
      this.spec = spec;
      this.evidence$6 = evidence$6;
      this.evidence$7 = evidence$7;
      this.evidence$8 = evidence$8;
      this.evidence$9 = evidence$9;
      this.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY());
      this.partitioner = (Partitioner)spec.getPartitioner().getOrElse(() -> new HashPartitioner(this.ssc().sc().defaultParallelism()));
      this.mappingFunction = spec.getFunction();
      this.mustCheckpoint = true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
