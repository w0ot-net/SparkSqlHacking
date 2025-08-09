package scala.collection.parallel.mutable;

import java.io.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.parallel.Combiner;
import scala.runtime.ModuleSerializationProxy;

public final class ParHashMap$ extends ParMapFactory implements Serializable {
   public static final ParHashMap$ MODULE$ = new ParHashMap$();
   private static int iters = 0;

   public int iters() {
      return iters;
   }

   public void iters_$eq(final int x$1) {
      iters = x$1;
   }

   public ParHashMap empty() {
      return new ParHashMap();
   }

   public Combiner newCombiner() {
      return ParHashMapCombiner$.MODULE$.apply();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParMapFactory.CanCombineFromMap();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParHashMap$.class);
   }

   private ParHashMap$() {
   }
}
