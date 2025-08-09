package algebra.lattice;

public interface Bool$mcI$sp extends Bool, GenBool$mcI$sp, Heyting$mcI$sp {
   // $FF: synthetic method
   static int imp$(final Bool$mcI$sp $this, final int a, final int b) {
      return $this.imp(a, b);
   }

   default int imp(final int a, final int b) {
      return this.imp$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int imp$mcI$sp$(final Bool$mcI$sp $this, final int a, final int b) {
      return $this.imp$mcI$sp(a, b);
   }

   default int imp$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(this.complement$mcI$sp(a), b);
   }

   // $FF: synthetic method
   static int without$(final Bool$mcI$sp $this, final int a, final int b) {
      return $this.without(a, b);
   }

   default int without(final int a, final int b) {
      return this.without$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int without$mcI$sp$(final Bool$mcI$sp $this, final int a, final int b) {
      return $this.without$mcI$sp(a, b);
   }

   default int without$mcI$sp(final int a, final int b) {
      return this.and$mcI$sp(a, this.complement$mcI$sp(b));
   }

   // $FF: synthetic method
   static int xor$(final Bool$mcI$sp $this, final int a, final int b) {
      return $this.xor(a, b);
   }

   default int xor(final int a, final int b) {
      return this.xor$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int xor$mcI$sp$(final Bool$mcI$sp $this, final int a, final int b) {
      return $this.xor$mcI$sp(a, b);
   }

   default int xor$mcI$sp(final int a, final int b) {
      return this.or$mcI$sp(this.without$mcI$sp(a, b), this.without$mcI$sp(b, a));
   }

   // $FF: synthetic method
   static Bool dual$(final Bool$mcI$sp $this) {
      return $this.dual();
   }

   default Bool dual() {
      return this.dual$mcI$sp();
   }

   // $FF: synthetic method
   static Bool dual$mcI$sp$(final Bool$mcI$sp $this) {
      return $this.dual$mcI$sp();
   }

   default Bool dual$mcI$sp() {
      return new DualBool$mcI$sp(this);
   }
}
