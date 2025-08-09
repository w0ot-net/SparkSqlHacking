package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

public final class LongMap$ implements Serializable {
   public static final LongMap$ MODULE$ = new LongMap$();
   private static final Function1 scala$collection$mutable$LongMap$$exceptionDefault = (k) -> $anonfun$exceptionDefault$1(BoxesRunTime.unboxToLong(k));

   private final int IndexMask() {
      return 1073741823;
   }

   private final int MissingBit() {
      return Integer.MIN_VALUE;
   }

   private final int VacantBit() {
      return 1073741824;
   }

   private final int MissVacant() {
      return -1073741824;
   }

   public Function1 scala$collection$mutable$LongMap$$exceptionDefault() {
      return scala$collection$mutable$LongMap$$exceptionDefault;
   }

   public LongMap apply(final scala.collection.immutable.Seq elems) {
      return this.buildFromIterableOnce(elems);
   }

   private LongMap buildFromIterableOnce(final IterableOnce elems) {
      int sz = elems.knownSize();
      if (sz < 0) {
         sz = 4;
      }

      LongMap lm = new LongMap(sz * 2);
      elems.iterator().foreach((x0$1) -> {
         $anonfun$buildFromIterableOnce$1(lm, x0$1);
         return BoxedUnit.UNIT;
      });
      if (lm.size() < sz >> 3) {
         lm.repack();
      }

      return lm;
   }

   public LongMap empty() {
      return new LongMap();
   }

   public LongMap withDefault(final Function1 default) {
      return new LongMap(default);
   }

   public LongMap from(final IterableOnce source) {
      return source instanceof LongMap ? ((LongMap)source).clone() : this.buildFromIterableOnce(source);
   }

   public ReusableBuilder newBuilder() {
      return new LongMap.LongMapBuilder();
   }

   public LongMap fromZip(final long[] keys, final Object values) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int sz = Math.min(keys.length, Array.getLength(values));
      LongMap lm = new LongMap(sz * 2);

      for(int i = 0; i < sz; ++i) {
         lm.update(keys[i], ScalaRunTime$.MODULE$.array_apply(values, i));
      }

      if (lm.size() < sz >> 3) {
         lm.repack();
      }

      return lm;
   }

   public LongMap fromZip(final scala.collection.Iterable keys, final scala.collection.Iterable values) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int sz = Math.min(keys.size(), values.size());
      LongMap lm = new LongMap(sz * 2);
      Iterator ki = keys.iterator();
      Iterator vi = values.iterator();

      while(ki.hasNext() && vi.hasNext()) {
         lm.update(BoxesRunTime.unboxToLong(ki.next()), vi.next());
      }

      if (lm.size() < sz >> 3) {
         lm.repack();
      }

      return lm;
   }

   public Factory toFactory(final LongMap$ dummy) {
      return LongMap.ToFactory$.MODULE$;
   }

   public BuildFrom toBuildFrom(final LongMap$ factory) {
      return LongMap.ToBuildFrom$.MODULE$;
   }

   public Factory iterableFactory() {
      return LongMap.ToFactory$.MODULE$;
   }

   public BuildFrom buildFromLongMap() {
      return LongMap.ToBuildFrom$.MODULE$;
   }

   public int scala$collection$mutable$LongMap$$repackMask(final int mask, final int _size, final int _vacant) {
      int m = mask;
      if ((double)(_size + _vacant) >= (double)0.5F * (double)mask && !((double)_vacant > 0.2 * (double)mask)) {
         m = (mask << 1) + 1 & 1073741823;
      }

      while(m > 8 && _size < m >>> 3) {
         m >>>= 1;
      }

      return m;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LongMap$.class);
   }

   // $FF: synthetic method
   public static final Nothing$ $anonfun$exceptionDefault$1(final long k) {
      throw new NoSuchElementException(Long.toString(k));
   }

   // $FF: synthetic method
   public static final void $anonfun$buildFromIterableOnce$1(final LongMap lm$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long k = x0$1._1$mcJ$sp();
         Object v = x0$1._2();
         lm$2.update(k, v);
      } else {
         throw new MatchError((Object)null);
      }
   }

   private LongMap$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
