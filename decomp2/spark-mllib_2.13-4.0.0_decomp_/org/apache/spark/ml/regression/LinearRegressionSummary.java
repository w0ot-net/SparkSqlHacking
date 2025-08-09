package org.apache.spark.ml.regression;

import breeze.stats.distributions.StudentsT;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.Summary;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import scala.MatchError;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ue\u0001\u0002\u0013&\u0001AB\u0001\"\u0013\u0001\u0003\u0006\u0004%\tA\u0013\u0005\t7\u0002\u0011\t\u0011)A\u0005\u0017\"A\u0001\r\u0001BC\u0002\u0013\u0005\u0011\r\u0003\u0005k\u0001\t\u0005\t\u0015!\u0003c\u0011!Y\u0007A!b\u0001\n\u0003\t\u0007\u0002\u00037\u0001\u0005\u0003\u0005\u000b\u0011\u00022\t\u00115\u0004!Q1A\u0005\u0002\u0005D\u0001B\u001c\u0001\u0003\u0002\u0003\u0006IA\u0019\u0005\t_\u0002\u0011)\u0019!C\u0005a\"AQ\u000f\u0001B\u0001B\u0003%\u0011\u000f\u0003\u0005w\u0001\t\u0015\r\u0011\"\u0003x\u0011!q\bA!A!\u0002\u0013A\bbB@\u0001\t\u0003)\u0013\u0011\u0001\u0005\n\u0003#\u0001!\u0019!C\u0005\u0003'A\u0001\"!\n\u0001A\u0003%\u0011Q\u0003\u0005\n\u0003S\u0001!\u0019!C\u0001\u0003WAq!a\u0010\u0001A\u0003%1\u0010C\u0005\u0002D\u0001\u0011\r\u0011\"\u0001\u0002,!9\u0011q\t\u0001!\u0002\u0013Y\b\"CA&\u0001\t\u0007I\u0011AA\u0016\u0011\u001d\ty\u0005\u0001Q\u0001\nmD\u0011\"a\u0015\u0001\u0005\u0004%\t!a\u000b\t\u000f\u0005]\u0003\u0001)A\u0005w\"I\u00111\f\u0001C\u0002\u0013\u0005\u00111\u0006\u0005\b\u0003?\u0002\u0001\u0015!\u0003|\u0011%\t\u0019\u0007\u0001b\u0001\n\u0003\tY\u0003C\u0004\u0002l\u0001\u0001\u000b\u0011B>\t\u0013\u0005=\u0004\u0001#b\u0001\n\u0003Q\u0005BCA;\u0001!\u0015\r\u0011\"\u0001\u0002x!I\u0011q\u0010\u0001C\u0002\u0013\u0005\u0011q\u000f\u0005\t\u0003\u000f\u0003\u0001\u0015!\u0003\u0002z!I\u00111\u0012\u0001\t\u0006\u0004%\ta\u001e\u0005\n\u0003\u001b\u0003\u0001R1A\u0005\u0002]D\u0011\"a$\u0001\u0011\u000b\u0007I\u0011A<\t\u0013\u0005E\u0005\u0001#b\u0001\n\u00039(a\u0006'j]\u0016\f'OU3he\u0016\u001c8/[8o'VlW.\u0019:z\u0015\t1s%\u0001\u0006sK\u001e\u0014Xm]:j_:T!\u0001K\u0015\u0002\u00055d'B\u0001\u0016,\u0003\u0015\u0019\b/\u0019:l\u0015\taS&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002]\u0005\u0019qN]4\u0004\u0001M!\u0001!M\u001c>!\t\u0011T'D\u00014\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c4\u0005\u0019\te.\u001f*fMB\u0011\u0001hO\u0007\u0002s)\u0011!hJ\u0001\u0005kRLG.\u0003\u0002=s\t91+^7nCJL\bC\u0001 G\u001d\tyDI\u0004\u0002A\u00076\t\u0011I\u0003\u0002C_\u00051AH]8pizJ\u0011\u0001N\u0005\u0003\u000bN\nq\u0001]1dW\u0006<W-\u0003\u0002H\u0011\na1+\u001a:jC2L'0\u00192mK*\u0011QiM\u0001\faJ,G-[2uS>t7/F\u0001L!\ta\u0005L\u0004\u0002N-:\u0011a\n\u0016\b\u0003\u001fNs!\u0001\u0015*\u000f\u0005\u0001\u000b\u0016\"\u0001\u0018\n\u00051j\u0013B\u0001\u0016,\u0013\t)\u0016&A\u0002tc2L!!R,\u000b\u0005UK\u0013BA-[\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002F/\u0006a\u0001O]3eS\u000e$\u0018n\u001c8tA!\u0012!!\u0018\t\u0003eyK!aX\u001a\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!\u00049sK\u0012L7\r^5p]\u000e{G.F\u0001c!\t\u0019wM\u0004\u0002eKB\u0011\u0001iM\u0005\u0003MN\na\u0001\u0015:fI\u00164\u0017B\u00015j\u0005\u0019\u0019FO]5oO*\u0011amM\u0001\u000faJ,G-[2uS>t7i\u001c7!\u0003!a\u0017MY3m\u0007>d\u0017!\u00037bE\u0016d7i\u001c7!\u0003-1W-\u0019;ve\u0016\u001c8i\u001c7\u0002\u0019\u0019,\u0017\r^;sKN\u001cu\u000e\u001c\u0011\u0002\u0019A\u0014\u0018N^1uK6{G-\u001a7\u0016\u0003E\u0004\"A]:\u000e\u0003\u0015J!\u0001^\u0013\u0003+1Kg.Z1s%\u0016<'/Z:tS>tWj\u001c3fY\u0006i\u0001O]5wCR,Wj\u001c3fY\u0002\n1\u0002Z5bO&sg/\u0011;X\u0003V\t\u0001\u0010E\u00023snL!A_\u001a\u0003\u000b\u0005\u0013(/Y=\u0011\u0005Ib\u0018BA?4\u0005\u0019!u.\u001e2mK\u0006aA-[1h\u0013:4\u0018\t^,BA\u00051A(\u001b8jiz\"b\"a\u0001\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006\u0003\u001b\ty\u0001\u0005\u0002s\u0001!)\u0011*\u0004a\u0001\u0017\")\u0001-\u0004a\u0001E\")1.\u0004a\u0001E\")Q.\u0004a\u0001E\")q.\u0004a\u0001c\")a/\u0004a\u0001q\u00069Q.\u001a;sS\u000e\u001cXCAA\u000b!\u0011\t9\"!\t\u000e\u0005\u0005e!\u0002BA\u000e\u0003;\t!\"\u001a<bYV\fG/[8o\u0015\r\ty\"K\u0001\u0006[2d\u0017NY\u0005\u0005\u0003G\tIBA\tSK\u001e\u0014Xm]:j_:lU\r\u001e:jGN\f\u0001\"\\3ue&\u001c7\u000f\t\u0015\u0003\u001fu\u000b\u0011#\u001a=qY\u0006Lg.\u001a3WCJL\u0017M\\2f+\u0005Y\b&\u0002\t\u00020\u0005m\u0002\u0003BA\u0019\u0003oi!!a\r\u000b\u0007\u0005U\u0012&\u0001\u0006b]:|G/\u0019;j_:LA!!\u000f\u00024\t)1+\u001b8dK\u0006\u0012\u0011QH\u0001\u0006c9*d\u0006M\u0001\u0013Kb\u0004H.Y5oK\u00124\u0016M]5b]\u000e,\u0007\u0005K\u0003\u0012\u0003_\tY$A\tnK\u0006t\u0017IY:pYV$X-\u0012:s_JDSAEA\u0018\u0003w\t!#\\3b]\u0006\u00137o\u001c7vi\u0016,%O]8sA!*1#a\f\u0002<\u0005\u0001R.Z1o'F,\u0018M]3e\u000bJ\u0014xN\u001d\u0015\u0006)\u0005=\u00121H\u0001\u0012[\u0016\fgnU9vCJ,G-\u0012:s_J\u0004\u0003&B\u000b\u00020\u0005m\u0012\u0001\u0006:p_RlU-\u00198TcV\f'/\u001a3FeJ|'\u000fK\u0003\u0017\u0003_\tY$A\u000bs_>$X*Z1o'F,\u0018M]3e\u000bJ\u0014xN\u001d\u0011)\u000b]\ty#a\u000f\u0002\u0005I\u0014\u0004&\u0002\r\u00020\u0005m\u0012a\u0001:3A!*\u0011$a\f\u0002<\u0005)!OM1eU\"*!$a\f\u0002h\u0005\u0012\u0011\u0011N\u0001\u0006e9\u001ad\u0006M\u0001\u0007eJ\nGM\u001b\u0011)\u000bm\ty#a\u001a\u0002\u0013I,7/\u001b3vC2\u001c\b&\u0002\u000f\u00020\u0005m\u0002F\u0001\u000f^\u00031qW/\\%ogR\fgnY3t+\t\tI\bE\u00023\u0003wJ1!! 4\u0005\u0011auN\\4\u0002!\u0011,wM]3fg>3gI]3fI>l\u0007&\u0002\u0010\u00020\u0005\r\u0015EAAC\u0003\u0015\u0011dF\r\u00181\u0003E!Wm\u001a:fKN|eM\u0012:fK\u0012|W\u000e\t\u0015\u0006?\u0005=\u00121Q\u0001\u0012I\u00164\u0018.\u00198dKJ+7/\u001b3vC2\u001c\u0018!G2pK\u001a4\u0017nY5f]R\u001cF/\u00198eCJ$WI\u001d:peN\fq\u0001\u001e,bYV,7/A\u0004q-\u0006dW/Z:)\u000b\u0001\ty#a\u000f"
)
public class LinearRegressionSummary implements Summary, Serializable {
   private transient Dataset residuals;
   private long numInstances;
   private double[] devianceResiduals;
   private double[] coefficientStandardErrors;
   private double[] tValues;
   private double[] pValues;
   private final transient Dataset predictions;
   private final String predictionCol;
   private final String labelCol;
   private final String featuresCol;
   private final LinearRegressionModel privateModel;
   private final double[] diagInvAtWA;
   private final transient RegressionMetrics metrics;
   private final double explainedVariance;
   private final double meanAbsoluteError;
   private final double meanSquaredError;
   private final double rootMeanSquaredError;
   private final double r2;
   private final double r2adj;
   private final long degreesOfFreedom;
   private volatile byte bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public Dataset predictions() {
      return this.predictions;
   }

