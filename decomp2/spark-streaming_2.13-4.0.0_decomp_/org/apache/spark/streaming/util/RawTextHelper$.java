package org.apache.spark.streaming.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.collection.OpenHashMap;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class RawTextHelper$ {
   public static final RawTextHelper$ MODULE$ = new RawTextHelper$();

   public Iterator splitAndCountPartitions(final Iterator iter) {
      OpenHashMap map = new OpenHashMap.mcJ.sp(.MODULE$.apply(String.class), .MODULE$.Long());
      int i = 0;
      int j = 0;

      while(iter.hasNext()) {
         String s = (String)iter.next();
         i = 0;

         while(i < s.length()) {
            for(j = i; j < s.length() && s.charAt(j) != ' '; ++j) {
            }

            if (j > i) {
               String w = s.substring(i, j);
               BoxesRunTime.boxToLong(map.changeValue$mcJ$sp(w, (JFunction0.mcJ.sp)() -> 1L, (JFunction1.mcJJ.sp)(x$1) -> x$1 + 1L));
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            for(i = j; i < s.length() && s.charAt(i) == ' '; ++i) {
            }
         }

         map.iterator().map((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               long v = x0$1._2$mcJ$sp();
               return new Tuple2(k, BoxesRunTime.boxToLong(v));
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      return map.iterator().map((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            long v = x0$2._2$mcJ$sp();
            return new Tuple2(k, BoxesRunTime.boxToLong(v));
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   public Iterator topK(final Iterator data, final int k) {
      Tuple2[] taken = new Tuple2[k];
      int i = 0;
      int len = 0;
      Tuple2 value = null;
      Tuple2 swap = null;
      int count = 0;

      while(data.hasNext()) {
         value = (Tuple2)data.next();
         if (value != null) {
            ++count;
            if (len == 0) {
               taken[0] = value;
               len = 1;
            } else if (len < k || value._2$mcJ$sp() > taken[len - 1]._2$mcJ$sp()) {
               if (len < k) {
                  ++len;
               }

               taken[len - 1] = value;

               for(int var9 = len - 1; var9 > 0 && taken[var9 - 1]._2$mcJ$sp() < taken[var9]._2$mcJ$sp(); --var9) {
                  swap = taken[var9];
                  taken[var9] = taken[var9 - 1];
                  taken[var9 - 1] = swap;
               }
            }
         }
      }

      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])taken));
   }

   public void warmUp(final SparkContext sc) {
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), 1).foreach((JFunction1.mcJI.sp)(i) -> {
         RDD var10000 = org.apache.spark.rdd.RDD..MODULE$;
         RDD qual$1 = sc.parallelize(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), 200000), 1000, .MODULE$.Int()).map((JFunction1.mcII.sp)(x$2x) -> x$2x % 1331, .MODULE$.Int()).map((x$3) -> $anonfun$warmUp$3(BoxesRunTime.unboxToInt(x$3)), .MODULE$.apply(String.class));
         Function1 x$1 = (iter) -> MODULE$.splitAndCountPartitions(iter);
         boolean x$2 = qual$1.mapPartitions$default$2();
         return var10000.rddToPairRDDFunctions(qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(Tuple2.class)), .MODULE$.apply(String.class), .MODULE$.Long(), scala.math.Ordering.String..MODULE$).reduceByKey((JFunction2.mcJJJ.sp)(x$4, x$5) -> x$4 + x$5, 10).count();
      });
   }

   public long add(final long v1, final long v2) {
      return v1 + v2;
   }

   public long subtract(final long v1, final long v2) {
      return v1 - v2;
   }

   public long max(final long v1, final long v2) {
      return scala.math.package..MODULE$.max(v1, v2);
   }

   // $FF: synthetic method
   public static final String $anonfun$warmUp$3(final int x$3) {
      return Integer.toString(x$3);
   }

   private RawTextHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
