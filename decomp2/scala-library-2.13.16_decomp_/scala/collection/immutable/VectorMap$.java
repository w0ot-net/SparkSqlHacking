package scala.collection.immutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.runtime.ModuleSerializationProxy;

public final class VectorMap$ implements MapFactory {
   public static final VectorMap$ MODULE$ = new VectorMap$();
   private static final VectorMap EmptyMap;

   static {
      VectorMap$ var10000 = MODULE$;
      Vector$ var10002 = Vector$.MODULE$;
      EmptyMap = new VectorMap(Vector0$.MODULE$, HashMap$.MODULE$.empty());
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public VectorMap empty() {
      return EmptyMap;
   }

   public VectorMap from(final IterableOnce it) {
      return it instanceof VectorMap ? (VectorMap)it : (VectorMap)((Builder)Growable.addAll$(new VectorMapBuilder(), it)).result();
   }

   public Builder newBuilder() {
      return new VectorMapBuilder();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorMap$.class);
   }

   private VectorMap$() {
   }
}
