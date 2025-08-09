package org.apache.spark.ml.regression;

import breeze.stats.distributions.Gaussian;
import breeze.stats.distributions.StudentsT;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb\u0001B\t\u0013\u0001uA\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\t\t\u0002\u0011\t\u0011)A\u0005\u000b\"A\u0001\n\u0001BC\u0002\u0013%\u0011\n\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003K\u0011!\t\u0006A!b\u0001\n\u0003\u0011\u0006\u0002C0\u0001\u0005\u0003\u0005\u000b\u0011B*\t\u0011\u0005\u0004!Q1A\u0005\u0002\tD\u0001\u0002\u001c\u0001\u0003\u0002\u0003\u0006Ia\u0019\u0005\u0007]\u0002!\tAE8\t\u0011q\u0004!\u0019!C\u0001)uDq!a\u0001\u0001A\u0003%a\u0010C\u0005\u0002\u0006\u0001A)\u0019!C\u0001\u0013\"I\u0011\u0011\u0002\u0001\t\u0006\u0004%\t!\u0013\u0005\n\u0003\u001b\u0001\u0001R1A\u0005\u0002%C1\"!\u0005\u0001\u0011\u000b\u0007I\u0011\u0001\u000b\u0002\u0014!9\u0011Q\u0004\u0001\u0005B\u0005}!AK$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3beJ+wM]3tg&|g\u000e\u0016:bS:LgnZ*v[6\f'/\u001f\u0006\u0003'Q\t!B]3he\u0016\u001c8/[8o\u0015\t)b#\u0001\u0002nY*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001q\"\u0005\u0005\u0002 A5\t!#\u0003\u0002\"%\t\u0011s)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:\u001cV/\\7bef\u0004\"aI\u0017\u000f\u0005\u0011RcBA\u0013)\u001b\u00051#BA\u0014\u001d\u0003\u0019a$o\\8u}%\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Y\u00059\u0001/Y2lC\u001e,'\"A\u0015\n\u00059z#\u0001D*fe&\fG.\u001b>bE2,'BA\u0016-\u0003\u001d!\u0017\r^1tKR\u0004$A\r\u001e\u0011\u0007M2\u0004(D\u00015\u0015\t)d#A\u0002tc2L!a\u000e\u001b\u0003\u000f\u0011\u000bG/Y:fiB\u0011\u0011H\u000f\u0007\u0001\t%Y\u0014!!A\u0001\u0002\u000b\u0005AHA\u0002`IY\n\"!P!\u0011\u0005yzT\"\u0001\u0017\n\u0005\u0001c#a\u0002(pi\"Lgn\u001a\t\u0003}\tK!a\u0011\u0017\u0003\u0007\u0005s\u00170A\u0005pe&<Wj\u001c3fYB\u0011qDR\u0005\u0003\u000fJ\u0011\u0001eR3oKJ\fG.\u001b>fI2Kg.Z1s%\u0016<'/Z:tS>tWj\u001c3fY\u0006YA-[1h\u0013:4\u0018\t^,B+\u0005Q\u0005c\u0001 L\u001b&\u0011A\n\f\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003}9K!a\u0014\u0017\u0003\r\u0011{WO\u00197f\u00031!\u0017.Y4J]Z\fEoV!!\u00035qW/\\%uKJ\fG/[8ogV\t1\u000b\u0005\u0002?)&\u0011Q\u000b\f\u0002\u0004\u0013:$\bfA\u0003X;B\u0011\u0001lW\u0007\u00023*\u0011!LF\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001/Z\u0005\u0015\u0019\u0016N\\2fC\u0005q\u0016!\u0002\u001a/a9\u0002\u0014A\u00048v[&#XM]1uS>t7\u000f\t\u0015\u0004\r]k\u0016AB:pYZ,'/F\u0001d!\t!\u0007N\u0004\u0002fMB\u0011Q\u0005L\u0005\u0003O2\na\u0001\u0015:fI\u00164\u0017BA5k\u0005\u0019\u0019FO]5oO*\u0011q\r\f\u0015\u0004\u000f]k\u0016aB:pYZ,'\u000f\t\u0015\u0004\u0011]k\u0016A\u0002\u001fj]&$h\b\u0006\u0004qcZ<\bP\u001f\t\u0003?\u0001AQ\u0001M\u0005A\u0002I\u0004$a];\u0011\u0007M2D\u000f\u0005\u0002:k\u0012I1(]A\u0001\u0002\u0003\u0015\t\u0001\u0010\u0005\u0006\t&\u0001\r!\u0012\u0005\u0006\u0011&\u0001\rA\u0013\u0005\u0006#&\u0001\ra\u0015\u0015\u0004q^k\u0006\"B1\n\u0001\u0004\u0019\u0007f\u0001>X;\u0006q\u0011n\u001d(pe6\fGnU8mm\u0016\u0014X#\u0001@\u0011\u0005yz\u0018bAA\u0001Y\t9!i\\8mK\u0006t\u0017aD5t\u001d>\u0014X.\u00197T_24XM\u001d\u0011\u00023\r|WM\u001a4jG&,g\u000e^*uC:$\u0017M\u001d3FeJ|'o\u001d\u0015\u0004\u0019]k\u0016a\u0002;WC2,Xm\u001d\u0015\u0004\u001b]k\u0016a\u00029WC2,Xm\u001d\u0015\u0004\u001d]k\u0016AG2pK\u001a4\u0017nY5f]R\u001cx+\u001b;i'R\fG/[:uS\u000e\u001cXCAA\u000b!\u0011q4*a\u0006\u0011\u0011y\nIbY'N\u001b6K1!a\u0007-\u0005\u0019!V\u000f\u001d7fk\u0005AAo\\*ue&tw\rF\u0001dQ\r\u0001q+\u0018"
)
public class GeneralizedLinearRegressionTrainingSummary extends GeneralizedLinearRegressionSummary {
   private double[] coefficientStandardErrors;
   private double[] tValues;
   private double[] pValues;
   private Tuple5[] coefficientsWithStatistics;
   private double[] diagInvAtWA;
   private final int numIterations;
   private final String solver;
   private final boolean isNormalSolver;
   private volatile byte bitmap$0;

