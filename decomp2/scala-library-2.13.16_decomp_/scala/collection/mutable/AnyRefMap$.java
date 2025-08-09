package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime$;

/** @deprecated */
public final class AnyRefMap$ implements Serializable {
   public static final AnyRefMap$ MODULE$ = new AnyRefMap$();
   private static final AnyRefMap.ExceptionDefault scala$collection$mutable$AnyRefMap$$exceptionDefault = new AnyRefMap.ExceptionDefault();

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

   public AnyRefMap.ExceptionDefault scala$collection$mutable$AnyRefMap$$exceptionDefault() {
      return scala$collection$mutable$AnyRefMap$$exceptionDefault;
   }

   public AnyRefMap apply(final scala.collection.immutable.Seq elems) {
      return this.buildFromIterableOnce(elems);
   }

   public ReusableBuilder newBuilder() {
      return new AnyRefMap.AnyRefMapBuilder();
   }

   private AnyRefMap buildFromIterableOnce(final IterableOnce elems) {
      int sz = elems.knownSize();
      if (sz < 0) {
         sz = 4;
      }

      AnyRefMap arm = new AnyRefMap(sz * 2);
      elems.iterator().foreach((x0$1) -> {
         $anonfun$buildFromIterableOnce$1(arm, x0$1);
         return BoxedUnit.UNIT;
      });
      if (arm.size() < sz >> 3) {
         arm.repack();
      }

      return arm;
   }

   public AnyRefMap empty() {
      return new AnyRefMap();
   }

   public AnyRefMap withDefault(final Function1 default) {
      return new AnyRefMap(default);
   }

   public AnyRefMap from(final IterableOnce source) {
      return source instanceof AnyRefMap ? ((AnyRefMap)source).clone() : this.buildFromIterableOnce(source);
   }

   public AnyRefMap fromZip(final Object[] keys, final Object values) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int sz = Math.min(keys.length, Array.getLength(values));
      AnyRefMap arm = new AnyRefMap(sz * 2);

      for(int i = 0; i < sz; ++i) {
         arm.update(keys[i], ScalaRunTime$.MODULE$.array_apply(values, i));
      }

      if (arm.size() < sz >> 3) {
         arm.repack();
      }

      return arm;
   }

   public AnyRefMap fromZip(final Iterable keys, final Iterable values) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int sz = Math.min(keys.size(), values.size());
      AnyRefMap arm = new AnyRefMap(sz * 2);
      Iterator ki = keys.iterator();
      Iterator vi = values.iterator();

      while(ki.hasNext() && vi.hasNext()) {
         arm.update(ki.next(), vi.next());
      }

      if (arm.size() < sz >> 3) {
         arm.repack();
      }

      return arm;
   }

   public Factory toFactory(final AnyRefMap$ dummy) {
      return AnyRefMap.ToFactory$.MODULE$;
   }

   public BuildFrom toBuildFrom(final AnyRefMap$ factory) {
      return AnyRefMap.ToBuildFrom$.MODULE$;
   }

   public Factory iterableFactory() {
      return AnyRefMap.ToFactory$.MODULE$;
   }

   public BuildFrom buildFromAnyRefMap() {
      return AnyRefMap.ToBuildFrom$.MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnyRefMap$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$buildFromIterableOnce$1(final AnyRefMap arm$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v = x0$1._2();
         arm$3.update(k, v);
      } else {
         throw new MatchError((Object)null);
      }
   }

   private AnyRefMap$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
