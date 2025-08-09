package scala.collection;

import scala.runtime.ModuleSerializationProxy;

public final class SortedMap$ extends SortedMapFactory.Delegate {
   public static final SortedMap$ MODULE$ = new SortedMap$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(SortedMap$.class);
   }

   private SortedMap$() {
      super(scala.collection.immutable.SortedMap$.MODULE$);
   }
}
