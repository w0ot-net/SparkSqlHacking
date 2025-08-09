package org.apache.spark;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.PartitionPruningRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.JavaSerializer;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.util.CollectionsUtils$;
import org.apache.spark.util.Utils$;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Set;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tuc\u0001B\u0012%\u0001-B\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tq\u0001\u0011\t\u0011)A\u0005s!AA\u000b\u0001BA\u0002\u0013%Q\u000b\u0003\u0005Z\u0001\t\u0005\r\u0011\"\u0003[\u0011!\u0001\u0007A!A!B\u00131\u0006\u0002C1\u0001\u0005\u000b\u0007I\u0011\u00012\t\u0011\r\u0004!\u0011!Q\u0001\nIB\u0001\u0002\u001a\u0001\u0003\u0004\u0003\u0006Y!\u001a\u0005\tc\u0002\u0011\u0019\u0011)A\u0006e\")\u0001\u0010\u0001C\u0001s\"1\u0001\u0010\u0001C\u0001\u0003\u001bA\u0011\"!\u000b\u0001\u0001\u0004%I!a\u000b\t\u0013\u00055\u0002\u00011A\u0005\n\u0005=\u0002bBA\u001a\u0001\u0001\u0006K!\u001a\u0005\n\u0003k\u0001\u0001\u0019!C\u0005\u0003oA\u0011\"a\u0010\u0001\u0001\u0004%I!!\u0011\t\u0011\u0005\u0015\u0003\u0001)Q\u0005\u0003sAa!a\u0012\u0001\t\u0003\u0011\u0007\"CA%\u0001\u0001\u0007I\u0011BA&\u0011%\t\u0019\u0006\u0001a\u0001\n\u0013\t)\u0006\u0003\u0005\u0002Z\u0001\u0001\u000b\u0015BA'\u0011\u001d\tY\u0006\u0001C\u0001\u0003;Bq!a\u0019\u0001\t\u0003\n)\u0007C\u0004\u0002l\u0001!\t%!\u001c\t\u000f\u0005=\u0004\u0001\"\u0003\u0002r!9\u0011q\u0013\u0001\u0005\n\u0005eu\u0001CATI!\u0005A%!+\u0007\u000f\r\"\u0003\u0012\u0001\u0013\u0002,\"1\u0001\u0010\bC\u0001\u0003sCq!a/\u001d\t\u0003\ti\fC\u0004\u0002lr!\t!!<\t\u0013\t\rB$%A\u0005\u0002\t\u0015\u0002\"\u0003B!9E\u0005I\u0011\u0001B\"\u0011%\u0011i\u0005HA\u0001\n\u0013\u0011yE\u0001\tSC:<W\rU1si&$\u0018n\u001c8fe*\u0011QEJ\u0001\u0006gB\f'o\u001b\u0006\u0003O!\na!\u00199bG\",'\"A\u0015\u0002\u0007=\u0014xm\u0001\u0001\u0016\u00071Z%k\u0005\u0002\u0001[A\u0011afL\u0007\u0002I%\u0011\u0001\u0007\n\u0002\f!\u0006\u0014H/\u001b;j_:,'/\u0001\u0006qCJ$\u0018\u000e^5p]N\u0004\"a\r\u001c\u000e\u0003QR\u0011!N\u0001\u0006g\u000e\fG.Y\u0005\u0003oQ\u00121!\u00138u\u0003\r\u0011H\r\u001a\u0019\u0003u\u0005\u00032aO\u001f@\u001b\u0005a$B\u0001\u001d%\u0013\tqDHA\u0002S\t\u0012\u0003\"\u0001Q!\r\u0001\u0011I!IAA\u0001\u0002\u0003\u0015\ta\u0011\u0002\u0004?\u00122\u0014C\u0001#H!\t\u0019T)\u0003\u0002Gi\t9aj\u001c;iS:<\u0007\u0003B\u001aI\u0015FK!!\u0013\u001b\u0003\u0011A\u0013x\u000eZ;diJ\u0002\"\u0001Q&\u0005\u000b1\u0003!\u0019A'\u0003\u0003-\u000b\"\u0001\u0012(\u0011\u0005Mz\u0015B\u0001)5\u0005\r\te.\u001f\t\u0003\u0001J#Qa\u0015\u0001C\u00025\u0013\u0011AV\u0001\nCN\u001cWM\u001c3j]\u001e,\u0012A\u0016\t\u0003g]K!\u0001\u0017\u001b\u0003\u000f\t{w\u000e\\3b]\u0006i\u0011m]2f]\u0012LgnZ0%KF$\"a\u00170\u0011\u0005Mb\u0016BA/5\u0005\u0011)f.\u001b;\t\u000f}#\u0011\u0011!a\u0001-\u0006\u0019\u0001\u0010J\u0019\u0002\u0015\u0005\u001c8-\u001a8eS:<\u0007%\u0001\u000ftC6\u0004H.\u001a)pS:$8\u000fU3s!\u0006\u0014H/\u001b;j_:D\u0015N\u001c;\u0016\u0003I\nQd]1na2,\u0007k\\5oiN\u0004VM\u001d)beRLG/[8o\u0011&tG\u000fI\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u00014o\u0015:\u0011q\r\u001c\b\u0003Q.l\u0011!\u001b\u0006\u0003U*\na\u0001\u0010:p_Rt\u0014\"A\u001b\n\u00055$\u0014a\u00029bG.\fw-Z\u0005\u0003_B\u0014\u0001b\u0014:eKJLgn\u001a\u0006\u0003[R\n!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u0019hOS\u0007\u0002i*\u0011Q\u000fN\u0001\be\u00164G.Z2u\u0013\t9HO\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q9!P`@\u0002\n\u0005-AcA>}{B!a\u0006\u0001&R\u0011\u0015!'\u0002q\u0001f\u0011\u0015\t(\u0002q\u0001s\u0011\u0015\t$\u00021\u00013\u0011\u0019A$\u00021\u0001\u0002\u0002A\"\u00111AA\u0004!\u0011YT(!\u0002\u0011\u0007\u0001\u000b9\u0001B\u0005C\u007f\u0006\u0005\t\u0011!B\u0001\u0007\"9AK\u0003I\u0001\u0002\u00041\u0006bB1\u000b!\u0003\u0005\rA\r\u000b\t\u0003\u001f\tI\"a\u0007\u0002(Q)10!\u0005\u0002\u0016!A\u00111C\u0006\u0002\u0002\u0003\u000fQ-\u0001\u0006fm&$WM\\2fIMB\u0001\"a\u0006\f\u0003\u0003\u0005\u001dA]\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004\"B\u0019\f\u0001\u0004\u0011\u0004B\u0002\u001d\f\u0001\u0004\ti\u0002\r\u0003\u0002 \u0005\r\u0002\u0003B\u001e>\u0003C\u00012\u0001QA\u0012\t-\t)#a\u0007\u0002\u0002\u0003\u0005)\u0011A\"\u0003\u0007}#s\u0007C\u0003U\u0017\u0001\u0007a+\u0001\u0005pe\u0012,'/\u001b8h+\u0005)\u0017\u0001D8sI\u0016\u0014\u0018N\\4`I\u0015\fHcA.\u00022!9q,DA\u0001\u0002\u0004)\u0017!C8sI\u0016\u0014\u0018N\\4!\u0003-\u0011\u0018M\\4f\u0005>,h\u000eZ:\u0016\u0005\u0005e\u0002\u0003B\u001a\u0002<)K1!!\u00105\u0005\u0015\t%O]1z\u0003=\u0011\u0018M\\4f\u0005>,h\u000eZ:`I\u0015\fHcA.\u0002D!Aq\fEA\u0001\u0002\u0004\tI$\u0001\u0007sC:<WMQ8v]\u0012\u001c\b%A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\rE&t\u0017M]=TK\u0006\u00148\r[\u000b\u0003\u0003\u001b\u0002raMA(\u0003sQ%'C\u0002\u0002RQ\u0012\u0011BR;oGRLwN\u001c\u001a\u0002!\tLg.\u0019:z'\u0016\f'o\u00195`I\u0015\fHcA.\u0002X!Aq\fFA\u0001\u0002\u0004\ti%A\u0007cS:\f'/_*fCJ\u001c\u0007\u000eI\u0001\rO\u0016$\b+\u0019:uSRLwN\u001c\u000b\u0004e\u0005}\u0003BBA1-\u0001\u0007a*A\u0002lKf\fa!Z9vC2\u001cHc\u0001,\u0002h!1\u0011\u0011N\fA\u00029\u000bQa\u001c;iKJ\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002e\u0005YqO]5uK>\u0013'.Z2u)\rY\u00161\u000f\u0005\b\u0003kJ\u0002\u0019AA<\u0003\ryW\u000f\u001e\t\u0005\u0003s\n\u0019)\u0004\u0002\u0002|)!\u0011QPA@\u0003\tIwN\u0003\u0002\u0002\u0002\u0006!!.\u0019<b\u0013\u0011\t))a\u001f\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0015\u00063\u0005%\u0015Q\u0013\t\u0006g\u0005-\u0015qR\u0005\u0004\u0003\u001b#$A\u0002;ie><8\u000f\u0005\u0003\u0002z\u0005E\u0015\u0002BAJ\u0003w\u00121\"S(Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u0011qR\u0001\u000be\u0016\fGm\u00142kK\u000e$HcA.\u0002\u001c\"9\u0011Q\u0014\u000eA\u0002\u0005}\u0015AA5o!\u0011\tI(!)\n\t\u0005\r\u00161\u0010\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007&\u0002\u000e\u0002\n\u0006U\u0015\u0001\u0005*b]\u001e,\u0007+\u0019:uSRLwN\\3s!\tqCdE\u0003\u001d\u0003[\u000b\u0019\fE\u00024\u0003_K1!!-5\u0005\u0019\te.\u001f*fMB!\u0011\u0011PA[\u0013\u0011\t9,a\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005%\u0016AB:lKR\u001c\u0007.\u0006\u0003\u0002@\u0006mGCBAa\u0003G\f9\u000f\u0006\u0003\u0002D\u0006u\u0007cB\u001a\u0002F\u0006%\u0017qZ\u0005\u0004\u0003\u000f$$A\u0002+va2,'\u0007E\u00024\u0003\u0017L1!!45\u0005\u0011auN\\4\u0011\u000bM\nY$!5\u0011\u0011M\n\u0019NMAe\u0003/L1!!65\u0005\u0019!V\u000f\u001d7fgA)1'a\u000f\u0002ZB\u0019\u0001)a7\u0005\u000b1s\"\u0019A'\t\u0013\u0005}g$!AA\u0004\u0005\u0005\u0018AC3wS\u0012,gnY3%kA!1O^Am\u0011\u0019Ad\u00041\u0001\u0002fB!1(PAm\u0011\u0019\tIO\ba\u0001e\u000512/Y7qY\u0016\u001c\u0016N_3QKJ\u0004\u0016M\u001d;ji&|g.A\beKR,'/\\5oK\n{WO\u001c3t+\u0011\ty/a>\u0015\r\u0005E(Q\u0001B\u0011)\u0019\t\u00190!?\u0002\u0000B)1'a\u000f\u0002vB\u0019\u0001)a>\u0005\u000b1{\"\u0019A'\t\u0013\u0005mx$!AA\u0004\u0005u\u0018AC3wS\u0012,gnY3%mA!aM\\A{\u0011%\u0011\taHA\u0001\u0002\b\u0011\u0019!\u0001\u0006fm&$WM\\2fI]\u0002Ba\u001d<\u0002v\"9!qA\u0010A\u0002\t%\u0011AC2b]\u0012LG-\u0019;fgB1!1\u0002B\u000b\u00053i!A!\u0004\u000b\t\t=!\u0011C\u0001\b[V$\u0018M\u00197f\u0015\r\u0011\u0019\u0002N\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\f\u0005\u001b\u00111\"\u0011:sCf\u0014UO\u001a4feB91'!2\u0002v\nm\u0001cA\u001a\u0003\u001e%\u0019!q\u0004\u001b\u0003\u000b\u0019cw.\u0019;\t\u000bEz\u0002\u0019\u0001\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\u0019\u00119C!\u0010\u0003@U\u0011!\u0011\u0006\u0016\u0004-\n-2F\u0001B\u0017!\u0011\u0011yC!\u000f\u000e\u0005\tE\"\u0002\u0002B\u001a\u0005k\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t]B'\u0001\u0006b]:|G/\u0019;j_:LAAa\u000f\u00032\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b1\u0003#\u0019A'\u0005\u000bM\u0003#\u0019A'\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135+\u0019\u0011)E!\u0013\u0003LU\u0011!q\t\u0016\u0004e\t-B!\u0002'\"\u0005\u0004iE!B*\"\u0005\u0004i\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B)!\u0011\u0011\u0019F!\u0017\u000e\u0005\tU#\u0002\u0002B,\u0003\u007f\nA\u0001\\1oO&!!1\fB+\u0005\u0019y%M[3di\u0002"
)
public class RangePartitioner extends Partitioner {
   private final int partitions;
   private boolean ascending;
   private final int samplePointsPerPartitionHint;
   private final ClassTag evidence$2;
   private Ordering ordering;
   private Object rangeBounds;
   private Function2 binarySearch;

