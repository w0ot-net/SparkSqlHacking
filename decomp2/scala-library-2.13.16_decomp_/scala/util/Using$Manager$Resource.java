package scala.util;

public final class Using$Manager$Resource {
   private final Object resource;
   private final Using.Releasable releasable;

   public void release() {
      this.releasable.release(this.resource);
   }

   public Using$Manager$Resource(final Object resource, final Using.Releasable releasable) {
      this.resource = resource;
      this.releasable = releasable;
   }
}
