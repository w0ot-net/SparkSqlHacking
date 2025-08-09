package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.package.;
import scala.runtime.BoxesRunTime;

public final class Merge$ {
   public static final Merge$ MODULE$ = new Merge$();

   public JValue merge(final JValue val1, final JValue val2, final MergeDep instance) {
      return instance.apply(val1, val2);
   }

   public List mergeFields(final List vs1, final List vs2) {
      return this.mergeRec$1(.MODULE$.Nil(), vs1, vs2);
   }

   public List mergeVals(final List vs1, final List vs2) {
      return this.mergeRec$2(.MODULE$.Nil(), vs1, vs2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeFields$1(final String xn$1, final Tuple2 x$1) {
      boolean var3;
      label23: {
         Object var10000 = x$1._1();
         if (var10000 == null) {
            if (xn$1 == null) {
               break label23;
            }
         } else if (var10000.equals(xn$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeFields$2(final Tuple2 y$1, final Tuple2 x$2) {
      boolean var10000;
      label23: {
         if (x$2 == null) {
            if (y$1 == null) {
               break label23;
            }
         } else if (x$2.equals(y$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private final List mergeRec$1(final List acc, final List xleft, final List yleft) {
      while(true) {
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (xleft == null) {
               break;
            }
         } else if (var10000.equals(xleft)) {
            break;
         }

         if (xleft instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)xleft;
            Tuple2 var10 = (Tuple2)var9.head();
            List xs = var9.next$access$1();
            if (var10 != null) {
               String xn = (String)var10._1();
               JValue xv = (JValue)var10._2();
               Option var14 = yleft.find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$mergeFields$1(xn, x$1)));
               if (var14 instanceof Some) {
                  Some var15 = (Some)var14;
                  Tuple2 y = (Tuple2)var15.value();
                  if (y != null) {
                     JValue yv = (JValue)y._2();
                     List var19 = (List)acc.$plus$plus((IterableOnce).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{JsonAST$.MODULE$.JField().apply(xn, this.merge(xv, yv, JValue$.MODULE$.jjj()))}))));
                     yleft = yleft.filterNot((x$2) -> BoxesRunTime.boxToBoolean($anonfun$mergeFields$2(y, x$2)));
                     xleft = xs;
                     acc = var19;
                     continue;
                  }
               }

               if (scala.None..MODULE$.equals(var14)) {
                  List var18 = (List)acc.$plus$plus((IterableOnce).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{JsonAST$.MODULE$.JField().apply(xn, xv)}))));
                  yleft = yleft;
                  xleft = xs;
                  acc = var18;
                  continue;
               }

               throw new MatchError(var14);
            }
         }

         throw new MatchError(xleft);
      }

      List var5 = (List)acc.$plus$plus(yleft);
      return var5;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeVals$1(final JValue x$5, final JValue x$3) {
      boolean var10000;
      label23: {
         if (x$3 == null) {
            if (x$5 == null) {
               break label23;
            }
         } else if (x$3.equals(x$5)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeVals$2(final JValue y$2, final JValue x$4) {
      boolean var10000;
      label23: {
         if (x$4 == null) {
            if (y$2 == null) {
               break label23;
            }
         } else if (x$4.equals(y$2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private final List mergeRec$2(final List acc, final List xleft, final List yleft) {
      while(true) {
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (xleft == null) {
               break;
            }
         } else if (var10000.equals(xleft)) {
            break;
         }

         if (xleft instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)xleft;
            JValue x = (JValue)var9.head();
            List xs = var9.next$access$1();
            Option var12 = yleft.find((x$3) -> BoxesRunTime.boxToBoolean($anonfun$mergeVals$1(x, x$3)));
            if (var12 instanceof Some) {
               Some var13 = (Some)var12;
               JValue y = (JValue)var13.value();
               List var16 = (List)acc.$plus$plus((IterableOnce).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new JValue[]{this.merge(x, y, JValue$.MODULE$.jjj())})));
               yleft = yleft.filterNot((x$4) -> BoxesRunTime.boxToBoolean($anonfun$mergeVals$2(y, x$4)));
               xleft = xs;
               acc = var16;
               continue;
            }

            if (scala.None..MODULE$.equals(var12)) {
               List var15 = (List)acc.$plus$plus((IterableOnce).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new JValue[]{x})));
               yleft = yleft;
               xleft = xs;
               acc = var15;
               continue;
            }

            throw new MatchError(var12);
         }

         throw new MatchError(xleft);
      }

      List var5 = (List)acc.$plus$plus(yleft);
      return var5;
   }

   private Merge$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
