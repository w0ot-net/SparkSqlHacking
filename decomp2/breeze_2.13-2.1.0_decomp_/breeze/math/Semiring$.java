package breeze.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Semiring$ implements Serializable {
   public static final Semiring$ MODULE$ = new Semiring$();

   public Semiring semiringD() {
      return Ring$.MODULE$.ringD();
   }

   public Semiring semiringFloat() {
      return Ring$.MODULE$.ringFloat();
   }

   public Semiring semiringInt() {
      return Ring$.MODULE$.ringInt();
   }

   public Semiring semiringLong() {
      return Ring$.MODULE$.ringLong();
   }

   public Semiring semiringBigInt() {
      return Ring$.MODULE$.ringBigInt();
   }

   public Semiring semiringShort() {
      return Ring$.MODULE$.ringShort();
   }

   public Semiring semiringCmplx() {
      return Ring$.MODULE$.ringComplex();
   }

   public Semiring semiringFromRing(final Ring ring) {
      return ring;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Semiring$.class);
   }

   private Semiring$() {
   }
}
