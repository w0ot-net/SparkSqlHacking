package scala.collection.parallel.mutable;

import java.io.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.parallel.Combiner;
import scala.runtime.ModuleSerializationProxy;

public final class ParTrieMap$ extends ParMapFactory implements Serializable {
   public static final ParTrieMap$ MODULE$ = new ParTrieMap$();

   public ParTrieMap empty() {
      return new ParTrieMap();
   }

   public Combiner newCombiner() {
      return new ParTrieMap();
   }

   public CanCombineFrom canBuildFrom() {
      return new ParMapFactory.CanCombineFromMap();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParTrieMap$.class);
   }

   private ParTrieMap$() {
   }
}
