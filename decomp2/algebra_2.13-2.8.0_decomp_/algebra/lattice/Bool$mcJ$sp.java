package algebra.lattice;

public interface Bool$mcJ$sp extends Bool, GenBool$mcJ$sp, Heyting$mcJ$sp {
   // $FF: synthetic method
   static long imp$(final Bool$mcJ$sp $this, final long a, final long b) {
      return $this.imp(a, b);
   }

   default long imp(final long a, final long b) {
      return this.imp$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long imp$mcJ$sp$(final Bool$mcJ$sp $this, final long a, final long b) {
      return $this.imp$mcJ$sp(a, b);
   }

   default long imp$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(this.complement$mcJ$sp(a), b);
   }

   // $FF: synthetic method
   static long without$(final Bool$mcJ$sp $this, final long a, final long b) {
      return $this.without(a, b);
   }

   default long without(final long a, final long b) {
      return this.without$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long without$mcJ$sp$(final Bool$mcJ$sp $this, final long a, final long b) {
      return $this.without$mcJ$sp(a, b);
   }

   default long without$mcJ$sp(final long a, final long b) {
      return this.and$mcJ$sp(a, this.complement$mcJ$sp(b));
   }

   // $FF: synthetic method
   static long xor$(final Bool$mcJ$sp $this, final long a, final long b) {
      return $this.xor(a, b);
   }

   default long xor(final long a, final long b) {
      return this.xor$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long xor$mcJ$sp$(final Bool$mcJ$sp $this, final long a, final long b) {
      return $this.xor$mcJ$sp(a, b);
   }

   default long xor$mcJ$sp(final long a, final long b) {
      return this.or$mcJ$sp(this.without$mcJ$sp(a, b), this.without$mcJ$sp(b, a));
   }

   // $FF: synthetic method
   static Bool dual$(final Bool$mcJ$sp $this) {
      return $this.dual();
   }

   default Bool dual() {
      return this.dual$mcJ$sp();
   }

   // $FF: synthetic method
   static Bool dual$mcJ$sp$(final Bool$mcJ$sp $this) {
      return $this.dual$mcJ$sp();
   }

   default Bool dual$mcJ$sp() {
      return new DualBool$mcJ$sp(this);
   }
}
