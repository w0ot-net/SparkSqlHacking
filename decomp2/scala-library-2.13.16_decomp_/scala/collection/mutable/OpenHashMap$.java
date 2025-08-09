package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.runtime.ModuleSerializationProxy;

/** @deprecated */
public final class OpenHashMap$ implements MapFactory {
   public static final OpenHashMap$ MODULE$ = new OpenHashMap$();
   private static final long serialVersionUID = 3L;

   static {
      OpenHashMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public OpenHashMap empty() {
      return new OpenHashMap();
   }

   public OpenHashMap from(final IterableOnce it) {
      return (OpenHashMap)(new OpenHashMap()).addAll(it);
   }

   public Builder newBuilder() {
      return new GrowableBuilder(new OpenHashMap());
   }

   public int nextPositivePowerOfTwo(final int target) {
      return 1 << -Integer.numberOfLeadingZeros(target - 1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OpenHashMap$.class);
   }

   private OpenHashMap$() {
   }
}
