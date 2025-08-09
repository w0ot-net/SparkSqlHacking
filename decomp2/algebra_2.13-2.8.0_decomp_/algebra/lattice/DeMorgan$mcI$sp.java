package algebra.lattice;

public interface DeMorgan$mcI$sp extends DeMorgan, Logic$mcI$sp {
   // $FF: synthetic method
   static int meet$(final DeMorgan$mcI$sp $this, final int a, final int b) {
      return $this.meet(a, b);
   }

   default int meet(final int a, final int b) {
      return this.meet$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int meet$mcI$sp$(final DeMorgan$mcI$sp $this, final int a, final int b) {
      return $this.meet$mcI$sp(a, b);
   }

   default int meet$mcI$sp(final int a, final int b) {
      return this.and$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int join$(final DeMorgan$mcI$sp $this, final int a, final int b) {
      return $this.join(a, b);
   }

   default int join(final int a, final int b) {
      return this.join$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int join$mcI$sp$(final DeMorgan$mcI$sp $this, final int a, final int b) {
      return $this.join$mcI$sp(a, b);
   }

   default int join$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int imp$(final DeMorgan$mcI$sp $this, final int a, final int b) {
      return $this.imp(a, b);
   }

   default int imp(final int a, final int b) {
      return this.imp$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int imp$mcI$sp$(final DeMorgan$mcI$sp $this, final int a, final int b) {
      return $this.imp$mcI$sp(a, b);
   }

   default int imp$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(this.not$mcI$sp(a), b);
   }
}
