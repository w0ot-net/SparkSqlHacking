package scala.concurrent;

import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class Promise$ {
   public static final Promise$ MODULE$ = new Promise$();

   public final Promise apply() {
      return new scala.concurrent.impl.Promise.DefaultPromise();
   }

   public final Promise failed(final Throwable exception) {
      Try fromTry_result = new Failure(exception);
      return new scala.concurrent.impl.Promise.DefaultPromise(fromTry_result);
   }

   public final Promise successful(final Object result) {
      Try fromTry_result = new Success(result);
      return new scala.concurrent.impl.Promise.DefaultPromise(fromTry_result);
   }

   public final Promise fromTry(final Try result) {
      return new scala.concurrent.impl.Promise.DefaultPromise(result);
   }

   private Promise$() {
   }
}
