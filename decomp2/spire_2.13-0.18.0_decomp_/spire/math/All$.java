package spire.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class All$ implements Serializable {
   public static final All$ MODULE$ = new All$();

   public final String toString() {
      return "All";
   }

   public All apply() {
      return new All();
   }

   public boolean unapply(final All x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(All$.class);
   }

   private All$() {
   }
}
