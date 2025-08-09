package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112Q\u0001B\u0003\u0001\u000f5A\u0001\u0002\u0007\u0001\u0003\u0006\u0004%\tA\u0007\u0005\t=\u0001\u0011\t\u0011)A\u00057!)q\u0004\u0001C\u0001A\t12\t[3dWB|\u0017N\u001c;S\t\u0012\u0003\u0016M\u001d;ji&|gN\u0003\u0002\u0007\u000f\u0005\u0019!\u000f\u001a3\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c2\u0001\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0011QCF\u0007\u0002\u000f%\u0011qc\u0002\u0002\n!\u0006\u0014H/\u001b;j_:\fQ!\u001b8eKb\u001c\u0001!F\u0001\u001c!\tyA$\u0003\u0002\u001e!\t\u0019\u0011J\u001c;\u0002\r%tG-\u001a=!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011e\t\t\u0003E\u0001i\u0011!\u0002\u0005\u00061\r\u0001\ra\u0007"
)
public class CheckpointRDDPartition implements Partition {
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

   public int index() {
      return this.index;
   }

   public CheckpointRDDPartition(final int index) {
      this.index = index;
      Partition.$init$(this);
   }
}
