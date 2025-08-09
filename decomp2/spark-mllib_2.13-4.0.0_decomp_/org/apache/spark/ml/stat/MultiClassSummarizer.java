package org.apache.spark.ml.stat;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=4Q!\u0004\b\u0001!aAQ\u0001\f\u0001\u0005\u00025Bq\u0001\r\u0001C\u0002\u0013%\u0011\u0007\u0003\u0004G\u0001\u0001\u0006IA\r\u0005\b\u000f\u0002\u0001\r\u0011\"\u0003I\u0011\u001dI\u0005\u00011A\u0005\n)Ca\u0001\u0015\u0001!B\u0013\u0001\u0005\"B)\u0001\t\u0003\u0011\u0006bB,\u0001#\u0003%\t\u0001\u0017\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006O\u0002!\t\u0001\u0013\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006U\u0002!\ta\u001b\u0002\u0015\u001bVdG/[\"mCN\u001c8+^7nCJL'0\u001a:\u000b\u0005=\u0001\u0012\u0001B:uCRT!!\u0005\n\u0002\u00055d'BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0014\u0007\u0001Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A%r!!I\u0014\u000f\u0005\t2S\"A\u0012\u000b\u0005\u0011*\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003qI!\u0001K\u000e\u0002\u000fA\f7m[1hK&\u0011!f\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Qm\ta\u0001P5oSRtD#\u0001\u0018\u0011\u0005=\u0002Q\"\u0001\b\u0002\u0017\u0011L7\u000f^5oGRl\u0015\r]\u000b\u0002eA!1\u0007\u000f\u001e>\u001b\u0005!$BA\u001b7\u0003\u001diW\u000f^1cY\u0016T!aN\u000e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002:i\t9\u0001*Y:i\u001b\u0006\u0004\bC\u0001\u000e<\u0013\ta4DA\u0002J]R\u0004BA\u0007 A\u0007&\u0011qh\u0007\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005i\t\u0015B\u0001\"\u001c\u0005\u0011auN\\4\u0011\u0005i!\u0015BA#\u001c\u0005\u0019!u.\u001e2mK\u0006aA-[:uS:\u001cG/T1qA\u0005yAo\u001c;bY&sg/\u00197jI\u000esG/F\u0001A\u0003M!x\u000e^1m\u0013:4\u0018\r\\5e\u0007:$x\fJ3r)\tYe\n\u0005\u0002\u001b\u0019&\u0011Qj\u0007\u0002\u0005+:LG\u000fC\u0004P\u000b\u0005\u0005\t\u0019\u0001!\u0002\u0007a$\u0013'\u0001\tu_R\fG.\u00138wC2LGm\u00118uA\u0005\u0019\u0011\r\u001a3\u0015\u00079\u001aV\u000bC\u0003U\u000f\u0001\u00071)A\u0003mC\n,G\u000eC\u0004W\u000fA\u0005\t\u0019A\"\u0002\r],\u0017n\u001a5u\u00035\tG\r\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\t\u0011L\u000b\u0002D5.\n1\f\u0005\u0002]C6\tQL\u0003\u0002_?\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003An\t!\"\u00198o_R\fG/[8o\u0013\t\u0011WLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ!\\3sO\u0016$\"AL3\t\u000b\u0019L\u0001\u0019\u0001\u0018\u0002\u000b=$\b.\u001a:\u0002\u0019\r|WO\u001c;J]Z\fG.\u001b3\u0002\u00159,Xn\u00117bgN,7/F\u0001;\u0003%A\u0017n\u001d;pOJ\fW.F\u0001m!\rQRnQ\u0005\u0003]n\u0011Q!\u0011:sCf\u0004"
)
public class MultiClassSummarizer implements Serializable {
   private final HashMap distinctMap = new HashMap();
   private long totalInvalidCnt = 0L;

   private HashMap distinctMap() {
      return this.distinctMap;
   }

   private long totalInvalidCnt() {
      return this.totalInvalidCnt;
   }

   private void totalInvalidCnt_$eq(final long x$1) {
      this.totalInvalidCnt = x$1;
   }