   public static int $lessinit$greater$default$4() {
      return RangePartitioner$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return RangePartitioner$.MODULE$.$lessinit$greater$default$3();
   }

   public static Object determineBounds(final ArrayBuffer candidates, final int partitions, final Ordering evidence$6, final ClassTag evidence$7) {
      return RangePartitioner$.MODULE$.determineBounds(candidates, partitions, evidence$6, evidence$7);
   }

   public static Tuple2 sketch(final RDD rdd, final int sampleSizePerPartition, final ClassTag evidence$5) {
      return RangePartitioner$.MODULE$.sketch(rdd, sampleSizePerPartition, evidence$5);
   }

   private boolean ascending() {
      return this.ascending;
   }

   private void ascending_$eq(final boolean x$1) {
      this.ascending = x$1;
   }

   public int samplePointsPerPartitionHint() {
      return this.samplePointsPerPartitionHint;
   }

   private Ordering ordering() {
      return this.ordering;
   }

   private void ordering_$eq(final Ordering x$1) {
      this.ordering = x$1;
   }

   private Object rangeBounds() {
      return this.rangeBounds;
   }

   private void rangeBounds_$eq(final Object x$1) {
      this.rangeBounds = x$1;
   }

   public int numPartitions() {
      return .MODULE$.array_length(this.rangeBounds()) + 1;
   }

