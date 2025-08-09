package scala.collection.parallel.immutable;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.immutable.OldHashMap;
import scala.collection.parallel.Combiner;
import scala.runtime.ModuleSerializationProxy;

public final class ParHashMap$ extends ParMapFactory implements Serializable {
   public static final ParHashMap$ MODULE$ = new ParHashMap$();
   private static AtomicInteger totalcombines = new AtomicInteger(0);

   public ParHashMap empty() {
      return new ParHashMap();
   }

   public Combiner newCombiner() {
      return HashMapCombiner$.MODULE$.apply();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParMapFactory.CanCombineFromMap();
   }

   public ParHashMap fromTrie(final OldHashMap t) {
      return new ParHashMap(t);
   }

   public AtomicInteger totalcombines() {
      return totalcombines;
   }

   public void totalcombines_$eq(final AtomicInteger x$1) {
      totalcombines = x$1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParHashMap$.class);
   }

   private ParHashMap$() {
   }
}
