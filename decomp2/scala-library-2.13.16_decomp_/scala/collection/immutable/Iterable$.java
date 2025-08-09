package scala.collection.immutable;

import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.runtime.ModuleSerializationProxy;

public final class Iterable$ extends IterableFactory.Delegate {
   public static final Iterable$ MODULE$ = new Iterable$();
   private static final long serialVersionUID = 3L;

   public Iterable from(final IterableOnce it) {
      return it instanceof Iterable ? (Iterable)it : (Iterable)super.from(it);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Iterable$.class);
   }

   private Iterable$() {
      super(List$.MODULE$);
   }
}
