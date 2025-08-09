package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public class BinaryMerge$ArrayBinaryMerge$mcV$sp extends BinaryMerge.ArrayBinaryMerge {
   public final BoxedUnit[] a$mcV$sp;
   public final BoxedUnit[] b$mcV$sp;
   public final Order o$mcV$sp;
   public final BoxedUnit[] r$mcV$sp;
   private final ClassTag c;

   public BoxedUnit[] r$mcV$sp() {
      return this.r$mcV$sp;
   }

   public BoxedUnit[] r() {
      return this.r$mcV$sp();
   }

   public BoxedUnit[] result() {
      return this.result$mcV$sp();
   }

   public BoxedUnit[] result$mcV$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcV$sp(final BoxedUnit[] a$mcV$sp, final BoxedUnit[] b$mcV$sp, final Order o$mcV$sp, final ClassTag c) {
      super(a$mcV$sp, b$mcV$sp, o$mcV$sp, c);
      this.a$mcV$sp = a$mcV$sp;
      this.b$mcV$sp = b$mcV$sp;
      this.o$mcV$sp = o$mcV$sp;
      this.c = c;
      this.r$mcV$sp = (BoxedUnit[]).MODULE$.ofDim(a$mcV$sp.length + b$mcV$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
