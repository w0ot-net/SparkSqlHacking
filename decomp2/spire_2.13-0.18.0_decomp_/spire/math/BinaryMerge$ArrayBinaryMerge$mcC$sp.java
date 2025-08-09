package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcC$sp extends BinaryMerge.ArrayBinaryMerge {
   public final char[] a$mcC$sp;
   public final char[] b$mcC$sp;
   public final Order o$mcC$sp;
   public final char[] r$mcC$sp;
   private final ClassTag c;

   public char[] r$mcC$sp() {
      return this.r$mcC$sp;
   }

   public char[] r() {
      return this.r$mcC$sp();
   }

   public char[] result() {
      return this.result$mcC$sp();
   }

   public char[] result$mcC$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcC$sp(final char[] a$mcC$sp, final char[] b$mcC$sp, final Order o$mcC$sp, final ClassTag c) {
      super(a$mcC$sp, b$mcC$sp, o$mcC$sp, c);
      this.a$mcC$sp = a$mcC$sp;
      this.b$mcC$sp = b$mcC$sp;
      this.o$mcC$sp = o$mcC$sp;
      this.c = c;
      this.r$mcC$sp = (char[]).MODULE$.ofDim(a$mcC$sp.length + b$mcC$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
