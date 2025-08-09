package org.apache.spark.mllib.stat;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf\u0001\u0002\u001a4\u0001yBQ!\u0016\u0001\u0005\u0002YCq\u0001\u0017\u0001A\u0002\u0013%\u0011\fC\u0004^\u0001\u0001\u0007I\u0011\u00020\t\r\u0011\u0004\u0001\u0015)\u0003[\u0011%)\u0007\u00011AA\u0002\u0013%a\rC\u0005n\u0001\u0001\u0007\t\u0019!C\u0005]\"I\u0001\u000f\u0001a\u0001\u0002\u0003\u0006Ka\u001a\u0005\nc\u0002\u0001\r\u00111A\u0005\n\u0019D\u0011B\u001d\u0001A\u0002\u0003\u0007I\u0011B:\t\u0013U\u0004\u0001\u0019!A!B\u00139\u0007\"\u0003<\u0001\u0001\u0004\u0005\r\u0011\"\u0003g\u0011%9\b\u00011AA\u0002\u0013%\u0001\u0010C\u0005{\u0001\u0001\u0007\t\u0011)Q\u0005O\"I1\u0010\u0001a\u0001\u0002\u0004%IA\u001a\u0005\ny\u0002\u0001\r\u00111A\u0005\nuD\u0011b \u0001A\u0002\u0003\u0005\u000b\u0015B4\t\u0013\u0005\u0005\u0001\u00011A\u0005\n\u0005\r\u0001\"CA\u0006\u0001\u0001\u0007I\u0011BA\u0007\u0011!\t\t\u0002\u0001Q!\n\u0005\u0015\u0001\"CA\n\u0001\u0001\u0007I\u0011BA\u000b\u0011%\t9\u0002\u0001a\u0001\n\u0013\tI\u0002C\u0004\u0002\u001e\u0001\u0001\u000b\u0015\u00026\t\u0013\u0005}\u0001\u00011A\u0005\n\u0005U\u0001\"CA\u0011\u0001\u0001\u0007I\u0011BA\u0012\u0011\u001d\t9\u0003\u0001Q!\n)D!\"!\u000b\u0001\u0001\u0004\u0005\r\u0011\"\u0003g\u0011-\tY\u0003\u0001a\u0001\u0002\u0004%I!!\f\t\u0015\u0005E\u0002\u00011A\u0001B\u0003&q\rC\u0006\u00024\u0001\u0001\r\u00111A\u0005\n\u0005U\u0002bCA\u001d\u0001\u0001\u0007\t\u0019!C\u0005\u0003wA1\"a\u0010\u0001\u0001\u0004\u0005\t\u0015)\u0003\u00028!Q\u0011\u0011\t\u0001A\u0002\u0003\u0007I\u0011\u00024\t\u0017\u0005\r\u0003\u00011AA\u0002\u0013%\u0011Q\t\u0005\u000b\u0003\u0013\u0002\u0001\u0019!A!B\u00139\u0007BCA&\u0001\u0001\u0007\t\u0019!C\u0005M\"Y\u0011Q\n\u0001A\u0002\u0003\u0007I\u0011BA(\u0011)\t\u0019\u0006\u0001a\u0001\u0002\u0003\u0006Ka\u001a\u0005\b\u0003+\u0002A\u0011AA,\u0011!\t)\u0006\u0001C\u0001o\u0005u\u0004bBAD\u0001\u0011\u0005\u0011\u0011\u0012\u0005\b\u0003#\u0003A\u0011IAJ\u0011\u001d\t9\n\u0001C!\u0003'Cq!a'\u0001\t\u0003\n\u0019\u0001C\u0004\u0002 \u0002!\t%!\u0006\t\u000f\u0005\u0005\u0006\u0001\"\u0011\u0002\u0014\"9\u0011Q\u0015\u0001\u0005B\u0005M\u0005bBAU\u0001\u0011\u0005\u00131\u0013\u0005\b\u0003[\u0003A\u0011IAJ\u0011\u001d\t)\f\u0001C!\u0003'\u0013A$T;mi&4\u0018M]5bi\u0016|e\u000e\\5oKN+X.\\1sSj,'O\u0003\u00025k\u0005!1\u000f^1u\u0015\t1t'A\u0003nY2L'M\u0003\u00029s\u0005)1\u000f]1sW*\u0011!hO\u0001\u0007CB\f7\r[3\u000b\u0003q\n1a\u001c:h\u0007\u0001\u0019B\u0001A F\u0013B\u0011\u0001iQ\u0007\u0002\u0003*\t!)A\u0003tG\u0006d\u0017-\u0003\u0002E\u0003\n1\u0011I\\=SK\u001a\u0004\"AR$\u000e\u0003MJ!\u0001S\u001a\u0003=5+H\u000e^5wCJL\u0017\r^3Ti\u0006$\u0018n\u001d;jG\u0006d7+^7nCJL\bC\u0001&S\u001d\tY\u0005K\u0004\u0002M\u001f6\tQJ\u0003\u0002O{\u00051AH]8pizJ\u0011AQ\u0005\u0003#\u0006\u000bq\u0001]1dW\u0006<W-\u0003\u0002T)\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011+Q\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003]\u0003\"A\u0012\u0001\u0002\u00039,\u0012A\u0017\t\u0003\u0001nK!\u0001X!\u0003\u0007%sG/A\u0003o?\u0012*\u0017\u000f\u0006\u0002`EB\u0011\u0001\tY\u0005\u0003C\u0006\u0013A!\u00168ji\"91mAA\u0001\u0002\u0004Q\u0016a\u0001=%c\u0005\u0011a\u000eI\u0001\tGV\u0014(/T3b]V\tq\rE\u0002AQ*L!![!\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0001[\u0017B\u00017B\u0005\u0019!u.\u001e2mK\u0006a1-\u001e:s\u001b\u0016\fgn\u0018\u0013fcR\u0011ql\u001c\u0005\bG\u001a\t\t\u00111\u0001h\u0003%\u0019WO\u001d:NK\u0006t\u0007%A\u0004dkJ\u0014XJ\r8\u0002\u0017\r,(O]'3]~#S-\u001d\u000b\u0003?RDqaY\u0005\u0002\u0002\u0003\u0007q-\u0001\u0005dkJ\u0014XJ\r8!\u0003\u0019\u0019WO\u001d:Ne\u0005Q1-\u001e:s\u001bJzF%Z9\u0015\u0005}K\bbB2\r\u0003\u0003\u0005\raZ\u0001\bGV\u0014(/\u0014\u001a!\u0003\u0019\u0019WO\u001d:Mc\u0005Q1-\u001e:s\u0019FzF%Z9\u0015\u0005}s\bbB2\u0010\u0003\u0003\u0005\raZ\u0001\bGV\u0014(\u000fT\u0019!\u0003!!x\u000e^1m\u0007:$XCAA\u0003!\r\u0001\u0015qA\u0005\u0004\u0003\u0013\t%\u0001\u0002'p]\u001e\fA\u0002^8uC2\u001ce\u000e^0%KF$2aXA\b\u0011!\u0019'#!AA\u0002\u0005\u0015\u0011!\u0003;pi\u0006d7I\u001c;!\u00039!x\u000e^1m/\u0016Lw\r\u001b;Tk6,\u0012A[\u0001\u0013i>$\u0018\r\\,fS\u001eDGoU;n?\u0012*\u0017\u000fF\u0002`\u00037AqaY\u000b\u0002\u0002\u0003\u0007!.A\bu_R\fGnV3jO\"$8+^7!\u0003=9X-[4iiN\u000bX/\u0019:f'Vl\u0017aE<fS\u001eDGoU9vCJ,7+^7`I\u0015\fHcA0\u0002&!91\rGA\u0001\u0002\u0004Q\u0017\u0001E<fS\u001eDGoU9vCJ,7+^7!\u00035\u0019WO\u001d:XK&<\u0007\u000e^*v[\u0006\t2-\u001e:s/\u0016Lw\r\u001b;Tk6|F%Z9\u0015\u0007}\u000by\u0003C\u0004d7\u0005\u0005\t\u0019A4\u0002\u001d\r,(O],fS\u001eDGoU;nA\u0005\u0019aN\u001c>\u0016\u0005\u0005]\u0002\u0003\u0002!i\u0003\u000b\tqA\u001c8{?\u0012*\u0017\u000fF\u0002`\u0003{A\u0001b\u0019\u0010\u0002\u0002\u0003\u0007\u0011qG\u0001\u0005]:T\b%A\u0004dkJ\u0014X*\u0019=\u0002\u0017\r,(O]'bq~#S-\u001d\u000b\u0004?\u0006\u001d\u0003bB2\"\u0003\u0003\u0005\raZ\u0001\tGV\u0014(/T1yA\u000591-\u001e:s\u001b&t\u0017aC2veJl\u0015N\\0%KF$2aXA)\u0011\u001d\u0019G%!AA\u0002\u001d\f\u0001bY;se6Kg\u000eI\u0001\u0004C\u0012$G\u0003BA-\u00037j\u0011\u0001\u0001\u0005\b\u0003;2\u0003\u0019AA0\u0003\u0019\u0019\u0018-\u001c9mKB!\u0011\u0011MA4\u001b\t\t\u0019GC\u0002\u0002fU\na\u0001\\5oC2<\u0017\u0002BA5\u0003G\u0012aAV3di>\u0014\b&\u0002\u0014\u0002n\u0005e\u0004\u0003BA8\u0003kj!!!\u001d\u000b\u0007\u0005Mt'\u0001\u0006b]:|G/\u0019;j_:LA!a\u001e\u0002r\t)1+\u001b8dK\u0006\u0012\u00111P\u0001\u0006c9\nd\u0006\r\u000b\u0007\u00033\ny(a!\t\u000f\u0005\u0005u\u00051\u0001\u0002`\u0005A\u0011N\\:uC:\u001cW\r\u0003\u0004\u0002\u0006\u001e\u0002\rA[\u0001\u0007o\u0016Lw\r\u001b;\u0002\u000b5,'oZ3\u0015\t\u0005e\u00131\u0012\u0005\u0007\u0003\u001bC\u0003\u0019A,\u0002\u000b=$\b.\u001a:)\u000b!\ni'!\u001f\u0002\t5,\u0017M\\\u000b\u0003\u0003?BS!KA7\u0003s\n\u0001B^1sS\u0006t7-\u001a\u0015\u0006U\u00055\u0014\u0011P\u0001\u0006G>,h\u000e\u001e\u0015\u0006W\u00055\u0014\u0011P\u0001\no\u0016Lw\r\u001b;Tk6\f1B\\;n\u001d>t'0\u001a:pg\"*Q&!\u001c\u0002z\u0005\u0019Q.\u0019=)\u000b9\ni'!\u001f\u0002\u00075Lg\u000eK\u00030\u0003[\nI(\u0001\u0004o_JlGJ\r\u0015\u0006a\u00055\u0014\u0011W\u0011\u0003\u0003g\u000bQ!\r\u00183]A\naA\\8s[2\u000b\u0004&B\u0019\u0002n\u0005E\u0006&\u0002\u0001\u0002n\u0005e\u0004"
)
public class MultivariateOnlineSummarizer implements MultivariateStatisticalSummary, Serializable {
   private int n = 0;
   private double[] currMean;
   private double[] currM2n;
   private double[] currM2;
   private double[] currL1;
   private long totalCnt = 0L;
   private double totalWeightSum = (double)0.0F;
   private double weightSquareSum = (double)0.0F;
   private double[] currWeightSum;
   private long[] nnz;
   private double[] currMax;
   private double[] currMin;

