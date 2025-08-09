package org.apache.spark.mllib.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.RangePartitioner;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.MatchError;
import scala.Predef;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\u0010!\u0001-B\u0001B\u000f\u0001\u0003\u0002\u0004%Ia\u000f\u0005\t\u007f\u0001\u0011\t\u0019!C\u0005\u0001\"Aa\t\u0001B\u0001B\u0003&A\bC\u0003H\u0001\u0011%\u0001\nC\u0003H\u0001\u0011\u0005A\nC\u0003W\u0001\u0011\u0005q\u000bC\u0003\\\u0001\u0011\u0005A\fC\u0003\\\u0001\u0011\u0005q\u000eC\u0004\u0000\u0001\u0011\u0005\u0001%!\u0001\t\u000f\u0005-\u0001\u0001\"\u0003\u0002\u000e!9\u00111\u0003\u0001\u0005\n\u0005UaABA\r\u0001\u0011\tY\u0002\u0003\u0004H\u0019\u0011\u0005\u0011Q\u0004\u0005\u000f\u0003CaA\u0011!A\u0003\u0002\u000b\u0007I\u0011BA\u0012\u0011-\t)\u0004\u0004B\u0001\u0002\u0003\u0006I!!\n\t\u0017\u0005]B\u0002%A\u0001\u0004\u0003\u0006I\u0001\u001b\u0005\u000f\u0003saA\u0011!A\u0003\u0002\u0003\u0007I\u0011BA\u001e\u00119\ti\u0004\u0004C\u0001\u0002\u000b\u0005\t\u0019!C\u0005\u0003\u007fA!\"a\u0011\r\u0005\u0003\u0005\t\u0015)\u0003l\u00119\t)\u0005\u0004C\u0001\u0002\u000b\u0005\t\u0019!C\u0005\u0003wAa\"a\u0012\r\t\u0003\u0005)\u0011!a\u0001\n\u0013\tI\u0005\u0003\u0006\u0002N1\u0011\t\u0011!Q!\n-Da\"a\u0014\r\t\u0003\u0005)\u0011!a\u0001\n\u0013\tY\u0004\u0003\b\u0002R1!\t\u0011!B\u0001\u0002\u0004%I!a\u0015\t\u0015\u0005]CB!A\u0001B\u0003&1\u000eC\u0004\u0002Z1!\t!a\u0017\t\u000f\u0005%D\u0002\"\u0001\u0002l!9\u00111\u000f\u0007\u0005\u0002\u0005U\u0004bBA>\u0019\u0011\u0005\u0011Q\u0010\u0005\b\u0003\u0003cA\u0011AAB\u0005II5o\u001c;p]&\u001c'+Z4sKN\u001c\u0018n\u001c8\u000b\u0005\u0005\u0012\u0013A\u0003:fOJ,7o]5p]*\u00111\u0005J\u0001\u0006[2d\u0017N\u0019\u0006\u0003K\u0019\nQa\u001d9be.T!a\n\u0015\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0013aA8sO\u000e\u00011c\u0001\u0001-eA\u0011Q\u0006M\u0007\u0002])\tq&A\u0003tG\u0006d\u0017-\u0003\u00022]\t1\u0011I\\=SK\u001a\u0004\"a\r\u001d\u000e\u0003QR!!\u000e\u001c\u0002\u0005%|'\"A\u001c\u0002\t)\fg/Y\u0005\u0003sQ\u0012AbU3sS\u0006d\u0017N_1cY\u0016\f\u0001\"[:pi>t\u0017nY\u000b\u0002yA\u0011Q&P\u0005\u0003}9\u0012qAQ8pY\u0016\fg.\u0001\u0007jg>$xN\\5d?\u0012*\u0017\u000f\u0006\u0002B\tB\u0011QFQ\u0005\u0003\u0007:\u0012A!\u00168ji\"9QIAA\u0001\u0002\u0004a\u0014a\u0001=%c\u0005I\u0011n]8u_:L7\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005%[\u0005C\u0001&\u0001\u001b\u0005\u0001\u0003\"\u0002\u001e\u0005\u0001\u0004aD#A%)\u0007\u0015qE\u000b\u0005\u0002P%6\t\u0001K\u0003\u0002RI\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0003&!B*j]\u000e,\u0017%A+\u0002\u000bEr3G\f\u0019\u0002\u0017M,G/S:pi>t\u0017n\u0019\u000b\u00031fk\u0011\u0001\u0001\u0005\u0006u\u0019\u0001\r\u0001\u0010\u0015\u0004\r9#\u0016a\u0001:v]R\u0011Q\f\u0019\t\u0003\u0015zK!a\u0018\u0011\u0003/%\u001bx\u000e^8oS\u000e\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007\"B1\b\u0001\u0004\u0011\u0017!B5oaV$\bcA2gQ6\tAM\u0003\u0002fI\u0005\u0019!\u000f\u001a3\n\u0005\u001d$'a\u0001*E\tB)Q&[6lW&\u0011!N\f\u0002\u0007)V\u0004H.Z\u001a\u0011\u00055b\u0017BA7/\u0005\u0019!u.\u001e2mK\"\u001aqA\u0014+\u0015\u0005u\u0003\b\"B1\t\u0001\u0004\t\bc\u0001:wq6\t1O\u0003\u00028i*\u0011Q\u000fJ\u0001\u0004CBL\u0017BA<t\u0005\u001dQ\u0015M^1S\t\u0012\u0003R!L5zsf\u0004\"A_?\u000e\u0003mT!\u0001 \u001c\u0002\t1\fgnZ\u0005\u0003[nD3\u0001\u0003(U\u0003)i\u0017m[3V]&\fX/\u001a\u000b\u0005\u0003\u0007\tI\u0001\u0005\u0003.\u0003\u000bA\u0017bAA\u0004]\t)\u0011I\u001d:bs\"1\u0011-\u0003a\u0001\u0003\u0007\tQ\u0003]8pY\u0006#'.Y2f]R4\u0016n\u001c7bi>\u00148\u000f\u0006\u0003\u0002\u0004\u0005=\u0001bBA\t\u0015\u0001\u0007\u00111A\u0001\u0011G2,\u0017M\\+oSF,X-\u00138qkR\fQ\u0004]1sC2dW\r\u001c)p_2\fEM[1dK:$h+[8mCR|'o\u001d\u000b\u0005\u0003\u0007\t9\u0002C\u0003b\u0017\u0001\u0007!MA\tQ_&tGo]!dGVlW\u000f\\1u_J\u001c\"\u0001\u0004\u0017\u0015\u0005\u0005}\u0001C\u0001-\r\u00039{'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ7mY&\u0014GE]3he\u0016\u001c8/[8oI%\u001bx\u000e^8oS\u000e\u0014Vm\u001a:fgNLwN\u001c\u0013Q_&tGo]!dGVlW\u000f\\1u_J$Ce\\;uaV$XCAA\u0013!\u0015\t9#!\ri\u001b\t\tIC\u0003\u0003\u0002,\u00055\u0012aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003_q\u0013AC2pY2,7\r^5p]&!\u00111GA\u0015\u0005-\t%O]1z\u0005V4g-\u001a:\u0002\u001f>\u0014x\rJ1qC\u000eDW\rJ:qCJ\\G%\u001c7mS\n$#/Z4sKN\u001c\u0018n\u001c8%\u0013N|Go\u001c8jGJ+wM]3tg&|g\u000e\n)pS:$8/Q2dk6,H.\u0019;pe\u0012\"s.\u001e;qkR\u0004\u0013\u0001\u0002=%cE\nAk\u001c:hI\u0005\u0004\u0018m\u00195fIM\u0004\u0018M]6%[2d\u0017N\u0019\u0013sK\u001e\u0014Xm]:j_:$\u0013j]8u_:L7MU3he\u0016\u001c8/[8oIA{\u0017N\u001c;t\u0003\u000e\u001cW/\\;mCR|'\u000f\n\u0013dkJ\u0014XM\u001c;MC\n,G.F\u0001l\u0003a{'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ7mY&\u0014GE]3he\u0016\u001c8/[8oI%\u001bx\u000e^8oS\u000e\u0014Vm\u001a:fgNLwN\u001c\u0013Q_&tGo]!dGVlW\u000f\\1u_J$CeY;se\u0016tG\u000fT1cK2|F%Z9\u0015\u0007\u0005\u000b\t\u0005C\u0004F%\u0005\u0005\t\u0019A6\u0002+>\u0014x\rJ1qC\u000eDW\rJ:qCJ\\G%\u001c7mS\n$#/Z4sKN\u001c\u0018n\u001c8%\u0013N|Go\u001c8jGJ+wM]3tg&|g\u000e\n)pS:$8/Q2dk6,H.\u0019;pe\u0012\"3-\u001e:sK:$H*\u00192fY\u0002\nak\u001c:hI\u0005\u0004\u0018m\u00195fIM\u0004\u0018M]6%[2d\u0017N\u0019\u0013sK\u001e\u0014Xm]:j_:$\u0013j]8u_:L7MU3he\u0016\u001c8/[8oIA{\u0017N\u001c;t\u0003\u000e\u001cW/\\;mCR|'\u000f\n\u0013dkJ\u0014XM\u001c;GK\u0006$XO]3\u00025>\u0014x\rJ1qC\u000eDW\rJ:qCJ\\G%\u001c7mS\n$#/Z4sKN\u001c\u0018n\u001c8%\u0013N|Go\u001c8jGJ+wM]3tg&|g\u000e\n)pS:$8/Q2dk6,H.\u0019;pe\u0012\"3-\u001e:sK:$h)Z1ukJ,w\fJ3r)\r\t\u00151\n\u0005\b\u000bV\t\t\u00111\u0001l\u0003]{'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ7mY&\u0014GE]3he\u0016\u001c8/[8oI%\u001bx\u000e^8oS\u000e\u0014Vm\u001a:fgNLwN\u001c\u0013Q_&tGo]!dGVlW\u000f\\1u_J$CeY;se\u0016tGOR3biV\u0014X\rI\u0001V_J<G%\u00199bG\",Ge\u001d9be.$S\u000e\u001c7jE\u0012\u0012Xm\u001a:fgNLwN\u001c\u0013Jg>$xN\\5d%\u0016<'/Z:tS>tG\u0005U8j]R\u001c\u0018iY2v[Vd\u0017\r^8sI\u0011\u001aWO\u001d:f]R<V-[4ii\u0006IvN]4%CB\f7\r[3%gB\f'o\u001b\u0013nY2L'\r\n:fOJ,7o]5p]\u0012J5o\u001c;p]&\u001c'+Z4sKN\u001c\u0018n\u001c8%!>Lg\u000e^:BG\u000e,X.\u001e7bi>\u0014H\u0005J2veJ,g\u000e^,fS\u001eDGo\u0018\u0013fcR\u0019\u0011)!\u0016\t\u000f\u0015C\u0012\u0011!a\u0001W\u00061vN]4%CB\f7\r[3%gB\f'o\u001b\u0013nY2L'\r\n:fOJ,7o]5p]\u0012J5o\u001c;p]&\u001c'+Z4sKN\u001c\u0018n\u001c8%!>Lg\u000e^:BG\u000e,X.\u001e7bi>\u0014H\u0005J2veJ,g\u000e^,fS\u001eDG\u000fI\u0001\u0011g\"|W\u000f\u001c3BG\u000e,X.\u001e7bi\u0016$2\u0001PA/\u0011\u0019\tyF\u0007a\u0001W\u00069a-Z1ukJ,\u0007f\u0001\u000e\u0002dA\u0019Q&!\u001a\n\u0007\u0005\u001ddF\u0001\u0004j]2Lg.Z\u0001\nI\r|Gn\u001c8%KF$2!QA7\u0011\u0019\tyg\u0007a\u0001Q\u0006)\u0001o\\5oi\"\u001a1$a\u0019\u0002\u0011\u0011\u0002H.^:%KF$2!QA<\u0011\u0019\ty\u0007\ba\u0001Q\"\u001aA$a\u0019\u0002\u001d\u0005\u0004\b/\u001a8e)>|U\u000f\u001e9viR\t\u0011\tK\u0002\u001e\u0003G\n\u0011bZ3u\u001fV$\b/\u001e;\u0016\u0005\u0005\r\u0001f\u0001\u0010\u0002d!\u001a\u0001A\u0014+"
)
public class IsotonicRegression implements Serializable {
   private boolean isotonic;

