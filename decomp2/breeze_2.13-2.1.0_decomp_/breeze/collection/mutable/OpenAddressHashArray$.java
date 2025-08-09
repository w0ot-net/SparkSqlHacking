package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Zero;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OpenAddressHashArray$ implements Serializable {
   public static final OpenAddressHashArray$ MODULE$ = new OpenAddressHashArray$();

   public ConfigurableDefault $lessinit$greater$default$5() {
      return ConfigurableDefault$.MODULE$.default();
   }

   public OpenAddressHashArray apply(final Seq values, final ClassTag evidence$1, final Zero evidence$2) {
      OpenAddressHashArray rv = new OpenAddressHashArray(values.length(), evidence$1, evidence$2);
      Object zero = ((Zero).MODULE$.implicitly(evidence$2)).zero();
      ((IterableOps)values.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).withFilter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$2(zero, x$5))).foreach((x$6) -> {
         $anonfun$apply$3(rv, x$6);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public int breeze$collection$mutable$OpenAddressHashArray$$calculateSize(final int size) {
      return size < 4 ? 4 : this.nextPowerOfTwo(size - 1);
   }

   private int nextPowerOfTwo(final int size) {
      .MODULE$.require(size < 1073741824);
      int v = size | size >> 1;
      v |= v >> 2;
      v |= v >> 4;
      v |= v >> 8;
      v |= v >> 16;
      ++v;
      return v;
   }

   public int[] breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(final int size) {
      int[] arr = new int[size];
      Arrays.fill(arr, -1);
      return arr;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OpenAddressHashArray$.class);
   }

   public OpenAddressHashArray apply$mDc$sp(final Seq values, final ClassTag evidence$1, final Zero evidence$2) {
      OpenAddressHashArray rv = new OpenAddressHashArray$mcD$sp(values.length(), evidence$1, evidence$2);
      double zero = ((Zero).MODULE$.implicitly(evidence$2)).zero$mcD$sp();
      ((IterableOps)values.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$4(check$ifrefutable$1))).withFilter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(zero, x$5))).foreach((x$6) -> {
         $anonfun$apply$6(rv, x$6);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public OpenAddressHashArray apply$mFc$sp(final Seq values, final ClassTag evidence$1, final Zero evidence$2) {
      OpenAddressHashArray rv = new OpenAddressHashArray$mcF$sp(values.length(), evidence$1, evidence$2);
      float zero = ((Zero).MODULE$.implicitly(evidence$2)).zero$mcF$sp();
      ((IterableOps)values.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$7(check$ifrefutable$1))).withFilter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$8(zero, x$5))).foreach((x$6) -> {
         $anonfun$apply$9(rv, x$6);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public OpenAddressHashArray apply$mIc$sp(final Seq values, final ClassTag evidence$1, final Zero evidence$2) {
      OpenAddressHashArray rv = new OpenAddressHashArray$mcI$sp(values.length(), evidence$1, evidence$2);
      int zero = ((Zero).MODULE$.implicitly(evidence$2)).zero$mcI$sp();
      ((IterableOps)values.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$10(check$ifrefutable$1))).withFilter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$11(zero, x$5))).foreach((x$6) -> {
         $anonfun$apply$12(rv, x$6);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public OpenAddressHashArray apply$mJc$sp(final Seq values, final ClassTag evidence$1, final Zero evidence$2) {
      OpenAddressHashArray rv = new OpenAddressHashArray$mcJ$sp(values.length(), evidence$1, evidence$2);
      long zero = ((Zero).MODULE$.implicitly(evidence$2)).zero$mcJ$sp();
      ((IterableOps)values.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$13(check$ifrefutable$1))).withFilter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$14(zero, x$5))).foreach((x$6) -> {
         $anonfun$apply$15(rv, x$6);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$2(final Object zero$1, final Tuple2 x$5) {
      if (x$5 != null) {
         Object v = x$5._1();
         boolean var2 = !BoxesRunTime.equals(v, zero$1);
         return var2;
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$3(final OpenAddressHashArray rv$1, final Tuple2 x$6) {
      if (x$6 != null) {
         Object v = x$6._1();
         int i = x$6._2$mcI$sp();
         rv$1.update(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$4(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$5(final double zero$2, final Tuple2 x$5) {
      if (x$5 != null) {
         double v = x$5._1$mcD$sp();
         boolean var3 = v != zero$2;
         return var3;
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$6(final OpenAddressHashArray rv$2, final Tuple2 x$6) {
      if (x$6 != null) {
         double v = x$6._1$mcD$sp();
         int i = x$6._2$mcI$sp();
         rv$2.update$mcD$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$7(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$8(final float zero$3, final Tuple2 x$5) {
      if (x$5 != null) {
         float v = BoxesRunTime.unboxToFloat(x$5._1());
         boolean var2 = v != zero$3;
         return var2;
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$9(final OpenAddressHashArray rv$3, final Tuple2 x$6) {
      if (x$6 != null) {
         float v = BoxesRunTime.unboxToFloat(x$6._1());
         int i = x$6._2$mcI$sp();
         rv$3.update$mcF$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$10(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$11(final int zero$4, final Tuple2 x$5) {
      if (x$5 != null) {
         int v = x$5._1$mcI$sp();
         boolean var2 = v != zero$4;
         return var2;
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$12(final OpenAddressHashArray rv$4, final Tuple2 x$6) {
      if (x$6 != null) {
         int v = x$6._1$mcI$sp();
         int i = x$6._2$mcI$sp();
         rv$4.update$mcI$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$13(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$14(final long zero$5, final Tuple2 x$5) {
      if (x$5 != null) {
         long v = x$5._1$mcJ$sp();
         boolean var3 = v != zero$5;
         return var3;
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$15(final OpenAddressHashArray rv$5, final Tuple2 x$6) {
      if (x$6 != null) {
         long v = x$6._1$mcJ$sp();
         int i = x$6._2$mcI$sp();
         rv$5.update$mcJ$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   private OpenAddressHashArray$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
