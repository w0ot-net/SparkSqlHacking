package scala.util;

public class Using$Releasable$AutoCloseableIsReleasable$ implements Using.Releasable {
   public static final Using$Releasable$AutoCloseableIsReleasable$ MODULE$ = new Using$Releasable$AutoCloseableIsReleasable$();

   public void release(final AutoCloseable resource) {
      resource.close();
   }
}
