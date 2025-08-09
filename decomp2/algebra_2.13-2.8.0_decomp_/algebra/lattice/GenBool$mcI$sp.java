package algebra.lattice;

public interface GenBool$mcI$sp extends GenBool, BoundedJoinSemilattice$mcI$sp, DistributiveLattice$mcI$sp {
   // $FF: synthetic method
   static int meet$(final GenBool$mcI$sp $this, final int a, final int b) {
      return $this.meet(a, b);
   }

   default int meet(final int a, final int b) {
      return this.meet$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int meet$mcI$sp$(final GenBool$mcI$sp $this, final int a, final int b) {
      return $this.meet$mcI$sp(a, b);
   }

   default int meet$mcI$sp(final int a, final int b) {
      return this.and$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int join$(final GenBool$mcI$sp $this, final int a, final int b) {
      return $this.join(a, b);
   }

   default int join(final int a, final int b) {
      return this.join$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int join$mcI$sp$(final GenBool$mcI$sp $this, final int a, final int b) {
      return $this.join$mcI$sp(a, b);
   }

   default int join$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int xor$(final GenBool$mcI$sp $this, final int a, final int b) {
      return $this.xor(a, b);
   }

   default int xor(final int a, final int b) {
      return this.xor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int xor$mcI$sp$(final GenBool$mcI$sp $this, final int a, final int b) {
      return $this.xor$mcI$sp(a, b);
   }

   default int xor$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(this.without$mcI$sp(a, b), this.without$mcI$sp(b, a));
   }
}
