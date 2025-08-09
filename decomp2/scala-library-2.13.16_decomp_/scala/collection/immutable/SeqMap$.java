package scala.collection.immutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.mutable.Builder;
import scala.runtime.ModuleSerializationProxy;

public final class SeqMap$ implements MapFactory {
   public static final SeqMap$ MODULE$ = new SeqMap$();

   static {
      SeqMap$ var10000 = MODULE$;
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public SeqMap empty() {
      return SeqMap.EmptySeqMap$.MODULE$;
   }

   public SeqMap from(final IterableOnce it) {
      if (it instanceof ListMap) {
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
      } else if (it instanceof SeqMap.SeqMap4) {
         return (SeqMap.SeqMap4)it;
      } else {
         return (SeqMap)(it instanceof Iterable && ((Iterable)it).isEmpty() ? SeqMap.EmptySeqMap$.MODULE$ : (SeqMap)(new SeqMap.SeqMapBuilderImpl()).addAll(it).result());
      }
   }

   public Builder newBuilder() {
      return new SeqMap.SeqMapBuilderImpl();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SeqMap$.class);
   }

   private SeqMap$() {
   }
}
