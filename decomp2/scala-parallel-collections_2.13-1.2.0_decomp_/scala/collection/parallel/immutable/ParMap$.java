package scala.collection.parallel.immutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.parallel.Combiner;

public final class ParMap$ extends ParMapFactory {
   public static final ParMap$ MODULE$ = new ParMap$();

   public ParMap empty() {
      return new ParHashMap();
   }

   public Combiner newCombiner() {
      return HashMapCombiner$.MODULE$.apply();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParMapFactory.CanCombineFromMap();
   }

   private ParMap$() {
   }
}
