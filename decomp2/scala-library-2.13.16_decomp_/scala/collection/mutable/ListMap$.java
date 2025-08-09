package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.runtime.ModuleSerializationProxy;

/** @deprecated */
public final class ListMap$ implements MapFactory {
   public static final ListMap$ MODULE$ = new ListMap$();
   private static final long serialVersionUID = 3L;

   static {
      ListMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public ListMap empty() {
      return new ListMap();
   }

   public ListMap from(final IterableOnce it) {
      Growable$ var10000 = Growable$.MODULE$;
      return (ListMap)(new ListMap()).addAll(it);
   }

   public Builder newBuilder() {
      return new GrowableBuilder(new ListMap());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ListMap$.class);
   }

   private ListMap$() {
   }
}
