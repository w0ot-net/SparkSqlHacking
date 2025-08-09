package scala.collection;

import java.io.Serializable;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class SortedMapFactory$ implements Serializable {
   public static final SortedMapFactory$ MODULE$ = new SortedMapFactory$();

   public Factory toFactory(final SortedMapFactory factory, final Ordering evidence$41) {
      return new SortedMapFactory.ToFactory(factory, evidence$41);
   }

   public BuildFrom toBuildFrom(final SortedMapFactory factory, final Ordering evidence$43) {
      return new SortedMapFactory.SortedMapFactoryToBuildFrom(factory, evidence$43);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SortedMapFactory$.class);
   }

   private SortedMapFactory$() {
   }
}