   public String predictionCol() {
      return this.predictionCol;
   }

   public String labelCol() {
      return this.labelCol;
   }

   public String featuresCol() {
      return this.featuresCol;
   }

   private LinearRegressionModel privateModel() {
      return this.privateModel;
   }

   private double[] diagInvAtWA() {
      return this.diagInvAtWA;
   }

   private RegressionMetrics metrics() {
      return this.metrics;
   }

   public double explainedVariance() {
      return this.explainedVariance;
   }

   public double meanAbsoluteError() {
      return this.meanAbsoluteError;
   }

   public double meanSquaredError() {
      return this.meanSquaredError;
   }

   public double rootMeanSquaredError() {
      return this.rootMeanSquaredError;
   }

   public double r2() {
      return this.r2;
   }

   public double r2adj() {
      return this.r2adj;
   }

   private Dataset residuals$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            UserDefinedFunction t = .MODULE$.udf((JFunction2.mcDDD.sp)(pred, label) -> label - pred, ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
            this.residuals = this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{t.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col(this.predictionCol()), .MODULE$.col(this.labelCol())}))).as("residuals")})));
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.residuals;
   }

   public Dataset residuals() {
      return !this.bitmap$trans$0 ? this.residuals$lzycompute() : this.residuals;
   }

   private long numInstances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numInstances = this.metrics().count();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numInstances;
   }

   public long numInstances() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.numInstances$lzycompute() : this.numInstances;
   }

   public long degreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   private double[] devianceResiduals$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            Column weighted = this.privateModel().isDefined(this.privateModel().weightCol()) && !this.privateModel().getWeightCol().isEmpty() ? .MODULE$.sqrt(.MODULE$.col(this.privateModel().getWeightCol())) : .MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F));
            Row dr = (Row)this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col(this.privateModel().getLabelCol()).minus(.MODULE$.col(this.privateModel().getPredictionCol())).multiply(weighted).as("weightedResiduals")}))).select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.min(.MODULE$.col("weightedResiduals")).as("min"), .MODULE$.max(.MODULE$.col("weightedResiduals")).as("max")}))).first();
            this.devianceResiduals = new double[]{dr.getDouble(0), dr.getDouble(1)};
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.devianceResiduals;
   }

   public double[] devianceResiduals() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.devianceResiduals$lzycompute() : this.devianceResiduals;
   }

   private double[] coefficientStandardErrors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            if (this.diagInvAtWA().length == 1 && this.diagInvAtWA()[0] == (double)0) {
               throw new UnsupportedOperationException("No Std. Error of coefficients available for this LinearRegressionModel");
            }

            double var10001;
            if (this.privateModel().isDefined(this.privateModel().weightCol()) && !this.privateModel().getWeightCol().isEmpty()) {
               UserDefinedFunction t = .MODULE$.udf((pred, label, weight) -> BoxesRunTime.boxToDouble($anonfun$coefficientStandardErrors$1(BoxesRunTime.unboxToDouble(pred), BoxesRunTime.unboxToDouble(label), BoxesRunTime.unboxToDouble(weight))), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
               var10001 = ((Row)this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{t.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col(this.privateModel().getPredictionCol()), .MODULE$.col(this.privateModel().getLabelCol()), .MODULE$.col(this.privateModel().getWeightCol())}))).as("wse")}))).agg(.MODULE$.sum(.MODULE$.col("wse")), scala.collection.immutable.Nil..MODULE$).first()).getDouble(0);
            } else {
               var10001 = this.meanSquaredError() * (double)this.numInstances();
            }

            double rss = var10001;
            double sigma2 = rss / (double)this.degreesOfFreedom();
            this.coefficientStandardErrors = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.diagInvAtWA()), (JFunction1.mcDD.sp)(x$12) -> x$12 * sigma2, scala.reflect.ClassTag..MODULE$.Double())), (JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.sqrt(x), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var8) {
         throw var8;
      }

      return this.coefficientStandardErrors;
   }

   public double[] coefficientStandardErrors() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.coefficientStandardErrors$lzycompute() : this.coefficientStandardErrors;
   }

   private double[] tValues$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            if (this.diagInvAtWA().length == 1 && this.diagInvAtWA()[0] == (double)0) {
               throw new UnsupportedOperationException("No t-statistic available for this LinearRegressionModel");
            }

            double[] estimate = this.privateModel().getFitIntercept() ? (double[])scala.Array..MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new double[][]{this.privateModel().coefficients().toArray(), {this.privateModel().intercept()}})), scala.reflect.ClassTag..MODULE$.Double()) : this.privateModel().coefficients().toArray();
            this.tValues = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(estimate), scala.Predef..MODULE$.wrapDoubleArray(this.coefficientStandardErrors()))), (x) -> BoxesRunTime.boxToDouble($anonfun$tValues$1(x)), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.tValues;
   }

   public double[] tValues() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.tValues$lzycompute() : this.tValues;
   }

   private double[] pValues$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            if (this.diagInvAtWA().length == 1 && this.diagInvAtWA()[0] == (double)0) {
               throw new UnsupportedOperationException("No p-value available for this LinearRegressionModel");
            }

            this.pValues = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.tValues()), (JFunction1.mcDD.sp)(x) -> (double)2.0F * ((double)1.0F - (new StudentsT((double)this.degreesOfFreedom(), breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).cdf(scala.math.package..MODULE$.abs(x))), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.pValues;
   }

   public double[] pValues() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.pValues$lzycompute() : this.pValues;
   }

   // $FF: synthetic method
   public static final double $anonfun$coefficientStandardErrors$1(final double pred, final double label, final double weight) {
      return scala.math.package..MODULE$.pow(label - pred, (double)2.0F) * weight;
   }

   // $FF: synthetic method
   public static final double $anonfun$tValues$1(final Tuple2 x) {
      return x._1$mcD$sp() / x._2$mcD$sp();
   }

   public LinearRegressionSummary(final Dataset predictions, final String predictionCol, final String labelCol, final String featuresCol, final LinearRegressionModel privateModel, final double[] diagInvAtWA) {
      this.predictions = predictions;
      this.predictionCol = predictionCol;
      this.labelCol = labelCol;
      this.featuresCol = featuresCol;
      this.privateModel = privateModel;
      this.diagInvAtWA = diagInvAtWA;
      Column weightCol = privateModel.isDefined(privateModel.weightCol()) && !privateModel.getWeightCol().isEmpty() ? .MODULE$.col(privateModel.getWeightCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$) : .MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F));
      this.metrics = new RegressionMetrics(predictions.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col(predictionCol), .MODULE$.col(labelCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$), weightCol}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object pred = ((SeqOps)var3.get()).apply(0);
               Object label = ((SeqOps)var3.get()).apply(1);
               Object weight = ((SeqOps)var3.get()).apply(2);
               if (pred instanceof Double) {
                  double var7 = BoxesRunTime.unboxToDouble(pred);
                  if (label instanceof Double) {
                     double var9 = BoxesRunTime.unboxToDouble(label);
                     if (weight instanceof Double) {
                        double var11 = BoxesRunTime.unboxToDouble(weight);
                        return new Tuple3(BoxesRunTime.boxToDouble(var7), BoxesRunTime.boxToDouble(var9), BoxesRunTime.boxToDouble(var11));
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Product.class)), !privateModel.getFitIntercept());
      this.explainedVariance = this.metrics().explainedVariance();
      this.meanAbsoluteError = this.metrics().meanAbsoluteError();
      this.meanSquaredError = this.metrics().meanSquaredError();
      this.rootMeanSquaredError = this.metrics().rootMeanSquaredError();
      this.r2 = this.metrics().r2();
      int interceptDOF = privateModel.getFitIntercept() ? 1 : 0;
      this.r2adj = (double)1 - ((double)1 - this.r2()) * (double)(this.numInstances() - (long)interceptDOF) / (double)(this.numInstances() - (long)privateModel.coefficients().size() - (long)interceptDOF);
      this.degreesOfFreedom = privateModel.getFitIntercept() ? this.numInstances() - (long)privateModel.coefficients().size() - 1L : this.numInstances() - (long)privateModel.coefficients().size();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
