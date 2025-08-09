package scala.concurrent.impl;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import scala.Function1;
import scala.concurrent.ExecutionContext;
import scala.runtime.NonLocalReturnControl;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.control.ControlThrowable;

public final class Promise$ {
   public static final Promise$ MODULE$ = new Promise$();
   public static final Promise.Transformation scala$concurrent$impl$Promise$$Noop;

   static {
      scala$concurrent$impl$Promise$$Noop = new Promise.Transformation(0, (Function1)null, ExecutionContext.parasitic$.MODULE$);
   }

   public final Try scala$concurrent$impl$Promise$$resolve(final Try value) {
      if (Objects.requireNonNull(value) instanceof Success) {
         return value;
      } else {
         Throwable t = ((Failure)value).exception();
         if (!(t instanceof ControlThrowable) && !(t instanceof InterruptedException) && !(t instanceof Error)) {
            return value;
         } else {
            return (Try)(t instanceof NonLocalReturnControl ? new Success(((NonLocalReturnControl)t).value()) : new Failure(new ExecutionException("Boxed Exception", t)));
         }
      }
   }

   public final int Xform_noop() {
      return 0;
   }

   public final int Xform_map() {
      return 1;
   }

   public final int Xform_flatMap() {
      return 2;
   }

   public final int Xform_transform() {
      return 3;
   }

   public final int Xform_transformWith() {
      return 4;
   }

   public final int Xform_foreach() {
      return 5;
   }

   public final int Xform_onComplete() {
      return 6;
   }

   public final int Xform_recover() {
      return 7;
   }

   public final int Xform_recoverWith() {
      return 8;
   }

   public final int Xform_filter() {
      return 9;
   }

   public final int Xform_collect() {
      return 10;
   }

   private Promise$() {
   }
}
