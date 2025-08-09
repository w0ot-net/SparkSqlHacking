package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.PartitionEvaluatorFactory;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Experimental;
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001B\u0005\u000b\u0001MA\u0001b\u0003\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\tU\u0001\u0011\u0019\u0011)A\u0006W!1\u0011\u0007\u0001C\u0001\u0019IBQa\u000e\u0001\u0005\u0002aBqA\u001a\u0001\u0012\u0002\u0013\u0005q\rC\u0003t\u0001\u0011\u0005A\u000fC\u0005\u0002\u0016\u0001\t\n\u0011\"\u0001\u0002\u0018!9\u00111\u0004\u0001\u0005\u0002\u0005u!A\u0003*E\t\n\u000b'O]5fe*\u00111\u0002D\u0001\u0004e\u0012$'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001U\u0011A#I\n\u0003\u0001U\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007c\u0001\u000f\u001e?5\t!\"\u0003\u0002\u001f\u0015\t\u0019!\u000b\u0012#\u0011\u0005\u0001\nC\u0002\u0001\u0003\u0006E\u0001\u0011\ra\t\u0002\u0002)F\u0011Ae\n\t\u0003-\u0015J!AJ\f\u0003\u000f9{G\u000f[5oOB\u0011a\u0003K\u0005\u0003S]\u00111!\u00118z\u0003))g/\u001b3f]\u000e,G%\r\t\u0004Y=zR\"A\u0017\u000b\u00059:\u0012a\u0002:fM2,7\r^\u0005\u0003a5\u0012\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005M2DC\u0001\u001b6!\ra\u0002a\b\u0005\u0006U\r\u0001\u001da\u000b\u0005\u0006\u0017\r\u0001\raG\u0001\u000e[\u0006\u0004\b+\u0019:uSRLwN\\:\u0016\u0005ejDc\u0001\u001eC)R\u00111h\u0010\t\u00049ua\u0004C\u0001\u0011>\t\u0015qDA1\u0001$\u0005\u0005\u0019\u0006b\u0002!\u0005\u0003\u0003\u0005\u001d!Q\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001\u00170y!)1\t\u0002a\u0001\t\u0006\ta\r\u0005\u0003\u0017\u000b\u001e\u001b\u0016B\u0001$\u0018\u0005%1UO\\2uS>t\u0017\u0007E\u0002I!~q!!\u0013(\u000f\u0005)kU\"A&\u000b\u00051\u0013\u0012A\u0002\u001fs_>$h(C\u0001\u0019\u0013\tyu#A\u0004qC\u000e\\\u0017mZ3\n\u0005E\u0013&\u0001C%uKJ\fGo\u001c:\u000b\u0005=;\u0002c\u0001%Qy!9Q\u000b\u0002I\u0001\u0002\u00041\u0016!\u00069sKN,'O^3t!\u0006\u0014H/\u001b;j_:Lgn\u001a\t\u0003-]K!\u0001W\f\u0003\u000f\t{w\u000e\\3b]\"\u0012AA\u0017\t\u00037zk\u0011\u0001\u0018\u0006\u0003;2\t!\"\u00198o_R\fG/[8o\u0013\tyFL\u0001\u0007FqB,'/[7f]R\fG\u000eK\u0002\u0005C\u0012\u0004\"a\u00172\n\u0005\rd&!B*j]\u000e,\u0017%A3\u0002\u000bIrCG\f\u0019\u0002/5\f\u0007\u000fU1si&$\u0018n\u001c8tI\u0011,g-Y;mi\u0012\u0012TC\u00015s+\u0005I'F\u0001,kW\u0005Y\u0007C\u00017q\u001b\u0005i'B\u00018p\u0003%)hn\u00195fG.,GM\u0003\u0002^/%\u0011\u0011/\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002 \u0006\u0005\u0004\u0019\u0013AF7baB\u000b'\u000f^5uS>t7oV5uQ&sG-\u001a=\u0016\u0005ULH\u0003\u0002<~\u0003\u0017!\"a\u001e>\u0011\u0007qi\u0002\u0010\u0005\u0002!s\u0012)aH\u0002b\u0001G!91PBA\u0001\u0002\ba\u0018AC3wS\u0012,gnY3%gA\u0019Af\f=\t\u000b\r3\u0001\u0019\u0001@\u0011\u000fYy\u00181A$\u0002\n%\u0019\u0011\u0011A\f\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004c\u0001\f\u0002\u0006%\u0019\u0011qA\f\u0003\u0007%sG\u000fE\u0002I!bDq!\u0016\u0004\u0011\u0002\u0003\u0007a\u000b\u000b\u0002\u00075\"\"a!YA\tC\t\t\u0019\"A\u00034]Ar\u0003'\u0001\u0011nCB\u0004\u0016M\u001d;ji&|gn],ji\"Le\u000eZ3yI\u0011,g-Y;mi\u0012\u0012Tc\u00015\u0002\u001a\u0011)ah\u0002b\u0001G\u0005QR.\u00199QCJ$\u0018\u000e^5p]N<\u0016\u000e\u001e5Fm\u0006dW/\u0019;peV!\u0011qDA\u0014)\u0011\t\t#!\r\u0015\t\u0005\r\u00121\u0006\t\u00059u\t)\u0003E\u0002!\u0003O!a!!\u000b\t\u0005\u0004\u0019#!A+\t\u0013\u00055\u0002\"!AA\u0004\u0005=\u0012AC3wS\u0012,gnY3%iA!AfLA\u0013\u0011\u001d\t\u0019\u0004\u0003a\u0001\u0003k\t\u0001#\u001a<bYV\fGo\u001c:GC\u000e$xN]=\u0011\u000f\u0005]\u0012\u0011H\u0010\u0002&5\tA\"C\u0002\u0002<1\u0011\u0011\u0004U1si&$\u0018n\u001c8Fm\u0006dW/\u0019;pe\u001a\u000b7\r^8ss\"\u001a\u0001\"a\u0010\u0011\u0007m\u000b\t%C\u0002\u0002Dq\u0013A\u0002R3wK2|\u0007/\u001a:Ba&DC\u0001C1\u0002H\u0005\u0012\u0011\u0011J\u0001\u0006g9*d\u0006\r\u0015\u0003\u0001iC3\u0001A1e\u0001"
)
public class RDDBarrier {
   private final RDD rdd;
   private final ClassTag evidence$1;

