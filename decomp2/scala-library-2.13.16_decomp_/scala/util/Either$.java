package scala.util;

import java.io.Serializable;
import scala.Function0;
import scala.runtime.ModuleSerializationProxy;

public final class Either$ implements Serializable {
   public static final Either$ MODULE$ = new Either$();

   public Either cond(final boolean test, final Function0 right, final Function0 left) {
      return (Either)(test ? new Right(right.apply()) : new Left(left.apply()));
   }

   public Either MergeableEither(final Either x) {
      return x;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Either$.class);
   }

   private Either$() {
   }
}
