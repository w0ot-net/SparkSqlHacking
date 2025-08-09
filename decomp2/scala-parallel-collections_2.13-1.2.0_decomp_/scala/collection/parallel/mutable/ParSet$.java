package scala.collection.parallel.mutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.Combiner;

public final class ParSet$ extends ParSetFactory {
   public static final ParSet$ MODULE$ = new ParSet$();

   public CanCombineFrom canBuildFrom() {
      return new ParSetFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return ParHashSet$.MODULE$.newBuilder();
   }

   public Combiner newCombiner() {
      return ParHashSet$.MODULE$.newCombiner();
   }

   private ParSet$() {
   }
}
