package org.apache.spark.mllib.rdd;

import org.apache.spark.Partition;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3Q\u0001D\u0007\u0001\u001f]A\u0001\u0002\r\u0001\u0003\u0006\u0004%\t!\r\u0005\tk\u0001\u0011\t\u0011)A\u0005e!Aa\u0007\u0001BC\u0002\u0013\u0005q\u0007\u0003\u00059\u0001\t\u0005\t\u0015!\u0003 \u0011!I\u0004A!b\u0001\n\u0003Q\u0004\u0002C%\u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u0011)\u0003!Q1A\u0005\u0002EB\u0001b\u0013\u0001\u0003\u0002\u0003\u0006IA\r\u0005\u0006\u0019\u0002!\t!\u0014\u0005\b)\u0002\u0011\r\u0011\"\u00112\u0011\u0019)\u0006\u0001)A\u0005e\t\u00192\u000b\\5eS:<'\u000b\u0012#QCJ$\u0018\u000e^5p]*\u0011abD\u0001\u0004e\u0012$'B\u0001\t\u0012\u0003\u0015iG\u000e\\5c\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<WC\u0001\rA'\u0011\u0001\u0011dH\u0012\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g!\t\u0001\u0013%D\u0001\u0012\u0013\t\u0011\u0013CA\u0005QCJ$\u0018\u000e^5p]B\u0011A%\f\b\u0003K-r!A\n\u0016\u000e\u0003\u001dR!\u0001K\u0015\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001H\u0005\u0003Ym\tq\u0001]1dW\u0006<W-\u0003\u0002/_\ta1+\u001a:jC2L'0\u00192mK*\u0011AfG\u0001\u0004S\u0012DX#\u0001\u001a\u0011\u0005i\u0019\u0014B\u0001\u001b\u001c\u0005\rIe\u000e^\u0001\u0005S\u0012D\b%\u0001\u0003qe\u00164X#A\u0010\u0002\u000bA\u0014XM\u001e\u0011\u0002\tQ\f\u0017\u000e\\\u000b\u0002wA\u0019A\u0005\u0010 \n\u0005uz#aA*fcB\u0011q\b\u0011\u0007\u0001\t\u0015\t\u0005A1\u0001C\u0005\u0005!\u0016CA\"G!\tQB)\u0003\u0002F7\t9aj\u001c;iS:<\u0007C\u0001\u000eH\u0013\tA5DA\u0002B]f\fQ\u0001^1jY\u0002\naa\u001c4gg\u0016$\u0018aB8gMN,G\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b9\u0003\u0016KU*\u0011\u0007=\u0003a(D\u0001\u000e\u0011\u0015\u0001\u0014\u00021\u00013\u0011\u00151\u0014\u00021\u0001 \u0011\u0015I\u0014\u00021\u0001<\u0011\u0015Q\u0015\u00021\u00013\u0003\u0015Ig\u000eZ3y\u0003\u0019Ig\u000eZ3yA\u0001"
)
public class SlidingRDDPartition implements Partition {
   private final int idx;
   private final Partition prev;
   private final Seq tail;
   private final int offset;
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

   public int idx() {
      return this.idx;
   }

   public Partition prev() {
      return this.prev;
   }

   public Seq tail() {
      return this.tail;
   }

   public int offset() {
      return this.offset;
   }

   public int index() {
      return this.index;
   }

   public SlidingRDDPartition(final int idx, final Partition prev, final Seq tail, final int offset) {
      this.idx = idx;
      this.prev = prev;
      this.tail = tail;
      this.offset = offset;
      Partition.$init$(this);
      this.index = idx;
   }
}
