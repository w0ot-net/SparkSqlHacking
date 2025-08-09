package spire.random;

public final class Size$ {
   public static final Size$ MODULE$ = new Size$();

   public Size apply(final int n) {
      return new Size.Exact(n);
   }

   public Size upTo(final int n) {
      return new Size.Between(0, n);
   }

   public Size between(final int n1, final int n2) {
      return new Size.Between(n1, n2);
   }

   private Size$() {
   }
}
