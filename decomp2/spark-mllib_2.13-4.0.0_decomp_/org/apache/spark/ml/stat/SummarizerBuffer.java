package org.apache.spark.ml.stat;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015h!B\u001d;\u0001y\"\u0005\u0002C*\u0001\u0005\u0003\u0005\u000b\u0011B+\t\u0011%\u0004!\u0011!Q\u0001\n)DQA\u001c\u0001\u0005\u0002=Dqa\u001d\u0001A\u0002\u0013%A\u000fC\u0004y\u0001\u0001\u0007I\u0011B=\t\r}\u0004\u0001\u0015)\u0003v\u0011%\t\t\u0001\u0001a\u0001\n\u0013\t\u0019\u0001C\u0005\u0002\u0012\u0001\u0001\r\u0011\"\u0003\u0002\u0014!A\u0011q\u0003\u0001!B\u0013\t)\u0001C\u0005\u0002\u001a\u0001\u0001\r\u0011\"\u0003\u0002\u0004!I\u00111\u0004\u0001A\u0002\u0013%\u0011Q\u0004\u0005\t\u0003C\u0001\u0001\u0015)\u0003\u0002\u0006!I\u00111\u0005\u0001A\u0002\u0013%\u00111\u0001\u0005\n\u0003K\u0001\u0001\u0019!C\u0005\u0003OA\u0001\"a\u000b\u0001A\u0003&\u0011Q\u0001\u0005\n\u0003[\u0001\u0001\u0019!C\u0005\u0003\u0007A\u0011\"a\f\u0001\u0001\u0004%I!!\r\t\u0011\u0005U\u0002\u0001)Q\u0005\u0003\u000bA\u0011\"a\u000e\u0001\u0001\u0004%I!!\u000f\t\u0013\u0005\u0005\u0003\u00011A\u0005\n\u0005\r\u0003\u0002CA$\u0001\u0001\u0006K!a\u000f\t\u0013\u0005%\u0003\u00011A\u0005\n\u0005-\u0003\"CA'\u0001\u0001\u0007I\u0011BA(\u0011!\t\u0019\u0006\u0001Q!\n\u0005-\u0001\"CA+\u0001\u0001\u0007I\u0011BA&\u0011%\t9\u0006\u0001a\u0001\n\u0013\tI\u0006\u0003\u0005\u0002^\u0001\u0001\u000b\u0015BA\u0006\u0011%\ty\u0006\u0001a\u0001\n\u0013\t\u0019\u0001C\u0005\u0002b\u0001\u0001\r\u0011\"\u0003\u0002d!A\u0011q\r\u0001!B\u0013\t)\u0001C\u0005\u0002j\u0001\u0001\r\u0011\"\u0003\u0002l!I\u0011q\u000e\u0001A\u0002\u0013%\u0011\u0011\u000f\u0005\t\u0003k\u0002\u0001\u0015)\u0003\u0002n!I\u0011q\u000f\u0001A\u0002\u0013%\u00111\u0001\u0005\n\u0003s\u0002\u0001\u0019!C\u0005\u0003wB\u0001\"a \u0001A\u0003&\u0011Q\u0001\u0005\n\u0003\u0003\u0003\u0001\u0019!C\u0005\u0003\u0007A\u0011\"a!\u0001\u0001\u0004%I!!\"\t\u0011\u0005%\u0005\u0001)Q\u0005\u0003\u000bAaA\u001c\u0001\u0005\u0002\u0005-\u0005bBAG\u0001\u0011\u0005\u0011q\u0012\u0005\b\u0003\u001b\u0003A\u0011AAV\u0011\u001d\ti\t\u0001C\u0001\u0003\u007fCq!a1\u0001\t\u0003\t)\rC\u0004\u0002L\u0002!\t!!4\t\u000f\u0005=\u0007\u0001\"\u0001\u0002N\"9\u0011\u0011\u001b\u0001\u0005\u0002\u00055\u0007bBAj\u0001\u0011\u0005\u0011Q\u001a\u0005\b\u0003+\u0004A\u0011BA\u0002\u0011\u001d\t9\u000e\u0001C\u0001\u0003sAq!!7\u0001\t\u0003\tY\u0005C\u0004\u0002\\\u0002!\t!!4\t\u000f\u0005u\u0007\u0001\"\u0001\u0002N\"9\u0011q\u001c\u0001\u0005\u0002\u00055\u0007bBAq\u0001\u0011\u0005\u0011Q\u001a\u0005\b\u0003G\u0004A\u0011AAg\u0005A\u0019V/\\7be&TXM\u001d\"vM\u001a,'O\u0003\u0002<y\u0005!1\u000f^1u\u0015\tid(\u0001\u0002nY*\u0011q\bQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0003\n\u000ba!\u00199bG\",'\"A\"\u0002\u0007=\u0014xmE\u0002\u0001\u000b.\u0003\"AR%\u000e\u0003\u001dS\u0011\u0001S\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0015\u001e\u0013a!\u00118z%\u00164\u0007C\u0001'R\u001b\u0005i%B\u0001(P\u0003\tIwNC\u0001Q\u0003\u0011Q\u0017M^1\n\u0005Ik%\u0001D*fe&\fG.\u001b>bE2,\u0017\u0001\u0005:fcV,7\u000f^3e\u001b\u0016$(/[2t\u0007\u0001\u00012A\u00160b\u001d\t9FL\u0004\u0002Y76\t\u0011L\u0003\u0002[)\u00061AH]8pizJ\u0011\u0001S\u0005\u0003;\u001e\u000bq\u0001]1dW\u0006<W-\u0003\u0002`A\n\u00191+Z9\u000b\u0005u;\u0005C\u00012g\u001d\t\u0019G-D\u0001;\u0013\t)'(\u0001\nTk6l\u0017M]=Ck&dG-\u001a:J[Bd\u0017BA4i\u0005\u0019iU\r\u001e:jG*\u0011QMO\u0001\u0015e\u0016\fX/Z:uK\u0012\u001cu.\u001c9NKR\u0014\u0018nY:\u0011\u0007Ys6\u000e\u0005\u0002cY&\u0011Q\u000e\u001b\u0002\u000e\u0007>l\u0007/\u001e;f\u001b\u0016$(/[2\u0002\rqJg.\u001b;?)\r\u0001\u0018O\u001d\t\u0003G\u0002AQaU\u0002A\u0002UCQ![\u0002A\u0002)\f\u0011A\\\u000b\u0002kB\u0011aI^\u0005\u0003o\u001e\u00131!\u00138u\u0003\u0015qw\fJ3r)\tQX\u0010\u0005\u0002Gw&\u0011Ap\u0012\u0002\u0005+:LG\u000fC\u0004\u007f\u000b\u0005\u0005\t\u0019A;\u0002\u0007a$\u0013'\u0001\u0002oA\u0005A1-\u001e:s\u001b\u0016\fg.\u0006\u0002\u0002\u0006A)a)a\u0002\u0002\f%\u0019\u0011\u0011B$\u0003\u000b\u0005\u0013(/Y=\u0011\u0007\u0019\u000bi!C\u0002\u0002\u0010\u001d\u0013a\u0001R8vE2,\u0017\u0001D2veJlU-\u00198`I\u0015\fHc\u0001>\u0002\u0016!Aa\u0010CA\u0001\u0002\u0004\t)!A\u0005dkJ\u0014X*Z1oA\u000591-\u001e:s\u001bJr\u0017aC2veJl%G\\0%KF$2A_A\u0010\u0011!q8\"!AA\u0002\u0005\u0015\u0011\u0001C2veJl%G\u001c\u0011\u0002\r\r,(O]'3\u0003)\u0019WO\u001d:Ne}#S-\u001d\u000b\u0004u\u0006%\u0002\u0002\u0003@\u000f\u0003\u0003\u0005\r!!\u0002\u0002\u000f\r,(O]'3A\u000511-\u001e:s\u0019F\n!bY;se2\u000bt\fJ3r)\rQ\u00181\u0007\u0005\t}F\t\t\u00111\u0001\u0002\u0006\u000591-\u001e:s\u0019F\u0002\u0013\u0001\u0003;pi\u0006d7I\u001c;\u0016\u0005\u0005m\u0002c\u0001$\u0002>%\u0019\u0011qH$\u0003\t1{gnZ\u0001\ri>$\u0018\r\\\"oi~#S-\u001d\u000b\u0004u\u0006\u0015\u0003\u0002\u0003@\u0015\u0003\u0003\u0005\r!a\u000f\u0002\u0013Q|G/\u00197D]R\u0004\u0013A\u0004;pi\u0006dw+Z5hQR\u001cV/\\\u000b\u0003\u0003\u0017\t!\u0003^8uC2<V-[4iiN+Xn\u0018\u0013fcR\u0019!0!\u0015\t\u0011y<\u0012\u0011!a\u0001\u0003\u0017\tq\u0002^8uC2<V-[4iiN+X\u000eI\u0001\u0010o\u0016Lw\r\u001b;TcV\f'/Z*v[\u0006\u0019r/Z5hQR\u001c\u0016/^1sKN+Xn\u0018\u0013fcR\u0019!0a\u0017\t\u0011yT\u0012\u0011!a\u0001\u0003\u0017\t\u0001c^3jO\"$8+];be\u0016\u001cV/\u001c\u0011\u0002\u001b\r,(O],fS\u001eDGoU;n\u0003E\u0019WO\u001d:XK&<\u0007\u000e^*v[~#S-\u001d\u000b\u0004u\u0006\u0015\u0004\u0002\u0003@\u001e\u0003\u0003\u0005\r!!\u0002\u0002\u001d\r,(O],fS\u001eDGoU;nA\u0005\u0019aN\u001c>\u0016\u0005\u00055\u0004#\u0002$\u0002\b\u0005m\u0012a\u00028ou~#S-\u001d\u000b\u0004u\u0006M\u0004\u0002\u0003@!\u0003\u0003\u0005\r!!\u001c\u0002\t9t'\u0010I\u0001\bGV\u0014(/T1y\u0003-\u0019WO\u001d:NCb|F%Z9\u0015\u0007i\fi\b\u0003\u0005\u007fG\u0005\u0005\t\u0019AA\u0003\u0003!\u0019WO\u001d:NCb\u0004\u0013aB2veJl\u0015N\\\u0001\fGV\u0014(/T5o?\u0012*\u0017\u000fF\u0002{\u0003\u000fC\u0001B \u0014\u0002\u0002\u0003\u0007\u0011QA\u0001\tGV\u0014(/T5oAQ\t\u0001/A\u0002bI\u0012$\u0002\"!%\u0002\u0014\u0006\r\u0016qU\u0007\u0002\u0001!9\u0011QS\u0015A\u0002\u0005]\u0015a\u00048p]j+'o\\%uKJ\fGo\u001c:\u0011\u000bY\u000bI*!(\n\u0007\u0005m\u0005M\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\u00191\u0015qT;\u0002\f%\u0019\u0011\u0011U$\u0003\rQ+\b\u000f\\33\u0011\u0019\t)+\u000ba\u0001k\u0006!1/\u001b>f\u0011\u001d\tI+\u000ba\u0001\u0003\u0017\taa^3jO\"$HCBAI\u0003[\u000bi\fC\u0004\u00020*\u0002\r!!-\u0002\u0011%t7\u000f^1oG\u0016\u0004B!a-\u0002:6\u0011\u0011Q\u0017\u0006\u0004\u0003oc\u0014A\u00027j]\u0006dw-\u0003\u0003\u0002<\u0006U&A\u0002,fGR|'\u000fC\u0004\u0002**\u0002\r!a\u0003\u0015\t\u0005E\u0015\u0011\u0019\u0005\b\u0003_[\u0003\u0019AAY\u0003\u0015iWM]4f)\u0011\t\t*a2\t\r\u0005%G\u00061\u0001q\u0003\u0015yG\u000f[3s\u0003\u0011iW-\u00198\u0016\u0005\u0005E\u0016aA:v[\u0006Aa/\u0019:jC:\u001cW-A\u0002ti\u0012\fqbY8naV$XMV1sS\u0006t7-Z\u0001\u0006G>,h\u000e^\u0001\no\u0016Lw\r\u001b;Tk6\f1B\\;n\u001d>t'0\u001a:pg\u0006\u0019Q.\u0019=\u0002\u00075Lg.\u0001\u0004o_JlGJM\u0001\u0007]>\u0014X\u000eT\u0019"
)
public class SummarizerBuffer implements Serializable {
   private final Seq requestedMetrics;
   private final Seq requestedCompMetrics;
   private int n;
   private double[] currMean;
   private double[] currM2n;
   private double[] currM2;
   private double[] currL1;
   private long totalCnt;
   private double totalWeightSum;
   private double weightSquareSum;
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

