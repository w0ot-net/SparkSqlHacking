package spire.algebra;

public interface MultiplicativeAction$mcI$sp extends MultiplicativeAction {
   // $FF: synthetic method
   static Action multiplicative$(final MultiplicativeAction$mcI$sp $this) {
      return $this.multiplicative();
   }

   default Action multiplicative() {
      return this.multiplicative$mcI$sp();
   }

   // $FF: synthetic method
   static Action multiplicative$mcI$sp$(final MultiplicativeAction$mcI$sp $this) {
      return $this.multiplicative$mcI$sp();
   }

   default Action multiplicative$mcI$sp() {
      return new Action$mcI$sp() {
         // $FF: synthetic field
         private final MultiplicativeAction$mcI$sp $outer;

         public int actl(final Object g, final int p) {
            return this.actl$mcI$sp(g, p);
         }

         public int actr(final int p, final Object g) {
            return this.actr$mcI$sp(p, g);
         }

         public int actl$mcI$sp(final Object g, final int p) {
            return this.$outer.gtimesl$mcI$sp(g, p);
         }

         public int actr$mcI$sp(final int p, final Object g) {
            return this.$outer.gtimesr$mcI$sp(p, g);
         }

         public {
            if (MultiplicativeAction$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeAction$mcI$sp.this;
            }
         }
      };
   }
}
