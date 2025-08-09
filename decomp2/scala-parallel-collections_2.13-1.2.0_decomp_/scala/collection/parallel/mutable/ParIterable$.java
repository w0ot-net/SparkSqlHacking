package scala.collection.parallel.mutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;

public final class ParIterable$ extends ParFactory {
   public static final ParIterable$ MODULE$ = new ParIterable$();

   public CanCombineFrom canBuildFrom() {
      return new ParFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return package$.MODULE$.ParArrayCombiner().apply();
   }

   public Combiner newCombiner() {
      return package$.MODULE$.ParArrayCombiner().apply();
   }

   private ParIterable$() {
   }
}
