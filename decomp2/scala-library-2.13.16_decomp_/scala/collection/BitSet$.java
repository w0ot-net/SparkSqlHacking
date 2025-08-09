package scala.collection;

import scala.Function0;
import scala.collection.mutable.Builder;

public final class BitSet$ implements SpecificIterableFactory {
   public static final BitSet$ MODULE$ = new BitSet$();
   private static final long serialVersionUID = 3L;

   static {
      BitSet$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq xs) {
      return SpecificIterableFactory.apply$(this, xs);
   }

   public Object fill(final int n, final Function0 elem) {
      return SpecificIterableFactory.fill$(this, n, elem);
   }

   public Factory specificIterableFactory() {
      return SpecificIterableFactory.specificIterableFactory$(this);
   }

   public final String ordMsg() {
      return "No implicit Ordering[${B}] found to build a SortedSet[${B}]. You may want to upcast to a Set[Int] first by calling `unsorted`.";
   }

   public final String zipOrdMsg() {
      return "No implicit Ordering[${B}] found to build a SortedSet[(Int, ${B})]. You may want to upcast to a Set[Int] first by calling `unsorted`.";
   }

   public BitSet empty() {
      return scala.collection.immutable.BitSet$.MODULE$.empty();
   }

   public Builder newBuilder() {
      return scala.collection.immutable.BitSet$.MODULE$.newBuilder();
   }

   public BitSet fromSpecific(final IterableOnce it) {
      return scala.collection.immutable.BitSet$.MODULE$.fromSpecific(it);
   }

   private BitSet$() {
   }
}
