package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcZ$sp extends BinaryMerge.ArrayBinaryMerge {
   public final boolean[] a$mcZ$sp;
   public final boolean[] b$mcZ$sp;
   public final Order o$mcZ$sp;
   public final boolean[] r$mcZ$sp;
   private final ClassTag c;

   public boolean[] r$mcZ$sp() {
      return this.r$mcZ$sp;
   }

   public boolean[] r() {
      return this.r$mcZ$sp();
   }

   public boolean[] result() {
      return this.result$mcZ$sp();
   }

   public boolean[] result$mcZ$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcZ$sp(final boolean[] a$mcZ$sp, final boolean[] b$mcZ$sp, final Order o$mcZ$sp, final ClassTag c) {
      super(a$mcZ$sp, b$mcZ$sp, o$mcZ$sp, c);
      this.a$mcZ$sp = a$mcZ$sp;
      this.b$mcZ$sp = b$mcZ$sp;
      this.o$mcZ$sp = o$mcZ$sp;
      this.c = c;
      this.r$mcZ$sp = (boolean[]).MODULE$.ofDim(a$mcZ$sp.length + b$mcZ$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
