package breeze.optimize.linear;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Array.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class KuhnMunkres$ implements BipartiteMatching {
   public static final KuhnMunkres$ MODULE$ = new KuhnMunkres$();

   public Tuple2 extractMatching(final Seq costs) {
      Tuple2 var10000;
      if (costs.length() > ((SeqOps)costs.apply(0)).length()) {
         double[][] newCosts = (double[][]).MODULE$.fill(((SeqOps)costs.apply(0)).length(), costs.length(), (JFunction0.mcD.sp)() -> (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), costs.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), ((SeqOps)costs.apply(0)).length()).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> newCosts[j][i] = BoxesRunTime.unboxToDouble(((SeqOps)costs.apply(i)).apply(j))));
         var10000 = new Tuple2(scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])newCosts), (row) -> scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.doubleArrayOps(row)), scala.reflect.ClassTag..MODULE$.apply(Seq.class)))), BoxesRunTime.boxToBoolean(true));
      } else {
         var10000 = new Tuple2(costs, BoxesRunTime.boxToBoolean(false));
      }

      Tuple2 var4 = var10000;
      if (var4 != null) {
         Seq costs2 = (Seq)var4._1();
         boolean inverted = var4._2$mcZ$sp();
         if (costs2 != null && true) {
            Tuple2 var2 = new Tuple2(costs2, BoxesRunTime.boxToBoolean(inverted));
            Seq costs2 = (Seq)var2._1();
            boolean inverted = var2._2$mcZ$sp();
            double[][] C = this.padMatrix(costs2);
            int n = scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])C));
            boolean[] rowCovered = (boolean[]).MODULE$.fill(n, (JFunction0.mcZ.sp)() -> false, scala.reflect.ClassTag..MODULE$.Boolean());
            boolean[] colCovered = (boolean[]).MODULE$.fill(n, (JFunction0.mcZ.sp)() -> false, scala.reflect.ClassTag..MODULE$.Boolean());
            IntRef primeR = IntRef.create(0);
            IntRef primeC = IntRef.create(0);
            int[][] path = (int[][]).MODULE$.fill(2 * n, 2 * n, (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int());
            int[][] marked = (int[][]).MODULE$.fill(n, n, (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int());

            for(int step = 1; step < 7; step = var25) {
               switch (step) {
                  case 1:
                     var25 = step1$1(n, C);
                     break;
                  case 2:
                     var25 = step2$1(n, C, rowCovered, colCovered, marked);
                     break;
                  case 3:
                     var25 = step3$1(n, marked, colCovered);
                     break;
                  case 4:
                     var25 = step4$1(marked, primeR, primeC, rowCovered, colCovered, n, C);
                     break;
                  case 5:
                     var25 = step5$1(path, primeR, primeC, rowCovered, colCovered, n, marked);
                     break;
                  case 6:
                     var25 = step6$1(n, rowCovered, C, colCovered);
                     break;
                  default:
                     throw new MatchError(BoxesRunTime.boxToInteger(step));
               }
            }

            ObjectRef answers = ObjectRef.create((int[]).MODULE$.fill(costs2.length(), (JFunction0.mcI.sp)() -> -1, scala.reflect.ClassTag..MODULE$.Int()));
            DoubleRef cost = DoubleRef.create((double)0.0F);
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), ((int[])answers.elem).length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
               Object qual$1 = scala.Predef..MODULE$.intArrayOps(marked[i]);
               Function1 x$1 = (x$10) -> x$10 == 1;
               int x$2 = scala.collection.ArrayOps..MODULE$.indexWhere$default$2$extension(qual$1);
               int j = scala.collection.ArrayOps..MODULE$.indexWhere$extension(qual$1, x$1, x$2);
               if (j >= 0) {
                  cost.elem += BoxesRunTime.unboxToDouble(((SeqOps)costs2.apply(i)).apply(j));
                  ((int[])answers.elem)[i] = j;
               }

            });
            if (inverted) {
               int[] answers2 = (int[]).MODULE$.fill(((SeqOps)costs2.apply(0)).length(), (JFunction0.mcI.sp)() -> -1, scala.reflect.ClassTag..MODULE$.Int());
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), ((int[])answers.elem).length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
                  int j = ((int[])answers.elem)[i];
                  if (j != -1) {
                     answers2[j] = i;
                  }

               });
               answers.elem = answers2;
            }

            return new Tuple2(scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq((int[])answers.elem), BoxesRunTime.boxToDouble(cost.elem));
         }
      }

      throw new MatchError(var4);
   }

   private double[][] padMatrix(final Seq costs) {
      int rows = costs.length();
      int cols = ((SeqOps)costs.apply(0)).length();
      int n = scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(rows), cols);
      double[][] ret = (double[][]).MODULE$.tabulate(n, n, (JFunction2.mcDII.sp)(i, j) -> i >= rows ? (double)0.0F : (j >= ((SeqOps)costs.apply(i)).length() ? (double)0.0F : BoxesRunTime.unboxToDouble(((SeqOps)costs.apply(i)).apply(j))), scala.reflect.ClassTag..MODULE$.Double());
      return ret;
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$extractMatching$9(final int n$1, final boolean[] rowCovered$1, final boolean[] colCovered$1, final double[][] C$1, final int i) {
      return scala.package..MODULE$.Iterator().range(0, n$1).withFilter((JFunction1.mcZI.sp)(j) -> !rowCovered$1[i] && !colCovered$1[j]).map((JFunction1.mcDI.sp)(j) -> C$1[i][j]);
   }

   private static final double findSmallestNotCovered$1(final int n$1, final boolean[] rowCovered$1, final boolean[] colCovered$1, final double[][] C$1) {
      Iterator mins = scala.package..MODULE$.Iterator().range(0, n$1).flatMap((i) -> $anonfun$extractMatching$9(n$1, rowCovered$1, colCovered$1, C$1, BoxesRunTime.unboxToInt(i)));
      return BoxesRunTime.unboxToDouble(mins.reduceLeft((JFunction2.mcDDD.sp)(x$2, x$3) -> scala.runtime.RichDouble..MODULE$.min$extension(scala.Predef..MODULE$.doubleWrapper(x$2), x$3)));
   }

   private static final Tuple2 findZero$1(final int n$1, final double[][] C$1, final boolean[] rowCovered$1, final boolean[] colCovered$1) {
      int row = -1;
      int col = -1;
      int i = 0;

      for(boolean done = false; !done && i < n$1; ++i) {
         for(int j = 0; j < n$1; ++j) {
            if (C$1[i][j] == (double)0 && !rowCovered$1[i] && !colCovered$1[j]) {
               row = i;
               col = j;
               done = true;
            }
         }
      }

      return new Tuple2.mcII.sp(row, col);
   }

   private static final void erasePrimes$1(final int n$1, final int[][] marked$1) {
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).withFilter((JFunction1.mcZI.sp)(j) -> marked$1[i][j] == 2).foreach((JFunction1.mcVI.sp)(j) -> marked$1[i][j] = 0));
   }

   private static final int findStarInRow$1(final int row, final int[][] marked$1) {
      Object qual$1 = scala.Predef..MODULE$.intArrayOps(marked$1[row]);
      Function1 x$1 = (x$4) -> 1 == x$4;
      int x$2 = scala.collection.ArrayOps..MODULE$.indexWhere$default$2$extension(qual$1);
      return scala.collection.ArrayOps..MODULE$.indexWhere$extension(qual$1, x$1, x$2);
   }

   private static final int findStarInCol$1(final int col, final int n$1, final int[][] marked$1) {
      Iterator qual$1 = scala.package..MODULE$.Iterator().range(0, n$1);
      Function1 x$1 = (i) -> marked$1[i][col] == 1;
      int x$2 = qual$1.indexWhere$default$2();
      return qual$1.indexWhere(x$1, x$2);
   }

   private static final int findPrimeInRow$1(final int row, final int[][] marked$1) {
      Object qual$1 = scala.Predef..MODULE$.intArrayOps(marked$1[row]);
      Function1 x$1 = (x$5) -> 2 == x$5;
      int x$2 = scala.collection.ArrayOps..MODULE$.indexWhere$default$2$extension(qual$1);
      return scala.collection.ArrayOps..MODULE$.indexWhere$extension(qual$1, x$1, x$2);
   }

   private static final void convertPath$1(final int[][] path, final int count, final int[][] marked$1) {
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), count).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         if (marked$1[path[i][0]][path[i][1]] == 1) {
            marked$1[path[i][0]][path[i][1]] = 0;
         } else {
            marked$1[path[i][0]][path[i][1]] = 1;
         }

      });
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$extractMatching$20(final double[][] C$1, final int i) {
      double min = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(C$1[i]).reduceLeft((JFunction2.mcDDD.sp)(x$6, x$7) -> scala.runtime.RichDouble..MODULE$.min$extension(scala.Predef..MODULE$.doubleWrapper(x$6), x$7)));
      return new Tuple2.mcID.sp(i, min);
   }

   // $FF: synthetic method
   public static final void $anonfun$extractMatching$22(final int n$1, final double[][] C$1, final Tuple2 x$8) {
      if (x$8 != null) {
         int i = x$8._1$mcI$sp();
         double min = x$8._2$mcD$sp();
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> {
            double[] var5 = C$1[i];
            var5[j] -= min;
         });
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$8);
      }
   }

   private static final int step1$1(final int n$1, final double[][] C$1) {
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).map((i) -> $anonfun$extractMatching$20(C$1, BoxesRunTime.unboxToInt(i))).foreach((x$8) -> {
         $anonfun$extractMatching$22(n$1, C$1, x$8);
         return BoxedUnit.UNIT;
      });
      return 2;
   }

   private static final int step2$1(final int n$1, final double[][] C$1, final boolean[] rowCovered$1, final boolean[] colCovered$1, final int[][] marked$1) {
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).withFilter((JFunction1.mcZI.sp)(c) -> C$1[r][c] == (double)0 && !rowCovered$1[r] && !colCovered$1[c]).foreach((JFunction1.mcVI.sp)(c) -> {
            marked$1[r][c] = 1;
            rowCovered$1[r] = true;
            colCovered$1[c] = true;
         }));
      Arrays.fill(rowCovered$1, false);
      Arrays.fill(colCovered$1, false);
      return 3;
   }

   private static final int step3$1(final int n$1, final int[][] marked$1, final boolean[] colCovered$1) {
      IntRef count = IntRef.create(0);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).withFilter((JFunction1.mcZI.sp)(j) -> marked$1[i][j] == 1).foreach((JFunction1.mcVI.sp)(j) -> {
            colCovered$1[j] = true;
            ++count.elem;
         }));
      return count.elem >= n$1 ? 7 : 4;
   }

   private static final int step4$1(final int[][] marked$1, final IntRef primeR$1, final IntRef primeC$1, final boolean[] rowCovered$1, final boolean[] colCovered$1, final int n$1, final double[][] C$1) {
      int star_col = -1;
      boolean done = false;
      int step = 0;

      while(!done) {
         Tuple2 var12 = findZero$1(n$1, C$1, rowCovered$1, colCovered$1);
         if (var12 == null) {
            throw new MatchError(var12);
         }

         int row = var12._1$mcI$sp();
         int col = var12._2$mcI$sp();
         Tuple2.mcII.sp var7 = new Tuple2.mcII.sp(row, col);
         int row = ((Tuple2)var7)._1$mcI$sp();
         int col = ((Tuple2)var7)._2$mcI$sp();
         if (row == -1) {
            done = true;
            step = 6;
         } else {
            marked$1[row][col] = 2;
            int starredCol = findStarInRow$1(row, marked$1);
            if (starredCol == -1) {
               done = true;
               primeR$1.elem = row;
               primeC$1.elem = col;
               step = 5;
            } else {
               rowCovered$1[row] = true;
               colCovered$1[starredCol] = false;
            }
         }
      }

      return step;
   }

   private static final int step5$1(final int[][] path$2, final IntRef primeR$1, final IntRef primeC$1, final boolean[] rowCovered$1, final boolean[] colCovered$1, final int n$1, final int[][] marked$1) {
      int count = 0;
      path$2[count][0] = primeR$1.elem;
      path$2[count][1] = primeC$1.elem;
      boolean done = false;

      while(!done) {
         int row = findStarInCol$1(path$2[count][1], n$1, marked$1);
         if (row >= 0) {
            ++count;
            path$2[count][0] = row;
            path$2[count][1] = path$2[count - 1][1];
         } else {
            done = true;
         }

         if (!done) {
            int col = findPrimeInRow$1(path$2[count][0], marked$1);
            ++count;
            path$2[count][0] = path$2[count - 1][0];
            path$2[count][1] = col;
         }
      }

      convertPath$1(path$2, count, marked$1);
      Arrays.fill(rowCovered$1, false);
      Arrays.fill(colCovered$1, false);
      erasePrimes$1(n$1, marked$1);
      return 3;
   }

   private static final int step6$1(final int n$1, final boolean[] rowCovered$1, final double[][] C$1, final boolean[] colCovered$1) {
      double min = findSmallestNotCovered$1(n$1, rowCovered$1, colCovered$1, C$1);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n$1).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
            if (rowCovered$1[r]) {
               double[] var7 = C$1[r];
               var7[c] += min;
            }

            if (!colCovered$1[c]) {
               double[] var8 = C$1[r];
               var8[c] -= min;
            }

         }));
      return 4;
   }

   private KuhnMunkres$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
