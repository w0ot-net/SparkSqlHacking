package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.sparkproject.guava.annotations.GwtCompatible;

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