   private boolean isotonic() {
      return this.isotonic;
   }

   private void isotonic_$eq(final boolean x$1) {
      this.isotonic = x$1;
   }

   public IsotonicRegression setIsotonic(final boolean isotonic) {
      this.isotonic_$eq(isotonic);
      return this;
   }

   public IsotonicRegressionModel run(final RDD input) {
      RDD preprocessedInput = this.isotonic() ? input : input.map((x) -> new Tuple3(BoxesRunTime.boxToDouble(-BoxesRunTime.unboxToDouble(x._1())), x._2(), x._3()), .MODULE$.apply(Tuple3.class));
      Tuple3[] pooled = this.parallelPoolAdjacentViolators(preprocessedInput);
      double[] predictions = this.isotonic() ? (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])pooled), (x$5) -> BoxesRunTime.boxToDouble($anonfun$run$2(x$5)), .MODULE$.Double()) : (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])pooled), (x$6) -> BoxesRunTime.boxToDouble($anonfun$run$3(x$6)), .MODULE$.Double());
      double[] boundaries = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])pooled), (x$7) -> BoxesRunTime.boxToDouble($anonfun$run$4(x$7)), .MODULE$.Double());
      return new IsotonicRegressionModel(boundaries, predictions, this.isotonic());
   }

   public IsotonicRegressionModel run(final JavaRDD input) {
      return this.run(input.rdd().retag(.MODULE$.apply(Tuple3.class)));
   }

   public Tuple3[] makeUnique(final Tuple3[] input) {
      Tuple3[] cleanInput = (Tuple3[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])input), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$makeUnique$1(x0$1)));
      if (cleanInput.length <= 1) {
         return cleanInput;
      } else {
         PointsAccumulator pointsAccumulator = new PointsAccumulator();
         pointsAccumulator.$colon$eq((Tuple3)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])cleanInput)));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.refArrayOps((Object[])cleanInput))), (x0$2) -> {
            $anonfun$makeUnique$3(pointsAccumulator, x0$2);
            return BoxedUnit.UNIT;
         });
         pointsAccumulator.appendToOutput();
         return pointsAccumulator.getOutput();
      }
   }

   private Tuple3[] poolAdjacentViolators(final Tuple3[] cleanUniqueInput) {
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])cleanUniqueInput))) {
         return (Tuple3[])scala.Array..MODULE$.empty(.MODULE$.apply(Tuple3.class));
      } else {
         int[] blockBounds = scala.Array..MODULE$.range(0, cleanUniqueInput.length);
         Tuple2[] weights = (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])cleanUniqueInput), (x0$1) -> {
            if (x0$1 != null) {
               double y = BoxesRunTime.unboxToDouble(x0$1._1());
               double weight = BoxesRunTime.unboxToDouble(x0$1._3());
               return new Tuple2.mcDD.sp(weight, weight * y);
            } else {
               throw new MatchError(x0$1);
            }
         }, .MODULE$.apply(Tuple2.class));
         int i = 0;

         while(nextBlock$1(i, blockBounds) < cleanUniqueInput.length) {
            if (average$1(i, weights) >= average$1(nextBlock$1(i, blockBounds), weights)) {
               merge$1(i, nextBlock$1(i, blockBounds), blockBounds, weights);

               while(i > 0 && average$1(prevBlock$1(i, blockBounds), weights) >= average$1(i, weights)) {
                  i = merge$1(prevBlock$1(i, blockBounds), i, blockBounds, weights);
               }
            } else {
               i = nextBlock$1(i, blockBounds);
            }
         }

         ArrayBuffer output = scala.collection.mutable.ArrayBuffer..MODULE$.empty();

         for(int var6 = 0; var6 < cleanUniqueInput.length; var6 = nextBlock$1(var6, blockBounds)) {
            if (BoxesRunTime.unboxToDouble(cleanUniqueInput[blockEnd$1(var6, blockBounds)]._2()) > BoxesRunTime.unboxToDouble(cleanUniqueInput[var6]._2())) {
               output.$plus$eq(new Tuple3(BoxesRunTime.boxToDouble(average$1(var6, weights)), cleanUniqueInput[var6]._2(), BoxesRunTime.boxToDouble(weights[var6]._1$mcD$sp() / (double)2)));
               output.$plus$eq(new Tuple3(BoxesRunTime.boxToDouble(average$1(var6, weights)), cleanUniqueInput[blockEnd$1(var6, blockBounds)]._2(), BoxesRunTime.boxToDouble(weights[var6]._1$mcD$sp() / (double)2)));
            } else {
               output.$plus$eq(new Tuple3(BoxesRunTime.boxToDouble(average$1(var6, weights)), cleanUniqueInput[var6]._2(), BoxesRunTime.boxToDouble(weights[var6]._1$mcD$sp())));
            }
         }

         return (Tuple3[])output.toArray(.MODULE$.apply(Tuple3.class));
      }
   }

   private Tuple3[] parallelPoolAdjacentViolators(final RDD input) {
      RDD keyedInput = input.keyBy((x$8) -> BoxesRunTime.boxToDouble($anonfun$parallelPoolAdjacentViolators$1(x$8)));
      ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      RDD qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(keyedInput, .MODULE$.Double(), .MODULE$.apply(Tuple3.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).partitionBy(new RangePartitioner(keyedInput.getNumPartitions(), keyedInput, org.apache.spark.RangePartitioner..MODULE$.$lessinit$greater$default$3(), org.apache.spark.RangePartitioner..MODULE$.$lessinit$greater$default$4(), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, .MODULE$.Double())), .MODULE$.Double(), .MODULE$.apply(Tuple3.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).values();
      Function1 x$1 = (p) -> scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple3[][]{(Tuple3[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(p.toArray(.MODULE$.apply(Tuple3.class))), (x$9) -> BoxesRunTime.boxToDouble($anonfun$parallelPoolAdjacentViolators$3(x$9)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)})));
      boolean x$2 = qual$1.mapPartitions$default$2();
      Tuple3[] parallelStepResult = (Tuple3[])var10000.sortBy$extension(var10001.refArrayOps(qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple3.class))).map((inputx) -> this.makeUnique(inputx), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple3.class))).flatMap((cleanUniqueInput) -> scala.Predef..MODULE$.wrapRefArray((Object[])this.poolAdjacentViolators(cleanUniqueInput)), .MODULE$.apply(Tuple3.class)).collect()), (x$10) -> BoxesRunTime.boxToDouble($anonfun$parallelPoolAdjacentViolators$6(x$10)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      return this.poolAdjacentViolators(parallelStepResult);
   }

   // $FF: synthetic method
   public static final double $anonfun$run$2(final Tuple3 x$5) {
      return BoxesRunTime.unboxToDouble(x$5._1());
   }

   // $FF: synthetic method
   public static final double $anonfun$run$3(final Tuple3 x$6) {
      return -BoxesRunTime.unboxToDouble(x$6._1());
   }

   // $FF: synthetic method
   public static final double $anonfun$run$4(final Tuple3 x$7) {
      return BoxesRunTime.unboxToDouble(x$7._2());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeUnique$1(final Tuple3 x0$1) {
      if (x0$1 != null) {
         double y = BoxesRunTime.unboxToDouble(x0$1._1());
         double x = BoxesRunTime.unboxToDouble(x0$1._2());
         double weight = BoxesRunTime.unboxToDouble(x0$1._3());
         scala.Predef..MODULE$.require(weight >= (double)0.0F, () -> "Negative weight at point (" + y + ", " + x + ", " + weight + "). Weights must be non-negative");
         return weight > (double)0;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$makeUnique$3(final PointsAccumulator pointsAccumulator$1, final Tuple3 x0$2) {
      if (x0$2 != null) {
         double feature = BoxesRunTime.unboxToDouble(x0$2._2());
         if (pointsAccumulator$1.shouldAccumulate(feature)) {
            pointsAccumulator$1.$plus$eq(x0$2);
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            pointsAccumulator$1.appendToOutput();
            pointsAccumulator$1.$colon$eq(x0$2);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   private static final int blockEnd$1(final int start, final int[] blockBounds$1) {
      return blockBounds$1[start];
   }

   private static final int blockStart$1(final int end, final int[] blockBounds$1) {
      return blockBounds$1[end];
   }

   private static final int nextBlock$1(final int start, final int[] blockBounds$1) {
      return blockEnd$1(start, blockBounds$1) + 1;
   }

   private static final int prevBlock$1(final int start, final int[] blockBounds$1) {
      return blockStart$1(start - 1, blockBounds$1);
   }

   private static final int merge$1(final int block1, final int block2, final int[] blockBounds$1, final Tuple2[] weights$1) {
      scala.Predef..MODULE$.assert(blockEnd$1(block1, blockBounds$1) + 1 == block2, () -> "Attempting to merge non-consecutive blocks [" + block1 + ", " + blockEnd$1(block1, blockBounds$1) + "] and [" + block2 + ", " + blockEnd$1(block2, blockBounds$1) + "]. This is likely a bug in the isotonic regression implementation. Please file a bug report.");
      blockBounds$1[block1] = blockEnd$1(block2, blockBounds$1);
      blockBounds$1[blockEnd$1(block2, blockBounds$1)] = block1;
      Tuple2 w1 = weights$1[block1];
      Tuple2 w2 = weights$1[block2];
      weights$1[block1] = new Tuple2.mcDD.sp(w1._1$mcD$sp() + w2._1$mcD$sp(), w1._2$mcD$sp() + w2._2$mcD$sp());
      return block1;
   }

   private static final double average$1(final int start, final Tuple2[] weights$1) {
      return weights$1[start]._2$mcD$sp() / weights$1[start]._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$parallelPoolAdjacentViolators$1(final Tuple3 x$8) {
      return BoxesRunTime.unboxToDouble(x$8._2());
   }

   // $FF: synthetic method
   public static final double $anonfun$parallelPoolAdjacentViolators$3(final Tuple3 x$9) {
      return BoxesRunTime.unboxToDouble(x$9._2());
   }

   // $FF: synthetic method
   public static final double $anonfun$parallelPoolAdjacentViolators$6(final Tuple3 x$10) {
      return BoxesRunTime.unboxToDouble(x$10._2());
   }

   private IsotonicRegression(final boolean isotonic) {
      this.isotonic = isotonic;
      super();
   }

   public IsotonicRegression() {
      this(true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class PointsAccumulator {
      private final ArrayBuffer org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$output;
      // $FF: synthetic field
      private final Tuple3 x$11;
      private double org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel;
      private double org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature;
      private double org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight;
      // $FF: synthetic field
      public final IsotonicRegression $outer;

      public ArrayBuffer org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$output() {
         return this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$output;
      }

      public double org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel() {
         return this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel;
      }

      public void org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel_$eq(final double x$1) {
         this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel = x$1;
      }

      public double org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature() {
         return this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature;
      }

      public void org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature_$eq(final double x$1) {
         this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature = x$1;
      }

      public double org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight() {
         return this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight;
      }

      public void org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight_$eq(final double x$1) {
         this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight = x$1;
      }

      public boolean shouldAccumulate(final double feature) {
         return this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature() == feature;
      }

      public void $colon$eq(final Tuple3 point) {
         if (point != null) {
            double label = BoxesRunTime.unboxToDouble(point._1());
            double feature = BoxesRunTime.unboxToDouble(point._2());
            double weight = BoxesRunTime.unboxToDouble(point._3());
            Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble(label), BoxesRunTime.boxToDouble(feature), BoxesRunTime.boxToDouble(weight));
            double label = BoxesRunTime.unboxToDouble(var3._1());
            double featurex = BoxesRunTime.unboxToDouble(var3._2());
            double weightx = BoxesRunTime.unboxToDouble(var3._3());
            this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel_$eq(label * weightx);
            this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature_$eq(featurex);
            this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight_$eq(weightx);
         } else {
            throw new MatchError(point);
         }
      }

      public void $plus$eq(final Tuple3 point) {
         if (point != null) {
            double label = BoxesRunTime.unboxToDouble(point._1());
            double weight = BoxesRunTime.unboxToDouble(point._3());
            Tuple2.mcDD.sp var3 = new Tuple2.mcDD.sp(label, weight);
            double label = ((Tuple2)var3)._1$mcD$sp();
            double weight = ((Tuple2)var3)._2$mcD$sp();
            this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel_$eq(this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel() + label * weight);
            this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight_$eq(this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight() + weight);
         } else {
            throw new MatchError(point);
         }
      }

      public void appendToOutput() {
         this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$output().$plus$eq(new Tuple3(BoxesRunTime.boxToDouble(this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel() / this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight()), BoxesRunTime.boxToDouble(this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature()), BoxesRunTime.boxToDouble(this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight())));
      }

      public Tuple3[] getOutput() {
         return (Tuple3[])this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$output().toArray(.MODULE$.apply(Tuple3.class));
      }

      // $FF: synthetic method
      public IsotonicRegression org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$$outer() {
         return this.$outer;
      }

      public PointsAccumulator() {
         if (IsotonicRegression.this == null) {
            throw null;
         } else {
            this.$outer = IsotonicRegression.this;
            super();
            this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$output = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToDouble((double)0.0F));
            if (var3 != null) {
               double currentLabel = BoxesRunTime.unboxToDouble(var3._1());
               double currentFeature = BoxesRunTime.unboxToDouble(var3._2());
               double currentWeight = BoxesRunTime.unboxToDouble(var3._3());
               if (true && true && true) {
                  this.x$11 = new Tuple3(BoxesRunTime.boxToDouble(currentLabel), BoxesRunTime.boxToDouble(currentFeature), BoxesRunTime.boxToDouble(currentWeight));
                  this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentLabel = BoxesRunTime.unboxToDouble(this.x$11._1());
                  this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentFeature = BoxesRunTime.unboxToDouble(this.x$11._2());
                  this.org$apache$spark$mllib$regression$IsotonicRegression$PointsAccumulator$$currentWeight = BoxesRunTime.unboxToDouble(this.x$11._3());
                  return;
               }
            }

            throw new MatchError(var3);
         }
      }
   }
}
