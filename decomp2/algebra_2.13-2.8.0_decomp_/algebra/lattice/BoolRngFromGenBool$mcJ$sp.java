package algebra.lattice;

public class BoolRngFromGenBool$mcJ$sp extends BoolRngFromGenBool {
   public final GenBool orig$mcJ$sp;

   public long zero() {
      return this.zero$mcJ$sp();
   }

   public long zero$mcJ$sp() {
      return this.orig$mcJ$sp.zero$mcJ$sp();
   }

   public long plus(final long x, final long y) {
      return this.plus$mcJ$sp(x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return this.orig$mcJ$sp.xor$mcJ$sp(x, y);
   }

   public long times(final long x, final long y) {
      return this.times$mcJ$sp(x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return this.orig$mcJ$sp.and$mcJ$sp(x, y);
   }

   public BoolRngFromGenBool$mcJ$sp(final GenBool orig$mcJ$sp) {
      super(orig$mcJ$sp);
      this.orig$mcJ$sp = orig$mcJ$sp;
   }
}
