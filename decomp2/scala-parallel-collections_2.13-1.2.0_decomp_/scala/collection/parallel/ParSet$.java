package scala.collection.parallel;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.mutable.ParHashSetCombiner$;

public final class ParSet$ extends ParSetFactory {
   public static final ParSet$ MODULE$ = new ParSet$();

   public Combiner newCombiner() {
      return ParHashSetCombiner$.MODULE$.apply();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParSetFactory.GenericCanCombineFrom();
   }

   private ParSet$() {
   }
}
