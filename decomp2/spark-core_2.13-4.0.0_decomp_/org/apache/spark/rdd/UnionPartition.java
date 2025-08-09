package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.util.Utils$;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!B\t\u0013\u0001QQ\u0002\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011M\u0001!Q1A\u0005\n-B\u0001b\u000f\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\t\u0001\u0002\u0011)\u0019!C\u0001\u0003\"A!\t\u0001B\u0001B\u0003%\u0001\u0006\u0003\u0005D\u0001\t\u0015\r\u0011\"\u0003B\u0011!!\u0005A!A!\u0002\u0013A\u0003\u0002\u0003$\u0001\u0005\u0007\u0005\u000b1B$\t\u000b5\u0003A\u0011\u0001(\t\u000fY\u0003\u0001\u0019!C\u0001/\"9\u0001\f\u0001a\u0001\n\u0003I\u0006BB0\u0001A\u0003&!\u0005C\u0003a\u0001\u0011\u0005\u0011\rC\u0004w\u0001\t\u0007I\u0011I!\t\r]\u0004\u0001\u0015!\u0003)\u0011\u0015A\b\u0001\"\u0003z\u00059)f.[8o!\u0006\u0014H/\u001b;j_:T!a\u0005\u000b\u0002\u0007I$GM\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h+\tY\"gE\u0002\u00019\t\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0007CA\u0012%\u001b\u0005!\u0012BA\u0013\u0015\u0005%\u0001\u0016M\u001d;ji&|g.A\u0002jIb\u001c\u0001\u0001\u0005\u0002\u001eS%\u0011!F\b\u0002\u0004\u0013:$X#\u0001\u0017\u0011\u00075r\u0003'D\u0001\u0013\u0013\ty#CA\u0002S\t\u0012\u0003\"!\r\u001a\r\u0001\u0011)1\u0007\u0001b\u0001i\t\tA+\u0005\u00026qA\u0011QDN\u0005\u0003oy\u0011qAT8uQ&tw\r\u0005\u0002\u001es%\u0011!H\b\u0002\u0004\u0003:L\u0018\u0001\u0002:eI\u0002B#aA\u001f\u0011\u0005uq\u0014BA \u001f\u0005%!(/\u00198tS\u0016tG/\u0001\bqCJ,g\u000e\u001e*eI&sG-\u001a=\u0016\u0003!\nq\u0002]1sK:$(\u000b\u001a3J]\u0012,\u0007\u0010I\u0001\u0018a\u0006\u0014XM\u001c;SI\u0012\u0004\u0016M\u001d;ji&|g.\u00138eKb\f\u0001\u0004]1sK:$(\u000b\u001a3QCJ$\u0018\u000e^5p]&sG-\u001a=!Q\t9Q(\u0001\u0006fm&$WM\\2fIE\u00022\u0001S&1\u001b\u0005I%B\u0001&\u001f\u0003\u001d\u0011XM\u001a7fGRL!\u0001T%\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtD#B(S'R+FC\u0001)R!\ri\u0003\u0001\r\u0005\u0006\r&\u0001\u001da\u0012\u0005\u0006M%\u0001\r\u0001\u000b\u0005\u0006'%\u0001\r\u0001\f\u0005\u0006\u0001&\u0001\r\u0001\u000b\u0005\u0006\u0007&\u0001\r\u0001K\u0001\u0010a\u0006\u0014XM\u001c;QCJ$\u0018\u000e^5p]V\t!%A\nqCJ,g\u000e\u001e)beRLG/[8o?\u0012*\u0017\u000f\u0006\u0002[;B\u0011QdW\u0005\u00039z\u0011A!\u00168ji\"9alCA\u0001\u0002\u0004\u0011\u0013a\u0001=%c\u0005\u0001\u0002/\u0019:f]R\u0004\u0016M\u001d;ji&|g\u000eI\u0001\u0013aJ,g-\u001a:sK\u0012dunY1uS>t7\u000fF\u0001c!\r\u00197N\u001c\b\u0003I&t!!\u001a5\u000e\u0003\u0019T!aZ\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005y\u0012B\u00016\u001f\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001\\7\u0003\u0007M+\u0017O\u0003\u0002k=A\u0011qn\u001d\b\u0003aF\u0004\"!\u001a\u0010\n\u0005It\u0012A\u0002)sK\u0012,g-\u0003\u0002uk\n11\u000b\u001e:j]\u001eT!A\u001d\u0010\u0002\u000b%tG-\u001a=\u0002\r%tG-\u001a=!\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\u0005iS\b\"B>\u0011\u0001\u0004a\u0018aA8pgB\u0019Q0!\u0002\u000e\u0003yT1a`A\u0001\u0003\tIwN\u0003\u0002\u0002\u0004\u0005!!.\u0019<b\u0013\r\t9A \u0002\u0013\u001f\nTWm\u0019;PkR\u0004X\u000f^*ue\u0016\fW\u000eK\u0003\u0011\u0003\u0017\t9\u0002E\u0003\u001e\u0003\u001b\t\t\"C\u0002\u0002\u0010y\u0011a\u0001\u001e5s_^\u001c\bcA?\u0002\u0014%\u0019\u0011Q\u0003@\u0003\u0017%{U\t_2faRLwN\\\u0012\u0003\u0003#\u0001"
)
public class UnionPartition implements Partition {
   private final transient RDD rdd;
   private final int parentRddIndex;
   private final transient int parentRddPartitionIndex;
   private Partition parentPartition;
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

   private RDD rdd() {
      return this.rdd;
   }

   public int parentRddIndex() {
      return this.parentRddIndex;
   }

   private int parentRddPartitionIndex() {
      return this.parentRddPartitionIndex;
   }

   public Partition parentPartition() {
      return this.parentPartition;
   }

   public void parentPartition_$eq(final Partition x$1) {
      this.parentPartition = x$1;
   }

   public Seq preferredLocations() {
      return this.rdd().preferredLocations(this.parentPartition());
   }

   public int index() {
      return this.index;
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.parentPartition_$eq(this.rdd().partitions()[this.parentRddPartitionIndex()]);
         oos.defaultWriteObject();
      });
   }

   public UnionPartition(final int idx, final RDD rdd, final int parentRddIndex, final int parentRddPartitionIndex, final ClassTag evidence$1) {
      this.rdd = rdd;
      this.parentRddIndex = parentRddIndex;
      this.parentRddPartitionIndex = parentRddPartitionIndex;
      Partition.$init$(this);
      this.parentPartition = rdd.partitions()[parentRddPartitionIndex];
      this.index = idx;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
