package org.glassfish.jersey.internal.guava;

public final class SettableFuture extends AbstractFuture {
   private SettableFuture() {
   }

   public static SettableFuture create() {
      return new SettableFuture();
   }

   public boolean set(Object value) {
      return super.set(value);
   }

   public boolean setException(Throwable throwable) {
      return super.setException(throwable);
   }
}
