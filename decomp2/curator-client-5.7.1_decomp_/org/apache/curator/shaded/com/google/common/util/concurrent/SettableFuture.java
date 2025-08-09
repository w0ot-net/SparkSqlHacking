package org.apache.curator.shaded.com.google.common.util.concurrent;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class SettableFuture extends AbstractFuture.TrustedFuture {
   public static SettableFuture create() {
      return new SettableFuture();
   }

   @CanIgnoreReturnValue
   public boolean set(@ParametricNullness Object value) {
      return super.set(value);
   }

   @CanIgnoreReturnValue
   public boolean setException(Throwable throwable) {
      return super.setException(throwable);
   }

   @CanIgnoreReturnValue
   public boolean setFuture(ListenableFuture future) {
      return super.setFuture(future);
   }

   private SettableFuture() {
   }
}
