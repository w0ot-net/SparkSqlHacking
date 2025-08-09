package scala.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class PartialOrdering$ implements Serializable {
   public static final PartialOrdering$ MODULE$ = new PartialOrdering$();

   public PartialOrdering apply(final PartialOrdering ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PartialOrdering$.class);
   }

   private PartialOrdering$() {
   }
}
