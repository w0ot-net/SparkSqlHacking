package io.vertx.core.impl.future;

import io.vertx.core.Expectation;
import io.vertx.core.VertxException;
import io.vertx.core.impl.ContextInternal;

public class Expect extends Operation implements Listener {
   private final Expectation expectation;

   public Expect(ContextInternal context, Expectation expectation) {
      super(context);
      this.expectation = expectation;
   }

   public void onSuccess(Object value) {
      Throwable err = null;

      try {
         if (!this.expectation.test(value)) {
            err = this.expectation.describe(value);
            if (err == null) {
               err = new VertxException("Unexpected result: " + value, true);
            }
         }
      } catch (Throwable e) {
         err = e;
      }

      if (err != null) {
         this.tryFail(err);
      } else {
         this.tryComplete(value);
      }

   }

   public void onFailure(Throwable failure) {
      this.tryFail(failure);
   }
}
