package scala.collection.mutable;

import java.io.Serializable;
import java.util.Arrays;
import scala.Function0;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SpecificIterableFactory;
import scala.runtime.ModuleSerializationProxy;

public final class BitSet$ implements SpecificIterableFactory, Serializable {
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

   public BitSet fromSpecific(final IterableOnce it) {
      Growable$ var10000 = Growable$.MODULE$;
      return (BitSet)(new BitSet()).addAll(it);
   }

   public BitSet empty() {
      return new BitSet();
   }

   public Builder newBuilder() {
      return new GrowableBuilder(new BitSet());
   }

   public BitSet fromBitMask(final long[] elems) {
      int len = elems.length;
      if (len == 0) {
         return new BitSet();
      } else {
         long[] a = Arrays.copyOf(elems, len);
         return new BitSet(a);
      }
   }

   public BitSet fromBitMaskNoCopy(final long[] elems) {
      return elems.length == 0 ? new BitSet() : new BitSet(elems);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BitSet$.class);
   }

   private BitSet$() {
   }
}
