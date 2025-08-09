package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Comparator;
import scala.Function2;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ManifestFactory;
import scala.reflect.package.;
import scala.runtime.BoxesRunTime;

public final class CollectionsUtils$ {
   public static final CollectionsUtils$ MODULE$ = new CollectionsUtils$();

   public Function2 makeBinarySearch(final Ordering evidence$1, final ClassTag evidence$2) {
      ClassTag var4 = .MODULE$.classTag(evidence$2);
      ManifestFactory.FloatManifest var10000 = scala.reflect.ClassTag..MODULE$.Float();
      if (var10000 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$1(l, x));
         }
      } else if (var10000.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$1(l, x));
      }

      ManifestFactory.DoubleManifest var13 = scala.reflect.ClassTag..MODULE$.Double();
      if (var13 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$2(l, x));
         }
      } else if (var13.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$2(l, x));
      }

      ManifestFactory.ByteManifest var14 = scala.reflect.ClassTag..MODULE$.Byte();
      if (var14 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$3(l, x));
         }
      } else if (var14.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$3(l, x));
      }

      ManifestFactory.CharManifest var15 = scala.reflect.ClassTag..MODULE$.Char();
      if (var15 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$4(l, x));
         }
      } else if (var15.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$4(l, x));
      }

      ManifestFactory.ShortManifest var16 = scala.reflect.ClassTag..MODULE$.Short();
      if (var16 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$5(l, x));
         }
      } else if (var16.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$5(l, x));
      }

      ManifestFactory.IntManifest var17 = scala.reflect.ClassTag..MODULE$.Int();
      if (var17 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$6(l, x));
         }
      } else if (var17.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$6(l, x));
      }

      ManifestFactory.LongManifest var18 = scala.reflect.ClassTag..MODULE$.Long();
      if (var18 == null) {
         if (var4 == null) {
            return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$7(l, x));
         }
      } else if (var18.equals(var4)) {
         return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$7(l, x));
      }

      Comparator comparator = (Comparator)scala.Predef..MODULE$.implicitly(evidence$1);
      return (l, x) -> BoxesRunTime.boxToInteger($anonfun$makeBinarySearch$8(comparator, l, x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$1(final Object l, final Object x) {
      return Arrays.binarySearch((float[])l, BoxesRunTime.unboxToFloat(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$2(final Object l, final Object x) {
      return Arrays.binarySearch((double[])l, BoxesRunTime.unboxToDouble(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$3(final Object l, final Object x) {
      return Arrays.binarySearch((byte[])l, BoxesRunTime.unboxToByte(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$4(final Object l, final Object x) {
      return Arrays.binarySearch((char[])l, BoxesRunTime.unboxToChar(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$5(final Object l, final Object x) {
      return Arrays.binarySearch((short[])l, BoxesRunTime.unboxToShort(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$6(final Object l, final Object x) {
      return Arrays.binarySearch((int[])l, BoxesRunTime.unboxToInt(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$7(final Object l, final Object x) {
      return Arrays.binarySearch((long[])l, BoxesRunTime.unboxToLong(x));
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBinarySearch$8(final Comparator comparator$1, final Object l, final Object x) {
      return Arrays.binarySearch(l, x, comparator$1);
   }

   private CollectionsUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
