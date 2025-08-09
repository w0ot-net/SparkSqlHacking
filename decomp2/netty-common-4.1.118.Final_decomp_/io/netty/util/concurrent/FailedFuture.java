package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;

public final class FailedFuture extends CompleteFuture {
   private final Throwable cause;

   public FailedFuture(EventExecutor executor, Throwable cause) {
      super(executor);
      this.cause = (Throwable)ObjectUtil.checkNotNull(cause, "cause");
   }

   public Throwable cause() {
      return this.cause;
   }

   public boolean isSuccess() {
      return false;
   }

   public Future sync() {
      PlatformDependent.throwException(this.cause);
      return this;
   }

   public Future syncUninterruptibly() {
      PlatformDependent.throwException(this.cause);
      return this;
   }

   public Object getNow() {
      return null;
   }
}
