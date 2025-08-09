package scala.collection.immutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.mutable.Builder;
import scala.runtime.ModuleSerializationProxy;

public final class Map$ implements MapFactory {
   public static final Map$ MODULE$ = new Map$();
   private static final long serialVersionUID = 3L;

   static {
      Map$ var10000 = MODULE$;
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public Map empty() {
      return Map.EmptyMap$.MODULE$;
   }

   public Map from(final IterableOnce it) {
      if (it instanceof Iterable && ((Iterable)it).isEmpty()) {
         return Map.EmptyMap$.MODULE$;
      } else if (it instanceof HashMap) {
         return (HashMap)it;
      } else if (it instanceof Map.Map1) {
         return (Map.Map1)it;
      } else if (it instanceof Map.Map2) {
         return (Map.Map2)it;
      } else if (it instanceof Map.Map3) {
         return (Map.Map3)it;
      } else if (it instanceof Map.Map4) {
         return (Map.Map4)it;
      } else if (it instanceof ListMap) {
         return (ListMap)it;
      } else if (it instanceof TreeSeqMap) {
         return (TreeSeqMap)it;
      } else if (it instanceof VectorMap) {
         return (VectorMap)it;
      } else if (it instanceof SeqMap.SeqMap1) {
         return (SeqMap.SeqMap1)it;
      } else if (it instanceof SeqMap.SeqMap2) {
         return (SeqMap.SeqMap2)it;
      } else if (it instanceof SeqMap.SeqMap3) {
         return (SeqMap.SeqMap3)it;
      } else {
         return (Map)(it instanceof SeqMap.SeqMap4 ? (SeqMap.SeqMap4)it : (Map)(new MapBuilderImpl()).addAll(it).result());
      }
   }

   public Builder newBuilder() {
      return new MapBuilderImpl();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Map$.class);
   }

   private Map$() {
   }
}
