package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcJ$sp extends BinaryMerge.ArrayBinaryMerge {
   public final long[] a$mcJ$sp;
   public final long[] b$mcJ$sp;
   public final Order o$mcJ$sp;
   public final long[] r$mcJ$sp;
   private final ClassTag c;

   public long[] r$mcJ$sp() {
      return this.r$mcJ$sp;
   }

   public long[] r() {
      return this.r$mcJ$sp();
   }

   public long[] result() {
      return this.result$mcJ$sp();
   }

   public long[] result$mcJ$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcJ$sp(final long[] a$mcJ$sp, final long[] b$mcJ$sp, final Order o$mcJ$sp, final ClassTag c) {
      super(a$mcJ$sp, b$mcJ$sp, o$mcJ$sp, c);
      this.a$mcJ$sp = a$mcJ$sp;
      this.b$mcJ$sp = b$mcJ$sp;
      this.o$mcJ$sp = o$mcJ$sp;
      this.c = c;
      this.r$mcJ$sp = (long[]).MODULE$.ofDim(a$mcJ$sp.length + b$mcJ$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