   private double[] diagInvAtWA() {
      return this.diagInvAtWA;
   }

   public int numIterations() {
      return this.numIterations;
   }

   public String solver() {
      return this.solver;
   }

   public boolean isNormalSolver() {
      return this.isNormalSolver;
   }

   private double[] coefficientStandardErrors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            if (!this.isNormalSolver()) {
               throw new UnsupportedOperationException("No Std. Error of coefficients available for this GeneralizedLinearRegressionModel");
            }

            this.coefficientStandardErrors = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.diagInvAtWA()), (JFunction1.mcDD.sp)(x$4) -> x$4 * this.dispersion(), scala.reflect.ClassTag..MODULE$.Double())), (JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.sqrt(x), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.diagInvAtWA = null;
      return this.coefficientStandardErrors;
   }

   public double[] coefficientStandardErrors() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.coefficientStandardErrors$lzycompute() : this.coefficientStandardErrors;
   }

   private double[] tValues$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            if (!this.isNormalSolver()) {
               throw new UnsupportedOperationException("No t-statistic available for this GeneralizedLinearRegressionModel");
            }

            double[] estimate = this.model().getFitIntercept() ? (double[])scala.Array..MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new double[][]{this.model().coefficients().toArray(), {this.model().intercept()}})), scala.reflect.ClassTag..MODULE$.Double()) : this.model().coefficients().toArray();
            this.tValues = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(estimate), scala.Predef..MODULE$.wrapDoubleArray(this.coefficientStandardErrors()))), (x) -> BoxesRunTime.boxToDouble($anonfun$tValues$1(x)), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.tValues;
   }

   public double[] tValues() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.tValues$lzycompute() : this.tValues;
   }

   private double[] pValues$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            if (!this.isNormalSolver()) {
               throw new UnsupportedOperationException("No p-value available for this GeneralizedLinearRegressionModel");
            }

            double[] var7;
            label76: {
               label87: {
                  String var10001 = this.model().getFamily().toLowerCase(Locale.ROOT);
                  String var2 = GeneralizedLinearRegression.Binomial$.MODULE$.name();
                  if (var10001 == null) {
                     if (var2 == null) {
                        break label87;
                     }
                  } else if (var10001.equals(var2)) {
                     break label87;
                  }

                  var10001 = this.model().getFamily().toLowerCase(Locale.ROOT);
                  String var3 = GeneralizedLinearRegression.Poisson$.MODULE$.name();
                  if (var10001 == null) {
                     if (var3 == null) {
                        break label87;
                     }
                  } else if (var10001.equals(var3)) {
                     break label87;
                  }

                  var7 = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.tValues()), (JFunction1.mcDD.sp)(x) -> (double)2.0F * ((double)1.0F - (new StudentsT((double)this.degreesOfFreedom(), breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).cdf(scala.math.package..MODULE$.abs(x))), scala.reflect.ClassTag..MODULE$.Double());
                  break label76;
               }

               var7 = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.tValues()), (JFunction1.mcDD.sp)(x) -> (double)2.0F * ((double)1.0F - (new Gaussian((double)0.0F, (double)1.0F, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).cdf(scala.math.package..MODULE$.abs(x))), scala.reflect.ClassTag..MODULE$.Double());
            }

            this.pValues = var7;
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.pValues;
   }

   public double[] pValues() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.pValues$lzycompute() : this.pValues;
   }

   private Tuple5[] coefficientsWithStatistics$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            ObjectRef featureNamesLocal = ObjectRef.create(this.featureNames());
            ObjectRef coefficientsArray = ObjectRef.create(this.model().coefficients().toArray());
            int[] index = scala.Array..MODULE$.range(0, ((double[])coefficientsArray.elem).length);
            if (this.model().getFitIntercept()) {
               featureNamesLocal.elem = (String[]).MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String[])featureNamesLocal.elem)), "(Intercept)", scala.reflect.ClassTag..MODULE$.apply(String.class));
               coefficientsArray.elem = (double[]).MODULE$.$colon$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[])coefficientsArray.elem), BoxesRunTime.boxToDouble(this.model().intercept()), scala.reflect.ClassTag..MODULE$.Double());
               int var5 = ((double[])coefficientsArray.elem).length - 1;
               index = (int[]).MODULE$.$plus$colon$extension(scala.Predef..MODULE$.intArrayOps(index), BoxesRunTime.boxToInteger(var5), scala.reflect.ClassTag..MODULE$.Int());
            }

            this.coefficientsWithStatistics = (Tuple5[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(index), (i) -> $anonfun$coefficientsWithStatistics$1(this, featureNamesLocal, coefficientsArray, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Tuple5.class));
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.coefficientsWithStatistics;
   }

   public Tuple5[] coefficientsWithStatistics() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.coefficientsWithStatistics$lzycompute() : this.coefficientsWithStatistics;
   }

   public String toString() {
      if (!this.isNormalSolver()) {
         throw new UnsupportedOperationException("No summary available for this GeneralizedLinearRegressionModel");
      } else {
         StringBuilder sb;
         label29: {
            String[] colNames = (String[])((Object[])(new String[]{"Feature", "Estimate", "Std Error", "T Value", "P Value"}));
            String[][] data = (String[][]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.coefficientsWithStatistics()), (row) -> {
               Iterator strRow = row.productIterator().map((cell) -> {
                  String var10000;
                  if (cell instanceof String var4) {
                     var10000 = var4;
                  } else {
                     if (!(cell instanceof Double)) {
                        throw new MatchError(cell);
                     }

                     double var5 = BoxesRunTime.unboxToDouble(cell);
                     var10000 = round$1(var5);
                  }

                  String str = var10000;
                  return str.length() > 20 ? str.substring(0, 17) + "..." : str;
               });
               return (String[])strRow.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
            int[] colWidths = (int[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])colNames), (x$5) -> BoxesRunTime.boxToInteger($anonfun$toString$3(x$5)), scala.reflect.ClassTag..MODULE$.Int());
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])data), (strRow) -> {
               $anonfun$toString$4(colWidths, strRow);
               return BoxedUnit.UNIT;
            });
            sb = new StringBuilder();
            sb.append("Coefficients:\n");
            scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])colNames))), (x0$2) -> {
               if (x0$2 != null) {
                  String colName = (String)x0$2._1();
                  int i = x0$2._2$mcI$sp();
                  if (colName != null && true) {
                     return StringUtils.leftPad(colName, colWidths[i]);
                  }
               }

               throw new MatchError(x0$2);
            }, scala.reflect.ClassTag..MODULE$.apply(String.class))).addString(sb, "", " ", "\n");
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])data), (x0$3) -> {
               if (x0$3 != null) {
                  return scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])x0$3))), (x0$4) -> {
                     if (x0$4 != null) {
                        String cell = (String)x0$4._1();
                        int i = x0$4._2$mcI$sp();
                        if (cell != null && true) {
                           return StringUtils.leftPad(cell, colWidths[i]);
                        }
                     }

                     throw new MatchError(x0$4);
                  }, scala.reflect.ClassTag..MODULE$.apply(String.class))).addString(sb, "", " ", "\n");
               } else {
                  throw new MatchError(x0$3);
               }
            });
            sb.append("\n");
            String var10001 = this.family().name();
            sb.append("(Dispersion parameter for " + var10001 + " family taken to be " + round$1(this.dispersion()) + ")");
            sb.append("\n");
            String var10000 = round$1(this.nullDeviance());
            String nd = "Null deviance: " + var10000 + " on " + this.degreesOfFreedom() + " degrees of freedom";
            var10000 = round$1(this.deviance());
            String rd = "Residual deviance: " + var10000 + " on " + this.residualDegreeOfFreedom() + " degrees of freedom";
            int l = scala.math.package..MODULE$.max(nd.length(), rd.length());
            sb.append(StringUtils.leftPad(nd, l));
            sb.append("\n");
            sb.append(StringUtils.leftPad(rd, l));
            var10000 = this.family().name();
            String var8 = "tweedie";
            if (var10000 == null) {
               if (var8 != null) {
                  break label29;
               }
            } else if (!var10000.equals(var8)) {
               break label29;
            }

            BoxedUnit var11 = BoxedUnit.UNIT;
            return sb.toString();
         }

         sb.append("\n");
         sb.append("AIC: " + round$1(this.aic()));
         return sb.toString();
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$tValues$1(final Tuple2 x) {
      return x._1$mcD$sp() / x._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple5 $anonfun$coefficientsWithStatistics$1(final GeneralizedLinearRegressionTrainingSummary $this, final ObjectRef featureNamesLocal$1, final ObjectRef coefficientsArray$1, final int i) {
      return new Tuple5(((String[])featureNamesLocal$1.elem)[i], BoxesRunTime.boxToDouble(((double[])coefficientsArray$1.elem)[i]), BoxesRunTime.boxToDouble($this.coefficientStandardErrors()[i]), BoxesRunTime.boxToDouble($this.tValues()[i]), BoxesRunTime.boxToDouble($this.pValues()[i]));
   }

   private static final String round$1(final double x) {
      return scala.package..MODULE$.BigDecimal().apply(x).setScale(4, scala.math.BigDecimal.RoundingMode..MODULE$.HALF_UP()).toString();
   }

   // $FF: synthetic method
   public static final int $anonfun$toString$3(final String x$5) {
      return x$5.length();
   }

   // $FF: synthetic method
   public static final void $anonfun$toString$5(final int[] colWidths$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String cell = (String)x0$1._1();
         int i = x0$1._2$mcI$sp();
         if (cell != null && true) {
            colWidths$1[i] = scala.math.package..MODULE$.max(colWidths$1[i], cell.length());
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toString$4(final int[] colWidths$1, final String[] strRow) {
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])strRow))), (x0$1) -> {
         $anonfun$toString$5(colWidths$1, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public GeneralizedLinearRegressionTrainingSummary(final Dataset dataset, final GeneralizedLinearRegressionModel origModel, final double[] diagInvAtWA, final int numIterations, final String solver) {
      this.diagInvAtWA = diagInvAtWA;
      this.numIterations = numIterations;
      this.solver = solver;
      super(dataset, origModel);
      this.isNormalSolver = diagInvAtWA.length != 1 || diagInvAtWA[0] != (double)0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