   public MultiClassSummarizer add(final double label, final double weight) {
      .MODULE$.require(weight >= (double)0.0F, () -> "instance weight, " + weight + " has to be >= 0.0");
      if (weight == (double)0.0F) {
         return this;
      } else {
         if (label - (double)((int)label) == (double)0.0F && !(label < (double)0)) {
            Tuple2 var7 = (Tuple2)this.distinctMap().getOrElse(BoxesRunTime.boxToInteger((int)label), () -> new Tuple2.mcJD.sp(0L, (double)0.0F));
            if (var7 == null) {
               throw new MatchError(var7);
            }

            long counts = var7._1$mcJ$sp();
            double weightSum = var7._2$mcD$sp();
            if (false || false) {
               throw new MatchError(var7);
            }

            Tuple2.mcJD.sp var6 = new Tuple2.mcJD.sp(counts, weightSum);
            long counts = ((Tuple2)var6)._1$mcJ$sp();
            double weightSum = ((Tuple2)var6)._2$mcD$sp();
            this.distinctMap().put(BoxesRunTime.boxToInteger((int)label), new Tuple2.mcJD.sp(counts + 1L, weightSum + weight));
         } else {
            this.totalInvalidCnt_$eq(this.totalInvalidCnt() + 1L);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this;
      }
   }

   public double add$default$2() {
      return (double)1.0F;
   }

   public MultiClassSummarizer merge(final MultiClassSummarizer other) {
      Tuple2 var4 = this.distinctMap().size() > other.distinctMap().size() ? new Tuple2(this, other) : new Tuple2(other, this);
      if (var4 != null) {
         MultiClassSummarizer largeMap = (MultiClassSummarizer)var4._1();
         MultiClassSummarizer smallMap = (MultiClassSummarizer)var4._2();
         Tuple2 var3 = new Tuple2(largeMap, smallMap);
         MultiClassSummarizer largeMap = (MultiClassSummarizer)var3._1();
         MultiClassSummarizer smallMap = (MultiClassSummarizer)var3._2();
         smallMap.distinctMap().foreach((x0$1) -> {
            if (x0$1 != null) {
               int key = x0$1._1$mcI$sp();
               Tuple2 value = (Tuple2)x0$1._2();
               Tuple2 var8 = (Tuple2)largeMap.distinctMap().getOrElse(BoxesRunTime.boxToInteger(key), () -> new Tuple2.mcJD.sp(0L, (double)0.0F));
               if (var8 != null) {
                  long counts = var8._1$mcJ$sp();
                  double weightSum = var8._2$mcD$sp();
                  if (true && true) {
                     Tuple2.mcJD.sp var7 = new Tuple2.mcJD.sp(counts, weightSum);
                     long counts = ((Tuple2)var7)._1$mcJ$sp();
                     double weightSumx = ((Tuple2)var7)._2$mcD$sp();
                     return largeMap.distinctMap().put(BoxesRunTime.boxToInteger(key), new Tuple2.mcJD.sp(counts + value._1$mcJ$sp(), weightSumx + value._2$mcD$sp()));
                  }
               }

               throw new MatchError(var8);
            } else {
               throw new MatchError(x0$1);
            }
         });
         largeMap.totalInvalidCnt_$eq(largeMap.totalInvalidCnt() + smallMap.totalInvalidCnt());
         return largeMap;
      } else {
         throw new MatchError(var4);
      }
   }

   public long countInvalid() {
      return this.totalInvalidCnt();
   }

   public int numClasses() {
      return this.distinctMap().isEmpty() ? 0 : BoxesRunTime.unboxToInt(this.distinctMap().keysIterator().max(scala.math.Ordering.Int..MODULE$)) + 1;
   }

   public double[] histogram() {
      double[] result = (double[])scala.Array..MODULE$.ofDim(this.numClasses(), scala.reflect.ClassTag..MODULE$.Double());
      int i = 0;

      for(int len = result.length; i < len; ++i) {
         result[i] = ((Tuple2)this.distinctMap().getOrElse(BoxesRunTime.boxToInteger(i), () -> new Tuple2.mcJD.sp(0L, (double)0.0F)))._2$mcD$sp();
      }

      return result;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