   public SummarizerBuffer add(final Iterator nonZeroIterator, final int size, final double weight) {
      .MODULE$.require(weight >= (double)0.0F, () -> "sample weight, " + weight + " has to be >= 0.0");
      if (weight == (double)0.0F) {
         return this;
      } else {
         if (this.n() == 0) {
            .MODULE$.require(size > 0, () -> "Vector should have dimension larger than zero.");
            this.n_$eq(size);
            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeMean$.MODULE$)) {
               this.currMean_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeM2n$.MODULE$)) {
               this.currM2n_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeM2$.MODULE$)) {
               this.currM2_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeL1$.MODULE$)) {
               this.currL1_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeWeightSum$.MODULE$)) {
               this.currWeightSum_$eq((double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeNNZ$.MODULE$)) {
               this.nnz_$eq((long[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Long()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeMax$.MODULE$)) {
               this.currMax_$eq((double[])scala.Array..MODULE$.fill(this.n(), (JFunction0.mcD.sp)() -> -Double.MAX_VALUE, scala.reflect.ClassTag..MODULE$.Double()));
            }

            if (this.requestedCompMetrics.contains(SummaryBuilderImpl.ComputeMin$.MODULE$)) {
               this.currMin_$eq((double[])scala.Array..MODULE$.fill(this.n(), (JFunction0.mcD.sp)() -> Double.MAX_VALUE, scala.reflect.ClassTag..MODULE$.Double()));
            }
         }

         .MODULE$.require(this.n() == size, () -> {
            int var10000 = this.n();
            return "Dimensions mismatch when adding new sample. Expecting " + var10000 + " but got " + size + ".";
         });
         if (nonZeroIterator.nonEmpty()) {
            double[] localCurrMean = this.currMean();
            double[] localCurrM2n = this.currM2n();
            double[] localCurrM2 = this.currM2();
            double[] localCurrL1 = this.currL1();
            double[] localCurrWeightSum = this.currWeightSum();
            long[] localNumNonzeros = this.nnz();
            double[] localCurrMax = this.currMax();
            double[] localCurrMin = this.currMin();
            nonZeroIterator.foreach((x0$1) -> {
               $anonfun$add$6(localCurrMax, localCurrMin, localCurrWeightSum, localCurrMean, weight, localCurrM2n, localCurrM2, localCurrL1, localNumNonzeros, x0$1);
               return BoxedUnit.UNIT;
            });
         }

         this.totalWeightSum_$eq(this.totalWeightSum() + weight);
         this.weightSquareSum_$eq(this.weightSquareSum() + weight * weight);
         this.totalCnt_$eq(this.totalCnt() + 1L);
         return this;
      }
   }

   public SummarizerBuffer add(final Vector instance, final double weight) {
      return this.add(instance.nonZeroIterator(), instance.size(), weight);
   }

   public SummarizerBuffer add(final Vector instance) {
      return this.add(instance, (double)1.0F);
   }

   public SummarizerBuffer merge(final SummarizerBuffer other) {
      if (this.totalWeightSum() != (double)0.0F && other.totalWeightSum() != (double)0.0F) {
         .MODULE$.require(this.n() == other.n(), () -> {
            int var10000 = this.n();
            return "Dimensions mismatch when merging with another summarizer. Expecting " + var10000 + " but got " + other.n() + ".";
         });
         this.totalCnt_$eq(this.totalCnt() + other.totalCnt());
         this.totalWeightSum_$eq(this.totalWeightSum() + other.totalWeightSum());
         this.weightSquareSum_$eq(this.weightSquareSum() + other.weightSquareSum());

         for(int i = 0; i < this.n(); ++i) {
            if (this.currWeightSum() != null) {
               double thisWeightSum = this.currWeightSum()[i];
               double otherWeightSum = other.currWeightSum()[i];
               double totalWeightSum = thisWeightSum + otherWeightSum;
               if (totalWeightSum != (double)0.0F && this.currMean() != null) {
                  double deltaMean = other.currMean()[i] - this.currMean()[i];
                  double[] var11 = this.currMean();
                  var11[i] += deltaMean * otherWeightSum / totalWeightSum;
                  if (this.currM2n() != null) {
                     double[] var13 = this.currM2n();
                     var13[i] += other.currM2n()[i] + deltaMean * deltaMean * thisWeightSum * otherWeightSum / totalWeightSum;
                  }
               }

               this.currWeightSum()[i] = totalWeightSum;
            }

            if (this.currM2() != null) {
               double[] var15 = this.currM2();
               var15[i] += other.currM2()[i];
            }

            if (this.currL1() != null) {
               double[] var17 = this.currL1();
               var17[i] += other.currL1()[i];
            }

            if (this.currMax() != null) {
               this.currMax()[i] = scala.math.package..MODULE$.max(this.currMax()[i], other.currMax()[i]);
            }

            if (this.currMin() != null) {
               this.currMin()[i] = scala.math.package..MODULE$.min(this.currMin()[i], other.currMin()[i]);
            }

            if (this.nnz() != null) {
               long[] var19 = this.nnz();
               var19[i] += other.nnz()[i];
            }
         }
      } else if (this.totalWeightSum() == (double)0.0F && other.totalWeightSum() != (double)0.0F) {
         this.n_$eq(other.n());
         if (other.currMean() != null) {
            this.currMean_$eq((double[])other.currMean().clone());
         }

         if (other.currM2n() != null) {
            this.currM2n_$eq((double[])other.currM2n().clone());
         }

         if (other.currM2() != null) {
            this.currM2_$eq((double[])other.currM2().clone());
         }

         if (other.currL1() != null) {
            this.currL1_$eq((double[])other.currL1().clone());
         }

         this.totalCnt_$eq(other.totalCnt());
         this.totalWeightSum_$eq(other.totalWeightSum());
         this.weightSquareSum_$eq(other.weightSquareSum());
         if (other.currWeightSum() != null) {
            this.currWeightSum_$eq((double[])other.currWeightSum().clone());
         }

         if (other.nnz() != null) {
            this.nnz_$eq((long[])other.nnz().clone());
         }

         if (other.currMax() != null) {
            this.currMax_$eq((double[])other.currMax().clone());
         }

         if (other.currMin() != null) {
            this.currMin_$eq((double[])other.currMin().clone());
         }
      }

      return this;
   }

   public Vector mean() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.Mean$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realMean = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());

      for(int i = 0; i < this.n(); ++i) {
         realMean[i] = this.currMean()[i] * (this.currWeightSum()[i] / this.totalWeightSum());
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(realMean);
   }

   public Vector sum() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.Sum$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realSum = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());

      for(int i = 0; i < this.n(); ++i) {
         realSum[i] = this.currMean()[i] * this.currWeightSum()[i];
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(realSum);
   }

   public Vector variance() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.Variance$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realVariance = this.computeVariance();
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(realVariance);
   }

   public Vector std() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.Std$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realVariance = this.computeVariance();
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(realVariance), (JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.sqrt(x), scala.reflect.ClassTag..MODULE$.Double()));
   }

   private double[] computeVariance() {
      double[] realVariance = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());
      double denominator = this.totalWeightSum() - this.weightSquareSum() / this.totalWeightSum();
      if (denominator > (double)0.0F) {
         double[] deltaMean = this.currMean();
         int i = 0;

         for(int len = this.currM2n().length; i < len; ++i) {
            realVariance[i] = scala.math.package..MODULE$.max((this.currM2n()[i] + deltaMean[i] * deltaMean[i] * this.currWeightSum()[i] * (this.totalWeightSum() - this.currWeightSum()[i]) / this.totalWeightSum()) / denominator, (double)0.0F);
         }
      }

      return realVariance;
   }

   public long count() {
      return this.totalCnt();
   }

   public double weightSum() {
      return this.totalWeightSum();
   }

   public Vector numNonzeros() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.NumNonZeros$.MODULE$));
      .MODULE$.require(this.totalCnt() > 0L, () -> "Nothing has been added to this summarizer.");
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.longArrayOps(this.nnz()), (JFunction1.mcDJ.sp)(x$10) -> (double)x$10, scala.reflect.ClassTag..MODULE$.Double()));
   }

   public Vector max() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.Max$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");

      for(int i = 0; i < this.n(); ++i) {
         if (this.nnz()[i] < this.totalCnt() && this.currMax()[i] < (double)0.0F) {
            this.currMax()[i] = (double)0.0F;
         }
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(this.currMax());
   }

   public Vector min() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.Min$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");

      for(int i = 0; i < this.n(); ++i) {
         if (this.nnz()[i] < this.totalCnt() && this.currMin()[i] > (double)0.0F) {
            this.currMin()[i] = (double)0.0F;
         }
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(this.currMin());
   }

   public Vector normL2() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.NormL2$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      double[] realMagnitude = (double[])scala.Array..MODULE$.ofDim(this.n(), scala.reflect.ClassTag..MODULE$.Double());
      int i = 0;

      for(int len = this.currM2().length; i < len; ++i) {
         realMagnitude[i] = scala.math.package..MODULE$.sqrt(this.currM2()[i]);
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(realMagnitude);
   }

   public Vector normL1() {
      .MODULE$.require(this.requestedMetrics.contains(SummaryBuilderImpl.NormL1$.MODULE$));
      .MODULE$.require(this.totalWeightSum() > (double)0, () -> "Nothing has been added to this summarizer.");
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(this.currL1());
   }

   // $FF: synthetic method
   public static final void $anonfun$add$6(final double[] localCurrMax$1, final double[] localCurrMin$1, final double[] localCurrWeightSum$1, final double[] localCurrMean$1, final double weight$1, final double[] localCurrM2n$1, final double[] localCurrM2$1, final double[] localCurrL1$1, final long[] localNumNonzeros$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int index = x0$1._1$mcI$sp();
         double value = x0$1._2$mcD$sp();
         if (localCurrMax$1 != null && localCurrMax$1[index] < value) {
            localCurrMax$1[index] = value;
         }

         if (localCurrMin$1 != null && localCurrMin$1[index] > value) {
            localCurrMin$1[index] = value;
         }

         if (localCurrWeightSum$1 != null) {
            if (localCurrMean$1 != null) {
               double prevMean = localCurrMean$1[index];
               double diff = value - prevMean;
               localCurrMean$1[index] = prevMean + weight$1 * diff / (localCurrWeightSum$1[index] + weight$1);
               if (localCurrM2n$1 != null) {
                  localCurrM2n$1[index] += weight$1 * (value - localCurrMean$1[index]) * diff;
               }
            }

            localCurrWeightSum$1[index] += weight$1;
         }

         if (localCurrM2$1 != null) {
            localCurrM2$1[index] += weight$1 * value * value;
         }

         if (localCurrL1$1 != null) {
            localCurrL1$1[index] += weight$1 * scala.math.package..MODULE$.abs(value);
         }

         if (localNumNonzeros$1 != null) {
            int var10002 = localNumNonzeros$1[index]++;
            BoxedUnit var20 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   public SummarizerBuffer(final Seq requestedMetrics, final Seq requestedCompMetrics) {
      this.requestedMetrics = requestedMetrics;
      this.requestedCompMetrics = requestedCompMetrics;
      this.n = 0;
      this.currMean = null;
      this.currM2n = null;
      this.currM2 = null;
      this.currL1 = null;
      this.totalCnt = 0L;
      this.totalWeightSum = (double)0.0F;
      this.weightSquareSum = (double)0.0F;
      this.currWeightSum = null;
      this.nnz = null;
      this.currMax = null;
      this.currMin = null;
   }

   public SummarizerBuffer() {
      this((Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new SummaryBuilderImpl.Metric[]{SummaryBuilderImpl.Mean$.MODULE$, SummaryBuilderImpl.Sum$.MODULE$, SummaryBuilderImpl.Variance$.MODULE$, SummaryBuilderImpl.Std$.MODULE$, SummaryBuilderImpl.Count$.MODULE$, SummaryBuilderImpl.NumNonZeros$.MODULE$, SummaryBuilderImpl.Max$.MODULE$, SummaryBuilderImpl.Min$.MODULE$, SummaryBuilderImpl.NormL2$.MODULE$, SummaryBuilderImpl.NormL1$.MODULE$})), (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new SummaryBuilderImpl.ComputeMetric[]{SummaryBuilderImpl.ComputeMean$.MODULE$, SummaryBuilderImpl.ComputeM2n$.MODULE$, SummaryBuilderImpl.ComputeM2$.MODULE$, SummaryBuilderImpl.ComputeL1$.MODULE$, SummaryBuilderImpl.ComputeWeightSum$.MODULE$, SummaryBuilderImpl.ComputeNNZ$.MODULE$, SummaryBuilderImpl.ComputeMax$.MODULE$, SummaryBuilderImpl.ComputeMin$.MODULE$})));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
