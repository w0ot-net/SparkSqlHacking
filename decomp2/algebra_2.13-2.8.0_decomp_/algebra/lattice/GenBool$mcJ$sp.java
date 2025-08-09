package algebra.lattice;

public interface GenBool$mcJ$sp extends GenBool, BoundedJoinSemilattice$mcJ$sp, DistributiveLattice$mcJ$sp {
   // $FF: synthetic method
   static long meet$(final GenBool$mcJ$sp $this, final long a, final long b) {
      return $this.meet(a, b);
   }

   default long meet(final long a, final long b) {
      return this.meet$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long meet$mcJ$sp$(final GenBool$mcJ$sp $this, final long a, final long b) {
      return $this.meet$mcJ$sp(a, b);
   }

   default long meet$mcJ$sp(final long a, final long b) {
      return this.and$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long join$(final GenBool$mcJ$sp $this, final long a, final long b) {
      return $this.join(a, b);
   }

   default long join(final long a, final long b) {
      return this.join$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long join$mcJ$sp$(final GenBool$mcJ$sp $this, final long a, final long b) {
      return $this.join$mcJ$sp(a, b);
   }

   default long join$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long xor$(final GenBool$mcJ$sp $this, final long a, final long b) {
      return $this.xor(a, b);
   }

   default long xor(final long a, final long b) {
      return this.xor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long xor$mcJ$sp$(final GenBool$mcJ$sp $this, final long a, final long b) {
      return $this.xor$mcJ$sp(a, b);
   }

   default long xor$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(this.without$mcJ$sp(a, b), this.without$mcJ$sp(b, a));
   }
}
