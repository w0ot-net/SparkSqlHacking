package scala.collection.mutable;

import scala.collection.IterableFactory;
import scala.runtime.ModuleSerializationProxy;

public final class Iterable$ extends IterableFactory.Delegate {
   public static final Iterable$ MODULE$ = new Iterable$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(Iterable$.class);
   }

   private Iterable$() {
      super(ArrayBuffer$.MODULE$);
   }
}
