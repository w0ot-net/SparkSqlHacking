package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.mllib.stat.test.ChiSqTestResult;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055g\u0001\u0002\u0017.\u0001aBQa\u0013\u0001\u0005\u00021Cq\u0001\u0017\u0001A\u0002\u0013\u0005\u0011\fC\u0004^\u0001\u0001\u0007I\u0011\u00010\t\r\u0011\u0004\u0001\u0015)\u0003[\u0011\u001d)\u0007\u00011A\u0005\u0002\u0019DqA\u001b\u0001A\u0002\u0013\u00051\u000e\u0003\u0004n\u0001\u0001\u0006Ka\u001a\u0005\b]\u0002\u0001\r\u0011\"\u0001g\u0011\u001dy\u0007\u00011A\u0005\u0002ADaA\u001d\u0001!B\u00139\u0007bB:\u0001\u0001\u0004%\tA\u001a\u0005\bi\u0002\u0001\r\u0011\"\u0001v\u0011\u00199\b\u0001)Q\u0005O\"9\u0001\u0010\u0001a\u0001\n\u00031\u0007bB=\u0001\u0001\u0004%\tA\u001f\u0005\u0007y\u0002\u0001\u000b\u0015B4\t\u000fu\u0004\u0001\u0019!C\u0001}\"I\u0011q\u0002\u0001A\u0002\u0013\u0005\u0011\u0011\u0003\u0005\b\u0003+\u0001\u0001\u0015)\u0003\u0000\u0011\u0019Y\u0005\u0001\"\u0001\u0002\u0018!9\u0011\u0011\u0005\u0001\u0005\u0002\u0005\r\u0002bBA\u0019\u0001\u0011\u0005\u00111\u0007\u0005\b\u0003s\u0001A\u0011AA\u001e\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Bq!!\u0014\u0001\t\u0003\ty\u0005C\u0004\u0002V\u0001!\t!a\u0016\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`\u001dA\u0011qQ\u0017\t\u0002E\nIIB\u0004-[!\u0005\u0011'a#\t\r-kB\u0011AAN\u0011%\ti*\bb\u0001\n\u0003\td\u0010C\u0004\u0002 v\u0001\u000b\u0011B@\t\u0013\u0005\u0005VD1A\u0005\u0002Er\bbBAR;\u0001\u0006Ia \u0005\n\u0003Kk\"\u0019!C\u0001cyDq!a*\u001eA\u0003%q\u0010C\u0005\u0002*v\u0011\r\u0011\"\u00012}\"9\u00111V\u000f!\u0002\u0013y\b\"CAW;\t\u0007I\u0011A\u0019\u007f\u0011\u001d\ty+\bQ\u0001\n}D\u0011\"!-\u001e\u0005\u0004%\t!a-\t\u0011\u0005mV\u0004)A\u0005\u0003kC\u0011\"!0\u001e\u0003\u0003%I!a0\u0003\u001b\rC\u0017nU9TK2,7\r^8s\u0015\tqs&A\u0004gK\u0006$XO]3\u000b\u0005A\n\u0014!B7mY&\u0014'B\u0001\u001a4\u0003\u0015\u0019\b/\u0019:l\u0015\t!T'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002m\u0005\u0019qN]4\u0004\u0001M\u0019\u0001!O \u0011\u0005ijT\"A\u001e\u000b\u0003q\nQa]2bY\u0006L!AP\u001e\u0003\r\u0005s\u0017PU3g!\t\u0001\u0005J\u0004\u0002B\r:\u0011!)R\u0007\u0002\u0007*\u0011AiN\u0001\u0007yI|w\u000e\u001e \n\u0003qJ!aR\u001e\u0002\u000fA\f7m[1hK&\u0011\u0011J\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u000fn\na\u0001P5oSRtD#A'\u0011\u00059\u0003Q\"A\u0017)\u0007\u0005\u0001f\u000b\u0005\u0002R)6\t!K\u0003\u0002Tc\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005U\u0013&!B*j]\u000e,\u0017%A,\u0002\u000bIr\u0013G\f\u0019\u0002\u001d9,X\u000eV8q\r\u0016\fG/\u001e:fgV\t!\f\u0005\u0002;7&\u0011Al\u000f\u0002\u0004\u0013:$\u0018A\u00058v[R{\u0007OR3biV\u0014Xm]0%KF$\"a\u00182\u0011\u0005i\u0002\u0017BA1<\u0005\u0011)f.\u001b;\t\u000f\r\u001c\u0011\u0011!a\u00015\u0006\u0019\u0001\u0010J\u0019\u0002\u001f9,X\u000eV8q\r\u0016\fG/\u001e:fg\u0002\n!\u0002]3sG\u0016tG/\u001b7f+\u00059\u0007C\u0001\u001ei\u0013\tI7H\u0001\u0004E_V\u0014G.Z\u0001\u000fa\u0016\u00148-\u001a8uS2,w\fJ3r)\tyF\u000eC\u0004d\r\u0005\u0005\t\u0019A4\u0002\u0017A,'oY3oi&dW\rI\u0001\u0004MB\u0014\u0018a\u00024qe~#S-\u001d\u000b\u0003?FDqaY\u0005\u0002\u0002\u0003\u0007q-\u0001\u0003gaJ\u0004\u0013a\u00014ee\u00069a\r\u001a:`I\u0015\fHCA0w\u0011\u001d\u0019G\"!AA\u0002\u001d\fAA\u001a3sA\u0005\u0019am^3\u0002\u000f\u0019<Xm\u0018\u0013fcR\u0011ql\u001f\u0005\bG>\t\t\u00111\u0001h\u0003\u00111w/\u001a\u0011\u0002\u0019M,G.Z2u_J$\u0016\u0010]3\u0016\u0003}\u0004B!!\u0001\u0002\n9!\u00111AA\u0003!\t\u00115(C\u0002\u0002\bm\na\u0001\u0015:fI\u00164\u0017\u0002BA\u0006\u0003\u001b\u0011aa\u0015;sS:<'bAA\u0004w\u0005\u00012/\u001a7fGR|'\u000fV=qK~#S-\u001d\u000b\u0004?\u0006M\u0001bB2\u0013\u0003\u0003\u0005\ra`\u0001\u000eg\u0016dWm\u0019;peRK\b/\u001a\u0011\u0015\u00075\u000bI\u0002C\u0003Y)\u0001\u0007!\f\u000b\u0003\u0015!\u0006u\u0011EAA\u0010\u0003\u0015\tdf\r\u00181\u0003E\u0019X\r\u001e(v[R{\u0007OR3biV\u0014Xm\u001d\u000b\u0005\u0003K\t9#D\u0001\u0001\u0011\u0019\tI#\u0006a\u00015\u0006)a/\u00197vK\"\"Q\u0003UA\u0017C\t\ty#A\u00032]Yr\u0003'A\u0007tKR\u0004VM]2f]RLG.\u001a\u000b\u0005\u0003K\t)\u0004\u0003\u0004\u0002*Y\u0001\ra\u001a\u0015\u0004-A3\u0016AB:fi\u001a\u0003(\u000f\u0006\u0003\u0002&\u0005u\u0002BBA\u0015/\u0001\u0007q\rK\u0002\u0018!Z\u000baa]3u\r\u0012\u0014H\u0003BA\u0013\u0003\u000bBa!!\u000b\u0019\u0001\u00049\u0007\u0006\u0002\rQ\u0003\u0013\n#!a\u0013\u0002\u000bIr#G\f\u0019\u0002\rM,GOR<f)\u0011\t)#!\u0015\t\r\u0005%\u0012\u00041\u0001hQ\u0011I\u0002+!\u0013\u0002\u001fM,GoU3mK\u000e$xN\u001d+za\u0016$B!!\n\u0002Z!1\u0011\u0011\u0006\u000eA\u0002}D3A\u0007)W\u0003\r1\u0017\u000e\u001e\u000b\u0005\u0003C\n9\u0007E\u0002O\u0003GJ1!!\u001a.\u0005I\u0019\u0005.[*r'\u0016dWm\u0019;pe6{G-\u001a7\t\u000f\u0005%4\u00041\u0001\u0002l\u0005!A-\u0019;b!\u0019\ti'a\u001d\u0002x5\u0011\u0011q\u000e\u0006\u0004\u0003c\n\u0014a\u0001:eI&!\u0011QOA8\u0005\r\u0011F\t\u0012\t\u0005\u0003s\ny(\u0004\u0002\u0002|)\u0019\u0011QP\u0018\u0002\u0015I,wM]3tg&|g.\u0003\u0003\u0002\u0002\u0006m$\u0001\u0004'bE\u0016dW\r\u001a)pS:$\b\u0006B\u000eQ\u0003;AC\u0001\u0001)\u0002\u001e\u0005i1\t[5TcN+G.Z2u_J\u0004\"AT\u000f\u0014\tuI\u0014Q\u0012\t\u0005\u0003\u001f\u000bI*\u0004\u0002\u0002\u0012*!\u00111SAK\u0003\tIwN\u0003\u0002\u0002\u0018\u0006!!.\u0019<b\u0013\rI\u0015\u0011\u0013\u000b\u0003\u0003\u0013\u000baBT;n)>\u0004h)Z1ukJ,7/A\bOk6$v\u000e\u001d$fCR,(/Z:!\u0003)\u0001VM]2f]RLG.Z\u0001\f!\u0016\u00148-\u001a8uS2,\u0007%A\u0002G!J\u000bAA\u0012)SA\u0005\u0019a\t\u0012*\u0002\t\u0019#%\u000bI\u0001\u0004\r^+\u0015\u0001\u0002$X\u000b\u0002\nac];qa>\u0014H/\u001a3TK2,7\r^8s)f\u0004Xm]\u000b\u0003\u0003k\u0003BAOA\\\u007f&\u0019\u0011\u0011X\u001e\u0003\u000b\u0005\u0013(/Y=\u0002/M,\b\u000f]8si\u0016$7+\u001a7fGR|'\u000fV=qKN\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAAa!\u0011\t\u0019-!3\u000e\u0005\u0005\u0015'\u0002BAd\u0003+\u000bA\u0001\\1oO&!\u00111ZAc\u0005\u0019y%M[3di\u0002"
)
public class ChiSqSelector implements Serializable {
   private int numTopFeatures;
   private double percentile;
   private double fpr;
   private double fdr;
   private double fwe;
   private String selectorType;

