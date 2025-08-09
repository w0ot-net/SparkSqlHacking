package scala.collection.parallel.immutable;

import java.io.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;
import scala.runtime.ModuleSerializationProxy;

public final class ParVector$ extends ParFactory implements Serializable {
   public static final ParVector$ MODULE$ = new ParVector$();

   public CanCombineFrom canBuildFrom() {
      return new ParFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return this.newCombiner();
   }

   public Combiner newCombiner() {
      return new LazyParVectorCombiner();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParVector$.class);
   }

   private ParVector$() {
   }
}