   private int n() {
      return this.n;
   }

   private void n_$eq(final int x$1) {
      this.n = x$1;
   }

   private double[] currMean() {
      return this.currMean;
   }

   private void currMean_$eq(final double[] x$1) {
      this.currMean = x$1;
   }

   private double[] currM2n() {
      return this.currM2n;
   }

   private void currM2n_$eq(final double[] x$1) {
      this.currM2n = x$1;
   }

   private double[] currM2() {
      return this.currM2;
   }

   private void currM2_$eq(final double[] x$1) {
      this.currM2 = x$1;
   }

   private double[] currL1() {
      return this.currL1;
   }

   private void currL1_$eq(final double[] x$1) {
      this.currL1 = x$1;
   }

   private long totalCnt() {
      return this.totalCnt;
   }

   private void totalCnt_$eq(final long x$1) {
      this.totalCnt = x$1;
   }

   private double totalWeightSum() {
      return this.totalWeightSum;
   }

   private void totalWeightSum_$eq(final double x$1) {
      this.totalWeightSum = x$1;
   }

   private double weightSquareSum() {
      return this.weightSquareSum;
   }

   private void weightSquareSum_$eq(final double x$1) {
      this.weightSquareSum = x$1;
   }

   private double[] currWeightSum() {
      return this.currWeightSum;
   }

