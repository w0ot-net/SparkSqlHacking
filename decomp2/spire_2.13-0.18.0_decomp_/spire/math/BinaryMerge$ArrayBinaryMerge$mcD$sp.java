package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcD$sp extends BinaryMerge.ArrayBinaryMerge {
   public final double[] a$mcD$sp;
   public final double[] b$mcD$sp;
   public final Order o$mcD$sp;
   public final double[] r$mcD$sp;
   private final ClassTag c;

   public double[] r$mcD$sp() {
      return this.r$mcD$sp;
   }

   public double[] r() {
      return this.r$mcD$sp();
   }

   public double[] result() {
      return this.result$mcD$sp();
   }

   public double[] result$mcD$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcD$sp(final double[] a$mcD$sp, final double[] b$mcD$sp, final Order o$mcD$sp, final ClassTag c) {
      super(a$mcD$sp, b$mcD$sp, o$mcD$sp, c);
      this.a$mcD$sp = a$mcD$sp;
      this.b$mcD$sp = b$mcD$sp;
      this.o$mcD$sp = o$mcD$sp;
      this.c = c;
      this.r$mcD$sp = (double[]).MODULE$.ofDim(a$mcD$sp.length + b$mcD$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
