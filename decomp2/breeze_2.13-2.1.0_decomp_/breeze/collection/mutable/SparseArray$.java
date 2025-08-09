package breeze.collection.mutable;

import breeze.storage.Zero;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Array.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparseArray$ implements Serializable {
   public static final SparseArray$ MODULE$ = new SparseArray$();

   public SparseArray apply(final Seq values, final ClassTag evidence$3, final Zero evidence$4) {
      SparseArray rv = new SparseArray(.MODULE$.range(0, values.length()), values.toArray(evidence$3), values.length(), values.length(), ((Zero)scala.Predef..MODULE$.implicitly(evidence$4)).zero());
      rv.compact();
      return rv;
   }

   public SparseArray fill(final int length, final Function0 value, final ClassTag evidence$5, final Zero evidence$6) {
      SparseArray var10000;
      if (!BoxesRunTime.equals(value.apply(), ((Zero)scala.Predef..MODULE$.implicitly(evidence$6)).zero())) {
         SparseArray rv = new SparseArray(length, evidence$5, evidence$6);

         for(int i = 0; i < length; ++i) {
            rv.update(i, value.apply());
         }

         var10000 = rv;
      } else {
         var10000 = new SparseArray(length, evidence$5, evidence$6);
      }

      return var10000;
   }

   public SparseArray create(final int length, final Seq values, final ClassTag evidence$7, final Zero evidence$8) {
      SparseArray rv = new SparseArray(length, evidence$7, evidence$8);
      values.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$create$1(check$ifrefutable$1))).foreach((x$2) -> {
         $anonfun$create$2(rv, x$2);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public SparseArray tabulate(final int length, final Function1 fn, final ClassTag evidence$9, final Zero evidence$10) {
      SparseArray rv = new SparseArray(length, evidence$9, evidence$10);

      for(int i = 0; i < length; ++i) {
         Object v = fn.apply(BoxesRunTime.boxToInteger(i));
         if (!BoxesRunTime.equals(v, rv.default())) {
            rv.update(i, v);
         }
      }

      rv.compact();
      return rv;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparseArray$.class);
   }

   public SparseArray apply$mDc$sp(final Seq values, final ClassTag evidence$3, final Zero evidence$4) {
      SparseArray rv = new SparseArray$mcD$sp(.MODULE$.range(0, values.length()), (double[])values.toArray(evidence$3), values.length(), values.length(), ((Zero)scala.Predef..MODULE$.implicitly(evidence$4)).zero$mcD$sp());
      rv.compact();
      return rv;
   }

   public SparseArray apply$mFc$sp(final Seq values, final ClassTag evidence$3, final Zero evidence$4) {
      SparseArray rv = new SparseArray$mcF$sp(.MODULE$.range(0, values.length()), (float[])values.toArray(evidence$3), values.length(), values.length(), ((Zero)scala.Predef..MODULE$.implicitly(evidence$4)).zero$mcF$sp());
      rv.compact();
      return rv;
   }

   public SparseArray apply$mIc$sp(final Seq values, final ClassTag evidence$3, final Zero evidence$4) {
      SparseArray rv = new SparseArray$mcI$sp(.MODULE$.range(0, values.length()), (int[])values.toArray(evidence$3), values.length(), values.length(), ((Zero)scala.Predef..MODULE$.implicitly(evidence$4)).zero$mcI$sp());
      rv.compact();
      return rv;
   }

   public SparseArray fill$mDc$sp(final int length, final Function0 value, final ClassTag evidence$5, final Zero evidence$6) {
      Object var10000;
      if (value.apply$mcD$sp() != ((Zero)scala.Predef..MODULE$.implicitly(evidence$6)).zero$mcD$sp()) {
         SparseArray rv = new SparseArray$mcD$sp(length, evidence$5, evidence$6);

         for(int i = 0; i < length; ++i) {
            rv.update$mcD$sp(i, value.apply$mcD$sp());
         }

         var10000 = rv;
      } else {
         var10000 = new SparseArray$mcD$sp(length, evidence$5, evidence$6);
      }

      return (SparseArray)var10000;
   }

   public SparseArray fill$mFc$sp(final int length, final Function0 value, final ClassTag evidence$5, final Zero evidence$6) {
      Object var10000;
      if (value.apply$mcF$sp() != ((Zero)scala.Predef..MODULE$.implicitly(evidence$6)).zero$mcF$sp()) {
         SparseArray rv = new SparseArray$mcF$sp(length, evidence$5, evidence$6);

         for(int i = 0; i < length; ++i) {
            rv.update$mcF$sp(i, value.apply$mcF$sp());
         }

         var10000 = rv;
      } else {
         var10000 = new SparseArray$mcF$sp(length, evidence$5, evidence$6);
      }

      return (SparseArray)var10000;
   }

   public SparseArray fill$mIc$sp(final int length, final Function0 value, final ClassTag evidence$5, final Zero evidence$6) {
      Object var10000;
      if (value.apply$mcI$sp() != ((Zero)scala.Predef..MODULE$.implicitly(evidence$6)).zero$mcI$sp()) {
         SparseArray rv = new SparseArray$mcI$sp(length, evidence$5, evidence$6);

         for(int i = 0; i < length; ++i) {
            rv.update$mcI$sp(i, value.apply$mcI$sp());
         }

         var10000 = rv;
      } else {
         var10000 = new SparseArray$mcI$sp(length, evidence$5, evidence$6);
      }

      return (SparseArray)var10000;
   }

   public SparseArray create$mDc$sp(final int length, final Seq values, final ClassTag evidence$7, final Zero evidence$8) {
      SparseArray rv = new SparseArray$mcD$sp(length, evidence$7, evidence$8);
      values.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$create$3(check$ifrefutable$1))).foreach((x$2) -> {
         $anonfun$create$4(rv, x$2);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public SparseArray create$mFc$sp(final int length, final Seq values, final ClassTag evidence$7, final Zero evidence$8) {
      SparseArray rv = new SparseArray$mcF$sp(length, evidence$7, evidence$8);
      values.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$create$5(check$ifrefutable$1))).foreach((x$2) -> {
         $anonfun$create$6(rv, x$2);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public SparseArray create$mIc$sp(final int length, final Seq values, final ClassTag evidence$7, final Zero evidence$8) {
      SparseArray rv = new SparseArray$mcI$sp(length, evidence$7, evidence$8);
      values.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$create$7(check$ifrefutable$1))).foreach((x$2) -> {
         $anonfun$create$8(rv, x$2);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public SparseArray tabulate$mDc$sp(final int length, final Function1 fn, final ClassTag evidence$9, final Zero evidence$10) {
      SparseArray rv = new SparseArray$mcD$sp(length, evidence$9, evidence$10);

      for(int i = 0; i < length; ++i) {
         double v = fn.apply$mcDI$sp(i);
         if (v != rv.default$mcD$sp()) {
            rv.update$mcD$sp(i, v);
         }
      }

      rv.compact();
      return rv;
   }

   public SparseArray tabulate$mFc$sp(final int length, final Function1 fn, final ClassTag evidence$9, final Zero evidence$10) {
      SparseArray rv = new SparseArray$mcF$sp(length, evidence$9, evidence$10);

      for(int i = 0; i < length; ++i) {
         float v = fn.apply$mcFI$sp(i);
         if (v != rv.default$mcF$sp()) {
            rv.update$mcF$sp(i, v);
         }
      }

      rv.compact();
      return rv;
   }

   public SparseArray tabulate$mIc$sp(final int length, final Function1 fn, final ClassTag evidence$9, final Zero evidence$10) {
      SparseArray rv = new SparseArray$mcI$sp(length, evidence$9, evidence$10);

      for(int i = 0; i < length; ++i) {
         int v = fn.apply$mcII$sp(i);
         if (v != rv.default$mcI$sp()) {
            rv.update$mcI$sp(i, v);
         }
      }

      rv.compact();
      return rv;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$create$2(final SparseArray rv$1, final Tuple2 x$2) {
      if (x$2 != null) {
         int k = x$2._1$mcI$sp();
         Object v = x$2._2();
         rv$1.update(k, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$3(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$create$4(final SparseArray rv$2, final Tuple2 x$2) {
      if (x$2 != null) {
         int k = x$2._1$mcI$sp();
         double v = x$2._2$mcD$sp();
         rv$2.update$mcD$sp(k, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$5(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$create$6(final SparseArray rv$3, final Tuple2 x$2) {
      if (x$2 != null) {
         int k = x$2._1$mcI$sp();
         float v = BoxesRunTime.unboxToFloat(x$2._2());
         rv$3.update$mcF$sp(k, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$7(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$create$8(final SparseArray rv$4, final Tuple2 x$2) {
      if (x$2 != null) {
         int k = x$2._1$mcI$sp();
         int v = x$2._2$mcI$sp();
         rv$4.update$mcI$sp(k, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   private SparseArray$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
