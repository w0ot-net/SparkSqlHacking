package spire.math;

import cats.kernel.Order;
import scala.Array.;
import scala.reflect.ClassTag;

public class BinaryMerge$ArrayBinaryMerge$mcB$sp extends BinaryMerge.ArrayBinaryMerge {
   public final byte[] a$mcB$sp;
   public final byte[] b$mcB$sp;
   public final Order o$mcB$sp;
   public final byte[] r$mcB$sp;
   private final ClassTag c;

   public byte[] r$mcB$sp() {
      return this.r$mcB$sp;
   }

   public byte[] r() {
      return this.r$mcB$sp();
   }

   public byte[] result() {
      return this.result$mcB$sp();
   }

   public byte[] result$mcB$sp() {
      return this.r();
   }

   public boolean specInstance$() {
      return true;
   }

   public BinaryMerge$ArrayBinaryMerge$mcB$sp(final byte[] a$mcB$sp, final byte[] b$mcB$sp, final Order o$mcB$sp, final ClassTag c) {
      super(a$mcB$sp, b$mcB$sp, o$mcB$sp, c);
      this.a$mcB$sp = a$mcB$sp;
      this.b$mcB$sp = b$mcB$sp;
      this.o$mcB$sp = o$mcB$sp;
      this.c = c;
      this.r$mcB$sp = (byte[]).MODULE$.ofDim(a$mcB$sp.length + b$mcB$sp.length, c);
      this.spire$math$BinaryMerge$ArrayBinaryMerge$$ri = 0;
      this.merge0(0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.a), 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.b));
   }
}
