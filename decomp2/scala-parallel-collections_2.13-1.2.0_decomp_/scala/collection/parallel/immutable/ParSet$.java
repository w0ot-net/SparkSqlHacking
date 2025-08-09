package scala.collection.parallel.immutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.Combiner;

public final class ParSet$ extends ParSetFactory {
   public static final ParSet$ MODULE$ = new ParSet$();

   public Combiner newCombiner() {
      return HashSetCombiner$.MODULE$.apply();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParSetFactory.GenericCanCombineFrom();
   }

   private ParSet$() {
   }
}
