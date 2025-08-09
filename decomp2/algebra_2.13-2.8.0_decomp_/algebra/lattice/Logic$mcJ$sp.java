package algebra.lattice;

public interface Logic$mcJ$sp extends Logic, BoundedDistributiveLattice$mcJ$sp {
   // $FF: synthetic method
   static long xor$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.xor(a, b);
   }

   default long xor(final long a, final long b) {
      return this.xor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long xor$mcJ$sp$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.xor$mcJ$sp(a, b);
   }

   default long xor$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(this.and$mcJ$sp(a, this.not$mcJ$sp(b)), this.and$mcJ$sp(this.not$mcJ$sp(a), b));
   }

   // $FF: synthetic method
   static long nand$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.nand(a, b);
   }

   default long nand(final long a, final long b) {
      return this.nand$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nand$mcJ$sp$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.nand$mcJ$sp(a, b);
   }

   default long nand$mcJ$sp(final long a, final long b) {
      return this.not$mcJ$sp(this.and$mcJ$sp(a, b));
   }

   // $FF: synthetic method
   static long nor$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.nor(a, b);
   }

   default long nor(final long a, final long b) {
      return this.nor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nor$mcJ$sp$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.nor$mcJ$sp(a, b);
   }

   default long nor$mcJ$sp(final long a, final long b) {
      return this.not$mcJ$sp(this.or$mcJ$sp(a, b));
   }

   // $FF: synthetic method
   static long nxor$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.nxor(a, b);
   }

   default long nxor(final long a, final long b) {
      return this.nxor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nxor$mcJ$sp$(final Logic$mcJ$sp $this, final long a, final long b) {
      return $this.nxor$mcJ$sp(a, b);
   }

   default long nxor$mcJ$sp(final long a, final long b) {
      return this.not$mcJ$sp(this.xor$mcJ$sp(a, b));
   }
}
