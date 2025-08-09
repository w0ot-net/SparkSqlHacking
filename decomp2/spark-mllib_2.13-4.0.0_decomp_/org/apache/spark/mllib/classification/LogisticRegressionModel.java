package org.apache.spark.mllib.classification;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.classification.impl.GLMClassificationModel;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.pmml.PMMLExportable;
import org.apache.spark.mllib.regression.GeneralizedLinearModel;
import org.apache.spark.mllib.util.Saveable;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg\u0001B\u000f\u001f\u0001%B\u0001B\u0014\u0001\u0003\u0006\u0004%\te\u0014\u0005\n?\u0002\u0011\t\u0011)A\u0005!\u0002D\u0001B\u0019\u0001\u0003\u0006\u0004%\te\u0019\u0005\nS\u0002\u0011\t\u0011)A\u0005I*D\u0001\u0002\u001c\u0001\u0003\u0006\u0004%\t!\u001c\u0005\ti\u0002\u0011\t\u0011)A\u0005]\"Aa\u000f\u0001BC\u0002\u0013\u0005Q\u000e\u0003\u0005y\u0001\t\u0005\t\u0015!\u0003o\u0011\u0015Q\b\u0001\"\u0001|\u0011!\ti\u0001\u0001b\u0001\n\u0013i\u0007bBA\b\u0001\u0001\u0006IA\u001c\u0005\n\u0003#\u0001!\u0019!C\u0005\u0003'A\u0001\"a\u0007\u0001A\u0003%\u0011Q\u0003\u0005\u0007u\u0002!\t!!\b\t\u0013\u0005\u0015\u0002\u00011A\u0005\n\u0005\u001d\u0002\"CA\u0018\u0001\u0001\u0007I\u0011BA\u0019\u0011!\ti\u0004\u0001Q!\n\u0005%\u0002bBA \u0001\u0011\u0005\u0011\u0011\t\u0005\b\u0003\u0013\u0002A\u0011AA\u0014\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u001fBq!a\u0015\u0001\t#\n)\u0006C\u0004\u0002b\u0001!\t%a\u0019\t\u000f\u0005\u001d\u0005\u0001\"\u0011\u0002\n\u001e9\u0011\u0011\u0013\u0010\t\u0002\u0005MeAB\u000f\u001f\u0011\u0003\t)\n\u0003\u0004{3\u0011\u0005\u0011\u0011\u0017\u0005\b\u0003gKB\u0011IA[\u0011%\ti,GA\u0001\n\u0013\tyLA\fM_\u001eL7\u000f^5d%\u0016<'/Z:tS>tWj\u001c3fY*\u0011q\u0004I\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\t\t#%A\u0003nY2L'M\u0003\u0002$I\u0005)1\u000f]1sW*\u0011QEJ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\n1a\u001c:h\u0007\u0001\u0019b\u0001\u0001\u00161i\tC\u0005CA\u0016/\u001b\u0005a#BA\u0017!\u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0003_1\u0012acR3oKJ\fG.\u001b>fI2Kg.Z1s\u001b>$W\r\u001c\t\u0003cIj\u0011AH\u0005\u0003gy\u00111c\u00117bgNLg-[2bi&|g.T8eK2\u0004\"!N \u000f\u0005YbdBA\u001c;\u001b\u0005A$BA\u001d)\u0003\u0019a$o\\8u}%\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>}\u00059\u0001/Y2lC\u001e,'\"A\u001e\n\u0005\u0001\u000b%\u0001D*fe&\fG.\u001b>bE2,'BA\u001f?!\t\u0019e)D\u0001E\u0015\t)\u0005%\u0001\u0003vi&d\u0017BA$E\u0005!\u0019\u0016M^3bE2,\u0007CA%M\u001b\u0005Q%BA&!\u0003\u0011\u0001X.\u001c7\n\u00055S%A\u0004)N\u001b2+\u0005\u0010]8si\u0006\u0014G.Z\u0001\bo\u0016Lw\r\u001b;t+\u0005\u0001\u0006CA)U\u001b\u0005\u0011&BA*!\u0003\u0019a\u0017N\\1mO&\u0011QK\u0015\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\u00059V\f\u0005\u0002Y76\t\u0011L\u0003\u0002[E\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005qK&!B*j]\u000e,\u0017%\u00010\u0002\u000bEr\u0003G\f\u0019\u0002\u0011],\u0017n\u001a5ug\u0002J!A\u0014\u0018)\u0007\t9V,A\u0005j]R,'oY3qiV\tA\r\u0005\u0002fM6\ta(\u0003\u0002h}\t1Ai\\;cY\u0016D3aA,^\u0003)Ig\u000e^3sG\u0016\u0004H\u000fI\u0005\u0003E:B3\u0001B,^\u0003-qW/\u001c$fCR,(/Z:\u0016\u00039\u0004\"!Z8\n\u0005At$aA%oi\"\u001aQa\u0016:\"\u0003M\fQ!\r\u00184]A\nAB\\;n\r\u0016\fG/\u001e:fg\u0002B3AB,s\u0003)qW/\\\"mCN\u001cXm\u001d\u0015\u0004\u000f]\u0013\u0018a\u00038v[\u000ec\u0017m]:fg\u0002B3\u0001C,s\u0003\u0019a\u0014N\\5u}Q9A0`@\u0002\u0004\u0005\u001d\u0001CA\u0019\u0001\u0011\u0015q\u0015\u00021\u0001QQ\rix+\u0018\u0005\u0006E&\u0001\r\u0001\u001a\u0015\u0004\u007f^k\u0006\"\u00027\n\u0001\u0004q\u0007\u0006BA\u0002/JDQA^\u0005A\u00029DC!a\u0002Xe\"\u001a\u0011b\u0016:\u0002!\u0011\fG/Y,ji\"\u0014\u0015.Y:TSj,\u0017!\u00053bi\u0006<\u0016\u000e\u001e5CS\u0006\u001c8+\u001b>fA\u0005aq/Z5hQR\u001c\u0018I\u001d:bsV\u0011\u0011Q\u0003\t\u0005K\u0006]A-C\u0002\u0002\u001ay\u0012Q!\u0011:sCf\fQb^3jO\"$8/\u0011:sCf\u0004C#\u0002?\u0002 \u0005\u0005\u0002\"\u0002(\u000f\u0001\u0004\u0001\u0006\"\u00022\u000f\u0001\u0004!\u0007f\u0001\bX;\u0006IA\u000f\u001b:fg\"|G\u000eZ\u000b\u0003\u0003S\u0001B!ZA\u0016I&\u0019\u0011Q\u0006 \u0003\r=\u0003H/[8o\u00035!\bN]3tQ>dGm\u0018\u0013fcR!\u00111GA\u001d!\r)\u0017QG\u0005\u0004\u0003oq$\u0001B+oSRD\u0011\"a\u000f\u0011\u0003\u0003\u0005\r!!\u000b\u0002\u0007a$\u0013'\u0001\u0006uQJ,7\u000f[8mI\u0002\nAb]3u)\"\u0014Xm\u001d5pY\u0012$B!a\u0011\u0002F5\t\u0001\u0001\u0003\u0004\u0002&I\u0001\r\u0001\u001a\u0015\u0004%]k\u0016\u0001D4fiRC'/Z:i_2$\u0007fA\nXe\u0006q1\r\\3beRC'/Z:i_2$GCAA\"Q\r!r+X\u0001\raJ,G-[2u!>Lg\u000e\u001e\u000b\bI\u0006]\u00131LA0\u0011\u0019\tI&\u0006a\u0001!\u0006QA-\u0019;b\u001b\u0006$(/\u001b=\t\r\u0005uS\u00031\u0001Q\u000319X-[4ii6\u000bGO]5y\u0011\u0015\u0011W\u00031\u0001e\u0003\u0011\u0019\u0018M^3\u0015\r\u0005M\u0012QMA9\u0011\u001d\t9G\u0006a\u0001\u0003S\n!a]2\u0011\t\u0005-\u0014QN\u0007\u0002E%\u0019\u0011q\u000e\u0012\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000f\u0005Md\u00031\u0001\u0002v\u0005!\u0001/\u0019;i!\u0011\t9(a \u000f\t\u0005e\u00141\u0010\t\u0003oyJ1!! ?\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011QAB\u0005\u0019\u0019FO]5oO*\u0019\u0011Q\u0010 )\u0007Y9&/\u0001\u0005u_N#(/\u001b8h)\t\t)\b\u000b\u0003\u0001/\u00065\u0015EAAH\u0003\u0015\u0001d\u0006\u000f\u00181\u0003]aunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u000223M9\u0011$a&\u0002\u001e\u0006\r\u0006cA3\u0002\u001a&\u0019\u00111\u0014 \u0003\r\u0005s\u0017PU3g!\u0011\u0019\u0015q\u0014?\n\u0007\u0005\u0005FI\u0001\u0004M_\u0006$WM\u001d\t\u0005\u0003K\u000by+\u0004\u0002\u0002(*!\u0011\u0011VAV\u0003\tIwN\u0003\u0002\u0002.\u0006!!.\u0019<b\u0013\r\u0001\u0015q\u0015\u000b\u0003\u0003'\u000bA\u0001\\8bIR)A0a.\u0002:\"9\u0011qM\u000eA\u0002\u0005%\u0004bBA:7\u0001\u0007\u0011Q\u000f\u0015\u00047]\u0013\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAAa!\u0011\t\u0019-!3\u000e\u0005\u0005\u0015'\u0002BAd\u0003W\u000bA\u0001\\1oO&!\u00111ZAc\u0005\u0019y%M[3di\"\u001a\u0011d\u0016:)\u0007a9&\u000f"
)
public class LogisticRegressionModel extends GeneralizedLinearModel implements ClassificationModel, Saveable, PMMLExportable {
   private final int numFeatures;
   private final int numClasses;
   private final int dataWithBiasSize;
   private final double[] weightsArray;
   private Option threshold;