   private void currWeightSum_$eq(final double[] x$1) {
      this.currWeightSum = x$1;
   }

   private long[] nnz() {
      return this.nnz;
   }

   private void nnz_$eq(final long[] x$1) {
      this.nnz = x$1;
   }

   private double[] currMax() {
      return this.currMax;
   }

   private void currMax_$eq(final double[] x$1) {
      this.currMax = x$1;
   }

   private double[] currMin() {
      return this.currMin;
   }

   private void currMin_$eq(final double[] x$1) {
      this.currMin = x$1;
   }

   public MultivariateOnlineSummarizer add(final Vector sample) {
      return this.add(sample, (double)1.0F);
   }

   public MultivariateOnlineSummarizer add(final Vector instance, final double weight) {
      .MODULE$.require(weight >= (double)0.0F, () -> "sample weight, " + weight + " has to be >= 0.0");
      if (weight == (double)0.0F) {
         return this;
      } else {
         if (this.n() == 0) {
            .MODULE$.require(instance.size() > 0, () -> "Vector should have dimension larger than zero.");
            this.n_$eq(instance.size());
            this.currMean_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            this.currM2n_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            this.currM2_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            this.currL1_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            this.currWeightSum_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            this.nnz_$eq((long[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Long()));
            this.currMax_$eq((double[])scala.Array..MODULE$.fill(this.n(), (JFunction0.mcD.sp)() -> -Double.MAX_VALUE, scala.reflect.ClassTag..MODULE$.Double()));
            this.currMin_$eq((double[])scala.Array..MODULE$.fill(this.n(), (JFunction0.mcD.sp)() -> Double.MAX_VALUE, scala.reflect.ClassTag..MODULE$.Double()));
         }

         .MODULE$.require(this.n() == instance.size(), () -> {
            int var10000 = this.n();
            return "Dimensions mismatch when adding new sample. Expecting " + var10000 + " but got " + instance.size() + ".";
         });
         double[] localCurrMean = this.currMean();
         double[] localCurrM2n = this.currM2n();
         double[] localCurrM2 = this.currM2();
         double[] localCurrL1 = this.currL1();
         double[] localWeightSum = this.currWeightSum();
         long[] localNumNonzeros = this.nnz();
         double[] localCurrMax = this.currMax();
         double[] localCurrMin = this.currMin();
         instance.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> {
            if (localCurrMax[index] < value) {
               localCurrMax[index] = value;
            }

            if (localCurrMin[index] > value) {
               localCurrMin[index] = value;
            }

            double prevMean = localCurrMean[index];
            double diff = value - prevMean;
            localCurrMean[index] = prevMean + weight * diff / (localWeightSum[index] + weight);
            localCurrM2n[index] += weight * (value - localCurrMean[index]) * diff;
            localCurrM2[index] += weight * value * value;
            localCurrL1[index] += weight * scala.math.package..MODULE$.abs(value);
            localWeightSum[index] += weight;
            int var10002 = localNumNonzeros[index]++;
         });
         this.totalWeightSum_$eq(this.totalWeightSum() + weight);
         this.weightSquareSum_$eq(this.weightSquareSum() + weight * weight);
         this.totalCnt_$eq(this.totalCnt() + 1L);
         return this;
      }
   }

