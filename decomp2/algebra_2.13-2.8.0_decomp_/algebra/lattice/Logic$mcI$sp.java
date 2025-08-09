package algebra.lattice;

public interface Logic$mcI$sp extends Logic, BoundedDistributiveLattice$mcI$sp {
   // $FF: synthetic method
   static int xor$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.xor(a, b);
   }

   default int xor(final int a, final int b) {
      return this.xor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int xor$mcI$sp$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.xor$mcI$sp(a, b);
   }

   default int xor$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(this.and$mcI$sp(a, this.not$mcI$sp(b)), this.and$mcI$sp(this.not$mcI$sp(a), b));
   }

   // $FF: synthetic method
   static int nand$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.nand(a, b);
   }

   default int nand(final int a, final int b) {
      return this.nand$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nand$mcI$sp$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.nand$mcI$sp(a, b);
   }

   default int nand$mcI$sp(final int a, final int b) {
      return this.not$mcI$sp(this.and$mcI$sp(a, b));
   }

   // $FF: synthetic method
   static int nor$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.nor(a, b);
   }

   default int nor(final int a, final int b) {
      return this.nor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nor$mcI$sp$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.nor$mcI$sp(a, b);
   }

   default int nor$mcI$sp(final int a, final int b) {
      return this.not$mcI$sp(this.or$mcI$sp(a, b));
   }

   // $FF: synthetic method
   static int nxor$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.nxor(a, b);
   }

   default int nxor(final int a, final int b) {
      return this.nxor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nxor$mcI$sp$(final Logic$mcI$sp $this, final int a, final int b) {
      return $this.nxor$mcI$sp(a, b);
   }

   default int nxor$mcI$sp(final int a, final int b) {
      return this.not$mcI$sp(this.xor$mcI$sp(a, b));
   }
}
