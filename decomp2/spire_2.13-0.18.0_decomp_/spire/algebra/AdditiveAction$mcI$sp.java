package spire.algebra;

public interface AdditiveAction$mcI$sp extends AdditiveAction {
   // $FF: synthetic method
   static Action additive$(final AdditiveAction$mcI$sp $this) {
      return $this.additive();
   }

   default Action additive() {
      return this.additive$mcI$sp();
   }

   // $FF: synthetic method
   static Action additive$mcI$sp$(final AdditiveAction$mcI$sp $this) {
      return $this.additive$mcI$sp();
   }

   default Action additive$mcI$sp() {
      return new Action$mcI$sp() {
         // $FF: synthetic field
         private final AdditiveAction$mcI$sp $outer;

         public int actl(final Object g, final int p) {
            return this.actl$mcI$sp(g, p);
         }

         public int actr(final int p, final Object g) {
            return this.actr$mcI$sp(p, g);
         }

         public int actl$mcI$sp(final Object g, final int p) {
            return this.$outer.gplusl$mcI$sp(g, p);
         }

         public int actr$mcI$sp(final int p, final Object g) {
            return this.$outer.gplusr$mcI$sp(p, g);
         }

         public {
            if (AdditiveAction$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveAction$mcI$sp.this;
            }
         }
      };
   }
}
