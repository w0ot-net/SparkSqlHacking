package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SpecificIterableFactory;
import scala.collection.mutable.Builder;
import scala.runtime.ModuleSerializationProxy;

public final class BitSet$ implements SpecificIterableFactory, Serializable {
   public static final BitSet$ MODULE$ = new BitSet$();
   private static final long serialVersionUID = 3L;
   private static final BitSet empty;

   static {
      BitSet$ var10000 = MODULE$;
      empty = new BitSet.BitSet1(0L);
   }

   public Object apply(final Seq xs) {
      return SpecificIterableFactory.apply$(this, xs);
   }

   public Object fill(final int n, final Function0 elem) {
      return SpecificIterableFactory.fill$(this, n, elem);
   }

   public Factory specificIterableFactory() {
      return SpecificIterableFactory.specificIterableFactory$(this);
   }

   public BitSet fromSpecific(final IterableOnce it) {
      if (it instanceof BitSet) {
         return (BitSet)it;
      } else {
         Builder var10000 = this.newBuilder();
         if (var10000 == null) {
            throw null;
         } else {
            return (BitSet)((Builder)var10000.addAll(it)).result();
         }
      }
   }

   public final BitSet empty() {
      return empty;
   }

   public Builder newBuilder() {
      return scala.collection.mutable.BitSet$.MODULE$.newBuilder().mapResult((bs) -> MODULE$.fromBitMaskNoCopy(bs.elems()));
   }

   public BitSet scala$collection$immutable$BitSet$$createSmall(final long a, final long b) {
      return (BitSet)(b == 0L ? new BitSet.BitSet1(a) : new BitSet.BitSet2(a, b));
   }

   public BitSet fromBitMask(final long[] elems) {
      int len = elems.length;
      if (len == 0) {
         return this.empty();
      } else if (len == 1) {
         return new BitSet.BitSet1(elems[0]);
      } else if (len == 2) {
         return this.scala$collection$immutable$BitSet$$createSmall(elems[0], elems[1]);
      } else {
         long[] a = Arrays.copyOf(elems, len);
         return new BitSet.BitSetN(a);
      }
   }

   public BitSet fromBitMaskNoCopy(final long[] elems) {
      int len = elems.length;
      if (len == 0) {
         return this.empty();
      } else if (len == 1) {
         return new BitSet.BitSet1(elems[0]);
      } else {
         return (BitSet)(len == 2 ? this.scala$collection$immutable$BitSet$$createSmall(elems[0], elems[1]) : new BitSet.BitSetN(elems));
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BitSet$.class);
   }

   private BitSet$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
