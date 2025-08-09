package breeze.optimize.linear;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.BitSet;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class CompetitiveLinking$ implements BipartiteMatching {
   public static final CompetitiveLinking$ MODULE$ = new CompetitiveLinking$();

   public Tuple2 extractMatching(final Seq matchingPotentials) {
      int n = matchingPotentials.length();
      int m = ((SeqOps)matchingPotentials.apply(0)).length();
      .MODULE$.require(m >= n, () -> "Column dimension must be at least the size of row dim.");
      Iterator predsIt = matchingPotentials.iterator().zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$extractMatching$2(check$ifrefutable$1))).flatMap((x$2) -> {
         if (x$2 != null) {
            Seq arr = (Seq)x$2._1();
            int i = x$2._2$mcI$sp();
            Iterator var1 = arr.iterator().zipWithIndex().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$extractMatching$4(check$ifrefutable$2))).map((x$1) -> {
               if (x$1 != null) {
                  double w = x$1._1$mcD$sp();
                  int j = x$1._2$mcI$sp();
                  CompetitiveLinking.Prediction var2 = new CompetitiveLinking.Prediction(i, j, w);
                  return var2;
               } else {
                  throw new MatchError(x$1);
               }
            });
            return var1;
         } else {
            throw new MatchError(x$2);
         }
      });
      Seq preds = (Seq)predsIt.toSeq().sortWith((x$3, x$4) -> BoxesRunTime.boxToBoolean($anonfun$extractMatching$6(x$3, x$4)));
      BitSet leftSet = new BitSet(n);
      BitSet rightSet = new BitSet(m);
      int[] matching = (int[])scala.Array..MODULE$.fill(n, (JFunction0.mcI.sp)() -> -1, scala.reflect.ClassTag..MODULE$.Int());
      DoubleRef score = DoubleRef.create((double)0.0F);
      preds.iterator().takeWhile((x$5) -> BoxesRunTime.boxToBoolean($anonfun$extractMatching$8(leftSet, n, rightSet, m, x$5))).withFilter((pred) -> BoxesRunTime.boxToBoolean($anonfun$extractMatching$9(leftSet, rightSet, pred))).foreach((pred) -> {
         $anonfun$extractMatching$10(matching, leftSet, rightSet, score, pred);
         return BoxedUnit.UNIT;
      });
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.intArrayOps(matching), (JFunction1.mcVI.sp)(a) -> .MODULE$.assert(a >= 0));
      return new Tuple2(scala.collection.compat.immutable.package..MODULE$.ArraySeq().unsafeWrapArray(matching), BoxesRunTime.boxToDouble(score.elem));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractMatching$2(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractMatching$4(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractMatching$6(final CompetitiveLinking.Prediction x$3, final CompetitiveLinking.Prediction x$4) {
      return x$3.v() < x$4.v();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractMatching$8(final BitSet leftSet$1, final int n$1, final BitSet rightSet$1, final int m$1, final CompetitiveLinking.Prediction x$5) {
      return leftSet$1.size() < n$1 || rightSet$1.size() < m$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractMatching$9(final BitSet leftSet$1, final BitSet rightSet$1, final CompetitiveLinking.Prediction pred) {
      return !leftSet$1.apply(BoxesRunTime.boxToInteger(pred.i())) && !rightSet$1.apply(BoxesRunTime.boxToInteger(pred.j()));
   }

   // $FF: synthetic method
   public static final void $anonfun$extractMatching$10(final int[] matching$1, final BitSet leftSet$1, final BitSet rightSet$1, final DoubleRef score$1, final CompetitiveLinking.Prediction pred) {
      matching$1[pred.i()] = pred.j();
      leftSet$1.add(BoxesRunTime.boxToInteger(pred.i()));
      rightSet$1.add(BoxesRunTime.boxToInteger(pred.j()));
      score$1.elem += pred.v();
   }

   private CompetitiveLinking$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
