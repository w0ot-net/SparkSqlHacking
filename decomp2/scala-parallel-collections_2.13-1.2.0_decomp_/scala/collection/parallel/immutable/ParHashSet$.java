package scala.collection.parallel.immutable;

import java.io.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.immutable.OldHashSet;
import scala.collection.parallel.Combiner;
import scala.runtime.ModuleSerializationProxy;

public final class ParHashSet$ extends ParSetFactory implements Serializable {
   public static final ParHashSet$ MODULE$ = new ParHashSet$();

   public Combiner newCombiner() {
      return HashSetCombiner$.MODULE$.apply();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParSetFactory.GenericCanCombineFrom();
   }

   public ParHashSet fromTrie(final OldHashSet t) {
      return new ParHashSet(t);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParHashSet$.class);
   }

   private ParHashSet$() {
   }
}
