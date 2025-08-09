package org.sparkproject.jetty.util;

import org.sparkproject.jetty.util.thread.Invocable;

public abstract class IteratingNestedCallback extends IteratingCallback {
   final Callback _callback;

   public IteratingNestedCallback(Callback callback) {
      this._callback = callback;
   }

   public Invocable.InvocationType getInvocationType() {
      return this._callback.getInvocationType();
   }

   protected void onCompleteSuccess() {
      this._callback.succeeded();
   }

   protected void onCompleteFailure(Throwable x) {
      this._callback.failed(x);
   }

   public String toString() {
      return String.format("%s@%x", this.getClass().getSimpleName(), this.hashCode());
   }
}
