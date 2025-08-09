package breeze.linalg.support;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.MatchError;
import scala.Tuple1;
import scala.Tuple10;
import scala.Tuple11;
import scala.Tuple12;
import scala.Tuple13;
import scala.Tuple14;
import scala.Tuple15;
import scala.Tuple16;
import scala.Tuple17;
import scala.Tuple18;
import scala.Tuple19;
import scala.Tuple2;
import scala.Tuple20;
import scala.Tuple21;
import scala.Tuple22;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.Tuple7;
import scala.Tuple8;
import scala.Tuple9;
import scala.collection.SeqOps;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

public final class LiteralRow$ {
   public static final LiteralRow$ MODULE$ = new LiteralRow$();

   public LiteralRow array() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Object arr, final Function2 fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), scala.runtime.ScalaRunTime..MODULE$.array_length(arr)).foreach((i) -> $anonfun$foreach$1(fn, arr, BoxesRunTime.unboxToInt(i)));
         }

         public int length(final Object arr) {
            return scala.runtime.ScalaRunTime..MODULE$.array_length(arr);
         }

         // $FF: synthetic method
         public static final Object $anonfun$foreach$1(final Function2 fn$1, final Object arr$1, final int i) {
            return fn$1.apply(BoxesRunTime.boxToInteger(i), scala.runtime.ScalaRunTime..MODULE$.array_apply(arr$1, i));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow dv() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final DenseVector arr, final Function2 fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), arr.length()).foreach((i) -> $anonfun$foreach$2(fn, arr, BoxesRunTime.unboxToInt(i)));
         }

         public int length(final DenseVector arr) {
            return arr.length();
         }

         // $FF: synthetic method
         public static final Object $anonfun$foreach$2(final Function2 fn$2, final DenseVector arr$2, final int i) {
            return fn$2.apply(BoxesRunTime.boxToInteger(i), arr$2.apply(i));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow seq(final scala..less.colon.less ev) {
      return new LiteralRow(ev) {
         private final scala..less.colon.less ev$1;

         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Object arr, final Function2 fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), ((SeqOps)this.ev$1.apply(arr)).length()).foreach((i) -> $anonfun$foreach$3(this, fn, arr, BoxesRunTime.unboxToInt(i)));
         }

         public int length(final Object arr) {
            return ((SeqOps)this.ev$1.apply(arr)).length();
         }

         // $FF: synthetic method
         public static final Object $anonfun$foreach$3(final Object $this, final Function2 fn$3, final Object arr$3, final int i) {
            return fn$3.apply(BoxesRunTime.boxToInteger(i), ((SeqOps)$this.ev$1.apply(arr$3)).apply(i));
         }

         public {
            this.ev$1 = ev$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow vLiteral() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Object tup, final Function2 fn) {
            fn.apply(BoxesRunTime.boxToInteger(0), tup);
         }

         public int length(final Object tup) {
            return 1;
         }
      };
   }

   public LiteralRow tuple1() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple1 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$4(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple1 tup) {
            return 1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$4(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple2() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple2 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$6(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple2 tup) {
            return 2;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$6(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple3() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple3 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$8(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple3 tup) {
            return 3;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$8(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple4() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple4 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$10(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple4 tup) {
            return 4;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$10(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple5() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple5 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$12(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple5 tup) {
            return 5;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$12(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple6() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple6 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$14(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple6 tup) {
            return 6;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$14(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple7() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple7 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$16(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple7 tup) {
            return 7;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$16(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple8() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple8 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$18(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple8 tup) {
            return 8;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$18(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple9() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple9 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$20(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple9 tup) {
            return 9;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$20(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple10() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple10 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$22(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple10 tup) {
            return 10;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$22(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple11() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple11 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$24(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple11 tup) {
            return 11;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$24(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple12() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple12 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$26(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple12 tup) {
            return 12;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$26(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple13() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple13 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$28(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple13 tup) {
            return 13;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$28(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple14() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple14 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$30(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple14 tup) {
            return 14;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$30(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple15() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple15 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$32(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple15 tup) {
            return 15;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$32(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple16() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple16 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$34(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple16 tup) {
            return 16;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$34(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple17() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple17 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$36(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple17 tup) {
            return 17;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$36(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple18() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple18 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$38(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple18 tup) {
            return 18;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$38(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple19() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple19 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$40(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple19 tup) {
            return 19;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$40(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple20() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple20 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$42(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple20 tup) {
            return 20;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$42(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple21() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple21 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$44(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple21 tup) {
            return 21;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$44(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LiteralRow tuple22() {
      return new LiteralRow() {
         public void foreach$mcZ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcZ$sp$(this, row, fn);
         }

         public void foreach$mcB$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcB$sp$(this, row, fn);
         }

         public void foreach$mcC$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcC$sp$(this, row, fn);
         }

         public void foreach$mcD$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcD$sp$(this, row, fn);
         }

         public void foreach$mcF$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcF$sp$(this, row, fn);
         }

         public void foreach$mcI$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcI$sp$(this, row, fn);
         }

         public void foreach$mcJ$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcJ$sp$(this, row, fn);
         }

         public void foreach$mcS$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcS$sp$(this, row, fn);
         }

         public void foreach$mcV$sp(final Object row, final Function2 fn) {
            LiteralRow.foreach$mcV$sp$(this, row, fn);
         }

         public void foreach(final Tuple22 tup, final Function2 fn) {
            tup.productIterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$46(check$ifrefutable$1))).foreach((x$1) -> {
               if (x$1 != null) {
                  Object v = x$1._1();
                  int i = x$1._2$mcI$sp();
                  Object var2 = fn.apply(BoxesRunTime.boxToInteger(i), v);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
         }

         public int length(final Tuple22 tup) {
            return 22;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$46(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private LiteralRow$() {
   }
}
