package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcF$sp extends BinaryMerge.ArrayBinaryMerge {
   public final float[] a$mcF$sp;
   public final float[] b$mcF$sp;
   public final Order o$mcF$sp;
   public final float[] r$mcF$sp;
   private final ClassTag c;

   public float[] r$mcF$sp() {
      return this.r$mcF$sp;
   }

   public float[] r() {
      return this.r$mcF$sp();
   }

   public float[] result() {
      return this.result$mcF$sp();
   }

   public float[] result$mcF$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcF$sp(final float[] a$mcF$sp, final float[] b$mcF$sp, final Order o$mcF$sp, final ClassTag c) {
      super(a$mcF$sp, b$mcF$sp, o$mcF$sp, c);
      this.a$mcF$sp = a$mcF$sp;
      this.b$mcF$sp = b$mcF$sp;
      this.o$mcF$sp = o$mcF$sp;
      this.c = c;
      this.r$mcF$sp = (float[]).MODULE$.ofDim(a$mcF$sp.length + b$mcF$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
