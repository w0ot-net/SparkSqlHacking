package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.runtime.ModuleSerializationProxy;

public final class LinkedHashMap$ implements MapFactory {
   public static final LinkedHashMap$ MODULE$ = new LinkedHashMap$();
   private static final long serialVersionUID = 3L;

   static {
      LinkedHashMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public LinkedHashMap empty() {
      return new LinkedHashMap();
   }

   public LinkedHashMap from(final IterableOnce it) {
      LinkedHashMap newlhm = new LinkedHashMap();
      int sizeHint_delta = 0;
      Builder.sizeHint$(newlhm, it, sizeHint_delta);
      newlhm.addAll(it);
      return newlhm;
   }

   public GrowableBuilder newBuilder() {
      return new GrowableBuilder(new LinkedHashMap());
   }

   public final double defaultLoadFactor() {
      return (double)0.75F;
   }

   public final int defaultinitialSize() {
      return 16;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinkedHashMap$.class);
   }

   private LinkedHashMap$() {
   }
}
