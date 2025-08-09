package spire.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Empty$ implements Serializable {
   public static final Empty$ MODULE$ = new Empty$();

   public final String toString() {
      return "Empty";
   }

   public Empty apply() {
      return new Empty();
   }

   public boolean unapply(final Empty x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Empty$.class);
   }

   private Empty$() {
   }
}
