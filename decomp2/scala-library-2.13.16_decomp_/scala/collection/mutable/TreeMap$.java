package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SortedMapFactory;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class TreeMap$ implements SortedMapFactory {
   public static final TreeMap$ MODULE$ = new TreeMap$();
   private static final long serialVersionUID = 3L;

   static {
      TreeMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems, final Ordering evidence$38) {
      return SortedMapFactory.apply$(this, elems, evidence$38);
   }

   public Factory sortedMapFactory(final Ordering evidence$40) {
      return SortedMapFactory.sortedMapFactory$(this, evidence$40);
   }

   public TreeMap from(final IterableOnce it, final Ordering evidence$1) {
      Growable$ var10000 = Growable$.MODULE$;
      return (TreeMap)(new TreeMap(evidence$1)).addAll(it);
   }

   public TreeMap empty(final Ordering evidence$2) {
      return new TreeMap(evidence$2);
   }

   public Builder newBuilder(final Ordering evidence$3) {
      return new GrowableBuilder(new TreeMap(evidence$3));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeMap$.class);
   }

   private TreeMap$() {
   }
}
