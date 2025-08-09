package scala.util;

import java.io.Serializable;
import scala.Function0;
import scala.runtime.ModuleSerializationProxy;
import scala.util.control.NonFatal$;

public final class Try$ implements Serializable {
   public static final Try$ MODULE$ = new Try$();

   public Try apply(final Function0 r) {
      try {
         Object r1 = r.apply();
         return new Success(r1);
      } catch (Throwable var4) {
         if (var4 != null && NonFatal$.MODULE$.apply(var4)) {
            return new Failure(var4);
         } else {
            throw var4;
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Try$.class);
   }

   private Try$() {
   }
}
