package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcI$sp extends BinaryMerge.ArrayBinaryMerge {
   public final int[] a$mcI$sp;
   public final int[] b$mcI$sp;
   public final Order o$mcI$sp;
   public final int[] r$mcI$sp;
   private final ClassTag c;

   public int[] r$mcI$sp() {
      return this.r$mcI$sp;
   }

   public int[] r() {
      return this.r$mcI$sp();
   }

   public int[] result() {
      return this.result$mcI$sp();
   }

   public int[] result$mcI$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcI$sp(final int[] a$mcI$sp, final int[] b$mcI$sp, final Order o$mcI$sp, final ClassTag c) {
      super(a$mcI$sp, b$mcI$sp, o$mcI$sp, c);
      this.a$mcI$sp = a$mcI$sp;
      this.b$mcI$sp = b$mcI$sp;
      this.o$mcI$sp = o$mcI$sp;
      this.c = c;
      this.r$mcI$sp = (int[]).MODULE$.ofDim(a$mcI$sp.length + b$mcI$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