   public static String[] supportedSelectorTypes() {
      return ChiSqSelector$.MODULE$.supportedSelectorTypes();
   }

   public int numTopFeatures() {
      return this.numTopFeatures;
   }

   public void numTopFeatures_$eq(final int x$1) {
      this.numTopFeatures = x$1;
   }

   public double percentile() {
      return this.percentile;
   }

   public void percentile_$eq(final double x$1) {
      this.percentile = x$1;
   }

   public double fpr() {
      return this.fpr;
   }

   public void fpr_$eq(final double x$1) {
      this.fpr = x$1;
   }

   public double fdr() {
      return this.fdr;
   }

   public void fdr_$eq(final double x$1) {
      this.fdr = x$1;
   }

   public double fwe() {
      return this.fwe;
   }

   public void fwe_$eq(final double x$1) {
      this.fwe = x$1;
   }

   public String selectorType() {
      return this.selectorType;
   }

   public void selectorType_$eq(final String x$1) {
      this.selectorType = x$1;
   }

   public ChiSqSelector setNumTopFeatures(final int value) {
      this.numTopFeatures_$eq(value);
      return this;
   }

   public ChiSqSelector setPercentile(final double value) {
      .MODULE$.require((double)0.0F <= value && value <= (double)1.0F, () -> "Percentile must be in [0,1]");
      this.percentile_$eq(value);
      return this;
   }

