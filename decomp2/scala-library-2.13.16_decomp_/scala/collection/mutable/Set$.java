package scala.collection.mutable;

import scala.collection.IterableFactory;
import scala.runtime.ModuleSerializationProxy;

public final class Set$ extends IterableFactory.Delegate {
   public static final Set$ MODULE$ = new Set$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(Set$.class);
   }

   private Set$() {
      super(HashSet$.MODULE$);
   }
}
