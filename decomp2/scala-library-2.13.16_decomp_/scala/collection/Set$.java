package scala.collection;

import scala.runtime.ModuleSerializationProxy;

public final class Set$ extends IterableFactory.Delegate {
   public static final Set$ MODULE$ = new Set$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(Set$.class);
   }

   private Set$() {
      super(scala.collection.immutable.Set$.MODULE$);
   }
}
