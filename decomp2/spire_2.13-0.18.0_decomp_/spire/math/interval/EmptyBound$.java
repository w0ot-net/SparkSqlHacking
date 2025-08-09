package spire.math.interval;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class EmptyBound$ implements Serializable {
   public static final EmptyBound$ MODULE$ = new EmptyBound$();

   public final String toString() {
      return "EmptyBound";
   }

   public EmptyBound apply() {
      return new EmptyBound();
   }

   public boolean unapply(final EmptyBound x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EmptyBound$.class);
   }

   private EmptyBound$() {
   }
}
