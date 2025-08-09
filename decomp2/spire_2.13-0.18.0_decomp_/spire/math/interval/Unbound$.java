package spire.math.interval;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Unbound$ implements Serializable {
   public static final Unbound$ MODULE$ = new Unbound$();

   public final String toString() {
      return "Unbound";
   }

   public Unbound apply() {
      return new Unbound();
   }

   public boolean unapply(final Unbound x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Unbound$.class);
   }

   private Unbound$() {
   }
}