   @Experimental
   public RDD mapPartitions(final Function1 f, final boolean preservesPartitioning, final ClassTag evidence$2) {
      return (RDD)this.rdd.withScope(() -> {
         SparkContext qual$1 = this.rdd.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         Function1 cleanedF = (Function1)qual$1.clean(f, x$2);
         return new MapPartitionsRDD(this.rdd, (context, index, iter) -> $anonfun$mapPartitions$2(cleanedF, context, BoxesRunTime.unboxToInt(index), iter), preservesPartitioning, true, MapPartitionsRDD$.MODULE$.$lessinit$greater$default$5(), evidence$2, this.evidence$1);
      });
   }

   public boolean mapPartitions$default$2() {
      return false;
   }

   @Experimental
   public RDD mapPartitionsWithIndex(final Function2 f, final boolean preservesPartitioning, final ClassTag evidence$3) {
      return (RDD)this.rdd.withScope(() -> {
         SparkContext qual$1 = this.rdd.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         Function2 cleanedF = (Function2)qual$1.clean(f, x$2);
         return new MapPartitionsRDD(this.rdd, (x$1, index, iter) -> $anonfun$mapPartitionsWithIndex$2(cleanedF, x$1, BoxesRunTime.unboxToInt(index), iter), preservesPartitioning, true, MapPartitionsRDD$.MODULE$.$lessinit$greater$default$5(), evidence$3, this.evidence$1);
      });
   }

   public boolean mapPartitionsWithIndex$default$2() {
      return false;
   }

   @DeveloperApi
   public RDD mapPartitionsWithEvaluator(final PartitionEvaluatorFactory evaluatorFactory, final ClassTag evidence$4) {
      return (RDD)this.rdd.withScope(() -> new MapPartitionsWithEvaluatorRDD(this.rdd, evaluatorFactory, this.evidence$1, evidence$4));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$mapPartitions$2(final Function1 cleanedF$1, final TaskContext context, final int index, final Iterator iter) {
      return (Iterator)cleanedF$1.apply(iter);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$mapPartitionsWithIndex$2(final Function2 cleanedF$2, final TaskContext x$1, final int index, final Iterator iter) {
      return (Iterator)cleanedF$2.apply(BoxesRunTime.boxToInteger(index), iter);
   }

   public RDDBarrier(final RDD rdd, final ClassTag evidence$1) {
      this.rdd = rdd;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
