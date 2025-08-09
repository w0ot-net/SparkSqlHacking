package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.runtime.ModuleSerializationProxy;

public final class WeakHashMap$ implements MapFactory {
   public static final WeakHashMap$ MODULE$ = new WeakHashMap$();
   private static final long serialVersionUID = 3L;

   static {
      WeakHashMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public WeakHashMap empty() {
      return new WeakHashMap();
   }

   public WeakHashMap from(final IterableOnce it) {
      Growable$ var10000 = Growable$.MODULE$;
      return (WeakHashMap)(new WeakHashMap()).addAll(it);
   }

   public Builder newBuilder() {
      return new GrowableBuilder(new WeakHashMap());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WeakHashMap$.class);
   }

   private WeakHashMap$() {
   }
}
