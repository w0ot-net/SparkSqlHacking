package scala.collection.parallel.immutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;

public final class ParSeq$ extends ParFactory {
   public static final ParSeq$ MODULE$ = new ParSeq$();

   public CanCombineFrom canBuildFrom() {
      return new ParFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return ParVector$.MODULE$.newBuilder();
   }

   public Combiner newCombiner() {
      return ParVector$.MODULE$.newCombiner();
   }

   private ParSeq$() {
   }
}
