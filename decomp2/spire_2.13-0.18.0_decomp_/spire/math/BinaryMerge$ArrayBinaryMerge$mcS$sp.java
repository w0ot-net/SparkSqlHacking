package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcS$sp extends BinaryMerge.ArrayBinaryMerge {
   public final short[] a$mcS$sp;
   public final short[] b$mcS$sp;
   public final Order o$mcS$sp;
   public final short[] r$mcS$sp;
   private final ClassTag c;

   public short[] r$mcS$sp() {
      return this.r$mcS$sp;
   }

   public short[] r() {
      return this.r$mcS$sp();
   }

   public short[] result() {
      return this.result$mcS$sp();
   }

   public short[] result$mcS$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcS$sp(final short[] a$mcS$sp, final short[] b$mcS$sp, final Order o$mcS$sp, final ClassTag c) {
      super(a$mcS$sp, b$mcS$sp, o$mcS$sp, c);
      this.a$mcS$sp = a$mcS$sp;
      this.b$mcS$sp = b$mcS$sp;
      this.o$mcS$sp = o$mcS$sp;
      this.c = c;
      this.r$mcS$sp = (short[]).MODULE$.ofDim(a$mcS$sp.length + b$mcS$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
