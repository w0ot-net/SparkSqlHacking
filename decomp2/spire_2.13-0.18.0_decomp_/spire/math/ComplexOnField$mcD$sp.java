package spire.math;

import spire.algebra.FieldAssociativeAlgebra$mcD$sp;

public interface ComplexOnField$mcD$sp extends ComplexOnField, FieldAssociativeAlgebra$mcD$sp, ComplexOnCRing$mcD$sp {
   // $FF: synthetic method
   static Complex fromDouble$(final ComplexOnField$mcD$sp $this, final double n) {
      return $this.fromDouble(n);
   }

   default Complex fromDouble(final double n) {
      return this.fromDouble$mcD$sp(n);
   }

   // $FF: synthetic method
   static Complex fromDouble$mcD$sp$(final ComplexOnField$mcD$sp $this, final double n) {
      return $this.fromDouble$mcD$sp(n);
   }

   default Complex fromDouble$mcD$sp(final double n) {
      return Complex$.MODULE$.apply$mDc$sp(this.scalar$mcD$sp().fromDouble$mcD$sp(n), this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex div$(final ComplexOnField$mcD$sp $this, final Complex a, final Complex b) {
      return $this.div(a, b);
   }

   default Complex div(final Complex a, final Complex b) {
      return this.div$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Complex div$mcD$sp$(final ComplexOnField$mcD$sp $this, final Complex a, final Complex b) {
      return $this.div$mcD$sp(a, b);
   }

   default Complex div$mcD$sp(final Complex a, final Complex b) {
      return a.$div$mcD$sp(b, this.scalar$mcD$sp(), this.order$mcD$sp(), this.signed$mcD$sp());
   }
}
