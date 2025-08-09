package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.util.Utils$;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da!\u0002\n\u0014\u0001UY\u0002\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011-\u0002!Q1A\u0005\n1B\u0001B\u000e\u0001\u0003\u0002\u0003\u0006I!\f\u0005\t\u0005\u0002\u0011)\u0019!C\u0005\u0007\"A\u0011\n\u0001B\u0001B\u0003%A\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003)\u0011!a\u0005A!A!\u0002\u0013A\u0003\"B'\u0001\t\u0003q\u0005bB/\u0001\u0001\u0004%\tA\u0018\u0005\b?\u0002\u0001\r\u0011\"\u0001a\u0011\u00191\u0007\u0001)Q\u0005E!9q\r\u0001a\u0001\n\u0003q\u0006b\u00025\u0001\u0001\u0004%\t!\u001b\u0005\u0007W\u0002\u0001\u000b\u0015\u0002\u0012\t\u000f1\u0004!\u0019!C![\"1a\u000e\u0001Q\u0001\n!BQa\u001c\u0001\u0005\nA\u0014!cQ1si\u0016\u001c\u0018.\u00198QCJ$\u0018\u000e^5p]*\u0011A#F\u0001\u0004e\u0012$'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0014\u0007\u0001a\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VM\u001a\t\u0003G\u0011j\u0011!F\u0005\u0003KU\u0011\u0011\u0002U1si&$\u0018n\u001c8\u0002\u0007%$\u0007p\u0001\u0001\u0011\u0005uI\u0013B\u0001\u0016\u001f\u0005\rIe\u000e^\u0001\u0005e\u0012$\u0017'F\u0001.a\tqC\u0007E\u00020aIj\u0011aE\u0005\u0003cM\u00111A\u0015#E!\t\u0019D\u0007\u0004\u0001\u0005\u0013U\u001a\u0011\u0011!A\u0001\u0006\u0003Y$aA0%c\u0005)!\u000f\u001a32A!\u00121\u0001\u000f\t\u0003;eJ!A\u000f\u0010\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018C\u0001\u001f@!\tiR(\u0003\u0002?=\t9aj\u001c;iS:<\u0007CA\u000fA\u0013\t\teDA\u0002B]f\fAA\u001d3eeU\tA\t\r\u0002F\u000fB\u0019q\u0006\r$\u0011\u0005M:E!\u0003%\u0006\u0003\u0003\u0005\tQ!\u0001<\u0005\ryFEM\u0001\u0006e\u0012$'\u0007\t\u0015\u0003\u000ba\nqa]\u0019J]\u0012,\u00070A\u0004te%sG-\u001a=\u0002\rqJg.\u001b;?)\u0019y\u0005+\u0015,\\9B\u0011q\u0006\u0001\u0005\u0006M!\u0001\r\u0001\u000b\u0005\u0006W!\u0001\rA\u0015\u0019\u0003'V\u00032a\f\u0019U!\t\u0019T\u000bB\u00056#\u0006\u0005\t\u0011!B\u0001w!)!\t\u0003a\u0001/B\u0012\u0001L\u0017\t\u0004_AJ\u0006CA\u001a[\t%Ae+!A\u0001\u0002\u000b\u00051\bC\u0003L\u0011\u0001\u0007\u0001\u0006C\u0003M\u0011\u0001\u0007\u0001&\u0001\u0002tcU\t!%\u0001\u0004tc}#S-\u001d\u000b\u0003C\u0012\u0004\"!\b2\n\u0005\rt\"\u0001B+oSRDq!\u001a\u0006\u0002\u0002\u0003\u0007!%A\u0002yIE\n1a]\u0019!\u0003\t\u0019('\u0001\u0004te}#S-\u001d\u000b\u0003C*Dq!Z\u0007\u0002\u0002\u0003\u0007!%A\u0002te\u0001\nQ!\u001b8eKb,\u0012\u0001K\u0001\u0007S:$W\r\u001f\u0011\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0003CFDQA]\tA\u0002M\f1a\\8t!\t!\u00180D\u0001v\u0015\t1x/\u0001\u0002j_*\t\u00010\u0001\u0003kCZ\f\u0017B\u0001>v\u0005Iy%M[3di>+H\u000f];u'R\u0014X-Y7)\tEa\u0018Q\u0001\t\u0004;u|\u0018B\u0001@\u001f\u0005\u0019!\bN]8xgB\u0019A/!\u0001\n\u0007\u0005\rQOA\u0006J\u001f\u0016C8-\u001a9uS>t7%A@"
)
public class CartesianPartition implements Partition {
   private final transient RDD rdd1;
   private final transient RDD rdd2;
   private final int s1Index;
   private final int s2Index;
   private Partition s1;
   private Partition s2;
   private final int index;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   private RDD rdd1() {
      return this.rdd1;
   }

   private RDD rdd2() {
      return this.rdd2;
   }

   public Partition s1() {
      return this.s1;
   }

   public void s1_$eq(final Partition x$1) {
      this.s1 = x$1;
   }

   public Partition s2() {
      return this.s2;
   }

   public void s2_$eq(final Partition x$1) {
      this.s2 = x$1;
   }

   public int index() {
      return this.index;
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.s1_$eq(this.rdd1().partitions()[this.s1Index]);
         this.s2_$eq(this.rdd2().partitions()[this.s2Index]);
         oos.defaultWriteObject();
      });
   }

   public CartesianPartition(final int idx, final RDD rdd1, final RDD rdd2, final int s1Index, final int s2Index) {
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      this.s1Index = s1Index;
      this.s2Index = s2Index;
      Partition.$init$(this);
      this.s1 = rdd1.partitions()[s1Index];
      this.s2 = rdd2.partitions()[s2Index];
      this.index = idx;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
