package scala.collection.mutable;

import scala.collection.SortedIterableFactory;
import scala.runtime.ModuleSerializationProxy;

public final class SortedSet$ extends SortedIterableFactory.Delegate {
   public static final SortedSet$ MODULE$ = new SortedSet$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(SortedSet$.class);
   }

   private SortedSet$() {
      super(TreeSet$.MODULE$);
   }
}
