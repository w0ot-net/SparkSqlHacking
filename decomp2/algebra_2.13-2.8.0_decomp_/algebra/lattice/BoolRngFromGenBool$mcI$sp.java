package algebra.lattice;

public class BoolRngFromGenBool$mcI$sp extends BoolRngFromGenBool {
   public final GenBool orig$mcI$sp;

   public int zero() {
      return this.zero$mcI$sp();
   }

   public int zero$mcI$sp() {
      return this.orig$mcI$sp.zero$mcI$sp();
   }

   public int plus(final int x, final int y) {
      return this.plus$mcI$sp(x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return this.orig$mcI$sp.xor$mcI$sp(x, y);
   }

   public int times(final int x, final int y) {
      return this.times$mcI$sp(x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return this.orig$mcI$sp.and$mcI$sp(x, y);
   }

   public BoolRngFromGenBool$mcI$sp(final GenBool orig$mcI$sp) {
      super(orig$mcI$sp);
      this.orig$mcI$sp = orig$mcI$sp;
   }
}
