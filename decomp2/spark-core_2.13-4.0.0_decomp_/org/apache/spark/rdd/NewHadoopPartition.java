package org.apache.spark.rdd;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.spark.Partition;
import org.apache.spark.SerializableWritable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3QAC\u0006\u0001\u001bMA\u0001B\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\tG\u0001\u0011)\u0019!C\u0001I!AQ\u0005\u0001B\u0001B\u0003%\u0001\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003(\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u001d\t\u0005A1A\u0005\u0002\tCaA\u0012\u0001!\u0002\u0013\u0019\u0005\"B$\u0001\t\u0003B\u0005\"B%\u0001\t\u0003R%A\u0005(fo\"\u000bGm\\8q!\u0006\u0014H/\u001b;j_:T!\u0001D\u0007\u0002\u0007I$GM\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'\r\u0001AC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005maR\"A\u0007\n\u0005ui!!\u0003)beRLG/[8o\u0003\u0015\u0011H\rZ%e\u0007\u0001\u0001\"!F\u0011\n\u0005\t2\"aA%oi\u0006)\u0011N\u001c3fqV\t\u0001%\u0001\u0004j]\u0012,\u0007\u0010I\u0001\te\u0006<8\u000b\u001d7jiJ\u0019\u0001F\u000b\u001a\u0007\t%\u0002\u0001a\n\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003WAj\u0011\u0001\f\u0006\u0003[9\n\u0011\"\\1qe\u0016$WoY3\u000b\u0005=z\u0011A\u00025bI>|\u0007/\u0003\u00022Y\tQ\u0011J\u001c9viN\u0003H.\u001b;\u0011\u0005M2T\"\u0001\u001b\u000b\u0005Ur\u0013AA5p\u0013\t9DG\u0001\u0005Xe&$\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q!!\bP\u001f?!\tY\u0004!D\u0001\f\u0011\u0015qR\u00011\u0001!\u0011\u0015\u0019S\u00011\u0001!\u0011\u00151S\u00011\u0001@%\r\u0001%F\r\u0004\u0005S\u0001\u0001q(A\ftKJL\u0017\r\\5{C\ndW\rS1e_>\u00048\u000b\u001d7jiV\t1\tE\u0002\u001c\t\u001eJ!!R\u0007\u0003)M+'/[1mSj\f'\r\\3Xe&$\u0018M\u00197f\u0003a\u0019XM]5bY&T\u0018M\u00197f\u0011\u0006$wn\u001c9Ta2LG\u000fI\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001%\u0001\u0004fcV\fGn\u001d\u000b\u0003\u0017:\u0003\"!\u0006'\n\u000553\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u001f&\u0001\r\u0001U\u0001\u0006_RDWM\u001d\t\u0003+EK!A\u0015\f\u0003\u0007\u0005s\u0017\u0010"
)
public class NewHadoopPartition implements Partition {
   private final int rddId;
   private final int index;
   private final SerializableWritable serializableHadoopSplit;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int index() {
      return this.index;
   }

   public SerializableWritable serializableHadoopSplit() {
      return this.serializableHadoopSplit;
   }

   public int hashCode() {
      return 31 * (31 + this.rddId) + this.index();
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public NewHadoopPartition(final int rddId, final int index, final InputSplit rawSplit) {
      this.rddId = rddId;
      this.index = index;
      Partition.$init$(this);
      this.serializableHadoopSplit = new SerializableWritable((Writable)rawSplit);
   }
}