   private Function2 binarySearch() {
      return this.binarySearch;
   }

   private void binarySearch_$eq(final Function2 x$1) {
      this.binarySearch = x$1;
   }

   public int getPartition(final Object key) {
      Object k = key;
      int partition = 0;
      if (.MODULE$.array_length(this.rangeBounds()) <= 128) {
         while(partition < .MODULE$.array_length(this.rangeBounds()) && this.ordering().gt(k, .MODULE$.array_apply(this.rangeBounds(), partition))) {
            ++partition;
         }
      } else {
         partition = BoxesRunTime.unboxToInt(this.binarySearch().apply(this.rangeBounds(), key));
         if (partition < 0) {
            partition = -partition - 1;
         }

         if (partition > .MODULE$.array_length(this.rangeBounds())) {
            partition = .MODULE$.array_length(this.rangeBounds());
         }
      }

      return this.ascending() ? partition : .MODULE$.array_length(this.rangeBounds()) - partition;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof RangePartitioner var4)) {
         return false;
      } else {
         return scala.Predef..MODULE$.genericWrapArray(var4.rangeBounds()).sameElements(scala.Predef..MODULE$.genericWrapArray(this.rangeBounds())) && var4.ascending() == this.ascending();
      }
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;

      for(int i = 0; i < .MODULE$.array_length(this.rangeBounds()); ++i) {
         result = prime * result + .MODULE$.array_apply(this.rangeBounds(), i).hashCode();
      }

      result = prime * result + Boolean.hashCode(this.ascending());
      return result;
   }

   private void writeObject(final ObjectOutputStream out) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         Serializer sfactory = SparkEnv$.MODULE$.get().serializer();
         if (sfactory instanceof JavaSerializer) {
            out.defaultWriteObject();
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            out.writeBoolean(this.ascending());
            out.writeObject(this.ordering());
            out.writeObject(this.binarySearch());
            SerializerInstance ser = sfactory.newInstance();
            Utils$.MODULE$.serializeViaNestedStream(out, ser, (stream) -> {
               $anonfun$writeObject$2(this, stream);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      });
   }

   private void readObject(final ObjectInputStream in) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         Serializer sfactory = SparkEnv$.MODULE$.get().serializer();
         if (sfactory instanceof JavaSerializer) {
            in.defaultReadObject();
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            this.ascending_$eq(in.readBoolean());
            this.ordering_$eq((Ordering)in.readObject());
            this.binarySearch_$eq((Function2)in.readObject());
            SerializerInstance ser = sfactory.newInstance();
            Utils$.MODULE$.deserializeViaNestedStream(in, ser, (ds) -> {
               $anonfun$readObject$2(this, ds);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$writeObject$2(final RangePartitioner $this, final SerializationStream stream) {
      stream.writeObject(scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass($this.evidence$2.runtimeClass()))), scala.reflect.ClassTag..MODULE$.apply(ClassTag.class));
      stream.writeObject($this.rangeBounds(), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass($this.evidence$2.runtimeClass())));
   }

   // $FF: synthetic method
   public static final void $anonfun$readObject$2(final RangePartitioner $this, final DeserializationStream ds) {
      ClassTag classTag = (ClassTag)ds.readObject(scala.reflect.ClassTag..MODULE$.apply(ClassTag.class));
      $this.rangeBounds_$eq(ds.readObject(classTag));
   }

   public RangePartitioner(final int partitions, final RDD rdd, final boolean ascending, final int samplePointsPerPartitionHint, final Ordering evidence$1, final ClassTag evidence$2) {
      this.partitions = partitions;
      this.ascending = ascending;
      this.samplePointsPerPartitionHint = samplePointsPerPartitionHint;
      this.evidence$2 = evidence$2;
      super();
      scala.Predef..MODULE$.require(partitions >= 0, () -> "Number of partitions cannot be negative but found " + this.partitions + ".");
      scala.Predef..MODULE$.require(samplePointsPerPartitionHint > 0, () -> "Sample points per partition must be greater than 0 but found " + this.samplePointsPerPartitionHint());
      this.ordering = (Ordering)scala.Predef..MODULE$.implicitly(evidence$1);
      Object var10001;
      if (partitions <= 1) {
         var10001 = scala.Array..MODULE$.empty(evidence$2);
      } else {
         double sampleSize = scala.math.package..MODULE$.min((double)samplePointsPerPartitionHint * (double)partitions, (double)1000000.0F);
         int sampleSizePerPartition = (int)scala.math.package..MODULE$.ceil((double)3.0F * sampleSize / (double)rdd.partitions().length);
         Tuple2 var12 = RangePartitioner$.MODULE$.sketch(rdd.map((x$6) -> x$6._1(), evidence$2), sampleSizePerPartition, evidence$2);
         if (var12 == null) {
            throw new MatchError(var12);
         }

         long numItems = var12._1$mcJ$sp();
         Tuple3[] sketched = (Tuple3[])var12._2();
         Tuple2 var11 = new Tuple2(BoxesRunTime.boxToLong(numItems), sketched);
         long numItems = var11._1$mcJ$sp();
         Tuple3[] sketched = (Tuple3[])var11._2();
         if (numItems == 0L) {
            var10001 = scala.Array..MODULE$.empty(evidence$2);
         } else {
            double fraction = scala.math.package..MODULE$.min(sampleSize / (double)scala.math.package..MODULE$.max(numItems, 1L), (double)1.0F);
            ArrayBuffer candidates = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
            Set imbalancedPartitions = (Set)scala.collection.mutable.Set..MODULE$.empty();
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])sketched), (x0$1) -> {
               if (x0$1 != null) {
                  int idx = BoxesRunTime.unboxToInt(x0$1._1());
                  long n = BoxesRunTime.unboxToLong(x0$1._2());
                  Object sample = x0$1._3();
                  if (fraction * (double)n > (double)sampleSizePerPartition) {
                     return imbalancedPartitions.$plus$eq(BoxesRunTime.boxToInteger(idx));
                  } else {
                     float weight = (float)((double)n / (double).MODULE$.array_length(sample));
                     scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.genericArrayOps(sample), (key) -> (ArrayBuffer)candidates.$plus$eq(new Tuple2(key, BoxesRunTime.boxToFloat(weight))));
                     return BoxedUnit.UNIT;
                  }
               } else {
                  throw new MatchError(x0$1);
               }
            });
            if (imbalancedPartitions.nonEmpty()) {
               PartitionPruningRDD imbalanced = new PartitionPruningRDD(rdd.map((x$8) -> x$8._1(), evidence$2), (JFunction1.mcZI.sp)(elem) -> imbalancedPartitions.contains(BoxesRunTime.boxToInteger(elem)), evidence$2);
               int seed = scala.util.hashing.package..MODULE$.byteswap32(-rdd.id() - 1);
               Object reSampled = imbalanced.sample(false, fraction, (long)seed).collect();
               float weight = (float)((double)1.0F / fraction);
               candidates.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(reSampled), (x) -> new Tuple2(x, BoxesRunTime.boxToFloat(weight)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))));
            } else {
               BoxedUnit var27 = BoxedUnit.UNIT;
            }

            var10001 = RangePartitioner$.MODULE$.determineBounds(candidates, scala.math.package..MODULE$.min(partitions, candidates.size()), evidence$1, evidence$2);
         }
      }

      this.rangeBounds = var10001;
      this.binarySearch = CollectionsUtils$.MODULE$.makeBinarySearch(evidence$1, evidence$2);
   }

   public RangePartitioner(final int partitions, final RDD rdd, final boolean ascending, final Ordering evidence$3, final ClassTag evidence$4) {
      this(partitions, rdd, ascending, 20, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