   public MultivariateOnlineSummarizer merge(final MultivariateOnlineSummarizer other) {
      if (this.totalWeightSum() != (double)0.0F && other.totalWeightSum() != (double)0.0F) {
         .MODULE$.require(this.n() == other.n(), () -> {
            int var10000 = this.n();
            return "Dimensions mismatch when merging with another summarizer. Expecting " + var10000 + " but got " + other.n() + ".";
         });
         this.totalCnt_$eq(this.totalCnt() + other.totalCnt());
         this.totalWeightSum_$eq(this.totalWeightSum() + other.totalWeightSum());
         this.weightSquareSum_$eq(this.weightSquareSum() + other.weightSquareSum());

         for(int i = 0; i < this.n(); ++i) {
            double thisNnz = this.currWeightSum()[i];
            double otherNnz = other.currWeightSum()[i];
            double totalNnz = thisNnz + otherNnz;
            long totalCnnz = this.nnz()[i] + other.nnz()[i];
            if (totalNnz != (double)0.0F) {
               double deltaMean = other.currMean()[i] - this.currMean()[i];
               double[] var13 = this.currMean();
               var13[i] += deltaMean * otherNnz / totalNnz;
               double[] var15 = this.currM2n();
               var15[i] += other.currM2n()[i] + deltaMean * deltaMean * thisNnz * otherNnz / totalNnz;
               double[] var17 = this.currM2();
               var17[i] += other.currM2()[i];
               double[] var19 = this.currL1();
               var19[i] += other.currL1()[i];
               this.currMax()[i] = scala.math.package..MODULE$.max(this.currMax()[i], other.currMax()[i]);
               this.currMin()[i] = scala.math.package..MODULE$.min(this.currMin()[i], other.currMin()[i]);
            }

            this.currWeightSum()[i] = totalNnz;
            this.nnz()[i] = totalCnnz;
         }
      } else if (this.totalWeightSum() == (double)0.0F && other.totalWeightSum() != (double)0.0F) {
         this.n_$eq(other.n());
         this.currMean_$eq((double[])other.currMean().clone());
         this.currM2n_$eq((double[])other.currM2n().clone());
         this.currM2_$eq((double[])other.currM2().clone());
         this.currL1_$eq((double[])other.currL1().clone());
         this.totalCnt_$eq(other.totalCnt());
         this.totalWeightSum_$eq(other.totalWeightSum());
         this.weightSquareSum_$eq(other.weightSquareSum());
         this.currWeightSum_$eq((double[])other.currWeightSum().clone());
         this.nnz_$eq((long[])other.nnz().clone());
         this.currMax_$eq((double[])other.currMax().clone());
         this.currMin_$eq((double[])other.currMin().clone());
      }

      return this;
   }

