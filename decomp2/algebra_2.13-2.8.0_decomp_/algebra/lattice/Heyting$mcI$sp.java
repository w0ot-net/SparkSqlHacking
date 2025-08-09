package algebra.lattice;

public interface Heyting$mcI$sp extends Heyting, BoundedDistributiveLattice$mcI$sp {
   // $FF: synthetic method
   static int meet$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.meet(a, b);
   }

   default int meet(final int a, final int b) {
      return this.meet$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int meet$mcI$sp$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.meet$mcI$sp(a, b);
   }

   default int meet$mcI$sp(final int a, final int b) {
      return this.and$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int join$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.join(a, b);
   }

   default int join(final int a, final int b) {
      return this.join$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int join$mcI$sp$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.join$mcI$sp(a, b);
   }

   default int join$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int xor$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.xor(a, b);
   }

   default int xor(final int a, final int b) {
      return this.xor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int xor$mcI$sp$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.xor$mcI$sp(a, b);
   }

   default int xor$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(this.and$mcI$sp(a, this.complement$mcI$sp(b)), this.and$mcI$sp(this.complement$mcI$sp(a), b));
   }

   // $FF: synthetic method
   static int nand$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.nand(a, b);
   }

   default int nand(final int a, final int b) {
      return this.nand$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nand$mcI$sp$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.nand$mcI$sp(a, b);
   }

   default int nand$mcI$sp(final int a, final int b) {
      return this.complement$mcI$sp(this.and$mcI$sp(a, b));
   }

   // $FF: synthetic method
   static int nor$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.nor(a, b);
   }

   default int nor(final int a, final int b) {
      return this.nor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nor$mcI$sp$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.nor$mcI$sp(a, b);
   }

   default int nor$mcI$sp(final int a, final int b) {
      return this.complement$mcI$sp(this.or$mcI$sp(a, b));
   }

   // $FF: synthetic method
   static int nxor$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.nxor(a, b);
   }

   default int nxor(final int a, final int b) {
      return this.nxor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nxor$mcI$sp$(final Heyting$mcI$sp $this, final int a, final int b) {
      return $this.nxor$mcI$sp(a, b);
   }

   default int nxor$mcI$sp(final int a, final int b) {
      return this.complement$mcI$sp(this.xor$mcI$sp(a, b));
   }
}
