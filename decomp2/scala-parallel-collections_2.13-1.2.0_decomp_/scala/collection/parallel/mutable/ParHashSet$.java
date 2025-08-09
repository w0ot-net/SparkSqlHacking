package scala.collection.parallel.mutable;

import java.io.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.Combiner;
import scala.runtime.ModuleSerializationProxy;

public final class ParHashSet$ extends ParSetFactory implements Serializable {
   public static final ParHashSet$ MODULE$ = new ParHashSet$();

   public CanCombineFrom canBuildFrom() {
      return new ParSetFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return this.newCombiner();
   }

   public Combiner newCombiner() {
      return ParHashSetCombiner$.MODULE$.apply();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParHashSet$.class);
   }

   private ParHashSet$() {
   }
}
