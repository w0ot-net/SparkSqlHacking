package spire.math;

import spire.algebra.FieldAssociativeAlgebra$mcF$sp;

public interface ComplexOnField$mcF$sp extends ComplexOnField, FieldAssociativeAlgebra$mcF$sp, ComplexOnCRing$mcF$sp {
   // $FF: synthetic method
   static Complex fromDouble$(final ComplexOnField$mcF$sp $this, final double n) {
      return $this.fromDouble(n);
   }

   default Complex fromDouble(final double n) {
      return this.fromDouble$mcF$sp(n);
   }

   // $FF: synthetic method
   static Complex fromDouble$mcF$sp$(final ComplexOnField$mcF$sp $this, final double n) {
      return $this.fromDouble$mcF$sp(n);
   }

   default Complex fromDouble$mcF$sp(final double n) {
      return Complex$.MODULE$.apply$mFc$sp(this.scalar$mcF$sp().fromDouble$mcF$sp(n), this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex div$(final ComplexOnField$mcF$sp $this, final Complex a, final Complex b) {
      return $this.div(a, b);
   }

   default Complex div(final Complex a, final Complex b) {
      return this.div$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Complex div$mcF$sp$(final ComplexOnField$mcF$sp $this, final Complex a, final Complex b) {
      return $this.div$mcF$sp(a, b);
   }

   default Complex div$mcF$sp(final Complex a, final Complex b) {
      return a.$div$mcF$sp(b, this.scalar$mcF$sp(), this.order$mcF$sp(), this.signed$mcF$sp());
   }
}
