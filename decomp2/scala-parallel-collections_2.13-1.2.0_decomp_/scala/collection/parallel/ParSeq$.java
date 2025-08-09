package scala.collection.parallel;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;

public final class ParSeq$ extends ParFactory {
   public static final ParSeq$ MODULE$ = new ParSeq$();

   public CanCombineFrom canBuildFrom() {
      return new ParFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return scala.collection.parallel.mutable.package$.MODULE$.ParArrayCombiner().apply();
   }

   public Combiner newCombiner() {
      return scala.collection.parallel.mutable.package$.MODULE$.ParArrayCombiner().apply();
   }

   private ParSeq$() {
   }
}