   public ChiSqSelector setFpr(final double value) {
      .MODULE$.require((double)0.0F <= value && value <= (double)1.0F, () -> "FPR must be in [0,1]");
      this.fpr_$eq(value);
      return this;
   }

   public ChiSqSelector setFdr(final double value) {
      .MODULE$.require((double)0.0F <= value && value <= (double)1.0F, () -> "FDR must be in [0,1]");
      this.fdr_$eq(value);
      return this;
   }

   public ChiSqSelector setFwe(final double value) {
      .MODULE$.require((double)0.0F <= value && value <= (double)1.0F, () -> "FWE must be in [0,1]");
      this.fwe_$eq(value);
      return this;
   }

   public ChiSqSelector setSelectorType(final String value) {
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])ChiSqSelector$.MODULE$.supportedSelectorTypes()), value), () -> "ChiSqSelector Type: " + value + " was not supported.");
      this.selectorType_$eq(value);
      return this;
   }

   public ChiSqSelectorModel fit(final RDD data) {
      Tuple2[] var19;
      label71: {
         Tuple2[] chiSqTestResult;
         label74: {
            chiSqTestResult = scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps(Statistics$.MODULE$.chiSqTest(data)));
            String var5 = this.selectorType();
            String var10000 = ChiSqSelector$.MODULE$.NumTopFeatures();
            if (var10000 == null) {
               if (var5 == null) {
                  break label74;
               }
            } else if (var10000.equals(var5)) {
               break label74;
            }

            label75: {
               var10000 = ChiSqSelector$.MODULE$.Percentile();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label75;
                  }
               } else if (var10000.equals(var5)) {
                  break label75;
               }

               label76: {
                  var10000 = ChiSqSelector$.MODULE$.FPR();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label76;
                     }
                  } else if (var10000.equals(var5)) {
                     break label76;
                  }

                  label77: {
                     var10000 = ChiSqSelector$.MODULE$.FDR();
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label77;
                        }
                     } else if (var10000.equals(var5)) {
                        break label77;
                     }

                     var10000 = ChiSqSelector$.MODULE$.FWE();
                     if (var10000 == null) {
                        if (var5 != null) {
                           throw new IllegalArgumentException("Unknown ChiSqSelector Type: " + var5);
                        }
                     } else if (!var10000.equals(var5)) {
                        throw new IllegalArgumentException("Unknown ChiSqSelector Type: " + var5);
                     }

                     var19 = (Tuple2[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])chiSqTestResult), (x0$6) -> BoxesRunTime.boxToBoolean($anonfun$fit$7(this, chiSqTestResult, x0$6)));
                     break label71;
                  }

                  Tuple2[] tempRes = (Tuple2[])scala.collection.ArrayOps..MODULE$.sortBy$extension(.MODULE$.refArrayOps((Object[])chiSqTestResult), (x0$4) -> BoxesRunTime.boxToDouble($anonfun$fit$4(x0$4)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
                  Tuple2[] selected = (Tuple2[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps((Object[])tempRes))), (x0$5) -> BoxesRunTime.boxToBoolean($anonfun$fit$5(this, chiSqTestResult, x0$5)));
                  if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(.MODULE$.refArrayOps((Object[])selected))) {
                     var19 = (Tuple2[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
                  } else {
                     int maxIndex = BoxesRunTime.unboxToInt(.MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])selected), (x$3) -> BoxesRunTime.boxToInteger($anonfun$fit$6(x$3)), scala.reflect.ClassTag..MODULE$.Int())).max(scala.math.Ordering.Int..MODULE$));
                     var19 = (Tuple2[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.refArrayOps((Object[])tempRes), maxIndex + 1);
                  }
                  break label71;
               }

               var19 = (Tuple2[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])chiSqTestResult), (x0$3) -> BoxesRunTime.boxToBoolean($anonfun$fit$3(this, x0$3)));
               break label71;
            }

            var19 = (Tuple2[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(.MODULE$.refArrayOps((Object[])chiSqTestResult), (x0$2) -> BoxesRunTime.boxToDouble($anonfun$fit$2(x0$2)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), (int)((double)chiSqTestResult.length * this.percentile()));
            break label71;
         }

         var19 = (Tuple2[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(.MODULE$.refArrayOps((Object[])chiSqTestResult), (x0$1) -> BoxesRunTime.boxToDouble($anonfun$fit$1(x0$1)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), this.numTopFeatures());
      }

      Tuple2[] features = var19;
      int[] indices = (int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])features), (x0$7) -> BoxesRunTime.boxToInteger($anonfun$fit$8(x0$7)), scala.reflect.ClassTag..MODULE$.Int());
      return new ChiSqSelectorModel(indices);
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         ChiSqTestResult res = (ChiSqTestResult)x0$1._1();
         return res.pValue();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$2(final Tuple2 x0$2) {
      if (x0$2 != null) {
         ChiSqTestResult res = (ChiSqTestResult)x0$2._1();
         return res.pValue();
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$3(final ChiSqSelector $this, final Tuple2 x0$3) {
      if (x0$3 != null) {
         ChiSqTestResult res = (ChiSqTestResult)x0$3._1();
         return res.pValue() < $this.fpr();
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$4(final Tuple2 x0$4) {
      if (x0$4 != null) {
         ChiSqTestResult res = (ChiSqTestResult)x0$4._1();
         return res.pValue();
      } else {
         throw new MatchError(x0$4);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$5(final ChiSqSelector $this, final Tuple2[] chiSqTestResult$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         Tuple2 var5 = (Tuple2)x0$5._1();
         int index = x0$5._2$mcI$sp();
         if (var5 != null) {
            ChiSqTestResult res = (ChiSqTestResult)var5._1();
            return res.pValue() <= $this.fdr() * (double)(index + 1) / (double)chiSqTestResult$1.length;
         }
      }

      throw new MatchError(x0$5);
   }

   // $FF: synthetic method
   public static final int $anonfun$fit$6(final Tuple2 x$3) {
      return x$3._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$7(final ChiSqSelector $this, final Tuple2[] chiSqTestResult$1, final Tuple2 x0$6) {
      if (x0$6 != null) {
         ChiSqTestResult res = (ChiSqTestResult)x0$6._1();
         return res.pValue() < $this.fwe() / (double)chiSqTestResult$1.length;
      } else {
         throw new MatchError(x0$6);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$fit$8(final Tuple2 x0$7) {
      if (x0$7 != null) {
         int index = x0$7._2$mcI$sp();
         return index;
      } else {
         throw new MatchError(x0$7);
      }
   }

   public ChiSqSelector() {
      this.numTopFeatures = 50;
      this.percentile = 0.1;
      this.fpr = 0.05;
      this.fdr = 0.05;
      this.fwe = 0.05;
      this.selectorType = ChiSqSelector$.MODULE$.NumTopFeatures();
   }

   public ChiSqSelector(final int numTopFeatures) {
      this();
      this.numTopFeatures_$eq(numTopFeatures);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
