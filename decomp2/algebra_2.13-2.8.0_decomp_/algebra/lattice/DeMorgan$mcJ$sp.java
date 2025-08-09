package algebra.lattice;

public interface DeMorgan$mcJ$sp extends DeMorgan, Logic$mcJ$sp {
   // $FF: synthetic method
   static long meet$(final DeMorgan$mcJ$sp $this, final long a, final long b) {
      return $this.meet(a, b);
   }

   default long meet(final long a, final long b) {
      return this.meet$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long meet$mcJ$sp$(final DeMorgan$mcJ$sp $this, final long a, final long b) {
      return $this.meet$mcJ$sp(a, b);
   }

   default long meet$mcJ$sp(final long a, final long b) {
      return this.and$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long join$(final DeMorgan$mcJ$sp $this, final long a, final long b) {
      return $this.join(a, b);
   }

   default long join(final long a, final long b) {
      return this.join$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long join$mcJ$sp$(final DeMorgan$mcJ$sp $this, final long a, final long b) {
      return $this.join$mcJ$sp(a, b);
   }

   default long join$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long imp$(final DeMorgan$mcJ$sp $this, final long a, final long b) {
      return $this.imp(a, b);
   }

   default long imp(final long a, final long b) {
      return this.imp$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long imp$mcJ$sp$(final DeMorgan$mcJ$sp $this, final long a, final long b) {
      return $this.imp$mcJ$sp(a, b);
   }

   default long imp$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(this.not$mcJ$sp(a), b);
   }
}
