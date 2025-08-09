package algebra.lattice;

public interface Heyting$mcJ$sp extends Heyting, BoundedDistributiveLattice$mcJ$sp {
   // $FF: synthetic method
   static long meet$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.meet(a, b);
   }

   default long meet(final long a, final long b) {
      return this.meet$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long meet$mcJ$sp$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.meet$mcJ$sp(a, b);
   }

   default long meet$mcJ$sp(final long a, final long b) {
      return this.and$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long join$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.join(a, b);
   }

   default long join(final long a, final long b) {
      return this.join$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long join$mcJ$sp$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.join$mcJ$sp(a, b);
   }

   default long join$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long xor$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.xor(a, b);
   }

   default long xor(final long a, final long b) {
      return this.xor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long xor$mcJ$sp$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.xor$mcJ$sp(a, b);
   }

   default long xor$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(this.and$mcJ$sp(a, this.complement$mcJ$sp(b)), this.and$mcJ$sp(this.complement$mcJ$sp(a), b));
   }

   // $FF: synthetic method
   static long nand$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.nand(a, b);
   }

   default long nand(final long a, final long b) {
      return this.nand$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nand$mcJ$sp$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.nand$mcJ$sp(a, b);
   }

   default long nand$mcJ$sp(final long a, final long b) {
      return this.complement$mcJ$sp(this.and$mcJ$sp(a, b));
   }

   // $FF: synthetic method
   static long nor$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.nor(a, b);
   }

   default long nor(final long a, final long b) {
      return this.nor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nor$mcJ$sp$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.nor$mcJ$sp(a, b);
   }

   default long nor$mcJ$sp(final long a, final long b) {
      return this.complement$mcJ$sp(this.or$mcJ$sp(a, b));
   }

   // $FF: synthetic method
   static long nxor$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.nxor(a, b);
   }

   default long nxor(final long a, final long b) {
      return this.nxor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nxor$mcJ$sp$(final Heyting$mcJ$sp $this, final long a, final long b) {
      return $this.nxor$mcJ$sp(a, b);
   }

   default long nxor$mcJ$sp(final long a, final long b) {
      return this.complement$mcJ$sp(this.xor$mcJ$sp(a, b));
   }
}