   public static LogisticRegressionModel load(final SparkContext sc, final String path) {
      return LogisticRegressionModel$.MODULE$.load(sc, path);
   }

   public void toPMML(final String localPath) {
      PMMLExportable.toPMML$(this, (String)localPath);
   }

   public void toPMML(final SparkContext sc, final String path) {
      PMMLExportable.toPMML$(this, sc, path);
   }

   public void toPMML(final OutputStream outputStream) {
      PMMLExportable.toPMML$(this, (OutputStream)outputStream);
   }

   public String toPMML() {
      return PMMLExportable.toPMML$(this);
   }

   public JavaRDD predict(final JavaRDD testData) {
      return ClassificationModel.predict$(this, testData);
   }

   public Vector weights() {
      return super.weights();
   }

   public double intercept() {
      return super.intercept();
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public int numClasses() {
      return this.numClasses;
   }

   private int dataWithBiasSize() {
      return this.dataWithBiasSize;
   }

   private double[] weightsArray() {
      return this.weightsArray;
   }

   private Option threshold() {
      return this.threshold;
   }

   private void threshold_$eq(final Option x$1) {
      this.threshold = x$1;
   }

   public LogisticRegressionModel setThreshold(final double threshold) {
      this.threshold_$eq(new Some(BoxesRunTime.boxToDouble(threshold)));
      return this;
   }

   public Option getThreshold() {
      return this.threshold();
   }

   public LogisticRegressionModel clearThreshold() {
      this.threshold_$eq(.MODULE$);
      return this;
   }

   public double predictPoint(final Vector dataMatrix, final Vector weightMatrix, final double intercept) {
      scala.Predef..MODULE$.require(dataMatrix.size() == this.numFeatures());
      if (this.numClasses() == 2) {
         double margin = BLAS$.MODULE$.dot(weightMatrix, dataMatrix) + intercept;
         double score = (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(-margin));
         Option var11 = this.threshold();
         if (var11 instanceof Some) {
            Some var12 = (Some)var11;
            double t = BoxesRunTime.unboxToDouble(var12.value());
            return score > t ? (double)1.0F : (double)0.0F;
         } else if (.MODULE$.equals(var11)) {
            return score;
         } else {
            throw new MatchError(var11);
         }
      } else {
         IntRef bestClass = IntRef.create(0);
         DoubleRef maxMargin = DoubleRef.create((double)0.0F);
         boolean withBias = dataMatrix.size() + 1 == this.dataWithBiasSize();
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.numClasses() - 1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            DoubleRef margin = DoubleRef.create((double)0.0F);
            dataMatrix.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> margin.elem += value * this.weightsArray()[i * this.dataWithBiasSize() + index]);
            if (withBias) {
               margin.elem += this.weightsArray()[i * this.dataWithBiasSize() + dataMatrix.size()];
            }

            if (margin.elem > maxMargin.elem) {
               maxMargin.elem = margin.elem;
               bestClass.elem = i + 1;
            }
         });
         return (double)bestClass.elem;
      }
   }

   public void save(final SparkContext sc, final String path) {
      GLMClassificationModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.getClass().getName(), this.numFeatures(), this.numClasses(), this.weights(), this.intercept(), this.threshold());
   }

   public String toString() {
      String var10000 = super.toString();
      return var10000 + ", numClasses = " + this.numClasses() + ", threshold = " + this.threshold().getOrElse(() -> "None");
   }

   public LogisticRegressionModel(final Vector weights, final double intercept, final int numFeatures, final int numClasses) {
      super(weights, intercept);
      this.numFeatures = numFeatures;
      this.numClasses = numClasses;
      ClassificationModel.$init$(this);
      PMMLExportable.$init$(this);
      if (numClasses == 2) {
         scala.Predef..MODULE$.require(weights.size() == numFeatures, () -> {
            int var10000 = this.numFeatures();
            return "LogisticRegressionModel with numClasses = 2 was given non-matching values: numFeatures = " + var10000 + ", but weights.size = " + this.weights().size();
         });
      } else {
         int weightsSizeWithoutIntercept = (numClasses - 1) * numFeatures;
         int weightsSizeWithIntercept = (numClasses - 1) * (numFeatures + 1);
         scala.Predef..MODULE$.require(weights.size() == weightsSizeWithoutIntercept || weights.size() == weightsSizeWithIntercept, () -> {
            int var10000 = this.numClasses();
            return "LogisticRegressionModel.load with numClasses = " + var10000 + " and numFeatures = " + this.numFeatures() + " expected weights of length " + weightsSizeWithoutIntercept + " (without intercept) or " + weightsSizeWithIntercept + " (with intercept), but was given weights of length " + this.weights().size();
         });
      }

      this.dataWithBiasSize = weights.size() / (numClasses - 1);
      if (weights instanceof DenseVector var10) {
         this.weightsArray = var10.values();
         this.threshold = new Some(BoxesRunTime.boxToDouble((double)0.5F));
      } else {
         throw new IllegalArgumentException("weights only supports dense vector but got type " + weights.getClass() + ".");
      }
   }

   public LogisticRegressionModel(final Vector weights, final double intercept) {
      this(weights, intercept, weights.size(), 2);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