   public Vector mean() {
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realMean = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());

      for(int i = 0; i < this.n(); ++i) {
         realMean[i] = this.currMean()[i] * (this.currWeightSum()[i] / this.totalWeightSum());
      }

      return Vectors$.MODULE$.dense(realMean);
   }

   public Vector variance() {
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realVariance = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());
      double denominator = this.totalWeightSum() - this.weightSquareSum() / this.totalWeightSum();
      if (denominator > (double)0.0F) {
         double[] deltaMean = this.currMean();
         int i = 0;

         for(int len = this.currM2n().length; i < len; ++i) {
            realVariance[i] = scala.math.package..MODULE$.max((this.currM2n()[i] + deltaMean[i] * deltaMean[i] * this.currWeightSum()[i] * (this.totalWeightSum() - this.currWeightSum()[i]) / this.totalWeightSum()) / denominator, (double)0.0F);
         }
      }

      return Vectors$.MODULE$.dense(realVariance);
   }

   public long count() {
      return this.totalCnt();
   }

   public double weightSum() {
      return this.totalWeightSum();
   }

   public Vector numNonzeros() {
      .MODULE$.require(this.totalCnt() > 0L, () -> "Nothing has been added to this summarizer.");
      return Vectors$.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.longArrayOps(this.nnz()), (JFunction1.mcDJ.sp)(x$9) -> (double)x$9, scala.reflect.ClassTag..MODULE$.Double()));
   }

   public Vector max() {
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");

      for(int i = 0; i < this.n(); ++i) {
         if (this.nnz()[i] < this.totalCnt() && this.currMax()[i] < (double)0.0F) {
            this.currMax()[i] = (double)0.0F;
         }
      }

      return Vectors$.MODULE$.dense(this.currMax());
   }

   public Vector min() {
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");

      for(int i = 0; i < this.n(); ++i) {
         if (this.nnz()[i] < this.totalCnt() && this.currMin()[i] > (double)0.0F) {
            this.currMin()[i] = (double)0.0F;
         }
      }

      return Vectors$.MODULE$.dense(this.currMin());
   }

   public Vector normL2() {
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realMagnitude = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());
      int i = 0;

      for(int len = this.currM2().length; i < len; ++i) {
         realMagnitude[i] = scala.math.package..MODULE$.sqrt(this.currM2()[i]);
      }

      return Vectors$.MODULE$.dense(realMagnitude);
   }

   public Vector normL1() {
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      return Vectors$.MODULE$.dense(this.currL1());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
